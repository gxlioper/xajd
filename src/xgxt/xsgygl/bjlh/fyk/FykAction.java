package xgxt.xsgygl.bjlh.fyk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.xsgygl.bjlh.BjlhGyglForm;
import xgxt.xsgygl.bjlh.sjwh.SjwhModel;
import xgxt.xsgygl.bjlh.sjwh.SjwhService;
import xgxt.xsgygl.bjlh.ssfp.SsfpService;

public class FykAction extends DispatchAction {

	
	/**
	 * 房源分配结果
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fykManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_fpjg";
		String realTable = "";
//		String doType = request.getParameter("doType");

		String xydm = "";
		String lx = "";
		String isAdmin = "yes";
		String isXy = "no";
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

  		// 判断用户权限
		if (!( "admin".equalsIgnoreCase(userType))) {	
			lx = myForm.getLx();
			isAdmin = "no";
			if (userDep.equalsIgnoreCase(service.getBmdm("科研处"))) {
				lx = "研究生";	
			}else if (userDep.equalsIgnoreCase(service.getBmdm("成人教育处"))) {
				lx = "成教生";	
			}else if (userDep.equalsIgnoreCase(service.getBmdm("团委"))) {
				lx = "团委生";	
			}else if (userDep.equalsIgnoreCase(service.getBmdm("体育教学部"))) {
				lx = "体优生";	
			} else {
				if ("xx".equalsIgnoreCase(userType)) {	
					isAdmin = "yes";
				}else{
					lx = "全日制";
					isXy = "yes";
					myForm.setXydm(userDep);
				}
			}
			myForm.setLx(lx);
			
			if (!Base.isNull(lx) && !"全日制".equalsIgnoreCase(lx)) {
				isXy = "yes";
			}
		}
		
		BeanUtils.copyProperties(model, myForm);
		
		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go")){
			String[] colList = new String[] { "pk", "xymc", "xqmc", "ssbh",
					"cws", "bxyyzrs", "kcws", "zt" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
			
			xydm = model.getXydm();
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "bjlh_gygl_fpjg.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("isXy", isXy);
		request.setAttribute("isAdmin", isAdmin);
		// 设置列表值
		myForm.setXydm("");
		myForm.setZydm("");
		myForm.setBjdm("");
		service.setList(myForm, request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		myForm.setXydm(xydm);
		
		return mapping.findForward("fykManage");
	}
	
	/**
	 * 精简统计
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	@SuppressWarnings("unchecked")
	public ActionForward jjtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		FykService service = new FykService();
		SsfpService ssfpService = new SsfpService(); 
		SjwhService whservice = new SjwhService();
		String lx="";
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		
		String isAdmin = "yes";
		String isXy = "no";
		// 判断用户权限 "全日制", "团委", "体育教学部", "科研处", "成人教育处" 
		if (!( "admin".equalsIgnoreCase(userType))) {
			lx = myForm.getFbbj();
			if (userDep.equalsIgnoreCase(whservice.getBmdm("科研处"))) {
				lx = "0110";		
			}else if (userDep.equalsIgnoreCase(whservice.getBmdm("成人教育处"))) {
				lx = "0117";		
			}else if (userDep.equalsIgnoreCase(whservice.getBmdm("团委"))) {
				lx = "0202";		
			}else if (userDep.equalsIgnoreCase(whservice.getBmdm("体育教学部"))) {
				lx = "0405";		
			}else{
				if (!"xx".equalsIgnoreCase(userType)) {
					lx = "qrz";
					isXy = "yes";
					myForm.setXydm(userDep);
				}
			}
			myForm.setFbbj(lx);
			isAdmin = "no";
			
		}
		request.setAttribute("isXy", isXy);
		request.setAttribute("isAdmin", isAdmin);
		FykModel model = new FykModel();
		//myForm.setFbbj(request.getParameter("fbbj"));
		BeanUtils.copyProperties(model, myForm);
		List<HashMap<String, String>> xyList = ssfpService.getXyList();

		String query = request.getParameter("operType");
		
		HashMap<String, String> xyfjzsMap = new HashMap<String, String>();

		appendList(request, myForm);//加载楼栋，层数列表信息
		//查询数据操作
		if ("query".equalsIgnoreCase(query)) {
			List rs = service.queryTjxx(model, (List)request.getAttribute("xqList"), xyList,true);
			request.setAttribute("rs", rs);
			
		}
		request.setAttribute("sclxList", service.getSclxList());
		request.setAttribute("xyfjzsMap", xyfjzsMap);
		//排序后的学院列表
		request.setAttribute("xyList", xyList);
		// 存放访问路径求取读写权
		request.setAttribute("path", "bjlh_gygl_fyk.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("query", query);
		return mapping.findForward("jjtjManage");
	}
	
	/**
	 * 导出房源库查询数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expfpjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		FykService service = new FykService();
		SsfpService ssfpService = new SsfpService(); 
		SjwhService sjwhservice = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		FykModel model = new FykModel();
		BeanUtils.copyProperties(model, myForm);
		List<HashMap<String, String>> xyList = ssfpService.getXyList();
		List<HashMap<String, String>> xqList = sjwhservice.getSelectList(
				"dm_zju_xq", "dm", "xqmc");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		service.expQueryFytjxx(wwb, model, xqList, xyList);
		
		return mapping.findForward("");
	}
	
	/**
	 * 加载楼栋，层数，校区，寝室列表
	 * @param request
	 * @param myForm
	 */
	public void appendList(HttpServletRequest request, BjlhGyglForm myForm) {
		SjwhService service = new SjwhService();
		List<HashMap<String, String>> xqList = service.getSelectList(
				"dm_zju_xq", "dm", "xqmc");
		List<HashMap<String, String>> ldList = service.getSelectList("sslddmb",
				"lddm", "ldmc");
		List<HashMap<String, String>> csList = service.getCsList(myForm
				.getLddm());
		List<HashMap<String, String>> qsList = service.getQsList(myForm
				.getLddm(), myForm.getCs(), myForm.getFplx(), myForm.getXydm());
		request.setAttribute("xqList", xqList);
		request.setAttribute("ldList", ldList);
		request.setAttribute("csList", csList);
		request.setAttribute("qsList", qsList);
		request.setAttribute("tjlxList", service.getTjlxList());
		request.setAttribute("fpbjList", service.getFpbjList());
	}
	
