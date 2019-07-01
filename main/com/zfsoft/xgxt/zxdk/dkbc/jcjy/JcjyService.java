/**
 * @部门:学工产品事业部
 * @日期：2016-12-6 下午03:17:03 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.jcjy;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgDao;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款-基层就业
 * @类功能描述: 学生基层就业信息记录
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2016-12-6 下午03:18:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcjyService extends SuperServiceImpl<JcjyModel, JcjyDao>{
	/**
	 * 取行业类别名称和代码
	 */
	public List<HashMap<String, String>> getHylbList(){
		return dao.getHylbList();
	}
	
	/**
	 * 同一个学号代偿类别只能存在一条记录
	 */
	public boolean isHaveRecord(String xh,String dclb){
		return dao.isHaveRecord(xh,dclb);
	}
	
	/**
	 * 取行业类别名称
	 */
	public String getHylb(String xh){
		return dao.getHylb(xh);
	}
}
