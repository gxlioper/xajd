
package xgxt.pjpy.zzdx;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.GlobalsVariable;

import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ݴ�ѧ��������Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-23</p>
 */
public class PjpyZzdxAction extends CommonAction {

	private String xydm = "";
	private String zydm = "";
	private String nj = "";
	/**
	 * ֤���ӡ����,����ҳ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzdxzsPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZzdxService service = new PjpyZzdxService();
		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String hjxn = request.getParameter("hjxn");
		String hjxjmc = DealString.toGBK(request.getParameter("hjxjmc"));
		String hjny = DealString.toGBK(request.getParameter("hjny"));
		String hjqsxn = "0000";//����ʼѧ��
		String hjjsxn = "0000";//�񽱽���ѧ��
		String fzxn = "0000";//��֤��
		String fzy = "00";//��֤��
		String fzr = "00";//��֤��
		if (!StringUtils.isNull(hjxn) && hjxn.indexOf("-") < 0 && hjxn.length()==8) {
			 hjqsxn = hjxn.substring(0, 4);
			 hjjsxn = hjxn.substring(4, 8);
			 /*hjqsxn = !StringUtils.isNull(hjqsxn) ? hjqsxn.substring(2, 4) : "";
			 hjjsxn = !StringUtils.isNull(hjjsxn) ? hjjsxn.substring(2, 4) : "";*/
		}
		if (!StringUtils.isNull(hjxn) && hjxn.length()==9) {
			hjqsxn = hjxn.substring(0, 4);
			hjjsxn = hjxn.substring(5, 9);
		}
		if (!StringUtils.isNull(hjny) && hjny.indexOf("-") < 0 && hjny.length()==8) {
			 fzxn = hjny.substring(0, 4);
			 fzy = hjny.substring(4, 6);
			 fzr = hjny.substring(6, 8);
		}
		if (!StringUtils.isNull(hjny) && hjny.length()==10) {
			fzxn = hjny.substring(0, 4);
			fzy = hjny.substring(5, 7);
			fzr = hjny.substring(8, 10);
		}
		String[] topleftList = service.getTopLeftStr("zzdxzs");//��ȡ����
		String topstr = "";
		String leftstr = "";
		if (topleftList != null && topleftList.length == 2) {
			topstr = topleftList[0];//TOPλ��
			leftstr = topleftList[1];//LEFTλ��
		}
		request.setAttribute("xm", xm);
		request.setAttribute("hjqsxn", hjqsxn);
		request.setAttribute("hjjsxn", hjjsxn);
		request.setAttribute("fzxn", fzxn);
		request.setAttribute("fzy", fzy);
		request.setAttribute("fzr", fzr);
		request.setAttribute("xh", xh);
		request.setAttribute("hjxjmc", hjxjmc);
		request.setAttribute("topstr", topstr);
		request.setAttribute("leftstr", leftstr);
		request.setAttribute("zhi", "-");
		return mapping.findForward("zsprintone");
	}
	
	/**
	 * ֤���ӡ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzdxzsPrintMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZzdxService service = new PjpyZzdxService();
		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String hjxn = request.getParameter("hjxn");
		String hjxjmc = DealString.toGBK(request.getParameter("hjxjmc"));
		String hjny = DealString.toGBK(request.getParameter("hjny"));
		String hjqsxn = "0000";//����ʼѧ��
		String hjjsxn = "0000";//�񽱽���ѧ��
		String fzxn = "0000";//��֤��
		String fzy = "00";//��֤��
		String fzr = "00";//��֤��
		if (!StringUtils.isNull(hjxn) && hjxn.indexOf("-") < 0 && hjxn.length()==8) {
			 hjqsxn = hjxn.substring(0, 4);
			 hjjsxn = hjxn.substring(4, 8);
			 /*hjqsxn = !StringUtils.isNull(hjqsxn) ? hjqsxn.substring(2, 4) : "";
			 hjjsxn = !StringUtils.isNull(hjjsxn) ? hjjsxn.substring(2, 4) : "";*/
		}
		if (!StringUtils.isNull(hjxn) && hjxn.length()==9) {
			hjqsxn = hjxn.substring(0, 4);
			hjjsxn = hjxn.substring(5, 9);
		}
		if (!StringUtils.isNull(hjny) && hjny.indexOf("-") < 0 && hjny.length()==8) {
			 fzxn = hjny.substring(0, 4);
			 fzy = hjny.substring(4, 6);
			 fzr = hjny.substring(6, 8);
		}
		if (!StringUtils.isNull(hjny) && hjny.length()==10) {
			fzxn = hjny.substring(0, 4);
			fzy = hjny.substring(5, 7);
			fzr = hjny.substring(8, 10);
		}
		String[] topleftList = service.getTopLeftStr("zzdxzs");//��ȡ����
		String topstr = "";
		String leftstr = "";
		if (topleftList != null && topleftList.length == 2) {
			topstr = topleftList[0];//TOPλ��
			leftstr = topleftList[1];//LEFTλ��
		}
		request.setAttribute("xm", xm);
		request.setAttribute("hjxn", hjxn);
		request.setAttribute("hjny", hjny);
		request.setAttribute("hjqsxn", hjqsxn);
		request.setAttribute("hjjsxn", hjjsxn);
		request.setAttribute("fzxn", fzxn);
		request.setAttribute("fzy", fzy);
		request.setAttribute("fzr", fzr);
		request.setAttribute("xh", xh);
		request.setAttribute("hjxjmc", hjxjmc);
		request.setAttribute("topstr", topstr);
		request.setAttribute("leftstr", leftstr);
		request.setAttribute("zhi", "-");
		return mapping.findForward("zsprintmore");
	}
	
	/**
	 * �����ƺ�֤���ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychzsPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZzdxService service = new PjpyZzdxService();
		PjpyZzdxActionForm zzdxForm = (PjpyZzdxActionForm) form;
		String go = request.getParameter("go");
		List<HashMap<String, String>> titList = new ArrayList<HashMap<String,String>>();
		List<String[]> rsList = new ArrayList<String[]>();
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		//ѧ���û���Ȩ�����ҳ��
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(userType)
				|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(userType)) {
			request.setAttribute("message", "����Ȩ���ʸ�ҳ��!");
			return new ActionForward("/prompt.do",false);
		}
//		HttpSession session = request.getSession();
//		String userType = session.getAttribute("userType") == null ? ""
//				: session.getAttribute("userType").toString(); 
		if (!StringUtils.isNull(go) && "a".equalsIgnoreCase(go)) {
			titList = service.title();
			PjpyZzdxModel model = new PjpyZzdxModel();
			BeanUtils.copyProperties(model, zzdxForm);
//			if (StringUtils.isNull(model.getXn())) {
//				model.setXn(Base.getJxjsqxn());
//			}
//			if (StringUtils.isNull(model.getXq())) {
//				model.setXq(Base.getJxjsqxq());
//			}
			rsList = service.result(model);
		}
		appendRychList(request);
		appendQryResult(request, titList, rsList);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("rychprintpage");
	}
	
	public ActionForward rychzsprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZzdxService service = new PjpyZzdxService();
		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String hjxn = request.getParameter("hjxn");
		String hjxjmc = DealString.toGBK(request.getParameter("hjxjdj"));
		String hjny = DealString.toGBK(request.getParameter("hjny"));
		String hjqsxn = "0000";//����ʼѧ��
		String hjjsxn = "0000";//�񽱽���ѧ��
		String fzxn = "0000";//��֤��
		String fzy = "00";//��֤��
//		String fzr = "00";//��֤��
		if (!StringUtils.isNull(hjxn) && hjxn.indexOf("-") < 0 && hjxn.length()==8) {
			 hjqsxn = hjxn.substring(0, 4);
			 hjjsxn = hjxn.substring(4, 8);
			 /*hjqsxn = !StringUtils.isNull(hjqsxn) ? hjqsxn.substring(2, 4) : "";
			 hjjsxn = !StringUtils.isNull(hjjsxn) ? hjjsxn.substring(2, 4) : "";*/
		}
		if (!StringUtils.isNull(hjxn) && hjxn.length()==9) {
			hjqsxn = hjxn.substring(0, 4);
			hjjsxn = hjxn.substring(5, 9);
		}
		if (!StringUtils.isNull(hjny) && hjny.indexOf("-") < 0 && hjny.length()==6) {
			 fzxn = hjny.substring(0, 4);
			 fzy = hjny.substring(4, 6);
			 //fzr = hjny.substring(6, 8);
		}
		if (!StringUtils.isNull(hjny) && hjny.length()==7) {
			fzxn = hjny.substring(0, 4);
			fzy = hjny.substring(5, 7);
			//fzr = hjny.substring(8, 10);
		}
		String[] topleftList = service.getTopLeftStr("rych");//��ȡ����
		String topstr = "";
		String leftstr = "";
		if (topleftList != null && topleftList.length == 2) {
			topstr = topleftList[0];//TOPλ��
			leftstr = topleftList[1];//LEFTλ��
		}
		request.setAttribute("xm", xm);
		request.setAttribute("hjqsxn", hjqsxn);
		request.setAttribute("hjjsxn", hjjsxn);
		request.setAttribute("fzxn", fzxn);
		request.setAttribute("fzy", fzy);
		//request.setAttribute("fzr", fzr);
		request.setAttribute("xh", xh);
		request.setAttribute("hjxjmc", hjxjmc);
		request.setAttribute("topstr", topstr);
		request.setAttribute("leftstr", leftstr);
		request.setAttribute("zhi", "-");
		return mapping.findForward("rychprintone");
	}
	
	public ActionForward rychPrintmore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZzdxService service = new PjpyZzdxService();
		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String hjxn = request.getParameter("hjxn");
		String hjxjmc = DealString.toGBK(request.getParameter("hjxjmc"));
		String hjny = DealString.toGBK(request.getParameter("hjny"));
		String hjqsxn = "0000";//����ʼѧ��
		String hjjsxn = "0000";//�񽱽���ѧ��
		String fzxn = "0000";//��֤��
		String fzy = "00";//��֤��
		//String fzr = "00";//��֤��
		if (!StringUtils.isNull(hjxn) && hjxn.indexOf("-") < 0 && hjxn.length()==8) {
			 hjqsxn = hjxn.substring(0, 4);
			 hjjsxn = hjxn.substring(4, 8);
			 /*hjqsxn = !StringUtils.isNull(hjqsxn) ? hjqsxn.substring(2, 4) : "";
			 hjjsxn = !StringUtils.isNull(hjjsxn) ? hjjsxn.substring(2, 4) : "";*/
		}
		if (!StringUtils.isNull(hjxn) && hjxn.length()==9) {
			hjqsxn = hjxn.substring(0, 4);
			hjjsxn = hjxn.substring(5, 9);
		}
		if (!StringUtils.isNull(hjny) && hjny.indexOf("-") < 0 && hjny.length()==6) {
			 fzxn = hjny.substring(0, 4);
			 fzy = hjny.substring(4, 6);
			// fzr = hjny.substring(6, 8);
		}
		if (!StringUtils.isNull(hjny) && hjny.length()==7) {
			fzxn = hjny.substring(0, 4);
			fzy = hjny.substring(5, 7);
			//fzr = hjny.substring(8, 10);
		}
		String[] topleftList = service.getTopLeftStr("rych");//��ȡ����
		String topstr = "";
		String leftstr = "";
		if (topleftList != null && topleftList.length == 2) {
			topstr = topleftList[0];//TOPλ��
			leftstr = topleftList[1];//LEFTλ��
		}
		request.setAttribute("xm", xm);
		request.setAttribute("hjxn", hjxn);
		request.setAttribute("hjny", hjny);
		request.setAttribute("hjqsxn", hjqsxn);
		request.setAttribute("hjjsxn", hjjsxn);
		request.setAttribute("fzxn", fzxn);
		request.setAttribute("fzy", fzy);
		//request.setAttribute("fzr", fzr);
		request.setAttribute("xh", xh);
		request.setAttribute("hjxjmc", hjxjmc);
		request.setAttribute("topstr", topstr);
		request.setAttribute("leftstr", leftstr);
		request.setAttribute("zhi", "-");
		return mapping.findForward("rychprintmore");
	}
}
