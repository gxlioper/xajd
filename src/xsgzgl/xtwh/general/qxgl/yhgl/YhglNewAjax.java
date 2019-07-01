package xsgzgl.xtwh.general.qxgl.yhgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.Encrypt;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.comm.BasicModel;
import xsgzgl.xtwh.general.qxgl.yhzgl.YhzglNewForm;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_权限管理_用户管理_ajax类
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
public class YhglNewAjax extends BasicAction {

	YhglNewService service = new YhglNewService();
	
	// 表名
	private final String TABLENAME = "yhb";
	
	// 保存字段
	private final String[] SAVE = {"yhm","xm","kl","szbm","zdm","dwdm","qx"};

	// 修改字段
	private final String[] UPDATE = {"yhm","xm","szbm","zdm","dwdm","qx"};
	
	/**
	 * 用户组信息查询
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */	
	public ActionForward yhxxSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		YhglNewForm myForm = (YhglNewForm) form;
		YhglNewInit init = new YhglNewInit();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		init.initSearch(rForm, myForm, user, request);
		
		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {"ie","checkbox"});
		
		service.getModelValue(myForm, request);

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);
		
		String checkbox = valueMap.get("checkbox");
		rsModel.setCheckBoxRs(checkbox);
		// =================== end ===================
		
		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================
		
		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getYhxxTop(user,checkbox);
		// 结果集
		ArrayList<String[]> rsArrList = service.getYhxxList(myForm,user);
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
	 * 用户增加
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public ActionForward yhxxAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Encrypt ept = new Encrypt();
		
		YhglNewForm myForm = (YhglNewForm) form;

		BasicModel model=new BasicModel();
		
		HashMap<String,String> valueMap=service.getValueMap(request, SAVE);
		
		// 参数赋值
		service.getModelValue(myForm, request);
		
		// --------------表名------------
		model.setTableName(TABLENAME);
		// --------------需要保存的值--------------------
		model.setValueMap(valueMap);
		
		//消息信息
		String message="";
				
		boolean flag=false;
		
		//验证用户名称是否存在
		flag = service.checkYhm(myForm.getYhm());
		
		if(flag){
			model.getValueMap().put("kl", ept.encrypt(myForm.getKl()));
			flag=service.saveInfo(model);
			if(flag){
				service.saveYhqx(myForm.getYhm());
			}
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
	 * 加载用户信息
	 * 
	 * @date 2013-01-10
	 * @author zhanghui
	 */
	public ActionForward getYhxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String yhm = request.getParameter("yhm");
				
		Map<String, String> map = service.getYhxx(yhm);
		
		String json = JSONObject.fromObject(map).toString();	
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);

		return null;
	}
	
	/**
	 * 用户修改
	 * 
	 * @date 2013-01-10
	 * @author zhanghui
	 */
	public ActionForward yhxxUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		YhglNewForm myForm = (YhglNewForm) form;

		BasicModel model=new BasicModel();
		HashMap<String,String> valueMap=service.getValueMap(request, UPDATE);
		
		// 参数赋值
		service.getModelValue(myForm, request);
		
		// --------------表名------------
		model.setTableName(TABLENAME);
		// --------------需要修改的值--------------------
		model.setValueMap(valueMap);
		// --------------主键------------
		model.setPk(new String[]{"yhm"});
		
		//消息信息
		String message="";
				
		boolean flag=false;
		
		HashMap<String,String> map = service.getYhxx(myForm.getYhm());		
		
		flag=service.updateInfo(model);
		
		if(flag && !myForm.getZdm().equalsIgnoreCase(map.get("zdm"))){
			service.saveYhqx(myForm.getYhm());
		}
				
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
			
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 用户删除
	 * 
	 * @date 2013-01-09
	 * @author zhanghui
	 */
	public ActionForward yhxxDelete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		YhglNewForm myForm = (YhglNewForm) form;
				
		// 参数赋值
		service.getModelValue(myForm, request);
		
		//消息信息
		String message="";
				
		boolean flag=false;
				
		flag=service.deleteYhxx(myForm);
				
		message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
				: MessageInfo.MESSAGE_DEL_FALSE;
	
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 用户密码初始化
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public ActionForward yhmmCsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		YhglNewForm myForm = (YhglNewForm) form;
				
		// 参数赋值
		service.getModelValue(myForm, request);
		
		//消息信息
		String message="";
				
		boolean flag=false;
				
		flag=service.cshYhmm(myForm);
				
		message = flag ? MessageInfo.MESSAGE_INIT_SUCCESS
				: MessageInfo.MESSAGE_INIT_FALSE;
	
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	/**
	 * 用户密码初始化-根据查询结果批量
	 */
	public ActionForward yhmmCshPl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		YhglNewForm myForm = (YhglNewForm) form;
		YhglNewInit init = new YhglNewInit();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		init.initSearch(rForm, myForm, user, request);
		
		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {"ie","checkbox"});
		
		service.getModelValue(myForm, request);

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);
		
		String checkbox = valueMap.get("checkbox");
		rsModel.setCheckBoxRs(checkbox);
		// =================== end ===================
		
		ArrayList<String[]> rsArrList = service.getYhxxAllList(myForm,user);
		String[] primarykey_checkVal = new String[rsArrList.size()];
		for (int i = 0; i < rsArrList.size(); i++) {
			primarykey_checkVal[i] = rsArrList.get(i)[0];
		}
		myForm.setPrimarykey_checkVal(primarykey_checkVal);
		
		String message="";
		
		boolean flag=false;
		
		flag=service.cshYhmm(myForm);
		
		message = flag ? MessageInfo.MESSAGE_INIT_SUCCESS : MessageInfo.MESSAGE_INIT_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * 用户信息修改
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public ActionForward yhxxPlupdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		YhglNewForm myForm = (YhglNewForm) form;
				
		// 参数赋值
		service.getModelValue(myForm, request);
		
		//消息信息
		String message="";
				
		boolean flag=false;
				
		flag=service.plQxYhxx(myForm);
				
		message = flag ? MessageInfo.MESSAGE_WORK_SUCCESS
				: MessageInfo.MESSAGE_WORK_FALSE;
	
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	/**
	 * 用户分组
	 */
	public ActionForward yhfz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		YhglNewForm myForm = (YhglNewForm) form;
		
		// 参数赋值
		service.getModelValue(myForm, request);
		
		//消息信息
		String message="";
		
		boolean flag=false;
		
		flag=service.yhfz(myForm);
		
		message = flag ? MessageInfo.MESSAGE_WORK_SUCCESS
				: MessageInfo.MESSAGE_WORK_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
	/**
	 * 用户停用
	 */
	public ActionForward yhty(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		YhglNewForm myForm = (YhglNewForm) form;
		
		// 参数赋值
		service.getModelValue(myForm, request);
		
		//消息信息
		String message="";
		
		boolean flag=false;
		
		flag=service.yhty(myForm);
		
		message = flag ? MessageInfo.MESSAGE_WORK_SUCCESS
				: MessageInfo.MESSAGE_WORK_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
	/**
	 * @description	： 思政是否可见
	 * @author 		： CP（1352）
	 * @date 		：2017-12-6 下午02:17:45
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward szkj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String data = request.getParameter("zghs");	
		String[] zghs=data.split(",");
		String sfbl = request.getParameter("sfbl");
		String message="";
		int[] flag=service.szkj(zghs,sfbl);
		message = (flag.length==zghs.length) ? MessageInfo.MESSAGE_WORK_SUCCESS
				: MessageInfo.MESSAGE_WORK_FALSE;
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 获取辅导员信息
	 * 
	 * @date 2013-01-14
	 * @author zhanghui 
	 */
	public ActionForward getFdyxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String yhm = request.getParameter("yhm");
		
		Map<String, String> map = service.getFdyxx(yhm);
		
		String json = JSONObject.fromObject(map).toString();	
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		
		return null;
	}
}
