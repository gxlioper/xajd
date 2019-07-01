/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:49:48 
 */  
package com.zfsoft.xgxt.xsxx.djjd.dmwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @类功能描述:代码维护
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:49:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JddjDmwhAction extends SuperAction<JddjDmwhModel, JddjDmwhService> {
	
	private static final String url = "ntgm_jjdj_dmwh.do";

	/**代码维护列表*/
	@SystemAuth(url = url)
	public ActionForward dmwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "ntgm_jjdj_dmwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dmwhList");
	}
	
	/**代码维护列表*/
	@SystemAuth(url = url)
	public ActionForward getDmwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JddjDmwhService service = getService();
		JddjDmwhModel model = (JddjDmwhModel) form;
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dmwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("dmwhAdd");
	}
	
	/**代码维护修改*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dmwhEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JddjDmwhService service = getService();
		JddjDmwhModel myForm = (JddjDmwhModel) form;
		
		JddjDmwhModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		
		return mapping.findForward("dmwhEdit");
	}
	
	
	/**代码维护删除*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-技术等级鉴定（南通工贸）-代码维护-删除PK:{ids}")
	public ActionForward dmwhDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		JddjDmwhService service = getService();
		
		if (!StringUtil.isNull(ids)){
			int num = service.runDelete(ids.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.XYJD_DELETE_DMEXIST,"代码");
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
}
