package xgxt.pjpy.comm.pjpy.rssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_人数设置_action类
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

public class PjpyRsszAction extends BasicExtendAction{
	
	/**
	 * 人数设置主界面方法 
	 */
	public ActionForward rsszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyRsszForm pjForm = (PjpyRsszForm)form;
		User user = getUser(request);
		
		PjpyRsszService rsszService = new PjpyRsszService();
		
		//如果不曾做过人数设置初始化，系统自动初始化。
		if (rsszService.isRsszCsh()){
			rsszService.initRssz();
		}
		
		if(!rsszService.isTbRyqd()){
			String msg = "本次评奖周期尚未执行过“同步部门”和“同步人员”的操作，无法进行人数设置，请在“评奖人员确定”模块进行设置”！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		pjForm.setPjxn(PjxtszModel.pjxtszModel.getPjxn());
		pjForm.setPjnd(PjxtszModel.pjxtszModel.getPjnd());
		pjForm.setPjxq(PjxtszModel.pjxtszModel.getPjxq());
		
		// 能否人数分配权限
		boolean kfsz = false;
		// 人数设置方式
		String rsszfs = PjxtszModel.pjxtszModel.getRsszfs();
		
		if("xx".equalsIgnoreCase(rsszfs)){
			kfsz = "xx".equalsIgnoreCase(user.getUserStatus());
		}else if("xxxy".equalsIgnoreCase(rsszfs)){
			kfsz = "xx".equalsIgnoreCase(user.getUserStatus()) || "xy".equalsIgnoreCase(user.getUserStatus());
		}
		
		setWriteAbleAndTitle(request, "pjpy_xmsz_rssz.do");
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("kfsz", kfsz);
		request.setAttribute("xmxzList", rsszService.getXmxzList()); // 项目性质
		request.setAttribute("xmfwList", rsszService.getXmfwList()); // 项目范围
		request.setAttribute("pjxtszModel", PjxtszModel.pjxtszModel);
		// 可分配人数的评奖项目
		request.setAttribute("pjxmList", rsszService.getXmForRssz());
		request.setAttribute("user", user);
		return mapping.findForward("rsszManage");
	}
	/**
	 * 人数设置主界面方法 
	 */
	public ActionForward rsszManageFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyRsszForm pjForm = (PjpyRsszForm)form;
		User user = getUser(request);
		
		String rssz = request.getParameter("rssz");
		boolean sfrssz = "否".equalsIgnoreCase(rssz) ? false:true;
		request.setAttribute("sfrssz", sfrssz);
		request.setAttribute("rssz", rssz);
		
		PjpyRsszService rsszService = new PjpyRsszService();
		
		//如果不曾做过人数设置初始化，系统自动初始化。
		if (rsszService.isRsszCsh()){
			rsszService.initRssz();
		}
		
		if(!rsszService.isTbRyqd()){
			String msg = "本次评奖周期尚未执行过“同步部门”和“同步人员”的操作，无法进行人数设置，请在“评奖人员确定”模块进行设置”！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		pjForm.setPjxn(PjxtszModel.pjxtszModel.getPjxn());
		pjForm.setPjnd(PjxtszModel.pjxtszModel.getPjnd());
		pjForm.setPjxq(PjxtszModel.pjxtszModel.getPjxq());
		
		// 能否人数分配权限
		boolean kfsz = false;
		// 人数设置方式
		String rsszfs = PjxtszModel.pjxtszModel.getRsszfs();
		
		if("xx".equalsIgnoreCase(rsszfs)){
			kfsz = "xx".equalsIgnoreCase(user.getUserStatus());
		}else if("xxxy".equalsIgnoreCase(rsszfs)){
			kfsz = "xx".equalsIgnoreCase(user.getUserStatus()) || "xy".equalsIgnoreCase(user.getUserStatus());
		}
		
		setWriteAbleAndTitle(request, "pjpy_xmsz_rssz.do");
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("kfsz", kfsz);
		request.setAttribute("xmxzList", rsszService.getXmxzList()); // 项目性质
		request.setAttribute("xmfwList", rsszService.getXmfwList()); // 项目范围
		request.setAttribute("pjxtszModel", PjxtszModel.pjxtszModel);
		// 可分配人数的评奖项目
		request.setAttribute("pjxmList", rsszService.getXmForRssz());
		request.setAttribute("user", user);
		return mapping.findForward("rsszManageFlow");
	}
	
