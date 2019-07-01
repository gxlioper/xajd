package xgxt.bdsz;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.zfsoft.basic.BasicService;
import com.zfsoft.database.model.TableModel;


import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.CommonUpdata;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �Զ����Service</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: zfsoft</p>
 * <p>Author: �����</p>
 * <p>Version: 1.0</p>
 *  <p>Time: 2010-05-31</p>
 */
public class BdszService {
	
	BdszDAO dao = new BdszDAO();
	
	/**
	 * ɾ���Զ����ֶ�
	 * 
	 * @throws Exception
	 */
	public boolean delBdsz(String pk, String zdyTable,
			HttpServletRequest request) throws Exception {
		return dao.delBdsz(pk, zdyTable, request);
	}
	
	
	/**
	 * ��ȡ�Զ����ֶ��б�
	 * 
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getBdszList(String tableName, BdszModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		return dao.getBdszList(tableName, model, colList);
	}
	
	
	/**
	 * ���ģ���б�
	 * @param zdyTable 
	 * @return
	 * @deprecated �����ݿ���ȡģ���б���
	 */
	public List<HashMap<String, String>> getMkList(String mkmc) {
		
		return dao.getMkList(mkmc);
	}
	
	
	/**
	 * ���ص�������
	 * @param tableName
	 * @param colList
	 * @param pkCol
	 * @param pk
	 * @return
	 */
	public HashMap<String, String> getBdsz(String tableName, String[] colList, String pkCol, String pk) {
		
		return dao.getBdsz(tableName,colList,pkCol,pk);
	}
	
	
	/**
	 * ���ĳ���ֶ��Ƿ����
	 * @param tabname
	 * @param zdid
	 * @return
	 * @throws SQLException
	 */
	public boolean checkTableCol(String tabname, String zdid)
			throws SQLException {
		
		DAO dao = DAO.getInstance();
		String sql = "select 1 zdcz from user_tab_cols where TABLE_NAME = upper(?) and COLUMN_NAME = upper(?)";
		String[] zdcz = dao.getArray(sql, new String[] { tabname, zdid },
				"zdcz");
		if (null != zdcz  && zdcz.length != 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
	/**
	 * �����Զ����ֶ�
	 * @param model
	 * @param tableName
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveBdsz(BdszModel model, String tableName, String pkValue,
			HttpServletRequest request) throws Exception {
		String pkCol = "zdid||'-'||tabname";
		if ("PY_BDSZB".equalsIgnoreCase(tableName)) {
			pkCol = "zdid||tabname";
		}
		String[] colList = new String[] { "bz", "cxxs", "cxxspx", "mkmc",
				"tabname", "zdcd", "zdid", "zdlx", "zdmc", "zdpx", "sfnum", "sfnull" ,"xzf"};
		boolean update = CommonUpdata.commonUpdata(colList, model, tableName,
				pkCol, pkValue, request);
		if (update) {
			if (pkValue == null || pkValue.equalsIgnoreCase("")) {
				pkValue = model.getZdid() + model.getTabname();
			}

			update = dao.updataOps(model, pkValue);
		}
		return update;
	}
	
	
	/**
	 * �����Զ����ֶ�
	 * @param model
	 * @param pkValue  ����ʱ��������һ����null;
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveBdsz(BdszModel model,String pkValue, HttpServletRequest request) throws Exception {
		
		String tableName = "ty_bdsz";
		String pkCol = "zdid||tabname";
		String[] colList = new String[] { "bz", "cxxs", "cxxspx", "mkmc","sfbt",
				"tabname", "zdcd", "zdid", "zdlx", "zdmc", "zdpx","mkdm" };
		boolean update = CommonUpdata.commonUpdata(colList, model, tableName,pkCol, pkValue, request);
		
		//�޸��Զ����ֶ�(������)��option
		if (update) {
			
			if (Base.isNull(pkValue)) {
				pkValue = model.getZdid()+model.getTabname();
			}
			
			update = dao.updataOps(model, pkValue);
		}
		return update;
	}
	
	
	/**
	 * ��ȡ�Զ��������б�
	 * @param pk
	 * @return
	 */
	public List<HashMap<String, String>> getOpList(String pk) {
		return dao.getOpList(pk);
	}
	
	/**
	 * ��ȡ�Զ����ֶ���Ϣ
	 * @param tableName
	 * @param mkmc
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getZdydz(String tableName, String mkmc){
		return dao.getBdZd(tableName, mkmc);
	}
	
	
	
	
	
	/**
	 * ��ȡ����
	 * @param tableName
	 * @param realTable 
	 * @param myForm
	 */
	public void getDcZd(String tableName, 
			            String realTable, 
			            BdszModel model, 
			            String zdyTable) {
		DAO commondao = DAO.getInstance();
		String[] colList = commondao.getColumnName("select * from "+tableName);
		String[] colListCN = commondao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> list = dao.getBdZd(realTable);
		if("py_bdsz_bcnr".equalsIgnoreCase(zdyTable)){
			list = dao.getBdZd("view_" + realTable, "pjpy");			
		}
		String[] zdyZd = new String[list.size()];
		String[] zdyZdz = new String[list.size()];
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				String zd = map.get("zdid");
				String zdz = map.get("zdmc");
				zdyZd[i] = zd;
				zdyZdz[i] = zdz;
			}
		}
		//���ֶγ���
		String[] colListZCN = new String[colListCN.length+zdyZdz.length];
		for(int i=0;i<colList.length;i++){
				colListZCN[i]=colListCN[i];  
		}
		for(int i=0;i<zdyZd.length;i++){
			colListZCN[colListCN.length+i]=zdyZdz[i];        
		}
		
		model.setArrZdid(StringUtils.joinStrArr(colList,zdyZd));
		model.setArrZdmc(colListZCN);
	}
	
	
	
