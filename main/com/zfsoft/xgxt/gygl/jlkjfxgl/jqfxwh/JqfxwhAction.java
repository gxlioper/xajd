/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-29 ����09:27:42 
 */  
package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxwh;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
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
import com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz.JqfxjcszForm;
import com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz.JqfxjcszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ڷ�Уά������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-2-29 ����09:27:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
@SuppressWarnings("unchecked")
public class JqfxwhAction extends SuperAction{
	
	//���ڷ�Уά��
	private static final String JLKJXYJQFXWH = "jlkjxyjqfxwh";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(JLKJXYJQFXWH);	
	}			
	private static final String url = "jlkjxyjqfxwh.do";
	
	public static String JQFXWCL = "δ����";
	public static String JQFXWFX = "δ��У";
	public static String JQFXYFX = "�ѷ�У";
	

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-2-29 ����04:33:10
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

		JqfxwhForm model = (JqfxwhForm) form;
		JqfxwhService service = new JqfxwhService();		
	
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
		String path = "jlkjxyjqfxwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		
		//-----------------��ȡ�������ÿ��عر����-------------------
		JqfxjcszService jqfxjcszservice = new JqfxjcszService();
		JqfxjcszForm jcszModel = jqfxjcszservice.getModel();
		request.setAttribute("jcszModel", jcszModel);
		
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", "01".equals(Base.currXq)?"��":"��");	
		request.setAttribute("fxmc", service.getFxmc()); 
		return mapping.findForward("jlkjxyjqfxwh");
	}
	
	

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-4 ����05:25:09
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
		
			JqfxwhForm model = (JqfxwhForm) form;
			JqfxwhService service = new JqfxwhService();
			if (!StringUtil.isNull(model.getXh())) {
				// ����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			request.setAttribute("jbxxList", jbxxList);	
			if (SAVE.equalsIgnoreCase(model.getType())){				
				// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
				boolean isExist = false;
				if (!isExist) {														
					// ����ճ���Ϊ���
					model.setTbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss") );
					boolean result = service.saveJqFxwh(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;					
				} else {					
					response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
					return null;					
				}
			}			
			JqfxwhForm jqfxJg = service.getModel(model.getId());
			if(jqfxJg != null){
				BeanUtils.copyProperties(model, jqfxJg);
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
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-4 ����04:16:47
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
			JqfxwhForm model = (JqfxwhForm) form;
			JqfxwhService service = new JqfxwhService();
			@SuppressWarnings("unused")
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
				boolean isExist = false;
				if (!isExist){	
					
					boolean result = service.saveJqWfxwh(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
					
				}else{					
					response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
					return null;					
				}
			}			
			
			request.setAttribute("rs", StringUtils.formatData(service.getOneFxgljgList(model.getXh())));
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
				JqfxwhForm updateForm = service.getModel(model);
				BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
			}			
			return mapping.findForward("addxsJqwfx");			
	}
	
	/**
	 * 
	 * @����:TODO(�����޸�ѧ�����ڷ�Уά��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-7 ����09:06:19
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
		
		JqfxwhForm model = (JqfxwhForm) form;
		JqfxwhService service = new JqfxwhService();		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);				
		User user = getUser(request);
		
		if (SAVE.equalsIgnoreCase(model.getType())) {			
			// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
			boolean isExist = false;
			if (!isExist) {					
				//model.setXq("��".equals(model.getXq())?"01":"02");				
				boolean result = service.plSaveJqwh(model,user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;					
			} else {					
				response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
				return null;					
			}
		}
		
		
		//String countNum =  (String) request.getAttribute("countNum");
		request.setAttribute("countNum", model.getCountNum()); 		
		return mapping.findForward("addPlxsJqfx");		
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-22 ����12:02:08
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
		
		JqfxwhForm model = (JqfxwhForm) form;
		JqfxwhService service = new JqfxwhService();
		
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
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-18 ����02:15:19
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
						
		
		JqfxwhForm model = (JqfxwhForm) form;
		JqfxwhService service = new JqfxwhService();						
		
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {				
			// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
			boolean isExist = false;
			if (!isExist) {		
				boolean result = service.pldgSaveJqwh(model,user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;					
			} else {					
				response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
				return null;					
			}
		}
		
			request.setAttribute("countNum", model.getCountNum()); 	
		request.setAttribute("pldgxsJqfx", "1"); 
		return mapping.findForward("addPlxsJqfx");	
		
		
		
		
	}
	
	

	

	
	/**
	 * 
	 * @����:TODO(�鿴ѧ�����ڷ�У)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-4 ����04:57:15
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
		
			JqfxwhForm model = (JqfxwhForm) form;
			JqfxwhService service = new JqfxwhService();
			@SuppressWarnings("unused")
			User user = getUser(request);
			if (!StringUtil.isNull(model.getXh())) {
				// ����ѧ��������Ϣ
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}	
			request.setAttribute("jbxxList", jbxxList);	
			request.setAttribute("rs", StringUtils.formatData(service.getOneFxgljgList(model.getXh())));
			//��ȡδ��У��Ϣ��¼
			request.setAttribute("wfxxsrs", StringUtils.formatData(service.getOneWfxxwhjgList(model.getXh())));
			request.setAttribute("xn", Base.currXn);
			request.setAttribute("xq", "01".equals(Base.currXq)?"��":"��");	
			
			if(!"null".equals(model.getId())){
				JqfxwhForm updateForm = service.getModel(model);
				BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
			}
			request.setAttribute("fxzt", model.getFxzt());
			return mapping.findForward("viewxsJqfx");				
	}	
	
	/**
	 * 
	 * @����:TODO(�Զ��嵼��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-9 ����10:52:32
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
		JqfxwhForm model = (JqfxwhForm) form;
		JqfxwhService service = new JqfxwhService();

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
	 * @����:TODO(C���ó������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-14 ����06:42:22
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
	public ActionForward szCyyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);	
		JqfxwhService service = new JqfxwhService();
		List<HashMap<String,String>> cyyyList = service.getCyyyList(user);		
		request.setAttribute("cyyyList", cyyyList);
		return mapping.findForward("szCyyy");
		
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ����ԭ�򼯺�)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-15 ����11:35:03
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
	public ActionForward getCyyyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);	
		JqfxwhService service = new JqfxwhService();
		List<HashMap<String,String>> cyyjList = service.getCyyyList(user);		
		JSONArray json = JSONArray.fromObject(cyyjList);
		response.getWriter().print(json);
		
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(����δ��У����ԭ��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-15 ����11:32:23
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
	public ActionForward saveCyyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		String[] cyyy = request.getParameterValues("cyyy");	
		JqfxwhService service = new JqfxwhService();
		//����δ��У����ԭ��
		boolean result = service.saveCyyy(user, cyyy);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @����:TODO(ѧ����������δ��У)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-4 ����04:16:47
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
		
			JqfxwhForm model = (JqfxwhForm) form;						
			JqfxwhService service = new JqfxwhService();
			@SuppressWarnings("unused")
			User user = getUser(request);					
			if (SAVE.equalsIgnoreCase(model.getType())){				
				// Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
				boolean isExist = false;
				if (!isExist){					
					boolean result = service.saveplxsJqWfxwh(model);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;					
				}else{					
					response.getWriter().print(getJsonMessage(MessageKey.JQFXGL_JQFXWH_REPEAT));
					return null;					
				}
			}																
			return mapping.findForward("addplxsJqwfx");			
	}

}
