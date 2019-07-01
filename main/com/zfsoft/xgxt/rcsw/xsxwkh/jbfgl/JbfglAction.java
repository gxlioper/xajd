/**
 * @部门:学工产品事业部
 * @日期：2016-8-2 下午04:08:46 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.jbfgl;

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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
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

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生基本分管理
 * @作者： caopei[工号:1352]
 * @时间： 2016-8-2 下午04:08:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */


public class JbfglAction extends SuperAction<JbfglForm, JbfglService> {
	private final String RCSW="rcswrcxw";
	private JbfglService service = new JbfglService();
	private static final String url = "xsxwkh_jbfgl.do";
	
	/**
	 * 基本分列表
	 */
	@SystemAuth(url = url)
	public ActionForward getjbflist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			JbfglForm model = (JbfglForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
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
			// 默认高级查询条件(当前学年)
			SearchModel searchModel = new SearchModel();
			searchModel.setSearch_tj_xn(new String[] { Base.currXn });
			request.setAttribute("searchTj", searchModel);
			request.setAttribute("path", url);
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("getjbflist");
	}
			

	/**
	 * @描述: 增加
	 */
	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
		@SystemLog(description = "访问日常事务-基本分管理--增加XH:{xh}")
	public ActionForward jbfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JbfglForm myForm = (JbfglForm) form;
		JbfglService service = new JbfglService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 取当前学年
		myForm.setXn(Base.currXn);
		request.setAttribute("xn", Base.currXn);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			// 唯一性判断
			boolean isExist = service.isExistQysj(myForm);
			if (!isExist) {
				boolean result = service.runInsert(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XSXWKH_JBFGL_ADD_EXIST));
			}
			return null;
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSW);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xsxwkhJbfgl.do?method=jbfAdd";
		request.setAttribute("path", path);
		return mapping.findForward("jbfAdd");
	}
	
	
	/**
	 * @描述:【修改】
	 */
	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	public ActionForward jbfUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JbfglService service = new JbfglService();
		JbfglForm model = (JbfglForm) form;
		JbfglForm myForm = service.getModel(model);
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("xn", myForm.getXn());
		request.setAttribute("jbfid", myForm.getJbfid());
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.isExistQysj(model);
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String message = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(message));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_STUDENT, false));
			}

			return null;
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSW);
		request.setAttribute("jbxxList", jbxxList);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		request.setAttribute("xscpdj", model.getXscpdj());
		request.setAttribute("bzrcpdj", model.getBzrcpdj());
		return mapping.findForward("jbfUpdate");
	}
		
		

	/**
	 * @描述: 删除
	 */
	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-基本分管理-删除VALUES:{values}")
	public ActionForward JbfDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JbfglService service = new JbfglService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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
	 * @描述: 导出功能
	
	 */				
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JbfglService service=new JbfglService();
		JbfglForm model=(JbfglForm) form;
		
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
	 * @描述: 点击学号查看

	 */
	@SystemAuth(url = url)
	public ActionForward jbfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JbfglService service = new JbfglService();
		JbfglForm model = (JbfglForm) form;

		JbfglForm myForm = service.getModel(model);
		request.setAttribute("bz", myForm.getBz());
		request.setAttribute("xn", myForm.getXn());
		request.setAttribute("zf",Integer.parseInt(myForm.getBzrcpfz())+Integer.parseInt(myForm.getXscpfz()));
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSW);
		request.setAttribute("jbxxList", jbxxList);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("jbfView");
	}
		
		
	
		
}
