/**
 * @部门:学工产品事业部
 * @日期：2016-6-21 上午09:42:32 
 */  
package com.zfsoft.xgxt.xszz.sqlydmwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助-困难生认定
 * @类功能描述: 申请理由代码维护 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-6-21 上午09:42:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SqlyDmwhService extends SuperServiceImpl<SqlyDmwhForm, SqlyDmwhDao> {
	
	private SqlyDmwhDao dao = new SqlyDmwhDao();
	
	public SqlyDmwhService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述: 重复性验证
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-21 下午01:34:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistBySqlywh(SqlyDmwhForm form) {
		
		boolean flag = false;
		 
		 String num = dao.sqlywhCheckExist(form);
		 if(!"0".equalsIgnoreCase(num)){
			 flag = true;
		 }
		 
		 return flag;	
	} 
	
}
