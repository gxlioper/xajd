/**
 * @部门:学工产品事业部
 * @日期：2016-2-1 上午09:16:10 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xsxnjxsq;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsztz.jxgl.xnjxsq.XnjxsqForm;
import com.zfsoft.xgxt.xsztz.jxgl.xnjxsq.XnjxsqService;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgForm;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgService;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbDao;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbService;

import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-2-1 上午09:16:10 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxnjxsqAction extends SuperAction<XsxnjxsqForm, XsxnjxsqService> {
	private static final String url = "sztz_jxgl_xsxnjxsq.do";
	private final String TZXMSQ ="tzxmsq";
	XsxnjxsqService service = new XsxnjxsqService();
	XnjxsqService xnjxsqService = new XnjxsqService();
	XmsbDao xmsbDao = new XmsbDao();
	
	@SystemAuth(url = url)
	public ActionForward xsxnjxsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    XsxnjxsqForm model = (XsxnjxsqForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
//		String[] sqshkg = service.getSqShKg();//错误
//		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("xmjbList", xmsbDao.getXmjbList());
		String path = "sztz_jxgl_xsxnjxsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsxnjxsqList");
	}
	
	
	/** 
	 * @描述:学生校内奖项申请增加
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-1 下午01:45:38
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
	public ActionForward xsxnjxsqAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsxnjxsqForm model = (XsxnjxsqForm) form;
	    User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMSQ);
		request.setAttribute("jbxxList", jbxxList);
		XsXmJgService xsXmJgService = new XsXmJgService();
		XsXmJgForm jgForm = xsXmJgService.getModel(model.getJgid());
		XmsbService xmsbService = new XmsbService();
		HashMap<String, String> xmxx = xmsbService.getXmxx(jgForm.getXmdm());
		request.setAttribute("rs", xmxx);
		List<HashMap<String,String>> jxxx = xmsbService.getXmjxList(jgForm.getXmdm());
		request.setAttribute("xmjxList", jxxx);
		model.setXmdm(jgForm.getXmdm());
		model.setJgid(jgForm.getJgid());
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("currxn", Base.currXn);
		request.setAttribute("xh", user.getUserName());
		String path = "jxgl_xsxnjxsq.do?method=xsxnjxsqAdd";
		request.setAttribute("path", path);
		//其他信息配置
		return mapping.findForward("xsxnjxsqAdd");
	}
	
	/** 
	 * @描述:保存结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-1 下午02:42:26
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
	public ActionForward saveSqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsxnjxsqForm model = (XsxnjxsqForm) form;
		boolean result = false;
	    User user = getUser(request);
//	    XsXmSqForm form1 = new XsXmSqForm();
//		form1.setXh(model.getXh());
 		if(model.getType().equals("save")||model.getType().equals("submit")){
 			model.setXn(Base.currXn);
 			model.setXq(Base.currXq);
			result = service.saveSqjg(model, user);
		}else if(model.getType().equals("update")||model.getType().equals("updatesubmit")){
			result = service.saveSqjgUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-1 下午03:12:59
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
	public ActionForward editSqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsxnjxsqForm myForm = (XsxnjxsqForm) form;
		XnjxsqForm model = xnjxsqService.getModel(myForm.getId());
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMSQ);
		request.setAttribute("jbxxList", jbxxList);
		XmsbService xmsbService = new XmsbService();
		HashMap<String, String> xmxx = xmsbService.getXmxx(model.getXmdm());
		request.setAttribute("rs", xmxx);
		List<HashMap<String,String>> jxxx = xmsbService.getXmjxList(model.getXmdm());
		request.setAttribute("xmjxList", jxxx);
		//HashMap<String, String> hdmap = service.getHdMap(model.getXmdm(),model.getXn(),model.getXq());//false
		//hdmap.put("sqid", model.getSqid());
		//request.setAttribute("hdmap", StringUtils.formatData(hdmap));
		String path = "jxgl_xsxnjxsq.do?method=editSqjg";
		request.setAttribute("path", path);
		request.setAttribute("xh", myForm.getXh());
		return mapping.findForward("editSqjg");
	}
}
