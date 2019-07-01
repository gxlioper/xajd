package xgxt.szdw.xmlg.bgtj;

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
import xgxt.szdw.xmlg.XmlgSzdwForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

public class BgtjAction extends DispatchAction {

	/**
	 * ��ȫ�������
	 * 
	 * @throws Exception
	 */
	public ActionForward aqbgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

//		BgtjService service = new BgtjService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		BgtjModel model = new BgtjModel();

		String tableName = "view_xmlg_wmbj_hdbzd";
		String realTable = "xmlg_szdw_aqbgb";
		String title = "˼������ - �����༶ - �걨��������";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("del")) {

//			String pk = DealString.toGBK(request.getParameter("pk"));
			//result = service.delCssz(pk, request);

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
			//rs = service.getCsszList(tableName, model, colList);
		}
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "xmlg_wmbj_sz.do");

		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);

		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("aqbgManage");
	}

	/**
	 * ��ȫ����ά��
	 * 
	 * @throws Exception
	 */
	public ActionForward aqbgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

//		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
//		String userDep = (String) session.getAttribute("userDep");
		
		BgtjService service = new BgtjService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		BgtjModel model = new BgtjModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		String title = "˼������ -  ��ȫ����";
		String doType = request.getParameter("doType");
		String tableName = "xmlg_szdw_aqbgb";
//		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);
		
		if (!Base.isNull(doType)
				&& ("edit".equalsIgnoreCase(doType) || "view"
						.equalsIgnoreCase(doType))) {
//			String[] colList = new String[] { "xn", "xq", "xqmc", "zd", "zdm",
//					"zdlx" };
			//rs = service.getCssz(tableName, colList, "xn||xq||zd", pk);
		}
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] colList = new String[] { "tjr", "bgdm", "bgnr", "ydbf", "bz"};
			model.setTjr(userName);
			result = service.saveBg(tableName, model, colList, request);

			if (result) {
				doType = "";
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		
		if (Base.isNull(doType)) {
			String[] colList = new String[] { "zgh", "xm", "xb", "bmmc",
					"zwmc", "lxdh" };
			rs = service.getFdyxx(colList, userName);
		}
		
		request.setAttribute("lxList", service.getLxList("xmlg_szdw_aqbglx"));
		request.setAttribute("mcList", service.getMcList("xmlg_szdw_aqbgmc",null));
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		FormModleCommon.setNdXnXqList(request);

		return mapping.findForward("aqbgUpdate");
	}

	/**
	 * �ƻ����ܽ��ύά��
	 * 
	 * @throws Exception
	 */
	public ActionForward jhzjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

//		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
//		String userDep = (String) session.getAttribute("userDep");
		
		BgtjService service = new BgtjService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		BgtjModel model = new BgtjModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		String title = "˼������ -  �ƻ����ܽ�";
		String doType = request.getParameter("doType");
		String tableName = "xmlg_szdw_jhzjb";
//		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);
		
		if (!Base.isNull(doType)
				&& ("edit".equalsIgnoreCase(doType) || "view"
						.equalsIgnoreCase(doType))) {
//			String[] colList = new String[] { "xn", "xq", "xqmc", "zd", "zdm",
//					"zdlx" };
			//rs = service.getCssz(tableName, colList, "xn||xq||zd", pk);
		}
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] colList = new String[] { "tjr", "lx","xmmc", "bgnr", "bz"};
			model.setTjr(userName);
			result = service.saveBg(tableName, model, colList, request);

			if (result) {
				doType = "";
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		
		if (Base.isNull(doType)) {
			String[] colList = new String[] { "zgh", "xm", "xb", "bmmc",
					"zwmc", "lxdh" };
			rs = service.getFdyxx(colList, userName);
		}
		
		request.setAttribute("lxList", service.getLxList("xmlg_szdw_jhzjlx"));
		//request.setAttribute("mcList", service.getMcList("xmlg_szdw_aqbgmc",null));
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		FormModleCommon.setNdXnXqList(request);

		return mapping.findForward("jhzjUpdate");
	}
	
	/**
	 * ���������ύά��
	 * 
	 * @throws Exception
	 */
	public ActionForward gzalUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

//		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
//		String userDep = (String) session.getAttribute("userDep");
		
		BgtjService service = new BgtjService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		BgtjModel model = new BgtjModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		String title = "˼������ -  ��������";
		String doType = request.getParameter("doType");
		String tableName = "xmlg_szdw_gzalb";
//		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);
		
		if (!Base.isNull(doType)
				&& ("edit".equalsIgnoreCase(doType) || "view"
						.equalsIgnoreCase(doType))) {
//			String[] colList = new String[] { "xn", "xq", "xqmc", "zd", "zdm",
//					"zdlx" };
			//rs = service.getCssz(tableName, colList, "xn||xq||zd", pk);
		}
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] colList = new String[] { "tjr", "alfl", "alcs", "lx", "bz"};
			model.setTjr(userName);
			result = service.saveBg(tableName, model, colList, request);

			if (result) {
				doType = "";
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		
		if (Base.isNull(doType)) {
			String[] colList = new String[] { "zgh", "xm", "xb", "bmmc",
					"zwmc", "lxdh" };
			rs = service.getFdyxx(colList, userName);
		}
		
		request.setAttribute("lxList", service.getLxList("xmlg_szdw_aqbglx"));
		request.setAttribute("mcList", service.getMcList("xmlg_szdw_aqbgmc",null));
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		FormModleCommon.setNdXnXqList(request);

		return mapping.findForward("gzalUpdate");
	}
	
	/**
	 * ���������ύά��
	 * 
	 * @throws Exception
	 */
	public ActionForward gzjyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

//		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
//		String userDep = (String) session.getAttribute("userDep");
		
		BgtjService service = new BgtjService();
		XmlgSzdwForm myForm = (XmlgSzdwForm) form;
		BgtjModel model = new BgtjModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		String title = "˼������ -  ��������";
		String doType = request.getParameter("doType");
		String tableName = "xmlg_szdw_aqbgb";
//		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);
		
		if (!Base.isNull(doType)
				&& ("edit".equalsIgnoreCase(doType) || "view"
						.equalsIgnoreCase(doType))) {
//			String[] colList = new String[] { "xn", "xq", "xqmc", "zd", "zdm",
//					"zdlx" };
			//rs = service.getCssz(tableName, colList, "xn||xq||zd", pk);
		}
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] colList = new String[] { "tjr", "bgdm", "bgnr", "ydbf", "bz"};
			model.setTjr(userName);
			result = service.saveBg(tableName, model, colList, request);

			if (result) {
				doType = "";
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		
		if (Base.isNull(doType)) {
			String[] colList = new String[] { "zgh", "xm", "xb", "bmmc",
					"zwmc", "lxdh" };
			rs = service.getFdyxx(colList, userName);
		}
		
		request.setAttribute("lxList", service.getLxList("xmlg_szdw_aqbglx"));
		request.setAttribute("mcList", service.getMcList("xmlg_szdw_aqbgmc",null));
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		FormModleCommon.setNdXnXqList(request);

		return mapping.findForward("gzjyUpdate");
	}
}
