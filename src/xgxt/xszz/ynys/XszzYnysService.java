package xgxt.xszz.ynys;

/**
 * Title: ѧ����������ϵͳ
 * Description: ��������ѧԺѧ������Service
 * Copyright: Copyright (c) 2008
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2008��10��9��
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.utils.String.StringUtils;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;

public class XszzYnysService {
	private static DAO comDao = DAO.getInstance();
	
	XszzYnysDAO dao = null;

	private static XszzYnysService service = new XszzYnysService();

	public static XszzYnysService getInstance() {
		return service;
	}

	private XszzYnysService() {
	}

	private static final String[] nullArray = {};

	/** save or modi pre data */
	public boolean saveCommonInfo(XszzYnysActionForm myForm, String realTable,
			String pk) throws Exception {
		this.setCommonVar(myForm);
		boolean flag = true;
		String value = myForm.getNd() + myForm.getXh(); // nd||xh
		// �õ�����ֵ���������޸ı����ʱ����
		String[] columns = FormUtils.chgArrayElem2LowerCase(comDao
				.getColumnName(SQL_Util.getNoResultSql(realTable)));
		String[] removeColumns = new String[] { "sqsj", "xysh", "xxsh" }; // ����ʱ��
		// ѧԺ���
		// ѧУ���
		// Ĭ��ֵ
		if (FormUtils.haveOneRecord(realTable, pk, value)) {// �Ѿ��м�¼���� delete
			removeColumns = new String[] { "sqsj" }; // ѧУ��˺�ѧԺ�����Ҫ����ҳ�洫ֵ,��������ΪĬ��ֵ
			flag = comDao.runUpdate(SQL_Util.getDelSqlByPKParam(realTable, pk),
					new String[] { value });
		}
		columns = FormUtils.removeArrayElem(columns, removeColumns); // Remove
		// Elements
		if (flag) {
			flag = comDao.runUpdate(FormUtils.getInsertSql(realTable, columns),
					FormUtils.getProperties(columns, myForm));
		}
		return flag;
	}

	public void getCommonAllInfo(XszzYnysActionForm myForm, String tableName,
			String pk) throws Exception {
		String[] columnNames = FormUtils.chgArrayElem2LowerCase(comDao
				.getColumnName(SQL_Util.getNoResultSql(tableName)));
		String[] rs = comDao.getOneRs(SQL_Util.getQuerySqlByPKParam(tableName,
				pk), new String[] { myForm.getPkVal() }, columnNames);
		FormUtils.setProperties(columnNames, rs, myForm);
	}

	/** <font color='red'>���������������ݼ�</font> */
	public List<HashMap<String, String>> getCommonList(
			XszzYnysActionForm myForm, String tableName, String[] condiColumns,
			String[] outputValues) throws Exception {
		myForm.setColumnCN(comDao.arrayToList(outputValues, comDao
				.getColumnNameCN(outputValues, tableName))); // ���ض�Ӧ������ע��
		return comDao.getList(FormUtils.getNormalCondiSql(tableName,
				condiColumns, FormUtils.getProperties(condiColumns, myForm),
				outputValues), nullArray, outputValues);
	}

	/**
	 * <font color='red'>List<String[]> ��������ʾ��ʱ��ȽϷ��㣬����Ҫ��һ���ֶ���ʾ����Ҫ����jspҳ��</font>
	 * 
	 * @param myForm
	 * @param tableName
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCommonVector(XszzYnysActionForm myForm,
			String tableName, String userType) throws Exception {
		String[] columns = null;
		String[] outputValues = null;
		String shlb = this.getUserTypeShZd(userType);
		if (StringUtils.isNotNull(myForm.getSqms())) { // �����������ģʽ
			columns = new String[] { "xn", "nj", "xydm", "zydm", "bjdm", "xh",
					"xm", "nd", "xysh", "xxsh" };
			outputValues = new String[] { "nd", "xh", "nj", "xm", "xb", "xymc",
					"zymc", "bjmc", "sqsj", "xysh", "xxsh" };
			// ���ض�Ӧ������ע��
			myForm.setColumnCN(comDao.arrayToList(outputValues, comDao
					.getColumnNameCN(outputValues, tableName)));
			return comDao.rsToVator3(FormUtils.getNormalCondiSql(tableName,
					columns, FormUtils.getProperties(columns, myForm),
					outputValues), nullArray, outputValues);
		} else { // ���ģʽ
			// ����˼���Ҫ���������
			FormUtils.setOneProperty(shlb, myForm.getShlb(), myForm); // ����˵�ֵ�ŵ�myForm��
			columns = new String[] { "xn", "nj", "xydm", "zydm", "bjdm", "xh",
					"xm", "nd", shlb };
			outputValues = new String[] { "nd", "xh", "nj", "xm", "xb", "xymc",
					"zymc", "bjmc", "sqsj", shlb };
			myForm.setColumnCN(comDao.arrayToList(outputValues, comDao
					.getColumnNameCN(outputValues, tableName)));
			return comDao.rsToVator3(
					// ������� ����ɫ����
					// FormUtils.get_SH_NormalCondiSql
					SQL_Util
							.get_SH2_CondiSql(tableName, columns, FormUtils
									.getProperties(columns, myForm),
									outputValues, shlb), nullArray,
					FormUtils.arrayCopy(outputValues,
							new String[] { "bgcolor" }));
		}
	}

	/** <font color='red'>�����������ݵ��������ݣ������ֶ�Ϊȫ���ֶ� </font> */
	public List<String[]> getCommonVector_Exp(XszzYnysActionForm myForm,
			String tableName, String userType) throws Exception {
		String shlb = this.getUserTypeShZd(userType);
		FormUtils.setOneProperty(shlb, myForm.getShlb(), myForm); // ����˵�ֵ�ŵ�myForm��
		String[] columnNames = FormUtils.chgArrayElem2LowerCase(comDao
				.getColumnName(SQL_Util.getNoResultSql(tableName)));
		String[] columns = new String[] { "xn", "nj", "xydm", "zydm", "bjdm",
				"xh", "xm", "nd", shlb };
		return comDao.rsToVator3(FormUtils.get_SH_NormalCondiSql(tableName,
				columns, FormUtils.getProperties(columns, myForm), null, shlb),
				nullArray, columnNames);
	}

	/** ����ɾ�� */
	public boolean batchDelRecord(String pk, String realTable, String pkColumn)
			throws Exception {
		String[] pkArray = pk.split("!!splitOne!!"); // split�ķ�ʽ ������jspҳ��
		return comDao.runUpdate(SQL_Util.getDelSqlWith_InParam(realTable,
				pkColumn, pkArray.length), pkArray);
	}

	/** ����Ψһ�����ݼ� ȫ��ɨ�� */
	public List<HashMap<String, String>> getSpecialDisList(String tableName,
			String[] outputValues) {
		return comDao.getList(FormUtils.getNormalCondiSql_Distin(tableName,
				nullArray, nullArray, outputValues), nullArray, outputValues);
	}

	/** ����ѧУ������ */
	public String getXXmc() {
		return comDao.getOneRs("select xxmc from dmk_xx where xxdm=?",
				new String[] { comDao.getXxdm() }, new String[] { "xxmc" })[0];
	}

	/** �������� */
	public void getCommonExp(XszzYnysActionForm myForm,
			HttpServletResponse response, HttpServletRequest request,
			String tableName, String userType) throws Exception {
		ArrayList<Object> rs = new ArrayList<Object>();
		rs.addAll(this.getCommonVector_Exp(myForm, tableName, userType));
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = comDao.getColumnNameCN(comDao
				.getColumnName(SQL_Util.getNoResultSql(tableName)), tableName);
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/** ����������Ϣ �����ѧ������Null */
	public List<HashMap<String, String>> getCheckList(String userType, int type) {
		if (!userType.equalsIgnoreCase("stu")) { // ����ѧ���û�
			return comDao.getChkList(type);
		}
		return null;
	}

	/** private method */
	private void setCommonVar(XszzYnysActionForm myForm) {
		myForm.setXn(Base.currXn); // ѧ��
		myForm.setNd(Base.currNd); // ���
		// myForm.setXq(Base.currXq); //ѧ��
	}

	/**
	 * �����û����������ֶ� For Example : <font color='red'>���磬ѧԺ�û�Ϊxysh ѧУ�û�Ϊxxsh</font>
	 */
	private String getUserTypeShZd(String userType) {
		if (userType.equals("xx") || userType.equals("admin")) {
			return "xxsh";
		} else if (userType.equals("xy")) {
			return "xysh";
		}
		return "xysh";
	}
	
	/**
	 * ��ȡѧ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStu(String xh) throws Exception {
		dao = new XszzYnysDAO();
		return dao.getStu(xh);
	}
	
	/**
	 * ��ȡѧ�ѻ�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhjxx(String pkVal) throws Exception {
		dao = new XszzYnysDAO();
		return dao.getXfhjxx(pkVal);
	}
	
	/**
	 * �õ�ѧ�ѻ�������Ȩ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getXfhjSqQx(String sUserType,String userDep,String xh,String nd) throws Exception {
		dao = new XszzYnysDAO();
		return dao.getXfhjSqQx(sUserType,userDep,xh,nd);
	}
	
	/**
	 * ����ѧ�ѻ���������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjSqxx(XfhjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzYnysDAO();
		return dao.saveXfhjSqxx(model, request);
	}
	
	/**
	 * �õ�ѧ�ѻ���������ӡ��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXfhjSqb(XfhjModel model,HttpServletRequest request) throws Exception {
		dao = new XszzYnysDAO();
		return dao.getXfhjSqb(model,request);
	}
	
	/**
	 * ɾ��ѧ�ѻ�����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void delXfhjxx(String cbVal, HttpServletRequest request)
			throws Exception {
		dao = new XszzYnysDAO();
		dao.delXfhjxx(cbVal, request);
	}
	
	/**
	 * �����޸�ѧ�ѻ�����˽��
	 * 
	 * @return
	 * @throws Exception
	 */
	public void modXfhjxx(String cbVal, String shjg, HttpServletRequest request)
			throws Exception {
		dao = new XszzYnysDAO();
		dao.modXfhjxx(cbVal, shjg, request);
	}
	
	/**
	 * ѧ�ѻ��ɲ�ѯ��ͷ xfhjtit ---- ѧ�ѻ��ɲ�ѯ��ͷ
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXfhjTit() throws Exception {
		dao = new XszzYnysDAO();
		return dao.getXfhjTit();
	}
	
	/**
	 * ѧ�ѻ��ɲ�ѯ��� xfhjres ---- ѧ�ѻ���
	 * 
	 * @param queryModel,request
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXfhjRes(QueryModel queryModel,HttpServletRequest request)
			throws Exception {
		dao = new XszzYnysDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("go".equalsIgnoreCase(queryModel.getGo())) {
			resList = dao.getXfhjRes(queryModel,request);
		}
		return resList;
	}
	
	/**
	 * ѧ�ѻ��ɵ��� xfhjExp ---- ѧ�ѻ��ɵ���
	 * 
	 * @param iType
	 *            ����iType ����ȡ��ͬ�ı�ͷ
	 * @return
	 * @throws Exception
	 */
	public void getXfhjExp(QueryModel queryModel,
			HttpServletResponse response,HttpServletRequest request) throws Exception {
		dao = new XszzYnysDAO();
		ArrayList<Object> rs = new ArrayList<Object>();

		rs = dao.getExpDate(queryModel, "view_xszz_ynys_xfhj",request);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getExpTit("view_xszz_ynys_xfhj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}
	
	/**
	 * ����ѧ�ѻ��������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjShxx(XfhjModel model,
			HttpServletRequest request) throws Exception {
		dao = new XszzYnysDAO();
		return dao.saveXfhjShxx(model, request);
	}
	
	/**
	 * ��ȡ�������ȼ��б�
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getKnsdjList(){
		dao = new XszzYnysDAO();
		return dao.selectKnsdjList();
	}
}
