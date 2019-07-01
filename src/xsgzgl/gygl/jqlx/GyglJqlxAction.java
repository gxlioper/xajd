package xsgzgl.gygl.jqlx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-7-18 ����11:34:03</p>
 */
public class GyglJqlxAction extends BasicAction{
	
	GyglJqlxService service = new GyglJqlxService();
	
	GyglJqlxInit init = new GyglJqlxInit();
	
	// -----------------------������У���� begin---------------------------
	/**
	 * ������У���ù���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// -----------------��ض��� begin -----------------
		RequestForm rForm = new RequestForm();
		
		GyglJqlxForm  myForm=(GyglJqlxForm )form;
		
		BasicModel model=new BasicModel();
		
		User user=getUser(request);
		// -----------------��ض��� end -----------------

		// ------------������У���ó�ʼ�� begin--------------		
		init.initLxszManage(rForm, model, request, user);
		
		HashMap<String,String>rs=service.viewInfo(model);
		// ------------������У���ó�ʼ�� begin--------------		
		
		// ------------����������Ϣ���� begin--------------	
		request.setAttribute("rs", rs);
		
		myForm.setSqkg(Base.isNull(rs.get("sqkg"))? "close" : rs.get("sqkg"));
		
		myForm.setLcid(Base.isNull(rs.get("lcid"))? "no" : rs.get("lcid"));
		// ------------����������Ϣ���� end--------------		
		
		// ------------------��У����·��Ȩ�� begin-----------------------
		request.setAttribute("path", "jqlx_lxsz.do");
		
		FormModleCommon.commonRequestSet(request);
		// ------------------��У����·��Ȩ�� end-------------------------
		
		return mapping.findForward("lxszManage");
	
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
	public ActionForward lxsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		
		GyglJqlxForm myForm=(GyglJqlxForm)form;
		service.setJbszInfo(myForm);
		// -----------------��ض��� begin ---------------		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// -----------------��ض��� end -----------------	
		
		// �û���
		String userType=user.getUserType();
		
		// -----------------����ѧ��ӵ������ -------------------
		if(!"stu".equalsIgnoreCase(userType)){
			
			String msg = "��ģ��ֻ����<font color='blue'>ѧ���û�</font>���в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if(!"open".equalsIgnoreCase(myForm.getSqkg())){
			
			String msg = "��У���빦���ѹر��޷��������룬��ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// ----------------��ʾtitle���ж϶�дȨ----------------
		
		// ----------------����PATH begin-----------------------
		rForm.setPath("jqlx_lxsq.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		// ��ȡ����������Ϣ
		service.setJbszInfo(myForm);
		
		return mapping.findForward("lxsqManage");
	}
	
	/**
	 * ������У���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward lxshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		
		// -----------------��ض��� begin ---------------		
		GyglJqlxForm myForm=(GyglJqlxForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// -----------------��ض��� end -----------------	
		
		// -----------��ȡָ���û�������λ�� begin--------------
		String spgwNum=service.countSpgw(user);
		
		// Ϊ0����������� �޷��������
		if("0".equalsIgnoreCase(spgwNum)){
			String msg = "��������������У�������������ϵ����Ա!";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("jqlx_lxsh.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ��ȡ����������Ϣ
		service.setJbszInfo(myForm);
		request.setAttribute("spgwNum", spgwNum);
		return mapping.findForward("lxshManage");
	}

	/**
	 * ������У�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward lxshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// ����ֵ
		String pkValue=request.getParameter("sqid");
		// ������λ
		String spgw=request.getParameter("spgw");
		
		// ѧ�����롢�����Ϣ���
		HashMap<String, String> stuInfo=new HashMap<String,String>();
		
		if(!Base.isNull(pkValue)){
			// ѧ��������Ϣ
			stuInfo.putAll(service.getJqlxSqInfo(pkValue));
			
			// ѧ�������Ϣ
			request.setAttribute("xmshInfo", service.getJqlxShInfo(pkValue,spgw));
		}
		
		// ������Ϣ
		request.setAttribute("map", stuInfo);
		// ����ֵ
		request.setAttribute("pkValue", pkValue);
		// ������λ
		request.setAttribute("spgw", spgw);
		
		// -----------��ͷ����дȨ begin ------------
		request.setAttribute("path", "jqlx_lxsh.do");
		FormModleCommon.commonRequestSet(request);
		// -----------��ͷ����дȨ end ------------
		
		return mapping.findForward("lxshDetail");
	}
	
	/**
	 * ������У�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward lxjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response
			) throws Exception {
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("jqlx_lxjg.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "xg_view_gygl_jqlx_jgcx");
		// ----------------- ��������� ------------------------
		
		return mapping.findForward("lxjgManage");
	}
	
	/**
	 * ������У�鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward lxjgCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// ----------------����ID begin--------------------
		String pkValue=request.getParameter("sqid");
		// ----------------����ID end--------------------
		
		HashMap<String, String> rs=new HashMap<String,String>();
		
		if(!Base.isNull(pkValue)){
			// ѧ��������Ϣ
			rs.putAll(service.getJqlxSqInfo(pkValue));
			
			request.setAttribute("xmshInfo", service.getJqlxShInfo(pkValue,""));
		}
		
		request.setAttribute("map", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "jqlx_lxjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lxjgCk");
	}
	
	// ---------------------������У���� end -----------------------------
}
