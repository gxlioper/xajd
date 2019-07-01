/**
 * @部门:学工产品事业部
 * @日期：2016-02-03下午02:21:14 
 */
package com.zfsoft.xgxt.ystgl.rtgl.rtsq;

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

import xgxt.DAO.DAO;
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
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 艺术团入团管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1206]
 * @时间： 2016-02-03下午02:21:14
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class YstRtsqAction extends SuperAction {
	DAO dao = DAO.getInstance();
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private final String YstRtsq ="rtsq";
	YstRtsqService service = new YstRtsqService();
	private RtjgService rtjgservice = new RtjgService();
	private static final String url = "ystgl_rtgl_rtsq.do";

	/**
	 * 
	 * @描述:艺术团入团申请列表
	 * @作者：xiaxia[工号：1206]
	 * @日期：2016-02-03下午02:36:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward getYstRtsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtsqForm model = (YstRtsqForm) form;
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		List<HashMap<String, String>> xnList = dao.getXnndList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("usertype", user.getUserType());
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xh", user.getUserName());
		return mapping.findForward("getYstRtsqList");
		
	}

	

	/**
	 * 
	 * @描述:艺术团入团申请保存
	 * @作者：xiaxia[工号：1206]
	 * @日期：2016-02-03上午11:34:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemLog(description = "访问艺术团管理-入团申请-保存-rtid:{rtid}")
	public ActionForward saveYstRtsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtsqForm model = (YstRtsqForm) form;
		boolean result = false;
		String message=null;
	    User user = getUser(request);
		String splc = service.getsplc(model);
		model.setSplc(splc);
 		if(model.getType().equals("save")||model.getType().equals("submit")){
 			if(service.checkExistForSave(model) == true){
 				response.getWriter().print(getJsonMessageByKey(MessageKey.CFTX));
				return null;
 			}
 		    model.setRtxn(Base.currXn);
 			model.setRtxq(Base.currXq);
			result = service.saveYstRtsq(model, user);
		}else {
			result = service.saveSqjgUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}


	/**
	 * 
	 * @描述:艺术团入团申请撤销
	 * @作者：xiaxia[工号：1206]
	 * @日期：2016-02-03下午01:46:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemLog(description = "访问艺术团管理-入团申请-撤销-rtid:{rtid}")
	public ActionForward cancelYstRtsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rtid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(rtid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			YstRtsqForm model = new YstRtsqForm();
			model.setRtid(rtid);
			model.setSplc(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(rtid)) > 0) {
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
	 * 艺术团入团申请信息查看
	 */
	public ActionForward viewYstRtsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtsqForm myForm = (YstRtsqForm) form;
		YstRtsqForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(YstRtsq);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String, String> ystxx = service.getYstxxMap(model);
		request.setAttribute("ystxx", StringUtils.formatData(ystxx));
		User user = getUser(request);
		request.setAttribute("usertype", user.getUserType());
		return mapping.findForward("viewYstRtsq");
	}
	
	//申请信息删除
	@SystemLog(description = "访问艺术团管理-入团申请-删除-rtid:{rtid}")
	public ActionForward delYstRtsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	 * 艺术团入团结果信息导出
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YstRtsqForm model = (YstRtsqForm) form;

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
	
	//提交
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtsqForm myForm = (YstRtsqForm) form;
		String value = request.getParameter("values");
		myForm.setRtid(value);
		YstRtsqForm model = service.getModel(myForm);
		User user = getUser(request);
		String splc = service.getsplc(model);
		model.setSplc(splc);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	 /**
     * 
     * @描述: 艺术团入团申请
     */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtsqForm model = (YstRtsqForm) form;
	    User user = getUser(request);
	    if(user.getUserType().equalsIgnoreCase("stu")){
	    	model.setXh(user.getUserName());
	    }
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(YstRtsq);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("currxn", Base.currXn);
		String path = "ystglRtsq.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("usertype",user.getUserType());
		//其他信息配置
		return mapping.findForward("addYstRtsq");
	}
	//艺术团入团申请修改
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问艺术团管理-入团申请-修改-rtid:{rtid}")
	public ActionForward editYstRtsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtsqForm myForm = (YstRtsqForm) form;
		YstRtsqForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(YstRtsq);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rylblist", rtjgservice.getRylbList());
		HashMap<String, String> ystxx = service.getYstxxMap(model);
		request.setAttribute("ystxx", StringUtils.formatData(ystxx));
		String path = "ystglRtsq.do?method=editYstRtsq";
		request.setAttribute("path", path);
		return mapping.findForward("editYstRtsq");
	}
	
	//艺术团入团申请选择社团项目，项目选择页面
	public ActionForward getYstxmList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtsqForm myForm = (YstRtsqForm) form;
		String xh= request.getParameter("xh");
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getStxmList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("xh", xh);
		String path = "ystgl_ystgl_ystwh.do";
		request.setAttribute("path", path);
		return mapping.findForward("getYstxmList");
	}
	
	//学生页面查询
	@SystemAuth(url = "ystglRtsq.do?method=getStuYstRtsqList")
	public ActionForward getStuYstRtsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstRtsqForm model = (YstRtsqForm) form;
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			List<HashMap<String, String>> resultList = service.getStuYstRtsqList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		List<HashMap<String, String>> xnList = dao.getXnndList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("usertype", user.getUserType());
		request.setAttribute("path", "ystglRtsq.do?method=getStuYstRtsqList");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xh", user.getUserName());
		return mapping.findForward("stuYstRtsqList");
		
	}
}
