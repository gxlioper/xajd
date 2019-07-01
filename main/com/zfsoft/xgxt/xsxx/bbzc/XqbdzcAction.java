/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-16 ����09:59:58 
 */  
package com.zfsoft.xgxt.xsxx.bbzc;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.bbzc.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ�ڱ���ע��ACTION 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-16 ����09:59:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XqbdzcAction extends SuperAction {
	
	private static final String url = "xsxx_xqbdzc.do";

	/**
	 * 
	 * @����:����ע��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-16 ����11:09:32
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
	public ActionForward viewBdzcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		CsszService csszService  = new CsszService();
		if(QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			
			String xn = null;
			String xq = null;
			
			if("stu".equals(user.getUserType())){
				xn = Base.currXn;
				xq = Base.currXq;
			}else{
				xn = searchModel.getSearch_tj_xn()[0];
				xq = searchModel.getSearch_tj_xq()[0];
			}
			
			model.setSearchXn(xn);
			model.setSearchXq(xq);
			
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		model.setSearchXn(Base.currXn);
		model.setSearchXq(Base.currXq);
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		
		String path = "xsxx_xqbdzc.do";
		request.setAttribute("zckg", csszService.getCsszParam());
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xqbdzcManage");
	}

	/**
	 * 
	 * @����:����ѧ��ע��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-16 ����05:14:21
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
	public ActionForward dtXqbdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		
		//String xn = model.getXn();
		//String xq = model.getXq();
		String xh = model.getXh();
		
		User user = getUser(request);
		
		if("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		
		
		if(!StringUtil.isNull(xh)){
			
			//ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			
			//ע����Ϣ
			HashMap<String,String> xqbdxx  =  service.getXqZcXx(xh, Base.currXn, Base.currXq);
			if(StringUtils.isBlank(xqbdxx.get("zcsj"))){
				xqbdxx.put("zcsj", DateUtils.getCurrDate());
			}
			request.setAttribute("xqbdxx", xgxt.utils.String.StringUtils.formatData(xqbdxx));
			
			
			//��������
			List<HashMap<String , String>> cwsjList = service.getCwsjList(xh, Base.currXn, Base.currXq);
			request.setAttribute("cwsjList", cwsjList);
		}
		
		String path = "xsxx_xqbdzcgl.do?method=dtXqbdzc";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("tDgzc");
		
	}
	
	/**
	 * 
	 * @����:�鿴ѧ��ע��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-17 ����03:01:11
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
	public ActionForward ckXqbdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		
		String xn = model.getXn();
		String xq = model.getXq();
		String xh = model.getXh();
		
		//ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		//ע����Ϣ
		HashMap<String,String> xqbdxx  =  service.getXqZcXx(xh, xn, xq);
		request.setAttribute("xqbdxx", xgxt.utils.String.StringUtils.formatData(xqbdxx));
		
		//��������
		List<HashMap<String , String>> cwsjList = service.getCwsjList(xh, xn, xq);
		request.setAttribute("cwsjList", cwsjList);
		//��ȡע��״̬
		String zczt = service.getZczt(xh, xn, xq);
		request.setAttribute("zczt", zczt);
		return mapping.findForward("tCkzc");
		
	}
	/**
	 * 
	 * @����:����ע��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-17 ����10:39:39
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
	@SystemLog(description="����ѧ����Ϣ-����ע�ᣨ��ʦ��-ѧ�ڱ���ע��-����PK:{id}")
	public ActionForward doDtZc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		
		model.setZczt("1");
		model.setZcr(getUser(request).getUserName());
		//model.setZcsj(DateUtils.getCurrTime());
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		
		boolean isSuccess = service.doDtZc(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ����ע��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-17 ����11:35:53
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
	public ActionForward plXqbdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		String type = model.getType();
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if(type != null && "zc_all".equals(type)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			String xn = searchModel.getSearch_tj_xn()[0];
			String xq = searchModel.getSearch_tj_xq()[0];
			
			model.setSearchXn(xn);
			model.setSearchXq(xq);
			
			String rownum_w = service.getWzcListCount(model, user ); //δ��������
			String rownum_x = service.getWclListCount(model, user ); //δע������
			int rownum_wx = Integer.valueOf(rownum_w) + Integer.valueOf(rownum_x);//������
			Map<String,String> map = new HashMap<String, String>();
			map.put("rownum_w", rownum_w);
			map.put("rownum_x", rownum_x);
			map.put("rownum_wx", String.valueOf(rownum_wx));
			
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			
//			String length= service.getWzcListCount(model, user); //δע������
//			response.getWriter().print(getJsonMessage(length));
			return null;
		
		}else if(type != null && "zc_select".equals(type)){
			
			request.setAttribute("rownum_w", model.getRownum_w());
			
			request.setAttribute("rownum_x", model.getRownum_x());
			
			request.setAttribute("rownum_wx", model.getRownum_wx());
			
		}else if(request.getParameter("rownum_w") !=null){
			
            request.setAttribute("rownum_w", request.getParameter("rownum_w"));
			
			request.setAttribute("rownum_x", request.getParameter("rownum_x"));
			
			request.setAttribute("rownum_wx", request.getParameter("rownum_wx"));

		}
		
		request.setAttribute("plsqsj", DateUtils.getCurrDate());
		this.saveToken(request);
		return mapping.findForward("tPlzc");
	}
	
	/**
	 * 
	 * @����:����ע��
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-17 ����01:00:21
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
	@SystemLog(description="����ѧ����Ϣ-����ע�ᣨ��ʦ��-ѧ�ڱ���ע��-�����޸�PK:{plIds}")
	public ActionForward doPlXqbdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
//		if (!isTokenValid(request)){
//			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//			return null;
//		} else {
//			super.resetToken(request);
//		}
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String[] plIds = null;
//		String[] wzcxsArr=null;
		User user = getUser(request);
		if(null==model.getPlIds()){
		model.setSearchXn(searchModel.getSearch_tj_xn()[0]);
		model.setSearchXq(searchModel.getSearch_tj_xq()[0]);
//		wzcxsArr= service.getWzcList(model, user); //ȫ����ѯ
		}else{
		plIds=model.getPlIds().split(",");
		}
		String zcsj = model.getZcsj();
		String xn = Base.currXn;
		String xq = Base.currXq;
		String zcr = user.getUserName();
		boolean isSuccess = service.plXqzc(plIds, xn, xq, zcr, zcsj, model, user);

		request.setAttribute("plsqsj", DateUtils.getCurrDate());
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-17 ����01:34:37
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
	public ActionForward cxXqbdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		User user = getUser(request);
		String type = model.getType();
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		if(type != null && "cx_all".equals(type)){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			String xn = searchModel.getSearch_tj_xn()[0];
			String xq = searchModel.getSearch_tj_xq()[0];
			
			model.setSearchXn(xn);
			model.setSearchXq(xq);
			
//			List<HashMap<String,String>> resultList = service.getBdzcList(model, user); //ȫ����ѯ
//
//			StringBuilder ids = new StringBuilder();
//			
//			int rownum_w = 0; //δע������ͳ��
//			int rownum_y = 0; //��ע������ͳ��
//			for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
//				HashMap<String, String> hashMap = (HashMap<String, String>) iterator.next();
//				if(hashMap.get("zczt") != null && "1".equals(hashMap.get("zczt"))){
//					rownum_y ++;
//				}else{
//					rownum_w ++;
//				}
//			}
//			
//			model.setPlIds(ids.toString());
//			
//			request.setAttribute("rownum_w", rownum_w);
//			
//			request.setAttribute("rownum_y", rownum_y);
//			
//			request.setAttribute("rownum_t", resultList.size());
			
			String rownum_w = service.getWzcListCount(model, user); //δ��������
			String rownum_x = service.getWclListCount(model, user); //δע������
		    String rownum_t = service.getBdzcListCount(model, user); //������
			int rownum_y = Integer.valueOf(rownum_t) - Integer.valueOf(rownum_w) - Integer.valueOf(rownum_x);
			
			Map<String,String> map = new HashMap<String, String>();
			map.put("rownum_w", rownum_w);
			map.put("rownum_x", rownum_x);
			map.put("rownum_t", rownum_t);
			map.put("rownum_y", String.valueOf(rownum_y));
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			return null;
			
		}else if(type != null && "cx_select".equals(type)){
			
			request.setAttribute("rownum_w", model.getRownum_w());
			
			request.setAttribute("rownum_x", model.getRownum_x());
			
			request.setAttribute("rownum_y", model.getRownum_y());
			
			request.setAttribute("rownum_t", model.getRownum_t());
			
		
		}else if(request.getParameter("rownum_w") !=null){
			
			request.setAttribute("rownum_w", request.getParameter("rownum_w"));
			
			request.setAttribute("rownum_x", request.getParameter("rownum_x"));
			
			request.setAttribute("rownum_y", request.getParameter("rownum_y"));
			
			request.setAttribute("rownum_t", request.getParameter("rownum_t"));

		}
		
		return mapping.findForward("tPlcx");
	}
	/**
	 * 
	 * @����:��������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-17 ����01:39:58
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
	@SystemLog(description="����ѧ����Ϣ-����ע�ᣨ��ʦ��-ѧ�ڱ���ע��-��������PK:{plIds}")
	public ActionForward doCxXqbdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String[] plIds = null;
//		List<HashMap<String,String>> cxxsList=null;
		User user = getUser(request);
		if(null==model.getPlIds()){
		model.setSearchXn(searchModel.getSearch_tj_xn()[0]);
		model.setSearchXq(searchModel.getSearch_tj_xq()[0]);
//		cxxsList = service.getBdzcList(model, user); //ȫ����ѯ
		}else{
		plIds=model.getPlIds().split(",");
		}
		String xn = Base.currXn;
		String xq = Base.currXq;

		boolean isSuccess = service.plCxzc(plIds, xn , xq, model, user );
		
		request.setAttribute("plsqsj", DateUtils.getCurrDate());
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_AUD_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-17 ����02:22:26
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		XqbdzcService service = new XqbdzcService();
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ

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
	 * @����:δ����ԭ��ά��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-23 ����02:48:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward wbdyywh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqbdzcService service = new XqbdzcService();
		request.setAttribute("wbdlbList", service.getWbdlb());
		return mapping.findForward("wbdyywh");
	}
	/**
	 * 
	 * @����:ѧ��δ����ԭ�򱣴�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-24 ����08:55:08
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
	@SystemLog(description="����ѧ����Ϣ-����ע��-ѧ�ڱ���ע��-δ����ԭ��ά��PK:{plIds}")
	public ActionForward saveWbdyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqbdzcForm model = (XqbdzcForm) form;
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		XqbdzcService service = new XqbdzcService();
		boolean isSuccess = service.wbdyywh(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:��ȡ�û���������б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-23 ����03:56:10
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
	public ActionForward getCyyyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqbdzcService service = new XqbdzcService();
		User user = getUser(request);
		List<HashMap<String,String>> cyyjList = service.getCyyyList(user);
		JSONArray json = JSONArray.fromObject(cyyjList);
		response.getWriter().print(json);
		
		return null;
	}
	/**
	 * 
	 * @����:���ó���δ����ԭ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-23 ����04:09:08
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
	public ActionForward szCyyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqbdzcService service = new XqbdzcService();
		User user = getUser(request);
		List<HashMap<String,String>> cyyyList = service.getCyyyList(user);
		
		request.setAttribute("cyyyList", cyyyList);
		return mapping.findForward("szcyyy");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveCyyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqbdzcService service = new XqbdzcService();
		User user = getUser(request);
		String[] cyyy = request.getParameterValues("cyyy");
		//���泣��ԭ��
		boolean result = service.saveCyyy(user,cyyy);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
}
