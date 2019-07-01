package xgxt.dtjs.zjcm;

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
import xgxt.dtjs.DtjsForm;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.dtjs.czxx.dyxx.DyxxService;
import xgxt.form.SaveForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.xsgygl.GyglTyForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �㽭��ýѧԺѧ����Ϣ�����Ž���-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class ZjcmDtjsAction extends DispatchAction {

	/**
	 * ��ѵ��Ϣ����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward pxxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjsForm myForm = (DtjsForm) form;
		ZjcmDtjsService service = new ZjcmDtjsService();
		ZjcmDtjsModel model = new ZjcmDtjsModel();

		String doType = request.getParameter("doType");
		String tableName = "view_xspxxx";
		String realTable = "xspxxxb";
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
	
		BeanUtils.copyProperties(model, myForm);

		// ����ɾ����ѵ��Ϣ
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||xh||pxxmdm";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDtjs(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "nd", "xn", "xqmc", "xh",
					"xm", "xb", "bjmc", "pxxmmc", "pxkssj", "pxjssj", "pxjg",
					"zsyw" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDtjsList(tableName, model, colList,"");

		}
		// ��ʼ���б�ֵ
		service.setList(myForm, request, "pxxx");
		
		request.setAttribute("path", "dtjs_pxxx.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("pxxxManage");
	}
	
	/**
	 * ��ѵ��Ϣά��
	 * 
	 * @return ActionForward
	 */
	public ActionForward pxxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "���Ž��� - ����ά�� - ��ѵ��Ϣ";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_xspxxx";
		String realTable = "xspxxxb";
		doType = Base.isNull(doType) ? "add" : doType;

		DtjsForm myForm = (DtjsForm) form;
		ZjcmDtjsService service = new ZjcmDtjsService();
		ZjcmDtjsModel model = new ZjcmDtjsModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xn", "xq", "nd", "xh", "xm",
					"xb", "nj", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
					"pxxmdm", "pxkssj", "pxjssj", "pxjg", "zsyw", "bz" };
			rs = service.getDtjsInfo(tableName, pk, pkValue, colList);
		}
		
		// ������ѵ��Ϣ
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "nd", "xn", "xq", "xh", "pxxmdm",
					"pxkssj", "pxjssj", "pxjg", "zsyw", "bz" };
			pk = "xn||xq||xh||pxxmdm";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh() + myForm.getPxxmdm();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDtjs(saveForm, model, request);
			request.setAttribute("result", result);
		}

		//	��ʼ���б�ֵ
		service.setList(myForm, request, "pxxx");

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("pxxxUpdate");
	}

	/**
	 * ��չ�������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fzdxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjsForm myForm = (DtjsForm) form;
		ZjcmDtjsService service = new ZjcmDtjsService();
		ZjcmDtjsModel model = new ZjcmDtjsModel();

		String doType = request.getParameter("doType");
		String tableName = "view_fzdx";
		String realTable = "fzdx";
		String[] checkVal = myForm.getCheckVal();
		String csh = request.getParameter("csh");
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
	
		BeanUtils.copyProperties(model, myForm);

		// ��ʼ�����ݣ���ְ״̬Ϊyes
		if (Base.isNull(csh)) {
			myForm.setZzzt("yes");
			csh = "no";
		}
	
		// ����ɾ����չ����
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||xh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDtjs(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		// ��չ���� --> Ԥ����Ա
		if (!Base.isNull(doType) && "zz".equalsIgnoreCase(doType)) {
			//�ж��������Ƿ�����û����ѵ֤��
			if(service.isPxzs(checkVal)){
				String message = "�ύ��������û����ѵ֤�飬��ȷ��";
				result = true;
				request.setAttribute("message", message);
			}else{
				result = service.changeDylx(checkVal, "��չ����", request);
				request.setAttribute("result", result);
			}
		}
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "nd", "xn", "xqmc", "xh",
					"xm", "xb", "bjmc", "kssj", "jssj" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getFzdxList(tableName, model, colList);

		}
		// ��ʼ���б�ֵ
		service.setList(myForm, request, "fzdx");
		
		request.setAttribute("path", "dtjs_fzdx.do");
		request.setAttribute("csh", csh);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("fzdxManage");
	}
	
	/**
	 * ��չ����ά��
	 * 
	 * @return ActionForward
	 */
	public ActionForward fzdxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "���Ž��� - ����ά�� - ��չ����";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_fzdx";
		String realTable = "fzdx";
		doType = Base.isNull(doType) ? "add" : doType;

		DtjsForm myForm = (DtjsForm) form;
		ZjcmDtjsService service = new ZjcmDtjsService();
		ZjcmDtjsModel model = new ZjcmDtjsModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xn", "xq", "nd", "xh", "xm",
					"xb", "nj", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc",
					"kssj", "jssj", "bz" };
			rs = service.getDtjsInfo(tableName, pk, pkValue, colList);
		}
		
		// ���淢չ����
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "nd", "xn", "xq", "xh", "kssj",
					"jssj", "bz" };
			pk = "xn||xq||xh";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDtjs(saveForm, model, request);
			request.setAttribute("result", result);
		}

		//	��ʼ���б�ֵ
		service.setList(myForm, request, "fzdx");

		if(!Base.isNull(rs.get("xh"))){
			model= new ZjcmDtjsModel();
			model.setXh(rs.get("xh"));
			tableName="view_xspxxx";
			String[] colList = new String[] { "xn","xqmc","nd","pxxmmc","pxkssj","pxjssj","pxjg","zsyw" };
			ArrayList<String[]> rsList = service.getDtjsList(tableName, model, colList,"");
			if (rsList != null && rsList.size() > 0) {
				List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName, colList, null);
				request.setAttribute("topTr", topTr);
				request.setAttribute("rsList", rsList);
				request.setAttribute("rsNum", rsList.size());
			}
		}
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("fzdxUpdate");
	}
}
