package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
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
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbcssz.CsszService;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjcsz.XsgzzbJcszForm;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjcsz.XsgzzbJcszService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.*;

public class XsgzzbsqAction extends SuperAction {
	private List<HashMap<String, String>> xyList;
	private MessageResources messageResources = MessageResources.getMessageResources("config.ApplicationResources");
	
	{
		XsgzzbsqService xsgzzbsqService = new XsgzzbsqService();
		xyList = xsgzzbsqService.getXyList();
	}
	
	private static final String url = "rcsw_xsgzzb_xsgzzbsq.do";

	/**
	 * @描述:查询周报申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgzzbsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbsqService service = new XsgzzbsqService();
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		if("stu".equalsIgnoreCase(userStatus)){
			String msg = "该模块不允许学生访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList;
			if("bj".equals(gzzblx)){
				resultList = service.getPageListBj(model, user);
			}else{
				resultList = service.getPageList(model, user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		XsgzzbJcszService xsgzzbJcszService = new XsgzzbJcszService();
		XsgzzbJcszForm jcszModel = xsgzzbJcszService.getModel(gzzblx);
		request.setAttribute("jcszModel", jcszModel);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		String path = "rcsw_xsgzzb_xsgzzbsq.do";
		if("bj".equals(gzzblx)){
			path = "rcsw_xsgzzb_xsgzzbsqgl.do?method=xsgzzbsqManage&gzzblx=bj";
		}
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		List<HashMap<String, String>> zcList = service.getZcList();
		request.setAttribute("zcListSize", zcList.size());
		if("bj".equals(gzzblx)){
			return mapping.findForward("bjgzzbsqManage");
		}
		return mapping.findForward("xsgzzbsqManage");
	}
	
	/**
	 * @描述:查询班级
	 */
	public ActionForward bjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		XsgzzbsqService service = new XsgzzbsqService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getBjList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("flag", StringUtils.isNotNull(request.getParameter("flag"))? request.getParameter("flag"):"");
		String path = "rcsw_xsgzzb_xsgzzbsqgl.do?method=bjManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjManage");
	}

	/**
	 * @描述:增加周报申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问日常事务-学生工作周报表-周报填写-增加BMDM:{bmdm},ZC:{zc},YZZYGZ:{yzzygz},XZZYGZ:{xzzygz}")
	public ActionForward addXsgzzbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbsqService service = new XsgzzbsqService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())
				|| SUBMIT.equalsIgnoreCase(model.getType())) {
			// 唯一性判断（学年，学期，学院，周次，录入人）
			boolean isExist = false;
			model.setLrsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			model.setLrr(user.getUserName());
			isExist = service.isExistByXszbbsq(model, user);
			if (!isExist) {
				// 添加
				String sqid = UniqID.getInstance().getUniqIDHash();
				model.setSqid(sqid);
				if(Base.xxdm.equalsIgnoreCase("13815")){
					model.setWjlxdms(request.getParameterValues("wjlxdm"));
				}
				boolean result = service.saveXsgzzbsq(model);
				String messageKey = "";
				if (SAVE.equalsIgnoreCase(model.getType())) {
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				} else {
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				String mc = messageResources.getMessage("lable.xy");
				if("bj".equals(gzzblx)){
					mc = "班级";
				}
				String message = MessageUtil.getText(MessageKey.RCSW_XSGZZB_XSGZZBSQCZ, mc) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}

		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setXqmc(service.getCurrentXqmc(model));
		List<HashMap<String, String>> zcList = service.getZcList();
		// ========= 初始化今天所在周 begin =========
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		DateFormat d = DateFormat.getDateInstance();
		for (HashMap<String, String> zcMap : zcList) {
			Date ksrq = d.parse(zcMap.get("ksrq"));
			Date jsrq = d.parse(zcMap.get("jsrq"));
			boolean flag = now.after(ksrq) && now.before(jsrq);
			if(flag){
				model.setZc(zcMap.get("dm"));
			}
		}
		// ========= 初始化今天所在周 end =========
		if(!"bj".equals(gzzblx)){
			// ========= 获取学院名称 begin =========
			model.setBmdm(user.getUserDep());
			for (HashMap<String, String> xyMap : xyList) {
				if(xyMap.get("xydm").equals(user.getUserDep())){
					model.setBmdmmc(xyMap.get("xymc"));
				}
			}
			// ========= 获取学院名称 end =========
			request.setAttribute("xyList", xyList);
		}
		request.setAttribute("zcList", zcList);
		request.setAttribute("xsgzzbsqMap", model);
		request.setAttribute("userStatus", user.getUserStatus());
		if("bj".equals(gzzblx)){
			return mapping.findForward("addBjgzzbsq");
		}
		//四川信息职业技术学院文件list,用于下拉框
		if(Base.xxdm.equals("13815")){
			CsszService csszservice =  new CsszService();
			request.setAttribute("wjlxlist", csszservice.getWjlxList());
		}
		return mapping.findForward("addXsgzzbsq");
	}

	/**
	 * @描述:修改周报申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问日常事务-学生工作周报表-周报填写-修改SQID:{sqid},BMDM:{bmdm},ZC:{zc},YZZYGZ:{yzzygz},XZZYGZ:{xzzygz}")
	public ActionForward updateXsgzzbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbsqService service = new XsgzzbsqService();
		User user = getUser(request);
		if (UPDATE.equalsIgnoreCase(model.getType())
				|| SUBMIT.equalsIgnoreCase(model.getType())) {
			// 唯一性判断（学年，学期，学院，周次，录入人）
			boolean isExist = false;
			model.setLrsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			model.setLrr(user.getUserName());
			isExist = service.isExistByXszbbsq(model, user);
			if (!isExist) {
				if(Base.xxdm.equalsIgnoreCase("13815")){
					model.setWjlxdms(request.getParameterValues("wjlxdm"));
				}
				boolean result = service.updateXsgzzbsq(model);
				String messageKey = "";
				if (UPDATE.equalsIgnoreCase(model.getType())) {
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				} else {
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				String mc = messageResources.getMessage("lable.xy");
				if("bj".equals(gzzblx)){
					mc = "班级";
				}
				String message = MessageUtil.getText(MessageKey.RCSW_XSGZZB_XSGZZBSQCZ, mc) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		XsgzzbsqForm updateForm = null;
		if("bj".equals(gzzblx)){
			updateForm = service.getModelBj(model);
		}else{
			updateForm = service.getModel(model);
		}
		XsgzzbJcszService xsgzzbJcszService = new XsgzzbJcszService();
		XsgzzbJcszForm jcszModel = xsgzzbJcszService.getModel(gzzblx);
		request.setAttribute("jcszModel", StringUtils.formatData(jcszModel));
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("xsgzzbsqMap", StringUtils.formatData(model));
		if("bj".equals(gzzblx)){
			return mapping.findForward("updateBjgzzbsq");
		}
		//四川信息职业技术学院文件list,用于下拉框
		if(Base.xxdm.equals("13815")){
			CsszService csszservice =  new CsszService();
			List<HashMap<String, String>>  yscfjlist = csszservice.getYscfjList(model.getSqid());
			request.setAttribute("wjlxlist", csszservice.getWjlxList());
			request.setAttribute("yscfjlist", yscfjlist);
			if(yscfjlist == null || yscfjlist.size() == 0){
				request.setAttribute("scbz", "0");
			}else{
				request.setAttribute("scbz", "1");
			}
			
		}
		return mapping.findForward("updateXsgzzbsq");
	}

	/**
	 * @描述:删除周报申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问日常事务-学生工作周报表-周报填写-删除VALUES:{values}")
	public ActionForward delXsgzzbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		XsgzzbsqService service = new XsgzzbsqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// 删除
			service.setTableInfo(model);
			List<HashMap<String, String>> fjlj = null;
			ArrayList<String> delFileArrId = new ArrayList<String>();
			//四川信息职业技术学院个性化功能删除时判断删除附件
			if(Base.xxdm.equalsIgnoreCase("13815")){
				String[]ids = values.split(",");
				String filegid = null;
				int idslen = ids.length;
				for(int i = 0;i <= idslen-1;i++){
					filegid = service.getFileGID(ids[i]);
					if(StringUtils.isNotNull(filegid)){
						delFileArrId.add(filegid);
					}
				}
				if(delFileArrId != null && delFileArrId.size() != 0){
					 fjlj = service.getfjlj(delFileArrId.toArray(new String[]{}));
				}
			}
			int num = service.runDelete(values.split(","));
			//四川信息职业技术学院个性化功能删除时判断删除附件
			if(Base.xxdm.equalsIgnoreCase("13815")){
				boolean fjselresult = service.Delfile_13815(delFileArrId.toArray(new String[]{}));
				if(num > 0 && fjlj != null){
					if(fjselresult){
						fjselresult = service.Delfile_13815_realfile(fjlj);
					}
					if(!fjselresult){
						throw new SystemException(MessageKey.SYS_DEL_FAIL);
					}
				}
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
	 * @描述:提交周报申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submitXsgzzbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		String sqid = request.getParameter("values");
		model.setSqid(sqid);
		XsgzzbsqService service = new XsgzzbsqService();
		boolean result = service.submitXsgzzbsq(model);
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
	public ActionForward cancelXsgzzbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm myForm = (XsgzzbsqForm) form;
		XsgzzbsqService service = new XsgzzbsqService();
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			XsgzzbsqForm model = new XsgzzbsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			model.setGzzblx(myForm.getGzzblx());
			service.cancelXsgzzbsq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @描述:查看周报申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewXsgzzbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbsqService service = new XsgzzbsqService();
		if("bj".equals(gzzblx)){
			model = service.getModelBj(model);
		}else{
			model = service.getModel(model);
		}
		request.setAttribute("model", StringUtils.formatData(model));
		if("bj".equals(gzzblx)){
			return mapping.findForward("viewBjgzzbsq");
		}
		//四川信息职业技术学院文件list,用于下拉框
		if(Base.xxdm.equals("13815")){
			CsszService csszservice =  new CsszService();
			request.setAttribute("yscfjlist", csszservice.getYscfjList(model.getSqid()));
			
		}
		return mapping.findForward("viewXsgzzbsq");
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbsqForm model = (XsgzzbsqForm) form;
		XsgzzbsqService service = new XsgzzbsqService();
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
	
	//删除单个文件
	public ActionForward deleteFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileid = request.getParameter("id");
		XsgzzbsqService service = new XsgzzbsqService();
		HashMap<String, String> fjlj = service.onefjlj(fileid);
	    boolean result = service.delonefjlj(fileid);;
		if (StringUtils.isNotNull(fjlj.get("fjlj")) && result == true){
			File file = new File(fjlj.get("fjlj"));
			if (file.exists()){
				result = file.delete();
			}
			if(result){
				response.getWriter().print("true");
			}else{
				response.getWriter().print("false");
			}
		}
		return null;
	}
	
	//下载单个文件
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileid = request.getParameter("id");
		XsgzzbsqService service = new XsgzzbsqService();
		HashMap<String, String> fjlj = service.onefjlj(fileid);
		if (StringUtils.isNotNull(fjlj.get("fjlj"))){
			File file = new File(fjlj.get("fjlj"));
			if (file.exists()){
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(fjlj.get("fjmc"),"utf-8")); 
					FileUtil.outputFile(response, file);
				}
			}
		}
		return null;
	}
}
