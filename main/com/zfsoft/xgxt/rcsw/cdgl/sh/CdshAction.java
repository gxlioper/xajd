/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-7 ����10:14:17 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.rcsw.cdgl.jg.CdjgService;
import com.zfsoft.xgxt.rcsw.cdgl.sq.CdsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: �������Action 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-4-23 ����04:48:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdshAction extends SuperAction {
	
	/**
	 * @���� ѧ����ʾ��Ϣ������
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * @���� ѧ����Ϣ����
	 */
	private XsxxService xsxxService = new XsxxService();
	
	/**
	 * @���� ѧ���������б�
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * @���� �����������
	 */
	private CdsqService cdsqService = new CdsqService();
	
	/**
	 * @���� ���ؽ������
	 */
	private CdjgService cdjgService = new CdjgService();
	
	/**
	 * @���� ����������˷���
	 */
	private CdshService service = new CdshService();
	
	/**
	 * @���ԣ� CDGL_BBID ������id
	 */
	private static final String CDGL_BBID = "rcswcdgl"; 
	
	/**
	 *  @���ԣ� PATH ·��
	 */
	public static final String PATH = "rcsw_cdgl_cdshgl.do";
	
	public static final String url = "rcsw_cdgl_cdshgl.do";
	
	/**
	 * @���� ��ʼ��ѧ����Ϣ�������б�
	 */
	public CdshAction() {
		super();
		jbxxList = bdpzService.getJbxxpz(CDGL_BBID);
	}

	/**
	 * 
	 * @����:��ѯ����������������б�
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
	public ActionForward cdshCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cdshCx");
	}
	
	/**
	 * 
	 * @����:���������б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:43:04
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
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdshForm model = (CdshForm) form;
		User user = getUser(request);
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
	
	/**
	 * 
	 * @����:��˳����������
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
	public ActionForward cdshSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdshForm model  = (CdshForm) form;
		String sqid = model.getSqid();
		String xtgwid = model.getXtgwid();
		String shid = model.getShid();
		
		if(StringUtils.isNotBlank(sqid)){
			CdshForm dataModel = service.getModel(sqid);
			if(null != dataModel){
				BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(dataModel));
				model.setShid(shid);
				model.setXtgwid(xtgwid);
			}
			HashMap<String , String> cdsqxx = cdsqService.getCdsqxx(model.getSqid());//��ȡ����������Ϣ
			request.setAttribute("cdsqxx", xgxt.utils.String.StringUtils.formatData(cdsqxx));
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}

		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("cdshSh");
	}
	
	/**
	 * 
	 * @����:�鿴 ������������
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
	public ActionForward cdshCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdshForm model  = (CdshForm) form;
		CdshService service = new CdshService();
		
		if(StringUtils.isNotBlank(model.getSqid())){
			CdshForm dataModel = service.getModel(model.getSqid());
			if(null != dataModel){
				BeanUtils.copyProperties(model, dataModel);
			}
			HashMap<String , String> cdsqxx = cdsqService.getCdsqxx(model.getSqid());//��ȡ����������Ϣ
			request.setAttribute("cdsqxx", xgxt.utils.String.StringUtils.formatData(cdsqxx));
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(dataModel.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("cdshCk");
	}
	
	/** 
	 * @����:�ύ�����������
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
	@SystemLog(description="�����ճ�����-���ع���-����ʹ���������-���SQID:{sqid}")
	public ActionForward cdshShAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdshForm model  = (CdshForm) form;
		CdshService service = new CdshService();
		User user = getUser(request);
		
		boolean checkConditions = service.checkSqSjd(model);

		String messageKey ;
		
		if(!checkConditions){
			messageKey = MessageKey.RCSW_CDSQ_CHECK_ERROR;
		}else{
			//�����������
			boolean result = service.saveSh(model,user);
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;	
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @����:���������������
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
	@SystemLog(description="�����ճ�����-���ع���-����ʹ���������-����SQID:{sqid}")
	public ActionForward cancelCdshAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdshForm model  = (CdshForm) form;
		CdshService service = new CdshService();
		CdshForm dataModel = service.getModel(model.getSqid());
		dataModel.setShzt(Constants.YW_SHZ);
		boolean isSuccess = service.runUpdate(dataModel); //���� Model
		if(isSuccess){
			isSuccess = cdjgService.deleteJgBySqid(dataModel.getSqid()); //ɾ�����������
		}
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����:�����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-28 ����11:52:29
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
	@SystemLog(description="�����ճ�����-���ع���-����ʹ���������-��������ID:{id}")
	public ActionForward cdsqPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CdshForm model  = (CdshForm) form;
		CdshService service = new CdshService();
		
		User user = getUser(request);
		
		if("SAVE".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		return mapping.findForward("cdsqPlsh");
	}
	
}
