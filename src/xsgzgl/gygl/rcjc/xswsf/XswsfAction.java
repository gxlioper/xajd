/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-5-8 ����02:18:03 
 */  
package xsgzgl.gygl.rcjc.xswsf;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����-�������-ѧ�������֣���������ְҵ����ѧԺ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� lgx[����:1553]
 * @ʱ�䣺 2018-5-8 ����02:18:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XswsfAction extends SuperAction<XswsfForm,XswsfService>  {
	private XswsfService service = new XswsfService();
	private static final String url = "gyglnew_xswsf_xswsfgl.do";
	
	
	public ActionForward xswsfManage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsfForm model = (XswsfForm) form;
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
		/*SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		request.setAttribute("searchTj", searchModel);*/
		String path = "gyglnew_xswsf_xswsfgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xswsfManage");
	}
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xswsfEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsfForm myForm = (XswsfForm) form;
		HashMap<String, String> map = service.getXswsfById(myForm);
		BeanUtils.copyProperties(myForm,StringUtils.formatData(map));
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
		request.setAttribute("jbxx", xsjbxx);
		//ѧ��������Ϣ�б�
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("gyglnew_xswsf");
		
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rs", map);
		request.setAttribute("lrrxm",Base.getJsxmForzgh(map.get("lrr")));
		request.setAttribute("bz",map.get("bz"));
		
		return mapping.findForward("xswsjcEdit");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xswsfSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsfForm model = (XswsfForm) form;
		boolean result =service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey, result));
		return null;
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printWmqs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsfForm model = (XswsfForm) form;
		List<HashMap<String , String>> xnList = Base.getXnndList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", Base.getXqList());
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		return mapping.findForward("printWmqs");
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveXsmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsfForm model = (XswsfForm) form;
		List<HashMap<String,String>> xsmcList = service.getWmqsxsMd(model.getXn(),model.getXq());
		User user = getUser(request);
		if(xsmcList.size() > 0){
			boolean result =service.insertXsmc(xsmcList,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey, result));
		}else{
			Map<String,String> map = new HashMap<String,String>();
			map.put("message", model.getXn()+"ѧ��"+model.getXq()+"ѧ��"+"û��ѧ�������������");
			map.put("success", String.valueOf(false));
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
		}
		
		return null;
	}
	
	/**
	 * @����������
	 * @���ߣ�lgx[����:1553]
	 * @���ڣ�2018��5��8�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsfForm model = (XswsfForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward viewXswsf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XswsfForm myForm = (XswsfForm) form;
		HashMap<String, String> map = service.getXswsfById(myForm);
		//����ѧ��������Ϣ
		if (!StringUtil.isNull(map.get("xh"))) {
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ�б�
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("gyglnew_xswsf");
		
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rs", map);
		return mapping.findForward("xswsjcView");
	}
	
}
