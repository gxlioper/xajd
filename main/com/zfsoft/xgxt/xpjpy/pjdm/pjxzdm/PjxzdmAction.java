/**
 * @部门:学工产品事业部
 * @日期：2013-8-21 上午10:46:26 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm.pjxzdm;

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
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖性质代码维护
 * @作者：CQ [工号：785]
 * @时间： 2013-8-21 上午10:46:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjxzdmAction extends SuperAction{
	
	private static final String url = "pj_dmwh.do";
	
	/**
	 * 评奖项目性质代码维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewPjxzdmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjxzdmForm model = (PjxzdmForm) form;
		PjxzdmService service = new PjxzdmService();
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "pj_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewPjxzdmList");
	}
	
	/**
	 * 增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问评奖评优-基本设置-代码维护-评奖项目性质增加XMXZMC:{xmxzmc}")
	public ActionForward addPjxzdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjxzdmForm model = (PjxzdmForm) form;
		PjxzdmService service = new PjxzdmService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//判断评奖结果当中是否存在
			model.setXmxzmc(model.getXmxzmc().trim());
			boolean isExist=service.isExistByXmxzdm(model, SAVE);
			if(!isExist){
				int nextXzdm=service.getNextXmxzdm();
				model.setXmxzdm(nextXzdm+"");
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_XMXZ_XZEXIST));
				return null;
			}
		}
		
		return mapping.findForward("addPjxzdm");
	}
	
	/**
	 * 
	 * @描述:修改评奖性质代码
	 * @作者：cq [工号：785]
	 * @日期：2013-8-21 下午02:06:22
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
	@SystemLog(description="访问评奖评优-基本设置-代码维护-评奖项目性质修改-XMXZDM：{xmxzdm},XMXZMC:{xmxzmc}")
	public ActionForward updatePjxzdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjxzdmForm myForm = (PjxzdmForm) form;
		PjxzdmService service = new PjxzdmService();
		PjxzdmForm model = service.getModel(myForm);
		
		if (UPDATE.equalsIgnoreCase(myForm.getType())){
			//判断有么有修改性质名称
			if(!myForm.getXmxzmc().trim().equals(model.getXmxzmc().trim())){
				//检验评奖结果当中是否有使用
				String checkPjxzdmForPjjg = service.checkXzForPjjg(myForm.getXmxzdm());
				//检验评奖项目当中是否有使用
				String checkPjxzdmForPjxm = service.checkXzForPjxm(myForm.getXmxzdm());
				if(!checkPjxzdmForPjjg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJJG_UPDATE,checkPjxzdmForPjjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				if(!checkPjxzdmForPjxm.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJXM_UPDATE,checkPjxzdmForPjjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}else{
				//没有修改直接保存
				myForm.setXmxzmc(myForm.getXmxzmc().trim());
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			//判断性质名称是否存在
			myForm.setXmxzmc(myForm.getXmxzmc().trim());
			boolean isExist = service.isExistByXmxzdm(myForm, UPDATE);
			if(!isExist){
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_XMXZ_XZEXIST));
				return null;
			}
		}
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		
		return mapping.findForward("updatePjxzdm");
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：cq [工号：785]
	 * @日期：2013-8-21 下午02:19:16
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
	@SystemLog(description="访问评奖评优-基本设置-代码维护-评奖项目性质删除-VALUES：{values}")
	public ActionForward delPjxzdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjxzdmService service = new PjxzdmService();
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			//检验评奖结果当中是否有使用
			String checkPjxzdmForPjjg = service.checkXzForPjjg(values);
			//检验评奖项目当中是否有使用
			String checkPjxzdmForPjxm = service.checkXzForPjxm(values);
			if(!checkPjxzdmForPjjg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJJG_DELETE,checkPjxzdmForPjjg);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			if(!checkPjxzdmForPjxm.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJXM_DELETE,checkPjxzdmForPjjg);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}

}
