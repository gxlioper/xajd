package com.zfsoft.xgxt.xszz.knsdc;

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
import com.zfsoft.xgxt.xszz.knsjcsz.JcszService;



/**
 * 档次维护Action
 * @author maxh
 * 2013-4-17
 */
@SuppressWarnings("rawtypes")
public class KnsdcAction extends SuperAction {

	private static final String url = "xszz_knsdc_wh.do";
	
	/**
	 * 档次维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward dcwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsdcForm model = (KnsdcForm) form;
		KnsdcService service = new KnsdcService();
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("knsdcList");
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-困难生认定-档次维护-增加-DCMC:{dcmc}")
	public ActionForward addKnsdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsdcForm model = (KnsdcForm) form;
		KnsdcService service = new KnsdcService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//判断档次名称是否存在
			boolean isExist=service.isExistByKnsdc(model,SAVE);
			if(!isExist){
				int nextDcdm=service.getNextDcdm();
				model.setDcdm(nextDcdm+"");
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_KNSDC_REPEAT));
				return null;
				
			}
		}
		
		return mapping.findForward("addKnsdc");
	}
	
	/**
	 * 
	 * @描述:修改困难档次
	 * @作者：maxh
	 * @日期：2013-4-18 上午10:01:23
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
	@SystemLog(description="访问学生资助-困难生认定-档次维护-修改-DCDM:{dcdm},DCMC:{dcmc}")
	public ActionForward updateKnsdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsdcForm myForm = (KnsdcForm) form;
		KnsdcService service = new KnsdcService();
		
		KnsdcForm model = new KnsdcForm();
		String oldDcdm = request.getParameter("oldDcdm");
		if(StringUtils.isNull(oldDcdm)){
			model = service.getModel(myForm);
		}else{
			KnsdcForm oldForm = new KnsdcForm();
			oldForm.setDcdm(oldDcdm);
			model = service.getModel(oldForm);
		}
		
		if (UPDATE.equalsIgnoreCase(myForm.getType())){
			//判断有没修改档次名称
			if(!myForm.getDcmc().trim().equals(model.getDcmc().trim())
					|| !myForm.getDcdm().trim().equals(model.getDcdm().trim())){
				//检验困难生结果中是否有被修改的档次
				String checkDcmcForKnsjg=service.checkDcForKnsjg(model.getDcdm());
				//检验困难生审核中是否有被修改的档次
				String checkDcmcForKnssh=service.checkDcForKnssh(model.getDcdm());
				if(!checkDcmcForKnsjg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.XSZZ_KNSDC_EXIST_KNSJG_UPDATE,checkDcmcForKnsjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				
				if(!checkDcmcForKnssh.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.XSZZ_KNSDC_EXIST_KNSSH_UPDATE,checkDcmcForKnssh);
					response.getWriter().print(getJsonMessage(message));
					return null;
					
				}
			}
			//判断档次名称是否存在
			boolean isExist=service.isExistByKnsdc(myForm,UPDATE);
			if(!isExist){
				boolean result =   service.updateKnsdcInfo(model.getDcdm(), myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_KNSDC_REPEAT));
				return null;
			  
			}
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("oldDcdm", myForm.getDcdm());
		return mapping.findForward("updateKnsdc");
	}
	/**
	 * 
	 * @描述:删除
	 * @作者：maxh
	 * @日期：2013-4-18 下午04:17:51
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
	@SystemLog(description="访问学生资助-困难生认定-档次维护-删除-VALUES:{values}")
	public ActionForward delKnsdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsdcService service = new KnsdcService();
		
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			//检验困难生结果中是否有被删除的档次
			String checkDcmcForKnsjg=service.checkDcForKnsjg(values);
			//检验困难生审核中是否有被删除的档次
			String checkDcmcForKnssh=service.checkDcForKnssh(values);
			if(!checkDcmcForKnsjg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.XSZZ_KNSDC_EXIST_KNSJG_DELETE,checkDcmcForKnsjg);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			if(!checkDcmcForKnssh.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.XSZZ_KNSDC_EXIST_KNSSH_DELETE,checkDcmcForKnssh);
				response.getWriter().print(getJsonMessage(message));
				return null;
				
			}
			JcszService jcszService=new JcszService();
			if(jcszService.getKnsrsszfs().equals("1")){
				jcszService.delKnsrssz(values);
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
	@SystemAuth(url = url)
	public ActionForward knyyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsdcForm model = (KnsdcForm) form;
		KnsdcService service = new KnsdcService();
		
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.knyyList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("knyyList");
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-困难生认定-困难原因维护-增加-yymc:{yymc}")
	public ActionForward addKnyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsdcForm model = (KnsdcForm) form;
		KnsdcService service = new KnsdcService();
		if (SAVE.equalsIgnoreCase(model.getType())){
			//判断档次名称是否存在
			boolean isExist=service.checkKnyy(model);
			if(!isExist){
				boolean result = service.saveKnyy(model,"add");
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_KNYY_REPEAT));
				return null;
				
			}
		}
		
		return mapping.findForward("addKnyy");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-困难生认定-困难原因维护-修改-YYDM:{yydm}")
	public ActionForward updateKnyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsdcForm myForm = (KnsdcForm) form;
		KnsdcService service = new KnsdcService();
		KnsdcForm model = new KnsdcForm();
			model = service.getKnyy(myForm);
		if (UPDATE.equalsIgnoreCase(myForm.getType())){
			//判断困难原因是否存在
			boolean isExist=service.checkKnyy(myForm);
			if(!isExist){
				boolean result =   service.saveKnyy( myForm,"update");
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_KNYY_USED));
				return null;
			}
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateKnyy");
	}
	/**
	 * 
	 * @描述:删除
	 * @作者：maxh
	 * @日期：2013-4-18 下午04:17:51
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
	@SystemLog(description="访问学生资助-困难生认定-困难原因维护-删除-VALUES:{values}")
	public ActionForward delKnyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsdcService service = new KnsdcService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)){
			
			boolean flag = service.checkKnyyUsed(values.split(","));
			if(flag){
				String message=MessageUtil.getText(MessageKey.XSZZ_KNYY_USED);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			int num = service.deleteKnyyInfo(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}
	
}
