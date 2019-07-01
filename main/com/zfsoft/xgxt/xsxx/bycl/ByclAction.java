package com.zfsoft.xgxt.xsxx.bycl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������:��ҵ����
 * @���ߣ� qilm
 * @ʱ�䣺 2013-12-5
 * @�汾�� V1.0
 */
public class ByclAction extends SuperAction {

	private ByclService service = new ByclService();

	private static final String url = "xjyd_bycl.do";
	public static final String DRCGBZ = "����ɹ���";
	public static final String DRSBBZ = "����ʧ��,����ϸ�˶ԡ���������.xls����";
	public static final String KBG = "��excel���";
	public static final String KFILE = "û���ļ���";
	public static final String EXCELREPEAT = "Excel�д����ظ�����(ѧ�������ظ�)������ϸ�˶ԣ�";
	
	/**
	 * @����:��ҵ�����б�
	 * @���ߣ� qilm
	 * @ʱ�䣺 2013-9-27
	 * @�汾�� V1.0
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward byclList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ByclForm myForm = (ByclForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {

			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("xjyd_bycl.do");
			myForm.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		request.setAttribute("path", "xjyd_bycl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("byclList");
	}

	/**
	 * 
	 * @����: ��ҵ����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-5 ����04:26:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-��ҵ����-����XH:{xh}")
	public ActionForward bycl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ByclForm myForm = (ByclForm) form;
		User user = getUser(request);
		String selected = request.getParameter("selected");

		if (SAVE.equalsIgnoreCase(myForm.getType())) {

			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("xjyd_bycl.do");
			myForm.setSearchModel(searchModel);

			boolean result = service.runUpdate(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// ȫѡ�����
		if ("all".equals(selected)) {

			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("xjyd_bycl.do");
			myForm.setSearchModel(searchModel);

			// ��ѯȡ����������
			int counts = service.getCounts(myForm, user);

			request.setAttribute("yxzxss", counts);
			myForm.setSelected("all");

		} else {
			// �趨VALUE������
			String values = myForm.getValues();
			request.setAttribute("values", values);
			if (StringUtils.isNotNull(values)) {
				request.setAttribute("yxzxss", values.split(",").length);
			} else {
				request.setAttribute("yxzxss", "0");
			}
		}

		return mapping.findForward("bycl");
	}

	/**
	 * 
	 * @����: ȡ����ҵ����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-5 ����04:26:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-ѧ���춯-��ҵ����-����ȡ��XH:{xh}")
	public ActionForward qxbycl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ByclForm myForm = (ByclForm) form;
		User user = getUser(request);

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("xjyd_bycl.do");
		myForm.setSearchModel(searchModel);

		boolean result = service.runDelete(myForm, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;

	}

	/**
	 * 
	 * @����: ��ҵ������
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByclForm model = (ByclForm) form;
		ByclService service = new ByclService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("xjyd_bycl.do");
		model.setSearchModel(searchModel);
		User user = getUser(request);
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
	 * @����:����ʦ����ѧ��ҵ��������Ϣ��ӡ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-23 ����03:17:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printJcXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByclForm myForm = (ByclForm) form;
		File wordFile = getJcxxWord(myForm, request);
			FileUtil.outputWord(response, wordFile);
		return null;
	}

	/**
	 * 
	 * @����:����ʦ����ѧ��ҵ��������Ϣ��ӡ(���)
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-23 ����05:06:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printJcXxZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByclForm myForm = (ByclForm) form;
		String value = request.getParameter("xh");
		if (StringUtils.isNotNull(value)) {
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0, n = values.length; i < n; i++) {
				myForm.setXh(values[i]);
				File file = getJcxxWord(myForm, request);
				if(null!=file){
				files.add(file);
				}
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}

		return null;
	}
	

	/**
	 * 
	 * @����:����ʦ����ѧ��ҵ��������Ϣ������ӡ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-23 ����06:24:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printPlJcXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByclForm myForm = (ByclForm) form;
		User user = getUser(request);
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("xjyd_bycl.do");
		searchModel.setSearch_tj_yw(new String[]{"��"});
		myForm.setSearchModel(searchModel);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(myForm, user);
		List<File> files = new ArrayList<File>();
		for (HashMap<String, String> map : resultList) {
			myForm.setXh(map.get("xh"));
			File file = getJcxxWord(myForm, request);
			if(null!=file){
			files.add(file);
			}
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
		FileUtil.outputZip(response, zipFile);

		return null;
	}

	private File getJcxxWord(ByclForm myForm, HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		String xh = myForm.getXh();
		PjjgService pjjgService = new PjjgService();
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> xsxxMap = xsxxglService.getXsxxByXh(xh);// ѧ����Ϣ
		List<HashMap<String, String>> pjList = pjjgService.getPjpyInfoMap(xh);// ������Ϣ
		List<HashMap<String, String>> wjcfList = xsxxglService.getWjcfList(xh);// Υ�ʹ�����Ϣ
		File file = null;
		if(0==pjList.size()&&0==wjcfList.size()){
			return null;
		}
		data.putAll(xsxxMap);
		int pjSize=(12 - pjList.size())<0?0:(12 - pjList.size());
		int wjcfSize=(4 - wjcfList.size())<=0?0:(4 - wjcfList.size());
		data.put("pjBlankList", service.getBlankList(pjSize));
		data.put("wjcfBlankList", service.getBlankList(wjcfSize));
		String nj = xsxxMap.get("nj");
		String xz = xsxxMap.get("xz");
		String bynd = null;
		if(null!=nj&&xz!=null){
			bynd = Integer.parseInt(nj) + Integer.parseInt(xz) + "";
		}
		data.put("pjList", pjList);
		data.put("wjcfList", wjcfList);
		data.put("bynd", bynd);
		data.put("xxmc",Base.xxmc);
		String dysj = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss");
		data.put("dysj", DateTranCnDate.fomartDateToCn(dysj,FomartDateType.month));
		file = FreeMarkerUtil.getWordFile(data,
				"classpath://templates//xsxx//xjyd", "jcxxb_11318.xml", xh
						+ "-" + xsxxMap.get("xm"));
		return file;

	}
	/**
	 * 
	 * @����:��֤ѧ���Ƿ���ڽ�����Ϣ�����������򲻴�ӡ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-2 ����01:55:19
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
	public ActionForward checkJcxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		PjjgService pjjgService = new PjjgService();
		XsxxglService xsxxglService = new XsxxglService();
		List<HashMap<String, String>> pjList = pjjgService.getPjpyInfoMap(xh);// ������Ϣ
		List<HashMap<String, String>> wjcfList = xsxxglService.getWjcfList(xh);// Υ�ʹ�����Ϣ
		if(0==pjList.size()&&0==wjcfList.size()){
			response.getWriter().print(getJsonMessage("��ѧ���޽�����Ϣ�������ӡ��"));
		}else{
			response.getWriter().print(getJsonMessage("true"));
		}
		return null;

	}
	
	/**
	 * @����: ������ҽҩ��ѧ��ҵѧ����Ϣ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-10 ����11:34:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward byxsImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("byxsImport");
	}
	
	/**
	 * @����: ��ҵѧ����Ϣ����-ģ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-10 ����02:57:32
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
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename=\""+ new String("xsxx_byxs.xls".getBytes(), "GBK") + "\"");
		/*����������*/
		service.createWwb(response.getOutputStream());
		return null;
	}
	
	/**
	 * @����: ���뱣��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-10 ����04:50:20
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
			HttpServletRequest request, HttpServletResponse response)
		throws IOException{
		ByclForm model = (ByclForm) form;
		FormFile file = model.getDrmb();
		if(file != null){
			try{
				model.setFilepath(servlet.getServletContext().getRealPath("/temp/importTemp/")+"/");
				HashMap<String,Object> resultMap = service.saveDrExcelInfo(file.getInputStream(),model);
				/*����ɹ�*/
				String message = DRCGBZ;
				if(resultMap.get("result").equals("true")){
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if(resultMap.get("result").equals("null")){
					/*��Excel���*/
					 message = KBG;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if("excelrepeat".equals(resultMap.get("result"))){
					/*Excel�д����ظ�����(ѧ�������ظ�)������ϸ�˶ԣ�*/
					 message = EXCELREPEAT;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else{
					/*����ʧ��,����ϸ�˶ԡ���������.xls����*/
				    message = DRSBBZ;
				    Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						map.put("gid", (String)resultMap.get("gid"));
						JSONObject json = JSONObject.fromObject(map); 
					    response.getWriter().print(json);
						return null;
				}
				
			}catch (FileNotFoundException e) {
				// TODO �Զ����� catch ��
				logger.info("�����ļ�δ�ҵ���");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				logger.info("IO�쳣��");
				e.printStackTrace();
			}
		}else{
		   /*û���ļ���*/
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
	 * @����: �����ļ�����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-12 ����08:46:34
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
}
