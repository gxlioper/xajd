package xgxt.base;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Fdypd;
import xgxt.utils.GetTime;

import com.zfsoft.zfca.tp.cas.client.ZfssoBean;
import com.zfsoft.zfca.tp.cas.client.ZfssoSetsessionService;
import common.Globals;

public class SsoSetSession implements ZfssoSetsessionService {
	private Log logger = LogFactory.getLog(SsoSetSession.class);
	public Boolean chkUserSession(ZfssoBean zfssobean) {
		Boolean res = false;
		String userName = "";
		// У���û�session�Ƿ���ڣ����ڷ���true��ʧ�ܷ���false;
		HttpServletRequest request = zfssobean.getRequest();
		HttpSession session = request.getSession();
		
		if (null != session.getAttribute("userName") || null != session.getAttribute("jyweb_userName")) {
			String xgUserName = (String) session.getAttribute("userName");
			String jywebUserName = (String)session.getAttribute("jyweb_userName");
			
			userName = Base.isNull(xgUserName) ? jywebUserName : xgUserName;
			
		}
		
//		System.out.println("�û�����"+ userName);
		
		if (null != userName && (zfssobean.getYhm().equalsIgnoreCase(userName))) {
			res = true;
		}
		
//		System.out.println("У���û�session�Ƿ����:"+ res);
		
		return res;
	}
	
	
	
	
	public boolean setXgUserSession(ZfssoBean zfssobean) {
		HttpSession session = zfssobean.getRequest().getSession();
		session.setAttribute("istysfrz", "yes");
		String userType = "";
		String userName = zfssobean.getYhm();
		String errMsg = "";
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		
		logger.info("--------------userName:"+userName+"--------------");
		String sql = "select szbm,xm,zdm from yhb where yhm=? and qx='1' and zdm is not null";
		String[] userChk = dao.getOneRs(sql, new String[] { userName },
				new String[] { "szbm", "xm", "zdm" });
		logger.info("--------------userChk:"+userChk+"--------------");
		if ((userChk != null) && (userChk.length == 3)) {
			userType = "teacher";
		} else {
			sql = "select xm,bmdm szbm,'6727' zdm from view_xsjbxx where xh=?";
			userChk = dao.getOneRs(sql, new String[] { userName },
					new String[] { "szbm", "xm", "zdm" });
			//��ʦ����Ի��ж�
			if(Base.xxdm.equals("10698")&&(userChk == null || userChk.length == 0)){
				sql = "select xm,xydm szbm,'6727' zdm from view_xsxxb where xh=? and xjztm = '��ѧ��'";
				userChk = dao.getOneRs(sql, new String[] { userName },
						new String[] { "szbm", "xm", "zdm" });
			}
			if ((userChk != null) && (userChk.length == 3)) {
				userType = "student";
			} else {
				errMsg = "�𾴵��û��� �Բ�����δ����Ȩ���ʴ�ҵ��ϵͳ��<br/>��ȷ�ϻ����ӦȨ�޺��������������Դ��";
				System.out.println("�û������޸��û����޷���½ѧ��ϵͳ����ȷ�ϣ���");
				zfssobean.getSession().setAttribute("errMsg", errMsg);// ��������
				//if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
//					try {
//						//zfssobean.getSession().setAttribute("yhInfo", errMsg);// ��������
//						//zfssobean.getResponse().sendRedirect("/yhInfo.do");
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
				//}
				return false;
			}
		}
		
		if (userType.equalsIgnoreCase("teacher")) {
			boolean isFdy = Fdypd.isFdy(userName);
			session.setAttribute("isFdyUser", isFdy);
			session.setAttribute("isFdy", isFdy);
			session.setAttribute("isBzr", Fdypd.isBzr(userName,""));
			session.setAttribute("fdyQx", Fdypd.fdybjck(userName));
			session.setAttribute("bzrQx", Fdypd.bzrbjck(userName));
			// �Ƿ����ѧԺ�û�
			String sfjryx= Fdypd.checkSfjrXy(userName).get("sfjryx");
			// �ǿ�ȡ����
			if(!Base.isNull(sfjryx)){
				session.setAttribute("sfjryx", sfjryx);
				
				// �û�Ȩ��ѡ��Ĭ���ɴ�С
				if ("true".equalsIgnoreCase(sfjryx)){
					session.setAttribute("fdyQx", false);
					session.setAttribute("bzrQx", false);
				}
				
			}else{
				session.setAttribute("fdyQx", false);
				session.setAttribute("bzrQx", false);
				session.setAttribute("isFdy", false);
				session.setAttribute("isBzr", false);
			}
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
		
		//�Ƿ���Ժ�û�
		String sySql = "select sydm from fdyxxb where zgh=?";
		String sydm = dao.getOneRs(sySql, new String[] { userName },
				"sydm");
		String syQx = !Base.isNull(sydm) ? "yes" : "no";
		
		session.setAttribute("syQx", syQx);// �Ƿ�����Ժ�û�
		session.setAttribute("gyglyQx", gyglyQx);
		session.setAttribute("LoginXq", Base.currXq);
		session.setAttribute("LoginZc", StandardOperation.getCurrZc());
		String openType = zfssobean.getRequest().getParameter("openType");
		if ("".equalsIgnoreCase(openType) || openType == null) {
			openType = "2";
		}
		session.setAttribute("openType", openType);
		
		//��ְҵ���Ի����������ҵĹ����洢����
		if (Globals.XXDM_JHZYJSXY.equalsIgnoreCase(Base.xxdm)) {
			String userStatus = "";
			boolean fdyQx = Fdypd.isFdy(userName);
			boolean bzrQx = Fdypd.isBzr(userName,"");
			if (bzrQx && fdyQx) {
				userStatus = "jd";// �����μ渨��Ա
			} else if (fdyQx) {
				userStatus = "fdy";// ����Ա
			} else if (bzrQx) {
				userStatus = "bzr";// ������
			} else if ("xy".equalsIgnoreCase(userType)) {
				userStatus = "xy";// ѧԺ
			} else if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				userStatus = "xx";// ѧУ�û�������Ա��
			} else {
				userStatus = "stu";// ѧ��
			}
			try {
				dao.runProcuder("{call pro_xg_xtwh_wdgz(?,?,?)}",new String[]{userName, userStatus, userChk[0]});
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				System.out.println("=====>δ֪����ͨ���Ż���������ҵĹ����洢����ʧ��!======>");
				e.printStackTrace();
			}
		}
		//end
				
		/** ʶ���û��������� */
		String adminDep = "";
		if (userType.equalsIgnoreCase("teacher")) {
			sql = "select xgbdm from xtszb where rownum=1";
			adminDep = dao.getOneRs(sql, new String[] {},
					new String[] { "xgbdm" })[0];
			if (Base.isNull(adminDep)) {
				errMsg = "ѧ��������Ϊ�գ��޷���½ѧ��ϵͳ����ȷ�ϣ���";
				System.out.println("ѧ��������Ϊ�գ��޷���½ѧ��ϵͳ����ȷ�ϣ���");
				session.setAttribute("errMsg", errMsg);// ��������
				return false;
			}
			sql = "select (case bmjb when 1 then bmdm else bmfdm end) bmdm,bmmc,bmlb from zxbz_xxbmdm where bmdm=?";
			String[] userTmp = dao.getOneRs(sql, new String[] { userChk[0] },
					new String[] { "bmdm", "bmmc", "bmlb" });
			if (userTmp == null) {
				errMsg = "���ű����޸ò��ţ��޷���½ѧ��ϵͳ����ȷ�ϣ���";
				System.out.println("���ű����޸ò��ţ��޷���½ѧ��ϵͳ����ȷ�ϣ���");
				session.setAttribute("errMsg", errMsg);// ��������
				return false;
			}
			session.setAttribute("bmmc", userTmp[1]);
			// TODO
			if (userTmp[0] != null && userTmp[0].equalsIgnoreCase(adminDep)) {
				userType = "admin";
			}else if (!Base.isNull(userTmp[2])) {
				if (userTmp[2].equalsIgnoreCase("5")) {
					userType = "xy";
				} else {
					userType = "xx";
				}
			}else{
				errMsg = "���û��������Ϊ�գ��޷���½ѧ��ϵͳ����ȷ�ϣ���";
				System.out.println("���û��������Ϊ�գ��޷���½ѧ��ϵͳ����ȷ�ϣ���");
				session.setAttribute("errMsg", errMsg);// ��������
				return false;
			}
			
			if (!Base.isNull(sydm)) {
				userType = "sy";
			}
		} else {
			userType = "stu";
		}
		session.setAttribute("userType", userType);// �����ࣩ
//		 �汾
		String edition = Base.getEdition();
		// �Ƿ���Ҫ�߼���ѯ
		String superSearch = Base.getSuperSearch();
		
//		 -------------------------2011.9.14 qlj-------------------------------------
		// ===================�޸��û���½ʱ�� begin=======================
		
		// ��ʽ: yyyy-mm-dd HH24:MI:SS
		String dlsj=GetTime.getNowTime4();
		// ���û���½ʱ�����õ�session��
		session.setAttribute("loginTime",dlsj);
		
		List<String>inputV=new ArrayList<String>();
		StringBuilder updateTime=new StringBuilder();
		
		String checkTime=" select count(1)num from xg_xtwh_yhdlb where yhm='"+userName+"' and yhlx='"+userType+"' ";
		String number=dao.getOneRs(checkTime.toString(), new String[]{}, "num");
		
		// ===============�ж��û��Ƿ���� begin================
		
		
//		if("0".equalsIgnoreCase(number)){
//			updateTime.append(" insert into xg_xtwh_yhdlb(yhm,yhlx,dlsj)values(?,?,?) ");
//			inputV.add(userName);
//			inputV.add(userType);
//			inputV.add(dlsj);
//			try {
//				dao.runUpdate(updateTime.toString(), inputV.toArray(new String[]{}));
//			} catch (Exception e) {
//				// TODO �Զ����� catch ��
//				e.printStackTrace();
//			}
//		}else{
//			updateTime.append(" update xg_xtwh_yhdlb set dlsj=? where yhm=? and yhlx=? ");
//			inputV.add(dlsj);
//			inputV.add(userName);
//			inputV.add(userType);
//			try {
//				dao.runUpdate(updateTime.toString(), inputV.toArray(new String[]{}));
//			} catch (Exception e) {
//				// TODO �Զ����� catch ��
//				e.printStackTrace();
//			}
//		}
		// ===============�ж��û��Ƿ���� end================
		// ===================�޸��û���½ʱ�� end=======================
		
		session.setAttribute("edition", edition);
		session.setAttribute("superSearch", superSearch);
		return true;
	}
	
	
	
	
	public boolean  setJywebUserSession(ZfssoBean zfssobean) {
		HttpSession session = zfssobean.getRequest().getSession();

		String userType = "";
		String userName = zfssobean.getYhm();
		String errMsg = "";
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		
		String sql = "select a.*,'' shzt,(select zmc from yhzb where zdm=a.zdm) zmc from yhb a  where yhm=? ";
		String[] userChk = dao.getOneRs(sql, new String[] { userName },
				new String[] { "xm","szbm","shzt","zmc" });
	
//		System.out.println("��ѯ�û���.....");
		
		
		if ((userChk != null) && (userChk.length == 4)) {
			userType = "xy";
			
//			System.out.println("ѧԺ�û�.....");
		}else {
			System.out.println("ѧ���û�.....");
			sql = "select xm,bmdm szbm,'6727' zdm,'ѧ��' zmc from view_xsjbxx where xh=?";
			userChk = dao.getOneRs(sql, new String[] { userName },
					new String[] {"xm", "szbm","zdm","zmc" });
			//��ʦ����Ի��ж�
			if(Base.xxdm.equals("10698")&&(userChk == null || userChk.length == 0)){
				sql = "select xm,xydm szbm,'6727' zdm,'ѧ��' zmc from view_xsxxb where xh=? and xjztm = '��ѧ��'";
				userChk = dao.getOneRs(sql, new String[] { userName },
						new String[] {"xm", "szbm","zdm","zmc" });
			}
			if ((userChk != null) && (userChk.length == 4)) {
				userType = "stu";
			} else {
				errMsg = "�𾴵��û��� �Բ�����δ����Ȩ���ʴ�ҵ��ϵͳ��<br/>��ȷ�ϻ����ӦȨ�޺��������������Դ��";
				System.out.println("�û������޸��û����޷���½ѧ��ϵͳ����ȷ�ϣ���");
				zfssobean.getSession().setAttribute("errMsg", errMsg);// ��������
				//if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
//					try {
//						zfssobean.getSession().setAttribute("yhInfo", errMsg);// ��������
//						zfssobean.getResponse().sendRedirect("/yhInfo.do");
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
				//}
				return false;
			}
		}
		
		if (!Base.isNull(userType)) {
			
			if ("��ҵ��".equals(userChk[3])) {
				session.setAttribute("jyweb_userType", "admin");
			} else {
				session.setAttribute("jyweb_userType", userType);
			}
			System.out.println("��ҵ���û����ͣ�"+(String)session.getAttribute("jyweb_userType"));
			session.setAttribute("jyweb_userName", userName);
			session.setAttribute("jyweb_realName", userChk[0]);
			session.setAttribute("jyweb_userDep", userChk[1]);
			session.setAttribute("jyweb_dwshzt", "");
		
		}
		String xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},"xxmc");
		session.setAttribute("xxmc", xxmc);
		session.setAttribute("xxdm", xxdm);
		
		return true;
	}
	
	
	public Boolean setUserSession(ZfssoBean zfssobean) {
		
		SsoSetSession sso = new SsoSetSession();
		
//		System.out.println("���÷�����setXgUserSession");
		boolean xg = sso.setXgUserSession(zfssobean);
		
//		System.out.println("���÷�����setJywebUserSession");
		boolean jyweb = sso.setJywebUserSession(zfssobean);
		
//		System.out.println("׼�����أ�jyweb && xg");
		return xg && jyweb;

	}
	
}
