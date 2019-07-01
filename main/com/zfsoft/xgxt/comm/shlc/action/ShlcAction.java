/**
 * @部门:学工产品事业部
 * @日期：2013-6-18 上午09:58:56 
 */  
package com.zfsoft.xgxt.comm.shlc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.User;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 审核流程管理模块
 * @类功能描述: 审核流程Action
 * @作者： zhangjw
 * @时间： 2013-6-18 上午09:58:56 
 * @版本： V1.0
 * @修改记录: 
 */

public class ShlcAction extends SuperAction {

	/**
	 * @描述:审批流程跟踪
	 * @作者：zhangjw
	 * @日期：2013-6-18 上午10:04:57
	 * @修改记录: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 */
	public ActionForward lcgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("sqid");
		String splc = request.getParameter("splc");
		List<HashMap<String,String>> infoList = ShlcUtil.getShlcInfo(sqid);
		List<HashMap<String,String>> gwList = ShlcUtil.getSpbzBySplc(splc);
		request.setAttribute("infoList", infoList);
		request.setAttribute("gwList", gwList);
		HashMap<String,String> dqgw = ShlcUtil.getDqGwbz(splc,sqid);
		request.setAttribute("dqgw", dqgw);
		request.setAttribute("gwListSize", infoList.size()-1);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("lcgz");
	}
	/**
	 * @描述:西北工业大学-班级学风建设汇报的流程跟踪
	 * @作者：lgx [工号：1553]
	 * @日期：2018/7/31 9:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	public ActionForward lcgz_hb(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] sqids = request.getParameter("sqids").split(",");
		String[] splcs = request.getParameter("splcs").split(",");
		String[] hblxs = request.getParameter("hblxs").split(",");
		for(int i=0;i<hblxs.length;i++){
			if("nzhb".equals(hblxs[i])){
				List<HashMap<String,String>> infoList = ShlcUtil.getShlcInfo(sqids[i]);
				List<HashMap<String,String>> gwList = ShlcUtil.getSpbzBySplc(splcs[i]);
				request.setAttribute("infoList_nzhb", infoList);
				request.setAttribute("gwList_nzhb", gwList);
				HashMap<String,String> dqgw = ShlcUtil.getDqGwbz(splcs[i],sqids[i]);
				request.setAttribute("dqgw_nzhb", dqgw);
				request.setAttribute("gwListSize_nzhb", infoList.size()-1);

			}
			if("nzzj".equals(hblxs[i])){
				List<HashMap<String,String>> infoList = ShlcUtil.getShlcInfo(sqids[i]);
				List<HashMap<String,String>> gwList = ShlcUtil.getSpbzBySplc(splcs[i]);
				request.setAttribute("infoList_nzzj", infoList);
				request.setAttribute("gwList_nzzj", gwList);
				HashMap<String,String> dqgw = ShlcUtil.getDqGwbz(splcs[i],sqids[i]);
				request.setAttribute("dqgw_nzzj", dqgw);
				request.setAttribute("gwListSize_nzzj", infoList.size()-1);
			}
		}
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("lcgz_hb");
	}
	/**
	 * @描述:TODO审批流程查询
	 * @作者：zhangjw
	 * @日期：2013-6-18 下午07:43:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward lccx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("sqid");
		List<HashMap<String,String>> infoList = ShlcUtil.getShlcInfo(sqid);

		request.setAttribute("infoList", infoList);
		String ywzdFlg = "";//业务字段1
		String ywzdFlg2 = "";//业务字段2
		if(infoList!=null && infoList.size()>0){
			for(HashMap<String,String> info:infoList){
				if(info.get("zd1")!=null&& !"".equalsIgnoreCase(info.get("zd1"))){
					ywzdFlg = info.get("zd1");
				}
				if(info.get("zd4")!=null&& !"".equalsIgnoreCase(info.get("zd4"))){
					ywzdFlg2 = info.get("zd4");
				}
			}
		}
		request.setAttribute("ywzdFlg", ywzdFlg);
		request.setAttribute("ywzdFlg2", ywzdFlg2);
		if("10026".equalsIgnoreCase(Base.xxdm)){
			request.setAttribute("xxdm", Base.xxdm);
		}		
		return mapping.findForward("lccx");
	}

	/**
	 * @描述:审核退回
	 * @作者：zhangjw
	 * @日期：2013-8-1 上午9:47:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward shth(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//需输入三个参数 splc shid shyj
		//ShxxModel model = (ShxxModel)form;
		String shid = request.getParameter("shid");
		String lcid = request.getParameter("lcid");
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		List<HashMap<String,String>> infoList = new CommShlcImpl().getKthSpgw(lcid,shxx.get("gwid"));
		//String upGwid = ShlcUtil.getUpGwid(model.getShlc(), shxx.get("gwid"));
		request.setAttribute("spgw", infoList);
		//request.setAttribute("spgwSize", infoList.size());
		//request.setAttribute("upGwid", upGwid);
		//request.setAttribute("model", model);
		if("zzd".equalsIgnoreCase(request.getParameter("gnid"))){
			return mapping.findForward("lcthForZzd");
		}
		return mapping.findForward("lcth");
	}
	/**
	 * @描述:撤销审核操作
	 * @作者：zhangjw
	 * @日期：2013-8-5 上午10:22:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward cxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShxxModel model = (ShxxModel)form;
		ShlcInterface service = new CommShlcImpl();
		User user = getUser(request);
		boolean result = service.runCancel(user.getUserName(), model.getShid(), model.getShlc());
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @描述:撤销审核操作NEW
	 * @作者：zhangjw
	 * @日期：2013-8-5 上午10:22:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShxxModel model = (ShxxModel) form;
		ShlcInterface service = new CommShlcImpl();
		User user = getUser(request);
		String cancelFlg = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());

		// 审核撤销成功
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("shid", model.getShid());
		map.put("splc", model.getShlc());
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	
	
	/**
	 * @描述:流程退回
	 * @作者：zhangjw
	 * @日期：2013-8-7 下午3:25:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward lcth(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShxxModel model = (ShxxModel)form;
		HashMap<String,String> shxx = ShlcUtil.getShxx(model.getShid());
		ShlcInterface service = new CommShlcImpl();
		User user = getUser(request);
		//输入 shyj ,thgw,shid shlc
		model.setShr(user.getUserName());
		model.setShzt("3");//退回
		model.setGwid(shxx.get("gwid"));//
		model.setYwid(shxx.get("ywid"));
		
		String result = service.runAuditing(model);
		
		// 我的待办回滚
		MyJobsManager manager = new MyJobsImpl();
		manager.cancelJob(shxx.get("ywid"), model.getThgw());
		
		String messageKey = true ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_BACK_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 按用户和功能ID查询常用审核意见
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-5-4 下午03:53:23
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
	public ActionForward getShyjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		String id = request.getParameter("id");
		
		ShlcInterface service = new CommShlcImpl();
		
		List<HashMap<String,String>> shyjList = service.getShyjList(user, id);
		JSONArray json = JSONArray.fromObject(shyjList);
		response.getWriter().print(json);
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述: 设置审核意见
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-5-4 下午03:57:42
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
	public ActionForward szShyj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		String gnid = request.getParameter("gnid");
		
		ShlcInterface service = new CommShlcImpl();
		List<HashMap<String,String>> shyjList = service.getShyjList(user, gnid);
		
		request.setAttribute("gnid", gnid);
		request.setAttribute("shyjList", shyjList);
		return mapping.findForward("szShyj");
	}
	
	
	/**
	 * 
	 * @描述: 保存常用审核意见
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-5-4 下午05:16:58
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
	public ActionForward saveCyyj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		String[] shyj = request.getParameterValues("shyj");
		String gnid = request.getParameter("gnid");
		
		ShlcInterface service = new CommShlcImpl();
		//保存常用意见
		boolean result = service.saveCyyj(user, gnid, shyj);
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
}
