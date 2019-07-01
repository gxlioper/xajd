package xsgzgl.pjpy.general.wdpj.lssb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import xgxt.pjpy.comm.pjpy.pjlc.xmsb.PjpyXmsbInit;
import xgxt.utils.Pages;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyWdpjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjLssbInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXssqInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjInit;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-7-11 上午10:36:33</p>
 */
public class WdpjLssbAction extends BasicAction{
	
	// -------------------------结果集相关 begin--------------------------------
	/**
	 * 查询上报学生列表
	 * (指定项目、班级)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward searchWdpjLssb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		RequestForm rForm = new RequestForm();
		
		PjpyWdpjModel model = new PjpyWdpjModel();
		SearchRsModel rsModel = new SearchRsModel();
		
		PjpyGeneralService myService = new PjpyGeneralService();
		
		PjpyWdpjInit init = new PjpyWdpjInit();
		
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initLssb(rForm, myForm, user, request);
		WdpjLssbInterface service = myService.getWdpjLssbService(myForm);
		
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// 项目代码
		String xmdm=otherValue[1];
		model.setXmdm(xmdm);
		// 班级代码
		String bjdm=otherValue[2];
		model.setBjdm(bjdm);
		
		// ==================分页相关========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		HashMap<String,String>qdrsMap=service.getQdrsByBj(xmdm, bjdm);
		// 标题
		List<HashMap<String, String>> topTr = service.getWdpjLssbTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getWdpjLssbList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createWdpjLssbHTML(rsModel,model,qdrsMap, rsArrList,
				user);
		
		spHtml+=service.createKidneyDiv(rsModel, model,qdrsMap, rsArrList, user);
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
	// -------------------------结果集相关 end--------------------------------

	// -------------------------上报、撤销相关 begin--------------------------------	
	/**
	 * 保存项目上报
	 * author qlj
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXmsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BasicModel basicModel=new BasicModel();
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjpyWdpjInit init = new PjpyWdpjInit();
	
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initSaveLssb(rForm, myForm,basicModel, user, request);
		WdpjLssbInterface service = myService.getWdpjLssbService(myForm);
		// 操作类型
		String opera=request.getParameter("opera");

		//消息信息
		String message="";
		
		String xmdm= request.getParameter("xmdm");
		
		String xh=request.getParameter("xh");
		
		//保存数据方法
		boolean flag=false;
		if("add".equalsIgnoreCase(opera)){
			
			flag=service.saveXmsb(basicModel, user);
			
			if(flag){
				flag=service.saveLssbShInfo(xmdm, xh);
			}
			
			if (flag){
				
				//===待办工作=====2013-1-18===qph=======begin=========
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
			
	
		}else{
//			 执行修改方法
			flag=service.updateLssbInfo(basicModel, user);
			
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		
		}
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}

	/**
	 * 撤销老师上报信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward disfrockLssbInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BasicModel model = new BasicModel();
		
		BasicService service = new BasicService();
		// 设置主键字段
		model.setPk(new String[] { "xh","xmdm","pjxn","pjxq","pjnd" });
		// 将unic码转gbk
		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));
		// 获取主键值
		String[] pkValue = pkStr.split("!!array!!");

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		// --------------表名------------
		model.setTableName("xg_pjpy_pjxmsqb");

		// 主键
		model.setPkValue(pkValue);
		
		// 删除申请表信息
		flag = service.batchDelete(model);
		
		if(flag){
			// 删除审核表信息
			model.setTableName("xg_pjpy_pjxmshb");
			
			flag = service.batchDelete(model);
			
			if (flag){
				//===待办工作==2013-1-18==qph===begin=======
				MyJobsManager manager = new MyJobsImpl();
				manager.removeJob(pkValue, "评奖评优");
				//===待办工作==2013-1-18==qph===end=========
			}
			
		}
		
		// 提示信息
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 老师上报窗口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showLssbDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		//init.initSaveXssq(rForm, myForm, user, request);
		WdpjLssbInterface service = myService.getWdpjLssbService(myForm);
		// 操作类型
		String xh=request.getParameter("xh");
		
		String xmdm = request.getParameter("xmdm");
		
		String opera = request.getParameter("opera");
		
		//老师上报
		service.showLssbDiv( user,opera,xmdm,xh, response);
		
		return null;
	}
	
	/**
	 * 判断学生是否有上报资格
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkXssbZg(ActionMapping mapping,
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
		// 项目代码
		String xmdm=request.getParameter("xmdm");
		// 学号
		String xh=request.getParameter("xh");
		model.setXh(xh);
		// 项目代码
		model.setXmdm(xmdm);
		// 判断学生是否有申请奖学金的资格
		message=pjtjService.checkSqzg(model, user);
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	// -------------------------上报、撤销相关 end--------------------------------		
	
	// -------------------------学生信息相关 begin------------------------
	/**
	 * 显示学生相关信息(课程成绩、综测成绩)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showXsxxDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		WdpjLssbInterface service = myService.getWdpjLssbService(myForm);
		// 操作类型
		String xh=request.getParameter("xh");
		
		String xmdm = request.getParameter("xmdm");
		
		service.showXsxxDiv( user,xmdm,xh, response);
		
		return null;
	}
	// -------------------------学生信息相关 end------------------------
}
