/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-19 ����09:32:32 
 */  
package com.zfsoft.xgxt.xsxx.kzxxgl.jg;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.extend.service.ExtendService;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.rcsw.zdzm.jggl.ZdzmJgService;
import com.zfsoft.xgxt.rcsw.zdzm.sh.ZdzmShForm;
import com.zfsoft.xgxt.rcsw.zdzm.sh.ZdzmShService;
import com.zfsoft.xgxt.xsxx.kzxxgl.sh.XsxxKzxxglShForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-19 ����09:32:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxxKzxxglJgAction extends SuperAction<XsxxKzxxglJgForm, XsxxKzxxglJgService> {
	
	private static final String url = "xsxx_kzxxgl_jggl.do";

	private XsxxKzxxglJgService service = new XsxxKzxxglJgService();
	
	private ExtendService extendService = new ExtendService();
	
	public static final String DATA_EXTEND_MODULE = "XSXX";
	/**
	 * 
	 * @����:�б�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-4 ����04:44:38
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
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxKzxxglJgForm model  = (XsxxKzxxglJgForm) form;
		User user = getUser(request);
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if (QUERY.equalsIgnoreCase(model.getActionType())){
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
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xsxx_kzxxgl_jggl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jgManage");
	}
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-19 ����05:47:33
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
		XsxxKzxxglJgForm model  = (XsxxKzxxglJgForm) form;
		XsxxKzxxglJgForm dataModel = service.getModel(model.getJgid());
		if(null != dataModel){
			BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		}
		model.setExendDateModuleId(DATA_EXTEND_MODULE);
		return mapping.findForward("ck");
	} 
	
}
