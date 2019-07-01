package xsgzgl.qgzx.gwgl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.jcdmwh.QgzxJcdmwhService;

import com.zfsoft.basic.BasicAction;
/**
 * �ڹ���ѧ-�ڹ���λ����-��λ��Ϣ����
 * @author yeyipin
 * @since 2012.7.17
 */
public class QgzxGwglAction extends BasicAction {
	
	/**
	 * ��λ�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setPath("qgzx_gwgl_gwsq.do");
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("rs", service.setZjmrCs(request));
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_gwgl_gwsq.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xg_qgzx_gwxxsqb");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_qgzx_gwxxsqb");
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("gwsqCx");
		}
	}
	
	
	/**
	 * ��λ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxJcdmwhService jcdmservice = new QgzxJcdmwhService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("qgzx_gwgl_gwsq.do");
		HashMap<String,String> rs = service.setZjmrCs(request);
		request.setAttribute("rs", rs);
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
//		request.setAttribute("gwxzList", jcdmservice.getGwxzdmList());
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gwsqZj");
	}
	
	
	/**
	 * ��λ�����޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwsqXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxJcdmwhService jcdmservice = new QgzxJcdmwhService();
		QgzxGwglForm model = (QgzxGwglForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		rForm.setPath("qgzx_gwgl_gwsq.do");
		HashMap<String,String> rs = service.getGwsqMap(model);
		request.setAttribute("rs", StringUtils.formatData(rs));
		//�������
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
		//request.setAttribute("gwxzList", jcdmservice.getGwxzdmList());
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gwsqXg");
	}
	
	/**
	 * ��λ��˲�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwshCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_qgshzt(new String[]{"δ���"});
		searchModel.setPath("qgzx_gwgl_gwsh.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_gwgl_gwsh.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xg_qgzx_gwxxsqb");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_qgzx_gwxxsqb");
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("gwshCx");
		}
	}
	
	
	/**
	 * ��λ��Ϣ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwshDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxGwglForm model = (QgzxGwglForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("pkValue", pkValue);
		rForm.setPath("qgzx_gwgl_gwsh.do");
		HashMap<String,String> rs = service.getGwsqMap(model);
		request.setAttribute("rs", StringUtils.formatDataView(rs));
		//�������
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
		
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gwshDgsh");
	}
	
	
	/**
	 * ��λ��Ϣ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwshPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("pkValue", pkValue);
		
		rForm.setPath("qgzx_gwgl_gwsh.do");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gwshPlsh");
	}
	
	/**
	 * ��λ��Ϣ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setPath("qgzx_gwgl_gwxxgl.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_gwgl_gwxxgl.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xg_qgzx_gwxxb");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_qgzx_gwxxb");
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("gwxxCx");
		}
	}
	
	
	/**
	 * ��λ��Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxJcdmwhService jcdmservice = new QgzxJcdmwhService();
		QgzxGwglForm model = (QgzxGwglForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("qgzx_gwgl_gwxxgl.do");
		HashMap<String,String> rs = service.setZjmrCs(request);
		request.setAttribute("rs", rs);
		//�������
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
		
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("yrbmList", service.getYrbm(model));
//		request.setAttribute("gwxzList", jcdmservice.getGwxzdmList());
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gwxxZj");
	}
	
	
	/**
	 * ��λ��Ϣ�������޸ģ��鿴�������Ա���˸���Ա��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		QgzxJcdmwhService jcdmservice = new QgzxJcdmwhService();
		QgzxGwglForm myForm = (QgzxGwglForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		myForm.setPkValue(pkValue);
		myForm.setDoType(doType);
		HashMap<String,String> rs = service.getGwryxx(myForm,request);
		request.setAttribute("rs", rs);
		//�������
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		request.setAttribute("jcpz", qgzxCsszService.getCssz());
		
		User user = getUser(request);
		rForm.setPath("qgzx_gwgl_gwxxgl.do");
		service.setRequestValue(rForm, user, request);
//		request.setAttribute("gwxzList", jcdmservice.getGwxzdmList());
		return mapping.findForward(doType);
	}
	
	
	/**
	 * ���ѧ���б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		QgzxGwglService service = new QgzxGwglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_gwgl_getStu.do");
		// ----------------����PATH end-----------------------
		String pkValue = request.getParameter("pkValue");
		service.setRequestValue(rForm, user, request);
		request.setAttribute("pkValue", pkValue);
		//�ڹ����Ի�����
		request.setAttribute("xn", request.getParameter("xn"));
		//�ʸ���鿪�ؿ�����ֻ���ڹ���ѧ���е�ѧ���������
		if ("yes".equals(new QgzxCsszService().getCssz().get("sfxyzgsc"))){
			request.setAttribute("sfxyzgsc", "yes");
		}
		return mapping.findForward("getStu");
	}
	
	

}
