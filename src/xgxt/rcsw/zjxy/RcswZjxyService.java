package xgxt.rcsw.zjxy;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.SaveForm;
import xgxt.mdqr.MdqrForm;
import xgxt.mdqr.MdqrModel;
import xgxt.rcsw.RcswForm;
import xgxt.rcsw.RcswService;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.xginterface.gxt.GxtWebService;
import xgxt.xginterface.gxt.SendMess;

import com.zfsoft.basic.BasicService;

public class RcswZjxyService extends RcswService {

	RcswZjxyDAO dao = new RcswZjxyDAO();

	/**
	 * ��ø��Ի���ͷ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {

		DAO dao = DAO.getInstance();
		String[] colListCN = null;
		String[] colListEN = null;

		if ("swff_jg".equalsIgnoreCase(lx)
				&& "13275".equalsIgnoreCase(Base.xxdm)) {
			colListCN = new String[] { "ѧ��", "����", Base.YXPZXY_KEY,"��Ŀ����","��Ŀ����", "ѧ��", "ѧ��", "���",
					 "����ʼʱ��", "�������ʱ��", "�Ƿ����" };
			colListEN = new String[] { "zgh", "xm", "xymc","xmlxmc","xmmc", "xn", "xqmc", "nd",
					 "ffsj","ffjssj", "isff" };
		} else if ("ffdx_xs".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "ѧ��", "����","�꼶", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����" };
			colListEN = new String[] { "xh", "xm","nj", "xymc", "zymc", "bjmc" };
		} else if ("ffdx_ls".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "ѧ��", "����", "��������", "ְ������" };
			colListEN = new String[] { "xh", "xm", "bmmc", "zwmc" };
		} else if ("swff_jg".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "ְ����", "����", "���ڲ���", "���", "ѧ��", "ѧ��",
					"���", "������Ŀ", "����ʱ��", "�Ƿ񷢷�" };
			colListEN = new String[] { "zgh", "xm", "xymc", "lx", "xn", "xqmc",
					"nd", "xmmc", "ffsj", "isff" };
		} else if ("swff_jgtj".equalsIgnoreCase(lx)) {
			colListCN = new String[] { "ѧ��", "ѧ��", "���", "��Ŀ����", "��Ŀ����", "����ʼʱ��",
					"�������ʱ��", "Ӧ��������", "�Ѱ�������", "δ��������","���Ͷ�������","�յ���������","�Ѿ���������","δ��������" };
			colListEN = new String[] { "xn", "xq", "nd", "xmlx", "xmmc", "ffsj",
					"ffjssj", "xffrs", "yffrs", "wffrs","ytz","yfs","ypj","wpj" };
		}

		return dao.arrayToList(colListEN, colListCN);
	}

	/**
	 * ����ʵ�﷢����Ŀά��
	 * 
	 * @author luojw
	 * 
	 * @throws Exception
	 */
	public Boolean saveSwffXmwh(RcswZjxyModel model, String tableName)
			throws Exception {

		String[] ffrq = model.getFfrq();

		boolean flag = false;

		if (ffrq != null && ffrq.length > 0) {

			String pk = "xn||xq||nd||xmlx||ffsj";
			String pkValue = model.getXn() + model.getXq() + model.getNd()
					+ model.getXmlx() + model.getFfsj();
			String[] arrzd = new String[] { "ffrq" };
			String[] onezd = new String[] { "xn", "xq", "nd", "xmlx", "ffsj",
					"bz" };

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);

			flag = saveRcsw(saveForm, model);
		}

