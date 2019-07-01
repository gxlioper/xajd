package xsgzgl.xsxx.general.xsxxgl;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjtx.HcccqjtxService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.wjcf.cfjg.CfjgService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xsxx.cxpy.CxpyService;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxcj.XsxxcjForm;
import com.zfsoft.xgxt.xsxx.xsxxcj.XsxxcjService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import common.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.ResourceUtils;
import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.cwgl.CwglService;
import xsgzgl.wjcf.cfsjwh.WjcfCfsjwhService;
import xsgzgl.xsxx.general.qzxgmdsz.QzxgmdszService;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 学生信息 - 学生信息管理
 * lt
 * 2013-1-7
 */
public class XsxxtyglAction extends BasicAction {
	

	/**
	 * 查询在校生信息
	 * @param form
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cxZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		XsxxtyglInit init = new XsxxtyglInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initZxsSearch(rForm, user, request);
		// =================== end ===================
		//===============导出表设置===================
		request.setAttribute("tableName", "view_xsxxb_zxs");
		request.setAttribute("realTable", "xsxxb");
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		request.setAttribute("xxdm", Base.xxdm);
		// =================== end ===================
		if("stu".equalsIgnoreCase(user.getUserType())){
			myForm.setXh(user.getUserName());
			request.setAttribute("sfxsgban", "bxs");
			//广西医科学生用户无打印按钮 问题处理 982 张昌路
			if(Base.xxdm.equals("10598")){
				request.removeAttribute("sfxsgban");
			}
			return ckZxsxx(mapping, myForm, request, response);
		}else{
			return mapping.findForward("cxZxsxx");
		}
		
	}
	
	/**
	 * 查询在校生结果信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxZxsxxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
	
		XsxxtyglInit init = new XsxxtyglInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initZxsSearch(rForm, user, request);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cd", "zxs");
		map.put("gnlj", "xsxx_tygl_cxzxs.do");
		// 标题
		List<HashMap<String, String>> topTr = service.getTopList(map);
		// 结果集
		ArrayList<String[]> rsArrList = service.getZxsxxList(myForm, user);
		// 构建结果集
		String spHtml =  service.createHTML(rsModel,topTr, rsArrList);
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
	 * 在校生信息增加
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward zjZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

		XsxxtyglService service = new XsxxtyglService();
		RequestForm rForm = new RequestForm();
		rForm.setMenu("zxsxx");
		rForm.setGnmk("xsxx");

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setAllList(model, rForm, request);
		// =================== end ===================
		String path = "/xsgzgl/xsxx/general/xsxxgl/"+Base.xxdm+"/zjZxsxx.jsp";
		if (validateUrlIsExists(request,path )) {
			return new ActionForward(path);
		}
		
		return mapping.findForward("zjZxsxx");
	}
	
	/**
	 * 保存在校生信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		XsxxtyglInit init = new XsxxtyglInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();
		rForm.setMenu("zxsxx");
		rForm.setGnmk("xsxx");
		myForm.setUser(user);
		String flag = request.getParameter("sfxgFlag");
		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setAllList(model, rForm, request);
		// =================== end ===================
		boolean result = true;
	
		String guid = init.bcZxsxx(myForm, request, user,flag);
		
		String message = !"".equals(guid) ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		
		String qzxg = request.getParameter("fromQzxg");
		
		//插入待办工作
		if("保存成功".equals(message)&& "true".equals(flag)){
			String xh = user.getUserName();
			HashMap<String, String> csszMap = service.getCsszjg();
			//带审核流
			if (StringUtils.isNotNull(csszMap.get("shlid")) && !"wxsh".equalsIgnoreCase(csszMap.get("shlid"))){
				 HashMap<String, String> gwmap = service.getMinSpgw();
				 String id= guid;
				 //检测是否存在
				 HashMap<String,String> map = service.getWdgz(id);
				 if (StringUtils.isNotNull(gwmap.get("spgw"))&&StringUtils.isNull(map.get("gzid"))){
						Job job = Job.getJobInstance(id, xh, gwmap.get("spgw"), 
						"general_xsxx.do?method=xgshSearch&spgw="+gwmap.get("spgw"), 
						"xsxx_general_xxxg_xgjg.do","学生信息修改", "学生信息修改");
						MyJobsManager manager = new MyJobsImpl();
						manager.pushJob(job);
					}
			}
		}
		
		//强制修改跳转
		if ("yes".equals(qzxg) && result){
			QzxgmdszService qzxgservice = new QzxgmdszService ();
			qzxgservice.xgQzxgzt(user.getUserName());
			return new ActionForward("/stuPage.jsp");
		}
		
		//获取还未修改的学生信息
		HashMap<String, String> xsxxMap = service.getZxsxxByXhCk(myForm);
		
		if ("xg".equalsIgnoreCase(request.getParameter("doType"))) {//跳转到修改页面
			
			if ("stu".equalsIgnoreCase(user.getUserType())) {
				myForm.setXh(user.getUserName());
				// =================== end ===================
				//以学工为准的字段配置
				HashMap<String, String> zdpzMap = service.getXsxxTbzdpzbStr("stu");
				request.setAttribute("zdpzstr", zdpzMap.get("zdstr"));
				request.setAttribute("xgzt", zdpzMap.get("xgzt"));
				//学生修改前的字段对应值字符串
				request.setAttribute("xsxxstr", service.getStuZdszValue(xsxxMap));
				HashMap<String, String> csszMap = service.getCsszjg();
				String kfxg = "n".equalsIgnoreCase(csszMap.get("kgzt")) ? "n" : "y";//修改状态开关
				String msg = "";
				//对不能进行修改的信息提示处理
				if ("n".equalsIgnoreCase(kfxg)) {
					msg = "学生信息修改状态已关闭！";
				} else {
					String sqzt = service.getStuSqzt(myForm.getXh());
					if (!"0".equalsIgnoreCase(sqzt)) {
						msg = "当前修改信息处于审核中，不能进行操作！";
					}
				}
				request.setAttribute("xgts", msg);
				request.setAttribute("xsxxxg", "yes");//判断关闭按钮是否显示 
				//获取当前申请修改的学生信息
				xsxxMap = service.getZxsxxByXh(myForm);
				return sqXsxxxg(mapping, myForm, request, response);
			} else {
			
				//获取单个学生信息
				//HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);
				
				//不以学工为准的字段配置
				HashMap<String, String> zdpzMap = service.getXsxxTbzdpzbStr("tea");
				request.setAttribute("zdpzstr", zdpzMap.get("zdstr"));
				request.setAttribute("xgzt", zdpzMap.get("xgzt"));
				
				return xgZxsxx(mapping, myForm, request, response);
			}
			
			
		} else {
			//跳转到增加页面
			
			//获取当前申请修改的学生信息
			xsxxMap = service.getZxsxxByXh(myForm);
			
			request.setAttribute("rs", xsxxMap);
			String path = "/xsgzgl/xsxx/general/xsxxgl/"+Base.xxdm+"/zjZxsxx.jsp";
			if (validateUrlIsExists(request, path)) {
				return new ActionForward(path); 
			}
			return mapping.findForward("zjZxsxx");
		}
	}
	
	/**
	 * 修改在校生信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xgZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		RequestForm rForm = new RequestForm();
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		User user = getUser(request);
		myForm.setUser(user);
			rForm.setMenu("zxsxx");
			rForm.setGnmk("xsxx");
			HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);
			request.setAttribute("rs", xsxxMap);
			// ============= 设置request的值 =============
			CommForm model = new CommForm();
			service.setRequestValue(rForm, request);
			service.setAllList(model, rForm, request);
			// =================== end ===================
			//以学工为准的字段配置
			HashMap<String, String> zdpzMap = service.getXsxxTbzdpzbStr("tea");
			request.setAttribute("zdpzstr", zdpzMap.get("zdstr"));
			request.setAttribute("xgzt", zdpzMap.get("xgzt"));
			//可否修改学生信息
			request.setAttribute("xgts", "");
			request.setAttribute("bcansfxs", "xs");//判断保存按钮是否显示 
			String path = "/xsgzgl/xsxx/general/xsxxgl/"+Base.xxdm+"/xgZxsxx.jsp";
		if (validateUrlIsExists(request, path)) {
			return new ActionForward(path,false);
		}
		return mapping.findForward("xgZxsxx");
	}
	
	/**
	 * 删除在校生信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward scZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(myForm, request);
		// ============= end ============

		// ==================构建前台页面====================

		boolean flag = service.deleteZxsxx(myForm, user);

		String message = flag ? "删除成功" : "删除失败，请联系相关人员";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 查看在校生学生详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ckZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		/////学生信息，其他模块跳转至详细页面，新旧版本共存暂时处理方式。判断有无新版本菜单，有菜单即跳转至新版功能///////
		try {
			HttpSession session = request.getSession();
			String xsxxZdybd =  (String)session.getAttribute("XSXX_ZDYBD");
			if(xsxxZdybd != null && xsxxZdybd.equals("1")){
				String xh = request.getParameter("xh");
				String toPage = "/xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh;
				return new ActionForward(toPage, false);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		/////////////////////////////////////////////////////////////////////////////
		
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		User user = getUser(request);
		myForm.setUser(user);
		XsxxtyglService service = new XsxxtyglService();
		HashMap<String, String> xsxxMap = service.getZxsxxByXhCk(myForm);
		//zd4被个性化用于 银行名称（2）
		if(Base.xxdm.equals("12036")&&null!=xsxxMap.get("zd4")){
			//更改显示的银行代码为银行对应名称
			xsxxMap.put("zd4", service.getYhmc(xsxxMap.get("zd4")));
		}
		request.setAttribute("rs", xsxxMap);
		//天津職業大學学生台账信息
		if("11032".equalsIgnoreCase(Base.xxdm)){
			XsxxcjService xsxxcjService=new XsxxcjService();
			XsxxcjForm xsxxcjForm=new XsxxcjForm();
			xsxxcjForm.setXh(xsxxMap.get("xh"));
			XsxxcjForm model=null;
			try {
				model=xsxxcjService.getModel(xsxxcjForm);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("xsxxcjForm", model);
		}
		
		List<HashMap<String, String>> mkList = service.getCkxsgnmkxx();
		//思政老师列表
		List<HashMap<String, String>> szxxList = service.getFdyBzrListByBjdm(xsxxMap.get("bjdm"));
		List<HashMap<String, String>> gbList = service.getGbxxList(xsxxMap.get("xh"));
		request.setAttribute("gbList", gbList);
		request.setAttribute("ckList", mkList);//查看一级模块列表
		request.setAttribute("ywlbList", service.getCkywlbList(mkList));//二级模块列表
		request.setAttribute("mksize", null!=mkList&&mkList.size()>7?"true":"false");
		
		request.setAttribute("fdyList", service.getSzxxList("fdy", szxxList));//辅导员老师信息列表
		request.setAttribute("bzrList", service.getSzxxList("bzr", szxxList));//班主任老师信息列表
		
		String path = "/xsgzgl/xsxx/general/xsxxgl/"+Base.xxdm+"/ckZxsxx.jsp";
		if (validateUrlIsExists(request, path)) {
			return new ActionForward(path,false);
		}
		return mapping.findForward("ckZxsxx");
	}
	
	/**
	 * 查询非在校生信息
	 * @param form
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cxFzxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		XsxxtyglInit init = new XsxxtyglInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initFzxsSearch(rForm, user, request);
		// =================== end ===================
		//===============导出表设置===================
		request.setAttribute("tableName", "view_xsxxb_fzxs");
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		
		return mapping.findForward("cxFzxsxx");
	}
	
	/**
	 * 查询非在校生结果信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxFzxsxxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
	
		XsxxtyglInit init = new XsxxtyglInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initFzxsSearch(rForm, user, request);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cd", "zxs");
		map.put("gnlj", "xsxx_tygl_cxzxs.do");
		// 标题
		List<HashMap<String, String>> topTr = service.getTopList(map);
		// 结果集
		ArrayList<String[]> rsArrList = service.getFzxsxxList(myForm, user);
		// 构建结果集
		String spHtml =  service.createHTML(rsModel,topTr, rsArrList);
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
	 * 
	  * validateUrlIsExists 方法
	  * <p>方法说明:验证URL路径是否存在，存在返回TRUE,反之FALSE</p> 
	  * @param jspUrl
	  * @return
	  * @return boolean
	  * @author litao
	  * @date 2011-6-9
	 */
	public boolean validateUrlIsExists(HttpServletRequest request, String jspUrl) {
		File tempFilePath  = new File(request.getRealPath(jspUrl));
		if (!tempFilePath.exists()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 检查学号是否存在 存在返回1,反之0
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String jcXhsfcz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		// ============= end ============

		// ==================构建前台页面====================
		String message = service.chkStuIsExists(myForm.getXh());

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 显示各业务模块功能列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String xsYwmklb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsywxxService service = new XsywxxService();
		// ============= end ============

		// ==================构建前台页面====================
		String message = service.getYwmklbHtml(myForm.getGnmk(), myForm.getXh());

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	/**
	 * 学生密码初始化
	 * 
	 * @date 2013-01-11
	 * @author CMJ
	 */
	public ActionForward yhmmCsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
				
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
	 * 学生申请修改个人信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sqXsxxxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxxtyglService service = new XsxxtyglService();
		RequestForm rForm = new RequestForm();
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		User user = getUser(request);// 用户对象
		myForm.setUser(user);
		if(!"stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块仅允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg); 
			return new ActionForward("/yhInfo.do", false);
		}
		myForm.setXh(user.getUserName());
		rForm.setMenu("zxsxx");
		rForm.setGnmk("xsxx");
		
		//已申请修改的学生信息
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);
		xsxxMap.put("jgshen", xsxxMap.get("jg") != null ? xsxxMap.get("jg").substring(0, 2)+"0000" : "");
		xsxxMap.put("jgshi", xsxxMap.get("jg") != null && !"00".equalsIgnoreCase(xsxxMap.get("jg").substring(3, 4)) ? xsxxMap.get("jg").substring(0, 4)+"00" : "");
		xsxxMap.put("jgxian", xsxxMap.get("jg") != null && !"00".equalsIgnoreCase(xsxxMap.get("jg").substring(5, 6)) ? xsxxMap.get("jg").substring(0, 6) : "");
		
		xsxxMap.put("hkshen", xsxxMap.get("hkszd") != null ? xsxxMap.get("hkszd").substring(0, 2)+"0000" : "");
		xsxxMap.put("hkshi", xsxxMap.get("hkszd") != null && !"00".equalsIgnoreCase(xsxxMap.get("hkszd").substring(3, 4)) ? xsxxMap.get("hkszd").substring(0, 4)+"00" : "");
		xsxxMap.put("hkxian", xsxxMap.get("hkszd") != null && !"00".equalsIgnoreCase(xsxxMap.get("hkszd").substring(5, 6)) ? xsxxMap.get("hkszd").substring(0, 6) : "");
		
		xsxxMap.put("syds", xsxxMap.get("syd") != null ? xsxxMap.get("syd").substring(0, 2)+"0000" : "");
		xsxxMap.put("sydshi", xsxxMap.get("syd") != null && !"00".equalsIgnoreCase(xsxxMap.get("syd").substring(3, 4)) ? xsxxMap.get("syd").substring(0, 4)+"00" : "");
		xsxxMap.put("sydx", xsxxMap.get("syd") != null && !"00".equalsIgnoreCase(xsxxMap.get("syd").substring(5, 6)) ? xsxxMap.get("syd").substring(0, 6) : "");
		
		request.setAttribute("rs", xsxxMap);
		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setAllList(model, rForm, request);
		// =================== end ===================
		//以学工为准的字段配置
		HashMap<String, String> zdpzMap = service.getXsxxTbzdpzbStr("stu");
		request.setAttribute("zdpzstr", zdpzMap.get("zdstr"));
		request.setAttribute("xgzt", zdpzMap.get("xgzt"));
		
		//当前学生信息Map   还未修改的学生信息
		HashMap<String, String> dqXsxxMap = service.getZxsxxByXhCk(myForm);
		
		//学生修改前的字段对应值字符串
		request.setAttribute("xsxxstr", service.getStuZdszValue(dqXsxxMap));
		HashMap<String, String> csszMap = service.getCsszjg();
		String kfxg = "n".equalsIgnoreCase(csszMap.get("kgzt")) ? "n" : "y";//修改状态开关
		HashMap<String, String> thjgMap = service.getXsthsq(myForm.getXh());
		boolean sfth = null!=thjgMap&&thjgMap.size()>0?true:false;//是否存在退回数据
		String msg = "";
		String bcansfxs = "xs";
		
		String zpsfcz = service.checkxszpSfcz(myForm.getXh());
		
		//对不能进行修改的信息提示处理
		if ("n".equalsIgnoreCase(kfxg)&&!sfth) {
			msg = "学生信息修改状态已关闭！";
			bcansfxs="";
		} else {
			String sqzt = service.getStuSqzt(myForm.getXh());
			if (!"0".equalsIgnoreCase(sqzt)) {
				msg = "当前修改信息处于审核中，不能进行操作！";
				bcansfxs="";
			}
		}
		List<HashMap<String,String>> shxxList = service.getXgshxx(user);//审核信息list
		if(null!=shxxList&&shxxList.size()>0){
			msg = "你的修改申请被退回，请重新填写！";
		}
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("xgts", msg);
		request.setAttribute("xsxxxg", "yes");//判断关闭按钮是否显示 
		request.setAttribute("bcansfxs", bcansfxs);//判断保存按钮是否显示 
		request.setAttribute("shxxList", shxxList);
		String url = "/xsgzgl/xsxx/general/xsxxgl/"+Base.xxdm+"/xgZxsxx.jsp";
		if (validateUrlIsExists(request, url)) {
			return new ActionForward(url); //mapping.findForward("xgZxsxx" + Base.xxdm);
		}
		
		return mapping.findForward("xgZxsxx");
	}
	
