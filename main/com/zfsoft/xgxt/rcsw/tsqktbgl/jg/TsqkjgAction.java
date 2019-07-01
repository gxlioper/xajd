/**
 * @部门:学工产品事业部
 * @日期：2016-3-21 下午04:33:36 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.jg;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.tsqktbgl.sq.TsqktbService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-21 下午04:33:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsqkjgAction extends SuperAction<TsqkjgForm, TsqkjgService>{
private static final String url = "rcsw_tsqktbgl_jg.do";
	
	private TsqkjgService service = new TsqkjgService();
	private TsqktbService tsqktbService = new TsqktbService();
	
	private static final String TSQKTBSQ = "tsqktbsq";
	private static List<HashMap<String, String>> jbxxList = null;
	
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(TSQKTBSQ);
	}
	
	/** 
	 * @描述:查询
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-21 下午05:12:33
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
	public ActionForward getTsqkjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsqkjgForm model = (TsqkjgForm) form;
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
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_tsqktbgl_jg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getTsqkjgList");
	}
	
	/** 
	 * @描述:增加 
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-22 上午08:39:58
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
	public ActionForward addTsqkjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsqkjgForm model = (TsqkjgForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学年 学期list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setTxr(user.getUserName());
		model.setTbsj(GetTime.getTimeByFormat("yyyy-mm-dd"));
		String path = "tsqktbgl_jg.do?method=addTsqkjg";
		request.setAttribute("path", path);
		request.setAttribute("xnsr", Base.currXn);
		request.setAttribute("xqsr", tsqktbService.getXqmc(Base.currXq));
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xqflList", tsqktbService.getXqflList());
		return mapping.findForward("addTsqkjg");
	}
	
	/** 
	 * @描述:保存
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-22 上午08:47:01
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
	public ActionForward saveTsqkjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsqkjgForm myForm = (TsqkjgForm) form;
		if (service.isHaveRecordForjg(myForm)) {
			String message = MessageUtil.getText(MessageKey.RCSW_TSQKTB_REPEATED);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		myForm.setSjly("0");
		boolean result = service.saveTsqkjg(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:修改
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-22 上午09:24:23
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
	public ActionForward editTsqkjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsqkjgForm myForm = (TsqkjgForm) form;
		TsqkjgForm model = service.getModel(myForm);
		if (null != model) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		// 加载项目奖项信息
		request.setAttribute("xnsr", model.getXn());
		request.setAttribute("xqsr", tsqktbService.getXqmc(model.getXq()));
		request.setAttribute("xqflList", tsqktbService.getXqflList());
		String path = "tsqktbgl_jg.do?method=editTsqkjg";
		request.setAttribute("path", path);
		return mapping.findForward("editTsqkjg");
	}
	
	/** 
	 * @描述:删除
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-22 上午09:40:23
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
	public ActionForward delTsqkjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	 * @描述:查看
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-22 上午09:44:53
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
	public ActionForward viewTsqkjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsqkjgForm model = (TsqkjgForm) form;
		Map<String,String> map = service.getTbjgxx(model);
		request.setAttribute("rs", map);
		request.setAttribute("jbxxList", jbxxList);
		TsqkjgForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, viewForm);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		return mapping.findForward("viewTsqkjg");
	}
	
	/** 
	 * @描述:导出数据
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-22 下午01:34:39
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
		TsqkjgForm model = (TsqkjgForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
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
