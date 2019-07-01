package xgxt.sztz;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.audit.AuditUtil;
import xgxt.audit.spgc.AuditingInterface;
import xgxt.audit.spgc.AuditingManage;
import xgxt.audit.spgc.AuditingModel;
import xgxt.comm.CommService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.db.GetSysData;
import com.zfsoft.basic.BasicAction;

/**
 * ������չ-�������ְҵ��������
 * <br>ϣ������ͨ�ð汾�����������ڴ˻�������չ
 * @author Penghui.Qu
 */
public class SztzAction extends BasicAction {

	
	/**
	 * ѧ������-�걨ʱ������
	 * @throws Exception
	 */
	public ActionForward sjszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xxdm = Base.xxdm;
		String tableName = "xg_sztz_csszb";
		
		selectPageDataByOne(request, tableName, tableName, xxdm);
		
		request.setAttribute("path", "sztz_sjsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sjszManage");
	}
	
	
	
	/**
	 * ѧ������-�걨ʱ������
	 * @throws Exception
	 */
	public ActionForward sjszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SztzForm model = (SztzForm) form;
		model.setXxdm(Base.xxdm);

		String tableName = "xg_sztz_csszb";
		String[] onezd = new String[] { "xxdm", "sbkssj", "sbjssj" };
		SaveForm saveForm = new SaveForm();
		SztzService service = new SztzService();

		saveForm.setOnezd(onezd);
		saveForm.setTableName(tableName);
		saveForm.setPk("xxdm");
		saveForm.setPkValue(new String[] { model.getXxdm() });
		boolean result = service.saveSztz(saveForm, model, request);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);

		return sjszManage(mapping,form,request,response);
	}


	/**
	 * ��Ŀ��������
	 */
	public ActionForward kmslManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xg_sztz_kmdmb";
		SztzForm model = (SztzForm) form;
		String[] topTr = new String[]{"��Ŀ����","��Ŀ����"};
		
		//if (QUERY.equalsIgnoreCase(model.getDoType())){
			String[] colList = new String[]{"pkValue","kmdm","kmmc"};
			selectPageDataByPagination(request, form, tableName, tableName, colList);
		//}
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "sztz_kmsl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("kmslManage");
	}
	
	/**
	 * ɾ����Ŀ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkDelKmdm (ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		SztzService service = new SztzService();
		List<HashMap<String,String>> xmlist = service.getXmBykmdm(pkValue);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(xmlist!=null&&xmlist.size()>0?"false":"true");
		return null;
	}
	
	/**
	 * ɾ����Ŀ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkDelXm (ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		SztzService service = new SztzService();
		List<HashMap<String,String>> xmlist = service.getXmlist(pkValue);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(xmlist!=null&&xmlist.size()>0?"false":"true");
		return null;
	}
	
	/**
	 * ��Ŀ����ɾ��
	 */
	public ActionForward kmslDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SztzForm model = (SztzForm) form;
		SztzService service = new SztzService();
		String[] kmdm = request.getParameterValues("primarykey_cbv");

		boolean result = service.delKmsz(kmdm);
		request.setAttribute("message", result ? DEL_SUCCESS : DEL_FAIL);

		model.setDoType(QUERY);
		return kmslManage(mapping, form, request, response);
	}
	
	
	/**
	 * ��Ŀ����ά��
	 */
	public ActionForward kmslUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xg_sztz_kmdmb";
		SztzForm model = (SztzForm) form;
		
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			
			String[] onezd = new String[]{"kmdm","kmmc"};
			SaveForm saveForm = new SaveForm();
			SztzService service = new SztzService();
			
			saveForm.setOnezd(onezd);
			saveForm.setTableName(tableName);
			saveForm.setPk("kmdm");
			saveForm.setPkValue(new String[]{model.getKmdm()});
			boolean result = service.saveKmwh(saveForm, model, request);
			request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		return mapping.findForward("kmslUpdate");
	}
	

	/**
	 * ��Ŀ������������
	 */
	public ActionForward kmslView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "xg_sztz_kmdmb";
		String pkValue = request.getParameter("pkValue");
		
		if (StringUtils.isNotNull(pkValue)){
			SztzService service = new SztzService();
			//��Ŀ��Ϣ
			selectPageDataByOne(request, tableName, tableName, pkValue);
			//��Ӧ�ĺ�������
			List<HashMap<String,String>> hxnl = service.getHxnl(pkValue);
			request.setAttribute("hxnlList", hxnl);
		}
		
		return mapping.findForward("kmslUpdate");
	}
	
	

	/**
	 * ��Ŀ����
	 */
	public ActionForward xmglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		SztzForm model = (SztzForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String xydm = model.getXydm();
		String nj = model.getNj();
		// �꼶�б�
		List<HashMap<String, String>> njList = Base.getNjList();
		request.setAttribute("njList", njList);
		// ѧԺ�б�
		List<HashMap<String, String>> xyList = Base.getXyList();	
		request.setAttribute("xyList", xyList);

		String[] topTr = new String[]{"��Ŀ����","ѧ��","ѧ��","�꼶","ѧԺ","������Ŀ","ѧʱ","��������","��Ŀ����","���״̬"};
		String qry="";
		if(userType.equalsIgnoreCase("xy")){//Ժϵ�û�
			xydm = userDep;
			model.setXydm(userDep);	
			
			if(!"".equalsIgnoreCase(model.getXydm())){
				qry=" and ( xydm ='"+model.getXydm()+"' or xydm = 'xj' )";
			}
			request.setAttribute("xydm", xydm);
		}
		if ("stu".equalsIgnoreCase(userType)) {// �в���Ȩ�޵�ѧ���ɲ���¼
			String[] temV = dao.getOneRs("select xydm,nj,zydm,bjdm from view_xsjbxx where xh=?",new String[] { userName }, new String[] { "xydm", "nj",
					"zydm", "bjdm" });
			if (temV != null) {
				xydm = (temV[0] == null || temV[0].equalsIgnoreCase("") ? "": temV[0]);
				nj = (temV[1] == null || temV[1].equalsIgnoreCase("") ? "": temV[1]);
				model.setXydm(xydm);
				model.setNj(nj);
			}
			
			if(!"".equalsIgnoreCase(model.getXydm()) && !"".equalsIgnoreCase(model.getNj())){
				qry=" and (xydm ='"+model.getXydm()+"' or xydm = 'xj') and nj='"+model.getNj()+"'";
			}
			request.setAttribute("xydm", xydm);
			request.setAttribute("nj", nj);
		}
		if(model.getQueryequals_xn()==null){
			model.setQueryequals_xn(Base.currXn);
			qry+="and xn='"+Base.currXn+"'";
		}
		if(model.getQueryequals_xq()==null){
			model.setQueryequals_xq(Base.currXq);
			qry+=" and xq='"+Base.currXq+"'";
		}
		request.setAttribute("annexTerm", qry);
		//if (QUERY.equalsIgnoreCase(model.getDoType())){
			String tableName = "xg_sztz_xmglb";
			String viewName = "xg_view_sztz_xmglb";
			String[] colList = new String[]{"dis","id","xmmc","xn","xqmc","nj","xymc","kmmc","xs","hxnlmc","xmlxmc","shzt"};
			
			request.setAttribute("clientColumns", "case when a.shzt='�˻�' or  a.shzt='δ���' then '' else 'disabled' end dis,");
			selectPageDataByPagination(request, model, tableName, viewName, colList);
		//}
			
		
		setList(request);
		request.setAttribute("path", "sztz_xmcx.do");
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmglManage");
	}

	/**
	 * ��Ŀ��ӡ��ѯ
	 */
	public ActionForward xmdyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "xg_sztz_xmglb";
		CommService commService = new CommService();
		// ѧԺ�б�
		List<HashMap<String, String>> xmList = commService.getRsList(tableName, "", new String[]{}, new String[]{"id","xmmc"}, "");
		request.setAttribute("xmList", xmList);
		setList(request);
		request.setAttribute("path", "sztz_xmdy.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmdyManage");
	}
	
	/**
	 * ��ȡ��Ŀ����
	 */
	public ActionForward getXmmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		SztzService service = new SztzService();
		List<HashMap<String, String>> list = service.getXmmc(xn, xq);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		return null;
	}
	
	/**
	 * ��Ŀ��ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmDy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		SztzService service = new SztzService();
		SztzForm model = (SztzForm) form;
		model.setId(id);
		// ============= ��ʼ����������ֵ ============
		response.setContentType("application/vnd.ms-excel");
		service.xmDy(response.getOutputStream(),model);
		return null;
	}
	
	/**
	 * ��Ŀɾ��
	 */
	public ActionForward xmDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		SztzForm model = (SztzForm) form;
		CommService commService = new CommService();
		String tableName = "xg_sztz_xmglb";
		String[] colList = new String[]{"id","xn","xq","kmdm","hxnl",
				"xmlx","xmmc","shlcid","jcf","sqbm","jbkssj",
				"jbjssj","zbf","xs","sbr","fzr","bz","rssx","nj","xydm","shzt"};
		SztzService service = new SztzService();
		String[] xmid = request.getParameterValues("primarykey_cbv");
		if(userType.equalsIgnoreCase("xy")){//Ժϵ�û�
			int j=0;
			for(int i=0;i<xmid.length;i++){
				HashMap<String,String> xmInfo = commService.getRsInfo(tableName, "id", xmid[i], colList);
				if(xmInfo.get("xydm").equals("xj")){
					j++;
				}
			}
			if(j>0){
				request.setAttribute("message", "������ɾ��У����Ŀ��");
				model.setDoType(QUERY);
				return xmglManage(mapping, form, request, response);
			}
		}
		boolean result = service.delXm(xmid);
		request.setAttribute("message", result ? DEL_SUCCESS : DEL_FAIL);

		model.setDoType(QUERY);
		return xmglManage(mapping, form, request, response);
	}
	
	
	/**
	 * ��Ŀ�걨
	 */
	public ActionForward xmsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		boolean flg = Boolean.valueOf(request.getParameter("flg"));
		SztzForm model = (SztzForm) form;
		String xydm = model.getXydm();
		// �꼶�б�
		List<HashMap<String, String>> njList = Base.getNjList();
		request.setAttribute("njList", njList);
		// ѧԺ�б�
		List<HashMap<String, String>> xyList = Base.getXyList();	
		request.setAttribute("xyList", xyList);
		if(userType.equalsIgnoreCase("xy")){//Ժϵ�û�
			xydm = userDep;
			request.setAttribute("xydm", xydm);
		}
		
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		if (SAVE.equalsIgnoreCase(model.getDoType())){
			String tableName = "xg_sztz_xmglb";
			String[] onezd = new String[]{"id","xn","xq","kmdm","hxnl",
							"xmlx","xmmc","shlcid","jcf","sqbm","jbkssj",
							"jbjssj","zbf","xs","sbr","fzr","bz","rssx","nj","xydm"};
			SaveForm saveForm = new SaveForm();
			SztzService sztzService = new SztzService();
			
			if (StringUtils.isNull(model.getId())){
				flg = true;//�걨ʱ�ύ��������޸�ʱ���ύ��������ɶ����¼��
				model.setId(GetSysData.getGuid());
			}
			
			saveForm.setOnezd(onezd);
			saveForm.setTableName(tableName);
			saveForm.setPk("id");
			saveForm.setPkValue(new String[]{model.getId()});
			boolean result = sztzService.saveXmsb(saveForm, model, request);
			request.setAttribute("message", result ? SAVE_SUCCESS : "����ʧ��,��Ҫ��ӵ���Ŀ�Ѵ��ڡ�");
			
			//�ύ���������
			if (flg && result && StringUtils.isNotNull(model.getShlcid())){
				String shztb = "xg_sztz_xmshjlb";
				AuditingInterface manage = new AuditingManage();
				AuditingModel auditModel = new AuditingModel();
				
				auditModel.setXtgwid("Applicant");
				auditModel.setId(model.getId());
				auditModel.setShlcid(model.getShlcid());
				auditModel.setShr(model.getSbr());
				auditModel.setSftj("��");
				auditModel.setSqjlb("xg_sztz_xmglb");
				manage.saveAuditing(auditModel, shztb,null,auditModel);
			}
		}
		
		if (UPDATE.equalsIgnoreCase(model.getDoType())){
			
			SztzService sztzService = new SztzService();
			

			boolean result = sztzService.updateXmsb(model);
			request.setAttribute("message", result ? SAVE_SUCCESS : "����ʧ��,��Ҫ��ӵ���Ŀ�Ѵ��ڡ�");
			
			//�ύ���������
			if (flg && result && StringUtils.isNotNull(model.getShlcid())){
				String shztb = "xg_sztz_xmshjlb";
				AuditingInterface manage = new AuditingManage();
				AuditingModel auditModel = new AuditingModel();
				
				auditModel.setXtgwid("Applicant");
				auditModel.setId(model.getId());
				auditModel.setShlcid(model.getShlcid());
				auditModel.setShr(model.getSbr());
				auditModel.setSftj("��");
				auditModel.setSqjlb("xg_sztz_xmglb");
				manage.saveAuditing(auditModel, shztb,null,auditModel);
			}
		}
		
		setList(request);
		return mapping.findForward("xmsb");
	}


	/**
	 * �����б�����
	 * @param request
	 */
	private void setList(HttpServletRequest request) {
		CommService service = new CommService();
		//��Ŀ�б�
		request.setAttribute("kmdmList", service.getWhList("xg_sztz_kmdmb", "kmdm", "kmmc", null, null, null));
		//���������б�
		request.setAttribute("hxnlList", service.getWhList("xg_sztz_hxnlb", "hxnldm", "hxnlmc", null, null, null));
		//��Ŀ�����б�
		request.setAttribute("xmlxList", service.getWhList("xg_sztz_xmlxb", "lxdm", "lxmc", null, null, null));
		//��������
		request.setAttribute("shlcList", service.getWhList("xg_xtwh_splc", "id", "mc", null, "djlx", "sztz"));
		FormModleCommon.setNdXnXqList(request);
	}
	
	
	/**
	 * ������Ŀ����
	 */
	public ActionForward xmsbView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		SztzForm model = (SztzForm) form;
		String tableName = "xg_sztz_xmglb";
		String pkValue = request.getParameter("pkValue");
		// �꼶�б�
		List<HashMap<String, String>> njList = Base.getNjList();
		request.setAttribute("njList", njList);
		// ѧԺ�б�
		List<HashMap<String, String>> xyList = Base.getXyList();	
		request.setAttribute("xyList", xyList);
		CommService commService = new CommService();
		String[] colList = new String[]{"id","xn","xq","kmdm","hxnl",
				"xmlx","xmmc","shlcid","jcf","sqbm","jbkssj",
				"jbjssj","zbf","xs","sbr","fzr","bz","rssx","nj","xydm","shzt"};
