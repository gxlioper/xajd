package xgxt.qgzx.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import common.Globals;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.qgzx.dao.QgzxCjffglDAO;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.form.QgzxForm;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description: �ڹ���ѧѧ����λ����Service
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ����
 * </p>
 * <p>
 * Version: 2.0
 * </p>
 * <p>
 * Time: 2009-12-02
 * </p>
 */
public class QgzxCjffglService {
	QgzxCjffglDAO dao = new QgzxCjffglDAO();

	/**
	 * ��ȡ�е���������
	 * 
	 * @param String
	 *            [] colList
	 * @param String
	 *            tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] colList, String tableName) {
		return dao.getColumnNameCN(colList, tableName);
	}

	/**
	 * ��ѯʣ�ྭ��
	 * 
	 * @param HashMap
	 *            <String, String> paramMap
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> querySyjf(HashMap<String, String> paramMap) {
		return dao.selectCjffSyjf(paramMap);
	}

	/**
	 * ��ѯѧ����𷢷���Ϣ
	 * 
	 * @param pkValue
	 * @return String[]
	 * */
	public List<HashMap<String, String>> queryXscjff(String pkValue,
			HashMap<String, String> paramMap) {
		String xxdm = paramMap.get("xxdm");
		List<HashMap<String, String>> ffList = dao.selectXscjff(pkValue,
				paramMap);
		// ��λ�����󣬷���ѧ���ڵ���ǰ�ĸ�λ����
		QgzxService service = new QgzxService();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
			// �㽭��
			ffList.addAll(service.queryTzqxsgw(ffList, pkValue, paramMap
					.get("nd"), paramMap.get("yf")));
		} else if (Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)) {
			// �㽭�Ƽ���ֻ���й������˼�¼��ѧ���ſɷ��ų��
			QgzxDao qgzxDao = new QgzxDao();
			ffList.addAll(qgzxDao.queryTzqxsgwForZjkj(pkValue));
		} else {
			ffList
					.addAll(service.queryTzqxsgw(ffList, "gwdm||gwsbsj",
							pkValue));
		}
		return ffList;
	}

	/**
	 * ��ѯѧ����𷢷���Ϣ����
	 * 
	 * @param QgzxForm
	 *            model String[] output
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> queryXscjffForExport(QgzxForm model, String[] output)
			throws Exception {
		return dao.selectXscjffxx(model, output);
	}

	/**
	 * �й����ʴ�ѧ��ѯѧ����𷢷���Ϣ����
	 * 
	 * @param QgzxForm
	 *            model String[] output
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> queryZgdzdxXscjffForExport(QgzxForm model,
			String[] output) throws Exception {
		List<String[]> list = new ArrayList<String[]>();
		String fflx = model.getFflx();
		// �й����ʴ�ѧ�·�ǰ��0
		// String yf = model.getYf();
		// yf = StringUtils.isNull(yf) ? "0" : yf;
		// model.setYf(Integer.parseInt(yf) + "");

		if ("��ʱ�ڹ���".equalsIgnoreCase(fflx)) {
			list = dao.selectXslsgzffxx(model, output);
		} else {
			list = dao.selectZgdzdxXscjffxx(model, output);
		}
		return list;
	}

	/**
	 * ����ѧ����𷢷���Ϣ
	 * 
	 * @param QgzxForm
	 *            model
	 * @param HttpServletRequest
	 *            request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean saveXscjff(QgzxForm model, HttpServletRequest request)
			throws Exception {
		boolean flag = true;
		String tableName = "xscjffb";
		String pk = "xh||gwdm||'-'||sqsj||nd||yf";
		String gwdm = model.getGwdm();
		String gwsbsj = model.getGwsbsj();
		String xh = model.getXh();
		String nd = model.getNd();
		String yf = model.getYf();
		String gzsj = model.getGzsj();
		String cjje = model.getCjje();
		String yhkh = model.getYhkh();
		String yhmc = model.getYhmc();
		String bz = model.getBz();
		String pkValue = xh + gwdm + "-" + gwsbsj + nd + yf;

		if (dao.checkExists(tableName, pk, pkValue)) {// ����¼�Ƿ����,��¼�Ѿ������Ƚ���¼ɾ�������²���
			flag = StandardOperation.delete(tableName, pk, pkValue, request);
		}
		if (flag) {// ��¼����
			String[] columns = { "xh", "gwdm", "sqsj", "nd", "yf", "gzsj",
					"cjje", "yhkh", "yhmc", "bz" };
			String[] values = { xh, gwdm, gwsbsj, nd, yf, gzsj, cjje, yhkh,
					yhmc, bz };
			flag = StandardOperation
					.insert(tableName, columns, values, request);
		}
		return flag;
	}

	/**
	 * ��𷢷���Ϣ�����޸�
	 * 
	 * @param QgzxForm
	 *            model
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean modiXscjffBatch(QgzxForm model, HttpServletRequest request)
			throws Exception {
		boolean flag = true;
		String pk = "xh||gwdm||sqsj||nd||yf";
		String[] pkV = model.getPkV();
		String[] sqlArr = new String[pkV.length];

		for (int i = 0; i < pkV.length; i++) {
			StringBuffer sb = new StringBuffer();
			sb.append(Base.isNull(model.getGzsj()) ? ", gzsj " : ("'"
					+ model.getGzsj() + "'"));
			sb.append(Base.isNull(model.getCjje()) ? ", cjje " : ("'"
					+ model.getCjje() + "'"));
			sb.append(Base.isNull(model.getYhkh()) ? ", yhkh " : ("'"
					+ model.getYhkh() + "'"));
			sb.append(Base.isNull(model.getYhmc()) ? ", yhmc " : ("'"
					+ model.getYhmc() + "'"));
			sb.append(Base.isNull(model.getBz()) ? ", bz " : ("'"
					+ model.getBz() + "'"));

			sqlArr[i] = "insert into xscjffb (xh,gwdm,sqsj,xn,nd,xq,yf,gzsj,cjje,yhkh,yhmc,bz)(select xh,gwdm,sqsj,xn,'"
					+ model.getNd()
					+ "',xq,'"
					+ model.getYf()
					+ "'"
					+ sb
					+ " from xscjffb where " + pk + "='" + pkV[i] + "' )";
		}
		try {
			dao.runBatch(sqlArr);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * ѧ����𷢷���Ϣ�������
	 * 
	 * @param QgzxForm
	 *            model
	 * @return boolean
	 * */
	public boolean xscjffAudiBatch(QgzxForm model) {
		boolean flag = true;
		String[] pkV = model.getPkV();
		String pk = "xh||gwdm||sqsj||nd||yf||fflx";
		String[] sqlArr = new String[pkV.length];

		for (int i = 0; i < pkV.length; i++) {
			sqlArr[i] = "update xscjffb set xxsh='" + model.getShjg()
					+ "',shsj='" + GetTime.getNowTime2() + "' where " + pk
					+ "='" + pkV[i] + "'";
		}
		try {
			dao.runBatch(sqlArr);
		} catch (Exception ex) {
			ex.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * ��ѯ������𷢷���Ϣ
	 * 
	 * @param String
	 *            pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryXscjffOne(String pkValue) {
		return dao.selectXscjffOne(pkValue);
	}

	/**
	 * ѧ����𷢷ŵ������
	 * 
	 * @param QgzxForm
	 *            model
	 * @param HttpServletRequest
	 *            request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean xscjffAudiOne(QgzxForm model, HttpServletRequest request)
			throws Exception {
		return StandardOperation.update("xscjffb", new String[] { "xxsh",
				"shsj" },
				new String[] { model.getShjg(), GetTime.getNowTime2() },
				"xh||gwdm||sqsj||nd||yf||fflx", model.getPkValue(), request);
	}

	//
	public void printCjffhzb(QgzxForm model, User user, OutputStream os) {
		WritableWorkbook wwb = null;
		List<HashMap<String, String>> yrdwList = dao.getYrdwList(user);
		String xqmc = dao.getXqmc(model.getQueryequals_xueqi());
		try {
			wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("���ʷ��Ż��ܱ�", 0);
			WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			WritableCellFormat wcfH = ExcelMethods.getWcf(WritableFont.ARIAL,
					10, true, Alignment.CENTRE, VerticalAlignment.CENTRE, true,
					Border.ALL);
			// ������
			ExcelMB ex = new ExcelMB();
			ws.mergeCells(0, 0, 7, 1);
			ex.printToOneCell_multy(ws, model.getQueryequals_xn() + "ѧ��" + xqmc
					+ "�ڹ���ѧ��ʱͳ�ƻ��ܱ�", 0, 0, 20, true, Alignment.CENTRE,
					VerticalAlignment.CENTRE, true, 650, Border.NONE);
			ws.addCell(new Label(0, 2, "�ù�����", wcfH));
			ws.addCell(new Label(1, 2, "�ù���λ", wcfH));
			ws.addCell(new Label(2, 2, "ѧ ��", wcfH));
			ws.addCell(new Label(3, 2, "�� ��", wcfH));
			ws.addCell(new Label(4, 2, "�� ʱ", wcfH));
			ws.addCell(new Label(5, 2, "�� ��", wcfH));
			ws.addCell(new Label(6, 2, Base.YXPZXY_KEY, wcfH));
			ws.addCell(new Label(7, 2, "רҵ��", wcfH));

			int base = 3;
			float gzsjCount = 0;// �ܹ���ʱ��
			float cjjeCount = 0;// �ܳ����
			for (int i = 0; i < yrdwList.size(); i++) {
				model.setYrdwdm(yrdwList.get(i).get("yrdwdm"));// ���˵�λ����
				List<HashMap<String, String>> xscjffList = dao
						.getXscjffList(model);
				float bmgzsjCount = 0;// �ܹ���ʱ��
				float bmcjjeCount = 0;// �ܳ����
				for (int j = 0; j < xscjffList.size(); j++) {
					// д��ѧ�������Ϣ
					ws.addCell(new Label(0, base, xscjffList.get(j).get(
							"yrdwmc"), wcf));
					ws.addCell(new Label(1, base,
							xscjffList.get(j).get("gwdm"), wcf));
					ws.addCell(new Label(2, base, xscjffList.get(j).get("xh"),
							wcf));
					ws.addCell(new Label(3, base, xscjffList.get(j).get("xm"),
							wcf));
					ws.addCell(new Label(4, base,
							xscjffList.get(j).get("gzsj"), wcf));
					ws.addCell(new Label(5, base,
							xscjffList.get(j).get("cjje"), wcf));
					ws.addCell(new Label(6, base,
							xscjffList.get(j).get("xymc"), wcf));
					ws.addCell(new Label(7, base,
							xscjffList.get(j).get("bjmc"), wcf));
					base++;
					bmgzsjCount += Float.parseFloat(StringUtils
							.isNull(xscjffList.get(j).get("gzsj")) ? "0"
							: xscjffList.get(j).get("gzsj"));
					bmcjjeCount += Float.parseFloat(StringUtils
							.isNull(xscjffList.get(j).get("cjje")) ? "0"
							: xscjffList.get(j).get("cjje"));
				}
				if (xscjffList == null || xscjffList.size() < 1) {
					ws.addCell(new Label(0, base,
							yrdwList.get(i).get("yrdwmc"), wcf));
					ws.addCell(new Label(1, base, "", wcf));
					ws.addCell(new Label(2, base, "", wcf));
					ws.addCell(new Label(3, base, "", wcf));
					ws.addCell(new Label(4, base, "", wcf));
					ws.addCell(new Label(5, base, "", wcf));
					ws.addCell(new Label(6, base, "", wcf));
					ws.addCell(new Label(7, base, "", wcf));
					base++;
				}
				// д�뵥λ������Ϣ
				ws.addCell(new Label(0, base, yrdwList.get(i).get("yrdwmc")
						+ "����", wcfH));
				ws.addCell(new Label(1, base, "", wcf));
				ws.addCell(new Label(2, base, "", wcf));
				ws.addCell(new Label(3, base, "", wcf));
				ws.addCell(new Label(4, base, bmgzsjCount + "", wcfH));
				ws.addCell(new Label(5, base, bmcjjeCount + "", wcfH));
				ws.addCell(new Label(6, base, "", wcf));
				ws.addCell(new Label(7, base, "", wcf));
				gzsjCount += bmgzsjCount;
				cjjeCount += bmcjjeCount;
				base++;
			}
			// д���ܻ�����Ϣ
			ws.addCell(new Label(0, base, "�ܻ���", wcfH));
			ws.addCell(new Label(1, base, "", wcf));
			ws.addCell(new Label(2, base, "", wcf));
			ws.addCell(new Label(3, base, "", wcf));
			ws.addCell(new Label(4, base, gzsjCount + "", wcfH));
			ws.addCell(new Label(5, base, cjjeCount + "", wcfH));
			ws.addCell(new Label(6, base, "", wcf));
			ws.addCell(new Label(7, base, "", wcf));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// д�뵽�ͻ���
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	public void printGzqfb(QgzxForm model,OutputStream os) {

		List<String[]> data = dao.getGzffList(model);
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet(Base.YXPZXY_KEY+"����ǩ����", 0);

		try {
			excel.printTitle(ws, Base.YXPZXY_KEY+"����ǩ����", 7, 800);// ����
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.RIGHT, VerticalAlignment.CENTRE,
					Border.ALL);// ��Ԫ����ʽ

			ws.mergeCells(0, 1, 6, 1);
			ws.addCell(new Label(0, 1, "���ڣ�"+GetTime.getNowTime(), wcfTytle));

			wcfTytle = ExcelMB.getWritableCellFormat(8, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
			ws.addCell(new Label(0, 2, "���", wcfTytle));
			ws.addCell(new Label(1, 2, "ϵ������", wcfTytle));
			ws.addCell(new Label(2, 2, "��λ", wcfTytle));
			ws.addCell(new Label(3, 2, "����", wcfTytle));
			ws.addCell(new Label(4, 2, "���", wcfTytle));
			ws.addCell(new Label(5, 2, "ǩ��", wcfTytle));
			ws.addCell(new Label(6, 2, "��ע", wcfTytle));
			
			if (null != data && data.size() > 0){
				for (int i = 0 ; i < data.size() ; i++){
					ws.addCell(new Label(0, 3+i, String.valueOf(i+1), wcfTytle));
					
					for (int j = 0 ; j < data.get(i).length ; j++){
						ws.addCell(new Label(1+j, 3+i, data.get(i)[j], wcfTytle));
					}
					
					ws.addCell(new Label(5, 3+i, "", wcfTytle));
					ws.addCell(new Label(6, 3+i, "", wcfTytle));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
}
