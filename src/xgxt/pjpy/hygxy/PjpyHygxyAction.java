
package xgxt.pjpy.hygxy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ������ѧԺ��������Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-13</p>
 */
public class PjpyHygxyAction extends DispatchAction {

	CommonAction commonAction = null;
//	private String xydm = "";
//	private String zydm = "";
//	private String nj = "";
	/**
	 * ��ѧ���������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjdmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyJxjdmService service = new PjpyJxjdmService();
		List<HashMap<String, String>> jxjlbList = service.getJxjlbList();
		request.setAttribute("jxjlbList", jxjlbList);
		request.setAttribute("writable", "yes");
		return mapping.findForward("jxjdmdefault");
	}
	
	/**
	 * ��ѧ����뱣��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjdmSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJxjdmService service = new PjpyJxjdmService();
		PjpyHygxyActionForm hygxyForm = (PjpyHygxyActionForm) form;
		List<HashMap<String, String>> jxjlbList = service.getJxjlbList();
		PjpyJxjdmModel model = new PjpyJxjdmModel();
		BeanUtils.copyProperties(model, hygxyForm);
		boolean bFlag = service.jxjdmSave(model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("jxjlbList", jxjlbList);
		request.setAttribute("writable", "yes");
		return mapping.findForward("jxjdmdefault");
	}
	
	/**
	 * ��ѧ������޸���ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjdmView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		PjpyJxjdmService service = new PjpyJxjdmService();
		PjpyHygxyActionForm hygxyForm = (PjpyHygxyActionForm) form;
		List<HashMap<String, String>> jxjlbList = service.getJxjlbList();
		HashMap<String, String> rsMap = service.viewJxjdm(pkValue);
		if (rsMap != null) {
			hygxyForm.setJxjdm(rsMap.get("jxjdm"));
			hygxyForm.setJlje(rsMap.get("jlje"));
			hygxyForm.setJxjjb(rsMap.get("jxjjb"));
			hygxyForm.setJxjjd(rsMap.get("jxjjd"));
			hygxyForm.setJxjlb(rsMap.get("jxjlb"));
			hygxyForm.setJxjmc(rsMap.get("jxjmc"));
			hygxyForm.setSzjdbz(rsMap.get("szjdbz"));
		}
		request.setAttribute("jxjlbList", jxjlbList);
		request.setAttribute("writable", "yes");
		return mapping.findForward("jxjdmview");
	}
	
	/**
	 * ��ѧ������޸ı���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjdmModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJxjdmService service = new PjpyJxjdmService();
		PjpyHygxyActionForm hygxyForm = (PjpyHygxyActionForm) form;
		hygxyForm.setJxjdm(request.getParameter("jxjdm"));
		List<HashMap<String, String>> jxjlbList = service.getJxjlbList();
		PjpyJxjdmModel model = new PjpyJxjdmModel();
		BeanUtils.copyProperties(model, hygxyForm);
		hygxyForm.setJxjdm(request.getParameter("jxjdm"));
		BeanUtils.copyProperties(model, hygxyForm);
		boolean bFlag = service.updateJxjdm(model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("jxjlbList", jxjlbList);
		request.setAttribute("writable", "yes");
		return mapping.findForward("jxjdmdefault");
	}
	
	/**
	 * ��ѧ��������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjlbdmWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHygxyActionForm hygxyForm = (PjpyHygxyActionForm) form;
		hygxyForm.setJxjjd("0");
		request.setAttribute("writable", "yes");
		return mapping.findForward("jxjlbdmdefault");
	}
	
	/**
	 * ��ѧ���������޸�ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjlbdmModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHygxyActionForm hygxyForm = (PjpyHygxyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		PjpyHygxyJxjlbdmService service = new PjpyHygxyJxjlbdmService();
		HashMap<String, String> rsMap = service.getJxjlbdmDetails(pkValue);
		if (rsMap != null) {
			hygxyForm.setJxjlbdm(rsMap.get("jxjlbdm"));
			hygxyForm.setJxjlbmc(rsMap.get("jxjlbmc"));
			hygxyForm.setJxjjd(rsMap.get("jxjjd"));
		}
		request.setAttribute("writable", "yes");
		return mapping.findForward("jxjlbdmmodi");
	}
	
	/**
	 * ��ѧ�������뱣��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjlbdmSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHygxyActionForm hygxyForm = (PjpyHygxyActionForm) form;
		JxjlbdmModel model = new JxjlbdmModel(); 
		BeanUtils.copyProperties(model, hygxyForm);
		PjpyHygxyJxjlbdmService service = new PjpyHygxyJxjlbdmService();
		boolean bFlag = service.saveJxjlbdm(model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "yes");
		}
		hygxyForm.setJxjlbmc("");
		request.setAttribute("writable", "yes");
		return mapping.findForward("jxjlbdmdefault");
	}
	
	/**
	 * ��ѧ���������޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjlbdmmodiSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHygxyActionForm hygxyForm = (PjpyHygxyActionForm) form;
		hygxyForm.setJxjlbdm(request.getParameter("jxjlbdm"));
		JxjlbdmModel model = new JxjlbdmModel(); 
		BeanUtils.copyProperties(model, hygxyForm);
		PjpyHygxyJxjlbdmService service = new PjpyHygxyJxjlbdmService();
		boolean bFlag = service.updateJxjlbdm(model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "yes");
		}
		hygxyForm.setJxjlbmc("");
		request.setAttribute("writable", "yes");
		return mapping.findForward("jxjlbdmmodi");
	}
	
	/**
	 * ����ʱ���趨
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjsjSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHygxyActionForm hygxyForm = (PjpyHygxyActionForm) form;
		PjpyHygxyService service = new PjpyHygxyService();
		HashMap<String, String> rsMap = service.getJxjsqsj();
		HashMap<String, String> pdsjMap = service.getPdsj();
		if (rsMap != null) {
			request.setAttribute("jxjsqxn", rsMap.get("jxjsqxn"));
			request.setAttribute("jxjsqxq", rsMap.get("jxjsqxq"));
			request.setAttribute("jxjsqnd", rsMap.get("jxjsqnd"));
		}
		if (pdsjMap != null && !StringUtils.isNull(pdsjMap.get("jxjpdsj"))) {
			hygxyForm.setJxjpdsj(pdsjMap.get("jxjpdsj"));
			hygxyForm.setRychpdsj(pdsjMap.get("rychpdsj"));
		} else {
			hygxyForm.setJxjpdsj("0");
			hygxyForm.setRychpdsj("0");
		}
		request.setAttribute("writable", "yes");
		return mapping.findForward("pjsjsz");
	}
	
	/**
	 * ��������ʱ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savePjsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHygxyActionForm hygxyForm = (PjpyHygxyActionForm) form;
		PjpyHygxyService service = new PjpyHygxyService();
		HashMap<String, String> rsMap = service.getJxjsqsj();
		if (rsMap != null) {
			request.setAttribute("jxjsqxn", rsMap.get("jxjsqxn"));
			request.setAttribute("jxjsqxq", rsMap.get("jxjsqxq"));
			request.setAttribute("jxjsqnd", rsMap.get("jxjsqnd"));
		}
		boolean bFlag = service.savePdsj(hygxyForm.getJxjpdsj(), hygxyForm.getRychpdsj(), request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("writable", "yes");
		return mapping.findForward("pjsjsz");
	}
}
