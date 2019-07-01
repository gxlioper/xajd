/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-18 ����03:45:07 
 */  
package com.zfsoft.xgxt.xszz.xfbzmd;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������ģ��
 * @�๦������: ��ʦ����ֱ����ϵͳ��鿴�����з���ѧ�Ѳ���������ͬѧ��������Ϣ������ѧ�š�������ѧԺ������Ļ�����ѧ԰����
 * 			       ͬʱ���ܿ���ÿ��ͬѧ����ѧ�Ѳ�����ԭ��ͬʱ֧�ֵ�����
 * 			       ������EXCLE�ֶ��� ��š�ѧ�š�ѧԺ���ơ���ע��ÿ��ѧ����������ѧ�Ѳ�����������
 * @���ߣ� ����[����:1186]
 * @ʱ�䣺 2016-7-18 ����03:45:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XfbzmdAction extends SuperAction{
	private static final String url = "xszz_zzxmjg_xfbz.do";
	
	@SystemAuth(url = url)
	@SystemLog(description="����ѧ������-ѧ�Ѳ���������ѯ����list")
	public ActionForward xfbzmdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfbzmdService service=new XfbzmdService();
		XfbzmdForm model=(XfbzmdForm) form;
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("realTable", "xszz_zzxmjg_xfbz");
		String path = "xszz_zzxmjg_xfbz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xfbzmdList");
	}
	/**
	 * @����: ѧ�Ѳ�����������
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ�2016-7-18 ����07:06:57
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
	@SystemLog(description="����ѧ������-ѧ�Ѳ���������������")
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfbzmdService service = new XfbzmdService();
		XfbzmdForm model=(XfbzmdForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//��ѯ�����м�¼������ҳ
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
}
