/**
 * @部门:学工产品事业部
 * @日期：2017-10-11 下午03:24:37 
 */  
package com.zfsoft.xgxt.znxgl.wdznx;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.action.Base;
import xgxt.base.DBEncrypt;
import xgxt.base.Encrypt;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2017-10-11 下午03:24:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JgxxtsService {

	private Log logger = LogFactory.getLog(JgxxtsService.class);
	
	public String sendMsg(HashMap<String, String> paramMap) {
		
		String parameterList=null;
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
		String time=format.format(date);
		parameterList="{"+"\"timeToLive\":\""+Base.getInitProperties().get("timeToLive")+"\","
			+"\"tsdx\":[\""+paramMap.get("account")+"\"],"
			+"\"tsdxlx\":\""+Base.getInitProperties().get("tsdxlx")+"\","
			+"\"tsfs\":\""+Base.getInitProperties().get("tsfs")+"\","
			+"\"tsnr\":\""+paramMap.get("content")+"\","
			+"\"tspt\":\""+Base.getInitProperties().get("tspt")+"\","
			+"\"appType\":\""+Base.getInitProperties().get("appType")+"\","
			+"\"tsry\":\""+paramMap.get("tsry")+"\","
			+"\"extras\":{"
				+"\"sys_type\" : \""+Base.getInitProperties().get("sys_type")+"\","
				+"\"msg_type\" : \""+Base.getInitProperties().get("msg_type")+"\","   
				+"\"func_type\" : \""+Base.getInitProperties().get("func_type")+"\","											
				+"\"this_unread\" : \""+paramMap.get("newsCount")+"\","								
				+"\"login_flg\" : \""+Base.getInitProperties().get("login_flg")+"\","  											
				+"\"send_time\" : \""+time+"\","
				+"\"xtbm\" : \""+Base.getInitProperties().get("xtbm")+"\","
				+"\"webURL\" : \""+paramMap.get("webUrl")+"\","
				+"\"webNAME\" : \""+paramMap.get("webName")+"\""
				+"}"
			+"}";
		DBEncrypt tool=new DBEncrypt();
		byte[] a=Base.getInitProperties().get("PushEncryptWord").getBytes();
		String befor=tool.dCode(a);
		Encrypt ec = new Encrypt();
		String sign=ec.encrypt(parameterList+befor);
		logger.info("开始推送消息  ：");
		String endpoint = Base.getInitProperties().get("endpoint");
		logger.info(endpoint);
		Service service = new Service();
		Call call;
		String ret=null;
		try {
		call = (Call) service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		call.setOperationName("Push");
		ret = (String) call.invoke(new Object[]{parameterList,sign});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(ret);
		return ret;

			
	}
}
