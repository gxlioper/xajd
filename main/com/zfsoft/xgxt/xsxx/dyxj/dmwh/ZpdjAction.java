/**
 * @部门:学工产品事业部
 * @日期：2015-6-18 上午11:13:42 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.dmwh;

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
 * @类功能描述: 自评等级
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-18 上午11:13:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZpdjAction extends SuperAction<ZpdjModel, ZpdjService> {
	
	private static final String url = "xsxx_dyxj_dmwh.do";
	
	/**代码维护列表*/
	@SystemAuth(url = url)
	public ActionForward zpdjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "xsxx_dyxj_dmwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zpdjList");
	}
	
	/**代码维护列表*/
	@SystemAuth(url = url)
	public ActionForward getZpdjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZpdjService service = getService();
		ZpdjModel model = (ZpdjModel) form;
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-德育小结-代码维护-增加DJMC:{djmc}")
	public ActionForward zpdjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("zpdjAdd");
	}
	
	/**代码维护修改*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-德育小结-代码维护-修改DJDM:{djdm},DJMC:{djmc}")
	public ActionForward zpdjEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZpdjService service = getService();
		ZpdjModel myForm = (ZpdjModel) form;
		
		ZpdjModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		
		return mapping.findForward("zpdjEdit");
	}
	
	
	/**代码维护删除*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-德育小结-代码维护-删除PK:{ids}")
	public ActionForward zpdjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		ZpdjService service = getService();
		
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
	
	
	/**按等级名称查询是否存在**/
	public ActionForward getCountByDjmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZpdjModel myForm = (ZpdjModel) form;
		ZpdjService service = getService();
		
		String count = service.getCountByDjmc(myForm.getDjmc());
		response.getWriter().print(count);
		return null;
	}
}
