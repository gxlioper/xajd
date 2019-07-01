package xgxt.pjpy.tyb.zhszcp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.basic.BasicService;
import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.tyb.zhszcp.action.PjpyZhszcpwhActionForm;
import xgxt.pjpy.tyb.zhszcp.dao.PjpyZctjszDAO;
import xgxt.pjpy.tyb.zhszcp.dao.PjpyZhcpjxjDAO;
import xgxt.pjpy.tyb.zhszcp.dao.PjpyZhszcpDAO;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszcpModel;
import xgxt.rcsw.bxlp.BxlpDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class PjpyZhszcpService {

	PjpyZhszcpDAO dao = new PjpyZhszcpDAO();
	
	/**
	 * ͨ��������ѯ�Զ����ֶ���Ϣ
	 * @param tableName
	 * @return
	 */
	public List<HashMap<String, String>> getZdyZdList(String tableName) {
		return dao.getZdyZdList(tableName);
	}
	
	/**
	 * ����Զ����ֶν�� 
	 * @param tableName
	 * @return
	 */
	public HashMap<String, String[]> getZdyzdMap(String tableName) {
		List<HashMap<String, String>> rs = getZdyZdList(tableName, "��ʾ");
		HashMap<String, String[]> result = new HashMap<String, String[]>();
		if (!rs.isEmpty()) {
			String[] zdyCol = new String[rs.size()];
			String[] zdyColCN = new String[rs.size()];
			
			for(int i=0;i<rs.size();i++){
				zdyCol[i] = rs.get(i).get("zdid");
				zdyColCN[i] = rs.get(i).get("zdmc");
			}
			//�Զ����ֶ�Ӣ���ֶ�
			result.put("zdid", zdyCol);
			//�Զ����ֶ������ֶ�
			result.put("zdmc", zdyColCN);
		}
		return result;
	}
	
	/**
	 * �ۺ����ʲ�����ѯ��ͷ ����Զ����ֶ�
	 * 
	 * @param rs
	 * @return
	 */
	public List<HashMap<String, String>> getZhszcpTitle(
			HashMap<String, String[]> zdyzdMap, HashMap<String, String[]> zdMap) {
		// �Զ����ֶ��У�Ӣ���б�
		String[] zdyenArr = !zdyzdMap.isEmpty() ? zdyzdMap.get("zdid")
				: new String[] {};
		String[] zdycnArr = !zdyzdMap.isEmpty() ? zdyzdMap.get("zdmc")
				: new String[] {};

		// Ĭ�ϵĲ�ѯ�ֶ��б�
		String[] zdenArr = !zdMap.isEmpty() ? zdMap.get("en") : new String[] {};
		String[] zdcnArr = !zdMap.isEmpty() ? zdMap.get("cn") : new String[] {};

		DAO mydao = DAO.getInstance();

		// ���Ĭ���������Զ����ֶ�
		zdenArr = mydao.copyArray(zdenArr, zdyenArr);
		zdcnArr = mydao.copyArray(zdycnArr, zdycnArr);

		return mydao.arrayToList(zdenArr, zdcnArr);
	}
	
	/**
	 * �ۺ����ʲ�����ѯ��ͷ ����Զ����ֶ�
	 * 
	 * @param zdMap
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getZhszcpTitle(
			HashMap<String, String[]> zdMap) {
		DAO dao = DAO.getInstance();
		String[] en = zdMap.get("en");
		String[] cn = zdMap.get("cn");

		return dao.arrayToList(en, cn);
	}
	
	
	/**
	 * ��ȡ�ۺ����ʲ���Ҫ��ѯ���ֶ�
	 * @param tableName
	 * @param shjb
	 * @return HashMap<String, String[]>
	 * */
	public HashMap<String, String[]> getPjpyCxzdMap(String tableName, String shjb){
		DAO dao = DAO.getInstance();
		HashMap<String, String[]> resultMap = new HashMap<String, String[]>();
		HashMap<String, String[]> zdyzd = getZdyzdMap(tableName);//�Զ����ֶ�
		String[] zdyzdEn = zdyzd.get("zdid");//�Զ����ֶ�����
		String[] zdyzdCn = zdyzd.get("zdmc");//�Զ����ֶ���������
		
		HashMap<String, String[]> zdMap = getPjpyZdcxMap(tableName);//Ҫ��ѯ����֪�ֶ�
		String[] zdEn = zdMap.get("en");//Ҫ��ѯ����֪�ֶ�����
		String[] zdCn = zdMap.get("cn");//Ҫ��ѯ����֪�ֶ���������
		
		//����ֶ�
		String shzd = "";
		if("3".equalsIgnoreCase(shjb)){//3�����
			shzd = "fdysh,xysh,xxsh";
		}else if("2".equalsIgnoreCase(shjb)){//2�����
			shzd = "xysh,xxsh";
		}else if("1".equalsIgnoreCase(shjb)){//1�����
			shzd = "xxsh";
		}
		
		String[] shzdEn = null;//����ֶ�����
		String[] shzdCn = null;//����ֶ���������
		
		if(StringUtils.isNotNull(shzd)){
			shzdEn = shzd.split(",");
			shzdCn = dao.getColumnNameCN(shzdEn, tableName);
		}
		//����ֶ�
		String[] en = StringUtils.joinStrArr(zdEn,zdyzdEn,shzdEn);
		String[] cn = StringUtils.joinStrArr(zdCn,zdyzdCn,shzdCn);
		
		resultMap.put("en", en);
		resultMap.put("cn", cn);
		return resultMap;
	}
	
	/**
	 * ��ѯ�ۺ����ʲ������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhszcpResult(PjpyZhszcpModel model,
			HashMap<String, String[]> zdMap,
			HashMap<String, String[]> zdyzdMap,
			String tableName, String fdm, String[] zbzdArray)
			throws Exception {
		
		String[] colList = !zdMap.isEmpty() ? zdMap.get("en")
				: new String[] {};
		
		//���Զ����ֶ��ظ�������Ҫ�滻��.
		//String[] zdyzdArray = zdyzdMap.get("zdid");
		String[] zdyzdArray = zdyzdMap.get("zdid") != null ? zdyzdMap
				.get("zdid") : new String[] {};
		zbzdArray = replaceCfsj(zbzdArray, toLowerCaseArray(zdyzdArray));
		
		
		return dao.queryZhszcpResult(model, colList, zdyzdMap, tableName, fdm, zbzdArray);
	}
	
	/**
	 * ��ѯ��������Ĭ��Ҫ��ѯ�ֶ��б�
	 * 
	 * @param tableName
	 * @return
	 */
	public List<String[]> queryPjpyzdcxList(String tableName) {
		return dao.queryPjpyzdcxList(tableName);
	}
	
	/**
	 * ����ѯ������Ĭ�ϲ�ѯ�ֶ�ת��ΪMAP
	 * 
	 * @param tableName
	 * @return
	 */
	public HashMap<String, String[]> getPjpyZdcxMap(String tableName) {
		List<String[]> rs = queryPjpyzdcxList(tableName);
		HashMap<String, String[]> map = new HashMap<String, String[]>();
		if (!rs.isEmpty()) {
			String[] enArray = new String[rs.size()];
			String[] cnArray = new String[rs.size()];
			for (int i = 0; i < rs.size(); i++) {
				String[] array = rs.get(i);
				if (array != null && array.length >= 2) {
					enArray[i] = array[0];
					cnArray[i] = array[1];
				}
			}
			map.put("en", enArray);
			map.put("cn", cnArray);
		}
		return map;
	}
	
	/**
	 * ��ȡ����б�
	 * @param type
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getChkList(int type){
		return dao.getChkList(type);
	}
	
	/**
	 * ����ѯ�������Զ����ֶη�װ��JSON����
	 * 
	 * @param rs
	 * @return ��ʽ{zdid:'1',zdmc:'xx',...}!@{zdid:'1',zdmc:'xx',...}...
	 */
	public String appendJsOjbectByZdyzdxx(List<HashMap<String, String>> rs) {
		StringBuilder result = new StringBuilder("");
		if (!rs.isEmpty()) {
			for (int i = 0; i < rs.size(); i++) {
				HashMap<String, String> map = rs.get(i);
				if (!map.isEmpty()) {
					result.append("{");
					for (Map.Entry<String, String> entry : map.entrySet()) {
						result.append(entry.getKey());
						result.append(":'");
						result
								.append(StringUtils.isNull(entry.getValue())
										|| "null".equalsIgnoreCase(entry
												.getValue()) ? "" : entry
										.getValue());
						result.append("',");
					}
					result = new StringBuilder(result.delete(
							result.length() - 1, result.length()));
					result.append("}!@");
				}
			}
		}
		return result.toString();
	}
	
	/**
	 * ͨ���ֶ�ID�ͱ�����ѯ���ֶ�����Ӧ�������б�����
	 * 
	 * @param tableName
	 * @param xn
	 * @return
	 */
	public List<HashMap<String, String>> queryZdyzdSelectData(
			String zdid, String tabName) {
		List<HashMap<String, String>> list = dao.queryZdyzdSelectOption(zdid, tabName);
		return list;
	}
	
	/**
	 * ���ݱ�����������ѯ������Ϣ
	 * @param tableName
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryZdyzdNr(String tableName, String pkValue){
		//�Զ����ֶα�������
		List<HashMap<String, String>> map = dao.getZdyzybcnr(tableName, pkValue);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		for(HashMap<String, String> zdnr : map){
			resultMap.put(zdnr.get("zdid"), zdnr.get("bcnr"));
		}
		
		return resultMap;
	}
	
	/**
	 * �����Զ����ֶ���Ϣ,valueMap�б������pkValue
	 * @param tableName
	 * @param valueMap
	 * @return boolean 
	 * */
	public int[] saveZdyzdNr(String tableName, HashMap<String, String>[] valueMap){
		return dao.saveZdyzdNr(tableName,valueMap);
	}
	
	/**
	 * �����Զ����ֶ���Ϣ,valueMap�б������pkValue
	 * @param tableName
	 * @param valueMap
	 * @return boolean 
	 * */
	public String[] saveZdyzdNrByString(String tableName, HashMap<String, String>[] valueMap){
		return dao.saveZdyzdNrByString(tableName, valueMap);
	}
	
	/**
	 * �����Զ����ֶ���Ϣ,valueMap�б������pkValue
	 * @param tableName
	 * @param valueMap
	 * @return boolean 
	 * */
	public boolean saveZdyzdNrByFlag(String tableName, HashMap<String, String>[] valueMap){
		return dao.checkBatch(dao.saveZdyzdNr(tableName,valueMap));
		
	}
	
	/**
	 * ͨ��������ȡ�ñ�����Ӧ������ֵ  ����Ϊ����
	 * @param realTable
	 * @return
	 */
	public String[] getTablePrimaryKey(String realTable) {
		BasicService service = new BasicService();
		String pkStr = service.getTable(realTable).getPrimaryKey();
		return StringUtils.isNotNull(pkStr) ? pkStr.split(",")
				: new String[] {};
	}
	
	
	/**
	 * �����ۺ����ʲ���������Ϣ
	 * @param model
	 * @param zdyzdValueMap
	 * @return
	 */
	public int[] saveZhszcpZbInfo(PjpyZhszcpModel model,
			HashMap<String, String[]> zdyzdValueMap, String tableName, List<HashMap<String, String>> zdyzdMap){
		
		tableName = tableName != null ? tableName.replace("view_", "") : tableName;
		
		StringBuilder sbd = new StringBuilder("insert into ");
		sbd.append(tableName);
		sbd.append(" (xh,xn,xq,nd,");
		
		//�����ֶ��б�
		String[] pkArr = getTablePrimaryKey(tableName);
		List<String> pkzdList = new ArrayList<String>();
		
		//ȡ����xh,xn,xq,nd�ֶ��б�
		if (pkArr != null ) {
			for (int i=0;i<pkArr.length;i++) {
				if (!"xh".equalsIgnoreCase(pkArr[i])
						&& !"xn".equalsIgnoreCase(pkArr[i])
						&& !"xq".equalsIgnoreCase(pkArr[i])
						&& !"nd".equalsIgnoreCase(pkArr[i])) {
					pkzdList.add(pkArr[i]);
				}
			}
		}
		
		for (String s : pkzdList) {
			sbd.append(s);
			sbd.append(",");
		}
		
		//ƴ��ѧ�ţ�ѧ�꣬ѧ�ڣ���Ȳ���
		sbd.deleteCharAt(sbd.length() - 1);
		sbd.append(") values ('");
		sbd.append(model.getXh());
		sbd.append("','");
		sbd.append(model.getXn());
		sbd.append("','");
		sbd.append(model.getXq());
		sbd.append("','");
		sbd.append(model.getNd());
		sbd.append("',");
		
		
		//ƴ�Ӹ��Զ�����е���������ֶβ���
		String[] zdValue = zdyzdValueMap.get(zdyzdMap.get(0).get("zdid"));	
		String[] sqlArr = new String[zdValue.length]; 
		for(int i=0; i<zdValue.length; i++){
			StringBuilder sql = new StringBuilder();
			sql.append(sbd);
			for(String key : pkzdList){
				sql.append("'");
				String[] valueArray = zdyzdValueMap.get(key);
				sql.append(valueArray != null && valueArray.length > 0 ? valueArray[i] : "");
				sql.append("',");
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(")");
			sqlArr[i] = sql.toString();
		}
		//ƴ�����
		
		DAO myDao = DAO.getInstance();
		int[] count = null;
		try{
			count = myDao.runBatch(sqlArr);
		} catch (Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * �����ۺ����ʲ���������Ϣ
	 * @param model
	 * @param zdyzdValueMap
	 * @return
	 */
	public String[] saveZhszcpZbInfoByString(PjpyZhszcpModel model,
			HashMap<String, String[]> zdyzdValueMap, String tableName, List<HashMap<String, String>> zdyzdMap){
		
		tableName = tableName != null ? tableName.replace("view_", "") : tableName;
		
		StringBuilder sbd = new StringBuilder("insert into ");
		sbd.append(tableName);
		sbd.append(" (xh,xn,xq,nd,");
		
		//�����ֶ��б�
		String[] pkArr = getTablePrimaryKey(tableName);
		List<String> pkzdList = new ArrayList<String>();
		
		//ȡ����xh,xn,xq,nd�ֶ��б�
		if (pkArr != null ) {
			for (int i=0;i<pkArr.length;i++) {
				if (!"xh".equalsIgnoreCase(pkArr[i])
						&& !"xn".equalsIgnoreCase(pkArr[i])
						&& !"xq".equalsIgnoreCase(pkArr[i])
						&& !"nd".equalsIgnoreCase(pkArr[i])) {
					pkzdList.add(pkArr[i]);
				}
			}
		}
		
		for (String s : pkzdList) {
			sbd.append(s);
			sbd.append(",");
		}
		
		//ƴ��ѧ�ţ�ѧ�꣬ѧ�ڣ���Ȳ���
		sbd.deleteCharAt(sbd.length() - 1);
		sbd.append(") values ('");
		sbd.append(model.getXh());
		sbd.append("','");
		sbd.append(model.getXn());
		sbd.append("','");
		sbd.append(model.getXq());
		sbd.append("','");
		sbd.append(model.getNd());
		sbd.append("',");
		
		
		//ƴ�Ӹ��Զ�����е���������ֶβ���
		String[] zdValue = zdyzdValueMap.get(zdyzdMap.get(0).get("zdid"));	
		String[] sqlArr = new String[zdValue.length]; 
		for(int i=0; i<zdValue.length; i++){
			StringBuilder sql = new StringBuilder();
			sql.append(sbd);
			for(String key : pkzdList){
				sql.append("'");
				String[] valueArray = zdyzdValueMap.get(key);
				sql.append(valueArray != null && valueArray.length > 0 ? valueArray[i] : "");
				sql.append("',");
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(")");
			sqlArr[i] = sql.toString();
		}
		//ƴ�����
		
//		DAO myDao = DAO.getInstance();
//		int[] count = null;
//		try{
//			count = myDao.runBatch(sqlArr);
//		} catch (Exception e){
//			e.printStackTrace();
//		}
		return sqlArr;
	}
	
	/**
	 * �����ۺ����ʲ�����Ϣ
	 * @param model
	 * @param zdyzdValueMap
	 * @param tableName
	 * @return
	 */
	public boolean saveZhszcpInfo(PjpyZhszcpModel model,
			HashMap<String, String[]> zdyzdValueMap, String tableName,
			List<HashMap<String, String>> zdyzdMap) throws Exception{
		//����������Ϣ
		String[] zbresult = saveZhszcpZbInfoByString(model, zdyzdValueMap, tableName, zdyzdMap);
		
		//boolean flag = dao.checkBatch(result);
		
		//��������ʧ�ܵļ�¼�������м�¼
//		List<String> list = new ArrayList<String>();
//		if (result != null) {
//			String[] zbStr = new String[result.length];
//			for (int i=0;i<zbStr.length;i++) {
//				if (result[i] <= 0) {
//					list.add(String.valueOf(i+1));
//				}
//			}
//		}
		
		//���渽����Ϣ�����Զ����ֶ���Ϣ
	//	if (flag) {
			String pkValue = model.getXh()+model.getXn()+model.getXq()+model.getNd();//����ֵxh,xn,xq,nd
			
			int length = 0;
			//TODO 
			for(String key : zdyzdValueMap.keySet()){
				if(length == 0){
					length = zdyzdValueMap.get(key).length;
					//break add;
				}
			}
			HashMap[] valueMap = new HashMap[zdyzdValueMap.size()*length];
			int rCount = 0;//��¼��
			int m = 0;
			for(String key : zdyzdValueMap.keySet()){					
				String[] zdz = zdyzdValueMap.get(key);
				rCount = zdz.length;
				for(int i=0; i<rCount; i++){		
					HashMap<String, String> recordMap = new HashMap<String, String>();
					recordMap.put(key, zdz[i]);
					recordMap.put("pkValue", pkValue
							+ appendPkValue(zdyzdValueMap, i,
									tableName != null ? tableName.replace(
											"view_", "") : tableName));
					valueMap[m++] = recordMap;					
				}
			}			
			String[] fbresult = saveZdyzdNrByString(tableName, valueMap);
			
			//�Ա��渽��ʧ�ܵļ�¼�����б���
//			if (result != null) {
//				String[] zbStr = new String[result.length];
//				for (int i=0;i<zbStr.length;i++) {
//					if (result[i] <= 0) {
//						if (list != null && checkExists(list, String.valueOf(result[i]))) {
//							list.add(String.valueOf(i+1));
//						}
//					}
//				}
//			}
			//TODO ���渽����Ϣ			
		//}
		//TODO ����Ҫ�޸�Ϊ��������,���ڱ���ʧ�ܵļ�¼�����ص�ҳ�沢��ʾ���û�
		
		int[] flag = dao.runBatch(dao.unionArray(zbresult, fbresult));	
		return dao.checkBatch(flag);
	}
	
	/**
	 * ��ȡ��¼���Զ����ֶ�����ֵ
	 * @param zdyzdValueMap
	 * @param index
	 * @param tableName
	 * */
	public String appendPkValue(HashMap<String, String[]> zdyzdValueMap,
								int index,
								String tableName){
		String[] pkArr = getTablePrimaryKey(tableName);
		List<String> pkzdList = new ArrayList<String>();		
		//ȡ����xh,xn,xq,nd�ֶ��б�
		if (pkArr != null ) {
			for (int i=0;i<pkArr.length;i++) {
				if (!"xh".equalsIgnoreCase(pkArr[i])
						&& !"xn".equalsIgnoreCase(pkArr[i])
						&& !"xq".equalsIgnoreCase(pkArr[i])
						&& !"nd".equalsIgnoreCase(pkArr[i])) {
					pkzdList.add(pkArr[i]);
				}
			}
		}
		
		//��ȡ�Զ����ֶ����������ֶε�ָ����¼��ֵ
		StringBuilder result = new StringBuilder();			
		for(String key : pkzdList){
			result.append(zdyzdValueMap.get(key)[index]);
		}
		
		return result.toString();
	}
	
	/**
	 * ɾ���Զ����ֶ�����
	 * @param primaryKeys
	 * @param tabName
	 * @param bdszBcnrb
	 * @param user
	 * @return boolean
	 * */
	public boolean deleteBdszNrbcb(String[] primaryKeys,
			                       String tabName,
			                       String bdszBcnrb,
			                       User user){
		return dao.deleteBdsznr(primaryKeys, tabName, bdszBcnrb, user);
	}
	
	/**
	 * ͨ������������ۺ����ʲ��������б�
	 * @param dmjb
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpdmList(
			List<HashMap<String, String>> dmList, String dmjb) {
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		if (StringUtils.isNotNull(dmjb)) {
			for (HashMap<String, String> map : dmList) {
				if (dmjb.equalsIgnoreCase(map.get("dmjb"))) {
					rs.add(map);
				}
			}
		}
		return rs;
	} 
	
	/**
	 * �����ϼ������ȡ���������б�
	 * @param dmjb
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpdmList(String dmjb, String sjbmdm) {
		PjpyZhcpjxjDAO dao = new PjpyZhcpjxjDAO();
		return dao.getZhcpdmList(dmjb, sjbmdm);
	} 
	
	
	/**
	 * ��ѯ�ۺ����ʲ������еĴ�����Ϣ
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpdmList() {
		PjpyZctjszDAO tjszDAO = new PjpyZctjszDAO();
		return tjszDAO.queryZhszcpDmList();
	}
	
	/**
	 * ��ȡ�ۺ����ʲ����������ݵı�ͷ����Ϣ
	 * @param pjzq
	 * @param jb
	 * @param xmdmMap
	 * @return HashMap<String, String[]>
	 * */
	public HashMap<String, String[]> getZhszcpzfExpTitle(String pjzq,
			String jb, HashMap<String, String> map, boolean isQuery,
			PjpyZhszcpwhActionForm model) throws Exception {
		HashMap<String, String[]> rs = new HashMap<String, String[]>();

		PjpyZhszcpDAO dao = new PjpyZhszcpDAO();
		String viewName = "view_pjpy_zhszcpb";//��ͼ
		String[] en = {"xh","xm","nj","xymc","zymc","bjmc"};//Ĭ��Ҫ��������ѧ��������Ϣ��ص��ֶ�
		
		if (isQuery) {
			en = new String[]{"pk", "xh","xm","bjmc"};
		}
		
		String[] cn = {};//�е���������
		
		//��������
		if("xn".equalsIgnoreCase(pjzq)){
			en = StringUtils.joinStrArr(en, new String[]{"xn"});
		}else if("xq".equalsIgnoreCase(pjzq)){
			en = StringUtils.joinStrArr(en, new String[]{"xn", "xqmc"});
		}else if("nd".equalsIgnoreCase(pjzq)){
			en = StringUtils.joinStrArr(en, new String[]{"nd"});
		}
		//��ȡĬ��Ҫ�����е���������
		cn = dao.getColumnNameCN(en, viewName);
		
		//Ҫ��������Ŀ
		if(!"1".equalsIgnoreCase(jb)){//��һ��
			String xmdm = map.get(jb);//��Ŀ����
			String[] xmdmArray = new String[]{};
			if ("2".equalsIgnoreCase(jb)) {
				if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
					xmdm = map.get("2");
					xmdmArray = new String[]{xmdm};
				}
				
			} else if ("3".equalsIgnoreCase(jb)) {
				if (StringUtils.isNotNull(model.getQueryequals_sjdm())) {
					xmdm = map.get("3");
					xmdmArray = new String[]{xmdm};
				} else if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
					xmdm = map.get("2");
					xmdmArray = dao
							.getArray(
									"select dm from pjpy_zctjszb where fdm = ? and dmjb='3'",
									new String[] { xmdm }, "dm");
				}
				
			} else if ("4".equalsIgnoreCase(jb)) {
				if (StringUtils.isNotNull(model.getQueryequals_sidm())) {
					xmdm = map.get("4");	
					xmdmArray = new String[]{xmdm};
				} else if (StringUtils.isNotNull(model.getQueryequals_sjdm())) {
					xmdm = map.get("3");
					xmdmArray = dao
							.getArray(
									"select dm from pjpy_zctjszb where fdm = ? and dmjb='4'",
									new String[] { xmdm }, "dm");
				} else if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
					xmdm = map.get("2");
					xmdmArray = dao
							.getArray(
									"select dm from pjpy_zctjszb a where exists (select 1 from pjpy_zctjszb b where b.fdm = ? and b.dmjb='3' and a.fdm=b.dm)",
									new String[] { xmdm }, "dm");
				}
			}
			
			cn = StringUtils.joinStrArr(cn,  dao.getZhcpxmMc(jb,xmdmArray));
		}
		//Ĭ�ϵ����ܷ��ֶ�
		cn = StringUtils.joinStrArr(cn, new String[]{"�ܷ�","�ܷ�����"});//�ܷ�
		
		//���������к�
//		if (en != null && cn != null && en.length == cn.length) {
//			String[] addEn = new String[en.length + 1];
//			String[] addCn = new String[cn.length + 1];
//			addEn[0] = "r";
//			addCn[0] = "�к�";
//			for (int i=0;i<en.length;i++) {
//				addEn[i + 1] = en[i];
//				addCn[i + 1] = cn[i];
//			}
//		}
		
		rs.put("en", en);
		rs.put("cn", cn);
		
		return rs;
	}
	
	/**
	 * ��ѯ�ۺϲ����ܷ���Ϣ
	 * @param pjzq
	 * @param jb
	 * @param map
	 * @param model
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]> queryZhszcpzfForExp(String pjzq, String jb,
			HashMap<String, String> map, String[] output,
			PjpyZhszcpwhActionForm model, boolean isPage) throws Exception {
		return dao.queryZhszcpzfForExp(pjzq, jb, map, output, model, isPage);
	}
	 
	/**
	 * �����ۺ����ʲ�����������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean countZhszcpzf(PjpyZhszcpModel model){
		String xxdm = Base.xxdm;
		//�����۲��Զ����㷽ʽ
		//��һ����pjpy_zhszcpb�����Ѿ������ļ���Ŀ�ķ���,�ڶ�������Ҫ�ȼ����ļ���Ŀ�ķ���
		
		if (Globals.XXDM_NTZYDX.equalsIgnoreCase(xxdm) ) {//�������ѧУ������ͨ��һ�ף���������������ж�
			return dao.countZhszcpzfSec(model);
		} else {
			return dao.countZhszcpzf(model);
		}
		
	}
	
	/**
	 * �����ۺ����ʲ�����������(�ڶ���)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean countZhszcpzfSec(PjpyZhszcpModel model){
		return dao.countZhszcpzfSec(model);
	}
	
	/**
	 * ����������ظ����ݣ�ɾȥ�ظ�����,���ص�һ�������в���ڶ��������ظ�������
	 * 
	 * @author lt
	 */
	public String[] replaceCfsj(String[] first, String[] second) {

		if (first != null && first.length > 0 && second != null
				&& second.length > 0) {
			List<String> fir = new ArrayList<String>(Arrays.asList(first));
			List<String> sec = Arrays.asList(second);

			fir.removeAll(sec);
			return !fir.isEmpty() ? fir.toArray(new String[0]) : new String[] {};
		} else {
			return first;
		}
	}
	
	/**
	 * ������������ַ�ȫ��ת��д
	 * @param array
	 * @return
	 */
	public String[] toLowerCaseArray(String[] array) {
		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				array[i] = array[i] != null ? array[i].toUpperCase() : "";
			}
		}
		return array;
	}
	
	/**
	 * ��ѯ�ۺ����ʲ�����ͷ
	 * @param cnName
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpTitle(String[] cnName) {
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		if (cnName != null && cnName.length > 0) {
			for (int i = 0; i < cnName.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("en", cnName[i]);
				map.put("cn", cnName[i]);
				rs.add(map);
			}
		}
		return rs;
	}
	
	/**
	 * ͨ��������ѯ�Զ����ֶ���Ϣ
	 * @param tableName
	 * @return
	 */
	public List<HashMap<String, String>> getZdyZdList(String tableName, String cxxs) {
		return dao.getZdyZdList(tableName, cxxs);
	}
	
	public String[] queryStuXh(String bjdm) throws Exception{
		List<String> rs = dao.queryXhList(bjdm);
		return rs != null && !rs.isEmpty() ? rs.toArray(new String[0]) : null;
	}
	
	public boolean checkExists(List<String> list, String str) {
		if (list != null) {
			for (int i=0;i<list.size();i++) {
				if (str.equalsIgnoreCase(list.get(i))) {
					return false;
				} 
			}
		}
		return true;
	}
	
	public static void main(String...strings) {
		List<String> list = new ArrayList<String>(){{add("1");add("2");add("3");add("1");add("3");}};
		System.out.println(list.toString());
	}
	
	public boolean selectTableExists(String table) {
		return dao.selectTableExists(table);
	}
}
