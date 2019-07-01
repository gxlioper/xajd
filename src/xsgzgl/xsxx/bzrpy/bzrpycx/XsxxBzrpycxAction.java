package xsgzgl.xsxx.bzrpy.bzrpycx;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.xsxx.bzrpy.BasicModel;
import xsgzgl.xsxx.bzrpy.bzrpygl.XsxxBzrpyForm;

import com.zfsoft.basic.BasicAction;

public class XsxxBzrpycxAction extends BasicAction {


	// 视图名
	private String VIEWNAME = "xg_view_xsxx_bzrpycx";

	// 查詢數組
	private String[] COLLIST = {"xn","xh", "xm", "nj", "bjmc","pyyj","pyxx","pyrxm","pysj"};

	XsxxBzrpycxService service = new XsxxBzrpycxService();

	XsxxBzrpycxInit init = new XsxxBzrpycxInit();

	/**
	 * 班主任评议
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bzrpycxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		User user=getUser(request);
		
		XsxxBzrpyForm myForm=(XsxxBzrpyForm)form;
		
		BasicModel model=new BasicModel();
		//将form值拷贝到model中
		BeanUtils.copyProperties(model, myForm);
		
		//查询视图名
		model.setViewName(VIEWNAME);
		//查询的结果集
		model.setColList(COLLIST);
		//功能模块
		model.setGnmk("xsxx");
		//模块路径
		model.setPath("xsxx_bzrpycx.do?method=bzrpycxManage");
		
		init.initBzrpy(rForm, model, request);
		//结果列表
		ArrayList<String[]> rsArrList = null;
		rsArrList = (ArrayList<String[]>)service.getBasicList(model);
		//高级查询
		request.setAttribute("searchTj", model.getSearchModel());
		
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("rsArrList", rsArrList);

		// =================== end ===================
		String url = "/xsgzgl/xsxx/bzrpycx/bzrpycxManage.jsp";
		return new ActionForward(url, false);
	}
}
