package xgxt.rcgl.gzdx.lxgl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

public class LxglAction extends BasicExtendAction{
	
	/**
	 * 学生假期留校申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 用户名
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		String userName = user.getUserName();

		String doType = request.getParameter("doType");
		String xh = "stu".equalsIgnoreCase(userStatus) ? userName : request.getParameter("xh");
		
		LxglService service = new LxglService();
		
		if("save".equalsIgnoreCase(doType)){
			LxglForm myForm = (LxglForm)form;
			
			String message = service.saveLxsq(myForm) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
		
		Map<String, String> stuInfo = new HashMap<String, String>();
		// 学号不为空则获取学生信息
		if(StringUtils.isNotNull(xh)){
			stuInfo = service.getStuInfo(xh);
			//----------2013.01.24 honglin控制申请提交按钮 beging----------//
			Map<String, String> map = service.getXslxInfo(xh);
			//判断是否已经申请过
			if(map==null || map.size()<=0){//没有申请过
				request.setAttribute("issq", "no");
			}else{//已经申请过
				request.setAttribute("issq", "yes");
				//判断是否已经审核
				if(StringUtils.isNotNull(map.get("sh1")) && StringUtils.isNotNull(map.get("sh2"))){
					if(!"未审核".equals(map.get("sh1")) || !"未审核".equals(map.get("sh2"))){
						request.setAttribute("issh", "yes");
					}else{
						request.setAttribute("issh", "no");
					}
				}else if(StringUtils.isNotNull(map.get("sh1")) && StringUtils.isNull(map.get("sh2"))){
					if(!"未审核".equals(map.get("sh1"))){
						request.setAttribute("issh", "yes");
					}else{
						request.setAttribute("issh", "no");
					}
				}else if(StringUtils.isNull(map.get("sh1")) && StringUtils.isNotNull(map.get("sh2"))){
					if(!"未审核".equals(map.get("sh2"))){
						request.setAttribute("issh", "yes");
					}else{
						request.setAttribute("issh", "no");
					}
				}else{
					request.setAttribute("issh", "no");
				}
			}
			stuInfo.putAll(map);
						//----------2013.01.24 honglin控制申请提交按钮 end----------//
		}
		
		setWriteAbleAndTitle(request, "rcsw_lxsq.do");
		request.setAttribute("rs", stuInfo);
		request.setAttribute("userStatus", userStatus);
		return mapping.findForward("lxsqUpdate");
	}
	
	/**
	 * 留校审核管理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		User user = getUser(request);
		// 用户名
		String userStatus = user.getUserStatus();
		String doType = request.getParameter("doType");
		
		// 学生用户无审核权限
		if("stu".equalsIgnoreCase(userStatus)){
			request.setAttribute("yhInfo", "学生用户不能操作该模块！");
			return new ActionForward("/yhInfo.do");
		}
		
		LxglService service = new LxglService();
		LxglForm model = (LxglForm)form;
		
		// 批量审核操作
		if("sh".equalsIgnoreCase(doType)){
			String shjg = "tg".equalsIgnoreCase(request.getParameter("shjg")) ? "通过" : "不通过";
			String yj = request.getParameter("shyj");
			String[] pkValues = request.getParameterValues("pkValues");
			String message = service.batchSh(pkValues, user, shjg, yj) ? "操作成功" : "操作失败";
			request.setAttribute("message", message);
		}
		
		Map<String, Object> map = service.getTopMap("sh");
		String[] outPutList = (String[])map.get("outputs");

		request.setAttribute("topTr", map.get("topTr"));
		request.setAttribute("rs", service.getShList(model, user, outPutList));
		
		if("xy".equalsIgnoreCase(userStatus)){// 学院登陆
			model.setXydm(user.getUserDep());
		}

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
 		setWriteAbleAndTitle(request, "rcsw_lxsh.do");
 		
 		request.setAttribute("pageSize", model.getPages().getPageSize());
		request.setAttribute("user", user);

		return mapping.findForward("lxshManage");
	}
	
	/**
	 * 留校审核管理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		User user = getUser(request);
		// 用户名
		String userStatus = user.getUserStatus();
		String doType = request.getParameter("doType");
	
		LxglService service = new LxglService();
		LxglForm model = (LxglForm)form;
		
		// 批量审核操作
		if("del".equalsIgnoreCase(doType)){
			String[] pkValues = request.getParameterValues("pkValues");
			String message = service.batchDel(pkValues) ? "操作成功" : "操作失败";
			request.setAttribute("message", message);
		}else if("export".equalsIgnoreCase(doType)){
			response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
			response.setContentType("application/vnd.ms-excel");
			
			service.lxgldc(model, user, response.getOutputStream());
			return null;
		}
		
		Map<String, Object> map = service.getTopMap("cx");
		String[] outPutList = (String[])map.get("outputs");

		if("xy".equalsIgnoreCase(userStatus)){// 学院登陆
			model.setXydm(user.getUserDep());
		}else if("stu".equalsIgnoreCase(userStatus)){
			model.setXh(user.getUserName());
		}
		
		request.setAttribute("topTr", map.get("topTr"));
		request.setAttribute("rs", service.getCxList(model, user, outPutList));
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
 		setWriteAbleAndTitle(request, "rcsw_lxcx.do");
 		
 		request.setAttribute("pageSize", model.getPages().getPageSize());
		request.setAttribute("user", user);

		return mapping.findForward("lxcxManage");
	}
	
	/**
	 * 留校管理查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxglView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		LxglService service = new LxglService();
		Map<String, String> map = service.getLxInfo(pkValue);
		
		request.setAttribute("rs", map);
		
		request.setAttribute("doType", doType);
		return mapping.findForward("lxglView");
	}
	
	/**
	 * 留校修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxglUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String opera = request.getParameter("opera");
		
		String forward = "print".equalsIgnoreCase(doType) ? "lxglPrint" : "lxglUpdate";
		
		LxglService service = new LxglService();
		LxglForm model = (LxglForm) form;
		
		// 修改操作
		if("update".equalsIgnoreCase(opera)){
			String message = service.updateLxgl(model, pkValue) ? "修改成功！" : "修改失败！";
			request.setAttribute("message", message);
		}
		
		Map<String, String> map = service.getLxInfo(pkValue);
		
		request.setAttribute("rs", map);
		
		request.setAttribute("doType", doType);
		return mapping.findForward(forward);
	}
	
	/**
	 * 留校申请修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2013-01-25
	 */
	public ActionForward lxsqxgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String opera = request.getParameter("opera");
		
		
		LxglService service = new LxglService();
		LxglForm model = (LxglForm) form;
		
