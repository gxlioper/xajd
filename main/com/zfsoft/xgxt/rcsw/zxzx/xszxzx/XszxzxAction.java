package com.zfsoft.xgxt.rcsw.zxzx.xszxzx;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.zxzx.zxbksz.ZxbkszService;

public class XszxzxAction extends SuperAction {
	
	private static final String url = "rcsw_zxzx_xszxzx.do";

	/**
	 * 查询在线咨询
	 */
	@SystemAuth(url = url)
	public ActionForward xszxzxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszxzxForm model = (XszxzxForm) form;
		XszxzxService service = new XszxzxService();
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		if(!"stu".equalsIgnoreCase(userStatus)){
			String msg = "该模块只允许学生访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_zxzx_xszxzx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		ZxbkszService zxbkszService = new ZxbkszService();
		List<HashMap<String, String>> zxbkszList = zxbkszService.getAllOkZxbkszList();
		request.setAttribute("zxbkszList", zxbkszList);
		return mapping.findForward("xszxzxManage");
	}
	/**
	 * 增加在线咨询
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在线咨询-在线咨询-增加")
	public ActionForward addXszxzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszxzxForm model = (XszxzxForm) form;
		XszxzxService service = new XszxzxService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			model.setSfcjwt("0");
			model.setSfzd("0");
			model.setZxr(user.getUserName());
			model.setZxsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			String zxid = UniqID.getInstance().getUniqIDHash();
			model.setZxid(zxid);
			boolean result = service.insertXszxzx(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		ZxbkszService zxbkszService = new ZxbkszService();
		List<HashMap<String, String>> zxbkszList = zxbkszService.getAllOkZxbkszList();
		request.setAttribute("zxbkszList", zxbkszList);
		return mapping.findForward("addXszxzx");
	}
	/**
	 * 修改在线咨询
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在线咨询-在线咨询-修改ZXID:{zxid}")
	public ActionForward updateXszxzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszxzxForm model = (XszxzxForm) form;
		XszxzxService service = new XszxzxService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			model.setZxr(user.getUserName());
			model.setZxsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			boolean result = service.updateXszxzx(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XszxzxForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs", model);
		ZxbkszService zxbkszService = new ZxbkszService();
		List<HashMap<String, String>> zxbkszList = zxbkszService.getAllOkZxbkszList();
		request.setAttribute("zxbkszList", zxbkszList);
		return mapping.findForward("updateXszxzx");
	}
	/**
	 * 删除在线咨询
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在线咨询-在线咨询-删除VALUES:{values}")
	public ActionForward delXszxzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszxzxForm model = (XszxzxForm) form;
		XszxzxService service = new XszxzxService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			boolean result = service.deleteXszxzx(values);
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,values.split(",").length) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 查看在线咨询
	 */
	@SystemAuth(url = url)
	public ActionForward viewXszxzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszxzxForm model = (XszxzxForm) form;
		XszxzxService service = new XszxzxService();
		User user = getUser(request);
		model = service.getModel(model);
		request.setAttribute("rs", StringUtils.formatData(model));
		return mapping.findForward("viewXszxzx");
	}
	
}
