/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-4-8 ����10:23:06 
 */  
package com.zfsoft.xgxt.zxdk.tyxs.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCssz;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;

import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Ӣ[���ţ�1177]
 * @ʱ�䣺 2015-4-8 ����10:23:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TyxsCsszAction extends SuperAction<TyxsCssz, TyxsCsszService> {
	/**����ѧ����ѧѧ��������������*/
	private static final String KGZT_CLOSE = "0";
	private Log logger = LogFactory.getLog(TyxsCsszAction.class);
	
	private static final String url = "zxdk_tyxs_cssz.do";
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = " ����ѧ����ѧѧ������-��������")
	public ActionForward tyxsCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("start");
		TyxsCssz csszForm = (TyxsCssz) form;
		
		TyxsCsszService service = getService();
		TyxsCssz model = service.getModel();
		
		if (model != null){
			if (StringUtil.isNull(model.getXfzzsqkg())){
				
				model.setXfzzsqkg(KGZT_CLOSE);
			}
			
			if (StringUtil.isNull(model.getXfzzshkg())){
				model.setXfzzshkg(KGZT_CLOSE);
			}
			BeanUtils.copyProperties(csszForm, model);
		} else {
			csszForm.setXfzzshkg(KGZT_CLOSE);
			csszForm.setXfzzsqkg(KGZT_CLOSE);
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		// ������������������б��ȡֵͨ�÷���
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("zxdk");
		request.setAttribute("shlcList", shlc);
		
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		logger.info(onOff);
		logger.info("model"+model.toString());
		request.setAttribute("onOff", onOff);
		request.setAttribute("path", "zxdk_tyxs_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tyxsCssz");
	}
	
}
