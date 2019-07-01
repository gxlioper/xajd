package com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy;



import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������ù���ģ��
 * @�๦������: action 
 * @���ߣ� maxh 
 * @ʱ�䣺 2013-4-19 ����10:17:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: 
 */
public class KnsjcszbjpyAction extends SuperAction {
	
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	
	private static final String url = "xszz_knsdc_wh.do";
	/**
	 * 
	 * @����:��������
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-19 ����10:35:11
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
	public ActionForward jcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsjcszbjpyForm myForm = (KnsjcszbjpyForm) form;
		KnsjcszbjpyService service = new KnsjcszbjpyService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//�Ƿ�list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		//��������б�
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("xszz");
		request.setAttribute("shlcList", shlc);
		String path = "xszz_knsjcszbjpy_wh.do";
		request.setAttribute("path", path);
		request.setAttribute("sqzq", SQZQ);
		request.setAttribute("xnList", Base.getXnndList2());
		request.setAttribute("xqList", Base.getXqList());
		FormModleCommon.commonRequestSet(request);
		KnsjcszbjpyForm model = service.getModel(myForm);
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}else{
			myForm.setXn(Base.currXn);
			if ("xq".equals(SQZQ)) {
				myForm.setXq(Base.currXq);
			}
		}
		return mapping.findForward("jcsz");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward knsrdbjpyJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsjcszbjpyForm myForm = (KnsjcszbjpyForm) form;
		KnsjcszbjpyService service = new KnsjcszbjpyService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//�Ƿ�list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		//��������б�
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("xszz");
		request.setAttribute("shlcList", shlc);
		String path = "xg_xszz_knsrdbjpy_jcsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		KnsjcszbjpyForm model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		return mapping.findForward("knsrdbjpyJcsz");
	}
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-7 ����01:48:29
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
	@SystemLog(description="����ѧ������-�������϶�-��������-����-RSKZJB:{rskzjbView}")
	public ActionForward saveJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KnsjcszbjpyForm myForm = (KnsjcszbjpyForm) form;
		KnsjcszbjpyService service = new KnsjcszbjpyService();
		
		
		// ȡ����������֤�ж�
//		KnsjcszbjpyForm oldForm=service.getModel();
		
//		if((oldForm != null && Constants.OPEN.equals(myForm.getSqkg()) && !myForm.getSplc().equals(oldForm.getSplc()))||Constants.CLOSE.equals(myForm.getSqkg())){
//			KnsrdbjpyService  knsrdbjpy=new KnsrdbjpyService();
//			//�ж��Ƿ��������������
//			boolean isUse=knsrdbjpy.allowUpdateSetting();
//			if(!isUse){
//				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_KNSJCSZ_SHLC_EXIST));
//				return null;
//			}
//		}
		
		myForm.setRskzjb(request.getParameter("rskzjbView"));
		boolean result = service.saveJcsz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:����������̣���ȡ��������
	 * @���ߣ�������
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public ActionForward getShgw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String splc = request.getParameter("splc");
		if (!StringUtil.isNull(splc)) {
			XtwhShlcService xtwhShlc = new XtwhShlcService();
			List<HashMap<String, String>> spjbListByGnmk = xtwhShlc
					.getSpjbListByGnmk(splc);// spgwid Ϊѡ����������̴���ֵ
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONArray.fromObject(spjbListByGnmk));
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����:�������������ò�ѯ
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-12-9 ����10:35:24
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
	public ActionForward knsRssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjcszbjpyForm myForm=(KnsjcszbjpyForm) form;
		KnsjcszbjpyService service = new KnsjcszbjpyService();
		
		List<String>  njList = service.getNj();//�õ����а���ѧ�����꼶
		KnsjcszbjpyForm model=service.getModel();
		if(model==null){
			model=new KnsjcszbjpyForm();
		}
		request.setAttribute("njArrList", njList);
		request.setAttribute("rskzfw", myForm.getRskzfw());
		request.setAttribute("rskznj", model.getRskznj());
		
		if (StringUtil.isNull(myForm.getXn()) || "undefined".equals(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		
		if(StringUtil.isNull(myForm.getXq()) || "undefined".equals(myForm.getXq())){
			if ("xq".equals(SQZQ)){
				myForm.setXq(Base.currXq);
			} else {
				myForm.setXq("on");
			}
		}
		
		// �꼶ѧԺרҵ�༶
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList = service
					.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		return mapping.findForward("knsRssz");
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-12-9 ����04:05:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-�������϶�-��������-��ѯ-��������������-��������-����-FPFS:{fpfs}")
	public ActionForward knsRsszSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KnsjcszbjpyForm myForm = (KnsjcszbjpyForm) form;
		KnsjcszbjpyService service = new KnsjcszbjpyService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String json = request.getParameter("json");
			List<KnsjcszbjpyForm> list = JsonUtil.jsonToList(json,
					KnsjcszbjpyForm.class);
			String messageKey;
			String fpfs = request.getParameter("fpfs");
			String zme = null;
			if (fpfs != null && fpfs.equals("zme")) {//�����ʽ
				zme = request.getParameter("zme");
			}
			String rskznj = request.getParameter("rskznj");//���������꼶
			messageKey = service.fpsz(myForm, list, zme,rskznj);
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}

		KnsjcszbjpyForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		return null;
	
	}
	
	/**
	 * @����:�������Ʒ�Χ�ֶ��޸ģ�ajax��ʱ�ύ����
	 * @���ߣ�cmj 
	 * @���ڣ�2013-12-11 ����11:46:27
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
	public ActionForward changeRskzfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjcszbjpyForm myForm = (KnsjcszbjpyForm) form;
		KnsjcszbjpyService service = new KnsjcszbjpyService();
		boolean result=service.updateRskzfw(myForm);
		String message=result?"true":"false";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * @����:��ȡ���������������
	 * @���ߣ�������
	 * @���ڣ�2013-12-11 ����02:55:22
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
	public ActionForward knsRsszYszrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjcszbjpyForm myForm = (KnsjcszbjpyForm) form;
		KnsjcszbjpyService service = new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszForm=service.getModel();
		String yszrs=service.getYszrs(myForm);
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("yszrs", yszrs);
		if(jcszForm!=null){
			map.put("zme", jcszForm.getZme());
		}else{
			map.put("zme", "0");
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(map));
		return null;
	}
	
	/**
	 * @����:���������޸�
	 * @���ߣ�cmj 
	 * @���ڣ�2013-12-11 ����03:41:30
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
	@SystemLog(description="����ѧ������-�������϶�-��������-��ѯ-��������������-����GUIDS:{guids}")
	public ActionForward rsszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjcszbjpyForm myForm = (KnsjcszbjpyForm) form;
		KnsjcszbjpyService service = new KnsjcszbjpyService();
		String messageKey;
		messageKey = service.setZzrs(myForm);
		response.getWriter().print(getJsonMessage(messageKey));
		return null;
	}
	
	/**
	 * @����:��֤���Ƿ���������
	 * @���ߣ�������
	 * @���ڣ�2013-12-11 ����04:44:48
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
	public ActionForward yzSfszrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KnsjcszbjpyForm myForm = (KnsjcszbjpyForm) form;
		KnsjcszbjpyService service = new KnsjcszbjpyService();
		boolean result=service.checkRsSfsz(myForm);
		String messageKey=result?"true":"false";
		response.getWriter().print(getJsonMessage(messageKey));
		return null;
	}
	
	
	
}
