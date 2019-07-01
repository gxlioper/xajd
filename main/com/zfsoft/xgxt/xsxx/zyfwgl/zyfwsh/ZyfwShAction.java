/**
 * @部门:学工产品事业部
 * @日期：2017年5月10日 上午8:40:46 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdsh.SthdshForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq.ZyfwSqForm;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq.ZyfwSqService;

import common.newp.StringUtil;
import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 志愿服务管理模块
 * @类功能描述: 志愿服务审核Action
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月10日 上午8:40:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwShAction extends SuperAction<ZyfwShForm,ZyfwShService>{

	private final String ZYFW="zyfw";
	private ZyfwShService zyfwShService = new ZyfwShService();
	private static final String url = "xsxx_zyfwgl_sh.do?method=zyfwShList";
	
	/**
	 * @描述:转到志愿服务审核列表页面
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月10日 上午10:03:03
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
	public ActionForward zyfwShList(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path",url);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zyfwShList");
	}
	
	/**
	 * @描述:获取志愿服务审核列表JSON数据
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月10日 上午10:03:03
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
	public ActionForward getZyfwShListData(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ZyfwShForm zyfwShForm = (ZyfwShForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zyfwShForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = zyfwShService.getPageList(zyfwShForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述:跳转到志愿服务单个审核页面
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月10日 下午3:04:51
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
	public ActionForward zyfwShDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ZyfwShForm zyfwShForm = (ZyfwShForm) form;
		ZyfwSqForm model = new ZyfwSqService().getModel(zyfwShForm.getFwid());
		if (!StringUtil.isNull(zyfwShForm.getXh())) {
			BeanUtils.copyProperties(zyfwShForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zyfwShForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZYFW);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("zyfwShDgsh");
	}
	
	/**
	 * @描述:保存志愿服务单个审核
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月10日 下午3:11:11
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
	public ActionForward saveForDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ZyfwShForm zyfwShForm = (ZyfwShForm) form;
		User user = getUser(request);
		// 保存单个审核
		boolean result = zyfwShService.saveForDgsh(zyfwShForm, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述:跳转到志愿服务批量审核页面
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月10日 下午3:04:51
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
	public ActionForward zyfwShPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		return mapping.findForward("zyfwShPlsh");
	}
	
	/**
	 * @描述:保存志愿服务批量审核
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月10日 下午3:12:22
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
	public ActionForward saveForPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ZyfwShForm zyfwShForm = (ZyfwShForm) form;
		User user = getUser(request);
		String message = zyfwShService.saveForPlsh(zyfwShForm, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * @描述:最后一级撤销审核
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月10日 下午3:04:51
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
	public ActionForward cancelShLast(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ZyfwShForm zyfwShForm = (ZyfwShForm) form;
		// 最后一级撤销
		boolean isSuccess = zyfwShService.cancelShLast(zyfwShForm);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
