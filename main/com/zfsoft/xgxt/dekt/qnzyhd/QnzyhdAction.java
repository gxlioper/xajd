/**
 * @部门:学工产品事业部
 * @日期：2017-7-13 上午09:54:34 
 */  
package com.zfsoft.xgxt.dekt.qnzyhd;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 青年志愿活动action(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-7-13 上午09:54:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QnzyhdAction extends SuperAction<QnzyhdForm, QnzyhdService>{
	public static final String OVERSIZE = "oversize";
	public static final String ERRORTYPE = "errortype";
	public static final String YFB = "1";
	public static final String SHZ = "0";
	private QnzyhdService service = new QnzyhdService();
	
	private static final String url = "dekt_qnzyhd_hdlb.do";
	
	private static final String YfbUrl = "dekt_qnzyhd_yfbhd.do";
	
	private static final String YcjUrl = "dekt_qnzyhd_ycjhd.do";
	
	private static final String HDFBSHUrl = "dekt_qnzyhd_hdfbsh.do";
	
	/** 
	 * @描述:活动发布列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-17 下午01:40:42
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
	public ActionForward zyhdList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}		
		String path = "dekt_qnzyhd_hdlb.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zyhdList");
	}
	
	/** 
	 * @描述:增加(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-17 下午03:34:05
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
	public ActionForward addHdfb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<HashMap<String,String>> list = service.getFwlxList();
		request.setAttribute("fwlxList", list);
		request.setAttribute("xyList",Base.getXyNewList());
		return mapping.findForward("addHdfb");
	} 
	
	/** 
	 * @描述:柳俊(这里用一句话描述这个方法的作用)
	 * @作者：异步上传文件[工号：1282]
	 * @日期：2017-7-18 上午11:25:04
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
	public ActionForward upLoadPic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyhdForm model = (QnzyhdForm)form;
		String size = model.getMaxsize();
		String accept = model.getAccept();
		FileUtil.conversionFormFile(model.getFile());
		String contextPath = request.getSession().getServletContext().getRealPath("");
		String dir = (contextPath.substring(0,contextPath.lastIndexOf(File.separator))) + "\\pic";
		String lastPath = model.getLastPath();
		String str = service.upLoadFile(model.getFile(),lastPath,dir, size, accept);
		JSONObject j = new JSONObject();
		if(str.equals(OVERSIZE) || str.equals(ERRORTYPE)){
			j.put("result", "false");
			j.put("message", str);
		}else{
			j.put("result", "true");
			j.put("path", str);
			//上传海报，同时保存到数据库，实现实时更新（金华职业技术学院后续需求，要求活动结束后可以上传海报）
			if(null != model.getHdid()){
				model.setFjpath(str);
				service.updateFbShzt(model);
			}
		}
		response.getWriter().print(j);
		return null;
	}
	
	/** 
	 * @描述:增加保存(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-18 下午03:17:43
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
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyhdForm model = (QnzyhdForm)form;
		User user = getUser(request);
		String id = UniqID.getInstance().getUniqIDHash();
		model.setHdid(id);
		model.setHdfbr(user.getUserName());//设置发布人
		model.setFbzt(YFB);//设置发布状态
		model.setShzt(SHZ);//设置审核状态为审核中
		String content = DealString.toGBK(request.getParameter("editorid"));//设置活动详情
		model.setHdxq(content);//设置活动详情
		boolean result = service.runInsert(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
	
	/** 
	 * @描述:青年志愿人员报名(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-19 上午09:34:05
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
	public ActionForward qnhdrybm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm)form;
		HashMap<String,String> data = service.getQnzyhdInfo(model.getHdid());
		request.setAttribute("data", data);
		return mapping.findForward("qnhdrybm");
	}
	
	/** 
	 * @描述:已发布活动列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-20 下午02:37:29
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
	@SystemAuth(url = YfbUrl)
	public ActionForward yfbhdList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getYfbhdList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}		
		String path = "dekt_qnzyhd_yfbhd.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yfbhdList");
	}
	
	/** 
	 * @描述:修改活动发布(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-20 下午04:15:24
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
	@SystemAuth(url = YfbUrl,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateHdfb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		List<HashMap<String,String>> list = service.getFwlxList();
		request.setAttribute("fwlxList", list);
		HashMap<String,String> data = service.getQnzyhdInfo(model.getHdid());
		request.setAttribute("data", data);
		List<String> xydms = service.getGlXy(model);
		model.setXydms(xydms.toArray(new String[]{}));
		request.setAttribute("xyList",Base.getXyNewList());
		return mapping.findForward("updateHdfb");
	}
	
	/** 
	 * @描述:修改保存发布活动(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-20 下午04:47:38
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
	public ActionForward updateBcHdfb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		if(StringUtils.isNull(model.getType())){		
			String content = DealString.toGBK(request.getParameter("editorid"));//设置活动详情
			model.setHdxq(content);
		}
		if(StringUtils.isNotNull(model.getOldPath()) && StringUtils.isNotNull(model.getLastPath())){//删除原有的附件图片
			String contextPath = request.getSession().getServletContext().getRealPath("");
			String oldPath = (contextPath.substring(0,contextPath.lastIndexOf("\\"))) + model.getOldPath().replaceAll("/", "\\\\");
			model.setOldPath(oldPath);
		}
		//默认为提交状态
		model.setShzt(SHZ);
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:已参加活动列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-7-24 下午02:18:05
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
	@SystemAuth(url = YcjUrl)
	public ActionForward ycjhdList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getYcjhdList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}		
		String path = "dekt_qnzyhd_ycjhd.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ycjhdList");
	}
	
	/** 
	 * @描述:活动发布审核list(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-29 下午03:47:28
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
	@SystemAuth(url = HDFBSHUrl)
	public ActionForward hdfbshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getfbshList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}		
		String path = "dekt_qnzyhd_hdfbsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hdfbshList");
	}
	
	/** 
	 * @描述:申报活动单个审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-29 下午05:49:28
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
	public  ActionForward sbDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		HashMap<String,String> map = service.getQnzyhdInfo(model.getHdid());
		request.setAttribute("data", map);
		return mapping.findForward("sbDgsh");
	} 
	
	/** 
	 * @描述:保存单个审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-29 下午06:51:08
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
	public  ActionForward BcDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		boolean result = service.updateFbShzt(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
	
	/** 
	 * @描述:撤销发布审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-29 下午07:11:16
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
	public  ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		boolean result = service.cxFbsh(model.getIds());
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:申报批量审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-9-29 下午07:34:06
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
	public ActionForward sbPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		if(SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.plshFb(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("sbPlsh");
	}
	
	/** 
	 * @描述:变更发布状态(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-11 下午02:49:19
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
	public ActionForward bgFb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyhdForm model = (QnzyhdForm) form;
		boolean result = service.updateFbShzt(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	
}
