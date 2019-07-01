package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.fdxxgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;

/** 
 * @功能描述：资助育人项目管理-辅导信息管理dao
 * @author：Lu.Yao 【1271】
 * @date：2017-10-20 上午11:22:37 
 */
public class FdxxglDao extends SuperDAOImpl<ZzyrxmglActionForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("XG_ZZYR_FDXXB");
		super.setKey("fdxxid");
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
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.fdxxid,t1.fdfbid,t1.fdrxh,t1.bfdrxh,t1.xspjjg,t1.lspjjg,t1.tysj,t4.fdkm, ");
		sql.append(" t2.xm fdrxm,t2.xymc fdrxymc,");
		sql.append(" t3.xm bfdrxm,t3.xymc bfdrxymc,t2.xydm,t2.zydm,t2.bjdm,t2.nj");
		sql.append(" from xg_zzyr_fdxxb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.fdrxh=t2.xh ");
		sql.append(" left join view_xsxxb t3 on t1.bfdrxh=t3.xh ");
		sql.append(" left join xg_zzyr_fdfbb t4 on t1.fdfbid=t4.fdfbid");
		sql.append(" where t1.shzt = '1' ");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * @throws Exception  
	 * @description：填写评价
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午06:01:50 
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean addFdxxpj(ZzyrxmglActionForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_zzyr_fdxxb set lspjjg=? where fdxxid = ? ");
		return dao.runUpdate(sql.toString(), new String[]{model.getLspjjg(),model.getFdxxid()});
	}

	public List<HashMap<String, String>> getShPageList(
			ZzyrxmglActionForm t, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String userStatus =user.getUserStatus();
		// 用户所在部门
		String userDep = user.getUserDep();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.*,t4.fdkm, ");
		sql.append(" t2.xm fdrxm,t2.xymc fdrxymc,");
		sql.append(" t3.xm bfdrxm,t3.xymc bfdrxymc,t2.xydm,t2.zydm,t2.bjdm,t2.nj,t3.bjdm bjdm2,t3.xydm xydm2,case ");
		sql.append(" when t5.fff='1'and t6.fff is null then 'fdy'");
		sql.append(" when t5.fff is null and t6.fff='1' then 'bfdy'");
		sql.append(" when t5.fff='1'and t6.fff='1' then 'jdfdy' ");
		if ("xy".equalsIgnoreCase(userStatus)) {
			sql.append(" when (t5.fff is null and t6.fff is null) and t7.fff='1' and t8.fff is null then 'xy' ");
			sql.append(" when (t5.fff is null and t6.fff is null)and t7.fff is null and t8.fff='1' then 'bxy' ");
			sql.append(" when (t5.fff is null and t6.fff is null)and t7.fff='1' and t8.fff='1' then 'jdxy' ");
		}
		sql.append(" else 'xx' end shrxz");
		sql.append(" from xg_zzyr_fdxxb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.fdrxh=t2.xh ");
		sql.append(" left join view_xsxxb t3 on t1.bfdrxh=t3.xh ");
		sql.append(" left join xg_zzyr_fdfbb t4 on t1.fdfbid=t4.fdfbid");
		
		sql.append(" left join (select fdxxid,'1' fff from xg_zzyr_fdxxb a left join view_xsxxb b on a.fdrxh=b.xh where b.bjdm in (select bjdm from fdybjb where zgh='"+user.getUserName()+"')) t5 ");
		sql.append(" on t1.fdxxid=t5.fdxxid ");
		sql.append(" left join (select fdxxid,'1' fff from xg_zzyr_fdxxb a left join view_xsxxb b on a.bfdrxh=b.xh where b.bjdm in (select bjdm from fdybjb where zgh='"+user.getUserName()+"'))t6 ");
		sql.append(" on t1.fdxxid=t6.fdxxid ");
		if ("xy".equalsIgnoreCase(userStatus)) {
			sql.append(" left join (select fdxxid,'1' fff from xg_zzyr_fdxxb a left join view_xsxxb b on a.fdrxh=b.xh where b.xydm ='"+userDep+"') t7 ");
			sql.append(" on t1.fdxxid=t7.fdxxid ");
			sql.append("  left join (select fdxxid,'1' fff from xg_zzyr_fdxxb a left join view_xsxxb b on a.bfdrxh=b.xh where b.xydm ='"+userDep+"') t8");
			sql.append(" on t1.fdxxid=t8.fdxxid ");
		}
		sql.append(" where t1.shzt = '1' ");
		
		if ("xx".equalsIgnoreCase(userStatus)) {
			sql.append(" and t1.fdyshzt='1' and t1.bfdyshzt='1' ) a  where 1 = 1   ");
		}else if ("xy".equalsIgnoreCase(userStatus)) {
			sql.append(" and t1.xscshzt is null) a  where 1 = 1   ");
			sql.append(" and (a.xydm ='"+userDep+"' or a.xydm2 ='"+userDep+"') ");
		}else if ("bzr".equalsIgnoreCase(userStatus)) {
			sql.append(" and t1.xscshzt is null) a  where 1 = 1   ");
			sql.append("  and exists (select 1  from bzrbbb t5 where (a.bjdm = t5.bjdm or a.bjdm2 = t5.bjdm)");
			sql.append(" and zgh='"+user.getUserName()+"') ");
		}else if ("fdy".equalsIgnoreCase(userStatus)) {
			sql.append(" and t1.xscshzt is null) a  where 1 = 1   ");
			sql.append("  and exists (select 1  from fdybjb t5 where (a.bjdm = t5.bjdm or a.bjdm2 = t5.bjdm)");
			sql.append(" and zgh='"+user.getUserName()+"') ");
		}else if ("jd".equalsIgnoreCase(userStatus)) {//是班主任又是辅导员
			sql.append(" and t1.xscshzt is null) a  where 1 = 1   ");
			sql.append("  and exists (select 1  from fdybjb t5 where (a.bjdm = t5.bjdm or a.bjdm2 = t5.bjdm)");
			sql.append(" and zgh='"+user.getUserName()+"') ");
		}
		
	
		
		sql.append(" and 1 = 1  ");
//		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	public boolean updateShzt(ZzyrxmglActionForm model, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		String [] input =  new String[]{};
//		boolean isFdy = isFdy(model,user);
//		boolean isBFdy = isBFdy(model,user);
		String shrxz = model.getShrxz();
		if ("fdy".equals(shrxz)||"xy".equals(shrxz)) {
			sql.append("update xg_zzyr_fdxxb set fdrfdy=?,fdyshzt=? where fdxxid = ? ");
			input= new String[]{user.getUserName(),model.getShzt(),model.getFdxxid()};
		}else if("bfdy".equals(shrxz)||"bxy".equals(shrxz)){
			sql.append("update xg_zzyr_fdxxb set bfdrfdy=?,bfdyshzt=? where fdxxid = ? ");
			input= new String[]{user.getUserName(),model.getShzt(),model.getFdxxid()};
		}else if ("jdfdy".equals(shrxz)||"jdxy".equals(shrxz)) {
			sql.append("update xg_zzyr_fdxxb set fdrfdy=?,fdyshzt=?,bfdrfdy=?,bfdyshzt=? where fdxxid = ? ");
			input= new String[]{user.getUserName(),model.getShzt(),user.getUserName(),model.getShzt(),model.getFdxxid()};
		}else {//都没有就要看是否
			sql.append("update xg_zzyr_fdxxb set xscshr=?,xscshzt=? where fdxxid = ? ");
			input= new String[]{user.getUserName(),model.getShzt(),model.getFdxxid()};
		}
		return dao.runUpdate(sql.toString(), input);
	}

	public boolean isFdy(ZzyrxmglActionForm model, User user) {
		String sql = " select count(1) num from xg_zzyr_fdxxb t1 left join view_xsxxb t2 on t1.fdrxh=t2.xh where  bjdm in (select bjdm from fdybjb where zgh=?) and fdxxid=?" ;
		String num = dao.getOneRs(sql, new String[]{user.getUserName(),model.getFdxxid()}, "num");
		return Integer.valueOf(num)>0;
	}
	public boolean isBFdy(ZzyrxmglActionForm model, User user) {
		String sql = " select count(1) num from xg_zzyr_fdxxb t1 left join view_xsxxb t2 on t1.bfdrxh=t2.xh where  bjdm in (select bjdm from fdybjb where zgh=?) and fdxxid=?" ;
		String num = dao.getOneRs(sql, new String[]{user.getUserName(),model.getFdxxid()}, "num");
		return Integer.valueOf(num)>0;
	}
	

}
