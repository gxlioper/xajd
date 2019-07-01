package xsgzgl.jxgl.general.jxjzgl.jxjzgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.jxgl.general.jxbxgl.JxglJxbxglService;
import xsgzgl.jxgl.general.jxcjgl.JxglJxcjglService;
import xsgzgl.jxgl.general.jxxxwh.JxglJxxxwhService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_军训建制管理_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 易江东
 * @version 1.0
 */

public class JxjzglAction extends BasicAction {
	
	/**
	 * 查询军训建制
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJxjz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JxjzglService service=new JxjzglService();
		RequestForm rForm = new RequestForm();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		JxjzglFrom jxjzglModel=(JxjzglFrom)form;
		User user=getUser(request);
		JxjzglFrom jxjzglFrom=new JxjzglFrom();
		
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		//验证军训是否存在
		if(jxxxwhModel == null || jxxxwhModel.isEmpty()){
			//返回不能建制的页面
			String msg = "请建立并运行军训！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			jxjzglFrom.setJzid(jxxxwhModel.get("jxid"));
			jxjzglFrom.setJzmc(jxxxwhModel.get("jxmc"));
			service.initJxjzglRootNode(jxjzglFrom, user);
		}
		//查询统计人数
		jxjzglModel.setJxid(jxxxwhModel.get("jxid"));
		jxjzglModel.setSjid(jxxxwhModel.get("jxid"));
		HashMap<String, String> rsTj=service.getRszjTjb(jxjzglModel);
		//设置查询人数
		request.setAttribute("rsTj", rsTj);
		request.setAttribute("jxxxwhModel", jxxxwhModel);
		
		// 访问路径
		String path = "jxjzgl_jxjzgl_cxJxjz.do";
		rForm.setPath(path);
		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		
		return mapping.findForward("cxJxjz");
	}
	
	/**
	 * 查询建制维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJzwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JxjzglService service=new JxjzglService();
		RequestForm rForm = new RequestForm();
		JxjzglFrom jxjzglModel=(JxjzglFrom)form;
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		
		//查询统计人数
		jxjzglModel.setJxid(jxxxwhModel.get("jxid"));
		jxjzglModel.setSjid(jxxxwhModel.get("jxid"));
		HashMap<String, String> rsTj=service.getRszjTjb(jxjzglModel);
		//设置查询人数
		request.setAttribute("rsTj", rsTj);
		
		//获取军训等级列表
		List<HashMap<String, String>> jxdjList=service.cxJxdjList(jxjzglModel);
		HashMap<String, String> jxdjzdModel=service.ckJxdjZdModel();
		// ============= 设置request的值 --业务数据 =============
		request.setAttribute("jxdjList", jxdjList);
		//设置业务数据request
		request.setAttribute("jxdjzdModel", jxdjzdModel);
		request.setAttribute("jxxxwhModel", jxxxwhModel);
		
		// 访问路径
		String path = "jxjzgl_jxjzgl_cxJxjz.do";
		rForm.setPath(path);
		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		return mapping.findForward("cxJzwh");
	}
	
	/**
	 * 增加建制维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initYjJxjz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom jxjzModel=new JxjzglFrom();
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		//设置上级id为当前军训id
		jxjzModel.setSjid(jxxxwhModel.get("jxid"));
		List<HashMap<String, String>> jxjzData=service.cxOneNode(jxjzModel);
		
		String json = JSONArray.fromObject(jxjzData).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * 增加建制维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjJzwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglService service=new JxjzglService();
		JxjzglFrom model=(JxjzglFrom)form;
		
		//model.setJzjb(request.getParameter("jzjb"));
		String zjJzwhHtml=service.zjJzwhHtml(model);
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(zjJzwhHtml);
		return null;
	}
	
	/**
	 * 增加保存建制维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjBcJzwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JxjzglService service=new JxjzglService();
		JxjzglFrom model=(JxjzglFrom)form;
		User user=getUser(request);
		
		//表单内容转码
		jxjzglUnicode2Gbk(model);
		
		boolean result=service.zjBcBzjd(model, user);
		List<HashMap<String, String>> treeNode=null;
		if(result){
			treeNode=zjTreeNode(model);
		}else{
			treeNode=new ArrayList<HashMap<String,String>>();
		}
		String json = JSONArray.fromObject(treeNode).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().print(json);
		
		return null;
	}
	
	/**
	 * 修改建制维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xgJzwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JxjzglService service=new JxjzglService();
		JxjzglFrom model=(JxjzglFrom)form;
		
		String zjJzwhHtml=service.xgJzwhHtml(model);
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(zjJzwhHtml);
		return mapping.findForward("xgJzwh");
	}
	
	/**
	 * 修改建制维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xgBcJzwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglService service=new JxjzglService();
		JxjzglFrom model=(JxjzglFrom)form;
		User user=getUser(request);
		
		//表单内容转码
		jxjzglUnicode2Gbk(model);
		
		boolean result=service.xgBcBzjd(model, user);
		HashMap<String, String> jxjzModel=new HashMap<String, String>();
		if(result){
			jxjzModel=service.ckJxjzModel(model);
		}else{
			
		}
		String json = JSONObject.fromObject(jxjzModel).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().print(json);
		
		return null;
	}
	
	/**
	 * 删除建制维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward scJzwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglService service=new JxjzglService();
		JxjzglFrom model=(JxjzglFrom)form;
		User user=getUser(request);
		
		boolean result=service.scBzjd(model, user);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * 查询树形节点
	 * @param model
	 * @return
	 */
	private List<HashMap<String, String>> zjTreeNode(JxjzglFrom model){
		List<HashMap<String, String>> treeNode=new ArrayList<HashMap<String,String>>();
		HashMap<String, String> node=new HashMap<String, String>();
		
		node.put("jzid", model.getJzid());
		node.put("jzmc", model.getJzmc());
		node.put("djdm", model.getJzjb());
		treeNode.add(node);
		return treeNode;
		
	}
	
