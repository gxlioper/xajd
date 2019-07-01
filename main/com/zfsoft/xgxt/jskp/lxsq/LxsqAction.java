package com.zfsoft.xgxt.jskp.lxsq;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import org.apache.struts.upload.FormFile;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.jskp.dmwh.DmwhService;
import com.zfsoft.xgxt.jskp.sbsh.JskpXmsbshForm;
import com.zfsoft.xgxt.jskp.sbsh.JskpXmsbshService;
import common.newp.StringUtil;

public class LxsqAction extends SuperAction<LxsqForm,LxsqService> {
	private LxsqService service = new LxsqService();
	private final String url = "pjpy_jskp_lxsq.do";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DRCGBZ = "����ɹ���";
	public static final String DRSBBZ = "����ʧ��,����ϸ�˶ԡ���������.xls����";
	public static final String KBG = "��excel���";
	public static final String KFILE = "δ�ϴ��ļ���";
	public static final String EXCELREPEAT = "Excle�д����ظ�����(�����ˡ�����ʱ�䡢��Ŀ�����ظ�)������ϸ�˶ԣ�";
	
	/**
	 * 
	 * @����: ���������ѯ��ת
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-6 ����09:48:28
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
	@SystemLog(description = "���ʼ�ʵ����-��������")
	@SystemAuth(url = url)
	public ActionForward getLxsqCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		/**ֻ��2017���Ժ��ѧ����������,��ʦ�û�����*/
		/*��ǰ��¼�û�*/
		User user = getUser(request); 
		boolean stuTj = service.isStandardStu(user);
		if(!stuTj){
			String msg = "��ģ�������2017�����Ժ��ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		request.setAttribute("path", url);
		request.setAttribute("splc", new CsszService().getSplc("lx").get("splc"));
		/*ȡ�������ñ��е��Ƿ�������ֶ�*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lxsqcx");
	}
	
	/**
	 * 
	 * @����: ��ѯ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-6 ����09:51:08
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
	public ActionForward seachForLxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm model = (LxsqForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// ��ѯ
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: ��������������ת
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-6 ����09:54:27
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
	public ActionForward addLxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setAttribute("xmlbList", new DmwhService().getXmlbList());
		//����ʱ�����ֵ��ȡϵͳ��ǰ����
		request.setAttribute("maxtime", GetTime.getTimeByFormat(DATE_FORMAT));
		User user = getUser(request);
		request.setAttribute("fzrxm", user.getRealName());
		request.setAttribute("fzr", user.getUserName());
		//ȡ��ϵ��ʽ
		String lxfs = "";
		if("stu".equals(user.getUserType())){
			lxfs = service.getFzrxxStu(user.getUserName()).get("sjhm");
		}else{
			lxfs = service.getFzrxxTea(user.getUserName()).get("lxdh");
		}
		request.setAttribute("lxfs", lxfs);
		/*ȡ�������ñ��е��Ƿ�������ֶ�*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		return mapping.findForward("addlxsq");
	}
	
	/**
	 * 
	 * @����: �޸���������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-6 ����09:57:48
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
	public ActionForward updateLxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm model = (LxsqForm)form;
		LxsqForm lxsq = service.getModel(model);
		BeanUtils.copyProperties(model, lxsq);
		request.setAttribute("xmlbList", new DmwhService().getXmlbList());
		//����ʱ�����ֵ��ȡϵͳ��ǰ����
		request.setAttribute("maxtime", GetTime.getTimeByFormat(DATE_FORMAT));
		User user = getUser(request);
		request.setAttribute("fzrxm", user.getRealName());
		request.setAttribute("fzr", user.getUserName());
		List<HashMap<String, String>> xhList = service.getXmcyryXhs(lxsq.getSqid());
		request.setAttribute("xhList", xhList);
		StringBuilder xhforTextarea = new StringBuilder();
	    for (int i = 0; i < xhList.size(); i++) {
	    	xhforTextarea.append(xhList.get(i).get("xh")+"\n");
		}
	    request.setAttribute("xhs", xhforTextarea.toString());
	    request.setAttribute("bmmc", service.getBmmc(lxsq.getZdbm()).get("bmmc"));
	    /*ȡ�������ñ��е��Ƿ�������ֶ�*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		return mapping.findForward("updatelxsq");
	}
	
	/**
	 * 
	 * @����: ������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-6 ����09:59:09
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
	public ActionForward saveForLxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm lxsq = (LxsqForm)form;
		String[] xhs = request.getParameterValues("xh");
		lxsq.setXhs(xhs);
		
		/**��ȡ�û�*/
		User user = getUser(request);
		
		/*�������������⣬ֱ����������*/
		/*LxsqService tranService = TransactionControlProxy.newProxy(new LxsqService());*/
		boolean rs = true;
		try {
			rs = new LxsqService().saveForLxsq(lxsq,user);
		} catch (SystemException e) {
			// TODO �Զ����� catch ��
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-6 ����10:01:54
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			if(result){
				result = service.delRys(ids);
			}
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
	 * @����: �ύ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-6 ����10:04:13
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
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm myForm = (LxsqForm) form;
		String value = request.getParameter("values");
		myForm.setSqid(value);
		LxsqForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����: �����ύ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-4-3 ����04:39:06
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
	public ActionForward batchSubmission (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String values = request.getParameter("values");
		if(!StringUtil.isNull(values)){
			boolean result = false;
			int okNum = 0;
			//����ID��ѯѧ���걨��Ŀ����Ϣ
			List<HashMap<String,String>> dataList = service.getStuSbDataList(values.split(","));
			
			for(int i = 0; i < dataList.size(); i++){
				HashMap<String,String> dataMap = dataList.get(i);
				String sqid = dataMap.get("sqid");
				String splc = dataMap.get("splcid");
				String fzr = dataMap.get("fzr");
				result = service.plSubmit(sqid,splc,fzr);
				if (result) {
					okNum++;
				}
			}
			String resultMsg = "�ύ�ɹ�"+okNum+"����";
			response.getWriter().print(getJsonMessage(resultMsg));
		}
		return null;
	}
	
	/**
	 * 
	 * @����: �����ύ��¼
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-6 ����10:05:49
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
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		/*��������Ϊ0ʱ��Ҫ�����ϱ���˱��е����״̬����Ϊ����ֵΪ0ʱ��ȡ����xg_jskp_xmsbb�е����״̬*/
		String sfsh = new CsszService().getSfsh();
		
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		JskpXmsbshService jskpXmsbshService = new JskpXmsbshService();
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			LxsqForm model = new LxsqForm();
			model.setSqid(sqid);
			model.setSplcid(lcid);
			
			/*��������Ϊ0ʱ��ֵ����form*/
			JskpXmsbshForm jskpXmsbshForm = new JskpXmsbshForm();
			jskpXmsbshForm.setSqid(sqid);
			jskpXmsbshForm.setSplcid(lcid);
			
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
				if("0".equals(sfsh)){
					jskpXmsbshForm.setShzt(Constants.YW_YTH);
				}
			} else {
				model.setShzt(Constants.YW_WTJ);
				if("0".equals(sfsh)){
					jskpXmsbshForm.setShzt(Constants.YW_WTJ);
				}
			}
			service.runUpdate(model);
			if("0".equals(sfsh)){
				jskpXmsbshService.runUpdate(jskpXmsbshForm);
			}
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: �鿴��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-6 ����10:10:51
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
	public ActionForward ckLxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm model = (LxsqForm)form;
		LxsqForm lxsq = service.getModel(model);
		BeanUtils.copyProperties(model, lxsq);
		request.setAttribute("xmlbList", new DmwhService().getXmlbList());
		//����ʱ�����ֵ��ȡϵͳ��ǰ����
		request.setAttribute("maxtime", GetTime.getTimeByFormat(DATE_FORMAT));
		User user = getUser(request);
		request.setAttribute("fzrxm", user.getRealName());
		request.setAttribute("fzr", user.getUserName());
		List<HashMap<String, String>> xhList = service.getXmcyryXhs(lxsq.getSqid());
		request.setAttribute("xhList", xhList);
	    request.setAttribute("bmmc", service.getBmmc(lxsq.getZdbm()).get("bmmc"));
	    request.setAttribute("xmlbmc",new DmwhService().getModel(lxsq.getXmlb()).getXmlbmc());
	    /*ȡ�������ñ��е��Ƿ�������ֶ�*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		return mapping.findForward("cklxsq");
	}
	
	/**
	 * 
	 * @����: ��Ա����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-6 ����10:35:26
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
	public ActionForward rysz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String sqid = request.getParameter("sqid");
		LxsqForm model = service.getModel(sqid);
		request.setAttribute("rs",model);
		request.setAttribute("xmlbmc",new DmwhService().getModel(model.getXmlb()).getXmlbmc());
		request.setAttribute("shzt", model.getShzt());
		request.setAttribute("sqid", sqid);
		return mapping.findForward("rysz");
	}
	
	/**
	 * 
	 * @����: ������Ա����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-6 ����10:39:19
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
	public ActionForward saveRysz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm myForm = (LxsqForm) form;
		String[] xhs = request.getParameterValues("xh");
		myForm.setXhs(xhs);
		LxsqService service = TransactionControlProxy.newProxy(new LxsqService());
		boolean rs = service.saveRysz(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
	    response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ��֤ѧ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-7 ����05:03:30
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
	public ActionForward checkXh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		String[] xhs = xh.replaceAll("\r", " ").replaceAll("\n",
		" ").split(" ");
		String ryflag = request.getParameter("ryflag");
		String sqid = request.getParameter("sqid");
		xhs = common.newp.ArrayUtil.removeRepeatElementInArray(xhs);
		List<HashMap<String,String>> AvailableXhList = service.getAvailableXhList(xhs,sqid,ryflag);
		List<String> inAvailableXhList = new ArrayList<String>();
		for (int i = 0; i < xhs.length; i++) {
			String tempXh = xhs[i];
			if(StringUtils.isNotNull(tempXh) && (AvailableXhList != null && !AvailableXhList.isEmpty())){
				//��ȱ�ʶ
				boolean flag = false;
				for (int j = 0; j < AvailableXhList.size(); j++) {
					if(tempXh.equals(AvailableXhList.get(j).get("xh"))){
						flag = true;
						break;
					}
				}
				if(!flag){
					inAvailableXhList.add(tempXh);
				}else{
					flag = false;
				}
				
			}else{
				inAvailableXhList.add(tempXh);
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("use", AvailableXhList);
		jsonObject.put("nouse", inAvailableXhList);
		response.getWriter().print(jsonObject);
		return null;
	}
	
	/**
	 * 
	 * @����: ��ѯ��Ա����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-13 ����08:55:13
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
	public ActionForward searchRysz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm model = (LxsqForm)form;
		// ��ѯ
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getRyszList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**
	 * 
	 * @����: ������Ա����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-13 ����08:50:33
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
	public ActionForward addRy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxsqForm model = (LxsqForm)form;
		request.setAttribute("sqid", model.getSqid());
		request.setAttribute("shzt", model.getShzt());
		return mapping.findForward("addry");
	}
	
	/**
	 * 
	 * @����: ɾ����Ա
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-20 ����04:15:54
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
	public ActionForward delRy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String values = request.getParameter("values");
		if(StringUtils.isNotNull(values)){
			String[] sqids = values.split(",");
			if(!service.isNotHaveShjl(sqids)){
				response.getWriter().print(getJsonMessage("�м�¼�ѱ��û���ˣ��޷���ɾ����"));
				return null;
			}
			boolean rs = service.delXmry(sqids);
			String messageKey = rs ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @����: ���Ի�����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-19 ����05:13:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dataImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("dataImport");
	}
	
	/**
	 * @����: ���ص���ģ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-23 ����11:59:49
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
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename=\""+ new String("jskp_lxsq.xls".getBytes(), "GBK") + "\"");
		service.createWwb(response.getOutputStream());
		return null;
	}
	
	/**
	 * @����: ���뱣��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2018-1-23 ����05:00:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
		LxsqForm model = (LxsqForm)form;
		/**request���ȡ�û�*/
		User user = getUser(request);
		FormFile file = model.getDrmb();
		if(file != null){
			try{
				model.setExclePath(servlet.getServletContext().getRealPath("/temp/importTemp/")+"/");
				HashMap<String,Object> resultMap = service.saveDrExcelInfo(file.getInputStream(),model,user);
				String message = DRCGBZ;
				if(resultMap.get("result").equals("true")){
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if(resultMap.get("result").equals("null")){
					 message = KBG;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if("excelrepeat".equals(resultMap.get("result"))){
					 message = EXCELREPEAT;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}
				else{
				    message = DRSBBZ;
				    Map<String,String> map = new HashMap<String, String>();
					map.put("message", message);
					map.put("gid", (String)resultMap.get("gid"));
					JSONObject json = JSONObject.fromObject(map); 
				    response.getWriter().print(json);
					return null;
				}
				
			}catch (FileNotFoundException e) {
				// TODO �Զ����� catch ��
				logger.info("�����ļ�δ�ҵ���");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				logger.info("IO�쳣��");
				e.printStackTrace();
			}
		}else{
			String message = KFILE;
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
		}
		return null;
	}
	
	/**
	 * @����: ���ش�������
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2018-1-26 ����03:18:32
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
	public ActionForward downloadFileError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//�õ�tomcat/webapp/temp/importTemp�´����ļ���·��
		String filename = request.getParameter("filename");
		String path = servlet.getServletContext().getRealPath("/temp/importTemp/")+"/"+filename;
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("��������.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}

}
