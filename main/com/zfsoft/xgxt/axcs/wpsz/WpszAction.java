/**
 * @部门:学工产品事业部
 * @日期：2014-12-2 下午06:13:18 
 */
package com.zfsoft.xgxt.axcs.wpsz;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-2 下午06:13:18
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WpszAction extends SuperAction {
	DAO dao = DAO.getInstance();
	private String messageKey;

	private static final String url = "axcs_axcswpsz.do";
	
	/**
	 * 
	 * @描述:物品设置列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-2 下午06:52:30
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
	public ActionForward wpszList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm model = (WpszForm) form;
		WpszService service = new WpszService();
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		List<HashMap<String, String>> xnList = dao.getXnndList();
		request.setAttribute("xnList", xnList);
		model.setXn(Base.currXn);
		request.setAttribute("model", model);
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
		request.setAttribute("path", "axcs_axcswpsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wpszList");

	}

	/**
	 * 
	 * @描述:增加物品
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-3 下午03:22:13
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
	public ActionForward addWp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm model = (WpszForm) form;
		WpszService service = new WpszService();
		if (SAVE.equals(model.getType())) {
			boolean result = service.addWp(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		// 生成物品项目代码，上传图片时使用
		String xmdm = StringUtils.getGuid();
		request.setAttribute("xmdm", xmdm);
		List<HashMap<String, String>> wplbList = service.getWplbList();
		request.setAttribute("wplbList", wplbList);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("path", "axcs_axcswpsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("addWp");
	}

	/**
	 * 
	 * @描述:更新物品
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-4 上午10:18:21
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
	public ActionForward updateWp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm myForm = (WpszForm) form;
		WpszService service = new WpszService();
		WpszForm model = service.getModel(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		List<HashMap<String, String>> wplbList = service.getWplbList();
		request.setAttribute("wplbList", wplbList);
		request.setAttribute("xmdm", model.getXmdm());
		request.setAttribute("xn", model.getXn());
		BeanUtils.copyProperties(myForm, StringUtils.formatData(StringUtils.formatData(model)));
		return mapping.findForward("updateWp");
	}

	/**
	 * 
	 * @描述:删除物品
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-4 下午04:30:15
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
	public ActionForward delWp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszService service = new WpszService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {

			if (service.isHaveSqJl(values)) {// 关联性
				messageKey = MessageKey.AXCS_AXWP_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}

			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_SUCCESS);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;
	}

	/**
	 * 
	 * @描述:查看物品
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-4 上午11:21:42
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
	public ActionForward ckWp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm myForm = (WpszForm) form;
		WpszService service = new WpszService();
		HashMap<String,String> rs = service.getWpxxByXmdm(myForm.getXmdm());
		request.setAttribute("xmdm", myForm.getXmdm());
		request.setAttribute("rs", StringUtils.formatData(rs));
		return mapping.findForward("ckWp");

	}

	/**
	 * 
	 * @描述:物品基本设置
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-3 下午03:10:36
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
	public ActionForward wpJbsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm myForm = (WpszForm) form;
		WpszService service = new WpszService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.bcWpJbsz(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		WpszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("axjz");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// 是否list
		request.setAttribute("kgList", isnotList);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
		request.setAttribute("onoffList", onoffList);
		request.setAttribute("path", "axcs_axcswpsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wpJbsz");
	}

	/**
	 * 
	 * @描述:物品条件设置
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-4 下午04:38:41
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
	public ActionForward wpTjsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm myForm = (WpszForm) form;
		WpszService service = new WpszService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.bcWpTjsz(myForm,request);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		WpszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		// 困难生档次列表
		KnsdcService knsdcSerivce = new KnsdcService();
		request.setAttribute("knsdcList", knsdcSerivce.getKnsdcList());
		request.setAttribute("tjpzList", service.getTjpzList());
		request.setAttribute("path", "axcs_axcswpsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wpTjsz");
	}

	/**
	 * 
	 * @描述:物品复制
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-4 下午05:24:12
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
	public ActionForward wpFz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm myForm = (WpszForm) form;
		WpszService service = new WpszService();
		
		 if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.wpFz(myForm.getXn());
			String messageKey = result ? MessageKey.AXCS_WPFZ_SUCCESS : MessageKey.AXCS_WPFZ_NOTJL;
			response.getWriter().print(getJsonResult(messageKey, result));
			return null;
		}
		 String xn=Base.currXn;
		List<HashMap<String, String>> wpfzList = service.getFzXnList(xn);// 物品复制学年列表
		request.setAttribute("fzmbxn", Base.currXn);
		request.setAttribute("wpfzList", wpfzList);
		String path = "xpj_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("wpfz");
	}

	/**
	 * 
	 * @描述:显示照片
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-3 下午04:45:30
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
	public ActionForward showPhoto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WpszForm model = (WpszForm) form;
		WpszService service = new WpszService();
		String type = request.getParameter("type");
		String xmdm = request.getParameter("xmdm");
		if (null == model.getXmdm() || "".equals(model.getXmdm())||"add".equals(type)) {
			ServletContext application = request.getSession().getServletContext();
			InputStream is = new FileInputStream(new File(application.getRealPath("/images/axcsbgtp.jpg")));
			FileUtil.outputFileStream(is, response.getOutputStream());
		} else {
			InputStream is = service.getPhotoStream(xmdm);
			if (is == null) {
				ServletContext application = request.getSession().getServletContext();
				is = new FileInputStream(new File(application.getRealPath("/images/axcsbgtp.jpg")));
			}
			FileUtil.outputFileStream(is, response.getOutputStream());
		}
		return null;
	}

}
