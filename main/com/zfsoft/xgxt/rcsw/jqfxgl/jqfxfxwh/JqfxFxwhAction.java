 
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxfxwh;

import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
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
import com.zfsoft.xgxt.rcsw.jqfxgl.jqfxjbsz.JqfxJbszForm;
import com.zfsoft.xgxt.rcsw.jqfxgl.jqfxjbsz.JqfxJbszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;




/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�lgx[����:1553]
 * @ʱ�䣺 2018-4-20 ����04:02:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
@SuppressWarnings("unchecked")
public class JqfxFxwhAction extends SuperAction{
	
	//���ڷ�Уά��
	private static final String RCSW_JQFXGL_FXWH = "rcsw_jqfxgl_fxwh";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSW_JQFXGL_FXWH);	
	}			
	private static final String url = "rcsw_jqfxgl_fxwh.do";
	
	public static String JQFXWCL = "δ����";
	public static String JQFXWFX = "δ��У";
	public static String JQFXYFX = "�ѷ�У";
	

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
 	 * @���ߣ�lgx[����:1553]
 	 * @ʱ�䣺 2018-4-20 ����04:02:43 
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
	public ActionForward jqfxwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JqfxFxwhForm model = (JqfxFxwhForm) form;
		JqfxFxwhService service = new JqfxFxwhService();		
	
		if (QUERY.equals(model.getType())) {			
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			//��ѯ��ȡ����ά���������
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
			
		}
		//------------------���ø߼���ѯĬ��ֵ-------------
		SearchModel searchModel= model.getSearchModel();
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		request.setAttribute("searchTj",  searchModel);
		String path = "rcsw_jqfxgl_fxwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		
		//-----------------��ȡ�������ÿ��عر����-------------------
		JqfxJbszService jqfxJbszService = new JqfxJbszService();
		JqfxJbszForm jbszModel = jqfxJbszService.getModel();
		request.setAttribute("jbszModel", jbszModel);
		
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", "01".equals(Base.currXq)?"��":"��");	
		request.setAttribute("fxmc", service.getFxmc()); 
		return mapping.findForward("rcswjqfxwh");
	}
	
	

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:43 
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
	public ActionForward addxsJqfx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			JqfxFxwhForm model = (JqfxFxwhForm) form;
			JqfxFxwhService service = new JqfxFxwhService();
			if (!StringUtil.isNull(model.getXh())) {
				// ����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			request.setAttribute("jbxxList", jbxxList);	
			if (SAVE.equalsIgnoreCase(model.getType())){				
				// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
				/*boolean isExist = false;
				if (!isExist) {		*/												
					// ����ճ���Ϊ���
					model.setTbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss") );
					boolean result = service.saveJqFxwh(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;					
				/*} else {					
					response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
					return null;					
				}*/
			}			
			JqfxFxwhForm jqfxJg = service.getModel(model.getId());
			if(jqfxJg != null){
				BeanUtils.copyProperties(StringUtils.formatData(jqfxJg), model);
			}
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", "01".equals(Base.currXq)?"��":"��");	
			request.setAttribute("fxztmc",  "1".equals(model.getFxzt())?JQFXYFX:JQFXWCL);
			request.setAttribute("tbsj" , GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss") );
			if("0".equals(model.getFxzt())){
				request.setAttribute("fxztmc",JQFXWFX);
			}								
			return mapping.findForward("addxsJqfx");			
	}
	
	
	/**
	 * 
	 * @����:TODO(ѧ������δ��У)
 * @���ߣ�lgx[����:1553]
 * @ʱ�䣺 2018-4-20 ����04:02:43 
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
	public ActionForward addxsJqwfx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
			JqfxFxwhForm model = (JqfxFxwhForm) form;
			JqfxFxwhService service = new JqfxFxwhService();
			User user = getUser(request);
			if (!StringUtil.isNull(model.getXh())){
				// ����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}	
			request.setAttribute("jbxxList", jbxxList);	
			
			if (SAVE.equalsIgnoreCase(model.getType())){				
				// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
			/*	boolean isExist = false;
				if (!isExist){	
					*/
					boolean result = service.saveJqWfxwh(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
					
				/*}else{					
					response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
					return null;					
				}*/
			}			
			List<HashMap<String, String>> wfxyyList = service.getAllWfxyy();
			request.setAttribute("wfxyyList", wfxyyList);
			//request.setAttribute("rs", StringUtils.formatData(service.getOneFxgljgList(model.getXh())));
			//��ȡδ��У��Ϣ��¼
			request.setAttribute("wfxxsrs", StringUtils.formatData(service.getOneWfxxwhjgList(model.getXh())));
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", "01".equals(Base.currXq)?"��":"��");	
			request.setAttribute("fxztmc",  "0".equals(model.getFxzt())?JQFXWFX:JQFXWCL);	
			if("1".equals(model.getFxzt())){
				request.setAttribute("fxztmc",JQFXYFX);	
			}
			
			//�����ѧ���Ѿ���δ��У��δ��Уԭ��ע
			if("0".equals(model.getFxzt())||"1".equals(model.getFxzt())){		
				JqfxFxwhForm updateForm = service.getModel(model);
				BeanUtils.copyProperties( StringUtils.formatData(updateForm), model);
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dateString = formatter.format(new Date());
			request.setAttribute("nowDate",dateString);
			return mapping.findForward("addxsJqwfx");			
	}
	
	/**
	 * 
	 * @����:TODO(�����޸�ѧ�����ڷ�Уά��)
	 * @���ߣ�lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:43 
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
	public ActionForward plxsJqfx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxFxwhForm model = (JqfxFxwhForm) form;
		JqfxFxwhService service = new JqfxFxwhService();		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);				
		User user = getUser(request);
		
		if (SAVE.equalsIgnoreCase(model.getType())) {			
			// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
		/*	boolean isExist = false;
			if (!isExist) {	*/				
				//model.setXq("��".equals(model.getXq())?"01":"02");	
				model.setBz( URLDecoder.decode(model.getBz() , "utf-8"));
				boolean result = service.plSaveJqwh(model,user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;					
		/*	} else {					
				response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
				return null;					
			}*/
		}
		List<HashMap<String, String>> wfxyyList = service.getAllWfxyy();
		request.setAttribute("wfxyyList", wfxyyList);
		
		//String countNum =  (String) request.getAttribute("countNum");
		request.setAttribute("countNum", model.getCountNum()); 		
		return mapping.findForward("addPlxsJqfx");		
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:43 
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
	public ActionForward getCountNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxFxwhForm model = (JqfxFxwhForm) form;
		JqfxFxwhService service = new JqfxFxwhService();
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);				
		User user = getUser(request);
		String countNum =  service.getCountNum(model,user);
		
		response.getWriter().print(countNum);
		//request.setAttribute("countNum",countNum); 		
		return null;		
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:43 
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
	public ActionForward pldgxsJqfx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
						
		
		JqfxFxwhForm model = (JqfxFxwhForm) form;
		JqfxFxwhService service = new JqfxFxwhService();						
		
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {				
			// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
			/*boolean isExist = false;
			if (!isExist) {	*/	
				model.setBz( URLDecoder.decode(model.getBz() , "utf-8"));
				boolean result = service.pldgSaveJqwh(model,user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;					
			/*} else {					
				response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
				return null;					
			}*/
		}
		
		request.setAttribute("countNum", model.getCountNum()); 	
		request.setAttribute("pldgxsJqfx", "1"); 
		return mapping.findForward("addPlxsJqfx");	
		
		
		
		
	}
	
	

	

	
	/**
	 * 
	 * @����:TODO(�鿴ѧ�����ڷ�У)
	 * @���ߣ�lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:43 
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
	public ActionForward viewxsJqfx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			JqfxFxwhForm model = (JqfxFxwhForm) form;
			JqfxFxwhService service = new JqfxFxwhService();
			User user = getUser(request);
			if (!StringUtil.isNull(model.getXh())) {
				// ����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}	
			request.setAttribute("jbxxList", jbxxList);	
			//request.setAttribute("rs", service.getOneFxgljgList(model.getXh()));
			//��ȡδ��У��Ϣ��¼
			Map<String, String> wfxxx = service.getOneWfxxwhjgList(model.getXh());
			request.setAttribute("wfxxsrs", wfxxx);
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", "01".equals(Base.currXq)?"��":"��");	
			
			if(!"null".equals(model.getId())){
				JqfxFxwhForm updateForm = service.getModel(model);
				String wfxyymc = "";
				if(StringUtil.isNull(updateForm.getWfxyy()))
					wfxyymc = service.getWfxyyMc(updateForm.getWfxyy());
				model.setWfxyy(wfxyymc);
				BeanUtils.copyProperties( StringUtils.formatData(updateForm), model);
			}
			request.setAttribute("fxsj", model.getFxsj());
			request.setAttribute("fxzt", model.getFxzt());
			String sfqdlx = model.getSfqdlx();
			if( "0".equals(model.getFxzt())){
				if(StringUtil.isNull(sfqdlx))  
					sfqdlx="��";
			}else{
				sfqdlx="";
			}
			
			request.setAttribute("sfqdlx",sfqdlx);
			return mapping.findForward("viewxsJqfx");				
	}	
	
	/**
	 * 
	 * @����:TODO(�Զ��嵼��)
	 * @���ߣ�lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:43 
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//ActionForm
		JqfxFxwhForm model = (JqfxFxwhForm) form;
		JqfxFxwhService service = new JqfxFxwhService();

		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//��ѯ�����м�¼������ҳ
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
	 * 
	 * @����:TODO(ѧ����������δ��У)
	 * @���ߣ�lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����04:02:43 
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
	public ActionForward addplxsJqwfx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		
			JqfxFxwhForm model = (JqfxFxwhForm) form;						
			JqfxFxwhService service = new JqfxFxwhService();
			User user = getUser(request);					
			if (SAVE.equalsIgnoreCase(model.getType())){				
				// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
				/*boolean isExist = false;
				if (!isExist){		*/			
					boolean result = service.saveplxsJqWfxwh(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;					
				/*}else{					
					response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
					return null;					
				}*/
			}		
			List<HashMap<String, String>> wfxyyList = service.getAllWfxyy();
			request.setAttribute("wfxyyList", wfxyyList);
			return mapping.findForward("addplxsJqwfx");			
	}

	/**
	 * @����:�������ͳ�Ʊ�
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/6/19 13:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [mapping, form, request, response]
	 * @return: org.apache.struts.action.ActionForward
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward bdqktjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqfxFxwhForm model = (JqfxFxwhForm) form;
		JqfxFxwhService service = new JqfxFxwhService();
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getBdqktjList(model,user);
		HashMap<String,String> hashMap = new HashMap<String, String>();
		
		File file = service.getBdqktjFile(resultList);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
