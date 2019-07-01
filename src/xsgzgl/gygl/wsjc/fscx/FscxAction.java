package xsgzgl.gygl.wsjc.fscx;

import java.util.HashMap;
import java.util.List;

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
import xsgzgl.gygl.comm.GyglNewInit;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-28 ����11:29:22
 * </p>
 */
public class FscxAction extends BasicAction {

	/**
	 * ������飬��������Ϣ�Ĳ�ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fscxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);

		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("gyglnew_wsjc_fscx.do");
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xg_gygl_new_wsjc_qsfsb_dc");
		
		//------------------���ø߼���ѯĬ��ֵ-------------
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("userType", user.getUserType());
		
		if (("0").equals(GyglNewInit.JFFS)) {
			request.setAttribute("sfsdj", "0");
		} else {
			request.setAttribute("sfsdj", "1");
		}
		return mapping.findForward("fscxCx");
	}

	/**
	 * ������飬��������Ϣ�Ĳ鿴
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fscxCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		FscxForm fscxForm = (FscxForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("lddm", lddm);
		request.setAttribute("qsh", qsh);
		
		request.setAttribute("sfsdj", GyglNewInit.JFFS);
		
		HashMap<String,String> jcrc = service.getFscxCz2(null, pkValue);
		
		String jclx = jcrc.get("jclx");
		if (StringUtil.isNull(jclx)){
			jclx = GyglNewInit.JFFS;
		}
		
		request.setAttribute("jclx", jclx);
		// ����ճ�
		HashMap<String, String> jcrcbt = service.getFscxCz2(fscxForm, pkValue);
		String jcrcmc = jcrcbt.get("mc") + "[" + jcrcbt.get("kssj") + "��"+ jcrcbt.get("jssj") + "]";
		request.setAttribute("jcrc", jcrcmc);

		// ��ѯ����¼����ϸ
		fscxForm.setLddm(lddm);
		fscxForm.setGuid(pkValue);
		fscxForm.setQsh(qsh);
		HashMap<String, String> fscxxx = service.getFscxMap(fscxForm);
		HashMap<String, String> fscxxxAll = service.getFscxAllMap(fscxForm);
		request.setAttribute("rs", fscxxx);
		request.setAttribute("rsAll", fscxxxAll);
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("gyglnew_wsjc_fslr.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("fscxCk");
	}
	
	/**
	 * ������飬��������Ϣ��ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fscxWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		FscxForm fscxForm = (FscxForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("lddm", lddm);
		request.setAttribute("qsh", qsh);
		
		request.setAttribute("sfsdj", GyglNewInit.JFFS);

		HashMap<String,String> jcrc = service.getFscxCz2(null, pkValue);
		
		String jclx = jcrc.get("jclx");
		if (StringUtil.isNull(jclx)){
			jclx = GyglNewInit.JFFS;
		}
		
		request.setAttribute("jclx", jclx);
		
		// ����ճ�
		HashMap<String, String> jcrcbt = service.getFscxCz2(fscxForm, pkValue);
		String jcrcmc = jcrcbt.get("mc") + "[" + jcrcbt.get("kssj") + "��"+ jcrcbt.get("jssj") + "]";
		request.setAttribute("jcrc", jcrcmc);

		// ��ѯ����¼����ϸ
		fscxForm.setLddm(lddm);
		fscxForm.setGuid(pkValue);
		fscxForm.setQsh(qsh);
		HashMap<String, String> fscxxx = service.getFscxMap(fscxForm);
		request.setAttribute("rs", fscxxx);
		// ��ȡ�ȼ������б��
		request.setAttribute("djlist", service.getDjList(request));
		request.setAttribute("xjlist", service.getXjList(request));
		
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("gyglnew_wsjc_fslr.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("fscxWh");
	}

	/**
	 * ������飬����������Ϣ������ɾ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-�������-�����ֲ�ѯ-ɾ��VALUES:{str}")
	public ActionForward fscxSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		String message = "";
		boolean flag = false;
		FscxForm myForm = (FscxForm) form;
		User user = getUser(request);
		String username = user.getUserName();
		String[] valArr = service.unicode2Gbk(request.getParameter("str")).split("!!@@");
		flag = service.fscxSc(myForm, valArr, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * @description	����Ԣ�������ֵ���ҳ��
	 * @author 		�� ������1282��
	 * @date 		��2017-11-17 ����11:20:07
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyywsfDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		request.setAttribute("nd", Base.currNd);
		FscxService fscxService = new FscxService();
		List<HashMap<String,String>> yfList = Base.getYfList();
		request.setAttribute("yfList", yfList);
		List<HashMap<String,String>> ldList = fscxService.getLdList();
		request.setAttribute("ldList", ldList);
		return  mapping.findForward("gyywsfDc");
	}
	
	/**
	 * @description	�� ��Ԣ��������ͳ�Ƶ���
	 * @author 		�� ������1282��
	 * @date 		��2017-11-17 ����02:27:28
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gywsfdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		FscxForm model = (FscxForm) form;

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.gywsfdc(model,response.getOutputStream());
				
		return null;		
	}
	
	/**
	 * @description	�� �Ƿ���
	 * @author 		�� ������1282��
	 * @date 		��2017-11-20 ����04:31:57
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sfjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		FscxForm model = (FscxForm) form;

		boolean result = true;
		List<HashMap<String, String>> list = service.getLdYwsfList(model);
		
		if (null != list && list.size() > 0) {
			result = true;
			response.getWriter().print(result);
		}else{
			result = false;
			response.getWriter().print(result);
		}
		return null;		
	} 
}