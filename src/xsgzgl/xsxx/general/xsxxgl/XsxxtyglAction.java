package xsgzgl.xsxx.general.xsxxgl;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.export.util.DateUtils;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjtx.HcccqjtxService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.wjcf.cfjg.CfjgService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xsxx.cxpy.CxpyService;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxcj.XsxxcjForm;
import com.zfsoft.xgxt.xsxx.xsxxcj.XsxxcjService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import common.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.ResourceUtils;
import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.cwgl.CwglService;
import xsgzgl.wjcf.cfsjwh.WjcfCfsjwhService;
import xsgzgl.xsxx.general.qzxgmdsz.QzxgmdszService;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ѧ����Ϣ - ѧ����Ϣ����
 * lt
 * 2013-1-7
 */
public class XsxxtyglAction extends BasicAction {
	

	/**
	 * ��ѯ��У����Ϣ
	 * @param form
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cxZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		XsxxtyglInit init = new XsxxtyglInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initZxsSearch(rForm, user, request);
		// =================== end ===================
		//===============����������===================
		request.setAttribute("tableName", "view_xsxxb_zxs");
		request.setAttribute("realTable", "xsxxb");
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		request.setAttribute("xxdm", Base.xxdm);
		// =================== end ===================
		if("stu".equalsIgnoreCase(user.getUserType())){
			myForm.setXh(user.getUserName());
			request.setAttribute("sfxsgban", "bxs");
			//����ҽ��ѧ���û��޴�ӡ��ť ���⴦�� 982 �Ų�·
			if(Base.xxdm.equals("10598")){
				request.removeAttribute("sfxsgban");
			}
			return ckZxsxx(mapping, myForm, request, response);
		}else{
			return mapping.findForward("cxZxsxx");
		}
		
	}
	
	/**
	 * ��ѯ��У�������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxZxsxxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
	
		XsxxtyglInit init = new XsxxtyglInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initZxsSearch(rForm, user, request);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cd", "zxs");
		map.put("gnlj", "xsxx_tygl_cxzxs.do");
		// ����
		List<HashMap<String, String>> topTr = service.getTopList(map);
		// �����
		ArrayList<String[]> rsArrList = service.getZxsxxList(myForm, user);
		// ���������
		String spHtml =  service.createHTML(rsModel,topTr, rsArrList);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ��У����Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward zjZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

		XsxxtyglService service = new XsxxtyglService();
		RequestForm rForm = new RequestForm();
		rForm.setMenu("zxsxx");
		rForm.setGnmk("xsxx");

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setAllList(model, rForm, request);
		// =================== end ===================
		String path = "/xsgzgl/xsxx/general/xsxxgl/"+Base.xxdm+"/zjZxsxx.jsp";
		if (validateUrlIsExists(request,path )) {
			return new ActionForward(path);
		}
		
		return mapping.findForward("zjZxsxx");
	}
	
	/**
	 * ������У����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bcZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		XsxxtyglInit init = new XsxxtyglInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();
		rForm.setMenu("zxsxx");
		rForm.setGnmk("xsxx");
		myForm.setUser(user);
		String flag = request.getParameter("sfxgFlag");
		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setAllList(model, rForm, request);
		// =================== end ===================
		boolean result = true;
	
		String guid = init.bcZxsxx(myForm, request, user,flag);
		
		String message = !"".equals(guid) ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		
		String qzxg = request.getParameter("fromQzxg");
		
		//������칤��
		if("����ɹ�".equals(message)&& "true".equals(flag)){
			String xh = user.getUserName();
			HashMap<String, String> csszMap = service.getCsszjg();
			//�������
			if (StringUtils.isNotNull(csszMap.get("shlid")) && !"wxsh".equalsIgnoreCase(csszMap.get("shlid"))){
				 HashMap<String, String> gwmap = service.getMinSpgw();
				 String id= guid;
				 //����Ƿ����
				 HashMap<String,String> map = service.getWdgz(id);
				 if (StringUtils.isNotNull(gwmap.get("spgw"))&&StringUtils.isNull(map.get("gzid"))){
						Job job = Job.getJobInstance(id, xh, gwmap.get("spgw"), 
						"general_xsxx.do?method=xgshSearch&spgw="+gwmap.get("spgw"), 
						"xsxx_general_xxxg_xgjg.do","ѧ����Ϣ�޸�", "ѧ����Ϣ�޸�");
						MyJobsManager manager = new MyJobsImpl();
						manager.pushJob(job);
					}
			}
		}
		
		//ǿ���޸���ת
		if ("yes".equals(qzxg) && result){
			QzxgmdszService qzxgservice = new QzxgmdszService ();
			qzxgservice.xgQzxgzt(user.getUserName());
			return new ActionForward("/stuPage.jsp");
		}
		
		//��ȡ��δ�޸ĵ�ѧ����Ϣ
		HashMap<String, String> xsxxMap = service.getZxsxxByXhCk(myForm);
		
		if ("xg".equalsIgnoreCase(request.getParameter("doType"))) {//��ת���޸�ҳ��
			
			if ("stu".equalsIgnoreCase(user.getUserType())) {
				myForm.setXh(user.getUserName());
				// =================== end ===================
				//��ѧ��Ϊ׼���ֶ�����
				HashMap<String, String> zdpzMap = service.getXsxxTbzdpzbStr("stu");
				request.setAttribute("zdpzstr", zdpzMap.get("zdstr"));
				request.setAttribute("xgzt", zdpzMap.get("xgzt"));
				//ѧ���޸�ǰ���ֶζ�Ӧֵ�ַ���
				request.setAttribute("xsxxstr", service.getStuZdszValue(xsxxMap));
				HashMap<String, String> csszMap = service.getCsszjg();
				String kfxg = "n".equalsIgnoreCase(csszMap.get("kgzt")) ? "n" : "y";//�޸�״̬����
				String msg = "";
				//�Բ��ܽ����޸ĵ���Ϣ��ʾ����
				if ("n".equalsIgnoreCase(kfxg)) {
					msg = "ѧ����Ϣ�޸�״̬�ѹرգ�";
				} else {
					String sqzt = service.getStuSqzt(myForm.getXh());
					if (!"0".equalsIgnoreCase(sqzt)) {
						msg = "��ǰ�޸���Ϣ��������У����ܽ��в�����";
					}
				}
				request.setAttribute("xgts", msg);
				request.setAttribute("xsxxxg", "yes");//�жϹرհ�ť�Ƿ���ʾ 
				//��ȡ��ǰ�����޸ĵ�ѧ����Ϣ
				xsxxMap = service.getZxsxxByXh(myForm);
				return sqXsxxxg(mapping, myForm, request, response);
			} else {
			
				//��ȡ����ѧ����Ϣ
				//HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);
				
				//����ѧ��Ϊ׼���ֶ�����
				HashMap<String, String> zdpzMap = service.getXsxxTbzdpzbStr("tea");
				request.setAttribute("zdpzstr", zdpzMap.get("zdstr"));
				request.setAttribute("xgzt", zdpzMap.get("xgzt"));
				
				return xgZxsxx(mapping, myForm, request, response);
			}
			
			
		} else {
			//��ת������ҳ��
			
			//��ȡ��ǰ�����޸ĵ�ѧ����Ϣ
			xsxxMap = service.getZxsxxByXh(myForm);
			
			request.setAttribute("rs", xsxxMap);
			String path = "/xsgzgl/xsxx/general/xsxxgl/"+Base.xxdm+"/zjZxsxx.jsp";
			if (validateUrlIsExists(request, path)) {
				return new ActionForward(path); 
			}
			return mapping.findForward("zjZxsxx");
		}
	}
	
	/**
	 * �޸���У����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xgZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		RequestForm rForm = new RequestForm();
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		User user = getUser(request);
		myForm.setUser(user);
			rForm.setMenu("zxsxx");
			rForm.setGnmk("xsxx");
			HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);
			request.setAttribute("rs", xsxxMap);
			// ============= ����request��ֵ =============
			CommForm model = new CommForm();
			service.setRequestValue(rForm, request);
			service.setAllList(model, rForm, request);
			// =================== end ===================
			//��ѧ��Ϊ׼���ֶ�����
			HashMap<String, String> zdpzMap = service.getXsxxTbzdpzbStr("tea");
			request.setAttribute("zdpzstr", zdpzMap.get("zdstr"));
			request.setAttribute("xgzt", zdpzMap.get("xgzt"));
			//�ɷ��޸�ѧ����Ϣ
			request.setAttribute("xgts", "");
			request.setAttribute("bcansfxs", "xs");//�жϱ��水ť�Ƿ���ʾ 
			String path = "/xsgzgl/xsxx/general/xsxxgl/"+Base.xxdm+"/xgZxsxx.jsp";
		if (validateUrlIsExists(request, path)) {
			return new ActionForward(path,false);
		}
		return mapping.findForward("xgZxsxx");
	}
	
	/**
	 * ɾ����У����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward scZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();

		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(myForm, request);
		// ============= end ============

		// ==================����ǰ̨ҳ��====================

		boolean flag = service.deleteZxsxx(myForm, user);

		String message = flag ? "ɾ���ɹ�" : "ɾ��ʧ�ܣ�����ϵ�����Ա";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * �鿴��У��ѧ����ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ckZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		/////ѧ����Ϣ������ģ����ת����ϸҳ�棬�¾ɰ汾������ʱ����ʽ���ж������°汾�˵����в˵�����ת���°湦��///////
		try {
			HttpSession session = request.getSession();
			String xsxxZdybd =  (String)session.getAttribute("XSXX_ZDYBD");
			if(xsxxZdybd != null && xsxxZdybd.equals("1")){
				String xh = request.getParameter("xh");
				String toPage = "/xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh;
				return new ActionForward(toPage, false);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		/////////////////////////////////////////////////////////////////////////////
		
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		User user = getUser(request);
		myForm.setUser(user);
		XsxxtyglService service = new XsxxtyglService();
		HashMap<String, String> xsxxMap = service.getZxsxxByXhCk(myForm);
		//zd4�����Ի����� �������ƣ�2��
		if(Base.xxdm.equals("12036")&&null!=xsxxMap.get("zd4")){
			//������ʾ�����д���Ϊ���ж�Ӧ����
			xsxxMap.put("zd4", service.getYhmc(xsxxMap.get("zd4")));
		}
		request.setAttribute("rs", xsxxMap);
		//����I��Wѧ��̨����Ϣ
		if("11032".equalsIgnoreCase(Base.xxdm)){
			XsxxcjService xsxxcjService=new XsxxcjService();
			XsxxcjForm xsxxcjForm=new XsxxcjForm();
			xsxxcjForm.setXh(xsxxMap.get("xh"));
			XsxxcjForm model=null;
			try {
				model=xsxxcjService.getModel(xsxxcjForm);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("xsxxcjForm", model);
		}
		
		List<HashMap<String, String>> mkList = service.getCkxsgnmkxx();
		//˼����ʦ�б�
		List<HashMap<String, String>> szxxList = service.getFdyBzrListByBjdm(xsxxMap.get("bjdm"));
		List<HashMap<String, String>> gbList = service.getGbxxList(xsxxMap.get("xh"));
		request.setAttribute("gbList", gbList);
		request.setAttribute("ckList", mkList);//�鿴һ��ģ���б�
		request.setAttribute("ywlbList", service.getCkywlbList(mkList));//����ģ���б�
		request.setAttribute("mksize", null!=mkList&&mkList.size()>7?"true":"false");
		
		request.setAttribute("fdyList", service.getSzxxList("fdy", szxxList));//����Ա��ʦ��Ϣ�б�
		request.setAttribute("bzrList", service.getSzxxList("bzr", szxxList));//��������ʦ��Ϣ�б�
		
		String path = "/xsgzgl/xsxx/general/xsxxgl/"+Base.xxdm+"/ckZxsxx.jsp";
		if (validateUrlIsExists(request, path)) {
			return new ActionForward(path,false);
		}
		return mapping.findForward("ckZxsxx");
	}
	
	/**
	 * ��ѯ����У����Ϣ
	 * @param form
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cxFzxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		XsxxtyglInit init = new XsxxtyglInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initFzxsSearch(rForm, user, request);
		// =================== end ===================
		//===============����������===================
		request.setAttribute("tableName", "view_xsxxb_fzxs");
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		
		return mapping.findForward("cxFzxsxx");
	}
	
	/**
	 * ��ѯ����У�������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxFzxsxxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
	
		XsxxtyglInit init = new XsxxtyglInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initFzxsSearch(rForm, user, request);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cd", "zxs");
		map.put("gnlj", "xsxx_tygl_cxzxs.do");
		// ����
		List<HashMap<String, String>> topTr = service.getTopList(map);
		// �����
		ArrayList<String[]> rsArrList = service.getFzxsxxList(myForm, user);
		// ���������
		String spHtml =  service.createHTML(rsModel,topTr, rsArrList);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");
		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * 
	  * validateUrlIsExists ����
	  * <p>����˵��:��֤URL·���Ƿ���ڣ����ڷ���TRUE,��֮FALSE</p> 
	  * @param jspUrl
	  * @return
	  * @return boolean
	  * @author litao
	  * @date 2011-6-9
	 */
	public boolean validateUrlIsExists(HttpServletRequest request, String jspUrl) {
		File tempFilePath  = new File(request.getRealPath(jspUrl));
		if (!tempFilePath.exists()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * ���ѧ���Ƿ���� ���ڷ���1,��֮0
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String jcXhsfcz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		// ============= end ============

		// ==================����ǰ̨ҳ��====================
		String message = service.chkStuIsExists(myForm.getXh());

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ��ʾ��ҵ��ģ�鹦���б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String xsYwmklb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsywxxService service = new XsywxxService();
		// ============= end ============

		// ==================����ǰ̨ҳ��====================
		String message = service.getYwmklbHtml(myForm.getGnmk(), myForm.getXh());

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	/**
	 * ѧ�������ʼ��
	 * 
	 * @date 2013-01-11
	 * @author CMJ
	 */
	public ActionForward yhmmCsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
				
		// ������ֵ
		service.getModelValue(myForm, request);
		
		//��Ϣ��Ϣ
		String message="";
				
		boolean flag=false;
				
		flag=service.cshYhmm(myForm);
				
		message = flag ? MessageInfo.MESSAGE_INIT_SUCCESS
				: MessageInfo.MESSAGE_INIT_FALSE;
	
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	
	/**
	 * ѧ�������޸ĸ�����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sqXsxxxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxxtyglService service = new XsxxtyglService();
		RequestForm rForm = new RequestForm();
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		User user = getUser(request);// �û�����
		myForm.setUser(user);
		if(!"stu".equalsIgnoreCase(user.getUserType())){
			String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg); 
			return new ActionForward("/yhInfo.do", false);
		}
		myForm.setXh(user.getUserName());
		rForm.setMenu("zxsxx");
		rForm.setGnmk("xsxx");
		
		//�������޸ĵ�ѧ����Ϣ
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);
		xsxxMap.put("jgshen", xsxxMap.get("jg") != null ? xsxxMap.get("jg").substring(0, 2)+"0000" : "");
		xsxxMap.put("jgshi", xsxxMap.get("jg") != null && !"00".equalsIgnoreCase(xsxxMap.get("jg").substring(3, 4)) ? xsxxMap.get("jg").substring(0, 4)+"00" : "");
		xsxxMap.put("jgxian", xsxxMap.get("jg") != null && !"00".equalsIgnoreCase(xsxxMap.get("jg").substring(5, 6)) ? xsxxMap.get("jg").substring(0, 6) : "");
		
		xsxxMap.put("hkshen", xsxxMap.get("hkszd") != null ? xsxxMap.get("hkszd").substring(0, 2)+"0000" : "");
		xsxxMap.put("hkshi", xsxxMap.get("hkszd") != null && !"00".equalsIgnoreCase(xsxxMap.get("hkszd").substring(3, 4)) ? xsxxMap.get("hkszd").substring(0, 4)+"00" : "");
		xsxxMap.put("hkxian", xsxxMap.get("hkszd") != null && !"00".equalsIgnoreCase(xsxxMap.get("hkszd").substring(5, 6)) ? xsxxMap.get("hkszd").substring(0, 6) : "");
		
		xsxxMap.put("syds", xsxxMap.get("syd") != null ? xsxxMap.get("syd").substring(0, 2)+"0000" : "");
		xsxxMap.put("sydshi", xsxxMap.get("syd") != null && !"00".equalsIgnoreCase(xsxxMap.get("syd").substring(3, 4)) ? xsxxMap.get("syd").substring(0, 4)+"00" : "");
		xsxxMap.put("sydx", xsxxMap.get("syd") != null && !"00".equalsIgnoreCase(xsxxMap.get("syd").substring(5, 6)) ? xsxxMap.get("syd").substring(0, 6) : "");
		
		request.setAttribute("rs", xsxxMap);
		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setAllList(model, rForm, request);
		// =================== end ===================
		//��ѧ��Ϊ׼���ֶ�����
		HashMap<String, String> zdpzMap = service.getXsxxTbzdpzbStr("stu");
		request.setAttribute("zdpzstr", zdpzMap.get("zdstr"));
		request.setAttribute("xgzt", zdpzMap.get("xgzt"));
		
		//��ǰѧ����ϢMap   ��δ�޸ĵ�ѧ����Ϣ
		HashMap<String, String> dqXsxxMap = service.getZxsxxByXhCk(myForm);
		
		//ѧ���޸�ǰ���ֶζ�Ӧֵ�ַ���
		request.setAttribute("xsxxstr", service.getStuZdszValue(dqXsxxMap));
		HashMap<String, String> csszMap = service.getCsszjg();
		String kfxg = "n".equalsIgnoreCase(csszMap.get("kgzt")) ? "n" : "y";//�޸�״̬����
		HashMap<String, String> thjgMap = service.getXsthsq(myForm.getXh());
		boolean sfth = null!=thjgMap&&thjgMap.size()>0?true:false;//�Ƿ�����˻�����
		String msg = "";
		String bcansfxs = "xs";
		
		String zpsfcz = service.checkxszpSfcz(myForm.getXh());
		
		//�Բ��ܽ����޸ĵ���Ϣ��ʾ����
		if ("n".equalsIgnoreCase(kfxg)&&!sfth) {
			msg = "ѧ����Ϣ�޸�״̬�ѹرգ�";
			bcansfxs="";
		} else {
			String sqzt = service.getStuSqzt(myForm.getXh());
			if (!"0".equalsIgnoreCase(sqzt)) {
				msg = "��ǰ�޸���Ϣ��������У����ܽ��в�����";
				bcansfxs="";
			}
		}
		List<HashMap<String,String>> shxxList = service.getXgshxx(user);//�����Ϣlist
		if(null!=shxxList&&shxxList.size()>0){
			msg = "����޸����뱻�˻أ���������д��";
		}
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("xgts", msg);
		request.setAttribute("xsxxxg", "yes");//�жϹرհ�ť�Ƿ���ʾ 
		request.setAttribute("bcansfxs", bcansfxs);//�жϱ��水ť�Ƿ���ʾ 
		request.setAttribute("shxxList", shxxList);
		String url = "/xsgzgl/xsxx/general/xsxxgl/"+Base.xxdm+"/xgZxsxx.jsp";
		if (validateUrlIsExists(request, url)) {
			return new ActionForward(url); //mapping.findForward("xgZxsxx" + Base.xxdm);
		}
		
		return mapping.findForward("xgZxsxx");
	}
	
	/** 
	 * @����: ѧ����������ӡ
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-17 ����03:35:43
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
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		XsxxtyglForm model = (XsxxtyglForm) form;
		User user = getUser(request);
		model.setUser(user);
		if("stu".equals(user.getUserType()) && !user.getUserName().equals(model.getXh())){
			model.setXh("");
		}
		XsxxtyglService service = new XsxxtyglService();
		
		if (StringUtil.isNull(user.getUserName())) {
			request.setAttribute("message", "���ȵ�½ϵͳ�ٽ��з��ʣ�");
			return mapping.findForward("error");
		}
		
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			HashMap<String,String> xsjbxx = service.getZxsxxByXh(model);
			//�ֽ����֤��begin
			String xssfzh = xsjbxx.get("sfzh")==null || "".equals("sfzh")?"":xsjbxx.get("sfzh");
			int sylen= 18-xssfzh.length();
			for (int i=0;i<xssfzh.length();i++){
				xsjbxx.put("sfzh"+i, xssfzh.charAt(i)+"");
			}
			for (int i=0;i<sylen;i++){
				xsjbxx.put("sfzh"+(xssfzh.length()+i), "");
			}
			//�ֽ����֤��end
			request.setAttribute("jbxx", xsjbxx);
			
			//��ȡѧ��ס����Ϣ
			CwglService zsxxService = new CwglService();
			HashMap<String,String> zsxx =zsxxService.getCwForXh(model.getXh());
			request.setAttribute("zsxx", zsxx);
			
			// ��ͥ��Ա��Ϣ
			List<HashMap<String, String>> jtcyxxList = service
					.getJtcyxxXsList(model.getXh());
			request.setAttribute("jtcyxxList", jtcyxxList);
			
			String qfje = service.getQfqk(model.getXh());
			
			request.setAttribute("qfje", qfje); 
			
		}
        request.setAttribute("mkxxForm", model);
        request.setAttribute("xxmc", Base.xxmc);
		//String forward = "/xsgzgl/xsxx/general/xsxxgl/print/xsdjb_default.jsp";
        //�㶫ʡ�Ṥҵ��ʦѧԺ���Ի�
        if("9800003".equals(Base.xxdm)){
        	//������������
            HashMap<String,String> xscxpyxx = service.getCxpyxxByXh(model);
            request.setAttribute("xscxpyxx", xscxpyxx);
        }
        String forward = "";
        List<HashMap<String,String>>  cjList = service.getCjList(model.getXh());
        request.setAttribute("cjList", cjList);
		//��������ѧԺ���Ի�&�人ְҵ����ѧԺ
		if (validateUrlIsExists(request, "/xsgzgl/xsxx/general/xsxxgl/print/xsdjb_"+Base.xxdm+".jsp")) {
			forward = "/xsgzgl/xsxx/general/xsxxgl/print/xsdjb_"+Base.xxdm+".jsp";
		}
		return new ActionForward(forward, false);
	}
	
	/** 
	 * @����: ѧ����������ӡ��������
	 * @���ߣ�cmj
	 * @���ڣ�2013-7-29 ����03:35:43
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
	public ActionForward plPrintJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] xhs=new String[]{};
    	String xhstr = request.getParameter("xhstr");		
		xhs = xhstr.split(",");
		
		String newxhstr = "";
		for(int i=1;i < xhs.length;i++){
			newxhstr +=xhs[i]+",";
		}
		request.setAttribute("xhstr", newxhstr);
		String xh=xhs[0];
		XsxxtyglForm model = (XsxxtyglForm) form;
		model.setXh(xh);
		User user = getUser(request);
		model.setUser(user);
		XsxxtyglService service = new XsxxtyglService();
		
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			HashMap<String,String> xsjbxx = service.getZxsxxByXh(model);
			//�ֽ����֤��begin
			String xssfzh = xsjbxx.get("sfzh")==null || "".equals("sfzh")?"":xsjbxx.get("sfzh");
			int sylen= 18-xssfzh.length();
			for (int i=0;i<xssfzh.length();i++){
				xsjbxx.put("sfzh"+i, xssfzh.charAt(i)+"");
			}
			for (int i=0;i<sylen;i++){
				xsjbxx.put("sfzh"+(xssfzh.length()+i), "");
			}
			//�ֽ����֤��end
			request.setAttribute("jbxx", xsjbxx);
			
			//��ȡѧ��ס����Ϣ
			CwglService zsxxService = new CwglService();
			HashMap<String,String> zsxx =zsxxService.getCwForXh(model.getXh());
			request.setAttribute("zsxx", zsxx);
		}
        request.setAttribute("mkxxForm", model);
        request.setAttribute("xxmc", Base.xxmc);
		//String forward = "/xsgzgl/xsxx/general/xsxxgl/print/xsdjb_default.jsp";
        //������������
        HashMap<String,String> xscxpyxx = service.getCxpyxxByXh(model);
        request.setAttribute("xscxpyxx", xscxpyxx);
        String forward = "";
		//��������ѧԺ���Ի�&�人ְҵ����ѧԺ
		if (validateUrlIsExists(request, "/xsgzgl/xsxx/general/xsxxgl/print/xsdjb_"+Base.xxdm+".jsp")) {
			forward = "/xsgzgl/xsxx/general/xsxxgl/print/xsdjb_"+Base.xxdm+".jsp";
		}
		return new ActionForward(forward, false);
	}
	
	
	/** 
	 * @����: ����ҽ�ƴ�ѧ��ҵ���嵥��ӡ
	 * @���ߣ�cmj
	 * @���ڣ�2013-7-23 ����03:35:43
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
	
	public ActionForward printBysqd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		XsywxxService xsywxxService=new XsywxxService();
		HashMap<String, String> xsxxMap = service.getZxsxxByXhCk(myForm);
		request.setAttribute("rs", xsxxMap);
		WjcfCfsjwhService wjcfService = new WjcfCfsjwhService();
		List<HashMap<String, Object>> wjlist = new ArrayList<HashMap<String, Object>>();//Υ��ҵ������
		List<HashMap<String, Object>> pjlist = new ArrayList<HashMap<String, Object>>();//����ҵ������
		wjlist = wjcfService.getStuWjcfList(myForm.getXh());
		pjlist = xsywxxService.getStuPjList(myForm.getXh());
		//��ȡΥ��ҵ������
		HashMap<String, Object> map=wjlist.get(0);
		List<String[]> wj=(List<String[]>) map.get("cxjg");
		if(wj.size()==1){//���list��СΪ1��˵��ֻ�б�ͷ���ݣ�
			wj=null;
		}
		if(pjlist.size()==3){
			//��ȡ��ѧ��ҵ������
			map=pjlist.get(0);
			List<String[]> jxj=(List<String[]>) map.get("cxjg");
			if(jxj.size()==1){//���list��СΪ1��˵��ֻ�б�ͷ���ݣ�
				jxj=null;
			}
			//��ȡ�����ƺ�ҵ������
			map=pjlist.get(1);
			List<String[]> rych=(List<String[]>) map.get("cxjg");
			if(rych.size()==1){//���list��СΪ1��˵��ֻ�б�ͷ���ݣ�
				rych=null;
			}
			//��ȡ�۲��ҵ������
			map=pjlist.get(2);
			List<String[]> zcf=(List<String[]>) map.get("cxjg");
			if(zcf.size()==1){//���list��СΪ1��˵��ֻ�б�ͷ���ݣ�
				zcf=null;
			}
			request.setAttribute("wjlist", wj);
			request.setAttribute("jxj", jxj);
			request.setAttribute("rych", rych);
			request.setAttribute("zcf", zcf);
		}
		return mapping.findForward("printBysqd");
		
	}
	
	
	/**
	 * 
	 * @����: չʾѧ�������ʼ��ҳ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-28 ����08:58:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	public ActionForward showCshmm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("showCshmm");
	}
	
	/**
	 * ��У����Ϣ�Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward zxsxxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		XsxxtyglForm model = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getZxsxxExportDataList(model, user);
		
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
	 * ����У����Ϣ�Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward fzxsxxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm model = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getFzxsxxExportDataList(model, user);
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
	//��Ա
	public ActionForward getPrintTY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm model = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getTyExportDataList(model, user);
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
	//�Ÿɲ�
	public ActionForward getPrintTGB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm model = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getTgbExportDataList(model, user);
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
	 * ����ѧ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public ActionForward getXjkWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		
		File wordFile = getWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	/**
	 * 
	 * @����:����֯��ϵ��ӡ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-24 ����05:08:09
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
	public ActionForward printZzjsx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		File wordFile = getZzjsxWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	public ActionForward printZzjsxZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getZzjsxWord(myForm, request);;
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	
	/*
	 * ��ȡword
	 */
	private File getWord(XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		String xh = myForm.getXh();
		User user = getUser(request);
		myForm.setUser(user);
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//ѧ��������Ϣ
		List<String> kcList=service.getKcList(myForm);//ѧ���γ�list
		List<String> xnList=service.getXnList(myForm);//ѧ��ѧ��list
		List<HashMap<String, String>> cjList=service.getCjList(myForm,xnList,kcList);//ѧ���ɼ�list
		HashMap<String, String> cxpyMap=service.getCxpyxxByXh(myForm);
		HashMap<String, String> zpMap=service.getZpMap(myForm,request,"zp");
		HashMap<String, String> xszpMap=service.getZpMap(myForm,request,"xszp");
		data.putAll(xsxxMap);
		data.put("xxmc", Base.xxmc);
		data.put("cjList", cjList);
		data.putAll(cxpyMap);
		data.putAll(zpMap);
		data.putAll(xszpMap);
		data.put("blankList", service.getBlankList(22 - cjList.size()));
		String fileName = "xsxjk.xml";
		if("11527".equals(Base.xxdm)){
			fileName="xsxjk_11527.xml";
		}
		return FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx",fileName,xh+"-"+xsxxMap.get("xm"));
	}
	/**
	 * 
	 * @����: ����ZIP
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-5-21 ����05:27:29
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
	public ActionForward getXjkZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����:(����ͨ�ð汾��ѧ����)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-9-9 ����02:47:22
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
	public ActionForward getXjk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		XsxxtyglService service = new XsxxtyglService();
		User user = getUser(request);
		if("stu".equals(user.getUserType())&&!user.getUserName().equals(myForm.getXh())){
			File file =FreeMarkerUtil.getWordFile(null,"classpath://templates//xsxx","error.xml","error");
			FileUtil.outputWord(response, file);
			return null;
		}
		Map<String,String> authMap = user.getAuthMap();
		
		//��֤����Ȩ��
		if(!"stu".equals(user.getUserType())&&(!((authMap.containsKey("xsxx_xsxxgl_cxzxs.do")&&service.checkDownLoadAuth(myForm, user))
				|| (authMap.containsKey("xsxx_xsxxgl_cxfzxs.do")&&!service.checkDownLoadAuth(myForm, user))))){
			return null;
		}
		File wordFile = getXjkWord(myForm,request);
		
		if(StringUtils.isEqual(Base.xxdm, "12867")){
			outputFile(response, wordFile, "application/vnd.ms-word");
		}else {
			FileUtil.outputWord(response, wordFile);
		}
		return null;
	}
	
	public static void outputFile(HttpServletResponse response, File file,
			String type) throws IOException {
		String fileName = new String(file.getName().getBytes("UTF-8"),"ISO-8859-1");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName);
		response.setContentType(type);
		OutputStream out = null;
		InputStream in = null;
		out = response.getOutputStream();
		in = new FileInputStream(file);
		FileUtil.outputFileStream(in, out);

	}
	
	/**
	 * 
	 * @����:(����ͨ�ð汾��ѧ����zip)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-9-9 ����02:47:22
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
	public ActionForward getXjkZipTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getXjkWord(myForm,request);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	
		 
		 
	private File getXjkWord(XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		XsxxglService newservice = new XsxxglService();
		String xh = myForm.getXh();
		User user = getUser(request);
		String hnjdDjb = request.getParameter("hnjdDjb");
		myForm.setUser(user);
		Map<String,Object> data = new HashMap<String,Object>();
		PjjgService pjjgService = new PjjgService();
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//ѧ��������Ϣ
 		List<HashMap<String, String>> pjList = pjjgService.getPjpyInfoMap(xh);
 		List<HashMap<String, String>> pjjg4line = pjjgService.getHjqkByXhMap(xh,4);//ȡ����4���������������4������
 		List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
 		List<HashMap<String, String>> xlshjlList = xsxxglService.getXlshjlList4line(xh);
 		
		HashMap<String, String> zpMap=service.getZpMap(myForm,request,"zp");
		String dqrq = xsxxglService.getDqrq(xh);
		String dyrq = DateTranCnDate.fomartDateToCn(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),FomartDateType.day);
		data.putAll(xsxxMap);
		data.put("dqrq", DateTranCnDate.fomartDateToCn(dqrq,FomartDateType.day));
		data.put("xxmc", Base.xxmc);
		data.put("pjjg4line", pjjg4line);
		data.put("pjList", pjList);
		data.put("jtcyxxList", jtcyxxList);
		data.put("xlshjlList", xlshjlList);
		data.putAll(zpMap);
		int size=(4 - pjList.size())<0?0:(4 - pjList.size());
		int size2=(4 - jtcyxxList.size())<=0?0:(4 - jtcyxxList.size());
		int size3=(4 - xlshjlList.size())<=0?0:(4 - xlshjlList.size());
		int size4=(14 - jtcyxxList.size())<=0?0:(14 - jtcyxxList.size()); 
		data.put("blankList", service.getBlankList(size));
		data.put("blankList2", service.getBlankList(size2));
		data.put("blankList3", service.getBlankList(size3));
		data.put("blankList4", service.getBlankList(size4));
		data.put("qfqk", service.getQfqk(xh));  //Ƿ�����
		if(Globals.XXDM_CDTYXY.equals(Base.xxdm)){
			data.put("cdcj",xsxxglService.getCdtyCjxxByxh(xh));
			DtxxjgService dtxxservice = new DtxxjgService();
			String rdsj = dtxxservice.getGrDtjgxx(xh, "09").get("kssj");
			String rtsj = dtxxservice.getGrDtjgxx(xh, "02").get("kssj");
			data.put("hhgrjl", HtmlUtil.wordHh(xsxxMap.get("grjl")));
			if(!StringUtil.isNull(rdsj)){
				data.put("rdtsj", DateTranCnDate.fomartDateToCn(rdsj,FomartDateType.day));
			}else{
				data.put("rdtsj", DateTranCnDate.fomartDateToCn(rtsj,FomartDateType.day));
			}
		}
		//ѧ����Ƭ
		XsxxService xsxxService = new XsxxService();
		InputStream is = xsxxService.getPhotoStream(xh);
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		
		if (StringUtil.isNull(photo)){
			//ȡĬ����Ƭ 
			photo = xsxxService.getDefaultPhotoBase64(request);
		}
		
		if(photo == null){
			photo = "";
		}
		data.put("photo", photo);
		
		//��ȡѧ��ס����Ϣ
		CwglService zsxxService = new CwglService();
		HashMap<String,String> zsxx =zsxxService.getCwForXh(xh);
		data.put("zsxx", zsxx);
		
		//��ͥ���
		JtqkdcService jtqkdcService = new JtqkdcService();
		JtqkdcForm jtqkdc = jtqkdcService.getModel(xh);
		data.put("jtqkdc", jtqkdc);
		
		//��ȡ�𳵳˳�����
		HashMap<String, String> hcccqj = new HcccqjtxService().getHcccqj(xh);
		data.put("hcccqj", hcccqj);
		
		//��ȡ���θɲ�
		String drgb = new DwwhService().getZwForXh(xh);
		data.put("drgb", drgb);
		
		//Υ�����
		List<HashMap<String, String>> wjcfList = newservice.getWjcfList(xh);// ����ѧ�Ų�ѯΥ�ʹ����б�
		data.put("wjcfList", wjcfList);
		File file = null;
		
		//������ְҵѧԺ������ѧ�ǼǱ� ���Ի�
		if(StringUtils.isEqual(Base.xxdm, "12484"))
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_12484.xml",xh+"-"+xsxxMap.get("xm"));
		//����ʦ����ѧ������ѧ�ǼǱ� ���Ի�
		else if(StringUtils.isEqual(Base.xxdm, "10511")){
			//�Ѹ��˼����͹��������ŵ�һ��ȡֵ
			List<HashMap<String, String>> grjlShjlList = xsxxglService.getGrjlShjlList(xh);
			data.put("grjlShjlList", grjlShjlList);
			int size14=(10 - grjlShjlList.size())<=0?0:(10 - grjlShjlList.size());
			data.put("blankListhsd", service.getBlankList(size14));
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_10511.xml",xh+"-"+xsxxMap.get("xm"));
		}

		//����ʡ����ҽҩ
		else if(Base.xxdm.equals(Globals.XXDM_JSSXZYYGDZYXX)){		
			/*��ͥ��Ա��Ϣ��ְ*/
			jtcyxxList = jtqkdcService.getJtcyList(xh, 5);
			data.put("jtcyxxList_70002", jtcyxxList);			
			/*��ͥ��Ա��Ϣ�������в�����*/
			int size6 = (5 - jtcyxxList.size())<=0?0:(5 - jtcyxxList.size()); 
			data.put("blankListJtcy", service.getBlankList(size6));			
			/*��ͥ��Ա��Ϣ��ְ*/
			List<HashMap<String, String>> jtcyxxgzList = xsxxglService.getJtcyxxXsList(xh);
			jtcyxxgzList = jtqkdcService.getJtcyList(xh, 7);
			data.put("jtcyxxgzList_70002", jtcyxxgzList);	
			/*��ͥ��Ա��Ϣ�������в�����*/
			int size13 = (7 - jtcyxxgzList.size())<=0?0:(7 - jtcyxxgzList.size()); 
			data.put("gzblankListJtcy", service.getBlankList(size13));
			
			/*ѧ��ѧ��*/
			String xsxz = service.getXsxz(xh);	
			/*ѧ���춯��ְ*/
			List<HashMap<String, String>> xjydList = new XjydjgService().getXsydList(myForm.getXh());
			data.put("xjydList_70002", xjydList);
			/*ѧ���춯��Ϣ����2�в�2��*/
			int size7 = (2 - xjydList.size())<=0?0:(2 - xjydList.size()); 
			data.put("blankListXjyd", service.getBlankList(size7));
			/*ѧ���춯��ְ*/
			List<HashMap<String, String>> xjydgzList = new XjydjgService().getXsydList(myForm.getXh());
			data.put("xjydgzList_70002", xjydgzList);
			/*ѧ���춯��Ϣ�������в�����*/
			int size14= (6 - xjydgzList.size())<=0?0:(6 - xjydgzList.size()); 
			data.put("gzblankListXjyd", service.getBlankList(size14));
			
			//������ְ
			XsxxtyglService xsxxtyglservice = new XsxxtyglService();
			List<HashMap<String, String>> jcrqjyy = xsxxtyglservice.getJcrqjyy(xh,5);
			data.put("jcrqjyyList_70002", jcrqjyy);
			/*������Ϣ��ְ�������в�����*/
			int size8 = (5 - jcrqjyy.size())<=0?0:(5 - jcrqjyy.size()); 
			data.put("blankListJcxx", service.getBlankList(size8));
			//���͸�ְ
			List<HashMap<String, String>> gzjcrqjyy = xsxxtyglservice.getJcrqjyy(xh,8);
			data.put("gzjcrqjyy_70002", gzjcrqjyy);
			/*������Ϣ��ְ����8�в�8��*/
			int size15 = (8 - gzjcrqjyy.size())<=0?0:(8 - gzjcrqjyy.size()); 
			data.put("gzblankListJcxx", service.getBlankList(size15));
			
			CxpyService cxpyService = new CxpyService();
			int nj = Integer.parseInt(Base.currNd);
			if(xsxxMap.get("nj")!=null && !"".equals(xsxxMap.get("nj"))){
				nj = Integer.parseInt(xsxxMap.get("nj"));
			}
			String diXn = String.valueOf(nj) + "-" + String.valueOf(nj+1); // ��һѧ��
			String deXn = String.valueOf(nj+1) + "-" + String.valueOf(nj+2); // �ڶ�ѧ��
			String dsXn = String.valueOf(nj+2) + "-" + String.valueOf(nj+3); // ����ѧ��
			String fourthXn = String.valueOf(nj+3) + "-" + String.valueOf(nj+4); // ����ѧ��
			String fivethXn = String.valueOf(nj+4) + "-" + String.valueOf(nj+5); // ����ѧ��
			// ��һѧ��,��һѧ��
			HashMap<String, String> diXnYiCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), diXn ,"01");
			data.put("diyicxpy", diXnYiCxpy.get("cxpy"));
			data.put("diyibzr", diXnYiCxpy.get("bzr"));
			data.put("diyiy", diXnYiCxpy.get("y"));
			data.put("diyim", diXnYiCxpy.get("m"));
			data.put("diyid", diXnYiCxpy.get("d"));
			data.put("diyicxdjmc", diXnYiCxpy.get("cxdjmc"));
			// ��һѧ�꣬�ڶ�ѧ��
			HashMap<String, String> diXnErCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), diXn ,"02");
			data.put("diercxpy", diXnErCxpy.get("cxpy"));
			data.put("dierbzr", diXnErCxpy.get("bzr"));
			data.put("diery", diXnErCxpy.get("y"));
			data.put("dierm", diXnErCxpy.get("m"));
			data.put("dierd", diXnErCxpy.get("d"));
			data.put("diercxdjmc", diXnErCxpy.get("cxdjmc"));
			// �ڶ�ѧ�� ��һѧ��
			HashMap<String, String> deXnYiCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), deXn ,"01");
			data.put("disancxpy", deXnYiCxpy.get("cxpy"));
			data.put("disanbzr", deXnYiCxpy.get("bzr"));
			data.put("disany", deXnYiCxpy.get("y"));
			data.put("disanm", deXnYiCxpy.get("m"));
			data.put("disand", deXnYiCxpy.get("d"));
			data.put("disancxdjmc", deXnYiCxpy.get("cxdjmc"));
			//�ڶ�ѧ�� �ڶ�ѧ��
			HashMap<String, String> deXnerCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), deXn ,"02");
			data.put("disicxpy", deXnerCxpy.get("cxpy"));
			data.put("disibzr", deXnerCxpy.get("bzr"));
			data.put("disiy", deXnerCxpy.get("y"));
			data.put("disim", deXnerCxpy.get("m"));
			data.put("disid", deXnerCxpy.get("d"));
			data.put("disicxdjmc", deXnerCxpy.get("cxdjmc"));
			// ����ѧ�� ��һѧ��
			HashMap<String, String> dsanXnYiCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), dsXn ,"01");
			data.put("diwucxpy", dsanXnYiCxpy.get("cxpy"));
			data.put("diwubzr", dsanXnYiCxpy.get("bzr"));
			data.put("diwuy", dsanXnYiCxpy.get("y"));
			data.put("diwum", dsanXnYiCxpy.get("m"));
			data.put("diwud", dsanXnYiCxpy.get("d"));
			data.put("diwucxdjmc", dsanXnYiCxpy.get("cxdjmc"));
			// ����ѧ�� �ڶ�ѧ��
			HashMap<String, String> dsanXnErCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), dsXn , "02");
			data.put("diliucxpy", dsanXnErCxpy.get("cxpy"));
			data.put("diliubzr", dsanXnErCxpy.get("bzr"));
			data.put("diliuy", dsanXnErCxpy.get("y"));
			data.put("dilium", dsanXnErCxpy.get("m"));
			data.put("diliud", dsanXnErCxpy.get("d"));
			data.put("diliucxdjmc", dsanXnErCxpy.get("cxdjmc"));
			// ����ѧ�� ��һѧ��
			HashMap<String, String> dsiXnYiCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), fourthXn ,"01");
			data.put("diqicxpy", dsiXnYiCxpy.get("cxpy"));
			data.put("diqibzr", dsiXnYiCxpy.get("bzr"));
			data.put("diqiy", dsiXnYiCxpy.get("y"));
			data.put("diqim", dsiXnYiCxpy.get("m"));
			data.put("diqid", dsiXnYiCxpy.get("d"));
			data.put("diqicxdjmc", dsiXnYiCxpy.get("cxdjmc"));
			// ����ѧ�� �ڶ�ѧ��
			HashMap<String, String> dsiXnErCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), fourthXn , "02");
			data.put("dibacxpy", dsiXnErCxpy.get("cxpy"));
			data.put("dibabzr", dsiXnErCxpy.get("bzr"));
			data.put("dibay", dsiXnErCxpy.get("y"));
			data.put("dibam", dsiXnErCxpy.get("m"));
			data.put("dibad", dsiXnErCxpy.get("d"));
			data.put("dibacxdjmc", dsiXnErCxpy.get("cxdjmc"));
			// ����ѧ�� ��һѧ��
			HashMap<String, String> dwuXnYiCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), fivethXn ,"01");
			data.put("dijiucxpy", dwuXnYiCxpy.get("cxpy"));
			data.put("dijiubzr", dwuXnYiCxpy.get("bzr"));
			data.put("dijiuy", dwuXnYiCxpy.get("y"));
			data.put("dijium", dwuXnYiCxpy.get("m"));
			data.put("dijiud", dwuXnYiCxpy.get("d"));
			data.put("dijiucxdjmc", dwuXnYiCxpy.get("cxdjmc"));
			// ����ѧ�� �ڶ�ѧ��
			HashMap<String, String> dwuXnErCxpy = cxpyService.getCxpyForXzyy(myForm.getXh(), fivethXn , "02");
			data.put("dishicxpy", dwuXnErCxpy.get("cxpy"));
			data.put("dishibzr", dwuXnErCxpy.get("bzr"));
			data.put("dishiy", dwuXnErCxpy.get("y"));
			data.put("dishim", dwuXnErCxpy.get("m"));
			data.put("dishid", dwuXnErCxpy.get("d"));
			data.put("dishicxdjmc", dwuXnErCxpy.get("cxdjmc"));

			//ѧҵ�ɼ����ܱ�
	 		List<HashMap<String, String>> xycjoneList = xsxxglService.getCjoneList(xh);//ѧҵ�ɼ�ѭ����һ����
	 		List<HashMap<String, String>> xycjtwoList = xsxxglService.getCjtwoList(xh);//ѧҵ�ɼ�ѭ���ڶ�����
			data.put("xycjoneList", xycjoneList);//ѧҵ�ɼ�ѭ����һ����
			data.put("xycjtwoList", xycjtwoList);//ѧҵ�ɼ�ѭ���ڶ�����
			int count1 = 1;//���1
			int count2 = 40;//���2
			List<HashMap<String, String>> xycjAllList = new ArrayList<HashMap<String, String>>();
			int xycjSize = xycjoneList.size();
			if(xycjSize < xycjtwoList.size()){
				xycjSize = xycjtwoList.size();
			}
			HashMap<String,String> map = null;
			for (int i = 0;i<xycjSize;i++){
				map = new HashMap<String,String>();
				if(i< xycjoneList.size()){
					map.put("row1",String.valueOf(count1));//�Ӹ����1-39
					map.put("kcmc1", xycjoneList.get(i).get("kcmc"));
					if(String.valueOf(nj).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq1", "һ");
						}else{
							map.put("xq1", "��");
						}
					}
					if(String.valueOf(nj+1).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq1", "��");
						}else{
							map.put("xq1", "��");
						}
					}
					if(String.valueOf(nj+2).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq1", "��");
						}else{
							map.put("xq1", "��");
						}
					}
					if(String.valueOf(nj+3).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq1", "��");
						}else{
							map.put("xq1", "��");
						}
					}
					if(String.valueOf(nj+4).equals(xycjoneList.get(i).get("y"))){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq1", "��");
						}else{
							map.put("xq1", "ʮ");
						}
					}
					map.put("cj1", xycjoneList.get(i).get("cj"));
					map.put("xf1", xycjoneList.get(i).get("xf"));
					count1 ++;
				}
				if(i< xycjtwoList.size()){
					map.put("row2", String.valueOf(count2));//�����40-78
					map.put("kcmc2", xycjtwoList.get(i).get("kcmc"));
					if(String.valueOf(nj) == xycjoneList.get(i).get("y")){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq2", "һ");
						}else{
							map.put("xq2", "��");
						}
					}
					if(String.valueOf(nj+1).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq2", "��");
						}else{
							map.put("xq2", "��");
						}
					}
					if(String.valueOf(nj+2).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq2", "��");
						}else{
							map.put("xq2", "��");
						}
					}
					if(String.valueOf(nj+3).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq2", "��");
						}else{
							map.put("xq2", "��");
						}
					}
					if(String.valueOf(nj+4).equals(xycjoneList.get(i).get("y")) ){
						if("01".equals(xycjoneList.get(i).get("xq"))){
							map.put("xq2", "��");
						}else{
							map.put("xq2", "ʮ");
						}
					}
					map.put("cj2", xycjtwoList.get(i).get("cj"));
					map.put("xf2", xycjtwoList.get(i).get("xf"));
					count2 ++;
				}
				xycjAllList.add(map);
			}
			data.put("xycjAllList",xycjAllList);
			if(null==xycjAllList){
				for (int i = 0; i < 39; i++) {
					xycjAllList.add(new HashMap<String, String>());
				}
			}else if (xycjAllList.size()<39){
				int y = xycjAllList.size();
				for (int i = 0; i <= 39-y; i++) {
					xycjAllList.add(new HashMap<String, String>());
				}
			}
			data.put("xycjAllList",xycjAllList);
			
			
			
			if("5".equals(xsxz)){
				//����ҽҩ��ְ
				file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","Xzyygzxjk_70002.xml",xh+"-"+xsxxMap.get("xm"));
			}else{
				//����ҽҩ��ְ
				file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","Xzyyzzxjk_70002.xml",xh+"-"+xsxxMap.get("xm"));
			}
		}
		//���������߼�����ѧУѧ�������Ի�
		else if(StringUtils.isEqual(Base.xxdm, "80152")){
			List<HashMap<String, String>> grjlList = xsxxglService.getGrjlList(xh);//���������߼��������˼�����Ϣѭ��
	 		List<HashMap<String, String>> xycjoneList = xsxxglService.getXycjoneList(xh);//ѧҵ�ɼ�ѭ����һ����
	 		List<HashMap<String, String>> xycjtwoList = xsxxglService.getXycjtwoList(xh);//ѧҵ�ɼ�ѭ���ڶ�����
	 		data.put("grjlList", grjlList);//���������߼��������˼�����Ϣѭ��
			data.put("xycjoneList", xycjoneList);//ѧҵ�ɼ�ѭ����һ����
			data.put("xycjtwoList", xycjtwoList);//ѧҵ�ɼ�ѭ���ڶ�����
			int size6=(3 - jtcyxxList.size())<=0?0:(3 - jtcyxxList.size());//��ͥ��Ա��Ϣ
			int size7=(3 - grjlList.size())<=0?0:(3 - grjlList.size());//���������߼��������˼�����Ϣѭ��
			data.put("blankList6", service.getBlankList(size6));//��ͥ��Ա��Ϣ
			data.put("blankList7", service.getBlankList(size7));///���������߼��������˼�����Ϣѭ��
			List<HashMap<String, String>> xycjAllList = new ArrayList<HashMap<String, String>>();
			int xycjSize = xycjoneList.size();
			if(xycjSize < xycjtwoList.size()){
				xycjSize = xycjtwoList.size();
			}
			HashMap<String,String> map = null;
			for (int i = 0;i<xycjSize;i++){
				map = new HashMap<String,String>();
				if(i< xycjoneList.size()){
					map.put("kc1", xycjoneList.get(i).get("kc1"));
					map.put("xn1xq1", xycjoneList.get(i).get("xn1xq1"));
					map.put("xn1xq2", xycjoneList.get(i).get("xn1xq2"));
					map.put("xn2xq1", xycjoneList.get(i).get("xn2xq1"));
					map.put("xn2xq2", xycjoneList.get(i).get("xn2xq2"));
					map.put("xn3xq1", xycjoneList.get(i).get("xn3xq1"));
					map.put("xn3xq2", xycjoneList.get(i).get("xn3xq2"));
				}
				if(i< xycjtwoList.size()){
					map.put("kc2", xycjtwoList.get(i).get("kc2"));
					map.put("xn4xq1", xycjtwoList.get(i).get("xn4xq1"));
					map.put("xn4xq2", xycjtwoList.get(i).get("xn4xq2"));
					map.put("xn5xq1", xycjtwoList.get(i).get("xn5xq1"));
					map.put("xn5xq2", xycjtwoList.get(i).get("xn5xq2"));
					map.put("xn6xq1", xycjtwoList.get(i).get("xn6xq1"));
					map.put("xn6xq2", xycjtwoList.get(i).get("xn6xq2"));
				}
				xycjAllList.add(map);
			}
			data.put("xycjAllList",xycjAllList);
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			data.put("csrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.month));//������������
			data.put("rxrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("rxrq"),FomartDateType.day));//��ѧ����������
			data.put("byny",DateTranCnDate.fomartDateToCn(xsxxMap.get("byny"),FomartDateType.day));//��ҵ����������
			//���֤�ŷֽ⿪ʼ
			String xssfzh =  StringUtil.isNull(xsjbxx.get("sfzh")) ? "" : xsjbxx.get("sfzh");
			for (int i = 0,j = xssfzh.length(); i < j; i++) {
				data.put("sfzh" + i, String.valueOf(xssfzh.charAt(i)));
			}	
			if(null==xycjAllList){
				for (int i = 0; i < 10; i++) {
					xycjAllList.add(new HashMap<String, String>());
				}
			}else if (xycjAllList.size()<10){
				int y = xycjAllList.size();
				for (int i = 0; i <= 10-y; i++) {
					xycjAllList.add(new HashMap<String, String>());
				}
			}
			data.put("xycjAllList",xycjAllList);
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","hzqcgjjgxxxjk_80152.xml",xh+"-"+xsxxMap.get("xm"));
		}
		//��ְͨҵ����ѧԺ����ͨũҵְҵ����ѧԺ��
		else if(StringUtils.isEqual(Base.xxdm, "12684")){
			data.put("csrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.day));//������������
			data.put("rxrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("rxrq"),FomartDateType.day));//��ѧ����������
			
			//��ͥ��Ա��Ϣ
			List<HashMap<String, String>> jtcyxxList1 = new ArrayList<HashMap<String, String>>();
			HashMap<String,String> map = null;
			int jtcyxxsize = 4;
			if(jtcyxxList.size() > jtcyxxsize){
				for (int i = 0;i < jtcyxxsize;i++){
					map = new HashMap<String,String>();
					map.put("jtcygxmc", jtcyxxList.get(i).get("jtcygxmc"));
					map.put("cyxm", jtcyxxList.get(i).get("cyxm"));
					map.put("ylzd4", jtcyxxList.get(i).get("ylzd4"));
					map.put("cyxxdw", jtcyxxList.get(i).get("cyxxdw"));
					map.put("cyzy", jtcyxxList.get(i).get("cyzy"));
					map.put("cyzzmm", jtcyxxList.get(i).get("cyzzmm"));
					map.put("cynsr", jtcyxxList.get(i).get("cynsr"));
					jtcyxxList1.add(map);
				}
				data.put("jtcyxxList1",jtcyxxList1);
			} else {
				data.put("jtcyxxList1", jtcyxxList);
			}

			int size1=(3 - jtcyxxList.size())<=0?0:(3 - jtcyxxList.size());
			data.put("blankList1", service.getBlankList(size1));//��ͥ��Ա��Ϣ
		    //==���еȵڼ�����begin==
			List<HashMap<String, String>> cxdtList1 = new ArrayList<HashMap<String, String>>();
			HashMap<String,String> cxdtMap = null;
			List<HashMap<String, String>> cxdtList = xsxxglService.getCxdt(xh);
			for (int i = 0;i < cxdtList.size();i++){
				cxdtMap = new HashMap<String,String>();
				cxdtMap.put("xn", cxdtList.get(i).get("xn"));
				cxdtMap.put("xqmc", cxdtList.get(i).get("xqmc"));
				cxdtMap.put("cxdjmc", cxdtList.get(i).get("cxdjmc"));
				cxdtMap.put("cxpy", HtmlUtil.xmlZy(cxdtList.get(i).get("cxpy")));
				cxdtList1.add(cxdtMap);
			}
			data.put("cxdtList", cxdtList1);
			int size5=(10 - cxdtList.size())<=0?0:(10 - cxdtList.size());
			data.put("blankList2", service.getBlankList(size5));
			//==���еȵڼ�����end==
			//==�����begin==
			List<HashMap<String, String>> hjList = xsxxglService.gethjqk(xh);
			data.put("hjList", hjList);
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","ntzyjsxy_12684.xml",xh+"-"+xsxxMap.get("xm"));
		}
		//�Ϻ����ѧԺ������ѧ�ǼǱ� ���Ի�
		else if(StringUtils.isEqual(Base.xxdm, "11458")){
			// ��ѧ����
			String rxrq = xsxxMap.get("rxrq");
			if(rxrq != null && rxrq.contains("-")){
				rxrq = rxrq.substring(0, rxrq.lastIndexOf("-")).replace("-", "");
			}
			data.put("rxrq_11458", rxrq);
			// ��������
			String csrq = xsxxMap.get("csrq");
			if(csrq != null){
				csrq = csrq.replaceAll("-", "");
			}
			data.put("csrq_11458", csrq);
			// ˼����ʦ�б�
			XsxxtyglService xsxxtyglService = new XsxxtyglService();
			List<HashMap<String, String>> szxxList = xsxxtyglService.getFdyBzrListByBjdm((String) xsxxMap.get("bjdm"));
			HashMap<String, String> fdyBzrOne = new HashMap<String, String>();
			for (HashMap<String, String> szxxMap : szxxList) {
				fdyBzrOne = szxxMap;
			}
//			// ����Ա��ʦ��Ϣ�б�
//			List<HashMap<String, String>> fdyList = xsxxtyglService.getSzxxList("fdy", szxxList);
//			// ��������ʦ��Ϣ�б�
//			List<HashMap<String, String>> bzrList = xsxxtyglService.getSzxxList("bzr", szxxList);
//			for (HashMap<String, String> fdyMap : fdyList) {
//				fdyBzrOne = fdyMap;
//			}
//			for (HashMap<String, String> bzrMap : bzrList) {
//				fdyBzrOne = bzrMap;
//			}
			data.put("fdyBzrOne", fdyBzrOne);
			
			//��ͥ��Ա��Ϣ
			List<HashMap<String, String>> jtcyxxList1 = new ArrayList<HashMap<String, String>>();
			HashMap<String,String> map = null;
			int jtcyxxsize = 2;
			if(jtcyxxList.size() > jtcyxxsize){
				for (int i = 0;i < jtcyxxsize;i++){
					map = new HashMap<String,String>();
					map.put("jtcygxmc", jtcyxxList.get(i).get("jtcygxmc"));
					map.put("cyxm", jtcyxxList.get(i).get("cyxm"));
					map.put("cynl", jtcyxxList.get(i).get("cynl"));
					map.put("cyxxdw", jtcyxxList.get(i).get("cyxxdw"));
					map.put("ylzd2", jtcyxxList.get(i).get("ylzd2"));
					map.put("cyzy", jtcyxxList.get(i).get("cyzy"));
					map.put("cylxdh", jtcyxxList.get(i).get("cylxdh"));
					jtcyxxList1.add(map);
				}
				data.put("jtcyxxList1",jtcyxxList1);
			} else {
				data.put("jtcyxxList1", jtcyxxList);
			}
			
			int jtcyxxBlankSize=(jtcyxxsize - jtcyxxList.size())<=0?0:(jtcyxxsize - jtcyxxList.size());
			data.put("jtcyxxBlankList", service.getBlankList(jtcyxxBlankSize));
			int xlshjlBlankSize=(4 - xlshjlList.size())<=0?0:(4 - xlshjlList.size());
			data.put("xlshjlBlankList", service.getBlankList(xlshjlBlankSize));
			// ��ӡ����Ϊ��ǰ����
			
			data.put("dyrq", dyrq);
//			// ������� ����ѧ����Ϣ���
//			String jtqkdcYlzd1 = (jtqkdc != null) ? jtqkdc.getYlzd1() : "";
//			data.put("jtqkdcYlzd1", jtqkdcYlzd1);
			
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_11458.xml",xh+"-"+xsxxMap.get("xm"));
		}
		//������ҵ��ѧ������������Ϣ�ǼǱ� ���Ի�
		else if(StringUtils.isEqual(Base.xxdm, "10022")){
			ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
			//��ĸ����Ϣ
			for (HashMap<String, String> jtcyxxMap : jtcyxxList) {
				String jtcygxmc = jtcyxxMap.get("jtcygxmc"); // �뱾�˹�ϵ
				String jtcyxm = jtcyxxMap.get("cyxm"); // ����
				String zzmmmc = jtcyxxMap.get("zzmmmc"); //������ò
				String cynl = jtcyxxMap.get("cynl"); //����
				String lxdh = jtcyxxMap.get("cylxdh"); //����
				String zy = jtcyxxMap.get("cyzy"); //ְҵ
				String gzdw = jtcyxxMap.get("cyxxdw"); // ������λ����ַ
				if("����".equals(jtcygxmc)){
					data.put("fqxm", jtcyxm);
					data.put("fqgzdw", gzdw);
					data.put("fqzy", zy);
					data.put("fqlxdh", lxdh);
				}else if("ĸ��".equals(jtcygxmc)){
					data.put("mqxm", jtcyxm);
					data.put("mqgzdw", gzdw);
					data.put("mqzy", zy);
					data.put("mqlxdh", lxdh);
				}else {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("cyxm", jtcyxm);
					map.put("jtcygxmc", jtcygxmc);
					map.put("cynl", cynl);
					if ("�й�������Ԥ����Ա".equals(zzmmmc)) {
						map.put("zzmmmc", "�й�Ԥ����Ա");
					}else if ("�й���������Ա".equals(zzmmmc)) {
						map.put("zzmmmc", "�й���Ա");
					}else if ("�й�����������������Ա".equals(zzmmmc)) {
						map.put("zzmmmc", "������Ա");
					}else {
						map.put("zzmmmc", zzmmmc);
					}
					map.put("cygzdw", gzdw);
					arrayList.add(map);
				}
			}
			// ����ѧ����ͥ������Ϣ
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(xh);
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
			if (null == jtqkmodel) {
				jtqkmodel = new JtqkdcForm();
			}
			data.put("jtqk", jtqkmodel);
			data.put("photo", photo);
			data.put("jtqtcylist", arrayList);
			int size1=(3 - arrayList.size())<=0?0:(3 - arrayList.size()); 
			data.put("cyblankList", service.getBlankList(size1));
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","bjly_10022.xml",xh+"-"+xsxxMap.get("xm"));
		}
		//�㽭��ѧ������ѧ�ǼǱ�
		else if (StringUtils.isEqual(Base.xxdm, "10335")){
			//����ѧϰ�͹�������
			List<HashMap<String, String>> xxhgzjlList = xsxxglService.getXxhgzjlList(xh);
			data.put("xxhgzjlList", xxhgzjlList);
			int size1=(3 - xxhgzjlList.size())<=0?0:(3 - xxhgzjlList.size());
			data.put("blankList1", service.getBlankList(size1));
			//��ͥ��Ҫ��Ա����Ҫ����ϵ
			int size7=(3 - jtcyxxList.size())<=0?0:(3 - jtcyxxList.size()); 
			data.put("blankList2", service.getBlankList(size7));
			data.put("byny",DateTranCnDate.fomartDateToCn(xsxxMap.get("byny"),FomartDateType.day));//��ҵ����������
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xsrxdjb_10335.xml",xh+"-"+xsxxMap.get("xm"));}
		//�㽭����ְҵѧԺ
		else if (StringUtils.isEqual(Base.xxdm, "12867")){
			//��ͥ��Ҫ��Ա����Ҫ����ϵ
			int size7=(3 - jtcyxxList.size())<=0?0:(3 - jtcyxxList.size()); 
			data.put("blankList2", service.getBlankList(size7));
			//��ø���Ա��Ϣ
			HashMap<String, String> fdyxx = service.getFdyxx(xh);
			data.put("fdyxx", fdyxx);
			//��ð�������Ϣ
			HashMap<String,	String> bzrxx = service.getBzrxx(xh);
			data.put("bzrxx", bzrxx);
			//����ѧϰ�͹�������
			List<HashMap<String, String>> xxhgzjlList = xsxxglService.getXxhgzjlList(xh);
			data.put("xxhgzjlList", xxhgzjlList);
			int size1=(3 - xxhgzjlList.size())<=0?0:(3 - xxhgzjlList.size());
			data.put("blankList1", service.getBlankList(size1));
			//��ͥ���������϶�
			HashMap<String,	String> knrd = service.getKnrd(xh);
			String sfpks ="��";
			if(null!=knrd.get("xh")&& !"��ͥ���ò�����".equals(knrd.get("dcmc"))){
				sfpks="��";
			}
			data.put("knrd", knrd);
			data.put("sfpks", sfpks);
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","zjlyzyxy_xsdjb_12867.xml",xh+"-"+xsxxMap.get("xm"));
		}
		else if(StringUtils.isEqual(Base.xxdm, "11660"))
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_11660.xml",xh+"-"+xsxxMap.get("xm"));
		else if(StringUtils.isEqual(Base.xxdm, "13033")&&(null!=hnjdDjb&&hnjdDjb.equals("djb"))){
			
			
			List<HashMap<String, String>> xjydList = new XjydjgService().getXsydList(myForm.getXh());// ����ѧ�Ų�ѯѧ���춯�б�
			//���⴦��
			for (int i = 1; i <= 3; i++) {
				String key = "jtcy" + i;
				if(i <= jtcyxxList.size()){
					data.put(key, jtcyxxList.get(i - 1));
				}else{
					data.put(key, new HashMap<String, String>());
				}
			}
			Map<String,String> map=null;
			
			for (int i = 1; i <= 5; i++) {
				String key = "xjyd" + i;
				if(i <= xjydList.size()){
					map=xjydList.get(i - 1);
					map.put("bdyy", map.get("ydqxjlbmc")+" -> "+map.get("ydlbmc")); //�䶯ԭ��
					data.put(key,map);
					
				}else{
					data.put(key, new HashMap<String, String>());
				}
			}
			
			for (int i = 1; i <= 6; i++) {
				String key = "xxjl" + i;
				if(i <= xlshjlList.size()){
					data.put(key, xlshjlList.get(i - 1));
				}else{
					data.put(key, new HashMap<String, String>());
				}
			}
			
			for (int i = 1; i <= 4; i++) {
				String key = "pj" + i;
				if(i <= pjList.size()){
					pjList.get(i - 1).put("xxmc", Base.xxmc);
					data.put(key, pjList.get(i - 1));
				}else{
					data.put(key, new HashMap<String, String>());
				}
			}
			
			for (int i = 1; i <= 4; i++) {
				String key = "wjcf" + i;
				if(i <= wjcfList.size()){
					wjcfList.get(i - 1).put("xxmc", Base.xxmc);
					data.put(key, wjcfList.get(i - 1));
				}else{
					data.put(key, new HashMap<String, String>());
				}
			}
			
			//���⴦��
			data.put("xjydList", xjydList);
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_13033.xml",xh+"-"+xsxxMap.get("xm"));
		}else if(StringUtils.isEqual(Base.xxdm, "10653")) {
			//���⴦��
			int size5=(3 - jtcyxxList.size())<=0?0:(3 - jtcyxxList.size());
			data.put("blankList5", service.getBlankList(size5));
			String sfdb = xsxxglService.getSfdb(xh);
			data.put("sfdb", sfdb == null?"":sfdb);
			data.put("kslb", xsxxMap.get("kslbmc"));
			data.put("csrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.day));
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_10653.xml",xh+"-"+xsxxMap.get("xm"));
		}
		else if(StringUtils.isEqual(Base.xxdm, "50394")){
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xsdjb_50394.xml",xh+"-"+xsxxMap.get("xm"));
		}else if(StringUtils.isEqual(Base.xxdm, "13943")){
			//������·ְҵ����ѧԺ
			List<HashMap<String, String>> xjydList = new XjydjgService().getXsydList(myForm.getXh());// ����ѧ�Ų�ѯѧ���춯�б�
			jtcyxxList = jtqkdcService.getJtcyList(xh, 6);
			pjjgService.addBlankList(jtcyxxList, 6 - jtcyxxList.size());
			data.put("jtcyxxList_13943", jtcyxxList);
			//���ͼ�¼�еĽ���ϲ�У������
			pjList.addAll(newservice.getXsxwhjqkList(xh));
			data.put("pjListSize_13943", pjList.size());
			pjjgService.addBlankList(pjList, 1 - pjList.size());
			data.put("pjList_13943", pjList);
			data.put("wjcfListSize_13943", wjcfList.size());
			pjjgService.addBlankList(wjcfList, 1 - wjcfList.size());
			data.put("wjcfList_13943", wjcfList);
			data.put("xjydListSize_13943", xjydList.size());
			pjjgService.addBlankList(xjydList, 1 - xjydList.size());
			data.put("xjydList_13943", xjydList);
			// ======== ��ȡ����������Ϣ begin ========
			CxpyService cxpyService = new CxpyService();
			int nj = Integer.parseInt(Base.currNd);
			if(xsxxMap.get("nj")!=null && !"".equals(xsxxMap.get("nj"))){
				nj = Integer.parseInt(xsxxMap.get("nj"));
			}
			String diXn = String.valueOf(nj) + "-" + String.valueOf(nj+1); // ��һѧ��
			String deXn = String.valueOf(nj+1) + "-" + String.valueOf(nj+2); // �ڶ�ѧ��
			String dsXn = String.valueOf(nj+2) + "-" + String.valueOf(nj+3); // ����ѧ��
			String fourthXn = String.valueOf(nj+3) + "-" + String.valueOf(nj+4); // ����ѧ��
			// ��һѧ��
			HashMap<String, String> diXnCxpy = cxpyService.getCxpyByXhXn(myForm.getXh(), diXn);
			if(!"null".equals(diXnCxpy)){
				String diXn1 = diXnCxpy.get("xn");
				if(!"".equals(diXn1)&&StringUtils.isNotNull(diXn1)){
					String[] xnArr1= diXn1.split("-");
					data.put("diXn", xnArr1[1]);
				}else{
					data.put("diXn", "");
				}
			}else{
				data.put("diXn", "");
			}
			diXnCxpy.put("xnmc", "��һѧ��");
//			data.put("diXnCxpy", diXnCxpy);
			
			// �ڶ�ѧ��
			HashMap<String, String> deXnCxpy = cxpyService.getCxpyByXhXn(myForm.getXh(), deXn);
			if(!"".equals(deXnCxpy)){
				String deXn1 = deXnCxpy.get("xn");
				if(!"".equals(deXn1)&&StringUtils.isNotNull(deXn1)){
					String[] xnArr2= deXn1.split("-");
					data.put("deXn", xnArr2[1]);
				}else{
					data.put("deXn", "");
				}
			}else{
				data.put("deXn", "");
			}
			deXnCxpy.put("xnmc", "�ڶ�ѧ��");
//			data.put("deXnCxpy", deXnCxpy);
			
			// ����ѧ��
			HashMap<String, String> currXnCxpy = cxpyService.getCxpyByXhXn(myForm.getXh(), dsXn);
			if(!"".equals(currXnCxpy)){
				String dsXn1 = currXnCxpy.get("xn");
				if(!"".equals(dsXn1)&&StringUtils.isNotNull(dsXn1)){
					String[] xnArr3= dsXn1.split("-");
					data.put("dsXn", xnArr3[1]);
				}else{
					data.put("dsXn", "");
				}
			}else{
				data.put("dsXn", "");
			}
			currXnCxpy.put("xnmc", "����ѧ��");
//			data.put("currXnCxpy", currXnCxpy);
			
			// ����ѧ��
			HashMap<String, String> fourthXnCxpy = cxpyService.getCxpyByXhXn(myForm.getXh(), fourthXn);
			fourthXnCxpy.put("xnmc", "����ѧ��");
			List<HashMap<String,String>> cxpyList = new ArrayList<HashMap<String,String>>();
			cxpyList.add(diXnCxpy);
			cxpyList.add(deXnCxpy);
			cxpyList.add(currXnCxpy);
			cxpyList.add(fourthXnCxpy);
			data.put("cxpyList", cxpyList);
			//Ժϵ���
			/*String yxyj = new XsxxtyglService().getYxyj(myForm.getXh());
			request.setAttribute("yxyj", yxyj);*/
			//XsxxtyglForm myForm
			//ѧ�ƴ���
			int xzInt = 2;
			//Ժϵ�����������
			String yxyj = xsxxMap.get("shgxgzdw1");
			data.put("yxyj", yxyj);
			String xzStr = xsxxMap.get("xz");
			int xz_13943 = Integer.valueOf(xzStr);
			int bynd = xz_13943 + nj ;
			data.put("bynd", String.valueOf(bynd));
			if(StringUtils.isNotNull(xzStr)){
				xzInt = Integer.valueOf(xzStr);
				if(xzInt>4){
					xzInt = 4;
				}
			}
			data.put("xzInt", xzInt);
			// ======== ��ȡ����������Ϣ end ========
			
			// ======== ��ȡ�����뵳 begin ========
			DtxxjgService dtxxjgService = new DtxxjgService();
			HashMap<String, String> rtMap = dtxxjgService.getGrDtjgxx(myForm.getXh(), "02"); // ����
			data.put("rtMap", rtMap);
			HashMap<String, String> rdMap = dtxxjgService.getGrDtjgxx(myForm.getXh(), "11"); // �뵳
			data.put("rdMap", rdMap);
			// ======== ��ȡ�����뵳 end ========
			String xmlFile = "";
			if(null!=hnjdDjb&&hnjdDjb.equals("djb")){
				xmlFile = "tyxjk_13943.xml";
				pjjgService.addBlankList(xlshjlList, 5 - xlshjlList.size());
			}else{
				xmlFile = "xsdjb_13943.xml";
				pjjgService.addBlankList(xlshjlList, 6 - xlshjlList.size());
			}
			data.put("xlshjlList_13943", xlshjlList);
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx",xmlFile,xh+"-"+xsxxMap.get("xm"));
		}
		else if("12036".equals(Base.xxdm)){
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xsdjb_12036.xml",xh+"-"+xsxxMap.get("xm"));
		}else if("12898".equals(Base.xxdm)){
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_12898.xml",xh+"-"+xsxxMap.get("xm"));
		}else if("10466".equals(Base.xxdm)){//����ũҵ��ѧ���Ի�
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_10466.xml",xh+"-"+xsxxMap.get("xm"));
		}else if("88003".equals(Base.xxdm)){
			data.put("csrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.day));
			data.put("rxrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("rxrq"),FomartDateType.month));//��ѧ����
			for(int xn=1;xn<=5;xn++){
				for(int xq=1;xq<=2;xq++){
					HashMap<String,String> cxpdmap=service.getCxpd(xh, String.valueOf(xn), "0"+String.valueOf(xq));
					String py=HtmlUtil.xmlZy(cxpdmap.get("py"));
					cxpdmap.put("py", py);
					data.put("cxpd"+String.valueOf(xn)+"0"+String.valueOf(xq), cxpdmap);
				}
			}
			List<HashMap<String, String>> xjydList = new XjydjgService().getXsydList(myForm.getXh());
			Map<String,String> map=null;
			for (int i = 1; i <= 2; i++) {
				String key = "xjyd" + i;
				if(i <= xjydList.size()){
					map=xjydList.get(i - 1);
					data.put(key,map);
				}else{
					data.put(key, new HashMap<String, String>());
				}
			}
			
			if(xsxxMap.get("pyccmc").contains("��ְ")){
				List<HashMap<String,String>>jxscjList=service.getXnXscj(xh);
				data.put("jxscjList", jxscjList);
				file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_88003_zz.xml",xh+"-"+xsxxMap.get("xm"));
			}else{//��ְ
				for(int xn=1;xn<=5;xn++){
					List<HashMap<String, String>> cjListNew1 = null;
					List<HashMap<String, String>> cjListNew2 = null;
					for(int xq=1;xq<=2;xq++){
						List<HashMap<String, String>> cjList = xsxxglService.getXnXqcj(String.valueOf(xn-1),String.valueOf(xn-1),"0"+String.valueOf(xq),xh);
						List<HashMap<String, String>> cjListNew=new ArrayList<HashMap<String, String>>();
						//����8����ȥ������8������
						for(int i=0;i<cjList.size()&&i<8;i++){
							cjListNew.add(cjList.get(i));
						}
						for(int i=0;i<8-cjList.size();i++){
							cjListNew.add(new HashMap<String,String>());
						}
						if(xq==1){
							cjListNew1=cjListNew;
						}else{
							cjListNew2=cjListNew;
						}
					}
					HashMap<String,String> cjMapA=cjListNew1.get(0);//ÿѧ���һ��
					for(Map.Entry<String, String> entry:cjListNew2.get(0).entrySet()){
						cjMapA.put(entry.getKey()+"2",entry.getValue());
					}
					data.put("cjMapA"+xn, cjMapA);
					List<HashMap<String, String>> cjListLeft=new ArrayList<HashMap<String, String>>();
					for(int i=1;i<8;i++){
						HashMap<String,String> cjMapB=cjListNew1.get(i);
						for(Map.Entry<String, String> entry:cjListNew2.get(i).entrySet()){
							cjMapB.put(entry.getKey()+"2",entry.getValue());
						}
						cjListLeft.add(cjMapB);
					}
					data.put("cjListLeft"+xn, cjListLeft);
				}
				
				file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_88003_gz.xml",xh+"-"+xsxxMap.get("xm"));
			}
			
		}
		//������ͨ��ѧ��ѧԺ
		else if(StringUtils.isEqual(Base.xxdm, "13431")){
			//����
			List<HashMap<String, String>> pjjg5line = pjjgService.getHjqkByXhMap(xh,5);
			data.put("pjjg5line", pjjg5line);
			//��ͥ��Ա
			jtcyxxList = jtqkdcService.getJtcyList(xh, 5);
			data.put("jtcyxxList_13431", jtcyxxList);
			//�۲�ɼ�
			List<HashMap<String, String>> zcfsList_13431 = new ZcfsService().getZcfsListForWord(myForm.getXh(),5);// ͨ��ѧ�Ų�ѯ�۲����
			data.put("zcfsList_13431", zcfsList_13431);
			//ѧ���춯
			List<HashMap<String, String>> xjydList_13431 = new XjydjgService()
					.getXsydListForWord(myForm.getXh(),2);// ����ѧ�Ų�ѯѧ���춯�б�
			data.put("xjydList_13431", xjydList_13431);

			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_13431.xml",xh+"-"+xsxxMap.get("xm"));
		}
		else if(StringUtils.isEqual(Base.xxdm, "10876")){
			List<HashMap<String, String>> hjqkList = pjjgService.getHjqkInfoMap(xh);
			data.put("hjqkList", hjqkList);
			int size_10876=(4 - hjqkList.size())<=0?0:(4 - hjqkList.size());
			data.put("blank_10876", service.getBlankList(size_10876));
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_10876.xml",xh+"-"+xsxxMap.get("xm"));
		}else if(StringUtils.isEqual(Base.xxdm, "11527")){
			
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_11527.xml",xh+"-"+xsxxMap.get("xm"));
		}else{
			String templatePath = "classpath://templates//xsxx//tyxjk_"+Base.xxdm+".xml";
			try{
				ResourceUtils.getFile(templatePath);
				file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_"+Base.xxdm+".xml",xh+"-"+xsxxMap.get("xm"));
			}catch (Exception e) {
				file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk.xml",xh+"-"+xsxxMap.get("xm"));
			}
		}
		return file;
		
	}
	
	
	
	/**
	 * 
	 * @����:(���ؽ����Ƽ�ʦ����ѧ������ѧ�ǼǱ�) �㽭��ý�����ǼǱ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-5 ����10:39:24
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
	public ActionForward getDjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		
		File wordFile = getDjbWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	
	/**
	 * 
	 * @����:(���ؽ����Ƽ�ʦ����ѧ������ѧ�ǼǱ�zip)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-5 ����10:43:44
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
	public ActionForward getDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getDjbWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	
	private File getDjbWord(XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		String xh = myForm.getXh();
		User user = getUser(request);
		String hnjdDjb = request.getParameter("hnjdDjb");
		myForm.setUser(user);
		Map<String,Object> data = new HashMap<String,Object>();
		PjjgService pjjgService = new PjjgService();
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//ѧ��������Ϣ
 		List<HashMap<String, String>> pjList = pjjgService.getPjpyInfoMap(xh);
 		List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
 		List<HashMap<String, String>> xlshjlList = xsxxglService.getXlshjlList(xh);
		HashMap<String, String> zpMap=service.getZpMap(myForm,request,"zp");
		data.putAll(xsxxMap);
		data.put("xxmc", Base.xxmc);
		data.put("pjList", pjList);
		data.put("jtcyxxList", jtcyxxList);
		data.put("xlshjlList", xlshjlList);
		data.putAll(zpMap);
		int size=(4 - pjList.size())<0?0:(4 - pjList.size());
		int size2=(5 - jtcyxxList.size())<=0?0:(5 - jtcyxxList.size());
		int size3=(13 - xlshjlList.size())<=0?0:(13 - xlshjlList.size());
		int size4=(14 - jtcyxxList.size())<=0?0:(14 - jtcyxxList.size());
		int size5=(4 - xlshjlList.size())<=0?0:(4 - xlshjlList.size());
		int size6=(4 - jtcyxxList.size())<=0?0:(4 - jtcyxxList.size());
		data.put("blankList", service.getBlankList(size));
		data.put("blankList2", service.getBlankList(size2));
		data.put("blankList3", service.getBlankList(size3));
		data.put("blankList4", service.getBlankList(size4));
		data.put("blankList5", service.getBlankList(size5));
		data.put("blankList6", service.getBlankList(size6));
		data.put("qfqk", service.getQfqk(xh));  //Ƿ�����
		
		//ѧ����Ƭ
		XsxxService xsxxService = new XsxxService();
		InputStream is = xsxxService.getPhotoStream(xh);
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		
		if (StringUtil.isNull(photo)){
			//ȡĬ����Ƭ
			photo = xsxxService.getDefaultPhotoBase64(request);
		}
		
		if(photo == null){
			photo = "";
		}
		
		data.put("photo", photo);
		
		//��ȡѧ��ס����Ϣ
		CwglService zsxxService = new CwglService();
		HashMap<String,String> zsxx =zsxxService.getCwForXh(xh);
		data.put("zsxx", zsxx);
		
		String fqsjh = null; //��ȡѧ�������ֻ��š�ĸ���ֻ���
		String mqsjh = null;
		
		for (int i = 0; i < jtcyxxList.size(); i++) {
			if("����".equals(jtcyxxList.get(i).get("jtcygxmc"))){
				fqsjh = jtcyxxList.get(i).get("jtcylxdh1");
			}
			if("ĸ��".equals(jtcyxxList.get(i).get("jtcygxmc"))){
				mqsjh = jtcyxxList.get(i).get("jtcylxdh1");
			}
		}
		data.put("fqsjh", fqsjh);
		data.put("mqsjh", mqsjh);
		HashMap<String,String> fdyxm =service.getFdyByBj(xsxxMap.get("bjdm"));
		data.put("fdyxms", fdyxm);
		// ��ǰʱ��
		Calendar now = Calendar.getInstance();
		String year = String.valueOf(now.get(Calendar.YEAR));
		String month = String.valueOf(now.get(Calendar.MONTH) + 1);
		String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
		data.put("year", year);
		data.put("month", month);
		data.put("day", day);
		
		File file = null;
		
		if(Base.xxdm.equals(Globals.XXDM_ZJCMXY)){ //�㽭��ýѧԺѧ�������ǼǱ�
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","kjdjb_11647.xml",xh+"-"+xsxxMap.get("xm"));
		}else if(Base.xxdm.equals(Globals.XXDM_YKZJX)) {
			//����ְҵ����ѧУ��ѧ�ǼǱ� ���Ի�
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xsrxdcb_90211.xml",xh+"-"+xsxxMap.get("xm"));
		}else if(Base.xxdm.equals(Globals.XXDM_CQSXYYGDZKXX)) {
			//������Ͽҽҩ�ߵ�ר��ѧУ��ѧ�ǼǱ� ���Ի�
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xsrxdjb_14008.xml",xh+"-"+xsxxMap.get("xm"));
		}else {
			//�����Ƽ�ʦ����ѧ��ѧ�ǼǱ� ���Ի�
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tyxjk_11318.xml",xh+"-"+xsxxMap.get("xm"));
		}
		
		return file;
		
	}
	
	/**
	 * @����:���ػ���ʦ����ѧѧ����
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-15 ����02:44:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	public ActionForward getXskpWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		
		File wordFile = getXskpWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @����:���ػ���ʦ����ѧѧ����
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-15 ����02:44:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	public ActionForward getXskpZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getXskpWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	/**
	 * @����:���ػ���ʦ����ѧѧ����
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-10-15 ����02:44:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	private File getXskpWord(XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		String xh = myForm.getXh();
		User user = getUser(request);
		myForm.setUser(user);
		Map<String,Object> data = new HashMap<String,Object>();
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//ѧ��������Ϣ
 		List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh); //��ͥ��Ա
 		List<HashMap<String, String>> xlshjlList = xsxxglService.getXlshjlList(xh); // ѧ����ᾭ��
		data.putAll(xsxxMap);
		data.put("xxmc", Base.xxmc);
		data.put("jtcyxxList", jtcyxxList);
		data.put("xlshjlList", xlshjlList);
		int xlshjlSize=(5 - xlshjlList.size())<=0?0:(5 - xlshjlList.size());
		int jtcyxxSize=(4 - jtcyxxList.size())<=0?0:(4 - jtcyxxList.size());
		data.put("xlshjlBlankList", service.getBlankList(xlshjlSize));
		data.put("jtcyxxBlankList", service.getBlankList(jtcyxxSize));
		// ========����ģ�����ƣ���Ҫ������ ��ѧ���ɲ��ϲ���һ����� begin==============
 		List<HashMap<String, String>> pjXsgbxxList = xsxxglService.getPjXsgbxxList(xh);
 		data.put("pjXsgbxxList", pjXsgbxxList);
 		int pjXsgbxxSize=(2 - pjXsgbxxList.size())<=0?0:(2 - pjXsgbxxList.size());
		data.put("pjXsgbxxBlankList", service.getBlankList(pjXsgbxxSize));
		// ========����ģ�����ƣ���Ҫ������ ��ѧ���ɲ��ϲ���һ����� end==============
		//ѧ����Ƭ
		XsxxService xsxxService = new XsxxService();
		InputStream is = xsxxService.getPhotoStream(xh);
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		if (StringUtil.isNull(photo)){
			//ȡĬ����Ƭ
			photo = xsxxService.getDefaultPhotoBase64(request);
		}
		if(photo == null){
			photo = "";
		}
		data.put("photo", photo);
		
		//��ȡѧ��ס����Ϣ
		CwglService zsxxService = new CwglService();
		HashMap<String,String> zsxx =zsxxService.getCwForXh(xh);
		data.put("zsxx", zsxx);
		
		//��ø۰�̨��������
		XsxxglService xsxxqtService = new XsxxglService();
		HashMap<String,String> qtxx = xsxxqtService.getXsxxByXh(xh);
		data.put("qtxx", qtxx);
		
		// ��ͥ�ֻ���
		String jtshhm = "";
		for (HashMap<String, String> jtcyxxMap : jtcyxxList) {
			String jtcylxdh1 = jtcyxxMap.get("jtcylxdh1");
			if(StringUtils.isNotNull(jtcylxdh1)){
				jtshhm = jtcylxdh1;
			}
		}
		data.put("jtshhm", jtshhm);
		
		File file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xskp_10511.xml",xh+"-"+xsxxMap.get("xm"));
		return file;
		
	}
	private File getZzjsxWord(XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		String xh = myForm.getXh();
		User user = getUser(request);
		myForm.setUser(user);
		String dysj = GetTime.getTimeByFormat("yyyy-MM-dd");
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//ѧ��������Ϣ
		xsxxMap.put("zd3", DateTranCnDate.fomartDateToCn(xsxxMap.get("zd3"), FomartDateType.month));
		if(!"".equals(xsxxMap.get("zd2"))&&null!=xsxxMap.get("zd2")){
			xsxxMap.put("zd2", xsxxMap.get("zd2")+"��");
		}
		data.putAll(xsxxMap);
		data.put("xxmc", Base.xxmc);
		data.put("dysj", DateUtils.getDateString(DateUtils.parse(dysj),"5"));
		return FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","tzzgxb.xml",xh+"-"+xsxxMap.get("xm"));
	}
	
	/**
	 * 
	 * @����:�������������ɼ�����ӡ
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-7-6 ����09:20:57
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
	public ActionForward printCjd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		File wordFile = getCjdWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * 
	 * @����:�������������ɼ���������ӡ
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-7-6 ����09:20:57
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
	public ActionForward getCjdZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getCjdWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			
			for(int j=0;j<files.size();j++){
				files.get(j).delete();
			}
			
		}
		
		return null;
	}
	
	/**
	 * 
	 * @����:���ɳɼ�����Word-���������������Ի�
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-7-6 ����10:18:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getCjdWord(XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		String xh = myForm.getXh();
		User user = getUser(request);
		myForm.setUser(user);
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//ѧ��������Ϣ
		List<HashMap<String, String>> xscj = service.getCjListByXhXnXq(xh, Base.currXn, Base.currXq);//ѧ���ɼ���Ϣ
		
		data.put("rs",xsxxMap);
		data.put("xnf", xgxt.utils.date.DateUtils.numToZh(Base.currXn.substring(2, 4)));
		data.put("xns", xgxt.utils.date.DateUtils.numToZh(Base.currXn.substring(7, 9)));
		data.put("xq", xgxt.utils.date.DateUtils.numToZh(String.valueOf(Integer.parseInt(Base.currXq))));
		
		//����ɼ���Ϣ����Ҫ12��
		for(int i=1;i<12;i++){
			if(i<=xscj.size()){
				data.put("cj"+i, xscj.get(i-1).get("cj"));
				data.put("kcmc"+i, xscj.get(i-1).get("kcmc"));
			}else{
				data.put("cj"+i,"");
				data.put("kcmc"+i,"");
			}	
		}
		
		//��ȡ����������Ϣ
		CxpyService cxpyService = new CxpyService();
		data.putAll(cxpyService.getCxpyByXhXnXq(xh, Base.currXn,Base.currXq));
		
		PjjgService pjjgService = new PjjgService();
		
		CfjgService CfjgService = new CfjgService();
		
		String pjjg = pjjgService.getPjxxByXhXnXq(xh, Base.currXn, Base.currXq);
		String wjjg = CfjgService.getWjxxByXhXnXq(xh, Base.currXn, Base.currXq);
		data.put("jcqk", pjjg+";"+wjjg);
		
		return FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","hzqcjg_80152_cjd.xml",
				xh+"-"+xsxxMap.get("xm")+"�ɼ���");
	}
	
	/**
	 * 
	 * @����:�Ϻ�������Ի��ǼǱ�
	 * @���ߣ�ChenQ-856
	 * @���ڣ�2015-9-11 ����04:45:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public ActionForward printXsdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		File wordFile = getXsdjbWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	/**
	 * 
	 * @����:�Ϻ�������Ի��ǼǱ�
	 * @���ߣ�ChenQ-856
	 * @���ڣ�2015-9-11 ����04:45:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public ActionForward getXsdjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getXsdjbWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			
			for(int j=0;j<files.size();j++){
				files.get(j).delete();
			}
		}
		return null;
	}
	/**
	 * 
	 * @����:�Ϻ�������Ի��ǼǱ�
	 * @���ߣ�ChenQ-856
	 * @���ڣ�2015-9-11 ����04:45:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	private File getXsdjbWord(XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
		XsxxtyglService service = new XsxxtyglService();
		String xh = myForm.getXh();
		User user = getUser(request);
		myForm.setUser(user);
		Map<String,Object> data = new HashMap<String,Object>();
		HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//ѧ��������Ϣ
		XsxxglService xsxxglService = new XsxxglService();
 		List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh); //��ͥ��Ա
 		List<HashMap<String, String>> xlshjlList = xsxxglService.getXlshjlList(xh); // ѧ����ᾭ��
 		HashMap<String, String> zpMap=service.getZpMap(myForm,request,"zp");
 		
 		//�յ�List����ǰ̨����հ���
 		int i = 4;
 		if(xlshjlList.size()>=4){
 			i = 0;
 		}else{
 			i = 4 - xlshjlList.size();
 		}
 		String[] blankList = new String[i];
 		
 		//�յ�List����ǰ̨����հ���
 		int j = 6;
 		if(jtcyxxList.size()>=6){
 			j = 0;
 		}else{
 			j = 6 - jtcyxxList.size();
 		}
 		String[] blankList1 = new String[j];
 		
		for (Map.Entry<String, String> entry : xsxxMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			if (StringUtils.isNull(value)){
				xsxxMap.put(key, "��");
			}
		}
		data.put("xs",xsxxMap);
		data.putAll(zpMap);
		data.put("csrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.day));
		data.put("jrgqtsj",DateTranCnDate.fomartDateToCn(xsxxMap.get("jrgqtsj"),FomartDateType.month));
		data.put("jrgcdsj",null==DateTranCnDate.fomartDateToCn(xsxxMap.get("jrgcdsj"),FomartDateType.month)?"��":DateTranCnDate.fomartDateToCn(xsxxMap.get("jrgcdsj"),FomartDateType.month));
		data.put("jtcyList", jtcyxxList);
		data.put("jyjlList", xlshjlList);
		data.put("blankList",  Arrays.asList(blankList));
		data.put("blankList1",  Arrays.asList(blankList1));
		
		CwglService zsxxService = new CwglService();
		HashMap<String,String> zsxx =zsxxService.getCwForXh(xh);
		data.put("zsxx", zsxx);
		data.putAll(xsxxMap);
		List<HashMap<String, String>> xlshjlList5line = xsxxglService.getXlshjlList(xh,5);
		List<HashMap<String, String>> jtcyList4line = xsxxglService.getJtcyList(xh,4);
		data.put("xlshjlList5line", xlshjlList5line);
		data.put("jtcyList4line", jtcyList4line);
		data.put("sysdate", new SimpleDateFormat("yyyy��MM��dd��").format(new Date()));
		
		return FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xsdjb_"+Base.xxdm+".xml",
				xh+"-"+xsxxMap.get("xm")+"�����ǼǱ�");
	}
	
	
	/**
	 * @����: �������غ��������߼�����ѧУѧ����
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-9-25 ����10:33:46
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
	public ActionForward printHzqcxjk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		File wordFile = getXjkWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * @����: �������غ��������߼�����ѧУѧ����
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-10-21 ����09:53:32
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
	public ActionForward getHzqcxjkZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getXjkWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			
			for(int j=0;j<files.size();j++){
				files.get(j).delete();
			}
		}
		return null;
	}
	/**
	 * @����: ������������ҽҩѧ����
	 * @���ߣ�JZ[���ţ�1529]
	 * @���ڣ�2017-12-29 
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
	public ActionForward printXzyyxjk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		File wordFile = getXjkWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	/**
	 * @����: �����������ҽҩѧ����
	 * @���ߣ�JZ[���ţ�1529]
	 * @���ڣ�2017-12-29 
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
	public ActionForward getXzyyxjkZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getXjkWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			
			for(int j=0;j<files.size();j++){
				files.get(j).delete();
			}
		}
		return null;
	}
	/** �㽭��ѧ������ѧ�ǼǱ�
	 * @����: ������ӡ�ǼǱ�
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-12-3 ����07:29:09
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
	public ActionForward Xsrxdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		File wordFile = getXjkWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	
	/**
	 * ������ҵ��ѧ
	 * @����:������������Ϣ�ǼǱ�
	 * @���ߣ�caopei[���ţ�1352]
	 * @���ڣ�2016-8-30 ����07:52:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward Jbxxdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		File wordFile = getXjkWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		wordFile.delete();
		return null;
	}
	/**�㽭��ѧ������ѧ�ǼǱ�
	 * @����: ������ӡ�ǼǱ�
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2015-12-3 ����07:33:15
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
	public ActionForward XsrxdjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XsxxtyglForm myForm = (XsxxtyglForm) form;
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				myForm.setXh(values[i]);
				File file = getXjkWord(myForm,request);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
			for(int j=0;j<files.size();j++){
				files.get(j).delete();
			}
		}
		return null;
	}
	/**
	 * ������ҵ��ѧ
	 * @����:������ӡ�ǼǱ�
	 * @���ߣ�caopei[���ţ�1352]
	 * @���ڣ�2016-8-30 ����07:57:33
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
		public ActionForward JbxxdjbZip(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			XsxxtyglForm myForm = (XsxxtyglForm) form;
			String value = request.getParameter("value");
			if (StringUtils.isNotNull(value)){
				String[] values = value.split(",");
				List<File> files = new ArrayList<File>();
				for (int i = 0 , n = values.length ; i < n ; i++){
					myForm.setXh(values[i]);
					File file = getXjkWord(myForm,request);
					files.add(file);
				}
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
				for(int j=0;j<files.size();j++){
					files.get(j).delete();
				}
			}
			return null;
		}
	/**
	 * @����: ��ͨ�Ƽ�ְҵѧԺѧ���ɼ�������ӡ(3����)
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-5-17 ����09:47:03
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
		public ActionForward getPrintXscjb(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			XsxxtyglForm myForm = (XsxxtyglForm) form;
			File wordFile = getCjbWord(myForm, request);
			FileUtil.outputWord(response, wordFile);
			wordFile.delete();
			return null;
		}
	/**
	 * @����: ��ͨ�Ƽ�ְҵѧԺѧ���ɼ���������ӡ(3����)
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-5-17 ����09:47:03
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
		public ActionForward getPrintXscjbZip(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			XsxxtyglForm myForm = (XsxxtyglForm) form;
			String value = request.getParameter("value");
			if (StringUtils.isNotNull(value)){
				String[] values = value.split(",");
				List<File> files = new ArrayList<File>();
				for (int i = 0 , n = values.length ; i < n ; i++){
					myForm.setXh(values[i]);
					File file = getCjbWord(myForm,request);
					files.add(file);
				}
				
				File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
				FileUtil.outputZip(response, zipFile);
				
				for(int j=0;j<files.size();j++){
					files.get(j).delete();
				}
			}
			return null;
		}
	/**
	 * @����: ��ͨũҵְҵ����ѧԺ���Ӱ�ť�����ɼ�����Ի�_12684����ͨ�Ƽ�ְҵѧԺ��
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-5-17 ����09:22:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
		private File getCjbWord (XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
			XsxxService xsxxService = new XsxxService();
			XsxxglService xsxxglService = new XsxxglService();
			String xh = myForm.getXh();
			String dyrq = xsxxglService.getDqrq(xh).replace("-", ".");
			User user = getUser(request);
			myForm.setUser(user);
			Map<String,Object> data = new HashMap<String,Object>();
			HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
			data.putAll(xsxxMap);//����ѧ��������Ϣ
			data.put("csrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.month));//��������
			data.put("rxrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("rxrq"),FomartDateType.month));//��ѧ����
			data.put("dyrq",dyrq);//��ô�ӡ����
			File file = null;
			//��ȡ��һѧ���һѧ�ڳɼ�
			List<HashMap<String, String>> getXn1Xq1List = xsxxglService.getXnXqcj("0","0","01",xh);
			//��ȡ��һѧ��ڶ�ѧ�ڳɼ�
			List<HashMap<String, String>> getXn1Xq2List = xsxxglService.getXnXqcj("0","0","02",xh);
			//��ȡ�ڶ�ѧ���һѧ�ڳɼ�
			List<HashMap<String, String>> getXn2Xq1List = xsxxglService.getXnXqcj("1","1","01",xh);
			//��ȡ�ڶ�ѧ��ڶ�ѧ�ڳɼ�
			List<HashMap<String, String>> getXn2Xq2List = xsxxglService.getXnXqcj("1","1","02",xh);
			//��ȡ����ѧ���һѧ�ڳɼ�
			List<HashMap<String, String>> getXn3Xq1List = xsxxglService.getXnXqcj("2","2","01",xh);
			//��ȡ����ѧ��ڶ�ѧ�ڳɼ�
			List<HashMap<String, String>> getXn3Xq2List = xsxxglService.getXnXqcj("2","2","02",xh);
			//��ȡ����ѧ��һѧ���ڳɼ�
			List<HashMap<String, String>> getXn4Xq1List = xsxxglService.getXnXqcj("3","3","01",xh);
			//��ȡ����ѧ��ڶ�ѧ�ڳɼ�
			List<HashMap<String, String>> getXn4Xq2List = xsxxglService.getXnXqcj("3","3","02",xh);
			//��һѧ�ꡢ�ڶ�ѧ��ɼ���ѭ��
			int Cj1Size = 13;
			if(Cj1Size < getXn1Xq1List.size()){
				Cj1Size = getXn1Xq1List.size();
			}
			if(Cj1Size < getXn1Xq2List.size()){
				Cj1Size = getXn1Xq2List.size();
			}
			if(Cj1Size < getXn2Xq1List.size()){
				Cj1Size = getXn2Xq1List.size();
			}
			if(Cj1Size < getXn2Xq2List.size()){
				Cj1Size = getXn2Xq2List.size();
			}
			List<HashMap<String, String>> OneList = new ArrayList<HashMap<String, String>>();
			HashMap<String,String> map1 = null;
			for (int i = 0;i<Cj1Size;i++){
				map1 = new HashMap<String,String>();
				if(i< getXn1Xq1List.size()){
					map1.put("kcmc1", getXn1Xq1List.get(i).get("kcmc"));
					map1.put("kcxz1", getXn1Xq1List.get(i).get("kcxz"));
					map1.put("cxbj1", getXn1Xq1List.get(i).get("cxbj"));
					map1.put("xf1", getXn1Xq1List.get(i).get("xf"));
					map1.put("cj1", getXn1Xq1List.get(i).get("cj"));
				} else {
					map1.put("kcmc1", "");
					map1.put("kcxz1", "");
					map1.put("cxbj1", "");
					map1.put("xf1", "");
					map1.put("cj1", "");
				}
				if(i< getXn1Xq2List.size()){
					map1.put("kcmc2", getXn1Xq2List.get(i).get("kcmc"));
					map1.put("kcxz2", getXn1Xq2List.get(i).get("kcxz"));
					map1.put("cxbj2", getXn1Xq2List.get(i).get("cxbj"));
					map1.put("xf2", getXn1Xq2List.get(i).get("xf"));
					map1.put("cj2", getXn1Xq2List.get(i).get("cj"));
				} else {
					map1.put("kcmc2", "");
					map1.put("kcxz2", "");
					map1.put("cxbj2", "");
					map1.put("xf2", "");
					map1.put("cj2", "");
				}
				if(i< getXn2Xq1List.size()){
					map1.put("kcmc3", getXn2Xq1List.get(i).get("kcmc"));
					map1.put("kcxz3", getXn2Xq1List.get(i).get("kcxz"));
					map1.put("cxbj3", getXn2Xq1List.get(i).get("cxbj"));
					map1.put("xf3", getXn2Xq1List.get(i).get("xf"));
					map1.put("cj3", getXn2Xq1List.get(i).get("cj"));
				} else {
					map1.put("kcmc3", "");
					map1.put("kcxz3", "");
					map1.put("cxbj3", "");
					map1.put("xf3", "");
					map1.put("cj3", "");
				}
				if(i< getXn2Xq2List.size()){
					map1.put("kcmc4", getXn2Xq2List.get(i).get("kcmc"));
					map1.put("kcxz4", getXn2Xq2List.get(i).get("kcxz"));
					map1.put("cxbj4", getXn2Xq2List.get(i).get("cxbj"));
					map1.put("xf4", getXn2Xq2List.get(i).get("xf"));
					map1.put("cj4", getXn2Xq2List.get(i).get("cj"));
				} else {
					map1.put("kcmc4", "");
					map1.put("kcxz4", "");
					map1.put("cxbj4", "");
					map1.put("xf4", "");
					map1.put("cj4", "");
				}
				OneList.add(map1);
			}
			data.put("OneList",OneList);
			//����ѧ�ꡢ����ѧ��ɼ���ѭ��
			int Cj2Size = 21-Cj1Size;
			if(Cj2Size < getXn3Xq1List.size()){
				Cj2Size = getXn3Xq1List.size();
			}
			if(Cj2Size < getXn3Xq2List.size()){
				Cj2Size = getXn3Xq2List.size();
			}
			if(Cj2Size < getXn4Xq1List.size()){
				Cj2Size = getXn4Xq1List.size();
			}
			if(Cj2Size < getXn4Xq2List.size()){
				Cj2Size = getXn4Xq2List.size();
			}
			List<HashMap<String, String>> TwoList = new ArrayList<HashMap<String, String>>();
			HashMap<String,String> map2 = null;
			for (int i = 0;i<Cj2Size;i++){
				map2 = new HashMap<String,String>();
				if(i< getXn3Xq1List.size()){
					map2.put("kcmc5", getXn3Xq1List.get(i).get("kcmc"));
					map2.put("kcxz5", getXn3Xq1List.get(i).get("kcxz"));
					map2.put("cxbj5", getXn3Xq1List.get(i).get("cxbj"));
					map2.put("xf5", getXn3Xq1List.get(i).get("xf"));
					map2.put("cj5", getXn3Xq1List.get(i).get("cj"));
				} else {
					map2.put("kcmc5", "");
					map2.put("kcxz5", "");
					map2.put("cxbj5", "");
					map2.put("xf5", "");
					map2.put("cj5", "");
				}
				if(i< getXn3Xq2List.size()){
					map2.put("kcmc6", getXn3Xq2List.get(i).get("kcmc"));
					map2.put("kcxz6", getXn3Xq2List.get(i).get("kcxz"));
					map2.put("cxbj6", getXn3Xq2List.get(i).get("cxbj"));
					map2.put("xf6", getXn3Xq2List.get(i).get("xf"));
					map2.put("cj6", getXn3Xq2List.get(i).get("cj"));
				} else {
					map2.put("kcmc6", "");
					map2.put("kcxz6", "");
					map2.put("cxbj6", "");
					map2.put("xf6", "");
					map2.put("cj6", "");
				}
				if(i< getXn4Xq1List.size()){
					map2.put("kcmc7", getXn4Xq1List.get(i).get("kcmc"));
					map2.put("kcxz7", getXn4Xq1List.get(i).get("kcxz"));
					map2.put("cxbj7", getXn4Xq1List.get(i).get("cxbj"));
					map2.put("xf7", getXn4Xq1List.get(i).get("xf"));
					map2.put("cj7", getXn4Xq1List.get(i).get("cj"));
				} else {
					map2.put("kcmc7", "");
					map2.put("kcxz7", "");
					map2.put("cxbj7", "");
					map2.put("xf7", "");
					map2.put("cj7", "");
				}
				if(i< getXn4Xq2List.size()){
					map2.put("kcmc8", getXn4Xq2List.get(i).get("kcmc"));
					map2.put("kcxz8", getXn4Xq2List.get(i).get("kcxz"));
					map2.put("cxbj8", getXn4Xq2List.get(i).get("cxbj"));
					map2.put("xf8", getXn4Xq2List.get(i).get("xf"));
					map2.put("cj8", getXn4Xq2List.get(i).get("cj"));
				} else {
					map2.put("kcmc8", "");
					map2.put("kcxz8", "");
					map2.put("cxbj8", "");
					map2.put("xf8", "");
					map2.put("cj8", "");
				}
				TwoList.add(map2);
			}
			data.put("TwoList",TwoList);
			//ѧ���γ�ѧ�ں���ѧ��
			HashMap<String, String> kcxqMap = xsxxglService.getKcxq(xh);
			data.put("kcxqList", kcxqMap);
			//��ҵ�����Ŀ���ɼ�
			HashMap<String, String> bysj = xsxxglService.getBysj(xh);
			data.put("bysj", bysj);
			//�ȼ����Գɼ�
			List<HashMap<String, String>> djksList = xsxxglService.getDjkscj(xh);
			data.put("djksList",djksList);
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xscjb_12684.xml",xh+"-"+xsxxMap.get("xm"));
			return file;
		}
		/**
		 * @����: ��ͨ�Ƽ�ְҵѧԺѧ���ɼ�������ӡ(5����)
		 * @���ߣ� ����[���ţ�1186]
		 * @���ڣ�2016-5-17 ����09:47:03
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
			public ActionForward getPrintXscjbwu(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				XsxxtyglForm myForm = (XsxxtyglForm) form;
				File wordFile = getCjbwuWord(myForm, request);
				FileUtil.outputWord(response, wordFile);
				wordFile.delete();
				return null;
			}
		/**
		 * @����: ��ͨ�Ƽ�ְҵѧԺѧ���ɼ���������ӡ(5����)
		 * @���ߣ� ����[���ţ�1186]
		 * @���ڣ�2016-5-17 ����09:47:03
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
			public ActionForward getPrintXscjbwuZip(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				XsxxtyglForm myForm = (XsxxtyglForm) form;
				String value = request.getParameter("value");
				if (StringUtils.isNotNull(value)){
					String[] values = value.split(",");
					List<File> files = new ArrayList<File>();
					for (int i = 0 , n = values.length ; i < n ; i++){
						myForm.setXh(values[i]);
						File file = getCjbwuWord(myForm,request);
						files.add(file);
					}
					
					File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
					FileUtil.outputZip(response, zipFile);
					
					for(int j=0;j<files.size();j++){
						files.get(j).delete();
					}
				}
				return null;
			}
			private File getCjbwuWord (XsxxtyglForm myForm,HttpServletRequest request) throws Exception{
				XsxxService xsxxService = new XsxxService();
				XsxxglService xsxxglService = new XsxxglService();
				String xh = myForm.getXh();
				String dyrq = xsxxglService.getDqrq(xh).replace("-", ".");
				User user = getUser(request);
				myForm.setUser(user);
				Map<String,Object> data = new HashMap<String,Object>();
				HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
				data.putAll(xsxxMap);//����ѧ��������Ϣ
				data.put("csrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.month));//��������
				data.put("rxrq",DateTranCnDate.fomartDateToCn(xsxxMap.get("rxrq"),FomartDateType.month));//��ѧ����
				data.put("dyrq",dyrq);//��ô�ӡ����
				File file = null;
				//��ȡ��һѧ���һѧ�ڳɼ�
				List<HashMap<String, String>> getXn1Xq1List = xsxxglService.getXnXqcj("0","0","01",xh);
				//��ȡ��һѧ��ڶ�ѧ�ڳɼ�
				List<HashMap<String, String>> getXn1Xq2List = xsxxglService.getXnXqcj("0","0","02",xh);
				//��ȡ�ڶ�ѧ���һѧ�ڳɼ�
				List<HashMap<String, String>> getXn2Xq1List = xsxxglService.getXnXqcj("1","1","01",xh);
				//��ȡ�ڶ�ѧ��ڶ�ѧ�ڳɼ�
				List<HashMap<String, String>> getXn2Xq2List = xsxxglService.getXnXqcj("1","1","02",xh);
				//��ȡ����ѧ���һѧ�ڳɼ�
				List<HashMap<String, String>> getXn3Xq1List = xsxxglService.getXnXqcj("2","2","01",xh);
				//��ȡ����ѧ��ڶ�ѧ�ڳɼ�
				List<HashMap<String, String>> getXn3Xq2List = xsxxglService.getXnXqcj("2","2","02",xh);
				//��ȡ����ѧ��һѧ���ڳɼ�
				List<HashMap<String, String>> getXn4Xq1List = xsxxglService.getXnXqcj("3","3","01",xh);
				//��ȡ����ѧ��ڶ�ѧ�ڳɼ�
				List<HashMap<String, String>> getXn4Xq2List = xsxxglService.getXnXqcj("3","3","02",xh);
				//��ȡ����ѧ���һѧ�ڳɼ�
				List<HashMap<String, String>> getXn5Xq1List = xsxxglService.getXnXqcj("4","4","01",xh);
				//��ȡ����ѧ��ڶ�ѧ�ڳɼ�
				List<HashMap<String, String>> getXn5Xq2List = xsxxglService.getXnXqcj("4","4","02",xh);
				//��һѧ�ꡢ�ڶ�ѧ��ɼ���ѭ��
				int Cj1Size = 13;
				if(Cj1Size < getXn1Xq1List.size()){
					Cj1Size = getXn1Xq1List.size();
				}
				if(Cj1Size < getXn1Xq2List.size()){
					Cj1Size = getXn1Xq2List.size();
				}
				if(Cj1Size < getXn2Xq1List.size()){
					Cj1Size = getXn2Xq1List.size();
				}
				if(Cj1Size < getXn2Xq2List.size()){
					Cj1Size = getXn2Xq2List.size();
				}
				List<HashMap<String, String>> OneList = new ArrayList<HashMap<String, String>>();
				HashMap<String,String> map1 = null;
				for (int i = 0;i<Cj1Size;i++){
					map1 = new HashMap<String,String>();
					if(i< getXn1Xq1List.size()){
						map1.put("kcmc1", getXn1Xq1List.get(i).get("kcmc"));
						map1.put("kcxz1", getXn1Xq1List.get(i).get("kcxz"));
						map1.put("cxbj1", getXn1Xq1List.get(i).get("cxbj"));
						map1.put("xf1", getXn1Xq1List.get(i).get("xf"));
						map1.put("cj1", getXn1Xq1List.get(i).get("cj"));
					} else {
						map1.put("kcmc1", "");
						map1.put("kcxz1", "");
						map1.put("cxbj1", "");
						map1.put("xf1", "");
						map1.put("cj1", "");
					}
					if(i< getXn1Xq2List.size()){
						map1.put("kcmc2", getXn1Xq2List.get(i).get("kcmc"));
						map1.put("kcxz2", getXn1Xq2List.get(i).get("kcxz"));
						map1.put("cxbj2", getXn1Xq2List.get(i).get("cxbj"));
						map1.put("xf2", getXn1Xq2List.get(i).get("xf"));
						map1.put("cj2", getXn1Xq2List.get(i).get("cj"));
					} else {
						map1.put("kcmc2", "");
						map1.put("kcxz2", "");
						map1.put("cxbj2", "");
						map1.put("xf2", "");
						map1.put("cj2", "");
					}
					if(i< getXn2Xq1List.size()){
						map1.put("kcmc3", getXn2Xq1List.get(i).get("kcmc"));
						map1.put("kcxz3", getXn2Xq1List.get(i).get("kcxz"));
						map1.put("cxbj3", getXn2Xq1List.get(i).get("cxbj"));
						map1.put("xf3", getXn2Xq1List.get(i).get("xf"));
						map1.put("cj3", getXn2Xq1List.get(i).get("cj"));
					} else {
						map1.put("kcmc3", "");
						map1.put("kcxz3", "");
						map1.put("cxbj3", "");
						map1.put("xf3", "");
						map1.put("cj3", "");
					}
					if(i< getXn2Xq2List.size()){
						map1.put("kcmc4", getXn2Xq2List.get(i).get("kcmc"));
						map1.put("kcxz4", getXn2Xq2List.get(i).get("kcxz"));
						map1.put("cxbj4", getXn2Xq2List.get(i).get("cxbj"));
						map1.put("xf4", getXn2Xq2List.get(i).get("xf"));
						map1.put("cj4", getXn2Xq2List.get(i).get("cj"));
					} else {
						map1.put("kcmc4", "");
						map1.put("kcxz4", "");
						map1.put("cxbj4", "");
						map1.put("xf4", "");
						map1.put("cj4", "");
					}
					OneList.add(map1);
				}
				data.put("OneList",OneList);
				//����ѧ�ꡢ����ѧ��ɼ���ѭ��
				int Cj2Size = 11;
				if(Cj2Size < getXn3Xq1List.size()){
					Cj2Size = getXn3Xq1List.size();
				}
				if(Cj2Size < getXn3Xq2List.size()){
					Cj2Size = getXn3Xq2List.size();
				}
				if(Cj2Size < getXn4Xq1List.size()){
					Cj2Size = getXn4Xq1List.size();
				}
				if(Cj2Size < getXn4Xq2List.size()){
					Cj2Size = getXn4Xq2List.size();
				}
				List<HashMap<String, String>> TwoList = new ArrayList<HashMap<String, String>>();
				HashMap<String,String> map2 = null;
				for (int i = 0;i<Cj2Size;i++){
					map2 = new HashMap<String,String>();
					if(i< getXn3Xq1List.size()){
						map2.put("kcmc5", getXn3Xq1List.get(i).get("kcmc"));
						map2.put("kcxz5", getXn3Xq1List.get(i).get("kcxz"));
						map2.put("cxbj5", getXn3Xq1List.get(i).get("cxbj"));
						map2.put("xf5", getXn3Xq1List.get(i).get("xf"));
						map2.put("cj5", getXn3Xq1List.get(i).get("cj"));
					} else {
						map2.put("kcmc5", "");
						map2.put("kcxz5", "");
						map2.put("cxbj5", "");
						map2.put("xf5", "");
						map2.put("cj5", "");
					}
					if(i< getXn3Xq2List.size()){
						map2.put("kcmc6", getXn3Xq2List.get(i).get("kcmc"));
						map2.put("kcxz6", getXn3Xq2List.get(i).get("kcxz"));
						map2.put("cxbj6", getXn3Xq2List.get(i).get("cxbj"));
						map2.put("xf6", getXn3Xq2List.get(i).get("xf"));
						map2.put("cj6", getXn3Xq2List.get(i).get("cj"));
					} else {
						map2.put("kcmc6", "");
						map2.put("kcxz6", "");
						map2.put("cxbj6", "");
						map2.put("xf6", "");
						map2.put("cj6", "");
					}
					if(i< getXn4Xq1List.size()){
						map2.put("kcmc7", getXn4Xq1List.get(i).get("kcmc"));
						map2.put("kcxz7", getXn4Xq1List.get(i).get("kcxz"));
						map2.put("cxbj7", getXn4Xq1List.get(i).get("cxbj"));
						map2.put("xf7", getXn4Xq1List.get(i).get("xf"));
						map2.put("cj7", getXn4Xq1List.get(i).get("cj"));
					} else {
						map2.put("kcmc7", "");
						map2.put("kcxz7", "");
						map2.put("cxbj7", "");
						map2.put("xf7", "");
						map2.put("cj7", "");
					}
					if(i< getXn4Xq2List.size()){
						map2.put("kcmc8", getXn4Xq2List.get(i).get("kcmc"));
						map2.put("kcxz8", getXn4Xq2List.get(i).get("kcxz"));
						map2.put("cxbj8", getXn4Xq2List.get(i).get("cxbj"));
						map2.put("xf8", getXn4Xq2List.get(i).get("xf"));
						map2.put("cj8", getXn4Xq2List.get(i).get("cj"));
					} else {
						map2.put("kcmc8", "");
						map2.put("kcxz8", "");
						map2.put("cxbj8", "");
						map2.put("xf8", "");
						map2.put("cj8", "");
					}
					TwoList.add(map2);
				}
				data.put("TwoList",TwoList);
				//����ѧ��ɼ���ѭ��
				int Cj3Size = 9;
				if(Cj3Size < getXn5Xq1List.size()){
					Cj3Size = getXn5Xq1List.size();
				}
				if(Cj3Size < getXn5Xq2List.size()){
					Cj3Size = getXn5Xq2List.size();
				}
				List<HashMap<String, String>> ThreeList = new ArrayList<HashMap<String, String>>();
				HashMap<String,String> map3 = null;
				for (int i = 0;i<Cj3Size;i++){
					map3 = new HashMap<String,String>();
					if(i< getXn5Xq1List.size()){
						map3.put("kcmc9", getXn5Xq1List.get(i).get("kcmc"));
						map3.put("kcxz9", getXn5Xq1List.get(i).get("kcxz"));
						map3.put("cxbj9", getXn5Xq1List.get(i).get("cxbj"));
						map3.put("xf9", getXn5Xq1List.get(i).get("xf"));
						map3.put("cj9", getXn5Xq1List.get(i).get("cj"));
					} else {
						map3.put("kcmc9", "");
						map3.put("kcxz9", "");
						map3.put("cxbj9", "");
						map3.put("xf9", "");
						map3.put("cj9", "");
					}
					if(i< getXn5Xq2List.size()){
						map3.put("kcmc10", getXn5Xq2List.get(i).get("kcmc"));
						map3.put("kcxz10", getXn5Xq2List.get(i).get("kcxz"));
						map3.put("cxbj10", getXn5Xq2List.get(i).get("cxbj"));
						map3.put("xf10", getXn5Xq2List.get(i).get("xf"));
						map3.put("cj10", getXn5Xq2List.get(i).get("cj"));
					} else {
						map3.put("kcmc10", "");
						map3.put("kcxz10", "");
						map3.put("cxbj10", "");
						map3.put("xf10", "");
						map3.put("cj10", "");
					}
					ThreeList.add(map3);
				}
				data.put("ThreeList",ThreeList);
				//ѧ���γ�ѧ�ں���ѧ��
				HashMap<String, String> kcxqMap = xsxxglService.getKcxq(xh);
				data.put("kcxqList", kcxqMap);
				//��ҵ�����Ŀ���ɼ�
				HashMap<String, String> bysj = xsxxglService.getBysj(xh);
				data.put("bysj", bysj);
				//�ȼ����Գɼ�
				List<HashMap<String, String>> djksList = xsxxglService.getDjkscj(xh);
				data.put("djksList",djksList);
				file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xscjbwnz_12684.xml",xh+"-"+xsxxMap.get("xm"));
				return file;
			}
			
			/** 
			 * @����:��ӡ�༶������(����ְҵ����ѧԺ���Ի�)
			 * @���ߣ�����[���ţ�1282]
			 * @���ڣ�2016-12-15 ����04:23:01
			 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 * ActionForward �������� 
			 * @throws 
			 */
			public ActionForward xnzyjsxyhmcExportData(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response) throws Exception{
				XsxxtyglForm myForm = (XsxxtyglForm) form;
				//���ɸ߼���ѯ����		
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				SearchModel sm = new SearchModel();
				if(null != searchModel.getSearch_tj_nj()){//�꼶��ѯ����
					sm.setSearch_tj_nj(searchModel.getSearch_tj_nj());
				}
				if(null != searchModel.getSearch_tj_zy()){//רҵ��ѯ����
					sm.setSearch_tj_zy(searchModel.getSearch_tj_zy());
				}
				if(null != searchModel.getSearch_tj_bj()){//�༶��ѯ����
					sm.setSearch_tj_bj(searchModel.getSearch_tj_bj());
				}
				sm.setSearch_tj_xy(searchModel.getSearch_tj_xy());//ѧԺ��ѯ����
				sm.setPath(searchModel.getPath());
				myForm.setSearchModel(sm);//���û������ѯ����
				XsxxtyglService service = new XsxxtyglService();
				File file = service.getBjhmcExcelList(myForm);//��ȡ������exl�ļ�
				FileUtil.outputExcel(response, file);
				return null;				
			}
			
			/**
			 * @����:ѧ�����֤��Word�ļ����أ����칤��ְҵ����ѧԺ��
			 * @���ߣ�xuwen[���ţ�1426]
			 * @���ڣ�2017��1��4�� ����5:17:41
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
			public ActionForward getSI (ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response) throws Exception{
				XsxxtyglForm myForm = (XsxxtyglForm) form;
				File wordFile = getSIWord(myForm, request);
				FileUtil.outputWord(response, wordFile);
				wordFile.delete();
				return null;
			}
			
			/**
			 * @����:ѧ�����֤��Word�ļ�����Zip���أ����칤��ְҵ����ѧԺ��
			 * @���ߣ�xuwen[���ţ�1426]
			 * @���ڣ�2017��1��4�� ����5:22:15
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
			public ActionForward getSIZip (ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response) throws Exception{
				XsxxtyglForm myForm = (XsxxtyglForm) form;
				String value = request.getParameter("value");
				if (StringUtils.isNotNull(value)){
					String[] values = value.split(",");
					List<File> files = new ArrayList<File>();
					for (int i = 0 , n = values.length ; i < n ; i++){
						myForm.setXh(values[i]);
						File file = getSIWord(myForm,request);
						files.add(file);
					}
					
					File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
					FileUtil.outputZip(response, zipFile);
					
					for(int j=0;j<files.size();j++){
						files.get(j).delete();
					}
				}
				return null;
			}
			
			/**
			 * @����:����Word�ļ�������ѧ�����֤��Word��Zip�ļ�����ʱ���ã���������칤��ְҵ����ѧԺ��
			 * @���ߣ�xuwen[���ţ�1426]
			 * @���ڣ�2017��1��4�� ����5:25:37
			 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
			 * @param myForm
			 * @param request
			 * @return
			 * @throws Exception
			 * File �������� 
			 * @throws
			 */
			private File getSIWord(XsxxtyglForm myForm,HttpServletRequest request)throws Exception{
				XsxxtyglService service = new XsxxtyglService();
				String xh = myForm.getXh();
				User user = getUser(request);
				myForm.setUser(user);
				Map<String,Object> data = new HashMap<String,Object>();
				HashMap<String, String> xsxxMap = service.getZxsxxByXh(myForm);//ѧ��������Ϣ
		 		
				//������ѧ���ڻ��꼶�͵�ǰ���ڵ��꼶����
				Map<Integer,Character> njmcMap = xgxt.utils.date.DateUtils.getZHNumberMap();
				
				String nj = xsxxMap.get("nj");
				
				if(!StringUtil.isNull(nj)){
					Integer njValue = Integer.parseInt(nj);
					Calendar calendar = Calendar.getInstance();
					Integer year = calendar.get(Calendar.YEAR);
					Integer month = calendar.get(Calendar.MONTH)+1;
					Character njmc = month>=9?njmcMap.get(year-njValue+1):njmcMap.get(year-njValue);
					
					//��������꼶���Ƽ����������map
					data.put("njmc", njmc);
				}
				
				//��������������
				data.put("cy", xgxt.utils.date.DateUtils.getYear());
				data.put("zhDate", xgxt.utils.date.DateUtils.getZHDate());
				
				data.putAll(xsxxMap);
					
				File file = FreeMarkerUtil.getWordFile(data,"classpath://templates//xsxx","xssfzm.xml",xh+"-"+xsxxMap.get("xm"));
				return file;
			}

}
