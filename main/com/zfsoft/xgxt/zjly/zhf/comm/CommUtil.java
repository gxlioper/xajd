/**
 * @部门:学工产品事业部
 * @日期：2016-6-20 下午03:00:56 
 */  
package com.zfsoft.xgxt.zjly.zhf.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-6-20 下午03:00:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CommUtil extends SuperDAOImpl {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List getPageList(Object t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List getPageList(Object t, User user) throws Exception {
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
	 * @描述:大项list
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-20 下午03:02:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param userdept
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
    public List<HashMap<String, String>> getDxList(String userdept,String username){
		StringBuilder sql = new StringBuilder();
		sql.append("  select xmmkdm,xmmkmc from xg_zjly_zhszf_mkxmb   ");
		ArrayList<String > paralist = new ArrayList<String>();
		//todo授权控制
    	if(!username.equals("zf01")){
    		sql.append(" where xmmkdm in");
    		sql.append(" (");
    		sql.append(" select xmmkdm from xg_zjly_zhszf_jfxmb where jfxmdm in ");
    		sql.append(" (select jfxmdm ");
    		sql.append(" from xg_zjly_zhszf_xmsqb");
    		sql.append(" where bmdm = ?)");
    		paralist.add(userdept);
    		sql.append(" ");
    		sql.append(" ");
    		sql.append(" ");
    		sql.append(" )");
    	}
		sql.append(" order by xmmkdm");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}));
    }
    
    /**
     * 
     * @描述:小项list
     * @作者：yxy[工号：1206]
     * @日期：2016-6-20 下午03:03:03
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param userdept
     * @return
     * List<HashMap<String,String>> 返回类型 
     * @throws
     */
    public List<HashMap<String, String>> getXxList(String userdept,String username){
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String > paralist = new ArrayList<String>();
    	sql.append("  select jfxmdm,jfxmmc,xmmkdm,khyd,fs from xg_zjly_zhszf_jfxmb ");
    	//todo授权控制
    	if(!username.equals("zf01")){
    		sql.append(" where jfxmdm in");
    		sql.append(" (");
    		sql.append(" select jfxmdm ");
    		sql.append(" from xg_zjly_zhszf_xmsqb");
    		sql.append(" where bmdm = ?");
    		paralist.add(userdept);
    		sql.append(" ");
    		sql.append(" ");
    		sql.append(" ");
    		sql.append(" )");
    	}
		sql.append(" order by xmmkdm ,to_number(jfxmdm) ");
		
		return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}));
	}
}
