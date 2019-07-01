package xgxt.xsxx.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.xginfo.CommXgInfoService;
import xgxt.form.User;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.service.XsxxXjydglService;
import xgxt.xtwh.common.service.XtlcglService;

import com.zfsoft.basic.BasicAction;
import common.GlobalsVariable;

/** 
 * Creation date: 01-25-2011
 * author lr 
 */
public class XsxxXjydglAction extends BasicAction {

	/**
	 * ѧ���춯����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward xjydsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		XsxxglService xsxxglService = new XsxxglService();
		XsxxXjydglService service = new XsxxXjydglService();
		StudentInfoForm model = (StudentInfoForm)form;
		//�û���Ϣ
		User user = getUser(request);
		//����
		String tableName = "bks_xjydxx";
		//��ͼ��
		String viewName = "view_xjydjbxx";
		//����·��
		String path = "xjydsq.do";
		//����
		String pkValue = request.getParameter("pkValue");
		//ѧ�� 
		String xh = "stu".equals(user.getUserType()) //ѧ���û�ȡ�û���
					? user.getUserName() 
					: request.getParameter("xh");		
		//��������
		String oper = request.getParameter("oper");
		//����
		String doType = request.getParameter("doType");
		if("save".equals(doType)){//������Ϣ����
			this.insertOperation(request, tableName);
			pkValue = model.getSave_ydxh();//�춯���
			//��ʼ����˼�¼
			model.setYdxh(model.getSave_ydxh());
			model.setYdlbdm(request.getParameter("save_ydlbm"));
			service.initXjydshjl(model,user);
		}
		if("modify".equalsIgnoreCase(doType)){//������Ϣ�޸�
			//�ж��Ƿ���޸�
			if(service.sfkxgXjydxx(model.getSave_ydxh())){
				this.updateOperation(request, tableName);
			}else{
				request.setAttribute("result", false);
				request.setAttribute("message","�Ѿ�������У���ʱ�����޸ģ�");
			}
		}
		
		//��ѯѧ�����춯������Ϣ
		this.selectPageDataByOne(request, tableName, viewName, pkValue);				
		HashMap<String, String> result = new HashMap<String, String>();
		if(StringUtils.isNotNull(pkValue)){
			result = (HashMap<String, String>) request.getAttribute("rs");
		}
		
		//��ѯѧ���Ļ�����Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();	
		rs.putAll(xsxxglService.selectStuinfo(xh));
		if(result != null){//���ֱ��ֶε����ƺ���ͼ���ֶε����Ʋ�һ�£���Ҫת��
			result.put("save_ydlbm", result.get("save_ydlbdm"));
			result.put("save_ydqbdm", result.get("save_ydqbdm"));
			result.put("save_ydqbjmc", result.get("ydqbjmc"));
			result.put("save_ydhbdm", result.get("save_ydhbjdm"));
			rs.putAll(result);
		}
		if(StringUtils.isNull(pkValue)){//��ʼ���춯��Ϣ	
			rs.putAll(service.initYdxx(rs));
		}
		
		request.setAttribute("rs",rs );
		request.setAttribute("path",path);
		FormModleCommon.commonRequestSet(request);//·������дȨ��Ϣ
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("oper", oper); 
		setListData(request);//���������б������
		return mapping.findForward("success");
	}
	
	/**
	 * ѧ���춯�޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward modiXxjydsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		XsxxglService xsxxglService = new XsxxglService();
		XsxxXjydglService service = new XsxxXjydglService();
		StudentInfoForm model = (StudentInfoForm)form;
		//�û���Ϣ
		User user = getUser(request);
		//����
		String tableName = "bks_xjydxx";
		//��ͼ��
		String viewName = "view_xjydjbxx";
		//����·��
		String path = "xjydsq.do";
		//����
		String pkValue = "";
		//ѧ�� 
		String xh = "stu".equals(user.getUserType()) //ѧ���û�ȡ�û���
					? user.getUserName() 
					: request.getParameter("xh");
		//����
		String doType = request.getParameter("doType");
		if("save".equals(doType)){//������Ϣ����
			this.insertOperation(request, tableName);
			pkValue = model.getSave_ydxh();//�춯���
			//��ʼ����˼�¼
			model.setYdxh(model.getSave_ydxh());
			model.setYdlbdm(request.getParameter("save_ydlbm"));
			service.initXjydshjl(model,user);
		}
		if("modify".equalsIgnoreCase(doType)){//������Ϣ�޸�
			this.updateOperation(request, tableName);
		}
		
		//��ѯѧ�����춯������Ϣ
		this.selectPageDataByOne(request, tableName, viewName, pkValue);				
		HashMap<String, String> result = new HashMap<String, String>();
		if(StringUtils.isNotNull(pkValue)){
			result = (HashMap<String, String>) request.getAttribute("rs");
		}
		
		//��ѯѧ���Ļ�����Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();	
		rs.putAll(xsxxglService.selectStuinfo(xh));
		if(result != null){//���ֱ��ֶε����ƺ���ͼ���ֶε����Ʋ�һ�£���Ҫת��
			result.put("save_ydlbm", result.get("save_ydlbdm"));
			result.put("save_ydqbdm", result.get("save_ydqbdm"));
			result.put("save_ydqbjmc", result.get("ydqbjmc"));
			result.put("save_ydhbdm", result.get("save_ydhbjdm"));
			rs.putAll(result);
		}
		if(StringUtils.isNull(pkValue)){//��ʼ���춯��Ϣ	
			rs.putAll(service.initYdxx(rs));
		}
		
		request.setAttribute("rs",rs );
		request.setAttribute("path",path);
		FormModleCommon.commonRequestSet(request);//·������дȨ��Ϣ
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		setListData(request);//���������б������
		return mapping.findForward("success");
	}
	
	/**
	 * ѧ���춯���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xjydsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		StudentInfoForm model = (StudentInfoForm)form;
		XsxxXjydglService service = new XsxxXjydglService();
		XtlcglService xtlcglService = new XtlcglService();
		boolean result = false;
		//�û���Ϣ
		User user = getUser(request);
		//����·��
		String path = "xjydsh.do";
		//����
		String doType = request.getParameter("doType");
		//�춯������
		String ydlbm = model.getYdlbdm();
		
		//��ѯ
		List<HashMap<String, String>> topTr = service.getXjydshToptr(ydlbm);
		List<String[]> rs =  new ArrayList<String[]>();
		
		
		//�������
		if ("sh".equals(doType)) {
			//��˽��
			String shjg = request.getParameter("shjg");
			String shyj = request.getParameter("save_shyj");
			model.setShjg(shjg);
			model.setShyj(shyj);
			//�������
			result = service.xjydshBatch(model);
			request.setAttribute("message", result ? "�����ɹ���" : "����ʧ�ܣ�");
			request.setAttribute("result", result);
			doType = "query";
		}
		//ȡ�����
		if("qxsh".equalsIgnoreCase(doType)){
			result = service.xjydqxsh(model, user);
			request.setAttribute("message", result ? "�����ɹ���" : "����ʧ�ܣ�");
			request.setAttribute("result", result);
			doType = "query";
		}
		if ("query".equals(doType)) {
			rs = service.queryXjydsh(model,user);
		}	
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		setListData(request);//���������б������
		List<HashMap<String, String>> ydlbList = (List)request.getAttribute("ydlbList");
		model.setYdlbdm(StringUtils.isNull(model.getYdlbdm()) ? ydlbList.get(0).get("dm") : model.getYdlbdm());
		//��˸�λ
		request.setAttribute("xtgwidList", service.getXjydshgw(model.getYdlbdm()));
		//ֵ���Ի�ԭѡ������״̬����
		request.setAttribute("shztStr", StringUtils.joinStrByArray(model.getShztArr(), ",") );
		request.setAttribute("now", GetTime.getSystemTime());//��ǰϵͳʱ��
		//��ѯ�û����и�λ
		List<HashMap<String, String>> list = xtlcglService.getYhgwList(GlobalsVariable.GNDM_XSXX_XJYDSH+model.getYdlbdm(), 
				user.getUserName()); 		
		model.setXtgwid(StringUtils.isNull(model.getXtgwid()) ? (list != null && list.size()>0 ? list.get(0).get("xtgwid") : "") : model.getXtgwid());
		request.setAttribute("yhgwList", list);
		return mapping.findForward("xjydsh");
	}
	
	/**
	 * ѧ���춯��˲鿴���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xjydShOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		StudentInfoForm model = (StudentInfoForm)form;
		XsxxglService xsxxglService = new XsxxglService();
		XsxxXjydglService service = new XsxxXjydglService();
		//ϵͳ��λid
		String xtgwid = request.getParameter("xtgwid");
		//�û���Ϣ
		User user = getUser(request);
		//����
		String tableName = "bks_xjydxx";
		//��ͼ��
		String viewName = "view_xjydjbxx";
		//����·��
		String path = "xjydsh.do";
		//����
		String doType = request.getParameter("doType");
		//����
		String pkValue = request.getParameter("pkValue");		
		
		//�������
		if ("save".equals(doType)) {
			//��˽��
			String shjg = request.getParameter("shjg");
			 
			model.setUserName(user.getUserName());
			model.setShjg(shjg);
			model.setYdlbdm(request.getParameter("ydlbdm"));
			//���
			boolean result = service.modifyXsxjxx(model,pkValue);
			request.setAttribute("message", result ? "�����ɹ���" : "����ʧ�ܣ�");
			request.setAttribute("result", result);
		}
		//��Ϣ��ѯ
		this.selectPageDataByOne(request, tableName, viewName, pkValue);
		HashMap<String, String> rs = (HashMap<String, String>)request.getAttribute("rs");
		if(!"view".equalsIgnoreCase(doType)){
			//��ѯ��ǰ�û������Ϣ
			rs.putAll(service.getXjydshxx(xtgwid, pkValue));
		}else{
			//�鿴
			request.setAttribute("shxxList", service.getXjydshxxOne(pkValue));
		}
		
		//��ǰʱ��
		request.setAttribute("dqsj", GetTime.getSystemTime());
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		//��������б������
		request.setAttribute("shList", xsxxglService.getList(GlobalsVariable.XTWH_SH_LIST));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		return mapping.findForward("xjydshOne");
	}
	
	/**
	 * ѧ���춯��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xjydcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		StudentInfoForm model = (StudentInfoForm)form;
		XsxxXjydglService service = new XsxxXjydglService();
		//�û���Ϣ
		User user = getUser(request);
		//����·��
		String path = "xjydcx.do";
		//����
		String doType = request.getParameter("doType");
		//�춯������
		String ydlbm = model.getYdlbdm();
		
		//��ѯ
		List<HashMap<String, String>> topTr = service.getXjydshToptr(ydlbm);
		List<String[]> rs =  new ArrayList<String[]>();
		
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, "xg_xsxx_xjydxx_shb");
			this.deleteOperation(request, "bks_xjydxx");
			doType = "query";
		}
		if ("query".equals(doType)) {
			rs = service.queryXjydsqxx(model,user);
		}
		if("stu".equalsIgnoreCase(user.getUserType())){
			model.setQuerylike_xh(user.getUserName());
		}		
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		setListData(request);//���������б������
		List<HashMap<String, String>> ydlbList = (List)request.getAttribute("ydlbList");
		model.setYdlbdm(StringUtils.isNull(model.getYdlbdm()) ? ydlbList.get(0).get("dm") : model.getYdlbdm());
		//��˸�λ
		request.setAttribute("xtgwidList", service.getXjydshgw(model.getYdlbdm()));
		//ֵ���Ի�ԭѡ������״̬����
		request.setAttribute("shztStr", StringUtils.joinStrByArray(model.getShztArr(), ",") );
		request.setAttribute("tableName", "view_xjydjbxx");
		request.setAttribute("realTable", "bks_xjydxx");
		return mapping.findForward("xjydcx");
	}
	
	/**
	 * ѧ���춯�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xjydshlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		StudentInfoForm model = (StudentInfoForm)form;
		XsxxglService xsxxglService = new XsxxglService();
		XsxxXjydglService service = new XsxxXjydglService();
		CommXgInfoService commService = new CommXgInfoService(); 
		//��ѯ
		List<HashMap<String, String>> topTr = service.getXjydshlcToptr();
		List<String[]> rs =  new ArrayList<String[]>();
		
		//��ѯѧ���춯����������
		rs = service.queryXjydlbshlc(model);
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("ydlbList",xsxxglService.getList(GlobalsVariable.XSXX_KTEYS_YDLBLIST));
		request.setAttribute("xjztmList", xsxxglService.getList(GlobalsVariable.XSXX_KTEYS_XJZTLIST));
		request.setAttribute("pageSize", model.getPages().getPageSize());
		request.setAttribute("path", "xjydshlc.do");
		//���뷽ʽ
		request.setAttribute("cyfsList", commService.getCyfsList());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xjydshlc");
	}
	
	/**
	 * ѧ���춯��������޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward updateXjydshlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		XsxxXjydglService service = new XsxxXjydglService();
		StudentInfoForm model = (StudentInfoForm)form;
		User user = getUser(request);
		//����
		String pkValue = request.getParameter("pkValue");
		//��������
		String doType = request.getParameter("doType");
		//����
		String tableName = "dm_ydlb";
		
		if("save".equalsIgnoreCase(doType)){
			//��������,���ݴ��ڣ�ִ���޸Ĳ���
			this.updateOperation(request, tableName);
			model.setYdlbdm(request.getParameter("save_ydlbm"));
			model.setSave_shlcid(request.getParameter("save_shlcid"));
			
			service.saveShlcdyxxb(model,user);
		}
		
		request.setAttribute("rs", service.getXjydshlcxx(pkValue));
		request.setAttribute("path", "xjydshlc.do");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("updateXjydshlc");
	}
	
	
	/**
	 * ���������б������
	 * @param reqeust
	 * */
	public void setListData(HttpServletRequest request){
		XsxxglService xsxxglService = new XsxxglService();
		//�춯���
		request.setAttribute("ydlbList", xsxxglService.getList(GlobalsVariable.XSXX_KTEYS_YDLBLIST));
		//ѧ��״̬
		request.setAttribute("xjztList", xsxxglService.getList(GlobalsVariable.XSXX_KTEYS_XJZTLIST));
		//�Ƿ���У
		request.setAttribute("sfzxList", xsxxglService.getList(GlobalsVariable.XSXX_KTEYS_SFZXLIST));
		//����б�
		request.setAttribute("shList", xsxxglService.getList(GlobalsVariable.XTWH_SH_LIST));		
		//�꼶��ѧԺ��רҵ���༶
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		//��ȡ�ѧ�ꡢѧ��
		FormModleCommon.setNdXnXqList(request);		
	}
}