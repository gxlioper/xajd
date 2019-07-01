package com.zfsoft.xgxt.xszz.knsjcsz;


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

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
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
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;


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
public class JcszAction extends SuperAction {
	
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	
	private static final String url = "xszz_jcsz_wh.do";
	
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
		
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//�Ƿ�list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		//�������϶�ѧ��
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> xnList = dao.getXnndList();
		xnList.remove(0);
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("sqzq", SQZQ);
		request.setAttribute("xxdm", Base.xxdm);
		
		//��������б�
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("xszz");
		request.setAttribute("shlcList", shlc);
		String path = "xszz_jcsz_wh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		JcszForm model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}else{
			myForm.setRdxn(Base.currXn);
		}
		return mapping.findForward("jcsz");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward knsrdJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//�Ƿ�list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		//�������϶�ѧ��
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> xnList = dao.getXnndList();
		xnList.remove(0);
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("sqzq", SQZQ);
		request.setAttribute("xxdm", Base.xxdm);
		
		//��������б�
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("xszz");
		request.setAttribute("shlcList", shlc);
		String path = "xg_xszz_knsrd_jcsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		JcszForm model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}else{
			myForm.setRdxn(Base.currXn);
		}
		return mapping.findForward("knsrdJcsz");
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
		
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		
		
		// ȡ����������֤�ж�
//		JcszForm oldForm=service.getModel();
		
//		if((oldForm != null && Constants.OPEN.equals(myForm.getSqkg()) && !myForm.getSplc().equals(oldForm.getSplc()))||Constants.CLOSE.equals(myForm.getSqkg())){
//			KnsrdService  knsrd=new KnsrdService();
//			//�ж��Ƿ��������������
//			boolean isUse=knsrd.allowUpdateSetting();
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
		JcszForm myForm=(JcszForm) form;
		JcszService service = new JcszService();
		
		List<String>  njList = service.getNj();//�õ����а���ѧ�����꼶
		JcszForm model=service.getModel();
		if(model==null){
			model=new JcszForm();
		}
		request.setAttribute("njArrList", njList);
		request.setAttribute("rskzfw", myForm.getRskzfw());
		request.setAttribute("rskznj", model.getRskznj());
		//myForm.setXn(Base.currXn);
		//myForm.setXq(Base.currXq);
		if(myForm.getXq() == null || "undefined".equals(myForm.getXq())){
			myForm.setXq("on");
		}
		
		request.setAttribute("knsrsszfs", service.getKnsrsszfs());
		request.setAttribute("knsdcList", new KnsdcService().getKnsdcList());
		
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

		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String json = request.getParameter("json");
			List<JcszForm> list = JsonUtil.jsonToList(json,JcszForm.class);
			String messageKey;
			String fpfs = request.getParameter("fpfs");
			String zme = null;
			if (fpfs != null && fpfs.equals("zme")) {//�����ʽ
				zme = request.getParameter("zme");
			}
			String rskznj = request.getParameter("rskznj");//���������꼶
			if(service.getKnsrsszfs().equals("1")){
				rskznj="";
			}
			messageKey = service.fpsz(myForm, list, zme,rskznj);
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}

		JcszForm model = service.getModel(myForm);
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
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
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
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		JcszForm jcszForm=service.getModel();
		String yszrs=service.getYszrs(myForm);
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("yszrs", yszrs);
		if(jcszForm!=null){
			map.put("zme", jcszForm.getZme());
			if(service.getKnsrsszfs().equals("1")){
				map.put("zme", service.getRddcZme(myForm.getRddc()));
			}
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
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
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
		JcszForm myForm = (JcszForm) form;
		JcszService service = new JcszService();
		boolean result=service.checkRsSfsz(myForm);
		String messageKey=result?"true":"false";
		response.getWriter().print(getJsonMessage(messageKey));
		return null;
	}
	
	
	
}
