/**
 * @部门:学工产品事业部
 * @日期：2015-6-2 下午02:15:53 
 */  
package com.zfsoft.extend.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.extend.action.form.ExtendActionForm;
import com.zfsoft.extend.model.DateModuleConfig;
import com.zfsoft.extend.model.ExtendModule;
import com.zfsoft.extend.model.ExtendStoreData;
import com.zfsoft.extend.service.ExtendService;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @类功能描述: 扩展信息
 * @作者： zhangxiaobin[工号:1036]
 * @时间： 2015-6-2 下午02:15:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

@SuppressWarnings("unchecked")
public class DataExtendAction extends SuperAction {

	private static Log log = LogFactory.getLog(DataExtendAction.class);

	private static final String DEFAULE_XSJBXX_PZID = "Extend_Xsjbxx";
	
	private BdpzService bdpzService = new BdpzService();
	
	private ExtendService service = new ExtendService();
	
	public ActionForward queryDataModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ExtendActionForm extendForm = (ExtendActionForm) form;
		String extendModuleID = extendForm.getExtendModuleID();
		ExtendModule queryExtentModule = service.queryExtentModule(extendModuleID);
		response.getWriter().print(JSONObject.fromBean(queryExtentModule));
		return null;
	}
	
	public ActionForward queryDataModuleElements(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ExtendActionForm extendForm = (ExtendActionForm) form;
			
		return null;
	}

	/**
	 * 
	 * @描述:获取扩展字段配置
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-10 下午03:01:17
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
	public ActionForward obtainConfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ExtendActionForm extendForm = (ExtendActionForm) form;
		String extendModuleID = extendForm.getExtendModuleID();	
		DateModuleConfig obtainDataModuleConfig = service.obtainDataModuleConfig(extendModuleID);
		if(obtainDataModuleConfig != null){
			JSONObject generateJSONObject = obtainDataModuleConfig.generateJSONObject();
			response.getWriter().print(generateJSONObject);
		}else{
			response.getWriter().print(getJsonMessageResult("Module configuration not found!", false));
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:获取数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-10 下午03:01:35
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
	public ActionForward obtainData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ExtendActionForm extendForm = (ExtendActionForm) form;
		String dataId = extendForm.getDataId();
		String extendModuleID = extendForm.getExtendModuleID();
		String dataType = extendForm.getDataType();
		if(StringUtils.isBlank(dataId) || StringUtils.isBlank(extendModuleID) || StringUtils.isBlank(dataType)){
			response.getWriter().print(getJsonMessageResult("no data found", Boolean.FALSE));
			return null;
		}
		JSONArray dataQueryJSON = service.dataQueryJSON(dataId, extendModuleID, dataType);
		JSONObject returnValue = new JSONObject();
		returnValue.put("success", "true");
		returnValue.put("data", dataQueryJSON);
		response.getWriter().print(returnValue);
		return null;
	}
	
	/**
	 * 
	 * @描述:提交数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-10 下午03:02:12
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
	public ActionForward submitData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		return null;
	} 
	
	/**
	 * 
	 * @描述: 获取学生基本信息
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-11 下午02:09:04
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
	public ActionForward getStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ExtendActionForm extendForm = (ExtendActionForm) form;
		// 学生信息
		if (!StringUtil.isNull(extendForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(extendForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		String bdpzid = StringUtils.isBlank(extendForm.getBdpzid()) ? DEFAULE_XSJBXX_PZID : extendForm.getBdpzid();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(bdpzid);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("stuinfo");
	} 
	
	/**
	 * 
	 * @描述: 获取附加数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-23 上午11:54:35
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
	public ActionForward getPluginData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ExtendActionForm extendForm = (ExtendActionForm) form;
		String lx = extendForm.getLx();
		if(StringUtils.equals("ssx", lx)){
			response.getWriter().print(getSsxJson());
		}
		return null;
	}
	
	private String getSsxJson() throws Exception {
		String jsonStr = "";
		List<HashMap<String, String>> shList = service.extendPluginDAO.SH;
		List<HashMap<String, String>> qxList = service.extendPluginDAO.QX;
		List<HashMap> list = new ArrayList<HashMap>();
		List<HashMap> resultShiList = null;
		List<HashMap> resultXiList = null;
		HashMap resultShMap = null;
		HashMap resultShiMap = null;
		HashMap resultXiMap = null;
		for (HashMap<String, String> shMap : shList) {
			String shdm = shMap.get("dm");
			String shmc = shMap.get("mc");

			resultShMap = new HashMap();
			resultShiList = new ArrayList<HashMap>();
			resultShMap.put("treeNode", shmc);
			resultShMap.put("value", shdm);
			resultShMap.put("childNode", resultShiList);
			if(qxList == null || qxList.size() == 0){
				continue;
			}
			for (HashMap<String, String> qxMap : qxList) {
				String qxdm = qxMap.get("dm");
				String qxmc = qxMap.get("mc");
				if (qxdm == null || qxdm.length() < 6 || !qxdm.substring(0, 2).equals(shdm.substring(0, 2)) || qxdm.equals(shdm)) {
					continue;
				}
				if (qxdm.substring(4, 6).equals("00")) {
					resultXiList = new ArrayList<HashMap>();
					resultShiMap = new HashMap();
					resultShiMap.put("treeNode", qxmc);
					resultShiMap.put("value", qxdm);					
					resultShiMap.put("childNode", resultXiList);
					resultShiList.add(resultShiMap);
				} else {
					resultXiMap = new HashMap();
					resultXiMap.put("treeNode", qxmc);
					resultXiMap.put("value", qxdm);
					resultXiList.add(resultXiMap);
				}
			}
			list.add(resultShMap);
		}
		jsonStr = JSONArray.fromCollection(list).toString();
		return jsonStr;

	}
	
	/**
	 * 
	 * @描述: 校验数据的合法性
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-16 上午09:43:45
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
	public ActionForward validation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ExtendActionForm extendForm = (ExtendActionForm) form;
		String data = extendForm.getData();
		String mid  = extendForm.getExtendModuleID();
		JSONObject result;
		if(StringUtils.isNotBlank(data)){
			JSONArray jsonData = JSONArray.fromString(data);
			result = service.dataValidation(mid, jsonData);
		}else{
			result = getJsonMessageResult("No data for validation!", Boolean.TRUE);
		}
		response.getWriter().print(result);
		return null;
	}
	
	
	public ActionForward dataExtend(){
		log.info("Invoke DataExtendAction[dataExtend]");
		return null;
	}
	
	
}
