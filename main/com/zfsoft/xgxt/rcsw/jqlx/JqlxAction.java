/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-30 ����10:41:42 
 */
package com.zfsoft.xgxt.rcsw.jqlx;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.qsgl.QsglService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-������У
 * @�๦������: ������Уaction
 * @���ߣ�945
 * @ʱ�䣺 2013-12-30 ����10:41:42
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JqlxAction extends SuperAction {
	
	private static final String RCSWXSZBB = "rcswxszbb";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	

	/**
	 * ****************************�������*************************
	 */
	/**
	 * 
	 * @����:������У�����б�
	 * @���ߣ�945
	 * @���ڣ�2013-12-31 ����02:34:48
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
	
	public ActionForward jqlxSqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
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
		JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jcszModel = jqlxszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		// Ĭ������
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_jqlxsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jqlxSqList");
	}

	/**
	 * 
	 * @����:����������У����
	 * @���ߣ�945
	 * @���ڣ�2013-12-31 ����03:31:09
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
	
	@SystemLog(description="�����ճ�����-������У-������У����-����XH:{xh},LXKSSJ:{lxkssj},LXJZSJ:{lxjzsj},SQLY:{sqly}")
	public ActionForward addJqlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();

		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			if("11647".equals(Base.xxdm)){
				request.setAttribute("defaluevalue", xsjbxx.get("xqdm"));
			}
			if (StringUtil.isNull(model.getRzdz())){
				// ����
				WdgwsqService wdgwsqService = new WdgwsqService();
				String rzdz=wdgwsqService.getQsxxJqlx(model.getXh());
//				model.setRzdz(rzdz);
				model.setYzqs(rzdz);
			}
		}
        if (SAVE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType()) ){
        	if (!isTokenValid(request)){
    			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
    			return null;
    		}

        	String messageKey = "";
        	int k = service.getSfcfCount(model);
        	if(k==0){
        		super.resetToken(request);
        		boolean result = service.savaJqlxsq(model);
        		if(SAVE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
        	}else{
        		messageKey = MessageKey.DATA_OVERLAP;
        	}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String path = "rcsw_jqlx.do?method=addJqlx";
		request.setAttribute("path", path);
		//ѧ��������Ϣ��ʾ����
		if("11458".equals(Base.xxdm)){
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB + "_11458");
		}else{
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		}
		JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jqlxszModel = jqlxszService.getJqlxszModelSqJg();
		request.setAttribute("jcszModel", jqlxszModel);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("jqlxV", jqlxszModel.getJqlx());
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�꼯��
		request.setAttribute("xnList", Base.getXnndList2());
		//ѧ�ڼ���
		request.setAttribute("xqList", Base.getXqList());
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setXqmc(Base.getDqxqmc());
		request.setAttribute("rs", StringUtils.formatData(model));
		//����ѧԺ����ũ����Ի�
		if("11488".equals(Base.xxdm)||"10364".equals(Base.xxdm)){
			request.setAttribute("lxyyList", service.getLxyyList());
		}
		if("10344".equals(Base.xxdm)){
			request.setAttribute("lxtjList", service.getLxtjList());
		}
		//�㽭��ýѧԺ���Ի�������У����
		if("11647".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);
		}
		//������ҽҩ
		if("10026".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);

			List<HashMap<String, String>> ldList = new QsglService().getLdList();
			request.setAttribute("ldList", ldList);
		}
		//��ȡ��У���������б��´���Ի���
		if("10351".equals(Base.xxdm)) {
			request.setAttribute("lxsqlxList", service.getLxsqList());		
		}
		//�㽭��ҽҩ
		if("10344".equals(Base.xxdm)) {
			request.setAttribute("yqList", service.getYqList());		
		}
		//�Ϻ�����ѧԺ
		if("10277".equals(Base.xxdm)) {
			if (!StringUtil.isNull(model.getXh())){
				request.setAttribute("sfkns", service.getKnsxx(model.getXh()));		
			}
		}
		this.saveToken(request);
		return mapping.findForward("addJqlx");
	}

	/**
	 * 
	 * @����:�޸ļ�����У����
	 * @���ߣ�945
	 * @���ڣ�2013-12-31 ����03:31:31
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
	
	@SystemLog(description="�����ճ�����-������У-������У����-�޸�SQID:{sqid},XH:{xh},LXKSSJ:{lxkssj},LXJZSJ:{lxjzsj},SQLY:{sqly}")
	public ActionForward updateJqlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
        if (UPDATE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType())){
        	String messageKey = "";
        	int k = service.getSfcfCount(model);
        	if(k==0){
        		boolean result = service.savaJqlxsq(model);
        		if(UPDATE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}else{
        			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        		}
        	}else{
        		messageKey = MessageKey.DATA_OVERLAP;
        	}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
        
        JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jcszModel = jqlxszService.getJqlxszModelSqJg();
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("jcszModel", jcszModel);
		
        String path = "rcsw_jqlx.do?method=updateJqlx";
		request.setAttribute("path", path);
		//ѧ��������Ϣ��ʾ����
		if("11458".equals(Base.xxdm)){
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB + "_11458");
		}else{
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		}
		request.setAttribute("jqlxV", jcszModel.getJqlx());
		request.setAttribute("jbxxList", jbxxList);
		
		JqlxModel updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		//ѧ�꼯��
		request.setAttribute("xnList", Base.getXnndList2());
		//ѧ�ڼ���
		request.setAttribute("xqList", Base.getXqList());

		request.setAttribute("jcszModel", jcszModel);
		request.setAttribute("rs", model);
		//����ѧԺ����ũ����Ի�
		if("11488".equals(Base.xxdm)||"10364".equals(Base.xxdm)){
			request.setAttribute("lxyyList", service.getLxyyList());
		}
		//�㽭��ýѧԺ���Ի�������У����
		if("11647".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);
		}
		//������ҽҩ
		if("10026".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);

			List<HashMap<String, String>> ldList = new QsglService().getLdList();
			request.setAttribute("ldList", ldList);
		}
		//��ȡ��У���������б��´���Ի���
		if("10351".equals(Base.xxdm)) {
			request.setAttribute("lxsqlxList", service.getLxsqList());		
		}
		//�㽭��ҽҩ
		if("10344".equals(Base.xxdm)) {
			request.setAttribute("yqList", service.getYqList());	
			request.setAttribute("lxtjList", service.getLxtjList());
		}
		//�Ϻ�����ѧԺ
		if("10277".equals(Base.xxdm)) {
			request.setAttribute("sfkns", service.getKnsxx(model.getXh()));		
		}
		return mapping.findForward("updateJqlx");
	}

	/**
	 * 
	 * @����:�б��ύ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-31 ����04:35:30
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
	
	@SystemLog(description="�����ճ�����-������У-������У����-�ύSQID:{sqid}")
	public ActionForward submitJqlxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		boolean result = service.submitSq(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:����ĳ���
	 * @���ߣ�945
	 * @���ڣ�2013-12-31 ����04:54:14
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
	
	@SystemLog(description="�����ճ�����-������У-������У����-����SQID:{sqid}")
	public ActionForward cancelJqlxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		boolean result = service.cancleRecord(model.getSqid(),model.getLcid());
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			JqlxModel myForm = new JqlxModel();
			myForm.setSqid(model.getSqid());
			
			//�鿴�Ƿ�������˻ؼ�¼���ǣ�״̬Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(model.getSqid()))>0){
				myForm.setSqzt(Constants.YW_YTH);
			}else{
				myForm.setSqzt(Constants.YW_WTJ);
			}
			service.updateModel(myForm);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:ɾ��������У��¼����
	 * @���ߣ�945
	 * @���ڣ�2013-12-31 ����04:18:33
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
	
	@SystemLog(description="�����ճ�����-������У-������У����-ɾ��VALUES:{values}")
	public ActionForward delJqlxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxService service = new JqlxService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteJqlxsq(values.split(","));
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
	 * @����:�鿴������У����
	 * @���ߣ�945
	 * @���ڣ�2013-12-31 ����05:18:26
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
	
	public ActionForward viewJqlxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//result
		request.setAttribute("rs", service.getModel(model));
		//ѧ��������Ϣ��ʾ����
		if("11458".equals(Base.xxdm)){
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB + "_11458");
		}else{
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		}
		JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jqlxszModel = jqlxszService.getModel();
		request.setAttribute("jqlxV", jqlxszModel.getJqlx());
		
		request.setAttribute("jbxxList", jbxxList);
		//�Ϻ�����ѧԺ
		if("10277".equals(Base.xxdm)) {
			request.setAttribute("sfkns", service.getKnsxx(model.getXh()));		
		}
		request.setAttribute("model", model);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("viewJqlxsq");
	}

	/**
	 * ****************************������*************************
	 */

	/**
	 * ����б�
	 */
	@SystemAuth(url = "rcsw_jqlxsh.do")
	public ActionForward jqlxShManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String,String>> resultList = service.getAuddingList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ������
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_jqlxsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jqlxShList");
	}
	
	/**
	 * 
	 * @����:��˹��̵ĳ���
	 * @���ߣ�945
	 * @���ڣ�2014-1-2 ����05:05:04
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
	@SystemAuth(url = "rcsw_jqlxsh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-������У-������У���-����SQID:{sqid}")
	public ActionForward cancelJqlxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		//HashMap<String,String> shxx = ShlcUtil.getShxx(model.getShid());	
		// ҵ��ع�
		boolean result = service.cancel(model.getSqid());//shxx.get("ywid")
		// ҵ��ع��ɹ�
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}

	/**
	 * 
	 * @����:������У���
	 * @���ߣ�945
	 * @���ڣ�2014-1-2 ����05:04:47
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
	@SystemAuth(url = "rcsw_jqlxsh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-������У-������У���-���SQID:{sqid}")
	public ActionForward toViewShDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel myForm = (JqlxModel) form;
		JqlxService service = TransactionControlProxy.newProxy(new JqlxService());
		if (!StringUtil.isNull(myForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			User user = getUser(request);
			//���浥�����
			boolean result = service.singleSh(myForm,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//ѧ��������Ϣ��ʾ����
		if("11458".equals(Base.xxdm)){
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB + "_11458");
		}else{
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		}
		//�Ϻ�����ѧԺ
		if("10277".equals(Base.xxdm)) {
			request.setAttribute("sfkns", service.getKnsxx(myForm.getXh()));		
		}
		JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jqlxszModel = jqlxszService.getModel();
		request.setAttribute("jqlxV", jqlxszModel.getJqlx());
		request.setAttribute("jbxxList", jbxxList);
		JqlxModel model=service.getModel(myForm);
		model.setGwid(myForm.getGwid());
		model.setShid(myForm.getShid());
		request.setAttribute("rs", model);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("shDetail");
	}
	
	/**
	 * 
	 * @����:�������ҳ
	 * @���ߣ�945
	 * @���ڣ�2014-1-9 ����03:03:33
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
	@SystemAuth(url = "rcsw_jqlxsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward jqlxPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("jqlxPlsh");
	}
	
	/**
	 * 
	 * @����:�����������
	 * @���ߣ�945
	 * @���ڣ�2014-1-9 ����03:07:29
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
	@SystemAuth(url = "rcsw_jqlxsh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-������У-������У���-�������ID:{id}")
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel myForm = (JqlxModel) form;
		JqlxService service = TransactionControlProxy.newProxy(new JqlxService());
		User user = getUser(request);
		String message = service.savePlsh(myForm, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * ��˵���
	 */
	@SystemAuth(url = "rcsw_jqlxsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportSqshData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getResultAllListSqsh(model, user);//��ѯ�����м�¼������ҳ
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
	 * ***************************������****************************
	 */
	
	/** 
	 * ��λ�б�
	 */
	
	public ActionForward selectCwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		JqlxModel myForm = (JqlxModel) form;
		JqlxService service = new JqlxService();
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			// ��ѯ
			if(!Base.xxdm.equals("10344")){				
				List<HashMap<String, String>> resultList = service.getCwxxList(myForm, user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
			}
			return null;
		}
		String gotoPath = request.getParameter("goto");
		String xh = request.getParameter("xh");
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		myForm.setXh(xh);
		String path = "rcsw_jqlx.do?method=selectCwxx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("xh", xh);
		request.setAttribute("sqid", myForm.getSqid());
		return mapping.findForward("selectCwxx");
	}
	
	@SystemAuth(url = "rcsw_jqlxjg.do")
	public ActionForward jqlxJgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getResultList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}		
		JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jcszModel = jqlxszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		// Ĭ������
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_jqlxjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jqlxJgList");
	}
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ�945
	 * @���ڣ�2014-1-3 ����09:59:18
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
	@SystemAuth(url = "rcsw_jqlxjg.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-������У-������У���-����XH:{xh},XN:{xn},XQ:{xq},LXKSSJ:{lxkssj},LXJZSJ:{lxjzsj},SQLY:{sqly}")
	public ActionForward addJqlxJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			if("11647".equals(Base.xxdm)){
				request.setAttribute("defaluevalue", xsjbxx.get("yxdm"));
			}
			if (StringUtil.isNull(model.getRzdz())){
				// ����
				WdgwsqService wdgwsqService = new WdgwsqService();
				String rzdz=wdgwsqService.getQsxxJqlx(model.getXh());
//				model.setRzdz(rzdz);
				model.setYzqs(rzdz);
				//��λ��Ϣ�O��
				HashMap<String, String> qsxx = wdgwsqService.getQsxx(model.getXh());
				String lddm = qsxx.get("lddm");
				String qsh = qsxx.get("qsh");
				String cwh = qsxx.get("cwh");
				if (!StringUtil.isNull(lddm)
						&& !StringUtil.isNull(qsh)
						&& !StringUtil.isNull(cwh)) {
					model.setCwxx(lddm + "_" + qsh + "_" + cwh);
				}
			}
		}
        if (SAVE.equalsIgnoreCase(model.getType())){
        	if (!isTokenValid(request)){
    			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
    			return null;
    		}

        	String messageKey = "";
        	int k = service.getSfcfCount(model);
        	if(k==0){
        		super.resetToken(request);
        		boolean result = service.savaJqlxJg(model);
        		if(SAVE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}
        	}else{
        		messageKey = MessageKey.DATA_OVERLAP;
        	}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String path = "rcsw_jqlx.do?method=addJqlxJg";
		request.setAttribute("path", path);
		//ѧ��������Ϣ��ʾ����
		if("11458".equals(Base.xxdm)){
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB + "_11458");
		}else{
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		}
		JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jqlxszModel = jqlxszService.getJqlxszModelSqJg();
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("jcszModel", jqlxszModel);
		request.setAttribute("jqlxV", jqlxszModel.getJqlx());
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�꼯��
		request.setAttribute("xnList", Base.getXnndList2());
		//ѧ�ڼ���
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("rs", model);
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		//����ѧԺ����ũ����Ի�
		if("11488".equals(Base.xxdm)||"10364".equals(Base.xxdm)){
			request.setAttribute("lxyyList", service.getLxyyList());
		}
		//�㽭��ýѧԺ���Ի�������У����
		if("11647".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);
		}
		if("10344".equals(Base.xxdm)){
			request.setAttribute("lxtjList", service.getLxtjList());
		}
		//������ҽҩ
		if("10026".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);

			List<HashMap<String, String>> ldList = new QsglService().getLdList();
			request.setAttribute("ldList", ldList);
		}
		//��ȡ��У���������б��´���Ի���
		if("10351".equals(Base.xxdm)) {
			request.setAttribute("lxsqlxList", service.getLxsqList());		
		}
		//�㽭��ҽҩ��ѧ
		if("10344".equals(Base.xxdm)) {
			request.setAttribute("yqList", service.getYqList());		
		}
		//�Ϻ�����ѧԺ
		if("10277".equals(Base.xxdm)) {
			if (!StringUtil.isNull(model.getXh())){
				request.setAttribute("sfkns", service.getKnsxx(model.getXh()));		
			}
		}
		this.saveToken(request);
		return mapping.findForward("addJqlxJg");
	}
	
	/**
	 * 
	 * @����:���½��
	 * @���ߣ�945
	 * @���ڣ�2014-1-3 ����09:59:04
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
	@SystemAuth(url = "rcsw_jqlxjg.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-������У-������У���-�޸�SQID:{sqid},XN:{xn},XQ:{xq},LXKSSJ:{lxkssj},LXJZSJ:{lxjzsj},SQLY:{sqly}")
	public ActionForward updateJqlxJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
        if (UPDATE.equalsIgnoreCase(model.getType())){
        	String messageKey = "";
        	int k = service.getSfcfCount(model);
        	if(k==0){
        		boolean result = true;
        		if("1".equals(model.getSjlx())){
        			result = service.savaJqlxJgShlc(model);
        		}else{
        			result = service.savaJqlxJg(model);
        		}
        		if(UPDATE.equalsIgnoreCase(model.getType())){
        			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        		}
        	}else{
        		messageKey = MessageKey.DATA_OVERLAP;
        	}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
        String path = "rcsw_jqlx.do?method=updateJqlxJg";
		request.setAttribute("path", path);
		//ѧ��������Ϣ��ʾ����
		if("11458".equals(Base.xxdm)){
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB + "_11458");
		}else{
			jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		}
		JqlxszService jqlxszService = new JqlxszService();
		JqlxszModel jqlxszModel = jqlxszService.getJqlxszModelSqJg();
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("jcszModel", jqlxszModel);
		request.setAttribute("jqlxV", jqlxszModel.getJqlx());
		request.setAttribute("jbxxList", jbxxList);
		JqlxModel updateForm = service.getModel(model);
		//��λ��Ϣ�O��
		if (!StringUtil.isNull(updateForm.getLddm())
				&& !StringUtil.isNull(updateForm.getQsh())
				&& !StringUtil.isNull(updateForm.getCwh())) {
			updateForm.setCwxx(updateForm.getLddm() + "_" + updateForm.getQsh() + "_" + updateForm.getCwh());
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		//ѧ�꼯��
		request.setAttribute("xnList", Base.getXnndList2());
		//ѧ�ڼ���
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("rs", model);
		//����ѧԺ����ũ����Ի�
		if("11488".equals(Base.xxdm)||"10364".equals(Base.xxdm)){
			request.setAttribute("lxyyList", service.getLxyyList());
		}
		//�㽭��ýѧԺ���Ի�������У����
		if("11647".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);
		}
		//������ҽҩ
		if("10026".equals(Base.xxdm)){
			List<HashMap<String, String>> lsxqList = service.LsxqList();
			request.setAttribute("lsxqList", lsxqList);

			List<HashMap<String, String>> ldList = new QsglService().getLdList();
			request.setAttribute("ldList", ldList);
		}
		//��ȡ��У���������б��´���Ի���
		if("10351".equals(Base.xxdm)) {
			request.setAttribute("lxsqlxList", service.getLxsqList());		
		}
		//�㽭��ҽҩ��ѧ
		if("10344".equals(Base.xxdm)) {
			request.setAttribute("yqList", service.getYqList());	
			request.setAttribute("lxtjList", service.getLxtjList());
		}
		//�Ϻ�����ѧԺ
		if("10277".equals(Base.xxdm)) {
			request.setAttribute("sfkns", service.getKnsxx(model.getXh()));		
		}
		return mapping.findForward("updateJqlxJg");
	}
	
	/**
	 * 
	 * @����:���ɾ��
	 * @���ߣ�945
	 * @���ڣ�2014-1-3 ����09:58:47
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
	@SystemAuth(url = "rcsw_jqlxjg.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-������У-������У���-ɾ��VALUES:{values}")
	public ActionForward delJqlxJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxService service = new JqlxService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteJqlxjg(values.split(","));
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
	 * ���ص���ģ��
	 */
	@SystemAuth(url = "rcsw_jqlxjg.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward downloadXls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxService service = new JqlxService();
		JqlxModel myForm = (JqlxModel) form;
		User user = getUser(request);
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/rcsw/rcsw_jqlx.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.downloadXls(myForm, user, request, wwb);
		return null;
	}
	
	/** 
	 * ת�����ݼ��͵���ҳ��
	 */
	@SystemAuth(url = "rcsw_jqlxjg.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-������У-������У���-����TABLENAME:{tableName}")
	public ActionForward importData(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		String tableName = request.getParameter("tableName");//��ͼ��
		String realTable = request.getParameter("realTable");//����
		
		request.setAttribute("dzyDdTab", request.getParameter("dzyDdTab"));
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		
		String act=request.getParameter("act");
		//��������
		if("import".equals(act)){
			uploadFile(mapping, form, request, response);
			
			JqlxService service = new JqlxService();
			String back;
			if ("10344".equals(Base.xxdm)) {
				back= service.importData_10344(request,response);//��������
			}else {
				back= service.importData(request,response);//��������
			}
			String sfdcExcel=(String)request.getAttribute("sfdcExcel");
			if("yes".equals(sfdcExcel)){
				return mapping.findForward("");
			}
			request.setAttribute("back", back);
		}
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("importData");
	}
	
	/**
	 * �ļ��ϴ� 
	 * */
	@SystemAuth(url = "rcsw_jqlxjg.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-������У-������У���-�ϴ�FNAME:{userName}")
	private ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		//�ô���Ҫ��֤��������ԱȨ��
		String dir = servlet.getServletContext().getRealPath("/upload");
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		HttpSession session = request.getSession();
		String fName = (String) session.getAttribute("userName");
		String tabName = request.getParameter("tabName");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		fName += date.toString().substring(0, 19);
		fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		JqlxModel hff = (JqlxModel) form;
		FormFile file = hff.getImpFilePath();	
