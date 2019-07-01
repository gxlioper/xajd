/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-1 ����04:07:35 
 */  
package com.zfsoft.xgxt.zxdk.syddk;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAuditAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCssz;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;
import common.Globals;
/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ChenQ[����:856]
 * @ʱ�䣺 2015-7-1 ����04:07:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SydkSqshAction extends SuperAuditAction<SydkSqshModel,SydkSqshService> {
    
	private static final String GJZXDK = "gjzxdk";
	
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param gnid
	 * @param squrl
	 * @param shurl
	 */
	protected SydkSqshAction(String gnid, String squrl, String shurl) {
		super(gnid, squrl, shurl);
	}
	
	public SydkSqshAction(){
		this("zxdk_sydk", "zxdk_sydk_dksq.do", "zxdk_sydk_dksh.do");
	}
   
	/**
	 * 
	 * @����:��������
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-7-2 ����09:12:32
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
	@SystemAuth(url = "zxdk_sydk_dksq.do")
	public ActionForward dksqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz cssz  =  csszService.getModel();
		request.setAttribute("cssz", cssz);
		
		request.setAttribute("path", "zxdk_sydk_dksq.do");
		FormModleCommon.commonRequestSet(request);
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{cssz.getSydkxn()});
		request.setAttribute("searchTj", searchModel);
		
		return mapping.findForward("dksqList");
	}
	/**
	 * 
	 * @����:��������DataGrid��ȡ����
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-7-2 ����09:14:53
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
	@SystemAuth(url = "zxdk_sydk_dksq.do")
	public ActionForward getDksqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SydkSqshService service = getService();
		SydkSqshModel model = (SydkSqshModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		
		response.getWriter().print(dataList);
		
		return null;
		
	}
	/**
	 * 
	 * @����:����
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-7-2 ����02:15:57
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
	@SystemAuth(url = "zxdk_sydk_dksq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward dksq(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		SydkSqshModel model = (SydkSqshModel) form;
		SydkSqshService service = getService();
		ZxdkCsszService csszService = new ZxdkCsszService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//�����Ƽ���ѧ���Ի�
			if("10704".equalsIgnoreCase(Base.xxdm)){
				service.setXsjbxx(xsjbxx, model.getXh());
			}
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("cssz", csszService.getModel());
		request.setAttribute("yhList", service.getYhList());
		String path = "zxdk_sydk.do?method=dksq";
		request.setAttribute("path", path);
		this.saveToken(request);
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("dksq_zjdx");
		}
		return mapping.findForward("dksq");
	 }
	
	/**
	 * 
	 * @����:�޸�����
	 *  @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-7-2 ����05:08:25
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
	@SystemAuth(url = "zxdk_sydk_dksq.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SydkSqshModel myForm = (SydkSqshModel) form;
		SydkSqshService service = getService();
		SydkSqshModel model = service.getModel(myForm.getId());
		if (model != null) {
			BeanUtils.copyProperties(myForm, model);
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//�����Ƽ���ѧ���Ի�
			if("10704".equalsIgnoreCase(Base.xxdm)){
				service.setXsjbxx(xsjbxx, model.getXh());
			}
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("yhList", service.getYhList());
		//request.setAttribute("dkxxList", service.getDkxxList(model.getId()));
		String path = "zxdk_sydk.do?method=xgDksq";
		request.setAttribute("path", path);
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("xgDksq_zjdx");
		}
		return mapping.findForward("xgDksq");
	}
	
	@SystemAuth(url = "zxdk_sydk_dksq.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "������ѧ����-��Դ�ش���-��ִ¼��-ɾ��VALUES:{id}")
	public ActionForward delDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SydkSqshModel myForm = (SydkSqshModel) form;
		SydkSqshService service = getService();
		boolean result = service.runDelete(new String[]{myForm.getId()}) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**��ѧ��ѧ���ѯ��¼�Ƿ����**/
	public ActionForward isExitsByXhAndXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SydkSqshService service = getService();
		SydkSqshModel model = (SydkSqshModel) form;
		boolean flag = service.isExitsByXhAndXn(model);
		response.getWriter().print(flag);
		return null;
	}	
	
	@SystemAuth(url = "zxdk_sydk_dksh.do")
	public ActionForward dkshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("path", "zxdk_sydk_dksh.do");
		FormModleCommon.commonRequestSet(request);
		
		//���ø߼���ѯĬ������
		ZxdkCsszService csszService = new ZxdkCsszService();
		ZxdkCssz cssz  =  csszService.getModel();
		searchModel.setSearch_tj_xn(new String[]{cssz.getSydkxn()});
		
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("dkshList");
	}
	
	@SystemAuth(url = "zxdk_sydk_dksh.do")
	public ActionForward getDkshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SydkSqshService service = getService();
		SydkSqshModel model = (SydkSqshModel) form;
		User user = getUser(request);
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String,String>> resultList = service.getAudingList(model, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		
		response.getWriter().print(dataList);
		
		return null;
		
	}
	
	@SystemAuth(url = "zxdk_sydk_dksh.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward dksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SydkSqshService service = getService();
		SydkSqshModel myForm = (SydkSqshModel) form;
		
		SydkSqshModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//�����Ƽ���ѧ���Ի�
			if("10704".equalsIgnoreCase(Base.xxdm)){
				service.setXsjbxx(xsjbxx, model.getXh());
			}
		}
		
		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		//request.setAttribute("dkxxList", service.getDkxxList(model.getId()));
		List<HashMap<String,String>> shxx = service.getShxx(model);
		
		//�������ֶ�
		if(shxx.size()==1){
			myForm.setZd3(model.getHtbh());
			myForm.setZd5(model.getDkkssj());
			myForm.setZd6(model.getHzjym());
		}else{
			myForm.setZd3(shxx.get(shxx.size()-2).get("zd3"));
			myForm.setZd5(shxx.get(shxx.size()-2).get("zd5"));
			myForm.setZd6(shxx.get(shxx.size()-2).get("zd6"));
		}
		
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("dksh_zjdx");
		}
		
		return mapping.findForward("dksh");
	}
	
	public ActionForward ckDksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		SydkSqshService service = getService();
		SydkSqshModel myForm = (SydkSqshModel) form;
		SydkSqshModel model = service.getModel(myForm.getId());
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//�����Ƽ���ѧ���Ի�
			if("10704".equalsIgnoreCase(Base.xxdm)){
				service.setXsjbxx(xsjbxx, model.getXh());
			}
		}

		//ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String,String>> jbxxList = bdpzService.getJbxxpz(GJZXDK);
		request.setAttribute("jbxxList", jbxxList);
		//request.setAttribute("dkxxList", service.getDkxxList(model.getId()));
		if(Base.xxdm.equals(Globals.XXDM_ZJDX)){
			return mapping.findForward("ckDksq_zjdx");
		}
		return mapping.findForward("ckDksq");
	}
	
	@SystemAuth(url = "zxdk_sydk_dksh.do")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SydkSqshService service = getService();
		SydkSqshModel model = (SydkSqshModel) form;
		User user = getUser(request);

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @����: ���������˹��ܣ����Ի���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-18 ����09:25:26
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
	public ActionForward sydPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SydkSqshModel model = (SydkSqshModel) form;
		SydkSqshService sydkService = new SydkSqshService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(request.getParameter("type"))) {
			String message = sydkService.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("sydPlsh");
	}
	
	/**
	 * 
	 * @����: ��д��Դ�ش������뱣�淽��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-18 ����09:25:26
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
	@Override
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		SydkSqshModel model = (SydkSqshModel) form;
		SydkSqshService service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����: ��д��Դ�ش����ύ���뱣�淽��(��ֱ���ύ)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-18 ����09:25:26
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
	@Override
	public ActionForward saveAndSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		SydkSqshModel model = (SydkSqshModel) form;
		SydkSqshService service = getService();
		boolean isSuccess = false;
		//���������¼��Ӧ�����״̬������С�
		model.setShzt(Constants.YW_SHZ);
		
		//��֤����ID�����ID��һ���ԣ���ϵͳ����ΨһID
		if (StringUtil.isNull(model.getId())){
			String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			model.setId(uuid);
			
			//���������¼
			isSuccess = service.runInsert(model);
		} else {
			isSuccess = service.runUpdate(model);
		}
		
		JSONObject message = null;
		
		if (isSuccess){
			//�ύ���뵽�������
			message = submit("zxdk_sydk" , model.getId(), "zxdk_sydk_dksq.do", "zxdk_sydk_dksh.do");
		} else {
			message = getJsonMessageByKey(MessageKey.SYS_SUBMIT_FAIL);
		}
		
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @��������д���ύ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-3 ����11:19:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnid
	 * @param id
	 * @param squrl
	 * @param shurl
	 * @return
	 * @throws Exception
	 * JSONObject �������� 
	 * @throws
	 */
	private JSONObject submit(String gnid,String id,String squrl,String shurl)
	throws Exception {
		
		ShlcInterface shlc = new CommShlcImpl();
		
		SydkSqshService service = getService();
		//��ѯ�����¼
		SydkSqshModel model = service.getModel(id);
		String splcid = model.getSplcid();
		//�ύ��������
		boolean isSuccess = shlc.runSubmit(id, splcid, model.getXh(), shurl, squrl);
		
		if(isSuccess){
			//���������¼״̬
			model.setShzt(Constants.YW_SHZ);
			//model.setSplcid(splcid);
			isSuccess = service.runUpdate(model);
		}
		
		String message = isSuccess ? 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_SUCCESS) : 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);
		
		return getJsonMessage(message);
	}
}
