/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-17 ����09:55:07 
 */  
package com.zfsoft.xgxt.xsztz.tzxmjg;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-7-17 ����09:55:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

 public class XsXmJgAction extends SuperAction<XsXmJgForm,XsXmJgService> {
	XsXmJgService service = new XsXmJgService();
	private final String TZXMJG ="tzxmsq";
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	private static final String url = "sztz_xmsqgl_xmjg.do";
	
	@SystemAuth(url = url)
	public ActionForward getXmJgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    XsXmJgForm model = (XsXmJgForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
//		String[] sqshkg = service.getSqShKg();//����
//		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_xmsqgl_xmjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sztzjg");
	}
	/**
	 * 
	 * @����:ѧ������ѧ�ֲ�ѯ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-21 ����03:49:15
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
	@SystemAuth(url = "sztz_rzjggl_grxfcx.do")
	public ActionForward grxfcxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    XsXmJgForm model = (XsXmJgForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.grxfcxList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_rzjggl_grxfcx.do";
		request.setAttribute("path", path);
		if("stu".equalsIgnoreCase(user.getUserType())){
			request.setAttribute("grzxf", service.getgrZxf(user.getUserName()));
		}
		request.setAttribute("usertype", user.getUserType());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("grxfcxList");
	}
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-10 ����05:13:46
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����������չ-��չ��Ŀ����-��չ��Ŀ���-ɾ��VALUES:{values}")
	public ActionForward delSqjl(ActionMapping mapping, ActionForm form,
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
     * @����: ��չ��Ŀ����
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2015-7-10 ����05:28:38
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsXmJgForm model = (XsXmJgForm) form;
	    User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMJG);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("currxn", Base.currXn);
		String path = "xmsqgl_xmjg.do?method=add";
		request.setAttribute("path", path);
		//������Ϣ����
		return mapping.findForward("sztzadd");
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-13 ����11:10:18
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
	@SystemAuth(url = url)
	public ActionForward getXmSelectList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsXmJgForm model = (XsXmJgForm) form;
		List<HashMap<String,String>> xmsqInfoList = service.getXmSelectList(model.getXh());
	    request.setAttribute("xmsqInfoList", xmsqInfoList);
		return mapping.findForward("xmselect");
	}
	
    /**
     * 
     * @����:TODO(������һ�仰�����������������)
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2015-7-13 ����05:20:50
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����������չ-��չ��Ŀ����-��չ��Ŀ���-DOTYPE:{type},XH:{xh},XMMC:{xmmc},SQLY:{sqly},JGID:{jgid}")
	public ActionForward saveSqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsXmJgForm model = (XsXmJgForm) form;
		boolean result = false;
		String message=null;
	    User user = getUser(request);
 		if(model.getType().equals("save")){
 		model.setXn(Base.currXn);
 			model.setXq(Base.currXq);
 			if(service.checkExistForSave(model) == true){
 				response.getWriter().print(getJsonMessageByKey(MessageKey.CFTX));
				return null;
 			}
 			model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
			result = service.runInsert(model);
		}else if(model.getType().equals("update")){
			
			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-14 ����07:24:21
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
	public ActionForward check(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
		String xh = request.getParameter("xh");
		String xmdm = request.getParameter("xmdm");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		XsXmJgForm model = new XsXmJgForm();
		model.setXh(xh);
		model.setXn(xn);
		model.setXq(xq);
		model.setXmdm(xmdm);
		boolean result = false;
		String flag = "";
		Map<String,String> map = new HashMap<String,String>();
		result = service.checkExistForSave(model);
		if(result == true){
			flag = "1";
		}else{
			flag = "0";
		}
		map.put("flag", flag);
		JSONObject json = JSONObject.fromObject(map);
		response.setContentType("text/html; charset=GBK");
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * ����鿴
	 */
	@SystemAuth(url = url)
	public ActionForward XmjgView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsXmJgForm myForm = (XsXmJgForm) form;
		XsXmJgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMJG);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String, String> hdmap = service.getHdMap(model.getXmdm(),model.getXn(),model.getXq());
		request.setAttribute("hdmap", StringUtils.formatData(hdmap));
		request.setAttribute("form",model);
		return mapping.findForward("view");
	}
	
	/**
	 * ������չ��Ŀ�����޸�
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editSqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsXmJgForm myForm = (XsXmJgForm) form;
		XsXmJgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMJG);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String, String> hdmap = service.getHdMap(model.getXmdm(),model.getXn(),model.getXq());//false
		hdmap.put("jgid", model.getJgid());
		request.setAttribute("sqly", model.getSqly());
		request.setAttribute("hdmap", StringUtils.formatData(hdmap));
		String path = "xmsqgl_xmjg.do?method=editSqjg";
		request.setAttribute("path", path);
		return mapping.findForward("editSqjg");
	}
	
	
	
	/**
	 * ������չ����������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsXmJgForm  model = (XsXmJgForm) form;

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
	public ActionForward grxfExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsXmJgForm  model = (XsXmJgForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.grxfcxList(model,
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
	 * @����:����䷢����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-20 ����11:48:53
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveJxbf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsXmJgForm myForm = (XsXmJgForm) form;
		boolean result = service.saveJxbf(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * @����: ���չ����Ƽ�ְҵѧԺ�������Ի�����������չ������֤�����ѯ��������ѧ�ֲ�ѯ����
	 * 		  �����������ӡ��
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2015-11-24 ����05:20:25
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
	public ActionForward dyXftjbCommon(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsXmJgForm myForm = (XsXmJgForm) form;
		File wordFile = getXftjb(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	/**
	 * @����: ���չ����Ƽ�ְҵѧԺ�������Ի�����������չ������֤�����ѯ��������ѧ�ֲ�ѯ����
	 * 		  �����ڱ����ȡ���ݡ�
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-11-24 ����05:19:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getXftjb (XsXmJgForm myForm,HttpServletRequest request) throws Exception{
			XsxxService xsxxService = new XsxxService();
			XsXmJgService xsXmJgService = new XsXmJgService();
			String xh = myForm.getXh();
			Map<String,Object> data = new HashMap<String,Object>();
			// ����ѧ��������Ϣ
			HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
			data.putAll(xsxxMap);
			//ѧ����Ƭ
			InputStream is = xsxxService.getPhotoStream(xh);
			File photoFile = FileUtil.inputstreamToFile(is, "doc");
			String photo = FileUtil.getBASE64(photoFile);
			if (StringUtil.isNull(photo)){
				//ȡĬ����Ƭ 
				photo = xsxxService.getDefaultPhotoBase64(request);
			}
			if(photo == null){
				photo = "";
			}
			data.put("photo", photo);
			//��ѧ���
			data.put("rxrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("rxrq"),FomartDateType.month));
			//��ȡ���θɲ�
			String drgb = new DwwhService().getZwForXh(xh);
			data.put("drgb", drgb);
			//��ȡ��Ŀ������ѧ�֡��Ƿ�ϸ�ͳ���ж�
			List<HashMap<String, String>> sztzhdxfList = xsXmJgService.getSztzhdxfList(xh);
			data.put("sztzhdxfList", sztzhdxfList);
			File file = null;
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//sztz","sztzgrxfcx_13344.xml",xh+"-"+xsxxMap.get("xm"));
			return file;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xscjPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		File file = service.getXscjWord(xh,request);
		FileUtil.outputWord(response, file);
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xscjPrintZip(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<File> files = new ArrayList<File>();
		String xh = request.getParameter("xh");
		String[] xhs = xh.split(",");
		for (int i = 0 , n = xhs.length; i < n ; i++){
			File file = service.getXscjWord(xhs[i],request);
			files.add(file);
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
		FileUtil.outputZip(response, zipFile);
	return null;
	}
	
	
}
