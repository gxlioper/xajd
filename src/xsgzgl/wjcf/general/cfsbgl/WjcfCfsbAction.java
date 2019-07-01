package xsgzgl.wjcf.general.cfsbgl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Pages;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.WjcfGeneralService;
import xsgzgl.wjcf.general.inter.WjcfCfsbInterface;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * 
* 
* 类名称：WjcfCfsbAction 
* 类描述：违纪处分处分上报Action
* 创建人：xucy 
* 创建时间：2012-6-20 下午01:18:00 
* 修改备注：  
* @version 
*
 */
public class WjcfCfsbAction extends BasicAction {


	/**
	 * 违纪处分  处分上报查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchWjcfResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfGeneralService myService = new WjcfGeneralService();
		
		WjcfCfsbInit init = new WjcfCfsbInit();
		WjcfCfsbModel model = new WjcfCfsbModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initCfsb(rForm, myForm, user, request);
		WjcfCfsbInterface service = myService.getWjcfCfsbService(myForm);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE版本
		String ie = otherValue[0];
		String stylePath = otherValue[1];
		rsModel.setIe(ie);
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
		List<HashMap<String, String>> topTr = service.getWjcfCfsbTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getWjcfCfsbfList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createWjcfCfsbHTML(rsModel, model, rsArrList,
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
	
	
	/**
	 * 自定义导出处分类别代码维护数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward cfsbExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralForm model = (WjcfGeneralForm) form;
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfsbInterface service = myService.getWjcfCfsbService(model);
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList = service.getWjcfCfsbfExportList(model,user);
		
		//List<String[]> rsList = service.cflbdmCx(model);
		
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 增加处分上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjWjcf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh=request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		request.setAttribute("rs", stuService.selectStuinfo(xh));
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());//违纪处分类别
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());//违纪处分原因
		request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(xh));//已受违纪处分
		return mapping.findForward("cfsbZj");
		
	}
	
	/**
	 * 保存处分上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh=request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		User user = getUser(request);// 用户对象
		String cfid = wjcfcfsbService.saveCfsb(myForm,user);
		
		if (!StringUtil.isNull(cfid)){
			//=======待办工作推送===2013-1-15==qph===begin============
			String cflbdm = myForm.getCflbdm();
			String[] spgw = wjcfcfsbService.getSpgwByCflb(cflbdm);
			
			if (null != spgw && spgw.length > 0){
				HashMap<String, String> map = wjcfcfsbService.getCfsb(cfid);
				
				Job job = Job.getJobInstance(cfid, xh, spgw[0], 
						"general_wjcf.do?method=cfshCx&xmdm="+cflbdm+"&spgw="+spgw[0], 
						"xscfCx.do","处分上报", map.get("cflbmc"));
				MyJobsManager manager = new MyJobsImpl();
				manager.pushJob(job);
			}
			//=======待办工作推送===2013-1-15==qph===end==============
		}
		
		request.setAttribute("isSuccess", !StringUtil.isNull(cfid) ? "true":"false");
		request.setAttribute("rs", stuService.selectStuinfo(xh));
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());//违纪处分类别
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());//违纪处分原因
		
		request.setAttribute("message",!StringUtil.isNull(cfid) ? "操作成功！":"操作失败！");
		return mapping.findForward("cfsbZj");
	}
	
	/**
	 * 修改处分上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xgCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService stuService = new XsxxglService();
		
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		String cfId = request.getParameter("cfId");
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		HashMap<String, String> map = wjcfcfsbService.getCfsb(cfId);
		request.setAttribute("wjsb", map);
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());//违纪处分类别
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());//违纪处分原因
		if(null!=map){
			request.setAttribute("rs", stuService.selectStuinfo(map.get("xh")));
			request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(map.get("xh")));//已受违纪处分
		}
		return mapping.findForward("cfsbXg");
	}
	
	/**
	 * 修改保存处分上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService stuService = new XsxxglService();
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		User user = getUser(request);// 用户对象
		String cflbdmXt = request.getParameter("flag");
		boolean flag = wjcfcfsbService.updateCfsb(myForm,user,cflbdmXt);
		HashMap<String, String> map = wjcfcfsbService.getCfsb(myForm.getCfId());
		
		if (flag){
			//========待办工作==2013-1-15==qph====begin===============
			String cflbdm = myForm.getCflbdm();
			String[] spgw = wjcfcfsbService.getSpgwByCflb(cflbdm);
			
			if (null != spgw && spgw.length > 0){
				Job job = Job.getJobInstance(myForm.getCfId(), map.get("xh"), spgw[0], 
						"general_wjcf.do?method=cfshCx&xmdm="+cflbdm+"&spgw="+spgw[0], 
						"xscfCx.do","处分上报", map.get("cflbmc"));
				MyJobsManager manager = new MyJobsImpl();
				
				manager.removeJob(new String[]{myForm.getCfId()}, "处分上报");
				manager.pushJob(job);
			}
			//========待办工作==2013-1-15==qph====end=================
		}
		
		request.setAttribute("wjsb", map);
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());//违纪处分类别
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());//违纪处分原因
		if(null!=map){
			request.setAttribute("rs", stuService.selectStuinfo(map.get("xh")));
			request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(map.get("xh")));//已受违纪处分
		}
		request.setAttribute("message",flag?"操作成功！":"操作失败！");
		return mapping.findForward("cfsbXg");
	}
	
	/**
	 * 查看处分上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ckCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService stuService = new XsxxglService();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		String cfId = request.getParameter("cfId");
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		HashMap<String, String> map = wjcfcfsbService.getCfsb(cfId);
		List<HashMap<String, String>> cfsh = wjcfcfsbService.getCfshxxList(cfId);
		request.setAttribute("wjsb", map);
		request.setAttribute("cfshList", cfsh);
		if(null!=map){
			request.setAttribute("rs", stuService.selectStuinfo(map.get("xh")));
			request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(map.get("xh")));//已受违纪处分
		}
		return mapping.findForward("cfsbCk");
	}
	
	/**
	 * 删除处分上报查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward scCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		boolean flag = wjcfcfsbService.delWjsb(myForm.getPrimarykey_checkVal());
			
		if (flag){
			//===待办工作 ===2013-1-15=====qph=====begin========
			MyJobsManager manager = new MyJobsImpl();
			manager.removeJob(myForm.getPrimarykey_checkVal(), "处分上报");
			//===待办工作 ===2013-1-15=====qph=====end==========
		}
		
		
		String message =flag?"操作成功！":"操作失败！";
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 修改，删除时检测处分上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		String message = wjcfcfsbService.checkCfsb(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 结果导出
	 */
	public ActionForward expCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		WjcfCfsbModel model = new WjcfCfsbModel();
		User user = getUser(request);// 用户对象
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		String[] title = new String[]{"PK","学年", "学期","学号", "姓名", "处分类别","处分原因","处分文号","处分时间","上报人","审核结果"};
		List<String[]> rs = wjcfcfsbService.getWjcfCfsbfList(myForm, model, user);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, title, title, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * 附件下载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fjxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		byte b[] = new byte[500];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(myForm.getFjmc()));
		InputStream in = wjcfcfsbService.fjCx(myForm);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
	}
		
}
