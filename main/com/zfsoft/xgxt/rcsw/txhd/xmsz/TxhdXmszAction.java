/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-23 ����03:23:51 
 */  
package com.zfsoft.xgxt.rcsw.txhd.xmsz;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import xgxt.utils.GetTime;
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
import com.zfsoft.xgxt.rcsw.txhd.dmwh.TxhdDmwhForm;
import com.zfsoft.xgxt.rcsw.txhd.dmwh.TxhdDmwhService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ����񡪡���ѧ�
 * @�๦������: ��ѧ�Action
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-6-23 ����03:23:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TxhdXmszAction extends SuperAction {
	
	private TxhdXmszService service = new TxhdXmszService();
	private String messageKey;
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	
	private static final String url = "rcsw_txhd_xmsz.do";
	
	/**
	 * 
	 * @����:������ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-23 ����04:44:10
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
	public ActionForward xmszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdXmszForm myForm = (TxhdXmszForm) form;
		
		if (QUERY.equals(myForm.getType())) {
			
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			List<HashMap<String, String>> resultList = service
					.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);

		String path = "rcsw_txhd_xmsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("xmszCx");
	}
	
	
	/**
	 * 
	 * @����:������Ŀ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-24 ����11:10:22
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
	@SystemLog(description="�����ճ�����-��ѧ�-��Ŀ����-����")
	public ActionForward xmszZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdXmszForm myForm = (TxhdXmszForm) form;
		TxhdDmwhService thxdDmwhservice = new TxhdDmwhService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);

			if (service.isExistByXmwh(myForm)) {// �����ظ���֤
				messageKey = MessageKey.RCSW_TXHD_XMMC_EXIST;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}

			boolean result = service.runInsert(myForm);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		TxhdDmwhService txhdDmwhService = new TxhdDmwhService();
		List<HashMap<String, String>> lbList = txhdDmwhService.getLblist();// ��Ŀ���
		request.setAttribute("lbList", lbList);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("hdgglist", thxdDmwhservice.getHdggList());
		return mapping.findForward("xmszZj");

	}
	
	
	
	/**
	 * 
	 * @����:�޸���Ŀ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-24 ����06:36:24
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
	@SystemLog(description="�����ճ�����-��ѧ�-��Ŀ����-�޸�XMDM:{xmdm}")
	public ActionForward xmszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdXmszForm myForm = (TxhdXmszForm) form;
		TxhdXmszForm myModel = service.getModel(myForm);
		TxhdDmwhService thxdDmwhservice = new TxhdDmwhService();
		if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			
			//�ж���û�޸���Ŀ������ƣ�û���������ж�
			if(!StringUtils.isBlank(myForm.getXmmc())&&!myForm.getXmmc().trim().equals(myModel.getXmmc().trim())&&service.isExistByXmwh(myForm)){// �����ظ���֤
				messageKey = MessageKey.RCSW_TXHD_XMMC_EXIST;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			
			//�ж��������������Ƿ��������������
			service.sqrssx(myForm);
				
			boolean result = service.runUpdate(myForm);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		if(myForm.getXmdm() != null && !myForm.getXmdm().equals("")){
			// �Ƿ���ѧ������
			boolean flag = service.isExistByXmsq(myForm.getXmdm());
			request.setAttribute("spzt", flag);
		}
				
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("rcsw");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		
		TxhdDmwhService txhdDmwhService = new TxhdDmwhService();
		List<HashMap<String, String>> lbList = txhdDmwhService.getLblist();// ��Ŀ���
		request.setAttribute("lbList", lbList);
		request.setAttribute("xxdm", Base.xxdm);
		BeanUtils.copyProperties(myForm, xgxt.utils.String.StringUtils.formatData(myModel));
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("hdgglist", thxdDmwhservice.getHdggList());
		return mapping.findForward("xmszXg");

	}
	
	
	@SystemAuth(url = url)
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXmszForm myForm = (TxhdXmszForm) form;
		TxhdXmszForm myModel = service.getModel(myForm);
		if(myModel.getHdggdm() != null){
			TxhdDmwhService dmwhservice = new TxhdDmwhService();
			String hdgg = dmwhservice.getHdggmc(myModel.getHdggdm());
			request.setAttribute("hdgg", hdgg);
		}else{
			request.setAttribute("hdgg", "");
		}
		String sqrssx = myModel.getSqrssx();
		String shrssx = myModel.getShrssx();
		if(null!=sqrssx && !StringUtils.isBlank(sqrssx)){
			myModel.setSqrssx(sqrssx+" ��");
		}
		if(null!=shrssx && !StringUtils.isBlank(shrssx)){
			myModel.setShrssx(shrssx+" ��");
		}
		request.setAttribute("data", xgxt.utils.String.StringUtils.formatData(myModel));
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("rcsw");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		TxhdDmwhService txhdDmwhService = new TxhdDmwhService();
		List<HashMap<String, String>> lbList = txhdDmwhService.getLblist();// ��Ŀ���
		String hdlbmc=null;
		for(HashMap<String, String> hm:lbList){
			if(myModel.getLbdm().equals(hm.get("lbdm"))){
				hdlbmc=hm.get("lbmc");
			}
		}
		request.setAttribute("hdlbmc", hdlbmc);
		BeanUtils.copyProperties(myForm, myModel);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("spzt", "true");
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("xmszCk");
	}
	/**
	 * 
	 * @����:ɾ����Ŀ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-24 ����07:32:37
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
	@SystemLog(description="�����ճ�����-��ѧ�-��Ŀ����-ɾ��VALUES:{values}")
	public ActionForward xmszSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			// //�жϺϷ��ԣ���������Ƿ�������////
			if (service.isExistByXmsq(values)) {// ������
				messageKey = MessageKey.RCSW_TXHD_XMMC_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_SUCCESS);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;

	}
	
	/**
	 * 
	 * @����:��Ŀ����ʱ�俪��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-24 ����07:52:14
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
	public ActionForward xmszSjkg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdXmszForm myForm = (TxhdXmszForm) form;
		String xmdm = request.getParameter("xmdm");
		String xmmc = service.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);

		if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		List<HashMap<String, String>> isnotList = new OptionUtil()
				.getOptions(OptionUtil.ISNOT);// �Ƿ�list
		request.setAttribute("kgList", isnotList);

		List<HashMap<String, String>> onoffList = new OptionUtil()
				.getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);
		
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("rcsw");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);

		FormModleCommon.commonRequestSet(request);

		TxhdXmszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, xgxt.utils.String.StringUtils.formatData(model));
		if(myForm.getXmdm() != null && !myForm.getXmdm().equals("")){
			// �Ƿ���ѧ������
			boolean flag = service.isExistByXmsq(myForm.getXmdm());
			request.setAttribute("spzt", flag);
		}
		return mapping.findForward("xmszSjkg");
	}
	
	
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-30 ����10:33:50
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
		
		TxhdXmszForm myForm = (TxhdXmszForm) form;

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
    * @����:��Ŀ����
    * @���ߣ�yxy[���ţ�1206]
    * @���ڣ�2015-9-18 ����11:41:13
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
	public ActionForward copeOfXmxx(ActionMapping map,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		TxhdXmszForm model = (TxhdXmszForm)form;
//		//��ȡ������������Ϣ
//		StjgForm stjg = service.getModel(model);
		request.setAttribute("xmmc", model.getXmmc());
		String currxq =  Base.currXq;
		request.setAttribute("currxn", Base.currXn);
		request.setAttribute("currxq", currxq);
		List<HashMap<String, String>> xqlist = Base.getXqList();
		String xqmc = "";
		for (HashMap<String, String> hashMap : xqlist) {
			if(hashMap.get("xqdm").equals(currxq)){
				xqmc = hashMap.get("xqmc");
				break;
			}
		}
		request.setAttribute("xmdm", model.getXmdm());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xqmc", xqmc);
		return map.findForward("copexmxx");
	}
	
	/**
	 * 
	 * @����:��Ŀ���Ʊ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-9-18 ����11:41:33
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
	public ActionForward saveCopeXmxx(ActionMapping map,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		TxhdXmszForm model = (TxhdXmszForm)form;
		//�ж���ͬѧ��ͬѧ��������ͬ���Ƶļ�¼��ֱ�ӷ��ش�����Ϣ
		TxhdXmszForm checkform = new TxhdXmszForm();
		checkform.setXmmc(model.getXmmc());
		checkform.setXn(model.getXn());
		checkform.setXq(model.getXq());
		if(service.isExistByXmwh(checkform)){
			messageKey = MessageKey.RCSW_TXHD_XMMC_EXIST;
			response.getWriter().print(getJsonResult(messageKey, false));
			return null;
		}
		TxhdXmszForm xmjg = null;
		boolean result = false;
		//��ȡ������������Ϣ
		if(model.getXmdm() != null){
			xmjg = service.getModel(model);
		}
		
		TxhdXmszForm newxm = new TxhdXmszForm();
		if(xmjg != null){
			BeanUtils.copyProperties(newxm, xgxt.utils.String.StringUtils.formatData(xmjg));
			String xmdm = UniqID.getInstance().getUniqIDHash();
			xmdm = xmdm.toUpperCase();
			newxm.setXmdm(xmdm);
			newxm.setSjly("0");
			newxm.setXn(model.getXn());
			newxm.setXq(model.getXq());
			newxm.setXmmc(model.getXmmc());
			result=service.runInsert(newxm);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
}
