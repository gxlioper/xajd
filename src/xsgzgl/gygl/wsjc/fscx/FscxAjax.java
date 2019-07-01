package xsgzgl.gygl.wsjc.fscx;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

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
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-28 上午11:29:22
 * </p>
 */
public class FscxAjax extends BasicAction {

	/**
	 * 卫生检查，卫生分信息的查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fscxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		FscxForm myForm = (FscxForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gyglnew_wsjc_fscx.do");
		request.setAttribute("path", "gyglnew_wsjc_fscx.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// =================== end ===================
		List<HashMap<String, String>> topTr = service.getTopTr();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getFscxCx(myForm,request);

		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);

		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 卫生分查询 自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward wsfcxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FscxService service = new FscxService();
		FscxForm model = (FscxForm) form;		
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);		
		User user = getUser(request);	
		List<HashMap<String,String>> resultList =  service.getFscxExportCx(model,request,user); 		
		//导出功能代码
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
	 * 卫生检查，对卫生分信息的保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问公寓管理-卫生检查-卫生分查询-修改PK:{guid},FZ:{fz},JCRQ:{jcrq},JCBM:{jcbm},JCRY:{jcry}")
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FscxService service = new FscxService();
		String message = "";
		boolean flag = false;
		FscxForm myForm = (FscxForm) form;
		User user = getUser(request);

		String pkValue = request.getParameter("pkValue");
		String lddm = request.getParameter("lddm");
		String qsh = service.unicode2Gbk(request.getParameter("qsh"));
		// 获取到页面传过来的分值
		String fz = request.getParameter("fz");

		// 获取到页面传过来的等级
		String dj = service.unicode2Gbk(request.getParameter("dj"));
		String jcrq = request.getParameter("jcrq");
		String jcbm = service.unicode2Gbk(request.getParameter("jcbm"));
		String jcry = service.unicode2Gbk(request.getParameter("jcry"));
		String bz = service.unicode2Gbk(request.getParameter("bz"));
		String username = user.getUserName();

		myForm.setLddm(lddm);
		myForm.setQsh(qsh);
		myForm.setDj(fz);
		myForm.setDj(dj);
		myForm.setJcrq(jcrq);
		myForm.setJcbm(jcbm);
		myForm.setJcry(jcry);
		myForm.setBz(bz);

		flag = service.fscxXg(myForm, pkValue, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述: 验证检查日程是否提交
	 * @作者：易江东[工号：781]
	 * @日期：2013-9-2 上午09:31:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward checkJcrcSftj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String message = "";
		boolean flag = false;
		FscxForm myForm = (FscxForm) form;
		User user = getUser(request);
		FscxService service = new FscxService();
		
		flag = service.checkJcrcSftj(myForm, request, user);
		
		if(!flag){
			message = "导出数据中,存在未提交的日程。";
		}
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * @描述: 评定分数等级
	 * @作者：易江东[工号：781]
	 * @日期：2013-9-9 上午08:43:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward pdFsDj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		FscxForm model = (FscxForm) form;		
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);		
		User user = getUser(request);	
		
		String message = "";
		boolean flag = false;
		
		flag = service.pdQsDjXg(model, request, user);
		if (Base.isNull(message)) {
			message = flag ? "评定分数完成" : "没有已提交的日程";
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		
		return null;
	}
	
	
	/**
	 * 卫生分查询 不及格学生名单导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward bjgmdExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FscxService service = new FscxService();
		FscxForm model = (FscxForm) form;		
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);		
		User user = getUser(request);	
		request.setAttribute("path", "gyglnew_wsjc_fscx.do");
		List<HashMap<String,String>> resultList =  service.getbjgmdExportCx(model,request,user); 		
		//导出功能代码
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
	 * 
	 * @描述:温大寝室卫生分个性化导出
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-29 上午10:28:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward wsfDcOfWd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FscxService service = new FscxService();
		FscxForm exporModel = (FscxForm) form;	
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("gyglnew_wsjc_fscx.do");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.wsfDcOfWd(exporModel, request,response.getOutputStream(),user);
		
		// ============= end ============

		return null;
	}
	
	/** 
	 * @描述:周统计导出(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-7 上午11:04:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward zTjDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FscxService service = new FscxService();
		FscxForm exporModel = (FscxForm) form;	
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		
		searchModel.setPath("gyglnew_wsjc_fscx.do");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.zTjdc(exporModel, request,response.getOutputStream(),user);
		
		// ============= end ============

		return null;
	}
	
}