/**
 * @部门:学工产品事业部
 * @日期：2017-5-31 上午10:59:40 
 */  
package xsgzgl.gygl.wsjc.jcrcgl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 检查日程action(浙江商业技师学院)
 * @类功能描述: 浙江商业技师学院个性化 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-5-31 上午10:59:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class GyglJcrcglForZjSyJsAction extends SuperAction<GyglJcrcglForm, GyglJcrcglForZjSyJsService>{
	/** 
	 * @描述:保存(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-5-31 下午01:54:43
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
	public ActionForward saveForm (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		GyglJcrcglForm model = (GyglJcrcglForm) form;
		GyglJcrcglForZjSyJsService service = new GyglJcrcglForZjSyJsService();
		boolean result = service.insertJcrc(model);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}	
}
