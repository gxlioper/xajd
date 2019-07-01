package xgxt.rcgl.nbcs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.utils.String.StringUtils;

public class XfxxglAction  extends DispatchAction {
	
	/***
	 * ����ѧ����Ϣ�б�
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": (xydm==null ? "" : xydm); 
		zy = zy==null ? "": (zydm==null ? "" : zydm); 
		njLocal = nj==null ? "": nj;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		
		request.setAttribute("writeAble", "yes");//�ж��û���дȨ		
		request.setAttribute("njList", Base.getNjList());//�꼶�б�
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//�༶�б�
	}
	
	/***
	 * ����ʱ����Ϣ�б�
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * */
	public void appendTimeList(HttpServletRequest request){
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());//ѧ���б�
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		request.setAttribute("yfList", Base.getYfList());//�·��б�
	}
	
	/**
	 * ��ʾѧ��ѧ����Ϣ����ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xfxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XfxxglForm model = (XfxxglForm)form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		if("stu".equalsIgnoreCase(userType)){
			return xsxfxxcx(mapping, form, request, response);
		}
		
		request.setAttribute("realTable", "nbcs_xsxfxxb");
		request.setAttribute("writeAble", "yes");
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		appendProperties(request, "", "", "");
		return mapping.findForward("xfxxgl");
	}
	
	/**
	 * ��ѯѧ��ѧ����Ϣ
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xsxfxxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		if("stu".equalsIgnoreCase(userType)){
			model.setXh(session.getAttribute("userName").toString());
		}
		
		List<String[]> rs = service.queryXsxfxxb(model);
		
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("topTr", service.getXsxfxxbTopTr());		
		request.setAttribute("realTable", "nbcs_xsxfxxb");
		request.setAttribute("writeAble", "yes");
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		return mapping.findForward("xfxxgl");
	}
	
	/**
	 * ��ʾѧ��ѧ����Ϣ����ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xsxfxxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglForm model = (XfxxglForm)form;
		StudentInfoService xsxx = new StudentInfoService();
		request.setAttribute("rs", xsxx.getStuInfo(request.getParameter("xh")));
		
		model.setNd(Base.currNd);
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		return mapping.findForward("xsxfxxAdd");
	}
	
	/**
	 * ��ʾѧ��ѧ����Ϣ�޸�ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xsxfxxModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		model.setPkValue(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.queryXsxfxxbOne(model));//��������ѯ��Ϣ
		return mapping.findForward("xsxfxxModi");
	}
	
	/**
	 * ѧ��ѧ����Ϣ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveXsxfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		String page = "xsxfxxModi";
		String doType = request.getParameter("doType");
		
		
		model.setPkValue(model.getNd()+model.getXh());//��������ֵ		
		page = StringUtils.isNotNull(doType) && "add".equalsIgnoreCase(doType) ? "xsxfxxAdd" : page;
		
		request.setAttribute("result", service.saveXsxfxxb(model,request));//��Ϣ����
		request.setAttribute("rs", service.queryXsxfxxbOne(model));
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		return mapping.findForward(page);
	}
	
	/**
	 * ѧ��ѧ����Ϣɾ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward delXsxfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		
		request.setAttribute("result", service.delXsxfxxb(model));//ɾ����Ϣ
		return xsxfxxcx(mapping, form, request, response);
	}
	
	/**
	 * ѧ��ѧ����Ϣ��ѯ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expXsxfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		model.setNd(request.getParameter("nd"));
		model.setXydm(request.getParameter("xh"));
		
		String[] colList = {"nd","xh","xm","xb","xz","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","xxxs","pycc","zkzh","qjxf","qtf","qjdgf","Ƿ�ɺϼ�","jfzt"};
		String[] colListCN = service.getColCN("view_nbcs_xsxfxxb", colList);
		List<String[]> rs = service.queryXsxfxxbForExp(model);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * ��ʾѧ���̲ķѽ������ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward jcfjsgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XfxxglForm model = (XfxxglForm)form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		if("stu".equalsIgnoreCase(userType)){
			return jcfjsxxcx(mapping, form, request, response);
		}
		
		request.setAttribute("realTable", "jcfjsb");
		request.setAttribute("writeAble", "yes");
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		appendProperties(request, "", "", "");
		return mapping.findForward("jcfjsgl");
	}
	
	/**
	 * ��ѯ�̲ķѽ�����Ϣ
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward jcfjsxxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		if("stu".equalsIgnoreCase(userType)){
			model.setXh(session.getAttribute("userName").toString());
		}
		
		List<String[]> rs = service.queryJcfjsb(model);
		
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("topTr", service.getJcfjsbTopTr());		
		request.setAttribute("realTable", "jcfjsb");
		request.setAttribute("writeAble", "yes");
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		return mapping.findForward("jcfjsgl");
	}
	
	/**
	 * ��ʾ�̲ķѽ�������ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward jcfjsxxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglForm model = (XfxxglForm)form;
		StudentInfoService xsxx = new StudentInfoService();
		request.setAttribute("rs", xsxx.getStuInfo(request.getParameter("xh")));
		
		model.setNd(Base.currNd);
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		return mapping.findForward("jcfjsxxAdd");
	}
	
	/**
	 * ��ʾ�̲ķѽ����޸�ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward jcfjsxxModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		model.setPkValue(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.queryJcfjsbOne(model));//��������ѯ��Ϣ
		return mapping.findForward("jcfjsxxModi");
	}
	
	/**
	 * �̲ķѽ�����Ϣ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveJcfjsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		String page = "jcfjsxxModi";
		String doType = request.getParameter("doType");
		
		
		model.setPkValue(model.getNd()+model.getXh());//��������ֵ		
		page = StringUtils.isNotNull(doType) && "add".equalsIgnoreCase(doType) ? "jcfjsxxAdd" : page;
		
		request.setAttribute("result", service.saveJcfjsb(model,request));//��Ϣ����
		request.setAttribute("rs", service.queryJcfjsbOne(model));
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		return mapping.findForward(page);
	}
	
	/**
	 * �̲ķѽ�����Ϣɾ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward delJcfjsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		
		request.setAttribute("result", service.delJcfjsb(model));//ɾ����Ϣ
		return jcfjsxxcx(mapping, form, request, response);
	}
	
	/**
	 * �̲ķѽ�����Ϣ��ѯ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expJcfjsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		model.setNd(request.getParameter("nd"));
		model.setXydm(request.getParameter("xh"));
		
		String[] colList = {"nd","xh","xm","xb","xz","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","jyje"};
		String[] colListCN = service.getColCN("view_jcfjsb", colList);
		List<String[]> rs = service.queryJcfjsbForExp(model);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
}
