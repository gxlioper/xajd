package xgxt.comm.search;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CnToEnUtil;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.xml.XMLReader;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.xgxt.xsztz.xmsbgl.glygl.GlyglDao;
import com.zfsoft.xgxt.xtwh.yhsjfw.YhsjfwService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �߼���ѯ_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class SearchService extends CommService {

	SearchDAO dao = new SearchDAO();
	
	// ƴ������ĸ
	String[] pyszm = { "a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l",
			"m", "n", "o", "p", "q", "r", "s", "t", "w", "x", "y", "z" };

	public static final String[] PY_BIG = { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z", "-" };

	// ѧԺ�б�
	private static List<HashMap<String, Object>> xyTjList = new ArrayList<HashMap<String, Object>>();

	// רҵ�б�(ƴ��)
	private static List<HashMap<String, String>> zyPyList = new ArrayList<HashMap<String, String>>();

	// �༶�б�(ƴ��)
	private static List<HashMap<String, String>> bjPyList = new ArrayList<HashMap<String, String>>();

	// רҵ�б�
	private static List<HashMap<String, Object>> zyTjList = new ArrayList<HashMap<String, Object>>();

	// �༶�б�
	private static List<HashMap<String, Object>> bjTjList = new ArrayList<HashMap<String, Object>>();

	//--------------------------------ѧ���춯��-----START---------------------------------------------------
	// �꼶�б�
	private static List<HashMap<String, String>> njNewTjList = new ArrayList<HashMap<String, String>>();
	// ѧԺ�б�
	private static List<HashMap<String, Object>> xyNewTjList = new ArrayList<HashMap<String, Object>>();
	// רҵ�б�
	private static List<HashMap<String, Object>> zyNewTjList = new ArrayList<HashMap<String, Object>>();
	// �༶�б�
	private static List<HashMap<String, Object>> bjNewTjList = new ArrayList<HashMap<String, Object>>();
	//--------------------------------ѧ���춯��-----END---------------------------------------------------
	
	/**
	 * ��ù���ģ����Ҫ�Ĳ�ѯ����
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getSearchInfo(SearchForm model, User user) {

		List<HashMap<String, String>> list = getSearchTjList(model, user);

		HashMap<String, String> map = new HashMap<String, String>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> rs = list.get(i);
				String tj = rs.get("tj");

				map.put(tj, "yes");
			}
		}

		return map;
	}

	/**
	 * ��ù���ģ����Ҫ�Ĳ�ѯ����
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getSearchTjList(SearchForm model,User user) {

		// �����
		String tableName = "xg_search_szb";
		// ����
		String pk = "path";
		// ����ֵ
		String pkValue = model.getPath();
		// ����ֶ�
		String[] colList = new String[] { "tj", "mc", "lx", "ssmk", "zd","gshlx" };

		String query = " where 1 = 1 ";
		if (!Base.isNull(pkValue)) {
			query += " and " + pk + " = '" + pkValue + "'";
		}
		
		//�û�����
		String userType = user.getUserType();
		
		if("stu".equalsIgnoreCase(userType)){
			query += " and xszd = 'yes' ";
		}
		
		query += " order by to_number(xssx) ";

		return getRsList(tableName, query, new String[] {}, colList, "");
	}

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void setCommOptionList(SearchForm searchForm, RequestForm rForm,
			HttpServletRequest request) throws Exception {
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, searchForm);
		setList(model, rForm, request);
	}

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void setSearchTj(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {
		
		// ����ģ��
		String ssmk = searchForm.getSsmk();
		// ����·��
		String path = searchForm.getPath();
		// ����ͨ���б�
		setCommOptionList(searchForm, rForm, request);
		
		// �����꼶ѧԺרҵ�༶�б�
		setNjXyZyBjList(searchForm, rForm, user, request);

		// �����꼶ѧԺרҵ�༶�б�ȫ��[ѧ���춯��]
		setNjXyZyBjNewList(searchForm, rForm, user, request);
		
		SearchTj searchTj = new SearchTj();
		
		searchTj.setCommListList(searchForm, rForm, user, PY_BIG, request);

		if ("xsxx".equalsIgnoreCase(ssmk)) {// ѧ����Ϣ
			// ����ѧ����Ϣ����б�
			searchTj.setXsxxListList(searchForm, rForm, user, request);
		} else if ("rcsw".equalsIgnoreCase(ssmk)) {// �ճ�����
			// �����ճ���������б�
			searchTj.setRcswListList(searchForm, rForm, user, request);
		} else if ("gygl".equalsIgnoreCase(ssmk)) {// ��Ԣ����
			// ���ù�Ԣ��������б�
			searchTj.setGyglListList(searchForm, rForm, user, PY_BIG, request);
		} else if ("gygl_third".equalsIgnoreCase(ssmk)) {// ��Ԣ����_������
			// ���ù�Ԣ����(������)����б�
			searchTj.setGyglListList_Third(searchForm, rForm, user, PY_BIG, request);
		} else if ("pjpy".equalsIgnoreCase(ssmk)) {// ��������
			// ����������������б�
			searchTj.setPjpyListList(searchForm, rForm, user, request);
		} 
	}

	/**
	 * ��������ҳ���ʼ���б�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void setNjXyZyBjList(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		String sfzxs = "";
		
		// �û����
		String userStatus = "";
		// �û���
		String userName = user.getUserName();
		// �û�����
		String userDep = user.getUserDep();
		
		SearchTjByRoles rolesService = new SearchTjByRoles();
		
		 // ʹ���û���ɫ
		String userRolesApply = user.getUserRolesApply();

		if ("yes".equalsIgnoreCase(userRolesApply)) {
			// �û���ɫ
			List<HashMap<String, String>> userRolesList = rolesService
					.getUserGnmkRoles(searchForm, user);
			// �û����
			userStatus = rolesService.getUserStatus(userRolesList,user);
		} else {
			userStatus = user.getUserStatus();
		}
	
		// ������������
		//getEspecialTj(searchForm, request);

		String path = searchForm.getPath();
		if(path.equals("xsxx_xsxxgl_cxfzxs.do")){
			sfzxs="0";
		}
		if(path.equals("xsxx_xsxxgl_cxzxs.do")){
			sfzxs="1";
		}
		
		boolean isHistory = false;
		
		// ��ʷѧ����Ϣ  TODO ���µ���˵��� isHistoryֵ�ᱻ���� �ж�ʧЧ
		if ("stu_info_query.do?method=stuInfo&searchType=lsxx"
				.equalsIgnoreCase(path)
				|| "stu_info_query.do?method=stuInfo&sfzxk=no"
						.equalsIgnoreCase(path)
				|| "stu_info_query.do?method=stuInfo&sfyby=yes"
						.equalsIgnoreCase(path)
				|| "xsxx_tygl_cxfzxs.do".equalsIgnoreCase(path)
				|| "xjyd_xjydjg.do".equalsIgnoreCase(path)
				|| "xjyd_bycl.do".equalsIgnoreCase(path)
				|| "gyglnew_cwfpgl_cwfp.do".equalsIgnoreCase(path)
				|| "szdw_general_szbb.do".equalsIgnoreCase(path)
				|| "pjpy_tjcx_zcbjmd_djks.do".equalsIgnoreCase(path)) {
			isHistory = true;
			session.setAttribute("userNjList", null);
			session.setAttribute("userXyList", null);
			session.setAttribute("userZyPyList", null);
			session.setAttribute("userZyList", null);
			session.setAttribute("userBjList", null);
		} else {
			isHistory = false;
		}

		Base.setHistory(isHistory);
		
		// ѧУ�û�������Ա��
		if ("xx".equalsIgnoreCase(userStatus)) {

			// �꼶�б�
			List<HashMap<String, String>> njTjList = getNewNjList(userStatus,
					userName, userDep,sfzxs);
			
			searchForm.setNjTjList(njTjList);
			request.setAttribute("njTjList", njTjList);

			
				xyTjList = getNewXyPxList("", "", "",sfzxs);
			
			searchForm.setXyTjList(xyTjList);
			request.setAttribute("xyTjList", xyTjList);

			
				zyTjList = getNewZyPxList(null, "", "", "",sfzxs);
			
			searchForm.setZyTjList(zyTjList);
			request.setAttribute("zyTjList", zyTjList);

				bjTjList = getNewBjPxList(null, "", "", "",sfzxs);
			
			searchForm.setBjTjList(bjTjList);
			request.setAttribute("bjTjList", bjTjList);

		} else {

			// session�е��û��꼶�б�
			List<HashMap<String, String>> userNjList = (List<HashMap<String, String>>) session
					.getAttribute("userNjList");

			if (userNjList != null && userNjList.size() > 0) {
				request.setAttribute("njTjList", userNjList);
			} else {
				userNjList = getNewNjList(userStatus, userName, userDep,sfzxs);
				session.setAttribute("userNjList", userNjList);
				request.setAttribute("njTjList", userNjList);
			}
			searchForm.setNjTjList(userNjList);
			
			// session�е��û�ѧԺ�б�
			List<HashMap<String, Object>> userXyList = (List<HashMap<String, Object>>) session
					.getAttribute("userXyList");

			if (userXyList != null && userXyList.size() > 0) {
				request.setAttribute("xyTjList", userXyList);
			} else {
				userXyList = getNewXyPxList(userStatus, userName, userDep,sfzxs);
				session.setAttribute("userXyList", userXyList);
				request.setAttribute("xyTjList", userXyList);
			}
			searchForm.setXyTjList(userXyList);
			
			// session�е��û�רҵ�б�
			List<HashMap<String, Object>> userZyList = (List<HashMap<String, Object>>) session
					.getAttribute("userZyList");
			// session�е�רҵ�б�(��ƴ��)
			List<HashMap<String, String>> userZyPyList = (List<HashMap<String, String>>) session
					.getAttribute("userZyPyList");

			if (userZyList != null && userZyList.size() > 0) {
				request.setAttribute("zyTjList", userZyList);
			} else {

				// רҵ�б���ƴ����
				userZyPyList = getNewZyList(userStatus, userName, userDep, null,sfzxs);
				userZyList = getNewZyPxList(userZyPyList, userStatus, userName,
						userDep,sfzxs);

				session.setAttribute("userZyPyList", userZyPyList);
				session.setAttribute("userZyList", userZyList);
				request.setAttribute("zyTjList", userZyList);
			}
			searchForm.setZyTjList(userZyList);
			
			// session�е��û��༶�б�
			List<HashMap<String, Object>> userBjList = (List<HashMap<String, Object>>) session
					.getAttribute("userBjList");
			// session�еİ༶�б�(��ƴ��)
			List<HashMap<String, String>> userBjPyList = (List<HashMap<String, String>>) session
					.getAttribute("userBjPyList");

			if (userBjList != null && userBjList.size() > 0) {
				request.setAttribute("bjTjList", userBjList);
			} else {
				userBjPyList = getNewBjList(userStatus, userName, userDep,sfzxs);
				userBjList = getNewBjPxList(userBjPyList, userStatus, userName,
						userDep,sfzxs);
				
				session.setAttribute("userBjList", userBjList);
				request.setAttribute("bjTjList", userBjList);
			}
			
			searchForm.setBjTjList(userBjList);
			request.setAttribute("bjTjList", userBjList);
		}
	}
	
	/**
	 * ������꼶�б�
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getNewNjList(String userStatus,
			String userName, String userDep,String sfzxs) {
		return dao.getNewNjList(userStatus, userName, userDep,sfzxs);
	}
	/**
	 * 
	 * @����:����԰����ȡѧԺ
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-11-13 ����04:37:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getNewNjListByYq(String userStatus,
			String userName, String userDep,String[] xq,String[] yq) {
		return dao.getNewNjListByYq(userStatus, userName, userDep, xq, yq);
	}

	/**
	 * �����ѧԺ�б�
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getNewXyList(String userStatus,
			String userName, String userDep,String sfzxs) {
		
		List<HashMap<String, String>> xyList = dao.getXyList(userStatus,
				userName, userDep,sfzxs);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < xyList.size(); i++) {

			HashMap<String, String> map = xyList.get(i);

			String xydm = map.get("xydm");
			String xymc = map.get("xymc");
			String xypy = CnToEnUtil.getFirstLetter(xymc);

			if("-".equalsIgnoreCase(xypy)){
				xypy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", xydm);
				xypy = Base.isNull(xypy) ? "-" : xypy;
				System.out.println("��Ҫ���õ���Ƨ�ֲ��ţ�" + xymc + "(" + xydm + ")");
			}
			
			HashMap<String, String> xyMap = new HashMap<String, String>();
			xyMap.put("xypy", xypy.toUpperCase());
			xyMap.put("xydm", xydm);
			xyMap.put("xymc", xymc);

			list.add(xyMap);
		}

		return list;
	}
	
	public List<HashMap<String, String>> getNewXyListByYq(String userStatus,
			String userName, String userDep,String[] xq,String[] yq) {
		
		List<HashMap<String, String>> xyList = dao.getXyListByYq(userStatus,
				userName, userDep,xq,yq);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < xyList.size(); i++) {

			HashMap<String, String> map = xyList.get(i);

			String xydm = map.get("xydm");
			String xymc = map.get("xymc");
			String xypy = CnToEnUtil.getFirstLetter(xymc);

			if("-".equalsIgnoreCase(xypy)){
				xypy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", xydm);
				xypy = Base.isNull(xypy) ? "-" : xypy;
				System.out.println("��Ҫ���õ���Ƨ�ֲ��ţ�" + xymc + "(" + xydm + ")");
			}
			
			HashMap<String, String> xyMap = new HashMap<String, String>();
			xyMap.put("xypy", xypy.toUpperCase());
			xyMap.put("xydm", xydm);
			xyMap.put("xymc", xymc);

			list.add(xyMap);
		}

		return list;
	}

	/**
	 * �����רҵ�б�
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getNewZyList(String userStatus,
			String userName, String userDep, String[] nj,String sfzxs) {

		List<HashMap<String, String>> zyList = dao.getZyList(userStatus,
				userName, userDep, nj,sfzxs);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < zyList.size(); i++) {

			HashMap<String, String> map = zyList.get(i);

			String xydm = map.get("xydm");
			String xymc = map.get("xymc");
			String zydm = map.get("zydm");
			String zymc = map.get("zymc");
			String zypy = CnToEnUtil.getFirstLetter(zymc);
			
			if("-".equalsIgnoreCase(zypy)){
				zypy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", zydm);
				zypy = Base.isNull(zypy) ? "-" : zypy;
				System.out.println("��Ҫ���õ���Ƨ�ֲ��ţ�" + zymc + "(" + zydm + ")");
			}
			
			HashMap<String, String> zyMap = new HashMap<String, String>();
			zyMap.put("zypy", zypy.toUpperCase());
			zyMap.put("xydm", xydm);
			zyMap.put("xymc", xymc);
			zyMap.put("zydm", zydm);
			zyMap.put("zymc", zymc);

			list.add(zyMap);
		}

		return list;
	}

	/**
	 * ����°༶�б�
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getNewBjList(String userStatus,
			String userName, String userDep,String sfzxs) {

		List<HashMap<String, String>> bjList = dao.getBjList(userStatus,
				userName, userDep,sfzxs);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < bjList.size(); i++) {

			HashMap<String, String> map = bjList.get(i);

			String xydm = map.get("xydm");// ѧԺ����
			String xymc = map.get("xymc");// ѧԺ����
			String zydm = map.get("zydm");// רҵ����
			String zymc = map.get("zymc");// רҵ����
			String nj = map.get("nj");// �꼶
			String bjdm = map.get("bjdm");// �༶����
			String bjmc = map.get("bjmc");// �༶����
			String bjpy = CnToEnUtil.getFirstLetter(bjmc);// �༶ƴ��
			
			if("-".equalsIgnoreCase(bjpy)){
				bjpy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", bjdm);
				bjpy = Base.isNull(bjpy) ? "-" : bjpy;
				System.out.println("��Ҫ���õ���Ƨ�ֲ��ţ�" + bjmc + "(" + bjdm + ")");
			}
			
			HashMap<String, String> bjMap = new HashMap<String, String>();
			bjMap.put("bjpy", bjpy.toUpperCase());
			bjMap.put("xydm", xydm);
			bjMap.put("xymc", xymc);
			bjMap.put("zydm", zydm);
			bjMap.put("zymc", zymc);
			bjMap.put("nj", nj);
			bjMap.put("bjdm", bjdm);
			bjMap.put("bjmc", bjmc);

			list.add(bjMap);
		}

		return list;
	}

	/**
	 * ���ѧԺ�б�(����ƴ��)
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, Object>> getNewXyPxList(String userStatus,
			String userName, String userDep,String sfzxs) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		List<HashMap<String, String>> newXylist = getNewXyList(userStatus,
				userName, userDep,sfzxs);

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> xyList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newXylist.size(); j++) {

				HashMap<String, String> map = newXylist.get(j);

				String xypy = map.get("xypy");
				String xydm = map.get("xydm");
				String xymc = map.get("xymc");

				if (py.equalsIgnoreCase(xypy)) {

					HashMap<String, String> newXy = new HashMap<String, String>();
					newXy.put("xydm", xydm);
					newXy.put("xymc", xymc);
					xyList.add(newXy);

				}
			}

			HashMap<String, Object> xyMap = new HashMap<String, Object>();
			xyMap.put("py", py);
			xyMap.put("xyList", xyList);

			list.add(xyMap);
		}

		return list;

	}

	/**
	 * ���רҵ�б�(����ƴ��)
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, Object>> getNewZyPxList(
			List<HashMap<String, String>> userZyPyList, String userStatus,
			String userName, String userDep,String sfzxs) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// רҵ�б���ƴ����
		List<HashMap<String, String>> newZylist = null;

		if (userZyPyList != null && userZyPyList.size() > 0) {
			newZylist = userZyPyList;
		} else {
			newZylist = getNewZyList(userStatus, userName, userDep, null,sfzxs);
		}

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newZylist.size(); j++) {

				HashMap<String, String> map = newZylist.get(j);

				String zypy = map.get("zypy");
				String zydm = map.get("zydm");
				String zymc = map.get("zymc");

				if (py.equalsIgnoreCase(zypy)) {

					HashMap<String, String> newZy = new HashMap<String, String>();

					if (zyList.size() < 3) {

						newZy.put("zydm", zydm);
						newZy.put("zymc", zymc);

						zyList.add(newZy);

					} else {
						newZy.put("zydm", "zy_" + zypy);
						newZy.put("zymc", "����");

						zyList.add(newZy);

						break;
					}

				}
			}

			HashMap<String, Object> zyMap = new HashMap<String, Object>();
			zyMap.put("py", py);
			zyMap.put("zyList", zyList);

			list.add(zyMap);
		}

		return list;

	}

	/**
	 * ���רҵ��Ϣ������ƴ����
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getZyInfoByPy(String py,
			String userStatus, String userName, String userDep,String sfzxs) {

		List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();

		List<HashMap<String, String>> zyPyList = getNewZyList(userStatus,
				userName, userDep, null,sfzxs);

		for (int i = 0; i < zyPyList.size(); i++) {

			HashMap<String, String> zyMap = zyPyList.get(i);

			String zypy = zyMap.get("zypy");
			String zydm = zyMap.get("zydm");
			String zymc = zyMap.get("zymc");

			if (zypy.equalsIgnoreCase(py)) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("zydm", zydm);
				map.put("zymc", zymc);

				zyList.add(map);

			}
		}

		return zyList;
	}

	/**
	 * ���רҵ��Ϣ������ѧԺ��
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getZyInfoByXy(String[] nj,
			String[] xy, String userStatus, String userName, String userDep,String sfzxs) {

		List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();

		List<HashMap<String, String>> zyPyList = getNewZyList(userStatus,
				userName, userDep, nj,sfzxs);

		if (xy != null && xy.length > 0) {

			for (int i = 0; i < zyPyList.size(); i++) {

				HashMap<String, String> zyMap = zyPyList.get(i);

				String zypy = zyMap.get("zypy");
				String xydm = zyMap.get("xydm");
				String xymc = zyMap.get("xymc");
				String zydm = zyMap.get("zydm");
				String zymc = zyMap.get("zymc");

				for (int j = 0; j < xy.length; j++) {

					if (xy[j].equalsIgnoreCase(xydm)) {

						HashMap<String, String> map = new HashMap<String, String>();

						map.put("zypy", zypy);
						map.put("xymc", xymc);
						map.put("zydm", zydm);
						map.put("zymc", zymc);

						zyList.add(map);
					}
				}
			}
		} else {

			for (int i = 0; i < zyPyList.size(); i++) {

				HashMap<String, String> zyMap = zyPyList.get(i);

				String zypy = zyMap.get("zypy");
				String xymc = zyMap.get("xymc");
				String zydm = zyMap.get("zydm");
				String zymc = zyMap.get("zymc");

				for (int j = 0; j < PY_BIG.length; j++) {

					if (PY_BIG[j].equalsIgnoreCase(zypy)) {

						HashMap<String, String> map = new HashMap<String, String>();

						map.put("zypy", zypy);
						map.put("xymc", xymc);
						map.put("zydm", zydm);
						map.put("zymc", zymc);

						zyList.add(map);
					}
				}
			}
		}

		return zyList;
	}

	/**
	 * ��ð༶�б�(����ƴ��)
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, Object>> getNewBjPxList(
			List<HashMap<String, String>> userBjPyList, String userStatus,
			String userName, String userDep,String sfzxs) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// �༶�б���ƴ����
		List<HashMap<String, String>> newBjlist = null;

		if (userBjPyList != null && userBjPyList.size() > 0) {
			newBjlist = userBjPyList;
		} else {
			newBjlist = getNewBjList(userStatus, userName, userDep,sfzxs);
		}

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newBjlist.size(); j++) {

				HashMap<String, String> map = newBjlist.get(j);

				String bjpy = map.get("bjpy");
				String bjdm = map.get("bjdm");
				String bjmc = map.get("bjmc");

				if (py.equalsIgnoreCase(bjpy)) {

					HashMap<String, String> newBj = new HashMap<String, String>();

					if (bjList.size() < 3) {

						newBj.put("bjdm", bjdm);
						newBj.put("bjmc", bjmc);

						bjList.add(newBj);

					} else {
						newBj.put("bjdm", "bj_" + bjpy);
						newBj.put("bjmc", "����");

						bjList.add(newBj);

						break;
					}

				}
			}

			if (bjList != null && bjList.size() > 0) {
				HashMap<String, Object> bjMap = new HashMap<String, Object>();
				bjMap.put("py", py);
				bjMap.put("bjList", bjList);

				list.add(bjMap);
			}
		}

		return list;

	}

	/**
	 * ��ð༶��Ϣ������ƴ�����꼶��ѧԺ��רҵ��
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getBjInfoByTj(String py, String[] nj,
			String[] xy, String[] zy, String userStatus, String userName,
			String userDep,String sfzxs) {

		List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

		// �༶�б���ƴ����
		List<HashMap<String, String>> bjPyList = getZybjPyList(userStatus,
				userName, userDep);

		for (int i = 0; i < bjPyList.size(); i++) {

			HashMap<String, String> bjMap = bjPyList.get(i);

			String bjpy = bjMap.get("bjpy");// �༶ƴ��
			String bjdm = bjMap.get("bjdm");// �༶����
			String bjmc = bjMap.get("bjmc");// �༶����

			String njdm = bjMap.get("nj");// �꼶
			String xydm = bjMap.get("xydm");// ѧԺ
			String zydm = bjMap.get("zydm");// רҵ

			// �ж�ƴ���Ƿ����
			boolean pyFlag = true;

			if (!Base.isNull(py)) {

				pyFlag = false;

				if (bjpy.equalsIgnoreCase(py)) {
					pyFlag = true;
				}
			}

			// �ж��꼶�Ƿ����
			boolean njFlag = true;

			if (nj != null && nj.length > 0) {

				njFlag = false;

				for (int j = 0; j < nj.length; j++) {
					if (njdm.equalsIgnoreCase(nj[j])) {
						njFlag = true;
						break;
					}
				}
			}

			// �ж�ѧԺ�Ƿ����
			boolean xyFlag = true;

			if (xy != null && xy.length > 0) {

				xyFlag = false;

				for (int j = 0; j < xy.length; j++) {
					if (xydm.equalsIgnoreCase(xy[j])) {
						xyFlag = true;
						break;
					}
				}
			}

			// �ж�רҵ�Ƿ����
			boolean zyFlag = true;

			if (zy != null && zy.length > 0) {

				zyFlag = false;

				for (int j = 0; j < zy.length; j++) {
					if (zydm.equalsIgnoreCase(zy[j])) {
						zyFlag = true;
						break;
					}
				}
			}

			if (pyFlag && njFlag && xyFlag && zyFlag) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("bjpy", bjpy);
				map.put("bjdm", bjdm);
				map.put("bjmc", bjmc);

				bjList.add(map);

			}
		}

		return bjList;
	}

	/**
	 * �߼���ѯ��ȡ�����༶
	 * @param py
	 * @param sy
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @param sfzxs
	 * @return
	 */
	public List<HashMap<String, String>> getXzbjInfoByTj(String py, String[] sy, String userStatus, String userName,
													   String userDep,String sfzxs) {

		List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

		// �༶�б���ƴ����
		List<HashMap<String, String>> bjPyList = getXzbjPyList(userStatus,userName, userDep);

		for (int i = 0; i < bjPyList.size(); i++) {

			HashMap<String, String> bjMap = bjPyList.get(i);

			String bjpy = bjMap.get("bjpy");// �༶ƴ��
			String bjdm = bjMap.get("bjdm");// �༶����
			String bjmc = bjMap.get("bjmc");// �༶����

			String sydm = bjMap.get("sydm");// ��Ժ

			// �ж�ƴ���Ƿ����
			boolean pyFlag = true;

			if (!Base.isNull(py)) {

				pyFlag = false;

				if (bjpy.equalsIgnoreCase(py)) {
					pyFlag = true;
				}
			}

			// �ж���Ժ�Ƿ����
			boolean syFlag = true;

			if (sy != null && sy.length > 0) {

				syFlag = false;

				for (int j = 0; j < sy.length; j++) {
					if (sydm.equalsIgnoreCase(sy[j])) {
						syFlag = true;
						break;
					}
				}
			}

			if (pyFlag && syFlag ) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("bjpy", bjpy);
				map.put("bjdm", bjdm);
				map.put("bjmc", bjmc);

				bjList.add(map);

			}
		}

		return bjList;
	}

	/**
	 * ��ù�Ԣ���������Ϣ
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getGyglInfo(String lx, String[] xqdm,
			String[] yqdm, String[] lddm, String[] cs, String gyglyQx,
			String userName, String userDep) {

		List<HashMap<String, String>> list = null;

		String tableName = "";
		StringBuilder query = new StringBuilder(" where 1 = 1 ");
		ArrayList<String> inPutList = new ArrayList<String>();
		String[] colList = null;

		// ԰��
		if ("yq".equalsIgnoreCase(lx)) {

			tableName = "yqdmb";

			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from sslddmb a,xg_gygl_ldglb b ");
				query.append("where yqdmb.yqdm = a.yqdm and a.lddm =b.lddm and b.yhm='"+userName+"') ");
			}
			query.append(" order by yqdm ");
			
			colList = new String[] { "yqdm", "yqmc" };

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,
					"");
		}
		// ¥��
		else if ("ld".equalsIgnoreCase(lx)) {

			tableName = "sslddmb";

			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			if (yqdm != null && yqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < yqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" yqdm = ? ");
					
					inPutList.add(yqdm[i]);
				}
				query.append(" )");

			}
			
			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from xg_gygl_ldglb b ");
				query.append(" where sslddmb.lddm = b.lddm and b.yhm='"+userName+"') ");
			}
			
			query.append(" order by lddm ");
			
			colList = new String[] { "lddm", "ldmc" };

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,
			"");
		}
		//	����
		else if ("cs".equalsIgnoreCase(lx)) {

			String sql = "select distinct cs from view_ssxx ";

			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			if (yqdm != null && yqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < yqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" yqdm = ? ");
					
					inPutList.add(yqdm[i]);
				}
				query.append(" )");

			}
			
			if (lddm != null && lddm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < lddm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" lddm = ? ");
					
					inPutList.add(lddm[i]);
				}
				query.append(" )");

			}
			
			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from xg_gygl_ldglb b ");
				query.append(" where view_ssxx.lddm = b.lddm and b.yhm='"+userName+"') ");
			}
			query.append(" order by to_number(cs) ");
			
			sql+=query;
			
			colList = new String[] { "cs", "cs" };

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,
					sql);
		}
		//���Һ�
		else if ("qsh".equalsIgnoreCase(lx)) {

			String sql = "select distinct qsh from view_ssxx ";
			
			//У��
			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			//԰��
			if (yqdm != null && yqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < yqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" yqdm = ? ");
					
					inPutList.add(yqdm[i]);
				}
				query.append(" )");

			}
			
			//	¥��
			if (lddm != null && lddm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < lddm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" lddm = ? ");
					
					inPutList.add(lddm[i]);
				}
				query.append(" )");

			}
			
			// ����
			if (cs != null && cs.length > 0) {
				query.append(" and (");
				for (int i = 0; i < cs.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" cs = ? ");

					inPutList.add(cs[i]);
				}
				query.append(" )");

			}
			
			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from xg_gygl_ldglb b ");
				query.append(" where view_ssxx.lddm = b.lddm and b.yhm='"+userName+"') ");
			}
			
			query.append(" order by qsh ");
			
			sql+=query;
			
			colList = new String[] { "qsh"};

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,
					sql);

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				String qsh = map.get("qsh");
				String qshpy = CnToEnUtil.getFirstLetter(qsh);

				list.get(i).put("qshpy", qshpy);
				list.get(i).put("qshdm", qsh);
				list.get(i).put("qshmc", qsh);
			}
		}
		// ¥��(ʣ��������)
		else if ("ldqs".equalsIgnoreCase(lx)) {
			list = dao.getLdForWfpQssList(xqdm, yqdm, gyglyQx, userName,
					userDep);
		}
		return list;
	}
	
	/**
	 * ���������Ϣ������ƴ����
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getQsInfoByPy(String py,
			String[] xqdm, String[] yqdm, String[] lddm, String[] cs,
			String userStatus, String userName, String userDep) {

		// ��������б�
		List<HashMap<String, String>> qshList = dao.getQsList(xqdm, yqdm, lddm,
				cs, userStatus, userName, userDep);

		for (int i = 0; i < qshList.size(); i++) {

			HashMap<String, String> map = qshList.get(i);

			String qsh = map.get("qsh");
			String qshpy = CnToEnUtil.getFirstLetter(qsh);

			qshList.get(i).put("qshpy", qshpy);
		}

		List<HashMap<String, String>> qsList = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < qshList.size(); i++) {

			HashMap<String, String> map = qshList.get(i);

			String qsh = map.get("qsh");
			String qshpy = map.get("qshpy");

			if (qshpy.equalsIgnoreCase(py)) {

				HashMap<String, String> newQsMap = new HashMap<String, String>();
				newQsMap.put("qshdm", qsh);
				newQsMap.put("qshmc", qsh);

				qsList.add(newQsMap);

			}
		}

		return qsList;
	}
	
	/**
	 * ��ò���ƴ��
	 * 
	 * @author ΰ�����
	 * 
	 */
	public String getBmpy(String bmdm, String bmlx) {

		DAO dao = DAO.getInstance();
		
		
		String dm = bmlx + "dm";
		String mc = bmlx + "mc";
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct ");
		sql.append(dm + "," + mc);
		sql.append(" from view_njxyzybj ");
		sql.append(" where " + dm + " = ? ");
		
		String bmmc = dao.getOneRs(sql.toString(), new String[]{bmdm}, mc);
		String bmpy = CnToEnUtil.getFirstLetter(bmmc);// ����
		
		return bmpy;
	}
	
	/**
	 * ��ò�ѯ����(�û����)
	 * 
	 * @author ΰ�����
	 * 
	 */
	public String getSearchTjByUser(HttpServletRequest request, String tableBm,
			String xydm, String bjdm) {

		User user = getUser(request);

		// �û���
		String userName = user.getUserName();
		// �û�����
		String userType = user.getUserType();
		// �û����ڲ���
		String userDep = user.getUserDep();

		// ����ԱȨ��
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// ������Ȩ��
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());

		boolean isXy = false;

		if ("xy".equalsIgnoreCase(userType) && !fdyqx && !bzrqx) {
			// ѧԺ�û�
			isXy = true;
		}
		
		StringBuilder query = new StringBuilder();
		
		if (isXy && !Base.isNull(xydm)) {// �����û�ΪѧԺ
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(xydm + " = '" + userDep + "' ");

		} else if (fdyqx && bzrqx && !Base.isNull(bjdm)) {// �����û�Ϊ����Ա�������

			query.append(" and (exists (select 1 from bzrbbb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");
			
			query.append(" or exists (select 1 from fdybjb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "')) ");

		} else if (fdyqx && !Base.isNull(bjdm)) {
			
			query.append(" and exists (select 1 from fdybjb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");

		} else if (bzrqx) {
			
			query.append(" and exists (select 1 from bzrbbb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");
			
		} 
		
		return query.toString();
	}
	
	/**
	 * ��ò�ѯ����(�û����)
	 * 
	 * @author zhangxiaobin
	 * 
	 */
	public String getSearchTjByUser2(User user2, String tableBm,
			String xydm, String bjdm) {

		// �û���
		String userName = user2.getUserName();
		// �û�����
		String userType = user2.getUserType();
		// �û����ڲ���
		String userDep = user2.getUserDep();

		// ����ԱȨ��
		boolean fdyqx = Boolean.parseBoolean(user2.getFdyQx());
		// ������Ȩ��
		boolean bzrqx = Boolean.parseBoolean(user2.getBzrQx());

		boolean isXy = false;

		if ("xy".equalsIgnoreCase(userType) && !fdyqx && !bzrqx) {
			// ѧԺ�û�
			isXy = true;
		}
		
		StringBuilder query = new StringBuilder();
		
		if (isXy && !Base.isNull(xydm)) {// �����û�ΪѧԺ
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(xydm + " = '" + userDep + "' ");

		} else if (fdyqx && bzrqx && !Base.isNull(bjdm)) {// �����û�Ϊ����Ա�������

			query.append(" and (exists (select 1 from bzrbbb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");
			
			query.append(" or exists (select 1 from fdybjb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "')) ");

		} else if (fdyqx && !Base.isNull(bjdm)) {
			
			query.append(" and exists (select 1 from fdybjb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");

		} else if (bzrqx) {
			
			query.append(" and exists (select 1 from bzrbbb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");
			
		} 
		
		return query.toString();
	}
	
	/**
	 * ��ò�ѯ����(�û����)
	 * 
	 * @author ΰ�����
	 * 
	 */
	public static String getSearchTjByUser(User user, String tableBm,
			String xydm, String bjdm) { 

		// �û���
		String userName = user.getUserName();
		// �û�����
		String userType = user.getUserType();
		// �û����ڲ���
		String userDep = user.getUserDep();
		// �û����
		String userStatus = user.getUserStatus();
		// ��Ժ�û�
		String syQx = user.getSyQx();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		
		String yhsjfwSql = new YhsjfwService().getYhsjfw(user, tableBm, xydm, bjdm);
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			query.append(yhsjfwSql);
		}
		
		if ("xy".equalsIgnoreCase(userStatus)) {// �����û�ΪѧԺ
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
				query.append(xydm + " = '" + userDep + "' ");
			}
		} else if ("sy".equalsIgnoreCase(userStatus)) {

			query.append(" exists (select 1 from xg_xtwh_sybjglb xx join fdyxxb xy on xx.sydm = xy.sydm where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  xx.bjdm ");
			query.append(" and xy.zgh = '" + userName + "') ");
			
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("zybj =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");

		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" exists (select 1 from fdybjb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");

		} else if ("jd".equalsIgnoreCase(userStatus)) {// �����û�Ϊ����Ա�������
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" (exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("zybj =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");
			
			query.append(" or exists (select 1 from fdybjb z where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  z.bjdm ");
			query.append(" and z.zgh = '" + userName + "')) ");

		}else if("stu".equalsIgnoreCase(userType)){
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("xh = '"+userName+"'");
		}else if("parent".equalsIgnoreCase(userType)){
			//�ҳ��û�������Ů����
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("xh = '"+user.getChildId()+"'");
		}else{
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(" 1= 1 ");
			}
		}

		query.append(" )");
		
		/*if("yes".equalsIgnoreCase(syQx)){
			query.append(" and (");
			query.append(" exists (select 1 from xg_xtwh_sybjglb xx join fdyxxb xy on xx.sydm = xy.sydm where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  xx.bjdm ");
			query.append(" and xy.zgh = '" + userName + "') ");
			query.append(" )");
		}*/
		
		return query.toString();
	}

	/**
	 * ��ȡ�û���ѯ���� ������ר�� 20181009
	 */
	public static String getSearchTjByUserForCpz(User user, String tableBm,
										   String xydm, String bjdm) {

		// �û���
		String userName = user.getUserName();
		// �û�����
		String userType = user.getUserType();
		// �û����ڲ���
		String userDep = user.getUserDep();
		// �û����
		String userStatus = user.getUserStatus();
		// ��Ժ�û�
		String syQx = user.getSyQx();

		StringBuilder query = new StringBuilder();

		query.append(" and (");


		String yhsjfwSql = new YhsjfwService().getYhsjfw(user, tableBm, xydm, bjdm);
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			query.append(yhsjfwSql);
		}

		if ("xy".equalsIgnoreCase(userStatus)) {// �����û�ΪѧԺ
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
				query.append(xydm + " = '" + userDep + "' ");
			}
		} else if ("sy".equalsIgnoreCase(userStatus)) {

			query.append(" exists(select 1 from xg_zhcp_fstjjlb bb WHERE ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  bb.bjdm ");
			query.append(" and (bb.xn,bb.xq) = (select xn,xq from XG_PJPY_NEW_CSSZB) ");
			query.append(" AND exists(select 1 from fdycpzglb where cpzdm = bb.CPZDM and zgh = '"+userName+"')");
			query.append(" ) ");

		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("zybj =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");

		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" exists (select 1 from fdybjb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");

		} else if ("jd".equalsIgnoreCase(userStatus)) {// �����û�Ϊ����Ա�������
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" (exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("zybj =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");

			query.append(" or exists (select 1 from fdybjb z where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  z.bjdm ");
			query.append(" and z.zgh = '" + userName + "')) ");

		}else if("stu".equalsIgnoreCase(userType)){
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("xh = '"+userName+"'");
		}else if("parent".equalsIgnoreCase(userType)){
			//�ҳ��û�������Ů����
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("xh = '"+user.getChildId()+"'");
		}else{
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(" 1= 1 ");
			}
		}

		query.append(" )");

		return query.toString();
	}

	public static String getSearchTjByUserOfYdxg(User user, String tableBm,
			String xydm, String bjdm) { 

		// �û���
		String userName = user.getUserName();
		// �û�����
		String userType = user.getUserType();
		// �û����ڲ���
		String userDep = user.getUserDep();
		// �û����
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		
		String yhsjfwSql = new YhsjfwService().getYhsjfw(user, tableBm, xydm, bjdm);
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			query.append(yhsjfwSql);
		}
		
		if ("xy".equalsIgnoreCase(userStatus)) {// �����û�ΪѧԺ
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
				query.append(xydm + " = '" + userDep + "' ");
			}
		} else if ("bzr".equalsIgnoreCase(userStatus)) {
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");

		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" exists (select 1 from fdybjb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");

		} else if ("jd".equalsIgnoreCase(userStatus)) {// �����û�Ϊ����Ա�������
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				query.append(" or ");
			}
			query.append(" (exists (select 1 from bzrbbb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.zgh = '" + userName + "') ");
			
			query.append(" or exists (select 1 from fdybjb z where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  z.bjdm ");
			query.append(" and z.zgh = '" + userName + "')) ");

		}else if("stu".equalsIgnoreCase(userType)){
			query.append(" exists (select 1 from xsxxb x where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(bjdm + " =  x.bjdm ");
			query.append(" and x.xh = '" + userName + "') ");
		}else{
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(" 1= 1 ");
			}
		}

		query.append(" )");
		return query.toString();
	}

	/**
	 *  ��ò�ѯ����(�û����).
	 *  ѧУ�û������ˣ������û����û�������ѧԺ����Ĺ��ˣ�
	 *  ��Ӧ�Ĺ��ܲ˵���һ�㿪��ѧԺ�û���
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-04-17 11:10
	 * @param user
	 * @param tableBm
	 * @param xydm
	 * @return java.lang.String
	 * @throw
	 */
	public static String getSearchTjByUserOnlyXxXy(User user, String tableBm, String xydm) {

		String userStatus = user.getUserStatus();
		String userDep = user.getUserDep();
		StringBuilder query = new StringBuilder();

		if(!"xx".equalsIgnoreCase(userStatus)){
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(xydm + " = '" + userDep + "' ");
		}
		return query.toString();
	}


	public static String getSearchTjByUser_jhzy(User user, String tableBm, String xydm) {

		StringBuilder query = new StringBuilder();
		String userStatus = user.getUserStatus();
		if(!"xx".equalsIgnoreCase(userStatus)){
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(xydm + " = (select bmdm from fdyxxb where zgh='"+user.getUserName()+"') ");
		}
		return query.toString();
	}


		/**
         *
         * @����:�������֮�Ѹ��Ի�Ȩ�޿���
         * @���ߣ�xiaxia[���ţ�1104]
         * @���ڣ�2015-2-14 ����03:48:26
         * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
         * @param user
         * @param tableBm
         * @param xydm
         * @param bjdm
         * @return
         * String ��������
         * @throws
         */
	public static String getSearchQxTjByUser(User user, String tableBm,
			String xydm,String flag) { 

		// �û���
		String userName = user.getUserName();
		// �û�����
		String userType = user.getUserType();
		// �û����ڲ���
		String userDep = user.getUserDep();
		// �û����
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		
		
		if ("xy".equalsIgnoreCase(userStatus)) {// �����û�ΪѧԺ
				query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
				query.append(xydm + " = '" + userDep + "' ");
		}else if("xx".equalsIgnoreCase(userStatus)){
				query.append(" 1= 1 ");
		}
		if("true".equals(flag)){
			query.append(" or xydm='"+userDep+"'");
		}

		query.append(" )");
		return query.toString();
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:������չȨ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-13 ����03:08:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param tableBm
	 * @param xydm
	 * @param bjdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getSearchTjOfSztzByUser(User user, String tableBm,
			String sbbmdm,String sbr) throws Exception { 

		// �û���
		String userName = user.getUserName();
		// �û�����
		String userType = user.getUserType();
		// �û����ڲ���
		String userDep = user.getUserDep();
		// �û����
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		
		List<String> glyList = new GlyglDao().getGlyList();
		if(glyList.contains(userName)){
			query.append(" 1= 1 ");
		}
		
		else {
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(sbr + " = '" + userName + "' ");
		}

		query.append(" )");
		return query.toString();
	}
	public static String getSearchSztzShTjByUser(User user, String tableBm,
			String xydm,String sbr) { 

		// �û���
		String userName = user.getUserName();
		// �û�����
		String userType = user.getUserType();
		// �û����ڲ���
		String userDep = user.getUserDep();
		// �û����
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		 if("xx".equalsIgnoreCase(userStatus)){
				query.append(" 1= 1 ");
			}
			else{
					query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
					query.append(xydm + " = '" + userDep + "' ");
				}
			

		query.append(" )");
		return query.toString();
	}
	
	public static String getSearchTjOfKycxByUser(User user, String tableBm,
			String xh) { 

		// �û���
		String userName = user.getUserName();
		// �û����
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		 if("xx".equalsIgnoreCase(userStatus)){
				query.append(" 1= 1 ");
			}
			else{
					query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
					query.append(xh + " = '" + userName + "' ");
				}
			

		query.append(" )");
		return query.toString();
	}
	public static String getSearchTjOfFycxByUser(User user, String tableBm,
			String xh) { 

		// �û���
		String userName = user.getUserName();
		// �û����
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		// ����ԱȨ��
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// ������Ȩ��
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		query.append(" and (");
		
		 if("xx".equalsIgnoreCase(userStatus)){
				query.append(" 1= 1 ");
			}
		 else if ("xy".equalsIgnoreCase(userStatus)||fdyqx||bzrqx) {// �����û�ΪѧԺ
					query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
					query.append("xmssdw= '" + user.getUserDep() + "' ");
				
			}
			else{
					query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
					query.append(xh + " = '" + userName + "' ");
				}
			

		query.append(" )");
		return query.toString();
	}
	/**
	 * 
	 * @����:��ʦ�鿴������¼Ȩ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-3 ����11:06:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param tableBm
	 * @param xydm
	 * @param bjdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getSearchGzjlTjByUser(User user, String tableBm,
			String xydm,String bjdm) { 

		// �û���
		String userName = user.getUserName();
		// �û�����
		String userType = user.getUserType();
		// �û����ڲ���
		String userDep = user.getUserDep();
		// �û����
		String userStatus = user.getUserStatus();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		
		String yhsjfwSql = new YhsjfwService().getYhsjfw(user, tableBm, xydm, bjdm);
		if(yhsjfwSql != null && !yhsjfwSql.equals("")){
			query.append(yhsjfwSql);
		}
		
		if ("xy".equalsIgnoreCase(userStatus)) {// �����û�ΪѧԺ
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
				query.append(xydm + " = '" + userDep + "' ");
			}
		}else if("xx".equalsIgnoreCase(userStatus)){
			if(yhsjfwSql == null || yhsjfwSql.equals("")){
				query.append(" 1= 1 ");
			}
		}else {
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".zgh=");
			query.append("'"+userName+"'");
		}

		query.append(" )");
		return query.toString();
	}
	
	/**
	 * 
	 * @����:��ð�����/����Ա��˸�λ������(�û����)
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-7-4 ����09:32:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param tableBm
	 * @param xydm
	 * @param bjdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getShgwzByUser(User user, String tableBm,
			String xydm, String bjdm) {

		// �û���
		String userName = user.getUserName();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		
		// ������
		query.append(" (gwz = 'bzr' and exists (select 1 from bzrbbb x where ");
		query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
		query.append("zybj =  x.bjdm ");
		query.append(" and x.zgh = '" + userName + "')) ");

		// ����Ա
		query.append(" or (gwz = 'fdy' and exists (select 1 from fdybjb x where ");
		query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
		query.append(bjdm + " =  x.bjdm ");
		query.append(" and x.zgh = '" + userName + "')) ");
		
		// ������+����Ա
		query.append(" or (gwz = 'bzrfdy' and ( exists (select 1 from bzrbbb x where ");
		query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
		query.append("zybj =  x.bjdm ");
		query.append(" and x.zgh = '" + userName + "') or exists (select 1 from fdybjb x where ");
		query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
		query.append(bjdm + " =  x.bjdm ");
		query.append(" and x.zgh = '" + userName + "'))) ");
		
		// ����
		query.append(" or (gwz is null)");

		query.append(" )");
		return query.toString();
	}
		
	/**
	 * ��ò�ѯ����(�û����)
	 * 
	 * @author ΰ�����
	 * 
	 */
	public String getSearchTjByUser(String tableBm, User user) {

		// �û���
		String userName = user.getUserName();
		// �û�����
		String userType = user.getUserType();
		// �û����ڲ���
		String userDep = user.getUserDep();
		// ��Ԣ����ԱȨ��
		String gyglyQx = user.getGyglyQx();
		// �û����
		String userStatus = user.getUserStatus();
		// �����Ƶ����
		HashMap<String, String> notCtrlStatus = user.getNotCtrlStatus();
		boolean xy_flag = true;
		if (!notCtrlStatus.isEmpty()
				&& "yes".equalsIgnoreCase(notCtrlStatus.get("xy"))) {
			xy_flag = false;
		}

		boolean jd_flag = true;
		if (!notCtrlStatus.isEmpty()
				&& "yes".equalsIgnoreCase(notCtrlStatus.get("jd"))) {
			jd_flag = false;
		}

		boolean fdy_flag = true;
		if (!notCtrlStatus.isEmpty()
				&& "yes".equalsIgnoreCase(notCtrlStatus.get("fdy"))) {
			fdy_flag = false;
		}

		boolean bzr_flag = true;
		if (!notCtrlStatus.isEmpty()
				&& "yes".equalsIgnoreCase(notCtrlStatus.get("bzr"))) {
			bzr_flag = false;
		}

		boolean gygly_flag = true;
		if (!notCtrlStatus.isEmpty()
				&& "yes".equalsIgnoreCase(notCtrlStatus.get("gygly"))) {
			gygly_flag = false;
		}

		StringBuilder query = new StringBuilder();

		if ("xy".equalsIgnoreCase(userStatus) && xy_flag) {// �����û�ΪѧԺ
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("xydm = '" + userDep + "' ");

		} else if ("jd".equalsIgnoreCase(userStatus) && jd_flag) {// �����û�Ϊ����Ա�������

			query.append(" and (exists (select 1 from bzrbbb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("bjdm =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");

			query.append(" or exists (select 1 from fdybjb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("bjdm =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "')) ");

		} else if ("fdy".equalsIgnoreCase(userStatus) && fdy_flag) {

			query.append(" and exists (select 1 from fdybjb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("bjdm =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");

		} else if ("bzr".equalsIgnoreCase(userStatus) && bzr_flag) {

			query.append(" and exists (select 1 from bzrbbb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append(" bjdm =  b.bjdm ");
			query.append(" and b.zgh = '" + userName + "') ");

		}

		if ("yes".equalsIgnoreCase(gyglyQx) && gygly_flag) {
			query.append(" and exists (select 1 from xg_gygl_ldglb b where ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + ".");
			query.append("lddm =b.lddm ");
			query.append(" and b.yhm='" + userName + "') ");
		}

		return query.toString();
	}
	
	/**
	 * ��ò�ѯ����
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * 
	 */
	public static String getSearchTj(SearchModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		boolean singlePlcx=true;
		SearchDAO dao = new SearchDAO();
		
		// �����б�
		List<HashMap<String, String>> tjList = dao.getGnmkTjList(model);

		// ģ����ѯ����
		String mhcx_lx = model.getMhcx_lx();
		// ģ����ѯ
		String input_mhcx = model.getInput_mhcx();
		if (!Base.isNull(input_mhcx)) {
			model.setSearch_tj_mhcx(input_mhcx);
		}
		//������ѯ
		String plcx_xh = model.getPlcx_xh();
		if(!Base.isNull(plcx_xh)){
			model.setSearch_tj_xh(plcx_xh.replaceAll(" ", ",").replaceAll("\r", ",").replaceAll("\n",
			",").split(","));
		}
		String plcx_xm = model.getPlcx_xm();
		if(!Base.isNull(plcx_xm)){
			model.setSearch_tj_xm(plcx_xm.replaceAll(" ", ",").replaceAll("\r", ",").replaceAll("\n",
			",").split(","));
		}
		
		// ��ѯ������ģ����ѯ��
		String[] mhcx = model.getSearch_tj_mhcx();

		// ƴ�Ӳ�ѯ����
		StringBuilder query = new StringBuilder();
		// ģ������
		StringBuilder mhQuery = new StringBuilder();
		// ʱ������
		StringBuilder sjQuery = new StringBuilder();
		// ��ֵ��������
		StringBuilder numQuery = new StringBuilder();
		
		Class myClass = model.getClass();

		if (tjList != null && tjList.size() > 0) {
			
			// ��ʼʱ��
			String[] input_kssj = model.getSearch_tj_kssj();
			// ����ʱ��
			String[] input_jssj = model.getSearch_tj_jssj();
			
			int kssjNum = 0;
			int jssjNum = 0;
			int count = 0;
			
			// ��Сֵ
			String[] input_ksnum = model.getSearch_tj_ksnum();
			// ���ֵ
			String[] input_jsnum = model.getSearch_tj_jsnum();
			
			int ksnumNum = 0;
			int jsnumNum = 0;
			
			for (int i = 0; i<tjList.size() ; i++) {
				
				HashMap<String, String> map = tjList.get(i);
				// ����
				String lx = map.get("lx");
				// ����
				String tj = map.get("tj");
				// �ֶ�
				String zd = Base.isNull(map.get("zd")) ? tj : map.get("zd");

				// ģ����ѯ
				if ("mhcx".equalsIgnoreCase(lx)
						&& "all".equalsIgnoreCase(mhcx_lx)) {
					
					if (mhcx != null && mhcx.length > 0) {
						if(Base.isNull(mhQuery.toString())){
							mhQuery.append(" and ((");
						}else{
							mhQuery.append(" or ( ");
						}
						for (int j = 0; j < mhcx.length; j++) {
							if (j == 0) {
								mhQuery.append(zd + " like '%' || ? || '%' ");
							} else {
								mhQuery.append(" or " + zd + " like '%' || ? || '%' ");
							}
						}
						mhQuery.append(")");
					}
				}
				// ʱ���ѯ
				else if ("sjcx".equalsIgnoreCase(lx)) {

					if (input_kssj != null && input_kssj.length > 0) {
						
						String kssj = input_kssj[kssjNum];

						if (!Base.isNull(kssj)) {
							sjQuery.append(" and (to_number("+replaceTime(zd)+") >= ? )");
							//sjQuery.append(" or to_number(" + zd + ")>= ? )");
						}
						kssjNum++;
					}
					
					if (input_jssj != null && input_jssj.length > 0) {
					
						String jssj = input_jssj[jssjNum];

						if (!Base.isNull(jssj)) {
							sjQuery.append(" and ( to_number("+replaceTime(zd)+") <= ? )");
							//sjQuery.append(" or to_number(" + zd + ")<= ? )");
						}
						jssjNum++;
					}
				}
				// ����ʱ���ѯ
				else if ("dgsjcx".equalsIgnoreCase(lx)) {

					if (input_kssj != null && input_kssj.length > 0) {
						
						String kssj = input_kssj[count];

						if (!Base.isNull(kssj)) {
							sjQuery.append(" and ( to_number("+replaceTime(zd)+") = ? )");
							//sjQuery.append(" or to_number(" + zd + ")>= ? )");
						}

						count++;
					}
				}
				// ��ֵ�����ѯ
				else if ("numcx".equalsIgnoreCase(lx)) {

					if (input_ksnum != null && input_ksnum.length > 0) {
						
						String ksnum = input_ksnum[ksnumNum];

						if (!Base.isNull(ksnum)) {
							numQuery.append(" and (to_number("+zd+") >= to_number(?) )");
							//numQuery.append(" or to_number(" + zd + ")>= ? )");
						}
						ksnumNum++;
					}
					
					if (input_jsnum != null && input_jsnum.length > 0) {
					
						String jsnum = input_jsnum[jsnumNum];

						if (!Base.isNull(jsnum)) {
							numQuery.append(" and (to_number("+zd+") <= to_number(?) )");
							//numQuery.append(" or to_number(" + zd + ")<= ? )");
						}
						jsnumNum++;
					}
				}
				// �����ѯ
				else if ("djcx".equalsIgnoreCase(lx)||"zdycx".equalsIgnoreCase(lx)) {
					
					String methodName = "getSearch_tj_" + tj;

					String[] sT = (String[]) myClass.getMethod(methodName,
							(Class[]) null).invoke(model, (Object[]) null);

					// �����������
					if (sT != null && sT.length > 0) {
						query.append(" and ( ");
						for (int j = 0; j < sT.length; j++) {
							if (j == 0) {
								query.append(zd + " = ? ");
							} else {
								query.append(" or " + zd + " = ? ");
							}
						}
						query.append(")");
					}
				}
					else if ("plcx".equalsIgnoreCase(lx)) {
										
										String methodName = "getSearch_tj_" + tj;
					
										String[] sT = (String[]) myClass.getMethod(methodName,
												(Class[]) null).invoke(model, (Object[]) null);
					
										// �����������
										if (sT != null && sT.length > 0) {
											if(singlePlcx){
												query.append(" and ( ");
												singlePlcx=false;
											}else{
												query.append(" or  ");
											}
											
											for (int j = 0; j < sT.length; j++) {
												if (j == 0) {
													query.append(zd + " = ? ");
												} else {
													query.append(" or " + zd + " = ? ");
												}
											}
											
										}
									}
			}
			if(!singlePlcx){
				query.append(")");
			}
			if (!"all".equalsIgnoreCase(mhcx_lx)) {
				if (mhcx != null && mhcx.length > 0) {
					mhcx_lx = mhcx_lx.replace("mhcx_", "");
					if (Base.isNull(mhQuery.toString())) {
						mhQuery.append(" and ((");
					} else {
						mhQuery.append(" or ( ");
					}
					for (int j = 0; j < mhcx.length; j++) {
						if (j == 0) {
							mhQuery.append(mhcx_lx + " like '%' || ? || '%' ");
						} else {
							mhQuery.append(" or " + mhcx_lx
									+ " like '%' || ? || '%' ");
						}
					}
					mhQuery.append(")");
				}
			}
		}

		if(!Base.isNull(mhQuery.toString())){
			mhQuery.append(")");
		}
		query.append(mhQuery);
		query.append(sjQuery);
		query.append(numQuery);
		
		// ����ѧ��
		String input_xh = model.getInput_xh();

		// ѧ��
		if (!Base.isNull(input_xh)) {
			query.append(" and xh = ? ");
		}
		
		return query.toString();
	}
	
	
	/**
	 * 
	 * @����:��ò�ѯ����
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2016-1-12 ����11:05:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param tableBm 
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * String �������� 
	 * @throws
	 */
	public static String getSearchTj(SearchModel model, String tableBm)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		SearchDAO dao = new SearchDAO();
		
		// �����б�
		List<HashMap<String, String>> tjList = dao.getGnmkTjList(model);

		// ģ����ѯ����
		String mhcx_lx = model.getMhcx_lx();
		// ģ����ѯ
		String input_mhcx = model.getInput_mhcx();
		if (!Base.isNull(input_mhcx)) {
			model.setSearch_tj_mhcx(input_mhcx);
		}
		
		// ��ѯ������ģ����ѯ��
		String[] mhcx = model.getSearch_tj_mhcx();

		// ƴ�Ӳ�ѯ����
		StringBuilder query = new StringBuilder();
		// ģ������
		StringBuilder mhQuery = new StringBuilder();
		// ʱ������
		StringBuilder sjQuery = new StringBuilder();
		// ��ֵ��������
		StringBuilder numQuery = new StringBuilder();
		
		Class myClass = model.getClass();

		if (tjList != null && tjList.size() > 0) {
			
			// ��ʼʱ��
			String[] input_kssj = model.getSearch_tj_kssj();
			// ����ʱ��
			String[] input_jssj = model.getSearch_tj_jssj();
			
			int kssjNum = 0;
			int jssjNum = 0;
			int count = 0;
			
			// ��Сֵ
			String[] input_ksnum = model.getSearch_tj_ksnum();
			// ���ֵ
			String[] input_jsnum = model.getSearch_tj_jsnum();
			
			int ksnumNum = 0;
			int jsnumNum = 0;
			
			for (int i = 0; i<tjList.size() ; i++) {
				
				HashMap<String, String> map = tjList.get(i);
				// ����
				String lx = map.get("lx");
				// ����
				String tj = map.get("tj");
				// �ֶ�
				String zd = Base.isNull(map.get("zd")) ? tj : map.get("zd");

				// ģ����ѯ
				if ("mhcx".equalsIgnoreCase(lx)
						&& "all".equalsIgnoreCase(mhcx_lx)) {
					
					if (mhcx != null && mhcx.length > 0) {
						if(Base.isNull(mhQuery.toString())){
							mhQuery.append(" and ((");
						}else{
							mhQuery.append(" or ( ");
						}
						for (int j = 0; j < mhcx.length; j++) {
							if (j == 0) {
								mhQuery.append(Base.isNull(tableBm) ? "" : tableBm + ".");
								mhQuery.append(zd + " like '%' || ? || '%' ");
							} else {
								mhQuery.append(" or ");
								mhQuery.append(Base.isNull(tableBm) ? "" : tableBm + ".");
								mhQuery.append(zd + " like '%' || ? || '%' ");
							}
						}
						mhQuery.append(")");
					}
				}
				// ʱ���ѯ
				else if ("sjcx".equalsIgnoreCase(lx)) {

					if (input_kssj != null && input_kssj.length > 0) {
						
						String kssj = input_kssj[kssjNum];

						if (!Base.isNull(kssj)) {
							sjQuery.append(" and (to_number("+replaceTime(Base.isNull(tableBm) ? "" : tableBm + "." + zd)+") >= ? )");
							//sjQuery.append(" or to_number(" + zd + ")>= ? )");
						}
						kssjNum++;
					}
					
					if (input_jssj != null && input_jssj.length > 0) {
					
						String jssj = input_jssj[jssjNum];

						if (!Base.isNull(jssj)) {
							sjQuery.append(" and ( to_number("+replaceTime(Base.isNull(tableBm) ? "" : tableBm + "." + zd)+") <= ? )");
							//sjQuery.append(" or to_number(" + zd + ")<= ? )");
						}
						jssjNum++;
					}
				}
				// ����ʱ���ѯ
				else if ("dgsjcx".equalsIgnoreCase(lx)) {

					if (input_kssj != null && input_kssj.length > 0) {
						
						String kssj = input_kssj[count];

						if (!Base.isNull(kssj)) {
							sjQuery.append(" and ( to_number("+replaceTime(Base.isNull(tableBm) ? "" : tableBm + "." + zd)+") = ? )");
							//sjQuery.append(" or to_number(" + zd + ")>= ? )");
						}

						count++;
					}
				}
				// ��ֵ�����ѯ
				else if ("numcx".equalsIgnoreCase(lx)) {

					if (input_ksnum != null && input_ksnum.length > 0) {
						
						String ksnum = input_ksnum[ksnumNum];

						if (!Base.isNull(ksnum)) {
							numQuery.append(" and (to_number(" );
							numQuery.append(Base.isNull(tableBm) ? "" : tableBm + "." + zd + ") >= to_number(?) )");
							//numQuery.append(" or to_number(" + zd + ")>= ? )");
						}
						ksnumNum++;
					}
					
					if (input_jsnum != null && input_jsnum.length > 0) {
					
						String jsnum = input_jsnum[jsnumNum];

						if (!Base.isNull(jsnum)) {
							numQuery.append(" and (to_number(" );
							numQuery.append(Base.isNull(tableBm) ? "" : tableBm + "." + zd + ") <= to_number(?) )");
							//numQuery.append(" or to_number(" + zd + ")<= ? )");
						}
						jsnumNum++;
					}
				}
				// �����ѯ
				else if ("djcx".equalsIgnoreCase(lx)||"zdycx".equalsIgnoreCase(lx)) {
					
					String methodName = "getSearch_tj_" + tj;

					String[] sT = (String[]) myClass.getMethod(methodName,
							(Class[]) null).invoke(model, (Object[]) null);

					// �����������
					if (sT != null && sT.length > 0) {
						query.append(" and ( ");
						for (int j = 0; j < sT.length; j++) {
							if (j == 0) {
								query.append(Base.isNull(tableBm) ? "" : tableBm + "." + zd + " = ? ");
							} else {
								query.append(" or ");
								query.append(Base.isNull(tableBm) ? "" : tableBm + "." + zd + " = ? ");
							}
						}
						query.append(")");
					}
				}
			}
			
			if (!"all".equalsIgnoreCase(mhcx_lx)) {
				if (mhcx != null && mhcx.length > 0) {
					mhcx_lx = mhcx_lx.replace("mhcx_", "");
					if (Base.isNull(mhQuery.toString())) {
						mhQuery.append(" and ((");
					} else {
						mhQuery.append(" or ( ");
					}
					for (int j = 0; j < mhcx.length; j++) {
						if (j == 0) {
							mhQuery.append(Base.isNull(tableBm) ? "" : tableBm + "." + mhcx_lx + " like '%' || ? || '%' ");
						} else {
							mhQuery.append(" or ");
							mhQuery.append(Base.isNull(tableBm) ? "" : tableBm + "." + mhcx_lx
									+ " like '%' || ? || '%' ");
						}
					}
					mhQuery.append(")");
				}
			}
		}

		if(!Base.isNull(mhQuery.toString())){
			mhQuery.append(")");
		}
		query.append(mhQuery);
		query.append(sjQuery);
		query.append(numQuery);
		
		// ����ѧ��
		String input_xh = model.getInput_xh();

		// ѧ��
		if (!Base.isNull(input_xh)) {
			query.append(" and ");
			query.append(Base.isNull(tableBm) ? "" : tableBm + "." +"xh = ? ");
		}
		
		return query.toString();
	}
	
	/**
	 * ƴ�����ڿؼ���ʽ��Լ���
	 * @param time
	 * @return
	 */
	public static String replaceTime(String time){
		
		StringBuilder replaceTime = new StringBuilder();
		
		String[] replaceLx={"-","��","��","��","\\","/"," ",":"};
		
		for (int i=0;i<replaceLx.length;i++){
			
			if(i==0){
				
				replaceTime.append("  replace(" + time + ",'"+replaceLx[i]+"','') ");
			
			}else{
				
				replaceTime=new StringBuilder("  replace(" + replaceTime + ",'"+replaceLx[i]+"','') ");
				
			}
		
		}
		replaceTime=new StringBuilder("  substr(" + replaceTime + ",1,8) ");
		
		return replaceTime.toString();
		
	}

	/**
	 * ��ò�ѯ����
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public static String[] getTjInput(SearchModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		SearchDAO dao = new SearchDAO();
		// �����б�
		List<HashMap<String, String>> tjList = dao.getGnmkTjList(model);
		
		Class myClass = model.getClass();

		// ģ����ѯ����
		String mhcx_lx = model.getMhcx_lx();
		// ��������ֵ
		ArrayList<String> inputValue = new ArrayList<String>();
		// ģ������
		ArrayList<String> mhValue = new ArrayList<String>();
		// ʱ������
		ArrayList<String> sjValue = new ArrayList<String>();
		// ��ֵ��������
		ArrayList<String> numValue = new ArrayList<String>();
		
		if (tjList != null && tjList.size() > 0) {
			// ��ʼʱ��
			String[] input_kssj = model.getSearch_tj_kssj();
			// ����ʱ��
			String[] input_jssj = model.getSearch_tj_jssj();

			int count = 0;
			
			// ��Сֵ
			String[] input_ksnum = model.getSearch_tj_ksnum();
			// ���ֵ
			String[] input_jsnum = model.getSearch_tj_jsnum();
			
			int numcount = 0;
			
			for (int i = 0; i < tjList.size(); i++) {
				HashMap<String, String> map = tjList.get(i);
				// ����
				String lx = map.get("lx");
				// ����
				String tj = map.get("tj");

				// ģ����ѯ
				if ("mhcx".equalsIgnoreCase(lx)
						&& "all".equalsIgnoreCase(mhcx_lx)) {
					String methodName = "getSearch_tj_mhcx";
					String[] sT = (String[]) myClass.getMethod(methodName,
							(Class[]) null).invoke(model, (Object[]) null);

					// ��������
					if (sT != null && sT.length > 0) {
						//num += sT.length;
						//colList.add(tj);
						for (int j = 0; j < sT.length; j++) {
							mhValue.add(sT[j]);
						}
					}
				}
				// ʱ���ѯ
				else if ("sjcx".equalsIgnoreCase(lx)) {

					if (input_kssj != null && input_kssj.length > 0
							&& input_jssj != null && input_jssj.length > 0) {
						String kssj = input_kssj[count];
						String jssj = input_jssj[count];

						if (!Base.isNull(kssj)) {
							sjValue.add(kssj);
//							String kssjEsp = kssj.substring(0, 4) + "-"
//									+ kssj.substring(4, 6) + "-"
//									+ kssj.substring(6, 8);
//							sjValue.add(kssjEsp);
						}
						if (!Base.isNull(jssj)) {
							sjValue.add(jssj);
//							String jssjEsp = jssj.substring(0, 4) + "-"
//									+ jssj.substring(4, 6) + "-"
//									+ jssj.substring(6, 8);
//							sjValue.add(jssjEsp);
						}

						count++;
					}
				}
				// ����ʱ���ѯ
				else if ("dgsjcx".equalsIgnoreCase(lx)) {

					if (input_kssj != null && input_kssj.length > 0) {
						String kssj = input_kssj[count];

						if (!Base.isNull(kssj)) {
							sjValue.add(kssj);
						}

						count++;
					}
				}
				// ��ֵ�����ѯ
				else if ("numcx".equalsIgnoreCase(lx)) {

					if (input_ksnum != null && input_ksnum.length > 0
							&& input_jsnum != null && input_jsnum.length > 0) {
						String ksnum = input_ksnum[numcount];
						String jsnum = input_jsnum[numcount];

						if (!Base.isNull(ksnum)) {
							numValue.add(ksnum);
						}
						if (!Base.isNull(jsnum)) {
							numValue.add(jsnum);
						}

						numcount++;
					}
				}
				// �����ѯ
				else if ("djcx".equalsIgnoreCase(lx)||"zdycx".equalsIgnoreCase(lx)||"plcx".equalsIgnoreCase(lx)) {
					String methodName = "getSearch_tj_" + tj;
					String[] sT = (String[]) myClass.getMethod(methodName,
							(Class[]) null).invoke(model, (Object[]) null);

					// ��������
					if (sT != null && sT.length > 0) {
						//num += sT.length;
						//colList.add(tj);
						for (int j = 0; j < sT.length; j++) {
							inputValue.add(sT[j]);
						}
					}
				}	
			}
			
			if (!"all".equalsIgnoreCase(mhcx_lx)) {
				// ��ѯ������ģ����ѯ��
				String[] mhcx = model.getSearch_tj_mhcx();
				// ��������
				if (mhcx != null && mhcx.length > 0) {

					for (int j = 0; j < mhcx.length; j++) {
						mhValue.add(mhcx[j]);
					}
				}
			}
		}

		inputValue.addAll(mhValue);
		inputValue.addAll(sjValue);
		inputValue.addAll(numValue);
		
		// ����ѧ��
		String input_xh = model.getInput_xh();
		
		// ѧ��
		if (!Base.isNull(input_xh)) {
			inputValue.add(input_xh);
		}
		
		return inputValue.toArray(new String[]{});
	}

	/**
	 * ��������ģ�������
	 * 
	 * @param searchForm
	 * @param request
	 * 
	 * @author ΰ�����
	 */
	private void getEspecialTj(SearchForm searchForm, HttpServletRequest request) {
		// ģ��·��
		String path = searchForm.getPath();

		if ("xtwhSysz.do?method=xzzqView".equalsIgnoreCase(path)) {// ����ר��

			// �ļ������б�
			List<HashMap<String, String>> filelxList = getWhList(
					"xg_xtwh_szzqlxb", "dm", "mc", "", "", "");
			filelxList.remove(0);
			request.setAttribute("filelxList", filelxList);
		}
	}

	public static List<HashMap<String, String>> getBjPyList() {
		return bjPyList;
	}

	public static void setBjPyList(List<HashMap<String, String>> bjPyList) {
		SearchService.bjPyList = bjPyList;
	}

	public static List<HashMap<String, Object>> getBjTjList() {
		return bjTjList;
	}

	public static void setBjTjList(List<HashMap<String, Object>> bjTjList) {
		SearchService.bjTjList = bjTjList;
	}

	public static List<HashMap<String, Object>> getXyTjList() {
		return xyTjList;
	}

	public static void setXyTjList(List<HashMap<String, Object>> xyTjList) {
		SearchService.xyTjList = xyTjList;
	}

	public static List<HashMap<String, String>> getZyPyList() {
		return zyPyList;
	}

	public static void setZyPyList(List<HashMap<String, String>> zyPyList) {
		SearchService.zyPyList = zyPyList;
	}

	public static List<HashMap<String, Object>> getZyTjList() {
		return zyTjList;
	}

	public static void setZyTjList(List<HashMap<String, Object>> zyTjList) {
		SearchService.zyTjList = zyTjList;
	}
	
	/********************************************************************************************************************************/
	/**************************************************��Ԣ���������棩�߼���ѯ����**************************************************/
	/********************************************************************************************************************************/
	/**
	 * ��ù�Ԣ���������Ϣ	  
	 * @author zhang	 
	 */
	public List<HashMap<String, String>> getGyglInfo_Third(String lx, String[] xqdm,
			String[] yqdm, String[] lddm, String[] ch, String gyglyQx,
			String userName, String userDep) {

		List<HashMap<String, String>> list = null;

		String tableName = "";
		StringBuilder query = new StringBuilder(" where 1 = 1 ");
		ArrayList<String> inPutList = new ArrayList<String>();
		String[] colList = null;

		// ԰��
		if ("yq".equalsIgnoreCase(lx)) {

			tableName = "yqdmb";

			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from sslddmb a,xg_gygl_ldglb b ");
				query.append("where yqdmb.yqdm = a.yqdm and a.lddm =b.lddm and b.yhm='"+userName+"') ");
			}
			query.append(" order by yqdm ");
			
			colList = new String[] { "yqdm", "yqmc" };

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,
					"");
		}
		// ¥��
		else if ("ld".equalsIgnoreCase(lx)) {

			tableName = "xg_gygl_new_ldxxb";

			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			if (yqdm != null && yqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < yqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" yqdm = ? ");
					
					inPutList.add(yqdm[i]);
				}
				query.append(" )");

			}
			
			//�û�Ȩ���޶��жϣ��û���¥������������û�¥����Ϣ
			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from xg_gygl_ldglb b ");
				query.append(" where xg_gygl_new_ldxxb.lddm = b.lddm and b.yhm='"+userName+"') ");
			}
			
			query.append(" order by lddm ");
			
			colList = new String[] { "lddm", "ldmc" };

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,"");
		}
		//���
		else if ("ch".equalsIgnoreCase(lx)) {

			String sql = "select distinct ch,chmc from view_xg_gygl_new_cwxx ";

			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			if (yqdm != null && yqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < yqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" yqdm = ? ");
					
					inPutList.add(yqdm[i]);
				}
				query.append(" )");

			}
			
			if (lddm != null && lddm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < lddm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" lddm = ? ");
					
					inPutList.add(lddm[i]);
				}
				query.append(" )");

			}
			
			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from xg_gygl_ldglb b ");
				query.append(" where view_xg_gygl_new_cwxx.lddm = b.lddm and b.yhm='"+userName+"') ");
			}
			query.append(" order by to_number(ch) ");
			
			sql+=query;
			
			colList = new String[] { "ch", "chmc" };

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,
					sql);
		}		
		//���Һ�
		else if ("qsh".equalsIgnoreCase(lx)) {

			String sql = "select distinct qsh,ch from view_xg_gygl_new_cwxx ";
			
			//У��
			if (xqdm != null && xqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < xqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" xqdm = ? ");
					
					inPutList.add(xqdm[i]);
				}
				query.append(" )");
			}

			//԰��
			if (yqdm != null && yqdm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < yqdm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" yqdm = ? ");
					
					inPutList.add(yqdm[i]);
				}
				query.append(" )");

			}
			
			//	¥��
			if (lddm != null && lddm.length > 0) {
				query.append(" and (");
				for (int i = 0; i < lddm.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" lddm = ? ");
					
					inPutList.add(lddm[i]);
				}
				query.append(" )");

			}
			
			// ���
			if (ch != null && ch.length > 0) {
				query.append(" and (");
				for (int i = 0; i < ch.length; i++) {
					if (i != 0) {
						query.append(" or ");
					}
					query.append(" ch = ? ");

					inPutList.add(ch[i]);
				}
				query.append(" )");

			}
			
			if("yes".equalsIgnoreCase(gyglyQx)){
				query.append(" and exists(select 1 from xg_gygl_ldglb b ");
				query.append(" where view_xg_gygl_new_cwxx.lddm = b.lddm and b.yhm='"+userName+"') ");
			}
			
			query.append(" order by to_number(ch),qsh ");
			
			sql+=query;
			
			colList = new String[] { "qsh"};

			list = getRsList(tableName, query.toString(), inPutList.toArray(new String[]{}), colList,
					sql);

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);

				String qsh = map.get("qsh");
				String qshpy = CnToEnUtil.getFirstLetter(qsh);

				list.get(i).put("qshpy", qshpy.toUpperCase());
				list.get(i).put("qshdm", qsh);
				list.get(i).put("qshmc", qsh);
			}
		}
		return list;
	}
	
	/**
	 * ���������Ϣ������ƴ����
	 * @author zhangh	 
	 */
	public List<HashMap<String, String>> getQsInfoByPy_Third(String py,
			String[] xqdm, String[] yqdm, String[] lddm, String[] ch,
			String userStatus, String userName, String userDep) {

		// ��������б�
		List<HashMap<String, String>> qshList = dao.getQsList_Third(xqdm, yqdm, lddm,
				ch, userStatus, userName, userDep);

		for (int i = 0; i < qshList.size(); i++) {

			HashMap<String, String> map = qshList.get(i);

			String qsh = map.get("qsh");
			String qshpy = CnToEnUtil.getFirstLetter(qsh);

			qshList.get(i).put("qshpy", qshpy.toUpperCase());
		}

		List<HashMap<String, String>> qsList = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < qshList.size(); i++) {

			HashMap<String, String> map = qshList.get(i);

			String qsh = map.get("qsh");
			String qshpy = map.get("qshpy");

			if (qshpy.equalsIgnoreCase(py)) {

				HashMap<String, String> newQsMap = new HashMap<String, String>();
				newQsMap.put("qshdm", qsh);
				newQsMap.put("qshmc", qsh);

				qsList.add(newQsMap);

			}
		}

		return qsList;
	}
	
	/** ***************************************************************************************************************************** */
	/** ************************************************Ϊ���65535���⣬�߼���ѯ�ع����Ż�************************************************* */
	/** ***************************************************************************************************************************** */

	/**
	 * ��ʼ���б�¥������ţ����Һţ�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void setLdChQshList(SearchForm searchForm, User user)
			throws Exception {

		// У���б�
		List<HashMap<String, Object>> xqdmTjList = getXqdmList(user);
		searchForm.setXqdmTjList(xqdmTjList);
		
		// ԰���б�
		List<HashMap<String, Object>> yqdmTjList = getYqdmList(null,user);
		searchForm.setYqdmTjList(yqdmTjList);
		
		// ¥���б�
		List<HashMap<String, Object>> ldList = getLdList(null, null,user);
		searchForm.setLdTjList(ldList);

		// ����б�
		List<HashMap<String, Object>> chList = getChList(null, null, null);
		searchForm.setChTjList(chList);

		// �����б�
		List<HashMap<String, Object>> qsList = getQsList(null, null, null, null);
		searchForm.setQshTjList(qsList);
	}
	
	/**
	 * ��ʼ���б��ڹ����ţ�
	 * @author yeyipin
	 * @param searchForm
	 * @param user
	 * @throws Exception
	 */
	public void setQgbmList(SearchForm searchForm, User user) throws Exception{
		List<HashMap<String, Object>> qgbmTjList = getQgbmList(user);
		searchForm.setQgbmTjList(qgbmTjList);
	}
	
	/**
	 * ��ʼ���б�Υ�����
	 * @param searchForm
	 */
	public void setWjlbList(SearchForm searchForm){
		//Υ�ʹ���
		List<HashMap<String, Object>> gyjllbdldmTjList = getGyjllbdldmList();
		searchForm.setGyjllbdldmTjList(gyjllbdldmTjList);
		
		//Υ�����
		List<HashMap<String, Object>> gyjllbdmTjList = getGyjllbdmList(null);
		searchForm.setGyjllbdmTjList(gyjllbdmTjList);
	}
	
	/**
	 * ���У������
	 * 
	 * @author zhanghui
	 * @date   20120301
	 */
	private List<HashMap<String, Object>> getXqdmList(User user){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select DISTINCT a.dm,a.xqmc mc from dm_zju_xq a left join xg_gygl_new_ldxxb b on a.dm = b.xqdm where 1=1 ");
		if("yes".equalsIgnoreCase(user.getGyglyQx())){//�û�Ϊ��Ԣ����Ա������¥��
			sql.append(" and b.lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')");
		}
		sql.append(" order by a.dm ");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		List<HashMap<String, Object>> xqdmTjList = new ArrayList<HashMap<String, Object>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				xqdmTjList.add(map);
			}
		}
		return xqdmTjList;
	}
	
	
	/**
	 * ���԰������
	 * 
	 * @author zhanghui
	 * @date   20120301
	 */
	public List<HashMap<String, Object>> getYqdmList(String[] xq,User user){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select DISTINCT a.yqdm dm,yqmc mc from zxbz_ssyqdm a left join xg_gygl_new_ldxxb b on a.yqdm = b.yqdm where 1=1 ");
		if("yes".equalsIgnoreCase(user.getGyglyQx())){//�û�Ϊ��Ԣ����Ա������¥��
			sql.append(" and b.lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')");
		}
		
		// У���ǿ�
		if (xq != null && xq.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < xq.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.xqdm = '" + xq[i] + "' ");
			}
			sql.append(" )");
		}
		
		sql.append(" order by dm ");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		List<HashMap<String, Object>> yqdmTjList = new ArrayList<HashMap<String, Object>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				yqdmTjList.add(map);
			}
		}
		return yqdmTjList;
	}	
	
	/**
	 * ���¥���б�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, Object>> getLdList(String[] xq, String[] yq,User user) {

		DAO dao = DAO.getInstance();
		
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct t.lddm dm,ldmc mc ");
		sql.append("from xg_gygl_new_ldxxb t ");
		sql.append("where 1 = 1 ");
		if("yes".equalsIgnoreCase(user.getGyglyQx())){//�û�Ϊ��Ԣ����Ա������¥��
			sql.append(" and lddm in(select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"')");
		}
		// У���ǿ�
		if (xq != null && xq.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < xq.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" xqdm = '" + xq[i] + "' ");
			}
			sql.append(" )");
		}
		
		// ԰���ǿ�
		if (yq != null && yq.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < yq.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" yqdm = '" + yq[i] + "' ");
			}
			sql.append(" )");
		}
		
		sql.append("order by t.lddm ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		List<HashMap<String, Object>> ldList = new ArrayList<HashMap<String, Object>>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				ldList.add(map);
			}
		}

		return ldList;
	}
	
	
	
	/** 
	 * @����:������Ŀģ�����ȡ�üƷ���Ŀ��Ϣ
	 * @���ߣ�cp[���ţ�1352]
	 * @���ڣ�2017-1-4 ����03:57:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmmkdm
	 * @return
	 * List<HashMap<String,Object>> �������� 
	 * @throws 
	 */
	protected List<HashMap<String, Object>> getJfxmList(String[] xmmkdm) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct t.jfxmdm dm,jfxmmc mc from XG_ZJLY_ZHSZF_JFXMB t  ");
		sql.append("where 1 = 1 ");
		if (xmmkdm != null && xmmkdm.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < xmmkdm.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" xmmkdm = '" + xmmkdm[i] + "' ");
			}
			sql.append(" )");
		}
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });
		List<HashMap<String, Object>> jfxmList = new ArrayList<HashMap<String, Object>>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				jfxmList.add(map);
			}
		}
		return jfxmList;
	}
	/**
	 * ��ò���б�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, Object>> getChList(String[] xq, String[] yq,
			String[] ld) {

		DAO dao = DAO.getInstance();
		
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct t.ch dm,chmc mc ");
		sql.append("from view_xg_gygl_new_cwxx t ");
		sql.append("where 1 = 1 ");
		
		// У���ǿ�
		if (xq != null && xq.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < xq.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" xqdm = '" + xq[i] + "' ");
			}
			sql.append(" )");
		}
		
		// ԰���ǿ�
		if (yq != null && yq.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < yq.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" yqdm = '" + yq[i] + "' ");
			}
			sql.append(" )");
		}
		
		// ¥���ǿ�
		if (ld != null && ld.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < ld.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" lddm = '" + ld[i] + "' ");
			}
			sql.append(" )");
		}
		sql.append("order by to_number(ch) ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });
		
		List<HashMap<String, Object>> chList = new ArrayList<HashMap<String, Object>>();

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				chList.add(map);
			}
		}
		return chList;
	}
			
	/**
	 * ��������б���ƴ����
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, Object>> getQsList(String[] xq, String[] yq,
			String[] ld, String[] ch) {
		
		List<HashMap<String, Object>> gyglQshList = new ArrayList<HashMap<String, Object>>();

		DAO dao = DAO.getInstance();

		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct qsh from view_xg_gygl_new_qsxx t ");
		sql.append("where 1 = 1 ");
	
		// У���ǿ�
		if (xq != null && xq.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < xq.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" xqdm = '" + xq[i] + "' ");
			}
			sql.append(" )");
		}
		
		// ԰���ǿ�
		if (yq != null && yq.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < yq.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" yqdm = '" + yq[i] + "' ");
			}
			sql.append(" )");
		}
		
		// ¥���ǿ�
		if (ld != null && ld.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < ld.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" lddm = '" + ld[i] + "' ");
			}
			sql.append(" )");
		}
		// ��ŷǿ�
		if (ch != null && ch.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < ch.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" ch = '" + ch[i] + "' ");
			}
			sql.append(" )");
		}
		sql.append(" order by qsh ");

		List<HashMap<String, String>> qshList = dao.getList(sql.toString(),
				new String[] {}, new String[] { "qsh" });

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> qsList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < qshList.size(); j++) {

				HashMap<String, String> map = qshList.get(j);

				String qsh = map.get("qsh");
				String qshpy = CnToEnUtil.getFirstLetter(qsh);

				if (py.equalsIgnoreCase(qshpy)) {

					HashMap<String, String> newQsh = new HashMap<String, String>();

					if (qsList.size() < 3) {

						newQsh.put("qshdm", qsh);
						newQsh.put("qshmc", qsh);

						qsList.add(newQsh);

					} else {
						newQsh.put("qshdm", "qsh_" + qshpy);
						newQsh.put("qshmc", "����");

						qsList.add(newQsh);

						break;
					}

				}
			}

			HashMap<String, Object> qsMap = new HashMap<String, Object>();
			qsMap.put("py", py);
			qsMap.put("qsList", qsList);

			gyglQshList.add(qsMap);
		}
		return gyglQshList;
	}
	
	/**
	 * ����ڹ������б�
	 * yeyipin
	 * @param user
	 * @return
	 */
	private List<HashMap<String,Object>>getQgbmList(User user){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select bmdm dm,bmmc mc from view_xg_qgzx_yrdwdmb where bmmc<>'δȷ��' ");
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//��������ڹ�����Ա
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			sql.append(" and bmdm = '"+user.getUserDep()+"' ");
		}
		sql.append(" order by dm ");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		List<HashMap<String, Object>> qgbmTjList = new ArrayList<HashMap<String, Object>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				qgbmTjList.add(map);
			}
		}
		return qgbmTjList;
	}
	
	/**
	 * ��ò�ѯ�������ڹ���ѧ��
	 * yeyipin
	 * @param request
	 * @return
	 */
	public String getSearchTjByQgzx(HttpServletRequest request){
		User user = getUser(request);
		String searchTj = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//��������ڹ�����Ա
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			//searchTj+=" and yrdwdm = '"+user.getUserDep()+"' ";
			searchTj+=SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		}
		return searchTj;
	}
	
	/** ***************************************************************************************************************************** */
	/** ************************************************��ѵ�����ļ�����(�š�Ӫ��������)************************************************* */
	/** ***************************************************************************************************************************** */

	/**
	 * ��ʼ���б��š�Ӫ�������ţ�
	 * 
	 * @param request
	 * @author �׽���
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void setJxjzList(SearchForm searchForm, User user,String jxid)
			throws Exception {
		List<String[]> sjidList=new ArrayList<String[]>();
		sjidList.add(new String[]{});//�Ų���
		sjidList.add(new String[]{});//Ӫ����
		sjidList.add(new String[]{});//�Ų���
		sjidList.add(new String[]{});//�Ų���
		sjidList.add(new String[]{});//�Ų���
		sjidList.add(new String[]{});//�Ų���
		
		// ���б�
		List<HashMap<String, Object>> tidTjList = getTidList(sjidList, jxid);
		searchForm.setTidTjList(tidTjList);
		// Ӫ�б�
		List<HashMap<String, Object>> yidTjList = getYidList(sjidList, jxid);
		searchForm.setYidTjList(yidTjList);
		// ���б�
		List<HashMap<String, Object>> lidTjList = getLidList(sjidList, jxid);
		searchForm.setLidTjList(lidTjList);
		// ���б�
		List<HashMap<String, Object>> pidTjList = getPidList(sjidList, jxid);
		searchForm.setPidTjList(pidTjList);
		// ���б�
		List<HashMap<String, Object>> bidTjList = getBidList(sjidList, jxid);
		searchForm.setBidTjList(bidTjList);
		// �����б�
		List<HashMap<String, Object>> ssidTjList = getSsidList(sjidList, jxid);
		searchForm.setSsidTjList(ssidTjList);
	}
	/**
	 * @����:ʡ���� ��������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-21 ����11:48:11
	 * @�޸ļ�¼: 
	 * @param searchForm
	 * @param user
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void setQxList(SearchForm searchForm, User user)
			throws Exception {
		List<String> qxList=new ArrayList<String>();
	
		// ʡ�б�
		List<HashMap<String, Object>> shengTjList = getQxList(qxList, "sheng");
		// ���б�
		//List<HashMap<String, Object>> shiTjList = getQxList(qxList, "shi");
		// ���б�
		//List<HashMap<String, Object>> quTjList = getQxList(qxList, "qu");
		searchForm.setShengTjList(shengTjList);
		//searchForm.setShiTjList(shiTjList);
		//searchForm.setQuTjList(quTjList);
		}
	
	/**
	 * ��þ�ѵ������id
	 * 
	 * @author �׽���
	 * @param sjidList
	 * @param jxid
	 * @return
	 */
	public List<HashMap<String, Object>> getTidList(List<String[]> sjidList,String jxid){
		if(jxid == null || "".equals(jxid)){
			return null;
		}
		List<HashMap<String, String>> list = dao.getTidList(jxid, sjidList);

		List<HashMap<String, Object>> tidTjList = listToList(list);
		return tidTjList;
	}
	/**
	 * @����:ʡ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-5-21 ����11:01:10
	 * @�޸ļ�¼: 
	 * @param sjidList
	 * @param jxid
	 * @return
	 * List<HashMap<String,Object>> �������� 
	 * @throws
	 */
	public List<HashMap<String, Object>> getQxList(List<String> quList,String jb){
		if(jb == null || "".equals(jb)){
			return null;
		}
		List<HashMap<String, String>> list = dao.getQxList(quList,jb);
		Iterator<HashMap<String, String>> ite = list.iterator();
		List<HashMap<String, String>> pylist = new ArrayList<HashMap<String, String>>();
		while(ite.hasNext()){
			HashMap<String, String> map = ite.next();
			map.put("jzid", map.get("jzid"));
			map.put("jzmc", map.get("jzmc"));
			pylist.add(map);
		}
		List<HashMap<String, Object>> qxTjList = listToList(pylist);
		
		return qxTjList;
	}
	
	/**
	 * ��þ�ѵ����Ӫ id
	 * 
	 * @author �׽���
	 * @param sjidList
	 * @param jxid
	 * @return
	 */
	public List<HashMap<String, Object>> getYidList(List<String[]> sjidList,String jxid){
		if(jxid == null || "".equals(jxid)){
			return null;
		}
		List<HashMap<String, String>> list = dao.getYidList(jxid, sjidList);

		List<HashMap<String, Object>> yidTjList = listToList(list);
		return yidTjList;
	}
	
	
	/**
	 * ��þ�ѵ������id
	 * 
	 * @author �׽���
	 * @param sjidList
	 * @param jxid
	 * @return
	 */
	public List<HashMap<String, Object>> getLidList(List<String[]> sjidList,String jxid){
		if(jxid == null || "".equals(jxid)){
			return null;
		}
		List<HashMap<String, String>> list = dao.getLidList(jxid, sjidList);

		List<HashMap<String, Object>> lidTjList = listToList(list);
		return lidTjList;
	}
	
	
	/**
	 * ��þ�ѵ������id
	 * 
	 * @author �׽���
	 * @param sjidList
	 * @param jxid
	 * @return
	 */
	public List<HashMap<String, Object>> getPidList(List<String[]> sjidList,String jxid){
		if(jxid == null || "".equals(jxid)){
			return null;
		}
		List<HashMap<String, String>> list = dao.getPidList(jxid, sjidList);

		List<HashMap<String, Object>> pidTjList = listToList(list);
		return pidTjList;
	}
	
	/**
	 * 
	 * @����: ��þ�ѵ���ư�id
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-1 ����03:32:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sjidList
	 * @param jxid
	 * @return
	 * List<HashMap<String,Object>> �������� 
	 * @throws
	 */
	public List<HashMap<String, Object>> getBidList(List<String[]> sjidList,String jxid){
		if(jxid == null || "".equals(jxid)){
			return null;
		}
		List<HashMap<String, String>> list = dao.getBidList(jxid, sjidList);

		List<HashMap<String, Object>> bidTjList = listToList(list);
		return bidTjList;
	}
	
	/**
	 * 
	 * @����: ��þ�ѵ��������id
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-1 ����03:32:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sjidList
	 * @param jxid
	 * @return
	 * List<HashMap<String,Object>> �������� 
	 * @throws
	 */
	public List<HashMap<String, Object>> getSsidList(List<String[]> sjidList,String jxid){
		if(jxid == null || "".equals(jxid)){
			return null;
		}
		List<HashMap<String, String>> list = dao.getSsidList(jxid, sjidList);

		List<HashMap<String, Object>> ssidTjList = listToList(list);
		return ssidTjList;
	}
	
	/**
	 * ����ת��  ��ʽ������
	 * @param strList
	 * @return
	 */
	private List<HashMap<String, Object>> listToList(List<HashMap<String, String>> list){
		List<HashMap<String, Object>> objList = new ArrayList<HashMap<String, Object>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("jzid");
				String mc = list.get(i).get("jzmc");

				map.put("dm", dm);
				map.put("mc", mc);

				objList.add(map);
			}
		}
		return objList;
	}

	/**
	 * ���Υ�ʹ������
	 * 
	 * @author zhanghui
	 * @date   20121229
	 */
	private List<HashMap<String, Object>> getGyjllbdldmList(){
		return dao.getGyjllbdlList();
	}
	
	/**
	 * ���Υ�ʹ������
	 * 
	 * @author zhanghui
	 * @date   20121229
	 */
	public List<HashMap<String, Object>> getGyjllbdmList(String[] gyjllbdldm){
		return dao.getGyjllbList(gyjllbdldm);
	}
	
	/**
	 * 
	 * @����: ���ȫѧԺ�б�(����ƴ��)
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-24 ����03:16:21
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,Object>> �������� 
	 * @throws
	 */
	public List<HashMap<String, Object>> getXyNewList(String userStatus,
			String userName, String userDep) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// ���ȫ����ѧԺ/�����б�
		List<HashMap<String, String>> newXylist = getXyAllList(userStatus,
				userName, userDep);

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> xyList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newXylist.size(); j++) {

				HashMap<String, String> map = newXylist.get(j);

				String xypy = map.get("xypy");
				String xydm = map.get("xydm");
				String xymc = map.get("xymc");

				if (py.equalsIgnoreCase(xypy)) {

					HashMap<String, String> newXy = new HashMap<String, String>();
					newXy.put("xydm", xydm);
					newXy.put("xymc", xymc);
					xyList.add(newXy);

				}
			}

			HashMap<String, Object> xyMap = new HashMap<String, Object>();
			xyMap.put("py", py);
			xyMap.put("xyList", xyList);

			list.add(xyMap);
		}

		return list;

	}
	public List<HashMap<String, Object>> getXsXyList(String userStatus,
			String userName, String userDep,boolean sfzxs) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// ���ȫ����ѧԺ/�����б�
		List<HashMap<String, String>> newXylist = getXsXyListOfXsxx(userStatus,
				userName, userDep,sfzxs);

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> xyList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newXylist.size(); j++) {

				HashMap<String, String> map = newXylist.get(j);

				String xypy = map.get("xypy");
				String xydm = map.get("xydm");
				String xymc = map.get("xymc");

				if (py.equalsIgnoreCase(xypy)) {

					HashMap<String, String> newXy = new HashMap<String, String>();
					newXy.put("xydm", xydm);
					newXy.put("xymc", xymc);
					xyList.add(newXy);

				}
			}

			HashMap<String, Object> xyMap = new HashMap<String, Object>();
			xyMap.put("py", py);
			xyMap.put("xyList", xyList);

			list.add(xyMap);
		}

		return list;

	}
	/**
	 * 
	 * @����:�����ȫѧԺ�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-24 ����03:08:45
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXyAllList(String userStatus,
			String userName, String userDep) {
		
		List<HashMap<String, String>> xyList = dao.getXyNewList(userStatus,
				userName, userDep);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < xyList.size(); i++) {

			HashMap<String, String> map = xyList.get(i);

			String xydm = map.get("xydm");
			String xymc = map.get("xymc");
			String xypy = CnToEnUtil.getFirstLetter(xymc);

			if("-".equalsIgnoreCase(xypy)){
				xypy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", xydm);
				xypy = Base.isNull(xypy) ? "-" : xypy;
				System.out.println("��Ҫ���õ���Ƨ�ֲ��ţ�" + xymc + "(" + xydm + ")");
			}
			
			HashMap<String, String> xyMap = new HashMap<String, String>();
			xyMap.put("xypy", xypy.toUpperCase());
			xyMap.put("xydm", xydm);
			xyMap.put("xymc", xymc);

			list.add(xyMap);
		}

		return list;
	}
	
	public List<HashMap<String, String>> getXsXyListOfXsxx(String userStatus,
			String userName, String userDep,boolean sfzxs) {
		
		List<HashMap<String, String>> xyList = dao.getXsXyList(userStatus,
				userName, userDep,sfzxs);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < xyList.size(); i++) {

			HashMap<String, String> map = xyList.get(i);

			String xydm = map.get("xydm");
			String xymc = map.get("xymc");
			String xypy = CnToEnUtil.getFirstLetter(xymc);

			if("-".equalsIgnoreCase(xypy)){
				xypy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", xydm);
				xypy = Base.isNull(xypy) ? "-" : xypy;
				System.out.println("��Ҫ���õ���Ƨ�ֲ��ţ�" + xymc + "(" + xydm + ")");
			}
			
			HashMap<String, String> xyMap = new HashMap<String, String>();
			xyMap.put("xypy", xypy.toUpperCase());
			xyMap.put("xydm", xydm);
			xyMap.put("xymc", xymc);

			list.add(xyMap);
		}

		return list;
	}

	
	/**
	 * 
	 * @����:�������רҵ�б�(����ƴ��)
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-24 ����02:57:36
	 * @param userZyPyList
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,Object>> �������� 
	 * @throws
	 */
	public List<HashMap<String, Object>> getZyNewList(
			List<HashMap<String, String>> userZyPyList, String userStatus,
			String userName, String userDep) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// רҵ�б���ƴ����
		List<HashMap<String, String>> newZylist = null;

		if (userZyPyList != null && userZyPyList.size() > 0) {
			newZylist = userZyPyList;
		} else {
			newZylist = getZyAllList(userStatus, userName, userDep, null);
		}

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newZylist.size(); j++) {

				HashMap<String, String> map = newZylist.get(j);

				String zypy = map.get("zypy");
				String zydm = map.get("zydm");
				String zymc = map.get("zymc");

				if (py.equalsIgnoreCase(zypy)) {

					HashMap<String, String> newZy = new HashMap<String, String>();

					if (zyList.size() < 3) {

						newZy.put("zydm", zydm);
						newZy.put("zymc", zymc);

						zyList.add(newZy);

					} else {
						newZy.put("zydm", "zy_" + zypy);
						newZy.put("zymc", "����");

						zyList.add(newZy);

						break;
					}

				}
			}

			HashMap<String, Object> zyMap = new HashMap<String, Object>();
			zyMap.put("py", py);
			zyMap.put("zyList", zyList);

			list.add(zyMap);
		}

		return list;

	}
	public List<HashMap<String, Object>> getXsZyList(
			List<HashMap<String, String>> userZyPyList, String userStatus,
			String userName, String userDep,boolean sfzxs) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// רҵ�б���ƴ����
		List<HashMap<String, String>> newZylist = null;

		if (userZyPyList != null && userZyPyList.size() > 0) {
			newZylist = userZyPyList;
		} else {
			newZylist = getXsZyList(userStatus, userName, userDep, null,sfzxs);
		}

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newZylist.size(); j++) {

				HashMap<String, String> map = newZylist.get(j);

				String zypy = map.get("zypy");
				String zydm = map.get("zydm");
				String zymc = map.get("zymc");

				if (py.equalsIgnoreCase(zypy)) {

					HashMap<String, String> newZy = new HashMap<String, String>();

					if (zyList.size() < 3) {

						newZy.put("zydm", zydm);
						newZy.put("zymc", zymc);

						zyList.add(newZy);

					} else {
						newZy.put("zydm", "zy_" + zypy);
						newZy.put("zymc", "����");

						zyList.add(newZy);

						break;
					}

				}
			}

			HashMap<String, Object> zyMap = new HashMap<String, Object>();
			zyMap.put("py", py);
			zyMap.put("zyList", zyList);

			list.add(zyMap);
		}

		return list;

	}


	/**
	 * 
	 * @����:�����ȫרҵ�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-24 ����03:10:54
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @param nj
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZyAllList(String userStatus,
			String userName, String userDep, String[] nj) {

		List<HashMap<String, String>> zyList = dao.getZyNewList(userStatus,
				userName, userDep, nj);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < zyList.size(); i++) {

			HashMap<String, String> map = zyList.get(i);

			String xydm = map.get("xydm");
			String xymc = map.get("xymc");
			String zydm = map.get("zydm");
			String zymc = map.get("zymc");
			String zypy = CnToEnUtil.getFirstLetter(zymc);
			
			if("-".equalsIgnoreCase(zypy)){
				zypy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", zydm);
				zypy = Base.isNull(zypy) ? "-" : zypy;
				System.out.println("��Ҫ���õ���Ƨ�ֲ��ţ�" + zymc + "(" + zydm + ")");
			}
			
			HashMap<String, String> zyMap = new HashMap<String, String>();
			zyMap.put("zypy", zypy.toUpperCase());
			zyMap.put("xydm", xydm);
			zyMap.put("xymc", xymc);
			zyMap.put("zydm", zydm);
			zyMap.put("zymc", zymc);

			list.add(zyMap);
		}

		return list;
	}
	public List<HashMap<String, String>> getXsZyList(String userStatus,
			String userName, String userDep, String[] nj,boolean sfzxs) {

		List<HashMap<String, String>> zyList = dao.getXsZyList(userStatus,
				userName, userDep, nj,sfzxs);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < zyList.size(); i++) {

			HashMap<String, String> map = zyList.get(i);

			String xydm = map.get("xydm");
			String xymc = map.get("xymc");
			String zydm = map.get("zydm");
			String zymc = map.get("zymc");
			String zypy = CnToEnUtil.getFirstLetter(zymc);
			
			if("-".equalsIgnoreCase(zypy)){
				zypy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", zydm);
				zypy = Base.isNull(zypy) ? "-" : zypy;
				System.out.println("��Ҫ���õ���Ƨ�ֲ��ţ�" + zymc + "(" + zydm + ")");
			}
			
			HashMap<String, String> zyMap = new HashMap<String, String>();
			zyMap.put("zypy", zypy.toUpperCase());
			zyMap.put("xydm", xydm);
			zyMap.put("xymc", xymc);
			zyMap.put("zydm", zydm);
			zyMap.put("zymc", zymc);

			list.add(zyMap);
		}

		return list;
	}

	/**
	 * 
	 * @����:���ȫ���༶�б�(����ƴ��)
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-24 ����03:07:31
	 * @param userBjPyList
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,Object>> �������� 
	 * @throws
	 */
	public List<HashMap<String, Object>> getBjNewList(
			List<HashMap<String, String>> userBjPyList, String userStatus,
			String userName, String userDep) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// �༶�б���ƴ����
		List<HashMap<String, String>> newBjlist = null;

		if (userBjPyList != null && userBjPyList.size() > 0) {
			newBjlist = userBjPyList;
		} else {
			newBjlist = getBjAllList(userStatus, userName, userDep);
		}

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newBjlist.size(); j++) {

				HashMap<String, String> map = newBjlist.get(j);

				String bjpy = map.get("bjpy");
				String bjdm = map.get("bjdm");
				String bjmc = map.get("bjmc");

				if (py.equalsIgnoreCase(bjpy)) {

					HashMap<String, String> newBj = new HashMap<String, String>();

					if (bjList.size() < 3) {

						newBj.put("bjdm", bjdm);
						newBj.put("bjmc", bjmc);

						bjList.add(newBj);

					} else {
						newBj.put("bjdm", "bj_" + bjpy);
						newBj.put("bjmc", "����");

						bjList.add(newBj);

						break;
					}

				}
			}

			if (bjList != null && bjList.size() > 0) {
				HashMap<String, Object> bjMap = new HashMap<String, Object>();
				bjMap.put("py", py);
				bjMap.put("bjList", bjList);

				list.add(bjMap);
			}
		}

		return list;

	}
	public List<HashMap<String, Object>> getXsBjList(
			List<HashMap<String, String>> userBjPyList, String userStatus,
			String userName, String userDep,boolean sfzxs) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// �༶�б���ƴ����
		List<HashMap<String, String>> newBjlist = null;

		if (userBjPyList != null && userBjPyList.size() > 0) {
			newBjlist = userBjPyList;
		} else {
			newBjlist = getXsBjListOfXsxx(userStatus, userName, userDep,sfzxs);
		}

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newBjlist.size(); j++) {

				HashMap<String, String> map = newBjlist.get(j);

				String bjpy = map.get("bjpy");
				String bjdm = map.get("bjdm");
				String bjmc = map.get("bjmc");

				if (py.equalsIgnoreCase(bjpy)) {

					HashMap<String, String> newBj = new HashMap<String, String>();

					if (bjList.size() < 3) {

						newBj.put("bjdm", bjdm);
						newBj.put("bjmc", bjmc);

						bjList.add(newBj);

					} else {
						newBj.put("bjdm", "bj_" + bjpy);
						newBj.put("bjmc", "����");

						bjList.add(newBj);

						break;
					}

				}
			}

			if (bjList != null && bjList.size() > 0) {
				HashMap<String, Object> bjMap = new HashMap<String, Object>();
				bjMap.put("py", py);
				bjMap.put("bjList", bjList);

				list.add(bjMap);
			}
		}

		return list;

	}
	public List<HashMap<String, Object>> getZList(
			List<HashMap<String, String>> userBjPyList, String userStatus,
			String userName, String userDep) {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// �༶�б���ƴ����
		List<HashMap<String, String>> newBjlist = null;

		if (userBjPyList != null && userBjPyList.size() > 0) {
			newBjlist = userBjPyList;
		} else {
			newBjlist = getBjAllList(userStatus, userName, userDep);
		}

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < newBjlist.size(); j++) {

				HashMap<String, String> map = newBjlist.get(j);

				String bjpy = map.get("bjpy");
				String bjdm = map.get("bjdm");
				String bjmc = map.get("bjmc");

				if (py.equalsIgnoreCase(bjpy)) {

					HashMap<String, String> newBj = new HashMap<String, String>();

					if (bjList.size() < 3) {

						newBj.put("bjdm", bjdm);
						newBj.put("bjmc", bjmc);

						bjList.add(newBj);

					} else {
						newBj.put("bjdm", "bj_" + bjpy);
						newBj.put("bjmc", "����");

						bjList.add(newBj);

						break;
					}

				}
			}

			if (bjList != null && bjList.size() > 0) {
				HashMap<String, Object> bjMap = new HashMap<String, Object>();
				bjMap.put("py", py);
				bjMap.put("bjList", bjList);

				list.add(bjMap);
			}
		}

		return list;

	}

	/**
	 * 
	 * @����: �����ȫ�༶�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-24 ����03:11:57
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjAllList(String userStatus,
			String userName, String userDep) {

		List<HashMap<String, String>> bjList = dao.getBjNewList(userStatus,
				userName, userDep);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < bjList.size(); i++) {

			HashMap<String, String> map = bjList.get(i);

			String xydm = map.get("xydm");// ѧԺ����
			String xymc = map.get("xymc");// ѧԺ����
			String zydm = map.get("zydm");// רҵ����
			String zymc = map.get("zymc");// רҵ����
			String nj = map.get("nj");// �꼶
			String bjdm = map.get("bjdm");// �༶����
			String bjmc = map.get("bjmc");// �༶����
			String bjpy = CnToEnUtil.getFirstLetter(bjmc);// �༶ƴ��
			
			if("-".equalsIgnoreCase(bjpy)){
				bjpy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", bjdm);
				bjpy = Base.isNull(bjpy) ? "-" : bjpy;
				System.out.println("��Ҫ���õ���Ƨ�ֲ��ţ�" + bjmc + "(" + bjdm + ")");
			}
			
			HashMap<String, String> bjMap = new HashMap<String, String>();
			bjMap.put("bjpy", bjpy.toUpperCase());
			bjMap.put("xydm", xydm);
			bjMap.put("xymc", xymc);
			bjMap.put("zydm", zydm);
			bjMap.put("zymc", zymc);
			bjMap.put("nj", nj);
			bjMap.put("bjdm", bjdm);
			bjMap.put("bjmc", bjmc);

			list.add(bjMap);
		}

		return list;
	}
	public List<HashMap<String, String>> getXsBjListOfXsxx(String userStatus,
			String userName, String userDep,boolean sfzxs) {

		List<HashMap<String, String>> bjList = dao.getXsBjList(userStatus,
				userName, userDep,sfzxs);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < bjList.size(); i++) {

			HashMap<String, String> map = bjList.get(i);

			String xydm = map.get("xydm");// ѧԺ����
			String xymc = map.get("xymc");// ѧԺ����
			String zydm = map.get("zydm");// רҵ����
			String zymc = map.get("zymc");// רҵ����
			String nj = map.get("nj");// �꼶
			String bjdm = map.get("bjdm");// �༶����
			String bjmc = map.get("bjmc");// �༶����
			String bjpy = CnToEnUtil.getFirstLetter(bjmc);// �༶ƴ��
			
			if("-".equalsIgnoreCase(bjpy)){
				bjpy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", bjdm);
				bjpy = Base.isNull(bjpy) ? "-" : bjpy;
				System.out.println("��Ҫ���õ���Ƨ�ֲ��ţ�" + bjmc + "(" + bjdm + ")");
			}
			
			HashMap<String, String> bjMap = new HashMap<String, String>();
			bjMap.put("bjpy", bjpy.toUpperCase());
			bjMap.put("xydm", xydm);
			bjMap.put("xymc", xymc);
			bjMap.put("zydm", zydm);
			bjMap.put("zymc", zymc);
			bjMap.put("nj", nj);
			bjMap.put("bjdm", bjdm);
			bjMap.put("bjmc", bjmc);

			list.add(bjMap);
		}

		return list;
	}

	

	/**
	 * 
	 * @����: ���ó�ʼ���꼶ѧԺרҵ�༶[ȫ]
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-24 ����03:27:12
	 * @param searchForm
	 * @param rForm
	 * @param user
	 * @param request
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public void setNjXyZyBjNewList(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		
		// �û����
		String userStatus = "";
		// �û���
		String userName = user.getUserName();
		// �û�����
		String userDep = user.getUserDep();
		
		SearchTjByRoles rolesService = new SearchTjByRoles();
		
		 // ʹ���û���ɫ
		String userRolesApply = user.getUserRolesApply();

		if ("yes".equalsIgnoreCase(userRolesApply)) {
			// �û���ɫ
			List<HashMap<String, String>> userRolesList = rolesService
					.getUserGnmkRoles(searchForm, user);
			// �û����
			userStatus = rolesService.getUserStatus(userRolesList,user);
		} else {
			userStatus = user.getUserStatus();
		}
		// �꼶�б�
		List<HashMap<String, String>> njTjList = getNjNewTjList();	
		searchForm.setNjNewTjList(njTjList);	
		request.setAttribute("njNewTjList", njTjList);
		
		// ѧԺ�б�
		searchForm.setXyNewTjList(getXyNewList("", "", ""));
		request.setAttribute("xyNewTjList", getXyNewList("", "", ""));

		// רҵ�б�
		searchForm.setZyNewTjList(getNewZyPxList(null, "", "", "",""));
		request.setAttribute("zyNewTjList", getZyNewList(null, "", "", ""));

		// �༶�б�
		searchForm.setBjNewTjList(getNewBjPxList(null, "", "", "",""));
		request.setAttribute("bjNewTjList", getBjNewList(null, "", "", ""));
		
	}
	
	public void setNjXyZyBjZxsList(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request,boolean sfzxs) throws Exception {

		HttpSession session = request.getSession();
		
		// �û����
		String userStatus = "";
		// �û���
		String userName = user.getUserName();
		// �û�����
		String userDep = user.getUserDep();
		
		SearchTjByRoles rolesService = new SearchTjByRoles();
		
		 // ʹ���û���ɫ
		String userRolesApply = user.getUserRolesApply();

		if ("yes".equalsIgnoreCase(userRolesApply)) {
			// �û���ɫ
			List<HashMap<String, String>> userRolesList = rolesService
					.getUserGnmkRoles(searchForm, user);
			// �û����
			userStatus = rolesService.getUserStatus(userRolesList,user);
		} else {
			userStatus = user.getUserStatus();
		}
		
		
		// ѧԺ�б�
		searchForm.setZxsXyTjList(getXsXyList("", "", "",sfzxs));
		request.setAttribute("zxsXyTjList", getXsXyList("", "", "",sfzxs));

		// רҵ�б�
		searchForm.setZxsZyTjList(getXsZyList(null, "", "", "",sfzxs));
		request.setAttribute("zxsZyTjList", getXsZyList(null, "", "", "",sfzxs));

		// �༶�б�
		searchForm.setZxsBjTjList(getXsBjList(null, "", "", "",sfzxs));
		request.setAttribute("zxsBjTjList", getXsBjList(null, "", "", "",sfzxs));
		
	}
	public void setNjXyZyBjFzxsList(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request,boolean sfzxs) throws Exception {

		HttpSession session = request.getSession();
		
		// �û����
		String userStatus = "";
		// �û���
		String userName = user.getUserName();
		// �û�����
		String userDep = user.getUserDep();
		
		SearchTjByRoles rolesService = new SearchTjByRoles();
		
		 // ʹ���û���ɫ
		String userRolesApply = user.getUserRolesApply();

		if ("yes".equalsIgnoreCase(userRolesApply)) {
			// �û���ɫ
			List<HashMap<String, String>> userRolesList = rolesService
					.getUserGnmkRoles(searchForm, user);
			// �û����
			userStatus = rolesService.getUserStatus(userRolesList,user);
		} else {
			userStatus = user.getUserStatus();
		}

		// ѧԺ�б�
		searchForm.setFzxsXyTjList(getXsXyList("", "", "",sfzxs));
		request.setAttribute("fzxsXyTjList", getXsXyList("", "", "",sfzxs));

		// רҵ�б�
		searchForm.setFzxsZyTjList(getXsZyList(null, "", "", "",sfzxs));
		request.setAttribute("fzxsZyTjList", getXsZyList(null, "", "", "",sfzxs));

		// �༶�б�
		searchForm.setFzxsBjTjList(getXsBjList(null, "", "", "",sfzxs));
		request.setAttribute("fzxsBjTjList", getXsBjList(null, "", "", "",sfzxs));
	}


	/**
	 * ���רҵ��Ϣ������ѧԺ��
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getZyNewByXy(String[] nj,
			String[] xy, String userStatus, String userName, String userDep) {

		List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();

		List<HashMap<String, String>> zyPyList = getZyAllList(userStatus,
				userName, userDep, nj);

		if (xy != null && xy.length > 0) {

			for (int i = 0; i < zyPyList.size(); i++) {

				HashMap<String, String> zyMap = zyPyList.get(i);

				String zypy = zyMap.get("zypy");
				String xydm = zyMap.get("xydm");
				String xymc = zyMap.get("xymc");
				String zydm = zyMap.get("zydm");
				String zymc = zyMap.get("zymc");

				for (int j = 0; j < xy.length; j++) {

					if (xy[j].equalsIgnoreCase(xydm)) {

						HashMap<String, String> map = new HashMap<String, String>();

						map.put("zypy", zypy);
						map.put("xymc", xymc);
						map.put("zydm", zydm);
						map.put("zymc", zymc);

						zyList.add(map);
					}
				}
			}
		} else {

			for (int i = 0; i < zyPyList.size(); i++) {

				HashMap<String, String> zyMap = zyPyList.get(i);

				String zypy = zyMap.get("zypy");
				String xymc = zyMap.get("xymc");
				String zydm = zyMap.get("zydm");
				String zymc = zyMap.get("zymc");

				for (int j = 0; j < PY_BIG.length; j++) {

					if (PY_BIG[j].equalsIgnoreCase(zypy)) {

						HashMap<String, String> map = new HashMap<String, String>();

						map.put("zypy", zypy);
						map.put("xymc", xymc);
						map.put("zydm", zydm);
						map.put("zymc", zymc);

						zyList.add(map);
					}
				}
			}
		}

		return zyList;
	}


	/**
	 * ��ð༶��Ϣ������ƴ�����꼶��ѧԺ��רҵ��
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getBjNewByTj(String py, String[] nj,
			String[] xy, String[] zy, String userStatus, String userName,
			String userDep) {

		List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

		// �༶�б���ƴ����
		List<HashMap<String, String>> bjPyList = getBjAllList(userStatus,
				userName, userDep);

		for (int i = 0; i < bjPyList.size(); i++) {

			HashMap<String, String> bjMap = bjPyList.get(i);

			String bjpy = bjMap.get("bjpy");// �༶ƴ��
			String bjdm = bjMap.get("bjdm");// �༶����
			String bjmc = bjMap.get("bjmc");// �༶����

			String njdm = bjMap.get("nj");// �꼶
			String xydm = bjMap.get("xydm");// ѧԺ
			String zydm = bjMap.get("zydm");// רҵ

			// �ж�ƴ���Ƿ����
			boolean pyFlag = true;

			if (!Base.isNull(py)) {

				pyFlag = false;

				if (bjpy.equalsIgnoreCase(py)) {
					pyFlag = true;
				}
			}

			// �ж��꼶�Ƿ����
			boolean njFlag = true;

			if (nj != null && nj.length > 0) {

				njFlag = false;

				for (int j = 0; j < nj.length; j++) {
					if (njdm.equalsIgnoreCase(nj[j])) {
						njFlag = true;
						break;
					}
				}
			}

			// �ж�ѧԺ�Ƿ����
			boolean xyFlag = true;

			if (xy != null && xy.length > 0) {

				xyFlag = false;

				for (int j = 0; j < xy.length; j++) {
					if (xydm.equalsIgnoreCase(xy[j])) {
						xyFlag = true;
						break;
					}
				}
			}

			// �ж�רҵ�Ƿ����
			boolean zyFlag = true;

			if (zy != null && zy.length > 0) {

				zyFlag = false;

				for (int j = 0; j < zy.length; j++) {
					if (zydm.equalsIgnoreCase(zy[j])) {
						zyFlag = true;
						break;
					}
				}
			}

			if (pyFlag && njFlag && xyFlag && zyFlag) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("bjpy", bjpy);
				map.put("bjdm", bjdm);
				map.put("bjmc", bjmc);

				bjList.add(map);

			}
		}

		return bjList;
	}


	/**
	 * 
	 * @����: ���רҵ��Ϣ������ƴ����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-25 ����08:53:01
	 * @param py
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZyNewInfoByPy(String py,
			String userStatus, String userName, String userDep) {

		List<HashMap<String, String>> zyList = new ArrayList<HashMap<String, String>>();

		List<HashMap<String, String>> zyPyList = getZyAllList(userStatus,
				userName, userDep, null);

		for (int i = 0; i < zyPyList.size(); i++) {

			HashMap<String, String> zyMap = zyPyList.get(i);

			String zypy = zyMap.get("zypy");
			String zydm = zyMap.get("zydm");
			String zymc = zyMap.get("zymc");

			if (zypy.equalsIgnoreCase(py)) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("zydm", zydm);
				map.put("zymc", zymc);

				zyList.add(map);

			}
		}

		return zyList;
	}

	/**
	 * 
	 * @����: ��ð༶��Ϣ������ƴ�����꼶��ѧԺ��רҵ��
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-25 ����08:55:30
	 * @param py
	 * @param nj
	 * @param xy
	 * @param zy
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjNewInfoByTj(String py, String[] nj,
			String[] xy, String[] zy, String userStatus, String userName,
			String userDep) {

		List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

		// �༶�б���ƴ����
		List<HashMap<String, String>> bjPyList = getBjAllList(userStatus,
				userName, userDep);

		for (int i = 0; i < bjPyList.size(); i++) {

			HashMap<String, String> bjMap = bjPyList.get(i);

			String bjpy = bjMap.get("bjpy");// �༶ƴ��
			String bjdm = bjMap.get("bjdm");// �༶����
			String bjmc = bjMap.get("bjmc");// �༶����

			String njdm = bjMap.get("nj");// �꼶
			String xydm = bjMap.get("xydm");// ѧԺ
			String zydm = bjMap.get("zydm");// רҵ

			// �ж�ƴ���Ƿ����
			boolean pyFlag = true;

			if (!Base.isNull(py)) {

				pyFlag = false;

				if (bjpy.equalsIgnoreCase(py)) {
					pyFlag = true;
				}
			}

			// �ж��꼶�Ƿ����
			boolean njFlag = true;

			if (nj != null && nj.length > 0) {

				njFlag = false;

				for (int j = 0; j < nj.length; j++) {
					if (njdm.equalsIgnoreCase(nj[j])) {
						njFlag = true;
						break;
					}
				}
			}

			// �ж�ѧԺ�Ƿ����
			boolean xyFlag = true;

			if (xy != null && xy.length > 0) {

				xyFlag = false;

				for (int j = 0; j < xy.length; j++) {
					if (xydm.equalsIgnoreCase(xy[j])) {
						xyFlag = true;
						break;
					}
				}
			}

			// �ж�רҵ�Ƿ����
			boolean zyFlag = true;

			if (zy != null && zy.length > 0) {

				zyFlag = false;

				for (int j = 0; j < zy.length; j++) {
					if (zydm.equalsIgnoreCase(zy[j])) {
						zyFlag = true;
						break;
					}
				}
			}

			if (pyFlag && njFlag && xyFlag && zyFlag) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("bjpy", bjpy);
				map.put("bjdm", bjdm);
				map.put("bjmc", bjmc);

				bjList.add(map);

			}
		}

		return bjList;
	}
	
	/**
	 * 
	 * @����: �꼶�б�[ȫ]
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-25 ����09:40:43
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getNjNewTjList() {

		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct(nj) from BKS_BJDM order by nj ");
		return DAO.getInstance().getListNotOut(sql.toString(),
				new String[] {});
	}
	
	public static void setNjNewTjList(List<HashMap<String, String>> njNewTjList) {
		SearchService.njNewTjList = njNewTjList;
	}

	public static List<HashMap<String, Object>> getXyNewTjList() {
		return xyNewTjList;
	}

	public static void setXyNewTjList(List<HashMap<String, Object>> xyNewTjList) {
		SearchService.xyNewTjList = xyNewTjList;
	}

	public static List<HashMap<String, Object>> getZyNewTjList() {
		return zyNewTjList;
	}

	public static void setZyNewTjList(List<HashMap<String, Object>> zyNewTjList) {
		SearchService.zyNewTjList = zyNewTjList;
	}

	public static List<HashMap<String, Object>> getBjNewTjList() {
		return bjNewTjList;
	}

	public static void setBjNewTjList(List<HashMap<String, Object>> bjNewTjList) {
		SearchService.bjNewTjList = bjNewTjList;
	}
	
	/**
	 * 
	 * @����:Ȩ�޷�Χ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-1-5 ����11:27:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param xtgwid
	 * @param qxfw
	 * @param bmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getQxfw(User user, String xtgwid,
			String qxfw, String bmdm, String searchTjByUser) { 

		// �û���
		String userName = user.getUserName();
		// �û����ڲ���
		String userDep = user.getUserDep();
		
		StringBuilder query = new StringBuilder();
		
		query.append(" and (");
		query.append(" case when "+qxfw+" like '%'||"+xtgwid+"||'%' then ");
		query.append(" (case when "+xtgwid+" in (select spgw from xg_xtwh_spgwyh where spyh='"+userName+"') ");
		query.append(" and "+bmdm+" = '"+userDep+"'");
		query.append(" then 'true' else 'false' end) else ");
		query.append(" (case when "+xtgwid+" in (select spgw from xg_xtwh_spgwyh where spyh='"+userName+"') ");
		query.append( searchTjByUser+"  then 'true' else 'false' end)  end ");
		query.append(" ) = 'true'"); 
		
		return query.toString();
	}
	
	
	/**
	 * 
	 * @����:��ʼ����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-4-15 ����10:16:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lxdm
	 * @param xzdm
	 * @return
	 * List<HashMap<String,Object>> �������� 
	 * @throws
	 */
	public List<HashMap<String, Object>> getPjxmList(String[] lxdm, String[] xzdm) {

		DAO dao = DAO.getInstance();
		
		List<HashMap<String, Object>> pjpyPjxmList = new ArrayList<HashMap<String, Object>>();
		
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct xmmc dm,xmmc mc from (select xmmc,xzdm,LXDM FROM xg_pjpy_new_pjjgb ");
		sql.append("union SELECT xmmc,xzdm,lxdm FROM (select xmmc,xzdm,lxdm from xg_pjpy_new_xmsq a ");
		sql.append("left join xg_pjpy_new_pjxmb b on a.xmdm = b.xmdm)) where xmmc is not null and 1 = 1 ");
		
		//���ʹ���ǿ�
		if(lxdm != null && lxdm.length >0){
			sql.append(" and lxdm in ('");
			for (int i = 0; i < lxdm.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(lxdm[i]);
			}
			sql.append("')");
		}
		
		//���ʴ���ǿ�
		if(xzdm != null && xzdm.length >0){
			sql.append(" and xzdm in ('");
			for (int i = 0; i < xzdm.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(xzdm[i]);
			}
			sql.append("')");
		}
		
		sql.append("order by xmmc ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });
		
		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> pjxmList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < list.size(); j++) {

				HashMap<String, String> map = list.get(j);
				
				String dm = map.get("dm");
				String mc = map.get("mc");
				String mcPy = CnToEnUtil.getFirstLetter(mc);

				if (py.equalsIgnoreCase(mcPy)) {

					HashMap<String, String> newPjxm = new HashMap<String, String>();

					if (pjxmList.size() < 3) {

						newPjxm.put("dm", dm);
						newPjxm.put("mc", mc);

						pjxmList.add(newPjxm);

					} else {
						newPjxm.put("dm", "pjxm_" + mcPy);
						newPjxm.put("mc", "����");

						pjxmList.add(newPjxm);

						break;
					}

				}
			}

			HashMap<String, Object> pjxmMap = new HashMap<String, Object>();
			pjxmMap.put("py", py);
			pjxmMap.put("pjxmList", pjxmList);

			pjpyPjxmList.add(pjxmMap);
		}
			

		return pjpyPjxmList;
	}
	
	public List<HashMap<String, Object>> getXmlxList(String xzdm){
		DAO dao = DAO.getInstance();

		String table = "XG_PJPY_NEW_JXJXMLX";
		if("2".equals(xzdm)){
			table = "xg_pjpy_new_xmlx";
		}

		StringBuilder sql = new StringBuilder();
		sql.append("select xmlxdm dm,xmlxmc mc from ");
		sql.append(table);
		sql.append(" order by xmlxdm ");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		List<HashMap<String, Object>> lxdmTjList = new ArrayList<HashMap<String, Object>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				lxdmTjList.add(map);
			}
		}
		return lxdmTjList;
	}
	
	
	public List<HashMap<String, Object>> getXmxzList(){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select xmxzdm dm,xmxzmc mc from xg_pjpy_new_xmxz order by xmxzdm");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		List<HashMap<String, Object>> xmxzTjList = new ArrayList<HashMap<String, Object>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();

				String dm = list.get(i).get("dm");
				String mc = list.get(i).get("mc");

				map.put("dm", dm);
				map.put("mc", mc);

				xmxzTjList.add(map);
			}
		}
		return xmxzTjList;
	}
	
	
	/**
	 * �����Ŀ��Ϣ���������ͣ����ʣ�
	 * 
	 * @author 
	 * 
	 */
	public List<HashMap<String, String>> getXmInfoByTj(String py, String[] xmlx,
			String[] xmxz) {

		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String, String>>();

		// ��Ŀ�б���ƴ����
		List<HashMap<String, String>> xmPyList = getNewXmList(xmlx, xmxz);

		for (int i = 0; i < xmPyList.size(); i++) {

			HashMap<String, String> xmMap = xmPyList.get(i);

			String xmpy = xmMap.get("xmpy");// ��Ŀƴ��
			String xmdm = xmMap.get("xmdm");// ��Ŀ����
			String xmmc = xmMap.get("xmmc");// ��Ŀ����


			// �ж�ƴ���Ƿ����
			boolean pyFlag = true;

			if (!Base.isNull(py)) {

				pyFlag = false;

				if (xmpy.equalsIgnoreCase(py)) {
					pyFlag = true;
				}
			}

			if (pyFlag ) {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("xmpy", xmpy);
				map.put("xmdm", xmdm);
				map.put("xmmc", xmmc);

				xmList.add(map);

			}
		}

		return xmList;
	}
	
	
	/**
	 * �����������Ŀ�б�
	 * 
	 * @author
	 * 
	 */
	public List<HashMap<String, String>> getNewXmList(String[] xmlx, String[] xmxz) {

		List<HashMap<String, String>> xmList = dao.getPjxmList(xmlx,xmxz);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < xmList.size(); i++) {

			HashMap<String, String> map = xmList.get(i);
			
			String xmdm = map.get("xmdm");	//��Ŀ����
			String xmmc = map.get("xmmc");	//��Ŀ����
			String xmpy = CnToEnUtil.getFirstLetter(xmmc);// �༶ƴ��
			
			if("-".equalsIgnoreCase(xmpy)){
				xmpy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", xmdm);
				xmpy = Base.isNull(xmpy) ? "-" : xmpy;
				System.out.println("��Ҫ���õ���Ƨ�ֲ��ţ�" + xmmc + "(" + xmdm + ")");
			}
			
			HashMap<String, String> xmMap = new HashMap<String, String>();
			xmMap.put("xmpy", xmpy.toUpperCase());
			xmMap.put("xmdm", xmdm);
			xmMap.put("xmmc", xmmc);

			list.add(xmMap);
		}

		return list;
	}
	
	/**
	 * 
	 * @����: ��ȡ��Ԣ����ԱȨ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-16 ����01:48:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gyglyQx
	 * @param username
	 * @param path
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getGyglyQx(String gyglyQx,String username,String path) {
		String gygly_path = XMLReader.getFlowControl("gygl", "gygly_path");
		if(path!=null && gygly_path.contains(path) && "yes".equalsIgnoreCase(gyglyQx)){
			return " and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+username+"')";
		}else{
			return "";
		}
	}
	
	/**
	 * 
	 * @����: ������ѯʦȨ��ͨ�÷���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-28 ����02:28:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param username
	 * @return
	 * String �������� 
	 * @throws
	 */
	public static String getSearchQxfwForXlzxs(String username){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_xlzx_glyb where zgh = ?");
		String cnt = DAO.getInstance().getOneRs(sql.toString(),new String[]{username}, "cnt");
		if("0".equals(cnt)){
			return " and zgh = '"+username+"' ";
		}else{
			return " ";
		}
	}
	
	/**
	 * 
	 * @����:��ʼ����Ժ�б�
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2018-3-5 ����02:16:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param searchForm
	 * @param user
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void setSymcList(SearchForm searchForm, User user) throws Exception{
		List<HashMap<String, String>> syTjList = getSyList(user);
		searchForm.setSyTjList(syTjList);
	}
	
	private List<HashMap<String, String>> getSyList(User user){
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct a.sydm dm,a.symc mc from xg_xtwh_sydmb a left join fdyxxb b on a.sydm = b.sydm where 1=1 ");
		if("yes".equalsIgnoreCase(user.getSyQx())){
			sql.append(" and b.zgh = '"+user.getUserName()+"'");
		}
		sql.append(" order by a.sydm ");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		return list;
	}

	public void setZybjList(SearchForm searchForm, User user) throws Exception{
		List<HashMap<String,Object>> list = getZybjList(user);
		searchForm.setZybjTjList(list);
	}

	private List<HashMap<String, Object>> getZybjList(User user){

		List<HashMap<String,String>> zybjList = getZybjPyList(user.getUserStatus(),user.getUserName(),user.getUserDep());

		List<HashMap<String,Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < zybjList.size(); j++) {

				HashMap<String, String> map = zybjList.get(j);

				String bjpy = map.get("bjpy");
				String bjdm = map.get("bjdm");
				String bjmc = map.get("bjmc");

				if (py.equalsIgnoreCase(bjpy)) {

					HashMap<String, String> newBj = new HashMap<String, String>();

					if (bjList.size() < 3) {

						newBj.put("bjdm", bjdm);
						newBj.put("bjmc", bjmc);

						bjList.add(newBj);

					} else {
						newBj.put("bjdm", "bj_" + bjpy);
						newBj.put("bjmc", "����");

						bjList.add(newBj);

						break;
					}

				}
			}

			if (bjList != null && bjList.size() > 0) {
				HashMap<String, Object> bjMap = new HashMap<String, Object>();
				bjMap.put("zybjpy", py);
				bjMap.put("zybjList", bjList);

				list.add(bjMap);
			}
		}
		return list;
	}

	private List<HashMap<String,String>> getZybjPyList(String userStatus,String userName,String userDep){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append("select distinct xydm,xymc,zydm,zymc,nj,bjdm,bjmc from view_njxyzybj_bzr a where 1 = 1 ");
		sql.append(" and (");
		if ("bzr".equalsIgnoreCase(userStatus)) {
			// ������
			sql.append(" exists(select 1 from bzrbbb b ");
			sql.append(" where a.bjdm = b.bjdm and b.zgh = '"+userName+"' ) ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			sql.append(" a.xydm = '" + userDep + "' ");
		} else {
			sql.append(" 1=1 ");
		}
		sql.append(" ) ");
		List<HashMap<String,String>> zybjList = dao.getList(sql.toString(),new String[]{},new String[] { "xydm", "xymc", "zydm", "zymc", "nj", "bjdm",
				"bjmc" });

		for (int i = 0; i < zybjList.size(); i++) {

			HashMap<String, String> map = zybjList.get(i);

			String bjdm = map.get("bjdm");// �༶����
			String bjmc = map.get("bjmc");// �༶����
			String bjpy = CnToEnUtil.getFirstLetter(bjmc);// �༶ƴ��

			if("-".equalsIgnoreCase(bjpy)){
				bjpy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", bjdm);
				bjpy = Base.isNull(bjpy) ? "-" : bjpy;
				System.out.println("��Ҫ���õ���Ƨ�ֲ��ţ�" + bjmc + "(" + bjdm + ")");
			}
			zybjList.get(i).put("bjpy", bjpy.toUpperCase());
		}
		return zybjList;
	}

	/**
	 * ��ȡ�����༶
	 * @param userStatus
	 * @param userName
	 * @param userDep
	 * @return
	 */
	private List<HashMap<String,String>> getXzbjPyList(String userStatus,String userName,String userDep){
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append("select distinct * from (select a.sydm,b.symc,a.bjdm,c.bjmc,d.zgh,d.xm from  XG_XTWH_SYBJGLB a ");
		sql.append(" left join XG_XTWH_SYDMB b on a.sydm = b.sydm left join T_BJDMB c ");
		sql.append(" on a.bjdm = c.bjdm left join VIEW_FDYBBJ d on a.bjdm = d.bjdm where c.bjmc is not null)  where 1=1  ");
//		if ("fdy".equalsIgnoreCase(userStatus)) {
//			// ����Ա
//			sql.append(" and b.zgh = '"+userName+"' ) ");
//		} else if ("sy".equalsIgnoreCase(userStatus)) {
//			sql.append(" and a.sydm = '" + userDep + "' ");
//		} else {
//			sql.append("  ");
//		}
		List<HashMap<String,String>> xzbjList = dao.getList(sql.toString(),new String[]{},new String[] { "sydm", "symc", "bjdm", "bjmc", "zgh", "xm" });

		for (int i = 0; i < xzbjList.size(); i++) {

			HashMap<String, String> map = xzbjList.get(i);

			String bjdm = map.get("bjdm");// �༶����
			String bjmc = map.get("bjmc");// �༶����
			String bjpy = CnToEnUtil.getFirstLetter(bjmc);// �༶ƴ��

			if("-".equalsIgnoreCase(bjpy)){
				bjpy = dao.getOneValue("xg_comm_bmmcspzb", "py", "bmdm", bjdm);
				bjpy = Base.isNull(bjpy) ? "-" : bjpy;
				System.out.println("��Ҫ���õ���Ƨ�ֲ��ţ�" + bjmc + "(" + bjdm + ")");
			}
			xzbjList.get(i).put("bjpy", bjpy.toUpperCase());
		}
		return xzbjList;
	}
}