	/** 
	 * @描述: 学生报到单打印
	 * @作者：HongLin
	 * @日期：2013-5-17 下午03:35:43
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
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		XsxxtyglForm model = (XsxxtyglForm) form;
		User user = getUser(request);
		model.setUser(user);
		if("stu".equals(user.getUserType()) && !user.getUserName().equals(model.getXh())){
			model.setXh("");
		}
		XsxxtyglService service = new XsxxtyglService();
		
		if (StringUtil.isNull(user.getUserName())) {
			request.setAttribute("message", "请先登陆系统再进行访问！");
			return mapping.findForward("error");
		}
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			HashMap<String,String> xsjbxx = service.getZxsxxByXh(model);
			//分解身份证号begin
			String xssfzh = xsjbxx.get("sfzh")==null || "".equals("sfzh")?"":xsjbxx.get("sfzh");
			int sylen= 18-xssfzh.length();
			for (int i=0;i<xssfzh.length();i++){
				xsjbxx.put("sfzh"+i, xssfzh.charAt(i)+"");
			}
			for (int i=0;i<sylen;i++){
				xsjbxx.put("sfzh"+(xssfzh.length()+i), "");
			}
			//分解身份证号end
			request.setAttribute("jbxx", xsjbxx);
			
			//获取学生住宿信息
			CwglService zsxxService = new CwglService();
			HashMap<String,String> zsxx =zsxxService.getCwForXh(model.getXh());
			request.setAttribute("zsxx", zsxx);
			
			// 家庭成员信息
			List<HashMap<String, String>> jtcyxxList = service
					.getJtcyxxXsList(model.getXh());
			request.setAttribute("jtcyxxList", jtcyxxList);
			
			String qfje = service.getQfqk(model.getXh());
			
			request.setAttribute("qfje", qfje); 
			
		}
        request.setAttribute("mkxxForm", model);
        request.setAttribute("xxmc", Base.xxmc);
		//String forward = "/xsgzgl/xsxx/general/xsxxgl/print/xsdjb_default.jsp";
        //广东省轻工业技师学院个性化
        if("9800003".equals(Base.xxdm)){
        	//操行评语数据
            HashMap<String,String> xscxpyxx = service.getCxpyxxByXh(model);
            request.setAttribute("xscxpyxx", xscxpyxx);
        }
        String forward = "";
        List<HashMap<String,String>>  cjList = service.getCjList(model.getXh());
        request.setAttribute("cjList", cjList);
		//湖北经济学院个性化&武汉职业技术学院
		if (validateUrlIsExists(request, "/xsgzgl/xsxx/general/xsxxgl/print/xsdjb_"+Base.xxdm+".jsp")) {
			forward = "/xsgzgl/xsxx/general/xsxxgl/print/xsdjb_"+Base.xxdm+".jsp";
		}
		return new ActionForward(forward, false);
	}
	
	/** 
	 * @描述: 学生报到单打印（批量）
	 * @作者：cmj
	 * @日期：2013-7-29 下午03:35:43
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
	public ActionForward plPrintJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] xhs=new String[]{};
    	String xhstr = request.getParameter("xhstr");		
		xhs = xhstr.split(",");
		
		String newxhstr = "";
		for(int i=1;i < xhs.length;i++){
			newxhstr +=xhs[i]+",";
		}
		request.setAttribute("xhstr", newxhstr);
		String xh=xhs[0];
		XsxxtyglForm model = (XsxxtyglForm) form;
		model.setXh(xh);
		User user = getUser(request);
		model.setUser(user);
		XsxxtyglService service = new XsxxtyglService();
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			HashMap<String,String> xsjbxx = service.getZxsxxByXh(model);
			//分解身份证号begin
			String xssfzh = xsjbxx.get("sfzh")==null || "".equals("sfzh")?"":xsjbxx.get("sfzh");
			int sylen= 18-xssfzh.length();
			for (int i=0;i<xssfzh.length();i++){
				xsjbxx.put("sfzh"+i, xssfzh.charAt(i)+"");
			}
			for (int i=0;i<sylen;i++){
				xsjbxx.put("sfzh"+(xssfzh.length()+i), "");
			}
			//分解身份证号end
			request.setAttribute("jbxx", xsjbxx);
			
			//获取学生住宿信息
			CwglService zsxxService = new CwglService();
			HashMap<String,String> zsxx =zsxxService.getCwForXh(model.getXh());
			request.setAttribute("zsxx", zsxx);
		}
        request.setAttribute("mkxxForm", model);
        request.setAttribute("xxmc", Base.xxmc);
		//String forward = "/xsgzgl/xsxx/general/xsxxgl/print/xsdjb_default.jsp";
        //操行评语数据
        HashMap<String,String> xscxpyxx = service.getCxpyxxByXh(model);
        request.setAttribute("xscxpyxx", xscxpyxx);
        String forward = "";
		//湖北经济学院个性化&武汉职业技术学院
		if (validateUrlIsExists(request, "/xsgzgl/xsxx/general/xsxxgl/print/xsdjb_"+Base.xxdm+".jsp")) {
			forward = "/xsgzgl/xsxx/general/xsxxgl/print/xsdjb_"+Base.xxdm+".jsp";
		}
		return new ActionForward(forward, false);
	}
	
	
	/** 
	 * @描述: 广西医科大学毕业生清单打印
	 * @作者：cmj
	 * @日期：2013-7-23 下午03:35:43
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
	
	public ActionForward printBysqd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		XsywxxService xsywxxService=new XsywxxService();
		HashMap<String, String> xsxxMap = service.getZxsxxByXhCk(myForm);
		request.setAttribute("rs", xsxxMap);
		WjcfCfsjwhService wjcfService = new WjcfCfsjwhService();
		List<HashMap<String, Object>> wjlist = new ArrayList<HashMap<String, Object>>();//违纪业务数据
		List<HashMap<String, Object>> pjlist = new ArrayList<HashMap<String, Object>>();//评奖业务数据
		wjlist = wjcfService.getStuWjcfList(myForm.getXh());
		pjlist = xsywxxService.getStuPjList(myForm.getXh());
		//获取违纪业务数据
		HashMap<String, Object> map=wjlist.get(0);
		List<String[]> wj=(List<String[]>) map.get("cxjg");
		if(wj.size()==1){//如果list大小为1，说明只有表头数据，
			wj=null;
		}
		if(pjlist.size()==3){
			//获取奖学金业务数据
			map=pjlist.get(0);
			List<String[]> jxj=(List<String[]>) map.get("cxjg");
			if(jxj.size()==1){//如果list大小为1，说明只有表头数据，
				jxj=null;
			}
			//获取荣誉称号业务数据
			map=pjlist.get(1);
			List<String[]> rych=(List<String[]>) map.get("cxjg");
			if(rych.size()==1){//如果list大小为1，说明只有表头数据，
				rych=null;
			}
			//获取综测分业务数据
			map=pjlist.get(2);
			List<String[]> zcf=(List<String[]>) map.get("cxjg");
			if(zcf.size()==1){//如果list大小为1，说明只有表头数据，
				zcf=null;
			}
			request.setAttribute("wjlist", wj);
			request.setAttribute("jxj", jxj);
			request.setAttribute("rych", rych);
			request.setAttribute("zcf", zcf);
		}
		return mapping.findForward("printBysqd");
		
	}
	
	
	/**
	 * 
	 * @描述: 展示学生密码初始化页面
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-28 上午08:58:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward showCshmm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("showCshmm");
	}
	
	/**
	 * 在校生信息自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward zxsxxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		XsxxtyglForm model = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getZxsxxExportDataList(model, user);
		
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
	 * 非在校生信息自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward fzxsxxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm model = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getFzxsxxExportDataList(model, user);
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
	//团员
	public ActionForward getPrintTY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm model = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getTyExportDataList(model, user);
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
	//团干部
	public ActionForward getPrintTGB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm model = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getTgbExportDataList(model, user);
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
	 * 下载学籍卡
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public ActionForward getXjkWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		
		File wordFile = getWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	/**
	 * 
	 * @描述:团组织关系打印
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-24 下午05:08:09
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
	public ActionForward printZzjsx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		File wordFile = getZzjsxWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	public ActionForward printZzjsxZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getZzjsxWord(myForm, request);;
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	
	/*
	 * 获取word
	 */
	private File getWord(XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		String xh = myForm.getXh();
		User user = getUser(request);
		myForm.setUser(user);
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//学生基本信息
		List<String> kcList=service.getKcList(myForm);//学生课程list
		List<String> xnList=service.getXnList(myForm);//学生学年list
		List<HashMap<String, String>> cjList=service.getCjList(myForm,xnList,kcList);//学生成绩list
		HashMap<String, String> cxpyMap=service.getCxpyxxByXh(myForm);
		HashMap<String, String> zpMap=service.getZpMap(myForm,request,"zp");
		HashMap<String, String> xszpMap=service.getZpMap(myForm,request,"xszp");
		data.putAll(xsxxMap);
		data.put("xxmc", Base.xxmc);
		data.put("cjList", cjList);
		data.putAll(cxpyMap);
		data.putAll(zpMap);
		data.putAll(xszpMap);
		data.put("blankList", service.getBlankList(22 - cjList.size()));
		String fileName = "xsxjk.xml";
		if("11527".equals(Base.xxdm)){
			fileName="xsxjk_11527.xml";
		}
		return FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx",fileName,xh+"-"+xsxxMap.get("xm"));
	}
	/**
	 * 
	 * @描述: 下载ZIP
	 * @作者：cmj [工号：913]
	 * @日期：2013-5-21 下午05:27:29
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
	public ActionForward getXjkZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述:(下载通用版本的学籍卡)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-9-9 下午02:47:22
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
	public ActionForward getXjk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		User user = getUser(request);
		if("stu".equals(user.getUserType())&&!user.getUserName().equals(myForm.getXh())){
			File file =FreeMarkerUtil.getWordFile(null,"classpath://templates//xsxx","error.xml","error");
			FileUtil.outputWord(response, file);
			return null;
		}
		Map<String,String> authMap = user.getAuthMap();
		
		//验证数据权限
		if(!"stu".equals(user.getUserType())&&(!((authMap.containsKey("xsxx_xsxxgl_cxzxs.do")&&service.checkDownLoadAuth(myForm, user))
				|| (authMap.containsKey("xsxx_xsxxgl_cxfzxs.do")&&!service.checkDownLoadAuth(myForm, user))))){
			return null;
		}
		File wordFile = getXjkWord(myForm,request);
		
		if(StringUtils.isEqual(Base.xxdm, "12867")){
			outputFile(response, wordFile, "application/vnd.ms-word");
		}else {
			FileUtil.outputWord(response, wordFile);
		}
		return null;
	}
	
	public static void outputFile(HttpServletResponse response, File file,
			String type) throws IOException {
		String fileName = new String(file.getName().getBytes("UTF-8"),"ISO-8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName);
		response.setContentType(type);
		OutputStream out = null;
		InputStream in = null;
		out = response.getOutputStream();
		in = new FileInputStream(file);
		FileUtil.outputFileStream(in, out);

	}
	
	/**
	 * 
	 * @描述:(下载通用版本的学籍卡zip)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-9-9 下午02:47:22
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
	public ActionForward getXjkZipTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getXjkWord(myForm,request);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	
		 
		 
	private File getXjkWord(XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		XsxxglService newservice = new XsxxglService();
		String xh = myForm.getXh();
		User user = getUser(request);
		String hnjdDjb = request.getParameter("hnjdDjb");
		myForm.setUser(user);
		Map<String,Object> data = new HashMap<String,Object>();
		PjjgService pjjgService = new PjjgService();
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//学生基本信息
 		List<HashMap<String, String>> pjList = pjjgService.getPjpyInfoMap(xh);
 		List<HashMap<String, String>> pjjg4line = pjjgService.getHjqkByXhMap(xh,4);//取最新4条评奖结果，不足4条补空
 		List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
 		List<HashMap<String, String>> xlshjlList = xsxxglService.getXlshjlList4line(xh);
 		
		HashMap<String, String> zpMap=service.getZpMap(myForm,request,"zp");
		String dqrq = xsxxglService.getDqrq(xh);
		String dyrq = DateTranCnDate.fomartDateToCn(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),FomartDateType.day);
		data.putAll(xsxxMap);
		data.put("dqrq", DateTranCnDate.fomartDateToCn(dqrq,FomartDateType.day));
		data.put("xxmc", Base.xxmc);
		data.put("pjjg4line", pjjg4line);
		data.put("pjList", pjList);
		data.put("jtcyxxList", jtcyxxList);
		data.put("xlshjlList", xlshjlList);
		data.putAll(zpMap);
		int size=(4 - pjList.size())<0?0:(4 - pjList.size());
		int size2=(4 - jtcyxxList.size())<=0?0:(4 - jtcyxxList.size());
		int size3=(4 - xlshjlList.size())<=0?0:(4 - xlshjlList.size());
		int size4=(14 - jtcyxxList.size())<=0?0:(14 - jtcyxxList.size()); 
		data.put("blankList", service.getBlankList(size));
		data.put("blankList2", service.getBlankList(size2));
		data.put("blankList3", service.getBlankList(size3));
		data.put("blankList4", service.getBlankList(size4));
		data.put("qfqk", service.getQfqk(xh));  //欠费情况
		if(Globals.XXDM_CDTYXY.equals(Base.xxdm)){
			data.put("cdcj",xsxxglService.getCdtyCjxxByxh(xh));
			DtxxjgService dtxxservice = new DtxxjgService();
			String rdsj = dtxxservice.getGrDtjgxx(xh, "09").get("kssj");
			String rtsj = dtxxservice.getGrDtjgxx(xh, "02").get("kssj");
			data.put("hhgrjl", HtmlUtil.wordHh(xsxxMap.get("grjl")));
			if(!StringUtil.isNull(rdsj)){
				data.put("rdtsj", DateTranCnDate.fomartDateToCn(rdsj,FomartDateType.day));
			}else{
				data.put("rdtsj", DateTranCnDate.fomartDateToCn(rtsj,FomartDateType.day));
			}
		}
		//学生照片
		XsxxService xsxxService = new XsxxService();
		InputStream is = xsxxService.getPhotoStream(xh);
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		
		if (StringUtil.isNull(photo)){
			//取默认照片 
			photo = xsxxService.getDefaultPhotoBase64(request);
		}
		
		if(photo == null){
			photo = "";
		}
		data.put("photo", photo);
		
		//获取学生住宿信息
		CwglService zsxxService = new CwglService();
		HashMap<String,String> zsxx =zsxxService.getCwForXh(xh);
		data.put("zsxx", zsxx);
		
		//家庭情况
		JtqkdcService jtqkdcService = new JtqkdcService();
		JtqkdcForm jtqkdc = jtqkdcService.getModel(xh);
		data.put("jtqkdc", jtqkdc);
		
		//获取火车乘车区间
		HashMap<String, String> hcccqj = new HcccqjtxService().getHcccqj(xh);
		data.put("hcccqj", hcccqj);
		
		//获取担任干部
		String drgb = new DwwhService().getZwForXh(xh);
		data.put("drgb", drgb);
		
		//违纪情况
		List<HashMap<String, String>> wjcfList = newservice.getWjcfList(xh);// 根据学号查询违纪处分列表
		data.put("wjcfList", wjcfList);
		File file = null;
		
		//天津滨海职业学院新生入学登记表 个性化
		if(StringUtils.isEqual(Base.xxdm, "12484"))
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_12484.xml",xh+"-"+xsxxMap.get("xm"));
		//华东师范大学新生入学登记表 个性化
		else if(StringUtils.isEqual(Base.xxdm, "10511")){
			//把个人简历和工作简历放到一起取值
			List<HashMap<String, String>> grjlShjlList = xsxxglService.getGrjlShjlList(xh);
			data.put("grjlShjlList", grjlShjlList);
			int size14=(10 - grjlShjlList.size())<=0?0:(10 - grjlShjlList.size());
			data.put("blankListhsd", service.getBlankList(size14));
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_10511.xml",xh+"-"+xsxxMap.get("xm"));
		}

		//江苏省徐州医药
		else if(Base.xxdm.equals(Globals.XXDM_JSSXZYYGDZYXX)){		
			/*家庭成员信息中职*/
			jtcyxxList = jtqkdcService.getJtcyList(xh, 5);
			data.put("jtcyxxList_70002", jtcyxxList);			
			/*家庭成员信息不足五行补五行*/
			int size6 = (5 - jtcyxxList.size())<=0?0:(5 - jtcyxxList.size()); 
			data.put("blankListJtcy", service.getBlankList(size6));			
			/*家庭成员信息高职*/
			List<HashMap<String, String>> jtcyxxgzList = xsxxglService.getJtcyxxXsList(xh);
			jtcyxxgzList = jtqkdcService.getJtcyList(xh, 7);
			data.put("jtcyxxgzList_70002", jtcyxxgzList);	
			/*家庭成员信息不足七行补七行*/
			int size13 = (7 - jtcyxxgzList.size())<=0?0:(7 - jtcyxxgzList.size()); 
			data.put("gzblankListJtcy", service.getBlankList(size13));
			
