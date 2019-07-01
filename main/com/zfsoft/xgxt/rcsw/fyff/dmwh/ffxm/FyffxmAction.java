/**
 * @部门:学工产品事业部
 * @日期：2014-4-2 上午09:27:59 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.ffxm;

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
 * @模块名称: 日常事务-费用发放-基础数据维护-发放项目
 * @类功能描述: 
 * @作者： cq [工号:785]
 * @时间： 2014-4-2 上午09:27:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FyffxmAction extends SuperAction{

	private static final String url = "rcsw_fyff_dmwh_ffxm.do";
	
	/**
	 * 
	 * @描述:发放项目页面跳转
	 * @作者：cq [工号：785]
	 * @日期：2014-4-3 上午09:35:02
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
	public ActionForward viewFfxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = "rcsw_fyff_dmwh_ffxm.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewFfxm");
	}
	
	
	/**
	 * 
	 * @描述:查询发放项目list
	 * @作者：cq [工号：785]
	 * @日期：2014-4-3 上午09:42:14
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
	public ActionForward ffxmQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FyffxmForm model = (FyffxmForm) form;
		FyffxmService service = new FyffxmService();
		
		//查询结果集
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
		
	}
	
	
	/**
	 * 
	 * @描述:增加发放项目代码
	 * @作者：cq [工号：785]
	 * @日期：2014-4-3 上午10:02:33
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
	@SystemLog(description="访问日常事务-费用发放-代码维护-发放项目-增加FFXMMC:{ffxmmc}")
	public ActionForward addFfxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FyffxmForm model = (FyffxmForm) form;
		FyffxmService service = new FyffxmService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//判断项目代码和名称是否存在
			boolean isExist = service.isExistByFfxmmc(model);
			if(!isExist){
				//获得发放途径代码
				int nextFfxmdm = service.getNextFfxmdm();
				model.setFfxmdm(nextFfxmdm+"");
				//增加一条新的发放途径代码
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_FYFF_DMWH_EXIST));
				return null;
			}
		}
		
		//默认选中按次发放
		model.setFffs("1");
		
		return mapping.findForward("addFfxm");
	}
	
	
	/**
	 * 
	 * @描述:修改发放项目代码
	 * @作者：cq [工号：785]
	 * @日期：2014-4-3 上午10:53:07
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
	@SystemLog(description="访问日常事务-费用发放-代码维护-发放项目-修改FFXMDM:{ffxmdm},FFXMMC:{ffxmmc}")
	public ActionForward updateFfxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FyffxmForm model = (FyffxmForm) form;
		FyffxmService service = new FyffxmService();
		FyffxmForm myForm = service.getModel(model);
		
		
		if (UPDATE.equalsIgnoreCase(model.getType())){
			//判断有没修改发放项目名称
			if(!model.getFfxmmc().trim().equals(myForm.getFfxmmc().trim())){
				//判断发放结果当中是否已使用
				String checkFfxmForFfjg = service.checkFfxmdmForFfjg(model.getFfxmdm());
				
				if(!checkFfxmForFfjg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.RCSW_FYFF_DMWH_MCEXIST,checkFfxmForFfjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				
			}else{
				//没有修改名称直接保存
				model.setFfxmmc(myForm.getFfxmmc().trim());
				
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			boolean isExist=service.isExistByFfxmmc(model);
			
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_FYFF_DMWH_MCEXIST));
				return null;
			  
			}
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("updateFfxm");
		
	}
	
	
	/**
	 * 
	 * @描述:删除发放项目代码
	 * @作者：cq [工号：785]
	 * @日期：2014-4-3 上午11:37:53
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
	@SystemLog(description="访问日常事务-费用发放-代码维护-发放项目-删除VALUES:{values}")
	public ActionForward delFfxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FyffxmService service = new FyffxmService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			//判断发放结果当中是否已使用
			String checkFfxmForFfjg = service.checkFfxmdmForFfjg(values);
			
			if(!checkFfxmForFfjg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_FYFF_DMWH_MCEXIST,checkFfxmForFfjg);
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
