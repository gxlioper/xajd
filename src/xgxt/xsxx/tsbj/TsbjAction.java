package xgxt.xsxx.tsbj;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.search.SearchService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;

/**
 * 特色班级
 * @author Penghui.Qu
 */
public class TsbjAction extends BasicAction {

	
	/**
	 * 特色班级管理
	 */
	public ActionForward tsbjgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsbjForm model = (TsbjForm) form;
		String[] topTr = new String[]{"班级代码","班级名称","创建人","创建时间","班级人数"};
		
		if (QUERY.equalsIgnoreCase(model.getDoType())){
			TsbjService service = new TsbjService();
			
			List<String[]> rs = service.getTsbj(model);
			request.setAttribute("rs", rs);
		}
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "tsbjgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tsbjgl");
	}

	
	
	/**
	 * 特色班级删除
	 */
	public ActionForward tsbjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsbjForm model = (TsbjForm) form;
		String tableName = "xg_xsxx_tsbjb";
		
		deleteOperation(request, tableName);
		
		model.setDoType(QUERY);
		return tsbjgl(mapping, form, request, response);
	}
	
	
	
	
	/**
	 * 特色班级增加
	 */
	public ActionForward tsbjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsbjForm model = (TsbjForm) form;
		String tableName = "xg_xsxx_tsbjb";
		
		//保存
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			insertOperation(request, tableName);
		}
		
		return mapping.findForward("tsbjAdd");
	}
	
	
	/**
	 * 特色班级学生管理
	 */
	public ActionForward tsbjxsgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsbjForm model = (TsbjForm) form;
		TsbjService service = new TsbjService();
		String[] topTr = new String[]{"特色班级","学号","姓名","加入时间"};
		
		if (QUERY.equalsIgnoreCase(model.getDoType())){
			SearchService searchService = new SearchService();
			String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			String[] colList = new String[]{"pkValue","tsbjmc","xh","xm","jrsj"};
			
			List<String[]> rs = service.getTsbjStu(model, searchTjByUser+searchTj, inputV, colList);
			request.setAttribute("rs", rs);
		}
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("searchTj", model.getSearchModel());
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("tsbjList", service.getTsbjList());
		request.setAttribute("path", "tsbjxsgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tsbjxsgl");
	}
	
	
	
	/**
	 * 特色班级学生设置
	 */
	public ActionForward tsbjxssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsbjForm model = (TsbjForm) form;
		TsbjService service = new TsbjService();
		
		//if (QUERY.equals(model.getDoType())){
			//高级查询
			SearchService searchService = new SearchService();
			String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			String[] colList = new String[]{"xh","xm","nj","xymc","bjmc"};
			
			List<String[]> rs = service.getStudents(model, searchTjByUser+searchTj, inputV, colList);
			request.setAttribute("rs", rs);
		//}
		
		request.setAttribute("tsbjList", service.getTsbjList());
		request.setAttribute("searchTj", model.getSearchModel());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tsbjxssz");
	}
	
	
	
	/**
	 * 特色班级学生保存
	 */
	public ActionForward tsbjxsbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TsbjForm model = (TsbjForm) form;
		TsbjService service = new TsbjService();
		
		request.setAttribute("message", service.saveTsbjStu(model) ? SAVE_SUCCESS : SAVE_FAIL);
		return tsbjxssz(mapping, form, request, response);
	}
	
	
	/**
	 * 删除特色班级学生
	 */
	public ActionForward delTsbjXs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsbjForm model = (TsbjForm) form;
		String tableName = "xg_xsxx_tsbjxsb";
		
		deleteOperation(request, tableName);
		
		model.setDoType(QUERY);
		return tsbjxsgl(mapping, form, request, response);
	}
	
	
	
	/**
	 * 特色班级单条显示
	 */
	public ActionForward tsbjView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xg_xsxx_tsbjb";
		String viewName = "xg_view_xsxx_tsbj";
		String pkValue = request.getParameter("pkValue");
		
		if (StringUtils.isNotNull(pkValue)){
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		return mapping.findForward("tsbjView");
	}
	
	
	/**
	 * 特色班级修改
	 */
	public ActionForward tsbjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		String tableName = "xg_xsxx_tsbjb";
		
		updateOperation(request, tableName);
		
		return mapping.findForward("tsbjView");
	}
	
}
