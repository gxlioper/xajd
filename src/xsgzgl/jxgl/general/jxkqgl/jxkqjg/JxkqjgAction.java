/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-7 ����07:28:28 
 */  
package xsgzgl.jxgl.general.jxkqgl.jxkqjg;

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
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-7 ����07:28:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JxkqjgAction extends SuperAction<JxkqjgForm, JxkqjgService> {
	private static final String RCSWRCXW = "rcswrcxw";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private JxglJxxxwhService  jxxxwhService = new JxglJxxxwhService();
	
	private static final String url = "jxgl_jxkqgl_kqjggl.do";
	
	/**
	 * 
	 * @����:��ѵ�����б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-7 ����10:11:42
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
	public ActionForward jxkqjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		JxkqjgService service = new JxkqjgService();
		CommService cs = new CommService();
		JxkqjgForm myForm = (JxkqjgForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("jxgl_jxkqgl_kqjggl.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "jxgl_jxkqgl_kqjggl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jxkqjgList");
	}
	/**
	 * 
	 * @����:��ѵ���ڽ��ɾ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-7 ����11:32:48
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
	@SystemLog(description = "���ʾ�ѵ����-��ѵ���ڹ���-��ѵ���ڽ��-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxkqjgService service = new JxkqjgService();
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
	 * 
	 * @����:���ڽ������
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
	@SystemLog(description = "���ʾ�ѵ����-��ѵ���ڹ���-��ѵ���ڽ��-����XH:{xh},KQSJ:{kqsj},KQLB:{kqlb},KQLX:{kqlx}")
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxkqjgService service = new JxkqjgService();
		JxkqjgForm myForm = (JxkqjgForm) form;

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
		request.setAttribute("kqlxList", service.getKqlx());
		request.setAttribute("kqlbList", service.getKqlb());
		request.setAttribute("jxxxMap", jxxxwhService.getQyJxxx());
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		String path = "jxkqjg.do?method=add";
		request.setAttribute("path", path);
		return mapping.findForward("add");
	}
	/**
	 * ���ڽ���޸�
	 */
	 @SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʾ�ѵ����-��ѵ���ڹ���-��ѵ���ڽ��-�޸�KQID:{kqid},XH:{xh},KQSJ:{kqsj},KQLB:{kqlb},KQLX:{kqlx}")
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxkqjgService service = new JxkqjgService();
		JxkqjgForm myForm = (JxkqjgForm) form;

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
		JxkqjgForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

		request.setAttribute("kqlxList", service.getKqlx());
		request.setAttribute("kqlbList", service.getKqlb());
		request.setAttribute("jxxxMap", jxxxwhService.getQyJxxx());
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @����:���ڽ���鿴
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
		JxkqjgService service = new JxkqjgService();
		JxkqjgForm myForm = (JxkqjgForm) form;
		JxkqjgForm model = service.getModel(myForm);
		// ѧ����Ϣ
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("kqjg", StringUtils.formatData(model));
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
		JxkqjgForm model = (JxkqjgForm) form;
		// ���ݲ�ͬ��������� ȥ�Զ��嵼��
		JxkqjgService service = new JxkqjgService();
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
	 * @����:��ѵѧ���б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-8 ����04:44:04
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
	public ActionForward showJxStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JxkqjgService service = new JxkqjgService();
		JxkqjgForm model = (JxkqjgForm) form;
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getJxxsList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
	
	}
}
