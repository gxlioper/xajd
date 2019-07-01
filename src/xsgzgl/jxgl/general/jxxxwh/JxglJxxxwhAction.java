package xsgzgl.jxgl.general.jxxxwh;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.zjsysxhb.ZjsySxhbForm;
import com.zfsoft.xgxt.dtjs.dtxxgl.zjsysxhb.ZjsySxhbService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * ��ѵ����-������Ϣά��-��ѵ��Ϣά��
 * @author yeyipin
 * @since 2012.10.10
 */
public class JxglJxxxwhAction extends SuperAction{
	
	private static final String RCSWRCXW = "rcswqjgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;

	private static final String url = "jxgl_jcxxwh_jxxxwh.do";
	
	/**
	 * ��ѵ��Ϣ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jxxxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("jxgl_jcxxwh_jxxxwh.do");
		// ----------------����PATH end-----------------------
		jxglJxxxwhService.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("jxxxCx");
		}
	}
	
	@SystemAuth(url = url)
	public ActionForward jzdjwh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		List<HashMap<String,String>> jxnjList = jxglJxxxwhService.getJzdj();
		request.setAttribute("cjnjList", jxnjList);
		String path = "jxgl_jzdjwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jzdjwh");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveJzdj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		String params = request.getParameter("params");
		//�Ƚ�ȡ���򹳵��ó�0��Ȼ��Ѵ򹳵��ó�1
		String sql1 = "update xg_jxgl_new_jxjzdjb set sfqy=0";
		String sql2 = "update xg_jxgl_new_jxjzdjb set sfqy=1 where dm in ( " + params + ")" ;
		//�����е��Ƿ�Ҷ�ӽڵ���Ϊ0��Ȼ����ͼ���ѡ�еĽڵ���ΪҶ�ӽڵ�1
		String sql3 = "update xg_jxgl_new_jxjzdjb set sfyzjd=0";
		String sql4 = "update xg_jxgl_new_jxjzdjb set sfyzjd=1 where dm =(select max(dm) from xg_jxgl_new_jxjzdjb where sfqy=1) ";
		
		String[] sql = {sql1,sql3,sql2};
		int[] k = jxglJxxxwhService.serv_batchUpdateJzdj(sql);
		
		boolean result = jxglJxxxwhService.serv_updateJzdj(sql4);
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * ��ѵ������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jxmdCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		String cxqk = request.getParameter("cxqk");
		if("cx".equalsIgnoreCase(cxqk)){
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_cxqk(new String[]{"��ѵ"});
			searchModel.setPath("jxgl_jxxxwh.do?method=jxmdCx");
			request.setAttribute("searchTj", searchModel);
		}else if("hx".equalsIgnoreCase(cxqk)){
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_cxqk(new String[]{"��ѵ"});
			searchModel.setPath("jxgl_jxxxwh.do?method=jxmdCx");
			request.setAttribute("searchTj", searchModel);
		}
		request.setAttribute("pkValue", pkValue);
		model.setJxid(pkValue);
		HashMap<String,String> rs = jxglJxxxwhService.getJxxx(model);
		List<HashMap<String,String>> jxnjList = jxglJxxxwhService.getCjnj();
		request.setAttribute("cjnjList", jxnjList);
		request.setAttribute("rs", rs);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		request.setAttribute("path", "jxgl_jcxxwh_jxxxwh.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setPath("jxgl_jxxxwh.do?method=jxmdCx");
		// ----------------����PATH end-----------------------
		jxglJxxxwhService.setRequestValue(rForm, user, request);
		return mapping.findForward("jxmdCx");
		
	}
	
	
	public ActionForward jxxxXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		   String pkValue = request.getParameter("pkValue");
		   request.setAttribute("pkValue", pkValue);
		   return mapping.findForward("jxmdXg");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JxglJxxxwhForm model = (JxglJxxxwhForm) form;
		JxglJxxxwhService service = new JxglJxxxwhService();
        
		model.setJxid(request.getParameter("pkValue"));
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAlljxmdCx(model, request);

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
	
	/**
	 * 
	 * @����: ��ѵ����ѵ��ȡ����ѵ���򼰱���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-8-29 ����05:55:59
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
	public ActionForward jxxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxxxwhForm myForm = (JxglJxxxwhForm) form;
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			JxglJxxxwhDao jxDao = new JxglJxxxwhDao();
			myForm.setJxid(jxglJxxxwhService.unicode2Gbk(myForm.getJxid()));
			myForm.setLy(jxglJxxxwhService.unicode2Gbk(myForm.getLy()));
			myForm.setFj(jxglJxxxwhService.unicode2Gbk(myForm.getFj()));
			boolean result = false;
			if("hx".equalsIgnoreCase(myForm.getCxqk()) || "mx".equalsIgnoreCase(myForm.getCxqk())) {
				result = jxDao.upJxxx(myForm);
			}else {
				jxDao.scJxxx(myForm);
				result = jxDao.insqxJxxx(myForm);
			}
			
			String messageKey = result ? "����ɹ���" : "����ʧ�ܣ�";
			response.getWriter().print(messageKey);
			return null;
		}
		
		// ѧ��������Ϣ
		String path = "jxgl_jxxxwh.do?method=jxxx";
		request.setAttribute("path", path);
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		JxglJxxxwhService  jxxxwhService = new JxglJxxxwhService();
		HashMap<String,String> jxxxMap = jxxxwhService.getJxxxwhModel();
		request.setAttribute("jxxxMap", StringUtils.formatData(jxxxMap));
		request.setAttribute("cxz", myForm.getCxz());
		
		return mapping.findForward("jxxx");
	}
	
	/**
	 * 
	 * @����: �鿴
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-8-30 ����10:30:10
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
	public ActionForward viewJxxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JxglJxxxwhForm myForm = (JxglJxxxwhForm) form;
		JxglJxxxwhService jxglJxxxwhService = new JxglJxxxwhService();
		// ѧ����Ϣ
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		JxglJxxxwhService  jxxxwhService = new JxglJxxxwhService();
		HashMap<String,String> jxxxMap = jxxxwhService.getJxxxwhModel();
		request.setAttribute("jxxxMap", StringUtils.formatData(jxxxMap));
		HashMap<String,String> rs = jxglJxxxwhService.getJxxxView(myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("cxz", myForm.getCxqk());
		return mapping.findForward("viewJxxx");
	}
	
	/**
	 * 
	 * @����: ��ѵ���ӡ���������ܣ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-2 ����01:10:41
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
	public ActionForward printHx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JxglJxxxwhForm myForm = (JxglJxxxwhForm) form;
		File wordFile = getHxbWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * 
	 * @����: ��仺ѵֵ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-2 ����01:15:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getHxbWord(JxglJxxxwhForm myForm,HttpServletRequest request) throws Exception{
		
		JxglJxxxwhDao jxDao = new JxglJxxxwhDao();
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsxxMap = jxDao.getJxxh(myForm);
		String xh = xsxxMap.get("xh");
		User user = getUser(request);
		myForm.setUser(user);
		myForm.setXh(xh);
		HashMap<String, String> xsxxMapLy = jxDao.getJxly(myForm);
		String ly = xsxxMapLy.get("ly");
		data.put("ly", ly);
		//������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		data.put("rs", xsjbxx);
			
		return FreeMarkerUtil.getWordFile(data,"classpath://templates//jxgl","hxb_14073.xml",
				xh+"-"+xsjbxx.get("xm")+"��ѵ��");	
	}
	
	/**
	 * 
	 * @����: ������ѵ���ӡ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-2 ����01:16:04
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
	public ActionForward getHxbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JxglJxxxwhForm myForm = (JxglJxxxwhForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setPkValue(values[i]);
				File file = getHxbWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			
			for(int j=0;j<files.size();j++){
				files.get(j).delete();
			}
		}
		return null;
		
	}
	
	
	/**
	 * 
	 * @����: ��ѵ���ӡ���������ܣ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-2 ����01:10:41
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
	public ActionForward printMx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JxglJxxxwhForm myForm = (JxglJxxxwhForm) form;
		File wordFile = getMxbWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * 
	 * @����: �����ѵֵ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-2 ����01:15:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getMxbWord(JxglJxxxwhForm myForm,HttpServletRequest request) throws Exception{
		
		JxglJxxxwhDao jxDao = new JxglJxxxwhDao();
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsxxMap = jxDao.getJxxh(myForm);
		String xh = xsxxMap.get("xh");
		User user = getUser(request);
		myForm.setUser(user);
		myForm.setXh(xh);
		HashMap<String, String> xsxxMapLy = jxDao.getJxly(myForm);
		String ly = xsxxMapLy.get("ly");
		data.put("ly", ly);
		//������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		data.put("rs", xsjbxx);
			
		return FreeMarkerUtil.getWordFile(data,"classpath://templates//jxgl","mxb_14073.xml",
				xh+"-"+xsjbxx.get("xm")+"��ѵ��");	
	}
	
	/**
	 * 
	 * @����: ������ѵ���ӡ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-2 ����01:16:04
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
	public ActionForward getMxbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JxglJxxxwhForm myForm = (JxglJxxxwhForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setPkValue(values[i]);
				File file = getMxbWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			
			for(int j=0;j<files.size();j++){
				files.get(j).delete();
			}
		}
		return null;
		
	}
	
	
}
