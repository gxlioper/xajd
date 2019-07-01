/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-30 ����03:31:40 
 */
package com.zfsoft.xgxt.rcsw.jqlx;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ������У����
 * @���ߣ� 945
 * @ʱ�䣺 2013-12-30 ����03:31:40
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JqlxszAction extends SuperAction {
	
	private static final String url = "rcsw_jqlxsz.do";

	/**
	 * 
	 * @����:������У��������
	 * @���ߣ�945
	 * @���ڣ�2013-12-30 ����04:19:17
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
	public ActionForward jqlxsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxszModel myForm = (JqlxszModel) form;
		JqlxszService service = new JqlxszService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//�Ƿ�list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		//��������б�
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		String path = "rcsw_jqlxsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		JqlxszModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}else{
			model = new JqlxszModel();
		}
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("jqlxList", new OptionUtil().getOptions(OptionUtil.JQLX));
		request.setAttribute("jqlxV", model.getJqlx());
		return mapping.findForward("jqlxsz");
	}

	/**
	 * 
	 * @����:���������У����
	 * @���ߣ�945
	 * @���ڣ�2013-12-30 ����04:19:32
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
	@SystemLog(description="�����ճ�����-������У-������У����-����")
	public ActionForward saveJqlxsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxszModel myForm = (JqlxszModel) form;
		JqlxszService service = new JqlxszService();
		//2014-5-8 785 ��������ѱ����������̣�����Ҫ�ڽ��п��� 
//		JqlxszModel oldForm=service.getModel();
//		if(oldForm != null && Constants.OPEN.equals(myForm.getSqkg()) && !myForm.getSplc().equals(oldForm.getSplc())){
//			JqlxService  jqlxService = new JqlxService();
//			//�ж��Ƿ��������������
//			boolean isUse=jqlxService.allowUpdateSetting();
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
