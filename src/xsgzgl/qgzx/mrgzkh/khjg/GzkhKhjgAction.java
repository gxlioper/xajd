package xsgzgl.qgzx.mrgzkh.khjg;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

public class GzkhKhjgAction extends SuperAction<GzkhKhjgForm, GzkhKhjgService> {
	private final String CJFF="cjff";
	private GzkhKhjgService service = new GzkhKhjgService();
	
	private static final String url = "qgzx_mrgzkh_khjg.do";

	/**
	 * ÿ�չ������˽���б�
	 */
	@SystemAuth(url = url)
	public ActionForward getKhjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhjgForm model = (GzkhKhjgForm) form;
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
		String path = "qgzx_mrgzkh_khjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("khjgList");
	}
	/**
	 * ÿ�չ������˽������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addKhjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhjgForm model = (GzkhKhjgForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJFF);
		List<HashMap<String, String>> gzsjList = new OptionUtil().getOptions(OptionUtil.GZKH_SJXZ);
		List<HashMap<String,String>> yrdwList = service.getYrbm(model);
		if(yrdwList.size() > 0){
			model.setYrdw(yrdwList.get(0).get("bmdm"));
		}
		List<HashMap<String,String>> gwList = service.getGwxx(model);
		request.setAttribute("gwList", gwList);
		request.setAttribute("yrdwList", yrdwList);
		request.setAttribute("gzsjList", gzsjList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		String path = "mrgzkhKhjg.do?method=addKhjg";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("khjg");
	}
	/**
	 * ���˽���鿴
	 */
	@SystemAuth(url = url)
	public ActionForward khjgView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhjgForm myForm = (GzkhKhjgForm) form;
		GzkhKhjgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("gzkhKhjgXx", StringUtils.formatData(model));
		return mapping.findForward("viewKhjg");
	}
	/**
	 * ���˽������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-�ڹ�ÿ�չ������˹���-ÿ�չ������˽��-����XH:{xh},YRDW:{yrdw},GS:{gs},GWDM:{gwdm},GZDD:{gzdd},GZRQ:{gzrq},GZKSSJ:{gzkssj},GZJSSJ:{gzjssj},GZNR:{gznr},ID:{id}")
	public ActionForward saveKhjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		GzkhKhjgForm model = (GzkhKhjgForm) form;
		boolean result = false;
		String message=null;
		// �жϵ�ǰ��λ�Ƿ�����д��¼
		boolean isExist = service.checkExistForSave(model);
		if (isExist) {
			message = MessageUtil.getText(MessageKey.QGZX_MRGZKH_REPEAT,model.getGzrq());;
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		if ("update".equals(model.getType())){
			if(!"true".equals(service.checkZjeAndGsJGUp(model.getXh(),model.getXn(),model.getGs(),model.getGzrq(),model.getCjbz(),model.getId(),model.getGwdm()))){
				message= service.checkZjeAndGsJGUp(model.getXh(),model.getXn(),model.getGs(),model.getGzrq(),model.getCjbz(),model.getId(),model.getGwdm());
				 response.getWriter().print(getJsonMessage(message));
				 return null;
			}
		}else {
			if(!"true".equals(service.checkZjeAndGsJG(model.getXh(),model.getXn(),model.getGs(),model.getGzrq(),model.getCjbz(),null,model.getGwdm()))){
				message= service.checkZjeAndGsJG(model.getXh(),model.getXn(),model.getGs(),model.getGzrq(),model.getCjbz(),null,model.getGwdm());
				 response.getWriter().print(getJsonMessage(message));
				 return null;
			}
		}
		result = service.saveKhjg(model, getUser(request));
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * ÿ�չ������˽���޸�
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editKhjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhjgForm myForm = (GzkhKhjgForm) form;
		GzkhKhjgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJFF);
		List<HashMap<String, String>> gzsjList = new OptionUtil().getOptions(OptionUtil.GZKH_SJXZ);
		List<HashMap<String,String>> yrdwList = service.getYrbm(model);
		List<HashMap<String,String>> gwList = service.getGwxx(model);
		request.setAttribute("gwList", gwList);
		request.setAttribute("yrdwList", yrdwList);
		request.setAttribute("gzsjList", gzsjList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("gzkhKhjgXx", StringUtils.formatData(model));
		String path = "mrgzkhKhjg.do?method=editKhjg";
		request.setAttribute("path", path);
		return mapping.findForward("editKhjg");
	}
	/**
	 * ÿ�չ������˽��ɾ��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ڹ���ѧ-�ڹ�ÿ�չ������˹���-ÿ�չ������˽��-ɾ��VALUES:{values}")
	public ActionForward delKhjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GzkhKhjgService service = new GzkhKhjgService();
		
		//���id
		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			// ���¼���н�� begin
			GzkhKhjgForm myForm = new GzkhKhjgForm();
			for (String id : ids) {
				myForm.setId(id);
				GzkhKhjgForm model = service.getModel(myForm);
				String[] gwxx = model.getGwdm().split(",");
				model.setGwdm(gwxx[0]);
				model.setXn(gwxx[1]);
				service.xsCjxxJs(model);
			}
			// ���¼���н�� end
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
	 * ÿ�չ������˽������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GzkhKhjgForm model = (GzkhKhjgForm) form;
		GzkhKhjgService service = new GzkhKhjgService();

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
