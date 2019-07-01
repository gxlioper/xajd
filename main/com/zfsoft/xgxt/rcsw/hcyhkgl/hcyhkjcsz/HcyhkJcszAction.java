package com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhkjcsz;


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
 * @ģ������: ���Żݿ��������ģ��
 * @�๦������: TODO(���Żݿ������������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-26 ����09:18:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 * 
 */
public class HcyhkJcszAction extends SuperAction {
	
	private static final String url = "rcsw_hcyhk_jcsz.do";
	
	/**
	 * 
	 * @����:TODO(���Żݿ������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:18:48
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
	public ActionForward hcyhkJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcyhkJcszForm myForm = (HcyhkJcszForm) form;
		HcyhkJcszService service = new HcyhkJcszService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//�Ƿ�list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		//��������б�
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		String path = "rcsw_hcyhk_jcsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		HcyhkJcszForm model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		return mapping.findForward("hcyhkJcsz");
		
	}

	
	
	/**
	 * 
	 * 
	 * @����:TODO( �����������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:19:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 * 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-���Żݿ�����-���Żݿ���������-����SPLC:{splc}")
	public ActionForward saveHcyhkJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcyhkJcszForm myForm = (HcyhkJcszForm) form;
		HcyhkJcszService service = new HcyhkJcszService();
		
		//�������Ѿ���¼��������У����ò�������
//		HcyhkJcszForm oldForm=service.getModel();
		
//		if(oldForm != null && Constants.OPEN.equals(myForm.getSqkg()) && !myForm.getSplc().equals(oldForm.getSplc())){
//			HcccqjtxService  hcccqjtxService = new HcccqjtxService();
//			//�ж��Ƿ��������������
//			boolean isUse=hcccqjtxService.allowUpdateSetting();
//			if(!isUse){
//				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_HCYHK_SHLC_EXIST));
//				return null;
//			}
//		}
		
		boolean result = service.saveJcsz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
}
