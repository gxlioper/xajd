/**
 * @部门:学工产品事业部
 * @日期：2013-8-16 上午09:07:19 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm;

import java.util.ArrayList;
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
 * @模块名称: 评奖评优_代码维护（项目类型和性质）
 * @作者：CQ [工号：785]
 * @时间： 2013-8-16 上午09:07:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjdmAction extends SuperAction{
	
	private static final String urlJxj = "xpj_pjdm.do?method=xmlxList&xmxz=1&sindex=0";
	private static final String urlBz = "xpj_pjdm.do?method=xmlxList&xmxz=2&sindex=1";
	private static final String url="pj_dmwh.do";
	
	/**
	 * 
	 * 项目类型代码查看
	 * @作者：cq [工号：785]
	 * @日期：2013-8-16 上午11:08:51
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
	@SystemAuth(url = {urlJxj,urlBz})
	public ActionForward xmlxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjdmModel model = (PjdmModel) form;
		PjdmService service = new PjdmService();
		String xmxz = request.getParameter("xmxz");
		request.setAttribute("xmxz",xmxz);
		
		if (QUERY.equals(model.getType())){
			 xmxz = request.getParameter("xmxz");
			 model.setXmxz(xmxz);
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "pj_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("xmlxList");
	}
	
	/**
	 * 
	 * @描述:项目性质增加
	 * @作者：cq [工号：785]
	 * @日期：2013-8-16 上午11:23:35
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
	@SystemLog(description="访问评奖评优-基本设置-代码维护-评奖项目类型增加XMLXMC:{xmlxmc} ")
	public ActionForward addXmlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjdmModel model = (PjdmModel) form;
		PjdmService service = new PjdmService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//判断类型代码和名称是否存在
			model.setXmlxmc(model.getXmlxmc().trim());
			boolean isExist=service.isExistByXmlxmc(model, SAVE); 
			if(!isExist){
				int nextXmlxdm=service.getNextXmlxdm();
				model.setXmlxdm(nextXmlxdm+"");
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_XMLX_LXEXIST));
				return null;
				
			}
		}
		
		return mapping.findForward("addXmlx");
	}
	
	/**
	 * 
	 * @描述:项目性质修改
	 * @作者：cq [工号：785]
	 * @日期：2013-8-16 下午05:33:34
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
	@SystemLog(description="访问评奖评优-基本设置-代码维护-评奖项目类型修改-XMLXDM：{xmlxdm},XMLXMC:{xmlxmc}")
	public ActionForward updateXmlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjdmModel model = (PjdmModel) form;
		PjdmService service = new PjdmService();
		PjdmModel myForm = service.getModel(model);
		
		if (UPDATE.equalsIgnoreCase(model.getType())){
			//判断有没修改项目类型名称
			if(!model.getXmlxmc().trim().equals(myForm.getXmlxmc().trim())){
				//判断评奖结果库当中是否已使用
				String checkXmlxForPjjg=service.checkLxForPjjg(model.getXmlxdm());
				//判断评奖项目当中是否已使用
				String checkXmlxForPjxm=service.checkLxForPjxm(model.getXmlxdm());
				
				if(!checkXmlxForPjjg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJJG_UPDATE,checkXmlxForPjjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				
				if(!checkXmlxForPjxm.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJXM_UPDATE,checkXmlxForPjxm);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}else{
				//没有修改名称直接保存
				model.setXmlxmc(model.getXmlxmc().trim());
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			model.setXmlxmc(model.getXmlxmc().trim());
			boolean isExist=service.isExistByXmlxmc(model, UPDATE);
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_XMLX_LXEXIST));
				return null;
			  
			}
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("updateXmlx");
		
	}
	
	
	/**
	 * 
	 * @描述:项目性质删除
	 * @作者：cq [工号：785]
	 * @日期：2013-8-19 下午07:53:26
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
	@SystemLog(description="访问评奖评优-基本设置-代码维护-评奖项目类型删除-VALUES：{values}")
	public ActionForward delXmlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjdmService service = new PjdmService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			//判断评奖结果库当中是否已使用
			String checkXmlxForPjjg=service.checkLxForPjjg(values);
			//判断评奖项目当中是否已使用
			String checkXmlxForPjxm=service.checkLxForPjxm(values);
			if(!checkXmlxForPjjg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJJG_DELETE,checkXmlxForPjjg);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			if(!checkXmlxForPjxm.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJXM_DELETE,checkXmlxForPjxm);
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
