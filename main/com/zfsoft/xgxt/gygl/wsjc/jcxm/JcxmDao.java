/**
 * @部门:学工产品事业部
 * @日期：2015-5-28 下午04:44:03 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcxm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @类功能描述:检查项目
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-5-28 下午04:44:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcxmDao extends SuperDAOImpl<JcxmModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		super.setClass(JcxmModel.class);
		super.setTableName("xg_gygl_wsjc_jcxmb");
		super.setKey("xmdm");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JcxmModel t)
			throws Exception {
		
		StringBuilder sql = new StringBuilder("select * from xg_gygl_wsjc_jcxmb where 1=1 ");
		List<String> params = new ArrayList<String>();
		
		if (!StringUtil.isNull(t.getXmmc())){
			params.add(t.getXmmc());
			sql.append(" and xmmc like '%'||?||'%' ");
		}
		
		if (!StringUtil.isNull(t.getJcdx())){
			params.add(t.getJcdx());
			sql.append(" and jcdx = ? ");
		}
		
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JcxmModel t, User user)
			throws Exception {
		return null;
	}
	
	
	
	/**按日程、检查对象查询项目列表**/
	public List<HashMap<String, String>> getRcxmList(String rcid,String jcdx)
			throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xmdm,t2.xmmc from xg_gygl_wsjc_rcxmb t1 ");
		sql.append("left join xg_gygl_wsjc_jcxmb t2 on t1.xmdm=t2.xmdm where t1.rcid=? and t2.jcdx=?");
		
		return dao.getListNotOut(sql.toString(), new String[]{rcid,jcdx});
	}

	
	
	/**删除检查项目**/
	public int runDelete(String[] values) throws Exception {
		if (values == null || values.length == 0){
			logger.error("删除操作不能进行!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_gygl_wsjc_jcxmb t1");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("xmdm=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(") and not exists (select 1 from xg_gygl_wsjc_rcxmb t2 where t1.xmdm = t2.xmdm)");
		
		return dao.runDelete(sql.toString(), values);
	}
	
	/** 
	 * @描述:统计项目数(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-3 下午06:19:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public String getCountJl(JcxmModel model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_gygl_wsjc_jcxmb where xmmc = ?");
		if(null != model.getXmdm()){
			sql.append(" and xmdm <> ?");
			return dao.getOneRs(sql.toString(), new String[]{model.getXmmc(),model.getXmdm()}, "num");
		}else{
			return dao.getOneRs(sql.toString(), new String[]{model.getXmmc()}, "num");
		}
	}
}
