package xgxt.xsxx.comm.sjy.jcsjcsh;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.jygl.comman.JyglService;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.comm.jbsz.XsxxJbszForm;
import xgxt.xsxx.comm.jbsz.XsxxJbszInit;
import xgxt.xsxx.comm.jbsz.XsxxJbszService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_����Դ_�������ݳ�ʼ��action��
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

public class SjyJcsjcshAction extends BasicAction {

	/**
	 * ѧ����Ϣ_����Դ_�������ݳ�ʼ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcsjcshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SjyJcsjcshForm myForm = (SjyJcsjcshForm) form;
		SjyJcsjcshService service = new SjyJcsjcshService();
		SjyJcsjcshInit init = new SjyJcsjcshInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ������Ŀ
		String czxm = request.getParameter("czxm");
		czxm = Base.isNull(czxm) ? "xy" : czxm;
		myForm.setCzxm(czxm);

		RequestForm rForm = new RequestForm();
		init.getJcsjcshRForm(rForm, myForm, request);

		// ҳ����ת
		String forward = "";
		if ("xy".equalsIgnoreCase(czxm)) {
			forward = "jcsjcshForXy";
		} else if ("zy".equalsIgnoreCase(czxm)) {
			forward = "jcsjcshForZy";
		} else if ("bj".equalsIgnoreCase(czxm)) {
			forward = "jcsjcshForBj";
		}else if ("xsjbxx".equalsIgnoreCase(czxm)) {
			forward = "jcsjcshForStu";
		}

		// �Ƿ��ѯ����
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		//		 �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// �������ͣ����棬ɾ���ȣ�
		String importResult = request.getParameter("importResult");
		// �������ʾ�ֶ�
		String[] colList = rForm.getColList();
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// ��ʼ����Ŀ
		List<HashMap<String, String>> cshXmList = service.getCshXmList(myForm);
		request.setAttribute("cshXmList", cshXmList);

		// �������ʾ�ֶ�
		String editPageSize = request.getParameter("editPageSize");
		if (!"yes".equalsIgnoreCase(editPageSize)) {
			myForm.getPages().setPageSize(16);
			request.setAttribute("autoPageSize", "16");
		}
		// ================= end =====================

		// =============== ִ��ͬ������ ===============
		if ("tb".equalsIgnoreCase(doType)) {
		
			boolean flag = service.tbInfo(myForm, user);
			String message = flag ? "ͬ���ɹ�" : "ͬ��ʧ��";
			
			//ͬ��ʧ��
			if(!flag){
				//�洢��������
				String pro = "";
				
				if ("xy".equalsIgnoreCase(czxm)) {
					pro = "pro_xg_jcsj_bmtb";
				} else if ("zy".equalsIgnoreCase(czxm)) {
					pro = "pro_xg_jcsj_zytb";
				} else if ("bj".equalsIgnoreCase(czxm)) {
					pro = "pro_xg_jcsj_bjtb";
				}else if ("xsjbxx".equalsIgnoreCase(czxm)) {
					pro = "pro_xg_jcsj_stutb";
				}
				
				message += "\nע��ʧ��ԭ�����plsql�вο��洢����";
				message += pro;
				message += "���ɸ���ʵ����������޸�";
			}
			
			rForm.setMessage(message);

			search = true;
		}
		// ================= end =====================

		// =============== ��ѡ�ύ���� ===============
		if ("submit".equalsIgnoreCase(doType)) {
			boolean flag = service.submitCheckInfo(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
					: MessageInfo.MESSAGE_DO_FALSE;
			rForm.setMessage(message);

			search = true;
		}
		// ================= end =====================
		
		// =============== �����ύ���� ===============
		if ("allSubmit".equalsIgnoreCase(doType)) {
			boolean flag = service.allSubmit(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
					: MessageInfo.MESSAGE_DO_FALSE;
			rForm.setMessage(message);

			search = true;
		}
		// ================= end =====================
		
		// =============== ���ݵ���ɹ���ִ�й����ƶ� ===============
		if ("yes".equalsIgnoreCase(importResult)) {
			service.doRule(myForm, user);
		}
		// ================= end =====================
		
		// =============== ִ�в�ѯ���� ===========

		// �����
		rsArrList = service.getCshInfoList(myForm, user, colList);
		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward(forward);
	}

	/**
	 * ѧ����Ϣ_����Դ_�����ƶ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ruleManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SjyJcsjcshForm myForm = (SjyJcsjcshForm) form;
		SjyJcsjcshService service = new SjyJcsjcshService();
		SjyJcsjcshInit init = new SjyJcsjcshInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getJcsjcshRForm(rForm, myForm, request);

		// ������Ŀ
		String czxm = myForm.getCzxm();

		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// ================= end =====================

		// =============== ��ʼ�������� ===========
		// �����б�
		List<HashMap<String, Object>> ruleList = service.getRuleList(myForm);
		request.setAttribute("ruleList", ruleList);
		// ================= end =====================

		// =============== ִ��ͬ������ ===============
		if ("tb".equalsIgnoreCase(doType)) {
			boolean flag = service.doRule(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
					: MessageInfo.MESSAGE_DO_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =================== ��ʼ���б�ֵ ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("ruleManage");
	}

	/**
	 * ѧ����Ϣ_����Դ_�����ƶ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ruleUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SjyJcsjcshForm myForm = (SjyJcsjcshForm) form;
		SjyJcsjcshService service = new SjyJcsjcshService();
		SjyJcsjcshInit init = new SjyJcsjcshInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getJcsjcshRForm(rForm, myForm, request);

		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// ================= end =====================

		// =============== ִ�б������ ===============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveRule(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================

		// =============== ��ʼ�������� ===========
		// �����б�
		List<HashMap<String, String>> ruleList = service.getRuleZdList(myForm);
		request.setAttribute("ruleList", ruleList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("ruleUpdate");
	}
	
	/**
	 * ���ز��������б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setBmOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		id = Base.isNull(id) ? "bmdm" : id;
		String bmmc = request.getParameter(id);
		
		if (!Base.isNull(bmmc)){
			bmmc = URLDecoder.decode(bmmc, "UTF-8");
		}
		
		SjyJcsjcshService service = new SjyJcsjcshService();
		
		
		List<HashMap<String, String>> map = service.getBmOption(bmmc);
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
	
	/**
	 * �����꼶�����б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setNjOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		id = Base.isNull(id) ? "nj" : id;
		String bmmc = request.getParameter(id);
		
		if (!Base.isNull(bmmc)){
			bmmc = URLDecoder.decode(bmmc, "UTF-8");
		}
		
		SjyJcsjcshService service = new SjyJcsjcshService();
		
		
		List<HashMap<String, String>> map = service.getNjOption(bmmc);
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
	
	/**
	 * ����רҵ�����б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setZyOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");
		id = Base.isNull(id) ? "zydm" : id;
		
		String zymc = request.getParameter(id);
		
		if (!Base.isNull(zymc)){
			zymc = URLDecoder.decode(zymc, "UTF-8");
		}
		
		SjyJcsjcshService service = new SjyJcsjcshService();
		
		// �꼶
		String nj = request.getParameter("search_nj");
		// ѧԺ
		String xy = request.getParameter("search_xy");

		String[] searchTj = new String[] { nj, xy };
		       
		List<HashMap<String, String>> map = service.getZyOption(zymc,searchTj);
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
	
	/**
	 * ���ذ༶�����б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setBjOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		id = Base.isNull(id) ? "bjdm" : id;
		
		String bjmc = request.getParameter(id);
		// �꼶
		String nj = request.getParameter("search_nj");
		// ѧԺ
		String xy = request.getParameter("search_xy");
		// רҵ
		String zy = request.getParameter("search_zy");
		
		String[] searchTj = new String[] { nj, xy, zy };
		
		if (!Base.isNull(bjmc)){
			bjmc = URLDecoder.decode(bjmc, "UTF-8");
		}
		
		SjyJcsjcshService service = new SjyJcsjcshService();
		
		
		List<HashMap<String, String>> map = service.getBjOption(bjmc,searchTj);
		
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
	
	/**
	 * ����ѧ�������б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setXjOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		id = Base.isNull(id) ? "xjztm" : id;
		String bmmc = request.getParameter(id);
		
		if (!Base.isNull(bmmc)){
			bmmc = URLDecoder.decode(bmmc, "UTF-8");
		}
		
		SjyJcsjcshService service = new SjyJcsjcshService();
		
		
		List<HashMap<String, String>> map = service.getXjOption(bmmc);
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
	
	/**
	 * �����������������б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setXzqkOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		String lx = request.getParameter("lx");
		String xzqmc = request.getParameter(id);
		
		if (!Base.isNull(xzqmc)){
			xzqmc = URLDecoder.decode(xzqmc, "UTF-8");
		}
		
		// ʡ
		String sheng = request.getParameter("sheng");
		// ��
		String shi = request.getParameter("shi");
		// ��
		String xian = request.getParameter("xian");

		String[] searchTj = new String[] { sheng, shi, xian };
		
		SjyJcsjcshService service = new SjyJcsjcshService();
		
		
		List<HashMap<String, String>> map = service.getXzqkOption(xzqmc,lx,searchTj);
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
}
