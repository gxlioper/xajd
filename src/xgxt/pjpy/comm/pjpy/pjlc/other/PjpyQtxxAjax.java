package xgxt.pjpy.comm.pjpy.pjlc.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.xljk.hzny.HznyXljkZxzxForm;
import xgxt.xljk.hzny.HznyXljkZxzxInit;
import xgxt.xszz.whtl.ybbx.XszzYbbxForm;
import xgxt.xszz.whtl.ybbx.XszzYbbxSaveModel;
import xsgzgl.comm.BasicModel;

public class PjpyQtxxAjax extends BasicAction{
	
	PjpyQtxxService service = new PjpyQtxxService();
	
	PjpyQtxxInit init = new PjpyQtxxInit();
	
	// -------------------------咨询师信息管理 begin ------------------------------------
	/**
	 * 团学干部查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qtxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyQtxxForm myForm = (PjpyQtxxForm) form;

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		
		myForm.getSearchModel().setPath("pjpy_qtxx_qtjl.do");
		
		init.initQtxxManage(rForm, request);

		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);

		// =================== end ===================
	    
		List<HashMap<String, String>> topTr = service.getTopTr();

		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getQtxxList(myForm);

		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel,rsArrList, user);

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
	 * 批量删团学干部信息
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

		model.setPk(new String[] { "xn","xh"});

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// 消息信息
		String message = "";

		// 保存数据方法
		boolean flag = false;

		// --------------表名------------
		model.setTableName("xg_pjpy_qtjlb");

		// 主键
		model.setPkValue(pkValue);


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
	
	/**
	 * 审核保存
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
		
		//PjpyQtxxForm myForm=(PjpyQtxxForm)form;
		
		User user=getUser(request);
		
		BasicModel basicModel=new BasicModel();
		
		QtxxSaveModel saveModel=new QtxxSaveModel();
		
		// ------通过构造方法初始化 XszzYbbxSaveModel、BasicModel的数据-------------
		init.initSaveBatch(basicModel, saveModel, request);
		
		//消息信息
		String message="";
		
		//保存数据方法
		boolean flag=false;
		
		flag=service.saveBatch(basicModel,saveModel);

		if(Base.isNull(message)){
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
}
