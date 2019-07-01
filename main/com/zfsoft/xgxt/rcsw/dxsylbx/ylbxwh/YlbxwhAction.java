/**
 * @部门:学工产品事业部
 * @日期：2014-1-6 上午10:51:30
 */  
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxwh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 医疗保险维护管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：Dlq[工号:995]
 * @时间： 2014-1-6 上午10:50:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YlbxwhAction extends SuperAction {
	
	private static final String url = "rcsw_dxsylbx_ylbxwh.do";
	
	/**
	 * 
	 * @描述:TODO(财政全额补助人员身份)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午01:53:13
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
	public ActionForward czqebzlxListManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhForm model = (YlbxwhForm) form;
		YlbxwhService service = new YlbxwhService();
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getCzqebzlxPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_dxsylbx_ylbxwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("czqebzlxListManage");
		
	}
	
	/**
	 * 
	 * @描述:TODO(参保状况维护列表)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午02:11:13
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
	public ActionForward cbzklxListManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhForm model = (YlbxwhForm) form;
		YlbxwhService service = new YlbxwhService();
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getCbzklxPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_dxsylbx_ylbxwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cbzklxListManage");
		
	}
	
	/**
	 * 
	 * @描述:TODO(增加财政全额补助人员身份 )
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午11:31:39
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
	@SystemLog(description="访问日常事务-大学生医疗保险-医疗保险维护-补助类型-增加CZQEBZMC:{czqebzmc}")
	public ActionForward addCzqebzlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhForm model = (YlbxwhForm) form;
		YlbxwhService service = new YlbxwhService();
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.saveCzqebzlxInfo(model, "add");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String czqebzdm = service.changeCzqebzlxdm();
		model.setCzqebzdm(czqebzdm);
		request.setAttribute("model", model);
		return mapping.findForward("addCzqebzlx");
		
	}
	
	/**
	 * 
	 * @描述:TODO(修改财政全额补助人员身份 )
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 上午11:31:09
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
	@SystemLog(description="访问日常事务-大学生医疗保险-医疗保险维护-补助类型-修改CZQEBZDM:{czqebzdm},CZQEBZMC:{czqebzmc}")
	public ActionForward updateCzqebzlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhService service = new YlbxwhService();
		YlbxwhForm myForm = (YlbxwhForm) form;
		YlbxwhForm model = service.getCzqebzlxForm(myForm,myForm.getCzqebzdm());
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.saveCzqebzlxInfo(myForm, "update");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("updateCzqebzlx");
		
	}
	
	
	
	/**
	 * 
	 * @描述:TODO(删除全额补助人员身份)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午05:44:04
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
	@SystemLog(description="访问日常事务-大学生医疗保险-医疗保险维护-补助类型-删除VALUES:{values}")
	public ActionForward delCzqebzlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhService service = new YlbxwhService();	
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteCzqebzlxwhInfo(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}
	
	/**
	 * 
	 * @描述:TODO(增加参保状况类型)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午06:03:28
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
	@SystemLog(description="访问日常事务-大学生医疗保险-医疗保险维护-参保状况类型-增加CBZKMC:{cbzkmc}")
	public ActionForward addCbzklx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhForm model = (YlbxwhForm) form;
		YlbxwhService service = new YlbxwhService();
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.saveCbzklxInfo(model, "add");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String cbzkdm = service.changeCbzklxdm();
		model.setCbzkdm(cbzkdm);
		request.setAttribute("model", model);
		return mapping.findForward("addCbzklx");
		
	}
	

	/**
	 * 
	 * @描述:TODO(修改参保状况类型 )
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-7 上午11:01:39
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
	@SystemLog(description="访问日常事务-大学生医疗保险-医疗保险维护-参保状况类型-修改CBZKDM:{cbzkdm},CBZKMC:{cbzkmc}")
	public ActionForward updateCbzklx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhService service = new YlbxwhService();
		YlbxwhForm myForm = (YlbxwhForm) form;
		YlbxwhForm model = service.getCbzklxForm(myForm,myForm.getCbzkdm());
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.saveCbzklxInfo(myForm, "update");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("updateCbzklx");
		
	}
	
	
	/**
	 * 
	 * @描述:TODO(删除参保状况类型)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-6 下午05:44:04
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
	@SystemLog(description="访问日常事务-大学生医疗保险-医疗保险维护-参保状况类型-删除VALUES:{values}")
	public ActionForward delCbzklx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxwhService service = new YlbxwhService();	
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delCbzklxwhInfo(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}
	
	
}
