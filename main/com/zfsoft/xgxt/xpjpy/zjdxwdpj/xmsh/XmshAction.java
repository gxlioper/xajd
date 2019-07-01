/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2017-5-22 ����05:42:24 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsh;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������
 * @�๦������: ��������-�ҵ�����-�������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-5-22 ����05:42:24 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmshAction extends SuperAction{
	private static final String url = "xpjpy_wdpj_pjsh.do";
	private XmshService service = new XmshService();
	
	/**
	 * @����: ��ת����Ŀ�����б�
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-22 ����08:27:33
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
	@SystemLog(description = "��������������-�ҵ�����-�����������-��ѯҳ��")
	public ActionForward getXmshList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*����������Ϣ*/
		CsszService csszService = new CsszService();
		CsszForm csszModel = csszService.getCsszModel();
		request.setAttribute("cssz", csszModel);
		/*Ĭ�ϲ�ѯ���� ,��ǰ���ڵ���������*/
		XmwhService xmwhService = new XmwhService();
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{xmwhService.getCsszMap().get("xn")});
		request.setAttribute("searchTj", searchModel);
		/*����path*/
		String path = "xpjpy_wdpj_pjsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmshList");
	}
	
	/**
	 * @����: ��ȡ��Ŀ�����б�JSON����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-23 ����12:03:25
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
	@SystemLog(description = "��������������-�ҵ�����-�����������-��ѯ����Json����")
	public ActionForward getXmshListDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmshForm model = (XmshForm)form;
		/*���ɸ߼���ѯ����*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		/*��ѯ������JSON����*/
		List<HashMap<String, String>> resultList = service.getAudingListSingle(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����: �������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-24 ����01:57:03
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
	@SystemLog(description = "��������������-�ҵ�����-�����������-���")
	public ActionForward xmshPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception { 	 	 
 		request.setAttribute("id", request.getParameter("id"));
		return mapping.findForward("xmshPlsh");
	}
	 
	/** 	
	 * @����: �����ϸ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-24 ����02:26:33
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
	@SystemLog(description = "��������������-�ҵ�����-�����������-���ҳ����ϸ")
	public ActionForward xmshShmx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmshForm model = (XmshForm)form;
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		//��ѯ
		List<HashMap<String,String>> viweShmx = service.getXmshShmx(model,user);
		JSONArray dataList = JSONArray.fromObject(viweShmx);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����: ��˲�����1��ͨ��  2����ͨ��  3���˻�
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-25 ����03:30:44
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
	@SystemLog(description = "��������������-�ҵ�����-�����������-��˱���")
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmshForm model = (XmshForm)form;
		User user = getUser(request);
		System.out.println("��ʼʱ�䡾"+System.currentTimeMillis()+"��");
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		String message = service.saveZdPlsh(model, user);
		response.getWriter().print(getJsonMessage(message));
		System.out.println("����ʱ�䡾"+System.currentTimeMillis()+"��");
		return null;
	}
	
	/**
	 * @����: ������˳���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-26 ����11:26:14
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
	@SystemLog(description="������������-�ҵ�����-�������-��������-SHID:{shid}")
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmshForm model = new XmshForm();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("splc");
		String xmdm = request.getParameter("xmdm");
		String xh = request.getParameter("xh");
		model.setSplc(splc);
		model.setShid(shid);
		model.setXmdm(xmdm);
		model.setXh(xh);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
		// ��˳����ɹ�
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	/**
	 * @����: ��󼶵ĳ���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-26 ����03:25:48
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
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String splcid = request.getParameter("splcid");
		String shid = request.getParameter("shid");
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		// ҵ��ع�
		boolean result = service.cancel(splcid, shxx.get("ywid"),user);
		// ҵ��ع��ɹ�
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}
	
	/**
	 * @����: ��Ŀ��˵���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-16 ����05:57:00
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
	public ActionForward xmshExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmshForm model = (XmshForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		/*��ѯ�����м�¼������ҳ*/
		List<HashMap<String, String>> resultList = service.getAudingListSingle(model,user);
		/*�������ܴ���*/
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		/*��ǰ����Ա*/
		exportModel.setZgh(user.getUserName());
		/*��������*/
		exportModel.setDataList(resultList);
		/*���õ�ǰ�������ܱ��*/
		exportModel.setDcclbh(request.getParameter("dcclbh"));
		/*���ɵ����ļ�*/
		File file = exportService.getExportFile(exportModel);
		FileUtil.outputExcel(response, file);
		return null;
	}
}
