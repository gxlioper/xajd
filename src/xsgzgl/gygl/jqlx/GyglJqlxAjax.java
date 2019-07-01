package xsgzgl.gygl.jqlx;

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
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.hzny.HznyXljkZxzxForm;
import xgxt.xljk.hzny.HznyXljkZxzxInit;
import xsgzgl.comm.BasicModel;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXmshInterface;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpInit;
import xsgzgl.rcsw.zjbb.RcswZjbbDAO;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-7-20 下午02:05:02</p>
 */
public class GyglJqlxAjax extends BasicAction {
	
	GyglJqlxService service = new GyglJqlxService();
	
	GyglJqlxInit init =new GyglJqlxInit();
	
	/**
	 * 假期留校申请查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveLxsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		init.initLxszSave(model, request);
		
		
		flag=service.batchDelete(model);
		
		if(flag){
		
			flag = service.saveInfo(model);
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
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] {"id"});

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		// --------------表名------------
		model.setTableName("xg_gygl_jqlxsqb");

		// 主键
		model.setPkValue(pkValue);

		// 批量删除

		flag = service.batchDelete(model);
		
		if(flag){
			model.setPk(new String[] {"sqid"});
			model.setTableName("xg_gygl_jqlxshb");
			service.batchDelete(model);
			
			//=====删除待办===2013-1-15=====qph====begin===========
			MyJobsManager manager = new MyJobsImpl();
			manager.removeJob(pkValue, "假期留校");
			//=====删除待办===2013-1-15=====qph====end==============
		}

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 假期留校申请查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jqlxsqSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglJqlxForm myForm = (GyglJqlxForm) form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		
		init.initLxsqSearch(rForm, model, request, user);

		// 结果集显示字段
		String[]otherValue=request.getParameter("otherValue").split("!!@@!!");

		String ie=otherValue[0];
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
				user, model.getPath());

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getLxsqList(model);

		// 构建结果集
		String spHtml = service
				.createlxsQSearchHTML(rsModel, model, rsArrList, user);

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
	 * 假期留校审核查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jqlxshSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglJqlxForm myForm = (GyglJqlxForm) form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		model.setColList(new String[] { "id","xh","xm","nj","bjmc","sqsj","shzt" });
		
		model.setPath("jqlx_lxsh.do");
		init.initLxshSearch(rForm, model, request, user);

		// 结果集显示字段

		String[]otherValue=request.getParameter("otherValue").split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		String stylePath=otherValue[1];
		rsModel.setStylePath(stylePath);
		
		// 
		String spgw = otherValue[2];
		model.getValueMap().put("spgw", spgw);

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
				user, "jqlx_lxsh.do");

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getLxshList(model);

		// 构建结果集
		String spHtml = service
				.createLxshSearchHTML(rsModel, model, rsArrList, user);

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
	 * 假期留校结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jqlxjgSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglJqlxForm myForm = (GyglJqlxForm) form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		model.setColList(new String[] { "id", "xh", "xm", "nj", "yjzxsj",
				"sqsj", "sqjg" });
		
		init.initLxjgSearch(rForm, model, request, user);

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
				user,model.getPath());

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getLxjgList(model);

		// 构建结果集
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

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
	 * 假期留校结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyglJqlxForm myForm = (GyglJqlxForm) form;
		
		service.setJbszInfo(myForm);
		
		User user=getUser(request);

		BasicModel model = new BasicModel();
		
		model.setUser(user);

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		init.initLxsqSave(model, request);
		
		
		flag = service.saveInfo(model);
		
		if(flag){
		
			HashMap<String,String> map = model.getValueMap();
			String id=map.get("id");
			
			if(!"no".equalsIgnoreCase(myForm.getLcid()))
			flag = service.saveJqlxShb(id,user);
			
			
			//========待办工作  2013-1-15 qph beign=========
			String xh = map.get("xh");
			String lcid = map.get("lcid");
			
			String[] spgw = service.getSpgwByLcid(lcid);
			
			if (null != spgw && spgw.length > 0){
				Job job = Job.getJobInstance(id, xh, spgw[0], 
						"gyglnew_jqlx.do?method=lxshManage&spgw="+spgw[0], 
						"jqlx_lxjg.do","假期留校", "假期留校");
				MyJobsManager manager = new MyJobsImpl();
				manager.pushJob(job);
			}
			//========待办工作  2013-1-15 qph end=========
			
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
	 * 假期留校结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BasicModel basicModel=new BasicModel();
		
		GyglJqlxForm myForm = (GyglJqlxForm) form;
		
		init.initJqlxSh(basicModel, request);

		User user = getUser(request);// 用户对象
	
		// ============= end ============

		String message="";
		
		boolean flag = false;
	
		flag = service.updateShzt(myForm,basicModel,request, user);
		message = flag ? "审核成功" : "审核失败，请联系相关人员";
	
		if (flag){
			//===待办工作=====2013-1-15 qph==beign======
			
			String[] id = myForm.getSqid();
			String curShgw = myForm.getSpgw();
			String[] spgw = service.getSpgwByLcid(myForm.getLcid());
			int index = StringUtils.getStrIndexInArray(curShgw, spgw);
			
			for (String str : id){
				Job job = null;
				if ("tg".equals(myForm.getShzt())) {
					String nextShgw = index!=spgw.length-1 && spgw[index+1] != null ? spgw[index+1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"gyglnew_jqlx.do?method=lxshManage&spgw="+nextShgw,"假期留校");
				}  else {
					job = Job.getJobInstance(str,"假期留校");
				}
				MyJobsManager manager = new MyJobsImpl();
				manager.updateJob(job);
			}
			
			//===待办工作=====2013-1-15 qph==end=========
		}
		
		
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
//
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 弹出审核岗位选择DIV
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showShgwDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		GyglJqlxForm myForm=(GyglJqlxForm)form;
		
		User user = getUser(request);// 用户对象

		service.setJbszInfo(myForm);
		// ============= 初始化各变量的值 ============
		
		service.showShgwDiv(myForm, user ,response);
			
		return null;
	}
	
	/**
	 * 弹出审核岗位选择DIV
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkChangeShlc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		GyglJqlxForm myForm=(GyglJqlxForm)form;
		
		String lcid=request.getParameter("lcid");
		
		service.setJbszInfo(myForm);
		// ============= 初始化各变量的值 ============
		
		boolean flag=service.checkShjs(lcid);
		
		String message = flag ? "" : "该审批流程尚有申请记录<font color='blue'>未审核完成,无法更换</font>其他审批流程！";
		
		response.setContentType("text/html;charset=gbk");
//
		response.getWriter().print(message);
			
		return null;
	}
}
