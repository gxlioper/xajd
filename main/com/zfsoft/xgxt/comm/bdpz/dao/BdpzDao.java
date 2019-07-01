package com.zfsoft.xgxt.comm.bdpz.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 表单排版 
 * @作者： Penghui.Qu 
 * @时间： 2013-4-28 上午08:56:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BdpzDao  {

	
	/**
	 * 根据功能模块标识读取基本信息配置
	 * @param gnmk
	 * @return
	 */
	public List<HashMap<String, String>> getJbxxpz(String gnmk){
		
		DAO dao = DAO.getInstance();
		
		List<HashMap<String, String>> jbxxpz = null;
		
		String sql = "select zddm,zdmc from xg_xtwh_jbxxpzb where gnmk = ? and xxdm = ? order by to_number(nvl(xssx,0))";
		
		jbxxpz = dao.getListNotOut(sql, new String[]{gnmk,Base.xxdm});
		
		if( null==jbxxpz || jbxxpz.size()==0){
			jbxxpz = dao.getListNotOut(sql, new String[]{gnmk,"public"});
		}
		
		return jbxxpz;
		
	} 
	
	
}
