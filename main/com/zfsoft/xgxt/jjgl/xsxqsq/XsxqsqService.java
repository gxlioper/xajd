/**
 * @部门:学工产品事业部
 * @日期：2014-8-28 下午04:46:34 
 */  
package com.zfsoft.xgxt.jjgl.xsxqsq;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-28 下午04:46:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxqsqService extends SuperServiceImpl<XsxqsqForm, XsxqsqDao> {
	/**
	 * 
	 * @描述:更具需求id和学号获取Model
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-28 下午08:05:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * XsxqsqForm 返回类型 
	 * @throws
	 */
	public XsxqsqForm getModelByXqidAndXh(String xqid , String xh) throws Exception{
		return dao.getModelByXqidAndXh(xqid, xh);
	}
}
