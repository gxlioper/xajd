package xgxt.dtjs.sjxy.zbgl;

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
import xgxt.dtjs.sjxy.SjxyDtjsForm;
import xgxt.form.SaveForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

public class ZbglAction extends DispatchAction {

	/**
	 * ����֧����
	 * 
	 * @return ActionForward
	 */
	public ActionForward zbglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		HttpSession session = request.getSession();

		SjxyDtjsForm myForm = (SjxyDtjsForm) form;
		ZbglService service = new ZbglService();
		ZbglModel model = new ZbglModel();
		
		String doType = request.getParameter("doType");
		String tableName = "view_sjxy_dzz";
		String realTable = "sjxy_dzzb";
		boolean result = false;
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		BeanUtils.copyProperties(model, myForm);
		
		// ����ɾ������֧
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xydm";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delZbgl(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xymc", "dzzmc","xsrs","dyrs","sqrs"};

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getZbglList(tableName, model, colList);
		}
		
		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_zbgl.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,topTr);
		
		return mapping.findForward("zbglManage");
	}
	
	/**
	 * ����֧ά��
	 * 
	 * @return ActionForward
	 */
	public ActionForward dzzUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "���Ž��� - ֧������ - ֧��ά��";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_sjxy_dzz";
		String realTable = "sjxy_dzzb";

		SjxyDtjsForm myForm = (SjxyDtjsForm) form;
		ZbglService service = new ZbglService();
		ZbglModel model = new ZbglModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("update".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "pk", "xydm", "dzzmc","khqk", "xsrs","dyrs","sqrs","bz"};
			rs = service.getZbglInfo(tableName, pk, pkValue, colList);		
		}
		
		//���浳��֧��Ϣ
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xydm", "dzzmc", "khqk", "bz" };
			pk = "xydm";
			pkValue = myForm.getXydm();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveZbgl(saveForm, model, request);
			request.setAttribute("result", result);
		}

		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("dzzUpdate");
	}
	
	/**
	 * ��֧��ά��
	 * 
	 * @return ActionForward
	 */
	public ActionForward dzbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

//		String userType = session.getAttribute("userType").toString();
//		String userName = session.getAttribute("userName").toString();
		String userDep =  session.getAttribute("userDep").toString();
		
		String title = "���Ž��� - ֧������ - ֧��ά��";
		String doType = request.getParameter("doType");
		String pk = "pk";
		String pkValue = userDep;
		String tableName = "view_sjxy_dzz";
		String realTable = "sjxy_dzbb";
		
		SjxyDtjsForm myForm = (SjxyDtjsForm) form;
		ZbglService service = new ZbglService();
		ZbglModel model = new ZbglModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		String[] colList = new String[] { "pk", "xydm", "dzzmc", "khqk", "xsrs","dyrs","sqrs", "bz" };
		rs = service.getZbglInfo(tableName, pk, pkValue, colList);
		if (Base.isNull(rs.get("pk"))) {
			String msg = "�û�����"+Base.YXPZXY_KEY+"��δ��������֧��";
			request.setAttribute("msg", msg);
		}
		
		//���浳֧����Ϣ
		if ("save".equalsIgnoreCase(doType)) {
			
			pk = "id";
			String[] arrzd = new String[] { "sszb","sj", "fsj", "zzwy", "xcwy", "jjwy" };
			String[] onezd = new String[] { "xydm" };
	
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(myForm.getId());

			boolean result = service.saveZbgl(saveForm, model);
			request.setAttribute("result", result);

		}

		// ɾ����֧����Ϣ
		if ("del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				pk = "id";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				boolean result = service.delZbgl(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		service.setList(myForm, request);

		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("pk", pkValue);

		return mapping.findForward("dzbUpdate");
	}
}
