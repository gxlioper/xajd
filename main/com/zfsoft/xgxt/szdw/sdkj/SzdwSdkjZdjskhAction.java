/**
 * @部门:学工产品事业部
 * @日期：2013-5-16 上午09:54:23 
 */  
package com.zfsoft.xgxt.szdw.sdkj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：cmj 
 * @时间： 2013-5-16 上午09:54:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SzdwSdkjZdjskhAction extends SuperAction {
	
	private static final String url = "szdw_sdkj_zdjskh.do";
	
	/**
	 * 查看考核统计
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cmj
	 * @日期：2013-5-23 上午10:12:00
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
	public ActionForward zdjskh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdlskhForm zdlskhForm=(ZdlskhForm)form;
		SzdwSdkjZdjskhService service=new SzdwSdkjZdjskhService();
		request.setAttribute("nfList", service.getndList());
		request.setAttribute("yfList", Base.getYfList());
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("nf", Base.currNd);
		request.setAttribute("map", map);
		request.setAttribute("realTable", "zdlskhb");
		if (QUERY.equals(zdlskhForm.getType())){
			List<HashMap<String,String>> resultList = service.getPageList(zdlskhForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "szdw_sdkj_zdjskh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("jskhlr");
	}
	/**
	 * 查看月份教师录入详细记录
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cmj
	 * @日期：2013-5-23 上午10:12:22
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
	public ActionForward ckxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZdlskhForm zdlskhForm=(ZdlskhForm)form;
		SzdwSdkjZdjskhService service=new SzdwSdkjZdjskhService();
		HashMap<String, String> map=new HashMap<String, String>();
		
		request.setAttribute("nfList", service.getndList());
		request.setAttribute("yfList", Base.getYfList());
		
		request.setAttribute("tableName", "zdlskhb");
		
		String nf=request.getParameter("nf");
		String yf=request.getParameter("yf");
		if(!"".equalsIgnoreCase(nf)){
			map.put("nf", nf);
		}
		if(!"".equalsIgnoreCase(yf)){
			map.put("yf", yf);
		}
		request.setAttribute("map", map);
		if (QUERY.equals(zdlskhForm.getType())){
			List<HashMap<String,String>> resultList = service.getPageListxx(zdlskhForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "szdw_sdkj_zdjskh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("jskhck");
	}
	/**
	 * 删除教师考核记录
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cmj
	 * @日期：2013-5-23 上午10:13:02
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
	public ActionForward delKhjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzdwSdkjZdjskhService service=new SzdwSdkjZdjskhService();
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	

}
