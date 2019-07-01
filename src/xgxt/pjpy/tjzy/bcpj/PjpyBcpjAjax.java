package xgxt.pjpy.tjzy.bcpj;

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
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpInit;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpModel;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;

public class PjpyBcpjAjax extends BasicAction {

	// 表名
	private final String TABLENAME = "xg_pjpy_pjxmsqb";

	// 主键
	private final String[] pk = {"pjxn","pjxq","pjnd","xmdm","xh"};
	
	PjpyBcpjService service = new PjpyBcpjService();

	PjpyBcpjInit init = new PjpyBcpjInit();

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
		//删除无效数据
		service.deletePjxmInfo();
		
		message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
				: MessageInfo.MESSAGE_DEL_FALSE;
		
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
	public ActionForward searchZhcpZcxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();
		PjpyZhcpModel model = new PjpyZhcpModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initZhcp(rForm, myForm, user, request);
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		// 项目代码
		String xmdm = otherValue[1];
		model.setXmdm(xmdm);
		
		// 项目名称
		String xmmc = otherValue[2];
		
		// 来源表
		String lyb =otherValue[3];
		model.setLyb(lyb);
		
		//项目级别
		String xmjb =otherValue[4];
		model.setXmjb(xmjb);
		
		if(!Base.isNull(xmmc)){
			model.setXmmc(myService.unicode2Gbk(xmmc));
		}
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		
		// 标题
		List<HashMap<String, String>> topTr = service.getZhcpZcxxTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getZhcpZcxxList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createZhcpZcxxHTML(rsModel, model, rsArrList,
				user);
		
		// 构建学校个性化信息隐藏域
		spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
}