//		if (file == null || (file.getFileName() == null || file.getFileName().trim().equals(""))) {
//			file = hff.getCheckFilePath();
			if(file == null){
				return mapping.findForward("false");
			}
//		}
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = dir + "/" + fName + ".xls";
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[size];
		while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		in.close();
		request.setAttribute("tabName", tabName);
		request.setAttribute("filepath", filePath);
		request.setAttribute("moditag", request.getParameter("moditag"));
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ�945
	 * @���ڣ�2014-1-3 ����09:58:33
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
	@SystemAuth(url = "rcsw_jqlxjg.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getResultAllList(model, user);//��ѯ�����м�¼������ҳ
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
	 * @����:��ӡ�����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2015-12-24 ����03:51:09
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
	public ActionForward printjqlxsqb (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxService service = new JqlxService();
		XsxxService xsxxService = new XsxxService();
		XsxxglService xsxxglService = new XsxxglService();
		JqlxModel model = (JqlxModel) form;
		if(StringUtils.isNotNull(model.getSqid())){
			List<File> files = new ArrayList<File>();
			String[] ids = model.getSqid().split(",");
			for (String id : ids) {
				model.setSqid(id);
				JqlxModel myForm = service.getModel(model);
				//����ѧ��������Ϣ
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
				/*//���ؼ�ͥ��Ա
				List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(myForm.getXh());
				if(jtcyxxList.size()>0 && null != jtcyxxList) {					
					StringBuilder jtcylxdhh = new StringBuilder();
					for(int i =0;i<jtcyxxList.size();i++) {
						if(null != jtcyxxList.get(i).get("cylxdh") && !jtcyxxList.get(i).get("cylxdh").equals("")) {
							if(i != jtcyxxList.size()-1) {
								jtcylxdhh.append(jtcyxxList.get(i).get("cylxdh") + ",");
							} else {
								jtcylxdhh.append(jtcyxxList.get(i).get("cylxdh"));
							}					
						}
					}
					xsjbxx.put("jtcylxdh", jtcylxdhh.toString());
					String jtcylxdh = jtcyxxList.get(0).get("cylxdh");
					xsjbxx.put("jtcylxdh", jtcylxdh);						
				}*/
				List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(myForm.getXh());
				if(jtcyxxList.size()>0 && null != jtcyxxList) {
					for(int i =0;i<jtcyxxList.size();i++) {
						if(null != jtcyxxList.get(i).get("cylxdh") && !jtcyxxList.get(i).get("cylxdh").equals("")) {
							String jtcylxdh = jtcyxxList.get(i).get("cylxdh");
							xsjbxx.put("jtcylxdh", jtcylxdh);
							break;
						}
					}
					
				}
				//ƴ����Уʱ��
				String lxsj=DateTranCnDate.fomartDateToCn(myForm.getLxkssj(),FomartDateType.day);
				lxsj+=" ���� "+DateTranCnDate.fomartDateToCn(myForm.getLxjzsj(),FomartDateType.day);
				
				xsjbxx.put("lxsj", lxsj);				
				xsjbxx.put("sqly", HtmlUtil.xmlZy(myForm.getSqly()));
				/*if(myForm.getXqmc().equals("��")) {
					xsjbxx.put("jia", "��");
				}
				if(myForm.getXqmc().equals("��")) {
					xsjbxx.put("jia", "��");
				}*/
				File file = service.printForWord(xsjbxx);
				files.add(file);
			}
			if(files!=null && files.size()>1){
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files.get(0));
			 }
		}
		return null;
	}
	
	public ActionForward printLstxz (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JqlxModel model = (JqlxModel) form;
		JqlxService service = new JqlxService();
		XsxxService xsxxService=new XsxxService();
		if(StringUtils.isNull(model.getSqid())){
			return null;
		}
		String defaultPhotoPath=request.getSession().getServletContext().getRealPath("/images/type_pic.gif");
		
		List<HashMap<String,String>> stu4List=new ArrayList<HashMap<String,String>>();//4��ѧ��Ϊһ��hashmap��list
		List<HashMap<String,String>> resultList=service.getJgdcList(model.getSqid());
		HashMap<String,String>stu4map=null;//һ��map����4��ѧ������Ϣ
		int j=resultList.size()%4;
		while(j<4&&j>0){
			resultList.add(new HashMap<String,String>());
			j++;
		}
		for(int i=0;i<resultList.size();i++){
			j=i%4;
			if(j==0){
				stu4map=new HashMap<String,String>();
			}
			HashMap<String,String>map=resultList.get(i);
			String xh=map.get("xh");
			map.put("photo", xsxxService.getPhotoBase64(StringUtils.isNotNull(xh)?xh:"null",defaultPhotoPath));
			stu4map=jointMap(map,stu4map,Integer.toString(j));
			if(j==3){
				stu4List.add(stu4map);
			}
		}
		HashMap<String,Object> data=new HashMap<String,Object>();
		data.put("stu4List", stu4List);
		data.put("nd", Base.currNd);
		File file = service.getWord(data);
		FileUtil.outputWord(response, file);
		return null;
	}
	
	private HashMap<String,String>jointMap(HashMap<String,String>map,HashMap<String,String>map2,String str){
		for(Entry<String,String> entry:map.entrySet()){
			map2.put(entry.getKey()+str,entry.getValue());
		}
		return map2;
	}
	
}
