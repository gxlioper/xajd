package xgxt.comm.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.xml.XMLReader;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 高级查询_action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SearchAction extends BasicAction {

	/**
	 * 高级查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward SeachTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchForm myForm = (SearchForm) form;
		SearchService service = new SearchService();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();

		// 用户类型
		String userType = user.getUserType();
		// 用户名
		String userName = user.getUserName();
		// 功能模块路径
		String path = request.getParameter("path");
		myForm.setPath(path);
		
		// 学号
		String xh = "stu".equalsIgnoreCase(userType) ? userName : "";
		// =================== end ===================

		// =================== 初始化列表值 ===========
		service.setCommOptionList(myForm, rForm, request);
		service.setSearchTj(myForm, rForm, user, request);
		
		HashMap<String, String> rs = service.getSearchInfo(myForm, user);
		// ================= end =====================

		// ============= 设置request的值 =============
		// 其他字段
		String[] qtzd = new String[] { "path", "xh" };
		// 其他字段值
		String[] qtzdz = new String[] { path, xh };
		
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setRs(rs);
		
		service.setRequestValue(rForm, user, request);
		// =================== end ===================
		
		return mapping.findForward("seachTj");
	}
	
	public ActionForward xhPlcxDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("xhPlcxDiv");
	}
	public ActionForward xmPlcxDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("xmPlcxDiv");
	}
	
	/**
	 * 创建查询条件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createSearchTj(ActionMapping mapping,
										 ActionForm form, HttpServletRequest request,
										 HttpServletResponse response) throws Exception {

		SearchForm model = new SearchForm();
		SearchOptionList service = new SearchOptionList();
		RequestForm rForm = new RequestForm();
		SearchForm searchOptionModel = SearchForm.searchOptionModel;
		User user = getUser(request);// 用户对象
		searchOptionModel.SetUsername(user.getUserName());
		// ==================初始化========================
		// 访问路径
		String path = request.getParameter("searchPath");
		searchOptionModel.setPath(path);

		//个别权限控制:公寓管理-住宿信息管理，高级查询，此模块需限制公寓管理员的权限，其他一概不考虑
		//只有进入“住宿信息管理”菜单，公寓管理员权限为yes，其他都设置为no
		//其他业务需要判断用户公寓管理员 此if判断去除 modify by xiaxia 2017-01-03
//		String gygly_path = XMLReader.getFlowControl("gygl", "gygly_path");
//		if(gygly_path.contains(path) && "yes".equalsIgnoreCase(user.getGyglyQx())){
//			user.setGyglyQx("yes");
//			user.setUserStatus("xx");
//		}else{
//			user.setGyglyQx("no");
//		}

		//进入“住宿信息管理”菜单，若用户非公寓管理员，则均上升为学校管理员
/*		if(gygly_path.contains(path) && "no".equalsIgnoreCase(user.getGyglyQx())){
			user.setUserStatus("xx");
		}*/

		model.setPath(path);
		// 样式包地址
		String stylePath = request.getParameter("stylePath");
		searchOptionModel.setStylePath(stylePath);

		String yhlx = request.getParameter("yhlx");
		String jxid = request.getParameter("jxid");

		if(!Base.isNull(yhlx)){
			user.setUserStatus(yhlx);
		}
		// 初始化部门列表
		service.setNjXyZyBjList(searchOptionModel, rForm, user, request);
		// 初始化部门列表[全]
		service.setNjXyZyBjNewList(searchOptionModel, rForm, user, request);

		// 初始化公寓列表
		service.setLdChQshList(searchOptionModel, user);
//		if("10698".equals(Base.xxdm)){
		//西安交大初始化书院列表
		service.setSymcList(searchOptionModel,user);
		service.setZybjList(searchOptionModel,user);
//		}
		// 初始化勤工用人部门列表
		service.setQgbmList(searchOptionModel,user);
		// 初始化军训建制
		service.setJxjzList(searchOptionModel, user,jxid);
		// 初始化违纪类别列表
		service.setWjlbList(searchOptionModel);
		//初始化省市县
		service.setQxList(searchOptionModel,user);
		//初始化评奖名称
		service.setPjmcList(searchOptionModel,user,request.getParameter("xzdm"));

		// 条件列表
		List<HashMap<String, String>> tjList = service.getSearchTjList(model,
				user);
		// ==================初始化end========================

		// ==================加载列表========================
		// 点击查询列表
		List<HashMap<String, Object>> optionList = service.getSearchOptionList(
				searchOptionModel, tjList);
		// 批量查询列表
		List<HashMap<String, Object>> batchList = service.getSearchBatchList(
				searchOptionModel, tjList);
		// 时间查询列表
		List<HashMap<String, Object>> timeList = service.getSearchTimeList(
				searchOptionModel, tjList);
		// 数值区间查询列表
		List<HashMap<String, Object>> numList = service.getSearchNumList(
				searchOptionModel, tjList);


		// ==================加载列表 end========================

		// ==================构建前台页面========================
		service.createSearchDiv(searchOptionModel, optionList,batchList, timeList, numList,
				response);
		// ==================构建前台页面 end========================

		return null;
	}


	/**
	 * 创建部门过滤
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creatBmHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		User user = getUser(request);// 用户对象
		
		// ==================初始化========================
		// 点击年级
		String[] nj = null;
		if (request.getParameter("nj") != null
				&& request.getParameter("nj").length() > 0) {
			nj = request.getParameter("nj").split("!!@@!!");
		}

		// 点击学院
		String[] xy = null;
		if (request.getParameter("xy") != null
				&& request.getParameter("xy").length() > 0) {
			xy = request.getParameter("xy").split("!!@@!!");
		}

		// 点击专业
		String[] zy = null;
		if (request.getParameter("zy") != null
				&& request.getParameter("zy").length() > 0) {
			zy = request.getParameter("zy").split("!!@@!!");
		}

		// 部门类型
		String bmlx = request.getParameter("bmlx");
		// 样式包地址
		String stylePath = request.getParameter("stylePath");
		String searchPath = request.getParameter("searchPath");
		String sfzxs= "";
		if(searchPath.equals("xsxx_xsxxgl_cxfzxs.do")){
			sfzxs="0";
		}
		if(searchPath.equals("xsxx_xsxxgl_cxzxs.do")){
			sfzxs="1";
		}
		// ==================初始化end========================

		// ==================构建前台页面========================
		service.creatBmHtml(nj, xy, zy, bmlx, stylePath, user, response,sfzxs);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 创建书院过滤
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creatSyHtml(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		User user = getUser(request);// 用户对象

		// ==================初始化========================
		// 点击书院
		String[] sy = null;
		if (request.getParameter("sy") != null
				&& request.getParameter("sy").length() > 0) {
			sy = request.getParameter("sy").split("!!@@!!");
		}
		// 部门类型
		String bmlx = request.getParameter("bmlx");
		// 样式包地址
		String stylePath = request.getParameter("stylePath");
		String searchPath = request.getParameter("searchPath");
		String sfzxs= "";
		if(searchPath.equals("xsxx_xsxxgl_cxfzxs.do")){
			sfzxs="0";
		}
		if(searchPath.equals("xsxx_xsxxgl_cxzxs.do")){
			sfzxs="1";
		}
		// ==================初始化end========================

		// ==================构建前台页面========================
		service.creatSyHtml(sy, bmlx, stylePath, user, response,sfzxs);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 创建公寓过滤
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creatGyHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		User user = getUser(request);// 用户对象
		
		// ==================初始化========================
		// 点击校区
		String[] xqdm = null;
		if (request.getParameter("xqdm") != null
				&& request.getParameter("xqdm").length() > 0) {
			xqdm = request.getParameter("xqdm").split("!!@@!!");
		}
		
		// 点击园区
		String[] yqdm = null;
		if (request.getParameter("yqdm") != null
				&& request.getParameter("yqdm").length() > 0) {
			yqdm = request.getParameter("yqdm").split("!!@@!!");
		}
		
		// 点击楼栋
		String[] ld = null;
		if (request.getParameter("ld") != null
				&& request.getParameter("ld").length() > 0) {
			ld = request.getParameter("ld").split("!!@@!!");
		}

		// 点击层号
		String[] ch = null;
		if (request.getParameter("ch") != null
				&& request.getParameter("ch").length() > 0) {
			ch = request.getParameter("ch").split("!!@@!!");
		}
		
		// 部门类型
		String bmlx = request.getParameter("bmlx");
		// 样式包地址
		String stylePath = request.getParameter("stylePath");
		// ==================初始化end========================

		// ==================构建前台页面========================
		service.creatGyHtml(xqdm, yqdm, ld, ch, bmlx, stylePath, user,
						response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/** 
	 * @描述:浙江旅游---计分项目过滤
	 * @作者：cp[工号：1352]
	 * @日期：2017-1-4 下午04:05:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public ActionForward creatJfxmHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchOptionList service = new SearchOptionList();
			String[] xmmkdm = null;
			if (request.getParameter("xmmkdm") != null&& request.getParameter("xmmkdm").length() > 0) {
				xmmkdm = request.getParameter("xmmkdm").split("!!@@!!");
			}
			String bmlx = request.getParameter("bmlx");
			service.creatJfxmHtml(xmmkdm,bmlx,response);
	
		
		// ==================构建前台页面 end========================

		return null;
	}
	/**
	 * 创建军训建制过滤
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creatJxjzHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		User user = getUser(request);// 用户对象
		
		// ==================初始化========================
		// 点击团
		String[] tid = null;
		if (request.getParameter("tid") != null
				&& request.getParameter("tid").length() > 0) {
			tid = request.getParameter("tid").split("!!@@!!");
		}
		
		// 点击营
		String[] yid = null;
		if (request.getParameter("yid") != null
				&& request.getParameter("yid").length() > 0) {
			yid = request.getParameter("yid").split("!!@@!!");
		}
		
		// 点击连
		String[] lid = null;
		if (request.getParameter("lid") != null
				&& request.getParameter("lid").length() > 0) {
			lid = request.getParameter("lid").split("!!@@!!");
		}

		// 点击排
		String[] pid = null;
		if (request.getParameter("pid") != null
				&& request.getParameter("pid").length() > 0) {
			pid = request.getParameter("pid").split("!!@@!!");
		}
		
		// 点击班
		String[] bid = null;
		if (request.getParameter("bid") != null
				&& request.getParameter("bid").length() > 0) {
			bid = request.getParameter("bid").split("!!@@!!");
		}
		
		// 点击宿舍
		String[] ssid = null;
		if (request.getParameter("ssid") != null
				&& request.getParameter("ssid").length() > 0) {
			ssid = request.getParameter("ssid").split("!!@@!!");
		}
		
		// 联动类型
		String ldlx = request.getParameter("ldlx");
		// 样式包地址
		String stylePath = request.getParameter("stylePath");
		// 军训id
		String jxid = request.getParameter("jxid");
		// ==================初始化end========================

		// ==================构建前台页面========================
		service.createJxjzHtml(tid, yid, lid, pid, bid, ssid, ldlx, stylePath, user, jxid, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 创建公寓违纪过滤
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creatWjHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		// ==================初始化========================
		// 点击违纪类别大类
		String[] gyjllbdldm = null;
		if (request.getParameter("gyjllbdldm") != null
				&& request.getParameter("gyjllbdldm").length() > 0) {
			gyjllbdldm = request.getParameter("gyjllbdldm").split("!!@@!!");
		}
		
		// 联动类型
		String ldlx = request.getParameter("ldlx");
		// ==================初始化end========================

		// ==================构建前台页面========================
		service.createWjHtml(gyjllbdldm, ldlx, response);
		// ==================构建前台页面 end========================

		return null;
	}
	/**
	 * 省县市 三级联动
	 */
	public ActionForward creatQxHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		String[] qxdm = null;
		// 联动类型
		String jb = request.getParameter("ldlx");
		if(jb.equals("shi")){
			if (request.getParameter("sheng") != null&& request.getParameter("sheng").length() > 0) {
				qxdm = request.getParameter("sheng").split("!!@@!!");
			}
		}else if(jb.equals("sheng")){
			if (request.getParameter("sheng") != null&& request.getParameter("sheng").length() > 0) {
				qxdm = request.getParameter("sheng").split("!!@@!!");
			}
			jb="qu";
		}else{
			if (request.getParameter("shi") != null&& request.getParameter("shi").length() > 0) {
				qxdm = request.getParameter("shi").split("!!@@!!");
			}
		}
		List<String> list = new ArrayList<String>();
		if(qxdm!=null && qxdm.length>0){
			for (int i = 0; i < qxdm.length; i++) {
				list.add(qxdm[i]);
			}
		}
		service.createQxHtml(list, jb, response);
		return null;
	}
	

	/**
	 * 创建部门过滤
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward creatBmNewHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		User user = getUser(request);// 用户对象
		
		// ==================初始化========================
		// 点击年级
		String[] nj = null;
		if (request.getParameter("njNew") != null
				&& request.getParameter("njNew").length() > 0) {
			nj = request.getParameter("njNew").split("!!@@!!");
		}


		// 点击学院
		String[] xy = null;
		if (request.getParameter("xyNew") != null
				&& request.getParameter("xyNew").length() > 0) {
			xy = request.getParameter("xyNew").split("!!@@!!");
		}

		// 点击专业
		String[] zy = null;
		if (request.getParameter("zyNew") != null
				&& request.getParameter("zyNew").length() > 0) {
			zy = request.getParameter("zyNew").split("!!@@!!");
		}

		// 部门类型
		String bmlx = request.getParameter("bmlx");
		// 样式包地址
		String stylePath = request.getParameter("stylePath");
		// ==================初始化end========================

		// ==================构建前台页面========================
		service.creatBmNewHtml(nj, xy, zy, bmlx, stylePath, user, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 评奖项目联动
	 */
	public ActionForward creatPjxmHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SearchOptionList service = new SearchOptionList();
		
		// ==================初始化========================
		// 点击类型
		String[] xmlx = null;
		if (request.getParameter("xmlx") != null && request.getParameter("xmlx").length() > 0) {
			xmlx = request.getParameter("xmlx").split("!!@@!!");
		}

		// 点击性质
		String[] xmxz = null;
		String xzdm = request.getParameter("xzdm");
		if(StringUtils.isNotNull(xzdm)){
			xmxz = new String [] {request.getParameter("xzdm")};
		}else {
			xmxz = new String [] {"1"};
		}

		// 联动类型
		String ldlx = request.getParameter("ldlx");
		// 样式包地址
		String stylePath = request.getParameter("stylePath");
		// ==================初始化end========================

		// ==================构建前台页面========================
		service.creatPjxmHtml(xmlx, xmxz, ldlx, stylePath, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
}