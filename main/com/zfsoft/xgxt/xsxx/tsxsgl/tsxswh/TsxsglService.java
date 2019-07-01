/**
 * @部门:学工产品事业部
 * @日期：2015-5-14 下午01:45:36 
 */  
package com.zfsoft.xgxt.xsxx.tsxsgl.tsxswh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-5-14 下午01:45:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsxsglService extends SuperServiceImpl<TsxsglForm, TsxsglDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private TsxsglDao dao = new TsxsglDao();
	
	public TsxsglService() {
		super.setDao(dao);
	}
	
	
	/**
	 * 根据Id查询特殊学生信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsInfoById(String id){
		
		return dao.getTsxsInfoById(id);
	}
	
	public TsxsglForm getModelByXh(TsxsglForm model) throws Exception{
		
		TsxsglForm myForm= dao.getModelByXh(model);
		if(null==myForm){
			myForm=new TsxsglForm();
			myForm.setXh(model.getXh());
		}
		return myForm;
	}
	
	
	public boolean tsxsEdit(TsxsglForm model,User user) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			model.setLrsj(GetTime.getTimeByFormat(DATE_FORMAT));
			model.setLrr(user.getUserName());
			String id = UniqID.getInstance().getUniqIDHash();
			model.setId(id);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:更新关注状态
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-15 下午03:08:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param gzzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateBatchGzStatus(String[] id,String gzzt) throws Exception{
		
		return dao.updateBatchGzStatus(id, gzzt);
	}
	
}



