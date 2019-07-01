/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-8-4 ����10:17:42 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqjl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.ydgl.YdxxForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.kqgl.kqgl.KqglForm;
import com.zfsoft.xgxt.rcsw.kqgl.kqgl.KqglService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2017-8-4 ����10:17:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class kqjlAction extends SuperAction{

	private kqjlSercice service = new kqjlSercice();
	private static List<HashMap<String, String>> jbxxList = null;
	BdpzService bdpzService = new BdpzService();
	private static final String url = "rcsw_kqgl_kqjl.do";
	@SystemAuth(url = url)
	public ActionForward viewKqjlList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		kqjlForm model = (kqjlForm) form;
		if(QUERY.equals(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewKqjlList");
	}
	
	
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		kqjlForm model=(kqjlForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);
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
	
	
	
	public ActionForward viewKqjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		kqjlForm model = (kqjlForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz("kqgl");
		request.setAttribute("jbxxList", jbxxList);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			Map<String, String> rs = service.getKqjl(model.getXh(),model.getKqsj());// ������ϸ��Ϣ
			request.setAttribute("rs", rs);
			return mapping.findForward("viewKqjl");
	}
	

}
