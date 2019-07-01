/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-18 ����11:21:07 
 */  
package com.zfsoft.xgxt.jjgl.jjzg;

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

import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.jjgl.cssz.CsszService;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjForm;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjService;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkForm;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @�๦������: �ҽ��ʸ�
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-8-18 ����11:21:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JjzgAction extends SuperAuditAction<JjzgForm, JjzgService> {

	protected JjzgAction(String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}

	public JjzgAction(){
		this("xsjj_zgsq", "xsjj_zgsq.do", "xsjj_zgsh.do");
	}
	
	private static final String url = "xsjj_zgsq.do";
	
	/**
	 * 
	 * @����:�ҽ��ʸ�����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-18 ����11:23:49
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
	public ActionForward zgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		JjzgService service = getService();
		
		// ����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
		request.setAttribute("jbxx", xsjbxx);
		
		//����ѧ���ҽ���Ϣ
		JjzgForm model = service.getModel(user.getUserName());
		
		//�ҽ��꼶�б�
		JjnjService jjnjService = new JjnjService();
		List<HashMap<String,String>> jjnjList = jjnjService.getAllList(new JjnjForm());
		request.setAttribute("jjnjList", jjnjList);
		
		//�ҽ�ѧ���б�
		JjxkService jjxkService = new JjxkService();
		List<HashMap<String,String>> jjxkList = jjxkService.getAllList(new JjxkForm());
		request.setAttribute("jjxkList", jjxkList);
		// ���ݲ������ü��� ����������Ϣ
		CsszService csszService = new CsszService();
		request.setAttribute("cssz", csszService.getConfigMap());
		
		request.setAttribute("path", "xsjj_zgsq.do");
		FormModleCommon.commonRequestSet(request);
		
		if (model != null){
			BeanUtils.copyProperties(form, model);
			
			List<HashMap<String,String>> shxxList = ShlcUtil.getShlcInfo(model.getSqid());
			request.setAttribute("shxxList", shxxList);
			
			if (!Constants.YW_WTJ.equals(model.getShzt())){
				return mapping.findForward("zgsqView");
			}
		}
		
		return mapping.findForward("zgsq");
	}
	
	
	/**�ҽ̸�������**/
	@SystemAuth(url = url)
	public ActionForward grzl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		JjzgService service = getService();
		
		//����ѧ���ҽ���Ϣ
		JjzgForm jjzgModel = service.getModel(user.getUserName());
		
		if (jjzgModel == null || !Constants.YW_TG.equals(jjzgModel.getShzt())){
			request.setAttribute("message", "δ��üҽ��ʸ���Ȩ���ʴ�ҳ�棡");
			return mapping.findForward("error");
		}
		
		// ����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
		request.setAttribute("jbxx", xsjbxx);
		
		//����ѧ���ҽ���Ϣ
		JjzgForm model = service.getModel(user.getUserName());
		
		if (model != null){
			BeanUtils.copyProperties(form, model);
		}
		
		request.setAttribute("path", "xsjj_grzl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("grzl");
	}
	

	/**
	 * 
	 * @����: �ҽ��ʸ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-25 ����08:58:21
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
	@SystemAuth(url = "xsjj_zgsh.do")
	public ActionForward zgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "xsjj_zgsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zgsh");
	}
	
	
	/**
	 * 
	 * @����: �ʸ�����б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-25 ����08:59:55
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
	@SystemAuth(url = "xsjj_zgsh.do")
	public ActionForward getZgshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JjzgForm model = (JjzgForm) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//��ѯ
		List<HashMap<String,String>> resultList = getService().getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	/**
	 * 
	 * @����: �ҽ��ʸ񵥸����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-25 ����04:25:25
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
	@SystemAuth(url = "xsjj_zgsh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward jjzgDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JjzgForm jjzgForm = (JjzgForm) form;
		//����ѧ���ҽ���Ϣ
		JjzgForm model = getService().getModel(jjzgForm.getSqid());
		
		// ����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		if (model != null){
			BeanUtils.copyProperties(form, model);
		}
		
		return mapping.findForward("jjzgDgsh");
	}
	
	
	/**
	 * 
	 * @����: �ҽ��ʸ�鿴
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-29 ����09:43:39
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
	
	public ActionForward jjzgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JjzgForm jjzgForm = (JjzgForm) form;
		//����ѧ���ҽ���Ϣ
		JjzgForm model = getService().getModel(jjzgForm.getXh());
		
		// ����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		if (model != null){
			BeanUtils.copyProperties(form, model);
		
			//�ҽ̾���
			List<HashMap<String, String>> jjjlList = getService().getJJjlList(model.getXh());
			request.setAttribute("jjjlList", jjjlList);
		}
		
		
		
		return mapping.findForward("jjzgView");
	}
}
