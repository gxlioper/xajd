/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-4 ����04:18:09 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.sq;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bbdmpz.utils.BbdmUtils;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.zdzm.comm.PrintService;
import com.zfsoft.xgxt.rcsw.zdzm.comm.ZdzmComm;
import com.zfsoft.xgxt.rcsw.zdzm.cssz.ZdzmCsszForm;
import com.zfsoft.xgxt.rcsw.zdzm.cssz.ZdzmCsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.Globals;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ģ��
 * @�๦������: 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-4 ����04:18:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzmSqAction extends SuperAction {
	
	/**
	 * @���� ѧ����Ϣ����
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 * @���� �ڶ�֤���������
	 */
	private ZdzmSqService service = new ZdzmSqService();
	
	/**
	 * @���� �ڶ�֤���������÷���
	 */
	private ZdzmCsszService csszService = new ZdzmCsszService();
	
	/**
	 * @���� ѧ����ʾ��Ϣ������
	 */
	private BdpzService bdpzService = new BdpzService();
	
	/**
	 * @���� ����������ӿ�
	 */
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @���� ѧ���������б�
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * @���� ����ʼ��ѧ����Ϣ�������б�
	 */
	public ZdzmSqAction() {
		super();
		jbxxList = bdpzService.getJbxxpz(ZdzmComm.ZDZM_BDID);
	}

	private static final String url = ZdzmComm.PATH_SQ;
	
	/**
	 * 
	 * @����:��ѯ�ڶ�֤�������б�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-4 ����04:44:38
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
	public ActionForward queryZdzmSqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		
		User user = getUser(request);
		
		if (QUERY.equalsIgnoreCase(model.getType())){
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
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		
		ZdzmCsszService csszService = new ZdzmCsszService();			
		ZdzmCsszForm csszModel = csszService.getCssz();
		request.setAttribute("csszModel", csszModel);
		
		request.setAttribute("path", ZdzmComm.PATH_SQ);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("queryZdzmSqList");
	}
	
	/**
	 * 
	 * @����:�����ڶ�֤��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-5 ����03:34:50
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
	public ActionForward addZdzmSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if(!StringUtil.isNull(model.getXh())){
			
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		
		String path = "rcsw_zdzm_sqgl.do?method=addZdzmSq";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		this.saveToken(request);
		return mapping.findForward("addZdzmSq");
	}
	/**
	 * 
	 * @����:������������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����09:09:36
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
	@SystemLog(description="�����ճ�����-�ڶ�֤������-�ڶ�֤������-����XH:{xh},SQLY:{sqly}")
	public ActionForward addZdzmSqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		}

		ZdzmSqForm model  = (ZdzmSqForm) form;
		JSONObject message = null; 
		ZdzmCsszForm csszModel = csszService.getCssz(); 
		/*********************������������λ������ע�⣡��������*****************************/
		/**����Ĵ����ڿɶ��Է���Ƚϲ***************************************************/
		/**ϣ�����ͬѧ��д����ʱ�����ܹ��Ѹ���������ģ�鵥����ȡ������************************/
		/**�ô����߼���������Ϊ����ά��ͬѧ�ṩ���㡣***************************************/
		/**������Ҫ��ӱ�Ҫע�ͣ�**********************************************************/
		/*********************������������λ������ע�⣡����������***************************/
		if(null == csszModel) /*--------------------------------------->>ע��1�������������δ�趨��ѧ���������룬����ʾ������Ϣ��*/
			message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_CSSZ_NOT_EXIST);
		else if(StringUtils.equals(csszModel.getKsqkg(), "0"))/*-------->>ע��2����������������趨��������Ϊ�ر�״̬��ѧ���������룬����ʾ������Ϣ��*/
			message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_CSSZ_SQKG_CLOSE);
		else if(StringUtils.equals(csszModel.getKsqkg() , "1")){
			String curDate = DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDayOfMonth();/*-->>ע��2.1����ȡ��ǰʱ��*/
			String ksqkssj = csszModel.getKsqkssj();/*-------->>ע��2.2����ȡ�������ÿ����뿪ʼʱ�䣡*/
			String ksqjssj = csszModel.getKsqjssj();/*-------->>ע��2.3����ȡ�������ÿ��������ʱ�䣡*/
			model.setSqsj(curDate);
			if((!StringUtils.isBlank(ksqkssj) && curDate.compareTo(ksqkssj) < 0 ) ||(!StringUtils.isBlank(ksqjssj)&&curDate.compareTo(ksqjssj) > 0))/*-------->>ע��2.4�������ǰʱ�䲻�ڲ�������ʱ�䷶Χ�ڣ�������ѧ�����룡*/
				message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_SJ_NOT_IN_SZ);
			else{
				super.resetToken(request);															 	/*-------->>ע��2.5����֮������*/
				if(StringUtils.equals(SUBMIT, model.getType())){				/*-------->>ע��2.5.1��������ύ������doSubmitAction*/
					message = doAddSubmitAction(model, csszModel.getSplid());
				}else if(StringUtils.equals(SAVE, model.getType())){			/*-------->>ע��2.5.2������Ǳ��棬����doSaveAction*/
					message = doSaveAction(model, csszModel.getSplid());
				}
			}
		}
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * @throws Exception 
	 * @���� �����ڶ�֤������������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private JSONObject doSaveAction(ZdzmSqForm model , String splid) throws Exception{
		boolean isSuccess = true;
		model.setShzt(Constants.YW_WTJ);
		model.setSplcid(splid);
		isSuccess = service.saveZdzmSq(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		return message;
	}
	
	/**
	 * @throws Exception 
	 * @���� �����ڶ�֤���ĸ��²���
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private JSONObject doUpdateAction(ZdzmSqForm model) throws Exception{
		boolean isSuccess = service.updateZdzmSq(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		return message;
	}
	
	/**
	 * @throws Exception 
	 * @���� �����ڶ�֤���������ύ����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private JSONObject doAddSubmitAction(ZdzmSqForm model , String splid) throws Exception{
		boolean isSuccess = false;
		JSONObject message = null;
		List inShlSq = service.getZdzmInShlcByXh(model.getXh());
		if(null != inShlSq && inShlSq.size() >= 1){
			message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_ALREADY_IN_SHL);
		}else{
			String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			model.setShzt(Constants.YW_WTJ);
			model.setSplcid(splid);
			model.setZdzmsqid(uuid);
			isSuccess = service.saveZdzmSq(model);
			if(isSuccess){
				isSuccess = shlc.runSubmit(uuid, splid, model.getXh(), ZdzmComm.PATH_SH, ZdzmComm.PATH_SQ);
				if(isSuccess){
					model.setShzt(Constants.YW_SHZ);
					service.updateZdzmSq(model);	//�޸�Ϊ�����״̬
					String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					message = getJsonMessageByKey(messageKey);
				}
			}
		}
		return message;
	}
	
	/**
	 * @throws Exception 
	 * @���� �����ڶ�֤���ĸ����ύ����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private JSONObject doUpdateSubmitAction(ZdzmSqForm model ) throws Exception{
		boolean isSuccess = false;
		JSONObject message = null;
		List inShlSq = service.getZdzmInShlcByXh(model.getXh());
		if(null != inShlSq && inShlSq.size() >= 1){
			message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_ALREADY_IN_SHL);
		}else{
			ZdzmCsszForm csszModel = csszService.getCssz(); 
			if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt()))
				model.setSplcid(csszModel.getSplid());
			isSuccess = service.updateZdzmSq(model);
			if(isSuccess){
				isSuccess = shlc.runSubmit(model.getZdzmsqid(), model.getSplcid(), model.getXh(), ZdzmComm.PATH_SH, ZdzmComm.PATH_SQ);
				if(isSuccess){
					model.setShzt(Constants.YW_SHZ);
					service.updateZdzmSq(model);	//�޸�Ϊ�����״̬
					String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					message = getJsonMessageByKey(messageKey);
				}
			}
		}
		return message;
	}
	
	/**
	 * @throws Exception 
	 * @���� �����ڶ�֤���������ύ����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private JSONObject doSubmitAction(ZdzmSqForm model , String splid) throws Exception{
		boolean isSuccess = false;
		JSONObject message = null;
		List inShlSq = service.getZdzmInShlcByXh(model.getXh());
		if(null != inShlSq && inShlSq.size() >= 1){
			message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_ALREADY_IN_SHL);
		}else{
			isSuccess = service.updateZdzmSq(model);
			if(isSuccess){
				isSuccess = shlc.runSubmit(model.getZdzmsqid(), splid, model.getXh(), ZdzmComm.PATH_SH, ZdzmComm.PATH_SQ);
				if(isSuccess){
					model.setShzt(Constants.YW_SHZ);
					service.updateZdzmSq(model);	//�޸�Ϊ�����״̬
					String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					message = getJsonMessageByKey(messageKey);
				}
			}
		}
		return message;
	}
	
	/**
	 * 
	 * @����:�޸��ڶ�֤��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����02:18:47
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
	public ActionForward updateZdzmSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		if(!StringUtil.isNull(model.getZdzmsqid())){
			ZdzmSqForm dataModel = service.getModel(model.getZdzmsqid());
			BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(dataModel));
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		
		ZdzmCsszService csszService = new ZdzmCsszService();			
		ZdzmCsszForm csszModel = csszService.getCssz();
		request.setAttribute("csszModel", csszModel);
		
		String path = "rcsw_zdzm_sqgl.do?method=updateZdzmSq";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("updateZdzmSq");
	}
	
	/**
	 * 
	 * @����:�����ڶ� ����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����09:09:36
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
	@SystemLog(description="�����ճ�����-�ڶ�֤������-�ڶ�֤������-�޸�ZDZMSQID:{zdzmsqid},XH:{xh},SQLY:{sqly}")
	public ActionForward updateZdzmSqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		JSONObject message = null; 
		ZdzmCsszForm csszModel = csszService.getCssz(); 
		if(null == csszModel) /*--------------------------------------->>ע��1�������������δ�趨��ѧ���������룬����ʾ������Ϣ��*/
			message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_CSSZ_NOT_EXIST);
		else if(StringUtils.equals(csszModel.getKsqkg(), "0") && StringUtils.equals(SUBMIT, model.getType())&& !StringUtils.equals(Constants.YW_YTH, model.getShzt()))/*-------->>ע��2����������������趨��������Ϊ�ر�״̬��������״̬�������˻أ�ѧ���������룬����ʾ������Ϣ��*/
			message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_CSSZ_SQKG_CLOSE);
