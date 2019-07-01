package xsgzgl.xtwh.general.qxgl.yhzgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zfsoft.xgxt.base.message.MessageKey;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.general.qxgl.GnmkModel;
import xsgzgl.xtwh.general.qxgl.yhgl.YhglNewForm;
import xsgzgl.xtwh.general.qxgl.yhgl.YhglNewService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_Ȩ�޹���_�û������_action��
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
public class YhzglNewAction extends BasicAction{
	
	/**
	 * �û������
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public ActionForward yhzglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		YhzglNewForm myForm = (YhzglNewForm) form;
		YhzglNewService service = new YhzglNewService();
		YhzglNewInit init = new YhzglNewInit();
		
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initSearch(rForm, myForm, user, request);
		// =================== end ===================
		//===============����������===================
		request.setAttribute("tableName", "yhzb");
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("manage");
	}
	public ActionForward ymcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		String type=request.getParameter("type");
		YhzglNewService service = new YhzglNewService();
		if("add".equals(type)){
			return mapping.findForward("add"); 
		}else if("copy".equals(type)){
//			String zmc_old=request.getParameter("zmc_old");
//			request.setAttribute("zmc_old","����"+zmc_old);
			String zdm=request.getParameter("zdm");
			request.setAttribute("zdm",zdm);
			String zmc_old=service.getYhzmc(zdm);
			request.setAttribute("zmc_old","����"+zmc_old);
			return mapping.findForward("copy"); 
		}else{
//			String zmc_old=request.getParameter("zmc_old");
//			request.setAttribute("zmc_old",zmc_old);
			String zdm=request.getParameter("zdm");
			request.setAttribute("zdm",zdm);
			String zmc_old=service.getYhzmc(zdm);
			request.setAttribute("zmc_old",zmc_old);
			return mapping.findForward("update");
		}
		
	}
	/**
	 * �û������_������Ȩ
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public ActionForward yhzglGnsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		// ��½�û�
		String userName = (String)session.getAttribute("userName");
		
		YhzglNewService service = new YhzglNewService();
		
		YhzglNewForm myForm = (YhzglNewForm) form;
		String zdm = myForm.getPkValue();

		//�û�����Ϣ
		HashMap<String,String> map = service.getYhzxx(zdm);
		
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

		YhzglNewService service = new YhzglNewService();

		YhzglNewForm myForm = (YhzglNewForm) form;
		String zdm = myForm.getPkValue();
		List list = service.getAllDjGnmkList(zdm);
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
	public ActionForward yhzGnsqSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		YhzglNewService service = new YhzglNewService();

		YhzglNewForm myForm = (YhzglNewForm) form;
		String zdm = myForm.getPkValue();
		String yhzgnqx = myForm.getYhzgnqx();
		JSONArray yhzgnqxArray = JSONArray.fromObject(yhzgnqx);

		boolean result = false;
		result = service.yhzGnsqSave(zdm,yhzgnqxArray);

		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * �û������_�����û�
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public ActionForward yhzglFpyh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhzglNewService service = new YhzglNewService();
		YhzglNewForm myForm = (YhzglNewForm) form;
		String zdm = myForm.getPkValue();
		// ��������
		String doType = request.getParameter("doType");
		// �����������û�
		if("save".equalsIgnoreCase(doType)){
			zdm = myForm.getZdm();
			String yhm = myForm.getPkValue();
			String qx = request.getParameter("qx");
			if(StringUtils.isNull(qx)){
				qx = "1";//����Ȩ��Ϊ�������á�������֮ǰ�Ƿ������ã��û�����󶨺����Ϊ�����á���
			}
			YhglNewService yhglNewSV = new YhglNewService();
			YhglNewForm yhglForm = new YhglNewForm();
			yhglForm.setPrimarykey_checkVal(yhm.split(","));
			yhglForm.setZdm(zdm);
			yhglForm.setQx(qx);
			Boolean flag = yhglNewSV.plUpdateYhxx(yhglForm);
			response.getWriter().print(flag);
			return null;
		}
		RequestForm rForm = new RequestForm();
		rForm.setGnmk("xtwh");	
		rForm.setMenu("qxgl");
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
	
		request.setAttribute("zdm", zdm);
		request.setAttribute("fpzt", "wfp");
		//�û�����Ϣ
		HashMap<String,String> map = service.getYhzxx(zdm);
		request.setAttribute("rs", map);
		return mapping.findForward("fpyh");
	}
	/**
	 * �鿴�û�����û��б�
	 */
	public ActionForward yhzglViewYhxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {	
		String zdm = request.getParameter("zdm");		
		request.setAttribute("zdm", zdm);
		return mapping.findForward("view");
	}
	
	
	/**
	 * �û������_�����û�
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public ActionForward yhzglFpyhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhzglNewService service = new YhzglNewService();
		YhzglNewForm myForm = (YhzglNewForm) form;
		String zdm = request.getParameter("zdm");
		// ��������
		String doType = request.getParameter("doType");
		if(doType.equals("yfpyh")){
			
			
		}else if(doType.equals("dfpyh")){
			
		}
		
		return null;
	
	}
}
