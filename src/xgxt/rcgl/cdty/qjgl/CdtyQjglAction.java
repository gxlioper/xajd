package xgxt.rcgl.cdty.qjgl;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

public class CdtyQjglAction extends BasicExtendAction{
	
	/**
	 * 学生请假申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsqjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 用户名
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		String userName = user.getUserName();

		String doType = request.getParameter("doType");
		String xh = "stu".equalsIgnoreCase(userStatus) ? userName : request.getParameter("xh");
		
		CdtyQjglService service = new CdtyQjglService();
		
		if("save".equalsIgnoreCase(doType)){
			CdtyQjglForm myForm = (CdtyQjglForm)form;
			
			String message = service.saveXsqj(myForm) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
		
		Map<String, String> stuInfo = new HashMap<String, String>();
		// 学号不为空则获取学生信息
		if(StringUtils.isNotNull(xh)){
			stuInfo = service.getStuInfo(xh);
		}
		
		setWriteAbleAndTitle(request, "rcsw_cdty_qjsq.do");
		request.setAttribute("rs", stuInfo);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("userStatus", userStatus);
		request.setAttribute("xqmc", CdtyQjglService.xqdzMap.get(Base.currXq));
		return mapping.findForward("xsqjUpdate");
	}
	
	/**
	 * 请假审核管理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		User user = getUser(request);
		// 用户名
		String userStatus = user.getUserStatus();
		String qx = request.getParameter("qx");
		String doType = request.getParameter("doType");
		
		// 学生用户无审核权限
		if("stu".equalsIgnoreCase(userStatus)){
			request.setAttribute("yhInfo", "学生用户不能操作该模块！");
			return new ActionForward("/yhInfo.do");
		}
		
		CdtyQjglService service = new CdtyQjglService();
		CdtyQjglForm model = (CdtyQjglForm)form;
		
		// 区别审核级别
		Map<String, Object> map = service.getShMap(qx);
		
		// 批量审核操作
		if("sh".equalsIgnoreCase(doType)){
			String shjg = "tg".equalsIgnoreCase(request.getParameter("shjg")) ? "通过" : "不通过";
			String[] pkValues = request.getParameterValues("pkValues");
			
			String message = service.batchSh(pkValues, qx, user, GetTime.getNowTime(), shjg) ? "操作成功" : "操作失败";
			request.setAttribute("message", message);
		}
		
		String[] outPutList = (String[])map.get("outputs");

		request.setAttribute("topTr", map.get("topTr"));
		request.setAttribute("rs", service.getShList(model, qx, user, outPutList));
		
		if("xy".equalsIgnoreCase(userStatus)){// 学院登陆
			if("xy".equalsIgnoreCase(userStatus)){
				model.setXydm(user.getUserDep());
			}
		}

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.requestSetList(new String[]{"xn", "xq"}, request);
 		setWriteAbleAndTitle(request, (String)map.get("path"));
 		
 		request.setAttribute("pageSize", model.getPages().getPageSize());
		request.setAttribute("user", user);
		request.setAttribute("qx", qx);
		return mapping.findForward("qjshManage");
	}
	
	/**
	 * 请假审核修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjshUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		CdtyQjglService service = new CdtyQjglService();
		Map<String, String> map = service.getQjInfo(pkValue);
		
		request.setAttribute("rs", map);
		
		request.setAttribute("xqmc", CdtyQjglService.xqdzMap.get((map.get("xq"))));
		request.setAttribute("doType", doType);
		return mapping.findForward("qjshUpdate");
	}
	
	/**
	 * 请假查询管理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		User user = getUser(request);
		
		String userName = user.getUserName();
		String userDep = user.getUserDep();
		String userStatus = user.getUserStatus();
		String doType = request.getParameter("doType");
		
		CdtyQjglService service = new CdtyQjglService();
		CdtyQjglForm model = (CdtyQjglForm)form;
		
		if("del".equalsIgnoreCase(doType)){
			String[] pkValues = request.getParameterValues("pkValues");
			String message = service.batchDel(pkValues) ? "操作成功" : "操作失败";
			
			request.setAttribute("message", message);
		}
	
		// 点出查询按钮
			String[] outPutList = new String[]{"disabled","pkValue","xh", "xn", "xqmc", "xm", "bjmc", "qjsj", "qjts","sh1", "sh2", "sh3", "sh4"};
			
			request.setAttribute("topTr", service.getTitle("xg_view_rcsw_xsqj", outPutList));
			request.setAttribute("rs", service.getCxList(model, user, outPutList));
		
		if("stu".equalsIgnoreCase(userStatus)){
			model.setXh(userName);
		}else if("xy".equalsIgnoreCase(userStatus)){// 学院登陆
			model.setXydm(userDep);	
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.requestSetList(new String[]{"xn", "xq"}, request);
		
 		setWriteAbleAndTitle(request, "rcsw_xsqjcx.do");
 		request.setAttribute("user", user);
 		request.setAttribute("pageSize", model.getPages().getPageSize());
		request.setAttribute("realTable", "xg_rcsw_xsqjb");
		request.setAttribute("tableName", "xg_view_rcsw_xsqj");
		
		return mapping.findForward("qjcxManage");
	}
	
	/**
	 * 请假审核修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjxgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String opera = request.getParameter("opera");
		
		String forward = "print".equalsIgnoreCase(doType) ? "qjglPrint" : "qjxgUpdate";
		
		CdtyQjglService service = new CdtyQjglService();
		
		// 修改操作
		if("update".equalsIgnoreCase(opera)){
			CdtyQjglForm model = (CdtyQjglForm) form;
			String message = service.updateQjgl(model, pkValue) ? "修改成功！" : "修改失败！";
			
			request.setAttribute("message", message);
		}
		
		Map<String, String> map = service.getQjInfo(pkValue);
		
		request.setAttribute("rs", map);
		
		request.setAttribute("xqmc", CdtyQjglService.xqdzMap.get((map.get("xq"))));
		request.setAttribute("doType", doType);
		return mapping.findForward(forward);
	}
	
	/**
	 * 检验请假记录是否存在
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkQjjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		// 用户名
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		String userName = user.getUserName();

		String xh = "stu".equalsIgnoreCase(userStatus) ? userName : request.getParameter("xh");
		
		CdtyQjglService service = new CdtyQjglService();
		
		CdtyQjglForm myForm = (CdtyQjglForm)form;
		myForm.setXh(xh);
		String num=service.checkQjjl(myForm);	
		PrintWriter pw= response.getWriter();
		pw.write(num);
		pw.close();
		
		return null;
	}
}
