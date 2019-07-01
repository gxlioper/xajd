/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-6 ����02:21:14 
 */
package com.zfsoft.xgxt.xstgl.rtgl.rtsq;

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

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.utils.StringUtil;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgForm;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsztz.tzxmsq.XsXmSqForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ź���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����Դ [����:1206]
 * @ʱ�䣺 2015-8-6 ����02:21:14
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class RtsqAction extends SuperAction {
	DAO dao = DAO.getInstance();
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private final String RTSQ ="rtsq";
	RtsqService service = new RtsqService();
	private RtjgService rtjgservice = new RtjgService();
	
	private static final String url = "stgl_rtgl_rtsq.do";

	/**
	 * 
	 * @����:���������б�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-8-6 ����02:36:04
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
	public ActionForward getRtsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtsqForm model = (RtsqForm) form;
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		List<HashMap<String, String>> xnList = dao.getXnndList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("usertype", user.getUserType());
		request.setAttribute("path", "stgl_rtgl_rtsq.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xh", user.getUserName());
		return mapping.findForward("RtsqList");
		
	}

	

	/**
	 * 
	 * @����:�������뱣��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-8-6 ����11:34:04
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
	public ActionForward saveRtsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtsqForm model = (RtsqForm) form;
		boolean result = false;
		String message=null;
	    User user = getUser(request);
		String splc = service.getsplc(model);
		model.setSplc(splc);
 		if(model.getType().equals("save")||model.getType().equals("submit")){
 			if(service.checkExistForSave(model) == true){
 				response.getWriter().print(getJsonMessageByKey(MessageKey.CFTX));
				return null;
 			}
 		    model.setRtxn(Base.currXn);
 			model.setRtxq(Base.currXq);
			result = service.saveRtsq(model, user);
		}else if(model.getType().equals("update")||model.getType().equals("updatesubmit")){
			result = service.saveSqjgUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}


	/**
	 * 
	 * @����:�������볷��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-8-6 ����01:46:22
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
	public ActionForward cancelRtsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rtid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(rtid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			RtsqForm model = new RtsqForm();
			model.setRtid(rtid);
			model.setSplc(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(rtid)) > 0) {
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
	 * ����������Ϣ�鿴
	 */
	public ActionForward Rtsqck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtsqForm myForm = (RtsqForm) form;
		RtsqForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RTSQ);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String, String> stxx = service.getStxxMap(model);
		request.setAttribute("stxx", StringUtils.formatData(stxx));
		User user = getUser(request);
		request.setAttribute("usertype", user.getUserType());
	    HashMap<String, String> stcyxx = service.getStxxCyMap(model, user);
		request.setAttribute("stcyxx", stcyxx);
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		return mapping.findForward("viewRtsq");
	}
	
	//������Ϣɾ��
	public ActionForward delSqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
	 * ���Ž����Ϣ����
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RtsqForm model = (RtsqForm) form;

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
	
	//�ύ
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtsqForm myForm = (RtsqForm) form;
		String value = request.getParameter("values");
		myForm.setRtid(value);
		RtsqForm model = service.getModel(myForm);
		User user = getUser(request);
		String splc = service.getsplc(model);
		model.setSplc(splc);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	 /**
     * 
     * @����: ��������
     */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtsqForm model = (RtsqForm) form;
	    User user = getUser(request);
	    if(user.getUserType().equalsIgnoreCase("stu")){
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RTSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("currxn", Base.currXn);
		request.setAttribute("rylblist", rtjgservice.getRylbList());
		String path = "stglRtsq.do?method=add";
		request.setAttribute("path", path);
		request.setAttribute("usertype",user.getUserType());
		//������Ϣ����
		return mapping.findForward("addRtsq");
	}
	
	//���������޸�
	public ActionForward editRtsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtsqForm myForm = (RtsqForm) form;
		RtsqForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RTSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rylblist", rtjgservice.getRylbList());
		HashMap<String, String> stxx = service.getStxxMap(model);
		request.setAttribute("stxx", StringUtils.formatData(stxx));
		String path = "stglRtsq.do?method=editRtsq";
		request.setAttribute("path", path);
		return mapping.findForward("editRtsq");
	}
	
	//��������ѡ��������Ŀ����Ŀѡ��ҳ��
	public ActionForward getStxmList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		RtsqForm myForm = (RtsqForm) form;
		String xh= request.getParameter("xh");
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getStxmList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("xh", xh);
		String path = "stglRtsq.do?method=getStxmList";
		request.setAttribute("path", path);
		return mapping.findForward("getStxm");
	}
	
	//ѧ��ҳ���ѯ
	@SystemAuth(url = "stglRtsq.do?method=getStuRtsqList")
	public ActionForward getStuRtsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtsqForm model = (RtsqForm) form;
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			List<HashMap<String, String>> resultList = service.getStuRtsqList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		List<HashMap<String, String>> xnList = dao.getXnndList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("usertype", user.getUserType());
		request.setAttribute("path", "stglRtsq.do?method=getStuRtsqList");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xh", user.getUserName());
		return mapping.findForward("stuRtsqList");
		
	}


	public ActionForward getZdlsInfo(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);
		RtsqForm model = (RtsqForm) form;
		List<HashMap<String, String>> result = service.getZdlsInfo(model);
		JSONArray resultList = JSONArray.fromObject(result); // תΪjson��ʽ
		response.setContentType("text/html;charset=gbk"); // ajax���󷵻�����ת�룬�������������
		response.getWriter().print(resultList);
		return null;
	}
}
