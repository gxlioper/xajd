/**
 * @部门:学工产品事业部
 * @日期：2014-6-3 下午01:30:48 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.xlwjyjk;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-6-3 下午01:30:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlwjyjkService extends SuperServiceImpl<XlwjyjkForm, XlwjyjkDao> {

	/**
	 * 
	 * @描述:提交预警库操作
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-4 上午08:48:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submit(HashMap<String , String> data) throws Exception{
		
		XlwjyjkForm model = new XlwjyjkForm();
		
		model.setXh(data.get("xh"));
		model.setLxdm(data.get("lxdm"));
		model.setGzdj(data.get("gzdj"));
		model.setRksj(data.get("rksj"));
		model.setBz(data.get("bz"));
		
		return submit(model);
		
	}
	
	//提交
	public boolean submit(XlwjyjkForm model) throws Exception{
		return dao.runInsert(model);
	}
	
	//PL submit
	public int batchSubmit(XlwjyjkForm model) throws Exception{
		String xhs = model.getXhs();
		if(StringUtils.isBlank(xhs)){
			return 0;
		}
		
		return dao.batchSubmit(xhs.split(","), model.getLxdm(), model.getGzdj(), model.getBz(), model.getRksj());
		
	}
	
	
	/**
	 * 
	 * @描述:获取预警库学生信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-5 下午02:16:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> xlwjyjkxsxx(String xh) throws Exception{
		return dao.xlwjyjkxsxx(xh);
	}
	
	public XlwjyjkService(){
		setDao(new XlwjyjkDao());
	}
}
