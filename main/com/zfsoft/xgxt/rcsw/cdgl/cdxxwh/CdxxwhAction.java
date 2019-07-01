/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����10:08:16 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.cdxxwh;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.cdgl.sh.CdshService;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: ������Ϣά��Action 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-4-23 ����10:08:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdxxwhAction extends SuperAction {

	/**
	 * @���ԣ� SPL_MKID ģ��ID
	 */
	public static final String SPL_MKID = "rcsw";
	
	/**
	 *  @���ԣ� PATH ·��
	 */
	private static final String PATH = "rcsw_cdgl_cdxxwhgl.do";
	
	private static final String url = "rcsw_cdgl_cdxxwhgl.do";
	
	/**
	 * @���ԣ� service ������Ϣά������
	 */
	private CdxxwhService service = new CdxxwhService();
	
	/**
	 * @���ԣ� shlc ����������ӿ�
	 */
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * @���ԣ� shlcService ����������ӿ�
	 */
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	/**
	 * 
	 * @����:ҳ��dispatcher ������Ϣά����ѯҳ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:34:51
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
	public ActionForward cdxxwhCx(ActionMapping mapping, ActionForm form,
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
		return mapping.findForward("cdxxwhCx");
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
		CdxxwhForm model = (CdxxwhForm) form;
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
	 * @����:popup ������Ϣѡ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-24 ����10:37:26
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
	public ActionForward showCdxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String gotoPath = request.getParameter("goto");
		String search_sfkfsq = request.getParameter("search_sfkfsq");
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("path", PATH);
		request.setAttribute("search_sfkfsq", search_sfkfsq);
		return mapping.findForward("showCdxx");
	}
	
	/**
	 * 
	 * @����:popup ������Ϣѡ�� ���������б�
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
	public ActionForward showCdxxQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdxxwhForm model = (CdxxwhForm) form;
		//��ѯ
		List<HashMap<String,String>> resultList = service.getPopupCdxx(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:ҳ��dispatcher ������Ϣά������ҳ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����02:21:24
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
	public ActionForward cdxxwhXz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//��ȡ�����б�
		request.setAttribute("sfkfsqkgList", service.getKfkgList());
		//��ȡ���������б�
		request.setAttribute("shlcList", shlcService.getShlcByDjlx(SPL_MKID));
		//ҳ��ת��
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("cdxxwhXz");
	}
	
	
	
	/**
	 * 
	 * @����: �鿴ʹ���е���������
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2014-10-23 ����01:47:43
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
	public ActionForward checkSplc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String splcid=request.getParameter("splc");
		String cdid = request.getParameter("cdid");
		CdshService service = new CdshService();
		HashMap<String,String> map=new HashMap<String, String>();
		map.put("message",service.isHaveSplcSj(splcid,cdid).toString());
		response.getWriter().print(JSONObject.fromBean(map));
		return null;
	}
	
	/**
	 * 
	 * @����:ҳ��dispatcher ������Ϣά���޸�ҳ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����02:21:24
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
	public ActionForward cdxxwhXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdxxwhForm model = (CdxxwhForm) form;
		String cdid = model.getCdid();
		//��ѯmodel������������ֵ
		if(StringUtils.isNotBlank(cdid)){
			CdxxwhForm data = service.getModel(cdid);
			if(data != null){
				xgxt.utils.String.StringUtils.formatData(data);
				BeanUtils.copyProperties(model, xgxt.utils.String.StringUtils.formatData(data));
			}
		}
		HashMap<String,String> rs = service.getCdxxByCdid(cdid);
		request.setAttribute("rs", xgxt.utils.String.StringUtils.formatData(rs));
		//��ȡ�����б�
		request.setAttribute("sfkfsqkgList", service.getKfkgList());
		//��ȡ���������б�
		request.setAttribute("shlcList", shlcService.getShlcByDjlx(SPL_MKID));
		//ҳ��ת��
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("cdxxwhXg");
	}
	
	/**
	 * 
	 * @����:������Ϣά������ �������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����03:32:01
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
	@SystemLog(description="�����ճ�����-���ع���-������Ϣά��-����CDMC:{cdmc},LD:{ld},FJ:{fj},RNRS:{rnrs},LXR:{lxr},LXFS:{lxfs},DWKFSJKSSJ:{dwkfsjkssj},DWKFSJJSSJ:{dwkfsjjssj},SFKFSQ:{sfkfsq},SPLCID:{splcid}")
	public ActionForward cdxxwhXzAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CdxxwhForm model = (CdxxwhForm) form;
		
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			String cdmc = model.getCdmc();
			int cds = service.getCdsByCdmc(cdmc);
			if(cds > 0){
				String messageKsy = MessageKey.RCSW_CDSQ_CHECK_ERROR2;
				JSONObject message = getJsonMessageByKey(messageKsy);
				message.put("flag", "repeat");
				response.getWriter().print(message);
				return null;
			} else {
				super.resetToken(request);
			}
		}
		
		//����������Ϣ
		boolean isSuccess = service.runInsert(model);
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����:������Ϣά���޸� �������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����03:32:01
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
	@SystemLog(description="�����ճ�����-���ع���-������Ϣά��-�޸�CDID:{cdid},CDMC:{cdmc},LD:{ld},FJ:{fj},RNRS:{rnrs},LXR:{lxr},LXFS:{lxfs},DWKFSJKSSJ:{dwkfsjkssj},DWKFSJJSSJ:{dwkfsjjssj},SFKFSQ:{sfkfsq},SPLCID:{splcid}")
	public ActionForward cdxxwhXgAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdxxwhForm model = (CdxxwhForm) form;
		model.setJbsbjs(HtmlUtil.xmlZy(model.getJbsbjs()));
		//���³�����Ϣ
		boolean isSuccess = service.runUpdate(model);
		JSONObject message = getJsonMessageByKey(isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @����:ҳ��dispatcher ������Ϣά���鿴ҳ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����02:21:24
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
	public ActionForward cdxxwhCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdxxwhForm model = (CdxxwhForm) form;
		String cdid = model.getCdid();
		//��ѯ��ȡ������Ϣ
		if(StringUtils.isNotBlank(cdid)){
			request.setAttribute("cdxx", xgxt.utils.String.StringUtils.formatData(service.getCdxxByCdid(cdid)));
		}
		//ҳ��ת��
		return mapping.findForward("cdxxwhCk");
	}
	
	/**
	 * 
	 * @����:ɾ��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-���ع���-������Ϣά��-ɾ��CDIDS:{cdids}")
	public ActionForward cdxxwhScAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String cdxxids = request.getParameter("cdids"); //ɾ����ids
		int isSuccess = 0;
		List<String> cdidList = new ArrayList<String>();
		if(StringUtils.isNotBlank(cdxxids)){
			String[] cdidArr = cdxxids.split(",");
			for (String cdid : cdidArr) {
				if(!service.check(cdid))
					cdidList.add(cdid);
			}
			
			if(cdidList.size() >= 1){
				String[] exeCdid = new String[cdidList.size()];
				for (int i = 0; i < cdidList.size(); i++) {
					exeCdid[i] = cdidList.get(i);
				}
				isSuccess = service.runDelete(exeCdid);
			}
		}
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);	
		response.getWriter().print(getJsonMessage(message));
		return null;
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
		CdxxwhForm model = (CdxxwhForm) form;
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
	 * @����:����������̻�ȡ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-10-21 ����02:34:04
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
	public ActionForward xmwhShfw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (!StringUtil.isNull(value)) {
			XtwhShlcService xtwhShlc = new XtwhShlcService();
			List<HashMap<String, String>> spjbListByGnmk = xtwhShlc
					.getSpjbListByGnmk(value);// spgwid Ϊѡ����������̴���ֵ
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONArray.fromObject(spjbListByGnmk));
		}
		return null;

	}
}
