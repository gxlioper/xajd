package xsgzgl.gygl.gyjlxxglnew;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.gyjlxxgl.GyjlxxglForm;
import xsgzgl.gygl.gyjlxxgl.GyjlxxglService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

public class GyjlxxglNewAction extends BasicAction{
	
	/**
	 * ��Ԣ������Ϣά��
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxwh(ActionMapping mapping, ActionForm from,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		searchModel.setPath("gygl_gyjlglnew_gyjlxxwh.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("gygl_gyjlglnew_gyjlxxwh.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "xg_view_gygl_new_gyjlb");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_gygl_new_gyjlb");
		String userType=user.getUserType();
		request.setAttribute("userType", userType);
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{		
			return mapping.findForward("gyjlxxwh");
		}
	}
	
	
	/**
	 * ���ѧ���б�
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxscx(ActionMapping mapping, ActionForm from,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		// ----------------����PATH begin-----------------------
		request.setAttribute("title","���ѧ��");
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("gyjl_gyjlglnew_gyjlxscx.do");
		// ----------------����PATH end-----------------------
		String pkValue = request.getParameter("pkValue");
		service.setRequestValue(rForm, user, request);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("gyjlxscx");	
	}
	
	/**
	 * ѧ��������Ϣ��ѯ
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjlxxcx(ActionMapping mapping, ActionForm from,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("gygl_gyjlglnew_xsjlxxcx.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "");
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			return mapping.findForward("xsjlxxcx");
		}else{		
			String msg = "��ģ��ֻ����<font color='blue'>ѧ���û�</font>���в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}	
	}
	
	
	/**
	 * ���ӹ�Ԣ������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("gygl_gyjlglnew_gyjlxxwh.do");
		// ----------------����PATH end-----------------------
		//----------------����Ĭ��ҳ�����-----------------------
		HashMap<String,String> rs = service.setZjmrCs(request);
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("jldlList", service.getJldlList(request));
		request.setAttribute("jllbList", new ArrayList<HashMap<String,String>>());
		//�����ʵ��ѧ
		if("13627".equals(Base.xxdm)){
			String currDay = DateUtils.getYear()+"-"+DateUtils.getMonth()+"-"+DateUtils.getDayOfMonth();
			//ǰһ��ʱ��
			String lastDay = DateUtils.getLastDayOrNextDay(currDay, -3);
			request.setAttribute("currDay", currDay);
			request.setAttribute("lastDay", lastDay);
		}
		
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gyjlZj");
	}
	
	/**
	 * ɾ����Ԣ������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-��Ԣ������Ϣά-ɾ��PK:{str}")
	public ActionForward gyjlSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		String message = "";
		boolean flag = false;
		GyjlxxglForm myForm = new GyjlxxglForm();
		User user = getUser(request);
		String username = user.getUserName();
		String[] valArr = service.unicode2Gbk(request.getParameter("str")).split("!!@@");
		flag = service.gyjlSc(myForm, valArr, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * ������Ԣ���ɴ���
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-��Ԣ������Ϣ����-����PK:{str}")
	public ActionForward gyjlCancelCl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		String message = "";
		boolean flag = false;
		GyjlxxglForm myForm = new GyjlxxglForm();
		User user = getUser(request);
		String username = user.getUserName();
		String[] valArr = service.unicode2Gbk(request.getParameter("str")).split("!!@@");
		flag = service.gyjlCancelCl(myForm, valArr, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_CANCEL_NEW_SUCCESS : MessageInfo.MESSAGE_CANCEL_NEW_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * ������Ԣ�������
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-��Ԣ������Ϣ���-����PK:{str}")
	public ActionForward gyjlCancelSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		String message = "";
		boolean flag = false;
		GyjlxxglForm myForm = new GyjlxxglForm();
		User user = getUser(request);
		String username = user.getUserName();
		String[] valArr = service.unicode2Gbk(request.getParameter("str")).split("!!@@");
		flag = service.gyjlCancelSh(myForm, valArr, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_CANCEL_NEW_SUCCESS : MessageInfo.MESSAGE_CANCEL_NEW_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * ��Ԣ������Ϣ����
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward gyjlxxcl(ActionMapping mapping, ActionForm from,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		searchModel.setPath("gygl_gyjlglnew_gyjlxxcl.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("gygl_gyjlglnew_gyjlxxcl.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "");
		String userType=user.getUserType();
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("cflbList", new GyjlxxglService().getCflbList());
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{		
			return mapping.findForward("gyjlxxcl");
		}
	
	}
	
	
	/**
	 * ��Ԣ������Ϣ���
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxsh(ActionMapping mapping, ActionForm from,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		searchModel.setPath("gygl_gyjlglnew_gyjlxxsh.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("gygl_gyjlglnew_gyjlxxsh.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "");
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("gyjlxxsh");
	}
	
	
}
