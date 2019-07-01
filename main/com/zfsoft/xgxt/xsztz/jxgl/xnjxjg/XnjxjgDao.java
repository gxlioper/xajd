/**
 * @部门:学工产品事业部
 * @日期：2016-1-27 下午04:19:00 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xnjxjg;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-1-27 下午04:19:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnjxjgDao extends SuperDAOImpl<XnjxjgForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XnjxjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XnjxjgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.sskmmc,t2.sskmdm,t4.xmjbmc,t4.xmjbdm,t3.xmkssj,t3.xmmc xmmc1,t3.jcxf,t5.bjdm,t5.bjmc,t6.bmmc sbbmmc,t5.xb,");
		sql.append("t5.nj,t5.zydm,t5.zymc,t5.xydm,t5.xymc,t5.xm, ");
		sql.append(" case when t3.sqkg = 1 and sysdate between to_date(nvl(t3.sqkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and   ");
		sql.append("  to_date(nvl(t3.sqjssj, '9999-01-01 00:00'), 'yyyy-mm-dd hh24:mi') + 1 then  '1'  else '0' end sqkg,t7.xqmc,t8.jxmc,t8.fjxf   ");
		sql.append(" from XG_SZTZ_XS_SQJG t1");
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
		sql.append(" left join xg_sztz_xm_jx t8");
		sql.append(" on t1.ylzd1 = t8.jgid");
		sql.append(" where t1.ylzd1 is not null)t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(XnjxjgForm.class);
		super.setTableName("XG_SZTZ_XS_SQJG");
		super.setKey("jgid");
	}
	
	//从数据流过来的数据添加结果
	public boolean runInsert(XnjxjgForm form) throws Exception{
		String sql = "update XG_SZTZ_XS_SQJG set ylzd1=?,sjly1=?,lylcywid1=?,ylzd2=? where xh=? and xmdm=? and xq=? and xn=?";
		return dao.runUpdate(sql, new String[]{form.getJxid(),form.getSjly1(),form.getLylcywid1(),form.getSfqq(),form.getXh(),form.getXmdm(),form.getXq(),form.getXn()});
	}
	//删除数据流过来的数据结果，撤销用
	public boolean runDel(XnjxjgForm form) throws Exception{
		String sql = "update XG_SZTZ_XS_SQJG set ylzd1='',sjly1='',lylcywid1='',ylzd2='' where xh=? and xmdm=? and xq=? and xn=? and lylcywid1=?";
		return dao.runUpdate(sql, new String[]{form.getXh(),form.getXmdm(),form.getXq(),form.getXn(),form.getLylcywid1()});
	}
	
	public boolean runUpdate(XnjxjgForm form) throws Exception{
		String sql = "update XG_SZTZ_XS_SQJG set ylzd1=?,sjly1=?,lylcywid1=?,ylzd2=? where xh=? and xmdm=? and xq=? and xn=?";
		return dao.runUpdate(sql, new String[]{form.getJxid(),form.getSjly1(),form.getLylcywid1(),form.getSfqq(),form.getXh(),form.getXmdm(),form.getXq(),form.getXn()});
	}
	
	
	/** 
	 * @描述:专用于结果模块的删除
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-29 上午09:27:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean runDelForJg(XnjxjgForm form) throws Exception{
		String sql = "update XG_SZTZ_XS_SQJG set ylzd1='',sjly1='',lylcywid1='',ylzd2='' where xh=? and xmdm=? and xq=? and xn=?";
		return dao.runUpdate(sql, new String[]{form.getXh(),form.getXmdm(),form.getXq(),form.getXn()});
	}
	/** 
	 * @描述:专用于结果模块的插入
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-29 上午09:27:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean runInsertForjg(XnjxjgForm form) throws Exception{
		String sql = "update XG_SZTZ_XS_SQJG set ylzd1=?,sjly1=?,lylcywid1=?,ylzd2=? where xh=? and xmdm=? and xq=? and xn=?";
		return dao.runUpdate(sql, new String[]{form.getJxid(),form.getSjly1(),form.getLylcywid1(),form.getSfqq(),form.getXh(),form.getXmdm(),form.getXq(),form.getXn()});
	}
	
	
	/** 
	 * @描述:得到奖项信息
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-2 上午09:05:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getJginfo(XnjxjgForm form){
		String sql = "select * from XG_SZTZ_XS_SQJG where xmdm = ? and xh = ? and xn = ? and xq = ? and ylzd1 = ? ";
		return dao.getMapNotOut(sql, new String []{form.getXmdm(),form.getXh(),form.getXn(),form.getXq(),form.getJxid()});
	}
	
	
	/** 
	 * @描述:得到奖项id
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-2 上午09:39:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getJg(XnjxjgForm form){
		String sql = "select * from XG_SZTZ_XS_SQJG where xmdm = ? and xh = ? and xn = ? and xq = ? ";
		return dao.getMapNotOut(sql, new String []{form.getXmdm(),form.getXh(),form.getXn(),form.getXq()});
	}
	
	
	/** 
	 * @描述:得到可申请奖项的项目
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-2 上午09:09:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYiShen(String xh,String xmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.sskmdm,t2.lxdh,t3.sskmmc,t4.xmjbmc,t5.xqmc,t6.bmmc sbbmmc,t2.kcyrs,t2.xmkssj,nvl(t8.xm,t10.xm) sbrxm,case when t2.sfsljx='1' then '是' else '否' end sfsljxmc,t2.jcxf from XG_SZTZ_XS_SQJG t1 " );
		sql.append(" left join xg_sztz_xmsq t2 on t1.xmdm = t2.xmdm ");
		sql.append(" left join xg_sztz_sskm t3 on t3.sskmdm = t2.sskmdm ");
		sql.append(" left join xg_sztz_xmjb t4 on t2.xmjbdm = t4.xmjbdm");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		sql.append(" left join zxbz_xxbmdm t6 on t2.sbbmdm=t6.bmdm ");
		sql.append(" left join yhb t8 on t2.sbr=t8.yhm left join xsxxb t10 on t2.sbr=t10.xh");
		sql.append(" where t1.xh = ? and t1.xn = ? and t1.xq = ? and not exists(select 1 from XG_SZTZ_JXSQ t10 where t10.xmdm = t1.xmdm and t10.xh = ?) and t1.xmdm <> ? and t1.xmdm in (select xmdm from xg_sztz_xm_jx)");
		sql.append(" ) t");
		return dao.getListNotOut(sql.toString(), new String[]{xh,Base.currXn,Base.currXq,xh,xmdm});	
	}
	
}
