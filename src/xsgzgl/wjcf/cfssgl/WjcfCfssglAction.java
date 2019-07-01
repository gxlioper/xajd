package xsgzgl.wjcf.cfssgl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * 
* 
* 类名称：WjcfJcszAction 
* 类描述：违纪处分申诉管理Action
* 创建人：yijd 
* 创建时间：2012-6-19 上午09:20:00 
* 修改备注：  
* @version 
*
 */
public class WjcfCfssglAction extends BasicAction {
	
	/**
	 * 违纪处分  处分申诉维护查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfsswhCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();
		
		service.initPage(rForm, model, user, request);
		//页面跳转地址
		request.setAttribute("path", "cfsswhCx.do");
		
		// ============= 设置request的值 =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		return mapping.findForward("success");
	}
	
	/**
	 * 违纪处分申诉异步数据  查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfsswhCxSj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		


		// ============= 初始化各变量的值 ============
		service.initPage(rForm, model, user, request);
		//页面跳转地址
		request.setAttribute("path", "cfsswhCx.do");
		//WjcfCfsbInterface service = myService.getWjcfCfsbService(myForm);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setPath("cfsswhCx.do");
		model.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getTopTr(model, user,"sscx");
		// 结果集
		List<String[]> rsArrList = service.cfssglCx(model,user);
		// 构建结果集
		String html = service.createTableHTML(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList((ArrayList<String[]>)rsArrList);
		rsModel.setSpHtml(html);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================
		
		return null;
	}
	
	/**
	 * 处分申诉维护 自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward cfsswhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);

		List<HashMap<String,String>> resultList = service.cfssglCxExport(model,user);				
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
	 * 违纪处分  处分申诉维护查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfsswhCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		RequestForm rForm = new RequestForm();
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "cfsswhCx.do");
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("rs", service.cfssglCk(pkValue));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("cfshxxList", service.ssshxxCk(pkValue));
		// ============= 设置request的值 =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		return mapping.findForward("view");
	}
	
	/**
	 * 违纪处分  处分申诉增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfsswhZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
	
		if("save".equals(doType)){
			boolean result = false;
			
			 result=service.cfssglGx(model);
			
			 if (result){
				 //=====待办工作推送==2013-1-16==qph=====begin========
				String id = model.getCfid();
				HashMap<String,String> map = service.cfssglCk(id);
				String xh = map.get("xh");
				String[] spgw = service.getSsshSpgw();
				if (null != spgw && spgw.length > 0){
					Job job = Job.getJobInstance(id, xh, spgw[0], 
							"cfssshCx.do?xtgwid="+spgw[0], 
							"cfsswhCx.do","处分申诉", "处分申诉");
					MyJobsManager manager = new MyJobsImpl();
					manager.pushJob(job);
					
				}
				 //=====待办工作推送==2013-1-16==qph=====end==========
			 }
			 
			request.setAttribute("result",result);
		}
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("cfid", model.getCfid());
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "cfsswhCx.do");
		FormModleCommon.commonRequestSet(request);
		// ============= 设置request的值 =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		
		return mapping.findForward("add");
	}
	
	
	/**
	 * 违纪处分  处分申诉修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfsswhXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		request.setAttribute("cfid",model.getCfid());
		if("save".equals(doType)){
			boolean result = false;
			
			 result=service.cfssglGx(model);
			 
			request.setAttribute("result",result);
		}

		request.setAttribute("rs", service.cfssglCk(model.getCfid()));
		return mapping.findForward("edit");
	}
	
	
	/**
	 * 查询处分申述
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxCfsswh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm)form;
		
		String cfid = request.getParameter("cfid");
		model.setCfid(cfid);
		HashMap<String, String> result = service.cxCfsswh(model);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(JSONArray.fromObject(result));
		return null;
	}
	
	
	/**
	 * 违纪处分 处分申诉附件下载
	 * 
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssfjXz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm)form;
		//response.setHeader("Content-Disposition", "attachment;filename=aa.doc");
//		response.setContentType("application/msword");
//		service.cfssfjXz(model, response.getOutputStream());
//		return mapping.findForward("");
		byte b[] = new byte[500];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(model.getSsfjmc()));
		InputStream in = service.fjCx(model);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
	}
	
	/**
	 * 违纪处分 处分附件下载
	 * 
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cffjXz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm)form;
		byte b[] = new byte[500];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(model.getSsfjmc()));
		InputStream in = service.cffjCx(model);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
		
	}
	
	/**
	 * 结果导出
	 */
	public ActionForward expCfss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		model.getPages().setPageSize(Integer.MAX_VALUE);
		User user = getUser(request);// 用户对象
		String[] ColumnNameCN=new String[]{"学号","姓名","学年","学期","处分类别名称","处分原因名称","申诉文号","申诉时间","申诉审核状态"};
		String[] ColumnName=new String[]{"xh","xm","xn","xq","cflbmc","cfyymc","sswh","sssj","ssshzt"};
		List<String[]> rs = service.cfssglCx(model,user);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, ColumnName, ColumnNameCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * 违纪处分撤销
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssglSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		
		boolean result=service.cfssSc(pkValue);
		
