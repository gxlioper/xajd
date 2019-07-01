/**
 * @部门:学工产品事业部
 * @日期：2015-3-23 上午10:56:13 
 */  
package com.zfsoft.xgxt.xsxx.bbzc.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-3-23 上午10:56:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszService extends SuperServiceImpl<CsszForm, CsszDao> implements Constants{
	/**
	 * 
	 * @描述:获取参数设置信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-23 上午11:53:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * CsszForm 返回类型 
	 * @throws
	 */
	public CsszForm getModel() throws Exception{
		CsszForm model = dao.getModel();
		if (model == null){
			model = new CsszForm();
			model.setZckg(CLOSE);
		}
		return model;
	}
	/**
	 * 
	 * @描述:报到注册开放状态
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-23 下午01:44:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCsszParam(){
		return dao.getCsszParam();
	}

}
