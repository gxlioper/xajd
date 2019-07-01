package xgxt.jygl.tables;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;

/**
 * 就业管理-相关表格
 */
public class JyglTablesAction extends BasicAction {

	/**
	 * 学生列表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		JyglTablesForm model = (JyglTablesForm) form;
		JyglTablesService service = new JyglTablesService();
		String doType = request.getParameter("doType");
		String[] topTr = new String[]{"学号","姓名","年级", Base.YXPZXY_KEY+"名称","专业名称","班级名称"};
		
		if (QUERY.equals(doType)){
			//高级查询条件
			SearchService searchService = new SearchService();
			String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			//查询
			List<String[]> rs = service.getStudents(model,searchTjByUser+searchTj,inputV);
			request.setAttribute("rs", rs);
		}
		
		request.setAttribute("topTr",topTr);
		request.setAttribute("searchTj", model.getSearchModel());
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "njjs_jytjb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("showStudents");
	}
	
	
	/**
	 * 南京技师-就业推荐信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward njjsJytjbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String tableName = "xg_jygl_njjs_jytjb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		JyglTablesService service = new JyglTablesService();
		//保存
		if (SAVE.equals(doType)){
			insertOperation(request, tableName);
		}
		
		//修改
		if (UPDATE.equals(doType)){
			updateOperation(request, tableName);
		}
		
		//加载相关信息
		if (StringUtils.isNotNull(pkValue)){
//			CommService commService = new CommService();
			
			//加载学生基本信息
			selectPageDataByOne(request, "xsxxb", "view_xsxxb", pkValue);
			HashMap<String, String> stu = (HashMap<String, String>) request.getAttribute("rs");
//			if(StringUtils.isNotNull(stu.get("jtdz"))){//生源地代码转名称
//				stu.put("jtdz", commService.getSydmc(stu.get("jtdz"), "/", "/"));
//			}
			 request.setAttribute("stu", stu);
			 //家庭成员信息
			selectPageDataByOne(request, "xsxxb", "view_xsjtxx", pkValue);
			HashMap<String, String> jtcy = (HashMap<String, String>) request.getAttribute("rs");
			request.setAttribute("jtcy", jtcy);
			 
			//就业推荐表相关信息
			selectPageDataByOne(request, tableName, tableName, pkValue);
			request.setAttribute("zw", service.getXszwByXh(pkValue));
		}
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("path", "njjs_jytjb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("njjsJytjbUpdate");
	}
	
	
	/**
	 * 批量设置打印课程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plszDykc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		JyglTablesForm model = (JyglTablesForm) form;
		JyglTablesService service = new JyglTablesService();
		String doType = request.getParameter("doType");
		String bjdm = request.getParameter("bjdm");
		
		if ("xy".equals(userType)){
			model.setXydm(userDep);
		}
		
		if (SAVE.equals(doType)){
			request.setAttribute("message", service.savePlszDykc(model) ? SAVE_SUCCESS : SAVE_FAIL);
		}
	if(null!=bjdm&&!"".equals(bjdm)){
		request.setAttribute("kcList", service.getKcListByBjdm(bjdm));
	}else{
		request.setAttribute("kcList", service.getKcList());
	}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("plszDykc");
	}
	
	
	/**
	 * 单个设置打印课程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dgszDykc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xh = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		JyglTablesForm model = (JyglTablesForm) form;
		JyglTablesService service = new JyglTablesService();
		
		if (StringUtils.isNotNull(xh)){
			selectPageDataByOne(request, "xsxxb", "view_xsjbxx", xh);
		}
		
		if(SAVE.equals(doType)){
			request.setAttribute("message", service.saveDgszDykc(xh, model) ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		request.setAttribute("pkValue", xh);
		request.setAttribute("kcList", service.getKcListByXh(xh));
		return mapping.findForward("dgszDykc");
	}
	
	
	
	/**
	 * 南京技师就业推荐表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward printNjjsJytjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xg_jygl_njjs_jytjb";
		String xh = request.getParameter("xh");
		JyglTablesService service = new JyglTablesService();
		
		if (StringUtils.isNotNull(xh)){
			CommService commService = new CommService();
			
			//加载学生基本信息
			selectPageDataByOne(request, "xsxxb", "view_xsxxb", xh);
			HashMap<String, String> stu = (HashMap<String, String>) request.getAttribute("rs");
			if(StringUtils.isNotNull(stu.get("jtdz"))){//生源地代码转名称
				stu.put("jtdz", commService.getSydmc(stu.get("jtdz"), "/", "/"));
			}
			 request.setAttribute("stu", stu);
			 request.setAttribute("lsh", xh.substring(xh.length()-2));
			 
		 	//家庭成员信息
			selectPageDataByOne(request, "xsxxb", "view_xsjtxx", xh);
			HashMap<String, String> jtcy = (HashMap<String, String>) request.getAttribute("rs");
			request.setAttribute("jtcy", jtcy);
			 
			//就业推荐表相关信息
			selectPageDataByOne(request, tableName, tableName, xh);
			request.setAttribute("cjList",service.getXscjByXh(xh));
			request.setAttribute("zw", service.getXszwByXh(xh));
		}
		
		request.setAttribute("nd", Base.currNd);
		return mapping.findForward("printNjjsJytjb");
	}
	
	
	/**
	 * Ajax调用（查询学生已设置的课程）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXkkcForAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xh = request.getParameter("xh");
		JyglTablesService service = new JyglTablesService();
		
		response.getWriter().print(JSONArray.fromObject(service.getXscjByXh(xh)));
		return null;
	}
}
