package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjcsz;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
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
 * @ģ������: ��ѧҽ�Ʊ���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2014-1-6 ����10:04:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YlbxJcszAction extends SuperAction {
	
	private static final String url = "rcsw_dxsylbx_jcsz.do";
	
	/**
	 * 
	 * @����:TODO(��ѧҽ�Ʊ��ջ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����10:04:06
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
	public ActionForward ylbxJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxJcszForm myForm = (YlbxJcszForm) form;
		YlbxJcszService service = new YlbxJcszService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//�Ƿ�list
		request.setAttribute("kgList", isnotList);
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		
		//��������б�
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		String path = "rcsw_dxsylbx_jcsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		YlbxJcszForm model = service.getModel(myForm);
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		return mapping.findForward("ylbxJcsz");
		
	}
	
	/**
	 * 
	 * @����:TODO(����ҽ�Ʊ��ջ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����10:32:19
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
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ�������������-����SPLC:{splc}")
	public ActionForward saveYlbxJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxJcszForm myForm = (YlbxJcszForm) form;
		YlbxJcszService service = new YlbxJcszService();
		//�������Ѽ�¼��������У�ȡ���ж�2014-5-8
//		YlbxJcszForm oldForm=service.getModel();
//		if(oldForm != null && Constants.OPEN.equals(myForm.getSqkg()) && !myForm.getSplc().equals(oldForm.getSplc())){
//			YlbxsqService  ylbxsqService = new YlbxsqService();
//			//�ж��Ƿ��������������
//			boolean isUse = ylbxsqService.allowUpdateSetting();
//			if(!isUse){
//				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_DXSYLBX_SHLC_EXIST));
//				return null;
//			}
//		}
		boolean result = service.saveJcsz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}

}
