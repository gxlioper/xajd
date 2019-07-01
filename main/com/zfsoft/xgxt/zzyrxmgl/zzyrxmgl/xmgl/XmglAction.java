package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.xmgl;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.fdgl.FdglService;

/** 
 * @功能描述：资助育人项目管理-项目管理
 * @author：Lu.Yao 【1271】
 * @date：2017-10-16 下午03:55:26 
 */
public class XmglAction extends SuperAction {
	
	private static final String url = "zzyrxmgl_xmgl.do";
	private XmglService service = new XmglService();

	/** 
	 * @description：项目管理首页
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午10:41:29 
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
	public ActionForward xmglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "zzyrxmgl_xmgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		if("1".equals(model.getFblx())){
			return mapping.findForward("xmglManage2");
		}else{
			return mapping.findForward("xmglManage");
		}
	}
	
	/** 
	 * @description：查看
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午10:41:50 
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
	public ActionForward viewXmgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		if (!StringUtil.isNull(user.getUserName())&&"stu".equalsIgnoreCase(user.getUserStatus())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
			request.setAttribute("jbxx", xsjbxx);
		}
		FdglService fservice = new FdglService();
		List<HashMap<String, String>> xyList = fservice.getKfxydmByid(model);
		request.setAttribute("xyList", xyList);
		HashMap<String,String> rs = service.getModelMap(model);
		request.setAttribute("rs", StringUtils.formatData(rs));
		if("1".equals(rs.get("fblx"))){
			return mapping.findForward("viewXmgl2");
		}else{
			return mapping.findForward("viewXmgl");			
		}
	}
	
	/** 
	 * @description：获取开放学院代码list
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午10:42:26 
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
	public ActionForward kfxydmCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		FdglService service = new FdglService();
		List<HashMap<String, String>> resultList = service.getKfxydmByid(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/** 
	 * @description：申请
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午10:42:37 
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
	public ActionForward sqXmgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		boolean flag = service.checkBmrs(model);//判断报名通过人数是否超过限定人数
		if(flag){
			boolean result = service.insertFdxx(model, user);
			String messageKey = result ? "申请成功！" : "申请失败！";
			response.getWriter().print(getJsonMessage(messageKey));
		}else{
			response.getWriter().print(getJsonMessage("辅导名额已被抢完，下次早点来噢！"));
		}
		return null;
	}
	
	/** 
	 * @description：取消申请
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午10:42:43 
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
	public ActionForward qxsqXmgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		boolean flag = service.checkBmshzt(model,user);//判断报名是否已通过，通过则不允许取消
		if(flag){
			boolean result = service.deleteFdxx(model, user);
			String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_AUD_CANCEL_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));			
		}else{
			response.getWriter().print(getJsonMessage("您的申请已被审核，无法撤销！"));
		}
		return null;
	}
	
	/** 
	 * @description：同意辅导
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午10:43:06 
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
	public ActionForward tyfdXmgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		boolean flag = service.checkFdrs(model);//保存时先验证是否有人已经抢先一步辅导该学生
		if(flag){
			boolean result = service.insertTyfdxx(model, user);
			String messageKey = result ? "同意辅导成功！" : "同意辅导失败！";
			response.getWriter().print(getJsonMessage(messageKey));			
		}else{
			response.getWriter().print(getJsonMessage("已有人同意辅导此学生，请换个学生重试！"));
		}
		return null;
	}
	
	/** 
	 * @description：取消辅导
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午10:43:16 
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
	public ActionForward qxfdXmgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		boolean flag = service.checkFdjlBfcx(model);//根据辅导记录判断能否撤销辅导
		if(flag){
			boolean result = service.deleteTyfdxx(model, user);
			String messageKey = result ? "取消成功！" : "取消失败！";
			response.getWriter().print(getJsonMessage(messageKey));
		}else{
			response.getWriter().print(getJsonMessage("已有辅导记录，无法撤销！"));
		}
		return null;
	}
}
