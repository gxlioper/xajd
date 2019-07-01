/**
 * @部门:学工产品事业部
 * @日期：2016-2-19 上午08:54:43 
 */
package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxdmwh;


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
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理假期返校代码维护管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-11 上午09:15:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
@SuppressWarnings("unchecked")
public class JqfxdmwhAction extends SuperAction {
	
	/**
	 * 
	 * @描述:TODO(返校管理代码维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-11 上午09:16:43
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
	public ActionForward jlkjfxglDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqfxdmwhForm model = (JqfxdmwhForm) form;
		JqfxdmwhService service = new JqfxdmwhService();

		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service
					.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String path = "jlkjxyfxgldmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("jlkjfxglDmwh");

	}
	
	/**
	 * 
	 * @描述:TODO(增加返校管理代码维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-2-24 上午11:28:01
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
	public ActionForward addFxglDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqfxdmwhForm model = (JqfxdmwhForm) form;
		JqfxdmwhService service = new JqfxdmwhService();
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.saveJqfxgldmwhInfo(model, "add");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}		
		return mapping.findForward("addFxglDmwh");
	}
	
	/**
	 * 
	 * @描述:TODO(更改返校类别代码维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-2-24 下午12:44:58
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
	public ActionForward updateFxglDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxdmwhService service = new JqfxdmwhService();
		
		String fxdm = request.getParameter("fxdm");
		fxdm = java.net.URLDecoder.decode(fxdm, "utf-8");
		JqfxdmwhForm myForm = (JqfxdmwhForm) form;			
		
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.saveJqfxgldmwhInfo(myForm, "update");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		myForm.setFxdm(fxdm);
		JqfxdmwhForm model = service.getModel(myForm);		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateFxglDmwh");
	}
	
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-16 上午10:58:51
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
	public ActionForward delFxglDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxdmwhService service = new JqfxdmwhService();	
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
