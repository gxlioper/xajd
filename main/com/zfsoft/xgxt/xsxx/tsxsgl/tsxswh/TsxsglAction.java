/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-14 ����01:45:18 
 */  
package com.zfsoft.xgxt.xsxx.tsxsgl.tsxswh;

import java.io.File;
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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.tsxsgl.xslxwh.XslxwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-5-14 ����01:45:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsglAction extends SuperAction {
	
    private TsxsglService service = new TsxsglService();
    private XslxwhService lxwhService = new XslxwhService();
	/**
	 * ��������ѧ�����Դӻ�����Ϣ���л�ȡѧ����Ϣ
	 */
	private static final String THJL = "thjl";
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * ��ע״̬��1[��ע]
	 */
	private static String GZZT_GZ = "1";
	
	private static final String url = "tsxsgl_tsxswh.do";

	@SystemAuth(url = url)
	public ActionForward tsxsglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsglForm myForm = (TsxsglForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//��ѯ
			List<HashMap<String,String>> tsxsInfoList = service.getPageList( myForm,user);
			JSONArray dataList = JSONArray.fromObject(tsxsInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "tsxsgl_tsxswh.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tsxsglManage");
	}
	/**
	 * 
	 * @����:����ѧ������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-14 ����04:56:52
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
	@SystemLog(description="����ѧ����Ϣ-����ѧ������-����ѧ��ά��-����XH:{xh}")
	public ActionForward tsxsAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsglForm myForm = (TsxsglForm) form;
		if(!StringUtil.isNull(myForm.getXh())){
			TsxsglForm model=service.getModel(myForm);
			if(null==model){
				model=new TsxsglForm();
				model.setXh(myForm.getXh());
				model.setType(SAVE);
			}else{
				model.setType(UPDATE);
			}
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			request.setAttribute("model", model);
	 	}
		//����ѧ��������Ϣ
		szXsxx(request,myForm.getXh());
		request.setAttribute("path", "tsxs_tsxswh.do?method=tsxsAdd");
		List<HashMap<String,String>>  xslxList = lxwhService.getXslxList();
		request.setAttribute("xslxList", xslxList);
		return mapping.findForward("tsxsAdd");
	}
	/**
	 * 
	 * @����:����ѧ���޸�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-14 ����05:19:38
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
	@SystemLog(description="����ѧ����Ϣ-����ѧ������-����ѧ��ά��-�޸�XH:{xh}")
	public ActionForward tsxsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsglForm myForm = (TsxsglForm) form;
		if(!StringUtil.isNull(myForm.getXh())){
			TsxsglForm model=service.getModel(myForm);
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			request.setAttribute("model", model);
	 	}
		//����ѧ��������Ϣ
		szXsxx(request,myForm.getXh());
		List<HashMap<String,String>>  xslxList = lxwhService.getXslxList();
		request.setAttribute("xslxList", xslxList);
		return mapping.findForward("tsxsUpdate");
	}
	
	/**
	 * 
	 * @����:����ѧ������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-14 ����05:14:56
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
	@SystemLog(description="����ѧ����Ϣ-����ѧ������-����ѧ��ά��-���ӻ��޸ı���XH:{xh},GZZT:{gzzt},CLCS:{clcs},XSLXDM:{xslxdm}")
	public ActionForward tsxsSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsglForm myForm = (TsxsglForm) form;
		User user =getUser(request);
		boolean result = service.tsxsEdit(myForm,user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:����ѧ���鿴
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-14 ����08:06:57
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
	public ActionForward tsxsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsglForm myForm = (TsxsglForm) form;
		HashMap<String,String> xsInfoMap = service.getTsxsInfoById(myForm.getXh());
		if(null!=xsInfoMap.get("xslxdm")&&!"".equals(xsInfoMap.get("xslxdm"))){
			xsInfoMap.put("xslxmc",lxwhService.getXslxMc(xsInfoMap.get("xslxdm")));
 		}
		//����ѧ��������Ϣ
		szXsxx(request,myForm.getXh());
		request.setAttribute("rs", StringUtils.formatData(xsInfoMap));
		return mapping.findForward("tsxsView");
	}
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setTsxsGzzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		String doType = request.getParameter("doType");
		if(UPDATE.equals(doType)){
		 	if(!StringUtil.isNull(ids)){
		 		String[] id = ids.split(",");
				String gzzt = request.getParameter("gzzt");
				boolean flag = service.updateBatchGzStatus(id, gzzt);

				String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS	
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
		 	}
		}else if(!StringUtils.isNull(ids)){
			String[] id = ids.split(",");
			if(id!=null && id.length == 1){
				HashMap<String,String> xsInfoList = service.getTsxsInfoById(ids);
				String gzzt = xsInfoList.get("gzzt");
				request.setAttribute("gzzt", gzzt);
			}
		}
			
		request.setAttribute("ids", ids);
		return mapping.findForward("setBatchGzzt");
	}

	/**
	 * 
	 * @����:����ѧ��ɾ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-15 ����08:47:14
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
	@SystemLog(description="����ѧ����Ϣ-����ѧ������-����ѧ��ά��-ɾ��XH:{values}")
	public ActionForward delTsxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		String[] id = values.split(",");
		try {
			int count = service.runDelete(id);
			response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.SYS_DEL_NUM,count)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		return null;
	}
	
	@SystemAuth(url = url)
	private void szXsxx(HttpServletRequest request,String xh){
		//��ѯѧ����Ϣ
		if(xh != null && !"".equals(xh)){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}

		//ѧ��������Ϣ��ʾ����
		jbxxList = new BdpzService().getJbxxpz(THJL);
		request.setAttribute("jbxxList", jbxxList);
	}
	
	/**
	 * 
	 * @����:����ѧ����Ϣ�б���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-15 ����09:03:02
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
	public ActionForward exportTsxsData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsxsglForm myForm=(TsxsglForm)form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm,user);
		for(int i=0;i<resultList.size();i++){
			String xslxdm = resultList.get(i).get("xslxdm");
			if(!StringUtil.isNull(xslxdm)){
				resultList.get(i).put("xslxmc", lxwhService.getXslxMc(xslxdm));
			}
		}
		
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	

}