	/**
	 * 增加修改方法转码
	 * @param model
	 */
	private void jxjzglUnicode2Gbk(JxjzglFrom model){
		JxjzglService service=new JxjzglService();
		if(model != null){
			if(model.getJzmc() != null && !"".equals(model.getJzmc())){
				model.setJzmc(service.unicode2Gbk(model.getJzmc()));
			}
			if(model.getJgmc() != null && !"".equals(model.getJgmc())){
				model.setJgmc(service.unicode2Gbk(model.getJgmc()));
			}
			if(model.getJsmc() != null && !"".equals(model.getJsmc())){
				model.setJsmc(service.unicode2Gbk(model.getJsmc()));
			}
		}
	}
	
	
	/**
	 * 查询下级节点列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxXjjd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom jxjzModel=new JxjzglFrom();
		JxjzglService service=new JxjzglService();
		jxjzModel.setSjid(request.getParameter("nodeId"));
		
		List<HashMap<String, String>> jxjzData=service.cxJxjzNodeList(jxjzModel);
		
		String json = JSONArray.fromObject(jxjzData).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * 查询军训建制信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJxjzxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		//设置上级id为当前军训id
		model.setSjid(jxxxwhModel.get("jxid"));
		
		HashMap<String, String> jxjzModel = service.ckJxjzModelBySjid(model);
		jxjzModel.put("xxdm", Base.xxdm);
		String json = JSONObject.fromObject(jxjzModel).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * 验证建制名称
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkJzmc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom jxjzModel=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		jxjzglUnicode2Gbk(jxjzModel);
		boolean result=service.checkJzmc(jxjzModel);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(String.valueOf(result));
		
		return null;
	}
	
	
	/**
	 * 查询维护建制名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxWhjzmd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JxjzglService service=new JxjzglService();
		RequestForm rForm = new RequestForm();
		JxjzglFrom model=(JxjzglFrom)form;
		User user=getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		request.setAttribute("path", "jxjzgl_jxjzgl_cxJxjz.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setPath("jxjzgl_cxJxjz.do?method=cxWhjzmd");
		// ----------------设置PATH end-----------------------
		
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("model", model);
		return mapping.findForward("cxWhjzmd");
	}
	
	/**
	 * 查询维护建制名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxWhjzmdAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		RequestForm rForm = new RequestForm();
		User user=getUser(request);
		SearchRsModel rsModel = new SearchRsModel();
		//查询路径
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxjzgl_cxJxjz.do?method=cxWhjzmd");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		model.setSjid(jxxxwhModel.get("jxid"));
		model.setJxid(jxxxwhModel.get("jxid"));
		ArrayList<String[]> rsArrList=service.getJxjzXsxxList(model);
		//表头
		List<HashMap<String, String>> topTr = service.getTopTr("whjzmd");
		
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 增加保存维护军训建制名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjBcWhjxjzmd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		String pks=request.getParameter("pks");
		User user=getUser(request);
		JxjzglService service=new JxjzglService();
		RequestForm rForm = new RequestForm();
		
		service.setRequestValue(rForm, user, request);
		
		boolean result=service.zjBcJxjzmd(pks, model, user);
		response.setCharacterEncoding("gbk");
		response.getWriter().write(String.valueOf(result));
		return null;
	}
	
	
	/**
	 * 查询建制学生名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJzxsmdAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		RequestForm rForm = new RequestForm();
		User user=getUser(request);
		SearchRsModel rsModel = new SearchRsModel();
		//查询路径
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxjzgl_cxJxjz.do?method=cxJzxsmdAjax");
		// IE版本
		String[] otherValue=request.getParameter("otherValue").split("!!@@!!");
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		model.setSjid(jxxxwhModel.get("jxid"));
		model.setJzid(otherValue[1]);
		ArrayList<String[]> rsArrList=service.cxJxjzXsxxListByJzid1(model);
		//表头
		List<HashMap<String, String>> topTr = service.getTopTr("jxjzxsxx");
		
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 查询建制统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJxjzTjbAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		//设置上级id为当前军训id
		model.setSjid(jxxxwhModel.get("jxid"));
		
		List<HashMap<String, String>> jxjzList=service.cxJxjzTjb(model);
		
		String jxjzTjbHtml=service.createJxjzTjxHtml(jxjzList, model);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(String.valueOf(jxjzTjbHtml));
		return null;
	}
	
	/**
	 * 查询维护建制名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJzmd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JxjzglService service=new JxjzglService();
		RequestForm rForm = new RequestForm();
		JxjzglFrom model=(JxjzglFrom)form;
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		
		User user=getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		request.setAttribute("path", "jxjzgl_jxjzgl_cxJxjz.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setPath("jxjzgl_cxJxjz.do?method=cxJzmd");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("model", model);
		request.setAttribute("searchModel", model.getSearchModel());
		request.setAttribute("jxxxwhModel", jxxxwhModel);
		
		//设置导出
		request.setAttribute("tableName", "xg_view_jxgl_jxjzmdb");//一般为查询视图
		
		//设置返回path
		String resultPath = request.getParameter("resultPath");
		request.setAttribute("resultPath", resultPath);
		
		//设置默认查询条件
		String sjjz=request.getParameter("sjjz");
		if(sjjz != null && !"".equals(sjjz)){
			SearchModel searchModel = new SearchModel();
			if("1".equals(sjjz)){
				searchModel.setSearch_tj_sfybz(new String[]{"是"});
			}else{
				searchModel.setSearch_tj_sfybz(new String[]{"否"});
			}
			request.setAttribute("searchTj", searchModel);
		}
		
		//默认高级查询条件
		String jzid=request.getParameter("jzid");
		String bjdm=request.getParameter("bjdm");
		String xb=request.getParameter("xb");
		if(jzid != null && !"".equals(jzid)){
			SearchModel searchModel = service.setDefaultSearchModel(jzid, bjdm);
			if(!Base.isNull(xb)){
				searchModel.setSearch_tj_xb(new String[]{xb});
			}
			request.setAttribute("searchTj", searchModel);
		}
		
		return mapping.findForward("cxJzmd");
	}
	
	/**
	 * 查询维护建制名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJzmdAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		RequestForm rForm = new RequestForm();
		User user=getUser(request);
		SearchRsModel rsModel = new SearchRsModel();
		//查询路径
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxjzgl_cxJxjz.do?method=cxJzmd");
		// IE版本
		String[] otherValue = request.getParameter("otherValue").split("!!@@!!");
		rsModel.setIe(otherValue[0]);
		
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		//if(otherValue.length < 2){
		model.setSjid(jxxxwhModel.get("jxid"));
		model.setJxid(jxxxwhModel.get("jxid"));
		//}else{
		//	model.setSjid(otherValue[1]);
		//}
		
		ArrayList<String[]> rsArrList=service.getJxjzmdList(model);
		//表头
		List<HashMap<String, String>> topTr = service.getTopTr("whjzmd");
		
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 查询维护建制名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dcJzTjb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		model.setSjid(jxxxwhModel.get("jxid"));
		//建制人数统计
		List<HashMap<String, String>> jxjzList=service.cxJxjzTjb(model);
		//查询统计人数
		model.setJxid(jxxxwhModel.get("jxid"));
		HashMap<String, String> title=service.getRszjTjb(model);
		title.put("jxmc", jxxxwhModel.get("jxmc"));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.dcJzTjb(response.getOutputStream(),model,title,jxjzList);
		return mapping.findForward("");
	}
	
	/**
	 * 取消学生建制
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qxbz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user=getUser(request);
		JxjzglService service=new JxjzglService();
		JxglJxcjglService jxcjService=new JxglJxcjglService();
		JxglJxbxglService jxbxService=new JxglJxbxglService();
		
		//获取参数
		String pkValue=request.getParameter("pkValue");
		String jxid=request.getParameter("jxid");
		String jzids=request.getParameter("jzids");
		
		boolean isJxcj=jxcjService.checkIsJxcjByXs(pkValue, jxid);
		boolean isJxbx=jxbxService.checkIsJxbxByXs(pkValue, jxid);
		
		String message=service.scXsjz(isJxcj, isJxbx, pkValue, jzids, user);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(message);
		return null;
	}
	
	/**
	 * 查询维护建制名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkXscjAndBx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		
		JxglJxcjglService jxcjService=new JxglJxcjglService();
		JxglJxbxglService jxbxService=new JxglJxbxglService();
		
		List<HashMap<String, String>> jzmdList=service.getJxjzXsmdList(model);
		String xhs=service.setXsxhByListMap(jzmdList);
		
		boolean isJxcj=jxcjService.checkIsJxcjByXs(xhs, model.getJxid());
		boolean isJxbx=jxbxService.checkIsJxbxByXs(xhs, model.getJxid());
		
		HashMap<String, String> hashMap=new HashMap<String, String>();
		hashMap.put("isJxcj", String.valueOf(isJxcj));
		hashMap.put("isJxbx", String.valueOf(isJxbx));
		
		String json = JSONObject.fromObject(hashMap).toString();
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * 查询当前军训信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxDqjxxxAjax(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		
		//获取当前军训信息
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		//查询统计人数
		model.setJxid(jxxxwhModel.get("jxid"));
		model.setSjid(jxxxwhModel.get("jxid"));
		HashMap<String, String> rsTj=service.getRszjTjb(model);
		
		String dqjxxxHtml=service.createDqjxjzHtml(jxxxwhModel, rsTj);
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(String.valueOf(dqjxxxHtml));
		return null;
	}
	
	/**
	 * 军训建制管理自定义导出 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward jxjzglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		JxjzglFrom model=(JxjzglFrom)form;
		JxjzglService service=new JxjzglService();
		
		JxglJxxxwhService jxxxwhService=new JxglJxxxwhService();
		HashMap<String, String> jxxxwhModel=jxxxwhService.getJxxxwhModel();
		//if(otherValue.length < 2){
		model.setSjid(jxxxwhModel.get("jxid"));
		model.setJxid(jxxxwhModel.get("jxid"));
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = (List<HashMap<String,String>>) service.getJxjzmdExportDataList(model);
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
	
}
