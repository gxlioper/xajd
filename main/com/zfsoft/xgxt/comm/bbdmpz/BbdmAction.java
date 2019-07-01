/**
 * @部门:学工产品事业部
 * @日期：2013-12-26 下午02:26:10 
 */  
package com.zfsoft.xgxt.comm.bbdmpz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bbdmpz.model.BbdmModel;
import com.zfsoft.xgxt.comm.bbdmpz.service.BbdmService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-26 下午02:26:10 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BbdmAction extends SuperAction {

	/**
	 * 
	 * @描述:根据配置模块名称获取表格列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-26 下午02:27:40
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
	public ActionForward queryBbdmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BbdmModel myform = (BbdmModel)form;

		String mkdm = myform.getMkdm(); 
		
		String ywid = myform.getYwid();
		
		String thlj = myform.getThlj();

		if(null == mkdm){
			String msg = "本模块未配置表格，请联系管理员！";
			request.setAttribute("yhInfo", msg);
			request.setAttribute("returnBackUrl", request.getParameter("returnBackUrl"));
			return mapping.findForward("yhInfo");
		}

		BbdmService service = new BbdmService();
		
		HashMap<String , String> bbdmCs = service.getBbdmCs(mkdm);

		String dybb = service.getDybb(mkdm, ywid);

		myform.setDybb(dybb);
		
		//模块代码
		request.setAttribute("mkdm", mkdm);
		//当前设置的表格代码
		request.setAttribute("dybb", dybb);
		//退回路径
		request.setAttribute("thlj", thlj);
		//业务主键
		request.setAttribute("ywid", ywid);
		
		List<HashMap<String, String>> bbxxList = service.getBbdmList(mkdm);
	
		if(bbxxList == null || bbxxList.size() == 0){
			String msg = "本模块没有相关表格，请联系管理员！";
			request.setAttribute("yhInfo", msg);
			request.setAttribute("returnBackUrl", request.getParameter("returnBackUrl"));
			return mapping.findForward("yhInfo");
		}
		

		request.setAttribute("bbxxList", bbxxList);
		
		return mapping.findForward("viewBbdmList");
	}
	
	/**
	 * 
	 * @描述:根据GUID 选择报表
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-26 下午03:54:12
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
	public ActionForward queryBbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BbdmService service = new BbdmService();
		BbdmModel myform = (BbdmModel)form;
		String guid = myform.getGuid();//request.getParameter("bbdmwh_guid");
		String ywid = myform.getYwid();
		String dybb = myform.getDybb();
		List<HashMap<String,String>> bbxxList = service.getBbdm(guid);
		
		BbdmModel model = service.getModelByGuid(guid);
		
		if(model != null ){
			BeanUtils.copyProperties(myform, model);
			
			myform.setYwid(ywid);
			myform.setDybb(dybb);
		}
		
		request.setAttribute("bbdmModel", myform);
		
		request.setAttribute("bbxxList", bbxxList);
		
		return mapping.findForward("viewBbdmInfo");
	}
	
	/**
	 * 
	 * @描述:设置报表
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-27 下午01:09:59
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
	public ActionForward setBbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BbdmService service = new BbdmService();
		BbdmModel myform = (BbdmModel)form;
		
		String guid = myform.getGuid();
		String ywid = myform.getYwid();
		String mkdm = myform.getMkdm();
		
/*		if(StringUtil.isNull(ywid) || StringUtil.isNull(guid) || StringUtil.isNull(mkdm)){
			String messageKey = MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}*/
		
		boolean isSuccess = service.setupDybb(mkdm, ywid, guid);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
}
