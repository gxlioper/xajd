/**
 * @����:ѧ����Ʒ��1����
 * @���ڣ�2017-4-18 ����08:17:53 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.tjsz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������-��Ŀ����-��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-4-18 ����08:17:53 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjszAction extends SuperAction{
	private TjszService service = new TjszService();
	private String messageKey;
	private static final String url = "xpjpy_jbsz_xmsz.do";
	
	/**
	 * @����: ��ѯ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-19 ����09:30:52
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
	public ActionForward xmwhTjszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		request.setAttribute("xmdm", xmdm);
		XmwhService xmwhService = new XmwhService();
		String xmmc = xmwhService.getNameById(xmdm);
		/*�Ƿ���ѧ��������Ŀ*/
		/*����ѧ����ʹ������ĳ����Ŀ������Ŀ������Ҳ�ɱ��޸�*/
		//boolean flag = service.isExistsFlowData(xmdm);
		request.setAttribute("flagpath","");
		request.setAttribute("xmmc", xmmc);
		//request.setAttribute("spzt", flag);
		String path = "xpjpy_xmwh.do?method=getXmwhList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhTjszCx");
	}
	
	/**
	 * @����: ��ѯ���м�¼������json��ʽ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-19 ����10:24:02
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
	public ActionForward xmwhTjszSy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		String flagpath = request.getParameter("flagpath");
		TjszViewForm viewForm = new TjszViewForm();
		if(StringUtils.isNull(flagpath)){
			 viewForm = service.getAll(xmdm);
		}
		JSONObject json = JSONObject.fromBean(viewForm);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @����: ��ѯѧ��ѧ�ڼ�¼������json��ʽ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-19 ����02:01:59
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
	public ActionForward xmwhTjszXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjszForm myForm = (TjszForm)form;
		if (QUERY.equals(myForm.getType())) {
			String xmdm = request.getParameter("xmdm");
			TjszViewForm viewForm = service.getXn(xmdm);
			JSONObject json = JSONObject.fromBean(viewForm);
			response.getWriter().print(json);
			return null;
		}
		String xnVal = request.getParameter("xnVal");
		request.setAttribute("xnVal", xnVal);
		String zqlx = request.getParameter("zqlx");
		request.setAttribute("zqlx", zqlx);
		//XmwhService xmwhService = new XmwhService();
		
		//�õ���������,2Ϊѧ������
		CsszService csszService = new CsszService();
		request.setAttribute("pjzq", csszService.getCsz("pjzq"));
		
		request.setAttribute("knszq", MessageUtil.getText("xszz.knsrd.sqzq"));
		String tjdm = request.getParameter("tjdm");
		request.setAttribute("tjdm", tjdm);
		String path = "xpjpy_xmwh.do?method=getXmwhList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhTjszXn");
	}
	
	/**
	 * @����: ����ֵ����ѡ��ʽ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-19 ����02:02:55
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
		return mapping.findForward("xmwhTjszTjzDiv");
	}
	
	/**
	 * 
	 * @����:�޸ķ���
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:07:42
	 * @�޸ļ�¼:
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-��������-��Ŀ����-��������-����XMDM��{xmdm}")
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
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				}else{
					messageKey = MessageKey.SYS_SAVE_SUCCESS;
				}
			} catch (Exception e) {
				e.printStackTrace();// //�쳣��ӡ����������////////////////
				messageKey = MessageKey.SYS_SAVE_FAIL;
			}
			String message =  MessageUtil.getText(messageKey);
			Map<String,String> map = new HashMap<String,String>();
			map.put("message", message);
			map.put("success", String.valueOf(result));
			response.getWriter().print(JSONObject.fromObject(map));
			return null;
		}

		TjszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}

	/**
	 * @����: ɾ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-19 ����02:10:23
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
	@SystemLog(description="������������-��������-��Ŀ����-��������-ɾ��XMDM��{xmdm}")
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
