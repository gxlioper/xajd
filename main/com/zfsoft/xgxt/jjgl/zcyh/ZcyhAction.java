/**
 * @部门:学工产品事业部
 * @日期：2014-8-28 上午11:10:02 
 */  
package com.zfsoft.xgxt.jjgl.zcyh;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-28 上午11:10:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcyhAction extends SuperAction<ZcyhForm, ZcyhService> {

	
	
	/**
	 *  @属性： PATH 路径
	 */
	private static final String PATH = "jjgl_zcyh.do";
	
	private static final String url = "jjgl_zcyh.do";
	
	/**
	 * 
	 * @描述:注册用户查询页面
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-26 下午06:43:32
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
	public ActionForward zcyhCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zcyhCx");
	}
	
	
	/**
	 * 
	 * @描述:查询注册用户列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-27 上午09:43:49
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
	public ActionForward queryZcyhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcyhForm model = (ZcyhForm) form;
		
		//查询
		List<HashMap<String,String>> resultList = getService().getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述:黑名单页面
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-26 下午06:43:32
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
	public ActionForward hmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcyhForm model = (ZcyhForm) form;
		
		String yhm = model.getYhm();
		
		if(StringUtils.isNotBlank(yhm)){
			ZcyhForm dataModel = getService().getModel(yhm);
			
			BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(dataModel));
			
		}
		
		return mapping.findForward("hmd");
	}
	
	/**
	 * 
	 * @描述:设置黑名单提交
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-26 下午06:43:32
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
	public ActionForward hmdSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcyhForm model = (ZcyhForm) form;
		boolean result = false;
		JSONObject message = null;
		result = getService().szHmd(model);
		message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL );
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @描述:撤销黑名单
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-26 下午06:43:32
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
	public ActionForward hmdCancelSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcyhForm model = (ZcyhForm) form;
		boolean result = false;
		JSONObject message = null;
		result = getService().cancelHmd(model);
		message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL );
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:查看用户详情
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-26 下午06:43:32
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
	public ActionForward viewZcyh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcyhForm model = (ZcyhForm) form;
		
		String yhm = model.getYhm();
		
		if(StringUtils.isNotBlank(yhm)){
			HashMap<String , String> zcyhxxMap = getService().getZcyhMapById(yhm);
			
			List<HashMap<String, String>> znxxMapList = getService().getZnxxMapByYhm(yhm);
			
			request.setAttribute("zcyhxxMap", xgxt.utils.String.StringUtils.formatData(zcyhxxMap));
			
			request.setAttribute("znxxMapList", znxxMapList);
		}
		
		return mapping.findForward("ck");
	}

	/**
	 * 跳转到家长信息增加页面.
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-23 17:50
	 */
	@SystemAuth(url = url)
	public ActionForward jzxxAdd(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcyhService svc = getService();

		//yhm自动编码 年份+0001
		String maxYhm = svc.getMaxYhm();
		Integer yhm = 0;
		String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());
		if(maxYhm.substring(0,2).equals(yearLast)){
			yhm = Integer.parseInt(maxYhm)+1;
		}else {
			yhm = Integer.parseInt(yearLast+"0001");
		}
		request.setAttribute("yhm",yhm);
		return mapping.findForward("jzxxAdd");
	}


	/**
	 * 家长信息增加的保存.
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-26 16:34
	 */
	@SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
	public ActionForward jzxxSaveForAdd(ActionMapping mapping, ActionForm form,
										  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcyhForm zcyhForm = (ZcyhForm) form;
		ZcyhService svc = getService();
		//用户名 身份证号 联系电话已被注册
		boolean isYhmExist = svc.isYhmExist(zcyhForm);
		if (isYhmExist) {
			response.getWriter().print(getJsonMessage("用户名已被注册"));
			return null;
		}

		boolean isSfzhExist = svc.isSfzhExist(zcyhForm);
		if (isSfzhExist) {
			response.getWriter().print(getJsonMessage("身份证号已被注册"));
			return null;
		}

		boolean isLxdhExist = svc.isLxdhExist(zcyhForm);
		if (isLxdhExist) {
			response.getWriter().print(getJsonMessage("联系电话已被注册"));
			return null;
		}

		boolean result = getService().jzxxSaveForAdd(zcyhForm);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 跳转到家长信息修改页面.
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-23 17:50
	 */
	@SystemAuth(url = url)
	public ActionForward jzxxEdit(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZcyhForm zcyhForm = (ZcyhForm) form;
		ZcyhService zcyhService = getService();
		//查询家长信息
		ZcyhForm model = zcyhService.getModel(zcyhForm.getYhm());
		org.springframework.beans.BeanUtils.copyProperties(model,zcyhForm);
		//查询子女信息
		List<HashMap<String,String>> znxxList = zcyhService.getZnxxMapByYhm(zcyhForm.getYhm());
		request.setAttribute("znxxList",znxxList);
		return mapping.findForward("jzxxEdit");
	}


	/**
	 * 家长信息修改的保存.
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throw
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-11-26 16:34
	 */
	@SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
	public ActionForward jzxxSaveForEdit(ActionMapping mapping, ActionForm form,
										HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZcyhForm zcyhForm = (ZcyhForm) form;
		//身份证号 联系电话已被注册
		ZcyhService svc = getService();

		boolean isSfzhExist = svc.isSfzhExist(zcyhForm);
		if (isSfzhExist) {
			response.getWriter().print(getJsonMessage("身份证号已被注册"));
			return null;
		}

		boolean isLxdhExist = svc.isLxdhExist(zcyhForm);
		if (isLxdhExist) {
			response.getWriter().print(getJsonMessage("联系电话已被注册"));
			return null;
		}

		boolean result = getService().jzxxSaveForEdit(zcyhForm);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @描述:家长信息的删除（可批量）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 上午9:50:05
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
	@SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
	public ActionForward jzxxDel(ActionMapping mapping, ActionForm form,
								   HttpServletRequest request, HttpServletResponse response) throws Exception {

		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = getService().jzxxDel(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * @描述:根据用户名
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 上午9:50:05
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
	public ActionForward getJzxxByYhm(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response) throws Exception {

		String yhm = request.getParameter("yhm");
		List<HashMap<String,String>> znxxList = getService().getZnxxMapByYhm(yhm);

		JSONArray dataList = JSONArray.fromObject(znxxList);
		response.getWriter().print(dataList);
		return null;
	}
}
