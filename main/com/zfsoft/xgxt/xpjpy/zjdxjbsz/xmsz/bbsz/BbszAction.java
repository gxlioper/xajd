/**
 * @部门:学工产品(1)部
 * @日期：2017-4-20 上午09:15:08 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.bbsz;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-基本设置-项目设置-报表设置
 * @类功能描述: 登记表、上报表设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-4-20 上午09:16:35 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BbszAction extends SuperAction {
	
	/**
	 * @描述: 报表预览列表
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-20 上午10:24:31
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
	public ActionForward bbylList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BbszForm model = (BbszForm)form;
		BbszService service = new BbszService();
		XmwhService xmwhService = new XmwhService();
		/*项目信息*/
		XmwhForm xmxx = xmwhService.getModel(model.getXmdm());
		request.setAttribute("xmxx", xmxx);
		/*报表类型*/
		String bblx = model.getBblx();
		request.setAttribute("bblx", bblx);
		/*报表图片列表*/
		List<HashMap<String,String>> bbxxList = service.getBbxxList(bblx);
		request.setAttribute("bbxxList", bbxxList);
		return mapping.findForward("bbylList");
	}
	
	/**
	 * @描述: 报表预览
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-20 下午03:36:59
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
	public ActionForward showYlbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BbszForm model = (BbszForm)form;
		BbszService service = new BbszService();
		XmwhService xmwhService = new XmwhService();
		/*项目信息*/
		XmwhForm xmxx = xmwhService.getModel(model.getXmdm());
		request.setAttribute("xmxx", xmxx);
		/*报表预览*/
		String bbdm = model.getBbdm();
		List<HashMap<String,String>> bbxxList = service.getBbxxYlList(bbdm);
		request.setAttribute("bbxxList", bbxxList);
		request.setAttribute("bbwh", model);
		return mapping.findForward("showYlbb");
	}
	
	/**
	 * @描述: 更新项目报表
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-4-20 下午03:53:09
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
	public ActionForward updateXmbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BbszForm model = (BbszForm)form;
		XmwhForm xmwhModel = new XmwhForm();
		xmwhModel.setXmdm(model.getXmdm());
		if(StringUtils.isNotEmpty(model.getBblx())){
			if(model.getBblx().equals("1")){
				xmwhModel.setDjb(model.getBbdm());
			}else if(model.getBblx().equals("2")){
				xmwhModel.setSbb(model.getBbdm());
			}
		}
		XmwhService xmwhService = new XmwhService();
		boolean isSuccess = xmwhService.runUpdate(xmwhModel);
		response.getWriter().print(isSuccess);
		return null;
	}
}
