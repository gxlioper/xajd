/*
 * @className: XljkXlcyjgAction.java
 * 
 * @time: 2010-4-30
 * 
 * @copyRight hz-zf
 * 
 */

package xgxt.xljk.tyb.action;

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

import common.GlobalsVariable;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.xljk.tyb.model.XljkXlcyjgModel;
import xgxt.xljk.tyb.service.XljkXlcyjgService;

/**
 * ������ - ���������ά�����õ�ACTION, �����Դ�ѧ���˸�����������Խ���Ϳ��ض��˸����ز��Խ����ά��
 * 
 * @author lt
 * @version 1.0 2010-4-30
 */
public class XljkXlcyjgAction extends CommonAction {

	/**
	 * ��ѧ���˸����ز�����ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rgyswjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		XljkXlcyjgActionForm xljkForm = (XljkXlcyjgActionForm) form;
		XljkXlcyjgService service = new XljkXlcyjgService();

		setXydm(xljkForm, request);// ����Ĭ��ѧԺ����

		/* ��ѯ���ݲ��� */
		if (QUERY.equalsIgnoreCase(xljkForm.getAct())) {
			XljkXlcyjgModel model = new XljkXlcyjgModel();
			BeanUtils.copyProperties(model, xljkForm);
			rs = service.queryDxsrgysjgResult(model, isFdyAndGetName(request));
			topTr = service.queryDxsrgysclJgTitle();
			
			/* ��ѯ��ѧ���˸����Ӳ����ڵ��������� */
			request.setAttribute("yzmc", service.queryDxsyzNotExistsDB());
		} else if (DELETE.equalsIgnoreCase(xljkForm.getAct())) {
			appendOperResult(request, service.deletDxsrgyscyXx(xljkForm.getCbv(), "dxsrgyscyb"));
		}

		appendOperQx(request, "xljk_tyb_rgyswjwh.do"); // ����ҳ���дȨ
		appendQryResult(request, topTr, rs); // ����ҳ���ѯ���
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		FormModleCommon.getWriteAbleAndTitle(request);
		
