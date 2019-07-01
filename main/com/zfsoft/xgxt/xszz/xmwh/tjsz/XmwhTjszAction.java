/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.tjsz;

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
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xszz.sqsh.XszzSqshService;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: ��Ŀά��-��������
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmwhTjszAction extends SuperAction {
	private XmwhTjszService service = new XmwhTjszService();
	private String messageKey;
	
	private static final String url = "xszz_xmwh.do?method=xmwhCx";

	/**
	 * 
	 * @����:������ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xmwhTjszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		
		
		//�Ƿ���ѧ��������Ŀ
		XszzSqshService xszzSqshService = new XszzSqshService();
		boolean flag = xszzSqshService.isExistShlcData(xmdm);
		request.setAttribute("spzt", flag);
		
		String path = "xszz_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		// �꼶ѧԺרҵ�༶
		return mapping.findForward("xmwhTjszCx");
	}

	/**
	 * 
	 * @����:��ѯ���м�¼������json��ʽ
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-26 ����02:30:16
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
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
	 * @����:��ѯѧ��ѧ�ڼ�¼������json��ʽ
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-26 ����02:30:16
	 * @�޸ļ�¼:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	public ActionForward xmwhTjszXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhTjszForm myForm = (XmwhTjszForm) form;
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
		String path = "xszz_xmwh.do?method=xmwhCx";
		request.setAttribute("path", path);
		String tjdm = request.getParameter("tjdm");
		request.setAttribute("tjdm", tjdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhTjszXn");
	}
	
	
	/**
	 * 
	 * @����:����ֵ����ѡ��ʽ
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-31 ����08:54:54
	 * @�޸ļ�¼: 
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
		return mapping.findForward("xmwhTjszTjzDiv");
	}

	/**
	 * 
	 * @����:�޸ķ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-������Ŀ����-��������-�޸�XMDM:{xmdm}")
	public ActionForward xmwhTjszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhTjszForm myForm = (XmwhTjszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String xmdm = request.getParameter("xmdm");
			String json = request.getParameter("json");
			boolean result = false;
			try {
				List<XmwhTjszForm> list = JsonUtil.jsonToList(json,
						XmwhTjszForm.class);
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

		XmwhTjszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		return null;
	}

	/**
	 * 
	 * @����:ɾ������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ������-��������-������Ŀ����-��������-ɾ��XMDM:{xmdm}")
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


}