	/**
	 * 人数设置设置范围
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszForSzfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String szfw = request.getParameter("szfw");
		String flow = request.getParameter("flow");
		PjpyRsszService rsszService = new PjpyRsszService();
		
		//如果不曾做过人数设置初始化，系统自动初始化。
		if (rsszService.isRsszCsh()){
			rsszService.initRssz();
		}
		
		PjpyRsszForm model = (PjpyRsszForm)form;
		List<HashMap<String, String>> topTr = rsszService.getTopTr(szfw); 
		List<String[]> rs = null;
		
		if("xy".equalsIgnoreCase(szfw)){  // 学院设置范围
			rs = rsszService.searchForXy(model);
		}else if("zy".equalsIgnoreCase(szfw)){  // 专业设置范围
			rs = rsszService.searchForZy(model);
		}else if("bj".equalsIgnoreCase(szfw)){  // 班级设置范围
			rs = rsszService.searchForBj(model);
		}else{
			rs = rsszService.searchForNj(model);
		}
		
		// 该项目设置model
		PjpyXmszModel xmszModel = rsszService.getXmszForXmdm(PjxtszModel.pjxtszModel.getPjxn()+PjxtszModel.pjxtszModel.getPjxq()
								  +PjxtszModel.pjxtszModel.getPjnd()+model.getXmdm());
		
		// 查询出的记录中的人数
		Integer count = 0;
		for(String[] record: rs){
			Integer qdrs = StringUtils.isNull(record[record.length-1]) ? 0 : Integer.parseInt(record[record.length-1]);
			count += qdrs;
		}
		
		request.setAttribute("rssz", "true");
		request.setAttribute("count", count);
		request.setAttribute("syrs", rsszService.getSyrs(xmszModel, szfw));	// 剩余人数
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("size", topTr.size()+1);
		request.setAttribute("lastXmdm", model.getXmdm());	// 上一次的查询项目
		request.setAttribute("lastSzfw", model.getSzfw());	// 上一次的设置范围
		if("flow".equals(flow)){
			return rsszManageFlow(mapping, form, request, response);
		}
		return rsszManage(mapping, form, request, response);
	}
	
	
	/**
	 * 批量人数设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		PjpyRsszForm pjForm = (PjpyRsszForm)form;
		
		String szfw = pjForm.getSzfw();
		String xmdm = pjForm.getXmdm();
		
		PjpyRsszService rsszService = new PjpyRsszService();
		
		// 根据设置范围获得设置对象
		List<HashMap<String, Object>> fpdxList = rsszService.getFpdxList(pjForm, user);
		request.setAttribute("fpdxList", fpdxList);
		
//		if("xy".equalsIgnoreCase(szfw)){ //设置范围为学院
//			request.setAttribute("xyList", rsszService.getAllXy(pjForm));
//		}else if("zy".equalsIgnoreCase(szfw)){ // 设置范围为专业
//			
//		}else if("bj".equalsIgnoreCase(szfw)){ // 设这范围为班级
//			List<HashMap<String, Object>> fpdxList = rsszService.getFpdxList(pjForm, user);
//			request.setAttribute("fpdxList", fpdxList);
//		}else{  // 设置范围为年级
//			request.setAttribute("njList", rsszService.getAllNj(pjForm));
//		}
		
		// 获取基本评奖设置
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		pjForm.setPjxn(pjxn);
		pjForm.setPjnd(pjnd);
		pjForm.setPjxq(pjxq);
		
		// 项目设置model
		PjpyXmszModel xmszModel = rsszService.getXmszForXmdm(pjxn+pjxq+pjnd+pjForm.getXmdm());
		// 人数上限
		String rssx = xmszModel.getRssx();
		
		if(StringUtils.isNotNull(rssx)){
			request.setAttribute("rssx", rssx);
		}
		
		FormModleCommon.setNdXnXqList(request);
		
		// 可分配人数的评奖项目
		request.setAttribute("user", user);
		request.setAttribute("szfw", szfw);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("xmszModel", xmszModel);
		request.setAttribute("pjxmList", rsszService.getXmForRssz());
		request.setAttribute("title", "批量人数设置");
		return mapping.findForward("rsszUpdate");
	}
	/**
	 * 批量人数设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		PjpyRsszForm pjForm = (PjpyRsszForm)form;
		
		String xmdm = pjForm.getXmdm();
		
		
		String szfw = pjForm.getSzfw();
		
		
		PjpyRsszService rsszService = new PjpyRsszService();
		
		
		
		// 获取基本评奖设置
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		pjForm.setPjxn(pjxn);
		pjForm.setPjnd(pjnd);
		pjForm.setPjxq(pjxq);
		// 项目设置model
		PjpyXmszModel xmszModel = rsszService.getXmszForXmdm(pjxn+pjxq+pjnd+pjForm.getXmdm());
		szfw = xmszModel.getKzfw();
		pjForm.setSzfw(szfw);
		// 根据设置范围获得设置对象
		List<HashMap<String, Object>> fpdxList = rsszService.getFpdxList(pjForm, user);
		request.setAttribute("fpdxList", fpdxList);
		// 人数上限
		String rssx = xmszModel.getRssx();
		
		
		if(StringUtils.isNotNull(rssx)){
			request.setAttribute("rssx", rssx);
		}
		
		FormModleCommon.setNdXnXqList(request);
		
		// 可分配人数的评奖项目
		request.setAttribute("user", user);
		request.setAttribute("szfw", szfw);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("xmszModel", xmszModel);
		request.setAttribute("pjxmList", rsszService.getXmForRssz());
		request.setAttribute("title", "批量人数设置");
		return mapping.findForward("rsszFlow");
	}
	
	/**
	 * 参数设置，主要设置保留小数位数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyRsszForm pjForm = (PjpyRsszForm)form;
		String doType = request.getParameter("doType");
		
		PjpyRsszService service = new PjpyRsszService();
		
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveCssz(pjForm) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
		
		request.setAttribute("title", "参数设置");
		pjForm.setBlxs(service.getCssz());
		
		// 获取基本评奖设置
		request.setAttribute("jbszxx", PjxtszModel.pjxtszModel);
		return mapping.findForward("rsszCssz");
	}
	
	/**
	 * 保存人数设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveRssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyRsszForm pjForm = (PjpyRsszForm)form;
		PjpyRsszService rsszService = new PjpyRsszService();
		
		String[] pkValues = request.getParameterValues("primarykey_cbv");
		
		boolean result = rsszService.saveRssz(pkValues,pjForm);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		return rsszForSzfw(mapping, pjForm, request, response);
	}
	
	/**
	 * 初始化人数设置
	 */
	public ActionForward initData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyRsszForm model = (PjpyRsszForm) form;
		PjpyRsszService service = new PjpyRsszService();
		
