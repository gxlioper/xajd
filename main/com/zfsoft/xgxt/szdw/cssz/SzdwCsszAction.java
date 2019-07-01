/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-2 ����06:54:16 
 */  
package com.zfsoft.xgxt.szdw.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:�������� Action
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-7-2 ����06:53:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class SzdwCsszAction  extends SuperAction{

	private SzdwCsszService service = new SzdwCsszService();
	
	private static final String url = "szdw_cssz.do?method=bbsjCssz";
	
	/**
	 * @����:˼������ ������������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-3 ����04:42:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	//�˹���һ��Ϊ������Ϊ����Ա���á���ɲ�����
	/*public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzdwCsszForm myForm = (SzdwCsszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//��ȡ����Ա��ְ�������
		SzdwCsszForm rzsqModel = service.getModel(CsszUtil.SZDW_FDYRZSQ);
		//��ȡ����Ա��ѵ�������
		SzdwCsszForm pxModel = service.getModel(CsszUtil.SZDW_FDYPXSQ);
		//��ȡѧ���ɲ�ְ���������
		SzdwCsszForm zwModel = service.getModel(CsszUtil.SZDW_XSGBZWSQ);
		request.setAttribute("splcList", ShlcUtil.getShlcByGnmk("szdw"));
		request.setAttribute("rzsqModel", rzsqModel);
		request.setAttribute("pxModel", pxModel);
		request.setAttribute("zwModel", zwModel);
		request.setAttribute("path", "szdw_cssz.do?method=cssz");
		return mapping.findForward("szdw_cssz");
	}*/
	/**
	 * @����:˼������ -��������-����Ա��������
	 * @���ߣ�wanghj
	 * @���ڣ�2014-1-3 ����10:38:15
	 */
	@SystemAuth(url = "szdw_cssz.do?method=fdycssz",rewritable=ReadWrite.WRITEABLE)
	public ActionForward fdycssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzdwCsszForm myForm = (SzdwCsszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//��ȡ����Ա��ְ�������
		SzdwCsszForm rzsqModel = service.getModel(CsszUtil.SZDW_FDYRZSQ);

		XtwhShlcService shlcService = new XtwhShlcService();
		//��ȡ˼�����������
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("szdw");
		request.setAttribute("shlcList", shlc);
		request.setAttribute("rzsqModel", StringUtils.formatData(rzsqModel));
		request.setAttribute("path", "szdw_cssz.do?method=fdycssz");
		return mapping.findForward("fdycssz");
	}
	/**
	 * ���ʱ���������
	 */
	@SystemAuth(url = "szdw_cssz.do?method=bbsjCssz",rewritable=ReadWrite.WRITEABLE)
	public ActionForward bbsjCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		SzdwCsszForm myForm = (SzdwCsszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			if("0".equals(myForm.getKg())){
				myForm.setKssj("");
				myForm.setJssj("");
			}
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//��ȡ���ʱ�����
		SzdwCsszForm bbsjModel = service.getModel(CsszUtil.SZDW_BBSJ);
		
		request.setAttribute("bbsjModel", bbsjModel);
		request.setAttribute("path", "szdw_cssz.do?method=bbsjCssz");
		return mapping.findForward("bbsjCssz");
	}
	
	/**
	 * @����:˼������ -��������-��ɲ���������
	 * @���ߣ�wanghj
	 * @���ڣ�2014-1-3 ����10:42:17
	 */
	@SystemAuth(url = "szdw_cssz.do?method=bgbcssz",rewritable=ReadWrite.WRITEABLE)
	public ActionForward bgbcssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzdwCsszForm myForm = (SzdwCsszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//��ȡѧ���ɲ�ְ���������
		SzdwCsszForm zwModel = service.getModel(CsszUtil.SZDW_XSGBZWSQ);
		XtwhShlcService shlcService = new XtwhShlcService();
		//��ȡ˼�����������
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("szdw");
		request.setAttribute("shlcList", shlc);
		request.setAttribute("zwModel", StringUtils.formatData(zwModel));
		request.setAttribute("path", "szdw_cssz.do?method=bgbcssz");
		return mapping.findForward("bgbcssz");
	}
	
	/**
	 * @����:˼������ -��������-����Ա��������
	 * @���ߣ�wanghj
	 * @���ڣ�2014-1-3 ����10:38:15
	 */
	@SystemAuth(url = "szdw_cssz.do?method=fdypxCssz",rewritable=ReadWrite.WRITEABLE)
	public ActionForward fdypxCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzdwCsszForm myForm = (SzdwCsszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//��ȡ����Ա��ѵ�������
		SzdwCsszForm pxModel = service.getModel(CsszUtil.SZDW_FDYPXSQ);
		XtwhShlcService shlcService = new XtwhShlcService();
		//��ȡ˼�����������
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("szdw");
		request.setAttribute("shlcList", shlc);
		request.setAttribute("pxModel", StringUtils.formatData(pxModel));
		request.setAttribute("path", "szdw_cssz.do?method=fdycssz");
		
		return mapping.findForward("fdypxCssz");
	}
	
	
	
	
}
