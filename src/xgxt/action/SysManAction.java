/*
 * 创建日期 2006-8-25
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
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
 * 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
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
				chkUser.setErrMsg("登陆超时，请重新登陆！");
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
			// 判断用户读写权
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
//			在大师的号召下,将组是否显示设置和组名修改合并
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
			} else if ("CODEINIT".equalsIgnoreCase(myAct)) {                    //代码维护
				String codeType = request.getParameter("act");
 				myActFwd = codeManage(mapping, form, request, response,
						codeType);
			} else if ("CODECONF".equalsIgnoreCase(myAct)) {
				myActFwd = codeAdd(mapping, form, request, response);
			} else if ("LOGSEARCH".equalsIgnoreCase(myAct)) {
				myActFwd = logSearch(mapping, form, request, response);
			} else if ("logviewinfo".equalsIgnoreCase(myAct)) {
				myActFwd = logViewinfo(mapping, form, request, response);  //查看具体日志内容
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
				// 学生密码初始化
				myActFwd = stuPwdInit(mapping, form, request, response, p);
			} else if ("bak_set".equalsIgnoreCase(myAct)) {
				myActFwd = SetBakInfo(mapping, form, request, response);
			} else if ("bak_set_save".equalsIgnoreCase(myAct)) { //
				myActFwd = SaveSetBakInfo(mapping, form, request, response);
			} else if ("AllTeaUser".equalsIgnoreCase(myAct)) { // 统一全部生成教师密码
				myActFwd = AllTeaUser(mapping, form, request, response);
			} else if ("user_query".equalsIgnoreCase(myAct)) { // 用户查询
				myActFwd = UserQuery(mapping, form, request, response);
			} else if ("fdyxxQuery".equalsIgnoreCase(myAct)) { // 辅导员信息查询
				myActFwd = fdyxxQuery(mapping, form, request, response);
			} else if ("userorder".equalsIgnoreCase(myAct)) { // 辅导员信息查询
				myActFwd = userOrder(mapping, form, request, response);
			} else if ("EXPGROUP".equalsIgnoreCase(myAct)){//导出用户组权限
				myActFwd = expGroup(mapping, form, request, response);
			} else if ("EXPUSER".equalsIgnoreCase(myAct)){//导出用户权限
				myActFwd = expUser(mapping, form, request, response);
			} else if ("initPass".equalsIgnoreCase(myAct)){
				myActFwd = initPass(mapping, form, request, response);
			}else if ("baseDataExp".equalsIgnoreCase(myAct)) {
				// -----------2012.3.2 qlj-------------
				// -----------基础数据导出--------------
				myActFwd = baseDataExp(mapping, form, request, response);
			} 
			//request.setAttribute("devMode", LicenseOps.getInstance().getDevMode());
			request.setAttribute("devMode", "1");
			request.setAttribute("writeAble", writeAble);
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("数据连接中断，请重试！");
			return new ActionForward("/errMsg.do", false);
		}
	}

	private ActionForward initGroupPower(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, int power) throws Exception {
		// 初始化组维护页面
		DAO dao = DAO.getInstance();
		String sql = "";
		CommanForm groupForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String groupPower = request.getParameter("zmc");

		sql = "select gnmkdm,gnmkmc from view_yhqx where yhm=? order by gnmkdm";
		String[] input = { userName };
		if ((groupPower != null) || !"".equalsIgnoreCase(groupPower)) {//选择了用户组后，页面中当前子系统功能模块列表只显示还没有分给该组的功能
			sql = "select gnmkdm,gnmkmc from view_yhqx a where yhm=? and not exists(select 1 from view_yhzqx b where b.zdm=? and a.gnmkdm=b.gnmkdm and substr(gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?)) order by gnmkdm";
			input = new String[]{userName, groupPower, userName};
		}
		
		
		String[] output = { "gnmkdm", "gnmkmc" };
		String powerList = dao.getStringToSplit(sql, input, output);//登陆用户的所有权限
		// 初始化全部权限的列表
		sql = "select * from yhzb where zmc<>'系统管理员' order by zdm";
		input = new String[] {};
		output = new String[] { "zdm", "zmc" };
		List groupList = dao.getList(sql, input, output);//除系统管理员组外的所有组列表
		// 初始化全部用户组的列表

		if ((groupPower == null) || "".equalsIgnoreCase(groupPower)) {
			sql = "select (nvl(dxq,'0')||gnmkdm) gnmkdm,(rpad(gnmkdm,7,'_')||' | '||replace(replace(nvl(dxq,'0'),'0','只读'),'1','可写')||' | '||gnmkmc) gnmkmc from view_yhzqx where zdm=(select min(zdm) from yhzb) and substr(gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?) order by view_yhzqx.gnmkdm";
			input = new String[] { userName };
		} else {
			sql = "select (nvl(dxq,'0')||gnmkdm) gnmkdm,(rpad(gnmkdm,7,'_')||' | '||replace(replace(nvl(dxq,'0'),'0','只读'),'1','可写')||' | '||gnmkmc) gnmkmc from view_yhzqx where zdm=? and substr(gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?) order by view_yhzqx.gnmkdm";
			input = new String[] { groupPower, userName };
		}
		output = new String[] { "gnmkdm", "gnmkmc" };
		List powerListGroup = dao.getList(sql, input, output);//用户组所有权限列表
		// 初始化单个组权限列表
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
			groupForm.setErrMsg("没有选择合适的组！！！");
			return mapping.findForward("false");
		}
		// 在删除当前组的原有权限之前，先保存到临时表中
		
		dao.runUpdate("delete from yhzqxb_lsb where zdm=?",
				new String[] { groupName });
		
		dao.runUpdate("insert into yhzqxb_lsb(zdm,gnmkdm,dxq) "
				+ "select zdm,gnmkdm,dxq from yhzqxb where zdm=?",
				new String[] { groupName });
		// 删除当前组的原有权限
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
			String logMsg = "修改组权限,组代码为：" + groupName + "。";
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
			logMsg = "添加新组，组名称为：" + groupName + "。";
			errFlag = "Err0007";
		} else if (flag == 2) {
			input = new String[] { groupName,sfxsGroup, groupID };
			logMsg = "更改组名称，组代码为：" + groupID + "，组的新名称为：" + groupName + "。";
			logMsg +="设置组在用户维护时是否显示。组代码为：" + groupID + "，组名称为：" + groupName;
			errFlag = "Err0008";
		} else if (flag == 3)  {
			input = new String[] { groupName };
			logMsg = "删除组以及该组下的所有用户。组代码为：" + groupID + "，组名称为：" + groupName
					+ "。";
			errFlag = "Err0009";
		}
//		将组是否显示和修改组名合并
//		else {
//			input = new String[] { sfxsGroup, groupID };
//			logMsg = "设置组在用户维护时是否显示。组代码为：" + groupID + "，组名称为：" + groupName
//					+ "。";
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
			request.setAttribute("message", added ? "保存成功" : "保存失败");
		}else if(flag==3){
			request.setAttribute("message", added ? "删除成功" : "删除成功");
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
		// 修改或增加用户时需要用到用户组列表，在此初始化

		if ((groupList == null) || groupList.equalsIgnoreCase("")) {
			session.setAttribute("errNo", "Err0013");
			return mapping.findForward("false");
		}
		sql = "select distinct bmdm,bmmc from ZXBZ_XXBMDM order by bmdm";
		tmpOut[0] = "bmdm";
		tmpOut[1] = "bmmc";
		String partList = dao.getStringToSplit(sql, tmpIn, tmpOut);
		if ((partList == null) || partList.equalsIgnoreCase("")) {
			partList = "!!SplitSignOne!!!!SplitSignTwo!! !!SplitSignTwo!!未找到部门";
		}
		// 修改或增加用户时需要用到部门列表，在此初始化

		sql = "select distinct dwdm,dwmc from bks_dwdmb order by dwdm";
		tmpOut[0] = "dwdm";
		tmpOut[1] = "dwmc";
		String unitList = dao.getStringToSplit(sql, tmpIn, tmpOut);

		if ((unitList == null) || unitList.equalsIgnoreCase("")) {
			unitList = "!!SplitSignOne!!!!SplitSignTwo!! !!SplitSignTwo!!未找到单位";
		}
		// 修改或增加用户时需要用到单位列表，在此初始化

		sql = "select gnmkdm,gnmkmc from view_yhqx where yhm=? order by gnmkdm";
		String[] inputSQLPower = { userName };
		String[] outputSQLPower = { "gnmkdm", "gnmkmc" };
		String powerList = dao.getStringToSplit(sql, inputSQLPower,
				outputSQLPower);
		// 初始化全部权限列表

		sql = "select a.yhm,(a.yhm||'/'||nvl(a.xm,'-')||'/'||b.zmc||'/'||nvl(c.bmmc,a.szbm)||'/'||nvl(d.dwmc,a.dwdm))||'/'||(case a.qx when '1' then '启用' when '0' then '不启用' else a.qx end) xm,b.zmc from yhb a,yhzb b,ZXBZ_XXBMDM c,bks_dwdmb d where a.zdm<>'0001' and a.zdm=b.zdm and a.szbm=c.bmdm and a.dwdm=d.dwdm "
				+ " order by a.zdm,a.yhm";
		if(Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)){//浙江理工大学 去除单位
			sql = "select a.yhm,(a.yhm||'/'||nvl(a.xm,'-')||'/'||b.zmc||'/'||nvl(c.bmmc,a.szbm))||'/'||(case a.qx when '1' then '启用' when '0' then '不启用' else a.qx end) xm,b.zmc from yhb a,yhzb b,ZXBZ_XXBMDM c where a.zdm<>'0001' and a.zdm=b.zdm and a.szbm=c.bmdm "
				+ " order by a.zdm,a.yhm";
		}
		String[] inputSQLGroup = {};
		String[] outputSQLGroup = { "yhm", "xm" };
		List userList = dao.getList(sql, inputSQLGroup, outputSQLGroup);

		String[] inputSQLGroupPower = null;
		if ((userID == null) || "".equalsIgnoreCase(userID)) {
			sql = "select (nvl(dxq,'0')||gnmkdm) gnmkdm,(rpad(gnmkdm,7,'_')||' | '||replace(replace(nvl(dxq,'0'),'0','只读'),'1','可写')||' | '||gnmkmc) gnmkmc from view_yhqx where yhm=(select min(yhm) from yhb where yhm<>'admin') and substr(gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?) order by gnmkdm";
			inputSQLGroupPower = new String[] { userName };
		} else {
			sql = "select (nvl(dxq,'0')||gnmkdm) gnmkdm,(rpad(gnmkdm,7,'_')||' | '||replace(replace(nvl(dxq,'0'),'0','只读'),'1','可写')||' | '||gnmkmc) gnmkmc from view_yhqx where yhm=? and substr(gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?) order by gnmkdm";
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
				String logMsg = "修改用户权限,用户名为" + userPName + "。";
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
			String logMsg = "修改用户权限,用户名为" + userPName + "。";
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
			String logMsg = "修改用户权限,用户名为" + userPName + "。";
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
		String userPart = request.getParameter("newUserPart");//获取的是部门名称
		String userUnit = request.getParameter("dwText");
		String qx = request.getParameter("purview");
		String power = request.getParameter("power");
		String uN = request.getParameter("userName");
		String forward = "false";
		String logMsg = "";
		String errFlag = "";
		String[] inputSQL = null;
		String xxdm = StandardOperation.getXxdm();
		
		
		
		if(flag !=2 && !Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)){//非删除操作
			userUnit = service.getUserUnitId(userUnit,request);//根据单位名称查询单位id,查询不到id，插入一条新的记录，返回插入的id
		}
		boolean done = false;
		if ((userPassword != null) && !userPassword.equalsIgnoreCase("")) {
			userPassword = ept.encrypt(userPassword);
		}
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)){//金华职业技术学院		
		if (flag == 1) {
			logMsg = "添加新用户，用户名为：" + userID + "；所属组代码为：" + userGroup + "。";
			errFlag = "Err0014";
			inputSQL = new String[] { userID, userPassword, userGroup,
					userPName, userPart, userUnit,qx};
			done = dao.runUpdate(sql, inputSQL);
			// 添加新用户
			sql = "insert into yh_xg_qxb (select '" + inputSQL[0]
					+ "',gnmkdm,dxq from yhzqxb where zdm=?)";
			inputSQL = new String[] { userGroup };
			done = dao.runUpdate(sql, inputSQL);
			// 将用户所在组的默认权限分发个新用户
		} else if (flag == 2) {
			logMsg = "删除用户，用户名为：" + power + "。";
			errFlag = "Err0015";
			inputSQL = new String[] { DealString.toGBK(power) };
			done = dao.runUpdate(sql, inputSQL);
			// 删除用户
			sql = "delete from yh_xg_qxb where yhm=?";
			done = dao.runUpdate(sql, inputSQL);
			// 删除用户权限
		} else {
			logMsg = "修改用户信息，用户名为：" + userID + "。";
			errFlag = "Err0016";
			String getOldG = "select count(*) num from yhb where yhm=? and zdm=?";
			String oldG = dao.getOneRs(getOldG, new String[] { uN, userGroup },
					new String[] { "num" })[0];

			inputSQL = new String[] { userGroup, userPName, userPart, userUnit,qx,
					uN };
			done = dao.runUpdate(sql, inputSQL);

			if (Integer.parseInt(oldG) == 0) {
				// 修改用户信息
				sql = "delete from yh_xg_qxb where yhm=?";
				inputSQL = new String[] { uN };
				done = dao.runUpdate(sql, inputSQL);
				// 删除用户所有权限
				sql = "insert into yh_xg_qxb (select '" + uN
						+ "',gnmkdm,dxq from yhzqxb where zdm=?)";
				inputSQL = new String[] { userGroup };
				done = dao.runUpdate(sql, inputSQL);
				// 添加用户新权限
			}
		}
		}else{
			if (flag == 1) {
				logMsg = "添加新用户，用户名为：" + userID + "；所属组代码为：" + userGroup + "。";
				errFlag = "Err0014";
				inputSQL = new String[] { userID, userPassword, userGroup,
						userPName, userPart, userUnit,qx };
				// 添加新用户
				done = dao.runUpdate(sql, inputSQL);
				
				// 将用户所在组的默认权限分发给新用户
				sql = "insert into yhqxb (select '" + inputSQL[0]
						+ "',gnmkdm,dxq from yhzqxb where zdm=?)";
				inputSQL = new String[] { userGroup };
				done = dao.runUpdate(sql, inputSQL);
				
			} else if (flag == 2) {
				logMsg = "删除用户，用户名为：" + power + "。";
				errFlag = "Err0015";
				inputSQL = new String[] { DealString.toGBK(power) };
				// 删除用户
				done = dao.runUpdate(sql, inputSQL);
				
				// 删除用户权限
				sql = "delete from yhqxb where yhm=?";
				done = dao.runUpdate(sql, inputSQL);				
			} else {
				logMsg = "修改用户信息，用户名为：" + userID + "。";
				errFlag = "Err0016";
				String getOldG = "select count(*) num from yhb where yhm=? and zdm=?";
				String oldG = dao.getOneRs(getOldG, new String[] { uN, userGroup },
						new String[] { "num" })[0];

				inputSQL = new String[] { userGroup, userPName, userPart, userUnit,qx,
						uN };
				done = dao.runUpdate(sql, inputSQL);

				if (Integer.parseInt(oldG) == 0) {
					// 修改用户信息
					sql = "delete from yhqxb where yhm=?";
					inputSQL = new String[] { uN };
					done = dao.runUpdate(sql, inputSQL);
					// 删除用户所有权限
					sql = "insert into yhqxb (select '" + uN
							+ "',gnmkdm,dxq from yhzqxb where zdm=?)";
					inputSQL = new String[] { userGroup };
					done = dao.runUpdate(sql, inputSQL);
					// 添加用户新权限
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
		
		request.setAttribute("message", done ? "操作成功" : "操作失败");
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
			String logMsg = "查询用户：" + userID + "的密码。";
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
			tips = "思想教育";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
				pageCard = new String[] { "培训项目", "班级与党员联系人对应表" };
				tabNames = new String[] { "pxxmdmb", "bjdydygxb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				pageCard = new String[] { "辅导员评价等级" };
				tabNames = new String[] { "sxjy_fdypjdjdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)) {
				pageCard = new String[] { "团组织干部职务表" };
				tabNames = new String[] { "tzzzwdmb" };
			} 
			// ===============begin  2009/3/23 骆嘉伟 =================
			else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
				pageCard = new String[] { "材料类型","奖惩类型","奖惩理由" };
				tabNames = new String[] { "cllx","zjcm_jclx","zjcm_jcly" };
			}
			// ===============begin  2009/8/31 鲁宁 =================
			else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				pageCard = new String[] { "论文类别","科研项目级别","地大学工信息内容级别表","培训项目","学生层次"};
				tabNames = new String[] { "dtjsZgdzdxLwlbb","dtjsZgdzdxkyxmjbb","dtjsDdxgxxjbb","pxxmdmb","dtjs_xsccb" };
			}// ===============begin  2009/11/9 骆嘉伟 =================
			else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)){
				pageCard = new String[] { "培训项目", "学生层次" };
				tabNames = new String[] { "pxxmdmb", "dtjs_xsccb" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_CZXXZYJSXY)){
				pageCard = new String[] { "非团员培训项目","团员培训项目"};
				tabNames = new String[] { "pxxmdmb","typxxmdmb"};
			//TODO 2010-07-29 裘力俊	
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)){
				pageCard = new String[] { "团员信息异动表"};
				tabNames = new String[] { "gdby_dtjs_ydlxdmb"};
			}
			else {
				pageCard = new String[] { "培训项目", "学生层次", "等级代码" };
				tabNames = new String[] { "pxxmdmb", "dtjs_xsccb", "dtjs_djdmb" };
			}
		} else if (codeType.equalsIgnoreCase("prise")) {
			tips = "评奖评优";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
				// 浙江商业职业学院
				pageCard = new String[] { "奖学金", "荣誉称号", "测评比赛表", "寝室评比表",
						"参加活动列表", "处分列表", "智育竞赛", "体育竞赛" };
				tabNames = new String[] { "jxjdmb", "rychdmb", "zhcp_bslb",
						"zhcp_qspbb", "zhcp_cjhdb", "zhcp_cflb", "zhcp_zyjslb",
						"zhcp_tyjslb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)) {
				// 中北大学
				pageCard = new String[] { "奖学金", "荣誉称号", "体育竞赛项目维护",
						"人文素质竞赛项目维护", "创新素质竞赛项目维护" };
				tabNames = new String[] { "jxjdmb", "rychdmb", "zhcp_tyjslb",
						"zhcp_zyjslb", "zhcp_cxlb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)) {
				// 北京林业大学
				pageCard = new String[] { "奖学金类别", "奖学金" };
				tabNames = new String[] { "pjpylbwhb", "jxjdmb_tmp_bjly" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
				// 西北第二民族学院
				pageCard = new String[] { "奖学金", "荣誉称号" };
				tabNames = new String[] { "jxjdmb", "rychdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {// 其他学校用到的集体荣誉可以用jtrydmb
				// 杭州职业技术学院
				pageCard = new String[] { "奖学金", "荣誉称号", "集体荣誉","军训奖项" };
				tabNames = new String[] { "jxjdmb", "rychdmb", "pjpy_jtrydmb","jxjxdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
				// 安徽建筑工业学院
				pageCard = new String[] { "奖学金", "先进集体", "先进个人", "军训奖项",
						"学习竞赛项目" };
				tabNames = new String[] { "jxjdmb", "pjpy_jtrydmb", "rychdmb",
						"jxjxdmb", "xxjsdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)) {
				// 井冈山大学
				pageCard = new String[] { "奖学金", "专业奖学金", "荣誉称号", "军训奖项",
						"集体荣誉" };
				tabNames = new String[] { "jxjdmb", "zyjxjdmb", "rychdmb",
						"jxjxdmb", "pjpy_jtrydmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				pageCard = new String[] { "奖学金", "奖学金类别",  "荣誉称号", "军训奖项", "等级考试" };
				tabNames = new String[] { "jxjdmb", "jxjlbdmb", "rychdmb", "jxjxdmb",
						"djksdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
				// 武汉理工大学
				pageCard = new String[] { "奖学金", "荣誉称号", "先进班级类别", "军训奖项" };
				tabNames = new String[] { "jxjdmb", "rychdmb", "xjbjlbdmb",
						"jxjxdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)) {
				pageCard = new String[] { "奖学金", "荣誉称号", "军训奖项" ,"奖学金类别"};
				tabNames = new String[] { "jxjdmb", "rychdmb", "jxjxdmb", "jxjlbdmb" };				
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)) {//上海出版印刷
				pageCard = new String[] { "奖学金", "荣誉称号", "军训奖项" ,"奖学金类别"};
				tabNames = new String[] { "jxjdmb", "rychdmb", "jxjxdmb", "jxjlbdmb" };
			} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
				pageCard = new String[] { "奖学金", "荣誉称号", "军训奖项", "体育达标" };
				tabNames = new String[] { "jxjdmb", "rychdmb", "jxjxdmb" , "tydbdmb"};
			} else if (Globals.XXDM_ZJJJZYJSXY.equalsIgnoreCase(xxdm)) {
				pageCard = new String[] { "奖学金", "荣誉称号", "军训奖项", "德育考核等级"};
				tabNames = new String[] { "jxjdmb", "rychdmb", "jxjxdmb", "dycjdmb" };
			} else if (Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm)) {
				pageCard = new String[] { "奖学金", "荣誉称号", "军训奖项","素质等级" };
				tabNames = new String[] { "jxjdmb", "rychdmb", "jxjxdmb","szdjdmb" };
			} else if (Globals.XXDM_SZGYYQZYJSXY.equalsIgnoreCase(xxdm)) {
				pageCard = new String[] { "奖学金", "荣誉称号", "军训奖项", "综合素质加减分原因" };
				tabNames = new String[] { "jxjdmb", "rychdmb", "jxjxdmb", "szyc_jjfyyb" };
			} else if (Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)) {
				pageCard = new String[] { "奖学金", "荣誉称号","德育操作项目", "军训奖项" };
				tabNames = new String[] { "jxjdmb", "rychdmb","dycxxmb", "jxjxdmb" };
			} else if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
				pageCard = new String[] { "奖学金","奖学金类别", "荣誉称号", "军训奖项" };
				tabNames = new String[] { "jxjdmb", "jxjlbdmb","rychdmb", "jxjxdmb" };
			} else if (Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)) {//金华职业技术学院
				pageCard = new String[] { "奖学金","军训奖项","等级考试分数" };
				tabNames = new String[] { "jxjdmb","jxjxdmb","jhzy_djksfsb" };		
			} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {//浙江传媒
				pageCard = new String[] { "奖学金类别", "奖学金", "荣誉称号",
						"校外奖学金人数", "违纪类型", "请假类型", "学风检查类型", "旷课处分" , "班级大类设置"};
				tabNames = new String[] { "jxjlbdmb", "jxjdmb", "rychdmb", "zjcm_xwjxjrs", "pjpy_xfjs_wjlxdmb","pjpy_xfjs_qjlxdmb",
						"pjpy_xfjs_jclxdmb", "pjpy_xfjs_kkcfszb" , "zjcm_wlkdmb"};	
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				pageCard = new String[] { "奖学金","荣誉称号"};
				tabNames = new String[] { "jxjdmb","rychdmb"};
				request.setAttribute("showcsmz", "yes");
				request.setAttribute("xybmdm", xydm);
			}else if(Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {//厦门理工大学
				pageCard = new String[] { "奖学金类别","奖学金", "荣誉称号" };
				tabNames = new String[] { "xmlg_jxjlbdmb","xmlg_jxjdmb", "xmlg_rychdmb"};				
			}else if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){//广州大学
				pageCard = new String[] { "奖学金类别", "奖学金", "荣誉称号", "军训奖项",
						"行为表现分项目", "突出表现分项目" };
				tabNames = new String[] { "jxjlbdmb", "jxjdmb", "rychdmb",
						"jxjxdmb", "xwbxfdmb", "tcbxfdmb" };				
			} else if (Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(xxdm)) {// 浙江交通职业技术学院
				pageCard = new String[] { "奖学金类别代码维护", "奖学金代码维护", "荣誉称号代码维护",
						"综合素质测评二级代码维护", "军训奖项代码维护", "操行分一级类别（区域）",
						"操行分二级类别（类别）", "操行分三级类别（细项）" };
				tabNames = new String[] { "jxjlbxxdmb", "jxjdmb", "rychdmb",
						"zhszcpdmb", "jxjxdmb", "zjjt_cxf_dj1", "zjjt_cxf_dj2",
						"zjjt_cxf_dj3" };		
			}  else if(Globals.XXDM_GHZYJSXY.equalsIgnoreCase(xxdm)){
				pageCard = new String[] { "奖学金类别代码维护", "奖学金代码维护", "荣誉称号代码维护",
						"综合素质测评二级代码维护", "军训奖项代码维护", "试题所属表","班级指标代码表" ,
						"班级荣誉分设置","班级荣誉分批次设置","寝室指标代码表","寝室荣誉分设置","寝室荣誉分批次设置 ",
						"br","荣誉减分具体项目维护"};
				tabNames = new String[] { "jxjlbxxdmb", "jxjdmb", "rychdmb",
						"zhszcpdmb", "jxjxdmb", "wjdc_stssb" ,"bjzbdmb","bjryfszb","bjryfpcb",
						"qszbdmb","qsryfszb","qsryfpcb","br","ghxy_ryjfxmb"};		
			} else if(Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)){// 海南大学
				pageCard = new String[] { "奖学金类别代码维护", "奖学金代码维护", "荣誉称号代码维护",
					"综合素质测评二级代码维护", "军训奖项代码维护","发展性素质项目维护",
						"德育评议项目维护"};
				tabNames = new String[] { "jxjlbxxdmb", "jxjdmb", "rychdmb",
						"zhszcpdmb", "jxjxdmb","hndx_fzxszxmb","hndx_dypyxmb"};		
			} else if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {//闽江学院
				pageCard = new String[] { "奖学金类别代码维护", "奖学金代码维护", "荣誉称号代码维护",
						"综合素质测评二级代码维护", "军训奖项代码维护" ,"集体荣誉称号维护"};
				tabNames = new String[] { "jxjlbxxdmb", "jxjdmb", "rychdmb",
						"zhszcpdmb", "jxjxdmb","jtrychdmb"};				
			} else if (Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {
				pageCard = new String[] { "奖学金类别代码维护", "奖学金代码维护", "荣誉称号代码维护",
						"综测代码维护", "试题所属表"};
				tabNames = new String[] { "jxjlbxxdmb", "jxjdmb", "rychdmb",
						"zhszcpdmb", "wjdc_stssb"};		
			} else {
				pageCard = new String[] { "奖学金类别代码维护", "奖学金代码维护", "荣誉称号代码维护",
						"综测代码维护"};
				tabNames = new String[] { "jxjlbxxdmb", "jxjdmb", "rychdmb",
						"zhszcpdmb"};				
			}
		} else if (codeType.equalsIgnoreCase("comm")) {
			tips = "对外交流";
			pageCard = new String[] { "对外交流方式", "对外交流类别", "对外交流项目" };
			tabNames = new String[] { "dwjlfsdmb", "dwjlfldmb", "dwjlxmdmb" };
		} else if (codeType.equalsIgnoreCase("assis")) {
			tips = "学生资助";
			// pageCard = new String[] { "学校助学贷款", "NSEP级别", "NSEP项目", "外设助学金",
			// "困难补助", "收入来源项目", "困难程度", "校内助学金", "证明材料", "国家奖学金等级" };
			// tabNames = new String[] { "xxdxjlbdmb", "nsepjbdmb", "nsepxmdmb",
			// "wszxjdmb", "knbzdmb", "bjlydx_knssrly", "kncddmb",
			// "xnzxjdmb", "zmcldmb", "gjjxjdjdmb" };
			pageCard = new String[] { "外设助学金","临时补助" };
			tabNames = new String[] { "wszxjdmb","lsknbzdmb" };
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)) {// 北京林业大学
				pageCard = new String[] { "学校助学贷款", "外设助学金", "困难补助", "收入来源项目" };
				tabNames = new String[] { "xxdxjlbdmb", "wszxjdmb", "knbzdmb",
						"bjlydx_knssrly" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)) {// 深圳信息职业技术学院
				pageCard = new String[] { "学校助学贷款", "外设助学金", "困难补助", "困难程度",
						"校内助学金", "证明材料", "国家奖学金等级" };
				tabNames = new String[] { "xxdxjlbdmb", "wszxjdmb", "knbzdmb",
						"kncddmb", "xnzxjdmb", "zmcldmb", "gjjxjdjdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)) {// 漳州师范
				pageCard = new String[] { "学校助学贷款", "外设助学金", "困难补助", "资助等级" };
				tabNames = new String[] { "xxdxjlbdmb", "wszxjdmb", "knbzdmb",
						"zzsf_xszzdjdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {// 广东女子职业技术学院
				pageCard = new String[] { "奖学金", "助学金", "困难补助", "贷款类型", "年利率" };
				tabNames = new String[] { "GdNZZY_xszz_jzjdmb",
						"GdNZZY_xszz_zxjdmb", "GdNZZY_xszz_knbzdmb",
						"gdnzzyxy_dklxdmb", "gdnzzyxy_dknlldmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {// 江苏信息职业技术学院
				pageCard = new String[] { "学校助学贷款", "外设助学金", "困难补助", "国家助学金等级" };
				tabNames = new String[] { "xxdxjlbdmb", "wszxjdmb", "knbzdmb",
						"jsxx_gjzxjdjdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {// 浙江机电职业技术学院
				pageCard = new String[] { "外设助学金", "国家助学金", "临时困难补助", "学费减免" };
				tabNames = new String[] { "wszxjdmb", "zjjd_gjzxjdmb",
						"zjjd_lsbzdmb", "ZJJD_XFJMDMB" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {// 中国矿业大学
				pageCard = new String[] { "特殊人群" };
				tabNames = new String[] { "xszz_zgkd_tsrqdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {// 北京联合大学
				pageCard = new String[] { "外设助学金", "国家助学金", "临时补助" };
				tabNames = new String[] { "wszxjdmb", "xszz_bjlh_gjzxjdmb", "lsknbzdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBZYJSXY)) {// 宁波职业技术学院
				pageCard = new String[] { "外设助学金", "捐款人角色" };
				tabNames = new String[] { "wszxjdmb", "nbzy_syjj_jkrjsdmb" };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {// 云南艺术
				pageCard = new String[] { "外设助学金","临时补助","奖学金等级","困难等级" };
				tabNames = new String[] { "wszxjdmb","lsknbzdmb","ynys_zzdjb", "kncddmb" };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {// 金华职业技术学院
				pageCard = new String[] { "帮困助学基金等级","国家助学金等级" };
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
			tips = "奖助学金";
			pageCard = new String[] { "奖助学金类别" };
			tabNames = new String[] { "dzdxZxjlbb" };
			
		} else if (codeType.equalsIgnoreCase("work")) {
			tips = "勤工助学";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)) {
				// 上海工程 云南艺术 漳州师范
				pageCard = new String[] { "岗位性质", "用人单位", "勤工俭学时间", "校区", "计酬方式" };
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "kcjsjdmb",
						"dm_zju_xq", "qgzx_jcfsdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				// 北京联合大学
				pageCard = new String[] { "岗位性质", "用人单位", "岗位信息", "计酬方式" };
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "gwdmb", "qgzx_jcfsdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
				// 技术信息职业技术学院
				pageCard = new String[] { "岗位性质", "用人单位", "校区", "计酬方式" };
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "dm_zju_xq", "qgzx_jcfsdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				// 重庆科技学院 长沙民政学院
				pageCard = new String[] { "岗位名称", "岗位性质", "用人单位", "计酬方式"};
				tabNames = new String[] { "gwmcdmb", "gwxzdmb", "yrdwdmb", "qgzx_jcfsdmb"};
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
				// 武汉理工大学
				pageCard = new String[] { "岗位性质", "用人单位", "岗位信用度", "计酬方式"};
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "xyddmb", "qgzx_jcfsdmb" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
				//浙江机电职业技术学院
				pageCard = new String[] { "岗位性质", "用人单位", "勤工俭学时间", "计酬方式" };
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "kcjsjdmb","qgzx_jcfsdmb" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDXHXXY)){
				//武汉理工大学华夏学院
				pageCard = new String[] { "岗位性质", "用工部门", "计酬方式"};
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "qgzx_jcfsdmb" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_CZXXZYJSXY)){
				//常州信息职业技术学院
				pageCard = new String[] { "岗位性质", "用人单位", "储蓄银行", "计酬方式"};
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "czxx_yhdmb", "qgzx_jcfsdmb"};
			} else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				//中国地质大学
				pageCard = new String[] { "岗位性质", "用人单位", "计酬方式", "职位性质"};
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "qgzx_jcfsdmb", "qgzx_zwxzdmb"};
			}else {
				pageCard = new String[] { "岗位性质", "用人单位", "计酬方式"};
				tabNames = new String[] { "gwxzdmb", "yrdwdmb", "qgzx_jcfsdmb"};
			}
		} else if (codeType.equalsIgnoreCase("health")) {
			tips = "心理健康";
			pageCard = new String[] { "心理测验项目", "测验项目结果表", "需要特别关心学生类别", "试题所属题型",
					"试题难度级别", "试题测试类别","测试项目因子","卡特尔人格测试因子评语","试卷总分评语","心理测试类别","心理问题类型", "大学生人格测试因子评语"};
			tabNames = new String[] { "xlcsxmdmb", "xlcsjgdmb", "tbgxxslbdmb",
					"stlxdmb", "stndjbdmb", "xljk_stsslbdmb","csxmyzb","zcbzpyb","sjzfpyb","xlcslbdmb","xlwtlxdmb", "rgysyzdmb"};
		} else if (codeType.equalsIgnoreCase("train")) {
			tips = "军训管理";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
				pageCard = new String[] { "军训服号型表", "解放鞋尺码表", "军训帽头围表",
						"文化衫尺寸表", "军训奖项"};
				tabNames = new String[] { "zxfhxb", "jfxcmb", "zxmtwb",
						"whsccb", "jxjxdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				// 宁波理工
				pageCard = new String[] { "军训服号型表", "解放鞋尺码表", "军训帽头围表",
						"文化衫尺寸表", "考勤状态", "学生军训获奖类别", "军训建制等级", "军训单位", "军训职位",
						"军训编制职位", "军训服装" };
				tabNames = new String[] { "zxfhxb", "jfxcmb", "zxmtwb",
						"whsccb", "jxkqztb", "jxhjlbb", "jxjzdj","nblg_jxgl_jxdw",
						"nblg_jxgl_jxzw", "nblg_jxgl_bzzwb", "nblg_jxgl_jxfz" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
				// 淮海工学院
				pageCard = new String[] { "军训服号型表", "解放鞋尺码表", "军训帽头围表",
						"文化衫尺寸表", "考勤状态", "学生军训获奖类别", "军训建制等级", "军训单位", "军训职位",
						"军训编制职位" };
				tabNames = new String[] { "zxfhxb", "jfxcmb", "zxmtwb",
						"whsccb", "jxkqztb", "jxhjlbb", "jxjzdj","nblg_jxgl_jxdw",
						"nblg_jxgl_jxzw", "nblg_jxgl_bzzwb" };
			} else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				//中国地质大学
				pageCard = new String[] { "军训服号型表", "解放鞋尺码表", "军训帽头围表",
						"文化衫尺寸表", "考勤状态", "军训建制等级","军训编制职位","国防生连队" };
				tabNames = new String[] { "zxfhxb", "jfxcmb", "zxmtwb",
						"whsccb", "jxkqztb", "jxjzdj","nblg_jxgl_bzzwb","zgdd_gfsldb" };
			} else if (Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)) {
				//金华职业
				pageCard = new String[] { "军训服号型表", "解放鞋尺码表", "军训帽头围表",
						"文化衫尺寸表", "考勤状态", "组织领导职务表"  };
				tabNames = new String[] { "zxfhxb", "jfxcmb", "zxmtwb",
						"whsccb", "jxkqztb",  "jhzy_zwb" };
			} else if (Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)) {
				// 贵州大学
				pageCard = new String[] { "军训服号型表", "解放鞋尺码表", "军训帽头围表",
						"文化衫尺寸表", "考勤状态", "军训建制等级", "军训编制职位", "征兵报名类型", "免缓训类型" };
				tabNames = new String[] { "zxfhxb", "jfxcmb", "zxmtwb",
						"whsccb", "jxkqztb", "jxjzdj", "nblg_jxgl_bzzwb",
						"jxgl_rwzblxb", "jxgl_mhxlxb" };
			} else {
				pageCard = new String[] { "军训服号型表", "解放鞋尺码表", "军训帽头围表",
						"文化衫尺寸表", "考勤状态", "军训建制等级", "军训编制职位" };
				tabNames = new String[] { "zxfhxb", "jfxcmb", "zxmtwb",
						"whsccb", "jxkqztb", "jxjzdj", "nblg_jxgl_bzzwb" };
			}
		} else if (codeType.equalsIgnoreCase("discip")) {
			tips = "违纪处分";
			pageCard = new String[] { "处分类别", "处分原因" };
			tabNames = new String[] { "cflbdmb", "cfyydmb" };
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				pageCard = new String[] { "处分类别", "处分原因", "考试违纪处分类别", "考试违纪处分原因" };
				tabNames = new String[] { "cflbdmb", "cfyydmb", "ks_cflbdmb", "ks_cfyydmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
				pageCard = new String[] { "处分类别", "处分原因", "处分扣分" };
				tabNames = new String[] { "cflbdmb", "cfyydmb", "zjlg_wjkfb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {
				pageCard = new String[] { "处分类别", "处分原因", "处分扣分" };
				tabNames = new String[] { "cflbdmb", "cfyydmb", "zjlg_wjkfb" };
			}
		} else if (codeType.equalsIgnoreCase("other")) {
			tips = "其他数据";
			pageCard = new String[] { "保险公司", "保险险种" };
			tabNames = new String[] { "bxgsdmb", "bxxzb" };
			if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {
				pageCard = new String[] { "保险公司", "保险险种", "保险档次" };
				tabNames = new String[] { "bxgsdmb", "bxxzb", "gdnzzy_bxdcdmb" };
			}
		} else if (codeType.equalsIgnoreCase("social")) {
			tips = "社会工作";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				pageCard = new String[] { "任职部门", "学生社团", "社会活动性质", "学生干部培训项目",
						"社团申请类别" };
				tabNames = new String[] { "rzbmdmb", "stdmb", "shhdxzdmb",
						"xsgbpxxmdmb", "stsqlbb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				pageCard = new String[] { "任职部门", "学生社团", "社团申请类别", "社团评优项目" };
				tabNames = new String[] { "rzbmdmb", "stdmb", "stsqlbb",
						"stpyxmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				pageCard = new String[] { "学生组织所属科目", "学生组织干部代码" };
				tabNames = new String[] { "nblg_sskmdmb", "nblg_xsgbzldmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
				pageCard = new String[] { "任职部门", "学生社团", "社会活动性质", "学生干部培训项目",
						"社团申请类别", "社团评优项目", "活动信息" };
				tabNames = new String[] { "rzbmdmb", "stdmb", "shhdxzdmb",
						"xsgbpxxmdmb", "stsqlbb", "stpyxmb", "zgdd_shgz_hdb" };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
				pageCard = new String[] { "所属组织", "所任职务"};
				tabNames = new String[] { "sszzdmb", "srzwdmb"};
			}else if(Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)){
				pageCard = new String[] { "任职部门", "学生社团", "团队性质", "学生干部培训项目",
						"社团申请类别", "社团评优项目" };
				tabNames = new String[] { "rzbmdmb", "stdmb", "shhdxzdmb",
						"xsgbpxxmdmb", "stsqlbb", "stpyxmb" };				
			}else {
				pageCard = new String[] { "任职部门", "学生社团", "学生干部培训项目",
						"社团申请类别", "社团评优项目" };
				tabNames = new String[] { "rzbmdmb", "stdmb", 
						"xsgbpxxmdmb", "stsqlbb", "stpyxmb" };
			}
		} else if (codeType.equalsIgnoreCase("dorm")) {
			tips = "公寓管理";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				pageCard = new String[] { "宿舍楼栋", "值班人员", "卫生检查部门", "公寓维修人员",
						"纪律类别", "外住类型" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY)) {
				pageCard = new String[] { "宿舍楼栋", "值班人员", "卫生检查部门", "公寓维修人员",
						"纪律类别", "纪律事由", "外住类型" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "gygl_wjsydmb", "wzlxdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				pageCard = new String[] { "宿舍楼栋", "值班人员", "卫生检查部门", "公寓维修人员",
						"纪律类别", "学生奖励", "学生惩罚", "文明寝室类别", "外住类型" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "xsjldmb", "xscfdmb",
						"qslbdmb", "wzlxdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				pageCard = new String[] { "宿舍楼栋", "值班人员", "卫生检查部门", "公寓维修人员",
						"纪律类别", "外住类型" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb" };
			} else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)){
				pageCard = new String[] { "宿舍楼栋",  "公寓维修人员",
						"纪律类别", "外住类型" };
				tabNames = new String[] { "sslddmb","gywxryb", "gygl_zswjlbdmb", "wzlxdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {//中国地质大学
				pageCard = new String[] { "园区","宿舍楼栋", "纪律类别","外住类型","卫生检查等级","文明寝室类别" };
				tabNames = new String[] { "yqdmb","sslddmb", "gygl_zswjlbdmb", "wzlxdmb","zgdd_wsjcdj","qslbdmb" };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
				pageCard = new String[] { "宿舍楼栋", "值班人员", "文明寝室类别","卫生检查部门", "公寓维修人员",
						"纪律类别", "外住类型" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "qslbdmb","gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb" };
			}else if(Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)){
				pageCard = new String[] { "宿舍楼栋", "值班人员", "文明寝室类别(评比等级)",
						"卫生检查部门", "公寓维修人员", "纪律类别", "外住类型", "卫生检查等级",
						"学生奖励", "学生惩罚", "处分等级" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "qslbdmb",
						"gywsjcbmb", "gywxryb", "gygl_zswjlbdmb", "wzlxdmb",
						"hhgxy_wsjcdj", "xsjldmb", "xscfdmb",
						"hhgxy_cfdj" };
			}else if(Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)){
				pageCard = new String[] { "宿舍楼栋", "值班人员", "卫生检查部门", 
						"纪律类别", "外住类型","维修内容" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gygl_zswjlbdmb", "wzlxdmb","wxnrdmb" };
			}else if(Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)){
				pageCard = new String[] { "宿舍楼栋", "值班人员", "卫生检查部门", "公寓维修人员",
						"纪律类别", "外住类型","内务卫生等级"};
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb","nwwsdjdmb" };
			}else if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)){
				pageCard = new String[] { "宿舍楼栋", "值班人员", "卫生检查部门", "公寓维修人员",
						"纪律类别", "外住类型", "自管会部门","大功率电器","党建考核对象","公寓支部" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb", "zghbmxxb","jhzy_dgldqb","gygl_khdxb","gyzbb" };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)) {
				pageCard = new String[] { "公寓区","宿舍楼栋", "值班人员", "卫生检查部门", "公寓维修人员",
						"纪律类别", "外住类型" };
				tabNames = new String[] { "yqdmb","sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb"};
			} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {// 浙江传媒
				pageCard = new String[] { "宿舍楼栋", "值班人员", "卫生检查部门", "公寓维修人员",
						"纪律类别", "外住类型", "住宿纪律处理类型", "卫生检查等级" };
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb", "zsjlcllxb",
						"zjcm_wsjcdjb" };
			}else {
				pageCard = new String[] { "宿舍楼栋", "值班人员", "卫生检查部门", "公寓维修人员",
						"纪律类别", "外住类型","卫生检查等级","公寓区"};
				tabNames = new String[] { "sslddmb", "zbrydmb", "gywsjcbmb",
						"gywxryb", "gygl_zswjlbdmb", "wzlxdmb","zjcm_wsjcdjb","yqdmb"};
		    }
		} else if (codeType.equalsIgnoreCase("dailyAffair")) {
			tips = "日常事务";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				pageCard = new String[] { "经办人", "车站列表", "欠费类型", "行为",
						"收假报到学年 ", "收假报到学期", "诚信", "品德表现" };
				tabNames = new String[] { "jbrxxb", "czdmb", "qflxdmb",
						"rcgl_dmwhb" + "!!rcgl_rcxw",
						"rcgl_dmwhb" + "!!rcgl_xn", "rcgl_dmwhb" + "!!rcgl_xq",
						"rcgl_dmwhb" + "!!rcgl_xscx",
						"rcgl_dmwhb" + "!!rcgl_xspd" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NBCSZYJSXY)){//宁波城市职业技术学院
				pageCard = new String[] { "经办人", "车站列表", "欠费类型", "补办原因代码表","预约场地","预约设备","学生指导服务中心部门","请假类型" };
				tabNames = new String[] { "jbrxxb", "czdmb", "qflxdmb",
						"bbyydmb","cdyyb","sbyyb","xslfslbmdmb","kqgl_xskqdmb"};				
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GUIZHDX)){
				pageCard = new String[] { "经办人", "车站列表", "欠费类型", "补办原因代码表",
						"预约场地", "预约设备", "留言类型", "留言评价", "项目名称", 
						"实物发放类型" ,"出险类型", "学生证补发类型"};
				tabNames = new String[] { "jbrxxb", "czdmb", "qflxdmb",
						"bbyydmb", "cdyyb", "sbyyb", "rcsw_lylxb",
						"rcsw_lypjb", "rcsw_zxzm_xmdmb", "rcsw_swfflxb","rcsw_cxlx", "xszbblxdmb" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_CSDLZYJSXY)){//常沙电力
				pageCard = new String[] { "经办人", "车站列表", "欠费类型", "补办原因代码表",
						"预约场地", "预约设备", "留言类型", "留言评价", "项目名称", "实物发放类型" ,"出险类型","旷课课程"};
				tabNames = new String[] { "jbrxxb", "czdmb", "qflxdmb",
						"bbyydmb", "cdyyb", "sbyyb", "rcsw_lylxb",
						"rcsw_lypjb", "rcsw_zxzm_xmdmb", "rcsw_swfflxb","rcsw_cxlx","bks_kcdm"};
			}else {
				pageCard = new String[] { "经办人", "车站列表", "欠费类型", "补办原因代码表",
						"预约场地", "预约设备", "留言类型", "留言评价", "项目名称", "实物发放类型" ,"出险类型"};
				tabNames = new String[] { "jbrxxb", "czdmb", "qflxdmb",
						"bbyydmb", "cdyyb", "sbyyb", "rcsw_lylxb",
						"rcsw_lypjb", "rcsw_zxzm_xmdmb", "rcsw_swfflxb","rcsw_cxlx"};
			}
		} else if (codeType.equalsIgnoreCase("fdyxx")) {
			tips = "思政队伍";
			if (xxdm.endsWith(Globals.XXDM_XBEMY)) {                //西北二民院
				pageCard = new String[] { "职务列表", "班干部种类", "思政考核等级","评价指标类别","评价群组" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "szdwkhdjdmb","szdw_fdykhstdlb","szdw_fdykhqzb" };
			} else if (xxdm.endsWith(Globals.XXDM_AHJZGYXY)) {      //安徽建筑工业学院
				pageCard = new String[] { "职务列表", "学生干部种类", "学生干部面试项目",
						"学生干部评比项目", "学生干部帐户维护","评价指标类别","评价群组" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","szdw_fdykhstdlb","szdw_fdykhqzb" };
			}else if (xxdm.endsWith(Globals.XXDM_WHLGDX)) {      //武汉理工大学
				pageCard = new String[] { "职务列表", "学生干部种类", "学生干部面试项目",
						"学生干部评比项目", "学生干部帐户维护","辅导员学历维护","辅导员学位维护",
						"辅导员最后学位所属学科维护","辅导员从事工作维护","辅导员职称维护",
						"辅导员级别维护","评价指标类别","评价群组"};
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","fdyxlwhb","fdyxwwhb","fdyzhxlssxkb",
						"fdycsgzb","fdyzcb","fdyjbb","szdw_fdykhstdlb","szdw_fdykhqzb"};
			}else if (xxdm.endsWith(Globals.XXDM_NBLGXY)) {      //宁波理工
				pageCard = new String[] { "职务列表", "人员类别表","专业技术等级表","班干部种类", "学生干部面试项目",
						"学生干部评比项目", "学生干部帐户项目维护","评价指标类别","评价群组" };
				tabNames = new String[] { "nblg_szdwzwdmb", "nblg_szdwrylbb","nblg_szdwzyjsdjb","sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","szdw_fdykhstdlb","szdw_fdykhqzb" };
			}else if (xxdm.endsWith(Globals.XXDM_ZGDZDX)) {      //中国地质大学
				pageCard = new String[] { "职务列表", "班干部种类", "学生干部面试项目",
						"学生干部评比项目", "学生干部帐户维护","学术科技成果类型","科技成果级别","评价指标类别","评价群组" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","fzjy_sbxskjcglxdmb","fzjy_cgjb","szdw_fdykhstdlb","szdw_fdykhqzb"};
			}else if (xxdm.endsWith(Globals.XXDM_ZGKYDX)) {      //中國礦業大學
				pageCard = new String[] { "职务列表", "班干部种类", "学生干部面试项目",
						"学生干部评比项目", "学生干部帐户维护","文档文件种类表","评价指标类别表","评价群组" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","szdw_wdzlb","szdw_fdykhstdlb","szdw_fdykhqzb" };
			}else if(xxdm.endsWith(Globals.XXDM_YCWSZYJSXY)) {      //盐城卫生职业技术学院
				pageCard = new String[] { "教师职务列表", "班干部种类", "学生干部面试项目",
						"学生干部评比项目", "学生干部帐户维护","评价指标类别","参与评价群组","学生会干部种类" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","szdw_fdykhstdlb","szdw_fdykhqzb","sxjy_xshgbzlb" };
			}else if(xxdm.endsWith(Globals.XXDM_JHZYJSXY)) {      //金华职业技术学院
				pageCard = new String[] { "教师职务列表", "班干部种类", "学生干部面试项目",
						"学生干部评比项目", "学生干部帐户维护","评价指标类别","参与评价群组","学生总会干部种类",Base.YXPZXY_KEY+"学生会干部","心理健康学生会干部种类" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","szdw_fdykhstdlb","szdw_fdykhqzb","sxjy_xshgbzlb","sxjy_xygbzlb","sxjy_xljkgbzlb" };
			} else if (xxdm.endsWith(Globals.XXDM_XMLGXY)) { // 厦门理工学院
				pageCard = new String[] { "职务列表", "班干部种类", "学生干部面试项目",
						"学生干部评比项目", "学生干部帐户维护", "评价指标类别", "评价群组", "安全报告类型",
						"安全报告名称", "总结类型", "工作案例分类", "工作案例项目类型", "工作建议项目类型" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb", "szdw_fdykhstdlb",
						"szdw_fdykhqzb", "xmlg_szdw_aqbglx",
						"xmlg_szdw_aqbgmc", "xmlg_szdw_jhzjlx",
						"xmlg_szdw_gzalfl", "xmlg_szdw_gzallx",
						"xmlg_szdw_gzjylx" };
			}else if(xxdm.endsWith(Globals.XXDM_ZJLG)) {      //浙江理工
				pageCard = new String[] { "职务列表", "班干部种类", "学生干部面试项目",
						"学生干部评比项目", "学生干部帐户维护","评价指标类别","评价群组","分工类型","队伍类别" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","szdw_fdykhstdlb","szdw_fdykhqzb","szdw_fglxdmb","fdydwlbdmb" };
			}else {
				pageCard = new String[] { "职务列表", "班干部种类", "班干部种类级别","学生干部面试项目",
						"学生干部评比项目", "学生干部帐户维护","评价指标类别","评价群组" };
				tabNames = new String[] { "zwb", "sxjy_bjgbzlb","bjgbzljbb", "xsgbmsxmb",
						"xsgbpbxmb", "xsgbzhwhbb","szdw_fdykhstdlb","szdw_fdykhqzb" };
			}
		} else if (codeType.equalsIgnoreCase("hzjy")) {
			tips = "合作教育";
			pageCard = new String[] { "合作教育项目", Base.YXPZXY_KEY+"时间设置表" };
			tabNames = new String[] { "hzjyxmb", "hzjy_xysjszb" };
		} else if (codeType.equalsIgnoreCase("sztz")) {
			tips = "素质拓展";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {// 西南民族大学
				pageCard = new String[] { "拓展班级" };
				tabNames = new String[] { "sztz_bjdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)) {// 江苏信息职业技术学院,西昌学院
				pageCard = new String[] { "科目设立", "拓展项目", "申请申报理由", "奖项类别" };
				tabNames = new String[] { "sztz_kmdmb", "sztz_xmdmb",
						"sztz_sqsblyb", "sztz_hjjbdmb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {
				// ==============begin luojw 2009/6/4=================
				pageCard = new String[] { "科目设立", "活动内容", "活动内容属性", "下拉菜单属性" };
				tabNames = new String[] { "sztz_kmdmb", "zgkd_hdnrb",
						"zgkd_hdnrsxb", "zgkd_xlcdsxb" };
				// ==============end luojw 2009/6/4=================
			} else if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){//柳州职业技术学院
				tips = "第二课堂活动";
				pageCard = new String[] { "科目设立"};
				tabNames = new String[] { "sztz_kmdmb"};
			}
			else {
				pageCard = new String[] { "科目设立"};
				tabNames = new String[] { "sztz_kmdmb"};
			}
		} else if (codeType.equalsIgnoreCase("student")) {
			tips = "学生信息";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				// 上海工程
				pageCard = new String[] { "学籍状态", "归档资料", "异动类别"};
				tabNames = new String[] { "dm_zju_xjzt","stu_gdzlb", "dm_ydlb"};
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
				pageCard = new String[] { "学籍状态","到档情况", "归档资料", "异动类别"};
				tabNames = new String[] { "dm_zju_xjzt","xbemy_ddqkdmb", "stu_gdzlb", "dm_ydlb" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				// 北京联合
				pageCard = new String[] { "学籍状态","异动类别", "宗教信息", "归档资料" };
				tabNames = new String[] { "dm_zju_xjzt","dm_ydlb", "zjxxb" ,"stu_gdzlb"};
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				// 长沙民族
				pageCard = new String[] { "学籍状态","毕业证发放状态", "异动类别","归档资料" };
				tabNames = new String[] { "dm_zju_xjzt","byzffztdmb", "dm_ydlb","stu_gdzlb"};
			} else if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
				//中国地质大学
				pageCard = new String[] { "学籍状态","异动类别","归档资料","类型","类别"};
				tabNames = new String[] { "dm_zju_xjzt","dm_ydlb","stu_gdzlb","stu_lxdmb","stu_lbdmb"};
		    } else if(Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)){
		    	//安徽建筑工业学院
				pageCard = new String[] { "学籍状态","异动类别","归档资料","特殊类型"};
				tabNames = new String[] { "dm_zju_xjzt","dm_ydlb","stu_gdzlb","stu_lxdmb"};		    	
		    }else if(Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)){
		    	//浙江理工
				pageCard = new String[] { "学籍状态","异动类别","归档资料","交流项目"};
				tabNames = new String[] { "dm_zju_xjzt","dm_ydlb","stu_gdzlb","zjlg_dwjlxm"};		    	
		    }else if(Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)){
		    	//武汉理工华夏学院
		    	pageCard = new String[] { "学籍状态","银行","异动类别","归档资料"};
				tabNames = new String[] { "dm_zju_xjzt","dmk_yh", "dm_ydlb","stu_gdzlb"};
		    }else if(Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)){
		    	//三江学院
		    	pageCard = new String[] { "学籍状态","异动类别","归档资料","归档资料类别"};
				tabNames = new String[] { "dm_zju_xjzt","dm_ydlb","stu_gdzlb","gdzllbb"};
		    } else if (Globals.XXDM_NJJS.equalsIgnoreCase(xxdm)) {
				// 南京技师
		    	// ------------2010/5/13 edit by luojw-------------
				pageCard = new String[] { "学籍状态", "异动类别", "归档资料", "银行", "学生层次",
						"户口性质表","入学前文化程度" };
				tabNames = new String[] { "dm_zju_xjzt", "dm_ydlb",
						"stu_gdzlb", "dmk_yh", "dtjs_xsccb", "xsxx_hkxzb","xsxx_rxqwhcdb" };
				// ------------end-------------
			} else if(Globals.XXDM_GUIZHDX.equalsIgnoreCase(xxdm)){
				//贵州大学
				//------------2010/9/28 edit by qlj-------------
				pageCard = new String[] { "学籍状态", "异动类别", "归档资料", "银行","档案登记" };
				tabNames = new String[] { "dm_zju_xjzt", "dm_ydlb",
						"stu_gdzlb", "dmk_yh","xsxx_daxxdj"};
				// ------------end-------------
			}else {
				pageCard = new String[] { "学籍状态", "异动类别", "归档资料", "银行" };
				tabNames = new String[] { "dm_zju_xjzt", "dm_ydlb",
						"stu_gdzlb", "dmk_yh" };
			}
		} else if (codeType.equalsIgnoreCase("jygl")) {
			
			tips = "就业管理";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJJJGLZYXY)) {
				pageCard = new String[] { "高职专业", "高职培养方式",
						"毕业去向", "地区流向", "单位所在地区", "居住证或蓝表标志位", "生源地区主管部门", "单位性质",
						"单位性质2", "行业分类", "生源地区", "性别", "学历", "学校", "主管部门", "政治面貌" };
				tabNames = new String[] { "dmk_bzgzzy", "dmk_bzpyfs",
						"dmk_byqx", "dmk_dqlx", "dmk_dwdq",
						"dmk_jzzhlbbzw", "dmk_sydzgbm", "dmk_dwxz", "dmk_dwxz2",
						"dmk_hyfl", "dmk_sydq", "dmk_xb", "dmk_xl", "dmk_xx",
						"dmk_zgbm", "dmk_zzmm"};
			} else if(Globals.XXDM_ZGDZDX.equals(xxdm)) {
				pageCard = new String[] {"用人单位","单位所在地" };
				tabNames = new String[] {"dmk_zgdd_yrdw","dmk_dwdq"};
			}else{
				pageCard = new String[] { "本专高职专业", "研究生专业", "本专培养方式",
						"研究生培养方式", "毕业去向", "地区流向", "单位地区", "居住证或蓝表标志位",
						"生源地主管部门", "单位性质", "单位性质2", "行业分类", "生源地区", "性别", "学历",
						"学校", "主管部门", "政治面貌", "活动形式", "学生层次", "教务处与就业部专业代码对照表","用人单位" };
				tabNames = new String[] { "dmk_bzgzzy", "dmk_yjszy",
						"dmk_bzpyfs", "dmk_yjspyfs", "dmk_byqx", "dmk_dqlx",
						"dmk_dwdq", "dmk_jzzhlbbzw", "dmk_sydzgbm", "dmk_dwxz",
						"dmk_dwxz2", "dmk_hyfl", "dmk_sydq", "dmk_xb",
						"dmk_xl", "dmk_xx", "dmk_zgbm", "dmk_zzmm",
						"jycjh_hdxs", "jygl_xsccb", "dmk_zydmdzb","dmk_zgdd_yrdw" };
			}
		}  else if (codeType.equalsIgnoreCase("twgz")) {
			tips = "团委工作";
			pageCard = new String[] { "第二课堂项目", "获奖级别", "获奖等级", "项目参与者", "活动时间" };
			tabNames = new String[] { "twgz_xmdmb", "twgz_hjjbdmb",
					"twgz_hjdjdmb", "twgz_xmcyzdmb", "twgz_hdsjdmb" };
		} else if (codeType.equalsIgnoreCase("yxgl")) {
			tips = "迎新管理";
			pageCard = new String[] { "报到情况", "注册情况" };
			tabNames = new String[] { "bdqkb", "zcqkb"};
		} else if (codeType.equalsIgnoreCase("xsh")) {

			tips = "学生会";

			// 北京协和
			pageCard = new String[] { "社团通用干部", "社团独用干部" };
			tabNames = new String[] { "bjxh_sttygbb", "bjxh_stdygbb" };
			
		} else {
			codeForm.setErrMsg("参数不可识别！");
			return mapping.findForward("false");
		}
		if ((tName == null) || tName.equalsIgnoreCase("")) {
			tName = tabNames[0];
		}
		if (tName.indexOf("rcgl_dmwhb") != -1) {
			tTem = tName.replace("rcgl_dmwhb!!", "");
			tName = "rcgl_dmwhb";
		}
		
		sql = sysService.getQuerrySQL(tName,tTem);//获取查询信息的查询语句
		tabTitle = dao.getColumnName(sql);
		// 获取页面表格的表头id（表的英文列名）
		tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
		if (tName.equalsIgnoreCase("view_yrdwdmb")) {
			tName = "yrdwdmb";
		}
		if("view_yrdwdmb".equalsIgnoreCase(tName) 
			   || "yrdwdmb".equalsIgnoreCase(tName)){
			tabTitle = new String[]{"行号","YRDWDM" ,"YRDWMC", "LXR", "LXDH", "DWLB",
					                "SSXQ", "XYMC", "DLM", "YRDWDZ"};
			tabTitleCN = new String[]{"行号","用人单位代码","用人单位名称","联系人",
					                  "联系电话", "单位类别","所属校区",Base.YXPZXY_KEY,
					                  "登录名","用人单位地址"};
		}
		// if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){//北京联合大学
		// if("yrdwdmb".equalsIgnoreCase(tName)){
		// tabTitleCN[4]="所属部门";
		// tabTitleCN[1]="用人单位";
		// }
		// if("sslddmb".equalsIgnoreCase(tName)){
		// tabTitleCN[3]="学院代码";
		// }
		// }
		
		if("gywxryb".equalsIgnoreCase(tName)){
			if(Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)){
				tabTitleCN = new String[] { "行号", "人员代码", "人员姓名", "物业公司名称" };
			}
		}
		
		if("zbrydmb".equalsIgnoreCase(tName)){
			tabTitle = new String[] { "行号", "zbrydm", "zbrymc", "ldmc" };
			tabTitleCN = new String[] { "行号", "值班人员代码", "值班人员姓名", "楼栋名称" };
		}
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJYS)) {
			if ("yrdwdmb".equalsIgnoreCase(tName)) {
				tabTitleCN[5] = "所属部门";
			}
		}
		if ("rcgl_dmwhb".equalsIgnoreCase(tName)) {
			tName += "!!" + tTem;
		}
		if (tName.equalsIgnoreCase("jxjdmb")) {
//			tabTitle = new String[] { "行号", "jxjdm", "jxjmc", "jxjlb", "jxjjb",
//					"jlje", "szjdbz"};
//			tabTitleCN = new String[] { "行号", "奖学金代码", "奖学金名称", "奖学金类别",
//					"奖学金级别", "奖励金额", "设置绩点标准"};
			
			tabTitle = new String[] { "行号", "jxjdm", "jxjmc", "jxjlb", "jxjjb",
					"jlje","sfqy"};
			tabTitleCN = new String[] { "行号", "奖学金代码", "奖学金名称", "奖学金类别",
					"奖学金级别", "奖励金额","是否启用"};
			sql = "select rownum 行号,jxjdm,jxjmc,jxjlb,jxjjb,jlje,(case when sfqy='yes' then '是' else '否' end) sfqy from jxjdmb ";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
				tabTitle = new String[] { "行号", "jxjdm", "jxjmc", "jxjlb",
						"jlje", "szjdbz","dkjd" };
				tabTitleCN = new String[] { "行号", "奖学金代码", "奖学金名称",
						"奖学金类别", "奖励金额", "设置平均绩点标准","设置单科绩点标准" };
			} 
			if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDXHXXY)){
				//武汉理工华夏学院
				sql = "select rownum 行号,jxjdm,jxjmc,jxjlb,jxjjb,jlje,szjdbz,jlf from jxjdmb ";
				tabTitle = new String[] { "行号", "jxjdm", "jxjmc", "jxjlb", "jxjjb",
						"jlje", "szjdbz" ,"jlf"};
				tabTitleCN = new String[] { "行号", "奖学金代码", "奖学金名称", "奖学金类别",
						"奖学金级别", "奖励金额", "设置绩点标准", "奖励分" };
			} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				sql = "select rownum 行号,jxjdm,jxjmc,jxjlb,jxjjb,jlje from jxjdmb";
				tabTitle = new String[] { "行号", "jxjdm", "jxjmc", "jxjlb", "jxjjb",
						"jlje"};
				tabTitleCN = new String[] { "行号", "奖学金代码", "奖学金名称", "奖学金类别",
						"奖学金级别", "奖励金额"};
			}
		}
		
		if (tName.equalsIgnoreCase("rychdmb")) {
			tabTitle = new String[] { "行号", "rychdm", "rychmc" };
			tabTitleCN = new String[] { "行号", "荣誉称号代码", "荣誉称号名称" };
		}
		if (tName.equalsIgnoreCase("jxjxdmb")) {
			tabTitle = new String[] { "行号", "jxdm", "jxmc" };
			tabTitleCN = new String[] { "行号", "奖项代码", "奖项名称" };
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			HttpSession session = request.getSession();
			if (tName.equalsIgnoreCase("jxjdmb")) {
				tabTitle = new String[] { "xydm", "行号", "jxjdm", "jxjmc",
						"jxjlb", "jxjjb", "jlje", "szjdbz", "sztzxfbz", "bmmc" ,"fbr"};
				tabTitleCN = new String[] { "xydm", "行号", "奖学金代码", "奖学金名称",
						"奖学金类别", "奖学金级别", "奖励金额", "设置成绩学分标准", "素质拓展学分标准" ,"所在部门", "发布人"};
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
				tabTitle = new String[] { "xydm", "行号", "rychdm", "rychmc",
						"rychlb","bmmc","fbr" };
				tabTitleCN = new String[] { "xydm", "行号", "荣誉称号代码", "荣誉称号名称",
						"荣誉称号类别" ,"所在部门", "发布人"};
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
			 * String[]{"行号", "jxdm", "jxmc"}; tabTitleCN = new String[]{ "行号",
			 * "奖项代码", "奖项名称"}; }
			 */
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			if (tName.equalsIgnoreCase("jxjdmb")) {
				tabTitle = new String[] { "行号", "jxjdm", "jxjmc",
						"jxjjb", "jlje", "jxjlb" };
				tabTitleCN = new String[] { "行号", "奖学金代码", "奖学金名称",
						"奖学金级别", "奖励金额", "奖学金分类" };
			}
			if (tName.equalsIgnoreCase("rychdmb")) {
				tabTitle = new String[] { "行号", "rychdm", "rychmc", "pxbl" };
				tabTitleCN = new String[] { "行号", "荣誉称号代码", "荣誉称号名称", "评选比例" };
			}
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
			if (tName.equalsIgnoreCase("rychdmb")) {
				sql = "select rownum 行号,a.rychdm,a.rychmc,(select xmzl from pjpy_bbdyljb where a.bdbblxdm = xmdm and xmlb = 'rych') bdbblxdm from rychdmb a";
				tabTitle = new String[] { "行号", "rychdm", "rychmc","bdbblxdm"};
				tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
			}
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {// 武汉理工大学
			if (tName.equalsIgnoreCase("jxjdmb")) {
				tabTitle = new String[] { "行号", "jxjdm", "jxjmc", "jxjlb",
						"jxjjb", "jlje", "jxjfl" };
				tabTitleCN = new String[] { "行号", "奖学金代码", "奖学金名称", "奖学金类别",
						"奖学金级别", "奖励金额", "奖学金分类" };
			}
			if (tName.equalsIgnoreCase("rychdmb")) {
				tabTitle = new String[] { "行号", "rychdm", "rychmc", "rychfl" };
				tabTitleCN = new String[] { "行号", "荣誉称号代码", "荣誉称号名称", "荣誉称号分类" };
			}
		}
		
		if (tName.equalsIgnoreCase("jxjlbdmb")) {
			sql = "select rownum 行号,jxjlbdm,jxjlbmc,(case when jxjjd='1' then '是' else '否' end) jxjjd from jxjlbdmb ";
			if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				sql = "select rownum 行号,jxjlbdm,jxjlbmc from jxjlbdmb ";
				tabTitle = new String[]{"行号", "jxjlbdm", "jxjlbmc"};
			}
			
		}
		
		// 获取页面表格的中文表头（表的中文列名）
		// vector.add(tabTitleCN);
		// 将表的中文列名放入结果集的头部
		List pageCard_tabNames = dao.arrayToList(tabNames, pageCard);
		// 将选项卡标签与其对应的表名合并到一个List
		List tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);
		// 将要显示的表格的表头与其对应的英文字段名合并到一个List
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			if (tName.equalsIgnoreCase("stu_gdzlb")) {
				sql = "select rownum 行号,a.gdzldm,a.zlbmc　from stu_gdzlb a";
			}
		}

		if (tName.equalsIgnoreCase("dm_ydlb")) {
			sql = "select rownum 行号,a.ydlbm,a.ydlbmc,replace(replace(nvl(a.xjzt,' '),'1','有'),'0','无') xjzt,a.sfzx from dm_ydlb a";
			tabTitle = new String[] { "行号", "ydlbm", "ydlbmc", "xjzt", "sfzx" };
			tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
			tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);
		}
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY) || xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)) {
			if (tName.equalsIgnoreCase("jxjdmb")) {
				sql = "select rownum 行号,a.jxjdm,a.jxjmc,a.jxjjb,b.jxjlbmc jxjlb,a.jlje,a.szjdbz from jxjdmb a left join jxjlbdmb b on a.jxjlb=b.jxjlbdm";
			}
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
			if (tName.equalsIgnoreCase("jxjdmb")) {
				sql = "select rownum 行号,a.jxjdm,a.jxjmc,a.jxjjb,b.jxjlbmc jxjlb,a.jlje,(select xmzl from pjpy_bbdyljb where a.bdbblxdm = xmdm and xmlb = 'jxj' ) bdbblxdm from jxjdmb a left join jxjlbdmb b on a.jxjlb=b.jxjlbdm";
				tabTitle = new String[] { "行号", "jxjdm", "jxjmc","jxjlb","jxjjb","jlje","bdbblxdm"};
				tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
				tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);		                
			}
		}
		if(!Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)&&"sslddmb".equalsIgnoreCase(tName)){
			sql = "select rownum 行号,lddm,ldmc,xqdm,xbxd,cs,yqdm from sslddmb ";
			tabTitle = new String[] { "行号", "lddm", "ldmc", "xqdm", "xbxd","cs","yqdm" };
			tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
			tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);
		}
		if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)&&"jtrychdmb".equalsIgnoreCase(tName)){
			sql = "select rownum 行号,jtrychdm ,jtrychmc from jtrychdmb ";
			tabTitle = new String[] { "行号", "jtrychdm","jtrychmc" };
			tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
			tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);
		}
		if("sztz_kmdmb".equalsIgnoreCase(tName)){
			if(Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm)){
				sql = "select rownum 行号,kmdm,kmm,(case when hgfs<1 then '0'||hgfs else to_char(hgfs) end ) hgfs,sm from sztz_kmdmb order by to_number(kmdm) ";
				tabTitle = new String[] { "行号", "kmdm", "kmm","hgfs","sm" };
			}else{
				sql = "select rownum 行号,kmdm,kmm,(case when hgfs<1 then '0'||hgfs else to_char(hgfs) end ) hgfs from sztz_kmdmb order by to_number(kmdm)";
				tabTitle = new String[] { "行号", "kmdm", "kmm","hgfs"};
			}
			tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
			tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);		
		}
		if("stu_gdzlb".equalsIgnoreCase(tName) && Globals.XXDM_SJXY.equalsIgnoreCase(xxdm)){
			sql = "select rownum 行号,gdzldm ,zlbmc ,(select mc from gdzllbb where dm=a.zlblb) zlblb from stu_gdzlb a order by to_number(gdzldm)";
			tabTitle = new String[] { "行号", "gdzldm", "zlbmc","zlblb"};
			tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
			tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);		
		}
		if (tName.equalsIgnoreCase("zcbzpyb")) {
			tabTitle = new String[] { "行号", "dm", "yx", "yxmc", "bzf", "bzpy", "dj" };
			sql = "select rownum 行号,dm,yx,yxmc,bzf,bzpy,dj from zcbzpyb";
			tabTitleCN = dao.getColumnNameCN(tabTitle, tName);
			tabTitle_EN_CN = dao.arrayToList(tabTitle, tabTitleCN);		
		}

		vector.addAll(dao.rsToVator(sql, new String[] {}, tabTitle));
		// 返回结果集
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
			toInitSel_2 = "!!SplitSignOne!!!!SplitSignTwo!!男!!SplitSignTwo!!男!!SplitSignOne!!!!SplitSignTwo!!女!!SplitSignTwo!!女!!SplitSignOne!!!!SplitSignTwo!!混合!!SplitSignTwo!!混合";
		   
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
			toInitSel_1 = "!!SplitSignOne!!!!SplitSignTwo!!001!!SplitSignTwo!!短字段!!SplitSignOne!!!!SplitSignTwo!!日期!!SplitSignTwo!!日期!!SplitSignOne!!!!SplitSignTwo!!文本域!!SplitSignTwo!!文本域";
		}
		if (tName.equalsIgnoreCase("yrdwdmb")) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				sql = "select * from bks_dwdmb";
				toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
						new String[] { "dwdm", "dwmc" });
				sql = "select yhm,xm from yhb";
				toInitSel_2 = dao.getStringToSplit(sql, new String[] {},
						new String[] { "yhm", "xm" });
				toInitSel_3 = "!!SplitSignOne!!!!SplitSignTwo!!校内!!SplitSignTwo!!校内!!SplitSignOne!!!!SplitSignTwo!!校外!!SplitSignTwo!!校外";
				sql = "select * from dm_zju_xq";
				toInitSel_4 = dao.getStringToSplit(sql, new String[] {},
						new String[] { "dm", "xqmc" });
				sql = "select distinct bmdm,bmmc from zxbz_xxbmdm";
				toInitSel_5 = dao.getStringToSplit(sql, new String[] {},
						new String[] { "bmdm", "bmmc" });
			} else {
				toInitSel_1 = "!!SplitSignOne!!!!SplitSignTwo!!校内!!SplitSignTwo!!校内!!SplitSignOne!!!!SplitSignTwo!!校外!!SplitSignTwo!!校外";
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
			toInitSel_2 = "!!SplitSignOne!!!!SplitSignTwo!!按学制!!SplitSignTwo!!按学制!!SplitSignOne!!!!SplitSignTwo!!自定义!!SplitSignTwo!!自定义";
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
			sql = "select dm,mc from (select '是' dm,'是'mc from dual union select '否'dm,'否'mc from dual )order by dm desc nulls first";
			toInitSel_2 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "dm", "mc" });
		}
		if (tName.equalsIgnoreCase("sztz_sqsblyb")
				|| tName.equalsIgnoreCase("sztz_hjjbdmb")) {
			sql = "select * from sztz_xmdmb";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "xmdm", "xmmc" });
		}
		//TODO 广东白云 团员异动状态表 author 裘力俊 2010-7-30
		if(tName.equalsIgnoreCase("gdby_dtjs_ydlxdmb")) {
			sql = "select * from gdby_dtjs_tyydzt";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
					new String[] { "ydzt","ydzt" });
		}
		//TODO 请假类型代码表 author 裘力俊 2010-7-19
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
//				// 北京联合大学
//				toInitSel_1 = "!!SplitSignOne!!!!SplitSignTwo!!正常!!SplitSignTwo!!正常!!SplitSignOne!!!!SplitSignTwo!!保留学籍!!SplitSignTwo!!保留学籍"
//						+ "!!SplitSignOne!!!!SplitSignTwo!!离校!!SplitSignTwo!!离校";
//			}
			toInitSel_2 = "!!SplitSignOne!!!!SplitSignTwo!!在校!!SplitSignTwo!!在校!!SplitSignOne!!!!SplitSignTwo!!不在校!!SplitSignTwo!!不在校";
		}
		if (tName.equalsIgnoreCase("stu_gdzlb")) {
			toInitSel_1 = "!!SplitSignOne!!!!SplitSignTwo!!1!!SplitSignTwo!!是!!SplitSignOne!!!!SplitSignTwo!!0!!SplitSignTwo!!否";
		}
		//评奖评优奖学金代码增加时选择下拉列表的设置
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
				//先注释通用的
				//toInitSel_1 = "!!SplitSignOne!!!!SplitSignTwo!!校!!SplitSignTwo!!校!!SplitSignOne!!!!SplitSignTwo!!外!!SplitSignTwo!!外";
				
				//通用的全部改成从奖学金类别表取数据
				sql = "select dm,mc from jxjlbxxdmb";
				toInitSel_1 = dao.getStringToSplit(sql, new String[] {},
						new String[] { "dm","mc" });
				
				toInitSel_2 = "!!SplitSignOne!!!!SplitSignTwo!!yes!!SplitSignTwo!!是!!SplitSignOne!!!!SplitSignTwo!!no!!SplitSignTwo!!否";
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
			toInitSel_1 = "!!SplitSignOne!!!!SplitSignTwo!!校审!!SplitSignTwo!!校审!!SplitSignOne!!!!SplitSignTwo!!院审!!SplitSignTwo!!院审";
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
			sql = "select jxjdm dm,jxjmc mc from jxjdmb a where exists (select 1 from jxjlbdmb b where jxjlbmc like '校外%' and a.jxjlb=b.jxjlbdm) order by dm";
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
			toInitSel_1 = "!!SplitSignOne!!!!SplitSignTwo!!学校!!SplitSignTwo!!学校" +
					"!!SplitSignOne!!!!SplitSignTwo!!"+Base.YXPZXY_KEY+"!!SplitSignTwo!!学院!!" +
					"SplitSignOne!!!!SplitSignTwo!!辅导员!!SplitSignTwo!!辅导员";
		}
		if (tName.equalsIgnoreCase("stu_gdzlb")) {
			String[] colList = new String[] { "dm", "mc" };
			sql = "select distinct(dm) dm,mc mc from gdzllbb order by dm";
			toInitSel_1 = dao.getStringToSplit(sql, new String[] {}, colList);
		}
		
		
		// =============end luojw 2009/12/4 =======================
		// =============begin luojw 2010/6/12 =======================
		if (tName.equalsIgnoreCase("xszz_fsbzffb")) {
			//学生资助_副食补助
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
		//学生会干部
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
			sb.append("when a.stxz = '专业' then ");
			sb.append("(select distinct b.zymc from view_njxyzybj b where a.bmdm = b.zydm) ");
			sb.append("when a.stxz = '班级' then ");
			sb.append("(select distinct b.bjmc from view_njxyzybj b where a.bmdm = b.bjdm) ");
			sb.append("else '学校' end || ')' mc from xsh_stglb a ");
			
			toInitSel_1 = dao.getStringToSplit(sb.toString(), new String[] {}, colList);
		}
		// =============end luojw 2010/7/15 =======================
		
		request.setAttribute("selInit_1", toInitSel_1);//如果是下拉列表,那么这是第一个下拉列表的值
		request.setAttribute("selInit_2", toInitSel_2);//如果是下拉列表,那么这是第一个下拉列表的值
		request.setAttribute("selInit_3", toInitSel_3);//如果是下拉列表,那么这是第一个下拉列表的值
		request.setAttribute("selInit_4", toInitSel_4);//如果是下拉列表,那么这是第一个下拉列表的值
		request.setAttribute("selInit_5", toInitSel_5);//如果是下拉列表,那么这是第一个下拉列表的值
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
			} else if (tName.equalsIgnoreCase("gywsjcbmb")) {// 公寓管理－卫生检查部门
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
			if(codeByDept != null && "true".equalsIgnoreCase(codeByDept)){//需要保存操作人信息
				//保存操作人信息
				String tableName = "dmwhczrxxb";
				boolean flag = StandardOperation.delete(tableName, "xmdm||ssb", newCode[0]+tName, request);
				if(flag){
					StandardOperation.insert(tableName, new String[]{"xmdm","ssb","czr"}, new String[]{newCode[0],tName,userName}, request);
				}
			}
			String res = result ? "OK" : "NO";
			request.setAttribute("result", res);
			String userDo = "在表'" + tName + "'中增加代码，代码为：" + newCode[0];
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
			if (tName.equalsIgnoreCase("gywsjcbmb")) {// 公寓管理－卫生检查部门
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
			if(codeByDept != null && "true".equalsIgnoreCase(codeByDept)){//修改时操作人信息为空的,需要保存操作人信息
				//保存操作人信息
				String tableName = "dmwhczrxxb";
				boolean flag = sysService.checkExists(tableName, "xmdm||ssb", newCode[0]+tName);
				if(!flag){
					StandardOperation.insert(tableName, new String[]{"xmdm","ssb","czr"}, new String[]{newCode[0],tName,userName}, request);
				}
			}
			
			String res = result ? "OK" : "NO";
			request.setAttribute("result", res);
			String userDo = "修改表'" + tName + "'中代码，代码为：" + newCode[0];
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
			String userDo = "删除表'" + tName + "'中代码，代码为：" + delV;
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
		sql = StringUtils.joinStr("select a.*,rownum r,rownum 行号 from (select a.*,(",
			                     "select distinct xm from yhb b where b.yhm=a.yhm)用户姓名 from (",
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
				.getColumnName("select rownum 行号,a.rzxh,a.yhm,'用户姓名' 用户姓名,a.yhcz,a.czsj,a.ip,a.mac,a.host from czrzb a");
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
	
	
	
	//查看日志具体内容
	private ActionForward logViewinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		
		
		String pk = "rzxh";
		String sql = ""; // sql语句
		String realTable = "czrzb"; //操作日志表
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from "+ realTable +" where 1=2"); // 返回列名数组
			String[] zpxxinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zpxxinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zpxxinfo[i]); // 将记录循环放入map中
				}
			}
		}
		request.setAttribute("rs", map);// 发送数据集
		
		
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
		sql = "select rownum 行号,a.* from sjbfjl a order by a.bfsj";

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

	// 系统设置
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
		// ================= begin 骆嘉伟 2009/6/1======================
		xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		// ================= end 骆嘉伟 2009/6/1======================

		// ================= begin 骆嘉伟 2010/4/8======================	
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
		// ================= end 骆嘉伟 2010/4/8======================
		
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
				String dqzc = "放假";
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
			request.setAttribute("dqzc", Base.chgNull(result[2], "放假", 0));

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
		request.setAttribute("webSerTb", Base.initProperties.get("webSerTb"));//用于判断是否开启websevice同步功能
		request.setAttribute("jbsjTableList", jbsjTableList);// 用于导入数据的基础数据表
		request.setAttribute("glsjTableList", glsjTableList);// 用于webservice关联数据的基础数据表
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
	 * 裘力俊
	 * 基础数据导出
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
			if(xh != null && !"".equalsIgnoreCase(xh)){//单个学生密码初始化
				sql = "select xh from view_xsjbxx where xh='" + xh + "'";
				String[] result = dao.getOneRs(sql, new String[] {}, new String[] { "xh" });
				if (result == null) {
					commanForm.setChanged("密码初始化失败，该学号不存在!");
					request.setAttribute("tName", tName);
					return mapping.findForward(returnPage);
				}
				if (userType != null && "xy".equalsIgnoreCase(userType)) {//学院用户
					sql = "select xh from view_xsjbxx where xydm=? and xh=?";
					result = dao.getOneRs(sql, new String[] { userDep, xh },
							new String[] { "xh" });
					if (result == null || result.length < 1) {
						commanForm.setChanged("密码初始化失败，该学生不是您所在"+Base.YXPZXY_KEY+"的学生!");
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
				commanForm.setChanged("密码初始化成功!");
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
		// 判断路径是不是存在，不存在则创建
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
				// 修改bak_path的owner及修改导出脚本的模式
				try {
					String commandLine = "chown oracle:dba " + bak_path;// 修改备份文件夹权限，使得oracle用户可以写入
					Runtime.getRuntime().exec(commandLine);
					commandLine = "chown oracle:dba " + bak_pathV;// 修改备份的文件权限，使得oracle用户可以执行
					Runtime.getRuntime().exec(commandLine);
					commandLine = "chmod a+x " + bak_pathV;// 修改导出文件的脚本为可执行文件
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
			resualt = "初始化成功！";
			request.setAttribute("message",resualt);
		} else {
			resualt = "初始化失败！";
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

	// 宁波职业辅导员信息同步
	public ActionForward fdyxxQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String act = DealString.toGBK(request.getParameter("act"));
		String sql = "";
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();

		// 目前数据库时间
		String rightTime = dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') rtime from dual",
				new String[] {}, new String[] { "rtime" })[0];

		// 上次同步时间
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

		// 学生基本信息
		sql = "select a.r 行号,a.* from (select a.*,rownum r from (select distinct a.rowid rid, a.zgh,a.xm, a.dzyx from fdyxxb a where 1=1 "
				+ " ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize());

		String[] colList = new String[] { "rid", "行号", "zgh", "xm", "dzyx" };

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
	
	//用户组和用户排序
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
	 * 导出用户组权限
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
		
		String yhz = DealString.toGBK(request.getParameter("yhz"));//用户组代码
		String gnmkdm = DealString.toGBK(request.getParameter("gnmkdm"));//功能模块代码		
		
		yhzForm.setZdm(yhz);
		yhzForm.setGnmkdm(gnmkdm);
		
		String sql = service.querryZqx(yhzForm);//查询数据的查询语句
		String tableName = "view_yhzqx";//要导出的数据所属表名称
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(sql, tableName, response.getOutputStream());//将数据写到Excel文件				
		return mapping.findForward("");
	}
	
	/**
	 * 导出用户权限
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
		
		String yhm = DealString.toGBK(request.getParameter("yhm"));//用户名
		String gnmkdm = DealString.toGBK(request.getParameter("gnmkdm"));//功能模块代码		
		
		yhForm.setYhm(yhm);
		yhForm.setGnmkdm(gnmkdm);
		
		String sql = service.querryYhqx(yhForm);//查询数据的查询语句
		String tableName = "view_yhqx";//要导出的数据所属表名称
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(sql, tableName, response.getOutputStream());//将数据写到Excel文件				
		return mapping.findForward("");
	}
	
	/**
	 * 密码初始化操作
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
			resualt = "重置成功！";
			request.setAttribute("message",resualt);
		} else {
			resualt = "重置失败！";
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