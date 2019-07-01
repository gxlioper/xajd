
package xgxt.pjpy.njtdzyjsxy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;

/**
 * Title:南京铁道职业技术学院评奖评优页面跳转控制类
 * Copyright:Copright(c)2008
 * Company:杭州正方电子工程有限公司
 * @Author:Lp
 * @version 1.0
 * 生成日期：2008-05-26
 */
public class PjpyNjtdzyjsxyAction extends DispatchAction {
	String writeAble = "";
	String userType = "";
	boolean isStu = false;
	String sUName = "";
	String userDep = "";
	String tableName = "";
	String realTable = "";
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		HttpSession session = request.getSession();
		/** 在线检测 */
		int i = Base.chkTimeOut(session);
		if (i <= 2) {
			request.setAttribute("errMsg", "登陆超时，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
		userType = session.getAttribute("userType").toString();   //得到用户类型
		userDep = session.getAttribute("userDep").toString();//用户所在部门
		isStu = (userType.equalsIgnoreCase("stu"));				 //判断是否是学生用户登录
		sUName = session.getAttribute("userName").toString();	//得到登录用户名
		request.setAttribute("userType",userType);
		return super.execute(mapping,form,request,response);
	}
	/**
	 * 综合素质成绩导入默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public ActionForward zhszcjDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyNjtdzyjsxyActionForm myForm = (PjpyNjtdzyjsxyActionForm)form;
		PjpyNjtdzyjsxyZhszcjModel zhszcjModel = new PjpyNjtdzyjsxyZhszcjModel();
		String xydm = "";
		String zydm = "";
		String nj = "";
		tableName = "view_njtdzhszcj";
		realTable = "njtd_zhszcjb";
		//院系用户访问限制操作本院系内学生相关数据
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			myForm.setXydm(xydm);
		}
		BeanUtils.copyProperties(zhszcjModel,myForm);//把form中的pjpyModel字段值复制到zhszcjModel中
		appendProperities(request,xydm,zydm,nj);//获取相关数据列表
		
		/**判断用户读写权*/
		writeAble = (Base.chkUPower(sUName, "zhszcjImportDefaul.do", isStu) == 1) ? "yes" : "no";
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable",realTable);
		return mapping.findForward("zhszcj_Dr_Default");
	}
	/**
	 * 综合素质成绩导入查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcjSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyNjtdzyjsxyActionForm myForm = (PjpyNjtdzyjsxyActionForm)form;
		PjpyNjtdzyjsxyZhszcjModel zhszcjModel = new PjpyNjtdzyjsxyZhszcjModel();
		PjpyNjtdzyjsxyServices service = new PjpyNjtdzyjsxyServices();
		String xydm = "";
		String zydm = "";
		String nj = "";
//		院系用户访问限制操作本院系内学生相关数据
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
		}
		BeanUtils.copyProperties(zhszcjModel,myForm);//把form中的pjpyModel字段值复制到zhszcjModel中
		ArrayList<HashMap<String, String>> topTr = service.getzhszcjSearchTitle();//获得相关表头信息
		ArrayList<String[]> rs = service.getzhszcjResult(zhszcjModel);//结果查询
		request.setAttribute("topTr",topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		appendProperities(request,xydm,zydm,nj);//获取相关数据列表
		request.setAttribute("writeAble",writeAble);//读写权
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable",realTable);
		return mapping.findForward("zhszcj_Dr_Default");
	}
	/**
	 * 获得该生该学年学期综合素质成绩详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcjDetails(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		PjpyNjtdzyjsxyServices service = new PjpyNjtdzyjsxyServices();
		ArrayList<HashMap<String, String>> cjList = service.getZhszcjJsJg(xh, xn, xq);//获得该生该学年学期综合素质成绩详细信息
		request.setAttribute("rs",cjList);
		return mapping.findForward("zhszcjDetails");
	}
	/**
	 * 在request对象中增加相应的项目:xnList,njList,xyList,zyList,bjList
	 * @param request
	 */
	private void appendProperities(HttpServletRequest request,String xydm,String zydm,String nj){		
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": xydm; 
		zy = zy==null ? "": zydm; 
		njLocal = nj==null ? "": njLocal;
		
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
				
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
	}
}
