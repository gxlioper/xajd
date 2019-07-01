/**
 * @部门:学工产品事业部
 * @日期：2014-5-4 下午02:30:39 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpjsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.pjpy.jtpj.jtpjsq.JtpjSqDao;
import com.zfsoft.xgxt.pjpy.jtpj.jtpjsq.JtpjSqForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-5-4 下午02:30:39
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JtpjShDao extends JtpjSqDao {
	@Override
	public List<HashMap<String, String>> getPageList(JtpjSqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
//				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select * from (");
		sql.append(" select * from (");
		sql.append(" select * ");
		sql.append(" from (select t1.sqid,t1.splcid,t1.pjjtmc,t1.rs,t1.xydm,t1.zydm,t1.bjdm,t1.sqsj,t1.sqxn,t1.sqxq,n.pdxn,n.pdxq,n.jxmc,n.jxid,f.*,d.shzt,");
		sql.append(" (select xqmc from xqdzb where xqdm=n.pdxq) pdxqmc,");
		sql.append(" d.gwid, ");
		sql.append("  d.shr, ");
		sql.append("  d.shyj, ");
		sql.append(" d.guid shid, ");
		sql.append(" row_number() over(partition by t1.sqid order by d.shsj desc) rn ");
		sql.append(" from (");
		// 班级评奖单位
		sql.append("(select a.*, b.xydm, b.zydm, b.bjdm");
		sql.append(" from xg_pjpy_jtpj_jtjxsqb a");
		sql.append(" left join view_njxyzybj_all b");
		sql.append("  on a.pjjtid = b.bjdm");
		sql.append(" where  a.jtpjdw = 'bj' ");
		
		// 用户身份
		String userStatus = user.getUserStatus();

		// 用户名
		String userName = user.getUserName();
		// 用户所在部门
		String userDep = user.getUserDep();
		// 访问用户为学院
		if ("xy".equalsIgnoreCase(userStatus)) {
				sql.append(" and b.xydm = '" + userDep + "' ");
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			sql.append(" and exists (select 1 from bzrbbb x where ");
			sql.append(" b.bjdm =  x.bjdm ");
			sql.append(" and x.zgh = '" + userName + "') ");

		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			sql.append(" and exists (select 1 from fdybjb x where ");
			sql.append(" b.bjdm =  x.bjdm ");
			sql.append(" and x.zgh = '" + userName + "') ");

		} else if ("jd".equalsIgnoreCase(userStatus)) {// 访问用户为辅导员兼班主任
			sql.append(" and (exists (select 1 from bzrbbb x where ");
			sql.append(" b.bjdm =  x.bjdm ");
			sql.append(" and x.zgh = '" + userName + "') ");
			
			sql.append(" or exists (select 1 from fdybjb z where ");
			sql.append(" b.bjdm =  z.bjdm ");
			sql.append(" and z.zgh = '" + userName + "')) ");

		}
		
		sql.append(" ) ");	
		
		
		sql.append("union all");

		// 学院评奖单位
		sql.append("(select a.*,c.xydm, '' zydm, '' bjdm");
		sql.append("  from xg_pjpy_jtpj_jtjxsqb a");
		sql.append("  left join (select distinct xydm from view_njxyzybj_all) c");
		sql.append("    on a.pjjtid = c.xydm");
		sql.append("  where a.jtpjdw = 'xy' ");		

		// 访问用户为学院/班主任/辅导员/贱人
		if ("xy".equalsIgnoreCase(userStatus) || "bzr".equalsIgnoreCase(userStatus) 
				|| "fdy".equalsIgnoreCase(userStatus) || "jd".equalsIgnoreCase(userStatus)) {
			sql.append(" and c.xydm = '" + userDep + "' ");
		} 
		
		sql.append(" )) ");
		
		sql.append(" t1 ");
		sql.append(" left join xg_pjpy_jtpj_jtjxsz n");
		sql.append(" on t1.jxid=n.jxid ");
		sql.append("  left join xg_xtwh_shztb d ");
		sql.append("  on t1.sqid = d.ywid ");
		sql.append("  left join xg_xtwh_spgwyh e ");
		sql.append("   on d.gwid = e.spgw ");
		sql.append(" left join xg_xtwh_spgw f ");
		sql.append("    on d.gwid = f.id ");
		sql.append(" where e.spyh = '" + user.getUserName()
				+ "' and t1.shzt<>9 and d.shzt<>9 ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (d.shzt<>0 and d.shzt<>4 )  ");
		} else {
			sql.append(" and ( d.shzt=0 or d.shzt=4 )  ");
		}
		sql.append(" ) a where rn = 1 )a where 1=1 ");
//
//		sql.append(searchTjByUser);
		sql.append(")");		
		sql.append(" union all (");
		sql.append(getQtSql(t, user));
		sql.append(")");		
		sql.append(") where 1=1");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述: 获取其他sql查询
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-23 上午09:21:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型
	 */
	private String getQtSql(JtpjSqForm t, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select * ");
		sql
				.append(" from (select t1.sqid,t1.splcid,t1.pjjtmc,t1.rs,'' xydm,'' zydm,'' bjdm,t1.sqsj,t1.sqxn,t1.sqxq,n.pdxn,n.pdxq,n.jxmc,n.jxid,f.*,d.shzt,");
		sql.append(" (select xqmc from xqdzb where xqdm=n.pdxq) pdxqmc,");
		sql.append(" d.gwid, ");
		sql.append("  d.shr, ");
		sql.append("  d.shyj, ");
		sql.append(" d.guid shid, ");
		sql
				.append(" row_number() over(partition by t1.sqid order by d.shsj desc) rn ");
		sql.append(" from (");
		sql.append(" select a.* ");
		sql.append(" from xg_pjpy_jtpj_jtjxsqb a ");
		sql.append(" where a.jtpjdw = 'qt' or a.jtpjdw = 'qs')");
		sql.append(" t1 ");
		sql.append(" left join xg_pjpy_jtpj_jtjxsz n");
		sql.append(" on t1.jxid=n.jxid ");
		sql.append("  left join xg_xtwh_shztb d ");
		sql.append("  on t1.sqid = d.ywid ");
		sql.append("  left join xg_xtwh_spgwyh e ");
		sql.append("   on d.gwid = e.spgw ");
		sql.append(" left join xg_xtwh_spgw f ");
		sql.append("    on d.gwid = f.id ");
		sql.append(" where e.spyh = '" + user.getUserName()
				+ "' and t1.shzt<>9 and d.shzt<>9 ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (d.shzt<>0 and d.shzt<>4 )  ");
		} else {
			sql.append(" and ( d.shzt=0 or d.shzt=4 )  ");
		}
		sql.append(" ) a where rn = 1 )a where 1=1 ");
		return sql.toString();
	}

	/**
	 * 
	 * @描述: 修改审核状态
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-4 下午04:51:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jsf
	 * @return
	 * @throws Exception int 返回类型
	 */
	public boolean updateShzt(JtpjShForm jsf) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update xg_pjpy_jtpj_jtjxsqb set shzt=? where sqid=?");
		return dao.update(sb.toString(), new String[] { jsf.getShzt(),
				jsf.getSqid() }) > 0;
	}
	
	/** 
	 * @描述:获取最新评定分数（西安科技大学个性化）(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-20 上午10:35:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public String getPdfs(JtpjShForm model){
		String sql = "select zd3 from (select * from xg_xtwh_shztb where ywid = ? order by shsj desc nulls last) where rownum = 1 ";
		return dao.getOneRs(sql, new String[]{model.getSqid()}, "zd3");
	}
	
	/**
	 * @description	： 集体评奖名单导出（南京高等职业技术学校）
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-9 下午04:51:01
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getJtpjmd(JtpjShForm t) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select jxid,sqxn,sqxq,sqxqmc,bjdm,sqly,bjmc,xydm,xymc,jxmc,bzr from");
    	sql.append(" (select a.jxid,a.sqxn,a.sqxq,x.xqmc sqxqmc,a.pjjtid bjdm,a.sqly,b.bjmc,b.xydm,b.xymc,c.jxmc,d.bzr");
    	sql.append(" from xg_pjpy_jtpj_jtjxsqb a");
    	sql.append(" left join view_njxyzybj b on a.pjjtid = b.bjdm");
    	sql.append(" left join xg_pjpy_jtpj_jtjxsz c on a.jxid = c.jxid");
    	sql.append(" left join (select a.bjdm,replace(wm_concat(b.xm),';',',') bzr from bzrbbb a,fdyxxb b where a.zgh = b.zgh group by a.bjdm) d on a.pjjtid = d.bjdm");
    	sql.append(" left join xqdzb x on x.xqdm = a.sqxq where a.jtpjdw = 'bj')");
    	sql.append(" where 1 = 1 ");
    	sql.append(searchTj);
    	sql.append(" group by jxid,sqxn,sqxq,sqxqmc,bjdm,sqly,bjmc,xydm,xymc,jxmc,bzr order by sqxn,sqxq,xydm,bjdm asc");
    	return dao.getListNotOut(sql.toString(), inputV);
	}
}
