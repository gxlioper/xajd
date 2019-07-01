/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-10-26 ����09:31:11 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.cssz;

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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����_�������_����ģ��
 * @�๦������: ��������������Action
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-10-26 ����09:31:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqCsszAction extends SuperAction<KqCsszForm,KqCsszService> {
	
	
	private static final String KGZT_CLOSE = "0";

	private static final String url = "rcsw_zjsy_kqcssz.do";
	
	KqCsszService service = new KqCsszService();
	
	/**
	 * 
	 * @����:������������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-26 ����09:43:33
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
	public ActionForward jcsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KqCsszForm myForm = (KqCsszForm) form;
		KqCsszForm model = service.getModel();
		if (model != null) {
			if (StringUtil.isNull(model.getSqkg())) {
				model.setSqkg(KGZT_CLOSE);
			}
			if (StringUtil.isNull(model.getSqkg())) {
				model.setShkg(KGZT_CLOSE);
			}
			BeanUtils.copyProperties(myForm, model);
		} else {
			myForm.setSqkg(KGZT_CLOSE);
			myForm.setShkg(KGZT_CLOSE);
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("rcsw");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);
		request.setAttribute("path", "rcsw_zjsy_kqcssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jcsz");
	}
	
	/**
	 * 
	 * @����:��ȡ������˿���״̬
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-27 ����09:26:26
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
	public ActionForward checkSqsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqCsszForm myForm = (KqCsszForm) form;
		String[] sqshkg = service.getSqShKg();
		String kg = "0";
		if(myForm.getType().equals("sq")){
			kg = sqshkg==null?"0":sqshkg[0];
		}else{
			kg = sqshkg==null?"0":sqshkg[1];
		}
		response.getWriter().print(kg);
		return null;
	} 

}
