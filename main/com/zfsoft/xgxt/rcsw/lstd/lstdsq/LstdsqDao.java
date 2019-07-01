/**
 * @部门:学工产品事业部
 * @日期：2014-11-25 上午09:44:02 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdsq;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 绿色通道
 * @作者： cq [工号:785]
 * @时间： 2014-11-25 上午09:44:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LstdsqDao extends SuperDAOImpl<LstdsqForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LstdsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LstdsqForm t, User user)
			throws Exception {
		
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.splc splcidnew from ( ");
		sql.append(" select t1.sqid,t1.xh,t1.xn,t1.xq,(select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc,t1.sqsj,t1.lxdm, ");
		sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.xymc,t2.zymc,t2.zydm, t2.bjdm, t2.nj, t2.sfzh, ");
		sql.append(" decode(t1.shzt,  '0', '未提交', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', ");
		sql.append(" t1.shzt) shztmc,t3.lxmc from XG_RCSW_LSTD_SQB t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh left join XG_RCSW_LSTD_LXWHB t3 ");
		sql.append(" on t1.lxdm = t3.lxdm  ) a, XG_RCSW_LSTD_JCSZ b where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(LstdsqForm.class);
		super.setKey("sqid");
		super.setTableName("xg_rcsw_lstd_sqb");

	}
	
	/**
	 * 
	 * @描述:单条信息查看
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午11:19:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbsqId
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneSqList(String sqId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xh,t1.sqsj,t1.lxdm,t1.sqly,t1.shzt,t1.splc, ");
		sql.append(" decode(t1.shzt,  '0', '未提交', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', ");
		sql.append(" t1.shzt) shztmc,t3.lxmc from XG_RCSW_LSTD_SQB t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh left join XG_RCSW_LSTD_LXWHB t3 ");
		sql.append(" on t1.lxdm = t3.lxdm where t1.sqid = ? ");
		
		return dao.getMapNotOut(sql.toString(),new String[]{sqId});
	}
	
	/**
	 * 
	 * @描述:获取审批流ID
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午11:28:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select  splc from xg_rcsw_lstd_jcsz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	public HashMap<String, String> getLstdsq(String sqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from XG_RCSW_LSTD_SQB a");
		sb.append(",view_xsbfxx xsxx where a.xh=xsxx.xh and a.sqid= ? ");
		return dao.getMapNotOut(sb.toString(),new String[]{sqid});
	}
	
	
	/**
	 * 
	 * @描述:类型维护集合
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午11:31:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLxwhList() {
		String sql = "select lxdm,lxmc from XG_RCSW_LSTD_LXWHB order by lxdm ";
		return dao.getList(sql, new String[] {}, new String[] {"lxdm", "lxmc" });
	}
	
	
	public boolean isCanDel(String sqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from XG_RCSW_LSTD_SQB where sqid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{sqid});
		String sqzt=map.get("shzt");
		//如果未提交才可以提交
		return null==sqzt||sqzt.equals("0")?true:false;
	}
	
	/**
	 * 
	 * @描述:更新申请状态
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午11:34:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateSq(LstdsqForm model) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE XG_RCSW_LSTD_SQB ");
		sql.append(" set ");
		sql.append(" shzt = ?,");
		sql.append(" splc = ? ");
		sql.append(" where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @描述:单个查看
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午11:35:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String>  getLstdsqInfo(LstdsqForm t){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xh,t1.xn,(select xqmc from xqdzb b where b.xqdm=t1.xq) xqmc,t1.sqsj,t1.lxdm, ");
		sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm, t2.bjdm, t2.nj, ");
		sql.append(" decode(t1.shzt,  '0', '未审核', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', ");
		sql.append(" t1.shzt) shztmc,t3.lxmc from XG_RCSW_LSTD_SQB t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh left join XG_RCSW_LSTD_LXWHB t3 ");
		sql.append(" on t1.lxdm = t3.lxdm   where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}
	
	/**
	 * 
	 * @描述:待审核的记录总数
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午11:39:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int getDshCount() throws SQLException{
		
		String sql = "select count(1) num from XG_RCSW_LSTD_SQB where shzt='0' or shzt='5'  ";
		
		return dao.getOneRsint(sql);
	}
	
	
	/**
	 * 
	 * @描述:按照学号判断该学生该学年绿色通道申请是否已经存在
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午11:40:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(LstdsqForm model) {
		StringBuilder sql = new StringBuilder(
				" select count(1) num from XG_RCSW_LSTD_SQB where xh=? and xn = ? and xq = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq()}, "num");
		return num;

	}
	
	
}
