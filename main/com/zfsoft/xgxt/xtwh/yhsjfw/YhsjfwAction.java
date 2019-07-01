/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xtwh.yhsjfw;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述: 项目类别维护
 * @作者： ligl
 * @时间： 2013-4-18 下午02:42:37
 * @版本： V1.0
 * @修改记录:
 */
public class YhsjfwAction extends SuperAction {
	private String messageKey;

	private static final String url = "xtwh_yhsjfw.do?method=yhsjfwCx";
	
	/**
	 * 
	 * @描述:普通查询方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward yhsjfwCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YhModel myForm = (YhModel) form;
		YhsjfwService service = new YhsjfwService();

		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service
					.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		List<HashMap<String, String>> yjbmList = service.getYjbmList();
		request.setAttribute("yjbmList", yjbmList);
		List<HashMap<String, String>> yhzForSzdwList = service
				.getYhzForSzdwList();
		request.setAttribute("yhzForSzdwList", yhzForSzdwList);
		String path = "xtwh_yhsjfw.do?method=yhsjfwCx";
		request.setAttribute("path", path);

		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("yhsjfwCx");
	}
	
	/**
	 * 
	 * @描述:用户数据范围授权
	 * @作者：ligl
	 * @日期：2014-2-26 下午02:37:18
	 * @修改记录: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward yhsjfwSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhModel myForm = (YhModel) form;
		YhsjfwService service = new YhsjfwService();
		String ids = request.getParameter("ids");
		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			List<HashMap<String, String>> njxyzyList = service.getNjxyzyList();
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("njxyzyList", njxyzyList);
			map.put("ids", ids);
			if(ids != null && ids.indexOf(",") == -1){
				HashMap<String, String> dataMap = service.getDataById(ids);
				map.putAll(dataMap);
			}
			JSONObject json = JSONObject.fromMap(map);
			response.getWriter().print(json);
			return null;
		}else if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String sjfwdmSubmit = request.getParameter("sjfwdmSubmit");
			String sjfwmcSubmit = request.getParameter("sjfwmcSubmit");
			YhsjfwModel yhsjfwModel = new YhsjfwModel();
			yhsjfwModel.setIds(ids);
			yhsjfwModel.setSjfwdm(sjfwdmSubmit);
			yhsjfwModel.setSjfwmc(sjfwmcSubmit);
			boolean result = service.runDeal(yhsjfwModel);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		request.setAttribute("ids", ids);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("yhsjfwSq");
	}


}
