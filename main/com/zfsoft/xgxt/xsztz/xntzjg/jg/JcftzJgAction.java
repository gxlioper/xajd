/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-31 ����11:17:17 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.jg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-31 ����11:17:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcftzJgAction extends SuperAction<JcftzJgForm, JcftzJgService>{
	private static final String url = "sztz_jcftz_jg.do";
	
	private JcftzJgService service = new JcftzJgService();
	
	/** 
	 * @����:��ѯ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-1 ����04:32:06
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
	public ActionForward getJcftzJgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
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
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_jcftz_jg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getJcftzJgList");
	}
	
	/** 
	 * @����:�����Ŀ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-5 ����09:03:57
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
	public ActionForward addJcftzJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
	    User user = getUser(request);
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("currxn", Base.currXn);
		String path = "jcftz_jg.do?method=addJcftzJg";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		//������Ϣ����
		return mapping.findForward("addJcftzJg");
	}
	
	/** 
	 * @����:�õ���Ŀ�б�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-5 ����09:56:53
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
	public ActionForward getXmSelectList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
	    User user = getUser(request);
		List<HashMap<String,String>> xmsqInfoList = service.getXmSelectList(model,user);
	    request.setAttribute("xmsqInfoList", xmsqInfoList);
		return mapping.findForward("xmselect");
	}
	
	/** 
	 * @����:ͨ��ajax�õ���Ŀ�б�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-5 ����02:18:21
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
	public ActionForward getXmSelectListForAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
	    User user = getUser(request);
		List<HashMap<String,String>> xmsqInfoList = service.getXmSelectList(model,user);
		JSONArray dataList = JSONArray.fromObject(xmsqInfoList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/** 
	 * @����:��ȡ��Ҫ�϶���ѧ���б�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-5 ����02:20:02
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
	public ActionForward getXsListForRenDingForAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
		String csms = model.getCsms();
	    User user = getUser(request);
	    List<HashMap<String,String>> xmsqInfoList = null;
	    if("1".equals(csms)){
	    	 xmsqInfoList = service.getXsListForRenDing(model,user);
	    }else if("2".equals(csms)){
	    	xmsqInfoList = service.getTtListForRenDing(model, user);
	    }else{
	    	//���csms(����ģʽ)�ֶ�ֵ������1Ҳ������2��ֱ�ӷ��ؿ�
	    	return null;
	    }
		JSONArray dataList = JSONArray.fromObject(xmsqInfoList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-5 ����06:37:04
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
	public ActionForward savejg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
		model.setXhs(request.getParameterValues("xh"));
		model.setJxdms(request.getParameterValues("jxdm"));
		model.setSfqqs(request.getParameterValues("sfqq"));
		model.setTzhjcfs(request.getParameterValues("tzhjcf"));
		model.setJgids(request.getParameterValues("jgidss"));
		
		
		if(null!=request.getParameterValues("bz1")){		//������ͨ��ע1-5
			model.setBz1s(request.getParameterValues("bz1"));
			model.setBz2s(request.getParameterValues("bz2"));
			model.setBz3s(request.getParameterValues("bz3"));
			model.setBz4s(request.getParameterValues("bz4"));
			model.setBz5s(request.getParameterValues("bz5"));
		}else{
			int n=model.getSfqqs().length;
			String[] strs=new String[n];
			model.setBz1s(strs);
			model.setBz2s(strs);
			model.setBz3s(strs);
			model.setBz4s(strs);
			model.setBz5s(strs);
		}
		boolean result = service.saveJg(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;						
	}
	
	/** 
	 * @����:ɾ�����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-8 ����02:05:55
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
	public ActionForward delJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
		String xmdms = request.getParameter("xmdms");
		String[] xmdmArr = xmdms.split(",");
		List<String> xmdmList = new ArrayList<String>();
		for(int i = 0;i<xmdmArr.length;i++){
			if(xmdmList.contains(xmdmArr[i])){
				continue;
			}else{
				xmdmList.add(xmdmArr[i]);
			}			
		}
		Boolean result = true;
		for(String xmdm : xmdmList){
			model.setXmdm(xmdm);
			model.setXfrdjgzt("0");
		    result = service.delForJg(model);
		    result = service.updateRenDing(model);
		}
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS
				: MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @����:�޸�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-8 ����03:34:54
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
	public ActionForward editJcftzJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
		Map<String, String> map = service.getXmxx(model);
		request.setAttribute("rs", map);
		String path = "jcftz_jg.do?method=editJcftzJg";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		//������Ϣ����
		return mapping.findForward("editJcftzJg");
	}
	
	/** 
	 * @����:�õ���Ҫ�޸��϶���ѧ���б�ͨ��ajax��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-8 ����03:49:08
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
	public ActionForward getXsListForUpdateForAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzJgForm model = (JcftzJgForm) form;
	    User user = getUser(request);
		List<HashMap<String,String>> xmsqInfoList = service.getXsListForUpdate(model,user);
		JSONArray dataList = JSONArray.fromObject(xmsqInfoList);
		response.getWriter().print(dataList);
		return null;
	}
	
}
