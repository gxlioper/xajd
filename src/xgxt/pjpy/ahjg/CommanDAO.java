package xgxt.pjpy.ahjg;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Fdypd;
import xgxt.utils.GetTime;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description: ���ս�����ҵѧԺ��������ͨ��DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ����
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2008-06-16
 * </p>
 */
public class CommanDAO {

	/**
	 * ��ȡ
	 * 
	 * @param xjbjQryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql1(XjBjQryModel xjbjQryModel)
			throws Exception {
		StringBuffer whereSql = new StringBuffer();

		return whereSql;
	}
	
	/**
	 * �û������½   
	 * @param yhm    �û���
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean ssoLogin(String yhm, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("istysfrz", "yes");
		String userType = "";
		String userName = yhm;
		String errMsg = "";
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		
		String sql = "select szbm,xm,zdm from yhb where yhm=?";
		String[] userChk = dao.getOneRs(sql, new String[] { userName },
				new String[] { "szbm", "xm", "zdm" });
		if ((userChk != null) && (userChk.length == 3)) {
			userType = "teacher";
		} else {
			sql = "select xm,bmdm szbm,'6727' zdm from view_xsjbxx where xh=?";
			userChk = dao.getOneRs(sql, new String[] { userName },
					new String[] { "szbm", "xm", "zdm" });
			if ((userChk != null) && (userChk.length == 3)) {
				userType = "student";
			} else {
				errMsg = "�û������޸��û����޷���½ѧ��ϵͳ����ȷ�ϣ���";
				//session.setAttribute("errMsg", errMsg);// ��������
				request.setAttribute("errMsg", errMsg);// ��������
				return false;
			}
		}

		if (userType.equalsIgnoreCase("teacher")) {
			boolean isFdy = Fdypd.isFdy(userName);
			session.setAttribute("isFdyUser", isFdy);
			session.setAttribute("isFdy", isFdy);
			session.setAttribute("isBzr", Fdypd.isBzr(userName, ""));
			session.setAttribute("fdyQx", Fdypd.fdybjck(userName));
			session.setAttribute("bzrQx", Fdypd.bzrbjck(userName));
		} else {
			session.setAttribute("isFdyUser", false);
			session.setAttribute("isFdy", false);
			session.setAttribute("isBzr", false);
			session.setAttribute("fdyQx", false);
			session.setAttribute("bzrQx", false);
		}

		userChk = new String[] { userChk[0], userChk[1], userChk[2], userType };

		String xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},
				"xxmc");

		/** �洢��¼�û��Ļ�����Ϣ,�洢��session�� */
		session.setAttribute("pjzq", StandardOperation.getCollegePriseCycle());// ѧУ����������,xn
		// Ϊÿѧ����һ��;xq
		// Ϊѧ����һ��
		session.setAttribute("userOnLine", userType);// �û����ͣ�ѧ������ʦ��
		session.setAttribute("userName", userName);// ��½��
		session.setAttribute("userDep", userChk[0]);// �û�����
		session.setAttribute("userNameReal", userChk[1]);// �û�����
		session.setAttribute("xxmc", xxmc);
		session.setAttribute("xxdm", xxdm);
		session.setAttribute("LoginXn", Base.currXn);
		String gyglySql = "select count(1) num from xg_gygl_new_gyfdyb where yhm = ?";
		String num = dao.getOneRs(gyglySql, new String[] { userName }, "num");
		String gyglyQx = !Base.isNull(num) && !"0".equalsIgnoreCase(num) ? "yes"
				: "no";
		session.setAttribute("gyglyQx", gyglyQx);
		session.setAttribute("LoginXq", Base.currXq);
		session.setAttribute("LoginZc", StandardOperation.getCurrZc());
		String openType = request.getParameter("openType");
		if ("".equalsIgnoreCase(openType) || openType == null) {
			openType = "2";
		}
		session.setAttribute("openType", openType);

