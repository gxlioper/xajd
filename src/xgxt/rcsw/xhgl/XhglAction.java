package xgxt.rcsw.xhgl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.szyqxy.zhszcp.PjpySzyqxyZhszcpDAO;
import xgxt.rcsw.xszgl.XszglService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

public class XhglAction extends BasicAction {

	
//	 ====================================У�շ��� begin============================================
	/**
	 * У�շ��Ź���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xhffManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		XhglForm myForm = (XhglForm) form;
		XhglService service = new XhglService();
		XszglService xszglService = new XszglService();
		CommService commService=new CommService();

		String doType=request.getParameter("doType");
		String ffsj=request.getParameter("hid_ffsj");
		myForm.setFfsj(ffsj);
		myForm.setUser(user);
		String message="";
		
		// -----------ȡ������-------------
		if("del".equalsIgnoreCase(doType)){
			boolean flag=service.qxxhffBatch(myForm);
			message = flag ? MessageInfo.MESSAGE_CANCEL_SUCCESS
					: MessageInfo.MESSAGE_CANCEL_FALSE;
			request.setAttribute("message", message);
		}
		
		// -----------��������---------
		if("plff".equalsIgnoreCase(doType)){
			boolean flag=service.xhffBatch(myForm);
		
			message = flag ? MessageInfo.MESSAGE_EXTEND_SUCCESS
					: MessageInfo.MESSAGE_EXTEND_FALSE;
			request.setAttribute("message", message);
		}
		
//		 ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		// ��ʾ����
		String showNum = String.valueOf(service.getTopTr("xhff",rForm).size()-1);
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);
		
		myForm.setUser(user);
		
		if(Base.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		
		if(Base.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		
		if(Base.isNull(myForm.getNd())){
			myForm.setNd(Base.currNd);
		}
		
		rForm.setRsArrList((ArrayList<String[]>)service.queryXhgl(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============ͨ������ end ============
		
		
		request.setAttribute("path", "xhgl.do?method=xhffManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.currXq);
		request.setAttribute("dqnd", Base.currNd);
		request.setAttribute("jbrList", xszglService.getJbrList());
		request.setAttribute("topTr", service.getTopTr("xhff",rForm));
		return mapping.findForward("xhffManage");
	}
	
	/**
	 * У�շ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xhffUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
		XszglService xszglService = new XszglService();
		User user=getUser(request);
		XhglForm myForm = (XhglForm) form;
		XhglService service = new XhglService();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		myForm.setUser(user);
		
		// =================ѧ��֤ע�� begin==================
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.addXhff(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		// =================ѧ��֤ע�� end==================
		
		request.setAttribute("path", "xhgl.do?method=xhffManage");
		FormModleCommon.commonRequestSet(request);
		
		// ============ѧ�ꡢѧ�ڡ�ѧ������ begin====================
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("xqmc", pjpyDAO.getXqmc(Base.currXq));
		// ============ѧ�ꡢѧ�ڡ�ѧ������ end====================
		request.setAttribute("jbrList", xszglService.getJbrList());
		request.setAttribute("rs", stuInfo);
		request.setAttribute("doType", "add");
		return mapping.findForward("xhffUpdate");
	}
	
	/**
	 * У�շ��ţ���ѯ���޸ģ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xhffOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
		XszglService xszglService = new XszglService();
		XhglForm myForm = (XhglForm) form;
		XhglService service = new XhglService();
		
		String doType=request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		
		HashMap<String,String>rs=new HashMap<String,String>();
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			myForm.setPkValue(pkValue);
			boolean flag = service.updateXhff(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		
		// ѧ��֤ע����Ϣ
		myForm.setPkValue(pkValue);
		rs=service.getXhglMap(myForm);
		
		request.setAttribute("path", "zgmsxy_xszz.do?method=syddkManage");
		FormModleCommon.commonRequestSet(request);
		
		// ============ѧ�ꡢѧ�ڡ�ѧ������ begin====================
		request.setAttribute("xn", rs.get("xn"));
		request.setAttribute("xq", rs.get("xq"));
		request.setAttribute("nd", rs.get("nd"));
		request.setAttribute("xqmc", pjpyDAO.getXqmc(rs.get("xq")));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("jbrList", xszglService.getJbrList());
		// ============ѧ�ꡢѧ�ڡ�ѧ������ end====================
		request.setAttribute("rs", rs);
		request.setAttribute("doType",doType);
		return mapping.findForward("xhffUpdate");
	}
	
	
	
//	 ====================================У�շ��� end============================================
	/**
	 * У�շ��Ź���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xhbbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		XhglForm myForm = (XhglForm) form;
		XhglService service = new XhglService();
		CommService commService=new CommService();

		String doType=request.getParameter("doType");
		String ffsj=request.getParameter("hid_ffsj");
		myForm.setFfsj(ffsj);
		myForm.setUser(user);
		String message="";
		
		// -----------ȡ������-------------
		if("del".equalsIgnoreCase(doType)){
			boolean flag=service.qxxhffBatch(myForm);
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", message);
		}
		
		// -----------��������---------
		if("plff".equalsIgnoreCase(doType)){
			boolean flag=service.xhffBatch(myForm);
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", message);
		}
		
//		 ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		// ��ʾ����
		String showNum = String.valueOf(service.getTopTr("xhff",rForm).size()-1);
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);
		
		myForm.setUser(user);
		
		if(Base.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		
		if(Base.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		
		if(Base.isNull(myForm.getNd())){
			myForm.setNd(Base.currNd);
		}
		
		rForm.setRsArrList((ArrayList<String[]>)service.queryXhgl(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============ͨ������ end ============
		
		
		request.setAttribute("path", "xhgl.do?method=xhffManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.currXq);
		request.setAttribute("dqnd", Base.currNd);
		request.setAttribute("topTr", service.getTopTr("xhff",rForm));
		return mapping.findForward("xhbbManage");
	}
	
	/**
	 * У�շ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xhbbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
		User user=getUser(request);
		XhglForm myForm = (XhglForm) form;
		XhglService service = new XhglService();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		myForm.setUser(user);
		
		// =================ѧ��֤ע�� begin==================
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.addXhff(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		// =================ѧ��֤ע�� end==================
		
		request.setAttribute("path", "zgmsxy_xszz.do?method=syddkManage");
		FormModleCommon.commonRequestSet(request);
		
		// ============ѧ�ꡢѧ�ڡ�ѧ������ begin====================
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("xqmc", pjpyDAO.getXqmc(Base.currXq));
		// ============ѧ�ꡢѧ�ڡ�ѧ������ end====================
		request.setAttribute("rs", stuInfo);
		request.setAttribute("doType", "add");
		return mapping.findForward("xhbbUpdate");
	}
	
	/**
	 * У�շ��ţ���ѯ���޸ģ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xhbbOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
	
		XhglForm myForm = (XhglForm) form;
		XhglService service = new XhglService();
		
		String doType=request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		
		HashMap<String,String>rs=new HashMap<String,String>();
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			myForm.setPkValue(pkValue);
			boolean flag = service.updateXhff(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		
		// ѧ��֤ע����Ϣ
		myForm.setPkValue(pkValue);
		rs=service.getXhglMap(myForm);
		
		request.setAttribute("path", "zgmsxy_xszz.do?method=syddkManage");
		FormModleCommon.commonRequestSet(request);
		
		// ============ѧ�ꡢѧ�ڡ�ѧ������ begin====================
		request.setAttribute("xn", rs.get("xn"));
		request.setAttribute("xq", rs.get("xq"));
		request.setAttribute("nd", rs.get("nd"));
		request.setAttribute("xqmc", pjpyDAO.getXqmc(rs.get("xq")));
		request.setAttribute("pkValue", pkValue);
		// ============ѧ�ꡢѧ�ڡ�ѧ������ end====================
		request.setAttribute("rs", rs);
		request.setAttribute("doType",doType);
		return mapping.findForward("xhbbUpdate");
	}
	

//	 ====================================У�ղ��� begin============================================
//	 ====================================У�ղ��� end============================================
	
	
	
	
//	
//	
//	
//	/**
//	 * ���Żݿ��������
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	public ActionForward yhkAuditing(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		
//		HttpSession session = request.getSession();
//		String userName = (String) session.getAttribute("userName");
//		String userType = (String) session.getAttribute("userType");
//		String userDep = (String) session.getAttribute("userDep");
//		
//		HcyhkForm model = (HcyhkForm) form;
//		HcyhkService service = new HcyhkService();
//		RcswService rcswService = new RcswService();
//		String doType = request.getParameter("doType");
//		
//		String gnmc = AuditGnmc.HCYHKBB;
//		String[] gwmc = AuditUtil.getGwmcByGnmc(gnmc);
//		String[] yygw = AuditUtil.getGwmcByGnmcAndUser(gnmc,userName);
//		String[] topTr = new String[]{"pkValue","ѧ��","����","�꼶",
//				Base.YXPZXY_KEY+"����","רҵ����","�༶����","����ʱ��"};
//		
//		//�������
//		if(PLSH.equals(doType)){
//			model.setShr(userName);
//			model.setShsj(GetTime.getNowTime2());
//			String[] pkValues = request.getParameterValues("primarykey_cbv");
//			request.setAttribute("message", service.plshHcyhkbb(model, pkValues, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
//			
//			doType= QUERY;
//		}
//		
//		//ȡ�����
//		if(QXSH.equals(doType)){
//			model.setShjg("δ���");
//			model.setShsj("");
//			model.setShr("");
//			model.setShyj("");
//			
//			String[] pkValues = request.getParameterValues("primarykey_cbv");
//			request.setAttribute("message", service.plshHcyhkbb(model, pkValues, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
//			
//			doType= QUERY;
//		}
//		
//		//��ѯ
//		if(QUERY.equals(doType)){
//			if (StringUtils.isNull(model.getShgw())){
//				try{
//					model.setShgw(yygw[0]);
//				}catch(RuntimeException re){
//					catchSystemException(request,new SystemException("Error-1023"));
//				}
//			}
//			
//			User user = service.getUser(request);
//			String[] colList = new String[]{"disabled","isdis","pkValue","xh",
//								"xm","nj","xymc","zymc","bjmc","sqsj"};
//			HashMap<String,Object> map = service.queryHcyhkbbsh(user,model, colList,topTr,gwmc);
//			request.setAttribute("rs", map.get("result"));
//			topTr = (String[]) map.get("topTr");
//		}
//		
//		if ("xy".equals(userType)){
//			model.setXydm(userDep);
//		} else if("stu".equals(userType)){
//			request.setAttribute("message", "�Բ�������Ȩ���ʴ�ҳ");
//			return new ActionForward("/prompt.do",false);
//		}
//		
//		request.setAttribute("shjgList", rcswService.getSelectList("shjg"));
//		request.setAttribute("shztList", rcswService.getSelectList("shzt"));
//		request.setAttribute("yygw", yygw);//ӵ�и�λ
//		request.setAttribute("topTr", topTr);
//		request.setAttribute("maxNum", model.getPages().getPageSize());
//		request.setAttribute("path", "hcyhk.do?method=yhkAuditing");
//		FormModleCommon.commonRequestSet(request);
//		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
//		return mapping.findForward("yhkAuditing");
//	}
//	
//	
//	
//	/**
//	 * ���Żݿ����쵥�����
//	 * @return ActionForward
//	 */
//	@SuppressWarnings("unchecked")
//	public ActionForward hcyhkDgsh(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		
//		HcyhkForm model = (HcyhkForm) form;
//		HcyhkService service = new HcyhkService();
//		RcswService rcswService = new RcswService();
//		XsxxglService xsxxglService = new XsxxglService();
//		String gnmc =  AuditGnmc.HCYHKBB;
//		String tableName = "xg_rcsw_hcyhkbb";
//		String pkValue = request.getParameter("pkValue");
//		String doType = request.getParameter("doType");
//		
//		if (SAVE.equals(doType)){//������˱���
//			request.setAttribute("message", service.plshHcyhkbb(model, new String[]{pkValue}, gnmc) ? SAVE_SUCCESS : SAVE_FAIL);
//		}
//		
//		//���ص�����¼��Ϣ
//		selectPageDataByOne(request, tableName, tableName, pkValue);
//		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
//		//����ѧ����Ϣ
//		rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));
//		request.setAttribute("rs", rs);
//		//�����Ϣ
//		List<HashMap<String,String>> shxx = service.getHcyhkbbShxx(pkValue, gnmc);
//		
//		request.setAttribute("shxx", shxx);
//		request.setAttribute("shztList", rcswService.getSelectList("shzt"));
//		FormModleCommon.commonRequestSet(request);
//		//��������
//		request.setAttribute("shsj", GetTime.getNowTime2());//���ʱ��
//		request.setAttribute("doType", doType);
//		return mapping.findForward("hcyhkDgsh");
//	}
//	
//	
//	
//	/**
//	 * ���Żݿ������޸�
//	 * @return ActionForward
//	 */
//	@SuppressWarnings("unchecked")
//	public ActionForward hcyhkbbUpdate(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		
//		XsxxglService xsxxglService = new XsxxglService();
//		
//		String tableName = "xg_rcsw_hcyhkbb";
//		String doType = request.getParameter("doType");
//		
//		if(SAVE.equalsIgnoreCase(doType)){
//			updateOperation(request, tableName);
//		}
//		
//		String pkValue = request.getParameter("pkValue");			
//		selectPageDataByOne(request, tableName, tableName, pkValue);
//		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
//		rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));
//		
//		request.setAttribute("rs", rs);
//		return mapping.findForward("hcyhkbbUpdate");
//	}
}
