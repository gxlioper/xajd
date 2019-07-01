package xgxt.comm.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.xml.XMLReader;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �߼���ѯ_action��
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

public class SearchAction extends BasicAction {

	/**
	 * �߼���ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward SeachTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchForm myForm = (SearchForm) form;
		SearchService service = new SearchService();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();

		// �û�����
		String userType = user.getUserType();
		// �û���
		String userName = user.getUserName();
		// ����ģ��·��
		String path = request.getParameter("path");
		myForm.setPath(path);
		
		// ѧ��
		String xh = "stu".equalsIgnoreCase(userType) ? userName : "";
		// =================== end ===================

		// =================== ��ʼ���б�ֵ ===========
		service.setCommOptionList(myForm, rForm, request);
		service.setSearchTj(myForm, rForm, user, request);
		
		HashMap<String, String> rs = service.getSearchInfo(myForm, user);
		// ================= end =====================

		// ============= ����request��ֵ =============
		// �����ֶ�
		String[] qtzd = new String[] { "path", "xh" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { path, xh };
		
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setRs(rs);
		
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		
		return mapping.findForward("seachTj");
	}
	
	public ActionForward xhPlcxDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("xhPlcxDiv");
	}
	public ActionForward xmPlcxDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("xmPlcxDiv");
	}
	
	/**
	 * ������ѯ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createSearchTj(ActionMapping mapping,
										 ActionForm form, HttpServletRequest request,
										 HttpServletResponse response) throws Exception {

		SearchForm model = new SearchForm();
		SearchOptionList service = new SearchOptionList();
		RequestForm rForm = new RequestForm();
		SearchForm searchOptionModel = SearchForm.searchOptionModel;
		User user = getUser(request);// �û�����
		searchOptionModel.SetUsername(user.getUserName());
		// ==================��ʼ��========================
		// ����·��
		String path = request.getParameter("searchPath");
		searchOptionModel.setPath(path);

		//����Ȩ�޿���:��Ԣ����-ס����Ϣ�����߼���ѯ����ģ�������ƹ�Ԣ����Ա��Ȩ�ޣ�����һ�Ų�����
		//ֻ�н��롰ס����Ϣ�����˵�����Ԣ����ԱȨ��Ϊyes������������Ϊno
		//����ҵ����Ҫ�ж��û���Ԣ����Ա ��if�ж�ȥ�� modify by xiaxia 2017-01-03
//		String gygly_path = XMLReader.getFlowControl("gygl", "gygly_path");
//		if(gygly_path.contains(path) && "yes".equalsIgnoreCase(user.getGyglyQx())){
//			user.setGyglyQx("yes");
//			user.setUserStatus("xx");
//		}else{
//			user.setGyglyQx("no");
//		}

		//���롰ס����Ϣ�����˵������û��ǹ�Ԣ����Ա���������ΪѧУ����Ա
/*		if(gygly_path.contains(path) && "no".equalsIgnoreCase(user.getGyglyQx())){
			user.setUserStatus("xx");
		}*/

		model.setPath(path);
		// ��ʽ����ַ
		String stylePath = request.getParameter("stylePath");
		searchOptionModel.setStylePath(stylePath);

		String yhlx = request.getParameter("yhlx");
		String jxid = request.getParameter("jxid");

		if(!Base.isNull(yhlx)){
			user.setUserStatus(yhlx);
		}
		// ��ʼ�������б�
		service.setNjXyZyBjList(searchOptionModel, rForm, user, request);
		// ��ʼ�������б�[ȫ]
		service.setNjXyZyBjNewList(searchOptionModel, rForm, user, request);

		// ��ʼ����Ԣ�б�
		service.setLdChQshList(searchOptionModel, user);
//		if("10698".equals(Base.xxdm)){
		//���������ʼ����Ժ�б�
		service.setSymcList(searchOptionModel,user);
		service.setZybjList(searchOptionModel,user);
//		}
		// ��ʼ���ڹ����˲����б�
		service.setQgbmList(searchOptionModel,user);
		// ��ʼ����ѵ����
		service.setJxjzList(searchOptionModel, user,jxid);
		// ��ʼ��Υ������б�
		service.setWjlbList(searchOptionModel);
		//��ʼ��ʡ����
		service.setQxList(searchOptionModel,user);
		//��ʼ����������
		service.setPjmcList(searchOptionModel,user,request.getParameter("xzdm"));

		// �����б�
		List<HashMap<String, String>> tjList = service.getSearchTjList(model,
				user);
		// ==================��ʼ��end========================

		// ==================�����б�========================
		// �����ѯ�б�
		List<HashMap<String, Object>> optionList = service.getSearchOptionList(
				searchOptionModel, tjList);
		// ������ѯ�б�
		List<HashMap<String, Object>> batchList = service.getSearchBatchList(
				searchOptionModel, tjList);
		// ʱ���ѯ�б�
		List<HashMap<String, Object>> timeList = service.getSearchTimeList(
				searchOptionModel, tjList);
		// ��ֵ�����ѯ�б�
		List<HashMap<String, Object>> numList = service.getSearchNumList(
				searchOptionModel, tjList);


