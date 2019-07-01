/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-18 ����04:50:20 
 */  
package com.zfsoft.xgxt.rcsw.hdkhgl;

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
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.txhd.xmjg.TxhdXmjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-8-18 ����04:50:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HdkhglAction extends SuperAction<HdkhglForm, HdkhglService> {
	private HdkhglService service = new  HdkhglService();
	private TxhdXmjgService txhdservice  = new TxhdXmjgService();
	private final String HDKHGL ="hdkhgl";
	
	private static final String url = "rcsw_hdkhgl_jlygx.do";
	
	//���ڵǼǲ�ѯ
	@SystemAuth(url = url)
	public ActionForward getHddjList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HdkhglForm model = (HdkhglForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_hdkhgl_hddj.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getHddjList");
	}
	
	//��¼������ѯ
	@SystemAuth(url = url)
	public ActionForward getJlyGxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HdkhglForm model = (HdkhglForm) form;
	    User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("usertype", user.getUserType());
		String path = "rcsw_hdkhgl_jlygx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getJlyGxList");
	}
	
	//ѧ�����ڵǼ�״̬ά��
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward XsKqdjWh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdkhglForm hdgl = (HdkhglForm) form;
		HashMap<String, String> xskqdj = service.getXsHdKhxx(hdgl);
		request.setAttribute("xskqdj", xskqdj);
		request.setAttribute("sfcj", xskqdj.get("sfcj"));
		OptionUtil opt = new OptionUtil();
		request.setAttribute("kqdj",opt.getOptions("KQDJ") );
		return mapping.findForward("kqdj");
	}
	
	//����ѧ�����ڵǼ���Ϣ
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveKqdj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdkhglForm hdgl = (HdkhglForm) form;
		boolean result = service.savekqdj(hdgl);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//ѧ����¼�����ά��
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward XsJlGxWh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdkhglForm hdgl = (HdkhglForm) form;
		HashMap<String, String> xsjlgxxx = service.getXsHdKhxx(hdgl);
		request.setAttribute("xsjlgxxx", xsjlgxxx);
		HashMap<String, String> hdxxMAP = txhdservice.getOneHdjgList(hdgl.getHdjgid());
		request.setAttribute("data", hdxxMAP);
		return mapping.findForward("jlygx");
	}
	
	//����ѧ����¼�����ά��
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveXsjlgx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdkhglForm hdgl = (HdkhglForm) form;
		boolean result = true;
		result = service.runUpdate(hdgl);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * ѧ������ڽ������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HdkhglForm model = (HdkhglForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ

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
	
	//ѧ����¼�����ά���鿴
	@SystemAuth(url = url)
	public ActionForward XsJlGxWhview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdkhglForm hdgl = (HdkhglForm) form;
		if(null!=hdgl){
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(hdgl.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(HDKHGL);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String, String> xsjlgxxx = service.getXsHdKhxx(hdgl);
		HashMap<String, String> hdxxMAP = txhdservice.getOneHdjgList(hdgl.getHdjgid());
		request.setAttribute("data", hdxxMAP);
		request.setAttribute("xsjlgxxx", xsjlgxxx);
		return mapping.findForward("viewjlygx");
	}
	
	//ѧ�����ڵǼ���Ϣ�鿴
	@SystemAuth(url = url)
	public ActionForward kqdjview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdkhglForm hdgl = (HdkhglForm) form;
		if(null!=hdgl){
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(hdgl.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(HDKHGL);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String, String> xskqdj = service.getXsHdKhxx(hdgl);
		HashMap<String, String> hdxxMAP = txhdservice.getOneHdjgList(hdgl.getHdjgid());
		request.setAttribute("data", hdxxMAP);
		request.setAttribute("xskqdj", xskqdj);
		request.setAttribute("sfcj", xskqdj.get("sfcj"));
		return mapping.findForward("ck");
	}
	
	//ѧ�����ڵǼ�״̬����ά��
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward XsKqdjPlWh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OptionUtil opt = new OptionUtil();
		request.setAttribute("kqdj",opt.getOptions("KQDJ") );
		request.setAttribute("flag", "dgdj");
		return mapping.findForward("plkqdj");
	}
	
	//��������ѧ�����ڵǼ�
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveXsKqdjPlWh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdkhglForm hdgl = (HdkhglForm) form;
		boolean result = service.savePlsh(hdgl);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
