package xgxt.xsh.stgl.cdty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import com.zfsoft.basic.BasicAction;

public class CdtyXsstAction extends BasicAction {

	/**
	 * ����ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stxxWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("stxxWh");
	}

	/**
	 * ѧ����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsstSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxglService stuService = new XsxxglService();
		CdtyXsstService service =new CdtyXsstService();
		CdtyXsstForm myForm=(CdtyXsstForm)form;
		
		User user = getUser(request);
		String userType = user.getUserType();
		String xh = "";
		String stdm=request.getParameter("stdm");
		String doType=request.getParameter("doType");
		// �жϽ�ʦ��ѧ��
		
		if ("stu".equalsIgnoreCase(userType) && Base.isNull(xh)) {
			// �����ѧ��ֱ��ȡ�û���
			xh = user.getUserName();

		} else  {
			// ����ǽ�ʦ��request�л��ѧ��
			xh = request.getParameter("xh");
		}

		HashMap<String, String> stuInfo = new HashMap<String, String>();
		if (!Base.isNull(xh)) {
			stuInfo = stuService.selectStuinfo(xh);
		}
		
		if(!Base.isNull(stdm)){
			
			stuInfo.putAll(service.getStInfoList(myForm));
		}
		
		if("save".equalsIgnoreCase(doType)){
			service.saveXsstInfo(myForm);
		}
		
		request.setAttribute("xh", xh);
		request.setAttribute("path", "stgl_xsstsq.do");
		request.setAttribute("doType", "add");
		request.setAttribute("rs", stuInfo);
		request.setAttribute("rtsj", GetTime.getNowTime2());
		request.setAttribute("stxxList", service.getStxxList(myForm));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsstSq");
	}

	/**
	 * ѧ�����������޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsstUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxglService stuService = new XsxxglService();
		CdtyXsstService service =new CdtyXsstService();
		CdtyXsstForm myForm=(CdtyXsstForm)form;
		
		User user = getUser(request);
		String pkValue=request.getParameter("pkValue");
		// �жϽ�ʦ��ѧ��
		
		HashMap<String, String> stuInfo = new HashMap<String, String>();
		if(Base.isNull(pkValue)){
			String[]pkArr=pkValue.split("!!@@!!");
			String xh=pkArr[0];
			String stdm=pkArr[1];
			if (!Base.isNull(xh)) {
				stuInfo = stuService.selectStuinfo(xh);
			}
			
			if(!Base.isNull(stdm)){
				
				stuInfo.putAll(service.getStInfoList(myForm));
			}
			request.setAttribute("xh", xh);
			request.setAttribute("stdm", stdm);
		}
	
		
		request.setAttribute("path", "stgl_xsstsq.do");
		request.setAttribute("doType", "update");
		request.setAttribute("rs", stuInfo);
		request.setAttribute("rtsj", GetTime.getNowTime2());
		request.setAttribute("stxxList", service.getStxxList(myForm));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsstSq");
	}

	
	/**
	 * ѧ�����Ź���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsstManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CdtyXsstService service=new CdtyXsstService();
		CdtyXsstForm myForm=(CdtyXsstForm)form;
		
		String doType=request.getParameter("doType");
		
		//ɾ������
		if("del".equalsIgnoreCase(doType)){
			
			service.delXsstInfo(myForm);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("rs",service.getXsstInfo(myForm));
		request.setAttribute("topTr", service.getTopTr("sqcx"));
		request.setAttribute("path", "stgl_stgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsstManage");
	}

	/**
	 * ѧ�������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsstsqSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdtyXsstService service=new CdtyXsstService();
		CdtyXsstForm myForm=(CdtyXsstForm)form;
		User user = getUser(request);
		String doType=request.getParameter("doType");
		String shzt=request.getParameter("shzt");
		
		if("plsh".equalsIgnoreCase(doType)){
			myForm.setShzt(shzt);
			myForm.setShsj(GetTime.getNowTime2());
			myForm.setShr(user.getUserName());
			service.updateShzt(myForm);	
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("rs",service.getXsstInfo(myForm));
		request.setAttribute("topTr", service.getTopTr("sqcx"));
		request.setAttribute("path", "stgl_xssqsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsstsqSh");
	}
	
	/**
	 * ѧ�����ŵ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsstDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CdtyXsstService service =new CdtyXsstService();
		CdtyXsstForm myForm=(CdtyXsstForm)form;
		
		User user = getUser(request);
		String pkValue=request.getParameter("pkValue");
		String doType=request.getParameter("doType");
		// �жϽ�ʦ��ѧ��
		
		HashMap<String, String> stuInfo = new HashMap<String, String>();
		if("dgsh".equalsIgnoreCase(doType)){
			myForm.setPrimarykey_checkVal(new String[]{pkValue});
			myForm.setShsj(GetTime.getNowTime2());
			myForm.setShr(user.getUserName());
			service.updateShzt(myForm);
		}
		if(!Base.isNull(pkValue)){
			String[]pkArr=pkValue.split("!!@@!!");
			String xh=pkArr[0];
			String stdm=pkArr[1];
			
			stuInfo.putAll(service.getXsstMap(myForm));
			
			request.setAttribute("xh", xh);
			request.setAttribute("stdm", stdm);
			
			request.setAttribute("pkValue", pkValue);
		}
		
		
		
		request.setAttribute("path", "stgl_xsstsq.do");
		request.setAttribute("doType", "dgsh");
		request.setAttribute("rs", stuInfo);
		request.setAttribute("rtsj", GetTime.getNowTime2());
		request.setAttribute("stxxList", service.getStxxList(myForm));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsstSq");
	}

	/**
	 * ���Ÿɲ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsstgbSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CdtyXsstService service=new CdtyXsstService();
		CdtyXsstForm myForm=(CdtyXsstForm)form;
		
		User user=getUser(request);
		String userType=user.getUserType();
		String xh=request.getParameter("xh");
		String doType=request.getParameter("doType");
		
		HashMap<String, String> stuInfo = new HashMap<String, String>();
		if("save".equalsIgnoreCase(doType)){
			myForm.setSqsj(GetTime.getNowTime2());
			service.saveXsstgb(myForm);
		}
		
		if ("stu".equalsIgnoreCase(userType) && Base.isNull(xh)) {
			// �����ѧ��ֱ��ȡ�û���
			xh = user.getUserName();

		} else  {
			// ����ǽ�ʦ��request�л��ѧ��
			xh = request.getParameter("xh");
			request.setAttribute("doType", "add");
		}
		
		if(!Base.isNull(xh)){
			myForm.setXh(xh);
			stuInfo.putAll(service.getStxsByXh(myForm));
			
			if(!(stuInfo!=null && stuInfo.size()>0)){
				String msg = "";
				if("stu".equalsIgnoreCase(userType)){
					msg = "��û�вμ��κ����Ų����������Ÿɲ�����ȷ�ϣ�";
				}else{
					msg = "��ѧ����δ�μ��κ������޷��������Ÿɲ�����ȷ�ϣ�";
				}
				
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
			}
		}
		
		// -------ѧ��������Ϣ-------
		request.setAttribute("stList", service.getStByXh(myForm));
		request.setAttribute("rs", stuInfo);
		request.setAttribute("stgbList", service.getStgbList(myForm));
		
		request.setAttribute("path", "stgl_stgbsq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsstgbSq");
	}
	
	/**
	 * ���Ÿɲ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsstgbDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdtyXsstService service = new CdtyXsstService();
		CdtyXsstForm myForm = (CdtyXsstForm) form;

		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		// �жϽ�ʦ��ѧ��

		HashMap<String, String> stuInfo = new HashMap<String, String>();
		if ("dgsh".equalsIgnoreCase(doType)) {
			myForm.setPrimarykey_checkVal(new String[] { pkValue });
			myForm.setShsj(GetTime.getNowTime2());
			myForm.setShr(user.getUserName());
			service.updateShzt(myForm);
		}
		if (!Base.isNull(pkValue)) {
			String[] pkArr = pkValue.split("!!@@!!");
			String xh = pkArr[0];
			String stdm = pkArr[1];

			stuInfo.putAll(service.getXsstMap(myForm));

			request.setAttribute("xh", xh);
			request.setAttribute("stdm", stdm);

			request.setAttribute("pkValue", pkValue);
		}

		
		//		 -------ѧ��������Ϣ-------
		request.setAttribute("stList", service.getStByXh(myForm));	
		request.setAttribute("stgbList", service.getStgbList(myForm));
		request.setAttribute("path", "stgl_xsstsq.do");
		request.setAttribute("doType", "dgsh");
		request.setAttribute("rs", stuInfo);
		request.setAttribute("rtsj", GetTime.getNowTime2());
		request.setAttribute("stxxList", service.getStxxList(myForm));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsstgbSq");
	}
	
	
	/**
	 * ���Ÿɲ���ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stgbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CdtyXsstService service=new CdtyXsstService();
		CdtyXsstForm myForm=(CdtyXsstForm)form;
		
		String doType=request.getParameter("doType");
		
		//ɾ������
		if("del".equalsIgnoreCase(doType)){
			
			service.delXsstInfo(myForm);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("rs",service.getStgbInfo(myForm));
		request.setAttribute("topTr", service.getTopTr("sqcx"));
		
		request.setAttribute("path", "stgl_stgbcx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("stgbManage");
	}

	/**
	 * ���Ÿɲ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsstgbSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdtyXsstService service=new CdtyXsstService();
		CdtyXsstForm myForm=(CdtyXsstForm)form;
		User user = getUser(request);
		String doType=request.getParameter("doType");
		String shzt=request.getParameter("shzt");
		
		if("plsh".equalsIgnoreCase(doType)){
			myForm.setShzt(shzt);
			myForm.setShsj(GetTime.getNowTime2());
			myForm.setShr(user.getUserName());
			service.updateShzt(myForm);	
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("rs",service.getStgbInfo(myForm));
		request.setAttribute("topTr", service.getTopTr("sqcx"));
		request.setAttribute("path", "stgl_stgbsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsstgbSh");
	}
	
	/**
	 * ���Ÿɲ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmxxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CdtyXsstService service=new CdtyXsstService();
		CdtyXsstForm myForm=(CdtyXsstForm)form;

		List<String[]>stxxInfo=new ArrayList<String[]>();
		
		stxxInfo=service.getStxxInfo(myForm);
		
		
		request.setAttribute("topTr", service.getTopTr("xzst"));
		request.setAttribute("stxxInfo", stxxInfo);
		return mapping.findForward("xmxxInfo");
	}
}
