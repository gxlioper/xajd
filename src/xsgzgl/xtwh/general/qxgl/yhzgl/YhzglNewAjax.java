package xsgzgl.xtwh.general.qxgl.yhzgl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mortbay.util.UrlEncoded;

import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_Ȩ�޹���_�û������_ajax��
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
public class YhzglNewAjax extends BasicAction  {
	
	YhzglNewService service = new YhzglNewService();
	
	/**
	 * �û�����Ϣ��ѯ
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */	
	public ActionForward yhzxxSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		YhzglNewForm myForm = (YhzglNewForm) form;
		YhzglNewInit init = new YhzglNewInit();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		init.initSearch(rForm, myForm, user, request);
		
		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {"ie"});
		
		service.getModelValue(myForm, request);

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);
		// =================== end ===================
		
		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================
		
		// ==================��ʾ����========================
		// ����
		List<HashMap<String, String>> topTr = service.getYhzxxTop(user);
		// �����
		ArrayList<String[]> rsArrList = service.getYhzxxList(myForm,user);
		// ���������
		String spHtml = "";
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");
		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * �û�������
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	@SystemLog(description="����ϵͳά��-Ȩ�޹���-�û������-����ZMC:{zmc}")
	public ActionForward yhzxxAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		YhzglNewForm myForm = (YhzglNewForm) form;
		
		// ������ֵ
		service.getModelValue(myForm, request);
		
		//��Ϣ��Ϣ
		String message="";
				
		boolean flag=false;
		
		//��֤�û��������Ƿ����
		flag = service.checkZmc(myForm.getZmc());
		
		if(flag){
			flag=service.addYhzxx(myForm);
					
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}else{
			message = "exist";
		}
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	/**
	 * �û��鸴��
	 * 
	 * @date 2013-03-19
	 * @author zhangjw
	 */
	public ActionForward yhzxxCopy(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		YhzglNewForm myForm = (YhzglNewForm) form;
		myForm.setZmc(URLDecoder.decode(myForm.getZmc(),"UTF-8"));
		// ������ֵ
		service.getModelValue(myForm, request);
		
		//��Ϣ��Ϣ
		String message="";
				
		boolean flag=false;
		
		//��֤�û��������Ƿ����
		flag = service.checkZmc(myForm.getZmc());
		
		if(flag){
			flag=service.copyYhzxx(myForm);
					
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}else{
			message = "exist";
		}
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �û����޸�
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	@SystemLog(description="����ϵͳά��-Ȩ�޹���-�û������-����ZMC:{zmc},ZDM:{zdm}")
	public ActionForward yhzxxUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		YhzglNewForm myForm = (YhzglNewForm) form;
		
				
		// ������ֵ
		service.getModelValue(myForm, request);
		
		//��Ϣ��Ϣ
		String message="";
				
		boolean flag=false;
		
		//��֤�û��������Ƿ����
		flag = service.checkZmc(myForm.getZmc());
		
		if(flag){
			flag=service.updateYhzxx(myForm);
					
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}else{
			message = "exist";
		}
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �û���ɾ��
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	@SystemLog(description="����ϵͳά��-Ȩ�޹���-�û������-ɾ��value:{primarykey_checkVal}")
	public ActionForward yhzxxDelete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		YhzglNewForm myForm = (YhzglNewForm) form;
				
		// ������ֵ
		service.getModelValue(myForm, request);
		
		//��Ϣ��Ϣ
		String message="";
				
		boolean flag=false;
		
		flag = service.checkYhz(myForm.getPrimarykey_checkVal());
		
		if(flag){
			flag=service.deleteYhzxx(myForm);
					
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}else{
			message = "ɾ��ʧ�ܣ�<br>��ά���û����û��鲻��ɾ����";
		}
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
}
