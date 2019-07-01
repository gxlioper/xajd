/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-20 ����01:31:50 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.cssz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import com.zfsoft.xgxt.xpjpy.bfjs.jsxm.BfjsJsxmService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.OptionUtil;



/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��羺������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-3-31 ����04:05:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BfjsCsszAction extends SuperAction {

	private static final String url = "xpjpy_bfjs_cssz.do";
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-3-31 ����04:06:20
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
	public ActionForward viewBfjsCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BfjsCsszService service = new BfjsCsszService();
		BfjsCsszModel BfjsCsszForm = (BfjsCsszModel) form;
		BfjsCsszModel BfjsCsszModel = service.getModel();
		
		if (BfjsCsszModel != null){
			//��������ֵ 
			BeanUtils.copyProperties(BfjsCsszForm, BfjsCsszModel);
		}
		
		request.setAttribute("sqkgList", new OptionUtil().getOptions(OptionUtil.ONOFF));
		request.setAttribute("pdzqList", service.getPdzqList());
		request.setAttribute("path", url);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewBfjsCssz");
	}
	
	

	
	
	/**
	 * 
	 * @����: �����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-3-31 ����04:06:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="���ʰ�羺��-��������-��������-����ZDKEY:{zdKey} ZDVALUE:{zdValue}")
	public ActionForward saveBfjsCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zdKey = request.getParameter("zdKey");
		String zdValue = request.getParameter("zdValue");
		BfjsCsszService service = new BfjsCsszService();
		User user =getUser(request);
		service.updateBfjsCssz(zdKey, zdValue);
		if ("pdzq".equals(zdKey) && !StringUtil.isNull(zdValue)){
			//��ʼ��������Ŀ�ṹ
			BfjsJsxmService jsxmService = new BfjsJsxmService();
			jsxmService.initBfjsJsxmList(zdValue);
			//��ʼ�������༶
			service.initJsbj(user);
		}
		return null;
	}
}
