/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-23 ����08:42:15 
 */
package com.zfsoft.xgxt.xszz.zzdy.jcsz;

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

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-11-23 ����08:42:15
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZzdyJcszAction extends SuperAction<ZzdyJcszForm, ZzdyJcszService> {
	private static final String url = "xszz_zzdy_jcsz.do";
	private ZzdyJcszService service = new ZzdyJcszService();

	@SystemAuth(url = url)
	public ActionForward getSzxmList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyJcszForm model = (ZzdyJcszForm) form;
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
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getSzxmList");
	}

	/**
	 * 
	 * @����:����������Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-23 ����01:55:20
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
	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description = "����ѧ������-��������-��������-����")
	public ActionForward addSzxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyJcszForm model = (ZzdyJcszForm) form;
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		request.setAttribute("ZzdyJcszForm", model);
		request.setAttribute("xmList", service.getXmList(model.getXn(), model.getXq()));
		// ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		// ѧ��list
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("addSzxm");
	}

	/**
	 * 
	 * @����:�޸�������Ŀ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-23 ����01:55:20
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
	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description = "����ѧ������-��������-��������-�޸�-XMMC:{xmmc}")
	public ActionForward updateSzxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyJcszForm myForm = (ZzdyJcszForm) form;
		ZzdyJcszForm ZzdyJcszForm = service.getModel(myForm);
		if (null != ZzdyJcszForm) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(ZzdyJcszForm));
			request.setAttribute("ZzdyJcszForm", ZzdyJcszForm);
		}
		request.setAttribute("xmList", service.getXmList(ZzdyJcszForm.getXn(), ZzdyJcszForm.getXq()));
		// ѧ��list
		request.setAttribute("xnList", Base.getXnndList());
		// ѧ��list
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("updateSzxm");
	}

	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description = "����ѧ������-��������-��������-���ӻ��޸ı���-XMDM:{xmdm},SZID:{szid},XN:{xn},XQ:{xq},FFKSYF:{ffksyf},FFJSYF:{ffjsyf},FFYS:{ffys}")
	public ActionForward saveSzxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyJcszForm myForm = (ZzdyJcszForm) form;
		if (service.isHave(myForm)) {
			String message = MessageUtil.getText(MessageKey.XSZZ_ZZDY_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editSzxm(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	public ActionForward getXmList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyJcszForm model = (ZzdyJcszForm) form;
		List<HashMap<String, String>> sbflList = service.getXmList(model.getXn(), model.getXq());
		JSONArray dataList = JSONArray.fromObject(sbflList);
		response.getWriter().print(dataList);
		return null;
	}

	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description = "����ѧ������-��������-��������-ɾ��-VALUES:{values}")
	public ActionForward delSzxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		String message=null;
		if (!StringUtil.isNull(values)) {
			 //�жϿ��˱��Ƿ�ʹ��
			 boolean flag =service.isUsed(values.split(","));
			 if (flag) {
			 response.getWriter().print(getJsonMessage(String.valueOf(flag)));
			 return null;
			 }
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			message = num > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xmsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZzdyJcszForm myForm = (ZzdyJcszForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		ZzdyJcszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsz");
	}

}
