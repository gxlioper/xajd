package com.zfsoft.xgxt.rcsw.txhd.xmxxsq;

import java.io.File;
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
import xgxt.xtwh.comm.splc.XtwhShlcService;

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
import com.zfsoft.xgxt.rcsw.txhd.dmwh.TxhdDmwhForm;
import com.zfsoft.xgxt.rcsw.txhd.dmwh.TxhdDmwhService;
import com.zfsoft.xgxt.rcsw.txhd.xmxxjcsz.XmxxJcszForm;
import com.zfsoft.xgxt.rcsw.txhd.xmxxjcsz.XmxxJcszService;

public class XmxxsqAction extends SuperAction {
	
	private static final String url = "rcsw_txhd_xmxxsq.do";
	
	/**
	 * @描述:查询项目申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward xmxxsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxxsqForm model = (XmxxsqForm) form;
		XmxxsqService service = new XmxxsqService();
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
		XmxxJcszService xmxxJcszService = new XmxxJcszService();
		XmxxJcszForm jcszModel = xmxxJcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		String path = "rcsw_txhd_xmxxsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmxxsqManage");
	}

	/**
	 * @描述:增加项目申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-团学活动-项目申请-增加")
	public ActionForward addXmxxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxxsqForm model = (XmxxsqForm) form;
		XmxxsqService service = new XmxxsqService();
		TxhdDmwhService thxdDmwhservice = new TxhdDmwhService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())
				|| SUBMIT.equalsIgnoreCase(model.getType())) {
			boolean isExist = false;
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			model.setSqr(user.getUserName());
			isExist = service.isExist(model);
			if (!isExist) {
				// 添加
				String sqid = UniqID.getInstance().getUniqIDHash();
				model.setSqid(sqid);
				boolean result = service.saveXmxxsq(model);
				String messageKey = "";
				if (SAVE.equalsIgnoreCase(model.getType())) {
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				} else {
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				String message = MessageUtil.getText(MessageKey.RCSW_TXHD_XMMC_EXIST) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		TxhdDmwhService txhdDmwhService = new TxhdDmwhService();
		List<HashMap<String, String>> lbList = txhdDmwhService.getLblist();// 项目类别
		request.setAttribute("lbList", lbList);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("hdgglist", thxdDmwhservice.getHdggList());
		String path = "rcsw_txhd_xmxxsqgl.do?method=addXmxxsq";
		request.setAttribute("path", path);
		return mapping.findForward("addXmxxsq");
	}

	/**
	 * @描述:修改项目申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-团学活动-项目申请-修改SQID:{sqid}")
	public ActionForward updateXmxxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxxsqForm model = (XmxxsqForm) form;
		XmxxsqService service = new XmxxsqService();
		TxhdDmwhService thxdDmwhservice = new TxhdDmwhService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())
				|| SUBMIT.equalsIgnoreCase(model.getType())) {
			boolean isExist = false;
//			model.setXn(Base.currXn);
//			model.setXq(Base.currXq);
//			model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
//			model.setSqr(user.getUserName());
			isExist = service.isExist(model);
			if (!isExist) {
				boolean result = service.updateXmxxsq(model);
				String messageKey = "";
				if (SAVE.equalsIgnoreCase(model.getType())) {
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				} else {
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				String message = MessageUtil.getText(MessageKey.RCSW_TXHD_XMMC_EXIST) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		TxhdDmwhService txhdDmwhService = new TxhdDmwhService();
		List<HashMap<String, String>> lbList = txhdDmwhService.getLblist();// 项目类别
		request.setAttribute("lbList", lbList);
		request.setAttribute("xxdm", Base.xxdm);
		XmxxsqForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("hdgglist", thxdDmwhservice.getHdggList());
		String path = "rcsw_txhd_xmxxsqgl.do?method=updateXmxxsq";
		request.setAttribute("path", path);
		return mapping.findForward("updateXmxxsq");
	}
	
	/**
	 * @描述:删除项目申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-团学活动-项目申请-删除VALUES:{values}")
	public ActionForward delXmxxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxxsqService service = new XmxxsqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// 删除
			int num = service.runDelete(values.split(","));
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
	 * @描述:提交项目申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-团学活动-项目申请-提交VALUES:{values}")
	public ActionForward submitXmxxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxxsqForm model = (XmxxsqForm) form;
		String sqid = request.getParameter("values");
		model.setSqid(sqid);
		XmxxsqService service = new XmxxsqService();
		boolean result = service.submitXmxxsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @描述:撤销
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-团学活动-项目申请-撤销VALUES:{values}")
	public ActionForward cancelXmxxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxxsqService service = new XmxxsqService();
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			XmxxsqForm model = new XmxxsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.cancelXmxxsq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @描述:查看项目申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewXmxxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxxsqForm myForm = (XmxxsqForm) form;
		XmxxsqService service = new XmxxsqService();
		XmxxsqForm myModel = (XmxxsqForm)StringUtils.formatData(service.getModel(myForm));
		if(myModel.getHdggdm() != null){
			TxhdDmwhService dmwhservice = new TxhdDmwhService();
			String hdgg = dmwhservice.getHdggmc(myModel.getHdggdm());
			request.setAttribute("hdgg", hdgg);
		}else{
			request.setAttribute("hdgg", "");
		}
		String sqrssx = myModel.getSqrssx();
		String shrssx = myModel.getShrssx();
		if(null!=sqrssx && !"".equals(sqrssx)){
			myModel.setSqrssx(sqrssx+" 人");
		}
		if(null!=shrssx && !"".equals(shrssx)){
			myModel.setShrssx(shrssx+" 人");
		}
		request.setAttribute("data", myModel);
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("rcsw");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		TxhdDmwhService txhdDmwhService = new TxhdDmwhService();
		List<HashMap<String, String>> lbList = txhdDmwhService.getLblist();// 项目类别
		String hdlbmc=null;
		for(HashMap<String, String> hm:lbList){
			if(myModel.getLbdm().equals(hm.get("lbdm"))){
				hdlbmc=hm.get("lbmc");
			}
		}
		request.setAttribute("hdlbmc", hdlbmc);
		BeanUtils.copyProperties(myForm, myModel);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("spzt", "true");
		request.setAttribute("xxdm", Base.xxdm);
		
		return mapping.findForward("viewXmxxsq");
	}

	/**
	 * @描述:导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxxsqForm model = (XmxxsqForm) form;
		XmxxsqService service = new XmxxsqService();
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
