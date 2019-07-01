/**
 * @部门:学工产品事业部
 * @日期：2014-6-3 下午01:30:48 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.ylxlxsgl;


import java.sql.SQLException;
import java.util.HashMap;

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

public class YlxlxsglService extends SuperServiceImpl<YlxlxsglForm, YlxlxsglDao> {

	/**
	 * 
	 * @描述:根据学号查询学生心理信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-5 下午02:12:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> ylxlxsxx(String xh) throws Exception{
		return dao.ylxlxsxx(xh);
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @描述:检查数据库是否存在
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-26 上午10:54:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public boolean checkExist(String xh) throws SQLException{
		return dao.checkExist(xh) >= 1;
	}
	
	
	public YlxlxsglService(){
		setDao(new YlxlxsglDao());
	}
}
