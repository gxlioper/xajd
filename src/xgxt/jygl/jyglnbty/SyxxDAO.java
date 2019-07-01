package xgxt.jygl.jyglnbty;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.rcgl.jycxzm.jyglUtil;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;



// TODO: Auto-generated Javadoc
/**
 * The Class JycxzmDAO.
 */
public class SyxxDAO extends DAO {

	/**
	 * Dao_ jycczm add.教育储蓄证明录入
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public boolean dao_JycczmAdd(SyxxModel model,String tableName) throws Exception {
		DAO dao = DAO.getInstance();
		boolean result = false;
		String sql = "";
		String pk = model.getXh();
//		String xn = model.getXn();
//		String xq = model.getXq();
//		String zqsj = model.getZqsj();
		boolean isExists = dao_JycczmIsExists(pk);
		String[] input = null;
		if(StringUtils.isNotNull(pk)){
//			sql = "delete from czxx_jycyzmb where xh=? and xn=? and xq=? and zqsj=?";
//			dao.runUpdate(sql, new String[]{pk,xn,xq,zqsj});
//			input = new String[]{"xh","xxshsj","yzyh","yzzh","sfzh","zqsj","nd","xn","xq","xxshr"};
//			sql = SyxxUtil.insertSqlForModel(input, model, tableName);
//			result = dao.insert(sql, new String[]{});
		}
		if(!isExists){
			input = new String[]{"xh","xm","xb","sfzh","csny","mz","zzmm","lxdz","yzbm","lxdh","sjhm","email","xynx",
					"xxdm","xydm","zydm","bjdm","rxnf","bynf","xlcc","pyfs","zslb","dxhwpdw","sfzz",
					"sfsf","sfdlxy","syds","sydshi","sydx"};
			sql = jyglUtil.updateSqlForModel(input, model, tableName, "xh", pk);
			result = StandardOperation.updateNoLog(sql);
		}else {
			input = new String[]{"xh","xm","xb","sfzh","csny","mz","zzmm","lxdz","yzbm","lxdh","sjhm","email","xynx",
					"xxdm","xydm","zydm","bjdm","rxnf","bynf","xlcc","pyfs","zslb","dxhwpdw","sfzz",
					"sfsf","sfdlxy","syds","sydshi","sydx","bjmc"};
			sql = jyglUtil.insertSqlForModel(input, model, tableName);
			result = dao.insert(sql, new String[]{});
		}
		
		return result;
	}
	
	/**
	 * Dao_ jycczm is exists.
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public boolean dao_JycczmIsExists(String xh) throws Exception {
		DAO dao = DAO.getInstance();
		boolean result = false;
		String sql = "";
		sql = "select xh from nbtysysbb where xh='"+xh+"'";
		String[] outputValue = new String[]{"xh"};
		HashMap<String, String> map = dao.getMap(sql, new String[]{}, outputValue);
		if (map == null || map.size()<1) {
			result = true;
		}
		return result;
	}
	
	/**
	 * Dao_ jycczm update.教育储蓄证明更新
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public boolean dao_JycczmUpdate(SyxxModel model,String tableName) throws Exception {
//		DAO dao = DAO.getInstance();
		boolean result = false;
		String sql = "";
//		StringBuffer buffer = new StringBuffer();
//		buffer.append(model.getXh())
//			  .append(model.getXn())
//			  .append(model.getXq())
//			  .append(model.getZqsj());
		String[] input = null;
		if(StringUtils.isNotNull(model.getXh())){
			input = new String[]{"xh","xm","xb","sfzh","csny","mz","zzmm","lxdz","yzbm","lxdh","sjhm","email","xynx",
					"xxdm","xydm","zydm","bjdm","rxnf","bynf","xlcc","pyfs","zslb","dxhwpdw","sfzz",
					"sfsf","sfdlxy","syds","sydshi","sydx"};
			sql = jyglUtil.updateSqlForModel(input, model, tableName, "xh", model.getXh());
			result = StandardOperation.updateNoLog(sql);
		}
		return result;
	}
	
	/**
	 * Dao_ jycczm query.教育储蓄证明查询
	 * 
	 * @param model the model
	 * @param tableName the table name
	 * @param colList the col list
	 * 
	 * @return the array list< string[]>
	 * 
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws SecurityException the security exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException the no such method exception
	 */
	public ArrayList<String[]> dao_JycczmQuery(SyxxModel model,String tableName,String[] colList) throws IllegalArgumentException, 
			SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String xm = model.getXm();
		String xh = model.getXh();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
//		String xxsh = model.getXxsh();
		
