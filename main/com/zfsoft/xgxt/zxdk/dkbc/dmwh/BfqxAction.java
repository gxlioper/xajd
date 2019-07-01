/**
 * @部门:学工产品事业部
 * @日期：2014-11-5 上午09:41:51 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.dmwh;

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
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 还款状态
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-11-5 上午09:41:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BfqxAction extends SuperAction<BfqxModel, BfqxService> {
	
	private static final String url = "zxdk_jcjy_dmwh.do";

	@SystemAuth(url = url)
	public ActionForward bfqxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "zxdk_jcjy_dmwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bfqxList");
	}
	
	@SystemAuth(url = url)
	public ActionForward getBfqxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfqxService service = getService();
		BfqxModel model = (BfqxModel) form;
		
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	/**增加贷款状态*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dmwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("dmwhAdd");
	}
	
	/**修改贷款状态*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dmwhEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfqxService service = getService();
		BfqxModel hkztForm = (BfqxModel) form;
		
		BfqxModel model = service.getModel(hkztForm);
		
		if (model != null){
			BeanUtils.copyProperties(hkztForm, StringUtils.formatData(model));
		}
		
		return mapping.findForward("dmwhEdit");
	}
	
	/**删除贷款状态*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问助学贷款-基层就业-代码维护-删除values:{ids}")
	public ActionForward dmwhDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfqxService service = getService();
		String ids = request.getParameter("ids");
		
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
