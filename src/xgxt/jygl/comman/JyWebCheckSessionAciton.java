package xgxt.jygl.comman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import common.Globals;


/**
 * ��ҵ��-��Ҫ��¼����ܷ��ʵĹ���
 * @author Penghui.Qu
 */
public class JyWebCheckSessionAciton extends BasicAction {
	
	
	/**
	 * ������Ҫ��¼����ܷ��ʵĲ˵���
	 * ���������ֱ������·������û�е�¼����ת��ҳ����ʾ��¼��
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession  session = request.getSession();
		String userName = (String) session.getAttribute("jyweb_userName");

		if (StringUtils.isNull(userName)){
			request.setAttribute("message", "������ĵ�ַ��Ҫ��¼����ܷ���!");
			return new JyWEBAction().index(mapping, form, request, response);
		} else {
			return super.execute(mapping, form, request, response);
		}
	}
	
	
	/**
	 * ���Ź���
	 * @return
	 * @throws Exception
	 */
	public ActionForward newsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "jygl_zcwjb";
		String[] colList = new String[] {"pkValue","wjbt","fbr","fbsj","readtimes"};
		String doType = request.getParameter("doType");
		
		
		//ɾ�� 
		if (!Base.isNull(doType) && "del".equals(doType)) {
			deleteOperation(request, tableName);
			JyWEBService.setNewsList();//���¼��������б�
			
//			doType = "query";
		}
		
		//��ѯ
//		if ("query".equals(doType)) {
			selectPageDataByPagination(request, form, tableName, tableName, colList);
//		}
		
		
		//����
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			
			expPageData(request, response, tableName, tableName, service.getColumn(tableName));
			return mapping.findForward("");
		}
		
		service.setJywebList(request, "new");
		request.setAttribute("topTr", service.getColumn(tableName,colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("newsManage");
	}
	
	
	/**
	 * ��������ά��
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward newUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		HttpSession session = request.getSession();
		
//		String tableName = "jygl_zcwjb";
		String dirPath = request.getRealPath("batEditor/WEB-INF/upLoad");
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String type = request.getParameter("type");
		String realName = (String) session.getAttribute("jyweb_realName");
		
		myForm.setFbr(realName);//����form�з����˵�ֵ
		
		if (!Base.isNull(pkValue)) {
			pkValue = pkValue.replace(" ", "+");
		}
		
		//����������ϸ��Ϣ
		if (!Base.isNull(pkValue)) {
			Map<String, String> map = service.getNewInfo(pkValue,type);
			map.put("wjnr", map.get("wjnr").replaceAll("\"", "\'"));
			
			request.setAttribute("rs", map) ;
		}
		
		//����������Ϣ
		if (!Base.isNull(doType) && "save".equals(doType)) {
			
				service.upload(myForm,dirPath, request);
			//if (StringUtils.isNotNull(path)){
				boolean result = service.saveNews(myForm);
				request.setAttribute("message", result ? "�����ɹ�" : "����ʧ��");
				JyWEBService.setNewsList();//���¼��������б�
			//}
			
		}
		
		//�޸�������Ϣ
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			service.upload(myForm,dirPath, request);
//			File file = new File(tpdz); 
//			if (null != file && file.exists()) {
//				file.delete();
//			}
			//if (StringUtils.isNotNull(path)){
				boolean result = service.updateNews(myForm,pkValue);
				request.setAttribute("message", result ? "�����ɹ�" : "����ʧ��");
				JyWEBService.setNewsList();//���¼��������б�
			//}
		}
	
		service.setJywebList(request, "new");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		return mapping.findForward("newUpdate");
	}
	
	
	/**
	 * ��λ����
	 * @return
	 * @throws Exception
	 */
	public ActionForward companyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "jygl_dwxxb";
		String viewName = "view_jygl_dwxxb";
		String[] colList = new String[] {"pkValue","disabled","dwmc","dwxzmc","hyflmc","zcsj","shzt"};
		String doType = request.getParameter("doType");
		
		//ɾ��
		if (!Base.isNull(doType) && "del".equals(doType)) {
			deleteOperation(request, tableName);
			JyWEBService.setDwList();//���¼�����ҵĿ¼
//			doType = "query";
		}
		
		//��ѯ
//		if ("query".equals(doType)) {
			service.setCustomAudiColumn(request, "shzt");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
//		}
		
		//����
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			
			return mapping.findForward("");
		}
		
		service.setJywebList(request, "company");
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("realTable", tableName);
		return mapping.findForward("companyManage");
	}
	
	
	
	/**
	 * ��λ���
	 * @return
	 * @throws Exception
	 */
	public ActionForward companyAuditing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("jyweb_userName");
		
		String tableName = "jygl_dwxxb";
		String viewName = "view_jygl_dwxxb";
		String[] colList = new String[] {  "pkValue","disabled", "dwmc",
				"dwxzmc", "hyflmc", "zcsj", "shzt"};
		String doType = request.getParameter("doType");
		
		//��λ���
		if (!Base.isNull(doType) && "sh".equals(doType)) {
			
			HashMap<String, String> valueMap = new HashMap<String, String>();
			
			valueMap.put("shzt", myForm.getSave_shzt());
			valueMap.put("shr", userName);
			valueMap.put("shsj", service.getNow());
			valueMap.put("shyj", myForm.getSave_shyj());
			
			auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), valueMap, tableName);
			JyWEBService.setDwList();//���¼�����ҳ��ҵĿ¼
			
