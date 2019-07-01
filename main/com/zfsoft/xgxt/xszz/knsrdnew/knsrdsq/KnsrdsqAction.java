/**
 * 
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-24 ����09:05:23  
 * 
 */  
package com.zfsoft.xgxt.xszz.knsrdnew.knsrdsq;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszForm;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszService;
import com.zfsoft.xgxt.xszz.knsrdnew.knsrdzb.KnsrdzbForm;
import com.zfsoft.xgxt.xszz.knsrdnew.knsrdzb.KnsrdzbService;
import org.apache.commons.beanutils.BeanUtils;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������϶�����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2014-1-24 ����09:05:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdsqAction extends SuperAction {
	//����ѧ���������϶��������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String KNSRD = "knsrd";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	private static final String OPEN = "1";
	
	private static final String url = "xg_xszz_knsrd_knsq.do";
	
	/**
	 * 
	 * @����:TODO(�������϶������б�)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����08:59:04
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
	@SystemAuth(url = url)
	public ActionForward knsrdsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdsqForm model = (KnsrdsqForm) form;
		KnsrdsqService service = new KnsrdsqService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}		
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		String path = "xg_xszz_knsrd_knsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("knsrdsqManage");
	}

	/**
	 * 
	 * @����:TODO(�����������϶�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-26 ����09:17:17
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SuppressWarnings("deprecation")
	@SystemLog(description="����ѧ������-�������϶�-�������϶�����-����-JSON:{json},XH:{xh}")
	public ActionForward addKnsrdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdsqForm model = (KnsrdsqForm) form;
		KnsrdsqService service = new KnsrdsqService();
		User user = getUser(request);
		JtqkdcForm jtqkModel = null;
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			JcszService jcszService = new JcszService();
			JcszForm jcszModel = jcszService.getModel();
			
			if (jcszModel != null && OPEN.equals(jcszModel.getSfwcjtdc()) ){
				JtqkdcService jtqkService = new JtqkdcService();
				JtqkdcForm jtqkForm = new JtqkdcForm();
				jtqkForm.setXh(model.getXh());
				jtqkModel = jtqkService.getModel(jtqkForm);
				
				request.setAttribute("openJtqk", jtqkModel == null);
			}
			
		}
		//��ȡ��������������
		JcszService jcszService=new JcszService();
		JcszForm jcszForm=jcszService.getModel();
		request.setAttribute("sqsftxdc", jcszForm.getSqsftxdc());
		request.setAttribute("isopen", jcszForm.getIsopen());
		JcszForm jcszModel = jcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		KnsrdzbForm knsrdzbForm = new KnsrdzbForm();
		KnsrdzbService knsrdzbService = new KnsrdzbService();
		Map<HashMap<String, String>, List<HashMap<String, String>>> object = new LinkedHashMap<HashMap<String,String>, List<HashMap<String,String>>>();
		String zbid = service.getKnsrdzbid();
		
		if(OPEN.equals(jcszModel.getSfwcjtdc())) {
//			if (!isTokenValid(request)){
//				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//				return null;
//			} else {
//				super.resetToken(request);
//			}
	        if ((jtqkModel != null && SAVE.equalsIgnoreCase(model.getType())) || (jtqkModel != null && SUBMIT.equalsIgnoreCase(model.getType())) ){
	        	model.setZbid(zbid);
	        	
	        	//Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
	        	boolean isExist= false;
	        	isExist = service.isExistByKnsrdsq(model);
	        	String jsonStr = request.getParameter("json");
				JSONArray jsonArray = new JSONArray(jsonStr);
	        	if(!isExist){
		        	//���ѧ��֤��������
	        		boolean result = service.saveKnsrdsq(jsonArray,model);
	        		String messageKey = "";
	        		if(SAVE.equalsIgnoreCase(model.getType())){
	        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
	        		}else{
	        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
	        		}
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
	        	}else{
	        		response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_KNSRDZB_KNSRDSQ_REPEAT));
					return null;
	        	}
			}
		}else {
//			if (!isTokenValid(request)){
//				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//				return null;
//			} else {
//				super.resetToken(request);
//			}
			if (SAVE.equalsIgnoreCase(model.getType()) || SUBMIT.equalsIgnoreCase(model.getType()) ){
	        	model.setZbid(zbid);
	        	
	        	//Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
	        	boolean isExist= false;
	        	isExist = service.isExistByKnsrdsq(model);
	        	String jsonStr = request.getParameter("json");
				JSONArray jsonArray = new JSONArray(jsonStr);
	        	if(!isExist){
		        	//���ѧ��֤��������
	        		boolean result = service.saveKnsrdsq(jsonArray,model);
	        		String messageKey = "";
	        		if(SAVE.equalsIgnoreCase(model.getType())){
	        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
	        		}else{
	        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
	        		}
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
	        	}else{
	        		response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_KNSRDZB_KNSRDSQ_REPEAT));
					return null;
	        	}
			}
		}
		String path = "xg_xszz_knsrd_knsqgl.do?method=addKnsrdsq";
		request.setAttribute("path", path);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("jbxxList", jbxxList);

		knsrdzbForm = knsrdzbService.getModel(knsrdzbForm);
		List<HashMap<String, String>> knsrdzbsxList = new ArrayList<HashMap<String, String>>();
		//��ȡ�������϶�ָ�����Լ���
		knsrdzbsxList = knsrdzbService.getKnsrdzbsxList(zbid);
		//��ȡ�������϶�ָ�����ݼ���
		List<HashMap<String, String>> knsrdzbnrList = new ArrayList<HashMap<String, String>>();
		//�������϶�ָ�����ݼ��������������϶�ָ�����Լ��Ϸŵ�map ������ ��ǰ̨ҳ�����
		for (HashMap<String, String> hm : knsrdzbsxList) {
			String sxid = hm.get("sxid"); 
			knsrdzbnrList = knsrdzbService.getKnsrdzbnrList(sxid);
			object.put(hm,knsrdzbnrList);
		}
		//request.setAttribute("model", model);
		request.setAttribute("object", object);
		request.setAttribute("zbid", zbid);
		this.saveToken(request);
		return mapping.findForward("addKnsrdsq");
	}
	
	/**
	 * 
	 * @����:TODO(�����������϶�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����08:59:31
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SuppressWarnings("deprecation")
	@SystemLog(description="����ѧ������-�������϶�-�������϶�����-�޸�-JSON:{json},XH:{xh}")
	public ActionForward updateKnsrdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdsqForm model = (KnsrdsqForm) form;
		KnsrdsqService service = new KnsrdsqService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		KnsrdzbForm knsrdzbForm = new KnsrdzbForm();
		KnsrdzbService knsrdzbService = new KnsrdzbService();
		Map<HashMap<String, String>, List<HashMap<String, String>>> object = new LinkedHashMap<HashMap<String,String>, List<HashMap<String,String>>>();
		knsrdzbForm.setZbid(model.getZbid());
		
        if (UPDATE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType()) ){
        	//model.setZbid(zbid);
        	//Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
        	boolean isExist= false;
        	String jsonStr = request.getParameter("json");
			JSONArray jsonArray = new JSONArray(jsonStr);
        	if(!isExist){
	        	//���ѧ��֤��������
        		boolean result = service.updateKnsrdsq(jsonArray,model);
        		String messageKey = "";
        		if(UPDATE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_KNSRDZB_KNSRDSQ_REPEAT));
				return null;
        	}
		}
		String path = "xg_xszz_knsrd_knsqgl.do?method=updateKnsrdsq";
		request.setAttribute("path", path);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("jbxxList", jbxxList);
		//��ȡ��������������
		JcszService jcszService=new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);

		knsrdzbForm = knsrdzbService.getModel(knsrdzbForm);
		List<HashMap<String, String>> knsrdzbsxList = new ArrayList<HashMap<String, String>>();
		//��ȡ�������϶�ָ�����Լ���
		knsrdzbsxList = service.getKnsrdzbsxList(model);
		//��ȡ�������϶�ָ�����ݼ���
		List<HashMap<String, String>> knsrdzbnrList = new ArrayList<HashMap<String, String>>();
		//�������϶�ָ�����ݼ��������������϶�ָ�����Լ��Ϸŵ�map ������ ��ǰ̨ҳ�����
		for (HashMap<String, String> hm : knsrdzbsxList) {
			String sxid = hm.get("sxid"); 
			knsrdzbnrList = knsrdzbService.getKnsrdzbnrList(sxid);
			object.put(hm,knsrdzbnrList);
		}
		String nrids = service.getKnsrdzbsqnrids(model);
		request.setAttribute("nrids", nrids);
		request.setAttribute("object", object);
		
		KnsrdsqForm updateKnsrdsqForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateKnsrdsqForm));
		return mapping.findForward("updateKnsrdsq");
	}
	

	
	/**
	 * 
	 * @����:TODO(�鿴�������϶�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����08:59:53
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
	@SystemAuth(url = url)
	public ActionForward viewKnsrdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdsqForm model = (KnsrdsqForm) form;
		KnsrdsqService service = new KnsrdsqService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		KnsrdzbForm knsrdzbForm = new KnsrdzbForm();
		KnsrdzbService knsrdzbService = new KnsrdzbService();
		Map<HashMap<String, String>, List<HashMap<String, String>>> object = new LinkedHashMap<HashMap<String,String>, List<HashMap<String,String>>>();
		//String zbid = service.getKnsrdzbid();
		knsrdzbForm.setZbid(model.getZbid());
       
		String path = "xg_xszz_knsrd_knsqgl.do?method=updateKnsrdsq";
		request.setAttribute("path", path);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(KNSRD);
		request.setAttribute("jbxxList", jbxxList);

		knsrdzbForm = knsrdzbService.getModel(knsrdzbForm);
		List<HashMap<String, String>> knsrdzbsxList = new ArrayList<HashMap<String, String>>();
		model = service.getModel(model);
		//��ȡ�������϶�ָ�����Լ���
		knsrdzbsxList = service.getKnsrdzbsxList(model);
		//��ȡ�������϶�ָ�����ݼ���
		List<HashMap<String, String>> knsrdzbnrList = new ArrayList<HashMap<String, String>>();
		//�������϶�ָ�����ݼ��������������϶�ָ�����Լ��Ϸŵ�map ������ ��ǰ̨ҳ�����
		for (HashMap<String, String> hm : knsrdzbsxList) {
			hm=(HashMap<String, String>) StringUtils.formatDataView(hm);
			String sxid = hm.get("sxid"); 
			knsrdzbnrList = knsrdzbService.getKnsrdzbnrList(sxid);
			object.put(hm,knsrdzbnrList);
		}
		String nrids = service.getKnsrdzbsqnrids(model);
		request.setAttribute("nrids", nrids);
		request.setAttribute("object", object);
		return mapping.findForward("viewKnsrdsq");
	}
	
	
	
	/**
	 * 
	 * @����:TODO(ɾ��������ָ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-26 ����05:25:39
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-�������϶�-�������϶�����-ɾ��-VALUES:{values}")
	public ActionForward delKnsrdzbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdsqService service = new KnsrdsqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteKnsrdzbsq(values.split(","));
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
	 * @����:TODO(�ύ�������϶�����״̬)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����08:32:59
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-�������϶�-�������϶�����-�ύ-VALUES:{values}")
	public ActionForward submitKnsrdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdsqForm model = (KnsrdsqForm) form;
		String sqid = request.getParameter("values");
		String xh = request.getParameter("xh");
		String splc = request.getParameter("splc");
		model.setSqid(sqid);
		model.setXh(xh);
		model.setSplc(splc);
		KnsrdsqService service = new KnsrdsqService();
		boolean result = service.submitKnsrdsq(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(�����������϶�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����08:50:43
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-�������϶�-�������϶�����-����-VALUES:{values}")
	public ActionForward cancelKnsrdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdsqService service = new KnsrdsqService();
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		//ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid,lcid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			KnsrdsqForm model = new KnsrdsqForm();
			model.setSqid(sqid);
			model.setShzt(Constants.YW_WTJ);
			result = service.updateKnsrdsq(model);
			if(result){
				//ɾ�������Ϣ
				ShlcInterface shlc = new CommShlcImpl();
				shlc.deleteShjl(model.getSqid());
			}
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	

	
	/**
	 * 
	 * @����:TODO(�Զ��嵼������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����08:51:14
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsrdsqForm model = (KnsrdsqForm) form;
		KnsrdsqService service = new KnsrdsqService();
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	

}
