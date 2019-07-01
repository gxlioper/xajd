/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-2-10 ����04:58:34 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.zcsh;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.dtjs.zzgxzc.zcsq.ZcsqSERVICE;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-2-10 ����04:58:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcshDO extends SuperAction<ZcshForm, ZcshSERVICE> {
	ZcshSERVICE service = new ZcshSERVICE();
	private final String ZCSQ ="zcsq";
	
	private static final String url = "dtjs_dzzgxsh.do";	//����б�dtjs_dzzgxsh.do
	
	/**
	 * 
	 * @����: ����֯��ϵת����˲�ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-13 ����09:14:15
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
	public ActionForward zcshCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZcshForm zcshform = (ZcshForm)form;
		if (QUERY.equalsIgnoreCase(zcshform.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcshform.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(zcshform, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "dtjs_dzzgxsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zcshcx");
	}
	
	/**
	 * 
	 * @����: �������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-13 ����10:30:30
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
	@SystemLog(description="���ʵ��Ž���-��֯��ϵת��-��Ϣ���-���XH:{xh},SZDZB:{szdzb},SFSN:{sfsn},JSDZZ:{jsdzz},SQDW:{sqdw},DFJZRQ:{dfjzrq},"
			+ "SFKJHYZM:{sfkjhyzm},SHJG:{shjg},JSXBH:{jsxbh},SHYJ:{shyj}")
	public ActionForward Dgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZcshForm zcshform = (ZcshForm)form;
		
		//����
		if(SAVE.equalsIgnoreCase(zcshform.getType())){
			User user = getUser(request);
			ZcshSERVICE shService = TransactionControlProxy.newProxy(new ZcshSERVICE());
			//���浥�����
			boolean result = false;
			try {
				result = shService.saveSh(zcshform, user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				throw e;
			
			}
			return null;
		}
		
		//���ݸ�λid���ж��Ƿ���ʾ�����ű�ţ���˸�λΪ�����һ����ʾ������������ֻ��һ����λ�����һ����ʾ
		boolean isJsxbhShow = service.isJsxbhShow(zcshform);
		
		if (!StringUtil.isNull(zcshform.getXh()) && !SAVE.equalsIgnoreCase(zcshform.getType())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zcshform.getXh());
			request.setAttribute("jbxx", xsjbxx);
			ZcshForm model = service.getModel(zcshform);
			BeanUtils.copyProperties(zcshform, model);
		}
		
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("shid", request.getParameter("shid"));
		request.setAttribute("dzbList",new ZcsqSERVICE().getDzbList());
		ZcsqSERVICE sqService = new ZcsqSERVICE();
		request.setAttribute("rs", sqService.ckZcsq(zcshform.getXh()));
		request.setAttribute("isJsxbhShow", isJsxbhShow);
		return mapping.findForward("dgsh");
	}
	
	
	/**
	 * 
	 * @����: �������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-13 ����10:47:20
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
	@SystemLog(description="���ʵ��Ž���-��֯��ϵת��-��Ϣ���-��������ˣ�SQID:{sqid},SHZT{shzt}")
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZcshForm model = (ZcshForm) form;
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
	
	/**
	 * 
	 * @����: �������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-13 ����10:54:25
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
	@SystemLog(description="���ʵ��Ž���-��֯��ϵת��-��Ϣ���-��������ˣ�SHID:{shid},SPLC{splc}")
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcshForm model = (ZcshForm) form;
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
		// ��˳����ɹ�
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;

	}
	
	/**
	 * 
	 * @����: ���������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-11 ����04:01:20
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
		ZcshForm myForm = (ZcshForm)form;
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(myForm,
				user);// ��ѯ�����м�¼������ҳ
		
		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
