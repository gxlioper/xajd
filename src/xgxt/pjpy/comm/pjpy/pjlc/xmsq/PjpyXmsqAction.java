package xgxt.pjpy.comm.pjpy.pjlc.xmsq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyStuModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目流程_学生申请_action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author sjf
 * @version 1.0
 */
public class PjpyXmsqAction extends BasicExtendAction{
	
	/**
	 * 学生申请主页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xssqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String go = request.getParameter("go");
		PjpyXmsqForm sqForm = (PjpyXmsqForm) form;
		PjpyXmsqService xmsqService = new PjpyXmsqService();
		
		// if(!"stu".equalsIgnoreCase(user.getUserStatus())){
		// String msg = "本模块只能由学生用户进行操作，请确认！";
		// request.setAttribute("yhInfo", msg);
		// return new ActionForward("/yhInfo.do", false);
		// }

		// 判断是否在评奖人员库内
		if ("stu".equalsIgnoreCase(user.getUserStatus())
				&& !xmsqService.isPjry(user.getUserName())) {
			String msg = "您不在本次评奖人员库内，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		// 获得所有可被申请的评奖项目
		if ("go".equalsIgnoreCase(go)) {
			String onload = "yes";
			request.setAttribute("onload", onload);
			Map<String, String> querryMap = new HashMap<String, String>();
			querryMap.put("xmlx", sqForm.getXmlx());
			querryMap.put("xmxz", sqForm.getXmxz());
			querryMap.put("xmfw", sqForm.getXmfw());
			querryMap.put("pjxn", sqForm.getPjxn());
			querryMap.put("pjxq", sqForm.getPjxq());
			querryMap.put("pjnd", sqForm.getPjnd());
			querryMap.put("sfqy", "是");

			List<HashMap<String, String>> rs = "stu".equalsIgnoreCase(user
					.getUserStatus()) ? xmsqService.getPjxmForXssq(user
					.getUserName(), querryMap) : xmsqService
					.getPjxmForXssq(querryMap);
			request.setAttribute("rs", rs);
		}

		setWriteAbleAndTitle(request, "pjpy_pjlc_xssq.do"); // 设置title和读写权
		request.setAttribute("topTr", xmsqService.getToptr(user.getUserType()));
		request.setAttribute("jbsz", PjxtszModel.pjxtszModel);
		request.setAttribute("xmxzList", xmsqService.getXmxzList()); // 项目性质
		request.setAttribute("xmfwList", xmsqService.getXmfwList()); // 项目范围
		request.setAttribute("user", user);

		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("xssqManage");
	}
	
	/**
	 * 学生具体项目申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xssqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		String doType = request.getParameter("doType");
		String opera = request.getParameter("opera");   
		
		PjpyXmsqForm sqForm = (PjpyXmsqForm)form;
		User user = getUser(request);
		String xh = "stu".equalsIgnoreCase(user.getUserType()) ? user.getUserName() : request.getParameter("xh");
		String title = "";
		
		PjpyXmsqService xmsqService = new PjpyXmsqService();
		
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		PjpyXmszModel xmxxModel = xmsqService.getXmszForXmdm(pjxn+pjxq+pjnd+xmdm);
		PjpyStuModel stuModel = new PjpyStuModel(); 
		
		if(!"stu".equalsIgnoreCase(user.getUserStatus())){
			request.setAttribute("lssq", true);
		}
		
		if(StringUtils.isNotNull(xh)){
			stuModel = xmsqService.getStuForPj(xh, xmxxModel);
			
			if(!"stu".equalsIgnoreCase(user.getUserStatus())){
				String tjMessage = xmsqService.judgeTjk(xh, pjxn+pjxq+pjnd+xmdm);
				
				request.setAttribute("tjMessage", tjMessage);
			}
		}
		
		if("add".equalsIgnoreCase(opera)){
			title = "学生单个项目申请";
		}else if("update".equalsIgnoreCase(opera)){
			title = "学生申请项目修改";
			request.setAttribute("sqxmInfo", xmsqService.getSqxmInfo(xh, xmxxModel));
		}
		
		// 项目申请保存或修改操作
		if("save".equalsIgnoreCase(doType)){
			String message = xmsqService.saveXssq(xmxxModel, sqForm) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}else if("update".equalsIgnoreCase(doType)){
			String message = xmsqService.updateXssq(sqForm) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
		
		request.setAttribute("title", title);
		request.setAttribute("opera", opera);
		request.setAttribute("pjxtsz", PjxtszModel.pjxtszModel);
		request.setAttribute("xmxzList", xmsqService.getXmxzList()); // 项目性质
		request.setAttribute("xmfwList", xmsqService.getXmfwList()); // 项目范围
		request.setAttribute("xmxx", xmxxModel);					 // 评奖项目信息
		request.setAttribute("stuJbxx", stuModel.getJbxx());		 // 学生基本信息
		request.setAttribute("stuCjxx", stuModel.getCjxx());		 // 学生成绩信息
		request.setAttribute("stuDjks", stuModel.getDjks());		 // 等级考试
		request.setAttribute("stuZcxx", stuModel.getZcxx());         // 综测成绩信息
		request.setAttribute("shlc", xmsqService.formatShlc(xmxxModel.getShlc()));
		request.setAttribute("sqsj", DateUtils.getLyr());
		return mapping.findForward("xssqUpdate");
	}
	
	/**
	 * 获得评奖学生信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = "view_xg_pjpy_ryqd";
		String go = request.getParameter("go");
		User user = getUser(request);
		
		PjpyXmsqForm sqForm = (PjpyXmsqForm) form;
		
		if("xy".equalsIgnoreCase(user.getUserStatus())){
			sqForm.setQueryequals_xydm(user.getUserDep());
		}
		
		if ("go".equalsIgnoreCase(go)) {
			String[] output = new String[] { "xh", "xm", "xb", "pjnj", "pjxymc", "pjzymc", "pjbjmc"};
			this.selectPageDataByPagination(request, form, viewName, viewName,
					output);
		}
		
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		request.setAttribute("userType", user.getUserStatus());
		return mapping.findForward("stuInfo");
	}
}
