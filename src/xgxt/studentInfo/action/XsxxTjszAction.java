package xgxt.studentInfo.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.XsxxTjszService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息-统计设置-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * 12 Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * 
 * @version 1.0
 */

public class XsxxTjszAction extends BasicAction{

	/**
	 * 统计设置
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward tjszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		StudentInfoForm myForm = (StudentInfoForm) form;
		XsxxTjszService service = new XsxxTjszService();
		User user = getUser(request);// 用户对象

		// ================= 赋初值 ==================
		// 访问路径
		String path = "xsxx_tjsz.do?method=tjszManage";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 功能模块
		String gnmk = "xsxx";
		// 菜单
		String menu = "";
		// 提示信息
		String message = "";
		// =================end==================

		// =================执行保存操作==================;
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveTjsz(myForm, user);
			message = flag ? "操作成功" : "操作失败";
		}
		// =================end==================

		// =================获得DB配置内容==================
		List<HashMap<String, Object>> rsList = service.getDbPzList(myForm);
		// =================end==================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, user, request);
		request.setAttribute("rsList", rsList);
		if (rsList != null && rsList.size() > 0) {
			request.setAttribute("rsNum", rsList.size());
		}
		// ===================end ====================

		// ===================初始化列表值 ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("tjszManage");
	}
}