		StringBuffer query = new StringBuffer();
		String[] queryColList = {"xh","xydm","zydm","bjdm"};
		query = FormModleCommon.makeQuery(queryColList, model);
		
		query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
		query.append(Base.isNull(xh) ? " and 1=1" : " and xh='"+xh.trim()+ "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm.trim()+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm.trim()+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm.trim()+ "'");
//		query.append(Base.isNull(xxsh) ? " and 1=1" : " and xxsh='"+xxsh.trim()+ "'");	
		String sql = "(select * from "+tableName+" a "+query.toString()+") ";
		System.out.println(sql);
		return commonQuery(sql, query.toString(), new String[] {}, colList,"", model);	
	}
	
	/**
	 * Common query.
	 * 
	 * @param innerSql the inner sql
	 * @param query the query
	 * @param inPutList the in put list
	 * @param colList the col list
	 * @param sql the sql
	 * @param model the model
	 * 
	 * @return the array list< string[]>
	 * 
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws SecurityException the security exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException the no such method exception
	 */
	public static ArrayList<String[]> commonQuery(String innerSql,String query,String [] inPutList,
			String [] colList,String sql, Object model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DAO dao = DAO.getInstance();
    //    查询用 获得数组的通用方法
		ArrayList<String[]> rs = null;
		//取到colList的长度
		int size = colList.length-1;
		Class<?> myClass = model.getClass();
		Pages pages = (Pages)myClass.getMethod("getPages",(Class[]) null).invoke(model,(Object[]) null);
		String count = dao.getOneRs("select count(*) count from "+innerSql, inPutList, "count");
		pages.setMaxRecord(Integer.parseInt(StringUtils.isNull(count)?"0":count));
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select * from (select rownum r,");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(innerSql); 
//			sqlBuffer.append(query);
			sqlBuffer.append(")");
			sqlBuffer.append(" where r > "); 
			sqlBuffer.append(pages.getStart());
			sqlBuffer.append(" and r <= ");
			sqlBuffer.append((pages.getStart() + pages.getPageSize()));
			rs = dao.rsToVator(sqlBuffer.toString(), inPutList, colList);
		}else{
			rs = dao.rsToVator(sql, inPutList, colList);
		}
		return rs;
	}

