package xgxt.pjpy.zgms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;
import xgxt.utils.dealDate;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;
import java.util.*;

public class PjpyZgmsAction extends CommonAction {

	private String xydm = "";
	private String zydm = "";
	private String nj = "";
	
	/**
	 * ���μ�¼ά��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kqWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZgmsActionForm zgmsForm = (PjpyZgmsActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			zgmsForm.setXydm(xydm);
		}
		String act = request.getParameter("act");//��ѯ״̬
		List<String[]> resList = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		if (!StringUtils.isNull(act) && "go".equalsIgnoreCase(act)) {
			ZgmsModel model = new ZgmsModel();
			KkjlService service = new KkjlService();
			BeanUtils.copyProperties(model, zgmsForm);
			topTr = service.title();
			resList = service.result(model);
		}
		zgmsForm.setXm(DealString.toGBK(zgmsForm.getXm()));
		appendProperties(request, xydm, zydm, nj);
		appendQryResult(request, topTr, resList);
		appendTableXx(request, "view_pjpy_xskqb", "pjpy_xskqb");
		return mapping.findForward("kkjlpage");
	}
	
	/**
	 * ���μ�¼����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kkjlAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZgmsActionForm zgmsForm = (PjpyZgmsActionForm) form;
		zgmsForm.setXh(request.getParameter("xh"));
		String act = request.getParameter("act");//����״̬
		String xh = request.getParameter("xh");
		HashMap<String, String> rs = new HashMap<String, String>();
		KkjlService service = new KkjlService();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuinfo(xh);
		}
		if (!StringUtils.isNull(act) && "save".equalsIgnoreCase(act)) {
			ZgmsModel model = new ZgmsModel();
			BeanUtils.copyProperties(model, zgmsForm);
			boolean bFlag = service.save(model, request);//��������
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		zgmsForm.setKkdd(DealString.toGBK(zgmsForm.getKkdd()));
		zgmsForm.setKkjl(DealString.toGBK(zgmsForm.getKkjl()));
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("kkjladdpage");
	}
	
	/**
	 * ���μ�¼�޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kkjlModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		PjpyZgmsActionForm zgmsForm = (PjpyZgmsActionForm) form;
		zgmsForm.setXh(request.getParameter("xh"));
		HashMap<String, String> rs = new HashMap<String, String>();
		KkjlService service = new KkjlService();
		if (!StringUtils.isNull(pkValue)) {
			rs = service.getKkjlinfo(pkValue);
		}
		if (!StringUtils.isNull(act) && "save".equalsIgnoreCase(act)) {
			ZgmsModel model = new ZgmsModel();
			BeanUtils.copyProperties(model, zgmsForm);
			
			boolean bFlag = service.update(model, request, pkValue);//�޸�����
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
			zgmsForm.setKkdd(DealString.toGBK(zgmsForm.getKkdd()));
			zgmsForm.setKkjl(DealString.toGBK(zgmsForm.getKkjl()));
		}
		if (!StringUtils.isNull(act) && "view".equalsIgnoreCase(act)) {
			request.setAttribute("flag", "view");
		}
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		if (rs != null) {
			zgmsForm.setXn(rs.get("xn"));
			zgmsForm.setXq(rs.get("xq"));
			zgmsForm.setKksj(rs.get("kksj"));
			zgmsForm.setKkdd(rs.get("kkdd"));
			zgmsForm.setKkjl(rs.get("kkjl"));
		}
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("kkjlmodipage");
	}
	
	/**
	 * ���μ�¼ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kkjlDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZgmsActionForm zgmsForm = (PjpyZgmsActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			zgmsForm.setXydm(xydm);
		}
		appendProperties(request, xydm, zydm, nj);
		KkjlService service = new KkjlService();
		String res = service.delete(zgmsForm.getCbv(), request);
		if (StringUtils.isNull(res)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "�������,���е�" + res + "�����ݲ���ʧ��!");
		}
		appendTableXx(request, "view_pjpy_xskqb", "pjpy_xskqb");
		return mapping.findForward("kkjlpage");
	}
	
	/**
	 * �ɼ�ά��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZgmsActionForm dataSearchForm = (PjpyZgmsActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			dataSearchForm.setXydm(xydm);
		}
		String act = request.getParameter("act");
		KkjlService service = new KkjlService();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		List<String[]> resList = new ArrayList<String[]>();
		if (!StringUtils.isNull(act) && "go".equalsIgnoreCase(act)) {
			ZgmsModel model = new ZgmsModel();
			BeanUtils.copyProperties(model, dataSearchForm);
			topList = service.cjTitle();
			resList = service.cjResult(model, dataSearchForm);
			int count = service.cjResultNum(model).size();//��ѯ�����ļ�¼��
			dataSearchForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//�������ļ�¼��
		}
		appendQryResult(request, topList, resList);
		appendTableXx(request, "view_cjb", "view_cjb");
		appendProperties(request, xydm, zydm, nj);
		dataSearchForm.setXm(DealString.toGBK(dataSearchForm.getXm()));
		return mapping.findForward("cjwhpage");
	}
	
	/**
	 * �ɼ�������ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		KkjlService service = new KkjlService();
		rs = service.cjView(pkValue);
		request.setAttribute("rs", rs);
		return mapping.findForward("cjview");
	}
	
	/**
	 * �������ά��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tydbWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZgmsActionForm zgmsForm = (PjpyZgmsActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			zgmsForm.setXydm(xydm);
		}
		nj = zgmsForm.getNj();
		zydm = zgmsForm.getZydm();
		String act = request.getParameter("act");
		KkjlService service = new KkjlService();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		List<String[]> rsList = new ArrayList<String[]>();
		if (!StringUtils.isNull(act) && "go".equalsIgnoreCase(act)) {
			ZgmsModel model = new ZgmsModel();
			BeanUtils.copyProperties(model, zgmsForm);
			topList = service.tydbTitle();
			rsList = service.tydbResult(model);
		}
		appendQryResult(request, topList, rsList);
		appendTableXx(request, "view_pjpy_tydbqkb", "pjpy_tydbqkb");
		appendProperties(request, xydm, zydm, nj);
		zgmsForm.setXm(DealString.toGBK(zgmsForm.getXm()));
		return mapping.findForward("tydbpage");
	}
	
	/**
	 * ��������޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tydbmodi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZgmsActionForm zgmsForm = (PjpyZgmsActionForm) form;
		zgmsForm.setXh(request.getParameter("xh"));
		KkjlService service = new KkjlService();
		List<HashMap<String, String>> tydbList = service.tydbList();
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		if (!StringUtils.isNull(act) && "view".equalsIgnoreCase(act)) {
			request.setAttribute("flag", "view");
		}
		HashMap<String, String> rs = service.viewTydbinfo(pkValue);
		if (!StringUtils.isNull(act) && "save".equalsIgnoreCase(act)) {
			ZgmsModel model = new ZgmsModel();
			BeanUtils.copyProperties(model, zgmsForm);
			boolean bFlag = service.updateTydb(pkValue, model, request);
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
			zgmsForm.setBz(DealString.toGBK(zgmsForm.getBz()));
		}
		if (rs != null) {
			zgmsForm.setTydb(rs.get("tydb"));
			zgmsForm.setBz(rs.get("bz"));
			zgmsForm.setXn(rs.get("xn"));
			zgmsForm.setXq(rs.get("xq"));
			zgmsForm.setTydb(rs.get("tydb"));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("tydbList", tydbList);
		
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("tydbmodipage");
	}
	
	/**
	 * �����������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tydbdel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZgmsActionForm zgmsForm = (PjpyZgmsActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			zgmsForm.setXydm(xydm);
		}
		zydm = zgmsForm.getZydm();
		nj = zgmsForm.getNj();
		KkjlService service = new KkjlService();
		String res = service.deleteTydb(zgmsForm.getCbv(), request);
		if (StringUtils.isNull(res)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "�������,���е�"+res+"����¼����ʧ��!");
		}
		appendTableXx(request, "view_pjpy_tydbqkb", "pjpy_tydbqkb");
		appendProperties(request, xydm, zydm, nj);
		zgmsForm.setXm(DealString.toGBK(zgmsForm.getXm()));
		return mapping.findForward("tydbpage");
	}
	
	/**
	 * �ɼ�ͬ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjtb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZgmsActionForm zgmsForm = (PjpyZgmsActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			zgmsForm.setXydm(xydm);
		}
		KkjlService service = new KkjlService();
		boolean bFlag = service.cjtb(request.getParameter("xn"));
		if (bFlag) {
			request.setAttribute("deleted", "ok");
		} else {
			request.setAttribute("deleted", "nok");
		}
		appendTableXx(request, "view_cjb", "view_cjb");
		appendProperties(request, xydm, zydm, nj);
		zgmsForm.setXm(DealString.toGBK(zgmsForm.getXm()));
		return mapping.findForward("cjwhpage");
	}
	
	public ActionForward ryprint (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String rychmc = DealString.toGBK(request.getParameter("rychmc"));
		rychmc = StringUtils.isNull(rychmc) ? "" :rychmc.trim();
		KkjlService service = new KkjlService();
		String pkValue = request.getParameter("pkValue");
		String pk = request.getParameter("p");
		pk = StringUtils.isNull(pk) ? "" : pk.trim();
		HashMap<String, String> rs = new HashMap<String, String>();
		if ("k".equalsIgnoreCase(pk)) {
			rs = service.rychPrint(pkValue);
		} else {
			rs = service.ryPrint(pkValue);
		}
		dealDate dd = new dealDate();
		String result = dd.getToday();
		String[] data = result.split("-");
		if (data != null && data.length==3) {
			rs.put("y", data[0]);
			rs.put("m", data[1]);
			rs.put("r", data[2]);
		}
		
		request.setAttribute("rs", rs);
		if ("Ժ�ű�ҵ��".equalsIgnoreCase(rychmc)) {
			return mapping.findForward("yybysprint");
		}
		return mapping.findForward("ryprint");
	}
	
}
