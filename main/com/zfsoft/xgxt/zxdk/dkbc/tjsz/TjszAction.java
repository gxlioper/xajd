/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-4 ����10:41:09 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.tjsz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xszz.xmwh.tjsz.XmwhTjszViewForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����
 * @�๦������: ��������  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-3-4 ����10:41:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjszAction extends SuperAction {
	private TjszService service = new TjszService();
	private String messageKey;
	String xmdm = "JCJYDKBC";
	String xmmc = "�������";
	
	/**
	 * 
	 * @����: ��������ҳ��鿴
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-7 ����03:20:05
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
	public ActionForward tjszCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String szzt = service.szztCx(xmdm);
		String path = "zxdk_jcjy_tjsz.do";
		request.setAttribute("path", path);
		request.setAttribute("szzt", szzt);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("xmmc", xmmc);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tjszCk");
	}
	
	/**
	 * 
	 * @����: ����������ҳ���ѯ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-7 ����03:20:05
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
	public ActionForward tjszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("xmmc", xmmc);	
		return mapping.findForward("tjszCx");
	}
	
	/**
	 * 
	 * @����: ���������б�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-7 ����03:20:05
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
	public ActionForward xmwhTjszSy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		XmwhTjszViewForm viewForm = service.getAll(xmdm);
		JSONObject json = JSONObject.fromBean(viewForm);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-7 ����03:20:05
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
	public ActionForward xmwhTjszSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		String tjdm = request.getParameter("tjdm");

		if (!StringUtil.isNull(xmdm) && !StringUtil.isNull(tjdm)) {
			service.delDeal(xmdm,tjdm);//ɾ�����еĹ�����
		} 
		return null;

	}
	
	/**
	 * 
	 * @����: ������������ѧ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-7 ����03:20:05
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
	public ActionForward xmwhTjszXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjszForm myForm = (TjszForm) form;
		if (QUERY.equals(myForm.getType())) {
			String xmdm = request.getParameter("xmdm");
			XmwhTjszViewForm viewForm = service.getXn(xmdm);
			JSONObject json = JSONObject.fromBean(viewForm);
			response.getWriter().print(json);
			return null;
		}
		String xnVal = request.getParameter("xnVal");
		request.setAttribute("xnVal", xnVal);
		String zqlx = request.getParameter("zqlx");
		request.setAttribute("zqlx", zqlx);
		request.setAttribute("knszq", MessageUtil.getText("xszz.knsrd.sqzq"));
		String path = "zxdk_jcjy_tjsz.do";
		request.setAttribute("path", path);
		String tjdm = request.getParameter("tjdm");
		request.setAttribute("tjdm", tjdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tjszXn");
		
	}
	
	/**
	 * 
	 * @����: ������������������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-7 ����03:20:05
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
	public ActionForward xmwhTjszTjzDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("tjszTjzDiv");
	}
	
	/**
	 * 
	 * @����: ��������
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-7 ����03:20:05
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
	public ActionForward xmwhTjszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjszForm myForm = (TjszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String xmdm = request.getParameter("xmdm");
			String json = request.getParameter("json");
			boolean result = false;
			try {
				List<TjszForm> list = JsonUtil.jsonToList(json,
						TjszForm.class);
				if (list != null && list.size() > 0) {
					result = service.saveOrUpdate(xmdm, list);
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
				}else{
					messageKey = MessageKey.SYS_SAVE_SUCCESS;
				}
			} catch (Exception e) {
				e.printStackTrace();// //�쳣��ӡ����������////////////////
				messageKey = MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonResult(messageKey, result));
			return null;
		}
		
		TjszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		return null;
		
	}
	
}
