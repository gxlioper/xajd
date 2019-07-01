/**
 * @部门:学工产品事业部
 * @日期：2014-5-26 上午11:57:01 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.zbrcgl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

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
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xsxx.jcsjwh.JcsjForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-26 上午11:57:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZbrcAction extends SuperAction {
	/**
	 *  @属性： PATH 路径
	 */
	public static final String PATH = "xljk_xlwygl_zbrcgl.do";
	
	public static final String url = "xljk_xlwygl_zbrcgl.do";
	
	private ZbrcService service = new ZbrcService();
	/**
	 * 
	 * @描述:页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url)
	public ActionForward cx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述:检索数据列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:43:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url)
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZbrcForm model = (ZbrcForm) form;
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//查询
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZbrcForm model = (ZbrcForm) form;
		//学年 学期
		model.setXq(Base.currXq);
		model.setXn(Base.currXn);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("path", URLEncoder.encode("xljk_xlwygl_zbrcglwh.do?method=xz" , "gbk"));
		return mapping.findForward("xz");
	}
	
	/**
	 * 
	 * @描述:新增保存
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-24 下午05:19:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xzAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZbrcForm model = (ZbrcForm) form;
		if(service.isHaveRecordForjg(model)) {
			String messageKey = "该学年学期周次已存在！";
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		boolean isSuccess = service.runInsert(model);
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述:删除
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-6 下午03:52:58
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
	public ActionForward delAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String zbids = request.getParameter("zbids"); 
		
		if(zbids == null)
			zbids = "";
		
		int isSuccess = service.runDelete(zbids.split(","));
		
		String message = isSuccess >= 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * @描述: 未/已上报班级页面dispatcher
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-24 上午11:17:38
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward cxSbbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZbrcForm model = (ZbrcForm) form;
		String sbbjlx = request.getParameter("sbbjlx");
		String zbid = model.getZbid();
		request.setAttribute("sbbjlx", sbbjlx);
		request.setAttribute("zbid", zbid);
		String path = PATH + "?method=cxSbbj";
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx_sbbj");
	}
	
	/**
	 * @描述: 未/已上报班级 检索数据列表
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-24 上午11:17:38
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward cxSbbjQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sbbjlx = request.getParameter("sbbjlx");
		User user = getUser(request);
		ZbrcForm model = (ZbrcForm) form;
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath(PATH + "?method=cxSbbj");
		model.setSearchModel(searchModel);
		//查询
		List<HashMap<String,String>> resultList = service.cxSbbj(model, user, sbbjlx);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:页面dispatcher
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZbrcForm model = (ZbrcForm) form;
		String zbid = model.getZbid();
		ZbrcForm data = service.getModel(zbid);
		if(data != null){
			BeanUtils.copyProperties(model, data);
		}
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("path", URLEncoder.encode("xljk_xlwygl_zbrcglwh.do?method=xg" , "gbk"));
		return mapping.findForward("xg");
	}
	
	/**
	 * 
	 * @描述:修改保存
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:34:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZbrcForm model = (ZbrcForm) form;
		if(service.isHaveRecordForjgU(model)) {
			String messageKey = "该学年学期周次已存在！";
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		boolean isSuccess = service.runUpdate(model);
		String message = isSuccess ? MessageUtil.getText(
				MessageKey.SYS_SAVE_SUCCESS) : MessageUtil
				.getText(MessageKey.SYS_SAVE_FAIL);	
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
}
