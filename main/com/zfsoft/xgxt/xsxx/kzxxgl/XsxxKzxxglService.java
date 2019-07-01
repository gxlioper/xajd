/**
 * @部门:学工产品事业部
 * @日期：2015-6-12 上午10:02:35 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-12 上午10:02:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxKzxxglService extends  SuperServiceImpl<XsxxKzxxglForm, XsxxKzxxglDAO>{

	
	/**
	 * 
	 * @描述: 获取学生扩展信息,结果表
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-19 上午08:51:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * XsxxKzxxglForm 返回类型 
	 * @throws
	 */
	public XsxxKzxxglForm getJgxxByXh(String xh) throws Exception{
		if(StringUtils.isNotBlank(xh)){
			return dao.getXskzxxByXh(xh);
		}
		return null;
	}
	/**
	 * 
	 * @描述: 根据学号获取扩展信息申请记录
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-17 上午11:10:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<XsxxKzxxglForm> 返回类型 
	 * @throws
	 */
	public List<XsxxKzxxglForm> getSqListByXh(String xh) throws Exception{
		if(StringUtils.isNotBlank(xh)){
			return dao.getModleList(xh);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 根据学号获取最新申请的记录
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-18 下午06:35:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * XsxxKzxxglForm 返回类型 
	 * @throws
	 */
	public XsxxKzxxglForm getLatestSqByXh(String xh) throws Exception{
		if(StringUtils.isBlank(xh)){
			return null;
		}
		return dao.getLatestModel(xh);
	}
	
	/**
	 * 
	 * @描述:根据学号获取当前学号有效的申请记录
	 *  //1.如果该学生没有申请数据，学生可以申请新数据 返回 NULL
		//2.如果该学生已经有数据正在审核中 学生不能再次申请新数据,但如果还未第一步审核，可以撤回 返回 该条申请记录
		//3.如果该学生的数据已经审核完成 学生可以申请新数据 返回该条申请记录
		//4.如果学生的数据还未提交，学生可以修改之前的数据 返回该条记录
		//5.如果没声的数据不通过审核，学生需要重新申请，并返回该条不通过的记录
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-17 下午03:15:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * XsxxKzxxglForm 返回类型 
	 * @throws
	 */
	public XsxxKzxxglForm getOneSqListByXh(String xh) throws Exception{
		return getLatestSqByXh(xh);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:根据学号获取该学生的申请记录
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-12 上午10:27:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<XsxxKzxxglForm> 返回类型 
	 * @throws
	 */
	public List<XsxxKzxxglForm> getModleListByXh(String xh) throws Exception{
		if(StringUtils.isBlank(xh)){
			return null;
		}
		List<XsxxKzxxglForm> modleList = dao.getModleList(xh);
		return modleList;
	}
	
	/**
	 * 
	 * @描述: 检查该学生是否有在审核中的申请记录，、
	 *        如果有返回该申请就，没有或者审核流结束，返回NULL
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-12 上午10:37:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * XsxxKzxxglForm 返回类型 
	 * @throws
	 */
	public XsxxKzxxglForm checkSQ(String xh) throws Exception{
		List<XsxxKzxxglForm> modleListByXh = getModleListByXh(xh);
		if(modleListByXh == null || modleListByXh.size() == 0){
			return null;
		}
		for (XsxxKzxxglForm xsxxKzxxglForm : modleListByXh) {
			String shzt = xsxxKzxxglForm.getShzt();
			if(StringUtils.equals(shzt, Constants.YW_SHZ) || 
					StringUtils.equals(shzt, Constants.YW_WTJ) || 
					StringUtils.equals(shzt, Constants.YW_YTH)){
				return xsxxKzxxglForm;
			}
		}
		return null;
	}
	
}
