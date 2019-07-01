package xsgzgl.qgzx.jfgl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
/**
 * �ڹ���ѧ-�ڹ����ѹ���-������Ϣ����
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxJfglAction extends BasicAction{
	/**
	 * ������Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jfxxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_nd(new String[]{Base.currNd});
		searchModel.setPath("qgzx_jfgl_jfxxgl.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_jfgl_jfxxgl.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			request.setAttribute("tableName", "view_xg_qgzx_jfhbb_yf");
		}else{
			request.setAttribute("tableName", "view_xg_qgzx_jfhbb_nd");
		}
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_qgzx_jfhbb");
		if("no".equalsIgnoreCase(qgzxCsszService.getCssz().get("sfjfhb"))){
			String msg = "��ǰ������������ͨ�����ѻ������Ƴ�𷢷ţ���ģ������ά����";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		//��֤�Ƿ����ڹ�����Ա
		QgzxGlyglService qgzxGlyglService=new QgzxGlyglService();
		boolean sfQggly=qgzxGlyglService.sfQggly(user.getUserName());
		if(!sfQggly){
			String msg = "��ģ��������ڹ�����Ա�û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("jfhbfs", new QgzxCsszService().getJfhbfs());
		
		return mapping.findForward("jfxxCx");
	}
	
	public ActionForward jfxxInit(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		QgzxJfglService service = new QgzxJfglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		rForm.setPath("qgzx_jfgl_jfxxgl.do");
		HashMap<String, String> rs = service.getMrcs(user);
		service.setRequestValue(rForm, user, request);
		QgzxJfglForm myForm =  (QgzxJfglForm)form;
		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList());
		myForm.setXn(Base.currXn);
		request.setAttribute("ndList", Base.getXnndList());
		//ȡ��ǰѧ������˲����б�
		String qgzq = QgCommUtilf.getQgzq();
		String xq = "xq".equals(qgzq) ? Base.currXq : null;
		request.setAttribute("bmList", service.getBms(Base.currXn,xq));
		request.setAttribute("nowTime",GetTime.getNowTime2());
		request.setAttribute("rs", rs);
		request.setAttribute("jfhbfs", new QgzxCsszService().getJfhbfs());
		this.saveToken(request);

		return mapping.findForward("jfxxInit");
	}
	
	/**
	 * ������Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jfxxZj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		QgzxJfglService service = new QgzxJfglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		
		rForm.setPath("qgzx_jfgl_jfxxgl.do");
		HashMap<String, String> rs = service.getMrcs(user);
		service.setRequestValue(rForm, user, request);
		QgzxJfglForm myForm =  (QgzxJfglForm)form;
		// ѧ�� ѧ��
		request.setAttribute("xnList", Base.getXnndList2());
		myForm.setXn(Base.currXn);
		request.setAttribute("ndList", Base.getXnndList());
		String qgzq = QgCommUtilf.getQgzq();
		String xq = "xq".equals(qgzq) ? Base.currXq : null;
		myForm.setXq(xq);
		myForm.setNd(Base.currNd);
		//ȡ��ǰѧ������˲����б�
		request.setAttribute("bmList", service.getBms(Base.currXn,xq));
		request.setAttribute("nowTime",GetTime.getNowTime2() );
		request.setAttribute("rs", rs);
		request.setAttribute("jfhbfs", new QgzxCsszService().getJfhbfs());
		request.setAttribute("qgzq",qgzq);
		request.setAttribute("xqList", Base.getXqList());
		this.saveToken(request);
		return mapping.findForward("jfxxZj");
	}
	/**
	 * ������Ϣ�޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jfxxXg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = new QgzxJfglForm();
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("jfhbfs", new QgzxCsszService().getJfhbfs());
		myForm.setPkValue(pkValue);
		myForm.setDoType(doType);
		HashMap<String, String> rs = service.getJfxxMap(myForm);
		List<HashMap<String,String>> list = new QgzxJfglDAO().getJfxxList(myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("list", list);
		String qgzq = QgCommUtilf.getQgzq();
		request.setAttribute("qgzq", qgzq);
		//�����������ж�
		String colspan = "xq".equals(qgzq)? "6":"5";
		request.setAttribute("col", colspan);
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_jfgl_jfxxgl.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("jfxxXg");
	}
	
	
	/**============================������ڹ�Begin============================*/
	/**
	 * @����: ��ѯ�б�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-12-20 ����04:02:24
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
	public ActionForward jfhbxxList(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		QgzxJfglForm model = (QgzxJfglForm) form;
		QgzxJfglService service = new QgzxJfglService();
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
		
		String path = "qgzx_jfcjgl_jfhb_zjdx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jfhbxxList");
	}
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-19 ����09:50:00
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
	public ActionForward jfhbxxAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		QgzxJfglService service = new QgzxJfglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("qgzx_jfgl_jfxxgl.do");
		service.setRequestValue(rForm, user, request);
		//ȡ���list
		request.setAttribute("ndList", Base.getXnndList());
		//ȡ��ǰѧ������˲����б�
		request.setAttribute("bmList", service.getYrbm());
		//ȡϵͳ��ǰ��ʽ����ʽΪ
		request.setAttribute("nowTime",GetTime.getNowTime2());
		//������Ⱥ����˲��Ŵ���
		HashMap<String, String> rs = service.getXycs(user);
		request.setAttribute("rs", rs);
		this.saveToken(request);
		return mapping.findForward("jfhbxxAdd");
	}
	
	/**
	 * @����: ���ӱ�����֤
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-19 ����09:49:44
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
	public ActionForward checkBcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm model = (QgzxJfglForm) form;
		// ������������
		model.setBm(service.unicode2Gbk(model.getBm()));
		model.setHbsj(service.unicode2Gbk(model.getHbsj()));
		String message = service.checkBcInfo(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * @����: �޸ķ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-12-19 ����02:09:58
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
	public ActionForward jfhbxxUpdate(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = new QgzxJfglForm();
		RequestForm rForm = new RequestForm();
		//��Ϊ���ѻ������������������ִ�JS����һ������������
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		myForm.setPkValue(pkValue);
		myForm.setDoType(doType);
		HashMap<String, String> rs = service.getZdJfxxMap(myForm);
		request.setAttribute("rs", rs);
		User user = getUser(request);
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_jfcjgl_jfhb_zjdx.do");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("jfhbxxUpdate");
	}
	/**
	 * @����: �޸ı�����֤
	 * @���ߣ�  Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-19 ����04:51:12
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
	public ActionForward checkXgBcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = (QgzxJfglForm) form;
		// ������������
		myForm.setBm(service.unicode2Gbk(myForm.getBm()));
		myForm.setHbje(service.unicode2Gbk(myForm.getHbje()));
		String message = service.checkXgBcInfo(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * @����: �޸ı���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-19 ����04:44:25
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
	public ActionForward updateBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = (QgzxJfglForm) form;
		// ������������
		myForm.setBm(service.unicode2Gbk(myForm.getBm()));
		myForm.setHbsj(service.unicode2Gbk(myForm.getHbsj()));
		myForm.setHbje(service.unicode2Gbk(myForm.getHbje()));
		myForm.setBz(service.unicode2Gbk(myForm.getBz()));
		String message = service.jfxxXg(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/** 
	 * @����: �鿴����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-20 ����10:14:03
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
	public ActionForward jfhbxxView(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		QgzxJfglService service = new QgzxJfglService();
		QgzxJfglForm myForm = new QgzxJfglForm();
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("pkValue", pkValue);
		myForm.setPkValue(pkValue);
		HashMap<String, String> rs = service.getJfhbMap(myForm);
		request.setAttribute("rs", rs);
		User user = getUser(request);
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("qgzx_jfcjgl_jfhb_zjdx.do");
		service.setRequestValue(rForm, user, request);
		//��ѯ������ϸ�б�
		HashMap<String,String> ffmx = service.getFfmxList(myForm);
		request.setAttribute("ffmx", ffmx);
		return mapping.findForward("jfhbxxView");
	}
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-20 ����06:31:52
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
		QgzxJfglForm model = (QgzxJfglForm) form;
		QgzxJfglService service = new QgzxJfglService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.getPageList(model, user);//��ѯ�����м�¼������ҳ
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
	/**============================������ڹ�End============================*/
	
	/**
	 * @����: ���ƾ��ѻ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-20 ����06:31:52
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
	public ActionForward copyJfhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List<HashMap<String, String>> ndyfList = new QgzxJfglService().getSourceMonth();
		request.setAttribute("ndyfList", ndyfList);
		request.setAttribute("targetMonth", xgxt.utils.date.DateUtils.getCurrYearAndMonth2());
		if(!new QgzxJfglService().checkIsNotJfhbDataExist()){
			request.setAttribute("message", "��ǰ�·����о��ѻ������ݣ����ܽ��п���������");
			return mapping.findForward("error");
		}else{
			return mapping.findForward("copyjfhb");
		}
		
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-4-24 ����01:21:12
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
	public ActionForward saveJfhbCopy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String sourcemonth = request.getParameter("sourcemonth");
		String targetmonth = request.getParameter("targetmonth");
		boolean rs = new QgzxJfglService().copyJfhbData(targetmonth, sourcemonth);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
