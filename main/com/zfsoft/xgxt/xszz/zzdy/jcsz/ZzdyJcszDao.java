/**
 * @部门:学工产品事业部
 * @日期：2015-11-23 上午08:39:52 
 */  
package com.zfsoft.xgxt.xszz.zzdy.jcsz;

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

public class ZzdyJcszDao extends SuperDAOImpl<ZzdyJcszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzdyJcszForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzdyJcszForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select t1.*,t2.xmmc,t3.xqmc,case when t1.sqkg = 1 and sysdate between to_date(nvl(t1.kssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t1.jssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end isopen ");
		sql.append(" from xg_xszz_new_zzdy_xmszb t1 left join xg_xszz_new_zzxmdmb t2 on t1.xmdm=t2.xmdm left join xqdzb t3 on t2.sqxq=t3.xqdm)t where 1=1");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	public boolean isHave(ZzdyJcszForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_xszz_new_zzdy_xmszb where xmdm=?");
		if(null!=model.getSzid()){
			sql.append(" and szid<>'"+model.getSzid()+"' ");	
		}
 		String num= dao.getOneRs(sql.toString(), new String[]{model.getXmdm()}, "num");
 		return Integer.parseInt(num)>0;
	}
	
	public boolean isUsed(String[] values) {
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from XG_XSZZ_NEW_ZZDY_ZZMDB where xmdm in(select xmdm from xg_xszz_new_zzdy_xmszb where szid in(");
		for (int i = 0; i < values.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params.add(values[i]);
		}
		sql.append(" )) ");
 		String num= dao.getOneRs(sql.toString(), params.toArray(new String[]{}), "num");
 		return Integer.parseInt(num)>0;
	}
	/**
	 * 
	 * @描述:获取项目列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-11-23 上午11:43:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmList(String xn,String xq) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_xszz_new_zzxmdmb  where pdxn=? ");
		if("".equals(xq)){
			sql.append(" and pdxq is null");
		}else{
			sql.append("and pdxq='"+xq+"'");
		}
		return dao.getListNotOut(sql.toString(), new String[]{xn});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(ZzdyJcszForm.class);
		super.setKey("szid");
		super.setTableName("xg_xszz_new_zzdy_xmszb");

		
	}

}
