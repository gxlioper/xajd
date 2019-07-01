/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.strtsq;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;

public class StrtsqAction extends SuperAction{

	private static final String url = "xg_ttgl_strtsq.do";
	StrtsqService service = new StrtsqService();
	
	/**
	 * @description	： 可申请的社团列表
	 * @author 		： CP（1352）
	 * @date 		：2018-2-6 下午04:06:11
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward stList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StrtsqForm model = (StrtsqForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//基础设置信息
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("stList");
	}
	
	/**
	 * @description	： 社团入团申请
	 * @author 		： CP（1352）
	 * @date 		：2018-2-7 上午08:56:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward stsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			StrtsqForm model = (StrtsqForm) form;
			User user = getUser(request);
			if ("stu".equals(user.getUserType())){
				model.setXh(user.getUserName());
			}
			if (SAVE.equalsIgnoreCase(model.getType())) {
				boolean isExist = service.isExist(model);//看是否已有申请
				if (!isExist) {
					model.setShzt("0");
					boolean result = service.saveRtsq(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
		    	}else {
		    		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
		    	}
				return null;
			}
		HashMap<String,String> stxxMap = service.getStxxInfo(model);
		request.setAttribute("rs", StringUtils.formatData(stxxMap));
		request.setAttribute("fzrxxInfo", service.getFzrxx(model));	
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("sqr", user.getRealName());
		request.setAttribute("sqsj", df.format(new Date()));
		String path = "ttgl_strtsq.do?method=stsq";
		request.setAttribute("path", path);
		return mapping.findForward("stsq");
	}
	
	
	/**
	 * @description	： 撤销申请
	 * @author 		： CP（1352）
	 * @date 		：2018-2-6 下午05:51:21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cancelSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			StrtsqForm model = (StrtsqForm) form;
			User user = getUser(request);
			if ("stu".equals(user.getUserType())){
				model.setXh(user.getUserName());
			}
			boolean result = service.cancelSq(model);
			String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));	
		return null;
	}
	
	/**
	 * @description	：查看
	 * @author 		： CP（1352）
	 * @date 		：2018-2-7 下午03:04:49
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StrtsqForm myForm = (StrtsqForm) form;
		HashMap<String,String> stxxMap = service.getStxxInfo(myForm);
		myForm.setSjly(stxxMap.get("sjly"));
		request.setAttribute("rs", StringUtils.formatData(stxxMap));
		request.setAttribute("fzrxxInfo", service.getFzrxx(myForm));	
		request.setAttribute("filepath", stxxMap.get("filepath"));
		return mapping.findForward("ck");
	}
	
}
