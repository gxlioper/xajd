/**
 * @部门:学工产品事业部
 * @日期：2017-1-24 下午03:59:06 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.xxjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设-组织关系转出-信息结果模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xuwen[工号:1426]
 * @时间： 2017年2月10日 下午7:16:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XxjgDao extends SuperDAOImpl<XxjgForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_dtjs_zzgxzc_zzgxzcjgb");
		super.setKey("jgid");
		super.setClass(XxjgForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XxjgForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XxjgForm t, User user) throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT t1.jgid,t2.xh,t2.xm,t2.nj,t2.xymc,t2.xydm,t2.zymc,t1.sfsn,t2.csrq,");
		sql.append(" t2.sjhm,case t2.xz when '3' then '大学专科' else '大学本科' end xl,");
		sql.append(" t2.xb,floor(months_between(SYSDATE, to_date(substr(t2.sfzh,7,8),'yyyyMMdd'))/ 12) nl,t2.mzmc,");
		sql.append(" t2.sfzh,t1.sqdw,t1.dfjzrq,t1.jsxbh,");
		sql.append(" t2.zydm,t2.bjmc,t2.bjdm,t2.zzmmmc,t1.szdzb,(SELECT dzbmc FROM XG_DTJS_ZZGXZC_DZBDMB WHERE dzbdm = t1.szdzb) szdzbmc,");
		sql.append("t1.JSDZZ,t1.sqsj,t1.sjly  FROM xg_dtjs_zzgxzc_zzgxzcjgb t1 ");
		sql.append("LEFT JOIN VIEW_XSBFXX t2 ON t1.xh = t2.xh) t WHERE 1 = 1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @描述:判断信息结果中是否已有某学生记录
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月14日 下午1:23:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xxjgForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(XxjgForm xxjgForm) {
		String sql = "select count(1) count from xg_dtjs_zzgxzc_zzgxzcjgb where xh = ? ";
		String count = dao.getOneRs(sql, new String[]{xxjgForm.getXh()}, "count");
		return Integer.parseInt(count)>0;
	}

	/** 
	 * @描述:判断信息结果中介绍信标号是否已被使用
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月14日 下午1:23:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xxjgForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isJsxbhRepeat(XxjgForm xxjgForm) {
		String sql = "select count(1) count from xg_dtjs_zzgxzc_zzgxzcjgb where jsxbh = ?";
		
		String jsxbh = xxjgForm.getJsxbh();
		String jgid = xxjgForm.getJgid();
		List<String> inputList = new ArrayList<String>();
		inputList.add(jsxbh);
		
		if(!StringUtil.isNull(jgid)){
			sql += "and jgid != ?";
			inputList.add(jgid);
		}
		String [] inputValue = inputList.toArray(new String[]{});
		String count = dao.getOneRs(sql, inputValue, "count");
		return Integer.parseInt(count)>0;
	}
	
	/**
	 * @throws Exception  
	 * @描述:重写getModel，获得所在党支部名称
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月14日 下午1:20:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xxjgForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public XxjgForm getModel(String jgid) throws Exception{
		String sql = "select t1.*,t2.dzbmc szdzbmc from  xg_dtjs_zzgxzc_zzgxzcjgb t1 left join xg_dtjs_zzgxzc_dzbdmb t2 on t1.szdzb = t2.dzbdm where t1.jgid = ?";
		return super.getModel(sql, new String[]{jgid});
	}

	/** 
	 * @描述:根据id数组查询
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月15日 下午5:08:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXxjgFormList(String [] ids) {
		StringBuilder sql = new StringBuilder("select t1.*,t2.dzbmc szdzbmc from  xg_dtjs_zzgxzc_zzgxzcjgb t1 left join xg_dtjs_zzgxzc_dzbdmb t2 on t1.szdzb = t2.dzbdm ");
		sql.append(" where t1.jgid = ");
		for(int i=0;i<ids.length;i++){
			sql.append(" ? ");
			if(i!=ids.length-1){
				sql.append(" or t1.jgid = ");
			}
		}
		return dao.getListNotOut(sql.toString(),ids);
	}
	
	/**
	 * 
	 * @描述:党组织关系结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-18 下午05:37:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getDzcgxJgMap(String jgid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,t1.dzbmc,t2.xm,t2.mz,t2.sfzh,t2.sjhm,t2.sfzh,to_char(sysdate,'yyyy') - nvl(substr(t2.sfzh,7,4),0) nl,to_char(to_date(t.dfjzrq,'yyyy-mm-dd hh24:mi:ss'),'yyyy') year,to_char(to_date(t.dfjzrq,'yyyy-mm-dd hh24:mi:ss'),'mm') mon");
		sql.append(" from xg_dtjs_zzgxzc_zzgxzcjgb t");
		sql.append(" left join XG_DTJS_ZZGXZC_DZBDMB t1");
		sql.append(" on t.szdzb = t1.dzbdm");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t.xh = t2.xh");
		sql.append(" where t.jgid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{jgid});
	}

}
