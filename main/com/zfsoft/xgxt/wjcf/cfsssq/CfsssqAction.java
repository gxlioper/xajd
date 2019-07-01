/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-29 ����01:46:50 
 */  
package com.zfsoft.xgxt.wjcf.cfsssq;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.wjcf.cfsh.CfshForm;
import com.zfsoft.xgxt.wjcf.cfsh.CfshService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (������������) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-29 ����01:46:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfsssqAction extends SuperAction {
	
	BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	private final static String CFSBZDPZ="cfsbzdpz";
	private final static String RETURNFLAG="return";
	
	private static final String url = "wjcf_cfsssq.do?method=cxCfsssqList";
	
	@SystemAuth(url = url)
	public ActionForward cxCfsssqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsssqForm model=(CfsssqForm)form;
		CfsssqService service=new CfsssqService();
		if (QUERY.equals(model.getType())){
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
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		String path = "wjcf_cfsssq.do?method=cxCfsssqList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cxCfsssqList");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cfsssqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsssqForm model=(CfsssqForm)form;
		CfshService cfshService=new CfshService();
		CfsssqService service=new CfsssqService();
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
		String returnflg =request.getParameter("returnflag");
		if(RETURNFLAG.equalsIgnoreCase(returnflg)){
			String ssid = request.getParameter("ssid");
			model.setReturnflag("backup");
			model.setSsid(ssid);
			//������Ϣ
			CfsssqForm myForm=service.getModel(model);
			BeanUtils.copyProperties(model, myForm);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", UPDATE);
		//Υ����Ϣ
		CfshForm cfshForm = new CfshForm();
		cfshForm.setYwid(model.getCfid());
		HashMap<String, String> map=cfshService.getCfsbxxForjg(cfshForm);
		request.setAttribute("map", StringUtils.formatData(map));
		this.saveToken(request);
		return mapping.findForward("cfsssqAdd");
	}
	/**
	 * 
	 * @����:(���߱���)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-29 ����04:37:34
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
	@SystemLog(description = "����Υ�ʹ���-�������߹���-��������-����CFID��{cfid},XH:{xh},SQLY:{sqly}")
	public ActionForward cfsssqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		CfsssqForm model=(CfsssqForm)form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		CfsssqService service=new CfsssqService();
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		String result=service.save(model);
		String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
				: "true".equals(result)?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @����:(�����޸� )
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-29 ����04:37:34
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
	public ActionForward cfsssqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsssqForm model=(CfsssqForm)form;
		CfshService cfshService=new CfshService();
		CfsssqService service=new CfsssqService();
		
		
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
		
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("type", UPDATE);
		//Υ����Ϣ
		CfshForm cfshForm = new CfshForm();
		cfshForm.setYwid(model.getCfid());
		HashMap<String, String> map=cfshService.getCfsbxxForjg(cfshForm);
		request.setAttribute("map", StringUtils.formatData(map));
		
		//������Ϣ
		CfsssqForm myForm=service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("cfsssqUpdate");
	}
	
	/**
	 * 
	 * @����:(�����޸ı��� )
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-29 ����04:37:34
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
	@SystemLog(description = "����Υ�ʹ���-�������߹���-��������-�޸�SSID��{ssid},XH:{xh},SQLY:{sqly}")
	public ActionForward cfsssqUpdateSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsssqForm model=(CfsssqForm)form;
		CfsssqService service=new CfsssqService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//boolean result = false;
		//�޸�ҳ�汣��
		if(!StringUtils.isNull(model.getSqly())){
			model.setSqly(model.getSqly().replaceAll("\\n", ""));
		}
		if(SAVE.equalsIgnoreCase(model.getType())){
			//model.setSsjg(Constants.WSH);
			boolean result=service.runUpdate(model);
			 String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			 response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			//�޸�ҳ���ύ
			String result=service.cfsssqUpdateSave(model);
			String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
					: "true".equals(result)?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}
		
		
		
		return null;
	}
	
	/**
	 * 
	 * @����:(����ɾ�� )
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-29 ����04:37:34
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
	@SystemLog(description = "����Υ�ʹ���-�������߹���-��������-�޸�VALUES:{values}")
	public ActionForward cfsssqDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsssqService service=new CfsssqService();
		String[] ids=request.getParameter("values").split(",");
		String splcid=request.getParameter("splcid");
		String shzt = request.getParameter("shzt");
		if(!shzt.equalsIgnoreCase("0")){
			//ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
			boolean ssResult = service.cancelRecord(ids[0],splcid);
			int num  = 0;
			if(ssResult){
			 num = service.runDelete(ids);
			 boolean result =  num > 0;
				String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
						: MessageUtil.getText(MessageKey.WJCF_CFSS_CFQXSS);
				response.getWriter().print(getJsonMessage(message));
			}else{
				String message =   MessageUtil.getText(MessageKey.WJCF_CFSS_CFQXSS);
				response.getWriter().print(getJsonMessage(message));
			}
		}else{
			//δ�ύ������Ҳ���Գ�������
			int num = service.deleteRecord(ids);
			 boolean result =  num > 0;
				String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
						: MessageUtil.getText(MessageKey.WJCF_CFSS_CFQXSS);
				response.getWriter().print(getJsonMessage(message));
		}
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelCfsssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//YdsqService service = new YdsqService();
		CfsssqService service=new CfsssqService();
		String ssid = request.getParameter("values");
		String splcid = request.getParameter("splcid");
		//ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(ssid,splcid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			CfsssqForm model = new CfsssqForm();
			model.setCfid(ssid);
			model.setSplcid(splcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(ssid))>0){
				model.setSsjg(Constants.YW_YTH);
			}else{
				model.setSsjg(Constants.YW_WTJ);
			}
			service.updateRecord(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitCfsssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfsssqForm model = (CfsssqForm) form;
		CfsssqService service = new CfsssqService();
		String ssid = request.getParameter("values");
		String xh = request.getParameter("xh");

		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		String splcid = request.getParameter("splcid");
		model.setCfid(ssid);
		model.setXh(xh);

		CfsssqForm myModel = service.getModel(ssid);
		// ���˻�
		if(Constants.YW_YTH.equals(myModel.getSsjg())){
			splcid = myModel.getSplcid();
		}else{
			//��ȡ���߽������
			HashMap<String, String> ssjcsplc = service.getSsjcsplc();
			splcid = ssjcsplc.get("ssspl");
		}
		//��������Ϊ�գ���ʾ��ʾ������������
		if (null == splcid||"".equalsIgnoreCase(splcid)) {
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SELECT_SHLC_FAIL));			
			return null;
		}
		model.setSplcid(splcid);
		
		model.setSsjg(Constants.YW_SHZ);
		boolean result = service.submitCfsssq(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}


	/**
	 * �ӱ�����ʦ��ѧԺzip
	 */
	public ActionForward getWjcfjdsZip(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String value = request.getParameter("value");
		String xhs = request.getParameter("xhs");
		if (StringUtils.isNotNull(value)){
			String[] ids = value.split(",");
			String[] xh = xhs.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = ids.length ; i < n ; i++){
				CfsssqForm model = (CfsssqForm) form;
				model.setCfid(ids[i]);
				model.setXh(xh[i]);
				File file = getWord(model);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}

		return null;
	}

	/**
	 *
	 * @����: ��ӡWord(�ӱ�����ʦ��ѧԺ)
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2018-7-18 ����02:22:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getWjcfjdsWord(ActionMapping mapping, ActionForm form,
										HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CfsssqForm model = (CfsssqForm) form;
		File wordFile = getWord(model);
		FileUtil.outputWord(response, wordFile);
		return null;

	}

	private File getWord(CfsssqForm model) {
		CfshService cfshService=new CfshService();
		Map<String, Object> data = new HashMap<String, Object>();
		String xh = model.getXh();
		if (!StringUtils.isNull(xh)) {
			// ������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			HashMap<String,String> xsnl = cfshService.getxsnl(xh);
			data.putAll(xsnl);
			data.putAll(xsjbxx);

			//Υ����Ϣ

			CfshForm cfshForm = new CfshForm();
			cfshForm.setYwid(model.getCfid());
			HashMap<String, String> map=cfshService.getCfsbxxForjg(cfshForm);
			data.putAll(map);
			data.put("model",model);
			File wordFile = FreeMarkerUtil.getWordFile(data,
					"classpath://templates//wjcf", "wjcfjds_10098.xml", xh + "-"
							+ xsjbxx.get("xm"));
			return wordFile;
		}

		return null;
	}

}
