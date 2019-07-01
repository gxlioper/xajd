package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.xmgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.sun.tools.xjc.model.Model;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;

/** 
 * @功能描述：资助育人项目管理-项目管理dao
 * @author：Lu.Yao 【1271】
 * @date：2017-10-20 上午11:16:30 
 */
public class XmglDao extends SuperDAOImpl<ZzyrxmglActionForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("XG_ZZYR_FDFBB");
		super.setKey("fdfbid");
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZzyrxmglActionForm t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZzyrxmglActionForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.fdfbid,t1.fbrxh,t1.fdkm,t1.fdsj,t1.fbbz,t1.fblx, ");
		if("0".equals(t.getFblx())){
			sql.append(" (select count(*) from xg_zzyr_fdxxb b where b.fdfbid=t1.fdfbid and b.bfdrxh='"+user.getUserName()+"') sfsq, ");
		}else{
			sql.append(" (select count(*) from xg_zzyr_fdxxb b where b.fdfbid=t1.fdfbid and b.fdrxh='"+user.getUserName()+"') sffd, ");
		}
		sql.append(" t2.xm fbrxm,t2.xymc fbrxymc");
		sql.append(" from xg_zzyr_fdfbb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.fbrxh=t2.xh where exists ");
		sql.append("(select 1 from xg_zzyr_fdfbxyglb a where a.fdfbid = t1.fdfbid and a.kfxydm='");
		sql.append(user.getUserDep());
		sql.append("') and t1.fbrxh <> '"+user.getUserName()+"' ");
		if("1".equals(t.getFblx())){//已被别人同意辅导的不显示
			sql.append(" and not exists (select 1 from xg_zzyr_fdxxb c where c.fdfbid = t1.fdfbid and c.fdrxh <> '"+user.getUserName()+"' and c.shzt = '1') ");
		}else{//限定人数 = 已同意人数 的 辅导不显示
			sql.append(" and to_number(nvl(t1.xdrs,0)) > to_number((select count(*) from xg_zzyr_fdxxb d where d.fdfbid=t1.fdfbid and d.shzt='1')) order by t1.fbrxh asc");
		}
		sql.append(" ) a where 1 = 1");
		//sql.append(searchTjByUser);
		sql.append(" and fblx = '"+t.getFblx()+"' ");
		sql.append(searchTj);
		if("stu".equalsIgnoreCase(user.getUserStatus())){
			ArrayList<String> inputVs = new ArrayList<String>();
			if (StringUtils.isNotNull(t.getFdkm())) {
				sql.append(" and fdkm like ?");
				inputVs.add("%"+t.getFdkm()+"%");
			}
			return getPageList(t, sql.toString(), inputVs.toArray(new String[] {}));
		}else{
			String[] inputV = SearchService.getTjInput(t.getSearchModel());
			return getPageList(t, sql.toString(), inputV);
		}
	}

	/** 
	 * @description：查询单条记录
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-17 下午08:22:42 
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getModelMap(ZzyrxmglActionForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.fdfbid,t1.fbrxh,t1.fdkm,t1.fdsj,t1.fbbz,t1.fblx,t1.xdrs, ");
		sql.append(" t2.xm fbrxm,t2.xymc fbrxymc");
		sql.append(" from xg_zzyr_fdfbb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.fbrxh=t2.xh where t1.fdfbid = ?");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getFdfbid() });
	}

	/**
	 * @throws SQLException  
	 * @description：增加
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-18 上午09:35:41 
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertFdxx(ZzyrxmglActionForm model, User user) throws SQLException {
		String sql = "insert into xg_zzyr_fdxxb (fdfbid,fdrxh,bfdrxh) values (?,(select fbrxh from xg_zzyr_fdfbb where fdfbid=?),?)";
		return dao.insert(sql, new String[]{model.getFdfbid(),model.getFdfbid(),user.getUserName()});
	}

	/**
	 * @throws Exception  
	 * @description：删除
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-18 下午04:29:20 
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean deleteFdxx(ZzyrxmglActionForm model, User user) throws Exception{
		String sql = "delete from xg_zzyr_fdxxb where fdfbid = ? and bfdrxh= ?";
		return dao.runDelete(sql, new String[]{model.getFdfbid(),user.getUserName()}) > 0;
	}

	/**
	 * @throws SQLException  
	 * @description：同意辅导
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-18 下午04:42:23 
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertTyfdxx(ZzyrxmglActionForm model, User user) throws SQLException {
		String sql = "insert into xg_zzyr_fdxxb (fdfbid,bfdrxh,fdrxh,shzt,tysj) values (?,(select fbrxh from xg_zzyr_fdfbb where fdfbid=?),?,'1',?)";
		return dao.insert(sql, new String[]{model.getFdfbid(),model.getFdfbid(),user.getUserName(),DealString.getDateTime()});
	}

	/**
	 * @throws Exception  
	 * @description：取消同意辅导
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-18 下午04:44:47 
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean deleteTyfdxx(ZzyrxmglActionForm model, User user) throws Exception {
		String sql = "delete from xg_zzyr_fdxxb where fdfbid = ? and fdrxh= ?";
		return dao.runDelete(sql, new String[]{model.getFdfbid(),user.getUserName()}) > 0;
	}

	/** 
	 * @description：查询辅导报名人数
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 上午11:49:51 
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getFdbmrs(ZzyrxmglActionForm model) {
		String sql = "select count(*) bmrs from xg_zzyr_fdxxb where fdfbid = ? and shzt = '1'";
		return dao.getOneRs(sql, new String[]{model.getFdfbid()}, "bmrs");
	}

	/** 
	 * @description：查询辅导限定人数
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 上午11:50:16 
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getFdXdrs(ZzyrxmglActionForm model) {
		String sql = "select xdrs from xg_zzyr_fdfbb where fdfbid = ?";
		return dao.getOneRs(sql, new String[]{model.getFdfbid()}, "xdrs");
	}

	/** 
	 * @description：查询报名审核状态
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午01:48:02 
	 * @param model
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public String checkBmshzt(ZzyrxmglActionForm model,User user) {
		String sql = "select nvl(shzt,'2') shzt from xg_zzyr_fdxxb where fdfbid = ? and bfdrxh = ?";
		return dao.getOneRs(sql, new String[]{model.getFdfbid(),user.getUserName()}, "shzt");
	}

	/** 
	 * @description：查询辅导人数
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午01:58:18 
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public String checkFdrs(ZzyrxmglActionForm model) {
		String sql = "select count(*) fdrs from xg_zzyr_fdxxb where fdfbid = ? ";
		return dao.getOneRs(sql, new String[]{model.getFdfbid()}, "fdrs");
	}

	/** 
	 * @description：根据辅导记录判断能否撤销辅导
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午02:09:42 
	 * @param model
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public String checkFdjlBfcx(ZzyrxmglActionForm model) {
		String sql = "select count(*) fdjl from xg_zzyr_fdjlb t1 left join xg_zzyr_fdxxb t2 on " +
				"t1.fdxxid = t2.fdxxid where t2.fdfbid = ?";
		return dao.getOneRs(sql, new String[]{model.getFdfbid()}, "fdjl");
	}
	
}
