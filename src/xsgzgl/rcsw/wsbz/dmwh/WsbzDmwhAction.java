/**
 * @部门:学工产品事业部
 * @日期：2016-5-5 上午09:56:00 
 */  
package xsgzgl.rcsw.wsbz.dmwh;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-5-5 上午09:56:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsbzDmwhAction extends SuperAction<WsbzDmwhForm, WsbzDmwhService> {
	WsbzDmwhService service = new WsbzDmwhService();
	private static final String url = "rcsw_wsbz_dmwh.do";
	public ActionForward getDmwhCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		WsbzDmwhForm model = (WsbzDmwhForm)form;
		User user = getUser(request);
		if("query".equalsIgnoreCase(model.getType())){
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray datalist = JSONArray.fromObject(resultList);
			response.getWriter().print(datalist);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/*浙江旅游个性化start*/

	//增加
	public ActionForward addWsbz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		return mapping.findForward("add");
	}
	
	public ActionForward updateWsbz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		WsbzDmwhForm myForm = (WsbzDmwhForm) form;
		WsbzDmwhForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		return mapping.findForward("udpate");
	}
	
	
	public ActionForward setWsbz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		HashMap<String,String> cs = service.getQjcs();
		request.setAttribute("cs", cs);
		return mapping.findForward("setQjcs");
	}
	
	public ActionForward savesetQjcs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		String bmcs =request.getParameter("bmcs");
		String jzts =request.getParameter("jzts");
		String jzsj =request.getParameter("jzsj");
		boolean flag = service.runUpdateQjcs(bmcs,jzts,jzsj);
		String messageKey = flag ? MessageKey.SYS_OPERATE_SUCCESS
				: MessageKey.SYS_OPERATE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:查看
	 * @作者：yxy[工号：1206]
	 * @日期：2016-4-28 下午02:48:27
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
	public ActionForward ckWsbz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		WsbzDmwhForm myForm = (WsbzDmwhForm) form;
		HashMap<String,String> jg = service.getWsbzCk(myForm.getFddm());
		request.setAttribute("jg", jg);
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @描述:保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-4-28 下午02:47:47
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
	public ActionForward saveWsbz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		WsbzDmwhForm modle = (WsbzDmwhForm)form;
	    boolean flag = service.checkIsExists(modle);
	    String message = "";
	    HashMap<String, String> map = new HashMap<String, String>();
		if(modle.getType().equals("add")){
			if(flag){
				message = "名称已存在，请确认！";
			    response.getWriter().print(getJsonMessage(message));
			    return null;
			}
			 flag = service.runInsert(modle);
			 message = flag ? "保存成功！" :"保存失败！";
			 response.getWriter().print(getJsonMessage(message));
			 return null;
		}else{
			if(flag){
				message = "名称已存在，请确认！";
			    response.getWriter().print(getJsonMessage(message));
			    return null;
			}
			 flag = service.runUpdate(modle);
			 message = flag ? "保存成功！" :"保存失败！";
			 response.getWriter().print(getJsonMessage(message));
			 return null;
			
		}
	}
	
	/**
	 * 
	 * @描述:删除
	 * @作者：yxy[工号：1206]
	 * @日期：2016-4-28 下午02:47:16
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
	public ActionForward delWsbz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		//获得id
		String values = request.getParameter("values");
		String message = "";
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			if(service.checkIsUsingNow(ids)){
				message = "被删除的项目正在被使用！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			int num = service.runDelete(ids);
			boolean result = num > 0;
			 message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/*浙江旅游个性化end*/
}
