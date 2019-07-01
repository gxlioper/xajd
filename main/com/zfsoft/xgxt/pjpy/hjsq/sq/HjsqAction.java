package com.zfsoft.xgxt.pjpy.hjsq.sq;

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

import com.zfsoft.xgxt.base.action.SuperAction;
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
import com.zfsoft.xgxt.pjpy.hjsq.cssz.CsszForm;
import com.zfsoft.xgxt.pjpy.hjsq.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;
public class HjsqAction extends SuperAction<HjsqForm, HjsqService> {
	private static final String url = "pjpy_jxsq.do";
	private HjsqService service = new HjsqService();
	private static final String JXSQ = "jxsq";
	/**
	 * 
	 * @描述: 奖项申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-8 上午10:33:44
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
	public ActionForward getJxsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		CsszForm kgModel = new CsszService().getModel();
		boolean sqkg = (kgModel == null || StringUtils.isNull(kgModel.getSplc())) ? false :true;
		request.setAttribute("sqkg", sqkg);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述: 查询奖项申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-8 下午03:25:15
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
	public ActionForward searchForHjsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjsqForm hjForm = (HjsqForm)form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		hjForm.setSearchModel(searchModel);  
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(hjForm, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-8 下午05:42:25
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
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjsqForm zpForm = (HjsqForm)form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			zpForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(zpForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zpForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JXSQ);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jxsq.do?method=add";
		request.setAttribute("xn",Base.currXn);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(Base.currXq));
		request.setAttribute("path", path);
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @描述: 修改
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-8 下午05:42:25
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
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjsqForm zpForm = (HjsqForm)form;
		HjsqForm zpModel = service.getModel(zpForm.getId());
		if(zpModel != null){
			BeanUtils.copyProperties(zpForm,StringUtils.formatData(zpModel) );
		}
		if (!StringUtil.isNull(zpForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zpForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JXSQ);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jxsq.do?method=update";
		request.setAttribute("path", path);
		request.setAttribute("xn",zpModel.getXn());
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zpModel.getXq()));
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-8 下午05:42:25
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
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjsqForm zpForm = (HjsqForm)form;
		boolean rs = true;
		try {
			rs = service.save(zpForm);
		}catch (SystemException e) {
			// TODO 自动生成 catch 块
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-9 上午09:54:55
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
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HjsqForm zpForm = (HjsqForm)form;
		HjsqForm zpModel = service.getModel(zpForm);
		if(zpModel != null){
			BeanUtils.copyProperties(zpForm, zpModel);
		}
		if (!StringUtil.isNull(zpForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zpForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JXSQ);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jxsq.do?method=view";
		request.setAttribute("xn", zpModel.getXn());
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zpModel.getXq()));
		request.setAttribute("path", path);
		return mapping.findForward("view");
	}
	
	/**
	 * 
	 * @描述：删除
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-9 上午10:01:31
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
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	 * @描述: 撤销申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-9 上午10:03:59
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
	public ActionForward cancelSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ywid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		boolean rs = service.cancelSq(ywid, lcid);
		if (rs) {
			// 更新业务状态为'未提交'
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			HjsqForm myForm = new HjsqForm();
			myForm.setId(ywid);
			if (Integer.valueOf(shlcdao.getExistTh(ywid)) > 0) {
				myForm.setShzt(Constants.YW_YTH);
			} else {
				myForm.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(myForm);
		}
		String messageKey = rs ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 提交
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-9 下午02:24:47
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
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String value = request.getParameter("values");
		HjsqForm model = service.getModel(value);
		User user = getUser(request);
		boolean result = service.submitBusiness(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-9 下午02:31:19
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
		
		HjsqForm model = (HjsqForm) form;

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
	
}