		/** ʶ���û��������� */
		String adminDep = "";
		if (userType.equalsIgnoreCase("teacher")) {
			sql = "select xgbdm from xtszb where rownum=1";
			adminDep = dao.getOneRs(sql, new String[] {},
					new String[] { "xgbdm" })[0];
			if (Base.isNull(adminDep)) {
				errMsg = "ѧ��������Ϊ�գ��޷���½ѧ��ϵͳ����ȷ�ϣ���";
				//session.setAttribute("errMsg", errMsg);// ��������
				request.setAttribute("errMsg", errMsg);// ��������
				return false;
			}
			sql = "select (case bmjb when 1 then bmdm else bmfdm end) bmdm,bmmc,bmlb from zxbz_xxbmdm where bmdm=?";
			String[] userTmp = dao.getOneRs(sql, new String[] { userChk[0] },
					new String[] { "bmdm", "bmmc", "bmlb" });
			if (userTmp == null) {
				errMsg = "���ű����޸ò��ţ��޷���½ѧ��ϵͳ����ȷ�ϣ���";
				//session.setAttribute("errMsg", errMsg);// ��������
				request.setAttribute("errMsg", errMsg);// ��������
				return false;
			}
			session.setAttribute("bmmc", userTmp[1]);
			// TODO
			if (userTmp[0] != null && userTmp[0].equalsIgnoreCase(adminDep)) {
				userType = "admin";
			} else if (!Base.isNull(userTmp[2])) {
				if (userTmp[2].equalsIgnoreCase("5")) {
					userType = "xy";
				} else {
					userType = "xx";
				}
			} else {
				errMsg = "���û��������Ϊ�գ��޷���½ѧ��ϵͳ����ȷ�ϣ���";
				//session.setAttribute("errMsg", errMsg);// ��������
				request.setAttribute("errMsg", errMsg);// ��������
				return false;
			}
		} else {
			userType = "stu";
		}
		session.setAttribute("userType", userType);// �����ࣩ
		// �汾
		String edition = Base.getEdition();
		// �Ƿ���Ҫ�߼���ѯ
		String superSearch = Base.getSuperSearch();

		// -------------------------2011.9.14
		// qlj-------------------------------------
		// ===================�޸��û���½ʱ�� begin=======================

		// ��ʽ: yyyy-mm-dd HH24:MI:SS
		String dlsj = GetTime.getNowTime4();
		// ���û���½ʱ�����õ�session��
		session.setAttribute("loginTime", dlsj);

		List<String> inputV = new ArrayList<String>();
		StringBuilder updateTime = new StringBuilder();

		String checkTime = " select count(1)num from xg_xtwh_yhdlb where yhm='"
				+ userName + "' and yhlx='" + userType + "' ";
		String number = dao.getOneRs(checkTime.toString(), new String[] {},
				"num");

		// ===================�޸��û���½ʱ�� end=======================

		session.setAttribute("edition", edition);
		session.setAttribute("superSearch", superSearch);
		return true;
	}
	
	/**
	 * ��ȡ�û���
	 * @param yhm
	 * @return
	 */
	public String getUserType(String yhm){
		String userName = yhm;
		DAO dao = DAO.getInstance();
		String userType="";
		String sql = "select szbm,xm,zdm from yhb where yhm=?";
		String[] userChk = dao.getOneRs(sql, new String[] { userName },
				new String[] { "szbm", "xm", "zdm" });
		if ((userChk != null) && (userChk.length == 3)) {
			userType = "teacher";
		} else {
			sql = "select xm,bmdm szbm,'6727' zdm from view_xsjbxx where xh=?";
			userChk = dao.getOneRs(sql, new String[] { userName },
					new String[] { "szbm", "xm", "zdm" });
			if ((userChk != null) && (userChk.length == 3)) {
				userType = "student";
			} else {
				userType="";
			}
		}
		return userType;
	}
}