			/*学生学制*/
			String xsxz = service.getXsxz(xh);	
			/*学籍异动中职*/
			List<HashMap<String, String>> xjydList = new XjydjgService().getXsydList(myForm.getXh());
			data.put("xjydList_70002", xjydList);
			/*学籍异动信息不足2行补2行*/
			int size7 = (2 - xjydList.size())<=0?0:(2 - xjydList.size()); 
			data.put("blankListXjyd", service.getBlankList(size7));
			/*学籍异动高职*/
			List<HashMap<String, String>> xjydgzList = new XjydjgService().getXsydList(myForm.getXh());
			data.put("xjydgzList_70002", xjydgzList);
			/*学籍异动信息不足六行补六行*/
			int size14= (6 - xjydgzList.size())<=0?0:(6 - xjydgzList.size()); 
			data.put("gzblankListXjyd", service.getBlankList(size14));
			
			//奖惩中职
			XsxxtyglService xsxxtyglservice = new XsxxtyglService();
			List<HashMap<String, String>> jcrqjyy = xsxxtyglservice.getJcrqjyy(xh,5);
			data.put("jcrqjyyList_70002", jcrqjyy);
			/*奖惩信息中职不足五行补五行*/
			int size8 = (5 - jcrqjyy.size())<=0?0:(5 - jcrqjyy.size()); 
			data.put("blankListJcxx", service.getBlankList(size8));
			//奖惩高职
			List<HashMap<String, String>> gzjcrqjyy = xsxxtyglservice.getJcrqjyy(xh,8);
			data.put("gzjcrqjyy_70002", gzjcrqjyy);
			/*奖惩信息高职不足8行补8行*/
			int size15 = (8 - gzjcrqjyy.size())<=0?0:(8 - gzjcrqjyy.size()); 
			data.put("gzblankListJcxx", service.getBlankList(size15));
			
