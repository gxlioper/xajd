/**
 * @部门:学工产品事业部
 * @日期：2014-2-17 上午09:43:43 
 */
package com.zfsoft.xgxt.xsxx.fbgl.bbgl;

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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsxx.fbgl.Config;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjServer;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 编班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-2-17 上午09:43:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglBbglAcion extends SuperAction {
	
	private static final String url = "fbglbbglbase.do";
	
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
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
		request.setAttribute("path", "fbglbbglbase.do");
		//编班状态
		request.setAttribute("bbzt", myForm.getBbzt());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fbglbbgllb");
	}

	/**
	 * 
	 * @描述: 添加
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-1 上午10:28:42
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
	@SystemLog(description="访问学生信息-分班管理-编班管理-增加")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service.getBbxx(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		//编班配置规则id
		request.setAttribute("pzgzList", fgts.getYqyTjnrList(Config._TJGZ_GZDM_BBGZ));
		request.setAttribute("pk", myForm.getPk());
		return mapping.findForward("fbglbbglzj");
	}

	/**
	 * 
	 * @描述: 分班管理列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-4 上午09:24:52
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
	public ActionForward fbglbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		//配置规则id
		String pzgzid = request.getParameter("pzgzid");
		request.setAttribute("pzgzid", pzgzid);
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service.getBbxx(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		} else {
			//可修改信息
			String kxgxx = service.getKxgxx(pzgzid);
			request.setAttribute("autoGrid", service.getAutoGrid(kxgxx));
		}
		return mapping.findForward("fbglbbglbb");

	}

	/**
	 * 
	 * @描述: 保存编班
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-26 下午03:32:49
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
	@SystemLog(description="访问学生信息-分班管理-编班管理-保存编班PZGZID:{pzgzid}")
	public ActionForward saveBb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		String pzgzid = request.getParameter("pzgzid");
		String json = request.getParameter("json");
		String all = request.getParameter("all");
		JSONArray array = JSONArray.fromString(json);
		String messageKey = service.save(array, pzgzid, all);
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述: 保存调班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-5 下午03:43:40
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
	@SystemLog(description="访问学生信息-分班管理-编班管理-保存调班PZGZID:{pzgzid}")
	public ActionForward saveTbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		String pzgzid = request.getParameter("pzgzid");
		String json = request.getParameter("json");
		String pk = request.getParameter("pk");

		JSONArray array = JSONArray.fromString(json);
		boolean isok = service.saveTbxx(array, pzgzid, pk);
		String messageKey = isok ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述: 调班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-1 上午10:28:58
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
	public ActionForward fbgltb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		String pk = request.getParameter("pk");
		List<HashMap<String, String>> bjxx = service.getTbJtBj(pk);
		if (QUERY.equals(myForm.getType())) {
			JSONArray dataList = JSONArray.fromObject(bjxx);
			response.getWriter().print(dataList);
			return null;
		} else {
			HashMap<String, String> data = service.getTbxx(pk);
			String pzgzid = bjxx.get(0).get("pzgzid");
			request.setAttribute("data", StringUtils.formatData(data));
			request.setAttribute("pzgzid", pzgzid);
			request.setAttribute("bjgs", bjxx.size());
			//配置规则名称
			FbglGzpzTjServer fgtxs = new FbglGzpzTjServer();
			FbglGzpzTjForm fgtxf = fgtxs.getModel(pzgzid);
			request.setAttribute("pzgzmc", fgtxf.getPzgzmc());
			//可修改信息
			String kxgxx = service.getKxgxx(pzgzid);
			request.setAttribute("autoGrid", service.getAutoGrid(kxgxx));
		}
		request.setAttribute("pk", pk);
		return mapping.findForward("fbglbbgltb");

	}

	/**
	 * @描述: 调班详细信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-1 上午10:29:29
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
	public ActionForward fbgltbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		FbglBbglForm myForm = (FbglBbglForm) form;
		String pk = request.getParameter("pk");
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> bjxx = service.getTbJtBj(pk);
			JSONArray dataList = JSONArray.fromObject(bjxx);
			response.getWriter().print(dataList);
			return null;
		}
		return mapping.findForward("fbglbbgltbxx");
	}

	/**
	 * 
	 * @描述: 获取下一个code
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-1 上午10:29:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward getNextCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pzgzid = request.getParameter("pzgzid");
		String pk = request.getParameter("pk");
		String index = request.getParameter("index");
		FbglBbglService service = new FbglBbglService();
		FbglBbglForm fbf = service.fbglBbglNextCodexx(pk, pzgzid, index);
		response.getWriter().print(JSONObject.fromObject(fbf));
		return null;
	}

	/**
	 * 
	 * @描述: 批量删除
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-1 上午10:30:02
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
	@SystemLog(description="访问学生信息-分班管理-编班管理-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglBbglService service = new FbglBbglService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			Map<String, Object> map = service.delete(values.split(","));
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
}
