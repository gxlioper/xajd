package xsgzgl.xtwh.general.qxgl.yhzgl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mortbay.util.UrlEncoded;

import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_权限管理_用户组管理_ajax类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author zhanghui
 * @version 1.0
 */
public class YhzglNewAjax extends BasicAction  {
	
	YhzglNewService service = new YhzglNewService();
	
	/**
	 * 用户组信息查询
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */	
	public ActionForward yhzxxSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		YhzglNewForm myForm = (YhzglNewForm) form;
		YhzglNewInit init = new YhzglNewInit();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		init.initSearch(rForm, myForm, user, request);
		
		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {"ie"});
		
		service.getModelValue(myForm, request);

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);
		// =================== end ===================
		
		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================
		
		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getYhzxxTop(user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getYhzxxList(myForm,user);
		// 构建结果集
		String spHtml = "";
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");
		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 用户组增加
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	@SystemLog(description="访问系统维护-权限管理-用户组管理-增加ZMC:{zmc}")
	public ActionForward yhzxxAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		YhzglNewForm myForm = (YhzglNewForm) form;
		
		// 参数赋值
		service.getModelValue(myForm, request);
		
		//消息信息
		String message="";
				
		boolean flag=false;
		
		//验证用户组名称是否存在
		flag = service.checkZmc(myForm.getZmc());
		
		if(flag){
			flag=service.addYhzxx(myForm);
					
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}else{
			message = "exist";
		}
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	/**
	 * 用户组复制
	 * 
	 * @date 2013-03-19
	 * @author zhangjw
	 */
	public ActionForward yhzxxCopy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		YhzglNewForm myForm = (YhzglNewForm) form;
		myForm.setZmc(URLDecoder.decode(myForm.getZmc(),"UTF-8"));
		// 参数赋值
		service.getModelValue(myForm, request);
		
		//消息信息
		String message="";
				
		boolean flag=false;
		
		//验证用户组名称是否存在
		flag = service.checkZmc(myForm.getZmc());
		
		if(flag){
			flag=service.copyYhzxx(myForm);
					
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}else{
			message = "exist";
		}
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 用户组修改
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	@SystemLog(description="访问系统维护-权限管理-用户组管理-增加ZMC:{zmc},ZDM:{zdm}")
	public ActionForward yhzxxUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		YhzglNewForm myForm = (YhzglNewForm) form;
		
				
		// 参数赋值
		service.getModelValue(myForm, request);
		
		//消息信息
		String message="";
				
		boolean flag=false;
		
		//验证用户组名称是否存在
		flag = service.checkZmc(myForm.getZmc());
		
		if(flag){
			flag=service.updateYhzxx(myForm);
					
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}else{
			message = "exist";
		}
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 用户组删除
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	@SystemLog(description="访问系统维护-权限管理-用户组管理-删除value:{primarykey_checkVal}")
	public ActionForward yhzxxDelete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		YhzglNewForm myForm = (YhzglNewForm) form;
				
		// 参数赋值
		service.getModelValue(myForm, request);
		
		//消息信息
		String message="";
				
		boolean flag=false;
		
		flag = service.checkYhz(myForm.getPrimarykey_checkVal());
		
		if(flag){
			flag=service.deleteYhzxx(myForm);
					
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}else{
			message = "删除失败！<br>已维护用户的用户组不可删除！";
		}
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
}