//			doType = "query";
		}
		
		
		//��λȡ�����
		if (!Base.isNull(doType) && "qxsh".equals(doType)) {
			
			HashMap<String, String> valueMap = new HashMap<String, String>();
			
			valueMap.put("shzt", "δ���");
			valueMap.put("shr", "");
			valueMap.put("shsj", "");
			valueMap.put("shyj", "");
			
			auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), valueMap, tableName);
			JyWEBService.setDwList();//���¼�����ҳ��ҵĿ¼
			
//			doType = "query";
		}
		
		//��ѯ
//		if ("query".equals(doType)) {
			service.setCustomAudiColumn(request, "shzt");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
//		}
		
		service.setJywebList(request, "company");
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("companyAuditing");
	}
	
	
	/**
	 * ��̨-��λά��
	 * @return
	 * @throws Exception
	 */
	public ActionForward companyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		
		String tableName = "jygl_dwxxb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		//���浥λ
		if (!Base.isNull(doType) && "save".equals(doType)) {
			insertOperation(request, tableName);
			JyWEBService.setDwList();//���¼�����ҵĿ¼
			pkValue = request.getParameter("save_yhm");
		}
		
		//�޸ĵ�λ
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			updateOperation(request, tableName);
			JyWEBService.setDwList();//���¼�����ҵĿ¼
			pkValue = request.getParameter("save_yhm");
		}
		
		//���ص�λ��Ϣ
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, tableName, pkValue);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		service.setJywebList(request, "company");
		return mapping.findForward("companyUpdate");
	}
	
	
	
	/**
	 * ����ά��
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward resumeUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		HttpSession session = request.getSession();
		
		String tableName = "jygl_jytjb";
		String viewName = "view_jygl_jytjb";
		String mklx = request.getParameter("mklx");
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String xh = request.getParameter("xh");
		String userName = (String) session.getAttribute("jyweb_userName");
		String userType = (String) session.getAttribute("jyweb_userType");
		
		//�����ѧ����userNameΪ����ֵ
		if ("stu".equals(userType)) {
			pkValue = userName;
		}
		
		if (!Base.isNull(xh)) {
			pkValue = xh;
		}
		
		//����
		if (!Base.isNull(doType) && "save".equals(doType)) {
			insertOperation(request, tableName);
			JyWEBService.setRcList();//���¼����˲��б�
			pkValue = request.getParameter("save_xh");
		}
		
		//�޸�
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			updateOperation(request, tableName);
			pkValue = request.getParameter("save_xh");
		}
		
		//����ѧ������
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		//�����˼�����û���ݣ�ȡ��ҵ����Ϣ��������
		if (null==rs || rs.isEmpty()) {
			selectPageDataByOne(request, "jy_bysxxb", "view_jy_bysxxb", pkValue);
		} else {
			doType="update";
		}
		
		rs = (HashMap<String, String>) request.getAttribute("rs");
		//����ҵ����Ϣ������Ȼû���ݣ�ȡѧ��������Ϣ��
		if (null==rs || rs.isEmpty()) {
			selectPageDataByOne(request, "xsxxb", "view_xsxxb", pkValue);
		}
		
		service.setJywebList(request, "resume");
		service.setJywebList(request, "work");
		
		if (!Base.isNull(pkValue)) {
			request.setAttribute("doType", doType);
		}else {
			request.setAttribute("doType", null);
		}
		
		
		//�Ӿ�ҵ����̨ά��
		if ("admin".equals(mklx)) {
			return mapping.findForward("admin_resumeUpdate");
		}
		//ѧ���û��ھ�ҵ��ά��
		return mapping.findForward("resumeUpdate");
	}
	
	
	
	/**
	 * ��λ�鿴����Ͷ�ݵ�ѧ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward resumecx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		HttpSession session = request.getSession();
		
		String realName = (String) session.getAttribute("jyweb_realName");
		
		String doType = request.getParameter("doType");
		String tableName = "jyweb_stujobs";
		String viewName = "view_jyweb_stujobs";
		
		//�޸��Ƿ�״̬Ϊ���ǡ�
		if("del".equalsIgnoreCase(doType)){
			
			deleteOperation(request, tableName);
			
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			request.setAttribute("message", service.updateSfsc(pkValues) ? "�����ɹ�" : "����ʧ��");
		}
		
		String[] outputColumn = new String[]{"pkValue", "xh",  "xm", "gwmc","gwfbsj","tdrq","hfqk"}; 
		
		//request.setAttribute("clientColumns", " (case when hfqk='�ѻظ�' then 'disabled' else '' end ) disabled,");
		StringBuilder annexTerm = new StringBuilder();
		annexTerm.append(" and dwmc='").append(realName).append("' and joblb='����' ");
		
		if (Globals.XXDM_NJJS.equals(Base.xxdm)){
			//�Ͼ���ʦ���Ի���������
			annexTerm.append(" and exists (select 1 from xg_view_jyweb_njjs_xsbmshb b where a.xh=b.xh and a.gwmc||a.dwmc||a.gwfbsj=b.gwid and b.xxsh='ͨ��') ");
		}
		
		request.setAttribute("annexTerm", annexTerm.toString());
		selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		
		if("expData".equalsIgnoreCase(doType)){
			String[] output = new String[]{"xh", "xm", "gwmc", "tdrq","hfqk"};
			expPageData(request, response, tableName, viewName, output);
			return mapping.findForward("");
		}
		
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("realTable", tableName);
		return mapping.findForward("resumecx");
	}
	
	
	
	/**
	 * ��λ�����ظ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward revertResume(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		if("save".equalsIgnoreCase(doType)){
			JyWEBService service = new JyWEBService();
			JyglForm myForm = (JyglForm) form;
			Model model = new Model();
			BeanUtils.copyProperties(model, myForm);
			
			String msg = service.insertHfxx(model) ? "�ظ��ɹ�" : "�ѻظ���ѧ��!";
			
			request.setAttribute("message", msg);
		}
		
		this.selectPageDataByOne(request, "jyweb_stujobs", "view_jyweb_stujobs", pkValue);	
		
		return mapping.findForward("revertResume");
	}
	
	
	
	/**
	 * �鿴��λ�����ظ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward revertcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("jyweb_userName");
		String doType = request.getParameter("doType");
		String tableName = "jyweb_zpxxtjb";
		
		if("view".equalsIgnoreCase(doType)){
			String pkValue = request.getParameter("pkValue");
			this.selectPageDataByOne(request, tableName, tableName, pkValue);
			return mapping.findForward("revertView");
		}
		
		if("del".equalsIgnoreCase(doType)){
			HashMap<String, String[]> primaryMap = getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY);
			
			HashMap<String, String> valueMap = new HashMap<String, String>();
			valueMap.put("sfsc", "��");

			// ͨ����˷���
			auditingBatchOperation(request, primaryMap, valueMap, tableName);
			
		}
		
		String[] output = new String[]{"pkValue", "dwmc", "gwmc", "sj"};
		request.setAttribute("annexTerm", " and sfsc='��' and czlx='�ظ�' and xh='"+userName+"'");
		selectPageDataByPagination(request, form, "jyweb_zpxxtjb", "jyweb_zpxxtjb", output);
		
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("revertcx");
	}
	
	
	/**
	 * ��λ�û��޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mmUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		if("save".equalsIgnoreCase(doType)){
			JyWEBService service = new JyWEBService();
			JyglForm myForm = (JyglForm)form;
			Model model = new Model();
			BeanUtils.copyProperties(model, myForm);
			String msg = "";
			
			String yhm = (String)request.getSession().getAttribute("jyweb_userName");
			if(service.isUserExists(yhm, model.getOldmm())){
				msg = service.updateMm(model) ? "�޸ĳɹ���" : "�޸�ʧ�ܣ�";
			}else{
				msg = "����д�ľ����벻��ȷ��";
			}
			request.setAttribute("message", msg);
		}
		
		return mapping.findForward("mmUpdate");
	}
	
	
	
	/**
	 * ��ҵ��Ƹ��Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward workManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		String tableName = "jygl_zpxxb";
		String viewName = "view_jygl_zpxx";
		String[] outputColumn = new String[]{"pkValue","disabled","gsmc","zpzwmc",
								"gwxz","fbsj","yxqx","xxsh"};
		String doType = request.getParameter("doType");
		
		String userType = (String)session.getAttribute("jyweb_userType");
		JyglForm myForm = (JyglForm)form;
		JyWEBService service = new JyWEBService();
		
		if("dw".equalsIgnoreCase(userType)){
			// ��ȡ��������
			String bmmc = (String)session.getAttribute("jyweb_realName"); 
			String shzt = (String)session.getAttribute("jyweb_dwshzt");
			
			if(!"ͨ��".equals(shzt)){
				String msg = "����û��ѧУ��ˣ����ܷ�����Ƹ��Ϣ��";
				request.setAttribute("message", msg);
				return mapping.findForward("remindpage");
			}
			
			myForm.setQuerylike_gsmc(bmmc);
			request.setAttribute("annexTerm", " and gsmc='"+bmmc+"'");
		}
			
		// ɾ������
		if("del".equalsIgnoreCase(doType)){
			deleteOperation(request, tableName);
			JyWEBService.setZpxxList();//������Ƹ��λ��Ϣ
			
//			doType="query";
		}
		
		// ��ѯ����
//		if ("query".equals(doType)) {
			service.setCustomAudiColumn(request, "xxsh");
			selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
//		}
		
		// ��������
		if("expData".equalsIgnoreCase(doType)){
			String[] output = new String[]{"gsmc","dwxzmc","hyflmc","dz","zpzw",
							  "gwxz","gzdd","zprs","xb","xlyq","wyyq","lxr","lxdh","fbsj","yxqx"};
			expPageData(request, response, tableName, viewName, output);
			return mapping.findForward("");
		}
		
		service.setJywebList(request, "");
		request.setAttribute("topTr", service.getColumn(viewName, outputColumn));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		return mapping.findForward("workManage");
	}
	
	
	
	/**
	 * ��Ƹ��λ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward workAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 		String tableName = "jygl_zpxxb";
		String viewName = "view_jygl_zpxx";
		
		HttpSession session = request.getSession();
		
		// ����û�����
		String userType = (String)session.getAttribute("jyweb_userType");
		String doType = request.getParameter("doType");
		
		JyWEBService service = new JyWEBService();
		JyglForm myForm = (JyglForm)form;
		
		if("save".equalsIgnoreCase(doType)){
			insertOperation(request, tableName);
			JyWEBService.setZpxxList();//������Ƹ��λ��Ϣ
		}
		
		if ("del".equalsIgnoreCase(doType)){
			deleteOperation(request, tableName);
		}
		
		Map<String, String> companyInfo = null;
			
		// ��ù�˾��Ϣ,��λ�û�ֱ��ˢ����˾��Ϣ��ѧУ�û�ѡ��λ���ù�˾��Ϣ
		String[] colList = new String[]{"dwmc", "email", "dz", "dwxzmc", "dwlx", "wz", "hyflmc", "cz", "dwjj"};
		if("dw".equalsIgnoreCase(userType)){
			String yhm = session.getAttribute("jyweb_userName").toString();
			companyInfo = service.getCompanyInfo("yhm" ,yhm, colList);
		}else {
			String gsmc = myForm.getSave_gsmc();
			if(StringUtils.isNotNull(gsmc)){
				companyInfo = service.getCompanyInfo("dwmc", gsmc, colList);
			}
		}
		
		if (null != companyInfo){
			String[] outputColumn = new String[]{"pkValue","gsmc","zpzwmc","gwxz","fbsj","yxqx"};
			request.setAttribute("annexTerm", " and gsmc= '"+companyInfo.get("dwmc")+"'");
			selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		}
		
		
		service.setJywebList(request, "work");
		request.setAttribute("fbsj", GetTime.getNowTime2());
		request.setAttribute("companyInfo", companyInfo);
		request.setAttribute("doType", doType);
		
		if("dw".equalsIgnoreCase(userType)){
			return mapping.findForward("dw_workAdd"); 
		}
		
		return mapping.findForward("workAdd"); 
	}
	
	
	/**
	 * ��λά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		
		String tableName = "jygl_zpxxb";
		String doType = request.getParameter("doType");
		
		if ("save".equals(doType)){
			insertOperation(request, tableName);
		}
		
		
		service.setJywebList(request, "work");
		return mapping.findForward("gwwh");
	}
	
	

	/**
	 * ��Ƹ��λ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward workUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "jygl_zpxxb";
		String viewName = "view_jygl_zpxx";

		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		JyWEBService service = new JyWEBService();
		
		if("save".equalsIgnoreCase(doType)){
			updateOperation(request, tableName);
			JyWEBService.setZpxxList();//������Ƹ��λ��Ϣ
		}

		selectPageDataByOne(request, tableName, viewName, pkValue);

		service.setJywebList(request, "work");
		request.setAttribute("doType", doType);
		return mapping.findForward("workUpdate"); 
	}

	
	
	/**
	 * ��Ƹ��Ϣ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward workAuditing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "jygl_zpxxb";
		String viewName = "view_jygl_zpxx";
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		HttpSession session = request.getSession();

		String userName = (String) session.getAttribute("jyweb_userName");
		String doType = request.getParameter("doType");
		
		if("sh".equalsIgnoreCase(doType)){
			
			HashMap<String, String> valueMap = new HashMap<String, String>();
			
			valueMap.put("xxsh", myForm.getSave_xxsh());
			valueMap.put("shr", userName);
			valueMap.put("shsj", service.getNow());
			valueMap.put("shyj", myForm.getSave_shyj());
			
			auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), valueMap, tableName);
			
			JyWEBService.setZpxxList();//������Ƹ��λ��Ϣ
			
//			doType="query";
		}
		
		if("qxsh".equalsIgnoreCase(doType)){
			
			HashMap<String, String> valueMap = new HashMap<String, String>();
			
			valueMap.put("xxsh", "δ���");
			valueMap.put("shr", "");
			valueMap.put("shsj", "");
			valueMap.put("shyj","");
			
			auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), valueMap, tableName);
			
			JyWEBService.setZpxxList();//������Ƹ��λ��Ϣ
			
//			doType="query";
		}
		
		
		String[] outputColumn = new String[]{"pkValue","disabled",
							"gsmc","zpzwmc","gwxz","fbsj","yxqx","xxsh"};
		
//		if ("query".equals(doType)) {
			service.setCustomAudiColumn(request, "xxsh");
			selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
//		}
		
		service.setJywebList(request, "");
		request.setAttribute("topTr", service.getColumn(viewName, outputColumn));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("workAuditing");
	}
	
	
	/**
	 * ��������
	 * @return
	 * @throws Exception
	 */
	public ActionForward resumeManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName="jygl_jytjb";
		String viewName="view_jygl_jytjb";
		String[] colList = new String[] { "pkValue", "xh", "sfzh", "xm", "xb",
				"xymc", "zymc", "gzxz", "gzdd", "qwxs" };
		String doType = request.getParameter("doType");
		
		//ɾ��
		if (!Base.isNull(doType) && "del".equals(doType)) {
			deleteOperation(request, tableName);
			JyWEBService.setRcList();//���¼����˲��б�
			
//			doType = "query";
		}
		
		//��ѯ
