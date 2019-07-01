package xgxt.xsxx.pdk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.comm.XsxxCommForm;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * <p>
 * 	<title>学生信息个性化功能</title>
 *  
 *  请勿随便通用，否则造成一切后果自负
 * </p>
 * @author 鲁大
 * @version 1.0
 */
public class MoralCardAction extends BasicAction {
	
	private static final String QUERY = "query";
	private static final String SAVE = "save";

	
	/**
	 * 显示学生基本信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxCommForm model = (XsxxCommForm) form;
		MoralCardService service = new MoralCardService();
		String doType = request.getParameter("doType");
		String[] colList = new String[]{"xh","xm","nj","xymc","zymc","bjmc","xz"};
		List<String[]> rs = new ArrayList<String[]>();
		String mkmc = request.getParameter("mkmc");
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		if ("stu".equals(userType)){
			return viewMoralCard(mapping, form, request, response);
		}
		
		if (QUERY.equals(doType)){
			rs = service.getStudents(model,searchTjByUser+searchTj,inputV,colList);
		} else {
			model.getPages().setMaxRecord(0);
		}
		
		if ("njjs".equals(mkmc)){
			request.setAttribute("path", "bbdy_njjs.do");
		} else {
			request.setAttribute("path", "moralCardIndex.do");
		}
		
		
		request.setAttribute("mkmc", mkmc);
		request.setAttribute("searchTj", model.getSearchModel());
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getColumn("view_xsjbxx", colList));
		request.setAttribute("maxNum", model.getPages().getPageSize());
		return mapping.findForward("showStudents");
	}
	
	
	
	/**
	 * 德育等第维护界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward dyddPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MoralCardService service = new MoralCardService();
		String xh = request.getParameter("pkValue");
		//根据学号加载已维护的德育等第
		if (StringUtils.isNotNull(xh)){
			selectPageDataByOne(request, "xsxxb", "view_xsjbxx", xh);
			request.setAttribute("dyddList", service.getDyddListByXh(xh));
		}
		
		return mapping.findForward("dyddPage");
	}
	
	
	
	/**
	 * 保存德育等第
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问学生信息-品德卡-学生品德卡-保存德育等第XH:{pkValue}")
	public ActionForward saveDydd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MoralCardService service = new MoralCardService();
		String xh = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String[] xqmc = request.getParameterValues("xqmc");
		String[] pjjg = request.getParameterValues("pjjg");
		String[] xssx = request.getParameterValues("xssx");
		
		//保存德育等第
		if (SAVE.equals(doType)){
			boolean result = service.saveDydd(xh, xqmc, pjjg,xssx);
			request.setAttribute("message", result ? "保存成功" : "保存失败");
		}
		return mapping.findForward("dyddPage");
	}
	
	
	
	/**
	 * 奖惩记录维护界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcjlPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MoralCardService service = new MoralCardService();
		String xh = request.getParameter("pkValue");
		
		//根据学号加载已维护的德育等第
		if (StringUtils.isNotNull(xh)){
			selectPageDataByOne(request, "xsxxb", "view_xsjbxx", xh);
			request.setAttribute("jcjlList", service.getJcjlListByXh(xh));
		}
		
		return mapping.findForward("jcjlPage");
	}
	
	
	
	/**
	 * 保存奖惩记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问学生信息-品德卡-学生品德卡-保存奖惩记录XH:{pkValue}")
	public ActionForward saveJcjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MoralCardService service = new MoralCardService();
		String xh = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String[] rq = request.getParameterValues("rq");
		String[] zy = request.getParameterValues("zy");
		String[] bz = request.getParameterValues("bz");
		//保存奖惩记录
		if (SAVE.equals(doType)){
			boolean result = service.saveJcjl(xh, rq, zy, bz);
			request.setAttribute("message", result ? "保存成功" : "保存失败");
		}		
		return mapping.findForward("jcjlPage");
	}
	
	
	
	/**
	 * 品德卡详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward viewMoralCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String pkValue = request.getParameter("pkValue");
		String xhArr = request.getParameter("xhArr");
		String flg = request.getParameter("flg");
		String shr = request.getParameter("shr");
		
		MoralCardService service = new MoralCardService();
		
		if ("stu".equals(userType)){
			pkValue = userName;
		}
		
		if (StringUtils.isNotNull(pkValue)){
			CommService commService = new CommService();
			
			//家庭成员信息
			selectPageDataByOne(request, "xsxxb", "view_xsjtxx", pkValue);
			HashMap<String, String> jtcy = (HashMap<String, String>) request.getAttribute("rs");
			request.setAttribute("jtcy", jtcy);
			
			//学生基本信息
			selectPageDataByOne(request, "xsxxb", "view_xsxxb", pkValue);
			HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
			if(StringUtils.isNotNull(rs.get("pycc"))){//培养层次
				selectPageDataByOne(request, "xg_xsxx_pyccdmb", "xg_xsxx_pyccdmb", rs.get("pycc"));
				HashMap<String, String> pyrs = (HashMap<String, String>) request.getAttribute("rs");
				rs.put("pyccmc", pyrs.get("pyccmc"));
			}
			if(StringUtils.isNotNull(rs.get("syd"))){//生源地代码转名称
			   rs.put("sydmc", commService.getSydmc(rs.get("syd"), "/", "/"));
			}
			request.setAttribute("rs", rs);
			//德育等第记录
			request.setAttribute("dyddList", service.getDyddListByXh(pkValue));
			//奖惩记录
			request.setAttribute("pjpyList", service.getPjpyList(pkValue));//评奖记录
			//request.setAttribute("rychList", service.getRychList(pkValue));//评奖记录old
			request.setAttribute("wjcfList", service.getWjcfList(pkValue));//违纪记录
			request.setAttribute("jcwjcfList", service.getJcWjcfList(pkValue));//解除违纪记录
			//request.setAttribute("xszzList", commService.getXszzList(pkValue));//资助记录old
			request.setAttribute("xszzList", service.getXszzList(pkValue));//资助记录new
			//request.setAttribute("jcjlList", service.getJcjlListByXh(pkValue));
		}
		
		request.setAttribute("xhArr", xhArr);
		request.setAttribute("flg", flg);
		request.setAttribute("shr", StringUtils.isNotNull(shr) ? shr : "");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xxdm", Base.xxdm);
		
		Date d = new Date();
		request.setAttribute("now", new SimpleDateFormat("yyyy年MM月dd日").format(d));
		return mapping.findForward("viewMoralCard");
	}



	/**
	 * <p>南京技师-学生登记表</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward xsdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String pkValue = request.getParameter("pkValue");
		if (StringUtils.isNotNull(pkValue)){
			CommService commService = new CommService();
			
			//家庭成员信息
			selectPageDataByOne(request, "xsxxb", "view_xsjtxx", pkValue);
			HashMap<String, String> jtcy = (HashMap<String, String>) request.getAttribute("rs");
			request.setAttribute("jtcy", jtcy);
			
			//学生基本信息
			selectPageDataByOne(request, "xsxxb", "view_xsxxb", pkValue);
			HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
			if(StringUtils.isNotNull(rs.get("syd"))){//生源地代码转名称
			   rs.put("sydmc", commService.getSydmc(rs.get("syd"), "/", "/"));
			   rs.put("hkszd", commService.getSydmc(rs.get("hkszd"), "/", "/"));
			   request.setAttribute("rs", rs);
			}
		}
		
		return mapping.findForward("xsdjb");
	}
	
	/**
	 * <p>南京技师-学生学籍卡</p>
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * author lyl
	 */
	@SuppressWarnings("unchecked")
	public ActionForward xjk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String pkValue = request.getParameter("pkValue");
		if (StringUtils.isNotNull(pkValue)){
			CommService commService = new CommService();
			
			//家庭成员信息
			selectPageDataByOne(request, "xsxxb", "view_xsjtxx", pkValue);
			HashMap<String, String> jtcy = (HashMap<String, String>) request.getAttribute("rs");
			request.setAttribute("jtcy", jtcy);
			System.out.println(jtcy.get("yb"));
			
			//学生基本信息
			selectPageDataByOne(request, "xsxxb", "view_xsxxb", pkValue);
			HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
			if(StringUtils.isNotNull(rs.get("syd"))){//生源地代码转名称
			   rs.put("sydmc", commService.getSydmc(rs.get("syd"), "/", "/"));
			   rs.put("hkszd", commService.getSydmc(rs.get("hkszd"), "/", "/"));
			   request.setAttribute("rs", rs);
			}
			if(rs.get("xz").equals("5")||rs.get("xz").equals("6")){
				return mapping.findForward("xjk_five");
			}
			if(rs.get("xz").equals("7")){ 
				return mapping.findForward("xjk_seven");
			}
		}
		
