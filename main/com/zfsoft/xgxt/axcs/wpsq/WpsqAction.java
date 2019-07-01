/**
 * @部门:学工产品事业部
 * @日期：2014-12-5 下午02:21:14 
 */
package com.zfsoft.xgxt.axcs.wpsq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.axcs.wpsq.js.WpsqJsService;
import com.zfsoft.xgxt.axcs.wpsz.WpszDao;
import com.zfsoft.xgxt.axcs.wpsz.WpszForm;
import com.zfsoft.xgxt.axcs.wpsz.WpszService;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-5 下午02:21:14
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WpsqAction extends SuperAction {
	DAO dao = DAO.getInstance();
	private BdpzService bdpzService = new BdpzService();
	private WpszService wpszService = new WpszService();
	private List<HashMap<String, String>> jbxxList = null;
	private static final String AXCSXSZBB = "axcs";
	WpsqService service = new WpsqService();
	
	private static final String url = "axcs_axcswpsq_stu.do";

	/**
	 * 
	 * @描述:物品申请列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-5 下午02:36:04
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
	public ActionForward wpsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqForm model = (WpsqForm) form;
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		model.setXn(Base.currXn);
		List<HashMap<String, String>> xnList = dao.getXnndList();
		List<HashMap<String, String>> wplbList = wpszService.getWplbList();
		request.setAttribute("wplbList", wplbList);
		request.setAttribute("xnList", xnList);
		request.setAttribute("path", "axcs_axcswpsq_stu.do");
		FormModleCommon.commonRequestSet(request);
		if (!"stu".equalsIgnoreCase(user.getUserType())) {
			String msg = "该模块仅允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			request.setAttribute("xh", user.getUserName());
			return mapping.findForward("wpsqList");
		}
	}

	/**
	 * 
	 * @描述:验证学生是否满足申请条件
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-11 下午02:20:59
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
	public ActionForward checkSqTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqForm model = (WpsqForm) form;
		List<HashMap<String, String>> resultList = new WpsqJsService().checkTj(model.getXmdm(), model.getXh());
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}

	/**
	 * 
	 * @描述:物品申请（学生）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-9 下午07:04:48
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
	public ActionForward wpsqxsZj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpsqForm model = (WpsqForm) form;
		User user = getUser(request);
		String xh = user.getUserName();
		if (!StringUtil.isNull(xh)) {
			// 学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(AXCSXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		WpszService wpszService = new WpszService();
		request.setAttribute("wpModel", StringUtils.formatData(wpszService.getWpxxByXmdm(model.getXmdm())));
		return mapping.findForward("wpsqxsZj");
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
		WpsqForm model = (WpsqForm) form;
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
		WpsqForm WpsqForm = (WpsqForm) form;
		WpsqForm model = service.getModel(WpsqForm);
		if (model != null) {
			BeanUtils.copyProperties(WpsqForm, model);
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
		WpsqForm WpsqForm = (WpsqForm) form;
		boolean result = false;
		// 如果提交，插入审核状态
		if ("submit".equalsIgnoreCase(WpsqForm.getType()) || "tj".equalsIgnoreCase(WpsqForm.getType())) {
			if ("tj".equalsIgnoreCase(WpsqForm.getType())) {
				String values = request.getParameter("values");
				WpsqForm.setSqid(values);
			}
			WpsqForm.setShzt(Constants.YW_SHZ);// 审核中
			result = service.runUpdate(WpsqForm);
			WpszDao wpszDao = new WpszDao();
			WpszForm wpszForm = wpszDao.getModel(WpsqForm.getXmdm());
			String splc = wpszForm.getSplc();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(WpsqForm.getSqid(), splc, WpsqForm.getXh(), "axcs_axcswpsh.do", "axcs_axcswpsq_tea.do");
			}
		} else {
			result = service.runUpdate(WpsqForm);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
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
			WpsqForm model = new WpsqForm();
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

}
