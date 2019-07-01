/**
 * @部门:学工产品事业部
 * @日期：2015-7-1 下午04:07:35 
 */  
package com.zfsoft.xgxt.zxdk.syddk;

import java.io.File;
import java.util.HashMap;
import java.util.List;

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
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCssz;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;
import common.Globals;
/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： ChenQ[工号:856]
 * @时间： 2015-7-1 下午04:07:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SydkSqshAction extends SuperAuditAction<SydkSqshModel,SydkSqshService> {
    
	private static final String GJZXDK = "gjzxdk";
	
	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param gnid
	 * @param squrl
	 * @param shurl
	 */
	protected SydkSqshAction(String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}
	
	public SydkSqshAction(){
		this("zxdk_sydk", "zxdk_sydk_dksq.do", "zxdk_sydk_dksh.do");
	}
   
	/**
	 * 
	 * @描述:贷款申请
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-7-2 上午09:12:32
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
	@SystemAuth(url = "zxdk_sydk_dksq.do")
	public ActionForward dksqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz cssz  =  csszService.getModel();
		request.setAttribute("cssz", cssz);
		
		request.setAttribute("path", "zxdk_sydk_dksq.do");
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{cssz.getSydkxn()});
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("dksqList");
	}
	/**
	 * 
	 * @描述:贷款申请DataGrid获取数据
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-7-2 上午09:14:53
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
	@SystemAuth(url = "zxdk_sydk_dksq.do")
	public ActionForward getDksqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SydkSqshService service = getService();
		SydkSqshModel model = (SydkSqshModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		
		response.getWriter().print(dataList);
		
		return null;
		
	}
	/**
	 * 
	 * @描述:申请
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-7-2 下午02:15:57
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
	@SystemAuth(url = "zxdk_sydk_dksq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward dksq(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		SydkSqshModel model = (SydkSqshModel) form;
		SydkSqshService service = getService();
		ZxdkCsszService csszService = new ZxdkCsszService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//西安科技大学个性化
			if("10704".equalsIgnoreCase(Base.xxdm)){
				service.setXsjbxx(xsjbxx, model.getXh());
			}
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("cssz", csszService.getModel());
		request.setAttribute("yhList", service.getYhList());
		String path = "zxdk_sydk.do?method=dksq";
		request.setAttribute("path", path);
		this.saveToken(request);
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("dksq_zjdx");
		}
		return mapping.findForward("dksq");
	 }
	
	/**
	 * 
	 * @描述:修改申请
	 *  @作者：ChenQ[工号：856]
	 * @日期：2015-7-2 下午05:08:25
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
	@SystemAuth(url = "zxdk_sydk_dksq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SydkSqshModel myForm = (SydkSqshModel) form;
		SydkSqshService service = getService();
		SydkSqshModel model = service.getModel(myForm.getId());
		if (model != null) {
			BeanUtils.copyProperties(myForm, model);
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//西安科技大学个性化
			if("10704".equalsIgnoreCase(Base.xxdm)){
				service.setXsjbxx(xsjbxx, model.getXh());
			}
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("yhList", service.getYhList());
		//request.setAttribute("dkxxList", service.getDkxxList(model.getId()));
		String path = "zxdk_sydk.do?method=xgDksq";
		request.setAttribute("path", path);
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("xgDksq_zjdx");
		}
		return mapping.findForward("xgDksq");
	}
	
	@SystemAuth(url = "zxdk_sydk_dksq.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问助学贷款-生源地贷款-回执录入-删除VALUES:{id}")
	public ActionForward delDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SydkSqshModel myForm = (SydkSqshModel) form;
		SydkSqshService service = getService();
		boolean result = service.runDelete(new String[]{myForm.getId()}) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**按学号学年查询记录是否存在**/
	public ActionForward isExitsByXhAndXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SydkSqshService service = getService();
		SydkSqshModel model = (SydkSqshModel) form;
		boolean flag = service.isExitsByXhAndXn(model);
		response.getWriter().print(flag);
		return null;
	}	
	
	@SystemAuth(url = "zxdk_sydk_dksh.do")
	public ActionForward dkshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("path", "zxdk_sydk_dksh.do");
		FormModleCommon.commonRequestSet(request);
		
		//设置高级查询默认条件
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz cssz  =  csszService.getModel();
		searchModel.setSearch_tj_xn(new String[]{cssz.getSydkxn()});
		
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("dkshList");
	}
	
	@SystemAuth(url = "zxdk_sydk_dksh.do")
	public ActionForward getDkshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SydkSqshService service = getService();
		SydkSqshModel model = (SydkSqshModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getAudingList(model, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		
		response.getWriter().print(dataList);
		
		return null;
		
	}
	
	@SystemAuth(url = "zxdk_sydk_dksh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward dksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SydkSqshService service = getService();
		SydkSqshModel myForm = (SydkSqshModel) form;
		
		SydkSqshModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//西安科技大学个性化
			if("10704".equalsIgnoreCase(Base.xxdm)){
				service.setXsjbxx(xsjbxx, model.getXh());
			}
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		//request.setAttribute("dkxxList", service.getDkxxList(model.getId()));
		List<HashMap<String,String>> shxx = service.getShxx(model);
		
		//填充审核字段
		if(shxx.size()==1){
			myForm.setZd3(model.getHtbh());
			myForm.setZd5(model.getDkkssj());
			myForm.setZd6(model.getHzjym());
		}else{
			myForm.setZd3(shxx.get(shxx.size()-2).get("zd3"));
			myForm.setZd5(shxx.get(shxx.size()-2).get("zd5"));
			myForm.setZd6(shxx.get(shxx.size()-2).get("zd6"));
		}
		
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("dksh_zjdx");
		}
		
		return mapping.findForward("dksh");
	}
	
	public ActionForward ckDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		SydkSqshService service = getService();
		SydkSqshModel myForm = (SydkSqshModel) form;
		SydkSqshModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//西安科技大学个性化
			if("10704".equalsIgnoreCase(Base.xxdm)){
				service.setXsjbxx(xsjbxx, model.getXh());
			}
		}

		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		//request.setAttribute("dkxxList", service.getDkxxList(model.getId()));
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("ckDksq_zjdx");
		}
		return mapping.findForward("ckDksq");
	}
	
	@SystemAuth(url = "zxdk_sydk_dksh.do")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SydkSqshService service = getService();
		SydkSqshModel model = (SydkSqshModel) form;
		User user = getUser(request);

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// 查询出所有记录，不分页

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
	
	/**
	 * 
	 * @描述: 浙大批量审核功能（个性化）
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-18 上午09:25:26
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
	public ActionForward sydPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SydkSqshModel model = (SydkSqshModel) form;
		SydkSqshService sydkService = new SydkSqshService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(request.getParameter("type"))) {
			String message = sydkService.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("sydPlsh");
	}
	
	/**
	 * 
	 * @描述: 重写生源地贷款申请保存方法
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-18 上午09:25:26
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
	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		SydkSqshModel model = (SydkSqshModel) form;
		SydkSqshService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述: 重写生源地贷款提交申请保存方法(表单直接提交)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-18 上午09:25:26
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
	@Override
	public ActionForward saveAndSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		SydkSqshModel model = (SydkSqshModel) form;
		SydkSqshService service = getService();
		boolean isSuccess = false;
		//设置申请记录对应的审核状态“审核中”
		model.setShzt(Constants.YW_SHZ);
		
		//保证申请ID和审核ID的一致性，由系统生成唯一ID
		if (StringUtil.isNull(model.getId())){
			String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			model.setId(uuid);
			
			//保存申请记录
			isSuccess = service.runInsert(model);
		} else {
			isSuccess = service.runUpdate(model);
		}
		
		JSONObject message = null;
		
		if (isSuccess){
			//提交申请到审核流程
			message = submit("zxdk_sydk" , model.getId(), "zxdk_sydk_dksq.do", "zxdk_sydk_dksh.do");
		} else {
			message = getJsonMessageByKey(MessageKey.SYS_SUBMIT_FAIL);
		}
		
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述：重写表单提交方法
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-3 上午11:19:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gnid
	 * @param id
	 * @param squrl
	 * @param shurl
	 * @return
	 * @throws Exception
	 * JSONObject 返回类型 
	 * @throws
	 */
	private JSONObject submit(String gnid,String id,String squrl,String shurl)
	throws Exception {
		
		ShlcInterface shlc = new CommShlcImpl();
		
		SydkSqshService service = getService();
		//查询申请记录
		SydkSqshModel model = service.getModel(id);
		String splcid = model.getSplcid();
		//提交申请流程
		boolean isSuccess = shlc.runSubmit(id, splcid, model.getXh(), shurl, squrl);
		
		if(isSuccess){
			//更新申请记录状态
			model.setShzt(Constants.YW_SHZ);
			//model.setSplcid(splcid);
			isSuccess = service.runUpdate(model);
		}
		
		String message = isSuccess ? 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_SUCCESS) : 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);
		
		return getJsonMessage(message);
	}
}
