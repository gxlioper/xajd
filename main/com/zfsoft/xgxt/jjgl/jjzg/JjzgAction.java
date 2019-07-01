/**
 * @部门:学工产品事业部
 * @日期：2014-8-18 上午11:21:07 
 */  
package com.zfsoft.xgxt.jjgl.jjzg;

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

import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.jjgl.cssz.CsszService;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjForm;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjService;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkForm;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @类功能描述: 家教资格
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-8-18 上午11:21:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JjzgAction extends SuperAuditAction<JjzgForm, JjzgService> {

	protected JjzgAction(String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}

	public JjzgAction(){
		this("xsjj_zgsq", "xsjj_zgsq.do", "xsjj_zgsh.do");
	}
	
	private static final String url = "xsjj_zgsq.do";
	
	/**
	 * 
	 * @描述:家教资格申请
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-8-18 上午11:23:49
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
	public ActionForward zgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		JjzgService service = getService();
		
		// 加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
		request.setAttribute("jbxx", xsjbxx);
		
		//加载学生家教信息
		JjzgForm model = service.getModel(user.getUserName());
		
		//家教年级列表
		JjnjService jjnjService = new JjnjService();
		List<HashMap<String,String>> jjnjList = jjnjService.getAllList(new JjnjForm());
		request.setAttribute("jjnjList", jjnjList);
		
		//家教学科列表
		JjxkService jjxkService = new JjxkService();
		List<HashMap<String,String>> jjxkList = jjxkService.getAllList(new JjxkForm());
		request.setAttribute("jjxkList", jjxkList);
		// 根据参数设置加载 审批流程信息
		CsszService csszService = new CsszService();
		request.setAttribute("cssz", csszService.getConfigMap());
		
		request.setAttribute("path", "xsjj_zgsq.do");
		FormModleCommon.commonRequestSet(request);
		
		if (model != null){
			BeanUtils.copyProperties(form, model);
			
			List<HashMap<String,String>> shxxList = ShlcUtil.getShlcInfo(model.getSqid());
			request.setAttribute("shxxList", shxxList);
			
			if (!Constants.YW_WTJ.equals(model.getShzt())){
				return mapping.findForward("zgsqView");
			}
		}
		
		return mapping.findForward("zgsq");
	}
	
	
	/**家教个人资料**/
	@SystemAuth(url = url)
	public ActionForward grzl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		JjzgService service = getService();
		
		//加载学生家教信息
		JjzgForm jjzgModel = service.getModel(user.getUserName());
		
		if (jjzgModel == null || !Constants.YW_TG.equals(jjzgModel.getShzt())){
			request.setAttribute("message", "未获得家教资格，无权访问此页面！");
			return mapping.findForward("error");
		}
		
		// 加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
		request.setAttribute("jbxx", xsjbxx);
		
		//加载学生家教信息
		JjzgForm model = service.getModel(user.getUserName());
		
		if (model != null){
			BeanUtils.copyProperties(form, model);
		}
		
		request.setAttribute("path", "xsjj_grzl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("grzl");
	}
	

	/**
	 * 
	 * @描述: 家教资格审核
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-8-25 上午08:58:21
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
	@SystemAuth(url = "xsjj_zgsh.do")
	public ActionForward zgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "xsjj_zgsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zgsh");
	}
	
	
	/**
	 * 
	 * @描述: 资格审核列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-8-25 上午08:59:55
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
	@SystemAuth(url = "xsjj_zgsh.do")
	public ActionForward getZgshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JjzgForm model = (JjzgForm) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//查询
		List<HashMap<String,String>> resultList = getService().getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 家教资格单个审核
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-8-25 下午04:25:25
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
	@SystemAuth(url = "xsjj_zgsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward jjzgDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JjzgForm jjzgForm = (JjzgForm) form;
		//加载学生家教信息
		JjzgForm model = getService().getModel(jjzgForm.getSqid());
		
		// 加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		if (model != null){
			BeanUtils.copyProperties(form, model);
		}
		
		return mapping.findForward("jjzgDgsh");
	}
	
	
	/**
	 * 
	 * @描述: 家教资格查看
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-8-29 上午09:43:39
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
	
	public ActionForward jjzgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JjzgForm jjzgForm = (JjzgForm) form;
		//加载学生家教信息
		JjzgForm model = getService().getModel(jjzgForm.getXh());
		
		// 加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		if (model != null){
			BeanUtils.copyProperties(form, model);
		
			//家教经历
			List<HashMap<String, String>> jjjlList = getService().getJJjlList(model.getXh());
			request.setAttribute("jjjlList", jjjlList);
		}
		
		
		
		return mapping.findForward("jjzgView");
	}
}
