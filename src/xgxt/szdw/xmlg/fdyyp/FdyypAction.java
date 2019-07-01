package xgxt.szdw.xmlg.fdyyp;

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
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.szdw.xmlg.XmlgSzdwForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

public class FdyypAction extends DispatchAction {

	/**
	 * �Զ����ֶι���
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward csszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		FdyypService service = new FdyypService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		FdyypModel model = new FdyypModel();

		String tableName = "view_xmlg_fdyyp_hdbzd";
		String realTable = "xmlg_fdyyp_hdbzd";
		String title = "˼������ - ����Ա���� - �걨��������";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("del")) {

			String pk = DealString.toGBK(request.getParameter("pk"));
			result = service.delCssz(pk, request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xn", "xqmc", "zd", "zdm",
					"lxmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getCsszList(tableName, model, colList);
		}
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "xmlg_fdyyp_sz.do");

		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);

		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("csszManage");
	}

	/**
	 * �Զ����ֶ�ά��
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward csszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdyypService service = new FdyypService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		FdyypModel model = new FdyypModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		String title = "˼������ - ����Ա���� - �걨��������";
		String doType = request.getParameter("doType");
		String tableName = "view_xmlg_fdyyp_hdbzd";
		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType)
				&& ("edit".equalsIgnoreCase(doType) || "view"
						.equalsIgnoreCase(doType))) {
			String[] colList = new String[] { "xn", "xq", "xqmc", "zd", "zdm",
					"zdlx" };
			rs = service.getCssz(tableName, colList, "xn||xq||zd", pk);
		}
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			result = service.saveCssz(model, request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		if (myForm.getXn() == null) {
			rs.put("xn", Base.currXn);
		}
		if (myForm.getXq() == null) {
			rs.put("xq", Base.currXq);
		}

		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		FormModleCommon.setNdXnXqList(request);

		return mapping.findForward("csszUpdate");
	}

	/**
	 * ����Ա�����걨
	 * @author luo 
	 * @throws Exception
	 */
	public ActionForward fdyypSb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		String tableName = "";
		String realTable = "";
		String title = "˼������ - ����Ա���� - �걨";
		String doType = request.getParameter("doType");
		String zgh = request.getParameter("zgh");
		
		FdyypService service = new FdyypService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		FdyypModel model = new FdyypModel();

		if ("stu".equalsIgnoreCase(userType)) {
			request.setAttribute("errMsg", "�˹��ܲ���Զ�ѧ�����ţ�");
			return new ActionForward("/errMsg.do", false);
		}
		if (!service.isXfgsj(userName) && 
				!"admin".equalsIgnoreCase(userType) && !"xx".equalsIgnoreCase(userType)) {
			request.setAttribute("errMsg", "ֻ����ϵ�ֹ����ν��в�������ȷ�ϣ�");
			return new ActionForward("/errMsg.do", false);
		}

		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			String[] zdyZd = new String[0];
			String[] zdyZdz = new String[0];

			List<HashMap<String, String>> list = service.getZdyZd(myForm
					.getXn(), myForm.getXq());

