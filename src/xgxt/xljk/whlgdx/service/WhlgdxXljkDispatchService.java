package xgxt.xljk.whlgdx.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.utils.New_Random_ID;
import xgxt.utils.String.StringUtils;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;
import xgxt.xljk.whlgdx.form.Xljk_XskhdxwhForm;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description: �人����ѧ������Action
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
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2008-10-08
 * </p>
 */
public class WhlgdxXljkDispatchService {
	/** New Service */
	private static final WhlgdxXljkDispatchService service = new WhlgdxXljkDispatchService();

	/** New DAO */
	private static DAO comDao = DAO.getInstance();

	/** Get Instrance */
	public static WhlgdxXljkDispatchService getInstance() {
		return service;
	}

	private WhlgdxXljkDispatchService() {
	}

	private static final String[] nullArray = {};

	/** save or modi pre data */
	public boolean saveCommonInfo(Xljk_XskhdxwhForm myForm, String realTable,
			String pk) throws Exception {
		this.setCommonVar(myForm);
		formToGBK(myForm);
		boolean flag = true;
		String value = myForm.getPkVal(); // �õ�����ֵ�������� �޸ı����ʱ����
		myForm.setXn_id(new New_Random_ID().new_xnid(realTable));// ��������ֵ
		String[] columns = FormUtils.chgArrayElem2LowerCase(comDao
				.getColumnName(SQL_Util.getNoResultSql(realTable)));
		columns = FormUtils.removeArrayElem(columns, FormUtils
				.getDefaultColumns(realTable));
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

	/** ���渨��Ա��Ϣ */
	public boolean saveFdyInfo(Xljk_XskhdxwhForm myForm, String realTable,
			String pk) throws Exception {
		this.setCommonVar(myForm);
		boolean flag = true;
		String value = myForm.getPkVal(); // �õ�����ֵ�������� �޸ı����ʱ����
		String[] columns = FormUtils.chgArrayElem2LowerCase(comDao
				.getColumnName(SQL_Util.getNoResultSql(realTable)));
		columns = FormUtils.removeArrayElem(columns, FormUtils
				.getDefaultColumns(realTable));
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

	public void getCommonAllInfo(Xljk_XskhdxwhForm myForm, String tableName,
			String pk) throws Exception {
		String[] columnNames = FormUtils.chgArrayElem2LowerCase(comDao
				.getColumnName(SQL_Util.getNoResultSql(tableName)));
		String[] rs = comDao.getOneRs(SQL_Util.getQuerySqlByPKParam(tableName, pk),
				new String[] { myForm.getPkVal() }, columnNames);
		FormUtils.setProperties(columnNames, rs, myForm);
	}

	/** <font color='red'>get List List<HashMap<String,String>> </font> */
	public List<HashMap<String, String>> getCommonList(
			Xljk_XskhdxwhForm myForm, String tableName, String[] condiColumns,
			String[] outputValues) throws Exception {
		myForm.setColumnCN(comDao.arrayToList(outputValues, comDao
				.getColumnNameCN(outputValues, tableName))); // ���ض�Ӧ������ע��
		return comDao.getList(FormUtils.getNormalCondiSql(tableName,
				condiColumns, FormUtils.getProperties(condiColumns, myForm),
				outputValues), nullArray, outputValues);
	}

	/** <font color='red'>get List List<String[]> </font> */
	public List<String[]> getCommonVector(Xljk_XskhdxwhForm myForm,
			String tableName, String[] condiColumns, String[] outputValues)
			throws Exception {
		myForm.setColumnCN(comDao.arrayToList(outputValues, comDao
				.getColumnNameCN(outputValues, tableName))); // ���ض�Ӧ������ע��
		return comDao.rsToVator3(FormUtils.getNormalCondiSql(tableName,
				condiColumns, FormUtils.getProperties(condiColumns, myForm),
				outputValues), nullArray, outputValues);
	}

	public boolean batchDelRecord(String pk, String realTable, String pkColumn)
			throws Exception {
		String[] pkArray = pk.split("!!splitOne!!"); // split�ķ�ʽ ������jspҳ��
		return comDao.runUpdate(SQL_Util.getDelSqlWith_InParam(realTable,
				pkColumn, pkArray.length), pkArray);
	}

	/** <font color='red'>�����������ݵ��������ݣ������ֶ�Ϊȫ���ֶ� </font> */
	public List<String[]> getCommonVector_Exp(Xljk_XskhdxwhForm myForm,
			String tableName, String userType) throws Exception {
		String[] columnNames = FormUtils.chgArrayElem2LowerCase(comDao
				.getColumnName(SQL_Util.getNoResultSql(tableName)));
		//��������  ѧ�� /  ְ���� / ѧԺ���� /������ / �����
		String[] columns = new String[] { "xn", "zgh", "xydm","zcr","hdrq"};
		return comDao.rsToVator3(
				SQL_Util.getQuerySqlUseValue(tableName, columns, FormUtils
						.getProperties(columns, myForm), columnNames),
						nullArray, columnNames);
	}

	/** �������� */
	public void getCommonExp(Xljk_XskhdxwhForm myForm,
			HttpServletResponse response, HttpServletRequest request,
			String tableName, String userType) throws Exception {
		ArrayList<Object> rs = new ArrayList<Object>();
		if(StringUtils.isNotNull(request.getParameter("seeFdyInfo"))){
			//���ѧԺ��ѧУ�û���������Ա������¼ʱҪ��xydm����Ϊ�գ�
			//��xljk_fdygzjlb��û��xydm�ֶ�
			myForm.setXydm("");
		}
		rs.addAll(this.getCommonVector_Exp(myForm, tableName, userType));
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = comDao.getColumnNameCN(comDao
				.getColumnName(SQL_Util.getNoResultSql(tableName)), tableName);
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
	}

	/**
	 * �����б� ȫ��ɨ�裬û���κ����� <br>
	 * For Example : <font color='red'>select
	 * outputValues[0],outputValues[1]..from TABLENAME</font>
	 * 
	 * @param tableName
	 *            ��ͼ��
	 * @param outputValues
	 *            Ҫ��ѯ���ֶ�
	 * @return
	 */
	public List<HashMap<String, String>> getSpecialDisList(String tableName,
			String[] outputValues) {
		return comDao.getList(FormUtils.getNormalCondiSql_Distin(tableName,
				nullArray, nullArray, outputValues), nullArray, outputValues);
	}
	
	/**
	 * For Example : <font color='red'>select
	 * outputValues[0],outputValues[1]..from TABLENAME<br> 
	 * where columns[0]= values[0] and ...</font>
	 * @param tableName
	 * @param columns
	 * @param values
	 * @param outputValues
	 * @return
	 */
	public List<HashMap<String, String>> getSpecialDisList(String tableName,
			String[] columns,String[] values,String[] outputValues) {
		return comDao.getList(FormUtils.getNormalCondiSql_Distin(tableName,
				columns, values, outputValues), nullArray, outputValues);
	}
	
	/**
	 * ֻ�Ƿ���һ����¼���ʸ��ֶ���Ϣ
	 * 
	 * @param tableName
	 * @param outputValue
	 * @return
	 */
	public String getOneStr(String tableName, String pk, String pkValue,
			String[] outputValue) {
		String[] oneRs = comDao.getOneRs(FormUtils.getNormalCondiSql(tableName,
				new String[] { pk }, new String[] { pkValue }, outputValue),
				nullArray, outputValue);
		if (oneRs == null) {
			return "";
		}
		return oneRs[0];
	}

	/** private method */
	private void setCommonVar(Xljk_XskhdxwhForm myForm) {
		myForm.setXn(Base.currXn); // ѧ��
		myForm.setNd(Base.currNd); // ���
	}
	public void formToGBK(Object model) throws IllegalArgumentException,
	SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
//   ��form����model������String���͵�ֵת��һ�η���ҳ��
	Class myClass = model.getClass();
	Method[] methods=myClass.getDeclaredMethods();
	for(int i = 0 ;i<methods.length;i++){
		String methodOne = methods[i].getName();
		String methodType = methods[i].getReturnType().getName();
	if(methodOne.length()>3&&methodOne.substring(0, 3).equalsIgnoreCase("get")&&methodType.equalsIgnoreCase("java.lang.String")){
		String setMethod    = "set"+methodOne.substring(3);
		String newValue = DealString.toGBK((String) myClass.getMethod(methodOne,(Class[])null).invoke(model,(Object[]) null));
		myClass.getMethod(setMethod, new Class[]{String.class}).invoke(model, newValue);
	}
}
}	
}
