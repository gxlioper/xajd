package xgxt.xszz.comm;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommForm;
import xgxt.comm.FileManage;
import xgxt.comm.xml.XMLReader;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.xszz.XszzService;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.exp.XszzExpService;
import xgxt.xszz.comm.xgsz.XszzXgszService;
import xgxt.xszz.comm.xmtj.XszzXmtjService;
import xgxt.xszz.tjgy.XszzPrintService;
import xgxt.xszz.zgdzdx.synData.SynDataService;

import com.zfsoft.basic.BasicAction;

import common.Globals;
import common.XszzXmdm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ͨ�ð汾ѧ������-action��
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

public class XszzCommAction extends BasicAction {

	/**
	 * ѧ������_��Ϣ_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward msgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("msgManage");
	}

	/**
	 * ѧ������_�ļ��ϴ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fileUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		String zd = request.getParameter("zd");
		String filePath = "";
		String doType = request.getParameter("doType");
		String message = "";

		if ("save".equalsIgnoreCase(doType)) {
			filePath = service.upLoadFile(request, myForm.getUploadFile(),
					"xszz");
			if (!Base.isNull(filePath)) {

				message = "�ϴ��ɹ�";
			}
		}

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "zd" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { zd };

		rForm.setDoType(doType);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		return mapping.findForward("fileUpload");
	}

	public ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzTyForm myForm = (XszzTyForm) form;

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
			request.setAttribute("filePath", filePath);
			request.setAttribute("message", "�ϴ��ɹ�");
		}
		return mapping.findForward("uploadFile");
	}

	/**
	 * @describe ������ѡ���ļ�
	 * @author luojw
	 * @throws Exception
	 */
	public ActionForward downLoad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

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
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(filename));
		InputStream in = new FileInputStream(fileload);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
	}

	/**
	 * @describe ɾ�����ϴ��ļ�
	 * @author luojw
	 * @throws Exception
	 */
	public void fileDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = null;
		XszzTyForm myForm = (XszzTyForm) form;
		XszzCommService service = new XszzCommService();

		String doType = request.getParameter("doType");
		String pk = "xh||sqsj";
		String pkValue = myForm.getSave_xh() + myForm.getSave_sqsj();
		String realTable = "xszz_knsb";

		if (!Base.isNull(pkValue)) {
			service.fileDel(realTable, "scdz", pk, pkValue);
		}

		request.setAttribute("doType", doType);
		JSONObject obj = new JSONObject();
		obj.put("msg", "ɾ���ɹ�");
		out.flush();
		out.close();
	}

	/**
	 * ѧ������_��Ŀά��_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// ����Ա
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// ������
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// ��ͼ��
		String tableName = "view_xszz_comm_xmwh";
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xszz_comm_xmwh");
		// ����
		String realTable = "xszz_zzxmb";
		// ����·��
		String path = "yes".equals(myForm.getIskns()) ? "kns_jbsz.do"
				: "xszz_xgsz_xmwh.do";
		// ��ǰѧ��
		String xn = Base.currXn;
		myForm.setQueryequals_xn(xn);
		// ��ǰѧ��
		String xq = Base.currXq;
		myForm.setQueryequals_xq(xq);
		// ��ǰ���
		String nd = Base.currNd;
		myForm.setQueryequals_nd(nd);
		// ��ʾ��Ϣ
		String message = "";
		// ģ������
		String mklx = (String) session.getAttribute("mklx");
		myForm.setMklx(mklx);
		if (!Base.isNull(mklx)) {
			tableName = "pj".equalsIgnoreCase(mklx) ? "xg_view_xszz_pjpy_xmwh"
					: "xg_view_xszz_xmwh";
			path += "?mklx=" + mklx;
		}
		// =================end==================

		// ==================��½�û���� ==================
		if (bzrQx || fdyQx || "stu".equalsIgnoreCase(userType)) {
			String msg = "��ģ��ֻ����"+Base.YXPZXY_KEY+"����ѧУ�û����в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================end ===================

		// ==================ִ��ɾ������ ==================
		if ("xmDel".equalsIgnoreCase(doType)) {
			boolean result = service.delXmxgInfo(myForm);
			if (result) {
				this.deleteOperation(request, realTable);
			}
			message = result ? "�����ɹ�" : "����ʧ��";
		}
		// =================end ===================

		// ==================ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {
			// ��Ŀ����
			String[] zzxmdm = myForm.getZzxmdm();
			if (zzxmdm != null && zzxmdm.length > 0) {
				boolean result = service.updateKgzt(myForm);
				message = result ? "�����ɹ�" : "����ʧ��";
			} else {
				message = "����ȷ��������Ŀ�Ŀ���״̬!";
			}
		}
		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		// if (search) {
		String[] outputColumn = { "pk", "mrxm", "xmdm", "xmmc", "xmlb", "sqzq",
				"pdsj", "sfje", "sffj", "jelx", "shjb", "rskz", "kgzt" };

		// �����������ò�ѯ��Χ
		request.setAttribute("annexTerm", new XszzService()
				.getFlowControlSql(myForm));
		this.selectPageDataByPagination(request, myForm, "", tableName,
				outputColumn);
		// }
		// =================end ===================

		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			outputColumn = null;
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "message" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { message };

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", service.getXqMc(Base.currXq));
		request.setAttribute("nd", Base.currNd);
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "xmwh");
		// =================end ===================

		return mapping.findForward("xmwhManage");
	}

	/**
	 * ѧ������_��Ŀά��_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xmwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = "view_xszz_comm_xmwh";
		// ����
		String realTable = "xszz_zzxmb";
		// ����·��
		String path = "xszz_xgsz_xmwh.do";
		// ����ֵ
		String pkValue = request.getParameter("pk");
		myForm.setPkValue(pkValue);
		// ��Ŀ����
		String xmdm = myForm.getXmdm();
		xmdm = Base.isNull(xmdm) ? service.getXmbh(realTable, "xmdm") : xmdm;
		myForm.setXmdm(xmdm);
		// ��ʾ��Ϣ
		String message = "";
		// ��Ŀ��Ϣ
		HashMap<String, String> rs = service.getXmwhfo(myForm, tableName);
		// ģ������
		String mklx = (String) session.getAttribute("mklx");
		myForm.setMklx(mklx);
		if (!Base.isNull(mklx)) {
			path += "?mklx=" + mklx;
		}
		// ��������
		String savedRskz = "";
		// ��Ŀ����
		String savedXmdm = "";
		// ��Ŀ����
		String savedKzjb = "";
		// =================end==================

		// ===================�����Ŀ�����⴦�� ======================
		String xmb = rs.get("xmb");
		String knsTj = "yes";
		// /��ͥ����������������
		if ("jtqkdcb".equalsIgnoreCase(xmb)
				|| "xszz_knsb".equalsIgnoreCase(xmb)) {
			knsTj = "no";
		}
		// =================end==================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			// ���������Ϣ
			HashMap<String, String> tjMap = this.getValueMap(request,
					PRIFIX_SAVE);
			boolean result = service.saveXmwh(myForm, tjMap, request);
			message = result ? "�����ɹ�" : "����ʧ��";

			savedXmdm = myForm.getXmdm();
			savedRskz = myForm.getRskz();
			savedKzjb = myForm.getKzjb();
		} else {
			savedXmdm = rs.get("xmdm");
			savedRskz = rs.get("rskz");
			savedKzjb = rs.get("kzjb");
			
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "message", "xmdm", "knsTj", "savedXmdm",
				"savedRskz", "savedKzjb" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { message, xmdm, knsTj, savedXmdm,
				savedRskz, savedKzjb };

		rForm.setRs(rs);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "xmwh");
		// =================end ===================

		return mapping.findForward("xmwhUpdate");
	}

	/**
	 * ѧ������_��������_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;
		User user = getUser(request);

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = user.getUserType();
		// �û����ڲ���
		String userDep = user.getUserDep();
		// ��ͼ��
		String tableName = "";
		// ����
		String realTable = "xszz_zzxmb";
		// ����·��
		String path = "yes".equals(myForm.getIskns()) ? "kns_rssz.do"
				: "xszz_xgsz_rssz.do";
		// ��ǰѧ��
		String xn = Base.currXn;
		myForm.setQueryequals_xn(xn);
		// ��ǰѧ��
		String xq = Base.currXq;
		myForm.setQueryequals_xq(xq);
		// ��ǰ���
		String nd = Base.currNd;
		myForm.setQueryequals_nd(nd);
		// ��Ŀ����
		String xmdm = myForm.getQueryequals_xmdm();

		String cz = request.getParameter("cz");

		request.setAttribute("cz", cz);

		if (Base.isNull(xmdm)) {
			xmdm = request.getParameter("xmdm");
		}
		myForm.setXmdm(xmdm);
		// ���Ƽ���
		String kzjb = myForm.getKzjb();

		if (Base.isNull(kzjb)) {
			kzjb = request.getParameter("kzjb");
		}
		myForm.setKzjb(kzjb);

		// ��������ID
		String rsszId = "";
		// ��ʾ��Ϣ
		String message = "";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// ģ������
		String mklx = request.getParameter("mklx");
		myForm.setMklx(mklx);
		if (!Base.isNull(mklx)) {
			path += "?mklx=" + mklx;
		}
		// =================end==================

		// ================= �û����ͼ�� ==================
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		// =================end==================

		// ==================ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {
			// �жϱ��������Ƿ񳬹�����
			message = service.isCgRssx(myForm);
			if (Base.isNull(message)) {
				boolean result = service.updateRssz(myForm);
				message = result ? "�����ɹ�" : "����ʧ��";
			}
		}
		// =================end ===================

		// =================��ʼ�� ===================
		if (("".equalsIgnoreCase(doType) || null == doType) && !search) {
			myForm = service.initXmdm(myForm, "rssz");
			// ���ݿ��Ƽ������ֱ�ͷ
			kzjb = myForm.getKzjb();
			if (myForm.getXmdm() == null
					|| "".equalsIgnoreCase(myForm.getXmdm())) {
				request.setAttribute("yhInfo", "�Բ�����ʱû����Ҫ���������������Ŀ����ȷ��!");
				return new ActionForward("/yhInfo.do", false);
			}
			search = true;
		}

		request.setAttribute("xmdm", myForm.getXmdm());
		// =================��ѯ ===================
		if (search) {

			// ���ݿ��Ƽ������ֱ�ͷ
			if ("ѧԺ".equalsIgnoreCase(kzjb)) {
				rsszId = "rssz_xy";
			} else if ("רҵ".equalsIgnoreCase(kzjb)) {
				rsszId = "rssz_zy";
			} else if ("�༶".equalsIgnoreCase(kzjb)) {
				rsszId = "rssz_bj";
			}

			// ��ͷ
			List<HashMap<String, String>> topTr = service.getTopTr(rsszId);
			// ����
			ArrayList<String[]> rs = service.getRsszList(myForm);
			// ��Ŀ������������޵ȣ�
			HashMap<String, String> xmInfo = service.getXmrsInfo(myForm);
			request.setAttribute("xmInfo", xmInfo);
			request.setAttribute("rsNum", rs.size());
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "message" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { message };

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ================��ǰѧ�ꡢѧ�ڡ����==================
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", service.getXqMc(Base.currXq));
		request.setAttribute("nd", Base.currNd);
		// =======================end========================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "rssz");
		// =================end ===================

		// �����ʽ����
		String widthType = request.getAttribute("widthType") != null ? request
				.getAttribute("widthType").toString() : myForm.getWidthType();
		if ("dbsx".equalsIgnoreCase(widthType)) {
			// �Ӵ����������
			request.setAttribute("widthType", "dbsx");
		} else if ("kjfs".equalsIgnoreCase(widthType)) {
			// �ӿ�ݷ�ʽ����
			request.setAttribute("widthType", "kjfs");
		} else {
			// ����Ŀ��˽���
			request.setAttribute("widthType", "xmsh");
		}

		// ҳ����ʾ��¼��
		request.setAttribute("pageSize", myForm.getPages().getPageSize());

		request.setAttribute("kzjb", kzjb);

		return mapping.findForward("rsszManage");
	}

	/**
	 * ѧ������_��������_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward rsszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_xgsz_rssz.do";
		// /��ͼ
		String tableName = "view_xszz_comm_xmwh";
		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		// ��ǰѧ��
		String xn = Base.currXn;
		// ��ǰѧ��
		String xq = Base.currXq;
		// ��ǰ���
		String nd = Base.currNd;
		// ��ʾ��Ϣ
		String message = "";
		// ģ������
		String mklx = (String) session.getAttribute("mklx");
		myForm.setMklx(mklx);
		if (!Base.isNull(mklx)) {
			path += "?mklx=" + mklx;
		}
		// =================end==================

		// ===================ִ�в鿴�������� ======================
		String pk = "xmdm";
		String pkValue = xmdm;
		String[] colList = new String[] { "xmdm", "kzjb", "xmsm", "rssx" };
		HashMap<String, String> rs = service.getXszzInfo(tableName, pk,
				pkValue, colList);

		rs.put("xn", xn);
		rs.put("xq", xq);
		rs.put("nd", nd);
		rs.put("fpfs", "����");
		// =================end ===================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			boolean resault = service.saveRssz(myForm);
			message = resault ? "����ɹ�" : "����ʧ��,����������Ŀ���ޣ���ȷ�ϣ�";
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "message" };
		String[] qtzdz = new String[] { message };

		rForm.setRs(rs);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "rssz");
		// =================end ===================

		return mapping.findForward("rsszUpdate");
	}

	/**
	 * ѧ������_��Ŀ����_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// �û���
		String userName = (String) session.getAttribute("userName");
		myForm.setXh(userName);
		// �û����ڲ���
		String userDep = (String) session.getAttribute("userDep");
		// ��ͼ��
		String tableName = "";
		// ����
		String realTable = "xszz_zzxmb";
		// ����·��
		String path = "yes".equals(myForm.getIskns()) ? "kns_xmsq.do"
				: "xszz_xscz_xmsq.do";
		// ��ǰѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);
		// ��ǰѧ��
		String xq = Base.currXq;
		myForm.setXq(xq);
		// ��ǰ���
		String nd = Base.currNd;
		myForm.setNd(nd);
		// ��ǰʱ��
		String sqsjCn = service.getNowTime("YYYY��MM��DD��");
		String sqsj = service.getNowTime("YYYYMMDD");
		myForm.setSqsj(sqsj);
		myForm.setSqsjCn(sqsjCn);
		// ��ʾ��Ϣ
		String message = "";
		// ������Ϣ
		String msg = "";
		// ��Ŀ�б�
		List<HashMap<String, String>> rsList = null;
		// =================end==================

		// ==================��½�û���� ==================
		if (!"stu".equalsIgnoreCase(userType)) {
			msg = "��ģ��ֻ����ѧ���û����в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================end ===================

		// ==================Ĭ��չ�����е�½�����ʸ��������Ŀ ==================
		if ("stu".equalsIgnoreCase(userType)) {
			myForm.setXmdm(null);
			// ��ͷ
			List<HashMap<String, String>> topTr = service.getTopTr("xmsq");
			rsList = service.getXmsqList(myForm);

			request.setAttribute("topTr", topTr);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "message", "msg" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { message, msg };

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setUserDep(userDep);
		rForm.setUserName(userName);
		rForm.setRsList(rsList);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "sqsh");
		// =================end ===================

		return mapping.findForward("xmsqManage");
	}

	/**
	 * ѧ������_��Ŀ����_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xmsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û���
		String userName = (String) session.getAttribute("userName");
		myForm.setXh(userName);
		// ����
		String realTable = "xszz_zzxmb";
		// ����·��
		String path = "xszz_xscz_xmsq.do";
		// ��ǰѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);
		// ��ǰѧ��
		String xq = Base.currXq;
		String xqmc = Base.getDqxqmc();
		myForm.setXq(xq);
		// ��ǰ���
		String nd = Base.currNd;
		myForm.setNd(nd);
		// ��ǰʱ��
		String sqsj = request.getParameter("sqsj");
		sqsj = Base.isNull(sqsj) ? service.getNowTime("YYYYMMDD") : sqsj;
		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		xmdm = Base.isNull(xmdm) ? request.getParameter("save_xmdm") : xmdm;
		myForm.setPkValue(xmdm);
		myForm.setXmdm(xmdm);
		// ģ������
		String mklx = request.getParameter("mklx");
		// �Ƿ�������
		String ysq = request.getParameter("ysq");
		// =================end==================

		// ===================�����Ŀ�����Ϣ ======================
		HashMap<String, String> xmInfo = service.getXmxgInfo(myForm);

		// ��������
		String sqzq = xmInfo.get("sqzq");
		// ����ʱ��
		String pdsj = xmInfo.get("pdsj");
		// ����ʱ��Ϊǰ��
		if ("ǰ��".equalsIgnoreCase(pdsj)) {
			HashMap<String, String> befInfo = service.getBeforeXnXqNd(sqzq,
					pdsj, myForm);
			xn = befInfo.get("xn");
			xq = befInfo.get("xq");
			xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);
			nd = befInfo.get("nd");
		}
		xmInfo.put("xn", xn);
		xmInfo.put("xq", xq);
		xmInfo.put("xqmc", xqmc);
		xmInfo.put("nd", nd);
		xmInfo.put("sqsj", sqsj);
		xmInfo.put("xh", userName);
		xmInfo.put("ysq", ysq);

		realTable = xmInfo.get("xmb");
		String mrxm = xmInfo.get("mrxm");
		// ------2010.9.27 lr------
		myForm.setXn(xn);
		myForm.setXq(xq);
		myForm.setNd(nd);
		// --------end------
		myForm.setMrxm(mrxm);
		myForm.setXmb(realTable);

		// ��Ŀ�����б�
		List<HashMap<String, Object>> xmnrList = service.getXmSqNrList(myForm);
		// ��Ŀ�����б�
		List<HashMap<String, String>> xmfjqkList = service
				.getXmfjqkList(myForm);
		request.setAttribute("xmnrList", xmnrList);
		request.setAttribute("xmfjqkList", xmfjqkList);

		boolean xspjpy = ("5004".equals(xmdm) || "1001".equalsIgnoreCase(xmdm))
				&& Boolean.valueOf(XMLReader.getFlowControl("xszz", "xspjxx"));
		// =================end ===================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {

			// ִ���������
			if ("sq".equalsIgnoreCase(mklx)) {

				this.insertOperation(request, realTable);

				// ---------2011.7.6 qph----����ɹ����ٱ������״̬------------------------
				boolean result = (Boolean) request.getAttribute("result");

				// ������Ϣȥ״̬��
				if (result) {
					HashMap<String, String> map = this.getValueMap(request,
							PRIFIX_SAVE);
					service.addZtInfo(map);
				}

			} else if ("xg".equalsIgnoreCase(mklx)) {
				// ִ���޸Ĳ���
				this.updateOperation(request, realTable);

			}

			boolean knsdl = "yes".equals(myForm.getIskns())
					&& "xszz_knsb".equals(realTable)
					&& Boolean.valueOf(XMLReader
							.getFlowControl("xszz", "knsdl"))
					&& Boolean.valueOf(XMLReader.getFlowControl("xszz",
							"jtqkdc"));
			// �������϶���Ϊ����ģ�飬�����������϶���Ŀ������˼�ͥ�������
			if (knsdl) {
				service.saveJtqkdcFromKns(myForm.getSave_xh(), myForm
						.getSave_sqsj());
			}

			// ��ͥ���
			if ("jtqkdcb".equalsIgnoreCase(realTable) || knsdl) {
				String message = service.saveJtcy(myForm);
				if (!Base.isNull(message)) {
					request.setAttribute("message", message);
				}
			}
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] {};
		String[] qtzdz = new String[] {};

		rForm.setMklx(mklx);
		rForm.setDoType(doType);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		request.setAttribute("xmInfo", xmInfo);
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		request.setAttribute("xspjpy", xspjpy);
		request.setAttribute("jxjList", service.getPjpyInfo("01", userName));
		request.setAttribute("rychList", service.getPjpyInfo("02", userName));
		service.setList(myForm, request, "sqsh");
		// =================end ===================

		return mapping.findForward("xmsqUpdate");
	}


	/**
	 * ѧ������_��Ŀͳ��_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// �û���
		String userName = (String) session.getAttribute("userName");
		myForm.setZgh(userName);
		// ����ԱȨ��
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// ������Ȩ��
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// �û����ڲ���
		String userDep = (String) session.getAttribute("userDep");
		myForm.setUserDep(userDep);

		if ("stu".equals(userType)) {
			request.setAttribute("yhInfo", "�Բ�������Ȩ���ʴ�ҳ!");
			return new ActionForward("/yhInfo.do", false);
		}

		// ��ͼ��
		String tableName = "";
		// ����
		String realTable = "xszz_zzxmb";
		// ����·��
		String path = "yes".equals(myForm.getIskns()) ? "kns_xmsh.do"
				: "xszz_jscz_xmsh.do";
		// ��ǰѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);
		// ��ǰѧ��
		String xq = Base.currXq;
		myForm.setXq(xq);
		// ��ǰ���
		String nd = Base.currNd;
		myForm.setNd(nd);
		// ��ǰʱ��
		String sqsjCn = service.getNowTime("YYYY��MM��DD��");
		String sqsj = service.getNowTime("YYYYMMDD");
		myForm.setSqsj(sqsj);
		myForm.setSqsjCn(sqsjCn);
		// ��ʾ��Ϣ
		String message = "";
		// ������Ϣ
		String msg = "";
		// ��Ŀ�б�
		List<HashMap<String, String>> rsList = null;
		// ģ������
		String mklx = (String) session.getAttribute("mklx");
		myForm.setMklx(mklx);
		if (!Base.isNull(mklx)) {
			path += "?mklx=" + mklx;
		}
		// =================end==================

		// ==================��½�û���� ==================
		if ("stu".equalsIgnoreCase(userType)) {
			msg = "��ģ�鲻����ѧ���û����в�������ȷ�ϣ�";
		} else {
			// ��½�û�����
			String lx = "";

			if (bzrQx && fdyQx) {// �����μ渨��Ա
				lx = "jd";
			} else if (fdyQx) {// ����Ա
				lx = "fdy";
			} else if (bzrQx) {// ������
				lx = "bzr";
			} else if ("xy".equalsIgnoreCase(userType)) {// ѧԺ
				lx = "xy";
			} else if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {// ѧУ�û�������Ա��
				lx = "xx";
			}

			myForm.setLx(lx);
		}
		// =================end ===================

		// ==================Ĭ��չ�����е�½�����ʸ��������Ŀ ==================
		if (!"stu".equalsIgnoreCase(userType)) {
			// ��ͷ
			List<HashMap<String, String>> topTr = service.getTopTr("xmsh");
			rsList = service.getXmshList(myForm);
			myForm.getPages().setMaxRecord(rsList.size());//
			request.setAttribute("topTr", topTr);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "message", "msg" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { message, msg };

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setUserDep(userDep);
		rForm.setUserName(userName);
		rForm.setRsList(rsList);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "sqsh");
		// =================end ===================

		return mapping.findForward("xmtjManage");
	}

	/**
	 * ѧ������_��Ŀ���_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ҳ���ȿ���
		String widthType = request.getAttribute("widthType") != null ? request
				.getAttribute("widthType").toString() : myForm.getWidthType();
		// �û�����
		String userType = (String) session.getAttribute("userType");

		if ("stu".equalsIgnoreCase(userType)) {
			request.setAttribute("yhInfo", "��û�з��ʸ�ģ���Ȩ�ޣ�");
			return new ActionForward("/yhInfo.do", false);
		}

		// �û���
		String userName = (String) session.getAttribute("userName");
		myForm.setZgh(userName);
		// ����ԱȨ��
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// ������Ȩ��
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// �û����ڲ���
		String userDep = (String) session.getAttribute("userDep");

		myForm.setUserDep(userDep);
		// ��ͼ��
		String tableName = "view_xszz_comm_xmwh";
		// ����
		String realTable = "xszz_zzxmb";
		// ����·��
		String path = "yes".equals(myForm.getIskns()) ? "kns_xmsh.do"
				: "commXszz.do?method=xmshManage";
		// ��ǰѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);
		// ��ǰѧ��
		String xq = Base.currXq;
		myForm.setXq(xq);
		// ��ǰ���
		String nd = Base.currNd;
		myForm.setNd(nd);
		// ��ǰʱ��
		String sqsjCn = service.getNowTime("YYYY��MM��DD��");
		String sqsj = service.getNowTime("YYYYMMDD");
		myForm.setSqsj(sqsj);
		myForm.setSqsjCn(sqsjCn);
		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		xmdm = Base.isNull(xmdm) ? myForm.getXmdm() : xmdm;
		myForm.setXmdm(xmdm);
		myForm.setPkValue(xmdm);
		// ��Ŀ��Ϣ
		HashMap<String, String> xmInfo = service.getXmwhfo(myForm, tableName);
		// ��Ŀ����
		String xmmc = xmInfo.get("xmmc");
		myForm.setXmmc(xmmc);
		// ��Ŀ��
		String xmb = xmInfo.get("xmb");
		myForm.setXmb(xmb);
		// �Ƿ�ּ�
		String sffj = xmInfo.get("sffj");
		// ��ʾ��Ϣ
		String message = "";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// �Ƿ�ѧԺ
		boolean isxy = false;
		// ��Ŀ�б�
		List<HashMap<String, String>> rsList = null;
		// ģ������
		String mklx = request.getParameter("mklx");
		myForm.setMklx(mklx);
		if (!Base.isNull(mklx)) {
			path += "&mklx=" + mklx;
		}
		// =================end==================

		// ==================��½�û���� ==================

		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// ѧԺ�û�
			myForm.setXydm(userDep);
			isxy = true;
		}

		// ��½�û�����
		String lx = "";

		if (bzrQx && fdyQx) {// �����μ渨��Ա
			lx = "jd";
		} else if (fdyQx) {// ����Ա
			lx = "fdy";
		} else if (bzrQx) {// ������
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// ѧԺ
			lx = "xy";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// ѧУ�û�������Ա��
			lx = "xx";
		}

		myForm.setLx(lx);
		// =================end ===================

		// =================��ʼ�� ===================
		if (("".equalsIgnoreCase(doType) || null == doType) && !search
				&& ("".equalsIgnoreCase(xmdm) || null == xmdm)) {
			myForm = service.initXmdm(myForm, "xmsh_xs");
			xmdm = myForm.getXmdm();
			if (xmdm != null && !"".equalsIgnoreCase(xmdm)) {
				myForm.setPkValue(xmdm);
				// ��������
				xmInfo = service.getXmwhfo(myForm, tableName);
				sffj = xmInfo.get("sffj");
			} else {
				request.setAttribute("yhInfo", "�Բ�����ʱû����Ҫ��˵������Ŀ����ȷ��!");
				return new ActionForward("/yhInfo.do", false);
			}
			search = true;
		}
		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		if (search) {

			// ��ͷ
			List<HashMap<String, String>> topTr = service.getTopTr("xmsh_xs");
			request.setAttribute("topTr", topTr);

			// ����
			xmInfo.put("yhzgh", userName);
			rsList = service.getXsShList(myForm, xmInfo);
			// ��¼��
			request.setAttribute("rsNum", rsList.size());
		}
		// =================end ===================

		// =================�����ּ���� ===================
		// ��Ŀ�����б�
		myForm.setXmdm(xmdm);
		List<HashMap<String, String>> fjList = service.getXmfjqkList(myForm);

		if (fjList != null && fjList.size() > 0) {
			HashMap<String, String> jbMap = new HashMap<String, String>();
			jbMap.put("fjmc", "δָ��");
			jbMap.put("fjxxje", "δָ��");
			jbMap.put("fjsxje", "δָ��");
			jbMap.put("fjqdje", "δָ��");
			fjList.add(jbMap);
			request.setAttribute("sffj", "yes");
			request.setAttribute("fjList", fjList);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "message", "xmdm", "xmmc", "isxy",
				"sffj" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { message, xmdm, xmmc,
				String.valueOf(isxy), sffj };

		rForm.setRsList(rsList);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setUserDep(userDep);
		rForm.setUserName(userName);
		rForm.setRsList(rsList);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ================��ǰѧ�ꡢѧ�ڡ����==================
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", service.getXqMc(Base.currXq));
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("sqsjCn", sqsjCn);
		// =======================end========================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "xmsh");
		// =================end ===================

		// ҳ����ʾ��¼��
		request.setAttribute("mklx", mklx);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("xmshList", service.getXmshList(myForm));

		// �����ʽ����
		if ("dbsx".equalsIgnoreCase(widthType)) {
			// �Ӵ����������
			request.setAttribute("widthType", "dbsx");
		} else if ("kjfs".equalsIgnoreCase(widthType)) {
			// �ӿ�ݷ�ʽ����
			request.setAttribute("widthType", "kjfs");
		} else {
			// ����Ŀ��˽���
			request.setAttribute("widthType", "xmsh");
		}
		return mapping.findForward("xmshManage");
	}

	/**
	 * ѧ������_��Ŀ���_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xmshUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// ����ԱȨ��
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// ������Ȩ��
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// ����
		String realTable = "xszz_zzxmb";
		// ����·��
		String path = "xszz_jscz_xmsh.do";
		// ��ǰѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);
		// ��ǰѧ��
		String xq = Base.currXq;
		String xqmc = Base.getDqxqmc();
		myForm.setXq(xq);
		// ��ǰ���
		String nd = Base.currNd;
		myForm.setNd(nd);
		// ��ǰʱ��
		// String sqsjCn = service.getNowTime("YYYY��MM��DD��");
		String sqsj = request.getParameter("sqsj");
		sqsj = Base.isNull(sqsj) ? service.getNowTime("YYYYMMDD") : sqsj;
		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		xmdm = Base.isNull(xmdm) ? request.getParameter("save_xmdm") : xmdm;
		myForm.setPkValue(xmdm);
		myForm.setXmdm(xmdm);
		// ѧ��
		String xh = request.getParameter("xh");
		xh = Base.isNull(xh) ? xh : xh.trim();
		myForm.setXh(xh);
		// ģ������
		String mklx = request.getParameter("mklx");
		mklx = Base.isNull(mklx) ? "sh" : mklx;
		// �������
		String shpk = request.getParameter("shpk");
		myForm.setShpk(shpk);
		// ���״̬
		String shzt = request.getParameter("shzt");
		// ģ������
		String mk = (String) session.getAttribute("mklx");
		myForm.setMklx(mk);
		if (!Base.isNull(mk)) {
			path += "?mklx=" + mk;
		}
		// =================end==================

		// ==================��½�û���� ==================
		// ��½�û�����
		String lx = "";

		if (bzrQx && fdyQx) {// �����μ渨��Ա
			lx = "jd";
		} else if (fdyQx) {// ����Ա
			lx = "fdy";
		} else if (bzrQx) {// ������
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// ѧԺ
			lx = "xy";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// ѧУ�û�������Ա��
			lx = "xx";
		}

		myForm.setLx(lx);
		// =================end ===================

		// ===================�����Ŀ�����Ϣ ======================
		HashMap<String, String> xmInfo = service.getXmxgInfo(myForm);
		// ��������
		String sqzq = xmInfo.get("sqzq");
		// ����ʱ��
		String pdsj = xmInfo.get("pdsj");
		// ����ʱ��Ϊǰ��
		if ("ǰ��".equalsIgnoreCase(pdsj)) {
			HashMap<String, String> befInfo = service.getBeforeXnXqNd(sqzq,
					pdsj, myForm);
			xn = befInfo.get("xn");
			xq = befInfo.get("xq");
			xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);
			nd = befInfo.get("nd");
		}
		xmInfo.put("xn", xn);
		xmInfo.put("xq", xq);
		xmInfo.put("xqmc", xqmc);
		xmInfo.put("nd", nd);
		xmInfo.put("sqsj", sqsj);
		xmInfo.put("xh", xh);

		realTable = xmInfo.get("xmb");
		myForm.setXmb(realTable);

		// ��Ŀ�����б�
		List<HashMap<String, Object>> xmnrList = service.getXmShNrList(myForm,
				xmInfo);
		// ��Ŀ�����б�
		myForm.setXmdm(xmdm);
		List<HashMap<String, String>> xmfjqkList = service
				.getXmfjqkList(myForm);

		request.setAttribute("xmnrList", xmnrList);
		request.setAttribute("xmfjqkList", xmfjqkList);

		if (xmfjqkList != null && xmfjqkList.size() > 0) {
			request.setAttribute("fjNum", xmfjqkList.size());

			if (xmfjqkList.size() == 1) {

				HashMap<String, String> map = xmfjqkList.get(0);

				String fjqdje = map.get("fjqdje");
				String fjsxje = map.get("fjsxje");
				String fjxxje = map.get("fjxxje");

				if ("��".equalsIgnoreCase(fjqdje)
						&& "��".equalsIgnoreCase(fjsxje)
						&& "��".equalsIgnoreCase(fjxxje)) {
					request.setAttribute("noJe", "yes");
				}
			}
		}
		// =================end ===================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {

			String message = "";

			if ("tg".equalsIgnoreCase(shzt)) {

				// �ж��Ƿ񳬹���������
				if (("��".equals(xmInfo.get("bzrkz")) && bzrQx)
						|| ("��".equals(xmInfo.get("fdykz")) && fdyQx)
						|| ("��".equals(xmInfo.get("xykz"))
								&& "xy".equals(userType) && !fdyQx || ("��"
								.equals(xmInfo.get("xxkz")))
								&& "xx".equals(lx))) {
					message = service.isCgrssx(xmInfo, myForm);
				}

				// �ж��Ƿ񳬹���������
				if (Base.isNull(message)) {
					XszzXmtjService xmtjService = new XszzXmtjService();
					message = xmtjService.isCgZzsx(xmInfo, myForm);
				}
			}

			if (Base.isNull(message)) {

				this.updateOperation(request, realTable);

				// ������Ϣȥ״̬��
				HashMap<String, String> map = this.getValueMap(request,
						PRIFIX_SAVE);
				service.updateZtInfo(map);
			} else {
				request.setAttribute("message", message);
			}

		} else if ("plsave".equalsIgnoreCase(doType)) {

			realTable = request.getParameter("xmb");

			String message = "";

			if ("tg".equalsIgnoreCase(shzt)) {
				if (("��".equals(xmInfo.get("bzrkz")) && bzrQx)
						|| ("��".equals(xmInfo.get("fdykz")) && fdyQx)
						|| ("��".equals(xmInfo.get("xykz"))
								&& "xy".equals(userType) && !fdyQx || ("��"
								.equals(xmInfo.get("xxkz")))
								&& "xx".equals(lx))) {
					message = service.isCgrssxPl(xmInfo, myForm);
				}

				// �ж��Ƿ񳬹���������
				if (Base.isNull(message)) {
					XszzXmtjService xmtjService = new XszzXmtjService();
					message = xmtjService.isCgZzsxPl(xmInfo, myForm);
				}
			}

			// ���������Ϣ
			HashMap<String, String> map = this
					.getValueMap(request, PRIFIX_SAVE);

			if (Base.isNull(message)) {
				boolean result = service.savePlsh(myForm, realTable, map);
				message = result ? "�����ɹ�" : "����ʧ��";

				if (result) {
					service.updateZtInfoPl(map, myForm);
				}
			}

			request.setAttribute("message", message);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "lx", "shpk" };
		String[] qtzdz = new String[] { lx, shpk };

		rForm.setMklx(mklx);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		request.setAttribute("xmInfo", xmInfo);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "sqsh");
		// =================end ===================
		
		String jb=request.getParameter("jb");
		myForm.setSave_xmzzjb(jb);
		if ("plsh".equalsIgnoreCase(doType)
				|| "plsave".equalsIgnoreCase(doType)) {

			return mapping.findForward("xmshPl");
		}

		request.setAttribute("path", "commXszz.do?method=xmshManage");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmshUpdate");
	}

	/**
	 * ѧ������_�����ѯ_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jgcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// �û���
		String userName = (String) session.getAttribute("userName");
		myForm.setZgh(userName);
		// ����ԱȨ��
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// ������Ȩ��
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// �û����ڲ���
		String userDep = (String) session.getAttribute("userDep");
		myForm.setUserDep(userDep);
		// ��ͼ��
		String tableName = "view_xszz_comm_xmwh";
		// ����
		String realTable = "xszz_zzxmb";
		// ����·��
		String path = "yes".equals(myForm.getIskns()) ? "kns_jgcx.do"
				: "xszz_xmxg_jgcx.do";
		// ��Ŀ����
		String xmdm = myForm.getXmdm();
		// ��ʾ��Ϣ
		String message = "";
		// �Ƿ�ѧԺ
		boolean isxy = false;
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// ��Ŀ�б�
		List<HashMap<String, String>> rsList = null;
		// ģ������
		String mklx = (String) session.getAttribute("mklx");
		// ҳ����
		String widthType = request.getAttribute("widthType") != null ? request
				.getAttribute("widthType").toString() : myForm.getWidthType();

		myForm.setMklx(mklx);
		if (!Base.isNull(mklx)) {
			path += "?mklx=" + mklx;
		}
		// =================end==================

		// ==================��½�û���� ==================
		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// ѧԺ�û�
			myForm.setXydm(userDep);
			isxy = true;
		} else if ("stu".equalsIgnoreCase(userType)) {
			// ѧ���û�
			myForm.setXh(userName);
		}

		// ��½�û�����
		String lx = "";

		if (bzrQx && fdyQx) {// �����μ渨��Ա
			lx = "jd";
		} else if (fdyQx) {// ����Ա
			lx = "fdy";
		} else if (bzrQx) {// ������
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// ѧԺ
			lx = "xy";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// ѧУ�û�������Ա��
			lx = "xx";
		}

		myForm.setLx(lx);
		// =================end ===================

		// ==================ִ��ɾ������ ==================
		if ("del".equalsIgnoreCase(doType)) {

			boolean result = service.delXmsqInfo(myForm);

			// ����Ǽ�ͥ�������Ļ� ɾ����ص�ѧ��������Ϣ 2011.3.28 QLJ
			if ("5001".equalsIgnoreCase(xmdm) && result) {
				result = service.delXsJtcy();
			}
			message = result ? "�����ɹ�" : "����ʧ��";
		}
		// =================end ===================

		// =================��ʼ�� ===================
		if (("".equalsIgnoreCase(doType) || null == doType) && !search) {
			myForm = service.initXmdm(myForm, "jgcx");
			xmdm = myForm.getXmdm();
			if (xmdm == null || "".equalsIgnoreCase(xmdm)) {
				request.setAttribute("yhInfo", "�Բ�����ʱû�п��Բ鿴�������Ŀ����ȷ��!��");
				return new ActionForward("/yhInfo.do", false);
			}

			search = true;

		}
		request.setAttribute("xmdm", xmdm);

		// ==================ִ�в�ѯ���� ==================
		if (search) {

			xmdm = Base.isNull(xmdm) ? "no" : xmdm;
			// ��ͷ
			List<HashMap<String, String>> topTr = service.getTopTr("jgcx!!@@!!"
					+ xmdm);
			request.setAttribute("topTr", topTr);
			// ����
			rsList = service.getJgcxList(myForm);

			request.setAttribute("rsNum",
					(rsList != null && rsList.size() > 0) ? rsList.size() : 0);

		}
		// =================end ===================

		// ==================ִ��ͬ������ ==================
		if ("tb".equalsIgnoreCase(doType)) {
			boolean result = service.tbZtInfo();
			message = result ? "ͬ���ɹ�" : "ͬ��ʧ��";

			SynDataService synService = new SynDataService();
			synService.synImpData();
		}

		// =================end ===================
		// �������϶������ͥ�����������ͬ������ͥ��������
		if ("jtqkdcTb".equalsIgnoreCase(doType)) {
			boolean result = service.saveJtqkdcFromKns(null, null);
			message = result ? "ͬ���ɹ�" : "ͬ��ʧ��";
		}
		// ------------------end------------------

		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {

			String xxdm = Base.xxdm;

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// �й��ش�
				XszzExpService expService = new XszzExpService();
				expService.expInfo(myForm, response.getOutputStream());
			} else if (Globals.XXDM_WHSYFWXY.equalsIgnoreCase(xxdm)) {//�人��ҵ����
				XszzExpService expService = new XszzExpService();
				expService.expInfo(myForm, response.getOutputStream());
			} else if (Globals.XXDM_HZNYDX.equalsIgnoreCase(xxdm)) {//����ũҵ��ѧ
				XszzExpService expService = new XszzExpService();
				expService.expInfo(myForm, response.getOutputStream());
			}else if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)) {//����ʦ��ѧԺ
				XszzExpService expService = new XszzExpService();
				expService.expInfo(myForm, response.getOutputStream());
			} else {
				service.expInfo(myForm, response.getOutputStream());
			}

			return mapping.findForward("");
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "message", "isxy", "xmjb" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { message, String.valueOf(isxy),
				myForm.getXmzzjb() };

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setUserDep(userDep);
		rForm.setUserName(userName);
		rForm.setRsList(rsList);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "jgcx");
		// =================end ===================

		// �����ʽ����
		if ("dbsx".equalsIgnoreCase(widthType)) {
			// �Ӵ����������
			request.setAttribute("widthType", "dbsx");
		} else if ("kjfs".equalsIgnoreCase(widthType)) {
			// �ӿ�ݷ�ʽ����
			request.setAttribute("widthType", "kjfs");
		} else {
			// ����Ŀ��˽���
			request.setAttribute("widthType", "xmsh");
		}

		// ҳ����ʾ��¼��
		request.setAttribute("pageSize", myForm.getPages().getPageSize());

		return mapping.findForward("jgcxManage");
	}

	/**
	 * ѧ������_�����ѯ_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jgcxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;
		// ѧУ����
		String xxdm = Base.xxdm;
		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// ����
		String realTable = request.getParameter("xmb");
		realTable = Base.isNull(realTable) ? "xszz_zzxmb" : realTable;
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// �û���
		String userName = (String) session.getAttribute("userName");
		myForm.setZgh(userName);
		// ����ԱȨ��
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// ������Ȩ��
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// ����·��
		String path = "xszz_xmxg_jgcx.do";
		// ��ǰѧ��
		String xn = Base.currXn;
		// myForm.setXn(xn);
		// ��ǰѧ��
		String xq = Base.currXq;
		String xqmc = Base.getDqxqmc();
		// myForm.setXq(xq);
		// ��ǰ���
		String nd = Base.currNd;
		// myForm.setNd(nd);
		// ģ������
		String mklx = "jg";
		myForm.setMklx(mklx);
		// �����޸�
		String canEdit = "no";
		// ����
		String pk = request.getParameter("pk");
		String[] arr_pk = !Base.isNull(pk) ? pk.split("!!@@!!") : null;
		// ģ������
		String mk = (String) session.getAttribute("mklx");
		if (!Base.isNull(mk)) {
			path += "?mklx=" + mk;
		}
		// =================end==================

		// ==================��½�û���� ==================

		// ��½�û�����
		String lx = "";

		if (bzrQx && fdyQx) {// �����μ渨��Ա
			lx = "jd";
		} else if (fdyQx) {// ����Ա
			lx = "fdy";
		} else if (bzrQx) {// ������
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// ѧԺ
			lx = "xy";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// ѧУ�û�������Ա��
			lx = "xx";
		}
		// =================end==================
		if ("fileDel".equalsIgnoreCase(doType)) {
			String pkV = "xh||sqsj";
			String pkValue = myForm.getSave_xh() + myForm.getSave_sqsj();
			if (!Base.isNull(pkValue)) {
				service.fileDel("xszz_knsb", "scdz", pkV, pkValue);
			}
		}
		String filePath = request.getParameter("scdz");
		if ("upload".equalsIgnoreCase(doType)) {
			// �����ļ��ϴ�
			FormFile file = myForm.getUploadFile();

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
				request.setAttribute("message", "�ϴ��ɹ�");
			}
		}
		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("save_xh");
			String sqsj = request.getParameter("save_sqsj");
			String xmdm = request.getParameter("save_xmdm");
			myForm.setXh(xh);
			arr_pk = new String[3];
			arr_pk[0] = xh;
			arr_pk[1] = sqsj;
			arr_pk[2] = xmdm;

			String bclx = request.getParameter("bclx");

			if ("add".equalsIgnoreCase(bclx)) {
				if ("xszz_knsb".equals(myForm.getXmb())
						&& Globals.XXDM_TJGYDX.equalsIgnoreCase(xxdm)) {
					request.setAttribute("save_scdz", myForm.getScdz());
					request.getParameterMap();
				}
				this.insertOperation(request, realTable);
				// ������Ϣȥ״̬��
				HashMap<String, String> map = this.getValueMap(request,
						PRIFIX_SAVE);
				service.addZtInfo(map);
			} else {
				this.updateOperation(request, realTable);
			}

			boolean knsdl = "yes".equals(myForm.getIskns())
					&& "xszz_knsb".equals(realTable)
					&& Boolean.valueOf(XMLReader
							.getFlowControl("xszz", "knsdl"))
					&& Boolean.valueOf(XMLReader.getFlowControl("xszz",
							"jtqkdc"));
			// �������϶���Ϊ����ģ�飬�����������϶���Ŀ������˼�ͥ�������
			if (knsdl) {
				service.saveJtqkdcFromKns(myForm.getSave_xh(), myForm
						.getSave_sqsj());
			}

			// ��ͥ���
			if ("jtqkdcb".equalsIgnoreCase(realTable) || knsdl) {
				String message = service.saveJtcy(myForm);
				if (!Base.isNull(message)) {
					request.setAttribute("message", message);
				}
			}
		}
		// =================end ===================

		// ===================ִ����Ӳ��� ======================
		if ("add".equalsIgnoreCase(doType)
				|| "fileDel".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			String xmdm = request.getParameter("xmdm");
			String sqsj = service.getNowTime("yyyymmdd");
			
			boolean xspjpy = ("5004".equals(xmdm) || "1001".equalsIgnoreCase(xmdm))
			&& Boolean.valueOf(XMLReader.getFlowControl("xszz", "xspjxx"));
			request.setAttribute("jxjList", service.getPjpyInfo("01", xh));
			request.setAttribute("rychList", service.getPjpyInfo("02", xh));
			request.setAttribute("xspjpy", xspjpy);
			
			myForm.setPkValue(xmdm);

			HashMap<String, String> xmInfo = service.getXmxgInfo(myForm);
			// ------2010.9.27 lr------
			String pdsj = xmInfo.get("pdsj");
			String sqzq = xmInfo.get("sqzq");
			if ("ǰ��".equalsIgnoreCase(pdsj)) {
				myForm.setXn(xn);
				myForm.setXq(xq);
				myForm.setNd(nd);
				HashMap<String, String> befInfo = service.getBeforeXnXqNd(sqzq,
						pdsj, myForm);
				xn = befInfo.get("xn");
				xq = befInfo.get("xq");
				xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);
				nd = befInfo.get("nd");
			}
			// ------end 2010.9.27 lr------

			xmInfo.put("xn", xn);
			xmInfo.put("xq", xq);
			xmInfo.put("xqmc", xqmc);
			xmInfo.put("nd", nd);
			xmInfo.put("sqsj", sqsj);
			xmInfo.put("xh", xh);

			realTable = xmInfo.get("xmb");
			canEdit = "yes";
			myForm.setXn(xn);
			myForm.setXq(xq);
			myForm.setNd(nd);
			myForm.setXmb(realTable);

			// ��Ŀ�����б�
			List<HashMap<String, Object>> xmnrList = service
					.getXmSqNrList(myForm);
			// ��Ŀ�����б�
			List<HashMap<String, String>> xmfjqkList = service
					.getXmfjqkList(myForm);

			request.setAttribute("xmInfo", xmInfo);
			request.setAttribute("xmnrList", xmnrList);
			request.setAttribute("xmfjqkList", xmfjqkList);
			request.setAttribute("xmdm", xmdm);
		}
		// =================end ===================

		// ===================�����Ŀ�����Ϣ ======================

		if (arr_pk != null && arr_pk.length >= 3
				&& !"add".equalsIgnoreCase(doType)) {
			String xh = arr_pk[0];
			String sqsj = arr_pk[1];
			String xmdm = arr_pk[2];
			
			boolean xspjpy = ("5004".equals(xmdm) || "1001".equalsIgnoreCase(xmdm))
			&& Boolean.valueOf(XMLReader.getFlowControl("xszz", "xspjxx"));
			request.setAttribute("jxjList", service.getPjpyInfo("01", xh));
			request.setAttribute("rychList", service.getPjpyInfo("02", xh));
			request.setAttribute("xspjpy", xspjpy);
			
			myForm.setXh(xh);
			myForm.setXmdm(xmdm);
			myForm.setPkValue(xmdm);

			HashMap<String, String> xmInfo = service.getXmxgInfo(myForm);
			realTable = xmInfo.get("xmb");
			String shjb = xmInfo.get("shjb");

			myForm.setSqsj(sqsj);
			myForm.setShjb(shjb);
			myForm.setXmb(realTable);
			myForm.setPkValue(xh + sqsj);

			HashMap<String, String> sqInfo = service.getXsSqInfo(myForm,
					realTable);

			myForm.setXn(sqInfo.get("xn"));
			myForm.setXq(sqInfo.get("xq"));
			myForm.setNd(sqInfo.get("nd"));
			xmInfo.putAll(sqInfo);

			// ��Ŀ�����б�
			List<HashMap<String, Object>> xmnrList = service.getXmShNrList(
					myForm, xmInfo);
			// ��Ŀ�����б�
			List<HashMap<String, String>> xmfjqkList = service
					.getXmfjqkList(myForm);

			// �ϴ�����
			String file = service.getFile(myForm);

			request.setAttribute("file", file);
			request.setAttribute("xmdm", xmdm);
			request.setAttribute("xmInfo", xmInfo);
			request.setAttribute("xmnrList", xmnrList);
			request.setAttribute("xmfjqkList", xmfjqkList);

			if (xmfjqkList != null && xmfjqkList.size() > 0) {
				request.setAttribute("fjNum", xmfjqkList.size());

				if (xmfjqkList.size() == 1) {

					HashMap<String, String> map = xmfjqkList.get(0);

					String fjqdje = map.get("fjqdje");
					String fjsxje = map.get("fjsxje");
					String fjxxje = map.get("fjxxje");

					if ("��".equalsIgnoreCase(fjqdje)
							&& "��".equalsIgnoreCase(fjsxje)
							&& "��".equalsIgnoreCase(fjxxje)) {
						request.setAttribute("noJe", "yes");
					}
				} else {
					for (int i = 0; i < xmfjqkList.size(); i++) {
						HashMap<String, String> map = xmfjqkList.get(i);
						String fjqdje = map.get("fjqdje");
						String fjsxje = map.get("fjsxje");
						String fjxxje = map.get("fjxxje");

						if ("��".equalsIgnoreCase(fjqdje)
								&& "��".equalsIgnoreCase(fjsxje)
								&& "��".equalsIgnoreCase(fjxxje)) {
							request.setAttribute("noJe", "yes");
							break;
						}
					}
				}
			}

			// ����Ա�û�����δ��˵��������޸ĵĹ��ܰ�ť
			if ("xx".equalsIgnoreCase(lx)
					|| ("update".equalsIgnoreCase(doType) && service
							.canEdit(myForm))) {
				canEdit = "yes";
			}
		}

		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "canEdit" };
		String[] qtzdz = new String[] { canEdit };

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		myForm.setMklx(mk);
		service.setList(myForm, request, "sqsh");
		
		
		// =================end ===================
		return mapping.findForward("jgcxUpdate");
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

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// �û���
		String userName = (String) session.getAttribute("userName");
		myForm.setZgh(userName);
		// ����ԱȨ��
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// ������Ȩ��
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// �û����ڲ���
		String userDep = (String) session.getAttribute("userDep");
		myForm.setUserDep(userDep);
		// ��ͼ��
		String tableName = "view_xsjbxx";
		// ����
		String realTable = "";
		// ����·��
		String path = "";
		// ��ʾ��Ϣ
		String message = "";
		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// ��ѯ���
		List<HashMap<String, String>> rsList = null;
		List<HashMap<String, String>> topTr = null;
		// =================end==================

		// ==================��½�û���� ==================
		// ��½�û�����
		String lx = "";

		if (bzrQx && fdyQx) {// �����μ渨��Ա
			lx = "jd";
		} else if (fdyQx) {// ����Ա
			lx = "fdy";
		} else if (bzrQx) {// ������
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// ѧԺ
			lx = "xy";
			myForm.setXydm(userDep);
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// ѧУ�û�������Ա��
			lx = "xx";
		}

		myForm.setLx(lx);
		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		if (search) {

			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc" };
			// ��ͷ
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			// ����
			rsList = service.getXsxxList(myForm);

			request.setAttribute("topTr", topTr);
			request.setAttribute("rsNum",
					(rsList != null && rsList.size() > 0) ? rsList.size() : 0);

		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "message", "xmdm" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { message, xmdm };

		rForm.setRsList(rsList);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setUserDep(userDep);
		rForm.setUserName(userName);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "xsxx");
		// =================end ===================

		return mapping.findForward("xsxxManage");
	}

	/**
	 * ��ʦ��Ϣ����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward lsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// �û����ڲ���
		String userDep = (String) session.getAttribute("userDep");
		myForm.setUserDep(userDep);
		// ��ͼ��
		String tableName = "view_fdyxx";
		// ����
		String realTable = "";
		// ����·��
		String path = "";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// ��ѯ���
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		// =================end==================

		// ==================ִ�в�ѯ���� ==================
		if (search) {

			// ����
			String[] colList = new String[] { "zgh", "xm", "bmmc", "zwmc",
					"zzmm", "xl", "yddh", "bgdh" };
			// ��ͷ

			topTr = SearchUtils.getTopTr(tableName, colList, null);

			rs = service.getXszzList(tableName, myForm, colList, "");

			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);

		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setUserDep(userDep);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "lsxx");
		// =================end ===================

		return mapping.findForward("lsxxManage");
	}

	/**
	 * ѧ������_ͳ�Ʊ���
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward tjbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= ����ֵ ==================
		// ��Ŀ����
		String xmdm = myForm.getXmdm();
		myForm.setXmdm(xmdm);
		// ����ʱ��
		String sqsj = myForm.getSave_sqsj();
		myForm.setSqsj(sqsj);
		// ================= end ==================

		// ================= ִ��ͳ�����EXCEL ==================
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		if (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) {// ������
			service.printKns(myForm, response.getOutputStream());
		} else if (XszzXmdm.XSZZ_LSTD.equals(xmdm)) {// ��ɫͨ��
			service.printLstd(myForm, response.getOutputStream());
		} else if (XszzXmdm.XSZZ_GJLZJXJ.equals(xmdm)) {// ������־��ѧ
			service.printLzjxj(myForm, response.getOutputStream());
		} else if (XszzXmdm.XSZZ_XFHJ.equals(xmdm)) {// ѧ������ѧ��
			service.printHjxf(myForm, response.getOutputStream());
		} else if (XszzXmdm.XSZZ_GJZXJ.equals(xmdm)) {
			service.printGjzxj(myForm, response.getOutputStream());
		} else {
			throw new Exception("������˼�����ͳ�ƻ�û���أ�");
		}
		// ================= end ==================

		return mapping.findForward("");
	}

	/**
	 * ѧ������_ͳ�Ʊ���
	 * 
	 * @author lr
	 * @return ActionForward
	 */
	public ActionForward tjbbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;
		if ("xy".equalsIgnoreCase((String)request.getSession().getAttribute("userType")
				)) {
			myForm.setXydm((String)request.getSession().getAttribute("userDep")
					);
		}

		// �����б�
		request.setAttribute("tjbbList", service.getTjbbList());
		// �꼶ѧԺרҵ�༶
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		// ���ѧ��ѧ��
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("path", "xszz_jscz_tjbb.do");// ·��
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tjbbManage");
	}

	/**
	 * ѧ������_��ӡͳ�Ʊ���
	 * 
	 * @author lr
	 * @return ActionForward
	 */
	public ActionForward printTjbbManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		response.reset();
		response.setContentType("application/vnd.ms-excel");

		// ��ӡͳ�Ʊ�
		service.printZztjbb(myForm, response.getOutputStream());

		return mapping.findForward("");
	}

	/**
	 * ѧ������_��������_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzhzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzXmtjService service = new XszzXmtjService();
		XszzTyForm myForm = (XszzTyForm) form;
		User user = getUser(request);

		// ================= ����ֵ ==================
		// �û�����
		String userType = user.getUserType();
		// �û���
		String userName = user.getUserName();
		// ���ڲ���
		String userDep = user.getUserDep();
		// ����·��
		String path = "xszz_xmtj_zzhz.do";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ѧУ����
		String xxdm = Base.xxdm;
		// ����ģ��
		String gnmk = "xszz";
		// �˵�
		String menu = "xmtj";
		// ��ǰʱ��
		String nowTime = service.getNowTime("YYYYMMDD");
		// ��ʼʱ��
		String kssj = myForm.getKssj();
		myForm.setKssj(Base.isNull(kssj) ? Base.currNd + "0101" : kssj);
		// ����ʱ��
		String jssj = myForm.getJssj();
		myForm.setJssj(Base.isNull(jssj) ? nowTime : jssj);
		// ��ʾ��Ϣ
		String message = "";
		// �Ƿ�ѧԺ
		boolean isxy = service.isXy(user);
		// ��ͷ
		List<HashMap<String, String>> topTr = null;
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;

		// =================end==================

		// ==================��½�û���� ==================
		if (isxy) {
			// ѧԺ�û�
			myForm.setXydm(userDep);
		} else if ("stu".equalsIgnoreCase(userType)) {
			// ѧ���û�
			myForm.setXh(userName);
		}

		// ��½�û�����
		String lx = service.getUserLx(user);
		myForm.setLx(lx);
		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			topTr = service.getTopTr(xxdm, "zzhz");
			rsArrList = service.getZzhzList(myForm);
		}
		// =================end ===================

		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.expZzhzInfo(myForm, response.getOutputStream());

			return mapping.findForward("");
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "isxy" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { String.valueOf(isxy) };

		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("zzhzManage");
	}

	/**
	 * ѧ������_��������_��ϸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzhzUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzXmtjService service = new XszzXmtjService();
		XsxxglService stuService = new XsxxglService();
		XszzTyForm myForm = (XszzTyForm) form;
		User user = getUser(request);

		// ================= ����ֵ ==================
		// ѧ��
		String xh = request.getParameter("xh");
		myForm.setXh(xh);
		// ��ʼʱ��
		String kssj = request.getParameter("kssj");
		myForm.setKssj(kssj);
		// ����ʱ��
		String jssj = request.getParameter("jssj");
		myForm.setJssj(jssj);
		// ����·��
		String path = "xszz_xmtj_zzhz.do";
		// ����ģ��
		String gnmk = "xszz";
		// �˵�
		String menu = "xmtj";
		// ��ʾ��Ϣ
		String message = "";
		// =================end==================

		// ==================�����Ϣչʾ ==================
		// ѧ��������Ϣ
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		// ѧ������������
		List<HashMap<String, String>> rsList = service.getXsZzhzList(myForm);
		int rsNum = (rsList != null && rsList.size() > 0) ? rsList.size() : 0;

		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "kssj", "jssj", "rsNum" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { kssj, jssj, String.valueOf(rsNum) };

		rForm.setRsList(rsList);
		rForm.setRs(stuInfo);
		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("zzhzUpdate");
	}

	/**
	 * ѧ������_��Ŀά��_�ֶ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zdszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzXgszService service = new XszzXgszService();
		XszzTyForm myForm = (XszzTyForm) form;
		User user = getUser(request);

		// ================= ����ֵ ==================
		// ����·��
		String path = "xszz_xgsz_zdsz.do";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����ģ��
		String gnmk = "xszz";
		// �˵�
		String menu = "zdsz";
		// ��ʾ��Ϣ
		String message = "";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;

		// =================end==================

		// ==================ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {

		}
		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		if (search) {

		}
		// =================end ===================
		RequestForm rForm = new RequestForm();
		rForm.setMessage(message);
		rForm.setPath(path);

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("zdszManage");
	}

	/**
	 * ��������
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

		String pkKey = request.getParameter("pk");
		String pkValue = request.getParameter("pkValue");
		String tableName = request.getParameter("tableName");
		String filePath = DealString.toGBK(request.getParameter("filePath"));
		String fileName = DealString.toGBK(request.getParameter("fileName"));

		if (FileManage.downLoadFile(filePath, fileName, response)) {
			return null;
		} else {
			request.setAttribute("errMsg", "�ļ������ڻ�����ɾ��!");
			FileManage.delFileInfo(tableName, "fileName", "filePath", pkKey,
					pkValue);
			return new ActionForward("/errMsg.do", false);
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

		String filePath = request.getParameter("filepath");
		String tableName = request.getParameter("tableName");
		String url = request.getParameter("url");
		String pk = "xmdm||xh||sqsj";
		String xmdm = request.getParameter("xmdm");
		String sqsj = request.getParameter("sqsj");
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");

		boolean result = false;

		File f = new File(filePath);
		// �жϸ����Ƿ���ڣ���������ɾ��
		if (f.exists()) {
			f.delete();
		}

		result = FileManage.delFileInfo(tableName, "fileName", "filePath", pk,
				pkValue);
		request.setAttribute("message", result ? "�����ɹ�!" : "����ʧ��!");

		return new ActionForward(new StringBuilder().append(
				"/commXszz.do?method=").append(url).append("&pk=").append(
				pkValue).append("&xdmd=").append(xmdm).append("&sqsj=").append(
				sqsj).append("&xmb=").append(tableName).append("&doType=")
				.append(doType).toString(), false);
	}

	/**
	 * �����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzxbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzTyForm myForm = (XszzTyForm) form;
		XszzXgszService service = new XszzXgszService();

		// ��ͼ��
		String tableName = "view_xszz_comm_xmwh";
		// ����
		String realTable = "xszz_zzxmb";
		String xmdm = request.getParameter("xmdm");
		String doType = request.getParameter("doType");

		List<HashMap<String, String>> rsList = null;

		if (!Base.isNull(xmdm)) {
			HashMap<String, String> xmInfo = service.getXmIfno(xmdm);
			request.setAttribute("xmInfo", xmInfo);
		}

		if ("zzxb".equals(doType)) {

			String[] pkValue = request
					.getParameterValues("primarykey_checkVal");
			boolean result = service.saveZzxb(pkValue, myForm);
			request.setAttribute("message", result ? "�����ɹ�!"
					: "����ʧ��,�����Ƿ����ظ�����!");

			doType = "query";
		}

		if ("del".equalsIgnoreCase(doType)) {

			boolean result = service.delXmsqInfo(myForm);
			request.setAttribute("message", result ? "�����ɹ�" : "����ʧ��");

			doType = "query";
		}

		if ("query".equals(doType)) {

			xmdm = Base.isNull(xmdm) ? "no" : xmdm;
			// ��ͷ
			List<HashMap<String, String>> topTr = service.getTopTr("zzxb!!@@!!"
					+ xmdm);
			request.setAttribute("topTr", topTr);
			// ����
			rsList = service.getZzxbList(myForm);

			request.setAttribute("rsNum",
					(rsList != null && rsList.size() > 0) ? rsList.size() : 0);
			request.setAttribute("rsList", rsList);
		}

		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("path", "xszz_zzxb.do");
		FormModleCommon.commonRequestSet(request);
		service.setList(myForm, request, "jgcx");
		return mapping.findForward("zzxbManage");
	}

	/**
	 * ��������ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzxbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		String doType = request.getParameter("doType");
		String pk = request.getParameter("pk");
		String[] tempArr = Base.isNull(pk) ? null : pk.split("!!@@!!");

		if (null != tempArr && tempArr.length == 4) {

			String xh = tempArr[0];
			String sqsj = tempArr[1];
			String xmdm = tempArr[2];
			String xmb = tempArr[3];

			myForm.setXh(xh);
			myForm.setSqsj(sqsj);
			myForm.setXmdm(xmdm);
			myForm.setXmb(xmb);
			myForm.setPkValue(xmdm);

			// ��Ŀ�����Ϣ
			HashMap<String, String> xmInfo = service.getXmxgInfo(myForm);
			// �ֶ�
			List<HashMap<String, Object>> xmnrList = service.getXmShNrList(
					myForm, xmInfo);

			myForm.setPkValue(new StringBuilder().append(xh).append(sqsj)
					.toString());

			// ��¼��ϸ��Ϣ
			HashMap<String, String> zzxbInfo = service.getXsSqInfo(myForm, xmb);

			xmInfo.putAll(zzxbInfo);
			xmInfo.put("xqmc", service.getOneValue("xqdzb", "xqmc", "xqdm",
					xmInfo.get("xq")));
			// ��Ŀ�����б�
			myForm.setXmdm(xmdm);

			request.setAttribute("xmnrList", xmnrList);
			request.setAttribute("xmInfo", xmInfo);

			request.setAttribute("xh", xh);
			request.setAttribute("sqsj", sqsj);
			request.setAttribute("xmdm", xmdm);
			request.setAttribute("xmb", xmb);

			request.setAttribute("pkValue", new StringBuilder().append(sqsj)
					.append(xh));
		}

		if ("save".equals(doType)) {
			updateOperation(request, myForm.getXmb());
		}

		// ===================��ʼ���б�ֵ ======================
		request.setAttribute("pk", pk);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "xszz_zzxb.do");
		FormModleCommon.commonRequestSet(request);
		service.setList(myForm, request, "sqsh");
		// =================end ===================

		return mapping.findForward("zzxbUpdate");
	}

	/**
	 * ����ҳ��
	 */
	public ActionForward dowDqfbMb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		return mapping.findForward("dowDqfbMb");
	}

	
	/**
	 * ���ص����ֲ�ģ��
	 */
	@SuppressWarnings("deprecation")
	public ActionForward downloadMb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String doType = request.getParameter("doType");

		if ("dow".equalsIgnoreCase(doType)) {
			String filePath = request.getRealPath("") + "\\xlsDown\\�����ֲ���.xls";
			String fileName = "�����ֲ�.xls";

			FileManage.downLoadFile(filePath, fileName, response);
		}

		return null;
	}
	
	/**
	 * �����ֲ�����
	 */
	@SuppressWarnings("deprecation")
	public ActionForward impDqfbMb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		XszzTyForm model = (XszzTyForm) form;
		XszzPrintService service = new XszzPrintService();
		
		HashMap<String,String> map = FileManage.fileUpload(model.getUploadFile(), request.getRealPath("")+".upload", 10);
		boolean result = service.impZcfInfo(map.get("filePath"), user);
		
		request.setAttribute("message", result ? "����ɹ�!" : "����ʧ��!");
		return mapping.findForward("dowDqfbMb");
	}
}