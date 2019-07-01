/**
 * @部门:学工产品事业部
 * @日期：2013-5-13 上午08:56:32 
 */  
package com.zfsoft.xgxt.rwgl.rwtw;

import java.util.HashMap;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 人武管理模块
 * @类功能描述: TODO入伍学费补偿管理
 * @作者：HongLin 
 * @时间： 2013-5-13 上午08:55:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RwxfbcglService extends SuperServiceImpl<RwxfbcglForm, RwxfbcglDao>{

	private RwxfbcglDao dao = new RwxfbcglDao();
	
	public RwxfbcglService(){
		super.setDao(dao);
	}
	
	/** 
	 * @描述: 唯一性判断（学号，学年）
	 * @作者：HongLin
	 * @日期：2013-5-14 下午02:52:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExistByRwxfbc(RwxfbcglForm model, String type)throws Exception{
	    boolean flag = false;
		if("save".equalsIgnoreCase(type)){
			String num=dao.checkExistForSave(model);
			flag = !"0".equalsIgnoreCase(num);
			
		}else if("update".equalsIgnoreCase(type)){
			String num=dao.checkExistForUpdate(model);
			flag = !"0".equalsIgnoreCase(num);
		}
    	return  flag;
}
	
	/** 
	 * @描述:TODO 获得单个学生入伍学费补偿信息
	 * @作者：HongLin
	 * @日期：2013-5-14 下午02:09:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getOneRwxfbcList(String  xh) throws Exception {
		 
		return dao.getOneRwxfbcList(xh);
	}
	
	/** 
	 * @描述: 保存批量学生学费补偿
	 * @作者：HongLin
	 * @日期：2013-5-14 下午06:46:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public boolean savePlbc(RwxfbcglForm model,User user) throws Exception{
		boolean isSuccess = true;
		if(null!=model.getGuid() && !"".equals(model.getGuid())){
			boolean falg = dao.updatePlbc(model);
			if(!falg){
				isSuccess = false;
			}
		}
		if(null!=model.getXh() && !"".equals(model.getXh())){
			boolean falg = dao.insertPlbc(model);
			if(!falg){
				isSuccess = false;
			}
		}
		return isSuccess;
	}
}
