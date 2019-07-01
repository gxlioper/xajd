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
	 * 党总支管理
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
		
		// 批量删除党总支
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
		
		// 点击查询按钮进行查询
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
	 * 党总支维护
	 * 
	 * @return ActionForward
	 */
	public ActionForward dzzUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "党团建设 - 支部管理 - 支部维护";
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
		
		//保存党总支信息
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
	 * 党支部维护
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
		
		String title = "党团建设 - 支部管理 - 支部维护";
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
			String msg = "用户所在"+Base.YXPZXY_KEY+"还未建立党总支。";
			request.setAttribute("msg", msg);
		}
		
		//保存党支部信息
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

		// 删除党支部信息
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
