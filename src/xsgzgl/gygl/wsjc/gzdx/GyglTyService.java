package xsgzgl.gygl.wsjc.gzdx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.dtjs.czxx.dyxx.DyxxModel;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;

public class GyglTyService extends CommService {

	GyglTyDAO dao = new GyglTyDAO();

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(GyglTyForm myForm, HttpServletRequest request,
			String manu) throws Exception {
		
		String lddm = myForm.getLddm();
		lddm = Base.isNull(lddm) ? myForm.getQueryequals_lddm() : lddm;
		String cs = myForm.getCs();
		cs = Base.isNull(cs) ? myForm.getQueryequals_cs() : cs;
		
		// -----------------ͨ		��---------------
		List<HashMap<String, String>> wsjcdjList = dao.getGyglList("hhgxy_wsjcdj", "wsjcdm", "wsjcdj", "", "", "");// �������
		List<HashMap<String, String>> xqdmList = dao.getGyglList("dm_zju_xq", "dm", "xqmc", "", "", "");// У��
		List<HashMap<String, String>> ldList = dao.getGyglList("sslddmb", "lddm", "ldmc", "", "", "");// ¥��
		List<HashMap<String, String>> csList = dao.getCsList(lddm);	//����
		List<HashMap<String, String>> qsList = dao.getQsList(lddm, cs, "");// ����
		List<HashMap<String, String>> bblxList = dao.getSelectList("bblx");//��������
		//List<HashMap<String, String>> zcList = dao.getZcList();// �ܴ�
		
		request.setAttribute("wsjcdjList", wsjcdjList);
		request.setAttribute("xqdmList", xqdmList);
		request.setAttribute("ldList", ldList);
		request.setAttribute("csList", csList);
		request.setAttribute("qsList", qsList);
		request.setAttribute("bblxList", bblxList);
		//request.setAttribute("zcList", zcList);
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);// �꼶ѧԺרҵ�༶
		FormModleCommon.setNdXnXqList(request);//���ѧ��ѧ��
		FormModleCommon.requestSetList(new String[] { "bm" }, request);// �Զ���(Ŀǰ�����Ŵ���)
		
		List<HashMap<String, String>> sfList = dao.getSelectList("sflx");// �Ƿ��б�
		request.setAttribute("sfList", sfList);
		
		List<HashMap<String, String>> shList = dao.getSelectList("shlx");// ����б�
		request.setAttribute("shList", shList);
		
		List<HashMap<String, String>> xbList = dao.getSelectList("xblx");// �Ա�
		request.setAttribute("xbList", xbList);
		// -----------------end---------------
		
		// ======================��Ԣ����============================
		if ("gybx".equalsIgnoreCase(manu)) {

			// �����ϱ�
			List<HashMap<String, String>> nrTitleList = dao
					.getSelectList("clsb");
			request.setAttribute("nrTitleList", nrTitleList);

			// ��������
			List<HashMap<String, String>> cllxList = dao.getGyglList(
					"gygl_bxcllxb", "dm", "mc", "", "", "");
			request.setAttribute("cllxList", cllxList);

			// ��������
			List<HashMap<String, String>> pjList = dao.getGyglList(
					"gygl_bxpjb", "dm", "mc", "", "", "");
			request.setAttribute("pjList", pjList);

			// ͳ�Ʒ�ʽ
			List<HashMap<String, String>> tjfsList = dao
					.getSelectList("bxtjfs");
			request.setAttribute("tjfsList", tjfsList);

		}
		// ======================end============================
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
	 * ��ù�Ԣ������Ϣ���б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getGyglList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getGyglList(tableName, model, colList, other_query);
	}

	/**
	 * ��ù�Ԣ���������Ϣ����ϸ��
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 */
	public HashMap<String, String> getGyglInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getGyglInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * ɾ����Ԣ������Ϣ
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @throws Exception
	 */
	public boolean delGygl(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();

		return dao.delDate(form, model);
	}

	/**
	 * ���湫Ԣ���������Ϣ��������
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
	public boolean saveGygl(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}

	/**
	 * ���浳Ա��Ϣ�����Ϣ��������
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
	public boolean saveGygl(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * ���¹�Ԣ���������Ϣ
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
	public boolean updateGygl(SaveForm form, DyxxModel model) throws Exception {
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
	 * �������ҳ
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getResultList(ArrayList<String[]> list,
			GyglTyForm model) {

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
	 * �Ƿ�Ԣ����Ա
	 * 
	 * @author luojw
	 */
	public Boolean isGyfdy(String userName) {
		return dao.isGyfdy(userName);
	}
	
	/**
	 * ��Ԣ����Ա��ʼ��У��¥���б�
	 * 
	 * @author luojw
	 */
	public void initGyglList(String userName, HttpServletRequest request) {
		// У��
		List<HashMap<String, String>> xqdmList = dao.getXqLdList("xq", "", "",
				userName);
		// У��
		List<HashMap<String, String>> ldList = dao.getXqLdList("ld", "", "",
				userName);

		request.setAttribute("xqdmList", xqdmList);
		request.setAttribute("ldList", ldList);
	}
	
  	/**
	 * �Ƿ���ֵ����Ա
	 * 
	 * @param userName
	 * @author sjf
	 */
	public Boolean isZbry(String userName) {
		return dao.isZbry(userName);
	}
}
