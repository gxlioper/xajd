/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����04:48:28 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sq;

import java.io.File;
import java.net.URLEncoder;
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
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqDao;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;
import xsgzgl.szdw.general.dwwh.DwwhService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhForm;
import com.zfsoft.xgxt.rcsw.cdgl.cdxxwh.CdxxwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: ��������Action 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-4-23 ����04:48:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdsqAction extends SuperAction {

	/**
	 * @���� ��ȡ�����б�
	 */
	private DwwhService dwwhService = new DwwhService();
	
	/**
	 * @���� ѧ����ʾ��Ϣ������
	 */
	private BdpzService bdpzService = new BdpzService();
	
	/**
	 * @���� ѧ����Ϣ����
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 * @���ԣ� service �������������
	 */
	private CdsqService service = new CdsqService();
	
	/**
	 * @���ԣ� cdxxwhService ������Ϣά������
	 */
	public CdxxwhService cdxxwhService = new CdxxwhService();
	
	/**
	 * @���� ѧ���������б�
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 *  @���ԣ� PATH ·��
	 */
	public static final String PATH = "rcsw_cdgl_cdsqgl.do";
	
	public static final String url = "rcsw_cdgl_cdsqgl.do";
	
	/**
	 * @���ԣ� CDGL_BBID ������id
	 */
	private static final String CDGL_BBID = "rcswcdgl"; 
	
	/**
	 * 
	 * @���� �����췽��
	 */
	public CdsqAction(){
		super();
		jbxxList = bdpzService.getJbxxpz(CDGL_BBID);
	}
	
	/**
	 * 
	 * @����:ҳ��dispatcher ���������ѯҳ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:34:51
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
	public ActionForward cdsqCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cdsqCx");
	}
	/**
	 * 
	 * @����:������������鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-12-15 ����11:45:12
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
	@SystemAuth(url = "rcsw_cdgl_cdsqck.do")
	public ActionForward cdsqjlCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdsqForm model = (CdsqForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			//��ѯ
			List<HashMap<String, String>> resultList = service.getPageListOfSqjl(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//�����ڿؼ���ƥ�䵱ǰ����
		model.setSqsjdkssj(GetTime.getTimeByFormat("yyyy-MM-dd"));
		request.setAttribute("path", "rcsw_cdgl_cdsqck.do");
		request.setAttribute("rs", model);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cdsqjlCx");
	}
	
	/**
	 * 
	 * @����:���������б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:43:04
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
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdsqForm model = (CdsqForm) form;
		User user = getUser(request);
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//��ѯ
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:ҳ��dispatcher ������������ҳ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����10:13:15
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
	public ActionForward cdsqSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pathForXh = "rcsw_cdgl_cdsq.do?method=cdsqSq";
		String pathForCd = "rcsw_cdgl_cdsq.do?method=cdsqSq";
		CdsqForm model = (CdsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			model.setBmlbdm(xsjbxx.get("xydm"));//Ĭ������ѧ������ѧԺ
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			pathForCd = pathForCd + (StringUtil.isNull(model.getXh()) ? "" : ("&xh=" + model.getXh()));
		}
		HashMap<String , String> cdxx = new HashMap<String , String>();
		if(!StringUtil.isNull(model.getCdid())){
			cdxx = cdxxwhService.getCdxxByCdid(model.getCdid());
			pathForXh = pathForXh + (StringUtil.isNull(model.getCdid()) ? "" : ("&cdid=" + model.getCdid()));
		}
		request.setAttribute("cdxx", cdxx); //��ѯ������Ϣ
		// �����б�
		List<HashMap<String, String>> bmList = dwwhService.getBmList();
		request.setAttribute("path", URLEncoder.encode(pathForXh, "gbk"));
		request.setAttribute("pathForCd", URLEncoder.encode(pathForCd, "gbk"));
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("bmList", bmList);
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("cdsqSq");
	}

	/**
	 * 
	 * @����:�������泡������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����05:19:28
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
	@SystemLog(description="�����ճ�����-���ع���-����ʹ������-����XH:{xh},CDMC:{cdmc},SQSJDKSSJ:{sqsjdkssj},SQSJDJSSJ:{sqsjdjssj},CYRS:{cyrs}," +
			"FZRXM:{fzrxm},FZRLXFS:{fzrlxfs},SQLY:{sqly}")
	public ActionForward addCqsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdsqForm model = (CdsqForm) form;
		HashMap<String , Object> result = null;
		JSONObject json = null;
		
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			boolean checkConditions = service.checkSqSjd(model); //�����������
			
			if(!checkConditions){
				json = getJsonMessageByKey(MessageKey.RCSW_CDSQ_CHECK_ERROR);
				json.put("flag", "checkerror");
				response.getWriter().print(json);
				return null;
			}else{
				super.resetToken(request);
			}
		}

		result = service.saveCdsqNoSubmit(model);
		
		json = getJsonMessageByKey((Boolean)result.get("ISSUCCESS") ? 
				MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @����:�������沢�ύ��������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����05:19:28
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
	@SystemLog(description="�����ճ�����-���ع���-����ʹ������-�ύSQID:{sqid}")
	public ActionForward addSubmitCqsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdsqForm model = (CdsqForm) form;
		HashMap<String , Object> result = service.saveCdsqWithSubmit(model);
		JSONObject json = null;
		if(result.get("CODE") != null && StringUtils.equals((String) result.get("CODE"), "CHK_ERROR")){
			json = getJsonMessageByKey(MessageKey.RCSW_CDSQ_CHECK_ERROR);
			json.put("flag", "checkerror");
		}else{
			json = getJsonMessageByKey((Boolean)result.get("ISSUCCESS") ? 
					MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL);
		}
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @����:�ύ��������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����08:45:16
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
	@SystemLog(description="�����ճ�����-���ع���-����ʹ������-�ύSQID:{sqid}")
	public ActionForward submitCdsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdsqForm model  = (CdsqForm) form;
		
		CdsqForm modelGet = service.getModel(model.getSqid());
		
		// ���˻صļ�¼ȡ�ϵ��������ID;�����˻ؼ�¼����ȥȡ�������
		if(!Constants.YW_YTH.equals(modelGet.getShzt())){
			CdxxwhService service = new CdxxwhService();
			CdxxwhForm cdxx = new CdxxwhForm();
			cdxx.setCdid(modelGet.getCdid());
			cdxx = service.getModel(cdxx);
			if(cdxx!=null){
				model.setSplcid(cdxx.getSplcid());
				model.setQxfw(cdxx.getQxfw());
			}
			
		}else{
			model.setSplcid(modelGet.getSplcid());
			model.setQxfw(modelGet.getQxfw());
		}
		
		boolean isSuccess = service.submitCdsq(model);
		String message =isSuccess ? MessageUtil.getText(
				MessageKey.SYS_SUBMIT_SUCCESS) : MessageUtil
				.getText(MessageKey.RCSW_CDSQ_CHECK_ERROR);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @����:ɾ����������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����03:52:58
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
	@SystemLog(description="�����ճ�����-���ع���-����ʹ������-ɾ��SQIDS:{sqids}")
	public ActionForward deleteCdsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String sqids = request.getParameter("sqids"); //��ɾ����sqids
		
		int isSuccess = service.deleteCdsq(sqids.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @����:������������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����05:50:52
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
	@SystemLog(description="�����ճ�����-���ع���-����ʹ������-����SQID:{sqid}")
	public ActionForward cancelCdsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdsqForm model  = (CdsqForm) form;
		
		boolean isSuccess = service.cancelCdsq(model.getSqid()); //��������
		
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @����:�鿴��������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����09:11:20
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
	public ActionForward cdsqCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdsqForm model  = (CdsqForm) form;
		String sqid = model.getSqid(); //��ȡ����id
		HashMap<String , String> cdsqxx = service.getCdsqxx(sqid);//��ȡ����������Ϣ
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		String path = "rcsw_cdgl_cdsq.do?method=cdsqCk";
		request.setAttribute("cdsqxx", cdsqxx); 
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("cdsqCk");
	}
	
	/**
	 * 
	 * @����:ҳ��dispatcher ���������޸�ҳ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����10:13:15
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
	public ActionForward cdsqXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = "rcsw_cdgl_cdsq.do?method=cdsqXg";
		CdsqForm model = (CdsqForm) form;
		String sqid = model.getSqid(); //��ȡ����id
		if(StringUtils.isNotNull(sqid)){
			CdsqForm dataModel = service.getModel(sqid);
			StringUtils.formatData(dataModel);
			BeanUtils.copyProperties(model, dataModel);
		}

		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		if(!StringUtil.isNull(model.getCdid())){
			HashMap<String , String> cdxx = cdxxwhService.getCdxxByCdid(model.getCdid());
			request.setAttribute("cdxx", cdxx); //��ѯ������Ϣ
		}
		// �����б�
		List<HashMap<String, String>> bmList = dwwhService.getBmList();
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("bmList", bmList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("cdsqXg");
	}
	
	/**
	 * 
	 * @����:���³�������action
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-29 ����10:04:56
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
	@SystemLog(description="�����ճ�����-���ع���-����ʹ������-�޸�SQID:{sqid},XH:{xh},CDMC:{cdmc},SQSJDKSSJ:{sqsjdkssj},SQSJDJSSJ:{sqsjdjssj},CYRS:{cyrs}," +
			"FZRXM:{fzrxm},FZRLXFS:{fzrlxfs},SQLY:{sqly}")
	public ActionForward cdsqXgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CdsqForm model = (CdsqForm) form;
		model.setSqly(HtmlUtil.xmlZy(model.getSqly()));

		CdsqForm modelGet = service.getModel(model.getSqid());
		
		// ���˻صļ�¼ȡ�ϵ��������ID;�����˻ؼ�¼����ȥȡ�������
		if(!Constants.YW_YTH.equals(modelGet.getShzt())){

			CdxxwhService service = new CdxxwhService();
			CdxxwhForm cdxx = new CdxxwhForm();
			cdxx.setCdid(modelGet.getCdid());
			cdxx = service.getModel(cdxx);
			if(cdxx!=null){
				model.setSplcid(cdxx.getSplcid());
				model.setQxfw(cdxx.getQxfw());
			}
			
		}else{
			model.setCdid(modelGet.getCdid());
			model.setSplcid(modelGet.getSplcid());
			model.setQxfw(modelGet.getQxfw());
			//�����棬�����״̬��δ���˻�
			if(SAVE.equalsIgnoreCase(request.getParameter("type"))){
				model.setShzt(Constants.YW_YTH);
			}
		}
		
		boolean isSuccess = service.updateCdsqNoSubmit(model); //�������ݿ�
		
		String type = request.getParameter("type");
		JSONObject json = null;
		if(isSuccess && "submit".equals(type)){
			isSuccess = service.submitCdsq(model);
			if(isSuccess){
				json = getJsonMessageByKey(MessageKey.SYS_SUBMIT_SUCCESS);
			}else{
				json = getJsonMessageByKey(MessageKey.RCSW_CDSQ_CHECK_ERROR);
				json.put("flag", "checkerror");
			}
		}else{
			if(isSuccess){
				json = getJsonMessageByKey(MessageKey.SYS_SAVE_SUCCESS);
			}else{
				json = getJsonMessageByKey(MessageKey.RCSW_CDSQ_CHECK_ERROR);
				json.put("flag", "checkerror");
			}
		}
		response.getWriter().print(json);
		
		return null;
	}
	
	
	/**
	 * 
	 * @����: ��֤�Ƿ���ύ
	 * @���ߣ�qilm
	 * @���ڣ�2014-5-26
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward checkSfktj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CdsqForm model = (CdsqForm) form;
		String cdid = request.getParameter("cdid");
		model.setCdid(cdid);
		
		// ȡ���Ƿ������֤(�����춯���) true:���ύ/false�������ύ
		boolean isSfktj = service.checkSfktj(model);
		response.getWriter().print(isSfktj);
		return null;
	}
	
	/**
	 * 
	 * @���� �������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-17 ����04:19:00
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
	public ActionForward exportCdsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String sqid = request.getParameter("sqid");
		File wordFile = getWord(sqid,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getWord(String sqid,HttpServletRequest request) throws Exception{

		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		String flag = request.getParameter("flag");
		String tableName = flag.equals("sq") ? "XG_XLJK_CDGL_CDSQB" :"XG_XLJK_CDGL_CDSQJGB";
		//��������
		List<HashMap<String,String>> shyjList = cdxxwhService.getShyjList(sqid);
		
		HashMap<String,String> jgMap = cdxxwhService.getCdxx(sqid, tableName);
		data.putAll(jgMap);
		for (int i = 0; i < shyjList.size(); i++) {
			data.put("shyj"+i, shyjList.get(i).get("shyj"));
		}
		try{
			ResourceUtils.getFile(Constants.TEP_DIR+"rcsw/cdsqb.xml");
			file = FreeMarkerUtil.getWordFile(data,Constants.TEP_DIR+"rcsw","cdsqb.xml",FreeMarkerUtil.getFileName(jgMap.get("bmmc")+"���������"));
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return file;
	}
	
	/**
	 * 
	 * @���� �������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-17 ����04:19:00
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
	public ActionForward exportCdsqbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				File file = getWord(values[i],request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
}
