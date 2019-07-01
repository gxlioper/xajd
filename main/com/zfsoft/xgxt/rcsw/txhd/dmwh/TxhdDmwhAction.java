/**
 * @部门:学工产品事业部
 * @日期：2014-6-20 上午11:20:20 
 */  
package com.zfsoft.xgxt.rcsw.txhd.dmwh;

import java.net.URLDecoder;
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
 * @模块名称: 日常事务-团学活动
 * @类功能描述: 团学活动Action
 * @作者： cq [工号:785]
 * @时间： 2014-6-20 上午11:20:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TxhdDmwhAction extends SuperAction {
	
	private static final String url = "rcsw_txhd_dmwh.do";
	
	/**
	 * 
	 * @描述:类别代码查看
	 * @作者：cq [工号：785]
	 * @日期：2014-6-20 下午04:32:11
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
	public ActionForward lbdmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhForm model = (TxhdDmwhForm) form;
		TxhdDmwhService service = new TxhdDmwhService();
		
		if (QUERY.equals(model.getType())){
			String lbmc = request.getParameter("lbmc"); 
			lbmc = URLDecoder.decode(URLDecoder.decode(lbmc,"UTF-8"),"UTF-8");
			model.setLbmc(lbmc);
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_txhd_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("lbdmList");
	}
	
	
	/**
	 * 
	 * @描述:增加类别代码
	 * @作者：cq [工号：785]
	 * @日期：2014-6-20 下午04:32:30
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
	@SystemLog(description="访问日常事务-团学活动-代码维护-增加")
	public ActionForward addLbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhForm model = (TxhdDmwhForm) form;
		TxhdDmwhService service = new TxhdDmwhService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//判断类型代码和名称是否存在
			boolean isExist=service.isExistByLbmc(model);
			if(!isExist){
				int nextLbdm=service.getNextLbdm();
				model.setLbdm(nextLbdm+"");
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_LBDMEXIST));
				return null;
				
			}
		}
		
		return mapping.findForward("addLbdm");
	}
	
	
	/**
	 * 
	 * @描述:修改类别代码
	 * @作者：cq [工号：785]
	 * @日期：2014-6-20 下午04:45:06
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
	@SystemLog(description="访问日常事务-团学活动-代码维护-修改LBDM:{lbdm}")
	public ActionForward updateLbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhForm model = (TxhdDmwhForm) form;
		TxhdDmwhService service = new TxhdDmwhService();
		TxhdDmwhForm myModel = service.getModel(model);
		
		if (UPDATE.equalsIgnoreCase(model.getType())){
			//判断有没修改项目类别名称
			if(!model.getLbmc().trim().equals(myModel.getLbmc().trim())){
				//判断结果中是否已使用
				String checkLbdmForJg=service.checkLbForJg(model.getLbdm());
				//判断项目当中是否已使用
				String checkLbdmForXmwh=service.checkLbForXmwh(model.getLbdm());
				
				if(!checkLbdmForJg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.RCSW_TXHD_LBDMEXIST_JG_UPDATE,checkLbdmForJg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				
				if(!checkLbdmForXmwh.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.RCSW_TXHD_LBDMEXIST_XMWH_UPDATE,checkLbdmForXmwh);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}else{
				//没有修改名称直接保存
				model.setLbmc(myModel.getLbmc().trim());
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			boolean isExist=service.isExistByLbmc(model);
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_LBDMEXIST));
				return null;
			  
			}
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myModel));
		
		return mapping.findForward("updateLbdm");
		
	}
	
	
	/**
	 * 
	 * @描述:删除类别代码
	 * @作者：cq [工号：785]
	 * @日期：2014-6-20 下午05:15:51
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
	@SystemLog(description="访问日常事务-团学活动-代码维护-删除VALUES:{values}")
	public ActionForward delLbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhService service = new TxhdDmwhService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			//判断结果中是否已使用
			String checkLbdmForJg=service.checkLbForJg(values);
			//判断项目当中是否已使用
			String checkLbdmForXmwh=service.checkLbForXmwh(values);
			
			if(!checkLbdmForJg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_TXHD_LBDMEXIST_JG_DEL,checkLbdmForJg);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			if(!checkLbdmForXmwh.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_TXHD_LBDMEXIST_XMWH_DEL,checkLbdmForXmwh);
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
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2015-9-17 下午03:24:20
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
	public ActionForward getHdgglist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhForm model = (TxhdDmwhForm) form;
		TxhdDmwhService service = new TxhdDmwhService();
		
		if (QUERY.equals(model.getType())){
			String hdggmc = request.getParameter("hdggmc"); 
			hdggmc = URLDecoder.decode(URLDecoder.decode(hdggmc,"UTF-8"),"UTF-8");
			model.setHdggmc(hdggmc);
			List<HashMap<String,String>> resultList = service.getHdggList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_txhd_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("hdgglist");
	}
	
	//增加活动规格
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addHdgg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhForm model = (TxhdDmwhForm) form;
		TxhdDmwhService service = new TxhdDmwhService();
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getHdggList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_txhd_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("addHdgg");
	}
	
	//保存增加活动规格
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveAddNewHdgg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhForm model = (TxhdDmwhForm) form;
		TxhdDmwhService service = new TxhdDmwhService();
		String message = null;
		//活动规格重复验证
		if(service.checkIsExits(model)){
			message = MessageUtil.getText(MessageKey.RCSW_TXHD_DMWH_HDGG_REPEAT,model.getHdggmc());;
			response.getWriter().print(getJsonMessage(message));
		}else{
			boolean result = service.saveHdgg(model);
			message = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
		}
		return null;
	}
	
	//修改活动规格
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateHdgg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhForm model = (TxhdDmwhForm) form;
		request.setAttribute("hdggmc", model.getHdggmc());
		request.setAttribute("hdggdm", model.getHdggdm());
		return mapping.findForward("updateHdgg");
	}
	
	//保存修改活动规格
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveUpdateNewHdgg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		TxhdDmwhForm model = (TxhdDmwhForm) form;
		TxhdDmwhService service = new TxhdDmwhService();
		String message = null;
		//活动规格重复验证
		if(service.checkIsExits(model)){
			message = MessageUtil.getText(MessageKey.RCSW_TXHD_DMWH_HDGG_REPEAT,model.getHdggmc());;
			response.getWriter().print(getJsonMessage(message));
		}else{
			boolean result = service.saveUpdateHdgg(model);
			message = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
		}
		return null;
	}
	
	//删除活动规格
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delhdgg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		TxhdDmwhService service = new TxhdDmwhService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			int num = service.delHdgg(values.split(","));
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
