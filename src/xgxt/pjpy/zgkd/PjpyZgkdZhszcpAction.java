package xgxt.pjpy.zgkd;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyZgkdZhszcpAction extends CommonAction {

	PjpyZgkdZhszcpService service = PjpyZgkdZhszcpService.getInstance();

	private String xydm = "";

	private String zydm = "";

	private String nj = "";

	/**
	 * �ۺ����ʲ�����ҳ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZgkdZhszcpActionForm zgkdForm = (PjpyZgkdZhszcpActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? "" : session.getAttribute("userType")
				.toString();
		String userDep =  session.getAttribute("userDep") == null ? "" : session.getAttribute("userDep")
				.toString();
		String isFdy = session.getAttribute("isFdy") == null ? "" : session
				.getAttribute("isFdy").toString();
		String userName = session.getAttribute("userName") == null ? ""
				: session.getAttribute("userName").toString();
		if ("stu".equalsIgnoreCase(userType) || "student".equalsIgnoreCase(userType)) {
			return new ActionForward("/pjpy_zgkd_zhszcpinfo.do", false);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			zgkdForm.setXydm(xydm);
		} else {
			xydm = zgkdForm.getXydm();
		}
		if (StringUtils.isNull(zgkdForm.getXn())) {
			//zgkdForm.setXn(Base.currXn);
		}
		zydm = zgkdForm.getZydm();
		nj = zgkdForm.getNj();
		String act = request.getParameter("act");//������־
		HashMap<String, String> pmMap = service.queryZhszcpPmfs();
		String pm = "���ʷ�������ʽ��δ���ã�";
		if (!StringUtils.isNull(pmMap.get("pmfs")) && "zy".equalsIgnoreCase(pmMap.get("pmfs"))) {
			pm = "���ʷ�������ʽ��רҵΪ��λ";
		} else if (!StringUtils.isNull(pmMap.get("pmfs")) && "bj".equalsIgnoreCase(pmMap.get("pmfs"))) {
			pm = "���ʷ�������ʽ�԰༶Ϊ��λ";
		}
		zgkdForm.setPmfs(pmMap.get("pmfs"));
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		if ("go".equalsIgnoreCase(act)) {
			ZhszcpModel model = new ZhszcpModel();
			BeanUtils.copyProperties(model, zgkdForm);
			model.setJbszcpdj(zgkdForm.getSzdj());
			topTr = service.szfTitle();//��ѯ�б�
			int count = service.szfListNum(model, isFdy, userName);
			rs = service.szfList(model, isFdy, userName,zgkdForm);//��ѯ���
			zgkdForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//�������ļ�¼��
		} else if ("del".equalsIgnoreCase(act)) {
			String sFlag = service.szfDelete(zgkdForm.getCbv(), request);//ɾ������
			if (StringUtils.isNull(sFlag)) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
				request.setAttribute("failinfo", "������ɣ����е�" + sFlag + "�����ݲ���ʧ�ܣ�");
			}
		}
		List<HashMap<String, String>> szdjList = service.getSzdjList();//���ʵȼ��б�
		appendProperties(request, xydm, zydm, nj);
		if ("true".equalsIgnoreCase(isFdy)) {//����û��Ǹ���Ա��ֻ��ʾ�����ڰ༶��Ϣ
			appendFdybjList(request);
		}
		
		
		request.setAttribute("pm", pm);
		request.setAttribute("szdjList", szdjList);
		appendTableXx(request, "view_zgkd_zhszcp", "zgkd_zhszcpb");
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		request.setAttribute("topTr", topTr);
		zgkdForm.setXm(DealString.toGBK(zgkdForm.getXm()));
		return mapping.findForward("zhszcpdefault");
	}
	
	/**
	 * ���ʷ�����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szcpAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZgkdZhszcpActionForm zgkdForm = (PjpyZgkdZhszcpActionForm) form;
		String xh = request.getParameter("xh");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
			if (rs == null) {
				rs.put("stuExists", "no");
			}
		}
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			ZhszcpModel model = new ZhszcpModel();
			BeanUtils.copyProperties(model, zgkdForm);
			boolean bFlag = service.szfSave(model, request);
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		List<HashMap<String, String>> szdjList = service.getSzdjList();//���ʵȼ��б�
		request.setAttribute("szdjList", szdjList);
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("szcpadd");
	}
	
	/**
	 * �ۺ����ʲ���������ʾ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		PjpyZgkdZhszcpActionForm zgkdForm = (PjpyZgkdZhszcpActionForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			rs = service.szfView(pkValue);
			
		}
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			ZhszcpModel model = new ZhszcpModel();
			BeanUtils.copyProperties(model, zgkdForm);
			boolean bFlag = service.szfUpdate(model, pkValue, request);
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		List<HashMap<String, String>> szdjList = service.getSzdjList();//���ʵȼ��б�
		if (rs != null) {
			zgkdForm.setPdcpdf(rs.get("pdcpdf"));
			zgkdForm.setKcxxcjdf(rs.get("kcxxcjdf"));
			zgkdForm.setSxcpdf(rs.get("sxcpdf"));
			zgkdForm.setJbszcpdf(rs.get("jbszcpdf"));
			zgkdForm.setJbszcpdj(rs.get("jbszcpdj"));
			zgkdForm.setFzszcpdf(rs.get("fzszcpdf"));
			zgkdForm.setXn(rs.get("xn"));
		}
		if ("view".equalsIgnoreCase(act)) {
			request.setAttribute("act", "view");
		}
		request.setAttribute("szdjList", szdjList);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("szcpview");
	}
	
	/**
	 * �ۺ����ʲ���ͳ�ƻ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpHz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		xydm = request.getParameter("xydm");
//		zydm = request.getParameter("zydm");
//		nj = request.getParameter("nj");
		String xn = request.getParameter("xn");
//		String bjdm = request.getParameter("bjdm");
		ZhszcpModel model = new ZhszcpModel();
		model.setXydm(xydm);
		model.setXn(xn);
		//ArrayList<ArrayList<String[]>> rs = service.szcpHz(model);
		//request.setAttribute("rs", rs);
		//return mapping.findForward("szcphz");
		String modelPath = "";
		modelPath = servlet.getServletContext().getRealPath("")+"/print/zgkd_zhszcphz.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.szcpHz(model, wwb);
		return mapping.findForward("");
	}
	
	/**
	 * ѧ���鿴������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		String xh = session.getAttribute("userName") == null ? "" : session
				.getAttribute("userName").toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		if (!StringUtils.isNull(xh)) {
			rs = service.szcpInfo(xh);
		}
		
		request.setAttribute("rs", rs);
		return mapping.findForward("szcpinfo");
	}

	/**
	 * ������ʽ���ñ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pmfsSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZgkdZhszcpActionForm zgkdForm = (PjpyZgkdZhszcpActionForm) form;
		String act = request.getParameter("act");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(act) && "save".equalsIgnoreCase(act)) {
			boolean bFlag = service.saveZhszcpPmfs(request.getParameter("pmfs"), request);
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		} else {
			rs = service.queryZhszcpPmfs();
		}
		zgkdForm.setPmfs(rs.get("pmfs"));
		return mapping.findForward("pmszpage");
	}
}
