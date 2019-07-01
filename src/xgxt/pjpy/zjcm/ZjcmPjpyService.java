package xgxt.pjpy.zjcm;

import java.util.ArrayList;
import java.util.List;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.jaxen.function.RoundFunction;

import common.Globals;

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
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.zjcm.xfjs.PjpyXfjsService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;
import xgxt.wjcf.zjcm.WjcfZjcmDAO;
import xgxt.wjcf.zjcm.WjcfZjcmModel;

public class ZjcmPjpyService extends PjpyTyService {

	ZjcmPjpyDAO dao = new ZjcmPjpyDAO();
	
	/**
	 * ��ø��Ի���ͷ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {
		DAO dao = DAO.getInstance();
		String[] colListCN = new String[] { "ѧ��", "����", "�Ա�", "�꼶", "�༶",
				"����ѧ��", "����ѧ��", "������/�����", "������/�����", "������/�����", "������/�����",
				"�ۺϷ�", "���ν���" };
		String[] colListEN = new String[] { "xh", "xm", "xb", "nj", "bjmc",
				"xn", "xq", "dyf", "zyf", "tyf", "nlf", "zhf", "kkjs" };

		if ("jxjxn".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "ѧ��", "����", "�Ա�", "���������", "��������",
					"���������", "��������", "���������", "��������", "���������", "��������", "�ۺϷ�",
					"�ۺ�����" };
			colListEN = new String[] { "xh", "xm", "xb", "dyzhf", "dypm",
					"zyzhf", "zypm", "tyzhf", "typm", "nlzhf", "nlpm", "zhf",
					"zhpm" };
		}
		return dao.arrayToList(colListEN, colListCN);
	}
	
	/**
	 * ��ÿ�������б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKkList(ZjcmPjpyModel model)
			throws Exception {

		PjpyXfjsService xfService = new PjpyXfjsService();

		String xn = model.getXn();
		String xq = model.getXq();

		List<HashMap<String, String>> kkList = xfService.getXskqqk("", xn, xq,
				"xy");
		
		String tableName = "VIEW_ZJCM_PJPY_KKCSCX";
		
		String[] colList = new String[] { "xh", "kkcs" };

		StringBuilder query = new StringBuilder();
		query.append(" where xn = '" + xn + "'");
		query.append(" and xq = '" + xq + "'");
		
		List<HashMap<String, String>> whList = CommonQueryDAO
				.commonQueryforList(tableName, query.toString(),
						new String[] {}, colList, "");

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (whList != null && whList.size() > 0) {
			for (int i = 0; i < whList.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xh", whList.get(i).get("xh"));
				map.put("kkjs", whList.get(i).get("kkcs"));
				list.add(map);
			}
		}
		
		if (kkList != null && kkList.size() > 0) {
			
			for (int i = 0; i < kkList.size(); i++) {
				
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xh", kkList.get(i).get("xh"));
				map.put("kkjs", kkList.get(i).get("kkjs"));
				
				String kkxh = kkList.get(i).get("xh");
				
				boolean flag = true;
				
				for (int j = 0; j < list.size(); j++) {

					String xh = list.get(j).get("xh");
					
					if(xh.equalsIgnoreCase(kkxh)){
						flag = false;
					}
				}
				
				if(flag){
					list.add(map);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * �����������(�ۺϷ�)�б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getZhfList(ZjcmPjpyModel model) throws Exception {

		String[] colList = new String[] { "xh", "xm", "xb", "nj", "bjmc", "xn",
				"xqmc", "dyf", "dyzhf", "zyf", "zyzhf", "tyf", "tyzhf", "nlf",
				"nlzhf", "zhf" };
		
		String table = getZhfTable(model);
		ArrayList<String[]> list = new ArrayList<String[]>();
		ArrayList<String[]> zhfList  = new ArrayList<String[]>();
		ArrayList<String[]> zhfNewList = dao.getZhfList(table, model, colList);
		
		if (zhfNewList != null && zhfNewList.size() > 0) {
			
			for (int i = 0; i < zhfNewList.size(); i++) {
				
				String[] zhfInfo = zhfNewList.get(i);
				
				String dyzhf = dao.setNumZero(zhfInfo[8]);
				String zyzhf = dao.setNumZero(zhfInfo[10]);
				String tyzhf = dao.setNumZero(zhfInfo[12]);
				String nlzhf = dao.setNumZero(zhfInfo[14]);
				String zhf = dao.setNumZero(zhfInfo[15]);
				
				zhfInfo[8] = dyzhf;
				zhfInfo[10] = zyzhf;
				zhfInfo[12] = tyzhf;
				zhfInfo[14] = nlzhf;
				zhfInfo[15] = zhf;
				
				zhfList.add(zhfInfo);
			}
		}
		
		//List<HashMap<String, String>> kkList = xfService.getXskqqk("", xn, xq, "xy");
		List<HashMap<String, String>> kkList = getKkList(model);

		if (zhfList != null && zhfList.size() > 0) {
			for (int i = 0; i < zhfList.size(); i++) {
				// �ع��������ʹ�ۺϷ��б�����һ��ֵ�����ν�����
				String[] rs = new String[zhfList.get(i).length + 1];
				for (int k = 0; k < zhfList.get(i).length; k++) {
					rs[k] = zhfList.get(i)[k];
				}
				rs[zhfList.get(i).length] = "��";
				String xh = rs[0];
				if (!Base.isNull(xh)) {
					if (kkList != null && kkList.size() != 0) {
						for (int j = 0; j < kkList.size(); j++) {
							HashMap<String, String> map = kkList.get(j);
							// ������ѧ��
							String kkxh = map.get("xh");
							// ���ν���
							String kkjs = map.get("kkjs");
							// �жϷ����Ƿ���������
							if (xh.equalsIgnoreCase(kkxh)) {
								rs[zhfList.get(i).length] = kkjs;
							}
						}
					}
				}
				list.add(rs);
			}	
		}
		return list;
	}
	
	/**
	 * ����ۺ����ʷָ������
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getZhfBl(ZjcmPjpyModel model,
			String[] colList) {
		return dao.getZhfBl(model, colList);
	}
	
	/**
	 * ����ۺ����ʷ�Table
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public String getZhfTable(ZjcmPjpyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		// ��ø�����ֵ����
		String[] bl = new String[] { "dyfbl", "zyfbl", "tyfbl", "nlfbl" };
		HashMap<String, String> map = getZhfBl(model, bl);

		String xn = model.getXn();
		String xq = model.getXq();
		String dyfbl = map.get("dyfbl");
		String zyfbl = map.get("zyfbl");
		String tyfbl = map.get("tyfbl");
		String nlfbl = map.get("nlfbl");
		
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();
		
		StringBuffer sql = new StringBuffer();
		sql.append("(select xh, xm, xb, nj, xydm, xymc, zydm, zymc, bjdm, bjmc, xn, xq, xqmc, dyf, zyf, tyf, nlf,");
		sql.append("nvl(case when instr(to_char(round(dyzhf, 2)),'.',1,1)=1 then '0'||to_char(round(dyzhf, 2)) else to_char(round(dyzhf, 2)) end, '0') dyzhf, ");
		sql.append("nvl(case when instr(to_char(round(zyzhf, 2)),'.',1,1)=1 then '0'||to_char(round(zyzhf, 2)) else to_char(round(zyzhf, 2)) end, '0') zyzhf, ");
		sql.append("nvl(case when instr(to_char(round(tyzhf, 2)),'.',1,1)=1 then '0'||to_char(round(tyzhf, 2)) else to_char(round(tyzhf, 2)) end, '0') tyzhf, ");
		sql.append("nvl(case when instr(to_char(round(nlzhf, 2)),'.',1,1)=1 then '0'||to_char(round(nlzhf, 2)) else to_char(round(nlzhf, 2)) end, '0') nlzhf, ");
		sql.append("nvl(case when instr(to_char(round(zhf, 2)),'.',1,1)=1 then '0'||to_char(round(zhf, 2)) else to_char(round(zhf, 2)) end, '0') zhf from (");
		sql.append("select a.xh,a.xm,a.xb,a.nj,a.xydm, ");
		sql.append("a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc, '");
		sql.append(xn + "' xn,'" + xq + "' xq,(select xqmc from ");
		sql.append("xqdzb where xqdm = '" + xq + "' )xqmc, ");
		sql.append("nvl(b.dyf,0) dyf,nvl(b.zyf,0) zyf,nvl(b.tyf,0) tyf,nvl(b.nlf,0) nlf,");
		sql.append("nvl(case when instr(to_char(b.dyf * "+dyfbl+" / 100),'.',1,1)=1 then '0'||to_char(b.dyf * "+dyfbl+" / 100) else to_char(b.dyf * "+dyfbl+" / 100) end, '0') dyzhf, ");
		sql.append("nvl(case when instr(to_char(b.zyf * "+zyfbl+" / 100),'.',1,1)=1 then '0'||to_char(b.zyf * "+zyfbl+" / 100) else to_char(b.zyf * "+zyfbl+" / 100) end, '0') zyzhf, ");
		sql.append("nvl(case when instr(to_char(b.tyf * "+tyfbl+" / 100),'.',1,1)=1 then '0'||to_char(b.tyf * "+tyfbl+" / 100) else to_char(b.tyf * "+tyfbl+" / 100) end, '0') tyzhf, ");
		sql.append("nvl(case when instr(to_char(b.nlf * "+nlfbl+" / 100),'.',1,1)=1 then '0'||to_char(b.nlf * "+nlfbl+" / 100) else to_char(b.nlf * "+nlfbl+" / 100) end, '0') nlzhf, ");
		sql.append("nvl(case when instr(to_char(b.zhf),'.',1,1)=1 then '0'||to_char(b.zhf) else to_char(b.zhf) end, '0') zhf ");
		sql.append("from view_xsjbxx a left join (select * from (select a.xn,a.xq,a.pjxh, a.dyf, a.tyf, a.nlf, a.zhf, b.zyf from zjcm_zhf a ");
		sql.append("left join view_zjcm_zyf b on a.xn = b.xn and a.xq = b.xq and a.pjxh = b.xh) where xn = '" + xn + "'  and xq = '" + xq + "') b on a.xh = b.pjxh ");
		sql.append(" ) " + query + ")");
		
		return sql.toString();
	}
	
	/**
	 * �����ۺ����ʷ�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean jsZhf(ZjcmPjpyModel model) throws Exception {
		
		boolean flag = false;
		
		String xn = model.getXn();
		String xq = model.getXq();
		
		List<HashMap<String,String>> list = dao.jsZhf(model);
		
		if (list != null && list.size() > 0) {
			
			String[] sql = new String[list.size()];
			
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				String xh = map.get("xh");
				String zhf = map.get("zhf");
				sql[i] = "update zjcm_zhf set zhf = '" + zhf
						+ "' where xn||xq||pjxh = '" + xn + xq + xh + "'";
			}
			
			flag = dao.saveArrDate(sql);
		}
		
		return flag;
	}
	
	/**
	 * ����ѧ�ּ���
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String jsXfJd(ZjcmPjpyModel model) throws Exception {

		String message = "����ɹ�";

		String realTable = "zjcm_xfjdb";
		String pycc = model.getPycc();

		if ("ר��".equalsIgnoreCase(pycc)) {

			List<HashMap<String, String>> sspjfList = dao.getSspjfList(model);
			if (sspjfList != null && sspjfList.size() > 0) {

				String xn = model.getXn();
				String xq = model.getXq();
				String[] xfjdxh = new String[sspjfList.size()];
				String[] xfjd = new String[sspjfList.size()];

				for (int i = 0; i < sspjfList.size(); i++) {
					xfjdxh[i] = sspjfList.get(i).get("xh");
					xfjd[i] = sspjfList.get(i).get("sspjf");
				}

				String[] arrzd = new String[] { "xfjdxh", "xfjd" };
				String[] onezd = new String[] { "xn", "xq" };
				String pk = "xn||xq||xfjdxh";
				String[] pkValue = new String[sspjfList.size()];

				for (int i = 0; i < sspjfList.size(); i++) {
					pkValue[i] = xn + xq + xfjdxh[i];
				}

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setArrzd(arrzd);
				saveForm.setOnezd(onezd);
				saveForm.setPk(pk);
				saveForm.setPkValue(pkValue);

				model = new ZjcmPjpyModel();
				model.setXn(xn);
				model.setXq(xq);
				model.setXfjdxh(xfjdxh);
				model.setXfjd(xfjd);

				savePjpy(saveForm, model);

			}else{
				message = "û�з������������ݣ�����ʧ��";
			}
		} else if ("����".equalsIgnoreCase(pycc)) {
			List<HashMap<String, String>> pjxfjdList = dao.getPjxfjdList(model);
			if (pjxfjdList != null && pjxfjdList.size() > 0) {

				String xn = model.getXn();
				String xq = model.getXq();
				String[] xfjdxh = new String[pjxfjdList.size()];
				String[] xfjd = new String[pjxfjdList.size()];

				for (int i = 0; i < pjxfjdList.size(); i++) {
					xfjdxh[i] = pjxfjdList.get(i).get("xh");
					xfjd[i] = pjxfjdList.get(i).get("pjxfjd");
				}

				String[] arrzd = new String[] { "xfjdxh", "xfjd" };
				String[] onezd = new String[] { "xn", "xq" };
				String pk = "xn||xq||xfjdxh";
				String[] pkValue = new String[pjxfjdList.size()];

				for (int i = 0; i < pjxfjdList.size(); i++) {
					pkValue[i] = xn + xq + xfjdxh[i];
				}

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setArrzd(arrzd);
				saveForm.setOnezd(onezd);
				saveForm.setPk(pk);
				saveForm.setPkValue(pkValue);

				model = new ZjcmPjpyModel();
				model.setXn(xn);
				model.setXq(xq);
				model.setXfjdxh(xfjdxh);
				model.setXfjd(xfjd);

				savePjpy(saveForm, model);
			}else{
				message = "û�з������������ݣ�����ʧ��";
			}
		}
		return message;
	}
	
	/**
	 * ����ۺ����������б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZhfPmList(ZjcmPjpyModel model)
			throws Exception {
		String table = getZhfTable(model);
		List<HashMap<String, String>> list = dao.getZhfPmList(table, model);
		return list;
	}
	
	/**
	 * ��ӡ�ۺ����ʲ�����
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void printZhszcpb(ZjcmPjpyModel model, OutputStream os)
			throws Exception {

		String xn = model.getXn();
		String xq = model.getXq();
		String xydm = model.getXydm();
		String bjdm = model.getBjdm();
		String lx = model.getLx();
		
		// �жϱ����Ƿ���ҪӢ�������ɼ�
		int num1 = ("zhbnoyj".equalsIgnoreCase(lx)) ? 0 : 2;
		
		//��ð༶��ý�ѧ��(һ������)����
		List<HashMap<String, String>> jxjRs = dao.getJxjRs(model);
		//��ð༶�����Լ�����
		HashMap<String, String> bjInfo = dao.getBjRs(bjdm);
		//���ѧ������
		String xqmc = getOneValue("xqdzb", "xqmc", "xqdm", xq);
		//���ѧԺ����
		String xymc = getOneValue("view_njxyzybj", "xymc", "xydm", xydm);
		//��ð༶����
		String bjmc = bjInfo.get("bjmc");
		//��ð༶����
		String bjrs = bjInfo.get("num");
		//һ�Ƚ�ѧ��������
		String ydrs = "0";
		// ���Ƚ�ѧ��������
		String edrs = "0";
		// ���Ƚ�ѧ��������
		String sdrs = "0";
		
		// �Ը�����ѧ��������ֵ(һ������)
		if (jxjRs != null && jxjRs.size() > 0) {
			for (int i = 0; i < jxjRs.size(); i++) {
				HashMap<String, String> map = jxjRs.get(i);
				if (map.get("jxjmc").contains("һ��")) {
					ydrs = map.get("num");
				} else if (map.get("jxjmc").contains("����")) {
					edrs = map.get("num");
				} else if (map.get("jxjmc").contains("����")) {
					sdrs = map.get("num");
				}
			}
		}
		
		// ���ñ���
		StringBuffer title =new StringBuffer();
		title.append(xn);
		title.append("ѧ��");
		title.append(xqmc);
		title.append("ѧ��");
		title.append(xymc);
		title.append("�ۺ����ʲ�����");

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		WritableSheet ws = wwb.createSheet("�ۺ����ʲ�����", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		WritableCellFormat wcf3 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 18 + num1, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		// ����ͷ
		// --------------��һ��----------------
		ws.addCell(new Label(0, 2, "�༶��" + bjmc, wcf3));
		ws.mergeCells(0, 2, 10, 2);
		ws.addCell(new Label(11, 2, "�༶������" + bjrs + "��", wcf3));
		ws.mergeCells(11, 2, 18 + num1, 2);
		// --------------�ڶ���----------------
		ws.addCell(new Label(0, 3, "һ�Ƚ�ѧ��" + ydrs + "��", wcf3));
		ws.mergeCells(0, 3, 5, 3);
		ws.addCell(new Label(6, 3, "���Ƚ�ѧ��" + edrs + "��", wcf3));
		ws.mergeCells(6, 3, 10, 3);
		ws.addCell(new Label(11, 3, "���Ƚ�ѧ��" + sdrs + "��", wcf3));
		ws.mergeCells(11, 3, 18 + num1, 3);
		// --------------������----------------
		ws.addCell(new Label(11, 4, "������ǩ����", wcf3));
		ws.mergeCells(11, 4, 18 + num1, 4);
		// --------------������----------------
		ws.addCell(new Label(0, 5, "ѧ��", wcf2));
		ws.addCell(new Label(1, 5, "����", wcf2));
		ws.addCell(new Label(2, 5, "��������", wcf2));
		ws.addCell(new Label(3, 5, "��������", wcf2));
		ws.addCell(new Label(4, 5, "��������", wcf2));
		ws.addCell(new Label(5, 5, "��������", wcf2));
		ws.addCell(new Label(6, 5, "��������", wcf2));
		ws.addCell(new Label(7, 5, "��������", wcf2));
		ws.addCell(new Label(8, 5, "��������", wcf2));
		ws.addCell(new Label(9, 5, "�ܷ�", wcf2));
		ws.addCell(new Label(10, 5, "�ۺ�����", wcf2));
		ws.addCell(new Label(11, 5, "��ѧ�𼰵��(��Ϊ����ԭ������ѡ����ע��)", wcf2));
		ws.addCell(new Label(10, 5, "�ۺ�����", wcf2));
		ws.addCell(new Label(11, 5, "��ѧ�𼰵��(��Ϊ����ԭ������ѡ����ע��)", wcf2));
		ws.addCell(new Label(12, 5, "����ѧ�����Ÿ�", wcf2));
		ws.addCell(new Label(13, 5, "��У�⽱ѧ�������������ѧ�ڻ���ҽ�ѧ�������", wcf2));
		ws.addCell(new Label(14, 5, "ѧϰ�ɼ�����������", wcf2));
		if ("zhbyj".equalsIgnoreCase(lx)) {
			ws.addCell(new Label(15, 5, "Ӣ��������", wcf2));
			ws.addCell(new Label(16, 5, "������������", wcf2));
		}
		ws.addCell(new Label(15 + num1, 5, "������Ƿѧ��(�Ѵ�����ע��)", wcf2));
		ws.addCell(new Label(16 + num1, 5, "����У��ͨ��", wcf2));
		ws.addCell(new Label(17 + num1, 5, "�������(������У�쿴����ע��)", wcf2));
		ws.addCell(new Label(18 + num1, 5, "���ν���", wcf2));
		
		//�ۺϷ����������Ϣ
		List<HashMap<String, String>> zhflist = getZhfPmList(model);
		
		//��ý�ѧ�������ƺŵ������Ϣ
		List<HashMap<String, String>> jxjrychList = dao.getJxjhdList(model);
		
		//���ѧ�������Ϣ
		PjpyXfjsService xfService = new PjpyXfjsService();
		
		List<HashMap<String, String>> kkList = xfService.getXskqqk("", xn, xq,"xy");
		List<HashMap<String, String>> tbList = xfService.getXskqqk("", xn, xq,"xx");
		
		// Υ�������б�
		List<HashMap<String, String>> cfList = dao.getCfmcList(model);
		
		// Ӣ��ɼ���������ɼ����б�
		List<HashMap<String, String>> djksList = null;
		if ("zhbyj".equalsIgnoreCase(lx)) {
			djksList = dao.getDjksList(model);
		}
		
		if (zhflist != null && zhflist.size() > 0) {
			for (int i = 0; i < zhflist.size(); i++) {
				HashMap<String, String> map = zhflist.get(i);

				// �ۺϷ���Ϣ���
				ws.addCell(new Label(0, 6 + i, map.get("xh"), wcf2));
				ws.addCell(new Label(1, 6 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(2, 6 + i, map.get("dyzhf"), wcf2));
				ws.addCell(new Label(3, 6 + i, map.get("dypm"), wcf2));
				ws.addCell(new Label(4, 6 + i, map.get("zyzhf"), wcf2));
				ws.addCell(new Label(5, 6 + i, map.get("zypm"), wcf2));
				ws.addCell(new Label(6, 6 + i, map.get("tyzhf"), wcf2));
				ws.addCell(new Label(7, 6 + i, map.get("nlzhf"), wcf2));
				ws.addCell(new Label(8, 6 + i, map.get("nlpm"), wcf2));
				ws.addCell(new Label(9, 6 + i, map.get("zhf"), wcf2));
				ws.addCell(new Label(10, 6 + i, map.get("zhpm"), wcf2));
				
				// �����������Ϣ
				if (jxjrychList != null && jxjrychList.size() > 0) {
					for (int j = 0; j < jxjrychList.size(); j++) {
						HashMap<String, String> jxjMap = jxjrychList.get(j);
						if (map.get("xh").equalsIgnoreCase(jxjMap.get("xh"))) {
							ws.addCell(new Label(11, 6 + i, jxjMap.get("xnjxj"), wcf2));
							ws.addCell(new Label(12, 6 + i, jxjMap.get("rychmc"), wcf2));
							ws.addCell(new Label(13, 6 + i, jxjMap.get("xwjxj"), wcf2));
							ws.addCell(new Label(14, 6 + i, jxjMap.get("bjgs"), wcf2));
						}
					}
				}
				
				// �ȼ��������
				if ("zhbyj".equalsIgnoreCase(lx)) {
					if (djksList != null && djksList.size() > 0) {
						for (int j = 0; j < djksList.size(); j++) {
							HashMap<String, String> cjMap = djksList.get(j);
							if (map.get("xh").equalsIgnoreCase(cjMap.get("xh"))) {

								// Ӣ��ȼ�
								if ("yy".equalsIgnoreCase(cjMap.get("lx"))) {
									ws.addCell(new Label(15, 6 + i, cjMap
											.get("ksqk"), wcf2));
								} else {
									ws.addCell(new Label(15, 6 + i, "", wcf2));
								}

								// ������ȼ�
								if ("jxj".equalsIgnoreCase(cjMap.get("lx"))) {
									ws.addCell(new Label(16, 6 + i, cjMap
											.get("ksqk"), wcf2));
								} else {
									ws.addCell(new Label(16, 6 + i, "", wcf2));
								}
							} else {
								ws.addCell(new Label(15, 6 + i, "", wcf2));
								ws.addCell(new Label(16, 6 + i, "", wcf2));
							}
						}
					} else {
						ws.addCell(new Label(15, 6 + i, "", wcf2));
						ws.addCell(new Label(16, 6 + i, "", wcf2));
					}
				}
				
				// TODO
				// ��Ƿѧ��
				ws.addCell(new Label(15+ num1, 6 + i, "", wcf2));
				
				// ѧ�������Ϣ(У��ͨ��)
				if (tbList != null && tbList.size() > 0) {
					for (int j = 0; j < tbList.size(); j++) {
						HashMap<String, String> tbMap = tbList.get(j);
						if (map.get("xh").equalsIgnoreCase(tbMap.get("xh"))) {
							ws.addCell(new Label(16 + num1, 6 + i, "��", wcf2));
							break;
						} else {
							ws.addCell(new Label(16 + num1, 6 + i, "", wcf2));
						}
					}
				} else {
					ws.addCell(new Label(16 + num1, 6 + i, "", wcf2));
				}

				// Υ������
				if (cfList != null && cfList.size() > 0) {
					for (int j = 0; j < cfList.size(); j++) {
						HashMap<String, String> cfMap = cfList.get(j);
						if (map.get("xh").equalsIgnoreCase(cfMap.get("xh"))) {
							ws.addCell(new Label(17 + num1, 6 + i, cfMap
									.get("cflbmc"), wcf2));
							break;
						} else {
							ws.addCell(new Label(17 + num1, 6 + i, "", wcf2));
						}
					}
				} else {
					ws.addCell(new Label(17 + num1, 6 + i, "", wcf2));
				}

				// ѧ�������Ϣ(���ν���)
				if (kkList != null && kkList.size() > 0) {
					for (int j = 0; j < kkList.size(); j++) {
						HashMap<String, String> kkMap = kkList.get(j);
						if (map.get("xh").equalsIgnoreCase(kkMap.get("xh"))) {
							ws.addCell(new Label(18 + num1, 6 + i, kkMap
									.get("kkjs"), wcf2));
							break;
						} else {
							ws.addCell(new Label(18 + num1, 6 + i, "", wcf2));
						}
					}
				} else {
					ws.addCell(new Label(18 + num1, 6 + i, "", wcf2));
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��ӡ�ۺ����ʱ�����
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void printZhszblb(ZjcmPjpyModel model, OutputStream os)
			throws Exception {
	
		String xydm = model.getXydm();
		String xn = model.getXn();
		String xq = model.getXq();
		
		//���ѧ������
		String xqmc = getOneValue("xqdzb", "xqmc", "xqdm", xq);
		
		//���ѧԺ����
		String xymc = getOneValue("view_njxyzybj", "xymc", "xydm", xydm);
		
		// ���ñ���
		StringBuffer title =new StringBuffer();
		title.append("�㽭��ýѧԺ");
		title.append(xn);
		title.append("ѧ��");
		title.append(xqmc);
		title.append("ѧ��");
		title.append("���ཱѧ���ۺϲ�������");

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		WritableSheet ws = wwb.createSheet(xymc + "��ѧ���ۺϲ�������", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 15, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		// ��ý�ѧ�������ƺţ�����
		String firstCjBl = "0";// һ�Ƚ��ɼ�����
		String firstZcBl = "0";// һ�Ƚ��۲����
		String secondCjBl = "0";// ���Ƚ��ɼ�����
		String secondZcBl = "0";// ���Ƚ��۲����
		String thirdCjBl = "0";// ���Ƚ��ɼ�����
		String thirdZcBl = "0";// ���Ƚ��۲����
		String xxyxCjBl = "0";// ѧϰ���㽱�ɼ�����
		String xxjbCjBl = "0";// ѧϰ�������ɼ�����
		String yxgbDyBl = "0";// ����ɲ���������
		String yxgbZcBl = "0";// ����ɲ��۲����
		String shxsDyBl = "0";// ����ѧ����������
		String shxsCjBl = "0";// ����ѧ���ɼ�����
		
		List<HashMap<String, String>> tjList = dao.getJxjRychTjList(model);

		if (tjList != null && tjList.size() > 0) {
			for (int i = 0; i < tjList.size(); i++) {

				HashMap<String, String> map = tjList.get(i);

				// �����ֶ�
				String tjzd = map.get("tjzd");
				// ����ֵ
				String tjz = map.get("tjz");
				// ��ѧ�������ƺţ�����
				String jxjmc = map.get("jxjmc");

				// -------һ�Ƚ�ѧ������-------
				if (jxjmc.contains("һ��")) {
					// �����������ɼ�����������
					if ("zypm".equalsIgnoreCase(tjzd)) {
						firstCjBl = tjz;
					}
					// �ۺ���������
					else if ("zhpm".equalsIgnoreCase(tjzd)) {
						firstZcBl = tjz;
					}
				}

				// -------���Ƚ�ѧ������-------
				if (jxjmc.contains("����")) {
					// �����������ɼ�����������
					if ("zypm".equalsIgnoreCase(tjzd)) {
						secondCjBl = tjz;
					}
					// �ۺ���������
					else if ("zhpm".equalsIgnoreCase(tjzd)) {
						secondZcBl = tjz;
					}
				}

				// -------���Ƚ�ѧ������-------
				if (jxjmc.contains("����")) {
					// �����������ɼ�����������
					if ("zypm".equalsIgnoreCase(tjzd)) {
						thirdCjBl = tjz;
					}
					// �ۺ���������
					else if ("zhpm".equalsIgnoreCase(tjzd)) {
						thirdZcBl = tjz;
					}
				}

				// -------ѧϰ���㽱����-------
				if (jxjmc.contains("ѧϰ") && jxjmc.contains("����")) {
					// �����������ɼ�����������
					if ("zypm".equalsIgnoreCase(tjzd)) {
						xxyxCjBl = tjz;
					}
				}

				// -------ѧϰ����������-------
				if (jxjmc.contains("ѧϰ") && jxjmc.contains("����")) {
					// ѧϰ��������
					if ("xxjb".equalsIgnoreCase(tjzd)) {
						xxjbCjBl = tjz;
					}
				}

				// -------����ɲ�����-------
				if (jxjmc.contains("����") && jxjmc.contains("�ɲ�")) {
					// ������������
					if ("dypm".equalsIgnoreCase(tjzd)) {
						yxgbDyBl = tjz;
					}
					// �ۺ���������
					else if ("zhpm".equalsIgnoreCase(tjzd)) {
						yxgbZcBl = tjz;
					}
				}

				// -------����ѧ������-------
				if (jxjmc.contains("����ѧ��")) {
					// ������������
					if ("dypm".equalsIgnoreCase(tjzd)) {
						shxsDyBl = tjz;
					}
					// �ۺ���������
					else if ("zypm".equalsIgnoreCase(tjzd)) {
						shxsCjBl = tjz;
					}
				}
			}
		}
		
		// ����ͷ
		ws.addCell(new Label(0, 2, "ѧԺ", wcf2));
		ws.mergeCells(0, 2, 0, 3);
		ws.addCell(new Label(1, 2, "�༶", wcf2));
		ws.mergeCells(1, 2, 1, 3);
		ws.addCell(new Label(2, 2, "����", wcf2));
		ws.mergeCells(2, 2, 2, 3);
		ws.addCell(new Label(3, 2, "����50%", wcf2));
		ws.mergeCells(3, 2, 3, 3);
		ws.addCell(new Label(4, 2, "һ�Ƚ�", wcf2));
		ws.mergeCells(4, 2, 5, 2);
		ws.addCell(new Label(6, 2, "���Ƚ�", wcf2));
		ws.mergeCells(6, 2, 7, 2);
		ws.addCell(new Label(8, 2, "���Ƚ�", wcf2));
		ws.mergeCells(8, 2, 9, 2);
		ws.addCell(new Label(10, 2, "���", wcf2));
		ws.mergeCells(10, 2, 11, 2);
		ws.addCell(new Label(12, 2, "����ѧ���ɲ�", wcf2));
		ws.mergeCells(12, 2, 13, 2);
		ws.addCell(new Label(14, 2, "����ѧ��", wcf2));
		ws.mergeCells(14, 2, 15, 2);

		ws.addCell(new Label(4, 3, "�ɼ�����" + firstCjBl + "%", wcf2));
		ws.addCell(new Label(5, 3, "�ۺϲ���" + firstZcBl + "%", wcf2));
		ws.addCell(new Label(6, 3, "�ɼ�����" + secondCjBl + "%", wcf2));
		ws.addCell(new Label(7, 3, "�ۺϲ���" + secondZcBl + "%", wcf2));
		ws.addCell(new Label(8, 3, "�ɼ�����" + thirdCjBl + "%", wcf2));
		ws.addCell(new Label(9, 3, "�ۺϲ���" + thirdZcBl + "%", wcf2));
		ws.addCell(new Label(10, 3, "ѧϰ���㽱ǰ" + xxyxCjBl + "%", wcf2));
		ws.addCell(new Label(11, 3, "ѧϰ��������ǰ" + xxjbCjBl + "%", wcf2));
		ws.addCell(new Label(12, 3, "����ǰ" + yxgbDyBl + "%", wcf2));
		ws.addCell(new Label(13, 3, "�ۺϲ���" + yxgbZcBl + "%", wcf2));
		ws.addCell(new Label(14, 3, "����ǰ" + shxsDyBl + "%", wcf2));
		ws.addCell(new Label(15, 3, "�ɼ�����" + shxsCjBl + "%", wcf2));

		// ���ѧԺ�༶�����б�
		List<HashMap<String, String>> xybjrslist = dao.getXyBjrsList(model);
		
		if (xybjrslist != null && xybjrslist.size() > 0) {
			
			// �༶����
			int bjZrs = 0;
			// ��������
			int dyZrs = 0;
			// һ�ȳɼ�����
			int firstCjZRs = 0;
			// һ���۲�����
			int firstZcZRs = 0;
			// ���ȳɼ�����
			int secondCjZRs = 0;
			// �����۲�����
			int secondZcZRs = 0;
			// ���ȳɼ�����
			int thirdCjZRs = 0;
			// �����۲�����
			int thirdZcZRs = 0;
			// ѧϰ����ɼ�����
			int xxyxCjZRs = 0;
			// ѧϰ��������
			int xxjbZPm = 0;
			// ����ɲ���������
			int yxgbDyZRs = 0;
			// ����ɲ��۲�����
			int yxgbZcZRs = 0;
			// ����ѧ����������
			int shxsDyZRs = 0;
			// ����ѧ���ɼ�����
			int shxsCjZRs = 0;
			
			for (int i = 0; i < xybjrslist.size(); i++) {

				HashMap<String, String> map = xybjrslist.get(i);

				// �༶����
				String bjmc = map.get("bjmc");
				// �༶����
				String bjrs = map.get("num");
				// �������� ҳ��д��50% �޷����ݽ�ѧ���������������г�ȡ
				String dyrs = String.valueOf(Math
						.round(Float.parseFloat(bjrs) * 50 / 100));
				// һ�ȳɼ�����
				String firstCjRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(firstCjBl) / 100));
				// һ���۲�����
				String firstZcRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(firstZcBl) / 100));
				// ���ȳɼ�����
				String secondCjRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(secondCjBl) / 100));
				// �����۲�����
				String secondZcRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(secondZcBl) / 100));
				// ���ȳɼ�����
				String thirdCjRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(thirdCjBl) / 100));
				// �����۲�����
				String thirdZcRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(thirdZcBl) / 100));
				// ѧϰ����ɼ�����
				String xxyxCjRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(xxyxCjBl) / 100));
				// ѧϰ��������
				String xxjbPm = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(xxjbCjBl) / 100));
				// ����ɲ���������
				String yxgbDyRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(yxgbDyBl) / 100));
				// ����ɲ��۲�����
				String yxgbZcRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(yxgbZcBl) / 100));
				// ����ѧ����������
				String shxsDyRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(shxsDyBl) / 100));
				// ����ѧ���ɼ�����
				String shxsCjRs = String.valueOf(Math.round(Float
						.parseFloat(bjrs)
						* Float.parseFloat(shxsCjBl) / 100));

				ws.addCell(new Label(0, 4 + i, xymc, wcf2));
				ws.addCell(new Label(1, 4 + i, bjmc, wcf2));
				ws.addCell(new Label(2, 4 + i, bjrs, wcf2));
				ws.addCell(new Label(3, 4 + i, dyrs, wcf2));
				ws.addCell(new Label(4, 4 + i, firstCjRs, wcf2));
				ws.addCell(new Label(5, 4 + i, firstZcRs, wcf2));
				ws.addCell(new Label(6, 4 + i, secondCjRs, wcf2));
				ws.addCell(new Label(7, 4 + i, secondZcRs, wcf2));
				ws.addCell(new Label(8, 4 + i, thirdCjRs, wcf2));
				ws.addCell(new Label(9, 4 + i, thirdZcRs, wcf2));
				ws.addCell(new Label(10, 4 + i, xxyxCjRs, wcf2));
				ws.addCell(new Label(11, 4 + i, xxjbPm, wcf2));
				ws.addCell(new Label(12, 4 + i, yxgbDyRs, wcf2));
				ws.addCell(new Label(13, 4 + i, yxgbZcRs, wcf2));
				ws.addCell(new Label(14, 4 + i, shxsDyRs, wcf2));
				ws.addCell(new Label(15, 4 + i, shxsCjRs, wcf2));
				
				// �ۼӣ������ܼ�
				bjZrs += Integer.valueOf(bjrs);
				dyZrs += Integer.valueOf(dyrs);
				firstCjZRs += Integer.valueOf(firstCjRs);
				firstZcZRs += Integer.valueOf(firstZcRs);
				secondCjZRs += Integer.valueOf(secondCjRs);
				secondZcZRs += Integer.valueOf(secondZcRs);
				thirdCjZRs += Integer.valueOf(thirdCjRs);
				thirdZcZRs += Integer.valueOf(thirdZcRs);
				xxyxCjZRs += Integer.valueOf(xxyxCjRs);
				xxjbZPm += Integer.valueOf(xxjbZPm);
				yxgbDyZRs += Integer.valueOf(yxgbDyRs);
				yxgbZcZRs += Integer.valueOf(yxgbZcRs);
				shxsDyZRs += Integer.valueOf(shxsDyRs);
				shxsCjZRs += Integer.valueOf(shxsCjRs);
			}
			
			// �ܼ�
			ws.addCell(new Label(0, xybjrslist.size()+4, xymc, wcf2));
			ws.addCell(new Label(1, xybjrslist.size()+4, "�ܼ�", wcf2));
			ws.addCell(new Label(2, xybjrslist.size()+4, String.valueOf(bjZrs), wcf2));
			ws.addCell(new Label(3, xybjrslist.size()+4, String.valueOf(dyZrs), wcf2));
			ws.addCell(new Label(4, xybjrslist.size()+4, String.valueOf(firstCjZRs), wcf2));
			ws.addCell(new Label(5, xybjrslist.size()+4, String.valueOf(firstZcZRs), wcf2));
			ws.addCell(new Label(6, xybjrslist.size()+4, String.valueOf(secondCjZRs), wcf2));
			ws.addCell(new Label(7, xybjrslist.size()+4, String.valueOf(secondZcZRs), wcf2));
			ws.addCell(new Label(8, xybjrslist.size()+4, String.valueOf(thirdCjZRs), wcf2));
			ws.addCell(new Label(9, xybjrslist.size()+4, String.valueOf(thirdZcZRs), wcf2));
			ws.addCell(new Label(10, xybjrslist.size()+4, String.valueOf(xxyxCjZRs), wcf2));
			ws.addCell(new Label(11, xybjrslist.size()+4, String.valueOf(xxjbZPm), wcf2));
			ws.addCell(new Label(12, xybjrslist.size()+4, String.valueOf(yxgbDyZRs), wcf2));
			ws.addCell(new Label(13, xybjrslist.size()+4, String.valueOf(yxgbZcZRs), wcf2));
			ws.addCell(new Label(14, xybjrslist.size()+4, String.valueOf(shxsDyZRs), wcf2));
			ws.addCell(new Label(15, xybjrslist.size()+4, String.valueOf(shxsCjZRs), wcf2));
			
			ws.mergeCells(0, 4, 0, xybjrslist.size() + 4);
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��ӡ��ѧ����ͳ�Ʊ�
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void printJxjjetjb(ZjcmPjpyModel model, OutputStream os)
			throws Exception {
	
		DAO tyDao = DAO.getInstance();
		
		String xn = model.getXn();
		String xq = model.getXq();
		String jxjdm = model.getJxjdm();
		String yhdm = model.getYhdm();
		String nowTime = tyDao.getNowTime("1");//���ϵͳʱ��
		
		// ���ѧ������
		String xqmc = getOneValue("xqdzb", "xqmc", "xqdm", xq);

		// ���ѧ������
		String jxjmc = getOneValue("jxjdmb", "jxjmc", "jxjdm", jxjdm);

		// �����������
		String yhmc = getOneValue("dmk_yh", "yhmc", "yhdm", yhdm);
		
		// ���ñ���
		StringBuffer title =new StringBuffer();
		title.append(xn);
		title.append("ѧ��");
		title.append(xqmc);
		title.append("ѧ��");
		title.append(jxjmc);
		title.append("��ѧ�����Ž�����ϸ��");

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		WritableSheet ws = wwb.createSheet(jxjmc+"�����ܱ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 7, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		// ����ͷ
		ws.addCell(new Label(0, 2, "���", wcf2));
		ws.addCell(new Label(1, 2, "ѧ������", wcf2));
		ws.addCell(new Label(2, 2, "Ժϵ", wcf2));
		ws.addCell(new Label(3, 2, "רҵ", wcf2));
		ws.addCell(new Label(4, 2, "����", wcf2));
		ws.addCell(new Label(5, 2, "���", wcf2));
		ws.addCell(new Label(6, 2, yhmc + "����", wcf2));
		ws.addCell(new Label(7, 2, "�ϼ�", wcf2));
		
		// ��ѧ����ͳ���б�
		List<HashMap<String, String>> list = dao.getJxjjeList(model);
		
		int zjje = 0;//�ܼƽ��
		
		if (list != null && list.size() > 0) {
			
			for (int i = 0; i < list.size(); i++) {
				
				HashMap<String, String> map = list.get(i);
				
				ws.addCell(new Label(0, 3 + i, String.valueOf(i + 1), wcf2));//���
				ws.addCell(new Label(1, 3 + i, map.get("xm"), wcf2));//����
				ws.addCell(new Label(2, 3 + i, map.get("xymc"), wcf2));//ѧԺ
				ws.addCell(new Label(3, 3 + i, map.get("zymc"), wcf2));//רҵ
				ws.addCell(new Label(4, 3 + i, map.get("jxjmc"), wcf2));//��ѧ������
				ws.addCell(new Label(5, 3 + i, map.get("je"), wcf2));//���
				ws.addCell(new Label(6, 3 + i, map.get("yhkh"), wcf2));//���п���
				
				// �ۼӽ������ܼƽ��
				if (!Base.isNull(map.get("je"))) {
					zjje += Integer.parseInt(map.get("je"));
				} 
			}
			
			// ��β��Ϣ
			ws.addCell(new Label(0, list.size() + 3, "�ϼƣ�"
					+ MoneyFormat.format(String.valueOf(zjje)), wcf2));// �ܼƽ��
			ws.mergeCells(0, list.size() + 3, 7, list.size() + 3);

			WritableCellFormat wcf3 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			wcf3 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.LEFT, VerticalAlignment.CENTRE, true, null);
			
			StringBuffer msg = new StringBuffer();
			msg.append("�Ʊ��ˣ�" );
			msg.append("                         " );
			msg.append("֤���ˣ�" );
			msg.append("                          " );
			msg.append("�Ʊ�ʱ�䣺" + nowTime);
			
			ws.addCell(new Label(0, list.size() + 5, msg.toString(), wcf3));// �ܼƽ��,֤����,�Ʊ�ʱ��		
			ws.mergeCells(0, list.size() + 5, 7, list.size() + 5);
			
			ws.addCell(new Label(0, list.size() + 7, "�����쵼ǩ�֣�", wcf3));// �����쵼ǩ��		
			ws.mergeCells(0, list.size() + 7, 7, list.size() + 7);
			
			ws.addCell(new Label(0, list.size() + 9, "У�쵼ǩ�֣�", wcf3));// У�쵼ǩ��
			ws.mergeCells(0, list.size() + 9, 7, list.size() + 9);
			
			//�ϲ���Ԫ��
			int xyje = 0;//�ܼƽ��
			int row = 1;//����
			boolean c = false;
			
			for (int i = 0; i <= list.size(); i++) {
				
				String c3 = "";
				String c4 = "";
				
				WritableCell c1 = ws.getWritableCell(2, 3 + i);//ѧԺ
				WritableCell f1 = ws.getWritableCell(5, 3 + i);//���
				
				c3 = c1.getContents();
				
				String je = f1.getContents();
				
				//�ۼӽ�����ѧԺ�ܼƽ��
				if (!Base.isNull(je)) {
					xyje += Integer.parseInt(je);
				} 
				
				if (i > 0) {
					WritableCell c2 = ws.getWritableCell(2, 3 + i - 1);
					c4 = c2.getContents();
				}
				
				if (c3.equals(c4)) {
					row++;
					c = true;
				}
				
				if ((!c3.equals(c4)) && c) {
					ws.addCell(new Label(7, 3 + i - row, String.valueOf(xyje), wcf2));//ѧԺ���
					ws.mergeCells(7, 3 + i - row, 7, 3 + i - 1);
					// ��Ԫ��ϲ������ò���
					row = 1;
					zjje = 0;
					c = false;
				}
			}
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��ӡ��ѧ�������
	 * @param ZjcmPjpyModel model
	 * @param OutputStream os
	 * @author lr
	 * @throws Exception
	 */
	public void printJxjhjmd(ZjcmPjpyModel model, OutputStream os)
			throws Exception {
		//��ѧ����� ����
		String jxjlbmc = dao.getOneValue("jxjlbdmb", "jxjlbmc", "jxjlbdm", model.getJxjlbdm());
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(jxjlbmc + "������", 0);
		
		//ѧԺ�б�
		List<String[]> xymcList = dao.queryXyList(model);
		//����ѧ�������
		List<String[]> jxjdmList = dao.queryJxjmcjrs(model);
		//������
		List<String[]> xmList = dao.queryJxjhjxmxx(model);
		
		//��ѧ��������ʽ
		WritableCellFormat jxjFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.ARIAL,13);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		jxjFormat.setFont(jxjFont);
		jxjFormat.setAlignment(Alignment.LEFT);
		
		
		//�����һ�еı�ͷ
		ws.addCell(new Label(0, 0,  
						StringUtils.joinStr(StandardOperation.getXxmc(), 
										model.getXn(), 
										"ѧ���",
										model.getXq(), 
										"ѧ��", 
										jxjlbmc, 
										"���������" 
										),
						jxjFormat
					)
		);
		ws.mergeCells(0, 0, 8, 0);
		
