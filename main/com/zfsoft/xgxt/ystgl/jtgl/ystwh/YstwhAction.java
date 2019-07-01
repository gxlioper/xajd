/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-4 ����11:36:27 
 */
package com.zfsoft.xgxt.ystgl.jtgl.ystwh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqService;
import com.zfsoft.xgxt.ystgl.dmwh.YstglDmwhService;

/**
 * @ϵͳ����: ��������ϵͳ
 * @ģ������: �����Ź���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2016-1-4 ����11:36:27
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class YstwhAction extends SuperAction<YstwhForm, YstwhService> {
	private YstwhService service = new YstwhService();
	private YstglDmwhService dmwhService = new YstglDmwhService();
	private StsqService stsqservice = new StsqService();
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	private static final String url = "ystgl_ystgl_ystwh.do";
	
	/**
	 * 
	 * @����:��ѯ�������б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-1-14 ����01:54:11
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
	public ActionForward getYstwhList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstwhForm model = (YstwhForm) form;
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
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getYstwhList");
	}
	
	/**
	 * 
	 * @����:����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-1-4 ����05:27:51
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
	@SystemLog(description = "���������Ź���-������ά��-����-ystid:{ystid}")
	public ActionForward addYstwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstwhForm model = (YstwhForm) form;
		User user = getUser(request);
		// ѧ�� ѧ��list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		initParam(request, user);
		List<HashMap<String, String>> ystlbList = dmwhService.getYstlbListAll();
		request.setAttribute("ystlbList", ystlbList);
		String ystlbdm = ystlbList.get(0).get("stlbdm");
		request.setAttribute("xmlbList", dmwhService.getXmlbListAll(ystlbdm));
		return mapping.findForward("addYstwh");
	}
	/**
	
	 * 
	 * @����:�޸�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-1-4 ����01:55:20
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
	@SystemLog(description = "���������Ź���-������ά��-�޸�-ystid:{ystid}")
	public ActionForward editYstwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstwhForm myForm = (YstwhForm) form;
		User user = getUser(request);
		YstwhForm ystwhForm = service.getModel(myForm);
		HashMap<String, String> ystwhMap = service.getYstwh(ystwhForm);
		
		List<HashMap<String, String>> ystlbList = dmwhService.getYstlbListAll();
		request.setAttribute("ystlbList", ystlbList);
		request.setAttribute("xmlbList", dmwhService.getXmlbListAll(ystwhForm.getYstlbdm()));
		if(null!=ystwhForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(ystwhMap));
		}
		request.setAttribute("ystwhMap", ystwhMap);
		initParam(request, user);
		return mapping.findForward("editYstwh");
	}
	/**
	 * 
	 * @����:�鿴������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-1-4 ����01:55:20
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
	@SystemLog(description = "���������Ź���-������ά��-�鿴-ystid:{ystid}")
	public ActionForward viewYstwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstwhForm myForm = (YstwhForm) form;
		YstwhForm model = service.getModel(myForm);
		HashMap<String,String> YstwhMap = service.getYstwh(model);
		request.setAttribute("rs", StringUtils.formatData(YstwhMap));
		request.setAttribute("path", "stglYstwh.do?method=viewYstwh");
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xstgl");
		String splcname = "";
		for (HashMap<String, String> hashMap : shlc) {
			if(hashMap.get("splc").equalsIgnoreCase(model.getSplc())){
				splcname = hashMap.get("lcxx");
			}
		}
		request.setAttribute("splcname", splcname);
		return mapping.findForward("viewYstwh");
	}
	
	/**
	 * 
	 * @����:�����ű���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-1-4 ����05:28:12
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
	@SystemLog(description = "���������Ź���-������ά��-����-ystid:{ystid}")
	public ActionForward saveYstwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstwhForm myForm = (YstwhForm) form;
		myForm.setXn(Base.currXn);
		if (service.isHaveYstxx(myForm)) {
			String message = MessageUtil.getText(MessageKey.SZTZ_XMSB_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editYstwh(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-1-5 ����02:06:43
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
	@SystemLog(description = "���������Ź���-������ά��-ɾ��-ystid:{ystid}")
	public ActionForward delYstwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	 * @����:�����ŵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-1-4 ����10:28:18
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
		
		YstwhForm model = (YstwhForm) form;
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
	 * @����:��Ŀ����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2016-1-27 ����10:32:13
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
		YstwhForm myForm = (YstwhForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		YstwhForm model = service.getModel(myForm);
		request.setAttribute("xmmc",model.getYstxmmc());
		BeanUtils.copyProperties(myForm, model);
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// �Ƿ�list
		request.setAttribute("kgList", isnotList);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsz");
	}
	
	private void initParam(HttpServletRequest request,User user) throws Exception{
		List<HashMap<String, String>> bmList =dmwhService.getGkdwListAll();
		request.setAttribute("jtrxm", user.getRealName());
		request.setAttribute("jtr", user.getUserName());
		
		request.setAttribute("sqsj", GetTime.getTimeByFormat("yyyy-MM-dd"));
		request.setAttribute("bmList", bmList);
		request.setAttribute("xnList", Base.getXnndList());
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xstgl");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		request.setAttribute("zclist", stsqservice.getZclblist());
		request.setAttribute("ssbmlist", stsqservice.getBbdmlist());
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:��������Ϣ���ƹ���
	 * @���ߣ�xiaxia[���ţ�1206]
	 * @���ڣ�2015-1-27 ����10:30:43
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
	@SystemLog(description = "���������Ź���-������ά��-����-ystxmmc:{ystxmmc}")
	public ActionForward copyYstxx(ActionMapping map,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		YstwhForm model = (YstwhForm)form;
//		//��ȡ��������������Ϣ
		request.setAttribute("ystxmmc", service.getModel(model).getYstxmmc());
		request.setAttribute("currxn", Base.currXn);
		request.setAttribute("ystid", model.getYstid());
		request.setAttribute("xnList", Base.getXnndList());
		return map.findForward("copyYstxx");
	}
	
	/**
	 * 
	 * @����:���Ʊ���
	 * @���ߣ�xiaxia[���ţ�1206]
	 * @���ڣ�2016-1-15 ����10:40:17
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
	public ActionForward saveCopyYstxx(ActionMapping map,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		YstwhForm model = (YstwhForm)form;
		//�ж���ͬѧ��������ͬ���Ƶļ�¼��ֱ�ӷ��ش�����Ϣ
		if(service.isHaveYstxx(model)){
			String message = MessageUtil.getText(MessageKey.YSTGL_YSTGL_ST_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = false;
		YstwhForm ystForm = service.getModel(model);
		
		YstwhForm newstxm = new YstwhForm();
		if(ystForm != null){
			BeanUtils.copyProperties(newstxm, StringUtils.formatData(ystForm));
			String ystid = UniqID.getInstance().getUniqIDHash();
			newstxm.setYstid(ystid);
			newstxm.setSjly("0");
			newstxm.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
			newstxm.setXn(model.getXn());
			newstxm.setYstxmmc(model.getYstxmmc());
			newstxm.setSqkg("0");
			newstxm.setSqjssj(null);
			newstxm.setSqjssj(null);
			result=service.runInsert(newstxm);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	public ActionForward getXmlblist(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String ystlbdm = request.getParameter("ystlbdm");
		List<HashMap<String, String>> xmlblist = dmwhService.getXmlbListAll(ystlbdm);
		response.getWriter().print(JSONArray.fromObject(xmlblist));
		return null;
	}
	
}
