package xgxt.xsgygl.zgdzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.dtjs.czxx.dyxx.DyxxService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.pjpy.czxx.PjpyCzxxActionForm;
import xgxt.pjpy.czxx.jxj.JxjModel;
import xgxt.pjpy.czxx.jxj.JxjService;
import xgxt.rcsw.RcswForm;
import xgxt.rcsw.gzdx.RcswGzdxModel;
import xgxt.rcsw.gzdx.RcswGzdxService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.GyglTyForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 中国地大公寓管理-action类
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

public class GyglZgddAction extends BasicAction {


	/**
	 * 公寓管理_房源库维护_图表统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rykTbtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglZgddService service = new GyglZgddService();
		GyglZgddModel model = new GyglZgddModel();

		// ---------------- 赋初值 ----------------
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户所在部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_rcsw_swffxmwh";
		// 表名
		String realTable = "rcsw_swffxmwhb";
		// 访问路径
		String path = "rcsw_swff_xmwh.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// -----------------end-----------------------

		// ===================权限控制========================
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		// ========================end ========================

		// ----------------执行查询操作 ----------------
		if (search) {
			List<Object> rs = service.setTbtj(myForm);
			request.setAttribute("rs", rs);
		}
		// -----------------end-----------------------
		
		// ----------------初始化request的值 ----------------
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ----------------end ----------------

		// ----------------初始化request的值 ----------------
		service.setList(myForm, request, "fyk_tbtj");
		// ----------------end ----------------
		request.setAttribute("flg", request.getParameter("flg"));
		return mapping.findForward("rykTbtj");
	}
}
