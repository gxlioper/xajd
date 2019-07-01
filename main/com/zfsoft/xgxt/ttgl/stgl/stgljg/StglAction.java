/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stgljg;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.ttgl.stgl.stglsq.StglsqService;
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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import common.newp.StringUtil;

public class StglAction extends SuperAction{
	
	StglService service = new StglService();
	private static final String url = "xg_ttgl_stgl.do";

	/**
	 * @description	�� �����б�
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-30 ����10:33:32
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward stglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglForm model = (StglForm) form;
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		request.setAttribute("userType", getUser(request).getUserType());
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("stglList");
	}
	/**
	 * @description	�� ��������
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-31 ����10:33:13
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward addst(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			StglForm model = (StglForm) form;
			String[] xhArray = request.getParameterValues("xh");
			model.setXhArray(xhArray);
		if (SAVE.equalsIgnoreCase(model.getType())) {
            String[] jflyArray = model.getJflyArray();
            StringBuilder s = new StringBuilder();
            for(int i=0;i<jflyArray.length;i++){
                s.append(jflyArray[i]).append(",");
            }
            if (s.length() > 0) {
                model.setJfly(s.substring(0, s.length()-1));//������Դ��ѡ���ö��Ÿ��������浽���ݿ�
            }
			boolean isExist = service.isExist(model);
			if (!isExist) {
				boolean result = service.saveSt(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
	    	}else {
	    		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
	    	}
			return null;
		}
		List<HashMap<String, String>> bmList = service.getbmList();
        StglService stglService = new StglService();
        //ѧ����֯������Դ
        List<HashMap<String, String>> xszzjflyList = stglService.getXszzjflyList();
        //��ȡѧ����֯������Դ����
        List<HashMap<String, String>> ndzztList = stglService.getNdzzztList();
        //��ȡ��֯��𼯺�
        List<HashMap<String, String>> zzlbList = stglService.getZzlbList();

		request.setAttribute("bmList", bmList);
        request.setAttribute("xszzjflyList",xszzjflyList);
        request.setAttribute("ndzztList",ndzztList);
        request.setAttribute("zzlbList",zzlbList);
        //ѧ��list
        request.setAttribute("currXn", Base.currXn);
		String path = "ttgl_stgl.do?method=addst";
		request.setAttribute("path", path);
		return mapping.findForward("add");
	}

	public ActionForward selectStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setAttribute("path","ttgl_stgl.do?method=selectStu");
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String appandTableId = request.getParameter("appandTableId");
		request.setAttribute("appandTableId",appandTableId);
		return mapping.findForward("selectStu");
	}
	
	public ActionForward searchForStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		StglForm myForm = (StglForm)form;
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getStuCx(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	/**
	 * @description	�� �޸�����
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-31 ����01:48:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward updatest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglForm model = (StglForm) form;
		StglForm myForm = service.getModel(model);
		if (UPDATE.equalsIgnoreCase(model.getType())) {
			String[] xhArray = request.getParameterValues("xh");
			model.setXhArray(xhArray);
            String[] jflyArray = model.getJflyArray();
            StringBuilder s = new StringBuilder();
            for(int i=0;i<jflyArray.length;i++){
                s.append(jflyArray[i]).append(",");
            }
            if (s.length() > 0) {
                model.setJfly(s.substring(0, s.length()-1));//������Դ��ѡ���ö��Ÿ��������浽���ݿ�
            }
			boolean isExist = service.isExist(model);
			if (!isExist) {
				boolean result = service.saveSt(model);
				String message = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(message));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.SYS_TOKEN_VALID, false));
			}

			return null;
		}
		List<HashMap<String, String>> bmList = service.getbmList();
        StglService stglService = new StglService();
        //ѧ����֯������Դ
        List<HashMap<String, String>> xszzjflyList = stglService.getXszzjflyList();
        //��ȡѧ����֯������Դ����
        List<HashMap<String, String>> ndzztList = stglService.getNdzzztList();
        //��ȡ��֯��𼯺�
        List<HashMap<String, String>> zzlbList = stglService.getZzlbList();
		request.setAttribute("bmList", bmList);
        request.setAttribute("xszzjflyList",xszzjflyList);
        request.setAttribute("ndzztList",ndzztList);
        request.setAttribute("zzlbList",zzlbList);
		request.setAttribute("stlx", myForm.getStlx());
		request.setAttribute("zdls", service.getfdyxm(myForm.getStzdls()));
		request.setAttribute("zgh", myForm.getStzdls());
		request.setAttribute("fzrxxInfo", service.getFzrxx(model));
        request.setAttribute("tzsxxInfo",service.getTzsxx(model));
        //ѧ��list
        request.setAttribute("currXn", Base.currXn);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("xg");
	}
	/**
	 * @description	�� ɾ��
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-31 ����03:27:42
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteStxxjg(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @description	�� �鿴
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-31 ����03:48:11
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglForm myForm = (StglForm) form;
		HashMap<String,String> stxxMap = service.getStxxInfo(myForm);
		myForm.setSjly(stxxMap.get("sjly"));
        //ѧ����֯������Դ
        StglService stglService = new StglService();
        List<HashMap<String, String>> xszzjflyList = stglService.getXszzjflyList();
        request.setAttribute("xszzjflyList",xszzjflyList);
		request.setAttribute("rs", StringUtils.formatData(stxxMap));
		request.setAttribute("fzrxxInfo", service.getFzrxx(myForm));
        request.setAttribute("tzsxxInfo",service.getTzsxx(myForm));
		request.setAttribute("filepath", stxxMap.get("filepath"));
		return mapping.findForward("ck");
	}
	
	/**
	 * @description	�� ���ų�Ա��Ϣ
	 * @author 		�� CP��1352��
	 * @date 		��2018-2-9 ����11:45:23
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglForm myForm = (StglForm) form;
		List<HashMap<String,String>> cyList = service.getcyList(myForm);
		request.setAttribute("cyList", cyList);	
		return mapping.findForward("cyck");
	}
	/**
	 * @description	�� ����
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-31 ����03:47:45
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglForm model = (StglForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
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
 * @description	�� ����ע��
 * @author 		�� CP��1352��
 * @date 		��2018-2-6 ����03:22:10
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward stzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			StglForm model = (StglForm) form;
			boolean result = service.stzx(model);
			String messageKey = result ? MessageKey.SYS_ZX_SUCCESS : MessageKey.SYS_XZ_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));	
		return null;
	}
	
	
	

	
	
}
