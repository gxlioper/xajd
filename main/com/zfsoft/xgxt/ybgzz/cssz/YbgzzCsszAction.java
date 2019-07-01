/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-24 ����09:50:00 
 */  
package com.zfsoft.xgxt.ybgzz.cssz;

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
 * @�๦������: �װ๤��վ-�������� 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-1-28 ����02:27:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YbgzzCsszAction extends SuperAction<YbgzzCssz, YbgzzCsszService> {

	private static final String KGZT_CLOSE = "0";
	
	
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
	private static final String url = "ybgzz_cssz.do";
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		YbgzzCssz csszForm = (YbgzzCssz) form;
		
		YbgzzCsszService service = getService();
		YbgzzCssz model = service.getModel();
		
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
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("ybgzz");
		request.setAttribute("shlcList", shlc);
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		request.setAttribute("onOff", onOff);
		
		request.setAttribute("path", "ybgzz_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
}
