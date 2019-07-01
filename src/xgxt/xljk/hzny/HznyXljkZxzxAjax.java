package xgxt.xljk.hzny;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.xszz.whtl.ybbx.XszzYbbxForm;
import xgxt.xszz.whtl.ybbx.XszzYbbxInit;
import xgxt.xszz.whtl.ybbx.XszzYbbxSaveModel;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;

public class HznyXljkZxzxAjax extends BasicAction {

	HznyXljkZxzxService service = new HznyXljkZxzxService();

	// -------------------------咨询师信息管理 begin
	// ------------------------------------
	/**
	 * 咨询师查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxsglSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxForm myForm = (HznyXljkZxzxForm) form;

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		model.setColList(new String[] { "pkValue", "zgh", "xm", "xb", "bmmc",
				"zxszg_info", "zxszg" });
		model.setPath("hn_xljk_zxsgl.do");
		init.initZxsglManage(rForm, model, request, user);

		// 结果集显示字段

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);

		model.setSearchModel(searchModel);

		// ==================高级查询相关 end========================

		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, "hn_xljk_zxsgl.do");

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getZxsglList(model);

		// 构建结果集
		String spHtml = service
				.createZxsglHTML(rsModel, model, rsArrList, user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 新增咨询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		model.setTableName("xg_xljk_zxsxxb");
		// flag=service.checkInfo(model);

		// if(flag){

		init.initZxsSave(model, request);

		flag = service.saveInfo(model);

		// }else {
		// message="该记录已有审核操作，无法修改！";
		//		
		// }

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 修改咨询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiZxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		model.setTableName("xg_xljk_zxsxxb");

		init.initZxsModi(model, request);

		flag = service.updateInfo(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 批量删除询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] { "zgh" });

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		// --------------表名------------
		model.setTableName("xg_xljk_zxsxxb");

		// 主键
		model.setPkValue(pkValue);

		// flag=service.checkInfo(model);
		// if(!flag){
		//			
		// message="勾选记录中存在已审核操作，无法删除！";
		// }

		// 批量删除

		flag = service.batchDelete(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// -------------------------咨询师信息管理 end ------------------------------------

	// -------------------------特殊学生管理 begin
	// ------------------------------------
	/**
	 * 咨询师查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsxsglSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		model.setColList(new String[] { "pkValue", "xh", "xm", "nj", "xymc",
				"zymc", "bjmc" });
		model.setPath("hn_xljk_tsxsgl.do");
		init.initZxsglManage(rForm, model, request, user);

		// 结果集显示字段

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);

		model.setSearchModel(searchModel);

		// ==================高级查询相关 end========================

		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, "hn_xljk_tsxsgl.do");

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getTsxsglList(model);

		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, model, rsArrList,
				user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 新增咨询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTsxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		init.initTsxsSave(model, request, user);

		flag = service.saveInfo(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 修改咨询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiTsxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		init.initTsxsModi(model, request);

		flag = service.updateInfo(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 批量删除询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteTsxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] { "xh" });

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		// --------------表名------------
		model.setTableName("xg_xljk_tsxsxxb");

		// 主键
		model.setPkValue(pkValue);

		// flag=service.checkInfo(model);
		// if(!flag){
		//			
		// message="勾选记录中存在已审核操作，无法删除！";
		// }

		// 批量删除

		flag = service.batchDelete(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// -------------------------咨询师信息管理 end ------------------------------------

	// ------------------------网络咨询管理 begin ------------------------------------
	/**
	 * 咨询师查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxzxglSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxForm myForm = (HznyXljkZxzxForm) form;

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		String userType = user.getUserType();

		// --------------------根据权限区分 学生与咨询师的结果集字段-------------------------
		if ("stu".equalsIgnoreCase(userType)) {

			model.setColList(new String[] { "pkValue", "xh", "xm", "zxsj",
					"zxwt_info", "sfhf", "zxwt" });
		} else {

			model.setColList(new String[] { "pkValue", "xh", "xsxm", "nj",
					"bjmc", "zxsj", "zxwt_info", "sfhf","sftsxs", "zxwt" });
		}

		model.setPath("hn_xljk_zxzx.do");
		init.initZxzxglManage(rForm, model, request, user);

		// 结果集显示字段

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		searchModel.setPath("hn_xljk_zxzx.do");
		model.setSearchModel(searchModel);

		// ==================高级查询相关 end========================

		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, "hn_xljk_zxzx.do");

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getZxzxglList(model);

		// 构建结果集
		String spHtml = service.createZxzxHTML(rsModel, model, rsArrList, user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 新增咨询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZxzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		init.initZxzxSave(model, request);

		flag = service.saveInfo(model);

		String czcg="感谢您的信任，我们将尽快回复，有必要时将电话联系您。中心联系电话027-87670586，中心地址：大学生活动中心208室。";
		if (Base.isNull(message)) {
			message = flag ? czcg
					:MessageInfo.MESSAGE_SAVE_FALSE ;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 修改咨询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiZxzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		User user = getUser(request);
		
		String userName=user.getUserName();

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		init.initZxzxModi(model, request, user);

		String guid = request.getParameter("guid");
		
		flag= service.checkZxs(userName);
		
		if(!flag){
		
			flag = service.checkZxzx(guid);
		}	
		
		if (flag) {
			
			flag = service.updateInfo(model);
		
		} else {
			
			message = "心理咨询师已做回复记录无法修改！";
		}

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 批量删除询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteZxzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] { "guid" });

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		// --------------表名------------
		model.setTableName("xg_xljk_zxzxb");

		// 主键
		model.setPkValue(pkValue);
		

		// 批量删除
		flag=service.checkZxzx(pkValue);
		if(!flag){
					
			 message="勾选记录中存在咨询师已回复记录，无法删除！";
		}else{

			flag = service.batchDelete(model);
		}
		
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// -------------------------网络咨询管理 end ------------------------------------

	// ------------------------学生咨询 begin ------------------------------------
	/**
	 * 学生咨询查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszxSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxForm myForm = (HznyXljkZxzxForm) form;

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		String userType = user.getUserType();

		// --------------------根据权限区分 学生与咨询师的结果集字段-------------------------
		if ("stu".equalsIgnoreCase(userType)) {

			model.setColList(new String[] { "pkValue", "zxsxm", "zxsj",
					"zxnrjyj", "sffk","sfbrpj" });
		} else {

			model.setColList(new String[] { "pkValue", "zxsxm", "zxsj",
					"zxnrjyj", "sffk", "sfzdbr" });
		}
		
		String path="hn_xljk_xszx.do";
//		查询的结果集
		if("stu".equalsIgnoreCase(userType)){
			
			model.setColList(new String[]{"pkValue","zxsxm","zxsj", "zxnrjyj", "sffk"});
		
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			path="hn_xljk_xszx.do?searchType=admin";
			model.setColList(new String[]{"pkValue","zgh","zxsxm","xh","xm","zxsj", "sffk"});
		
		}else {
			path="hn_xljk_xszx.do?searchType=zxs";
			model.setColList(new String[]{"pkValue","zxsxm","zxsj", "zxnrjyj", "sffk","sfzdbr"});
		}
		
		
		model.setPath(path);
		init.initXszxManage(rForm, model, request, user);

		// 结果集显示字段

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);

		model.setSearchModel(searchModel);

		// ==================高级查询相关 end========================

		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, path);

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getXszxList(model);

		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, model, rsArrList,
				user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 新增咨询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXszx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		User user = getUser(request);

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		init.initXszxSave(model, request, user);

		flag = service.saveInfo(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 修改咨询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiXszx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		User user = getUser(request);
		
		String userType=user.getUserType();

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = true;

		init.initXszxModi(model, request, user);
		
		if("stu".equalsIgnoreCase(userType)){
			flag=service.checkXszx(model.getValueMap().get("guid"));
		}
		
		if(!flag){
			
			message="勾选的记录咨询师已做反馈，无法修改！";
		}else{
			
			flag = service.updateInfo(model);

		}

	
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 批量删除询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteXszx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] { "guid" });

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		// --------------表名------------
		model.setTableName("xg_xljk_xszxfkb");

		// 主键
		model.setPkValue(pkValue);

	    flag=service.checkXszx(pkValue);
		if(!flag){
					
			message="勾选记录中存在咨询师已做回复记录，无法删除，请确认！";
		 
		}else {

			// 批量删除
	
			flag = service.batchDelete(model);
		
		}

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// -------------------------网络咨询管理 end ------------------------------------

	// ------------------------学生咨询 begin ------------------------------------
	/**
	 * 学生咨询查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjlSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxForm myForm = (HznyXljkZxzxForm) form;

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		String userType = user.getUserType();

		// --------------------根据权限区分 学生与咨询师的结果集字段-------------------------
		if("stu".equalsIgnoreCase(userType)){
			model.setColList(new String[] { "pkValue", "xh", "xm", "nj", "bjmc","zxsj" });
		}else {
			
			model.setColList(new String[] { "pkValue", "xh", "xsxm", "nj", "bjmc",
					"zxsj","sftsxs" });
		}
		model.setPath("hn_xljk_zxjl.do");
		init.initXszxManage(rForm, model, request, user);

		// 结果集显示字段

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);

		model.setSearchModel(searchModel);

		// ==================高级查询相关 end========================

		// ==================显示内容========================

		// 标题
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, "hn_xljk_zxjl.do");

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getZxjlList(model);

		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, model, rsArrList,
				user);

		// 构建学校个性化信息隐藏域
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 新增咨询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZxjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		User user = getUser(request);

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		init.initZxjlSave(model, request, user);

		flag = service.saveInfo(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 修改咨询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiZxjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HznyXljkZxzxInit init = new HznyXljkZxzxInit();

		BasicModel model = new BasicModel();

		User user = getUser(request);

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		init.initZxjlModi(model, request, user);

		flag = service.updateInfo(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 批量删除询师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteZxjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] { "guid" });

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		// --------------表名------------
		model.setTableName("xg_xljk_xlzxjlb");

		// 主键
		model.setPkValue(pkValue);

		// flag=service.checkInfo(model);
		// if(!flag){
		//			
		// message="勾选记录中存在已审核操作，无法删除！";
		// }

		// 批量删除

		flag = service.batchDelete(model);

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// -------------------------网络咨询管理 end ------------------------------------

}