		return mapping.findForward("xjk_three");
	}
	

	/**
	 * 湖北交通学籍确认单
	 */
	public ActionForward xjqrdPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] xh = request.getParameterValues("pk");
		MoralCardService service = new MoralCardService();
		
		List<HashMap<String,String>> rs = service.getXjxxList(xh);
		
		request.setAttribute("rs", rs);
		return mapping.findForward("xjqrdPrint");
	}
	
	
	
	/**
	 * 德育等第管理
	 * @return
	 * @throws Exception
	 */
	public ActionForward dyddManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxCommForm model = (XsxxCommForm) form;
		MoralCardService service = new MoralCardService();
		
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] input = SearchService.getTjInput(model.getSearchModel());
		
		List<String[]> rs = service.getDyddList(model, searchTjByUser+searchTj, input);	
		request.setAttribute("rs", rs);
		
		request.setAttribute("path", "dyddgl.do");
		FormModleCommon.commonRequestSet(request);
		String[] topTr = new String[]{"学号","姓名","年级",Base.YXPZXY_KEY,"专业","班级","学期","评分/等级"};
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("searchTj", model.getSearchModel());
		return mapping.findForward("dyddManage");
	}
	
	
	
	/**
	 * 德育等第导出
	 * @throws Exception
	 */
	public ActionForward expDydd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxCommForm model = (XsxxCommForm) form;
		MoralCardService service = new MoralCardService();
		
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] input = SearchService.getTjInput(model.getSearchModel());
		
		model.getPages().setPageSize(Integer.MAX_VALUE);
		String[] topTr = new String[]{"学号","姓名","年级",Base.YXPZXY_KEY,"专业","班级","学期","评分/等级"};
		List<String[]> rs = service.getDyddList(model, searchTjByUser+searchTj, input);	
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, topTr, topTr, response.getOutputStream());
		
		return mapping.findForward("");
	}
}
