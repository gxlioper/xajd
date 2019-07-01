/**
 * @部门:学工产品事业部
 * @日期：2014-3-3 下午02:21:44 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.cssz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.rcsw.zdzm.comm.ZdzmComm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-在读证明管理模块
 * @类功能描述: 日常事务-在读证明-参数设置
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-3 下午02:21:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzmCsszAction extends SuperAction {
	
	/**@描述 开关关闭*/
	private static final String KG_0 = "0"; 
	
	/**@描述 开关开启*/
	private static final String KG_1 = "1"; 
	
	/**
	 * @描述 审批流服务
	 */
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	private static final String url = ZdzmComm.PATH_CSSZ;
	
	/**
	 * 
	 * @描述:查询参数设置配置
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-3 下午03:34:13
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
	public ActionForward queryCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		ZdzmCsszService service = new ZdzmCsszService();
		
		ZdzmCsszForm model = (ZdzmCsszForm)	form;

		ZdzmCsszForm csszModel = service.getCssz();
		if(null != csszModel){
			BeanUtils.copyProperties(model, csszModel);
		}
		//申请开关列表
		request.setAttribute("sqkgList", service.getSqkgList());
		//下载开关列表
		request.setAttribute("xzkgList", service.getXzkgList());
		//下载控制列表
		request.setAttribute("xzkzList", service.getXzkzList());
		// 以下为公共配置项
		request.setAttribute("shlcList", shlcService.getShlcByDjlx(ZdzmComm.SPL_MKID));
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", ZdzmComm.PATH_CSSZ);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("queryCssz");
	}
	
	/**
	 * 
	 * @描述:保存参数设置
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-4 上午11:46:06
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
	@SystemLog(description="访问日常事务-在读证明办理-基础设置-保存")
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmCsszService service = new ZdzmCsszService();
		ZdzmCsszForm model = (ZdzmCsszForm)	form;
		boolean result = false;
		JSONObject message = null;
		//如果是关闭操作，直接更新数据库
		if(StringUtils.isEqual(model.getKsqkg() , KG_0)){
			result = service.closeCssz(model);
			message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		//如果是开启操作，并且不改变splid ， 直接更新数据库
		}else if(StringUtils.isEqual(model.getKsqkg() , KG_1)){
			
			//2014-5-9update 785 审核流程已存入申请表中，无需再判断。
//			HashMap<String , String> splxgxx = service.getShlcxx();
			
//			if(StringUtils.isEqual(splxgxx.get("shlts"), "0") || 
//					StringUtils.isEqual(model.getSplid(), splxgxx.get("splcid"))){
				result = service.saveCssz(model);
				message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
//			}
//			else if(!StringUtils.isEqual(model.getSplid(), splxgxx.get("splcid")) && !StringUtils.isEqual(splxgxx.get("shlts"), "0")){
//				message = getJsonMessage("有流程正在审核中，不能修改！");
//			}
		}
		response.getWriter().print(message);
		return null;
	}
	
	
}
