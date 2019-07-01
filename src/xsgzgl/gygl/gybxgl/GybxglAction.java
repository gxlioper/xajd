package xsgzgl.gygl.gybxgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;

public class GybxglAction extends SuperAction {
	private static final String BXSQ = "bxsq";
	private static List<HashMap<String, String>> jbxxList = null;
	
	
	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(BXSQ);
	}
	/**
	 * ��Ԣ���޹�������Ա��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-�����������-{doType}PK:{primarykey_checkVal}")
	public ActionForward gybxglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_gybxgl_gybxgl.do");
		GybxglForm myForm = (GybxglForm) form;
		GybxglService service = new GybxglService();
		String doType = request.getParameter("doType");
		
		// ɾ������
		if("del".equalsIgnoreCase(doType)){
			String message = service.delGybx(myForm) ? "ɾ���ɹ���" : "ɾ��ʧ�ܣ�" ;
			request.setAttribute("message", message);
		}
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		
		// �������ʾ�ֶ�
		String[] cn = new String[]{"¥��","����","��λ","ѧ��","����","����ʱ��","��������","ά��ʱ��","�����̶�","�������","����״̬","����̶�","ά����Ա","ά�޷���"};
		String[] en = new String[]{"pk","ldmc","qsh","cwh","xh","xm","bxsj","bxnr","wxsj","jjcd","bxlbmc","clzt","mycd","wxry","wxfy","subbxnr"};

		if("1103202".equals(Base.xxdm)){
			cn = new String[]{"¥��","����","��λ","ѧ��","����","����ʱ��","��������","ά��ʱ��","�����̶�","�������","����״̬","ά����Ա","ά�޷���"};
		}

		User user = getUser(request);
        myForm.setYhm(user.getUserName());
		// =============== ִ�в�ѯ���� ===========
		rsArrList = service.getGybxglInfoList(myForm,en,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("topTr", service.getToplist(cn));
		
		FormModleCommon.commonRequestSet(request);	//��ʾtitle
		// ================= end =====================
		
		//ѧУ���Ի�
		if("10596".equalsIgnoreCase(Base.xxdm)){
			return new ActionForward("/xsgzgl/gygl/gybxgl/xxgxh/gybxglManage_"+Base.xxdm+".jsp",false);
		}
			return mapping.findForward("gybxglManage");
		
	}
	
	/**
	 *  ��Ԣ���޹��� �Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	
	public ActionForward gybxglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		GybxglForm model = (GybxglForm) form;
		if("bxfp".equals(model.getFlag())){
			request.setAttribute("path", "gyglnew_gybxgl_gybxgl_bxfp.do");
		}else{
			request.setAttribute("path", "gyglnew_gybxgl_gybxgl.do");
		}
		GybxglService service = new GybxglService();
		
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);		
		User user = getUser(request);
		
		String[] en = new String[]{"pk","ldmc","qsh","cwh","xh","xm","bxsj","bxnr","wxsj","jjcd","bxlbmc","bxlbzxmc","clzt","mycd"};
		List<HashMap<String,String>> resultList = service.getGybxglInfoExportList(model,en,request);
		
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
	 * ��Ԣ���޹���ѧ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-�����������-{doType}ά��PK:{primarykey_checkVal}")
	public ActionForward gybxglStudent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_gybxgl_gybxgl_stu.do");
		GybxglForm myForm = (GybxglForm) form;
		GybxglService service = new GybxglService();
		User user = getUser(request);// �û�����
		String doType = request.getParameter("doType");
		
		// ���۲���
		if("pj".equalsIgnoreCase(doType)){
			String message = service.pjUpdate(myForm) ? "���۳ɹ���" : "����ʧ�ܣ�" ;
			request.setAttribute("message", message);
		}
		// ɾ������
		if("del".equalsIgnoreCase(doType)){
			String message = service.delGybx(myForm) ? "ɾ���ɹ���" : "ɾ��ʧ�ܣ�" ;
			request.setAttribute("message", message);
		}
		
		List<String[]> rsArrList = new ArrayList<String[]>();
		
		// �������ʾ�ֶ�
		String[] cn = new String[]{"����ʱ��","��������","�����̶�","�������","�����������","����ά��ʱ��","ʵ��ά��ʱ��","����״̬","����̶�"};
		String[] en = new String[]{"pk","xh","bxsj","bxnr","jjcd","bxlbmc","bxlbzxmc","qwwxsj","wxsj","clzt","mycd","subbxnr"};

		if("1103202".equals(Base.xxdm)){
			cn = new String[]{"����ʱ��","��������","�����̶�","�������","�����������","����ά��ʱ��","ʵ��ά��ʱ��","����״̬"};
		}

		// =============== ִ�в�ѯ���� ===========
		GyglNewService gyglNewService = new GyglNewService();
		String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request); // ��Ԣ����Ա���ݷ�Χ��������
		myForm.setXh(user.getUserName());
		rsArrList = service.getGybxglSelfList(myForm, en, user, searchTjByGyfdy);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());	//������
		request.setAttribute("topTr", service.getToplist(cn));
		FormModleCommon.commonRequestSet(request);	//��ʾtitle
		// ================= end =====================

		//ѧУ���Ի�
		if("10596".equalsIgnoreCase(Base.xxdm)){
			return new ActionForward("/xsgzgl/gygl/gybxgl/xxgxh/gybxglStudent_"+Base.xxdm+".jsp",false);
		}
		
		return mapping.findForward("gybxglStudent");
	}
	/**
	 * 
	 * @����:������۰�ť����������ʽ�޸�
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-23 ����12:25:07
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
	
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-�����������-����PK:{idList}")
	public ActionForward gybxglStudentpj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GybxglForm myForm = (GybxglForm) form;
		GybxglService service = new GybxglService();
		String doType = request.getParameter("doType");
		String idList = request.getParameter("idList");
		idList.substring(0, idList.length()-1);
		request.setAttribute("idList", idList);
		List<HashMap<String,String>> mydList = new OptionUtil().getOptions(OptionUtil.GYBX_MYD); // �����
		request.setAttribute("mydList", mydList);
		// ���۲���
		if("pj".equalsIgnoreCase(doType)){
			myForm.setPrimarykey_checkVal(idList.split(","));
			String message = service.pjUpdate(myForm) ? "���۳ɹ���" : "����ʧ�ܣ�" ;
			request.setAttribute("message", message);
		}

		return mapping.findForward("gybxglPj");
	}
	
	
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-�����������-����XH:{xh},JJCD:{jjcd},LXDH:{lxdh},BXNR:{bxnr}")
	public ActionForward gybxglAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);// �û�����
		String doType = request.getParameter("doType");
		GybxglForm myForm = (GybxglForm) form;
		// �����Ƿ������������ж������ӻ����޸�
		String pk = myForm.getPk();
		if(StringUtils.isNotNull(pk)){
			request.setAttribute("type", "update");
		}
		
		GybxglService service = new GybxglService();
		String xh = "stu".equals(user.getUserType()) ? user.getUserName() : myForm.getXh();
		if (!StringUtil.isNull(xh) || !StringUtil.isNull(pk)){
			HashMap<String,String> xsjbxx = service.viewXsxx(pk, xh);
			request.setAttribute("jbxx", xsjbxx);
			myForm.setXh(xh);
		}
		
		if(SAVE.equalsIgnoreCase(doType)){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			String messageKey = "";
    		boolean result = false;
    		if(StringUtils.isNull(pk)){
    			result = service.gybxglAdd(myForm);
    		}else if(StringUtils.isNotNull(pk)){
    			result = service.gybxglModi(myForm);
    		}
    		messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		List<HashMap<String, String>> bxlbList = service.getBxlbList();
		request.setAttribute("bxlbList",bxlbList);
		request.setAttribute("bxlbzxList", new ArrayList<HashMap<String,String>>());
		request.setAttribute("path", "gyglnew_gybxgl.do?method=gybxglAdd");
		request.setAttribute("jbxxList", jbxxList);
		this.saveToken(request);
		return mapping.findForward("gybxglAdd");
	}
	
	
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-�����������-�޸�PK:{pk},JJCD:{jjcd},LXDH:{lxdh},BXNR:{bxnr}")
	public ActionForward gybxglUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType = request.getParameter("doType");
		GybxglForm myForm = (GybxglForm) form;
		User user = getUser(request);
		
		GybxglService service = new GybxglService();
		
		String xh = myForm.getXh();
		String pk = myForm.getPk();
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		
		// �������
		if("save".equalsIgnoreCase(doType)){
			String message = service.gybxglUpdate(myForm) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}
	
		request.setAttribute("rs",service.viewXsxx(pk, xh));
		
		return mapping.findForward("gybxglUpdate");
	}
	
	
	public ActionForward gybxglView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType = request.getParameter("doType");
		GybxglForm myForm = (GybxglForm) form;
		User user = getUser(request);
		
		GybxglService service = new GybxglService();
		
		String xh = myForm.getXh();
		String pk = myForm.getPk();
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		
		// �������
		if("save".equalsIgnoreCase(doType)){
			String message = service.gybxglUpdate(myForm) ? "�޸ĳɹ���" : "�޸�ʧ�ܣ�";
			request.setAttribute("message", message);
		}
	
		request.setAttribute("rs",StringUtils.formatData(service.viewXsxx(pk, xh)));
		
		return mapping.findForward("gybxglView");
	}
	
	
	public ActionForward viewXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);// �û�����
		String xh = user.getUserName();
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		GybxglService service = new GybxglService();

		Map<String, String> map = service.viewXsxx(xh);
		String json = JSONObject.fromObject(map).toString();	
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	/** 
	 * @����:�˵���ת��ָ����ַ�����ݴ�ѧ���ƣ�
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-31 ����03:51:58
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
	
	public ActionForward gybxglMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("gybxglMenu_"+Base.xxdm);
	}
	/**
	 * 
	 * @����:��Ԣ�����������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-5 ����01:55:02
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
	
	public ActionForward getBxlbzxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GybxglService service = new GybxglService();
		String bxlb = request.getParameter("bxlb");
		List<HashMap<String,String>> jllbList = service.getBxlbzxList(bxlb);
		String json = JSONArray.fromCollection(jllbList).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
		
	}
	
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-�����������-ɾ��PK:{primarykey_checkVal}")
	public ActionForward gybxglManageBxfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setAttribute("path", "gyglnew_gybxgl_gybxgl_bxfp.do");
		GybxglForm myForm = (GybxglForm) form;
		GybxglService service = new GybxglService();
		String flag = (String)request.getAttribute("flag");
		String doType = request.getParameter("doType");
		
		// ɾ������
		if("del".equalsIgnoreCase(doType)){
			String message = service.delGybx(myForm) ? "ɾ���ɹ���" : "ɾ��ʧ�ܣ�" ;
			request.setAttribute("message", message);
		}
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		
		// �������ʾ�ֶ�
		String[] cn = new String[]{"¥��","����","��λ","ѧ��","����","����״̬","���䲿��","����ʱ��","ά��ʱ��","�����̶�","�������"};
		String[] en = new String[]{"pk","ldmc","qsh","cwh","xh","xm","fpztmc","fpbmmc","bxsj","wxsj","jjcd","bxlbmc","mycd","fpzt","clzt"};
        User user = getUser(request);
        myForm.setYhm(user.getUserName());
        myForm.setFlag("bxfp");
		// =============== ִ�в�ѯ���� ===========
		rsArrList = service.getGybxglInfoList(myForm,en,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("topTr", service.getToplist(cn));
		
		FormModleCommon.commonRequestSet(request);	//��ʾtitle
		// ================= end =====================
		
		//ѧУ���Ի�
		if("10596".equalsIgnoreCase(Base.xxdm)){
			return new ActionForward("/xsgzgl/gygl/gybxgl/xxgxh/gybxglManage_"+Base.xxdm+".jsp",false);
		}
		return mapping.findForward("gybxglManagebxfp");
	}
	
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ����-���޷������-�޸�PK:{pk},JJCD:{jjcd},LXDH:{lxdh},BXNR:{bxnr}")
	public ActionForward gybxglfpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType = request.getParameter("doType");
		GybxglForm myForm = (GybxglForm) form;
		User user = getUser(request);
		
		GybxglService service = new GybxglService();
		
		String xh = myForm.getXh();
		String pk = myForm.getPk();
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		
		// �������
		if("save".equalsIgnoreCase(doType)){
			String message = service.gybxglfpUpdate(myForm) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		if("12688".equals(Base.xxdm)){
			request.setAttribute("xqlist", service.getfpxqList());
		}
		request.setAttribute("rs",service.viewXsxx(pk, xh));
		request.setAttribute("bmlist", service.getfpbmList());
		
		return mapping.findForward("gybxglfpUpdate");
	}
	
	public ActionForward gybxglfpView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType = request.getParameter("doType");
		GybxglForm myForm = (GybxglForm) form;
		User user = getUser(request);
		
		GybxglService service = new GybxglService();
		
		String xh = myForm.getXh();
		String pk = myForm.getPk();
		
		if ("stu".equals(user.getUserType())){
			xh = user.getUserName();
		}
		
		// �������
		if("save".equalsIgnoreCase(doType)){
			String message = service.gybxglUpdate(myForm) ? "�޸ĳɹ���" : "�޸�ʧ�ܣ�";
			request.setAttribute("message", message);
		}
	
		request.setAttribute("rs",StringUtils.formatData(service.viewfpXsxx(pk, xh)));
		
		return mapping.findForward("gybxglfpView");
	}
}
