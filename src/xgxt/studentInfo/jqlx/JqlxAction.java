package xgxt.studentInfo.jqlx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.NullStringException;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
/**
 * 学生假期留校管理
 */
public class JqlxAction extends BasicAction {
	/**
	 * 假期留校申请（学生申请查询）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward jqlxsq(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		
		JqlxService service = new JqlxService();
		HashMap<String, String> map = new HashMap<String, String>();
		String tableName = "xg_xsxx_xsjqlxsqb";
		String viewName = "view_xg_xsxx_xsjqlxsqb";
		//加载学生基本信息
		if ("stu".equals(userType)) {
			map = service.getStuInfo(userName);
		} else {
			request.setAttribute("message", "本页面只有学生用户可以访问");
			return new ActionForward("/prompt.do",false);
		}
		String doType = request.getParameter("doType");
		if("del".equals(doType)){
			deleteOperation(request, tableName);
		}
		
		request.setAttribute("map", map);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("xqmc", service.getXqmcFromXqdm());
		request.setAttribute("path", "xsxx_xsjqlxsq.do");
		request.setAttribute("cont", service.getCont(Base.currXn + Base.currXq + userName));
		FormModleCommon.commonRequestSet(request);
		
		//查询已申请记录
		String[] outputColumn = new String[] { "pkValue", "bgcolor","xq","xh", "xn", "xqmc", "lxkssj",
				"lxjssj", "lxdh", "ssbh", "fdyxm", "fdysh","xysh" };
		request.setAttribute("annexTerm", " and xh='" + userName + "' ");
		this.selectPageData(request, tableName, viewName, outputColumn);
		return mapping.findForward("jqlxsq");
	}
	
	/**
	 * 假期留校申请（申请信息处理）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NullStringException
	 */
	public ActionForward jqlxsqDetial(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws NullStringException{
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String isFdy = session.getAttribute("isFdy").toString();//判断是否是辅导员
		
		JqlxService service = new JqlxService();
		HashMap<String, String> map = new HashMap<String, String>();
		JqlxActionForm myForm = (JqlxActionForm)form;
		String tableName = "xg_xsxx_xsjqlxsqb";
		String viewName = "view_xg_xsxx_xsjqlxsqb";
		//加载学生基本信息
		if ("stu".equals(userType)) {
			map = service.getStuInfo(userName);
		} else if(StringUtils.isEqual(isFdy, "true") || "xy".equals(userType) || "xx".equals(userType) || "admin".equals(userType)){
			map = service.getJqsqInfo(myForm.getPkValue());
		} else {
			request.setAttribute("errMsg","对不起，您无权访问此页！" );
			return new ActionForward("/errMsg.do",false);
		}
		request.setAttribute("map", map);
		
		String oper = request.getParameter("oper");
		if("add".equals(oper)){
			HashMap<String, String> rs = new HashMap<String, String>();
			rs.put("xn", Base.currXn);
			rs.put("xq", Base.currXq);
			rs.put("xqmc", service.getXqmcFromXqdm());
			request.setAttribute("rs", rs);
		}else{
			this.selectPageDataByOne(request, tableName, viewName, myForm.getPk());
		}
		request.setAttribute("buttonCtrl", request.getParameter("buttonCtrl"));
		
		//保存
		String doType = request.getParameter("doType");
		if ("save".equals(doType)) {
			if("0".equals(service.getCont(myForm.getSave_xn() + myForm.getSave_xq() + myForm.getSave_xh()))){
				this.insertOperation(request, tableName);
			}else{
				this.updateOperation(request, tableName);
			}
			this.selectPageDataByOne(request, tableName, viewName, myForm.getSave_xn() + myForm.getSave_xq() + myForm.getSave_xh());
		}
		return mapping.findForward("jqlxsqDetail");
	}
	
	/**
	 * 假期留校审核（查询）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward jqlxsh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String isFdy = session.getAttribute("isFdy").toString();//判断是否是辅导员
		
		JqlxActionForm myForm = (JqlxActionForm) form;
		JqlxService service = new JqlxService();
		
		String tableName = "xg_xsxx_xsjqlxsqb";
		String viewName = "view_xg_xsxx_xsjqlxsqb";
		
		String doType = request.getParameter("doType");
		String[] outputColumn = null;
		
		/*权限控制*/
		if(StringUtils.isEqual(isFdy, "true")){
			outputColumn = new String[] {"pkValue","bagcolor","dis", "xn", "xqmc","xh","xm", "lxkssj",
					"lxjssj", "lxdh", "ssbh", "fdyxm", "fdysh","xysh"};
			request.setAttribute("shzd", "fdysh");
			request.setAttribute("annexTerm", " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
			request.setAttribute("clientColumns", " (case fdysh when '通过' then '#99CCFF' when '不通过' then '#FF9999' else '#FFFFFF' end) bagcolor,(case xysh when '未审核' then '' else 'disabled' end) dis,");
		} else if ("xy".equals(userType)) {
			outputColumn = new String[] {"pkValue","bagcolor","dis", "xn", "xqmc","xh","xm", "lxkssj",
					"lxjssj", "lxdh", "ssbh", "fdyxm", "fdysh", "xysh" };
			request.setAttribute("shzd", "xysh");
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"' and fdysh<>'未审核' ");
			request.setAttribute("clientColumns", " (case xysh when '通过' then '#99CCFF' when '不通过' then '#FF9999' else '#FFFFFF' end) bagcolor,(case fdysh when '未审核' then 'disabled' else '' end) dis,");
		} else if("xx".equals(userType) || "admin".equals(userType)){
			outputColumn = new String[] {"pkValue","bagcolor","dis", "xn", "xqmc","xh","xm", "lxkssj",
					"lxjssj", "lxdh", "ssbh", "fdyxm", "fdysh", "xysh" };
			request.setAttribute("clientColumns", " (case xysh when '通过' then '#99CCFF' when '不通过' then '#FF9999' else '#FFFFFF' end) bagcolor,(case when (fdysh<>'未审核' or xysh<>'未审核') then 'disabled' else '' end) dis,");
		} else {
			request.setAttribute("errMsg","对不起，您无权访问此页！" );
			return new ActionForward("/errMsg.do",false);
		}
		
		//查询
		this.selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		
		//批量审核
		if (!Base.isNull(doType) && "sh".equalsIgnoreCase(doType)) {
			this.auditingBatchOperation(request, tableName);
		}
		if("del".equals(doType)){
			deleteOperation(request, tableName);
		}
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			this.expPageData(request, response, tableName, viewName, new String[] {"xn", "xqmc","xh","xm", "lxkssj",
					"lxjssj", "lxdh", "ssbh", "fdyxm", "fdysh", "xysh" });
			return mapping.findForward("");
		}
		
