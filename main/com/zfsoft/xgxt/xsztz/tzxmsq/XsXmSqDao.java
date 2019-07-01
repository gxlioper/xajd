/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午04:51:51 
 */  
package com.zfsoft.xgxt.xsztz.tzxmsq;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-7-9 下午04:51:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsXmSqDao extends SuperDAOImpl<XsXmSqForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsXmSqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsXmSqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.sskmmc,t2.sskmdm,t4.xmjbmc,t4.xmjbdm,t3.xmkssj,t3.xmmc xmmc1,t3.jcxf,t5.bjdm,t5.bjmc,t6.bmmc sbbmmc,t5.xb,");
		sql.append("t5.nj,t5.zydm,t5.zymc,t5.xydm,t5.xymc,t5.xm,decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc,  ");
		sql.append(" case when t3.sqkg = 1 and sysdate between to_date(nvl(t3.sqkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and   ");
		sql.append("  to_date(nvl(t3.sqjssj, '9999-01-01 00:00'), 'yyyy-mm-dd hh24:mi') + 1 then  '1'  else '0' end sqkg,t7.xqmc   ");
		sql.append(" from xg_sztz_xsxmsq  t1");
		sql.append(" left join xg_sztz_xmjg t3");
		sql.append(" on t1.xmdm = t3.xmdm");
		sql.append(" left join xg_sztz_sskm t2 ");
		sql.append(" on t3.sskmdm = t2.sskmdm");
		sql.append(" left join xg_sztz_xmjb  t4");
		sql.append(" on t3.xmjbdm = t4.xmjbdm");
		sql.append(" left join view_xsjbxx t5" );
		sql.append(" on t1.xh = t5.xh");
		sql.append(" left join zxbz_xxbmdm t6");
		sql.append(" on t3.sbbmdm = t6.bmdm");
		sql.append(" left join xqdzb t7");
		sql.append(" on t1.xq = t7.xqdm");
		sql.append(" )t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(XsXmSqForm.class);
		super.setKey("sqid");
		super.setTableName("xg_sztz_xsxmsq");
	}
	
	/**
	 * 
	 * @描述:获取申请审核开关
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-10 下午04:29:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getSqShKg() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg ");
		sql.append(" from xg_sztz_xmjg t where 1=1 and t.xmdm = ?");
		return dao.getOneRs(sql.toString(),new String[]{},new String[]{"sqkg"});
	}
	
	/**
	 * 
	 * @描述:重复判断
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-10 下午04:37:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkExistForSave(XsXmSqForm model) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select max(sqid) sqid from");
		sql.append("(select count(sqid) sqid from xg_sztz_xsxmsq t where t.xh = ? and t.xn = ? and t.xmdm = ?  and t.xq = ? ");
		sql.append(" union select count(jgid) sqid from xg_sztz_xs_sqjg t where t.xh = ? and t.xn = ? and t.xmdm = ? and t.xq = ? )");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXmdm(),model.getXq(),
			model.getXh(),model.getXn(),model.getXmdm(),model.getXq()}, "sqid");
		if (!num.equals("0")){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:重复判断2
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-10 下午04:37:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkExistForSave2(XsXmSqForm model) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("  select count(jgid) sqid from xg_sztz_xs_sqjg t where t.xh = ? and t.xn = ? and t.xmdm = ? and t.xq = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXmdm(),model.getXq()}, "sqid");
		if (!num.equals("0")){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:只查询个人项目（csms = 1）参赛模式  =1
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-13 上午11:03:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlist(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select t1.*, nvl(t2.sqrs,0) sqrs from (select t.*, nvl(t1.tgrs,0) tgrs");
		sql.append(" from (select t.*, t1.sskmmc, t2.xmjbmc, t3.bmmc sbbmmc, t4.xqmc , case  when exists (select 1");
        sql.append("   from xg_sztz_xsxmsq c  where t.xmdm = c.xmdm  and xh = ?) then  '1' else '0'  end sfsq, ");
        sql.append("  case when (((select xydm from view_xsjbxx where xh = ?) in (select xydm ");
        sql.append("  from XG_SZTZ_XMCYXYB c where t.xmdm = c.xmdm ) or(t.sbbmdm not in(select xydm from XG_SZTZ_XMCYXYB a where a.xmdm=t.xmdm  )and nvl(t5.num,0)='0')) and exists (select 1 from xg_sztz_xmjg t11 where t.xmdm = t11.xmdm and t11.xfrdsqzt in ('0','3') and t11.xfrdjgzt = '0')) then");
        sql.append("  '1' else '0' end sfksq");
		sql.append(" from xg_sztz_xmjg t  left join (select count(1) num ,xmdm from XG_SZTZ_XMCYXYB group by xmdm ) t5 on t.xmdm=t5.xmdm ");
		sql.append(" left join xg_sztz_sskm t1");
		sql.append(" on t1.sskmdm = t.sskmdm");
		sql.append(" left join xg_sztz_xmjb t2 ");
		sql.append(" on t.xmjbdm = t2.xmjbdm");
		sql.append(" left join zxbz_xxbmdm  t3 ");
		sql.append(" on t.sbbmdm = t3.bmdm ");
		sql.append(" left join xqdzb t4 ");
		sql.append(" on t.xq = t4.xqdm");
		sql.append(" where  t.sqkg = 1 and t.csms = '1') t");
		sql.append(" left join (select count(1) tgrs, t.xmdm");
		sql.append(" from xg_sztz_xs_sqjg t where xn = ? and xq = ?");
		sql.append("  group by t.xmdm) t1");
		sql.append("  on t.xmdm = t1.xmdm) t1 left join");
		sql.append("  (select count(1) sqrs, t.xmdm");
		sql.append("  from xg_sztz_xsxmsq t");
		sql.append("  where xn = ? and xq = ? and shzt <> 3 and shzt <> 2 group by t.xmdm) t2 on t1.xmdm = t2.xmdm");
			sql.append("  where (t1.sqkssj <= to_char(sysdate, 'yyyyMMdd')");
			sql.append("  and t1.sqjssj >= to_char(sysdate, 'yyyyMMdd')) or (t1.sqkssj is null and t1.sqjssj is null)");
			sql.append("  or (t1.sqkssj is null and  t1.sqjssj >= to_char(sysdate, 'yyyyMMdd'))");
			sql.append("  or (t1.sqjssj is null and  t1.sqkssj <= to_char(sysdate, 'yyyyMMdd'))");
		sql.append(" )t where 1= 1  ");
		sql.append(" order by sfrm desc ");
		return dao.getListNotOut(sql.toString(), new String[]{xh,xh,Base.currXn,Base.currXq,Base.currXn,Base.currXq});
	}
	
	//获取审批流
//	public String getShlcID() {
//		StringBuffer sql = new StringBuffer();
//		sql.append(" select splc from xg_sztz_xmjg ");
//		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
//	}
	
	//获取sqid
	public String getSqid(XsXmSqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select sqid from xg_sztz_xsxmsq where xh = ? and  xn = ? and xmdm = ? and xq = ? ");
		return dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXmdm(),model.getXq()}, "sqid");
	}
	
	/**
	 * 
	 * @描述:获取修改查看时的活动信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-15 下午08:09:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getHdMap(String xmdm,String xn,String xq){
		StringBuffer sql = new StringBuffer();
		sql.append("  select t.*,nvl(t1.tgrs,0) tgrs from ");
		sql.append("  (select t.*,nvl(t1.sqrs,0) sqrs from ");
		sql.append("  (select t.*, t1.sskmmc, t2.xmjbmc, t3.bmmc sbbmmc,t4.xqmc xqmc ");
		sql.append("  from xg_sztz_xmjg t");
		sql.append("  left join xg_sztz_sskm t1" );
		sql.append("  on t1.sskmdm = t.sskmdm");
		sql.append("  left join xg_sztz_xmjb t2" );
		sql.append("  on t.xmjbdm = t2.xmjbdm");
		sql.append("  left join zxbz_xxbmdm t3 ");
		sql.append("  on t.sbbmdm = t3.bmdm ");
		sql.append("  left join xqdzb t4 ");
		sql.append("  on t4.xqdm = t.xq ");
		sql.append("  where  t.xmdm = ? ) t left join (select count(1) sqrs,xmdm  from xg_sztz_xsxmsq  where xmdm = ? and xn = ? and xq= ? group by xmdm ) t1 ");
		sql.append("  on t.xmdm = t1.xmdm)t ");
		sql.append("  left join (select count(1) tgrs,xmdm  from xg_sztz_xs_sqjg  where xmdm = ? and xn = ? and xq= ? group by xmdm )t1 on t.xmdm = t1.xmdm ");
		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,xmdm,xn,xq,xmdm,xn,xq});
	}
	
}
