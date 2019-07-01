package xsgzgl.xsxx.bzrpy.bzrpygl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.utils.FormModleCommon;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjInit;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshService;
import xsgzgl.xsxx.bzrpy.BasicModel;

import com.zfsoft.basic.BasicAction;

public class XsxxBzrpyAction extends BasicAction {


	// 视图名
	private String VIEWNAME = "xg_view_xsxx_bzrpy";

	// 查詢數組
	private String[] COLLIST = {"pkValue","xh", "xm","nj", "bjmc","pyyj","pyxx","pyrxm","pysj"};
	
	//排序字段
	private String[] ORDERBY ={"nj","bjdm","xh"};

	XsxxBzrpyService service = new XsxxBzrpyService();

	XsxxBzrpyInit init = new XsxxBzrpyInit();

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
	public ActionForward bzrpyManage(ActionMapping mapping, ActionForm form,
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
		//排序字段
		model.setOrderBy(ORDERBY);
		//功能模块
		model.setGnmk("xsxx");
		//模块路径
		model.setPath("xsxx_bzrpy.do?method=bzrpyManage");
		
		
		init.initBzrpy(rForm, model, request);
		//结果列表
		ArrayList<String[]> rsArrList = null;
		rsArrList = (ArrayList<String[]>)service.getBasicList(model);
		//高级查询
		request.setAttribute("searchTj", model.getSearchModel());
		
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pysjsz", service.getBzrpyJzsj());
		
		request.setAttribute("realTable", "xg_xsxx_bzrpyb");
		request.setAttribute("tableName", VIEWNAME);
		FormModleCommon.commonRequestSet(request);
		// =================== end ===================
		String url = "/xsgzgl/xsxx/bzrpy/bzrpyManage.jsp";
		return new ActionForward(url, false);
	}
}