		// ==================�����б� end========================

		// ==================����ǰ̨ҳ��========================
		service.createSearchDiv(searchOptionModel, optionList,batchList, timeList, numList,
				response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}


	/**
	 * �������Ź���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creatBmHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		User user = getUser(request);// �û�����
		
		// ==================��ʼ��========================
		// ����꼶
		String[] nj = null;
		if (request.getParameter("nj") != null
				&& request.getParameter("nj").length() > 0) {
			nj = request.getParameter("nj").split("!!@@!!");
		}

		// ���ѧԺ
		String[] xy = null;
		if (request.getParameter("xy") != null
				&& request.getParameter("xy").length() > 0) {
			xy = request.getParameter("xy").split("!!@@!!");
		}

		// ���רҵ
		String[] zy = null;
		if (request.getParameter("zy") != null
				&& request.getParameter("zy").length() > 0) {
			zy = request.getParameter("zy").split("!!@@!!");
		}

		// ��������
		String bmlx = request.getParameter("bmlx");
		// ��ʽ����ַ
		String stylePath = request.getParameter("stylePath");
		String searchPath = request.getParameter("searchPath");
		String sfzxs= "";
		if(searchPath.equals("xsxx_xsxxgl_cxfzxs.do")){
			sfzxs="0";
		}
		if(searchPath.equals("xsxx_xsxxgl_cxzxs.do")){
			sfzxs="1";
		}
		// ==================��ʼ��end========================

		// ==================����ǰ̨ҳ��========================
		service.creatBmHtml(nj, xy, zy, bmlx, stylePath, user, response,sfzxs);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * ������Ժ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creatSyHtml(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		User user = getUser(request);// �û�����

		// ==================��ʼ��========================
		// �����Ժ
		String[] sy = null;
		if (request.getParameter("sy") != null
				&& request.getParameter("sy").length() > 0) {
			sy = request.getParameter("sy").split("!!@@!!");
		}
		// ��������
		String bmlx = request.getParameter("bmlx");
		// ��ʽ����ַ
		String stylePath = request.getParameter("stylePath");
		String searchPath = request.getParameter("searchPath");
		String sfzxs= "";
		if(searchPath.equals("xsxx_xsxxgl_cxfzxs.do")){
			sfzxs="0";
		}
		if(searchPath.equals("xsxx_xsxxgl_cxzxs.do")){
			sfzxs="1";
		}
		// ==================��ʼ��end========================

		// ==================����ǰ̨ҳ��========================
		service.creatSyHtml(sy, bmlx, stylePath, user, response,sfzxs);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ������Ԣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creatGyHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		User user = getUser(request);// �û�����
		
		// ==================��ʼ��========================
		// ���У��
		String[] xqdm = null;
		if (request.getParameter("xqdm") != null
				&& request.getParameter("xqdm").length() > 0) {
			xqdm = request.getParameter("xqdm").split("!!@@!!");
		}
		
		// ���԰��
		String[] yqdm = null;
		if (request.getParameter("yqdm") != null
				&& request.getParameter("yqdm").length() > 0) {
			yqdm = request.getParameter("yqdm").split("!!@@!!");
		}
		
		// ���¥��
		String[] ld = null;
		if (request.getParameter("ld") != null
				&& request.getParameter("ld").length() > 0) {
			ld = request.getParameter("ld").split("!!@@!!");
		}

		// ������
		String[] ch = null;
		if (request.getParameter("ch") != null
				&& request.getParameter("ch").length() > 0) {
			ch = request.getParameter("ch").split("!!@@!!");
		}
		
		// ��������
		String bmlx = request.getParameter("bmlx");
		// ��ʽ����ַ
		String stylePath = request.getParameter("stylePath");
		// ==================��ʼ��end========================

		// ==================����ǰ̨ҳ��========================
		service.creatGyHtml(xqdm, yqdm, ld, ch, bmlx, stylePath, user,
						response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/** 
	 * @����:�㽭����---�Ʒ���Ŀ����
	 * @���ߣ�cp[���ţ�1352]
	 * @���ڣ�2017-1-4 ����04:05:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param
	 * @return
	 * Object �������� 
	 * @throws 
	 */
	public ActionForward creatJfxmHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchOptionList service = new SearchOptionList();
			String[] xmmkdm = null;
			if (request.getParameter("xmmkdm") != null&& request.getParameter("xmmkdm").length() > 0) {
				xmmkdm = request.getParameter("xmmkdm").split("!!@@!!");
			}
			String bmlx = request.getParameter("bmlx");
			service.creatJfxmHtml(xmmkdm,bmlx,response);
	
		
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	/**
	 * ������ѵ���ƹ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creatJxjzHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		User user = getUser(request);// �û�����
		
		// ==================��ʼ��========================
		// �����
		String[] tid = null;
		if (request.getParameter("tid") != null
				&& request.getParameter("tid").length() > 0) {
			tid = request.getParameter("tid").split("!!@@!!");
		}
		
		// ���Ӫ
		String[] yid = null;
		if (request.getParameter("yid") != null
				&& request.getParameter("yid").length() > 0) {
			yid = request.getParameter("yid").split("!!@@!!");
		}
		
		// �����
		String[] lid = null;
		if (request.getParameter("lid") != null
				&& request.getParameter("lid").length() > 0) {
			lid = request.getParameter("lid").split("!!@@!!");
		}

		// �����
		String[] pid = null;
		if (request.getParameter("pid") != null
				&& request.getParameter("pid").length() > 0) {
			pid = request.getParameter("pid").split("!!@@!!");
		}
		
		// �����
		String[] bid = null;
		if (request.getParameter("bid") != null
				&& request.getParameter("bid").length() > 0) {
			bid = request.getParameter("bid").split("!!@@!!");
		}
		
		// �������
		String[] ssid = null;
		if (request.getParameter("ssid") != null
				&& request.getParameter("ssid").length() > 0) {
			ssid = request.getParameter("ssid").split("!!@@!!");
		}
		
		// ��������
		String ldlx = request.getParameter("ldlx");
		// ��ʽ����ַ
		String stylePath = request.getParameter("stylePath");
		// ��ѵid
		String jxid = request.getParameter("jxid");
		// ==================��ʼ��end========================

		// ==================����ǰ̨ҳ��========================
		service.createJxjzHtml(tid, yid, lid, pid, bid, ssid, ldlx, stylePath, user, jxid, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ������ԢΥ�͹���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creatWjHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		// ==================��ʼ��========================
		// ���Υ��������
		String[] gyjllbdldm = null;
		if (request.getParameter("gyjllbdldm") != null
				&& request.getParameter("gyjllbdldm").length() > 0) {
			gyjllbdldm = request.getParameter("gyjllbdldm").split("!!@@!!");
		}
		
		// ��������
		String ldlx = request.getParameter("ldlx");
		// ==================��ʼ��end========================

		// ==================����ǰ̨ҳ��========================
		service.createWjHtml(gyjllbdldm, ldlx, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	/**
	 * ʡ���� ��������
	 */
	public ActionForward creatQxHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		String[] qxdm = null;
		// ��������
		String jb = request.getParameter("ldlx");
		if(jb.equals("shi")){
			if (request.getParameter("sheng") != null&& request.getParameter("sheng").length() > 0) {
				qxdm = request.getParameter("sheng").split("!!@@!!");
			}
		}else if(jb.equals("sheng")){
			if (request.getParameter("sheng") != null&& request.getParameter("sheng").length() > 0) {
				qxdm = request.getParameter("sheng").split("!!@@!!");
			}
			jb="qu";
		}else{
			if (request.getParameter("shi") != null&& request.getParameter("shi").length() > 0) {
				qxdm = request.getParameter("shi").split("!!@@!!");
			}
		}
		List<String> list = new ArrayList<String>();
		if(qxdm!=null && qxdm.length>0){
			for (int i = 0; i < qxdm.length; i++) {
				list.add(qxdm[i]);
			}
		}
		service.createQxHtml(list, jb, response);
		return null;
	}
	

	/**
	 * �������Ź���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creatBmNewHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		User user = getUser(request);// �û�����
		
		// ==================��ʼ��========================
		// ����꼶
		String[] nj = null;
		if (request.getParameter("njNew") != null
				&& request.getParameter("njNew").length() > 0) {
			nj = request.getParameter("njNew").split("!!@@!!");
		}


		// ���ѧԺ
		String[] xy = null;
		if (request.getParameter("xyNew") != null
				&& request.getParameter("xyNew").length() > 0) {
			xy = request.getParameter("xyNew").split("!!@@!!");
		}

		// ���רҵ
		String[] zy = null;
		if (request.getParameter("zyNew") != null
				&& request.getParameter("zyNew").length() > 0) {
			zy = request.getParameter("zyNew").split("!!@@!!");
		}

		// ��������
		String bmlx = request.getParameter("bmlx");
		// ��ʽ����ַ
		String stylePath = request.getParameter("stylePath");
		// ==================��ʼ��end========================

		// ==================����ǰ̨ҳ��========================
		service.creatBmNewHtml(nj, xy, zy, bmlx, stylePath, user, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ������Ŀ����
	 */
	public ActionForward creatPjxmHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		
		// ==================��ʼ��========================
		// �������
		String[] xmlx = null;
		if (request.getParameter("xmlx") != null && request.getParameter("xmlx").length() > 0) {
			xmlx = request.getParameter("xmlx").split("!!@@!!");
		}

		// �������
		String[] xmxz = null;
		String xzdm = request.getParameter("xzdm");
		if(StringUtils.isNotNull(xzdm)){
			xmxz = new String [] {request.getParameter("xzdm")};
		}else {
			xmxz = new String [] {"1"};
		}

		// ��������
		String ldlx = request.getParameter("ldlx");
		// ��ʽ����ַ
		String stylePath = request.getParameter("stylePath");
		// ==================��ʼ��end========================

		// ==================����ǰ̨ҳ��========================
		service.creatPjxmHtml(xmlx, xmxz, ldlx, stylePath, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
}