package xgxt.pjpy.zjcm.cssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.GlobalsVariable;

import xgxt.action.Base;
import xgxt.pjpy.zjcm.PjpyZjcmActionForm;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyZjcmCsszAction extends CommonAction {

	
	/**
	 * ������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rsblsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		
		//�����жϷ�ѧУ�û����ܽ����ģ��
		if (GlobalsVariable.XTDM_XY.equalsIgnoreCase(request.getSession()
				.getAttribute("userType").toString())
				|| "true".equalsIgnoreCase(request.getSession().getAttribute(
						"isFdy").toString())
				|| GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(request
						.getSession().getAttribute("userType").toString())) {
			request.setAttribute("errMsg", "��ǰ�û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		
		setXydm(request, zjcmForm);
		setPjxnndxq(zjcmForm);
		
		if (StringUtils.isNull(zjcmForm.getKey())) {
			zjcmForm.setKey(GlobalsVariable.PJPY_JXJ);
		}
		
		//��ѯ���ݲ���
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		if (QUERY.equalsIgnoreCase(zjcmForm.getAct())) {
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, zjcmForm);
			topTr = service.queryJxjrsTitle(zjcmForm.getKey());
			rs = (ArrayList<String[]>) service.queryJxjrsResult(model, zjcmForm.getKey());
		}
		
		//�����������������
		request.setAttribute("path", "prise_conf_rs.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("dmList", service.getDmList(zjcmForm.getKey(),
				zjcmForm.getJxjlb()));
		request.setAttribute("lbList", service.getJxjlbList(true));
		request.setAttribute("cpfwList", service.getCpfwList(false));
		return mapping.findForward("rsblsz");
	}	
	
	/**
	 * ��ѧ������������ù���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjblPlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		HashMap<String, String> rs = new HashMap<String, String>();
		//����Ĭ�ϵ�����ѧ�꣬ѧ�ڣ����
		rs.put("xn", Base.getJxjsqxn());
		rs.put("xq", Base.getJxjsqxq());
		rs.put("nd", Base.getJxjsqnd());
		
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, zjcmForm);
			model.setKey(GlobalsVariable.PJPY_JXJ);
			appendOperResult(request, service.updateJxjrs(model));
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("cpfwList", service.getCpfwList(false));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("dmList", service.getDmList(GlobalsVariable.PJPY_JXJ,
				zjcmForm.getJxjlb()));
		request.setAttribute("lbList", service.getJxjlbList(true));
		return mapping.findForward("jxjblPlsz");
	}
	
	/**
	 * �������ݳ�ʼ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward baseDataInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String lb = request.getParameter("lb");
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		appendOperResult(request, service.baseDataInit(lb));
		//���ݴ�������������ж���תҳ��
		if (GlobalsVariable.PJPY_JXJ.equalsIgnoreCase(lb)) {
			return rsblsz(mapping, form, request, response);
		} else {
			return rychRsblsz(mapping, form, request, response);
		}
		
	}

	/**
	 * ��ѧ�𵥸���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjrstz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		String pkValue = zjcmForm.getPkValue();
		HashMap<String, String> rs = new HashMap<String, String>();
		
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			appendOperResult(request, service.updateJxjDgtzrs(pkValue, zjcmForm
					.getJxjrs()));
		} else if (VIEW.equalsIgnoreCase(request.getParameter("act"))) {//���ö�дȨ��
			request.setAttribute("writable", "view");
		}
		
		//��ѯ�������ݲ���
		rs = service.queryDgJxjrsxx(pkValue);
		rs.put("pkValue", pkValue);
		request.setAttribute("rs", rs);
		request.setAttribute("cpfwList", service.getCpfwList(false));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("dmList", service.getDmList(GlobalsVariable.PJPY_JXJ,
				zjcmForm.getJxjlb()));
		request.setAttribute("lbList", service.getJxjlbList(true));
		return mapping.findForward("jxjrstz");
	}
	
	/**
	 * ��ѧ��������ܱ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjblFpb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
		BeanUtils.copyProperties(model, zjcmForm);
		//����Ĭ�ϵ�����ѧ�꣬ѧ�ڣ����
		model.setXn(Base.getJxjsqxn());
		model.setXq(Base.getJxjsqxq());
		model.setNd(Base.getJxjsqnd());
		model.setXymc(request.getParameter("xymc"));
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response); 
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		service.exportJxjblFpb(wwb, model);
		return mapping.findForward("");
	}
	
	/**
	 * �����ƺ�������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychRsblsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		
		//�����жϷ�ѧУ�û����ܽ����ģ��
		if (GlobalsVariable.XTDM_XY.equalsIgnoreCase(request.getSession()
				.getAttribute("userType").toString())
				|| "true".equalsIgnoreCase(request.getSession().getAttribute(
						"isFdy").toString())
				|| GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(request
						.getSession().getAttribute("userType").toString())) {
			request.setAttribute("errMsg", "��ǰ�û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		
		setXydm(request, zjcmForm);
		setPjxnndxq(zjcmForm);
		
		if (StringUtils.isNull(zjcmForm.getKey())) {
			zjcmForm.setKey(GlobalsVariable.PJPY_RYCH);
		}
		
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		//��ѯ���ݲ���
		if (QUERY.equalsIgnoreCase(zjcmForm.getAct())) {
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, zjcmForm);
			topTr = service.queryJxjrsTitle(zjcmForm.getKey());
			rs = (ArrayList<String[]>) service.queryJxjrsResult(model, zjcmForm.getKey());
		}
		
		request.setAttribute("path", "prise_conf_rs.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("dmList", service.getDmList(zjcmForm.getKey(),
				zjcmForm.getJxjlb()));
		request.setAttribute("cpfwList", service.getCpfwList(false));
		return mapping.findForward("rychRsblsz");
	}
	
	/**
	 * �����ƺű�����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychblPlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		HashMap<String, String> rs = new HashMap<String, String>();
		//����Ĭ�ϵ�����ѧ�꣬ѧ�ڣ����
		rs.put("xn", Base.getJxjsqxn());
		rs.put("xq", Base.getJxjsqxq());
		rs.put("nd", Base.getJxjsqnd());
		
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, zjcmForm);
			model.setKey(GlobalsVariable.PJPY_RYCH);
			appendOperResult(request, service.updateJxjrs(model));
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("cpfwList", service.getCpfwList(false));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("dmList", service.getDmList(GlobalsVariable.PJPY_RYCH,
				zjcmForm.getJxjlb()));
		
		return mapping.findForward("rychblPlsz");
	}
	
	/**
	 * �����ƺŵ�����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychrstz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		String pkValue = zjcmForm.getPkValue();
		HashMap<String, String> rs = new HashMap<String, String>();
		
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {//�������ݲ���
			appendOperResult(request, service.updateJxjDgtzrs(pkValue, zjcmForm
					.getJxjrs()));
		} else if (VIEW.equalsIgnoreCase(request.getParameter("act"))) {//���ö�дȨ��
			request.setAttribute("writable", "view");
		}
		//��ѯ��������
		rs = service.queryDgJxjrsxx(pkValue);
		rs.put("pkValue", pkValue);
		request.setAttribute("rs", rs);
		request.setAttribute("cpfwList", service.getCpfwList(false));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("dmList", service.getDmList(GlobalsVariable.PJPY_RYCH,
				zjcmForm.getJxjlb()));
		
		return mapping.findForward("rychrstz");
	}
	
	//�����ѧԺ�û�������Ĭ�ϲ��Ŵ���
	public void setXydm(HttpServletRequest request, PjpyZjcmActionForm form) {
		String userType = request.getSession().getAttribute("userType").toString();
		if (GlobalsVariable.XTDM_XY.equalsIgnoreCase(userType)) {
			form.setXydm(request.getAttribute("userDep").toString());
		}
	}
	
	//��������ѧ�꣬��ȣ�ѧ��
	public void setPjxnndxq(PjpyZjcmActionForm form) {
		if (StringUtils.isNull(form.getXn())) {
			form.setXn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(form.getNd())) {
			form.setNd(Base.getJxjsqnd());
		}
		if (StringUtils.isNull(form.getXq())) {
			form.setXq(Base.getJxjsqxq());
		}
	}
}
