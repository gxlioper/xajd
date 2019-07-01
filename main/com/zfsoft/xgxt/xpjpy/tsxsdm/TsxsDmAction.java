/**
 * @部门:学工产品事业部
 * @日期：2013-8-20 上午09:29:00 
 */  
package com.zfsoft.xgxt.xpjpy.tsxsdm;

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
 * @模块名称: XXXX管理模块
 * @类功能描述: 特殊学生代码维护 
 * @作者：CQ [工号：785]
 * @时间： 2013-8-20 上午09:29:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsxsDmAction extends SuperAction{

	private static final String url = "xpj_tsxsdm.do?method=tsxsDmList&mklx=pjpy";
	
	/**
	 * 特殊学生代码维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsxsDmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TsxsDmForm model = (TsxsDmForm) form;
		TsxsDmService service = new TsxsDmService();
		String mklx = request.getParameter("mklx");
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "xpj_tsxsdm.do?method=tsxsDmList&mklx="+mklx;
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tsxsDmList");
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
	@SystemLog(description="访问评奖评优-特殊学生-代码维护-增加-LXMC:{lxmc},LXSX:{lxsx}")
	public ActionForward addTsxsDm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsxsDmForm model = (TsxsDmForm) form;
		TsxsDmService service = new TsxsDmService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//判断档次名称是否存在
			boolean isExist=service.isExistByTsxsDm(model,SAVE);
			if(!isExist){
				int nextLxdm=service.getNextTsxsDm();
				model.setLxdm(nextLxdm+"");
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_TSXSDM_NAME_REPEAT));
				return null;
				
			}
		}
		
		return mapping.findForward("addTsxsDm");
	}
	
	/**
	 * 
	 * @描述:修改特学生类型
	 * @作者：cq [工号：785]
	 * @日期：2013-8-20 下午02:03:10
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
	@SystemLog(description="访问评奖评优-特殊学生-代码维护-修改-LXDM：{lxdm},LXMC:{lxmc},LXSX:{lxsx}")
	public ActionForward updateTsxsDm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsxsDmForm myForm = (TsxsDmForm) form;
		TsxsDmService service = new TsxsDmService();
		TsxsDmForm model = service.getModel(myForm);
		
		if (UPDATE.equalsIgnoreCase(myForm.getType())){
			//判断有没修改档次名称
			if(!myForm.getLxmc().trim().equals(model.getLxmc().trim())||!myForm.getLxsx().trim().equals(model.getLxsx().trim())){
				//检验特殊学生表中是否有被修改的类型
				String checkLxmcForTsxsb = service.checkDcForTsxsb(myForm.getLxdm());
				if(!checkLxmcForTsxsb.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_TSXSDM_EXIST_TSXSB_UPDATE,checkLxmcForTsxsb);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}else{
				//没有修改档次名称直接保存
				myForm.setLxmc(model.getLxmc().trim());
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
				
			}
			//判断档次名称是否存在
			boolean isExist = service.isExistByTsxsDm(myForm, UPDATE);
			if(!isExist){
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_TSXSDM_NAME_REPEAT));
				return null;
			  
			}
		}
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		
		return mapping.findForward("updateTsxsDm");
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：cq [工号：785]
	 * @日期：2013-8-20 下午02:19:32
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
	@SystemLog(description="访问评奖评优-特殊学生-代码维护-删除-VALUES：{values}")
	public ActionForward delTsxsDm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsxsDmService service = new TsxsDmService();
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			//检验特殊学生结果中是否有被删除的类型
			String checkLxmcForTsxsb = service.checkDcForTsxsb(values);
			if(!checkLxmcForTsxsb.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_TSXSDM_EXIST_TSXSB_DELETE,checkLxmcForTsxsb);
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
