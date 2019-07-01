/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:03:38 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.Globals;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.dtjs.dtxxgl.DtxxglService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������Ϣ����ģ��
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:03:38
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class DtxxjgAction extends SuperAction {
	// �����ճ��������ճ���Ϊ�������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String RCSWRCXW = "dtxxXsxxpz";
	private static List<HashMap<String, String>> jbxxList = null;
	
	private static final String url = "dtxxjgbase.do";

	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
	}

	/**
	 * 
	 * @����:������Ϣ�б��ѯ��ʾ
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgService service = new DtxxjgService();
		CommService cs = new CommService();
		DtxxjgForm myForm = (DtxxjgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("dtxxjgbase.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "dtxxjgbase.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dtxxjglb");
	}

	/**
	 * 
	 * @����:����ɾ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgService service = new DtxxjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delete(values.split(","));
			if (null == mess || mess.length != 2) {
				String message = MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", mess[0]);
			map.put("nodel", mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 
	 * @����:ͬ��������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-31 ����11:14:12
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
	public ActionForward tbdtxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxglService dtxxglService = new DtxxglService();
		String message = dtxxglService.tbgxzzmm() ? "ͬ�����³ɹ���" : "ͬ������ʧ�ܣ�";
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}
	/**
	 * 
	 * @����: ������Ϣģ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgService service = new DtxxjgService();
		DtxxjgForm myForm = (DtxxjgForm) form;
		DtxxjgForm model = service.getModel(myForm);
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMoreForDtgl(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String message = service.save(myForm);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		List<HashMap<String, String>> jdlist = service.getGrJdxx(myForm.getXh());
		request.setAttribute("jdlist", jdlist);
		// ҳ�治�û�ȡsize ��ҳ��ѭ����0��ʼ��-1
		request.setAttribute("size", jdlist.size() - 1);
		// ѧ��������Ϣ
		String path = "dtxxjg.do?method=update";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("dtxxjgxg");
	}

	/**
	 * 
	 * @����:����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-17 ����10:49:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgService service = new DtxxjgService();
		DtxxjgForm myForm = (DtxxjgForm) form;

		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			String messageKey = service.save(myForm);
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		List<HashMap<String, String>> jdlist = service.getGrJdxx(myForm.getXh());
		request.setAttribute("jdlist", jdlist);
		// ҳ�治�û�ȡsize ��ҳ��ѭ����0��ʼ��-1
		request.setAttribute("size", jdlist.size() - 1);
		// ѧ��������Ϣ
		String path = "dtxxjg.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("dtxxjgzj");
	}

	/**
	 * 
	 * @����:��ʾ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-17 ����05:23:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url)
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgService service = new DtxxjgService();
		DtxxjgForm myForm = (DtxxjgForm) form;
		DtxxjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		// ѧ����Ϣ
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		List<HashMap<String, String>> jdlist = service.getGrJdxx(myForm.getXh());
		request.setAttribute("jdlist", jdlist);
		// ҳ�治�û�ȡsize ��ҳ��ѭ����0��ʼ��-1
		request.setAttribute("size", jdlist.size() - 1);
		// ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("dtxxjgck");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgForm model = (DtxxjgForm) form;
		// ���ݲ�ͬ��������� ȥ�Զ��嵼��
		DtxxjgService service = new DtxxjgService();
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList=null;
		String dcclbh = request.getParameter("dcclbh");
		if("13871".equals(Base.xxdm)){
			resultList = service.getExportList_13871(model,user);
		}else if("10698".equals(Base.xxdm)){
		    //�������󵼳�4��ģ��
		    if(dcclbh.equals("dtxxjg_jjfz.do")){
                resultList = service.getJjfzList(model,user);
            }else if(dcclbh.equals("dtxxjg_fzdx.do")){
                resultList = service.getFzdxList(model,user);
            }else if(dcclbh.equals("dtxxjg_ybdy.do")){
                resultList = service.getYbdyList(model,user);
            }else if(dcclbh.equals("dtxxjg_zsdy.do")){
                resultList = service.getZsdyList(model,user);
            }
        }
		else{
			resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ
		}
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
	 * @����:�༭��鿴
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-28 ����12:39:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	
	public ActionForward editorView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgForm model = (DtxxjgForm) form;
		ShlcpzService ss=new ShlcpzService();
		boolean ishave=ss.isHaveSPlc(model.getJddm());
		if("13022".equals(Base.xxdm)&&("06".equals(model.getJddm())||"10".equals(model.getJddm()))){
			request.setAttribute("dxjy", "true");
		}
		
		//�㽭��ҵ���Ի�
		if(Globals.XXDM_ZJSYZYXY.equals(Base.xxdm)
				&&"05".equals(model.getJddm())){
			request.setAttribute("zjsydxjy", "true");
		}
		
		request.setAttribute("ishave",ishave);
		request.setAttribute("data",StringUtils.formatData(model));
		if (model.getType().equals("view")) {
			return mapping.findForward("view");
		}
		return mapping.findForward("edit");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward mcexport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtxxjgForm model = (DtxxjgForm) form;
		// ���ݲ�ͬ��������� ȥ�Զ��嵼��
		DtxxjgService service = new DtxxjgService();
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		File file = service.getExportFile(model, user);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		file.delete();
		return null;
	}
	
	/**
	 * 
	 * @����: ������Ͽҽ��ר-Ԥ��չ����������ܱ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-24 ����09:14:55
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
	public ActionForward getYsqs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DtxxjgService service = new DtxxjgService();
		Map<String,Object> data = new HashMap<String,Object>();
		
		String xymc = request.getParameter("value");
		List<HashMap<String,String>> resultList = service.getDtxxYsfzList(xymc);
		data.put("dtxsxxList",resultList);
		data.put("xymc",xymc);
		// ��ǰ������ʱ����
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(new java.util.Date());
        data.put("y", time.trim().substring(0, 4));
        data.put("m", time.trim().substring(5, 7));
        data.put("d", time.trim().substring(8, 10));
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//dtxx", "ysqs_14008.xml", "Ԥ��չ����������ܱ�" );
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @����: ������Ͽҽ��ר-Ԥ����Ա
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-24 ����02:23:59
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
	public ActionForward getXsyb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DtxxjgService service = new DtxxjgService();
		Map<String,Object> data = new HashMap<String,Object>();
		
		String xymc = request.getParameter("value");
		List<HashMap<String,String>> resultList = service.getDtxxYbdyList(xymc);
		data.put("dtxsxxList",resultList);
		data.put("xymc",xymc);
		// ��ǰ������ʱ����
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(new java.util.Date());
        data.put("y", time.trim().substring(0, 4));
        data.put("m", time.trim().substring(5, 7));
        data.put("d", time.trim().substring(8, 10));
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//dtxx", "xsyb_14008.xml", "����Ԥ����Ա������ܱ�" );
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @����: ������Ͽҽ��ר-Ԥ����Աת������ʽ��Ա��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-24 ����02:23:59
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
	public ActionForward getYbzz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		DtxxjgService service = new DtxxjgService();
		Map<String,Object> data = new HashMap<String,Object>();
		
		String xymc = request.getParameter("value");
		List<HashMap<String,String>> resultList = service.getDtxxZsdyList(xymc);
		data.put("dtxsxxList",resultList);
		data.put("xymc",xymc);
		// ��ǰ������ʱ����
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(new java.util.Date());
        data.put("y", time.trim().substring(0, 4));
        data.put("m", time.trim().substring(5, 7));
        data.put("d", time.trim().substring(8, 10));
		File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//dtxx", "ybzz_14008.xml", "Ԥ����Աת��������ܱ�" );
		FileUtil.outputWord(response, wordFile);
		return null;
	}
}
