package xsgzgl.gygl.gyccgl.dmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

public class GyccDmwhAction extends SuperAction<GyccDmwhForm, GyccDmwhService> {
	private static final String url = "gygl_gyccgl_dmwh.do";
	private GyccDmwhService service = new GyccDmwhService();
	/**
	 * 
	 * @描述: 查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 上午11:58:05
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
	public ActionForward searchRs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyccDmwhForm myForm = (GyccDmwhForm)form;
		if(QUERY.equals(myForm.getType())){
			User user = getUser(request);
			List<HashMap<String, String>> resultList = new ArrayList<HashMap<String,String>>();
			if("wp".equals(myForm.getCxlx())){
				resultList = service.getPageList(myForm);
			}else{
				resultList = service.getPageList(myForm,user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
		
	}
	
	/**
	 * 
	 * @描述:增加物品跳转页面
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午04:18:52
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
	public ActionForward addWp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		return mapping.findForward("addwp");
	}
	
	/**
	 * 
	 * @描述:修改物品跳转页面
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午04:18:52
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
	public ActionForward updateWp(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)throws Exception{
		GyccDmwhForm myForm = (GyccDmwhForm)form;
		GyccDmwhForm model = service.getModel(myForm.getDm());
		BeanUtils.copyProperties(myForm, model);
		return mapping.findForward("updatewp");
	}
	
	/**
	 * 
	 * @描述: 保存物品
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午04:21:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问公寓管理-宿舍财产管理-代码维护-保存物品，操作类型:{type}-DM:{dm},MC:{mc}")
	public ActionForward saveWpForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		GyccDmwhForm myForm = (GyccDmwhForm)form;
		String messageKey = service.saveWpForm(myForm) ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:增加物品跳转页面
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午04:18:52
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
	public ActionForward addShcd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		return mapping.findForward("addshcd");
	}
	
	/**
	 * 
	 * @描述:修改物品跳转页面
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午04:18:52
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
	public ActionForward updateShcd(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)throws Exception{
		GyccDmwhForm myForm = (GyccDmwhForm)form;
		GyccDmwhForm model = service.getBscdForm(myForm.getShcddm());
		BeanUtils.copyProperties(myForm, model);
		return mapping.findForward("updateshcd");
	}
	
	/**
	 * 
	 * @描述: 保存损害程度
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午05:13:36
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
	@SystemLog(description = "访问公寓管理-宿舍财产管理-代码维护-保存损害程度，操作类型:{type}-SHCDDM:{shcddm},SHCDMC:{shcdmc},JE:{je}")
	public ActionForward saveShcdForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		GyccDmwhForm myForm = (GyccDmwhForm)form;
		String messageKey = service.saveShcdForm(myForm) ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除物品
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午05:37:21
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
	@SystemLog(description = "访问公寓管理-宿舍财产管理-代码维护-删除物品财产DMS:{values}")
	public ActionForward delWp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			if(!service.isWpdmNotUserd(ids)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_DMWH_USERD_NOTDEL));
				return null;
			}
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除损害程度
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午05:39:50
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
	@SystemLog(description = "访问公寓管理-宿舍财产管理-代码维护-删除损坏程度SHCDDMS:{values}")
	public ActionForward delShcd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			if(!service.isShcdNotUserd(ids)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_DMWH_USERD_NOTDEL));
				return null;
			}
			int num = service.DelShcd(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	
}
