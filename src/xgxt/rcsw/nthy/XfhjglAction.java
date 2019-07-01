package xgxt.rcsw.nthy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;

/**
 * 学费缓交管理ACTION
 */
public class XfhjglAction extends CommonAction {

	/**
	 * 时间设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xfhjSjkz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XfhjglActionForm xfhjForm = (XfhjglActionForm) form;
		XfhjglService service = new XfhjglService();
		HashMap<String, String> map = new HashMap<String, String>();
		
		//保存数据
		if ("save".equalsIgnoreCase(request.getParameter("type"))) {
			boolean flag = service.saveXfhjkg(xfhjForm);
			request.setAttribute("message", flag ? "操作成功！" : "操作失败！");
		}
		
		//查询数据
		map = service.queryXfhjkg();
		if (map ==null || map.get("kg") == null) {
			map = new HashMap<String, String>();
			map.put("kg", "0");
		}
		request.setAttribute("path", "rcsw_nthy_xfhjsjkz.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("rs", map);
		return mapping.findForward("xfhjSjkz");
	}
	
	/**
	 * 学业费缓交申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XfhjglActionForm hjForm = (XfhjglActionForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();
		XfhjglService service = new XfhjglService();
		String xh = request.getParameter("xh");
		HttpSession session = request.getSession();
		String show=request.getParameter("show");
		if ("student".equalsIgnoreCase(session.getAttribute("userOnLine").toString())) {
			xh = session.getAttribute("userName").toString();
		}
		if (!StringUtils.isNull(xh)) {
			rs = getStuInfo(xh);
		}
		if ("save".equalsIgnoreCase(request.getParameter("doType"))) {//保存信息
			boolean result = service.saveXfhjsqxx(hjForm);
			request.setAttribute("message", result ? "操作成功！" : "操作失败！");
		}
		request.setAttribute("rs", rs);
		request.setAttribute("sqsj", DateUtils.getCurrTime());
		request.setAttribute("xn", Base.currXn);
		
		HashMap<String, String> map = service.queryXfhjkg();
		//缓交申请开关已打开
		String kssj = map.get("chkhjkssj");
		String jssj = map.get("chkhjjssj");
		String data = "bksq";
		String currsj = DateUtils.getTime();
		if ("1".equalsIgnoreCase(map.get("kg"))) {//开关打开才进行时间点的判断
			if (!StringUtil.isNull(kssj)
					&& !StringUtil.isNull(jssj)
					&& (Float.parseFloat(kssj) <= Float
							.parseFloat(currsj))
					&& (Float.parseFloat(currsj) <= Float
							.parseFloat(jssj))) {//当前时间可以进行缓交
				data = "ksq";//可申请
			}else{
				
				data ="fsqsj";//非申请时间
			}
		}
		
		if (!data.equalsIgnoreCase("ksq")) {
			String msg = "学费缓交申请开关已关闭，不能进行申请。";
			if("fsqsj".equalsIgnoreCase(data)){
				msg = "不在学费缓交申请时间内，不能进行申请。";
			}
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		request.setAttribute("path", "rcsw_nthy_xfhjsq.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("show", show);
		request.setAttribute("data", data);
		return mapping.findForward("xfhjsq");
	}
	
	/**
	 * 学费缓交审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XfhjglActionForm hjForm = (XfhjglActionForm) form;
		hjForm.setXn(Base.currXn);
		XfhjglService service = new XfhjglService();
		RequestForm rForm = new RequestForm();
		CommService commService=new CommService();
		User user=getUser(request);
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//部门代码
		String userType = session.getAttribute("userType").toString();//用户类型
		if (StringUtils.isEqual(userType, "xy")) {//学院用户直接获取部门代码
			hjForm.setXydm(bmdm);
		} 
		hjForm.setUserType(userType);
		hjForm.setZgh(user.getUserName());
		hjForm.setFdy(session.getAttribute("isFdy") != null ? Boolean.parseBoolean(session.getAttribute("isFdy").toString()) : false);
		
//		 ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		topTr = service.queryXfhjshTitle(hjForm);
		int num = 8;
		rForm.setColList(new String[]{"pk", "dis","r", "xn", "xh", "xm", "xb", "bjmc", "shsj", "shjg"});
		if ("xy".equalsIgnoreCase(userType)) {
			num = 9;
			rForm.setColList(new String[]{"pk", "dis","r", "xn", "xh", "xm", "xb", "bjmc", "shsj", "shjg", "sjsh"});
		} 
		// 显示列数
		String showNum = String.valueOf(num);
		commSetting.setShowNum(showNum);
		
		rForm.setCommSetting(commSetting);
		
		
		// ===============通用设置 end ============
		
		
		rs = (ArrayList<String[]>)service.queryXfhjshResult(hjForm);
		request.setAttribute("path", "rcsw_nthy_xfhjsh.do");
		appendQryResult(request, topTr, rs);
		rForm.setRsArrList(rs);
		commService.setRequestValue(rForm, user, request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("xfhjsh");
	}
	
	/**
	 * 学费审核操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhjsjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String doType = request.getParameter("doType");
		String pk = request.getParameter("pk");
		User user=getUser(request);
		XfhjglActionForm hjForm = (XfhjglActionForm) form;
		
		XfhjglService service = new XfhjglService();
		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");
		
		hjForm.setShsj(DateUtils.getCurrTime());
		hjForm.setZgh(user.getUserName());
		hjForm.setUserType(user.getUserType());
		hjForm.setFdy(user.getIsFdy() != null ? Boolean.parseBoolean(user.getIsFdy()) : false);
		if ("save".equalsIgnoreCase(act)) {//保存数据
			hjForm.setPkValue(pk);
			boolean flag = false;
			if ("dg".equalsIgnoreCase(doType)) {//单个保存
				flag = service.updateXfhjshxx(hjForm);
			} else {//批量保存
				hjForm.setPk(pk.split("!@"));
				flag = service.plshXfhjxx(hjForm);
			}
			request.setAttribute("message", flag ? "操作成功！" : "操作失败！");
		} 
		if ("dg".equalsIgnoreCase(doType)) {//单个查看学生信息
				map = service.queryXfhjshxx(pk);
				if ("xy".equalsIgnoreCase(user.getUserType())) {
					if ("true".equalsIgnoreCase(user.getIsFdy())) {
						hjForm.setShjg(map.get("fdysh"));
						hjForm.setShyj(map.get("fdyyj"));
					} else {
						hjForm.setShjg(map.get("xysh"));
						hjForm.setShyj(map.get("xyyj"));
					}
				} else {
					hjForm.setShjg(map.get("xxsh"));
					hjForm.setShyj(map.get("xxyj"));
				}
			}

		request.setAttribute("doType", doType);
		request.setAttribute("num", request.getParameter("num"));
		request.setAttribute("rs", map);
		request.setAttribute("pk", pk);
		request.setAttribute("act", act);
		return mapping.findForward("xfhjsjsh");
	}
	
	/**
	 * 学费缓交查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhjcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XfhjglActionForm hjForm = (XfhjglActionForm) form;
		XfhjglService service = new XfhjglService();
		RequestForm rForm = new RequestForm();
		CommService commService=new CommService();
		User user=getUser(request);
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();//部门代码
		String userType = session.getAttribute("userType").toString();//用户类型
		if (StringUtils.isEqual(userType, "xy")) {//学院用户直接获取部门代码
			hjForm.setXydm(bmdm);
		} 
		if ("student".equalsIgnoreCase(session.getAttribute("userOnLine").toString())) {
			hjForm.setXh(user.getUserName());
		}
		hjForm.setUserType(userType);
		hjForm.setFdy(session.getAttribute("isFdy") != null ? Boolean.parseBoolean(session.getAttribute("isFdy").toString()) : false);
		hjForm.setZgh(user.getUserName());
		if ("del".equalsIgnoreCase(request.getParameter("act"))) {
			boolean flag = service.deleteXfhjsqxx(request.getParameterValues("cbv"));
			request.setAttribute("message", flag ? "操作成功！" : "操作失败！");
		}
		
//		 ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		topTr = service.queryXfhjjgTitle();
		// 显示列数
		String showNum = String.valueOf(9);
		commSetting.setShowNum(showNum);
		rForm.setColList(new String[]{"pk", "dis","r", "xn", "xh", "xm", "xb", "bjmc", "fdysh", "xysh", "xxsh"});
		rForm.setCommSetting(commSetting);
		
		// ===============通用设置 end ============
		
		rs = (ArrayList<String[]>)service.queryXfhjjg(hjForm);
		request.setAttribute("path", "rcsw_nthy_xfhjcx.do");
		appendQryResult(request, topTr, rs);
		rForm.setRsArrList(rs);
		commService.setRequestValue(rForm, user, request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		
		request.setAttribute("realTable", "xg_rcsw_nthy_xfhjsqb");
		request.setAttribute("tableName", "xg_view_rcsw_nthy_xfhjsqb");
		return mapping.findForward("xfhjcx");
	}
	
	/**
	 * 学费缓交信息修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhjxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XfhjglActionForm hjForm = (XfhjglActionForm) form;
		String pkValue = request.getParameter("pkValue");
		XfhjglService service = new XfhjglService();
		hjForm.setPkValue(pkValue);
		if ("save".equalsIgnoreCase(request.getParameter("doType"))) {
			User user=getUser(request);
			hjForm.setSqsj(DateUtils.getCurrTime());
			hjForm.setZgh(user.getUserName());
			boolean flag = service.saveXfhjsqxx(hjForm);
			request.setAttribute("message", flag ? "操作成功！" : "操作失败！");
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map = service.queryXfhjshxx(pkValue);
		String act = request.getParameter("act");
		if (!"未审核".equalsIgnoreCase(map.get("fdysh")) || !"未审核".equalsIgnoreCase(map.get("xysh")) || !"未审核".equalsIgnoreCase(map.get("xxsh"))) {
			act = "view";
		}
		hjForm.setSqyy(map.get("sqyy"));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		return mapping.findForward("xfhjxg");
	}
}

