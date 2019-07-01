package xgxt.rcgl.gzdx.lxgl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

public class LxglAction extends BasicExtendAction{
	
	/**
	 * ѧ��������У����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// �û���
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		String userName = user.getUserName();

		String doType = request.getParameter("doType");
		String xh = "stu".equalsIgnoreCase(userStatus) ? userName : request.getParameter("xh");
		
		LxglService service = new LxglService();
		
		if("save".equalsIgnoreCase(doType)){
			LxglForm myForm = (LxglForm)form;
			
			String message = service.saveLxsq(myForm) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		Map<String, String> stuInfo = new HashMap<String, String>();
		// ѧ�Ų�Ϊ�����ȡѧ����Ϣ
		if(StringUtils.isNotNull(xh)){
			stuInfo = service.getStuInfo(xh);
			//----------2013.01.24 honglin���������ύ��ť beging----------//
			Map<String, String> map = service.getXslxInfo(xh);
			//�ж��Ƿ��Ѿ������
			if(map==null || map.size()<=0){//û�������
				request.setAttribute("issq", "no");
			}else{//�Ѿ������
				request.setAttribute("issq", "yes");
				//�ж��Ƿ��Ѿ����
				if(StringUtils.isNotNull(map.get("sh1")) && StringUtils.isNotNull(map.get("sh2"))){
					if(!"δ���".equals(map.get("sh1")) || !"δ���".equals(map.get("sh2"))){
						request.setAttribute("issh", "yes");
					}else{
						request.setAttribute("issh", "no");
					}
				}else if(StringUtils.isNotNull(map.get("sh1")) && StringUtils.isNull(map.get("sh2"))){
					if(!"δ���".equals(map.get("sh1"))){
						request.setAttribute("issh", "yes");
					}else{
						request.setAttribute("issh", "no");
					}
				}else if(StringUtils.isNull(map.get("sh1")) && StringUtils.isNotNull(map.get("sh2"))){
					if(!"δ���".equals(map.get("sh2"))){
						request.setAttribute("issh", "yes");
					}else{
						request.setAttribute("issh", "no");
					}
				}else{
					request.setAttribute("issh", "no");
				}
			}
			stuInfo.putAll(map);
						//----------2013.01.24 honglin���������ύ��ť end----------//
		}
		
		setWriteAbleAndTitle(request, "rcsw_lxsq.do");
		request.setAttribute("rs", stuInfo);
		request.setAttribute("userStatus", userStatus);
		return mapping.findForward("lxsqUpdate");
	}
	
	/**
	 * ��У��˹���ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		User user = getUser(request);
		// �û���
		String userStatus = user.getUserStatus();
		String doType = request.getParameter("doType");
		
		// ѧ���û������Ȩ��
		if("stu".equalsIgnoreCase(userStatus)){
			request.setAttribute("yhInfo", "ѧ���û����ܲ�����ģ�飡");
			return new ActionForward("/yhInfo.do");
		}
		
		LxglService service = new LxglService();
		LxglForm model = (LxglForm)form;
		
		// ������˲���
		if("sh".equalsIgnoreCase(doType)){
			String shjg = "tg".equalsIgnoreCase(request.getParameter("shjg")) ? "ͨ��" : "��ͨ��";
			String yj = request.getParameter("shyj");
			String[] pkValues = request.getParameterValues("pkValues");
			String message = service.batchSh(pkValues, user, shjg, yj) ? "�����ɹ�" : "����ʧ��";
			request.setAttribute("message", message);
		}
		
		Map<String, Object> map = service.getTopMap("sh");
		String[] outPutList = (String[])map.get("outputs");

		request.setAttribute("topTr", map.get("topTr"));
		request.setAttribute("rs", service.getShList(model, user, outPutList));
		
		if("xy".equalsIgnoreCase(userStatus)){// ѧԺ��½
			model.setXydm(user.getUserDep());
		}

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
 		setWriteAbleAndTitle(request, "rcsw_lxsh.do");
 		
 		request.setAttribute("pageSize", model.getPages().getPageSize());
		request.setAttribute("user", user);

		return mapping.findForward("lxshManage");
	}
	
	/**
	 * ��У��˹���ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		User user = getUser(request);
		// �û���
		String userStatus = user.getUserStatus();
		String doType = request.getParameter("doType");
	
		LxglService service = new LxglService();
		LxglForm model = (LxglForm)form;
		
		// ������˲���
		if("del".equalsIgnoreCase(doType)){
			String[] pkValues = request.getParameterValues("pkValues");
			String message = service.batchDel(pkValues) ? "�����ɹ�" : "����ʧ��";
			request.setAttribute("message", message);
		}else if("export".equalsIgnoreCase(doType)){
			response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
			response.setContentType("application/vnd.ms-excel");
			
			service.lxgldc(model, user, response.getOutputStream());
			return null;
		}
		
		Map<String, Object> map = service.getTopMap("cx");
		String[] outPutList = (String[])map.get("outputs");

		if("xy".equalsIgnoreCase(userStatus)){// ѧԺ��½
			model.setXydm(user.getUserDep());
		}else if("stu".equalsIgnoreCase(userStatus)){
			model.setXh(user.getUserName());
		}
		
		request.setAttribute("topTr", map.get("topTr"));
		request.setAttribute("rs", service.getCxList(model, user, outPutList));
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
 		setWriteAbleAndTitle(request, "rcsw_lxcx.do");
 		
 		request.setAttribute("pageSize", model.getPages().getPageSize());
		request.setAttribute("user", user);

		return mapping.findForward("lxcxManage");
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
	public ActionForward lxglView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		LxglService service = new LxglService();
		Map<String, String> map = service.getLxInfo(pkValue);
		
		request.setAttribute("rs", map);
		
		request.setAttribute("doType", doType);
		return mapping.findForward("lxglView");
	}
	
	/**
	 * ��У�޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxglUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String opera = request.getParameter("opera");
		
		String forward = "print".equalsIgnoreCase(doType) ? "lxglPrint" : "lxglUpdate";
		
		LxglService service = new LxglService();
		LxglForm model = (LxglForm) form;
		
		// �޸Ĳ���
		if("update".equalsIgnoreCase(opera)){
			String message = service.updateLxgl(model, pkValue) ? "�޸ĳɹ���" : "�޸�ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		Map<String, String> map = service.getLxInfo(pkValue);
		
		request.setAttribute("rs", map);
		
		request.setAttribute("doType", doType);
		return mapping.findForward(forward);
	}
	
	/**
	 * ��У�����޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2013-01-25
	 */
	public ActionForward lxsqxgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String opera = request.getParameter("opera");
		
		
		LxglService service = new LxglService();
		LxglForm model = (LxglForm) form;
		
