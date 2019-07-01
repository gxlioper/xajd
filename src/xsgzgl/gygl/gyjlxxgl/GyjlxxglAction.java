package xsgzgl.gygl.gyjlxxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.gyjlxxglnew.GyjlxxglNewForm;
import xsgzgl.gygl.gyjlxxglnew.GyjlxxglNewService;
import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.log.SystemLog;

public class GyjlxxglAction extends BasicAction{
	/**
	 * 公寓纪律信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "gyglnew_gyjlgl_gyjlgl.do");
		GyjlxxglForm myForm = (GyjlxxglForm) form;
		GyjlxxglService service = new GyjlxxglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		
		// 结果集显示字段
		String[] colList = service.getTopTr("jlxx");

		// =============== 执行查询操作 ===========
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", service.getJlxxList(myForm, request));
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("num", myForm.getPages().getMaxRecord());
		request.setAttribute("topTr", service.getToplist(colList));
		service.setRequestValue(rForm, user,request);
		
		request.setAttribute("tableName", "xg_view_gygl_new_xszsgl");	// 导出表
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("gyjlxxManage");
	}
	
	
	/**
	 * 公寓纪律学生查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxsCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "gyglnew_gyjlgl_gyjlgl.do");
		GyjlxxglForm myForm = (GyjlxxglForm) form;
		GyjlxxglService service = new GyjlxxglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		
		// 结果集显示字段
		String[] colList = service.getTopTr("jlxx");

		// =============== 执行查询操作 ===========
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", service.getJlxxList(myForm, request));
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("num", myForm.getPages().getMaxRecord());
		request.setAttribute("topTr", service.getToplist(colList));
		service.setRequestValue(rForm, user,request);
		
		request.setAttribute("tableName", "xg_view_gygl_new_xszsgl");	// 导出表
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("gyjlxsCx");
	}
	
	/**
	 * 违纪信息编辑
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律信息维护-修改PK:{xh}")
	public ActionForward gyjlxxEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");	
		
		GyjlxxglForm myForm = (GyjlxxglForm) form;
		GyjlxxglService service = new GyjlxxglService();
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveJlxx(myForm, request);
			request.setAttribute("message", message);
		}		
		request.setAttribute("jldlList", service.getJldlList(request));
		request.setAttribute("jllbList", new ArrayList<HashMap<String,String>>());
		return mapping.findForward("gyjlxxEdit");
	}
	/**
	 * 单个学生违纪信息查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward gyjlxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 主键
		String pkValue = request.getParameter("pkValue");
		
		GyjlxxglService service = new GyjlxxglService();
		request.setAttribute("rs", service.getXsxx(pkValue,request));
		request.setAttribute("rsArrList", service.getOneWjxxList(pkValue,"", request));
		return mapping.findForward("gyjlxxView");
	}*/
	/**
	 * 公寓纪律信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlcxQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_gyjlgl_gyjlcx.do");
		String doType=request.getParameter("doType");
		GyjlxxglForm myForm = (GyjlxxglForm) form;
		GyjlxxglService service = new GyjlxxglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		if("del".equals(doType)){
			String message = service.delWjxx(myForm,request);
			request.setAttribute("message", message);
			
		}
		// 结果集显示字段
		String[] colList = service.getTopTr("wjxx");

		// =============== 执行查询操作 ===========
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", service.getWjxxList(myForm, request,"wjxx"));
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("num", myForm.getPages().getMaxRecord());
		request.setAttribute("topTr", service.getToplist(colList));
		service.setRequestValue(rForm, user,request);
		
		request.setAttribute("tableName", "xg_view_gygl_new_gyjlb");	// 导出表
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("gyjlcxQuery");
	}
	
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlcxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		// 主键
		String xh = request.getParameter("xh");
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		String wjsj = request.getParameter("wjsj");
		String gyjllbdm = request.getParameter("gyjllbdm");
		String[] pkValue = new String[]{xh,wjsj,gyjllbdm};
		GyjlxxglService service = new GyjlxxglService();
		request.setAttribute("rsArrList", service.getOneWjxxList(xh,wjsj, request));
		request.setAttribute("rs", service.getXswjxx(pkValue,request));
		request.setAttribute("xxdm", Base.xxdm);
		if ("clview".equalsIgnoreCase(request.getParameter("act"))) {
			return mapping.findForward("gyjlclView");
		}
		return mapping.findForward("gyjlcxView");
	}
	
	/**
	 * 增加公寓纪律信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律信息维护-增加XH:{xh},XN:{xn},XQ:{xq},JLDLDM:{jldldm},JLLBDM:{jllbdm},WJSJ:{wjsj}")
	public ActionForward gyjlxxZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyjlxxglForm myForm = (GyjlxxglForm) form;
		User user = getUser(request);
		String xh = request.getParameter("xh");
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		
		xh = StringUtil.isNull(xh) ? (request.getParameter("wjxh") !=null ? request.getParameter("wjxh").replaceAll("!!@@!!", "") : "") : xh;
		String doType = request.getParameter("doType");
		GyjlxxglService service = new GyjlxxglService();
		XsxxglService stuService = new XsxxglService();
		HashMap<String,String> rs = stuService.selectStuinfoGy(xh);
		rs.put("xn", Base.currXn);
		rs.put("wjxq", Base.currXq);
		rs.put("xq", CommonQueryDAO.getXqMc(Base.currXq));
		request.setAttribute("rs", rs);
		if("save".equals(doType)){
			
			String wjxh = request.getParameter("wjxh");
			myForm.setXh(wjxh);
			String message = service.saveGyjlxx(myForm,request);
			request.setAttribute("message", message);
			
		}
		request.setAttribute("jldlList", service.getJldlList(request));
		request.setAttribute("jllbList", new ArrayList<HashMap<String,String>>());
		//设置学年学期列表
		request.setAttribute("jllb", myForm.getJllbdm());
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("gyjlxxZj");
	}
	
	/**
	 * 修改公寓纪律信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律信息维护-修改PK:{xh},JLDLDM:{jldldm},JLLBDM:{jllbdm},WJSJ:{wjsj}")
	public ActionForward gyjlxxXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String xh = request.getParameter("xh");
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		
		String doType = request.getParameter("doType");
		GyjlxxglService service = new GyjlxxglService();
//		XsxxglService stuService = new XsxxglService();
		String jlsj = request.getParameter("jlsj");
		String gyjllbdm = request.getParameter("gyjllbdm");
		String[] pkValue = new String[]{xh,jlsj,gyjllbdm};
		HashMap<String,String> rs = (HashMap<String, String>) service.getXswjxx(pkValue,request);
//		HashMap<String,String> rs = stuService.selectStuinfoGy(xh);
/*		rs.put("xn", Base.currXn);
		rs.put("xq", Base.currXq);*/
		rs.put("xn", rs.get("wjxn"));
		rs.put("xq", rs.get("wjxq"));
		rs.put("jldldm", rs.get("gyjllbdldm"));
		rs.put("jllbdm", rs.get("gyjllbdm"));
		rs.put("bz", rs.get("bz"));
		request.setAttribute("rs", rs);
		if("update".equals(doType)){
			GyjlxxglForm myForm = (GyjlxxglForm) form;
			myForm.setXh(xh);
			String message = service.updateGyjlxx(myForm,request);
			request.setAttribute("message", message);
		}
		request.setAttribute("jldlList", service.getJldlList(request));
		request.setAttribute("jllbList", new ArrayList<HashMap<String,String>>());
		request.setAttribute("xxdm", Base.xxdm);
		//设置学年学期列表
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("gyjlxxXg");
	}
	
	/**
	 * 获得一个学生信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String xh = request.getParameter("xh");
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		GyjlxxglService service = new GyjlxxglService();

		Map<String, String> map = service.getXsxx(xh,request);
		String json = JSONObject.fromObject(map).toString();	
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	/**
	 * 联动获得纪律类别list
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJllbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jldldm = request.getParameter("jldldm");
		GyjlxxglService service = new GyjlxxglService();
		List<HashMap<String,String>> jllbList = service.getJllbList(jldldm, request);
		String json = JSONArray.fromCollection(jllbList).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * 联动获得纪律类别list
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getTqs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String xh = request.getParameter("xh");
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		GyjlxxglService service = new GyjlxxglService();
		List<HashMap<String,String>> tqsList = service.getTqs(xh, request);
		String json = JSONArray.fromCollection(tqsList).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * 
	 * @描述:浙江传媒个性化，根据违纪时间获取学生对应请假时间
	 * @作者：cq [工号：785]
	 * @日期：2017-6-13 下午05:49:46
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
	public ActionForward getQjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String xh = request.getParameter("xh");
		String wjsj = request.getParameter("wjsj");
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		GyjlxxglService service = new GyjlxxglService();
		List<HashMap<String,String>> qjInfoList = service.getQjInfo(xh, wjsj);
		String json = JSONArray.fromCollection(qjInfoList).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * 公寓纪律信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = "gygl_gyjlglnew_gyjlxxcl.do")
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律信息维护-处理PK:{pkStr}")
	public ActionForward gyjlxxCl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType=request.getParameter("doType");
		GyjlxxglForm myForm = (GyjlxxglForm) form;
		GyjlxxglService service = new GyjlxxglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		if("cl".equals(doType)){
			boolean result = service.plclGyjlxx(myForm);
			request.setAttribute("message", result ? "操作成功！" : "操作失败！");
		}
		
		
		// 结果集显示字段
		String[] colList = service.getTopTr("wjcl");

		// =============== 执行查询操作 ===========
		//String[] search_tj_clzt=myForm.getSearchModel().getSearch_tj_clzt();
		myForm.getSearchModel().setPath("gyglnew_gyjlgl_gyjlcl.do");
//		if(search_tj_clzt==null || search_tj_clzt.length==0){
//			search_tj_clzt=new String[]{"未处理"};
//			myForm.getSearchModel().setSearch_tj_clzt(search_tj_clzt);
//		}
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", service.getWjxxList(myForm, request,"wjcl"));
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("num", myForm.getPages().getMaxRecord());
		request.setAttribute("topTr", service.getToplist(colList));
		service.setRequestValue(rForm, user,request);
		request.setAttribute("cflbList", service.getCflbList());
		request.setAttribute("tableName", "xg_view_gygl_new_gyjlb");	// 导出表
		request.setAttribute("path", "gyglnew_gyjlgl_gyjlcl.do");
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("gyjlxxCl");
	}
	
	/**
	 * 公寓纪律信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律信息维护-删除PK:{div_pkValue}")
	public ActionForward gyjlxxWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType=request.getParameter("doType");
		GyjlxxglForm myForm = (GyjlxxglForm) form;
		GyjlxxglService service = new GyjlxxglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		if("del".equals(doType)){
			String message = service.delWjxx(myForm,request);
			request.setAttribute("message", message);
			
		}
		
		
		// 结果集显示字段
		String[] colList = service.getTopTr("wjcl");

		// =============== 执行查询操作 ===========
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", service.getWjxxList(myForm, request,"wjcl"));
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("num", myForm.getPages().getMaxRecord());
		request.setAttribute("topTr", service.getToplist(colList));
		service.setRequestValue(rForm, user,request);
		
		request.setAttribute("tableName", "xg_view_gygl_new_gyjlb");	// 导出表
		request.setAttribute("path", "gyglnew_gyjlgl_gyjlcl.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("gyjlxxwh");
	}
	
	
	
	
	/**
	 * 修改公寓纪律信息处理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律信息维护-处理PK:{xh}")
	public ActionForward gyjlxxClwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);

		String xh = request.getParameter("xh");

		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		

		String doType = request.getParameter("doType");
		GyjlxxglService service = new GyjlxxglService();
//		XsxxglService stuService = new XsxxglService();
		String jlsj = request.getParameter("jlsj");
		String gyjllbdm = request.getParameter("gyjllbdm");
		String[] pkValue = new String[]{xh,jlsj,gyjllbdm};
		HashMap<String,String> rs = (HashMap<String, String>) service.getXswjxx(pkValue,request);
//		HashMap<String,String> rs = stuService.selectStuinfoGy(xh);
/*		rs.put("xn", Base.currXn);
		rs.put("xq", Base.currXq);*/
		rs.put("xn", rs.get("wjxn"));
		rs.put("xq", rs.get("xqmc"));
		
		rs.put("jldldm", rs.get("gyjllbdldm"));
		rs.put("jllbdm", rs.get("gyjllbdm"));
		request.setAttribute("rs", rs);
		request.setAttribute("div_pkValue", jlsj+"!!shen!!"+xh+"!!shen!!"+gyjllbdm);
		GyjlxxglNewService gyjlxxglNewService = new GyjlxxglNewService();
		GyjlxxglNewForm model = new GyjlxxglNewForm();
		model.setXh(xh);
		request.setAttribute("rsArrList", gyjlxxglNewService.getWjxxList(model));
		if("cl".equals(doType)){
			GyjlxxglForm myForm = (GyjlxxglForm) form;
			String sfcl=myForm.getSfcl();
			if("bcl".equalsIgnoreCase(sfcl)){
				myForm.setCljg("");
			}
			
			myForm.setXh(xh);
			
			boolean flag= service.saveCljg(myForm,user);
			
			String message="处理失败！";
			if(flag){
				message="处理成功！";
			}
			request.setAttribute("message", message);
		}
		request.setAttribute("jldlList", service.getJldlList(request));
		// 处分类别列表
		request.setAttribute("cflbList", service.getCflbList());
		request.setAttribute("jllbList", new ArrayList<HashMap<String,String>>());
		request.setAttribute("xxdm", Base.xxdm);
		//设置学年学期列表
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("gyjlxxClwh");
	}
	
	/**
	 * 公寓纪律信息审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律信息审核-审核PK:{div_pkValue}")
	public ActionForward gyjlSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType=request.getParameter("doType");
		GyjlxxglForm myForm = (GyjlxxglForm) form;
		GyjlxxglService service = new GyjlxxglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		
		// 公寓纪律审核
		if("sh".equals(doType)){
			boolean flag = service.saveShzt(myForm,user);
			
			String message="审核失败！";
			if(flag){
				message="审核成功！";
			}
			request.setAttribute("message", message);
		}
		// 结果集显示字段
		String[] colList = service.getTopTr("shxx");
		
		// =============== 执行查询操作 ===========
		//String[] search_tj_shzt1=myForm.getSearchModel().getSearch_tj_shzt1();
		myForm.getSearchModel().setPath("gyglnew_gyjlgl_gyjlsh.do");
//		if(search_tj_shzt1==null || search_tj_shzt1.length==0){
//			search_tj_shzt1=new String[]{"未审核"};
//			myForm.getSearchModel().setSearch_tj_shzt1(search_tj_shzt1);
//		}
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", service.getGyjlShList(myForm, user,request));
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("num", myForm.getPages().getMaxRecord());
		request.setAttribute("topTr", service.getToplist(colList));
		service.setRequestValue(rForm, user,request);
		
		request.setAttribute("tableName", "");	// 导出表
		request.setAttribute("path", "gyglnew_gyjlgl_gyjlsh.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("gyjlSh");
	}
	
	/**
	 * 审核公寓纪律信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律信息审核-审核PK:{div_pkValue}")
	public ActionForward gyjlClsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		String xh = request.getParameter("xh");
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		String doType = request.getParameter("doType");
		GyjlxxglService service = new GyjlxxglService();
		GyjlxxglForm myForm=(GyjlxxglForm)form;
//		XsxxglService stuService = new XsxxglService();
		String jlsj = request.getParameter("jlsj");
		String gyjllbdm = request.getParameter("gyjllbdm");
		String[] pkValue = new String[]{xh,jlsj,gyjllbdm};
		HashMap<String,String> rs = (HashMap<String, String>) service.getXswjxx(pkValue,request);
//		HashMap<String,String> rs = stuService.selectStuinfoGy(xh);
/*		rs.put("xn", Base.currXn);
		rs.put("xq", Base.currXq);*/
		rs.put("xn", rs.get("wjxn"));
		rs.put("xq", rs.get("wjxq"));
		rs.put("jldldm", rs.get("gyjllbdldm"));
		rs.put("jllbdm", rs.get("gyjllbdm"));
		request.setAttribute("rs", rs);

		// 公寓纪律审核
		if("sh".equals(doType)){
			boolean flag = service.saveShzt(myForm,user);
			
			String message="审核失败！";
			if(flag){
				message="审核成功！";
			}
			request.setAttribute("message", message);
		}
		request.setAttribute("jldlList", service.getJldlList(request));
		request.setAttribute("jllbList", new ArrayList<HashMap<String,String>>());
		GyjlxxglNewService gyjlxxglNewService = new GyjlxxglNewService();
		GyjlxxglNewForm model = new GyjlxxglNewForm();
		model.setXh(xh);
		request.setAttribute("rsArrList", gyjlxxglNewService.getWjxxList(model));
		request.setAttribute("xxdm", Base.xxdm);
		//设置学年学期列表
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("gyjlClsh");
	}
	
	/**
	 * 学生纪律信息查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjlxxck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String xh = request.getParameter("xh");
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}

		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		GyjlxxglService service = new GyjlxxglService();
		String jlsj = request.getParameter("jlsj");
		String gyjllbdm = request.getParameter("gyjllbdm");
		String[] pkValue = new String[]{xh,jlsj,gyjllbdm};
		HashMap<String,String> rs = (HashMap<String, String>) service.getXswjxx(pkValue,request);
		rs.put("xn", rs.get("wjxn"));
		rs.put("xq", rs.get("wjxq"));
		rs.put("jldldm", rs.get("gyjllbdldm"));
		rs.put("jllbdm", rs.get("gyjllbdm"));
		request.setAttribute("rs", rs);
		request.setAttribute("jldlList", service.getJldlList(request));
		request.setAttribute("jllbList", new ArrayList<HashMap<String,String>>());
		GyjlxxglNewService gyjlxxglNewService = new GyjlxxglNewService();
		GyjlxxglNewForm model = new GyjlxxglNewForm();
		model.setXh(xh);
		request.setAttribute("rsArrList", gyjlxxglNewService.getWjxxList(model));
		//设置学年学期列表
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("xsjlxxCk");
	}
	
}
