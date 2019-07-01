/**
 * @部门:学工产品事业部
 * @日期：2014-3-6 下午03:30:22 
 */
package com.zfsoft.xgxt.xsxx.fbgl.fbgl;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xsxx.fbgl.Config;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjServer;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-6 下午03:18:09
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglAction extends SuperAction {
	
	private static final String url = "fbglfbbase.do";
	
	/**
	 * 
	 * @描述: 分班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-4 上午09:08:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglService service = new FbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		User user = getUser(request);

		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			myForm.setSearchModel(cs.getSearchModel(request));
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service
					.getPageListForFb(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "fbglfbbase.do");
		request.setAttribute("fbzt", myForm.getFbzt());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fbgllb");
	}

	/**
	 * 
	 * @描述: 已分班
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-4 上午09:07:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward yfblist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglService service = new FbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			myForm.setSearchModel(cs.getSearchModel(request));
			// ==================高级查询相关 end========================
			myForm.setFbzt(FbglService._TBZT_YFB);
			List<HashMap<String, String>> resultList = service
					.getPageListForFb(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "fbglfbbase.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fbglyfblb");
	}

	/**
	 * 
	 * @描述: 分班
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-3 下午02:40:43
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
	public ActionForward fb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglService service = new FbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		String pk = request.getParameter("pk");
		pk=new String (pk.getBytes("iso8859-1"),"gbk");
		// 如果没有选择，则对所有进行操作
		if (StringUtil.isNull(pk)) {
			pk = service.getAllPks();
		}
		// 已分班条数
		request.setAttribute("yfbts", service.getYfbTs(pk));
		// 未分班条数
		request.setAttribute("wfbts", service.getWfbTs(pk));
		// 专业数量
		//request.setAttribute("zysl", service.getSelectZy(pk));
		request.setAttribute("zysl", pk.split(",").length);
		//request.setAttribute("zyids", service.getSelectZyIds(pk));
		// 配置规则集合
		request.setAttribute("pzgzList", fgts
				.getYqyTjnrList(Config._TJGZ_GZDM_FBGZ));
		request.setAttribute("pk",pk);
		return mapping.findForward("fbglfb");
	}

	/**
	 * 
	 * @描述: 生成分班
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-10 下午02:52:29
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
	public ActionForward createFb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglService service = new FbglService();
		String pzgzid = request.getParameter("pzgzid");
		String pk = request.getParameter("pk");
		boolean isok = service.saveFb(pzgzid, pk.split(","));
		Map<String, String> result = new HashMap<String, String>();
		result.put("message", String.valueOf(isok));
		response.getWriter().print(JSONObject.fromObject(result));
		return null;
	}

	/**
	 * 
	 * @描述: 调整分班
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-10 下午02:52:42
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
	@SystemLog(description="访问学生信息-分班管理-分班管理-调整分班VALUES:{values}")
	public ActionForward tzbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglService service = new FbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		String values = request.getParameter("values");
		if (SAVE.equals(myForm.getType())) {
			String bjdm = request.getParameter("bjdm");
			String bjmc = request.getParameter("bjmc");
			boolean isok = service.tzbj(values.split(","), bjdm, bjmc);
			Map<String, String> result = new HashMap<String, String>();
			result.put("message", String.valueOf(isok));
			response.getWriter().print(JSONObject.fromObject(result));
			return null;
		}
		// 班级列表
		request.setAttribute("bjlist", service.getDqBjList(myForm));
		// 已选学生信息
		request.setAttribute("data", service.getTbParam(values.split(",")));
		request.setAttribute("ids", values);
		return mapping.findForward("fbgltb");
	}

	/**
	 * 
	 * @描述: 删除
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-3 下午02:40:57
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
	@SystemLog(description="访问学生信息-分班管理-分班管理-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglService service = new FbglService();
		String values = request.getParameter("values");
		String ids[]=null;
		if(StringUtils.isNotNull(values)){
			ids=values.split(",");
		}
		String fbzt = request.getParameter("fbzt");
		Map<String, Object> map = service.deleteFb(ids, fbzt);
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}
}