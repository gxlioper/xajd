/**
 * @部门:学工产品事业部
 * @日期：2018年5月22日 上午10:23:23 
 */  
package xsgzgl.gyjc.jcsd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;

import net.sf.json.JSONArray;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018年5月22日 上午10:23:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxxmAction extends SuperAction<CxxmForm, CxxmService> {
	private CxxmService service = new  CxxmService();
	private static final String url = "xg_gyjc_cxxmsz.do";
	
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
	
	public  ActionForward  getCxxmList(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		CxxmForm model = (CxxmForm) form;
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
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		return mapping.findForward("add");
	}
	

	//保存
	public ActionForward saveCxxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		CxxmForm model = (CxxmForm) form;
		System.out.println(model.getDm());
		boolean rs = service.saveCxxm(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//删除（未做完 先判断是否还在使用）
	public ActionForward delCxxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		CxxmForm model = (CxxmForm) form;
		CxxmService tanService = TransactionControlProxy.newProxy(new CxxmService());
		boolean rs = tanService.delRs(model.getDms());
		String messageKey = rs ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_SUCCESS;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
