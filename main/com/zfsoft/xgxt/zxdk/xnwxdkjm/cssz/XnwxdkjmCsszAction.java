/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-24 ����02:51:33 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdkjm.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.zfsoft.xgxt.zxdk.xnwxdk.cssz.XnwxdkCsszModel;
import com.zfsoft.xgxt.zxdk.xnwxdk.cssz.XnwxdkCsszService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-24 ����02:51:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkjmCsszAction extends SuperAction<XnwxdkjmCsszModel, XnwxdkjmCsszService> {
private static final String KGZT_CLOSE = "0";
	
	private static final String url = "zxdk_xnwxdkjm_cssz.do";
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnwxdkjmCsszModel myForm = (XnwxdkjmCsszModel) form;
		XnwxdkjmCsszService service = new XnwxdkjmCsszService();
		XnwxdkjmCsszModel model = service.getModel();
		if (model != null) {
			if (StringUtil.isNull(model.getSqkg())) {
				model.setSqkg(KGZT_CLOSE);
			}
			BeanUtils.copyProperties(myForm, model);
		} else {
			myForm.setSqkg(KGZT_CLOSE);
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("zxdk");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);
		request.setAttribute("path", "zxdk_xnwxdkjm_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xmwhShfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (!StringUtil.isNull(value)) {
			XtwhShlcService xtwhShlc = new XtwhShlcService();
			List<HashMap<String, String>> spjbListByGnmk = xtwhShlc
					.getSpjbListByGnmk(value);// spgwid Ϊѡ����������̴���ֵ
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONArray.fromObject(spjbListByGnmk));
		}
		return null;

	}

}
