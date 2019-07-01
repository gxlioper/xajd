/**
 * @部门:学工产品事业部
 * @日期：2015-11-23 上午08:42:15 
 */
package com.zfsoft.xgxt.xszz.zzdy.zzmdgl;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.zzdy.zzbtff.ZzdyBtffService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-11-23 上午08:42:15
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZzdyMdglAction extends SuperAction<ZzdyMdglForm, ZzdyMdglService> {
	private static final String url = "xszz_zzdy_zzmdgl.do";
	private static List<HashMap<String, String>> jbxxList = null;
	BdpzService bdpzService = new BdpzService();
		
	private ZzdyMdglService service = new ZzdyMdglService();

	@SystemAuth(url = url)
	public ActionForward getZzmdList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyMdglForm model = (ZzdyMdglForm) form;
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
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getZzmdList");
	}

	

	/**
	 * 
	 * @描述:修改
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-11-23 下午01:55:20
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
	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description = "访问学生资助-资助待遇-资助名单-修改-id:{id}")
	public ActionForward updateZzmd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyMdglForm myForm = (ZzdyMdglForm) form;
		ZzdyMdglForm model = service.getModel(myForm);
		ZzdyBtffService btffService = new ZzdyBtffService();
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (null != model) {
			model.setBghje(model.getYffje());
			model.setBghzt(model.getFfzt());
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			request.setAttribute("rs", model);
		}
		// 学年list
		request.setAttribute("xnList", Base.getXnndList());
		// 学期list
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("ffjlList", btffService.getFfjlList(model.getXh(), model.getXmdm()));
		jbxxList = bdpzService.getJbxxpz("zzxm");
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("updateZzmd");
	}
	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description = "访问学生资助-资助待遇-资助名单-查看-id:{id}")
	public ActionForward viewZzmd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyMdglForm myForm = (ZzdyMdglForm) form;
		ZzdyMdglForm model = service.getModel(myForm);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (null != model) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			request.setAttribute("rs", model);
		}
		//变更记录
		List<HashMap<String, String>> bgjlList = service.getBgjlList(myForm);
		jbxxList = bdpzService.getJbxxpz("zzxm");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("bgjlList", bgjlList);
		return mapping.findForward("viewZzmd");
	}

	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description = "访问学生资助-资助待遇-资助名单-保存-BGQJE:{bgqje},XH:{xh},XMDM:{xmdm}")
	public ActionForward saveZzmd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyMdglForm myForm = (ZzdyMdglForm) form;
		User user = getUser(request);
		myForm.setBgr(user.getUserName());
		boolean result = service.editZzmd(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description = "访问学生资助-资助待遇-资助名单管理-同步-id:{id}")
	public ActionForward zzmdTb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyMdglForm model = (ZzdyMdglForm) form;
			Boolean result = service.zzmdTb(model.getXn(),model.getXq());
			String message = result ? MessageUtil.getText(MessageKey.SYS_TB_SUCCESS) : MessageUtil.getText(MessageKey.SYS_TB_FAIL);
			response.getWriter().print(getJsonMessage(message));
			return null;
		} 
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xmsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyMdglForm myForm = (ZzdyMdglForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		ZzdyMdglForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
		request.setAttribute("onoffList", onoffList);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsz");
	}

}
