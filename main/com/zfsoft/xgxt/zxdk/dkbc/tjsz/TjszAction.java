/**
 * @部门:学工产品事业部
 * @日期：2016-3-4 上午10:41:09 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.tjsz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xszz.xmwh.tjsz.XmwhTjszViewForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款
 * @类功能描述: 条件设置  
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-3-4 上午10:41:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjszAction extends SuperAction {
	private TjszService service = new TjszService();
	private String messageKey;
	String xmdm = "JCJYDKBC";
	String xmmc = "贷款代偿";
	
	/**
	 * 
	 * @描述: 条件设置页面查看
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:20:05
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
	public ActionForward tjszCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String szzt = service.szztCx(xmdm);
		String path = "zxdk_jcjy_tjsz.do";
		request.setAttribute("path", path);
		request.setAttribute("szzt", szzt);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("xmmc", xmmc);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tjszCk");
	}
	
	/**
	 * 
	 * @描述: 条件设置子页面查询
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:20:05
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
	public ActionForward tjszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("xmmc", xmmc);	
		return mapping.findForward("tjszCx");
	}
	
	/**
	 * 
	 * @描述: 所有条件列表
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:20:05
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
	public ActionForward xmwhTjszSy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		XmwhTjszViewForm viewForm = service.getAll(xmdm);
		JSONObject json = JSONObject.fromBean(viewForm);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:20:05
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
	public ActionForward xmwhTjszSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		String tjdm = request.getParameter("tjdm");

		if (!StringUtil.isNull(xmdm) && !StringUtil.isNull(tjdm)) {
			service.delDeal(xmdm,tjdm);//删除所有的关联表
		} 
		return null;

	}
	
	/**
	 * 
	 * @描述: 条件设置依赖学年
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:20:05
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
	public ActionForward xmwhTjszXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjszForm myForm = (TjszForm) form;
		if (QUERY.equals(myForm.getType())) {
			String xmdm = request.getParameter("xmdm");
			XmwhTjszViewForm viewForm = service.getXn(xmdm);
			JSONObject json = JSONObject.fromBean(viewForm);
			response.getWriter().print(json);
			return null;
		}
		String xnVal = request.getParameter("xnVal");
		request.setAttribute("xnVal", xnVal);
		String zqlx = request.getParameter("zqlx");
		request.setAttribute("zqlx", zqlx);
		request.setAttribute("knszq", MessageUtil.getText("xszz.knsrd.sqzq"));
		String path = "zxdk_jcjy_tjsz.do";
		request.setAttribute("path", path);
		String tjdm = request.getParameter("tjdm");
		request.setAttribute("tjdm", tjdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tjszXn");
		
	}
	
	/**
	 * 
	 * @描述: 条件设置条件弹出层
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:20:05
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
	public ActionForward xmwhTjszTjzDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("tjszTjzDiv");
	}
	
	/**
	 * 
	 * @描述: 条件保存
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-7 下午03:20:05
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
	public ActionForward xmwhTjszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjszForm myForm = (TjszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String xmdm = request.getParameter("xmdm");
			String json = request.getParameter("json");
			boolean result = false;
			try {
				List<TjszForm> list = JsonUtil.jsonToList(json,
						TjszForm.class);
				if (list != null && list.size() > 0) {
					result = service.saveOrUpdate(xmdm, list);
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
				}else{
					messageKey = MessageKey.SYS_SAVE_SUCCESS;
				}
			} catch (Exception e) {
				e.printStackTrace();// //异常打印。。。。。////////////////
				messageKey = MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonResult(messageKey, result));
			return null;
		}
		
		TjszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		return null;
		
	}
	
}
