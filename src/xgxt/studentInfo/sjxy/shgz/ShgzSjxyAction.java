package xgxt.studentInfo.sjxy.shgz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;

/**
 * Title: 学生工作管理系统 Description:三江学院社会工作履历Action Copyright: Copyright (c) 2010
 * Company: zfsoft Author: sjf Version: 1.0 Time: 2010-7-19
 */
public class ShgzSjxyAction extends BasicAction {
	/**
	 * 添加社会工作履历
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgzadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");

		ShgzForm sForm = (ShgzForm) form;
		ShgzModel model = new ShgzModel();
		BeanUtils.copyProperties(model, sForm);

		// 是否有申请资格
		boolean isApply = true;

		// 操作成功判定标志
		boolean flag = false;
		// 
		ShgzSjxyService service = new ShgzSjxyService();

		String xh = StringUtils.isEqual("stu", userType) ? userName : request
				.getParameter("xh");

		if (service.isAuditing(xh)) {
			isApply = false;
			request.setAttribute("msg", "你申请的记录已经被审核，请不要重复申请");
		}

		if ("add".equalsIgnoreCase(doType) && isApply) {

			// 存储数据，放到三张表中，如果数据存在执行更新操作
			if (service.isExists(xh)) {
				flag = service.updateRecord(model);
			} else {
				flag = service.insertRecord(model);
			}

			// 保存操作提示符
			if (flag) {
				request.setAttribute("msg", "操作成功");
			} else {
				request.setAttribute("msg", "操作失败");
			}
		}

		Map<String, String> map = service.getStuInfo(xh);

		loadInfo(request, "sjxy_shgzwh.do?method=shgzadd");
		request.setAttribute("option", "add");
		request.setAttribute("rs", map);
		request.setAttribute("isApply", isApply);
		return mapping.findForward("shgzadd");
	}

	/**
	 * 社会工作履历审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = "view_sjxy_shgz";
		String tableName = "sjxy_shgzb";
		String doType = request.getParameter("doType");
		String go = request.getParameter("go");
		ShgzForm sForm = (ShgzForm) form;

		HttpSession session = request.getSession();
		String user = getUserType(session);
		String userDep = session.getAttribute("userDep").toString();

		if ("xy".equalsIgnoreCase(user)) {
			sForm.setQueryequals_xydm(userDep);
		}

		if ("sh".equalsIgnoreCase(doType)) {
			String shzd = request.getParameter("shzd");
			String shsj = request.getParameter("shsj");
			String shjg = request.getParameter("shjg");

			if (StringUtils.isNotNull(shjg)) {
				shjg = "tg".equalsIgnoreCase(shjg) ? "通过" : "不通过";
			}

			// 获取页面中以primarykey_为开始的数据
			HashMap<String, String[]> primaryMap = getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY);
			HashMap<String, String> valueMap = new HashMap<String, String>();
			valueMap.put(shzd, shjg);
			valueMap.put(shsj, GetTime.getNowTime());

			// 通用审核方法
			auditingBatchOperation(request, primaryMap, valueMap, tableName);
		}

		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = getOutputColumn(request, user);
			this.selectPageDataByPagination(request, sForm, tableName,
					viewName, outputColumn);
		}

		loadInfo(request, "sjxy_shgzwh.do?method=shgzsh");
		request.setAttribute("user", user);
		return mapping.findForward("shgzsh");
	}

	/**
	 * 查看和修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgzViewAndModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		boolean isApply = true;
		String user = getUserType(session);
		String option = "view";

		ShgzSjxyService service = new ShgzSjxyService();
		Map<String, String> map = service.getShgzInfo(pkValue);

		ShgzForm sForm = (ShgzForm) form;
		ShgzModel model = new ShgzModel();
		BeanUtils.copyProperties(model, sForm);

		if ("view".equalsIgnoreCase(doType)) {
			isApply = false;
			request.setAttribute("title", "社会工作履历 - 社会工作履历单个查看");

		}

		if ("modi".equalsIgnoreCase(doType)) {
			String xtwsh = request.getParameter("xtwsh");
			if ("xy".equalsIgnoreCase(user) && "通过".equals(xtwsh)) {
				isApply = false;
			}
			option = "modi";
			request.setAttribute("title", "社会工作履历 - 社会工作履历修改");
		}

		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.updateRecord(model);

			request.setAttribute("result", "true");
			if (flag) {
				request.setAttribute("msg", "操作成功");
			} else {
				request.setAttribute("msg", "操作失败");
			}
		}

		request.setAttribute("option", option);
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("isApply", isApply);
		return mapping.findForward("shgzadd");
	}

	/**
	 * 社会工作履历查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward shgzcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = "view_sjxy_shgz";
		String tableName = "sjxy_shgzb";
		String doType = request.getParameter("doType");
		String go = request.getParameter("go");
		ShgzForm sForm = (ShgzForm) form;
		
		ShgzSjxyService service = new ShgzSjxyService();

		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		
		String user = getUserType(session);
		String userDep = session.getAttribute("userDep").toString();

		loadInfo(request, "sjxy_shgzwh.do?method=shgzcx");

		if ("stu".equalsIgnoreCase(user)) {
			Map<String, String> map = service.getShgzInfo(userName);
			request.setAttribute("rs", map);
			request.setAttribute("pkValue", userName);
			return mapping.findForward("shgzcxforstu");
		}

		if ("xy".equalsIgnoreCase(user)) {
			sForm.setQueryequals_xydm(userDep);
		}

		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.delRecord(sForm.getPkValues());

			if (flag) {
				request.setAttribute("msg", "操作成功");
			} else {
				request.setAttribute("msg", "操作失败");
			}
		}

		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = getOutputColumn(request, user);
			this.selectPageDataByPagination(request, sForm, tableName,
					viewName, outputColumn);
		}

		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		request.setAttribute("user", user);
		return mapping.findForward("shgzcx");

	}

	/**
	 * 单个审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgzshone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String pkValue = request.getParameter("pkValue");
		String xtwsh = request.getParameter("xtwsh");
		String user = getUserType(session);
		String doType = request.getParameter("doType");
		boolean isApply = true;
		boolean flag = true;
		ShgzSjxyService service = new ShgzSjxyService();
		ShgzModel model = new ShgzModel();
		BeanUtils.copyProperties(model, form);

		// 上级通过，下级便不能审核
		if ("xy".equalsIgnoreCase(user) && "通过".equalsIgnoreCase(xtwsh)) {
			isApply = false;
		}

		if (isApply && "save".equalsIgnoreCase(doType)) {
			flag = service.updateRecord(model);

			request.setAttribute("result", "true");
			// 保存操作提示符
			if (flag) {
				request.setAttribute("msg", "操作成功");
			} else {
				request.setAttribute("msg", "操作失败");
			}
		}

		Map<String, String> map = service.getShgzInfo(pkValue);

		request.setAttribute("option", "shone");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", map);
		request.setAttribute("nowtime", GetTime.getNowTime());
		request.setAttribute("user", user);
		request.setAttribute("isApply", isApply);
		request.setAttribute("title", "社会工作履历 - 社会工作履历单个审核");
		return mapping.findForward("shgzadd");
	}

	/**
	 * 导出数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgzExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "sjxy_shgzb";
		String viewName = "view_sjxy_shgz";
		// 需要导出的字段
		String[] output = { "xh", "xm", "xb", "xymc", "zymc", "bjmc", "ftwsh",
				"xtwsh", "ftwshyj", "xtwshyj" };

		expPageData(request, response, tableName, viewName, output);
		return mapping.findForward("");
	}

	/**
	 * 打印报表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgzprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		ShgzSjxyService service = new ShgzSjxyService();

		Map<String, String> shgzInfo = service.getShgzInfo(pkValue);
		List<HashMap<String, String>> rzqkInfo = service.getRzqkInfo(pkValue);
		List<HashMap<String, String>> hjqkInfo = service.getHjqkInfo(pkValue);

		int rzRow = 10;
		int hjRow = 10;
		int rzSize = 0;
		int hjSize = 0;

		if (rzqkInfo != null) {
			rzSize = rzqkInfo.size();
		}

		if (hjqkInfo != null) {
			hjSize = hjqkInfo.size();
		}

		if (rzSize > rzRow) {
			rzRow = rzSize;
		}

		if (hjSize > hjRow) {
			hjRow = hjSize;
		}

		request.setAttribute("rzSize", rzSize);
		request.setAttribute("hjSize", hjSize);
		request.setAttribute("rzRow", rzRow);
		request.setAttribute("hjRow", hjRow);
		request.setAttribute("shgz", shgzInfo);
		request.setAttribute("rzqk", rzqkInfo);
		request.setAttribute("hjqk", hjqkInfo);
		request.setAttribute("str", new String[10]);

		return mapping.findForward("shgzprint");
	}

	/**
	 * 存放页面中需要的到一些信息
	 * 
	 * @param request
	 */
	private void loadInfo(HttpServletRequest request, String path) {
		request.setAttribute("path", path);

		String[] writeAndTitle = FormModleCommon.getWriteAbleAndTitle(request);

		request.setAttribute("writeAble", writeAndTitle[0]);
		request.setAttribute("title", writeAndTitle[1]);
		FormModleCommon.setNjXyZyBjList(request);
	}

	/**
	 * 把userType 分成三类，学生，学院，学校
	 * 
	 * @param session
	 * @return
	 */
	private String getUserType(HttpSession session) {
		String userType = (String) session.getAttribute("userType");

		if ("xy".equalsIgnoreCase(userType)) {
			userType = "xy";
		} else if ("admin".equalsIgnoreCase(userType)
				|| ("xx".equalsIgnoreCase(userType))) {
			userType = "xx";
		}
		return userType;
	}

	/**
	 * 获取查询显示的字段
	 * 
	 * @param request
	 * @param userType
	 * @return
	 */
	private String[] getOutputColumn(HttpServletRequest request, String userType) {
		String[] outputColumn = new String[] { "pkValue", "disabled", "xh",
				"xm", "xb", "xymc", "zymc","bjmc","ftwsh", "xtwsh" };

		// 获取上级是否审核通过信息
		if ("xy".equalsIgnoreCase(userType)) {
			request
					.setAttribute("clientColumns",
							"(case xtwsh when '通过' then 'disabled' else '' end) disabled,");
		} else {
			request.setAttribute("clientColumns", "'' disabled,");
		}
		return outputColumn;
	}
}
