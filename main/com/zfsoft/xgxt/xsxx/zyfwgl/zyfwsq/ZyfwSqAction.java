/**
 * @部门:学工产品事业部
 * @日期：2017年5月4日 下午2:21:52 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq;

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
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjcsz.ZyfwJcszService;
import common.newp.StringUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 志愿服务管理模块
 * @类功能描述: 志愿服务申请Action
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月4日 下午2:21:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwSqAction extends SuperAction<ZyfwSqForm,ZyfwSqService>{
	private final String ZYFW = "zyfw";	//用于学生基本信息显示配置
	private ZyfwJcszService zyfwJcszService = new ZyfwJcszService();
	private ZyfwSqService zyfwSqService = new ZyfwSqService();
	
	private static final String url = "xsxx_zyfwgl_sq.do?method=zyfwSqList";
	
	/**
	 * @描述:转到志愿服务申请列表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月4日 下午5:04:07
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
	public ActionForward zyfwSqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		
		//获取申请审核开关信息
		String[] sqshkg = zyfwJcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg[0]);
		request.setAttribute("path", url);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zyfwSqList");
	}
	
	/**
	 * @描述:获取志愿服务申请列表JSON数据
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月4日 下午5:45:07
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
	public ActionForward getZyfwSqListData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zyfwSqForm.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = zyfwSqService.getPageList(zyfwSqForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述:志愿服务申请的查看
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 下午4:43:20
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
	public ActionForward zyfwSqShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		ZyfwSqForm model = zyfwSqService.getModel(zyfwSqForm.getFwid());
		if(StringUtils.isNotNull(model.getXh())){
			BeanUtils.copyProperties(zyfwSqForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZYFW);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("zyfwSqShow");
	}
	
	/**
	 * @描述:转到志愿服务申请弹框页面
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月5日 下午2:04:19
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
	public ActionForward zyfwSqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			zyfwSqForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(zyfwSqForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zyfwSqForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学年 学期list
		zyfwSqForm.setXn(Base.currXn);
		zyfwSqForm.setXq(Base.currXq);
		String path = "xsxx_zyfwgl_sq.do?method=zyfwSqAdd";
		request.setAttribute("path", path);
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZYFW);
		request.setAttribute("jbxxList", jbxxList);

		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("zyfwSqAdd");
	}
	
	/**
	 * @描述:志愿服务申请（新增）的保存
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月5日 下午3:22:06
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
	@SystemLog(description="访问学生信息-志愿服务管理-志愿服务申请-增加XH:{xh},XN:{xn},XQ:{xq},"
			+ "FWKSSJ:{fwkssj},FWJSSJ:{fwjssj},FWDDSSX:{fwddssx},FWDD:{fwdd},JZR:{jzr},FWXSS:{fwxss},FWNR:{fwnr}")
	public ActionForward zyfwSqSaveForAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		 //判断当前时间是否有申请记录
		boolean isRepeat = zyfwSqService.isRepeat(zyfwSqForm);
		if (isRepeat) {
			String message = MessageUtil.getText(MessageKey.XSXX_ZYFWSQ_REPEAT,new String[]{zyfwSqForm.getFwkssj(),zyfwSqForm.getFwjssj()});;
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = zyfwSqService.zyfwSqSaveForAdd(zyfwSqForm);
		String messageKey = "submit".equals(zyfwSqForm.getType())?(result?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL)
																 :(result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述:转到志愿服务修改弹框页面
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月5日 下午2:04:19
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
	public ActionForward zyfwSqEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		ZyfwSqForm model = zyfwSqService.getModel(zyfwSqForm);
		if (StringUtils.isNotNull(model.getXh())) {
			BeanUtils.copyProperties(zyfwSqForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZYFW);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xsxx_zyfwgl_sq.do?method=zyfwSqEdit";
		request.setAttribute("path", path);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("zyfwSqEdit");
	}
	
	/**
	 * @描述:志愿服务申请（修改）的保存
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月5日 下午3:22:06
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
	@SystemLog(description="访问学生信息-志愿服务管理-志愿服务申请-修改FWID:{fwid},XH:{xh},XN:{xn},XQ:{xq},"
			+ "FWKSSJ:{fwkssj},FWJSSJ:{fwjssj},FWDDSSX:{fwddssx},FWDD:{fwdd},JZR:{jzr},FWXSS:{fwxss},FWNR:{fwnr}")
	public ActionForward zyfwSqSaveForEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		//判断当前时间是否有申请记录
		boolean isRepeat = zyfwSqService.isRepeat(zyfwSqForm);
		if (isRepeat) {
			String message = MessageUtil.getText(MessageKey.XSXX_ZYFWSQ_REPEAT,new String[]{zyfwSqForm.getFwkssj(),zyfwSqForm.getFwkssj()});;
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = zyfwSqService.zyfwSqSaveForEdit(zyfwSqForm);
		String messageKey = "submit".equals(zyfwSqForm.getType())?(result?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL)
				 :(result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述:志愿服务申请的删除（可批量）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 上午9:50:05
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
	@SystemLog(description="访问学生信息-志愿服务管理-志愿服务申请-删除VALUES:{values}")
	public ActionForward zyfwSqDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = zyfwSqService.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @描述:志愿服务申请的提交
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 上午10:43:33
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
	@SystemLog(description="访问学生信息-志愿服务管理-志愿服务申请-提交VALUES:{values}")
	public ActionForward zyfwSqSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		boolean result = zyfwSqService.zyfwSqSubmit(zyfwSqForm);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述:志愿服务申请的撤销
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 上午11:53:01
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
	@SystemLog(description="访问学生信息-志愿服务管理-志愿服务申请-撤销VALUES:{values},SPLCID:{splcid}")
	public ActionForward zyfwSqCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String fwid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = zyfwSqService.zyfwSqCancel(fwid, lcid);
		
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述:志愿服务申请的导出
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 下午2:22:44
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ZyfwSqForm zyfwSqForm = (ZyfwSqForm) form;
		String dcclbh = request.getParameter("dcclbh");
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zyfwSqForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = zyfwSqService.getAllList(zyfwSqForm,user);// 查询出所有记录，不分页
		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = zyfwSqForm.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(dcclbh);// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
