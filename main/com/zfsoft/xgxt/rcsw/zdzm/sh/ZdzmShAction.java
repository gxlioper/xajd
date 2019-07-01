/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-7 ����10:14:17 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.sh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.zdzm.comm.ZdzmComm;
import com.zfsoft.xgxt.rcsw.zdzm.cssz.ZdzmCsszForm;
import com.zfsoft.xgxt.rcsw.zdzm.cssz.ZdzmCsszService;
import com.zfsoft.xgxt.rcsw.zdzm.jggl.ZdzmJgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: �ڶ�֤����˹���
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-7 ����10:14:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzmShAction extends SuperAction {
	
	/**
	 * @���� ѧ����ʾ��Ϣ������
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * @���� ����������ӿ�
	 */
	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * @���� ѧ���������б�
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * @���� ��ʼ��ѧ����Ϣ�������б�
	 */
	public ZdzmShAction() {
		super();
		jbxxList = bdpzService.getJbxxpz(ZdzmComm.ZDZM_BDID);
	}

	private static final String url = ZdzmComm.PATH_SH;
	
	/**
	 * 
	 * @����:��ѯ�ڶ�֤����������б�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-4 ����04:44:38
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
	@SystemAuth(url = url)
	public ActionForward queryZdzmShList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmShForm model  = (ZdzmShForm) form;
		ZdzmShService service = new ZdzmShService();
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		
		ZdzmCsszService csszService = new ZdzmCsszService();			
		ZdzmCsszForm csszModel = csszService.getCssz();
		request.setAttribute("csszModel", csszModel);
		
		request.setAttribute("path", ZdzmComm.PATH_SH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("queryZdzmShList");
	}
	
	/**
	 * 
	 * @����:����ڶ�֤������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����01:38:49
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
	public ActionForward shZdzmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmShForm model  = (ZdzmShForm) form;
		ZdzmShService service = new ZdzmShService();
		
		String xtgwid = model.getXtgwid();
		String shid = model.getShid();
		
		ZdzmShForm dataModel = service.getModel(model.getZdzmsqid());
		
		if(null != dataModel){
			BeanUtils.copyProperties(model, dataModel);
			model.setShid(shid);
			model.setXtgwid(xtgwid);
		}
		request.setAttribute("jbxxList", jbxxList);
		XsxxService xsxxService = new XsxxService();
		HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		return mapping.findForward("shZdzmsq");
	}
	
	/**
	 * 
	 * @����:�鿴 �ڶ�֤������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����01:38:49
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
	@SystemAuth(url = url)
	public ActionForward viewZdzmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmShForm model  = (ZdzmShForm) form;
		ZdzmShService service = new ZdzmShService();
		
		ZdzmShForm dataModel = service.getModel(model.getZdzmsqid());
		
		if(null != dataModel){
			BeanUtils.copyProperties(model, dataModel); //���ݿ���
		}
		request.setAttribute("jbxxList", jbxxList);
		XsxxService xsxxService = new XsxxService();
		HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(dataModel.getXh());
		request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		return mapping.findForward("viewZdzmsq");
	}
	
	/**
	 * 
	 * @����:�ύ�ڶ�֤�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����02:14:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-�ڶ�֤������-�ڶ�֤�����-���ZDZMSQID:{zdzmsqid}")
	public ActionForward shZdzmsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		ZdzmShForm model  = (ZdzmShForm) form;
		ZdzmShService service = TransactionControlProxy.newProxy(new ZdzmShService());
		User user = getUser(request);
		
		//�����������
		boolean result = service.saveSh(model,user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @����:�����ڶ�֤�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����03:22:24
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
	@SystemLog(description="�����ճ�����-�ڶ�֤������-�ڶ�֤�����-����ZDZMSQID:{zdzmsqid}")
	public ActionForward cancelZdzmsqAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmShForm model  = (ZdzmShForm) form;
		ZdzmShService service = new ZdzmShService();
		
		ZdzmShForm dataModel = service.getModel(model.getZdzmsqid());
		
		dataModel.setShzt(Constants.YW_SHZ);
		
		boolean isSuccess = service.runUpdate(dataModel); //���� Model
		
		if(isSuccess){
			isSuccess = new ZdzmJgService().deleteZdzmJgBySqid(dataModel.getZdzmsqid()); 
		}
		
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		
		return null;
	}
	
	
	/**
	 * 
	 * @����:������˱���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-25 ����03:57:35
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
	@SystemLog(description="�����ճ�����-�ڶ�֤������-�ڶ�֤�����-�������ID:{id}")
	public ActionForward zdzmPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZdzmShForm model  = (ZdzmShForm) form;
		ZdzmShService service = TransactionControlProxy.newProxy(new ZdzmShService());
		User user = getUser(request);
		
		if("SAVE".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		return mapping.findForward("zdzmPlsh");
	}
	
}
