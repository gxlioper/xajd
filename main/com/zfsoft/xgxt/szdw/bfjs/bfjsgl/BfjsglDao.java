/**
 * @部门:学工产品事业部
 * @日期：2017-4-19 上午11:42:00 
 */  
package com.zfsoft.xgxt.szdw.bfjs.bfjsgl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 班风建设dao(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-4-19 上午11:42:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BfjsglDao extends SuperDAOImpl<BfjsglForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BfjsglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BfjsglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.xydm,t2.nj,t2.bjmc,t2.xymc,t2.zydm,t2.zymc,count(t3.xh) bjrs,");
		sql.append(" (count(t3.xh)-(select count(1) from xg_szdw_bfjs_kqjcmxb b where b.jclx = '1' and t1.jcid = b.jcid)) zcs,");
		sql.append(" (count(t3.xh)-(select count(1) from xg_szdw_bfjs_kqjcmxb b where b.jclx = '2' and t1.jcid = b.jcid)) zds,");
		sql.append(" (count(t3.xh)-(select count(1) from xg_szdw_bfjs_kqjcmxb b where b.jclx = '3' and t1.jcid = b.jcid)) sks,");
		sql.append(" (count(t3.xh)-(select count(1) from xg_szdw_bfjs_kqjcmxb b where b.jclx = '4' and t1.jcid = b.jcid)) wzxs");
		sql.append(" from XG_SZDW_BFJS_KQJCB t1");
		sql.append(" left join view_njxyzybj t2 on t1.bjdm = t2.bjdm");
		sql.append(" left join view_xsbfxx t3 on t1.bjdm = t3.bjdm");
		sql.append(" group by t1.jcid,t1.bjdm,t1.jcrq,t2.xydm,t2.nj,t2.bjmc,t2.xymc,t2.zydm,t2.zymc order by t1.jcrq desc");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/** 
	 * @描述:获取班级列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-21 上午11:23:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjList(BfjsglForm t,User user) throws Exception{
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1","xydm", "bjdm");
	
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.* from view_njxyzybj a where not exists (select 1 from XG_SZDW_BFJS_KQJCB b where a.bjdm = b.bjdm and b.jcrq = '"+t.getJcrq()+"')");
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
	
		return getPageList(t, sql.toString(), inputValue);
	}
	
	/** 
	 * @描述:获取学生信息列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-21 下午02:29:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsxxList(BfjsglForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhs = model.getXhs();		
		StringBuilder sql = new StringBuilder("select a.* from(select a.xh,a.xm,a.nj,a.xb,a.xymc,a.zymc,");
		sql.append(" a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a where a.bjdm='" + model.getBjdm() + "'");

		if(xhs.length>0){
			sql.append(" and a.xh not in(");
			for (int i = 0; i < xhs.length; i++) {
				if(i!=0){
					sql.append(", ");
				}
				sql.append("'"+xhs[i]+"' ");
			}
			sql.append(")");
		}
		sql.append(")a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(BfjsglForm.class);
		super.setKey("jcid");
		super.setTableName("XG_SZDW_BFJS_KQJCB");
		
	}
	
	/**
	 * @throws SQLException  
	 * @描述:批量插入检查明细(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-19 下午02:42:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertJcmx(List<String[]> list,BfjsglForm form) throws SQLException{
		String[] sqr = new String[list.size()];
		boolean flg = false;
		for(int i = 0;i<sqr.length;i++){
			String[] str = list.get(i);
			StringBuilder sb = new StringBuilder();
			sb.append("insert into xg_szdw_bfjs_kqjcmxb(xh,jclx,kqlx,jcid) values('"+str[0]+"','"+str[1]+"','"+str[2]+"','"+form.getJcid()+"')");
			sqr[i] = sb.toString();
		}
		int []res = dao.runBatch(sqr);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	
	/** 
	 * @描述:判断该班级在该检查日期是否存在(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-24 上午08:34:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isBjExist(BfjsglForm form) {
		String sql = "select count(1) num from XG_SZDW_BFJS_KQJCB where bjdm = ? and jcrq = ?";
		String num = dao.getOneRs(sql, new String[]{form.getBjdm(),form.getJcrq()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：获取考勤人员信息[工号：1282]
	 * @日期：2017-4-24 上午09:55:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getKqRyxxList(BfjsglForm form){
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.*,decode(qqlxdm,'cq','到勤','qj','请假','qq','缺勤','cd','迟到','zt','早退') qqlxmc from ");
		//各个检查时间段缺勤情况
		sb.append("(select a.jcid,a.bjdm,a.jcrq,b.xh,c.xm,decode(b.jclx,'1','zc','2','zd','3','sk','4','wzx')jclx,decode(b.kqlx,'1','qj','2','qq','3','cd','4','zt')qqlxdm from XG_SZDW_BFJS_KQJCB a left join xg_szdw_bfjs_kqjcmxb b on a.jcid = b.jcid left join view_xsbfxx c on b.xh = c.xh where a.jcid = ? ");	
		//早操出勤人员
		sb.append(" union all ");
		sb.append(" select a.jcid,a.bjdm,a.jcrq,b.xh,b.xm,'zc' jclx,'cq' qqlxdm from XG_SZDW_BFJS_KQJCB a");
		sb.append(" left join view_xsbfxx b on a.bjdm = b.bjdm where not exists(select 1 from xg_szdw_bfjs_kqjcmxb c where a.jcid = c.jcid and b.xh = c.xh and c.jclx = '1')");
		sb.append(" and a.jcid = ? ");		
		//早读出勤人员
		sb.append(" union all ");
		sb.append(" select a.jcid,a.bjdm,a.jcrq,b.xh,b.xm,'zd' jclx,'cq' qqlxdm from XG_SZDW_BFJS_KQJCB a");
		sb.append(" left join view_xsbfxx b on a.bjdm = b.bjdm where not exists(select 1 from xg_szdw_bfjs_kqjcmxb c where a.jcid = c.jcid and b.xh = c.xh and c.jclx = '2')");
		sb.append(" and a.jcid = ? ");
		//上课出勤人员
		sb.append(" union all ");
		sb.append(" select a.jcid,a.bjdm,a.jcrq,b.xh,b.xm,'sk' jclx,'cq' qqlxdm from XG_SZDW_BFJS_KQJCB a");
		sb.append(" left join view_xsbfxx b on a.bjdm = b.bjdm where not exists(select 1 from xg_szdw_bfjs_kqjcmxb c where a.jcid = c.jcid and b.xh = c.xh and c.jclx = '3')");
		sb.append(" and a.jcid = ? ");
		//晚自习出勤人员
		sb.append(" union all ");
		sb.append(" select a.jcid,a.bjdm,a.jcrq,b.xh,b.xm,'wzx' jclx,'cq' qqlxdm from XG_SZDW_BFJS_KQJCB a");
		sb.append(" left join view_xsbfxx b on a.bjdm = b.bjdm where not exists(select 1 from xg_szdw_bfjs_kqjcmxb c where a.jcid = c.jcid and b.xh = c.xh and c.jclx = '4')");
		sb.append(" and a.jcid = ? ) a order by xh asc");
		return dao.getListNotOut(sb.toString(), new String[]{form.getJcid(),form.getJcid(),form.getJcid(),form.getJcid(),form.getJcid()});
	}
	
	/** 
	 * @描述:获取model(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-24 上午11:11:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * BfjsglForm 返回类型 
	 * @throws 
	 */
	public HashMap<String,String> getModelMap(BfjsglForm form){
		String sql = "select a.*,b.bjmc from XG_SZDW_BFJS_KQJCB a left join view_njxyzybj b on a.bjdm = b.bjdm where a.jcid = ?";
		return dao.getMapNotOut(sql.toString(), new String[]{form.getJcid()});
	}
	
	/**
	 * @throws Exception  
	 * @描述:删除班风建设(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-24 下午04:14:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delBfjs(String[] ids) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("delete from xg_szdw_bfjs_kqjcmxb where jcid in (");
		for(int i = 0;i<ids.length;i++){
			if(i != ids.length - 1){
				sb.append("?,");
			}else{
				sb.append("?)");
			}
		}
		return dao.runUpdate(sb.toString(), ids);
	}
	
	/**
	 * @throws SQLException  
	 * @描述:根据学号、jcID、检查类型删除考勤明细(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-25 上午08:57:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public void delXh(List<String[]> list) throws SQLException{
		String sql = "delete from xg_szdw_bfjs_kqjcmxb where jcid = ? and jclx = ? and xh = ?";
		dao.runBatch(sql, list);
	}
	
}
