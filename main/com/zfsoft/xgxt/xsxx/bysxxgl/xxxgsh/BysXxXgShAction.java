/**
 * @部门:学工产品事业部
 * @日期：2014-7-10 上午11:13:00 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DBEncrypt;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xsxx.bysxxgl.bysxx.BysXxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-7-10 上午11:13:00
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BysXxXgShAction extends SuperAction {
	
	private static final String url = "xsxx_new_bysxx_xxxgsh.do";
	
	/**
	 * 
	 * @描述:信息修改申请审核列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-10 上午11:28:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xgSqShList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxXgShService service = new BysXxXgShService();
		CommService cs = new CommService();
		BysXxXgShForm myForm = (BysXxXgShForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("xsxx_new_bysxx_xxxgsh.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_new_bysxx_xxxgsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xgSqShList");
	}

	/**
	 * 
	 * @描述:审核
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-10 下午02:58:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SuppressWarnings("unchecked")
	public ActionForward xgSqSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxXgShForm model = (BysXxXgShForm) form;
		BysXxService bysxxService = new BysXxService();
		HashMap xsxxMap = bysxxService.getBysXx(model.getXh());
		if (QUERY.equalsIgnoreCase(model.getType())) {
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		String zpsfcz = new XsxxtyglService().checkxszpSfcz(model.getXh());
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("rs", StringUtils.formatData(xsxxMap));
		xgxt.studentInfo.service.XsxxglService xsxxglService = new xgxt.studentInfo.service.XsxxglService();
		request.setAttribute("jtgxList", xsxxglService.getJtgxList());
		request.setAttribute("xh", model.getXh());
		request.setAttribute("gwid", request.getParameter("gwid"));
		request.setAttribute("ywid", request.getParameter("ywid"));
		request.setAttribute("lcid", request.getParameter("lcid"));
		request.setAttribute("shid", request.getParameter("shid"));
		this.saveToken(request);
		return mapping.findForward("xgSqSh");
	}

	/**
	 * 
	 * @描述:审核保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-28 上午11:01:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-毕业生信息管理-信息修改审核-审核GWID:{gwid}")
	public ActionForward xgSqShBc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		String gwid = request.getParameter("gwid");
		String ywid = request.getParameter("ywid");
		String xh = request.getParameter("xh");
		String lcid = request.getParameter("lcid");
		BysXxXgShForm model = (BysXxXgShForm) form;
		BysXxXgShService service = new BysXxXgShService();
		User user = getUser(request);
		model.setGwid(gwid);
		model.setSqid(ywid);
		model.setSqr(xh);
		model.setSplc(lcid);
		// 保存单个审核
		boolean result = service.saveSqSh(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:TODO批量审核
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-11 上午10:33:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgSqPlSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String dataJson = request.getParameter("params");
		DBEncrypt p = new DBEncrypt();
		String afterE = p.eCode(dataJson);
		request.setAttribute("dataJson", afterE);
		this.saveToken(request);
		return mapping.findForward("xgSqPlSh");
	}

	/**
	 * 
	 * @描述:批量审核保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-17 下午06:44:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-毕业生信息管理-信息修改审核-批量审核DATAJSON:{dataJson}")
	public ActionForward savePlSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		BysXxXgShForm model = (BysXxXgShForm) form;
		String params = request.getParameter("dataJson");
		DBEncrypt dbEncrypt = new DBEncrypt();
		String dataJson = dbEncrypt.dCode(params.getBytes());
		BysXxXgShService service = new BysXxXgShService();
		User user = getUser(request);
		boolean result = service.savePlXgSh(model, dataJson, user);
		String messageKey = result ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:TODO审核撤销退回
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-11 上午09:55:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-毕业生信息管理-信息修改审核-撤销SQID:{sqid}")
	public ActionForward xgShCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxXgShService service = new BysXxXgShService();

		String sqid = request.getParameter("sqid");
		boolean result = service.CxXgsq(sqid);
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		response.getWriter().print(JSONObject.fromObject(map));
		return null;

	}
}
