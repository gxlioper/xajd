/*
 * �������� 2006-8-25
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.action;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.springframework.context.ApplicationContext;

import com.zfsoft.license.LicenseOps;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.DAO.LdapTestDao;
import xgxt.action.service.SystemService;
import xgxt.base.DealString;
import xgxt.base.Encrypt;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.*;
import xgxt.utils.String.StringUtils;

/**
 * @author bat_zzj
 * 
 * Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class SysManAction extends Action {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	// private boolean isNull(String str) {
	// return ((str == null) || str.equalsIgnoreCase(""));
	// }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// String userType;

		// String sUName;

		// String logMsg;

		String writeAble;
		// HttpSession session = request.getSession();
		CommanForm chkUser = (CommanForm) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("��½��ʱ�������µ�½��");
				return new ActionForward("/login.jsp", false);
			}

			// boolean isStu = true;
			// userType = session.getAttribute("userType").toString();
			// isStu = (userType.equalsIgnoreCase("stu"));
			// sUName = session.getAttribute("userName").toString();

			ActionForward myActFwd = null;
			String myAct = mapping.getParameter();
			// String power;
			// String para = "";
			int p = -1;
			// �ж��û���дȨ
			writeAble = Base.getWriteAble(request);

			if ("INITGROUPPOWER".equalsIgnoreCase(myAct)) {
				myActFwd = initGroupPower(mapping, form, request, response, p);
			} else if ("SAVEGROUPPOWER".equalsIgnoreCase(myAct)) {
				myActFwd = saveGroupPower(mapping, form, request, response);
			}
//				else if ("ADDGROUP".equalsIgnoreCase(myAct)) {
//				int flag = 1;
//				String sql = "insert into yhzb(zdm,zmc) values(lpad(to_char(lsh_yhz.nextval),8,'0'),?)";
//				myActFwd = groupDo(mapping, form, request, response, sql, flag);
//			} else if ("MODIGROUP".equalsIgnoreCase(myAct)) {
//				int flag = 2;
//				String sql = "update yhzb set zmc=?,sfxs=? where zdm=?";
//				myActFwd = groupDo(mapping, form, request, response, sql, flag);
//			} else if ("DELGROUP".equalsIgnoreCase(myAct)) {
//				int flag = 3;
//				String sql = "{call deleteGroup(?)}";
//				myActFwd = groupDo(mapping, form, request, response, sql, flag);
//			}
//			�ڴ�ʦ�ĺ�����,�����Ƿ���ʾ���ú������޸ĺϲ�
//			else if ("SZGROUP".equalsIgnoreCase(myAct)) {
//				int flag = 4;
//				String sql = "update yhzb set sfxs=? where zdm=?";
//				myActFwd = groupDo(mapping, form, request, response, sql, flag);
//			}
			else if ("INITUSERPAGE".equalsIgnoreCase(myAct)) {
				myActFwd = initUserPower(mapping, form, request, response, p);
			} else if ("SAVEUSERPOWER".equalsIgnoreCase(myAct)) {
				myActFwd = saveUserPower(mapping, form, request, response);
			} 
//				else if ("ADDUSER".equalsIgnoreCase(myAct)) {
//				int flag = 1;
//				String sql = "insert into yhb(yhm,kl,zdm,xm,szbm,dwdm,qx) values(?,?,?,?,?,?,?)";
//				myActFwd = userDo(mapping, form, request, response, sql, flag);
//			} else if ("DELUSER".equalsIgnoreCase(myAct)) {
//				int flag = 2;
//				String sql = "delete from yhb where yhm=?";
//				myActFwd = userDo(mapping, form, request, response, sql, flag);
//			} else if ("MODIUSER".equalsIgnoreCase(myAct)) {
//				int flag = 3;
//				String sql = "update yhb set zdm=?,xm=?,szbm=?,dwdm=?,qx=? where yhm=?";
//				myActFwd = userDo(mapping, form, request, response, sql, flag);
//			} 
			else if ("VIEWUSERPASSWORD".equalsIgnoreCase(myAct)) {
				myActFwd = viewPassword(mapping, form, request, response);
			} else if ("CODEINIT".equalsIgnoreCase(myAct)) {                    //����ά��
				String codeType = request.getParameter("act");
 				myActFwd = codeManage(mapping, form, request, response,
						codeType);
			} else if ("CODECONF".equalsIgnoreCase(myAct)) {
				myActFwd = codeAdd(mapping, form, request, response);
			} else if ("LOGSEARCH".equalsIgnoreCase(myAct)) {
				myActFwd = logSearch(mapping, form, request, response);
			} else if ("logviewinfo".equalsIgnoreCase(myAct)) {
				myActFwd = logViewinfo(mapping, form, request, response);  //�鿴������־����
			} 		
			else if ("dataBackup".equalsIgnoreCase(myAct)) {
				myActFwd = dataBackup(mapping, form, request, response);
			} else if ("systemInit".equalsIgnoreCase(myAct)) {
				myActFwd = systemInit(mapping, form, request, response);
			} else if ("baseDataInit".equalsIgnoreCase(myAct)) {
				myActFwd = baseDataInit(mapping, form, request, response);
			} else if ("bjdydygxb".equalsIgnoreCase(myAct)) {
				myActFwd = bjdydygxb(mapping, form, request, response);
			} else if ("STUPWDINIT".equalsIgnoreCase(myAct)) {
				// ѧ�������ʼ��
				myActFwd = stuPwdInit(mapping, form, request, response, p);
			} else if ("bak_set".equalsIgnoreCase(myAct)) {
				myActFwd = SetBakInfo(mapping, form, request, response);
			} else if ("bak_set_save".equalsIgnoreCase(myAct)) { //
				myActFwd = SaveSetBakInfo(mapping, form, request, response);
			} else if ("AllTeaUser".equalsIgnoreCase(myAct)) { // ͳһȫ�����ɽ�ʦ����
				myActFwd = AllTeaUser(mapping, form, request, response);
			} else if ("user_query".equalsIgnoreCase(myAct)) { // �û���ѯ
				myActFwd = UserQuery(mapping, form, request, response);
			} else if ("fdyxxQuery".equalsIgnoreCase(myAct)) { // ����Ա��Ϣ��ѯ
				myActFwd = fdyxxQuery(mapping, form, request, response);
			} else if ("userorder".equalsIgnoreCase(myAct)) { // ����Ա��Ϣ��ѯ
				myActFwd = userOrder(mapping, form, request, response);
			} else if ("EXPGROUP".equalsIgnoreCase(myAct)){//�����û���Ȩ��
				myActFwd = expGroup(mapping, form, request, response);
			} else if ("EXPUSER".equalsIgnoreCase(myAct)){//�����û�Ȩ��
				myActFwd = expUser(mapping, form, request, response);
			} else if ("initPass".equalsIgnoreCase(myAct)){
				myActFwd = initPass(mapping, form, request, response);
			}else if ("baseDataExp".equalsIgnoreCase(myAct)) {
				// -----------2012.3.2 qlj-------------
				// -----------�������ݵ���--------------
				myActFwd = baseDataExp(mapping, form, request, response);
			} 
			//request.setAttribute("devMode", LicenseOps.getInstance().getDevMode());
			request.setAttribute("devMode", "1");
			request.setAttribute("writeAble", writeAble);
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("���������жϣ������ԣ�");
			return new ActionForward("/errMsg.do", false);
		}
	}

	private ActionForward initGroupPower(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, int power) throws Exception {
		// ��ʼ����ά��ҳ��
		DAO dao = DAO.getInstance();
		String sql = "";
		CommanForm groupForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String groupPower = request.getParameter("zmc");

		sql = "select gnmkdm,gnmkmc from view_yhqx where yhm=? order by gnmkdm";
		String[] input = { userName };
		if ((groupPower != null) || !"".equalsIgnoreCase(groupPower)) {//ѡ�����û����ҳ���е�ǰ��ϵͳ����ģ���б�ֻ��ʾ��û�зָ�����Ĺ���
			sql = "select gnmkdm,gnmkmc from view_yhqx a where yhm=? and not exists(select 1 from view_yhzqx b where b.zdm=? and a.gnmkdm=b.gnmkdm and substr(gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?)) order by gnmkdm";
			input = new String[]{userName, groupPower, userName};
		}
		
		
		String[] output = { "gnmkdm", "gnmkmc" };
		String powerList = dao.getStringToSplit(sql, input, output);//��½�û�������Ȩ��
		// ��ʼ��ȫ��Ȩ�޵��б�
		sql = "select * from yhzb where zmc<>'ϵͳ����Ա' order by zdm";
		input = new String[] {};
		output = new String[] { "zdm", "zmc" };
		List groupList = dao.getList(sql, input, output);//��ϵͳ����Ա������������б�
		// ��ʼ��ȫ���û�����б�

		if ((groupPower == null) || "".equalsIgnoreCase(groupPower)) {
			sql = "select (nvl(dxq,'0')||gnmkdm) gnmkdm,(rpad(gnmkdm,7,'_')||' | '||replace(replace(nvl(dxq,'0'),'0','ֻ��'),'1','��д')||' | '||gnmkmc) gnmkmc from view_yhzqx where zdm=(select min(zdm) from yhzb) and substr(gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?) order by view_yhzqx.gnmkdm";
			input = new String[] { userName };
		} else {
			sql = "select (nvl(dxq,'0')||gnmkdm) gnmkdm,(rpad(gnmkdm,7,'_')||' | '||replace(replace(nvl(dxq,'0'),'0','ֻ��'),'1','��д')||' | '||gnmkmc) gnmkmc from view_yhzqx where zdm=? and substr(gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?) order by view_yhzqx.gnmkdm";
			input = new String[] { groupPower, userName };
		}
		output = new String[] { "gnmkdm", "gnmkmc" };
		List powerListGroup = dao.getList(sql, input, output);//�û�������Ȩ���б�
		// ��ʼ��������Ȩ���б�
		groupForm.setDisplayGroupPower("yes");

		if ((groupList == null) || "Error".equalsIgnoreCase(powerList)) {
			groupForm.setErrMsg("Err0002");
			return mapping.findForward("false");
		}
		groupForm.setDisplaySubPower(powerList);
		request.setAttribute("listGroup", groupList);
		request.setAttribute("powerListG", powerListGroup);
		return mapping.findForward("success");
	}

	private ActionForward saveGroupPower(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		CommanForm groupForm = (CommanForm) form;
		String groupName = request.getParameter("zmc");
		String[] groupPower = request.getParameter("power").split(",");
		int i = groupPower.length;
		String[][] groupPowerRW = new String[i][2];
		String forward = "false";
		for (int j = 0; j < i; j++) {
			groupPowerRW[j] = groupPower[j].split(":");
		}

		if (groupName.equalsIgnoreCase("0001")) {
			groupForm.setErrMsg("Err0005");
			return mapping.findForward("false");
		}

		sql = "delete from yhzqxb where zdm=?";
		if (groupName == null || groupName.trim().length() == 0
				|| !Check_Input_Value.check2(groupName)) {
			groupForm.setErrMsg("û��ѡ����ʵ��飡����");
			return mapping.findForward("false");
		}
		// ��ɾ����ǰ���ԭ��Ȩ��֮ǰ���ȱ��浽��ʱ����
		
		dao.runUpdate("delete from yhzqxb_lsb where zdm=?",
				new String[] { groupName });
		
		dao.runUpdate("insert into yhzqxb_lsb(zdm,gnmkdm,dxq) "
				+ "select zdm,gnmkdm,dxq from yhzqxb where zdm=?",
				new String[] { groupName });
		// ɾ����ǰ���ԭ��Ȩ��
		boolean del = dao.runUpdate(sql, new String[] { groupName });
		sql = "insert into yhzqxb(zdm,gnmkdm,dxq) values(?,?,?)";
		boolean ins = false;
		for (i = 0; i < groupPowerRW.length; i++) {
			String tmp[] = new String[] { groupName, groupPowerRW[i][1],
					groupPowerRW[i][0] };
			ins = dao.runUpdate(sql, tmp);
		}

		sql = "{call updateUserPower(?)}";
		boolean update = dao.runProcuder(sql, new String[] { groupName });

		if (del && ins && update) {
			dao = new DAO(request.getRemoteAddr());
			String logMsg = "�޸���Ȩ��,�����Ϊ��" + groupName + "��";
			dao.writeLog("", null, null, logMsg, request);
			forward = "success";
		} else {
			groupForm.setErrMsg("Err0006");
			forward = "false";
		}
		return mapping.findForward(forward);
	}

	private ActionForward groupDo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String sql, int flag) throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm groupForm = (CommanForm) form;
		String groupID = request.getParameter("zmc");
		String groupName = DealString.toGBK(request
				.getParameter("newGroupName"));
		String sfxsGroup = DealString.toGBK(request
				.getParameter("sfxsGroup"));
		String forward = "false";
		String logMsg = "";
		String errFlag = "";
		String[] input = null;
		if (flag == 1) {
			input = new String[] { groupName };
			logMsg = "������飬������Ϊ��" + groupName + "��";
			errFlag = "Err0007";
		} else if (flag == 2) {
			input = new String[] { groupName,sfxsGroup, groupID };
			logMsg = "���������ƣ������Ϊ��" + groupID + "�����������Ϊ��" + groupName + "��";
			logMsg +="���������û�ά��ʱ�Ƿ���ʾ�������Ϊ��" + groupID + "��������Ϊ��" + groupName;
			errFlag = "Err0008";
		} else if (flag == 3)  {
			input = new String[] { groupName };
			logMsg = "ɾ�����Լ������µ������û��������Ϊ��" + groupID + "��������Ϊ��" + groupName
					+ "��";
			errFlag = "Err0009";
		}
//		�����Ƿ���ʾ���޸������ϲ�
//		else {
//			input = new String[] { sfxsGroup, groupID };
//			logMsg = "���������û�ά��ʱ�Ƿ���ʾ�������Ϊ��" + groupID + "��������Ϊ��" + groupName
//					+ "��";
//			errFlag = "Err0009";
//		}

		boolean added = dao.runUpdate(sql, input);

		if (!added) {
			groupForm.setErrMsg(errFlag);
			forward = "false";
		} else {
			dao = new DAO(request.getRemoteAddr());
			dao.writeLog("", null, null, logMsg, request);
			forward = "success";
		}
		if(flag==1 || flag==2){
			request.setAttribute("message", added ? "����ɹ�" : "����ʧ��");
		}else if(flag==3){
			request.setAttribute("message", added ? "ɾ���ɹ�" : "ɾ���ɹ�");
		}		
		return mapping.findForward(forward);
	}

	private ActionForward initUserPower(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		CommanForm userForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String xxdm = StandardOperation.getXxdm();

		String userID = request.getParameter("userName");

		sql = "select distinct zdm,zmc from yhzb where sfxs='1' and zdm<>'6727' order by zdm";
		String[] tmpIn = {};
		String[] tmpOut = { "zdm", "zmc" };
		String groupList = dao.getStringToSplit(sql, tmpIn, tmpOut);
		// �޸Ļ������û�ʱ��Ҫ�õ��û����б��ڴ˳�ʼ��

		if ((groupList == null) || groupList.equalsIgnoreCase("")) {
			session.setAttribute("errNo", "Err0013");
			return mapping.findForward("false");
		}
		sql = "select distinct bmdm,bmmc from ZXBZ_XXBMDM order by bmdm";
		tmpOut[0] = "bmdm";
		tmpOut[1] = "bmmc";
		String partList = dao.getStringToSplit(sql, tmpIn, tmpOut);
		if ((partList == null) || partList.equalsIgnoreCase("")) {
			partList = "!!SplitSignOne!!!!SplitSignTwo!! !!SplitSignTwo!!δ�ҵ�����";
		}
		// �޸Ļ������û�ʱ��Ҫ�õ������б��ڴ˳�ʼ��

		sql = "select distinct dwdm,dwmc from bks_dwdmb order by dwdm";
		tmpOut[0] = "dwdm";
		tmpOut[1] = "dwmc";
		String unitList = dao.getStringToSplit(sql, tmpIn, tmpOut);

		if ((unitList == null) || unitList.equalsIgnoreCase("")) {
			unitList = "!!SplitSignOne!!!!SplitSignTwo!! !!SplitSignTwo!!δ�ҵ���λ";
		}
		// �޸Ļ������û�ʱ��Ҫ�õ���λ�б��ڴ˳�ʼ��

		sql = "select gnmkdm,gnmkmc from view_yhqx where yhm=? order by gnmkdm";
		String[] inputSQLPower = { userName };
		String[] outputSQLPower = { "gnmkdm", "gnmkmc" };
		String powerList = dao.getStringToSplit(sql, inputSQLPower,
				outputSQLPower);
		// ��ʼ��ȫ��Ȩ���б�

		sql = "select a.yhm,(a.yhm||'/'||nvl(a.xm,'-')||'/'||b.zmc||'/'||nvl(c.bmmc,a.szbm)||'/'||nvl(d.dwmc,a.dwdm))||'/'||(case a.qx when '1' then '����' when '0' then '������' else a.qx end) xm,b.zmc from yhb a,yhzb b,ZXBZ_XXBMDM c,bks_dwdmb d where a.zdm<>'0001' and a.zdm=b.zdm and a.szbm=c.bmdm and a.dwdm=d.dwdm "
				+ " order by a.zdm,a.yhm";
		if(Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)){//�㽭����ѧ ȥ����λ
			sql = "select a.yhm,(a.yhm||'/'||nvl(a.xm,'-')||'/'||b.zmc||'/'||nvl(c.bmmc,a.szbm))||'/'||(case a.qx when '1' then '����' when '0' then '������' else a.qx end) xm,b.zmc from yhb a,yhzb b,ZXBZ_XXBMDM c where a.zdm<>'0001' and a.zdm=b.zdm and a.szbm=c.bmdm "
				+ " order by a.zdm,a.yhm";
		}
		String[] inputSQLGroup = {};
		String[] outputSQLGroup = { "yhm", "xm" };
		List userList = dao.getList(sql, inputSQLGroup, outputSQLGroup);

		String[] inputSQLGroupPower = null;
		if ((userID == null) || "".equalsIgnoreCase(userID)) {
			sql = "select (nvl(dxq,'0')||gnmkdm) gnmkdm,(rpad(gnmkdm,7,'_')||' | '||replace(replace(nvl(dxq,'0'),'0','ֻ��'),'1','��д')||' | '||gnmkmc) gnmkmc from view_yhqx where yhm=(select min(yhm) from yhb where yhm<>'admin') and substr(gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?) order by gnmkdm";
			inputSQLGroupPower = new String[] { userName };
		} else {
			sql = "select (nvl(dxq,'0')||gnmkdm) gnmkdm,(rpad(gnmkdm,7,'_')||' | '||replace(replace(nvl(dxq,'0'),'0','ֻ��'),'1','��д')||' | '||gnmkmc) gnmkmc from view_yhqx where yhm=? and substr(gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?) order by gnmkdm";
			inputSQLGroupPower = new String[] { userID, userName };
		}
		String[] outputSQLGroupPower = { "gnmkdm", "gnmkmc" };
		List powerListGroup = dao.getList(sql, inputSQLGroupPower,
				outputSQLGroupPower);
		request.setAttribute("powerListG", powerListGroup);

		if ((userList == null) || "Error".equalsIgnoreCase(powerList)) {
			userForm.setErrMsg("Err0002");
			return mapping.findForward("false");
		}
		userForm.setPartList(partList);
		userForm.setGroupList(groupList);
		userForm.setUserPower(powerList);
		userForm.setUnitList(unitList);
		request.setAttribute("sUserName", userName);
		request.setAttribute("xxdm", StandardOperation.getXxdm());
		request.setAttribute("listGroup", userList);
		return mapping.findForward("success");
	}

	private ActionForward saveUserPower(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		CommanForm userForm = (CommanForm) form;
		String userPName = request.getParameter("userName");
		String[] userPower = request.getParameter("power").split(",");
		int i = userPower.length;
		String[][] groupPowerRW = new String[i][2];
		String forward = "false";
		String xxdm = request.getParameter("xxdm");
		for (int j = 0; j < i; j++) {
			groupPowerRW[j] = userPower[j].split(":");
		}

//		if ("admin".equalsIgnoreCase(userPName)
//				|| "shgc".equalsIgnoreCase(userPName)) {
//			userForm.setErrMsg("Err0005");
//			return mapping.findForward("false");
//		}
		boolean del = false;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)){
			sql = "delete from yh_xg_qxb where yhm=?";
			del = dao.runUpdate(sql, new String[] { userPName });

			if ("14136".equalsIgnoreCase(xxdm) && del && groupPowerRW.length == 1) {
				String zdm = dao.getOneRs("select zdm from yhb where yhm=?",
						new String[] { userPName }, "zdm");
				sql = "insert into yh_xg_qxb (yhm,gnmkdm,dxq) (select " + userPName
						+ ",gnmkdm,dxq from yhzqxb where zdm=?)";
				dao.runUpdate(sql, new String[] { zdm });
				dao = new DAO(request.getRemoteAddr());
				String logMsg = "�޸��û�Ȩ��,�û���Ϊ" + userPName + "��";
				dao.writeLog("", null, null, logMsg, request);
				return mapping.findForward("success");
			}

			sql = "insert into yh_xg_qxb (yhm,gnmkdm,dxq) values(?,?,?)";
		}else{
			sql = "delete from yhqxb where yhm=?";
				del = dao.runUpdate(sql, new String[] { userPName });
			
				if ("14136".equalsIgnoreCase(xxdm) && del && groupPowerRW.length == 1) {
					String zdm = dao.getOneRs("select zdm from yhb where yhm=?",
					new String[] { userPName }, "zdm");
					sql = "insert into yhqxb (yhm,gnmkdm,dxq) (select " + userPName
					+ ",gnmkdm,dxq from yhzqxb where zdm=?)";
			dao.runUpdate(sql, new String[] { zdm });
			dao = new DAO(request.getRemoteAddr());
			String logMsg = "�޸��û�Ȩ��,�û���Ϊ" + userPName + "��";
			dao.writeLog("", null, null, logMsg, request);
			return mapping.findForward("success");
		}

		sql = "insert into yhqxb(yhm,gnmkdm,dxq) values(?,?,?)";
		}
		boolean ins = false;
		for (i = 0; i < groupPowerRW.length; i++) {
			String tmp[] = new String[] { userPName, groupPowerRW[i][1],
					groupPowerRW[i][0] };
			ins = dao.runUpdate(sql, tmp);
		}

		if (del && ins) {
			dao = new DAO(request.getRemoteAddr());
			String logMsg = "�޸��û�Ȩ��,�û���Ϊ" + userPName + "��";
			dao.writeLog("", null, null, logMsg, request);
			forward = "success";
		} else {
			userForm.setErrMsg("Err0006");
			forward = "false";
		}
		return mapping.findForward(forward);
	}

	private ActionForward userDo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String sql, int flag) throws Exception {
		DAO dao = DAO.getInstance();
		SystemService service = new SystemService();
		Encrypt ept = new Encrypt();
		CommanForm userForm = (CommanForm) form;
		String userID = request.getParameter("newUserID");
		String userPassword = request.getParameter("newPassword");
		String userPName = request.getParameter("newUserName");
		String userGroup = request.getParameter("newUserGroup");
		String userPart = request.getParameter("newUserPart");//��ȡ���ǲ�������
		String userUnit = request.getParameter("dwText");
		String qx = request.getParameter("purview");
		String power = request.getParameter("power");
		String uN = request.getParameter("userName");
		String forward = "false";
		String logMsg = "";
		String errFlag = "";
		String[] inputSQL = null;
		String xxdm = StandardOperation.getXxdm();
		
		
		
		if(flag !=2 && !Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)){//��ɾ������
			userUnit = service.getUserUnitId(userUnit,request);//���ݵ�λ���Ʋ�ѯ��λid,��ѯ����id������һ���µļ�¼�����ز����id
		}
		boolean done = false;
		if ((userPassword != null) && !userPassword.equalsIgnoreCase("")) {
			userPassword = ept.encrypt(userPassword);
		}
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)){//��ְҵ����ѧԺ		
		if (flag == 1) {
			logMsg = "������û����û���Ϊ��" + userID + "�����������Ϊ��" + userGroup + "��";
			errFlag = "Err0014";
			inputSQL = new String[] { userID, userPassword, userGroup,
					userPName, userPart, userUnit,qx};
			done = dao.runUpdate(sql, inputSQL);
			// ������û�
			sql = "insert into yh_xg_qxb (select '" + inputSQL[0]
					+ "',gnmkdm,dxq from yhzqxb where zdm=?)";
			inputSQL = new String[] { userGroup };
			done = dao.runUpdate(sql, inputSQL);
			// ���û��������Ĭ��Ȩ�޷ַ������û�
		} else if (flag == 2) {
			logMsg = "ɾ���û����û���Ϊ��" + power + "��";
			errFlag = "Err0015";
			inputSQL = new String[] { DealString.toGBK(power) };
			done = dao.runUpdate(sql, inputSQL);
			// ɾ���û�
			sql = "delete from yh_xg_qxb where yhm=?";
			done = dao.runUpdate(sql, inputSQL);
			// ɾ���û�Ȩ��
		} else {
			logMsg = "�޸��û���Ϣ���û���Ϊ��" + userID + "��";
			errFlag = "Err0016";
			String getOldG = "select count(*) num from yhb where yhm=? and zdm=?";
			String oldG = dao.getOneRs(getOldG, new String[] { uN, userGroup },
					new String[] { "num" })[0];

			inputSQL = new String[] { userGroup, userPName, userPart, userUnit,qx,
					uN };
			done = dao.runUpdate(sql, inputSQL);

			if (Integer.parseInt(oldG) == 0) {
				// �޸��û���Ϣ
				sql = "delete from yh_xg_qxb where yhm=?";
				inputSQL = new String[] { uN };
				done = dao.runUpdate(sql, inputSQL);
				// ɾ���û�����Ȩ��
				sql = "insert into yh_xg_qxb (select '" + uN
						+ "',gnmkdm,dxq from yhzqxb where zdm=?)";
				inputSQL = new String[] { userGroup };
				done = dao.runUpdate(sql, inputSQL);
				// ����û���Ȩ��
			}
		}
		}else{
			if (flag == 1) {
				logMsg = "������û����û���Ϊ��" + userID + "�����������Ϊ��" + userGroup + "��";
				errFlag = "Err0014";
				inputSQL = new String[] { userID, userPassword, userGroup,
						userPName, userPart, userUnit,qx };
				// ������û�
				done = dao.runUpdate(sql, inputSQL);
				
				// ���û��������Ĭ��Ȩ�޷ַ������û�
				sql = "insert into yhqxb (select '" + inputSQL[0]
						+ "',gnmkdm,dxq from yhzqxb where zdm=?)";
				inputSQL = new String[] { userGroup };
				done = dao.runUpdate(sql, inputSQL);
				
			} else if (flag == 2) {
				logMsg = "ɾ���û����û���Ϊ��" + power + "��";
				errFlag = "Err0015";
				inputSQL = new String[] { DealString.toGBK(power) };
				// ɾ���û�
				done = dao.runUpdate(sql, inputSQL);
				
				// ɾ���û�Ȩ��
				sql = "delete from yhqxb where yhm=?";
				done = dao.runUpdate(sql, inputSQL);				
			} else {
				logMsg = "�޸��û���Ϣ���û���Ϊ��" + userID + "��";
				errFlag = "Err0016";
				String getOldG = "select count(*) num from yhb where yhm=? and zdm=?";
				String oldG = dao.getOneRs(getOldG, new String[] { uN, userGroup },
						new String[] { "num" })[0];

				inputSQL = new String[] { userGroup, userPName, userPart, userUnit,qx,
						uN };
				done = dao.runUpdate(sql, inputSQL);

				if (Integer.parseInt(oldG) == 0) {
					// �޸��û���Ϣ
					sql = "delete from yhqxb where yhm=?";
					inputSQL = new String[] { uN };
					done = dao.runUpdate(sql, inputSQL);
					// ɾ���û�����Ȩ��
					sql = "insert into yhqxb (select '" + uN
							+ "',gnmkdm,dxq from yhzqxb where zdm=?)";
					inputSQL = new String[] { userGroup };
					done = dao.runUpdate(sql, inputSQL);
					// ����û���Ȩ��
				}
			}
		}
		if (!done) {
			userForm.setErrMsg(errFlag);
			forward = "false";
		} else {
			forward = "success";
			dao = new DAO(request.getRemoteAddr());
			dao.writeLog("", null, null, logMsg, request);
		}
		
		request.setAttribute("message", done ? "�����ɹ�" : "����ʧ��");
		return mapping.findForward(forward);
	}

	private ActionForward viewPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		Encrypt ept = new Encrypt();
		CommanForm userForm = (CommanForm) form;
		String userID = request.getParameter("userName");
		String forward = "false";
		sql = "select * from yhb where yhm=?";
		String[] inputSQL = { userID };
		String[] outputSQL = { "yhm", "xm", "kl" };
		String password = dao.getStringToSplit(sql, inputSQL, outputSQL);
		String[] tmp1 = password.split("!!SplitSignOne!!");
		String[] tmp2 = tmp1[1].split("!!SplitSignTwo!!");
		tmp2[3] = ept.decrypt(tmp2[3]);

		password = tmp2[1] + "!!SplitSigne!!" + tmp2[2] + "!!SplitSigne!!"
				+ tmp2[3];

		userForm.setPassword(password);

		if ((password == null) || password.equalsIgnoreCase("")) {
			userForm.setErrMsg("Err0001");
			forward = "false";
		} else {
			forward = "success";
			String logMsg = "��ѯ�û���" + userID + "�����롣";
			dao = new DAO(request.getRemoteAddr());
			dao.writeLog("", null, null, logMsg, request);
		}
		return mapping.findForward(forward);
	}

	private ActionForward codeManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String codeType) throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		SystemService sysService= new SystemService();
		String sql = "";
		CommanForm codeForm = (CommanForm) form;
		String tName = request.getParameter("tName");
		String tTem = "";
		String tips = "";
		String[] pageCard = null;
		String[] tabNames = null;
		String[] tabTitle = null;
		String[] tabTitleCN = null;
		Vector<Object> vector = new Vector<Object>();
		String toInitSel_1 = "";
		String toInitSel_2 = "";
		String toInitSel_3 = "";
		String toInitSel_4 = "";
		String toInitSel_5 = "";
		String xydm = "";
		String userType = request.getSession().getAttribute("userType").toString();
		String bmdm = request.getSession().getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType,"xy")) {
			xydm = bmdm;
		} else {
			xydm = dao.getOneRs("select xgbdm from xtszb where rownum=1",
					new String[] {}, "xgbdm");
		}
		if (codeType.equalsIgnoreCase("thought")) {
			tips = "˼�����";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
				pageCard = new String[] { "��ѵ��Ŀ", "�༶�뵳Ա��ϵ�˶�Ӧ��" };
				tabNames = new String[] { "pxxmdmb", "bjdydygxb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				pageCard = new String[] { "����Ա���۵ȼ�" };
				tabNames = new String[] { "sxjy_fdypjdjdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)) {
				pageCard = new String[] { "����֯�ɲ�ְ���" };
				tabNames = new String[] { "tzzzwdmb" };
			} 
			// ===============begin  2009/3/23 ���ΰ =================
			else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
				pageCard = new String[] { "��������","��������","��������" };
				tabNames = new String[] { "cllx","zjcm_jclx","zjcm_jcly" };
			}
			// ===============begin  2009/8/31 ³�� =================
			else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				pageCard = new String[] { "�������","������Ŀ����","�ش�ѧ����Ϣ���ݼ����","��ѵ��Ŀ","ѧ�����"};
				tabNames = new String[] { "dtjsZgdzdxLwlbb","dtjsZgdzdxkyxmjbb","dtjsDdxgxxjbb","pxxmdmb","dtjs_xsccb" };
			}// ===============begin  2009/11/9 ���ΰ =================
			else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)){
				pageCard = new String[] { "��ѵ��Ŀ", "ѧ�����" };
				tabNames = new String[] { "pxxmdmb", "dtjs_xsccb" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_CZXXZYJSXY)){
				pageCard = new String[] { "����Ա��ѵ��Ŀ","��Ա��ѵ��Ŀ"};
				tabNames = new String[] { "pxxmdmb","typxxmdmb"};
			//TODO 2010-07-29 ������	
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)){
				pageCard = new String[] { "��Ա��Ϣ�춯��"};
				tabNames = new String[] { "gdby_dtjs_ydlxdmb"};
			}
			else {
				pageCard = new String[] { "��ѵ��Ŀ", "ѧ�����", "�ȼ�����" };
				tabNames = new String[] { "pxxmdmb", "dtjs_xsccb", "dtjs_djdmb" };
			}
		} else if (codeType.equalsIgnoreCase("prise")) {
			tips = "��������";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
				// �㽭��ҵְҵѧԺ
				pageCard = new String[] { "��ѧ��", "�����ƺ�", "����������", "�������ȱ�",
						"�μӻ�б�", "�����б�", "��������", "��������" };
				tabNames = new String[] { "jxjdmb", "rychdmb", "zhcp_bslb",
						"zhcp_qspbb", "zhcp_cjhdb", "zhcp_cflb", "zhcp_zyjslb",
						"zhcp_tyjslb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)) {
				// �б���ѧ
				pageCard = new String[] { "��ѧ��", "�����ƺ�", "����������Ŀά��",
						"�������ʾ�����Ŀά��", "�������ʾ�����Ŀά��" };
				tabNames = new String[] { "jxjdmb", "rychdmb", "zhcp_tyjslb",
						"zhcp_zyjslb", "zhcp_cxlb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)) {
				// ������ҵ��ѧ
				pageCard = new String[] { "��ѧ�����", "��ѧ��" };
				tabNames = new String[] { "pjpylbwhb", "jxjdmb_tmp_bjly" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
				// �����ڶ�����ѧԺ
				pageCard = new String[] { "��ѧ��", "�����ƺ�" };
				tabNames = new String[] { "jxjdmb", "rychdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {// ����ѧУ�õ��ļ�������������jtrydmb
				// ����ְҵ����ѧԺ
				pageCard = new String[] { "��ѧ��", "�����ƺ�", "��������","��ѵ����" };
				tabNames = new String[] { "jxjdmb", "rychdmb", "pjpy_jtrydmb","jxjxdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
				// ���ս�����ҵѧԺ
				pageCard = new String[] { "��ѧ��", "�Ƚ�����", "�Ƚ�����", "��ѵ����",
						"ѧϰ������Ŀ" };
				tabNames = new String[] { "jxjdmb", "pjpy_jtrydmb", "rychdmb",
						"jxjxdmb", "xxjsdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)) {
				// ����ɽ��ѧ
				pageCard = new String[] { "��ѧ��", "רҵ��ѧ��", "�����ƺ�", "��ѵ����",
						"��������" };
				tabNames = new String[] { "jxjdmb", "zyjxjdmb", "rychdmb",
						"jxjxdmb", "pjpy_jtrydmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				pageCard = new String[] { "��ѧ��", "��ѧ�����",  "�����ƺ�", "��ѵ����", "�ȼ�����" };
				tabNames = new String[] { "jxjdmb", "jxjlbdmb", "rychdmb", "jxjxdmb",
						"djksdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
				// �人����ѧ
				pageCard = new String[] { "��ѧ��", "�����ƺ�", "�Ƚ��༶���", "��ѵ����" };
				tabNames = new String[] { "jxjdmb", "rychdmb", "xjbjlbdmb",
						"jxjxdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)) {
				pageCard = new String[] { "��ѧ��", "�����ƺ�", "��ѵ����" ,"��ѧ�����"};
				tabNames = new String[] { "jxjdmb", "rychdmb", "jxjxdmb", "jxjlbdmb" };				
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)) {//�Ϻ�����ӡˢ
				pageCard = new String[] { "��ѧ��", "�����ƺ�", "��ѵ����" ,"��ѧ�����"};
				tabNames = new String[] { "jxjdmb", "rychdmb", "jxjxdmb", "jxjlbdmb" };
			} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
				pageCard = new String[] { "��ѧ��", "�����ƺ�", "��ѵ����", "�������" };
				tabNames = new String[] { "jxjdmb", "rychdmb", "jxjxdmb" , "tydbdmb"};
			} else if (Globals.XXDM_ZJJJZYJSXY.equalsIgnoreCase(xxdm)) {
				pageCard = new String[] { "��ѧ��", "�����ƺ�", "��ѵ����", "�������˵ȼ�"};
				tabNames = new String[] { "jxjdmb", "rychdmb", "jxjxdmb", "dycjdmb" };
			} else if (Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm)) {
				pageCard = new String[] { "��ѧ��", "�����ƺ�", "��ѵ����","���ʵȼ�" };
				tabNames = new String[] { "jxjdmb", "rychdmb", "jxjxdmb","szdjdmb" };
			} else if (Globals.XXDM_SZGYYQZYJSXY.equalsIgnoreCase(xxdm)) {
				pageCard = new String[] { "��ѧ��", "�����ƺ�", "��ѵ����", "�ۺ����ʼӼ���ԭ��" };
				tabNames = new String[] { "jxjdmb", "rychdmb", "jxjxdmb", "szyc_jjfyyb" };
			} else if (Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)) {
				pageCard = new String[] { "��ѧ��", "�����ƺ�","����������Ŀ", "��ѵ����" };
				tabNames = new String[] { "jxjdmb", "rychdmb","dycxxmb", "jxjxdmb" };
			} else if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
				pageCard = new String[] { "��ѧ��","��ѧ�����", "�����ƺ�", "��ѵ����" };
				tabNames = new String[] { "jxjdmb", "jxjlbdmb","rychdmb", "jxjxdmb" };
			} else if (Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)) {//��ְҵ����ѧԺ
				pageCard = new String[] { "��ѧ��","��ѵ����","�ȼ����Է���" };
				tabNames = new String[] { "jxjdmb","jxjxdmb","jhzy_djksfsb" };		
			} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {//�㽭��ý
				pageCard = new String[] { "��ѧ�����", "��ѧ��", "�����ƺ�",
						"У�⽱ѧ������", "Υ������", "�������", "ѧ��������", "���δ���" , "�༶��������"};
				tabNames = new String[] { "jxjlbdmb", "jxjdmb", "rychdmb", "zjcm_xwjxjrs", "pjpy_xfjs_wjlxdmb","pjpy_xfjs_qjlxdmb",
						"pjpy_xfjs_jclxdmb", "pjpy_xfjs_kkcfszb" , "zjcm_wlkdmb"};	
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				pageCard = new String[] { "��ѧ��","�����ƺ�"};
				tabNames = new String[] { "jxjdmb","rychdmb"};
				request.setAttribute("showcsmz", "yes");
				request.setAttribute("xybmdm", xydm);
			}else if(Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {//��������ѧ
				pageCard = new String[] { "��ѧ�����","��ѧ��", "�����ƺ�" };
				tabNames = new String[] { "xmlg_jxjlbdmb","xmlg_jxjdmb", "xmlg_rychdmb"};				
			}else if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){//���ݴ�ѧ
				pageCard = new String[] { "��ѧ�����", "��ѧ��", "�����ƺ�", "��ѵ����",
						"��Ϊ���ַ���Ŀ", "ͻ�����ַ���Ŀ" };
				tabNames = new String[] { "jxjlbdmb", "jxjdmb", "rychdmb",
						"jxjxdmb", "xwbxfdmb", "tcbxfdmb" };				
			} else if (Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(xxdm)) {// �㽭��ְͨҵ����ѧԺ
				pageCard = new String[] { "��ѧ��������ά��", "��ѧ�����ά��", "�����ƺŴ���ά��",
						"�ۺ����ʲ�����������ά��", "��ѵ�������ά��", "���з�һ���������",
						"���зֶ���������", "���з��������ϸ�" };
				tabNames = new String[] { "jxjlbxxdmb", "jxjdmb", "rychdmb",
						"zhszcpdmb", "jxjxdmb", "zjjt_cxf_dj1", "zjjt_cxf_dj2",
						"zjjt_cxf_dj3" };		
			}  else if(Globals.XXDM_GHZYJSXY.equalsIgnoreCase(xxdm)){
				pageCard = new String[] { "��ѧ��������ά��", "��ѧ�����ά��", "�����ƺŴ���ά��",
						"�ۺ����ʲ�����������ά��", "��ѵ�������ά��", "����������","�༶ָ������" ,
						"�༶����������","�༶��������������","����ָ������","��������������","������������������ ",
						"br","�������־�����Ŀά��"};
				tabNames = new String[] { "jxjlbxxdmb", "jxjdmb", "rychdmb",
						"zhszcpdmb", "jxjxdmb", "wjdc_stssb" ,"bjzbdmb","bjryfszb","bjryfpcb",
						"qszbdmb","qsryfszb","qsryfpcb","br","ghxy_ryjfxmb"};		
			} else if(Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)){// ���ϴ�ѧ
				pageCard = new String[] { "��ѧ��������ά��", "��ѧ�����ά��", "�����ƺŴ���ά��",
					"�ۺ����ʲ�����������ά��", "��ѵ�������ά��","��չ��������Ŀά��",
						"����������Ŀά��"};
				tabNames = new String[] { "jxjlbxxdmb", "jxjdmb", "rychdmb",
						"zhszcpdmb", "jxjxdmb","hndx_fzxszxmb","hndx_dypyxmb"};		
			} else if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {//����ѧԺ
				pageCard = new String[] { "��ѧ��������ά��", "��ѧ�����ά��", "�����ƺŴ���ά��",
						"�ۺ����ʲ�����������ά��", "��ѵ�������ά��" ,"���������ƺ�ά��"};
				tabNames = new String[] { "jxjlbxxdmb", "jxjdmb", "rychdmb",
						"zhszcpdmb", "jxjxdmb","jtrychdmb"};				
			} else if (Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {
				pageCard = new String[] { "��ѧ��������ά��", "��ѧ�����ά��", "�����ƺŴ���ά��",
						"�۲����ά��", "����������"};
				tabNames = new String[] { "jxjlbxxdmb", "jxjdmb", "rychdmb",
						"zhszcpdmb", "wjdc_stssb"};		
			} else {
				pageCard = new String[] { "��ѧ��������ά��", "��ѧ�����ά��", "�����ƺŴ���ά��",
						"�۲����ά��"};
				tabNames = new String[] { "jxjlbxxdmb", "jxjdmb", "rychdmb",
						"zhszcpdmb"};				
			}
		} else if (codeType.equalsIgnoreCase("comm")) {
			tips = "���⽻��";
			pageCard = new String[] { "���⽻����ʽ", "���⽻�����", "���⽻����Ŀ" };
			tabNames = new String[] { "dwjlfsdmb", "dwjlfldmb", "dwjlxmdmb" };
		} else if (codeType.equalsIgnoreCase("assis")) {
			tips = "ѧ������";
			// pageCard = new String[] { "ѧУ��ѧ����", "NSEP����", "NSEP��Ŀ", "������ѧ��",
			// "���Ѳ���", "������Դ��Ŀ", "���ѳ̶�", "У����ѧ��", "֤������", "���ҽ�ѧ��ȼ�" };
			// tabNames = new String[] { "xxdxjlbdmb", "nsepjbdmb", "nsepxmdmb",
			// "wszxjdmb", "knbzdmb", "bjlydx_knssrly", "kncddmb",
			// "xnzxjdmb", "zmcldmb", "gjjxjdjdmb" };
			pageCard = new String[] { "������ѧ��","��ʱ����" };
			tabNames = new String[] { "wszxjdmb","lsknbzdmb" };
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)) {// ������ҵ��ѧ
				pageCard = new String[] { "ѧУ��ѧ����", "������ѧ��", "���Ѳ���", "������Դ��Ŀ" };
				tabNames = new String[] { "xxdxjlbdmb", "wszxjdmb", "knbzdmb",
						"bjlydx_knssrly" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)) {// ������Ϣְҵ����ѧԺ
				pageCard = new String[] { "ѧУ��ѧ����", "������ѧ��", "���Ѳ���", "���ѳ̶�",
						"У����ѧ��", "֤������", "���ҽ�ѧ��ȼ�" };
				tabNames = new String[] { "xxdxjlbdmb", "wszxjdmb", "knbzdmb",
						"kncddmb", "xnzxjdmb", "zmcldmb", "gjjxjdjdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)) {// ����ʦ��
				pageCard = new String[] { "ѧУ��ѧ����", "������ѧ��", "���Ѳ���", "�����ȼ�" };
				tabNames = new String[] { "xxdxjlbdmb", "wszxjdmb", "knbzdmb",
						"zzsf_xszzdjdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {// �㶫Ů��ְҵ����ѧԺ
				pageCard = new String[] { "��ѧ��", "��ѧ��", "���Ѳ���", "��������", "������" };
				tabNames = new String[] { "GdNZZY_xszz_jzjdmb",
						"GdNZZY_xszz_zxjdmb", "GdNZZY_xszz_knbzdmb",
						"gdnzzyxy_dklxdmb", "gdnzzyxy_dknlldmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {// ������Ϣְҵ����ѧԺ
				pageCard = new String[] { "ѧУ��ѧ����", "������ѧ��", "���Ѳ���", "������ѧ��ȼ�" };
				tabNames = new String[] { "xxdxjlbdmb", "wszxjdmb", "knbzdmb",
						"jsxx_gjzxjdjdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {// �㽭����ְҵ����ѧԺ
				pageCard = new String[] { "������ѧ��", "������ѧ��", "��ʱ���Ѳ���", "ѧ�Ѽ���" };
				tabNames = new String[] { "wszxjdmb", "zjjd_gjzxjdmb",
						"zjjd_lsbzdmb", "ZJJD_XFJMDMB" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {// �й���ҵ��ѧ
				pageCard = new String[] { "������Ⱥ" };
				tabNames = new String[] { "xszz_zgkd_tsrqdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {// �������ϴ�ѧ
				pageCard = new String[] { "������ѧ��", "������ѧ��", "��ʱ����" };
				tabNames = new String[] { "wszxjdmb", "xszz_bjlh_gjzxjdmb", "lsknbzdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBZYJSXY)) {// ����ְҵ����ѧԺ
				pageCard = new String[] { "������ѧ��", "����˽�ɫ" };
				tabNames = new String[] { "wszxjdmb", "nbzy_syjj_jkrjsdmb" };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {// ��������
				pageCard = new String[] { "������ѧ��","��ʱ����","��ѧ��ȼ�","���ѵȼ�" };
				tabNames = new String[] { "wszxjdmb","lsknbzdmb","ynys_zzdjb", "kncddmb" };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {// ��ְҵ����ѧԺ
				pageCard = new String[] { "������ѧ����ȼ�","������ѧ��ȼ�" };
				tabNames = new String[] { "xszz_jhzy_bkzxjjdmb","xszz_jhzy_gjzxjdmb" };
			}
			
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			sql = "select dmb,mc from xszz_n05_dmwhxmb";
			list.addAll(dao.getList(sql, new String[] {}, new String[] { "dmb", "mc" }));
			
			if (list.size() > 0) {
				pageCard = new String[list.size()];
				tabNames = new String[list.size()];
				int i = 0;
				for (HashMap<String, String> hs : list) {
					pageCard[i] = hs.get("mc");
					tabNames[i] = hs.get("dmb");
					i++;
				}
			}
		} else if (codeType.equalsIgnoreCase("zgdzdxJzxj")) {
			tips = "����ѧ��";
			pageCard = new String[] { "����ѧ�����" };
			tabNames = new String[] { "dzdxZxjlbb" };
			
		} else if (codeType.equalsIgnoreCase("work")) {
			tips = "�ڹ���ѧ";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)) {
				// �Ϻ����� �������� ����ʦ��
				pageCard = new String[] { "��λ����", "���˵�λ", "�ڹ���ѧʱ��", "У��", "�Ƴ귽ʽ" };
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "kcjsjdmb",
						"dm_zju_xq", "qgzx_jcfsdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				// �������ϴ�ѧ
				pageCard = new String[] { "��λ����", "���˵�λ", "��λ��Ϣ", "�Ƴ귽ʽ" };
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "gwdmb", "qgzx_jcfsdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
				// ������Ϣְҵ����ѧԺ
				pageCard = new String[] { "��λ����", "���˵�λ", "У��", "�Ƴ귽ʽ" };
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "dm_zju_xq", "qgzx_jcfsdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				// ����Ƽ�ѧԺ ��ɳ����ѧԺ
				pageCard = new String[] { "��λ����", "��λ����", "���˵�λ", "�Ƴ귽ʽ"};
				tabNames = new String[] { "gwmcdmb", "gwxzdmb", "yrdwdmb", "qgzx_jcfsdmb"};
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
				// �人����ѧ
				pageCard = new String[] { "��λ����", "���˵�λ", "��λ���ö�", "�Ƴ귽ʽ"};
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "xyddmb", "qgzx_jcfsdmb" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
				//�㽭����ְҵ����ѧԺ
				pageCard = new String[] { "��λ����", "���˵�λ", "�ڹ���ѧʱ��", "�Ƴ귽ʽ" };
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "kcjsjdmb","qgzx_jcfsdmb" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDXHXXY)){
				//�人����ѧ����ѧԺ
				pageCard = new String[] { "��λ����", "�ù�����", "�Ƴ귽ʽ"};
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "qgzx_jcfsdmb" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_CZXXZYJSXY)){
				//������Ϣְҵ����ѧԺ
				pageCard = new String[] { "��λ����", "���˵�λ", "��������", "�Ƴ귽ʽ"};
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "czxx_yhdmb", "qgzx_jcfsdmb"};
			} else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				//�й����ʴ�ѧ
				pageCard = new String[] { "��λ����", "���˵�λ", "�Ƴ귽ʽ", "ְλ����"};
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "qgzx_jcfsdmb", "qgzx_zwxzdmb"};
			}else {
				pageCard = new String[] { "��λ����", "���˵�λ", "�Ƴ귽ʽ"};
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "qgzx_jcfsdmb"};
			}
		} else if (codeType.equalsIgnoreCase("health")) {
			tips = "������";
			pageCard = new String[] { "���������Ŀ", "������Ŀ�����", "��Ҫ�ر����ѧ�����", "������������",
					"�����Ѷȼ���", "����������","������Ŀ����","���ض��˸������������","�Ծ��ܷ�����","����������","������������", "��ѧ���˸������������"};
			tabNames = new String[] { "xlcsxmdmb", "xlcsjgdmb", "tbgxxslbdmb",
					"stlxdmb", "stndjbdmb", "xljk_stsslbdmb","csxmyzb","zcbzpyb","sjzfpyb","xlcslbdmb","xlwtlxdmb", "rgysyzdmb"};
		} else if (codeType.equalsIgnoreCase("train")) {
			tips = "��ѵ����";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
				pageCard = new String[] { "��ѵ�����ͱ�", "���Ь�����", "��ѵñͷΧ��",
						"�Ļ����ߴ��", "��ѵ����"};
				tabNames = new String[] { "zxfhxb", "jfxcmb", "zxmtwb",
						"whsccb", "jxjxdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				// ������
				pageCard = new String[] { "��ѵ�����ͱ�", "���Ь�����", "��ѵñͷΧ��",
						"�Ļ����ߴ��", "����״̬", "ѧ����ѵ�����", "��ѵ���Ƶȼ�", "��ѵ��λ", "��ѵְλ",
						"��ѵ����ְλ", "��ѵ��װ" };
				tabNames = new String[] { "zxfhxb", "jfxcmb", "zxmtwb",
						"whsccb", "jxkqztb", "jxhjlbb", "jxjzdj","nblg_jxgl_jxdw",
						"nblg_jxgl_jxzw", "nblg_jxgl_bzzwb", "nblg_jxgl_jxfz" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
				// ������ѧԺ
				pageCard = new String[] { "��ѵ�����ͱ�", "���Ь�����", "��ѵñͷΧ��",
						"�Ļ����ߴ��", "����״̬", "ѧ����ѵ�����", "��ѵ���Ƶȼ�", "��ѵ��λ", "��ѵְλ",
						"��ѵ����ְλ" };
				tabNames = new String[] { "zxfhxb", "jfxcmb", "zxmtwb",
						"whsccb", "jxkqztb", "jxhjlbb", "jxjzdj","nblg_jxgl_jxdw",
						"nblg_jxgl_jxzw", "nblg_jxgl_bzzwb" };
			} else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				//�й����ʴ�ѧ
				pageCard = new String[] { "��ѵ�����ͱ�", "���Ь�����", "��ѵñͷΧ��",
						"�Ļ����ߴ��", "����״̬", "��ѵ���Ƶȼ�","��ѵ����ְλ","����������" };
				tabNames = new String[] { "zxfhxb", "jfxcmb", "zxmtwb",
						"whsccb", "jxkqztb", "jxjzdj","nblg_jxgl_bzzwb","zgdd_gfsldb" };
			} else if (Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)) {
				//��ְҵ
				pageCard = new String[] { "��ѵ�����ͱ�", "���Ь�����", "��ѵñͷΧ��",
						"�Ļ����ߴ��", "����״̬", "��֯�쵼ְ���"  };
				tabNames = new String[] { "zxfhxb", "jfxcmb", "zxmtwb",
						"whsccb", "jxkqztb",  "jhzy_zwb" };
			} else if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {
				// ���ݴ�ѧ
				pageCard = new String[] { "��ѵ�����ͱ�", "���Ь�����", "��ѵñͷΧ��",
						"�Ļ����ߴ��", "����״̬", "��ѵ���Ƶȼ�", "��ѵ����ְλ", "������������", "�⻺ѵ����" };
				tabNames = new String[] { "zxfhxb", "jfxcmb", "zxmtwb",
						"whsccb", "jxkqztb", "jxjzdj", "nblg_jxgl_bzzwb",
						"jxgl_rwzblxb", "jxgl_mhxlxb" };
			} else {
				pageCard = new String[] { "��ѵ�����ͱ�", "���Ь�����", "��ѵñͷΧ��",
						"�Ļ����ߴ��", "����״̬", "��ѵ���Ƶȼ�", "��ѵ����ְλ" };
				tabNames = new String[] { "zxfhxb", "jfxcmb", "zxmtwb",
						"whsccb", "jxkqztb", "jxjzdj", "nblg_jxgl_bzzwb" };
			}
		} else if (codeType.equalsIgnoreCase("discip")) {
			tips = "Υ�ʹ���";
			pageCard = new String[] { "�������", "����ԭ��" };
			tabNames = new String[] { "cflbdmb", "cfyydmb" };
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				pageCard = new String[] { "�������", "����ԭ��", "����Υ�ʹ������", "����Υ�ʹ���ԭ��" };
				tabNames = new String[] { "cflbdmb", "cfyydmb", "ks_cflbdmb", "ks_cfyydmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
				pageCard = new String[] { "�������", "����ԭ��", "���ֿ۷�" };
				tabNames = new String[] { "cflbdmb", "cfyydmb", "zjlg_wjkfb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {
				pageCard = new String[] { "�������", "����ԭ��", "���ֿ۷�" };
				tabNames = new String[] { "cflbdmb", "cfyydmb", "zjlg_wjkfb" };
			}
		} else if (codeType.equalsIgnoreCase("other")) {
			tips = "��������";
			pageCard = new String[] { "���չ�˾", "��������" };
			tabNames = new String[] { "bxgsdmb", "bxxzb" };
			if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {
				pageCard = new String[] { "���չ�˾", "��������", "���յ���" };
				tabNames = new String[] { "bxgsdmb", "bxxzb", "gdnzzy_bxdcdmb" };
			}
		} else if (codeType.equalsIgnoreCase("social")) {
			tips = "��Ṥ��";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				pageCard = new String[] { "��ְ����", "ѧ������", "�������", "ѧ���ɲ���ѵ��Ŀ",
						"�����������" };
				tabNames = new String[] { "rzbmdmb", "stdmb", "shhdxzdmb",
						"xsgbpxxmdmb", "stsqlbb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				pageCard = new String[] { "��ְ����", "ѧ������", "�����������", "����������Ŀ" };
				tabNames = new String[] { "rzbmdmb", "stdmb", "stsqlbb",
						"stpyxmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				pageCard = new String[] { "ѧ����֯������Ŀ", "ѧ����֯�ɲ�����" };
				tabNames = new String[] { "nblg_sskmdmb", "nblg_xsgbzldmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
				pageCard = new String[] { "��ְ����", "ѧ������", "�������", "ѧ���ɲ���ѵ��Ŀ",
						"�����������", "����������Ŀ", "���Ϣ" };
				tabNames = new String[] { "rzbmdmb", "stdmb", "shhdxzdmb",
						"xsgbpxxmdmb", "stsqlbb", "stpyxmb", "zgdd_shgz_hdb" };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
				pageCard = new String[] { "������֯", "����ְ��"};
				tabNames = new String[] { "sszzdmb", "srzwdmb"};
			}else if(Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)){
				pageCard = new String[] { "��ְ����", "ѧ������", "�Ŷ�����", "ѧ���ɲ���ѵ��Ŀ",
						"�����������", "����������Ŀ" };
				tabNames = new String[] { "rzbmdmb", "stdmb", "shhdxzdmb",
						"xsgbpxxmdmb", "stsqlbb", "stpyxmb" };				
			}else {
				pageCard = new String[] { "��ְ����", "ѧ������", "ѧ���ɲ���ѵ��Ŀ",
						"�����������", "����������Ŀ" };
				tabNames = new String[] { "rzbmdmb", "stdmb", 
						"xsgbpxxmdmb", "stsqlbb", "stpyxmb" };
			}
		} else if (codeType.equalsIgnoreCase("dorm")) {
			tips = "��Ԣ����";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				pageCard = new String[] { "����¥��", "ֵ����Ա", "������鲿��", "��Ԣά����Ա",
						"�������", "��ס����" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY)) {
				pageCard = new String[] { "����¥��", "ֵ����Ա", "������鲿��", "��Ԣά����Ա",
						"�������", "��������", "��ס����" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "gygl_wjsydmb", "wzlxdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				pageCard = new String[] { "����¥��", "ֵ����Ա", "������鲿��", "��Ԣά����Ա",
						"�������", "ѧ������", "ѧ���ͷ�", "�����������", "��ס����" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "xsjldmb", "xscfdmb",
						"qslbdmb", "wzlxdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				pageCard = new String[] { "����¥��", "ֵ����Ա", "������鲿��", "��Ԣά����Ա",
						"�������", "��ס����" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb" };
			} else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)){
				pageCard = new String[] { "����¥��",  "��Ԣά����Ա",
						"�������", "��ס����" };
				tabNames = new String[] { "sslddmb","gywxryb", "gygl_zswjlbdmb", "wzlxdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {//�й����ʴ�ѧ
				pageCard = new String[] { "԰��","����¥��", "�������","��ס����","�������ȼ�","�����������" };
				tabNames = new String[] { "yqdmb","sslddmb", "gygl_zswjlbdmb", "wzlxdmb","zgdd_wsjcdj","qslbdmb" };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
				pageCard = new String[] { "����¥��", "ֵ����Ա", "�����������","������鲿��", "��Ԣά����Ա",
						"�������", "��ס����" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "qslbdmb","gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb" };
			}else if(Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)){
				pageCard = new String[] { "����¥��", "ֵ����Ա", "�����������(���ȵȼ�)",
						"������鲿��", "��Ԣά����Ա", "�������", "��ס����", "�������ȼ�",
						"ѧ������", "ѧ���ͷ�", "���ֵȼ�" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "qslbdmb",
						"gywsjcbmb", "gywxryb", "gygl_zswjlbdmb", "wzlxdmb",
						"hhgxy_wsjcdj", "xsjldmb", "xscfdmb",
						"hhgxy_cfdj" };
			}else if(Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)){
				pageCard = new String[] { "����¥��", "ֵ����Ա", "������鲿��", 
						"�������", "��ס����","ά������" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gygl_zswjlbdmb", "wzlxdmb","wxnrdmb" };
			}else if(Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)){
				pageCard = new String[] { "����¥��", "ֵ����Ա", "������鲿��", "��Ԣά����Ա",
						"�������", "��ס����","���������ȼ�"};
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb","nwwsdjdmb" };
			}else if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)){
				pageCard = new String[] { "����¥��", "ֵ����Ա", "������鲿��", "��Ԣά����Ա",
						"�������", "��ס����", "�ԹܻᲿ��","���ʵ���","�������˶���","��Ԣ֧��" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb", "zghbmxxb","jhzy_dgldqb","gygl_khdxb","gyzbb" };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)) {
				pageCard = new String[] { "��Ԣ��","����¥��", "ֵ����Ա", "������鲿��", "��Ԣά����Ա",
						"�������", "��ס����" };
				tabNames = new String[] { "yqdmb","sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb"};
			} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// �㽭��ý
				pageCard = new String[] { "����¥��", "ֵ����Ա", "������鲿��", "��Ԣά����Ա",
						"�������", "��ס����", "ס�޼��ɴ�������", "�������ȼ�" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb", "zsjlcllxb",
						"zjcm_wsjcdjb" };
			}else {
				pageCard = new String[] { "����¥��", "ֵ����Ա", "������鲿��", "��Ԣά����Ա",
						"�������", "��ס����","�������ȼ�","��Ԣ��"};
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb","zjcm_wsjcdjb","yqdmb"};
		    }
		} else if (codeType.equalsIgnoreCase("dailyAffair")) {
			tips = "�ճ�����";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				pageCard = new String[] { "������", "��վ�б�", "Ƿ������", "��Ϊ",
						"�ռٱ���ѧ�� ", "�ռٱ���ѧ��", "����", "Ʒ�±���" };
				tabNames = new String[] { "jbrxxb", "czdmb", "qflxdmb",
						"rcgl_dmwhb" + "!!rcgl_rcxw",
						"rcgl_dmwhb" + "!!rcgl_xn", "rcgl_dmwhb" + "!!rcgl_xq",
						"rcgl_dmwhb" + "!!rcgl_xscx",
						"rcgl_dmwhb" + "!!rcgl_xspd" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NBCSZYJSXY)){//��������ְҵ����ѧԺ
				pageCard = new String[] { "������", "��վ�б�", "Ƿ������", "����ԭ������","ԤԼ����","ԤԼ�豸","ѧ��ָ���������Ĳ���","�������" };
				tabNames = new String[] { "jbrxxb", "czdmb", "qflxdmb",
						"bbyydmb","cdyyb","sbyyb","xslfslbmdmb","kqgl_xskqdmb"};				
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GUIZHDX)){
				pageCard = new String[] { "������", "��վ�б�", "Ƿ������", "����ԭ������",
						"ԤԼ����", "ԤԼ�豸", "��������", "��������", "��Ŀ����", 
						"ʵ�﷢������" ,"��������", "ѧ��֤��������"};
				tabNames = new String[] { "jbrxxb", "czdmb", "qflxdmb",
						"bbyydmb", "cdyyb", "sbyyb", "rcsw_lylxb",
						"rcsw_lypjb", "rcsw_zxzm_xmdmb", "rcsw_swfflxb","rcsw_cxlx", "xszbblxdmb" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_CSDLZYJSXY)){//��ɳ����
				pageCard = new String[] { "������", "��վ�б�", "Ƿ������", "����ԭ������",
						"ԤԼ����", "ԤԼ�豸", "��������", "��������", "��Ŀ����", "ʵ�﷢������" ,"��������","���ογ�"};
				tabNames = new String[] { "jbrxxb", "czdmb", "qflxdmb",
						"bbyydmb", "cdyyb", "sbyyb", "rcsw_lylxb",
						"rcsw_lypjb", "rcsw_zxzm_xmdmb", "rcsw_swfflxb","rcsw_cxlx","bks_kcdm"};
			}else {
				pageCard = new String[] { "������", "��վ�б�", "Ƿ������", "����ԭ������",
						"ԤԼ����", "ԤԼ�豸", "��������", "��������", "��Ŀ����", "ʵ�﷢������" ,"��������"};
				tabNames = new String[] { "jbrxxb", "czdmb", "qflxdmb",
						"bbyydmb", "cdyyb", "sbyyb", "rcsw_lylxb",
						"rcsw_lypjb", "rcsw_zxzm_xmdmb", "rcsw_swfflxb","rcsw_cxlx"};
			}
		} else if (codeType.equalsIgnoreCase("fdyxx")) {
			tips = "˼������";
			if (xxdm.endsWith(Globals.XXDM_XBEMY)) {                //��������Ժ
				pageCard = new String[] { "ְ���б�", "��ɲ�����", "˼�����˵ȼ�","����ָ�����","����Ⱥ��" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "szdwkhdjdmb","szdw_fdykhstdlb","szdw_fdykhqzb" };
			} else if (xxdm.endsWith(Globals.XXDM_AHJZGYXY)) {      //���ս�����ҵѧԺ
				pageCard = new String[] { "ְ���б�", "ѧ���ɲ�����", "ѧ���ɲ�������Ŀ",
						"ѧ���ɲ�������Ŀ", "ѧ���ɲ��ʻ�ά��","����ָ�����","����Ⱥ��" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","szdw_fdykhstdlb","szdw_fdykhqzb" };
			}else if (xxdm.endsWith(Globals.XXDM_WHLGDX)) {      //�人����ѧ
				pageCard = new String[] { "ְ���б�", "ѧ���ɲ�����", "ѧ���ɲ�������Ŀ",
						"ѧ���ɲ�������Ŀ", "ѧ���ɲ��ʻ�ά��","����Աѧ��ά��","����Աѧλά��",
						"����Ա���ѧλ����ѧ��ά��","����Ա���¹���ά��","����Աְ��ά��",
						"����Ա����ά��","����ָ�����","����Ⱥ��"};
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","fdyxlwhb","fdyxwwhb","fdyzhxlssxkb",
						"fdycsgzb","fdyzcb","fdyjbb","szdw_fdykhstdlb","szdw_fdykhqzb"};
			}else if (xxdm.endsWith(Globals.XXDM_NBLGXY)) {      //������
				pageCard = new String[] { "ְ���б�", "��Ա����","רҵ�����ȼ���","��ɲ�����", "ѧ���ɲ�������Ŀ",
						"ѧ���ɲ�������Ŀ", "ѧ���ɲ��ʻ���Ŀά��","����ָ�����","����Ⱥ��" };
				tabNames = new String[] { "nblg_szdwzwdmb", "nblg_szdwrylbb","nblg_szdwzyjsdjb","sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","szdw_fdykhstdlb","szdw_fdykhqzb" };
			}else if (xxdm.endsWith(Globals.XXDM_ZGDZDX)) {      //�й����ʴ�ѧ
				pageCard = new String[] { "ְ���б�", "��ɲ�����", "ѧ���ɲ�������Ŀ",
						"ѧ���ɲ�������Ŀ", "ѧ���ɲ��ʻ�ά��","ѧ���Ƽ��ɹ�����","�Ƽ��ɹ�����","����ָ�����","����Ⱥ��" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","fzjy_sbxskjcglxdmb","fzjy_cgjb","szdw_fdykhstdlb","szdw_fdykhqzb"};
			}else if (xxdm.endsWith(Globals.XXDM_ZGKYDX)) {      //�Ї��V�I��W
				pageCard = new String[] { "ְ���б�", "��ɲ�����", "ѧ���ɲ�������Ŀ",
						"ѧ���ɲ�������Ŀ", "ѧ���ɲ��ʻ�ά��","�ĵ��ļ������","����ָ������","����Ⱥ��" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","szdw_wdzlb","szdw_fdykhstdlb","szdw_fdykhqzb" };
			}else if(xxdm.endsWith(Globals.XXDM_YCWSZYJSXY)) {      //�γ�����ְҵ����ѧԺ
				pageCard = new String[] { "��ʦְ���б�", "��ɲ�����", "ѧ���ɲ�������Ŀ",
						"ѧ���ɲ�������Ŀ", "ѧ���ɲ��ʻ�ά��","����ָ�����","��������Ⱥ��","ѧ����ɲ�����" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","szdw_fdykhstdlb","szdw_fdykhqzb","sxjy_xshgbzlb" };
			}else if(xxdm.endsWith(Globals.XXDM_JHZYJSXY)) {      //��ְҵ����ѧԺ
				pageCard = new String[] { "��ʦְ���б�", "��ɲ�����", "ѧ���ɲ�������Ŀ",
						"ѧ���ɲ�������Ŀ", "ѧ���ɲ��ʻ�ά��","����ָ�����","��������Ⱥ��","ѧ���ܻ�ɲ�����",Base.YXPZXY_KEY+"ѧ����ɲ�","������ѧ����ɲ�����" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","szdw_fdykhstdlb","szdw_fdykhqzb","sxjy_xshgbzlb","sxjy_xygbzlb","sxjy_xljkgbzlb" };
			} else if (xxdm.endsWith(Globals.XXDM_XMLGXY)) { // ������ѧԺ
				pageCard = new String[] { "ְ���б�", "��ɲ�����", "ѧ���ɲ�������Ŀ",
						"ѧ���ɲ�������Ŀ", "ѧ���ɲ��ʻ�ά��", "����ָ�����", "����Ⱥ��", "��ȫ��������",
						"��ȫ��������", "�ܽ�����", "������������", "����������Ŀ����", "����������Ŀ����" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb", "szdw_fdykhstdlb",
						"szdw_fdykhqzb", "xmlg_szdw_aqbglx",
						"xmlg_szdw_aqbgmc", "xmlg_szdw_jhzjlx",
						"xmlg_szdw_gzalfl", "xmlg_szdw_gzallx",
						"xmlg_szdw_gzjylx" };
			}else if(xxdm.endsWith(Globals.XXDM_ZJLG)) {      //�㽭��
				pageCard = new String[] { "ְ���б�", "��ɲ�����", "ѧ���ɲ�������Ŀ",
						"ѧ���ɲ�������Ŀ", "ѧ���ɲ��ʻ�ά��","����ָ�����","����Ⱥ��","�ֹ�����","�������" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","szdw_fdykhstdlb","szdw_fdykhqzb","szdw_fglxdmb","fdydwlbdmb" };
			}else {
				pageCard = new String[] { "ְ���б�", "��ɲ�����", "��ɲ����༶��","ѧ���ɲ�������Ŀ",
						"ѧ���ɲ�������Ŀ", "ѧ���ɲ��ʻ�ά��","����ָ�����","����Ⱥ��" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb","bjgbzljbb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","szdw_fdykhstdlb","szdw_fdykhqzb" };
			}
		} else if (codeType.equalsIgnoreCase("hzjy")) {
			tips = "��������";
			pageCard = new String[] { "����������Ŀ", Base.YXPZXY_KEY+"ʱ�����ñ�" };
			tabNames = new String[] { "hzjyxmb", "hzjy_xysjszb" };
		} else if (codeType.equalsIgnoreCase("sztz")) {
			tips = "������չ";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {// ���������ѧ
				pageCard = new String[] { "��չ�༶" };
				tabNames = new String[] { "sztz_bjdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)) {// ������Ϣְҵ����ѧԺ,����ѧԺ
				pageCard = new String[] { "��Ŀ����", "��չ��Ŀ", "�����걨����", "�������" };
				tabNames = new String[] { "sztz_kmdmb", "sztz_xmdmb",
						"sztz_sqsblyb", "sztz_hjjbdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {
				// ==============begin luojw 2009/6/4=================
				pageCard = new String[] { "��Ŀ����", "�����", "���������", "�����˵�����" };
				tabNames = new String[] { "sztz_kmdmb", "zgkd_hdnrb",
						"zgkd_hdnrsxb", "zgkd_xlcdsxb" };
				// ==============end luojw 2009/6/4=================
			} else if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){//����ְҵ����ѧԺ
				tips = "�ڶ����û";
				pageCard = new String[] { "��Ŀ����"};
				tabNames = new String[] { "sztz_kmdmb"};
			}
			else {
				pageCard = new String[] { "��Ŀ����"};
				tabNames = new String[] { "sztz_kmdmb"};
			}
		} else if (codeType.equalsIgnoreCase("student")) {
			tips = "ѧ����Ϣ";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				// �Ϻ�����
				pageCard = new String[] { "ѧ��״̬", "�鵵����", "�춯���"};
				tabNames = new String[] { "dm_zju_xjzt","stu_gdzlb", "dm_ydlb"};
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
				pageCard = new String[] { "ѧ��״̬","�������", "�鵵����", "�춯���"};
				tabNames = new String[] { "dm_zju_xjzt","xbemy_ddqkdmb", "stu_gdzlb", "dm_ydlb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				// ��������
				pageCard = new String[] { "ѧ��״̬","�춯���", "�ڽ���Ϣ", "�鵵����" };
				tabNames = new String[] { "dm_zju_xjzt","dm_ydlb", "zjxxb" ,"stu_gdzlb"};
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				// ��ɳ����
				pageCard = new String[] { "ѧ��״̬","��ҵ֤����״̬", "�춯���","�鵵����" };
				tabNames = new String[] { "dm_zju_xjzt","byzffztdmb", "dm_ydlb","stu_gdzlb"};
			} else if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
				//�й����ʴ�ѧ
				pageCard = new String[] { "ѧ��״̬","�춯���","�鵵����","����","���"};
				tabNames = new String[] { "dm_zju_xjzt","dm_ydlb","stu_gdzlb","stu_lxdmb","stu_lbdmb"};
		    } else if(Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)){
		    	//���ս�����ҵѧԺ
				pageCard = new String[] { "ѧ��״̬","�춯���","�鵵����","��������"};
				tabNames = new String[] { "dm_zju_xjzt","dm_ydlb","stu_gdzlb","stu_lxdmb"};		    	
		    }else if(Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)){
		    	//�㽭��
				pageCard = new String[] { "ѧ��״̬","�춯���","�鵵����","������Ŀ"};
				tabNames = new String[] { "dm_zju_xjzt","dm_ydlb","stu_gdzlb","zjlg_dwjlxm"};		    	
		    }else if(Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)){
		    	//�人������ѧԺ
		    	pageCard = new String[] { "ѧ��״̬","����","�춯���","�鵵����"};
				tabNames = new String[] { "dm_zju_xjzt","dmk_yh", "dm_ydlb","stu_gdzlb"};
		    }else if(Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)){
		    	//����ѧԺ
		    	pageCard = new String[] { "ѧ��״̬","�춯���","�鵵����","�鵵�������"};
				tabNames = new String[] { "dm_zju_xjzt","dm_ydlb","stu_gdzlb","gdzllbb"};
		    } else if (Globals.XXDM_NJJS.equalsIgnoreCase(xxdm)) {
				// �Ͼ���ʦ
		    	// ------------2010/5/13 edit by luojw-------------
				pageCard = new String[] { "ѧ��״̬", "�춯���", "�鵵����", "����", "ѧ�����",
						"�������ʱ�","��ѧǰ�Ļ��̶�" };
				tabNames = new String[] { "dm_zju_xjzt", "dm_ydlb",
						"stu_gdzlb", "dmk_yh", "dtjs_xsccb", "xsxx_hkxzb","xsxx_rxqwhcdb" };
				// ------------end-------------
			} else if(Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)){
				//���ݴ�ѧ
				//------------2010/9/28 edit by qlj-------------
				pageCard = new String[] { "ѧ��״̬", "�춯���", "�鵵����", "����","�����Ǽ�" };
				tabNames = new String[] { "dm_zju_xjzt", "dm_ydlb",
						"stu_gdzlb", "dmk_yh","xsxx_daxxdj"};
				// ------------end-------------
			}else {
				pageCard = new String[] { "ѧ��״̬", "�춯���", "�鵵����", "����" };
				tabNames = new String[] { "dm_zju_xjzt", "dm_ydlb",
						"stu_gdzlb", "dmk_yh" };
			}
		} else if (codeType.equalsIgnoreCase("jygl")) {
			
			tips = "��ҵ����";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJJJGLZYXY)) {
				pageCard = new String[] { "��ְרҵ", "��ְ������ʽ",
						"��ҵȥ��", "��������", "��λ���ڵ���", "��ס֤�������־λ", "��Դ�������ܲ���", "��λ����",
						"��λ����2", "��ҵ����", "��Դ����", "�Ա�", "ѧ��", "ѧУ", "���ܲ���", "������ò" };
				tabNames = new String[] { "dmk_bzgzzy", "dmk_bzpyfs",
						"dmk_byqx", "dmk_dqlx", "dmk_dwdq",
						"dmk_jzzhlbbzw", "dmk_sydzgbm", "dmk_dwxz", "dmk_dwxz2",
						"dmk_hyfl", "dmk_sydq", "dmk_xb", "dmk_xl", "dmk_xx",
						"dmk_zgbm", "dmk_zzmm"};
			} else if(Globals.XXDM_ZGDZDX.equals(xxdm)) {
				pageCard = new String[] {"���˵�λ","��λ���ڵ�" };
				tabNames = new String[] {"dmk_zgdd_yrdw","dmk_dwdq"};
			}else{
				pageCard = new String[] { "��ר��ְרҵ", "�о���רҵ", "��ר������ʽ",
						"�о���������ʽ", "��ҵȥ��", "��������", "��λ����", "��ס֤�������־λ",
						"��Դ�����ܲ���", "��λ����", "��λ����2", "��ҵ����", "��Դ����", "�Ա�", "ѧ��",
						"ѧУ", "���ܲ���", "������ò", "���ʽ", "ѧ�����", "�������ҵ��רҵ������ձ�","���˵�λ" };
				tabNames = new String[] { "dmk_bzgzzy", "dmk_yjszy",
						"dmk_bzpyfs", "dmk_yjspyfs", "dmk_byqx", "dmk_dqlx",
						"dmk_dwdq", "dmk_jzzhlbbzw", "dmk_sydzgbm", "dmk_dwxz",
						"dmk_dwxz2", "dmk_hyfl", "dmk_sydq", "dmk_xb",
						"dmk_xl", "dmk_xx", "dmk_zgbm", "dmk_zzmm",
						"jycjh_hdxs", "jygl_xsccb", "dmk_zydmdzb","dmk_zgdd_yrdw" };
			}
		}  else if (codeType.equalsIgnoreCase("twgz")) {
			tips = "��ί����";
			pageCard = new String[] { "�ڶ�������Ŀ", "�񽱼���", "�񽱵ȼ�", "��Ŀ������", "�ʱ��" };
			tabNames = new String[] { "twgz_xmdmb", "twgz_hjjbdmb",
					"twgz_hjdjdmb", "twgz_xmcyzdmb", "twgz_hdsjdmb" };
		} else if (codeType.equalsIgnoreCase("yxgl")) {
			tips = "ӭ�¹���";
			pageCard = new String[] { "�������", "ע�����" };
			tabNames = new String[] { "bdqkb", "zcqkb"};
		} else if (codeType.equalsIgnoreCase("xsh")) {

			tips = "ѧ����";

			// ����Э��
			pageCard = new String[] { "����ͨ�øɲ�", "���Ŷ��øɲ�" };
			tabNames = new String[] { "bjxh_sttygbb", "bjxh_stdygbb" };
			
		} else {
			codeForm.setErrMsg("��������ʶ��");
			return mapping.findForward("false");
		}
		if ((tName == null) || tName.equalsIgnoreCase("")) {
			tName = tabNames[0];
		}
		if (tName.indexOf("rcgl_dmwhb") != -1) {
			tTem = tName.replace("rcgl_dmwhb!!", "");
			tName = "rcgl_dmwhb";
		}
		
		sql = sysService.getQuerrySQL(tName,tTem);//��ȡ��ѯ��Ϣ�Ĳ�ѯ���
		tabTitle = dao.getColumnName(sql);
		// ��ȡҳ����ı�ͷid�����Ӣ��������
		tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
		if (tName.equalsIgnoreCase("view_yrdwdmb")) {
			tName = "yrdwdmb";
		}
		if("view_yrdwdmb".equalsIgnoreCase(tName) 
			   || "yrdwdmb".equalsIgnoreCase(tName)){
			tabTitle = new String[]{"�к�","YRDWDM" ,"YRDWMC", "LXR", "LXDH", "DWLB",
					                "SSXQ", "XYMC", "DLM", "YRDWDZ"};
			tabTitleCN = new String[]{"�к�","���˵�λ����","���˵�λ����","��ϵ��",
					                  "��ϵ�绰", "��λ���","����У��",Base.YXPZXY_KEY,
					                  "��¼��","���˵�λ��ַ"};
		}
		// if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){//�������ϴ�ѧ
		// if("yrdwdmb".equalsIgnoreCase(tName)){
		// tabTitleCN[4]="��������";
		// tabTitleCN[1]="���˵�λ";
		// }
		// if("sslddmb".equalsIgnoreCase(tName)){
		// tabTitleCN[3]="ѧԺ����";
		// }
		// }
		
		if("gywxryb".equalsIgnoreCase(tName)){
			if(Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)){
				tabTitleCN = new String[] { "�к�", "��Ա����", "��Ա����", "��ҵ��˾����" };
			}
		}
		
		if("zbrydmb".equalsIgnoreCase(tName)){
			tabTitle = new String[] { "�к�", "zbrydm", "zbrymc", "ldmc" };
			tabTitleCN = new String[] { "�к�", "ֵ����Ա����", "ֵ����Ա����", "¥������" };
		}
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJYS)) {
			if ("yrdwdmb".equalsIgnoreCase(tName)) {
				tabTitleCN[5] = "��������";
			}
		}
		if ("rcgl_dmwhb".equalsIgnoreCase(tName)) {
			tName += "!!" + tTem;
		}
		if (tName.equalsIgnoreCase("jxjdmb")) {
//			tabTitle = new String[] { "�к�", "jxjdm", "jxjmc", "jxjlb", "jxjjb",
//					"jlje", "szjdbz"};
//			tabTitleCN = new String[] { "�к�", "��ѧ�����", "��ѧ������", "��ѧ�����",
//					"��ѧ�𼶱�", "�������", "���ü����׼"};
			
			tabTitle = new String[] { "�к�", "jxjdm", "jxjmc", "jxjlb", "jxjjb",
					"jlje","sfqy"};
			tabTitleCN = new String[] { "�к�", "��ѧ�����", "��ѧ������", "��ѧ�����",
					"��ѧ�𼶱�", "�������","�Ƿ�����"};
			sql = "select rownum �к�,jxjdm,jxjmc,jxjlb,jxjjb,jlje,(case when sfqy='yes' then '��' else '��' end) sfqy from jxjdmb ";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
				tabTitle = new String[] { "�к�", "jxjdm", "jxjmc", "jxjlb",
						"jlje", "szjdbz","dkjd" };
				tabTitleCN = new String[] { "�к�", "��ѧ�����", "��ѧ������",
						"��ѧ�����", "�������", "����ƽ�������׼","���õ��Ƽ����׼" };
			} 
			if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDXHXXY)){
				//�人������ѧԺ
				sql = "select rownum �к�,jxjdm,jxjmc,jxjlb,jxjjb,jlje,szjdbz,jlf from jxjdmb ";
				tabTitle = new String[] { "�к�", "jxjdm", "jxjmc", "jxjlb", "jxjjb",
						"jlje", "szjdbz" ,"jlf"};
				tabTitleCN = new String[] { "�к�", "��ѧ�����", "��ѧ������", "��ѧ�����",
						"��ѧ�𼶱�", "�������", "���ü����׼", "������" };
			} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				sql = "select rownum �к�,jxjdm,jxjmc,jxjlb,jxjjb,jlje from jxjdmb";
				tabTitle = new String[] { "�к�", "jxjdm", "jxjmc", "jxjlb", "jxjjb",
						"jlje"};
				tabTitleCN = new String[] { "�к�", "��ѧ�����", "��ѧ������", "��ѧ�����",
						"��ѧ�𼶱�", "�������"};
			}
		}
		
		if (tName.equalsIgnoreCase("rychdmb")) {
			tabTitle = new String[] { "�к�", "rychdm", "rychmc" };
			tabTitleCN = new String[] { "�к�", "�����ƺŴ���", "�����ƺ�����" };
		}
		if (tName.equalsIgnoreCase("jxjxdmb")) {
			tabTitle = new String[] { "�к�", "jxdm", "jxmc" };
			tabTitleCN = new String[] { "�к�", "�������", "��������" };
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			HttpSession session = request.getSession();
			if (tName.equalsIgnoreCase("jxjdmb")) {
				tabTitle = new String[] { "xydm", "�к�", "jxjdm", "jxjmc",
						"jxjlb", "jxjjb", "jlje", "szjdbz", "sztzxfbz", "bmmc" ,"fbr"};
				tabTitleCN = new String[] { "xydm", "�к�", "��ѧ�����", "��ѧ������",
						"��ѧ�����", "��ѧ�𼶱�", "�������", "���óɼ�ѧ�ֱ�׼", "������չѧ�ֱ�׼" ,"���ڲ���", "������"};
				if (!StringUtils.isNull(userType) && StringUtils.isEqual(userType, "xy")) {
					sql += " where xydm='" + request.getSession().getAttribute("userDep").toString() + "' ";
					sql += " and fbr='";
					sql += session.getAttribute("userNameReal").toString();
					sql += "'";
					//sql += " and exists(select 1 from yhb b where b.yhm=a.fbr)";
				}
				request.setAttribute("iscsmz", "yes");
			}
			if (tName.equalsIgnoreCase("rychdmb")) {
				tabTitle = new String[] { "xydm", "�к�", "rychdm", "rychmc",
						"rychlb","bmmc","fbr" };
				tabTitleCN = new String[] { "xydm", "�к�", "�����ƺŴ���", "�����ƺ�����",
						"�����ƺ����" ,"���ڲ���", "������"};
				if (!StringUtils.isNull(userType) && StringUtils.isEqual(userType, "xy")) {
					sql += " where xydm='" + request.getSession().getAttribute("userDep").toString() + "' ";
					sql += " and fbr='";
					sql += session.getAttribute("userNameReal").toString();
					sql += "'";
				}
				request.setAttribute("iscsmz", "yes");
			}
			/*
			 * if (tName.equalsIgnoreCase("jxjxdmb")) { tabTitle = new
			 * String[]{"�к�", "jxdm", "jxmc"}; tabTitleCN = new String[]{ "�к�",
			 * "�������", "��������"}; }
			 */
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			if (tName.equalsIgnoreCase("jxjdmb")) {
				tabTitle = new String[] { "�к�", "jxjdm", "jxjmc",
						"jxjjb", "jlje", "jxjlb" };
				tabTitleCN = new String[] { "�к�", "��ѧ�����", "��ѧ������",
						"��ѧ�𼶱�", "�������", "��ѧ�����" };
			}
			if (tName.equalsIgnoreCase("rychdmb")) {
				tabTitle = new String[] { "�к�", "rychdm", "rychmc", "pxbl" };
				tabTitleCN = new String[] { "�к�", "�����ƺŴ���", "�����ƺ�����", "��ѡ����" };
			}
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
			if (tName.equalsIgnoreCase("rychdmb")) {
				sql = "select rownum �к�,a.rychdm,a.rychmc,(select xmzl from pjpy_bbdyljb where a.bdbblxdm = xmdm and xmlb = 'rych') bdbblxdm from rychdmb a";
				tabTitle = new String[] { "�к�", "rychdm", "rychmc","bdbblxdm"};
				tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
			}
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {// �人����ѧ
			if (tName.equalsIgnoreCase("jxjdmb")) {
				tabTitle = new String[] { "�к�", "jxjdm", "jxjmc", "jxjlb",
						"jxjjb", "jlje", "jxjfl" };
				tabTitleCN = new String[] { "�к�", "��ѧ�����", "��ѧ������", "��ѧ�����",
						"��ѧ�𼶱�", "�������", "��ѧ�����" };
			}
			if (tName.equalsIgnoreCase("rychdmb")) {
				tabTitle = new String[] { "�к�", "rychdm", "rychmc", "rychfl" };
				tabTitleCN = new String[] { "�к�", "�����ƺŴ���", "�����ƺ�����", "�����ƺŷ���" };
			}
		}
		
		if (tName.equalsIgnoreCase("jxjlbdmb")) {
			sql = "select rownum �к�,jxjlbdm,jxjlbmc,(case when jxjjd='1' then '��' else '��' end) jxjjd from jxjlbdmb ";
			if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				sql = "select rownum �к�,jxjlbdm,jxjlbmc from jxjlbdmb ";
				tabTitle = new String[]{"�к�", "jxjlbdm", "jxjlbmc"};
			}
			
		}
		
		// ��ȡҳ��������ı�ͷ���������������
		// vector.add(tabTitleCN);
		// �����������������������ͷ��
		List pageCard_tabNames = dao.arrayToList(tabNames, pageCard);
		// ��ѡ���ǩ�����Ӧ�ı����ϲ���һ��List
		List tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);
		// ��Ҫ��ʾ�ı��ı�ͷ�����Ӧ��Ӣ���ֶ����ϲ���һ��List
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			if (tName.equalsIgnoreCase("stu_gdzlb")) {
				sql = "select rownum �к�,a.gdzldm,a.zlbmc��from stu_gdzlb a";
			}
		}

		if (tName.equalsIgnoreCase("dm_ydlb")) {
			sql = "select rownum �к�,a.ydlbm,a.ydlbmc,replace(replace(nvl(a.xjzt,' '),'1','��'),'0','��') xjzt,a.sfzx from dm_ydlb a";
			tabTitle = new String[] { "�к�", "ydlbm", "ydlbmc", "xjzt", "sfzx" };
			tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
			tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);
		}
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY) || xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)) {
			if (tName.equalsIgnoreCase("jxjdmb")) {
				sql = "select rownum �к�,a.jxjdm,a.jxjmc,a.jxjjb,b.jxjlbmc jxjlb,a.jlje,a.szjdbz from jxjdmb a left join jxjlbdmb b on a.jxjlb=b.jxjlbdm";
			}
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
			if (tName.equalsIgnoreCase("jxjdmb")) {
				sql = "select rownum �к�,a.jxjdm,a.jxjmc,a.jxjjb,b.jxjlbmc jxjlb,a.jlje,(select xmzl from pjpy_bbdyljb where a.bdbblxdm = xmdm and xmlb = 'jxj' ) bdbblxdm from jxjdmb a left join jxjlbdmb b on a.jxjlb=b.jxjlbdm";
				tabTitle = new String[] { "�к�", "jxjdm", "jxjmc","jxjlb","jxjjb","jlje","bdbblxdm"};
				tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
				tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);		                
			}
		}
		if(!Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)&&"sslddmb".equalsIgnoreCase(tName)){
			sql = "select rownum �к�,lddm,ldmc,xqdm,xbxd,cs,yqdm from sslddmb ";
			tabTitle = new String[] { "�к�", "lddm", "ldmc", "xqdm", "xbxd","cs","yqdm" };
			tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
			tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);
		}
		if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)&&"jtrychdmb".equalsIgnoreCase(tName)){
			sql = "select rownum �к�,jtrychdm ,jtrychmc from jtrychdmb ";
			tabTitle = new String[] { "�к�", "jtrychdm","jtrychmc" };
			tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
			tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);
		}
		if("sztz_kmdmb".equalsIgnoreCase(tName)){
			if(Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm)){
				sql = "select rownum �к�,kmdm,kmm,(case when hgfs<1 then '0'||hgfs else to_char(hgfs) end ) hgfs,sm from sztz_kmdmb order by to_number(kmdm) ";
				tabTitle = new String[] { "�к�", "kmdm", "kmm","hgfs","sm" };
			}else{
				sql = "select rownum �к�,kmdm,kmm,(case when hgfs<1 then '0'||hgfs else to_char(hgfs) end ) hgfs from sztz_kmdmb order by to_number(kmdm)";
				tabTitle = new String[] { "�к�", "kmdm", "kmm","hgfs"};
			}
			tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
			tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);		
		}
		if("stu_gdzlb".equalsIgnoreCase(tName) && Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)){
			sql = "select rownum �к�,gdzldm ,zlbmc ,(select mc from gdzllbb where dm=a.zlblb) zlblb from stu_gdzlb a order by to_number(gdzldm)";
			tabTitle = new String[] { "�к�", "gdzldm", "zlbmc","zlblb"};
			tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
			tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);		
		}
		if (tName.equalsIgnoreCase("zcbzpyb")) {
			tabTitle = new String[] { "�к�", "dm", "yx", "yxmc", "bzf", "bzpy", "dj" };
			sql = "select rownum �к�,dm,yx,yxmc,bzf,bzpy,dj from zcbzpyb";
			tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
			tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);		
		}

		vector.addAll(dao.rsToVator(sql, new String[] {}, tabTitle));
		// ���ؽ����
		if (tName.equalsIgnoreCase("dwjlxmdmb")) {
			sql = "select * from dwjlfldmb";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "dwjllbdm", "dwjllbmc" });
			sql = "select * from dwjlfsdmb";
			toInitSel_2 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "dwjlfsdm", "dwjlfsmc" });
		}
		if (tName.equalsIgnoreCase("nsepxmdmb")) {
			sql = "select * from nsepjbdmb";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "NSEPJBDM", "NSEPJBMC" });
		}
		if (tName.equalsIgnoreCase("xlcsjgdmb")) {
			sql = "select * from xlcsxmdmb";

			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "xlcsxmdm", "xlcsxmmc" });
		}
		if (tName.equalsIgnoreCase("sjzfpyb")) {
			sql = "select * from sjb";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "sjlsh", "sjm" });
		}
		if (tName.equalsIgnoreCase("sslddmb")) {
			sql = "select * from dm_zju_xq";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "dm", "xqmc" });
			toInitSel_2 = "!!SplitSignOne!!!!SplitSignTwo!!��!!SplitSignTwo!!��!!SplitSignOne!!!!SplitSignTwo!!Ů!!SplitSignTwo!!Ů!!SplitSignOne!!!!SplitSignTwo!!���!!SplitSignTwo!!���";
		   
		    toInitSel_3=dao.getStringToSplit(" select yqdm,yqmc from yqdmb ",new String[]{}, new String[]{"yqdm","yqmc"});
		    
		}
		
		if (tName.equalsIgnoreCase("sxjy_bjgbzlb")) {
			sql = "select jbdm,jbmc from bjgbzljbb";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "jbdm","jbmc" });
		}		
		
		if("yqdmb".equalsIgnoreCase(tName)){
			sql = "select dm,xqmc from dm_zju_xq ";
			toInitSel_1 =  dao.getStringToSplit(sql, new String[] {},new String[] { "dm", "xqmc" });			
		}
		if (tName.equalsIgnoreCase("xsgbzhwhbb")) {
			toInitSel_1 = "!!SplitSignOne!!!!SplitSignTwo!!001!!SplitSignTwo!!���ֶ�!!SplitSignOne!!!!SplitSignTwo!!����!!SplitSignTwo!!����!!SplitSignOne!!!!SplitSignTwo!!�ı���!!SplitSignTwo!!�ı���";
		}
		if (tName.equalsIgnoreCase("yrdwdmb")) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				sql = "select * from bks_dwdmb";
				toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
						new String[] { "dwdm", "dwmc" });
				sql = "select yhm,xm from yhb";
				toInitSel_2 = dao.getStringToSplit(sql, new String[] {},
						new String[] { "yhm", "xm" });
				toInitSel_3 = "!!SplitSignOne!!!!SplitSignTwo!!У��!!SplitSignTwo!!У��!!SplitSignOne!!!!SplitSignTwo!!У��!!SplitSignTwo!!У��";
				sql = "select * from dm_zju_xq";
				toInitSel_4 = dao.getStringToSplit(sql, new String[] {},
						new String[] { "dm", "xqmc" });
				sql = "select distinct bmdm,bmmc from zxbz_xxbmdm";
				toInitSel_5 = dao.getStringToSplit(sql, new String[] {},
						new String[] { "bmdm", "bmmc" });
			} else {
				toInitSel_1 = "!!SplitSignOne!!!!SplitSignTwo!!У��!!SplitSignTwo!!У��!!SplitSignOne!!!!SplitSignTwo!!У��!!SplitSignTwo!!У��";
				sql = "select * from dm_zju_xq";
				toInitSel_2 = dao.getStringToSplit(sql, new String[] {},
						new String[] { "dm", "xqmc" });
				sql = "select distinct bmdm,bmmc from zxbz_xxbmdm order by bmmc";
				toInitSel_3 = dao.getStringToSplit(sql, new String[] {},
						new String[] { "bmdm", "bmmc" });
			}
		}
		if (tName.equalsIgnoreCase("zbrydmb")) {
			sql = "select lddm,ldmc from sslddmb order by  lddm ";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "lddm", "ldmc" });
		}
		if (tName.equalsIgnoreCase("bxxzb")) {
			sql = "select bxgsdm,bxgsmc from bxgsdmb";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "bxgsdm", "bxgsmc" });
			toInitSel_2 = "!!SplitSignOne!!!!SplitSignTwo!!��ѧ��!!SplitSignTwo!!��ѧ��!!SplitSignOne!!!!SplitSignTwo!!�Զ���!!SplitSignTwo!!�Զ���";
		}
		if (tName.equalsIgnoreCase("sztz_xmdmb")) {
			sql = "select kmdm,kmm from sztz_kmdmb";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "kmdm", "kmm" });
			sql = "select bmdm,bmmc from zxbz_xxbmdm";
			toInitSel_2 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "bmdm", "bmmc" });
			toInitSel_3 = dao.listToSplit(dao.getXnndList(),
					new String[] { "xn","xn" });
			sql = "select xqdm,xqmc from xqdzb ";
			toInitSel_4 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "xqdm", "xqmc" });
		}
		if(tName.equalsIgnoreCase("xmlg_jxjdmb")){
			sql = "select dm,mc from xmlg_jxjlbdmb order by dm nulls first";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "dm", "mc" });
			sql = "select dm,mc from (select '��' dm,'��'mc from dual union select '��'dm,'��'mc from dual )order by dm desc nulls first";
			toInitSel_2 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "dm", "mc" });
		}
		if (tName.equalsIgnoreCase("sztz_sqsblyb")
				|| tName.equalsIgnoreCase("sztz_hjjbdmb")) {
			sql = "select * from sztz_xmdmb";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "xmdm", "xmmc" });
		}
		//TODO �㶫���� ��Ա�춯״̬�� author ������ 2010-7-30
		if(tName.equalsIgnoreCase("gdby_dtjs_ydlxdmb")) {
			sql = "select * from gdby_dtjs_tyydzt";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "ydzt","ydzt" });
		}
		//TODO ������ʹ���� author ������ 2010-7-19
		if (tName.equalsIgnoreCase("kqgl_xskqdmb")) {
			sql = "select * from kqgl_xskqdmb";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "QJLXDM", "QJLXMC" });
		}
		if (tName.equalsIgnoreCase("sztz_bjdmb")) {
			toInitSel_1 = dao.listToString2(dao.getXnndList(), new String[] {
					"xn", "xn" });
			sql = "select xqdm,xqmc from xqdzb";
			toInitSel_2 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "xqdm", "xqmc" });
		}
		if (tName.equalsIgnoreCase("bjlydx_knssrly")) {
			sql = "select * from bjlydx_knssrly";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "dm", "mc" });
		}
		if (tName.equalsIgnoreCase("dm_ydlb")) {
			sql = "select distinct zxdmmc mc from dm_zju_xjzt";
			toInitSel_1 = dao.getStringToSplit(sql, new String[]{}, new String[]{"mc","mc"});
//			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
//				// �������ϴ�ѧ
//				toInitSel_1 = "!!SplitSignOne!!!!SplitSignTwo!!����!!SplitSignTwo!!����!!SplitSignOne!!!!SplitSignTwo!!����ѧ��!!SplitSignTwo!!����ѧ��"
//						+ "!!SplitSignOne!!!!SplitSignTwo!!��У!!SplitSignTwo!!��У";
//			}
			toInitSel_2 = "!!SplitSignOne!!!!SplitSignTwo!!��У!!SplitSignTwo!!��У!!SplitSignOne!!!!SplitSignTwo!!����У!!SplitSignTwo!!����У";
		}
		if (tName.equalsIgnoreCase("stu_gdzlb")) {
			toInitSel_1 = "!!SplitSignOne!!!!SplitSignTwo!!1!!SplitSignTwo!!��!!SplitSignOne!!!!SplitSignTwo!!0!!SplitSignTwo!!��";
		}
		//�������Ž�ѧ���������ʱѡ�������б������
		if (tName.equalsIgnoreCase("jxjdmb")) {
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG) ){
			sql = "select jxjlbdm,jxjlbmc from jxjlbdmb";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "jxjlbdm","jxjlbmc" });
			sql = "select xmdm,xmzl from pjpy_bbdyljb where xmlb = 'jxj' and xxdm = '10338' ";
			toInitSel_2 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "xmdm","xmzl" });
			} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm) || Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
				sql = "select jxjlbdm,jxjlbmc from jxjlbdmb";
				toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
						new String[] { "jxjlbdm","jxjlbmc" });
			} else{
				//��ע��ͨ�õ�
				//toInitSel_1 = "!!SplitSignOne!!!!SplitSignTwo!!У!!SplitSignTwo!!У!!SplitSignOne!!!!SplitSignTwo!!��!!SplitSignTwo!!��";
				
				//ͨ�õ�ȫ���ĳɴӽ�ѧ������ȡ����
				sql = "select dm,mc from jxjlbxxdmb";
				toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
						new String[] { "dm","mc" });
				
				toInitSel_2 = "!!SplitSignOne!!!!SplitSignTwo!!yes!!SplitSignTwo!!��!!SplitSignOne!!!!SplitSignTwo!!no!!SplitSignTwo!!��";
			}
		}
		if (tName.equalsIgnoreCase("dwjlxmdmb")) {
			sql = "select dwjllbdm,dwjllbmc from dwjlfldmb";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "dwjllbdm","dwjllbmc" });
			sql = "select dwjlfsdm,dwjlfsmc from dwjlfsdmb";
			toInitSel_2 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "dwjlfsdm","dwjlfsmc" });
		}
		//end
		if(tName.equalsIgnoreCase("cflbdmb")){
			toInitSel_1 = "!!SplitSignOne!!!!SplitSignTwo!!У��!!SplitSignTwo!!У��!!SplitSignOne!!!!SplitSignTwo!!Ժ��!!SplitSignTwo!!Ժ��";
		}
		if(tName.equalsIgnoreCase("rychdmb")&&xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)){
			sql = "select xmdm,xmzl from pjpy_bbdyljb where xmlb = 'rych' and xxdm = '10338'";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
			new String[] { "xmdm","xmzl" });
		}
		if (tName.equalsIgnoreCase("hzjy_xysjszb")) {
			sql = "select * from hzjy_xysjszb";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "xymc", "hzjykssj", "hzjyjssj" });
		}
		// =============begin luojw 2009/6/4 =======================
		if (tName.equalsIgnoreCase("zgkd_hdnrb")) {
			String[] colList = new String[] { "dm", "mc" };
			sql = "select kmdm dm,kmm mc from sztz_kmdmb order by kmdm";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {}, colList);
		}
		if (tName.equalsIgnoreCase("zgkd_hdnrsxb")) {
			String[] colList = new String[] { "dm", "mc" };
			sql = "select '00' dm, '' mc from dual union select kmdm dm, kmm mc from sztz_kmdmb order by dm";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {}, colList);
			sql = "select '00' dm, '' mc from dual union select hdnrdm dm, hdnrmc mc from zgkd_hdnrb order by dm";
			toInitSel_2 = dao.getStringToSplit(sql, new String[] {}, colList);
		}
		if (tName.equalsIgnoreCase("zgkd_xlcdsxb")) {
			String[] colList = new String[] { "dm", "mc" };
			sql = "select '00' dm, '' mc from dual union select kmdm dm, kmm mc from sztz_kmdmb order by dm";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {}, colList);
			sql = "select '00' dm, '' mc from dual union select hdnrdm dm, hdnrmc mc from zgkd_hdnrb order by dm";
			toInitSel_2 = dao.getStringToSplit(sql, new String[] {}, colList);
			sql = "select '00' dm, '' mc from dual union select sxdm dm, sxmc mc from zgkd_hdnrsxb order by dm";
			toInitSel_3 = dao.getStringToSplit(sql, new String[] {}, colList);
		}
		// =============end luojw 2009/6/4 =======================
		// =============begin luojw 2009/6/27 =======================
		if (tName.equalsIgnoreCase("nblg_jxgl_jxzw")) {
			String[] colList = new String[] { "dm", "mc" };
			sql = "select dwdm dm,dwmc mc from nblg_jxgl_jxdw order by dwdm";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {}, colList);
		}
		if (tName.equalsIgnoreCase("nblg_jxgl_bzzwb")) {
			String[] colList = new String[] { "dm", "mc" };
			sql = "select jzdm dm,jzmc mc from jxjzdj  order by jzdm";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {}, colList);
		}
		// =============end luojw 2009/6/27 =======================
		// =============begin luojw 2009/10/23 =======================
		if ("zjcm_xwjxjrs".equalsIgnoreCase(tName)) {
			String[] colList = new String[] { "dm", "mc" };
			sql = "select jxjdm dm,jxjmc mc from jxjdmb a where exists (select 1 from jxjlbdmb b where jxjlbmc like 'У��%' and a.jxjlb=b.jxjlbdm) order by dm";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {}, colList);
			
			int currentNd = Integer.parseInt(DealString.getDateTime()
					.substring(0, 4));
			String xn = "";
			ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
			String rsStr = "";
			String[] outputValue = new String[]{"xn","xn"};
			for (int i = currentNd - 5; i < currentNd + 5; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
				map.put("xn", xn);
				aList.add(map);
			}
			for (int i = 0; i < aList.size(); i++) {
				rsStr += "!!SplitSignOne!!";
				for (int j = 0; j < outputValue.length; j++) {
					if ((aList.get(i).get("xn") == null)
							|| aList.get(i).get("xn").equalsIgnoreCase("null")) {
						rsStr += ("!!SplitSignTwo!!" + " ");
					} else {
						rsStr += ("!!SplitSignTwo!!" + aList.get(i).get("xn"));
					}
				}
			}
			toInitSel_2 = rsStr;
			
			sql = "select xqdm dm,xqmc mc from xqdzb";
			toInitSel_3 = dao.getStringToSplit(sql, new String[] {}, colList);
		}
		// =============end luojw 2009/10/23 =======================
		// =============begin luojw 2009/9/8 =======================
		if (tName.equalsIgnoreCase("zjlg_wjkfb")) {
			String[] colList = new String[] { "dm", "mc" };
			sql = "select cflbdm dm,cflbmc mc from cflbdmb order by dm";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {}, colList);

			int currentNd = Integer.parseInt(DealString.getDateTime()
					.substring(0, 4));
			String xn = "";
			ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
			String rsStr = "";
			String[] outputValue = new String[]{"xn","xn"};
			for (int i = currentNd - 5; i < currentNd + 5; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
				map.put("xn", xn);
				aList.add(map);
			}
			for (int i = 0; i < aList.size(); i++) {
				rsStr += "!!SplitSignOne!!";
				for (int j = 0; j < outputValue.length; j++) {
					if ((aList.get(i).get("xn") == null)
							|| aList.get(i).get("xn").equalsIgnoreCase("null")) {
						rsStr += ("!!SplitSignTwo!!" + " ");
					} else {
						rsStr += ("!!SplitSignTwo!!" + aList.get(i).get("xn"));
					}
				}
			}
			toInitSel_2 = rsStr;
		}
		// =============end luojw 2009/9/8 =======================
		// =============begin luojw 2009/11/9 =======================
		if (tName.equalsIgnoreCase("zjlg_zbmc")) {
			String[] colList = new String[] { "dm", "mc" };
			sql = "select distinct(xydm) dm,xymc mc from view_njxyzybj order by dm";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {}, colList);
		}
		// =============end luojw 2009/11/9 =======================
		// =============begin luojw 2009/12/4 =======================
		if (tName.equalsIgnoreCase("xmlg_szdw_aqbgmc")) {
			String[] colList = new String[] { "dm", "mc" };
			sql = "select distinct(dm) dm,mc mc from xmlg_szdw_aqbglx order by dm";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {}, colList);
		}
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		if (tName.equalsIgnoreCase("gdzllbb")) {
			toInitSel_1 = "!!SplitSignOne!!!!SplitSignTwo!!ѧУ!!SplitSignTwo!!ѧУ" +
					"!!SplitSignOne!!!!SplitSignTwo!!"+Base.YXPZXY_KEY+"!!SplitSignTwo!!ѧԺ!!" +
					"SplitSignOne!!!!SplitSignTwo!!����Ա!!SplitSignTwo!!����Ա";
		}
		if (tName.equalsIgnoreCase("stu_gdzlb")) {
			String[] colList = new String[] { "dm", "mc" };
			sql = "select distinct(dm) dm,mc mc from gdzllbb order by dm";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {}, colList);
		}
		
		
		// =============end luojw 2009/12/4 =======================
		// =============begin luojw 2010/6/12 =======================
		if (tName.equalsIgnoreCase("xszz_fsbzffb")) {
			//ѧ������_��ʳ����
			int currentNd = Integer.parseInt(DealString.getDateTime()
					.substring(0, 4));
			String nd = "";
			ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
			String rsStr = "";
			String[] outputValue = new String[]{"nd","nd"};
			for (int i = currentNd - 5; i < currentNd + 5; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				nd = String.valueOf(i);
				map.put("nd", nd);
				aList.add(map);
			}
			for (int i = 0; i < aList.size(); i++) {
				rsStr += "!!SplitSignOne!!";
				for (int j = 0; j < outputValue.length; j++) {
					if ((aList.get(i).get("nd") == null)
							|| aList.get(i).get("nd").equalsIgnoreCase("null")) {
						rsStr += ("!!SplitSignTwo!!" + " ");
					} else {
						rsStr += ("!!SplitSignTwo!!" + aList.get(i).get("nd"));
					}
				}
			}
			toInitSel_1 = rsStr;
			
			String[] colList = new String[] { "dm", "mc" };
			sql = "select distinct(dm) dm,mc mc from xszz_fsbzlxb order by dm";
			toInitSel_2 = dao.getStringToSplit(sql, new String[] {}, colList);
		}
		// =============end luojw 2010/6/12 =======================
		// =============begin luojw 2010/6/23 =======================
		if (tName.equalsIgnoreCase("zjjt_cxf_dj3")) {
			String[] colList = new String[] { "dm", "mc" };
			sql = "select distinct(dm) dm,mc mc from zjjt_cxf_dj2 order by dm";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {}, colList);
		}
		// =============end luojw 2010/6/23 =======================
		
		// =============begin luojw 2010/7/15 =======================
		//ѧ����ɲ�
		if (tName.equalsIgnoreCase("bjxh_sttygbb")) {
			
			String[] colList = new String[] { "dm", "mc" };
			
			sql = "select dm,mc from bjxh_xsgblxb";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {}, colList);
		}
		if (tName.equalsIgnoreCase("bjxh_stdygbb")) {
			
			String[] colList = new String[] { "dm", "mc" };
			StringBuilder sb = new StringBuilder();
			Base.YXPZXY_KEY = message.getMessage("lable.xb");
			sb.append("select a.stmc || a.stxz || a.bmdm dm, ");
			sb.append("a.stmc || '(' || case  when a.stxz = '"+Base.YXPZXY_KEY+"' then ");
			sb.append("(select distinct b.xymc from view_njxyzybj b where a.bmdm = b.xydm) ");
			sb.append("when a.stxz = 'רҵ' then ");
			sb.append("(select distinct b.zymc from view_njxyzybj b where a.bmdm = b.zydm) ");
			sb.append("when a.stxz = '�༶' then ");
			sb.append("(select distinct b.bjmc from view_njxyzybj b where a.bmdm = b.bjdm) ");
			sb.append("else 'ѧУ' end || ')' mc from xsh_stglb a ");
			
			toInitSel_1 = dao.getStringToSplit(sb.toString(), new String[] {}, colList);
		}
		// =============end luojw 2010/7/15 =======================
		
		request.setAttribute("selInit_1", toInitSel_1);//����������б�,��ô���ǵ�һ�������б��ֵ
		request.setAttribute("selInit_2", toInitSel_2);//����������б�,��ô���ǵ�һ�������б��ֵ
		request.setAttribute("selInit_3", toInitSel_3);//����������б�,��ô���ǵ�һ�������б��ֵ
		request.setAttribute("selInit_4", toInitSel_4);//����������б�,��ô���ǵ�һ�������б��ֵ
		request.setAttribute("selInit_5", toInitSel_5);//����������б�,��ô���ǵ�һ�������б��ֵ
		request.setAttribute("codeType", codeType);
		request.setAttribute("tips", tips);
		request.setAttribute("tName", tName);
		request.setAttribute("pageCard", pageCard_tabNames);
		request.setAttribute("topTr", tabTitle_EN_CN);
		request.setAttribute("rs", vector);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("codeByDept", Base.getInitProperties().get("codeByDept"));
		// ActionForward sf = new ActionForward("/login.jsp",false);
		return mapping.findForward("success");
	}

	private ActionForward codeAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String sql = "";
		ActionForward newFwd = null;
		CommanForm codeForm = (CommanForm) form;
		SystemService sysService = new SystemService();
		String tName = request.getParameter("tName");
		String tTem = "";
		String codeType = request.getParameter("codeType");
		String doType = request.getParameter("doType");
		String xxdm = StandardOperation.getXxdm();
		String userName = request.getSession().getAttribute("userName").toString();
		
		if ((doType == null) || doType.equalsIgnoreCase("")) {
			newFwd = new ActionForward("/xtwh/code.jsp", false);
			return newFwd;
		} else if (doType.equalsIgnoreCase("add")) {
			String[] newCode = codeForm.getNewCode();
			// String[] code = new String[5];
			if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm) && "jxjlbdmb".equalsIgnoreCase(tName)) {
				sql = "insert into " + tName + " (jxjlbdm,jxjlbmc) values(";
			} else {
				sql = "insert into " + tName + " values(";
			}
			
			for (int i = 0; i < newCode.length; i++) {
				sql += ("?,");
				newCode[i] = DealString.toGBK(newCode[i]);
			}
			sql = sql.substring(0, sql.length() - 1);
			sql += ")";
			boolean result = false;
			if (tName.equalsIgnoreCase("xljk_stsslbdmb")) {
				New_Random_ID newId = new New_Random_ID();
				String xn_id = newId.new_xnid("stsslbdmb");
				sql = "insert into xljk_stsslbdmb (SSLXDM,SSLXMC,XN_ID) values(?,?,?)";
				String temp = "";
				String s1 = "!!SplitSignOne!!";
				for (int i = 0; i < newCode.length; i++) {
					temp = temp + newCode[i] + s1;
				}
				temp = temp + xn_id;
				String[] newCode1 = temp.split(s1);
				result = dao.runUpdate(sql, newCode1);
			} else if (tName.equalsIgnoreCase("bjlydx_knssrly")) {
				String sqlTemp = "alter table bjlydx_knssqb add (srly"
						+ newCode[0] + " varchar(255))";
				if (dao.runUpdateTab(sqlTemp)) {
					sqlTemp = "alter table bjlydx_knssqb modify srly"
							+ newCode[0] + " default '0'";
					dao.runUpdateTab(sqlTemp);
					String[] outV = dao.getViewComm("view_bjlydx_knssqb");
					sqlTemp = "create or replace view view_bjlydx_knssqb as select a.*,b.xm,b.xb,b.sfzh,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc from bjlydx_knssqb a,view_xsjbxx b where a.xh=b.xh";
					dao.creatView(sqlTemp, outV);
					sqlTemp = "comment on column bjlydx_knssqb.srly"
							+ newCode[0] + " is '" + newCode[1] + "'";
					dao.runUpdateTab(sqlTemp);
					sqlTemp = "comment on column view_bjlydx_knssqb.srly"
							+ newCode[0] + " is '" + newCode[1] + "'";
					if (dao.runUpdateTab(sqlTemp)) {
						result = dao.runUpdate(sql, newCode);
					}
				}
			} else if (tName.equalsIgnoreCase("gywsjcbmb")) {// ��Ԣ����������鲿��
				sql = "insert into gywsjcbmb(bmdm,bmmc) values(?,?)";
				result = dao.runUpdate(sql, newCode);
			} else if (tName.equalsIgnoreCase("yrdwdmb")
					&& xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				newCode[1] = dao.getOneRs(
						"select bmmc from zxbz_xxbmdm where bmdm=?",
						new String[] { newCode[0] }, "bmmc");
				newCode[3] = dao.getOneRs("select xm from yhb where yhm=?", new String[]{newCode[3]}, "xm");
				sql = "insert into yrdwdmb(yrdwdm,yrdwmc,xydm,lxr,lxdh,dwlb,ssxq,dlm) values(?,?,?,?,?,?,?,?)";
				result = dao.runUpdate(sql, newCode);
			} else if (tName.indexOf("rcgl_dmwhb") != -1) {
				tTem = tName.replace("rcgl_dmwhb!!", "");
				tName = "rcgl_dmwhb";
				sql = "insert into rcgl_dmwhb(XN_ID,MKDM,MKMC,MKSS) values('"
						+ newCode[0] + tTem + "',?,?,'" + tTem + "')";
				result = dao.runUpdate(sql, newCode);
			} else if ("sslddmb".equalsIgnoreCase(tName)
					&&!Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
				sql = "insert into sslddmb(lddm,ldmc,xqdm,xbxd,cs,yqdm) values(?,?,?,?,?,?)";
				result = dao.runUpdate(sql, newCode);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				if (tName.equalsIgnoreCase("jxjdmb")) {
					sql = "insert into jxjdmb (jxjdm,jxjmc,jxjlb,jxjjb,jlje,jxjfl) values (?,?,?,?,?,?)";
					result = dao.runUpdate(sql, newCode);
				}
				if (tName.equalsIgnoreCase("rychdmb")) {
					sql = "insert into rychdmb (rychdm,rychmc,pxbl) values (?,?,?)";
					result = dao.runUpdate(sql, newCode);
				}
				if (tName.equalsIgnoreCase("jxjxdmb")) {
					sql = "insert into jxjxdmb (jxdm,jxmc) values (?,?)";
					result = dao.runUpdate(sql, newCode);
				}
				if (tName.equalsIgnoreCase("djksdmb")) {
					sql = "insert into djksdmb (djksdm,djksmc,dymc) values (?,?,?)";
					result = dao.runUpdate(sql, newCode);
				} else {
					result = dao.runUpdate(sql, newCode);
				}
			}else if ("sztz_kmdmb".equalsIgnoreCase(tName)){
				if (Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm)) {
					sql = "insert into sztz_kmdmb(kmdm,kmm,hgfs,sm) values(?,?,?,?)";
					result = dao.runUpdate(sql, newCode);
				}else{
					sql = "insert into sztz_kmdmb(kmdm,kmm,hgfs) values(?,?,?)";
					result = dao.runUpdate(sql, newCode);
				}
			}else {
				if (tName.equalsIgnoreCase("jxjdmb")) {
					sql = "insert into jxjdmb (jxjdm,jxjmc,jxjlb,jxjjb,jlje,szjdbz,sfqy) values (?,?,?,?,?,'',?)";
					if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
						sql = "insert into jxjdmb (jxjdm,jxjmc,jxjlb,jxjjb,jlje,jxjfl) values (?,?,?,?,?,?)";
					}
					else if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
						sql = "insert into jxjdmb (jxjdm,jxjmc,jxjlb,jlje,szjdbz,dkjd) values (?,?,?,?,?,?)";
					}
					else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDXHXXY)){
						sql = "insert into jxjdmb (jxjdm,jxjmc,jxjlb,jxjjb,jlje,szjdbz,jlf) values (?,?,?,?,?,?,?)";
					}
					else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG )){
						sql = "insert into jxjdmb (jxjdm,jxjmc,jxjlb,jxjjb,jlje,bdbblxdm) values (?,?,?,?,?,?)";
					} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
						sql = "insert into jxjdmb (jxjdm,jxjmc,jxjlb,jxjjb,jlje) values (?,?,?,?,?)";
					}
					result = dao.runUpdate(sql, newCode);
				} else if (tName.equalsIgnoreCase("rychdmb")) {
					sql = "insert into rychdmb (rychdm,rychmc) values (?,?)";
					if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
						sql = "insert into rychdmb (rychdm,rychmc,rychfl) values (?,?,?)";
					}
					if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG )){
						sql = "insert into rychdmb (rychdm,rychmc,bdbblxdm) values (?,?,?)";
					}
					result = dao.runUpdate(sql, newCode);
				} else if (tName.equalsIgnoreCase("jxjxdmb")) {
					sql = "insert into jxjxdmb (jxdm,jxmc) values (?,?)";
					result = dao.runUpdate(sql, newCode);
				} else if ("zcbzpyb".equalsIgnoreCase(tName)) {
					sql = "insert into zcbzpyb (dm,yx,yxmc,bzf,bzpy,dj) values (?,?,?,?,?,?)";
					result = dao.runUpdate(sql, newCode);
				} else {
					result = dao.runUpdate(sql, newCode);
				}
			}
			String codeByDept = Base.getInitProperties().get("codeByDept");
			if(codeByDept != null && "true".equalsIgnoreCase(codeByDept)){//��Ҫ�����������Ϣ
				//�����������Ϣ
				String tableName = "dmwhczrxxb";
				boolean flag = StandardOperation.delete(tableName, "xmdm||ssb", newCode[0]+tName, request);
				if(flag){
					StandardOperation.insert(tableName, new String[]{"xmdm","ssb","czr"}, new String[]{newCode[0],tName,userName}, request);
				}
			}
			String res = result ? "OK" : "NO";
			request.setAttribute("result", res);
			String userDo = "�ڱ�'" + tName + "'�����Ӵ��룬����Ϊ��" + newCode[0];
			dao = new DAO(request.getLocalAddr());
			dao.writeLog("", null, null, userDo, request);
			request.setAttribute("tName", tName);
			newFwd = new ActionForward("/xtwh/code.jsp", false);
		} else if (doType.equalsIgnoreCase("modi")) {
			String[] newCode = codeForm.getNewCode();
			String key = request.getParameter("key");
			Vector<HashMap<String, Object>> vec = new Vector<HashMap<String, Object>>();
			HashMap<String, Object> sqlMap = new HashMap<String, Object>();
			String[] sqls = new String[2];
			sqls[0] = "delete from " + tName + " where " + key + "=?";
			
			if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm) && "jxjlbdmb".equalsIgnoreCase(tName)) {
				sqls[1] = "insert into " + tName + " (jxjlbdm,jxjlbmc) values (";
			} else {
				sqls[1] = "insert into " + tName + " values(";
			}
			
			for (int i = 0; i < newCode.length; i++) {
				sqls[1] += ("?,");
				newCode[i] = DealString.toGBK(newCode[i]);
			}
			sqls[1] = sqls[1].substring(0, sqls[1].length() - 1);
			sqls[1] += ")";
			
			if ("zcbzpyb".equalsIgnoreCase(tName)) {
				sqls[1] = "insert into zcbzpyb (dm,yx,yxmc,bzf,bzpy,dj) values (?,?,?,?,?,?)";
			}

			sqlMap.put("sqlTxt", sqls[0]);
			sqlMap.put("sqlVal", new String[] { newCode[0] });
			vec.add(sqlMap);
			sqlMap = new HashMap<String, Object>();
			// String[] code = new String[5];
			sqlMap.put("sqlTxt", sqls[1]);
			sqlMap.put("sqlVal", newCode);
			vec.add(sqlMap);
			boolean result = false;
			if (tName.equalsIgnoreCase("xljk_stsslbdmb")) {
				vec = new Vector<HashMap<String, Object>>();
				String sslbdm = newCode[0];
				HashMap<String, Object> sqlMap2 = new HashMap<String, Object>();

				sqlMap2.put("sqlTxt",
						"delete from xljk_stsslbdmb where SSLXDM=?");
				sqlMap2.put("sqlVal", new String[] { sslbdm });
				vec.add(sqlMap2);
				sqlMap2 = new HashMap<String, Object>();

				New_Random_ID newId = new New_Random_ID();
				String xn_id = newId.new_xnid("stsslbdmb");
				sql = "insert into xljk_stsslbdmb (SSLXDM,SSLXMC,XN_ID) values(?,?,?)";
				String temp = "";
				String s1 = "!!SplitSignOne!!";
				for (int i = 0; i < newCode.length; i++) {
					temp = temp + newCode[i] + s1;
				}
				temp = temp + xn_id;
				String[] newCode1 = temp.split(s1);
				sqlMap2.put("sqlTxt", sql);
				sqlMap2.put("sqlVal", newCode1);
				vec.add(sqlMap2);
			}
			if (tName.equalsIgnoreCase("gywsjcbmb")) {// ��Ԣ����������鲿��
				sqls[0] = "delete from gywsjcbmb where " + key + "=?";
				sqls[1] = "insert into gywsjcbmb(bmdm,bmmc) values(?,?)";
				sqlMap.put("sqlTxt", sqls[0]);
				sqlMap.put("sqlVal", new String[] { newCode[0] });
				vec.add(sqlMap);
				sqlMap = new HashMap<String, Object>();
				sqlMap.put("sqlTxt", sqls[1]);
				sqlMap.put("sqlVal", newCode);
				vec.add(sqlMap);
			}
			if (tName.equalsIgnoreCase("bjlydx_knssrly")) {
				String sqlTemp = "comment on column bjlydx_knssqb.srly"
						+ newCode[0] + " is '" + newCode[1] + "'";
				dao.runUpdateTab(sqlTemp);
				sqlTemp = "comment on column view_bjlydx_knssqb.srly"
						+ newCode[0] + " is '" + newCode[1] + "'";
				if (dao.runUpdateTab(sqlTemp)) {
					result = dao.runUpdate(vec);
				}
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)
					&& tName.equalsIgnoreCase("yrdwdmb")) {
				newCode[1] = DealString.toUtf8String(dao.getOneRs(
						"select dwmc from bks_dwdmb where dwdm=?",
						new String[] { newCode[0] }, "dwmc"));
				newCode[1] = DealString.toGBK(newCode[1]);
				sqls[0] = "delete from yrdwdmb where " + key + "=?";
				sqls[1] = "insert into yrdwdmb(yrdwdm,yrdwmc,xydm,lxr,lxdh,dwlb,ssxq) values(?,?,?,?,?,?,?)";
				sqlMap.put("sqlTxt", sqls[0]);
				sqlMap.put("sqlVal", new String[] { newCode[0] });
				vec.add(sqlMap);
				sqlMap = new HashMap<String, Object>();
				sqlMap.put("sqlTxt", sqls[1]);
				sqlMap.put("sqlVal", newCode);
				vec.add(sqlMap);
				result = dao.runUpdate(vec);
			} else if (tName.indexOf("rcgl_dmwhb") != -1) {
				tTem = tName.replace("rcgl_dmwhb!!", "");
				tName = "rcgl_dmwhb";
				vec = new Vector<HashMap<String, Object>>();
				sqls[0] = "delete from rcgl_dmwhb where mkdm=?";
				sqls[1] = "insert into rcgl_dmwhb(XN_ID,MKDM,MKMC,MKSS) values('"
						+ newCode[0] + tTem + "',?,?,'" + tTem + "')";
				sqlMap.put("sqlTxt", sqls[0]);
				sqlMap.put("sqlVal", new String[] { newCode[0] });
				vec.add(sqlMap);
				sqlMap = new HashMap<String, Object>();
				sqlMap.put("sqlTxt", sqls[1]);
				sqlMap.put("sqlVal", newCode);
				vec.add(sqlMap);
				result = dao.runUpdate(vec);
			} else 	if ("sslddmb".equalsIgnoreCase(tName)
					&&!Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
				boolean bDel = dao.runUpdate(sqls[0],
						new String[] { newCode[0] });
				if (bDel) {
					sql = "insert into sslddmb(lddm,ldmc,xqdm,xbxd,cs,yqdm) values(?,?,?,?,?,?)";
					result = dao.runUpdate(sql, newCode);
				}
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				if (tName.equalsIgnoreCase("jxjdmb")) {
					boolean bDel = dao.runUpdate(sqls[0],
							new String[] { newCode[0] });
					if (bDel) {
						sql = "insert into jxjdmb (jxjdm,jxjmc,jxjlb,jxjjb,jlje,jxjfl) values (?,?,?,?,?,?)";
						result = dao.runUpdate(sql, newCode);
					}
				}
				if (tName.equalsIgnoreCase("rychdmb")) {
					boolean bDel = dao.runUpdate(sqls[0],
							new String[] { newCode[0] });
					if (bDel) {
						sql = "insert into rychdmb (rychdm,rychmc,pxbl) values (?,?,?)";
						result = dao.runUpdate(sql, newCode);
					}
				}
				if (tName.equalsIgnoreCase("jxjxdmb")) {
					boolean bDel = dao.runUpdate(sqls[0],
							new String[] { newCode[0] });
					if (bDel) {
						sql = "insert into jxjxdmb (jxdm,jxmc) values (?,?)";
						result = dao.runUpdate(sql, newCode);
					}
				}
				if (tName.equalsIgnoreCase("djksdmb")) {
					boolean bDel = dao.runUpdate(sqls[0],
							new String[] { newCode[0] });
					if (bDel) {
						sql = "insert into djksdmb (djksdm,djksmc,dymc) values (?,?,?)";
						result = dao.runUpdate(sql, newCode);
					}
				} else {
					result = dao.runUpdate(vec);
				}
			}else if("sztz_kmdmb".equalsIgnoreCase(tName)){				
					boolean bDel = dao.runUpdate(sqls[0],
							new String[] { newCode[0] });
					if (bDel) {
						if (Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm)) {
							sql = "insert into sztz_kmdmb(kmdm,kmm,hgfs,sm) values(?,?,?,?)";
							result = dao.runUpdate(sql, newCode);
						}else{
							sql = "insert into sztz_kmdmb(kmdm,kmm,hgfs) values(?,?,?)";
							result = dao.runUpdate(sql, newCode);
						}
					}				 
			}
			else{
				if (tName.equalsIgnoreCase("jxjdmb")) {
					boolean bDel = dao.runUpdate(sqls[0],
							new String[] { newCode[0] });
					if (bDel) {
						sql = "insert into jxjdmb (jxjdm,jxjmc,jxjlb,jxjjb,jlje,szjdbz,sfqy) values (?,?,?,?,?,'',?)";
						if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
							sql = "insert into jxjdmb (jxjdm,jxjmc,jxjlb,jxjjb,jlje,jxjfl) values (?,?,?,?,?,?)";
						} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
							sql = "insert into jxjdmb (jxjdm,jxjmc,jxjlb,jlje,szjdbz,dkjd) values (?,?,?,?,?,?)";
						}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDXHXXY)){
							sql = "insert into jxjdmb (jxjdm,jxjmc,jxjlb,jxjjb,jlje,szjdbz,jlf) values (?,?,?,?,?,?,?)";
						}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)){
							sql = "insert into jxjdmb (jxjdm,jxjmc,jxjlb,jxjjb,jlje,bdbblxdm) values (?,?,?,?,?,?)";
						} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
							sql = "insert into jxjdmb (jxjdm,jxjmc,jxjlb,jxjjb,jlje) values (?,?,?,?,?)";
						}
						result = dao.runUpdate(sql, newCode);
					}
				} else if (tName.equalsIgnoreCase("rychdmb")) {
					boolean bDel = dao.runUpdate(sqls[0],
							new String[] { newCode[0] });
					if (bDel) {
						sql = "insert into rychdmb (rychdm,rychmc) values (?,?)";
						if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
							sql = "insert into rychdmb (rychdm,rychmc,rychfl) values (?,?,?)";
						}
						if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
							sql = "insert into rychdmb (rychdm,rychmc,bdbblxdm) values (?,?,?)";
						}
						result = dao.runUpdate(sql, newCode);
					}
				} else if (tName.equalsIgnoreCase("jxjxdmb")) {
					boolean bDel = dao.runUpdate(sqls[0],
							new String[] { newCode[0] });
					if (bDel) {
						sql = "insert into jxjxdmb (jxdm,jxmc) values (?,?)";
						result = dao.runUpdate(sql, newCode);
					}
				} else if ("sslddmb".equalsIgnoreCase(tName)
						&&!Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
					boolean bDel = dao.runUpdate(sqls[0],
							new String[] { newCode[0] });
					if (bDel) {
						sql = "insert into sslddmb(lddm,ldmc,xqdm,xbxd,cs) values(?,?,?,?,?)";
						result = dao.runUpdate(sql, newCode);
					}
				} else {
					result = dao.runUpdate(vec);
				}
			}
			
			String codeByDept = Base.getInitProperties().get("codeByDept");
			if(codeByDept != null && "true".equalsIgnoreCase(codeByDept)){//�޸�ʱ��������ϢΪ�յ�,��Ҫ�����������Ϣ
				//�����������Ϣ
				String tableName = "dmwhczrxxb";
				boolean flag = sysService.checkExists(tableName, "xmdm||ssb", newCode[0]+tName);
				if(!flag){
					StandardOperation.insert(tableName, new String[]{"xmdm","ssb","czr"}, new String[]{newCode[0],tName,userName}, request);
				}
			}
			
			String res = result ? "OK" : "NO";
			request.setAttribute("result", res);
			String userDo = "�޸ı�'" + tName + "'�д��룬����Ϊ��" + newCode[0];
			dao = new DAO(request.getLocalAddr());
			dao.writeLog("", null, null, userDo, request);
			request.setAttribute("tName", tName);
			newFwd = new ActionForward("/xtwh/code.jsp", false);
		} else if (doType.equalsIgnoreCase("del")) {
			String delKey = request.getParameter("delKey");
			String delV = DealString.toGBK(request.getParameter("delV"));
			if (tName.equalsIgnoreCase("bjdydygxb")) {
				String[] delkeys = delKey.split("!!");
				delKey = delkeys[0];
				for (int i = 1; i < delkeys.length; i++) {
					delKey += "||" + delkeys[i];
				}
			}
			if (tName.equalsIgnoreCase("xszz_zgkd_tsrqdmb")) {
				sql = "delete from xszz_zgkd_tsrq where tsrqdm=?";
				dao.runUpdate(sql, new String[] { delV });
				sql = "delete from xszz_zgktdx_tsrqxmwh where tsrqdm=?";
				dao.runUpdate(sql, new String[] { delV });
			}
			if (tName.indexOf("rcgl_dmwhb") != -1) {
				tTem = tName.replace("rcgl_dmwhb!!", "");
				tName = "rcgl_dmwhb";
			}
			sql = "delete from " + tName + " where " + delKey + "=?";
			boolean result = dao.runUpdate(sql, new String[] { delV });
			String res = result ? "OK" : "NO";
			request.setAttribute("result", res);
			String userDo = "ɾ����'" + tName + "'�д��룬����Ϊ��" + delV;
			dao.writeLog("", null, null, userDo, request);
			request.setAttribute("tName", tName);
			newFwd = new ActionForward("/code_man.do?act=" + codeType, false);
		}
		return newFwd;
	}

	private ActionForward logSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm model = (CommanForm)form;
		DAO dao = DAO.getInstance();
		String sql = "";
		String yhm = request.getParameter("yhm");
		String dateF = request.getParameter("dateF");
		String dateT = request.getParameter("dateT");
		String ipF = request.getParameter("ipF");
		String ipT = request.getParameter("ipT");
		String zdm = request.getParameter("zdm");
		boolean allNull = true;
		sql = StringUtils.joinStr("select a.*,rownum r,rownum �к� from (select a.*,(",
			                     "select distinct xm from yhb b where b.yhm=a.yhm)�û����� from (",
			                     "select a.* from czrzb a where yhm like ? ",
			                     "and to_number(substr(czsj,0,4)||substr(czsj,6,2)||substr(czsj,9,2))>=? ",
			                     "and to_number(substr(czsj,0,4)||substr(czsj,6,2)||substr(czsj,9,2))<=?",
			                     " and ip<? and ip>?  order by a.rzxh) a) a");
		if ((yhm == null) || yhm.equalsIgnoreCase("")) {
			yhm = "%";
		} else {
			yhm += "%";
		}
		if ((dateF == null) || dateF.equalsIgnoreCase("")) {
			dateF = "1900-01-01";
		} else {
			allNull = false;
		}
		if ((dateT == null) || dateT.equalsIgnoreCase("")) {
			dateT = "2070-12-31";
		} else {
			allNull = false;
		}
		if ((ipF == null) || ipF.equalsIgnoreCase("")) {
			ipF = "0";
		} else {
			allNull = false;
		}
		if ((ipT == null) || ipT.equalsIgnoreCase("")) {
			ipT = "9";
		} else {
			allNull = false;
		}
		
		if ((zdm != null) && !zdm.equalsIgnoreCase("")) {
			sql += " where exists (select 1 from yhb where zdm = '"+zdm+"' and a.yhm=yhm)";
		}
		String[] tabTitle = dao
				.getColumnName("select rownum �к�,a.rzxh,a.yhm,'�û�����' �û�����,a.yhcz,a.czsj,a.ip,a.mac,a.host from czrzb a");
		String[] tabTitleCN = dao.getColumnNameCN(tabTitle, "czrzb");
		List titles = dao.arrayToList(tabTitle, tabTitleCN);
		List vector;
		String rsNum = "";
		if (allNull) {
			vector = null;
		} else {
			vector = CommonQueryDAO.commonQuery(sql, "", new String[] { yhm, dateF.replaceAll("-", ""), dateT.replaceAll("-",""), ipT,
					ipF }, tabTitle, model);
//			vector = dao.getList(sql, new String[] { yhm, dateF.replaceAll("-", ""), dateT.replaceAll("-",""), ipT,
//					ipF }, tabTitle);
		}
		if (vector == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(vector.size());
		}
		request.setAttribute("topTr", titles);
		request.setAttribute("rs", vector);
		request.setAttribute("yhm", yhm);
		request.setAttribute("dateF", dateF);
		request.setAttribute("dateT", dateT);
		request.setAttribute("ipF", ipF);
		request.setAttribute("ipT", ipT);
		request.setAttribute("rsNum", rsNum);
		FormModleCommon.requestSetList(new String[]{"yhz"}, request);
		return mapping.findForward("success");
	}
	
	
	
	//�鿴��־��������
	private ActionForward logViewinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		
		
		String pk = "rzxh";
		String sql = ""; // sql���
		String realTable = "czrzb"; //������־��
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from "+ realTable +" where 1=2"); // ������������
			String[] zpxxinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zpxxinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zpxxinfo[i]); // ����¼ѭ������map��
				}
			}
		}
		request.setAttribute("rs", map);// �������ݼ�
		
		
		return mapping.findForward("success");
	}
	
	
	

	private ActionForward dataBackup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
