/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:05:30 
 */  
package xsgzgl.gygl.gyyggl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xucy [工号：754]
 * @时间： 2013-7-30 下午04:05:30 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyygzwdmglAction extends SuperAction{
	
	private static final String url = "gygl_gyyggl_dmwh.do";
	
	/**
	 * 员工职位代码维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward gyygzwdmManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyygzwdmglForm model = (GyygzwdmglForm) form;
		GyygzwdmglService service = new GyygzwdmglService();
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "gygl_gyyggl_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("gyygzwdmManage");
	
	}
	
	/**
	 * 员工职位代码维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-公寓员工管理-职位代码维护-增加:ZWDM:{zwdm},ZWMC:{zwmc}")
	public ActionForward addZwdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyygzwdmglForm model = (GyygzwdmglForm) form;
		GyygzwdmglService service = new GyygzwdmglService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//等级代码是否存在
			boolean isExist=service.isExist(model);
			if(!isExist){
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_DM_REPEAT));
				return null;
			}
		}
		
		return mapping.findForward("addZwdm");
	}
	
	/**
	 * 员工职位代码维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-公寓员工管理-职位代码维护-修改PK:{zwdm},ZWMC:{zwmc}")
	public ActionForward updateZwdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyygzwdmglForm myForm = (GyygzwdmglForm) form;
		GyygzwdmglService service = new GyygzwdmglService();
		GyygzwdmglForm model=service.getModel(myForm);
		if (UPDATE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateZwdm");
	}
	
	/**
	 * 员工职位代码维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-公寓员工管理-职位代码维护-删除VALUES:{values}")
	public ActionForward delZwdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyygzwdmglService service = new GyygzwdmglService();
		
		String values = request.getParameter("values");
		
		boolean isExist=service.checkDel(values);
		if(!isExist){
		int num = service.runDelete(values.split(","));
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
								: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(message));
		}else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_DELDM_EXIST_FAIL));
			
		}
		return null;
	}
	
}
