package xsgzgl.rcsw.zjbb;

import java.io.File;
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
import xgxt.utils.String.StringUtils;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.jqlx.GyglJqlxForm;
import xsgzgl.gygl.jqlx.GyglJqlxInit;
import xsgzgl.gygl.jqlx.GyglJqlxService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXmshInterface;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class RcswZjbbAjax extends BasicAction{

	RcswZjbbService service = new RcswZjbbService();
	
	RcswZjbbInit init =new RcswZjbbInit();
	
	// -------------------------补办设置管理 begin ---------------------------------
	/**
	 * 补办设置管理查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbszSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZjbbForm myForm = (RcswZjbbForm) form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		
		init.initBbszSearch(rForm, model, request, user);

		// 结果集显示字段

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});
		
		service.getModelValue(myForm, request);

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================


		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getBbszList(myForm,model);

		// 构建结果集
		String spHtml = service
				.createBbszSearchHTML(rsModel, model, rsArrList, user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 证件补办结果自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward zjbbjgExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BasicModel model = new BasicModel();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		model.setColList(new String[] { "id", "xh", "xm", "nj", "xydm",
				"xymc", "zydm","zymc","bjdm","bjmc","sqsj","zjmc","sqjg" });
		
		//init.initBbjgSearch(rForm, model, request, user);
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//User user = getUser(request);// 用户对象
		
		
		List<HashMap<String,String>> resultList =  service.getBbjgExportList(model,user);
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 补办设置管理查询保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		init.initBbszSave(model, request);
		
		
		flag = service.saveInfo(model);
		
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);


		return null;
	}
	
	/**
	 * 补办设置管理查询修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiBbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		init.initBbszModi(model, request);
		
		flag = service.updateInfo(model);
		
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);


		return null;
	}
	
	/**
	 * 显示证件补办模式窗口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showZjbb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		RcswZjbbForm myForm = (RcswZjbbForm) form;
		BasicModel model=new BasicModel();
		
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		String option=request.getParameter("option");
		
		String id = request.getParameter("id");
		
		String[]pkValue=new String[]{id};
		String[]pk=new String[]{"id"};
		
		model.setPk(pk);
		model.setPkValue(pkValue);
		model.setTableName("xg_rcsw_zjbbszb");
		
		//消息信息
		String message="";
		
		//保存数据方法
		
		if(Base.isNull(message)){
			
			String html=service.showZjbbDiv(user,response,option,model);
			request.setAttribute("html",html);
			request.setAttribute("zjmc",request.getParameter("zjmc"));
			request.setAttribute("lcid",request.getParameter("lcid"));
			request.setAttribute("xmid",request.getParameter("xmid"));
			request.setAttribute("id", id);
			return mapping.findForward("bbszzj");
		}

		return null;
	}
	
	/**
	 * 批量删除询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteBbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] {"id"});

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		// --------------表名------------
		model.setTableName("xg_rcsw_zjbbszb");

		// 主键
		model.setPkValue(pkValue);

		flag = service.batchDelete(model);
		
		message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
				: MessageInfo.MESSAGE_DEL_FALSE;
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	// -------------------------补办设置管理 end ---------------------------------
	
	/**
	 * 批量删除询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] {"id"});

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		// --------------表名------------
		model.setTableName("xg_rcsw_zjbbsqb");

		// 主键
		model.setPkValue(pkValue);

		// 批量删除

		flag = service.batchDelete(model);
		
		if(flag){
			model.setPk(new String[] {"sqid"});
			model.setTableName("xg_gygl_jqlxshb");
			service.batchDelete(model);
			
			//=======删除待办==2013-1-15==qph==begin============
			MyJobsManager manager = new MyJobsImpl();
			manager.removeJob(pkValue, "证件补办");
			//=======删除待办==2013-1-15==qph==end============
		}

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 假期留校申请查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbsqSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZjbbForm myForm = (RcswZjbbForm) form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		
		init.initBbsqSearch(rForm, model, request, user);

		// 结果集显示字段

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);

		model.setSearchModel(searchModel);

		// ==================高级查询相关 end========================

		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getBbsqList(model);

		// 构建结果集
		String spHtml = service
				.createBbsqSearchHTML(rsModel, model, rsArrList, user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	

	/**
	 * 假期留校审核查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbshSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZjbbForm myForm=(RcswZjbbForm)form;
		
		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		model.setColList(new String[] { "id","xh","xm","nj","bjmc","sqsj","shzt" });
		
		init.initBbshSearch(rForm, model, request, user);

		// 结果集显示字段

		String[]otherValue=request.getParameter("otherValue").split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// IE版本
		String stylePath = otherValue[1];
		rsModel.setStylePath(stylePath);
		// 
		String spgw = otherValue[3];
		model.getValueMap().put("spgw", spgw);
		
		String xmdm = otherValue[2];
		model.getValueMap().put("xmdm", xmdm);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);

		model.setSearchModel(searchModel);

		// ==================高级查询相关 end========================

		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, "rcsw_zjbb_bbsh.do");

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getBbshList(model);

		// 构建结果集
		String spHtml = service
				.createBbshSearchHTML(rsModel, model, rsArrList, user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 假期留校结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbjgSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZjbbForm myForm = (RcswZjbbForm) form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		model.setColList(new String[] { "id", "xh", "xm", "nj", "yjzxsj",
				"sqsj", "sqjg" });
		
		init.initBbjgSearch(rForm, model, request, user);

		// 结果集显示字段

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);

		model.setSearchModel(searchModel);

		// ==================高级查询相关 end========================

		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user,model.getPath());

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getBbjgList(model);

		// 构建结果集
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 假期留校结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswZjbbForm myForm = (RcswZjbbForm) form;
		
		User user=getUser(request);

		BasicModel model = new BasicModel();
		
		model.setUser(user);

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		init.initBbsqSave(model, request);
		
		
		
		flag = service.saveInfo(model);
		
		if(flag){
			
			HashMap<String,String>valueMap=model.getValueMap();
			
			myForm.setXmid(valueMap.get("xmid"));
			
			HashMap<String,String>zjbbMap=service.getBbszInfo(myForm);
			
			String id=model.getValueMap().get("id");
			if(!"no".equalsIgnoreCase(zjbbMap.get("lcid"))){
				
				flag = service.saveZjbbShb(id,valueMap.get("xmid"));
			
			}
			
			
			/*
			* 待办工作（业务ID，申请人,审批岗位，审核人跳转URL，申请人跳转URL，业务功能，项目名称）
			* 2013-1-10 qph
			*/
			String xh = valueMap.get("xh");
			String xmid = valueMap.get("xmid");
			
			try {
				String[] spgw = service.getSpgwByXmid(xmid);
				
				if (null != spgw && spgw.length > 0){
					RcswZjbbDAO zjbbDao = new RcswZjbbDAO();
					myForm.setXmid(xmid);
					HashMap<String,String> zjszInfo = zjbbDao.getBbszInfo(myForm);
					
					Job job = Job.getJobInstance(id, xh, spgw[0], 
							"rcsw_zjbb.do?method=bbshManage&xmid="+xmid+"&spgw="+spgw[0], 
							"rcsw_zjbb.do?method=zjbbCk&sqid="+id,"证件补办", zjszInfo.get("zjmc"));
					MyJobsManager manager = new MyJobsImpl();
					manager.pushJob(job);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);


		return null;
	}
	
	/**
	 * 假期留校结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BasicModel basicModel=new BasicModel();
		
		RcswZjbbForm myForm = (RcswZjbbForm) form;
		
		init.initZjbbSh(basicModel, request);

		User user = getUser(request);// 用户对象
	
		// ============= end ============

		String message="";
		
		boolean flag = false;
	
		flag = service.updateShzt(myForm,basicModel,request, user);
		message = flag ? "审核成功" : "审核失败，请联系相关人员";
	
		if(flag){
			/*
			 * 推送待办
			 * 2013-1-10 qph
			 */
			String[] id = myForm.getSqid();
			String curShgw = myForm.getSpgw();
			String[] spgw = service.getSpgwByXmid(myForm.getXmid());
			int index = StringUtils.getStrIndexInArray(curShgw, spgw);
			
			for (String str : id){
				Job job = null;
				if ("tg".equals(myForm.getShzt())) {
					String nextShgw = index!=spgw.length-1 && spgw[index+1] != null ? spgw[index+1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"rcsw_zjbb.do?method=bbshManage&xmid="+myForm.getXmid()+"&spgw="+nextShgw,"证件补办");
				} else if ("th".equals(myForm.getShzt())){
					String nextShgw = index!=0 ? spgw[index-1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"rcsw_zjbb.do?method=bbshManage&xmid="+myForm.getXmid()+"&spgw="+nextShgw,"证件补办");
				} else {
					job = Job.getJobInstance(str,"证件补办");
				}
				MyJobsManager manager = new MyJobsImpl();
				manager.updateJob(job);
			}
		}
		
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
//
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 弹出审核岗位选择DIV
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showShgwDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		RcswZjbbForm myForm=(RcswZjbbForm)form;
		
		User user = getUser(request);// 用户对象
		
		String xmdm=request.getParameter("xmdm");
		
		myForm.setXmid(xmdm);
		// ============= 初始化各变量的值 ============
		
		service.showShgwDiv(myForm, user ,response);
			
		return null;
	}
	/**
	 * 
	 * @描述:(学生申请补办)
	 * @作者：cmj[工号：913]
	 * @日期：2013-8-27 上午11:38:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward bbsqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("zjbbList", service.getZjbbList());
		return mapping.findForward("bbsqAdd");
	
	}
	
}
