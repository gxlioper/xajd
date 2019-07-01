/**
 * @部门:学工产品事业部
 * @日期：2014-6-25 上午10:14:00 
 */  
package com.zfsoft.xgxt.rcsw.txhd.xmsq.js;

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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszForm;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 团学活动项目申请（教师） Action
 * @作者： cq [工号:785]
 * @时间： 2014-6-25 上午10:14:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TxhdXmsqJsAction extends SuperAction {

	private TxhdXmsqJsService service = new TxhdXmsqJsService();
	private List<HashMap<String,String>> jbxxList = null;
	private static final String RCSWRCXW = "rcswqjgl";
	private BdpzService bdpzService = new BdpzService();
	
	private static final String url = "rcsw_txhd_xmsq_js.do";
	
	
	/**
	 * 
	 * @描述:查询页面
	 * @作者：cq [工号：785]
	 * @日期：2014-6-25 上午11:20:12
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
	public ActionForward viewJssqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdXmsqJsForm model = (TxhdXmsqJsForm) form;
		
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
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		searchModel.setSearch_tj_xq(new String[]{csszModel.getXq()});
		request.setAttribute("searchTj", searchModel);
		
		request.setAttribute("path", "rcsw_txhd_xmsq_js.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewJssqList");
		
	}
	
	
	/**
	 * 
	 * @描述:填写项目申请
	 * @作者：cq [工号：785]
	 * @日期：2014-6-25 下午01:30:02
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
	public ActionForward txhdXmsq (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdXmsqJsForm model = (TxhdXmsqJsForm) form;
		User user = getUser(request);
		
		String xh = "stu".equals(user.getUserType()) ? user.getUserName() : model.getXh();
		
		if (!StringUtil.isNull(xh)){
			//学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息
		String path = "rcsw_txhd_xmsq.do?method=txhdXmsq";
		request.setAttribute("path", path);
		
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		
		request.setAttribute("xh", xh);
		request.setAttribute("currXn", Base.currXn);
		request.setAttribute("currXq", Base.getDqxqmc());
		
		return mapping.findForward("txhdXmsq");
	}
	
	
	
	/**
	 * 
	 * @描述:保存项目申请
	 * @作者：cq [工号：785]
	 * @日期：2014-6-25 下午02:06:35
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
	@SystemLog(description="访问日常事务-团学活动-活动申请（教师）-修改SQID:{sqid}")
	public ActionForward saveXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TxhdXmsqJsForm myForm = (TxhdXmsqJsForm) StringUtils.formatBean(form);
		User user = getUser(request);

		String[] xmdmArray = request.getParameterValues("xmdmArray");
		
		//提交判断是否可申请（剩余名额）
		boolean  sfksq = true;
		
		for(int i = 0, n = xmdmArray.length; i < n; i++){
			sfksq = service.getSymeForXmdm(xmdmArray[i],myForm.getType());
			if(!sfksq){
				break;
			}
		}
		
		if(sfksq){
			// 保存
			boolean result = service.saveXmsq(myForm, xmdmArray,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_XMMC_FULL));
		}

		return null;
		
	}
	
	
	/**
	 * 
	 * @描述:申请项目选择页
	 * @作者：cq [工号：785]
	 * @日期：2014-6-25 下午02:53:37
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
	public ActionForward getXmsqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TxhdXmsqJsForm model = (TxhdXmsqJsForm) form;

		// 项目申请信息
		List<HashMap<String, String>> xmsqInfoList = service.getXmsqInfoList(model.getXh());
		request.setAttribute("xmsqInfoList", xmsqInfoList);

		return mapping.findForward("getXmsqInfo");
	}

	
	
	/**
	 * 
	 * @描述:修改项目申请
	 * @作者：cq [工号：785]
	 * @日期：2014-6-27 上午10:25:24
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
	public ActionForward updateXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TxhdXmsqJsForm myForm = (TxhdXmsqJsForm) form;
		TxhdXmsqJsForm model = service.getModel(myForm);

		if (model != null) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

		}
		//获取申请项目信息
		//获取申请项目信息
		TxhdXmszService txhdXmszService=new TxhdXmszService();
		TxhdXmszForm txhdXmszForm = txhdXmszService.getModel(model.getXmdm());
		request.setAttribute("txhdXmszForm", StringUtils.formatData(txhdXmszForm));
		
		request.setAttribute("xmwhForm", StringUtils.formatData(model));
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("updateXmsq");
	}
	
	@SystemAuth(url = url)
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TxhdXmsqJsForm myForm = (TxhdXmsqJsForm) form;
		TxhdXmsqJsForm model = service.getModel(myForm);

		if (model != null) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

		}
		//获取申请项目信息
		//获取申请项目信息
		
		TxhdXmszService txhdXmszService=new TxhdXmszService();
		TxhdXmszForm txhdXmszForm = txhdXmszService.getModel(model.getXmdm());
		request.setAttribute("txhdXmszForm", StringUtils.formatData(txhdXmszForm));
		
		request.setAttribute("xmwhForm", StringUtils.formatData(model));
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewXmsq");
	}
	
	
	/**
	 * 
	 * @描述:修改保存
	 * @作者：cq [工号：785]
	 * @日期：2014-6-27 上午11:24:15
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
	@SystemLog(description="访问日常事务-团学活动-活动申请（教师）-修改SQID:{sqid}")
	public ActionForward saveSqxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TxhdXmsqJsForm myForm = (TxhdXmsqJsForm) form;
		
		//判断是否可申请（剩余名额）
		boolean  sfksq = true;
		
		sfksq = service.getSymeForXmdm(myForm.getXmdm(), myForm.getType());
		
		if(sfksq){
			// 执行修改操作
			boolean result = service.bcxgXmsq(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
		}else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_XMMC_FULL));
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述:删除项目申请
	 * @作者：cq [工号：785]
	 * @日期：2014-6-27 上午11:53:01
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
	@SystemLog(description="访问日常事务-团学活动-活动申请（教师）-删除VALUES:{values}")
	public ActionForward delXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			// 删除
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
	 * @描述:提交申请记录
	 * @作者：cq [工号：785]
	 * @日期：2014-6-27 上午11:58:13
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
	@SystemLog(description="访问日常事务-团学活动-活动申请（教师）-提交VALUES:{values}")
	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		String xh = request.getParameter("xh");
		String xmdm = request.getParameter("xmdm");
		
		//判断是否可申请（剩余名额）
		boolean  sfksq = true;
		sfksq = service.getSymeForXmdm(xmdm,"submit");
		
		if(sfksq){
			boolean result = service.submitRecord(values,lcid,xh);
			if(result){
				//更新业务状态为'审核中'
				TxhdXmsqJsForm model = new TxhdXmsqJsForm();
				model.setSqid(values);
				model.setShzt(Constants.YW_SHZ);
				service.updateModel(model);
			}
			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_XMMC_FULL));
		}
		
		return null;
	}
	/**
	 * 
	 * @描述:验证申请人数
	 * @作者：cq [工号：785]
	 * @日期：2014-6-27 上午11:58:13
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
	public ActionForward sfksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		
		//判断是否可申请（剩余名额）
		boolean  sfksq = true;
		sfksq = service.getSymeForXmdm(xmdm,"submit");
		if(sfksq){
			String messageKey = sfksq ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}
		else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_XMMC_FULL));
		}
		
		return null;
	}
	
	
	
	
	/**
	 * 
	 * @描述: 撤销申请记录
	 * @作者：cq [工号：785]
	 * @日期：2014-6-27 下午01:26:29
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
	@SystemLog(description="访问日常事务-团学活动-活动申请（教师）-撤销VALUES:{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		
		boolean result = service.cancleRecord(values,lcid);
		
		if(result){
			
			//更新业务状态为'未提交'
			TxhdXmsqJsForm model = new TxhdXmsqJsForm();
			model.setSqid(values);
			//查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(values))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateModel(model);
			
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:导出
	 * @作者：cq [工号：785]
	 * @日期：2014-6-27 下午01:36:07
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
		
		TxhdXmsqJsForm myForm = (TxhdXmsqJsForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(myForm,
				user);// 查询出所有记录，不分页

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
