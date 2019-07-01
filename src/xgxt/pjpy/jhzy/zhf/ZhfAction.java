package xgxt.pjpy.jhzy.zhf;

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
import xgxt.pjpy.jhzy.JhzyPjpyForm;
import xgxt.pjpy.jhzy.JhzyPjpyUnit;

public class ZhfAction extends DispatchAction {

	/**
	 * �ۺϷֹ���
	 * 
	 * @throws Exception
	 */
	public ActionForward zhfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		ZhfService service = new ZhfService();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		ZhfModel model = new ZhfModel();

		String tableName = "view_jhzy_zhf";
		String realTable = "jhzy_zhf";
		String title = "�������� - ��Ϣά�� - �ۺ����ʷ�ά��";

		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("xydm", userDep);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);
		
		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("js")) {

			String xn = myForm.getXn();
			String xq = myForm.getXq();
			result = service.jsZhf(xn, xq);

			if (result) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))|| result) {
			String[] colList = new String[] { "xh", "xm", "xb", "bjmc", "xn",
					"xqmc", "dyf", "zyf", "tyf", "jnf", "jcf", "zhf","pm" };

			topTr = service.getTopTr(tableName, colList);
			rs = service.getZhfList(tableName, model, colList);
			
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "zjlgPjpy.do?method=zhszcp");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("pjxn", Base.getJxjsqxn());
		request.setAttribute("pjxq", Base.getJxjsqxq());
		request.setAttribute("userDep", userDep);
		request.setAttribute("xqList", service.getXqList());
		unit.setNjXyZyBjList(request, myForm);
		unit.commonRequestSet(request, tableName, realTable, rs, topTr);
		
//		myForm.setXn(xn);
//		myForm.setXq(xq);

		return mapping.findForward("zhfManage");
	}
	
	/**
	 * �ۺϷ�ά��
	 * 
	 * @throws Exception
	 * 
	 */
	public ActionForward zhfUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZhfService service = new ZhfService();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		ZhfModel model = new ZhfModel();

		HashMap<String, String> map = new HashMap<String, String>();

		String title = "�������� - ��Ϣά�� - �ۺ����ʷ�����ά��";
		String doType = request.getParameter("doType");

		BeanUtils.copyProperties(model, myForm);

		if ("save".equals(doType)) {
			boolean result = service.saveZhfsx(model, request);

			request.setAttribute("result", result);
		}

		map=service.getZhfsx();
		
		unit.setNjXyZyBjList(request, myForm);

		request.setAttribute("xqList", service.getXqList());
		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", map );
		return mapping.findForward("zhfUpdate");
	}
}
