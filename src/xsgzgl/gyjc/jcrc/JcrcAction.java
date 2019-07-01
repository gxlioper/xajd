package xsgzgl.gyjc.jcrc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import xsgzgl.gyjc.jcsd.JcsdForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;

public class JcrcAction extends SuperAction<JcrcForm, JcrcService> {
	private JcrcService service = new  JcrcService();
	private static final String url = "xg_gyjc_jcrc.do";
	private static final String FWQXTSY = "�˲˵�ֻ��У���û���Ժ���û�ʹ�ã�";
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 
	 * @����: ��ѯ����ճ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-13 ����02:22:28
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
	public ActionForward getJcrcList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JcrcForm model = (JcrcForm) form;
		User user = getUser(request);
		if(!"xx".equals(user.getUserStatus()) && ! "xy".equals(user.getUserStatus())){
			request.setAttribute("message",FWQXTSY);
			return mapping.findForward("error");
		}
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		//���û�����û�ҳ��
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("xydm",user.getUserDep());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("search");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-14 ����02:25:23
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
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JcrcForm model = (JcrcForm) form;
		User user = getUser(request);
		String currXn = Base.currXn;
		String currXq = Base.currXq;
		model.setJs(user.getUserStatus());
		model.setXn(currXn);
		model.setXq(currXq);
		String jsmc = "";
		if("xy".equals(user.getUserStatus())){
			model.setXydm(user.getUserDep());
			jsmc = "Ժ��";
		}else{
			jsmc = "У��";
		}
		request.setAttribute("jsmc", jsmc);
		request.setAttribute("jcmxList", service.getJcrcAddList(model));
		request.setAttribute("xn", currXn);
		request.setAttribute("xqmc", Base.getXqmcForXqdm(currXq));
		request.setAttribute("xq", currXq);
		/**
		 * ����ά����Сֵ
		 */
		request.setAttribute("minDate", GetTime.getTimeByFormat(DATE_FORMAT));
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @����: �������ճ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����09:27:51
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
	@SystemLog(description = "������������-����ճ�-����ճ�-����[ȫ��]JS:{js},XYDMS:{xydms},JCBLS:{jcbls}")
	public ActionForward saveJcRc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JcrcForm model = (JcrcForm) form;
		String[] xydms = request.getParameterValues("xydm");
		String[] jcbls = request.getParameterValues("jcbl");
		String[] lddms = request.getParameterValues("lddm");
		User user = getUser(request);
		model.setXydms(xydms);
		model.setJcbls(jcbls);
		model.setJs(user.getUserStatus());
		model.setLddms(lddms);
		if("xy".equals(model.getJs())){
			model.setXydm(user.getUserDep());
		}else{
			model.setXydm(null);
		}
		/**
		 * �ж������Ƿ�������ճ����ص����ص���������
		 */
		String xydm = "xx".equals(user.getUserStatus()) ? "" : user.getUserDep();
		boolean rs = service.checkIfRqIntersection(model.getCcrq(), model.getJzrq(), model.getGuid(), model.getJs(), xydm);
		if(!rs){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XG_GYJC_DEL_RQ_REPEAT_TS));
			return null;
		}
		if(StringUtils.isNotNull(model.getGuid())){
			new JcrcService().delJcrc(model.getGuid(),false);
		}
		JcrcService tranService = TransactionControlProxy.newProxy(new JcrcService());
		 rs = tranService.saveJcRc(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����: �޸�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-14 ����02:25:23
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
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JcrcForm model = (JcrcForm) form;
		User user = getUser(request);
		JcrcForm myForm = service.getModel(model);
		if(myForm != null){
			BeanUtils.copyProperties(model, myForm);
		}else{
			request.setAttribute("message", "��ǰѡ���޸ĵ��ճ��ѱ������û�ɾ����");
			return mapping.findForward("error");
		}
		String jsmc = "";
		if("xy".equals(myForm.getJs())){
			model.setXydm(user.getUserDep());
			jsmc = "Ժ��";
		}else{
			jsmc = "У��";
		}
		request.setAttribute("jsmc", jsmc);
		List<HashMap<String, String>> jcmxList = service.getJcrcAddList(model);
		List<HashMap<String, String>> jcblList = service.getUpdatejcrcmx(model.getGuid());
		List<HashMap<String, String>> ldList = service.getLdxxList(model.getGuid());
		request.setAttribute("xqmc", Base.getXqmcForXqdm(myForm.getXq()));
		request.setAttribute("jcmxList", service.getZhList(jcmxList, jcblList,ldList));
		request.setAttribute("xn", myForm.getXn());
		String disabled = (!service.checkIfExistTj(model.getGuid()) || !service.checkIfLastNew(model.getGuid(), model.getJs(),model.getXydm())) ? "disabled" : "" ;
		request.setAttribute("disabled", disabled);
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-18 ����05:40:37
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
	@SystemLog(description = "���ʹ�Ԣ����-���ù���-У��ס�޽��-ɾ��RCID:{rcid}")
	public ActionForward delJcrc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String rcid = request.getParameter("rcid");
		User user = getUser(request);
		boolean rs = service.checkIfExistTj(rcid);
		if(!rs){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XG_GYJC_DEL_TJ_TS));
			return null;
			
		}
		String xydm = "xx".equals(user.getUserStatus()) ? "" : user.getUserDep();
		rs = service.checkIfLastNew(rcid, user.getUserStatus(),xydm);
		if(!rs){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XG_GYJC_DEL_LASTNEW_TS));
			return null;
		}
		JcrcService tranService = new JcrcService();
		rs = tranService.delJcrcTran(rcid,true); 
		String messageKey = rs ? MessageKey.SYS_DEL_SUCCESS:MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: �����޸ļ���ճ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-19 ����11:40:44
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
	@SystemLog(description = "������������-����ճ�-����ճ̱���[ֻ����]ccrq:{ccrq},jzrq:{jzrq}")
	public ActionForward saveJcrcUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JcrcForm model = (JcrcForm) form;
		User user = getUser(request);
		String xydm = "xx".equals(user.getUserStatus()) ? "" : user.getUserDep();
		boolean rs = service.checkIfRqIntersection(model.getCcrq(), model.getJzrq(), model.getGuid(),user.getUserStatus(), xydm);
		if(!rs){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XG_GYJC_DEL_RQ_REPEAT_TS));
			return null;
		}
		rs = service.runUpdate(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ¥����Ϣ��ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-5-24 ����10:03:06
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
	public ActionForward getSelLdCx(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		JcrcForm model = (JcrcForm) form;
		User user = getUser(request);
		model.setJs(user.getUserStatus());
		String lddms = request.getParameter("lddms");
		String[] lddmAndxydm = lddms.split(",");
		
		List<String> paraList = new ArrayList<String>();
		for (int i = 0; i < lddmAndxydm.length; i++) {
			paraList.add(lddmAndxydm[i].split("-")[0]);
		}
		List<HashMap<String,String>> ldList = service.getSelLdList(model);
		request.setAttribute("LdList", ldList);
		request.setAttribute("ldArray", paraList.toArray(new String[]{}));
		request.setAttribute("xydm", model.getXydm());
		return mapping.findForward("selLd");
	}
}
