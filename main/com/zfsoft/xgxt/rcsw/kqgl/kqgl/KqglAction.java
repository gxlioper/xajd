/**
 * @部门:学工产品事业部
 * @日期：2015-8-31 下午02:42:18 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqgl;

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
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;

@SuppressWarnings("unchecked")
public class KqglAction extends SuperAction {
	
	private KqglService service = new KqglService();
	private static List<HashMap<String, String>> jbxxList = null;
	private static final String KQGL = "kqgl";
	BdpzService bdpzService = new BdpzService();
	
	private static final String url = "rcsw_kqjg_cx.do";
	
	/**
	 * 
	 * @描述:查询考勤结果
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-9-1 上午09:27:20
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
	public ActionForward viewKqjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqglForm model = (KqglForm) form;
		if(QUERY.equals(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_kqjg_cx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewKqjgList");
	}
   
	/**
	 * 
	 * @描述:查看单个考勤结果
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-9-1 上午09:27:44
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
	public ActionForward viewKqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqglForm model = (KqglForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		KqglForm myForm = service.getModel(model.getId());
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(KQGL);
		request.setAttribute("jbxxList", jbxxList);
		if (model != null) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			xsjbxx.remove("xymc");
			xsjbxx.put("xymc", myForm.getXymc());
			xsjbxx.remove("zymc");
			xsjbxx.put("zymc", myForm.getZymc());
			xsjbxx.remove("bjmc");
			xsjbxx.put("bjmc", myForm.getBjmc());
			
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("viewKqjg");
	}
	
	/**
	 * 
	 * @描述:增加考勤结果
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-9-1 上午09:27:56
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
	@SystemLog(description="访问日常事务-考勤管理-考勤管理-增加考勤结果XH:{xh},XN:{xn},XQ:{xq},ZC:{zc},KQKC:{kqkc},KQSJ:{kqsj},KQLXDM:{kqlxdm}")
	public ActionForward addKqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqglForm model = (KqglForm) form;
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}

		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(KQGL);
		request.setAttribute("jbxxList", jbxxList);
		if (!StringUtil.isNull(model.getXh())) {
			// 如学号不为空，加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			// 唯一性判断（学号，考勤时间，考勤类型）
			boolean isExist = service.isKqjgExists(model);
			if (!isExist) {
				super.resetToken(request);
				model.setZjsj(DateUtils.getCurrTime());
				// 添加学生考勤登记
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}
		request.setAttribute("kqlblist", service.getList("xg_rcsw_kqgl_kqlbdmb"));
		request.setAttribute("qkjblblist", service.getList("xg_rcsw_kqgl_qkjblbdmb"));
		request.setAttribute("ybqkjblist", service.getList("xg_rcsw_kqgl_ybqkjbdmb"));
		request.setAttribute("dqztlist", service.getList("xg_rcsw_kqgl_mqzkdmb"));
		
		String path = "rcsw_kqgl_kqgljg.do?method=addKqjg";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("addKqjg");
	}
	/**
	 * 
	 * @描述:更新考勤结果
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-9-1 上午09:28:10
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
	@SystemLog(description="访问日常事务-考勤管理-考勤管理-修改考勤结果ID:{id},XH:{xh},XN:{xn},XQ:{xq},ZC:{zc},KQKC:{kqkc},KQSJ:{kqsj},KQLXDM:{kqlxdm}")
	public ActionForward updateKqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqglForm model = (KqglForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		KqglForm myForm = service.getModel(model.getId());
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			xsjbxx.remove("xymc");
			xsjbxx.put("xymc", myForm.getXymc());
			xsjbxx.remove("zymc");
			xsjbxx.put("zymc", myForm.getZymc());
			xsjbxx.remove("bjmc");
			xsjbxx.put("bjmc", myForm.getBjmc());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.isKqjgExists(model);
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_SAVE_FAIL, false));
			}
			return null;
		}
		request.setAttribute("kqlblist", service.getList("xg_rcsw_kqgl_kqlbdmb"));
		request.setAttribute("qkjblblist", service.getList("xg_rcsw_kqgl_qkjblbdmb"));
		request.setAttribute("ybqkjblist", service.getList("xg_rcsw_kqgl_ybqkjbdmb"));
		request.setAttribute("dqztlist", service.getList("xg_rcsw_kqgl_mqzkdmb"));
		
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		jbxxList = bdpzService.getJbxxpz(KQGL);
		request.setAttribute("jbxxList", jbxxList);		
		return mapping.findForward("updateKqjg");
	}
	
	/**
	 * 
	 * @描述:删除考勤结果
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-9-1 上午09:28:24
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
	@SystemLog(description="访问日常事务-考勤管理-考勤管理-删除VALUES:{values}")
	public ActionForward deleteKqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

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
	 * 
	 * @描述:导出数据
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-9-1 上午09:27:12
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
		KqglForm model = (KqglForm) form;
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
		file.delete();
		return null;
	}
}
