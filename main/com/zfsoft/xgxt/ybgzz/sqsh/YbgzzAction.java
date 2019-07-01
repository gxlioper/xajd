/**
 * @部门:学工产品事业部
 * @日期：2014-11-12 上午09:36:20 
 */  
package com.zfsoft.xgxt.ybgzz.sqsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.ybgzz.cssz.YbgzzCsszService;
import com.zfsoft.xgxt.ybgzz.cywh.YbcyService;

/**
 * 
 * @类功能描述: 易班工作站 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-1-29 上午09:09:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YbgzzAction extends SuperAuditAction<YbgzzModel, YbgzzService> {

	protected YbgzzAction (String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}

	public YbgzzAction(){
		this("ybgzz", "ybgzz_xssq.do", "ybgzz_jssh.do");
	}
	
	
	/**
	 * 
	 * @描述:查询
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-1-29 上午09:14:27
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
	@SystemAuth(url = "ybgzz_xssq.do")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YbgzzCsszService csszService = new YbgzzCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "ybgzz_xssq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("list");
	}
	
	
	/**ajax查询**/
	@SystemAuth(url = "ybgzz_xssq.do")
	public ActionForward getList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YbgzzModel model = (YbgzzModel) form;
		YbgzzService service = new YbgzzService();
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	/**申请**/
	@SystemAuth(url = "ybgzz_xssq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YbgzzModel model = (YbgzzModel) form;
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			YbcyService ybcyService = new YbcyService();
			boolean isExists = ybcyService.isExists(model.getXh());
			boolean isSqExists = ybcyService.isSqExists(model.getXh());
			request.setAttribute("isExists", isExists);
			request.setAttribute("isSqExists", isSqExists);
		}
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		
		YbgzzCsszService csszService = new YbgzzCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "ybgzzSqsh.do?method=add");
		return mapping.findForward("add");
	}
	
	
	/**修改**/
	@SystemAuth(url = "ybgzz_xssq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YbgzzModel djjdForm = (YbgzzModel) form;
		YbgzzService service = new YbgzzService();
		
		YbgzzModel model = service.getModel(djjdForm);
		
		if (model != null){
			BeanUtils.copyProperties(djjdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//加载学生基本信息
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		YbgzzCsszService csszService = new YbgzzCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		//学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("edit");
	}
	
	
	/**取消申请**/
	@SystemAuth(url = "ybgzz_xssq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YbgzzModel djjdForm = (YbgzzModel) form;
		YbgzzService service = new YbgzzService();
		
		boolean result = service.runDelete(new String[]{djjdForm.getId()}) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述:审核列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-1-29 上午09:17:25
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
	@SystemAuth(url = "ybgzz_jssh.do")
	public ActionForward jsshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "ybgzz_jssh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jsshList");
	}
	
	
	/**
	 * 
	 * @描述: ajax加载审核列表
	 * @作者：屈朋辉[工号：445]
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
	@SystemAuth(url = "ybgzz_jssh.do")
	public ActionForward getJsshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YbgzzModel model = (YbgzzModel) form;
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
	
	
	/**教师审核**/
	@SystemAuth(url = "ybgzz_jssh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward jssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YbgzzModel myForm = (YbgzzModel) form;
		YbgzzService service = new YbgzzService();
		
		YbgzzModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
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
		
		return mapping.findForward("jssh");
	}
	
	
	/**查看申请*/
	@SystemAuth(url = "ybgzz_jssh.do")
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YbgzzModel djjdForm = (YbgzzModel) form;
		YbgzzService service = new YbgzzService();
		
		YbgzzModel model = service.getModel(djjdForm.getId());
		
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
		
		return mapping.findForward("view");
	}
	
	
}
