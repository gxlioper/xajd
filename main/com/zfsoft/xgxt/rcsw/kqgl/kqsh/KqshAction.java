/**
 * @部门:学工产品事业部
 * @日期：2016-10-28 上午11:18:26 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqsh;

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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.rcsw.kqgl.zjsy.ZjsyKqForm;
import com.zfsoft.xgxt.rcsw.kqgl.zjsy.ZjsyKqService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务——考情管理管理模块
 * @类功能描述: 考情审核action
 * @作者： cq [工号:785]
 * @时间： 2016-10-28 上午11:18:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqshAction extends SuperAction<KqshForm, KqshService> {
	
	private KqshService service = new KqshService();
	private static final String url = "rcsw_zjsy_kqsh.do";
	
	/**
	 * 
	 * @描述:获取考情审核list
	 * @作者：cq [工号：785]
	 * @日期：2016-10-28 下午01:48:43
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
	public ActionForward kqshKqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqshForm model = (KqshForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		searchModel.setSearch_tj_yf(new String[]{DateUtils.getMonth()});
		ZjsyKqService zjsyKqService = new ZjsyKqService();
		String dqzc = zjsyKqService.getDqZc();
		if(!StringUtil.isNull(dqzc)){
			searchModel.setSearch_tj_zjsyzc(new String[]{dqzc});
		}
		request.setAttribute("searchTj", searchModel);
		
		String path = "rcsw_zjsy_kqsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("kqshKqList");
	}
	
	/**
	 * 
	 * @描述:考情单个审核
	 * @作者：cq [工号：785]
	 * @日期：2016-10-28 下午04:10:40
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
	public ActionForward kqDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqshForm model = (KqshForm) form;
		ZjsyKqService zjsyKqService = new ZjsyKqService();
		ZjsyKqForm myForm = zjsyKqService.getModel(model.getId());
		
		if (SAVE.equalsIgnoreCase(model.getType())) {
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
			User user = getUser(request);
			// 保存单个审核
			boolean result = service.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		List<HashMap<String,String>> kqinfoList= zjsyKqService.getKqinfo(model.getId());
		request.setAttribute("kqinfoList", kqinfoList);
		request.setAttribute("model", StringUtils.formatData(model));
		model.setShid(request.getParameter("shid"));
		return mapping.findForward("kqDgsh");
		
	}
	
	/**
	 * 
	 * @描述:审核撤销
	 * @作者：cq [工号：785]
	 * @日期：2016-10-29 上午09:58:39
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
	@SystemLog(description="访问日常事务-考情管理-考情审核-撤销id:{id}")
	public ActionForward kqshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqshForm model = (KqshForm) form;
		User user = getUser(request);
		
		String cancelFlg = service.kqshcx(model,user);

		// 审核撤销成功
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:考情批量审核
	 * @作者：cq [工号：785]
	 * @日期：2016-10-29 上午10:54:27
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
	public ActionForward kqPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KqshForm model = (KqshForm) form;
		User user = getUser(request);
		String values = request.getParameter("values");
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePlsh(values, model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		List<HashMap<String, String>> checkShs = service.checkShs(values,model,user);
		request.setAttribute("dqshrs", checkShs.size());
		return mapping.findForward("kqPlsh");
	}
	
	
	/**
	 * 
	 * @描述:考情审核查看
	 * @作者：cq [工号：785]
	 * @日期：2016-10-29 下午01:32:19
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
	public ActionForward kqshCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqshForm model = (KqshForm) form;
		ZjsyKqService zjsyKqService = new ZjsyKqService();
		ZjsyKqForm myForm = zjsyKqService.getModel(model.getId());
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		List<HashMap<String,String>> kqinfoList= zjsyKqService.getKqinfo(model.getId());
		request.setAttribute("kqinfoList", kqinfoList);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("kqshCk");
		
	}

}
