/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-20 ����03:13:22 
 */  
package xsgzgl.qgzx.zjdx.cjff;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.qgzx.jfgl.QgzxJfglService;
import xsgzgl.qgzx.zjdx.tjcx.TjcxDAO;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zjly.zhf.zhfdr.ZhfDrForm;

import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �㽭��ѧ��𷢷�
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-12-20 ����03:13:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CjffAction extends SuperAction<CjffForm, CjffService> {
	CjffService service = new CjffService();
	public static final String DRCGBZ = "����ɹ���";
	public static final String DRSBBZ = "����ʧ��,����ϸ�˶ԡ���������.xls����";
	public static final String KBG = "��excel���";
	public static final String KFILE = "û���ļ���";
	public static final String EXCELREPEAT = "excel�д����ظ�����(ѧ�ţ���������·ݣ����˵�λ�����ظ�)������ϸ�˶ԣ�";
	public ActionForward cjffCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CjffForm model = (CjffForm) form;
		TjcxDAO tjcxDAO = new TjcxDAO();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_nd(new String[] {Base.currNd});
		//Ĭ�ϸ߼���ѯ����-�·�
		String Yf = tjcxDAO.getCsszYf();
		searchModel.setSearch_tj_yf(new String[]{Yf});
		
		HashMap<String,String> dataMap = service.cjffCxTitleXx(Base.currNd,user);
		request.setAttribute("tsxx", dataMap);
		request.setAttribute("dqnd", Base.currNd);
		request.setAttribute("searchTj", searchModel);
		String path = "qgzx_jfcjgl_cjff_zjdx.do";
		request.setAttribute("path", path);
		//��֤�Ƿ��ڳ�𷢷ſ���ʱ����ڣ��Ӷ��õ���������
		HashMap<String, String> csszMap = service.getCsszMap();
		int kssj = Integer.parseInt(csszMap.get("kssj"));
		int jssj = Integer.parseInt(csszMap.get("jssj"));
		request.setAttribute("jssj", jssj);
		boolean sfkq = service.checkIsInKfsjd(kssj, jssj);
		String sqkg = sfkq ? "open" :"close";
		/**
		 * Ϊ��ֹoracel job��ʱ��������ִ�У����ڴ˵�˵�ʱ��������ݣ������������������
		 * ��Ҫ�޸ģ����Բ�������web.xml listerner�����ķ�ʽ,дtime��ʱ��ȥʵ��
		 */
//		if(!sfkq){
//			service.updateWtjsj();
//		}
		request.setAttribute("sqkg", sqkg);
		//��֤�Ƿ����ڹ�����Ա
		QgzxJfglService qgzxJfglService = new QgzxJfglService();
		boolean sfqggly = qgzxJfglService.sfQggly(user.getUserName());
		request.setAttribute("sfqggly", sfqggly);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cjffcx");
	}
	
	/**
	 * 
	 * @����: ��𷢷�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����10:01:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward cjffAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CjffForm myForm = (CjffForm)form;
		if(StringUtils.isNotNull(myForm.getXh())){
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
		}
		User user = getUser(request);
		//���˵�λ
		List<HashMap<String, String>> yrdwList = service.getYrdwyList(user);
		//��λ����
		List<HashMap<String, String>> gwxzList = service.getGwxzList();
		//��λ���
		List<HashMap<String, String>> gwlbList = service.getGwlbList();
		//У��
		List<HashMap<String, String>> xqList = service.getXqList();
		//��������
		HashMap<String, String> csszMap = service.getCsszMap();
		//�·�������
		List<HashMap<String, String>> yfList = service.createList(csszMap.get("ksyf"), csszMap.get("jsyf"));
		request.setAttribute("yrdwList", yrdwList);
		request.setAttribute("gwxzList", gwxzList);
		request.setAttribute("gwlbList", gwlbList);
		request.setAttribute("xqList", xqList);
		request.setAttribute("yfList", yfList);
		//ȡ���������
		request.setAttribute("sxsz", csszMap.get("sxsz"));
		//ȡ���������õ��Ƿ��������������
		String sfyxcgcjsx = csszMap.get("sfyxcgcjsx");
		request.setAttribute("sfyxcgcjsx", sfyxcgcjsx);
		//ȡ������׼
		request.setAttribute("cjbz", csszMap.get("cjbz"));
		request.setAttribute("type", "add");
		request.setAttribute("path", "cjff_zjdx.do?method=cjffAdd");
		//ȡ���û����Ŵ���
		request.setAttribute("yrdwdm", user.getUserDep());
		return mapping.findForward("cjffadd");
	}
	
	/**
	 * 
	 * @����: ��𷢷��޸�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����05:11:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward cjffEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CjffForm myForm = (CjffForm)form;
		CjffForm model = service.getModel(myForm);
		if(StringUtils.isNotNull(model.getId())){
			// ����ѧ��������Ϣ
			HashMap<String, String> xsjbxx = service.getXsxxck(myForm.getId());
			//����
			BeanUtils.copyProperties(myForm, model);
			request.setAttribute("jbxx", xsjbxx);
		} 
		 
		User user = getUser(request);
		//���˵�λ
		List<HashMap<String, String>> yrdwList = service.getYrdwyList(user);
		//��λ����
		List<HashMap<String, String>> gwxzList = service.getGwxzList();
		//��λ���
		List<HashMap<String, String>> gwlbList = service.getGwlbList();
		//У��
		List<HashMap<String, String>> xqList = service.getXqList();
		//��������
		HashMap<String, String> csszMap = service.getCsszMap();
		request.setAttribute("yrdwList", yrdwList);
		request.setAttribute("gwxzList", gwxzList);
		request.setAttribute("gwlbList", gwlbList);
		request.setAttribute("xqList", xqList);
		//��������·ݣ��鿴��
		request.setAttribute("ffndyf", model.getFfndyf());
		//ȡ���������
		request.setAttribute("sxsz", csszMap.get("sxsz"));
		//ȡ���������õ��Ƿ��������������
		String sfyxcgcjsx = csszMap.get("sfyxcgcjsx");
		request.setAttribute("sfyxcgcjsx", sfyxcgcjsx);
		//ȡ������׼
		request.setAttribute("cjbz", csszMap.get("cjbz"));
		request.setAttribute("type", "update");
		return mapping.findForward("cjffedit");
	}
	
	/**
	 * 
	 * @����:��𷢷Ų鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����05:32:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward cjffck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CjffForm myForm = (CjffForm)form;
		if(StringUtils.isNotNull(myForm.getId())){
			// ����ѧ��������Ϣ
			HashMap<String, String> xsjbxx = service.getXsxxck(myForm.getId());
			//����
			BeanUtils.copyProperties(myForm, myForm);
			request.setAttribute("jbxx", xsjbxx);
			HashMap<String, String> bdxxMap = service.getYwbdxxCk(myForm.getId());
			request.setAttribute("bdxxMap", bdxxMap);
		}
		request.setAttribute("type", "view");
		return mapping.findForward("cjffck");
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-22 ����08:45:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward deljg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-22 ����08:47:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CjffForm model = (CjffForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ
		

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 *
	 * @����: �����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-22 ����08:51:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward saveForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CjffForm model = (CjffForm) form;
		String message = "";
		//����֤ѧ�ţ�����׼ȷ��
		if(!service.checkXhXmIsTrue(model.getXh(), model.getXm())){
			message = "ѧ�ţ�����û�ж�Ӧһ�£���ȷ�ϣ�";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		//��֤�����Ƿ��ظ�
		if(!service.checkIsNotExists(model.getXh(), model.getFfndyf(), model.getYrdwdm(), model.getId())){
			message = "������ͬѧ����ͬ���˵�λ��ͬ�������µļ�¼����ȷ�ϣ�";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		//��֤�Ƿ���Ͼ��ѻ���
		HashMap<String, String> dataMap = service.checkIsFhJfhb(model.getFfndyf(), model.getYrdwdm(), model.getBcje(),model.getId());
		if(("false").equals(dataMap.get("rs"))){
			message = "����𡿲��ܳ������ž�������ǰ���ž������"+dataMap.get("syje")+"Ԫ����Ч���Ž��ܳ���"+dataMap.get("syje")+"Ԫ��";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		User user = getUser(request);
		model.setLrr(user.getUserName());
		boolean result = service.saveForm(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: �ύ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-22 ����09:23:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward tj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String values = request.getParameter("values");
		String message = "";
		boolean result = true;
		if (!StringUtil.isNull(values)) {
			 result = service.submit(values.split(","));
		}
		
		message = result ? "�ύ�ɹ���"
				: "�ύʧ�ܣ�";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-22 ����05:30:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward dr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("dr");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ���ص���ģ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-23 ����09:16:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("zjdx_qgzx_cjff_xzmb.xls".getBytes(), "GBK") + "\"");
		service.createWwb(response.getOutputStream());
		return null;
	}
	
	/**
	 * 
	 * @����: ���뱣��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-23 ����11:03:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		CjffForm model = (CjffForm) form;
		FormFile file = model.getDrmb();
		User user = getUser(request);
		model.setLrr(user.getUserName());
		model.setUser(user);
		//��������
		HashMap<String, String> csszMap = service.getCsszMap();
		//�·�������
		List<HashMap<String, String>> yfList = service.createList(csszMap.get("ksyf"), csszMap.get("jsyf"));
		model.setYfList(yfList);
		if(file != null){
			try {
				model.setFilepath(servlet.getServletContext().getRealPath(
				"/temp/importTemp/")+"/");
				HashMap<String,Object> resultMap = service.saveDrExcelInfo(file.getInputStream(),model);
				String message = DRCGBZ;
				if(resultMap.get("result").equals("true")){
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if(resultMap.get("result").equals("null")){
					 message = KBG;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if("excelrepeat".equals(resultMap.get("result"))){
					 message = EXCELREPEAT;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}
				else{
				    message = DRSBBZ;
				    Map<String,String> map = new HashMap<String, String>();
					map.put("message", message);
					map.put("gid", (String)resultMap.get("gid"));
					JSONObject json = JSONObject.fromObject(map); 
				    response.getWriter().print(json);
					return null;
				}
				
			} catch (FileNotFoundException e) {
				// TODO �Զ����� catch ��
				logger.info("�����ļ�δ�ҵ���");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				logger.info("IO�쳣��");
				e.printStackTrace();
			}
		}else{
			  String message = KFILE;
			   Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map);
				response.getWriter().print(json);
				return null;
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����: �����ļ�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-23 ����11:11:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward downloadFileError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//�õ�tomcat/webapp/temp/importTemp�´����ļ���·��
		String filename = request.getParameter("filename");
		String path = servlet.getServletContext().getRealPath(
		"/temp/importTemp/")+"/"+filename;
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("��������.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ȡ���ύ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-6 ����04:52:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward CancelTjRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			boolean result = service.cancelTjjl(ids);
			String message = result ? "ȡ���ύ�ɹ���" : "ȡ���ύʧ�ܣ�";
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	
}
