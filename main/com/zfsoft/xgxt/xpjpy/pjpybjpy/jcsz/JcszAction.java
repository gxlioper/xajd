package com.zfsoft.xgxt.xpjpy.pjpybjpy.jcsz;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;

/**
 * ��������
 */
public class JcszAction extends SuperAction {
	// �Ƿ�༶����.1���ǣ�0����
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xpjpy.sfbjpy"));
	
	private static final String url = "xpjpy_pjpybjpy_jcsz.do";
	
	/**
	 * ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward jcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		
		if(!SFBJPY_Y){
			String msg = "�༶����δ����������ϵ����Ա��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//�Ƿ�list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		String path = "xpjpy_pjpybjpy_jcsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		JcszForm model = service.getModel();
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		return mapping.findForward("jcsz");
	}
	
	/**
	 * �����������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-�����༶�������-��������-����")
	public ActionForward saveJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		boolean result = service.saveJcsz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
