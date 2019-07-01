/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午03:32:58 
 */  
package com.zfsoft.xgxt.rcsw.xxgl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xsgzgl.qgzx.cjgl.QgzxCjglForm;
import xsgzgl.qgzx.cjgl.QgzxCjglService;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 学生献血管理 -业务处理类
 * @作者： zhangjw 
 * @时间： 2013-4-18 下午03:26:39 
 * @版本： V5.1.75
 */
public class XsxxglService extends SuperServiceImpl<XsxxglForm, XsxxglDAO>{

	private XsxxglDAO dao = new XsxxglDAO();
	
	public XsxxglService(){
		super.setDao(dao);
	}
	/**
	 * @描述:查询条件列表
	 * @作者：zhangjw
	 * @日期：2013-4-18 下午04:33:13
	 * @修改记录: 
	 * @param request
	 * @param myForm
	 * @return  HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getFfmrCs(HttpServletRequest request,XsxxglForm myForm){
		QgzxCjglService qservice = new QgzxCjglService();
		QgzxCjglForm model = new QgzxCjglForm();
		/**
		 * 获取查询条件 
		 * 因为QgzxCjglService有写过为了避免重复，在这里进行转换获取
		 */
		model.setXh(myForm.getXh());
		model.setDoType(myForm.getType());
		model.setBz(myForm.getBz());
		model.setXn(myForm.getXn());
		HashMap<String,String> rs = qservice.setFfmrCs(request,model);
		return rs;
	}
}