		if(result){
			//====待办工作==2013-1-16===qph====begin======
			MyJobsManager manager = new MyJobsImpl();
			manager.removeJob(new String[]{pkValue}, "处分申诉");
			//====待办工作==2013-1-16===qph====end========
		}
		
		request.setAttribute("result", result);//保存操作结果
		
		cfsswhCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
	
	/**
	 * 违纪处分申诉审核 列表  页面加载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssshCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();
		List<HashMap<String, String>> spgwList=service.spgwCx(user.getUserName());
		//页面跳转地址
		request.setAttribute("path", "cfssshCx.do");
		service.initPage(rForm, model, user, request);
		request.setAttribute("spgwList", spgwList);
		request.setAttribute("isZgjyh", service.isZgjyh(user));
		if(null==spgwList||spgwList.size()==0){
				String msg = "本模块没有该用户的审批岗位，请确认！";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
		}else{
			// ============= 设置request的值 =============
			CommForm commModel = new CommForm();
			service.setRequestValue(rForm, request);
			service.setList(commModel, rForm, request);
			return mapping.findForward("success");
		}
	}
	
	/**
	 * 违纪处分申诉审核 列表  数据加载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssshCxSj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		


		// ============= 初始化各变量的值 ============
		service.initPage(rForm, model, user, request);
		//页面跳转地址
		request.setAttribute("path", "cfssshCx.do");
		//WjcfCfsbInterface service = myService.getWjcfCfsbService(myForm);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		String stylePath=otherValue[1];
		rsModel.setStylePath(stylePath);
		//审批流权限
		if(otherValue.length >= 3){
			model.setXtgwid(otherValue[2]);
		}
		
		
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setPath("cfssshCx.do");
		model.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getTopTr(model, user,"ssshcx");
		// 结果集
		List<String[]> rsArrList = service.cfssshCx(model,user);
		// 构建结果集
		String html = service.createHTMLCfssshCx(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList((ArrayList<String[]>)rsArrList);
		rsModel.setSpHtml(html);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================
		
		return null;
	}
	
	
	/**
	 * 审批岗位查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward spgwCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();
		
		service.initPage(rForm, model, user, request);
		service.showShgwDiv( user, response);
		//request.setAttribute("spgwList", service.spgwCx(user.getUserName()));
		//页面跳转地址
		request.setAttribute("path", "cfssshCx.do");
		return null;
	}
	
	
	/**
	 *	申诉审核 单个
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssshSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		String xtgwid = Base.chgNull(request.getParameter("xtgwid"), "", 0);
		
		//查询信息依据
		model.setCfid(pkValue);
		model.setXtgwid(xtgwid);
		//审核流程信息begin
		HashMap<String, String> sjSp=service.splcSjsh(model);
		HashMap<String, String> xjSp=service.splcXjsh(model);
		HashMap<String, String> yjSp=service.splyjCx(model);
		HashMap<String, String> djSp=service.spldjCx(model);
		HashMap<String, String> dqSp=service.ssshCkGjCfidGwjb(model);
		request.setAttribute("splcDjRs", djSp);//审批流程顶级
		request.setAttribute("splcYjRs", yjSp);//审批流程一级
		request.setAttribute("splcSjRs", sjSp);//审批流程上级
		request.setAttribute("splcXjRs", xjSp);//审批流程下级
		//审核流程信息end
		
		request.setAttribute("ssshList", service.ssshCxGjCfid(model));
		request.setAttribute("rsSh", dqSp);
		request.setAttribute("spgwqx", service.spgwQx(sjSp, xjSp, yjSp, djSp, dqSp));
		
		request.setAttribute("rs", service.cfssglCk(pkValue));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xtgwid", xtgwid);
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "cfssshCx.do");
		// ============= 设置request的值 =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		return mapping.findForward("cfssshSh");
		
	}
	

	/**
	 *	保存申诉审核 单个
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bccfssshSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		User user=getUser(request);
		boolean sfZgj = false;
		if("save".equals(doType)){
			//保存单个申诉审核
			
			if(null!=model.getShyj()){//转码
				model.setShyj(service.unicode2Gbk(model.getShyj()));
			}
			if(null!=model.getCfggw()&&!"undefined".equals(model.getCfggw())){//转码
				model.setCfggw(service.unicode2Gbk(model.getCfggw()));
			}
			if(null!=model.getSswh()&&null!=model.getSssj()){
				model.setSswh(service.unicode2Gbk(model.getSswh()));
				sfZgj = true;
			}
			model.setShr(user.getUserName());
			model.setSftj("0");//一般审核是不提交的
			boolean result=service.ssshXg(model);
			
			if (result){
				//====待办工作===2013-1-16==qph===begin========
				String[] id = new String[]{model.getCfid()};
				String curShgw = model.getXtgwid();
				String[] spgw = service.getSsshSpgw();
				int index = StringUtils.getStrIndexInArray(curShgw, spgw);
				
				for (String str : id){
					Job job = null;
					if ("tg".equals(model.getShzt())) {
						String nextShgw = index!=spgw.length-1 && spgw[index+1] != null ? spgw[index+1] : null;
						job = Job.getJobInstance(str,nextShgw,
								"cfssshCx.do?xtgwid="+nextShgw,"处分申诉");
					} else if ("th".equals(model.getShzt())){
						String nextShgw = index!=0 ? spgw[index-1] : null;
						job = Job.getJobInstance(str,nextShgw,
								"cfssshCx.do?xtgwid="+nextShgw,"处分申诉");
					} else {
						job = Job.getJobInstance(str,"处分申诉");
					}
					MyJobsManager manager = new MyJobsImpl();
					manager.updateJob(job);
				}
				//====待办工作===2013-1-16==qph===end==========
			}
			
			
			
			request.setAttribute("result", result);
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(sfZgj);
		
		return null;
	}
	
	/**
	 *	申诉审核  批量
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssshPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		String xtgwid = Base.chgNull(request.getParameter("xtgwid"), "", 0);
		
		String[] pks=null;
		if(pkValue!=null && !"".equals(pkValue)){
			pks=pkValue.split("@@");
		}
		//查询信息依据
		//model.setCfid(pkValue);
		model.setCfid(pks[0]);
		model.setXtgwid(xtgwid);
		//审核流程信息begin
		HashMap<String, String> sjSp=service.splcSjsh(model);
		HashMap<String, String> xjSp=service.splcXjsh(model);
		HashMap<String, String> yjSp=service.splyjCx(model);
		HashMap<String, String> djSp=service.spldjCx(model);
		HashMap<String, String> dqSp=service.ssshCkGjCfidGwjb(model);
		request.setAttribute("splcDjRs", djSp);//审批流程顶级
		request.setAttribute("splcYjRs", yjSp);//审批流程一级
		request.setAttribute("splcSjRs", sjSp);//审批流程上级
		request.setAttribute("splcXjRs", xjSp);//审批流程下级
		//审核流程信息end
		
		//request.setAttribute("ssshList", service.ssshCxGjCfid(model));
		request.setAttribute("rsSh", dqSp);
		request.setAttribute("spgwqx", service.spgwQx(sjSp, xjSp, yjSp, djSp, dqSp));
		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xtgwid", xtgwid);
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "cfssshCx.do");
		// ============= 设置request的值 =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		return mapping.findForward("cfssshPlsh");
	}
	
	/**
	 *	申诉审核  批量
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bccfssshPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfCfssglForm model=(WjcfCfssglForm) form;
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		User user=getUser(request);
		boolean sfZgj = false;
			if(null!=model.getSswh()){
				sfZgj = true;
			}
			if(null!=model.getShyj()){//转码
				model.setShyj(service.unicode2Gbk(model.getShyj()));
			}
			if(null!=model.getCfggw()&&!"undefined".equals(model.getCfggw())){//转码
				model.setCfggw(service.unicode2Gbk(model.getCfggw()));
			}
			if(null!=model.getSswh()){
				model.setSswh(service.unicode2Gbk(model.getSswh()));
			}
			//保存批量申诉审核
			model.setShr(user.getUserName());
			model.setSftj("0");//一般审核是不提交的
			boolean result=service.ssshPlxg(model, pkValue);
			request.setAttribute("result", result);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(sfZgj);
		
		return null;
	}
	
	/**
	 *	申诉提交
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssshTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		if("tj".equals(doType)){
			boolean result=service.ssshTjWjcf();
			request.setAttribute("result", result);
		}
		request.setAttribute("shtjList", service.ssshShjgTj());
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "cfssshCx.do");
		return mapping.findForward("cfssshTj");
	}
	
	/**
	 *	申诉提交（用于审核后直接提交）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfssshZjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfssglServices service=new WjcfCfssglServices();
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		boolean result=service.ssshTj(pkValue);
		request.setAttribute("result", result);
		request.setAttribute("shtjList", service.ssshShjgTj());
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "cfssshCx.do");
		return mapping.findForward("cfssshTj");
	}
	
	// 弹出处分类别层
	public ActionForward showCflbDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ============= 初始化各变量的值 ============
		WjcfCfssglServices service=new WjcfCfssglServices();

		// 消息信息
		String message = "";

		// 保存数据方法

		if (Base.isNull(message)) {
			service.showCflbDiv(response);
		}
		return null;
	}
	
}
