/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-10 ����11:36:27 
 */
package com.zfsoft.xgxt.khgl.khdxgl.pfzgl;

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
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.khgl.khdxgl.khdxgl.KhdxglService;

/**
 * @ϵͳ����: ��������ϵͳ
 * @ģ������: ���������ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-10 ����11:36:27
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class PfzglAction extends SuperAction<PfzglForm, PfzglService> {
	private PfzglService service = new PfzglService();
	private KhdxglService khdxService = new KhdxglService();

	private static final String url = "khgl_khdxgl_pfzgl.do";
	
	/**
	 * 
	 * @����:��ѯ�������б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����01:54:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward getPfzglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfzglForm model = (PfzglForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "khgl_khdxgl_pfzgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getPfzglList");
	}

	/**
	 * 
	 * @����:����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����05:27:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addPfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("khlxList", new OptionUtil().getOptions("khlx"));
		request.setAttribute("khdxList", khdxService.getKhdxList());
		return mapping.findForward("addPfz");
	}

	/**
	 * 
	 * @����:�޸�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����01:55:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updatePfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfzglForm myForm = (PfzglForm) form;
		String yfpcy = request.getParameter("yfpcy");
		
		PfzglForm PfzglForm = service.getModel(myForm);
		if (null != PfzglForm) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(PfzglForm));
			request.setAttribute("PfzglForm", PfzglForm);
		}
		//�ѷ������ֳ�Ա���ѱ�ռ����ֻ�����޸�����������
		if("1".equals(yfpcy)||service.isUsed(myForm.getPfzid())){
			request.setAttribute("sfkxg", "false");
		}
		request.setAttribute("khlxList", new OptionUtil().getOptions("khlx"));
		request.setAttribute("khdxList", khdxService.getKhdxList());
		return mapping.findForward("updatePfz");
	}

	/**
	 * 
	 * @����:�鿴������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����01:55:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward viewPfzgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfzglForm myForm = (PfzglForm) form;
		HashMap<String, String> sbjgMap = service.getPfzgl(myForm);
		request.setAttribute("rs", StringUtils.formatData(sbjgMap));
		request.setAttribute("path", "xmsbPfzgl.do?method=viewPfzgl");
		return mapping.findForward("viewPfzgl");
	}

	/**
	 * 
	 * @����:�����鱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����05:28:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʿ��˹���-���˶������-�Զ������������-���ӻ��޸ı���PFZMC:{pfzmc},PFLX:{pflx},KHDXID:{khdxid},PFZID:{pfzid}")
	public ActionForward savePfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfzglForm myForm = (PfzglForm) form;
		
		if (service.isHave(myForm)) {
			String message = MessageUtil
					.getText(MessageKey.KHGL_KHDXGL_PFDX_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editPfz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:ɾ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����04:08:06
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
	@SystemLog(description = "���ʿ��˹���-���˶������-�Զ������������-ɾ��VALUES:{values}")
	public ActionForward delPfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//�ж��������Ƿ�ʹ��
			if (service.isUsed(values)) {
				String message = MessageUtil.getText(MessageKey.KHGL_KHDXGL_PFZ_USED);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			String message = num>0 ? MessageUtil.getText(
					MessageKey.SYS_DEL_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @����:���˶���ѡ���б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-10 ����04:43:08
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
	public ActionForward showKhdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzglForm myForm = (PfzglForm) form;
		String forWardUrl=null;
		String path = null;
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getKhdxList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "khgl_khdxgl_pfzgl.do");
		FormModleCommon.commonRequestSet(request);
		if("1".equals(myForm.getKhlx())){ //�����˶���Ϊѧ��
			path="khglKhdxgl.do?method=viewKhdxOfStuList";//����·����ֻ�����ڸ߼���ѯ
			forWardUrl="showStu";
		}else{
			path="khglKhdxgl.do?method=viewKhdxOfTeaList";//����·����ֻ�����ڸ߼���ѯ
			forWardUrl="showTea";
		}
		
		request.setAttribute("path", path);
		request.setAttribute("pfzid", myForm.getPfzid());
		request.setAttribute("khdxid", myForm.getKhdxid());
		request.setAttribute("pflx", myForm.getPflx());
		request.setAttribute("khlx", myForm.getKhlx());
		request.setAttribute("xxdm", Base.xxdm);
	
		return mapping.findForward(forWardUrl);
	}
	
	@SystemAuth(url = url)
	public ActionForward showPfcy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzglForm myForm = (PfzglForm) form;
		String forWardUrl=null;
		String path = null;
		String bjfp=request.getParameter("bjfp");//�Ƿ񰴰༶�������ѧ��
		if (QUERY.equals(myForm.getType())) {
			List<HashMap<String, String>> resultList=null;
			if(StringUtils.isNotNull(bjfp)&&"y".equals(bjfp)){
				resultList=service.getPfcybjList(myForm);
			}else{
				// ���ɸ߼���ѯ����
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				myForm.setSearchModel(searchModel);
				User user = getUser(request);
				// ��ѯ
				resultList = service.getPfcyList(myForm, user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		if("1".equals(myForm.getPflx())){
			path = "khglPfzgl.do?method=showPfcyStu";//���˶���Ϊѧ��
			forWardUrl="showPfcyStu";
			if(StringUtils.isNotNull(bjfp)&&"y".equals(bjfp)){
				forWardUrl="showPfcyStuClass";
				request.setAttribute("xm", request.getParameter("xm"));
				request.setAttribute("bmmc", request.getParameter("bmmc"));
			}
		}else{
			path = "khglPfzgl.do?method=showPfcyTea";//���˶���Ϊ��ʦ
			forWardUrl="showPfcyTea";
		}
		request.setAttribute("pfzid", myForm.getPfzid());
		request.setAttribute("khdxid", myForm.getKhdxid());
		request.setAttribute("pflx", myForm.getPflx());
		request.setAttribute("xh", myForm.getXh());
		request.setAttribute("zgh", myForm.getZgh());
		request.setAttribute("khlx", myForm.getKhlx());
		request.setAttribute("pflx", myForm.getPflx());
		request.setAttribute("sfqx", request.getParameter("sfqx"));
		request.setAttribute("khdxrs", request.getParameter("khdxrs"));
		request.setAttribute("path", path);
		return mapping.findForward(forWardUrl);
	}
	
	@SystemAuth(url = url)
	public ActionForward viewPfcy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzglForm myForm = (PfzglForm) form;
		String path = null;
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getViewPfcyList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		if("1".equals(myForm.getPflx())){
			//���ڸ߼���ѯ
			path = "khglPfzgl.do?method=showPfcyStu";//���˶���Ϊѧ��
		}else{
			path = "khglPfzgl.do?method=showPfcyTea";//���˶���Ϊ��ʦ
		}
		request.setAttribute("pfzid", myForm.getPfzid());
		request.setAttribute("pflx", myForm.getPflx());
		request.setAttribute("xh", myForm.getXh());
		request.setAttribute("zgh", myForm.getZgh());
		request.setAttribute("khlx", myForm.getKhlx());
		request.setAttribute("pflx", myForm.getPflx());
		request.setAttribute("path", path);
		return mapping.findForward("viewPfcy");
	}
	/**
	 * 
	 * @����:���ֳ�Ա����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����08:55:42
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
	public ActionForward savePfcy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzglForm model = (PfzglForm) form;
		User user = getUser(request);
		// ���ɸ߼���ѯ����
		String bjfp=request.getParameter("bjfp");//�Ƿ񰴰༶�������ѧ��
		boolean result=true;
		String value = request.getParameter("values");
		String khdxr = request.getParameter("khdxrs");
		if(StringUtils.isNotNull(bjfp)&&"y".equals(bjfp)){
			result=service.savePfcybj(model,value,khdxr,user);
		}else{
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			result=service.savePfcy(model,value,khdxr,user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * 
	 * @����:��������䱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-11 ����08:55:42
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
	public ActionForward savePfzQxPf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzglForm model = (PfzglForm) form;
		boolean result=true;
		String value = request.getParameter("values");
		result=service.savePfzQxPf(model,value);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * @���������ֳ�Ա����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��1�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward pfcydr(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzglForm myForm = (PfzglForm) form;
		request.setAttribute("pfzid", myForm.getPfzid());
		request.setAttribute("zgh", myForm.getZgh());
		request.setAttribute("xm", request.getParameter("xm"));
		return mapping.findForward("pfcydr");
	}
	
	/**
	 * @���������ֳ�Ա���뱣��
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��11��1�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward pfcydrSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PfzglForm model = (PfzglForm) form;
		User user = getUser(request);
		model.setPflx("1");
		boolean result = service.pfcydrSave(model, user);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey, result));
		return null;
	}
	
	
}
