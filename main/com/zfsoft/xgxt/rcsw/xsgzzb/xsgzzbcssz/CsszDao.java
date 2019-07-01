/**
 * @部门:学工产品事业部
 * @日期：2015-11-10 下午04:57:04 
 */  
package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbcssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-11-10 下午04:57:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszDao extends SuperDAOImpl<CsszForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from XG_XSGZZB_CSSZ a where 1=1");
		if (!StringUtil.isNull(t.getWjlxmc())) {
			params.add(t.getWjlxmc());
			sql.append(" and a.wjlxmc like '%'||?||'%'");
		}
		return getPageList(t, sql.toString(), params.toArray(new String[] {}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(CsszForm.class);
		super.setKey("wjlxdm");
		super.setTableName("XG_XSGZZB_CSSZ");
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-11 上午09:34:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveWjlx(CsszForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into XG_XSGZZB_CSSZ values(SEQ_XSGZZB_CSSZ.nextval,?)");
		return dao.runUpdate(sql.toString(), new String[]{t.getWjlxmc()});
	}
	
	//验证数据库中是否有同名的文件类型名称
	public boolean isExistsSameWjlxmc(String wjlxmc,String wjlxdm){
		StringBuilder sql = new StringBuilder();
		String[] inputvalue = new String[]{wjlxmc};
		sql.append(" select count(1) flag from  XG_XSGZZB_CSSZ  where wjlxmc = ?");
		//修改时传入wjlxdm进行判断，增加时传入null判断
		if(wjlxdm != null && !wjlxdm.equals("")){
			sql.append(" and wjlxdm != ?");
			inputvalue = new String[]{wjlxmc,wjlxdm};
		}
		String flag = dao.getOneRs(sql.toString(), inputvalue, "flag");
		return (Integer.parseInt(flag) > 0) ? true:false;
	}
	
	//获取文件类型名称
	public String getWjlxmc(String wjlxdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select wjlxmc from XG_XSGZZB_CSSZ where wjlxdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{wjlxdm}, "wjlxmc");
	}
	
	//删除文件类型时判断在业务表中是否在用
	public boolean isExistsWjlxmc_user(String wjlxdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) flag");
		sql.append(" from XG_RCSW_XSGZZB_XSGZZB_FJB");
		sql.append(" where wjlxdm = ?");
		String flag = dao.getOneRs(sql.toString(), new String[]{wjlxdm}, "flag");
		return (Integer.parseInt(flag) > 0) ? true:false;
	}
	
	//获取文件类型名称list
	public List<HashMap<String,String>> getWjlxList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from XG_XSGZZB_CSSZ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	//已上传附件信息list
	public List<HashMap<String, String>> getYscfjList(String sqid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, t1.wjlxmc");
		sql.append(" from XG_RCSW_XSGZZB_XSGZZB_FJB t");
		sql.append(" left join XG_XSGZZB_CSSZ t1");
		sql.append(" on t.wjlxdm = t1.wjlxdm");
		sql.append(" where t.filegid in");
		sql.append(" (select t.filegid");
		sql.append(" from xg_rcsw_xsgzzb_xsgzzbsqb t");
		sql.append(" where t.sqid = ?)");
		return dao.getListNotOut(sql.toString(), new String[]{sqid});
	}
}
