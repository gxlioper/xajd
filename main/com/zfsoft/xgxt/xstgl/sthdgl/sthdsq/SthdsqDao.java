/**
 * @部门:学工产品事业部
 * @日期：2015-7-27 下午05:03:37 
 */  
package com.zfsoft.xgxt.xstgl.sthdgl.sthdsq;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdjg.SthdjgForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-27 下午05:03:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SthdsqDao extends SuperDAOImpl<SthdsqForm> {
	@Override
	protected void setTableInfo() {
		super.setClass(SthdsqForm.class);
		super.setKey("hdid");
		super.setTableName("xg_sthd_dj");

	}


	@Override
	public List<HashMap<String, String>> getPageList(SthdsqForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(SthdsqForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t6.xm,t6.xydm,t6.xymc,t6.zydm,t6.zymc,t6.bjdm,t6.bjmc,");
		sql.append("t6.nj,t6.xb,t6.zybj,t6.zybjmc,t6.mz,t6.mzdm,t6.zzmm,t6.zzmmmc,t6.sydm1 sydm,t6.symc1 symc, ");
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc, ");
	    sql.append(" ssx.shengmc||ssx.shimc||ssx.xianmc|| t1.fwdd fwddxxdz ");
		sql.append(" from xg_sthd_dj t1 ");
		sql.append(" left join view_xsjbxx t6 on t1.xh=t6.xh ");
		sql.append(" left join xg_view_dmk_qx ssx on ssx.qxdm=t1.fwddssx ");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述:获取审批流
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-27 上午11:03:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_stgl_sthdgl_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	public SthdsqForm getSqxx(SthdsqForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append("select t1.*,t6.xm,t6.xydm,t6.xymc,t6.zydm,t6.zymc,t6.bjdm,t6.bjmc,");
		sql.append("t6.nj,t6.xb,t6.zybj,t6.zybjmc,t6.mz,t6.zzmmmc, t6.sydm1 sydm,t6.symc1 symc,");
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc, ");
		sql.append(" ssx.shengmc||ssx.shimc||ssx.xianmc|| t1.fwdd fwddxxdz ");
		sql.append(" from xg_sthd_dj t1 ");
		sql.append(" left join view_xsjbxx t6 on t1.xh=t6.xh ");
		sql.append(" left join xg_view_dmk_qx ssx on ssx.qxdm=t1.fwddssx ");
		sql.append(" ) t where t.hdid=? ");
		return super.getModel(sql.toString(),new String[]{model.getHdid()});
	}
	
	
	/**
	 * 
	 * @描述:判断当前岗位是否有填写记录
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-27 上午11:05:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveSqJl(SthdsqForm model) throws Exception {
		String id = model.getHdid() == null ? "-1" : model.getHdid();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from(select case when fwsj=?  then '0' else '1' end sfcd ");
		sql.append("from xg_sthd_dj where xh=? and  hdid <>?)where sfcd='1' ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getFwsj(),model.getXh(),id}, "num");
		return Integer.valueOf(num) > 0;
	}

	/**
	 * 
	 * @描述:社团项目列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-27 上午10:24:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getStxmList()throws Exception{
		String sql ="select a.stid,a.stxmmc from xg_stgl_jtjg a left join xg_stgl_stlb b on a.stlbdm=b.stlbdm where b.stlbmc like '%志愿者%'";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	

}
