/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-12 ����09:36:20 
 */  
package com.zfsoft.xgxt.xsxx.djjd.sqsh;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.djjd.cssz.DjjdCsszService;
import com.zfsoft.xgxt.xsxx.djjd.dmwh.JddjDmwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �����ȼ�����
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-11-12 ����09:36:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DjjdAction extends SuperAuditAction<DjjdModel, DjjdService> {

	protected DjjdAction (String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}

	public DjjdAction(){
		this("ntgm_djjd", "ntgm_jjdj_sq.do", "ntgm_jjdj_sh.do");
	}
	
	
	/**�����ȼ�����ҳ��**/
	@SystemAuth(url = "ntgm_jjdj_sq.do")
	public ActionForward djjdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DjjdCsszService csszService = new DjjdCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "ntgm_jjdj_sq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("djjdList");
	}
	
	
	/**�����ȼ�������ѯ**/
	@SystemAuth(url = "ntgm_jjdj_sq.do")
	public ActionForward getDjjdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DjjdModel model = (DjjdModel) form;
		DjjdService service = new DjjdService();
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	/**�����ȼ�����������**/
	@SystemAuth(url = "ntgm_jjdj_sq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward jdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DjjdModel model = (DjjdModel) form;
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		DjjdCsszService csszService = new DjjdCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		JddjDmwhService dmwhService = new JddjDmwhService();
		List<HashMap<String, String>> xmdmList = dmwhService.getListByType(JddjDmwhService.Type.XM);
		List<HashMap<String, String>> xmjbList = dmwhService.getListByType(JddjDmwhService.Type.JB);
		
		request.setAttribute("xmdmList", xmdmList);
		request.setAttribute("xmjbList", xmjbList);
		
		request.setAttribute("path", "jddj_sqsh.do?method=jdsq");
		return mapping.findForward("jdsq");
	}
	
	
	/**�����ȼ������޸�**/
	@SystemAuth(url = "ntgm_jjdj_sq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DjjdModel djjdForm = (DjjdModel) form;
		DjjdService service = new DjjdService();
		
		DjjdModel model = service.getModel(djjdForm);
		
		if (model != null){
			BeanUtils.copyProperties(djjdForm, model);
			
			if (!StringUtil.isNull(model.getXh())){
				//����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
		}
		
		JddjDmwhService dmwhService = new JddjDmwhService();
		List<HashMap<String, String>> xmdmList = dmwhService.getListByType(JddjDmwhService.Type.XM);
		List<HashMap<String, String>> xmjbList = dmwhService.getListByType(JddjDmwhService.Type.JB);
		
		request.setAttribute("xmdmList", xmdmList);
		request.setAttribute("xmjbList", xmjbList);
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz("xsxxgl");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		return mapping.findForward("xgsq");
	}
	
	
	/**ȡ������**/
	@SystemAuth(url = "jddj_sqsh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-�����ȼ���������ͨ��ó��-�������-ȡ������PK:{id}")
	public ActionForward qxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DjjdModel djjdForm = (DjjdModel) form;
		DjjdService service = new DjjdService();
		
		boolean result = service.runDelete(new String[]{djjdForm.getId()}) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @����: ��������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-25 ����03:37:30
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
	@SystemAuth(url = "ntgm_jjdj_sh.do")
	public ActionForward jdshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "ntgm_jjdj_sh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jdshList");
	}
	
	
	/**
	 * 
	 * @����: ajax���ش�������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-25 ����03:39:06
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
	@SystemAuth(url = "ntgm_jjdj_sh.do")
	public ActionForward getJdshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DjjdModel model = (DjjdModel) form;
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
	
	
	/**�����ȼ��������**/
	@SystemAuth(url = "ntgm_jjdj_sh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward jdsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DjjdModel djjdForm = (DjjdModel) form;
		DjjdService service = new DjjdService();
		
		DjjdModel model = service.getModel(djjdForm.getId());
		
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
		
		return mapping.findForward("jdsh");
	}
	
	
	/**�鿴����*/
	@SystemAuth(url = "ntgm_jjdj_sh.do")
	public ActionForward cksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DjjdModel djjdForm = (DjjdModel) form;
		DjjdService service = new DjjdService();
		
		DjjdModel model = service.getModel(djjdForm.getId());
		
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
		
		return mapping.findForward("cksq");
	}
}
