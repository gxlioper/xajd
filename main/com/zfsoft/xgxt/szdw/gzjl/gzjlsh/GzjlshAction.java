/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����04:33:26 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjlsh;

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
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.szdw.gzjl.gzjljg.GzjljgService;
import com.zfsoft.xgxt.szdw.gzjl.gzjlsq.GzjlsqService;
import com.zfsoft.xgxt.szdw.gzjl.jcsz.GzjlJcszService;
import com.zfsoft.xgxt.szdw.gzjl.lbgl.GzjlLbglService;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-6-25 ����04:33:26 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzjlshAction extends SuperAction<GzjlshForm, GzjlshService> {
	private final String GZJL="gzjl";
	private GzjlshService service = new GzjlshService();
	private GzjljgService gzjljgservice = new GzjljgService();
	
	private static final String url = "gzjl_gzjlsh.do";
	
	/**
	 * 
	 * @����:������¼����б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-2 ����09:11:07
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
	public ActionForward gzjlshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlshForm model = (GzjlshForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		GzjlJcszService  jcszService = new GzjlJcszService();
		String[] sqshkg = jcszService.getSqShKg();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("shkg", sqshkg==null?"0":sqshkg[1]);
		request.setAttribute("path", "gzjl_gzjlsh.do");
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gzjlshList");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward gzjlDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlshForm model = (GzjlshForm) form;
		if (!StringUtil.isNull(model.getZgh())) {
			// ���ؽ�ʦ������Ϣ
			HashMap<String, String> xsjbxx = gzjljgservice.getJsjbxx(model.getZgh());
			request.setAttribute("jbxx", xsjbxx);
			HashMap<String, String> infoList = service.getGzjlshInfo(model);
			if("11842".equals(Base.xxdm)){
				if(StringUtils.isNotNull(infoList.get("xh"))){
					String[] xhArr = infoList.get("xh").split(",");
					GzjlsqService gzjlsqService = new GzjlsqService();
					List<HashMap<String,String>> thdxList = gzjlsqService.getThdxList(xhArr);
					request.setAttribute("thdxList", thdxList);
				}
			}
			request.setAttribute("rs", StringUtils.formatData(infoList));
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// ���浥�����
			boolean result = service.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(GZJL);
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		model.setShid(request.getParameter("shid"));
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("gzjlDgsh");
	}
	/**
	 * 
	 * @����:�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����04:07:50
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
	public ActionForward gzjlPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlshForm model = (GzjlshForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("gzjlPlsh");
	}
	/**
	 * 
	 * @����:���һ����˳���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����04:08:06
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
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GzjlshForm model = (GzjlshForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
		// ���һ������
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
//	/**
//	 * 
//	 * @����:��˳���
//	 * @���ߣ�xiaxia[���ţ�1104]
//	 * @���ڣ�2015-1-20 ����06:50:51
//	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 * ActionForward �������� 
//	 * @throws
//	 */
//	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		GzjlshForm model =new GzjlshForm();
//		String shid = request.getParameter("shid");
//		String splc = request.getParameter("shlc");
//		model.setShlc(splc);
//		model.setShid(shid);
//		User user = getUser(request);
//		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
//		
//		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
//		
//
//		// ��˳����ɹ�
//		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
//				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
//				: MessageKey.SYS_CANCEL_FAIL;
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("message", MessageUtil.getText(messageKey));
//		map.put("cancelFlg", cancelFlg);
//		response.getWriter().print(JSONObject.fromObject(map));
//		return null;
//	}


}
