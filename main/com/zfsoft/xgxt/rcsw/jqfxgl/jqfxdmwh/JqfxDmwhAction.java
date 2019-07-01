/**
 * @部门:学工产品事业部
 * @日期：2018-4-20 下午03:26:26 
 */  
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxdmwh;

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

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： lgx[工号:1553]
 * @时间： 2018-4-20 下午03:26:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
@SuppressWarnings("unchecked")
public class JqfxDmwhAction  extends SuperAction {
	
	/**
	 * 
	 * @描述:TODO(返校管理代码维护)
	 * @作者：lgx[工号：1553]
	 * @时间： 2018-4-20 下午03:26:26 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward jqfxDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqfxDmwhForm model = (JqfxDmwhForm) form;
		JqfxDmwhService service = new JqfxDmwhService();

		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service
					.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String path = "rcsw_jqfxgl_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("jqfxDmwh");

	}
	
	/**
	 * 
	 * @描述:TODO(增加返校管理代码维护)
	 * @作者：lgx[工号：1553]
	 * @时间： 2018-4-20 下午03:26:26 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward addJqfxDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqfxDmwhForm model = (JqfxDmwhForm) form;
		JqfxDmwhService service = new JqfxDmwhService();
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.saveJqfxgldmwhInfo(model, "add");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}		
		return mapping.findForward("addJqfxDmwh");
	}
	
	/**
	 * 
	 * @描述:TODO(更改返校类别代码维护)
	 * @作者：lgx[工号：1553]
	 * @时间： 2018-4-20 下午03:26:26 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward updateJqfxDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxDmwhService service = new JqfxDmwhService();
		
		String fxdm = request.getParameter("fxdm");
		fxdm = java.net.URLDecoder.decode(fxdm, "utf-8");
		JqfxDmwhForm myForm = (JqfxDmwhForm) form;			
		
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.saveJqfxgldmwhInfo(myForm, "update");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		myForm.setFxdm(fxdm);
		JqfxDmwhForm model = service.getModel(myForm);		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateJqfxDmwh");
	}
	
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @时间： 2018-4-20 下午03:26:26 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward delJqfxDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxDmwhService service = new JqfxDmwhService();	
		String values = request.getParameter("values");
	
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deletFxlbdmwhInfo(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

}
