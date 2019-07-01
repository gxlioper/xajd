/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-2 ����02:15:53 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @�๦������: ��չ��Ϣ
 * @���ߣ� zhangxiaobin[����:1036]
 * @ʱ�䣺 2015-6-2 ����02:15:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
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
	 * @����:��ȡ��չ�ֶ�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-10 ����03:01:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
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
	 * @����:��ȡ����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-10 ����03:01:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
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
	 * @����:�ύ����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-10 ����03:02:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward submitData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		return null;
	} 
	
	/**
	 * 
	 * @����: ��ȡѧ��������Ϣ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-11 ����02:09:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward getStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ExtendActionForm extendForm = (ExtendActionForm) form;
		// ѧ����Ϣ
		if (!StringUtil.isNull(extendForm.getXh())){
			//����ѧ��������Ϣ
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
	 * @����: ��ȡ��������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-23 ����11:54:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
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
	 * @����: У�����ݵĺϷ���
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-16 ����09:43:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
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
