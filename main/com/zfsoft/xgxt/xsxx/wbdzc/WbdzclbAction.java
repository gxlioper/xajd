/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 上午10:56:35 
 */  
package com.zfsoft.xgxt.xsxx.wbdzc;
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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;


		/**
		 * @系统名称: 学生工作管理系统
		 * @模块名称: 学生信息--报到注册（华师大）--未报到注册类别
		 * @类功能描述:  
		 * @作者： 孟威[工号:1186]
		 * @时间： 2016-3-16 上午09:28:46 
		 * @版本： V1.0
		 * @修改记录: 类修改者-修改日期-修改说明
		 */
		public class WbdzclbAction extends SuperAction {
			private static final String url = "xsxx_wbdlb.do";
		
		
		/**
		 * @描述:	未报到注册类型查询页面
		 * @作者：	孟威[工号：1186]
		 * @日期：	2016-3-16 上午09:29:42
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
		public ActionForward wbdzclbManage (ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception {
			WbdzclbForm model = ( WbdzclbForm ) form;
			WbdzclbService service = new WbdzclbService();
			if (QUERY.equals(model.getType())){
				//查询记录
				List<HashMap<String,String>> resultList = service.getPageList(model);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			String path = "xsxx_wbdlb.do";
			request.setAttribute("path", path);
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("WbdzclbManage");
		}

		
		/**
		 * @描述:	增加
		 * @作者：	孟威[工号：1186]
		 * @日期：	2016-3-16 上午09:35:11
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
		@SystemLog(description = "访问学生信息-报到注册-未报到注册类别-增加Wbdlbmc:{wbdlbmc}")
		public ActionForward addWbdzclb(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			WbdzclbForm model = (WbdzclbForm) form;
			WbdzclbService service = new WbdzclbService();		
			if (SAVE.equalsIgnoreCase(model.getType())){
				model.setWbdlbmc(StringUtil.trim(model.getWbdlbmc()));
				boolean result = service.saveLxInfo(model, "add");
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				
				return null;
			}
			String wbdlbdm = service.changeWbdlbdm();
			model.setWbdlbdm(wbdlbdm);
			
			request.setAttribute("model", StringUtils.formatData(model));
			return mapping.findForward("addWbdzclb");
		}
		
		
		/**
		 * @描述:	修改
		 * @作者：	孟威[工号：1186]
		 * @日期：	2016-3-16 上午09:36:00
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
		@SystemLog(description = "访问学生信息-报到注册-未报到注册类别-修改Wbdlbdm:{wbdlbdm},Wbdlbmc:{wbdlbmc}")
		public ActionForward updateWbdzclb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			WbdzclbForm myForm = (WbdzclbForm) form;
			WbdzclbService service = new WbdzclbService();		
			WbdzclbForm model = service.getModel(myForm);
			if (UPDATE.equalsIgnoreCase(myForm.getType())){	
				//判断有没修未报到类别次名称
				if(!myForm.getWbdlbmc().trim().equals(model.getWbdlbmc().trim())){
					String wbdlbmcpd = service.pdsfsy(myForm.getWbdlbdm());
					if(!wbdlbmcpd.trim().equals("")){
						String message=MessageUtil.getText(MessageKey.XSXX_WBDZC_UPDATE,wbdlbmcpd);
						response.getWriter().print(getJsonMessage(message));
						return null;
					}
				}else{
					//没有未报到类别名称直接保存
					myForm.setWbdlbmc(model.getWbdlbmc().trim());
					boolean result = service.runUpdate(myForm);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
				}
				//判断未报到类别名称是否存在
				boolean isExist = service.isExistByWbdlbdm(myForm, UPDATE);
				if(!isExist){
					boolean result = service.runUpdate(myForm);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
				}else{
					response.getWriter().print(getJsonMessageByKey(MessageKey.XSXX_WBDZC_WBDLBMC));
					return null;
				}
			}
			request.setAttribute("model", model);
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));			
			return mapping.findForward("updateWbdzclb");
		}
		
		
		/**
		 * @描述:	删除
		 * @作者：	孟威[工号：1186]
		 * @日期：	2016-3-16 上午09:37:48
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
		@SystemLog(description = "访问学生信息-报到注册-未报到注册类别-删除VALUES:{values}")
		public ActionForward delWbdzclb (ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
		throws Exception {
			WbdzclbService service = new WbdzclbService();
			String values = request.getParameter("values");
			if(!StringUtil.isNull(values)){
				//判断发放结果当中是否已使用
				String pdsfsy = service.pdsfsy(values);
				if(!pdsfsy.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_FYFF_DMWH_MCEXIST,pdsfsy);
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
