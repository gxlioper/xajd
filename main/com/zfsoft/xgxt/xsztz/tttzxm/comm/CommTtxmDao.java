/**
 * @部门:学工产品事业部
 * @日期：2016-7-25 上午08:52:08 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsztz.tttzxm.sq.TttzxmForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-25 上午08:52:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CommTtxmDao extends SuperDAOImpl<TttzxmForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TttzxmForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 *团体项目申请项目选择查询,只过滤参赛模式（csms） = 2,并且学年学期为本学年本学期的项目，实际可参与数>0,参赛模式：1.个人，2.团体
	 *
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TttzxmForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String xydm = user.getUserDep();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
        sql.append(" select t.*,");
        sql.append(" nvl(t1.sqrs,0) sqrs,");
        sql.append(" nvl(t2.tgrs,0) tgrs,");
        sql.append(" (t.kcyrs - nvl(t2.tgrs,0)) sjnum ,");//实际可参与数量= 可参与数-通过人数
        sql.append(" t3.sskmmc,");
        sql.append(" t4.xmjbmc,");
        sql.append(" t5.bmmc,");
      
        if(user.getUserType().equalsIgnoreCase("stu")){
        	  sql.append(" t6.xqmc,'");
        	  sql.append(xydm +"' xydm ");
        }else{
        	 sql.append(" t6.xqmc ");
        }
       
//        sql.append("  case when (((select xydm from view_xsjbxx where xh = ?) in (select xydm ");
//        sql.append("  from XG_SZTZ_XMCYXYB c where t.xmdm = c.xmdm ) or(t.sbbmdm not in(select xydm from XG_SZTZ_XMCYXYB a where a.xmdm=t.xmdm  )and nvl(t5.num,0)='0')) and exists (select 1 from xg_sztz_xmjg t11 where t.xmdm = t11.xmdm and t11.xfrdsqzt in ('0','3') and t11.xfrdjgzt = '0')) then");
//        sql.append("  '1' else '0' end sfksq");
        sql.append(" from xg_sztz_xmjg t");
        sql.append(" left join (select count(1) sqrs,xmdm from xg_sztz_ttxmsq group by xmdm) t1");
        sql.append(" on t.xmdm = t1.xmdm");
        sql.append(" left join (select count(1) tgrs,xmdm from xg_sztz_ttxmjg group by xmdm) t2");
        sql.append(" on t.xmdm = t2.xmdm");
        sql.append(" left join xg_sztz_sskm t3");
        sql.append(" on t.sskmdm = t3.sskmdm");
        sql.append(" left join xg_sztz_xmjb t4");
        sql.append(" on t.xmjbdm = t4.xmjbdm");
        sql.append(" left join zxbz_xxbmdm t5");
        sql.append(" on t.sbbmdm = t5.bmdm");
        sql.append(" left join xqdzb t6");
        sql.append(" on t.xq = t6.xqdm");
        sql.append(" ");
        sql.append(" ");
        sql.append(" ");
		sql.append(") t where  t.sqkg = '1' and t.csms = '2' and  t.xn= '"+Base.currXn+"' and t.xq = '"+Base.currXq+"'");
		sql.append(" and sjnum >'0'");
		sql.append(" and ((t.sqkssj <= to_char(sysdate, 'yyyyMMdd')");
		sql.append(" and t.sqjssj >= to_char(sysdate, 'yyyyMMdd')) or (t.sqkssj is null and t.sqjssj is null)");
		sql.append(" or (t.sqkssj is null and  t.sqjssj >= to_char(sysdate, 'yyyyMMdd'))");
		sql.append(" or (t.sqjssj is null and  t.sqkssj <= to_char(sysdate, 'yyyyMMdd')))");
		sql.append(" and (t.xfrdsqzt in ('0','3') and t.xfrdjgzt = '0')");
		if(user.getUserType().equalsIgnoreCase("stu")){
			sql.append(" and  (exists(select 1 from XG_SZTZ_XMCYXYB t7 where t.xmdm = t7.xmdm and t.xydm = t7.xydm)");
			sql.append(" or t.xmdm not in (select xmdm from  XG_SZTZ_XMCYXYB ))");
		}
		
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	
	/**
	 * 
	 * @描述:查询学生信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-25 上午11:05:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXsxx(String xh,String xmdm,String[]xhs){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select * from (select t2.xh,t2.xm,t2.xydm,t2.xymc,t2.bjmc,'"+xmdm+"' xmdm from view_xsjbxx t2) t");
		sql.append(" where  (exists(select 1 from XG_SZTZ_XMCYXYB t1 where t1.xmdm = t.xmdm and t.xydm = t1.xydm)");
		sql.append(" or xmdm not in (select xmdm from  XG_SZTZ_XMCYXYB ))");
		
		if(xhs != null){
			sql.append("and xh not in(");
			for (int i = 0; i < xhs.length; i++) {
				sql.append("?");
				if(xhs.length-1 != i ){
					sql.append(",");
				}
				paralist.add(xhs[i]);
			}
			sql.append(")");
		}
		sql.append(" and  xh = ?");
		paralist.add(xh);
		return dao.getMapNotOut(sql.toString(),paralist.toArray(new String[]{}));
	}
	
	/**
	 * 学生列表
	 */
	public List<HashMap<String, String>> getXsxxList(TttzxmForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhArr = new String[0];
		if(!"".equals(model.getXhs())){
			xhArr = model.getXhs().split(",");
		}
		StringBuilder sql = new StringBuilder("select a.* from (select a1.xz,a1.nj,a1.xh,a1.xm,a1.xb,a1.xymc,a1.zymc,");
		sql.append("a1.bjmc,a1.xydm,a1.zydm,a1.bjdm, '"+model.getXmdm()+"' xmdm from view_xsjbxx a1  ");
		if(xhArr.length > 0){
			sql.append(" where a1.xh not in(");
			for (int i = 0; i < xhArr.length; i++) {
				sql.append("'"+xhArr[i]+"' ");
				if(i < xhArr.length - 1){
					sql.append(", ");
				}
			}
			sql.append(")");
		}
		sql.append(")a where 1=1 ");
		sql.append(" and  (exists(select 1 from XG_SZTZ_XMCYXYB t1 where t1.xmdm = a.xmdm and a.xydm = t1.xydm)");
		sql.append(" or xmdm not in (select xmdm from  XG_SZTZ_XMCYXYB ))");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @描述:检查数据重复 一个人在一个项目中只能参加一个团队(一个人只能参加一个项目)
	 * @描述：为了控制一个团队项目中不存在重复的学号，此处判断重复性时不做申请还是结果的区分
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 上午11:32:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public String checkIsNotExists(String[] xh,String xmdm,String ttsqid){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select wm_concat(xh) str from xg_sztz_ttcy");
		sql.append(" where xh in( ");
		for (int i = 0; i < xh.length; i++) {
			sql.append("?");
			if(i != xh.length-1){
				sql.append(",");
			}
			paralist.add(xh[i]);
		}
		sql.append(" )");
		sql.append(" and xmdm = ?");
		
		paralist.add(xmdm);
		if(StringUtils.isNotNull(ttsqid)){
			sql.append(" and ttsqid != ?");
			paralist.add(ttsqid);
		}
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		String result = dao.getOneRs(sql.toString(), paralist.toArray(new String[]{}), "str");
		return result;
	}
	
	/**
	 * 
	 * @描述: 保存团队成员
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午01:49:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveTtcy(String ttsqid,String xmdm,String[] xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_sztz_ttcy where  ttsqid = '"+ttsqid+"'");
		ArrayList<String> sqlArr = new ArrayList<String>();
		sqlArr.add(sql.toString());
		for (int i = 0; i < xh.length; i++) {
			StringBuilder sqlLs = new StringBuilder();
			sqlLs.append(" insert into xg_sztz_ttcy (xmdm,ttsqid,xh) values(");
			sqlLs.append("'"+xmdm+"',");
			sqlLs.append("'"+ttsqid+"',");
			sqlLs.append("'"+xh[i]+"'");
			sqlLs.append(" )");
			sqlArr.add(sqlLs.toString());
		}
		
		int[] result =	dao.runBatch(sqlArr.toArray(new String[]{}));
		return result != null&&result.length == (xh.length+1) ? true:false;
		
	}
	
	/**
	 * 
	 * @描述: 检查团队名称是否重复(同一项目下队伍名称不可以重复)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午02:19:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tdmc
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkNameIsNotExists(String tdmc,String xmdm,String ttsqid,String flag){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> parameter = new ArrayList<String>();
		boolean result = true;
		//进行申请表和结果表的重复验证,qb(全部)
		if(flag.equalsIgnoreCase("qb")){
			sql.append(" select sum(flag) flag from");
			sql.append(" (select count(1) flag from ");
			sql.append(" xg_sztz_ttxmsq");
			sql.append(" where xmdm = ?");
			sql.append(" and tdmc = ?");
			parameter.add(xmdm);
			parameter.add(tdmc);
			if(StringUtils.isNotNull(ttsqid)){
				sql.append(" and ttsqid != ?");
				parameter.add(ttsqid);
			}
			sql.append(" union all");
			sql.append(" select count(1) flag from ");
			sql.append(" xg_sztz_ttxmjg");
			sql.append(" where xmdm = ?");
			sql.append(" and tdmc = ?");
			parameter.add(xmdm);
			parameter.add(tdmc);
			if(StringUtils.isNotNull(ttsqid)){
				sql.append(" and ttjgid != ?");
				parameter.add(ttsqid);
			}
			sql.append(")");
		}else if(flag.equalsIgnoreCase("jg")){
			sql.append(" select count(1) flag from ");
			sql.append(" xg_sztz_ttxmjg");
			sql.append(" where xmdm = ?");
			sql.append(" and tdmc = ?");
			parameter.add(xmdm);
			parameter.add(tdmc);
			if(StringUtils.isNotNull(ttsqid)){
				sql.append(" and ttjgid != ?");
				parameter.add(ttsqid);
			}
//			parameter.add(utilform.getXn());
//			parameter.add(utilform.getXq());
//			parameter.add(utilform.getXh());
		}
		String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			result = false;
		}
		return result;
	}
	
	/**
	 * 
	 * @描述: 检查是否已进入阶段维护
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午02:52:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotInJdwh(String jdcy){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_sztz_xm_jdwh where jdcy = ?");
		String num  = dao.getOneRs(sql.toString(),new String[]{jdcy}, "num");
		return "0".equals(num) ? true : false;
	}
	
	/**
	 * 
	 * @描述:获取排除队长外其他学生
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-27 上午09:01:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ttsqid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDyxxNotDz(String ttsqid,String dzxh){
		StringBuilder sql = new StringBuilder();
		sql.append("  select t.xh, t1.xm, t1.xymc, t1.bjmc ");
		sql.append("  from xg_sztz_ttcy t");
		sql.append("  left join view_xsjbxx t1");
		sql.append("  on t.xh = t1.xh");
		sql.append("  where t.xh != ?");
		sql.append("  and t.ttsqid =?");
		sql.append("  order by t1.xh,t1.xymc,t1.bjmc");
		return dao.getListNotOut(sql.toString(), new String[]{dzxh,ttsqid});
	}
	
	/**
	 * 
	 * @描述: 获取项目信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-27 上午09:41:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXmxxMap(String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
        sql.append(" select t.*,");
        sql.append(" nvl(t1.sqrs,0) sqrs,");
        sql.append(" nvl(t2.tgrs,0) tgrs,");
        sql.append(" t3.sskmmc,");
        sql.append(" t4.xmjbmc,");
        sql.append(" t5.bmmc,");
        sql.append(" t6.xqmc");
        sql.append(" from xg_sztz_xmjg t");
        sql.append(" left join (select count(1) sqrs,xmdm from xg_sztz_ttxmsq group by xmdm) t1");
        sql.append(" on t.xmdm = t1.xmdm");
        sql.append(" left join (select count(1) tgrs,xmdm from xg_sztz_ttxmjg group by xmdm) t2");
        sql.append(" on t.xmdm = t2.xmdm");
        sql.append(" left join xg_sztz_sskm t3");
        sql.append(" on t.sskmdm = t3.sskmdm");
        sql.append(" left join xg_sztz_xmjb t4");
        sql.append(" on t.xmjbdm = t4.xmjbdm");
        sql.append(" left join zxbz_xxbmdm t5");
        sql.append(" on t.sbbmdm = t5.bmdm");
        sql.append(" left join xqdzb t6");
        sql.append(" on t.xq = t6.xqdm");
        sql.append(" ");
        sql.append(" ");
        sql.append(" ");
		sql.append(") t where  t.xmdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xmdm});
	}
	
	/**
	 * 
	 * @描述:获取队长信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-29 上午09:50:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xmdm
	 * @param xhs
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getDzxx(String xh){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select t.xh,t.xm,t.xymc,t.bjmc from view_xsjbxx t");
		sql.append(" where  xh = ?");
		paralist.add(xh);
		return dao.getMapNotOut(sql.toString(),paralist.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除团体成员
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-29 上午11:27:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delTtcy(String[] ids) throws Exception{
		if(ids == null){
			return false;
		}else{
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from xg_sztz_ttcy where ttsqid in (");
			for (int i = 0; i < ids.length; i++) {
				sql.append("?");
				if(i != ids.length-1){
					sql.append(",");
				}
			}
			sql.append(")");
			return dao.runUpdate(sql.toString(), ids);
		}
		
	}
	
}
