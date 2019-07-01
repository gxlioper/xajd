/**
 * @部门:学工产品事业部
 * @日期：2016-5-10 下午04:03:08 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.sh;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.byhkgl.sq.ByhkglSqForm;
import com.zfsoft.xgxt.zxdk.byhkgl.sq.ByhkglSqService;
import com.zfsoft.xgxt.zxdk.xyddk.DkjgService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 毕业还款管理
 * @类功能描述: 毕业还款审核
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-10 下午04:03:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ByhkglShAction extends SuperAction<ByhkglShForm, ByhkglShService>{
	private ByhkglShService service = new ByhkglShService();
	private ByhkglSqService byhkglSqservice = new ByhkglSqService();
	private static final String BYHKGLSQ = "byhkglsq";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(BYHKGLSQ);
	}
	
	private static final String url = "zxdk_byhkgl_byhksh.do";
	
	/**
	 * 
	 * @描述: 查询
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-10 下午06:38:55
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
	public ActionForward getByhkglShList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ByhkglShForm model = (ByhkglShForm) form;
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
		String path = "zxdk_byhkgl_byhksh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("getByhkglShList");
	}
	
	/**
	 * 
	 * @描述: 审核
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-11 上午09:32:52
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
	public ActionForward sbDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ByhkglShForm model = (ByhkglShForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// 保存单个审核
			boolean result = service.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		ByhkglSqForm byhkglSqForm = byhkglSqservice.getModel(model.getSqid());
		BeanUtils.copyProperties(model, byhkglSqForm);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			DkjgService dkjgService = new DkjgService();
			List<HashMap<String,String>> khkList = dkjgService.getKhkList(model.getXh());
			request.setAttribute("khkList", khkList);
		}
		model.setShid(request.getParameter("shid"));
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("zqyyList", byhkglSqservice.getZqyyList());		
		request.setAttribute("zqyymc", byhkglSqservice.getZqyymc(model.getXh()));
		request.setAttribute("rs", model);
		
		return mapping.findForward("sbDgsh");
		
	}
	
	/**
	 * 
	 * @描述: 审核撤销
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-11 下午01:28:05
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
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ByhkglShForm model = new ByhkglShForm();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setSplc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);		

		// 审核撤销成功
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		
		return null;		
	}
	
	/**
	 * 
	 * @描述: 审核撤销
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-11 下午01:54:57
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
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ByhkglShForm model = (ByhkglShForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
		// 最后一级撤销
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;		
	}
	
	/**
	 * 
	 * @描述: 批量审核
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-11 下午02:01:49
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
	public ActionForward sbPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ByhkglShForm model = (ByhkglShForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		return mapping.findForward("sbPlsh");	
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-11 下午02:35:43
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
	public ActionForward viewByhkglSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ByhkglShForm model = (ByhkglShForm) form;
		request.setAttribute("jbxxList", jbxxList);
		ByhkglShForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, viewForm);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			DkjgService dkjgService = new DkjgService();
			List<HashMap<String,String>> khkList = dkjgService.getKhkList(model.getXh());
			request.setAttribute("khkList", khkList);
		}
		request.setAttribute("rs", model);		
		request.setAttribute("zqyymc", byhkglSqservice.getZqyymc(model.getXh()));
		
		return mapping.findForward("viewByhkglSh");
	}
	

}
