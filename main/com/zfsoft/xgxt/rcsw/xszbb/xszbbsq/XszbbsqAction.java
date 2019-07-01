/**
 * 
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-17 ����01:36:00
 * 
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbbsq;

import java.io.File;
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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.xszbb.xszbbjcsz.XszbbJcszForm;
import com.zfsoft.xgxt.rcsw.xszbb.xszbbjcsz.XszbbJcszService;
import com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh.XszbblxwhDao;
import com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh.XszbblxwhForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;



public class XszbbsqAction extends SuperAction {
	//����ѧ��֤������ѧ��֤���쳣�����Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String RCSWXSZBB = "rcswxszbb";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "rcsw_xszbb_bbsq.do";
	
	/**
	 * ѧ��֤����:"001"
	 */
	private static final String XSZDM = "001";
	
	/**
	 * 
	 * @����:TODO(ѧ��֤���������б�)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����01:36:00
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
	public ActionForward xszbbsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbsqForm model = (XszbbsqForm) form;
		XszbbsqService service = new XszbbsqService();
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
		XszbbJcszService xszbbJcszService = new XszbbJcszService();
		XszbbJcszForm jcszModel = xszbbJcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		String path = "rcsw_xszbb_bbsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszbbsqManage");
	}

	/**
	 * 
	 * @����:TODO(����ѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����03:18:28
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
	@SystemLog(description="�����ճ�����-֤������-֤����������-����XH:{xh},XSZBBLXDM:{xszbblxdm},SFBBHCYHK:{sfbbhcyhk},SQSJ:{sqsj},SQLY:{sqly}")
	public ActionForward addXszbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbsqForm model = (XszbbsqForm) form;
		XszbbsqService service = new XszbbsqService();
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
        if (SAVE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType()) ){
        	if (!isTokenValid(request)){
    			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
    			return null;
    		}

        	//Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
        	boolean isExist= false;
        	isExist = service.isExistByXszbbsq(model);
        	if(!isExist){
        		super.resetToken(request);
        		//��ѧ��֤����ղ����Żݿ���Ϣ
        		if(!XSZDM.equalsIgnoreCase(model.getXszbblxdm())){
        			model.setSfbbhcyhk("");
        		}
	        	//���ѧ��֤��������
				String bbsqid = UniqID.getInstance().getUniqIDHash();
				model.setBbsqid(bbsqid);
        		boolean result = service.saveXszbbsq(model);
        		String messageKey = "";
        		
        		if(SAVE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XSZBB_XSZBBSQ_REPEAT));
				return null;
        	}
		}
		String path = "rcsw_xszbb_bbsqgl.do?method=addXszbbsq";
		request.setAttribute("path", path);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		//��������ά������
		request.setAttribute("bblxwhList", service.getBblxwhList());
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		model.setSfbbhcyhk("y");
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("addXszbbsq");
	}
	
	/**
	 * 
	 * @����:TODO( �޸�ѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����04:12:14
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
	@SystemLog(description="�����ճ�����-֤������-֤����������-�޸�BBSQID:{bbsqid},XSZBBLXDM:{xszbblxdm},SFBBHCYHK:{sfbbhcyhk},SQSJ:{sqsj},SQLY:{sqly}")
	public ActionForward updateXszbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbsqForm model = (XszbbsqForm) form;
		XszbbsqService service = new XszbbsqService();
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
        if (UPDATE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType())){
	        	//��ѧ��֤����ղ����Żݿ���Ϣ
	    		if(!XSZDM.equalsIgnoreCase(model.getXszbblxdm())){
	    			model.setSfbbhcyhk("");
	    			if(Base.xxdm.equals("13011") || Base.xxdm.equals("10695")){//�ൺ�Ƶ����ְҵ����ѧԺ���Ի�
	    				model.setCcqdz("");
	    				model.setCczdz("");
	    			}
	    		}else{
	    			if((Base.xxdm.equals("13011") || Base.xxdm.equals("10695")) && "n".equalsIgnoreCase(model.getSfbbhcyhk())){//�ൺ�Ƶ����ְҵ����ѧԺ���Ի�
	    				model.setCcqdz("");
	    				model.setCczdz("");
	    			}
	    		}
				boolean result = service.updateXszbbsq(model);
				String messageKey = "";
        		if(UPDATE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
		}
        String path = "rcsw_xszbb_bbsqgl.do?method=addXszbbsq";
		request.setAttribute("path", path);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		
		XszbbJcszService xszbbJcszService = new XszbbJcszService();
		XszbbJcszForm jcszModel = xszbbJcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		//��������ά������
		request.setAttribute("bblxwhList", service.getBblxwhList());
		XszbbsqForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("updateXszbbsq");
	}
	
	
	/**
	 * 
	 * @����:TODO(ɾ��ѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:28:32
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
	@SystemLog(description="�����ճ�����-֤������-֤����������-ɾ��VALUES:{values}")
	public ActionForward delXszbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbsqService service = new XszbbsqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteXszbbsq(values.split(","));
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
	 * @����:TODO(�ύѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:29:03
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
	@SystemLog(description="�����ճ�����-֤������-֤����������-�ύVALUES:{values}")
	public ActionForward submitXszbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbsqForm model = (XszbbsqForm) form;
		String bbsqid = request.getParameter("values");
		String xh = request.getParameter("xh");
		String xszbblxdm = request.getParameter("xszbblxdm");
		model.setBbsqid(bbsqid);
		model.setXh(xh);
		model.setXszbblxdm(xszbblxdm);
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// ��ȡ��������
			XszbblxwhForm bblx = new XszbblxwhForm();
			bblx.setXszbblxdm(model.getXszbblxdm());
			String splc = new XszbblxwhDao().getModel(bblx).getShlc();
			model.setSplc(splc);
		}
		model.setShzt(Constants.YW_SHZ);
		XszbbsqService service = new XszbbsqService();
		boolean result = service.submitBbsq(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����06:28:42
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
	@SystemLog(description="�����ճ�����-֤������-֤����������-����VALUES:{values}")
	public ActionForward cancelXszbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszbbsqService service = new XszbbsqService();
		String bbsqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		//ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(bbsqid,lcid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			XszbbsqForm model = new XszbbsqForm();
			model.setBbsqid(bbsqid);
			model.setSplc(lcid);
			//�鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(bbsqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			
			service.updateBbsq(model);
		}
		
		
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����:TODO(�鿴��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:29:32
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
	public ActionForward viewXszbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbbsqForm model = (XszbbsqForm) form;
		XszbbsqService service = new XszbbsqService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//��ѯ������Ϊ��Ϣ���
		request.setAttribute("rs", StringUtils.formatData(service.getXszbbsqInfo(model)));
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("viewXszbbsq");
		
		
	}
	
	/**
	 * 
	 * @����:TODO(�Զ��嵼������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:30:04
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
		XszbbsqForm model = (XszbbsqForm) form;
		XszbbsqService service = new XszbbsqService();
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
	
	/** 
	 * @����:��ȡ�𳵳˳�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-5-23 ����05:24:55
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
	public ActionForward getHcqjxx(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		XszbbsqForm model = (XszbbsqForm) form;
		XszbbsqService service = new XszbbsqService();
		HashMap<String,String> xxmap = service.getHcccqj(model.getXh());
		JSONObject json = JSONObject.fromObject(xxmap);
		response.getWriter().print(json);
		return null;		
	}
	

}