	/**
	 * ��ȡ����
	 * @param tableName
	 * @param request 
	 * @param queryCol 
	 * @param realTable 
	 * @param strings 
	 * @param myForm
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */

	public String getDcSql(String tableName, String zdyTable, String[] zdyCol,
			String realTable, String query) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getDcSql(tableName, zdyTable, zdyCol, realTable, query);
	}
	
	
	
	/**
	 * ����ʹ���Զ����ֶ�ҵ��ģ��Ĳ�ѯ��ͷ
	 * @param tableName
	 * @param colList
	 * @param zdyCol
	 * @param zdyColCN
	 * @return
	 */
	public List<HashMap<String, String>> getZdyTopTr(String tableName,
			String[] colList, String[] zdyCol, String[] zdyColCN) {
		
		String[] colListCn = dao.getColumnNameCN(colList, tableName);
	
		String[] colJoinListEn = StringUtils.joinStrArr(colList,zdyCol);
		String[] colJoinListCn = StringUtils.joinStrArr(colListCn,zdyColCN);

		return dao.arrayToList(colJoinListEn,colJoinListCn);
	}
	
	
	/**
	 * ���Ӹ���Ա��ѯ���ݷ�Χ���
	 * @param isFdy
	 * @param userName
	 * @return String 
	 * */
	public String appendFdysql(String isFdy, String userName){
		String sql = "";
		if("true".equalsIgnoreCase(isFdy)){
			sql += StringUtils.joinStr(" and exists(select 1 from view_fdybbj b where b.zgh='",
				                       userName,
				                       "' and a.bjdm=b.bjdm)");
		}		
		return sql;
	}
	
	
	
	
	/**
	 * ��ȡ��Ҫ���Զ����ֶ�ά���Ĺ���ģ��
	 * @author qph
	 * @return
	 */
	public List<HashMap<String,String>> getGnmkList(){
		
		return dao.getGnmkList();
	}
	
	
	
	/**
	 * ��ȡ��Ҫ���Զ����ֶ�ά���Ĺ��ܵ�
	 * @author qph
	 * @return
	 */
	public List<HashMap<String,String>> getGnmcList(){
		
		return dao.getGnmcList();
	}
	
	
	
	/**
	 * ɾ���Զ����ֶ�
	 * @author qph
	 * @param pkValues
	 * @return
	 */
	public HashMap<String,Object> delZdyZd(String[] pkValues) {
		
		HashMap<String,Object> map = new HashMap<String, Object>();
		//��ȡ�Զ����ֶ���ҵ��ģ�������
		List<HashMap<String,String>> zdnrList = dao.getZdnrList(pkValues);
		String[] tempArr = new String[zdnrList.size()];
		
		map.put("wscList", zdnrList);
		
		for (int i = 0 ; i < zdnrList.size() ; i++) {
			tempArr[i] = zdnrList.get(i).get("zdid")+zdnrList.get(i).get("tabname");
		}
		
		List<String> pkValueList = new ArrayList<String>(Arrays.asList(pkValues));
		List<String> tempList = Arrays.asList(tempArr);
		
		//�Ƴ���ҵ��ģ��������ݵ�����ֵ
		pkValueList.removeAll(tempList);
		
		try {
			if (null != pkValueList && pkValueList.size() >0) {
				//ɾ���Զ����ֶ�
				boolean result = dao.delZdyZd(pkValueList.toArray(new String[] {}));
				map.put("result", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	
	/**
	 * ������������ȡ�Զ����ֶ����ݵı����
	 * @author qph
	 * @param realTable
	 * @return
	 */
	public String getZdyBcnrb(String realTable) {
		
		String zdyTable = dao.getZdyBcnrb(realTable);
		
		if (Base.isNull(zdyTable)) {
			zdyTable = "ty_bdsz_bcnr";
		}
		
		return zdyTable;
	}
	
	
	
	/**
	 * ���غ��Զ����ֶε��С�Ӣ����
	 * @author qph
	 * @param tableName
	 * @param realTable
	 * @return
	 */
	public HashMap<String, String[]> getZdyZd(String tableName, String realTable) {
		
		//������ͼ�е���
		String[] colListEn = dao.getColumn(tableName);
		//������ͼ���е�������
		String[] colListCn = dao.getColumnCn(colListEn, tableName);
		//�Զ����ֶ��б�
		List<HashMap<String, String>> list = dao.getZdyZd(realTable);
		//����ֵ
		HashMap<String,String[]> map = new HashMap<String, String[]>();
		
		if (null != list && list.size() > 0) {
			//�Զ����ֶε�Ӣ������
			String[] zdyZdEn = new String[list.size()];
			//�Զ����ֶε���������
			String[] zdyZdCn = new String[list.size()];
			//�Զ����ֶ�����
			String[] zdyZdLx = new String[list.size()];
			
			
			for (int i = 0; i < list.size(); i++) {
				zdyZdEn[i] = list.get(i).get("zdid");
				zdyZdCn[i] = list.get(i).get("zdmc");
				zdyZdLx[i] = list.get(i).get("zdlx");
			}
			
			//���������Զ���ƴ��
			String[] colJoinListCn = StringUtils.joinStrArr(colListCn,zdyZdCn);
			String[] colJoinListEn = StringUtils.joinStrArr(colListEn,zdyZdEn);
			
			map.put("zdyZdLx", zdyZdLx);
			map.put("zdyZdEn", zdyZdEn);
			map.put("colListCn", colJoinListCn);
			map.put("colListEn", colJoinListEn);
			
		} else {
			map.put("zdyZdLx", new String[] {}); 
			map.put("zdyZdEn", new String[] {}); 
			map.put("colListCn", colListCn);
			map.put("colListEn", colListEn);
		}

		return map;
	}
	
	
	
	/**
	 * ���ز�ѯSQL
	 * @author qph
	 * @param realTable
	 * @param tableName
	 * @param zdyCol
	 * @param query
	 * @return
	 */
	public String getQueryDataSql(String realTable, String tableName,
			String[] zdyCol, String[] zdlx, String query) {

		String zdyTable = getZdyBcnrb(realTable);//�Զ����ֶ����ݱ����
		String key = getPkKey(realTable,"a.");

		return dao.getQueryDataSql(realTable, key, tableName,
				zdyTable, zdyCol, zdlx, query);
	}


	
	/**
	 * ��ȡ������
	 * @author qph
	 * @param realTable
	 * @return
	 */
	private String getPkKey(String realTable, String rName) {
		// ʵ����table����
		TableModel table = new BasicService().getTable(realTable);
		// ��ȡ��������
		String[] pkKey = table.getPrimaryKey().split(",");
		StringBuilder key = new StringBuilder();

		if (null != pkKey && pkKey.length > 0) {

			for (int i = 0; i < pkKey.length; i++) {
				key.append(rName);
				key.append(pkKey[i]);

				if (i != pkKey.length - 1) {
					key.append("||");
				}
			}

		}
		return key.toString();
	}
	
	
	
	/**
	 * ������������ѯ���Զ����ֶ�
	 * @author qph
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String,String[]> getZdyCol(String realTable)
			throws SQLException {

		HashMap<String,String[]> map = new HashMap<String, String[]>();
		List<HashMap<String, String>> zdyColList = dao.zdyColList(realTable);
		
		if (null != zdyColList && zdyColList.size() > 0) {
			
			String[] zdyZdEn = new String[zdyColList.size()];
			String[] zdyZdCn = new String[zdyColList.size()];
			String[] zdyZdLx = new String[zdyColList.size()];
			
			for (int i = 0 ; i < zdyColList.size(); i++) {
				zdyZdEn[i] =  "zdid_"+zdyColList.get(i).get("zdid");
				zdyZdCn[i] = zdyColList.get(i).get("zdmc");
				zdyZdLx[i] = zdyColList.get(i).get("zdlx");
			}
			
			map.put("zdyZdEn", zdyZdEn);
			map.put("zdyZdCn", zdyZdCn);
			map.put("zdyZdLx", zdyZdLx);
		} else {
			map.put("zdyZdEn", new String[] {});
			map.put("zdyZdCn", new String[] {});
			map.put("zdyZdCn", new String[] {});
		}
		
		return map;
	}
	
	
	
	/**
	 * ʹ���Զ����ֶε�ҵ��ģ�����ݲ�ѯ
	 * @author qph
	 * @param realTable
	 * @param tableName
	 * @param queryList
	 * @param queryLikeList
	 * @param model
	 * @param colList
	 * @param zdyCol
	 * @param zdyZdLx
	 * @return
	 */
	public ArrayList<String[]> getZdyData(String realTable, String tableName,
			String[] queryList, String[] queryLikeList,
			Object model, String[] colList,String[] zdyCol,String[] zdyZdLx) throws Exception{

		//��ȡ��ѯ����
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(queryList, queryLikeList, model);
		String[] inputList = makeQuery.getInputList();
		String query = makeQuery.getQueryString();

		//��ѯSQLƴ��
		String sql = getQueryDataSql(realTable, tableName, zdyCol, zdyZdLx, query);
		
		//Ҫ���ص���
		String[] colJoinList = StringUtils.joinStrArr(colList, zdyCol);
		
		return CommonQueryDAO.commonQuery(sql, "", inputList,colJoinList, model);
	}
	
	
	
	/**
	 * ����������Զ����ֶ�����
	 * @author qph
	 * @return
	 */
	public boolean saveData(String realTable, String pkValue,
							 Object model,HttpServletRequest request) throws Exception {
		
		String zdyTable = getZdyBcnrb(realTable);//�Զ����ֶ����ݱ����
		String[] colList = dao.getColumn(realTable);//�����е���
		//����Ҫ�����ֵ
		String[] inputList = ModelToStrings.modelToStrings(colList, model);
		//������������
		boolean result = StandardOperation.insertNoLog(realTable, colList,inputList);
		
		if (result) {
			//���������ȡ�Զ����ֶ���request�е�ֵ
			List<HashMap<String, String>> zdyZd = getZdyZdValue(realTable, request);
			//�����Զ����ֶ�����
			if (null != zdyZd && zdyZd.size() > 0) {
				result = dao.saveZdyData(realTable, zdyTable, pkValue, zdyZd);
			}
		}
		return result;
	}


	
	/**
	 * ���������ȡ�Զ����ֶ���request�е�ֵ
	 * @author qph
	 * @param realTable
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	private List<HashMap<String, String>> getZdyZdValue(String realTable, HttpServletRequest request) throws SQLException {
		
		List<HashMap<String,String>> zdyColList = dao.getZdyZd(realTable);
		//�Զ����ֶ����� ��-�ֶ�  ֵ-��request���ȡ
		List<HashMap<String,String>> zdyZd = new ArrayList<HashMap<String,String>>();
		
		if (null != zdyColList && zdyColList.size() > 0) {
			
			for (int i = 0 ; i < zdyColList.size(); i++) {
				
				HashMap<String,String> tempMap = new HashMap<String, String>();
				
				tempMap.put("zdid", zdyColList.get(i).get("zdid"));
				tempMap.put("zdValue", request.getParameter(zdyColList.get(i).get("zdid")));
				zdyZd.add(tempMap);
				
			}
			
		} 
		
		return zdyZd;
	}
	
	
	
	/**
	 * �޸Ĳ��������Զ����ֶ�����
	 * @author qph
	 * @param realTable
	 * @param zdyTable
	 * @param newPkValue
	 * @param oldPkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateData(String realTable, String pkValue,String oldPkValue, Object model,
			HttpServletRequest request) throws Exception {

		String zdyTable = getZdyBcnrb(realTable);//�Զ����ֶ����ݱ����
		String pkKey = getPkKey(realTable,"");//����
		String[] colList = dao.getColumn(realTable);//�����е���
		String[] inputList = ModelToStrings.modelToStrings(colList, model);
		
		//�޸������е�����
		boolean result = StandardOperation.updateNolog(realTable, colList,inputList, pkKey, oldPkValue);

		if (result) {
			//���������ȡ�Զ����ֶ���request�е�ֵ
			List<HashMap<String, String>> zdyZd = getZdyZdValue(realTable, request);
			//�޸��Զ����ֶ�����
			if (null != zdyZd && zdyZd.size() > 0) {
				result = dao.updataZdyData(realTable,zdyTable, pkValue,oldPkValue,zdyZd);
			}
		}
		return result;
	}
	

	
	/**
	 * ���ص������Զ����ֶεĽ����
	 * @author qph
	 * @param tableName
	 * @param realTable
	 * @param zdyTable
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> getOneData(String tableName,
			String realTable, String pkValue) {
		
		String zdyTable = getZdyBcnrb(realTable);//�Զ����ֶ����ݱ����
		String pkKey = getPkKey(realTable,"");//����
		String[] colList = dao.getColumn(tableName);//��ͼ�е���
		//����������
		HashMap<String, String> rs = CommonQueryDAO.commonQueryOne(tableName,colList, pkKey, pkValue);
		//�Զ����ֶ�����
		List<HashMap<String,String>> zdyZdData = dao.getZdyDataByOne(realTable, zdyTable, pkValue);
		
		//���������ݺ��Զ����ֶ�����ƴ��һ���������
		if (null != zdyZdData && zdyZdData.size() > 0) {
			
			for (int i = 0 ; i < zdyZdData.size() ; i++) {
				rs.put(zdyZdData.get(i).get("zdid"), zdyZdData.get(i).get("bcnr"));
			}
		}
		
		return rs;
	}
	
	
	
	/**
	 * ɾ��ʹ�����Զ����ֶε�ҵ��ģ�������
	 * @author qph
	 * @param realTable
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean delData(String realTable, String[] pkValue)
			throws Exception {

		String zdyTable = getZdyBcnrb(realTable);//�Զ����ֶ����ݱ����
		String pkKey = getPkKey(realTable,"");//����
		
		return dao.delData(realTable, zdyTable, pkKey, pkValue);
	}


	
	/**
	 * ���Զ����ֶ���Ϣ�󶨵�form��
	 * @param tableName
	 * @param form
	 * @throws Exception 
	 * @deprecated ������� setBdZd
	 * */
	@SuppressWarnings("unchecked")
	public void getBdZd(String tableName,String mkmc,Object form) throws Exception{
		
		Class myClass = form.getClass();
		List<HashMap<String, String>> list = getZdydz(tableName, mkmc);
		HashMap<String, ArrayList<HashMap<String, String>>> opslist = dao.getOps(mkmc,tableName);
		String[] zdyZd = new String[list.size()];
		String[] zdyZdz = new String[list.size()];
		String[] zdyZdlx = new String[list.size()];
		String[] zdyZdcd = new String[list.size()];
		String[] zdySfnum = new String[list.size()];
		String[] zdySfnull = new String[list.size()];
		String[] zdyXzf = new String[list.size()];
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);
				String zd = map.get("zdid");
				String zdz = map.get("zdmc");
				String zdlx = map.get("zdlx");
				String zdcd = map.get("zdcd");
				String sfnum = map.get("sfnum");
				String sfnull = map.get("sfnull");
				String xzf = map.get("xzf");
				zdyZd[i] = zd;
				zdyZdz[i] = zdz;
				zdyZdlx[i] = zdlx;
				zdyZdcd[i] = zdcd;
				zdySfnum[i] = sfnum;
				zdySfnull[i] = sfnull;
				zdyXzf[i] = xzf;
			}
		}
		myClass.getMethod("setArrZdid", new Class[] { String[].class}).invoke(form, new Object[]{zdyZd});
		myClass.getMethod("setArrZdmc",new Class[] { String[].class }).invoke(form, new Object[]{zdyZdz});
		myClass.getMethod("setArrZdlx",new Class[] { String[].class }).invoke(form, new Object[]{zdyZdlx});
		myClass.getMethod("setArrZdcd",new Class[] { String[].class }).invoke(form, new Object[]{zdyZdcd});
		myClass.getMethod("setOpslist",new Class[] { HashMap.class }).invoke(form, opslist);
		myClass.getMethod("setArrSfnum",new Class[] { String[].class }).invoke(form, new Object[]{zdySfnum});
		myClass.getMethod("setArrSfbt",new Class[] { String[].class }).invoke(form, new Object[]{zdySfnull});
		myClass.getMethod("setArrXzf",new Class[] { String[].class }).invoke(form, new Object[]{zdyXzf});
	}
	
	
	/**
	 * �����Զ����ֶε���
	 * @param realTable
	 * @param model
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void setBdZd(String realTable,Object model) throws Exception{
		
		Class myClass = model.getClass();
		List<HashMap<String, String>> list = dao.getZdyZd(realTable);
		HashMap<String, ArrayList<HashMap<String, String>>> opslist = dao.getZdyOptions(realTable);
		
		if (null != list && list.size() > 0) {
				
			String[] zdyZdid = new String[list.size()];
			String[] zdyZdmc = new String[list.size()];
			String[] zdyZdlx = new String[list.size()];
			String[] zdyZdcd = new String[list.size()];
			String[] zdySfbt = new String[list.size()];
			
			for (int i = 0; i < list.size(); i++) {
				
				HashMap<String, String> map = list.get(i);
				
				zdyZdid[i] = map.get("zdid");
				zdyZdmc[i] = map.get("zdmc");
				zdyZdlx[i] = map.get("zdlx");
				zdyZdcd[i] = map.get("zdcd");
				zdySfbt[i] = map.get("sfbt");
				
			}
			
			myClass.getMethod("setArrZdid", new Class[] { String[].class}).invoke(model, new Object[]{zdyZdid});
			myClass.getMethod("setArrZdmc",new Class[] { String[].class }).invoke(model, new Object[]{zdyZdmc});
			myClass.getMethod("setArrZdlx",new Class[] { String[].class }).invoke(model, new Object[]{zdyZdlx});
			myClass.getMethod("setArrZdcd",new Class[] { String[].class }).invoke(model, new Object[]{zdyZdcd});
			myClass.getMethod("setArrSfbt",new Class[] { String[].class }).invoke(model, new Object[]{zdySfbt});
			myClass.getMethod("setOpslist",new Class[] { HashMap.class }).invoke(model, opslist);
		}
		
	}


}