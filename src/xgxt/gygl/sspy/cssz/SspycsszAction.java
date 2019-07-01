/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package xgxt.gygl.sspy.cssz;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;
/**
 * @className	�� SspycsszAction
 * @description	�� ����������Ŀ��������
 * @author 		��CP��1352��
 * @date		�� 2018-4-27 ����11:26:46
 * @version 	V1.0
 */
public class SspycsszAction extends SuperAction{
	private  static final String url ="sspy_cssz.do";
	SspycsszService service = new SspycsszService();
	/**
	 * @description	�� ��������ҳ��
	 * @author 		�� CP��1352��
	 * @date 		��2018-4-27 ����01:37:52
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		SspycsszForm myForm = (SspycsszForm) form;
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		//��������б�
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("gygl");
		request.setAttribute("shlcList", shlc);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		SspycsszForm model = service.getModel();
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		return mapping.findForward("cssz");
		
	}
	/**
	 * @description	�� �����������
	 * @author 		�� CP��1352��
	 * @date 		��2018-4-27 ����01:42:14
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SspycsszForm myForm = (SspycsszForm) form;
		boolean result = service.saveCssz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	
}