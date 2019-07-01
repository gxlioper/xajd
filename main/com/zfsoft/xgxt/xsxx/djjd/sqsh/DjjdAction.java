/**
 * @部门:学工产品事业部
 * @日期：2014-11-12 上午09:36:20 
 */  
package com.zfsoft.xgxt.xsxx.djjd.sqsh;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.djjd.cssz.DjjdCsszService;
import com.zfsoft.xgxt.xsxx.djjd.dmwh.JddjDmwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 技术等级鉴定
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-11-12 上午09:36:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DjjdAction extends SuperAuditAction<DjjdModel, DjjdService> {

	protected DjjdAction (String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}

	public DjjdAction(){
		this("ntgm_djjd", "ntgm_jjdj_sq.do", "ntgm_jjdj_sh.do");
	}
	
	
	/**技术等级鉴定页面**/
	@SystemAuth(url = "ntgm_jjdj_sq.do")
	public ActionForward djjdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DjjdCsszService csszService = new DjjdCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "ntgm_jjdj_sq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("djjdList");
	}
	
	
	/**技术等级鉴定查询**/
	@SystemAuth(url = "ntgm_jjdj_sq.do")
	public ActionForward getDjjdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DjjdModel model = (DjjdModel) form;
		DjjdService service = new DjjdService();
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	/**技术等级鉴定查申请**/
	@SystemAuth(url = "ntgm_jjdj_sq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward jdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DjjdModel model = (DjjdModel) form;
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		DjjdCsszService csszService = new DjjdCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		JddjDmwhService dmwhService = new JddjDmwhService();
		List<HashMap<String, String>> xmdmList = dmwhService.getListByType(JddjDmwhService.Type.XM);
		List<HashMap<String, String>> xmjbList = dmwhService.getListByType(JddjDmwhService.Type.JB);
		
		request.setAttribute("xmdmList", xmdmList);
		request.setAttribute("xmjbList", xmjbList);
		
		request.setAttribute("path", "jddj_sqsh.do?method=jdsq");
		return mapping.findForward("jdsq");
	}
	
	
	/**技术等级鉴定修改**/
	@SystemAuth(url = "ntgm_jjdj_sq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DjjdModel djjdForm = (DjjdModel) form;
		DjjdService service = new DjjdService();
		
		DjjdModel model = service.getModel(djjdForm);
		
		if (model != null){
			BeanUtils.copyProperties(djjdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		JddjDmwhService dmwhService = new JddjDmwhService();
		List<HashMap<String, String>> xmdmList = dmwhService.getListByType(JddjDmwhService.Type.XM);
		List<HashMap<String, String>> xmjbList = dmwhService.getListByType(JddjDmwhService.Type.JB);
		
		request.setAttribute("xmdmList", xmdmList);
		request.setAttribute("xmjbList", xmjbList);
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		return mapping.findForward("xgsq");
	}
	
	
	/**取消申请**/
	@SystemAuth(url = "jddj_sqsh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-技术等级鉴定（南通工贸）-鉴定结果-取消申请PK:{id}")
	public ActionForward qxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DjjdModel djjdForm = (DjjdModel) form;
		DjjdService service = new DjjdService();
		
		boolean result = service.runDelete(new String[]{djjdForm.getId()}) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述: 贷款审核列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-25 下午03:37:30
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
	@SystemAuth(url = "ntgm_jjdj_sh.do")
	public ActionForward jdshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "ntgm_jjdj_sh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jdshList");
	}
	
	
	/**
	 * 
	 * @描述: ajax加载贷款审核列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-25 下午03:39:06
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
	@SystemAuth(url = "ntgm_jjdj_sh.do")
	public ActionForward getJdshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DjjdModel model = (DjjdModel) form;
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
	
	
	/**技术等级鉴定审核**/
	@SystemAuth(url = "ntgm_jjdj_sh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward jdsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DjjdModel djjdForm = (DjjdModel) form;
		DjjdService service = new DjjdService();
		
		DjjdModel model = service.getModel(djjdForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(djjdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("jdsh");
	}
	
	
	/**查看申请*/
	@SystemAuth(url = "ntgm_jjdj_sh.do")
	public ActionForward cksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DjjdModel djjdForm = (DjjdModel) form;
		DjjdService service = new DjjdService();
		
		DjjdModel model = service.getModel(djjdForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(djjdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("cksq");
	}
}
