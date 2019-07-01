/**
 * @部门:学工产品事业部
 * @日期：2014-6-24 上午09:38:17 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsq.xs;

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
import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.rcsw.txhd.xmsq.js.TxhdXmsqJsService;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszForm;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 团学活动申请管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-6-25 下午12:38:17
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TxhdXssqAction extends SuperAction {
	// 定义日常事务中日常行为常量可以从基本信息表中获取学生信息
	private static final String RCSWRCXW = "rcswqjgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	
	private static final String url = "rcsw_txhd_xmsq_xs.do";

	/**
	 * 
	 * @描述:活动申请（学生）页面
	 * @作者：夏夏 [工号：1104]
	 * @日期：2014-6-25 下午12:38:17
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
	public ActionForward xmsqCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);// 当前登录学生
		if (!"stu".equalsIgnoreCase(user.getUserType())) {
			String msg = "该模块仅允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("curXn", Base.currXn);
		String path = "rcsw_txhd_xmsq_xs.do";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsqCk");
	}

	/**
	 * 
	 * @描述:学生活动申请增加
	 * @作者：夏夏 [工号：1104]
	 * @日期：2014-6-26 下午19:38:17
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
	@SystemLog(description="访问日常事务-团学活动-活动申请（学生）-保存SQID:{sqid}")
	public ActionForward xmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXssqForm model = (TxhdXssqForm) form;
		TxhdXssqService service = new TxhdXssqService();
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.saveSq(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		} else if ("submit".equalsIgnoreCase(model.getType())) {
			TxhdXmsqJsService txhdXmsqJsService =  new TxhdXmsqJsService();
			boolean sfksq = txhdXmsqJsService.getSymeForXmdm(model.getXmdm(),model.getType());
			if(sfksq){
				boolean result = service.saveSq(model, "submit");
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_XMMC_FULL));
				return null;
			}
			
		}
		String path = "rcsw_txhd_xs_xmsq?method=xmsq";
		request.setAttribute("path", path);
		request.setAttribute("model", model);
		return mapping.findForward("xmsq");
	}

	/**
	 * 
	 * @描述:活动申请数据查询
	 * @作者：夏夏 [工号：1104]
	 * @日期：2014-6-26 上午10:38:17
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
	public ActionForward xmsqAjaxXs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXssqForm model = (TxhdXssqForm) form;
		TxhdXssqService service = new TxhdXssqService();
		User user = getUser(request); // 当前登录学生
		String type = model.getType();
		if (org.apache.commons.lang.StringUtils.equals(type, "wsq")) {
			List<HashMap<String, String>> data = service.getXmsqPageListXs(
					model, user);
			HashMap<String, String> map = null;
//			for (HashMap<String, String> hashMap : data) {
//				hashMap.put("hdsj", hashMap.get("hdkssj") + "~"
//						+ hashMap.get("hdjssj"));
//				map = service.getCount(hashMap.get("xmdm"), hashMap.get("xq"),
//						hashMap.get("xn"));
//				String syme = null;
//				if(null ==hashMap.get("sqrssx")){
//					syme = "";
//				}else{
//					if (null == map.get("sqrs")) {
//						syme = "0";
//					} else {
//						syme = (Integer.parseInt(hashMap.get("sqrssx")) - Integer.parseInt(map.get("sqrs")))+ "";
//					}
//				}
//
//				hashMap.put("syme", syme);
//			}
			JSONArray dataList = JSONArray.fromObject(data);
			response.getWriter().print(dataList);
			return null;
		} else if (org.apache.commons.lang.StringUtils.equals(type, "ysq")) {
			List<HashMap<String, String>> data = service.getXmsqPageListXsYsq(
					model, user);
			HashMap<String, String> map = null;
			for (HashMap<String, String> hashMap : data) {
				hashMap.put("hdsj", hashMap.get("hdkssj") + "~"
						+ hashMap.get("hdjssj"));
				map = service.getCount(hashMap.get("xmdm"), hashMap.get("xq"),
						hashMap.get("xn"));
				String ysqrs = null;
				if (null == map.get("sqrs")) {
					ysqrs = "0";
				} else {
					ysqrs = map.get("sqrs");
				}

				hashMap.put("ysqrs", ysqrs);
			}
			JSONArray dataList = JSONArray.fromObject(data);
			response.getWriter().print(dataList);
			return null;
		}

		return null;
	}

	/**
	 * 
	 * @描述:项目申请
	 * @作者：夏夏 [工号：1104]
	 * @日期：2014-6-26 下午15:38:17
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
	public ActionForward xmsqXs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXmszService xmszService = new TxhdXmszService();
		TxhdXssqForm myForm = (TxhdXssqForm) form;

		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		// 学年 学期
		myForm.setXq(Base.currXq);
		myForm.setXn(Base.currXn);
		// 活动类别
		TxhdXmszForm model = xmszService.getModel(myForm.getXmdm());
		model.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(model.getXq()));
		request.setAttribute("data", StringUtils.formatData(model));

		return mapping.findForward("xmsqXs");
	}

	/**
	 * 
	 * @描述:TODO提交活动申请
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-6-27 下午02:36:33
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
	@SystemLog(description="访问日常事务-团学活动-活动申请（学生）-提交SQID:{sqid}")
	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXssqService service = new TxhdXssqService();
		String sqid = request.getParameter("sqid");
		String splc = request.getParameter("splc");
		String xmdm = request.getParameter("xmdm");
		System.out.println("shlc:" + splc);
		String xh = request.getParameter("xh");
		
		TxhdXmsqJsService txhdXmsqJsService =  new TxhdXmsqJsService();
		boolean sfksq = txhdXmsqJsService.getSymeForXmdm(xmdm,"submit");
		if(sfksq){
			boolean result = service.submitRecord(sqid, splc, xh);
			if (result) {
				// 更新业务状态为'审核中'
				TxhdXssqForm model = new TxhdXssqForm();
				model.setSqid(sqid);
				model.setShzt(Constants.YW_SHZ);
				service.updateModel(model);
			}
			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_XMMC_FULL));
		}
		return null;
	}

	/**
	 * 
	 * @描述:活动申请修改
	 * @作者：夏夏 [工号：1104]
	 * @日期：2014-6-25 下午12:38:17
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
	@SystemLog(description="访问日常事务-团学活动-活动申请（学生）-修改SQID:{sqid}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXssqService service = new TxhdXssqService();
		TxhdXssqForm myForm = (TxhdXssqForm) form;
		TxhdXmszService xmszService = new TxhdXmszService();
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			myForm.setShzt(Constants.YW_WTJ);
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		} else if ("submit".equalsIgnoreCase(myForm.getType())) {
			myForm.setShzt(Constants.YW_SHZ);
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		TxhdXssqForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

		// 学生基本信息
		String path = "rcsw_txhd_xs_xmsq.do?method=update";
		request.setAttribute("path", path);
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		TxhdXmszForm xmszmodel = xmszService.getModel(myForm.getXmdm());
		model.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(model.getXq()));
		request.setAttribute("sqdata", StringUtils.formatData(model));
		request.setAttribute("data", StringUtils.formatData(xmszmodel));
		return mapping.findForward("update");
	}

	/**
	 * 
	 * @描述:撤销申请
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-06-27 下午14:18:05
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
	@SystemLog(description="访问日常事务-团学活动-活动申请（学生）-撤销VALUES:{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXssqService service = new TxhdXssqService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = service.cancleRecord(values, lcid);
		if (result) {

			// 更新业务状态为'未提交'
			TxhdXssqForm model = new TxhdXssqForm();
			model.setSqid(values);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(values)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateModel(model);

		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:批量删除
	 * @作者：夏夏 [工号：1104]
	 * @日期：2014-06-24 下午15:50:35
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
	@SystemLog(description="访问日常事务-团学活动-活动申请（学生）-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXssqService service = new TxhdXssqService();
		String values = request.getParameter("values");
		int result = service.runDelete(values.split(","));
		String messageKey = null;
		if (result > 0) {
			messageKey = ShlcUtil.deleteSpxx(values.split(",")) ? MessageKey.SYS_DEL_SUCCESS
					: MessageKey.SYS_DEL_FAIL;
		} else {
			messageKey = MessageKey.SYS_DEL_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;

	}

}