		String xmdm = model.getXmdm();
		boolean result = false;
		
		if (StringUtils.isNotNull(xmdm)){
			//对某个项目初始化人数设置
			result = service.initRsszByXmdm(xmdm);
		} else {
			//人数设置全部初始化
			result = service.initRssz();
		}
		
		response.setContentType("text/html;charset=gbk"); //ajax请求返回数据转码，否则会中文乱码
		response.getWriter().print(result);
		
		return null;
	}
	
	/**
	 * 保存确定人数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveQdrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyRsszForm pjForm = (PjpyRsszForm) form;
		PjpyRsszService servcie = new PjpyRsszService();
		
		String message = servcie.saveQdrs(pjForm) ? "保存成功！" : "保存失败！";
		
		request.setAttribute("message", message);
		return rsszForSzfw(mapping, form, request, response);
	}
	
	/**
	 * 启用默认人数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveQdrsFromMrrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyRsszService service = new PjpyRsszService();
		String[] pkValues = request.getParameterValues("primarykey_cbv");
		String message = service.saveQdrsFromMrrs(pkValues) ? "操作成功！" : "操作失败！";
		
		request.setAttribute("message", message);
		return rsszForSzfw(mapping, form, request, response);
	}
	
	/**
	 * 批量人数设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszFlowUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		PjpyRsszForm pjForm = (PjpyRsszForm)form;
		
		String xmdm = pjForm.getXmdm();
		
		
		String szfw = pjForm.getSzfw();
		
		
		PjpyRsszService rsszService = new PjpyRsszService();
		
		
		
		// 获取基本评奖设置
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		pjForm.setPjxn(pjxn);
		pjForm.setPjnd(pjnd);
		pjForm.setPjxq(pjxq);
		// 项目设置model
		PjpyXmszModel xmszModel = rsszService.getXmszForXmdm(pjxn+pjxq+pjnd+pjForm.getXmdm());
		szfw = xmszModel.getKzfw();
		pjForm.setSzfw(szfw);
		// 根据设置范围获得设置对象
		List<HashMap<String, Object>> fpdxList = rsszService.getFpdxList(pjForm, user);
		request.setAttribute("fpdxList", fpdxList);
		// 人数上限
		String rssx = xmszModel.getRssx();
		
		
		if(StringUtils.isNotNull(rssx)){
			request.setAttribute("rssx", rssx);
		}
		
		FormModleCommon.setNdXnXqList(request);
		
		// 可分配人数的评奖项目
		request.setAttribute("user", user);
		request.setAttribute("szfw", szfw);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("xmszModel", xmszModel);
		request.setAttribute("pjxmList", rsszService.getXmForRssz());
		request.setAttribute("title", "批量人数设置");
		return new ActionForward("/pjpy_ty_sjsz.do?method=sjszUpdate&doType=''",false);
	}
}
