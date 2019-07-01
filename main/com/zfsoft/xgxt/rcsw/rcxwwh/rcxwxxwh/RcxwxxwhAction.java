/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-2 ����09:23:53 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

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
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwdmwh.RcxwdmwhForm;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwdmwh.RcxwdmwhService;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgForm;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.Globals;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ���Ϊ����ģ��
 * @�๦������: �ճ���Ϊ��Ϣά�� 
 * @���ߣ� Dlq [���ţ�995]
 * @ʱ�䣺 2013-8-2 ����09:23:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RcxwxxwhAction extends SuperAction {
	//�����ճ��������ճ���Ϊ�������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String RCSWRCXW = "rcswrcxw";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	public static final String SUBMIT = "submit";
	
	private static final String url = "rcsw_rcxwwh_rcxwxxwh.do";
	
	/**
	 * 
	 * @����:��ѯ��ȡ�ճ���Ϊά������
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����05:00:13
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
	public ActionForward rcxwxxwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcxwxxwhForm model = (RcxwxxwhForm) form;
		RcxwxxwhService service = new RcxwxxwhService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_rcxwwh_rcxwxxwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rcxwxxwhManage");
	}

	/**
	 * 
	 * @����:�����ճ���Ϊ��Ϣά��
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����05:00:45
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
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ��Ϣά��-����XH:{xh},XWDLDMARR:{xwdldmArr},XWLBDMARR:{xwlbdmArr},FZARRAY:{fzArray},FSSJARR:{fssjArr},XQ:{xq},XN:{xn}")
	public ActionForward addXwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwxxwhForm model = (RcxwxxwhForm) form;
		RcxwxxwhService service = new RcxwxxwhService();
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
        	
    		//�ж��ճ���Ϊ�������Ƿ���Ҫ�������
//    		boolean isSplcExist = service.checkForSplc(model);
    		
        	if(!isExist){
	        	//����ճ���Ϊ��Ϣά��
        		model.setJlr(user.getUserName());
        		Hashtable files = model.getMultipartRequestHandler().getFileElements();
				String[] xwlbdmArr=model.getXwlbdmArr();
				String warnMessage="";
				for(int i=0;i<xwlbdmArr.length;i++){
					//������
					FormFile file = (FormFile) files.get("lbfj"+i);
					if(null!=file&&file.getFileSize() > 1024*1024*5){
						if(i!=0){
							warnMessage+="��";	
						}
						warnMessage+=file.getFileName();
					}
				}
				if(""!=warnMessage){
					String message = MessageUtil.getText(MessageKey.SYS_SAVE_FILESIZE_OUT,warnMessage);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				super.resetToken(request);
        		boolean result = service.saveRcww(model);
            	String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		response.getWriter().print(getJsonMessage(MessageKey.SYS_SAVE_FAIL));
				return null;
        	}
		}
       
		String path = "rcsw_rcxwwh_rcxwxxwhgl.do?method=addXwxx";
		request.setAttribute("path", path);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�꼯��
		request.setAttribute("xnList", Base.getXnndList());
		//ѧ�ڼ���
		request.setAttribute("xqList", Base.getXqList());
		//��Ϊ���༯��
		request.setAttribute("xwdlList", service.getXwdlList(request));
		//��Ϊ��𼯺�
		request.setAttribute("xwlbList", new ArrayList<HashMap<String,String>>());
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setRcxwjlsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		request.setAttribute("nowTime", GetTime.getTimeByFormat("yyyy-mm-dd"));
		this.saveToken(request);
		return mapping.findForward("addXwxxwh");
	}
	
	
	/**
	 * 
	 * @����:�����ճ���Ϊ��Ϣά��
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����05:00:45
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
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ��Ϣά��-�ύVALUES:{values}")
	public ActionForward submitXwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwxxwhService service = new RcxwxxwhService();
		String guid = request.getParameter("values");
		String [] ids  = guid.split(",");
		int okNum = 0;
		for (int i = 0; i < ids.length; i++) {
			RcxwxxwhForm model = (RcxwxxwhForm) form;
			model.setId(ids[i]);
			RcxwxxwhForm modelGet = service.getModel(model);
			// ���˻صļ�¼ȡ�ϵ��������ID;�����˻ؼ�¼����ȥȡ�������
			if(!Constants.YW_YTH.equals(modelGet.getShzt())){
				RcxwdmwhService rcxwService = new RcxwdmwhService();
				RcxwdmwhForm rcxwdmwh = new RcxwdmwhForm();
				rcxwdmwh.setRcxwlbdldm(modelGet.getRcxwlbdldm());
				rcxwdmwh = rcxwService.getModel(rcxwdmwh);
				if(rcxwdmwh!=null){
					model.setSplc(rcxwdmwh.getSplc());
				}
			}else{
				model.setSplc(modelGet.getSplc());
			}
			model.setXh(modelGet.getXh());
			model.setRcxwlbdldm(modelGet.getRcxwlbdldm());
			boolean result = service.submitRcww(model);
			if (result) {
				okNum++;
			}
		}
		String message = ids.length==okNum ? "�ύ�ɹ���" : "�ύ�ɹ���"+okNum+"����ʧ�ܣ�"+(ids.length - okNum) +"����";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ��Ϣά��-����VALUES:{values}")
	public ActionForward cancelRcxwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//YdsqService service = new YdsqService();
		RcxwxxwhService service = new RcxwxxwhService();
		String guid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		//ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(guid,lcid);
		if(result){
			//����ҵ��״̬Ϊ'δ�ύ'
			RcxwxxwhForm model = new RcxwxxwhForm();
			model.setId(guid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(guid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateRcxwModel(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	
	/**
	 * 
	 * @����:��ѯ��ȡ��Ϊ��𼯺�
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����05:03:08
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
	public ActionForward getXwlbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jldldm = request.getParameter("rcxwlbdldm");
		RcxwxxwhService service = new RcxwxxwhService();
		List<HashMap<String,String>> xwlbList = service.getXwlbList(jldldm, request);
		String json = JSONArray.fromCollection(xwlbList).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * �޸��ճ���Ϊ��Ϣά��
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-12 ����09:33:12
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
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ��Ϣά��-�޸�ID:{id},RCXWLBDM:{rcxwlbdm},RCXWLBDLDM:{rcxwlbdldm},FZ:{fz},XQ:{xq},XN:{xn}")
	public ActionForward updateXwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwxxwhForm model = (RcxwxxwhForm) form;
		RcxwxxwhService service = new RcxwxxwhService();
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
        if (UPDATE.equalsIgnoreCase(model.getType()) ||SUBMIT.equalsIgnoreCase(model.getType())){
        	Hashtable files = model.getMultipartRequestHandler().getFileElements();
			//������
			FormFile file = (FormFile) files.get("lbfj");
			if(null!=file&&file.getFileSize() > 1024*1024*5){
				String message = MessageUtil.getText(MessageKey.SYS_SAVE_FILESIZE_OUT,file.getFileName());
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
    		RcxwxxwhForm modelGet = service.getModel(model);

    		// ���˻صļ�¼ȡ�ϵ��������ID;�����˻ؼ�¼����ȥȡ�������
    		if(!Constants.YW_YTH.equals(modelGet.getShzt())){
    			RcxwdmwhService rcxwService = new RcxwdmwhService();
    			RcxwdmwhForm rcxwdmwh = new RcxwdmwhForm();
    			rcxwdmwh.setRcxwlbdldm(modelGet.getRcxwlbdldm());
    			rcxwdmwh = rcxwService.getModel(rcxwdmwh);
    			if(rcxwdmwh!=null){
    				model.setSplc(rcxwdmwh.getSplc());
    			}
    			
    		}else{
    			model.setSplc(modelGet.getSplc());
    			model.setRcxwlbdldm(modelGet.getRcxwlbdldm());
    			model.setRcxwlbdm(modelGet.getRcxwlbdm());

				//�����棬�����״̬��δ���˻�
				if(UPDATE.equalsIgnoreCase(model.getType())){
					model.setShzt(Constants.YW_YTH);
				}
    		}
    		
        	//�ж��ճ���Ϊ�������Ƿ���Ҫ�������
    		boolean isSplcExist = service.checkForSplc(model);
        	
        	//�������״̬�жϸ�����Ϣ�Ƿ�������л��߲���Ҫ���
        	//boolean isExist=service.isExistByXwxxwh(model,UPDATE);
    		boolean isExist = false;
        	if(!isExist){
	        	
				boolean result = service.updateRcww(model,isSplcExist);
				boolean rcxwjgResult = true;
				//���޸������Ϣ���ж��Ƿ�Ҫ����˽�����в�����߸��ĸ������ݡ�
				if(!isSplcExist && SUBMIT.equalsIgnoreCase(model.getType())){
					//����о���ó��ѧУ���»�
	        		if(Globals.XXDM_TJJM.equals(Base.xxdm)){
	        			model.setShzt("1");
	        			service.runUpdate(model);
	        		}
					RcxwjgForm rcxwjgForm = new RcxwjgForm();
            		RcxwjgService rcxwjgService = new RcxwjgService();
					boolean xwwhForxwjgFlag = rcxwjgService.checkXwwhForxwjg(model.getId());
					RcxwxxwhForm myForm=service.getModel(model);
		    		myForm.setRcxwlbdldm(model.getRcxwlbdldm());
		    		myForm.setMultipartRequestHandler(model.getMultipartRequestHandler());
					BeanUtils.copyProperties(rcxwjgForm, StringUtils.formatData(myForm));
					if(xwwhForxwjgFlag){
						//�����Ϊ������д��ڸ�����������ĸ�����
						//����о���ó��ѧУ���»�
		        		if(Globals.XXDM_TJJM.equals(Base.xxdm)){
		        			rcxwjgForm.setSjly("1");
		        		}
						rcxwjgResult = rcxwjgService.updateXwjg(rcxwjgForm);
					}else{
						//��Ϊ�ճ��������û�и�������������һ��
						//����о���ó��ѧУ���»�
		        		if(Globals.XXDM_TJJM.equals(Base.xxdm)){
		        			rcxwjgForm.setSjly("1");
		        		}
						rcxwjgResult = rcxwjgService.insertXwjg(rcxwjgForm);
					}	
            	}
				String messageKey = (result && rcxwjgResult) ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
        	}else{
        		//response.getWriter().print(getJsonMessage(MessageKey.RCSW_RCXWWH_XWWHSHZ));
        		throw new SystemException(MessageKey.RCSW_RCXWWH_XWWHSHZ);
        	}
		}
       
		
		String path = "rcsw_rcxwwh_rcxwxxwhgl.do?method=updateXwxx";
		request.setAttribute("path", path);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�꼯��
		request.setAttribute("xnList", Base.getXnndList());
		//ѧ�ڼ���
		request.setAttribute("xqList", Base.getXqList());
		//��Ϊ���༯��
		request.setAttribute("xwdlList", service.getXwdlList(request));
		
		List<HashMap<String,String>> xwlbList = new ArrayList<HashMap<String,String>>();
		//��ȡ��Ϊ��𼯺�
		xwlbList = service.getXwlbList(request.getParameter("rcxwlbdldm"), request);
		request.setAttribute("xwlbList", xwlbList);
		
		RcxwxxwhForm updateForm = service.getModel(model);
		request.setAttribute("rcxwlbdm", updateForm.getRcxwlbdm());
		//��Ϊ�����Ϣ
		List<HashMap<String,String>> xwlbxxList = service.getXwlbxx(request, updateForm.getRcxwlbdm());
		request.setAttribute("xwlbxx", xwlbxxList!=null&&xwlbxxList.size()>0?xwlbxxList.get(0):null);
		
		updateForm.setRcxwlbdldm(request.getParameter("rcxwlbdldm"));
		model.setRcxwlbdldm(request.getParameter("rcxwlbdldm"));
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("updateXwxxwh");
	}
	
	/**
	 * 
	 * ɾ���ճ���Ϊ��Ϣ,
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-8 ����05:28:50
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
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ��Ϣά��-ɾ��VALUES:{values}")
	public ActionForward delXwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcxwxxwhService service = new RcxwxxwhService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//ɾ���ճ���Ϊ��Ϣά��ֻ��ɾ��δ���״̬ 
			int num = service.runDeleteXwxx(values.split(","));
			boolean result = num > 0;
			
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}
	
	/**
	 * 
	 * �鿴��ѧ������Ϊ��Ϣ
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-12 ����10:08:17
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
	public ActionForward viewXwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwxxwhForm model = (RcxwxxwhForm) form;
		RcxwxxwhService service = new RcxwxxwhService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//��ѯ������Ϊ��Ϣ���
			Map<String, String> oneXwxxList = service.getOneXwxxList(model.getId());
			request.setAttribute("rs", StringUtils.formatData(oneXwxxList));

			//ѧ��������Ϣ��ʾ����
			jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
			request.setAttribute("jbxxList", jbxxList);
			model.setFjlj(oneXwxxList.get("fjlj"));
			model.setFjmc(oneXwxxList.get("fjmc"));
			request.setAttribute("model", StringUtils.formatData(model));
			//���״̬����
			request.setAttribute("shztmc", oneXwxxList.get("shztmc"));
			return mapping.findForward("viewXwxx");
		} else {
			return updateXwxx(mapping, form, request, response);
		}
		
	}
	
	/**
	 * �Զ��嵼������
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-12 ����01:43:26
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwxxwhForm model = (RcxwxxwhForm) form;
		RcxwxxwhService service = new RcxwxxwhService();

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
	 * 
	 * @����:��ѯ��ȡ��Ϊ���༯��
	 * @���ߣ�HongLin [���ţ�707]
	 * @���ڣ�2014-2-21 
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
	public ActionForward getXwdlList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��Ϊ���༯��
		RcxwxxwhService service = new RcxwxxwhService();
		List<HashMap<String,String>> xwdlList = service.getXwdlList(request);
		String json = JSONArray.fromCollection(xwdlList).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/**
	 * 
	 * @����:��ѯ��ȡ��Ϊ�����Ϣ
	 * @���ߣ�HongLin [���ţ�707]
	 * @���ڣ�2014-2-21 ����10:51:46
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
	public ActionForward getXwlbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lbdm = request.getParameter("rcxwlbdm");
		//��Ϊ���༯��
		RcxwxxwhService service = new RcxwxxwhService();
		List<HashMap<String,String>> xwlbxx = service.getXwlbxx(request,lbdm);
		String json = JSONArray.fromCollection(xwlbxx).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/** 
	 * @����: �ж���Ϣ�Ƿ��ظ�
	 * @���ߣ�HongLin[���ţ�707]
	 * @���ڣ�2014-2-24 ����05:44:20
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
	public ActionForward rcxwxxSfcf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xwlbStr = request.getParameter("xwlbStr");
		String fssjStr = request.getParameter("fssjStr");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		//��Ϊ���༯��
		RcxwxxwhService service = new RcxwxxwhService();
		String message = service.getRcxwxxSfcf(request,xh,xn,xq,xwlbStr,fssjStr);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}

	/**���ظ���*/
	@SystemAuth(url = url)
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwxxwhForm myForm = (RcxwxxwhForm) form;
		RcxwxxwhService service = new RcxwxxwhService();
		
		RcxwxxwhForm model = service.getModel(myForm);
		
		if (model != null && !StringUtil.isNull(model.getFjlj())){
			File file = new File(model.getFjlj());
			if (file.exists()){
				response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(model.getFjmc(),"utf-8")); 
				FileUtil.outputFile(response, file);
			}
		}
		
		return null;
	}
	
	/**ɾ������*/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ճ���Ϊά��-�ճ���Ϊ��Ϣά��-ɾ������FJLJ:{fjlj}")
	public ActionForward deleteFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwxxwhForm myForm = (RcxwxxwhForm) form;
		RcxwxxwhService service = new RcxwxxwhService();
		RcxwxxwhForm model = service.getModel(myForm);
		
		if (model != null && !StringUtil.isNull(model.getFjlj())){
			File file = new File(model.getFjlj());
			if (file.exists()){
				file.delete();
			}
			model.setFjlj("");
			model.setFjmc("");
			service.runUpdate(model);
		}
		
		return null;
	}
}
