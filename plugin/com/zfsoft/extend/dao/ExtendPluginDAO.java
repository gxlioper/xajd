/**
 * @部门:学工产品事业部
 * @日期：2015-6-23 上午11:56:16 
 */  
package com.zfsoft.extend.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-23 上午11:56:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExtendPluginDAO {

	private static String QUERY_SH = "select distinct sydqdm dm,sydq mc from dmk_sydq ORDER BY sydqdm";
	
	private static String QUERY_QX = "select distinct qxdm dm,qxmc mc from dmk_qx order by qxdm";
	
	private static DAO dao = DAO.getInstance();
	
	public static List<HashMap<String, String>> SH = dao.getListNotOut(QUERY_SH, new String[]{});
	
	public static List<HashMap<String, String>> QX = dao.getListNotOut(QUERY_QX, new String[]{});
}
