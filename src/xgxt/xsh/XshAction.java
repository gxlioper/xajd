package xgxt.xsh;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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

import sun.misc.BASE64Decoder;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.UniqID;

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.bdsz.BdszService;
import xgxt.form.SaveForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

public class XshAction extends BasicAction {

	/**
	 * ���Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hdxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BdszService bdszService = new BdszService();
		XshModel model = new XshModel();
		XshForm myForm = (XshForm) form;

		String realTable = "xsh_hdxx";
		// String tableName = "view_xsh_hdxx";
		String[] colList = new String[] { "pkValue", "hdzt", "hdsj", "hddd",
				"fqr" };
		String doType = request.getParameter("doType");
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		// ����ɾ����ѡ����
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = request.getParameterValues("checkVal");

			if (null != checkVal && checkVal.length > 0) {

				result = bdszService.delData(realTable, checkVal);

				request.setAttribute("result", result);
			}
		}

		// ��ѯ
		if (!Base.isNull(doType) && "query".equals(doType)) {

			// ��ѯ����
			String[] queryList = new String[] {};
			String[] queryLikeList = new String[] { "hdzt", "hddd", "fqr" };

			// ������������ѯ�Զ����ֶε����������������ֶ�����
			HashMap<String, String[]> zdyCol = bdszService.getZdyCol(realTable);

			String[] zdyZdEn = zdyCol.get("zdyZdEn");
			String[] zdyZdCn = zdyCol.get("zdyZdCn");
			String[] zdyZdLx = zdyCol.get("zdyZdLx");

			// ���Զ����ֶεı�ͷ�������
			topTr = bdszService.getZdyTopTr(realTable, colList, zdyZdEn,
					zdyZdCn);
			rs = bdszService.getZdyData(realTable, realTable, queryList,
					queryLikeList, model, colList, zdyZdEn, zdyZdLx);
		}

		request.setAttribute("path", "xsh_hdxx.do");
		FormModleCommon.commonRequestSet(request, realTable, realTable, rs,
				topTr);
		return mapping.findForward("hdxxManage");
	}

	/**
	 * ���Ϣά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hdxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userName = (String) session.getAttribute("userName");

		BdszService bdszService = new BdszService();
		XshModel model = new XshModel();
		XshForm myForm = (XshForm) form;

		boolean result = false;
		String realTable = "xsh_hdxx";
		String pkV = request.getParameter("pk");
		String doType = request.getParameter("doType");

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		model.setFqr(userName);
		// ����
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			String pkValue = model.getHdzt() + model.getHdsj()
					+ model.getHddd();
			String guid = UniqID.getInstance().getUniqIDHash();
			model.setStdm(guid);
			result = bdszService.saveData(realTable, pkValue, model, request);

			request.setAttribute("result", result);
		}

		// �޸�
		if (!Base.isNull(doType) && "modify".equalsIgnoreCase(doType)) {
			String pkValue = model.getHdzt() + model.getHdsj()
					+ model.getHddd();
			result = bdszService.updateData(realTable, pkValue, pkV, model,
					request);

			request.setAttribute("result", result);
		}

		// ��������
		if (!Base.isNull(doType) && "view".equalsIgnoreCase(doType)
				|| "update".equalsIgnoreCase(doType)) {

			rs = bdszService.getOneData(realTable, realTable, pkV);
		}

		bdszService.setBdZd(realTable, myForm);
		request.setAttribute("pk", pkV);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("path", "xsh_hdxx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hdxxUpdate");
	}

	/**
	 * ������Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xcxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BdszService bdszService = new BdszService();
		XshModel model = new XshModel();
		XshForm myForm = (XshForm) form;

		String realTable = "xsh_xcxx";
		// String tableName = "view_xsh_xcxx";
		String[] colList = new String[] { "pkValue", "xczt", "xcsj", "xcdd",
				"xckh" };
		String doType = request.getParameter("doType");
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		// ����ɾ����ѡ����
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = request.getParameterValues("checkVal");

			if (null != checkVal && checkVal.length > 0) {

				result = bdszService.delData(realTable, checkVal);

				request.setAttribute("result", result);
			}
		}

		// ��ѯ
		if (!Base.isNull(doType) && "query".equals(doType)) {

			// ��ѯ����
			String[] queryList = new String[] {};
			String[] queryLikeList = new String[] { "xczt", "xcdd", "xckh" };

			// ������������ѯ�Զ����ֶε����������������ֶ�����
			HashMap<String, String[]> zdyCol = bdszService.getZdyCol(realTable);

			String[] zdyZdEn = zdyCol.get("zdyZdEn");
			String[] zdyZdCn = zdyCol.get("zdyZdCn");
			String[] zdyZdLx = zdyCol.get("zdyZdLx");

			// ���Զ����ֶεı�ͷ�������
			topTr = bdszService.getZdyTopTr(realTable, colList, zdyZdEn,
					zdyZdCn);
			rs = bdszService.getZdyData(realTable, realTable, queryList,
					queryLikeList, model, colList, zdyZdEn, zdyZdLx);

		}

		request.setAttribute("path", "xsh_xcxx.do");
		FormModleCommon.commonRequestSet(request, realTable, realTable, rs,
				topTr);
		return mapping.findForward("xcxxManage");
	}

	/**
	 * ������Ϣά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xcxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BdszService bdszService = new BdszService();
		XshService service = new XshService();
		XshModel model = new XshModel();
		XshForm myForm = (XshForm) form;

		boolean result = false;
		String realTable = "xsh_xcxx";
		String pkV = request.getParameter("pk");
		String doType = request.getParameter("doType");

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		service.fileUpload(request, myForm, model);
		
		String message=(String)request.getAttribute("message");
		boolean flag=true;
		if(!Base.isNull(message)){
			flag=false;
		}

		// ����
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType) && flag) {

			String pkValue = model.getXczt() + model.getXcdd()
					+ model.getXcsj();

			result = bdszService.saveData(realTable, pkValue, model, request);

			request.setAttribute("message", result ? "�����ɹ�!" : "����ʧ��!");
		}

		// �޸�
		if (!Base.isNull(doType) && "modify".equalsIgnoreCase(doType) && flag) {
			String pkValue = model.getXczt() + model.getXcdd()
					+ model.getXcsj();
			result = bdszService.updateData(realTable, pkValue, pkV, model,
					request);

			request.setAttribute("message", result ? "�����ɹ�!" : "����ʧ��!");
		}

		// ��������
		rs = bdszService.getOneData(realTable, realTable, pkV);

		bdszService.setBdZd(realTable, myForm);
		request.setAttribute("pk", pkV);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("path", "xsh_xcxx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xcxxUpdate");
	}

	/**
	 * ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward downLoadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		byte b[] = new byte[500];
		String dir = DealString.toGBK(request.getParameter("dir"));
		String filename = dir.substring(26, dir.length());
		File fileload = new File(dir);
		if (fileload.exists()) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ DealString.toUtf8String(filename));
			InputStream in = new FileInputStream(fileload);
			in = new BufferedInputStream(in);
			while ((in.read(b)) != -1) {
				response.getOutputStream().write(b);
			}
			return null;

		} else {
			request.setAttribute("message", "�ļ������ڻ�����ɾ��!");
			return new ActionForward("/prompt.do", false);
		}

	}

	/**
	 * ɾ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XshService service = new XshService();
		XshModel model = new XshModel();

		String filepath = request.getParameter("filepath");
		String tableName = request.getParameter("tableName");
		String url = request.getParameter("url");
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");

		boolean result = false;

		File f = new File(filepath);
		// �жϸ����Ƿ���ڣ���������ɾ��
		if (f.exists()) {
			f.delete();
		}

		String pk = request.getParameter("pk");
		String[] onezd = new String[] { "fjlj" };
		model.setFjlj("");

		SaveForm saveForm = new SaveForm();

		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		result = service.updateXsh(saveForm, model);
		request.setAttribute("message", result ? "ɾ���ɹ�!" : "ɾ��ʧ��!");

		return new ActionForward("/xsh.do?method=" + url + "&doType=" + doType
				+ "&pk=" + pkValue, false);
	}

	/**
	 * ���Ź���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BdszService bdszService = new BdszService();
		XshService service = new XshService();
		XshModel model = new XshModel();
		XshForm myForm = (XshForm) form;

		String realTable = "xsh_stglb";
		String tableName = "view_xsh_stglb";
		String[] colList = new String[] { "pkValue", "stmc", "stxz", "stcsr",
				"stclsj", "zdls", "fzr" };
		String doType = request.getParameter("doType");
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		// ����ɾ����ѡ����
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = request.getParameterValues("checkVal");

			if (null != checkVal && checkVal.length > 0) {

				result = bdszService.delData(realTable, checkVal);

				request.setAttribute("result", result);
			}
		}

		// ��ѯ
		if (!Base.isNull(doType) && "query".equals(doType)) {

			// ��ѯ����
			String[] queryList = new String[] { "stxz" };
			String[] queryLikeList = new String[] { "stmc", "stcsr" };

			// ������������ѯ�Զ����ֶε����������������ֶ�����
			HashMap<String, String[]> zdyCol = bdszService.getZdyCol(realTable);

			String[] zdyZdEn = zdyCol.get("zdyZdEn");
			String[] zdyZdCn = zdyCol.get("zdyZdCn");
			String[] zdyZdLx = zdyCol.get("zdyZdLx");

			// ���Զ����ֶεı�ͷ�������
			topTr = bdszService.getZdyTopTr(tableName, colList, zdyZdEn,
					zdyZdCn);
			rs = bdszService.getZdyData(realTable, tableName, queryList,
					queryLikeList, model, colList, zdyZdEn, zdyZdLx);

		}

		service.setList(request, 30);
		request.setAttribute("path", "xsh_stwh.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		if (Globals.XXDM_CDTYXY.equalsIgnoreCase(Base.xxdm)) {
			request.setAttribute("cdtyxg", "cdtyxg");
		}
		return mapping.findForward("stwhManage");
	}

	/**
	 * ����ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BdszService bdszService = new BdszService();
		XshService service = new XshService();
		XshModel model = new XshModel();
		XshForm myForm = (XshForm) form;

		boolean result = false;
		String realTable = "xsh_stglb";
		String pkV = request.getParameter("pk");
		String doType = request.getParameter("doType");

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if (Base.isNull(model.getBmdm())) {
			model.setBmdm("��");
		}

		// ����
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			String pkValue = model.getStmc() + model.getStxz()
					+ model.getBmdm();
			String guid = UniqID.getInstance().getUniqIDHash();
			model.setStdm(guid);
			result = bdszService.saveData(realTable, pkValue, model, request);

			request.setAttribute("result", result);
		}

		// �޸�
		if (!Base.isNull(doType) && "modify".equalsIgnoreCase(doType)) {
			String pkValue = model.getStmc() + model.getStxz()
					+ model.getBmdm();
			result = bdszService.updateData(realTable, pkValue, pkV, model,
					request);

			request.setAttribute("result", result);
		}

		// ��������
		if (!Base.isNull(doType) && "view".equalsIgnoreCase(doType)
				|| "update".equalsIgnoreCase(doType)) {

			rs = bdszService.getOneData(realTable, realTable, pkV);

		}
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		bdszService.setBdZd(realTable, myForm);
		service.setList(request, 30);
		request.setAttribute("zdmc", request.getParameter("zdmc"));
		request
				.setAttribute("now", GetTime.getSystemTime()
						.replaceAll("-", ""));
		request.setAttribute("pk", pkV);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("path", "xsh_stwh.do");
		FormModleCommon.commonRequestSet(request);

		if (Globals.XXDM_CDTYXY.equalsIgnoreCase(Base.xxdm)) {
			request.setAttribute("cdtyxg", "cdtyxg");
		}

		return mapping.findForward("stwhUpdate");
	}

	/**
	 * ��ʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XshService service = new XshService();
		XshModel model = new XshModel();
		XshForm myForm = (XshForm) form;

		String doType = request.getParameter("doType");
		String zdmc = request.getParameter("zdmc");
		String[] topTr = new String[] { "�û���", "����", "�û���" };
		List<String[]> rs = new ArrayList<String[]>();

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) && "query".equals(doType)) {
			rs = service.getJs(model);

			if (null != rs) {
				request.setAttribute("rsNum", rs.size());
			}
		}

		request.setAttribute("zdmc", zdmc);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		return mapping.findForward("jsInfo");
	}

	/**
	 * ��Ա����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stwhCygl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName= (String) session.getAttribute("userName");
		//String n =request.getParameter("nd");
		
		XshService service = new XshService();
		XshForm myForm = (XshForm) form;

		String tableName = "xsxxb";
		String viewName = "view_xsjbxx";
		String[] outputColumn = new String[] { "pkValue", "xh", "xm", "nj",
				"xymc", "bjmc" };
		String doType = request.getParameter("doType");
		String flg = request.getParameter("flg");
		String stmc = request.getParameter("stmc");

		
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}

		if (!Base.isNull(doType) && "query".equals(doType)) {
			request.setAttribute("annexTerm",
					" and not exists(select 1 from xsh_cyglb b where a.xh=b.xh and b.stv='"
							+ stmc + "')");
			this.selectPageDataByPagination(request, form, tableName, viewName,
					outputColumn);
		}

		if (!Base.isNull(doType) && "save".equals(doType)) {
			String[] xh = request.getParameterValues("primarykey_cbv");
			String nd = request.getParameter("nd");
			if (null != xh && xh.length > 0 && !Base.isNull(nd)) {
				request.setAttribute("result", service.saveStcy(xh, stmc,nd));
			}else if(null != xh && xh.length > 0){
				request.setAttribute("result", service.saveStcy(xh, stmc));
			}

		}

		if (Base.isNull(flg)) {
			request.setAttribute("path", "xsh_cygl.do");
		} else {
			request.setAttribute("path", "xsh_xshcy.do");
		}

		request.setAttribute("flg", flg);
		
		List<HashMap<String,String>>stList=new ArrayList<HashMap<String,String>>();
		if ("stu".equalsIgnoreCase(userType)
				&& Globals.XXDM_CDTYXY.equalsIgnoreCase(Base.xxdm)) {
			stList= service.getStgbst(userName);
			if(stList==null || stList.size()==0){
				String msg = "��û��������ų�Ա��Ȩ�ޣ���ȷ�ϣ�";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
			}
			request.setAttribute("stcyddcl", "stcyddcl");
		} else {
			stList=service.getStList();
		}
		request.setAttribute("stList", stList);
		
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		return mapping.findForward("stwhCygl");
	}

	/**
	 * ѧ�����Ա���ɲ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stwhGbgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName=(String)session.getAttribute("userName");
		
		XshService service = new XshService();
		XshForm myForm = (XshForm) form;

		String tableName = "xsh_cyglb";
		String viewName = "view_xsh_cygl";
		String[] outputColumn = null;
		String doType = request.getParameter("doType");
		String flg = request.getParameter("flg");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("isst", "yes");
		map.put("stmc", "ty");

		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}

		if (Base.isNull(flg)) {
			request.setAttribute("path", "xsh_gbgl.do");
			if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_CDTYXY)){
				outputColumn = new String[] { "pkValue", "xh", "xm", "xymc",
						"bjmc", "stmc","zcnd", "stxz", "stcsr", "stclsj", "zdls", "fzr",
						"zwmc", "sfyxstgb" };
			}else{
				outputColumn = new String[] { "pkValue", "xh", "xm", "xymc",
						"bjmc", "stmc", "stxz", "stcsr", "stclsj", "zdls", "fzr",
						"zwmc", "sfyxstgb" };
			}
		} else {
			request.setAttribute("path", "xsh_xshcygl.do");
			outputColumn = new String[] { "pkValue", "xh", "xm", "xymc",
					"bjmc", "zwmc" };
		}

		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName,
					outputColumn);
		}

		if (!Base.isNull(doType) && "save".equals(doType)) {
			String[] pkValue = request.getParameterValues("primarykey_cbv");
			String zwdm = request.getParameter("zwdm");

			if (null != pkValue && pkValue.length > 0) {
				request.setAttribute("result", service.updateZw(pkValue, zwdm));
			}
		}

		if (!Base.isNull(doType) && "szyxfb".equalsIgnoreCase(doType)) {
			String[] pkValue = request.getParameterValues("primarykey_cbv");
			String sfyxstgb = request.getParameter("sfyxstgb");

			if (null != pkValue && pkValue.length > 0) {
				request.setAttribute("result", service.updateYxstgb(pkValue,
						sfyxstgb));
			}
		}

		if (!Base.isNull(doType) && "del".equals(doType)) {
			this.deleteOperation(request, tableName);
		}

		if (!Base.isNull(doType) && "expData".equals(doType)) {

			String[] column = new String[] { "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc", "stmc", "stxz", "rtsj", "zwmc", "sfyxstgb" };

			this.expPageData(request, response, tableName, viewName, column);
			return mapping.findForward("");
		}


		List<HashMap<String,String>>stList=new ArrayList<HashMap<String,String>>();
		if ("stu".equalsIgnoreCase(userType)
				&& Globals.XXDM_CDTYXY.equalsIgnoreCase(Base.xxdm)) {
			stList= service.getStgbst(userName);
			if(stList==null || stList.size()==0){
				String msg = "��û��������ų�Ա��Ȩ�ޣ���ȷ�ϣ�";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
			}
			request.setAttribute("stcyddcl", "stcyddcl");
		} else {
			stList=service.getStList();
		}
		
		
		request.setAttribute("flg", flg);
		request.setAttribute("gbList", service.getGbList(map));
		request.setAttribute("stList", stList);

		// �ɶ�����
		if (Globals.XXDM_CDTYXY.equalsIgnoreCase(Base.xxdm)) {
			request.setAttribute("cdtyxg", "cdtyxg");
		}
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("stwhGbgl");
	}

	/**
	 * ���Ż����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sthdManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BdszService service = new BdszService();
		XshModel model = new XshModel();
		XshForm myForm = (XshForm) form;

		String realTable = "xsh_sthdglb";
		// String tableName = "view_xsh_sthdglb";
		String[] colList = new String[] { "pkValue", "stmc", "hdzt", "hdsj",
				"hddd" };
		String doType = request.getParameter("doType");
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		// ����ɾ����ѡ����
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = request.getParameterValues("checkVal");

			if (null != checkVal && checkVal.length > 0) {

				result = service.delData(realTable, checkVal);

				request.setAttribute("result", result);
			}
		}

		// ��ѯ
		if (!Base.isNull(doType) && "query".equals(doType)) {

			// ��ѯ����
			String[] queryList = new String[] {};
			String[] queryLikeList = new String[] { "stmc", "hdzt", "hddd" };

			// ������������ѯ�Զ����ֶε����������������ֶ�����
			HashMap<String, String[]> zdyCol = service.getZdyCol(realTable);

			String[] zdyZdEn = zdyCol.get("zdyZdEn");
			String[] zdyZdCn = zdyCol.get("zdyZdCn");
			String[] zdyZdLx = zdyCol.get("zdyZdLx");

			// ���Զ����ֶεı�ͷ�������
			topTr = service.getZdyTopTr(realTable, colList, zdyZdEn, zdyZdCn);
			rs = service.getZdyData(realTable, realTable, queryList,
					queryLikeList, model, colList, zdyZdEn, zdyZdLx);

		}

		request.setAttribute("path", "xsh_sthdb.do");
		FormModleCommon.commonRequestSet(request, realTable, realTable, rs,
				topTr);

		return mapping.findForward("sthdManage");
	}

	/**
	 * ���Żά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sthdUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BdszService bdszService = new BdszService();
		XshService service = new XshService();
		XshModel model = new XshModel();
		XshForm myForm = (XshForm) form;

		boolean result = false;
		String realTable = "xsh_sthdglb";
		String pkV = request.getParameter("pk");
		String doType = request.getParameter("doType");

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// ����
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			String pkValue = new StringBuilder().append(model.getStmc())
					.append(model.getStxz()).append(model.getBmdm()).append(
							model.getHdsj()).append(model.getHddd()).toString();

			result = bdszService.saveData(realTable, pkValue, model, request);

			request.setAttribute("result", result);
		}

		// �޸�
		if (!Base.isNull(doType) && "modify".equalsIgnoreCase(doType)) {
			String pkValue = new StringBuilder().append(model.getStmc())
					.append(model.getStxz()).append(model.getBmdm()).append(
							model.getHdsj()).append(model.getHddd()).toString();
			result = bdszService.updateData(realTable, pkValue, pkV, model,
					request);

			request.setAttribute("result", result);
		}

		// ��������
		if (!Base.isNull(doType) && "view".equalsIgnoreCase(doType)
				|| "update".equalsIgnoreCase(doType)) {

			rs = bdszService.getOneData(realTable, realTable, pkV);
		}

		bdszService.setBdZd(realTable, myForm);
		request.setAttribute("pk", pkV);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("path", "xsh_sthdb.do");
		request.setAttribute("stList", service.getStList());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sthdUpdate");
	}

	/**
	 * �������ŵǼǱ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxstdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setAttribute("xn", Base.currXn);
		XshService service = new XshService();

		String pk = request.getParameter("pk");
		String pkCol = "stmc||stxz||bmdm";
		String tableName = "xsh_stglb";
		String realTable = "xsh_stglb";
		String[] colList = { "stmc" };
		request.setAttribute("rs", service.getOneData(tableName, realTable,
				colList, null, pkCol, pk));
		return mapping.findForward("yxstdjb");
	}

	/**
	 * �������Ÿɲ��ǼǱ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxstgbdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setAttribute("xn", Base.currXn);
		XshService service = new XshService();
		String pk = request.getParameter("pk");
		String pkCol = "xh||stv";
		String tableName = "view_xsh_cygl";
		String realTable = "view_xsh_cygl";
		String[] colList = { "xh", "zwmc" };

		HashMap<String, String> stuInfo = service.getOneData(tableName,
				realTable, colList, null, pkCol, pk);
		stuInfo.putAll(service.getStuInfo(stuInfo.get("xh")));
		request.setAttribute("rs", stuInfo);
		return mapping.findForward("yxstgbdjb");
	}

}
