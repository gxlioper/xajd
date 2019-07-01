/**
 * @部门:学工产品事业部
 * @日期：2014年6月12日 上午11:02:38 
 */
package com.zfsoft.xgxt.gygl.ssyd.plyd;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理模块
 * @类功能描述: 批量宿舍异动 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014年6月12日 上午11:02:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PlydAction extends SuperAction<PlydModel, PlydService> {

	private static final String url = "gygl_plyd.do";
	
	/**
	 * 
	 * @描述: 批量宿舍异动
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月12日 下午1:53:09
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
	public ActionForward plydList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		request.setAttribute("path", "gygl_plyd.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("plydList");
	}
	
	
	/**
	 * 
	 * @描述: 已入住列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月12日 下午2:44:17
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
	public ActionForward getYrzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PlydModel t = (PlydModel) form;
		User user = getUser(request);
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("gygl_plyd.do");
		t.setSearchModel(searchModel);
		
		List<HashMap<String,String>> yrzList = getService().getYrzPageList(t, user);
		JSONArray dataList = JSONArray.fromObject(yrzList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 待调整列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月12日 下午2:44:36
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
	public ActionForward getDtzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PlydModel t = (PlydModel) form;
		User user = getUser(request);
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("gygl_plyd.do");
		t.setSearchModel(searchModel);
		
		List<HashMap<String,String>> dtzList = getService().getDtzPageList(t, user);
		JSONArray dataList = JSONArray.fromObject(dtzList);
		response.getWriter().print(dataList);
		
		return null;
		
	}
	
	
	/**
	 * 
	 * @描述: 确认调整列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月12日 下午2:44:50
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
	public ActionForward getQrtzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PlydModel t = (PlydModel) form;
		User user = getUser(request);
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("gygl_plyd.do");
		t.setSearchModel(searchModel);
		
		List<HashMap<String,String>> qrtzList = getService().getQrtzList(t, user);
		JSONArray dataList = JSONArray.fromObject(qrtzList);
		response.getWriter().print(dataList);
		
		return null;
		
	}
	
	
	/**
	 * 
	 * @描述: 设置待
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 上午11:19:35
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
	@SystemLog(description="访问公寓管理-宿舍异动-批量宿舍异动-设置为待调整PK:{ids}")
	public ActionForward szDtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String ids = request.getParameter("ids");
		
		boolean result = getService().szDtz(ids);
		response.getWriter().print(result);
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述: 取消待调整
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 下午01:43:10
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
	@SystemLog(description="访问公寓管理-宿舍异动-批量宿舍异动-取消待调整PK:{ids}")
	public ActionForward qxDtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String ids = request.getParameter("ids");
		
		boolean result = getService().qxDtz(ids);
		response.getWriter().print(result);
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 取消调整
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 下午01:43:10
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
	@SystemLog(description="访问公寓管理-宿舍异动-批量宿舍异动-取消调整PK:{ids}")
	public ActionForward qxtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String ids = request.getParameter("ids");
		
		boolean result = getService().qxtz(ids);
		response.getWriter().print(result);
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述: 调整入住
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-17 下午02:07:39
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
	@SystemLog(description="访问公寓管理-宿舍异动-批量宿舍异动-调整入住PK:{ids}")
	public ActionForward tzrz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String ids = request.getParameter("ids");
		PlydService service = getService();
		
		List<HashMap<String,String>> xhList = service.getTzxsList(ids);
		
		if (xhList != null && !xhList.isEmpty()){
			HashMap<String,Object> fyxx = service.getCwxxList(xhList.get(0).get("xb"));
			request.setAttribute("fyxx", fyxx);
		}
		
		request.setAttribute("xhList", xhList);
		return mapping.findForward("tzrz");
	}
	
	
	
	/**
	 * 
	 * @描述: 保存入住
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-19 下午02:55:12
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
	@SystemLog(description="访问公寓管理-宿舍异动-批量宿舍异动-保存入住PK:{rzxx}")
	public ActionForward saveXsrz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String rzxx = request.getParameter("rzxx");
		boolean result = getService().saveRzxx(rzxx);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述: 确认提交 
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-6-24 下午02:26:49
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
	@SystemLog(description="访问公寓管理-宿舍异动-批量宿舍异动-确认提交")
	public ActionForward qrtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PlydService service = getService();
		int bktj = service.getCountByBktj();
		
		if (bktj > 0){
			response.getWriter().print(getJsonMessageByKey(MessageKey.GYGL_PLYD_BKTJ));
		} else {
			boolean result = service.qrtz();
			String messageKey = result ? MessageKey.GYGL_PLYD_QRTZ_SUCCESS : MessageKey.GYGL_PLYD_QRTZ_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}
		
		return null;
	}
}
