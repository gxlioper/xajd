/**
 * @部门:学工产品事业部
 * @日期：2014-11-26 下午04:34:40 
 */  
package com.zfsoft.xgxt.gygl.qsdsgl.qsdskh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理
 * @类功能描述: 寝室导师考核
 * @作者： 江水才[工号：1150]
 * @时间： 2014-11-26 下午04:34:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QsdskhService extends SuperServiceImpl<QsdskhForm, QsdskhDao> {

	public QsdskhService(){
		setDao(new QsdskhDao());
	}

	/**
	 * 
	 * 获取寝室导师考核
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getQsdskh(QsdskhForm model){
		
		return dao.getQsdskh(model);
	}
	
	/**
	 * 更新寝室导师考核
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateQsdskh(QsdskhForm model) throws Exception{
		return dao.updateQsdskh(model);
	}
	
	/**
	 * 寝室导师考核保存（检查）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qsdskhAddCheck(QsdskhForm model) throws Exception{
		return dao.qsdskhAddCheck(model);
	}
	
	/**
	 * 
	 * 删除信息批量
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ldxx
	 * @param qsh
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int[] deleteDsdsxxPl(List<String[]> pks) throws Exception{
		return dao.deleteDsdsxxPl(pks);
	}
	
}
