/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.rcsw.hczd;

import java.net.URLDecoder;
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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/**
 * @className	： HczdAction
 * @description	：火车站点action(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2017-11-22 下午04:46:00
 * @version 	V1.0 
 */

public class HczdAction extends SuperAction<HczdForm, HczdService>{
	
	private static final String url = "rcsw_hczdgl_hczdwh.do";
	
	/**
	 * @description	： 火车站点列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-22 下午04:54:48
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward hczdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HczdForm myForm = (HczdForm) form;
		HczdService service = new HczdService();
		
		if(QUERY.equals(myForm.getType())){		
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_hczdgl_hczdwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hczdList");
	}
	
	/**
	 * @description	：火车站点增加
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-22 下午04:59:16
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward hczdAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HczdForm myForm = (HczdForm) form;
		HczdService service = new HczdService();
		if("save".equalsIgnoreCase(myForm.getType())){
			if(service.isExist(myForm)){
				String messageKey = MessageKey.XG_HCZD_REPEAT;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
			}
			boolean result = service.runInsert(myForm);
			String messageKey=result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("hczdAdd");
	}
	
	/**
	 * @description	： 火车站点更新
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-22 下午05:09:51
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward hczdUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HczdForm myForm = (HczdForm) form;
		HczdService service = new HczdService();
		String zdmc = URLDecoder.decode(myForm.getZdmc(), "UTF-8");
		myForm.setZdmc(zdmc);
		HczdForm updateForm = service.getModel(myForm);
		if("save".equalsIgnoreCase(myForm.getType())){
			if(service.isExist(myForm)){
				String messageKey = MessageKey.XG_HCZD_REPEAT;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
			}
			boolean result = service.runUpdate(myForm);
			String messageKey=result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(updateForm));
		myForm.setOriZdmc(myForm.getZdmc());
		return mapping.findForward("hczdUpdate");
	}
	
	/**
	 * @description	： 删除火车站点
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-22 下午05:16:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward hczdDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HczdService service = new HczdService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//验证是否已使用		
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
					: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}			
		return null;
	}
}
