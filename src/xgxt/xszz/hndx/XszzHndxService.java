package xgxt.xszz.hndx;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.szdw.xmlg.wmbj.WmbjService;
import xgxt.utils.ExcelMethods;
import xgxt.xszz.XszzService;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommTjbbService;

import common.XszzXmdm;

public class XszzHndxService extends XszzService implements XszzCommTjbbService{

	XszzHndxDAO dao = new XszzHndxDAO();

	/**
	 * ��ø��Ի���ͷ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {
		DAO dao = DAO.getInstance();
		String[] colListCN = null;
		String[] colListEN = null;

		// ��������
		if ("cssz".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "ѧ��", Base.YXPZXY_KEY+"����", "������" };
			colListEN = new String[] { "xn", "xymc", "num" };
			
			//���������������б�
			List<HashMap<String, String>> djList = dao.getWhList(
					"hndx_xszz_jjknsjb", "dm", "mc", "", "", "");
			if (djList != null && djList.size() > 0) {
				
				colListCN = new String[2 + djList.size()];
				
				colListCN[0] = "ѧ��";
				colListCN[1] = Base.YXPZXY_KEY+"����";
				colListCN[2] = "������";
				
				colListEN = new String[2 + djList.size()];
				
				colListEN[0] = "xn";
				colListEN[1] = "xymc";
				colListEN[2] = "num";
				
				for (int i = 1; i < djList.size(); i++) {
					
					HashMap<String, String> map = djList.get(i);
					String jb = "jb" + map.get("dm");
					String mc = map.get("mc");
					
					colListEN[2 + i] = jb;
					colListCN[2 + i] = mc;
				}

			}
		}
		return dao.arrayToList(colListEN, colListCN);
	}
	
	/**
	 * ���澭������������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveJjknssq(XszzTyForm model, String table,
			HttpServletRequest request) throws Exception {

		String filePath = uploadFile(model, request);

		if (!Base.isNull(filePath)) {
			model.setScdz(filePath);
		}
		
		String[] onezd = new String[] { "brjdqzmbqk", "dydj", "jtdd", "jtdh",
				"jtdxrs", "jtfzqk", "jtnzsr", "jtrjsr", "jtrs", "jtsrly",
				"jtyzsr", "qtqk", "scdz", "sfcj", "sfdb", "sfge", "sfjthb",
				"sflszn", "sfpkzm", "sfzrch", "xh", "xn", "xsjtjjqk" };
		
		String pk = "xh||xn";
		String pkValue = model.getXh() + model.getXn();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		boolean result = saveXszz(saveForm, model, request);

		return result;
	}

	/**
	 * ��˾���������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean shJjknssq(XszzTyForm model, String table, String userType,
			Boolean isBjsh, String shzt) throws Exception {

		String[] onezd = null;
		String nowTime = getNowTime("YYYYMMDD");
		shzt = "tg".equalsIgnoreCase(shzt) ? "ͨ��" : "��ͨ��";

		if (isBjsh) {// �����λ��߰೤
			onezd = new String[] { "knsjb", "bjsh", "bjshyj", "bjshsj" };
			model.setBjsh(shzt);
			model.setBjshsj(nowTime);
		} else if ("xy".equalsIgnoreCase(userType)) {// ѧԺ
			onezd = new String[] { "knsjb", "xysh", "xyshyj", "xyshsj" };
			model.setXysh(shzt);
			model.setXyshsj(nowTime);
		} else {// ѧУ
			onezd = new String[] { "knsjb", "xxsh", "xxshyj", "xxshsj" };
			model.setXxsh(shzt);
			model.setXxshsj(nowTime);
		}

		String pk = "xh||xn";
		String pkValue = model.getXh() + model.getXn();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		boolean result = updateXszz(saveForm, model);

		return result;
	}
	
	/**
	 * �жϵ�½�û��Ƿ��а༶��˵�Ȩ��
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean isBjsh(String userName, HttpServletRequest request)
			throws Exception {

		HttpSession session = request.getSession();
		WmbjService wmbjService = new WmbjService();

		// �Ƿ������
		boolean isBzr  =  Boolean.parseBoolean(session.getAttribute("bzrQx").toString());
		// �Ƿ�೤
		boolean isBz = wmbjService.isBgb(userName);

		boolean flag = (isBzr || isBz) ? true : false;

		return flag;
	}

	/**
	 * �������ѧԺ�����б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getXzXyRsList(XszzTyForm model) {
		
		//ѧԺ����List
		ArrayList<String[]> xyList = dao.getXyRsList(model);
		
		return  xyList;
	}

	/**
	 * ���澭������������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveJjknsSz(XszzTyForm model, String table) throws Exception {
		
		
		ArrayList<String[]> list = getXzXyRsList(model);
		// ѧԺ
		String[] arr_xy = new String[list.size()];
		// ѧԺ����
		String[] arr_rs = new String[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			String[] xyInfo = list.get(i);
			arr_xy[i] = xyInfo[0];
			arr_rs[i] = xyInfo[3];
		}
		
		// ����
		List<HashMap<String, String>> jbList = dao.getWhList(
				"hndx_xszz_jjknsjb", "dm", "mc", "", "", "");
		jbList.remove(0);
		String[] arr_xmjb = new String[jbList.size()];
		
		for (int i = 0; i < jbList.size(); i++) {
			String jb = jbList.get(i).get("dm");
			arr_xmjb[i] = jb;
		}
		
		// ��Ŀ����
		String[] arr_xmbl = model.getXmbl();

		String[] szxy = null;
		String[] xmjb = null;
		String[] xmbl = null;
		String[] xmrs = null;
		String[] pkValue = null;
		String xn = model.getXn();
		//TODO Ŀǰд��
		String xmmc = "�����������϶�";
		
		// ��������
		if (arr_xy != null && arr_xy.length > 0 && arr_xmjb != null && arr_xmjb.length > 0) {
			
			szxy = new String[arr_xy.length * arr_xmjb.length];
			xmjb = new String[arr_xy.length * arr_xmjb.length];
			xmbl = new String[arr_xy.length * arr_xmjb.length];
			xmrs = new String[arr_xy.length * arr_xmjb.length];
			pkValue = new String[arr_xy.length * arr_xmjb.length];
			
			int n = 0;
			
			for (int i = 0; i < arr_xy.length; i++) {
				for (int j = 0; j < arr_xmjb.length; j++) {
					szxy[n] = arr_xy[i];
					xmjb[n] = arr_xmjb[j];
					xmbl[n] = arr_xmbl[j];
					//��������
					int rs = Math.round((Float.parseFloat(xmbl[j])
							* Float.parseFloat(arr_rs[i]) / 100));
					xmrs[n] = String.valueOf(rs);
					pkValue[n] = xn + arr_xy[i] + xmmc + arr_xmjb[j];
					n++;
				}
			}
		}
		
		model.setSzxy(szxy);
		model.setXmbl(xmbl);
		model.setXmjb(xmjb);
		//model.setXmmc(xmmc);
		model.setXmrs(xmrs);
		
		String[] arrzd = new String[] { "szxy", "xmjb", "xmbl","xmrs" };
		
		String[] onezd = new String[] { "xn", "xmmc" };
		
		String pk = "xn||szxy||xmmc||xmjb";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		boolean result = saveXszz(saveForm, model);

		return result;
	}
	
	/**
	 * ����ѧ����������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveKg(XszzTyForm model, String table,
			HttpServletRequest request) throws Exception {

		String[] onezd = new String[] { "xn", "xmmc", "kg" };
		String pk = "xn||xmmc";
		String pkValue = model.getXn() + model.getXmmc();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		boolean result = saveXszz(saveForm, model, request);

		return result;
	}
	
	/**
	 * ���ѧԺ��������(����)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXyRsBlList(XszzTyForm model) {
		return dao.getXyRsBlList(model);
	}
	
	/**
	 * ���ѧԺ����(��������)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXySzRsList(XszzTyForm model) {
		return dao.getXySzRsList(model);
	}
	
	/**
	 * �޸���Ŀ����
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean editXszzXmRs(XszzTyForm model, String table)
			throws Exception {

		String[] pkValue = null;

		String[] xmrs = model.getXmrs();
		String[] xmjb = model.getXmjb();
		String[] szxy = null; 
		
		if (xmrs != null && xmrs.length > 0) {
			pkValue = new String[xmrs.length];
			szxy = new String[xmrs.length];
			for (int i = 0; i < xmrs.length; i++) {
				pkValue[i] = model.getXn() + model.getXydm() + model.getXmmc()
						+ xmjb[i];
				szxy[i] = model.getXydm();
			}
		}

		model.setSzxy(szxy);
		
		String[] arrzd = new String[] { "szxy", "xmjb", "xmbl", "xmrs" };

		String[] onezd = new String[] { "xn", "xmmc" };
		
		String pk = "xn||szxy||xmmc||xmjb";
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		boolean result = saveXszz(saveForm, model);

		return result;
	}

	/**
	 * �ж���������ʱ�����Ƿ񳬹�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String isCgrs(XszzTyForm model)
			throws Exception {

		List<HashMap<String, String>> xyrsList = getPlshXyZrsList(model);
		
		String message = "";
		
		if (xyrsList != null && xyrsList.size() > 0) {
			for (int i = 0; i < xyrsList.size(); i++) {
				HashMap<String, String> map = xyrsList.get(i);
				//ѧԺ����
				String xymc = map.get("xymc");
				//��������
				String sqrs = map.get("sqrs");
				//����ͨ����
				String qttgr = map.get("qttgr");
				//��Ŀ����
				String xmrs = map.get("xmrs");
				
				// ��������
				if (Integer.parseInt(sqrs) + Integer.parseInt(qttgr) > Integer
						.parseInt(xmrs)) {
					message = xymc +"�ü������������������������������ȷ����";
					break;
				}
				
			}
		}
		return message;
	}
	
	/**
	 * ����������ѧ������ѧԺ��ǰѧ�����Ŀ��������
	 * @author luojw
	 */
	public List<HashMap<String, String>> getPlshXyZrsList(XszzTyForm model) {
		return dao.getPlshXyZrsList(model);
	}
	
