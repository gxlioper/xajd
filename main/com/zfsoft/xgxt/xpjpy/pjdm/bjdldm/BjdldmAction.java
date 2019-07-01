/**
 * @部门:学工产品事业部
 * @日期：2013-11-21 上午09:18:00 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm.bjdldm;

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
 * @类功能描述: 班级大类维护
 * @作者：CQ [工号：785]
 * @时间： 2013-11-21 上午09:18:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjdldmAction extends SuperAction{
	
	private static final String url = "pj_dmwh.do";
	
	/**
	 * 
	 * @描述:班级大类代码查看
	 * @作者：cq [工号：785]
	 * @日期：2013-11-21 上午09:37:46
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
	public ActionForward viewBjdldmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BjdldmForm model = (BjdldmForm) form;
		BjdldmService service = new BjdldmService();
		
		if(QUERY.equals(model.getType())){
			
			List<HashMap<String, String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "pj_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewBjdldmList");
	}
	
	
	/**
	 * 
	 * @描述:班级大类名称新增
	 * @作者：cq [工号：785]
	 * @日期：2013-11-21 上午11:27:30
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
	@SystemLog(description="访问评奖评优-基本设置-代码维护-评奖班级大类增加:LBMC:{lbmc}")
	public ActionForward addBjlbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjdldmForm model = (BjdldmForm) form;
		BjdldmService service = new BjdldmService();
		
		if(SAVE.equalsIgnoreCase(model.getType())){
			//判断是否已有维护
			model.setLbmc(model.getLbmc().trim());
			boolean isExist=service.isExistByBjdldm(model);
			if(!isExist){
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_BJDM_DMEXIST));
				return null;
			}
		}
		
		return mapping.findForward("addBjlbdm");
	}
	
	/**
	 * 
	 * @描述:班级大类名称修改
	 * @作者：cq [工号：785]
	 * @日期：2013-11-21 下午04:08:00
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
	@SystemLog(description="访问评奖评优-基本设置-代码维护-评奖班级大类修改-LBDM：{lbdm},LBMC:{lbmc}")
	public ActionForward updateBjlbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BjdldmForm myForm = (BjdldmForm) form;
		BjdldmService service = new BjdldmService();
		BjdldmForm model = service.getModel(myForm);

		if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			// 判断是否有修改大类名称
			if (!myForm.getLbmc().trim().equals(model.getLbmc().trim())) {
				// 检验结果当中是否有使用
				String checkBjldForBjlb = service.checkLbForBjdl(myForm
						.getLbdm());
				if (!checkBjldForBjlb.trim().equals("")) {
					String message = MessageUtil.getText(
							MessageKey.PJPY_BJDM_EXIST_BJDL_UPDATE,
							checkBjldForBjlb);
					response.getWriter().print(getJsonMessage(message));
					System.out.println(11);
					return null;
				}
			}else {
					// 没有修改直接保存
					myForm.setLbmc(myForm.getLbmc().trim());
					boolean result = service.runUpdate(myForm);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					System.out.println(11);
					return null;
			}
			//判断大类名称是否存在
			myForm.setLbmc(myForm.getLbmc().trim());
			boolean isExist = service.isExistByBjdldm(myForm);
			if(!isExist){
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_BJDM_DMEXIST));
				return null;
			}
			
		}
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

		return mapping.findForward("updateBjlbdm");
	}
	
	/**
	 * 
	 * @描述:班级大类名称删除
	 * @作者：cq [工号：785]
	 * @日期：2013-11-21 下午04:11:52
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
	@SystemLog(description="访问评奖评优-基本设置-代码维护-评奖班级大类删除-VALUES：{values}")
	public ActionForward deleteBjlbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjdldmService service = new BjdldmService();
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			//检验结果是否有使用
			String checkBjldForBjlb = service.checkLbForBjdl(values);
			if (!checkBjldForBjlb.trim().equals("")) {
				String message = MessageUtil.getText(
						MessageKey.PJPY_BJDM_EXIST_BJDL_DELETE,
						checkBjldForBjlb);
				response.getWriter().print(getJsonMessage(message));
				System.out.println(11);
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