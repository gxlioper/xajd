/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-2-12 ����03:53:28 
 */  
package com.zfsoft.xgxt.xszy.qsppgl;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.coyote.Request;
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
import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.xszy.xsqshf.XszyQshfForm;
import com.zfsoft.xgxt.xszy.xsqshf.XszyQshfService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-2-12 ����03:53:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XszyQsppAction extends SuperAction<XszyQsppForm, XszyQsppService>{
	private XszyQsppService service = new XszyQsppService();
	
	private static final String url = "xszy_qsppgl.do";
	
	/**
	 * 
	 * @����:����ƥ���б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-12 ����04:56:50
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
	public ActionForward getQsppList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQsppForm model = (XszyQsppForm) form;
		User user = getUser(request);
		if (!"xy".equalsIgnoreCase(user.getUserStatus())&&!"xx".equalsIgnoreCase(user.getUserStatus())) {
				String msg = "��ģ�������Ժϵ��У���û����ʣ���ȷ�ϣ�";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setNj(Base.currNd);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "xszy_qsppgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qsppList");
	}
	/**
	 * 
	 * @����:����ͳ���б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-2 ����08:54:47
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
	public ActionForward getQstjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQsppForm model = (XszyQsppForm) form;
		User user = getUser(request);
		if (!"xy".equalsIgnoreCase(user.getUserStatus())&&!"xx".equalsIgnoreCase(user.getUserStatus())) {
				String msg = "��ģ�������Ժϵ��У���û����ʣ���ȷ�ϣ�";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setNj(Base.currNd);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "xszy_tjcx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qstjList");
	}
	/**
	 * 
	 * @����:�ֹ�ƥ�����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-13 ����10:53:54
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
	public ActionForward getSgppXszyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQsppForm model = (XszyQsppForm) form;
		if (null == model.getNj()) {
			model.setNj(Base.currNd);
		}
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getSgppXszyList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("nj", Base.currNd);
		String path = "xszy_xszygl.do";
		request.setAttribute("path", path);
		request.setAttribute("qsppForm", StringUtils.formatData(model));
		return mapping.findForward("sgppXszyList");

	}
	/**
	 * 
	 * @����:�ֶ�ƥ�䱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-13 ����03:36:54
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
	public ActionForward saveSdpp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQsppForm model = (XszyQsppForm) form;
		User user = getUser(request);
		boolean result = service.saveSdpp(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;

	}
	/**
	 * 
	 * @����:�Զ�ƥ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-14 ����05:10:43
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
	public ActionForward zdpp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQsppForm model = (XszyQsppForm) form;
		User user = getUser(request);
		if(null==model.getNj()){
			model.setNj(Base.currNd);
		}
		HashMap<String,String> ppxqMap  = service.getQsxxAndXszy(model, user);
		request.setAttribute("ppxqMap", ppxqMap);
		return mapping.findForward("zdpp");

	}
	/**
	 * 
	 * @����:�Զ����䱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-15 ����04:42:44
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
	public ActionForward saveZdpp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQsppForm model = (XszyQsppForm) form;
		User user = getUser(request);
		if(null==model.getNj()){
			model.setNj(Base.currNd);
		}
		boolean result = service.saveZdpp(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;

	}
	/**
	 * 
	 * @����:���ƥ����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-14 ����11:11:35
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
	public ActionForward qkppjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQsppForm model = (XszyQsppForm) form;
		String values = model.getId();
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
	 * @����:����ƥ���˻�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-2-14 ����01:49:21
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
	public ActionForward qsppTh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String values = request.getParameter("values");
		String lddms =request.getParameter("lddms");
		String qshs = request.getParameter("qshs");
		String ssyxdms=request.getParameter("ssyxdms");
		User user = getUser(request);
		if (!StringUtil.isNull(values)) {
			boolean result=service.qsppTh(values, lddms, qshs,ssyxdms, user);
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_SAVE_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_SAVE_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_CANCEL_NULL);
		}
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward qstjExport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		XszyQsppForm model = (XszyQsppForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		model.setNj(Base.currNd);
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("xszy_tjcx.do");
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getExportData(model,
				user);// ��ѯ�����м�¼������ҳ
		// ============= ִ�д�ӡ���� ============
		response.reset();
		 response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("xszy_tjcx.xls".getBytes(), "GBK") + "\"");
		response.setContentType("application/vnd.ms-excel");
		
		service.qstjExport(model, response.getOutputStream(),resultList, user);

		// ============= end ============

		return null;
	}
	/**
	 * 
	 * @����:���ĵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-13 ����02:20:11
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
	public ActionForward fwExport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		XszyQsppForm model = (XszyQsppForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		model.setNj(Base.currNd);
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("xszy_tjcx.do");
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getfwExportData(model,
				user);// ��ѯ�����м�¼������ҳ
		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		service.fwExport(model, response.getOutputStream(),resultList, user);

		// ============= end ============

		return null;
	}
	/**
	 * 
	 * @����:��ϵ����ӡ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-4 ����04:28:23
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
	public ActionForward lxkPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XszyQsppForm model = (XszyQsppForm) form;
		XszyQshfForm qshfForm = new XszyQshfForm();
		
		qshfForm.setNj(model.getNj());
		qshfForm.setLddm(model.getLddm());
		qshfForm.setQsh(model.getQsh());
		qshfForm.setDl(URLDecoder.decode(model.getDl(),"UTF-8"));
		File file = getLxkWord(qshfForm);
		FileUtil.outputWord(response, file);
		return null;
	}
	/**
	 * 
	 * @����:��ϵ��������ӡ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-10 ����11:47:16
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
	public ActionForward lxkPrintZip(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XszyQsppForm model = (XszyQsppForm) form;
		XszyQshfForm qshfForm = new XszyQshfForm();
		CommService comService = new CommService();
//		String[] lddms = model.getLddm().split(",");
//		String[] qshs = model.getQsh().split(",");
//		String[] dls = URLDecoder.decode(model.getDl(),"UTF-8").split(",");
//		String[] njs = model.getNj().split(",");
		List<File> files = new ArrayList<File>();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("xszy_tjcx.do");
		model.setSearchModel(searchModel);
		model.setNj(Base.currNd);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(
				model, user);
		for (int i = 0 , n = resultList.size(); i < n ; i++){
			qshfForm.setNj(resultList.get(i).get("nj"));
			qshfForm.setLddm(resultList.get(i).get("lddm"));
			qshfForm.setQsh(resultList.get(i).get("qsh"));
			qshfForm.setDl(resultList.get(i).get("dl"));
			File file = getLxkWord(qshfForm);
			files.add(file);
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
		FileUtil.outputZip(response, zipFile);
	return null;
	}
    /**
     * 
     * @����:����֤���ӡ
     * @���ߣ�xiaxia[���ţ�1104]
     * @���ڣ�2015-3-12 ����05:04:51
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
	public ActionForward ryzsPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XszyQsppForm model = (XszyQsppForm) form;
		File file = getRyzsWord(model);
		FileUtil.outputWord(response, file);
		return null;
	}
	/**
	 * 
	 * @����:����֤��������ӡ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-10 ����11:47:16
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
	public ActionForward ryzsPrintZip(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XszyQsppForm model = (XszyQsppForm) form;
		String[] zghs = model.getZgh().split(",");
		String[] xms = model.getXm().split(",");
		String[] njs = model.getNj().split(",");
		List<File> files = new ArrayList<File>();
		for (int i = 0 , n = zghs.length ; i < n ; i++){
			model.setNj(njs[i]);
			model.setXm(xms[i]);
			model.setZgh(zghs[i]);
			File file = getRyzsWord(model);
			files.add(file);
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
		FileUtil.outputZip(response, zipFile);
	return null;
	}
	
	private File getLxkWord(XszyQshfForm model) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		XszyQshfService qshfService = new XszyQshfService();
		HashMap<String,String> qsxxMap = qshfService.getQsxx(model);
        List<HashMap<String,String>> rzxsList = qshfService.getRzxsListOfLxk(model);
        List<HashMap<String,String>> xsFdyList = qshfService.getXsFdyList(model);
        List<HashMap<String,String>> bzrList=new ArrayList<HashMap<String,String>>();
        bzrList.addAll(rzxsList);
        for (int i = 0; i < bzrList.size(); i++) {
			if(null==bzrList.get(i).get("bzrxm")||"".equals(bzrList.get(i).get("bzrxm"))||"null".equals(bzrList.get(i).get("bzrxm"))){
				bzrList.remove(i);
				--i;
			}
		}
        data.put("q", qsxxMap);
		data.put("rzxsList", rzxsList);
		data.put("bzrList", bzrList);
		data.put("xsFdyList", xsFdyList);
		data.put("xxmc", Base.xxmc);
		data.put("dl", model.getDl());
		File file  = FreeMarkerUtil.getWordFile(data, "classpath://templates//xszy", "xszy_lxk.xml", qsxxMap.get("ldmc")
				+ "-" + qsxxMap.get("qsh"));
		return file;
	}
	/**
	 * 
	 * @����:����֤��word
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-12 ����05:10:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getRyzsWord(XszyQsppForm model) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		String dysj = GetTime.getTimeByFormat("yyyy-MM-dd");
		data.put("xm", model.getXm());
        data.put("nj", model.getNj());
		data.put("xxmc", Base.xxmc);
		data.put("dysj", DateUtils.getDateString(DateUtils.parse(dysj),"5"));
		File file  = FreeMarkerUtil.getWordFile(data, "classpath://templates//xszy", "xszy_ryzs.xml", model.getZgh()
				+ "-" + model.getXm());
		return file;
	}
	
	/**
	 * 
	 * @����:���鵼��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-24 ����07:07:08
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
	public ActionForward qstjExportGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		XszyQsppForm model = (XszyQsppForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		model.setNj(Base.currNd);
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("xszy_tjcx.do");
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getExportData(model,
				user);// ��ѯ�����м�¼������ҳ
		List<HashMap<String, String>> groupList = service.getExportDataGroup(model, user);
		List<File> files = new ArrayList<File>();
		// ============= ִ�д�ӡ���� ============
		if(null != groupList  && groupList.size() > 0){
			for (int i = 0; i < groupList.size(); i++) {
				String fordwdm = groupList.get(i).get("dwdm");
				String fordwmc = groupList.get(i).get("dwmc");
				List<HashMap<String, String>> forList = new ArrayList<HashMap<String,String>>();
				for (int j = 0; j < resultList.size(); j++) {
					if(resultList.get(j).get("dwdm").equals(fordwdm)){
						forList.add(resultList.get(j));
					}
				}
			  File	file = service.qstjExportGroup(model, fordwmc, forList, user);
			  files.add(file);
			}
			if(null == files){
				response.reset();
				 response.setHeader("Content-Disposition", "attachment;filename=\""
			               + new String("xszy_tjcx.xls".getBytes(), "GBK") + "\"");
				response.setContentType("application/vnd.ms-excel");
				service.qstjExport(model, response.getOutputStream(), resultList, user);
				return null;
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			return null;
		}else{
			response.reset();
			 response.setHeader("Content-Disposition", "attachment;filename=\""
		               + new String("xszy_tjcx.xls".getBytes(), "GBK") + "\"");
			response.setContentType("application/vnd.ms-excel");
			service.qstjExport(model, response.getOutputStream(), resultList, user);
			return null;
		}
		

		// ============= end ============

		
	}
}
