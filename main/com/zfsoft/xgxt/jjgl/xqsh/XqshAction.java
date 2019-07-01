/**
 * @部门:学工产品事业部
 * @日期：2014-8-26 下午06:07:13 
 */  
package com.zfsoft.xgxt.jjgl.xqsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;

import com.zfsoft.xgxt.jjgl.jjxq.JjxqService;
import com.zfsoft.xgxt.jjgl.zcyh.ZcyhService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-26 下午06:07:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XqshAction extends SuperAction<XqshForm, XqshService> {

	/**
	 *  @属性： PATH 路径
	 */
	private static final String PATH = "jjgl_xqsh.do";
	
	private static final String url = "jjgl_xqsh.do";
	/**
	 * 
	 * @描述:需求审核页面
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-26 下午06:43:32
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
	@SystemAuth(url = url)
	public ActionForward xqshCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xqshCx");
	}
	
	/**
	 * 
	 * @描述:查询家教需求列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-27 上午09:43:49
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
	@SystemAuth(url = url)
	public ActionForward queryXqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XqshForm model = (XqshForm) form;
		
		//查询
		List<HashMap<String,String>> resultList = getService().getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	/**
	 * 
	 * @描述:单个审核
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-27 上午10:16:31
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xqDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqshForm model = (XqshForm) form;
		String xqid = model.getXqid();
		//家教需求信息
		if(StringUtils.isNotBlank(xqid)){
			HashMap<String , String> xqModelMap = new JjxqService().getModelMap(xqid);
			request.setAttribute("xqModelMap", xgxt.utils.String.StringUtils.formatData(xqModelMap));
			//子女信息
			if(xqModelMap != null && StringUtils.isNotBlank(xqModelMap.get("znid"))){
				HashMap<String , String> znxxMap = new ZcyhService().getZnxxMapById(xqModelMap.get("znid"));
				request.setAttribute("znxxMap", znxxMap);
			}
		}
		return mapping.findForward("xqDgsh2th");
	}
	
	/**
	 * 
	 * @描述:审核
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-27 上午11:20:05
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward audit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqshForm model = (XqshForm) form;
		boolean result = false;
		JSONObject message = null;
		result = getService().audit(model);
		message = getJsonMessageByKey(result ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_ERROR );
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述:查看
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-27 上午11:20:05
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
	@SystemAuth(url = url)
	public ActionForward xqshck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqshForm model = (XqshForm) form;
		String xqid = model.getXqid();
		//家教需求信息
		if(StringUtils.isNotBlank(xqid)){
			HashMap<String , String> xqModelMap = new JjxqService().getModelMap(xqid);
			request.setAttribute("xqModelMap", xgxt.utils.String.StringUtils.formatData(xqModelMap));
			//子女信息
			if(xqModelMap != null && StringUtils.isNotBlank(xqModelMap.get("znid"))){
				HashMap<String , String> znxxMap = new ZcyhService().getZnxxMapById(xqModelMap.get("znid"));
				request.setAttribute("znxxMap", znxxMap);
			}
		}
		return mapping.findForward("xqshck");
	}
	
	
	/**
	 * 
	 * @描述:需求审核修改
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-27 上午10:16:31
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xqshxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqshForm model = (XqshForm) form;
		String xqid = model.getXqid();
		
		if(StringUtils.isNotBlank(xqid)){
			HashMap<String , String> xqModelMap = new JjxqService().getModelMap(xqid);
			request.setAttribute("xqModelMap", xgxt.utils.String.StringUtils.formatData(xqModelMap));
		}
		
		return mapping.findForward("xqshxg");
	}
}