		// �޸Ĳ���
		if("update".equalsIgnoreCase(opera)){
			String message = service.updateXslxgl(model, pkValue) ? "�޸ĳɹ���" : "�޸�ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		Map<String, String> map = service.getXslxInfo(pkValue);
		
		//�ж��Ƿ��Ѿ������
		if(map==null || map.size()<=0){//û�������
			request.setAttribute("issq", "no");
		}else{//�Ѿ������
			request.setAttribute("issq", "yes");
			//�ж��Ƿ��Ѿ����
			if(StringUtils.isNotNull(map.get("sh1")) && StringUtils.isNotNull(map.get("sh2"))){
				if(!"δ���".equals(map.get("sh1")) || !"δ���".equals(map.get("sh2"))){
					request.setAttribute("issh", "yes");
				}else{
					request.setAttribute("issh", "no");
				}
			}else if(StringUtils.isNotNull(map.get("sh1")) && StringUtils.isNull(map.get("sh2"))){
				if(!"δ���".equals(map.get("sh1"))){
					request.setAttribute("issh", "yes");
				}else{
					request.setAttribute("issh", "no");
				}
			}else if(StringUtils.isNull(map.get("sh1")) && StringUtils.isNotNull(map.get("sh2"))){
				if(!"δ���".equals(map.get("sh2"))){
					request.setAttribute("issh", "yes");
				}else{
					request.setAttribute("issh", "no");
				}
			}else{
				request.setAttribute("issh", "no");
			}
			
		}
		
		request.setAttribute("rs", map);
		
		return mapping.findForward("lxsqUpdate");
	}
}
