package xgxt.dtjs.czxx.dyxx;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
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
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;

import com.zfsoft.basic.BasicAction;
import common.Globals;

public class DyxxAction extends BasicAction {

	/**
	 * �뵳�������
	 * 
	 * @return ActionForward
	 */
	public ActionForward rdsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();
		
		User user = getUser(request);
		
		String userType = user.getUserStatus();
		String userDep = user.getUserDep();
		String userName = user.getUserName();
		
		String doType = request.getParameter("doType");
		String tableName = "view_czxx_rdsq";
		String realTable = "czxx_rdsqb";
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// ѧԺ�û�����
		if("stu".equalsIgnoreCase(userType)){
			myForm.setXh(userName);
		}else if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		BeanUtils.copyProperties(model, myForm);

		// ����ɾ���뵳����
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xh||sqsj";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// �뵳����--> �뵳��������
		if ("zz".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			result = service.changeDylx(checkVal, "�뵳����", request);
		}

		// �뵳����ȼ�ת��
		if ("zhdj".equalsIgnoreCase(doType)) {
			String[] pkValue = myForm.getCheckVal();
			String zhdj = myForm.getZhdj();
			String zhsj = request.getParameter("zhsj");
			String pk = "xh||sqsj";
			String bjmc = "�뵳����";
			result = service.saveZhdj(pk, pkValue, bjmc, zhdj, zhsj);
			request.setAttribute("result", result);
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "sqsj" };

