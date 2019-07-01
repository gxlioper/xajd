package xgxt.jxgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.Globals;

import xgxt.DAO.DAO;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;

public class JxglTyService{

	JxglTyDAO dao = new JxglTyDAO();
	
	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(JxglTyForm myForm, HttpServletRequest request,
			String manu) throws Exception {

		// =====================ͨ��=============================
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);//�꼶ѧԺרҵ�༶
		FormModleCommon.setNdXnXqList(request);//���ѧ��ѧ��
		FormModleCommon.requestSetList(new String[]{"bm"}, request);//�Զ���(Ŀǰ�����Ŵ���)
		
		List<HashMap<String, String>> xbList = dao.getSelectList("xblx");//�Ա�
		request.setAttribute("xbList", xbList);
		
		// =====================��ѵ�ɼ�=============================
		if ("jxcj".equalsIgnoreCase(manu)) {
			DAO tyDao = DAO.getInstance();
			//ѧ���б�
			List<HashMap<String, String>> xnList = tyDao.getXnndList();
			xnList.remove(0);
			request.setAttribute("xnList", xnList);
		}
		// =====================���鱨��=============================
		else if ("rwbm".equalsIgnoreCase(manu)) {
			
			// �Ǽ������б�
			List<HashMap<String, String>> djlxList = dao.getWhList(
					"jxgl_rwzblxb", "dm", "mc", "", "", "");
			request.setAttribute("djlxList", djlxList);

			// ����������Ϣ
			StuInfoDAO stuDao = new StuInfoDAO();
			HashMap<String, String> map = new HashMap<String, String>();
			String xh = myForm.getXh();
			String[] colList = new String[] { "hkshen", "hkshi", "hkxian",
					"syd" };
			map = CommonQueryDAO
					.commonQueryOne("view_xsxxb", colList, "xh", xh);

			// ʡ�б�
			List<HashMap<String, String>> ssList = stuDao.getSsList();
			request.setAttribute("ssList", ssList);

			// �����������б�
			List<HashMap<String, String>> hkshiList = stuDao.getShiList(
					map.get("hkshen")).get("shiList");
			request.setAttribute("hkshiList", hkshiList);

			// �����������б�
			List<HashMap<String, String>> hkxianList = stuDao.getShiList(
					map.get("hkshi")).get("xianList");
			request.setAttribute("hkxianList", hkxianList);

			// ��Դ��
			String syd = map.get("syd");
			if (!Base.isNull(syd)) {
				String[] arrDq = syd.split("/");
				map.put("syshen", (arrDq.length >= 1) ? arrDq[0] : "");
				map.put("syshi", (arrDq.length >= 2) ? arrDq[1] : "");
				map.put("syxian", (arrDq.length >= 3) ? arrDq[2] : "");
			}

			// ��Դ���б�
			List<HashMap<String, String>> syshiList = stuDao.getShiList(
					map.get("syshen")).get("shiList");
			request.setAttribute("syshiList", syshiList);

			// ��Դ���б�
			List<HashMap<String, String>> syxianList = stuDao.getShiList(
					map.get("syshen")).get("xianList");
			request.setAttribute("syxianList", syxianList);
		}
		//	=====================�⻺ѵ=============================
		else if ("mhx".equalsIgnoreCase(manu)) {
			
			//	����
			StuInfoDAO stuDao = new StuInfoDAO();
			HashMap<String, String> map = new HashMap<String, String>();
			String xh = myForm.getXh();
			String[] colList = new String[] { "jg"};
			map = CommonQueryDAO.commonQueryOne("xsxxb", colList, "xh", xh);
			
			String jg = map.get("jg");
			if (!Base.isNull(jg)) {
				String[] arrDq = jg.split("/");
				map.put("jgshen", (arrDq.length >= 1) ? arrDq[0] : "");
				map.put("jgshi", (arrDq.length >= 2) ? arrDq[1] : "");
				map.put("jgxian", (arrDq.length >= 3) ? arrDq[2] : "");
			}
			
			// ʡ�б�
			List<HashMap<String, String>> ssList = stuDao.getSsList();
			request.setAttribute("ssList", ssList);
			
			// ���б�
			List<HashMap<String, String>> jgshiList = stuDao.getShiList(
					map.get("jgshen")).get("shiList");
			request.setAttribute("jgshiList", jgshiList);

			// ���б�
			List<HashMap<String, String>> jgxianList = stuDao.getShiList(
					map.get("jgshi")).get("xianList");
			request.setAttribute("jgxianList", jgxianList);
			
			// ��������
			List<HashMap<String, String>> mhlxList = dao.getWhList(
					"jxgl_mhxlxb", "dm", "mc", "", "", "");
			request.setAttribute("mhlxList", mhlxList);
			
			//���״̬�б�
			List<HashMap<String, String>> shList =dao.getSelectList("shlx");
			request.setAttribute("shList", shList);
		}
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
	 * ��þ�ѵ������Ϣ���б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getJxglList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getJxglList(tableName, model, colList, other_query);
	}

	/**
	 * ��þ�ѵ���������Ϣ����ϸ��
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 */
	public HashMap<String, String> getJxglInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getJxglInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * ɾ����ѵ������Ϣ
	 * 
	 * @author luojw
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * 
	 * @throws Exception
	 */
	public boolean delJxgl(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();

		return dao.delDate(form, model);
	}

	/**
	 * �����ѵ���������Ϣ��������
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
	public boolean saveJxgl(SaveForm form, Object model) throws Exception {
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
	public boolean saveJxgl(SaveForm form, Object model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * ���¾�ѵ���������Ϣ
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
	public boolean updateJxgl(SaveForm form, Object model) throws Exception {
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
	 * �������ά��������ֵ
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("djlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		}

		return dao.arrayToList(dm, mc);
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
}
