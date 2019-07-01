/**
 * @部门:学工产品事业部
 * @日期：2014-3-10 下午05:21:45 
 */
package com.zfsoft.xgxt.xsxx.fbgl.bxh;

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
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xsxx.fbgl.Config;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjServer;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: 生成学号action
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-10 下午05:21:45
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglBxhAction extends SuperAction {
	
	private static final String url = "fbglbxhbase.do";
	
	/**
	 * 
	 * @描述: 列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-4 下午03:54:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBxhService service = new FbglBxhService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			myForm.setSearchModel(cs.getSearchModel(request));
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service
					.getPageListForBxh(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "fbglbxhbase.do");
		//编学号状态
		request.setAttribute("xhzt",FbglBxhService._BXH_WBXH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bxhlb");
	}
	/**
	 * 
	 * @描述: 结果列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-4 下午03:53:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward jglist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBxhService service = new FbglBxhService();
	
		FbglBbglForm myForm = (FbglBbglForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			SearchModel sm=cs.getSearchModel(request);
			sm.setPath("fbglbxhjgbase.do");
			myForm.setSearchModel(sm);
			myForm.setXhzt(FbglBxhService._BXH_YBXH);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service
					.getPageListForBxh(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "fbglbxhbase.do");
		//编学号状态
		request.setAttribute("xhzt",FbglBxhService._BXH_YBXH);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "fbglbxhjgbase.do");
		return mapping.findForward("bxhjglb");
	}
	/**
	 * 
	 * @描述: 生成学号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-4 下午03:41:05
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
	@SystemLog(description="访问学生信息-分班管理-编学号-生成学号PK:{pk}")
	public ActionForward scxh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBxhService service = new FbglBxhService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		String pk = request.getParameter("pk");
		String bxhzt = request.getParameter("bxhzt");
		if (SAVE.equals(myForm.getType())) {
			String barKey = request.getParameter("barkey");
			String pzgzid = request.getParameter("pzgzid");
			String msg= service.scxh(pk, pzgzid, barKey, bxhzt);
			response.getWriter().print(getJsonMessageByKey(msg));
			return null;
		}
		// 学生总数
		request.setAttribute("xszs", service.getXszs(pk));
		// 已编学号
		request.setAttribute("ybxh", service.getYbxhXs(pk));
		// 未编学号
		int wbxh = service.getWbxhXs(pk);
		request.setAttribute("wbxh", wbxh);
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		//编学号规则
		request.setAttribute("pzgzList", fgts.getYqyTjnrList(Config._TJGZ_GZDM_BXHGZ));
		request.setAttribute("pk", pk);
		//编学号状态
		request.setAttribute("bxhzt", bxhzt);
		return mapping.findForward("scxh");
	}
	/**
	 * 
	 * @描述: 结果页面生成学号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-4 下午03:42:17
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
	@SystemLog(description="访问学生信息-分班管理-编学号-结果页面生成学号PK:{pk}")
	public ActionForward jgscxh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBxhService service = new FbglBxhService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		String pk = request.getParameter("pk");
		String bxhzt = request.getParameter("bxhzt");
		if (SAVE.equals(myForm.getType())) {
			String barKey = request.getParameter("barkey");
			String pzgzid = request.getParameter("pzgzid");
			response.getWriter().print(service.scxh(pk, pzgzid, barKey, bxhzt));
			return null;
		}
		
		FbglXsxxService fxs=new FbglXsxxService();
		//学生总数
		request.setAttribute("xszs",fxs.getXsxxForNjKsh(pk, FbglXsxxService._XSXX_LXCX_ALL).size());
		//已编学号
		request.setAttribute("ybxh", fxs.getXsxxForNjKsh(pk, FbglXsxxService._XSXX_LXCX_YBXH).size());
		//未编学号
		request.setAttribute("wbxh",fxs.getXsxxForNjKsh(pk, FbglXsxxService._XSXX_LXCX_WBXH).size());
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		//编学号规则
		request.setAttribute("pzgzList", fgts.getYqyTjnrList(Config._TJGZ_GZDM_BXHGZ));
		request.setAttribute("pk", pk);
		request.setAttribute("bxhzt", bxhzt);
		return mapping.findForward("scxh");
	}
	/**
	 * @描述: 删除
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-8 下午03:32:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-分班管理-编学号-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBxhService service = new FbglBxhService();
		String values = request.getParameter("values");
		String type = request.getParameter("type");
		Map<String, Object> map=null;
		if (!StringUtil.isNull(values)) {
			 map = service.deleteXh(values.split(","), type);
		} else {
			map=service.deleteAllXh();
		}
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}
}
