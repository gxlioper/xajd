/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-10-21 下午06:11:51</p>
 */
package xgxt.xsgygl.hngydx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


import xgxt.action.Base;
import xgxt.base.DealString;

public class GyglHngydxAction extends DispatchAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		try {
			HttpSession session = request.getSession();
			/** 在线检测 */
			int i = Base.chkTimeOut(session);
			if (i <= 2) {
				request.setAttribute("errMsg", "登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "系统出现错误，请重新登录！" + e.toString());
			return new ActionForward("/errMsg.do", false);
		}
		return super.execute(mapping,form,request, response);
	}
	
	public ActionForward getHmcTj(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		
		return mapping.findForward("getHmcTj");
	}
	public ActionForward HmcTj(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		String xb = DealString.toGBK(request.getParameter("xb"));
		GyglHngydxService myService = new GyglHngydxService();
		List<HashMap<String,String>> result = myService.serv_HmcTj(xb);
		
		String [] hjValue = new String []{"0","0","0","0","0"};	
		if(result!=null){
			for(int i=0;i<result.size();i++){
				hjValue[0]=Integer.parseInt(hjValue[0]) + Integer.parseInt(result.get(i).get("fjzs"))+"";
				hjValue[1]=Integer.parseInt(hjValue[1]) + Integer.parseInt(result.get(i).get("mfjrs"))+"";
				hjValue[2]=Integer.parseInt(hjValue[2]) + Integer.parseInt(result.get(i).get("cwzs"))+"";
				hjValue[3]=Integer.parseInt(hjValue[3]) + Integer.parseInt(result.get(i).get("rzrs"))+"";
				hjValue[4]=Integer.parseInt(hjValue[4]) + Integer.parseInt(result.get(i).get("kyrs"))+"";				
			}
			
		}
		request.setAttribute("fjzs_hj",hjValue[0]);
		request.setAttribute("mfjrs_hj",hjValue[1]);
		request.setAttribute("cwzs_hj",hjValue[2]);
		request.setAttribute("rzrs_hj",hjValue[3]);
		request.setAttribute("kyrs_hj",hjValue[4]);
		request.setAttribute("rs", result);
		return mapping.findForward("HmcTj");
	}
}
