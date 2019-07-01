package xgxt.wjcf.zjcm;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

public class WjcfZjcmAction extends DispatchAction {

	private static final String SAVE = "save";
	
	/**
	 * 留校察看时间设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxckSjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfZjcmActionForm zjcmForm = (WjcfZjcmActionForm) form;
		WjcfZjcmService service = new WjcfZjcmService();
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			appendOperResult(request, service.addSj(zjcmForm.getSj()));
		}
		zjcmForm.setSj(service.getSj());
		
		//保存读写权
		request.setAttribute("path", "wjcf_zjcm_lxcksjsz.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("lxckSjsz");
	}
	
	/**
	 * 处分呈报表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfbprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZjcmActionForm model = (WjcfZjcmActionForm) form;
		WjcfZjcmService service = new WjcfZjcmService();

		String cfpk = request.getParameter("cfpk");
		String xh = "";
		HashMap<String, String> map = new HashMap<String, String>();

		if (!StringUtils.isNull(cfpk)) {
			map = service.getCfbPrintxx(cfpk);
			xh = map.get("xh");
			
		}
		request.setAttribute("rs", map);
		
		if (!StringUtils.isNull(xh)) {
			model.setXh(xh);
			List<HashMap<String, String>> lnwjList = service.getLnwjList(model);
			if (lnwjList != null && lnwjList.size() > 0) {
				request.setAttribute("lnwjList", lnwjList);
			}
		}
		
		return mapping.findForward("cfbprint");
	}
	
	/**
	 * 各学院处分汇总表输出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cfhzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xydm = request.getParameter("xydm");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		WjcfZjcmModel model = new WjcfZjcmModel();
		model.setXydm(xydm);
		model.setXn(xn);
		model.setXq(xq);
		model.setCs(request.getParameter("cs"));
		WjcfZjcmService service = new WjcfZjcmService();
		String modelPath = "";
		modelPath = servlet.getServletContext().getRealPath("")+"/print/wjcf/zjcm/wjcf_zjcj_cftjb.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.exportCfhzb(model, wwb);
		return mapping.findForward("");
	}
	
	/**
	 * 解除留校察看呈报表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jclxcbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			WjcfZjcmService service = new WjcfZjcmService();
			rs = service.queryJclxbbxx(pkValue);
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("jclxcbb");
	}

	/**
	 * 学生查看留校察看详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stulxckxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			WjcfZjcmService service = new WjcfZjcmService();
			rs = service.queryStuLxclxx(pkValue);
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("stulxckxx");
	}
	
	//保存操作结果
	public void appendOperResult(HttpServletRequest request, boolean result) {
		request.setAttribute("inserted", result);
	}
	
	/**
	 * 成都体育处分申报表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cdtycfbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String cfpk = request.getParameter("cfpk");
		//是否审核模块
		String sfshmk = request.getParameter("sfshmk");
		WjcfZjcmService service = new WjcfZjcmService();
		if("yes".equalsIgnoreCase(sfshmk)){
			request.setAttribute("rs", service.printwjInfo(cfpk));
		}else{
			request.setAttribute("rs", service.queryXsCfxx(cfpk));
		}
		request.setAttribute("xxmc", Base.xxmc);
		request.setAttribute("time", GetTime.getNowTime());
		return mapping.findForward("cdtycfbPrint");
	}
}
