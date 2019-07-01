package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.fdgl;

import java.util.ArrayList;
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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.cssz.JcsszForm;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.cssz.JcszService;

/** 
 * @��������������������Ŀ����-��������action
 * @author��Lu.Yao ��1271��
 * @date��2017-10-16 ����03:55:26 
 */
public class FdglAction extends SuperAction {
	
	private static final String url = "zzyrxmgl_fdgl.do";
	
	private FdglService service = new FdglService();

	
	/** 
	 * @description����ҳ
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����11:06:39 
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
	public ActionForward fdglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
			if("1".equals(model.getFblx())){
				resultList = service.getPageList2(model, user);
			}else{
				resultList = service.getPageList(model, user);
			}
			//��ѯ
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "zzyrxmgl_fdgl.do";
		request.setAttribute("path", path);
		JcszService jcszService = new JcszService();
		JcsszForm jcszModel = jcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
//		boolean sfsq = service.sfksq();
//		boolean sfsq = true;
//		request.setAttribute("sfsq", sfsq == true ? "1": "0");
		FormModleCommon.commonRequestSet(request);
		if("1".equals(model.getFblx())){
			return mapping.findForward("fdglManage2");
		}else{
			return mapping.findForward("fdglManage");
		}
	}
	
	/** 
	 * @description������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����11:06:52 
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
	@SystemLog(description="���ʾ�ҵ����-��ҵ����-��ҵȥ��-����")
	public ActionForward addFdgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		List<HashMap<String, String>> xyList = Base.getXyList();
		request.setAttribute("xyList", xyList);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			model.setFdfbid(StringUtils.getGuid());
			boolean result = service.insertFdgl(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		if (!StringUtil.isNull(user.getUserName())&&"stu".equalsIgnoreCase(user.getUserStatus())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
			request.setAttribute("jbxx", xsjbxx);
		}
		String path = "zzyrxmglxmgl.do?method=addFdgl";
		request.setAttribute("path", path);
		return mapping.findForward("addFdgl");
	}
	
	
	/** 
	 * @description���޸�
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����11:06:58 
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
	@SystemLog(description="���ʾ�ҵ����-��ҵ����-��ҵȥ��-�޸�XH:{xh}")
	public ActionForward updateFdgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		List<HashMap<String, String>> xyList = Base.getXyList();
		request.setAttribute("xyList", xyList);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.updateFdgl(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		if (!StringUtil.isNull(user.getUserName())&&"stu".equalsIgnoreCase(user.getUserStatus())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(user.getUserName());
			request.setAttribute("jbxx", xsjbxx);
			HashMap<String, String> updateForm = service.getModelMap(model);
			BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		}
		request.setAttribute("rs",StringUtils.formatData(model));
		return mapping.findForward("updateFdgl");
	}
	
	/** 
	 * @description����ȡ����ѧԺlist
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����11:07:05 
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
		List<HashMap<String, String>> resultList = service.getKfxydmByid(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/** 
	 * @description��ɾ��
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����11:07:20 
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
	@SystemLog(description="���ʾ�ҵ����-��ҵ����-��ҵȥ��-ɾ��VALUES:{values}")
	public ActionForward delFdgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			boolean canDel = service.checkCandel(values);
			if(canDel){
				int num = service.runDelete(values);
				boolean result = num > 0;
				String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
			}else{
				response.getWriter().print(getJsonMessage("�����˲μӸ������޷�ɾ����"));
			}
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/** 
	 * @description���鿴
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����11:07:27 
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
	public ActionForward viewFdgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		List<HashMap<String,String>> fdjlList = service.getFdjlList(model);
		HashMap<String, String> updateForm = service.getModelMap2(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs",StringUtils.formatData(model));
		request.setAttribute("fdjlList", fdjlList);
		return mapping.findForward("viewFdgl");
	}

	/** 
	 * @description���鿴��������
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����11:07:35 
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
	public ActionForward viewBmrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		List<HashMap<String,String>> rs = service.getBmrsList(model);
		request.setAttribute("rs", rs);
		return mapping.findForward("viewBmrs");
	}
	
	/** 
	 * @description�����
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����11:07:46 
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
	public ActionForward shFdgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		boolean canSave = true;
		boolean canCancel = true;
		if("1".equals(model.getShzt())){//ͬ��ʱ�ж�
			canSave = service.checkYtyfdrs(model);//�ж�ͬ�⸨�������Ƿ񳬳��޶�����			
		}
		if("0".equals(model.getShzt())){//��ͬ��ʱ�ж�
			canCancel = service.checkCancancel(model);
		}
		if(canSave&&canCancel){
			boolean result = service.updateShzt(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));	
		}else if(!canSave){
			response.getWriter().print(getJsonMessage("�ѳ����޶������������޷�����ͬ�⸨����"));
		}else if(!(canCancel)){
			response.getWriter().print(getJsonMessage("���и�����¼���޷�ȡ��ͬ�⸨����"));
		}
		return null;
	}
	
	/** 
	 * @description����д��¼
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����11:07:54 
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
	@SystemLog(description="���ʾ�ҵ����-��ҵ����-��ҵȥ��-�޸�XH:{xh}")
	public ActionForward txFdgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.addFdjl(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		List<HashMap<String,String>> fdjlList = service.getFdjlList(model);
		HashMap<String, String> updateForm = service.getModelMap2(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs",StringUtils.formatData(model));
		request.setAttribute("fdjlList", fdjlList);
		request.setAttribute("size", fdjlList.size());
		return mapping.findForward("txFdgl");
	}
	
	/** 
	 * @description��ɾ��������¼
	 * @dept:ѧ��2��
	 * @author��Lu.Yao ��1271��
	 * @date��2017-10-20 ����11:08:08 
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
	@SystemLog(description="���ʾ�ҵ����-��ҵ����-��ҵȥ��-ɾ��VALUES:{values}")
	public ActionForward delFdjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		if (!StringUtil.isNull(model.getId())) {
			int num = service.deleteFdjl(model);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
}
