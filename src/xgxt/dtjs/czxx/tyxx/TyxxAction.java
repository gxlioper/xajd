package xgxt.dtjs.czxx.tyxx;

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

import com.zfsoft.basic.BasicAction;

import common.Globals;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.comm.MessageInfo;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.dtjs.czxx.dyxx.DyxxService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.SearchUtils;
import xgxt.xszz.zgmsxy.XszzZgmsxyActionForm;
import xgxt.xszz.zgmsxy.XszzZgmsxyService;

public class TyxxAction extends BasicAction {
	
	/***
	 * ����ҳ�����
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": (xydm==null ? "" : xydm); 
		zy = zy==null ? "": (zydm==null ? "" : zydm); 
		njLocal = nj==null ? "": nj;
		
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		
				
		request.setAttribute("njList", Base.getNjList());//�꼶�б�
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//�༶�б�	
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
	}
	
	/**
	 * ��Ա��Ϣ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward tyxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		TyxxService service = new TyxxService();
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		TyxxModel model = new TyxxModel();

		String tableName = "view_czxx_tyxx";
		String realTable = "tyxxb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		boolean reslut = false;
		
		// ����������Ա
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			// ��Աѧ��
			String[] tyxh = myForm.getTyxh();
			String[] rtrq = myForm.getRtrq();
			String[] rtdd = myForm.getRtdd();
			
			// ҳ������ѧ��
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xh";
				String[] arrzd = new String[] {"xh", "rtrq", "rtdd"};
				
				String[] rqarr = null;
				String[] ddarr = null;
				if(tyxh !=null && tyxh.length>0){
					rqarr = new String[tyxh.length];
					ddarr = new String[tyxh.length];
					
					for(int i=0;i<tyxh.length;i++){
						for(int j=i;j<checkVal.length;j++){
							if(tyxh[i].equalsIgnoreCase(checkVal[j])){
								rqarr[i] = rtrq[j];
								ddarr[i] = rtdd[j];
								break;
							}
						}
					}
				}
				
				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);
				saveForm.setArrzd(arrzd);
				
				TyxxbcModel tyxxbc = new TyxxbcModel();
				tyxxbc.setXh(tyxh);
				tyxxbc.setRtrq(rqarr);
				tyxxbc.setRtdd(ddarr);
				reslut = service.saveTyxx(saveForm, tyxxbc);
				request.setAttribute("result", reslut);
			}
		}
		if(!Base.isNull(userType) && "xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
			myForm.setXydm(userDep);
		}
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| reslut) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "xymc", "zymc", "bjmc", "sfty", "rtrq", "rtdd"};
			if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CDTYXY)){
				
				colList = new String[] { "pk","xh", "xm", "xb", "xymc", "zymc", "bjmc", "sfty", "nd", "rtrq", "rtdd"};
			}
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.queryTyxxList(tableName, model, colList);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "dtjs_tyxx.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// �����б�ֵ
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,	topTr);
		if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CDTYXY)){
			request.setAttribute("xntj", "xntj");
			
		}
		return mapping.findForward("tyxxManage");
	}
	
	/**
	 * ��Ա��Ϣ�޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tyxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		TyxxService service = new TyxxService();
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, "tyxxb");
		}
		
		request.setAttribute("rs", service.getTyxxOne(pkValue));
		return mapping.findForward("tyxxUpdate");
	}
	
	/**
	 * ��Ա��Ϣ��ѯ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward tyxxExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		TyxxModel model = new TyxxModel();
		TyxxService service = new TyxxService();
		model.setXh(request.getParameter("xh"));
		model.setXm(request.getParameter("xm"));
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm(request.getParameter("bjdm"));
		model.setNj(request.getParameter("nj"));
		model.setSfty(request.getParameter("sfty"));
		
		String[] colList = {"xh","xm","xb","xydm","xymc","zydm","zymc","bjdm","bjmc","nj","sfty"};
		String[] colListCN = service.getColumnNameCN(colList, "view_czxx_tyxx");
		ArrayList<String[]> rs = service.queryTyxxForExport("view_czxx_tyxx",model,colList);//��ѯ��Ա��Ϣ
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());		
		return mapping.findForward("");
	}
	
	/**
	 * ����Ա��ѵ��Ϣ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward ftypxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		String doType = request.getParameter("doType");

		TyxxService service = new TyxxService();
		CzxxDtjsForm model = (CzxxDtjsForm) form;

		String tableName = "view_czxx_ftypxxxb";
		String realTable = "ftypxxxb";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		boolean result = false;
		
		// ����ɾ�ǵ�Ա��ѵ��Ϣ
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = model.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xh||pxxmdm||pxsj";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delFtypxxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		if(!Base.isNull(userType) && "xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
			//myForm.setXydm(userDep);
		}
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk","xh", "xm", "xb", "xymc", "zymc", "bjmc", "nd","xn","xqmc","pxxmmc","pxsj","pxcj" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.queryFtypxxxList(tableName, model, colList);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "dtjs_ftypx.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// �����б�ֵ
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,	topTr);
		return mapping.findForward("ftypxManage");
	}
	
	/**
	 * ����Ա��ѵ��Ϣά��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 */
	public ActionForward ftypxxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String title = "���Ž��� - �����Ž� - ����Ա��ѵ  - ��Ϣά��";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_czxx_ftypxxxb";
		String realTable = "ftypxxxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm model = (CzxxDtjsForm) form;
		TyxxService service = new TyxxService();
		StudentInfoService xsxxService = new StudentInfoService();
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = xsxxService.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "xn", "xq","xqmc",
					"nd", "pxxmdm", "pxxmmc","pxcj","pxsj","bz" };
			rs = service.queryFtypxInfo(tableName, pk, pkValue, colList);
			model.setXydm(rs.get("xydm"));
		}

		// �������Ա��ѵ��Ϣ
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "pxxmdm",
					"pxsj","pxcj","bz" };
			pk = "xh||pxxmdm||pxsj";
			pkValue = model.getXh()+model.getPxxmdm()+model.getPxsj();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveFtypxxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		request.setAttribute("pxxmList", service.queryFtypxxmList());
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		return mapping.findForward("ftypxxxUpdate");
	}
	
	/**
	 * ��Ա��ѵ��Ϣ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward typxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		String doType = request.getParameter("doType");

		TyxxService service = new TyxxService();
		CzxxDtjsForm model = (CzxxDtjsForm) form;

		String tableName = "view_czxx_typxxxb";
		String realTable = "typxxxb";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		boolean result = false;
		
		// ����ɾ�ǵ�Ա��ѵ��Ϣ
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = model.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xh||pxxmdm||pxsj";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delFtypxxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		if(!Base.isNull(userType) && "xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
			//myForm.setXydm(userDep);
		}
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk","xh", "xm", "xb", "xymc", "zymc", "bjmc", "nd","xn","xqmc","pxxmmc","pxsj","pxcj" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.queryFtypxxxList(tableName, model, colList);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "dtjs_typx.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// �����б�ֵ
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,	topTr);
		return mapping.findForward("typxManage");
	}
	
	/**
	 * ��Ա��ѵ��Ϣά��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 */
	public ActionForward typxxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_czxx_typxxxb";
		String realTable = "typxxxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm model = (CzxxDtjsForm) form;
		TyxxService service = new TyxxService();
		StudentInfoService xsxxService = new StudentInfoService();
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = xsxxService.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "xn", "xq","xqmc",
					"nd", "pxxmdm", "pxxmmc","pxcj","pxsj","bz" };
			rs = service.queryFtypxInfo(tableName, pk, pkValue, colList);
			model.setXydm(rs.get("xydm"));
		}

		// �������Ա��ѵ��Ϣ
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "pxxmdm",
					"pxsj","pxcj","bz" };
			pk = "xh||pxxmdm||pxsj";
			pkValue = model.getXh()+model.getPxxmdm()+model.getPxsj();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveFtypxxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		request.setAttribute("path", "dtjs_typx.do");
		FormModleCommon.commonRequestSet(request);
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		request.setAttribute("pxxmList", service.queryTypxxmList());
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		return mapping.findForward("typxxxUpdate");
	}
	
	/**
	 * ����������Ϣ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward tntyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		String doType = request.getParameter("doType");
		String fdyQx = session.getAttribute("fdyQx").toString();
		boolean isBzr = !Base.isNull(fdyQx) && "true".equalsIgnoreCase(fdyQx) ? true : false;//Fdypd.isBzr(userName, "");
		String shjg = request.getParameter("shjg");

		TyxxService service = new TyxxService();
		CzxxDtjsForm model = (CzxxDtjsForm) form;

		String tableName = "view_yxtyxxb";
		String realTable = "yxtyxxb";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		boolean result = false;
		
		if(!Base.isNull(userType) && "xy".equalsIgnoreCase(userType) && !isBzr){
			model.setXydm(userDep);
		}
		
		// ����ɾ��������Ա��Ϣ
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = model.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xh||xn||nd||xq";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delFtypxxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		// �������������Ա��Ϣ
		if (!Base.isNull(doType) && "auditing".equalsIgnoreCase(doType)) {
			String[] checkVal = model.getCheckVal();
			String message = service.checkTy(checkVal);
			String[] shjd = null;			
			
			if(!Base.isNull(message) && "ͨ��".equalsIgnoreCase(shjg)){//������Ա �����ͨ��
				request.setAttribute("error", message+"���ʧ�ܣ�");
				result = true;
			}else{
				if(isBzr){
					model.setBzrsh(shjg);
					shjd = new String[]{"bzrsh"};
				}else if(!isBzr && "xy".equalsIgnoreCase(userType)){
					model.setXysh(shjg);
					shjd = new String[]{"xysh"};
				}else if(!isBzr && ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType))){
					model.setXxsh(shjg);
					shjd = new String[]{"xxsh"};
				}
				if (checkVal != null && checkVal.length > 0) {
					String pk = "xh||xn||nd||xq";
	
					SaveForm saveForm = new SaveForm();
					saveForm.setTableName(realTable);
					saveForm.setPk(pk);
					saveForm.setPkValue(checkVal);
					saveForm.setOnezd(shjd);
	
					result = service.auditingTnty(saveForm, model);
					request.setAttribute("result", result);
					
					model.setBzrsh("");
					model.setXysh("");
					model.setXxsh("");
				}
			}
		}
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			
			String[] colList = null;
			String xxdm = Base.xxdm;
			
			if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
				model.setIsBzr(isBzr ? "true" : "false");
				model.setUserName(userName);
				colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
						"zymc", "bjmc", "nd", "xn", "xqmc", "bzrsh", "xysh",
						"xxsh" };
				if (!isBzr && "xy".equalsIgnoreCase(userType)) {// ѧԺ�û�
					model.setBzrsh("ͨ��");
				}
				if (!isBzr && "xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {// ѧУ�û�
					model.setBzrsh("ͨ��");
					model.setXysh("ͨ��");
				}
			} else {
				colList = new String[] { "pk", "nd", "xn", "xqmc", "xh", "xm", "xb", "xymc",
						"zymc", "bjmc"};
			}
			
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.queryFtypxxxList(tableName, model, colList);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "dtjs_tnty.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("isBzr", isBzr);
		// �����б�ֵ
		DyxxService dtService = new DyxxService();
		CzxxDtjsForm dtModel = new CzxxDtjsForm();
		dtService.setList(dtModel, request);
		//appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,	topTr);
		if(isBzr){
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
		}
		return mapping.findForward("tntyManage");
	}
	
	/**
	 * ����������Ϣά��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 */
	public ActionForward tntyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String title = "���Ž��� - �����Ž� - ��������  - ��Ϣά��";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_yxtyxxb";
		String realTable = "yxtyxxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm model = (CzxxDtjsForm) form;
		TyxxService service = new TyxxService();
		StudentInfoService xsxxService = new StudentInfoService();
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = xsxxService.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "xn", "xq","xqmc",
					"nd", "bzrsh", "xysh","xxsh","bz" };
			rs = service.queryFtypxInfo(tableName, pk, pkValue, colList);
			model.setXydm(rs.get("xydm"));
		}

		// �������Ա��ѵ��Ϣ
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd","bz" };
			pk = "xh||xn||nd||xq";
			pkValue = model.getXh()+model.getXn()+model.getNd()+model.getXq();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveFtypxxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		request.setAttribute("pxxmList", service.queryTypxxmList());
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);		
		return mapping.findForward("tntyUpdate");
	}	
	
	/**
	 * ��Աע�����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 */
	public ActionForward tyzcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//��ȡuser����
		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		//forms
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		TyxxService service = new TyxxService();
		CommService commService=new CommService();

		String doType=request.getParameter("doType");
		
		String message="";
		
		//ɾ������
		if("del".equalsIgnoreCase(doType)){
			boolean flag=service.delTyzc(myForm);
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", message);
		}
		
