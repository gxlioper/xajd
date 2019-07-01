/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午02:00:09 
 */  
package com.zfsoft.xgxt.xpjpy.cpxz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.utils.StringUtil;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优2013-参评小组 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-20 下午02:00:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CpxzAction extends SuperAction {
	
	private static final String url = "pj_cpxz.do";
	
	/**
	 * 
	 * @描述: 排名组查询
	 * @作者：cq [工号：785]
	 * @日期：2013-7-22 上午10:07:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url)
	public ActionForward viewCpxzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpxzModel model = (CpxzModel) form;
		CpxzService service = new CpxzService();
		
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		request.setAttribute("cssz", csszModel);
		String path = "pj_cpxz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewCpxzList");
	}
	
	/**
	 * 
	 * @描述: 自动设置参评组_重新初始化
	 * @作者：cq [工号：785]
	 * @日期：2013-7-28 上午03:45:53
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
	@SystemLog(description="访问评奖评优-综合测评-参评组设置-初始化-CPZCSH：{cpzcsh}")
	public ActionForward zdszCpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpxzService service = new CpxzService();
		CpxzModel model = (CpxzModel) form;
		
		if ("cxjs".equals((model.getType()))) {
			
			String cpzcsh = request.getParameter("cpzcsh");
			
			boolean result = service.initCpxz(getUser(request),cpzcsh);
			String messageKey = result ? MessageKey.SYS_INIT_SUCCESS
					: MessageKey.SYS_INIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		return mapping.findForward("zdszCpz");
	}
	
	
	/**
	 * 
	 * @描述: 自动设置参评组_设置参评组
	 * @作者：cq [工号：785]
	 * @日期：2013-7-28 上午03:45:53
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
	public ActionForward szCpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpxzService service = new CpxzService();
		
		String ids = request.getParameter("ids");
		
		List<HashMap<String, String>> bjInfo = service.getBjInfo(ids);
		request.setAttribute("bjInfo", bjInfo);
		
		return mapping.findForward("szCpz");
	}
	
	
	/**
	 * 
	 * @描述:自动设置参评组_设置参评组_保存
	 * @作者：cq [工号：785]
	 * @日期：2013-8-13 上午09:47:57
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
	@SystemLog(description="访问评奖评优-综合测评-参评组设置-设置-保存BJDM：{bjdm}")
	public ActionForward saveCpxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CpxzService service = new CpxzService();
		CpxzModel model = (CpxzModel) form;
		
		boolean result = service.initCpzsz(model.getBjdm(),model.getPmz());
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null ;
	}

	@SystemAuth(url = "pj_cpzglywh.do")
	public ActionForward viewCpzglyList(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CpxzModel model = (CpxzModel) form;
		CpxzService service = new CpxzService();

		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getCpzglyList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();

		request.setAttribute("cssz", csszModel);
		String path = "pj_cpzglywh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewCpzglyList");
	}

	@SystemAuth(url = "pj_cpzglywh.do")
	public ActionForward fpgly(ActionMapping mapping, ActionForm form,
										HttpServletRequest request, HttpServletResponse response)throws Exception{

		CpxzModel model = (CpxzModel) form;
		CpxzService service = new CpxzService();

		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getGlyList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("cpzdms", model.getCpzdms());
		request.setAttribute("path", "xpj_cpxz.do?method=fpgly");
		request.setAttribute("isShow", model.getCpzdms().split(",").length == 1);
		return mapping.findForward("cpzfpgly");
	}

	public ActionForward saveFp(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		CpxzModel model = (CpxzModel) form;
		CpxzService service = new CpxzService();
		boolean rs = service.saveFp(model);
		String message = rs ? "分配成功！" :"分配失败！";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}

	public ActionForward cancelFp(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		CpxzModel model = (CpxzModel) form;
		CpxzService service = new CpxzService();
		boolean rs = service.cancelfp(model);
		String message = rs ? "取消成功！" :"取消失败！";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
}


