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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

public class BylfglAction  extends DispatchAction {
	
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
	 * ��ʾѧԺ��ҵ���������Ϣҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		BylfglForm model = (BylfglForm)form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		
		request.setAttribute("realTable", "bylfglb");
		request.setAttribute("writeAble", "yes");
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		return mapping.findForward("bylfgl");
	}
	
	/**
	 * ��ѯѧԺ��ҵ�����Ϣ
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		
		List<String[]> rs = service.queryBylfglb(model);
		
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("topTr", service.getBylfsqTopTr());		
		request.setAttribute("realTable", "bylfglb");
		request.setAttribute("writeAble", "yes");
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		return mapping.findForward("bylfgl");
	}
	
	/**
	 * ��ʾѧԺ��ҵ�������ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfsqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		BylfglForm model = (BylfglForm)form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		model.setNd(Base.currNd);
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		return mapping.findForward("bylfsqAdd");
	}
	
	/**
	 * ��ʾѧԺ��ҵ��������޸�ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfsqModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		model.setPkValue(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.queryBylfglbOne(model));//��������ѯ��Ϣ
		return mapping.findForward("bylfsqModi");
	}
	
	/**
	 * ��ҵ���������Ϣ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveBylfsqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		String page = "bylfsqModi";
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		
		model.setPkValue(model.getNd()+model.getXydm());//��������ֵ		
		page = StringUtils.isNotNull(doType) && "add".equalsIgnoreCase(doType) ? "bylfsqAdd" : page;
		
		request.setAttribute("result", service.saveBylfglbSq(model,request));//��Ϣ����
		request.setAttribute("rs", service.queryBylfglbOne(model));
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		return mapping.findForward(page);
	}
	
	/**
	 * ��ҵ�����Ϣɾ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward delBylfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		
		request.setAttribute("result", service.delBylfglb(model));//ɾ����Ϣ
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		return bylfcx(mapping, form, request, response);
	}
	
	/**
	 * ��ҵ�����Ϣ��ѯ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expBylfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		model.setNd(request.getParameter("nd"));
		model.setXydm(request.getParameter("xydm"));
		
		String[] colList = {"nd","xydm","xymc","sqsj","lqr","xxsh","shryhm","shrxm","shsj","bkfxl","bkfl","bkfm","bkfs","zkfxl","zkfl","zkfm","zkfs","dsfl","dsfm","dsfs","dsfxl","ghbkfl","xzfxl","xzfl","xzfm","xzfs","ghbkfxl","ghbkfl","ghbkfm","ghbkfs","ghzkfxl","ghzkfl","ghzkfm","ghzkfs","ghdsfxl","ghdsfl","ghdsfm","ghdsfs","ghxzfxl","ghxzfl","ghxzfm","ghxzfs","ghmaozi","ghpijian","ghlingdai","ghlingjie","lingdai","lingjie","maozi","pcje","pijian","shfz","shlingdai","shlingjie","shmaozi"};
		String[] colListCN = service.getColCN("view_bylfglb", colList);
		List<String[]> rs = service.queryBylfglbForExp(model);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * ��ʾѧԺ��ҵ���������Ϣ���ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfshgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("writeAble", "yes");
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		return mapping.findForward("bylfshgl");
	}
	
	/**
	 * ��ѯѧԺ��ҵ�����Ϣ���
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		
		List<String[]> rs = service.queryBylfglb(model);
		
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("topTr", service.getBylfsqTopTr());		
		request.setAttribute("writeAble", "yes");
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		return mapping.findForward("bylfshgl");
	}
	
	/**
	 * ��ʾѧԺ��ҵ������뵥�����ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfsqAudi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		model.setPkValue(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.queryBylfglbOne(model));//��������ѯ��Ϣ
		return mapping.findForward("bylfsqAudi");
	}
	
	/**
	 * ��ҵ������������Ϣ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveBylfsqsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		
		model.setPkValue(model.getNd()+model.getXydm());//��������ֵ		
		model.setShryhm(request.getSession().getAttribute("userName").toString());
		model.setShsj(GetTime.getSystemTime());
		
		request.setAttribute("result", service.saveBylfglbSh(model,request));//��Ϣ����
		request.setAttribute("rs", service.queryBylfglbOne(model));
		return mapping.findForward("bylfsqAudi");
	}
	
	/**
	 * ��ҵ������������Ϣ��������
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveBylfsqshBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		model.setXxsh(request.getParameter("xxsh"));
		model.setShryhm(request.getSession().getAttribute("userName").toString());
		model.setShsj(GetTime.getSystemTime());
		
		request.setAttribute("result", service.saveBylfglbShBatch(model));//��Ϣ����
		return bylfshcx(mapping, form, request, response);
	}
	
	/**
	 * ��ʾѧԺ��ҵ�������黹�Ǽ�ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfghdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("writeAble", "yes");
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		return mapping.findForward("bylfghdj");
	}
	
	/**
	 * ��ѯѧԺ��ҵ����黹�Ǽ���Ϣ
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfghdjcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		model.setXxsh("ͨ��");
		
		List<String[]> rs = service.queryBylfglbGhxx(model);
		
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("topTr", service.getBylfghdjTopTr());		
		request.setAttribute("writeAble", "yes");
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		return mapping.findForward("bylfghdj");
	}
	
	
	/**
	 * ��ʾѧԺ��ҵ����黹�Ǽ�ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward bylfgh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		model.setPkValue(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.queryBylfglbOne(model));//��������ѯ��Ϣ
		return mapping.findForward("bylfgh");
	}
	
	/**
	 * ��ҵ����黹�Ǽ���Ϣ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveBylfghdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		
		model.setPkValue(model.getNd()+model.getXydm());//��������ֵ
		
		request.setAttribute("result", service.saveBylfghdj(model,request));//��Ϣ����
		request.setAttribute("rs", service.queryBylfglbOne(model));
		return mapping.findForward("bylfgh");
	}
	
	/**
	 * ��ӡ��ҵ����黹һ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward printBylfghllb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BylfglService service = new BylfglService();
		BylfglForm model = (BylfglForm)form;
		
		model.setNd(request.getParameter("nd"));
		model.setXydm(request.getParameter("xydm"));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.printBylfghllb(response.getOutputStream(),model);
		return mapping.findForward("");
	}
	
}
