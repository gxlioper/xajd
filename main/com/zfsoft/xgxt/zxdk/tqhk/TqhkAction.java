/**
 * @部门:学工产品事业部
 * @日期：2014-11-5 上午09:33:24 
 */  
package com.zfsoft.xgxt.zxdk.tqhk;

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

import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCssz;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;
import com.zfsoft.xgxt.zxdk.hkzt.HkztModel;
import com.zfsoft.xgxt.zxdk.hkzt.HkztService;
import com.zfsoft.xgxt.zxdk.xyddk.DkjgService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款
 * @类功能描述: 提前还款 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-11-5 上午09:33:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TqhkAction extends SuperAuditAction<TqhkModel, TqhkService> {

	protected TqhkAction(String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}
	
	public TqhkAction(){
		this("zxdk_xyddk", "zxdk_tqhk_hksq.do", "zxdk_tqhk_hksh.do");
	}

	/**还款申请*/
	@SystemAuth(url = "zxdk_tqhk_hksq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward hksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		
		if (!"stu".equals(user.getUserType())){
			request.setAttribute("message", "该页面只能学生用户访问。");
			return mapping.findForward("error");
		}
		
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz csszModel = csszService.getModel();
		
		if (Constants.CLOSE.equals(csszModel.getTqhkKg())){
			request.setAttribute("message", "提前还款未开放。");
			return mapping.findForward("error");
		}
		
		DkjgService dkjgService = new DkjgService();
		List<HashMap<String,String>> khkList = null;
		if("10511".equals(Base.xxdm)){
			khkList = dkjgService.getKhkListHsd(user.getUserName());
		}else{
			khkList = dkjgService.getKhkList(user.getUserName());
		}
		if (khkList == null || khkList.size() == 0){
			request.setAttribute("message", "无可还款的贷款记录。");
			return mapping.findForward("error");
		}
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("khkList", khkList);
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("hksq");
	}
	
	
	/**修改还款申请**/
	@SystemAuth(url = "zxdk_tqhk_hksq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TqhkService service = getService();
		TqhkModel tqhkForm = (TqhkModel) form;
		
		TqhkModel model =  service.getModel(tqhkForm);
		
		if (model != null){
			BeanUtils.copyProperties(tqhkForm, model);
		}
		
		DkjgService dkjgService = new DkjgService();
		List<HashMap<String,String>> khkList = null;
		if("10511".equals(Base.xxdm)){
			khkList = dkjgService.getKhkListHsd(model.getXh());
		}else{
			khkList = dkjgService.getKhkList(model.getXh());
		}
		request.setAttribute("khkList", khkList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("xgsq");
	}
	
	
	/**还款申请列表*/
	@SystemAuth(url = "zxdk_tqhk_hksq.do")
	public ActionForward hksqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz csszModel = csszService.getModel();
		
		User user = getUser(request);
		
		if (!"stu".equals(user.getUserType())){
			request.setAttribute("message", "该页面只能学生用户访问。");
			return mapping.findForward("error");
		}
		
		DkjgService dkjgService = new DkjgService();
		List<HashMap<String,String>> khkList = dkjgService.getKhkList(user.getUserName());
		
		if (khkList == null || khkList.size() == 0){
			request.setAttribute("message", "无可还款的贷款记录。");
			return mapping.findForward("error");
		}
		
		request.setAttribute("cssz", csszModel);
		request.setAttribute("path", "zxdk_tqhk_hksq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hksqList");
	}
	
	
	/**还款申请列表*/
	@SystemAuth(url = "zxdk_tqhk_hksq.do")
	public ActionForward getHksqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TqhkService service = getService();
		TqhkModel model = (TqhkModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**删除提前还款申请*/
	@SystemAuth(url = "zxdk_tqhk_hksq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward delTqhk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TqhkService service = getService();
		TqhkModel model = (TqhkModel) form;
		
		boolean result = service.runDelete(new String[]{model.getId()}) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}

	
	
	/**还款审核列表*/
	@SystemAuth(url = "zxdk_tqhk_hksh.do")
	public ActionForward hkshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "zxdk_tqhk_hksh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hkshList");
	}
	
	
	/**还款审核列表*/
	@SystemAuth(url = "zxdk_tqhk_hksh.do")
	public ActionForward getHkshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TqhkModel model = (TqhkModel) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//查询
		List<HashMap<String,String>> resultList = getService().getAudingList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**还款审核**/
	@SystemAuth(url = "zxdk_tqhk_hksh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward hksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TqhkService service = getService();
		TqhkModel tqhkForm = (TqhkModel) form;
		TqhkModel model =  service.getModel(tqhkForm);
		
		if (model != null){
			BeanUtils.copyProperties(tqhkForm, model);
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			DkjgService dkjgService = new DkjgService();
			List<HashMap<String,String>> khkList = null;
			if("10511".equals(Base.xxdm)){
				khkList = dkjgService.getKhkListHsd(model.getXh());
			}else{
				khkList = dkjgService.getKhkList(model.getXh());
			}
			request.setAttribute("khkList", khkList);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("gjzxdk");
		request.setAttribute("jbxxList", jbxxList);
		
		HkztService hkztService = new HkztService();
		List<HashMap<String,String>> hkztList = hkztService.getAllList(new HkztModel());
		request.setAttribute("hkztList", hkztList);
		
		return mapping.findForward("hksh");
	}

	/**查看提前还款申请**/
	public ActionForward viewHksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TqhkService service = getService();
		TqhkModel tqhkForm = (TqhkModel) form;
		
		TqhkModel model =  service.getModel(tqhkForm);
		
		if (model != null){
			BeanUtils.copyProperties(tqhkForm, model);
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//学生基本信息显示配置
			BdpzService bdpzService = new BdpzService();
			List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("gjzxdk");
			request.setAttribute("jbxxList", jbxxList);
		}
		
		DkjgService dkjgService = new DkjgService();
		List<HashMap<String,String>> khkList = null;
		if("10511".equals(Base.xxdm)){
			khkList = dkjgService.getKhkListHsd(model.getXh());
		}else{
			khkList = dkjgService.getKhkList(model.getXh());
		}
		request.setAttribute("khkList", khkList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("viewHksq");
	}
	
	/**
	 * 
	 * @描述: 重写表单新增保存方法
	 * @作者： yxy[工号:1206]
	 * @日期：2014年6月9日 上午9:51:00
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
		TqhkModel model = (TqhkModel) form;
		TqhkService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述: 表单新增提交
	 * @作者： yxy[工号:1206]
	 * @日期：2014年6月9日 上午9:51:00
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
		TqhkModel model = (TqhkModel) form;
		TqhkService service = getService();
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
			message = submit("zxdk_xyddk" , model.getId(), "zxdk_tqhk_hksq.do", "zxdk_tqhk_hksh.do");
		} else {
			message = getJsonMessageByKey(MessageKey.SYS_SUBMIT_FAIL);
		}
		
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述: 重写提交 方法
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-3 上午11:57:53
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
		
		TqhkService service = getService();
		//查询申请记录
		TqhkModel model = service.getModel(id);
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
