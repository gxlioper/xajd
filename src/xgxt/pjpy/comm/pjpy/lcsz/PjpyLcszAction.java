package xgxt.pjpy.comm.pjpy.lcsz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyInit;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.basic.BasicService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_基本设置_流程设置_action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyLcszAction extends BasicAction {

	/**
	 * 评奖评优_基本设置_评奖流程设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjlcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyLcszForm myForm = (PjpyLcszForm) form;
		PjpyLcszService service = new PjpyLcszService();
		PjpyInit init = new PjpyInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getPjlcszRForm(rForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		// =================== end ===================
		
		// 删除操作
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, "XG_PJPY_LCSZB");
		}
		
		// =================== 初始化列表值 ===========
		service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		request.setAttribute("rs", service.getLcsz());
		request.setAttribute("topTr", new BasicService().getTopTr("XG_PJPY_LCSZB", 
									new String[]{"lcdj", "lcmc", "kssj", "jssj"}));
		// =================== end ===================

		return mapping.findForward("pjlcsz");
	}

	/**
	 * 评奖评优_基本设置_评奖流程设置(维护)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjlcszOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyLcszForm myForm = (PjpyLcszForm) form;
		PjpyLcszService service = new PjpyLcszService();
		PjpyInit init = new PjpyInit();
		User user = getUser(request);// 用户对象
		
		String pkValue = request.getParameter("pkValue");

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getPjlcszRForm(rForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		// =================== end ===================

		// ============= 执行保存操作 ============
		if ("save".equalsIgnoreCase(doType)) {

			boolean flag = service.savePjpyLcsz(myForm, rForm, user, request);
			String message = null;

			if(flag){
				pkValue = myForm.getLcdj();
				message = MessageInfo.MESSAGE_SAVE_SUCCESS;
			}else {
				message = MessageInfo.MESSAGE_SAVE_FALSE;
			}
			
			rForm.setMessage(message);
		}
		// =================== end ==============

		// ============= 存放流程等级 =================
		List<Integer> lcdjList = service.getLcdj();
		
		// ================= end ========================

		// ================== 返回指定流程 ================
		if(StringUtils.isNotNull(pkValue)){
			Map<String, String> rs = service.getLcForPk(pkValue);
			request.setAttribute("rs", rs);
			if(StringUtils.isNotNull(rs.get("lcdj"))){
				lcdjList = PjpyLcszService.sort(lcdjList, Integer.parseInt(rs.get("lcdj")));
			}
		}
		// ================== end ========================
	
		// =================== 初始化列表值 ===========
		service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		
		request.setAttribute("lcdjList", lcdjList);
		// =================== end ===================
		
		return mapping.findForward("pjlcszOne");
	}
}