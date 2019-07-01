package xgxt.pjpy.whlgdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 武汉理工大学评奖评优DAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-01</p>
 */
public class PjpyWhlgDAO {
	ArrayList<String> values = new ArrayList<String>();
	DAO dao = DAO.getInstance();
	/**
	 * 综合素质测评查询条件及值
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql2(WhlgdxZhszcpModel zhszcpModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String nj = zhszcpModel.getNj();
		String xn = zhszcpModel.getXn();
		String xq = zhszcpModel.getXq();
		String xh = zhszcpModel.getXh();
		String xydm = zhszcpModel.getXydm();
		String zydm = zhszcpModel.getZydm();
		String bjdm = zhszcpModel.getBjdm();
		String nd = zhszcpModel.getNd();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}
		return whereSql;
	}
	
	/**
	 * 先进班级查询条件及值
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSqlXjbj(WhlgdxXjbjModel xjbjModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();		
		String xn = xjbjModel.getXn();
		String xq = xjbjModel.getXq();
		String nd = xjbjModel.getNd();
		String xydm = xjbjModel.getXydm();
		String zydm = xjbjModel.getZydm();
		String bjdm = xjbjModel.getBjdm();
		String nj = xjbjModel.getNj();
		String userName = xjbjModel.getUserName();
		
		boolean isFdy = xjbjModel.isFdy();
		
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}
		if(isFdy){
			whereSql.append(" and exists(select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh=?)");
			values.add(userName);
		}
		return whereSql;
	}
	
	
	
	/**
	 * 获取学生的基本信息
	 * @param xh HashMap<String, String>
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		String sql= "select a.xh,a.xm,a.xb,a.nj,a.bjdm,a.bjmc,a.zydm,a.zymc,a.xydm,a.xymc,a.xz,a.sjhm from view_xsxxb a where xh=?";
		String[] output = {"xh", "xm", "xb", "nj", "bjdm", "bjmc", "zydm", "zymc", "xydm", "xymc", "xz","sjhm"};
		return dao.getMap(sql, new String[]{xh}, output);
	}
	
	/**
	 * 获取综合素质测评查询结果表头
	 * @return List
	 * */
	public List getZhszcpTitle(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xh||xn||nd||xq", "nd", "xn", "xq", "xh", "xm", "bjmc", "dcj", "stszzf", "sztzzf","zhszcpzf"};
		String[] cnList = new String[]{"xh||xn||nd||xq", "年度", "学年" ,"学期","学号","姓名", "班级名称" ,"思想道德素质分数", "身体素质分数", "拓展素质分数","综合素质测评总分"};
		for(int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			list.add(tmpMap);
		}
		return list;
	}
	
	/**
	 * 综合素质查询结果
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZhszcpResult(WhlgdxZhszcpModel zhszcpModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xh||xn||nd||xq,nd,xn,xq,xh,xm,bjmc,dcj,stszzf,sztzzf,zhszcpzf from view_zhszcp where 1=1 ";
		String[] opList = new String[]{"xh||xn||nd||xq", "nd", "xn", "xq", "xh", "xm", "bjmc", "dcj", "stszzf", "sztzzf","zhszcpzf"};		
		StringBuffer whereSql = getWhereSql2(zhszcpModel);//查询条件
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 查询学生综合素质测评详细信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getZhszcpInfoByPk(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from view_zhszcp where xh||xn||nd||xq=?";
		String[] output = {"xh","xm","xb","nj","xymc","zymc","bjmc","xn","nd","xq","dcj","xxpjcj","xxpjcjpm","xxpjcjpmbl",
				 "stszzf","sztzzf","zhszcpzf","zhszcpcjpm","zhszcpcjpmbl","dkzdfs","wygjqk","bz"};
		
		map = dao.getMap(sql, new String[]{pkValue}, output);
		return map;
	}
	
	/**
	 * 保存综合素质测评信息
	 * @param model
	 * @param request
	 * @return boolean 
	 * @throws Exception 
	 * */
	public boolean saveZhszcp(WhlgdxZhszcpModel model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String[] columns = { "xh", "xn", "nd", "xq", "dcj", "xxpjcj", "xxpjcjpm", "xxpjcjpmbl", "stszzf",
				"sztzzf", "zhszcpzf", "zhszcpcjpm", "zhszcpcjpmbl", "dkzdfs", "wygjqk", "bz" };
		String[] values = null;
		
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String dcj = model.getDcj();
		String xxpjcj = model.getXxpjcj();
		String xxpjcjpm = model.getXxpjcjpm();
		String xxpjcjpmbl = model.getXxpjcjpmbl();
		String stszzf = model.getStszzf();
		String sztzzf = model.getSztzzf();
		String zhszcpzf = model.getZhszcpzf();
		String zhszcpcjpm = model.getZhszcpcjpm();
		String zhszcpcjpmbl = model.getZhszcpcjpmbl();
		String dkzdfs = model.getDkzdfs();
		String wygjqk = DealString.toGBK(model.getWygjqk());
		String bz = DealString.toGBK(model.getBz());
		
		values = new String[]{xh, xn, nd, xq, dcj, xxpjcj, xxpjcjpm, xxpjcjpmbl, stszzf,
				sztzzf, zhszcpzf, zhszcpcjpm, zhszcpcjpmbl, dkzdfs, wygjqk, bz};
		flag = StandardOperation.delete("zhszcp", "xh||xn||nd||xq", xh + xn + nd + xq, request);
		if (flag) {
			flag = StandardOperation.insert("zhszcp", columns,values, request);
		}
		return flag;
	}
	
	/**
	 * 删除学生综合素质测评信息
	 * @param String[] key
	 * @return String
	 * @throws Exception
	 * */
	public String zhszcpDel(String[] key, HttpServletRequest request) throws Exception{
		int del = 0;//删除记录数
		for (int i = 0; i < key.length; i++) {
			String whichpk = key[i];// 得到主键
			boolean bFlag = StandardOperation.delete("zhszcp", "xh||xn||nd||xq", whichpk, request);
			if (bFlag){//删除成功
				del++;
			}
		}
		return String.format("%1$s 条记录成功删除!", del);
	}
	
