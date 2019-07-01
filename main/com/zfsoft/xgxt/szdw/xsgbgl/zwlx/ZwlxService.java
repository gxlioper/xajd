/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 上午10:28:41 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwlx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部职务类型Service
 * @作者： zhangjw
 * @时间： 2013-8-7 上午10:28:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwlxService extends SuperServiceImpl<ZwlxForm, ZwlxDAO> {

	private ZwlxDAO dao = new ZwlxDAO();

	public ZwlxService() {
		super.setDao(dao);
	}
	
	public List<HashMap<String, String>> getList(){
		return dao.getList();
	}
	/**
	 * @描述:根据类型名称查询数量
	 * @作者：zhangjw
	 * @日期：2013-8-28 下午6:01:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lxname
	 * @return
	 * @throws SQLException
	 * int 返回类型
	 */
	public boolean yzLxName(String lxname) throws SQLException{
		
		int result = dao.getCountBylxName(lxname);
		if(result>0){
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * 
	 * @描述: 审批流程名称查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-26 上午10:21:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splc
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String>  getSplcMc(String splc){
		return dao.getSplcMc(splc);
	}
	
	
}
