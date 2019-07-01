/**
 * @����: ѧ����Ʒ(1)��
 * @���ڣ� 2018-7-13 ����10:24:39 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.jdsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���������Ź���ģ��
 * @�๦������: ���ɼ������
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2018-7-13 ����10:24:14 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JdszAction extends SuperAction {
	private JdszService service = new JdszService();
	private String messageKey;
	private static final String url = "xpjpy_jbsz_xmsz.do";
	
	/**
	 * @����: ������ò�ѯ
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-13 ����11:01:55
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
	@SystemLog(description = "��������������-��������-��Ŀ����-���ɼ������ҳ��")
	public ActionForward xmwhJdszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JdszForm model = (JdszForm)form;
		String xmdm = request.getParameter("xmdm");
		
		/*������Ŀ�����ȡ��Ŀ����*/
		String xmmc = service.getXmmcByXmdm(xmdm);
		request.setAttribute("xmmc", xmmc);
		
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getByXmdm(xmdm,"");
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*����xmdm��ѯ����Ŀ�Ƿ�����*/
		boolean flag = service.isExistsFlowData(xmdm);
		request.setAttribute("spzt", flag);
		
		/*����path*/
		String path = "xpjpy_jbsz_xmsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhJdszcx");
	}
	
	/**
	 * @����: ��ȡ��ѡ��Ŀ�����������Ŀ(��ͬ�����ڵ�)
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-13 ����05:31:05
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
	@SystemLog(description = "��������������-��������-��Ŀ����-��ȡ��ѡ��Ŀ�����������Ŀ(��ͬ�����ڵ�)")
	public ActionForward xmwhJdszSy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String xmdm = request.getParameter("xmdm");
		/*��ȡ��ѡ��Ŀ�����������Ŀ(��ͬ�����ڵ�)*/
		List<HashMap<String, String>> resultList = service.getOthers(xmdm);
		response.getWriter().print(JSONArray.fromObject(resultList));
		return null;
	}
	
	/**
	 * @����: ���ɼ�����ñ��� 
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-14 ����11:29:59
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
	@SystemLog(description="��������������-��������-��Ŀ����-�������-����XMDM��{xmdm}")
	public ActionForward xmwhJdszXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		JdszForm myForm = (JdszForm)form;
		String xmdm = request.getParameter("xmdm");
		String xmdms = request.getParameter("xmdms");

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = false;
			try {
				result = service.jdsz(xmdm,xmdms);
				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			} catch (Exception e) {
				e.printStackTrace();// //�쳣��ӡ����������////////////////
				messageKey = MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		JdszForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return null;
	}
}
