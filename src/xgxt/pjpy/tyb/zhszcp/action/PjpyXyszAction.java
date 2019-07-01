package xgxt.pjpy.tyb.zhszcp.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.GlobalsVariable;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.tyb.zhszcp.service.PjpyXyszService;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

public class PjpyXyszAction extends CommonAction {

	/**
	 * 学院奖学金机动金额设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjjesz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		PjpyXyszActionForm szForm = (PjpyXyszActionForm) form;
		PjpyXyszService service = new PjpyXyszService();
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		String act = request.getParameter("act");
		//学院、学生用户不可访问
		if("stu".equalsIgnoreCase(user.getUserType()) || "xy".equalsIgnoreCase(user.getUserType())){
        	request.setAttribute("errMsg", "对不起,您无权访问此页！");
			return new ActionForward("/errMsg.do",false);
        }
		//用户判断该功能是否开放
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(user.getUserType()) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_JXJJESZ)
				&& zjkjService.checkKgflag()){
			String msg = "该功能暂时不开放操作！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//保存数据
		if (SAVE.equalsIgnoreCase(act)) {
			//奖学金金额设置信息保存
			boolean result = service.saveJxjjesz(szForm,user);
			request.setAttribute("result", result);
			request.setAttribute("message", result ? MESSAGE_SUCCESS : MESSAGE_FAIL);
			act = "qry";//查询标记
		}
		//数据查询
		if (QUERY.equalsIgnoreCase(act)) {
			//查询奖学金金额设置信息
			List<String[]> rs = service.queryJxjjesz(szForm,user);
			List<HashMap<String, String>> topTr = service.queryJxjjeszTitle();
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs!=null ? rs.size() : 0);
			request.setAttribute("topTr", topTr);
		}
		//读写权限
		request.setAttribute("path", "pjpy_tyb_jxjjesz.do");
		FormModleCommon.commonRequestSet(request);
		//加载年级、学院、专业、班级信息
		FormModleCommon.setNjXyZyBjList(request);
		//学年年度学期列表
		appendXnxqndList(request);
		//评奖周期为学年
		request.setAttribute("pjzq", "xn");
		//设置默认的学年选项
        szForm.setQueryequals_xn(StringUtils.isNull(szForm.getQueryequals_xn()) ? Base.getJxjsqxn(): szForm.getQueryequals_xn());
		return mapping.findForward("jxjjesz");
	}
	
}
