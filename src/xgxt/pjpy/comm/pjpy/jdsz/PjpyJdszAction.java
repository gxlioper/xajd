package xgxt.pjpy.comm.pjpy.jdsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.xmsz.PjpyXmszService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * <p>评奖评优-项目兼得设置</p>
 * @author 鲁大
 */
public class PjpyJdszAction extends BasicAction {
	
	/**
	 * 项目兼得设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jdszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyJdszForm myForm = (PjpyJdszForm) form;
		PjpyJdszService service = new PjpyJdszService();
		PjpyXmszService xmszService = new PjpyXmszService();
		String doType = request.getParameter("doType");
		String[] topTr = new String[]{"项目名称","不可兼得项目"};
		
		if (DEL.equals(doType)){
			String[] xmdm = request.getParameterValues("primarykey_cbv");
			request.setAttribute("message", service.delJdsz(xmdm) ? DEL_SUCCESS : DEL_FAIL);
			//doType = QUERY;
		}
		
		//if (QUERY.equals(doType)){
		List<HashMap<String,String>> rs = service.fjdxmQuery(myForm);
		//}
		
		
		xmszService.initList(request);
		request.setAttribute("pjxtszModel", PjxtszModel.pjxtszModel);
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("path", "pjpy_xmsz_jdsz.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("jdszManage");
	}
	
	
	
	/**
	 * 选择评奖项目
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xzxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmszService xmszService = new PjpyXmszService();
		PjpyCommService commService = new PjpyCommService();
		
		xmszService.initList(request);
		request.setAttribute("xmList", commService.getPjxmList(PjxtszModel.pjxtszModel));
		request.setAttribute("pjxtszModel",PjxtszModel.pjxtszModel);
		return mapping.findForward("xzxm");
	}
	
	
	
	/**
	 * 项目兼得设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jdsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyJdszForm myForm = (PjpyJdszForm) form;
		PjpyCommService commService = new PjpyCommService();
		PjpyJdszService service = new PjpyJdszService();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		if (StringUtils.isNotNull(pkValue)){
			myForm.setXmdm(pkValue);
		}
		
		
		if (SAVE.equals(doType)){
			request.setAttribute("message", service.saveJdsz(myForm) ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("xmList", commService.getPjxmList(PjxtszModel.pjxtszModel));
		return mapping.findForward("jdsz");
	}
	
	/**
	 * 项目兼得设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jdszFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyJdszForm myForm = (PjpyJdszForm) form;
		PjpyCommService commService = new PjpyCommService();
		PjpyJdszService service = new PjpyJdszService();
		String doType = request.getParameter("doType");
		String xmdm = request.getParameter("xmdm");
		if (StringUtils.isNotNull(xmdm)){
			myForm.setXmdm(xmdm);
		}
		
		
		if (SAVE.equals(doType)){
			request.setAttribute("message", service.saveJdsz(myForm) ? SAVE_SUCCESS : SAVE_FAIL);
			return new ActionForward("/pjpy_ty_tzfw.do?method=tzfwszFlow&doType=''",false);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("xmList", commService.getPjxmList(PjxtszModel.pjxtszModel));
		return mapping.findForward("jdszFlow");
	}
}
