/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-24 ����09:50:00 
 */  
package com.zfsoft.xgxt.zxdk.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.OptionUtil;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ѧ����-��������
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-9-24 ����09:50:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxdkCsszAction extends SuperAction<ZxdkCssz, ZxdkCsszService> {

	private static final String KGZT_CLOSE = "0";
	
	private static final String url = "zxdk_gjdk_cssz.do";
	
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
	@SystemAuth(url = "zxdk_gjdk_cssz.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʹ�����ѧ����-��������")
	public ActionForward gjdkCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ZxdkCssz csszForm = (ZxdkCssz) form;
		
		ZxdkCsszService service = getService();
		ZxdkCssz model = service.getModel();
		
		if (model != null){
			if (StringUtil.isNull(model.getXydkg())){
				model.setXydkg(KGZT_CLOSE);
			}
			
			if (StringUtil.isNull(model.getXdkg())){
				model.setXdkg(KGZT_CLOSE);
			}
			BeanUtils.copyProperties(csszForm, model);
		} else {
			csszForm.setXydkg(KGZT_CLOSE);
			csszForm.setXdkg(KGZT_CLOSE);
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		// ������������������б��ȡֵͨ�÷���
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("zxdk");
		request.setAttribute("shlcList", shlc);
		
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		
		request.setAttribute("onOff", onOff);
		request.setAttribute("path", "zxdk_gjdk_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gjdkCssz");
	}
	
	
	/**
	 * 
	 * @����: ��ѧ����-��Դ�ش���-��������
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
	@SystemAuth(url = "zxdk_syddk_cssz.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "������Դ�ش���-��������")
	public ActionForward syddkCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxdkCssz csszForm = (ZxdkCssz) form;
		
		ZxdkCsszService service = getService();
		ZxdkCssz model = service.getModel();
		
		if (model != null){
			BeanUtils.copyProperties(csszForm, model);
		}
		
		if (model == null || StringUtil.isNull(model.getSydkg())) {
			csszForm.setSydkg(KGZT_CLOSE);
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		// ������������������б��ȡֵͨ�÷���
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("zxdk");
		request.setAttribute("shlcList", shlc);
		request.setAttribute("xnlist", Base.getXnndList2());
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		
		request.setAttribute("onOff", onOff);
		request.setAttribute("path", "zxdk_syddk_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("syddkCssz");
	}
	
	
	/**��ǰ�����������*/
	@SystemAuth(url = "zxdk_tqhk_cssz.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "������ǰ����-��������")
	public ActionForward tqhkCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZxdkCssz csszForm = (ZxdkCssz) form;
		
		ZxdkCsszService service = getService();
		ZxdkCssz model = service.getModel();
		
		if (model != null){
			BeanUtils.copyProperties(csszForm, model);
		}
		
		if (model == null || StringUtil.isNull(model.getSydkg())) {
			csszForm.setTqhkkg(KGZT_CLOSE);
		}
		
		
		XtwhShlcService shlcService = new XtwhShlcService();
		// ������������������б��ȡֵͨ�÷���
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("zxdk");
		request.setAttribute("shlcList", shlc);
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		
		request.setAttribute("onOff", onOff);
		request.setAttribute("path", "zxdk_tqhk_cssz.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tqhkCssz");
	}
}
