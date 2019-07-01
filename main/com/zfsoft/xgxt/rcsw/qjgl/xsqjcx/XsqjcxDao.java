/**
 * @部门:学工产品事业部
 * @日期：2016-3-3 下午03:47:32 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.xsqjcx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2016-3-3 下午03:47:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsqjcxDao extends SuperDAOImpl<XsqjcxForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsqjcxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsqjcxForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		SearchModel searchmodel = t.getSearchModel();
		String[] searchTj_qjksjc = searchmodel.getSearch_tj_qjksjc();
		String[] searchTj_qjjsjc = searchmodel.getSearch_tj_qjjsjc();
		String[] searchTj_kcmc = searchmodel.getSearch_tj_kcmc();
		String[] searchTj_qjkssj= searchmodel.getSearch_tj_kssj();
		String[] searchTj_qjjssj = searchmodel.getSearch_tj_jssj();
		searchmodel.setSearch_tj_qjksjc(null);
		searchmodel.setSearch_tj_qjjsjc(null);
		searchmodel.setSearch_tj_kcmc(null);
		searchmodel.setSearch_tj_kssj(null);
		searchmodel.setSearch_tj_jssj(null);
		
		String searchTj = SearchService.getSearchTj(searchmodel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(searchmodel);
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ");
		sql.append(" sys_zjly_xsqjcx_view_all t");
		if(StringUtils.isNull(t.getFlag())){
			sql.append(" where t.zgh like '");
			sql.append("%"+user.getUserName()+"%");
			sql.append("'");
			sql.append(qjjcSqlString(searchTj_qjksjc,searchTj_qjjsjc,searchTj_kcmc,searchTj_qjkssj,searchTj_qjjssj));
			sql.append(" and 1=1");
		}else{
			sql.append(" where 1=1 ");
			sql.append(qjjcSqlString(searchTj_qjksjc,searchTj_qjjsjc,null,searchTj_qjkssj,searchTj_qjjssj));
			sql.append(searchTjByUser);
		}
	
		sql.append(" ");
		sql.append(searchTj);
	
		
//		if (sql.toString().split("qjkssj")!= null && sql.toString().split("qjkssj").length >=3) {
//			sql.replace(sql.toString().lastIndexOf("qjkssj"), sql.toString().lastIndexOf("qjkssj")+6, "qjjssj");
//		} else if(sql.toString().split("qjkssj")!= null && sql.toString().split("qjkssj").length >1) {
//			if(sql.toString().indexOf(">") != -1){
//				sql.replace(sql.toString().lastIndexOf(">"), sql.toString().lastIndexOf(">")+1, "");
//			}else{
//				sql.replace(sql.toString().lastIndexOf("qjkssj"), sql.toString().indexOf("qjkssj")+6, "qjjssj");
//				sql.replace(sql.toString().lastIndexOf("<"), sql.toString().lastIndexOf("<")+1, "");
//			}
//			
//		};
		
		sql.append(" order by xymc,zymc,bjmc,xn,xq desc");
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	
	public HashMap<String, String> Qjsqck(XsqjcxForm para){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from sys_zjly_xsqjcx_view_all ");
		sql.append(" where id = ? and xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{para.getId(),para.getXh()});
	}
	
	public String qjjcSqlString(String[]qjksjc,String[]qjjsjc,String[]kcmc,String[] qjkssj,String[] qjjssj){
		StringBuilder sql = new StringBuilder();
		if(qjksjc != null && qjksjc.length >0){
			sql.append(" and (");
			for (int i = 0; i < qjksjc.length; i++) {
				sql.append(" qjksjc like '%"+qjksjc[i]+"%' ");
				if(i != qjksjc.length-1){
					sql.append(" or ");
				}
				
			}
			sql.append(")");
			
		}
		if(qjjsjc != null && qjjsjc.length >0){
			sql.append(" and (");
            for (int i = 0; i < qjjsjc.length; i++) {
            	sql.append(" qjjsjc like '%"+qjjsjc[i]+"%' ");
				if(i != qjjsjc.length-1){
					sql.append(" or ");
				}
			}
            sql.append(")");
		}
		if(kcmc != null && kcmc.length >0){
			sql.append(" and (");
            for (int i = 0; i < kcmc.length; i++) {
            	sql.append(" kcmc like '%"+kcmc[i]+"%' ");
				if(i != kcmc.length-1){
					sql.append(" or ");
				}
			}
            sql.append(")");
		}
		if(qjkssj == null || qjkssj.length == 0 || StringUtils.isNull(qjkssj[0]) ){
			qjkssj = null;
		}
		if(qjjssj == null || qjjssj.length == 0 || StringUtils.isNull(qjjssj[0])){
			qjjssj = null;
		}
		/**
		 * 下面的查询条件是为了实现条件覆盖，请仔细理解，语言很难描述，请看下面
		 * 条件选择2016-09-10到2016-09-12
		 * 那么符合的情况有(1)2016-09-08到 2016-09-13，一头一尾全在区间外，有重合
		 *  (2)2016-09-10 到2016-09-12号，在区间内
		 *  (3)2016-09-11到2016-09-13号，尾在区间外，有重合
		 *  (4)2016-09-09到2016-09-11，头在区间外，有重合
		 *  总之与选择条件有重合的数据才能被搜索到
		 */
		if((qjkssj != null ) || (qjjssj != null )){
			if((qjkssj != null && qjjssj == null) || (qjkssj == null && qjjssj != null)){
				String para = qjkssj != null ? qjkssj[0] : qjjssj[0];
				sql.append(" and  ((to_number("+SearchService.replaceTime("qjkssj")+") <= "+para+" )");
				sql.append(" and (to_number("+SearchService.replaceTime("qjjssj")+") >= "+para+" )");
				sql.append(")");
			}else{
				String kssj = qjkssj[0];
				String jssj = qjjssj[0];
				sql.append(" and (");
				sql.append(" (to_number("+SearchService.replaceTime("qjkssj")+") <= "+jssj+" )");
				sql.append(" and ");
				sql.append(" (to_number("+SearchService.replaceTime("qjjssj")+") >= "+kssj+" )");
				sql.append(" )");
				sql.append(" ");
				sql.append(" ");
			}
		}
		return sql.toString();
		 
	}

	/** 
	 * @描述:取审批历史列表
	 * @作者：CP[工号：1352]
	 * @日期：2017-4-11 上午10:02:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSplsList(XsqjcxForm model) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select taskname,to_char(starttime,'yyyy-mm-dd hh24:mi:ss') starttime,to_char(endtime,'yyyy-mm-dd hh24:mi:ss') endtime,exefullname,to_char(opinion) opinion,");
		sql.append(" case when checkstatus='-1' then '正在审批'");
		sql.append(" when  checkstatus='1'  then '同意'");
		sql.append(" when  checkstatus='2'  then '反对'");
		sql.append(" when  checkstatus='3'  then '驳回'");
		sql.append(" when  checkstatus='4'  then '撤销'");
		sql.append(" when  checkstatus='14' then '终止'");
		sql.append(" when  checkstatus='15' then '沟通意见'");
		sql.append(" when  checkstatus='21' then '转办'");
		sql.append(" when  checkstatus='23' then '更改执行人'");
		sql.append(" when  checkstatus='24' then '驳回到发起人'");
		sql.append(" when  checkstatus='28' then '表单意见'");
		sql.append(" when  checkstatus='33' then '提交'");
		sql.append(" when  checkstatus='35' then '干预'");
		sql.append(" when  checkstatus='38' then '流转'");
		sql.append(" when  checkstatus='40' then '代提交' end checkstatus");
		sql.append(" from zfsoft_bpmx.bpm_task_opinion where actinstid =? order by starttime,endtime");
		return dao.getListNotOut(sql.toString(),new String[]{model.getActinstid()});
	}
}
