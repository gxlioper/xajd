/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-7-19 ����09:08:02 
 */  
package com.zfsoft.xgxt.dekt.qnzyry;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dekt.qnzyhd.QnzyhdService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ����־Ը��Աaction(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-7-19 ����09:08:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QnzyryAction extends SuperAction<QnzyryForm, QnzyryService>{
	public static final String BMWSH = "0";//����δ���
	public static final String BMSHTG = "1";//�������ͨ��
	public static final String BMSHWTG = "2";//�������δͨ��
	public static final String GSWSH = "0";//��ʱδ���
	public static final String GSSHTG = "1";//��ʱ���ͨ��
	public static final String GSSHWTG = "2";//��ʱ����˻� 
	public static final String YTJ = "1";//�ύ��ʱ״̬
	public static final String DEKTQNZYHD = "dektqnzyhd";
	private QnzyryService service = new QnzyryService();
	private static List<HashMap<String, String>> jbxxList = null;
	
	private static final String url = "dekt_qnzyhd_hdlb.do";
	
	private static final String YfbUrl = "dekt_qnzyhd_yfbhd.do";
	
	private static final String ycjUrl = "dekt_qnzyhd_ycjhd.do";
	
	private static final String gsshUrl = "dekt_qnzyhd_gssh.do";
	
	private static final String gsjgUrl = "dekt_qnzyhd_gsjg.do";
	
	
	
	/** 
	 * @����:�����¼(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-19 ����09:18:14
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
	
	public ActionForward saveJl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm)form;
		User user = getUser(request);
		model.setXh(user.getUserName());
		model.setBmzt(BMWSH);
		model.setGsshzt(GSWSH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sj = sdf.format(Calendar.getInstance().getTime());
		model.setBmsj(sj);
		if(service.isExist(model)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.JHZY_DEKT_YBM));
			return null;
		}else{			
			boolean result = service.runInsert(model);
			String message = result?MessageKey.JHZY_DEKT_BM_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
			return null;
		}
	}
	
	/** 
	 * @����:��Ա����ҳ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-21 ����10:14:48
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
	public ActionForward rygl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyryForm model = (QnzyryForm)form;
		QnzyhdService hdService = new QnzyhdService();
		HashMap<String,String> map = hdService.getQnzyhdInfo(model.getHdid());
		request.setAttribute("data", map);
//		List<HashMap<String,String>> ryList = service.getXmryList(model);
//		request.setAttribute("ryList",ryList);
		return mapping.findForward("rygl");		
	}
	
	/** 
	 * @����:������Ա(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-21 ����02:11:57
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
	public ActionForward searchRy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyryForm model = (QnzyryForm)form;
		List<HashMap<String,String>> ryList = service.getXmryList(model);
		JSONArray dataList = JSONArray.fromObject(ryList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/** 
	 * @����:���汨���������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-24 ����09:12:27
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
	public ActionForward bcBmPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyryForm model = (QnzyryForm)form;
		String messageKey = service.plshBmzt(model);
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @����:�ύ��ʱ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-25 ����03:54:49
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
	public ActionForward tjgs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		model.setSftj(YTJ);
		model.setGsshzt(GSWSH);
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @����:�����걨��¼(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-25 ����04:12:57
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
	public ActionForward qxjl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		boolean result = service.runDelete(new String[]{model.getId()})>0;
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @����:����ʱ����б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-25 ����05:41:08
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
	@SystemAuth(url = gsshUrl)
	public ActionForward gsshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
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
		request.setAttribute("searchTj", searchModel);		
		String path = "dekt_qnzyhd_gssh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gsshList");
	}
	
	/** 
	 * @����:�������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-26 ����09:34:35
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

	public ActionForward dgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		HashMap<String, String> data = service.getZyhdbmxx(model.getId());
		if (!StringUtil.isNull(data.get("xh"))) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(data.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		jbxxList = bdpzService.getJbxxpz(DEKTQNZYHD);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", data);
		return mapping.findForward("dgsh");
	}
	
	/** 
	 * @����:���浥�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-26 ����02:13:17
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
	
	public ActionForward BcDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		if(model.getGsshzt().equalsIgnoreCase(GSSHWTG)){
			model.setSftj("");
		}
		boolean result = service.runUpdate(model);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @����:�������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-26 ����03:31:22
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
	
	public ActionForward plsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		if(SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.plsh(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("plsh");
	}
	
	/** 
	 * @����:�������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-26 ����04:47:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws 
	 */
	
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyryForm model = (QnzyryForm) form;
		model.setGsshzt(GSWSH);
		model.setSftj(YTJ);
		boolean result = service.plcx(model.getIds());
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;		
	}
	
	/** 
	 * @����:���湤ʱ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-26 ����05:41:56
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
	
	public ActionForward saveGs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyryForm model = (QnzyryForm) form;
		if(StringUtils.isNull(model.getGs())){
			model.setGs("");
		}
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;		
	}
	
	/** 
	 * @����:��ʱ����б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-27 ����09:14:11
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
	@SystemAuth(url = gsjgUrl)
	public ActionForward gsjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QnzyryForm model = (QnzyryForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);			
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getJgPageList(model, user);			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);		
		String path = "dekt_qnzyhd_gsjg.do";
		request.setAttribute("path", path);
		//ѧ���û���¼��ѯ��õ���ѧ��
		if(user.getUserStatus().equalsIgnoreCase("stu")){			
			String xh = user.getUserName();
			String fs = service.countXf(xh);
			request.setAttribute("xf", fs);
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gsjgList");		
	}
	
	/** 
	 * @����:��ʱ��˽������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-27 ����01:58:46
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
	@SystemAuth(url = gsjgUrl)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//��ѯ�����м�¼������ҳ
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
	 * @����:�鿴־Ը�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-7-27 ����02:32:25
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
	public ActionForward viewZyhd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		BdpzService bdpzService = new BdpzService();
		jbxxList = bdpzService.getJbxxpz(DEKTQNZYHD);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String,String> data = service.getZyhdbmxx(model.getId());
		request.setAttribute("data", data);
		if (!StringUtil.isNull(data.get("xh"))) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(data.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		return mapping.findForward("viewZyhd");
	}
	
	/**
	 * @description	�� ����
	 * @author 		�� ������1282��
	 * @date 		��2017-12-15 ����03:53:36
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		QnzyryService service = new QnzyryService();
		QnzyryForm copyModel = service.getModel(model);
		if(null != copyModel){
			BeanUtils.copyProperties(model, StringUtils.formatData(copyModel));
		}
		return mapping.findForward("pfzhhd");
	}
	
	/**
	 * @description	�� ���ֱ���
	 * @author 		�� ������1282��
	 * @date 		��2017-12-15 ����05:25:42
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pfbc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnzyryForm model = (QnzyryForm) form;
		boolean result = service.runUpdateForPf(model);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
}
