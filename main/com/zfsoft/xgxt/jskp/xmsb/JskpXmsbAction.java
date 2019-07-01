package com.zfsoft.xgxt.jskp.xmsb;
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

import xgxt.action.Base;
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
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.jskp.xmjg.XmjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 纪实考评
 * @类功能描述: 项目申报 
 * @作者： xiaxia[工号:1104]
 * @时间： 2017-7-5 下午04:46:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JskpXmsbAction extends SuperAction<JskpXmsbForm,JskpXmsbService>{
	private JskpXmsbService service = new JskpXmsbService();
	private final String XSXX="xsxxgl";
	private  XmjgService xmjgService = new XmjgService();
	private static final String url = "jskp_xmsb.do";
	private static final String JGZQ_FLG="1";
	
	/**
	 * 
	 * @描述:项目申报列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-5 下午04:48:23
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
	@SystemAuth(url = url)
	public ActionForward getXmsbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbForm model = (JskpXmsbForm) form;
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
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "jskp_xmsb.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsbList");
	}
	
	/**
	 * 
	 * @描述:项目申报
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-6 下午05:36:46
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
	@SystemLog(description = "纪实考评-项目申报-xmid:{xmid}")
	public ActionForward xmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbForm model = (JskpXmsbForm) form;
		HashMap<String,String> xmxxMap = xmjgService.getXmxx(model.getXmid());
		request.setAttribute("xmxx", xmxxMap);
		this.saveToken(request);
		return mapping.findForward("xmsb");
	}
	/**
	 * 
	 * @描述:验证用户是否可申报
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-24 下午02:52:21
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
	public ActionForward xmsbCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbForm model = (JskpXmsbForm) form;
		User user = getUser(request);
		String jgzqFlg = service.xmsbCheck(model,user);
		String message = jgzqFlg.equals(JGZQ_FLG) ? MessageUtil.getText(MessageKey.XG_JSKP_JGZQ_REPEAT)
				: null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		map.put("jgzqFlg", jgzqFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}

	/**
	 * 
	 * @描述:申报保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-7 上午09:28:37
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
	@SystemLog(description = "纪实考评-项目申报-申报保存-xmid:{xmid}")
	public ActionForward saveXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		JskpXmsbForm model = (JskpXmsbForm) form;
		User user = getUser(request);
		model.setXh(user.getUserName());
		boolean result = service.saveXmsb(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}

	/**
	 * 
	 * @描述:删除申报
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-27 上午08:41:44
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
	@SystemLog(description = "纪实考评-项目申报-申报删除-xmid:{xmid}")
	public ActionForward delXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以删除
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			result=service.runDelete(sqid.split(","))>0;
		}
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 申报查看
	 */
	public ActionForward viewXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbForm myForm = (JskpXmsbForm) form;
		JskpXmsbForm model = service.getModel(myForm);
		if(null!=model){
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XSXX);
		request.setAttribute("jbxxList", jbxxList);
		/*当参数设置为0时，审核列表学号查看（xhLink）*/
		String sfsh = new CsszService().getSfsh();
		request.setAttribute("sfsh", sfsh);
		HashMap<String,String> ckxxMap = service.getXxck(model.getSqid());
		if("0".equals(sfsh)){
			request.setAttribute("rs", ckxxMap);
		}else{
			request.setAttribute("rs", StringUtils.formatData(model));
		}
		
		return mapping.findForward("viewXmsb");
	}

}
