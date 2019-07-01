/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018��5��22�� ����5:59:02 
 */  
package xsgzgl.gyjc.jcsd;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018��5��22�� ����5:59:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CcrcAction extends SuperAction<CcrcForm, CcrcService> {
	private static final String url = "xg_gyjc_ccrcsz.do";
	private CcrcService service = new  CcrcService();
	private static final String FWQXTSY = "�˲˵�ֻ��У���û���Ժ���û�ʹ�ã�";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private String[] lddms ;
	private String[] qshs ;
	
	public ActionForward getCcrcList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
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
	
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		String currXn = Base.currXn;
		String currXq = Base.currXq;

		model.setXn(currXn);
		model.setXqdm(currXq);
		request.setAttribute("xn", currXn);
		request.setAttribute("xqmc", Base.getXqmcForXqdm(currXq));
		request.setAttribute("xq", currXq);
		/**
		 * ����ά����Сֵ
		 */
		request.setAttribute("minDate", GetTime.getTimeByFormat(DATE_FORMAT));
		return mapping.findForward("add");
	}
	
	
	public ActionForward saveCcRc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		/**
		 * �ж������Ƿ�������ճ����ص����ص���������
		 */
		boolean rs = service.checkIfRqIntersection(model.getJcrq());
		if(!rs){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XG_GYJC_DEL_RQ_REPEAT_TS));
			return null;
		}
		CcrcService service = new CcrcService();
		 rs = service.saveCcRc(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		CcrcForm myForm = service.getModel(model);
		if(myForm != null){
			BeanUtils.copyProperties(model, myForm);
		}else{
			request.setAttribute("message", "��ǰѡ���޸ĵ��ճ��ѱ������û�ɾ����");
			return mapping.findForward("error");
		}
		request.setAttribute("xqmc", Base.getXqmcForXqdm(myForm.getXqdm()));
		request.setAttribute("xn", myForm.getXn());
		return mapping.findForward("update");
	}
	
	public ActionForward saveCcrcUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		boolean rs = service.checkIfRqIntersection(model.getJcrq());
		if(!rs){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XG_GYJC_DEL_RQ_REPEAT_TS));//�����ظ�
			return null;
		}
		rs = service.runUpdate(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	public ActionForward delCcrc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {//�ж�Ҫɾ�����ճ��Ƿ��������
		
		String rcid = request.getParameter("rcid");
		//�����ճ��Ƿ��Ѿ����ų������
		boolean rs = service.checkIfExistTj(rcid);
		if(!rs){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XG_GYJC_DEL_TJ_TS));
			return null;
		}
//	
		CcrcService tranService = new CcrcService();
		rs = tranService.delCcrcTran(rcid,true); 
		String messageKey = rs ? MessageKey.SYS_DEL_SUCCESS:MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	public ActionForward fenpei(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xg_gyjc_ccry.do?method=fenpei");
		//���û�����û�ҳ��
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("xydm",user.getUserDep());
		request.setAttribute("ccid", model.getCcid());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fenpei");

	}
	
	
	
	public ActionForward fpPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getRyfpList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xg_gyjc_ccry.do?method=fpPerson");
		lddms =model.getLddms().split(",");
		qshs = model.getQshs().split(",");
		//���û����,ѧԺ���룬��������û�ҳ��
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("ccid", model.getCcid());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("searchRyfp");
	}
	
	
	
	public ActionForward saveRyFp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm)form;
		CcrcService jcsdService = TransactionControlProxy.newProxy(new CcrcService());
		boolean rs = jcsdService.saveRyFp(model,lddms,qshs);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	public ActionForward insertQs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm)form;
		String[] lddm = model.getLds();
		String[] qsh = model.getQss();
		CcrcService jcsdService = TransactionControlProxy.newProxy(new CcrcService());
		boolean rs = jcsdService.saveRyFp(model,lddm,qsh);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	public ActionForward cancelRyfp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm)form;
		User user = getUser(request);
		CcrcService jcsdService = TransactionControlProxy.newProxy(new CcrcService());
		boolean rs = jcsdService.cancelRyfp(model, user.getUserStatus());
		String messageKey = rs ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_OPERATE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	public ActionForward updateRyFp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm)form;
		CcrcService jcsdService = TransactionControlProxy.newProxy(new CcrcService());
		boolean rs = jcsdService.updateRyFp(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	public void todayCc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.gettodayCcList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
		}
	

	}
	
	public ActionForward updateCcrList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getRyfpList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xg_gyjc_ccry.do?method=updateRy");
		//���û����,ѧԺ���룬��������û�ҳ��
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("ccid", model.getCcid());
		request.setAttribute("xgzgh", model.getXgzgh());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("updateRyfp");
	}
	
	public ActionForward updateQsList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcrcForm model = (CcrcForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
				List<HashMap<String, String>> resultList = service.getList(model, user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			if("yfp".equalsIgnoreCase(model.getType()))
			{
				List<HashMap<String, String>> resultList = service.getqsfpList(model, user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			
			
		
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xg_gyjc_ccry.do?method=updateQs");
		//���û�����û�ҳ��
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("xydm",user.getUserDep());
		request.setAttribute("ccid", model.getCcid());
		request.setAttribute("zgh", model.getZgh());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("updateQs");

	}
	
	
	

}
