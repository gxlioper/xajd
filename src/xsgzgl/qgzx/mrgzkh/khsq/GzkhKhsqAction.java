/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-6 ����02:42:06 
 */
package xsgzgl.qgzx.mrgzkh.khsq;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import xgxt.utils.date.DateUtils;
import xsgzgl.qgzx.mrgzkh.jcsz.GzkhJcszService;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshForm;
import xsgzgl.qgzx.mrgzkh.khsh.GzkhKhshService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ÿ�չ������˹���ģ��
 * @�๦������: ÿ�չ�������-��������
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-6 ����02:42:06
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class GzkhKhsqAction extends SuperAction<GzkhKhsqForm, GzkhKhsqService> {
	private final String CJFF="cjff";
	private GzkhKhsqService service = new GzkhKhsqService();
	
	private static final String url = "qgzx_mrgzkh_khsq.do";

	/**
	 * 
	 * @����:ÿ�չ������������б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-6 ����03:04:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward getKhsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhsqForm model = (GzkhKhsqForm) form;
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
		GzkhJcszService  jcszService = new GzkhJcszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		String path = "qgzx_mrgzkh_khsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("khsqList");
	}
	/**
	 * 
	 * @����:ÿ�չ���������������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-6 ����04:06:41
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
	public ActionForward addKhsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhsqForm model = (GzkhKhsqForm) form;
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
		service.setXsGwxx(request,model);
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJFF);
		List<HashMap<String, String>> gzsjList = new OptionUtil().getOptions(OptionUtil.GZKH_SJXZ);
		request.setAttribute("gzsjList", gzsjList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("rq", DateUtils.getCurrDate());
		String path = "mrgzkhKhsq.do?method=addKhsq";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("khsq");
	}
	/**
	 * 
	 * @����:ÿ�չ������������޸�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-6 ����04:07:06
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
	public ActionForward editKhsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhsqForm myForm = (GzkhKhsqForm) form;
		GzkhKhsqForm model = service.getModel(myForm);
		if(null!=model){
			service.setXsGwxx(request,model);
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
		request.setAttribute("gzsjList", gzsjList);
		request.setAttribute("jbxxList", jbxxList);
		String path = "mrgzkhKhsq.do?method=editKhsq";
		request.setAttribute("path", path);
		GzkhJcszService  jcszService = new GzkhJcszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		return mapping.findForward("editKhsq");
	}
	/**
	 * 
	 * @����:��������鿴
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����11:32:02
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
	public ActionForward khsqView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhsqForm model = (GzkhKhsqForm) form;
		GzkhKhshService khShservice = new GzkhKhshService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		// ����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		GzkhKhshForm shForm = new GzkhKhshForm();
		shForm.setSqid(model.getSqid());
		request.setAttribute("rs", StringUtils.formatData(khShservice.getKhshInfo(shForm)));
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("shzt", model.getShzt());
		return mapping.findForward("viewKhsq");

	}
	/**
	 * 
	 * @����:�������뱣��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����10:18:49
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
	@SystemLog(description = "�����ڹ���ѧ-�ڹ�ÿ�չ������˹���-ÿ�չ���������д-����-XH:{xh},YRDW:{yrdw},GS:{gs},GWDM:{gwdm},GZDD:{gzdd},GZRQ:{gzrq},gzkssj:{gzkssj},GZJSSJ:{gzjssj},GZNR:{gznr}")
	public ActionForward saveKhsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhsqForm model = (GzkhKhsqForm) form;
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		if (service.isHaveSqJl(model,"add")) {// ������
			String message = MessageUtil.getText(MessageKey.QGZX_MRGZKH_REPEAT,model.getGzrq());;
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		String message = service.checkGs(model.getXh(),model.getXn(),model.getGs(),model.getGzrq(),model.getCjbz(),model.getSqid(),model.getGwdm());
		if(!"true".equals(message)){
			 response.getWriter().print(getJsonMessage(message));
			 return null;
		}
		boolean result = service.saveKhsq(model, getUser(request));
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:�����޸ı���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����02:33:27
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
	@SystemLog(description = "�����ڹ���ѧ-�ڹ�ÿ�չ������˹���-ÿ�չ���������д-����-XH:{xh},YRDW:{yrdw},GS:{gs},GWDM:{gwdm},GZDD:{gzdd},GZRQ:{gzrq},gzkssj:{gzkssj},GZJSSJ:{gzjssj},GZNR:{gznr},SQID:{sqid}")
	public ActionForward saveEditKhsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhsqForm model = (GzkhKhsqForm) form;
		String message=null;
		if (!"tj".equalsIgnoreCase(model.getType())&&service.isHaveSqJl(model,"update")) {// ������
			message = MessageUtil.getText(MessageKey.QGZX_MRGZKH_REPEAT,model.getGzrq());;
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
			
		if(!"tj".equalsIgnoreCase(model.getType())&&!"true".equals(service.checkGs(model.getXh(),model.getXn(),model.getGs(),model.getGzrq(),model.getCjbz(),model.getSqid(),model.getGwdm()))){
			message= service.checkGs(model.getXh(),model.getXn(),model.getGs(),model.getGzrq(),model.getCjbz(),model.getSqid(),model.getGwdm());
			 response.getWriter().print(getJsonMessage(message));
			 return null;
		}
		boolean result = service.saveEditKhsq(request,model);
		String messageKey = null;
		if("tj".equalsIgnoreCase(model.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}else {
			messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:ɾ������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����02:35:38
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
	@SystemLog(description = "�����ڹ���ѧ-�ڹ�ÿ�չ������˹���-ÿ�չ���������д-ɾ��VALUES:{values}")
	public ActionForward delKhsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// ɾ��
			int num = service.runDelete(values.split(","));
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", num + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 
	 * @����:��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����03:03:21
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
	public ActionForward cancelKhsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			GzkhKhsqForm model = new GzkhKhsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:���ظ�λ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����10:47:51
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
	public ActionForward getGwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzkhKhsqForm model = (GzkhKhsqForm)form;
		List<HashMap<String, String>> list = service.getGwxx(model);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	/**
	 * 
	 * @����:��ȡ�ڸ�ѧ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����10:14:34
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
	public ActionForward showGzkhStudents(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GzkhKhsqForm model = (GzkhKhsqForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getGzkhStuList(
				model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	/**
	 * 
	 * @����:���ݵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-9 ����11:18:26
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
		GzkhKhsqForm model = (GzkhKhsqForm) form;

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

}
