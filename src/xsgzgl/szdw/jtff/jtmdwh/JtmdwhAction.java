/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����02:27:22 
 */  
package xsgzgl.szdw.jtff.jtmdwh;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.szdw.jtff.jtff.JtffForm;
import xsgzgl.szdw.jtff.jtff.JtffService;
import xsgzgl.szdw.jtff.util.JtffUtilService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.gzjl.gzjljg.GzjljgForm;
import com.zfsoft.xgxt.szdw.gzjl.gzjlsq.GzjlsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2016-3-8 ����02:27:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JtmdwhAction extends SuperAction<JtmdwhForm, JtmdwhService> {
	private final String JTFF ="xjtff";
	private JtmdwhService service = new  JtmdwhService();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	private static final String url = "szdw_jtff_jtmdwh.do";
	
	@SystemAuth(url = url)
	public ActionForward getJtmdwhcx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    JtmdwhForm model = (JtmdwhForm) form;
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
		String path = "szdw_jtff_jtmdwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	@SystemAuth(url = url)
	/**
	 * ������������
	 */
	public ActionForward AddZcJtff(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtmdwhForm model = (JtmdwhForm) form;
		User user = getUser(request);
		if (!StringUtil.isNull(model.getZgh())) {
		    JtffUtilService jtffservice = new JtffUtilService();
			HashMap<String, String> jsjbxx = jtffservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", jsjbxx);
		}
		// ��ʦ������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JTFF);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jtff_jtmdwh.do?method=AddZcJtff";
		request.setAttribute("path", path);
		request.setAttribute("jtlb", "zc");
		return mapping.findForward("addzcjtff");
	}
	
	@SystemAuth(url = url)
	/**
	 * ���ӹ̶�����
	 */
	public ActionForward AddGdJtff(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtmdwhForm model = (JtmdwhForm) form;
		User user = getUser(request);
		if (!StringUtil.isNull(model.getZgh())) {
		    JtffUtilService jtffservice = new JtffUtilService();
			HashMap<String, String> jsjbxx = jtffservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", jsjbxx);
		}
		// ��ʦ������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JTFF);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jtff_jtmdwh.do?method=AddGdJtff";
		request.setAttribute("path", path);
		request.setAttribute("jtlb", "gd");
		return mapping.findForward("addgdjtff");
	}
	
	@SystemAuth(url = url)
	public ActionForward saveZcjt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtmdwhForm model = (JtmdwhForm) form;
		boolean result = false;
		String message=null;
	    User user = getUser(request);
		if(model.getType().equals("save")){
			// �жϵ�ǰ��ʦ�Ƿ��ڱ����м�¼
			JtffUtilService service1 = new JtffUtilService();
			boolean isExist = service1.isExists(model.getZgh());
			if (isExist) {
				message = MessageUtil.getText(MessageKey.SZDW_JTFF_RYWH_REPEAT,model.getZgh());
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			result = service.runInsert(model);
		}else if(model.getType().equals("update")){
			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward saveGdjt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtmdwhForm model = (JtmdwhForm) form;
		boolean result = false;
		String message=null;
	    User user = getUser(request);
		if(model.getType().equals("save")){
			// �жϵ�ǰ��ʦ�Ƿ��ڱ����м�¼
			JtffUtilService service1 = new JtffUtilService();
			boolean isExist = service1.isExists1(model.getZgh());
			if (isExist) {
				message = MessageUtil.getText(MessageKey.SZDW_JTFF_RYWH_REPEAT,model.getZgh());
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			model.setJtlb("gd");
			result = service.runUpdate(model);
		}else if(model.getType().equals("update")){
			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * �޸���������
	 */
	public ActionForward UpdateZcJtff(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtmdwhForm model = (JtmdwhForm) form;
		JtmdwhForm myForm = service.getModel(model);
		if (!StringUtil.isNull(model.getZgh())) {
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		    JtffUtilService jtffservice = new JtffUtilService();
			HashMap<String, String> jsjbxx = jtffservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", jsjbxx);
		}
		// ��ʦ������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JTFF);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jtff_jtmdwh.do?method=UpdateZcJtff";
		request.setAttribute("path", path);
		return mapping.findForward("updatezcjtff");
	}
	
	@SystemAuth(url = url)
	/**
	 * �޸Ĺ̶�����
	 */
	public ActionForward UpdateGdJtff(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JtmdwhForm model = (JtmdwhForm) form;
		JtmdwhForm myForm = service.getModel(model);
		if (!StringUtil.isNull(model.getZgh())) {
			BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		    JtffUtilService jtffservice = new JtffUtilService();
			HashMap<String, String> jsjbxx = jtffservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", jsjbxx);
		}
		// ��ʦ������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JTFF);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jtff_jtmdwh.do?method=UpdateGdJtff";
		request.setAttribute("path", path);
		return mapping.findForward("updategdjtff");
	}
	
	@SystemAuth(url = url)
	/**
	 * ɾ����������
	 */
	public ActionForward DelZcjt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * ɾ���̶�����
	 * @throws Exception 
	 */
	public ActionForward DelGdjt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDeletegdjtmd(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @����:���������鿴
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2016-3-10 ����02:28:14
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
	public ActionForward ZcjtCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		JtmdwhForm myForm = (JtmdwhForm) form;
		JtmdwhForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			JtffUtilService jtffservice = new JtffUtilService();
			HashMap<String, String> xsjbxx = jtffservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JTFF);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("ck", model);
		return mapping.findForward("zcjtck");
	}
	
	/**
	 * 
	 * @����:�̶������鿴
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2016-3-10 ����02:28:22
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
    public ActionForward GdjtCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	JtmdwhForm myForm = (JtmdwhForm) form;
    	JtmdwhForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			JtffUtilService jtffservice = new JtffUtilService();
			HashMap<String, String> xsjbxx = jtffservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(JTFF);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("ck", model);
		return mapping.findForward("gdjtck");
	}
    
    @SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
    	JtmdwhForm model = (JtmdwhForm) form;

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
}