	/**
	 * Gets the toptr title.
	 * 
	 * @param tableName the table name
	 * @param colList the col list
	 * 
	 * @return the toptr title
	 */
	public List<HashMap<String, String>> getToptrTitle(String tableName,String[] colList) {
			DAO dao = DAO.getInstance();
			if(!StringUtils.isArrayNotNull(colList)){
				colList = dao.getColumnName("select * from "+tableName);
			}
			String[] colListCN = dao.getColumnNameCN(colList, tableName);
			List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);
			return topTr;
	}
	
	/**
	 * Dao_idfor query.
	 * 
	 * @param pk the pk
	 * @param tableName the table name
	 * 
	 * @return the hash map< string, string>
	 */
	public HashMap<String, String> dao_idforQuery(String pk,String tableName){
		DAO dao = DAO.getInstance();
		String[] outputValue =  dao.getColumnName("select * from nbtysysbb");
		for (int i = 0; i < outputValue.length; i++) {
			outputValue[i] = outputValue[i].toLowerCase();
		}
		String[] inputValue = {pk};
		HashMap<String, String> map = dao.getMap("select * from nbtysysbb where xh = ?", inputValue, outputValue);
		return map;	
	}
	
	/**
	 * Dao_ all del list.删除
	 * 
	 * @param pks the pks
	 * @param tableName the table name
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public String dao_AllDelList(String pks,String tableName)
	throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from nbtysysbb where xh = '" + pk + "'";
			sb.append(sql);
			sb.append("!!");
		}
		pksql = sb.toString().split("!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}
	
	/**
	 * Dao_jycczm sh.审核
	 * 
	 * @param pks the pks
	 * @param userName the user name
	 * @param chkVal the chk val
	 * 
	 * @return the string
	 * 
	 * @throws Exception the exception
	 */
	public String dao_jycczmSh(String pks,String userName,String chkVal)
	throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!");
		String[] pksql = new String[] {};
		String curTime = getCurTime().substring(0,8);
		String xxsh = "";
		if ("tg".equals(chkVal)) {
			xxsh = "通过";
		} else {
			xxsh = "不通过";
		}
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "update czxx_jycyzmb set xxsh='"+xxsh+"',xxshr='"+userName+"',xxshsj='"+curTime+"' where xh||xn||xq||zqsj = '" + pk + "'";
			sb.append(sql);
			sb.append("!!");
		}
		pksql = sb.toString().split("!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}

	/**
	 * Dao_get xb list.获取性别
	 * 
	 * @return the list< hash map< string, string>>
	 */
	public List<HashMap<String,String>> dao_getXbList(){
		DAO dao = DAO.getInstance();
		return dao.getSexList();	
	}	
	
	/**
	 * Dao_get yh list.获取银行
	 * 
	 * @return the list< hash map< string, string>>
	 */
	public List<HashMap<String,String>> dao_getYhList(){
		DAO dao = DAO.getInstance();
		String sql = "select * from czxx_yhdmb";
		ArrayList<HashMap<String, String>> list = dao.getArrayList(sql, new String[]{}, new String[]{"yhdmid","yhdmmc"});
		return list;	
	}	
	
	/**
	 * Dao_get xq list.获取学期
	 * 
	 * @return the list< hash map< string, string>>
	 */
	public List<HashMap<String,String>> dao_getXqList(){
		DAO dao = DAO.getInstance();
		return dao.getXqList();	
	}	
	
	//获得当前数据库时间
	/**
	 * Gets the cur time.
	 * 
	 * @return the cur time
	 */
	public String getCurTime(){
		DAO dao = DAO.getInstance();
		String sql = "select to_char(sysdate,'yyyymmddhh24miss') sdate from dual";
		String curTime = "";
		curTime = dao.getOneRs(sql,new String[] {}, new String[] { "sdate" })[0];
		return curTime;
	}
	
	/**
	 * Gets the xsxx.
	 * 
	 * @param xh the xh
	 * 
	 * @return the xsxx
	 */
	public HashMap<String,String> getXsxx(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select a.xh,a.xm,a.xb,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj from view_xsxxb a where a.xh=?";	
		return dao.getMapNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 导出.
	 * 
	 * @param model the model
	 * @param sql the sql
	 * @param outputValue the output value
	 * 
	 * @return the hash map< string, object>
	 * 
	 * @throws Exception the exception
	 */
	public HashMap<String, Object> dao_expData(String sql,String[] outputValue,SyxxModel model) throws Exception {
		DAO dao = DAO.getInstance();
		
		Vector<Object> vec = new Vector<Object>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String ColumnNameCN[] = dao.getColumnNameCN(outputValue, "view_czxx_jycyzmb");
		map.put("ColumnNameCN", ColumnNameCN);
		vec.addAll(dao.rsToVator(sql, new String[] {}, outputValue));
		map.put("vec", vec);
		return map;
	}
	
	public List<HashMap<String, String>> getSydList(){
		DAO dao = DAO.getInstance();
		String sql = "select * from syddmb";
		ArrayList<HashMap<String, String>> list = dao.getArrayList(sql, new String[]{}, new String[]{"syddm","sydmc"});
		return list;
	}
}
