package com.zfsoft.xgxt.xsxx.xjyd.xjydsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgForm;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqForm;
import com.zfsoft.xgxt.xsxx.xjyd.xjydsq.XjydsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理-宿舍异动
 * @类功能描述: 学籍异动审核
 * @作者： Qilm[工号:964]
 * @时间： 2013-12-2 下午03:31:05 
 * @版本： V1.0
 */
public class XjydshAction extends SuperAction {
	
	private XjydshService service = new XjydshService();
	
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "xjyd_xjydsh.do";
	
	/**
	 * 
	 * @描述:学籍异动审核列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-2 下午03:31:24
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
	public ActionForward xjydshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydshForm myForm = (XjydshForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			request.setAttribute("path", "xjyd_xjydsh.do");
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//最后一级撤销审核后调用的路径
		request.setAttribute("cancelPath", "xjydsh.do?method=cancel");
		request.setAttribute("path", "xjyd_xjydsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydshList");
	}

	/**
	 * 
	 * @描述:学籍异动审核
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-2 下午03:30:48
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
	@SystemLog(description="访问学生信息-学籍异动-学籍异动审核-审核XJYDSQID:{xjydsqid}")
	public ActionForward xjydsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydshForm myForm = (XjydshForm) form;
		XjydsqService sqservice = new XjydsqService();
		//获取申请信息
		XjydsqForm model = sqservice.getModel(myForm.getXjydsqid());
		
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			
			//保存审核结果
			myForm.setSplcid(model.getSplcid());
			myForm.setXh(model.getXh());
			myForm.setXjydsqid(model.getXjydsqid());
			
			boolean result = service.ydsh(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//学生信息显示（可配置）
		szXsxx(request,myForm.getXh());

		//是否是最后一级审核
		boolean isZhgw = service.isZhgw(myForm.getGwid(), model.getSplcid());
		myForm.setIsZhgw(String.valueOf(isZhgw));
		request.setAttribute("myForm", myForm);
		
		// 异动起止时间（默认值）
		myForm.setSqkssj(model.getSqkssj());
		myForm.setSqjssj(model.getSqjssj());
		
		
		//异动申请信息
		request.setAttribute("data", model);
		
		//年级、学院、专业、班级
		FormModleCommon.setAllNjXyZyBjList(request);
		
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			//异动原因
			List<HashMap<String,String>> ydyyList = sqservice.getYdyyList();
			request.setAttribute("ydyyList", ydyyList);
			
			//维护来源学校/去向学校
			List<HashMap<String,String>> lyqxxxList = sqservice.getLyqxxxList();
			request.setAttribute("lyqxxxList", lyqxxxList);
			
			//当前状态
			List<HashMap<String,String>> dqztList = sqservice.getDqztList();
			request.setAttribute("dqztList", dqztList);
		}
		
		//request.setAttribute("isZhgw", String.valueOf(isZhgw));
		return mapping.findForward("xjydsh");
	}
	/**
	 * 
	 * @描述:学籍异动审核
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-2 下午03:30:48
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
	public ActionForward xjydshCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XjydshForm myForm = (XjydshForm) form;
		XjydsqService sqservice = new XjydsqService();
		
		//获取申请信息
		XjydsqForm model = sqservice.getModel(myForm.getXjydsqid());

		//学生信息显示（可配置）
		szXsxx(request,model.getXh());

		request.setAttribute("myForm", myForm);
		
		// 异动起止时间（默认值）
		myForm.setSqkssj(model.getSqkssj());
		myForm.setSqjssj(model.getSqjssj());
		
		
		//异动申请信息
		request.setAttribute("data", model);	
		request.setAttribute("shzt", model.getShzt());	
		
		XjydjgForm xjydjg = new XjydjgForm();
		XjydjgService xjydjgService = new XjydjgService();
		xjydjg = xjydjgService.getModelBySqid(myForm.getXjydsqid());
		request.setAttribute("xjydjg", xjydjg);
		
		return mapping.findForward("xjydshCk");
	}
	
	/**
	 * 
	 * @描述: 学生基本信息
	 * @作者：Qilm[工号：964]
	 * @日期：2014-3-12 下午04:58:07
	 * @param request
	 * @param xh
	 * void 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	private void szXsxx(HttpServletRequest request,String xh){
		//查询学生信息
		if(xh != null && !"".equals(xh)){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}

		//学生基本信息显示配置（同申请）
		jbxxList = new BdpzService().getJbxxpz("xjydsq");
		request.setAttribute("jbxxList", jbxxList);
	}
	

	/**
	 * @描述:最后一级撤销审核
	 * @作者：qilm
	 * @日期：2013-10-10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学籍异动-学籍异动审核-撤销SHID:{shid}")
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydshForm model = (XjydshForm) form;
		HashMap<String,String> shxx = ShlcUtil.getShxx(model.getShid());	
		// 业务回滚
		boolean result = service.cancel(model.getSplcid(), shxx.get("ywid"));
		// 业务回滚成功
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
	/**
	 * @描述:撤销审核操作（需要单独处理业务）
	 * @作者：qilm
	 * @日期：2013-9-30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学籍异动-学籍异动审核-撤销SHID:{shid}")
	public ActionForward cxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XjydshForm model = (XjydshForm) form;
		ShlcInterface service = new CommShlcImpl();
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(model.getShid());
		boolean result = service.runCancel(user.getUserName(), shxx.get("ywid"), model.getSplcid(), shxx.get("gwid"));
		
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
