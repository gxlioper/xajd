package xgxt.jxgl.comm.jxjg;


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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.form.CommanForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.wjsc.WjscDataAccessAction;
import xgxt.wjsc.wjff.WjffModel;
import xgxt.wjsc.wjff.WjffService;
import xsgzgl.jxgl.hzsf.cssz.JxglCsszService;


import com.zfsoft.basic.BasicAction;
import com.zfsoft.basic.BasicService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ѵ����_��ѵ���_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class JxglJxjgAction extends BasicAction {

	/**
	 * ��ѵ����_��ѵ����_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxmdManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		JxglJxjgForm myForm = (JxglJxjgForm) form;
		JxglJxjgService service = new JxglJxjgService();
		User user=getUser(request);
		myForm.setUser(user);
		List<HashMap<String,String>>topTr=service.getTopTr(myForm);
		
		service.getJxjzList(request);
		
		
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			String msg = "ѧ���û�û��Ȩ�޷��ʸ�ģ�飬��ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//===============����������� begin=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rs";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum =String.valueOf(topTr.size());
		commSetting.setShowNum(showNum);
		rForm.setCommSetting(commSetting);
		service.setRequestValue(rForm, user, request);
		//  ===============����������� end=================
		
		request.setAttribute("rs", service.getJxmdList(myForm,topTr));
		request.setAttribute("topTr",topTr);
		request.setAttribute("path", "jxgl_jxjg_jxmd.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		//����ʦ��ѧԺ
		if("10347".equalsIgnoreCase(Base.xxdm)){
			JxglCsszService csszService = new JxglCsszService();
			String lx = csszService.getCssz().get("lx");
			request.setAttribute("lx", lx);
		}
		//����ʦ��ѧԺend
		return mapping.findForward("jxmdManage");
	}	
	

	/**
	 * ��ѵ����_��ѵ����_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxmdOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxjgForm myForm = (JxglJxjgForm) form;
		JxglJxjgService service = new JxglJxjgService();
		XsxxglService stuService = new XsxxglService();
		String pkValue=request.getParameter("pkValue");
		HashMap<String,String>stuInfo=stuService.selectStuinfo(pkValue);
		User user=getUser(request);
		myForm.setUser(user);
		myForm.setXh(pkValue);
		request.setAttribute("xsjxbz", service.getXsJxbz(myForm));
		request.setAttribute("stuInfo", stuInfo);
		request.setAttribute("path", "jxgl_jxjg_jxmd.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jxmdOne");
	}	
	
	/**
	 * ��ѵ����_��ѵ����_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxjgService service = new JxglJxjgService();
		JxglJxjgForm myForm = (JxglJxjgForm) form;
		User user=getUser(request);
		myForm.setUser(user);
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.expExcel(myForm, response.getOutputStream());
		
		return mapping.findForward("");
	}	
}