	/**
	 * 图表统计
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward tbtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		String doType = request.getParameter("doType");
		String tableName = "view_bjlh_tjjbxx";
		
		SjwhService sjwhService = new SjwhService();
		FykService service = new FykService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		FykModel model = new FykModel();

//		String tableName = "";
//		String realTable = "";
		//String doType = request.getParameter("doType");

		List<Object> rs = new ArrayList<Object>();

//		String xydm = "";
		String lx = "";
		String isAdmin = "yes";
		String isXy = "no";
		
  		// 判断用户权限
		if (!( "admin".equalsIgnoreCase(userType))) {	
			lx = myForm.getLx();
			isAdmin = "no";
			if (userDep.equalsIgnoreCase(sjwhService.getBmdm("科研处"))) {
				lx = "研究生";	
			}else if (userDep.equalsIgnoreCase(sjwhService.getBmdm("成人教育处"))) {
				lx = "成教生";	
			}else if (userDep.equalsIgnoreCase(sjwhService.getBmdm("团委"))) {
				lx = "团委生";	
			}else if (userDep.equalsIgnoreCase(sjwhService.getBmdm("体育教学部"))) {
				lx = "体优生";	
			} else {
				if ("xx".equalsIgnoreCase(userType)) {	
					isAdmin = "yes";
				}else{
					lx = "全日制";
					isXy = "yes";
					myForm.setXydm(userDep);
				}
			}
			myForm.setLx(lx);
			
			if (!Base.isNull(lx) && !"全日制".equalsIgnoreCase(lx)) {
				isXy = "yes";
			}
		}
		
		BeanUtils.copyProperties(model, myForm);
		
		if (!Base.isNull(doType) && "print".equalsIgnoreCase(doType)) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			service.printTbtj(model, response.getOutputStream());
			return null;

		}
		
		//	图表统计
		if ((request.getParameter("go") != null) && request.getParameter("go").equalsIgnoreCase("go")) {
			rs = service.setTbtj(model);
		}
		
		// 存放访问路径求取读写权
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("tableName", tableName);
		request.setAttribute("rs", rs);
		request.setAttribute("isXy", isXy);
		request.setAttribute("isAdmin", isAdmin);
		
		service.setList(myForm, request);
		

		return mapping.findForward("tbtjManage");
	}
	
	/**
	 * 房源库信息查看
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fykUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "公寓管理 - 房源信息 - 查看";
		String key = request.getParameter("pk");
		String userType = session.getAttribute("userType").toString();
		// String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String tableName = "view_bjlh_ssxx";
		String realTable = "bjlh_ssxxb";
		
		doType = Base.isNull(doType) ? "add" : doType;

		SjwhService service = new SjwhService();
		FykService fykService = new FykService();
		
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// 查看或修改所选数据
		if ("view".equalsIgnoreCase(doType)) {
			String pk = "lddm||cs||qsh";
			String pkValue = key;
			String[] colList = new String[] { "lddm", "ldmc", "cs", "qsh",
					"cws", "fbbj", "qsdh", "sfbz", "bz", "xqdm", "xqmc" };
			rs = service.getGyglInfo(tableName, pk, pkValue, colList);
			
			colList = new String[] { "xh","xm","xb","lx","nj","xymc","zymc","bjmc","cwm" };
			ArrayList<String[]> rsList = fykService.getXszsList(colList, key);
			if (rsList != null && rsList.size() > 0) {
				List<HashMap<String, String>> topTr = SearchUtils.getTopTr("view_bjlh_xszsxx", colList, null);
				request.setAttribute("topTr", topTr);
				request.setAttribute("rsList", rsList);
				request.setAttribute("rsNum", rsList.size());
			}
		}
		
		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("rs", rs);
		// 设置列表值
		service.setList(myForm, request);

		return mapping.findForward("fykUpdate");
	}
}
