package xgxt.pjpy.ycwsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyYcwsxyAction extends CommonAction {

	private PjpyYcwsService service = PjpyYcwsService.getInstance();

	private String xydm = "";
	
	private String zydm = "";
	
	private String nj = "";
	/**
	 * ��ѵ����Ϣά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxhjWh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO �Զ����ɷ������
		PjpyYcwsActionForm ycwsForm = (PjpyYcwsActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ycwsForm.setXydm(xydm);
		} else {
			xydm = ycwsForm.getXydm();
		}
		nj = ycwsForm.getNj();
		zydm = ycwsForm.getZydm();
		String act = request.getParameter("act");//���ݲ�����־
		List<HashMap<String, String>> titList = new ArrayList<HashMap<String,String>>();
		List<String[]> rsList = new ArrayList<String[]>();
		if ("qry".equalsIgnoreCase(act)) {
			PjpyYcwsModel model = new PjpyYcwsModel();
			BeanUtils.copyProperties(model, ycwsForm);
			titList = service.queryBjjxhjTitle();
			rsList = service.getBjjxhjList(model, ycwsForm);
			int count = service.getBjjxhjListNum(model);
			ycwsForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//�������ļ�¼��
		}
		if ("del".equalsIgnoreCase(act)) {
			String flag = service.deleteBjjxhjxx(ycwsForm.getCbv());
			if (StringUtils.isNull(flag)) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
				request.setAttribute("failinfo", "�������,���е�"+ flag + "������ɾ��ʧ��!");
			}
		}
		appendProperties(request, xydm, zydm, nj);// ��ҳ�����ѧ�꣬��ȣ��꼶��ѧԺ,רҵ,�༶�б�
		appendQryResult(request, titList, rsList);
		appendTableXx(request, "view_pjpy_bjjxhj", "pjpy_bjjxhjb");
		ycwsForm.setXm(DealString.toGBK(ycwsForm.getXm()));
		appendJxjxdmList(request);
		return mapping.findForward("jxhjpage");
	}
	
	public ActionForward addBjjxjx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyYcwsActionForm ycwsForm = (PjpyYcwsActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ycwsForm.setXydm(xydm);
		} else {
			xydm = ycwsForm.getXydm();
		}
		nj = ycwsForm.getNj();
		zydm = ycwsForm.getZydm();
		String act = request.getParameter("act");//���ݲ�����־
		if ("save".equalsIgnoreCase(act)) {
			PjpyYcwsModel model = new PjpyYcwsModel();
			BeanUtils.copyProperties(model, ycwsForm);
			boolean flag = service.addBjjxhjxx(model, request);
			if (flag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		ycwsForm.setBz(DealString.toGBK(ycwsForm.getBz()));
		ycwsForm.setHjmx(DealString.toGBK(ycwsForm.getHjmx()));
		appendProperties(request, xydm, zydm, nj);// ��ҳ�����ѧ�꣬��ȣ��꼶��ѧԺ,רҵ,�༶�б�
		appendJxjxdmList(request);
		return mapping.findForward("addjxhjpage");
	}
	
	public ActionForward modiBjjxjx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");//���ݲ�����־
		PjpyYcwsActionForm ycwsForm = (PjpyYcwsActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ycwsForm.setXydm(xydm);
		} else {
			xydm = ycwsForm.getXydm();
		}
		nj = ycwsForm.getNj();
		zydm = ycwsForm.getZydm();
		HashMap<String, String> rs = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(act)) {
			PjpyYcwsModel model = new PjpyYcwsModel();
			BeanUtils.copyProperties(model, ycwsForm);
			boolean flag = service.updateBjjxhjxx(pkValue, model, request);
			if (flag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
			ycwsForm.setBz(DealString.toGBK(ycwsForm.getBz()));
			ycwsForm.setHjmx(DealString.toGBK(ycwsForm.getHjmx()));
		}
		if ("view".equalsIgnoreCase(act)) {
			request.setAttribute("act", "view");
		}
		if (!StringUtils.isNull(pkValue)) {
			rs = service.viewBjjxhjxx(pkValue);
			ycwsForm.setZydm(rs.get("zydm"));
			ycwsForm.setBjdm(rs.get("bjdm"));
			ycwsForm.setXn(rs.get("xn"));
			ycwsForm.setHjmx(rs.get("hjmx"));
			ycwsForm.setBz(rs.get("bz"));
			ycwsForm.setJxhjxm(rs.get("jxhjxm"));
			ycwsForm.setNj(rs.get("nj"));
			ycwsForm.setXydm(rs.get("xydm"));
		}
		
		
		appendProperties(request, xydm, zydm, nj);// ��ҳ�����ѧ�꣬��ȣ��꼶��ѧԺ,רҵ,�༶�б�
		appendJxjxdmList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("modijxhjpage");
	}
}
