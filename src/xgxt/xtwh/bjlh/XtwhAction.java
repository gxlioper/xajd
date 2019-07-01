package xgxt.xtwh.bjlh;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.BaseAction;

public class XtwhAction extends BaseAction {

	/**
	 * @describe 专业年级组编班
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward zynjzbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();

		String userType;

		String userDep;

		String sUName;

		String logMsg;

//		String writeAble;

		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		userDep = session.getAttribute("userDep").toString();


		XtwhForm xtwhForm = (XtwhForm) form;
		String sql;
		String inputSQL[];
		String outputSQL[];
		String bmdm = Base.chgNull(xtwhForm.getXydm(), "%", 0);
		String zgh = Base.chgNull(xtwhForm.getZgh(), "", 0);
		String nj = Base.chgNull(xtwhForm.getNj(), "%", 0);
		String zydm = Base.chgNull(xtwhForm.getZydm(), "%", 0);
		String zynjzxm = Base.chgNull(xtwhForm.getZynjzxm(), "", 0);
		String act = request.getParameter("act");
		Vector<HashMap<String, Object>> sqlToExe = new Vector<HashMap<String, Object>>();
		HashMap<String, Object> mapTmp;
		String[] tmp;

		userDep = request.getSession().getAttribute("userDep")
		.toString();
		userType = dao.getUserType(userDep);
		if ("xy".equalsIgnoreCase(userType)) {
			xtwhForm.setXydm(userDep);
			bmdm = userDep;
		}

		/** 保存 */
		if (!Base.isNull(act) && act.equalsIgnoreCase("save")) {

			boolean ok = false;

			String[] bjdm = xtwhForm.getBjdm();
			if (bjdm == null || Base.isNull(zgh)) {
				sql = "delete from zynjzjb where zgh=?";
				ok = dao.runUpdate(sql, new String[] { zgh });
				logMsg = "清空了代码为" + zgh + "的用户名下的班级";
			} else {
				mapTmp = new HashMap<String, Object>();
				sql = "delete from zynjzjb where zgh=?";
				ok = dao.runUpdate(sql, new String[] { zgh });
				tmp = new String[] { zgh };
				mapTmp.put("sqlTxt", sql);
				mapTmp.put("sqlVal", tmp);
				sqlToExe.add(mapTmp);
				logMsg = "维护专业年级组班级对照表，用户代码为" + zgh + "名下的班级有";
				for (int i = 0; i < bjdm.length; i++) {
					mapTmp = new HashMap<String, Object>();
					sql = "insert into zynjzjb(zgh,bjdm) values(?,?)";
					tmp = new String[] { zgh, bjdm[i] };
					mapTmp.put("sqlTxt", sql);
					mapTmp.put("sqlVal", tmp);
					sqlToExe.add(mapTmp);
					logMsg += bjdm[i] + ",";
				}
				ok = dao.runUpdate(sqlToExe);
			}

			if (ok) {
				Base.log(request, logMsg, sUName);
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		sql = "select a.yhm zgh,a.xm from yhb a,yhzb b where a.zdm=b.zdm and b.zmc='专业年级组' and a.szbm=? order by a.yhm";
		inputSQL = new String[] { bmdm };
		outputSQL = new String[] { "zgh", "xm" };	
		List zynjzList = dao.getList(sql, inputSQL, outputSQL);

		sql = "select distinct a.bjdm,a.bjmc from view_njxyzybj a where a.nj like ? and"
			+ " a.xydm = ? and a.zydm like ? and not exists (select bjdm from "
			+ "zynjzjb b where a.bjdm = b.bjdm)";
		inputSQL = new String[] { nj, bmdm, zydm };
		outputSQL = new String[] { "bjdm", "bjmc" };
		List bjList = dao.getList(sql, inputSQL, outputSQL);

		sql = "select distinct a.bjdm,a.bjmc from view_njxyzybj a where exists (select 1 from zynjzjb b where b.zgh=? and b.bjdm=a.bjdm)";
		inputSQL = new String[] { zgh };
		outputSQL = new String[] { "bjdm", "bjmc" };
		List zynjzBjList = dao.getList(sql, inputSQL, outputSQL);

		sql = "select a.yhm zgh,a.xm,b.bmmc from yhb a,zxbz_xxbmdm b where a.yhm=? and a.szbm=b.bmdm and a.szbm like ?";
		inputSQL = new String[] { zgh, bmdm };
		outputSQL = new String[] { "zgh", "xm", "bmmc" };
		HashMap<String, String> zynjzInfo = dao.getMap(sql, inputSQL, outputSQL);
		if (zynjzInfo == null) {
			zynjzInfo = new HashMap<String, String>();
		}

		zynjzInfo.put("zynjzxm", zynjzxm);
		zynjzInfo.put("zydm", zydm);
		zynjzInfo.put("xydm", bmdm);
		zynjzInfo.put("nj", nj);
		request.setAttribute("zynjzList", zynjzList);
		request.setAttribute("bjList", bjList);
		request.setAttribute("zynjzBjList", zynjzBjList);
		request.setAttribute("zynjzInfo", zynjzInfo);
		request.setAttribute("njList",  Base.getNjList());// 发送年级列表
		request.setAttribute("xyList",  Base.getXyList());// 发送学院列表
		if(bmdm.equalsIgnoreCase("%")){
			bmdm = "";
		}
		request.setAttribute("zyList", (Base.getZyMap()).get(bmdm));// 发送专业列表
		return mapping.findForward("zynjzbb");
	}
}
