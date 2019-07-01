/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��5��4�� ����10:24:12 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjcsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.OptionUtil;

import common.newp.StringUtil;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ־Ը�������ģ��
 * @�๦������: ־Ը�����������Action
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��5��4�� ����10:24:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwJcszAction extends SuperAction<ZyfwJcszForm,ZyfwJcszService>{
	
	private static final String KGZT_CLOSE = "0";
	private static final String url = "xsxx_zyfwgl_jcsz.do?method=zyfwJcsz";
	
	/**
	 * @����:ת��־Ը�������-־Ը�����������ҳ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��4�� ����11:03:50
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
	public ActionForward zyfwJcsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZyfwJcszForm zyfwJcszForm = (ZyfwJcszForm) form;
		ZyfwJcszService zyfwJcszService = new ZyfwJcszService();
		
		ZyfwJcszForm model = zyfwJcszService.getModel();
		if (model != null) {
			if (StringUtil.isNull(model.getSqkg())) {
				model.setSqkg(KGZT_CLOSE);
			}
			BeanUtils.copyProperties(zyfwJcszForm, model);
		} else {
			zyfwJcszForm.setSqkg(KGZT_CLOSE);
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xsxx");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);
		request.setAttribute("path",url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zyfwJcsz");
	}
	
}