//		Encrypt ept = new Encrypt();
		String act = request.getParameter("act");
		// String bfsj = request.getParameter("bfsj");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String pk = "bfr||bfsj";
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		if ((act != null) && act.equalsIgnoreCase("save")) {
			sql = "select * from bfszb where rownum=1";
			String[] bak_para = dao.getOneRs(sql, new String[] {},
					new String[] { "db_user", "db_pwd", "db_sid", "bak_path",
							"db_systemType" });
			String newBakInfo = request.getParameter("newBakInfo");
			if (newBakInfo == null) {
				newBakInfo = "";
			}
			newBakInfo = DealString.toGBK(newBakInfo);
			String fName = userName;
			Timestamp date = new Timestamp(System.currentTimeMillis());
			fName += date.toString().substring(0, 19);
			fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(
					":", "");
			String command = "";
			if ("windows".equalsIgnoreCase(bak_para[4])) {
				// bak_para[1] = ept.encrypt(bak_para[1]);
				command = "cmd /c start exp " + bak_para[0] + "/" + bak_para[1]
						+ "@" + bak_para[2] + " file='" + bak_para[3] + "/"
						+ fName + "' owner=" + bak_para[0];
				Runtime.getRuntime().exec(command);
			} else {
				String[] commands = { bak_para[3] + "/expDataAuto.sh" };
				Runtime.getRuntime().exec(commands);
			}

			sql = "insert into sjbfjl(bfwjm,bfr,bfsm) values(?,?,?)";
			dao.runUpdate(sql, new String[] { fName, userName, newBakInfo });
		} else if ("del".equalsIgnoreCase(act)) {
			sql = "delete sjbfjl where " + pk + "=?";
			dao.runUpdate(sql, new String[] { pkValue });
		}
		sql = "select rownum �к�,a.* from sjbfjl a order by a.bfsj";

		String[] tabTitle = dao.getColumnName(sql);
		List vector = dao.getList(sql, new String[] {}, tabTitle);
		String rsNum = "";
		if (vector == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(vector.size());
		}
		request.setAttribute("rs", vector);
		request.setAttribute("rsNum", rsNum);
		return mapping.findForward("success");
	}

	// ϵͳ����
	private ActionForward systemInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm sysForm = (CommanForm) form;
		Encrypt enc = new Encrypt();
		HttpSession session = request.getSession();
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String xxmc = DealString.toGBK(request.getParameter("xxmc"));
		
		String xxdm = "";
		String sql = "";
		// ================= begin ���ΰ 2009/6/1======================
		xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		// ================= end ���ΰ 2009/6/1======================

		// ================= begin ���ΰ 2010/4/8======================	
		String nowEdition =  request.getParameter("nowEdition");
		String nowSuperSearch =  request.getParameter("nowSuperSearch");
		
		String hadEdit = request.getParameter("hadEdit");
		
		if("yes".equalsIgnoreCase(hadEdit)){
			Base.setEdition(nowEdition);
		}
		
		if(!Base.isNull(nowSuperSearch)){
			Base.setSuperSearch(nowSuperSearch);
		}
		
		String edition = Base.getEdition();
		String superSearch = Base.getSuperSearch();
		
		request.setAttribute("hadEdit", hadEdit);
		session.setAttribute("edition", edition);
		session.setAttribute("superSearch", superSearch);
		// ================= end ���ΰ 2010/4/8======================
		
		if ("search".equals(doType)) {
			sql = "select xxdm from dmk_xx where xxmc=?";
			xxdm = dao.getOneRs(sql, new String[] { xxmc }, "xxdm");
			if (!("".equalsIgnoreCase(xxdm) || xxdm == null)) {
				request.setAttribute("xxmc", xxmc);
				request.setAttribute("setxxdm", xxdm);
			} else {
				request.setAttribute("nofind", "nofind");
			}
		}
		if ((act != null) && act.equalsIgnoreCase("save")) {
			String xn = request.getParameter("xn");
			String xq = request.getParameter("xq");
			String nd = request.getParameter("nd");
			String sydm = request.getParameter("sydm");
			String xydm = request.getParameter("xydm");
			xxmc = DealString.toGBK(request.getParameter("xxmc"));
			xxdm = DealString.toGBK(request.getParameter("setxxdm"));
			String xxbs = enc.encrypt(enc.encrypt(xxmc));
			boolean rst = false;
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
				String xqzs = request.getParameter("xqzs");
				sql = "update xtszb set xxmc=?,xxdm=?,dqxn=?,dqxq=?,dqnd=?,xxbs=?,sydm=?,xydm=?,xqzs=?";
				rst = dao.runUpdate(sql, new String[] { xxmc, xxdm, xn, xq, nd,
						xxbs, sydm, xydm,xqzs });
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)||Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)) {
				String xqzs = request.getParameter("xqzs");
				sql = "update xtszb set xxmc=?,xxdm=?,dqxn=?,dqxq=?,dqnd=?,xxbs=?,sydm=?,xydm=?,xqzs=?";
				rst = dao.runUpdate(sql, new String[] { xxmc, xxdm, xn, xq, nd,
						xxbs, sydm, xydm,xqzs });
				request.setAttribute("xqzs", xqzs);
			}else if (checkXxdmExists(Globals.SYS_MAN_QSRQXQZS)) {
				String xqzs = request.getParameter("xqzs");
				String qsrq = request.getParameter("qsrq");
				sql = "update xtszb set xxmc=?,xxdm=?,dqxn=?,dqxq=?,dqnd=?,xxbs=?,sydm=?,xydm=?,xqzs=?,qsrq=?";
				rst = dao.runUpdate(sql, new String[] { xxmc, xxdm, xn, xq, nd,
						xxbs, sydm, xydm, xqzs, qsrq });
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZSF)) {
				String yjzt = request.getParameter("yjzt");
				String xqzs = request.getParameter("xqzs");
				String qsrq = request.getParameter("qsrq");
				sql = "update xtszb set xxmc=?,xxdm=?,dqxn=?,dqxq=?,dqnd=?,xxbs=?,sydm=?,xydm=?,xqzs=?,qsrq=?,yjzt=?";
				rst = dao.runUpdate(sql, new String[] { xxmc, xxdm, xn, xq, nd,
						xxbs, sydm, xydm, xqzs, qsrq, yjzt});
			}else {
				sql = "update xtszb set xxmc=?,xxdm=?,dqxn=?,dqxq=?,dqnd=?,xxbs=?,sydm=?,xydm=?";
				rst = dao.runUpdate(sql, new String[] { xxmc, xxdm, xn, xq, nd,
						xxbs, sydm, xydm });
			}
			if (rst) {
				sysForm.setChanged("yes");
				Base.currXn = xn;
				Base.currNd = nd;
				Base.currXq = xq;
				Base.sydm = sydm;
				Base.xydm = xydm;
				Base.xxdm = xxdm;
				Base.xxmc = xxmc;
				Base.setDqxqmc(Base.getXqmcForXqdm(xq));
				Base.setXnndList(dao.getXnndList());
				Base.setXnndList2(dao.getXnndList2());
				session.setAttribute("xxdm", xxdm);
				session.setAttribute("xxmc", xxmc);
			} else {
				sysForm.setChanged("no");
			}
		}
		sql = "select * from xtszb where rownum=1";
		String[] result = dao.getOneRs(sql, new String[] {}, new String[] {
				"xxmc", "dqxn", "dqxq", "dqnd", "sydm", "xydm", "xxdm",
				"kxdyt", "xqzs", "dqzc" });
		if(Globals.XXDM_HZSF.equalsIgnoreCase(xxdm)){
			result = dao.getOneRs(sql, new String[] {}, new String[] {
					"xxmc", "dqxn", "dqxq", "dqnd", "sydm", "xydm", "xxdm",
					"kxdyt", "xqzs", "dqzc","yjzt" });
			request.setAttribute("yjzt", Base.chgNull(result[10], "0", 0));
		}
		
		if (result != null) {
			sysForm.setXn(result[1]);
			sysForm.setXq(result[2]);
			sysForm.setNd(result[3]);
			request.setAttribute("sydm", Base.chgNull(result[4], " ", 0));
			request.setAttribute("xydm", Base.chgNull(result[5], " ", 0));
			if (result[0] != null && !"search".equals(doType))
				request.setAttribute("xxmc", result[0]);
			else if ("".equals(xxmc)) {
				request.setAttribute("xxmc", "");
			} else if ("search".equals(doType)) {
				request.setAttribute("xxmc", xxmc);
			}
			if (result[6] != null && !"search".equals(doType))
				request.setAttribute("setxxdm", result[6]);
			else if (!"search".equals(doType)) {
				request.setAttribute("setxxdm", "");
			} else if ("search".equals(doType) && xxdm == "") {
				request.setAttribute("setxxdm", "");
			}
		}
		List<HashMap<String, String>> xnList = dao.getXnndList();
		xnList.remove(0);
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("xxdm", result[6]);
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)
				||Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)){
			request.setAttribute("showZs","");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)||Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)
				||Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)) {
			sql = "select * from xtszb where rownum=1";
			String[] result1 = dao.getOneRs(sql, new String[] {}, new String[] { "kxdyt",
					"xqzs", "dqzc" });
			request.setAttribute("xqzs", Base.chgNull(result1[1], "0", 0));
		}else if (checkXxdmExists(Globals.SYS_MAN_QSRQXQZS)) {
			sql = "select * from xtszb where rownum=1";
			String[] result1 = dao.getOneRs(sql, new String[] {}, new String[] {
					"kxdyt", "xqzs", "dqzc", "qsrq" });
			request.setAttribute("xqzs", Base.chgNull(result1[1], "0", 0));
			request.setAttribute("qsrq", Base.chgNull(result1[3], "", 0));
			request.setAttribute("qsrqxqzs", "on");
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)||result[6].equalsIgnoreCase(xxdm)){
			sql = "select * from xtszb where rownum=1";
			String[] result1 = dao.getOneRs(sql, new String[] {}, new String[] { "kxdyt",
					"xqzs", "dqzc" });
			request.setAttribute("xqzs", Base.chgNull(result1[1], "0", 0));
		}
		if (Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)) {
			String now = dao.getOneRs(
					"select to_char(sysdate,'yyyy-mm-dd') now from dual",
					new String[] {}, "now");
			Base.currNd = now.substring(0, 4);
			if ((act != null) && act.equalsIgnoreCase("save")) {
				String kxdyt = request.getParameter("kxdyt");
				kxdyt = kxdyt == null ? "2000-01-01" : kxdyt;
				String xqzs = request.getParameter("xqzs");
				String dqzc = "�ż�";
				int dqzcNum = 0;
				xqzs = xqzs == null || "".equalsIgnoreCase(xqzs) ? "" : xqzs;
				String dayNum = dao.getOneRs("select (to_date('" + now
						+ "','yyyy-mm-dd')-to_date('" + kxdyt
						+ "','yyyy-mm-dd')) dNum from dual", new String[] {},
						"dNum");
				for (int i = Integer.parseInt(dayNum); i > 0; i -= 7) {
					dqzcNum++;
				}
				if (dqzcNum < Integer.parseInt(xqzs)) {
					dqzc = String.valueOf(dqzcNum);
				}

				sql = "update xtszb set kxdyt=?,xqzs=?,dqzc=?";
				dao.runUpdate(sql, new String[] { kxdyt, xqzs, dqzc });
			}
			sql = "select * from xtszb where rownum=1";
			result = dao.getOneRs(sql, new String[] {}, new String[] { "kxdyt",
					"xqzs", "dqzc" });
			request.setAttribute("kxdyt", Base.chgNull(result[0], "2000-01-01",
					0));
			request.setAttribute("xqzs", Base.chgNull(result[1], "0", 0));
			request.setAttribute("dqzc", Base.chgNull(result[2], "�ż�", 0));

			return mapping.findForward("successBjlh");
		} else {
			return mapping.findForward("success");
		}
	}

	private ActionForward baseDataInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		sql = "select table_name tablename,nvl(comments,table_name) tablecomment from user_tab_comments where table_name in (select upper(tablename) from jbsjb)";
		List<HashMap<String, String>> jbsjTableList = dao.getList(sql,
				new String[] {}, new String[] { "tablename", "tablecomment" });
		sql = "select a.table_name||'!@'||b.remotetablename tablename,nvl(comments,table_name) tablecomment from user_tab_comments a, glsjb b where a.table_name = upper(b.localtablename)";
		List<HashMap<String, String>> glsjTableList = dao.getList(sql,
				new String[] {}, new String[] { "tablename", "tablecomment" });
		request.setAttribute("webSerTb", Base.initProperties.get("webSerTb"));//�����ж��Ƿ���webseviceͬ������
		request.setAttribute("jbsjTableList", jbsjTableList);// ���ڵ������ݵĻ������ݱ�
		request.setAttribute("glsjTableList", glsjTableList);// ����webservice�������ݵĻ������ݱ�
		String type = request.getParameter("type");
		if(type!=null&&type.equalsIgnoreCase("xyChange")){
			sql = "{call xydmAllbg()}";
			Boolean update = dao.runProcuder(sql, new String[] {});
			if(update){
				request.setAttribute("update", "true");
			}else{
				request.setAttribute("update", "false");
			}
		}else if(type!=null&&type.equalsIgnoreCase("zyChange")){
			sql = "{call zydmAllbg()}";
			Boolean update =dao.runProcuder(sql, new String[] {});
			if(update){
				request.setAttribute("update", "true");
			}else{
				request.setAttribute("update", "false");
			}
		}else if(type!=null&&type.equalsIgnoreCase("bjChange")){
			sql = "{call bjdmAllbg()}";
			Boolean update =dao.runProcuder(sql, new String[] {});
			if(update){
				request.setAttribute("update", "true");
			}else{
				request.setAttribute("update", "false");
			}
		}
		
		return mapping.findForward("success");
	}

	/**
	 * 2012.3.2
	 * ������
	 * �������ݵ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward baseDataExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DAO dao=DAO.getInstance();
		List<HashMap<String,String>>jcsjszList=new ArrayList<HashMap<String,String>>();
		StringBuilder sql=new StringBuilder();
		
		String tableName=request.getParameter("tableName");
		
		if(Base.isNull(tableName)){
			
		}
		
		sql.append(" select tableMc,tableName,zd from xg_xtwh_jcsjszb");
		jcsjszList=dao.getList(sql.toString(), new String[]{}, new String[]{"tableMc","tableName"});
		request.setAttribute("jcsjszList", jcsjszList);
		return mapping.findForward("success");
	}
	
	private ActionForward stuPwdInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, int power)
			throws Exception {
		DAO dao = DAO.getInstance();
		Encrypt ept = new Encrypt();
		CommanForm commanForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String xh = request.getParameter("xh");
		String doflag = request.getParameter("doflag");
		String mm = ept.encrypt("888888");
		String sql = "";
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tName = request.getParameter("tName");
		
		tName = Base.isNull(tName) ? "single" : tName;
		
		boolean flag = false;
		String returnPage = "success";
		String xydm = commanForm.getXydm();
		String zydm = commanForm.getZydm();
		String bjdm = commanForm.getBjdm();
		String nj = commanForm.getNj();
		
		xydm = xydm == null ? "" : xydm;
		zydm = zydm == null ? "" : zydm;
		bjdm = bjdm == null ? "" : bjdm;
		nj = nj == null ? "" : nj;
		String bjKey = xydm+"!!"+zydm+"!!"+nj;
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(xydm));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
		request.setAttribute("njList", Base.getNjList());
		/*sql = "insert into xsmmb (xh,mm) (select xh,? from view_xsjbxx a where not exists (select 1 from xsmmb b where a.xh=b.xh))";
		dao.runUpdate(sql, new String[] { mm });*/
        dao.runUpdate("insert into xsmmb(xh,mm) select xh,'"+mm+"'mm from "+ Base.xsxxb +" a where not exists(select 1 from xsmmb b where a.xh=b.xh)", new String[]{});
        /*String[] sqlArr = new String[2];
        sqlArr[0] = "insert into xsmmb(xh,mm) select xh,'"+mm+"'mm from xsxxb a where not exists(select 1 from xsmmb b where a.xh=b.xh)";
        sqlArr[1] = "insert into xsmmb(xh,mm) select xh,'"+mm+"'mm from bks_xsjbxx a where not exists(select 1 from xsmmb b where a.xh=b.xh)";
        
        dao.runBatch(sqlArr);*/
        
        
		if (doflag != null && !doflag.equals("")) {
			if(xh != null && !"".equalsIgnoreCase(xh)){//����ѧ�������ʼ��
				sql = "select xh from view_xsjbxx where xh='" + xh + "'";
				String[] result = dao.getOneRs(sql, new String[] {}, new String[] { "xh" });
				if (result == null) {
					commanForm.setChanged("�����ʼ��ʧ�ܣ���ѧ�Ų�����!");
					request.setAttribute("tName", tName);
					return mapping.findForward(returnPage);
				}
				if (userType != null && "xy".equalsIgnoreCase(userType)) {//ѧԺ�û�
					sql = "select xh from view_xsjbxx where xydm=? and xh=?";
					result = dao.getOneRs(sql, new String[] { userDep, xh },
							new String[] { "xh" });
					if (result == null || result.length < 1) {
						commanForm.setChanged("�����ʼ��ʧ�ܣ���ѧ������������"+Base.YXPZXY_KEY+"��ѧ��!");
						return mapping.findForward("success");
					}
				}				
				sql = "update xsmmb set mm = '" + mm + "' where xh = '" + xh + "'";
			}else{
				sql = "update xsmmb a set mm = '" + mm + "' where exists(select 1 from view_xsjbxx b where a.xh=b.xh ";
				if(xydm != null && !"".equalsIgnoreCase(xydm)){
					sql += " and xydm='" + xydm + "'";
				}
				if(zydm != null && !"".equalsIgnoreCase(zydm)){
					sql += " and zydm='" + zydm + "'";
				}
				if(bjdm != null && !"".equalsIgnoreCase(bjdm)){
					sql += " and bjdm='" + bjdm + "'";
				}
				if(nj != null && !"".equalsIgnoreCase(nj)){
					sql += " and nj='" + nj + "'";
				}
				sql += ")";
			}
			
			flag = dao.runUpdate(sql, new String[] {});
			if (flag == true) {
				commanForm.setChanged("�����ʼ���ɹ�!");
			}			
		}
		
		request.setAttribute("tName", tName);
		return mapping.findForward(returnPage);
	}

	private ActionForward bjdydygxb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm myForm = (CommanForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("act");
		// String codeType = request.getParameter("codeType");
		boolean sfbc = false;
		String sql = "";
		String xydm = (myForm.getXydm() != null && myForm.getXydm() != "") ? myForm
				.getXydm()
				: "";
		String zydm = (myForm.getZydm() != null && myForm.getZydm() != "") ? myForm
				.getZydm()
				: "";
		String bjdm = (myForm.getBjdm() != null && myForm.getBjdm() != "") ? myForm
				.getBjdm()
				: "";
		String dylxrdm = request.getParameter("dylxrdm");
		String dylxrxm = DealString.toGBK(request.getParameter("dylxrxm"));
		String dylxrbgs = DealString.toGBK(request.getParameter("dylxrbgs"));
		String dylxrdh = request.getParameter("dylxrdh");
		String dylxrsjh = request.getParameter("dylxrsjh");
		String[] values = null;
		if (request.getParameter("values") != null) {
			values = (request.getParameter("values")).split("!!");
		}
		String[] strs = new String[] { "bjdm", "dylxrdm", "dylxrxm",
				"dylxrbgs", "dylxrdh", "dylxrsjh" };
		List xyList = dao.getXyList();
		List zyList = dao.getZyList(xydm);
		List bjList = dao.getBjList(xydm, zydm);
		myForm.setXydm(xydm);
		myForm.setZydm(zydm);
		myForm.setBjdm(bjdm);
		if (doType != null && doType.equalsIgnoreCase("save")) {
			sql = "delete from bjdydygxb where bjdm='" + bjdm
					+ "' and dylxrdm='" + dylxrdm + "'";
			dao.runUpdate(sql, new String[] {});
			sql = "insert into bjdydygxb(bjdm,dylxrdm,dylxrxm,dylxrbgs,dylxrdh,dylxrsjh) values(?,?,?,?,?,?)";
			sfbc = dao.runUpdate(sql, new String[] { bjdm, dylxrdm, dylxrxm,
					dylxrbgs, dylxrdh, dylxrsjh });
			request.setAttribute("sfbc", sfbc);
			// return new ActionForward("/code_man.do?act=" + codeType, false);
		} else if (doType != null && doType.equalsIgnoreCase("modi")) {
			for (int i = 0; i < strs.length; i++) {
				map.put(strs[i], DealString.toGBK(values[i]));
			}
			// return new ActionForward("/code_man.do?act=" + codeType, false);
		}

		request.setAttribute("xyList", xyList);
		request.setAttribute("zyList", zyList);
		request.setAttribute("bjList", bjList);
		if (map == null) {
			for (int i = 0; i < strs.length; i++) {
				map.put(strs[i], " ");
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward SetBakInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException {
		DAO dao = DAO.getInstance();
//		Encrypt ept = new Encrypt();
		String sql = "";
		String tag = "";
		String[] colList = { "db_user", "db_pwd", "db_sid", "bak_path",
				"db_systemType" };
		HashMap<String, String> map = new HashMap<String, String>();
		sql = "select * from bfszb where rownum=1";
		tag = dao.returntag(sql, new String[] {});
		if ("empty".equalsIgnoreCase(tag)) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], "");
			}
		} else {
			String[] VcolList = dao.getOneRs(sql, new String[] {}, colList);
			map.put("db_user", VcolList[0]);
			// map.put("db_pwd", ept.encrypt(VcolList[1]));
			map.put("db_pwd", VcolList[1]);
			map.put("db_sid", VcolList[2]);
			map.put("bak_path", VcolList[3]);
			map.put("db_systemType", VcolList[4]);

		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	private ActionForward SaveSetBakInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		Encrypt ept = new Encrypt();
		String db_user = request.getParameter("db_user");
		String db_pwd = request.getParameter("db_pwd");
		String db_sid = request.getParameter("db_sid");
		String bak_path = DealString.toGBK(request.getParameter("bak_path"));
		String db_systemType = request.getParameter("db_systemType");
		String[] colList = { "db_user", "db_pwd", "db_sid", "bak_path",
				"db_systemType" };
		boolean del = false;
		boolean inset = false;
		if ((db_pwd != null) && !db_pwd.equalsIgnoreCase("")) {
			// db_pwd = ept.encrypt(db_pwd);
		}
		sql = "delete bfszb";
		del = dao.runUpdate(sql, new String[] {});
		if (del) {
			inset = StandardOperation
					.insert("bfszb", colList, new String[] { db_user, db_pwd,
							db_sid, bak_path, db_systemType }, request);
		}
		// �ж�·���ǲ��Ǵ��ڣ��������򴴽�
		if ("windows".equalsIgnoreCase(db_systemType)) {
			String[] bak_pathA = bak_path.split("/");
			String bak_pathV = "";
			for (int i = 0; i < bak_pathA.length; i++) {
				bak_pathV += bak_pathA[i];
				bak_pathV += "/";
				File f = new File(bak_pathV);
				if (!f.exists()) {
					f.mkdir();
				}
			}
		} else {
			String[] bak_pathA = bak_path.split("/");
			String bak_pathV = "";
			for (int i = 0; i < bak_pathA.length; i++) {
				bak_pathV += bak_pathA[i];
				bak_pathV += "/";
				File f = new File(bak_pathV);
				if (!f.exists()) {
					f.mkdir();
				}
			}
			bak_pathV += "expDataAuto.sh";
			File f = new File(bak_pathV);
			// System.out.println(f.getAbsolutePath().toString()+"
			// "+f.exists());
			if (!f.exists()) {
				f.createNewFile();
				StringBuffer autobakStr = new StringBuffer();
				db_pwd = ept.decrypt(db_pwd);
				autobakStr.append("#!/bin/bash\n");
				autobakStr
						.append("export ORACLE_HOME=/ora/oracle/product/10.2.1\n");
				autobakStr.append("export ORACLE_BASE=/ora/oracle\n");
				autobakStr.append("export ORACLE_SID=");
				autobakStr.append(db_sid == null ? "orcl" : db_sid
						.toLowerCase());
				autobakStr.append("\n");
				autobakStr.append("\n");
				autobakStr.append("echo \"============================\"\n");
				autobakStr.append("date +\"%Y-%m-%d %R\"\n");
				autobakStr.append("echo \"============================\"\n");
				autobakStr.append("export PATH=$PATH:$ORACLE_HOME/bin\n");
				autobakStr.append("rq=` date +\"%Y%m%d%R\" `\n");
				autobakStr.append("bak_dir=\"");
				autobakStr.append(bak_path);
				autobakStr.append("\"\n");
				autobakStr.append("exp ");
				autobakStr.append(db_user);
				autobakStr.append("/");
				autobakStr.append(db_pwd);
				autobakStr
						.append(" file=${bak_dir}/data_full_${rq}.dmp owner=");
				autobakStr.append(db_user);
				autobakStr.append(" buffer=10000000 feedback=10000\n");
				autobakStr.append("gzip -1 ${bak_dir}/data_full_${rq}.dmp\n");
				autobakStr.append("cd $bak_dir\n");
				autobakStr
						.append("find ./ -name \"data_full*.dmp\"  -mtime +30 -print\n");
				autobakStr
						.append("find ./ -name \"data_full*.dmp\"  -mtime +30 -exec rm{} \\;\n");
				FileWriter fw = new FileWriter(bak_pathV);
				fw.write(autobakStr.toString());
				fw.close();
				// �޸�bak_path��owner���޸ĵ����ű���ģʽ
				try {
					String commandLine = "chown oracle:dba " + bak_path;// �޸ı����ļ���Ȩ�ޣ�ʹ��oracle�û�����д��
					Runtime.getRuntime().exec(commandLine);
					commandLine = "chown oracle:dba " + bak_pathV;// �޸ı��ݵ��ļ�Ȩ�ޣ�ʹ��oracle�û�����ִ��
					Runtime.getRuntime().exec(commandLine);
					commandLine = "chmod a+x " + bak_pathV;// �޸ĵ����ļ��Ľű�Ϊ��ִ���ļ�
					Runtime.getRuntime().exec(commandLine);
				} catch (Exception e) {
					throw e;
				}
			}
		}
		if (inset) {
			request.setAttribute("result", "ok");
		} else {
			request.setAttribute("result", "no");
		}
		return mapping.findForward("success");
	}

	private ActionForward AllTeaUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql1 = null;
		Encrypt ept = new Encrypt();
		CommanForm chgForm = (CommanForm) form;
		String yhm = (String)request.getSession().getAttribute("userName");
		String newPassword = ept.encrypt("888888");
		sql1 = "update yhb set kl=? where yhm != 'zf01' or yhm != ?";
		String resualt = "";
		boolean tf = dao.runUpdate(sql1, new String[] { newPassword,yhm });
		if (tf) {
			resualt = "��ʼ���ɹ���";
			request.setAttribute("message",resualt);
		} else {
			resualt = "��ʼ��ʧ�ܣ�";
			request.setAttribute("message",resualt);
		}
		return initUserPower(mapping, form,request, response,-1);
	}

	private ActionForward UserQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String sql = "select distinct zdm,zmc from yhzb where zdm<>'6727'";
		request.setAttribute("zList", dao.getList(sql, new String[] {},
				new String[] { "zdm", "zmc" }));
		sql = "select distinct bmdm xydm,bmmc xymc from zxbz_xxbmdm";
		request.setAttribute("xyList", dao.getList(sql, new String[] {},
				new String[] { "xydm", "xymc" }));
		return mapping.findForward("success");
	}

	// ����ְҵ����Ա��Ϣͬ��
	public ActionForward fdyxxQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String act = DealString.toGBK(request.getParameter("act"));
		String sql = "";
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();

		// Ŀǰ���ݿ�ʱ��
		String rightTime = dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') rtime from dual",
				new String[] {}, new String[] { "rtime" })[0];

		// �ϴ�ͬ��ʱ��
		String afterTime = dao.getOneRs(
				"select sj from fdyxxtbchecktimeb where type='fdyxxtb'",
				new String[] {}, "sj");

		
		long crosstime = java.lang.Math.abs(Long.parseLong(rightTime)- Long.parseLong(afterTime));		
			if ("tb".equalsIgnoreCase(act)) {
				if ((crosstime > 10000)) {
				StandardOperation.update("fdyxxtbchecktimeb", new String[]{"sj"}, new String[]{rightTime},
				"type", "fdyxxtb", request);

				LdapTestDao ld = new LdapTestDao("cn=root", "password");

				if (!"false".equalsIgnoreCase(ld.getTeaInfo())) {
					ld.insertTeaInfolist(ld.getTeaInfo(), request);
					ld.insertTeaInfolistToYhb(ld.getTeaInfo(), request);
					request.setAttribute("tb", "ok");
				} else {
					request.setAttribute("tb", "no");
				}
			}
			else{
				request.setAttribute("crosstime", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from fdyxxb a where 1=1 ";

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ѧ��������Ϣ
		sql = "select a.r �к�,a.* from (select a.*,rownum r from (select distinct a.rowid rid, a.zgh,a.xm, a.dzyx from fdyxxb a where 1=1 "
				+ " ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize());

		String[] colList = new String[] { "rid", "�к�", "zgh", "xm", "dzyx" };

		rs = dao.getArrayList2(sql, new String[] {}, colList);

		sql = "select count(*) from fdyxxb where 1=1 ";
		int rsNuminfo = dao.getOneRsint(sql);
		String rsNum = String.valueOf(rsNuminfo);
		String[] colListCN = dao.getColumnNameCN(colList, "fdyxxb");
		List topTr = dao.arrayToList(colList, colListCN);

		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);

		return mapping.findForward("success");
	}
	
	//�û�����û�����
	public ActionForward userOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		CommanForm cform = (CommanForm)form;
		String pxlx = request.getParameter("pxlx");
		String doType = request.getParameter("doType");
		String sql = "";
		String query = "";
		String tabName = "";
		String pk = "";
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		if("save".equals(doType)){
			String[] idarray = request.getParameterValues("id");
			String[] orderarray = request.getParameterValues("xssx");
			String[] sqls = new String[idarray.length];
			if("yhz".equals(pxlx)){
				tabName = "yhzb";
				pk = "zdm";
			}else{
				tabName = "yhb";
				pk = "yhm";
			}
			for(int i=0;i<idarray.length;i++){
				sqls[i] = "update "+tabName+" set xssx='"+orderarray[i]+"' where "+pk+"='"+idarray[i]+"'";
			}
			int[] result = dao.runBatch(sqls);
			boolean flag = dao.checkBatch(result);
			if(flag){
				request.setAttribute("result","ok");	
			}else{
				request.setAttribute("result","no");
			}			
		}
		sql = "select * from yhzb";
		List<HashMap<String,String>> yhzlist = dao.getList(sql, new String[]{}, new String[]{"zdm","zmc","xssx"});
		request.setAttribute("yhzlist",yhzlist);
		if(pxlx != null && pxlx.equals("yhz")){
			request.setAttribute("rs",yhzlist);	
			request.setAttribute("pxlx","yhz");	
			request.setAttribute("rsNum",yhzlist.size());
		}else if(pxlx != null && pxlx.equals("yh")){
			sql = "select * from yhb where 1=1 ";
			if(!Base.isNull(cform.getZdm())){
				query = " and zdm='"+cform.getZdm()+"'";
			}
			sql += query;
			list = dao.getList(sql, new String[]{}, new String[]{"yhm","xm","xssx"});
			request.setAttribute("rs",list);
			request.setAttribute("pxlx","yh");
			request.setAttribute("rsNum",list.size());
		}
		return mapping.findForward("success");
	}
	
	/**
	 * �����û���Ȩ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * @throws IOException 
	 * */
	public ActionForward expGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, Exception{
		CommanForm yhzForm = (CommanForm)form;
		SystemService service = new SystemService();
		
		String yhz = DealString.toGBK(request.getParameter("yhz"));//�û������
		String gnmkdm = DealString.toGBK(request.getParameter("gnmkdm"));//����ģ�����		
		
		yhzForm.setZdm(yhz);
		yhzForm.setGnmkdm(gnmkdm);
		
		String sql = service.querryZqx(yhzForm);//��ѯ���ݵĲ�ѯ���
		String tableName = "view_yhzqx";//Ҫ��������������������
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(sql, tableName, response.getOutputStream());//������д��Excel�ļ�				
		return mapping.findForward("");
	}
	
	/**
	 * �����û�Ȩ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * @throws IOException 
	 * */
	public ActionForward expUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, Exception{
		CommanForm yhForm = (CommanForm)form;
		SystemService service = new SystemService();
		
		String yhm = DealString.toGBK(request.getParameter("yhm"));//�û���
		String gnmkdm = DealString.toGBK(request.getParameter("gnmkdm"));//����ģ�����		
		
		yhForm.setYhm(yhm);
		yhForm.setGnmkdm(gnmkdm);
		
		String sql = service.querryYhqx(yhForm);//��ѯ���ݵĲ�ѯ���
		String tableName = "view_yhqx";//Ҫ��������������������
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(sql, tableName, response.getOutputStream());//������д��Excel�ļ�				
		return mapping.findForward("");
	}
	
	/**
	 * �����ʼ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward initPass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql1 = null;
		Encrypt ept = new Encrypt();
		String userName = request.getParameter("userName");
		String newPassword = ept.encrypt("888888");
		sql1 = "update yhb set kl=? where yhm=?";
		String resualt = "";
		boolean tf = dao.runUpdate(sql1, new String[] { newPassword,
					userName });
		if (tf) {
			resualt = "���óɹ���";
			request.setAttribute("message",resualt);
		} else {
			resualt = "����ʧ�ܣ�";
			request.setAttribute("message",resualt);
		}
		return initUserPower(mapping, form,request, response,-1);
	}
	
	public boolean checkXxdmExists(String[] array) {
		String xxdm = Base.xxdm;
		for (String s : array) {
			if (s.equalsIgnoreCase(xxdm)) {
				return true;
			}
		}
		return false;
	}
	
}