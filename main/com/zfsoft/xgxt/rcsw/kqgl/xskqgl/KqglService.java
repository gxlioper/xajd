/**
 * @部门:学工产品事业部
 * @日期：2014-6-6 下午04:29:02 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.xskqgl;

import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考勤管理模块
 * @类功能描述: 学生考勤管理
 * @作者： 陶钢军[工号:1075]
 * @时间： 2014-6-6 下午04:29:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqglService extends SuperServiceImpl<KqglForm, KqglDao> {
	
	private KqglDao dao = new KqglDao();
	
	public KqglService(){
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @描述:判断是否已存在考勤登记表中（主键：学号，考勤时间，考勤类型）
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-9 上午10:03:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByKqdj(KqglForm form) 
			throws Exception {
		
		if("save".equalsIgnoreCase(form.getType())){
			//新增判断
			String num = dao.checkExistForSave(form);
			return Integer.valueOf(num) > 0;
		}else {
			//修改判断
			String num = dao.checkExistForUpdate(form);
			return Integer.valueOf(num) > 0;
		}
	}
	
	
	/**
	 * 
	 * @描述:考勤登记结果单个查看
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-9 上午10:05:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneKqdjList(String kqdjid) {
		
		return dao.getOneKqdjList(kqdjid);
	}
}
