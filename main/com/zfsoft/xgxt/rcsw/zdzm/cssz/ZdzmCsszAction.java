/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-3 ����02:21:44 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.cssz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.rcsw.zdzm.comm.ZdzmComm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-�ڶ�֤������ģ��
 * @�๦������: �ճ�����-�ڶ�֤��-��������
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-3 ����02:21:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzmCsszAction extends SuperAction {
	
	/**@���� ���عر�*/
	private static final String KG_0 = "0"; 
	
	/**@���� ���ؿ���*/
	private static final String KG_1 = "1"; 
	
	/**
	 * @���� ����������
	 */
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	private static final String url = ZdzmComm.PATH_CSSZ;
	
	/**
	 * 
	 * @����:��ѯ������������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-3 ����03:34:13
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
	public ActionForward queryCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		ZdzmCsszService service = new ZdzmCsszService();
		
		ZdzmCsszForm model = (ZdzmCsszForm)	form;

		ZdzmCsszForm csszModel = service.getCssz();
		if(null != csszModel){
			BeanUtils.copyProperties(model, csszModel);
		}
		//���뿪���б�
		request.setAttribute("sqkgList", service.getSqkgList());
		//���ؿ����б�
		request.setAttribute("xzkgList", service.getXzkgList());
		//���ؿ����б�
		request.setAttribute("xzkzList", service.getXzkzList());
		// ����Ϊ����������
		request.setAttribute("shlcList", shlcService.getShlcByDjlx(ZdzmComm.SPL_MKID));
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", ZdzmComm.PATH_CSSZ);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("queryCssz");
	}
	
	/**
	 * 
	 * @����:�����������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-4 ����11:46:06
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
	@SystemLog(description="�����ճ�����-�ڶ�֤������-��������-����")
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmCsszService service = new ZdzmCsszService();
		ZdzmCsszForm model = (ZdzmCsszForm)	form;
		boolean result = false;
		JSONObject message = null;
		//����ǹرղ�����ֱ�Ӹ������ݿ�
		if(StringUtils.isEqual(model.getKsqkg() , KG_0)){
			result = service.closeCssz(model);
			message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		//����ǿ������������Ҳ��ı�splid �� ֱ�Ӹ������ݿ�
		}else if(StringUtils.isEqual(model.getKsqkg() , KG_1)){
			
			//2014-5-9update 785 ��������Ѵ���������У��������жϡ�
//			HashMap<String , String> splxgxx = service.getShlcxx();
			
//			if(StringUtils.isEqual(splxgxx.get("shlts"), "0") || 
//					StringUtils.isEqual(model.getSplid(), splxgxx.get("splcid"))){
				result = service.saveCssz(model);
				message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
//			}
//			else if(!StringUtils.isEqual(model.getSplid(), splxgxx.get("splcid")) && !StringUtils.isEqual(splxgxx.get("shlts"), "0")){
//				message = getJsonMessage("��������������У������޸ģ�");
//			}
		}
		response.getWriter().print(message);
		return null;
	}
	
	
}
