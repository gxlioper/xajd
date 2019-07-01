package xgxt.pjpy.tyb.zhszcp.action;

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

import com.sun.tools.xjc.reader.RawTypeSet.Mode;

import common.GlobalsVariable;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZctjszModel;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszcpModel;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszfsModel;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZcsjwhService;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyService;
import xgxt.utils.FormModleCommon;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

public class PjpyZcsjwhAction extends CommonAction {

	
	/**
	 * 综合素质测评数据维护(第二版) 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcsjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//获取参数值
		PjpyZhszcpwhActionForm zcForm = (PjpyZhszcpwhActionForm) form;
		String dmlb = request.getParameter("dmlb");
		String dm = request.getParameter("dm");
		
		//设置默认评奖周期
		setDefaultPjxnxqnd(zcForm);
		
		//综测开关设置
		User user = getUser(request);
		XtwhOtherService xtwhService = new XtwhOtherService();
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(user.getUserType()) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_ZHSZCP)
				&& zjkjService.checkKgflag()){
			String msg = "该功能暂时不开放操作！";
			request.setAttribute("yhInfo", msg);
		}
		
		if (GlobalsVariable.XTDM_XY.equalsIgnoreCase(user.getUserType())) {
			zcForm.setXydm(user.getUserDep());
		} else if (GlobalsVariable.XTDM_STU
				.equalsIgnoreCase(user.getUserType())
				|| GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(user
						.getUserType())) {
			request.setAttribute("errMsg", "对不起,您无权访问此页！");
			return new ActionForward("/errMsg.do",false);
		}
		
		request.setAttribute("pjzq", Base.getZczq());
		request.setAttribute("dm", dm);
		request.setAttribute("dmlb", dmlb);
		appendOperQx(request, "pjpy_tyb_zcsjwh.do?dmlb=" + dmlb + "&dm=" + dm);
        FormModleCommon.commonRequestSet(request);
        FormModleCommon.setNjXyZyBjListForFdyBzr(request);
        FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("zcsjwh_second");
	}

	/**
	 * 综合素质测评数据维护(第二版) 查询功能
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcsjwhQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//获取基本参数
		PjpyZhszcpwhActionForm zcForm = (PjpyZhszcpwhActionForm) form;
		
		//设置默认评奖周期
		setDefaultPjxnxqnd(zcForm);
		
		PjpyZhszcpModel model = new PjpyZhszcpModel();
		BeanUtils.copyProperties(model, zcForm);
		String dmlb = request.getParameter("dmlb");
		String dm = request.getParameter("dm");
		PjpyZctjszModel zctjModel = getZctjModel(dmlb, dm);
		model.setZcfdm(dm);
		model.setZcdmjb(dmlb);
		PjpyZcsjwhService service = new PjpyZcsjwhService();
		
		//查询结果
		String pjzq = Base.getZczq();
		List<HashMap<String, String>> topTr = service.queryZcsjwhTitle(pjzq);
		List<String[]> rs = service.queryZcsjwhResult(model, pjzq, zctjModel,true);
		
		//下级代码总数
		List<HashMap<String, String>> dmList = service.queryZctjdmList(zctjModel);
		
		//计算页面展形文本框的起始长度
		int dmNum = !dmList.isEmpty() ? dmList.size() : 0;
		String[] array = !rs.isEmpty() ? rs.get(0) : new String[]{};
		int rsNum = array != null ? array.length : 0;
		
		request.setAttribute("dmNum", rsNum - dmNum > 0 ? rsNum - dmNum - 1 : 0);
		//request.setAttribute("lenNum", rsNum - dmNum > 0 ? rsNum - dmNum : 0);
		appendQryResult(request, topTr, rs);
		
		//项目列表
		request.setAttribute("xmList", dmList);
		return zcsjwh(mapping, form, request, response);
	}
	
	/**
	 * 保存综测分数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcfsSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZhszcpwhActionForm myForm = (PjpyZhszcpwhActionForm) form;
		PjpyZhszfsModel model = new PjpyZhszfsModel();
		BeanUtils.copyProperties(model, myForm);
		model.setDm(myForm.getDms());
		model.setXh(myForm.getXhs());
		model.setJb(request.getParameter("dmlb"));
		
		PjpyZcsjwhService service = new PjpyZcsjwhService();
		request.setAttribute("result", service.savelrf(model));
		return zcsjwhQuery(mapping, myForm, request, response);
	}
	
	/**
	 * 初始化综测分数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initZcf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String dmlb = request.getParameter("dmlb");
		String dm = request.getParameter("dm");
		
		PjpyZhszcpwhActionForm myForm = (PjpyZhszcpwhActionForm) form;
		setDefaultPjxnxqnd(myForm);
		
		PjpyZhszcpModel model = new PjpyZhszcpModel();
		BeanUtils.copyProperties(model, myForm);
		
		model.setZcfdm(dm);
		model.setZcdmjb(dmlb);
			
		PjpyZcsjwhService service = new PjpyZcsjwhService();
		
		service.initZcf(model);
		
		request.setAttribute("result", service.initZcf(model));
		return zcsjwhQuery(mapping, form, request, response);
	}
	
	/**
	 * 综测分数删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcfsDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZhszcpwhActionForm myForm = (PjpyZhszcpwhActionForm) form;
		
		String[] pkValues = request.getParameterValues("primarykey_cbv");
		PjpyZcsjwhService service = new PjpyZcsjwhService();
		boolean result = service.dellrf(pkValues);
		
		request.setAttribute("result", result);
		return zcsjwhQuery(mapping, myForm, request, response);
	}
	
	/**
	 * 导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dataExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZhszcpwhActionForm zcForm = (PjpyZhszcpwhActionForm) form;
		PjpyZhszcpModel model = new PjpyZhszcpModel();
		BeanUtils.copyProperties(model, zcForm);
		String dmlb = request.getParameter("dmlb");
		String dm = request.getParameter("dm");
		PjpyZctjszModel zctjModel = getZctjModel(dmlb, dm);
		model.setZcfdm(dm);
		model.setZcdmjb(dmlb);
		PjpyZcsjwhService service = new PjpyZcsjwhService();
		
		//查询结果
		String pjzq = Base.getZczq();
		List<HashMap<String, String>> topTr = service.queryZcsjwhTitle(zctjModel, Base.getZczq());
		List<String[]> rs = service.queryZcsjwhResult(model, pjzq, zctjModel, false);
		
		String[] title = new String[topTr.size()];
		
		for(int i=0;i<topTr.size();i++){
			title[i] = topTr.get(i).get("cn");
		}
		response.reset();
		response.setContentType("application/vnd.ms-excel");	
		service.dataExport("综测信息德育素质分", title, rs, response.getOutputStream());
		return null;
	}
	
	/**
	 * 设置默认学年，学期，年度
	 * @param zcForm
	 */
	public void setDefaultPjxnxqnd(PjpyZhszcpwhActionForm zcForm) {
		String pjzq = Base.getZczq();
		if ("nd".equalsIgnoreCase(pjzq)) {
			zcForm.setNd(Base.getJxjsqnd());
		} else if ("xq".equalsIgnoreCase(pjzq)) {
			zcForm.setXq(Base.getJxjsqxq());
			zcForm.setXn(Base.getJxjsqxn());
		} else if ("xn".equalsIgnoreCase(pjzq)) {
			zcForm.setXn(Base.getJxjsqxn());
		}
	}
	
	public PjpyZctjszModel getZctjModel(String dmjb, String dm) {
		PjpyZctjszModel model = new PjpyZctjszModel();
		model.setDmjb(dmjb);
		model.setFdm(dm);
		model.setSfwh("1");
		return model;
	}
	
}
