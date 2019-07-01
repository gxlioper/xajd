/**
 * @部门:学工产品事业部
 * @日期：2017年5月10日 上午8:41:37 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 志愿服务管理模块
 * @类功能描述: 志愿服务结果Service
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月10日 上午8:41:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwJgService extends SuperServiceImpl<ZyfwJgForm,ZyfwJgDao>{

	/** 
	 * @描述:判断结果表中指定时间段内是否有重复记录
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月11日 下午8:24:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyfwJgForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isRepeat(ZyfwJgForm zyfwJgForm) {
		
		return dao.isRepeat(zyfwJgForm);
	}

	/** 
	 * @描述:志愿服务结果编辑的保存
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月11日 下午8:30:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyfwJgForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean zyfwSqSaveForEdit(ZyfwJgForm zyfwJgForm) {
		// TODO 自动生成方法存根
		return false;
	}

	/** 
	 * @描述:根据学号查询该生志愿服务结果信息列表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月12日 上午11:59:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZyfwJgListByXh(String xh) {
		
		return dao.getZyfwJgListByXh(xh);
	}
	
	/**
	 * @描述:重写：查询一条申请详细信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 上午11:52:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fwid
	 * @param lcid
	 * @return
	 * boolean 返回类型 
	 * @throws Exception  
	 */
	public ZyfwJgForm getModel(String fwid) throws Exception{
		return dao.getModel(fwid);
	}

}
