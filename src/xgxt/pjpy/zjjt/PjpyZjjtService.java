package xgxt.pjpy.zjjt;

import java.util.ArrayList;
import java.util.List;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.jaxen.function.RoundFunction;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.zjcm.xfjs.PjpyXfjsService;
import xgxt.rcsw.gzdx.RcswGzdxModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;
import xgxt.wjcf.zjcm.WjcfZjcmDAO;
import xgxt.wjcf.zjcm.WjcfZjcmModel;

public class PjpyZjjtService extends PjpyTyService {

	PjpyZjjtDAO dao = new PjpyZjjtDAO();
	
	/**
	 * ��ø��Ի���ͷ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {
		
		DAO dao = DAO.getInstance();
		
		String[] colListCN = null;
		String[] colListEN = null;

		//���з�ͳ��
		if ("cxftj".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "ѧ��", "����", "�Ա�", "ѧ��", "ѧ��", "ѧԺ",
					"רҵ", "�༶", "У��", "¥��", "����", "���Һ�","�ӷ���Ŀ��","�ӷֺϼ�","������Ŀ��","���ֺϼ�","�����ܷ�","Ĭ�Ϸ�","���з�" };

			colListEN = new String[] { "xh", "xm", "xb", "xn", "xqm", "xymc",
					"zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh","jfsm","jffs","kfsm","kffs","clzf","mrf","cxf" };
		} else if("cxftjxn".equalsIgnoreCase(lx)){
			colListCN = new String[] { "ѧ��", "����", "�Ա�", "ѧ��", "ѧԺ",
					"רҵ", "�༶", "У��", "¥��", "����", "���Һ�","�ӷ���Ŀ��","�ӷֺϼ�","������Ŀ��","���ֺϼ�","�����ܷ�","Ĭ�Ϸ�","���з�" };

			colListEN = new String[] { "xh", "xm", "xb", "xn", "xymc",
					"zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh","jfsm","jffs","kfsm","kffs","clzf","mrf","cxf" };
		}
		return dao.arrayToList(colListEN, colListCN);
	}
	
	/**
	 * ������з�
	 * 
	 * @author luojw
	 */
	public Boolean saveCxf(PjpyZjjtModel model, String realTable)
			throws Exception {

		String[] arrzd = new String[] { "cxbz", "fz", "jb1", "jb2", "jb3", "jjf", "pjxh", "rq" };
		String[] onezd = new String[] { "xn", "xq" };
		String pk = "pjxh||xn||xq";
		
		// ��������
		String[] jjf = model.getJjf();
		String[] pjxh = null;
		String[] pkValue = null;
		
		if (jjf != null && jjf.length > 0) {
			pjxh = new String[jjf.length];
			pkValue = new String[jjf.length];
			for (int i = 0; i < jjf.length; i++) {
				pjxh[i] = model.getXh();
				pkValue[i] = model.getXh() + model.getXn() + model.getXq();
			}
			model.setPjxh(pjxh);
		}
			
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		
		//ִ�в�����������
		boolean result = savePjpy(saveForm, model);
		
		return result;
	}
	
