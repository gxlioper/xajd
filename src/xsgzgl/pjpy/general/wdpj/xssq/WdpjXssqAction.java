package xsgzgl.pjpy.general.wdpj.xssq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXssqInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjInit;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;
import common.Globals;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_学生申请_通用_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class WdpjXssqAction extends BasicAction {
	
	/**
	 * 查询项目申请结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchWdpjXssq(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjInit init = new PjpyWdpjInit();
		WdpjXssqModel model = new WdpjXssqModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initXssq(rForm, myForm, user, request);
		WdpjXssqInterface service = myService.getWdpjXssqService(myForm);
		
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// 项目类型
		String xmlx = otherValue[1];
		model.setXmlx(xmlx.trim());
		// 项目性质
		String xmxz = otherValue[2];
		model.setXmxz(xmxz.trim());
		// 项目名称
		String xmmc = otherValue[3];
		if (!Base.isNull(xmmc.trim())) {
			model.setXmmc(myService.unicode2Gbk(xmmc.trim()));
		}	
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getWdpjXssqTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getWdpjXssqList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createWdpjXssqHTML(rsModel, model, rsArrList,
				user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	//================old============================
	/**
	 * 查询学生申请功能的项目信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchXssqXmxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjInit init = new PjpyWdpjInit();
		WdpjXssqModel model = new WdpjXssqModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initXssq(rForm, myForm, user, request);
		WdpjXssqInterface service = myService.getWdpjXssqService(myForm);
		
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// 项目类型
		String xmlx = otherValue[1];
		model.setXmlx(xmlx.trim());
		// 项目性质
		String xmxz = otherValue[2];
		model.setXmxz(xmxz.trim());
		// 项目名称
		String xmmc = otherValue[3];
		if (!Base.isNull(xmmc.trim())) {
			model.setXmmc(myService.unicode2Gbk(xmmc.trim()));
		}	
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getWdpjXssqTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getWdpjXssqList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createWdpjXssqHTML(rsModel, model, rsArrList,
				user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 保存学生申请信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXssqInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BasicModel basicModel=new BasicModel();
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjpyWdpjInit init = new PjpyWdpjInit();
	
		CommService commService=new CommService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initSaveXssq(rForm, myForm, basicModel, user,request);
		WdpjXssqInterface service = myService.getWdpjXssqService(myForm);
		// 操作类型
		String opera=request.getParameter("opera");

		//消息信息
		String message="";
		
		String xmdm= request.getParameter("xmdm");
		//保存数据方法
		boolean flag=false;
		if("add".equalsIgnoreCase(opera)){ // 新增
			// 评奖评优条件接口
			WdpjPjtjInterface pjtjService = myService.getWdpjPjtjService(myForm);
			
			PjpyWdpjModel model=new PjpyWdpjModel();
			// 获取request中所需的值
			HashMap<String,String>valueMap=basicModel.getValueMap();
			// 学号
			model.setXh(valueMap.get("xh"));
			// 项目代码
			model.setXmdm(valueMap.get("xmdm"));
			// 判断学生是否有申请奖学金的资格
			message=pjtjService.checkSqzg(model, user);
			
			// 不满足申请的情况返回为空
			if(Base.isNull(message)){
				
				// 保存申请记录
				flag=service.saveXssqInfo(basicModel,commService.getValueMap(request, "xg_pjpy_pjxmsqb"), user);
				
				if(flag){
					// 保存审核记录
					flag=service.saveWdpjShInfo(xmdm, user);
				}
				
				if (flag){
					//===待办工作=====2013-1-18===qph=======begin=========
					String xh = model.getXh();
					String[] spgw = pjxmService.getSpgwByXmdm(xmdm);
					HashMap<String,String> pjxmInfo = pjxmService.getPjxmInfo(xmdm);
					PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
					
					if (null != spgw && spgw.length > 0){
						Job job = Job.getJobInstance(xh+"!!@@!!"+xmdm+"!!@@!!"+jbszModel.getPjxn()+"!!@@!!"+jbszModel.getPjxq()+"!!@@!!"+jbszModel.getPjnd(), xh, spgw[0], 
								"general_pjpy.do?method=xmshManage&xmdm="+xmdm+"&spgw="+spgw[0], 
								"pjpy_general_wdpj.do","评奖评优", pjxmInfo.get("xmmc"));
						MyJobsManager manager = new MyJobsImpl();
						manager.pushJob(job);
					}
					//===待办工作=====2013-1-18===qph=======end===========
				}
				
				message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
						: MessageInfo.MESSAGE_SAVE_FALSE;
		
			}
			
		}else { // 修改
			
			// 执行修改方法
			flag=service.updateXssqInfo(basicModel,commService.getValueMap(request, "xg_pjpy_pjxmsqb"), user);
			
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		
		}
		
		
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 判断学生申请资格
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkXssqZg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// 用户对象

		//消息信息
		String message="";
		
		// 评奖评优条件接口
		WdpjPjtjInterface pjtjService = myService.getWdpjPjtjService(myForm);
		
		PjpyWdpjModel model=new PjpyWdpjModel();
		// 获取request中所需的值
		String xmdm=request.getParameter("xmdm");
		// 学号
		model.setXh(user.getUserName());
		// 项目代码
		model.setXmdm(xmdm);
		// 判断学生是否有申请奖学金的资格
		message=pjtjService.checkSqzg(model, user);
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
		
	/**
	 * 弹出学生申请DIV(申请、修改根据类型判断)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showXssqDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		WdpjXssqInterface service = myService.getWdpjXssqService(myForm);
		// 操作类型
		String opera=request.getParameter("opera");
		// 项目代码
		String xmdm = request.getParameter("xmdm");

		//消息信息
		String message="";
		
		//保存数据方法
		String xxdm = Base.xxdm;
		if(Base.isNull(message)){
			if(xxdm.equals(Globals.XXDM_BJLHDX)){
				service.showXssqDivForBJLH(opera,xmdm, user, response);
			}else{
				service.showXssqDiv(opera,xmdm, user, response);
			}
		}
		return null;
	}
	
	/**
	 * 撤销学生申请信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward disfrockXssqInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BasicModel model = new BasicModel();
		
		BasicService service = new BasicService();

		model.setPk(new String[] { "xh","xmdm","pjxn","pjxq","pjnd" });

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		// --------------表名------------
		model.setTableName("xg_pjpy_pjxmsqb");

		// 主键
		model.setPkValue(pkValue);

		flag = service.batchDelete(model);
		
		if(flag){
			
			model.setTableName("xg_pjpy_pjxmshb");
			
			flag = service.batchDelete(model);
			
			
			if (flag){
				//===待办工作==2013-1-18==qph===begin=======
				MyJobsManager manager = new MyJobsImpl();
				manager.removeJob(pkValue, "评奖评优");
				//===待办工作==2013-1-18==qph===end=========
			}
			
		}

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
}
