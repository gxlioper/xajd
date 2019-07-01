/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-9 ����02:25:38 
 */  
package xsgzgl.gygl.sdftj;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-12-9 ����02:25:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class sdfTjAction extends SuperAction<sdfTjForm, sdfTjService> {
	private sdfTjService service = new sdfTjService();
	public ActionForward getSdfTjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		sdfTjForm model = (sdfTjForm) form;
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
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		request.setAttribute("searchTj", searchModel);
		String path = "xg_gygl_sdftj.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @����: ����ˮ���ά��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:13:35
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
	public ActionForward addSdfTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		sdfTjForm model = (sdfTjForm) form;
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("LddmList",service.getLddmList() );
		//������Ϣ����
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @����:�޸�ˮ���ά��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:58:32
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
	public ActionForward editSdfTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		sdfTjForm myForm = (sdfTjForm) form;
		sdfTjForm model = service.getModel(myForm.getId());
		BeanUtils.copyProperties(myForm, model);
		request.setAttribute("ldmc", service.getLdmc(model.getLddm()));
		request.setAttribute("ch", service.getCh(model.getQsh()));
		request.setAttribute("rs", model);
		return mapping.findForward("edit");
	}
	
	/**
	 * @����: ���ݵ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����05:13:07
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
		
		sdfTjForm model = (sdfTjForm) form;

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
	 * 
	 * @����:ɾ�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����05:17:22
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
	public ActionForward deljg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����05:18:29
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
	public ActionForward saveData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		sdfTjForm model = (sdfTjForm) form;
		String rs = service.saveData(model) ;
		response.getWriter().print(getJsonMessage(rs));
		return null;
	}
	
	/**
	 * 
	 * @����: �������л������ֵ�仯
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-12 ����09:19:20
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
	public ActionForward changeSelect(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		String type = request.getParameter("type");
		String lddm = request.getParameter("lddm");
		List<HashMap<String, String>>  dataList = new ArrayList<HashMap<String,String>>();
		if("ld".equals(type)){
			  dataList = service.getChList(lddm);
		}else if("cc".equals(type)){
			String ch = request.getParameter("ch");
			  dataList = service.getQshList(lddm, ch);
		}
		if(dataList.isEmpty()){
			response.getWriter().print(getJsonMessage("null"));
		}else{
			HashMap<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("dataList", dataList);
			dataMap.put("message", "true");
			JSONObject dataMapJson = JSONObject.fromObject(dataMap);
			response.getWriter().print(dataMapJson);
		}
		return null;
	}
	
	/**
	 * 
	 * @����:�鿴ˮ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-13 ����08:58:30
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
	public ActionForward ckSdfTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		sdfTjForm myForm = (sdfTjForm) form;
		sdfTjForm model = service.getModel(myForm.getId());
		request.setAttribute("ldmc", service.getLdmc(model.getLddm()));
		request.setAttribute("ch", service.getCh(model.getQsh()));
		request.setAttribute("rs", model);
		return mapping.findForward("ck");
	}
}
