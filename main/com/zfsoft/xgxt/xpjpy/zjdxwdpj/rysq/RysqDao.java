/**
 * @部门: 学工产品(1)部
 * @日期： 2018-7-3 下午05:02:22 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.rysq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 我的评奖_荣誉申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-7-3 下午05:01:26 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RysqDao extends SuperDAOImpl<RysqForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RysqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RysqForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//数据范围
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select t1.*,");
		sql.append("                      t5.xm,");
		sql.append("                      nvl(t3.nj, t5.nj) nj,");
		sql.append("                      nvl(t3.xydm, t5.xydm) xydm,");
		sql.append("                      nvl(t3.xymc, t5.xymc) xymc,");
		sql.append("                      nvl(t3.zydm, t5.zydm) zydm,");
		sql.append("                      nvl(t3.zymc, t5.zymc) zymc,");
		sql.append("                      nvl(t3.bjdm, t5.bjdm) bjdm,");
		sql.append("                      nvl(t3.bjmc, t5.bjmc) bjmc,");
		sql.append("                      case when t4.sqkg = 1 and sysdate between to_date(nvl(t4.sqkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi')");
		sql.append("                        and to_date(nvl(t4.sqjssj, '2020-01-01 00:00'), 'yyyy-mm-dd hh24:mi') + 1 then 'true' else 'false' end isopen,");
		sql.append("                      t4.xmmc,t4.lxdm,t4.xmje,t4.xssx,t4.xmsm,t6.lxmc,");
		sql.append("                      decode(t1.shzt, '0', '未提交', '1', '通过', '2', '不通过', '3', '已退回', '5', '审核中', t1.shzt) shztmc");
		sql.append("                from xg_zjdx_pjpy_xmsq t1");
		sql.append("                left join xg_zjdx_pjpy_cpmdb t2 on t1.xh = t2.xh and t1.xn = t2.xn");
		sql.append("                left join view_njxyzybj_all t3 on t2.bjdm = t3.bjdm");
		sql.append("                left join xg_zjdx_pjpy_pjxmb t4 on t1.xmdm = t4.xmdm");
		sql.append("                left join view_xsbfxx t5 on t1.xh = t5.xh");
		sql.append("                left join xg_zjdx_pjpy_xmlx t6 on t4.lxdm = t6.lxdm");
		sql.append(")a where 1 = 1 and lxmc = '荣誉称号'");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		/*主表为项目申请表*/
		super.setTableName("xg_zjdx_pjpy_xmsq");
		super.setKey("id");
		super.setClass(RysqForm.class);
	}
	
	/**
	 * @描述: 取学生最近一条申请信息，主要是取外语水平、宿舍电话、担任社会工作职务、个人学习经历、参加科研情况、对设奖单位的认识
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-4 上午09:39:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getLatestSqxx(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.* from xg_zjdx_pjpy_xmsq t1 ");
		sql.append("left join xg_zjdx_pjpy_pjxmb t2 on t1.xmdm = t2.xmdm ");
		sql.append("left join xg_zjdx_pjpy_xmlx t3 on t2.lxdm = t3.lxdm ");
		sql.append("where t1.xh = ? ");
		sql.append("and t3.lxmc = '荣誉称号' ");
		sql.append("order by t1.sqsj desc ");
		sql.append(") where rownum = 1");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * @描述: 本周期未申请项目列表
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-4 下午02:39:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param sqlx
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getKsqInfoList(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		XmwhService xmwhService = new XmwhService();
		sql.append("select t1.* from xg_zjdx_pjpy_pjxmb t1 ");
		sql.append("left join xg_zjdx_pjpy_xmlx t2 on t1.lxdm = t2.lxdm ");
		sql.append("where not exists (select 1 from xg_zjdx_pjpy_xmsq t3 where t1.xmdm = t3.xmdm and t3.xh = ? and t3.shzt != '3') ");
		sql.append("and t1.xn = '"+xmwhService.getCsszMap().get("xn")+"' ");
		sql.append("and t1.sqkg = '1' ");
		sql.append("and( sysdate between to_date(nvl(t1.sqkssj, '2018-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and to_date(nvl(t1.sqjssj, '2038-01-01 00:00'), 'yyyy-mm-dd hh24:mi') ) ");
		String sql_sqlx = " and t2.lxmc = '荣誉称号' ";
		sql.append(sql_sqlx);
		sql.append("order by to_number(t1.xssx) ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * @描述: 本周期已申请的项目
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-4 下午02:49:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param sqlx
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getYsqInfoList(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		XmwhService xmwhService = new XmwhService();
		sql.append("select t1.* from xg_zjdx_pjpy_pjxmb t1 ");
		sql.append("left join xg_zjdx_pjpy_xmlx t2 on t1.lxdm = t2.lxdm ");
		sql.append("where exists (select 1 from xg_zjdx_pjpy_xmsq t3 where t1.xmdm = t3.xmdm and t3.xh = ? and t3.shzt != '3') ");
		sql.append("and xn = '"+xmwhService.getCsszMap().get("xn")+"' ");
		String sql_sqlx = " and t2.lxmc = '荣誉称号' ";
		sql.append(sql_sqlx);
		sql.append("order by to_number(t1.xssx) ");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * @描述: 查询学生学号和学院代码
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-4 下午04:29:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getPjxmXsxxMap(RysqForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t2.xh,t4.xydm from xg_zjdx_pjpy_cpmdb t2 ");
		sql.append("left join view_njxyzybj_all t4 on t2.bjdm = t4.bjdm ");
		sql.append("where t2.xh = ? and t2.xn = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getXh(),t.getXn()});
	}
	
	/**
	 * @描述: 查询已设置人数的荣誉称号项目
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-8-8 下午08:05:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<String> 返回类型 
	 * @throws
	 */
	public List<String> getRychList(RysqForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select distinct xmmc from xg_zjdx_pjpy_pjxmb t1 ");
		sql.append("left join xg_zjdx_pjpy_xmlx t2 on t1.lxdm = t2.lxdm ");
		sql.append("left join xg_pjpy_new_rsszb t3 on t1.xmdm = t3.xmdm ");
		sql.append("where t1.xn = ? and t2.lxmc = '荣誉称号' and t3.zzme is not null ");
		sql.append(")");
		return dao.getList(sql.toString(), new String[]{model.getXn()}, "xmmc");
	}
}
