/**
 * @部门:学工产品事业部
 * @日期：2016-4-20 下午06:27:43 
 */  
package com.zfsoft.xgxt.xszz.qxknsjl;

import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-4-21 上午08:35:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class QxknsjlService extends SuperServiceImpl<QxknsjlForm, QxknsjlDao> {
	
	private QxknsjlDao dao = new QxknsjlDao();
		
	public QxknsjlService(){
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:TODO(获得单个学生认定信息)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-26 下午05:38:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * @throws Exception
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneKnsjgList(String  guid) throws Exception {
		 
		return dao.getOneKnsjgList(guid);
	}
	
	/**
	 * 
	 * @描述:TODO(获得单个学生取消困难生记录)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-26 下午06:16:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgguid
	 * @return
	 * @throws Exception
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneKnsqxjlList(String jgguid) throws Exception {
		 
		return dao.getOneKnsqxjlList(jgguid);
	}

}
