/**
 * @部门:学工产品事业部
 * @日期：2017-1-3 下午02:48:35 
 */  
package com.zfsoft.xgxt.rcsw.rwdjsq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.rwdjsh.RwdjshForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-3 下午02:48:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RwdjsqDao extends SuperDAOImpl<RwdjsqForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RwdjsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(RwdjsqForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuffer sql = new StringBuffer();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		sql.append(" select *");
		sql.append(" from (select (select mc from dmk_rwtjb rwtjb where rwtjb.dm = a.rwtj) rwtjmc,");
		sql.append(" x.xymc,");
		sql.append(" x.xydm,");
		sql.append(" x.bjmc,");
		sql.append(" x.bjdm,");
		sql.append(" x.zymc,");
		sql.append(" x.zydm,");
		sql.append(" x.xm,");
		sql.append(" x.xb,");
		sql.append(" x.nj,");
		sql.append(" a.*,");
		sql.append(" decode(a.shzt,");
		sql.append("  '0',");
		sql.append("  '未提交',");
		sql.append("  '1',");
		sql.append("  '通过',");
		sql.append("  '2',");
		sql.append("  '不通过',");
		sql.append("  '3',");
		sql.append("  '已退回',");
		sql.append("  '5',");
		sql.append("  '审核中',");
		sql.append("  a.shzt) shztmc");
		sql.append(" from xg_zbxx_sqb a");
		sql.append(" left join view_xsxxb x");
		sql.append(" on a.xh = x.xh) a");
		sql.append("  where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(RwdjsqForm.class);
		this.setTableName("XG_ZBXX_sqb");
		this.setKey("rwdjid");
	}
	
	/**
	 * 
	 * @描述: 验证该学生已存在入伍登记记录
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-4 上午09:01:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExist(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) num");
		sql.append(" from (select xh from XG_ZBXX union all select xh from Xg_Zbxx_Sqb)");
		sql.append(" where xh =?");
		String num = dao.getOneRs(sql.toString(), new String[]{xh}, "num");
		return "0".equals(num) ? true :false;
	}
	
	//获取审批流
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from XG_rwdj_JCSZ ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	//获取spid
	public String getSqbh(RwdjsqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select rwdjid from XG_ZBXX_sqb where xh = ? ");
		return dao.getOneRs(sql.toString(), new String[] {model.getXh()}, "rwdjid");
	}
	
	//获取审核状态名称
	public String getShztMc(RwdjsqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select decode(t2.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t2.shzt) shztmc from XG_ZBXX_sqb t2 where rwdjid = ? ");
		return dao.getOneRs(sql.toString(), new String[] {model.getRwdjid()}, "shztmc");
	}
	
	@Override
	public RwdjsqForm getModel(RwdjsqForm t) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,b.mc rwtjmc from XG_ZBXX_sqb a ");
		sql.append(" left join dmk_rwtjb b on a.rwtj=b.dm ");
		sql.append(" where a.rwdjid=? ");
		return super.getModel(t, sql.toString(), new String[]{ t.getRwdjid() });
	}
}
