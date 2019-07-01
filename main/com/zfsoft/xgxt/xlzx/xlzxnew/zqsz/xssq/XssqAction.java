package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.xssq;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.xssq.XssqForm;

public class XssqAction extends SuperAction<XssqForm,XssqService> {
	private final String url = "xg_xlzxnew_xssq.do";
	private XssqService service = new XssqService();
	/**
	 * @描述 学生信息服务
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 * @描述 学生显示信息表单服务
	 */
	private BdpzService bdpzService = new BdpzService();
	
	/**
	 * @描述 学生表单数据列表
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * @属性： CDGL_BBID 表单配置id
	 */
	private static final String BBID = "xlzx_xlwy"; 
	/**
	 * @描述 ：初始化学生信息表单参数列表
	 */
	public XssqAction() {
		super();
		jbxxList = bdpzService.getJbxxpz(BBID);
	}
	/**
	 * 
	 * @描述: 学生授权页面跳转
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-10 上午11:05:01
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
	public ActionForward getXssqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xssq");
	}
	
	/**
	 * 
	 * @描述: 学生授权查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-10 下午04:00:15
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
	public ActionForward searchForXssQ(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XssqForm myForm = (XssqForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 增加学生
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-10 上午11:31:36
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
	public ActionForward addStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XssqForm model = (XssqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("path", "xlzxnew_xssq.do?method=addStu");
		return mapping.findForward("addstu");
	}
	
	/**
	 * 
	 * @描述: 新增授权保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-10 下午05:22:20
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
	public ActionForward saveXssqBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XssqForm model = (XssqForm) form;
		boolean isSuccess = false;
		JSONObject message = null;
		
		if(service.checkExist(model.getXh())){
			message = getJsonMessageByKey(MessageKey.XLZXWZDX_XSSQ_EXIST);
		}else{
			isSuccess = service.runInsert(model);
			message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		}
		
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 
	 * @描述: 修改学生
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-10 上午11:32:27
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
	public ActionForward editStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XssqForm model = (XssqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		XssqForm modelData = service.getModel(model.getXh());
		if(modelData != null){
			BeanUtils.copyProperties(model, StringUtils.formatData(modelData));
		}
		request.setAttribute("path", "xlzxnew_xssq.do?method=editStu");
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("editstu");
	}
	
	/**
	 * 
	 * @描述: 修改保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-10 下午05:33:15
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
	public ActionForward saveXgBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XssqForm model = (XssqForm) form;
		boolean isSuccess = false;
		JSONObject message = null;
		isSuccess = service.runUpdate(model);
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述:导出
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-30 下午04:59:15
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XssqForm model = (XssqForm) form;
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
	 * 
	 * @描述: 查看
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-11 下午05:11:00
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
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XssqForm model  = (XssqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		HashMap<String , String> modelData = service.getModelData(model.getXh());
		if(!StringUtil.isNull(model.getXh())){
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //查询学生基本信息
		}
		request.setAttribute("modelData", StringUtils.formatData(modelData)); 
		request.setAttribute("path","xlzxnew_xssq.do?method=ck");
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-11 下午05:27:45
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
	public ActionForward delAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String pks = request.getParameter("pks"); //带删除的sqids
		
		if(pks == null)
			pks = "";
		
		int isSuccess = service.runDelete(pks.split(","));
		
		String message = isSuccess >= 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
}
