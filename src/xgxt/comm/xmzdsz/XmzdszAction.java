package xgxt.comm.xmzdsz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommService;

import com.zfsoft.basic.BasicAction;

public class XmzdszAction extends BasicAction {

	
	/**
	 * 项目字段设置管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmzdszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmzdszService service = new XmzdszService();
		
		String mkmc = request.getParameter("mkmc");
		//String[] bzzd = service.getMkxmbzzd(mkmc);
		HashMap<String,String> mkccMap = service.getMkccb(mkmc);
		String tableName = mkccMap.get("xmzdb");
		String viewName = mkccMap.get("xmzdView");
		//String[] colList = new String[] {"pkValue"};
		String doType = request.getParameter("doType");
		
		String[] column = new String[] {"pkValue","lybmc","zdmc","zdm","bxlr","zdlx","lrxz","zdsx"};
		
		
		if ("del".equals(doType)) {
			deleteOperation(request, tableName);
		}
		
		selectPageDataByPagination(request, form, tableName,viewName,column);
		
		String xmdm=request.getParameter("queryequals_xmdm");
		request.setAttribute("xmdm",xmdm );
		request.setAttribute("mkmc", mkmc);
		request.setAttribute("path", "xszz_xmzdszManage.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xmList", service.szxmList(mkmc));
		request.setAttribute("bzzdList", service.getMkxmbzzdList(mkmc));
		return mapping.findForward("xmzdszManage");
	}
	
	
	
	
	
	/**
	 * 项目字段设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmzdszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmzdszForm model = (XmzdszForm) form;
		XmzdszService service = new XmzdszService();
		
		String xmdm = request.getParameter("xmdm");
		String mkmc = request.getParameter("mkmc");
	//	String[] bzzd = service.getMkxmbzzd(mkmc);
		HashMap<String,String> mkccMap = service.getMkccb(mkmc);
		String tableName = mkccMap.get("xmzdb");
		//String viewName = mkccMap.get("xmzdView");
		//String[] colList = new String[] {"pkValue"};
		String[] sjyArr = service.getSjy(xmdm, mkmc);
		String doType = request.getParameter("doType");
		String sjy = request.getParameter("sjy");
		
		String xmmc = service.getXmbmc(xmdm, mkccMap.get("xmdmb"), mkccMap.get("xmbzd"));
		
		if ("save".equals(doType)) {
			String[] pkValue = request.getParameterValues("primarykey_cbv");
			String[] flg = request.getParameterValues("flg");
			String[] lyb = request.getParameterValues("lyb");
			String[] zd = request.getParameterValues("zd");
			String[] zdm = request.getParameterValues("zdm");
			String[] bxlr = request.getParameterValues("bxlr");
			String[] zdlx = request.getParameterValues("zdlx");
			String[] lrxz = request.getParameterValues("lrxz");
			String[] zdsx = request.getParameterValues("zdsx");
			
			boolean result = service.bcszzd(xmdm, tableName, pkValue, flg, lyb, zd, zdm, bxlr, zdlx, lrxz, zdsx,sjy);
			
			request.setAttribute("message", result ? "保存成功" : "保存失败");
			
			if ("xszz".equals(mkmc)) {
				XszzTyForm xszzForm = new XszzTyForm();
				xszzForm.setXmdm(xmdm);
				xszzForm.setMrxm(service.getIsMrxm(xmdm));
				xszzForm.setFlg(true);
				new XszzCommService().setXmzdList(xszzForm);
			}
			
		}
		
		List<HashMap<String,String>> rs = service.getZdList(xmdm, mkmc,model,sjy);
		
		
		
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("mkmc", mkmc);
		request.setAttribute("rs", rs);
		request.setAttribute("sjyArr", Base.isNull(sjy) ? Arrays.toString(sjyArr).replace("[", "").replace("]", "") : sjy);
		request.setAttribute("mkmc", mkmc);
		request.setAttribute("xmList", service.szxmList(mkmc));
		request.setAttribute("bzzdList", service.getMkxmbzzdList(mkmc));
		return mapping.findForward("xmzdszUpdate");
	}
}
