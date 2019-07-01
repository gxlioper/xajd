/**
 * @部门:学工产品事业部
 * @日期：2014-5-14 下午02:09:18 
 */  
package com.zfsoft.xgxt.szdw.bjxwjl.bjxwjlwh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @类功能描述: 班级信息记录维护
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-14 下午02:09:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjxwjlwhAction extends SuperAction {

	/**
	 * path 路径
	 */
	private static final String PATH = "szdw_bjxwjlwh.do?method=bjxwjlwhManage";
	
	private static final String url = "szdw_bjxwjlwh.do?method=bjxwjlwhManage";
	
	/**
	 * servive
	 */
	private static BjxwjlwhService service = new BjxwjlwhService();
	
	/**
	 * 班级信息记录维护管理
	 */
	@SystemAuth(url = url)
	public ActionForward bjxwjlwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjxwjlwhManage");
	}
	
	/**
	 * 
	 * @描述:检索数据列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:43:04
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
	@SystemAuth(url = url)
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjlwhForm model = (BjxwjlwhForm) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//查询
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:申请
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		//辅导员信息
		HashMap<String , String> fdyxx = service.getFdyxx(user.getUserName());
		//辅导员班级列表
		List<HashMap<String , String>> fdybjxxList = service.getFdyBjxxList(user.getUserName());
		//类别信息
		List<HashMap<String , String>> lbxxList = service.getLbList();
		
		request.setAttribute("lbxxList", lbxxList); 
		request.setAttribute("fdybjxxList", fdybjxxList); 
		request.setAttribute("fdyxx", StringUtils.formatData(fdyxx));
		String path = "szdw_bjxwjlwh.do?method=sq";
		request.setAttribute("path", path);
		return mapping.findForward("sq");
	}
	
	/**
	 * 
	 * @描述:申请Action
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-班级行为记录-班级行为记录维护-增加XNDSDM:{xndsdm},JSONDATA:{jsondata}")
	public ActionForward sqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjlwhForm model = (BjxwjlwhForm) form;

		//解析记录
		List<?> records = JsonUtil.jsonArrToList(model.getJsondata(), BjxwjlwhForm.Record.class);
		
		boolean isSuccess = service.saveBjjl(model.getXn(), model.getXqdm(), model.getJlr(), records);
		
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		
		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * 
	 * @描述:更新Action
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-班级行为记录-班级行为记录维护-修改GUID:{guid},XNDSDM:{xndsdm},JLNR:{jlnr},JLR:{jlr},JLSJ:{jlsj}")
	public ActionForward updateAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjlwhForm model = (BjxwjlwhForm) form;

		boolean isSuccess = service.runUpdate(model);
		
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		
		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * 
	 * @描述:删除
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-班级行为记录-班级行为记录维护-删除GUIDS:{guids}")
	public ActionForward deleteAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String sqids = request.getParameter("guids"); //带删除的sqids
		
		int isSuccess = service.runDelete(sqids.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:修改
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjlwhForm model = (BjxwjlwhForm) form;
		
		if(model.getGuid() != null){
			//辅导员班级列表
			List<HashMap<String , String>> fdybjxxList = service.getFdyBjxxList(service.getModel(model.getGuid()).getJlr());
			//类别信息
			List<HashMap<String , String>> lbxxList = service.getLbList();
			
			request.setAttribute("lbxxList", lbxxList); 
			
			request.setAttribute("fdybjxxList", fdybjxxList); 
			
			request.setAttribute("bjxwjlxx", service.getModelMap(model.getGuid())); 
		}

		String path = "szdw_bjxwjlwh.do?method=updateSq";
		request.setAttribute("path", path);
		return mapping.findForward("updateSq");
	}
	
	/**
	 * 
	 * @描述:查看
	 */
	@SystemAuth(url = url)
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjlwhForm model = (BjxwjlwhForm) form;
		request.setAttribute("bjxwjlxx", service.getModelMap(model.getGuid())); 
		String path = "szdw_bjxwjlwh.do?method=ck";
		request.setAttribute("path", path);
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @描述:导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjlwhForm model = (BjxwjlwhForm) form;
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
	
}
