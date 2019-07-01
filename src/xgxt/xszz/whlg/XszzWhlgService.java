package xgxt.xszz.whlg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;

/**
 * Title: ѧ����������ϵͳ Description: �人����ѧѧ������Service Copyright: Copyright (c) 2008
 * Company: zfsoft Author: ���� Version: 1.0 Time: 2008-09-09
 */
public class XszzWhlgService {

	XszzWhlgDAO dao = null;// ���ݲ���DAO

	DAO comDao = new DAO();

	private static XszzWhlgService service = new XszzWhlgService();

	public static XszzWhlgService getInstance() {
		return service;
	}

	/**
	 * ɾ����ͥ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delJtqkxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzWhlgDAO();
		dao.delJtqkxx(cbVal, request);
	}

	/**
	 * ��ͥ���������ѯ��ͷ jt qkdctit ---- ��ͥ���������ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJtqkdcTit() throws Exception {
		dao = new XszzWhlgDAO();
		return dao.getJtqkdcTit();
	}

	/**
	 * ��ͥ��������ѯ��� jtqkdcres ---- ��ͥ���������
	 * 
	 * @param queryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJtqkdcRes(QueryModel queryModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzWhlgDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getJtqkdcRes(queryModel, request);
		}
		return resList;
	}

	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzWhlgDAO();
		return dao.getStu(xh);
	}

	/**
	 * ��ȡ��ͥ���������ϸ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJtqkdcxx(String pkVal) throws Exception {
		dao = new XszzWhlgDAO();
		return dao.getJtqkdcxx(pkVal);
	}

	/**
	 * �����ͥ���������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveJtqkdcxx(JtqkdcModel jtqkdcModel,
			HttpServletRequest request) throws Exception {
		dao = new XszzWhlgDAO();
		return dao.saveJtqkdcxx(jtqkdcModel, request);
	}

	/**
	 * ��ͥ������鵼�� jtqkdcExp ---- ��ͥ������鵼��
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getJtqkdcExp(QueryModel queryZxxsdkModel,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		dao = new XszzWhlgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();
		rs = dao
				.getExpDate(queryZxxsdkModel, "view_xszz_whlg_jtqkdcb", request);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_whlg_jtqkdcb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ####################################�人����ѧ ������
	 * #######################################
	 */
	/**
	 * ����ѧ����������������Ϣ ֻ�в����ֶ��ǿ�����ѧ���Լ�ά����
	 */
	// public boolean saveStuKnsSqInfo(XszzWhlgdxActionForm myForm,String
	// realTable,String pk) throws Exception{
	// myForm.setXn(Base.currXn); //����ѧ��
	// String[] columns = new
	// String[]{"xh","xn","jtrjnsr","ghlmkh","zxlxdh","is_shbzx","is_shjp","is_lszn",
	// "is_stcj","is_zrzh","is_pysq","is_dq","is_dznsx","is_fmxg","is_qtys"};
	// if(1== 2){ //�ж��Ƿ��Ѿ�������ͨ��
	//			
	// }
	// String value = myForm.getXh() + myForm.getXn();
	// if(FormUtils.haveOneRecord(realTable, pk,value)){//�Ѿ��м�¼���� delete
	// if(comDao.runUpdate(SQL_Util.getDelSqlWith_PK(realTable, pk), new
	// String[]{value})){ //delete
	// return comDao.runUpdate(FormUtils.getInsertSql(realTable,
	// columns),FormUtils.getProperties(columns,myForm));//insert
	// }
	// return false;
	// }else{ //insert
	// return comDao.runUpdate(FormUtils.getInsertSql(realTable,
	// columns),FormUtils.getProperties(columns,myForm));
	// }
	// }
	/**
	 * @param pk������ֵ����
	 *            ��split��!!splitOne!!���������ӿͻ��˻�ȡ
	 * @param realTable
	 *            ��
	 * @param pkColumn
	 *            ������
	 * @return
	 * @throws Exception
	 */
	public boolean batchDelRecord(String pk, String realTable, String pkColumn)
			throws Exception {
		String[] pkArray = pk.split("!!splitOne!!"); // split�ķ�ʽ ������jspҳ��
		return comDao.runUpdate(SQL_Util.getDelSqlWith_InParam(realTable,
				pkColumn, pkArray.length), pkArray);
	}

	/**
	 * ���������ֶ�����
	 * 
	 * @param tableName
	 *            �����ͼ��
	 * @param outputValues
	 *            �ֶ�����
	 * @return
	 */
	public List<HashMap<String, String>> getColumnCN(String tableName,
			String[] outputValues) {
		return comDao.arrayToList(outputValues, comDao.getColumnNameCN(
				outputValues, tableName));
	}

