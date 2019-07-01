package xsgzgl.gyjc.jcsd;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;

public class JcsdAction extends SuperAction<JcsdForm, JcsdService> {
	private JcsdService service = new  JcsdService();
	private static final String url = "xg_gyjc_jcsd.do";
	private static final String FWQXTSY = "此菜单只供校级用户和院级用户使用！";
	/**
	 * 
	 * @描述: 基础设定主查询页面
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-7 下午04:36:08
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
	
	public ActionForward jcsdList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcsdForm model = (JcsdForm) form;
		User user = getUser(request);
		if(!"xx".equals(user.getUserStatus()) && ! "xy".equals(user.getUserStatus())){
			request.setAttribute("message",FWQXTSY);
			return mapping.findForward("error");
		}
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		//将用户身份置回页面
		request.setAttribute("userType", user.getUserStatus());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("search");
	}
	
	/**
	 * 
	 * @描述: 人员分配List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-10 上午11:17:12
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
	public ActionForward getRyfpList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcsdForm model = (JcsdForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getRyfpList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xg_gyjc_ryfp.do");
		//将用户身份,学院代码，检查类型置回页面
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("xydm", model.getXydm());
		request.setAttribute("jjlx", model.getJjlx());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("searchRyfp");
	}
	
	/**
	 * 
	 * @描述:保存人员分配
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-10 下午02:18:29
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
	@SystemLog(description = "访问文明寝室-基础设定-基础设定-人员分配保存")
	public ActionForward saveRyFp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcsdForm model = (JcsdForm)form;
		User user = getUser(request);
		JcsdService jcsdService = TransactionControlProxy.newProxy(new JcsdService());
		boolean rs = jcsdService.saveRyFp(model,user.getUserStatus());
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 取消分配
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-10 下午02:31:53
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
	@SystemLog(description = "访问文明寝室-基础设定-基础设定-人员分配撤销")
	public ActionForward cancelRyfp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcsdForm model = (JcsdForm)form;
		User user = getUser(request);
		boolean rs = service.cancelRyfp(model, user.getUserStatus());
		String messageKey = rs ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_OPERATE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
