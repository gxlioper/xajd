/**
 * @部门:学工产品事业部
 * @日期：2015-6-12 上午09:05:05 
 */  
package com.zfsoft.xgxt.xsxx.jcsjwh.ddwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import common.newp.StringUtil;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息-大队维护
 * @类功能描述: 浙江警察学院个性化大队维护功能 
 * @作者： ChenQ[工号:856]
 * @时间： 2015-6-12 上午08:59:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
@SuppressWarnings("unchecked")
public class DdwhAction extends SuperAction{
	
	private static final String url = "jcsj_ddwh.do";
	
   private DdwhService service = new DdwhService();
   
   @SystemAuth(url = url)
   public ActionForward ddwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	   DdwhForm myForm = (DdwhForm) form;
	   String path = "jcsj_ddwh.do";
	   request.setAttribute("path", path);
	   if(QUERY.equalsIgnoreCase(myForm.getType())){
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath(path);
			myForm.setSearchModel(searchModel);
			List<HashMap<String,String>> result = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(result);
			response.getWriter().print(dataList);
			return null;
	   }
	   FormModleCommon.commonRequestSet(request);
	   return mapping.findForward("ddwhList");
   }
   
   @SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
   @SystemLog(description="访问学生信息-大队维护-增加DDDM:{dddm},DDMC:{ddmc}")
   public ActionForward ddAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	   DdwhForm myForm = (DdwhForm) form;
	   if (SAVE.equalsIgnoreCase(myForm.getType())){
		   String messageKey = "";
		   if(!service.isHasExists(myForm)){
			   boolean flag = service.runInsert(myForm);
			   messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		   }else{
			   messageKey = MessageKey.SYS_SAVE_DM_REPEAT;
		   }		
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
	   return mapping.findForward("ddwhAdd");
   }
   
   @SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
   @SystemLog(description="访问学生信息-大队维护-修改:DDDM:{dddm},DDMC:{ddmc}")
   public ActionForward ddUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	   DdwhForm myForm = (DdwhForm) form;
	   if (UPDATE.equalsIgnoreCase(myForm.getType())){
		   boolean flag = service.runUpdate(myForm);
		   String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;		
		   response.getWriter().print(getJsonMessageByKey(messageKey));
		  return null;
		}
	   DdwhForm  model = service.getModel(myForm);
	   BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
	   return mapping.findForward("ddwhUpdate");
  }
   
   @SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
   @SystemLog(description="访问学生信息-大队维护-删除VALUES:{dddm}")
   public ActionForward ddDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
	   DdwhForm myForm = (DdwhForm) form;
	   String messageKey = service.runDelete(myForm);		
	   response.getWriter().print(getJsonMessageByKey(messageKey));
	   return null;
 }
   
   @SystemAuth(url = url)
   public ActionForward qdwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	   DdwhForm myForm = (DdwhForm) form;
	   String path = "zjjcddwh.do?method=qdwhList";
	   request.setAttribute("path", path);
	   if(QUERY.equalsIgnoreCase(myForm.getType())){
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath(path);
			myForm.setSearchModel(searchModel);
		   if(StringUtil.isNull(myForm.getFlag())){
				myForm.setFlag("0");
			}
			List<HashMap<String,String>> result = service.getQdList(myForm);
			JSONArray dataList = JSONArray.fromObject(result);
			response.getWriter().print(dataList);
			return null;
	   }
	   return mapping.findForward("qdwhList");
  }
   
   @SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问学生信息-区队维护-增加VALUES:{dddm}")
	public ActionForward qdAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DdwhForm myForm = (DdwhForm) form;
		String messageKey  = MessageKey.SYS_SAVE_FAIL;
		if(StringUtils.isNotNull(myForm.getDddm())){
			messageKey  = service.saveQd(myForm);
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
   @SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问学生信息-区队删除-删除VALUES:{dddm}")
	public ActionForward qdDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DdwhForm myForm = (DdwhForm) form;
		String messageKey  = MessageKey.SYS_DEL_SUCCESS;
		if(StringUtils.isNotNull(myForm.getDddm())){
			messageKey  = service.delQd(myForm);
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
   @SystemAuth(url = url)
	   public ActionForward viewQd(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		   DdwhForm myForm = (DdwhForm) form;
		   String path = "zjjcddwh.do?method=qdwhList";
		   request.setAttribute("path", path);
		   if(QUERY.equalsIgnoreCase(myForm.getType())){
				// 生成高级查询对象
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				myForm.setSearchModel(searchModel);
				myForm.setFlag("1");
				List<HashMap<String,String>> result = service.getQdList(myForm);
				JSONArray dataList = JSONArray.fromObject(result);
				response.getWriter().print(dataList);
				return null;
		   }
		   return mapping.findForward("view_QdList");
	  }
}
