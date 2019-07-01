package com.zfsoft.xgxt.rcsw.ylbx.ylbxsq;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.utils.String.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.ylbx.ylbxjcsz.YlbxjcszForm;
import com.zfsoft.xgxt.rcsw.ylbx.ylbxjcsz.YlbxjcszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class YlbxsqAction extends SuperAction {
	
	//�����ճ�������ҽ�Ʊ��ճ������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String RCSWYLBX = "rcswylbx";
	private static List<HashMap<String, String>> jbxxList = null;
	
	private static final String url = "rcsw_ylbx_ylbxsq.do";

	/**
	 * @����:��ѯҽ�Ʊ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward ylbxsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxsqForm model = (YlbxsqForm) form;
		YlbxsqService service = new YlbxsqService();
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
		YlbxjcszService ylbxjcszService = new YlbxjcszService();
		YlbxjcszForm jcszModel = ylbxjcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		SearchModel searchModel=new SearchModel();
		if("14073".equals(Base.xxdm)){
			searchModel.setSearch_tj_nd(new String[] {Base.currNd});
		}else{
			searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		}
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_ylbx_ylbxsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ylbxsqManage");
	}

	/**
	 * @����:����ҽ�Ʊ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ճ�����-ҽ�Ʊ���-ҽ�Ʊ�������-����XH:{xh},XN:{xn},ZD12:{zd12},ZD6:{zd6}")
	public ActionForward addYlbxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxsqForm model = (YlbxsqForm) form;
		YlbxsqService service = new YlbxsqService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(model.getType())
				|| SUBMIT.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			boolean isExist = false;
			model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			isExist = service.isExist(model);
			if (!isExist) {
				super.resetToken(request);
				// ���
				String sqid = UniqID.getInstance().getUniqIDHash();
				model.setSqid(sqid);
				boolean result = service.saveYlbxsq(model);
				String messageKey = "";
				if (SAVE.equalsIgnoreCase(model.getType())) {
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				} else {
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				String message = MessageUtil.getText(MessageKey.RCSW_YLBX_SQ_REPEAT) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		String path = "rcsw_ylbx_ylbxsqgl.do?method=addYlbxsq";
		request.setAttribute("path", path);

		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		if("14073".equals(Base.xxdm)){
			request.setAttribute("dqnd", Base.currNd);
		}else{
			request.setAttribute("dqxn", Base.currXn);
		}
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("addYlbxsq");
	}

	/**
	 * @����:�޸�ҽ�Ʊ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ճ�����-ҽ�Ʊ���-ҽ�Ʊ�������-�޸�SQID:{sqid},XH:{xh},XN:{xn},ZD12:{zd12},ZD6:{zd6}")
	public ActionForward updateYlbxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxsqForm model = (YlbxsqForm) form;
		YlbxsqService service = new YlbxsqService();
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(model.getType())
				|| SUBMIT.equalsIgnoreCase(model.getType())) {
			boolean isExist = false;
			model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			isExist = service.isExist(model);
			if (!isExist) {
				boolean result = service.updateYlbxsq(model);
				String messageKey = "";
				if (SAVE.equalsIgnoreCase(model.getType())) {
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				} else {
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
				}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				String message = MessageUtil.getText(MessageKey.RCSW_YLBX_SQ_REPEAT) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		String path = "rcsw_ylbx_ylbxsqgl.do?method=updateYlbxsq";
		request.setAttribute("path", path);

		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("updateYlbxsq");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward ylbxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("sqid");
		YlbxsqService service = new YlbxsqService();
		Map<String, String> ylbxXxMap = service.viewOneYlbxsq(sqid);
		ylbxXxMap = (Map<String, String>) StringUtils.formatData(ylbxXxMap);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONObject.fromMap(ylbxXxMap));
		return null;
	}

	/**
	 * @����:ɾ��ҽ�Ʊ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ճ�����-ҽ�Ʊ���-ҽ�Ʊ�������-ɾ��VALUES:{values}")
	public ActionForward delYlbxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxsqService service = new YlbxsqService();
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
	 * @����:�ύҽ�Ʊ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitYlbxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxsqForm model = (YlbxsqForm) form;
		String sqid = request.getParameter("values");
		model.setSqid(sqid);
		YlbxsqService service = new YlbxsqService();
		boolean result = service.submitYlbxsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @����:����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelYlbxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxsqService service = new YlbxsqService();
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			YlbxsqForm model = new YlbxsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.cancelYlbxsq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @����:�鿴ҽ�Ʊ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewYlbxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxsqForm model = (YlbxsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		String path = "rcsw_ylbx_ylbxsqgl.do?method=viewYlbxsq";
		request.setAttribute("path", path);

		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWYLBX);
		//ѧ��������Ϣ
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewYlbxsq");
	}

	/**
	 * @����:����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxsqForm model = (YlbxsqForm) form;
		YlbxsqService service = new YlbxsqService();
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getExportAllList(model, user);// ��ѯ�����м�¼������ҳ
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
	 * @����:ѧ����Ƭ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getZpZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxsqService service = new YlbxsqService();
		String value = request.getParameter("value");
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			for (int i = 0 , n = values.length ; i < n ; i++){
				HashMap<String , String> map = service.getZpDyxx(values[i]);
				list.add(map);
			}
		}
		service.zpdc(list, response);
		return null;
		
	}
	
}
