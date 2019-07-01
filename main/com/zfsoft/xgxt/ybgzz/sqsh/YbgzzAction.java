/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-12 ����09:36:20 
 */  
package com.zfsoft.xgxt.ybgzz.sqsh;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.ybgzz.cssz.YbgzzCsszService;
import com.zfsoft.xgxt.ybgzz.cywh.YbcyService;

/**
 * 
 * @�๦������: �װ๤��վ 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-1-29 ����09:09:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YbgzzAction extends SuperAuditAction<YbgzzModel, YbgzzService> {

	protected YbgzzAction (String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}

	public YbgzzAction(){
		this("ybgzz", "ybgzz_xssq.do", "ybgzz_jssh.do");
	}
	
	
	/**
	 * 
	 * @����:��ѯ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-1-29 ����09:14:27
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
	@SystemAuth(url = "ybgzz_xssq.do")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YbgzzCsszService csszService = new YbgzzCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "ybgzz_xssq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("list");
	}
	
	
	/**ajax��ѯ**/
	@SystemAuth(url = "ybgzz_xssq.do")
	public ActionForward getList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YbgzzModel model = (YbgzzModel) form;
		YbgzzService service = new YbgzzService();
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	/**����**/
	@SystemAuth(url = "ybgzz_xssq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YbgzzModel model = (YbgzzModel) form;
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			YbcyService ybcyService = new YbcyService();
			boolean isExists = ybcyService.isExists(model.getXh());
			boolean isSqExists = ybcyService.isSqExists(model.getXh());
			request.setAttribute("isExists", isExists);
			request.setAttribute("isSqExists", isSqExists);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		
		YbgzzCsszService csszService = new YbgzzCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "ybgzzSqsh.do?method=add");
		return mapping.findForward("add");
	}
	
	
	/**�޸�**/
	@SystemAuth(url = "ybgzz_xssq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YbgzzModel djjdForm = (YbgzzModel) form;
		YbgzzService service = new YbgzzService();
		
		YbgzzModel model = service.getModel(djjdForm);
		
		if (model != null){
			BeanUtils.copyProperties(djjdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		YbgzzCsszService csszService = new YbgzzCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("edit");
	}
	
	
	/**ȡ������**/
	@SystemAuth(url = "ybgzz_xssq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YbgzzModel djjdForm = (YbgzzModel) form;
		YbgzzService service = new YbgzzService();
		
		boolean result = service.runDelete(new String[]{djjdForm.getId()}) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @����:����б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-1-29 ����09:17:25
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
	@SystemAuth(url = "ybgzz_jssh.do")
	public ActionForward jsshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "ybgzz_jssh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jsshList");
	}
	
	
	/**
	 * 
	 * @����: ajax��������б�
	 * @���ߣ������[���ţ�445]
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
	@SystemAuth(url = "ybgzz_jssh.do")
	public ActionForward getJsshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YbgzzModel model = (YbgzzModel) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//��ѯ
		List<HashMap<String,String>> resultList = getService().getAudingList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**��ʦ���**/
	@SystemAuth(url = "ybgzz_jssh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward jssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YbgzzModel myForm = (YbgzzModel) form;
		YbgzzService service = new YbgzzService();
		
		YbgzzModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("jssh");
	}
	
	
	/**�鿴����*/
	@SystemAuth(url = "ybgzz_jssh.do")
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YbgzzModel djjdForm = (YbgzzModel) form;
		YbgzzService service = new YbgzzService();
		
		YbgzzModel model = service.getModel(djjdForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(djjdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("view");
	}
	
	
}
