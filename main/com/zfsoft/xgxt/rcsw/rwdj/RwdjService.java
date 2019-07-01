/**
 * @部门:学工产品事业部
 * @日期：2014-5-20 下午03:25:03 
 */
package com.zfsoft.xgxt.rcsw.rwdj;

import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-5-20 下午03:25:03
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class RwdjService extends SuperServiceImplExtend<RwdjForm, RwdjDao> {
	private RwdjDao rd = new RwdjDao();

	public RwdjService() {
		setDao(rd);
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-4 下午05:33:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExist(String xh){
		return dao.checkIsNotExist(xh);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 根据学号删除结果记录
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-5 上午10:30:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delJgbyXh(String xh) throws Exception{
		return dao.delJgbyXh(xh);
	}
}
