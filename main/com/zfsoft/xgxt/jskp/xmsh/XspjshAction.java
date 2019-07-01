/**
 * @部门:学工产品(1)部
 * @日期：2018-4-16 下午07:34:34 
 */  
package com.zfsoft.xgxt.jskp.xmsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生评价管理模块
 * @类功能描述: 学生评价审核
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-16 下午07:34:34 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XspjshAction extends SuperAction<XspjshForm,XspjshService>{
	private final String url = "xspj_xspj_xspjsh.do";
	private final String XSPJ = "xspj";
	private XspjshService service = new XspjshService();
	
	
	/**
	 * @描述: 查询列表并返回json数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-17 上午09:03:16
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
	@SystemLog(description = "访问学习评价-项目审核")
	public ActionForward getXspjshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjshForm model = (XspjshForm)form;
		if (QUERY.equalsIgnoreCase(model.getType())){
			/*生成高级查询对象*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xspjshList");
	}
	
	/**
	 * @描述: 单个审核
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-17 下午03:22:03
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
	public ActionForward xspjshSingle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjshForm model = (XspjshForm)form;
		if(!StringUtil.isNull(model.getSqid())){
			/*学生审核数据*/
			HashMap<String, String> xspjshInfo = service.getXspjshInfo(model);
			/*加载学生基本信息*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xspjshInfo.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("rs", StringUtils.formatData(xspjshInfo));
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XSPJ);
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		model.setShid(request.getParameter("shid"));
		/*取审核状态表中的最新一条记录的分数*/
		HashMap<String, String> shxxLevel = service.getLevelXxBySqid(model);
		request.setAttribute("shxxLevel", shxxLevel);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("xspjshSingle");
	}
	
	/**
	 * @描述: 单个审核保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-18 上午11:49:16
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
	@SystemAuth(url = "xspj_xspj_xspjsh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生评价--项目审核-审核保存-GUID:{sqid}")
	public ActionForward xspjshSingleSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XspjshForm model = (XspjshForm)form;
		XspjshService serviceOne = TransactionControlProxy.newProxy(new XspjshService());
		
		User user = getUser(request);
		boolean result = serviceOne.xspjshSingleSave(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 批量审核
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-18 下午04:24:04
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
	public ActionForward xspjshBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjshForm model = (XspjshForm)form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			XspjshService serviceOne = TransactionControlProxy.newProxy(new XspjshService());
			String message = serviceOne.xspjshBatchSave(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		HashMap<String, String> shxhList = service.getShxhForId(request.getParameter("id"));
		request.setAttribute("shxhList", shxhList);
		return mapping.findForward("xspjshBatch");
	}
	
	/**
	 * @描述: 撤销
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-18 下午05:24:23
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
	@SystemLog(description="访问学生评价--项目审核-撤销-申请ID:{sqid}")
	public ActionForward xspjshRevoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjshForm model = (XspjshForm)form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
		//最后一级撤销
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 无勾选批量审核页面
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-22 下午05:03:14
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
	@SystemLog(description="访问学生评价-项目审核-无勾选批量审核")
	public ActionForward xspjshPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		request.setAttribute("len", request.getParameter("len"));
		request.setAttribute("path", "xspj_xspj_xspjsh.do");
		return mapping.findForward("xspjshPlsh");
	}
	
	/**
	 * @描述: 批量审核保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-22 下午08:24:04
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
	@SystemLog(description="访问学生评价-项目审核-无勾选批量审核保存")
	public ActionForward xspjshPlshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjshForm model = (XspjshForm)form;
		User user = getUser(request);
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		List<HashMap<String, String>> resultList = service.xspjPlshxx(model,user);
		XspjshService serviceOne = TransactionControlProxy.newProxy(new XspjshService());
		String message = serviceOne.xspjshPlshSave(model, user,resultList);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
}
