package com.zfsoft.xgxt.xszz.knsrdbjpy.jglr;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyService;

public class JglrAction extends SuperAction {
	
	private static final String url = "xszz_knsrdbjpy_jglr.do";
	
	/**
	 * ��ѯ�༶������¼��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward jglrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JglrForm model = (JglrForm) form;
		JglrService service = new JglrService();
		User user = getUser(request);
		String xh = user.getUserName();
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
		KnsjcszbjpyService jcszService = new KnsjcszbjpyService();
		KnsjcszbjpyForm jcszModel = jcszService.getModel();
		if(jcszModel == null){
			String msg = "��������δ��ʼ��������ϵ����Ա��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("jcszModel", jcszModel);
		
		HashMap<String,String> bjpyxzcyMap = service.queryBjpyxzcy(xh);
		if(bjpyxzcyMap.get("tjzt") == null){
			String msg = "��ģ��ֻ����༶����С���Ա���ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else if("0".equals(bjpyxzcyMap.get("tjzt"))){
			String msg = "�༶����С����δ�ύ����ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		List<HashMap<String,String>> sfknList = new OptionUtil().getOptions(OptionUtil.SFKN);
		request.setAttribute("sfknList", sfknList);
		List<HashMap<String,String>> kndjList = new OptionUtil().getOptions(OptionUtil.KNDJ);
		request.setAttribute("kndjList", kndjList);
		
		HashMap<String,String> xsxxMap = service.queryXsxx(xh);
		request.setAttribute("xsxxMap", xsxxMap);
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{jcszModel.getXn()});
		if(!StringUtil.isNull(jcszModel.getXq())){
			searchModel.setSearch_tj_xq(new String[]{jcszModel.getXq()});
		}
		request.setAttribute("searchTj", searchModel);
		
		String path = "xszz_knsrdbjpy_jglr.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jglrManage");
	}

	/**
	 * ����༶������¼��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="ѧ������-�������϶�-�༶������¼��-����VALUES:{values}")
	public ActionForward bcJglr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JglrForm model = (JglrForm) form;
		JglrService service = new JglrService();
		User user = getUser(request);
		boolean result = service.bcJglr(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
