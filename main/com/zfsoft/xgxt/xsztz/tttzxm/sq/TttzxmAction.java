/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-22 ����10:41:42 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.sq;

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
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

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
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsztz.tttzxm.comm.CommTtxmService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-7-22 ����10:41:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TttzxmAction extends SuperAction<TttzxmForm, TttzxmService> {
	TttzxmService service = new TttzxmService();
	/**
	 * 
	 * @����: ��ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-25 ����10:53:30
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
	public ActionForward getTtxmsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmForm model = (TttzxmForm) form;
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
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_ttxm_sq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 *
	 * @����: ������չ��Ŀ��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-25 ����10:54:07
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
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		request.setAttribute("usertype", user.getUserType());
		if("stu".equals(user.getUserType())){
			String xh = user.getUserName();
			CommTtxmService service = new CommTtxmService();
			//�ûضӳ���Ϣ
			request.setAttribute("xsmap", service.getDzxx(xh));
		}
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @����: ��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����03:01:44
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
	public ActionForward saveTtsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmForm model = (TttzxmForm) form;
		CommTtxmService commService = new CommTtxmService();
		boolean result = false;
	    User user = getUser(request);
	    String[] xhs = request.getParameterValues("xh");
	    model.setXhArr(xhs);
	    model.setSqr(user.getUserName());
		if(model.getType().equals("save")||model.getType().equals("submit")){
			//�ж��Ŷ������Ƿ��ظ�
			boolean isNotExist = commService.checkNameIsNotExists(model.getTdmc(),  model.getXmdm(), null, "qb");
			//�ظ�������ʾ
			if(!isNotExist){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SZTZ_NEW_TTXM_MCREPEAT));
				return null;
			}
			//�ж��Ŷ���Ա�Ƿ��Ѿ����������Ŀ
			String xhRepeatStr =  commService.checkIsNotExists(xhs,model.getXmdm(), null);
			String[] xhRepeatStrArr = new String[0];
			if(StringUtils.isNotNull(xhRepeatStr)){
				xhRepeatStrArr = xhRepeatStr.split(";");
			}
			//������ص�ѧ���ַ����������0��ֱ�ӷ���
			if (xhRepeatStrArr.length > 0) {
				StringBuilder message = new StringBuilder();
				for (int i = 0; i < xhRepeatStrArr.length; i++) {
					message.append("["+xhRepeatStrArr[i]+"]");
					if(i != xhRepeatStrArr.length-1){
						message.append(",");
					}
				}
				message.append("�����������Ŀ!");
				response.getWriter().print(getJsonMessage(message.toString()));
				return null;
			}
			result = service.saveTtsq(model, user);
		}else if(model.getType().equals("update")||model.getType().equals("updatesubmit")){
			//�ж��Ŷ������Ƿ��ظ�
			boolean isNotExist = commService.checkNameIsNotExists(model.getTdmc(),  model.getXmdm(), model.getTtsqid(), "qb");
			//�ظ�������ʾ
			if(!isNotExist){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SZTZ_NEW_TTXM_MCREPEAT));
				return null;
			}
			//�ж��Ŷ���Ա�Ƿ��Ѿ����������Ŀ
			String xhRepeatStr =  commService.checkIsNotExists(xhs,model.getXmdm(), model.getTtsqid());
			String[] xhRepeatStrArr = new String[0];
			if(StringUtils.isNotNull(xhRepeatStr)){
				xhRepeatStrArr = xhRepeatStr.split(";");
			}
			//������ص�ѧ���ַ����������0��ֱ�ӷ���
			if (xhRepeatStrArr.length > 0) {
				StringBuilder message = new StringBuilder();
				for (int i = 0; i < xhRepeatStrArr.length; i++) {
					message.append("["+xhRepeatStrArr[i]+"]");
					if(i != xhRepeatStrArr.length-1){
						message.append(",");
					}
				}
				message.append("�����������Ŀ!");
				response.getWriter().print(getJsonMessage(message.toString()));
				return null;
			}
			result = service.saveTtsqUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����04:30:10
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
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmForm myForm = (TttzxmForm) form;
		String value = request.getParameter("values");
		myForm.setTtsqid(value);
		TttzxmForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
    /**
     * 
     * @����: ����
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-7-26 ����04:39:59
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
	public ActionForward cancelZssq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ttsqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(ttsqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			TttzxmForm model = new TttzxmForm();
			model.setTtsqid(ttsqid);
			model.setSplc(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(ttsqid)) > 0) {
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
	 * @����:����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����04:40:18
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TttzxmForm model = (TttzxmForm) form;

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
	
	/**
	 * @����: �鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����04:41:09
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
	public ActionForward TtsqView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmForm myForm = (TttzxmForm) form;
		TttzxmForm model = service.getModel(myForm);
		User user = getUser(request);
		CommTtxmService commService = new CommTtxmService();
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
		}
		request.setAttribute("usertype", user.getUserType());
		//�ӳ���Ϣmap
		request.setAttribute("dzxxmap", commService.getDzxx(model.getDzxh()));
		//��Ա��Ϣlist
		request.setAttribute("dyzzlist", commService.getDyxxNotDz(model.getTtsqid(), model.getDzxh()));
		//��Ŀ��Ϣ
		request.setAttribute("xmxxmap", commService.getXmxxMap(model.getXmdm()));
		return mapping.findForward("view");
	}
	
	/**
	 * 
	 * @����: �޸�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����04:41:31
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
	public ActionForward editTtsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmForm myForm = (TttzxmForm) form;
		TttzxmForm model = service.getModel(myForm);
		CommTtxmService commService = new CommTtxmService();
		User user = getUser(request);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		request.setAttribute("usertype", user.getUserType());
		//�ӳ���Ϣmap
		request.setAttribute("dzxxmap", commService.getDzxx(model.getDzxh()));
		//��Ա��Ϣlist
		request.setAttribute("dyzzlist", commService.getDyxxNotDz(model.getTtsqid(), model.getDzxh()));
		//��Ŀ��Ϣ
		request.setAttribute("xmxxmap", commService.getXmxxMap(model.getXmdm()));
		return mapping.findForward("editTtsq");
	}
	
	/**
	 * 
	 * @����:ɾ����������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-27 ����04:10:12
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
	public ActionForward delTtsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		CommTtxmService commService = new CommTtxmService();
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			XyzsSqForm myForm = new XyzsSqForm();
			int num = service.runDelete(ids);
			boolean result = num > 0;
			result = commService.delTtcy(ids);
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	

}
