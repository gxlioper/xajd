/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-12 ����09:36:20 
 */  
package com.zfsoft.xgxt.xsxx.cxpd.sqsh;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.cxdj.CxdjForm;
import com.zfsoft.xgxt.xsxx.cxdj.CxdjService;
import com.zfsoft.xgxt.xsxx.cxpd.cssz.CxpdCsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��������-������� 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2015-1-14 ����03:55:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class CxpdAction extends SuperAuditAction<CxpdModel, CxpdService> {

	protected CxpdAction (String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}

	public CxpdAction(){
		this("xsxx_cxpd", "cxpd_pdsb.do", "cxpd_pdsh.do");
	}
	
	
	/**
	 * 
	 * @����: ���������ϱ�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-1-14 ����04:22:13
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
	@SystemAuth(url = "cxpd_pdsb.do")
	public ActionForward pdsbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())){
			request.setAttribute("message", "����Ȩ���ʴ�ҳ");
			return mapping.findForward("prompt");
		}
		
		CxpdCsszService csszService = new CxpdCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		request.setAttribute("path", "cxpd_pdsb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("pdsbList");
	}
	
	
	/**
	 * 
	 * @����: ajax���ز����ϱ��б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-1-14 ����04:22:29
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
	@SystemAuth(url = "cxpd_pdsb.do")
	public ActionForward getPdsbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CxpdModel model = (CxpdModel) form;
		CxpdService service = new CxpdService();
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**
	 * 
	 * @����: ���������ϱ�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-1-14 ����04:22:52
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
	public ActionForward pdsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CxpdModel model = (CxpdModel) form;
		
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
		
		CxpdCsszService csszService = new CxpdCsszService();
		request.setAttribute("cssz", csszService.getModel());
		
		CxdjService cxdjService = new CxdjService();
		request.setAttribute("cxdjList", cxdjService.getAllList(new CxdjForm()));
		
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		request.setAttribute("nowTime", time);
		
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		request.setAttribute("path", "cxpdSqsh.do?method=pdsb");
		return mapping.findForward("pdsb");
	}
	
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CxpdModel cxpdForm = (CxpdModel) form;
		CxpdService service = new CxpdService();
		
		CxpdModel model = service.getModel(cxpdForm);
		
		if (model != null){
			BeanUtils.copyProperties(cxpdForm, model);
			
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
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		CxdjService cxdjService = new CxdjService();
		request.setAttribute("cxdjList", cxdjService.getAllList(new CxdjForm()));
		
		CxpdCsszService csszService = new CxpdCsszService();
		request.setAttribute("cssz", csszService.getModel());
		return mapping.findForward("xgsq");
	}
	
	
	/**ȡ������**/
	public ActionForward qxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxpdModel djjdForm = (CxpdModel) form;
		CxpdService service = new CxpdService();
		
		boolean result = service.runDelete(new String[]{djjdForm.getId()}) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	/**
	 * 
	 * @����: ������������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-1-14 ����04:25:16
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
	@SystemAuth(url = "cxpd_pdsh.do")
	public ActionForward pdshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "cxpd_pdsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("pdshList");
	}
	
	
	/**
	 * 
	 * @����: ajax���ز��������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-1-14 ����04:25:32
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
	@SystemAuth(url = "cxpd_pdsh.do")
	public ActionForward getPdshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxpdModel model = (CxpdModel) form;
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
	
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-1-14 ����04:26:00
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
	public ActionForward pdsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxpdModel cxpdForm = (CxpdModel) form;
		CxpdService service = new CxpdService();
		
		CxpdModel model = service.getModel(cxpdForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(cxpdForm, model);
			
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
		
		return mapping.findForward("pdsh");
	}
	
	
	/**�鿴����*/
	
	public ActionForward cksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxpdModel djjdForm = (CxpdModel) form;
		CxpdService service = new CxpdService();
		
		CxpdModel model = service.getModel(djjdForm.getId());
		
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


	/**��ѯ�Ѿ����ڵļ�¼��**/
	public ActionForward getExistsCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxpdModel model = (CxpdModel) form;
		CxpdService service = new CxpdService();
		
		String count = service.getCount(model);
		response.getWriter().print(count);
		
		return null;
	}


}
