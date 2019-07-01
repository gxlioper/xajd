/**
 * @部门:学工产品事业部
 * @日期：2014-3-7 下午02:49:36 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.jggl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-7 下午02:49:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzmJgService extends SuperServiceImpl<ZdzmJgForm, ZdzmJgDao> {

	public ZdzmJgService(){
		setDao(new ZdzmJgDao());
	}
	
	/**
	 * @描述：根据申请ID删除结果数据(单条)
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 下午03:38:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteZdzmJgBySqid(String sqid) throws Exception{
		return dao.deleteZdzmJgBySqid(sqid) == 1 ? true : false;
	}
	
	/**
	 * @描述：根据申请IDs删除结果数据（批量）
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-7 下午05:23:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int deleteZdzmJgs(String[] sqids) throws Exception{
		return dao.runDelete(sqids);
	}
	
}
