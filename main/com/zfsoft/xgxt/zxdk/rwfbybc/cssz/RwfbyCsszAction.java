/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-7 ����04:02:02 
 */
package com.zfsoft.xgxt.zxdk.rwfbybc.cssz;

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
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ChenQ[����:856]
 * @ʱ�䣺 2015-9-7 ����04:02:02
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class RwfbyCsszAction extends SuperAction<RwfbyCssz, RwfbyCsszService> {
	private static final String KGZT_CLOSE = "0";
	
	private static final String url = "zxdk_rwfby_cssz.do";

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwfbyCssz csszForm = (RwfbyCssz) form;
		RwfbyCsszService service = new RwfbyCsszService();
		RwfbyCssz model = service.getModel();

		if (model != null) {

			if (StringUtil.isNull(csszForm.getSqkg())) {
				csszForm.setSqkg(KGZT_CLOSE);
			}

			BeanUtils.copyProperties(csszForm, model);
		} else {
			csszForm.setSqkg(KGZT_CLOSE);
		}

		XtwhShlcService shlcService = new XtwhShlcService();
		// ������������������б��ȡֵͨ�÷���
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("zxdk");
		request.setAttribute("shlcList", shlc);

		List<HashMap<String, String>> onOff = new OptionUtil()
				.getOptions(OptionUtil.ONOFF);
		request.setAttribute("onOff", onOff);

		request.setAttribute("path", "zxdk_rwfby_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}

}