		return mapping.findForward("rgyswjwh");
	}
	
	/**
	 * ���Ӵ�ѧ���˸��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addDxsrgyscy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XljkXlcyjgActionForm xljkForm = (XljkXlcyjgActionForm) form;
		XljkXlcyjgService service = new XljkXlcyjgService();
		HashMap<String, String> rs = new HashMap<String, String>();
		
		if (StringUtils.isNotNull(xljkForm.getXh())) {
			rs = CommonQueryDAO.getStuInfo(xljkForm.getXh());
		}
		
		/* �������ݲ��� */
		if (SAVE.equalsIgnoreCase(xljkForm.getAct())) {
			XljkXlcyjgModel model = new XljkXlcyjgModel();
			BeanUtils.copyProperties(model, xljkForm);
			appendOperResult(request, service.addDxsrgyscyXx(model));
		}
		
		request.setAttribute("rs", rs);
		return mapping.findForward("addDxsrgyscy");
	}
	
	/**
	 * ��ʾ���޸Ĵ�ѧ���˸��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateDxsrgyscy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		XljkXlcyjgActionForm xljkForm = (XljkXlcyjgActionForm) form;
		XljkXlcyjgService service = new XljkXlcyjgService();
		List<String[]> pyList = new ArrayList<String[]>();
		
		/* �������ݲ��� */
		if (SAVE.equalsIgnoreCase(xljkForm.getAct())) {
			XljkXlcyjgModel model = new XljkXlcyjgModel();
			BeanUtils.copyProperties(model, xljkForm);
			appendOperResult(request, service.updateDxsrgyscyXx(model, pkValue));
		} else if (VIEW.equalsIgnoreCase(xljkForm.getAct())) {//��ʾ���ݶ�дȨ
			request.setAttribute("writable", "view");
		}
		
		/* ��ʾ���� */
		rs = service.viewDxsrgyscyXx(pkValue);
		if (!rs.isEmpty()) {
			BeanUtils.copyProperties(xljkForm, rs);
			
			/* ��ʾ���������ά���ж�Ӧ����ϸ���� */
			pyList = service.queryDxsrgpyResult(rs);
			request.setAttribute("yzmc", service.queryDxsyzNotExistsDB());
		}
		
		request.setAttribute("pyList", pyList);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		return mapping.findForward("updateDxsrgyscy");
	}
	
	/**
	 * ���ض��˸��������������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ktewjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		XljkXlcyjgActionForm xljkForm = (XljkXlcyjgActionForm) form;
		XljkXlcyjgService service = new XljkXlcyjgService();

		setXydm(xljkForm, request);// ����Ĭ��ѧԺ����

		/* ��ѯ���ݲ��� */
		if (QUERY.equalsIgnoreCase(xljkForm.getAct())) {
			XljkXlcyjgModel model = new XljkXlcyjgModel();
			BeanUtils.copyProperties(model, xljkForm);
			rs = service.queryKtergysResult(model, isFdyAndGetName(request));
			topTr = service.queryKtergysTitle();
			
			/* δά���Ŀ��ض����Ӵ��� */
			request.setAttribute("yzmc", service.queryKteyzNotExistsDB());
		} else if (DELETE.equalsIgnoreCase(xljkForm.getAct())) {
			appendOperResult(request, service.deletDxsrgyscyXx(xljkForm.getCbv(), "ktergysjcb"));
		}

		appendOperQx(request, "xljk_tyb_ktewjwh.do"); // ����ҳ���дȨ
		appendQryResult(request, topTr, rs); // ����ҳ���ѯ���
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		FormModleCommon.getWriteAbleAndTitle(request);
		
		return mapping.findForward("ktewjwh");
	}
	
	/**
	 * ���ӿ��ض��˸��������������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addKtewjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XljkXlcyjgActionForm xljkForm = (XljkXlcyjgActionForm) form;
		XljkXlcyjgService service = new XljkXlcyjgService();
		HashMap<String, String> rs = new HashMap<String, String>();
		
		if (StringUtils.isNotNull(xljkForm.getXh())) {
			rs = CommonQueryDAO.getStuInfo(xljkForm.getXh());
		}
		
		/* �������ݲ��� */
		if (SAVE.equalsIgnoreCase(xljkForm.getAct())) {
			XljkXlcyjgModel model = new XljkXlcyjgModel();
			BeanUtils.copyProperties(model, xljkForm);
			appendOperResult(request, service.addKtergysxx(model));
		}
		
		request.setAttribute("rs", rs);
		return mapping.findForward("addKtewjwh");
	}
	
	/**
	 * �޸Ŀ��ض��˸��������������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateKtewjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		XljkXlcyjgActionForm xljkForm = (XljkXlcyjgActionForm) form;
		XljkXlcyjgService service = new XljkXlcyjgService();
		List<String[]> pyList = new ArrayList<String[]>();
		
		/* �������ݲ��� */
		if (SAVE.equalsIgnoreCase(xljkForm.getAct())) {
			XljkXlcyjgModel model = new XljkXlcyjgModel();
			BeanUtils.copyProperties(model, xljkForm);
			appendOperResult(request, service.updateKtergysxx(model, pkValue));
		} else if (VIEW.equalsIgnoreCase(xljkForm.getAct())) {//��ʾ���ݶ�дȨ
			request.setAttribute("writable", "view");
		}
		
		/* ��ʾ���� */
		rs = service.viewKtergysxx(pkValue);
		if (!rs.isEmpty()) {
			BeanUtils.copyProperties(xljkForm, rs);
			pyList = service.queryKteyzpyResult(rs);
			/* δά���Ŀ��ض����Ӵ��� */
			request.setAttribute("yzmc", service.queryKteyzNotExistsDB());
		}
		
		request.setAttribute("pyList", pyList);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		return mapping.findForward("updateKtewjwh");
	}

	/**
	 * ����ѧԺ�û�Ĭ�ϵ�ѧԺ���룬��������Ǹ���Ա�û���������
	 * @param form
	 * @param request
	 */
	private void setXydm(XljkXlcyjgActionForm form, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") != null ? session
				.getAttribute("userType").toString() : "";
		String userDep = session.getAttribute("userDep") != null ? session
				.getAttribute("userDep").toString() : "";
		String fdyQx = session.getAttribute("fdyQx") != null ? session
				.getAttribute("fdyQx").toString() : "false";
				
		if (GlobalsVariable.XTDM_XY.equalsIgnoreCase(userType)
				&& !Boolean.parseBoolean(fdyQx)) {
			form.setXydm(userDep);
		}
	}
	
	private String isFdyAndGetName(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String isFdy = session.getAttribute("isFdy") != null ? session
				.getAttribute("isFdy").toString() : "false";
		if (UserTypePd.isFdy(isFdy)) {
			return session.getAttribute("userName").toString();
		} 
		return "";
	}
}
