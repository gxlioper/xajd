/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016��12��27�� ����11:35:19 
 */  
package com.zfsoft.xgxt.xszz.zzkff;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������-������Ź���ģ��
 * @�๦������: ѧ������Action
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2016��12��27�� ����11:35:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZzkffAction extends SuperAction{
	
	private static final String XSZZ_ZZKFF = "xszz_zzkff";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	private static final String url = "xszz_zzkff.do?method=zzkffCx";
	
	/**
	 * @����:������Ų�ѯ���߼���ѯ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2016��12��27�� ����1:43:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws Exception 
	 */
	@SystemAuth(url = url)
	public ActionForward zzkffCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ZzkffForm zzkffForm = (ZzkffForm)form;
		ZzkffService zzkffService = new ZzkffService();
		
		if(QUERY.equals(zzkffForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zzkffForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			
			//��ѯ��������б����ݣ���Ӧjson
			List<HashMap<String,String>> resultList = zzkffService.getPageList(zzkffForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//Ĭ�ϲ�ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		//���������б���ҳ
		String path = "xszz_zzkff.do?method=zzkffCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);//
		
		return mapping.findForward("zzkffCx");
	}
	
	/**
	 * @����:����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2016��12��28�� ����5:02:20
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
	@SystemLog(description="����ѧ������-��������-�������-����XH:{xh},XN:{xn},XQ:{xq},JE:{je},XMMC:{xmmc},WSYHZT:{wsyhzt},YHFKXX:{yhfkxx}")
	public ActionForward zzkffZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ZzkffForm zzkffForm = (ZzkffForm)form;
		ZzkffService zzkffService = new ZzkffService();
		
		if (SAVE.equalsIgnoreCase(zzkffForm.getType())){
			//һ��ѧ������ͬһ��ѧ��ѧ�ڡ����ܴ���ͬһ����Ŀ����
			if(zzkffService.IsXmmcRepeat(zzkffForm)){
				//�����ظ�
				String messageKey = MessageKey.XSZZ_ZZKFF_XMMC_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}else{
				//�������ظ�
				boolean result = zzkffService.runInsert(zzkffForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
			return null;
		}
		
		if (!StringUtil.isNull(zzkffForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(zzkffForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		String path = "xszz_zzkff.do?method=zzkffZj";
		request.setAttribute("path", path);
		jbxxList = bdpzService.getJbxxpz(XSZZ_ZZKFF);
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�� ѧ��
		zzkffForm.setXq(Base.currXq);
		zzkffForm.setXn(Base.currXn);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		return mapping.findForward("zzkffZj");
	}
	
	/**
	 * @����:�޸�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2016��12��29�� ����4:20:42
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
	@SystemLog(description="����ѧ������-��������-�������-�޸�ID:{id},XH:{xh},XN:{xn},XQ:{xq},JE:{je},XMMC:{xmmc},WSYHZT:{wsyhzt},YHFKXX:{yhfkxx}")
	public ActionForward zzkffXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZzkffForm zzkffForm = (ZzkffForm)form;
		ZzkffService zzkffService = new ZzkffService();
		
		if (SAVE.equalsIgnoreCase(zzkffForm.getType())){
			//һ��ѧ������ͬһ��ѧ��ѧ�ڡ����ܴ���ͬһ����Ŀ���ƣ��ô���Ҫ���ѧ���ѻ������Ŀ���Ƶ����������޸�ʱ��ֹ�ظ�
			if(zzkffService.IsXmmcRepeat(zzkffForm)){
				//�����ظ�
				String messageKey = MessageKey.XSZZ_ZZKFF_XMMC_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}else{
				//�������ظ�
				boolean result = zzkffService.runUpdate(zzkffForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}
			return null;
		}
		
		if (!StringUtil.isNull(zzkffForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(zzkffForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		ZzkffForm zf = zzkffService.getModel(zzkffForm);
		BeanUtils.copyProperties(zzkffForm, zf);
		
		//ѧ��������Ϣ��ʾ����
		String path = "xszz_zzkff.do?method=zzkffXg";
		request.setAttribute("path", path);
		jbxxList = bdpzService.getJbxxpz(XSZZ_ZZKFF);
		request.setAttribute("jbxxList", jbxxList);
		//ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		
		return mapping.findForward("zzkffXg");
	}
	
	/**
	 * @����:����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2016��12��30�� ����10:00:05
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
	public ActionForward zzkffXq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZzkffForm zzkffForm = (ZzkffForm)form;
		ZzkffService zzkffService = new ZzkffService();
		
		ZzkffForm zf = zzkffService.getModel(zzkffForm.getId());//����ʹ���Զ�������ص�getModel�����Բ鵽ѧ������
		BeanUtils.copyProperties(zzkffForm, zf);
		
		if (!StringUtil.isNull(zzkffForm.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(zzkffForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ��ʾ����
		String path = "xszz_zzkff.do?method=zzkffXq";
		request.setAttribute("path", path);
		jbxxList = bdpzService.getJbxxpz(XSZZ_ZZKFF);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("zzkffXq");
	}
	
	/**
	 * @����:����ɾ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2016��12��30�� ����11:25:56
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
	@SystemLog(description="����ѧ������-��������-�������-ɾ��VALUES:{values}")
	public ActionForward zzkffSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZzkffService zzkffService = new ZzkffService();
		
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)){
			int num = zzkffService.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @����:����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2016��12��30�� ����2:58:15
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZzkffForm zzkffForm = (ZzkffForm)form;
		
		//���ݲ�ͬ��������� ȥ�Զ��嵼��
		ZzkffService zzkffService = new ZzkffService();
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zzkffForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = zzkffService.getAllList(zzkffForm,user);//��ѯ�����м�¼������ҳ
		
		
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = zzkffForm.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcglbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
