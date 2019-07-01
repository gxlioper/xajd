package xsgzgl.gygl.mrjl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;

import com.zfsoft.xgxt.base.log.SystemLog;

public class MrjlglAction extends BasicExtendAction{
	
	/**
	 * 每日记录管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mrjlglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MrjlglService service = new MrjlglService();
		MrjlglForm myForm = (MrjlglForm)form;
		
		String[] topTr = new String[]{"主键", "时间", "楼栋名称", "值班人员", "天气"};
		String doType = request.getParameter("doType");
		
		if("del".equalsIgnoreCase(doType)){
			String[] pks = request.getParameterValues("primary_cbv");
			String message = service.delMrjl(pks) ? "删除成功！": "删除失败！";
			request.setAttribute("message", message);
		}
		
		setWriteAbleAndTitle(request, "gyglnew_mrjlgl_mrjlgl.do");
		
		request.setAttribute("searchTj", myForm.getSearchModel());	//高级查询条件
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", service.mrjlQuery(myForm));
		request.setAttribute("ldList", service.getLdList());		// 楼栋列表
		request.setAttribute("zbryList", service.getZbryList());	// 值班人员列表
		request.setAttribute("realTable", "");
		request.setAttribute("tableName", "xg_view_gygl_mrzbjl");
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		return mapping.findForward("mrjlglManage");
	}
	
	/**
	 * 每日记录管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-值班记录-每日记录-增加")
	public ActionForward mrjlglUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MrjlglService service = new MrjlglService();
		
		String doType = request.getParameter("doType");
		MrjlglForm myForm = (MrjlglForm)form;
		
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveMrjl(myForm) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
		
		request.setAttribute("ldList", service.getLdList());		// 楼栋列表
		request.setAttribute("zbryList", service.getZbryList());	// 值班人员列表
		return mapping.findForward("mrjlglUpdate");
	}
	
	/**
	 * 每日记录管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-值班记录-每日记录-修改LDDM:{lddm}")
	public ActionForward mrjlglModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MrjlglService service = new MrjlglService();
		
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		MrjlglForm myForm = (MrjlglForm)form;
		
		if("save".equalsIgnoreCase(doType)){
			String message = service.updateMrjl(myForm) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
		
		request.setAttribute("rs", service.getMrjl(pkValue));
		
		request.setAttribute("doType", doType);
		request.setAttribute("ldList", service.getLdList());		// 楼栋列表
		request.setAttribute("zbryList", service.getZbryList());	// 值班人员列表
		return mapping.findForward("mrjlglModi");
	}
}
