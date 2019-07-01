package xsgzgl.rcsw.zjbb;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.jqlx.GyglJqlxForm;
import xsgzgl.gygl.jqlx.GyglJqlxInit;
import xsgzgl.gygl.jqlx.GyglJqlxService;

import com.zfsoft.basic.BasicAction;

public class RcswZjbbAction extends BasicAction{

	RcswZjbbService service = new RcswZjbbService();
	
	RcswZjbbInit init = new RcswZjbbInit();
	
	// -----------------------������У���� begin---------------------------
	/**
	 * ֤���������ù���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswZjbbForm myForm=(RcswZjbbForm)form;
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("path", "rcsw_zjbb_jbsz.do");
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bbszManage");
	
	}
	// -----------------------������У���� end ---------------------------
	
	// -----------------------������У���� begin---------------------------
	/**
	 * ������У�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		String userType=user.getUserType();
		
		// -----------------����ѧ���û����� --------------------
		if(!"stu".equalsIgnoreCase(userType)){
			
			String msg = "��ģ��ֻ����<font color='blue'>ѧ���û�</font>���в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// ----------------��ʾtitle���ж϶�дȨ----------------
		// ----------------����PATH begin-----------------------
		rForm.setPath("rcsw_zjbb_bbsq.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		request.setAttribute("zjbbList", service.getZjbbList());
		// ----------------- ���������� ------------------------
		
		return mapping.findForward("bbsqManage");
	}
	
	/**
	 * ������У���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		
		RcswZjbbForm myForm=(RcswZjbbForm)form;
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		List<HashMap<String,String>>cshXmList= service.getCshXmList(user);
		//String spgwNum=service.countSpgw(user);
		
		if(cshXmList==null || cshXmList.size()==0){
			String msg = "��Ŀǰû������˵���Ŀ��������������ϵ����Ա!";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("rcsw_zjbb_bbsh.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		
		//service.setJbszInfo(myForm);
		
		request.setAttribute("cshXmList", cshXmList);
//		request.setAttribute("spgwNum", spgwNum);
		return mapping.findForward("bbshManage");
	}

	/**
	 * ������У�鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjbbCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType=request.getParameter("doType");
		
		String pkValue=request.getParameter("sqid");
		
		HashMap<String, String> stuInfo=new HashMap<String,String>();
		
		if(!Base.isNull(pkValue)){
			// ѧ��������Ϣ
			stuInfo.putAll(service.getZjbbSqInfo(pkValue));
			
			request.setAttribute("xmshInfo", service.getZjbbShInfo("", pkValue, ""));
		}
		
		request.setAttribute("map", stuInfo);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "rcsw_zjbb_bbjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zjbbCk");
	}
	
	/**
	 * ������У�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("rcsw_zjbb_bbjg.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "xg_view_rcsw_zjbb_jgcx");
		return mapping.findForward("bbjgManage");
	}
	
	/**
	 * ������У�鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType=request.getParameter("doType");
		
		String pkValue=request.getParameter("sqid");
		
		String spgw=request.getParameter("spgw");
		
		String xmid=request.getParameter("xmid");
		
		HashMap<String, String> stuInfo=new HashMap<String,String>();
		
		if(!Base.isNull(pkValue)){
			// ѧ��������Ϣ
			stuInfo.putAll(service.getZjbbSqInfo(pkValue));
			
			request.setAttribute("xmshInfo", service.getZjbbShInfo(xmid,pkValue,spgw));
		}
		
		request.setAttribute("map", stuInfo);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("spgw", spgw);
		request.setAttribute("xmid", xmid);
		request.setAttribute("path", "rcsw_bbsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bbshDetail");
	}
	// ---------------------������У���� end -----------------------------
}
