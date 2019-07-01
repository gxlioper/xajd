package xgxt.pjpy.tyb.zhszcp.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.GlobalsVariable;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.tyb.zhszcp.service.PjpyXyszService;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

public class PjpyXyszAction extends CommonAction {

	/**
	 * ѧԺ��ѧ������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjjesz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		PjpyXyszActionForm szForm = (PjpyXyszActionForm) form;
		PjpyXyszService service = new PjpyXyszService();
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		String act = request.getParameter("act");
		//ѧԺ��ѧ���û����ɷ���
		if("stu".equalsIgnoreCase(user.getUserType()) || "xy".equalsIgnoreCase(user.getUserType())){
        	request.setAttribute("errMsg", "�Բ���,����Ȩ���ʴ�ҳ��");
			return new ActionForward("/errMsg.do",false);
        }
		//�û��жϸù����Ƿ񿪷�
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(user.getUserType()) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_JXJJESZ)
				&& zjkjService.checkKgflag()){
			String msg = "�ù�����ʱ�����Ų�����";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//��������
		if (SAVE.equalsIgnoreCase(act)) {
			//��ѧ����������Ϣ����
			boolean result = service.saveJxjjesz(szForm,user);
			request.setAttribute("result", result);
			request.setAttribute("message", result ? MESSAGE_SUCCESS : MESSAGE_FAIL);
			act = "qry";//��ѯ���
		}
		//���ݲ�ѯ
		if (QUERY.equalsIgnoreCase(act)) {
			//��ѯ��ѧ����������Ϣ
			List<String[]> rs = service.queryJxjjesz(szForm,user);
			List<HashMap<String, String>> topTr = service.queryJxjjeszTitle();
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs!=null ? rs.size() : 0);
			request.setAttribute("topTr", topTr);
		}
		//��дȨ��
		request.setAttribute("path", "pjpy_tyb_jxjjesz.do");
		FormModleCommon.commonRequestSet(request);
		//�����꼶��ѧԺ��רҵ���༶��Ϣ
		FormModleCommon.setNjXyZyBjList(request);
		//ѧ�����ѧ���б�
		appendXnxqndList(request);
		//��������Ϊѧ��
		request.setAttribute("pjzq", "xn");
		//����Ĭ�ϵ�ѧ��ѡ��
        szForm.setQueryequals_xn(StringUtils.isNull(szForm.getQueryequals_xn()) ? Base.getJxjsqxn(): szForm.getQueryequals_xn());
		return mapping.findForward("jxjjesz");
	}
	
}
