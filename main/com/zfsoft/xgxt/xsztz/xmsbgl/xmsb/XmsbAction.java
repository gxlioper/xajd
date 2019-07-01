/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午05:02:12 
 */
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsb;

import java.io.File;
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
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xsztz.xmsbgl.cssz.CsszService;
import common.newp.StringUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-9 下午05:02:12
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XmsbAction extends SuperAction<XmsbForm, XmsbService> {
	private XmsbService service = new XmsbService();
	private CsszService csszService = new CsszService();
	
	private static final String url = "sztz_xmsbgl_xmsb.do";

	/**
	 * 
	 * @描述:项目申报列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-10 下午04:23:38
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
	public ActionForward getXmsbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm model = (XmsbForm) form;
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
		String path = "sztz_xmsbgl_xmsb.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXmsbList");
	}

	/**
	 * 
	 * @描述:项目申报增加
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 下午04:06:41
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
	public ActionForward addXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm model = (XmsbForm) form;
		User user = getUser(request);
		// 学年 学期list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setSfsljx("0");
		String path = "xmsbXmsb.do?method=addXmsb";
		request.setAttribute("path", path);
		request.setAttribute("sbr", user.getUserName());
		service.initParam(request, user);
		return mapping.findForward("addXmsb");
	}

	/**
	 * 
	 * @描述:项目申报修改
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 下午04:07:06
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
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-20 下午04:27:40
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
	public ActionForward editXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm myForm = (XmsbForm) form;
		User user = getUser(request);
		XmsbForm model = service.getModel(myForm);
		if (null != model) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		// 加载项目奖项信息
		List<HashMap<String, String>> xmjxList = service.getXmjxList(myForm.getXmdm());
		request.setAttribute("xmjxList", xmjxList);
		String path = "xmsbXmsb.do?method=editXmsb";
		request.setAttribute("path", path);
		request.setAttribute("csms", model.getCsms());
		service.initParam(request, user);
		return mapping.findForward("editXmsb");
	}

	/**
	 * 
	 * @描述:项目申报查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 上午11:32:02
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
	public ActionForward viewXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm model = (XmsbForm) form;
		HashMap<String, String> xmxxMap = service.getXmxx(model.getXmdm());
		request.setAttribute("rs", xmxxMap);
		// 加载项目奖项信息
		List<HashMap<String, String>> xmjxList = service.getXmjxList(model.getXmdm());
		request.setAttribute("xmjxList", xmjxList);
		// 加载项目参与学院信息
		List<HashMap<String, String>> xyList = service.getCyxyListForView(model.getXmdm());
		request.setAttribute("xyList", xyList);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("shzt", model.getShzt());
		return mapping.findForward("viewXmsb");

	}

	/**
	 * 
	 * @描述:项目申报保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 上午10:18:49
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
	@SystemLog(description = "访问素质拓展-项目申报管理-项目申报-增加XQ:{xn},XQ:{xq},XMMC:{xmmc},XMJBDM:{xmjbdm},SBBMDM:{sbbmdm},SSKMDM:{sskmdm},KCYRS:{kcyrs},XMKSSJ:{xmkssj},SBR:{sbr},LXDH:{lxdh},JCXF:{jcxf}")
	public ActionForward saveXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm model = (XmsbForm) form;
		String objStr = request.getParameter("objStr");
		String[] cyxyArr = request.getParameterValues("cyxy");
		if (service.isHaveSbJl(model, "add")) {// 关联性
			String message = MessageUtil.getText(MessageKey.SZTZ_XMSB_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		List<XmjxForm> jxxxList = null;
		if (objStr!= null && !"".equals(objStr)){
			jxxxList = JsonUtil.jsonArrToList(objStr, XmjxForm.class);
		}
		boolean result = service.saveXmsb(model, jxxxList,cyxyArr);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:项目申报修改保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 下午02:33:27
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
	@SystemLog(description = "访问素质拓展-项目申报管理-项目申报-修改XMDM:{xmdm},XQ:{xn},XQ:{xq},XMMC:{xmmc},XMJBDM:{xmjbdm},SBBMDM:{sbbmdm},SSKMDM:{sskmdm},KCYRS:{kcyrs},XMKSSJ:{xmkssj},SBR:{sbr},LXDH:{lxdh},JCXF:{jcxf}")
	public ActionForward saveEditXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm model = (XmsbForm) form;
		String objStr = request.getParameter("objStr");
		String[] cyxyArr = request.getParameterValues("cyxy");
		String message = null;
		if (service.isHaveSbJl(model, "update")) {// 关联性
			message = MessageUtil.getText(MessageKey.SZTZ_XMSB_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		List<XmjxForm> jxxxList = null;
		if (objStr!= null && !"".equals(objStr)){
			jxxxList = JsonUtil.jsonArrToList(objStr, XmjxForm.class);
		}
		boolean result = service.saveEditXmsb(model, jxxxList,cyxyArr);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 下午02:35:38
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
	@SystemLog(description = "访问素质拓展-项目申报管理-项目申报-删除VALUES:{values}")
	public ActionForward delXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			if(result){
				result = service.delPlXmjx(ids);
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
	 * @描述:撤销申请
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-10 下午03:03:21
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
	public ActionForward cancelXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			XmsbForm model = new XmsbForm();
			model.setXmdm(sqid);
			model.setSplc(lcid);
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
	 * @描述:提交
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-13 上午10:26:05
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
	public ActionForward submitXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm model = (XmsbForm) form;
		String values = request.getParameter("values");
		model.setXmdm(values);
		boolean result = service.submitXmsb(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:数据导出
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-9 上午11:18:26
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbForm model = (XmsbForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model, user);// 查询出所有记录，不分页
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

}
