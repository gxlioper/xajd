/**
 * @部门:学工产品事业部
 * @日期：2013-11-26 下午05:27:00 
 */  
package com.zfsoft.xgxt.xpjpy.bbwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhModel;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优（新）管理模块
 * @类功能描述: TODO(登记报表维护) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-11-26 下午05:27:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BbwhAction extends SuperAction {

	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(表格列表预览)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-11-27 上午09:07:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward bgylList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		BbwhForm model = (BbwhForm)form;
		BbwhService service = new BbwhService();
		XmwhService xmwhService = new XmwhService();
		//项目信息
		XmwhModel xmxx = xmwhService.getModel(model.getXmdm());
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			if (map.get("xqdm").equals(xmxx.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
		}
		String bblx=model.getBblx();
		//报表图片列表
		List<HashMap<String,String>> bbxxList = service.getBbxxList(bblx);
		
		request.setAttribute("bblx", bblx);
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("xmxx", xmxx);
		request.setAttribute("bbxxList", bbxxList);
		return mapping.findForward("viewBgylList");
	}
	/**
	 * 
	 * @描述:TODO(登记表预览)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-11-27 上午09:08:55
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
		
		BbwhForm model = (BbwhForm)form;
		BbwhService service = new BbwhService();
		XmwhService xmwhService = new XmwhService();
		//项目信息
		XmwhModel xmxx = xmwhService.getModel(model.getXmdm());
		
		//报表图片列表
		List<HashMap<String,String>> bbxxList = service.getBbxx(model.getBbdm());
		
		request.setAttribute("xmxx", xmxx);
		request.setAttribute("bbxxList", bbxxList);
		request.setAttribute("bbwh", model);
		return mapping.findForward("viewYlbb");

	}
	
	/**
	 * 
	 * @描述:TODO(设置项目登记表)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-11-27 上午09:09:32
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
	@SystemLog(description="访问评奖评优-基本设置-项目设置-登记表设置-保存XMDM：{xmdm}")
	public ActionForward updateXmbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BbwhForm model = (BbwhForm) form;
		XmwhService xmwhService = new XmwhService();
		
		XmwhModel xmxx = new XmwhModel();
		if(model.getBblx().equals("2")){
			xmxx.setDysbbb(model.getBbdm());
		}else{
			xmxx.setDybb(model.getBbdm());
		}
		xmxx.setXmdm(model.getXmdm());
		boolean isSuccess = xmwhService.runUpdate(xmxx);
		response.getWriter().print(isSuccess);
		return null;
	}
}
