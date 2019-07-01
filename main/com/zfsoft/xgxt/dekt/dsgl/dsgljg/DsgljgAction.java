/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.dekt.dsgl.dsgljg;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class DsgljgAction extends SuperAction{
	private static final String DEKT = "dekt";//rcswbxgl
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private static final String url = "xg_dekt_dsgljg.do";
	DsgljgService service = new DsgljgService();
	
	/**
	 * @description	�� ��ѯ
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-18 ����12:00:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	 
	@SystemAuth(url = url)
	public ActionForward dsglJgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DsgljgForm model = (DsgljgForm) form;
		if (QUERY.equals(model.getType())) {
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
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("dsjgcx");
		
	}
	
	
	
//	
//	/**
//	 * @description	�� ����
//	 * @author 		�� CP��1352��
//	 * @date 		��2018-1-18 ����01:49:01
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@SystemAuth(url = url)
//	public ActionForward add(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//			DsgljgForm model = (DsgljgForm) form;
//			if (!StringUtil.isNull(model.getXh())){
//				//����ѧ��������Ϣ
//				XsxxService xsxxService = new XsxxService();
//				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
//				request.setAttribute("jbxx", xsjbxx);
//			}
//		if (SAVE.equalsIgnoreCase(model.getType())) {
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			model.setSqsj(df.format(new Date()));
//			model.setXn(Base.currXn);
//			model.setXq(Base.currXq);
//			boolean isExist = service.isExist(model);
//        	if(!isExist){
//        		boolean result = service.runInsert(model);
//        		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
//				response.getWriter().print(getJsonMessageByKey(messageKey));
//        	}else{
//        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//        	}
//			return null;
//		}
//		//ѧ��������Ϣ��ʾ����
//		jbxxList = bdpzService.getJbxxpz(DEKT);
//		request.setAttribute("jbxxList", jbxxList);
//		//ѧ�� ѧ��
//		request.setAttribute("dqxn", Base.currXn);
//		request.setAttribute("dqxq", Base.getDqxqmc());
//		String path = "dekt_dsgljg.do?method=add";
//		request.setAttribute("path", path);
//		return mapping.findForward("dsjgzj");
//	}
//	
//	
//	/**
//	 * @description	�� �޸�
//	 * @author 		��CP��1352��
//	 * @date 		��2018-1-18 ����03:23:02
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@SystemAuth(url = url)
//	public ActionForward update(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//			DsgljgForm model = (DsgljgForm) form;
//			if (!StringUtil.isNull(model.getXh())){
//				//����ѧ��������Ϣ
//				XsxxService xsxxService = new XsxxService();
//				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
//				request.setAttribute("jbxx", xsjbxx);
//			}
//		if (UPDATE.equalsIgnoreCase(model.getType())) {
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			model.setSqsj(df.format(new Date()));
//			model.setXn(Base.currXn);
//			model.setXq(Base.currXq);
//			boolean isExist = service.isExist(model);
//        	if(!isExist){
//        		boolean result = service.updateDsxx(model);
//        		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
//				response.getWriter().print(getJsonMessageByKey(messageKey));
//        	}else{
//        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//        	}
//			return null;
//		}
//		//ѧ��������Ϣ��ʾ����
//		jbxxList = bdpzService.getJbxxpz(DEKT);
//		request.setAttribute("jbxxList", jbxxList);
//		//ѧ�� ѧ��
//		request.setAttribute("dqxn", Base.currXn);
//		request.setAttribute("dqxq", Base.getDqxqmc());
//		DsgljgForm updateForm = service.getModel(model);
//		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
//		String path = "dekt_dsgljg.do?method=update";
//		request.setAttribute("path", path);
//		return mapping.findForward("dsjgxg");
//	}
//	
//	
//	
//	
//	/**
//	 * @description	�� ɾ��
//	 * @author 		�� CP��1352��
//	 * @date 		��2018-1-18 ����03:23:25
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
//	public ActionForward del(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		String values = request.getParameter("values");
//		if (!StringUtil.isNull(values)) {
//			String[] mess = service.deleteDsxxjg(values.split(","));
//			if(null==mess||mess.length!=2){
//				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
//				response.getWriter().print(getJsonMessage(message));
//				return null;
//			}
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("num",mess[0]);
//			map.put("nodel",mess[1]);
//			JSONObject json = JSONObject.fromObject(map);
//			response.getWriter().print(json);
//		} else {
//			throw new SystemException(MessageKey.SYS_DEL_NULL);
//		}
//		return null;
//	}
//	
//	
//	/**
//	 * @description	�� �鿴
//	 * @author 		�� CP��1352��
//	 * @date 		��2018-1-18 ����03:23:35
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@SystemAuth(url = url)
//	public ActionForward view(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		DsgljgForm model = (DsgljgForm) form;
//		User user = getUser(request);
//		if ("stu".equals(user.getUserType())){
//			model.setXh(user.getUserName());
//		}
//		//����ѧ��������Ϣ
//		XsxxService xsxxService = new XsxxService();
//		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
//		request.setAttribute("jbxx", xsjbxx);
//		//��ѯ������Ϊ��Ϣ���
//		HashMap<String,String> gfqkMap = service.getDsxxInfo(model);
//		request.setAttribute("rs", StringUtils.formatData(gfqkMap));
//		request.setAttribute("gfqkfl", gfqkMap.get("gfqkfl"));
//		request.setAttribute("filepath", gfqkMap.get("filepath"));
//		//ѧ��������Ϣ��ʾ����
//		jbxxList = bdpzService.getJbxxpz(DEKT);
//		request.setAttribute("jbxxList", jbxxList);
//		request.setAttribute("model", StringUtils.formatData(model));
//		return mapping.findForward("dsjgck");
//	}
	
	/**
	 * @description	�� �Ķ�����
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-18 ����04:04:57
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewydxq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DsgljgForm model = (DsgljgForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//����ѧ��������Ϣ
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		//��ѯ�����Ķ�ͼ����Ϣ����
		List<String[]> ydlist = service.getYdxqInfo(model.getXh());//��ȡ�Ķ�
		request.setAttribute("list", ydlist);
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(DEKT);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("dsjgydxqck");
	}
	
	/**
	 * @description	�� ����
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-18 ����03:23:52
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
		DsgljgForm model = (DsgljgForm) form;
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
