package com.zfsoft.xgxt.rcsw.zxzx.cjwtsz;

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

public class CjwtszAction extends SuperAction {

	private static final String url = "rcsw_zxzx_cjwtsz.do";
	
	/**
	 * 查询常见问题
	 */
	@SystemAuth(url = url)
	public ActionForward cjwtszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CjwtszForm model = (CjwtszForm) form;
		CjwtszService service = new CjwtszService();
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		if("stu".equalsIgnoreCase(userStatus)){
			String msg = "该模块不允许学生访问，请确认！";
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
		String path = "rcsw_zxzx_cjwtsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cjwtszManage");
	}
	/**
	 * 增加常见问题
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在线咨询-常见问题设置-增加BKID:{bkid},SFZD:{sfzd},ZXZT:{zxzt},ZXNR:{zxnr},HFNR:{hfnr}")
	public ActionForward addCjwtsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CjwtszForm model = (CjwtszForm) form;
		CjwtszService service = new CjwtszService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			model.setSfcjwt("1");
			model.setZxr(user.getUserName());
			model.setZxsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			String zxid = UniqID.getInstance().getUniqIDHash();
			model.setZxid(zxid);
			boolean result = service.insertCjwtsz(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		ZxbkszService zxbkszService = new ZxbkszService();
		List<HashMap<String, String>> zxbkszList = zxbkszService.getAllOkZxbkszList();
		request.setAttribute("zxbkszList", zxbkszList);
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//是否list
		request.setAttribute("isnotList", isnotList);
		return mapping.findForward("addCjwtsz");
	}
	/**
	 * 修改常见问题
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在线咨询-常见问题设置-修改ZXID:{zxid},BKID:{bkid},SFZD:{sfzd},ZXZT:{zxzt},ZXNR:{zxnr},HFNR:{hfnr}")
	public ActionForward updateCjwtsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CjwtszForm model = (CjwtszForm) form;
		CjwtszService service = new CjwtszService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			model.setZxr(user.getUserName());
			model.setZxsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			boolean result = service.updateCjwtsz(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		CjwtszForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs", model);
		ZxbkszService zxbkszService = new ZxbkszService();
		List<HashMap<String, String>> zxbkszList = zxbkszService.getAllOkZxbkszList();
		request.setAttribute("zxbkszList", zxbkszList);
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//是否list
		request.setAttribute("isnotList", isnotList);
		return mapping.findForward("updateCjwtsz");
	}
	/**
	 * 删除常见问题
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在线咨询-常见问题设置-删除VALUES:{values}")
	public ActionForward delCjwtsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CjwtszForm model = (CjwtszForm) form;
		CjwtszService service = new CjwtszService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			boolean result = service.deleteCjwtsz(values);
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,values.split(",").length) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 查看常见问题
	 */
	@SystemAuth(url = url)
	public ActionForward viewCjwtsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CjwtszForm model = (CjwtszForm) form;
		CjwtszService service = new CjwtszService();
		User user = getUser(request);
		model = service.getModel(model);
		request.setAttribute("rs", StringUtils.formatData(model));
		request.setAttribute("tab", request.getParameter("tab"));
		return mapping.findForward("viewCjwtsz");
	}
	
}
