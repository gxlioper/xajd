/**
 * @部门:学工产品事业部
 * @日期：2015-11-23 上午08:39:52 
 */  
package com.zfsoft.xgxt.xszz.zzdy.zzbtff;

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

public class ZzdyBtffDao extends SuperDAOImpl<ZzdyBtffForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzdyBtffForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzdyBtffForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append(" select t1.*,t2.xmmc,t2.pdxn,t2.pdxq,t3.xqmc,t4.xm,t4.xymc,t4.xydm,t4.zydm,t4.zymc,t4.bjdm,t4.bjmc from xg_xszz_zzdy_zzffb t1 ");
		sql.append("left join xg_xszz_new_zzxmdmb t2 on t1.xmdm=t2.xmdm left join xqdzb t3 on t2.pdxq=t3.xqdm");
		sql.append(" left join view_xsbfxx t4 on t1.xh=t4.xh )t where 1=1");
		sql.append(searchTj);
		sql.append(" order by pdxn desc,ffyf desc,xh desc");
		return getPageList(t, sql.toString(), inputV);
	}

	public boolean isHave(ZzdyBtffForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_xszz_zzdy_zzffb where ffyf=?");
 		String num= dao.getOneRs(sql.toString(), new String[]{model.getFfyf()}, "num");
 		return Integer.parseInt(num)>0;
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
	public boolean btff(ZzdyBtffForm model) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("MERGE INTO xg_xszz_zzdy_zzffb t1");
		sql.append(" USING (");
		sql.append("select * from (select a.*,b.ffksyf,b.ffjsyf from xg_xszz_new_zzdy_zzmdb a");
		sql.append(" left join xg_xszz_new_zzdy_xmszb b on a. xmdm=b.xmdm ) where ? between ffksyf and ffjsyf and ffzt='1')");
		sql.append(" t2 ON (t1.xh=t2.xh and t1.xmdm=t2.xmdm and t1.ffyf=?)");
		sql.append(" when matched then ");
		sql.append("update set t1.ffje = t2.yffje ,t1.sjscsj=sysdate ");
		sql.append("WHEN NOT MATCHED THEN");
		sql.append("  INSERT (xh,xmdm,ffyf, ffje)");
		sql.append("  VALUES (t2.xh, t2.xmdm, ?, t2.yffje)");
		return dao.runUpdate(sql.toString(), new String[]{model.getFfyf(),model.getFfyf(),model.getFfyf()});
	}
	public List<HashMap<String, String>> getFfyfList() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select (select dqnd from xtszb)||lpad(level,2,0)ffyf from dual connect by level<13");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	public List<HashMap<String, String>> getFfjlList(String xh,String xmdm) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,b.xmmc from xg_xszz_zzdy_zzffb a left join xg_xszz_new_zzxmdmb b");
		sql.append(" on a.xmdm=b.xmdm where a.xh=? and a.xmdm=? order by a.sjscsj desc ");
		return dao.getListNotOut(sql.toString(), new String[]{xh,xmdm});
	}
	
	
	@Override
	protected void setTableInfo() {
		super.setClass(ZzdyBtffForm.class);
		super.setKey("id");
		super.setTableName("xg_xszz_zzdy_zzffb");

	}

}
