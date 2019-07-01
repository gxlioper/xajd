package xsgzgl.xtwh.general.qxgl.yhzgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zfsoft.xgxt.base.message.MessageKey;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.general.qxgl.GnmkModel;
import xsgzgl.xtwh.general.qxgl.yhgl.YhglNewForm;
import xsgzgl.xtwh.general.qxgl.yhgl.YhglNewService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_权限管理_用户组管理_action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author zhanghui
 * @version 1.0
 */
public class YhzglNewAction extends BasicAction{
	
	/**
	 * 用户组管理
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public ActionForward yhzglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		YhzglNewForm myForm = (YhzglNewForm) form;
		YhzglNewService service = new YhzglNewService();
		YhzglNewInit init = new YhzglNewInit();
		
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initSearch(rForm, myForm, user, request);
		// =================== end ===================
		//===============导出表设置===================
		request.setAttribute("tableName", "yhzb");
		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("manage");
	}
	public ActionForward ymcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		String type=request.getParameter("type");
		YhzglNewService service = new YhzglNewService();
		if("add".equals(type)){
			return mapping.findForward("add"); 
		}else if("copy".equals(type)){
//			String zmc_old=request.getParameter("zmc_old");
//			request.setAttribute("zmc_old","复制"+zmc_old);
			String zdm=request.getParameter("zdm");
			request.setAttribute("zdm",zdm);
			String zmc_old=service.getYhzmc(zdm);
			request.setAttribute("zmc_old","复制"+zmc_old);
			return mapping.findForward("copy"); 
		}else{
//			String zmc_old=request.getParameter("zmc_old");
//			request.setAttribute("zmc_old",zmc_old);
			String zdm=request.getParameter("zdm");
			request.setAttribute("zdm",zdm);
			String zmc_old=service.getYhzmc(zdm);
			request.setAttribute("zmc_old",zmc_old);
			return mapping.findForward("update");
		}
		
	}
	/**
	 * 用户组管理_功能授权
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public ActionForward yhzglGnsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		// 登陆用户
		String userName = (String)session.getAttribute("userName");
		
		YhzglNewService service = new YhzglNewService();
		
		YhzglNewForm myForm = (YhzglNewForm) form;
		String zdm = myForm.getPkValue();

		//用户组信息
		HashMap<String,String> map = service.getYhzxx(zdm);
		
		request.setAttribute("rs", map);
		return mapping.findForward("gnsq");
	}

	/**
	 * 异步获取菜单树
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getAllDjGnmkList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		YhzglNewService service = new YhzglNewService();

		YhzglNewForm myForm = (YhzglNewForm) form;
		String zdm = myForm.getPkValue();
		List list = service.getAllDjGnmkList(zdm);
		JSONArray dataList = JSONArray.fromObject(list);
		response.getWriter().print(dataList);
		return null;
	}

	/**
	 * 用户功能授权保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhzGnsqSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		YhzglNewService service = new YhzglNewService();

		YhzglNewForm myForm = (YhzglNewForm) form;
		String zdm = myForm.getPkValue();
		String yhzgnqx = myForm.getYhzgnqx();
		JSONArray yhzgnqxArray = JSONArray.fromObject(yhzgnqx);

		boolean result = false;
		result = service.yhzGnsqSave(zdm,yhzgnqxArray);

		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 用户组管理_分配用户
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public ActionForward yhzglFpyh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhzglNewService service = new YhzglNewService();
		YhzglNewForm myForm = (YhzglNewForm) form;
		String zdm = myForm.getPkValue();
		// 操作类型
		String doType = request.getParameter("doType");
		// 保存组分配的用户
		if("save".equalsIgnoreCase(doType)){
			zdm = myForm.getZdm();
			String yhm = myForm.getPkValue();
			String qx = request.getParameter("qx");
			if(StringUtils.isNull(qx)){
				qx = "1";//设置权限为“已启用”（无论之前是否已启用，用户与组绑定后就置为已启用。）
			}
			YhglNewService yhglNewSV = new YhglNewService();
			YhglNewForm yhglForm = new YhglNewForm();
			yhglForm.setPrimarykey_checkVal(yhm.split(","));
			yhglForm.setZdm(zdm);
			yhglForm.setQx(qx);
			Boolean flag = yhglNewSV.plUpdateYhxx(yhglForm);
			response.getWriter().print(flag);
			return null;
		}
		RequestForm rForm = new RequestForm();
		rForm.setGnmk("xtwh");	
		rForm.setMenu("qxgl");
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
	
		request.setAttribute("zdm", zdm);
		request.setAttribute("fpzt", "wfp");
		//用户组信息
		HashMap<String,String> map = service.getYhzxx(zdm);
		request.setAttribute("rs", map);
		return mapping.findForward("fpyh");
	}
	/**
	 * 查看用户组的用户列表
	 */
	public ActionForward yhzglViewYhxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {	
		String zdm = request.getParameter("zdm");		
		request.setAttribute("zdm", zdm);
		return mapping.findForward("view");
	}
	
	
	/**
	 * 用户组管理_分配用户
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public ActionForward yhzglFpyhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhzglNewService service = new YhzglNewService();
		YhzglNewForm myForm = (YhzglNewForm) form;
		String zdm = request.getParameter("zdm");
		// 操作类型
		String doType = request.getParameter("doType");
		if(doType.equals("yfpyh")){
			
			
		}else if(doType.equals("dfpyh")){
			
		}
		
		return null;
	
	}
}
