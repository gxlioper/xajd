/**
 * @部门:学工产品事业部
 * @日期：2015-7-13 上午11:36:27 
 */
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmjxForm;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbService;

/**
 * @系统名称: 工作管理系统
 * @模块名称: 项目申报管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-7-13 上午11:36:27
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XmsbjgAction extends SuperAction<XmsbjgForm, XmsbjgService> {
	private XmsbjgService service = new XmsbjgService();
	private XmsbService xmsbService = new XmsbService();
	
	private static final String url = "sztz_xmsbgl_xmwh.do";
	
	/**
	 * 
	 * @描述:查询项目申报结果列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-13 下午01:54:11
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
	public ActionForward getXmsbjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbjgForm model = (XmsbjgForm) form;
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
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_xmsbgl_xmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXmsbjgList");
	}
	
	@SystemAuth(url = url)
	public ActionForward xmsbJxbfList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbjgForm model = (XmsbjgForm) form;
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
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_jxbfgl_xnjxbf.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsbJxbfList");
	}
	
	@SystemAuth(url = url)
	public ActionForward xmcxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbjgForm model = (XmsbjgForm) form;
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
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_rzjggl_xmcx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmcxList");
	}
	/**
	public ActionForward xsxfList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbjgForm model = (XmsbjgForm) form;
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
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_rzjggl_xmcx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmcxList");
	}


	/**
	 * 
	 * @描述:项目申报结果增加
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-13 下午05:27:51
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
	public ActionForward addXmsbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbjgForm model = (XmsbjgForm) form;
		User user = getUser(request);
		// 学年 学期list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setSfsljx("0");
		String path = "xmsbXmsbjg.do?method=addXmsbjg";
		request.setAttribute("path", path);
		request.setAttribute("sbr", user.getUserName());
		xmsbService.initParam(request, user);
		return mapping.findForward("addXmsbjg");
	}
	/**
	 * 
	 * @描述:修改项目申报结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-13 下午01:55:20
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
	public ActionForward editXmsbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbjgForm myForm = (XmsbjgForm) form;
		User user = getUser(request);
		XmsbjgForm xmsbjgForm = service.getModel(myForm);
		if(null!=xmsbjgForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(xmsbjgForm));
			request.setAttribute("xmsbjgForm", StringUtils.formatData(xmsbjgForm));
		}
		
		// 加载项目奖项信息
		List<HashMap<String, String>> xmjxList = xmsbService.getXmjxList(xmsbjgForm.getXmdm());
		request.setAttribute("xmjxList", xmjxList);
		String path = "xmsbXmsbjg.do?method=editXmsbjg";
		request.setAttribute("path", path);
		request.setAttribute("csms", xmsbjgForm.getCsms());
		xmsbService.initParam(request, user);
		return mapping.findForward("editXmsbjg");
	}
	/**
	 * 
	 * @描述:查看项目申报结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-13 下午01:55:20
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
	public ActionForward viewXmsbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbjgForm myForm = (XmsbjgForm) form;
		HashMap<String,String> sbjgMap = service.getXmsbjg(myForm);
		request.setAttribute("rs", StringUtils.formatData(sbjgMap));
		// 加载项目奖项信息
		List<HashMap<String, String>> xmjxList = xmsbService.getXmjxList(sbjgMap.get("xmdm"));
		request.setAttribute("xmjxList", xmjxList);
		// 加载项目参与学院信息
		List<HashMap<String, String>> xyList = xmsbService.getCyxyListForView(sbjgMap.get("xmdm"));
		request.setAttribute("xyList", xyList);
		request.setAttribute("path", "xmsbXmsbjg.do?method=viewXmsbjg");
		return mapping.findForward("viewXmsbjg");
	}
	
	/**
	 * 
	 * @描述:项目申报结果保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-13 下午05:28:12
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
	@SystemLog(description = "访问素质拓展-项目申报管理-项目维护-增加或修改保存JGID:{jgid},XQ:{xn},XQ:{xq},XMMC:{xmmc},XMJBDM:{xmjbdm},SBBMDM:{sbbmdm},SSKMDM:{sskmdm},KCYRS:{kcyrs},XMKSSJ:{xmkssj},SBR:{sbr},LXDH:{lxdh},JCXF:{jcxf}")
	@SuppressWarnings("unchecked")
	public ActionForward saveXmsbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbjgForm myForm = (XmsbjgForm) form;
		String objStr = request.getParameter("objStr");
		String[] cyxyArr = request.getParameterValues("cyxy");
		User user =getUser(request);
		if (service.isHaveSbjg(myForm)) {
			String message = MessageUtil.getText(MessageKey.SZTZ_XMSB_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		List<XmjxForm> jxxxList = null;
		if (objStr!= null && !"".equals(objStr)){
			jxxxList = JsonUtil.jsonArrToList(objStr,XmjxForm.class);
		}
		boolean result = service.editXmsbjg(myForm,jxxxList,cyxyArr);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问素质拓展-项目申报管理-项目维护-删除VALUES:{values}")
	public ActionForward delXmsbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			if (service.isHaveSqJl(values)) {// 关联性
				String messageKey = MessageKey.SZTZ_XMSB_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			if(result){
				result = xmsbService.delPlXmjx(ids);
			}
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:项目申报结果导出
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-28 上午10:28:18
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmsbjgForm model = (XmsbjgForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// 查询出所有记录，不分页
		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	/**
	 * 
	 * @描述:项目设置
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-14 上午10:32:13
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
	public ActionForward xmsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbjgForm myForm = (XmsbjgForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String oldsplc = request.getParameter("oldsplc");
			if (!oldsplc.equals(myForm.getSplc())&&service.isHaveSqJl(myForm.getXmdm())) {// 关联性
				String messageKey = MessageKey.SZTZ_XMSB_SPLC_USED;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XmsbjgForm model = service.getModel(myForm);
		request.setAttribute("oldsplc", model.getSplc());
		request.setAttribute("xmmc", model.getXmmc());
		BeanUtils.copyProperties(myForm, model);
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xsztz");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// 是否list
		request.setAttribute("kgList", isnotList);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
		request.setAttribute("onoffList", onoffList);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsz");
	}
	
	public ActionForward xmrsKzfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (!StringUtil.isNull(value)) {
			XtwhShlcService xtwhShlc = new XtwhShlcService();
			List<HashMap<String, String>> spjbListByGnmk = xtwhShlc
					.getSpjbListByGnmk(value);// spgwid 为选择的审批流程代码值
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONArray.fromObject(spjbListByGnmk));
		}
		return null;

	}
	/**
	 * 
	 * @描述:项目热门设定
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-14 下午03:03:41
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
	public ActionForward setRmxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		String ids = request.getParameter("ids");
		if (!StringUtil.isNull(ids)) {
			String[] jgids = ids.split(",");
			boolean result = service.setRmxm(jgids,value);
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_SAVE_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_SAVE_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}
		return null;

	}
	/**
	 * 
	 * @描述:奖项颁发
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-16 下午04:10:31
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
	public ActionForward jxbf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbjgForm myForm = (XmsbjgForm) form;
		User user = getUser(request);
		HashMap<String,String> sbjgMap = service.getXmsbjg(myForm);
		request.setAttribute("rs", sbjgMap);
		String xh=request.getParameter("xh");
		// 加载项目奖项信息
		List<HashMap<String, String>> xmjxList = xmsbService.getXmjxList(sbjgMap.get("xmdm"));
		request.setAttribute("xmjxList", xmjxList);
		//获取申请项目的学生列表
		List<HashMap<String, String>> sqxsList = service.getSqxsList(sbjgMap.get("xmdm"),xh);
		request.setAttribute("sqxsList", sqxsList);
		request.setAttribute("sfqqList",new OptionUtil().getOptions(OptionUtil.ISNOT));
		return mapping.findForward("jxbf");
	}
	/**
	 * 
	 * @描述:获取学生
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-17 上午09:50:31
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
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		XmsbjgForm myForm = (XmsbjgForm) form;
		String xhArr= request.getParameter("xhArr");
		String jcxf= request.getParameter("jcxf");
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			String xmdm = request.getParameter("xmdm");
			myForm.setXmdm(xmdm);
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user,xhArr);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("xhArr", xhArr);
		request.setAttribute("xmdm", myForm.getXmdm());
		request.setAttribute("jcxf", jcxf);
		String path = "xmsbXmsbjg.do?method=getStu";
		request.setAttribute("path", path);
		return mapping.findForward("getStu");
	}
	/**
	 * 
	 * @描述:加载项目奖项
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-17 上午11:26:53
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
	public ActionForward getXmjxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm =request.getParameter("xmdm");
		List<HashMap<String,String>> sbflList = xmsbService.getXmjxList(xmdm);
		JSONArray dataList = JSONArray.fromObject(sbflList);
		response.getWriter().print(dataList);
		return null;
	}

	
	@SystemAuth(url = url)
	public ActionForward xmcxView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbjgForm myForm = (XmsbjgForm) form;
		User user = getUser(request);
		HashMap<String,String> sbjgMap = service.getXmsbjg(myForm);
		request.setAttribute("rs", sbjgMap);
		
		// 加载项目奖项信息
		List<HashMap<String, String>> xmjxList = xmsbService.getXmjxList(sbjgMap.get("xmdm"));
		request.setAttribute("xmjxList", xmjxList);
		List<HashMap<String, String>> sqxsList = null;
		//获取申请项目的学生列表
		if("1".equals(myForm.getCsms())){
			sqxsList = service.getSqxsList(sbjgMap.get("xmdm"),null);
		}else if("2".equals(myForm.getCsms())){
			sqxsList = service.getTtxmcyrs(sbjgMap.get("xmdm"));
		}else{
			sqxsList = new ArrayList<HashMap<String,String>>();
		}
		request.setAttribute("sqxsList", sqxsList);
		request.setAttribute("xsrs", sqxsList.size());
		//
		xmsbService.initParam(request, user);
		return mapping.findForward("xmcxView");
	}
	public ActionForward getCyxyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		List<HashMap<String, String>> resultList = service.getCyxyList(xmdm);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	public ActionForward getXylb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sbbmdm = request.getParameter("sbbmdm");
		String xylb = service.getXylb(sbbmdm);
		response.getWriter().print(getJsonMessage(xylb));
		return null;
	}
	
	public ActionForward sfxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xmdm = request.getParameter("xmdm");
		List<HashMap<String, String>> list = service.getXmForJcfrd(xmdm);
		if(list.size()>0){
			response.getWriter().print(false);
		}else{
			response.getWriter().print(true);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 项目阶段设置
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-21 上午11:37:19
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
	public ActionForward jdsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xmdm = request.getParameter("xmdm");
		String xmmc = request.getParameter("xmmc");
		List<HashMap<String, String>> xmjdszlist = service.getXmjdSzList(xmdm);
		request.setAttribute("xmjdsz", xmjdszlist);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("xmmc", xmmc);
		return mapping.findForward("jdsz");
	}
	
	/**
	 * 
	 * @描述:保存阶段设置
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-21 下午06:00:31
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
	public ActionForward saveXmjdSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xmdm = request.getParameter("xmdm");
		String[] jdid = request.getParameterValues("jdid");
		String[] jdmc = request.getParameterValues("jdmc");
		String[] jdf = request.getParameterValues("jdf");
		String[] jdsx = request.getParameterValues("jdsx");
		boolean result = service.saveJdsz(xmdm, jdid, jdmc, jdf, jdsx);
		String message = result ? MessageUtil.getText(
				MessageKey.SYS_SAVE_SUCCESS) : MessageUtil
				.getText(MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	

	
}
