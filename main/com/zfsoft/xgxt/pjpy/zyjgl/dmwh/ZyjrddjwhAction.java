/**
 * @部门:学工产品事业部
 * @日期：2015-12-21 下午01:23:25 
 */  
package com.zfsoft.xgxt.pjpy.zyjgl.dmwh;

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
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhForm;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-专业奖管理-代码维护
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2015-12-21 下午01:23:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyjrddjwhAction extends SuperAction<ZyjrddjwhForm, ZyjrddjwhService>{
	private static final String url = "pjpy_zyjgl_dmwh.do";
	
	@SystemAuth(url = url)
	public ActionForward rddjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZyjrddjwhForm myForm = (ZyjrddjwhForm) form;
		ZyjrddjwhService service = new ZyjrddjwhService();
		
		if (QUERY.equals(myForm.getType())){		
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "pjpy_zyjgl_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rddjList");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward rddjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZyjrddjwhForm myForm = (ZyjrddjwhForm) form;
		ZyjrddjwhService service = new ZyjrddjwhService();
		//处理空格
		myForm.setRddjmc(StringUtil.trim(myForm.getRddjmc()));
		if("save".equalsIgnoreCase(myForm.getType())){
			boolean flag=service.checkIsExist(myForm);
			if(flag){
				String messageKey=MessageKey.PJPY_ZYJGL_DMWH_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			boolean result = service.runInsert(myForm);
			String messageKey=result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("rddjAdd");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward rddjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZyjrddjwhForm myForm = (ZyjrddjwhForm) form;
		ZyjrddjwhService service = new ZyjrddjwhService();
		//处理空格
		myForm.setRddjmc(StringUtil.trim(myForm.getRddjmc()));
		if("save".equalsIgnoreCase(myForm.getType())){
			boolean flag=service.checkIsExist(myForm);
			if(flag){
				String messageKey=MessageKey.PJPY_ZYJGL_DMWH_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			//验证是否已使用
			if(service.checkDjdmExist(new String[] {myForm.getRddjdm()})) {
				String message = MessageUtil.getText(MessageKey.PJPY_ZYJGL_DMWH_USED);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			boolean result = service.runUpdate(myForm);
			String messageKey=result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		ZyjrddjwhForm updateForm = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(updateForm));
		return mapping.findForward("rddjUpdate");
	}
	
	//删除结果
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward rddjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyjrddjwhService service = new ZyjrddjwhService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//验证是否已使用
			if(service.checkDjdmExist(values.split(","))) {
				String message = MessageUtil.getText(MessageKey.PJPY_ZYJGL_DMWH_USED);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}else {
				int num = service.runDelete(values.split(","));
				boolean result =  num > 0;
				String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
						: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}			
	    }
		return null;
	}
}
