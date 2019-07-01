package xgxt.pjpy;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import common.Globals;

import xgxt.DAO.DAO;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xgxt.xsgygl.GyglTyDAO;

public class PjpyTyService {

	PjpyTyDAO dao = new PjpyTyDAO();

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(PjpyTyForm myForm, HttpServletRequest request,
			String menu) throws Exception {

		//����
		String xn =myForm.getQueryequals_xn();
		String xq =myForm.getQueryequals_xq();
		String nd =myForm.getQueryequals_nd();
		String pk = "";
		String pkValue = "";
		
		// =============ͨ��================
		FormModleCommon.setNdXnXqList(request);// ���ѧ��ѧ��
		FormModleCommon.requestSetList(new String[] { "bm", "zzmm" }, request);// �Զ���(Ŀǰ�����Ŵ���,������ò)
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);// �꼶ѧԺרҵ�༶

		// �Ƿ��б�
		List<HashMap<String, String>> sfList = dao.getSelectList("sflx");
		request.setAttribute("sfList", sfList);

		// ============= ������ά��================
		if ("zyf".equalsIgnoreCase(menu)) {

			// �������
			List<HashMap<String, String>> pyccList = dao.getPjpyList(
					"view_xsxxb", "pycc", "pycc", "", "", "");
			request.setAttribute("pyccList", pyccList);
		}
		// ============= ����ͳ��================
		else if ("bbtj".equalsIgnoreCase(menu)) {

			// ��������
			List<HashMap<String, String>> bblxList = dao.getSelectList("bblx");
			request.setAttribute("bblxList", bblxList);

			// ��ѧ�����
			List<HashMap<String, String>> jxjlbList = dao.getPjpyList(
					"jxjlbdmb", "jxjlbdm", "jxjlbmc", "", "", "");
			request.setAttribute("jxjlbList", jxjlbList);

			// �����ƺ�
			List<HashMap<String, String>> rychList = dao.getPjpyList("rychdmb",
					"rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);

			// �����б�
			List<HashMap<String, String>> yhList = dao.getPjpyList("dmk_yh",
					"yhdm", "yhmc", "", "", "");
			request.setAttribute("yhList", yhList);

		}
		// =============�����ά��================
		else if ("wlk".equalsIgnoreCase(menu)) {

			// ���������
			List<HashMap<String, String>> wlkList = dao.getPjpyList(
					"zjcm_wlkdmb", "dm", "mc", "", "", "");
			request.setAttribute("wlkList", wlkList);
		}
		// =============��������================
		else if ("tjsz".equalsIgnoreCase(menu)) {

			if (Globals.XXDM_ZJCMXY.equals(Base.xxdm)) {
				// У�ڽ�ѧ���б�
				List<HashMap<String, String>> jxjList = dao.getJxjList("У��");
				request.setAttribute("jxjList", jxjList);
				request.setAttribute("all", "no");
			} else {
				List<HashMap<String, String>> jxjList = dao.getPjpyList(
						"jxjdmb", "jxjdm", "jxjmc", "", "", "");
				request.setAttribute("jxjList", jxjList);
				request.setAttribute("all", "yes");
			}

			// �����ƺ��б�
			List<HashMap<String, String>> rychList = dao.getPjpyList("rychdmb",
					"rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);

			// �����б�
			List<HashMap<String, String>> tjList = dao.getTjList();
			// dao.getPjpyList("jxjtjzdb","zdmc", "zdsm", "", "", "");
			request.setAttribute("tjList", tjList);

			// ���������б�
			List<HashMap<String, String>> tjlxList = dao.getSelectList("tjlx");
			request.setAttribute("tjlxList", tjlxList);

			// ���������
			List<HashMap<String, String>> wlkList = dao.getPjpyList(
					"zjcm_wlkdmb", "dm", "mc", "", "", "");
			request.setAttribute("wlkList", wlkList);

		}
		// =============����С��================
		else if ("cpxz".equalsIgnoreCase(menu)) {

			// �����б�
			List<HashMap<String, String>> kgList = dao.getSelectList("kglx");
			request.setAttribute("kgList", kgList);

			// ְ���б�
			List<HashMap<String, String>> zwList = dao.getPjpyList(
					"sxjy_bjgbzlb", "bjgbdm", "bjgbmc", "", "", "");
			request.setAttribute("zwList", zwList);

		}
		// =============У�ڽ�ѧ��================
		else if ("jxjxn".equalsIgnoreCase(menu)) {

			// У�ڽ�ѧ���б�
			List<HashMap<String, String>> jxjList = dao.getJxjList("У��");
			request.setAttribute("jxjList", jxjList);

			// У�⽱ѧ���б�
			List<HashMap<String, String>> xwjxjList = dao.getJxjList("У��");
			request.setAttribute("xwjxjList", xwjxjList);

			// �������
			List<HashMap<String, String>> pyccList = dao.getPjpyList(
					"view_xsxxb", "pycc", "pycc", "", "", "");
			request.setAttribute("pyccList", pyccList);

			// ���������
			List<HashMap<String, String>> wlkList = dao.getPjpyList(
					"zjcm_wlkdmb", "dm", "mc", "", "", "");
			request.setAttribute("wlkList", wlkList);

		}
		// =============У�⽱ѧ��================
		else if ("jxjxw".equalsIgnoreCase(menu)) {

			// У�ڽ�ѧ���б�
			List<HashMap<String, String>> jxjList = dao.getJxjList("У��");
			request.setAttribute("jxjList", jxjList);

		}
		// =============�����ƺ�================
		else if ("rych".equalsIgnoreCase(menu)) {

			// �����ƺ��б�
			List<HashMap<String, String>> rychList = dao.getPjpyList("rychdmb",
					"rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);

		}
		// =============��ѧ��================
		else if ("jxj".equalsIgnoreCase(menu)) {

			// ��ѧ���б�
			List<HashMap<String, String>> jxjList = dao.getPjpyList("jxjdmb",
					"jxjdm", "jxjmc", "", "", "");			
			request.setAttribute("jxjList", jxjList);

		}
		// =============�걨���================
		else if ("sbjg".equalsIgnoreCase(menu)) {

			// �����ƺ��б�
			List<HashMap<String, String>> rychList = dao.getPjpyList("rychdmb",
					"rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);

			// У�ڽ�ѧ���б�
			List<HashMap<String, String>> jxjList = dao.getPjpyList("jxjdmb",
					"jxjdm", "jxjmc", "", "", "");
			request.setAttribute("jxjList", jxjList);
		}
		// =============�������================
		else if ("jdsz".equalsIgnoreCase(menu)) {

			// �����ƺ��б�
			List<HashMap<String, String>> rychList = dao.getPjpyList("rychdmb",
					"rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);

			// ��ѧ���б�
			List<HashMap<String, String>> jxjList = dao.getPjpyList("jxjdmb",
					"jxjdm", "jxjmc", "", "", "");
			request.setAttribute("jxjList", jxjList);

			// �����б���ѧ����������ƺţ�
			List<HashMap<String, String>> lxList = dao
					.getSelectList("jxjorrych");
			request.setAttribute("lxList", lxList);

		}
		// ===========================���з� begin==============================
		else if ("cxf".equalsIgnoreCase(menu)) {

			GyglTyDAO gyDao = new GyglTyDAO();
			String lddm = myForm.getLddm();
			String cs = myForm.getCs();

			// У���б�
			List<HashMap<String, String>> xqdmList = gyDao.getGyglList(
					"dm_zju_xq", "dm", "xqmc", "", "", "");
			request.setAttribute("xqdmList", xqdmList);

			// ¥���б�
			List<HashMap<String, String>> ldList = gyDao.getGyglList("sslddmb",
					"lddm", "ldmc", "", "", "");
			request.setAttribute("ldList", ldList);

			// �����б�
			List<HashMap<String, String>> csList = gyDao.getCsList(lddm);
			request.setAttribute("csList", csList);

			// �����б�
			List<HashMap<String, String>> qsList = gyDao
					.getQsList(lddm, cs, "");
			request.setAttribute("qsList", qsList);

			// �����б�
			List<HashMap<String, String>> qyList = dao.getPjpyList(
					"zjjt_cxf_dj1", "dm", "mc", "", "", "");
			request.setAttribute("qyList", qyList);

			// ����б�
			List<HashMap<String, String>> lbList = dao.getPjpyList(
					"zjjt_cxf_dj2", "dm", "mc", "", "", "");
			request.setAttribute("lbList", lbList);

			// ϸ���б�
			String lb = myForm.getLb();
			String ejdm = !Base.isNull(lb) ? "" : "ejdm";
			List<HashMap<String, String>> xxList = dao.getPjpyList(
					"zjjt_cxf_dj3", "dm", "mc", "", ejdm, lb);
			request.setAttribute("xxList", xxList);
		}
		// ===========================���з� end==============================

		// ===========================�������� begin==============================
		else if ("rssz".equalsIgnoreCase(menu)) {

			// ����
			List<HashMap<String, String>> lxList = dao
					.getSelectList("jxjorrych");
			request.setAttribute("lxList", lxList);

			// ��ѧ���б�
			List<HashMap<String, String>> jxjList = dao.getPjpyList("jxjdmb",
					"jxjdm", "jxjmc", "", "", "");
			request.setAttribute("jxjList", jxjList);

			// �����ƺ��б�
			List<HashMap<String, String>> rychList = dao.getPjpyList("rychdmb",
					"rychdm", "rychmc", "", "", "");
			request.setAttribute("rychList", rychList);
		}
		// ===========================�������� end==============================

		// ===========================�ʾ���� begin==============================
		else if ("wjdc".equalsIgnoreCase(menu)) {

			// ���������б�
			List<HashMap<String, String>> stlxList = dao.getPjpyList(
					"wjdc_stlxb", "dm", "mc", "", "", "");
			request.setAttribute("stlxList", stlxList);

			// �����б�
			List<HashMap<String, String>> ywlxList = dao.getSelectList("ywlx");
			request.setAttribute("ywlxList", ywlxList);

			// ���������б�
			if (!Base.isNull(xn) && !Base.isNull(xq) && !Base.isNull(nd)) {
				pk = "xn||xq||nd";
				pkValue = xn + xq + nd;
			}
			List<HashMap<String, String>> wjList = dao.getPjpyList(
					"wjdc_wjxxb", "id", "wjmc", "", pk, pkValue);
			pk = "";
			pkValue = "";
			request.setAttribute("wjList", wjList);

			// ��������
			List<HashMap<String, String>> stssList = dao.getPjpyList(
					"wjdc_stssb", "dm", "mc", "", "", "");
			request.setAttribute("stssList", stssList);

			// ����б�
			List<HashMap<String, String>> tkList = dao.getPjpyList(
					"view_wjdc_stxx", "stbh", "xsmc", "", "", "");
			request.setAttribute("tkList", tkList);

			// ����б�
			List<HashMap<String, String>> overList = dao.getSelectList("over");
			request.setAttribute("overList", overList);

			// �Ƿ����
			List<HashMap<String, String>> zzList = dao.getSelectList("zz");
			request.setAttribute("zzList", zzList);

			// �Ա��б�
			List<HashMap<String, String>> xbList = dao.getSelectList("xblx");
			request.setAttribute("xbList", xbList);
		}
		// ============================�ʾ���� begin==============================

		// ===========================Ʒ������ begin==============================
		else if ("pxpj".equalsIgnoreCase(menu)) {
			// ���������б�
			List<HashMap<String, String>> wjList = dao.getPjpyList(
					"wjdc_wjxxb", "id", "wjmc", "", "", "");
			request.setAttribute("wjList", wjList);
			// �Ա��б�
			List<HashMap<String, String>> fpList = dao.getSelectList("fplx");
			request.setAttribute("fpList", fpList);
		}
		// ============================Ʒ������ begin==============================
	}

	/**
	 * ���ѧ����Ϣ
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}

	/**
	 * ���ѧ����Ϣ
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDetStuInfo(String xh) {
		return CommonQueryDAO.getDetStuInfo(xh);
	}

	/**
	 * �������������Ϣ���б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getPjpyList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getPjpyListInfo(tableName, model, colList, other_query);
	}
	
	/**
	 * �������������Ϣ���б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getPjpyList(PjpyTyForm model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getPjpyListInfo(model, colList, other_query);
	}

	/**
	 * ����������������Ϣ����ϸ��
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 */
	public HashMap<String, String> getPjpyInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getPjpyInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * ɾ������������Ϣ
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @throws Exception
	 */
	public boolean delPjpy(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();

		return dao.delDate(form, model);
	}

	/**
	 * �����������������Ϣ��������
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param arrzd(�����ֶ�)
	 * @param onezd(��һ�ֶ�)
	 * @param notnull(�ǿ��ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean savePjpy(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}

	/**
	 * �����������������Ϣ��������
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param onezd(��һ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean savePjpy(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * �����������������Ϣ
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param onezd(��һ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean updatePjpy(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}

	/**
	 * @describe ɾ�����ϴ��ļ�
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param dzzd(��ַ�ֶ�)
	 * 
	 * @throws Exception
	 */
	public boolean fileDel(String tableName, String dzzd, String pk,
			String pkValue) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.fileDel(tableName, dzzd, pk, pkValue);
	}

	/**
	 * ���ϵͳ��ǰʱ��
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		DAO dao = DAO.getInstance();
		return dao.getNowTime(lx);
	}

	/**
	 * ���ָ�����ָ���ֶ�
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		return dao.getOneValue(tableName, dm, pk, pkValue);
	}

	/**
	 * ����������ظ����ݣ�ɾȥ�ظ�����,���ز��ظ�����
	 * 
	 * @author luojw
	 */
	public String[] checkCfsj(String[] first, String[] second) {

		if (first != null && first.length > 0 && second != null
				&& second.length > 0) {
			String[] arr = null;
			List<String> fir = new ArrayList<String>(Arrays.asList(first));
			List<String> sec = Arrays.asList(second);

			fir.removeAll(sec);

			List<String> list = new ArrayList<String>(fir);

			if (list != null && list.size() > 0) {
				arr = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					arr[i] = list.get(i);
				}
			}
			return arr;
		} else {
			return first;
		}
		// int n = 0;
		// String[] arr = null;
		// if (first != null && first.length > 0 && second != null
		// && second.length > 0) {
		// for (int i = 0; i < first.length; i++) {
		// if (Base.isNull(first[i])) {
		// n++;
		// } else {
		// for (int j = 0; j < second.length; j++) {
		// if (first[i].equalsIgnoreCase(second[j])) {
		// first[i] = "";
		// n++;
		// }
		// }
		// }
		// }
		// arr = new String[first.length - n];
		// n = 0;
		// for (int i = 0; i < first.length; i++) {
		// if (!Base.isNull(first[i])) {
		// arr[n] = first[i];
		// n++;
		// }
		// }
		// }
	}

	/**
	 * ���ָ��ѧԺ��רҵ���༶��ѧ��ѧ��
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public String[] getZdXh(String lx, String dm) throws SQLException {
		return dao.getZdXh(lx, dm);
	}

	/**
	 * ��ѯ��������б�
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> queryYhlxList() {
		return dao.queryYhlxList();
	}

	/**
	 * ��ѯ��������б�
	 * 
	 * @return
	 */
	public Boolean getNeedXq(String xxdm) {

		boolean flag = false;

		// ������Ҫѧ�ڵ�ѧУ
		// Ŀǰ������
		// ��ýѧԺ
		String[] needXq = new String[] { Globals.XXDM_ZJCMXY,Globals.XXDM_NTZYDX };

		if (needXq != null && needXq.length > 0) {
			for (int i = 0; i < needXq.length; i++) {
				if (xxdm.equalsIgnoreCase(needXq[i])) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * ����Request ��ֵ
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setRequestValue(RequestForm rForm, HttpServletRequest request) {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String writeAble = request.getParameter("writeAble");
		String title = rForm.getTitle();

		// ��ͼ��
		String tableName = rForm.getTableName();
		if (!Base.isNull(tableName)) {
			request.setAttribute("tableName", tableName);
		}

		// ����
		String realTable = rForm.getRealTable();
		if (!Base.isNull(realTable)) {
			request.setAttribute("realTable", realTable);
		}

		// ģ��·��
		String path = rForm.getPath();
		if (!Base.isNull(path)) {
			request.setAttribute("path", path);
		}

		// �û�����
		userType = Base.isNull(userType) ? rForm.getUserType() : userType;
		if (!Base.isNull(userType)) {
			request.setAttribute("userType", userType);
		}

		// �û���
		userName = Base.isNull(userName) ? rForm.getUserName() : userName;
		if (!Base.isNull(path)) {
			request.setAttribute("userName", userName);
		}

		// �û����ڲ���
		userDep = Base.isNull(userDep) ? rForm.getUserDep() : userDep;
		if (!Base.isNull(path)) {
			request.setAttribute("userDep", userDep);
		}

		// ��������
		doType = Base.isNull(doType) ? rForm.getDoType() : doType;
		if (!Base.isNull(doType)) {
			request.setAttribute("doType", doType);
		}

		// ��дȨ���
		if (Base.isNull(writeAble)) {
			String[] message = FormModleCommon.getWriteAbleAndTitle(request);
			writeAble = message != null && message.length >= 1 ? message[0]
					: "";
			if (Base.isNull(title)) {
				title = message != null && message.length >= 2 ? message[1]
						: "";
			}
		}
		request.setAttribute("writeAble", writeAble);

		// ģ�����
		if (!Base.isNull(title)) {
			request.setAttribute("title", title);
		}

		// ����
		String pk = rForm.getPk();
		if (!Base.isNull(pk)) {
			request.setAttribute("pk", pk);
		}

		// ģ�K���
		String mklx = rForm.getMklx();
		if (!Base.isNull(mklx)) {
			request.setAttribute("mklx", mklx);
		}

		// ��ϸ��Ϣ
		HashMap<String, String> rs = rForm.getRs();
		if (rs != null && rs.size() > 0) {
			request.setAttribute("rs", rs);
		}

		// ��ϸ�б���Ϣ
		List<HashMap<String, String>> rsList = rForm.getRsList();
		if (rsList != null && rsList.size() > 0) {
			request.setAttribute("rsList", rsList);
		}

		// �����ֶ�
		String[] qtzd = rForm.getQtzd();
		// �����ֶ�ֵ
		String[] qtzdz = rForm.getQtzdz();

		if (qtzd != null && qtzdz != null && (qtzd.length == qtzdz.length)) {
			for (int i = 0; i < qtzd.length; i++) {
				request.setAttribute(qtzd[i], qtzdz[i]);
			}
		}

	}

	/**
	 * ����������ŷ�ҳ
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getResultList(ArrayList<String[]> list,
			PjpyTyForm model) {

		// ��ҳ
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		if (list != null && list.size() > 0) {

			Pages pages = model.getPages();
			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}

		return rsList;
	}

	/**
	 * ����������ŷ�ҳ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getResultList(
			List<HashMap<String, String>> list, PjpyTyForm model) {

		// ��ҳ
		List<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();

		if (list != null && list.size() > 0) {

			Pages pages = model.getPages();
			pages.setMaxRecord(list.size());
			int start = pages.getStart();
			int size = pages.getPageSize();

			for (int i = start; i < start + size; i++) {
				if (i < list.size()) {
					rsList.add(list.get(i));
				}
			}
		}

		return rsList;
	}

	/**
	 * �������ŵ���
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expPjpyData(String title, List<HashMap<String, String>> topTr,
			ArrayList<String[]> list, OutputStream os) throws Exception {

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ����ͷ
		if (topTr != null && topTr.size() > 0) {
			for (int i = 0; i < topTr.size(); i++) {
				HashMap<String, String> map = topTr.get(i);
				ws.addCell(new Label(i, 0, map.get("cn"), wcf2));
			}
		}

		// �������
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				String[] info = list.get(i);

				if (info != null && info.length > 0) {

					for (int j = 0; j < info.length; j++) {
						ws.addCell(new Label(j, i + 1, info[j], wcf2));
					}
				}
			}
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * ��ȡ��������
	 * @return String
	 * */
	public String getPjpySqzq (){
		DAO dao = DAO.getInstance();
		String sqzq = "";//��������
		String sql = "select * from pjpy_pjzqb ";
		HashMap<String, String> map = dao.getMapNotOut(sql, new String[]{});
		if("checked".equalsIgnoreCase(map.get("xq"))){
			sqzq = "xq";
		}else if("checked".equalsIgnoreCase(map.get("nd"))){
			sqzq = "nd";
		}else if("checked".equalsIgnoreCase(map.get("xn"))){
			sqzq = "xn";
		}
		return sqzq;
	}
	
	/**
	 * ��ȡ�ۺϲ�������
	 * @return String
	 * */
	public String getZhcpSqzq (){
		DAO dao = DAO.getInstance();
		String sqzq = "";//��������
		String sql = "select * from pjpy_pjzqb ";
		HashMap<String, String> map = dao.getMapNotOut(sql, new String[]{});
		if("checked".equalsIgnoreCase(map.get("zcxq"))){
			sqzq = "xq";
		}else if("checked".equalsIgnoreCase(map.get("zcnd"))){
			sqzq = "nd";
		}else if("checked".equalsIgnoreCase(map.get("zcxn"))){
			sqzq = "xn";
		}
		return sqzq;
	}
	
	/**
	 * ��ȡ���������ֶ�
	 * @param pjzq
	 * @return String[]
	 * */
	public String[] getPjzqzd(String pjzq){
		String[] pjzqzd = {};
		if("xn".equalsIgnoreCase(pjzq)){
			pjzqzd = new String[]{"xn"};
		}else if("nd".equalsIgnoreCase(pjzq)){
			pjzqzd = new String[]{"nd"};
		}else if("xq".equalsIgnoreCase(pjzq)){
			pjzqzd = new String[]{"xn","xqmc"};
		}
		
		return pjzqzd;
	}
	
	/**
	 * �жϹ����Ƿ��жϿ�������
	 * @return boolean
	 * */
	public boolean checkKgflag(){
		boolean flag = false;
		if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			//�㽭�Ƽ�
			flag = true;
		}else if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			//��ְͨҵ
			flag = true;
		}
		
		return flag;
	}
}
