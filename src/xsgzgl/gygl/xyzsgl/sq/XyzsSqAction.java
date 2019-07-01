package xsgzgl.gygl.xyzsgl.sq;

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
import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszService;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.jg.XyzsglService;
import xsgzgl.qgzx.mrgzkh.jcsz.GzkhJcszService;
import xsgzgl.qgzx.mrgzkh.khsq.GzkhKhsqForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

public class XyzsSqAction extends SuperAction<XyzsSqForm, XyzsSqService> {
	private final String XYZSSQ="xyzsjg";
	private XyzsSqService service = new  XyzsSqService();
	
	private static final String url = "gygl_xyzssq.do";
	
	/**
	 * У��ס�������ʼ�������Լ���ѯ
	 */
	@SystemAuth(url = url)
	public ActionForward getXyzsSqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsSqForm model = (XyzsSqForm) form;
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
		XyzsJcszService jcszService = new XyzsJcszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		String path = "gygl_xyzssq.do";
		request.setAttribute("path", path);
		model.setXh(user.getUserName());
		boolean isExist = service.checkExistForSave(model);
	    XyzsglForm form1 = new XyzsglForm();
		form1.setXh(model.getXh());
		XyzsglDao gldao = new XyzsglDao();
		boolean isExist1 = gldao.checkExistForSave(form1);
		String cfbz = "0";
		if(isExist || isExist1 ){
			cfbz = "1";
		}
		request.setAttribute("cfbz", cfbz);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXyzsSqList");
	}
	
	/**
	 * У��ס�޽������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsSqForm model = (XyzsSqForm) form;
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
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XYZSSQ);
		request.setAttribute("jbxxList", jbxxList);
		String path = "gygl_xyzssqgl.do?method=add";
		request.setAttribute("path", path);
		List<HashMap<String, String>> zwjzyy = service.getZyjzxx(model);
		request.setAttribute("zwjzyy", zwjzyy);
		List<HashMap<String, String>> xl = service.getXl(model);
		request.setAttribute("xl", xl);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xxdm", Base.xxdm);
		//������Ϣ����
		return mapping.findForward("add");
	}
	
	/**
	 * ס�޽������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʹ�Ԣ����-У��ס�޹���-У��ס������-���ӻ��޸ı���XH��{xh},SQBH:{sqbh},SQSJSTART:{sqsjstart},SQSJEND:{sqsjend},XL:{xl},LXDH:{lxdh},PARENTSLXDY:{parentslxdy},XXDZ:{xxdz},ZWJZYY:{zwjzyy}")
	public ActionForward saveZsjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsSqForm model = (XyzsSqForm) form;
		boolean result = false;
		String message=null;
	    User user = getUser(request);
	    XyzsglForm form1 = new XyzsglForm();
		form1.setXh(model.getXh());
		XyzsglDao gldao = new XyzsglDao();
		boolean isExist1 = gldao.checkExistForSave(form1);
		if(model.getType().equals("save")||model.getType().equals("submit")){
			// �жϵ�ǰѧ���Ƿ���У��ס�޽��
			boolean isExist = service.checkExistForSave(model);
			if (isExist || isExist1) {
				message = MessageUtil.getText(MessageKey.GYGL_XYZS_REPEAT,model.getXh());;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			result = service.saveZsjg(model, user);
		}else if(model.getType().equals("update")||model.getType().equals("updatesubmit")){
			if (isExist1) {
				message = MessageUtil.getText(MessageKey.GYGL_XYZS_REPEAT,model.getXh());;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			result = service.saveZsjgUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * ס������ɾ��
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʹ�Ԣ����-У��ס�޹���-У��ס������-ɾ��SQBH:{values}")
	public ActionForward delZsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			XyzsSqForm myForm = new XyzsSqForm();
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
	 * У��ס������鿴
	 */
	@SystemAuth(url = url)
	public ActionForward ZsjgView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsSqForm myForm = (XyzsSqForm) form;
		XyzsSqForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XYZSSQ);
		HashMap<String, String> xl = service.getXlCk(model);
		HashMap<String, String> xyjzyy = service.getXyZsyy(model);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("zsjgxx", StringUtils.formatData(model));
		request.setAttribute("xlxx", xl);
		request.setAttribute("xyjzyy", StringUtils.formatData(xyjzyy));
		return mapping.findForward("view");
	}
	
	/**
	 * У��ס�޽���޸�
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editZsjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsSqForm myForm = (XyzsSqForm) form;
		XyzsSqForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XYZSSQ);
		List<HashMap<String, String>> zwjzyy = service.getZyjzxx(model);
		request.setAttribute("zwjzyy", zwjzyy);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("zsjgxx", StringUtils.formatData(model));
		List<HashMap<String, String>> xl = service.getXl(model);
		request.setAttribute("xl", xl);
		String path = "gygl_xyzssqgl.do?method=editZsjg";
		request.setAttribute("path", path);
		XyzsJcszService jcszService = new XyzsJcszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("editZssq");
	}
	
	//�ύ
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XyzsSqForm myForm = (XyzsSqForm) form;
		String value = request.getParameter("values");
		myForm.setSqbh(value);
		XyzsSqForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//����
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelZssq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqbh = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqbh, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			XyzsSqForm model = new XyzsSqForm();
			model.setSqbh(sqbh);
			model.setSplc(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqbh)) > 0) {
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
	 * ס�޽������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyzsSqForm model = (XyzsSqForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ
		
		//��Ϊѧ�����ƺ������סԭ��ֻ�ܻ�ȡ����ֵ���������ѭ������setֵ
		for (HashMap<String, String> hashMap : resultList) {
		  String sqbh = hashMap.get("sqbh");
		  model.setSqbh(sqbh);
		  HashMap<String, String> zwjzMap= service.getXyZsyy(model);
		  hashMap.put("zwjzyy",zwjzMap.get("mc"));
		  HashMap<String, String> xlMap = service.getXlCk(model);
		  hashMap.put("xl",xlMap.get("xlmc"));
		  String shztmc = service.getShztMc(model);
		  hashMap.put("shzt",shztmc);
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

}
