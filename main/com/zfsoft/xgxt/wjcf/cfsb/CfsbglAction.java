/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-22 ����11:32:01 
 */  
package com.zfsoft.xgxt.wjcf.cfsb;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.wjcf.cfjcsq.CfjcsqForm;
import com.zfsoft.xgxt.wjcf.cfjcsq.CfjcsqService;
import com.zfsoft.xgxt.wjcf.cfsh.CfshForm;
import com.zfsoft.xgxt.wjcf.cfsh.CfshService;
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
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhForm;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.Globals;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�ʹ��ֹ���ģ��
 * @�๦������: (Υ�ʹ����ϱ�����) 
 * @���ߣ�������[����:913]
 * @ʱ�䣺 2013-10-22 ����11:32:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfsbglAction extends SuperAction {


	BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	private final static String CFSBZDPZ="cfsbzdpz";
	public static final String SUBMIT = "submit";
	
	private static final String url = "wjcf_cfsbgl.do?method=cxCfsbList";
	
	/**
	 * 
	 * @����:TODO(��ѯ�����ϱ������б�)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-22 ����11:41:22
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
	public ActionForward cxCfsbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglForm model = (CfsbglForm) form;
		CfsbglService service = new CfsbglService();
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		String path = "wjcf_cfsbgl.do?method=cxCfsbList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cxCfsbList");
	}
	
	

	/**
	 * 
	 * @����:(�����ϱ�����)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-22 ����03:40:47
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
	public ActionForward cxCfsbAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglForm model = (CfsbglForm) form;
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			//����Υ�ʹ��֣�ȫ����¼��
			WjcfCfsbService wjcfCfsbService=new WjcfCfsbService();
			request.setAttribute("yscfqkList", wjcfCfsbService.getYscfqk(model.getXh()));
			
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ����ѧ��ѧ���б�
		FormModleCommon.setNdXnXqList(request);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		//get student detail
		//ѧ��������Ϣ
		String path = "wjcf_cfsbgl.do?method=cxCfsbAdd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());//Υ�ʹ������
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());//Υ�ʹ���ԭ��
		User user = getUser(request);
		request.setAttribute("sbbxm", user.getRealName());
		this.saveToken(request);
		return mapping.findForward("cxCfsbAdd");
	}
	
	/**
	 * 
	 * @����:(�����ϱ�����)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-22 ����03:40:47
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
	@SystemLog(description = "����Υ����-�����ϱ�����-�����ϱ�-����XH:{xh},XN:{xn},XQ:{xq},CFYYDM:{cfyydm},CFLBDM:{cflbdm},WJSJ:{wjsj}")
	public ActionForward cxCfsbSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		CfsbglForm model = (CfsbglForm) form;
		CfsbglService service = new CfsbglService();
		User user = getUser(request);
		//�����ϱ��ˡ�ѧ�ꡢѧ�ڡ��ϱ�ʱ��
		model.setSbb(user.getUserName());
		model.setSbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		
		//����ԭʼ����Ȩ�ޣ�����Ȩ��ʼ��ȡ���걨ʱ�����������Ӧ��Ȩ��
		String ffqx = new CflbdmwhService().getFwqxByLbdm(model.getCflbdm());
		model.setKzzd4(ffqx);
		
		String result = service.save(model);
		String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
				: "true".equals(result)?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:(�����ϱ��޸�)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-22 ����03:40:47
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
	public ActionForward cxCfsbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglForm myForm = (CfsbglForm) form;
		CfsbglService service = new CfsbglService();
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		
		CfsbglForm model=service.getModel(myForm);
		//��ѯѧ������
		if(StringUtils.isNotNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//����Υ�ʹ��֣�ȫ����¼��
			WjcfCfsbService wjcfCfsbService=new WjcfCfsbService();
			request.setAttribute("yscfqkList", wjcfCfsbService.getYscfqk(model.getXh()));
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		// ����ѧ��ѧ���б�
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());//Υ�ʹ������
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());//Υ�ʹ���ԭ��
		request.setAttribute("type", UPDATE);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("sbbxm", myForm.getSbbxm());
		return mapping.findForward("cxCfsbUpdate");
		
	}
	
	/**
	 * 
	 * @����:(�����ϱ��޸ı���)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-22 ����03:40:47
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
	@SystemLog(description = "����Υ����-�����ϱ�����-�����ϱ�-�޸�CFID:{cfid},XH:{xh},XN:{xn},XQ:{xq},CFYYDM:{cfyydm},CFLBDM:{cflbdm},WJSJ:{wjsj}")
	public ActionForward cxCfsbUpdateSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglForm myForm = (CfsbglForm) form;
		CfsbglService service = new CfsbglService();
		
		//����ԭʼ����Ȩ�ޣ�����Ȩ��ʼ��ȡ���걨ʱ�����������Ӧ��Ȩ��
		String ffqx = new CflbdmwhService().getFwqxByLbdm(myForm.getCflbdm());
		myForm.setKzzd4(ffqx);
		
		String result=service.updateSave(myForm);
		String messageKey = "";
		if(SUBMIT.equalsIgnoreCase(myForm.getType())){
			messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
					: "true".equals(result)?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
		}else{
				messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
					: "true".equals(result)?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		}
		
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	
	}
	
	/**
	 * 
	 * @����:(�����ϱ�ɾ��)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-22 ����03:40:47
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
	public ActionForward cxCfsbDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglService service = new CfsbglService();
		
		String values = request.getParameter("values");
		int num = service.runDelete(values.split(","));
		
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
				: MessageUtil.getText(MessageKey.WJCF_DEL_CFYSH);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * �鿴�����ϱ�
	 */
	@SystemAuth(url = url)
	public ActionForward cfsbView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglForm model = (CfsbglForm) form;
		CfsbglService service = new CfsbglService();
		CfsbglForm myForm=service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		//��ѯѧ������
		if(StringUtils.isNotNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", UPDATE);
		return mapping.findForward("cfsbView");
		
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����Υ����-�����ϱ�����-�����ϱ�-ɾ��VALUES:{values}")
	public ActionForward cfsbDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values=request.getParameter("values");
		String[] ids=values.split(",");
		CfsbglService service = new CfsbglService();
		int num = service.runDelete(ids);
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
				: MessageUtil.getText(MessageKey.WJCF_DEL_CFYSH);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ�cmj
	 * @���ڣ�2013-5-22 ����10:44:47
	 * @�޸ļ�¼:
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
		CfsbglForm myForm = (CfsbglForm) form;
		CfsbglService service = new CfsbglService();

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(myForm,
				user);// ��ѯ�����м�¼������ҳ

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @����:TODO�ύ�ϱ�)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-4 ����05:09:39
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
	public ActionForward submitCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsbglForm model = (CfsbglForm) form;
		CfsbglService service = new CfsbglService();
		String cfid = request.getParameter("values");
		String xh = request.getParameter("xh");
		model.setCfid(cfid);
		model.setXh(xh);
		CfsbglForm modelGet = service.getModel(cfid);
		
		// ���˻ؼ�¼����ȡ�������
		if(!Constants.YW_WTJ.equals(modelGet.getSbjg())){

			CflbdmwhForm myForm = new CflbdmwhForm();
			CflbdmwhService myservice = new CflbdmwhService();
			myForm.setCflbdm(modelGet.getCflbdm());
			myForm = myservice.getModel(myForm);
			model.setSplcid(myForm.getSpl());
		}else{
			model.setSplcid(modelGet.getSplcid());
		}
		boolean result = service.submitCfsb(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//YdsqService service = new YdsqService();
		CfsbglService service = new CfsbglService();
		String cfid = request.getParameter("values");
		String splcid = request.getParameter("splcid");
		//ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(cfid,splcid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			//����ҵ��״̬Ϊ'δ�ύ'
			//����ҵ��״̬Ϊ'δ�ύ'
			//����ҵ��״̬Ϊ'δ�ύ'
			//����ҵ��״̬Ϊ'δ�ύ'
			//����ҵ��״̬Ϊ'δ�ύ'
			//����ҵ��״̬Ϊ'δ�ύ'
			//����ҵ��״̬Ϊ'δ�ύ'
			//����ҵ��״̬Ϊ'δ�ύ'
			//����ҵ��״̬Ϊ'δ�ύ'
			//����ҵ��״̬Ϊ'δ�ύ'
			CfsbglForm model = new CfsbglForm();
			model.setCfid(cfid);
			model.setSplcid(splcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(cfid))>0){
				model.setSbjg(Constants.YW_YTH);
			}else{
				model.setSbjg(Constants.YW_WTJ);
			}
			service.updateCfsbModel(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:��֤�������ϱ��ͽ���⵱���Ƿ���� ����֤������ѧ�š�������𡢴���ʱ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-14 ����03:46:20
	 * @���ڣ�2014-5-14 ����03:46:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return true�����ڣ�false:������
	 * @throws Exception 
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward checkExistCfsbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CfsbglService service = new CfsbglService();
		CfsbglForm myForm = (CfsbglForm) form;
		// ȡ���Ƿ������֤
		boolean isExistCfsb = service.checkExistCfsbjg(myForm);
		response.getWriter().print(isExistCfsb);
		return null;
	}
	

	/**
	 * 
	 * @����:��֤�����ڽ�������Ƿ���� ����֤������ѧ�š�������𡢴���ʱ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-5-14 ����03:46:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return true�����ڣ�false:������
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward checkExistCfjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CfsbglService service = new CfsbglService();
		CfsbglForm myForm = (CfsbglForm) form;
		// ȡ���Ƿ������֤
		boolean isExistCfsb = service.checkExistCfjg(myForm);
		response.getWriter().print(isExistCfsb);
		return null;
	}
	/**
	 * 
	 * @����:��֤�����ĺ�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-29 ����06:54:51
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
	public ActionForward checkExistCfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CfsbglService service = new CfsbglService();
		CfsbglForm myForm = (CfsbglForm) form;
		// ȡ���Ƿ������֤
		boolean isExistCfwh = service.checkExistCfwh(myForm);
		response.getWriter().print(isExistCfwh);
		return null;
	}
	
	
	/**
	 * ��ӡWord�ǼǱ�
	 */
	public ActionForward getDjbWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CfsbglForm model = (CfsbglForm) form;
		String type = request.getParameter("type");
		String fwjg = request.getParameter("fwjg");
		File wordFile = getWord(model.getCfid(),fwjg, type);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * �Ǽ� ��ZIP
	 */
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		String type = request.getParameter("type");
		String fwjgArray =request.getParameter("fwjgArray");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			String[] fwjgs = fwjgArray == null ? new String[values.length] : fwjgArray.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getWord(values[i],fwjgs[i], type);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	//���ģ����������word�ļ�
	private File getWord(String id,String fwjg, String type) throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		CfsbglService service = new CfsbglService();
		HashMap<String, String> wjcfxx =  service.getDjbModel(id);
		
		if (wjcfxx != null){
			wjcfxx.put("cfjc_sqly", HtmlUtil.xmlZy(wjcfxx.get("cfjc_sqly")));
			wjcfxx.put("cfss_sqly", HtmlUtil.xmlZy(wjcfxx.get("cfss_sqly")));
			wjcfxx.put("wjssjg", HtmlUtil.xmlZy(wjcfxx.get("wjssjg")));
			wjcfxx.put("cfyj", HtmlUtil.xmlZy(wjcfxx.get("cfyj")));
			wjcfxx.put("cfwh", HtmlUtil.xmlZy(wjcfxx.get("cfwh")));
			String xh = wjcfxx.get("xh");
			String cfsj = wjcfxx.get("cfsj");
			String cfsjny = ""; // ���ĸ�ʽ����
			int cfsjNum = 0; // ���ֺ�ʱ�� ��������X����
			if(!StringUtil.isNull(cfsj)){
				cfsjny = DateTranCnDate.fomartDateToCn(cfsj,FomartDateType.day);
				Calendar calendar = Calendar.getInstance();
				int nowYear = calendar.get(Calendar.YEAR);
				int nowMonth = calendar.get(Calendar.MONTH);
				calendar.setTime(DateFormat.getDateInstance().parse(cfsj));
				nowYear = nowYear - calendar.get(Calendar.YEAR);
				nowMonth = nowMonth - calendar.get(Calendar.MONTH);
				int cfsjInt = nowYear * 12 + nowMonth;
				cfsjNum = cfsjInt < 0 ? 0 : cfsjInt;
			}
			wjcfxx.put("cfsjny", cfsjny);
			wjcfxx.put("wjsjny", DateTranCnDate.fomartDateToCn(wjcfxx.get("wjsj"),FomartDateType.day));
			wjcfxx.put("jcsjny", DateTranCnDate.fomartDateToCn(wjcfxx.get("jcsj"),FomartDateType.day));
			wjcfxx.put("cfsjNum", String.valueOf(cfsjNum));
			
			//������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			//ת��Ϊ�������ڸ�ʽ
			xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// ��������
			// ����ְ��
			DwwhService dwwhService = new DwwhService();
			data.put("zwmc", dwwhService.getZwForXh(xh));
			// ����
			WdgwsqService wdgwsqService = new WdgwsqService();
			HashMap<String, String> qsxx= wdgwsqService.getQsxx(xh);
			String qsbh=null;
			if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
				qsbh="";
			}else{
				qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
			}
			data.put("qsbh", qsbh);
			
			//����Υ�ʹ��֣�ȫ����¼��
			WjcfCfsbService wjcfCfsbService=new WjcfCfsbService();
			List<HashMap<String, String>> yscfqkList = wjcfCfsbService.getYscfqk(xh);
			HashMap<String, String> yscfqkMap = new HashMap<String, String>();
			if(yscfqkList.size() > 0){
				yscfqkMap = yscfqkList.get(0);
			}
			data.put("yscfqkMap", StringUtils.formatData(yscfqkMap));
			data.put("yscfqkList", yscfqkList);
			//����Υ�ʹ��֣�����������¼��
			List<HashMap<String, String>> yscfqkNotIdList = wjcfCfsbService.getYscfqkNotId(xh, id);
			data.put("yscfqkNotIdList", yscfqkNotIdList);
			if(yscfqkNotIdList.size() == 0){
				yscfqkNotIdList.add(new HashMap<String, String>());
			}
			//ͨ��������1-7��
			List<HashMap<String,String>> shyjtyList = new CommShlcImpl().getShyjListByYwid(id);
			for (int i = 0; i < shyjtyList.size(); i++) {
				data.put("shyjty"+i,shyjtyList.get(i).get("shyj"));
			}
			
			data.put("xsxx", xsjbxx);
			data.put("wjcfxx", wjcfxx);
			data.put("xxmc", Base.xxmc);
			String fileName = xh +"["+xsjbxx.get("xm")+"]" + "-";
			String mbmc = Base.xxdm + ".xml";
			// ��ͨ��ó��ʦѧԺ
			if("5002".equals(Base.xxdm)){
				if("wjcfsq".equals(type)){
					fileName += "ѧ�������걨��";
					mbmc = "wjcfsq_" + mbmc;
				}else if("wjcftzs".equals(type)){
					fileName += "ѧ��Υ�ʹ��־���֪ͨ��";
					mbmc = "wjcftzs_" + mbmc;
				}else if("cfjcsq".equals(type)){
					fileName += "ѧ���������������";
					mbmc = "cfjcsq_" + mbmc;
				}else if("cfjctzs".equals(type)){
					fileName += "ѧ���������־���֪ͨ��";
					mbmc = "cfjctzs_" + mbmc;
				}
			}
			// ������ũ��ְҵѧԺ
			if("12727".equals(Base.xxdm)){
				String cfsj1 = wjcfxx.get("cfsj");
				String cfqx = wjcfxx.get("sfszcfqx")!=null&&wjcfxx.get("sfszcfqx").equals("1")?wjcfxx.get("cfqx"):null;
				if (cfsj1!=null) {
					if (cfsj1.length()==10) {
						data.put("cfn", cfsj1.substring(0,4));
						data.put("cfy", cfsj1.substring(5,7));
						data.put("cfr", cfsj1.substring(8,10));
						if (cfqx!=null) {
							String[] array=cfqx.split("-");
							int ts=0 ;
							if (Integer.parseInt(array[0])!=0)
								ts+=Integer.parseInt(array[0])*365;
							if (Integer.parseInt(array[1])!=0)
								ts+=Integer.parseInt(array[1])*30;
							if (Integer.parseInt(array[2])!=0) 
								ts+=Integer.parseInt(array[2]);
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // ���ڸ�ʽ
							Date date = dateFormat.parse(cfsj1); // ��������
						    Calendar cl = Calendar.getInstance();
						    cl.setTime(date);
						    cl.add(Calendar.DATE,ts);
						    String newdate = dateFormat.format(cl.getTime());
							data.put("jsn", newdate.substring(0,4));
							data.put("jsy", newdate.substring(5,7));
							data.put("jsr", newdate.substring(8,10));
						}else{
							data.put("jsn", "  ");
							data.put("jsy", "  ");
							data.put("jsr", "  ");
						}
					}
				}
				if("wjcftzs".equals(type)){
					fileName += "ѧ��Υ�ʹ��־�����";
					mbmc = "wjcftzs_" + mbmc;
				}else if("wjcfqsd".equals(type)){
					fileName += "ѧ�����־�����ǩ�յ�";
					mbmc = "wjcfqsd_" + mbmc;
				}else if("wjcfsq".equals(type)){
					fileName += "ѧ��Υ�ʹ���������";
					String cflbmc = wjcfxx.get("cflbmc");
					if("��У�쿴".equals(cflbmc) || "����ѧ��".equals(cflbmc)){
						mbmc = "wjcfsq_lxckjys_" + mbmc;
					}else{
						mbmc = "wjcfsq_jgjyx_" + mbmc;
					}
				}else if("cfjcsq".equals(type)){
					fileName += "ѧ���������������";
					mbmc = "cfjcsq_" + mbmc;
				}
			}
			// ������Ͽҽҩ�ߵ�ר��ѧУ
			if("14008".equals(Base.xxdm)){
				if("cfsssq".equals(type)){
					fileName += "ѧ�����������";
					mbmc = "cfsssq_" + mbmc;
				}else if("cfjcsq".equals(type)){
					fileName += "ѧ�����ֳ��������";
					mbmc = "cfjcsq_" + mbmc;
				}
			}
			//���ݹ�ҵְҵ����ѧԺ
			if("12686".equals(Base.xxdm)){
				if("cfjgdy".equals(type)){
					if("�������".equals(fwjg)){
					fileName += "������־�����";
					mbmc = "jccfjds_" + mbmc;
					}else{
						fileName += "���־�����";
						mbmc = "cfjds_" + mbmc;
					}
				}
			}
			if(Globals.XXDM_CDTYXY.equals(Base.xxdm)){
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				String dyrq =  df.format(new java.util.Date());
				data.put("dyrq",DateTranCnDate.fomartDateToCn(dyrq,FomartDateType.day));
				if("wjcfsq".equals(type)){
					fileName += "ѧ�����ɴ���ǼǱ�";
					mbmc = "wjsbsq_" + mbmc;
				}else if("wjjcsq".equals(type)){
					StringBuilder sql = new StringBuilder();
					for(int i=0;i<yscfqkNotIdList.size();i++){
						String cjcfsj = yscfqkNotIdList.get(i).get("cfsj");
						sql.append(cjcfsj==null?"":cjcfsj+":");
						String cjcf = yscfqkNotIdList.get(i).get("cflbmc");
						sql.append(cjcf==null?"":cjcf+"; \n ");
						
					}
					data.put("cjwjqk", sql.toString());
					fileName += "ѧ�����(����)���ֵǼǱ�";
					mbmc = "wjjcsq_" + mbmc;
				}
				
			}
			//�Ĵ�ְҵ����ѧԺ
			if("12970".equals(Base.xxdm)){
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				String dyrq =  df.format(new java.util.Date());
				data.put("dyrq",DateTranCnDate.fomartDateToCn(dyrq,FomartDateType.day));
				if("wjcfsq".equals(type)){
					fileName += "ѧ�����ɴ���ǼǱ�";
					mbmc = "wjsbsq_" + mbmc;
				}
			}
			//��������
			if("14073".equals(Base.xxdm)) {
				//ת��Ϊ�������ڸ�ʽ
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.day));// ��������
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				xsjbxx.put("csny_num", csny.replaceAll("��", ".").replaceAll("��", ".").replaceAll("��", ""));// 1988.9.9
				if("wjcfsq".equals(type)){
					fileName += "ѧ��Υ�ʹ��ֵǼǱ�";
					mbmc = "wjcfdjb_" + mbmc;
				}else if("wjcfjds".equals(type)) {
					fileName += "ѧ��Υ�ʹ��־�����";
					mbmc = "wjcfjds_" + mbmc;			
				}
			}
			
			//�ൺ�Ƶ����ְҵ����ѧԺ������2017��08�·������Ѿ����ô��ж��ˣ��ж����µ�onclick�����2017��08��21��_��������
			if("13011".equals(Base.xxdm)){
				if("13011".equals(type)){
					fileName += "���־�����";
					mbmc = "cfjds_" + mbmc;
					String cfwh = wjcfxx.get("cfwh");
					if(cfwh.matches("^[0-9]{8}$")){
						wjcfxx.put("cfwh","��"+cfwh.substring(0,4)+"��"+cfwh.substring(4,8));
					}
				}
			}
			
			if("13871".equals(Base.xxdm)){
				if("wjcfjds".equals(type)){
					fileName += "ѧ��Υ�ʹ��־�����";
					mbmc = "wjcfjds_" + mbmc;
				}
			}
			//���ݸߵ�ҽ��ר��ѧУ
			if("70002".equals(Base.xxdm)){
				if("wjcfsq".equals(type)){
					fileName += "Υ��ѧ������������";
					mbmc = "jsygzcfb_" + mbmc;
				}else if("cfjgdy".equals(type)){
					fileName += "�������������";
					mbmc = "jsygzcx_" + mbmc;
				}
				
				HashMap<String, String> bzrxx = xsxxService.getBzrxxByXh(xh);
				data.put("bzr", bzrxx.get("bzrxx"));
			}
			//����ְҵ����ѧԺ
			if("11773".equals(Base.xxdm)) {
				if("wjcfsq".equals(type)){
					fileName += "ѧ��Υ�͵��鴦���걨��";
					mbmc = "wjdcclsbb_" + mbmc;
				}
			}
			fileName = fileName + "-" + wjcfxx.get("wjsj");
			File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf", mbmc, fileName);
			
			return wordFile;
		}
		
		return null;
	}
	
	/**
	 * @����: �ൺ�Ƶ����ְҵ����ѧԺ,���־������ӡ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-8-17 ����03:27:22
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
	public ActionForward getCfjdsForQdjd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		CfsbglService service = new CfsbglService();
		/*��ȡ��ѡ��ID*/
		String value = request.getParameter("ids");
		/*��������*/
		String [] ids = null;
		if(StringUtils.isNotNull(value)){
			ids = value.split(",");
		}
		/*����ID��ȡ������Ϣ,�ٴ����ȡ�ļ��ķ���*/
		List<HashMap<String,String>> cfjdList = service.getCfjdXxByIds(ids);
		/*���ݴ��������м�¼�ϲ�����*/
		Map<String,List<HashMap<String,String>>> cfjdXxMap = service.getCfjdListMap(cfjdList);
		/*����word�ļ�����*/
		File[] files = service.getCfjdsFiles(cfjdXxMap);
		
		if(files.length>1){
			File zipFile = ZipUtil.zip(files);
			FileUtil.outputZip(response, zipFile);
		}else{
			FileUtil.outputWord(response, files[0]);
		}
		return null;
	}


	/**
	 * �ӱ�����ʦ��ѧԺ�����zip
	 */
	public ActionForward getCfjcZip(ActionMapping mapping, ActionForm form,
								   HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String value = request.getParameter("value");
		String xhs = request.getParameter("xhs");
		String jcids =request.getParameter("jcids");
		if (StringUtils.isNotNull(value)){
			String[] ids = value.split(",");
			String[] xh = xhs.split(",");
			String[] jcid = new String[ids.length];;
			if(jcids==null)
			{
				for (int i = 0; i <ids.length ; i++) {
					CfsbglService service = new CfsbglService();
					HashMap<String,String> jcidMap = service.getjcid(ids[i]);
					String jc = jcidMap.get("jcid");
					jcid[i]=jc;
				}

			}
			else{
			 jcid = jcids.split(",");
			}

			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = ids.length ; i < n ; i++){
				CfsbglForm model = (CfsbglForm) form;
				model.setCfid(ids[i]);
				model.setXh(xh[i]);
				File file = getcfjcWord(model,jcid[i]);
				files.add(file);
			}

			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}

		return null;
	}



	/**
	 *
	 * @����: ��ӡWord(�ӱ�����ʦ��ѧԺ)
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2018-7-18 ����02:22:14
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
	public ActionForward getCfjcWord(ActionMapping mapping, ActionForm form,
										HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jcid = request.getParameter("jcid");
		CfsbglForm model = (CfsbglForm) form;
		if(jcid==null)
		{
			CfsbglService service = new CfsbglService();
			HashMap<String,String> jcidMap = service.getjcid(model.getCfid());
			jcid = jcidMap.get("jcid");
		}
		File wordFile = getcfjcWord(model,jcid);
		FileUtil.outputWord(response, wordFile);
		return null;

	}

	private File getcfjcWord(CfsbglForm model,String jcid) throws Exception {
		CfshService cfshService=new CfshService();
		CfjcsqService cfjcsqservice=new CfjcsqService();
		Map<String, Object> data = new HashMap<String, Object>();
		String xh = model.getXh();
		if (!StringUtils.isNull(xh)) {
			// ������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			data.putAll(xsjbxx);

			//�༶����
			String bjdm = xsjbxx.get("bjdm");
			HashMap<String,String> bjrsMap = cfshService.getbjrs(bjdm);
			data.putAll(bjrsMap);
			//Υ����Ϣ
			CfshForm cfshForm = new CfshForm();
			cfshForm.setYwid(model.getCfid());
			HashMap<String, String> map=cfshService.getCfsbxxForjg(cfshForm);
			data.putAll(map);
			data.put("model",model);

			//�������
			CfjcsqForm cfjcsqForm = new CfjcsqForm();
			cfjcsqForm.setJcid(jcid);
			CfjcsqForm myForm=cfjcsqservice.getModel(cfjcsqForm);
			HashMap<String,String> sqlyMap = new HashMap<String, String>();
			sqlyMap.put("sqly",myForm.getSqly());
			data.putAll(sqlyMap);
			File wordFile = FreeMarkerUtil.getWordFile(data,
					"classpath://templates//wjcf", "jccfspb_10098.xml", xh + "-"
							+ xsjbxx.get("xm"));
			return wordFile;
		}

		return null;
	}
}
