/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��1��25�� ����9:04:47 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.jcsz;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.OptionUtil;

import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž�����֯��ϵת������ģ��
 * @�๦������: ��������Action
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��1��25�� ����9:04:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcszAction extends SuperAction<JcszForm,JcszService>{
	private static final String KGZT_CLOSE = "0";
	private JcszService service = new JcszService();
	
	/**
	 * @����:��֯��ϵת����������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��25�� ����9:27:27
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
	public ActionForward jcsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcszForm jcszForm = (JcszForm) form;
		JcszForm model = service.getModel();
		if (model != null) {
			if (StringUtil.isNull(model.getSqkg())) {
				model.setSqkg(KGZT_CLOSE);
			}
			BeanUtils.copyProperties(jcszForm, model);
		} else {
			jcszForm.setSqkg(KGZT_CLOSE);
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("dtjs");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);
		request.setAttribute("path", "dtjs_jcsz.do?method=jcsz");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jcsz");
	}
}
