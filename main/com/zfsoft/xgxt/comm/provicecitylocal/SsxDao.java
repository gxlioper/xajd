/**
 * @部门:学工产品事业部
 * @日期：2015-12-12 下午03:38:02 
 */  
package com.zfsoft.xgxt.comm.provicecitylocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-12-12 下午03:38:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SsxDao extends SuperDAOImpl<SsxModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SsxModel t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SsxModel t, User user)
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
		
	}
	
	/**
	 * 
	 * @描述:获取省信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-12-12 下午03:50:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getProviceMap(){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> para = new ArrayList<String> ();
		sql.append(" select t.sydqdm provicedm, t.sydq provicename");
		sql.append(" from dmk_sydq t");
		sql.append(" order by t.sydqdm asc");
		return dao.getListNotOut(sql.toString(),para.toArray(new String[]{}) );
	}
	
	/**
	 * 
	 * @描述:获取地市信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-12-12 下午03:50:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCtiyMap(String provicedm){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> para = new ArrayList<String> ();
		sql.append(" select t.qxdm citydm,t.qxmc cityname");
		sql.append(" from dmk_qx t");
		sql.append(" where substr(t.qxdm,0,2) = ? and");
		para.add(provicedm);
		sql.append(" substr(t.qxdm, 3, 2) <> '00'");
		sql.append(" and substr(t.qxdm, 5, 2) = '00'");
		sql.append(" order by t.qxdm asc");
		return dao.getListNotOut(sql.toString(),para.toArray(new String[]{}) );
	}
	
	/**
	 * 
	 * @描述:获取县区信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-12-12 下午03:50:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
    public List<HashMap<String, String>> getLocalMap(String provicedm,String ctiydm){
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> para = new ArrayList<String> ();
		sql.append(" select t.qxdm localdm,t.qxmc localname");
		sql.append("  from dmk_qx t");
		sql.append("  where substr(t.qxdm,0,2) = ? and");
		para.add(provicedm);
		sql.append("  substr(t.qxdm, 3, 2) = ? ");
		para.add(ctiydm);
		sql.append("  and substr(t.qxdm, 5, 2) <> '00'");
		sql.append(" order by t.qxdm asc");
		return dao.getListNotOut(sql.toString(), para.toArray(new String[]{}));
	}
    
    /**
     * 
     * @描述:TODO(这里用一句话描述这个方法的作用)
     * @作者：喻鑫源[工号：1206]
     * @日期：2015-12-12 下午04:28:31
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * String 返回类型 
     * @throws
     */
    public String getSsxQcName(String dqdm){
    	String proflag = dqdm.substring(0, 2);
    	String cityflag = dqdm.substring(2, 4);
    	String localflag = dqdm.substring(4, 6);
    	String provicedm = proflag +"0000";
    	String ctiydm = null;
    	String localdm = null;
    	if(!cityflag.equalsIgnoreCase("00")){
    		ctiydm = proflag + cityflag +"00";
    	}
    	if(!localflag.equalsIgnoreCase("00")){
    		localdm = dqdm;
    	}
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> para = new ArrayList<String> ();
    	sql.append(" select replace(wm_concat(t.qxmc), ';', '') qc");
    	sql.append("  from dmk_qx t");
    	sql.append("  where t.qxdm = ? ");
    	para.add(provicedm);
    	if(StringUtils.isNotNull(ctiydm)){
    		sql.append("  or t.qxdm = ? ");
    		para.add(ctiydm);
    	}
    	if(StringUtils.isNotNull(localdm)){
    		sql.append("  or t.qxdm = ? ");
    		para.add(localdm);
    	}
    	sql.append("  order by t.qxdm asc ");
    	return dao.getOneRs(sql.toString(), para.toArray(new String[]{}), "qc");
    }

}
