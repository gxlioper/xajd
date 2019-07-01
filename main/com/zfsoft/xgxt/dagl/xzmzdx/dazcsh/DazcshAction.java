/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午03:01:13 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcsh;

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
import com.zfsoft.xgxt.dagl.xzmzdx.cssz.DazccsszForm;
import com.zfsoft.xgxt.dagl.xzmzdx.cssz.DazccsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息管理模块
 * @类功能描述: 档案转出-审核
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-5-14 下午03:28:27 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class DazcshAction extends SuperAction<DazcshForm,DazcshService>{
	private final String url = "xsxx_dagl_dazcsh.do";
	private final static String DAZC = "dazc";
	private static List<HashMap<String, String>> jbxxList = null;
	static{
		BdpzService bdpzService = new BdpzService();
		/*学生基本信息显示配置*/
		jbxxList = bdpzService.getJbxxpz(DAZC);
	}
	private DazcshService service = new DazcshService();
	
	/**
	 * @描述: 查询列表并返回Json数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-14 下午05:03:15
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
	@SystemLog(description = "访问学生信息-档案管理-档案转出审核-查询页面")
	public ActionForward getDazcshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcshForm model = (DazcshForm)form;
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
		return mapping.findForward("dazcshList");
	}
	
	/**
	 * @描述: 审核
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-14 下午05:43:06
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
	@SystemLog(description = "访问学生信息-档案管理-档案转出审核-单个审核")
	public ActionForward dazcshSingle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcshForm model = (DazcshForm)form;
		if(!StringUtil.isNull(model.getSqid())){
			/*学生审核数据*/
			HashMap<String, String> xspjshInfo = service.getDazcshInfo(model);
			/*加载学生基本信息*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xspjshInfo.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("rs", StringUtils.formatData(xspjshInfo));
		}
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		model.setShid(request.getParameter("shid"));
		
		/*参数设置信息*/
		DazccsszForm dazccsszForm = new DazccsszService().getModel();
		request.setAttribute("dazccsszForm", dazccsszForm);
		
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("dazcshSingle");
	}
	
	/**
	 * @描述: 保存单个审核
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-14 下午06:54:32
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
	@SystemLog(description="访问学生信息-档案管理-档案转出审核-单个审核保存-SQID:{sqid}")
	public ActionForward dazcshSingleSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		DazcshForm model = (DazcshForm)form;
		DazcshService serviceOne = TransactionControlProxy.newProxy(new DazcshService());
		
		User user = getUser(request);
		boolean result = serviceOne.dazcshSingleSave(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 撤销
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-14 下午05:31:28
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
	@SystemLog(description="访问学生信息-档案管理-档案转出审核-撤销ID:{sqid}")
	public ActionForward dazcshRevoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcshForm model = (DazcshForm)form;
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
}
