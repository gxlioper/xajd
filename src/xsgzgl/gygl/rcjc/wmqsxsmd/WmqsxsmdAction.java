/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-5-10 ����03:35:48 
 */  
package xsgzgl.gygl.rcjc.wmqsxsmd;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import xsgzgl.gygl.rcjc.xswsf.XswsfService;

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
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018-5-10 ����03:35:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WmqsxsmdAction  extends SuperAction<WmqsxsmdForm,WmqsxsmdService> {
	private WmqsxsmdService service = new WmqsxsmdService();
	private static final String url = "gyglnew_wmqsxsmd_wmqsxsmdgl.do";
	
	
	public ActionForward wmqsxsmdManage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WmqsxsmdForm model = (WmqsxsmdForm) form;
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
		String path = "gyglnew_wmqsxsmd_wmqsxsmdgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wmqsxsmdManage");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward wmqsxsmdAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WmqsxsmdForm model = (WmqsxsmdForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			/*����ѧ��������Ϣ*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//XswsfService xswsfService = new XswsfService();
		List<HashMap<String , String>> xnList = Base.getXnndList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", Base.getXqList());
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String lrsj = df.format(new Date());
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		model.setLrr(user.getUserName());
		model.setLrsj(lrsj);
		request.setAttribute("lrsj", lrsj);
		request.setAttribute("lrrxm", user.getRealName());
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("gyglnew_wmqsxsmd");
		String path = "gyglnew_wmqsxsmd_12688.do?method=wmqsxsmdAdd";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("wmqsxsAdd");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward wmqsxsmdEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WmqsxsmdForm myForm = (WmqsxsmdForm) form;
		WmqsxsmdForm model  = service.getModel(myForm);
		BeanUtils.copyProperties(myForm,StringUtils.formatData(model));
		User user = getUser(request);
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//ѧ��������Ϣ�б�
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("gyglnew_wmqsxsmd");
		
		request.setAttribute("jbxxList", jbxxList);
		XswsfService xswsfService = new XswsfService();
		List<HashMap<String , String>> xnList = xswsfService.getRcXnList();
		request.setAttribute("xnList", xnList);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String lrsj = df.format(new Date());
		model.setLrr(user.getUserName());
		model.setLrsj(lrsj);
		request.setAttribute("lrrxm", user.getRealName());
		request.setAttribute("xqmc", Base.getXqmcForXqdm(model.getXq()));
		request.setAttribute("model", model);
		return mapping.findForward("wmqsxsEdit");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward wmqsxsmdSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WmqsxsmdForm model = (WmqsxsmdForm) form;
		model.setSjly("1");
		String type = request.getParameter("type");
		boolean result = false;
		if("add".equals(type)){
			String count = service.countXs(model);
			if(!"0".equals(count)){
				Map<String,String> map = new HashMap<String,String>();
				map.put("message", "��ѧ����"+model.getXn()+"ѧ��"+model.getXq()+"ѧ��"+"�ѻ����������");
				map.put("success", String.valueOf(result));
				JSONObject json = JSONObject.fromObject(map); 
				response.getWriter().print(json);
				return null;
			}
			result = service.runInsert(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey, result));
		}
		if("edit".equals(type)){
			result =service.runUpdate(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey, result));
		}
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward viewWmqsxsmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WmqsxsmdForm myForm = (WmqsxsmdForm) form;
		WmqsxsmdForm model  = service.getModel(myForm);
		BeanUtils.copyProperties(myForm,StringUtils.formatData(model));
		//����ѧ��������Ϣ
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz("gyglnew_wmqsxsmd");
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", model);
		request.setAttribute("lrrxm",Base.getJsxmForzgh(model.getLrr()));
		request.setAttribute("xqmc", Base.getXqmcForXqdm(model.getXq()));
		return mapping.findForward("wmqsxsView");
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
		WmqsxsmdForm model = (WmqsxsmdForm) form;
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
	
}
