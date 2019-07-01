/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-10 ����04:49:07 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.cssz;

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

import com.zfsoft.xgxt.rcsw.qmlxdj.cssz.CsszService;
import com.zfsoft.xgxt.rcsw.qmlxdj.cssz.CsszForm;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.OptionUtil;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-10 ����04:49:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszAction extends SuperAction<CsszForm,CsszService> {
	 private static final String KGZT_CLOSE = "0";
		private CsszService service = new CsszService();
		/**
		 * 
		 * @����: ��������
		 * @���ߣ�yxy[���ţ�1206]
		 * @���ڣ�2017-1-4 ����10:02:10
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
			CsszForm myForm = (CsszForm) form;
			CsszForm model = service.getModel();
			if (model != null) {
				if (StringUtil.isNull(model.getSqkg())) {
					model.setSqkg(KGZT_CLOSE);
				}
//				if (StringUtil.isNull(model.getSqkg())) {
//					model.setShkg(KGZT_CLOSE);
//				}
				BeanUtils.copyProperties(myForm, model);
			} else {
				myForm.setSqkg(KGZT_CLOSE);
//				myForm.setShkg(KGZT_CLOSE);
			}
			XtwhShlcService shlcService = new XtwhShlcService();
			List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("rcsw");// ������������������б��ȡֵͨ�÷���
			request.setAttribute("shlcList", shlc);
			List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
			request.setAttribute("onoffList", onoffList);
			request.setAttribute("path", "rcsw_qmlxcssz.do");
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("jcsz");
		}
}
