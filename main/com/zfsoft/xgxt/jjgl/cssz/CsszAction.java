/**
 * @部门:学工产品事业部
 * @日期：2014-8-21 下午05:03:11 
 */  
package com.zfsoft.xgxt.jjgl.cssz;

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

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.jjgl.cssz.dmwh.FbzgForm;
import com.zfsoft.xgxt.jjgl.cssz.dmwh.FbzgService;
import com.zfsoft.xgxt.jjgl.cssz.dmwh.SfbzForm;
import com.zfsoft.xgxt.jjgl.cssz.dmwh.SfbzService;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjForm;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjService;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkForm;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-21 下午05:03:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszAction  extends SuperAction<CsszForm, CsszService> {
	/**
	 * @属性： SPL_MKID 模块ID
	 */
	public static final String SPL_MKID = "jjgl";
	
	/**
	 *  @属性： PATH 路径
	 */
	private static final String PATH = "jjgl_jcsz.do";
	
	/**
	 *  @属性：DMWH_PATH 路径
	 */
	private static final String DMWH_PATH = "jjxt_dmwh.do";

	/**
	 * @描述 审批流服务
	 */
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	/**
	 * @描述 家教学科查询
	 */
	private JjxkService jjxkService = new JjxkService();
	
	/**
	 * @描述 家教年级查询
	 */
	private JjnjService jjnjService = new JjnjService();
	
	/**
	 * 收费标准查询
	 */
	private SfbzService sfbzService = new SfbzService();
	
	/**
	 * 发布资格查询
	 */
	private FbzgService fbzgService = new FbzgService();
	
	private static final String url = "jjgl_jcsz.do";
	
	/**
	 * 
	 * @描述:参数设置表单
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-22 下午01:04:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		/**
		 * 获取配置数据
		 */
		Map<String , String> configDataMap = getService().getConfigMap();
		/**
		 * 设置model值
		 */
		if(configDataMap != null){
			BeanUtils.populate(form, configDataMap);
		}
		
		List<HashMap<String, String>> kgOptions = getService().getKgOptions();
		request.setAttribute("kgOptions", kgOptions);
		// 以下为公共配置项
		request.setAttribute("shlcList", shlcService.getShlcByDjlx(SPL_MKID));
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}

	/**
	 * 
	 * @描述:保存参数设置信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-25 上午10:20:29
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
	public  ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		CsszForm model = (CsszForm)	form;
		boolean result = false;
		JSONObject message = null;
		result = getService().saveCssz(model);
		message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	
	
	
	
	
	
	
	//======================代码维护===========================//
	/**
	 * 
	 * @描述:代码维护
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-22 下午01:04:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	@SystemAuth(url = DMWH_PATH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", DMWH_PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dmwh");
	}
	
	/**
	 * 
	 * @描述:获取各项数据
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-25 下午01:59:18
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
	@SystemAuth(url = DMWH_PATH)
	public ActionForward queryDataList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CsszForm model = (CsszForm)	form;
		List<HashMap<String,String>> resultList = null;
		String qryType = model.getQryType();
		//家教学科
		if(StringUtils.equals("jjxk", qryType)){
			JjxkForm queryModel = new JjxkForm();
			queryModel.setPages(model.getPages());
			resultList = this.jjxkService.getPageList(queryModel);
		//家教年级
		}else if(StringUtils.equals("jjnj", qryType)){
			JjnjForm queryModel = new JjnjForm();
			queryModel.setPages(model.getPages());
			resultList = this.jjnjService.getPageList(queryModel);
		}
		//查询
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:添加
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-25 下午04:03:57
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
	@SystemAuth(url = DMWH_PATH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CsszForm model = (CsszForm)	form;
		String qryType = model.getQryType();
		
		if(StringUtils.equals("sfbz", qryType)){
			
			request.setAttribute("jjxkList", jjxkService.getAllList(new JjxkForm()));
			request.setAttribute("jjnjList", jjnjService.getAllList(new JjnjForm()));
		}
		
		
		String forward = "add_" + qryType;
		return mapping.findForward(forward);
	}
	
	
	/**
	 * 
	 * @描述:修改
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-25 下午04:03:57
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
	@SystemAuth(url = DMWH_PATH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CsszForm model = (CsszForm)	form;
		String qryType = model.getQryType();
		String dm = model.getDm();
		if(StringUtils.equals("jjxk", qryType)){
			JjxkForm dataModel = jjxkService.getModel(dm);
			request.setAttribute("dataModel", dataModel);
		}else if(StringUtils.equals("jjnj", qryType)){
			JjnjForm dataModel = jjnjService.getModel(dm);
			request.setAttribute("dataModel", dataModel);
		}else if(StringUtils.equals("fbzg", qryType)){
			FbzgForm dataModel = fbzgService.getModel(dm);
			request.setAttribute("dataModel", dataModel);
		}else if(StringUtils.equals("sfbz", qryType)){
			SfbzForm dataModel = sfbzService.getModel(dm);
			request.setAttribute("dataModel", dataModel);
			request.setAttribute("jjxkList", jjxkService.getAllList(new JjxkForm()));
			request.setAttribute("jjnjList", jjnjService.getAllList(new JjnjForm()));
		}
		
		
		String forward = "update_" + qryType;
		return mapping.findForward(forward);
	}
	
	/**
	 * @return the shlcService
	 */
	public XtwhShlcService getShlcService() {
		return shlcService;
	}

	/**
	 * @param shlcService要设置的 shlcService
	 */
	public void setShlcService(XtwhShlcService shlcService) {
		this.shlcService = shlcService;
	}

	/**
	 * @return the jjxkService
	 */
	public JjxkService getJjxkService() {
		return jjxkService;
	}

	/**
	 * @param jjxkService要设置的 jjxkService
	 */
	public void setJjxkService(JjxkService jjxkService) {
		this.jjxkService = jjxkService;
	}

	/**
	 * @return the jjnjService
	 */
	public JjnjService getJjnjService() {
		return jjnjService;
	}

	/**
	 * @param jjnjService要设置的 jjnjService
	 */
	public void setJjnjService(JjnjService jjnjService) {
		this.jjnjService = jjnjService;
	}

	/**
	 * @return the sfbzService
	 */
	public SfbzService getSfbzService() {
		return sfbzService;
	}

	/**
	 * @param sfbzService要设置的 sfbzService
	 */
	public void setSfbzService(SfbzService sfbzService) {
		this.sfbzService = sfbzService;
	}

	/**
	 * @return the fbzgService
	 */
	public FbzgService getFbzgService() {
		return fbzgService;
	}

	/**
	 * @param fbzgService要设置的 fbzgService
	 */
	public void setFbzgService(FbzgService fbzgService) {
		this.fbzgService = fbzgService;
	}

}
