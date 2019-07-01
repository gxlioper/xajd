package xgxt.rcsw;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import xgxt.DAO.DAO;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.utils.FormModleCommon;

public class RcswService {

	RcswDAO dao = new RcswDAO();

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(RcswForm myForm, HttpServletRequest request, String manu)
			throws Exception {

		DAO tyDao = DAO.getInstance();
		
		// ---------------ѧ������----------------
		if ("lyfb".equalsIgnoreCase(manu)) {

			// ���������б�
			List<HashMap<String, String>> lylxList = getRcglList("rcsw_lylxb",
					"dm", "mc", "", "", "");
			request.setAttribute("lylxList", lylxList);

			// ����������б�
			List<HashMap<String, String>> lxList = getSelectList("shlx");
			request.setAttribute("lxList", lxList);
			
			// ���������б�
			List<HashMap<String, String>> lypjList = getRcglList("rcsw_lypjb",
					"dm", "mc", "", "", "");
			request.setAttribute("lypjList", lypjList);
			
			// ְλ�б�
			List<HashMap<String, String>> zwList = getRcglList("zwb", "zwdm",
					"zwmc", "", "", "");
			//����ְҵ ������ѯ��ʦ
			
			request.setAttribute("zwList", zwList);
		}

		// ---------------ʵ�﷢��----------------
		if ("swff".equalsIgnoreCase(manu)) {

			// ������Ŀ�б�
			List<HashMap<String, String>> xmlxList = getRcglList(
					"rcsw_swfflxb", "dm", "mc", "", "", "");
			request.setAttribute("xmlxList", xmlxList);

			// ������Ⱥ�б�
			List<HashMap<String, String>> ffrqList = getRcglList(
					"sxjy_bjgbzlb", "bjgbmc", "bjgbmc", "", "", "");// ��ɲ�
			List<HashMap<String, String>> otherRqList = getSelectList("ffrq");// ������Ⱥ
			if (otherRqList != null && otherRqList.size() > 0) {
				for (int i = 0; i < otherRqList.size(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("dm", otherRqList.get(i).get("en"));
					map.put("mc", otherRqList.get(i).get("cn"));
					ffrqList.add(map);
				}
			}
			if (ffrqList != null && ffrqList.size() > 0) {
				request.setAttribute("rqnum", ffrqList.size());
			}
			request.setAttribute("ffrqList", ffrqList);

			// ְλ�б�
			List<HashMap<String, String>> zwList = getRcglList("zwb", "zwdm",
					"zwmc", "", "", "");
			request.setAttribute("zwList", zwList);
			
			// ����б�
			List<HashMap<String, String>> ffList = getSelectList("isff");
			request.setAttribute("ffList", ffList);
		}
		
		if("dxtz".equalsIgnoreCase(manu)){
			//������Ŀ�б�
			List<HashMap<String, String>> xmlxList = getRcglList(
					"rcsw_swfflxb", "dm", "mc", "", "", "");
			request.setAttribute("xmlxList", xmlxList);

		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);// �꼶ѧԺרҵ�༶
		FormModleCommon.setNdXnXqList(request);// ���ѧ��ѧ��
		FormModleCommon.requestSetList(new String[] { "bm" }, request);// �Զ���(Ŀǰ�����Ŵ���)

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
			if(Base.isNull(title)){
				title = message != null && message.length >= 2 ? message[1] : "";
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
	 * ����ճ���������б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getRcswList(String tableName, Object model,
			String[] colList, String other_query, String[] orderBy)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getRcswList(tableName, model, colList, other_query, orderBy);
	}

	/**
	 * ����ճ����������Ϣ
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getRcswInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getRcswInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * �����ճ����������Ϣ��������
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
	public boolean saveRcsw(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}
	
	/**
	 * �����ճ����������Ϣ��������
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
	public boolean saveRcsw(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * �����ճ����������Ϣ
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
	public boolean updateRcsw(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}
	
	/**
	 * ɾ���ճ�������Ϣ
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @throws Exception
	 */
	public boolean delRcsw(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();

		return dao.delDate(form, model);
	}
	
	/**
	 * �����ά��������ֵ
	 * 
	 * @param tableName(����)
	 * @param dm(����)
	 * @param mc(����)
	 * @param msg(��ʾ��Ϣ)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getRcglList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getWhList(tableName, dm, mc,
				msg, pk, pkValue);

		return list;
	}
	
	/**
	 * ��ȡ������ѯ��ѯʦ
	 */
	public List<HashMap<String,String>>getXljkZxs(){
		return dao.getXljkZxs();
	}
	
	/**
	 * �������ά��������ֵ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("sflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		} else if ("yesorno".equalsIgnoreCase(lx)) {
			dm = new String[] { "yes", "no" };
			mc = new String[] { "��", "��" };
		} else if ("ywlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		} else if ("shlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "ѧ��", "��ʦ", "������" };
			mc = new String[] { "ѧ��", "��ʦ", "������" };
		} else if ("ffrq".equalsIgnoreCase(lx)) {
			dm = new String[] { "�༶", "����Ա", "������" };
			mc = new String[] { "�༶", "����Ա", "������" };
		}else if ("isff".equalsIgnoreCase(lx)) {
			dm = new String[] { "����", "δ����" };
			mc = new String[] { "����", "δ����" };
		} else if ("shzt".equalsIgnoreCase(lx)){
			dm = new String[] { "ͨ��", "�˻�","��ͨ��" };
			mc = dm;
		} else if ("shjg".equalsIgnoreCase(lx)){
			dm = new String[] { "ͨ��", "�˻�","��ͨ��","������","δ���" };
			mc = dm;
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * �޸�ʱ������
	 * 
	 * @author luojw
	 */
	public String changDateLx(String sj, String lx) {

		StringBuffer newSj = new StringBuffer();

		if (Base.isNull(sj)) {
			return sj;
		}

		if ("YYYYMMDDHH24MISS".equalsIgnoreCase(lx) && sj.length() == 14) {
			newSj.append(sj.substring(0, 4) + "��");
			newSj.append(sj.substring(4, 6) + "��");
			newSj.append(sj.substring(6, 8) + "��");
			newSj.append(" ");
			newSj.append(sj.substring(8, 10) + "ʱ");
			newSj.append(sj.substring(10, 12) + "��");
			newSj.append(sj.substring(12, 14) + "��");
		}

		return Base.isNull(newSj.toString()) ? sj : newSj.toString();
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
	}
	
	/**
	 * ���ָ�����ָ���ֶ�
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		DAO dao = DAO.getInstance();
		return dao.getOneValue(tableName, dm, pk, pkValue);
	}
	
	
	
	/**
	 * �����б�
	 * @author quph
	 */
	public List<HashMap<String, String>> getCcList() {
		
		return dao.getCcList();
	}
	
	
	
	/**
	 * ��վ�б�
	 * @author quph
	 */
	public List<HashMap<String, String>> getCzList() {
		
		return dao.getCzList();
	}
	
	/**
	 *  ���ݲ�ѯ������ѯ������ѯʦ
	 * 
	 */
	public List<HashMap<String,String>>getXlzxsByTj(String bmdm,String zgh,String xm){
		return dao.getXlzxsByTj(bmdm, zgh, xm);
	}
}
