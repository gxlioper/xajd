package xgxt.rcgl.gzdx.fsbtgl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

public class FsbtglAction extends BasicExtendAction{
	/**
	 * ��ʳ��������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsbtglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		User user = getUser(request);
		// �û���
		String userStatus = user.getUserStatus();
		String doType = request.getParameter("doType");
	
		FsbtglService service = new FsbtglService();
		FsbtglForm model = (FsbtglForm)form;
		
		// ������˲���
		if("del".equalsIgnoreCase(doType)){
			String[] pkValues = request.getParameterValues("pkValues");
			String message = service.batchDel(pkValues) ? "�����ɹ�" : "����ʧ��";
			request.setAttribute("message", message);
		}
		
		if("xy".equalsIgnoreCase(userStatus)){// ѧԺ��½
			model.setXydm(user.getUserDep());
		}else if("stu".equalsIgnoreCase(userStatus)){
			model.setXh(user.getUserName());
		}
		
		Map<String, Object> map = service.getTopMap("cx");
		String[] outPutList = (String[])map.get("outputs");

		request.setAttribute("topTr", map.get("topTr"));
		request.setAttribute("rs", service.getCxList(model, user, outPutList));
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		setWriteAbleAndTitle(request, "rcsw_fsbtgl.do");
		
		request.setAttribute("fsbtList", service.getFsbtList());
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("pageSize", model.getPages().getPageSize());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("user", user);
		request.setAttribute("realTable", "xg_rcsw_fsbtffb");

		return mapping.findForward("fsbtglManage");
	}
	
	/**
	 * ѧ����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsbtglUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// �û���
		User user = getUser(request);

		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		
		FsbtglService service = new FsbtglService();
		
		// ���渱ʳ����
		if("save".equalsIgnoreCase(doType)){
			FsbtglForm myForm = (FsbtglForm)form;
			String message = service.saveFsbt(myForm) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		Map<String, String> stuInfo = new HashMap<String, String>();
		
		// ѧ�Ų�Ϊ�����ȡѧ����Ϣ
		if(StringUtils.isNotNull(xh)){
			stuInfo = service.getStuInfo(xh);
		}
		
		request.setAttribute("rs", stuInfo);
		request.setAttribute("fsbtList", service.getFsbtList());
		request.setAttribute("yfList", service.getYfList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("user", user);
		
		return mapping.findForward("fsbtglUpdate");
	}
	
	/**
	 * ��У����鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fsbtglView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		FsbtglService service = new FsbtglService();
		Map<String, String> map = service.getFsbtInfo(pkValue);
		
		request.setAttribute("rs", map);
		
		request.setAttribute("doType", doType);
		return mapping.findForward("fsbtglView");
	}
}
