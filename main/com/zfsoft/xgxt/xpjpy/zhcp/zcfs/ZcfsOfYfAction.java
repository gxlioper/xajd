/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-23 ����01:36:54 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import java.io.File;
import java.util.ArrayList;
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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �۲����(���´��)
 * @���ߣ� xiaxia [���ţ�1104]
 * @ʱ�䣺 2015-10-29 ����01:36:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcfsOfYfAction extends SuperAction {
	
	private static final String url = "pj_zcflr.do";
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-10-29 ����09:20:52
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
	public ActionForward editZcfss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcfsForm.setSearchModel(searchModel);
		//��Ĭ����Ŀ�ͽӿ�ͬ����Ŀ�������������¼��
		service.initDefaultZcfs(zcfsForm, user);
		//��ѯ
		List<HashMap<String,String>> resultList = service.getPageListOfYf(zcfsForm,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;

	}
	
   
	
	/**
	 * 
	 * @����: �����۲����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-7-24 ����03:09:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-�ۺϲ���-�۲�ά��-¼��-ɾ��ѧ��XH��{xh}")
	public ActionForward saveZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		
		User user = getUser(request);
		
		boolean result = service.saveZcfsYf(zcfsForm, user);
		
		if (!result){
			//���ʧ�ܣ�����ʾ
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		
		return null;
	}
	@SystemAuth(url = url)
	public ActionForward viewZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageListOfYf(zcfsForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportViewZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZcfsService service = new ZcfsService();
		ZcfsModel zcfsForm = (ZcfsModel) form;
		User user = getUser(request);
		
		File file = service.getBjZcfFileOfYf(zcfsForm,user);
		//�����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