//		if ("query".equals(doType)) {
			selectPageDataByPagination(request, form, tableName, viewName, colList);
//		}
		
		//����
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		request.setAttribute("realTable", tableName);
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		service.setNjXyZyBjList(request);
		return mapping.findForward("resumeManage");
	}
	
	
	/**
	 * �������ӹ���
	 * @return
	 * @throws Exception
	 */
	public ActionForward linksManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "jyweb_yqljb";
		String[] colList = new String[] {"pkValue","ljmc","ljwz","xssx"};
		String doType = request.getParameter("doType");
		
		if (!Base.isNull(doType) && "del".equals(doType)) {
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			service.delFile(pkValues,"tp");//����ɾ��ָ���ļ� tp:ͼƬ
			deleteOperation(request, tableName);//ɾ�����ݿ��¼
			
			//���¼�����������
			JyWEBService.setTpljList();
			
//			doType = "query";
		}
		
		//��ѯ
//		if ("query".equals(doType)) {
			selectPageDataByPagination(request, form, tableName, tableName, colList);
//		}

		request.setAttribute("topTr", service.getColumn(tableName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("linksManage");
	}
	
	

	/**
	 * ��������ά��
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward linksUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "jyweb_yqljb";
		String dirPath = request.getRealPath("")+".upload/jyweb";
		String ljmc = request.getParameter("ljmc");
		String ljwz = request.getParameter("ljwz");
		String xssx = request.getParameter("xssx");
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");

		//ͼƬ�ϴ�
		if (!Base.isNull(doType) && ("save".equals(doType)||"modify".equals(doType))) {
			String path = service.upload(myForm, dirPath,request);
			
			//�޸Ĳ���
			if ("modify".equals(doType)) {
				service.delYqlj(pkValue);//ɾ��ԭ���ݺ�ͼƬ
			}
			//�������
			boolean result = service.saveYqlj(new String[] {ljmc,ljwz,path,xssx});
			request.setAttribute("message", result ? "�����ɹ���" : "����ʧ��!");

			JyWEBService.setTpljList();
		}
		
		//��������
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, tableName, pkValue);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("linksUpdate");
	}
	
	

	/**
	 * �ļ����� 
	 * @return
	 * @throws Exception
	 */
	public ActionForward filesManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "jyweb_wjglb";
		String[] colList = new String[] {"pkValue","wjm","scr","scsj","llcs","xzcs"};
		String doType = request.getParameter("doType");
		
		if (!Base.isNull(doType) && "del".equals(doType)) {
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			service.delFile(pkValues,"file");//����ɾ��ָ���ļ�
			deleteOperation(request, tableName);//ɾ�����ݿ��¼
			
			JyWEBService.setFilesList();//���¼����ļ�Ŀ¼
			
//			doType = "query";
		}
		
		//��ѯ
//		if ("query".equals(doType)) {
			selectPageDataByPagination(request, form, tableName, tableName, colList);
//		}
		
		request.setAttribute("topTr", service.getColumn(tableName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("filesManage");
	}
	
	

	/**
	 * �ļ��ϴ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fileUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		HttpSession session = request.getSession();
		
		String doType = request.getParameter("doType");
		String realName = (String) session.getAttribute("jyweb_realName");
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			
			myForm.setScr(realName);
			service.fileUpload(myForm, request);
			
			JyWEBService.setFilesList();//���¼����ļ�Ŀ¼
		}
		
		return mapping.findForward("fileUpdate");
	}
	
	
	/**
	 * ��ϵ��ʽά��
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxfsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "jyweb_lxfsb";
		String xxdm = Base.xxdm;
		String doType = request.getParameter("doType");
		//����
		if (!Base.isNull(doType) && "save".equals(doType)) {
			insertOperation(request, tableName);
			JyWEBService.setLxfxMap();
		}
		
		//�޸�
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			updateOperation(request, tableName);
			JyWEBService.setLxfxMap();
		}
		
		//������ϵ��ʽ
		selectPageDataByOne(request, tableName, tableName, xxdm);
		
		request.setAttribute("pkValue", xxdm);
		return mapping.findForward("lxfsUpdate");
	}
	
	

	/**
	 * ��Ƹ����� 
	 * @return
	 * @throws Exception
	 */
	public ActionForward zphManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "jyweb_xyzph";
		String viewName = "xg_view_jyweb_xyzph";
		String[] colList = new String[] {"pkValue","disabled","zplx","zphbt","fbsj","llcs","shzt"};
		String doType = request.getParameter("doType");
		
		if (!Base.isNull(doType) && "del".equals(doType)) {
			deleteOperation(request, tableName);
			
//			doType = "query";
		}
		
//		if ("query".equals(doType)) {
			service.setCustomAudiColumn(request, "shzt");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
//		}
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setJywebList(request, "zpgl");
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("zphManage");
	}
	

	
	/**
	 * ��Ƹ�����
	 * @return
	 * @throws Exception
	 */
	public ActionForward zphAuditing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("jyweb_userName");
		String tableName = "jyweb_xyzph";
		String viewName = "xg_view_jyweb_xyzph";
		String[] colList = new String[] {"pkValue","disabled","zplx","zphbt","fbsj","llcs","shzt"};
		String doType = request.getParameter("doType");
		
		if (!Base.isNull(doType) && "sh".equals(doType)) {
			
			HashMap<String, String> valueMap = new HashMap<String, String>();
			
			valueMap.put("shzt", myForm.getSave_shzt());
			valueMap.put("shr", userName);
			valueMap.put("shsj", service.getNow());
			valueMap.put("shyj", myForm.getSave_shyj());
			
			auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), valueMap, tableName);
			
			JyWEBService.setZphList();
			
