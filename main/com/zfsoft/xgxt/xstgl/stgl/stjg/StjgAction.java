/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-4 ����11:36:27 
 */
package com.zfsoft.xgxt.xstgl.stgl.stjg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.stlbgl.StlbglService;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.xmlbgl.XmlbglService;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgService;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqService;

/**
 * @ϵͳ����: ��������ϵͳ
 * @ģ������: ���Ź���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-4 ����11:36:27
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class StjgAction extends SuperAction<StjgForm, StjgService> {
	private StjgService service = new StjgService();
	private StlbglService stlbService = new StlbglService();
	private XmlbglService xmlbService = new XmlbglService();
	private StsqService stsqservice = new StsqService();
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	private static final String url = "stgl_stgl_stjg.do";
	
	/**
	 * 
	 * @����:��ѯ���Ž���б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����01:54:11
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
	@SystemAuth(url = url)
	public ActionForward getStjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StjgForm model = (StjgForm) form;
		User user = getUser(request);
		request.setAttribute("usertype", user.getUserType());
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
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
		request.setAttribute("searchTj", searchModel);
		String path = "stgl_stgl_stjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getStjgList");
	}
	
	/**
	 * 
	 * @����:���Ž������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����05:27:51
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
	public ActionForward addStjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StjgForm model = (StjgForm) form;
		User user = getUser(request);
		// ѧ�� ѧ��list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		initParam(request, user);
		List<HashMap<String, String>> stlbList = stlbService.getStlbList();
		request.setAttribute("stlbList", stlbList);
		String stlbdm = stlbList.get(0).get("stlbdm");
		if("12872".equals(Base.xxdm)) {
			request.setAttribute("gkdwList", service.getBbdmlist());
			List<HashMap<String, String>> stxjList = stsqservice.getstxjList();
			request.setAttribute("stxjList",stxjList);
		}
		request.setAttribute("xmlbList", xmlbService.getXmlbList(stlbdm));
		return mapping.findForward("addStjg");
	}
	/**
	
	 * 
	 * @����:�޸����Ž��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����01:55:20
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
	public ActionForward editStjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StjgForm myForm = (StjgForm) form;
		User user = getUser(request);
		StjgForm stjgForm = service.getModel(myForm);
		HashMap<String, String> stjg = service.getStjg(stjgForm);
		request.setAttribute("stfzrxm", stjg.get("stfzrxm"));
		request.setAttribute("zdlsxm", stjg.get("zdlsxm"));
		request.setAttribute("fzrlb", stjg.get("fzrlb"));
		request.setAttribute("sjly", stjg.get("sjly"));
		request.setAttribute("fzrbj", stjg.get("fzrbj"));
		request.setAttribute("fzrxy", stjg.get("fzrxy"));
		List<HashMap<String, String>> stlbList = stlbService.getStlbList();
		request.setAttribute("stlbList", stlbList);
		request.setAttribute("xmlbList", xmlbService.getXmlbList(stjgForm.getStlbdm()));
		//ָ����ʦ��Ϣ
		List<HashMap<String,String>> ZdlsInfoList=service.getZdlsInfo(myForm);
		request.setAttribute("ZdlsInfoList",ZdlsInfoList);
		if("12872".equals(Base.xxdm)) {
			request.setAttribute("gkdwList", service.getBbdmlist());
			List<HashMap<String, String>> stxjList = stsqservice.getstxjList();
			request.setAttribute("stxjList",stxjList);
		}
		if(null!=stjgForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(stjgForm));
		}
		initParam(request, user);
		return mapping.findForward("editStjg");
	}
	/**
	 * 
	 * @����:�鿴���Ž��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����01:55:20
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
	public ActionForward viewStjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StjgForm myForm = (StjgForm) form;
		StjgForm model = service.getModel(myForm);
		HashMap<String,String> stjgMap = service.getStjg(model);
		request.setAttribute("rs", StringUtils.formatData(stjgMap));
		request.setAttribute("path", "stglStjg.do?method=viewStjg");
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xstgl");
		String splcname = "";
		for (HashMap<String, String> hashMap : shlc) {
			if(hashMap.get("splc").equalsIgnoreCase(model.getSplc())){
				splcname = hashMap.get("lcxx");
			}
		}
		request.setAttribute("splcname", splcname);
		//ָ����ʦ��Ϣ
		List<HashMap<String,String>> ZdlsInfoList=service.getZdlsInfo(myForm);
		request.setAttribute("ZdlsInfoList",ZdlsInfoList);
		return mapping.findForward("viewStjg");
	}
	
	/**
	 * 
	 * @����:���Ž������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����05:28:12
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
	public ActionForward saveStjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StjgForm myForm = (StjgForm) form;
		User user =getUser(request);
		if (service.isHaveSbjg(myForm)) {
			String message = MessageUtil.getText(MessageKey.SZTZ_XMSB_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editStjg(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-5 ����02:06:43
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
	public ActionForward delStjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			if (service.isHaveSqJl(values)) {// ������
				String messageKey = MessageKey.STGL_STGL_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
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
	 * @����:���Ž������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����10:28:18
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		StjgForm model = (StjgForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getJgAll(model,
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
	 * @����:��Ŀ����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-8-4 ����10:32:13
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
	public ActionForward xmsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StjgForm myForm = (StjgForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		StjgForm model = service.getModel(myForm);
		request.setAttribute("xmmc",model.getStxmmc());
		BeanUtils.copyProperties(myForm, model);
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// �Ƿ�list
		request.setAttribute("kgList", isnotList);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsz");
	}
	
	private void initParam(HttpServletRequest request,User user) throws Exception{
		List<HashMap<String, String>> bmList = new OptionUtil().getOptions("gkdw");
		request.setAttribute("jtrxm", user.getRealName());
		request.setAttribute("jtr", user.getUserName());
		/*�����汾��Ĭ������ʱ���Ϊyyyy-MM-dd��ԭ��Ϊyyyy-MM-dd hh24:mm:ss*/
		request.setAttribute("sqsj", GetTime.getTimeByFormat("yyyy-MM-dd"));
		request.setAttribute("bmList", bmList);
		request.setAttribute("xnList", Base.getXnndList());
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xstgl");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		request.setAttribute("zclist", stsqservice.getZclblist());
		/*Ĭ����Чѧ��*/
		request.setAttribute("mryxxn", Base.currXn);
		/*�����汾������������*/
		request.setAttribute("ssbmlist", stsqservice.getBbdmlist());
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:�汾�����¼�������Ϣ���ƹ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-9-15 ����10:30:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param map
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward copeOfStxx(ActionMapping map,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		StjgForm model = (StjgForm)form;
//		//��ȡ������������Ϣ
//		StjgForm stjg = service.getModel(model);
		request.setAttribute("stxmmc", model.getStxmmc());
		request.setAttribute("currxn", Base.currXn);
		request.setAttribute("stid", model.getStid());
		request.setAttribute("xnList", Base.getXnndList());
		return map.findForward("copestxx");
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-9-15 ����10:40:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param map
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveCopeStxx(ActionMapping map,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		StjgForm model = (StjgForm)form;
		//�ж���ͬѧ��������ͬ���Ƶļ�¼��ֱ�ӷ��ش�����Ϣ
		StjgForm checkform = new StjgForm();
		checkform.setStxmmc(model.getStxmmc());
		checkform.setXn(model.getXn());
		if(service.isHaveSbjg(checkform)){
			String message = MessageUtil.getText(MessageKey.SZTZ_XMSB_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		StjgForm stjg = null;
		boolean result = false;
		//��ȡ������������Ϣ
		if(model.getStid() != null){
			stjg = service.getModel(model);
		}
		
		StjgForm newstxm = new StjgForm();
		if(stjg != null){
			BeanUtils.copyProperties(newstxm, StringUtils.formatData(stjg));
			String stid = UniqID.getInstance().getUniqIDHash();
			stid = stid.toUpperCase();
			newstxm.setStid(stid);
			newstxm.setCysl("0");
			newstxm.setSjly("0");
			newstxm.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
			newstxm.setXn(model.getXn());
			newstxm.setStxmmc(model.getStxmmc());
			newstxm.setSqkg("0");
			newstxm.setSqjssj(null);
			newstxm.setSqjssj(null);
			result=service.runInsert(newstxm);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	private File getWord(StjgForm myForm,HttpServletRequest request) throws Exception{
		StjgForm model = (StjgForm)myForm;
		StjgForm stjgForm = service.getModel(model);
		HashMap<String,String> stjgMap = service.getStjg(stjgForm);
		User user = getUser(request);
		Map<String,Object> data = new HashMap<String,Object>();
		List<HashMap<String, String>> stcyxx = service.getStxxCylist(stjgForm, user);
		data.putAll(stjgMap);
		data.put("dysj", GetTime.getNowTime());
		data.put("xxmc", Base.xxmc);
		data.put("stcyList", stcyxx);
		return FreeMarkerUtil.getWordFile(data,"classpath://templates//stgl","stqkdjb.xml",stjgMap.get("stxmmc"));
	}
	
	public ActionForward getStqkdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StjgForm myForm = (StjgForm) form;
		
		File wordFile = getWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	public ActionForward getStqkdjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StjgForm myForm = (StjgForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setStid(values[i]);
				File file = getWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
}
