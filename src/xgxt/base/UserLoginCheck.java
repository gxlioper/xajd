package xgxt.base;

import java.util.HashMap;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import thauth.ThauthConst;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Fdypd;

public class UserLoginCheck {
	/**
	 * ��������ͳһ�����֤
	 * @param session
	 * @param userMap
	 */
	public HashMap<String, String> setAllLoginInfo(HttpServletRequest request,Hashtable<String, String> userTable){
	       DAO dao = DAO.getInstance();
			/** �洢��¼�û��Ļ�����Ϣ,�洢��session�� */
	        HttpSession session = request.getSession();
			session.setAttribute("pjzq", StandardOperation.getCollegePriseCycle());// ѧУ����������,xn
			// Ϊÿѧ����һ��;xq
			// Ϊѧ����һ��
			String userType = userTable.get(ThauthConst.THAUTH_YHLB);// teacher/student
			String userName = userTable.get(ThauthConst.THAUTH_ZJH);
			String zjh      = userTable.get(ThauthConst.THAUTH_YHM);
			String sql = "";
			String[] userChk = new String[]{};
			HashMap<String, String> resultMap = new HashMap<String, String>(); 
			if("teacher".equalsIgnoreCase(userType)){
				userChk = dao.getOneRs("select bmdm szbm,xm from fdyxxb where zgh=?", new String[]{zjh}, new String[]{"szbm","xm"});
				if(userChk != null){
					session.setAttribute("isFdy", true);
				} else {
					sql = "select szbm,xm from yhb where yhm=?";
					userChk = dao.getOneRs(sql, new String[]{userName}, new String[]{"szbm","xm"});
					session.setAttribute("isFdy", false);
				}
				session.setAttribute("isBzr", Fdypd.isBzr(userName, ""));
				session.setAttribute("fdyQx", Fdypd.fdybjck(userName));
				session.setAttribute("bzrQx", Fdypd.bzrbjck(userName));
			} else if("student".equalsIgnoreCase(userType)){
				sql = "select xm,bmdm szbm from view_xsjbxx where xh=?";
				userChk = dao.getOneRs(sql, new String[]{userName}, new String[]{"szbm","xm"});
				session.setAttribute("isFdy", false);
				session.setAttribute("isBzr", false);
				session.setAttribute("fdyQx", false);
				session.setAttribute("bzrQx", false);
			} else {
				resultMap.put("errorMsg", "�û����Ͳ���ȷ��");
				session.setAttribute("isFdy", false);
				return resultMap;
			}
			
			
			String[] xx = dao.getOneRs("select xxdm,xxmc from xtszb where rownum = 1", new String[]{}, new String[]{"xxdm","xxmc"});
			
			session.setAttribute("userOnLine", userType);// �û����ͣ�ѧ������ʦ��
			session.setAttribute("userName", userName);  // ��½��
			
			if(userChk == null){//���ݿ��в�����
				resultMap.put("errorMsg", "�û������ڣ���˶Ժ��½��");
				session.setAttribute("isFdy", false);
				return resultMap;
			}else{
				session.setAttribute("userDep", (userChk!=null) && (userChk.length==2) ? userChk[0] :"");// �û�����
				session.setAttribute("userNameReal", (userChk!=null) && (userChk.length==2) ? userChk[1] :"");// �û�����
			}
			session.setAttribute("xxdm", xx[0]);//ѧУ����
			session.setAttribute("xxmc", xx[1]);//ѧУ����
			// Modify 10.12 
			session.setAttribute("LoginXn", Base.currXn);
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
					resultMap.put("errorMsg", "�û�����ʶ����������µ�½��");
				}
				sql = "select (case bmjb when 1 then bmdm else bmfdm end) bmdm,bmmc,bmlb from zxbz_xxbmdm where bmdm=?";
				String[] userTmp = dao.getOneRs(sql, new String[] { userChk[0] },
						new String[] { "bmdm", "bmmc", "bmlb" });
				if (userTmp == null) {
					resultMap.put("errorMsg", "�û�����ʶ����������µ�½��");
				}
				session.setAttribute("bmmc", userTmp[1]);
				if (userTmp[0].equalsIgnoreCase(adminDep)) {
					userType = "admin";
				} else if (userTmp[2].equalsIgnoreCase("5")) {
					userType = "xy";
				} else {
					userType = "xx";
				}
			} else {
				userType = "stu";
			}
			session.setAttribute("userType", userType);// �����ࣩ
			return resultMap;
	}
}