//		 ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		// ��ʾ����
		String showNum = String.valueOf(service.getTopTr(rForm).size());
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);
		myForm.setUser(user);
		rForm.setRsArrList((ArrayList<String[]>)service.getTyzcList(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============ͨ������ end ============
		
		
		request.setAttribute("path", "dtjs_tyzc.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		//��ͷ
		request.setAttribute("topTr", service.getTopTr(rForm));
		return mapping.findForward("tyzcManage");
	}	
	
	/**
	 * ��Աע��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 */
	public ActionForward tyzcAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		TyxxService service = new TyxxService();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		
		
		// ��Դ�ش����
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveTyzc(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}

		
		request.setAttribute("path",  "dtjs_tyzc.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("zcsj", GetTime.getNowTime2());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("rs", stuInfo);
		request.setAttribute("tableName", "view_tyxxb");
		request.setAttribute("doType", "add");
		return mapping.findForward("tyzcUpdate");
	}	
	
	/**
	 * ��Աע���޸�
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 */
	public ActionForward tyzcUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		TyxxService service = new TyxxService();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		myForm.setPkV(new String[]{pkValue});
		
		// ��Դ�ش����
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.updateTyzc(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
			doType="update";
		}

		request.setAttribute("path", "dtjs_tyzc.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("rs", service.getOneTyzc(myForm));
		
		request.setAttribute("doType", doType);
		return mapping.findForward("tyzcUpdate");
	}	
	
}
