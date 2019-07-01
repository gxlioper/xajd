/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-22 ����10:05:09 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.xsyybxxx;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ���չ���-ѧ��ԤԼ������Ϣ 
 * @���ߣ� ������ [����:1123]
 * @ʱ�䣺 2015-1-22 ����10:05:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsyybxxxAction extends SuperAction {
	
	private static final String RCSWBXGL = "rcswbxgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "rcsw_bxgl_xsyybxxx.do";
	
	/**
	 * 
	 * @����: ѧ��ԤԼ������Ϣ�б�
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2015-1-22 ����11:01:27
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
	public ActionForward xsyybxxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsyybxxxService service = new XsyybxxxService();
		XsyybxxxForm myForm = (XsyybxxxForm) form;
		User user = getUser(request);

		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("rcsw_bxgl_xsyybxxx.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_bxgl_xsyybxxx.do";
		request.setAttribute("path", path);
		request.setAttribute("tableName", "XG_RCSW_BXGL_XSYYBXXXB");
		request.setAttribute("realTable", "XG_RCSW_BXGL_XSYYBXXXB");
		FormModleCommon.commonRequestSet(request);
		
		if(!"stu".equalsIgnoreCase(user.getUserType())){
			//��ȡ�̶���ʽϵͳ������
			String nowTime = GetTime.getTimeByFormat("yyyymmdd");
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_kssj(new String[]{nowTime});
			searchModel.setSearch_tj_jssj(new String[]{nowTime});
			request.setAttribute("searchTj", searchModel);
		}

		return mapping.findForward("xsyybxxxList");
	}
	
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2015-1-26 ����09:37:08
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
	@SystemLog(description = "�����ճ�����-���չ���-ѧ��ԤԼ������Ϣ-����XH:{xh},YYSJ:{yysj},BLNR:{blnr}")
	public ActionForward yyxxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsyybxxxService service = new XsyybxxxService();
		XsyybxxxForm myForm = (XsyybxxxForm) form;
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		szXsxx(request,myForm.getXh());
		request.setAttribute("path", "rcsw_bxgl_xsyybx.do?method=yyxxAdd");
		request.setAttribute("model", myForm);
		this.saveToken(request);
		return mapping.findForward("yyxxAdd");
	}
	
	/**
	 * 
	 * @����: �޸�
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2015-1-26 ����09:37:08
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
	@SystemLog(description = "�����ճ�����-���չ���-ѧ��ԤԼ������Ϣ-�޸�YYBXID:{yybxid},XH:{xh},YYSJ:{yysj},BLNR:{blnr}")
	public ActionForward yyxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsyybxxxForm myForm = (XsyybxxxForm) form;
		XsyybxxxService service = new XsyybxxxService();
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//����ѧ��������Ϣ
		szXsxx(request,myForm.getXh());
		request.setAttribute("path", "rcsw_bxgl_xsyybx.do?method=yyxxUpdate");
//		request.setAttribute("type", "update");
//		XsyybxxxForm model = service.getModel(myForm);
//		BeanUtils.copyProperties(myForm, xgxt.utils.String.StringUtils.formatData(model));
		XsyybxxxForm model = (XsyybxxxForm)StringUtils.formatData(service.getModel(myForm));
		request.setAttribute("model", model);
		return mapping.findForward("yyxxUpdate");
	}
	
	/**
	 * 
	 * @����: ѧ��������Ϣ����
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2015-1-26 ����09:37:08
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
	private void szXsxx(HttpServletRequest request,String xh) {
		//��ѯѧ����Ϣ
		if(xh != null && !"".equals(xh)){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWBXGL);
		request.setAttribute("jbxxList", jbxxList);
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2015-1-26 ����09:37:08
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
	@SystemLog(description = "�����ճ�����-���չ���-ѧ��ԤԼ������Ϣ-ɾ��VALUES:{values}")
	public ActionForward yyxxDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsyybxxxService service = new XsyybxxxService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
					: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
	}
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2015-1-26 ����09:37:08
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
	public ActionForward ckYyxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsyybxxxForm myForm = (XsyybxxxForm) form;
		XsyybxxxService service = new XsyybxxxService();
		//����ѧ��������Ϣ
		szXsxx(request,myForm.getXh());
		request.setAttribute("path", "rcsw_bxgl_xsyybx.do?method=ckYyxx");
//		request.setAttribute("type", "update");
		XsyybxxxForm model = service.getModel(myForm);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("ckYyxx");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2015-1-26 ����09:37:08
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
	public ActionForward yyxxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsyybxxxForm model = (XsyybxxxForm)form;
		XsyybxxxService service = new XsyybxxxService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String,String>> resultList = service.getAllList(model,user);
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
}
