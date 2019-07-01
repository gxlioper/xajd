package xgxt.dtjs.zjlg.zsdy;

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
import xgxt.dtjs.zjlg.ZjlgDtjsForm;
import xgxt.dtjs.zjlg.zbgl.ZbglService;
import xgxt.dtjs.zjlg.zzgx.ZzgxModel;
import xgxt.form.SaveForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

public class ZsdyAction extends DispatchAction {

	/**
	 * ��ʽ��Ա����
	 * 
	 * @return ActionForward
	 */
	public ActionForward zsdyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		ZsdyService service = new ZsdyService();
		ZbglService zbService = new ZbglService();
		ZjlgDtjsForm myForm = (ZjlgDtjsForm) form;
		ZsdyModel model = new ZsdyModel();

		String title = "���Ž��� - ֧������ - ��ʽ��Ա";
		String doType = request.getParameter("doType");
		String tableName = "view_zjlg_dyxx";
		String realTable = "dyxxb";
		String[] checkVal = myForm.getCheckVal();
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		
		String xydm = myForm.getXydm();
		xydm = Base.isNull(xydm) ? "%" : xydm;
		
		String zzzt = myForm.getZzzt();
		if(Base.isNull(zzzt)){
			myForm.setZzzt("yes");
		}else if ("all".equalsIgnoreCase(zzzt)){
			myForm.setZzzt("");
		}	

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				result = service.delZsdy(checkVal);
				request.setAttribute("result", result);
			}
		}
		if ("zj".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				
				String[] xh = service.getArrZd(tableName, "xh", "xn||xq||xh",checkVal);
				String zjlx = request.getParameter("zjlx");
				String zjsj = request.getParameter("zjsj");
				String zjdz = request.getParameter("zjdz");
				String bz = request.getParameter("zjbz");
				
				String[] arrzd = new String[] { "xh" };
				String[] onezd = new String[] { "zjlx","zjsj", "zjdz", "bz","zjmm" };
				String pk = "xh||zjlx||zjsj";
				String[] pkValue = new String[checkVal.length];
				for (int i = 0; i < checkVal.length; i++) {
					pkValue[i] = xh[i] + zjlx + zjsj;
				}

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName("zjlg_zzgx");
				saveForm.setPk(pk);
				saveForm.setPkValue(pkValue);
				saveForm.setArrzd(arrzd);
				saveForm.setOnezd(onezd);

				ZzgxModel zjModel = new ZzgxModel();
				zjModel.setXh(xh);
				zjModel.setZjlx(zjlx);
				zjModel.setZjsj(zjsj);
				zjModel.setZjdz(zjdz);
				zjModel.setBz(bz);
				zjModel.setZjmm("zsdy");
				
				result = service.saveZsdy(saveForm, zjModel);
				
				if (result) {
					onezd = new String[] { "zzzt" };

					saveForm = new SaveForm();
					saveForm.setTableName(realTable);
					saveForm.setOnezd(onezd);
					saveForm.setPk("xn||xq||xh");
					saveForm.setPkValue(checkVal);

					model.setZzzt("no");
					
					result = service.updateZsdy(saveForm, model);
				}
				request.setAttribute("reslut", result);
			}
		}
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			
			// �����û�����MAP
			HashMap<String, String> map = service.setUserTypeMap(request);
			
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
					"xn", "xqmc", "zbmc", "rdsj", "ztmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getZsdyList(tableName, model, colList, map);
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			myForm.setZbdm(DealString.toGBK(myForm.getZbdm()));
		}
		
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "dtjs_zsdy.do");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("xsccList", service.getXscc());
		request.setAttribute("zbList", zbService.getZbList(xydm));
		
		FormModleCommon.setNjXyZyBjListForDzb(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("zsdyManage");
	}

	/**
	 * ��ʽ��Աά��
	 * 
	 * @return ActionForward
	 */
	public ActionForward zsdyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "���Ž��� - ֧������ - ��ʽ��Ա";
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String pk = request.getParameter("pk");
		String blxr = request.getParameter("blxr");
		
		ZsdyService service = new ZsdyService();
		ZjlgDtjsForm myForm = (ZjlgDtjsForm) form;
		ZsdyModel model = new ZsdyModel();
		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			if (Base.isNull(blxr)) {
				if (!Base.isNull(xh)) {
					rs = service.getDetStuInfo(xh);
					rs.put("sfwj", service.getWjqk(xh));
				}
			} else {
				rs = service.getStuInfo(blxr);
				if (Base.isNull(rs.get("rdlxr"))) {
					String lxr = request.getParameter("lxr");
					HashMap<String, String> map = service.getLxrInfo(lxr);
					rs.put("rdlxr", map.get("xh"));
					rs.put("lxrxm", map.get("xm"));
					rs.put("lxrzb", map.get("zbmc"));
					rs.put("lxrbj", map.get("bjmc"));
					rs.put("zzmm", map.get("zzmm"));
				}
				rs.put("sfwj", service.getWjqk(blxr));
			}
			String zzsj = request.getParameter("zzsj");
			rs.put("zzsj", zzsj);
			rs.put("rdsj", request.getParameter("rdsj"));
			rs.put("ybdykssj", request.getParameter("ybdykssj"));
			rs.put("ybdyjssj", request.getParameter("ybdyjssj"));
			rs.put("nd", request.getParameter("nd"));
			rs.put("xn", request.getParameter("xn"));
			rs.put("xq", request.getParameter("xq"));
		}
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			rs = service.getZsdyInfo(pk);
			String lxr = request.getParameter("lxr");
			lxr = Base.isNull(lxr) ? rs.get("rdlxr") : lxr;
			if (!Base.isNull(lxr)) {
				HashMap<String, String> map = service.getLxrInfo(lxr);
				rs.put("rdlxr", map.get("xh"));
				rs.put("lxrxm", map.get("xm"));
				rs.put("lxrzb", map.get("zbmc"));
				rs.put("lxrbj", map.get("bjmc"));
				rs.put("zzmm", map.get("zzmm"));
			}
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveZsdy(model, request);
			request.setAttribute("result", result);
		}
		if ("zz".equalsIgnoreCase(doType)) {
			boolean result = service.saveZzgx(model, request);
			request.setAttribute("result", result);
		}
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForDzb(request);

		request.setAttribute("xsccList", service.getXscc());
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("pk", pk);

		return mapping.findForward("zsdyUpdate");
	}
	
	/**
	 * ��ϵ����Ϣ����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward lxrxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		String doType = request.getParameter("doType");
		
		ZsdyService service = new ZsdyService();
		ZjlgDtjsForm myForm = (ZjlgDtjsForm) form;
		ZsdyModel model = new ZsdyModel();
		ZbglService zbService = new ZbglService();
		
		String tableName = "view_zjlg_lxrxx";
		String realTable = "view_zjlg_lxrxx";
		String blxr = request.getParameter("blxr");
		String xydm = myForm.getXydm();
		xydm = Base.isNull(xydm) ? "%" : xydm;
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			String[] colList = new String[] { "xh", "xm", "xb", "xymc", "zymc",
					"bjmc", "zbmc", "zzmm" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);

		}
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "gqt_dagl_tydj.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("doType", doType);
		request.setAttribute("blxr", blxr);
		request.setAttribute("zbList", zbService.getZbList(xydm));
		FormModleCommon.setNjXyZyBjListForDzb(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("lxrxxManage");
	}
}
