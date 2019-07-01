/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.szdw.sygl;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.UniqID;

public class SyglAction extends SuperAction{
	private static final String url = "xg_xtwh_syglwh.do";
	SyglService service =new SyglService();
	/**
	 * @description	： 书院列表
	 * @author 		： CP（1352）
	 * @date 		：2018-1-23 下午05:42:03
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward syList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SyglForm myForm = (SyglForm) form;
		if(QUERY.equals(myForm.getType())){		
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("syList");
	}
	/**
	 * @description	： 增加书院
	 * @author 		： CP（1352）
	 * @date 		：2018-1-24 上午09:19:11
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward syadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SyglForm myForm = (SyglForm) form;
		if("save".equalsIgnoreCase(myForm.getType())){
			if(service.isExist(myForm)){
				String messageKey = MessageKey.SYS_TOKEN_VALID;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
			}
			String sydm = UniqID.getInstance().getUniqIDHash();
			myForm.setSydm(sydm);
			boolean result = service.runInsert(myForm);
			String messageKey=result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", MessageUtil.getText(messageKey));
			map.put("sydm", myForm.getSydm());
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			return null;
		}
		return mapping.findForward("syadd");
	}
	/**
	 * @description	： 修改书院
	 * @author 		： CP（1352）
	 * @date 		：2018-1-24 上午09:33:34
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward syupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SyglForm myForm = (SyglForm) form;
		SyglForm updateForm = service.getModel(myForm);
		if("update".equalsIgnoreCase(myForm.getType())){
			if(service.isExist(myForm)){
				String messageKey = MessageKey.SYS_TOKEN_VALID;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
			}
			boolean result = service.runUpdate(myForm);
			String messageKey=result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(updateForm));
		return mapping.findForward("syupdate");
	}
	
	/**
	 * @description	： 删除
	 * @author 		： CP（1352）
	 * @date 		：2018-1-24 上午09:34:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward sydel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			boolean flag = service.isHaveBj(values);
			boolean result =false;
			String message ="";
			if (!flag) {
				 result = service.syDelete(values);
				 message = result ? MessageUtil.getText(
						 MessageKey.SYS_DEL_SUCCESS) : MessageUtil
						 .getText(MessageKey.SYS_DEL_FAIL);
			}else {
				message="该书院已经分配班级，不可删除！";
			}
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date 		：2018-1-24 下午03:57:46
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward bjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyglForm model = (SyglForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getBjList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("sydm",model.getSydm());
		String path = "xtwh_syglwh.do?method=bjManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjManage");
	}
	
	/**
	 * @description	： 分配
	 * @author 		： CP（1352）
	 * @date 		：2018-1-24 下午05:20:37
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveFp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sydm= request.getParameter("sydm");
		String bjdms = request.getParameter("bjdms");
		String[] bjdmarr = bjdms.split(",");
		boolean result = service.bjFp(sydm, bjdmarr);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * @description	： 取消分配
	 * @author 		： CP（1352）
	 * @date 		：2018-1-24 下午05:20:26
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelFp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sydm= request.getParameter("sydm");
		String bjdms = request.getParameter("bjdms");
		String[] bjdmarr = bjdms.split(",");
		boolean result = service.bjQxfp(sydm, bjdmarr);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
