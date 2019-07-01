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

	// ��ͼ��
	private String VIEWNAME = "xg_view_xsxx_pysjsz";

	private String[]QUERYV=new String[]{};
	
	private String[]INPUTV=new String[]{};
	
	private String[]OUTPUT={"xn","pysj","sfbc"};

	XsxxPysjService service = new XsxxPysjService();

	XsxxPysjInit init = new XsxxPysjInit();

	/**
	 * ����������
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
		//��formֵ������model��
		BeanUtils.copyProperties(model, myForm);
		
		//��ѯ����
		model.setViewName(VIEWNAME);
		//��ѯ�б�
		model.setOutPut(OUTPUT);
		//����
		model.setQueryV(QUERYV);
		
		model.setInputV(INPUTV);		
		//����ģ��
		model.setGnmk("xsxx");
		
		//ģ��·��
		model.setPath("xsxx_pysjsz.do?method=bzrpysj");
		
		request.setAttribute("rs",service.getBasicMap(model));
		//init.initBzrpy(rForm, model, request);
		//����б�
		service.setRequestValue(rForm, user, request);
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request);
		// =================== end ===================
		String url = "/xsgzgl/xsxx/pysjsz/bzrpysj.jsp";
		return new ActionForward(url, false);
	}
}
