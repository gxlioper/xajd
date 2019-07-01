/**
 * @部门:学工产品事业部
 * @日期：2016-3-29 上午09:06:17 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.sq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbService;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgService;
import com.zfsoft.xgxt.xsztz.xntzjg.cssz.JcftzCsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：柳俊[工号:1282]
 * @时间： 2016-3-29 上午09:06:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcftzSqAction extends SuperAction<JcftzSqForm, JcftzSqService>{
	private static final String url = "sztz_jcftz_sq.do";
	private JcftzSqService service = new JcftzSqService();
	private JcftzCsszService csszService = new JcftzCsszService();
	private XmsbService xmsbService = new XmsbService();
	private XmsbjgService xmsbjgService = new XmsbjgService();
	
	/** 
	 * @描述:查询
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-29 上午09:14:48
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
	public ActionForward getJcftzSqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzSqForm model = (JcftzSqForm) form;
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
		String[] sqshkg = csszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg[0]);
		String path = "sztz_jcftz_sq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getJcftzSqList");
	}
	
	/** 
	 * @描述:认定
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-29 下午02:30:07
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
	public ActionForward renDing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzSqForm model = (JcftzSqForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			 User user = getUser(request);
			 // 查询
				List<HashMap<String, String>> resultList = service.getPageListForRenDing(
						model, user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
		}	   
		String path = "sztz_jcftz_sq.do";
		request.setAttribute("path", path);
		request.setAttribute("xmdm", model.getXmdm());
		request.setAttribute("rs", service.getXmxx(model.getXmdm()));
		JSONArray jxList = JSONArray.fromObject(xmsbService.getXmjxList(model.getXmdm()));
		request.setAttribute("jxList", jxList);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("renDing");
	}
	
	/** 
	 * @描述:保存
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-29 下午02:32:37
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
	public ActionForward saveJcftzSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzSqForm model = (JcftzSqForm) form;
		String csms = request.getParameter("csms");
		boolean result = true;
		if("1".equals(csms)){
			 result = service.saveJcftzSq(model);	
		}else if("2".equals(csms)){
			result = service.saveJcftzTtSq(model);
		}else{
			//如果参赛模式为空或者为其他值，直接返回提示失败信息
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
			
		if (!result){
			//如果失败，则提示
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		return null;
	}
	
	/** 
	 * @描述:提交
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-29 下午04:58:21
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
	public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzSqForm model = (JcftzSqForm) form;
		User user = getUser(request);
		//如没有输入调整后基础分，默认为原有基础分
		service.checkIsCanSubmit(model);
		String message = service.tj(model, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/** 
	 * @描述:查看
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-29 下午04:32:33
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
	public ActionForward viewRs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzSqForm model = (JcftzSqForm) form;
		request.setAttribute("xmdm", model.getXmdm());
		return mapping.findForward("viewRs");
	}
	
	
	/** 
	 * @描述:获取分数
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-30 下午04:19:07
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
	public ActionForward getFs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzSqForm model = (JcftzSqForm) form;
		String jxdm = request.getParameter("jxdm");
		String data = service.getFs(jxdm);
		response.getWriter().print(data);
		return null;
	}
	
	/**
	 * 
	 * @描述: 团体认定入口查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-4 上午10:36:42
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
	public ActionForward TtrenDing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzSqForm model = (JcftzSqForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			 User user = getUser(request);
			 // 查询
				List<HashMap<String, String>> resultList = service.getPageListForTtRenDing(
						model, user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
		}	   
		String path = "sztz_jcftz_sq.do";
		request.setAttribute("path", path);
		request.setAttribute("xmdm", model.getXmdm());
		request.setAttribute("rs", service.getXmxx(model.getXmdm()));
		JSONArray jxList = JSONArray.fromObject(xmsbService.getXmjxList(model.getXmdm()));
		request.setAttribute("jxList", jxList);
		FormModleCommon.commonRequestSet(request);
		if("view".equals(request.getParameter("flag"))){
			return mapping.findForward("viewTts");
		}else{
			return mapping.findForward("TtrenDing");
		}
		
	}
	
	
	
	
	
}
