/**
 * @部门:学工产品事业部
 * @日期：2017-8-9 下午03:42:45 
 */  
package com.zfsoft.xgxt.xszz.knsjgwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.axcs.wpsh.WpshForm;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.szdw.bfjs.bfjsgl.BfjsglForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称:学风建设维护模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:1352]
 * @时间： 2017-8-9 下午03:42:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class knsjgwhService extends SuperServiceImpl<knsjgwhForm,knsjgwhDao>{

private knsjgwhDao dao = new knsjgwhDao();
	
	@SuppressWarnings("deprecation")
	public knsjgwhService(){
		super.setDao(dao);
	}

}