			if ("��".equalsIgnoreCase(myForm.getSfty())) {
				tableName = "view_czxx_yxtyrd";
			}
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		// ����ת���ȼ�
		myForm.setZhdj("�뵳����");
		// ��ʼ���б�
		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_rdsq.do");
		request.setAttribute("userType", userType);

		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("rdsqManage");
	}

	/**
	 * �뵳����ά��
	 * 
	 * @return ActionForward
	 */
	public ActionForward rdsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "���Ž��� - ֧������ - �뵳����";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_czxx_rdsq";
		String realTable = "czxx_rdsqb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "mzmc", "sqsj",
					"bz", "rxrq" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
		}

		// �뵳���� --> �뵳��������
		if ("zz".equalsIgnoreCase(doType)) {
			model.setLx("�뵳����");
			boolean result = service.changeDylx(model, request);
			request.setAttribute("result", result);
		}

		// �����뵳����
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xh", "sqsj", "bz" };
			pk = "xh||sqsj";
			pkValue = myForm.getXh() + myForm.getSqsj();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);
			// if (result) {
			// if (service.isYxty(model.getXh())) {
			// model.setLx("�뵳����");
			// result = service.changeDylx(model, request);
			// }
			// }
			request.setAttribute("result", result);
		}

		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("rdsqUpdate");
	}

	/**
	 * �뵳�������ӹ���
	 * 
	 * @return ActionForward
	 */
	public ActionForward jjfzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();
		
		User user = getUser(request);
		
		String userType = user.getUserStatus();
		String userDep = user.getUserDep();
		String userName = user.getUserName();
		String doType = request.getParameter("doType");
		String tableName = "view_sjxy_rdjjfzxx";
		String realTable = "rdjjfzxxb";
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// ѧԺ�û�����
		if ("stu".equalsIgnoreCase(userType)){
			myForm.setXh(userName);
		}else if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		String zzzt = myForm.getZzzt();
		if (Base.isNull(zzzt)) {
			myForm.setZzzt("yes");
		} else if ("all".equalsIgnoreCase(zzzt)) {
			myForm.setZzzt("");
		}

		BeanUtils.copyProperties(model, myForm);

		// ����ɾ���뵳��������
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||xh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// �뵳�������� --> ��չ����
		if ("zz".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			result = service.changeDylx(checkVal, "��������", request);
		}

		// �������ӵȼ�ת��
		if ("zhdj".equalsIgnoreCase(doType)) {
			String[] pkValue = myForm.getCheckVal();
			String zhdj = myForm.getZhdj();
			String zhsj = request.getParameter("zhsj");
			String pk = "xn||xq||xh";
			String bjmc = "��������";
			result = service.saveZhdj(pk, pkValue, bjmc, zhdj, zhsj);
			request.setAttribute("result", result);
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
					"xn", "xqmc", "kssj", "ztmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		// ����ת���ȼ�
		myForm.setZhdj("��������");
		// ��ʼ���б�
		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_jjfz.do");
		request.setAttribute("userType", userType);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("jjfzManage");
	}

	/**
	 * �뵳��������ά��
	 * 
	 * @return ActionForward
	 */
	public ActionForward jjfzUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "���Ž��� - ֧������ - �뵳��������";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_sjxy_rdjjfzxx";
		String realTable = "rdjjfzxxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "mzmc", "xn", "xq",
					"nd", "kssj", "zbmc", "xsccdm", "bz", "zzzt", "lwjjfzsj" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
		}

		// �뵳�������� --> Ԥ����Ա
		if ("zz".equalsIgnoreCase(doType)) {
			model.setLx("��������");
			boolean result = service.changeDylx(model, request);
			request.setAttribute("result", result);
		}

		// �����뵳��������
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
					"kssj", "bz", "lwjjfzsj" };
			if (!Base.isNull(myForm.getZzzt())) {
				onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
						"kssj", "bz", "zzzt", "lwjjfzsj" };
			}
			pk = "xn||xq||xh";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		// ���õ�����ϸ��Ϣ(����ѵ������)
		setDtxxInfo(rs, request);

		myForm.setXydm(rs.get("xydm"));
		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("jjfzUpdate");
	}

	/**
	 * ��չ�������
	 * 
	 * @return ActionForward
	 */
	public ActionForward fzdxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		User user = getUser(request);
		
		String userType = user.getUserStatus();
		String userDep = user.getUserDep();
		
		String doType = request.getParameter("doType");
		String tableName = "view_czxx_fzdx";
		String realTable = "czxx_fzdxb";
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// ѧԺ�û�����
		if("stu".equalsIgnoreCase(userType)){
			myForm.setXh(user.getUserName());
		}else if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		String zzzt = myForm.getZzzt();
		if (Base.isNull(zzzt)) {
			myForm.setZzzt("yes");
		} else if ("all".equalsIgnoreCase(zzzt)) {
			myForm.setZzzt("");
		}

		BeanUtils.copyProperties(model, myForm);

		// ����ɾ��Ԥ����Ա
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||xh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// ��չ���� --> Ԥ����Ա
		if ("zz".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			result = service.changeDylx(checkVal, "��չ����", request);
		}

		// ��չ����ȼ�ת��
		if ("zhdj".equalsIgnoreCase(doType)) {
			String[] pkValue = myForm.getCheckVal();
			String zhdj = myForm.getZhdj();
			String zhsj = request.getParameter("zhsj");
			String pk = "xn||xq||xh";
			String bjmc = "��չ����";
			result = service.saveZhdj(pk, pkValue, bjmc, zhdj, zhsj);
			request.setAttribute("result", result);
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
					"xn", "xqmc", "kssj", "ztmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		// ����ת���ȼ�
		myForm.setZhdj("��չ����");
		// ��ʼ���б�
		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_fzdx.do");
		request.setAttribute("userType", userType);
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

		String title = "���Ž��� - ��չ����";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_czxx_fzdx";
		String realTable = "czxx_fzdxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
			rs.put("jg", new CommService().getSydmc(rs.get("jg"), "/", "/"));
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "xn", "xq", "nd",
					"bz", "kssj", "jssj", "bz", "zzzt", "csrq", "jg", "mzmc" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
			rs.put("jg", new CommService().getSydmc(rs.get("jg"), "/", "/"));
		}

		// ��չ����--> Ԥ����Ա
		if ("zz".equalsIgnoreCase(doType)) {
			model.setLx("��չ����");
			boolean result = service.changeDylx(model, request);
			request.setAttribute("result", result);
		}

		// ����Ԥ����Ա
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "kssj",
					"jssj", "bz" };
			if (!Base.isNull(myForm.getZzzt())) {
				onezd = new String[] { "xn", "xq", "xh", "nd", "kssj", "jssj",
						"bz", "zzzt" };
			}
			pk = "xn||xq||xh";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		// ���õ�����ϸ��Ϣ(����ѵ������)
		setDtxxInfo(rs, request);

		myForm.setXydm(rs.get("xydm"));
		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("fzdxUpdate");
	}

	/**
	 * Ԥ����Ա����
	 * 
	 * @return ActionForward
	 */
	public ActionForward ybdyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();
		User user = getUser(request);
		
		String userType = user.getUserStatus();
		String userDep = user.getUserDep();
		String doType = request.getParameter("doType");
		String tableName = "view_sjxy_ybdyxx";
		String realTable = "ybdyxxb";
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// ѧԺ�û�����
		if("stu".equalsIgnoreCase(userType)){
			myForm.setXh(user.getUserName());
		}else if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		String zzzt = myForm.getZzzt();
		if (Base.isNull(zzzt)) {
			myForm.setZzzt("yes");
		} else if ("all".equalsIgnoreCase(zzzt)) {
			myForm.setZzzt("");
		}

		BeanUtils.copyProperties(model, myForm);

		// ����ɾ��Ԥ����Ա
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||xh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// Ԥ����Ա --> ��ʽ��Ա
		if ("zz".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			result = service.changeDylx(checkVal, "Ԥ����Ա", request);
		}

		// Ԥ����Ա�ȼ�ת��
		if ("zhdj".equalsIgnoreCase(doType)) {
			String[] pkValue = myForm.getCheckVal();
			String zhdj = myForm.getZhdj();
			String zhsj = request.getParameter("zhsj");
			String pk = "xn||xq||xh";
			String bjmc = "Ԥ����Ա";
			result = service.saveZhdj(pk, pkValue, bjmc, zhdj, zhsj);
			request.setAttribute("result", result);
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc",
					"xn", "xqmc", "kssj", "ztmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		// ����ת���ȼ�
		myForm.setZhdj("Ԥ����Ա");
		// ��ʼ���б�
		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_ybdy.do");
		request.setAttribute("userType", userType);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("ybdyManage");
	}

	/**
	 * Ԥ����Աά��
	 * 
	 * @return ActionForward
	 */
	public ActionForward ybdyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "���Ž��� - ֧������ - Ԥ����Ա";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_sjxy_ybdyxx";
		String realTable = "ybdyxxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "mzmc", "xn", "xq",
					"nd", "zbmc", "bz", "xsccdm", "kssj", "jssj", "zzlx", "bz",
					"zzzt","db","zb" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
		}

		// Ԥ����Ա --> ��ʽ��Ա
		if ("zz".equalsIgnoreCase(doType)) {
			model.setLx("Ԥ����Ա");
			boolean result = service.changeDylx(model, request);
			request.setAttribute("result", result);
		}

		// ����Ԥ����Ա
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
					"kssj", "jssj", "zzlx", "bz","db","zb" };
			if (!Base.isNull(myForm.getZzzt())) {
				onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
						"kssj", "jssj", "zzlx", "bz", "zzzt","db","zb" };
			}
			pk = "xn||xq||xh";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		// ���õ�����ϸ��Ϣ(����ѵ������)
		setDtxxInfo(rs, request);

		myForm.setXydm(rs.get("xydm"));
		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("ybdyUpdate");
	}

	/**
	 * ��ʽ��Ա����
	 * 
	 * @return ActionForward
	 */
	public ActionForward zsdyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = getUser(request);// �û�����

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		String doType = request.getParameter("doType");
		// �û����
		String userType = user.getUserStatus();
		// �û���
		String userName = user.getUserName();
		// �û���
		String userDep = user.getUserDep();

		String tableName = "view_sjxy_dyxx";
		String realTable = "dyxxb";
		boolean result = false;

		String[] colList = new String[] { "pk", "xh", "xm", "xb", "bjmc", "xn",
				"xqmc", "rdsj", "ztmc" };

		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);

		// ѧԺ�û�����
		if ("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		} else if ("stu".equalsIgnoreCase(userType)) {
			myForm.setXh(userName);
		}

		String zzzt = myForm.getZzzt();
		if (Base.isNull(zzzt)) {
			myForm.setZzzt("yes");
		} else if ("all".equalsIgnoreCase(zzzt)) {
			myForm.setZzzt("");
		}

		BeanUtils.copyProperties(model, myForm);

		// ����ɾ����ʽ��Ա
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||xh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				request.setAttribute("result", result);
			}
		} else if (!Base.isNull(doType) && "gxzjbxx".equalsIgnoreCase(doType)) {// ����Ա��Ϣ������������Ϣ
			result = service.gxzJbxx();
			request.setAttribute("result", result);
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {

			// topTr = SearchUtils.getTopTr(tableName, colList, null);
			rsArrList = service.getDyxxList(tableName, model, colList);

			request.setAttribute("searchTj", myForm.getSearchModel());
		} else {
			myForm.getPages().setMaxPage(1);
		}

		// ��ʼ���б�
		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_zsdy.do");
		request.setAttribute("userType", userType);
		FormModleCommon.commonRequestSet(request, tableName, realTable,
				rsArrList, topTr);

		RequestForm rForm = new RequestForm();

		// ��ҳ
		Pages pages = myForm.getPages();

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "1";
		commSetting.setStartNum(startNum);

		// ��ʾ������
		String showNum = "8";
		commSetting.setShowNum(showNum);
		// ===============ͨ������ end ============

		rForm.setColList(colList);
		rForm.setCommSetting(commSetting);
		rForm.setPages(pages);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);

		CommService commService = new CommService();

		commService.setRequestValue(rForm, user, request);

		if (Globals.XXDM_GDTYZYJSXY.equalsIgnoreCase(Base.xxdm)) {// �Ƿ�����-������������Ϣ
			request.setAttribute("sfxs_gxzjbxx", "��");
		}

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
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_sjxy_dyxx";
		String realTable = "dyxxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
					"xymc", "zydm", "zymc", "bjdm", "bjmc", "mzmc", "xn", "xq",
					"nd", "zbmc", "bz", "xsccdm", "rdsj", "zzsj", "ybdykssj",
					"ybdyjssj", "bz", "zzdw", "zzlx", "zzzt","dnzw" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
		}

		// ������ʽ��Ա
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
					"rdsj", "zzsj", "ybdykssj", "ybdyjssj", "bz", "zzdw",
					"zzlx","dnzw" };
			if (!Base.isNull(myForm.getZzzt())) {
				onezd = new String[] { "xn", "xq", "xh", "nd", "xsccdm",
						"rdsj", "zzsj", "ybdykssj", "ybdyjssj", "bz", "zzdw",
						"zzlx", "zzzt","dnzw" };
			}
			pk = "xn||xq||xh";
			pkValue = myForm.getXn() + myForm.getXq() + myForm.getXh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		// ���õ�����ϸ��Ϣ(����ѵ������)
		setDtxxInfo(rs, request);

		myForm.setXydm(rs.get("xydm"));
		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("zsdyUpdate");
	}

	/**
	 * ��ѵ��Ϣ����
	 * 
	 * @return ActionForward
	 */
	public ActionForward pxxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// HttpSession session = request.getSession();

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		String doType = request.getParameter("doType");
		String tableName = "view_czxx_dkxx";
		String realTable = "czxx_dkpxb";
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		// ����ɾ����ʽ��Ա
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "pxsj";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				if (result) {

				}
				request.setAttribute("result", result);
			}
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "pxmc", "pxsj", "pxdd",
					"cjrs" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		service.setList(myForm, request);
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

		String title = "���Ž��� - ֧������ - ������ѵ";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_dyxx";
		String realTable = "czxx_dkpxb";
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType) || "update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {

			String pxsj = Base.isNull(pkValue) ? request.getParameter("gzkssj")
					: pkValue;
			model.setPxsj(pxsj);

			pk = "pxsj";
			pkValue = pxsj;
			tableName = "czxx_dkpxb";
			String[] colList = new String[] { "pxmc", "pxdd", "pxsj", "pxnr",
					"bz" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
			rs.put("pxsj", pxsj);
			if ("".equalsIgnoreCase(rs.get("pxmc")) || null == rs) {
				rs.put("pxmc", myForm.getPxmc());
				rs.put("pxdd", myForm.getPxdd());
				rs.put("pxnr", myForm.getPxnr());
				rs.put("bz", myForm.getBz());
			}

			if (!Base.isNull(pxsj)) {
				colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
						"zymc", "bjmc", "dkcj" };
				tableName = "view_czxx_dksjb";
				List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
						tableName, colList, null);
				ArrayList<String[]> rsList = service.getDyxxList(tableName,
						model, colList);
				if (rsList != null && rsList.size() > 0) {
					request.setAttribute("topTr", topTr);
					request.setAttribute("rsList", rsList);
					request.setAttribute("rsNum", rsList.size());
				}
			}
		}

		// ������ʽ��Ա
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "pxmc", "pxsj", "pxdd", "pxnr",
					"bz" };
			pk = "pxsj";
			pkValue = myForm.getPxsj();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveDyxx(saveForm, model, request);

			if (result) {
				String[] checkVal = myForm.getPxmdxh();
				String[] pxmdxh = new String[checkVal.length];
				String pxsj = myForm.getPxsj();

				if (checkVal != null && checkVal.length > 0) {

					realTable = "czxx_dkpxmdb";
					pk = "pxmdxh||pxsj";
					String[] arrzd = new String[] { "pxmdxh", "dkcj" };
					onezd = new String[] { "pxsj" };

					for (int i = 0; i < checkVal.length; i++) {
						pxmdxh[i] = checkVal[i];
						checkVal[i] = checkVal[i] + pxsj;
					}

					saveForm = new SaveForm();
					saveForm.setTableName(realTable);
					saveForm.setPk(pk);
					saveForm.setPkValue(checkVal);
					saveForm.setArrzd(arrzd);
					saveForm.setOnezd(onezd);

					model.setPxmdxh(pxmdxh);
					result = service.saveDyxx(saveForm, model);
				}
			}
			request.setAttribute("result", result);
		}

		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("pxxxUpdate");
	}

	/**
	 * ������������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward dkmdManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		String tableName = "view_czxx_dkpxmdb";
		String realTable = "czxx_dkpxmdb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		boolean reslut = false;

		// ����������
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			String pxsj = request.getParameter("gzkssj");

			if (checkVal != null && checkVal.length > 0) {

				String pk = "pxmdxh||pxsj";
				String[] arrzd = new String[] { "pxmdxh" };
				String[] onezd = new String[] { "pxsj" };
				String[] pkValue = new String[checkVal.length];
				for (int i = 0; i < pkValue.length; i++) {
					pkValue[i] = checkVal[i] + pxsj;
				}

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(pkValue);
				saveForm.setArrzd(arrzd);
				saveForm.setOnezd(onezd);

				model.setPxmdxh(checkVal);
				model.setPxsj(pxsj);

				reslut = service.saveDyxx(saveForm, model);
				request.setAttribute("result", reslut);
			}
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| reslut) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "pxcs" };
			model.setPxsj(null);
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "dtjs_dkmd.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// �����б�ֵ
		service.setList(myForm, request);
		//tableName = "view_czxx_dkpxmdb";
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("dkmdManage");
	}

	/**
	 * ������ѵʱ��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward dkmdUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String tableName = "view_czxx_dksjb";
		String realTable = "czxx_dkpxmdb";
		String title = "���Ž��� - ����ʱ��";
		String xh = request.getParameter("pk");
		String doType = request.getParameter("doType");

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		model.setXh(xh);

		String[] colList = new String[] { "pk", "xm", "xb", "xymc", "zymc",
				"bjmc", "pxsj", "dkcj" };

		// ����������ѵʱ��
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "id";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				boolean result = service.delDyxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,
				colList, null);
		ArrayList<String[]> rsList = service.getDyxxList(tableName, model,
				colList);

		if (rsList != null && rsList.size() > 0) {
			request.setAttribute("topTr", topTr);
			request.setAttribute("rsList", rsList);
			request.setAttribute("rsNum", rsList.size());
		}

		request.setAttribute("title", title);
		request.setAttribute("pk", xh);

		return mapping.findForward("dkmdUpdate");
	}

	/**
	 * ѧ��������Ϣ����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		String tableName = request.getParameter("tableName");
		String lx = request.getParameter("lx");
		String realTable = "";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);
		String zd = request.getParameter("zd");
		String va = request.getParameter("va");
		model.setZd(zd);
		model.setZdValue(va);

		if (userType.equalsIgnoreCase("xy")) {
			model.setXydm(userDep);
			myForm.setXydm(userDep);
		}
		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc" };
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "bjlh_gygl_zsxx.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("lx", lx);
		request.setAttribute("zd", zd);
		request.setAttribute("va", va);

		if (rs != null) {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		// �����б�ֵ
		service.setList(myForm, request);
		// if (session.getAttribute("fdyQx").equals(true)
		// || "true".equalsIgnoreCase(session.getAttribute("fdyQx")
		// .toString())) {
		// request.setAttribute("bjList", Fdypd.getFdybjList(userName));
		// }
		return mapping.findForward("xsxxManage");
	}

	/**
	 * ˼��㱨����
	 * 
	 * @return ActionForward
	 */
	public ActionForward sxhbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String tableName = "view_czxx_sxhb";
		String realTable = "czxx_sxhbb";
		boolean result = false;

		// �ж��Ƿ�ѧ���û�
		if ("stu".equalsIgnoreCase(userType)) {
			myForm.setXh(userName);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		// ����ɾ��˼��㱨
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "id";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delDyxx(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "id", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "xn", "xqmc", "tjsj", "wjm", "scdz" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getDyxxList(tableName, model, colList);
		}

		service.setList(myForm, request);
		request.setAttribute("path", "dtjs_sxhb.do");
		request.setAttribute("userType", userType);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("sxhbManage");
	}

	/**
	 * ˼��㱨ά��
	 * 
	 * @return ActionForward
	 */
	public ActionForward sxhbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "���Ž��� - ˼��㱨";
		String doType = request.getParameter("doType");
		String pk = "";
		String pkValue = request.getParameter("pk");
		String tableName = "view_czxx_sxhb";
		String realTable = "czxx_sxhbb";
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		doType = Base.isNull(doType) ? "add" : doType;

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();
		DyxxModel model = new DyxxModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		if ("add".equalsIgnoreCase(doType)) {

			String xh = "";
			// �жϵ�½���Ƿ�ѧ��
			if ("stu".equalsIgnoreCase(userType)) {
				xh = userName;
			} else {
				xh = request.getParameter("xh");
			}
			rs = service.getStuInfo(xh);
		}

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			pk = "id";
			String[] colList = new String[] { "id", "xh", "xm", "xb", "nj",
					"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "xn", "xq",
					"tjsj", "wjm", "bz", "scdz" };
			rs = service.getDyxxInfo(tableName, pk, pkValue, colList);
		}

		// ����˼��㱨
		if ("save".equalsIgnoreCase(doType)) {

			// �����ļ��ϴ�
			FormFile file = myForm.getUploadFile();
			String filePath = request.getParameter("scdz");
			String fName = "";
			if (file != null) {
				String dir = "/upload/dtjs";
				File f = new File(dir);
				if (!f.exists()) {
					f.mkdirs();
				}
				Timestamp date = new Timestamp(System.currentTimeMillis());
				String dateStr = date.toString().substring(0, 19);
				dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
						.replaceAll(":", "");
				int size = file.getFileSize();
				if (size < 10485760 && size != 0) {
					fName = dateStr + file.getFileName();
					InputStream in = file.getInputStream();
					filePath = dir + "/" + fName;
					OutputStream out = new FileOutputStream(filePath);
					int bytesRead = 0;
					byte[] buffer = new byte[size];
					while ((bytesRead = in.read(buffer, 0, size)) != -1) {
						out.write(buffer, 0, bytesRead);
					}
					out.close();
					in.close();
				} else {
					request.setAttribute("alert", "cannot");
				}
			}

			myForm.setScdz(filePath);
			myForm.setTjr(userName);

			BeanUtils.copyProperties(model, myForm);

			String[] onezd = new String[] { "xh", "xn", "xq", "tjr", "tjsj",
					"wjm", "scdz", "bz" };
			pk = "id";
			pkValue = Base.isNull(myForm.getId()) ? "" : myForm.getId();

			boolean result = true;

			if (!Base.isNull(pkValue)) {
				result = service.fileDel(realTable, "scdz", pk, pkValue);
			}

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			result = service.saveDyxx(saveForm, model, request);
			request.setAttribute("result", result);
		}

		myForm.setXydm(rs.get("xydm"));
		service.setList(myForm, request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("rs", rs);

		return mapping.findForward("sxhbUpdate");
	}

	/**
	 * @describe ������ѡ���ļ�
	 * @author luojw
	 * @throws Exception
	 */
	public ActionForward downLoadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		if(!"10335".equals(Base.xxdm)){
		byte b[] = new byte[500];
		String dir = DealString.toGBK(request.getParameter("dir"));
		String filename = request.getParameter("fileName");

		if (!Base.isNull(filename)) {
			dir = servlet.getServletContext().getRealPath("WEB-INF/upLoad")
					+ "/" + filename;
			;
		} else {
			filename = dir.substring(27, dir.length());
		}

		File fileload = new File(dir);
		if(!fileload.exists()){
			String msg = "�������ص��ļ������ڣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
			
		}else{
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ DealString.toUtf8String(filename));
			InputStream in;
			try {
				in = new FileInputStream(fileload);
				in = new BufferedInputStream(in);
				while ((in.read(b)) != -1) {
					response.getOutputStream().write(b);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		}else{
			return null;
		}
				
		return null;
	}

	/**
	 * @describe ɾ�����ϴ��ļ�
	 * @author luojw
	 * @throws Exception
	 */
	public ActionForward fileDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CzxxDtjsForm myForm = (CzxxDtjsForm) form;
		DyxxService service = new DyxxService();

		String doType = request.getParameter("doType");
		String pk = "id";
		String pkValue = myForm.getId();
		pkValue = Base.isNull(pkValue) ? request.getParameter("id") : pkValue;
		String forward = request.getParameter("forward");
		String realTable = "czxx_sxhbb";

		if (!Base.isNull(pkValue)) {
			if (!Base.isNull(doType)) {
				forward += "&doType=" + doType;
			}
			forward += "&pk=" + pkValue;
			service.fileDel(realTable, "scdz", pk, pkValue);
		}

		request.setAttribute("doType", doType);

		return new ActionForward(forward, false);
	}

	/**
	 * @describe �������ӵǼǱ����
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @throws Exception
	 */
	public ActionForward jjfzdjbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkVal = request.getParameter("pk");
		String pk = "xn||xq||xh";
		DyxxService service = new DyxxService();
		HashMap<String, String> map = service.queryRdsqjbxx(pk, pkVal);
		int qsn = 1990;// ��ʼ��
		int dqn = Integer.parseInt(GetTime.getNowYear());// ��ǰ��
		String rxrq = map.get("rxrq");// ��ѧʱ��

		qsn = Integer.parseInt(Base.isNull(rxrq) ? map.get("nj") : rxrq
				.substring(0, 4));
		int flag = 0;
		String[] xqArr = { "01", "02" };
		int num = 0;
		while (qsn <= dqn && flag < 4) {
			String xn = qsn + "-" + (qsn + 1);
			qsn++;
			flag++;
			for (int i = 0; i < 2; i++) {
				request.setAttribute("xq" + (++num), service.queryXsxgxxByXq(
						map.get("xh"), xn, xqArr[i]));// ��ѧ�ڲ�ѯѧ�����������Ϣ
			}
		}

		map.put("pxsjjcj", service.getPxcjjsj(map.get("xh")));// ���뵳У��ѵʱ�估�ɼ�
		request.setAttribute("rs", map);
		return mapping.findForward("jjfzdjb");
	}

	/**
	 * ���õ�����ϸ��Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void setDtxxInfo(HashMap<String, String> rs,
			HttpServletRequest request) {

		String xh = rs.get("xh");
		String tableName = "";
		String xxdm = Base.xxdm;

		DyxxService service = new DyxxService();

		if (rs != null && rs.size() != 0) {

			// ��������
			String[] colList = new String[] { "xn", "xqmc" };
			String[] zd = new String[] { "xxsh", "xh" };
			String[] zdz = new String[] { "ͨ��", xh };

			if (!Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
				zd = new String[] { "xh" };
				zdz = new String[] { xh };
			}

			tableName = "view_yxtyxxb";
			List<HashMap<String, String>> topTnty = SearchUtils.getTopTr(
					tableName, colList, null);
			ArrayList<String[]> tyList = service.getDyxxList(tableName, zd,
					zdz, colList);

			if (tyList != null && tyList.size() > 0) {
				request.setAttribute("topTnty", topTnty);
				request.setAttribute("tyList", tyList);
				request.setAttribute("tyNum", tyList.size());
			}

			// ˼��㱨
			colList = new String[] { "xn", "xqmc", "wjm", "scdz" };
			zd = new String[] { "xh" };
			zdz = new String[] { xh };
			tableName = "view_czxx_sxhb";
			List<HashMap<String, String>> topSxhb = SearchUtils.getTopTr(
					tableName, colList, null);
			ArrayList<String[]> hbList = service.getDyxxList(tableName, zd,
					zdz, colList);

			if (hbList != null && hbList.size() > 0) {
				request.setAttribute("topSxhb", topSxhb);
				request.setAttribute("hbList", hbList);
				request.setAttribute("hbNum", hbList.size());
			}

			// ������ѵ
			colList = new String[] { "pxmc", "pxsj", "dkcj" };
			zd = new String[] { "xh" };
			zdz = new String[] { xh };
			tableName = "view_czxx_dksjb";
			List<HashMap<String, String>> topDkpx = SearchUtils.getTopTr(
					tableName, colList, null);
			ArrayList<String[]> pxList = service.getDyxxList(tableName, zd,
					zdz, colList);

			if (pxList != null && pxList.size() > 0) {
				request.setAttribute("topDkpx", topDkpx);
				request.setAttribute("pxList", pxList);
				request.setAttribute("pxNum", pxList.size());
			}
		}
	}
}
