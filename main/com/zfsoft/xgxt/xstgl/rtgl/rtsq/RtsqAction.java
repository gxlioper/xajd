/**
 * @部门:学工产品事业部
 * @日期：2015-8-6 下午02:21:14 
 */
package com.zfsoft.xgxt.xstgl.rtgl.rtsq;

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
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

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
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgForm;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsztz.tzxmsq.XsXmSqForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 入团管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 喻鑫源 [工号:1206]
 * @时间： 2015-8-6 下午02:21:14
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class RtsqAction extends SuperAction {
	DAO dao = DAO.getInstance();
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private final String RTSQ ="rtsq";
	RtsqService service = new RtsqService();
	private RtjgService rtjgservice = new RtjgService();
	
	private static final String url = "stgl_rtgl_rtsq.do";

	/**
	 * 
	 * @描述:入团申请列表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-8-6 下午02:36:04
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
	public ActionForward getRtsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtsqForm model = (RtsqForm) form;
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
		request.setAttribute("path", "stgl_rtgl_rtsq.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xh", user.getUserName());
		return mapping.findForward("RtsqList");
		
	}

	

	/**
	 * 
	 * @描述:入团申请保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-8-6 上午11:34:04
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
	public ActionForward saveRtsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtsqForm model = (RtsqForm) form;
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
			result = service.saveRtsq(model, user);
		}else if(model.getType().equals("update")||model.getType().equals("updatesubmit")){
			result = service.saveSqjgUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}


	/**
	 * 
	 * @描述:入团申请撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-8-6 下午01:46:22
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
	public ActionForward cancelRtsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rtid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(rtid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			RtsqForm model = new RtsqForm();
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
	 * 入团申请信息查看
	 */
	public ActionForward Rtsqck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtsqForm myForm = (RtsqForm) form;
		RtsqForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RTSQ);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String, String> stxx = service.getStxxMap(model);
		request.setAttribute("stxx", StringUtils.formatData(stxx));
		User user = getUser(request);
		request.setAttribute("usertype", user.getUserType());
	    HashMap<String, String> stcyxx = service.getStxxCyMap(model, user);
		request.setAttribute("stcyxx", stcyxx);
		// 学生基本信息显示配置gzkhKhjgXx
		return mapping.findForward("viewRtsq");
	}
	
	//申请信息删除
	public ActionForward delSqxx(ActionMapping mapping, ActionForm form,
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
	 * 入团结果信息导出
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RtsqForm model = (RtsqForm) form;

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
		RtsqForm myForm = (RtsqForm) form;
		String value = request.getParameter("values");
		myForm.setRtid(value);
		RtsqForm model = service.getModel(myForm);
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
     * @描述: 入团申请
     */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtsqForm model = (RtsqForm) form;
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RTSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("currxn", Base.currXn);
		request.setAttribute("rylblist", rtjgservice.getRylbList());
		String path = "stglRtsq.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("usertype",user.getUserType());
		//其他信息配置
		return mapping.findForward("addRtsq");
	}
	
	//入团申请修改
	public ActionForward editRtsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtsqForm myForm = (RtsqForm) form;
		RtsqForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RTSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rylblist", rtjgservice.getRylbList());
		HashMap<String, String> stxx = service.getStxxMap(model);
		request.setAttribute("stxx", StringUtils.formatData(stxx));
		String path = "stglRtsq.do?method=editRtsq";
		request.setAttribute("path", path);
		return mapping.findForward("editRtsq");
	}
	
	//入团申请选择社团项目，项目选择页面
	public ActionForward getStxmList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		RtsqForm myForm = (RtsqForm) form;
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
		String path = "stglRtsq.do?method=getStxmList";
		request.setAttribute("path", path);
		return mapping.findForward("getStxm");
	}
	
	//学生页面查询
	@SystemAuth(url = "stglRtsq.do?method=getStuRtsqList")
	public ActionForward getStuRtsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtsqForm model = (RtsqForm) form;
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			List<HashMap<String, String>> resultList = service.getStuRtsqList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		List<HashMap<String, String>> xnList = dao.getXnndList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("usertype", user.getUserType());
		request.setAttribute("path", "stglRtsq.do?method=getStuRtsqList");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xh", user.getUserName());
		return mapping.findForward("stuRtsqList");
		
	}


	public ActionForward getZdlsInfo(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		RtsqForm model = (RtsqForm) form;
		List<HashMap<String, String>> result = service.getZdlsInfo(model);
		JSONArray resultList = JSONArray.fromObject(result); // 转为json格式
		response.setContentType("text/html;charset=gbk"); // ajax请求返回数据转码，否则会中文乱码
		response.getWriter().print(resultList);
		return null;
	}
}
