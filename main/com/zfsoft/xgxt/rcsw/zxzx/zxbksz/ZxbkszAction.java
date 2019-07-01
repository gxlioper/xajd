package com.zfsoft.xgxt.rcsw.zxzx.zxbksz;

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
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class ZxbkszAction extends SuperAction {
	
	private static final String url = "rcsw_zxzx_zxbksz.do";

	/**
	 * 查询咨询板块
	 */
	@SystemAuth(url = url)
	public ActionForward zxbkszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxbkszForm model = (ZxbkszForm) form;
		ZxbkszService service = new ZxbkszService();
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
		String path = "rcsw_zxzx_zxbksz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zxbkszManage");
	}
	/**
	 * 增加咨询板块
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在线咨询-咨询版块设置-增加BKMC:{bkmc},XSSX:{xssx}")
	public ActionForward addZxbksz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxbkszForm model = (ZxbkszForm) form;
		ZxbkszService service = new ZxbkszService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean flag = service.checkExistSave(model, user);
			if (flag) {
				String bkid = UniqID.getInstance().getUniqIDHash();
				model.setBkid(bkid);
				boolean result = service.insertZxbksz(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				String message = MessageUtil.getText(MessageKey.RCSW_ZXZX_ZXBKSZ_EXISTS) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		return mapping.findForward("addZxbksz");
	}
	/**
	 * 修改咨询板块
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在线咨询-咨询版块设置-修改BKID:{bkid},BKMC:{bkmc},XSSX:{xssx}")
	public ActionForward updateZxbksz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxbkszForm model = (ZxbkszForm) form;
		ZxbkszService service = new ZxbkszService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean flag = service.checkExistSave(model, user);
			if (flag) {
				boolean result = service.updateZxbksz(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				String message = MessageUtil.getText(MessageKey.RCSW_ZXZX_ZXBKSZ_EXISTS) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		ZxbkszForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs", model);
		return mapping.findForward("updateZxbksz");
	}
	/**
	 * 删除咨询板块
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在线咨询-咨询版块设置-删除VALUES:{values}")
	public ActionForward delZxbksz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxbkszForm model = (ZxbkszForm) form;
		ZxbkszService service = new ZxbkszService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String checkZxbkszDel=service.checkZxbkszDel(values);
			if(checkZxbkszDel != null && !checkZxbkszDel.trim().equals("")){
				String message = MessageUtil.getText(MessageKey.RCSW_ZXZX_ZXBKSZ_DEL, checkZxbkszDel);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 启用/停用
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在线咨询-咨询版块设置-启用/停用VALUES:{values}")
	public ActionForward sfqyZxbksz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZxbkszService service = new ZxbkszService();
		String values = request.getParameter("values");
		String sfqy = request.getParameter("sfqy");
		boolean sfqy_y = "1".equals(sfqy);
		String SYS_SFQY_FAIL = MessageKey.SYS_SFQY_N_FAIL;
		String SYS_SFQY_NUM = MessageKey.SYS_SFQY_N_NUM;
		String SYS_SFQY_NULL = MessageKey.SYS_SFQY_N_NULL;
		if(sfqy_y){
			SYS_SFQY_FAIL = MessageKey.SYS_SFQY_Y_FAIL;
			SYS_SFQY_NUM = MessageKey.SYS_SFQY_Y_NUM;
			SYS_SFQY_NULL = MessageKey.SYS_SFQY_Y_NULL;
		}
		if (!StringUtil.isNull(values)){
			boolean result = true;
			String[] ids = values.split(",");
			int okNum = 0;
			ZxbkszForm myForm = new ZxbkszForm();
			for (int i = 0; i < ids.length; i++) {
				myForm.setBkid(ids[i]);
				myForm.setSfqy(sfqy);
				if(result){
					result = service.updateZxbksz(myForm);
					okNum++;
				}
			}
			String message = result ? MessageUtil.getText(SYS_SFQY_NUM, okNum) : MessageUtil.getText(SYS_SFQY_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(SYS_SFQY_NULL);
		}
		return null;
	}
	/**
	 * 查看咨询板块
	 */
	@SystemAuth(url = url)
	public ActionForward viewZxbksz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxbkszForm model = (ZxbkszForm) form;
		ZxbkszService service = new ZxbkszService();
		User user = getUser(request);
		model = service.getModel(model);
		request.setAttribute("rs", StringUtils.formatData(model));
		return mapping.findForward("viewZxbksz");
	}
	
}
