package xsgzgl.wjcf.general.cfsbgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.WjcfGeneralService;
import xsgzgl.wjcf.general.inter.WjcfCfsbInterface;
import xsgzgl.wjcf.general.inter.WjcfCfshInterface;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;

/**
 * 
 * 
 * 类名称：WjcfCfsbAction 
 * 类描述：违纪处分处分审核Action 
 * 创建人：xucy 
 * 创建时间：2012-7-11 下午01:18:00
 * 修改备注：
 * 
 * @version
 * 
 */
public class WjcfCfshAction extends BasicAction {

	/**
	 * 违纪处分 处分审核查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchWjcfResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfsbInit init = new WjcfCfsbInit();
		WjcfCfshModel model = new WjcfCfshModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initCfsh(rForm, myForm, user, request);
		WjcfCfshInterface service = myService.getWjcfCfshService(myForm);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);

		String cflbdm = otherValue[1];

		String spgw = otherValue[2];

		String stylePath = otherValue[3];
		myForm.setCflbdm(cflbdm);

		myForm.setSpgwId(spgw);
		rsModel.setStylePath(stylePath);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getWjcfCfshTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getWjcfCfshList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createWjcfCfshHTML(rsModel, model, rsArrList,
				user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================
		return null;
	}

	// 审核
	public ActionForward cfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxglService stuService = new XsxxglService();

		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		String cfId = request.getParameter("cfId");
		WjcfCfsbInterface wjcfcfsbService = myService
				.getWjcfCfsbService(myForm);
		WjcfCfshInterface wjcfcfshService = myService
				.getWjcfCfshService(myForm);
		User user = getUser(request);// 用户对象
		String cflbdm = request.getParameter("cflbdm");
		String spgw = request.getParameter("spgw");
		myForm.setCflbdm(cflbdm);
		myForm.setSpgwId(spgw);
		myForm.setCfId(cfId);

		HashMap<String, String> upSpgwmap = wjcfcfshService.getHigherUpSpMap(
				myForm, user);// 上级审批岗位
		boolean sfDyj = false;
		if (null == upSpgwmap || upSpgwmap.size() == 0) {// 如果没有上级审批岗位说明为第一级审批不能退回操作
			sfDyj = true;
		}

		HashMap<String, String> maxSpgwmap = wjcfcfshService.getMaxSpgw(myForm,
				user);// 最高级审批岗位
		boolean sfZgj = false;
		if (spgw.equals(maxSpgwmap.get("spgw"))) {
			sfZgj = true;
		}

		List<HashMap<String, String>> cfsh = wjcfcfshService.getCfshxx(cfId,spgw);
		request.setAttribute("cfshList", cfsh);

		HashMap<String, String> cfsbMap = wjcfcfsbService.getCfsb(cfId);// 处分上报数据
		HashMap<String, String> cfshMap = wjcfcfshService.getOnesCfsh(cfId,spgw);// 处分审核数据

		request.setAttribute("sfDyj", sfDyj);// 是否第一级
		request.setAttribute("sfZgj", sfZgj);// 是否最高级
		request.setAttribute("spgw", spgw);
		request.setAttribute("cflbdm", cflbdm);
		request.setAttribute("wjsb", cfsbMap);
		request.setAttribute("cfsh", cfshMap);

		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());// 违纪处分类别
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());// 违纪处分原因
		if (null != cfsbMap) {
			request.setAttribute("rs", stuService.selectStuinfo(cfsbMap
					.get("xh")));
			request.setAttribute("yscfqkList", wjcfcfsbService
					.getYscfqk(cfsbMap.get("xh")));// 已受违纪处分
		}
		return mapping.findForward("cfshZj");
	}

	// 弹出岗位层
	public ActionForward showShgwDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		WjcfCfshInterface service = myService.getWjcfCfshService(myForm);

		String cflbdm = request.getParameter("cflbdm");

		// 消息信息
		String message = "";

		// 保存数据方法

		if (Base.isNull(message)) {

			service.showShgwDiv(cflbdm, user, response);

		}

		return null;
	}

	// 保存审核
	public ActionForward saveCfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfsbService cfsbService = new WjcfCfsbService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfshInterface wjcfcfshService = myService
				.getWjcfCfshService(myForm);
		User user = getUser(request);// 用户对象
		String doType = request.getParameter("doType");
		boolean tgcz = false;
		if ("tg".equals(doType)) {
			myForm.setShzt("tg");
			tgcz=true;
		}
		if ("btg".equals(doType)) {
			myForm.setShzt("btg");
		}
		if ("th".equals(doType)) {
			myForm.setShzt("th");
		}
		//转码
		if(null!=myForm.getShyj()){
			myForm.setShyj(myService.unicode2Gbk(myForm.getShyj()));
		}
		if(null!=myForm.getCfwh()){
			myForm.setCfwh(myService.unicode2Gbk(myForm.getCfwh()));
		}
		boolean flag = wjcfcfshService.saveCfsh(myForm, user);
		
		if (flag){
			//===待办工作=====2013-1-15 qph==beign======
			String[] id = new String[]{myForm.getCfId()};
			String curShgw = myForm.getSpgwId();
			String[] spgw = cfsbService.getSpgwByCflb(myForm.getCflbdm());
			int index = StringUtils.getStrIndexInArray(curShgw, spgw);
			
			for (String str : id){
				Job job = null;
				if ("tg".equals(myForm.getShzt())) {
					String nextShgw = index!=spgw.length-1 && spgw[index+1] != null ? spgw[index+1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"general_wjcf.do?method=cfshCx&xmdm="+myForm.getCflbdm()+"&spgw="+nextShgw,"处分上报");
				} else if ("th".equals(myForm.getShzt())){
					String nextShgw = index!=0 ? spgw[index-1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"general_wjcf.do?method=cfshCx&xmdm="+myForm.getCflbdm()+"&spgw="+nextShgw,"处分上报");
				} else {
					job = Job.getJobInstance(str,"处分上报");
				}
				MyJobsManager manager = new MyJobsImpl();
				manager.updateJob(job);
			}
			//===待办工作=====2013-1-15 qph==end=========
		}
		
		HashMap<String, String> maxSpgwmap = wjcfcfshService.getMaxSpgw(myForm,
				user);// 最高级审批岗位
		boolean sfZgj = false;
		if (myForm.getSpgwId().equals(maxSpgwmap.get("spgw"))) {
			sfZgj = true;
		}
		request.setAttribute("message", flag);
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(tgcz&&sfZgj);
		
		return null;
	}

	/**
	 * 批量审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plCfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfshInterface wjcfcfshService = myService
				.getWjcfCfshService(myForm);
		User user = getUser(request);// 用户对象
		String cflbdm = request.getParameter("cflbdm");
		String spgw = request.getParameter("spgw");
		String cfId = request.getParameter("cfId");
		myForm.setCflbdm(cflbdm);
		myForm.setSpgwId(spgw);

		HashMap<String, String> upSpgwmap = wjcfcfshService.getHigherUpSpMap(
				myForm, user);// 上级审批岗位
		boolean sfDyj = false;
		if (null == upSpgwmap || upSpgwmap.size() == 0) {// 如果没有上级审批岗位说明为第一级审批不能退回操作
			sfDyj = true;
		}

		HashMap<String, String> maxSpgwmap = wjcfcfshService.getMaxSpgw(myForm,
				user);// 最高级审批岗位
		boolean sfZgj = false;
		if (spgw.equals(maxSpgwmap.get("spgw"))) {
			sfZgj = true;
		}

		request.setAttribute("sfDyj", sfDyj);// 是否第一级
		request.setAttribute("sfZgj", sfZgj);// 是否最高级
		request.setAttribute("spgw", spgw);
		request.setAttribute("cfId", cfId);
		request.setAttribute("cflbdm", cflbdm);

		return mapping.findForward("plCfsh");
	}

	/**
	 * 批量保存审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plSaveCfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfsbService cfsbService = new WjcfCfsbService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfshInterface wjcfcfshService = myService
				.getWjcfCfshService(myForm);
		User user = getUser(request);// 用户对象
		String doType = request.getParameter("doType");
		boolean tgcz = false;
		if ("tg".equals(doType)) {
			myForm.setShzt("tg");
			tgcz= true;
		}
		if ("btg".equals(doType)) {
			myForm.setShzt("btg");
		}
		if ("th".equals(doType)) {
			myForm.setShzt("th");
		}

		String cflbdm = request.getParameter("cflbdm");
		String spgw = myForm.getSpgwId();
		String cfId = myForm.getCfId();
		myForm.setCflbdm(cflbdm);
		myForm.setSpgwId(spgw);

		HashMap<String, String> upSpgwmap = wjcfcfshService.getHigherUpSpMap(
				myForm, user);// 上级审批岗位
		boolean sfDyj = false;
		if (null == upSpgwmap || upSpgwmap.size() == 0) {// 如果没有上级审批岗位说明为第一级审批不能退回操作
			sfDyj = true;
		}

		HashMap<String, String> maxSpgwmap = wjcfcfshService.getMaxSpgw(myForm,
				user);// 最高级审批岗位
		boolean sfZgj = false;
		if (spgw.equals(maxSpgwmap.get("spgw"))) {
			sfZgj = true;
		}
		//转码
		if(null!=myForm.getShyj()){
			myForm.setShyj(myService.unicode2Gbk(myForm.getShyj()));
		}
		if(null!=myForm.getCfwh()){
			myForm.setCfwh(myService.unicode2Gbk(myForm.getCfwh()));
		}
		boolean flag = wjcfcfshService.saveCfsh(myForm, user);
		
		if (flag){
			//===待办工作=====2013-1-15 qph==beign======
			String[] id = myForm.getCfId().split(",");
			String curShgw = myForm.getSpgwId();
			String[] spgwArr = cfsbService.getSpgwByCflb(myForm.getCflbdm());
			int index = StringUtils.getStrIndexInArray(curShgw, spgwArr);
			
			for (String str : id){
				Job job = null;
				if ("tg".equals(myForm.getShzt())) {
					String nextShgw = index!=spgwArr.length-1 && spgwArr[index+1] != null ? spgwArr[index+1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"general_wjcf.do?method=cfshCx&xmdm="+myForm.getCflbdm()+"&spgw="+nextShgw,"处分上报");
				} else if ("th".equals(myForm.getShzt())){
					String nextShgw = index!=0 ? spgwArr[index-1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"general_wjcf.do?method=cfshCx&xmdm="+myForm.getCflbdm()+"&spgw="+nextShgw,"处分上报");
				}  else {
					job = Job.getJobInstance(str,"处分上报");
				}
				MyJobsManager manager = new MyJobsImpl();
				manager.updateJob(job);
			}
			//===待办工作=====2013-1-15 qph==end=========
		}
		
		request.setAttribute("message", flag == true ? "操作成功" : "操作失败");

		request.setAttribute("sfDyj", sfDyj);// 是否第一级
		request.setAttribute("sfZgj", sfZgj);// 是否最高级
		request.setAttribute("spgw", spgw);
		request.setAttribute("cfId", cfId);
		request.setAttribute("cflbdm", myForm.getCflbdm());
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(tgcz&&sfZgj);
		
		return null;
	}

	/**
	 * 提交时统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tongJi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfshInterface wjcfcfshService = myService
				.getWjcfCfshService(myForm);

		request.setAttribute("tongjiList", wjcfcfshService.tongjiList());
		return mapping.findForward("tongJi");
	}

	/**
	 * 提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfshInterface wjcfcfshService = myService
				.getWjcfCfshService(myForm);

		boolean flag = wjcfcfshService.tjSh();
		request.setAttribute("message", flag == true ? "操作成功" : "操作失败");
		request.setAttribute("tongjiList", wjcfcfshService.tongjiList());
		return mapping.findForward("tongJi");
	}

	/**
	 * 提交(直接提交)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjtjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfshInterface wjcfcfshService = myService
				.getWjcfCfshService(myForm);

		boolean flag = wjcfcfshService.zjtjSh(myForm);
		request.setAttribute("message", flag == true ? "操作成功" : "操作失败");
		return null;
	}
	
}
