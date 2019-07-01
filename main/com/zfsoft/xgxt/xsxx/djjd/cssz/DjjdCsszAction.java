/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-24 ����09:50:00 
 */  
package com.zfsoft.xgxt.xsxx.djjd.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.OptionUtil;
import common.newp.StringUtil;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �����ȼ����� --��������
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-11-11 ����04:16:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DjjdCsszAction extends SuperAction<DjjdCssz, DjjdCsszService> {

	private static final String KGZT_CLOSE = "0";
	
	private static final String url = "ntgm_jjdj_cssz.do";
	
	/**
	 * 
	 * @����: ��ѧ����-������ѧ����-��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-9-25 ����11:50:30
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
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		DjjdCssz csszForm = (DjjdCssz) form;
		
		DjjdCsszService service = getService();
		DjjdCssz model = service.getModel();
		
		if (model != null){
			
			if (StringUtil.isNull(csszForm.getSqkg())){
				csszForm.setSqkg(KGZT_CLOSE);
			}
			
			BeanUtils.copyProperties(csszForm, model);
		} else {
			csszForm.setSqkg(KGZT_CLOSE);
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		// ������������������б��ȡֵͨ�÷���
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xsxx");
		request.setAttribute("shlcList", shlc);
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		request.setAttribute("onOff", onOff);
		
		request.setAttribute("path", "ntgm_jjdj_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
}
