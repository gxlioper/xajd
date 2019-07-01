/**
 * @部门:学工产品事业部
 * @日期：2017-3-9 上午09:39:56 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmlx;

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
 * @类功能描述: 代码维护_项目类型
 * @作者：Meng.Wei[工号:1186]
 * @时间： 2017-3-9 上午09:39:56 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmlxAction extends SuperAction{
	private static final String url = "xpjpy_dmwh.do";
	
	/**
	 * @描述: 查询数据并返回页面
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-9 下午07:28:14
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
	public ActionForward listXmlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XmlxForm model = (XmlxForm) form;
		XmlxService service = new XmlxService();
		
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "xpjpy_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("listXmlx");
	}
	
	/**
	 * @描述: 增加页面
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-9 下午07:27:21
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
	public ActionForward addXmlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmlxForm model = (XmlxForm) form;
		/*返回path*/
		String path = "xpjpy_dmwh.do?method=addXmlx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		if (model != null) {
			BeanUtils.copyProperties(model, StringUtils.formatData(model));
		}
		return mapping.findForward("addXmlx");
	}
	
	/** 
	 * @描述: 增加保存方法
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-9 下午07:31:36
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
	@SystemLog(description="访问新评奖评优-基本设置-代码维护-评奖项目类型增加LXMC:{lxmc} ")
	public ActionForward saveForAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmlxForm model = (XmlxForm) form;
		XmlxService service = new XmlxService();
		
		boolean isExist = service.isExistLxmc(model);
		
		if(!isExist){
			boolean result = service.runInsert(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_XMLX_LXEXIST));
		}
		return null;
	}
	
	/**
	 * @描述: 修改返回页面
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-10 上午11:02:50
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
	public ActionForward updateXmlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmlxForm model = (XmlxForm) form;
		XmlxService service = new XmlxService();
		XmlxForm myForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("updateXmlx");
	}
	
	/**
	 * @描述: 修改保存方法
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-10 上午11:19:36
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
	@SystemLog(description="访问评奖评优-基本设置-代码维护-评奖项目类型修改-LXDM：{lxdm},LXMC:{lxmc}")
	public ActionForward saveForUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmlxForm model = (XmlxForm) form;
		XmlxService service = new XmlxService();
		XmlxForm myForm = service.getModel(model);
			//判断有没修改项目类型名称
			if(!model.getLxmc().trim().equals(myForm.getLxmc().trim())){
				//判断评奖结果库当中是否已使用
				String checkLxmcForPjjg=service.checkLxForPjjg(model.getLxdm());
				//判断评奖项目当中是否已使用
				String checkLxmcForPjxm=service.checkLxForPjxm(model.getLxdm());
				if(!checkLxmcForPjjg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJJG_UPDATE,checkLxmcForPjjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				if(!checkLxmcForPjxm.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJXM_UPDATE,checkLxmcForPjxm);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}else{
				//没有修改名称直接保存
				model.setLxmc(model.getLxmc().trim());
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			model.setLxmc(model.getLxmc().trim());
			boolean isExist=service.isExistLxmc(model);
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_XMLX_LXEXIST));
			}
		return null;
	}
	
	/**
	 * @描述: 删除方法
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-9 下午08:10:03
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
	@SystemLog(description="访问评奖评优-基本设置-代码维护-项目类型删除-VALUES：{values}")
	public ActionForward delXmlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmlxService service = new XmlxService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			//判断评奖结果库当中是否已使用
			String checkLxmcForPjjg = service.checkLxForPjjg(values);
			//判断评奖项目当中是否已使用
			String checkLxmcForPjxm = service.checkLxForPjxm(values);
			if(!checkLxmcForPjjg.trim().equals("")){
				String message = MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJJG_DELETE,checkLxmcForPjjg);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			if(!checkLxmcForPjxm.trim().equals("")){
				String message = MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJXM_DELETE,checkLxmcForPjxm);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}else{
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
}
