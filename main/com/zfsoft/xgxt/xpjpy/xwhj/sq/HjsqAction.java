/**
 * @部门:学工产品事业部
 * @日期：2016-7-21 下午03:48:43 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.sq;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xpjpy.xwhj.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxdj.DmwhJxdjService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-获奖信息管理
 * @类功能描述: 获奖申请 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-7-21 下午03:48:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjsqAction extends SuperAction<HjsqForm, HjsqService> {
	
	private HjsqService service = new HjsqService();
	private CsszService csszService = new CsszService();
	private static final String HJSQ = "hjsq";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(HJSQ);
	}
	private static final String url = "pjpy_hjgl_sq.do";
	
	
	/**
	 * 
	 * @描述: 查询
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-21 下午04:59:44
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
	public ActionForward hjsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjsqForm model = (HjsqForm) form;
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
		
		String[] sqshkg = csszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg[0]);
		String path = "pjpy_hjgl_sq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("hjsqList");
	}
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 上午10:40:42
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
	public ActionForward addHjSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjsqForm model = (HjsqForm) form;
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
		String path = "xpj_hjsq.do?method=addHjSq";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		DmwhJxdjService djService = new DmwhJxdjService();
		request.setAttribute("jxlbList", djService.getJxlbList());
		
		return mapping.findForward("addHjSq");
	}
	
	/**
	 * 
	 * @描述: 修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-26 下午03:09:11
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
	public ActionForward editHjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjsqForm myForm = (HjsqForm) form;
		HjsqForm model = service.getModel(myForm);
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
		DmwhJxdjService djService = new DmwhJxdjService();
		request.setAttribute("jxlbList", djService.getJxlbList());
		request.setAttribute("jxdjdm", model.getJxdjdm());
		request.setAttribute("jxmcdm", model.getJxmcdm());
		request.setAttribute("je", model.getJe());
		String path = "xpj_hjsq.do?method=editHjsq";
		request.setAttribute("path", path);
		
		return mapping.findForward("editHjsq");
	}
	
	/**
	 * 
	 * @描述: 动态取值(奖项名次)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 下午02:03:21
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
	public ActionForward getJxmcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HjsqForm model = (HjsqForm) form;
		JSONArray json = JSONArray.fromObject(service.getJxmcList(model.getJxlbdm(), model.getJsfs(), model.getJxdjdm())); 
		response.getWriter().print(json);
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 动态取值(金额)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 下午04:22:57
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
	public ActionForward getJe(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HjsqForm model = (HjsqForm) form;
		JSONArray json = JSONArray.fromObject(service.getJe(model.getJxlbdm(), model.getJsfs(), model.getJxdjdm(), model.getJxmcdm())); 
		response.getWriter().print(json);
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 下午04:45:29
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
	public ActionForward saveHjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjsqForm model = (HjsqForm) form;
		boolean result = false;
	    User user = getUser(request);
	    String messageKey = "";
 		if(model.getType().equals("save") || model.getType().equals("submit")){
 			boolean isExist = service.isExistByHjsq(model,SAVE);
 			if(!isExist){
 				result = service.saveSqjg(model, user);
 			}else {
 				messageKey = "该学生当前奖项的申请已存在！";
 				response.getWriter().print(getJsonMessage(messageKey));
 				return null;
 			}
		}else if(model.getType().equals("update") || model.getType().equals("updatesubmit")){
			boolean isExist = service.isExistByHjsq(model,UPDATE);
			if(!isExist){
				result = service.saveSqjgUpdate(model, user);
			}else {
				messageKey = "该学生当前奖项的申请已存在！";
 				response.getWriter().print(getJsonMessage(messageKey));
 				return null;
			}
		}
		messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 下午05:40:05
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
	public ActionForward cancelHjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			HjsqForm model = new HjsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 提交
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 下午05:47:01
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
	public ActionForward submitHjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjsqForm model = (HjsqForm) form;
		String values = request.getParameter("values");
		model.setSqid(values);
		boolean result = service.submitHjsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-26 上午10:52:14
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
	public ActionForward delHjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
	 * @描述: 查看
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-26 下午03:54:05
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
	public ActionForward viewHjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HjsqForm model = (HjsqForm) form;
		request.setAttribute("jbxxList", jbxxList);
		HjsqForm viewForm = service.getModel(model.getSqid());
		BeanUtils.copyProperties(model, viewForm);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("rs", model);
		
		return mapping.findForward("viewHjsq");		
	}
	
	/**
	 * 
	 * @描述: 导出
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-26 下午03:59:26
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
		
		HjsqForm model = (HjsqForm) form;
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
