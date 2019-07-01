package com.zfsoft.xgxt.xlzx.xlzxnew.xsxlpc;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

public class XsxlpcAction extends SuperAction<XsxlpcForm,XsxlpcService> {
	private final static String XSPCJG ="xspcjg";
	private XsxlpcService service = new  XsxlpcService();
	private static final String url = "xg_xlzxnew_xsxlpc.do";
	// 学生基本信息显示配置
	private final static BdpzService bdpzService = new BdpzService();
	private static List<HashMap<String, String>>  jbxxList = null;
	static{
		 jbxxList = bdpzService.getJbxxpz(XSPCJG);
	}
	/**
	 * 
	 * @描述：新生心理普测主页跳转Action
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-8 下午02:27:06
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
	public ActionForward getXsxlpcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述: 查询页面
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-8 下午02:29:23
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
	public ActionForward searchForXsxlpcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxlpcForm model = (XsxlpcForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(model, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-8 下午04:08:19
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
	@SystemLog(description = "访问心理健康咨询—新生心理普测—新生心理普查管理-增加")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxlpcForm model = (XsxlpcForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("jtqk", service.getJtqkMap(model.getXh()));
		}
		request.setAttribute("jbxxList", jbxxList);
		String path = "xlzxnew_xsxlpc.do?method=add";
		request.setAttribute("path", path);
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @描述:修改
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-8 下午04:10:04
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
	@SystemLog(description = "访问心理健康咨询—新生心理普测—新生心理普查管理-修改")
	public ActionForward updateJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxlpcForm model = (XsxlpcForm) form;
		XsxlpcForm myForm = service.getModel(model);
		if(myForm != null){
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("jtqk", service.getJtqkMap(myForm.getXh()));
		}
		request.setAttribute("jbxxList", jbxxList);
		String path = "xlzxnew_xsxlpc.do?method=updateJg";
		request.setAttribute("path", path);
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @描述: 保存结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-8 下午04:25:18
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
	public ActionForward saveJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxlpcForm model = (XsxlpcForm) form;
		boolean rs = true;
		User user = getUser(request);
		model.setJlr(user.getUserName());
		if(StringUtils.isNotNull(model.getId())){
			rs = service.runUpdate(model);
		}else{
			if(!service.checkIsNotExists(model.getXh())){
				String message = "该学生已在普测结果中，请勿重复填写！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			rs = service.runInsert(model);
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS :  MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-8 下午07:22:16
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
		
		XsxlpcForm model = (XsxlpcForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// 查询出所有记录，不分页

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-9 上午10:24:24
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
		XsxlpcForm model = (XsxlpcForm) form;
		XsxlpcForm myForm = service.getModel(model);
		if(myForm != null){
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("jtqk", service.getJtqkMap(myForm.getXh()));
		}
		request.setAttribute("jbxxList", jbxxList);
		String path = "xlzxnew_xsxlpc.do?method=ck";
		request.setAttribute("path", path);
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @描述: 删除结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-9 下午04:27:23
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
	@SystemLog(description = "访问心理健康咨询—新生心理普测—新生心理普查管理-删除id:{values}")
	public ActionForward delJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:关注或取消关注！
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-9 下午04:50:55
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
	@SystemLog(description = "访问心理健康咨询—新生心理普测—新生心理普查管理-是否关注设置id:{values}")
	public ActionForward sz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("ids");
		String sfgz = request.getParameter("sfgz");
		String message = "关注成功！";
		if("0".equals(sfgz)){
			message = "取消关注成功！";
		}
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			boolean rs = service.sz(ids, sfgz);
			message = rs ? message : "取消失败！";
			response.getWriter().print(getJsonMessage(message));
		}else{
			message = "请先选择记录，再进行操作！";
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
	}
}
