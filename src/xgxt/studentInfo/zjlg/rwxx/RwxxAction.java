package xgxt.studentInfo.zjlg.rwxx;

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
import xgxt.studentInfo.zjlg.ZjlgXsxxForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

public class RwxxAction extends DispatchAction {

	/**
	 * ������Ϣ����
	 * 
	 * @return ActionForward
	 */
	public ActionForward rwxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		RwxxService service = new RwxxService();
		ZjlgXsxxForm myForm = (ZjlgXsxxForm) form;
		// BjlhGyglUnit unit = new BjlhGyglUnit();
		RwxxModel model = new RwxxModel();

		String title = "ѧ����Ϣ - ѧ����Ϣά�� - ������Ϣ";
		String doType = request.getParameter("doType");
		String tableName = "view_zjlg_rwxx";
		String realTable = "zjlg_rwxx";
		String[] checkVal = myForm.getCheckVal();
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				result = service.delRwxx(checkVal);
				request.setAttribute("result", result);
			}
		}

		// �����ѯ��ť���в�ѯ
		String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
				"rwsj", "twsj" };

		topTr = SearchUtils.getTopTr(tableName, colList, null);
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			
			rs = service.getRwxxList(tableName, model, colList);
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		//=========2011.1.26 lr=======
		if(rs == null || rs.size()<1){//�޼�¼ʱ����ʼ����ҳ�����ܼ�¼��
			myForm.getPages().setMaxPage(0);
			myForm.getPages().setMaxRecord(0);
		}
		//=========end=======
		
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "zjlg_xsxx_rwxx.do");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("jlxmList", service.getJlxm());
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("rwxxManage");
	}

	/**
	 * ������Ϣά��
	 * 
	 * @return ActionForward
	 */
	public ActionForward rwxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "ѧ����Ϣ - ѧ����Ϣά�� - ������Ϣ";
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String pk = request.getParameter("pk");

		RwxxService service = new RwxxService();
		ZjlgXsxxForm myForm = (ZjlgXsxxForm) form;
		RwxxModel model = new RwxxModel();
		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			if (Base.isNull(xh)) {
				rs.put("xh", " ");
			} else {
				rs = service.getStuInfo(xh);
			}
		}
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			rs = service.getRwxx(pk);
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveRwxx(model, request);
			request.setAttribute("result", result);
		}

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("rwxxUpdate");
	}
}