			if (list != null && list.size() > 0) {

				zdyZd = new String[list.size()];
				zdyZdz = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					HashMap<String, String> map = list.get(i);
					String zd = map.get("zd");
					String zdz = DealString.toGBK(request.getParameter(zd));
					zdyZd[i] = zd;
					zdyZdz[i] = zdz;
				}
			}

			myForm.setArrZd(zdyZd);
			myForm.setArrZdz(zdyZdz);
			// FormModleCommon.formToGBK(myForm);

			BeanUtils.copyProperties(model, myForm);

			boolean result = service.saveFdyypsb(model, zdyZd, zdyZdz, request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		HashMap<String, String> rs = new HashMap<String, String>();
		if (!Base.isNull(zgh)) {
			String[] colList = new String[] { "zgh", "xm", "xb", "bmmc", "zwmc" };
			rs = service.getFdyxx(colList, zgh);
		}
		rs.put("xn", Base.currXn);
		rs.put("xq", Base.currXq);
		rs.put("nd", Base.currNd);
		rs.put("sbr", service.getSbrxx(userName).get("yhm"));
		rs.put("sbrxm", service.getSbrxx(userName).get("xm"));
		rs.put("zgh", myForm.getZgh());
		rs.put("cpdf", myForm.getCpdf());
		rs.put("yf", myForm.getYf());
		rs.put("xyyj", DealString.toGBK(myForm.getXyyj()));
		
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "xmlg_fdyyp_sb.do");

		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);

		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.requestSetList(new String[]{"yf"}, request);

		return mapping.findForward("fdyypSb");
	}

	/**
	 * �걨�鿴����
	 * @author luo 
	 * @return ActionForward
	 */
	public ActionForward sbckManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		FdyypService service = new FdyypService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		FdyypModel model = new FdyypModel();

		String title = "˼������ - ����Ա���� - �鿴";
		String doType = request.getParameter("doType");
		String tableName = "view_xmlg_fdyypsh";
		String realTable = "xmlg_szdw_fdyypsh";
		String sbsj = myForm.getSbsj();
		String[] checkVal = myForm.getCheckVal();
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		String xydm = myForm.getXydm();
		xydm = Base.isNull(xydm) ? "%" : xydm;

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		if (!Base.isNull(sbsj) && sbsj.length() == 8) {
			sbsj = sbsj.substring(0, 4) + "-" + sbsj.substring(4, 6) + "-"
					+ sbsj.substring(6, 8);
			myForm.setSbsj(sbsj);
		}

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				result = service.delFdyyp(checkVal);
				request.setAttribute("result", result);
			}
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk","nd", "xn", "xqmc", "zgh", "xm",
					"bmmc", "zwmc", "sbrxm", "sbsj" ,"yf","cpdf"};

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getFdyypList(tableName, model, colList);
		}
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "xmlg_fdyyp_ck.do");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		FormModleCommon.requestSetList(new String[]{"bm","yf"}, request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("sbckManage");
	}

	/**
	 * ����Ա������ʦ�鿴
	 * 
	 * @return ActionForward
	 */
	public ActionForward fdyypCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		
		String title = "˼������ - ����Ա���� - �鿴";
		String pk = request.getParameter("pk");
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userType").toString();
		String qx = "no";

		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		FdyypService service = new FdyypService();
		FdyypModel model = new FdyypModel();

		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			String[] zdyZd = new String[0];
			String[] zdyZdz = new String[0];

			List<HashMap<String, String>> list = service.getZdyZd(myForm
					.getXn(), myForm.getXq());

			if (list != null && list.size() > 0) {

				zdyZd = new String[list.size()];
				zdyZdz = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					HashMap<String, String> map = list.get(i);
					String zd = map.get("zd");
					String zdz = DealString.toGBK(request.getParameter(zd));
					zdyZd[i] = zd;
					zdyZdz[i] = zdz;
				}
			}

			myForm.setArrZd(zdyZd);
			myForm.setArrZdz(zdyZdz);
			// FormModleCommon.formToGBK(myForm);

			BeanUtils.copyProperties(model, myForm);

			boolean result = service.saveFdyypsb(model, zdyZd, zdyZdz, request);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		if (!Base.isNull(pk)) {
			String[] pkValue = pk.split("!!@@!!");
			if (pkValue != null && pkValue.length > 3) {
				String xn = pkValue[0];
				String xq = pkValue[1];
				String yf = pkValue[2];
				String zgh = pkValue[3];
				String key = xn+xq+yf+zgh;

				HashMap<String, String> map = service.getFdyypXx(key);
				String sbr = map.get("sbr");
				
				String[] colList = new String[] { "zgh", "xm", "xb", "bmmc", "zwmc" };
				HashMap<String, String> rs = service.getFdyxx(colList, zgh);
				rs.put("xn", xn);
				rs.put("xq", xq);
				rs.put("yf", yf);
				rs.put("sbr", service.getSbrxx(sbr).get("yhm"));
				rs.put("sbrxm", service.getSbrxx(sbr).get("xm"));
				rs.put("cpdf", map.get("cpdf"));
				rs.put("xyyj", map.get("xyyj"));
				rs.put("nd", map.get("nd"));
				
				request.setAttribute("rs", rs);
			}
		}

		if("admin".equalsIgnoreCase(userType)||"xx".equalsIgnoreCase(userType)){
			qx = "yes";
		}
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.requestSetList(new String[]{"bm","yf"}, request);
		
		request.setAttribute("title", title);
		request.setAttribute("qx", qx);
		request.setAttribute("doType", doType);
		request.setAttribute("pk", pk);

		return mapping.findForward("fdyypCk");
	}

	/**
	 * ����Ա��ѯ
	 * @author luo
	 * @return ActionForward
	 */
	public ActionForward fdyxxSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		String title = "˼������ - ����Ա���� - ����Ա���Ų�ѯ";
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		String url = request.getParameter("url");
		
		FdyypService service = new FdyypService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		FdyypModel model = new FdyypModel();
		
		String tableName = "view_fdyxx";
		String realTable = "";
		
		if (service.isXfgsj(userName)){
			userType = "xfgsj";
			myForm.setBmdm(userDep);
		}
			
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		// �����ѯ��ť���в�ѯ
		
		BeanUtils.copyProperties(model, myForm);
		
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			String[] colList = new String[] { "zgh", "xm", "xb", "bmmc", "zwmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getFdyList(tableName, model, colList);
			myForm.setXb(DealString.toGBK(myForm.getXb()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		request.setAttribute("title", title);
		request.setAttribute("url", url);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.requestSetList(new String[]{"bm"}, request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("fdyxxSearch");
	}
	
	/**
	 * ����Ա��������
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward fdyypHz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdyypService service = new FdyypService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		FdyypModel model = new FdyypModel();

		BeanUtils.copyProperties(model, myForm);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.fdyypHz(model, response.getOutputStream());

		return null;

	}

	/**
	 * ����Ա�ش��ʧ����
	 * @author luo
	 * @return ActionForward
	 */
	public ActionForward gsjlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		FdyypService service = new FdyypService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		FdyypModel model = new FdyypModel();

		String doType = request.getParameter("doType");
		String tableName = "view_xmlg_fdygsjl";
		String realTable = "xmlg_szdw_fdygsjl";
		String sbsj = myForm.getSbsj();
		String[] checkVal = myForm.getCheckVal();
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		String xydm = myForm.getXydm();
		xydm = Base.isNull(xydm) ? "%" : xydm;

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		if (!Base.isNull(sbsj) && sbsj.length() == 8) {
			sbsj = sbsj.substring(0, 4) + "-" + sbsj.substring(4, 6) + "-"
					+ sbsj.substring(6, 8);
			myForm.setSbsj(sbsj);
		}

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				result = service.delFdyyp(checkVal);
				request.setAttribute("result", result);
			}
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xn", "xqmc", "zgh", "xm",
					"bmmc", "zwmc", "gssj"};

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getFdyGsList(tableName, model, colList);
		}
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "xmlg_fdyyp_gsjl.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		FormModleCommon.requestSetList(new String[]{"bm"}, request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("gsjlManage");
	}

	/**
	 * ����Ա�ش��ʧά��
	 * @author luo
	 * @return ActionForward
	 */
	public ActionForward gsjlUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "˼������ - ��Ϣά�� - ����Ա�ش��ʧ��¼";
		String userName = request.getSession().getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String zgh = request.getParameter("zgh");
		String pk = request.getParameter("pk");
		String url = request.getParameter("url");
		
		FdyypService service = new FdyypService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		FdyypModel model = new FdyypModel();
		//HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);
		HashMap<String, String> rs = new HashMap<String, String>();
		
		if ("update".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			rs = service.getFdyGsInfo(pk);
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveFdyGs(model, userName, request);
			request.setAttribute("result", result);
		}


		String[] colList = new String[] { "zgh", "xm", "xb", "bmmc", "zwmc" };
		
		if (!Base.isNull(zgh)) {
			rs = service.getFdyxx(colList, zgh);
		}
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("url", url);
		request.setAttribute("rs", rs);

		return mapping.findForward("gsjlUpdate");
	}
}
