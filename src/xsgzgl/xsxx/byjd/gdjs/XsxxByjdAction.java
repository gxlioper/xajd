package xsgzgl.xsxx.byjd.gdjs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;

public class XsxxByjdAction extends BasicAction{
	
	XsxxByjdService service =new XsxxByjdService();
	
	XsxxByjdInit init =new XsxxByjdInit();
	
	/**
	 * 查询模型 以高级查询为查询条件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward byjdManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		XsxxByjdForm myForm=(XsxxByjdForm)form;
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		String xmdm=request.getParameter("xmdm");
		
		//将form值拷贝到model中
		BeanUtils.copyProperties(model, myForm);
		
		//功能模块
		model.setGnmk("xsxx");
		//模块路径
		model.setPath("xsxx_byjd.do?method=byjdManage");

		init.initXsqtxx(rForm, myForm,user, request);
		
		//高级查询
		SearchModel searchModel=model.getSearchModel();

		request.setAttribute("searchTj", searchModel);
		
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("path", model.getPath());
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("xmdm", xmdm);
	
		// =================== end ===================
	
		return mapping.findForward("byjdManage");
	}
	
}