		//�����һ����ѧ�����ƺͻ�����
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			for (int i=0;i<jxjdmList.size();i++) {
				//����ѧ������д�뵽EXCEL��
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[1];
				String jxjdm = jxjdmArr[0];
				Label resultCell = new Label(0,properties.row, jxjmc + " (��" +jxjdmArr[2] +"��)");
				resultCell.setCellFormat(jxjFormat);
				ws.addCell(resultCell);
				ws.mergeCells(0, properties.row, 4, properties.row);
				
				//����ڶ�����ѧԺ����д��EXCEL��
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjdm.equalsIgnoreCase(xymcArr[2])) {						
						String xy = xymcArr != null && xymcArr.length >= 4 ? xymcArr[1] : "";
						String xydm = xymcArr != null && xymcArr.length >= 4 ? xymcArr[0] : "";
						List<String> xmByXyList = new ArrayList<String>();//���ÿ����ѧ�������ÿ��ѧԺ�Ļ�����
						for (String[] xmArr : xmList) {
							if (xmArr != null && xmArr.length >= 3
									&& xydm.equalsIgnoreCase(xmArr[1])
									&& jxjdm.equalsIgnoreCase(xmArr[0])) {
								xmByXyList.add(xmArr[2]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//���ѧԺ����
						++properties.row;
						Label xymcCell = new Label(1,properties.row,xy + " (" +xymcArr[3] + "��)");
						xymcCell.setCellFormat(jxjFormat);
						ws.addCell(xymcCell);
						ws.mergeCells(1,properties.row, 7, properties.row);
						//�����������ѧԺ����Ļ��������
						writeJxjXm2Excel(ws, properties, xmByXyList,0);//��������д�뵽EXCEL��
					}
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//��ͻ������
	}
	
	private void writeJxjXm2Excel(WritableSheet ws,
			JxjExportProperties properties, List<String> xmList,int rowCellCount)
			throws Exception {
		properties.rowCellCount = 1;
		properties.row++;//��ʼд������
		properties.x_axis = rowCellCount;//��ʼ�½�ѧ�������
		if (xmList != null) {
			for(int index = 1;index < xmList.size() + 1;index++){
				String cellContent = xmList.get(index-1);
				if(properties.rowCellCount == 8){
					properties.row++;//��10���ͻ���
					properties.x_axis=0;
					properties.rowCellCount = 1;//����ÿ�м���
				}
				// �ж���������,Ȼ����ݳ��ȿ��ƺϲ���Ԫ�񣬼��������ȵ�����½��л���
				if(cellContent.length() > 3){
					int xmLength = cellContent.length()/3 + (cellContent.length()%3==0 ? 0 : 1);
					int pre_x_axis = properties.x_axis;//�ϲ�ǰ��x��ֵ
					if(pre_x_axis + xmLength > 10){
						properties.row++;//��������������
						properties.x_axis=rowCellCount;
						properties.rowCellCount = 1;//����ÿ�м���
						pre_x_axis = properties.x_axis;
					}
					Label cell = new Label(++properties.x_axis,properties.row,cellContent);
					ws.addCell(cell);
					ws.setColumnView(properties.x_axis, 7);
					pre_x_axis++;
					ws.mergeCells(pre_x_axis, properties.row, pre_x_axis+xmLength-1, properties.row);
					properties.x_axis = pre_x_axis+xmLength-1;
				} else {
					Label cell = new Label(++properties.x_axis,properties.row,cellContent);
					ws.addCell(cell);
					ws.setColumnView(properties.x_axis, 7);
					properties.rowCellCount++;
				}
			}
		}
		properties.row+=1;//�½�ѧ����
	}
	
	private class JxjExportProperties{
		 int x_axis = 0;//X����
		 int row = 1;//�б�
		 int rowCellCount = 1;//ÿ�е�cell��������
		 int[] maxLength = {7,7,7,7,7,7,7};//Ĭ�ϵ��п�
		public int getX_axis() {
			return x_axis;
		}
		public void setX_axis(int x_axis) {
			this.x_axis = x_axis;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getRowCellCount() {
			return rowCellCount;
		}
		public void setRowCellCount(int rowCellCount) {
			this.rowCellCount = rowCellCount;
		}
		public int[] getMaxLength() {
			return maxLength;
		}
		public void setMaxLength(int[] maxLength) {
			this.maxLength = maxLength;
		}
	}
	
	/**
	 * ��ӡ�����ƺŻ�����
	 * @param ZjcmPjpyModel model
	 * @param OutputStream os
	 * @author lr
	 * @throws Exception
	 */
	public void printRychhjmd(ZjcmPjpyModel model, OutputStream os)
			throws Exception {
		//�����ƺ�����
		String rychmc = dao.getOneValue("rychdmb", "rychdm", "rychmc", model.getRychdm());
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(rychmc + "���������", 0);
		
		//ѧԺ�б�
		List<String[]> xymcList = dao.queryRychXyList(model);
		//������
		List<String[]> xmList = dao.queryRychhjxmxx(model);
		
		//�����ƺ�������ʽ
		WritableCellFormat jxjFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.ARIAL,13);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		jxjFormat.setFont(jxjFont);
		jxjFormat.setAlignment(Alignment.LEFT);
		
		
		//�����һ�еı�ͷ
		ws.addCell(new Label(0, 0,  
						StringUtils.joinStr(StandardOperation.getXxmc(), 
										model.getXn(), 
										"ѧ���",
										dao.getXqMc(model.getXq())										, 
										"ѧ��", 
										rychmc, 
										"���������" 
										),
						jxjFormat
					)
		);
		ws.mergeCells(0, 0, 8, 0);
		
		//�����һ����ѧ�����ƺͻ�����
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			String rychdm = model.getRychdm();
			//����ڶ�����ѧԺ����д��EXCEL��
			for (int index = 0; index < xymcList.size(); index++) {
				String[] xymcArr = xymcList.get(index);
				if (rychdm.equalsIgnoreCase(xymcArr[2])) {						
					String xy = xymcArr != null && xymcArr.length >= 4 ? xymcArr[1] : "";
					String xydm = xymcArr != null && xymcArr.length >= 4 ? xymcArr[0] : "";
					List<String> xmByXyList = new ArrayList<String>();//���ÿ����ѧ�������ÿ��ѧԺ�Ļ�����
					for (String[] xmArr : xmList) {
						if (xmArr != null && xmArr.length >= 3
								&& xydm.equalsIgnoreCase(xmArr[1])
								&& rychdm.equalsIgnoreCase(xmArr[0])) {
							xmByXyList.add(xmArr[2]);
						}
					}
					if (xmByXyList == null || xmByXyList.size() <= 0) {
						continue;
					}
					//���ѧԺ����
					
					Label xymcCell = new Label(0,properties.row,xy + " (" +xymcArr[3] + "��)");
					xymcCell.setCellFormat(jxjFormat);
					ws.addCell(xymcCell);
					ws.mergeCells(0,properties.row, 8, properties.row);
					
					//�����������ѧԺ����Ļ��������
					writeJxjXm2Excel(ws, properties, xmByXyList,-1);//��������д�뵽EXCEL��
					properties.row++;
				}
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//��ͻ������
	}	
	
	/**
	 * �������������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveWlk(ZjcmPjpyModel model) throws Exception {
		boolean flag = false;
		flag = dao.saveWlk(model);
		return flag;
	}
	
	/**
	 * ��ѧ������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] Jxjtj(ZjcmPjpyModel model) throws Exception {
		
		// ��������뽱ѧ�������õ�����

		String tableName = "view_zjcm_pjpy_tjsz";
		String query = " where xn = ? and xq = ? and lx = ? and jxjdm = ?";
		String xn = model.getXn();
		String xq = model.getXq();
		String lx = model.getLx();
		String jxjdm = "jxj".equalsIgnoreCase(lx) ? model.getJxjdm() : model.getRychdm();
		String[] inPutList = new String[] { xn, xq, lx, jxjdm };
		String[] colList = new String[] { "zdsm", "tjzd", "tjlx", "tjz", "bjlx" };

		List<HashMap<String, String>> tjList = CommonQueryDAO
				.commonQueryforList(tableName, query, inPutList, colList, "");

		String errXh[] = null;
		
		// ��ð༶����
		String bjdm = model.getBjdm();
		String bjrs = dao.getBjRs(bjdm).get("num");
		
		// ����걨�༶ȫ��ѧ��
		String[] pjxh = getZdXh("bj", bjdm);
		
		if (tjList != null && tjList.size() > 0) {

			for (int i = 0; i < tjList.size(); i++) {
				HashMap<String, String> tj = tjList.get(i);
				String tjzd = tj.get("tjzd"); // �����ֶ�
				String tjlx = tj.get("tjlx"); // ��������
				String tjz = tj.get("tjz"); // ����ֵ
				// TODO xsdjksb������jxjtjzdb����Ҫͳһ
				String zdsm = tj.get("zdsm");// �ֶ�˵��
				String bjlx = tj.get("bjlx"); // �༶����

				// -------------�жϵ��������Ƿ���������--------------
				if ("dypm".equalsIgnoreCase(tjzd)) {
					errXh = isPmTj(model, pjxh, "dypm", tjlx, tjz, bjrs);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------�ж����������Ƿ���������--------------
				else if ("zypm".equalsIgnoreCase(tjzd)) {
					errXh = isPmTj(model, pjxh, "zypm", tjlx, tjz, bjrs);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------�ж����������Ƿ���������--------------
				else if ("typm".equalsIgnoreCase(tjzd)) {
					errXh = isPmTj(model, pjxh, "typm", tjlx, tjz, bjrs);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------�ж����������Ƿ���������--------------
				else if ("nlpm".equalsIgnoreCase(tjzd)) {
					errXh = isPmTj(model, pjxh, "nlpm", tjlx, tjz, bjrs);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------�ж��ۺ������Ƿ���������--------------
				else if ("zhpm".equalsIgnoreCase(tjzd)) {
					errXh = isPmTj(model, pjxh, "zhpm", tjlx, tjz, bjrs);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------�жϲ������Ŀ�Ƿ���������--------------
				else if ("bjgkm".equalsIgnoreCase(tjzd)) {
					errXh = isBjg(model);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------�ж�Υ�ʹ����Ƿ���������--------------
				else if ("jg".equalsIgnoreCase(tjzd)
						|| "yzjg".equalsIgnoreCase(tjzd)
						|| "jig".equalsIgnoreCase(tjzd)
						|| "lxck".equalsIgnoreCase(tjzd)) {
					
					WjcfZjcmDAO wjcfDao = new WjcfZjcmDAO();
					
					// ��������
					String cflbmc = "";

					if ("jg".equalsIgnoreCase(tjzd)) {
						cflbmc = "����";
					} else if ("yzjg".equalsIgnoreCase(tjzd)) {
						cflbmc = "���ؾ���";
					} else if ("jig".equalsIgnoreCase(tjzd)) {
						cflbmc = "�ǹ�";
					} else if ("lxck".equalsIgnoreCase(tjzd)) {
						cflbmc = "��У�鿴";
					}
					
					// pjxh = checkCfsj(pjxh, errXh);
					// errXh = isWjcf(model);
					errXh = wjcfDao.queryWjcfxxByArrXh(pjxh, xn, xq, cflbmc);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------�жϵȼ����Գɼ��Ƿ���������--------------
				else if ("yy4j".equalsIgnoreCase(tjzd)// Ӣ���ļ�
						|| "yy3j".equalsIgnoreCase(tjzd)// Ӣ������
						|| "zy4j".equalsIgnoreCase(tjzd)// רҵӢ���ļ�
						|| "jsj1j".equalsIgnoreCase(tjzd)// �����һ��
						|| "jsj2j".equalsIgnoreCase(tjzd)) { // ���������
					errXh = isDjks(model, pjxh, zdsm, tjlx, tjz, bjlx);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------�ж�ѧϰ�����Ƿ���������--------------
				else if ("xxjb".equalsIgnoreCase(tjzd)) {
					errXh = isXxjb(model, pjxh, tjlx, tjz, bjrs);
					pjxh = checkCfsj(pjxh, errXh);
				}
				// -------------�ж�ѧ�����(У��ͨ��������)�Ƿ���������--------------
				else if ("xjtb".equalsIgnoreCase(tjzd)) {
					errXh = isXjtb(model, pjxh);
					pjxh = checkCfsj(pjxh, errXh);
				} 
				// -------------�ж�ѧ�����(����)�Ƿ���������--------------
				else if ("kkjs".equalsIgnoreCase(tjzd)) {
					errXh = isKkjs(model, pjxh);
					pjxh = checkCfsj(pjxh, errXh);				
				}
			}
		}
		
		return pjxh;
	}
	
	/**
	 * �ж���������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] isPmTj(ZjcmPjpyModel model, String[] pjxh, String pmlx,
			String tjlx, String tjz, String bjrs) throws Exception {
		
		int n = 0;
		String errXh[] = new String[pjxh.length];
//		String msg = "";
//
//		if ("dypm".equalsIgnoreCase(pmlx)) {
//			msg = "��ѧ�����������������㱾��ѧ�����������,��ȷ�ϣ�����";
//		} else if ("zypm".equalsIgnoreCase(pmlx)) {
//			msg = "��ѧ�����������������㱾��ѧ�����������,��ȷ�ϣ�����";
//		} else if ("typm".equalsIgnoreCase(pmlx)) {
//			msg = "��ѧ�����������������㱾��ѧ�����������,��ȷ�ϣ�����";
//		} else if ("nlpm".equalsIgnoreCase(pmlx)) {
//			msg = "��ѧ�����������������㱾��ѧ�����������,��ȷ�ϣ�����";
//		} else if ("zhpm".equalsIgnoreCase(pmlx)) {
//			msg = "��ѧ���ۺϷ����������㱾��ѧ�����������,��ȷ�ϣ�����";
//		}	
		
		//��������б�
		List<HashMap<String, String>> pmList = getPmList(model, pmlx);

		if (pmList != null && pmList.size() > 0) {
			if (pjxh != null && pjxh.length > 0) {
				for (int i = 0; i < pmList.size(); i++) {
					HashMap<String, String> map = pmList.get(i);
					for (int j = 0; j < pjxh.length; j++) {
						if (map.get("xh").equalsIgnoreCase(pjxh[j])) {
							// ѧ���༶����
							float pm = Float.parseFloat(map.get("pm"));
							// ��ѧ����������
							float tjpm = Float.parseFloat(bjrs)
									* Float.parseFloat(tjz) / 100;
							tjpm = Math.round(tjpm);
							// �ж��������Ƿ��������
							if (">".equalsIgnoreCase(tjlx)) {
								if (pm >= tjpm) {
									errXh[n] = pjxh[j];
									n++;
								}
							} else if (">=".equalsIgnoreCase(tjlx)) {
								if (pm > tjpm) {
									errXh[n] = pjxh[j];
									n++;
								}
							} else if ("=".equalsIgnoreCase(tjlx)) {
								if (pm != tjpm) {
									errXh[n] = pjxh[j];
									n++;
								}
							} else if ("<".equalsIgnoreCase(tjlx)) {
								if (pm <= tjpm) {
									errXh[n] = pjxh[j];
									n++;
								}
							} else if ("<".equalsIgnoreCase(tjlx)) {
								if (pm < tjpm) {
									errXh[n] = pjxh[j];
									n++;
								}
							}
						}
					}
				}
			}
		}
		
		return errXh;
	}
	
	/**
	 * ����ۺϷ��������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPmList(ZjcmPjpyModel model,
			String pmlx) throws Exception {

		String table = getZhfTable(model);
		
		List<HashMap<String, String>> dypmList = new ArrayList<HashMap<String,String>>();//�����������б�
		List<HashMap<String, String>> zypmList = new ArrayList<HashMap<String,String>>();//�����������б�
		List<HashMap<String, String>> typmList = new ArrayList<HashMap<String,String>>();//�����������б�
		List<HashMap<String, String>> nlpmList = new ArrayList<HashMap<String,String>>();//�����������б�
		List<HashMap<String, String>> zhpmList = new ArrayList<HashMap<String,String>>();//�ۺϷ������б�
		
		List<HashMap<String, String>> list = dao.getZhfPmList(table, model);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();

				String pjxh = list.get(i).get("xh");
				String dypm = list.get(i).get("dypm");
				String zypm = list.get(i).get("zypm");
				String typm = list.get(i).get("typm");
				String nlpm = list.get(i).get("nlpm");
				String zhpm = list.get(i).get("zhpm");

				if ("dypm".equalsIgnoreCase(pmlx)) {
					map.put("xh", pjxh);
					map.put("pm", dypm);
					dypmList.add(map);
				} else if ("zypm".equalsIgnoreCase(pmlx)) {
					map.put("xh", pjxh);
					map.put("pm", zypm);
					zypmList.add(map);
				} else if ("typm".equalsIgnoreCase(pmlx)) {
					map.put("xh", pjxh);
					map.put("pm", typm);
					typmList.add(map);
				} else if ("nlpm".equalsIgnoreCase(pmlx)) {
					map.put("xh", pjxh);
					map.put("pm", nlpm);
					nlpmList.add(map);
				} else if ("zhpm".equalsIgnoreCase(pmlx)) {
					map.put("xh", pjxh);
					map.put("pm", zhpm);
					zhpmList.add(map);
				}			
			}
		}
		
		if ("dypm".equalsIgnoreCase(pmlx)) {
			return dypmList;
		} else if ("zypm".equalsIgnoreCase(pmlx)) {
			return zypmList;
		} else if ("typm".equalsIgnoreCase(pmlx)) {
			return typmList;
		} else if ("nlpm".equalsIgnoreCase(pmlx)) {
			return nlpmList;
		} else if ("zhpm".equalsIgnoreCase(pmlx)) {
			return zhpmList;
		}

		return null;
	}
	
	/**
	 * �ж��Ƿ���ڲ������Ŀ��ѧ��
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] isBjg(ZjcmPjpyModel model) throws Exception {

		String[] errXh = null;

		List<HashMap<String, String>> list = dao.getBjgList(model);

		if (list != null && list.size() > 0) {
			errXh = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				errXh[i] = list.get(i).get("xh");
			}
			// msg = "��ѧ�����ڲ������Ŀ�������㱾��ѧ�����������,��ȷ�ϣ�����";
		}

		return errXh;
	}
	
	/**
	 * �ж��Ƿ����Υ�ʹ���
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] isWjcf(ZjcmPjpyModel model) throws Exception {

		String[] errXh = null;

		List<HashMap<String, String>> list = dao.getWjcfList(model);

		if (list != null && list.size() > 0) {
			errXh = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				errXh[i] = list.get(i).get("xh");
			}
			// msg = "��ѧ������Υ�ʹ��֣������㱾��ѧ�����������,��ȷ�ϣ�����";
		}

		return errXh;
	}
	
	/**
	 * �жϵȼ������Ƿ�ͨ��
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] isDjks(ZjcmPjpyModel model, String[] pjxh, String zdsm,
			String tjlx, String tjz, String bjlx) throws Exception {
		// TODO ����ƻ�δ��
		int n = 0;
		String errXh[] = null;
		String bjdm = model.getBjdm();
		String wlk = getOneValue("zjcm_wlkb", "lx", "wlkbjdm", bjdm);

		if (wlk.equalsIgnoreCase(bjlx)) {
			List<HashMap<String, String>> list = dao.getDjksList(model, pjxh);

			if (list != null && list.size() > 0) {

				errXh = new String[list.size()];

				for (int i = 0; i < list.size(); i++) {

					HashMap<String, String> map = list.get(i);
					// msg = "��ѧ������" + kcmc + "�����񣬲����㱾��ѧ�����������,��ȷ�ϣ�����";

					if (zdsm.equalsIgnoreCase(map.get("djksmc"))) {
						int cj = Integer.parseInt(map.get("cj"));
						int tjcj = Integer.parseInt(tjz);
						// �ж��������Ƿ��������
						if (">".equalsIgnoreCase(tjlx)) {
							if (cj <= tjcj) {
								errXh[n] = map.get("xh");
								n++;
							}
						} else if (">=".equalsIgnoreCase(tjlx)) {
							if (cj < tjcj) {
								errXh[n] = map.get("xh");
								n++;
							}
						} else if ("=".equalsIgnoreCase(tjlx)) {
							if (cj != tjcj) {
								errXh[n] = map.get("xh");
								n++;
							}
						} else if ("<".equalsIgnoreCase(tjlx)) {
							if (cj >= tjcj) {
								errXh[n] = map.get("xh");
								n++;
							}
						} else if ("<=".equalsIgnoreCase(tjlx)) {
							if (cj > tjcj) {
								errXh[n] = map.get("xh");
								n++;
							}
						}
					}
				}
			}
		}
		return errXh;
	}
	
	/**
	 * �ж�ѧϰ�����Ƿ�����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] isXxjb(ZjcmPjpyModel model, String[] pjxh, String tjlx,
			String tjz, String bjrs) throws Exception {

		DAO dao = DAO.getInstance();
		
		// ����ѧ��
		String xn = model.getXn();
		// ����ѧ��
		String xq = model.getXq();
		
		int n = 0;
		String errXh[] = new String[pjxh.length];
		
		// �������ѧ������������б�
		List<HashMap<String, String>> nowZyList = getPmList(model, "zypm");
		
		// ���ǰһѧ�ڵ����������б�
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("xn", xn);
		map.put("xq", xq);
		map = dao.getBeforeXq(map);

		String befXn = map.get("xn");
		String befXq = map.get("xq");

		ZjcmPjpyModel befModel = new ZjcmPjpyModel();

		befModel.setXn(befXn);
		befModel.setXq(befXq);
		befModel.setNj(model.getNj());
		befModel.setXydm(model.getXydm());
		befModel.setZydm(model.getZydm());
		befModel.setBjdm(model.getBjdm());
		List<HashMap<String, String>> befZyList = getPmList(befModel, "zypm");
		
		// �ж������ֽ������
		if (nowZyList != null && nowZyList.size() > 0) {
			if (befZyList != null && befZyList.size() > 0) {
				if (pjxh != null && pjxh.length > 0) {
					for (int i = 0; i < nowZyList.size(); i++) {
						HashMap<String, String> nowMap = nowZyList.get(i);
						for (int j = 0; j < befZyList.size(); j++) {
							HashMap<String, String> befMap = befZyList.get(j);
							for (int k = 0; k < pjxh.length; k++) {
								if (nowMap.get("xh").equalsIgnoreCase(befMap.get("xh"))
									&& befMap.get("xh").equalsIgnoreCase(pjxh[k])) {
									// ǰ������
									float befpm = Float.parseFloat(befMap.get("pm"));
									// ��������
									float nowpm = Float.parseFloat(nowMap.get("pm"));
									// ��������
									float pm = befpm - nowpm;
									// ��ѧ����������
									float tjpm = Float.parseFloat(bjrs)
											* Float.parseFloat(tjz) / 100;
									tjpm = Math.round(tjpm);
									// �ж��������Ƿ��������
									if (">".equalsIgnoreCase(tjlx)) {
										if (pm <= tjpm) {
											errXh[n] = pjxh[j];
											n++;
										}
									} else if (">=".equalsIgnoreCase(tjlx)) {
										if (pm < tjpm) {
											errXh[n] = pjxh[j];
											n++;
										}
									} else if ("=".equalsIgnoreCase(tjlx)) {
										if (pm != tjpm) {
											errXh[n] = pjxh[j];
											n++;
										}
									} else if ("<".equalsIgnoreCase(tjlx)) {
										if (pm >= tjpm) {
											errXh[n] = pjxh[j];
											n++;
										}
									} else if ("<".equalsIgnoreCase(tjlx)) {
										if (pm > tjpm) {
											errXh[n] = pjxh[j];
											n++;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return errXh;
	}
	
	/**
	 * �ж�У��ͨ���Ƿ�����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] isXjtb(ZjcmPjpyModel model, String[] pjxh) throws Exception {

		String xn = model.getXn();// ����ѧ��
		String xq = model.getXq();// ����ѧ��

		String errXh[] = new String[pjxh.length];

		PjpyXfjsService xfService = new PjpyXfjsService();

		List<HashMap<String, String>> tbList = xfService.getXskqqk("", xn, xq,
				"xx");

		// �����У��ͨ����ѧ��
		if (pjxh != null && pjxh.length > 0) {
			
			if (tbList != null && tbList.size() > 0) {
				
				for (int i = 0; i < pjxh.length; i++) {
					
					for (int j = 0; j < tbList.size(); j++) {
						
						HashMap<String, String> map = tbList.get(j);
						
						String xh = map.get("xh");
						
						if (pjxh[i].equalsIgnoreCase(xh)) {
							
							errXh[i] = pjxh[i];
							
						}
					}
				}
			}
		}

		return errXh;
	}
	
	/**
	 * �жϿ��ν����Ƿ�����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] isKkjs(ZjcmPjpyModel model, String[] pjxh) throws Exception {
			
		String xn = model.getXn();// ����ѧ��
		String xq = model.getXq();// ����ѧ��

		String errXh[] = new String[pjxh.length];
		
		PjpyXfjsService xfService = new PjpyXfjsService();

		 List<HashMap<String, String>> kkList = xfService.getXskqqk("", xn, xq,
				"xy");

		// ����п�����ѧ��
		if (pjxh != null && pjxh.length > 0) {
			
			if (kkList != null && kkList.size() > 0) {
				
				for (int i = 0; i < pjxh.length; i++) {
					
					for (int j = 0; j < kkList.size(); j++) {
						
						HashMap<String, String> map = kkList.get(j);
						
						String xh = map.get("xh");
						String kkjs = map.get("kkjs");
						
						// ���ν���>0��ѧ��
						if (pjxh[i].equalsIgnoreCase(xh) && !Base.isNull(kkjs)
								&& (Integer.parseInt(kkjs) > 0)) {

							errXh[i] = pjxh[i];

						}
					}
				}
			}
		}
		
		return errXh;
	}
	
	/**
	 * �ж��û��Ƿ��ǲ������û�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean isCpz(ZjcmPjpyModel model) {
		return dao.isCpz(model);
	}

	/**
	 * ���У�ڽ�ѧ���걨�б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getJxjSqXnList(ZjcmPjpyModel model)
			throws Exception {

		ArrayList<String[]> list = new ArrayList<String[]>();
		List<HashMap<String, String>> pmList = getZhfPmList(model);

		// ���ʸ�������ѧ��
		String[] pjxh = Jxjtj(model);
		// �ѻ�ü�ý�ѧ�������ƺţ���ѧ��
		String[] jdXh = getJxjjdXh(model);
		pjxh = checkCfsj(pjxh, jdXh);
		
		if (pmList != null && pmList.size() > 0) {
			if (pjxh != null && pjxh.length > 0) {
				for (int i = 0; i < pmList.size(); i++) {

					HashMap<String, String> map = pmList.get(i);

					String[] arr = new String[14];

					String xh = map.get("xh");// ѧ��
					String xm = map.get("xm");// ����
					String xb = map.get("xb");// �Ա�
					String dyzhf = map.get("dyzhf");// �����ۺ��
					String zyzhf = map.get("zyzhf");// �����ۺ��
					String tyzhf = map.get("tyzhf");// �����ۺ��
					String nlzhf = map.get("nlzhf");// �����ۺ��
					String zhf = map.get("zhf");// �ۺϷ�
					String dypm = map.get("dypm");// ��������
					String zypm = map.get("zypm");// ��������
					String typm = map.get("typm");// ��������
					String nlpm = map.get("nlpm");// ��������
					String zhpm = map.get("zhpm");// �ۺ�����
					String pk = xh;// ����

					boolean flag = false;
					for (int j = 0; j < pjxh.length; j++) {
						if (pjxh[j].equalsIgnoreCase(xh)) {
							flag = true;
						}
					}

					if (flag) {
						arr[0] = pk;
						arr[1] = xh;
						arr[2] = xm;
						arr[3] = xb;
						arr[4] = dyzhf;
						arr[5] = dypm;
						arr[6] = zyzhf;
						arr[7] = zypm;
						arr[8] = tyzhf;
						arr[9] = typm;
						arr[10] = nlzhf;
						arr[11] = nlpm;
						arr[12] = zhf;
						arr[13] = zhpm;

						list.add(arr);
					}
				}
			}}
		return list;
	}
	
	/**
	 * ��ý�ѧ��������������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjsqInfo(ZjcmPjpyModel model)
			throws Exception {

		String xh = model.getXh();// ������ѧ��
		String xn = model.getXn();// ����ѧ��
		String xq = model.getXq();// ����ѧ��
		String jxjdm = model.getJxjdm();// ��ѧ�����
		String rychdm = model.getRychdm();// �����ƺŴ���

		// ----------------ѧ��������Ϣ-------------------
		HashMap<String, String> map = getStuInfo(xh); 

		map.put("xn", xn);// ����ѧ��
		map.put("xq", xq);// ����ѧ��
		map.put("jxjdm", jxjdm);// ��ѧ�����
		map.put("rychdm", rychdm);// �����ƺŴ���

		// ----------------ѧ��ģ��ӿ�-------------------
		PjpyXfjsService xfService = new PjpyXfjsService();
		
		List<HashMap<String, String>> kkList = getKkList(model);
		// xfService.getXskqqk(xh, xn, xq,"xy");
		List<HashMap<String, String>> tbList = xfService.getXskqqk(xh, xn, xq,
				"xx");

		// ���ν���
		if (kkList != null && kkList.size() > 0) {
			map.put("kkjs", kkList.get(0).get("kkjs"));
		} else {
			map.put("kkjs", "��");
		}

		// У��ͨ��
		if (tbList != null && tbList.size() > 0) {
			map.put("xjtb", "��");
		} else {
			map.put("xjtb", "��");
		}

		// ---------------Υ�ʹ��ֽӿ�-------------------
		WjcfZjcmDAO wjcfDAO = new WjcfZjcmDAO();
		WjcfZjcmModel wjModel = new WjcfZjcmModel();
		
		wjModel.setXh(xh);
		wjModel.setXn(xn);
		wjModel.setXq(xq);
		
		String wjInfo = wjcfDAO.queryStuLxckxx(wjModel);
		wjInfo = Base.isNull(wjInfo) ? "��" : wjInfo;
		
		map.put("wjInfo", wjInfo);

		// ---------------����ӿڣ�������Ƿѧ��-------------------
		//TODO
		
		// ---------------Ӣ���ļ�����������ɼ����-------------------
		String tableName = "view_zjcm_djksxx";
		String query = " where xh = ? ";
		String[] inPutList = new String[] { xh };
		String[] colList = new String[] { "kcm", "cj" };
		
		List<HashMap<String, String>> djksList = CommonQueryDAO
				.commonQueryforList(tableName, query, inPutList, colList, "");
		
		String yycj = "";
		String pccj = "";
		
		if (djksList != null && djksList.size() > 0) {
			
			for (int i = 0; i < djksList.size(); i++) {
				HashMap<String, String> djks = djksList.get(i);
				String kcm = djks.get("kcm");// �γ���
				String cj = djks.get("cj");// �ɼ�
				if ("yy4j".equalsIgnoreCase(kcm)) {
					yycj += "Ӣ���ļ���" + cj + "�� ";
				} else if ("yy3j".equalsIgnoreCase(kcm)) {
					yycj += "Ӣ��������" + cj + "�� ";
				} else if ("jsj1j".equalsIgnoreCase(kcm)) {
					pccj += "�����һ����" + cj + "�� ";
				} else if ("jsj2j".equalsIgnoreCase(kcm)) {
					pccj += "�����������" + cj + "�� ";
				}
			}
		}
		
		map.put("yycj", Base.isNull(yycj) ? "��" : yycj);
		map.put("pccj", Base.isNull(pccj) ? "��" : pccj);

		// ---------------�ɼ������-------------------
		String bjgs = dao.getBjgs(model);
		map.put("bjgs", bjgs);
		
		// --------------�ѻ�ý�ѧ�������ƺ�---------------
		
		DAO tyDao = DAO.getInstance();
		
		// �����һѧ��ѧ��
		HashMap<String, String> xnxqMap = new HashMap<String, String>();
		xnxqMap.put("xn", xn);
		xnxqMap.put("xq", xq);
		xnxqMap = tyDao.getBeforeXq(xnxqMap);

		String befXn = xnxqMap.get("xn");
		String befXq = xnxqMap.get("xq");

		xnxqMap = new HashMap<String, String>();
		xnxqMap.put("bxn", xn);
		xnxqMap.put("bxq", xq);
		xnxqMap.put("sxn", befXn);
		xnxqMap.put("sxq", befXq);
		xnxqMap.put("xh", xh);
		
		List<HashMap<String, String>> ryList = dao.getJxjRyList(xnxqMap);
		
		String bxqry = "��";// ��ѧ�ڻ�ý�ѧ�������ƺţ�
		String sxqry = "��";// ��ѧ�ڻ�ý�ѧ�������ƺţ�
		
		if (ryList != null && ryList.size() > 0) {

			for (int i = 0; i < ryList.size(); i++) {
				// ��ѧ��
				if ("bxq".equalsIgnoreCase(ryList.get(i).get("xq"))) {
					
					bxqry = ("��".equalsIgnoreCase(bxqry)) ? "" : bxqry;
					
					if (Base.isNull(bxqry)) {
						bxqry += ryList.get(i).get("rymc");
					} else {
						bxqry += "," + ryList.get(i).get("rymc");
					}
				}

				if ("sxq".equalsIgnoreCase(ryList.get(i).get("xq"))) {
					
					sxqry = ("��".equalsIgnoreCase(sxqry)) ? "" : sxqry;
					
					if (Base.isNull(sxqry)) {
						sxqry += ryList.get(i).get("rymc");
					} else {
						sxqry += "," + ryList.get(i).get("rymc");
					}
				}
			}

			bxqry = ("��".equalsIgnoreCase(bxqry)) ? "��" : bxqry;
			sxqry = ("��".equalsIgnoreCase(sxqry)) ? "��" : sxqry;
	
		}
		map.put("sxqry", sxqry);
		map.put("bxqry", bxqry);
		
		// ---------------����ۺϷ��Լ����������Ϣ-------------------
		model.setXh("");
		model.setNj(map.get("nj"));
		model.setXydm(map.get("xydm"));
		model.setZydm(map.get("zydm"));
		model.setBjdm(map.get("bjdm"));
		List<HashMap<String, String>> pmList = getZhfPmList(model);

		if (pmList != null && pmList.size() > 0) {

			for (int i = 0; i < pmList.size(); i++) {

				HashMap<String, String> zhfPm = pmList.get(i);

				if (zhfPm.get("xh").equalsIgnoreCase(xh)) {
					map.put("dyf", zhfPm.get("dyf"));// ������
					map.put("dyzhf", zhfPm.get("dyzhf"));// �����ۺ��
					map.put("dypm", zhfPm.get("dypm"));// ��������

					map.put("zyf", zhfPm.get("zyf"));// ������
					map.put("zyzhf", zhfPm.get("zyzhf"));// �����ۺ��
					map.put("zypm", zhfPm.get("zypm"));// ��������

					map.put("tyf", zhfPm.get("tyf"));// ������
					map.put("tyzhf", zhfPm.get("tyzhf"));// �����ۺ��
					map.put("typm", zhfPm.get("typm"));// ��������

					map.put("nlf", zhfPm.get("nlf"));// ������
					map.put("nlzhf", zhfPm.get("nlzhf"));// �����ۺ��
					map.put("nlpm", zhfPm.get("nlpm"));// ��������

					map.put("zhf", zhfPm.get("zhf"));// �ۺϷ�
					map.put("zhpm", zhfPm.get("zhpm"));// �ۺ�����
				}
			}
		}

		return map;
	}

	/**
	 * ��ù��ҽ�ѧ������Ҫ����Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getGjjxjNeedInfo(ZjcmPjpyModel model)
			throws Exception {
		
		// TODO
		DAO tyDao = DAO.getInstance();

		String xh = model.getXh();// ������ѧ��
		String xn = model.getXn();// ����ѧ��
		String xq = model.getXq();// ����ѧ��

		HashMap<String, String> infoMap = new HashMap<String, String>();
		
		// �����һѧ��ѧ��
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xn", xn);
		map.put("xq", xq);
		map = tyDao.getBeforeXq(map);

		String befXn = map.get("xn");
		String befXq = map.get("xq");

		map = new HashMap<String, String>();
		map.put("bxn", xn);
		map.put("bxq", xq);
		map.put("sxn", befXn);
		map.put("sxq", befXq);
		map.put("xh", xh);
		
		// ��ð༶����
		String bjdm = model.getBjdm();
		String bjrs = dao.getBjRs(bjdm).get("num");
		
		// �ɼ��������
		List<HashMap<String, String>> cjpmList = dao.getCjPmQk(map);
		
		if (cjpmList != null && cjpmList.size() > 0) {
			for (int i = 0; i < cjpmList.size(); i++) {
				// ��ѧ�ڳɼ�����
				if ("bxq".equalsIgnoreCase(cjpmList.get(i).get("xq"))) {
					infoMap.put("bxqcj", cjpmList.get(i).get("cjpm") + "/"
							+ bjrs);
				}
				// ��ѧ�ڳɼ�����
				if ("sxq".equalsIgnoreCase(cjpmList.get(i).get("xq"))) {
					infoMap.put("sxqcj", cjpmList.get(i).get("cjpm") + "/"
							+ bjrs);
				}
			}
		}
		
		// �ۺϳɼ����������
		List<HashMap<String, String>> zhfPmList = getZhfPmList(model);// ��ѧ��
		
		if (zhfPmList != null && zhfPmList.size() > 0) {
			infoMap.put("bxqzhf", zhfPmList.get(0).get("zhf"));
			infoMap.put("bxqzhfpm", zhfPmList.get(0).get("zhpm") + "/" + bjrs);
		}

		model.setXn(befXn);
		model.setXq(befXq);
		zhfPmList = getZhfPmList(model);// ��ѧ��
		
		if (zhfPmList != null && zhfPmList.size() > 0) {
			infoMap.put("sxqzhf", zhfPmList.get(0).get("zhf"));
			infoMap.put("sxqzhfpm", zhfPmList.get(0).get("zhpm") + "/" + bjrs);
		}
		
		// �ѻ�ý�ѧ�������ƺ�
		List<HashMap<String, String>> ryList = dao.getJxjRyList(map);
		
		if (ryList != null && ryList.size() > 0) {

			String bxqry = "";// ��ѧ�ڻ�ý�ѧ�������ƺţ�
			String sxqry = "";// ��ѧ�ڻ�ý�ѧ�������ƺţ�

			for (int i = 0; i < ryList.size(); i++) {
				// ��ѧ��
				if ("bxq".equalsIgnoreCase(ryList.get(i).get("xq"))) {
					if (Base.isNull(bxqry)) {
						bxqry += ryList.get(i).get("rymc");
					} else {
						bxqry += "," + ryList.get(i).get("rymc");
					}
				}

				if ("sxq".equalsIgnoreCase(ryList.get(i).get("xq"))) {
					if (Base.isNull(bxqry)) {
						sxqry += ryList.get(i).get("rymc");
					} else {
						sxqry += "," + ryList.get(i).get("rymc");
					}
				}
			}

			String rymc = ((Base.isNull(bxqry)) ? "" : bxqry + "(��ѧ��)")
					+ ((Base.isNull(sxqry)) ? "" : sxqry + "(��ѧ��)");
			infoMap.put("rymc", rymc);
		}
		return infoMap;
	}

	/**
	 * ����걨��ȫ��ѧ��
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getJxjsbz(ArrayList<String[]> list) {
		String[] sbzxh = null;
		if (list != null && list.size() > 0) {
			sbzxh = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				String xh = list.get(i)[1];
				sbzxh[i] = xh;
			}
		}
		return sbzxh;
	}
	
	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * @param msg(��ʾ��Ϣ)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getPjpyList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {
		return dao.getPjpyList(tableName, dm, mc, msg, pk, pkValue);
	}
	
	/**
	 * ��ý�ѧ��������б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getJxjjdList(ZjcmPjpyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "zjcm_jdsz";
		String[] colList = new String[] { "xn", "xq", "lx", "jxjdm", "fjdlx",
				"fjddm", "fjddm" };

		String[] queryList = new String[] { "xn", "xq", "lx", "jxjdm" };
		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();

		List<HashMap<String, String>> wsdjList = CommonQueryDAO
				.commonQueryforList(tableName, query, myQuery.getInputList(),
						colList, "");
		return wsdjList;
	}
	
	/**
	 * ��ý�ѧ������ѧ��
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getJxjjdXh(ZjcmPjpyModel model) throws Exception {

		String[] jdXh = null;
		// ��ѧ�������
		List<HashMap<String, String>> jxjjdList = dao.getJxjjdList(model);

		// ���ڽ�ѧ����
		boolean jxjjd = false;
		// ���������ƺż��
		boolean rychjd = false;
		
		if (jxjjdList != null && jxjjdList.size() > 0) {
			
			String[] fjdlx = new String[jxjjdList.size()];
			String[] fjddm = new String[jxjjdList.size()];
			
			for (int i = 0; i < jxjjdList.size(); i++) {
				HashMap<String, String> map = jxjjdList.get(i);
				fjdlx[i] = map.get("fjdlx");
				fjddm[i] = map.get("fjddm");
				if ("jxj".equalsIgnoreCase(fjdlx[i])) {
					jxjjd = true;
				} else if ("rych".equalsIgnoreCase(fjdlx[i])) {
					rychjd = true;
				}
			}
			
			// �Ѿ���ò��ɼ�ý�ѧ�������ƺţ���ѧ��
			List<String> xhList = dao.getJxjjdXhList(model, jxjjd, rychjd);

			if (xhList != null && xhList.size() > 0) {
				jdXh = new String[xhList.size()];
				for (int i = 0; i < xhList.size(); i++) {
					jdXh[i] = xhList.get(i);
				}
			}
		}
		return jdXh;
	}
	
	/**
	 * ���潱ѧ���걨��Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveJxjsb(ZjcmPjpyModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveJxjsb(model, request);
	}	

	/**
	 * ��ѧ����������ݵ���
	 * @param model
	 * @param os
	 */
	public void exportJxjjeHzData(ZjcmPjpyModel model, OutputStream os) throws Exception{
		//��ѯ�����еļ�¼��
		List<String[]> data = dao.queryJxjjehzxx(model);
		//��ѯ���л�ѧ����ѧ��
		List<String[]> xhList = dao.queryJxjjeXhxx(model);
		//��ѯ�������Ҫ�Ľ�ѧ�������Ϣ
		List<HashMap<String, String>> dmList = dao.queryDmList();
		List<String[]> rs = new ArrayList<String[]>();
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("��ѧ������ܱ�", 0);
		//д���һ����ͷ
		writeYjTitle(model, ws);
		//д�������ͷ
		writeEjTitle(ws);
		
		//ͨ��ѧ�ţ������ݣ���ѧ�������װҪ��������� �������ܽ��
		float zje = appendData(data, xhList, dmList, rs);
		//����񽱽������
		WritableCellFormat format = ExcelMB.getWritableCellFormat(10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
		ExcelMB.writeDataToCellByIterator(ws, rs, 0, 2, format);
		//�ϲ��༶��Ԫ��
		ExcelMB.mergeCellsData(rs, ws, 0, 2, format);
		
		//����ܼƽ��
		int size = !rs.isEmpty() ? rs.size() : 0;
		ws.addCell(new Label(0, 2+size, "�ܼ�", format));
		ws.mergeCells(0, 2+size, 6, 2+size);
		ws.addCell(new Label(7, 2+size, String.valueOf(zje), format));
		
		//�����ע��Ϣ
		ws.addCell(new Label(0, 3+size, " ��ע������ϸ�˶�ѧ�������ͽ���������Ƿ���ȷ��" 
				+ "�з���©�����������⴦����ԭ�����޸ģ������ֽ�ʰ�12��25�գ����壩����10��00֮ǰ�Ͻ�" 
				+ "��лл���Ƿ�ȫ����ȷ����д�����ȷ���ǲ���ȷ�����˶���ǩ����             �˶�ʱ�䣺" 
				+ "            ѧԺ�������£�", format));
		ws.mergeCells(0, 3+size, 9, 5+size);
		
		//���õ�1,2,8,9��Ԫ����
		ws.setColumnView(0, 17);
		ws.setColumnView(1, 9);
		ws.setColumnView(7, 12);
		ws.setColumnView(8, 20);
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}

	//ͨ��ѧ�ţ������ݣ���ѧ�������װҪ���������
	private float appendData(List<String[]> data, List<String[]> xhList,
			List<HashMap<String, String>> dmList, List<String[]> rs) {
		float zje = 0;
		if (!xhList.isEmpty() && !dmList.isEmpty() && !data.isEmpty()) {
			for (String[] arr : xhList) {
				if (arr != null && arr.length >= 4) {
					String[] oneArr = new String[]{"", "", "", "", "", "", "", "", "", ""};
					oneArr[0] = arr[2];
					oneArr[1] = arr[1];
					float je = 0;
					for (HashMap<String, String> map : dmList) {
						if (!map.isEmpty()) {
							for (String[] oneData : data) {
								//ͨ��������ȡ��ѧ������
								je = getJxjmcByCondition(arr, oneArr, je, map, oneData);
							}
						}
					}
					oneArr[7] = je != 0 ? String.valueOf(je) : "";
					oneArr[8] = arr[3];
					oneArr[9] = "";
					//���Ϊ���0 �����Ϊ��
					oneArr[2] = StringUtils.isNotNull(oneArr[2]) ? oneArr[2]
							.substring(0, oneArr[2].length() - 1) : "";
					oneArr[3] = StringUtils.isNotNull(oneArr[3]) ? oneArr[3]
							.substring(0, oneArr[3].length() - 1) : "";
					oneArr[4] = StringUtils.isNotNull(oneArr[4]) ? oneArr[4]
							.substring(0, oneArr[4].length() - 1) : "";
					oneArr[5] = StringUtils.isNotNull(oneArr[5]) ? oneArr[5]
							.substring(0, oneArr[5].length() - 1) : "";
					oneArr[6] = StringUtils.isNotNull(oneArr[6]) ? oneArr[6]
							.substring(0, oneArr[6].length() - 1) : "";
					//������Ϊ���������κ�һ�����Ϊ�����������
					if (StringUtils.isNotNull(oneArr[1])
							&& (StringUtils.isNotNull(oneArr[2])
									|| StringUtils.isNotNull(oneArr[3])
									|| StringUtils.isNotNull(oneArr[4])
									|| StringUtils.isNotNull(oneArr[5]) 
									|| StringUtils.isNotNull(oneArr[6]))) {
						
						rs.add(oneArr);
					}
					
					//�����ܽ��
					zje += je;
				}
			}
		}
		return zje;
	}

	//ͨ�������õ���ѧ������
	private float getJxjmcByCondition(String[] arr, String[] oneArr, float je,
			HashMap<String, String> map, String[] oneData) {
		if (oneData != null && oneData.length > 0
				&& arr[0].equalsIgnoreCase(oneData[1])
				&& map.get("dm").equalsIgnoreCase(oneData[3])) {
			if ("1".equalsIgnoreCase(map.get("lx"))
					&& map.get("dm").equalsIgnoreCase(oneData[3])) {
				oneArr[2] += map.get("mc");
				oneArr[2] += ",";
			} else if ("2".equalsIgnoreCase(map.get("lx"))
					&& map.get("dm").equalsIgnoreCase(oneData[3])) {
				oneArr[3] += map.get("mc");
				oneArr[3] += ",";
			} else if ("3".equalsIgnoreCase(map.get("lx"))
					&& map.get("dm").equalsIgnoreCase(oneData[3])) {
				oneArr[4] += map.get("mc");
				oneArr[4] += ",";
			} else if ("4".equalsIgnoreCase(map.get("lx"))
					&& map.get("dm").equalsIgnoreCase(oneData[3])) {
				oneArr[5] += map.get("mc");
				oneArr[5] += ",";
			} else if ("5".equalsIgnoreCase(map.get("lx"))
					&& map.get("dm").equalsIgnoreCase(oneData[3])) {
				oneArr[6] += map.get("mc");
				oneArr[6] += ",";
			}
			je += StringUtils.isNotNull(oneData[5]) ? Float
					.parseFloat(oneData[5]) : 0;
		}
		return je;
	}

	//д�������ͷ��Ϣ
	private void writeEjTitle(WritableSheet ws) throws Exception {
		WritableCellFormat format = ExcelMB.getWritableCellFormat(12, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
		String[] title = { "�༶", "����", "Ժ�ڽ�ѧ�����ߣ�һ���������ȣ�", "����ѧ�������",
						   "����ѧ���ɲ������", "��������", "Ժ�⽱ѧ������", "���Ž��", 
						   "ѧ�����п���", "��ע" };
		ExcelMB.writeEjTitleToCell(ws, title, 0, 1, format);
	}

	//д���һ����ͷ��EXCEL��
	private void writeYjTitle(ZjcmPjpyModel model, WritableSheet ws) throws Exception {
		//��һ����ͷ��ʽ
		WritableCellFormat format = ExcelMB.getWritableCellFormat(14, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
		
		StringBuilder title1 = new StringBuilder(Base.xxmc)
							.append(model.getXn())
							.append("ѧ���")
							.append(model.getXq())
							.append("ѧ��");
		title1.append(model.getXymc());
		title1.append("(");
		if (StringUtils.isNotNull(model.getNj())) {
			title1.append(model.getNj());
			title1.append("�꼶");
		}
		title1.append("ѧ��");
		if (StringUtils.isNotNull(model.getYhmc())) {
			title1.append(model.getYhmc());
		}
		title1.append(")");
		title1.append("���������������ܱ�");
		
		ExcelMB.writeTitleToCell(ws, title1.toString(), 0, 0, 9, 0, format);
	}

	/**
	 * ���潱ѧ�������ƺţ�������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveJdqk(ZjcmPjpyModel model) throws Exception {

		boolean flag = false;
		
		String[] arrzd = new String[] { "fjdlx", "fjddm" };
		String[] onezd = new String[] { "xn", "xq", "lx", "jxjdm" };
		String pk = "xn||xq||lx||jxjdm";
		String[] jxjjd = model.getJxjjd();
		String[] rychjd = model.getRychjd();

		String tableName = model.getTableName();
		String xn = model.getXn();// ����ѧ��
		String xq = model.getXq();// ����ѧ��
		String lx = model.getLx();// ����
		String jxjdm = model.getJxjdm();// ��ѧ�����
		String rychdm = model.getRychdm();// �����ƺŴ���
		
		// ���ɼ�õĽ�ѧ���Լ������ƺ�����
		int num = ((jxjjd != null && jxjjd.length > 0) ? jxjjd.length : 0)
				+ ((rychjd != null && rychjd.length > 0) ? rychjd.length
						: 0);

		String[] pkValue = new String[num];// ����
		String[] fjdlx = new String[num];// �Ǽ������
		String[] fjddm = new String[num];// �Ǽ�ô���

		// ȷ�ϱ��Ƚ���ĿΪ��ѧ���������ƺ�
		jxjdm = ("rych".equalsIgnoreCase(lx)) ? rychdm : jxjdm;

		int n = 0;

		// ���ɼ�ý�ѧ��
		if (jxjjd != null && jxjjd.length > 0) {
			for (int i = 0; i < jxjjd.length; i++) {
				pkValue[n] = xn + xq + lx + jxjdm;
				fjdlx[n] = "jxj";
				fjddm[n] = jxjjd[i];
				n++;
			}
		}

		// ���ɼ�������ƺ�
		if (rychjd != null && rychjd.length > 0) {
			for (int i = 0; i < rychjd.length; i++) {
				pkValue[n] = xn + xq + lx + jxjdm;
				fjdlx[n] = "rych";
				fjddm[n] = rychjd[i];
				n++;
			}
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		// ��������
		model.setXn(xn);
		model.setXq(xq);
		model.setLx(lx);
		model.setJxjdm(jxjdm);
		model.setFjdlx(fjdlx);
		model.setFjddm(fjddm);
		
		flag = savePjpy(saveForm, model);

		if(flag){
			flag = saveJxjQtJd(xn, xq, fjdlx, fjddm, lx, jxjdm, tableName);
		}
		
		return flag;
	}
	
	/**
	 * ���潱ѧ�������ƺţ�����������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveJxjQtJd(String xn, String xq, String[] fjdlx,
			String[] fjddm, String lx, String jxjdm, String tableName)
			throws Exception {
		return dao.saveJxjQtJd(xn, xq, fjdlx, fjddm, lx, jxjdm, tableName);
	}
	
	/**
	 * ����������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean cxJdsz(ZjcmPjpyModel model) throws Exception {

		boolean flag = false;

		String tableName = model.getTableName();

		String xn = model.getXn();// ����ѧ��
		String xq = model.getXq();// ����ѧ��
		String lx = model.getLx();// ����
		String jxjdm = model.getJxjdm();// ��ѧ�����
		String rychdm = model.getRychdm();// �����ƺŴ���
		// ȷ�ϱ��Ƚ���ĿΪ��ѧ���������ƺ�
		jxjdm = ("rych".equalsIgnoreCase(lx)) ? rychdm : jxjdm;

		String pk = "xn||xq||lx||jxjdm";
		String pkValue = xn + xq + lx + jxjdm;

		// ȷ�ϱ��Ƚ���ĿΪ��ѧ���������ƺ�
		jxjdm = ("rych".equalsIgnoreCase(lx)) ? rychdm : jxjdm;

		SaveForm saveForm = new SaveForm();

		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		flag = delPjpy(saveForm, model);

		if (flag) {
			flag = dao.cxCxjQtJd(xn, xq, lx, jxjdm, tableName);
		}

		return flag;
	}
	
	/**
	 * �����������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveCpzInfo(PjpyTyForm model) throws Exception {

		String[] arrzd = new String[] { "cpxy", "zwdm" };
		String[] onezd = new String[] { "xn", "xq", "zhfkg", "jxjkg" };
		String pk = "xn||xq||cpxy";

		// ������
		String tableName = model.getTableName();
		// ְ�����
		String[] zwdm = model.getZwdm();
		// ��ʼ��ѧ��ѧ��
		String xn = Base.isNull(model.getXn()) ? Base.getJxjsqxn() : model
				.getXn();
		String xq = Base.isNull(model.getXq()) ? Base.getJxjsqxq() : model
				.getXq();
		// ѧԺ����
		String xydm = model.getXydm();
		String[] pkValue = new String[zwdm.length];
		String[] cpxy = new String[zwdm.length];
		String[] cpzw = new String[zwdm.length];
		// ��������,ѧԺ�б�
		int n = 0;
		if (Base.isNull(xydm)) {// ûѡѧԺ������ȫ��ѧԺ
			List<HashMap<String, String>> xyList = Base.getXyList();
			if (xyList != null && xyList.size() > 0) {
				pkValue = new String[zwdm.length * xyList.size()];
				cpxy = new String[zwdm.length * xyList.size()];
				cpzw = new String[zwdm.length * xyList.size()];
				for (int i = 0; i < xyList.size(); i++) {
					for (int j = 0; j < zwdm.length; j++) {
						pkValue[n] = xn + xq + xyList.get(i).get("xydm");
						cpxy[n] = xyList.get(i).get("xydm");
						cpzw[n] = zwdm[j];
						n++;
					}
				}
			}
		} else {// ѡ��ѧԺ�����浥һѧԺ
			for (int i = 0; i < zwdm.length; i++) {
				pkValue[i] = xn + xq + xydm;
				cpxy[i] = xydm;
				cpzw[i] = zwdm[i];
			}
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		model.setCpxy(cpxy);
		model.setZwdm(cpzw);

		return savePjpy(saveForm, model);

	}
	
	/**
	 * ������������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean cxCpzInfo(PjpyTyForm model) throws Exception {

		String pk = "xn||xq||cpxy||zwdm";

		// ������
		String tableName = model.getTableName();
		// ְ���б�
		List<HashMap<String, String>> zwList = dao.getPjpyList("sxjy_bjgbzlb",
				"bjgbdm", "bjgbmc", "", "", "");
		zwList.remove(0);
		int num = (zwList != null && zwList.size() > 0) ? zwList.size() : 0;
		// ��ʼ��ѧ��ѧ��
		String xn = Base.isNull(model.getXn()) ? Base.getJxjsqxn() : model
				.getXn();
		String xq = Base.isNull(model.getXq()) ? Base.getJxjsqxq() : model
				.getXq();
		// ѧԺ����
		String xydm = model.getXydm();
		String[] pkValue = new String[num];
		// ��������,ѧԺ�б�
		int n = 0;
		if (Base.isNull(xydm)) {// ûѡѧԺ������ȫ��ѧԺ
			List<HashMap<String, String>> xyList = Base.getXyList();
			if (xyList != null && xyList.size() > 0) {
				pkValue = new String[num * xyList.size()];
				for (int i = 0; i < xyList.size(); i++) {
					for (int j = 0; j < num; j++) {
						pkValue[n] = xn + xq + xyList.get(i).get("xydm")
								+ zwList.get(j).get("dm");
						n++;
					}
				}
			}
		} else {// ѡ��ѧԺ�����浥һѧԺ
			for (int i = 0; i < num; i++) {
				pkValue[i] = xn + xq + xydm + zwList.get(i).get("dm");
			}
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return delPjpy(saveForm, model);

	}
	
	/**
	 * ����������������ѡ�ҳ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getPjpyTjszCard(){
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();
		if(Globals.XXDM_MJXY.equalsIgnoreCase(Base.xxdm)){
			//����ѧԺ
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", "jxj");
			map.put("cn", "��ѧ��");
			rs.add(map);
		}else if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			//��ְͨҵ��ѧ
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", "zhcpjxj");
			map.put("cn", "�ۺϲ�����ѧ��");
			rs.add(map);	
		}else{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", "jxj");
			map.put("cn", "��ѧ��");
			rs.add(map);
			
			map = new HashMap<String, String>();
			map.put("en", "rych");
			map.put("cn", "�����ƺ�");
			rs.add(map);
		}
		return rs;
	}
	
	/**
	 * ��ȡ����������������Ĭ����Ŀ
	 * @param lx
	 * @return String
	 * */
	public String getPjpyTjszLx(String lx){
		if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			lx = StringUtils.isNull(lx) ? "zhcpjxj" : lx;
		}else{
			lx = StringUtils.isNull(lx) ? "jxj" : lx;
		}
		return lx;
	}
}
