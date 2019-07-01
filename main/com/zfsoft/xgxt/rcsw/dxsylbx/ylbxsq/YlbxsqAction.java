/**
 * 
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-17 ����01:36:00
 * 
 */
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsq;

import java.io.File;
import java.util.ArrayList;
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
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjcsz.YlbxJcszForm;
import com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjcsz.YlbxJcszService;
import com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsh.YlbxshService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class YlbxsqAction extends SuperAction {
	// ����ѧ��֤������ѧ��֤���쳣�����Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String DXSYLBX = "dxsylbx";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	
	private static final String url = "rcsw_dxsylbx_ylbxsq.do";

	/**
	 * 
	 * @����:(��ѯҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-7 ����11:39:49
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
	public ActionForward ylbxsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YlbxsqForm model = (YlbxsqForm) form;
		YlbxsqService service = new YlbxsqService();

		if (QUERY.equalsIgnoreCase(model.getType())) {

			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;

		}

		YlbxJcszService ylbxJcszService = new YlbxJcszService();
		YlbxJcszForm jcszModel = ylbxJcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		String path = "rcsw_dxsylbx_ylbxsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ylbxsqManage");

	}

	/**
	 * 
	 * @����:(����ҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-8 ����03:32:14
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ�������-����XH:{xh},XN:{xn},XQ:{xq},CZQEBZ:{czqebz},CBZK:{cbzk},SQLY:{sqly}")
	public ActionForward addYlbxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YlbxsqForm model = (YlbxsqForm) form;
		YlbxsqService service = new YlbxsqService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(model.getType())
				|| SUBMIT.equalsIgnoreCase(model.getType())) {
			// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}
			
			boolean isExist = false;
			model.setCbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			isExist = service.isExistByXszbbsq(model);
			if (!isExist) {
				super.resetToken(request);
				// ���ѧ��֤��������
				String ylsqid = UniqID.getInstance().getUniqIDHash();
				model.setYlsqid(ylsqid);
				boolean result = service.saveYlbxsq(model);
				String messageKey = "";
				if (SAVE.equalsIgnoreCase(model.getType())) {
					messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
				} else {
					messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
							: MessageKey.SYS_SUBMIT_FAIL;
				}
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			} else {
				response.getWriter().print(
						getJsonMessageByKey(MessageKey.RCSW_DXSYLBX_YLBXSQCZ));
				return null;
			}
		}
		String path = "rcsw_dxsylbx_ylbxsqgl.do?method=addYlbxsq";
		request.setAttribute("path", path);

		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(DXSYLBX);
		request.setAttribute("jbxxList", jbxxList);
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setXqmc(service.getCurrentXqmc(model));

		request.setAttribute("czqebzryList", service.getCzqebzryList());
		request.setAttribute("cbzkList", service.getCbzkList());
		this.saveToken(request);
		return mapping.findForward("addYlbxsq");

	}

	/**
	 * 
	 * @����:(�޸�ҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-8 ����03:32:42
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ�������-�޸�YLSQID:{ylsqid},XN:{xn},XQMC:{xqmc},CZQEBZ:{czqebz},CBZK:{cbzk},SQLY:{sqly}")
	public ActionForward updateYlbxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YlbxsqForm model = (YlbxsqForm) form;
		YlbxsqService service = new YlbxsqService();

		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (UPDATE.equalsIgnoreCase(model.getType())
				|| SUBMIT.equalsIgnoreCase(model.getType())) {
			model.setCbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			boolean result = service.updateYlbxsq(model);
			String messageKey = "";
			if (UPDATE.equalsIgnoreCase(model.getType())) {
				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
			} else {
				messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
						: MessageKey.SYS_SUBMIT_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String path = "rcsw_dxsylbx_ylbxsqgl.do?method=updateYlbxsq";
		request.setAttribute("path", path);

		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(DXSYLBX);
		request.setAttribute("jbxxList", jbxxList);
		YlbxsqForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		List<HashMap<String, String>> data = service.getCzqebzryList();
		model.setXqmc(service.getCurrentXqmc(model));
		
		YlbxJcszService ylbxJcszService = new YlbxJcszService();
		YlbxJcszForm jcszModel = ylbxJcszService.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		request.setAttribute("czqebzryList", data);
		request.setAttribute("cbzkList", service.getCbzkList());
		request.setAttribute("qtcbzkval", model.getQtcbzkval());
		return mapping.findForward("updateYlbxsq");

	}



	/**
	 * 
	 * @����:(ɾ��ҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-13 ����05:12:34
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ�������-ɾ��VALUES:{values}")
	public ActionForward delYlbxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YlbxsqService service = new YlbxsqService();
		String values = request.getParameter("values");
		// String[] ids = values.split(",");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delYlbxsq(values.split(","));
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
	 * @����:(�ύҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:29:03
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ�������-�ύVALUES:{values}")
	public ActionForward submitYlbxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YlbxsqForm model = (YlbxsqForm) form;
		String ylsqid = request.getParameter("values");
		String xh = request.getParameter("xh");
		model.setYlsqid(ylsqid);
		model.setXh(xh);
		YlbxsqService service = new YlbxsqService();
		boolean result = service.submitYlbxsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;

	}

	/**
	 * 
	 * @����:(������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����06:28:42
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ѧ��ҽ�Ʊ���-ҽ�Ʊ�������-����VALUES:{values}")
	public ActionForward cancelYlbxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxsqService service = new YlbxsqService();
		String ylsqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(ylsqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			YlbxsqForm model = new YlbxsqForm();
			model.setYlsqid(ylsqid);
			model.setSplc(lcid);
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(ylsqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.cancelYlbxsq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:(�鿴ҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-13 ����04:06:56
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
	public ActionForward viewYlbxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YlbxsqForm model = (YlbxsqForm) form;
		YlbxsqService service = new YlbxsqService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		// ����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
				.getXh());
		request.setAttribute("jbxx", xsjbxx);
		// �鿴ҽ�Ʊ�������
		request.setAttribute("rs", service.getYlbxsqInfo(model));

		// ѧ��������Ϣ
		jbxxList = bdpzService.getJbxxpz(DXSYLBX);
		request.setAttribute("jbxxList", jbxxList);
		String path = "rcsw_dxsylbx_ylbxsqgl.do?method=viewYlbxsq";
		request.setAttribute("path", path);
		model = service.getModel(model);
		YlbxshService ylbxshService = new YlbxshService();
		if (!"".equals(model.getCzqebzdm()) && model.getCzqebzdm() != null) {
			String[] czqebzdms = model.getCzqebzdm().split(",");
			request.setAttribute("czqebzrymcsList", ylbxshService
					.getCzqebzrymcList(czqebzdms));
		}
		if (!"".equals(model.getCbzkdm()) && model.getCbzkdm() != null) {
			String[] cbzkdms = model.getCbzkdm().split(",");
			request.setAttribute("cbzkmcsList", ylbxshService
					.getCbzkdmcsList(cbzkdms));
		}

		request.setAttribute("czqebzryList", service.getCzqebzryList());
		request.setAttribute("cbzkList", service.getCbzkList());
		request.setAttribute("qtcbzkval", model.getQtcbzkval());
		request.setAttribute("model", model);
		return mapping.findForward("viewYlbxsq");

	}

	/**
	 * 
	 * @����:(�鿴�α�״��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-14 ����01:58:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward viewCbzk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xm = request.getParameter("xm");
		if("null".equals(xm)){
			xm = "";
		}
		YlbxsqForm model = (YlbxsqForm) form;
		YlbxsqService service = new YlbxsqService();

		model = service.getModel(model);
		YlbxshService ylbxshService = new YlbxshService();
		if (!"".equals(model.getCzqebzdm()) && model.getCzqebzdm() != null) {
			String[] czqebzdms = model.getCzqebzdm().split(",");
			request.setAttribute("czqebzrymcsList", ylbxshService
					.getCzqebzrymcList(czqebzdms));
		}
		if (!"".equals(model.getCbzkdm()) && model.getCbzkdm() != null) {
			String[] cbzkdms = model.getCbzkdm().split(",");
			request.setAttribute("cbzkmcsList", ylbxshService
					.getCbzkdmcsList(cbzkdms));
		}
		
		request.setAttribute("qtcbzkval", model.getQtcbzkval());
		request.setAttribute("model", model);
		request.setAttribute("xm",xm);
		return mapping.findForward("viewCbzk");

	}

	/**
	 * 
	 * @����:(�Զ��嵼������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:30:04
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
	 * 
	 * @����:(��ѧ��ҽ�Ʊ��մ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-16 ����11:01:37
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
	public ActionForward getDxsylbxZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getDxsylbxWord(values[i]);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
		
	}
	
	

	/**
	 * 
	 * @����: ��ӡWord
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-16 ����02:22:14
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward getDxsylbxWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YlbxsqForm myForm = (YlbxsqForm) form;
		//String xh = myForm.getXh();
		String ylsqid = myForm.getYlsqid();
		File wordFile = getDxsylbxWord(ylsqid);
		FileUtil.outputWord(response, wordFile);
		return null;
		
	}

	// ���ģ����������word�ļ�
	private File getDxsylbxWord(String ylsqid) throws Exception {
  
		YlbxsqService service = new YlbxsqService();
		Map<String, Object> data = new HashMap<String, Object>();
		
		YlbxsqForm model = new YlbxsqForm();
		model.setYlsqid(ylsqid);
		model = service.getModel(model);
		String xh = model.getXh();
		if (!StringUtils.isNull(xh)) {
			// ������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			data.put("xxmc", Base.xxmc);
			data.putAll(xsjbxx);
			
			
			if(StringUtils.isNull(model.getQtcbzkval())){
				data.put("qtcbzkval", "");
			}else{
				data.put("qtcbzkval", model.getQtcbzkval());
			}
			List<HashMap<String, String>> dataCzqebzryMap = service.getCzqebzryList();
			data.put("czqebzryList",fomatCzqebzryData(model.getCzqebzdm(), dataCzqebzryMap));
			List<HashMap<String, String>> dataCbzkMap = service.getCbzkList();
			data.put("cbzkList", fomatCbzkdmData(model.getCbzkdm(), dataCbzkMap));
			data.put("model",model);
			File wordFile = FreeMarkerUtil.getWordFile(data,
					"classpath://templates//rcsw", "dxsylbx.xml", xh + "-"
							+ xsjbxx.get("xm"));
			return wordFile;
		}

		return null;
	}
	
	/**
	 * 
	 * @����:(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-16 ����11:05:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @param data
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	private List<HashMap<String, String>> fomatCzqebzryData(String str,
			List<HashMap<String, String>> data) {
		List<HashMap<String, String>> newData=new ArrayList<HashMap<String,String>>();
		if(str.length() >0){
			for(HashMap<String,String> hm:data){
					if (str.indexOf(hm.get("czqebzdm"))>=0) {
						hm.put("isExsit", "1");
					} else {
						hm.put("isExsit", "0");
					}
					newData.add(hm);
				}
		}else{
			for(HashMap<String,String> hm:data){
				hm.put("isExsit","0");
				newData.add(hm);
			}
		}
		return newData;
	}
	
	/**
	 * 
	 * @����:(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-16 ����11:05:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param str
	 * @param data
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	private List<HashMap<String, String>> fomatCbzkdmData(String str,List<HashMap<String, String>> data) {
		
		List<HashMap<String, String>> newData=new ArrayList<HashMap<String,String>>();
		if(str.length() >0){
			for(HashMap<String,String> hm:data){
				if (str.indexOf(hm.get("cbzkdm"))>=0) {
					hm.put("isExsit", "1");
				} else {
					hm.put("isExsit", "0");
				}
			   newData.add(hm);
			}
		}else{
			for(HashMap<String,String> hm:data){
				hm.put("isExsit","0");
				newData.add(hm);
			}
		}
		return newData;
		
	}
	
}