	/**
	 * ������ͳ�Ʊ���
	 * @param form
	 * @param os
	 * */
	public void printKnstjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_KNS;//������
		String xymc = dao.getXymc(model.getXydm());//ѧԺ����
		List<String[]> data = dao.getKnsData(xmdm, model);//��ѯ����������

		StringBuilder title = new StringBuilder();
		title.append("���ϴ�ѧ");
		title.append(xymc);
		title.append("ƶ������ͳ�Ʊ�");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("ƶ������ͳ�Ʊ�", 0);

		try {
			excel.printTitle(ws, title.toString(), 19, 800);// ����			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(10,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			//�������
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "����", wcfTytle));
			ws.addCell(new Label(2, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(3, 2, Base.YXPZXY_KEY, wcfTytle));
			ws.addCell(new Label(4, 2, "רҵ�༶", wcfTytle));
			ws.addCell(new Label(5, 2, "����", wcfTytle));
			ws.addCell(new Label(6, 2, "��������", wcfTytle));
			ws.addCell(new Label(7, 2, "���֤��", wcfTytle));
			ws.addCell(new Label(8, 2, "�Ա�", wcfTytle));
			ws.addCell(new Label(9, 2, "����", wcfTytle));
			ws.addCell(new Label(10, 2, "ƶ���̶�", wcfTytle));
			ws.addCell(new Label(11, 2, "���������ڵ����", wcfTytle));
			ws.addCell(new Label(12, 2, "����", wcfTytle));
			ws.addCell(new Label(13, 2, "��ϵ�绰", wcfTytle));
			ws.addCell(new Label(14, 2, "��ͥ��ϸ��ַ", wcfTytle));
			ws.addCell(new Label(15, 2, "��ͥ�ʱ�", wcfTytle));
			ws.addCell(new Label(16, 2, "��ͥ��ϵ�绰", wcfTytle));
			ws.addCell(new Label(17, 2, "У��", wcfTytle));
			ws.addCell(new Label(18, 2, "��ע", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ���ҽ�ѧ��ѧ������������
	 * @param form
	 * @param os
	 * */
	public void printGjjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJJXJ;//���ҽ�ѧ��
		List<String[]> data = dao.getGjjxjData(xmdm, model);//��ѯҪ����������
		
		StringBuilder title = new StringBuilder();
		title.append("����ʡ");
		title.append(model.getXn());
		title.append("��Ȼ���ҽ�ѧ��ѧ������������");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("���ҽ�ѧ������", 0);

		try {
			excel.printTitle(ws, title.toString(), 19, 800);// ����
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// ��Ԫ����ʽ			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			ws.mergeCells(0, 1, 9, 1);
			ws.addCell(new Label(0, 1, "���Ƶ�λ��               �����£�                                           ����ʱ�䣺       ��       ��       ��", btomTytle));
			
			//�������
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "ѧУ����", wcfTytle));
			ws.addCell(new Label(2, 2, Base.YXPZXY_KEY+"����", wcfTytle));
			ws.addCell(new Label(3, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(4, 2, "����", wcfTytle));
			ws.addCell(new Label(5, 2, "�Ա�", wcfTytle));
			ws.addCell(new Label(6, 2, "��������", wcfTytle));
			ws.addCell(new Label(7, 2, "�������ڵ�", wcfTytle));
			ws.addCell(new Label(8, 2, "����", wcfTytle));
			ws.addCell(new Label(9, 2, "רҵ", wcfTytle));
			ws.addCell(new Label(10, 2, "�꼶", wcfTytle));
			ws.addCell(new Label(11, 2, "��ѧʱ��", wcfTytle));
			ws.addCell(new Label(12, 2, "У��", wcfTytle));
			ws.addCell(new Label(13, 2, "�����˺�", wcfTytle));
			ws.addCell(new Label(14, 2, "��ע", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
			//ҳ��			
			ws.mergeCells(0, data.size()+3, 7, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "�����ˣ�                        ��ϵ�ˣ�                         ��ϵ�绰��", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * ������־��ѧ��ѧ������������
	 * @param form
	 * @param os
	 * */
	public void printGjlsjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJLZJXJ;//������־��ѧ��
		List<String[]> data = dao.getGjlzjxjData(xmdm, model);//��ѯҪ����������
		
		StringBuilder title = new StringBuilder();
		title.append("����ʡ");
		title.append(model.getXn());
		title.append("��Ȼ������־��ѧ��ѧ������������");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("������־��ѧ������", 0);

		try {
			excel.printTitle(ws, title.toString(), 19, 800);// ����
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// ��Ԫ����ʽ			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			//�������
			ws.mergeCells(0, 1, 9, 1);
			ws.addCell(new Label(0, 1, "���Ƶ�λ��               �����£�                                           ����ʱ�䣺       ��       ��       ��", btomTytle));
			
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "ѧУ����", wcfTytle));
			ws.addCell(new Label(2, 2, Base.YXPZXY_KEY+"����", wcfTytle));
			ws.addCell(new Label(3, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(4, 2, "����", wcfTytle));
			ws.addCell(new Label(5, 2, "�Ա�", wcfTytle));
			ws.addCell(new Label(6, 2, "��������", wcfTytle));
			ws.addCell(new Label(7, 2, "�������ڵ�", wcfTytle));
			ws.addCell(new Label(8, 2, "����", wcfTytle));
			ws.addCell(new Label(9, 2, "רҵ", wcfTytle));
			ws.addCell(new Label(10, 2, "�꼶", wcfTytle));
			ws.addCell(new Label(11, 2, "��ѧʱ��", wcfTytle));
			ws.addCell(new Label(12, 2, "�����˺�", wcfTytle));
			ws.addCell(new Label(13, 2, "��ͥ��������", wcfTytle));
			ws.addCell(new Label(14, 2, "У��", wcfTytle));
			ws.addCell(new Label(15, 2, "��ע", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
			//ҳ��			
			ws.mergeCells(0, data.size()+3, 7, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "�����ˣ�                        ��ϵ�ˣ�                         ��ϵ�绰��", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ������ѧ��ѧ������������
	 * @param form
	 * @param os
	 * */
	public void printGjzxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_GJZXJ;//������ѧ��
		List<String[]> data = dao.getGjzxjData(xmdm, model);//��ѯҪ����������
		
		StringBuilder title = new StringBuilder();
		title.append("����ʡ");
		title.append(model.getXn());
		title.append("��Ȼ������ѧ��ѧ������������");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("������ѧ������", 0);

		try {
			excel.printTitle(ws, title.toString(), 19, 800);// ����
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// ��Ԫ����ʽ			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			
			//�������
			ws.mergeCells(0, 1, 9, 1);
			ws.addCell(new Label(0, 1, "���Ƶ�λ��               �����£�                                           ����ʱ�䣺       ��       ��       ��", btomTytle));
			
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "ѧУ����", wcfTytle));
			ws.addCell(new Label(2, 2, Base.YXPZXY_KEY+"����", wcfTytle));
			ws.addCell(new Label(3, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(4, 2, "����", wcfTytle));
			ws.addCell(new Label(5, 2, "�Ա�", wcfTytle));
			ws.addCell(new Label(6, 2, "��������", wcfTytle));
			ws.addCell(new Label(7, 2, "�������ڵ�", wcfTytle));
			ws.addCell(new Label(8, 2, "����", wcfTytle));
			ws.addCell(new Label(9, 2, "רҵ", wcfTytle));
			ws.addCell(new Label(10, 2, "�꼶", wcfTytle));
			ws.addCell(new Label(11, 2, "��ѧʱ��", wcfTytle));
			ws.addCell(new Label(12, 2, "��������", wcfTytle));
			ws.addCell(new Label(13, 2, "�������", wcfTytle));
			ws.addCell(new Label(14, 2, "�����˺�", wcfTytle));
			ws.addCell(new Label(15, 2, "У��", wcfTytle));
			ws.addCell(new Label(16, 2, "��ע", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}			
			//ҳ��			
			ws.mergeCells(0, data.size()+3, 7, data.size()+3);
			ws.addCell(new Label(0, data.size()+3, "�����ˣ�                        ��ϵ�ˣ�                         ��ϵ�绰��", btomTytle));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ������������ѧ��ѧ������������
	 * @param form
	 * @param os
	 * */
	public void printYxpksjxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_HNSGXYXPKSJXJ;//������������ѧ��
		List<String[]> data = dao.getYxpksjxjData(xmdm, model);//��ѯҪ����������
		
		StringBuilder title = new StringBuilder();
		title.append(model.getNd());
		title.append("����ʡ����ƶ������ѧ��ѧ������������");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("������������ѧ������", 0);

		try {
			excel.printTitle(ws, title.toString(), 13, 800);// ����			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
			WritableCellFormat btomTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// ��Ԫ����ʽ
			//�������			
			ws.mergeCells(0, 1, 9, 1);
			ws.addCell(new Label(0, 1, "���λ��               �����£�                                           �������ڣ�       ��       ��       ��", btomTytle));
			
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "ԺУ����", wcfTytle));
			ws.addCell(new Label(2, 2, Base.YXPZXY_KEY, wcfTytle));
			ws.addCell(new Label(3, 2, "רҵ", wcfTytle));
			ws.addCell(new Label(4, 2, "����", wcfTytle));
			ws.addCell(new Label(5, 2, "�Ա�", wcfTytle));
			ws.addCell(new Label(6, 2, "��������", wcfTytle));
			ws.addCell(new Label(7, 2, "����", wcfTytle));
			ws.addCell(new Label(8, 2, "ѧ��", wcfTytle));
			ws.addCell(new Label(9, 2, "��ѧʱ��", wcfTytle));
			ws.addCell(new Label(10, 2, "��ͥ���ڵ�(��ϸ��", wcfTytle));
			ws.addCell(new Label(11, 2, "���п���", wcfTytle));
			ws.addCell(new Label(12, 2, "��ע", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * �к��ʹ�ѧ����ѧ������ܱ�
	 * @param form
	 * @param os
	 * */
	public void printZhydxszxjtjbb(XszzTyForm model, OutputStream os){
		String xmdm = XszzXmdm.XSZZ_TJBB_ZHYDXSZXJ;//�к��ʹ�ѧ����ѧ����
		List<String[]> data = dao.getZhydxszxjData(xmdm, model);//��ѯҪ����������
		HashMap<String, String> map = dao.getStuCollect(model);//ѧ���������
		
		StringBuilder title = new StringBuilder();
		title.append("�й����������ᡰ�к��ʹ�ѧ����ѧ��������ѧ�����ܱ�");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("�к��ʹ�ѧ����ѧ������ܱ�", 0);		

		try {
			excel.printTitle(ws, title.toString(), 11, 800);// ����			
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ��ͨ��Ԫ����ʽ
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
			WritableCellFormat titleTytle = ExcelMB.getWritableCellFormat(12,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// ���ͷ��Ԫ����ʽ
			titleTytle.setBackground(Colour.GRAY_25);
			
			WritableCellFormat liTytle = ExcelMB.getWritableCellFormat(10,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.NONE);// С���嵥Ԫ����ʽ
			WritableCellFormat qzTytle = ExcelMB.getWritableCellFormat(12,
					true, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// С���嵥Ԫ����ʽ
			
			
			WritableCellFormat zhTytle = ExcelMB.getWritableCellFormat(12,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.NONE);// С���嵥Ԫ����ʽ
			
			//�������					
			ws.mergeCells(0, 2, 11, 2);
			ws.addCell(new Label(0, 2, "ѧУ���ƣ����£�_________", qzTytle));
			ws.mergeCells(1, 4, 10, 4);
			ws.addCell(new Label(1, 4, "ѧ���������", titleTytle));
			
			ws.addCell(new Label(1, 5, "��Ŀ���", wcfTytle));
			ws.addCell(new Label(2, 5, "��У������", wcfTytle));
			ws.addCell(new Label(3, 5, "����������", wcfTytle));
			ws.addCell(new Label(4, 5, "ƶ��������", wcfTytle));
			ws.addCell(new Label(5, 5, "ũ�弮ѧ����", wcfTytle));
			ws.mergeCells(6, 5, 7, 5);
			ws.addCell(new Label(6, 5, "������������", wcfTytle));
			ws.mergeCells(8, 5, 9, 5);
			ws.addCell(new Label(8, 5, "ƶ����������", wcfTytle));
			ws.addCell(new Label(10, 5, "�����ǩ��", wcfTytle));
			
			ws.addCell(new Label(1, 6, model.getXn(), wcfTytle));
			ws.addCell(new Label(2, 6, map.get("zxrs"), wcfTytle));
			ws.addCell(new Label(3, 6, map.get("tksrs"), wcfTytle));
			ws.addCell(new Label(4, 6, map.get("pksrs"), wcfTytle));
			ws.addCell(new Label(5, 6, map.get("ncxss"), wcfTytle));
			ws.mergeCells(6, 6, 7, 6);
			ws.addCell(new Label(6, 6, map.get("xstkrs"), wcfTytle));
			ws.mergeCells(8, 6, 9, 6);
			ws.addCell(new Label(8, 6, map.get("xspkrs"), wcfTytle));
			ws.addCell(new Label(10, 6, "", wcfTytle));
			
			ws.mergeCells(0, 10, 10, 10);
			ws.addCell(new Label(0, 10, "                        ������_________     ������_________       �ʺ�_________", zhTytle));
			ws.mergeCells(1, 12, 6, 12);
			ws.addCell(new Label(1, 12, "���˵�����١�ƶ���̶ȡ�����д������������ƶ�����������ѡ�����ѧУ�Դ˱����ʵ�Ը���", liTytle));
			ws.mergeCells(1, 13, 10, 13);
			ws.addCell(new Label(1, 13, "����ѧ���������", titleTytle));
			
			ws.addCell(new Label(1, 14, "����", wcfTytle));
			ws.addCell(new Label(2, 14, "�Ա�", wcfTytle));
			ws.addCell(new Label(3, 14, "ƶ���̶�", wcfTytle));
			ws.addCell(new Label(4, 14, "����", wcfTytle));
			ws.addCell(new Label(5, 14, "�����Ƿ�Ƿծ", wcfTytle));
			ws.addCell(new Label(6, 14, "ÿ�������", wcfTytle));
			ws.mergeCells(7, 14, 8, 14);
			ws.addCell(new Label(7, 14, "����Ժϵ��רҵ", wcfTytle));
			
			ws.addCell(new Label(9, 14, "��ϵ�绰", wcfTytle));
			ws.addCell(new Label(10, 14, "���֤��", wcfTytle));

			
			for (int i = 0; i < data.size(); i++) {	
				int rowNum = 0;
				for (int j = 0; j < data.get(i).length; j++) {
					if(j==7){
						ws.mergeCells(7, 15 + i, 8, 15 + i);
						rowNum++;
					}
					ws.addCell(new Label(rowNum+1, 15 + i, data.get(i)[j], wcfTytle));
					rowNum++;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	

	/***
	 * ��ӡ�������е�ͳ�Ʊ���
	 * @param form
	 * @param os
	 * */
	
	public void printZztjbb(XszzTyForm form, OutputStream os) {
		if(XszzXmdm.XSZZ_TJBB_KNS.equalsIgnoreCase(form.getTjbbdm())){//������ͳ��
			printKnstjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_GJJXJ.equalsIgnoreCase(form.getTjbbdm())){//���ҽ�ѧ��
			printGjjxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_GJLZJXJ.equalsIgnoreCase(form.getTjbbdm())){//������־��ѧ��
			printGjlsjxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_FJZXJ.equalsIgnoreCase(form.getTjbbdm())){//������ѧ��
			printGjzxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_YXPKSJXJ.equalsIgnoreCase(form.getTjbbdm())){//����ƶ������ѧ��
			printYxpksjxjtjbb(form,os);
		}else if(XszzXmdm.XSZZ_TJBB_ZHYDXSZXJ.equalsIgnoreCase(form.getTjbbdm())){//�к��ʹ�ѧ����ѧ����
			printZhydxszxjtjbb(form,os);
		}
	}
	
}
