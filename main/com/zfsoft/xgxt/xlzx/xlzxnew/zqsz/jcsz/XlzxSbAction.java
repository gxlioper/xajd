package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;

public class XlzxSbAction extends SuperAction<XlzxSbJcszForm,XlzxSbService> {
	private XlzxSbService service = new XlzxSbService();
	/**
	 * @描述 审批流服务
	 */
	private XtwhShlcService shlcService = new XtwhShlcService();
	private final String url = "xg_xlzxnew_cssz.do";
	/**
	 *
	 * @描述: 参数设置查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-28 下午04:42:43
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
	public ActionForward getJcsszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XlzxSbJcszForm myForm = (XlzxSbJcszForm)form;
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getPageList(myForm, user);
		String ybsplc = "";
		String zbsplc = "";
		if(resultList != null && !resultList.isEmpty()){
			for (int i = 0; i < resultList.size(); i++) {
				if("zb".equals(resultList.get(i).get("lx"))){
					zbsplc = resultList.get(i).get("splc");
				}else{
					ybsplc = resultList.get(i).get("splc");
				}
			}
		}
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("xlzx"));
		request.setAttribute("ybsplc", ybsplc);
		request.setAttribute("zbsplc", zbsplc);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("csszcx");
	}
	
	/**
	 * 
	 * @描述: 保存参数设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-28 下午05:16:41
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
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String[] splcs = request.getParameterValues("splc");
		String[] lxs = request.getParameterValues("lx");
		boolean rs = service.saveJcsz(splcs,lxs);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
