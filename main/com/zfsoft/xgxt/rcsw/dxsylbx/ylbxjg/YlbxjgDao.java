/**
 * @部门:学工产品事业部
 * @时间： 2014-1-10 下午04:01:59 
 */  
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 医疗保险结果管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：Dlq[工号:995]
 * @时间： 2014-1-10 下午04:01:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YlbxjgDao extends SuperDAOImpl<YlbxjgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("xg_rcsw_ylbx_ylbxjgb");
		super.setKey("yljgid");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlbxjgForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (select t1.yljgid,t1.xh,t1.xn,t1.xq,t5.xqmc,t1.sqsj,t1.cbsj,t1.zjsyrxm,t1.zjh, ");
		sql.append(" t1.cbzkdm,t1.sqly,t1.sjly,t1.ylsqid,t2.xm,t2.xb,t2.bjmc, ");
		sql.append(" t2.lxdh,t2.xydm,t2.xymc,t2.zymc,t2.zydm,t2.bjdm,t2.nj,t2.jgmc,(t3.czqebzmc || '  ' || t7.czqebzmc || '  ' || t8.czqebzmc) bzlxmc, ");
		sql.append(" ( case when t1.czqebzdm is not null  then '有' else '无' end ) czqebzmc, ");
		sql.append(" ( case when t1.cbzkdm is not null  then '已参保' else '未参保' end ) cbzkmc ");
		sql.append(" from xg_rcsw_ylbx_ylbxjgb t1 left join " );
		sql.append(" (select t1.yljgid,substr(t1.czqebzdm,'0','3') a1,substr(t1.czqebzdm,'5','3') a2,substr(t1.czqebzdm,'9','3') a3 from xg_rcsw_ylbx_ylbxjgb t1) t6 on t1.yljgid=t6.yljgid ");		
		sql.append(" left join view_xsxxb t2 ");
		sql.append(" on t1.xh = t2.xh left join xg_rcsw_ylbx_czqebzlxb t3 ");
		sql.append(" on t6.a1 = t3.czqebzdm left join xg_rcsw_ylbx_czqebzlxb t7 on t6.a2 = t7.czqebzdm left join xg_rcsw_ylbx_czqebzlxb t8 on t6.a3 = t8.czqebzdm left join xg_rcsw_ylbx_cbzklxb t4 ");
		sql.append(" on t1.cbzkdm = t4.cbzkdm left join xqdzb t5 on t1.xq = t5.xqdm ) a where 1 = 1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	
	}
	
	/**
	 * 
	 * @描述:TODO(判断医疗保险结果表中是否已经存在该学生)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-13 上午11:54:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(YlbxjgForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_rcsw_ylbx_ylbxjgb where xh = ?  and xn = ? and xq = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq()}, "num");
		return num;
	}
	
	
	/**
	 * 
	 * @描述:TODO(是否要删除)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-13 下午02:41:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yljgid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanDel(String yljgid){
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_rcsw_ylbx_ylbxjgb where yljgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{yljgid});
		String sjly=map.get("sjly");
		//如果未提交才可以提交
		return sjly.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @描述:TODO(获取医疗结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-13 下午02:41:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yljgid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getYljg(String yljgid){
		StringBuffer sb=new StringBuffer();
		sb.append(" select a.xh xh, xsxx.xm xm from xg_rcsw_ylbx_ylbxjgb a ");
		sb.append(" ,view_xsxxb xsxx where a.xh=xsxx.xh and a.yljgid=? ");
		return dao.getMapNotOut(sb.toString(),new String[]{yljgid});
	}
	
	
	
	/**
	 * 
	 * @描述:TODO(查看单个医疗保险结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-13 下午02:40:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xszbbjgId
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> viewOneYlbxjgList(String  xszbbjgId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.cbsj,t1.cbzkdm,  ");
		sql.append(" t1.czqebzdm,t1.zjsyrxm,t1.zjh,t1.qtcbzkval, ");
		sql.append(" t1.sqly,t2.xqmc "); 
		sql.append(" from xg_rcsw_ylbx_ylbxjgb t1 left join xqdzb t2 on t1.xq = t2.xqdm where t1.yljgid = ? ");
		
		return dao.getMapNotOut(sql.toString(),new String[]{xszbbjgId});
		
	}
	
	/**
	 * 
	 * @描述:TODO(得到当前学期名称)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-8 下午03:16:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCurrentXqmc(YlbxjgForm model) {
		StringBuilder sql = new StringBuilder(
				" select xqmc from xqdzb where xqdm=? ");
		String xqmc = dao.getOneRs(sql.toString(), new String[] { model.getXq()}, "xqmc");
		return xqmc;
	}
	
}
