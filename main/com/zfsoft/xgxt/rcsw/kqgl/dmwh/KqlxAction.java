/**
 * @部门:学工产品事业部
 * @日期：2014-6-6 上午09:30:14 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.dmwh;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考勤管理模块
 * @类功能描述: 考勤类型代码维护
 * @作者： 陶钢军[工号:1075]
 * @时间： 2014-6-6 上午09:32:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KqlxAction extends SuperAction {

	private static final String url = "rcsw_kqgl_kqlxdmwh.do";
	
	/**
	 * 
	 * @描述:考勤类型代码维护
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-6 下午01:28:11
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
	public ActionForward viewKqlxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = "rcsw_kqgl_kqlxdmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewKqlxList");
	}
	
	
	/**
	 * 
	 * @描述:查询考勤类型列表
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-6 下午01:31:56
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
	public ActionForward kqlxQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqlxForm model = (KqlxForm) form;
		KqlxService service = new KqlxService();
		
		//查询结果集
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
		
	}
	
	
	/**
	 * 
	 * @描述:增加考勤类型代码
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-6 下午01:32:24
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
	@SystemLog(description="访问日常事务-考勤管理-代码维护-增加KQLXMC:{kqlxmc}")
	public ActionForward addKqlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqlxForm model = (KqlxForm) form;
		KqlxService service = new KqlxService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//判断考勤类型代码和名称是否存在
			boolean isExist = service.isExistByKqlxmc(model);
			if(!isExist){
				//获得考勤类型代码
				int nextKqlxdm = service.getNextKqlxdm();
				model.setKqlxdm(common.newp.StringUtil.toLengthStr(nextKqlxdm+"", 3, 0, "0"));
				//增加一条新的考勤类型代码
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_KQGL_DMWH_EXIST));
				return null;
			}
		}
		
		return mapping.findForward("addKqlx");
	}
	
	
	/**
	 * 
	 * @描述:修改考勤类型代码
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-6 下午01:44:56
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
	@SystemLog(description="访问日常事务-考勤管理-代码维护-修改KQLXDM:{kqlxdm},KQLXMC:{kqlxmc}")
	public ActionForward updateKqlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqlxForm model = (KqlxForm) form;
		KqlxService service = new KqlxService();
		KqlxForm myForm = service.getModel(model);
		
		
		if (UPDATE.equalsIgnoreCase(model.getType())){
			//判断有没修改考勤类型名称
			if(!model.getKqlxmc().trim().equals(myForm.getKqlxmc().trim())){
				//判断考勤登记表中是否已使用
				String checkKqlxdmForKqdj = service.checkKqlxdmForKqdj(model.getKqlxdm());
				
				if(!checkKqlxdmForKqdj.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.RCSW_KQGL_DMWH_MCEXIST, checkKqlxdmForKqdj);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				
			}else{
				//没有修改名称直接保存
				model.setKqlxmc(myForm.getKqlxmc().trim());
				
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			//判断考勤类型代码和名称是否存在
			boolean isExist=service.isExistByKqlxmc(model);
			
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_KQGL_DMWH_EXIST));
				return null;
			  
			}
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("updateKqlx");
		
	}
	
	
	/**
	 * 
	 * @描述:删除考勤类型代码
	 * @作者：陶钢军[工号：1075]
	 * @日期：2014-6-6 下午01:54:18
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
	@SystemLog(description="访问日常事务-考勤管理-代码维护-删除VALUES:{values}")
	public ActionForward delKqlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqlxService service = new KqlxService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			//判断考勤登记表中是否已使用
			String checkKqlxdmForKqdj = service.checkKqlxdmForKqdj(values);
			
			if(!checkKqlxdmForKqdj.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_KQGL_DMWH_MCEXIST, checkKqlxdmForKqdj);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
}
