/**
 * @部门:学工产品事业部
 * @日期：2017-8-9 下午03:42:45 
 */  
package com.zfsoft.xgxt.szdw.xfjs;

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

public class xfjsService extends SuperServiceImpl<xfjsForm,xfjsDao>{

private xfjsDao dao = new xfjsDao();
	
	@SuppressWarnings("deprecation")
	public xfjsService(){
		super.setDao(dao);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-10 上午10:04:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(xfjsForm myForm) {
		// TODO 自动生成方法存根
		return dao.isExist(myForm);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-10 上午10:04:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveDataXf(xfjsForm myForm) throws Exception {
		// TODO 自动生成方法存根
		return dao.saveDataXf(myForm);
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-10 下午03:36:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateData(xfjsForm myForm) throws Exception {
		// TODO 自动生成方法存根
		return dao.updateData(myForm);
	}
	
	public xfjsForm getModel(xfjsForm form) throws Exception{
		xfjsForm model = dao.getModel(form);
		HashMap<String, String> xqxslb = dao.getxqxslbfdy(model.getBjdm());
		model.setXqmc(dao.getxqmc(model.getXq()));
		model.setBjmc(xqxslb.get("bjmc"));
		model.setPyccmc(xqxslb.get("pyccmc"));
		model.setYxmc(xqxslb.get("xqmc"));
		model.setFdy(xqxslb.get("fdy"));
		model.setXymc(xqxslb.get("xymc"));
		return model;
	}
	
	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-11 上午09:45:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delXf(String[] ids) throws Exception {
		// TODO 自动生成方法存根
		return dao.delXf(ids);
	}

	/**
	 * @throws Exception  
	 * @描述:获取班级列表
	 * @作者：张昌路[工号：982]
	 * @日期：2017-8-11 下午04:01:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjList(xfjsForm model, User user) throws Exception {
		return dao.getBjList(model,user);
	}
}