	/**
	 * 获取综合素质测评信息导出
	 * @param model
	 * @return List
	 * */
	public List getZhszcpToExp(WhlgdxZhszcpModel model){
		List list = new ArrayList<HashMap<String, String>>();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xn = model.getXn();
		String xq = model.getXq();
		String nd = model.getNd();
		String xh = model.getXh();
		String sql = "select xh, xm, nd, xn, xq, dcj, xxpjcj, xxpjcjpm, xxpjcjpmbl,stszzf, sztzzf, zhszcpzf, zhszcpcjpm," +
				     "zhszcpcjpmbl, dkzdfs, wygjqk from view_zhszcp where 1=1 ";
		String[] input = {"xh","xm","nd","xn","xq","dcj","xxpjcj","xxpjcjpm","xxpjcjpmbl", "stszzf","sztzzf","zhszcpzf",
				         "zhszcpcjpm","zhszcpcjpmbl","dkzdfs","wygjqk"};
		
		StringBuffer whereSql = new StringBuffer();
		ArrayList<String> values = new ArrayList<String>();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(nd)) {
			whereSql.append(" and nd = ?");
			values.add(nd);
		}
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}
		list = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{},input);
		return list;
	}
	
	/**
	 * 获取综合素质测评导出数据表头
	 * @return String[]
	 * */
	public String[] getZhszcpTop(){
		String[] cName =  {"xh","xm","nd","xn","xq","dcj","xxpjcj","xxpjcjpm","xxpjcjpmbl","stszzf","sztzzf","zhszcpzf","zhszcpcjpm",
						  "zhszcpcjpmbl","dkzdfs","wygjqk"};
		String tabName = "view_zhszcp";
		return dao.getColumnNameCN(cName, tabName);
	}
	
	/**
	 * 获取先进班级查询结果表头
	 * @return List
	 * */
	public List getXjbjTitle(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||xjbjlbdm", "xn", "xq", "nj", "xymc", "zymc", "bjmc","xjbjlbmc"};
		String[] cnList = new String[]{"xn||xq||bjdm||xjbjlbdm", "学年" ,"学期", "年级","学院名称","专业名称","班级名称", "先进班级类别" };
		for(int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			list.add(tmpMap);
		}
		return list;
	}
	
	
	/**
	 *先进班级查询结果
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getXjbjResult(WhlgdxXjbjModel xjbjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xq||bjdm||xjbjlbdm,xn,xq,nj,xymc,zymc,bjmc,xjbjlbmc from view_pjpy_xjbj a where 1=1 ";
		String[] opList = new String[]{"xn||xq||bjdm||xjbjlbdm", "xn", "xq", "nj", "xymc", "zymc", "bjmc", "xjbjlbmc"};		
		StringBuffer whereSql = getWhereSqlXjbj(xjbjModel);//查询条件
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * 查询先进班级详细信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXjbjInfoByPk(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from view_pjpy_xjbj where xn||xq||bjdm||xjbjlbdm=?";
		String[] output = {"xn","xq","xydm","xymc","zydm","zymc","bjdm","bjmc","xjbjlbdm","xjbjlbmc","bz"};
		
		map = dao.getMap(sql, new String[]{pkValue}, output);
		return map;
	}
	
	/**
	 * 保存先进班级信息
	 * @param model
	 * @param request
	 * @return boolean 
	 * @throws Exception 
	 * */
	public boolean saveXjbj(WhlgdxXjbjModel model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tabName = "pjpy_xjbjandwmsqb";
		String[] columns = {"xn","xq","bjdm","rychdm","bz"};
		String[] values = null;
		String xn = model.getXn();
		String xq = model.getXq();
		String bjdm = model.getBjdm();
		String xjbjlbdm = model.getXjbjlbdm();
		String bz = DealString.toGBK(model.getBz());
		
		values = new String[]{xn, xq, bjdm, xjbjlbdm, bz};
		flag = StandardOperation.delete(tabName, "xn||xq||bjdm||rychdm",xn + xq + bjdm + xjbjlbdm, request);
		if (flag) {
			flag = StandardOperation.insert(tabName, columns,values, request);
		}
		return flag;
	}
	
	/**
	 * 删除先进班级信息
	 * @param String[] key
	 * @return String
	 * @throws Exception
	 * */
	public String xjbjDel(String[] key, HttpServletRequest request) throws Exception{
		int del = 0;//删除记录数
		String tabName = "pjpy_xjbjandwmsqb";
		for (int i = 0; i < key.length; i++) {
			String whichpk = key[i];// 得到主键
			boolean bFlag = StandardOperation.delete(tabName, "xn||xq||bjdm||rychdm", whichpk, request);
			if (bFlag){//删除成功
				del++;
			}
		}
		return String.format("%1$s 条记录成功删除!", del);
	}
	
	/**
	 * 获取先进班级类别代码
	 * @return List
	 * */
	public List getXjbjlbList(){
		String sql = "select distinct xjbjlbdm,xjbjlbmc from xjbjlbdmb order by xjbjlbdm";
		return dao.getList(sql, new String[]{}, new String[]{"xjbjlbdm", "xjbjlbmc"});
	}
	
	
	/**
	 * 获取先进班级导出数据表头
	 * @return String[]
	 * */
	public String[] getXjbjTop(){
		String[] cName =  {"xymc","bjmc","xjbjlbmc"};
		String tabName = "view_pjpy_xjbj";
		return dao.getColumnNameCN(cName, tabName);
	}
	
	/**
	 * 先进班级信息导出
	 * @param model
	 * @return List
	 * */
	public List getXjbjToExp(WhlgdxXjbjModel model){
		List list = new ArrayList<HashMap<String, String>>();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xn = model.getXn();
		String xq = model.getXq();
		String sql = "select xymc,bjmc,xjbjlbmc from view_pjpy_xjbj";
		String[] input = {"xymc","bjmc","xjbjlbmc"};
		
		StringBuffer whereSql = new StringBuffer();
		ArrayList<String> values = new ArrayList<String>();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}
		list = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{},input);
		return list;
	}
	
	/**
	 * 根据学院查询分配名额信息
	 * @param xydm
	 * @return List
	 * */
	public List getFpxxByXydm(String xydm,String nd){
		List list = null;
		String sql = "select jxjdm,jxjmc,jxjrs,jxjje from (" +
               "select jxjdm,(select distinct jxjmc from jxjdmb b where a.jxjdm=b.jxjdm)jxjmc,max(jxjrs)jxjrs,(select max(jlje) from jxjdmb b where a.jxjdm=b.jxjdm)jxjje from xyjxjrs a" +  
               " where (jxjrs<>'0' or jxjrs is not null) and nd=? and bmdm=? group by jxjdm)";
		list = dao.getList(sql, new String[]{nd,xydm}, new String[]{"jxjdm","jxjmc","jxjrs","jxjje"});
		return list;
	}
	
	/**
	 * 根据学院查询奖学金信息
	 * @param xydm
	 * @return String
	 * */
	public String getJxjjeOfXy(String xydm,String nd){
		String sql = "select sum(to_number(jxjje)) jxjje from (select jxjdm,jxjmc,jxjrs,jxjje from (" +
               "select jxjdm,(select distinct jxjmc from jxjdmb b where a.jxjdm=b.jxjdm)jxjmc,max(jxjrs)jxjrs,(select max(jlje) from jxjdmb b where a.jxjdm=b.jxjdm)jxjje from xyjxjrs a" +  
               " where (jxjrs<>'0' or jxjrs is not null) and nd=? and bmdm=? group by jxjdm))";
		return dao.getOneRs(sql, new String[]{nd,xydm}, "jxjje");
	}
	
	/**
	 * 查询奖学金年度学年学院名称信息
	 * @param xydm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getNdAndXyxx(String xydm){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select jxjsqxn,jxjsqnd,jxjsqxq from xtszb";
		map = dao.getMap(sql, new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
		sql = "select xymc from view_njxyzybj where xydm=?";
		map.put("xymc",dao.getOneRs(sql, new String[]{xydm}, "xymc"));
		return map;
	}
	
	/**
	 * 查询表头字段
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 * */
	public List<HashMap<String, String>> getJxjsztjTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "jxjdm", "rownum", "xn", "jxjmc","tjzdm", "tj", };
		String[] cnList = new String[]{"pk", "jxjdm", "行号", "学年", "名称", "条件字段名", "条件"};
		for (int i=0; i<enList.length; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", enList[i]);
			tmp.put("cn", cnList[i]);
			topList.add(tmp);
		}
		return topList;
	}
	
	/**
	 * 获取奖学金设置条件
	 * @param xn
	 * @param jxjdm
	 * @param tableName
	 * @return List
	 * */
	public List getJxjsztj(String xn,String jxjdm,String jxjfl,String tableName){
		List<String[]> jxjsztjList = new ArrayList<String[]>();
		String sql = "";
		if(tableName != null && tableName.equalsIgnoreCase("jxjdmb")){//奖学金
			sql = "select xn||jxjdm||tjzdm pk,jxjdm,(select distinct jxjmc from jxjdmb c where a.jxjdm=c.jxjdm)jxjmc,rownum,xn,(case when tjzdm='dcj' then '思想道德素质分数' when tjzdm='xxpjcj' then '学习平均成绩' when tjzdm='sztzzf' then '素质拓展分数' when tjzdm='dkzdfs' then '单科最低分数' when tjzdm='zhszcpcjpmbl' then '综合测评成绩排名比例' when tjzdm='xxpjcjpmbl' then '学习平均成绩排名比例' when tjzdm='wygjqk' then '外语过级情况' when tjzdm='sfpks' then '是否贫困生' end) tjzdm, zdcz||tj tj from jxjhdtj a where 1=1 ";	
		}else if (tableName != null && tableName.equalsIgnoreCase("rychdmb")){//荣誉称号			
			sql = "select xn||rychdm||tjzdm pk,rychdm jxjdm,(select rychmc from rychdmb c where a.rychdm=c.rychdm)jxjmc,rownum,xn,(case when tjzdm='dcj' then '思想道德素质分数' when tjzdm='xxpjcj' then '学习平均成绩' when tjzdm='sztzzf' then '素质拓展分数' when tjzdm='dkzdfs' then '单科最低分数' when tjzdm='zhszcpcjpmbl' then '综合测评成绩排名比例' when tjzdm='xxpjcjpmbl' then '学习平均成绩排名比例' when tjzdm='wygjqk' then '外语过级情况' when tjzdm='sfpks' then '是否贫困生' end) tjzdm, zdcz||tj tj from rychhdtj a where 1=1 ";
		}
		
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn='");
			whereSql.append(xn);
			whereSql.append("'");
		}
		if (!StringUtils.isNull(jxjdm) && tableName != null && !jxjdm.equalsIgnoreCase("null") && tableName.equals("jxjdmb")) {
			whereSql.append(" and jxjdm='");
			whereSql.append(jxjdm);
			whereSql.append("'");
		}
		if (!StringUtils.isNull(jxjdm) && tableName != null && !jxjdm.equalsIgnoreCase("null") && tableName.equals("rychdmb")) {
			whereSql.append(" and rychdm='");
			whereSql.append(jxjdm);
			whereSql.append("'");
		}
		if (!StringUtils.isNull(jxjfl) && tableName != null && !jxjfl.equalsIgnoreCase("null") && tableName.equals("jxjdmb")) {
			whereSql.append(" and exists(select 1 from jxjdmb b where a.jxjdm=b.jxjdm and b.jxjfl='");
			whereSql.append(jxjfl);
			whereSql.append("')");
		}
		if (!StringUtils.isNull(jxjfl) && tableName != null && !jxjfl.equalsIgnoreCase("null") && tableName.equals("rychdmb")) {
			whereSql.append(" and esists(select 1 from rychdmb b where a.rychdm=b.rychdm and b.rychfl='");
			whereSql.append(jxjfl);
			whereSql.append("')");
		}
		jxjsztjList = dao.rsToVator(sql + whereSql, new String[]{}, new String[]{"pk", "jxjdm","rownum","xn","jxjmc" , "tjzdm", "tj"});
		return jxjsztjList;
	}
	
	/**
	 * 获取奖学金分类列表
	 * @param tableName
	 * @return List
	 * */
	public List getJxjflList(String tableName){
		String sql = "";
		//奖学金
		sql = "select distinct JXJFL jxjfldm,JXJFL jxjflmc from jxjdmb";
		if(tableName!=null && tableName.equalsIgnoreCase("rychdmb")){//荣誉称号
			sql = "select distinct rychfl jxjfldm,rychfl jxjflmc from rychdmb";
		}
		List jxjflList = dao.getList(sql, new String[]{}, new String[]{"jxjfldm","jxjflmc"});		
		return jxjflList;
	}
	
	/**
	 * 获取奖学金列表
	 * @param jxjfl
	 * @param tableName 
	 * @return List
	 * */
	public List getJxjList(String jxjfl,String tableName){
		String sql = "";
		if(jxjfl!=null && !jxjfl.equalsIgnoreCase("") && !jxjfl.equalsIgnoreCase("null")){
			sql = "select distinct jxjdm,jxjmc from jxjdmb where jxjfl='" + jxjfl + "' order by jxjdm";
			if(tableName != null && tableName.equalsIgnoreCase("rychdmb")){
				sql = "select distinct rychdm jxjdm,rychmc jxjmc from rychdmb where rychfl='" + jxjfl + "' order by rychdm";
			}
		}else{
			sql = "select distinct jxjdm,jxjmc from jxjdmb order by jxjdm";
			if(tableName != null && tableName.equalsIgnoreCase("rychdmb")){
				sql = "select distinct rychdm jxjdm,rychmc jxjmc from rychdmb order by rychdm";
			}
		}
		return dao.getList(sql, new String[]{}, new String[]{"jxjdm","jxjmc"});
	}
	
	/**
	 * 根据代码获取奖学金或荣誉称号的名称
	 * @param tableName
	 * @param jxjdm
	 * @return String
	 * */	
	public String getJxjmc(String tableName, String jxjdm){
		String sql = "";
		if(tableName!=null && tableName.equalsIgnoreCase("jxjdmb")){			
			sql = "select jxjmc from jxjdmb where jxjdm=?";
		}else if (tableName!=null && tableName.equalsIgnoreCase("rychdmb")){
			sql = "select rychmc jxjmc from rychdmb where rychdm=?";
		}		
		return dao.getOneRs(sql, new String[]{jxjdm}, "jxjmc");
	}
	
	/**
	 * 保存条件设置信息
	 * @param model
	 * @return boolean 
	 * */
	@SuppressWarnings("unchecked")
	public String saveTjsz(PjpyWhlgdxForm model,String tableName){
		String flag = "true";
		String[] cztj = model.getCztj();
		String dcj = model.getDcj();
		String xxpjcj = model.getXxpjcj();
		String sztzzf = model.getSztzzf();
		String dkzdfs = model.getDkzdfs();
		String zhszcpcjpmbl = model.getZhszcpcjpmbl();  
		String xxpjcjpmbl = model.getXxpjcjpmbl();
		String wygjqk = DealString.toGBK(model.getWygjqk());
		String sfpks = DealString.toGBK(model.getSfpks());
		String xn = model.getXn();
		String jxjdm = model.getJxjdm();
		String jxjfl = model.getJxjfl();
		
		String[] cztjV = new String[cztj.length+2];
		for(int i=0; i<cztj.length; i++){
			cztjV[i] = cztj[i];
		}
		cztjV[cztj.length] = "=";//外语过级情况
		cztjV[cztj.length+1] = "="; //是否贫困生
		
		
		String[] value = {dcj,xxpjcj,sztzzf,dkzdfs,zhszcpcjpmbl,xxpjcjpmbl,wygjqk,sfpks};
		String[] input = {"dcj","xxpjcj","sztzzf","dkzdfs","zhszcpcjpmbl","xxpjcjpmbl","wygjqk","sfpks"};
		String[] sqlV = new String[value.length];
		String tjzddyb = "zhszcp";
		String sql = "";
		String tempSql = "";
		String whereSql = " where 1=1 ";		
		List jxjList = null;
		
		//判断是对哪个项目的条件设置
		if(tableName.equals("jxjdmb")){//奖学金
			sql = "insert into jxjhdtj(JXJDM,TJZDM,TJZDLYB,TJ,ZDCZ,XN) values(";
			tempSql = "select jxjdm from jxjdmb ";
			//查询奖学金代码列表条件
			if(jxjdm!=null && !jxjdm.equals("")){
				whereSql += " and jxjdm=" + jxjdm;
			}
			if(jxjfl!=null && !jxjfl.equalsIgnoreCase("")){
				whereSql += " and jxjfl=" + DealString.toGBK(jxjfl);
			}
			tableName = "jxjhdtj";
		}else if (tableName.equalsIgnoreCase("rychdmb")){
			sql = "insert into rychhdtj(RYCHDM,TJZDM,TJZDLYB,TJ,ZDCZ,XN) values(";//荣誉称号
			tempSql = "select rychdm jxjdm from rychdmb ";
			if(jxjdm!=null && !jxjdm.equals("")){
				whereSql += " and rychdm=" + jxjdm;
			}
//			if(jxjfl!=null && !jxjfl.equalsIgnoreCase("")){
//				whereSql += " and rychfl=" + DealString.toGBK(jxjfl);
//			}
			tableName = "rychhdtj";
		}
		
		
		jxjList = dao.getList(tempSql + whereSql, new String[]{}, new String[]{"jxjdm"});
		
		//组合sql 语句加入到数组中
		try {
		boolean dl = dao.runUpdate2("delete from " + tableName + whereSql, new String[]{});
		for(int j=0; j<jxjList.size(); j++){
			HashMap<String, String> jxjdmV = (HashMap<String, String>)jxjList.get(j);		
			
				if(dl){
					StringBuffer sb = new StringBuffer();
					for(int i=0; i<value.length; i++){						
						if(cztjV[i]!=null && !cztjV[i].equals("") && value[i]!=null && !value[i].equals("")){							
							sb.append(sql);
							sb.append("'" + (jxjdmV.get("jxjdm") ==null ? "" : jxjdmV.get("jxjdm")) + "',");//奖学金代码			
							sb.append("'" + input[i] + "',");
							sb.append("'" + tjzddyb + "',");
							sb.append("'" + (value[i]==null ? "" : value[i]) + "',");
							sb.append("'" + (cztjV[i]==null ? "" : cztjV[i]) + "',");
							sb.append("'" + (xn==null ? "" : xn) + "')");
							sb.append("!!##!!");
						}
						
					}	
					sqlV = sb.toString().split("!!##!!");
					int[] result = dao.runBatch(sqlV);
					for(int m=0; m<result.length; m++){
						if(result[m]<1){
							flag = "false";
							break;
						}
					}
				}
		}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}	
		return flag;
	}
	
	/**
	 * 查出设置条件
	 * @param tableName
	 * @param pkValue
	 * @param request
	 * @return boolean 
	 * */
	public boolean deleteTjsz(String tableName,String pkValue,HttpServletRequest request){
		boolean flag = false;
		String primaryKey = "";
		if(tableName != null && tableName.equals("jxjdmb")){
			primaryKey = "xn||jxjdm||tjzdm";
			tableName = "jxjhdtj";
		}
		if(tableName != null && tableName.equals("rychdmb")){
			primaryKey = "xn||rychdm||tjzdm";
			tableName = "rychhdtj";
		}
		try {
			flag = StandardOperation.delete(tableName, primaryKey, pkValue, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 获取奖学金申请学年学期学生的综合设置测评信息
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return List
	 * */
	public HashMap<String, String> getStuZhszcpxx(String xh,String xn,String xq){
		String outputValue[] = new String[]{"xh","xm","xb","xymc","zymc","bjmc","nj",
				 "dcj","xxpjcj","xxpjcjpm","xxpjcjpmbl","stszzf","sztzzf","zhszcpzf",
				 "zhszcpcjpm","zhszcpcjpmbl","dkzdfs","wygjqk"};
		String sql = "select * from view_zhszcp where xh=? and xn=? and xq=?";
		return dao.getMap(sql, new String[]{xh,xn,xq}, outputValue);
	}
	
	/**
	 * 判断学生是否是贫困生
	 * @param xh
	 * @return boolean
	 * */
	public boolean isKns(String xh){
		return dao.isKns(xh);		
	}
	
	/**
	 * 奖学金申请保存
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveJxjsq(WhlgJxjModel model,HttpServletRequest request){
		boolean flag = false;
		String tableName = "xsjxjb";
		String xh = model.getXh();
		String sqly = DealString.toGBK(model.getSqly());
		String jxjdm = model.getJxjdm();
		String lwmc = DealString.toGBK(model.getLwmc());
		String qkmc = DealString.toGBK(model.getQkmc());
		String fbsj = model.getFbsj();
		String sfdyzz = DealString.toGBK(model.getSfdyzz());
		String gkfs = model.getGkfs();
		String gzashjqk = DealString.toGBK(model.getGzashjqk());
//		String sbdj = DealString.toGBK(model.getSbdj());
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String tjFlag = DealString.toGBK(model.getTjFlag());
		String sqsj = dao.getOneRs("select to_char(sysdate,'yyyymmddhh24miss') sj from dual", new String[]{}, "sj");		
		
		String[] columns = {"xh","jxjdm","xn","nd","xq","lwmc","qkmc","fbsj","sfdyzz","gkfs","gzashjqk","sqsj","sqly","tjflag"};
		String[] values = {xh, jxjdm, xn, nd, xq, lwmc, qkmc, fbsj, sfdyzz, gkfs, gzashjqk, sqsj, sqly,tjFlag};
		
		try {
			flag = StandardOperation.delete("xsjxjb", "xh||jxjdm||nd||xn||xq", xh+jxjdm+nd+xn+xq, request);
			if(flag){		
				flag = StandardOperation.insert(tableName, columns, values, request);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 根据项目代码获取项目的分类
	 * @param tableName
	 * @param xmdm
	 * @return String
	 * */
	public String getXmfl(String tableName,String xmdm){
		String sql = "";
		if(tableName!=null && tableName.equalsIgnoreCase("jxjdmb")){
			sql = "select jxjfl xmfl from jxjdmb where jxjdm=?";
		}else{
			sql = "select rychfl xmfl from rychdmb where rychdm=?";
		}
		return dao.getOneRs(sql, new String[]{xmdm},"xmfl");
	}
	
	/**
	 * 判断学生所在的班级是否是先进班级
	 * @param xh
	 * @param xn
	 * @param xjbjlb
	 * @return boolean
	 * */
	private boolean isXjbj(String xh,String xn,String xq,String xjbjlb){
		boolean flag = false;
		String sql = "select count(*) count from view_pjpy_xjbj where xn=? and xq=? and bjdm=(select bjdm from view_xsjbxx where xh=?) and xjbjlbmc=?";
		flag = Integer.parseInt(dao.getOneRs(sql, new String[]{xn,xq,xh,xjbjlb}, "count"))>0 ? true : false;
		return flag;
	}
	
	/**
	 * 检测条件
	 * @param xh
	 * @param jxjdm
	 * @return String
	 * */
	@SuppressWarnings("unchecked")
	public String checkDemand(String xh, String jxjdm,String tableName){
		String result = "";
		String xn = getNdAndXyxx("").get("jxjsqxn");
		String xq = getNdAndXyxx("").get("jxjsqxq");
		String sql = "select tjzdm,tj,zdcz from jxjhdtj where jxjdm=? and xn=?";
		if(tableName!=null && tableName.equalsIgnoreCase("rychdmb")){
			sql = "select tjzdm,tj,zdcz from rychhdtj where rychdm=? and xn=?";
		}
		List list = dao.getList(sql, new String[]{jxjdm,xn}, new String[]{"tjzdm","tj","zdcz"});
		sql = "select dcj,xxpjcj,sztzzf,dkzdfs,zhszcpcjpmbl,xxpjcjpmbl,wygjqk from zhszcp where xh=? and xn=?";
		HashMap<String, String> tmpMap = dao.getMap(sql, new String[]{xh,xn}, new String[]{"dcj","xxpjcj","sztzzf","dkzdfs","zhszcpcjpmbl","xxpjcjpmbl","wygjqk"});;
		StringBuffer sb = new StringBuffer();
		
		for(int i=0;i<list.size();i++){
			HashMap<String, String> map = (HashMap<String, String>) list.get(i);
			String zdm = map.get("tjzdm");
			
			if(!(map.get("tjzdm").equals("wygjqk") || map.get("tjzdm").equals("sfpks"))){//数字类的操作				
				int value = Integer.parseInt(tmpMap.get(zdm)==null || tmpMap.get(zdm).equals("") ? "0" : tmpMap.get(zdm));
				int tj = Integer.parseInt(map.get("tj"));
				if(tableName!=null && tableName.equalsIgnoreCase("rychdmb")){//荣誉称号
					if(zdm.equalsIgnoreCase("zhszcpcjpmbl") || zdm.equalsIgnoreCase("xxpjcjpmbl")){
						if(isXjbj(xh,xn,xq,"优秀班级")){//优秀班集休
							tj = tj+5;
						}
						if(isXjbj(xh,xn,xq,"标兵班级")){//标兵班级体
							tj = tj+10;
						}
					}
					
				}
				if(map.get("zdcz").equals(">=")){//大于等于
					if(!(value>=tj)){
						sb.append(zdm + ",");
					}					
				}
				if(map.get("zdcz").equals(">")){//大于
					if(!(value>tj)){
						sb.append(zdm + ",");
					}
				}
				if(map.get("zdcz").equals("=")){//等于
					if(value!=tj){
						sb.append(zdm + ",");
					}
				}
				if(map.get("zdcz").equals("<")){//小于
					if(!(value<tj)){
						sb.append(zdm + ",");
					}
				}
				if(map.get("zdcz").equals("<=")){//小于等于
					if(!(value<=tj)){
						sb.append(zdm + ",");
					}
				}
			}
			
			if(map.get("tjzdm").equals("wygjqk")){//字符类的操作 外语过级情况
				if(!map.get("tj").equals(tmpMap.get("wygjqk"))){
					sb.append(zdm + ",");
				}
			}
			if(map.get("tjzdm").equals("sfpks")){//字符类的操作 是否贫困生
				String sfpks = dao.isKns(xh) ? "是" : "否";
				if(!map.get("tj").equals(sfpks)){
					sb.append(zdm + ",");
				}
			}
			
		}
		if(sb!=null && !sb.toString().equals("")){
			sb.deleteCharAt(sb.length()-1);
		}
		String[] nameCN = sb.toString().split(",");
		nameCN = dao.getColumnNameCN(nameCN, "zhszcp");
		for(int j=0; j<nameCN.length; j++){
			if(nameCN[j].equals("sfpks")){
				nameCN[j] = "是否贫困生";
			}
			result += nameCN[j] + " ";
		}

		return result;
	}
	
	/**
	 * 检测奖学金超限人数
	 * @param model
	 * @return boolean 
	 * */
	public int checkPersonNumber(WhlgJxjModel model){
		//boolean flag = false;
		String bmlb = "";
		String xh = model.getXh();
		String jxjdm = model.getJxjdm();
		String xn = model.getXn();
		String nd = model.getNd();
		String userType = model.getUserType();
		String userOper = "";
		String sql = "";
		String rs = "";
		if(userType!=null && userType.equals("fdy")){
			userOper = "fdysh";
		}else if(userType!=null && userType.equals("xy")){
			userOper = "xysh";
		}else{
			userOper = "xxsh";
		}
		
		sql = "select distinct bmlb from xyjxjrs where jxjdm=? and xn=? and nd=? and jxjrs is not null";
		bmlb = dao.getOneRs(sql, new String[]{jxjdm,xn,nd}, "bmlb");//获取奖学金人数设置方式
		sql = "select count(*) count from view_xsjxjb where " + bmlb + "=(select "+ bmlb +" from view_xsjbxx where xh=?) and "+userOper+"='通过'";
		
		rs = dao.getOneRs(sql, new String[]{xh}, "count");
		int tgrs =  Integer.parseInt(rs==null || rs.equals("") ? "0" : rs);//该项奖学金已经审核通过的人数	
		
		sql = "select jxjrs from xyjxjrs where jxjdm=? and xn=? and nd=? and bmdm=(select "+ bmlb +" from view_xsjbxx where xh=?)";
		rs = dao.getOneRs(sql,new String[]{jxjdm,xn,nd,xh}, "jxjrs");
		int yqrs = Integer.parseInt(rs==null || rs.equals("") ? "0" : rs);//最终调整后的人数
		
		return yqrs-tgrs;
	}
	
	/**
	 * 判断学生是否为新生
	 * @param xh
	 * @return boolean
	 * */
	public boolean checkIsNewStu(String xh){
		boolean flag = false;
		String nd = Base.currNd;
		String nj = dao.getOneRs("select nj from view_xsjbxx where xh=?", new String[]{xh}, "nj");
		if(Integer.parseInt(nd)==Integer.parseInt(nj) || Integer.parseInt(nd)-1==Integer.parseInt(nj)){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 查询学生奖学金申请信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsjxjInfo(String pk,String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from view_xsjxjb where " + pk + " = ?";
		map = dao.getMap(sql, new String[]{pkValue}, new String[]{"xh","jxjdm","nd","xn","xq","lwmc","qkmc","fbsj","sfdyzz","gkfs","gzashjqk","sqly"});
		return map;
	}
	
	/**
	 * 获取审核列表
	 * @param num
	 * @return List
	 * */
	public List getChkList(int num){
		return dao.getChkList(num);
	}
	
	/**
	 * 获取学生申请奖学金的信息
	 * @param pkValue
	 * @param userType
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsjxjCheckInfo(String pkValue,String userType){
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = "xn||nd||xh||jxjdm";
		String[] output = {"pk","xh","xm","xb","nj","xn","nd","xq","xymc","zymc","bjmc","jxjdm","jxjmc","sqly","lwmc","qkmc","fbsj","sfdyzz","gkfs","gzashjqk",
				  "dcj","xxpjcj","xxpjcjpm","xxpjcjpmbl","stszzf","sztzzf","zhszcpzf","zhszcpcjpm","zhszcpcjpmbl","dkzdfs","wygjqk","xxshyj",
				  "tjflag","xyshyj","fdyyj","yesNo"};
		String sql = "";
		if(userType!=null && userType.equals("fdy")){//辅导员
			sql = "select xn||nd||xh||jxjdm pk,xh,xm,xb,nj,xn,nd,xq,xymc,zymc,bjmc,jxjdm,jxjmc,sqly,lwmc,qkmc,fbsj,sfdyzz,gkfs,gzashjqk," +
				  "dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl,stszzf,sztzzf,zhszcpzf,zhszcpcjpm,zhszcpcjpmbl,dkzdfs,wygjqk,xxshyj," +
				  "tjflag,xyshyj,fdyyj,fdysh yesNo from view_xsjxjb where " + pk + "=?";			
		}else if(userType!=null && userType.equals("xy")){//学院
			sql = "select xn||nd||xh||jxjdm pk,xh,xm,xb,nj,xn,nd,xq,xymc,zymc,bjmc,jxjdm,jxjmc,sqly,lwmc,qkmc,fbsj,sfdyzz,gkfs,gzashjqk," +
			  "dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl,sztzzf,stszzf,zhszcpzf,zhszcpcjpm,zhszcpcjpmbl,dkzdfs,wygjqk,xxshyj," +
			  "tjflag,xyshyj,fdyyj,xysh yesNo from view_xsjxjb where " + pk + "=?";	
		}else{//学校
			sql = "select xn||nd||xh||jxjdm pk,xh,xm,xb,nj,xn,nd,xq,xymc,zymc,bjmc,jxjdm,jxjmc,sqly,lwmc,qkmc,fbsj,sfdyzz,gkfs,gzashjqk," +
			  "dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl,stszzf,sztzzf,zhszcpzf,zhszcpcjpm,zhszcpcjpmbl,dkzdfs,wygjqk,xxshyj," +
			  "tjflag,xyshyj,fdyyj,xxsh yesNo from view_xsjxjb where " + pk + "=?";	
		}
		map = dao.getMap(sql, new String[]{pkValue}, output);
		return map;
	}
	
	/**
	 * 保存奖学金审核结果
	 * @param model
	 * @param request
	 * @return boolean 
	 * */
	public boolean saveCheckPrise(WhlgJxjModel model,HttpServletRequest request){
		boolean flag = false;
		String tableName = "xsjxjb";
		String primaryKey = "xn||nd||xh||jxjdm";
		String userType = model.getUserType();
		String pkValue = model.getPkVal();
		String yesNo = DealString.toGBK(model.getYesNo());
		String fdyyj = DealString.toGBK(model.getFdyyj());
		String xxshyj = DealString.toGBK(model.getXxshyj());
		String xyshyj = DealString.toGBK(model.getXyshyj());
		try {
			if(userType!=null && userType.equals("fdy")){//辅导员
				flag = StandardOperation.update(tableName, new String[]{"fdysh", "fdyyj"}, new String[]{yesNo,fdyyj}, primaryKey, pkValue, request);
				
			}else if(userType!=null && userType.equals("xy")){//学院
				flag = StandardOperation.update(tableName, new String[]{"xysh", "xyshyj"}, new String[]{yesNo,xyshyj}, primaryKey, pkValue, request);
			}else{//学校
				flag = StandardOperation.update(tableName, new String[]{"xxsh", "xxshyj"}, new String[]{yesNo,xxshyj}, primaryKey, pkValue, request);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 学生荣誉称号申请保存 
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveRychsq(WhlgJxjModel model,HttpServletRequest request){
		boolean flag = false;
		String tableName = "xsrychb";
		String primaryKey = "xh||xn||nd||xq";
		String xh = model.getXh();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String rychdm = model.getRychdm();
		String sfdlsq = DealString.toGBK(model.getSfdlsq());
		String dlsqly = DealString.toGBK(model.getDlsqly());
		String sqly = DealString.toGBK(model.getSqly());
		
		String value = xh+xn+nd+xq;
		String[] columns = {"xh","nd","xn","xq","rychdm","sfdlsq","dlsqly","sqly"};
		String[] values = {xh, nd, xn, xq, rychdm, sfdlsq, dlsqly, sqly};
		try {
			flag = StandardOperation.delete(tableName, primaryKey, value, request);
			if(flag){
				flag = StandardOperation.insert(tableName, columns, values, request);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * 查询学生荣誉称号审核信息
	 * @param pk
	 * @param pkValue
	 * @param userType
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsrychInfo(String pk, String pkValue,String userType){
		String sql = "";
		String[] output = {"pk","xh","xm","xb","nj","xymc","zymc","bjmc","xn","nd","xq","rychdm","rychmc","dcj","xxpjcj","xxpjcjpm","xxpjcjpmbl",
				"stszzf","sztzzf","zhszcpzf","zhszcpcjpm","zhszcpcjpmbl","dkzdfs","wygjqk","fdyyj","xyyj","xxyj","yesNo"};
		if(userType!=null && userType.equalsIgnoreCase("fdy")){//辅导员
			sql = "select xn||nd||xh||rychdm pk, xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xq,rychdm,rychmc,dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl," +
					"stszzf,sztzzf,zhszcpzf,zhszcpcjpm,zhszcpcjpmbl,dkzdfs,wygjqk,fdyyj,xyyj,xxyj,fdysh yesNo from view_xsrychb where " + pk + "=?";
		}else if (userType!=null && userType.equalsIgnoreCase("xy")){//学院
			sql = "select xn||nd||xh||rychdm pk, xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xq,rychdm,rychmc,dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl," +
			"stszzf,sztzzf,zhszcpzf,zhszcpcjpm,zhszcpcjpmbl,dkzdfs,wygjqk,fdyyj,xyyj,xxyj,xysh yesNo from view_xsrychb where " + pk + "=?";
		}else{//学校
			sql = "select xn||nd||xh||rychdm pk, xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xq,rychdm,rychmc,dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl," +
			"stszzf,sztzzf,zhszcpzf,zhszcpcjpm,zhszcpcjpmbl,dkzdfs,wygjqk,fdyyj,xyyj,xxyj,xxsh yesNo from view_xsrychb where " + pk + "=?";
		}		
		return dao.getMap(sql, new String[]{pkValue}, output);
	}
	
	/**
	 * 学生荣誉称号单个审核保存
	 * @param model
	 * @param request
	 * @return boolean s
	 * */
	public boolean saveCheckRych(WhlgJxjModel model, HttpServletRequest request){
		boolean flag = false;
		String pk = "xn||nd||xh||rychdm";
		String tableName = "xsrychb";
		String pkValue = model.getPkVal();
		String userType = model.getUserType();
		String[] columns = {"xxsh","xxshyj"};
		String[] values = null;
		
		String yesNo = DealString.toGBK(model.getYesNo());
		String fdyyj = DealString.toGBK(model.getFdyyj());
		String xyyj = DealString.toGBK(model.getXyshyj());
		String xxyj = DealString.toGBK(model.getXxshyj());
		
		values = new String[]{yesNo, xxyj};		
		if(userType!=null && userType.equalsIgnoreCase("fdy")){//辅导员
			columns = new String[]{"fdysh", "fdyyj"};
			values = new String[]{yesNo, fdyyj};
		}else if(userType!=null && userType.equalsIgnoreCase("xy")){//学院
			columns = new String[]{"xysh", "xyyj"};
			values = new String[]{yesNo, xyyj};
		}
		
		try{
			flag = StandardOperation.delete(tableName, pk, pkValue, request);
			if(flag){
				flag = StandardOperation.insert(tableName, columns, values, request);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 学生荣誉称号批量审核
	 * @param pkValue
	 * @param userType
	 * @param yesNo
	 * @param request
	 * */
	public boolean checkRychAll(String[] pkValue,String userType,String yesNo, HttpServletRequest request)throws Exception{
		String sj = DateUtils.getTime();
		boolean flag = false;
		String tableName = "xsrychb";		
		String primaryKey = "xn||nd||xh||rychdm";
		String[] columns = new String[]{"xxsh", "xxshsj"};
		if(yesNo.equals("pass")){
			yesNo = "通过";
		}else if(yesNo.equals("nopass")){
			yesNo = "不通过";
		}
		
		String xxdm = Base.xxdm;
		if (Globals.XXDM_WHLGDX.equalsIgnoreCase(xxdm)) {
			if(userType!=null && userType.equalsIgnoreCase("fdy")){
				columns = new String[]{"fdysh", "xyshsj"};
			}else if(userType!=null && userType.equalsIgnoreCase("xy")){
				columns = new String[]{"xysh", "xyshsj"};
			}
		} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			String[] sqlArr = new String[pkValue.length];
			for (int i=0;i<pkValue.length;i++) {
				StringBuffer sql = new StringBuffer("update xsrychb set xxsh='");
				sql.append(yesNo);
				sql.append("',xxshsj='");
				sql.append(sj);
				sql.append("' where xh||xn||rychdm='");
				sql.append(pkValue[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
			int[] result = dao.runBatch(sqlArr);
			return dao.checkBatch(result);
		} else {
			if("xy".equalsIgnoreCase(userType) || "fdy".equalsIgnoreCase(userType)){
				columns = new String[]{"xysh","xyshsj"};
			}
		}
		
		try{
			for(int i=0; i<pkValue.length; i++){
				flag = StandardOperation.update(tableName, columns, new String[]{yesNo,sj}, primaryKey, pkValue[i], request);
				if(flag == false){
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return flag;
	}
	
	/**
	 * 获取条件语句
	 * @param model
	 * @return String
	 * */
	public String getWhereSql(WhlgJxjModel model){
		String jxjdm = model.getJxjdm();
		String rychdm = model.getRychdm();
		String xh = model.getXh();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		if(xh!=null && !xh.equalsIgnoreCase("")){
			sb.append(" and xh='");
			sb.append(xh);
			sb.append("' ");			
		}
		if(xydm!=null && !xydm.equalsIgnoreCase("")){
			sb.append(" and xydm ='");
			sb.append(xydm);
			sb.append("' ");
		}
		if(zydm!=null && !zydm.equalsIgnoreCase("")){
			sb.append(" and zydm='");
			sb.append(zydm);
			sb.append("' ");
		}
		if(bjdm!=null && !bjdm.equalsIgnoreCase("")){
			sb.append(" and bjdm ='");
			sb.append(bjdm);
			sb.append("' ");
		}
		if(nj!=null && !nj.equalsIgnoreCase("")){
			sb.append(" and nj='");
			sb.append(nj);
			sb.append("' ");
		}
		if(xn!=null && !xn.equalsIgnoreCase("")){
			sb.append(" and xn='");
			sb.append(xn);
			sb.append("' ");
		}
		if(nd!=null && !nd.equalsIgnoreCase("")){
			sb.append(" and nd='");
			sb.append(nd);
			sb.append("' ");
		}
		if(xq!=null && xq.equalsIgnoreCase("")){
			sb.append(" and xq='");
			sb.append(xq);
			sb.append("' ");
		}
		if(jxjdm!=null && !jxjdm.equalsIgnoreCase("")){
			sb.append(" and jxjdm='");
			sb.append(jxjdm);
			sb.append("' ");
		}
		if(rychdm!=null && !rychdm.equalsIgnoreCase("")){
			sb.append(" and rychdm='");
			sb.append(rychdm);
			sb.append("' ");
		}
		return sb.toString();
	}
	
	/**
	 * 获取奖学金分类导出的查询语句 
	 * @param model
	 * @return String
	 * */
	public String getPriseExpSql(WhlgJxjModel model){
		String sql = "";
		String jxjdm = model.getJxjdm();
		String jxjfl = getXmfl("jxjdmb", jxjdm);
		
		if(jxjfl!=null && (jxjfl.equalsIgnoreCase("国家、学校奖学金") 
				|| jxjfl.equalsIgnoreCase("国家奖学金")
				|| jxjfl.equalsIgnoreCase("学校奖学金")
				|| jxjfl.equalsIgnoreCase("学校、国家奖学金"))){//国家、学校奖学金
			sql = "select xh,xm,dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl,stszzf," +
					"sztzzf,zhszcpzf,zhszcpcjpm,zhszcpcjpmbl,dkzdfs,wygjqk," +
					"jxjmc from view_xsjxjb ";
		}else if(jxjfl!=null && jxjfl.equalsIgnoreCase("社会奖学金")){//社会奖学金
			sql = "select xh,xm,jxjmc 社会奖学金 from view_xsjxjb ";
		}else if(jxjfl!=null && (jxjfl.equalsIgnoreCase("学术科研奖") || jxjfl.equalsIgnoreCase("学术科研奖学金"))){//学术科研奖
			sql = "select xh,xm,lwmc ,qkmc ,fbsj ,sfdyzz from view_xsjxjb ";
		}else if (jxjfl!=null && jxjfl.equalsIgnoreCase("新生奖学金")){//新生奖学金
			sql = "select xh,xm,gkfs,gzashjqk,jxjmc from view_xsjxjb ";
		}else if(jxjfl!=null && (jxjfl.equalsIgnoreCase("单项奖") || jxjfl.equalsIgnoreCase("单项奖学金"))){//单项奖
			sql = "select xh,xm,jxjmc 拟评何种单项奖 from view_xsjxjb ";
		}else{
			sql = "select * from view_xsjxjb ";
		}
		
		sql += getWhereSql(model);	//组合查询条件	
		return sql;
	}
	
	/**
	 * 获取荣誉称号分类导出的查询语句 
	 * @param model
	 * @return String
	 * */
	public String getRychExpSql(WhlgJxjModel model){
		String sql = "";
		String rychdm = model.getRychdm(); 
		String rychfl = getXmfl("rychdmb", rychdm);
		
		if(rychfl!=null && rychfl.equalsIgnoreCase("荣誉称号")){//荣誉称号
			sql = "select xh,xm,dcj,xxpjcj,xxpjcjpm,xxpjcjpmbl,stszzf,sztzzf,zhszcpzf,zhszcpcjpm," +
				  "zhszcpcjpmbl,dkzdfs,wygjqk,rychmc from view_xsrychb ";
		}else if(rychfl!=null && rychfl.equalsIgnoreCase("优秀毕业生")){//优秀毕业生
			sql = "select xh, xm, sqly, sfdlsq from view_xsrychb ";
		}else {
			sql = "select * from view_xsrychb ";
		}		
		sql += getWhereSql(model);	//组合查询条件	
		return sql;
	}
}
