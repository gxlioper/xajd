package com.zfsoft.xgxt.xtgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class LoginDao extends SuperDAOImpl {

	@Override
	protected void setTableInfo() {

	}

	@Override
	public List getPageList(Object t) throws Exception {
		return null;
	}

	@Override
	public List getPageList(Object t, User user) throws Exception {
		return null;
	}

	
	public String getStuUser(String userName,String password){
		
		String sql = "select count(1) c from xsmmb where xh = ? and mm = ?";
		return dao.getOneRs(sql, new String[]{userName,password}, "c");
	}
	public String getStuUserByUserName(String userName){
		String sql = "select xh userName from view_xsjbxx where xh = ?";
		return dao.getOneRs(sql, new String[]{userName}, "userName");
	}
	
	
	public String getTeaUser(String userName,String password){
		
		String sql = "select count(1) c from yhb where yhm = ? and kl = ?";
		return dao.getOneRs(sql, new String[]{userName,password}, "c");
	}
	
	
	public boolean updateStuPwd(String userName,String password) throws Exception{
		String sql = "update xsmmb set mm=? where xh = ?";
		return dao.runUpdate(sql, new String[]{password,userName});
	}
	
	public boolean updateTeaPwd(String userName,String password) throws Exception{
		String sql = "update yhb set kl=? where yhm = ?";
		return dao.runUpdate(sql, new String[]{password,userName});
	}
	
	
	/***查询教职工的功能菜单权限*****/
	public List<HashMap<String,String>> getTeaAuth(String zgh){
		String sql = "select dyym,dxq from view_yhqx_dj where yhm=? and dyym>' ' ";
		return dao.getListNotOut(sql, new String[]{zgh});
	}
	
	/***查询学生的功能菜单权限*****/
	public List<HashMap<String,String>> getStuAuth(){
		String sql = "select dyym,dxq from view_yhzqx_dj where zdm='6727' and dyym>' ' ";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	//移动学工首页我的应用加载数据
	public List<HashMap<String,String>> getIndexKjfs(String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.cdid, b.gnmktblj_yd, b.dyym_yd,b.gnmkmc_yd,b.dyym,'1' sfyx from ");
		sql.append(" xg_xtwh_ksdhb_yd a  inner join xg_xtwh_dhtp b on a.cdid = b.cdid ");
		sql.append("  where yhm = ?  and dyym_yd is not null order by a.xssx ");
		return dao.getListNotOut(sql.toString(), new String[]{yhm});
	}
	
	//移动学工排除已选应用的应用List
	public List<HashMap<String,String>> getAllKjfs(String yhm,String usertype){
		StringBuilder sql = new StringBuilder();
		sql.append(" select cdid,gnmktblj_yd,dyym_yd,gnmkmc_yd,dyym,'1' sfyx  from xg_xtwh_dhtp ");
		sql.append(" where gnmklx_yd =? and dyym_yd is not null and cdid not in (select cdid from xg_xtwh_ksdhb_yd ");
		sql.append(" where yhm=? )");
		if("1".equals(usertype)){
			sql.append(" and (dyym in(select dyym from view_yhqx where yhm='"+yhm+"' and dyym is not null) or dyym='1')");
		}		
		
		sql.append("order by cdid ");
		return dao.getListNotOut(sql.toString(), new String[]{usertype,yhm});
	}
	
	public List<HashMap<String,String>> getAllMyKjfs(String yhm,String usertype){
		StringBuilder sql = new StringBuilder();
		sql.append(" select cdid,gnmktblj_yd,dyym_yd,gnmkmc_yd,cdlx_yd  from xg_xtwh_dhtp ");
		sql.append(" where gnmklx_yd =? and dyym_yd is not null ");
		
		if("1".equals(usertype)){
			sql.append(" and (dyym in(select dyym from view_yhqx where yhm='"+yhm+"' and dyym is not null)or dyym='1')");
		}		
		sql.append("order by cdid ");
		return dao.getListNotOut(sql.toString(), new String[]{usertype});
	}
	
	//移动学工删除已选应用
	public boolean deleteKjfs(String yhm) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xtwh_ksdhb_yd where yhm=? ");
		return dao.runUpdate(sql.toString(), new String[]{yhm});
	}
	
	//移动学工保存应用
	public boolean insertKjfs(String yhm,String usertype,List<String[]> params) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xtwh_ksdhb_yd values (?,?,?,?,?,?) ");
		int[] result =  dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}
	
	//移动学工保存应用
	public HashMap<String,String> getUserInfoByUserName(String openid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ydxg_wechat_yhbdb where openid=?");
		return dao.getMapNotOut(sql.toString(), new String[]{openid});
	}
	public boolean insertOpenid(String openid,String username,String password) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into ydxg_wechat_yhbdb values(?,?,?)");
		return dao.runUpdate(sql.toString(), new String[]{username,password,openid});
	}
	public boolean deleteOpenid(String username) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  ydxg_wechat_yhbdb where username=?");
		return dao.runUpdate(sql.toString(), new String[]{username});
	}
	/**
	 * 
	 * @描述:判断用户是否属于本系统
	 * @作者：xiaixa [工号：1104]
	 * @日期：2016-5-19 下午03:07:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param userName
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String isInSystem(String userName) throws Exception{
		String yhexistSql = "select xh yhm from view_xsjbxx where xh=? union all select yhm from yhb where yhm=?";
		yhexistSql += " union all select sjh yhm from ydxg_xtwh_jzzcb where sjh=? ";
		return dao.getOneRs(yhexistSql, new String[]{userName,userName,userName}, "yhm");
	}
	//查看需要验证webservice的学校
	public String isCheckWs(String xxdm) throws Exception{
		String wsSql = "select xxdm from XG_JKRZXXB where xxdm=?";
		return dao.getOneRs(wsSql, new String[]{xxdm}, "xxdm");
	}
	
	/**
	 * 获取周次
	 * @return
	 */
	public String getWeek(){
		StringBuilder sql = new StringBuilder();
		sql.append("  select case when ys =0 then week else week+1 end week from ");
		sql.append("  (select trunc(num/7) week,mod(num,7) ys from(");
		sql.append(" select trunc(sysdate-to_date((select kxdyt from xtszb) ,'yyyy-mm-dd')) num from dual))");
		return dao.getOneRs(sql.toString(), new String[]{}, "week");
	}


	public String getParentUser(String userName, String password) {

		String sql = "select count(1) c from ydxg_xtwh_jzzcb where sjh = ? and kl = ? and shzt  = '1' ";
		return dao.getOneRs(sql, new String[]{userName,password}, "c");
	}

	public boolean updateParentPwd(String userName, String password) throws Exception {
		String sql = "update ydxg_xtwh_jzzcb set kl=? where sjh = ?";
		return dao.runUpdate(sql, new String[]{password,userName});
	}
}
