/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 上午10:28:41 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwwh;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部职务Service
 * @作者： zhangjw
 * @时间： 2013-8-7 上午10:28:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwwhService extends SuperServiceImpl<ZwwhForm, ZwwhDAO> {

	private ZwwhDAO dao = new ZwwhDAO();

	public ZwwhService() {
		super.setDao(dao);
	}
	/**
	 * @描述:获取职务列表
	 * @作者：zhangjw
	 * @日期：2013-8-8 下午3:30:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getZwList(String zwid){
		return dao.getZwList(zwid);
	}
	
	/**
	 * @描述:根据职务类型验证职务数量
	 * @作者：zhangjw
	 * @日期：2013-8-13 上午9:20:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lxdms
	 * @return
	 * @throws SQLException
	 * boolean 返回类型
	 */
	public int yzZwCount(String[] lxdms) throws SQLException{
		StringBuffer sql = new StringBuffer();
		if(lxdms.length>0){
			String tt= "";
			for (int i = 0; i < lxdms.length; i++) {
				tt = "'"+lxdms[i]+"',";
			}
			int m = tt.lastIndexOf(",");
			tt = tt.substring(0,m);
			sql.append(tt);
		}else{
			sql.append("''");
		}
		return dao.getZwCount(sql.toString());
	}
}
