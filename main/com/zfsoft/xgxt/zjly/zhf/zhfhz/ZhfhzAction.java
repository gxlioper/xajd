/**
 * @部门:学工产品事业部
 * @日期：2016-6-23 上午10:01:13 
 */  
package com.zfsoft.xgxt.zjly.zhf.zhfhz;

import java.util.HashMap;
import java.util.List;

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
 * @模块名称: 综合素质分管理
 * @类功能描述: 综合素质提升学分汇总表 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-6-23 上午10:01:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfhzAction extends SuperAction<ZhfhzForm, ZhfhzService> {
	
	private static final String url = "xg_zjly_zhszfhz.do";
	
	/**
	 * 
	 * @描述: 综合素质分汇总
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-23 上午10:35:43
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
	public ActionForward getZhfhzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
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
		
		ZhfhzService service = new ZhfhzService();	
		HashMap<String,String> map = service.getXsxx(userName);
		HashMap<String,String> zfMap = service.getZfs(userName);
		String zfs = zfMap.get("zfs");
		if(zfs != null&& zfs !="") {
			if(".".equalsIgnoreCase(zfs.substring(0, 1))) {
				zfs = "0"+zfs;
			}
		}
		if(zfs == null || zfs =="" || "0".equalsIgnoreCase(zfs)){		
			zfs = "";
		}	
		request.setAttribute("path", "xg_zjly_zhszfhz.do");	
		request.setAttribute("rs", map);
		request.setAttribute("zfrs", zfs);
		List<HashMap<String, String>> mkzflist = service.getmkzf(userName);
		String html = service.getHzxxHtml(userName,mkzflist);
		   //取下表体数据
		request.setAttribute("html", html);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getZhfhzList");		
	}
	
}
