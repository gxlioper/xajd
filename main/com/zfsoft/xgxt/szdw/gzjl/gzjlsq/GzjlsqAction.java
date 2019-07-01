/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����04:38:49 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjlsq;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgForm;
import com.zfsoft.xgxt.szdw.gzjl.gzjljg.GzjljgForm;
import com.zfsoft.xgxt.szdw.gzjl.gzjljg.GzjljgService;
import com.zfsoft.xgxt.szdw.gzjl.jcsz.GzjlJcszService;
import com.zfsoft.xgxt.szdw.gzjl.lbgl.GzjlLbglService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-6-25 ����04:38:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzjlsqAction extends SuperAction<GzjlsqForm, GzjlsqService> {
	private final String GZJL="gzjl";
	private GzjlsqService service = new GzjlsqService();
	private GzjljgService gzjljgservice = new GzjljgService();
	private GzjlLbglService gzlbService = new GzjlLbglService();

	private static final String url = "gzjl_gzjlsq.do";
	
	/**
	 * 
	 * @����:������¼�����б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����05:05:47
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
	public ActionForward gzjlsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlsqForm model = (GzjlsqForm) form;
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
		GzjlJcszService  jcszService = new GzjlJcszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		String path = "gzjl_gzjlsq.do";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gzjlsqList");
	}
	/**
	 * 
	 * @����:������¼��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����05:05:47
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
	public ActionForward gzjlsqZj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlsqForm model = (GzjlsqForm) form;
		User user = getUser(request);
		model.setZgh(user.getUserName());
		if (!StringUtil.isNull(model.getZgh())) {
			// ���ؽ�ʦ������Ϣ
			
			HashMap<String, String> xsjbxx = gzjljgservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ��ʦ������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GZJL);
		List<HashMap<String, String>> gzlbList = gzlbService.getGzjllbList();
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("gzlbList", gzlbList);
		request.setAttribute("jlsj", GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		request.setAttribute("xnList", Base.getXnndList());
		//�㽭���˴�ѧȡ����������
		if("11842".equals(Base.xxdm)){
			request.setAttribute("lksList", service.getLks());
			model.setLks("7");
		}		
		String path = "gzjlsq.do?method=gzjlsqZj";
		request.setAttribute("path", path);
		return mapping.findForward("gzjlsqZj");
	}
	/**
	 * 
	 * @����:������¼�����޸�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����05:05:47
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
	public ActionForward gzjlsqXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlsqForm myForm = (GzjlsqForm) form;
		GzjlsqForm model = service.getModel(myForm);
		if(null!=model){
			model.setLbbh(model.getLbdm());
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// ���ؽ�ʦ������Ϣ
			
			HashMap<String, String> xsjbxx = gzjljgservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ��ʦ������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GZJL);
		List<HashMap<String, String>> gzlbList = gzlbService.getGzjllbList();
		request.setAttribute("gzlbList", gzlbList);
		request.setAttribute("jlsj", GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		request.setAttribute("jbxxList", jbxxList);
		//���˴�ѧ���Ի�����
		if("11842".equals(Base.xxdm)){
			request.setAttribute("lksList", service.getLks());
			if(StringUtils.isNotNull(model.getXh())){
				String[] xhArr = model.getXh().split(",");
				List<HashMap<String,String>> thdxList = service.getThdxList(xhArr);
				request.setAttribute("thdxList", thdxList);
			}
		}
		request.setAttribute("gzjlsq", model);
		String path = "gzjlsq.do?method=gzjlsqXg";
		request.setAttribute("path", path);
		return mapping.findForward("gzjlsqXg");
	}
	/**
	 * 
	 * @����:������¼����鿴
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����05:05:47
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
	public ActionForward gzjlsqCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlsqForm myForm = (GzjlsqForm) form;
		GzjlsqForm model = service.getModel(myForm);
		//���˴�ѧȡ��̸������
		if("11842".equals(Base.xxdm)){
			if(StringUtils.isNotNull(model.getXh())){
				String[] xhArr = model.getXh().split(",");
				List<HashMap<String,String>> thdxList = service.getThdxList(xhArr);
				request.setAttribute("thdxList", thdxList);
			}
			
		}		
		User user = getUser(request);
		
		// ���ؽ�ʦ������Ϣ
		
		HashMap<String, String> xsjbxx = gzjljgservice.getJsjbxx(model.getZgh());
		request.setAttribute("jbxx", xsjbxx);
		
		// ��ʦ������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GZJL);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("shzt", model.getShzt());
		return mapping.findForward("gzjlsqCk");

	}
	/**
	 * 
	 * @����:������¼���뱣��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����05:24:21
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
	@SystemLog(description="����ְ��������¼��Ϣ-������¼����-������¼��Ϣ-����ZGH:{zgh},GZSJ:{gzsj},LBDM:{lbdm},GZZY:{gzzy}")
	public ActionForward saveGzjlsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlsqForm model = (GzjlsqForm) form;
		//�㽭����ѧԺ���Ի�
		if("11842".equals(Base.xxdm)){
			String objStr = request.getParameter("objStr");
			if(null != objStr && !"".equals(objStr)) {
				model.setXh(objStr.substring(0, objStr.length()-1));
			}else{
				model.setXh("");
			}			
		}		
		boolean result = service.saveGzjlsq(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:�����޸ı���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����05:24:47
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
	@SystemLog(description="����ְ��������¼��Ϣ-������¼����-������¼��Ϣ-�޸�SQID:{sqid},ZGH:{zgh},GZSJ:{gzsj},LBDM:{lbdm},GZZY:{gzzy},JLSJ:{jlsj}")
	public ActionForward saveEditGzjlsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlsqForm model = (GzjlsqForm) form;
		String message=null;
		//���˴�ѧ
		if("11842".equals(Base.xxdm)){
			String objStr = request.getParameter("objStr");
			if(null != objStr && !"".equals(objStr)) {
				model.setXh(objStr.substring(0, objStr.length()-1));
			}else{
				model.setXh("");
			}			
		}
		boolean result = service.saveEditGzjlsq(request,model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:�����¼ɾ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����05:32:28
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
	@SystemLog(description="����ְ��������¼��Ϣ-������¼����-������¼��Ϣ-ɾ��sqid:{values}")
	public ActionForward delGzjlsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	 * @����:����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����05:32:46
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
	public ActionForward cancelGzjlsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			GzjlsqForm model = new GzjlsqForm();
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
	 * @����:���ݵ���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-29 ����05:33:05
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
		GzjlsqForm model = (GzjlsqForm) form;

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
	
	/** 
	 * @����:����̸������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-18 ����09:49:23
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
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		GzjlsqForm myForm = (GzjlsqForm) form;
		String xhArr= request.getParameter("xhArr");
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			//String bjdm = request.getParameter("bjdm");
			
			//myForm.setBjdm(bjdm);
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user,request);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("xhArr", xhArr);
		String path = "gzjlsq.do?method=getStu";
		request.setAttribute("path", path);
		return mapping.findForward("getStu");
	}

}
