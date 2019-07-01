package xsgzgl.xsxx.bzrpy.sjsz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.xsxx.bzrpy.BasicModel;
import xsgzgl.xsxx.bzrpy.bzrpygl.XsxxBzrpyForm;

import com.zfsoft.basic.BasicAction;

public class XsxxPysjAction extends BasicAction {

	// 视图名
	private String VIEWNAME = "xg_view_xsxx_pysjsz";

	private String[]QUERYV=new String[]{};
	
	private String[]INPUTV=new String[]{};
	
	private String[]OUTPUT={"xn","pysj","sfbc"};

	XsxxPysjService service = new XsxxPysjService();

	XsxxPysjInit init = new XsxxPysjInit();

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
	public ActionForward bzrpysj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		User user=getUser(request);
		
		XsxxBzrpyForm myForm=(XsxxBzrpyForm)form;
		
		BasicModel model=new BasicModel();
		//将form值拷贝到model中
		BeanUtils.copyProperties(model, myForm);
		
		//查询表明
		model.setViewName(VIEWNAME);
		//查询列表
		model.setOutPut(OUTPUT);
		//条件
		model.setQueryV(QUERYV);
		
		model.setInputV(INPUTV);		
		//功能模块
		model.setGnmk("xsxx");
		
		//模块路径
		model.setPath("xsxx_pysjsz.do?method=bzrpysj");
		
		request.setAttribute("rs",service.getBasicMap(model));
		//init.initBzrpy(rForm, model, request);
		//结果列表
		service.setRequestValue(rForm, user, request);
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request);
		// =================== end ===================
		String url = "/xsgzgl/xsxx/pysjsz/bzrpysj.jsp";
		return new ActionForward(url, false);
	}
}
