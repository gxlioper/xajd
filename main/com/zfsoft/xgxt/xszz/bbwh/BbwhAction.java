/**
 * @部门:学工产品事业部
 * @日期：2013-6-4 下午03:06:05 
 */  
package com.zfsoft.xgxt.xszz.bbwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhForm;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-6-4 下午03:06:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BbwhAction extends SuperAction {

	
	/**
	 * 
	 * @描述: 报表预览列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-5 下午02:12:47
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
	public ActionForward bgylList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BbwhForm model = (BbwhForm) form;
		BbwhService service = new BbwhService();
		XmwhService xmwhSerivce = new XmwhService();
		
		//项目信息
		XmwhForm xmxx = xmwhSerivce.getModel(model.getXmdm());
		//报表图片列表
		String bblx = model.getBblx();
		List<HashMap<String,String>> bbxxList = service.getBbxxLists(bblx);
		
		request.setAttribute("bblx", bblx);
		request.setAttribute("xmxx", xmxx);
		request.setAttribute("bbxxList", bbxxList);
		return mapping.findForward("bgylList");
	}
	
	
	/**
	 * 
	 * @描述: 登记表预览
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-5 下午03:52:49
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
	public ActionForward showYlbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BbwhForm model = (BbwhForm) form;
		BbwhService service = new BbwhService();
		XmwhService xmwhSerivce = new XmwhService();
		
		//项目信息
		XmwhForm xmxx = xmwhSerivce.getModel(model.getXmdm());
		List<HashMap<String,String>> bbxxList = service.getBbxxList(model.getBbdm());
		
		request.setAttribute("xmxx", xmxx);
		request.setAttribute("bbxxList", bbxxList);
		request.setAttribute("bbwh", model);
		return mapping.findForward("showYlbg");
	}
	
	
	
	/**
	 * 
	 * @描述: 更改项目报表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-6 上午08:47:31
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
	@SystemLog(description="访问学生资助-资助管理-资助项目设置-登记表保存XMDM:{xmdm}")
	public ActionForward updateXmbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BbwhForm model = (BbwhForm) form;
		XmwhForm xmwhModel = new XmwhForm();
		
		xmwhModel.setXmdm(model.getXmdm());
		if(StringUtils.isNotEmpty(model.getBblx())){
			if(model.getBblx().equals("1")){
				xmwhModel.setDybb(model.getBbdm());
			}else if(model.getBblx().equals("2")){
				xmwhModel.setDysbbb(model.getBbdm());
			}
		}
		XmwhService xmwhSerivce = new XmwhService();
		
		boolean isSuccess = xmwhSerivce.runUpdate(xmwhModel);
		response.getWriter().print(isSuccess);
		return null;
	}
}
