/**
 * @部门:学工产品事业部
 * @日期：2016-7-28 上午09:11:13 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.dmwh;

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
 * @模块名称: 助学贷款-院设奖学金-代码维护
 * @类功能描述: TODO(代码维护) 
 * @作者： MengWei[工号:1186]
 * @时间： 2016-7-28 上午09:11:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DmwhAction extends SuperAction {
	private static final String url = "zxdk_ysjxj_dmwh.do";
	
	/**
	 * @描述:TODO(资金来源跳转页面)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-28 下午02:16:39
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
	public ActionForward getDmwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = "zxdk_ysjxj_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dmwhList");
	}
	/**
	 * @描述:TODO(查询发放路径)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-28 下午02:19:20
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
	public ActionForward dmwhQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DmwhForm model = (DmwhForm) form;
		DmwhService service = new DmwhService();
		//查询结果集
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述:TODO(增加)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-28 下午03:56:46
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
	@SystemLog(description="访问日常事务-费用发放-代码维护-发放途径-增加ZJLYMC:{zjlymc}")
	public ActionForward dmwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DmwhForm model = (DmwhForm) form;
		DmwhService service = new DmwhService();
		if (SAVE.equalsIgnoreCase(model.getType())){
			model.setZjlymc(StringUtil.trim(model.getZjlymc()));
			//判断资金来源名称是否存在
			boolean isExist=service.isExistByZjlymc(model); 
			if(!isExist){
				//获得资金来源代码的最大代码的 下一级代码
				int nextZjlydm = service.getNextZjlydm();
				model.setZjlydm(nextZjlydm+"");
				//增加一条新的资金来源代码
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_FYFF_DMWH_EXIST));
				return null;
			}
		}
		return mapping.findForward("dmwhAdd");
	}
	
	/**
	 * @描述:TODO(修改)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-28 下午04:50:19
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
	@SystemLog(description="访问助学贷款-院设奖学金-代码维护-资金来源名称-修改ZJLYDM:{zjlydm},ZJLYMC:{zjlymc}")
	public ActionForward dmwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DmwhForm model = (DmwhForm) form;
		DmwhService service = new DmwhService();
		DmwhForm myForm = service.getModel(model);
		if (UPDATE.equalsIgnoreCase(model.getType())){
			model.setZjlymc(StringUtil.trim(model.getZjlymc()));
			//判断有没修改资金来源名称
			if(!model.getZjlymc().trim().equals(myForm.getZjlymc().trim())){
				//判断院设奖学金结果当中是否已使用
				String checkZjlymcForYsjxjjg = service.checkZjlymcForYsjxjjg(model.getZjlydm());
				if(!checkZjlymcForYsjxjjg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.RCSW_FYFF_DMWH_MCEXIST,checkZjlymcForYsjxjjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}else{
				//没有修改名称直接保存
				model.setZjlymc(myForm.getZjlymc().trim());
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			//判断资金来源名称是否存在
			boolean isExist=service.isExistByZjlymc(model);
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_FYFF_DMWH_EXIST));
				return null;
			}
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("dmwhUpdate");
	}
	
	/**
	 * @描述:TODO(删除，判断是否在院设奖学金结果中应用)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-7-28 下午03:14:57
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
	@SystemLog(description="访问助学贷款-院设奖学金-代码维护-删除VALUES:{values}")
	public ActionForward dmwhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DmwhService service = new DmwhService();
		String values = request.getParameter("values");
		if(!StringUtil.isNull(values)){
			//判断发放结果当中是否已使用
			String checkZjlymcForYsjxjjg = service.checkZjlymcForYsjxjjg(values);
			if(!checkZjlymcForYsjxjjg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_FYFF_DMWH_MCEXIST,checkZjlymcForYsjxjjg);
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
