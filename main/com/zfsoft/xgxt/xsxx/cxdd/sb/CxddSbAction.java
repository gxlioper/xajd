/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-28 ����05:19:51 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.sb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszService;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xsxx.cxdd.comm.CommForm;
import com.zfsoft.xgxt.xsxx.cxdd.comm.CommUtil;
import com.zfsoft.xgxt.xsxx.cxdd.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.cxdd.jg.CxddJgForm;
import com.zfsoft.xgxt.xsxx.cxdd.jg.CxddJgService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-3-28 ����05:19:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxddSbAction extends SuperAction<CxddSbForm, CxddSbService> {
	private static final String url = "xsxx_cxdd_pysb.do";
	private CxddSbService service = new  CxddSbService();
	public ActionForward getPysbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddSbForm model = (CxddSbForm)form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel = new SearchModel();
		String sqshkg =  new CsszService().getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg);
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		request.setAttribute("xn", Base.currXn);
		List<HashMap<String, String>> xqlist = Base.getXqList();
		String xqmc = "";
		for (HashMap<String, String> hashMap : xqlist) {
			if(Base.currXq.equalsIgnoreCase(hashMap.get("xqdm"))){
				xqmc = hashMap.get("xqmc");
			}
		}
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	public ActionForward getXsPageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddSbForm model = (CxddSbForm)form;
		service.UpdateBjtzRecode(model.getBjdms().split(","));
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			model.setFlag("gx");
			List<HashMap<String, String>> resultList = service.getXsPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		request.setAttribute("xn", Base.currXn);
		List<HashMap<String, String>> xqlist = Base.getXqList();
		String xqmc = "";
		for (HashMap<String, String> hashMap : xqlist) {
			if(Base.currXq.equalsIgnoreCase(hashMap.get("xqdm"))){
				xqmc = hashMap.get("xqmc");
			}
		}
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("bjdms",model.getBjdms());
		request.setAttribute("bjid", model.getBjid());
		request.setAttribute("path", "cxdd_pysb.do?method=getXsPageList");
		request.setAttribute("pylist", service.getCxdjdmList());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xscx");
	}
	
	//�ύ
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CxddSbForm myForm = (CxddSbForm) form;
		String value = request.getParameter("values");
		String bjdm = request.getParameter("bjdm");
		service.UpdateBjtzRecode(bjdm.split(","));
		//�����Ȩ���ύ
		if(!service.isHaveQxTj(bjdm)){
			String message = "ѧ���ϱ���Ϣ����������ȷ�ϣ�";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		//ɾ������У����
		service.delCxddbzx(bjdm);
		myForm.setBjid(value);
		CxddSbForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//����
	public ActionForward cancelZssq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String bjdmid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(bjdmid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			CxddSbForm model = new CxddSbForm();
			model.setBjid(bjdmid);
			model.setSplc(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(bjdmid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//����
	public ActionForward saveData(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		CxddSbForm myForm = (CxddSbForm) form;
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		CommForm utilform = new CommForm();
		User user = getUser(request);
		utilform.setXh(myForm.getXh());
		utilform.setXn(Base.currXn);
		utilform.setXq(Base.currXq);
		utilform.setBjdm(myForm.getBjdm());
		utilform.setType("sq");
		boolean flag  = new CommUtil().isNotExistBj(utilform);
        if(flag){
        	myForm.setTjr(user.getUserName());
        	myForm.setSplc(new CsszService().getModel().getSplc());
        	flag = service.runInsert(myForm);
        }else{
        	flag = service.runUpdate(myForm);
        }
        flag = new CommUtil().isNotExists(utilform);
        if(flag){
        	flag = service.saveDataXs(myForm, "insert");
        }else{
        	flag = service.saveDataXs(myForm, "update");
        }
    	
		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	public ActionForward getXsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxddSbForm model = (CxddSbForm)form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			model.setFlag("gx");
			model.setFlag1("ck");
			List<HashMap<String, String>> resultList = service.getXsPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		request.setAttribute("xn", Base.currXn);
		List<HashMap<String, String>> xqlist = Base.getXqList();
		String xqmc = "";
		for (HashMap<String, String> hashMap : xqlist) {
			if(Base.currXq.equalsIgnoreCase(hashMap.get("xqdm"))){
				xqmc = hashMap.get("xqmc");
			}
		}
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("bjdms",model.getBjdms());
		request.setAttribute("bjid", model.getBjid());
		request.setAttribute("path", "cxdd_pysb.do?method=getXsPageList");
		request.setAttribute("pylist", service.getCxdjdmList());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ck");
	}
	
}
