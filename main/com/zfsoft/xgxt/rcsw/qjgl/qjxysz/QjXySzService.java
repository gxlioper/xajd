/**
 * @部门:学工产品事业部
 * @日期：2015-11-3 上午10:59:33 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.qjxysz;

import java.util.HashMap;

import oracle.sql.CLOB;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-11-3 上午10:59:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QjXySzService extends SuperServiceImpl<QjXySzForm,QjXySzDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	public HashMap<String, String> getQjXySzDada() throws Exception{
		HashMap<String,String> data =  dao.getQjXySzDada();
//		data.put("content", this.getContent_Clob());
		return data;	
	}
	
	public boolean save_xyszData(QjXySzForm form)throws Exception{
	    if(this.del_Table_content()){
	    	form.setBjsj(GetTime.getTimeByFormat(DATE_FORMAT));
	    	form.setMc("请假协议设置");
	    	return dao.save(form);
	    }else{
	    	return false;
	    }
	}
	
//	//获取clob字段（协议设置内容）
//	public String getContent_Clob() throws Exception{
//		return dao.getContent_Clob();
//	}
	
	//先删一遍表中的数据
	public boolean del_Table_content() throws Exception{
		return dao.del_Table_content();
	}
}
