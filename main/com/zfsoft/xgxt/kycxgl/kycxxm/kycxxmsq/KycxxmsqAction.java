package com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmsq;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmjg.KycxxmjgService;
import com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmwh.KycxxmwhService;
import com.zfsoft.xgxt.szdw.jtff.FdyjtffService;

public class KycxxmsqAction extends SuperAction {
	
	private static final String url = "kycxgl_kycxxm_kycxxmsq.do";
	
	/**
	 * 查询科研创新项目申请
	 */
	@SystemAuth(url = url)
	public ActionForward kycxxmsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmsqForm model = (KycxxmsqForm) form;
		KycxxmsqService service = new KycxxmsqService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		request.setAttribute("searchTj", searchModel);
		String path = "kycxgl_kycxxm_kycxxmsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("kycxxmsqManage");
	}
	/**
	 * 增加科研创新项目申请
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-项目申请-增加")
	public ActionForward addKycxxmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmsqForm model = (KycxxmsqForm) form;
		KycxxmsqService service = new KycxxmsqService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType()) || SUBMIT.equalsIgnoreCase(model.getType())) {
			model.setCzsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			model.setCzr(user.getUserName());
			boolean flag = service.checkExistSave(model, user);
			if (flag) {
				String sqid = UniqID.getInstance().getUniqIDHash();
				model.setSqid(sqid);
				boolean result = service.insertKycxxmsq(model);
				String messageKey = "";
				if (SAVE.equalsIgnoreCase(model.getType())) {
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				} else {
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				String message = MessageUtil.getText(MessageKey.KYCXGL_KYCXXM_KYCXXMWH_EXISTS) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		model.setXn(Base.currXn);
		model.setXmsqrxm(user.getRealName());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		model.setXmsqsj(df.format(new Date()));
		request.setAttribute("rs", StringUtils.formatData(model));
		KycxxmwhService kycxxmwhService = new KycxxmwhService();
		request.setAttribute("lbList", kycxxmwhService.getKycxxmwhOpenList());
		return mapping.findForward("addKycxxmsq");
	}
	/**
	 * 修改科研创新项目申请
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-项目申请-修改SQID:{sqid}")
	public ActionForward updateKycxxmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmsqForm model = (KycxxmsqForm) form;
		KycxxmsqService service = new KycxxmsqService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType()) || SUBMIT.equalsIgnoreCase(model.getType())) {
			model.setCzsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			model.setCzr(user.getUserName());
			boolean flag = service.checkExistSave(model, user);
			if (flag) {
				boolean result = service.updateKycxxmsq(model);
				String messageKey = "";
				if (SAVE.equalsIgnoreCase(model.getType())) {
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				} else {
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				String message = MessageUtil.getText(MessageKey.KYCXGL_KYCXXM_KYCXXMWH_EXISTS) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		KycxxmsqForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs", StringUtils.formatData(model));
		KycxxmwhService kycxxmwhService = new KycxxmwhService();
		request.setAttribute("lbList", kycxxmwhService.getKycxxmwhOpenList());
		request.setAttribute("kycxxmcyList", new KycxxmjgService().getKycxxmcyList(model.getSqid(), user));
		return mapping.findForward("updateKycxxmsq");
	}

	/**
	 * 删除科研创新项目申请
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-项目申请-删除VALUES:{values}")
	public ActionForward delKycxxmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmsqForm model = (KycxxmsqForm) form;
		KycxxmsqService service = new KycxxmsqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			
			String [] sqidArr = values.split(",");
			boolean rs = new KycxxmjgService().delKycxxmcy(sqidArr);
			int num = 0;
			if(rs){
				num = service.runDelete(sqidArr);
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", num + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 提交科研创新项目申请
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-项目申请-提交VALUES:{values}")
	public ActionForward submitKycxxmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmsqForm model = (KycxxmsqForm) form;
		String sqid = request.getParameter("values");
		model.setSqid(sqid);
		KycxxmsqService service = new KycxxmsqService();
		boolean result = service.submitKycxxmsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 撤销
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-项目申请-撤销VALUES:{values}")
	public ActionForward cancelKycxxmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmsqForm myForm = (KycxxmsqForm) form;
		KycxxmsqService service = new KycxxmsqService();
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			KycxxmsqForm model = new KycxxmsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.cancelKycxxmsq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 查看科研创新项目申请
	 */
	@SystemAuth(url = url)
	public ActionForward viewKycxxmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmsqForm model = (KycxxmsqForm) form;
		KycxxmsqService service = new KycxxmsqService();
		model = service.getModel(model);
		request.setAttribute("rs", StringUtils.formatData(model));
		User user = getUser(request);
		request.setAttribute("kycxxmcyList", new KycxxmjgService().getKycxxmcyList(model.getSqid(), user));
		return mapping.findForward("viewKycxxmsq");
	}
	/**
	 * 导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmsqForm model = (KycxxmsqForm) form;
		KycxxmsqService service = new KycxxmsqService();
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model, user);// 查询出所有记录，不分页
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
