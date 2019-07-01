/**
 * @部门:学工产品事业部
 * @日期：2015-12-12 下午03:39:14 
 */  
package com.zfsoft.xgxt.comm.provicecitylocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-12-12 下午03:39:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SsxService extends SuperServiceImpl<SsxModel, SsxDao> {
	
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
		return dao.getProviceMap();
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
	public List<HashMap<String, String>>  getCtiyMap(String provicedm){
		return dao.getCtiyMap(provicedm);
	}
	
    public List<HashMap<String, String>>  getLocalMap(String provicedm,String ctiydm){
		return dao.getLocalMap(provicedm, ctiydm);
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
    	return dao.getSsxQcName(dqdm);
    }

}
