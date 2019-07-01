package xsgzgl.xsxx.qtxx;

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
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpInit;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpModel;
import xsgzgl.comm.BasicModel;
import xsgzgl.xsxx.bzrpy.bzrpygl.XsxxBzrpyInit;
import xsgzgl.xsxx.bzrpy.bzrpygl.XsxxBzrpyModel;
import xsgzgl.xsxx.bzrpy.bzrpygl.XsxxBzrpyService;
import xsgzgl.xsxx.bzrpy.bzrpygl.XsxxPySaveModel;

import com.zfsoft.basic.BasicAction;

public class XsxxQtxxAjax  extends BasicAction {
	
	// 表名
	private final String TABLENAME = "xg_xsxx_qtxxb";
	
	// 保存字段
	private final String[] SAVE = {"xh","xmdm","zd1","zd2","zd3","zd4",
			"zd5","zd6","zd7","zd8","zd9","zd10","zd11","zd12","zd13",
			"zd14","zd15","zd16","zd17","zd18","zd19","zd20","zd21","zd22",
			"zd23","zd24","zd25","zd26",
			"czr","czsj"};
	
	// 主键
	private final String[] pk = {"xh","xmdm"};
	
	XsxxQtxxService service = new XsxxQtxxService();

	XsxxBzrpyInit init = new XsxxBzrpyInit();
	
	/**
	 * 批量审核保存
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
		
		HashMap<String,String>valueMap=service.getValueMap(request, SAVE);
		
		// ---------------------固定的保存值 begin------------
		valueMap.put("czr",user.getUserName());
		
		valueMap.put("czsj",GetTime.getNowTime2());
		// ---------------------固定的保存值 end--------------
		
		//消息信息
		String message="";
		
		//保存数据方法
		boolean flag=false;
		
		// --------------表名------------
		model.setTableName(TABLENAME);
		// --------------需要保存的值--------------------
		model.setValueMap(valueMap);
		
		flag=service.saveInfo(model);
		
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 单个修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		model.setPk(pk);
		
		HashMap<String,String>valueMap=service.getValueMap(request, SAVE);
		
		// ---------------------固定的保存值 begin------------
		valueMap.put("czr",user.getUserName());
		
		valueMap.put("czsj",GetTime.getNowTime2());
		// ---------------------固定的保存值 end--------------
		
		//消息信息
		String message="";
		
		//保存数据方法
		boolean flag=false;
		
		// --------------表名------------
		model.setTableName(TABLENAME);
		// --------------需要保存的值--------------------
		model.setValueMap(valueMap);
		
		flag=service.updateInfo(model);
		
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	

	

	
	/**
	 * 查询综合测评（综测信息）维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qtxxSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxQtxxForm myForm=(XsxxQtxxForm)form;
		
		XsxxQtxxInit init = new XsxxQtxxInit();
		
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
		
		String xmdm=valueMap.get("xmdm");

		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		
		searchModel.setSearch_tj_xmdm(new String[]{xmdm});
		
		model.setSearchModel(searchModel);
		
		model.setColList(new String[]{"pkValue","xh","xm","nj","xymc","zymc","bjmc"});
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		
		// 标题
		List<HashMap<String, String>> topTr = service.getTopTr(model,
				user);
		
		
		// 结果集
		ArrayList<String[]> rsArrList =(ArrayList<String[]>)service.getXsQtxxList(model);
		
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
		
		model.setPk(pk);
		
		String pkStr=service.unicode2Gbk(request.getParameter("pkValue"));
		
		String[]pkValue=pkStr.split("!!array!!");
		
		//消息信息
		String message="";
		
		//保存数据方法
		boolean flag=false;
		
		// --------------表名------------
		model.setTableName(TABLENAME);
	
		//主键
		model.setPkValue(pkValue);
		
		//批量删除
		flag=service.batchDelete(model);
		
		message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
				: MessageInfo.MESSAGE_DEL_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
}
