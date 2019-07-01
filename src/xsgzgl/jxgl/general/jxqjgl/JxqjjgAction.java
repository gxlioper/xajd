/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-6 ����09:52:08 
 */
package xsgzgl.jxgl.general.jxqjgl;

import java.io.File;
import java.util.ArrayList;
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
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.jxgl.general.jxxxwh.JxglJxxxwhService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjlx.QjlxService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-6 ����09:52:08
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JxqjjgAction extends SuperAction<JxqjjgForm, JxqjjgService> {
	private static final String RCSWRCXW = "rcswqjgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private JxglJxxxwhService  jxxxwhService = new JxglJxxxwhService();
	
	private static final String url = "jxgl_jxqjgl_qjjggl.do";
	
	/**
	 * 
	 * @����:��ѵ����б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-6 ����10:11:42
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
	public ActionForward jxqjjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		JxqjjgService service = new JxqjjgService();
		CommService cs = new CommService();
		JxqjjgForm myForm = (JxqjjgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("jxgl_jxqjgl_qjjggl.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "jxgl_jxqjgl_qjjggl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jxqjjgList");
	}
	/**
	 * 
	 * @����:��ѵ��ٽ��ɾ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-6 ����11:32:48
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
	@SystemLog(description = "���ʾ�ѵ����-��ѵ��ٹ���-��ѵ��ٽ��-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxqjjgService service = new JxqjjgService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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
	 * ��ٽ���޸�
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʾ�ѵ����-��ѵ��ٹ���-��ѵ��ٽ��-�޸�QJID:{qjid},XH:{xh},QJTS:{qjts},QJLX:{qjlx},QJKSSJ:{qjkssj},QJJSSJ:{qjjssj},QJSY:{qjsy}")
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxqjjgService service = new JxqjjgService();
		JxqjjgForm myForm = (JxqjjgForm) form;

		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}

		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		JxqjjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

		request.setAttribute("qjlxList", service.getQjlx());
		
		// ѧ��������Ϣ
		String path = "jxqjjg.do?method=update";
		request.setAttribute("path", path);

		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String,String> jxxxMap = jxxxwhService.getJxxxwhModel();
		request.setAttribute("jxxxMap", StringUtils.formatData(jxxxMap));
		
		String jzmc = service.getJzmc(service.getJzid(), myForm.getXh());
		if(!(jzmc == "")) {
			request.setAttribute("jzmc", jzmc);
		}else {
			request.setAttribute("jzmc", "δ����");
		}
		
		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("update");
	}
	/**
	 * 
	 * @����:��ٽ������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-7 ����09:59:32
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
	@SystemLog(description = "���ʾ�ѵ����-��ѵ��ٹ���-��ѵ��ٽ��-����XH:{xh},QJTS:{qjts},QJLX:{qjlx},QJKSSJ:{qjkssj},QJJSSJ:{qjjssj},QJSY:{qjsy}")
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxqjjgService service = new JxqjjgService();
		JxqjjgForm myForm = (JxqjjgForm) form;

		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("qjlxList", service.getQjlx());
		// get student detail
		// ѧ��������Ϣ
		String path = "jxqjjg.do?method=add";
		request.setAttribute("path", path);
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		// ѧ�� ѧ��
		HashMap<String,String> jxxxMap = jxxxwhService.getJxxxwhModel();
		request.setAttribute("jxxxMap", jxxxMap);
		
		String jzmc = service.getJzmc(service.getJzid(), myForm.getXh());
		if(!(jzmc == "")) {
			request.setAttribute("jzmc", jzmc);
		}else {
			request.setAttribute("jzmc", "δ����");
		}
		
		myForm.setXq(Base.currXq);
		myForm.setXqmc(Base.getDqxqmc());
		myForm.setXn(Base.currXn);
		return mapping.findForward("add");
	}
	/**
	 * 
	 * @����:��ٽ���鿴
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-7 ����09:59:49
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
	public ActionForward showView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxqjjgService service = new JxqjjgService();
		JxqjjgForm myForm = (JxqjjgForm) form;
		JxqjjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		// ѧ����Ϣ
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// �������
		QjlxService qjlx = new QjlxService();
		request.setAttribute("qjlxmc", qjlx.getModel(model.getQjlx()).getQjlxmc());
		
		String jzmc = service.getJzmc(service.getJzid(), myForm.getXh());
		if(!(jzmc == "")) {
			request.setAttribute("jzmc", jzmc);
		}else {
			request.setAttribute("jzmc", "δ����");
		}
		
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("show");
	}
	/**
	 * 
	 * @����:�������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-7 ����10:00:37
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxqjjgForm model = (JxqjjgForm) form;
		// ���ݲ�ͬ��������� ȥ�Զ��嵼��
		JxqjjgService service = new JxqjjgService();
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model, user);// ��ѯ�����м�¼������ҳ

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
	 * @����:��ٽ������ӡ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-7 ����10:00:55
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
	public ActionForward printQjjgb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxqjjgService service = new JxqjjgService();
		JxqjjgForm myForm = (JxqjjgForm) form;
		XsxxService xsxxService = new XsxxService();
		if (StringUtils.isNotNull(myForm.getQjid())) {
			List<File> files = new ArrayList<File>();
			String[] qjjgids = myForm.getQjid().split(",");
			for (String id : qjjgids) {
				myForm.setQjid(id);
				HashMap<String, String> qjjgMap = service.getQjjgForPrint(id);
				// ����ѧ��������Ϣ
				String jzmc = service.getJzmc1(qjjgMap.get("xh"));
				qjjgMap.put("jzmc", jzmc);
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(qjjgMap.get("xh"));
				File file = service.printForWord(xsjbxx, qjjgMap);
				files.add(file);
			}
			if (files != null && files.size() > 1) {
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			} else {
				FileUtil.outputWord(response, files.get(0));
			}
		}
		return null;
	}
	
	/*
	 * ���ٵ�
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printBjd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxqjjgService service = new JxqjjgService();
		JxqjjgForm myForm = (JxqjjgForm) form;
		XsxxService xsxxService = new XsxxService();
		if (StringUtils.isNotNull(myForm.getQjid())) {
			List<File> files = new ArrayList<File>();
			String[] qjjgids = myForm.getQjid().split(",");
			for (String id : qjjgids) {
				myForm.setQjid(id);
				HashMap<String, String> qjjgMap = service.getQjjgForPrint(id);
				// ����ѧ��������Ϣ
				String jzmc = service.getJzmc1(qjjgMap.get("xh"));
				qjjgMap.put("jzmc", jzmc);
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(qjjgMap.get("xh"));

				File file = service.printForWordBjd(xsjbxx, qjjgMap);
				files.add(file);
			}
			if (files != null && files.size() > 1) {
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			} else {
				FileUtil.outputWord(response, files.get(0));
			}
		}
		return null;
	}
	
	

}
