package com.zfsoft.xgxt.szdw.thjl.zdgzxsk;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xtwh.yhsjfw.YhsjfwService;

/** 
 * 重点关注学生库维护
 */
public class SzdwZdgzxskDao extends SuperDAOImpl<SzdwZdgzxskForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(SzdwZdgzxskForm t)
			throws Exception {
		//  自动生成方法存根
		return null;
	}

	/**
	 * 查询
	 */
	public List<HashMap<String, String>> getPageList(SzdwZdgzxskForm t, User user)
			throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		// 关注等级过滤
		String searchTjByUserZdgzxsk = getSearchTjByUserZdgzxsk(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_view_szdw_thjl t where 1=1 ");
		sql.append(searchTjByUserZdgzxsk);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 获得查询条件(用户身份)
	 */
	public static String getSearchTjByUserZdgzxsk(User user, String tableBm,
			String xydm, String bjdm) { 

		// 用户名
		String userName = user.getUserName();
		// 用户类型
		String userType = user.getUserType();
		// 用户所在部门
		String userDep = user.getUserDep();
		// 用户身份
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		///String yhsjfwSql = new YhsjfwService().getYhsjfw(user, tableBm, xydm, bjdm);
		//if(yhsjfwSql != null && !yhsjfwSql.equals("")){
		//	query.append(yhsjfwSql);
		//	query.append(" and ");
		//}
		
		if ("xy".equalsIgnoreCase(userStatus)) {// 访问用户为学院
			// 如果用户是学院，并且【职务】是【书记】或【学院书记】，则显示学院内的【五星】学生。
		//	if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
				query.append(xydm + " = '" + userDep + "' ");
				query.append(" and ");
		//	}
		
			query.append(" ( ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("gzdj= '五星' ");
			query.append(" and ");
			query.append(" exists (select 1 from fdyxxb where zgh='" + userName + "' and zw in (select zwdm from zwb where (zwmc='书记' or zwmc='学院书记'))) ");
			query.append(" ) ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			// 如果用户是班主任或辅导员兼班主任，则显示带班的【三星、四星、五星】学生；
			query.append(" exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "' ");
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("gzdj in ('三星','四星','五星') ");
			query.append(" ) ");

		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			// 如果用户是辅导员，则显示带班的【四星、五星】学生
			query.append(" exists (select 1 from fdybjb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "' ");
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("gzdj in ('四星','五星') ");
			query.append(" ) ");

		} else if ("jd".equalsIgnoreCase(userStatus)) {// 访问用户为辅导员兼班主任
			// 如果用户是班主任或辅导员兼班主任，则显示带班的【三星、四星、五星】学生；
			query.append(" (exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "' ");
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("gzdj in ('三星','四星','五星') ");
			query.append(" ) ");
			
			query.append(" or exists (select 1 from fdybjb z where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  z.bjdm ");
			query.append(" and z.zgh = '" + userName + "' ");
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("gzdj in ('四星','五星') ");
			query.append(" ) ");
			query.append(" ) ");

		}else{// 访问用户为学校（管理员）
			// 显示全部记录
			query.append(" ( ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("gzdj in ('三星','四星','五星') ");
			query.append(" ) ");
		}

		query.append(" )");
		return query.toString();
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		
	}

}