	/**
	 * ��ò��з�ͳ���б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getCxfTjList(PjpyTyForm model)
			throws Exception {
		
		// ͳ������
		List<HashMap<String, String>> tjList = dao.getCxfTjList(model);
		// ѧ���б�
		List<HashMap<String, String>> xhList = dao.getTjXhList(model);
		// ����б�
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		if (xhList != null && xhList.size() > 0) {
			
			for (int i = 0; i < xhList.size(); i++) {

				HashMap<String, String> xhMap = xhList.get(i);
				// ѧ��
				String xh = xhMap.get("xh");
				// ����
				String xm = xhMap.get("xm");
				// �Ա�
				String xb = xhMap.get("xb");
				// ѧ��
				String xn = xhMap.get("xn");
				// ѧ��
				String xq = xhMap.get("xq");
				String xqm = xhMap.get("xqm");
				// Ĭ��ֵ
				String mrz = xhMap.get("mrz");
				
				// ����ǰ���ѧ��ͳ��
				if("xn".equalsIgnoreCase(model.getTjfs())){
					mrz = dao.getXnMrz(xn);
				}
				
				// ѧԺ����
				String xymc = xhMap.get("xymc");
				// רҵ����
				String zymc = xhMap.get("zymc");
				// �༶����
				String bjmc = xhMap.get("bjmc");
				// У������
				String xqmc = xhMap.get("xqmc");
				xqmc = Base.isNull(xqmc)?"":xqmc;
				// ¥������
				String ldmc = xhMap.get("ldmc");
				ldmc = Base.isNull(ldmc)?"":ldmc;
				// ����
				String cs = xhMap.get("cs");
				cs = Base.isNull(cs)?"":cs;
				// ���Һ�
				String qsh = xhMap.get("qsh");
				qsh = Base.isNull(qsh)?"":qsh;
				// �ܷ�
				float zf = Base.isNull(mrz) ? 0 : Float.parseFloat(mrz);
				int jfsm = 0;
				float jffs = 0;
				
				int kfsm = 0;
				float kffs = 0;

				if (tjList != null && tjList.size() > 0) {

					for (int j = 0; j < tjList.size(); j++) {

						HashMap<String, String> tjMap = tjList.get(j);

						// ����ѧ��
						String pjxh = tjMap.get("xh");
						// ѧ��
						String pjxn = tjMap.get("xn");
						// ѧ��
						String pjxq = tjMap.get("xq");
						// �Ӽ���
						String jjf = tjMap.get("jjf");
						// ��ֵ
						float fz = Base.isNull(tjMap.get("fz")) ? 0 : Float
								.parseFloat(tjMap.get("fz"));
						
						if("xq".equalsIgnoreCase(model.getTjfs())){
							if (xh.equalsIgnoreCase(pjxh)
									&& xn.equalsIgnoreCase(pjxn)
									&& xq.equalsIgnoreCase(pjxq)) {
								if ("�ӷ�".equalsIgnoreCase(jjf)) {
									zf += fz;
									jfsm ++;
									jffs += fz;
								} else if ("����".equalsIgnoreCase(jjf)) {
									zf -= fz;
									kfsm ++;
									kffs += fz;
								}
							}
						}else{
							if (xh.equalsIgnoreCase(pjxh)
									&& xn.equalsIgnoreCase(pjxn)) {
								if ("�ӷ�".equalsIgnoreCase(jjf)) {
									zf += fz;
									jfsm ++;
									jffs += fz;
								} else if ("����".equalsIgnoreCase(jjf)) {
									zf -= fz;
									kfsm ++;
									kffs += fz;
								}
							}
						}
					}
				}

				// ���з�
				String cxf = String.valueOf(zf);
				String clzf = String.valueOf(jffs-kffs);
				
				String[] info = null;
				if("xq".equalsIgnoreCase(model.getTjfs())){
					info = new String[] { xh, xm, xb, xn, xqm, xymc, zymc,
						bjmc, xqmc, ldmc, cs, qsh,String.valueOf(jfsm),String.valueOf(jffs),String.valueOf(kfsm),String.valueOf(kffs),clzf,mrz,cxf };
				}else {
					info = new String[] { xh, xm, xb, xn, xymc, zymc,
							bjmc, xqmc, ldmc, cs, qsh,String.valueOf(jfsm),String.valueOf(jffs),String.valueOf(kfsm),String.valueOf(kffs),clzf,mrz,cxf };
				}
				
				list.add(info);
			}
		}
		return list;
	}
	
	/**
	 * ��ò��з�ͳ���б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getCxfTjInfo(PjpyTyForm model) throws Exception {

		// ����б�
		ArrayList<String[]> list = getCxfTjList(model);

		// ��ҳ
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		if (list != null && list.size() > 0) {

			Pages pages = model.getPages();
			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}

		return rsList;
	}
	
	
	
	/**
	 * ���з�ͳ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxfTj(PjpyTyForm model,User user) throws Exception {
		
		if("xq".equalsIgnoreCase(model.getTjfs())){
			return dao.getCxftjByXq(model,user);
		}else{
			return dao.getCxftjByXn(model,user);
		}
	}
	
	
	
	/**
	 * ��ӡͳ�Ʊ���
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void printCxfTjbb(PjpyTyForm model, OutputStream os)
			throws Exception {

		// ���ñ���
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("ͳ�Ʊ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);	

		List<HashMap<String, String>> topTr = null;
		
		if("xq".equalsIgnoreCase(model.getTjfs())){
			topTr = getTopTr("cxftj");
		}else{
			topTr = getTopTr("cxftjxn");
		}
		
		// ����ͷ
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 0, map.get("cn"), wcf2));
			}	
		}
		
		ArrayList<String[]> list = getCxfTjList(model);
		
		//�������
		if (list != null && list.size() > 0) {
			
			for (int i = 0; i < list.size(); i++) {
				
				String[] info = list.get(i);
				
				if (info != null && info.length > 0) {
					
					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i+1, info[j], wcf2));
					}
				}
			}
		}
				
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��ò��з�ѧ������б�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getCxfXxList(PjpyTyForm model) {
		return dao.getCxfXxList(model);
	}
	
	/**
	 * ɾ�����з�
	 * 
	 * @author luojw
	 */
	public Boolean delCxf(PjpyZjjtModel model, String realTable)
			throws Exception {

		boolean result = false;

		String[] checkVal = model.getCheckVal();

		if (checkVal != null && checkVal.length > 0) {
			String pk = "id";

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setPk(pk);
			saveForm.setPkValue(checkVal);

			result = delPjpy(saveForm, model);

		}
		return result;
	}

	
	
	/**
	 * ��ʼ�����з�
	 * @param model
	 * @return
	 */
	public boolean initCxf(PjpyTyForm model){
		
		try {
			return dao.initCxf(model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