//	 if(userType.equalsIgnoreCase("xy")&&"update".equals(model.getDoType())){//Ժϵ�û�
//			HashMap<String,String> xmInfo = commService.getRsInfo(tableName, "id", pkValue, colList);
//			if(xmInfo.get("xydm").equals("xj")){
//				request.setAttribute("message", "�������޸�У����Ŀ��");
//				model.setDoType(QUERY);
//				return xmglManage(mapping, form, request, response);
//		}
//	}
		if (StringUtils.isNotNull(pkValue)){
			CommService service = new CommService();
			SztzService sztzService = new SztzService();
			//��Ŀ��Ϣ
			HashMap<String,String> xmInfo = service.getRsInfo(tableName, "id", pkValue, colList);
			request.setAttribute("rs", xmInfo);
			if(userType.equalsIgnoreCase("xy")){//Ժϵ�û�
				request.setAttribute("xydm", xmInfo.get("xydm"));
			}
			request.setAttribute("sbr", xmInfo.get("sbr"));
			//��Ŀ����
			List<HashMap<String,String>> jxList = sztzService.getXmjxList(pkValue);
			request.setAttribute("jxList", jxList);
		}
		request.setAttribute("doType", model.getDoType());
		setList(request);
		if("view".equals(model.getDoType())){
			return mapping.findForward("ckxmsb");
		}else{
			return mapping.findForward("xmsb");
		}
	}
	
	/**
	 * ɾ����Ŀ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkXgXm (ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		CommService commService = new CommService();
		String tableName = "xg_sztz_xmglb";
		String[] colList = new String[]{"id","xn","xq","kmdm","hxnl",
				"xmlx","xmmc","shlcid","jcf","sqbm","jbkssj",
				"jbjssj","zbf","xs","sbr","fzr","bz","rssx","nj","xydm","shzt"};
		HashMap<String,String> xmInfo = commService.getRsInfo(tableName, "id", pkValue, colList);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(xmInfo.get("xydm").equals("xj")?"false":"true");
		return null;
	}
	
	
	/**
	 * ��Ŀ����
	 */
	public ActionForward xmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 
		SztzForm model = (SztzForm) form;
		User user = getUser(request);
		String xh = model.getXh();
		
		if ("stu".equalsIgnoreCase(user.getUserType())){
			xh = user.getUserName();
		}
		
		XsxxglService service = new XsxxglService();
		//����ѧ��������Ϣ
		HashMap<String,String> stu = service.selectStuinfo(xh);
		
		//������Ŀ��Ϣ
		if (StringUtils.isNotNull(model.getXmid())){
			CommService commService = new CommService();
			String[] colList = new String[]{"id","xn","xqmc","kmmc","shlcid",
					"hxnlmc","xmlxmc","zbf","fzr","jcf","jbkssj","jbjssj"};
			HashMap<String,String> rs = commService.getRsInfo("xg_view_sztz_xmglb", "id", model.getXmid(), colList);
			request.setAttribute("rs", rs);
		}
		
		request.setAttribute("stu", stu);
		return mapping.findForward("xmsq");
	}
	
	
	/**
	 * ��Ŀ���뱣��
	 */
	public ActionForward xmsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SztzForm model = (SztzForm) form;
		
		if (StringUtils.isNull(model.getId())){
			model.setId(GetSysData.getGuid());
		}
		
		String tableName = "xg_sztz_xssqb";
		String[] onezd = new String[]{"id","xh","xmid","cyjs","sfcx","cgms","bz","shlcid","shzt"};
		SaveForm saveForm = new SaveForm();
		SztzService sztzService = new SztzService();
		
		saveForm.setOnezd(onezd);
		saveForm.setTableName(tableName);
		saveForm.setPk("id");
		saveForm.setPkValue(new String[]{model.getId()});
		boolean result = sztzService.saveSztz(saveForm, model, request);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		
		//�ύ���������
		if (result && "��".equalsIgnoreCase(model.getSftj())){
			String shztb = "xg_sztz_xssqshjlb";
			AuditingInterface manage = new SztzService();
			AuditingModel auditModel = new AuditingModel();
			
			auditModel.setXtgwid("Applicant");
			auditModel.setId(model.getId());
			auditModel.setShlcid(model.getShlcid());
			auditModel.setShr(model.getXh());
			auditModel.setSftj(model.getSftj());
			auditModel.setSqjlb("xg_sztz_xssqb");
			manage.saveAuditing(auditModel, shztb,null,auditModel);
		}
		
		return xmsq(mapping, form, request, response);
	}
	
	
	/**
	 * ѧ�������¼�б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xssqQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		SztzForm model = (SztzForm) form;
		SztzService service = new SztzService();
		
		String[] topTr = new String[]{"ѧ��","����","ѧ��","ѧ��","��Ŀ����",
				"��Ŀ����","������Ŀ","��������","���״̬"};
		
		if ("stu".equalsIgnoreCase(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		List<String[]> rs = service.sztzJgcx(model, user);
		request.setAttribute("rs", rs);
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("path", "sztz_xssq.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		return mapping.findForward("xssqQuery");
	}
	
	
	/**
	 * ѧ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfshQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		SztzForm model = (SztzForm) form;
		SztzService service = new SztzService();
		String[] topTr = new String[]{"ѧ��","����","ѧ��","ѧ��","��Ŀ����","����ʱ��"};
		
		if ("xy".equalsIgnoreCase(user.getUserType()) 
				&& !Boolean.parseBoolean(user.getIsFdy())){
			model.setXydm(user.getUserDep());
		}
		if(model.getXn()==null){
			model.setXn(Base.currXn);
		}
		if(model.getXq()==null){
			model.setXq(Base.currXq);
		}
		//if (QUERY.equalsIgnoreCase(model.getDoType())){
			List<String[]> rs = service.xfshQuery(model, user);
			request.setAttribute("rs", rs);
	//	}
		
		setList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("topTr", topTr);
		request.setAttribute("shztList", service.getList("shzt"));
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "sztz_xfsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xfshQuery");
	}
	
	public ActionForward tcsdxf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		String jxdm = request.getParameter("jxdm");
		String xmid = request.getParameter("xmid");
		SztzService service = new SztzService();
		List<HashMap<String, String>> list = service.getSdxf(jxdm,xmid);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	/**
	 * ������չ�������
	 */
	public ActionForward sztzXfPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SztzForm model = (SztzForm) form;
		String[] pkValues = model.getPrimarykey_cbv(); 
		User user = getUser(request);
		
		String shztb = "xg_sztz_xssqshjlb";
		String sqb = "xg_sztz_xssqb";
		AuditingInterface manage = new SztzService();
		AuditingModel auditingModel = new AuditingModel();
		auditingModel.setSftj("��");
		auditingModel.setShr(user.getUserName());
		auditingModel.setShyj(model.getShyj());
		auditingModel.setShzt(model.getShzt());
		auditingModel.setSqjlb(sqb);
		boolean result = manage.saveBatchAuditingAndDoSomething(auditingModel, pkValues, shztb,null,auditingModel);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		
		model.setDoType(QUERY);
		return xfshQuery(mapping, form, request, response);
	}
	
	
	/**
	 * ������չ�������
	 */
	public ActionForward sztzXmPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SztzForm model = (SztzForm) form;
		String[] pkValues = model.getPrimarykey_cbv(); 
		User user = getUser(request);
		
		String shztb = "xg_sztz_xmshjlb";
		String sqb = "xg_sztz_xmglb";
		AuditingInterface manage = new SztzService();
		AuditingModel auditingModel = new AuditingModel();
		auditingModel.setSftj("��");
		auditingModel.setShr(user.getUserName());
		auditingModel.setShyj(model.getShyj());
		auditingModel.setShzt(model.getShzt());
		auditingModel.setSqjlb(sqb);
		boolean result = manage.saveBatchAuditingAndDoSomething(auditingModel, pkValues, shztb,null,auditingModel);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		
		model.setDoType(QUERY);
		return xmshManage(mapping, form, request, response);
	}
	
	
	/**
	 * ������չ�������-��ѯ
	 */
	public ActionForward sztzDgshView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String pkValue = request.getParameter("pkValue");
		SztzService service = new SztzService();
		
		//������ѯ
		if (StringUtils.isNotNull(pkValue)){
			User user = getUser(request);
			
			//������Ϣ
			HashMap<String,String> rs = service.getXmsqInfoById(pkValue);
			request.setAttribute("rs", rs);
			
			//��Ŀ����
			request.setAttribute("xmjxList", service.getXmjxList(rs.get("xmid")));
			
			//�˻�ʱ��Ҫѡ���˻ص��ĸ�����
			//XsxxXjydService manage = new XsxxXjydService();
			AuditingModel auditingModel = new AuditingModel();
			auditingModel.setId(rs.get("id"));
			auditingModel.setShr(user.getUserName());
			request.setAttribute("xtgwList", service.getKthXtgw(rs.get("shlcid"), service.getSpgw(auditingModel, "xg_sztz_xssqshjlb")));
		}
		
		request.setAttribute("sysdate", GetTime.getTimeByFormat("yyyy-mm-dd"));
		request.setAttribute("shztList", service.getList("shzt"));
		return mapping.findForward("sztzDgshView");
	}

	
	/**
	 * ������չѧ�������¼�޸ļ���
	 */
	public ActionForward sztzUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		SztzService service = new SztzService();
		String pkValue = request.getParameter("pkValue");
		
		//������ѯ
		if (StringUtils.isNotNull(pkValue)){
			//������Ϣ
			HashMap<String,String> rs = service.getXmsqInfoById(pkValue);
			request.setAttribute("rs", rs);
		}
		
		return mapping.findForward("sztzUpdate");
	}
	
	
	/**
	 * ������չ�������-����
	 */
	public ActionForward sztzDgshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SztzForm model = (SztzForm) form;
		SztzService service = new SztzService();
		User user = getUser(request);
		
		//�������ý�����Ϣ
		boolean result = service.updateSdjx(model);
		
		if (result){
			AuditingModel auditingModel = new AuditingModel();
			auditingModel.setId(request.getParameter("id"));
			auditingModel.setSftj(model.getSftj());
			auditingModel.setShr(user.getUserName());
			auditingModel.setShyj(model.getShyj());
			auditingModel.setShzt(model.getShzt());
			auditingModel.setShlcid(model.getShlcid());
			auditingModel.setThgw(model.getThjsr());
			auditingModel.setSqjlb("xg_sztz_xssqb");
			result = service.saveAuditing(auditingModel, "xg_sztz_xssqshjlb",null,auditingModel);
			request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		}
		
		return sztzDgshView(mapping, form, request, response);
	}
	
	
	/**
	 * ������չ�����¼ɾ��
	 */
	public ActionForward sztzDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		SztzForm model = (SztzForm) form;
		SztzService service = new SztzService();
		
		boolean result = service.delSztz(model);
		request.setAttribute("message", result ? DEL_SUCCESS : DEL_FAIL);
		
		model.setDoType(QUERY);
		if ("stu".equalsIgnoreCase(user.getUserType())){
			return xssqQuery(mapping, form, request, response);
		} else {
			return xfglManage(mapping, form, request, response);
		}
	}
	
	
	/**
	 * ������չ�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		SztzForm model = (SztzForm) form;
		SztzService service = new SztzService();
		String[] topTr = new String[]{"ѧ��","����","ѧ��","ѧ��","��Ŀ����",
						"��Ŀ����","������Ŀ","��������","ѧʱ","���״̬"};
		
		if ("xy".equalsIgnoreCase(user.getUserType()) 
				&& !Boolean.parseBoolean(user.getIsFdy())){
			model.setXydm(user.getUserDep());
		}
		
		if ("stu".equalsIgnoreCase(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(model.getXn()==null){
			model.setXn(Base.currXn);
		}
		if(model.getXq()==null){
			model.setXq(Base.currXq);
		}
		
	//	if (QUERY.equalsIgnoreCase(model.getDoType())){
			List<String[]> rs = service.sztzJgcx(model, user);
			request.setAttribute("rs", rs);
	//	}
		
		setList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "sztz_xfcx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xfglManage");
	}
	
	
	/**
	 * ִ�и��ֲ�ѯ
	 */
	public ActionForward xsffManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		SztzForm model = (SztzForm) form;
		SztzService service = new SztzService();
		
		String[] topTr = new String[]{"ѧ��","����","ѧ��","ѧ��","��Ŀ����","��Ŀ����","�Ƿ�����","������","������","ѧ��"};
		
		if ("xy".equalsIgnoreCase(user.getUserType()) 
				&& !Boolean.parseBoolean(user.getIsFdy())){
			model.setXydm(user.getUserDep());
		}
		
		if ("stu".equalsIgnoreCase(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if(model.getXn()==null){
			model.setXn(Base.currXn);
		}
		if(model.getXq()==null){
			model.setXq(Base.currXq);
		}
		//if (QUERY.equalsIgnoreCase(model.getDoType())){
			List<String[]> rs = service.sztzXfcx(model, user);
			request.setAttribute("rs", rs);
		//}
		
		setList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("topTr", topTr);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("path", "sztz_zxff.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsffManage");
	}
	
	
	/**
	 * ִ�и��ֱ���
	 */
	public ActionForward xsffSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SztzForm model = (SztzForm) form;
		SztzService service = new SztzService();
		String[] pkValues = request.getParameterValues("primarykey_cbv");
		String[] sdxf = request.getParameterValues("sdxf");
		
		boolean result = service.saveZxff(pkValues, sdxf);
		request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		
		model.setDoType(QUERY);
		return xsffManage(mapping, form, request, response);
	}
	
	
	/**
	 * ������չ�ɼ�����ӡ
	 */
	public ActionForward sztzCjdPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SztzForm model = (SztzForm) form;
		SztzService service = new SztzService();
		
		String pk =request.getParameter("pkValue");
		String[] pkArr = pk.split(",");
		model.setXh(pkArr[0]);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.printSztzCjd(model, response.getOutputStream());
		
		return null;
	}
	
	
	
	/**
	 * ������չ-���̸���
	 * @return
	 * @throws Exception
	 */
	public ActionForward sztzLcgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String pkValue = request.getParameter("pkValue");
		SztzService service = new SztzService();
		
		if (StringUtils.isNotNull(pkValue)){
			
			HashMap<String,String> rs = service.getXmsqInfoById(pkValue);
			rs.put("title", rs.get("xm")+"��ѧ������");
			request.setAttribute("rs", rs);
			List<HashMap<String,String>> shxx = service.getShxxList(pkValue,"xg_sztz_xssqshjlb");//�����Ϣ

			request.setAttribute("shxx", shxx);
			request.setAttribute("spgwList", AuditUtil.getSpgw(rs.get("shlcid")));
		}
		
		return mapping.findForward("sztzLcgz");
	}
	
	
	/**
	 * ������չ--��Ŀ���--���̸���
	 * @return
	 * @throws Exception
	 */
	public ActionForward sztzXmshLcgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String pkValue = request.getParameter("pkValue");
		SztzService service = new SztzService();
		
		if (StringUtils.isNotNull(pkValue)){
			CommService commService = new CommService();
			String[] colList = new String[]{"xmmc","shlcid"};
			//��Ŀ��Ϣ
			HashMap<String,String> xmInfo = commService.getRsInfo("xg_view_sztz_xmglb", "id", pkValue, colList);
			xmInfo.put("title", xmInfo.get("xmmc"));
			request.setAttribute("rs", xmInfo);
			
			List<HashMap<String,String>> shxx = service.getShxxList(pkValue,"xg_sztz_xmshjlb");//�����Ϣ

			request.setAttribute("shxx", shxx);
			request.setAttribute("spgwList", AuditUtil.getSpgw(xmInfo.get("shlcid")));
		}
		
		return mapping.findForward("sztzLcgz");
	}
	
	
	
	/**
	 * Ajax��֤������Ŀ�����Ƿ����
	 */
	public ActionForward checkKmdmExists(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		SztzForm model = (SztzForm) form;
		SztzService service = new SztzService();
		String kmdm = model.getKmdm();
		
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(service.checkKmdmExists(kmdm));
		return null;
	}
	
	
	
	/**
	 * ��Ŀ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		SztzForm model = (SztzForm) form;
		SztzService service = new SztzService();
		String[] topTr = new String[]{"��Ŀ����","ѧ��","ѧ��","��Ŀ����","��������","��Ŀ����"};
		if(model.getXn()==null){
			model.setXn(Base.currXn);
		}
		if(model.getXq()==null){
			model.setXq(Base.currXq);
		}
		//��ѯ
		List<String[]> rs = service.xmshQuery(model, user);
		request.setAttribute("rs", rs);
		
		setList(request);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("shztList", service.getList("shzt"));
		request.setAttribute("topTr", topTr);
		request.setAttribute("path", "sztz_xmsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmshManage");
	}
	
	
	
	/**
	 * ����������չ��Ŀ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sztzXmshView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String pkValue = request.getParameter("pkValue");
		CommService service = new CommService();
		SztzService sztzService = new SztzService();
		//������ѯ
		if (StringUtils.isNotNull(pkValue)){
			String[] colList = new String[]{"id","xn","xqmc","kmmc","hxnlmc",
					"xmlxmc","xmmc","jcf","sqbm","jbkssj","shlcid",
					"jbjssj","zbf","sbr","fzr","bz","rssx"};
			//��Ŀ��Ϣ
			HashMap<String,String> xmInfo = service.getRsInfo("xg_view_sztz_xmglb", "id", pkValue, colList);
			request.setAttribute("rs", xmInfo);
			//��Ŀ����
			List<HashMap<String,String>> jxList = sztzService.getXmjxList(pkValue);
			request.setAttribute("jxList", jxList);
			
			User user = getUser(request);
			//�˻�ʱ��Ҫѡ���˻ص��ĸ�����
			AuditingModel auditingModel = new AuditingModel();
			auditingModel.setId(xmInfo.get("id"));
			auditingModel.setShr(user.getUserName());
			request.setAttribute("xtgwList", sztzService.getKthXtgw(xmInfo.get("shlcid"), sztzService.getSpgw(auditingModel, "xg_sztz_xmshjlb")));
		}
		
		request.setAttribute("sysdate", GetTime.getTimeByFormat("yyyy-mm-dd"));
		request.setAttribute("shztList", sztzService.getList("shzt"));
		
		return mapping.findForward("sztzXmshView");
	}
	
	
	
	/**
	 * ������չ��Ŀ�������-����
	 */
	public ActionForward sztzXmshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SztzForm model = (SztzForm) form;
		AuditingInterface service = new SztzService();
		User user = getUser(request);
		boolean result = false;
		//�������ý�����Ϣ
		//boolean result = service.updateSdjx(model);
		
		//if (result){
			AuditingModel auditingModel = new AuditingModel();
			auditingModel.setId(request.getParameter("id"));
			auditingModel.setSftj(model.getSftj());
			auditingModel.setShr(user.getUserName());
			auditingModel.setShyj(model.getShyj());
			auditingModel.setShzt(model.getShzt());
			auditingModel.setShlcid(model.getShlcid());
			auditingModel.setThgw(model.getThjsr());
			auditingModel.setSqjlb("xg_sztz_xmglb");
			
			result = service.saveAuditing(auditingModel, "xg_sztz_xmshjlb",null,auditingModel);
			request.setAttribute("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		//}
		
		return sztzXmshView(mapping, form, request, response);
	}


	
	/**
	 * ѧ��������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xssqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SztzForm model = (SztzForm) form;
		SztzService service = new SztzService();
		User user = getUser(request);
		// �꼶�б�
		List<HashMap<String, String>> njList = Base.getNjList();
		request.setAttribute("njList", njList);
		String xh = model.getXh();
		
		if ("stu".equalsIgnoreCase(user.getUserType())){
			xh = user.getUserName();
		}
		
		if (StringUtils.isNotNull(xh)){
			//����ѧ��������Ϣ
			XsxxglService xsxxService = new XsxxglService();
			HashMap<String,String> stu = xsxxService.selectStuinfo(xh);
			request.setAttribute("stu", stu);
			if(stu.get("nj")!="" && stu.get("nj")!=null){
				request.setAttribute("nj", stu.get("nj"));
				model.setNj(stu.get("nj"));
			}
			if(stu.get("xh")!="" && stu.get("xh")!=null){
				model.setXh(stu.get("xh"));
			}
			//���������Ŀ��Ϣ�������������
			request.setAttribute("xmList", service.getXmsqList(model));
			if(model.getNj()!=null){
				request.setAttribute("nj", model.getNj());
			}
		}
		
		request.setAttribute("cssz", service.getCssz(Base.xxdm));
		request.setAttribute("path", "sztz_xssq.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.getDqxqmc());
		setList(request);
		DAO dao = DAO.getInstance();
		// ��ò������ñ�����(�����������ʱ�䷶Χ)
		String sql = "select * from xg_sztz_csszb where xxdm=? and to_char(sysdate,'yyyy-MM-dd HH:mm:ss') between sbkssj and sbjssj";
		String tag = dao.returntag(sql, new String[] {Base.xxdm});
		if ("empty".equalsIgnoreCase(tag)) {
			//��������ʱ�䷶Χ
			if(service.getCssz(Base.xxdm).toString()!="{}"){
				request.setAttribute("sqsjTag", "1");
			}else{//û������ʱ�䷶Χ
				request.setAttribute("sqsjTag", "2");
			}
			
		}else{
			//������ʱ����
			request.setAttribute("sqsjTag", "0");
		} 	   
		return mapping.findForward("xssqManage");
	}
	
	
	
	/**
	 * ��Ŀ����Ajax���ύ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward xmsqAjaxSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean flg = false;
		SztzForm model = (SztzForm) form;
		
		model.setShzt(URLDecoder.decode(model.getShzt(),"utf-8"));
		
		if (StringUtils.isNull(model.getId())){
			model.setId(GetSysData.getGuid());
			flg = true;
		}
		
		String tableName = "xg_sztz_xssqb";
		String[] onezd = new String[]{"id","xh","xmid","cyjs","sfcx","cgms","bz","shlcid"};
		SaveForm saveForm = new SaveForm();
		SztzService sztzService = new SztzService();
		
		saveForm.setOnezd(onezd);
		saveForm.setTableName(tableName);
		saveForm.setPk("id");
		saveForm.setPkValue(new String[]{model.getId()});
		
		//ת��
		model.setCyjs(URLDecoder.decode(model.getCyjs(),"utf-8"));
		model.setSfcx(URLDecoder.decode(model.getSfcx(),"utf-8"));
		model.setCgms(URLDecoder.decode(model.getCgms(),"utf-8"));
		model.setBz(URLDecoder.decode(model.getBz(),"utf-8"));
		
		boolean result = sztzService.saveSztz(saveForm, model, request);
		
		//�ύ���������
		if (result && (flg || "�˻�".equalsIgnoreCase(model.getShzt()))){
			String shztb = "xg_sztz_xssqshjlb";
			AuditingInterface manage = new SztzService();
			AuditingModel auditModel = new AuditingModel();
			
			auditModel.setXtgwid("Applicant");
			auditModel.setId(model.getId());
			auditModel.setShlcid(model.getShlcid());
			auditModel.setShr(model.getXh());
			auditModel.setSftj("��");
			auditModel.setSqjlb("xg_sztz_xssqb");
			manage.saveAuditing(auditModel, shztb,null,auditModel);
		}
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("message", result ? SAVE_SUCCESS : SAVE_FAIL);
		map.put("id", model.getId());
		
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print( JSONObject.fromObject(map));
		
		return null;
	}

	
	
	public ActionForward sztzMxqCjdPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SztzForm model = (SztzForm) form;
		SztzService service = new SztzService();
		
		String pk =request.getParameter("pkValue");
		String[] pkArr = pk.split(",");
		model.setXh(pkArr[0]);
		model.setXn(pkArr[1]);
		model.setXq(pkArr[2]);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.printMxqCjd(model, response.getOutputStream());
		
		return null;
	}
	
	
	/**
	 * ������չ--��Ŀ����
	 * @return
	 * @throws Exception
	 */
	public ActionForward sztzXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SztzService service = new SztzService();
		String xmid = request.getParameter("xmid");
		SztzForm model = (SztzForm) form;
		String xh = model.getXh();
		User user = getUser(request);
		if ("stu".equalsIgnoreCase(user.getUserType())){
			xh = user.getUserName();
		}
		XsxxglService xsxxService = new XsxxglService();
		HashMap<String,String> stu = xsxxService.selectStuinfo(xh);
		request.setAttribute("stu", stu);
		if(stu.get("nj")!="" && stu.get("nj")!=null){
			request.setAttribute("nj", stu.get("nj"));
			model.setNj(stu.get("nj"));
		}
		if(stu.get("xh")!="" && stu.get("xh")!=null){
			model.setXh(stu.get("xh"));
		}
		if (StringUtils.isNotNull(xmid)){
			//��Ŀ��Ϣ
			model.setXmid(xmid);
			HashMap<String,String> xmInfo  = new HashMap<String,String>();
			List<HashMap<String,String>>  xmsqList = service.getXsxmsq(model);
			if(null!=xmsqList&&xmsqList.size()>0){
				 xmInfo = xmsqList.get(0);
			}
			request.setAttribute("rs", xmInfo);
			request.setAttribute("xh", model.getXh());
		}
		
		return mapping.findForward("sztzXmsq");
	}
	
	/**
	 * ������չ--��Ŀ����
	 * @return
	 * @throws Exception
	 */
	public ActionForward ckXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SztzService service = new SztzService();
		String xmid = request.getParameter("xmid");
		SztzForm model = (SztzForm) form;
		if (StringUtils.isNotNull(xmid)){
			//��Ŀ��Ϣ
			model.setXmid(xmid);
			HashMap<String,String> xmInfo  = new HashMap<String,String>();
			List<HashMap<String,String>>  xmsqList = service.getXsxmsq(model);
			if(null!=xmsqList&&xmsqList.size()>0){
				 xmInfo = xmsqList.get(0);
			}
			request.setAttribute("rs", xmInfo);
			request.setAttribute("xh", model.getXh());
		}
		
		return mapping.findForward("ckXmsq");
	}
	
	
	/**
	 * ��Ŀ���뱣��
	 */
	public ActionForward xsxmsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean flg = false;
		SztzForm model = (SztzForm) form;
		
		if (StringUtils.isNull(model.getId())){
			model.setId(GetSysData.getGuid());
			flg = true;
		}
		
		String tableName = "xg_sztz_xssqb";
		String[] onezd = new String[]{"id","xh","xmid","cyjs","sfcx","cgms","bz","shlcid","shzt"};
		SaveForm saveForm = new SaveForm();
		SztzService sztzService = new SztzService();
		
		saveForm.setOnezd(onezd);
		saveForm.setTableName(tableName);
		saveForm.setPk("id");
		saveForm.setPkValue(new String[]{model.getId()});
		boolean result = sztzService.saveSztz(saveForm, model, request);
		request.setAttribute("message", result ? SQ_SUCCESS : SQ_FAIL);
		
		//�ύ���������
		if (result && (flg || "�˻�".equalsIgnoreCase(model.getShzt())|| "��ͨ��".equalsIgnoreCase(model.getShzt()))){
			String shztb = "xg_sztz_xssqshjlb";
			AuditingInterface manage = new SztzService();
			AuditingModel auditModel = new AuditingModel();
			
			auditModel.setXtgwid("Applicant");
			auditModel.setId(model.getId());
			auditModel.setShlcid(model.getShlcid());
			auditModel.setShr(model.getXh());
			auditModel.setSftj("��");
			auditModel.setSqjlb("xg_sztz_xssqb");
			manage.saveAuditing(auditModel, shztb,null,auditModel);
		}
		
		return sztzXmsq(mapping, form, request, response);
	}
}
