/**
 * @部门:学工产品事业部
 * @日期：2016-6-21 上午09:45:49 
 */  
package com.zfsoft.xgxt.xszz.sqlydmwh;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助-困难生认定
 * @类功能描述: 申请理由代码维护  
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-6-21 上午09:45:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SqlyDmwhAction extends SuperAction<SqlyDmwhForm, SqlyDmwhService> {
	
	private static final String url = "xszz_knsrd_sqlywh.do";
	
	/**
	 * 
	 * @描述: 查询
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-21 上午10:45:03
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
	public ActionForward sqlywhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = "xszz_knsrd_sqlywh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("sqlywhManage");
	}
	
	/**
	 * 
	 * @描述: 查询
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-21 上午11:21:15
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
	public ActionForward sqlywhQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqlyDmwhForm model = (SqlyDmwhForm) form;
		SqlyDmwhService service = new SqlyDmwhService();
		
		//查询结果集
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;	
	}
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-21 下午01:30:43
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
	public ActionForward addSqlywh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqlyDmwhForm model = (SqlyDmwhForm) form;
		SqlyDmwhService service = new SqlyDmwhService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//判断是否存在
			boolean isExist = service.isExistBySqlywh(model);
			if(!isExist){
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_KNSRD_SQLYWH_REPEAT));
				return null;
			}
		}
		
		return mapping.findForward("addSqlywh");	
	}
	
	
	/**
	 * 
	 * @描述: 修改
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-21 下午02:52:41
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
	public ActionForward updateSqlywh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqlyDmwhForm model = (SqlyDmwhForm) form;
		SqlyDmwhService service = new SqlyDmwhService();
		SqlyDmwhForm myForm = service.getModel(model);
		
		if (UPDATE.equalsIgnoreCase(model.getType())){
			
			//判断有没修改
			if(model.getSqlymc().trim().equals(myForm.getSqlymc().trim())){
				
				model.setSqlymc(myForm.getSqlymc().trim());
				
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
				
			}
			
			//判断是否存在
			boolean isExist = service.isExistBySqlywh(model);
			
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_KNSRD_SQLYWH_REPEAT));
				return null;
			  
			}
			
		}
		
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("updateSqlywh");	
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-21 下午03:14:52
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
	public ActionForward delSqlywh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqlyDmwhService service = new SqlyDmwhService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			
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
