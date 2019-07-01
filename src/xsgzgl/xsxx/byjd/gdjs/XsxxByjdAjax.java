package xsgzgl.xsxx.byjd.gdjs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xsgzgl.comm.BasicModel;
import xsgzgl.xsxx.bzrpy.bzrpygl.XsxxBzrpyInit;

import com.zfsoft.basic.BasicAction;

public class XsxxByjdAjax  extends BasicAction {
	
	// 表名
	private final String TABLENAME = "xg_xsxx_byjdb";
	
	// 保存字段
	private final String[] SAVE = {"xh","byjd","czr","czsj"};
	
	// 主键
	private final String[] pk = {"xh"};
	
	XsxxByjdService service = new XsxxByjdService();

	XsxxBzrpyInit init = new XsxxBzrpyInit();
	
	/**
	 * 保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		XsxxByjdSaveModel saveModel=new XsxxByjdSaveModel();
	
		model.setPk(pk);
	
		service.getModelValue(saveModel, request);
		
		// ---------------------固定的保存值 begin------------
		saveModel.setCzr(user.getUserName());
		saveModel.setCzsj( GetTime.getNowTime2());
		
		// ---------------------固定的保存值 end--------------
		model.setPkValue(saveModel.getPkValue());
		
		model.setOneZd(new String[]{"byjd","czr","czsj"});
		
		model.setArrayZd(new String[]{"xh"});
		
		
		//消息信息
		String message="";
		
		//保存数据方法
		boolean flag=false;
		
		// --------------表名------------
		model.setTableName(TABLENAME);
		// --------------需要保存的值--------------------
		flag=service.saveBatch(model,saveModel);
		
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

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
	public ActionForward byjdSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxByjdForm myForm=(XsxxByjdForm)form;
		
		XsxxByjdInit init = new XsxxByjdInit();
		
		BasicModel model = new BasicModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		
		init.initXsqtxx(rForm, myForm, user, request);
		
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
		
		model.setColList(new String[]{"pkValue","xh","xm","nj","xymc","zymc","bjmc","sfyjd"});
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		
		// 标题
		
		List<HashMap<String, String>> topTr = service.getTopTr(model,
				user);
		
		
		// 结果集
		ArrayList<String[]> rsArrList =(ArrayList<String[]>)service.getByjdList(model);
		
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
	 * 显示详细信息DIV
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDetailDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxByjdForm myForm=(XsxxByjdForm)form;
		
		User user = getUser(request);// 用户对象

		// ==================构建前台页面========================
		service.showDetailDiv(myForm, user, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 显示详细信息DIV
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showByjdDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxByjdForm myForm=(XsxxByjdForm)form;
		
		User user = getUser(request);// 用户对象

		// ==================构建前台页面========================
		service.showByjdDiv(myForm, user, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	
	
}
