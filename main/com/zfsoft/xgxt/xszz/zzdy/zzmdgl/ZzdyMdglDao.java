/**
 * @部门:学工产品事业部
 * @日期：2015-11-23 上午08:39:52 
 */  
package com.zfsoft.xgxt.xszz.zzdy.zzmdgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-11-23 上午08:39:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzdyMdglDao extends SuperDAOImpl<ZzdyMdglForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzdyMdglForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzdyMdglForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append(" select t1.*,case when t1.ffzt='1' then '正常发放' when t1.ffzt='0' then '暂停发放' when t1.ffzt='-1' then '终止发放' else t1.ffzt end ffztmc ,");
		sql.append("t3.xqmc,t4.nj,t4.xm,t4.xb,t4.xymc,t4.zymc,t4.bjdm,t4.bjmc,t4.xydm,t4.zydm,t5.ffys from XG_XSZZ_NEW_ZZDY_ZZMDB t1 ");
		sql.append("left join xg_xszz_new_zzxmdmb t2 on t1.xmdm=t2.xmdm left join xqdzb t3 on t2.sqxq=t3.xqdm");
		sql.append(" left join view_xsbfxx t4 on t1.xh=t4.xh left join xg_xszz_new_zzdy_xmszb t5 on t1.xmdm=t5.xmdm)t where 1=1");
		sql.append(searchTj);
		sql.append(" order by xmmc,xymc,xn,xq desc ");
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:获取可同步学生列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-11-23 上午11:43:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public boolean zzmdTb(String xn,String xq) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("MERGE INTO xg_xszz_new_zzdy_zzmdb t1");
		sql.append(" USING (");
		sql.append("select a.*,b.xmdm,t5.ffys,ceil(a.je/t5.ffys) yffje from xg_xszz_new_zzxmjgb a left join xg_xszz_new_zzxmdmb b on a.xmmc=b.xmmc and a.pdxn=b.pdxn and a.pdxq=b.pdxq left join xg_xszz_new_zzdy_xmszb t5 on b.xmdm=t5.xmdm where a.xmmc||a.pdxn||a.pdxq  in(select t.xmmc||t.xn||t.xq from (");
		sql.append("select t1.*,t2.xmmc,case when t1.sqkg = 1 and sysdate between to_date(nvl(t1.kssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t1.jssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end isopen ");
		sql.append(" from xg_xszz_new_zzdy_xmszb t1 left join xg_xszz_new_zzxmdmb t2 on t1.xmdm=t2.xmdm )t where isopen='1'))t2");
		sql.append(" ON (t1.xh=t2.xh and t1.xmdm=t2.xmdm and t1.xn=t2.pdxn and t1.xq=t2.pdxq)");
		sql.append("WHEN NOT MATCHED THEN");
		sql.append("  INSERT (xh, xn, xq, xmdm,xmmc,yffje, zzzje)");
		sql.append("  VALUES (t2.xh, t2.pdxn, t2.pdxq, t2.xmdm,t2.xmmc,t2.yffje, t2.je)");
		return dao.runUpdate(sql.toString(), new String[]{});
		
	}
	public boolean insertBgLog(ZzdyMdglForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into xg_xszz_zzdy_jebgb (xmdm, xh, bgr,bgqzt,bgqje,bghzt,bghje,bgly) values(?,?,?,?,?,?,?,?)");
		return dao.runUpdate(sql.toString(), new String[]{t.getXmdm(),t.getXh(),t.getBgr(),t.getBgqzt(),t.getBgqje(),t.getBghzt(),t.getBghje(),t.getBgly()});
	}
	/**
	 * 
	 * @描述:变更记录列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-11-25 下午08:14:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBgjlList(ZzdyMdglForm model)
	throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,case when a.bgqzt='1' then '正常发放' when a.bgqzt='0' then '暂停发放' when a.bgqzt='-1' then '终止发放' else a.bgqzt end ffztmc  from xg_xszz_zzdy_jebgb a where a.xh=? and a.xmdm=? ");
		sql.append(" order by bgsj desc");
		return dao.getListNotOut(sql.toString(), new String[]{model.getXh(),model.getXmdm()});
	}
	public ZzdyMdglForm getModel(ZzdyMdglForm t) throws Exception{
		String sql = "select a.*,b.xmmc ,a.xn||c.xqmc pdzq from XG_XSZZ_NEW_ZZDY_ZZMDB a left join xg_xszz_new_zzxmdmb b on a.xmdm = b.xmdm left join xqdzb c on a. xq= c.xqdm where a.id=? ";
		return getModel(sql, new String[]{t.getId()});
		
	}
	@Override
	protected void setTableInfo() {
		super.setClass(ZzdyMdglForm.class);
		super.setKey("id");
		super.setTableName("XG_XSZZ_NEW_ZZDY_ZZMDB");

	}

}
