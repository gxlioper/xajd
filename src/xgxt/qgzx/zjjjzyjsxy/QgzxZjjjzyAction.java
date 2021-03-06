/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.qgzx.zjjjzyjsxy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.base.DealString;
import xgxt.utils.GetTime;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江经济职业技术学院勤工助学模块Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-12-03</p>
 */
public class QgzxZjjjzyAction extends DispatchAction {
	
	/**   
	 * 打印学生申请岗位报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward printZjjjzyGwsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> timeMap = new HashMap<String, String>();
		QgzxZjjjzyService service = new QgzxZjjjzyService();
		
		String xh = request.getParameter("xh");
		String gwmc = DealString.toGBK(request.getParameter("xmdm"));
		String yhtc = DealString.toGBK(request.getParameter("yhtc"));
		String jtjjqk = DealString.toGBK(request.getParameter("jtjjknqk"));
		String xssq = DealString.toGBK(request.getParameter("xssq"));
		String kh = DealString.toGBK(request.getParameter("kh"));
		
		map = service.getStuInfo(xh);
		
		map.put("gwmc", gwmc);
		map.put("yhtc", yhtc);
		map.put("jtjjknqk", jtjjqk);
		map.put("xssq", xssq);
		map.put("kh", kh);		
		map.put("isPks", service.isKns(xh) ? "是" : "否");//判断是否是贫困生
		
		timeMap = service.getCurrTime();//当前时间信息
		
		request.setAttribute("rs", map);
		request.setAttribute("rsTime", timeMap);
		return mapping.findForward("printGwsqb");
	}
	
	/**
	 * 打印学生勤工助学考核表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward printReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HashMap< String, String> map = new HashMap<String, String>();
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xymc = DealString.toGBK(request.getParameter("xymc"));
		String bjmc = DealString.toGBK(request.getParameter("bjmc"));
		String xh = DealString.toGBK(request.getParameter("xh"));
		String gwmc = DealString.toGBK(request.getParameter("gwmc"));
		String yrdwmc = DealString.toGBK(request.getParameter("yrdwmc"));
		String khdj = DealString.toGBK(request.getParameter("khdj"));
		String bcbz = DealString.toGBK(request.getParameter("bcbz"));
		String gzsj = DealString.toGBK(request.getParameter("gzsj"));
		String gzje = DealString.toGBK(request.getParameter("gzje"));
		
		map.put("xm", xm);
		map.put("xymc", xymc);
		map.put("bjmc", bjmc);
		map.put("xh", xh);
		map.put("gwmc", gwmc);
		map.put("yrdwmc", yrdwmc);
		map.put("khdj", khdj);
		map.put("bcbz", bcbz);
		map.put("gzsj", gzsj);
		map.put("gzje", gzje);
		
		request.setAttribute("year", GetTime.getNowYear());		
		request.setAttribute("month", GetTime.getNowMonth());
		request.setAttribute("rs", map);
		return mapping.findForward("qgzx_xsgwkhb");
	}
}