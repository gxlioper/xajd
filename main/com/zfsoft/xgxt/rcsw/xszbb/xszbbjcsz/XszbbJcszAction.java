package com.zfsoft.xgxt.rcsw.xszbb.xszbbjcsz;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��֤����ģ��
 * @�๦������: TODO(ѧ��֤�����������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-16 ����02:47:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XszbbJcszAction extends SuperAction {
	
	private static final String url = "rcsw_xszbb_jcsz.do";
	
	/**
	 * 
	 * @����:TODO(ѧ��֤�����������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:19:40
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
	public ActionForward xszbbJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbbJcszForm myForm = (XszbbJcszForm) form;
		XszbbJcszService service = new XszbbJcszService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//�Ƿ�list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		//��������б�
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		String path = "rcsw_xszbb_jcsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		XszbbJcszForm model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		return mapping.findForward("xszbbJcsz");
	}

	
	
	/**
	 * 
	 * @����:TODO(�����������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:19:52
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
	@SystemLog(description="�����ճ�����-֤������-֤�������������-����SPLC:{splc}")
	public ActionForward saveXszbbJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbbJcszForm myForm = (XszbbJcszForm) form;
		XszbbJcszService service = new XszbbJcszService();
//		XszbbJcszForm oldForm=service.getModel();
//		if(oldForm != null && Constants.OPEN.equals(myForm.getSqkg()) && !myForm.getSplc().equals(oldForm.getSplc())){
//			XszbbsqService  xszbbsqService = new XszbbsqService();
//			//�ж��Ƿ��������������
//			boolean isUse=xszbbsqService.allowUpdateSetting();
//			if(!isUse){
//				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XSZBB_SHLC_EXIST));
//				return null;
//			}
//		}
		boolean result = service.saveJcsz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
