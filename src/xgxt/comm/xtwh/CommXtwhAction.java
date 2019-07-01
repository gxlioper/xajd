package xgxt.comm.xtwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */

public class CommXtwhAction extends BasicAction {

	/**
	 * 首页管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommXtwhService service = new CommXtwhService();
		CommXtwhForm myForm = (CommXtwhForm) form;
		User user = getUser(request);

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xtwh_sywh.do";
		// 功能模块
		// String gnmk = "xtwh";
		// 菜单
		// String menu = "sywh";
		// 提示信息
		String message = "";
		// =================end==================

		// ==================执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveSysz(myForm, request);
			message = result ? "操作成功" : "操作失败";
		}
		// =================end ===================

		// ================= 首页设置信息==================
		HashMap<String, String> rs = service.getSyInfo(myForm);
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setRs(rs);
		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		// 暂无
		// =================end ===================

		return mapping.findForward("syManage");
	}
	
	/**
	 * 快捷方式维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kjfsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommXtwhService service = new CommXtwhService();
		CommXtwhForm myForm = (CommXtwhForm) form;
		User user = getUser(request);

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xtwh_kjfs.do";
		// 模块标题
		String title = "用户快捷方式设置";
		// 功能模块
		String gnmk = "xtwh";
		// 菜单
		String menu = "kjfs";
		// 提示信息
		String message = "";
		// =================end==================

		// ==================执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveKjfssz(myForm, user);
			message = result ? "操作成功\n（请刷新页面后才可看到效果）" : "操作失败";
		}
		// =================end ===================

		// ================= 首页设置信息==================
		List<HashMap<String, String>> rsList = service
				.setKjfsList(myForm, user);
		int size = 0;
		if (rsList != null && rsList.size() > 0) {
			size = rsList.size();
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "size" };
		// 其他字段值
		String[] qtzdz = new String[] { String.valueOf(size) };

		rForm.setTitle(title);
		rForm.setRsList(rsList);
		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("kjfsUpdate");
	}
	
	/**
	 * 验证用户权限
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yzyhqx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommXtwhService service = new CommXtwhService();
		User user = getUser(request);

		// ================= 赋初值 ==================
		// 快捷方式路径
		String path = request.getParameter("path");
		path=path.replace("!!@!!", "&");
		path = path.replace("'", "");
		// 是否被分配该路径
		boolean flag = service.hadQx(user, path);
		HttpSession session = request.getSession();
		session.setAttribute("clickPath", path);
		// =================end ===================

		// ==================是否能够访问检测 ==================
		if (!flag) {
			String msg = "对不起，您没有访问该模块的权限，请联系管理员！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================end ===================
		request.setAttribute("widthType","kjfs");
		return new ActionForward("/" + path, false);
	}
}