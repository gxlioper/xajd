/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-9-5 ����10:25:03 
 */  
package xsgzgl.gygl.ydgl;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.utils.String.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.ldgl.LdglModel;
import xsgzgl.gygl.ldgl.LdglServices;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �õ����ģ��
 * @�๦������: �õ�������ɾ�Ĳ鵼�뵼��������
 * @���ߣ� caopei[����:1352]
 * @ʱ�䣺 2016-9-5 ����10:25:03 
 */
public class YdxxAction extends SuperAction<YdxxForm,YdxxService>{
	private static final String url = "ydxxgl.do";//���ݿ����
	YdxxService service = new YdxxService();
	/**
	 * 
	 * @����:��ѯ����
	 * @���ڣ�2016-9-5����10:44:55
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward getYdlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdxxForm model = (YdxxForm)form;
		request.setAttribute("path", url);
		model.setPath(url);
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
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getYdlist");
	}
	/**
	 * 
	 * @����:����
	 * @���ߣ�caopei[���ţ�1352]
	 * @���ڣ�2016-9-5 ����03:20:59
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	
	public ActionForward ydxxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdxxForm myForm = (YdxxForm) form;
		if(StringUtils.isNotNull(myForm.getBz())){
			   myForm.setBz(URLDecoder.decode((URLDecoder.decode(myForm.getBz(),"UTF-8")),"UTF-8"));
			}
		User user = getUser(request);
		myForm.setJlr(user.getUserName());
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean isExist = service.isExistQysj(myForm);
	    	if (!isExist) {
	    		boolean flag = service.saveDataYdxx(myForm);
	        	String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
	    	}else {
	    		response.getWriter().print(getJsonMessageByKey(MessageKey.GYGL_YDGL_ADD_EXIST));
	    	}
				return null;
		}
		//�õ���½�û�Ȩ���ڵ�¥���б�
		List<HashMap<String, String>> ldlist =  service.getLdxxList(myForm);
		request.setAttribute("ldList",ldlist);
		request.setAttribute("path", url);
		return mapping.findForward("ydxxAdd");
	}
	public ActionForward getCws(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		String cws =(String) service.getCwhForQs(lddm, qsh);
		Map<String,String> map = new HashMap<String, String>();
		map.put("cws", cws);
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
	/**
	 * @����:�������Ҳ鿴
	 * @���ߣ�caopei[���ţ�1352]
	 * @���ڣ�2016-9-6 ����04:32:03
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
	public ActionForward ydxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdxxForm model = (YdxxForm) form;
		YdxxForm myForm = service.getModel(model);
		String ldqsxx = myForm.getLddm()+myForm.getQsh();
		Map<String, String> rs = service.getQsForPk(ldqsxx);// ������ϸ��Ϣ
		List<String[]> list = service.getCwForQs1(ldqsxx);//��ȡ����ѧ����Ϣ
		request.setAttribute("rs", rs);
		request.setAttribute("list", list);
		request.setAttribute("ydyf", myForm.getYdyf());
		request.setAttribute("lddm", myForm.getLddm());
		request.setAttribute("qsh", myForm.getQsh());
		request.setAttribute("ds", myForm.getDs());
		request.setAttribute("df", myForm.getDf());
		request.setAttribute("dfye", myForm.getDfye());
		request.setAttribute("bz", myForm.getBz());
		return mapping.findForward("ydxxView");
	}
	/**
	 * @����:����ҳ�����Һţ�����ȡ��λ��ѧ����Ϣ
	 * @���ߣ�caopei[���ţ�1352]
	 * @���ڣ�2016-9-6 ����08:37:50
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
	public ActionForward getCwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ldqsxx = request.getParameter("ldqsxx");//lddm+qsh
		Map<String, String> rs = service.getQsForPk(ldqsxx);// ������ϸ��Ϣ
		List<HashMap<String, String>> list = service.getCwForQs(ldqsxx);//��ȡ����ѧ����Ϣ
		request.setAttribute("rs", rs);
		request.setAttribute("list", list);
		JSONArray json = JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @����:�޸Ľ������Ϣ
	 * @���ߣ�caopei[���ţ�1352]
	 * @���ڣ�2016-9-6 ����11:52:41
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
	public ActionForward ydxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdxxForm model = (YdxxForm) form;
		YdxxForm myForm = service.getModel(model);
		User user = getUser(request);
		myForm.setJlr(user.getUserName());
		request.setAttribute("ydxxid", myForm.getYdxxid());
		String ldqsxx = myForm.getLddm()+myForm.getQsh();
		// ������ϸ��Ϣ
		Map<String, String> rs = service.getQsForPk(ldqsxx);
		//��ȡ����ѧ����Ϣ
		List<String[]> list = service.getCwForQs1(ldqsxx);

		request.setAttribute("rs", rs);
		request.setAttribute("list", list);
	//--------------------------------------------------------------------	
		//�õ���½�û�Ȩ���ڵ�¥���б�
		List<HashMap<String, String>> rs1 =  service.getLdxxList(myForm);
		request.setAttribute("ldList",rs1);
		request.setAttribute("ydyf", myForm.getYdyf());
		request.setAttribute("lddm", myForm.getLddm());
		request.setAttribute("qsh", myForm.getQsh());
		request.setAttribute("ds", myForm.getDs());
		request.setAttribute("df", myForm.getDf());
		request.setAttribute("dfye", myForm.getDfye());
		request.setAttribute("bz", myForm.getBz());
		return mapping.findForward("ydxxUpdate");
		
	}
	
	/**
	 * @����:�޸�
	 * @���ߣ�caopei[���ţ�1352]
	 * @���ڣ�2016-9-6 ����02:35:01
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward ydxxUpdatesv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YdxxForm myForm = (YdxxForm) form;
		if(StringUtils.isNotNull(myForm.getBz())){
			   myForm.setBz(URLDecoder.decode((URLDecoder.decode(myForm.getBz(),"UTF-8")),"UTF-8"));
			}
		User user = getUser(request);
		myForm.setJlr(user.getUserName());
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
				boolean flag = service.updateDataYdxx(myForm);
				String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("path", url);
		return mapping.findForward("getYdlist");
		
	}
	
	/**
	 * @����:ɾ��
	 * @���ߣ�caopei[���ţ�1352]
	 * @���ڣ�2016-9-6 ����02:45:12
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	
	public ActionForward ydxxDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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
	 * @����:����
	 * @���ߣ�caopei[���ţ�1352]
	 * @���ڣ�2016-9-6 ����04:03:42
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
		YdxxForm model=(YdxxForm) form;
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
	/**
	 * @����:����¥����Ϣ
	 * @���ߣ�caopei[���ţ�1352]
	 * @���ڣ�2016-9-6 ����05:01:48
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
	public ActionForward loadLdxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		LdglServices service = new LdglServices();
		LdglModel model = new LdglModel();
		model.setLddm(lddm);
		Map<String, String> map = service.getLdxxOne(model);
		String json = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);

		return null;
	}
	
	
	
	/**
	 * ��ȡ���Һ�ͨ��¥�����룬Ϊajax����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQshForLddm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String ch = request.getParameter("ch");
		List<HashMap<String, String>> list = service.getQshForLd(lddm, ch);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
}
