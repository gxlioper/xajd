/**
 * @部门:学工产品事业部
 * @日期：2013-8-2 上午10:12:36 
 */  
package com.zfsoft.xgxt.xpjpy.tsxs;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 新评奖管理模块
 * @类功能描述: 特殊学生维护
 * @作者：CQ [工号：785]
 * @时间： 2013-8-2 上午10:12:36 
 * @版本： V1.0O
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsxsAction extends SuperAction {
	private String messageKey;
	
	private static final String url = "xpj_tsxs.do?method=viewTsxsTj&mklx=pjpy";
	
	/**
	 * 
	 * @描述:特殊学生统计列表，以学年学期类型进行统计显示
	 * @作者：ligl
	 * @日期：2013-8-15 上午11:24:57
	 * @修改记录: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward viewTsxsTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsxsModel model = (TsxsModel) form;
		TsxsService service = new TsxsService();
		String mklx = request.getParameter("mklx");
		
		if (QUERY.equals(model.getType())){
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		CsszService csszService = new CsszService();
		String currXnXqmc = csszService.getModel().getZqmc();// 当前学年学期
		request.setAttribute("currXnXqmc", currXnXqmc);
		
		List<HashMap<String, String>> xnList = service.getXnList();//查询条件，学年
		request.setAttribute("xnList", xnList);
				
		List<HashMap<String, String>> xqList = Base.getXqList();//查询条件，学期
		request.setAttribute("xqList", xqList);
		
		String path = "xpj_tsxs.do?method=viewTsxsTj&mklx="+mklx;
		request.setAttribute("path", path);
		request.setAttribute("mklx", model.getMklx());
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewTsxsTj");
	}
	/**
	 * 
	 * @描述:特殊学生信息
	 * @作者：ligl
	 * @日期：2013-8-16 下午02:03:15
	 * @修改记录: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward tsxsXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsxsModel model = (TsxsModel) form;
		TsxsService service = new TsxsService();
		String xn = request.getParameter("xn");
		request.setAttribute("xn", xn);
		String xq = request.getParameter("xq");
		request.setAttribute("xqmc", service.getXqmc(xq));
		String lxdm = request.getParameter("lxdm");
		request.setAttribute("lxmc", service.getLxmc(lxdm));

		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "pj_tsxs.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tsxsXx");
	}

	/**
	 * 
	 * @描述:特殊学生未添加列表
	 * @作者：tgj[工号：1075]
	 * @日期：2017-7-11 上午09:04:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward tsxsDtjXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsxsModel model = (TsxsModel) form;
		TsxsService service = new TsxsService();
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		//查询
		List<HashMap<String,String>> resultList = service.getTsxsDtjPageList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:特殊学生新增
	 * @作者：ligl
	 * @日期：2013-8-15 下午02:44:28
	 * @修改记录: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward tsxsZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsModel myForm = (TsxsModel) form;
		TsxsService service = new TsxsService();
		if (QUERY.equals(myForm.getType())) {
			//加载特殊学生类型
			List<HashMap<String, String>> tslxList = service.getTslxList();
			JSONArray dataList = JSONArray.fromObject(tslxList);
			response.getWriter().print(dataList);
			return null;
		}
		String mklx = request.getParameter("mklx");
		String currXnXqmc = null;
		String currXn = Base.currXn;
		String currXq = Base.currXq;
		if("pjpy".equals(mklx)){
			CsszService csszService = new CsszService();
			currXnXqmc = csszService.getModel().getZqmc();// 评奖周期
			CsszModel csszModel = csszService.getModel();
			currXn = csszModel.getXn();// 当前学年
			currXq = csszModel.getXq();// 当年学期
		}else{
			 currXnXqmc = Base.currXn+" "+Base.getDqxqmc();
		}
		request.setAttribute("currXn", currXn);
		request.setAttribute("currXq", currXq);
		request.setAttribute("mklx", mklx);
		request.setAttribute("currXnXqmc", currXnXqmc);
		this.saveToken(request);
		return mapping.findForward("tsxsZj");
	}
	
	/**
	 * 
	 * @描述:特殊学生生成 
	 * @作者：ligl
	 * @日期：2013-8-15 下午03:59:59
	 * @修改记录: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemLog(description="访问评奖评优-特殊学生-特殊学生维护-保存-MKLX:{mklx},LXDM:{lxdm},TSXSXH:{tsxsxh}")
	public ActionForward tsxsCreate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		TsxsModel model = (TsxsModel) form;
		TsxsService service = new TsxsService();
		User user = getUser(request);
		
		String mklx = request.getParameter("mklx");
		if("pjpy".equals(mklx)){
			CsszService csszService = new CsszService();
			CsszModel csszModel = csszService.getModel();
			String currXn = csszModel.getXn();// 当前学年
			String currXq = csszModel.getXq();// 当年学期
			model.setXn(currXn);
			model.setXq(currXq);
		}else{
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
		}
		boolean result = service.saveTsxs(model, user);
		messageKey = result?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonResult(messageKey, result));
		return null;
	}

	/**
	 * 删除特殊人员信息
	 */
	@SystemLog(description="访问评奖评优-特殊学生-特殊学生维护-删除-VALUES:{values}")
	public ActionForward delTsxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TsxsService service = new TsxsService();
		
		String values = request.getParameter("values");
		boolean result = service.delTsxs(values);
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS
				: MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;

	}
	
	/**
	 * 
	 * @描述:根据学年学期类型，删除特殊学生
	 * @作者：ligl
	 * @日期：2013-8-16 下午01:58:28
	 * @修改记录: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemLog(description="访问评奖评优-特殊学生-特殊学生维护-删除-JSON:{json}")
	public ActionForward tsxsScForLx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TsxsService service = new TsxsService();
		
		String json = request.getParameter("json");
		boolean result = service.tsxsScForLx(json);
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS
				: MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;

	}
	
	
	/**
	 * 
	 * @描述:特殊学生维护
	 * @作者：cq [工号：785]
	 * @日期：2013-8-6 下午03:08:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsxsModel model = (TsxsModel) form;
		TsxsService service = new TsxsService();

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
		
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
	
	/**
	 * @throws IOException 
	 * @throws SQLException  
	 * @描述:批量增加特殊学生(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-26 下午02:35:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward plzjTsxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String lxdm = request.getParameter("lxdm");
		User user = getUser(request);
		String values = request.getParameter("values");
		TsxsService service = new TsxsService();
		boolean result = service.PlbcTsxs(values, xn, xq, lxdm, user.getUserName());
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
