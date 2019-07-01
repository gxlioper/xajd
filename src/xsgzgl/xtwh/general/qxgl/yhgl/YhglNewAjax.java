package xsgzgl.xtwh.general.qxgl.yhgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.Encrypt;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.comm.BasicModel;
import xsgzgl.xtwh.general.qxgl.yhzgl.YhzglNewForm;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_Ȩ�޹���_�û�����_ajax��
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
public class YhglNewAjax extends BasicAction {

	YhglNewService service = new YhglNewService();
	
	// ����
	private final String TABLENAME = "yhb";
	
	// �����ֶ�
	private final String[] SAVE = {"yhm","xm","kl","szbm","zdm","dwdm","qx"};

	// �޸��ֶ�
	private final String[] UPDATE = {"yhm","xm","szbm","zdm","dwdm","qx"};
	
	/**
	 * �û�����Ϣ��ѯ
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */	
	public ActionForward yhxxSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		YhglNewForm myForm = (YhglNewForm) form;
		YhglNewInit init = new YhglNewInit();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		init.initSearch(rForm, myForm, user, request);
		
		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {"ie","checkbox"});
		
		service.getModelValue(myForm, request);

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);
		
		String checkbox = valueMap.get("checkbox");
		rsModel.setCheckBoxRs(checkbox);
		// =================== end ===================
		
		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================
		
		// ==================��ʾ����========================
		// ����
		List<HashMap<String, String>> topTr = service.getYhxxTop(user,checkbox);
		// �����
		ArrayList<String[]> rsArrList = service.getYhxxList(myForm,user);
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
	 * �û�����
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public ActionForward yhxxAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Encrypt ept = new Encrypt();
		
		YhglNewForm myForm = (YhglNewForm) form;

		BasicModel model=new BasicModel();
		
		HashMap<String,String> valueMap=service.getValueMap(request, SAVE);
		
		// ������ֵ
		service.getModelValue(myForm, request);
		
		// --------------����------------
		model.setTableName(TABLENAME);
		// --------------��Ҫ�����ֵ--------------------
		model.setValueMap(valueMap);
		
		//��Ϣ��Ϣ
		String message="";
				
		boolean flag=false;
		
		//��֤�û������Ƿ����
		flag = service.checkYhm(myForm.getYhm());
		
		if(flag){
			model.getValueMap().put("kl", ept.encrypt(myForm.getKl()));
			flag=service.saveInfo(model);
			if(flag){
				service.saveYhqx(myForm.getYhm());
			}
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
	 * �����û���Ϣ
	 * 
	 * @date 2013-01-10
	 * @author zhanghui
	 */
	public ActionForward getYhxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String yhm = request.getParameter("yhm");
				
		Map<String, String> map = service.getYhxx(yhm);
		
		String json = JSONObject.fromObject(map).toString();	
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);

		return null;
	}
	
	/**
	 * �û��޸�
	 * 
	 * @date 2013-01-10
	 * @author zhanghui
	 */
	public ActionForward yhxxUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		YhglNewForm myForm = (YhglNewForm) form;

		BasicModel model=new BasicModel();
		HashMap<String,String> valueMap=service.getValueMap(request, UPDATE);
		
		// ������ֵ
		service.getModelValue(myForm, request);
		
		// --------------����------------
		model.setTableName(TABLENAME);
		// --------------��Ҫ�޸ĵ�ֵ--------------------
		model.setValueMap(valueMap);
		// --------------����------------
		model.setPk(new String[]{"yhm"});
		
		//��Ϣ��Ϣ
		String message="";
				
		boolean flag=false;
		
		HashMap<String,String> map = service.getYhxx(myForm.getYhm());		
		
		flag=service.updateInfo(model);
		
		if(flag && !myForm.getZdm().equalsIgnoreCase(map.get("zdm"))){
			service.saveYhqx(myForm.getYhm());
		}
				
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
			
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �û�ɾ��
	 * 
	 * @date 2013-01-09
	 * @author zhanghui
	 */
	public ActionForward yhxxDelete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		YhglNewForm myForm = (YhglNewForm) form;
				
		// ������ֵ
		service.getModelValue(myForm, request);
		
		//��Ϣ��Ϣ
		String message="";
				
		boolean flag=false;
				
		flag=service.deleteYhxx(myForm);
				
		message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
				: MessageInfo.MESSAGE_DEL_FALSE;
	
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �û������ʼ��
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public ActionForward yhmmCsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		YhglNewForm myForm = (YhglNewForm) form;
				
		// ������ֵ
		service.getModelValue(myForm, request);
		
		//��Ϣ��Ϣ
		String message="";
				
		boolean flag=false;
				
		flag=service.cshYhmm(myForm);
				
		message = flag ? MessageInfo.MESSAGE_INIT_SUCCESS
				: MessageInfo.MESSAGE_INIT_FALSE;
	
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	/**
	 * �û������ʼ��-���ݲ�ѯ�������
	 */
	public ActionForward yhmmCshPl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		YhglNewForm myForm = (YhglNewForm) form;
		YhglNewInit init = new YhglNewInit();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		init.initSearch(rForm, myForm, user, request);
		
		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {"ie","checkbox"});
		
		service.getModelValue(myForm, request);

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);
		
		String checkbox = valueMap.get("checkbox");
		rsModel.setCheckBoxRs(checkbox);
		// =================== end ===================
		
		ArrayList<String[]> rsArrList = service.getYhxxAllList(myForm,user);
		String[] primarykey_checkVal = new String[rsArrList.size()];
		for (int i = 0; i < rsArrList.size(); i++) {
			primarykey_checkVal[i] = rsArrList.get(i)[0];
		}
		myForm.setPrimarykey_checkVal(primarykey_checkVal);
		
		String message="";
		
		boolean flag=false;
		
		flag=service.cshYhmm(myForm);
		
		message = flag ? MessageInfo.MESSAGE_INIT_SUCCESS : MessageInfo.MESSAGE_INIT_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * �û���Ϣ�޸�
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public ActionForward yhxxPlupdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		YhglNewForm myForm = (YhglNewForm) form;
				
		// ������ֵ
		service.getModelValue(myForm, request);
		
		//��Ϣ��Ϣ
		String message="";
				
		boolean flag=false;
				
		flag=service.plQxYhxx(myForm);
				
		message = flag ? MessageInfo.MESSAGE_WORK_SUCCESS
				: MessageInfo.MESSAGE_WORK_FALSE;
	
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	/**
	 * �û�����
	 */
	public ActionForward yhfz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		YhglNewForm myForm = (YhglNewForm) form;
		
		// ������ֵ
		service.getModelValue(myForm, request);
		
		//��Ϣ��Ϣ
		String message="";
		
		boolean flag=false;
		
		flag=service.yhfz(myForm);
		
		message = flag ? MessageInfo.MESSAGE_WORK_SUCCESS
				: MessageInfo.MESSAGE_WORK_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
	/**
	 * �û�ͣ��
	 */
	public ActionForward yhty(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		YhglNewForm myForm = (YhglNewForm) form;
		
		// ������ֵ
		service.getModelValue(myForm, request);
		
		//��Ϣ��Ϣ
		String message="";
		
		boolean flag=false;
		
		flag=service.yhty(myForm);
		
		message = flag ? MessageInfo.MESSAGE_WORK_SUCCESS
				: MessageInfo.MESSAGE_WORK_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);
		
		return null;
	}
	/**
	 * @description	�� ˼���Ƿ�ɼ�
	 * @author 		�� CP��1352��
	 * @date 		��2017-12-6 ����02:17:45
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward szkj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String data = request.getParameter("zghs");	
		String[] zghs=data.split(",");
		String sfbl = request.getParameter("sfbl");
		String message="";
		int[] flag=service.szkj(zghs,sfbl);
		message = (flag.length==zghs.length) ? MessageInfo.MESSAGE_WORK_SUCCESS
				: MessageInfo.MESSAGE_WORK_FALSE;
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * ��ȡ����Ա��Ϣ
	 * 
	 * @date 2013-01-14
	 * @author zhanghui 
	 */
	public ActionForward getFdyxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String yhm = request.getParameter("yhm");
		
		Map<String, String> map = service.getFdyxx(yhm);
		
		String json = JSONObject.fromObject(map).toString();	
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		
		return null;
	}
}
