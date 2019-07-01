/**
 * @部门:学工产品事业部
 * @日期：2014-12-8 上午09:23:54 
 */
package com.zfsoft.xgxt.axcs.wpsq.js;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.axcs.axlb.AxlbglService;
import com.zfsoft.xgxt.axcs.wpsh.WpshForm;
import com.zfsoft.xgxt.axcs.wpsh.WpshService;
import com.zfsoft.xgxt.axcs.wpsz.WpszDao;
import com.zfsoft.xgxt.axcs.wpsz.WpszForm;
import com.zfsoft.xgxt.axcs.wpsz.WpszService;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-8 上午09:23:54
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WpsqJsAction extends SuperAction {
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private static final String AXCSXSZBB = "axcs";
	WpsqJsService service = new WpsqJsService();
	
	private static final String url = "axcs_axcswpsq_tea.do";

	/**
	 * 
	 * @描述:物品申请列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 上午10:14:42
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
	public ActionForward wpsqJsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqJsForm model = (WpsqJsForm) form;
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
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		request.setAttribute("searchTj", searchModel);
		String path = "axcs_axcswpsq_tea.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wpsqJsList");
	}

	/**
	 * 
	 * @描述:物品申请增加
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 上午11:49:33
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
	public ActionForward wpsqZj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqJsForm model = (WpsqJsForm) form;
		User user = getUser(request);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (!StringUtil.isNull(model.getXmdm())) {
			// 申请物品信息
			WpszService wpszService = new WpszService();
			HashMap<String,String> rusultList = wpszService.getWpxxByXmdm(model.getXmdm());
			request.setAttribute("rs", StringUtils.formatData(rusultList));
		}
		if (SAVE.equalsIgnoreCase(model.getType()) || SUBMIT.equalsIgnoreCase(model.getType())) {
			boolean isExist = false;
			model.setXn(Base.currXn);
			// isExist = service.isExistByLstdsq(model);
			if (!isExist) {
				String sqid = UniqID.getInstance().getUniqIDHash();
				model.setSqid(sqid);
				boolean result = true;
				// boolean result = service.saveLstdsq(model);
				String messageKey = "";

				if (SAVE.equalsIgnoreCase(model.getType())) {
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				} else {
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_LSTD_SQ_REPEAT));
				return null;
			}
		}
		request.setAttribute("path", "axcswpsqjs.do?method=wpsqZj");
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(AXCSXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		// 学年list
		request.setAttribute("xnList", Base.getXnndList());
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		return mapping.findForward("wpsqZj");
	}

	/**
	 * 
	 * @描述:物品选择列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 上午11:02:02
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
	public ActionForward selectWp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqJsForm model = (WpsqJsForm) form;
		Map<String, Object> resultMap = service.getWpsqInfoList(model.getXh());
		request.setAttribute("gotoPath", "axcswpsqjs.do?method=wpsqZj");
		request.setAttribute("xh", model.getXh());
		request.setAttribute("resultMap", StringUtils.formatData(resultMap));
		return mapping.findForward("selectWp");
	}

	/**
	 * 
	 * @描述:物品申请保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 上午11:34:04
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
	public ActionForward saveWpsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqJsForm model = (WpsqJsForm) form;
		String[] xmdm = request.getParameterValues("xmdm");
		boolean result = service.wpsqBc(xmdm, model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:物品申请修改
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 上午11:50:04
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
	public ActionForward wpsqUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqJsForm wpsqJsForm = (WpsqJsForm) form;
		WpsqJsForm model = service.getModel(wpsqJsForm);
		if (model != null) {
			BeanUtils.copyProperties(wpsqJsForm, model);
			// 学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			// 申请物品信息
			WpszService wpszService = new WpszService();
			HashMap<String, String> wpxxMap = wpszService.getWpxxByXmdm(model.getXmdm());
			request.setAttribute("wpxxMap", StringUtils.formatData(wpxxMap));
		}
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("wpsqUpdate");
	}

	/**
	 * 
	 * @描述:物品申请查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-11 下午04:16:40
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
	public ActionForward wpsqView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqJsForm model = (WpsqJsForm) form;
		User user = getUser(request);
		WpshService wpshService = new WpshService();
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		// 加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		WpshForm shForm = new WpshForm();
		shForm.setSqid(model.getSqid());
		request.setAttribute("rs", StringUtils.formatData(wpshService.getWpshInfo(shForm)));
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(AXCSXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", model);
		request.setAttribute("shzt", model.getShzt());
		return mapping.findForward("wpsqView");

	}

	/**
	 * 
	 * @描述:保存修改
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 下午12:46:38
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
	public ActionForward saveUpdateWpsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqJsForm wpsqJsForm = (WpsqJsForm) form;
		boolean result = false;
		// 如果提交，插入审核状态
		if ("submit".equalsIgnoreCase(wpsqJsForm.getType()) || "tj".equalsIgnoreCase(wpsqJsForm.getType())) {
			if ("tj".equalsIgnoreCase(wpsqJsForm.getType())) {
				String values = request.getParameter("values");
				wpsqJsForm.setSqid(values);
			}
			wpsqJsForm.setShzt(Constants.YW_SHZ);// 审核中
			result = service.runUpdate(wpsqJsForm);
			WpszDao wpszDao = new WpszDao();
			WpszForm wpszForm = wpszDao.getModel(wpsqJsForm.getXmdm());
			String splc = wpszForm.getSplc();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(wpsqJsForm.getSqid(), splc, wpsqJsForm.getXh(), "axcs_axcswpsh.do", "axcs_axcswpsq_tea.do");
			}
		} else {
			result = service.runUpdate(wpsqJsForm);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:删除物品申请
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 下午12:59:06
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
	public ActionForward delWpsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// 删除
			int num = service.runDelete(values.split(","));
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", num + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @描述:物品申请撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 下午01:46:22
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
	public ActionForward cancelWpsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			WpsqJsForm model = new WpsqJsForm();
			model.setSqid(sqid);
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
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-11 下午05:27:15
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
		WpsqJsForm model = (WpsqJsForm) form;

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}

}