			CxpyService cxpyService = new CxpyService();
			int nj = Integer.parseInt(Base.currNd);
			if(xsxxMap.get("nj")!=null && !"".equals(xsxxMap.get("nj"))){
				nj = Integer.parseInt(xsxxMap.get("nj"));
			}
			String diXn = String.valueOf(nj) + "-" + String.valueOf(nj+1); // 第一学年
			String deXn = String.valueOf(nj+1) + "-" + String.valueOf(nj+2); // 第二学年
			String dsXn = String.valueOf(nj+2) + "-" + String.valueOf(nj+3); // 第三学年
			String fourthXn = String.valueOf(nj+3) + "-" + String.valueOf(nj+4); // 第四学年
			String fivethXn = String.valueOf(nj+4) + "-" + String.valueOf(nj+5); // 第五学年
			// 第一学年,第一学期
			HashMap<String, String> diXnYiCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), diXn ,"01");
			data.put("diyicxpy", diXnYiCxpy.get("cxpy"));
			data.put("diyibzr", diXnYiCxpy.get("bzr"));
			data.put("diyiy", diXnYiCxpy.get("y"));
			data.put("diyim", diXnYiCxpy.get("m"));
			data.put("diyid", diXnYiCxpy.get("d"));
			data.put("diyicxdjmc", diXnYiCxpy.get("cxdjmc"));
			// 第一学年，第二学期
			HashMap<String, String> diXnErCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), diXn ,"02");
			data.put("diercxpy", diXnErCxpy.get("cxpy"));
			data.put("dierbzr", diXnErCxpy.get("bzr"));
			data.put("diery", diXnErCxpy.get("y"));
			data.put("dierm", diXnErCxpy.get("m"));
			data.put("dierd", diXnErCxpy.get("d"));
			data.put("diercxdjmc", diXnErCxpy.get("cxdjmc"));
			// 第二学年 第一学期
			HashMap<String, String> deXnYiCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), deXn ,"01");
			data.put("disancxpy", deXnYiCxpy.get("cxpy"));
			data.put("disanbzr", deXnYiCxpy.get("bzr"));
			data.put("disany", deXnYiCxpy.get("y"));
			data.put("disanm", deXnYiCxpy.get("m"));
			data.put("disand", deXnYiCxpy.get("d"));
			data.put("disancxdjmc", deXnYiCxpy.get("cxdjmc"));
			//第二学年 第二学期
			HashMap<String, String> deXnerCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), deXn ,"02");
			data.put("disicxpy", deXnerCxpy.get("cxpy"));
			data.put("disibzr", deXnerCxpy.get("bzr"));
			data.put("disiy", deXnerCxpy.get("y"));
			data.put("disim", deXnerCxpy.get("m"));
			data.put("disid", deXnerCxpy.get("d"));
			data.put("disicxdjmc", deXnerCxpy.get("cxdjmc"));
			// 第三学年 第一学期
			HashMap<String, String> dsanXnYiCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), dsXn ,"01");
			data.put("diwucxpy", dsanXnYiCxpy.get("cxpy"));
			data.put("diwubzr", dsanXnYiCxpy.get("bzr"));
			data.put("diwuy", dsanXnYiCxpy.get("y"));
			data.put("diwum", dsanXnYiCxpy.get("m"));
			data.put("diwud", dsanXnYiCxpy.get("d"));
			data.put("diwucxdjmc", dsanXnYiCxpy.get("cxdjmc"));
			// 第三学年 第二学期
			HashMap<String, String> dsanXnErCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), dsXn , "02");
			data.put("diliucxpy", dsanXnErCxpy.get("cxpy"));
			data.put("diliubzr", dsanXnErCxpy.get("bzr"));
			data.put("diliuy", dsanXnErCxpy.get("y"));
			data.put("dilium", dsanXnErCxpy.get("m"));
			data.put("diliud", dsanXnErCxpy.get("d"));
			data.put("diliucxdjmc", dsanXnErCxpy.get("cxdjmc"));
			// 第四学年 第一学期
			HashMap<String, String> dsiXnYiCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), fourthXn ,"01");
			data.put("diqicxpy", dsiXnYiCxpy.get("cxpy"));
			data.put("diqibzr", dsiXnYiCxpy.get("bzr"));
			data.put("diqiy", dsiXnYiCxpy.get("y"));
			data.put("diqim", dsiXnYiCxpy.get("m"));
			data.put("diqid", dsiXnYiCxpy.get("d"));
			data.put("diqicxdjmc", dsiXnYiCxpy.get("cxdjmc"));
			// 第四学年 第二学期
			HashMap<String, String> dsiXnErCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), fourthXn , "02");
			data.put("dibacxpy", dsiXnErCxpy.get("cxpy"));
			data.put("dibabzr", dsiXnErCxpy.get("bzr"));
			data.put("dibay", dsiXnErCxpy.get("y"));
			data.put("dibam", dsiXnErCxpy.get("m"));
			data.put("dibad", dsiXnErCxpy.get("d"));
			data.put("dibacxdjmc", dsiXnErCxpy.get("cxdjmc"));
			// 第五学年 第一学期
			HashMap<String, String> dwuXnYiCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), fivethXn ,"01");
			data.put("dijiucxpy", dwuXnYiCxpy.get("cxpy"));
			data.put("dijiubzr", dwuXnYiCxpy.get("bzr"));
			data.put("dijiuy", dwuXnYiCxpy.get("y"));
			data.put("dijium", dwuXnYiCxpy.get("m"));
			data.put("dijiud", dwuXnYiCxpy.get("d"));
			data.put("dijiucxdjmc", dwuXnYiCxpy.get("cxdjmc"));
			// 第五学年 第二学期
			HashMap<String, String> dwuXnErCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), fivethXn , "02");
			data.put("dishicxpy", dwuXnErCxpy.get("cxpy"));
			data.put("dishibzr", dwuXnErCxpy.get("bzr"));
			data.put("dishiy", dwuXnErCxpy.get("y"));
			data.put("dishim", dwuXnErCxpy.get("m"));
			data.put("dishid", dwuXnErCxpy.get("d"));
			data.put("dishicxdjmc", dwuXnErCxpy.get("cxdjmc"));

			//学业成绩汇总表
	 		List<HashMap<String, String>> xycjoneList = xsxxglService.getCjoneList(xh);//学业成绩循环第一部分
	 		List<HashMap<String, String>> xycjtwoList = xsxxglService.getCjtwoList(xh);//学业成绩循环第二部分
			data.put("xycjoneList", xycjoneList);//学业成绩循环第一部分
			data.put("xycjtwoList", xycjtwoList);//学业成绩循环第二部分
			int count1 = 1;//序号1
			int count2 = 40;//序号2
			List<HashMap<String, String>> xycjAllList = new ArrayList<HashMap<String, String>>();
			int xycjSize = xycjoneList.size();
			if(xycjSize < xycjtwoList.size()){
				xycjSize = xycjtwoList.size();
			}
			HashMap<String,String> map = null;
			for (int i = 0;i<xycjSize;i++){
				map = new HashMap<String,String>();
				if(i< xycjoneList.size()){
					map.put("row1",String.valueOf(count1));//加个序号1-39
					map.put("kcmc1", xycjoneList.get(i).get("kcmc"));
					if(String.valueOf(nj).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq1", "一");
						}else{
							map.put("xq1", "二");
						}
					}
					if(String.valueOf(nj+1).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq1", "三");
						}else{
							map.put("xq1", "四");
						}
					}
					if(String.valueOf(nj+2).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq1", "五");
						}else{
							map.put("xq1", "六");
						}
					}
					if(String.valueOf(nj+3).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq1", "七");
						}else{
							map.put("xq1", "八");
						}
					}
					if(String.valueOf(nj+4).equals(xycjoneList.get(i).get("y"))){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq1", "九");
						}else{
							map.put("xq1", "十");
						}
					}
					map.put("cj1", xycjoneList.get(i).get("cj"));
					map.put("xf1", xycjoneList.get(i).get("xf"));
					count1 ++;
				}
				if(i< xycjtwoList.size()){
					map.put("row2", String.valueOf(count2));//加序号40-78
					map.put("kcmc2", xycjtwoList.get(i).get("kcmc"));
					if(String.valueOf(nj) == xycjoneList.get(i).get("y")){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq2", "一");
						}else{
							map.put("xq2", "二");
						}
					}
					if(String.valueOf(nj+1).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq2", "三");
						}else{
							map.put("xq2", "四");
						}
					}
					if(String.valueOf(nj+2).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq2", "五");
						}else{
							map.put("xq2", "六");
						}
					}
					if(String.valueOf(nj+3).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq2", "七");
						}else{
							map.put("xq2", "八");
						}
					}
					if(String.valueOf(nj+4).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq2", "九");
						}else{
							map.put("xq2", "十");
						}
					}
					map.put("cj2", xycjtwoList.get(i).get("cj"));
					map.put("xf2", xycjtwoList.get(i).get("xf"));
					count2 ++;
				}
				xycjAllList.add(map);
			}
			data.put("xycjAllList",xycjAllList);
			if(null==xycjAllList){
				for (int i = 0; i < 39; i++) {
					xycjAllList.add(new HashMap<String, String>());
				}
			}else if (xycjAllList.size()<39){
				int y = xycjAllList.size();
				for (int i = 0; i <= 39-y; i++) {
					xycjAllList.add(new HashMap<String, String>());
				}
			}
			data.put("xycjAllList",xycjAllList);
			
			
			
			if("5".equals(xsxz)){
				//徐州医药高职
				file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","Xzyygzxjk_70002.xml",xh+"-"+xsxxMap.get("xm"));
			}else{
				//徐州医药中职
				file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","Xzyyzzxjk_70002.xml",xh+"-"+xsxxMap.get("xm"));
			}
		}
		//杭州汽车高级技工学校学籍卡个性化
		else if(StringUtils.isEqual(Base.xxdm, "80152")){
			List<HashMap<String, String>> grjlList = xsxxglService.getGrjlList(xh);//杭州汽车高级技工个人简历信息循环
	 		List<HashMap<String, String>> xycjoneList = xsxxglService.getXycjoneList(xh);//学业成绩循环第一部分
	 		List<HashMap<String, String>> xycjtwoList = xsxxglService.getXycjtwoList(xh);//学业成绩循环第二部分
	 		data.put("grjlList", grjlList);//杭州汽车高级技工个人简历信息循环
			data.put("xycjoneList", xycjoneList);//学业成绩循环第一部分
			data.put("xycjtwoList", xycjtwoList);//学业成绩循环第二部分
			int size6=(3 - jtcyxxList.size())<=0?0:(3 - jtcyxxList.size());//家庭成员信息
			int size7=(3 - grjlList.size())<=0?0:(3 - grjlList.size());//杭州汽车高级技工个人简历信息循环
			data.put("blankList6", service.getBlankList(size6));//家庭成员信息
			data.put("blankList7", service.getBlankList(size7));///杭州汽车高级技工个人简历信息循环
			List<HashMap<String, String>> xycjAllList = new ArrayList<HashMap<String, String>>();
			int xycjSize = xycjoneList.size();
			if(xycjSize < xycjtwoList.size()){
				xycjSize = xycjtwoList.size();
			}
			HashMap<String,String> map = null;
			for (int i = 0;i<xycjSize;i++){
				map = new HashMap<String,String>();
				if(i< xycjoneList.size()){
					map.put("kc1", xycjoneList.get(i).get("kc1"));
					map.put("xn1xq1", xycjoneList.get(i).get("xn1xq1"));
					map.put("xn1xq2", xycjoneList.get(i).get("xn1xq2"));
					map.put("xn2xq1", xycjoneList.get(i).get("xn2xq1"));
					map.put("xn2xq2", xycjoneList.get(i).get("xn2xq2"));
					map.put("xn3xq1", xycjoneList.get(i).get("xn3xq1"));
					map.put("xn3xq2", xycjoneList.get(i).get("xn3xq2"));
				}
				if(i< xycjtwoList.size()){
					map.put("kc2", xycjtwoList.get(i).get("kc2"));
					map.put("xn4xq1", xycjtwoList.get(i).get("xn4xq1"));
					map.put("xn4xq2", xycjtwoList.get(i).get("xn4xq2"));
					map.put("xn5xq1", xycjtwoList.get(i).get("xn5xq1"));
					map.put("xn5xq2", xycjtwoList.get(i).get("xn5xq2"));
					map.put("xn6xq1", xycjtwoList.get(i).get("xn6xq1"));
					map.put("xn6xq2", xycjtwoList.get(i).get("xn6xq2"));
				}
				xycjAllList.add(map);
			}
			data.put("xycjAllList",xycjAllList);
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			data.put("csrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.month));//出生日期年月
			data.put("rxrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("rxrq"),FomartDateType.day));//入学日期年月日
			data.put("byny",DateTranCnDate.fomartDateToCn(xsxxMap.get("byny"),FomartDateType.day));//毕业日期年月日
			//身份证号分解开始
			String xssfzh =  StringUtil.isNull(xsjbxx.get("sfzh")) ? "" : xsjbxx.get("sfzh");
			for (int i = 0,j = xssfzh.length(); i < j; i++) {
				data.put("sfzh" + i, String.valueOf(xssfzh.charAt(i)));
			}	
			if(null==xycjAllList){
				for (int i = 0; i < 10; i++) {
					xycjAllList.add(new HashMap<String, String>());
				}
			}else if (xycjAllList.size()<10){
				int y = xycjAllList.size();
				for (int i = 0; i <= 10-y; i++) {
					xycjAllList.add(new HashMap<String, String>());
				}
			}
			data.put("xycjAllList",xycjAllList);
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","hzqcgjjgxxxjk_80152.xml",xh+"-"+xsxxMap.get("xm"));
		}
		//南通职业技术学院（南通农业职业技术学院）
		else if(StringUtils.isEqual(Base.xxdm, "12684")){
			data.put("csrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.day));//出生日期年月
			data.put("rxrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("rxrq"),FomartDateType.day));//入学日期年月日
			
			//家庭成员信息
			List<HashMap<String, String>> jtcyxxList1 = new ArrayList<HashMap<String, String>>();
			HashMap<String,String> map = null;
			int jtcyxxsize = 4;
			if(jtcyxxList.size() > jtcyxxsize){
				for (int i = 0;i < jtcyxxsize;i++){
					map = new HashMap<String,String>();
					map.put("jtcygxmc", jtcyxxList.get(i).get("jtcygxmc"));
					map.put("cyxm", jtcyxxList.get(i).get("cyxm"));
					map.put("ylzd4", jtcyxxList.get(i).get("ylzd4"));
					map.put("cyxxdw", jtcyxxList.get(i).get("cyxxdw"));
					map.put("cyzy", jtcyxxList.get(i).get("cyzy"));
					map.put("cyzzmm", jtcyxxList.get(i).get("cyzzmm"));
					map.put("cynsr", jtcyxxList.get(i).get("cynsr"));
					jtcyxxList1.add(map);
				}
				data.put("jtcyxxList1",jtcyxxList1);
			} else {
				data.put("jtcyxxList1", jtcyxxList);
			}

			int size1=(3 - jtcyxxList.size())<=0?0:(3 - jtcyxxList.size());
			data.put("blankList1", service.getBlankList(size1));//家庭成员信息
		    //==操行等第及评语begin==
			List<HashMap<String, String>> cxdtList1 = new ArrayList<HashMap<String, String>>();
			HashMap<String,String> cxdtMap = null;
			List<HashMap<String, String>> cxdtList = xsxxglService.getCxdt(xh);
			for (int i = 0;i < cxdtList.size();i++){
				cxdtMap = new HashMap<String,String>();
				cxdtMap.put("xn", cxdtList.get(i).get("xn"));
				cxdtMap.put("xqmc", cxdtList.get(i).get("xqmc"));
				cxdtMap.put("cxdjmc", cxdtList.get(i).get("cxdjmc"));
				cxdtMap.put("cxpy", HtmlUtil.xmlZy(cxdtList.get(i).get("cxpy")));
				cxdtList1.add(cxdtMap);
			}
			data.put("cxdtList", cxdtList1);
			int size5=(10 - cxdtList.size())<=0?0:(10 - cxdtList.size());
			data.put("blankList2", service.getBlankList(size5));
			//==操行等第及评语end==
			//==获奖情况begin==
			List<HashMap<String, String>> hjList = xsxxglService.gethjqk(xh);
			data.put("hjList", hjList);
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","ntzyjsxy_12684.xml",xh+"-"+xsxxMap.get("xm"));
		}
		//上海电机学院新生入学登记表 个性化
		else if(StringUtils.isEqual(Base.xxdm, "11458")){
			// 入学日期
			String rxrq = xsxxMap.get("rxrq");
			if(rxrq != null && rxrq.contains("-")){
				rxrq = rxrq.substring(0, rxrq.lastIndexOf("-")).replace("-", "");
			}
			data.put("rxrq_11458", rxrq);
			// 出生日期
			String csrq = xsxxMap.get("csrq");
			if(csrq != null){
				csrq = csrq.replaceAll("-", "");
			}
			data.put("csrq_11458", csrq);
			// 思政老师列表
			XsxxtyglService xsxxtyglService = new XsxxtyglService();
			List<HashMap<String, String>> szxxList = xsxxtyglService.getFdyBzrListByBjdm((String) xsxxMap.get("bjdm"));
			HashMap<String, String> fdyBzrOne = new HashMap<String, String>();
			for (HashMap<String, String> szxxMap : szxxList) {
				fdyBzrOne = szxxMap;
			}
//			// 辅导员老师信息列表
//			List<HashMap<String, String>> fdyList = xsxxtyglService.getSzxxList("fdy", szxxList);
//			// 班主任老师信息列表
//			List<HashMap<String, String>> bzrList = xsxxtyglService.getSzxxList("bzr", szxxList);
//			for (HashMap<String, String> fdyMap : fdyList) {
//				fdyBzrOne = fdyMap;
//			}
//			for (HashMap<String, String> bzrMap : bzrList) {
//				fdyBzrOne = bzrMap;
//			}
			data.put("fdyBzrOne", fdyBzrOne);
			
			//家庭成员信息
			List<HashMap<String, String>> jtcyxxList1 = new ArrayList<HashMap<String, String>>();
			HashMap<String,String> map = null;
			int jtcyxxsize = 2;
			if(jtcyxxList.size() > jtcyxxsize){
				for (int i = 0;i < jtcyxxsize;i++){
					map = new HashMap<String,String>();
					map.put("jtcygxmc", jtcyxxList.get(i).get("jtcygxmc"));
					map.put("cyxm", jtcyxxList.get(i).get("cyxm"));
					map.put("cynl", jtcyxxList.get(i).get("cynl"));
					map.put("cyxxdw", jtcyxxList.get(i).get("cyxxdw"));
					map.put("ylzd2", jtcyxxList.get(i).get("ylzd2"));
					map.put("cyzy", jtcyxxList.get(i).get("cyzy"));
					map.put("cylxdh", jtcyxxList.get(i).get("cylxdh"));
					jtcyxxList1.add(map);
				}
				data.put("jtcyxxList1",jtcyxxList1);
			} else {
				data.put("jtcyxxList1", jtcyxxList);
			}
			
			int jtcyxxBlankSize=(jtcyxxsize - jtcyxxList.size())<=0?0:(jtcyxxsize - jtcyxxList.size());
			data.put("jtcyxxBlankList", service.getBlankList(jtcyxxBlankSize));
			int xlshjlBlankSize=(4 - xlshjlList.size())<=0?0:(4 - xlshjlList.size());
			data.put("xlshjlBlankList", service.getBlankList(xlshjlBlankSize));
			// 打印日期为当前日期
			
			data.put("dyrq", dyrq);
//			// 贷款情况 换成学生信息里的
//			String jtqkdcYlzd1 = (jtqkdc != null) ? jtqkdc.getYlzd1() : "";
//			data.put("jtqkdcYlzd1", jtqkdcYlzd1);
			
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_11458.xml",xh+"-"+xsxxMap.get("xm"));
		}
		//北京林业大学本科生基本信息登记表 个性化
		else if(StringUtils.isEqual(Base.xxdm, "10022")){
			ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
			//父母亲信息
			for (HashMap<String, String> jtcyxxMap : jtcyxxList) {
				String jtcygxmc = jtcyxxMap.get("jtcygxmc"); // 与本人关系
				String jtcyxm = jtcyxxMap.get("cyxm"); // 姓名
				String zzmmmc = jtcyxxMap.get("zzmmmc"); //政治面貌
				String cynl = jtcyxxMap.get("cynl"); //年龄
				String lxdh = jtcyxxMap.get("cylxdh"); //年龄
				String zy = jtcyxxMap.get("cyzy"); //职业
				String gzdw = jtcyxxMap.get("cyxxdw"); // 工作单位及地址
				if("父亲".equals(jtcygxmc)){
					data.put("fqxm", jtcyxm);
					data.put("fqgzdw", gzdw);
					data.put("fqzy", zy);
					data.put("fqlxdh", lxdh);
				}else if("母亲".equals(jtcygxmc)){
					data.put("mqxm", jtcyxm);
					data.put("mqgzdw", gzdw);
					data.put("mqzy", zy);
					data.put("mqlxdh", lxdh);
				}else {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("cyxm", jtcyxm);
					map.put("jtcygxmc", jtcygxmc);
					map.put("cynl", cynl);
					if ("中国共产党预备党员".equals(zzmmmc)) {
						map.put("zzmmmc", "中共预备党员");
					}else if ("中国共产党党员".equals(zzmmmc)) {
						map.put("zzmmmc", "中共党员");
					}else if ("中国共产主义青年团团员".equals(zzmmmc)) {
						map.put("zzmmmc", "共青团员");
					}else {
						map.put("zzmmmc", zzmmmc);
					}
					map.put("cygzdw", gzdw);
					arrayList.add(map);
				}
			}
			// 加载学生家庭基本信息
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(xh);
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
			if (null == jtqkmodel) {
				jtqkmodel = new JtqkdcForm();
			}
			data.put("jtqk", jtqkmodel);
			data.put("photo", photo);
			data.put("jtqtcylist", arrayList);
			int size1=(3 - arrayList.size())<=0?0:(3 - arrayList.size()); 
			data.put("cyblankList", service.getBlankList(size1));
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","bjly_10022.xml",xh+"-"+xsxxMap.get("xm"));
		}
		//浙江大学新生入学登记表
		else if (StringUtils.isEqual(Base.xxdm, "10335")){
			//本人学习和工作简历
			List<HashMap<String, String>> xxhgzjlList = xsxxglService.getXxhgzjlList(xh);
			data.put("xxhgzjlList", xxhgzjlList);
			int size1=(3 - xxhgzjlList.size())<=0?0:(3 - xxhgzjlList.size());
			data.put("blankList1", service.getBlankList(size1));
			//家庭主要成员和主要社会关系
			int size7=(3 - jtcyxxList.size())<=0?0:(3 - jtcyxxList.size()); 
			data.put("blankList2", service.getBlankList(size7));
			data.put("byny",DateTranCnDate.fomartDateToCn(xsxxMap.get("byny"),FomartDateType.day));//毕业日期年月日
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xsrxdjb_10335.xml",xh+"-"+xsxxMap.get("xm"));}
		//浙江旅游职业学院
		else if (StringUtils.isEqual(Base.xxdm, "12867")){
			//家庭主要成员和主要社会关系
			int size7=(3 - jtcyxxList.size())<=0?0:(3 - jtcyxxList.size()); 
			data.put("blankList2", service.getBlankList(size7));
			//获得辅导员信息
			HashMap<String, String> fdyxx = service.getFdyxx(xh);
			data.put("fdyxx", fdyxx);
			//获得班主任信息
			HashMap<String,	String> bzrxx = service.getBzrxx(xh);
			data.put("bzrxx", bzrxx);
			//本人学习和工作简历
			List<HashMap<String, String>> xxhgzjlList = xsxxglService.getXxhgzjlList(xh);
			data.put("xxhgzjlList", xxhgzjlList);
			int size1=(3 - xxhgzjlList.size())<=0?0:(3 - xxhgzjlList.size());
			data.put("blankList1", service.getBlankList(size1));
			//家庭经济困难认定
			HashMap<String,	String> knrd = service.getKnrd(xh);
			String sfpks ="否";
			if(null!=knrd.get("xh")&& !"家庭经济不困难".equals(knrd.get("dcmc"))){
				sfpks="是";
			}
			data.put("knrd", knrd);
			data.put("sfpks", sfpks);
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","zjlyzyxy_xsdjb_12867.xml",xh+"-"+xsxxMap.get("xm"));
		}
		else if(StringUtils.isEqual(Base.xxdm, "11660"))
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_11660.xml",xh+"-"+xsxxMap.get("xm"));
		else if(StringUtils.isEqual(Base.xxdm, "13033")&&(null!=hnjdDjb&&hnjdDjb.equals("djb"))){
			
			
			List<HashMap<String, String>> xjydList = new XjydjgService().getXsydList(myForm.getXh());// 根据学号查询学籍异动列表
			//特殊处理
			for (int i = 1; i <= 3; i++) {
				String key = "jtcy" + i;
				if(i <= jtcyxxList.size()){
					data.put(key, jtcyxxList.get(i - 1));
				}else{
					data.put(key, new HashMap<String, String>());
				}
			}
			Map<String,String> map=null;
			
			for (int i = 1; i <= 5; i++) {
				String key = "xjyd" + i;
				if(i <= xjydList.size()){
					map=xjydList.get(i - 1);
					map.put("bdyy", map.get("ydqxjlbmc")+" -> "+map.get("ydlbmc")); //变动原因
					data.put(key,map);
					
				}else{
					data.put(key, new HashMap<String, String>());
				}
			}
			
			for (int i = 1; i <= 6; i++) {
				String key = "xxjl" + i;
				if(i <= xlshjlList.size()){
					data.put(key, xlshjlList.get(i - 1));
				}else{
					data.put(key, new HashMap<String, String>());
				}
			}
			
			for (int i = 1; i <= 4; i++) {
				String key = "pj" + i;
				if(i <= pjList.size()){
					pjList.get(i - 1).put("xxmc", Base.xxmc);
					data.put(key, pjList.get(i - 1));
				}else{
					data.put(key, new HashMap<String, String>());
				}
			}
			
			for (int i = 1; i <= 4; i++) {
				String key = "wjcf" + i;
				if(i <= wjcfList.size()){
					wjcfList.get(i - 1).put("xxmc", Base.xxmc);
					data.put(key, wjcfList.get(i - 1));
				}else{
					data.put(key, new HashMap<String, String>());
				}
			}
			
			//特殊处理
			data.put("xjydList", xjydList);
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_13033.xml",xh+"-"+xsxxMap.get("xm"));
		}else if(StringUtils.isEqual(Base.xxdm, "10653")) {
			//特殊处理
			int size5=(3 - jtcyxxList.size())<=0?0:(3 - jtcyxxList.size());
			data.put("blankList5", service.getBlankList(size5));
			String sfdb = xsxxglService.getSfdb(xh);
			data.put("sfdb", sfdb == null?"":sfdb);
			data.put("kslb", xsxxMap.get("kslbmc"));
			data.put("csrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.day));
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_10653.xml",xh+"-"+xsxxMap.get("xm"));
		}
		else if(StringUtils.isEqual(Base.xxdm, "50394")){
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xsdjb_50394.xml",xh+"-"+xsxxMap.get("xm"));
		}else if(StringUtils.isEqual(Base.xxdm, "13943")){
			//广州铁路职业技术学院
			List<HashMap<String, String>> xjydList = new XjydjgService().getXsydList(myForm.getXh());// 根据学号查询学籍异动列表
			jtcyxxList = jtqkdcService.getJtcyList(xh, 6);
			pjjgService.addBlankList(jtcyxxList, 6 - jtcyxxList.size());
			data.put("jtcyxxList_13943", jtcyxxList);
			//奖惩记录中的奖需合并校外获奖情况
			pjList.addAll(newservice.getXsxwhjqkList(xh));
			data.put("pjListSize_13943", pjList.size());
			pjjgService.addBlankList(pjList, 1 - pjList.size());
			data.put("pjList_13943", pjList);
			data.put("wjcfListSize_13943", wjcfList.size());
			pjjgService.addBlankList(wjcfList, 1 - wjcfList.size());
			data.put("wjcfList_13943", wjcfList);
			data.put("xjydListSize_13943", xjydList.size());
			pjjgService.addBlankList(xjydList, 1 - xjydList.size());
			data.put("xjydList_13943", xjydList);
			// ======== 获取操行评语信息 begin ========
			CxpyService cxpyService = new CxpyService();
			int nj = Integer.parseInt(Base.currNd);
			if(xsxxMap.get("nj")!=null && !"".equals(xsxxMap.get("nj"))){
				nj = Integer.parseInt(xsxxMap.get("nj"));
			}
			String diXn = String.valueOf(nj) + "-" + String.valueOf(nj+1); // 第一学年
			String deXn = String.valueOf(nj+1) + "-" + String.valueOf(nj+2); // 第二学年
			String dsXn = String.valueOf(nj+2) + "-" + String.valueOf(nj+3); // 第三学年
			String fourthXn = String.valueOf(nj+3) + "-" + String.valueOf(nj+4); // 第四学年
			// 第一学年
			HashMap<String, String> diXnCxpy = cxpyService.getCxpyByXhXn(myForm.getXh(), diXn);
			if(!"null".equals(diXnCxpy)){
				String diXn1 = diXnCxpy.get("xn");
				if(!"".equals(diXn1)&&StringUtils.isNotNull(diXn1)){
					String[] xnArr1= diXn1.split("-");
					data.put("diXn", xnArr1[1]);
				}else{
					data.put("diXn", "");
				}
			}else{
				data.put("diXn", "");
			}
			diXnCxpy.put("xnmc", "第一学年");
//			data.put("diXnCxpy", diXnCxpy);
			
			// 第二学年
			HashMap<String, String> deXnCxpy = cxpyService.getCxpyByXhXn(myForm.getXh(), deXn);
			if(!"".equals(deXnCxpy)){
				String deXn1 = deXnCxpy.get("xn");
				if(!"".equals(deXn1)&&StringUtils.isNotNull(deXn1)){
					String[] xnArr2= deXn1.split("-");
					data.put("deXn", xnArr2[1]);
				}else{
					data.put("deXn", "");
				}
			}else{
				data.put("deXn", "");
			}
			deXnCxpy.put("xnmc", "第二学年");
//			data.put("deXnCxpy", deXnCxpy);
			
			// 第三学年
			HashMap<String, String> currXnCxpy = cxpyService.getCxpyByXhXn(myForm.getXh(), dsXn);
			if(!"".equals(currXnCxpy)){
				String dsXn1 = currXnCxpy.get("xn");
				if(!"".equals(dsXn1)&&StringUtils.isNotNull(dsXn1)){
					String[] xnArr3= dsXn1.split("-");
					data.put("dsXn", xnArr3[1]);
				}else{
					data.put("dsXn", "");
				}
			}else{
				data.put("dsXn", "");
			}
			currXnCxpy.put("xnmc", "第三学年");
//			data.put("currXnCxpy", currXnCxpy);
			
			// 第四学年
			HashMap<String, String> fourthXnCxpy = cxpyService.getCxpyByXhXn(myForm.getXh(), fourthXn);
			fourthXnCxpy.put("xnmc", "第四学年");
			List<HashMap<String,String>> cxpyList = new ArrayList<HashMap<String,String>>();
			cxpyList.add(diXnCxpy);
			cxpyList.add(deXnCxpy);
			cxpyList.add(currXnCxpy);
			cxpyList.add(fourthXnCxpy);
			data.put("cxpyList", cxpyList);
			//院系意见
			/*String yxyj = new XsxxtyglService().getYxyj(myForm.getXh());
			request.setAttribute("yxyj", yxyj);*/
			//XsxxtyglForm myForm
			//学制处理
			int xzInt = 2;
			//院系意见的最后年度
			String yxyj = xsxxMap.get("shgxgzdw1");
			data.put("yxyj", yxyj);
			String xzStr = xsxxMap.get("xz");
			int xz_13943 = Integer.valueOf(xzStr);
			int bynd = xz_13943 + nj ;
			data.put("bynd", String.valueOf(bynd));
			if(StringUtils.isNotNull(xzStr)){
				xzInt = Integer.valueOf(xzStr);
				if(xzInt>4){
					xzInt = 4;
				}
			}
			data.put("xzInt", xzInt);
			// ======== 获取操行评语信息 end ========
			
			// ======== 获取入团入党 begin ========
			DtxxjgService dtxxjgService = new DtxxjgService();
			HashMap<String, String> rtMap = dtxxjgService.getGrDtjgxx(myForm.getXh(), "02"); // 入团
			data.put("rtMap", rtMap);
			HashMap<String, String> rdMap = dtxxjgService.getGrDtjgxx(myForm.getXh(), "11"); // 入党
			data.put("rdMap", rdMap);
			// ======== 获取入团入党 end ========
			String xmlFile = "";
			if(null!=hnjdDjb&&hnjdDjb.equals("djb")){
				xmlFile = "tyxjk_13943.xml";
				pjjgService.addBlankList(xlshjlList, 5 - xlshjlList.size());
			}else{
				xmlFile = "xsdjb_13943.xml";
				pjjgService.addBlankList(xlshjlList, 6 - xlshjlList.size());
			}
			data.put("xlshjlList_13943", xlshjlList);
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx",xmlFile,xh+"-"+xsxxMap.get("xm"));
		}
		else if("12036".equals(Base.xxdm)){
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xsdjb_12036.xml",xh+"-"+xsxxMap.get("xm"));
		}else if("12898".equals(Base.xxdm)){
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_12898.xml",xh+"-"+xsxxMap.get("xm"));
		}else if("10466".equals(Base.xxdm)){//河南农业大学个性化
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_10466.xml",xh+"-"+xsxxMap.get("xm"));
		}else if("88003".equals(Base.xxdm)){
			data.put("csrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.day));
			data.put("rxrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("rxrq"),FomartDateType.month));//入学年月
			for(int xn=1;xn<=5;xn++){
				for(int xq=1;xq<=2;xq++){
					HashMap<String,String> cxpdmap=service.getCxpd(xh, String.valueOf(xn), "0"+String.valueOf(xq));
					String py=HtmlUtil.xmlZy(cxpdmap.get("py"));
					cxpdmap.put("py", py);
					data.put("cxpd"+String.valueOf(xn)+"0"+String.valueOf(xq), cxpdmap);
				}
			}
			List<HashMap<String, String>> xjydList = new XjydjgService().getXsydList(myForm.getXh());
			Map<String,String> map=null;
			for (int i = 1; i <= 2; i++) {
				String key = "xjyd" + i;
				if(i <= xjydList.size()){
					map=xjydList.get(i - 1);
					data.put(key,map);
				}else{
					data.put(key, new HashMap<String, String>());
				}
			}
			
			if(xsxxMap.get("pyccmc").contains("中职")){
				List<HashMap<String,String>>jxscjList=service.getXnXscj(xh);
				data.put("jxscjList", jxscjList);
				file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_88003_zz.xml",xh+"-"+xsxxMap.get("xm"));
			}else{//高职
				for(int xn=1;xn<=5;xn++){
					List<HashMap<String, String>> cjListNew1 = null;
					List<HashMap<String, String>> cjListNew2 = null;
					for(int xq=1;xq<=2;xq++){
						List<HashMap<String, String>> cjList = xsxxglService.getXnXqcj(String.valueOf(xn-1),String.valueOf(xn-1),"0"+String.valueOf(xq),xh);
						List<HashMap<String, String>> cjListNew=new ArrayList<HashMap<String, String>>();
						//多余8个舍去，少于8个补空
						for(int i=0;i<cjList.size()&&i<8;i++){
							cjListNew.add(cjList.get(i));
						}
						for(int i=0;i<8-cjList.size();i++){
							cjListNew.add(new HashMap<String,String>());
						}
						if(xq==1){
							cjListNew1=cjListNew;
						}else{
							cjListNew2=cjListNew;
						}
					}
					HashMap<String,String> cjMapA=cjListNew1.get(0);//每学年第一行
					for(Map.Entry<String, String> entry:cjListNew2.get(0).entrySet()){
						cjMapA.put(entry.getKey()+"2",entry.getValue());
					}
					data.put("cjMapA"+xn, cjMapA);
					List<HashMap<String, String>> cjListLeft=new ArrayList<HashMap<String, String>>();
					for(int i=1;i<8;i++){
						HashMap<String,String> cjMapB=cjListNew1.get(i);
						for(Map.Entry<String, String> entry:cjListNew2.get(i).entrySet()){
							cjMapB.put(entry.getKey()+"2",entry.getValue());
						}
						cjListLeft.add(cjMapB);
					}
					data.put("cjListLeft"+xn, cjListLeft);
				}
				
				file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_88003_gz.xml",xh+"-"+xsxxMap.get("xm"));
			}
			
		}
		//华东交通大学理工学院
		else if(StringUtils.isEqual(Base.xxdm, "13431")){
			//评奖
			List<HashMap<String, String>> pjjg5line = pjjgService.getHjqkByXhMap(xh,5);
			data.put("pjjg5line", pjjg5line);
			//家庭成员
			jtcyxxList = jtqkdcService.getJtcyList(xh, 5);
			data.put("jtcyxxList_13431", jtcyxxList);
			//综测成绩
			List<HashMap<String, String>> zcfsList_13431 = new ZcfsService().getZcfsListForWord(myForm.getXh(),5);// 通过学号查询综测分数
			data.put("zcfsList_13431", zcfsList_13431);
			//学籍异动
			List<HashMap<String, String>> xjydList_13431 = new XjydjgService()
					.getXsydListForWord(myForm.getXh(),2);// 根据学号查询学籍异动列表
			data.put("xjydList_13431", xjydList_13431);

			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_13431.xml",xh+"-"+xsxxMap.get("xm"));
		}
		else if(StringUtils.isEqual(Base.xxdm, "10876")){
			List<HashMap<String, String>> hjqkList = pjjgService.getHjqkInfoMap(xh);
			data.put("hjqkList", hjqkList);
			int size_10876=(4 - hjqkList.size())<=0?0:(4 - hjqkList.size());
			data.put("blank_10876", service.getBlankList(size_10876));
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_10876.xml",xh+"-"+xsxxMap.get("xm"));
		}else if(StringUtils.isEqual(Base.xxdm, "11527")){
			
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_11527.xml",xh+"-"+xsxxMap.get("xm"));
		}else{
			String templatePath = "classpath://templates//xsxx//tyxjk_"+Base.xxdm+".xml";
			try{
				ResourceUtils.getFile(templatePath);
				file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_"+Base.xxdm+".xml",xh+"-"+xsxxMap.get("xm"));
			}catch (Exception e) {
				file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk.xml",xh+"-"+xsxxMap.get("xm"));
			}
		}
		return file;
		
	}
	
	
	
	/**
	 * 
	 * @描述:(下载江西科技师范大学新生入学登记表) 浙江传媒户籍登记表
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-5 上午10:39:24
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
	public ActionForward getDjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		
		File wordFile = getDjbWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:(下载江西科技师范大学新生入学登记表zip)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-5 上午10:43:44
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
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getDjbWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	
	private File getDjbWord(XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		String xh = myForm.getXh();
		User user = getUser(request);
		String hnjdDjb = request.getParameter("hnjdDjb");
		myForm.setUser(user);
		Map<String,Object> data = new HashMap<String,Object>();
		PjjgService pjjgService = new PjjgService();
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//学生基本信息
 		List<HashMap<String, String>> pjList = pjjgService.getPjpyInfoMap(xh);
 		List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
 		List<HashMap<String, String>> xlshjlList = xsxxglService.getXlshjlList(xh);
		HashMap<String, String> zpMap=service.getZpMap(myForm,request,"zp");
		data.putAll(xsxxMap);
		data.put("xxmc", Base.xxmc);
		data.put("pjList", pjList);
		data.put("jtcyxxList", jtcyxxList);
		data.put("xlshjlList", xlshjlList);
		data.putAll(zpMap);
		int size=(4 - pjList.size())<0?0:(4 - pjList.size());
		int size2=(5 - jtcyxxList.size())<=0?0:(5 - jtcyxxList.size());
		int size3=(13 - xlshjlList.size())<=0?0:(13 - xlshjlList.size());
		int size4=(14 - jtcyxxList.size())<=0?0:(14 - jtcyxxList.size());
		int size5=(4 - xlshjlList.size())<=0?0:(4 - xlshjlList.size());
		int size6=(4 - jtcyxxList.size())<=0?0:(4 - jtcyxxList.size());
		data.put("blankList", service.getBlankList(size));
		data.put("blankList2", service.getBlankList(size2));
		data.put("blankList3", service.getBlankList(size3));
		data.put("blankList4", service.getBlankList(size4));
		data.put("blankList5", service.getBlankList(size5));
		data.put("blankList6", service.getBlankList(size6));
		data.put("qfqk", service.getQfqk(xh));  //欠费情况
		
		//学生照片
		XsxxService xsxxService = new XsxxService();
		InputStream is = xsxxService.getPhotoStream(xh);
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		
		if (StringUtil.isNull(photo)){
			//取默认照片
			photo = xsxxService.getDefaultPhotoBase64(request);
		}
		
		if(photo == null){
			photo = "";
		}
		
		data.put("photo", photo);
		
		//获取学生住宿信息
		CwglService zsxxService = new CwglService();
		HashMap<String,String> zsxx =zsxxService.getCwForXh(xh);
		data.put("zsxx", zsxx);
		
		String fqsjh = null; //获取学生父亲手机号、母亲手机号
		String mqsjh = null;
		
		for (int i = 0; i < jtcyxxList.size(); i++) {
			if("父亲".equals(jtcyxxList.get(i).get("jtcygxmc"))){
				fqsjh = jtcyxxList.get(i).get("jtcylxdh1");
			}
			if("母亲".equals(jtcyxxList.get(i).get("jtcygxmc"))){
				mqsjh = jtcyxxList.get(i).get("jtcylxdh1");
			}
		}
		data.put("fqsjh", fqsjh);
		data.put("mqsjh", mqsjh);
		HashMap<String,String> fdyxm =service.getFdyByBj(xsxxMap.get("bjdm"));
		data.put("fdyxms", fdyxm);
		// 当前时间
		Calendar now = Calendar.getInstance();
		String year = String.valueOf(now.get(Calendar.YEAR));
		String month = String.valueOf(now.get(Calendar.MONTH) + 1);
		String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
		data.put("year", year);
		data.put("month", month);
		data.put("day", day);
		
		File file = null;
		
		if(Base.xxdm.equals(Globals.XXDM_ZJCMXY)){ //浙江传媒学院学生户籍登记表
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","kjdjb_11647.xml",xh+"-"+xsxxMap.get("xm"));
		}else if(Base.xxdm.equals(Globals.XXDM_YKZJX)) {
			//永康职业技术学校入学登记表 个性化
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xsrxdcb_90211.xml",xh+"-"+xsxxMap.get("xm"));
		}else if(Base.xxdm.equals(Globals.XXDM_CQSXYYGDZKXX)) {
			//重庆三峡医药高等专科学校入学登记表 个性化
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xsrxdjb_14008.xml",xh+"-"+xsxxMap.get("xm"));
		}else {
			//江西科技师范大学入学登记表 个性化
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_11318.xml",xh+"-"+xsxxMap.get("xm"));
		}
		
		return file;
		
	}
	
	/**
	 * @描述:下载华中师范大学学生卡
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-15 下午02:44:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	public ActionForward getXskpWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		
		File wordFile = getXskpWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @描述:下载华中师范大学学生卡
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-15 下午02:44:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	public ActionForward getXskpZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getXskpWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	/**
	 * @描述:下载华中师范大学学生卡
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-15 下午02:44:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	private File getXskpWord(XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		String xh = myForm.getXh();
		User user = getUser(request);
		myForm.setUser(user);
		Map<String,Object> data = new HashMap<String,Object>();
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//学生基本信息
 		List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh); //家庭成员
 		List<HashMap<String, String>> xlshjlList = xsxxglService.getXlshjlList(xh); // 学历社会经验
		data.putAll(xsxxMap);
		data.put("xxmc", Base.xxmc);
		data.put("jtcyxxList", jtcyxxList);
		data.put("xlshjlList", xlshjlList);
		int xlshjlSize=(5 - xlshjlList.size())<=0?0:(5 - xlshjlList.size());
		int jtcyxxSize=(4 - jtcyxxList.size())<=0?0:(4 - jtcyxxList.size());
		data.put("xlshjlBlankList", service.getBlankList(xlshjlSize));
		data.put("jtcyxxBlankList", service.getBlankList(jtcyxxSize));
		// ========由于模板限制，需要把评奖 、学生干部合并后一起遍历 begin==============
 		List<HashMap<String, String>> pjXsgbxxList = xsxxglService.getPjXsgbxxList(xh);
 		data.put("pjXsgbxxList", pjXsgbxxList);
 		int pjXsgbxxSize=(2 - pjXsgbxxList.size())<=0?0:(2 - pjXsgbxxList.size());
		data.put("pjXsgbxxBlankList", service.getBlankList(pjXsgbxxSize));
		// ========由于模板限制，需要把评奖 、学生干部合并后一起遍历 end==============
		//学生照片
		XsxxService xsxxService = new XsxxService();
		InputStream is = xsxxService.getPhotoStream(xh);
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		if (StringUtil.isNull(photo)){
			//取默认照片
			photo = xsxxService.getDefaultPhotoBase64(request);
		}
		if(photo == null){
			photo = "";
		}
		data.put("photo", photo);
		
		//获取学生住宿信息
		CwglService zsxxService = new CwglService();
		HashMap<String,String> zsxx =zsxxService.getCwForXh(xh);
		data.put("zsxx", zsxx);
		
		//获得港澳台侨外名称
		XsxxglService xsxxqtService = new XsxxglService();
		HashMap<String,String> qtxx = xsxxqtService.getXsxxByXh(xh);
		data.put("qtxx", qtxx);
		
		// 家庭手机号
		String jtshhm = "";
		for (HashMap<String, String> jtcyxxMap : jtcyxxList) {
			String jtcylxdh1 = jtcyxxMap.get("jtcylxdh1");
			if(StringUtils.isNotNull(jtcylxdh1)){
				jtshhm = jtcylxdh1;
			}
		}
		data.put("jtshhm", jtshhm);
		
		File file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xskp_10511.xml",xh+"-"+xsxxMap.get("xm"));
		return file;
		
	}
	private File getZzjsxWord(XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		String xh = myForm.getXh();
		User user = getUser(request);
		myForm.setUser(user);
		String dysj = GetTime.getTimeByFormat("yyyy-MM-dd");
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//学生基本信息
		xsxxMap.put("zd3", DateTranCnDate.fomartDateToCn(xsxxMap.get("zd3"), FomartDateType.month));
		if(!"".equals(xsxxMap.get("zd2"))&&null!=xsxxMap.get("zd2")){
			xsxxMap.put("zd2", xsxxMap.get("zd2")+"、");
		}
		data.putAll(xsxxMap);
		data.put("xxmc", Base.xxmc);
		data.put("dysj", DateUtils.getDateString(DateUtils.parse(dysj),"5"));
		return FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tzzgxb.xml",xh+"-"+xsxxMap.get("xm"));
	}
	
	/**
	 * 
	 * @描述:杭州汽车技工成绩单打印
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-7-6 上午09:20:57
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
	public ActionForward printCjd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		File wordFile = getCjdWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * 
	 * @描述:杭州汽车技工成绩单批量打印
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-7-6 上午09:20:57
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
	public ActionForward getCjdZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getCjdWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			
			for(int j=0;j<files.size();j++){
				files.get(j).delete();
			}
			
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述:生成成绩单的Word-杭州汽车技工个性化
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-7-6 上午10:18:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getCjdWord(XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		String xh = myForm.getXh();
		User user = getUser(request);
		myForm.setUser(user);
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//学生基本信息
		List<HashMap<String, String>> xscj = service.getCjListByXhXnXq(xh, Base.currXn, Base.currXq);//学生成绩信息
		
		data.put("rs",xsxxMap);
		data.put("xnf", xgxt.utils.date.DateUtils.numToZh(Base.currXn.substring(2, 4)));
		data.put("xns", xgxt.utils.date.DateUtils.numToZh(Base.currXn.substring(7, 9)));
		data.put("xq", xgxt.utils.date.DateUtils.numToZh(String.valueOf(Integer.parseInt(Base.currXq))));
		
		//输入成绩信息，需要12门
		for(int i=1;i<12;i++){
			if(i<=xscj.size()){
				data.put("cj"+i, xscj.get(i-1).get("cj"));
				data.put("kcmc"+i, xscj.get(i-1).get("kcmc"));
			}else{
				data.put("cj"+i,"");
				data.put("kcmc"+i,"");
			}	
		}
		
		//获取操行评语信息
		CxpyService cxpyService = new CxpyService();
		data.putAll(cxpyService.getCxpyByXhXnXq(xh, Base.currXn,Base.currXq));
		
		PjjgService pjjgService = new PjjgService();
		
		CfjgService CfjgService = new CfjgService();
		
		String pjjg = pjjgService.getPjxxByXhXnXq(xh, Base.currXn, Base.currXq);
		String wjjg = CfjgService.getWjxxByXhXnXq(xh, Base.currXn, Base.currXq);
		data.put("jcqk", pjjg+";"+wjjg);
		
		return FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","hzqcjg_80152_cjd.xml",
				xh+"-"+xsxxMap.get("xm")+"成绩单");
	}
	
	/**
	 * 
	 * @描述:上海海洋个性化登记表
	 * @作者：ChenQ-856
	 * @日期：2015-9-11 下午04:45:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	public ActionForward printXsdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		File wordFile = getXsdjbWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	/**
	 * 
	 * @描述:上海海洋个性化登记表
	 * @作者：ChenQ-856
	 * @日期：2015-9-11 下午04:45:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	public ActionForward getXsdjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getXsdjbWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			
			for(int j=0;j<files.size();j++){
				files.get(j).delete();
			}
		}
		return null;
	}
	/**
	 * 
	 * @描述:上海海洋个性化登记表
	 * @作者：ChenQ-856
	 * @日期：2015-9-11 下午04:45:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getXsdjbWord(XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		String xh = myForm.getXh();
		User user = getUser(request);
		myForm.setUser(user);
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//学生基本信息
		XsxxglService xsxxglService = new XsxxglService();
 		List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh); //家庭成员
 		List<HashMap<String, String>> xlshjlList = xsxxglService.getXlshjlList(xh); // 学历社会经验
 		HashMap<String, String> zpMap=service.getZpMap(myForm,request,"zp");
 		
 		//空的List用于前台输出空白行
 		int i = 4;
 		if(xlshjlList.size()>=4){
 			i = 0;
 		}else{
 			i = 4 - xlshjlList.size();
 		}
 		String[] blankList = new String[i];
 		
 		//空的List用于前台输出空白行
 		int j = 6;
 		if(jtcyxxList.size()>=6){
 			j = 0;
 		}else{
 			j = 6 - jtcyxxList.size();
 		}
 		String[] blankList1 = new String[j];
 		
		for (Map.Entry<String, String> entry : xsxxMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			if (StringUtils.isNull(value)){
				xsxxMap.put(key, "无");
			}
		}
		data.put("xs",xsxxMap);
		data.putAll(zpMap);
		data.put("csrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.day));
		data.put("jrgqtsj",DateTranCnDate.fomartDateToCn(xsxxMap.get("jrgqtsj"),FomartDateType.month));
		data.put("jrgcdsj",null==DateTranCnDate.fomartDateToCn(xsxxMap.get("jrgcdsj"),FomartDateType.month)?"无":DateTranCnDate.fomartDateToCn(xsxxMap.get("jrgcdsj"),FomartDateType.month));
		data.put("jtcyList", jtcyxxList);
		data.put("jyjlList", xlshjlList);
		data.put("blankList",  Arrays.asList(blankList));
		data.put("blankList1",  Arrays.asList(blankList1));
		
		CwglService zsxxService = new CwglService();
		HashMap<String,String> zsxx =zsxxService.getCwForXh(xh);
		data.put("zsxx", zsxx);
		data.putAll(xsxxMap);
		List<HashMap<String, String>> xlshjlList5line = xsxxglService.getXlshjlList(xh,5);
		List<HashMap<String, String>> jtcyList4line = xsxxglService.getJtcyList(xh,4);
		data.put("xlshjlList5line", xlshjlList5line);
		data.put("jtcyList4line", jtcyList4line);
		data.put("sysdate", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
		
		return FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xsdjb_"+Base.xxdm+".xml",
				xh+"-"+xsxxMap.get("xm")+"新生登记表");
	}
	
	
	/**
	 * @描述: 单个下载杭州汽车高级技工学校学籍卡
	 * @作者：孟威[工号：1186]
	 * @日期：2015-9-25 上午10:33:46
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
	public ActionForward printHzqcxjk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		File wordFile = getXjkWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * @描述: 批量下载杭州汽车高级技工学校学籍卡
	 * @作者：孟威[工号：1186]
	 * @日期：2015-10-21 上午09:53:32
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
	public ActionForward getHzqcxjkZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getXjkWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			
			for(int j=0;j<files.size();j++){
				files.get(j).delete();
			}
		}
		return null;
	}
	/**
	 * @描述: 单个下载徐州医药学籍卡
	 * @作者：JZ[工号：1529]
	 * @日期：2017-12-29 
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
	public ActionForward printXzyyxjk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		File wordFile = getXjkWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	/**
	 * @描述: 多个下载徐州医药学籍卡
	 * @作者：JZ[工号：1529]
	 * @日期：2017-12-29 
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
	public ActionForward getXzyyxjkZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getXjkWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			
			for(int j=0;j<files.size();j++){
				files.get(j).delete();
			}
		}
		return null;
	}
	/** 浙江大学新生入学登记表
	 * @描述: 单个打印登记表
	 * @作者：孟威[工号：1186]
	 * @日期：2015-12-3 下午07:29:09
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
	public ActionForward Xsrxdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		File wordFile = getXjkWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * 北京林业大学
	 * @描述:本科生基本信息登记表
	 * @作者：caopei[工号：1352]
	 * @日期：2016-8-30 下午07:52:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward Jbxxdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		File wordFile = getXjkWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	/**浙江大学新生入学登记表
	 * @描述: 批量打印登记表
	 * @作者：孟威[工号：1186]
	 * @日期：2015-12-3 下午07:33:15
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
	public ActionForward XsrxdjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getXjkWord(myForm,request);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			for(int j=0;j<files.size();j++){
				files.get(j).delete();
			}
		}
		return null;
	}
	/**
	 * 北京林业大学
	 * @描述:批量打印登记表
	 * @作者：caopei[工号：1352]
	 * @日期：2016-8-30 下午07:57:33
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
		public ActionForward JbxxdjbZip(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			XsxxtyglForm myForm = (XsxxtyglForm) form;
			String value = request.getParameter("value");
			if (StringUtils.isNotNull(value)){
				String[] values = value.split(",");
				List<File> files = new ArrayList<File>();
				for (int i = 0 , n = values.length ; i < n ; i++){
					myForm.setXh(values[i]);
					File file = getXjkWord(myForm,request);
					files.add(file);
				}
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
				for(int j=0;j<files.size();j++){
					files.get(j).delete();
				}
			}
			return null;
		}
	/**
	 * @描述: 南通科技职业学院学生成绩表单个打印(3年制)
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-5-17 上午09:47:03
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
		public ActionForward getPrintXscjb(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			XsxxtyglForm myForm = (XsxxtyglForm) form;
			File wordFile = getCjbWord(myForm, request);
			FileUtil.outputWord(response, wordFile);
			wordFile.delete();
			return null;
		}
	/**
	 * @描述: 南通科技职业学院学生成绩表批量打印(3年制)
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-5-17 上午09:47:03
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
		public ActionForward getPrintXscjbZip(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			XsxxtyglForm myForm = (XsxxtyglForm) form;
			String value = request.getParameter("value");
			if (StringUtils.isNotNull(value)){
				String[] values = value.split(",");
				List<File> files = new ArrayList<File>();
				for (int i = 0 , n = values.length ; i < n ; i++){
					myForm.setXh(values[i]);
					File file = getCjbWord(myForm,request);
					files.add(file);
				}
				
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
				
				for(int j=0;j<files.size();j++){
					files.get(j).delete();
				}
			}
			return null;
		}
	/**
	 * @描述: 南通农业职业技术学院增加按钮、出成绩表个性化_12684（南通科技职业学院）
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-5-17 上午09:22:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
		private File getCjbWord (XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
			XsxxService xsxxService = new XsxxService();
			XsxxglService xsxxglService = new XsxxglService();
			String xh = myForm.getXh();
			String dyrq = xsxxglService.getDqrq(xh).replace("-", ".");
			User user = getUser(request);
			myForm.setUser(user);
			Map<String,Object> data = new HashMap<String,Object>();
			HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
			data.putAll(xsxxMap);//加载学生基本信息
			data.put("csrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.month));//出生年月
			data.put("rxrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("rxrq"),FomartDateType.month));//入学年月
			data.put("dyrq",dyrq);//获得打印日期
			File file = null;
			//获取第一学年第一学期成绩
			List<HashMap<String, String>> getXn1Xq1List = xsxxglService.getXnXqcj("0","0","01",xh);
			//获取第一学年第二学期成绩
			List<HashMap<String, String>> getXn1Xq2List = xsxxglService.getXnXqcj("0","0","02",xh);
			//获取第二学年第一学期成绩
			List<HashMap<String, String>> getXn2Xq1List = xsxxglService.getXnXqcj("1","1","01",xh);
			//获取第二学年第二学期成绩
			List<HashMap<String, String>> getXn2Xq2List = xsxxglService.getXnXqcj("1","1","02",xh);
			//获取第三学年第一学期成绩
			List<HashMap<String, String>> getXn3Xq1List = xsxxglService.getXnXqcj("2","2","01",xh);
			//获取第三学年第二学期成绩
			List<HashMap<String, String>> getXn3Xq2List = xsxxglService.getXnXqcj("2","2","02",xh);
			//获取第四学第一学期期成绩
			List<HashMap<String, String>> getXn4Xq1List = xsxxglService.getXnXqcj("3","3","01",xh);
			//获取第四学年第二学期成绩
			List<HashMap<String, String>> getXn4Xq2List = xsxxglService.getXnXqcj("3","3","02",xh);
			//第一学年、第二学年成绩表循序
			int Cj1Size = 13;
			if(Cj1Size < getXn1Xq1List.size()){
				Cj1Size = getXn1Xq1List.size();
			}
			if(Cj1Size < getXn1Xq2List.size()){
				Cj1Size = getXn1Xq2List.size();
			}
			if(Cj1Size < getXn2Xq1List.size()){
				Cj1Size = getXn2Xq1List.size();
			}
			if(Cj1Size < getXn2Xq2List.size()){
				Cj1Size = getXn2Xq2List.size();
			}
			List<HashMap<String, String>> OneList = new ArrayList<HashMap<String, String>>();
			HashMap<String,String> map1 = null;
			for (int i = 0;i<Cj1Size;i++){
				map1 = new HashMap<String,String>();
				if(i< getXn1Xq1List.size()){
					map1.put("kcmc1", getXn1Xq1List.get(i).get("kcmc"));
					map1.put("kcxz1", getXn1Xq1List.get(i).get("kcxz"));
					map1.put("cxbj1", getXn1Xq1List.get(i).get("cxbj"));
					map1.put("xf1", getXn1Xq1List.get(i).get("xf"));
					map1.put("cj1", getXn1Xq1List.get(i).get("cj"));
				} else {
					map1.put("kcmc1", "");
					map1.put("kcxz1", "");
					map1.put("cxbj1", "");
					map1.put("xf1", "");
					map1.put("cj1", "");
				}
				if(i< getXn1Xq2List.size()){
					map1.put("kcmc2", getXn1Xq2List.get(i).get("kcmc"));
					map1.put("kcxz2", getXn1Xq2List.get(i).get("kcxz"));
					map1.put("cxbj2", getXn1Xq2List.get(i).get("cxbj"));
					map1.put("xf2", getXn1Xq2List.get(i).get("xf"));
					map1.put("cj2", getXn1Xq2List.get(i).get("cj"));
				} else {
					map1.put("kcmc2", "");
					map1.put("kcxz2", "");
					map1.put("cxbj2", "");
					map1.put("xf2", "");
					map1.put("cj2", "");
				}
				if(i< getXn2Xq1List.size()){
					map1.put("kcmc3", getXn2Xq1List.get(i).get("kcmc"));
					map1.put("kcxz3", getXn2Xq1List.get(i).get("kcxz"));
					map1.put("cxbj3", getXn2Xq1List.get(i).get("cxbj"));
					map1.put("xf3", getXn2Xq1List.get(i).get("xf"));
					map1.put("cj3", getXn2Xq1List.get(i).get("cj"));
				} else {
					map1.put("kcmc3", "");
					map1.put("kcxz3", "");
					map1.put("cxbj3", "");
					map1.put("xf3", "");
					map1.put("cj3", "");
				}
				if(i< getXn2Xq2List.size()){
					map1.put("kcmc4", getXn2Xq2List.get(i).get("kcmc"));
					map1.put("kcxz4", getXn2Xq2List.get(i).get("kcxz"));
					map1.put("cxbj4", getXn2Xq2List.get(i).get("cxbj"));
					map1.put("xf4", getXn2Xq2List.get(i).get("xf"));
					map1.put("cj4", getXn2Xq2List.get(i).get("cj"));
				} else {
					map1.put("kcmc4", "");
					map1.put("kcxz4", "");
					map1.put("cxbj4", "");
					map1.put("xf4", "");
					map1.put("cj4", "");
				}
				OneList.add(map1);
			}
			data.put("OneList",OneList);
			//第三学年、第四学年成绩表循序
			int Cj2Size = 21-Cj1Size;
			if(Cj2Size < getXn3Xq1List.size()){
				Cj2Size = getXn3Xq1List.size();
			}
			if(Cj2Size < getXn3Xq2List.size()){
				Cj2Size = getXn3Xq2List.size();
			}
			if(Cj2Size < getXn4Xq1List.size()){
				Cj2Size = getXn4Xq1List.size();
			}
			if(Cj2Size < getXn4Xq2List.size()){
				Cj2Size = getXn4Xq2List.size();
			}
			List<HashMap<String, String>> TwoList = new ArrayList<HashMap<String, String>>();
			HashMap<String,String> map2 = null;
			for (int i = 0;i<Cj2Size;i++){
				map2 = new HashMap<String,String>();
				if(i< getXn3Xq1List.size()){
					map2.put("kcmc5", getXn3Xq1List.get(i).get("kcmc"));
					map2.put("kcxz5", getXn3Xq1List.get(i).get("kcxz"));
					map2.put("cxbj5", getXn3Xq1List.get(i).get("cxbj"));
					map2.put("xf5", getXn3Xq1List.get(i).get("xf"));
					map2.put("cj5", getXn3Xq1List.get(i).get("cj"));
				} else {
					map2.put("kcmc5", "");
					map2.put("kcxz5", "");
					map2.put("cxbj5", "");
					map2.put("xf5", "");
					map2.put("cj5", "");
				}
				if(i< getXn3Xq2List.size()){
					map2.put("kcmc6", getXn3Xq2List.get(i).get("kcmc"));
					map2.put("kcxz6", getXn3Xq2List.get(i).get("kcxz"));
					map2.put("cxbj6", getXn3Xq2List.get(i).get("cxbj"));
					map2.put("xf6", getXn3Xq2List.get(i).get("xf"));
					map2.put("cj6", getXn3Xq2List.get(i).get("cj"));
				} else {
					map2.put("kcmc6", "");
					map2.put("kcxz6", "");
					map2.put("cxbj6", "");
					map2.put("xf6", "");
					map2.put("cj6", "");
				}
				if(i< getXn4Xq1List.size()){
					map2.put("kcmc7", getXn4Xq1List.get(i).get("kcmc"));
					map2.put("kcxz7", getXn4Xq1List.get(i).get("kcxz"));
					map2.put("cxbj7", getXn4Xq1List.get(i).get("cxbj"));
					map2.put("xf7", getXn4Xq1List.get(i).get("xf"));
					map2.put("cj7", getXn4Xq1List.get(i).get("cj"));
				} else {
					map2.put("kcmc7", "");
					map2.put("kcxz7", "");
					map2.put("cxbj7", "");
					map2.put("xf7", "");
					map2.put("cj7", "");
				}
				if(i< getXn4Xq2List.size()){
					map2.put("kcmc8", getXn4Xq2List.get(i).get("kcmc"));
					map2.put("kcxz8", getXn4Xq2List.get(i).get("kcxz"));
					map2.put("cxbj8", getXn4Xq2List.get(i).get("cxbj"));
					map2.put("xf8", getXn4Xq2List.get(i).get("xf"));
					map2.put("cj8", getXn4Xq2List.get(i).get("cj"));
				} else {
					map2.put("kcmc8", "");
					map2.put("kcxz8", "");
					map2.put("cxbj8", "");
					map2.put("xf8", "");
					map2.put("cj8", "");
				}
				TwoList.add(map2);
			}
			data.put("TwoList",TwoList);
			//学生课程学期和总学分
			HashMap<String, String> kcxqMap = xsxxglService.getKcxq(xh);
			data.put("kcxqList", kcxqMap);
			//毕业设计题目、成绩
			HashMap<String, String> bysj = xsxxglService.getBysj(xh);
			data.put("bysj", bysj);
			//等级考试成绩
			List<HashMap<String, String>> djksList = xsxxglService.getDjkscj(xh);
			data.put("djksList",djksList);
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xscjb_12684.xml",xh+"-"+xsxxMap.get("xm"));
			return file;
		}
		/**
		 * @描述: 南通科技职业学院学生成绩表单个打印(5年制)
		 * @作者： 孟威[工号：1186]
		 * @日期：2016-5-17 上午09:47:03
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
			public ActionForward getPrintXscjbwu(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				XsxxtyglForm myForm = (XsxxtyglForm) form;
				File wordFile = getCjbwuWord(myForm, request);
				FileUtil.outputWord(response, wordFile);
				wordFile.delete();
				return null;
			}
		/**
		 * @描述: 南通科技职业学院学生成绩表批量打印(5年制)
		 * @作者： 孟威[工号：1186]
		 * @日期：2016-5-17 上午09:47:03
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
			public ActionForward getPrintXscjbwuZip(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				XsxxtyglForm myForm = (XsxxtyglForm) form;
				String value = request.getParameter("value");
				if (StringUtils.isNotNull(value)){
					String[] values = value.split(",");
					List<File> files = new ArrayList<File>();
					for (int i = 0 , n = values.length ; i < n ; i++){
						myForm.setXh(values[i]);
						File file = getCjbwuWord(myForm,request);
						files.add(file);
					}
					
					File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
					FileUtil.outputZip(response, zipFile);
					
					for(int j=0;j<files.size();j++){
						files.get(j).delete();
					}
				}
				return null;
			}
			private File getCjbwuWord (XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
				XsxxService xsxxService = new XsxxService();
				XsxxglService xsxxglService = new XsxxglService();
				String xh = myForm.getXh();
				String dyrq = xsxxglService.getDqrq(xh).replace("-", ".");
				User user = getUser(request);
				myForm.setUser(user);
				Map<String,Object> data = new HashMap<String,Object>();
				HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
				data.putAll(xsxxMap);//加载学生基本信息
				data.put("csrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.month));//出生年月
				data.put("rxrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("rxrq"),FomartDateType.month));//入学年月
				data.put("dyrq",dyrq);//获得打印日期
				File file = null;
				//获取第一学年第一学期成绩
				List<HashMap<String, String>> getXn1Xq1List = xsxxglService.getXnXqcj("0","0","01",xh);
				//获取第一学年第二学期成绩
				List<HashMap<String, String>> getXn1Xq2List = xsxxglService.getXnXqcj("0","0","02",xh);
				//获取第二学年第一学期成绩
				List<HashMap<String, String>> getXn2Xq1List = xsxxglService.getXnXqcj("1","1","01",xh);
				//获取第二学年第二学期成绩
				List<HashMap<String, String>> getXn2Xq2List = xsxxglService.getXnXqcj("1","1","02",xh);
				//获取第三学年第一学期成绩
				List<HashMap<String, String>> getXn3Xq1List = xsxxglService.getXnXqcj("2","2","01",xh);
				//获取第三学年第二学期成绩
				List<HashMap<String, String>> getXn3Xq2List = xsxxglService.getXnXqcj("2","2","02",xh);
				//获取第四学第一学期期成绩
				List<HashMap<String, String>> getXn4Xq1List = xsxxglService.getXnXqcj("3","3","01",xh);
				//获取第四学年第二学期成绩
				List<HashMap<String, String>> getXn4Xq2List = xsxxglService.getXnXqcj("3","3","02",xh);
				//获取第五学年第一学期成绩
				List<HashMap<String, String>> getXn5Xq1List = xsxxglService.getXnXqcj("4","4","01",xh);
				//获取第五学年第二学期成绩
				List<HashMap<String, String>> getXn5Xq2List = xsxxglService.getXnXqcj("4","4","02",xh);
				//第一学年、第二学年成绩表循序
				int Cj1Size = 13;
				if(Cj1Size < getXn1Xq1List.size()){
					Cj1Size = getXn1Xq1List.size();
				}
				if(Cj1Size < getXn1Xq2List.size()){
					Cj1Size = getXn1Xq2List.size();
				}
				if(Cj1Size < getXn2Xq1List.size()){
					Cj1Size = getXn2Xq1List.size();
				}
				if(Cj1Size < getXn2Xq2List.size()){
					Cj1Size = getXn2Xq2List.size();
				}
				List<HashMap<String, String>> OneList = new ArrayList<HashMap<String, String>>();
				HashMap<String,String> map1 = null;
				for (int i = 0;i<Cj1Size;i++){
					map1 = new HashMap<String,String>();
					if(i< getXn1Xq1List.size()){
						map1.put("kcmc1", getXn1Xq1List.get(i).get("kcmc"));
						map1.put("kcxz1", getXn1Xq1List.get(i).get("kcxz"));
						map1.put("cxbj1", getXn1Xq1List.get(i).get("cxbj"));
						map1.put("xf1", getXn1Xq1List.get(i).get("xf"));
						map1.put("cj1", getXn1Xq1List.get(i).get("cj"));
					} else {
						map1.put("kcmc1", "");
						map1.put("kcxz1", "");
						map1.put("cxbj1", "");
						map1.put("xf1", "");
						map1.put("cj1", "");
					}
					if(i< getXn1Xq2List.size()){
						map1.put("kcmc2", getXn1Xq2List.get(i).get("kcmc"));
						map1.put("kcxz2", getXn1Xq2List.get(i).get("kcxz"));
						map1.put("cxbj2", getXn1Xq2List.get(i).get("cxbj"));
						map1.put("xf2", getXn1Xq2List.get(i).get("xf"));
						map1.put("cj2", getXn1Xq2List.get(i).get("cj"));
					} else {
						map1.put("kcmc2", "");
						map1.put("kcxz2", "");
						map1.put("cxbj2", "");
						map1.put("xf2", "");
						map1.put("cj2", "");
					}
					if(i< getXn2Xq1List.size()){
						map1.put("kcmc3", getXn2Xq1List.get(i).get("kcmc"));
						map1.put("kcxz3", getXn2Xq1List.get(i).get("kcxz"));
						map1.put("cxbj3", getXn2Xq1List.get(i).get("cxbj"));
						map1.put("xf3", getXn2Xq1List.get(i).get("xf"));
						map1.put("cj3", getXn2Xq1List.get(i).get("cj"));
					} else {
						map1.put("kcmc3", "");
						map1.put("kcxz3", "");
						map1.put("cxbj3", "");
						map1.put("xf3", "");
						map1.put("cj3", "");
					}
					if(i< getXn2Xq2List.size()){
						map1.put("kcmc4", getXn2Xq2List.get(i).get("kcmc"));
						map1.put("kcxz4", getXn2Xq2List.get(i).get("kcxz"));
						map1.put("cxbj4", getXn2Xq2List.get(i).get("cxbj"));
						map1.put("xf4", getXn2Xq2List.get(i).get("xf"));
						map1.put("cj4", getXn2Xq2List.get(i).get("cj"));
					} else {
						map1.put("kcmc4", "");
						map1.put("kcxz4", "");
						map1.put("cxbj4", "");
						map1.put("xf4", "");
						map1.put("cj4", "");
					}
					OneList.add(map1);
				}
				data.put("OneList",OneList);
				//第三学年、第四学年成绩表循序
				int Cj2Size = 11;
				if(Cj2Size < getXn3Xq1List.size()){
					Cj2Size = getXn3Xq1List.size();
				}
				if(Cj2Size < getXn3Xq2List.size()){
					Cj2Size = getXn3Xq2List.size();
				}
				if(Cj2Size < getXn4Xq1List.size()){
					Cj2Size = getXn4Xq1List.size();
				}
				if(Cj2Size < getXn4Xq2List.size()){
					Cj2Size = getXn4Xq2List.size();
				}
				List<HashMap<String, String>> TwoList = new ArrayList<HashMap<String, String>>();
				HashMap<String,String> map2 = null;
				for (int i = 0;i<Cj2Size;i++){
					map2 = new HashMap<String,String>();
					if(i< getXn3Xq1List.size()){
						map2.put("kcmc5", getXn3Xq1List.get(i).get("kcmc"));
						map2.put("kcxz5", getXn3Xq1List.get(i).get("kcxz"));
						map2.put("cxbj5", getXn3Xq1List.get(i).get("cxbj"));
						map2.put("xf5", getXn3Xq1List.get(i).get("xf"));
						map2.put("cj5", getXn3Xq1List.get(i).get("cj"));
					} else {
						map2.put("kcmc5", "");
						map2.put("kcxz5", "");
						map2.put("cxbj5", "");
						map2.put("xf5", "");
						map2.put("cj5", "");
					}
					if(i< getXn3Xq2List.size()){
						map2.put("kcmc6", getXn3Xq2List.get(i).get("kcmc"));
						map2.put("kcxz6", getXn3Xq2List.get(i).get("kcxz"));
						map2.put("cxbj6", getXn3Xq2List.get(i).get("cxbj"));
						map2.put("xf6", getXn3Xq2List.get(i).get("xf"));
						map2.put("cj6", getXn3Xq2List.get(i).get("cj"));
					} else {
						map2.put("kcmc6", "");
						map2.put("kcxz6", "");
						map2.put("cxbj6", "");
						map2.put("xf6", "");
						map2.put("cj6", "");
					}
					if(i< getXn4Xq1List.size()){
						map2.put("kcmc7", getXn4Xq1List.get(i).get("kcmc"));
						map2.put("kcxz7", getXn4Xq1List.get(i).get("kcxz"));
						map2.put("cxbj7", getXn4Xq1List.get(i).get("cxbj"));
						map2.put("xf7", getXn4Xq1List.get(i).get("xf"));
						map2.put("cj7", getXn4Xq1List.get(i).get("cj"));
					} else {
						map2.put("kcmc7", "");
						map2.put("kcxz7", "");
						map2.put("cxbj7", "");
						map2.put("xf7", "");
						map2.put("cj7", "");
					}
					if(i< getXn4Xq2List.size()){
						map2.put("kcmc8", getXn4Xq2List.get(i).get("kcmc"));
						map2.put("kcxz8", getXn4Xq2List.get(i).get("kcxz"));
						map2.put("cxbj8", getXn4Xq2List.get(i).get("cxbj"));
						map2.put("xf8", getXn4Xq2List.get(i).get("xf"));
						map2.put("cj8", getXn4Xq2List.get(i).get("cj"));
					} else {
						map2.put("kcmc8", "");
						map2.put("kcxz8", "");
						map2.put("cxbj8", "");
						map2.put("xf8", "");
						map2.put("cj8", "");
					}
					TwoList.add(map2);
				}
				data.put("TwoList",TwoList);
				//第五学年成绩表循序
				int Cj3Size = 9;
				if(Cj3Size < getXn5Xq1List.size()){
					Cj3Size = getXn5Xq1List.size();
				}
				if(Cj3Size < getXn5Xq2List.size()){
					Cj3Size = getXn5Xq2List.size();
				}
				List<HashMap<String, String>> ThreeList = new ArrayList<HashMap<String, String>>();
				HashMap<String,String> map3 = null;
				for (int i = 0;i<Cj3Size;i++){
					map3 = new HashMap<String,String>();
					if(i< getXn5Xq1List.size()){
						map3.put("kcmc9", getXn5Xq1List.get(i).get("kcmc"));
						map3.put("kcxz9", getXn5Xq1List.get(i).get("kcxz"));
						map3.put("cxbj9", getXn5Xq1List.get(i).get("cxbj"));
						map3.put("xf9", getXn5Xq1List.get(i).get("xf"));
						map3.put("cj9", getXn5Xq1List.get(i).get("cj"));
					} else {
						map3.put("kcmc9", "");
						map3.put("kcxz9", "");
						map3.put("cxbj9", "");
						map3.put("xf9", "");
						map3.put("cj9", "");
					}
					if(i< getXn5Xq2List.size()){
						map3.put("kcmc10", getXn5Xq2List.get(i).get("kcmc"));
						map3.put("kcxz10", getXn5Xq2List.get(i).get("kcxz"));
						map3.put("cxbj10", getXn5Xq2List.get(i).get("cxbj"));
						map3.put("xf10", getXn5Xq2List.get(i).get("xf"));
						map3.put("cj10", getXn5Xq2List.get(i).get("cj"));
					} else {
						map3.put("kcmc10", "");
						map3.put("kcxz10", "");
						map3.put("cxbj10", "");
						map3.put("xf10", "");
						map3.put("cj10", "");
					}
					ThreeList.add(map3);
				}
				data.put("ThreeList",ThreeList);
				//学生课程学期和总学分
				HashMap<String, String> kcxqMap = xsxxglService.getKcxq(xh);
				data.put("kcxqList", kcxqMap);
				//毕业设计题目、成绩
				HashMap<String, String> bysj = xsxxglService.getBysj(xh);
				data.put("bysj", bysj);
				//等级考试成绩
				List<HashMap<String, String>> djksList = xsxxglService.getDjkscj(xh);
				data.put("djksList",djksList);
				file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xscjbwnz_12684.xml",xh+"-"+xsxxMap.get("xm"));
				return file;
			}
			
			/** 
			 * @描述:打印班级花名册(咸宁职业技术学院个性化)
			 * @作者：柳俊[工号：1282]
			 * @日期：2016-12-15 下午04:23:01
			 * @修改记录: 修改者名字-修改日期-修改内容
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * ActionForward 返回类型 
			 * @throws 
			 */
			public ActionForward xnzyjsxyhmcExportData(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response) throws Exception{
				XsxxtyglForm myForm = (XsxxtyglForm) form;
				//生成高级查询对象		
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				SearchModel sm = new SearchModel();
				if(null != searchModel.getSearch_tj_nj()){//年级查询条件
					sm.setSearch_tj_nj(searchModel.getSearch_tj_nj());
				}
				if(null != searchModel.getSearch_tj_zy()){//专业查询条件
					sm.setSearch_tj_zy(searchModel.getSearch_tj_zy());
				}
				if(null != searchModel.getSearch_tj_bj()){//班级查询条件
					sm.setSearch_tj_bj(searchModel.getSearch_tj_bj());
				}
				sm.setSearch_tj_xy(searchModel.getSearch_tj_xy());//学院查询条件
				sm.setPath(searchModel.getPath());
				myForm.setSearchModel(sm);//设置花名册查询条件
				XsxxtyglService service = new XsxxtyglService();
				File file = service.getBjhmcExcelList(myForm);//获取花名册exl文件
				FileUtil.outputExcel(response, file);
				return null;				
			}
			
			/**
			 * @描述:学生身份证明Word文件下载（重庆工程职业技术学院）
			 * @作者：xuwen[工号：1426]
			 * @日期：2017年1月4日 下午5:17:41
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
			public ActionForward getSI (ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response) throws Exception{
				XsxxtyglForm myForm = (XsxxtyglForm) form;
				File wordFile = getSIWord(myForm, request);
				FileUtil.outputWord(response, wordFile);
				wordFile.delete();
				return null;
			}
			
			/**
			 * @描述:学生身份证明Word文件批量Zip下载（重庆工程职业技术学院）
			 * @作者：xuwen[工号：1426]
			 * @日期：2017年1月4日 下午5:22:15
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
			public ActionForward getSIZip (ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response) throws Exception{
				XsxxtyglForm myForm = (XsxxtyglForm) form;
				String value = request.getParameter("value");
				if (StringUtils.isNotNull(value)){
					String[] values = value.split(",");
					List<File> files = new ArrayList<File>();
					for (int i = 0 , n = values.length ; i < n ; i++){
						myForm.setXh(values[i]);
						File file = getSIWord(myForm,request);
						files.add(file);
					}
					
					File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
					FileUtil.outputZip(response, zipFile);
					
					for(int j=0;j<files.size();j++){
						files.get(j).delete();
					}
				}
				return null;
			}
			
			/**
			 * @描述:返回Word文件（用于学生身份证明Word或Zip文件下载时调用，暂针对重庆工程职业技术学院）
			 * @作者：xuwen[工号：1426]
			 * @日期：2017年1月4日 下午5:25:37
			 * @修改记录: 修改者名字-修改日期-修改内容
			 * @param myForm
			 * @param request
			 * @return
			 * @throws Exception
			 * File 返回类型 
			 * @throws
			 */
			private File getSIWord(XsxxtyglForm myForm,HttpServletRequest request)throws Exception{
				XsxxtyglService service = new XsxxtyglService();
				String xh = myForm.getXh();
				User user = getUser(request);
				myForm.setUser(user);
				Map<String,Object> data = new HashMap<String,Object>();
				HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//学生基本信息
		 		
				//根据入学日期或年级和当前日期的年级换算
				Map<Integer,Character> njmcMap = xgxt.utils.date.DateUtils.getZHNumberMap();
				
				String nj = xsxxMap.get("nj");
				
				if(!StringUtil.isNull(nj)){
					Integer njValue = Integer.parseInt(nj);
					Calendar calendar = Calendar.getInstance();
					Integer year = calendar.get(Calendar.YEAR);
					Integer month = calendar.get(Calendar.MONTH)+1;
					Character njmc = month>=9?njmcMap.get(year-njValue+1):njmcMap.get(year-njValue);
					
					//将换算后年级名称加入数据填充map
					data.put("njmc", njmc);
				}
				
				//相关日期数据填充
				data.put("cy", xgxt.utils.date.DateUtils.getYear());
				data.put("zhDate", xgxt.utils.date.DateUtils.getZHDate());
				
				data.putAll(xsxxMap);
					
				File file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xssfzm.xml",xh+"-"+xsxxMap.get("xm"));
				return file;
			}

}