		return flag;
	}

	/**
	 * ɾ��ʵ�﷢����Ŀά��
	 * 
	 * @author luojw
	 * 
	 * @throws Exception
	 */
	public Boolean delSwffXmwh(RcswZjxyModel model, String[] pkValue,
			String realTable) throws Exception {

		String pk = "xn || xq || nd || xmlx || ffsj";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		// ɾ��������Ŀ
		boolean flag = delRcsw(saveForm, model);

		if (flag) {
			realTable = "rcsw_swffrqwhb";
			saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			// ɾ��������Ⱥ
			flag = delRcsw(saveForm, model);
		}
		return flag;
	}

	/**
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public void delSwffXmwh(String[] pkValue) throws Exception {
		dao.delSwffXmwh(pkValue);
	}

	/**
	 * ����ʵ�﷢�ŷ��Ŷ���
	 * 
	 * @author luojw
	 * 
	 * @throws Exception
	 */
	public Boolean saveSwffFfdx(RcswZjxyModel model, String tableName)
			throws Exception {

		boolean flag = false;

		// ѧ��ְ����
		String[] xhzgh = model.getXhzgh();
		// ��ʱѧ�ţ�ְ���ţ�
		String[] temp_xhzgh = checkCfsj(dao.getLsXhZgh(), xhzgh);
		int temp_Size = (temp_xhzgh != null && temp_xhzgh.length > 0) ? temp_xhzgh.length
				: 0;
		// ��ѡѧ��(ְ����)
		String[] checkXhZgh = model.getCheckVal();

		// ��������ѧ��(ְ����)
		String[] addXhZgh = new String[temp_Size + checkXhZgh.length];
		int m = 0;
		for (int i = 0; i < checkXhZgh.length; i++) {
			addXhZgh[m] = checkXhZgh[i];
			m++;
		}
		for (int i = 0; i < temp_Size; i++) {
			addXhZgh[m] = temp_xhzgh[i];
			m++;
		}

		// ����ɾ��ѧ�ţ�ְ���ţ�
		String[] no_checkXhZgh = checkCfsj(xhzgh, checkXhZgh);
		int temp_no_Size = (no_checkXhZgh != null && no_checkXhZgh.length > 0) ? no_checkXhZgh.length
				: 0;
		String[] delXhZgh = new String[temp_no_Size + addXhZgh.length];

		int n = 0;
		for (int i = 0; i < temp_no_Size; i++) {
			delXhZgh[n] = no_checkXhZgh[i];
			n++;
		}
		for (int i = 0; i < addXhZgh.length; i++) {
			delXhZgh[n] = addXhZgh[i];
			n++;
		}

		// ��������
		String pk = "xn||xq||nd||xmlx||ffsj||lx||xhzgh";
		String[] pkValue = new String[delXhZgh.length];
		for (int i = 0; i < delXhZgh.length; i++) {
			pkValue[i] = model.getXn() + model.getXq() + model.getNd()
					+ model.getXmlx() + model.getFfsj() + model.getLx()
					+ delXhZgh[i];
		}

		String[] arrzd = new String[] { "xhzgh" };
		String[] onezd = new String[] { "xn", "xq", "nd", "xmlx", "ffsj", "lx",
				"ffr", "ffbz" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		model.setXhzgh(addXhZgh);

		flag = saveRcsw(saveForm, model);

		return flag;
	}

	/**
	 * ����ʵ�﷢����Ա��Ϣ
	 * 
	 * @author qlj
	 * 
	 * @throws Exception
	 */
	public Boolean saveSwffRyxx(RcswZjxyModel model, String tableName)
			throws Exception {

		boolean flag = false;

		// ѧ��(hidden ������ ����)
		String[] xhzgh = model.getXhzgh();

		// ѧ��(checkbox ��ѡ�� �豣��)
		String[] checkVal = model.getCheckVal();

		// ����ɾ��ѧ�ţ�ְ���ţ�

		// ��������
		String pk = "xn||xq||nd||xmlx||ffsj||xhzgh";
		String[] pkValue = new String[xhzgh.length];

		// ��Ҫɾ��������
		for (int i = 0; i < xhzgh.length; i++) {
			pkValue[i] = model.getXn() + model.getXq() + model.getNd()
					+ model.getXmlx() + model.getFfsj() + xhzgh[i];
		}

		// ���������
		String[] arrzd = new String[] { "xhzgh" };
		String[] onezd = new String[] { "xn", "xq", "nd", "xmlx", "ffsj", };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		model.setXhzgh(checkVal);

		flag = saveRcsw(saveForm, model);

		return flag;
	}

	/**
	 * ���ʵ�﷢��ά����Ϣ
	 * 
	 * @author luojw
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> getSwffXmwhInfo(String pk) {

		List<HashMap<String, String>> list = dao.getSwffXmwhInfo(pk);
		HashMap<String, String> map = new HashMap<String, String>();
		if (list != null && list.size() > 0) {
			map = list.get(0);
			String ffrq = "";
			for (int i = 0; i < list.size(); i++) {
				ffrq += list.get(i).get("ffrq") + "!!@@!!";
			}
			map.put("ffrq", ffrq);
		}

		return map;
	}

	/**
	 * ��÷��Ŷ����б���Ϣ��ѧ����
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getXsFfdxList(RcswZjxyModel model, String rq)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ��Ŀ������Ⱥ
		String[] ffrq = rq.split("!!@@!!");
		// �ǰ�ɲ���Ⱥ
		List<HashMap<String, String>> ffrqList = getSelectList("ffrq");
		// �ɲ��б�
		List<String> gbList = new ArrayList<String>();

		// ɾ����ѧ����Ⱥ
		if (ffrq != null && ffrq.length > 0) {
			boolean flag = true;
			for (int i = 0; i < ffrq.length; i++) {
				for (int j = 0; j < ffrqList.size(); j++) {
					if (ffrq[i].equalsIgnoreCase(ffrqList.get(j).get("en"))) {
						flag = false;
					}
				}
				if (flag) {
					gbList.add(ffrq[i]);
				}
			}
		}

		// �༶ѧ���б�
		ArrayList<String[]> bjxsList = null;

		// ѧ���ɲ��б�
		List<HashMap<String, String>> xsgbList = getRcglList("sxjy_bjgbzlb",
				"bjgbmc", "bjgbmc", "", "", "");// ��ɲ�
		// �ɲ��б�
		List<String> allGbList = new ArrayList<String>();
		if (xsgbList != null && xsgbList.size() > 0) {
			for (int i = 0; i < xsgbList.size(); i++) {
				String gmmc = xsgbList.get(i).get("mc");
				allGbList.add(gmmc);
			}
		}

		// �жϷ�����Ⱥ�Ƿ��а༶
		boolean isBj = false;

		// �ɲ�ѧ���б�
		ArrayList<String[]> gbxsList = null;

		if (ffrq != null && ffrq.length > 0) {
			for (int i = 0; i < ffrq.length; i++) {
				if ("�༶".equalsIgnoreCase(ffrq[i])) {
					isBj = true;
					bjxsList = getBjxsList(model, allGbList);
					gbxsList = getGbxsList(model, allGbList);
				}
			}
		}

		// �ɲ�ѧ���б�(δ���ð༶)
		if (gbList != null && gbList.size() > 0 && !isBj) {
			gbxsList = getGbxsList(model, gbList);
		}

		// ѧ���б�
		ArrayList<String[]> xsList = new ArrayList<String[]>();
		if (bjxsList != null && bjxsList.size() > 0) {
			xsList.addAll(bjxsList);
		}
		if (gbxsList != null && gbxsList.size() > 0) {
			xsList.addAll(gbxsList);
		}

		// ��ҳ
		Pages pages = model.getPages();
		int size = pages.getPageSize();
		int start = pages.getStart();
		int end = start + size;

		if (xsList != null && xsList.size() > 0) {
			pages.setMaxRecord(xsList.size());
		}

		// �����б�
		ArrayList<String[]> list = new ArrayList<String[]>();
		if (xsList != null && xsList.size() > 0) {
			for (int i = start; i < end; i++) {
				if (xsList.size() > i) {
					list.add(xsList.get(i));
				}
			}
		}
		return list;
	}

	/**
	 * ��÷��Ŷ����б���Ϣ����ʦ��
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getLsFfdxList(RcswZjxyModel model, String rq)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ��Ŀ������Ⱥ
		String[] ffrq = rq.split("!!@@!!");

		// �������б�
		ArrayList<String[]> bzrList = null;
		// ����Ա�б�
		ArrayList<String[]> fdyList = null;

		if (ffrq != null && ffrq.length > 0) {
			for (int i = 0; i < ffrq.length; i++) {
				String zw = ffrq[i];
				if ("����Ա".equalsIgnoreCase(zw)) {
					fdyList = getFdyxxList(model);
				} else if ("������".equalsIgnoreCase(zw)) {
					bzrList = getBzrxxList(model);
				}
			}
		}

		// ��ʦ�б�
		ArrayList<String[]> lsList = new ArrayList<String[]>();
		if (fdyList != null && fdyList.size() > 0) {
			lsList.addAll(fdyList);
		}
		if (bzrList != null && bzrList.size() > 0) {
			lsList.addAll(bzrList);
		}

		// ��ҳ
		Pages pages = model.getPages();
		int size = pages.getPageSize();
		int start = pages.getStart();
		int end = start + size;

		if (lsList != null && lsList.size() > 0) {
			pages.setMaxRecord(lsList.size());
		}

		// �����б�
		ArrayList<String[]> list = new ArrayList<String[]>();
		if (lsList != null && lsList.size() > 0) {
			for (int i = start; i < end; i++) {
				if (lsList.size() > i) {
					list.add(lsList.get(i));
				}
			}
		}
		return list;
	}

	/**
	 * ��÷��Ŷ����б���Ϣ��ѧ����
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getXsFfdxList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		ArrayList<String[]> xsList = dao.getXsFfdxList(model);

		// ��ҳ
		Pages pages = model.getPages();
		int size = pages.getPageSize();
		int start = pages.getStart();
		int end = start + size;

		if (xsList != null && xsList.size() > 0) {
			pages.setMaxRecord(xsList.size());
		}

		// �����б�
		ArrayList<String[]> list = new ArrayList<String[]>();
		if (xsList != null && xsList.size() > 0) {
			for (int i = start; i < end; i++) {
				if (xsList.size() > i) {
					list.add(xsList.get(i));
				}
			}
		}

		return list;
	}

	/**
	 * ��÷��Ŷ����б���Ϣ����ʦ��
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getLsFfdxList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		ArrayList<String[]> xsList = dao.getLsFfdxList(model);

		// ��ҳ
		Pages pages = model.getPages();
		int size = pages.getPageSize();
		int start = pages.getStart();
		int end = start + size;

		if (xsList != null && xsList.size() > 0) {
			pages.setMaxRecord(xsList.size());
		}

		// �����б�
		ArrayList<String[]> list = new ArrayList<String[]>();
		if (xsList != null && xsList.size() > 0) {
			for (int i = start; i < end; i++) {
				if (xsList.size() > i) {
					list.add(xsList.get(i));
				}
			}
		}

		return list;
	}

	/**
	 * ��ð༶ѧ��
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getBjxsList(RcswZjxyModel model,
			List<String> gbList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getBjxsList(model, gbList);
	}

	public ArrayList<String[]> getFfxsList(RcswZjxyModel model)
			throws Exception {
		return dao.getFfxsList(model);
	}

	/**
	 * ��ð�ɲ�ѧ��
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getGbxsList(RcswZjxyModel model,
			List<String> gbList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getGbxsList(model, gbList);
	}

	/**
	 * ��ø���Ա��Ϣ�б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getFdyxxList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getFdyxxList(model);
	}

	/**
	 * ��ð�������Ϣ�б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getBzrxxList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getBzrxxList(model);
	}

	/**
	 * ���汻��������Ϣ(�����������ʱ�޷�����ͨ�÷���)
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void saveBffzxx(RcswZjxyModel model) throws Exception {

		// ��ձ���Ϣ
		delffzxx();

		String tableName = "rcsw_swffbffzb";
		String pk = "xhzgh||lx";
		String[] pkValue = model.getXhzgh();

		String[] arrzd = new String[] { "xhzgh" };
		String[] onezd = new String[] { "lx" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		saveRcsw(saveForm, model);

	}

	/**
	 * ɾ������������Ϣ
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void delffzxx() throws Exception {
		dao.delffzxx();
	}

	/**
	 * ��÷�����Ŀ����б�
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @throws Exception
	 */
	public ArrayList<String[]> getFfjgList(RcswZjxyModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// ������Ⱥ�б�
		/*
		 * List<HashMap<String, String>> gbList = getRcglList("sxjy_bjgbzlb",
		 * "bjgbmc", "bjgbmc", "", "", "");// ��ɲ� List<HashMap<String,
		 * String>> xmList = dao.getXmList(model);
		 */
		ArrayList<String[]> ffjgList = dao.getFfjgList(model);

		return ffjgList;
	}

	/**
	 * ʵ�﷢����������(����)
	 * 
	 * @author luojw
	 * 
	 * @throws Exception
	 */
	/**
	 * ʵ�﷢����������(����)
	 * 
	 * @author luojw
	 * @throws SQLException
	 * 
	 * @throws Exception
	 */
	public Boolean setSwffmd(String pk, String userName) throws SQLException {
		return dao.setSwffmd(pk, userName);
	}

	/**
	 * ����ְ���ź�ѧ�� ��ȡ������Ϣ
	 * 
	 * @throws Exception
	 */
	public void getFfxxByZgh(HttpServletRequest request, RcswForm myForm)
			throws Exception {

		BasicService basicService = new BasicService();
		MakeQuery mQuery = new MakeQuery();

		// ��ѯ�ֶ�
		String[] outPutStr = { "pkValue", "zgh", "xm", "xymc", "xn", "xqmc",
				"nd","xmlxmc", "xmmc", "ffsj","ffjssj", "mycd" };
		// ��ѯ����
		String[] colList = { "xn", "xq", "nd", "zgh", "ffsj", "xmlx" };
		// ģ����ѯ����
		String[] colLikeList = { "zgh" ,"xmmc"};
		// ���ɲ�ѯ����
		mQuery.makeQuery(colList, colLikeList, myForm);

		StringBuilder sql = new StringBuilder();
		StringBuilder query = new StringBuilder();
		query.append(getFfpjTj(myForm));
		query.append(" and isff='����' ");
		sql.append(" select rownum r,a.* from(select distinct (a.xhzgh)zgh, a.xn||a.xq||a.nd||b.xmlx||a.ffsj||b.lx||a.xhzgh pkValue,a.xm,");
		sql.append(" (case when b.mycd='fcmy' then '�ǳ�����' when b.mycd='my' then '����' ");
		sql.append("  when b.mycd='bjmy' then '�Ƚ�����' when b.mycd='bmy' then '������' end)mycd,a.xymc, a.xn, a.xq, a.nd,b.xmlx, ");
		sql.append(" (select xqmc from xqdzb where a.xq=xqdm)xqmc, a.xmmc,a.xmlxmc,a.ffjssj, a.ffsj,case when b.xhzgh is null then 'δ����' else '����' end isff from view_xg_swff_ffryb a ");
		sql.append(" left join rcsw_swffrqwhb b on a.xn = b.xn and a.xq = b.xq ");
		sql.append(" and a.nd = b.nd and a.xmlx = b.xmlx and a.ffsj = b.ffsj and a.xhzgh = b.xhzgh)a ");
		
		// ��ѯ���
		System.out.println(sql.toString() + mQuery.getQueryString() + query);
		request.setAttribute("rs", CommonQueryDAO.commonQuery(sql.toString(),
				mQuery.getQueryString() + query, mQuery.getInputList(),
				outPutStr, myForm));
		// ��ʾ��ͷ
		request.setAttribute("topTr", basicService.getTopTr("mdqr_xmszb",
				new String[] { "ѧ��", "����", Base.YXPZXY_KEY+"����", "ѧ��", "ѧ��", "���","��Ŀ����", "��Ŀ����",
						"����ʼʱ��","�������ʱ��", "����̶�" }));
	}

	/**
	 * ��ȡʵ�﷢��ͳ����Ϣ
	 * 
	 * @throws Exception
	 */
	public void getFfxx(HttpServletRequest request, RcswForm myForm)
			throws Exception {

		BasicService basicService = new BasicService();
		MakeQuery mQuery = new MakeQuery();

		// ��ѯ�ֶ�
		String[] outPutStr = { "pkValue", "zgh", "xm", "xymc", "xn", "xqmc",
				"nd", "xmmc", "ffsj", "mycd" };
		// ��ѯ����
		String[] colList = { "xn", "xq", "nd", "ffsj", "xmlx" };
		// ģ����ѯ����
		String[] colLikeList = { "zgh", "xm" };
		// ���ɲ�ѯ����
		mQuery.makeQuery(colList, colLikeList, myForm);
		
		StringBuilder sql = new StringBuilder();
		StringBuilder query = new StringBuilder();
		query.append(getFfpjTj(myForm));
		query.append(" and  mycd is not null ");
		sql.append(" select a.*,rownum r from  view_rcsw_swffrqwh a ");
		// ��ѯ���
		request.setAttribute("rs", CommonQueryDAO.commonQuery(sql.toString(),
				mQuery.getQueryString() + query, mQuery.getInputList(),
				outPutStr, myForm));
		// ��ʾ��ͷ
		request.setAttribute("topTr", basicService.getTopTr("mdqr_xmszb",
				new String[] { "ְ����", "����", Base.YXPZXY_KEY+"����", "ѧ��", "ѧ��", "���", "��Ŀ����",
						"����ʱ��", "����̶�" }));
	}

	/**
	 * ��ȡʵ�﷢�����۲�ѯ����
	 * 
	 * @return
	 */
	public String getFfpjTj(RcswForm myForm) {
		StringBuilder sb = new StringBuilder();

		if (!"".equalsIgnoreCase(myForm.getKssj())) {
			sb.append(" and ffsj>= " + myForm.getKssj());
		}

		if (!"".equalsIgnoreCase(myForm.getJssj())) {
			sb.append(" and ffsj<= " + myForm.getJssj());
		}
		if (!"".equalsIgnoreCase(myForm.getFfjskssj())) {
			sb.append(" and ffjssj>= " + myForm.getFfjskssj());
		}

		if (!"".equalsIgnoreCase(myForm.getFfjsjssj())) {
			sb.append(" and ffjssj<= " + myForm.getFfjsjssj());
		}

		return sb.toString();
	}

	/**
	 * ������������ʱ��ֵ
	 * 
	 */
	public HashMap<String, String> setPlpj(RcswForm myForm) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("mycd", myForm.getMycd());
		hashMap.put("pjyj", myForm.getPjyj());
		return hashMap;
	}

	/**
	 * ��ȡ�������ż�¼
	 */
	public HashMap<String, String> getOneFfjl(String pkValue) {
		return dao.getOneFfjl(pkValue);
	}

	/**
	 * ѧ���ɼ�ͳ�ƴ�ӡ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void pjxxTj(WritableWorkbook wwb, RcswForm myForm) throws Exception {

		// ��ȡ����ͳ����Ϣ
		List<String[]> list = dao.getPjtjXx(myForm);

		try {
			// ����xls��SHEET����
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			
			// ���ö��뷽ʽ
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);
			
			//���ⵥԪ��ϲ�
			ws.mergeCells(0, 0, 11, 0);
			//����
			ws.addCell(new Label(0,0,"                               			      			 ʵ�﷢������ͳ��               ",
						wcfTytle));
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setWrap(true);
			// ���ñ��߿�
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			//��ͷ
			ws.addCell(new Label(0, 1, "��Ŀ����", wcfTytle));
			ws.addCell(new Label(1, 1, "��Ŀ����", wcfTytle));
			ws.addCell(new Label(2, 1, "ѧ��", wcfTytle));
			ws.addCell(new Label(3, 1, "ѧ��", wcfTytle));
			ws.addCell(new Label(4, 1, "���", wcfTytle));
			ws.addCell(new Label(5, 1, "����ʼʱ��", wcfTytle));
			ws.addCell(new Label(6, 1, "�������ʱ��", wcfTytle));
			ws.addCell(new Label(7, 1, "�ǳ�����", wcfTytle));
			ws.addCell(new Label(8, 1, "�Ƚ�����", wcfTytle));
			ws.addCell(new Label(9, 1, "����", wcfTytle));
			ws.addCell(new Label(10, 1, "������", wcfTytle));
			ws.addCell(new Label(11, 1, "δ����", wcfTytle));
			
			//ѧ������ͳ����Ϣ
			for (int j = 0; j < list.size(); j++) {
				String[] tjxxArr = list.get(j);
				for(int i=0;i<tjxxArr.length;i++){
					ws.addCell(new Label(i, j + 2, tjxxArr[i], wcfTytle));
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ��ͻ������
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * ʵ�﷢����ȡ֪ͨ
	 * 
	 * @param myForm
	 * @return String
	 * @throws Exception
	 */
	public String saveLqtz(RcswForm myForm) throws Exception {

		// ����Ҫ����֪ͨ���û�Ⱥ ��ӵ� ʵ�﷢����ȡ֪ͨ��
		dao.saveLqtzInfo(myForm);
		// ���Ͷ��� ��ȡ���ŷ��ͽ��
		return sendMessage(dao.getLqtzByqr(myForm), myForm.getUserName(),myForm);
	}

	public String getQuery(RcswForm myForm) {
		StringBuilder sb = new StringBuilder();
		if (!Base.isNull(myForm.getXn())) {
			sb.append(" and xn='" + myForm.getXn() + "' ");
		}

		if (!Base.isNull(myForm.getXq())) {
			sb.append(" and xq='" + myForm.getXq() + "' ");
		}

		if (!Base.isNull(myForm.getNd())) {
			sb.append(" and nd='" + myForm.getNd() + "' ");
		}

		if (!Base.isNull(myForm.getXmlx())) {
			sb.append(" and xmlx='" + myForm.getXmlx() + "' ");
		}

		if (!Base.isNull(myForm.getFfsj())) {
			sb.append(" and ffsj='" + myForm.getFfsj() + "' ");
		}
		return sb.toString();
	}

	public String getPassWord() {
		HashMap<String, String> passMap = dao.getPassWord();
		String passWord = passMap.get("gxtmy");
		return passWord;
	}

	/**
	 * ���Ͷ���Ϣ method sendMessage
	 * 
	 * @param dxtzList
	 *            author qlj
	 * @throws Exception
	 */
	public String sendMessage(List<HashMap<String, String>> dxtzList,
			String userName,RcswForm myForm) throws Exception {

		GxtWebService service = new GxtWebService();
		String[] sendResult = null;
		if (dxtzList != null) {
			sendResult = new String[dxtzList.size()];
		}
		for (int i = 0; i < dxtzList.size(); i++) {

			StringBuilder msg = new StringBuilder();

			HashMap<String, String> dxtzMap = dxtzList.get(i);
			SendMess sendMess = new SendMess();
			sendMess.setPassWord(getPassWord());
			// ѧ��
			sendMess.setMps(dxtzMap.get("xhzgh"));
			// ��½��
			sendMess.setLoginName(userName);
			// Ψһ��ʶ
			sendMess.setUserDefId(Long.parseLong(dxtzMap.get("userdefid")));
			
			String ffsj=dxtzMap.get("ffsj").substring(0,4)+"��"+dxtzMap.get("ffsj").substring(4,6)+"��"+dxtzMap.get("ffsj").substring(6,8)+"��";
			String ffjssj=dxtzMap.get("ffjssj").substring(0,4)+"��"+dxtzMap.get("ffjssj").substring(4,6)+"��"+dxtzMap.get("ffjssj").substring(6,8)+"��";
			
			msg.append(dxtzMap.get("xm") + "ͬѧ���ã�");
			msg.append("����" + ffsj);
			if(!ffsj.equalsIgnoreCase(ffjssj)){
				msg.append("��"+ffjssj);
			}else{
				msg.append("(���޵���)");
			}
			
			// ������Ϣƴ��
			
			msg.append("ǰ��" + dxtzMap.get("ffdd")
					+ "��ȡ��" + dxtzMap.get("xmmc")+"����");
			sendMess.setSendTime(dxtzMap.get("dxtzsj"));
			sendMess.setSmsMsg(msg.toString());
			
			// ֻ֧�ֵ���ѧ����Ϣ�ķ��� ����һ����ִ״̬
			String result=service.sendSMS(sendMess);
			if(result==null){
				dao.deleteLqtz(myForm);
				return "false";
			}else if(!"0".equalsIgnoreCase(result)){
				dao.deleteLqtz(myForm);
				return "false";
			}
		}
		
		// �޸��Ƿ�֪ͨ��״̬
		dao.updateSftz();
//		getMsg(sendResult)
		return "true";
	}

	/**
	 * ��ȡ���Ͷ���Ϣ�Ľ�� method getMsg
	 * 
	 * @param sendResult
	 *  author qlj
	 */
	public String getMsg(String[] sendResult) {

		// ���˵��
		String hzlb = "scsm";
		// ��ȡ���˵���б�
		List<HashMap<String, String>> hzList = dao.getDxtzHzxx(hzlb);
		List<HashMap<String, String>> msgList = new ArrayList<HashMap<String, String>>();
		StringBuilder msg = new StringBuilder();
		int tjsl = 0;
		for (int i = 0; i < hzList.size(); i++) {
			HashMap<String, String> hzMap = hzList.get(i);
			for (int j = 0; j < sendResult.length; j++) {
				if (sendResult[j].equalsIgnoreCase(hzMap.get("hzdm"))) {
					tjsl = Integer.parseInt(hzMap.get("tjsl")) + 1;
					hzMap.put("tjsl", "" + tjsl);
				}
			}
			tjsl = 0;
			msgList.add(hzMap);
		}

		for (int i = 0; i < msgList.size(); i++) {
			HashMap<String, String> hzMap = hzList.get(i);
			msg.append(hzMap.get("hznr") + ":" + hzMap.get("tjsl") + "����¼");
		}

		return msg.toString();
	}

	public void getDxhz(RcswForm myForm) throws SQLException {

		GxtWebService service = new GxtWebService();

		List<HashMap<String, String>> txtzList = dao.getTxtzFhzt(myForm);
		List<HashMap<String, String>> txList = new ArrayList<HashMap<String, String>>();
		// ��Уͨ��Կ
		String passWord = getPassWord();
		int dxhz = 0;
		for (int i = 0; i < txtzList.size(); i++) {
			HashMap<String, String> txtzMap = txtzList.get(i);
			HashMap<String, String> txMap = new HashMap<String, String>();
			SendMess sendMess = new SendMess();
			// ������Կ
			sendMess.setPassWord(passWord);
			// ��½��
			sendMess.setLoginName(myForm.getUserName());
			// ѧ��
			sendMess.setMps(txtzMap.get("xhzgh"));
			// Ψһ��ʶ
			sendMess.setUserDefId(Long.parseLong(txtzMap.get("userdefid")));

			// ��ȡ���Ż�ִ
			dxhz = service.receiveRPT(sendMess);

			txMap.put("userdefid", txtzMap.get("userdefid"));
			txMap.put("fhzt", "" + dxhz);
			txList.add(txMap);
		}

		dao.updateFhzt(txList);
	}

	/**
	 * ��ȡ��Ա����ͳ����Ϣ
	 * @param myForm
	 * @return List<String[]>
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * author qlj
	 */
	public List<String[]> getFfryTjxx(RcswForm myForm)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getFfryTjxx(myForm);
	}

	/**
	 * ��ȡ��ȡ�����б�
	 * @return List<HashMap<String,String>> 
	 * author qlj
	 */
	public List<HashMap<String, String>> getLqcsList() {
		return dao.getLqcsList();
	}

	/**
	 * ��ȡ���Ŵ����б� 
	 * @return List<HashMap<String,String>> 
	 * author qlj
	 */
	public List<HashMap<String, String>> getFfcsList() {
		return dao.getFfcsList();
	}
	
	public List<HashMap<String,String>>getXmmcList(){
		return dao.getXmmcList();
	}
	
	public ArrayList<String[]> getYffry(RcswForm myForm,HttpServletRequest request) throws Exception{
		return dao.getYffry(myForm, request);
	}
	
	/**
	 * ����ĿΪ���������������ţ�
	 * @param request
	 * @param myForm
	 * @throws Exception
	 */
	public void saveYffry(HttpServletRequest request, RcswForm myForm)
		throws Exception {
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
	
		// �������ݲ��� �ı���
		String realTable = "rcsw_swffrqwhb";
	
		//����ֵ
		String[] pkValue = myForm.getCheckVal();
		//��Ŀ����
		String xmlx = myForm.getXmlx();
		//ѧ��
		String xn = myForm.getXn();
		//ѧ��
		String xq= myForm.getXq();
		//���
		String nd=myForm.getNd();
		//����ʱ��
		String ffsj=myForm.getFfsj();
		//���ű�ע��Ϣ
		String ffbz=myForm.getFfbz();
		//������
		String ffr=myForm.getFfr();
		//�������ͣ�ѧ����
		String lx="xs";
		
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		
		//�����ֶ�
		String []onezd={"xn","xq","nd","xmlx","ffsj","lx","ffbz","ffr"};
		//�����ֶ�
		String []arrzd={"xhzgh"};
		//����
		String pk="xn||xq||nd||xmlx||ffsj";
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
	
		RcswZjxyModel model = new RcswZjxyModel();
		model.setFfsj(ffsj);
		model.setXmlx(xmlx);
		model.setXn(xn);
		model.setXq(xq);
		model.setNd(nd);
		model.setLx(lx);
		model.setFfbz(ffbz);
		model.setXhzgh(pkValue);
		model.setFfr(ffr);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		
		boolean reslut = ghxyNjzrwhService.saveTyxx(saveForm, model);
		request.setAttribute("result", reslut);
	}
	
	
	/**
	 * ��ѧ��Ϊ���������������ţ�
	 * @param request
	 * @param myForm
	 * @throws Exception
	 */
	public boolean  saveFfxm(RcswForm myForm)
		throws Exception {
		return dao.saveFfxm(myForm);
	}
	
	
	public HashMap<String,String> getSwffxm(String pkValue){
		return dao.getSwffxm(pkValue);
	}
	
	public List<String[]>getXsFfxm(RcswForm myForm) throws Exception{
		return dao.getXsFfxm(myForm);
	}
	
	public List<String[]>getPjtjXx(RcswForm myForm)throws Exception{
		
		return dao.getPjtjXx(myForm);
	}
	
	public String saveDxtz(RcswForm myForm) throws Exception{

		String[]xhzgh=myForm.getXhzgh();
		String[]sftz=myForm.getSftz();
		
		int m=0;
		for(int i=0;i<sftz.length;i++){
			if("��".equalsIgnoreCase(sftz[i])){
				m++;
			}
		}
		String []xh=new String[m];
		m=0;
		for(int i=0;i<xhzgh.length;i++){
			if("��".equalsIgnoreCase(sftz[i])){
				xh[m]=xhzgh[i];
				m++;
			}
		}
		myForm.setTzdxxh(xh);
		dao.saveLqtzInfo(myForm);
		return sendMessage(dao.getLqtzry(myForm), myForm.getUserName(),myForm);
	}
	
	/**
	 * ��ȡѧ��(�������֤��)
	 * @param sfzh
	 * @return
	 */
	public HashMap<String, String> getXhBySfzh(String sfzh) {
		
		return dao.getXhBySfzh(sfzh);
	}
	
	/**
	 * �������,��������ѯ
	 * @return  List<String[]>
	 * @throws Exception 
	 */
	public ArrayList<String[]>getFfxxList(RcswForm model) throws Exception{
		
		return (ArrayList<String[]>)dao.getFfxxList(model);
	}
	
	public List<String[]>getTjxxOne(RcswForm model)throws Exception{
		
		return dao.getTjxxOne(model);
	}
}
