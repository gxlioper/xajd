/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午04:28:30 
 */  
package  xsgzgl.qgzx.kycxgl.cssz;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-9 下午04:28:30 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KyxmCsszAction extends SuperAction<KyxmCsszForm, KyxmCsszService> {
	private static final String KGZT_CLOSE = "0";
	private static final String KYCX_XMLB_KY = "kygl";
	private static final String KYCX_XMLB_SJ = "sjgl";
	
	
	/**
	 * 
	 * @描述:参数设置
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-11-30 下午04:40:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward cssz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmCsszForm myForm = (KyxmCsszForm) form;
		KyxmCsszService service = new KyxmCsszService();
		myForm.setXmlb(KYCX_XMLB_KY);
		KyxmCsszForm model = service.getModel(myForm);
		if (model != null) {
			BeanUtils.copyProperties(myForm, model);
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("qgzx");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		request.setAttribute("path", "qgzx_kycx_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	public ActionForward sjcssz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmCsszForm myForm = (KyxmCsszForm) form;
		KyxmCsszService service = new KyxmCsszService();
		myForm.setXmlb(KYCX_XMLB_SJ);
		KyxmCsszForm model = service.getModel(myForm);
		if (model != null) {
			BeanUtils.copyProperties(myForm, model);
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("qgzx");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		request.setAttribute("path", "qgzx_kycx_sjcssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sjcssz");
	}
	@SystemLog(description = "访问勤工助学-基础设置-保存-xmlb:{xmlb}")
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KyxmCsszForm model = (KyxmCsszForm) form;
		KyxmCsszService service = new KyxmCsszService();
		boolean result = false;
		service.deleteJcsz(model);
		result = service.runInsert(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
