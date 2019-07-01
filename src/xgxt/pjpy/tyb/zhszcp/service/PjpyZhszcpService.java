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
	 * 通过表名查询自定义字段信息
	 * @param tableName
	 * @return
	 */
	public List<HashMap<String, String>> getZdyZdList(String tableName) {
		return dao.getZdyZdList(tableName);
	}
	
	/**
	 * 存放自定义字段结果 
	 * @param tableName
	 * @return
	 */
	public HashMap<String, String[]> getZdyzdMap(String tableName) {
		List<HashMap<String, String>> rs = getZdyZdList(tableName, "显示");
		HashMap<String, String[]> result = new HashMap<String, String[]>();
		if (!rs.isEmpty()) {
			String[] zdyCol = new String[rs.size()];
			String[] zdyColCN = new String[rs.size()];
			
			for(int i=0;i<rs.size();i++){
				zdyCol[i] = rs.get(i).get("zdid");
				zdyColCN[i] = rs.get(i).get("zdmc");
			}
			//自定义字段英文字段
			result.put("zdid", zdyCol);
			//自定义字段中文字段
			result.put("zdmc", zdyColCN);
		}
		return result;
	}
	
	/**
	 * 综合素质测评查询表头 结合自定义字段
	 * 
	 * @param rs
	 * @return
	 */
	public List<HashMap<String, String>> getZhszcpTitle(
			HashMap<String, String[]> zdyzdMap, HashMap<String, String[]> zdMap) {
		// 自定义字段中，英文列表
		String[] zdyenArr = !zdyzdMap.isEmpty() ? zdyzdMap.get("zdid")
				: new String[] {};
		String[] zdycnArr = !zdyzdMap.isEmpty() ? zdyzdMap.get("zdmc")
				: new String[] {};

		// 默认的查询字段列表
		String[] zdenArr = !zdMap.isEmpty() ? zdMap.get("en") : new String[] {};
		String[] zdcnArr = !zdMap.isEmpty() ? zdMap.get("cn") : new String[] {};

		DAO mydao = DAO.getInstance();

		// 组合默认字优与自定义字段
		zdenArr = mydao.copyArray(zdenArr, zdyenArr);
		zdcnArr = mydao.copyArray(zdycnArr, zdycnArr);

		return mydao.arrayToList(zdenArr, zdcnArr);
	}
	
	/**
	 * 综合素质测评查询表头 结合自定义字段
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
	 * 获取综合素质测评要查询的字段
	 * @param tableName
	 * @param shjb
	 * @return HashMap<String, String[]>
	 * */
	public HashMap<String, String[]> getPjpyCxzdMap(String tableName, String shjb){
		DAO dao = DAO.getInstance();
		HashMap<String, String[]> resultMap = new HashMap<String, String[]>();
		HashMap<String, String[]> zdyzd = getZdyzdMap(tableName);//自定义字段
		String[] zdyzdEn = zdyzd.get("zdid");//自定义字段名称
		String[] zdyzdCn = zdyzd.get("zdmc");//自定义字段中文名称
		
		HashMap<String, String[]> zdMap = getPjpyZdcxMap(tableName);//要查询的已知字段
		String[] zdEn = zdMap.get("en");//要查询的已知字段名称
		String[] zdCn = zdMap.get("cn");//要查询的已知字段中文名称
		
		//审核字段
		String shzd = "";
		if("3".equalsIgnoreCase(shjb)){//3级审核
			shzd = "fdysh,xysh,xxsh";
		}else if("2".equalsIgnoreCase(shjb)){//2级审核
			shzd = "xysh,xxsh";
		}else if("1".equalsIgnoreCase(shjb)){//1级审核
			shzd = "xxsh";
		}
		
		String[] shzdEn = null;//审核字段名称
		String[] shzdCn = null;//审核字段中文名称
		
		if(StringUtils.isNotNull(shzd)){
			shzdEn = shzd.split(",");
			shzdCn = dao.getColumnNameCN(shzdEn, tableName);
		}
		//组合字段
		String[] en = StringUtils.joinStrArr(zdEn,zdyzdEn,shzdEn);
		String[] cn = StringUtils.joinStrArr(zdCn,zdyzdCn,shzdCn);
		
		resultMap.put("en", en);
		resultMap.put("cn", cn);
		return resultMap;
	}
	
	/**
	 * 查询综合素质测评结果
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
		
		//与自定义字段重复的数据要替换掉.
		//String[] zdyzdArray = zdyzdMap.get("zdid");
		String[] zdyzdArray = zdyzdMap.get("zdid") != null ? zdyzdMap
				.get("zdid") : new String[] {};
		zbzdArray = replaceCfsj(zbzdArray, toLowerCaseArray(zdyzdArray));
		
		
		return dao.queryZhszcpResult(model, colList, zdyzdMap, tableName, fdm, zbzdArray);
	}
	
	/**
	 * 查询评奖评优默认要查询字段列表
	 * 
	 * @param tableName
	 * @return
	 */
	public List<String[]> queryPjpyzdcxList(String tableName) {
		return dao.queryPjpyzdcxList(tableName);
	}
	
	/**
	 * 将查询出来的默认查询字段转化为MAP
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
	 * 获取审核列表
	 * @param type
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getChkList(int type){
		return dao.getChkList(type);
	}
	
	/**
	 * 将查询出来的自定义字段封装成JSON对象
	 * 
	 * @param rs
	 * @return 格式{zdid:'1',zdmc:'xx',...}!@{zdid:'1',zdmc:'xx',...}...
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
	 * 通过字段ID和表名查询该字段所对应的下拉列表数据
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
	 * 根据表名和主键查询附表信息
	 * @param tableName
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryZdyzdNr(String tableName, String pkValue){
		//自定义字段保存内容
		List<HashMap<String, String>> map = dao.getZdyzybcnr(tableName, pkValue);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		for(HashMap<String, String> zdnr : map){
			resultMap.put(zdnr.get("zdid"), zdnr.get("bcnr"));
		}
		
		return resultMap;
	}
	
	/**
	 * 保存自定义字段信息,valueMap中必须包含pkValue
	 * @param tableName
	 * @param valueMap
	 * @return boolean 
	 * */
	public int[] saveZdyzdNr(String tableName, HashMap<String, String>[] valueMap){
		return dao.saveZdyzdNr(tableName,valueMap);
	}
	
	/**
	 * 保存自定义字段信息,valueMap中必须包含pkValue
	 * @param tableName
	 * @param valueMap
	 * @return boolean 
	 * */
	public String[] saveZdyzdNrByString(String tableName, HashMap<String, String>[] valueMap){
		return dao.saveZdyzdNrByString(tableName, valueMap);
	}
	
	/**
	 * 保存自定义字段信息,valueMap中必须包含pkValue
	 * @param tableName
	 * @param valueMap
	 * @return boolean 
	 * */
	public boolean saveZdyzdNrByFlag(String tableName, HashMap<String, String>[] valueMap){
		return dao.checkBatch(dao.saveZdyzdNr(tableName,valueMap));
		
	}
	
	/**
	 * 通过表名获取该表所对应的主键值  返回为数组
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
	 * 保存综合素质测评主表信息
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
		
		//主键字段列表
		String[] pkArr = getTablePrimaryKey(tableName);
		List<String> pkzdList = new ArrayList<String>();
		
		//取出非xh,xn,xq,nd字段列表
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
		
		//拼接学号，学年，学期，年度部分
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
		
		
		//拼接跟自定义表中的主键相关字段部分
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
		//拼接完成
		
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
	 * 保存综合素质测评主表信息
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
		
		//主键字段列表
		String[] pkArr = getTablePrimaryKey(tableName);
		List<String> pkzdList = new ArrayList<String>();
		
		//取出非xh,xn,xq,nd字段列表
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
		
		//拼接学号，学年，学期，年度部分
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
		
		
		//拼接跟自定义表中的主键相关字段部分
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
		//拼接完成
		
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
	 * 保存综合素质测评信息
	 * @param model
	 * @param zdyzdValueMap
	 * @param tableName
	 * @return
	 */
	public boolean saveZhszcpInfo(PjpyZhszcpModel model,
			HashMap<String, String[]> zdyzdValueMap, String tableName,
			List<HashMap<String, String>> zdyzdMap) throws Exception{
		//保存主表信息
		String[] zbresult = saveZhszcpZbInfoByString(model, zdyzdValueMap, tableName, zdyzdMap);
		
		//boolean flag = dao.checkBatch(result);
		
		//保存主表失败的记录行数进行记录
//		List<String> list = new ArrayList<String>();
//		if (result != null) {
//			String[] zbStr = new String[result.length];
//			for (int i=0;i<zbStr.length;i++) {
//				if (result[i] <= 0) {
//					list.add(String.valueOf(i+1));
//				}
//			}
//		}
		
		//保存附表信息，即自定义字段信息
	//	if (flag) {
			String pkValue = model.getXh()+model.getXn()+model.getXq()+model.getNd();//主键值xh,xn,xq,nd
			
			int length = 0;
			//TODO 
			for(String key : zdyzdValueMap.keySet()){
				if(length == 0){
					length = zdyzdValueMap.get(key).length;
					//break add;
				}
			}
			HashMap[] valueMap = new HashMap[zdyzdValueMap.size()*length];
			int rCount = 0;//记录数
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
			
			//对保存附表失败的记录数进行保存
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
			//TODO 保存附表信息			
		//}
		//TODO 这里要修改为返回数据,对于保存失败的记录数返回到页面并提示给用户
		
		int[] flag = dao.runBatch(dao.unionArray(zbresult, fbresult));	
		return dao.checkBatch(flag);
	}
	
	/**
	 * 获取记录的自定义字段主键值
	 * @param zdyzdValueMap
	 * @param index
	 * @param tableName
	 * */
	public String appendPkValue(HashMap<String, String[]> zdyzdValueMap,
								int index,
								String tableName){
		String[] pkArr = getTablePrimaryKey(tableName);
		List<String> pkzdList = new ArrayList<String>();		
		//取出非xh,xn,xq,nd字段列表
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
		
		//获取自定义字段中是主键字段的指定记录的值
		StringBuilder result = new StringBuilder();			
		for(String key : pkzdList){
			result.append(zdyzdValueMap.get(key)[index]);
		}
		
		return result.toString();
	}
	
	/**
	 * 删除自定义字段内容
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
	 * 通过代码给别拆分综合素质测评代码列表
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
	 * 根据上级代码获取本级代码列表
	 * @param dmjb
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpdmList(String dmjb, String sjbmdm) {
		PjpyZhcpjxjDAO dao = new PjpyZhcpjxjDAO();
		return dao.getZhcpdmList(dmjb, sjbmdm);
	} 
	
	
	/**
	 * 查询综合素质测评所有的代码信息
	 * @return
	 */
	public List<HashMap<String, String>> queryZhszcpdmList() {
		PjpyZctjszDAO tjszDAO = new PjpyZctjszDAO();
		return tjszDAO.queryZhszcpDmList();
	}
	
	/**
	 * 获取综合素质测评导出数据的表头列信息
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
		String viewName = "view_pjpy_zhszcpb";//视图
		String[] en = {"xh","xm","nj","xymc","zymc","bjmc"};//默认要导出的与学生个人信息相关的字段
		
		if (isQuery) {
			en = new String[]{"pk", "xh","xm","bjmc"};
		}
		
		String[] cn = {};//列的中文名称
		
		//评奖周期
		if("xn".equalsIgnoreCase(pjzq)){
			en = StringUtils.joinStrArr(en, new String[]{"xn"});
		}else if("xq".equalsIgnoreCase(pjzq)){
			en = StringUtils.joinStrArr(en, new String[]{"xn", "xqmc"});
		}else if("nd".equalsIgnoreCase(pjzq)){
			en = StringUtils.joinStrArr(en, new String[]{"nd"});
		}
		//获取默认要导出列的中文名称
		cn = dao.getColumnNameCN(en, viewName);
		
		//要导出的项目
		if(!"1".equalsIgnoreCase(jb)){//非一级
			String xmdm = map.get(jb);//项目代码
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
		//默认导出总分字段
		cn = StringUtils.joinStrArr(cn, new String[]{"总分","总分排名"});//总分
		
		//单独增加行号
//		if (en != null && cn != null && en.length == cn.length) {
//			String[] addEn = new String[en.length + 1];
//			String[] addCn = new String[cn.length + 1];
//			addEn[0] = "r";
//			addCn[0] = "行号";
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
	 * 查询综合测评总分信息
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
	 * 计算综合素质测评总项级别分数
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean countZhszcpzf(PjpyZhszcpModel model){
		String xxdm = Base.xxdm;
		//二套综测自动计算方式
		//第一种是pjpy_zhszcpb里面已经存了四级项目的分数,第二种是需要先计算四级项目的分数
		
		if (Globals.XXDM_NTZYDX.equalsIgnoreCase(xxdm) ) {//如果其它学校采用南通这一套，可以在这里加上判断
			return dao.countZhszcpzfSec(model);
		} else {
			return dao.countZhszcpzf(model);
		}
		
	}
	
	/**
	 * 计算综合素质测评总项级别分数(第二套)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean countZhszcpzfSec(PjpyZhszcpModel model){
		return dao.countZhszcpzfSec(model);
	}
	
	/**
	 * 检查两数组重复数据，删去重复部分,返回第一个数组中不与第二个数组重复的数据
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
	 * 将数组里面的字符全部转大写
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
	 * 查询综合素质测评表头
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
	 * 通过表名查询自定义字段信息
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
