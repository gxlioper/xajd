/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014��6��9�� ����1:54:53 
 */  
package com.zfsoft.xgxt.comm.shlc.action;

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
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.shlc.model.CsszModel;
import com.zfsoft.xgxt.comm.shlc.service.CsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ͨ���������-��������
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014��6��9�� ����1:54:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszAction extends SuperAction<CsszModel, CsszService> {

	
	/**
	 * 
	 * @����: �������-��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��9�� ����1:55:59
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
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CsszModel csszForm = (CsszModel) form;
		CsszService service = new CsszService();
		
		CsszModel model = service.getModel(csszForm);
		
		if (model != null){
			BeanUtils.copyProperties(csszForm, model);
		}
		
		//�����б�
		OptionUtil optionUtil = new OptionUtil();
		List<HashMap<String , String>> kgList = optionUtil.getOptions(OptionUtil.ONOFF);
		request.setAttribute("kgList", kgList);
		
		//�������
		XtwhShlcService shlcService = new XtwhShlcService();
		request.setAttribute("shlcList", shlcService.getShlcByDjlx(csszForm.getSsmk()));
		
		request.setAttribute("path", "sqsh_cssz.do?id="+csszForm.getId()+"&ssmk="+csszForm.getSsmk());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
	
}
