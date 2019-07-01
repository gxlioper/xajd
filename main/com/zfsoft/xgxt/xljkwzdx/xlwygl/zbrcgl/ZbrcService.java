/**
 * @部门:学工产品事业部
 * @日期：2014-5-26 上午11:57:47 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.zbrcgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-26 上午11:57:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZbrcService extends SuperServiceImpl<ZbrcForm, ZbrcDao> {

	public ZbrcService(){
		setDao(new ZbrcDao());
	}
	
	public HashMap<String, String> getZbrcxx(String zbid){
		return dao.getZbrcxx(zbid);
	}
	
	/**
	 * @描述: 未/已上报班级
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-24 上午11:17:38
	 * @param model
	 * @param user
	 * @param sblx
	 * @return
	 * @throws Exception
	 * List<HashMap<String, String>> 返回类型
	 */
	public List<HashMap<String, String>> cxSbbj(ZbrcForm model, User user, String sbbjlx)
			throws Exception {
		return dao.cxSbbj(model, user, sbbjlx);
	}
	/**
	 * 
	 * @描述:
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-9 下午03:47:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zblx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getZbrcListByLx(String zblx){
		return dao.getZbrcListByLx(zblx);
	}
	
	/**
	 * 
	 * @描述: 唯一性判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-19 下午04:58:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveRecordForjg(ZbrcForm form) {
		return dao.isHaveRecordForjg(form);
	}
	
	/**
	 * 
	 * @描述: 唯一性判断
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-9-19 下午04:58:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHaveRecordForjgU(ZbrcForm form) {
		return dao.isHaveRecordForjgU(form);
	}
	
}
