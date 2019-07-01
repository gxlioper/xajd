/**
 * @部门:学工产品事业部
 * @日期：2014-4-25 上午11:28:57 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询-学生预约咨询
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-25 上午11:28:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsyysqDao extends SuperDAOImpl<XsyysqForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_XLZX_YYSQB_WZDX");
		setKey("sqid");
		setClass(XsyysqForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsyysqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsyysqForm t, User user)
			throws Exception {
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select sqid || zxid pkid,sqid,zxid,xh,xm,xb,yyzt,cjsj,zzaprq,zxs,zxsxm,zxzt,xspjzt from(")
		   .append("select t.sqid,t2.zxid,t.xh,t1.xm,t1.xb,t.yyzt,t.cjsj,t2.zzaprq,t2.zxs,t3.xm zxsxm,t2.zxzt,")
		   .append("(case when t2.xszxpj is null then '未评价' else '已评价' end) xspjzt ")
		   .append("from XG_XLZX_YYSQB_WZDX t ")
		   .append("left join VIEW_XSBFXX t1 ")
		   .append("on t.xh = t1.xh ")
		   .append("left join XG_XLZX_XLZXB_WZDX t2 ")
		   .append("on t.sqid = t2.sqid ")
		   .append("left join VIEW_FDYXX t3 ")
		   .append("on t2.zxs = t3.zgh ")
		   .append("union ")
		   .append("select a.sqid,a.zxid,a.xh,a1.xm,a1.xb,a2.yyzt,a2.cjsj,a.zzaprq,a.zxs,a3.xm zxsxm,a.zxzt,")
		   .append("(case when a.xszxpj is null then '未评价' else '已评价' end) xspjzt ")
		   .append("from XG_XLZX_XLZXB_WZDX a ")
		   .append("left join VIEW_XSBFXX a1 ")
		   .append("on a.xh = a1.xh ")
		   .append("left join XG_XLZX_YYSQB_WZDX a2 ")
		   .append("on a.sqid = a2.sqid ")
		   .append("left join VIEW_FDYXX a3 ")
		   .append("on a.zxs = a3.zgh ) t ")
		   .append("where 1 = 1 ")
		   .append(searchTjByUser);
		
		return getPageList(t, sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述:查询咨询预约说明
	 * @作者：王志刚
	 * @日期：2014-4-25 上午10:14:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param 
	 * @return
	 * boolean 返回类型 
	 */
	public String getZxyysm() throws Exception{
		String sql="select t.zxyysm from xljk_zxyysm t ";
		String zxyysm = dao.getOneRs(sql.toString(), new String[]{}, "zxyysm");
		return zxyysm;
	}
	
	/**
	 * 
	 * @描述:查出所有心理问题类型
	 * @作者：王志刚
	 * @日期：2014-4-25 下午03:17:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 */
	public List<HashMap<String, String>> getXlwtAllList(){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.lxdm,t.lxmc from XG_XLJK_XLWJYJ_XLWTLX t ");
		List<HashMap<String, String>> list=dao.getListNotOut(sql.toString(), new String[]{});
		return list;
	}
	
	/**
	 * 
	 * @描述:查出所选的心理问题类型
	 * @作者：王志刚
	 * @日期：2014-4-29 下午02:17:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lxdmstr   例：'23','123','213'
	 * @return
	 */
	public List<HashMap<String, String>> getXlwtAllListByLxdm(String lxdmstr){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.lxdm,t.lxmc from XG_XLJK_XLWJYJ_XLWTLX t where t.lxdm in (")
		.append(lxdmstr)
		.append(")");
		List<HashMap<String, String>> list=dao.getListNotOut(sql.toString(), new String[]{});
		return list;
	}
	
	/**
	 * 
	 * @描述: 取消学生预约咨询申请
	 * @作者：王志刚
	 * @日期：2014-4-29 上午11:33:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid 
	 * @param qxyyyy 取消预约原因
	 * @param yyzt 预约状态
	 * @return
	 * boolean 返回类型 
	 */
	public boolean cancelXsyysq(String sqid, String qxyyyy, String yyzt) throws Exception{
		String sql = "update XG_XLZX_YYSQB_WZDX set qxyyyy=?,yyzt=? where sqid=?";
		boolean result = dao.runUpdate(sql, new String[] { qxyyyy, yyzt, sqid });
		return result;
	}
	
	/**
	 * 
	 * @描述: 学生咨询评价(根据申请id)
	 * @作者：王志刚
	 * @日期：2014-5-7 上午11:46:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid 
	 * @param zxxgmydpf 咨询效果满意度评分
	 * @param xszxpj 学生咨询评价
	 * @return
	 * boolean 返回类型 
	 */
	public boolean setZxpj(String sqid, String zxxgmydpf, String xszxpj) throws Exception{
		String sql = "update XG_XLZX_XLZXB_WZDX set zxxgmydpf=?, xszxpj=? where sqid=?";
		boolean result = dao.runUpdate(sql, new String[] { zxxgmydpf, xszxpj, sqid });
		return result;
	}
	
	/**
	 * 
	 * @描述: 学生咨询评价(根据咨询id)
	 * @作者：王志刚
	 * @日期：2014-5-9 下午15:00:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zxid 
	 * @param zxxgmydpf 咨询效果满意度评分
	 * @param xszxpj 学生咨询评价
	 * @return
	 * boolean 返回类型 
	 */
	public boolean setZxpjByZxid(String zxid, String zxxgmydpf, String xszxpj) throws Exception{
		String sql = "update XG_XLZX_XLZXB_WZDX set zxxgmydpf=?, xszxpj=? where zxid=?";
		boolean result = dao.runUpdate(sql, new String[] { zxxgmydpf, xszxpj, zxid });
		return result;
	}

}
