package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.xmgl;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.fdgl.FdglService;

/** 
 * @��������������������Ŀ����-��Ŀ����
 * @author��Lu.Yao ��1271��
 * @date��2017-10-16 ����03:55:26 
 */
public class XmglAction extends SuperAction {
	
	private static final String url = "zzyrxmgl_xmgl.do";
	private XmglService service = new XmglService();

	/** 
	 * @description����Ŀ������ҳ
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����10:41:29 
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
	public ActionForward xmglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "zzyrxmgl_xmgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		if("1".equals(model.getFblx())){
			return mapping.findForward("xmglManage2");
		}else{
			return mapping.findForward("xmglManage");
		}
	}
	
	/** 
	 * @description���鿴
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����10:41:50 
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
	public ActionForward viewXmgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		if (!StringUtil.isNull(user.getUserName())&&"stu".equalsIgnoreCase(user.getUserStatus())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
			request.setAttribute("jbxx", xsjbxx);
		}
		FdglService fservice = new FdglService();
		List<HashMap<String, String>> xyList = fservice.getKfxydmByid(model);
		request.setAttribute("xyList", xyList);
		HashMap<String,String> rs = service.getModelMap(model);
		request.setAttribute("rs", StringUtils.formatData(rs));
		if("1".equals(rs.get("fblx"))){
			return mapping.findForward("viewXmgl2");
		}else{
			return mapping.findForward("viewXmgl");			
		}
	}
	
	/** 
	 * @description����ȡ����ѧԺ����list
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����10:42:26 
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
	public ActionForward kfxydmCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		FdglService service = new FdglService();
		List<HashMap<String, String>> resultList = service.getKfxydmByid(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/** 
	 * @description������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����10:42:37 
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
	public ActionForward sqXmgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		boolean flag = service.checkBmrs(model);//�жϱ���ͨ�������Ƿ񳬹��޶�����
		if(flag){
			boolean result = service.insertFdxx(model, user);
			String messageKey = result ? "����ɹ���" : "����ʧ�ܣ�";
			response.getWriter().print(getJsonMessage(messageKey));
		}else{
			response.getWriter().print(getJsonMessage("���������ѱ����꣬�´�������ޣ�"));
		}
		return null;
	}
	
	/** 
	 * @description��ȡ������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����10:42:43 
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
	public ActionForward qxsqXmgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		boolean flag = service.checkBmshzt(model,user);//�жϱ����Ƿ���ͨ����ͨ��������ȡ��
		if(flag){
			boolean result = service.deleteFdxx(model, user);
			String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_AUD_CANCEL_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));			
		}else{
			response.getWriter().print(getJsonMessage("���������ѱ���ˣ��޷�������"));
		}
		return null;
	}
	
	/** 
	 * @description��ͬ�⸨��
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����10:43:06 
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
	public ActionForward tyfdXmgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		boolean flag = service.checkFdrs(model);//����ʱ����֤�Ƿ������Ѿ�����һ��������ѧ��
		if(flag){
			boolean result = service.insertTyfdxx(model, user);
			String messageKey = result ? "ͬ�⸨���ɹ���" : "ͬ�⸨��ʧ�ܣ�";
			response.getWriter().print(getJsonMessage(messageKey));			
		}else{
			response.getWriter().print(getJsonMessage("������ͬ�⸨����ѧ�����뻻��ѧ�����ԣ�"));
		}
		return null;
	}
	
	/** 
	 * @description��ȡ������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����10:43:16 
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
	public ActionForward qxfdXmgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		boolean flag = service.checkFdjlBfcx(model);//���ݸ�����¼�ж��ܷ�������
		if(flag){
			boolean result = service.deleteTyfdxx(model, user);
			String messageKey = result ? "ȡ���ɹ���" : "ȡ��ʧ�ܣ�";
			response.getWriter().print(getJsonMessage(messageKey));
		}else{
			response.getWriter().print(getJsonMessage("���и�����¼���޷�������"));
		}
		return null;
	}
}
