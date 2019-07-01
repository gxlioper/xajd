package xgxt.pjpy.xbemy;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.base.BaseServicesUtil;
import xgxt.utils.String.StringUtils;

public class PjpyXbemyDAO  {
	DAO dao = DAO.getInstance();
	private String jsName = "zf01";//管理员
	/**
	 * @return 奖学金列表
	 */
	public ArrayList<HashMap<String, String>> getJxjList() {
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql = "select jxjdm,jxjmc from jxjdmb order by jxjdm";
		rs = dao.getArrayList(sql, new String[] {},
				new String[] { "jxjdm", "jxjmc" });
		return rs;
	}

	/**
	 * 这个方法只是供学院用户和管理员的查询 直接查询出相应班级、专业或学院的学生信息。如果学生已经审核通过，则显示通过标记
	 * 
	 * @param userName
	 *            当前用户的用户名
	 * @param pjpyModel
	 *            model
	 * @return
	 */
	public ArrayList<String[]> getXysbjxjSearch(String userName,
			PjpyXbemyXysbjxjModel pjpyModel) {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String sql = "SELECT a.xh||b.xn||b.jxjdm key, "
				+ "(case nvl(b.xysh,'未审核') when '通过' then '#99FFCC' when '未审核' then '#FFFFFF' end) bgcolor,"
				+ "a.xh,a.xm,a.zymc,a.bjmc,b.xysh FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
		StringBuffer whereSql = new StringBuffer(
				" ON A.XH=B.XH AND B.XN=? AND B.JXJDM=? WHERE 1=1 ");// 学年是必选项
		ArrayList<String> values = new ArrayList<String>();
		String xydm = pjpyModel.getXydm();
		String zydm = pjpyModel.getZydm();
		String bjdm = pjpyModel.getBjdm();
		String nj = pjpyModel.getNj();
		String xn = pjpyModel.getXn();
		String xmdm = pjpyModel.getXmdm();// 获得项目代码（奖学金的代码或荣誉称号的代码）
		// 输出的表列
		String[] opCols = { "key", "bgcolor", "xh", "xm", "zymc", "bjmc",
				"xysh" };
		if (!StringUtils.isNull(xn)) {// 学年是必选项
			values.add(xn);
		}
		if (!StringUtils.isNull(xmdm)) {// 奖学金是必选项
			values.add(xmdm);
		}
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and A.XYDM=? ");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and A.ZYDM=? ");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and A.BJDM=? ");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and A.NJ=? ");
			values.add(nj);
		}

		rs = dao.rsToVator(sql + whereSql, values.toArray(new String[0]), opCols);
		return rs;
	}

	/**
	 * @return 学院上报及学生处，教务处，财务处查询的表头
	 */
	public ArrayList<HashMap<String, String>> getXysbjxjSearchTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] opCols = { "xh", "xm", "zymc", "bjmc", "xysh" };
		String[] cnCols = { "学号", "姓名", "专业名称", "班级名称", "审核结果" };
		for (int i = 0; i < opCols.length; i++) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("en", opCols[i]);
			temp.put("cn", cnCols[i]);
			result.add(temp);
		}
		return result;
	}

	/**
	 * 审核的查询与审核操作界面的结果都通过这个方法获得 直接查询出相应班级、专业或学院的学生信息。如果学生已经审核通过，则显示通过标记
	 * 
	 * @param userName
	 *            当前用户的用户名
	 * @param pjpyModel
	 *            model
	 * @return
	 */
	public ArrayList<String[]> getShSearch(String userName, String userType,
			PjpyXbemyXysbjxjModel pjpyModel) {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String sql = "";
		String xydm = pjpyModel.getXydm();
		String zydm = pjpyModel.getZydm();
		String bjdm = pjpyModel.getBjdm();
		String nj = pjpyModel.getNj();
		String xn = pjpyModel.getXn();
		String xmdm = pjpyModel.getXmdm();// 获得项目代码（奖学金的代码或荣誉称号的代码）
		// 输出的表列
		String[] opCols = { "key", "bgcolor", "xh", "xm", "zymc", "bjmc", "sh" };
		// 根据用户组来判断用户的角色
		char js = BaseServicesUtil.checkUserToGroup(userName, "教务处") ? 'a'
				: (BaseServicesUtil.checkUserToGroup(userName, "财务处") ? 'b'
						: (BaseServicesUtil.checkUserToGroup(userName, "学生处") && !userName.equalsIgnoreCase(jsName)? 'c' //学生处
								: 'd')); //管理员
		// 分角色生成sql
		switch (js) {
		case 'a':// 教务处
		{
			sql = "SELECT a.xh||b.xn||b.jxjdm key,a.xh||b.xn||b.jxjdm key,(case nvl(b.jwcsh,'未审核') when '通过' then '#99FFCC' when '未审核' then '#FFFFFF'  end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.jwcsh sh FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			break;
		}
		case 'b':// 财务处
		{
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case nvl(b.cwcsh,'未审核') when '通过' then '#99FFCC' when '未审核' then '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.cwcsh sh FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			break;
		}
		case 'c':// 学生处
		{
			sql = "SELECT b.xh||b.xn||b.jxjdm key,decode(nvl(b.xscsh,'未审核'),'通过','#99FFCC','#FFFFFF') bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xscsh sh FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			break;
		}
		case 'd':// 管理员
		{
			opCols = new String[]{"key", "bgcolor", "xh", "xm", "zymc", "bjmc", "xysh", "jwcsh", "cwcsh",
			"xscsh", "zzjg" };
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case when (xysh = '通过' and xscsh = '通过' and jwcsh = '通过' and cwcsh = '通过') then '#99FFCC' else '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xysh,b.jwcsh,b.cwcsh,b.xscsh,(case when (xysh = '通过' and xscsh = '通过' and jwcsh = '通过' and cwcsh = '通过') then '通过' else '不通过' end) zzjg FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			//decode(nvl(b.jwcsh,'未审核'),'通过',decode(nvl(b.cwcsh,'未审核'),'已通过',decode(nvl(b.cwcsh,'未审核'),'已通过','#99CCFF','#FFFFFF'),'#FFFFFF'),'#FFFFFF')
			break;
		}
		}
		StringBuffer whereSql = new StringBuffer(" ON 1=1 AND A.XH=B.XH AND  B.XN=? and B.JXJDM=? where 1=1 and b.xysh='通过' ");//学院审核通过后其它处才可以审核
		ArrayList<String> values = new ArrayList<String>();
		if (!StringUtils.isNull(xn)) {// 学年是必选
			values.add(xn);
		}
		if (!StringUtils.isNull(xmdm)) {// 奖学金是必选
			values.add(xmdm);
		}

		if (!StringUtils.isNull(xydm)) {
			whereSql.append("and A.XYDM=?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append("and A.ZYDM=?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append("and A.BJDM=?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append("and A.NJ=?");
			values.add(nj);
		}
		rs = dao.rsToVator(sql + whereSql, values.toArray(new String[0]), opCols);
		return rs;
	}

	/**
	 * @return 管理员审核查看时各部门审核查询的表头
	 */
	public ArrayList<HashMap<String, String>> getAdminShTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		// 必须与方法getXysbjxjSearch中的输出表列一致
		String[] opCols = { "xh", "xm", "zymc", "bjmc", "xysh", "jwcsh", "cwcsh",
				"xscsh", "zzjg" };
		String[] cnCols = { "学号", "姓名", "专业名称", "班级名称", "学院审核", "教务处审核", "财务处审核",
				"学生处审核" ,"最终结果"};
		for (int i = 0; i < opCols.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", opCols[i]);
			map.put("cn", cnCols[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	
	/**
	 * 学院审核结果
	 * @param xn
	 * @param jxjdm
	 * @param keys
	 * @param shjg
	 * @return
	 */
	public boolean[] xyshResult(String xn, String jxjdm,
			String[] keys, String shjg) throws Exception {
		//keys中可能仅仅是学号，也可能是xh||xn||jxjdm
		//所以要进行保存前的检测
		boolean[] result = new boolean[keys.length];
		String tableName = "xsjxjb";
		String pk        = "xh||xn||jxjdm";
		String insertSql = "insert into xsjxjb(xh,xn,jxjdm,xysh) values(?,?,?,?)";
		String shjgTemp  = "tg".equalsIgnoreCase(shjg) ? "通过" : ("btg".equalsIgnoreCase(shjg) ? "不通过" : "未审核");
		String updateSql = "update xsjxjb set xysh=? where xh||xn||jxjdm = ?";
		boolean[] exists = dao.checkExists(tableName, pk, keys);
		for(int i=0;i<exists.length;i++){
			if(!exists[i]){//学生奖学金表中不存在相应记录
				String[] input = {keys[i],xn,jxjdm,shjgTemp};
				result[i]      = dao.runUpdate(insertSql, input);
			} else {//已经存在审核记录，修改审核状态
				String[] input = {shjgTemp ,keys[i]};
				result[i]      = dao.runUpdate(updateSql, input);
			}
		}
		return result;
	}
	/**
	 * 学生处审核
	 * @param xn
	 * @param jxjdm
	 * @param keys
	 * @param shjg
	 * @return
	 */
	public boolean[] xscshResult(String xn, String jxjdm,
			String[] keys, String shjg) throws Exception {
        //keys中可能仅仅是学号，也可能是xh||xn||jxjdm
		//所以要进行保存前的检测
		boolean[] result = new boolean[keys.length];
		String tableName = "xsjxjb";
		String pk        = "xh||xn||jxjdm";
		String insertSql = "insert into xsjxjb(xh,xn,jxjdm,xscsh) values(?,?,?,?)";
		String shjgTemp  = "tg".equalsIgnoreCase(shjg) ? "通过" : ("btg".equalsIgnoreCase(shjg) ? "不通过" : "未审核");
		String updateSql = "update xsjxjb set xscsh=? where xh||xn||jxjdm = ?";
		boolean[] exists = dao.checkExists(tableName, pk, keys);
		for(int i=0;i<exists.length;i++){
			if(!exists[i]){//学生奖学金表中不存在相应记录
				String[] input = {keys[i],xn,jxjdm,shjgTemp};
				result[i]      = dao.runUpdate(insertSql, input);
			} else {//已经存在审核记录，修改审核状态
				String[] input = {shjgTemp ,keys[i]};
				result[i]      = dao.runUpdate(updateSql, input);
			}
		}
		return result;
	}
	/**
	 * 教务处审核
	 * @param xn
	 * @param jxjdm
	 * @param keys
	 * @param shjg
	 * @return
	 */
	public boolean[] jwcshResult(String xn, String jxjdm,
			String[] keys, String shjg) throws Exception {
//		keys中可能仅仅是学号，也可能是xh||xn||jxjdm
		//所以要进行保存前的检测
		boolean[] result = new boolean[keys.length];
		String tableName = "xsjxjb";
		String pk        = "xh||xn||jxjdm";
		String insertSql = "insert into xsjxjb(xh,xn,jxjdm,jwcsh) values(?,?,?,?)";
		String shjgTemp  = "tg".equalsIgnoreCase(shjg) ? "通过" : ("btg".equalsIgnoreCase(shjg) ? "不通过" : "未审核");
		String updateSql = "update xsjxjb set jwcsh=? where xh||xn||jxjdm = ?";
		boolean[] exists = dao.checkExists(tableName, pk, keys);
		for(int i=0;i<exists.length;i++){
			if(!exists[i]){//学生奖学金表中不存在相应记录
				String[] input = {keys[i],xn,jxjdm,shjgTemp};
				result[i]      = dao.runUpdate(insertSql, input);
			} else {//已经存在审核记录，修改审核状态
				String[] input = {shjgTemp ,keys[i]};
				result[i]      = dao.runUpdate(updateSql, input);
			}
		}
		return result;
	}
	/**
	 * 财务处审核
	 * @param xn
	 * @param jxjdm
	 * @param keys
	 * @param shjg
	 * @return
	 */
	public boolean[] cwcshResult(String xn, String jxjdm,
			String[] keys, String shjg) throws Exception {
//		keys中可能仅仅是学号，也可能是xh||xn||jxjdm
		//所以要进行保存前的检测
		boolean[] result = new boolean[keys.length];
		String tableName = "xsjxjb";
		String pk        = "xh||xn||jxjdm";
		String insertSql = "insert into xsjxjb(xh,xn,jxjdm,cwcsh) values(?,?,?,?)";
		String shjgTemp  = "tg".equalsIgnoreCase(shjg) ? "通过" : ("btg".equalsIgnoreCase(shjg) ? "不通过" : "未审核");
		String updateSql = "update xsjxjb set cwcsh=? where xh||xn||jxjdm = ?";
		boolean[] exists = dao.checkExists(tableName, pk, keys);
		for(int i=0;i<exists.length;i++){
			if(!exists[i]){//学生奖学金表中不存在相应记录
				String[] input = {keys[i],xn,jxjdm,shjgTemp};
				result[i]      = dao.runUpdate(insertSql, input);
			} else {//已经存在审核记录，修改审核状态
				String[] input = {shjgTemp ,keys[i]};
				result[i]      = dao.runUpdate(updateSql, input);
			}
		}
		return result;
	}
	
	/**
	 * 学院奖学金审核结果查询
	 * @param pjpyModel
	 * @param jg
	 * @return
	 */
	public ArrayList<String[]> getJxjShJgSearch(PjpyXbemyXysbjxjModel pjpyModel, String jg) {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String sql = "";
		String xydm = pjpyModel.getXydm();
		String zydm = pjpyModel.getZydm();
		String bjdm = pjpyModel.getBjdm();
		String nj = pjpyModel.getNj();
		String xn = pjpyModel.getXn();
		String xmdm = pjpyModel.getXmdm();// 获得项目代码（奖学金的代码或荣誉称号的代码）
		String[] opCols = new String[]{"key", "bgcolor", "xh", "xm", "zymc", "bjmc", "xysh", "jwcsh", "cwcsh",
				"xscsh", "zzjg" };
		StringBuffer whereSql = null;//查询条件
		if (jg.equalsIgnoreCase("tg") && !jg.equalsIgnoreCase("")){//审核通过
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case when (xysh = '通过' and xscsh = '通过' and jwcsh = '通过' and cwcsh = '通过') then '#99FFCC' else '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xysh,b.jwcsh,b.cwcsh,b.xscsh,(case when (xysh = '通过' and xscsh = '通过' and jwcsh = '通过' and cwcsh = '通过') then '通过' else '不通过' end) zzjg FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			whereSql = new StringBuffer(" ON 1=1 AND A.XH=B.XH AND  B.XN=? and B.JXJDM=? where 1=1 and b.xysh='通过' and b.xscsh='通过' and jwcsh='通过' and cwcsh='通过'");
		}else if (jg.equalsIgnoreCase("btg") && !jg.equalsIgnoreCase("")){//未审核通过
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case when (xysh = '通过' and xscsh = '通过' and jwcsh = '通过' and cwcsh = '通过') then '#99FFCC' else '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xysh,b.jwcsh,b.cwcsh,b.xscsh,(case when (xysh = '通过' and xscsh = '通过' and jwcsh = '通过' and cwcsh = '通过') then '通过' else '不通过' end) zzjg FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			whereSql = new StringBuffer(" ON 1=1 AND A.XH=B.XH AND  B.XN=? and B.JXJDM=? where 1=1 and b.xysh='通过' and (((xscsh = '不通过' or xscsh = '未审核') or xscsh is null) or ((jwcsh = '不通过' or jwcsh = '未审核') or jwcsh is null) or ((cwcsh = '不通过' or cwcsh = '未审核') or cwcsh is null)) ");//审核未通过
		}else{//通过与未通过都显示
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case when (xysh = '通过' and xscsh = '通过' and jwcsh = '通过' and cwcsh = '通过') then '#99FFCC' else '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xysh,b.jwcsh,b.cwcsh,b.xscsh,(case when (xysh = '通过' and xscsh = '通过' and jwcsh = '通过' and cwcsh = '通过') then '通过' else '不通过' end) zzjg FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			whereSql = new StringBuffer(" ON 1=1 AND A.XH=B.XH AND  B.XN=? and B.JXJDM=? where 1=1 and b.xysh='通过' ");
		}//end if
		ArrayList<String> values = new ArrayList<String>();
		if (!StringUtils.isNull(xn)) {// 学年是必选
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(xmdm)) {// 奖学金是必选
			values.add(xmdm);
		}//end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append("and A.XYDM=?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append("and A.ZYDM=?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append("and A.BJDM=?");
			values.add(bjdm);
		}//end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append("and A.NJ=?");
			values.add(nj);
		}//end if
		rs = dao.rsToVator(sql + whereSql, values.toArray(new String[0]), opCols);
		return rs;
	}
	
	public ArrayList<String[]> getJxjShJgExp(PjpyXbemyXysbjxjModel pjpyModel, String jg) {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String sql = "";
		String xydm = pjpyModel.getXydm();
		String zydm = pjpyModel.getZydm();
		String bjdm = pjpyModel.getBjdm();
		String nj = pjpyModel.getNj();
		String xn = pjpyModel.getXn();
		String xmdm = pjpyModel.getXmdm();// 获得项目代码（奖学金的代码或荣誉称号的代码）
		String[] opCols = new String[]{"xh", "xm", "zymc", "bjmc", "xysh", "jwcsh", "cwcsh",
				"xscsh", "zzjg" };
		StringBuffer whereSql = null;//查询条件
		if (jg.equalsIgnoreCase("tg") && !jg.equalsIgnoreCase("")){//审核通过
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case when (xysh = '通过' and xscsh = '通过' and jwcsh = '通过' and cwcsh = '通过') then '#99FFCC' else '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xysh,b.jwcsh,b.cwcsh,b.xscsh,(case when (xysh = '通过' and xscsh = '通过' and jwcsh = '通过' and cwcsh = '通过') then '通过' else '不通过' end) zzjg FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			whereSql = new StringBuffer(" ON 1=1 AND A.XH=B.XH AND  B.XN=? and B.JXJDM=? where 1=1 and b.xysh='通过' and b.xscsh='通过' and jwcsh='通过' and cwcsh='通过'");
		}else if (jg.equalsIgnoreCase("btg") && !jg.equalsIgnoreCase("")){//未审核通过
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case when (xysh = '通过' and xscsh = '通过' and jwcsh = '通过' and cwcsh = '通过') then '#99FFCC' else '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xysh,b.jwcsh,b.cwcsh,b.xscsh,(case when (xysh = '通过' and xscsh = '通过' and jwcsh = '通过' and cwcsh = '通过') then '通过' else '不通过' end) zzjg FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			whereSql = new StringBuffer(" ON 1=1 AND A.XH=B.XH AND  B.XN=? and B.JXJDM=? where 1=1 and b.xysh='通过' and (((xscsh = '不通过' or xscsh = '未审核') or xscsh is null) or ((jwcsh = '不通过' or jwcsh = '未审核') or jwcsh is null) or ((cwcsh = '不通过' or cwcsh = '未审核') or cwcsh is null)) ");//审核未通过
		}else{//通过与未通过都显示
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case when (xysh = '通过' and xscsh = '通过' and jwcsh = '通过' and cwcsh = '通过') then '#99FFCC' else '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xysh,b.jwcsh,b.cwcsh,b.xscsh,(case when (xysh = '通过' and xscsh = '通过' and jwcsh = '通过' and cwcsh = '通过') then '通过' else '不通过' end) zzjg FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			whereSql = new StringBuffer(" ON 1=1 AND A.XH=B.XH AND  B.XN=? and B.JXJDM=? where 1=1 and b.xysh='通过' ");
		}//end if
		ArrayList<String> values = new ArrayList<String>();
		if (!StringUtils.isNull(xn)) {// 学年是必选
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(xmdm)) {// 奖学金是必选
			values.add(xmdm);
		}//end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append("and A.XYDM=?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append("and A.ZYDM=?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append("and A.BJDM=?");
			values.add(bjdm);
		}//end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append("and A.NJ=?");
			values.add(nj);
		}//end if
		rs = dao.rsToVator(sql + whereSql, values.toArray(new String[0]), opCols);
		return rs;
	}
}
