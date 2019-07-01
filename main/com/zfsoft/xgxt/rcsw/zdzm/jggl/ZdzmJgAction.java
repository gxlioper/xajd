/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-7 ����02:53:58 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.jggl;

import java.io.File;
import java.util.ArrayList;
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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bbdmpz.utils.BbdmUtils;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.zdzm.comm.PrintService;
import com.zfsoft.xgxt.rcsw.zdzm.comm.ZdzmComm;
import com.zfsoft.xgxt.rcsw.zdzm.cssz.ZdzmCsszForm;
import com.zfsoft.xgxt.rcsw.zdzm.cssz.ZdzmCsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-7 ����02:53:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzmJgAction extends SuperAction {

	/**
	 * ѧ����ʾ��Ϣ������
	 */
	private static BdpzService bdpzService = new BdpzService();
	
	/**
	 * ѧ���������б�
	 */
	private List<HashMap<String,String>> jbxxList = null;
	
	/**
	 * @���� ���޲ι����� 
	 * @���� ����ʼ��������
	 */
	public ZdzmJgAction() {
		super();
		jbxxList = bdpzService.getJbxxpz(ZdzmComm.ZDZM_BDID);
	}
	
	private static final String url = ZdzmComm.PATH_JG;
	
	

	/**
	 * 
	 * @����:��ѯ�ڶ�֤������б�
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
	public ActionForward queryZdzmJgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmJgForm model  = (ZdzmJgForm) form;
		ZdzmJgService service = new ZdzmJgService();
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
		
		request.setAttribute("path", ZdzmComm.PATH_JG);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("queryZdzmJgList");
	}
	
	/**
	 * 
	 * @����:�����ڶ�֤�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-5 ����03:34:50
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
	public ActionForward addZdzmJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmJgForm model  = (ZdzmJgForm) form;

		if(!StringUtil.isNull(model.getXh())){
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
			
		}
		String path = "rcsw_zdzm_jggl.do?method=addZdzmJg";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		this.saveToken(request);
		return mapping.findForward("addZdzmJg");
	}
	
	
	/**
	 * 
	 * @����:�����������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����09:09:36
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
	@SystemLog(description="�����ճ�����-�ڶ�֤������-�ڶ�֤�����-����XH:{xh},SQLY:{sqly}")
	public ActionForward addZdzmJgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		ZdzmJgForm model  = (ZdzmJgForm) form;
		
		model.setJrsj(DateUtils.getCurrDate());
		model.setSjly("0");
		model.setSqsj(DateUtils.getCurrDate());
		model.setZdzmjgid(UniqID.getInstance().getUniqIDHash().toUpperCase());
		
		ZdzmJgService service = new ZdzmJgService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:�޸��ڶ�֤�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����02:18:47
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
	public ActionForward updateZdzmJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmJgService service = new ZdzmJgService();
		ZdzmJgForm model  = (ZdzmJgForm) form;
		if(!StringUtil.isNull(model.getZdzmjgid())){
			ZdzmJgForm dataModel = service.getModel(model.getZdzmjgid());
			BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(dataModel));
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		
		String path = "rcsw_zdzm_jggl.do?method=updateZdzmJg";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("updateZdzmJg");
	}
	
	/**
	 * 
	 * @����:�����ڶ� ���
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����09:09:36
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
	@SystemLog(description="�����ճ�����-�ڶ�֤������-�ڶ�֤�����-�޸�ZDZMJGID:{zdzmjgid},XH:{xh},SQLY:{sqly}")
	public ActionForward updateZdzmJgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmJgService service = new ZdzmJgService();
		ZdzmJgForm model  = (ZdzmJgForm) form;
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:ɾ���ڶ�֤��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-6 ����03:52:58
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
	@SystemLog(description="�����ճ�����-�ڶ�֤������-�ڶ�֤�����-ɾ��SQIDS:{sqids}")
	public ActionForward deleteZdzmJgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmJgService service = new ZdzmJgService();
		String sqids = request.getParameter("sqids"); //ɾ����sqids
		int isSuccess = 0;
		if(StringUtils.isNotBlank(sqids)){
			isSuccess = service.deleteZdzmJgs(sqids.split(","));
		}
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);	
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	
	/**
	 * 
	 * @����:�鿴�ڶ�֤�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-7 ����09:11:20
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
	public ActionForward viewZdzmJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmJgService service = new ZdzmJgService();
		ZdzmJgForm model  = (ZdzmJgForm) form;
		if(!StringUtil.isNull(model.getZdzmjgid())){
			ZdzmJgForm dataModel = service.getModel(model.getZdzmjgid());
			BeanUtils.copyProperties(model, dataModel);
			XsxxService xsxxService = new XsxxService();
			HashMap<String , String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx); //��ѯѧ��������Ϣ
		}
		
		String path = "rcsw_zdzm_jggl.do?method=viewZdzmJg";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewZdzmJg");
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[����:1036]
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmJgForm model  = (ZdzmJgForm) form;
		ZdzmJgService service = new ZdzmJgService();

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
		
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @����:��ӡword���
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2014-3-31 ����09:56:39
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
	public ActionForward print(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xhs = request.getParameter("xhs");
		String pyccmc = request.getParameter("pyccmc");
		HashMap<String,String> xzModel = new HashMap<String, String>();
		if(xhs != null){
			xzModel.put("ts", xhs.split(",").length + "");
			xzModel.put("pyccmc", pyccmc);
			xzModel.put("xhs", xhs);
			request.setAttribute("xzModel", xzModel);
			request.setAttribute("zdzmlbList", new PrintService().getXzlbList());
		}
		
		return mapping.findForward("print");
	}
	
	/**
	 * ���ر��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward doPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZdzmJgForm model  = (ZdzmJgForm) form;
		String zdzmsqids = request.getParameter("xhs");
		if(zdzmsqids != null && zdzmsqids.split(",").length == 1){
			HashMap<String , String> data = new PrintService().getData(zdzmsqids);
			if("11488".equals(Base.xxdm)){
				String xymc=data.get("xymc");
				if(!StringUtil.isNull(xymc)){
					xymc = xymc.replace("ѧԺ", "");
				}
				data.put("xymc", xymc);
			}
			HashMap<String , Object> objectData = new HashMap<String, Object>();
			objectData.putAll(data);
			File file = null;
			String guid = "rcsw_zdzm";
			if(Base.xxdm.equals("10052")){
				String pyccmc = data.get("pyccmc");
				if(StringUtils.equals("����",pyccmc)){
					if("0".equals(model.getType()))
						guid = "rcsw_zdzm_cn_10052";
					else if("1".equals(model.getType()))
						guid = "rcsw_zdzm_en_10052";
				}else if(StringUtils.equals("Ԥ����",pyccmc)){
					if("0".equals(model.getType()))
						guid = "rcsw_zdzm_ysb_cn_10052";
					else if("1".equals(model.getType()))
						guid = "rcsw_zdzm_ysb_en_10052";
				}else{
					if("0".equals(model.getType()))
						guid = "rcsw_zdzm_cn_10052";
					else if("1".equals(model.getType()))
						guid = "rcsw_zdzm_en_10052";
				}
			}else if("11488".equals(Base.xxdm)){
				guid="rcsw_zdzm_11488";
			}
			objectData.put("xxmc", Base.xxmc);
			file = BbdmUtils.getSigleFile(guid, objectData);
			FileUtil.outputWord(response, file);
		}else{
			List<File> files = new ArrayList<File>();
			for(String zdzmsqid:zdzmsqids.split(",")){
				HashMap<String , String> data = new PrintService().getData(zdzmsqid);
				if("11488".equals(Base.xxdm)){
					String xymc=data.get("xymc");
					if(!StringUtil.isNull(xymc)){
						xymc = xymc.replace("ѧԺ", "");
					}
					data.put("xymc", xymc);
				}
				HashMap<String , Object> objectData = new HashMap<String, Object>();
				objectData.putAll(data);
				File file=null;
				String guid = "rcsw_zdzm";
				if(Base.xxdm.equals("10052")){
					String pyccmc = data.get("pyccmc");
					if(StringUtils.equals("����",pyccmc)){
						if("0".equals(model.getType()))
							guid = "rcsw_zdzm_cn_10052";
						else if("1".equals(model.getType()))
							guid = "rcsw_zdzm_en_10052";
					}else if(StringUtils.equals("Ԥ����",pyccmc)){
						if("0".equals(model.getType()))
							guid = "rcsw_zdzm_ysb_cn_10052";
						else if("1".equals(model.getType()))
							guid = "rcsw_zdzm_ysb_en_10052";
					}else {
						if("0".equals(model.getType()))
							guid = "rcsw_zdzm_cn_10052";
						else if("1".equals(model.getType()))
							guid = "rcsw_zdzm_en_10052";
					}
				}else if("11488".equals(Base.xxdm)){
					guid="rcsw_zdzm_11488";
				}
				objectData.put("xxmc", Base.xxmc);
				file = BbdmUtils.getSigleFile(guid, objectData);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}

}
