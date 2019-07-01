package xgxt.xszz.whtl.ybbx;

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

import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xsgzgl.comm.BasicModel;
import xsgzgl.xsxx.byjd.gdjs.XsxxByjdForm;
import xsgzgl.xsxx.byjd.gdjs.XsxxByjdInit;

import com.zfsoft.basic.BasicAction;

public class XszzYbbxAjax extends BasicAction{
	
	XszzYbbxService service = new XszzYbbxService();
	
	/**
	 * 查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ybbxcxSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XszzYbbxForm myForm=(XszzYbbxForm)form;
		
		XszzYbbxInit init=new XszzYbbxInit();
		
		BasicModel model = new BasicModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		model.setColList(new String[]{"pkValue","xh","xm", "nj",  "bjmc","xysh","xxsh"});
		model.setPath("xszz_ybbx_cx.do");
		init.initYbbxcx(rForm, model, request,user);
		
		// 结果集显示字段
		
		HashMap<String,String>valueMap=service.getValueMap(request,new String[]{});
		
		
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
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),user,"xszz_ybbx_cx.do");
		
		
		// 结果集
		ArrayList<String[]> rsArrList =(ArrayList<String[]>)service.getYbbxcxList(model);
		
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, model, rsArrList,
				user);
		
		// 构建学校个性化信息隐藏域
		//spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
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
	 * 查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ybbxshSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XszzYbbxForm myForm=(XszzYbbxForm)form;
		
		XszzYbbxInit init=new XszzYbbxInit();
		
		BasicModel model = new BasicModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		
		model.setPath("xszz_ybbx_sh.do");
		init.initYbbxsh(rForm, model, request, user);
		
		// 结果集显示字段
		
		HashMap<String,String>valueMap=service.getValueMap(request,new String[]{});
		
		
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
		
		model.setUser(user);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		
		// 标题
		
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),user,"xszz_ybbx_sh.do");
		
		
		// 结果集
		ArrayList<String[]> rsArrList =(ArrayList<String[]>)service.getYbbxshList(model);
		
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, model, rsArrList,
				user);
		
		// 构建学校个性化信息隐藏域
		//spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
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
	 * 批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BasicModel model=new BasicModel();
		
		model.setPk(new String[]{"xn","xh"});
		
		String pkStr=service.unicode2Gbk(request.getParameter("pkValue"));
		
		
		String[]pkValue=pkStr.split("!!array!!");
		
		//消息信息
		String message="";
		
		//保存数据方法
		boolean flag=false;
		
		// --------------表名------------
		model.setTableName("xg_xszz_ybbxsqb");
	
		//主键
		model.setPkValue(pkValue);
		
		flag=service.checkInfo(model);
		if(!flag){
			
			message="勾选记录中存在已审核操作，无法删除！";
		}
		
		//批量删除
		if(flag){
			flag=service.batchDelete(model);
		}
		//删除无效数据
		if(flag){
			flag=service.deleteYbbxInfo();
		}
		
		if(Base.isNull(message)){
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	
	public ActionForward dgsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		XszzYbbxInit init=new XszzYbbxInit();
		
		// ------通过构造方法初始化 XszzYbbxSaveModel、BasicModel的数据-------------
		XszzYbbxSaveModel saveModel=new XszzYbbxSaveModel();
		
		init.initDgshInfo(saveModel, model, request, user);
		

		service.trimZd(saveModel, new String[]{"mzyy","bxje","wzsj","ylyt","shje"});
		
		boolean flag=service.saveBatch(model,saveModel);
		
		// ---------------------固定的保存值 end--------------
		init.initShInfo(saveModel, model, request, user);
		
		flag=service.updateInfo(model);
		
		//消息信息
		String message="";
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	public ActionForward plsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		model.setUser(user);
		XszzYbbxInit init=new XszzYbbxInit();
		
		// ------通过构造方法初始化 XszzYbbxSaveModel、BasicModel的数据-------------
		XszzYbbxSaveModel saveModel=new XszzYbbxSaveModel();
		
		init.initPlsh(saveModel, model, request, user);
		
		boolean flag=service.plsh(model);
		
		// ---------------------固定的保存值 end--------------
	
		//消息信息
		String message="";
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 批量审核保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBatch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		XszzYbbxForm myForm=(XszzYbbxForm)form;
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		// ------通过构造方法初始化 XszzYbbxSaveModel、BasicModel的数据-------------
		XszzYbbxSaveModel saveModel=new XszzYbbxSaveModel(request,user,model);
		
		//消息信息
		String message="";
		
		//保存数据方法
		boolean flag=false;
		
		model.setTableName("xg_xszz_ybbxsqb");
		flag=service.checkInfo(model);
		
		if(flag){
			model.setTableName("xg_xszz_ybbxxxb");
			flag=service.saveBatch(model,saveModel);

			
			model.setTableName("xg_xszz_ybbxsqb");
			
			myForm.setPkValue(model.getPkValue()[0]);
			
			HashMap<String,String>ybbxSqbInfo=service.getYbbxSqbInfo(myForm);
			
			if(ybbxSqbInfo==null || ybbxSqbInfo.size()==0){
				service.saveInfo(model);
			}
		}else {
			message="该记录已有审核操作，无法修改！";
		
		}
		
		if(Base.isNull(message)){
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}

}
