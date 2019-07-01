/**
 * @部门:学工产品事业部
 * @日期：2015-5-26 下午02:01:49 
 */  
package com.zfsoft.xgxt.dekt.xfsh;

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
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszService;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqService;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.mrgzkh.jcsz.GzkhJcszService;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshForm;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshService;
import xsgzgl.qgzx.mrgzkh.khsq.GzkhKhsqService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.dekt.xfsq.DektxfsqForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;


public class DektxfshAction extends SuperAction<DektxfshForm, DektxfshService> {
	private DektxfshService dektxfshService = new DektxfshService();
	
	private static final String url = "dekt_xfsh_list.do";
	
	public ActionForward xfshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xfshList");
	}
	
	
	public ActionForward getXfshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfshForm model = (DektxfshForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = dektxfshService.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	public ActionForward xfshView(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfshForm model = (DektxfshForm) form;
		XsxxService xsxxService = new XsxxService();
		//学生信息
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz("dektxfgl");
		request.setAttribute("jbxxList", jbxxList);
		//业务信息
		request.setAttribute("model", dektxfshService.getView(model));
		return mapping.findForward("xfshView");
	}
	
	public ActionForward xfshDgsh(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfshForm model = (DektxfshForm) form;
		XsxxService xsxxService = new XsxxService();
		//学生信息
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz("dektxfgl");
		request.setAttribute("jbxxList", jbxxList);
		//业务信息
		request.setAttribute("model", dektxfshService.getView(model));
		model.setShid(request.getParameter("shid"));
		request.setAttribute("shid", model.getShid());
		return mapping.findForward("xfshDgsh");
	}
	
	public ActionForward saveSh(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfshForm model = (DektxfshForm) form;
		User user = getUser(request);
		// 保存单个审核
		boolean result = dektxfshService.saveSh(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述：批量审核跳转 //TODO 暂时不用
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年7月28日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward xfshPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("xfshPlsh");
	}
	
	/**
	 * @描述：保存批量审核
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年7月28日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DektxfshForm model = (DektxfshForm) form;
		User user = getUser(request);
		String message = dektxfshService.savePlsh(model, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	
	/**
	 * @描述：撤销审核
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年7月28日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward cxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)	throws Exception {
		DektxfshForm model =(DektxfshForm) form;
		User user = getUser(request);
		ShlcInterface shlc = new CommShlcImpl();
		String cancelFlag = shlc.runCancelNew(user.getUserName(), model.getShid(), model.getSplc());
		String messageKey=Constants.CANCLE_FLG_SUCCESS.equals(cancelFlag)||Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlag)?
				 MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		//最后一级
		if(Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlag)){
			boolean isSuccess = dektxfshService.cxshdel(model);
			messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