//		else if(StringUtils.equals(csszModel.getKsqkg() , "1")){
		else{
			//�����ǰʱ�䲻�ڲ�������ʱ�䷶Χ�ڣ�������ѧ�����룡
			if(!DateUtils.betweenTime(csszModel.getKsqkssj(), csszModel.getKsqjssj())){
				message = getJsonMessageByKey(MessageKey.RCSW_ZDZM_SJ_NOT_IN_SZ);
			}else{
				if(StringUtils.equals(SUBMIT, model.getType())){
					message = doUpdateSubmitAction(model);
				}else if(StringUtils.equals(SAVE, model.getType())){
					message =doUpdateAction(model);
				}
			}
		}
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����:ɾ���ڶ�֤������
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
	@SystemLog(description="�����ճ�����-�ڶ�֤������-�ڶ�֤������-ɾ��SQIDS:{sqids}")
	public ActionForward deleteZdzmSqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String sqids = request.getParameter("sqids"); //��ɾ����sqids
		
		int isSuccess = service.deleteZdzmSqs(sqids.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @����:�����ڶ�֤������
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
	@SystemLog(description="�����ճ�����-�ڶ�֤������-�ڶ�֤������-����ZDZMSQID:{zdzmsqid}")
	public ActionForward cancelZdzmSqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		
		boolean isSuccess = shlc.firstStepCancle(model.getZdzmsqid(), model.getSplcid());
		
		ZdzmSqForm dataModel = service.getModel(model.getZdzmsqid());

		if(isSuccess){
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(model.getZdzmsqid()))>0){
				dataModel.setShzt(Constants.YW_YTH);
			}else{
				dataModel.setShzt(Constants.YW_WTJ);
			}
			
			isSuccess = service.runUpdate(dataModel);
		}
		
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @����:�ύ�ڶ�֤������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����08:45:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request TODO
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ڶ�֤������-�ڶ�֤������-�ύZDZMSQID:{zdzmsqid}")
	public ActionForward submitZdzmSqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		String message = null; 
		ZdzmCsszForm csszModel = csszService.getCssz();
		String curDate = DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDayOfMonth();/*-->>ע��2.1����ȡ��ǰʱ��*/
		String ksqkssj = csszModel.getKsqkssj();/*-------->>ע��2.2����ȡ�������ÿ����뿪ʼʱ�䣡*/
		String ksqjssj = csszModel.getKsqjssj();/*-------->>ע��2.3����ȡ�������ÿ��������ʱ�䣡*/
		
		if(null == csszModel) /*--------------------------------------->>ע��1�������������δ�趨��ѧ���������룬����ʾ������Ϣ��*/
			message = MessageUtil.getText(MessageKey.RCSW_ZDZM_CSSZ_NOT_EXIST);
		else if(StringUtils.equals(csszModel.getKsqkg(), "0")&& !StringUtils.equals(Constants.YW_YTH, model.getShzt()))/*-------->>ע��2����������������趨��������Ϊ�ر�״̬��������״̬���˻أ�ѧ���������룬����ʾ������Ϣ��*/
			message = MessageUtil.getText(MessageKey.RCSW_ZDZM_CSSZ_SQKG_CLOSE);
		else if((curDate.compareTo(ksqkssj) < 0 || curDate.compareTo(ksqjssj) > 0) && !StringUtils.equals(Constants.YW_YTH, model.getShzt()))/*-------->>ע��3�������ǰʱ�䲻�ڲ�������ʱ�䷶Χ�ڣ�������ѧ�����룡*/
			message = MessageUtil.getText(MessageKey.RCSW_ZDZM_SJ_NOT_IN_SZ);
		else{
			if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt()))
				model.setSplcid(csszModel.getSplid());
			boolean isSuccess = shlc.runSubmit(model.getZdzmsqid(), model.getSplcid(), model.getXh(), "rcsw_zdzmsh.do", "rcsw_zdzmsq.do");
			if(isSuccess){
				ZdzmSqForm dataModel = service.getModel(model.getZdzmsqid());
				
				dataModel.setSplcid(model.getSplcid());
				dataModel.setShzt(Constants.YW_SHZ);
				isSuccess = service.runUpdate(dataModel);
			}
			 message =isSuccess ? MessageUtil.getText(
					MessageKey.SYS_SUBMIT_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_SUBMIT_FAIL);
		}
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @����:�鿴�ڶ�֤������
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
	public ActionForward viewZdzmSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		if(!StringUtil.isNull(model.getZdzmsqid())){
			ZdzmSqForm dataModel = service.getModel(model.getZdzmsqid());
			BeanUtils.copyProperties(model, dataModel);
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		
		String path = "rcsw_zdzm_sqgl.do?method=viewZdzmSq";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewZdzmSq");
	}
	
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[����:1036]
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
		ZdzmSqForm model  = (ZdzmSqForm) form;

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
		
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward print(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xhs = request.getParameter("xhs");
		String pyccmc = request.getParameter("pyccmc");
		HashMap<String,String> xzModel = new HashMap<String, String>();
		if(xhs != null){
			xzModel.put("ts", xhs.split(",").length + "");
			xzModel.put("pyccmc", pyccmc);
			xzModel.put("xhs", xhs);
			request.setAttribute("xzModel", xzModel);
			request.setAttribute("zdzmlbList", new PrintService().getXzlbList());
		}
		
		return mapping.findForward("print");
	}
	
	
	/**
	 * ���ر��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward doPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmSqForm model  = (ZdzmSqForm) form;
		String zdzmsqids = request.getParameter("xhs");
		if(zdzmsqids != null && zdzmsqids.split(",").length == 1){	/*-->���ص������*/
			HashMap<String , String> data = new PrintService().getData(zdzmsqids);
			if("11488".equals(Base.xxdm)){
				String xymc=data.get("xymc");
				data.put("xymc", xymc.replace("ѧԺ", ""));
			}
			HashMap<String , Object> objectData = new HashMap<String, Object>();
			objectData.putAll(data);
			File file = null;
			String guid = "rcsw_zdzm";
			if(Base.xxdm.equals(Globals.XXDM_ZYMZDX)){ /*-->���������ѧ���Ի�*/
				String pyccmc = data.get("pyccmc");
				if(StringUtils.equals("����",pyccmc)){
					if("0".equals(model.getType()))
						guid = "rcsw_zdzm_cn_10052"; 
					else if("1".equals(model.getType()))
						guid = "rcsw_zdzm_en_10052";
				}else if(StringUtils.equals("Ԥ����",pyccmc)){
					if("0".equals(model.getType()))
						guid = "rcsw_zdzm_ysb_cn_10052";
					else if("1".equals(model.getType()))
						guid = "rcsw_zdzm_ysb_en_10052";
				}else{
					if("0".equals(model.getType()))
						guid = "rcsw_zdzm_cn_10052";
					else if("1".equals(model.getType()))
						guid = "rcsw_zdzm_en_10052";
				}
			}else if("11488".equals(Base.xxdm)){
				guid="rcsw_zdzm_11488";
			}
			objectData.put("xxmc", Base.xxmc);
			file = BbdmUtils.getSigleFile(guid, objectData);
			FileUtil.outputWord(response, file);
		}else{/*-->���ض����� ZIP���*/
			List<File> files = new ArrayList<File>();
			for(String zdzmsqid:zdzmsqids.split(",")){
				HashMap<String , String> data = new PrintService().getData(zdzmsqid);
				if("11488".equals(Base.xxdm)){
					String xymc=data.get("xymc");
					data.put("xymc", xymc.replace("ѧԺ", ""));
				}
				HashMap<String , Object> objectData = new HashMap<String, Object>();
				objectData.putAll(data);
				File file=null;
				String guid = "rcsw_zdzm";
				if(Base.xxdm.equals(Globals.XXDM_ZYMZDX)){/*-->���������ѧ���Ի�*/
					String pyccmc = data.get("pyccmc");
					if(StringUtils.equals("����",pyccmc)){
						if("0".equals(model.getType()))
							guid = "rcsw_zdzm_cn_10052";
						else if("1".equals(model.getType()))
							guid = "rcsw_zdzm_en_10052";
					}else if(StringUtils.equals("Ԥ����",pyccmc)){
						if("0".equals(model.getType()))
							guid = "rcsw_zdzm_ysb_cn_10052";
						else if("1".equals(model.getType()))
							guid = "rcsw_zdzm_ysb_en_10052";
					}else{
						if("0".equals(model.getType()))
							guid = "rcsw_zdzm_cn_10052";
						else if("1".equals(model.getType()))
							guid = "rcsw_zdzm_en_10052";
					}
				}else if("11488".equals(Base.xxdm)){
					guid="rcsw_zdzm_11488";
				}
				objectData.put("xxmc", Base.xxmc);
				file = BbdmUtils.getSigleFile(guid, objectData);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}

}
