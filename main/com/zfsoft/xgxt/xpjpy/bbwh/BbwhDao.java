/**
 * @部门:学工产品事业部
 * @日期：2013-11-26 下午05:33:42 
 */  
package com.zfsoft.xgxt.xpjpy.bbwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-11-26 下午05:33:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BbwhDao extends SuperDAOImpl<BbwhForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BbwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BbwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-11-27 上午10:36:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBbxxList(){
		
		String[] input = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bblj from XG_PJPY_NEW_XMSZBBDMB t1 ");
		sql.append("left join XG_PJPY_NEW_BBDYTPB t2 on t1.bbdm=t2.bbdm and t2.dyym='1' ");
		
		return dao.getListNotOut(sql.toString(), input);
		
	}
	
	/**
	 * @描述：获取报表信息列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月12日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbdm
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getBbxxList(String bblx){
		
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bblj from XG_PJPY_NEW_XMSZBBDMB t1 ");
		sql.append("left join XG_PJPY_NEW_BBDYTPB t2 on t1.bbdm=t2.bbdm and t2.dyym='1' where ");
		if("2".equals(bblx)){
			sql.append(" t1.bblx='2' ");
		}else{
			sql.append(" t1.bblx is null or t1.bblx = '1' ");
		}
		sql.append("order by t1.bbmc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
		
	}
	
	/**
	 * @描述：获取报表信息
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月12日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbdm
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getBbxx(String bbdm){
		
		String[] input = new String[]{bbdm};
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.bblj from XG_PJPY_NEW_XMSZBBDMB t1 ");
		sql.append("left join XG_PJPY_NEW_BBDYTPB t2 on t1.bbdm=t2.bbdm where t1.bbdm = ? order by t2.dyym ");
		
		return dao.getListNotOut(sql.toString(), input);
		
	}
	
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-11-28 上午09:46:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbdm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getDataById(String bbdm) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from  XG_PJPY_NEW_XMSZBBDMB ");
		sb.append(" where bbdm=?");
		String[] inputValue = { bbdm };
		return dao.getMapNotOut(sb.toString(), inputValue);
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_PJY_NEW_XMSZBBDMB");
		super.setKey("bbdm");
		super.setClass(BbwhForm.class);		
	}

}
