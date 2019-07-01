/**
 * @部门:学工产品事业部
 * @日期：2016-12-28 上午11:53:34 
 */  
package com.zfsoft.xgxt.rcsw.zyfw.zyfwhz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;

/** 
 * @系统名称: 学生工作管理系统
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cp[工号：1352]
 * @时间： 2016-12-28 上午11:53:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwAction extends SuperAction<ZyfwForm,ZyfwService>{
	private static final String url = "bpmxzyfwhz.do";//数据库里的
	ZyfwService service = new ZyfwService();
	@SystemAuth(url = url)
		public ActionForward gjjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyfwForm model = (ZyfwForm) form;
		HttpSession session = request.getSession();	
		// 登陆用户
		String userName = (String)session.getAttribute("userName");		
		User user = getUser(request); // 当前登录学生
		String userType = user.getUserType();// 该模块只允许学生访问
		if (!"stu".equalsIgnoreCase(userType)) {
			String msg = "该模块仅允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		HashMap<String,String> map = service.getXsxx(userName);
		HashMap<String,String> zscMap = ((ZyfwService) service).getZsc(userName,model);
		request.setAttribute("path", "bpmxzyfwhz.do");	
		request.setAttribute("rs", map);
		request.setAttribute("zsc", zscMap);
		request.setAttribute("zyfwList", service.getZyfwList(userName,model));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getzyfwList");		
	}
}
