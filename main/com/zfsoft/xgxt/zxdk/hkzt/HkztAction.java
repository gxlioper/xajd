/**
 * @部门:学工产品事业部
 * @日期：2014-11-5 上午09:41:51 
 */  
package com.zfsoft.xgxt.zxdk.hkzt;

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

public class HkztAction extends SuperAction<HkztModel, HkztService> {

	private static final String url = "zxdk_tqhk_hkzt.do";
	
	/**贷款状态查询*/
	@SystemAuth(url = url)
	public ActionForward hkztList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "zxdk_tqhk_hkzt.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hkztList");
	}
	
	/**ajax加载贷款状态*/
	@SystemAuth(url = url)
	public ActionForward getHkztList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HkztService service = getService();
		HkztModel model = (HkztModel) form;
		
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
		
		HkztService service = getService();
		HkztModel hkztForm = (HkztModel) form;
		
		HkztModel model = service.getModel(hkztForm);
		
		if (model != null){
			BeanUtils.copyProperties(hkztForm, model);
		}
		
		return mapping.findForward("dmwhEdit");
	}
	
	/**删除贷款状态*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问助学贷款-提前还款-代码维护-删除values:{ids}")
	public ActionForward dmwhDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HkztService service = getService();
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
