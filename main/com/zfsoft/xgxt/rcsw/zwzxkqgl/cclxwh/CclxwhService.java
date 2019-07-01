/**
 * @部门:学工产品事业部
 * @日期：2015-1-22 上午10:57:45 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.cclxwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-22 上午10:57:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CclxwhService extends SuperServiceImpl<CclxwhForm, CclxwhDao>{
	private CclxwhDao cclxwhDao = new CclxwhDao();
	/**
	 *查询抽查类型列表
	 */
	public List<HashMap<String, String>> getCclxList(){
		return cclxwhDao.getCclxList();
	}
	
	/**
	 *查询抽查类型
	 */
	public HashMap<String, String> getCclxById(String lxdm){
		return cclxwhDao.getCclxById(lxdm);
	}
	
	/**
	 *查询缺勤类型列表
	 */
	public List<HashMap<String, String>> getQqlxList(){
		return cclxwhDao.getQqlxList();
	}

}
