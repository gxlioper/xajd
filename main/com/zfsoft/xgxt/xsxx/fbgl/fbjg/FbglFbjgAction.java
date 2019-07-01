/**
 * @部门:学工产品事业部
 * @日期：2014-3-24 上午10:42:10 
 */
package com.zfsoft.xgxt.xsxx.fbgl.fbjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-24 上午10:42:10
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglFbjgAction extends SuperAction {
	
	private static final String url = "fbglfbjgbase.do";
	
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglFbjgService service = new FbglFbjgService();
		FbglXsxxForm myForm = (FbglXsxxForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			myForm.setSearchModel(cs.getSearchModel(request));
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "fbglfbjgbase.do");
		request.setAttribute("tjzt", myForm.getTjzt());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fbglfbjglb");
	}

	/**
	 * 
	 * @描述: 提交正式库
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-26 下午02:36:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-分班管理-分班结果管理-提交正式库NJ:{nj}")
	public ActionForward tjzsk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglFbjgService service = new FbglFbjgService();
		FbglXsxxForm myForm = (FbglXsxxForm) form;
		String nj = request.getParameter("nj");
		User user=getUser(request);
		if (SAVE.equals(myForm.getType())) {
			Map<String, String> map = service.tjzskForMessage(nj,user);
			response.getWriter().print(JSONObject.fromObject(map));
			return null;
		}
		List<HashMap<String, String>> list=service.getNjList();
		if(StringUtils.isNull(nj)&&null!=list&&list.size()>0){
			nj=list.get(0).get("nj");
		}
		//未提交
		request.setAttribute("wtj", service.getXsxxSl(false, nj));
		//已提交
		request.setAttribute("ytj", service.getXsxxSl(true, nj));
		request.setAttribute("njList", list);
		return mapping.findForward("fbglfbjgtj");
	}

	/**
	 * 
	 * @描述: 撤销正式库数据
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-26 下午02:36:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-分班管理-分班结果管理-撤销提交NJ:{nj}")
	public ActionForward cxzsk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglFbjgService service = new FbglFbjgService();
		FbglXsxxForm myForm = (FbglXsxxForm) form;
		String nj = request.getParameter("nj");
		User user=getUser(request);
		if (SAVE.equals(myForm.getType())) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", "true");
			if (!service.cxzsk(nj,user)) {
				map.put("message", "false");
			}
			response.getWriter().print(JSONObject.fromObject(map));
			return null;
		}
		List<HashMap<String, String>> list=service.getYtjNjList();
		if(StringUtils.isNull(nj)&&null!=list&&list.size()>0){
			nj=list.get(0).get("nj");
		}
		//未提交
		request.setAttribute("wtj", service.getXsxxSl(false, nj));
		//已提交
		request.setAttribute("ytj", service.getXsxxSl(true, nj));
		request.setAttribute("njList", list);
		return mapping.findForward("fbglfbjgcx");
	}

	/**
	 * 
	 * @描述: 下载错误信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-26 下午02:36:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglFbjgService ffs = new FbglFbjgService();
		ffs.download(request, response);
		return null;
	}
	/**
	 * 
	 * @描述: 获取提交信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-14 下午05:17:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getTjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglFbjgService service = new FbglFbjgService();
		String nj=request.getParameter("nj");
		Map<String,Integer> data=new HashMap<String, Integer>();
		data.put("wtj", service.getXsxxSl(false, nj));
		data.put("ytj", service.getXsxxSl(true, nj));
		response.getWriter().print(JSONObject.fromObject(data));
		return null;
	}

}