		service.setList(request, "sh");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "xsxx_xsjqlxsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jqlxsh");
	}
	
	/**
	 * 假期留校审核（审核信息处理）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NullStringException
	 */
	public ActionForward jqlxshDetial(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws NullStringException{
//		HttpSession session = request.getSession();
//		String userName = session.getAttribute("userName").toString();
//		String userType = session.getAttribute("userType").toString();
//		String isFdy = session.getAttribute("isFdy").toString();//判断是否是辅导员
		
		JqlxService service = new JqlxService();
		JqlxActionForm myForm = (JqlxActionForm)form;
		String tableName = "xg_xsxx_xsjqlxsqb";
		String viewName = "view_xg_xsxx_xsjqlxsqb";
//		//加载学生基本信息
//		if(!StringUtils.isEqual(isFdy, "true")&&!"xy".equals(userType)){
//			request.setAttribute("errMsg","对不起，您无权访问此页！" );
//			return new ActionForward("/errMsg.do",false);
//		}
		
		this.selectPageDataByOne(request, tableName, viewName, myForm.getPk());
		service.setList(request, "sh");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		
		//保存
		String doType = request.getParameter("doType");
		if ("save".equals(doType)) {
			this.updateOperation(request, tableName);
			this.selectPageDataByOne(request, tableName, viewName, myForm.getPkValue());
		}
		return mapping.findForward("jqlxshDetail");
	}
	
}

