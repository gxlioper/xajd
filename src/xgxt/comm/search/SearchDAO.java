package xgxt.comm.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xsgzgl.jxgl.general.jxjzgl.jxjzgl.JxjzglFrom;

import com.zfsoft.xgxt.xtwh.yhsjfw.YhsjfwService;
import common.Globals;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 高级查询_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SearchDAO extends CommDAO {

	/**
	 * 获得新学院列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getNewNjList(String userStatus,
			String userName, String userDep,String sfzxs) {
		DAO dao = DAO.getInstance();
		
		String tableName = Base.isHistory() ? "view_njxyzybj_all"
				: "view_njxyzybj";
		if("1".equals(sfzxs)){
			tableName="(select * from view_xsxxcxjg a where a.nj is not null and (a.sfzx = '在校' or a.sfzx is null))";
		}
		if("0".equals(sfzxs)){
			tableName="(select * from view_xsxxcxjg a where a.nj is not null and a.sfzx = '不在校')";
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.nj from " + tableName + " a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and (");

		String yhsjfwSql = new YhsjfwService().getYhsjfw(userName,userStatus, "a","xydm", "bjdm");
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			sql.append(yhsjfwSql);
		}
		
		if ("jd".equalsIgnoreCase(userStatus)) {
			// 兼得
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  (exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
			sql.append(" or exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
			
		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			// 辅导员
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			// 班主任
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			// 学院
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" a.xydm = '"+userDep+"' ");
			}
		} else if ("stu".equalsIgnoreCase(userStatus)) {
			// 学生
			sql.append(" exists(select 1 from view_xsjbxx b ");
			sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
		}else{
			//学校（管理员）
			//return Base.getNjList();
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" 1= 1 ");
			}
		}
		sql.append(" )");
		sql.append(" order by nj ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "nj", "nj"});

		return list;
	}
	public List<HashMap<String, String>> getXsNjList(String userStatus,
			String userName, String userDep,boolean sfzxs) {
		DAO dao = DAO.getInstance();
		
		String searchTj = sfzxs ? "and(a.sfzx = '在校' or a.sfzx is null)"
				: "and a.sfzx = '不在校'";
		
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.nj from view_xsxxcxjg a ");
		sql.append(" where 1 = 1 ");
		sql.append(searchTj);
		sql.append(" and (");

		String yhsjfwSql = new YhsjfwService().getYhsjfw(userName,userStatus, "a","xydm", "bjdm");
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			sql.append(yhsjfwSql);
		}
		
		if ("jd".equalsIgnoreCase(userStatus)) {
			// 兼得
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  (exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
			sql.append(" or exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
			
		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			// 辅导员
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			// 班主任
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			// 学院
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" a.xydm = '"+userDep+"' ");
			}
		} else if ("stu".equalsIgnoreCase(userStatus)) {
			// 学生
			sql.append(" exists(select 1 from view_xsjbxx b ");
			sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
		}else{
			//学校（管理员）
			//return Base.getNjList();
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" 1= 1 ");
			}
		}
		sql.append(" )");
		sql.append(" order by nj ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "nj", "nj"});

		return list;
	}
	
	/**
	 * 获得学院列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getXyList(String userStatus,
			String userName, String userDep,String sfzxs) {

		DAO dao = DAO.getInstance();
		
		String tableName = Base.isHistory() ? "view_njxyzybj_all"
				: "view_njxyzybj";
		if("1".equals(sfzxs)){
			tableName="(select * from view_xsxxcxjg a where a.xymc is not null and (a.sfzx = '在校' or a.sfzx is null))";
		}
		if("0".equals(sfzxs)){
			tableName="(select * from view_xsxxcxjg a where a.xymc is not null and a.sfzx = '不在校')";
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.xydm,a.xymc from " + tableName + " a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and (");

		String yhsjfwSql = new YhsjfwService().getYhsjfw(userName,userStatus, "a","xydm", "bjdm");
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			sql.append(yhsjfwSql);
		}
		
		if ("jd".equalsIgnoreCase(userStatus)) {
			// 兼得
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  (exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
			sql.append(" or exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
			
		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			// 辅导员
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			// 班主任
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			// 学院
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" a.xydm = '"+userDep+"' ");
			}
		} else if ("stu".equalsIgnoreCase(userStatus)) {
			// 学生
			sql.append("  exists(select 1 from view_xsjbxx b ");
			sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
		}else{
			//学校（管理员）
			//return Base.getXyList();
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" 1= 1 ");
			}
		}
		sql.append(" )");

		sql.append(" order by xydm ");
		//System.out.println(sql);
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "xydm", "xymc"});

		return list;

	}
	public List<HashMap<String, String>> getXsXyList(String userStatus,
			String userName, String userDep,boolean sfzxs) {

		DAO dao = DAO.getInstance();
		
		String searchTj = sfzxs ? "and(a.sfzx = '在校' or a.sfzx is null)"
				: "and a.sfzx = '不在校'";
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.xydm,a.xymc from view_xsxxcxjg a ");
		sql.append(" where 1 = 1 and xydm is not null ");
		sql.append(searchTj);
		sql.append(" and (");

		String yhsjfwSql = new YhsjfwService().getYhsjfw(userName,userStatus, "a","xydm", "bjdm");
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			sql.append(yhsjfwSql);
		}
		
		if ("jd".equalsIgnoreCase(userStatus)) {
			// 兼得
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  (exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
			sql.append(" or exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
			
		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			// 辅导员
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			// 班主任
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			// 学院
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" a.xydm = '"+userDep+"' ");
			}
		} else if ("stu".equalsIgnoreCase(userStatus)) {
			// 学生
			sql.append("  exists(select 1 from view_xsjbxx b ");
			sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
		}else{
			//学校（管理员）
			//return Base.getXyList();
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" 1= 1 ");
			}
		}
		sql.append(" )");

		sql.append(" order by xydm ");
		//System.out.println(sql);
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "xydm", "xymc"});

		return list;

	}
	/**
	 * 
	 * @描述:获取园区所含学院
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-12 下午02:47:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXyListByYq(String userStatus,
			String userName, String userDep,String[] xq,String[] yq) {

		DAO dao = DAO.getInstance();
		
		String tableName = Base.isHistory() ? "view_njxyzybj_all"
				: "view_njxyzybj";
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.xydm,a.xymc from " + tableName + " a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and (");

		String yhsjfwSql = new YhsjfwService().getYhsjfw(userName,userStatus, "a","xydm", "bjdm");
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			sql.append(yhsjfwSql);
		}
		
		if ("jd".equalsIgnoreCase(userStatus)) {
			// 兼得
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  (exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
			sql.append(" or exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
			
		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			// 辅导员
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			// 班主任
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			// 学院
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" a.xydm = '"+userDep+"' ");
			}
		} else if ("stu".equalsIgnoreCase(userStatus)) {
			// 学生
			sql.append("  exists(select 1 from view_xsjbxx b ");
			sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
		}else{
			//学校（管理员）
			//return Base.getXyList();
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" 1= 1 ");
			}
		}
		sql.append(" )");
		// 园区非空
		if (yq != null && yq.length > 0) {
			//通过园区过滤学院
			sql.append(" and a.xydm in(select distinct(xydm) from (select * from xg_gygl_new_cwxxb a");
			sql.append(" left join xg_gygl_new_ldxxb b on a.lddm = b.lddm) ");
			sql.append(" where yqdm in(");
			for (int i = 0; i < yq.length; i++) {
				if(i!=0){
					sql.append(","+"'" + yq[i] + "' ");
				}else{
				sql.append("'" + yq[i] + "' ");
				}
			}
			sql.append(" )");
			// 校区非空
			if (xq != null && xq.length > 0) {
				sql.append(" and xqdm in (");
				for (int i = 0; i < xq.length; i++) {
					if(i!=0){
						sql.append(","+"'" + xq[i] + "' ");
					}else{
					sql.append("'" + xq[i] + "' ");
					}
					
				}
				sql.append(" )");
			}
			sql.append(" )");
		}
		sql.append(" order by xydm ");
		//System.out.println(sql);
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "xydm", "xymc"});

		return list;

	}
	/**
	 * 
	 * @描述:根据园区联动年级
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-13 下午04:20:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getNewNjListByYq(String userStatus,
			String userName, String userDep,String[] xq,String[] yq) {
		DAO dao = DAO.getInstance();
		
		String tableName = Base.isHistory() ? "view_njxyzybj_all"
				: "view_njxyzybj";
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.nj from " + tableName + " a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and (");

		String yhsjfwSql = new YhsjfwService().getYhsjfw(userName,userStatus, "a","xydm", "bjdm");
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			sql.append(yhsjfwSql);
		}
		
		if ("jd".equalsIgnoreCase(userStatus)) {
			// 兼得
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  (exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
			sql.append(" or exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
			
		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			// 辅导员
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			// 班主任
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			// 学院
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" a.xydm = '"+userDep+"' ");
			}
		} else if ("stu".equalsIgnoreCase(userStatus)) {
			// 学生
			sql.append(" exists(select 1 from view_xsjbxx b ");
			sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
		}else{
			//学校（管理员）
			//return Base.getNjList();
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" 1= 1 ");
			}
		}
		sql.append(" )");
		// 园区非空
		if (yq != null && yq.length > 0) {
			//通过园区过滤学院
			sql.append(" and a.nj in(select distinct(nj) from (select * from xg_gygl_new_cwxxb a");
			sql.append(" left join xg_gygl_new_ldxxb b on a.lddm = b.lddm) ");
			sql.append(" where yqdm in(");
			for (int i = 0; i < yq.length; i++) {
				if(i!=0){
					sql.append(","+"'" + yq[i] + "' ");
				}else{
				sql.append("'" + yq[i] + "' ");
				}
			}
			sql.append(" )");
			// 校区非空
			if (xq != null && xq.length > 0) {
				sql.append(" and xqdm in (");
				for (int i = 0; i < xq.length; i++) {
					if(i!=0){
						sql.append(","+"'" + xq[i] + "' ");
					}else{
					sql.append("'" + xq[i] + "' ");
					}
					
				}
				sql.append(" )");
			}
			sql.append(" )");
		}
		sql.append(" order by nj ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "nj", "nj"});

		return list;
	}
	
	/**
	 * 获得专业列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getZyList(String userStatus,
			String userName, String userDep, String[] nj,String sfzxs) {

		DAO dao = DAO.getInstance();

		String tableName = Base.isHistory() ? "view_njxyzybj_all"
				: "view_njxyzybj";
		if("1".equals(sfzxs)){
			tableName="(select * from view_xsxxcxjg a where a.zymc is not null and (a.sfzx = '在校' or a.sfzx is null))";
		}
		if("0".equals(sfzxs)){
			tableName="(select * from view_xsxxcxjg a where a.zymc is not null and a.sfzx = '不在校')";
		}
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select distinct xydm,xymc,zydm,zymc from "+tableName+" a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and (");

		String yhsjfwSql = new YhsjfwService().getYhsjfw(userName,userStatus, "a","xydm", "bjdm");
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			sql.append(yhsjfwSql);
		}
		
		if ("jd".equalsIgnoreCase(userStatus)) {
			// 兼得
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  (exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
			sql.append(" or exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
			
		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			// 辅导员
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			// 班主任
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			// 学院
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" a.xydm = '"+userDep+"' ");
			}
		} else if ("stu".equalsIgnoreCase(userStatus)) {
			// 学生
			sql.append(" exists(select 1 from view_xsjbxx b ");
			sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
		}else{
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" 1= 1 ");
			}
		}
		sql.append(" )");
		
		if (nj != null && nj.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < nj.length; i++) {
				if (i == 0) {
					sql.append(" a.nj = '" + nj[i] + "' ");
				} else {
					sql.append(" or a.nj = '" + nj[i] + "' ");
				}
			}
			sql.append(") ");
		}

		sql.append(" order by xydm ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "xydm", "xymc","zydm", "zymc" });

		return list;

	}
	public List<HashMap<String, String>> getXsZyList(String userStatus,
			String userName, String userDep, String[] nj,boolean sfzxs) {

		DAO dao = DAO.getInstance();

		String searchTj = sfzxs ? "and(a.sfzx = '在校' or a.sfzx is null)"
				: "and a.sfzx = '不在校'";
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select distinct xydm,xymc,zydm,zymc from view_xsxxcxjg a ");
		sql.append(" where 1 = 1 and zydm is not null ");
		sql.append(searchTj);
		sql.append(" and (");

		String yhsjfwSql = new YhsjfwService().getYhsjfw(userName,userStatus, "a","xydm", "bjdm");
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			sql.append(yhsjfwSql);
		}
		
		if ("jd".equalsIgnoreCase(userStatus)) {
			// 兼得
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  (exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
			sql.append(" or exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
			
		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			// 辅导员
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			// 班主任
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append("  exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			// 学院
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" a.xydm = '"+userDep+"' ");
			}
		} else if ("stu".equalsIgnoreCase(userStatus)) {
			// 学生
			sql.append(" exists(select 1 from view_xsjbxx b ");
			sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
		}else{
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" 1= 1 ");
			}
		}
		sql.append(" )");
		
		if (nj != null && nj.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < nj.length; i++) {
				if (i == 0) {
					sql.append(" a.nj = '" + nj[i] + "' ");
				} else {
					sql.append(" or a.nj = '" + nj[i] + "' ");
				}
			}
			sql.append(") ");
		}

		sql.append(" order by xydm ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "xydm", "xymc","zydm", "zymc" });

		return list;

	}
	
	/**
	 * 获得班级列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getBjList(String userStatus,
			String userName, String userDep,String sfzxs) {

		DAO dao = DAO.getInstance();


//		String tableName = Base.isHistory() ? "view_njxyzybj_all"
//				: "view_njxyzybj";
		String tableName = "view_njxyzybj_fdy";
		/*if ("bzr".equalsIgnoreCase(userStatus)) {
			tableName = "view_njxyzybj_bzr";
		}*/

		if("1".equals(sfzxs)){
			tableName="(select * from view_xsxxcxjg a where a.bjmc is not null and (a.sfzx = '在校' or a.sfzx is null))";
		}
		if("0".equals(sfzxs)){
			tableName="(select * from view_xsxxcxjg a where a.bjmc is not null and a.sfzx = '不在校')";
		}
		StringBuilder sql = new StringBuilder();

		sql.append(" select distinct xydm,xymc,zydm,zymc,nj,bjdm,bjmc from "+tableName+" a ");
		sql.append(" where 1 = 1 ");
		
		sql.append(" and (");
		String yhsjfwSql = new YhsjfwService().getYhsjfw(userName,userStatus, "a","xydm", "bjdm");
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			sql.append(yhsjfwSql);
		}
		
		if ("jd".equalsIgnoreCase(userStatus)) {
			// 兼得
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append(" (exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
			sql.append(" or exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
			
		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			// 辅导员
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append(" exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			// 班主任
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append(" exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			// 学院
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" a.xydm = '"+userDep+"' ");
			}
		} else if ("stu".equalsIgnoreCase(userStatus)) {
			// 学生
			sql.append(" exists(select 1 from view_xsjbxx b ");
			sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
		}else{
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" 1= 1 ");
			}
		}
		sql.append(" )");

		if(Globals.XXDM_ZJDX.equals(Base.xxdm)){
			sql.append(" order by nj,xydm,bjmc desc");
		} else {
			sql.append(" order by nj,xydm,zydm,bjdm ");
		}
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "xydm", "xymc", "zydm", "zymc", "nj", "bjdm",
						"bjmc" });

		return list;

	}
	public List<HashMap<String, String>> getXsBjList(String userStatus,
			String userName, String userDep,boolean sfzxs) {

		DAO dao = DAO.getInstance();


		String searchTj = sfzxs ? "and(a.sfzx = '在校' or a.sfzx is null)"
				: "and a.sfzx = '不在校'";
		
		StringBuilder sql = new StringBuilder();

		sql.append(" select distinct xydm,xymc,zydm,zymc,nj,bjdm,bjmc from view_xsxxcxjg a");
		sql.append(" where 1 = 1 and bjmc is not null ");
		sql.append(searchTj);
		sql.append(" and (");
		String yhsjfwSql = new YhsjfwService().getYhsjfw(userName,userStatus, "a","xydm", "bjdm");
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			sql.append(yhsjfwSql);
		}
		
		if ("jd".equalsIgnoreCase(userStatus)) {
			// 兼得
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append(" (exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
			sql.append(" or exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
			
		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			// 辅导员
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append(" exists(select 1 from fdybjb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			// 班主任
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql.append(" or ");
			}
			sql.append(" exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			// 学院
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" a.xydm = '"+userDep+"' ");
			}
		} else if ("stu".equalsIgnoreCase(userStatus)) {
			// 学生
			sql.append(" exists(select 1 from view_xsjbxx b ");
			sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
		}else{
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				sql.append(" 1= 1 ");
			}
		}
		sql.append(" )");

		sql.append(" order by nj,xydm,zydm,bjdm ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "xydm", "xymc", "zydm", "zymc", "nj", "bjdm",
						"bjmc" });

		return list;

	}

	/**
	 * 获得功能模块的查询条件
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getGnmkTjList(SearchModel model) {
		String path = model.getPath();

		String tableName = "xg_search_szb";
		String query = " where path = ?  order by lx,xssx";
		List<HashMap<String, String>> tjList = getRsList(tableName, query,
				new String[] { path }, new String[] { "lx", "tj", "zd" }, "");
		
		return tjList;
	}

	/**
	 * 获得寝室信息
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getQsList(
			String[] xqdm, String[] yqdm, String[] lddm, String[] cs,
			String userStatus, String userName, String userDep) {

		DAO dao = DAO.getInstance();
		
		// 寝室条件
		String sql = "select distinct qsh from view_xg_gygl_new_cwxx ";
		// 过滤条件
		StringBuilder query = new StringBuilder(" where 1 = 1 ");
		ArrayList<String> inPutList = new ArrayList<String>();

		//校区
		if (xqdm != null && xqdm.length > 0) {
			query.append(" and (");
			for (int i = 0; i < xqdm.length; i++) {
				if (i != 0) {
					query.append(" or ");
				}
				query.append(" xqdm = ? ");
				
				inPutList.add(xqdm[i]);
			}
			query.append(" )");
		}

		//园区
		if (yqdm != null && yqdm.length > 0) {
			query.append(" and (");
			for (int i = 0; i < yqdm.length; i++) {
				if (i != 0) {
					query.append(" or ");
				}
				query.append(" yqdm = ? ");
				
				inPutList.add(yqdm[i]);
			}
			query.append(" )");

		}
		
		//楼栋
		if (lddm != null && lddm.length > 0) {
			query.append(" and (");
			for (int i = 0; i < lddm.length; i++) {
				if (i != 0) {
					query.append(" or ");
				}
				query.append(" lddm = ? ");
				
				inPutList.add(lddm[i]);
			}
			query.append(" )");

		}
		
		//层数
		if (cs != null && cs.length > 0) {
			query.append(" and (");
			for (int i = 0; i < cs.length; i++) {
				if (i != 0) {
					query.append(" or ");
				}
				query.append(" ch = ? ");
				
				inPutList.add(cs[i]);
			}
			query.append(" )");

		}
		
		query.append(" order by qsh ");
		
		sql+=query;
		
		List<HashMap<String, String>> qshList = dao.getList(sql,
				inPutList.toArray(new String[]{}), new String[] { "qsh" });

		return qshList;
	}

	/**
	 * 获得楼栋信息列表（附为分配寝室数）
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getLdForWfpQssList(String[] xqdm,
			String[] yqdm, String gyglyQx, String userName, String userDep) {
		
		// 寝室自动分配
		StringBuilder gySql = new StringBuilder();
		gySql.append(" select a.lddm,a.ldmc,(a.qsnum - a.yfpnum) num from ( ");
		gySql.append(" select a.lddm,a.ldmc,nvl(b.qsNum,0) qsNum,nvl(c.yfpNum,0) yfpNum from ");
		gySql.append(" (select * from sslddmb where 1 = 1 ");
		//校区
		if (xqdm != null && xqdm.length > 0) {
			gySql.append(" and (");
			for (int i = 0; i < xqdm.length; i++) {
				if (i != 0) {
					gySql.append(" or ");
				}
				gySql.append(" xqdm = '"+xqdm[i]+"' ");	
			}
			gySql.append(" )");
		}

		//园区
		if (yqdm != null && yqdm.length > 0) {
			gySql.append(" and (");
			for (int i = 0; i < yqdm.length; i++) {
				if (i != 0) {
					gySql.append(" or ");
				}
				gySql.append(" yqdm = '"+yqdm[i]+"' ");	
			}
			gySql.append(" )");

		}
		
		if("yes".equalsIgnoreCase(gyglyQx)){
			gySql.append(" and exists(select 1 from xg_gygl_ldglb b ");
			gySql.append(" where sslddmb.lddm = b.lddm and b.yhm='"+userName+"') ");
		}
		
		gySql.append(" ) a ");
		
		gySql.append(" left join ");
		gySql.append(" (select b.lddm, count(1) qsNum from ssxxb b group by b.lddm) b on a.lddm = b.lddm ");
		gySql.append(" left join ");
		gySql.append(" (select c.lddm, count(1) yfpNum from xg_gygl_qsfpb c group by c.lddm) c on a.lddm = c.lddm ");
		
		gySql.append(" ) a order by a.lddm");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> ldQsList = dao.getList(gySql.toString(),
				new String[] {}, new String[] { "lddm", "ldmc", "num" });

		return ldQsList;

	}

	/**
	 * 获得用户角色
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 * 
	 */
	public String[] getGnmkRoles(User user, String gnmkdm) throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "select distinct jsdm from xg_xtwh_jscdqxb where gnmkdm = ?";

		String[] gnmkRoles = dao.getRs(sql, new String[] { gnmkdm }, "jsdm");

		return gnmkRoles;
	}
	
	/**
	 * 获得角色信息
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getUserRolesInfo(String[] userRoles){

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select jsdm,jscmdm,jscmmc from xg_view_xtwh_jswh ");
		sql.append(" where 1 = 1 ");
		if (userRoles != null && userRoles.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < userRoles.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" jsdm = ? ");
			}
			sql.append(" ) ");
		}else {
			sql.append(" and 1=2 ");
		}

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				userRoles, new String[] { "jsdm", "jscmdm", "jscmmc" });

		return list;
	}
	
	
	/********************************************************************************************************************************/
	/**************************************************公寓管理（第三版）高级查询条件**************************************************/
	/********************************************************************************************************************************/
	/**
	 * 获得寝室信息
	 * @author zhangh
	 */
	public List<HashMap<String, String>> getQsList_Third(
			String[] xqdm, String[] yqdm, String[] lddm, String[] ch,
			String userStatus, String userName, String userDep) {

		DAO dao = DAO.getInstance();
		
		// 寝室条件
		String sql = "select distinct qsh,ch from view_xg_gygl_new_cwxx ";
		//过滤条件
		StringBuilder query = new StringBuilder(" where 1 = 1 ");
		ArrayList<String> inPutList = new ArrayList<String>();

		//校区
		if (xqdm != null && xqdm.length > 0) {
			query.append(" and (");
			for (int i = 0; i < xqdm.length; i++) {
				if (i != 0) {
					query.append(" or ");
				}
				query.append(" xqdm = ? ");
				
				inPutList.add(xqdm[i]);
			}
			query.append(" )");
		}

		//园区
		if (yqdm != null && yqdm.length > 0) {
			query.append(" and (");
			for (int i = 0; i < yqdm.length; i++) {
				if (i != 0) {
					query.append(" or ");
				}
				query.append(" yqdm = ? ");
				
				inPutList.add(yqdm[i]);
			}
			query.append(" )");

		}
		
		//楼栋
		if (lddm != null && lddm.length > 0) {
			query.append(" and (");
			for (int i = 0; i < lddm.length; i++) {
				if (i != 0) {
					query.append(" or ");
				}
				query.append(" lddm = ? ");
				
				inPutList.add(lddm[i]);
			}
			query.append(" )");

		}
		
		//层数
		if (ch != null && ch.length > 0) {
			query.append(" and (");
			for (int i = 0; i < ch.length; i++) {
				if (i != 0) {
					query.append(" or ");
				}
				query.append(" ch = ? ");
				
				inPutList.add(ch[i]);
			}
			query.append(" )");

		}
		
		query.append(" order by to_number(ch),qsh ");
		
		sql+=query;
		
		List<HashMap<String, String>> qshList = dao.getList(sql,
				inPutList.toArray(new String[]{}), new String[] { "qsh" });

		return qshList;
	}
	/**
	 * @描述:省市县三级联动
	 * @作者：zhangjw
	 * @日期：2013-5-21 上午11:24:58
	 * @修改记录: 
	 * @param qxList 上级列表
	 * @param jb 级别
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQxList(List<String> qxList,String jb) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct qxdm jzid,qxmc jzmc from view_dmk_qx b where b.jb ='"+jb+"'");
		if(qxList.size()>0){
			sql.append("  CONNECT BY PRIOR qxdm=sjdm START WITH (");
			Iterator<String> ite = qxList.iterator();
			while(ite.hasNext()){
				String sheng = ite.next();
				sql.append("b.sjdm ='"+sheng+"' or ");
			}
			String newSql = sql.substring(0,sql.lastIndexOf("or"));
			sql = new StringBuilder();
			sql.append(newSql);
			sql.append(" )");
		}
		sql.append(" order by qxdm ");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),new String[]{}, new String[]{"jzid","jzmc"});
		return list;
	}
	/********************************************************************************************************************************/
	/**************************************************军训建制四级联动(团、营、连、排)**************************************************/
	/********************************************************************************************************************************/
	/**
	 * 获得均需团id
	 * @author 易江东
	 */
	public List<HashMap<String, String>> getTidList(String jxid,List<String[]> sjidList) {
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.* from ( ");
		sql.append(getJxjzSql());
		sql.append(" ) a ");
		sql.append(" where jzjb='001' ");
		
		String[] inputValue=new String[]{jxid};
		String[] outputValue=new String[]{"jzid", "jzmc", "jzjb", "sjid",
				"tid","yid","lid","pid","bid","ssid"};
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		
		return list;
	}
	
	/**
	 * 获得均需营id
	 * @author 易江东
	 */
	public List<HashMap<String, String>> getYidList(String jxid,List<String[]> sjidList) {
		if(sjidList==null || sjidList.size() < 1){
			return null;
		}
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		
		String[] sjidT=sjidList.get(0);
		
		sql.append(" select a.* from ( ");
		sql.append(getJxjzSql());
		sql.append(" ) a ");
		sql.append(" where jzjb='002' ");
		
		String[] inputValue=new String[]{jxid};
		String[] outputValue=new String[]{"jzid", "jzmc", "jzjb", "sjid",
				"tid","yid","lid","pid","bid","ssid"};
		
		//增加根据团id查询
		if(sjidT != null && sjidT.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidT.length; i++) {
				if(i==0){
					sql.append(" ( a.tid=? ");
				}else{
					sql.append(" or a.tid=? ");
				}
				if(i == (sjidT.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidT);
		}
			
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		
		return list;
	}
	
	/**
	 * 获得均需连id
	 * @author 易江东
	 */
	public List<HashMap<String, String>> getLidList(String jxid,List<String[]> sjidList) {
		if(sjidList==null || sjidList.size() < 2){
			return null;
		}
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		String[] sjidT=sjidList.get(0);
		String[] sjidY=sjidList.get(1);
		
		sql.append(" select a.* from ( ");
		sql.append(getJxjzSql());
		sql.append(" ) a ");
		sql.append(" where jzjb='003' ");
		
		String[] inputValue=new String[]{jxid};
		String[] outputValue=new String[]{"jzid", "jzmc", "jzjb", "sjid",
				"tid","yid","lid","pid","bid","ssid"};
		
		//增加根据团id查询
		if(sjidT != null && sjidT.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidT.length; i++) {
				if(i==0){
					sql.append(" ( a.tid=? ");
				}else{
					sql.append(" or a.tid=? ");
				}
				if(i == (sjidT.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidT);
		}
		
		//增加根据营id查询
		if(sjidY != null && sjidY.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidY.length; i++) {
				if(i==0){
					sql.append(" ( a.yid=? ");
				}else{
					sql.append(" or a.yid=? ");
				}
				if(i == (sjidY.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidY);
		}
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		
		return list;
	}
	
	/**
	 * 获得均需排id
	 * @author 易江东
	 */
	public List<HashMap<String, String>> getPidList(String jxid,List<String[]> sjidList) {
		if(sjidList==null || sjidList.size() < 3){
			return null;
		}
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		String[] sjidT=sjidList.get(0);
		String[] sjidY=sjidList.get(1);
		String[] sjidL=sjidList.get(2);
		
		sql.append(" select a.* from ( ");
		sql.append(getJxjzSql());
		sql.append(" ) a ");
		sql.append(" where jzjb='004' ");
		
		String[] inputValue=new String[]{jxid};
		String[] outputValue=new String[]{"jzid", "jzmc", "jzjb", "sjid",
				"tid","yid","lid","pid","bid","ssid"};
		
		//增加根据团id查询
		if(sjidT != null && sjidT.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidT.length; i++) {
				if(i==0){
					sql.append(" ( a.tid=? ");
				}else{
					sql.append(" or a.tid=? ");
				}
				if(i == (sjidT.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidT);
		}
		
		//增加根据营id查询
		if(sjidY != null && sjidY.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidY.length; i++) {
				if(i==0){
					sql.append(" ( a.yid=? ");
				}else{
					sql.append(" or a.yid=? ");
				}
				if(i == (sjidY.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidY);
		}
		
		//增加根据连id查询
		if(sjidL != null && sjidL.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidL.length; i++) {
				if(i==0){
					sql.append(" ( a.lid=? ");
				}else{
					sql.append(" or a.lid=? ");
				}
				if(i == (sjidL.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidL);
		}
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		
		return list;
	}
	
	
	/**
	 * 
	 * @描述: 获得均需班id
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-1 下午03:36:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jxid
	 * @param sjidList
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBidList(String jxid,List<String[]> sjidList) {
		if(sjidList==null || sjidList.size() < 4){
			return null;
		}
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		String[] sjidT=sjidList.get(0);
		String[] sjidY=sjidList.get(1);
		String[] sjidL=sjidList.get(2);
		String[] sjidP=sjidList.get(3);
		
		sql.append(" select a.* from ( ");
		sql.append(getJxjzSql());
		sql.append(" ) a ");
		sql.append(" where jzjb='005' ");
		
		String[] inputValue=new String[]{jxid};
		String[] outputValue=new String[]{"jzid", "jzmc", "jzjb", "sjid",
				"tid","yid","lid","pid","bid","ssid"};
		
		//增加根据团id查询
		if(sjidT != null && sjidT.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidT.length; i++) {
				if(i==0){
					sql.append(" ( a.tid=? ");
				}else{
					sql.append(" or a.tid=? ");
				}
				if(i == (sjidT.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidT);
		}
		
		//增加根据营id查询
		if(sjidY != null && sjidY.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidY.length; i++) {
				if(i==0){
					sql.append(" ( a.yid=? ");
				}else{
					sql.append(" or a.yid=? ");
				}
				if(i == (sjidY.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidY);
		}
		
		//增加根据连id查询
		if(sjidL != null && sjidL.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidL.length; i++) {
				if(i==0){
					sql.append(" ( a.lid=? ");
				}else{
					sql.append(" or a.lid=? ");
				}
				if(i == (sjidL.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidL);
		}
		
		//增加根据排id查询
		if(sjidP != null && sjidP.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidP.length; i++) {
				if(i==0){
					sql.append(" ( a.pid=? ");
				}else{
					sql.append(" or a.pid=? ");
				}
				if(i == (sjidP.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidP);
		}
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		
		return list;
	}
	
	
	/**
	 * 
	 * @描述: 获得均需宿舍id
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-1 下午03:38:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jxid
	 * @param sjidList
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSsidList(String jxid,List<String[]> sjidList) {
		if(sjidList==null || sjidList.size() < 5){
			return null;
		}
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		String[] sjidT=sjidList.get(0);
		String[] sjidY=sjidList.get(1);
		String[] sjidL=sjidList.get(2);
		String[] sjidP=sjidList.get(3);
		String[] sjidB=sjidList.get(4);
		
		sql.append(" select a.* from ( ");
		sql.append(getJxjzSql());
		sql.append(" ) a ");
		sql.append(" where jzjb='006' ");
		
		String[] inputValue=new String[]{jxid};
		String[] outputValue=new String[]{"jzid", "jzmc", "jzjb", "sjid",
				"tid","yid","lid","pid","bid","ssid"};
		
		//增加根据团id查询
		if(sjidT != null && sjidT.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidT.length; i++) {
				if(i==0){
					sql.append(" ( a.tid=? ");
				}else{
					sql.append(" or a.tid=? ");
				}
				if(i == (sjidT.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidT);
		}
		
		//增加根据营id查询
		if(sjidY != null && sjidY.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidY.length; i++) {
				if(i==0){
					sql.append(" ( a.yid=? ");
				}else{
					sql.append(" or a.yid=? ");
				}
				if(i == (sjidY.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidY);
		}
		
		//增加根据连id查询
		if(sjidL != null && sjidL.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidL.length; i++) {
				if(i==0){
					sql.append(" ( a.lid=? ");
				}else{
					sql.append(" or a.lid=? ");
				}
				if(i == (sjidL.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidL);
		}
		
		//增加根据排id查询
		if(sjidP != null && sjidP.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidP.length; i++) {
				if(i==0){
					sql.append(" ( a.pid=? ");
				}else{
					sql.append(" or a.pid=? ");
				}
				if(i == (sjidP.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidP);
		}
		
		
		//增加根据班id查询
		if(sjidB != null && sjidB.length > 0){
			sql.append(" and ");
			for (int i = 0; i < sjidB.length; i++) {
				if(i==0){
					sql.append(" ( a.bid=? ");
				}else{
					sql.append(" or a.bid=? ");
				}
				if(i == (sjidB.length-1)){
					sql.append(" ) ");
				}
			}
			inputValue=uniteArrays(inputValue,sjidB);
		}
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		
		return list;
	}
	
	
	
	/**
	 * 获取军训建制sql
	 * @return
	 */
	private String getJxjzSql(){
		List<HashMap<String, String>> list=getJxjzjbList();
		StringBuffer sql=new StringBuffer();
		sql.append(" select jzid, ");
		sql.append(" jzmc, ");
		sql.append(" jzjb, ");
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				sql.append(" func_getJxjzid(jzid,'");
				sql.append(list.get(i).get("djdm"));
				sql.append("') ");
				sql.append(list.get(i).get("djbm"));
				sql.append(", ");
			}
		}
		sql.append(" sjid, ");
		sql.append(" jgmc, ");
		sql.append(" jgdh, ");
		sql.append(" jsmc, ");
		sql.append(" jsdh, ");
		sql.append(" SYS_CONNECT_BY_PATH(jzmc, ' ') treejzmc ");
		sql.append(" FROM xg_jxgl_jxjzwhb ");
		sql.append(" START WITH sjid = ? ");
		sql.append(" CONNECT BY sjid = PRIOR jzid ");
		
		return sql.toString();
	}
	
	/**
	 * 合并数组
	 * @param arrays
	 * @return
	 * @author sjf
	 */
	public String[] uniteArrays(String[]...arrays){
		int length = 0;
		for (int i=0; i<arrays.length; i++){
			length += arrays[i].length;
		}
		
		String[] strs = new String[length];
		
		int count = 0;
		for(String[] array : arrays){
			for(int j=0; j<array.length; j++){
				strs[count++] = array[j];
			}
		}
		
		return strs; 
	}
	
	/**
	 * 查询军训等级
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	private List<HashMap<String, String>> getJxdjList(JxjzglFrom model){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();

		sql.append(" select dm djdm, mc djmc, dj djdj , bm djbm ");
		sql.append(" from xg_jxgl_jxjzdjb ");
		sql.append(" order by to_number(djdj) asc ");

		String[] inputValue = new String[] {};
		String[] outputValue = new String[] { "djdm", "djmc", "djdj","djbm" };
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	
	private static List<HashMap<String, String>> jxjzjbList=null;


	public List<HashMap<String, String>> getJxjzjbList() {
		if(this.jxjzjbList == null){
			this.jxjzjbList=getJxdjList(new JxjzglFrom());
		}
		return this.jxjzjbList;
	}
	
	
	public List<HashMap<String, Object>> getGyjllbdlList(){
		DAO dao = DAO.getInstance();
		String sql = "select GYJLLBDLDM dm,GYJLLBDLMC mc from XG_GYGL_NEW_GYJLLBDLB order by GYJLLBDLDM";
		List<HashMap<String, String>> gyjllbdldmTjList = dao.getList(sql,new String[] {}, new String[] { "dm", "mc" });
		return listToList(gyjllbdldmTjList);
	}
	
	public List<HashMap<String, Object>> getGyjllbList(String[] gyjllbdldm){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select GYJLLBDM dm,GYJLLBMC mc from xg_gygl_new_gyjllbdmb a where 1=1 ");
		
		// 公寓记录类别大类非空
		if (gyjllbdldm != null && gyjllbdldm.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < gyjllbdldm.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.gyjllbdldm = '" + gyjllbdldm[i] + "' ");
			}
			sql.append(" )");
		}
		sql.append(" order by GYJLLBDM ");
		List<HashMap<String, String>> gyjllbdmTjList = dao.getList(sql.toString(),new String[] {}, new String[] { "dm", "mc" });
		return listToList(gyjllbdmTjList);
	}
	/**
	 * 类型转换  格式化数据
	 * @param strList
	 * @return
	 */
	private List<HashMap<String, Object>> listToList(List<HashMap<String, String>> list){
		List<HashMap<String, Object>> objList = new ArrayList<HashMap<String, Object>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				objList.add(map);
			}
		}
		return objList;
	}
	
	

	/**
	 * 获得全部的学院/部门列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getXyNewList(String userStatus,
			String userName, String userDep) {

		DAO dao = DAO.getInstance();
		
		String tableName = Base.isHistory() ? "view_njxyzybj_all"
				: "view_njxyzybj";
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct  a.bmdm xydm,a.bmmc xymc from ZXBZ_XXBMDM a ");
		sql.append(" where bmlb ='5' and 1 = 1 ");
//		
//		if ("jd".equalsIgnoreCase(userStatus)) {
//			// 兼得
//			sql.append(" and (exists(select 1 from fdybjb b ");
//			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
//			sql.append(" or exists(select 1 from bzrbbb b ");
//			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
//			
//		} else if ("fdy".equalsIgnoreCase(userStatus)) {
//			// 辅导员
//			sql.append(" and exists(select 1 from fdybjb b ");
//			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
//		} else if ("bzr".equalsIgnoreCase(userStatus)) {
//			// 班主任
//			sql.append(" and exists(select 1 from bzrbbb b ");
//			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
//		} else if ("xy".equalsIgnoreCase(userStatus)) {
//			// 学院
//			sql.append(" and a.xydm = '"+userDep+"' ");
//		} else if ("stu".equalsIgnoreCase(userStatus)) {
//			// 学生
//			sql.append(" and exists(select 1 from view_xsjbxx b ");
//			sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
//		}else{
//			//学校（管理员）
//			//return Base.getXyList();
//		}
//			
		sql.append(" order by a.bmdm ");
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "xydm", "xymc"});

		return list;

	}
	

	/**
	 * 获得全部的专业列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getZyNewList(String userStatus,
			String userName, String userDep, String[] nj) {

		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct a.zydm, a.zymc, a.bmdm xydm , b.bmmc xymc ");
		sql.append("   from BKS_ZYDM a ");
		sql.append("   left join ZXBZ_XXBMDM b ");
		sql.append("     on a.bmdm = b.bmdm ");
//		
//		if ("jd".equalsIgnoreCase(userStatus)) {
//			// 兼得
//			sql.append(" and (exists(select 1 from fdybjb b ");
//			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
//			sql.append(" or exists(select 1 from bzrbbb b ");
//			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
//			
//		} else if ("fdy".equalsIgnoreCase(userStatus)) {
//			// 辅导员
//			sql.append(" and exists(select 1 from fdybjb b ");
//			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
//		} else if ("bzr".equalsIgnoreCase(userStatus)) {
//			// 班主任
//			sql.append(" and exists(select 1 from bzrbbb b ");
//			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
//		} else if ("xy".equalsIgnoreCase(userStatus)) {
//			// 学院
//			sql.append(" and a.xydm = '"+userDep+"' ");
//		} else if ("stu".equalsIgnoreCase(userStatus)) {
//			// 学生
//			sql.append(" and exists(select 1 from view_xsjbxx b ");
//			sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
//		}
//		
		if (nj != null && nj.length > 0) {
			sql.append("   left join BKS_BJDM c ");
			sql.append("     on a.zydm = c.zydm ");
			sql.append(" where 1=1 and ( ");
			for (int i = 0; i < nj.length; i++) {
				if (i == 0) {
					sql.append(" c.nj = '" + nj[i] + "' ");
				} else {
					sql.append(" or c.nj = '" + nj[i] + "' ");
				}
			}
			sql.append(") ");
		}
//		

		sql.append(" order by a.zydm ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "xydm", "xymc","zydm", "zymc" });

		return list;

	}
	

	/**
	 * 
	 * @描述: 获得全班级列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-24 下午03:12:46
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjNewList(String userStatus,
			String userName, String userDep) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append(" select distinct xydm,xymc,zydm,zymc,nj,bjdm,bjmc from view_njxyzybj_all a ");
		sql.append(" where 1 = 1 ");
//		
//		if ("jd".equalsIgnoreCase(userStatus)) {
//			// 兼得
//			sql.append(" and (exists(select 1 from fdybjb b ");
//			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
//			sql.append(" or exists(select 1 from bzrbbb b ");
//			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' )) ");
//			
//		} else if ("fdy".equalsIgnoreCase(userStatus)) {
//			// 辅导员
//			sql.append(" and exists(select 1 from fdybjb b ");
//			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
//		} else if ("bzr".equalsIgnoreCase(userStatus)) {
//			// 班主任
//			sql.append(" and exists(select 1 from bzrbbb b ");
//			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
//		} else if ("xy".equalsIgnoreCase(userStatus)) {
//			// 学院
//			sql.append(" and a.xydm = '"+userDep+"' ");
//		} else if ("stu".equalsIgnoreCase(userStatus)) {
//			// 学生
//			sql.append(" and exists(select 1 from view_xsjbxx b ");
//			sql.append(" where a.bjdm = b.bjdm and b.xh = '"+userName+"')");
//		}
		
		sql.append(" order by nj,xydm,zydm,bjdm ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "xydm", "xymc", "zydm", "zymc", "nj", "bjdm",
						"bjmc" });

		return list;

	}
	
	
	/**
	 * 获得评奖项目列表
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getPjxmList(String[] xmlx, String[] xmxz) {

		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();

		sql.append("select distinct xmmc xmdm,xmmc from (select xmmc,xzdm,LXDM FROM xg_pjpy_new_pjjgb ");
		sql.append("union SELECT xmmc,xzdm,lxdm FROM (select xmmc,xzdm,lxdm from xg_pjpy_new_xmsq a ");
		sql.append("left join xg_pjpy_new_pjxmb b on a.xmdm = b.xmdm)) where xmmc is not null and 1 = 1 ");

		if(xmlx !=null && xmlx.length>0){
			sql.append(" and lxdm in ('");
			for (int i = 0; i < xmlx.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(xmlx[i]);
			}
			sql.append("')");
		}
		
		
		if(xmxz !=null && xmxz.length>0){
			sql.append(" and xzdm in ('");
			for (int i = 0; i < xmxz.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(xmxz[i]);
			}
			sql.append("')");
		}
		
		sql.append(" order by xmmc");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "xmdm","xmmc"});

		return list;

	}
	
	
}
