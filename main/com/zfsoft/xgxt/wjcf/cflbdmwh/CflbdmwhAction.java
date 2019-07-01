/**
 * @部门:学工产品事业部
 * @日期：2013-10-24 上午10:59:52 
 */  
package com.zfsoft.xgxt.wjcf.cflbdmwh;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分类别代码维护) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-24 上午10:52:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CflbdmwhAction extends SuperAction {
	
	private static final String url = "wjcf_cflbdmwh.do?method=cxCflbdmList";
	
	@SystemAuth(url = url)
	public ActionForward cxCflbdmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CflbdmwhForm myForm = (CflbdmwhForm) form;
		CflbdmwhService service = new CflbdmwhService();
		
		if (QUERY.equals(myForm.getType())){
			
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "wjcf_cflbdmwh.do?method=cxCflbdmList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cxCflbdmList");
	}
	/**
	 * 
	 * @描述:(处分类别代码增加)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-24 下午01:33:16
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
	@SystemLog(description = "访问违纪处分-基础设置-处分类别代码维护-增加CFLBMC:{cflbmc},SPL:{spl},SFKSS:{sfkss},SFKSQJC:{sfksqjc}")
	public ActionForward cflbdmAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CflbdmwhForm myForm = (CflbdmwhForm) form;
		CflbdmwhService service = new CflbdmwhService();
		
		if("save".equalsIgnoreCase(myForm.getType())){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			boolean flag=service.checkIsExist(myForm);
			if(flag){
				String messageKey=MessageKey.WJCF_CFLBDM_CFLBCZ;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			myForm.setCjsj(sdf.format(new Date()));
			boolean result = service.runInsert(myForm);
			String messageKey=result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("wjcf"));
		this.saveToken(request);
		return mapping.findForward("cflbdmAdd");
	}
	
	/**
	 * 
	 * @描述:(处分类别代码修改)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-24 下午01:33:16
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
	@SystemLog(description = "访问违纪处分-基础设置-处分类别代码维护-修改CFLBDM:{cflbdm},CFLBMC:{cflbmc},SPL:{spl},SFKSS:{sfkss},SFKSQJC:{sfksqjc}")
	public ActionForward cflbdmUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CflbdmwhForm myForm = (CflbdmwhForm) form;
		CflbdmwhForm model = new CflbdmwhForm();
		CflbdmwhService service = new CflbdmwhService();
		if("save".equalsIgnoreCase(myForm.getType())){
			boolean flag=service.checkIsExist(myForm);
			if(flag){//处分类别名称是否存在
				String messageKey=MessageKey.WJCF_CFLBDM_CFLBCZ;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			//该类别的审批流中是否有数据在审，如果有则不允许修改
			//先空着...
			boolean result=service.runUpdate(myForm);
			String messageKey=result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		model=service.getModel(myForm);
		XtwhShlcService shlcService = new XtwhShlcService();
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("wjcf"));
		request.setAttribute("spjbList", shlcService.getSpjbListByGnmk(model.getSpl()));
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("cflbdmUpdate");
	}
	/**
	 * 
	 * @描述:(处分类别代码删除)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-24 下午01:33:16
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
	@SystemLog(description = "访问违纪处分-基础设置-处分类别代码维护-删除VALUES:{values}")
	public ActionForward cflbdmDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CflbdmwhService service = new CflbdmwhService();
		String values = request.getParameter("values");
		int num = service.runDelete(values.split(","));
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
				: MessageUtil.getText(MessageKey.WJCF_CFLBDM_CFLBYSY);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	public ActionForward getCfqx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CflbdmwhForm myForm = (CflbdmwhForm) form;
		CflbdmwhService service = new CflbdmwhService();
		if(StringUtils.isNotNull(myForm.getCflbdm())){
			String cfqx = service.getCfqx(myForm.getCflbdm());
			response.getWriter().print(getJsonMessage(cfqx));
		}
		return null;
	}
	public ActionForward getCfqxBymc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CflbdmwhForm myForm = (CflbdmwhForm) form;
		CflbdmwhService service = new CflbdmwhService();
		if(StringUtils.isNotNull(myForm.getCflbmc())){
			String cfqx = service.getCfqxByMc(myForm.getCflbmc());
			response.getWriter().print(getJsonMessage(cfqx));
		}
		return null;
	}
	
	/**
	 * @描述:获取审批级别（岗位）列表，列表中每条记录应包含审批岗位和岗位代码
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月27日 上午9:11:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws IOException 
	 */
	public ActionForward getSpjbListById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String splcId = request.getParameter("splcId");
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> spjbList = shlcService.getSpjbListByGnmk(splcId);
		JSONArray dataList = JSONArray.fromObject(spjbList);
		response.getWriter().print(dataList);
		return null;
	}
}
