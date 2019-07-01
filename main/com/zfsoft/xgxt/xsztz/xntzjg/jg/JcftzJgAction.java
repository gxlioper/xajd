/**
 * @部门:学工产品事业部
 * @日期：2016-3-31 上午11:17:17 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.jg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-31 上午11:17:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcftzJgAction extends SuperAction<JcftzJgForm, JcftzJgService>{
	private static final String url = "sztz_jcftz_jg.do";
	
	private JcftzJgService service = new JcftzJgService();
	
	/** 
	 * @描述:查询
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-1 下午04:32:06
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
	@SystemAuth(url = url)
	public ActionForward getJcftzJgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
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
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_jcftz_jg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getJcftzJgList");
	}
	
	/** 
	 * @描述:添加项目
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-5 上午09:03:57
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
	public ActionForward addJcftzJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
	    User user = getUser(request);
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("currxn", Base.currXn);
		String path = "jcftz_jg.do?method=addJcftzJg";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		//其他信息配置
		return mapping.findForward("addJcftzJg");
	}
	
	/** 
	 * @描述:得到项目列表
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-5 上午09:56:53
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
	@SystemAuth(url = url)
	public ActionForward getXmSelectList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
	    User user = getUser(request);
		List<HashMap<String,String>> xmsqInfoList = service.getXmSelectList(model,user);
	    request.setAttribute("xmsqInfoList", xmsqInfoList);
		return mapping.findForward("xmselect");
	}
	
	/** 
	 * @描述:通过ajax得到项目列表
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-5 下午02:18:21
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
	@SystemAuth(url = url)
	public ActionForward getXmSelectListForAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
	    User user = getUser(request);
		List<HashMap<String,String>> xmsqInfoList = service.getXmSelectList(model,user);
		JSONArray dataList = JSONArray.fromObject(xmsqInfoList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/** 
	 * @描述:获取需要认定的学生列表
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-5 下午02:20:02
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
	@SystemAuth(url = url)
	public ActionForward getXsListForRenDingForAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
		String csms = model.getCsms();
	    User user = getUser(request);
	    List<HashMap<String,String>> xmsqInfoList = null;
	    if("1".equals(csms)){
	    	 xmsqInfoList = service.getXsListForRenDing(model,user);
	    }else if("2".equals(csms)){
	    	xmsqInfoList = service.getTtListForRenDing(model, user);
	    }else{
	    	//如果csms(参赛模式)字段值不等于1也不等于2，直接返回空
	    	return null;
	    }
		JSONArray dataList = JSONArray.fromObject(xmsqInfoList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/** 
	 * @描述:保存
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-5 下午06:37:04
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
	public ActionForward savejg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
		model.setXhs(request.getParameterValues("xh"));
		model.setJxdms(request.getParameterValues("jxdm"));
		model.setSfqqs(request.getParameterValues("sfqq"));
		model.setTzhjcfs(request.getParameterValues("tzhjcf"));
		model.setJgids(request.getParameterValues("jgidss"));
		
		
		if(null!=request.getParameterValues("bz1")){		//重庆移通备注1-5
			model.setBz1s(request.getParameterValues("bz1"));
			model.setBz2s(request.getParameterValues("bz2"));
			model.setBz3s(request.getParameterValues("bz3"));
			model.setBz4s(request.getParameterValues("bz4"));
			model.setBz5s(request.getParameterValues("bz5"));
		}else{
			int n=model.getSfqqs().length;
			String[] strs=new String[n];
			model.setBz1s(strs);
			model.setBz2s(strs);
			model.setBz3s(strs);
			model.setBz4s(strs);
			model.setBz5s(strs);
		}
		boolean result = service.saveJg(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;						
	}
	
	/** 
	 * @描述:删除结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-8 下午02:05:55
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
	public ActionForward delJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
		String xmdms = request.getParameter("xmdms");
		String[] xmdmArr = xmdms.split(",");
		List<String> xmdmList = new ArrayList<String>();
		for(int i = 0;i<xmdmArr.length;i++){
			if(xmdmList.contains(xmdmArr[i])){
				continue;
			}else{
				xmdmList.add(xmdmArr[i]);
			}			
		}
		Boolean result = true;
		for(String xmdm : xmdmList){
			model.setXmdm(xmdm);
			model.setXfrdjgzt("0");
		    result = service.delForJg(model);
		    result = service.updateRenDing(model);
		}
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS
				: MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:修改
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-8 下午03:34:54
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
	public ActionForward editJcftzJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
		Map<String, String> map = service.getXmxx(model);
		request.setAttribute("rs", map);
		String path = "jcftz_jg.do?method=editJcftzJg";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		//其他信息配置
		return mapping.findForward("editJcftzJg");
	}
	
	/** 
	 * @描述:得到需要修改认定的学生列表（通过ajax）
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-8 下午03:49:08
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
	@SystemAuth(url = url)
	public ActionForward getXsListForUpdateForAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
	    User user = getUser(request);
		List<HashMap<String,String>> xmsqInfoList = service.getXsListForUpdate(model,user);
		JSONArray dataList = JSONArray.fromObject(xmsqInfoList);
		response.getWriter().print(dataList);
		return null;
	}
	
}
