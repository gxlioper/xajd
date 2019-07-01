/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-26 ����03:22:29 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbgl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.jcsz.JcszForm;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.jcsz.JcszService;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.xssqgl.XssqService;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbjggl.XxsbjgService;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.zbrcgl.ZbrcService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-26 ����03:22:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XxsbAction extends SuperAction {

	private XxsbService service = new XxsbService();
	/**
	 * @���� ѧ����ʾ��Ϣ������
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * @���� ѧ����Ϣ����
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 * ѧ����Ȩservice
	 */
	private XssqService xssqService = new XssqService();
	/**
	 *  @���ԣ� PATH ·��
	 */
	public static final String PATH = "xljk_xlwygl_xxsbgl.do";
	
	public static final String url = "xljk_xlwygl_xxsbgl.do";
	
	public static final String PATH_SH = "xljk_xlwygl_xxsbshgl.do";

	/**
	 * �ܱ��ճ�service
	 */
	private ZbrcService zbrcService = new ZbrcService();
	
	/**
	 * ��������
	 */
	private JcszService jcszService = new JcszService();
	
	/**
	 * ���service
	 */
	private XxsbjgService xxsbjgService = new XxsbjgService();
	
	/**
	 * @���ԣ� CDGL_BBID ������id
	 */
	private static final String BBID = "xlzxxxsbgl"; 
	/**
	 * 
	 * @����:ҳ��dispatcher
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:34:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url)
	public ActionForward cx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String userType = user.getUserType();//��ģ��ֻ����ѧ������
		if(!"stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//ѧ��Ȩ�޲�ѯ
		HashMap<String , String> xssqCheck = xssqService.xssqCheck(user.getUserName());
		if("N".equalsIgnoreCase(xssqCheck.get("bjxlwy")) && 
				"N".equalsIgnoreCase(xssqCheck.get("tsxs")) && 
				"N".equalsIgnoreCase(xssqCheck.get("gygly"))){
			String msg = "��û�и���ҳ�ķ���Ȩ�ޣ�����ϵ����Ա��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//
		request.setAttribute("xssqCheck", xssqCheck);
		request.setAttribute("xnList", Base.getXnndList2());
		request.setAttribute("xqList", Base.getXqList());
		
		XxsbForm myForm = (XxsbForm) form;
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @����:���������б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:43:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url)
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model = (XxsbForm) form;
		User user = getUser(request);
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		if(StringUtil.isNull(model.getXn())) {
			model.setXn(Base.currXn);
		}
		if(StringUtil.isNull(model.getXq())) {
			model.setXq(Base.currXq);
		}
		//��ѯ
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:�鿴
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����09:11:20
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
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model  = (XxsbForm) form;
		HashMap<String , String> sbxxdata = service.getModelMap(model.getSbsqid());
		//�ϱ�����
		String sblx = sbxxdata.get("sblx");
		if(StringUtils.equals("0", sblx)){
			sbxxdata.put("sblxmc", "�༶�����ܱ�");
		}else if(StringUtils.equals("1", sblx)){
			sbxxdata.put("sblxmc", "��Ԣ�����ܱ�");
		}else if(StringUtils.equals("2", sblx)){
			sbxxdata.put("sblxmc", "ƽʱ��Ϣ�ϱ�");
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", Base.getXqmcForXqdm(Base.currXq));
		}
		request.setAttribute("sbxxdata", xgxt.utils.String.StringUtils.formatData(sbxxdata));
		request.setAttribute("path", PATH);
		return mapping.findForward("ck");
	}
	
	
	
	
	/**
	 * 
	 * @����:ҳ��dispatcher
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:34:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward sb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model = (XxsbForm) form;
		HashMap<String , String> sbxx = new HashMap<String, String>();
		//�ϱ�����
		String sblx = model.getSblx();
		//�ϱ��ܱ�id
		String sbzbid = model.getSbzbid();
		JcszForm jcszModel = jcszService.getJcsz();
		
		if(jcszModel == null){
			String msg = "�������ò���ȷ������ϵ����Ա��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//�ܱ���Ϣ
		if(sbzbid != null){
			HashMap<String , String> zbrcModel = zbrcService.getZbrcxx(sbzbid);
			sbxx.put("zbqzrq", zbrcModel.get("zbksrq") + " �� " + zbrcModel.get("zbjsrq"));
			sbxx.putAll(zbrcModel);
		}
		//�ϱ�����
		String splcid = jcszModel.selectSplc(sblx);;
		sbxx.put("sblx", sblx);
		if(StringUtils.equals("0", sblx)){
			sbxx.put("sblxmc", "�༶�����ܱ�");
		}else if(StringUtils.equals("1", sblx)){
			sbxx.put("sblxmc", "��Ԣ�����ܱ�");
		}else if(StringUtils.equals("2", sblx)){
			sbxx.put("sblxmc", "ƽʱ��Ϣ�ϱ�");
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", Base.getXqmcForXqdm(Base.currXq));
		}
		sbxx.put("splcid", splcid);
		request.setAttribute("sbxx", sbxx);
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sb");
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����05:19:28
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
	public ActionForward saveAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model = (XxsbForm) form;
		JSONObject message = new JSONObject();
		User user = getUser(request);
		//����
		String actionType = model.getType();
		//��ȡ��������
		JcszForm jcszModel = jcszService.getJcsz();
		String splc = jcszModel.selectSplc(model.getSblx());
		//�������������
		boolean checkSpl =StringUtils.isBlank(splc) ? false : true;
		
		if((StringUtils.equals(actionType, "submit")||StringUtils.equals(actionType, "saveSubmit")) && (!checkSpl)){
			message = new JSONObject();
			message.put("code", "-1");
			response.getWriter().print(message);
			return null;
		}else if((StringUtils.equals(actionType, "submit")||StringUtils.equals(actionType, "saveSubmit"))&& (checkSpl)){
			String sblx = model.getSblx();
			HashMap<String , String> xssqData = null;
			//����ǰ༶�ܱ���Ҫ�����ְ��������
			if("0".equals(sblx)){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				xssqData = xssqService.getModelData(user.getUserName(), "0");
				String rzjsrq = xssqData.get("rzjsrq");
				if(StringUtils.isNotBlank(rzjsrq)){
					Date rzjsrqDate = df.parse(rzjsrq);
					Date curDate = df.parse(DateUtils.getCurrDate());
					if(curDate.getTime() > rzjsrqDate.getTime()){
						message = new JSONObject();
						message.put("code", "-2"); //��ʾ��ְ���������ѹ����޷��ύ����
						response.getWriter().print(message);
						return null;
					}
				}
				//ƽʱ�ϱ���Ҫ����Ƿ���Ҫƽʱ�ϱ��ֶ�
			}else if("2".equals(sblx)){
				xssqData = xssqService.getModelData(user.getUserName(), "1");
				String sfxypssb = xssqData.get("sfxypssb");
				if("0".equals(sfxypssb)){
					message = new JSONObject();
					message.put("code", "-3"); //�����ϱ�
					response.getWriter().print(message);
					return null;
				}
			}
			
			model.setSplcid(splc);
		}
		
		model.setXh(user.getUserName());
		model.setSbsj(DateUtils.getCurrTime());
		model.setSplcid(splc);
		if("2".equals(model.getSblx())) {
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
		}else {
			HashMap<String , String> xnxqData = service.getXnXqmc(model.getSbzbid());
			model.setXn(xnxqData.get("xn"));
			model.setXq(xnxqData.get("xq"));
		}
		boolean isSuccess = service.saveXxsb(model);
		message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		message.put("code", isSuccess ? "1" : "0");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����:ҳ��dispatcher
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:34:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model = (XxsbForm) form;
		HashMap<String , String> sbxx = new HashMap<String, String>();
		//�ϱ�����
		String sblx = model.getSblx();
		//�ϱ��ܱ�id
		String sbsqid = model.getSbsqid();
		JcszForm jcszModel = jcszService.getJcsz();
		//�ܱ���Ϣ
		if(sbsqid != null){
			XxsbForm data = service.getModel(sbsqid);
			BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(data));
			if(StringUtils.isNotBlank(model.getSbzbid())){}
			HashMap<String , String> zbrcModel = zbrcService.getZbrcxx(model.getSbzbid());
			sbxx.put("zbqzrq", zbrcModel.get("zbksrq") + " �� " + zbrcModel.get("zbjsrq"));
			sbxx.putAll(zbrcModel);
		}
		//�ϱ�����
		String splcid = jcszModel.selectSplc(sblx);;
		sbxx.put("sblx", sblx);
		if(StringUtils.equals("0", sblx)){
			sbxx.put("sblxmc", "�༶�����ܱ�");
		}else if(StringUtils.equals("1", sblx)){
			sbxx.put("sblxmc", "��Ԣ�����ܱ�");
		}else if(StringUtils.equals("2", sblx)){
			sbxx.put("sblxmc", "ƽʱ��Ϣ�ϱ�");
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", Base.getXqmcForXqdm(Base.currXq));
		}
		sbxx.put("splcid", splcid);
		request.setAttribute("sbxx", xgxt.utils.String.StringUtils.formatData(sbxx));
		return mapping.findForward("xg");
	}
	
	
	
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����08:45:16
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
	public ActionForward submitAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model = (XxsbForm) form;
		JSONObject message = new JSONObject();
		//��������model
		JcszForm jcszModel = jcszService.getJcsz();
		String splc = jcszModel.selectSplc(model.getSblx());
		if(StringUtils.isBlank(splc)){
			message = new JSONObject();
			message.put("code", "-1");
			response.getWriter().print(message);
			return null;
		}
		//������������
		if(StringUtils.isBlank(model.getSplcid())){
			model.setSplcid(splc);
		}
		
		/////////////////////////////////////////////////
		User user = getUser(request);
		String sblx = model.getSblx();
		HashMap<String , String> xssqData = null;
		//����ǰ༶�ܱ���Ҫ�����ְ��������
		if("0".equals(sblx)){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			xssqData = xssqService.getModelData(user.getUserName(), "0");
			String rzjsrq = xssqData.get("rzjsrq");
			if(StringUtils.isNotBlank(rzjsrq)){
				Date rzjsrqDate = df.parse(rzjsrq);
				Date curDate = df.parse(DateUtils.getCurrDate());
				if(curDate.getTime() > rzjsrqDate.getTime()){
					message = new JSONObject();
					message.put("code", "-2"); //��ʾ��ְ���������ѹ����޷��ύ����
					response.getWriter().print(message);
					return null;
				}
			}
			
			//ƽʱ�ϱ���Ҫ����Ƿ���Ҫƽʱ�ϱ��ֶ�
		}else if("2".equals(sblx)){
			xssqData = xssqService.getModelData(user.getUserName(), "1");
			String sfxypssb = xssqData.get("sfxypssb");
			if("0".equals(sfxypssb)){
				message = new JSONObject();
				message.put("code", "-3"); //�����ϱ�
				response.getWriter().print(message);
				return null;
			}
		}
		////////////////////////////////////
		
		boolean isSuccess = service.submitXxsb(model);
		String messageKey =isSuccess ? MessageUtil.getText(
				MessageKey.SYS_SUBMIT_SUCCESS) : MessageUtil
				.getText(MessageKey.SYS_SUBMIT_FAIL);
		message = getJsonMessage(messageKey);
		message.put("code", isSuccess ? "1" : "0");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����05:50:52
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
	public ActionForward cancelAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model  = (XxsbForm) form;
		
		boolean isSuccess = service.cancel(model.getSbsqid()); //��������
		
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	//----------------------------���ҳ��----------------------------------//
	
	/**
	 * 
	 * @����:ҳ��dispatcher
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:34:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = PATH_SH)
	public ActionForward shcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		String userType = user.getUserType();//��ģ��ֻ����ѧ������
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH_SH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("shcx");
	}
	
	
	
	/**
	 * 
	 * @����:���������б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:43:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 */
	@SystemAuth(url = PATH_SH)
	public ActionForward shquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model = (XxsbForm) form;
		User user = getUser(request);
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//��ѯ
		List<HashMap<String,String>> resultList = service.getSHPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:���
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����01:38:49
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
	@SystemAuth(url = PATH_SH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model  = (XxsbForm) form;
		String sbsqid = model.getSbsqid();
		String xtgwid = model.getXtgwid();
		String shid = model.getShid();

		HashMap<String , String> sbxxdata = service.getModelMap(sbsqid);
		//�ϱ�����
		String sblx = sbxxdata.get("sblx");
		if(StringUtils.equals("0", sblx)){
			sbxxdata.put("sblxmc", "�༶�����ܱ�");
		}else if(StringUtils.equals("1", sblx)){
			sbxxdata.put("sblxmc", "��Ԣ�����ܱ�");
		}else if(StringUtils.equals("2", sblx)){
			sbxxdata.put("sblxmc", "ƽʱ��Ϣ�ϱ�");
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", Base.getXqmcForXqdm(Base.currXq));
		}
		sbxxdata.put("xtgwid", xtgwid);
		sbxxdata.put("shid", shid);
		request.setAttribute("sbxxdata", sbxxdata);
		HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(sbxxdata.get("xh"));
		request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(BBID);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("sh");
	}
	
	
	/** 
	 * @����:�ύ���
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����02:14:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = PATH_SH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward shAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model  = (XxsbForm) form;
		User user = getUser(request);
		String messageKey ;
		//�����������
		boolean result = service.saveSh(model,user);
		messageKey = result ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����03:22:24
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
	@SystemAuth(url = PATH_SH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelCdshAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model  = (XxsbForm) form;
		XxsbForm dataModel = service.getModel(model.getSbsqid());
		dataModel.setShzt(Constants.YW_SHZ);
		boolean isSuccess = service.runUpdate(dataModel); //���� Model
		if(isSuccess){
			isSuccess = xxsbjgService.deleteBySqid(dataModel.getSbsqid()); //ɾ�����������
		}
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����:�����������
	 * @���ߣ�zhangxiaobin [���ţ�1036]
	 * @���ڣ�2014-4-28 ����11:52:29
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
	@SystemAuth(url = PATH_SH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward plsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model  = (XxsbForm) form;
		User user = getUser(request);
		if("SAVE".equalsIgnoreCase(model.getType())){
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("plsh");
	}
	
	/**
	 * 
	 * @����:�鿴
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����09:11:20
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
	@SystemAuth(url = PATH_SH)
	public ActionForward shck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxsbForm model  = (XxsbForm) form;
		HashMap<String , String> sbxxdata = service.getModelMap(model.getSbsqid());
		//�ϱ�����
		String sblx = sbxxdata.get("sblx");
		if(StringUtils.equals("0", sblx)){
			sbxxdata.put("sblxmc", "�༶�����ܱ�");
		}else if(StringUtils.equals("1", sblx)){
			sbxxdata.put("sblxmc", "��Ԣ�����ܱ�");
		}else if(StringUtils.equals("2", sblx)){
			sbxxdata.put("sblxmc", "ƽʱ��Ϣ�ϱ�");
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", Base.getXqmcForXqdm(Base.currXq));
		}
		request.setAttribute("sbxxdata", sbxxdata);
		request.setAttribute("path", PATH);
		return mapping.findForward("shck");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-8-23 ����04:57:44
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
	@SystemAuth(url = PATH_SH,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelShAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XxsbForm model  = (XxsbForm) form;
		String sbsqid = request.getParameter("sbsqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSbsqid(sbsqid);
		// ���һ������
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
}
