package xsgzgl.xtwh.general.qxgl.yhgl;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommForm;
import xgxt.form.RequestForm;
import xgxt.form.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_Ȩ�޹���_�û�����_action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author zhanghui
 * @version 1.0
 */
public class YhglNewAction extends BasicAction{
	
	/**
	 * �û�����
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public ActionForward yhglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		YhglNewForm myForm = (YhglNewForm) form;
		YhglNewService service = new YhglNewService();
		YhglNewInit init = new YhglNewInit();
		
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initSearch(rForm, myForm, user, request);				
		// =================== end ===================
		
		//===============����������===================
		request.setAttribute("tableName", "yhb");
		
		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		String pathUrl=request.getParameter("pathUrl");//��ת·��
		if(!StringUtil.isNull(pathUrl)){
			String selectId = request.getParameter("selectId");
			HashMap<String, String> yhxxMap = service.getYhxx(selectId);
			if(yhxxMap == null){
				yhxxMap = new HashMap<String, String>();
			}
			request.setAttribute("yhxxMap", yhxxMap);
			request.setAttribute("selectId", selectId);
			return mapping.findForward(pathUrl);
		}
		return mapping.findForward("manage");
	}
	
	/**
	 * �û�����_������Ȩ
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public ActionForward yhglGnsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		// ��½�û�
		String userName = (String)session.getAttribute("userName");
		// ��������
		String doType = request.getParameter("doType");
		
		YhglNewService service = new YhglNewService();
		
		YhglNewForm myForm = (YhglNewForm) form;
		String yhm = myForm.getPkValue();
		
		//�û���Ϣ
		HashMap<String,String> map = service.getYhxx(yhm);
				
		request.setAttribute("rs", map);
		return mapping.findForward("gnsq");
	}

	/**
	 * �첽��ȡ�˵���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getAllDjGnmkList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		YhglNewForm myForm = (YhglNewForm) form;
		String yhm = myForm.getYhm();

		YhglNewService service = new YhglNewService();
		List list = service.getAllDjGnmkList(yhm);
		JSONArray dataList = JSONArray.fromObject(list);
		response.getWriter().print(dataList);
		return null;
	}

	/**
	 * �û�������Ȩ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhGnsqSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		YhglNewForm myForm = (YhglNewForm) form;
		String yhm = myForm.getYhm();
		String yhgnqx = myForm.getYhgnqx();
		JSONArray yhgnqxArray = JSONArray.fromObject(yhgnqx);

		boolean result = false;
		YhglNewService service = new YhglNewService();
		result = service.yhGnsqSave(yhm,yhgnqxArray);

		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