		// 修改操作
		if("update".equalsIgnoreCase(opera)){
			String message = service.updateXslxgl(model, pkValue) ? "修改成功！" : "修改失败！";
			request.setAttribute("message", message);
		}
		
		Map<String, String> map = service.getXslxInfo(pkValue);
		
		//判断是否已经申请过
		if(map==null || map.size()<=0){//没有申请过
			request.setAttribute("issq", "no");
		}else{//已经申请过
			request.setAttribute("issq", "yes");
			//判断是否已经审核
			if(StringUtils.isNotNull(map.get("sh1")) && StringUtils.isNotNull(map.get("sh2"))){
				if(!"未审核".equals(map.get("sh1")) || !"未审核".equals(map.get("sh2"))){
					request.setAttribute("issh", "yes");
				}else{
					request.setAttribute("issh", "no");
				}
			}else if(StringUtils.isNotNull(map.get("sh1")) && StringUtils.isNull(map.get("sh2"))){
				if(!"未审核".equals(map.get("sh1"))){
					request.setAttribute("issh", "yes");
				}else{
					request.setAttribute("issh", "no");
				}
			}else if(StringUtils.isNull(map.get("sh1")) && StringUtils.isNotNull(map.get("sh2"))){
				if(!"未审核".equals(map.get("sh2"))){
					request.setAttribute("issh", "yes");
				}else{
					request.setAttribute("issh", "no");
				}
			}else{
				request.setAttribute("issh", "no");
			}
			
		}
		
		request.setAttribute("rs", map);
		
		return mapping.findForward("lxsqUpdate");
	}
}
