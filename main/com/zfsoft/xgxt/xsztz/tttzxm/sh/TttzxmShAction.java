/**
 * @部门:学工产品事业部
 * @日期：2016-7-22 上午10:45:34 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.sh;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsztz.tttzxm.comm.CommTtxmService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-22 上午10:45:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TttzxmShAction extends SuperAction<TttzxmShForm, TttzxmShService> {
	private TttzxmShService service = new TttzxmShService();
	private CommTtxmService commService = new CommTtxmService();
	
	private static final String url = "sztz_ttxm_sh.do";
	
	/**
	 * 
	 * @描述: 查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-28 上午10:47:20
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
	public ActionForward getTtxmshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmShForm model = (TttzxmShForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述: 单个审核
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-28 上午10:49:11
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
	public ActionForward TtxmDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmShForm myForm = (TttzxmShForm) form;
		boolean result = false;
		TttzxmShForm model = service.getModel(myForm);
		myForm.setSqr(model.getSqr());
		//GzkhKhsqService khsqService = new GzkhKhsqService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
//			String message = khsqService.checkZjeAndGs(model.getXh(),model.getXn(),model.getYxgs(),model.getGzrq(),model.getCjbz(),model.getSqid(),model.getGwdm());
//			if(!"true".equals(message)){
//				 response.getWriter().print(getJsonMessage(message));
//				 return null;
//			}
			User user = getUser(request);
			// 保存单个审核
			String flag = service.saveSh(myForm, user);
			if(flag.equals("true")){
				result = true;
			}else if(flag.equals("false")){
				result = false;
			}else{
				Map<String,String> map = new HashMap<String, String>();
				map.put("message", flag);
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;
			}
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
		}
		//队长信息map
		request.setAttribute("dzxxmap", commService.getDzxx(model.getDzxh()));
		//队员信息list
		request.setAttribute("dyzzlist", commService.getDyxxNotDz(model.getTtsqid(), model.getDzxh()));
		//项目信息
		request.setAttribute("xmxxmap", commService.getXmxxMap(model.getXmdm()));
		
		model.setShid(request.getParameter("shid"));
		request.setAttribute("form", model);
		return mapping.findForward("dgsh");
	}
	
	/**
	 * 
	 * @描述: 团体项目批量审核
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-28 下午01:52:38
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
	public ActionForward  TtxmPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmShForm model = (TttzxmShForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("plsh");
	}
	
	/**
	 * 
	 * @描述:撤销
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-28 下午01:57:25
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
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmShForm model = (TttzxmShForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setTtsqid(sqid);
		// 最后一级撤销
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:撤销
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-28 下午01:57:37
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
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TttzxmShForm model =new TttzxmShForm();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
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
	
}
