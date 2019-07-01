package com.zfsoft.xgxt.xszz.knsrdnew.knsrdzb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助管理模块
 * @类功能描述: 学生资助2013版 困难生认定
 * @作者： Penghui.Qu
 * @时间： 2013-4-20 上午11:38:31
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KnsrdzbAction extends SuperAction {
	
	public static final String COPY = "复制";  
	
	private static final String url = "xg_xszz_knsrd_knzb.do";

	/**
	 * 
	 * @描述:困难生申请管理
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 上午10:25:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward knsrdzbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsrdzbForm model = (KnsrdzbForm) form;
		KnsrdzbService service = new KnsrdzbService();
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service
					.getKnszbPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xg_xszz_knsrd_knzb.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("knsrdzbManage");
	}

	/**
	 * 
	 * @描述:TODO(增加困难生认定指标)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-21 下午02:31:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SuppressWarnings("deprecation")
	@SystemLog(description="访问学生资助-困难生认定-困难生认定指标-增加-JSON:{json}")
	public ActionForward addKnsrdzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdzbForm model = (KnsrdzbForm) form;
		KnsrdzbService service = new KnsrdzbService();

		if (SAVE.equalsIgnoreCase(model.getType())) {
			String zbmc = request.getParameter("zbmc");
			String zbdm = request.getParameter("zbdm");
			boolean check=service.checkZbmc(zbdm, zbmc);
			if(check){
				String jsonStr = request.getParameter("json");
				JSONArray jsonArray = new JSONArray(jsonStr);
				boolean result = service.saveKnszbInfo(jsonArray, zbmc);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}else{
				Map<String,String> data=new HashMap<String, String>();
				data.put("message", "已存在此指标名称！");
				response.getWriter().print(JSONObject.fromObject(data));
			}
			return null;
		}
		request.setAttribute("model", model);
		return mapping.findForward("addKnsrdzb");
	}
	
	/**
	 * 
	 * @描述:TODO(更新困难生认定指标)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 上午11:21:40
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
	@SuppressWarnings("deprecation")
	@SystemLog(description="访问学生资助-困难生认定-困难生认定指标-修改-ZBID:{zbid}")
	public ActionForward updateKnsrdzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdzbForm model = (KnsrdzbForm) form;
		KnsrdzbService service = new KnsrdzbService();
		
		
		Map<HashMap<String, String>, List<HashMap<String, String>>> object = new LinkedHashMap<HashMap<String,String>, List<HashMap<String,String>>>();
		String zbid = request.getParameter("zbid");
		model.setZbid(zbid);
		

		if (UPDATE.equalsIgnoreCase(model.getType())) {
			String jsonStr = request.getParameter("json");
			String existNum = request.getParameter("existNum");
			String zbmc = request.getParameter("zbmc");
			model = service.getModel(model);
			if(existNum.equalsIgnoreCase("0")){
				model.setZbmc(zbmc);
			}
			boolean check=service.checkZbmc(model.getZbid(), zbmc);
			if(check){
				//String zbmc = request.getParameter("zbmc");
				JSONArray jsonArray = new JSONArray(jsonStr);
				boolean result = service.updateKnszbInfo(jsonArray, model,existNum);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}else{
				Map<String,String> data=new HashMap<String, String>();
				data.put("message", "已存在此指标名称！");
				response.getWriter().print(JSONObject.fromObject(data));
			}
			return null;
		}
		/*request.setAttribute("knsrdzbsxList", knsrdzbsxList);
		request.setAttribute("knsrdzbnrList", knsrdzbnrList);*/
		model = service.getModel(model);
		List<HashMap<String, String>> knsrdzbsxList = new ArrayList<HashMap<String, String>>();
		//获取困难生认定指标属性集合
		knsrdzbsxList = service.getKnsrdzbsxList(zbid);
		//获取困难生认定指标内容集合
		List<HashMap<String, String>> knsrdzbnrList = new ArrayList<HashMap<String, String>>();
		//困难生认定指标内容集合联合困难生认定指标属性集合放到map 集合中 在前台页面回显
		for (HashMap<String, String> hm : knsrdzbsxList) {
			String sxid = hm.get("sxid"); 
			knsrdzbnrList = service.getKnsrdzbnrList(sxid);
			object.put(hm,knsrdzbnrList);
		}
		request.setAttribute("existNum", service.checkExistByKnsrdsq(zbid));
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("object", object);
		return mapping.findForward("updateKnsrdzb");
	}
	
	/**
	 * 
	 * @描述:TODO(启用困难生认定指标)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 上午11:21:21
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
	@SystemLog(description="访问学生资助-困难生认定-困难生认定指标-启用-ZBID:{zbid}")
	public ActionForward qyKnsrdzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdzbForm model = (KnsrdzbForm) form;
		KnsrdzbService service = new KnsrdzbService();
		boolean result = service.qyKnsrdzb(model.getZbid());
		String messageKey = result ? MessageKey.RCSW_KNSRDZB_QYSUCCESS : MessageKey.RCSW_KNSRDZB_QYFAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @描述:TODO(复制困难生认定指标)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 下午02:05:00
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
	@SystemLog(description="访问学生资助-困难生认定-困难生认定指标-复制-ZBID:{zbid}")
	public ActionForward copyKnsrdzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdzbForm model = (KnsrdzbForm) form;
		KnsrdzbService service = new KnsrdzbService();
		
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.copyKnsrdzb(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		KnsrdzbForm updateForm = service.getModel(model);
		updateForm.setZbmc(COPY+updateForm.getZbmc());
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		
		return mapping.findForward("copyKnsrdzb");
		
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生资助-困难生认定-困难生认定指标-删除-VALUES:{values}")
	public ActionForward delKnsrdzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsrdzbService service = new KnsrdzbService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteKnsrdzb(values.split(","));
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
	
	
	@SystemAuth(url = url)
	@SuppressWarnings("deprecation")
	public ActionForward viewKnsrdzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdzbForm model = (KnsrdzbForm) form;
		KnsrdzbService service = new KnsrdzbService();
		
		
		Map<HashMap<String, String>, List<HashMap<String, String>>> object = new LinkedHashMap<HashMap<String,String>, List<HashMap<String,String>>>();
		String zbid = request.getParameter("zbid");
		model.setZbid(zbid);
	
		
		model = service.getModel(model);
		List<HashMap<String, String>> knsrdzbsxList = new ArrayList<HashMap<String, String>>();
		//获取困难生认定指标属性集合
		knsrdzbsxList = service.getKnsrdzbsxList(zbid);
		//获取困难生认定指标内容集合
		List<HashMap<String, String>> knsrdzbnrList = new ArrayList<HashMap<String, String>>();
		//困难生认定指标内容集合联合困难生认定指标属性集合放到map 集合中 在前台页面回显
		for (HashMap<String, String> hm : knsrdzbsxList) {
			String sxid = hm.get("sxid"); 
			knsrdzbnrList = service.getKnsrdzbnrList(sxid);
			object.put(hm,knsrdzbnrList);
		}
		
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("object", object);
		return mapping.findForward("viewKnsrdzb");
	}

}
