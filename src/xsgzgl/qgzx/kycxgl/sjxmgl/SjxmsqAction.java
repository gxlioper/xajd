/**
 * @部门:学工产品事业部
 * @日期：2015-11-30 上午11:36:27 
 */
package  xsgzgl.qgzx.kycxgl.sjxmgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
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
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;


/**
 * @系统名称: 工作管理系统
 * @模块名称: 实践项目管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-11-30 上午11:36:27
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class SjxmsqAction extends SuperAction<SjxmglForm, SjxmglService> {
	private SjxmglService service = new SjxmglService();
	
	private static final String url = "qgzx_kycx_sjgl_sjxmsq.do";
	
	
	
	@SystemAuth(url = url)
	public ActionForward getSjxmsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmglForm model = (SjxmglForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getSjxmsqList");
	}

	/**
	 * 
	 * @描述:项目维护
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-3 上午08:57:08
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问勤工助学-实践项目申请-项目申请-xmid:{xmid}")
	public ActionForward xmwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmglForm myForm = (SjxmglForm) form;
		SjxmglForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		HashMap<String,String> sjxmMap = service.getSjxmgl(myForm.getXmid(),"");
		//获取成员列表
		List<HashMap<String, String>> cyList = service.getCyList(sjxmMap.get("xmid"));
		//获取老师列表
		List<HashMap<String, String>> zdlsList = service.getTeaList(sjxmMap.get("xmid"));
		request.setAttribute("cyList", cyList);
		request.setAttribute("zdlsList", zdlsList);
		List<HashMap<String, String>> xmnztList = new OptionUtil().getOptions(OptionUtil.XMNZT);
		request.setAttribute("xmnztList", xmnztList);
		request.setAttribute("rs", StringUtils.formatData(sjxmMap));
		this.saveToken(request);
		return mapping.findForward("xmwh");
	}
	/**
	 * 
	 * @描述:成员维护
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-3 上午09:34:16
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问勤工助学-实践项目申请-成员维护-xmid:{xmid}")
	public ActionForward cywh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmglForm myForm = (SjxmglForm) form;
		SjxmglForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		HashMap<String,String> sjxmMap = service.getSjxmgl(myForm.getXmid(),"");
		//获取成员列表
		List<HashMap<String, String>> cyList = service.getCyList(sjxmMap.get("xmid"));
		//获取老师列表
		List<HashMap<String, String>> zdlsList = service.getTeaList(sjxmMap.get("xmid"));
		request.setAttribute("cyList", cyList);
		request.setAttribute("zdlsList", zdlsList);
		List<HashMap<String, String>> xmnztList = new OptionUtil().getOptions(OptionUtil.XMNZT);
		request.setAttribute("xmnztList", xmnztList);
		request.setAttribute("rs", StringUtils.formatData(sjxmMap));
		return mapping.findForward("cywh");
	}
	/**
	 * 
	 * @描述:提交
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-3 上午08:56:57
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问勤工助学-实践项目申请-项目申请-提交:{xmid}")
	public ActionForward submitXmwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmglForm myForm = (SjxmglForm) form;
		User user = getUser(request);
		String values = request.getParameter("values");
		myForm.setXmid(values);
		boolean result = service.submitXmwh(myForm,user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-3 上午09:13:22
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问勤工助学-实践项目申请-项目申请-撤销:{xmid}")
	public ActionForward cancelXmwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			SjxmglForm model = new SjxmglForm();
			model.setXmid(sqid);
			model.setSplcid(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:项目维护申请保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-3 上午08:56:31
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
	@SystemLog(description = "访问勤工助学-实践项目申请-项目申请-保存xmid:{xmid}")
	public ActionForward saveXmwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		SjxmglForm myForm = (SjxmglForm) form;
		User user =getUser(request);
		String xsxxStr = request.getParameter("xsxxStr");
		String jsxxStr = request.getParameter("jsxxStr");
		List<SjxmglXsxxForm> xsxxList = JsonUtil.jsonArrToList(xsxxStr,SjxmglXsxxForm.class);
		List<SjxmglJsxxForm> jsxxList = JsonUtil.jsonArrToList(jsxxStr,SjxmglJsxxForm.class);
		boolean result = service.editXmwh(myForm,user,xsxxList,jsxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:成员维护保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-12-3 上午09:47:50
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
	@SystemLog(description = "访问勤工助学-实践项目申请-成员维护-保存xmid:{xmid}")
	public ActionForward saveCywh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SjxmglForm myForm = (SjxmglForm) form;
		String xsxxStr = request.getParameter("xsxxStr");
		String jsxxStr = request.getParameter("jsxxStr");
		List<SjxmglXsxxForm> xsxxList = JsonUtil.jsonArrToList(xsxxStr,SjxmglXsxxForm.class);
		List<SjxmglJsxxForm> jsxxList = JsonUtil.jsonArrToList(jsxxStr,SjxmglJsxxForm.class);
		
		boolean result = service.editCywh(myForm,xsxxList,jsxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SjxmglForm myForm = (SjxmglForm) form;
		String xhs= request.getParameter("xhs");
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			String xmid = request.getParameter("xmid");
			myForm.setXmid(xmid);
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user,xhs);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qgzx_kycxkyxmgl.do?method=getStu";
		request.setAttribute("path", path);
		request.setAttribute("xhs", xhs);
		request.setAttribute("xmid", myForm.getXmid());
		return mapping.findForward("getStu");
	}
	@SystemAuth(url = url)
	public ActionForward getTea(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		SjxmglForm myForm = (SjxmglForm) form;
		String zghs= request.getParameter("zghs");
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			String xmid = request.getParameter("xmid");
			myForm.setXmid(xmid);
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getTeaList(myForm,zghs);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qgzx_kycxkyxmgl.do?method=getTea";
		request.setAttribute("zghs", zghs);
		request.setAttribute("xmid", myForm.getXmid());
		request.setAttribute("path", path);
		return mapping.findForward("getTea");
	}
	
	
	
	
}