//			doType = "query";
		}
		
		if (!Base.isNull(doType) && "qxsh".equals(doType)) {
			HashMap<String, String> valueMap = new HashMap<String, String>();
			
			valueMap.put("shzt", "δ���");
			valueMap.put("shr", "");
			valueMap.put("shsj", "");
			valueMap.put("shyj", "");
			
			auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), valueMap, tableName);
			
			JyWEBService.setZphList();
			
//			doType = "query";
		}
		
//		if ("query".equals(doType)) {
			service.setCustomAudiColumn(request, "shzt");
			selectPageDataByPagination(request, form, tableName,viewName, colList);
//		}
		
		service.setJywebList(request, "zpgl");
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("zphAuditing");
	}
	
	
	/**
	 * ��Ƹ��ά�� 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward zphUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		
		String tableName = "jyweb_xyzph";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String zphbt = request.getParameter("save_zphbt");
		String fbr = request.getParameter("save_fbr");
		String fbsj = request.getParameter("save_fbsj");
			
		//����
		if (!Base.isNull(doType) && "save".equals(doType)) {
			insertOperation(request, tableName);
			
			pkValue = new StringBuilder().append(zphbt).append(fbr)
					.append(fbsj).toString();
		}
		
		//�޸�
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			updateOperation(request, tableName);
			
			pkValue = new StringBuilder().append(zphbt).append(fbr)
					.append(fbsj).toString();
		}
		
		//������Ƹ����Ϣ
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, tableName, pkValue);
			HashMap<String,String> map = (HashMap<String, String>) request.getAttribute("rs");
			map.put("zphnr", map.get("zphnr").replaceAll("\"", "\'"));
			request.setAttribute("rs", map);
		}
		
		service.setJywebList(request, "zpgl");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		service.setJywebList(request, "");
		return mapping.findForward("zphUpdate");
	}
	
	
	/**
	 * ͳ��
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String[] topTr  = null;
		List<String[]> rs = new ArrayList<String[]>();
		String flg = request.getParameter("flg");
		String tjfs = request.getParameter("tjfs");
		
			
		if ("zpxx".equals(flg)) {
			topTr = new String[] { "��λ������", "ѧУ������", "��������", "��Ͷ������", "���������",
					"���ղش���", "�ظ�ѧ��������" };
			rs = service.getZpxxTj(myForm);
		} else if ("stu".equals(flg)) {
			if ("xy".equals(tjfs)) {
				topTr = new String[] { Base.YXPZXY_KEY+"����", "��������", "������������", "Ͷ�ݵ�λ��",
						"�������", "�ղظ���", "���ظ�����" };
				rs = service.getStuTjByXy(myForm);
			} else if ("zy".equals(tjfs)) {
				topTr = new String[] { Base.YXPZXY_KEY+"����", "רҵ����", "��������", "������������",
						"Ͷ�ݵ�λ��", "�������", "�ղظ���", "���ظ�����" };
				rs = service.getStuTjByZy(myForm);
			} else if ("bj".equals(tjfs)) {
				topTr = new String[] { Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "��������",
						"������������", "Ͷ�ݵ�λ��", "�������", "�ղظ���", "���ظ�����" };
				rs = service.getStuTjByBj(myForm);
			} else {
				topTr = new String[] { Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����", "���֤��",
						"������������", "Ͷ�ݸ�λ��", "�������", "�ղظ���", "���ظ�����" };
				rs = service.getStuTjByStu(myForm);
			}
		} else {
			rs = service.getDwtj(myForm);
			topTr = new String[] { "��λ����", "������ҵ", "��λע�����", "ѧУ��Ӹ���", "����" };
		}

//		if (!Base.isNull(doType) && "query".equals(doType)) {
			request.setAttribute("rs", rs);
//		}
			
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("topTr", topTr);
		
		service.setJywebList(request, "company");
		service.setNjXyZyBjList(request);
		request.setAttribute("flg", Base.isNull(flg) ? "dwxg" : flg);
		return mapping.findForward("tjManage");
	}
	
	

	/**
	 * У����Ƹ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ztzpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "xg_jyweb_ztzpb";
		String doType = request.getParameter("doType");
		String[] colList = new String[]{"pkValue","disabled","zpzt","zpsj","zpdd","fbsj","fbr","shzt"};
		
		if ("del".equals(doType)) {
			deleteOperation(request, tableName);
			
			boolean result = Boolean.valueOf(request.getAttribute("result").toString());
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			
			if (result) {
				request.setAttribute("message", service.delZtzp(pkValues) ? "�����ɹ�" : "����ʧ��");
			}
			
			JyWEBService.setXnzpList();
			
//			doType = "query";
		}
		
//		if ("query".equals(doType)) {
			service.setCustomAudiColumn(request, "shzt");
			selectPageDataByPagination(request, form, tableName, tableName, colList);
//		}
		
		if ("expData".equals(doType)) {
			expPageData(request, response, tableName, tableName, service.getColumn(tableName));
			return mapping.findForward("");
		}
		
		service.setJywebList(request, "");
		request.setAttribute("topTr", service.getColumn(tableName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("realTable", tableName);
		return mapping.findForward("ztzpManage");
	}
	

	/**
	 * У����Ƹ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ztzpAuditing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("jyweb_userName");
		
		String tableName = "xg_jyweb_ztzpb";
		String doType = request.getParameter("doType");
		String[] colList = new String[]{"pkValue","disabled","zpzt","zpsj","zpdd","fbsj","fbr","shzt"};
		
		if ("sh".equals(doType)){
			
			HashMap<String, String> valueMap = new HashMap<String, String>();
			
			valueMap.put("shzt", myForm.getSave_shzt());
			valueMap.put("shr", userName);
			valueMap.put("shsj", service.getNow());
			valueMap.put("shyj", myForm.getSave_shyj());
			
			auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), valueMap, tableName);
			
			JyWEBService.setXnzpList();
			
//			doType = "query";
		}
		
		if ("qxsh".equals(doType)){
			
			HashMap<String, String> valueMap = new HashMap<String, String>();
			
			valueMap.put("shzt", "δ���");
			valueMap.put("shr", "");
			valueMap.put("shsj", "");
			valueMap.put("shyj", "");
			
			auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), valueMap, tableName);
			
			JyWEBService.setXnzpList();
			
//			doType = "query";
		}
		
//		if ("query".equals(doType)) {
			service.setCustomAudiColumn(request, "shzt");
			selectPageDataByPagination(request, form, tableName, tableName, colList);
//		}
		
		service.setJywebList(request, "");
		request.setAttribute("topTr", service.getColumn(tableName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("ztzpAuditing");
	}


	
	/**
	 * У����Ƹ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ztzpAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyWEBService service = new JyWEBService();
		
		String tableName = "xg_jyweb_ztzpb";
		String id = request.getParameter("save_id");
		String tempDwmc = request.getParameter("tempDwmc");
		String tempGwValue = request.getParameter("tempGwValue");
		String doType = request.getParameter("doType");
		
		if ("save".equals(doType)){
			insertOperation(request, tableName);
			boolean result = (Boolean) request.getAttribute("result");
			boolean ztzpResult = service.saveZtzp(id, tempDwmc, tempGwValue);
			
			request.setAttribute("message", result && ztzpResult ? "�����ɹ�" : "����ʧ��");
			
			JyWEBService.setXnzpList();
		}
		
		
		if (null != id){
			service.setLlcs(id);
			
			selectPageDataByOne(request, tableName, tableName, id);
			request.setAttribute("tempDwmc", service.getTempDwmc(id));
			request.setAttribute("tempGwValue", service.getTempGwValue(id));
			request.setAttribute("ztzpGwList", service.getZtzpGwList(id));
			request.setAttribute("tempDwmcList", service.getTempDwmc(id).split("!!@!!"));
		}
		
		request.setAttribute("now", service.getNow());
		request.setAttribute("id", StringUtils.getGuid());
		return mapping.findForward("ztzpAdd");
	}
	
	

	/**
	 * ����չʾ
	 * @return
	 * @throws Exception
	 */
	public ActionForward resumeView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "jygl_jytjb";
		String viewName = "view_jygl_jytjb";
		String pkValue = request.getParameter("pkValue");
		
		//����ѧ������
		if (!Base.isNull(pkValue)) {
			//�޸ļ������������
			HttpSession session = request.getSession();
			String userType = (String)session.getAttribute("jyweb_userType");
			String realName = (String)session.getAttribute("jyweb_realName");
			
			if("dw".equalsIgnoreCase(userType)){
				JyWEBService service = new JyWEBService();
				Map<String, String> map = new HashMap<String, String>();
				map.put("xh", pkValue);
				map.put("dwmc", realName);
				map.put("czlx", "���");
				map.put("gwmc", "��");
				service.insertTj(map);
			}
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		return mapping.findForward("resumeView");
	}
	
	
	
	/**
	 * ���Ľ���ά��
	 */
	public ActionForward zxjsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String lx = "1";//���Ľ���
		JyWEBService service = new JyWEBService();
		
		request.setAttribute("jsnr", service.getZxjs(lx));
		return mapping.findForward("zxjsManage");
	}
	
	
	/**
	 * ���Ľ��ܱ���
	 */
	public ActionForward zxjsSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm model = (JyglForm) form;
		JyWEBService service = new JyWEBService();
		
		String tableName = "xg_jyweb_zxjs";
		String[] onezd = new String[]{"lx","jsnr"};
		SaveForm saveForm = new SaveForm();
		
		saveForm.setOnezd(onezd);
		saveForm.setTableName(tableName);
		saveForm.setPk("lx");
		saveForm.setPkValue(new String[]{model.getLx()});
		boolean result = service.saveJyweb(saveForm, model, request);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		
		return zxjsManage(mapping, form, request, response);
	}
	
}
