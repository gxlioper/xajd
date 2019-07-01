/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-18 ����04:42:46 
 */  
package com.zfsoft.xgxt.dagl.daxxgl;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dagl.qdcl.DaqdclService;
import com.zfsoft.xgxt.dagl.qdmb.DaqdmbService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������ģ��
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2014-2-18 ����04:42:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DaxxglAction extends SuperAction {
	
	
	private static List<HashMap<String, String>> jbxxList = null;
	/**
	 * ��ʾ��ҵȥ��FLG��"1"
	 */
	private static String XSBYQX =  "1";
	/**
	 * ά��״̬����ά��
	 */
	private static String YWHWHZT =  "��ά��";
	
	private static final String url = "daxxgl.do?method=daxxglList";
	
	/**
	 * ��ѯ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward daxxglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DaxxglService service = new DaxxglService();
		DaxxglForm myForm = (DaxxglForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "daxxgl.do?method=daxxglList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("daxxglList");
	}
	
	
	/**
	 * ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��������-������Ϣ����-����XH:{xh},DAZRSJ:{dazrsj},ZRFSBH:{zrfsbh}")
	public ActionForward addDaxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaxxglService service = new DaxxglService();
		DaxxglForm myForm = (DaxxglForm) form;
		if (SAVE.equalsIgnoreCase(myForm .getType())){
			//����ʱ������ͻ(xh��dazrsj��������)
			String pk = myForm.getXh()+myForm.getDazrsj();
			HashMap<String, String> values = service.getDaxxInfoByPk(pk);
			if(values!=null && values.size()>0){
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSXX_DAXX_REPEAT_ERROR));
				return null;
			}
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		User user = getUser(request);
		//����ѧ��������Ϣ
		String xh = "";
		if(user.getUserStatus().equalsIgnoreCase("stu")){
			xh = user.getUserName();
		}else{
			xh = myForm.getXh();
		}	
		this.setXsxxInfo(request, xh);
		request.setAttribute("path", "daxxgl.do?method=addDaxxgl");
		return mapping.findForward("addDaxxgl");
	}
	/**
	 * �޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��������-������Ϣ����-�޸�XH:{xh},DAZRSJ:{dazrsj},ZRFSBH:{zrfsbh}")
	public ActionForward updateDaxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaxxglService service = new DaxxglService();
		DaxxglForm myForm = (DaxxglForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.updateDaxxgl(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		HashMap<String, String> values = service.getDaxxTableByPk(myForm.getPk());
		DaxxglForm model = (DaxxglForm) myForm.getClass().getConstructor().newInstance();
		if (!values.isEmpty()){
			for(Map.Entry<String,String> entry : values.entrySet()){
				String property = entry.getKey();
				String value = entry.getValue();
				Method setMethod = model.getClass().getMethod("set"+property.substring(0, 1).toUpperCase()+property.substring(1),String.class);
				setMethod.invoke(model, value);
			}
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		this.setXsxxInfo(request, myForm.getXh());
		return mapping.findForward("updateDaxxgl");
	}
	
	
	
	/**
	 * ɾ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��������-������Ϣ����-ɾ��PKS:{pks}")
	public ActionForward delDaxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaxxglService service = new DaxxglService();
		
		String pks = request.getParameter("pks");
		
		if (!StringUtil.isNull(pks)){
			int num = service.delDaxxgl(pks.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}
	
	/**
	 * �鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewDaxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaxxglService service = new DaxxglService();
		DaxxglForm myForm = (DaxxglForm) form;
		// pk:ѧ��+����ת��ʱ��
		String pk = myForm.getPk();
		HashMap<String, String> daxxInfo = service.getDaxxInfoByPk(pk);
		
		// �趨ѧ��������Ϣ���Ƿ���ʾ��ҵȥ���ж�
		request.setAttribute("rs", StringUtils.formatData(daxxInfo));
		String xh = daxxInfo.get("xh");
		setXsxxInfo(request, xh);
		
		String whzt = daxxInfo.get("whzt");
		request.setAttribute("whzt", whzt);
		
		//����ά�������嵥����ȡ��ģ������Ĳ���
		if(YWHWHZT.equals(whzt)){

			// ����ģ��ID
			String daqdmb_id = daxxInfo.get("daqdmb_id");
			
			// ȡ��ģ������Ĳ���
			List<HashMap<String, String>> mbnclList = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> mbwclList = new ArrayList<HashMap<String, String>>();
			if(StringUtils.isNotNull(daqdmb_id)){
				mbnclList = service.getXsdaclListByBmid(daqdmb_id,pk);//ѧ������ģ���ڲ���
			}
			mbwclList = service.getXsMbwclListByBmid(daqdmb_id,pk);//ѧ��������ģ�������
			request.setAttribute("mbnclList", mbnclList);
			request.setAttribute("mbwclList", mbwclList);
		}
		return mapping.findForward("viewDaxxgl");
	}

	/**
	 * �����嵥ά�����󶨲��ϣ�-δά����ѧ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SuppressWarnings("deprecation")
	@SystemLog(description="����ѧ����Ϣ-��������-������Ϣ����-�����嵥ά��XH:{xh}")
	public ActionForward bandXsqd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaxxglService service = new DaxxglService();
		DaxxglForm myForm = (DaxxglForm) form;
		DaqdmbService daqdbmSv = new DaqdmbService();
		DaqdclService daqdclSv = new DaqdclService();
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			
			String xh = request.getParameter("xh");
			String dazrsj = request.getParameter("dazrsj");
			String daqdmb_id = request.getParameter("daqdmb_id");
			String jsonStr = request.getParameter("json");
			
			JSONArray jsonArray = new JSONArray(jsonStr);
			//����XG_XSXX_DAGL_DAXXB������Ϣ��
			boolean result = service.updateDaxxgl(daqdmb_id, xh+dazrsj);
			
			if(result){
				//����XG_XSXX_DAGL_XSDACLBDBѧ���������ϰ󶨱�
				result = service.saveXsdaxxBand(xh,dazrsj,jsonArray);
			}
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		// pk:ѧ��+����ת��ʱ��
		String pk = myForm.getPk();
		
		// ѧ��������Ϣ
		HashMap<String, String> daxxInfo = service.getDaxxInfoByPk(pk);
		
		// ������Ϣ����
		if(daxxInfo!=null && daxxInfo.size()>0){
			
			// ȡ��ѧ��������Ϣ
			String xh = daxxInfo.get("xh");
			setXsxxInfo(request, xh);
			request.setAttribute("rs", StringUtils.formatData(daxxInfo));		
		}

		// ѡ���ģ��ID
		String daqdmb_id = myForm.getDaqdmb_id();
		List<HashMap<String, String>> mbclList = new ArrayList<HashMap<String, String>>();			
		if(StringUtils.isNotNull(daqdmb_id)){
			mbclList = service.getXsdaclListByBmid(daqdmb_id,pk);//ѧ������ģ���ڲ���
		}
		request.setAttribute("mbclList", mbclList);
		List<HashMap<String, String>> mbwclList = new ArrayList<HashMap<String, String>>();
		mbwclList = service.getXsMbwclListByBmid(daqdmb_id,pk);//ѧ������ģ�������
		request.setAttribute("mbwclList", mbwclList);
		request.setAttribute("daqdmb_id", daqdmb_id);
		
		request.setAttribute("mbList", daqdbmSv.getDaqdmbList());//ģ��������
		request.setAttribute("clList", daqdclSv.getDaqdclAllList());//����������
		return mapping.findForward("bandXsqd");
	}
	
	/**
	 * �����嵥����ά�����󶨲��ϣ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SuppressWarnings("deprecation")
	@SystemLog(description="����ѧ����Ϣ-��������-������Ϣ����-���������嵥ά��XH:{xh}")
	public ActionForward bandXsqdBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaxxglService service = new DaxxglService();
		DaxxglForm myForm = (DaxxglForm) form;
		DaqdmbService daqdbmSv = new DaqdmbService();
		DaqdclService daqdclSv = new DaqdclService();
		User user = getUser(request);

		String selected = request.getParameter("selected");
		request.setAttribute("selected", selected);
		
		// ������������
		if (SAVE.equalsIgnoreCase(myForm.getType())){

			// �������ϰ���Ϣ
			String jsonStr = request.getParameter("json");
			String daqdmb_id = request.getParameter("daqdmb_id");

			// ȫѡ�����
			if("all".equals(selected)){	

				// ���ɸ߼���ѯ����
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				searchModel.setPath("daxxgl.do?method=daxxglList");
				myForm.setSearchModel(searchModel);
			}
			JSONArray jsonArray = new JSONArray(jsonStr);
			
			//����XG_XSXX_DAGL_DAXXB������Ϣ��
			boolean  result = service.updateBatchDaxxgl(daqdmb_id, jsonArray, myForm, user);
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("daxxgl.do?method=daxxglList");
		myForm.setSearchModel(searchModel);
		
		//�趨VALUE����ѡ��ѧ����
		String values = request.getParameter("values");
		values = myForm.getValues();
		request.setAttribute("values", values);
		HashMap<String, String> dabdxx = service.getDabdxx(myForm, user);
		
		//��ά�������嵥ѧ����
		request.setAttribute("ywhdaqdxss", dabdxx!=null && dabdxx.size() > 0? dabdxx.get("ywhdaqdxss") : 0);
		
		if(StringUtils.isNotNull(values)){
			
			// ѡ��ѧ������
			request.setAttribute("yxzxss", values.split(",").length);
		}else{			
			// ȫѡ�����
			request.setAttribute("yxzxss", dabdxx!=null && dabdxx.size() > 0? dabdxx.get("yxzxss") : 0);
		}
		
		String daqdmb_id = myForm.getDaqdmb_id();
		List<HashMap<String, String>> mbclList = new ArrayList<HashMap<String, String>>();
		if(StringUtils.isNotNull(daqdmb_id)){
			mbclList = daqdbmSv.getDaqdclListByMbid(daqdmb_id);
		}
		request.setAttribute("daqdmb_id", daqdmb_id);
		request.setAttribute("mbclList", mbclList);

		request.setAttribute("sysdate", DateUtils.getCurrDate());
		request.setAttribute("mbList", daqdbmSv.getDaqdmbList());//ģ��������
		request.setAttribute("clList", daqdclSv.getDaqdclAllList());//����������
		
		return mapping.findForward("bandXsqdBatch");
	}
	
	/**
	 * 
	 * @����:ѧ����Ϣ��ʾ���Ƿ���ʾ��ҵȥ����趨
	 * @���ڣ�2014-4-24 ����09:13:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param xh
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void setXsxxInfo(HttpServletRequest request,String xh) throws Exception{
		DaxxglService service = new DaxxglService();
		if(StringUtils.isNotNull(xh)){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		String sfxsbyqx = service.getXsszInfo();
		
		if(XSBYQX.equals(sfxsbyqx)){
			request.setAttribute("sfxsbyqx", sfxsbyqx); 
			request.setAttribute("byqxList", service.getByqxList());
		}

		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz("daxxgl");
		request.setAttribute("jbxxList", jbxxList);
	}
	
	/**
	 * @����:������Ϣ��ѯ�б���
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2014-2-19 ����4:16:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = "zxdk_gjdk_dksh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportDaxxData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DaxxglForm myForm=(DaxxglForm)form;
		DaxxglService service = new DaxxglService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm,user);
		
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
