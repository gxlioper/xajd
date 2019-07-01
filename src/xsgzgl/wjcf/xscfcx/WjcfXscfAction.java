package xsgzgl.wjcf.xscfcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

import xgxt.base.DealString;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.String.StringUtils;
import xsgzgl.rcsw.zjbb.RcswZjbbDAO;
import xsgzgl.wjcf.cfsjwh.WjcfCfsjwhActionForm;
import xsgzgl.wjcf.cfsjwh.WjcfCfsjwhService;
import xsgzgl.wjcf.cfssgl.WjcfCfssglServices;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.WjcfGeneralService;
import xsgzgl.wjcf.general.cfjcgl.WjcfCfjcshService;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.wjcf.general.inter.WjcfCfjcshInterface;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;
/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ltt
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-17 下午12:36:30
 * </p>
 */
public class WjcfXscfAction extends BasicAction {

	/**
	 * 学生处分查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscfCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfXscfService service = new WjcfXscfService();
		User user = getUser(request);
		
		if ("stu".equalsIgnoreCase(user.getUserType())
				|| "student".equalsIgnoreCase(user.getUserType())) {
			RequestForm rForm = new RequestForm();
			// ----------------设置PATH begin-----------------------
			// ----------------显示title，判断读写权----------------
			rForm.setPath("xscfCx.do");
			// ----------------设置PATH end-----------------------
			service.setRequestValue(rForm, user, request);
			
		} else {
			String msg = "本模块只能由<font color='blue'>学生用户</font>进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 处分数据查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscfCxAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfXscfService service = new WjcfXscfService();
		WjcfXscfActionForm myForm = (WjcfXscfActionForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		myForm.setXh(user.getUserName());
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		service.initCfcxManage(rForm, request);
		myForm.getSearchModel().setPath("xscfCx.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// =================== end ===================
		List<HashMap<String, String>> topTr = service.getTopTr();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.xscfCx(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 学生处分查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscfCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfXscfActionForm myForm = (WjcfXscfActionForm) form;
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		
		//查询处分情况
		HashMap<String, String> rs = service.cfsjwhCk(myForm.getCfid());
		String pkValue = myForm.getCfid();
		String sswh = rs.get("sswh");
		String jcwh = rs.get("jcwh");
		
		if (StringUtils.isNotNull(sswh)) {
			WjcfCfssglServices ssservice=new WjcfCfssglServices();
			request.setAttribute("rs", ssservice.cfssglCk(pkValue));
			request.setAttribute("pkValue", pkValue);
			request.setAttribute("cfshxxList", ssservice.ssshxxCk(pkValue));
			return mapping.findForward("cfsswhCk");
		} else if (StringUtils.isNotNull(jcwh)) {
			XsxxglService stuService = new XsxxglService();
			String cfId = pkValue;
			WjcfCfjcshService wjcfcfjcshService = new WjcfCfjcshService();

			List<HashMap<String, String>> cfsh = wjcfcfjcshService.getCfjcshxxList(cfId);
			request.setAttribute("cfshList", cfsh);

			HashMap<String, String> cfsbMap = wjcfcfjcshService.getCfxx(cfId);// 处分数据

			HashMap<String, String> cfjcMap = wjcfcfjcshService.getOnesCfjc(cfId);// 处分解除数据
			
			HashMap<String, String> cfssMap = wjcfcfjcshService.getOnesCfss(cfId);// 处分申述数据
			
			if (null != cfsbMap) {
				request.setAttribute("rs", stuService.selectStuinfo(cfsbMap
						.get("xh")));
			}
			request.setAttribute("wjsb", cfsbMap);
			request.setAttribute("cfjc", cfjcMap);
			request.setAttribute("cfss", cfssMap);
			return mapping.findForward("ckCfjc");
		} else {
			request.setAttribute("rs", rs);
		}
		return mapping.findForward("xscfCk");
	}
	

	/**
	 * 学生处分申诉
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsssSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfXscfActionForm myForm = (WjcfXscfActionForm) form;
		WjcfXscfService service = new WjcfXscfService();
		WjcfCfssglServices ssglService = new WjcfCfssglServices();
		myForm.setSqsj(DealString.getDateTime());
		boolean flag = service.xsssSave(myForm);
		
		if (flag){
			//======待办工作推送=2013-1-15==qph==begin=========
			String xh = myForm.getUserName();
			String[] spgw = ssglService.getSsshSpgw();
			String id = myForm.getCfid();
			
			if (null != spgw && spgw.length > 0){
				Job job = Job.getJobInstance(id, xh, spgw[0], 
						"cfssshCx.do?xtgwid="+spgw[0], 
						"cfsswhCx.do","处分申诉", "处分申诉");
				MyJobsManager manager = new MyJobsImpl();
				manager.pushJob(job);
				
			}
			//======待办工作推送=2013-1-15==qph==end===========
		}
		
		request.setAttribute("isSuccess", flag==true?"true":"false");
		request.setAttribute("message",flag?"操作成功！":"操作失败！");
		
		xscfCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
	/**
	 * 学生处分解除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjcSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfXscfActionForm myForm = (WjcfXscfActionForm) form;
		WjcfXscfService service = new WjcfXscfService();
		myForm.setSqsj(DealString.getDateTime());
		boolean flag = service.cfjcSave(myForm);
		request.setAttribute("isSuccess", flag==true?"true":"false");
		
		request.setAttribute("message",flag?"操作成功！":"操作失败！");
		xscfCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
	/**
	 * 处分申诉取消
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsssQx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfXscfActionForm myForm = (WjcfXscfActionForm) form;
		WjcfXscfService service = new WjcfXscfService();
		boolean flag = service.xsssCx(myForm);
		request.setAttribute("isSuccess", flag==true?"true":"false");
		
		request.setAttribute("message",flag?"操作成功！":"操作失败！");
		xscfCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
	/**
	 * 处分解除取消
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscfjcQx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfXscfActionForm myForm = (WjcfXscfActionForm) form;
		WjcfXscfService service = new WjcfXscfService();
		boolean flag = service.jcsqCx(myForm);
		request.setAttribute("isSuccess", flag==true?"true":"false");
		
		request.setAttribute("message",flag?"操作成功！":"操作失败！");
		xscfCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
}
