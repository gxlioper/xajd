/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-09-20 ����03:02:29 
 */  
package com.zfsoft.xgxt.xlzx.zxswh;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ-��ѯʦ����ģ��
 * @�๦������: ��ѯʦ����
 * @���ߣ� whj [���ţ�1004]
 * @ʱ�䣺 2013-09-20 ����03:02:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class ZxsglAction extends SuperAction {
	
	private static final String url = "xlzx_zxs_zxs.do";
	
	private static ZxsglService service = new ZxsglService();
	/**
	 * �ڸ�״̬��1
	 */
	private static String STATUS_ZG = "1";
	/** 
	 * @����:��ѯʦ��Ϣ�б�
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-09-20 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward zxsglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglForm myForm = (ZxsglForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			//��ѯ
			List<HashMap<String,String>> zxsInfoList = service.getPageList( myForm,user);
			JSONArray dataList = JSONArray.fromObject(zxsInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xlzx_zxs_zxs.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("zxsglManage");
	}
	

	/** 
	 * @����:ѡ����ѯʦ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-09-20 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getZxsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglForm myForm = (ZxsglForm) form;
		User user = getUser(request);
		String doType = request.getParameter("doType");
		if (QUERY.equals(doType)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			// �����ڸ�״̬
			myForm.setStatus(STATUS_ZG);
			//��ѯ
			List<HashMap<String,String>> zxsInfoList = service.getPageList( myForm,user);
			JSONArray dataList = JSONArray.fromObject(zxsInfoList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("gotoPath");
		gotoPath = gotoPath.replaceAll("[$]", "&");
		request.setAttribute("path", "xlzx_zxs_zxs.do");
		request.setAttribute("gotoPath", gotoPath);
		return mapping.findForward("getZxsInfo");
	}
	
	/** 
	 * @����:��ѯʦ��Ϣ����
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-09-20 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	public ActionForward zxsglDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		String zgh = request.getParameter("zgh");
	 	HashMap<String, String> zxsInfoList=new HashMap<String,String>();
	 	
	 	if(!StringUtil.isNull(zgh)){
	 		zxsInfoList = service.getZxsInfoByZgh(zgh);
	 		if(zxsInfoList.size()==0){
	 			zxsInfoList.putAll(service.getFdyInfo(zgh));
	 		}
	 	}
	 	if(StringUtil.isNull(doType)){
	 		doType="add";
	 	}
	 	//���ϳ���ѧԺ�ó�������Ի�
	 	if("11527".equals(Base.xxdm)){
	 		if(zxsInfoList.get("sclydm")!=null && !"".equals(zxsInfoList.get("sclydm"))){
		 		zxsInfoList.put("sclymc", service.getSclyMc(zxsInfoList.get("sclydm")));
		 	}
		 	List<HashMap<String,String>>  sclyList = service.getSclyList();
		 	request.setAttribute("sclyList", sclyList);
	 	}
	 	
	 	request.setAttribute("path", "xlzx_zxs.do?method=zxsglDetail&doType="+doType);
		request.setAttribute("doType", doType);
		request.setAttribute("xqList",service.getXq());
		request.setAttribute("xq", zxsInfoList.get("xq"));
		request.setAttribute("zxsInfoList", StringUtils.formatData(zxsInfoList));
		
		return mapping.findForward("zxsglDetail");
	}
	
	/** 
	 * @����:������ѯʦ�ڸ�״̬
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-09-20 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setZgStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglForm myForm = (ZxsglForm) form;
		String dealZgh = request.getParameter("dealZgh");
		String doType = request.getParameter("doType");
		if(UPDATE.equals(doType)){
		 	if(!StringUtil.isNull(dealZgh)){
		 		String[] zgh = dealZgh.split(",");
		 		String status = myForm.getStatus();
		 		try {
					boolean flag = service.updateBatchZgStatus(zgh, status);
					String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
				} catch (Exception e) {
					e.printStackTrace();
				}
		 	}
		 // ��ֻѡ���ˣ�����ʾ���ڸ�״̬��
		}else if(!StringUtils.isNull(dealZgh)){
			String[] zgh = dealZgh.split(",");			
			if(zgh!=null && zgh.length == 1){
				
				List<HashMap<String,String>> zxsInfoList = service.getZxsInfoByZgh(zgh);
				String status = zxsInfoList.get(0).get("status");
				request.setAttribute("status", status);
			}
		}
		
		request.setAttribute("dealZgh", dealZgh);
		
		return mapping.findForward("setBatchZgStatus");
	}
		
	
	/** 
	 * @����:���ݱ�Ż�ȡ��ѯʦ��Ϣ��������
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-09-20 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url)
	public ActionForward getZxsInfoList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] zgh = request.getParameterValues("zgh");
		List<HashMap<String,String>> zxsInfoList = new ArrayList<HashMap<String,String>>();
		if(StringUtils.isArrayNotNull(zgh)){
			zxsInfoList = service.getZxsInfoByZgh(zgh);
		}
		JSONArray dataList = JSONArray.fromObject(zxsInfoList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/** 
	 * @����:ɾ����ѯʦ��Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-09-20 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward deleteZxsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _delZgh = request.getParameter("delZgh");
		String[] delZgh = _delZgh.split(",");
		int count = 0;
		try {
			count = service.delZxsByzgh(delZgh);
			response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.SYS_DEL_NUM,count)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_DEL_FAIL);
		}
		return null;
	}
	
	/** 
	 * @����:������ѯʦ��Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-09-20 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveZxsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		if(!StringUtil.isNull(doType) && doType.equals("add")){
			
			ZxsglForm model = (ZxsglForm) form;
			
			HashMap<String, String>  zxsInfo = service.getZxsInfoByZgh(model.getZgh());
			if(zxsInfo !=null && zxsInfo.size()!=0){
				response.getWriter().print(false);
				return null;
			}
			try {
				boolean flag = service.saveZxsInfo(model);
				response.getWriter().print(flag);
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		request.setAttribute("doType", doType);
		return mapping.findForward("zxsglDetail");
		
	}
	/** 
	 * @����:�޸���ѯʦ��Ϣ
	 * @���ߣ�whj [���ţ�1004]
	 * @���ڣ�2013-09-20 ����03:02:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param args
	 * void �������� 
	 * @throws 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateZxsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglForm model = (ZxsglForm) form;
		try {
			boolean flag = service.updateZxsInfo(model);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return null;
	}
	
	/**
	 * @����:��ѯʦ��Ϣ�б���
	 * @���ߣ�wanghj [���ţ�1004]
	 * @���ڣ�2013-10-11 ����1:25:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportZxsData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglForm myForm=(ZxsglForm)form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm,user);
		for(int i=0;i<resultList.size();i++){
			String sclydm = resultList.get(i).get("sclydm");
			if(!StringUtil.isNull(sclydm)){
				resultList.get(i).put("sclymc", service.getSclyMc(sclydm));
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
	public ActionForward getSclymc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsglForm myForm = (ZxsglForm) form;
		String sclydms = myForm.getSclydm();
		String sclymc = "";
		if(sclydms!=null){
			sclymc =service.getSclyMc(sclydms);
		}
		response.getWriter().print(sclymc);
		return null;
	}
}
