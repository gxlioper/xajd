/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-19 ����02:24:09 
 */  
package com.zfsoft.xgxt.jjgl.jjxq;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjService;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkService;
import com.zfsoft.xgxt.jjgl.jjzg.JjzgForm;
import com.zfsoft.xgxt.jjgl.jjzg.JjzgService;
import com.zfsoft.xgxt.jjgl.xqwh.XqwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @�๦������: �ҽ�����
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-8-19 ����02:24:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JjxqAction extends SuperAction<JjxqForm, JjxqService> {
	
	private static final String url = "xsjj_jjsc.do";

	/**
	 * 
	 * @����: �ҽ��г�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-19 ����02:31:22
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
	public ActionForward jjsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		JjzgService service = new JjzgService();
		
		//����ѧ���ҽ���Ϣ
		JjzgForm model = service.getModel(user.getUserName());
		
		if (model == null){
			request.setAttribute("message", "δ��üҽ��ʸ���Ȩ���ʴ�ҳ�棡");
			return mapping.findForward("error");
		}
		
		//�ҽ��꼶�б�
		JjnjService jjnjService = new JjnjService();
		List<HashMap<String,String>> jjnjList = jjnjService.getJjnjList();
		request.setAttribute("jjnjList", jjnjList);
		
		//�ҽ�ѧ���б�
		JjxkService jjxkService = new JjxkService();
		List<HashMap<String,String>> jjxkList = jjxkService.getJjxkList();
		request.setAttribute("jjxkList", jjxkList);
		
		request.setAttribute("path", "xsjj_jjsc.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jjsc");
	}
	
	
	/**
	 * 
	 * @����: ��ѯ�ҽ�����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-19 ����02:36:45
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
	public ActionForward getJjxqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		JjxqForm model = (JjxqForm) form;
		//��ѯ
		List<HashMap<String,String>> resultList = getService().getJjxqList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	

	/**
	 * 
	 * @����: �ҵļҽ��б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-20 ����11:25:14
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
	@SystemAuth(url = "xsjj_wdjj.do")
	public ActionForward wdjjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		JjzgService service = new JjzgService();
		
		//����ѧ���ҽ���Ϣ
		JjzgForm jjzgModel = service.getModel(user.getUserName());
		
		if (jjzgModel == null){
			request.setAttribute("message", "δ��üҽ��ʸ���Ȩ���ʴ�ҳ�棡");
			return mapping.findForward("error");
		}
		
		//�ҽ��꼶�б�
		JjnjService jjnjService = new JjnjService();
		List<HashMap<String,String>> jjnjList = jjnjService.getJjnjList();
		request.setAttribute("jjnjList", jjnjList);
		
		//�ҽ�ѧ���б�
		JjxkService jjxkService = new JjxkService();
		List<HashMap<String,String>> jjxkList = jjxkService.getJjxkList();
		request.setAttribute("jjxkList", jjxkList);
	
		request.setAttribute("path", "xsjj_wdjj.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("wdjjList");
	}
	
	
	/**ajax�����ҵļҽ�**/
	@SystemAuth(url = url)
	public ActionForward getWdjjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JjxqForm model = (JjxqForm) form;
		User user = getUser(request);
		//��ѯ
		List<HashMap<String,String>> resultList = getService().getPageList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}

	/**
	 * ��ת��ѧ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward toXssqPage(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JjxqForm jjxqForm = (JjxqForm) form;
		String xqid = jjxqForm.getXqid();
		Map<String,String> xqwhMap = new XqwhService().getXqwhMap(xqid);
		request.setAttribute("xqwhMap",xqwhMap);

		return mapping.findForward("xsjjsq");
	}

	/**
	 * 
	 * @����: ����ѧ����������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-27 ����01:47:38
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
	public ActionForward xssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JjxqForm jjxqForm = (JjxqForm) form;
		User user = getUser(request);

		JjxqService jjxqService = getService();
		//����ǰ����֤�ļҽ��Ƿ��Ѿ������� ����xqid��jjcz
		boolean isJjczExist = jjxqService.isJjczExist(jjxqForm);
		if (isJjczExist) {
			response.getWriter().print(getJsonMessage("��Ҫ���еļҽ̲����Ѵ���"));
			return null;
		}
		
		boolean result = jjxqService.saveXssq(jjxqForm, user);
		String messageKey = result?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: �����ҽ�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-27 ����03:19:15
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
	public ActionForward jsjj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JjxqForm jjxqForm = (JjxqForm) form;
		String xqid = jjxqForm.getXqid();
		Map<String,String> xqwhMap = new XqwhService().getXqwhMap(xqid);
		request.setAttribute("xqwhMap",xqwhMap);

		return mapping.findForward("jsjj");
	}
	
	
	/**
	 * 
	 * @����: �����ҽ�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-8-27 ����04:08:54
	 * @�޸ļ�¼: �����ҽ���Ҫ���
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
	public ActionForward saveJsjj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JjxqForm jjxqForm = (JjxqForm) form;
		User user = getUser(request);

		JjxqService jjxqService = getService();
		//����ǰ�����pjjsqid��jjcz�ж��Ƿ��Ѿ�������Ӧ�ļҽ̲�������
		boolean isJjczExist = jjxqService.isJjczExist(jjxqForm);
		if (isJjczExist) {
			response.getWriter().print(getJsonMessage("��Ҫ���еļҽ̲����Ѵ���"));
			return null;
		}

		boolean result = jjxqService.saveJsjj(jjxqForm, user);
		String messageKey = result?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����: �鿴�ҵļҽ�����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-2 ����04:12:53
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
	public ActionForward cksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JjxqForm jjxqForm = (JjxqForm) form;
		HashMap<String,String> map = getService().getXssqInfo(jjxqForm.getSqid());
		
		String xqid = map.get("xqid");
		
		if(StringUtils.isNotBlank(xqid)){
			HashMap<String , String> xqModelMap = new JjxqService().getModelMap(xqid);
			request.setAttribute("xqModelMap", xgxt.utils.String.StringUtils.formatData(xqModelMap));
		}
		request.setAttribute("jjxx", map);
		
		return mapping.findForward("cksq");
	}

	/**
	 * �ҽ����ۣ��Լҳ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jjpj(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JjxqForm jjxqForm = (JjxqForm) form;

		HashMap<String , String> pjxxMap = new JjxqService().getPjxxMap(jjxqForm.getXqid());
		pjxxMap.put("xqid",jjxqForm.getXqid());
		request.setAttribute("pjxx", pjxxMap);

		return mapping.findForward("jjpj");
	}

	/**
	 * ����ҽ����ۣ��Լҳ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jjpjSave(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String pjid = request.getParameter("pjid");
		String xqid = request.getParameter("xqid");
		String pjzs = request.getParameter("pjzs");
		String py = request.getParameter("py");
		JjxqService jjxqService = getService();
		boolean result = jjxqService.jjpjSave(pjid,xqid, pjzs,py);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * �ҽ�ʱ��ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jjgswh(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JjxqForm jjxqForm = (JjxqForm) form;
		List<HashMap<String,String>> gsxxList = new JjxqService().getJjgsxxList(jjxqForm.getXqid());
		request.setAttribute("gsxxList", gsxxList);
		request.setAttribute("jjbh",jjxqForm.getXqid());

		return mapping.findForward("jjgswh");
	}

	/**
	 * ����ҽ̹�ʱ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jjgsSave(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String jjbh = request.getParameter("jjbh");
		String jjny = request.getParameter("jjny");
		String jjgs = request.getParameter("jjgs");
		JjxqService jjxqService = getService();
		//����ǰ��֤ͬ��ͬ����Ƿ��ظ�
		boolean isJjgsExist = jjxqService.isJjgsExist(jjbh,jjny);
		if (isJjgsExist) {
			response.getWriter().print(getJsonMessage("�ñ�żҽ̸��¹�ʱ��¼�Ѵ���"));
			return null;
		}

		boolean result = jjxqService.jjgsSave(jjbh,jjny, jjgs);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}


	/***************************************************�ҽ̲������**************************************************/

    /**
     * ��ת���ҽ�����б�ҳ��.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = "jjgl_jjczsh.do")
    public ActionForward jjczshList(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        request.setAttribute("path","jjgl_jjczsh.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("jjczshList");
    }

    /**
     * ��ȡ�ҽ�����б�JSON����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = "jjgl_jjczsh.do")
    public ActionForward getJjczshListData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JjxqForm jjxqForm = (JjxqForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		jjxqForm.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String, String>> resultList = getService().getJjczshList(jjxqForm, user);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     * ��ת���ҽ̲������ҳ��
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = "jjgl_jjczsh.do",rewritable=ReadWrite.WRITEABLE)
    public ActionForward jjczsh(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {

		JjxqForm jjxqForm = (JjxqForm) form;
		Map<String,String> jjxqMap = null;
		//������Ϣ
		if("1".equals(jjxqForm.getJjcz())){
			//�ɼҽ�������Ϣ
			jjxqMap = getService().getJjxqMap(jjxqForm.getSqid());
		}else{
			//�˼ҽ̹رռҽ�������Ϣ
			jjxqMap = getService().getTjjxqMap(jjxqForm.getSqid());
		}

		jjxqForm.setSplc(jjxqMap.get("splc"));
//		jjxqForm.setJjcz(jjxqMap.get("jjcz"));
		jjxqForm.setJjczmc(jjxqMap.get("jjczmc"));
		jjxqForm.setSqly(jjxqMap.get("sqly"));
		jjxqForm.setXqid(jjxqMap.get("xqid"));

		JjzgService jjzgService = new JjzgService();
		if (!StringUtil.isNull(jjxqForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(jjxqForm.getXh());
			request.setAttribute("jbxx", xsjbxx);

			//����ѧ���ҽ���Ϣ
			JjzgForm jjzgForm = jjzgService.getModel(jjxqForm.getXh());
			request.setAttribute("jjzgForm",jjzgForm);

			//�ҽ̾���
			List<HashMap<String, String>> jjjlList = jjzgService.getJJjlList(jjxqForm.getXh());
			request.setAttribute("jjjlList", jjjlList);

		}

        return mapping.findForward("jjczsh");
    }

    /**
     * ����ҽ̲������
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = "jjgl_jjczsh.do",rewritable=ReadWrite.WRITEABLE)
    public ActionForward jjczshSave(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

		JjxqForm jjxqForm = (JjxqForm) form;
		User user = getUser(request);
		// ���浥�����
		boolean result = getService().jjczshSave(jjxqForm, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
    }

    /**
     * �ҽ̲���������һ���ĳ���
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = "jjgl_jjczsh.do",rewritable=ReadWrite.WRITEABLE)
    public ActionForward cancelShLast(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

		JjxqForm jjxqForm = (JjxqForm) form;
		// ���һ������
		boolean isSuccess = getService().cancelShLast(jjxqForm);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
    }

	/**
	 * �ҽ̰�ȫЭ����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jjaqxys(ActionMapping mapping, ActionForm form, HttpServletRequest request,
									  HttpServletResponse response) throws Exception {
		JjxqForm jjxqForm = (JjxqForm) form;
		return mapping.findForward("jjaqxys");
	}

}
