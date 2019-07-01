package xsgzgl.gyjc.jcsd;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gyjc.ccjgcx.CcjgForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;

public class PfbzAction extends SuperAction<PfbzForm, PfbzService> {
	private PfbzService service = new  PfbzService();
	private static final String url = "xg_gyjc_jcsd.do";
	/**
	 * 
	 * @描述: 查询评分标准
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-11 上午11:55:56
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
	public  ActionForward  getPfbzList(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		PfbzForm model = (PfbzForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//将用户身份置回页面
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("jjlx", model.getJjlx());
		request.setAttribute("xydm",model.getXydm());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("search");
	}
	
	/**
	 * 
	 * @描述:增加页面跳转
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-12 上午08:59:19
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
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		PfbzForm model = (PfbzForm) form;
		request.setAttribute("xmList", service.getXmSelectList(model));
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @描述:修改页面跳转[增加，修改]
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-12 上午08:59:19
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
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		PfbzForm model = (PfbzForm) form;
		PfbzForm pfbz = service.getPfbzModel(model.getGuid());
		BeanUtils.copyProperties(model, pfbz);
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @描述: 保存评分标准
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-12 上午10:08:40
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
	@SystemLog(description = "访问文明寝室-基础设定-基础设定-评分标准保存")
	public ActionForward savePfbz(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		PfbzForm model = (PfbzForm) form;
		boolean rs = service.savePfbz(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除评分标准
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-12 上午10:21:47
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
	@SystemLog(description = "访问文明寝室-基础设定-基础设定-删除评分标准")
	public ActionForward delPfbz(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		PfbzForm model = (PfbzForm) form;
		PfbzService tanService = TransactionControlProxy.newProxy(new PfbzService());
		boolean rs = tanService.delRs(model.getGuids(), model.getFjids());
		String messageKey = rs ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_SUCCESS;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:获取评分标准Ajax
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-4-18 下午04:44:04
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
	
	public ActionForward getPfbzListAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfbzForm model = (PfbzForm) form;
		PfbzService service =  new PfbzService();
		List<HashMap<String,String>> pfbzList = service.getPfbzListAjax(model.getFjid());
		JSONArray dataList = JSONArray.fromObject(pfbzList);
		response.getWriter().print(dataList);
		return null;
	}
}
