package xsgzgl.jxgl.general.jxcjgl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;


/**
 * ��ѵ����-��ѵ���˹���-��ѵ�ɼ�����
 * @author yeyipin
 * @since 2012.10.13
 */
public class JxglJxcjglAction extends BasicAction{

	/**
	 * ��ѵ�ɼ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxcjCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxcjglService jxglJxcjglService = new JxglJxcjglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		String jxid = request.getParameter("jxid");
		HashMap<String,String> rs = jxglJxcjglService.getJxxx(jxid);
		//��֤��ѵ�Ƿ����
		if(rs == null || rs.isEmpty()){
			String msg = "�뽨�������о�ѵ��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("jxxxList", jxglJxcjglService.getJxxxList());
		rForm.setPath("jxgl_jxkhgl_jxcjgl.do");
		// ----------------����PATH end-----------------------
		jxglJxcjglService.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("jxcjCx");
		}
		
	}
}