	/**
	 * �Ƿ���ƶ����
	 * 
	 * @param xh
	 * @return
	 */
	public boolean isPks(String xh) {
		return comDao.isKns(xh);
	}

	/**
	 * ���������ʾ��Ϣ �˷����޷����� �����ض����������ò�����
	 * 
	 * @param userName
	 * @param userOnLine
	 * @return
	 */
	public String getSfTsxx(String userName, String userOnLine,
			XszzWhlgdxActionForm myForm, String tableName, String pk)
			throws Exception {
		String tsxx = "";
		if (userOnLine.equals("student")) {
			// if(!this.isPks(userName)){
			// tsxx = "�Բ���������ƶ��������������";
			// }
			myForm.setPkVal(userName + Base.currXn); // Form �趨pkValue
			// ���ڲ�ѯΨһ��һ������
			this.getCommon_PreStuAllInfo(myForm, tableName, pk);// ����ĳ��ѧ����������¼
		} else if (userOnLine.endsWith("teacher")) {
			// if teacher then do data .................
		}
		return tsxx;
	}

	/**
	 * �����ݵ���
	 * 
	 * @param queryModel
	 * @param response
	 * @param request
	 * @param tableName
	 *            �����ͼ��
	 * @throws Exception
	 */
	public void getCommonExp(QueryModel queryModel,
			HttpServletResponse response, HttpServletRequest request,
			String tableName) throws Exception {
		dao = new XszzWhlgDAO();
		ArrayList<Object> rs = new ArrayList<Object>();
		rs = dao.getExpDate(queryModel, tableName, request);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit(tableName);
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * ####################################�人����ѧ ������
	 * #######################################
	 */
	/**
	 * ����ѧ���Ĺ�����ѧ����������Ϣ ������ʱ��sqsjȥ��
	 * 
	 * @param myForm
	 * @param realTable
	 * @return
	 * @throws Exception
	 */
	public boolean saveCommonStuInfo(XszzWhlgdxActionForm myForm,
			String realTable, String pk) throws Exception {
		myForm.setXn(Base.currXn); // ����ѧ��
		String value = myForm.getXh() + myForm.getXn(); // ����ֵ

		/** ����������Ŀ ������Ϊxh||nd BEGIN */
		if (pk.contains("nd")) {
			myForm.setNd(Base.currNd);
			value = myForm.getXh() + myForm.getNd();
		}
		/** ����������Ŀ ������Ϊxh||nd END */

		String[] columns = FormUtils.chgArrayElem2LowerCase(comDao
				.getColumnName(SQL_Util.getNoResultSql(realTable)));
		String[] removeColumns = new String[] { "sqsj" };
		columns = FormUtils.removeArrayElem(columns, removeColumns);// remove
		// sqsj
		// column
		// if(1== 2){ //�ж��Ƿ��Ѿ�������ͨ��
		//			
		// }
		boolean flag = true;
		if (FormUtils.haveOneRecord(realTable, pk, value)) {// �Ѿ��м�¼���� delete
			flag = comDao.runUpdate(SQL_Util.getDelSqlByPKParam(realTable, pk),
					new String[] { value });
		}
		if (flag) {
			flag = comDao.runUpdate(FormUtils.getInsertSql(realTable, columns),
					FormUtils.getProperties(columns, myForm));
		}
		return flag;
	}

	/**
	 * ������������ѧ�����б���Ϣ <font color='red'>Ϊ���÷���������������Ҫ����Ӧ</font>
	 * 
	 * @param myForm
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCommonStuList(
			XszzWhlgdxActionForm myForm, String tableName) throws Exception {
		String[] columns = new String[] { "xn", "nj", "xydm", "zydm", "bjdm",
				"xh", "xm", "nd" };
		String[] outputValues = new String[] { "xn", "nj", "xh", "xm", "xb",
				"xymc", "zymc", "bjmc", "sqsj" };
		myForm.setColumnCN(this.getColumnCN(tableName, outputValues)); // ���ض�Ӧ������ע��
		return comDao.getList(FormUtils.getNormalCondiSql(tableName, columns,
				FormUtils.getProperties(columns, myForm), outputValues),
				new String[] {}, outputValues);
	}

	/**
	 * ������������ѧ�����б���Ϣ <font color='red'><br>
	 * �÷�������ʹ����Ƚ����Ի������Ը����Լ�����Ҫ���Ʋ�ͬ�������ʽ</font>
	 * 
	 * @param myForm
	 * @param tableName
	 * @return outputValues ����ҳ������ʾ���ֶ�
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCommonStuList(
			XszzWhlgdxActionForm myForm, String tableName, String[] outputValues)
			throws Exception {
		String[] columns = new String[] { "nj", "xydm", "zydm", "bjdm", "xh",
				"xm", "nd" };
		myForm.setColumnCN(this.getColumnCN(tableName, outputValues)); // ���ض�Ӧ������ע��
		return comDao.getList(FormUtils.getNormalCondiSql(tableName, columns,
				FormUtils.getProperties(columns, myForm), outputValues),
				new String[] {}, outputValues);
	}

	/**
	 * ����һ��ѧ����ȫ����Ϣ
	 * 
	 * @param myForm
	 * @param tableName
	 * @throws Exception
	 */
	public void getCommon_PreStuAllInfo(XszzWhlgdxActionForm myForm,
			String tableName, String pk) throws Exception {
		String[] columnNames = FormUtils.chgArrayElem2LowerCase(comDao
				.getColumnName(SQL_Util.getNoResultSql(tableName))); // toLowerCase
		String[] rs = comDao.getOneRs(SQL_Util.getQuerySqlByPKParam(tableName,
				pk), new String[] { myForm.getPkVal() }, columnNames);
		if (rs != null) {
			FormUtils.setProperties(columnNames, rs, myForm);
		}
	}

	/** ��ʱû���� */
	public void xkxybbSpecialExp(HttpServletRequest request,
			XszzWhlgdxActionForm myForm, HttpServletResponse response) {
		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = Workbook.createWorkbook(response
					.getOutputStream());
			WritableSheet ws = wwb.createSheet("������ѧ������Ϣ", 0);
			WritableFont wf = new WritableFont(WritableFont.TAHOMA); // ���������ʽ
			wf.setBoldStyle(WritableFont.NO_BOLD); // ���������ʽ(����)
			wf.setColour(Colour.getInternalColour(20)); // ���������ʽ(��ɫ)
			wf.setUnderlineStyle(UnderlineStyle.NO_UNDERLINE); // ���������ʽ(���»���)
			wf.setPointSize(10); // ���������ʽ(��С)
			WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
			wcf.setFont(wf); // ����ָ�������ʽ�ĵ�Ԫ���ʽ
			wcf.setAlignment(Alignment.CENTRE); // ָ����ʽ�����ַ����Ҿ���
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // ָ����ʽ�����ַ����¾���
		    wcf.setWrap(true); // ָ����ʽ�����Զ�����
			// String[] columns = new
			// String[]{"��ͬ��","ѧ��","����","�Ա�","���֤��","����","Ժϵ","רҵ","��ѧ���"};
			// ��ҵ��� ��ͥ��ϸסַ �������� ��ͥ��ϵ�绰 �������� ���׹�����λ ������ϵ�绰 ĸ������ ĸ�׹�����λ ĸ����ϵ�绰
			// �����ܽ�� �������ޣ��£� ѧ�ӷѴ����ܶ� ס�޷Ѵ����ܶ� ��ȷſ��� У԰һ��ͨ�� �������������� ���ѧ��ʵ�ʻ�ô�����
			// �Ƿ񱨿����о��� ��ҵȥ�� ��Ч��ϵ��ʽ���ƶ��绰�� ����e-mail ������λ ��λ��ַ ��λ�ʱ� ��λ�绰}
			// "htbh"
			String[] columns = new String[] { "nj", "xydm", "zydm", "bjdm",
					"xh", "xm", "xn" };
			String[] outputValues = new String[] { "xh", "xm", "sfzh", "mzmc",
					"xymc", "zymc", "rxnf" };
			String tableName = "view_xszz_whlgdx_gjzxdk_sp";
			String[] columnsCN = comDao
					.getColumnNameCN(outputValues, tableName);
			for (int i = 0; i < outputValues.length; i++) {
				ws.setColumnView(i, 20);
				ws.setRowView(i, 15 * 20);
			}
			for (int m = 0; m < columnsCN.length; m++) {
				ws.addCell(new Label(m, 0, columnsCN[m], wcf));
			}
			List<HashMap<String, String>> rs = // get list
			comDao.getList(FormUtils.getNormalCondiSql(tableName, columns,
					FormUtils.getProperties(columns, myForm), outputValues),
					new String[] {}, outputValues);
			for (int i = 0; i < rs.size(); i++) {
				for (int j = 0; j < outputValues.length; j++) {
					ws.addCell(new Label(j, i + 1, rs.get(i).get(
							outputValues[j]), wcf));
				}
			}
			// if(i==0){// ����1��2����ʼ����ϲ���Ԫ��
			// ws.mergeCells(0,1,0,Integer.parseInt(sscout_ald));
			// }else{//�����ϴκϲ���Ϊ�������ϲ���Ԫ��
			// ws.mergeCells(0,nextNumV1-Integer.parseInt(sscout_ald_last)+1,0,nextNumV1);
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
