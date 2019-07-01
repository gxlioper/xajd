package xgxt.pjpy.tyb.zhszcp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sun.tools.xjc.reader.RawTypeSet.Mode;

import common.GlobalsVariable;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZctjszModel;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszcpModel;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszfsModel;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZcsjwhService;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyService;
import xgxt.utils.FormModleCommon;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

public class PjpyZcsjwhAction extends CommonAction {

	
	/**
	 * �ۺ����ʲ�������ά��(�ڶ���) 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcsjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//��ȡ����ֵ
		PjpyZhszcpwhActionForm zcForm = (PjpyZhszcpwhActionForm) form;
		String dmlb = request.getParameter("dmlb");
		String dm = request.getParameter("dm");
		
		//����Ĭ����������
		setDefaultPjxnxqnd(zcForm);
		
		//�۲⿪������
		User user = getUser(request);
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(user.getUserType()) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_ZHSZCP)
				&& zjkjService.checkKgflag()){
			String msg = "�ù�����ʱ�����Ų�����";
			request.setAttribute("yhInfo", msg);
		}
		
		if (GlobalsVariable.XTDM_XY.equalsIgnoreCase(user.getUserType())) {
			zcForm.setXydm(user.getUserDep());
		} else if (GlobalsVariable.XTDM_STU
				.equalsIgnoreCase(user.getUserType())
				|| GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(user
						.getUserType())) {
			request.setAttribute("errMsg", "�Բ���,����Ȩ���ʴ�ҳ��");
			return new ActionForward("/errMsg.do",false);
		}
		
		request.setAttribute("pjzq", Base.getZczq());
		request.setAttribute("dm", dm);
		request.setAttribute("dmlb", dmlb);
		appendOperQx(request, "pjpy_tyb_zcsjwh.do?dmlb=" + dmlb + "&dm=" + dm);
        FormModleCommon.commonRequestSet(request);
        FormModleCommon.setNjXyZyBjListForFdyBzr(request);
        FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("zcsjwh_second");
	}

	/**
	 * �ۺ����ʲ�������ά��(�ڶ���) ��ѯ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcsjwhQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//��ȡ��������
		PjpyZhszcpwhActionForm zcForm = (PjpyZhszcpwhActionForm) form;
		
		//����Ĭ����������
		setDefaultPjxnxqnd(zcForm);
		
		PjpyZhszcpModel model = new PjpyZhszcpModel();
		BeanUtils.copyProperties(model, zcForm);
		String dmlb = request.getParameter("dmlb");
		String dm = request.getParameter("dm");
		PjpyZctjszModel zctjModel = getZctjModel(dmlb, dm);
		model.setZcfdm(dm);
		model.setZcdmjb(dmlb);
		PjpyZcsjwhService service = new PjpyZcsjwhService();
		
		//��ѯ���
		String pjzq = Base.getZczq();
		List<HashMap<String, String>> topTr = service.queryZcsjwhTitle(pjzq);
		List<String[]> rs = service.queryZcsjwhResult(model, pjzq, zctjModel,true);
		
		//�¼���������
		List<HashMap<String, String>> dmList = service.queryZctjdmList(zctjModel);
		
		//����ҳ��չ���ı������ʼ����
		int dmNum = !dmList.isEmpty() ? dmList.size() : 0;
		String[] array = !rs.isEmpty() ? rs.get(0) : new String[]{};
		int rsNum = array != null ? array.length : 0;
		
		request.setAttribute("dmNum", rsNum - dmNum > 0 ? rsNum - dmNum - 1 : 0);
		//request.setAttribute("lenNum", rsNum - dmNum > 0 ? rsNum - dmNum : 0);
		appendQryResult(request, topTr, rs);
		
		//��Ŀ�б�
		request.setAttribute("xmList", dmList);
		return zcsjwh(mapping, form, request, response);
	}
	
	/**
	 * �����۲����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcfsSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZhszcpwhActionForm myForm = (PjpyZhszcpwhActionForm) form;
		PjpyZhszfsModel model = new PjpyZhszfsModel();
		BeanUtils.copyProperties(model, myForm);
		model.setDm(myForm.getDms());
		model.setXh(myForm.getXhs());
		model.setJb(request.getParameter("dmlb"));
		
		PjpyZcsjwhService service = new PjpyZcsjwhService();
		request.setAttribute("result", service.savelrf(model));
		return zcsjwhQuery(mapping, myForm, request, response);
	}
	
	/**
	 * ��ʼ���۲����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initZcf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String dmlb = request.getParameter("dmlb");
		String dm = request.getParameter("dm");
		
		PjpyZhszcpwhActionForm myForm = (PjpyZhszcpwhActionForm) form;
		setDefaultPjxnxqnd(myForm);
		
		PjpyZhszcpModel model = new PjpyZhszcpModel();
		BeanUtils.copyProperties(model, myForm);
		
		model.setZcfdm(dm);
		model.setZcdmjb(dmlb);
			
		PjpyZcsjwhService service = new PjpyZcsjwhService();
		
		service.initZcf(model);
		
		request.setAttribute("result", service.initZcf(model));
		return zcsjwhQuery(mapping, form, request, response);
	}
	
	/**
	 * �۲����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcfsDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZhszcpwhActionForm myForm = (PjpyZhszcpwhActionForm) form;
		
		String[] pkValues = request.getParameterValues("primarykey_cbv");
		PjpyZcsjwhService service = new PjpyZcsjwhService();
		boolean result = service.dellrf(pkValues);
		
		request.setAttribute("result", result);
		return zcsjwhQuery(mapping, myForm, request, response);
	}
	
	/**
	 * ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dataExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZhszcpwhActionForm zcForm = (PjpyZhszcpwhActionForm) form;
		PjpyZhszcpModel model = new PjpyZhszcpModel();
		BeanUtils.copyProperties(model, zcForm);
		String dmlb = request.getParameter("dmlb");
		String dm = request.getParameter("dm");
		PjpyZctjszModel zctjModel = getZctjModel(dmlb, dm);
		model.setZcfdm(dm);
		model.setZcdmjb(dmlb);
		PjpyZcsjwhService service = new PjpyZcsjwhService();
		
		//��ѯ���
		String pjzq = Base.getZczq();
		List<HashMap<String, String>> topTr = service.queryZcsjwhTitle(zctjModel, Base.getZczq());
		List<String[]> rs = service.queryZcsjwhResult(model, pjzq, zctjModel, false);
		
		String[] title = new String[topTr.size()];
		
		for(int i=0;i<topTr.size();i++){
			title[i] = topTr.get(i).get("cn");
		}
		response.reset();
		response.setContentType("application/vnd.ms-excel");	
		service.dataExport("�۲���Ϣ�������ʷ�", title, rs, response.getOutputStream());
		return null;
	}
	
	/**
	 * ����Ĭ��ѧ�꣬ѧ�ڣ����
	 * @param zcForm
	 */
	public void setDefaultPjxnxqnd(PjpyZhszcpwhActionForm zcForm) {
		String pjzq = Base.getZczq();
		if ("nd".equalsIgnoreCase(pjzq)) {
			zcForm.setNd(Base.getJxjsqnd());
		} else if ("xq".equalsIgnoreCase(pjzq)) {
			zcForm.setXq(Base.getJxjsqxq());
			zcForm.setXn(Base.getJxjsqxn());
		} else if ("xn".equalsIgnoreCase(pjzq)) {
			zcForm.setXn(Base.getJxjsqxn());
		}
	}
	
	public PjpyZctjszModel getZctjModel(String dmjb, String dm) {
		PjpyZctjszModel model = new PjpyZctjszModel();
		model.setDmjb(dmjb);
		model.setFdm(dm);
		model.setSfwh("1");
		return model;
	}
	
}
