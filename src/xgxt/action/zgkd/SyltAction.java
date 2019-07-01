package xgxt.action.zgkd;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.zgkd.bkManage.BkWeiHuModel;
import xgxt.action.zgkd.bkManage.BkglyPpModel;
import xgxt.action.zgkd.bkManage.bkManageService;
import xgxt.action.zgkd.userReg.UserRegModel;
import xgxt.action.zgkd.userReg.UserRegService;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:��ѧ��������̳Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author:
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2008-7-4 ����04:15:28
 * </p>
 */
public class SyltAction extends DispatchAction {
	// ��̳��ҳ��
	public ActionForward wjdcresult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> wjdclist = new ArrayList<HashMap<String, String>>();
		sql = "select distinct(bt) from jygl_wjdcb";
		String[] btinfo = dao.getOneRs(sql, new String[] {},
				new String[] { "bt" });
		String bt = "";
		if (null != btinfo) {
			bt = btinfo[0];
		}
		map.put("bt", bt);
		sql = "select sum(times) from jygl_wjdcb";
		String[] judgeinfo = dao.getOneRs(sql, new String[] {},
				new String[] { "sum(times)" });
		if (null != judgeinfo) {
			String sum = judgeinfo[0];
			String sql1 = "";
			if ("0".equals(sum)) {
				sql1 = "1";
			} else {
				sql1 = "select sum(times) from jygl_wjdcb";
			}
			sql = "select a.choice,a.times,round(to_number((a.times/( " + sql1
					+ " )) * 100),2) bili from jygl_wjdcb a";
			wjdclist = dao.getArrayList(sql, new String[] {}, new String[] {
					"choice", "times", "bili" });
		}

		request.setAttribute("bt", map);
		request.setAttribute("wjdclist", wjdclist);
		return mapping.findForward("success");
	}

	// ��̳������б�
	public ActionForward syltbklb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyltForm syltForm = (SyltForm) form;
		syltForm.getPages().setPageSize(20);
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		
		SyltService service = SyltService.getSyltService();
		// �������Ȩ��
		String bk = DealString.toGBK(request.getParameter("bk")).trim(); // �õ���������;
		boolean power = service.checkUserOperatePower(userName, bk);
		String userType = (String) session.getAttribute("userType");
		String powerNum = service.checkUserPower(userName);
		if (power || userType.equalsIgnoreCase("admin")
				|| powerNum.equalsIgnoreCase("2")) {
			// ��ʾ������ù���Ա��Ȩ�޻��ǳ�������Ա����admin
			request.setAttribute("power", "yes"); // ��Ȩ��
		} else {
			request.setAttribute("power", "no"); // û��Ȩ��
		}
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		HashMap<String, String> rs2 = new HashMap<String, String>();
		// String dlm = request.getParameter("userName"); //�õ���½��
		StringBuffer sqlBf = new StringBuffer();
		/*
		 * һ��������кܶ�����ӣ�����ÿ���������кܶ�����ۣ� ���ݶ��������ʱ������ʾ������ҳ���ϵ�˳��
		 */
		
        /*
         * ���� searchtype:1������ 2������ searchtype2:1������ 2������ 3��ȫ��
         */
		String querry = " ";
		String search = DealString.toGBK(request.getParameter("search"));
		String searchtype = DealString.toGBK(request.getParameter("searchtype"));
		String searchtype2 = DealString.toGBK(request.getParameter("searchtype2"));
		
		if(null!=search&&!"".equalsIgnoreCase(search)&&"1".equalsIgnoreCase(searchtype)){
			querry += " and bt like '%";
			querry +=search;
			querry +="%' ";
			
		}
		if(null!=search&&!"".equalsIgnoreCase(search)&&"2".equalsIgnoreCase(searchtype)){
			querry += " and fbrnc like '%";
			querry +=search;
			querry +="%' ";
		}
		if(null!=searchtype2&&!"".equalsIgnoreCase(searchtype2)&&"1".equalsIgnoreCase(searchtype2)){
			querry += " and lll > ";
			querry +="50";
			querry +=" ";
		}
		if(null!=searchtype2&&!"".equalsIgnoreCase(searchtype2)&&"2".equalsIgnoreCase(searchtype2)){
			querry += " and dj ='";
			querry +="10";
			querry +="' ";
		}
		rs2.put("search", search);
		rs2.put("searchtype", searchtype);
		rs2.put("searchtype2", searchtype2);
		
		
		//
		/*
		 */
		String zdsj = dao
				.getOneRs(
						"select count(*) num from view_sylt_tzpx where zdsj is not null and bk=?",
						new String[] {bk}, "num");
		if (!StringUtils.isNull(zdsj) && !"0".equalsIgnoreCase(zdsj)) {//���ö�ʱ��
			sqlBf.append("select rid,bt,fbrnc,fbsj,hfs,lll,is_zd,dj from (");
			sqlBf.append("select a.*,rownum r from view_sylt_tzpx a where bk=? ");
			sqlBf.append(querry);
			sqlBf.append("order by a.is_zd desc nulls last,zdsj desc nulls last,fbsj desc nulls last,maxfbsj desc nulls last) where r<=");
			sqlBf.append(syltForm.getPages().getStart()
					+ syltForm.getPages().getPageSize());
			sqlBf.append(" and r> ");
			sqlBf.append(syltForm.getPages().getStart());
			sqlBf.append(" ");
		} else {//���ö�ʱ��,������ʱ������
			sqlBf.append("select rid,bt,fbrnc,fbsj,hfs,lll,is_zd,dj from (");
			sqlBf.append("select a.*,rownum r from view_sylt_tzpx a where bk=? ");
			sqlBf.append(querry);
			sqlBf.append("order by fbsj desc nulls last,a.is_zd desc nulls last,zdsj desc nulls last,maxfbsj desc nulls last) where r<=");
			sqlBf.append(syltForm.getPages().getStart()
					+ syltForm.getPages().getPageSize());
			sqlBf.append(" and r> ");
			sqlBf.append(syltForm.getPages().getStart());
			sqlBf.append(" ");
		}
		String[] outputValue = new String[] { "rid", "bt", "fbrnc", "fbsj",
				"hfs", "lll", "is_zd", "dj" };
		rs = dao.getList(sqlBf.toString(), new String[] { bk }, outputValue);
		// �ж��Ƿ��ǵ��췢������
		String sysSql = "select to_char(sysdate,'yyyymmdd') curdate from dual";
		String sysDate = dao.getOneRs(sysSql, new String[] {},
				new String[] { "curdate" })[0];
		try {
			for (int i = 0; i < rs.size(); i++) {
				if (rs.get(i).get("fbsj").substring(0, 8).equals(sysDate)) {
					rs.get(i).put("isnew", "1");
				} else {
					rs.get(i).put("isnew", "0");
				}
			}
			for (int i = 0; i < rs.size(); i++) {
				if (rs.get(i).get("is_zd").equals("1")) {
					rs.get(i).put("color", "#FDF1DF");
				} else {
					rs.get(i).put("color", "");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		for (HashMap<String, String> map : rs) {
			if (map.get("fbsj") != null && !"".equals(map.get("fbsj"))) {
				map.put("fbsj", dao.doForTime2(map.get("fbsj")));
			}
		}
		String tempSql = "select count(*) num from syltb where type='����' and bk=? and pass='1' "+querry;
		String[] tempArray = dao.getOneRs(tempSql, new String[] { bk },
				new String[] { "num" });
		request.setAttribute("rsNum", (tempArray == null) ? "0" : tempArray[0]);
		syltForm.getPages().setMaxRecord(
				Integer.parseInt(request.getAttribute("rsNum").toString()));
		rs1.put("bk", bk);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", rs1);
		request.setAttribute("rs2", rs2);
		return mapping.findForward("success");
	}

	// ��������

	public ActionForward syltaddnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String bk = "";
		String bt = request.getParameter("doType");
		String go = "";
		HttpSession session = request.getSession();
		// String mac = dao.getMac();
		// String ip = dao.getIp();
		String ip = request.getRemoteAddr();
		String nr = "";
		String dlm = session.getAttribute("userName").toString();

		String yhnc = dao.getOneRs("select nc from sylt_yhb where dlm=?",
				new String[] { dlm }, "nc");

		if (null == yhnc || "".equals(yhnc)) {
			request.setAttribute("zhuce", "no");
			go = "no";
			// ��ʾ��û��ע���
			return mapping.findForward("false");
		}

		bk = DealString.toGBK(request.getParameter("bk")).trim();
		bt = DealString.toGBK(request.getParameter("bt"));
		nr = DealString.toGBK(request.getParameter("content1"));

		// check power
		SyltService service = SyltService.getSyltService();
		boolean power = service.checkUserOperatePower(dlm, bk);
		String userType = (String) session.getAttribute("userType");
		String powerNum = service.checkUserPower(dlm);
		if (power || userType.equalsIgnoreCase("admin")
				|| powerNum.equalsIgnoreCase("2")) {
			// ��ʾ������ù���Ա��Ȩ�޻��ǳ�������Ա����admin
			request.setAttribute("power", "yes"); // ��Ȩ��
		} else {
			request.setAttribute("power", "no"); // û��Ȩ��
		}

		request.setAttribute("bk", bk);
		request.setAttribute("bt", bt);
		request.setAttribute("content1", nr);
		request.setAttribute("yhnc", yhnc);

		String sql = "";

		// �ж���û�з�ip��
		sql = "select fip from sylt_fip where ip=?";
		String fip = dao.getOneRs(sql, new String[] { ip }, "fip");
		if ("fip".equals(fip)) {
			request.setAttribute("add", "fip");
			go = "no";
		}

		if ("save".equals(doType) && !("no".equals(go))) {
			// ������֤��
			String yzm = request.getParameter("yzm");
			String standardYzm = request.getSession().getAttribute("yzm")
					.toString();
			if (!yzm.equalsIgnoreCase(standardYzm)) {
				request.setAttribute("checkYzm", "no");
				return mapping.findForward("success");
			}

			String fbr = "";
			String fbrnc = "";

			String[] fbrinfo = dao.getOneRs(
					"select dlm,nc from sylt_yhb where dlm=?",
					new String[] { dlm }, new String[] { "dlm", "nc" });
			if (fbrinfo != null) {
				fbr = fbrinfo[0];
				fbrnc = fbrinfo[1];
			}

			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
							new String[] {}, new String[] { "sdate" }))[0];
			boolean judge = false;
			// �ж��ǲ����޸ĺ�ı��棬�������ôֻҪ������Ϣ����
			String view = request.getParameter("view");
			if (!StringUtils.isNull(view)) {
				// update data
				judge = StandardOperation.update("syltb", new String[] { "bk",
						"bt", "nr", "fbr", "fbrnc", "fbsj", "ip", "fbrtype",
						"tplj", "type" }, new String[] { bk, bt, nr, fbr,
						fbrnc, fbsj, ip, "", "jyweb/images/sylt.gif", "����" },
						"fbr||bt", fbr + bt, request);
			} else {
				judge = StandardOperation.insert("syltb", new String[] { "bk",
						"bt", "nr", "fbr", "fbrnc", "fbsj", "ip", "fbrtype",
						"tplj", "type" }, new String[] { bk, bt, nr, fbr,
						fbrnc, fbsj, ip, "", "jyweb/images/sylt.gif", "����" },
						request);
			}
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
		}

		request.setAttribute("yhnc", yhnc);
		// �����ŵ���ϸ��Ϣ
		if ("view".equals(doType) && !("no".equals(go))) {
			String rid = request.getParameter("rid").replaceAll(" ", "+")
					.trim();
			sql = "select * from syltb where rowid = ?";
			String[] newMoreInfo = dao.getOneRs(sql, new String[] { rid },
					new String[] { "fbrnc", "bt", "nr" });
			request.setAttribute("yhnc", newMoreInfo[0]);
			request.setAttribute("bt", newMoreInfo[1]);
			// CLOB clob = dao.getOneClob(sql, new String[]{}, "nr");
			// request.setAttribute("content1",clob);
			request.setAttribute("content1", newMoreInfo[2]);
			// request.setAttribute("content1",newMoreInfo[2]);
			request.setAttribute("view", "ok");
		}
		request.setAttribute("go", go);
		return mapping.findForward("success");
	}

	// ���Ӿ������ݲ鿴

	public ActionForward syltmoreinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyltService syltService = SyltService.getSyltService();
		DAO dao = DAO.getInstance();
		SyltDao syltDao = SyltDao.getSyltDao();
		String sql = "";
		String[] colList = null;
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> rs1 = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		pkValue = pkValue.replaceAll(" ", "+");
		String btValue = DealString.toGBK(request.getParameter("btValue"));
		HttpSession session = request.getSession();
		// String
		String dlm = session.getAttribute("userName").toString();
		String ip = request.getRemoteAddr();
		String go = "";
		String fbr = "";
		String fbrnc = "";
		// lll add 1 �б�־λ�ж�
		if (StringUtils.isNull(request.getParameter("noadd"))) {
			syltService.addLllOrPll("syltb", "lll", "rowid", pkValue);
			// ˢ����Ļʱ�������������1
			request.setAttribute("noadd", "yes");
		}
		String bk = DealString.toGBK(request.getParameter("bk"));
		String bt = DealString.toGBK(request.getParameter("bt"));
		String nr = DealString.toGBK(request.getParameter("content1"));

		SyltService service = SyltService.getSyltService();
		String userName = session.getAttribute("userName").toString();
		boolean power = service.checkUserOperatePower(userName, bk);
		String userType = (String) session.getAttribute("userType");
		String powerNum = service.checkUserPower(userName);
		if (power || userType.equalsIgnoreCase("admin")
				|| powerNum.equalsIgnoreCase("2")) {
			// ��ʾ������ù���Ա��Ȩ�޻��ǳ�������Ա����admin
			request.setAttribute("power", "yes"); // ��Ȩ��
		} else {
			request.setAttribute("power", "no"); // û��Ȩ��
		}
		String[] bkinfo = dao.getOneRs("select bk,bt from syltb where rowid=?",
				new String[] { pkValue }, new String[] { "bk", "bt" });
		if (bkinfo != null) {
			bk = bkinfo[0];
			bt = bkinfo[1];
			rs1.put("bk", bk);
			rs1.put("bt", bt);
			rs1.put("pkValue", pkValue);
			rs1.put("btValue", btValue);
		}

		// ����ɾ�� [��������ԱȨ��]
		if ("del".equals(doType)) {
			String gtpkValue = request.getParameter("gtpkValue").replaceAll(
					" ", "+"); // ����������
			boolean judge = false;
			judge = StandardOperation.delete("syltb", "rowid", gtpkValue, // ���rowid������
					request);
			/*
			 * if(judge){ syltDao.minusLll("syltb", "hfs", "rowid", pkValue);
			 * //����������1 }
			 */
			request.setAttribute("noadd", "yes");
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}
		// ȫ����� [��������ԱȨ��]
		if ("delall".equals(doType)) {
			boolean judge = false;
			// sql = "delete from syltb where rowid=?";
			// judge = dao.runUpdate(sql, new String[]{pkValue});
			sql = "delete from syltb where BTROWID=?";
			judge = dao.runUpdate(sql, new String[] { pkValue });
			// ��������ӵ���������Ϊ0
			sql = "update syltb set hfs='0' where rowid=?";
			dao.runUpdate(sql, new String[] { pkValue });
			// judge = dao.runUpdateTab(sql);
			// judge = StandardOperation.delete("syltb", new String[]{"bt"}, new
			// String[]{bt}, request);
			if (judge) {
				request.setAttribute("delall", "ok");
				// rs1 = null;
			} else {
				request.setAttribute("delall", "no");
			}
			request.setAttribute("noadd", "yes");
		}

		// �����жϼ��û���֤
		// if ("save".equals(doType)) {
		//			
		// String yzm = session.getAttribute("yzm").toString().toLowerCase();
		// String yzm2 = request.getParameter("yzm").toLowerCase();
		// sql = "select fip from jygl_lyb_fip where ip=?";
		// String fip = dao.getOneRs(sql, new String[] { ip }, "fip");
		// if ("fip".equals(fip)) {
		// request.setAttribute("add", "fip");
		// go = "no";
		// }
		// if (yzm2 == null || !(yzm2.equals(yzm)) || "".equals(yzm2)) {
		// request.setAttribute("add", "no");
		// session.setAttribute("yzm", "");
		// // ���԰�����
		//
		// CommanForm dataSearchForm = (CommanForm) form;
		// sql = "select count(*) count from view_jygl_lyb ";
		// dataSearchForm.getPages().setMaxRecord(
		// Integer.parseInt(dao.getOneRs(sql, new String[] {},
		// "count")));
		//
		// sql = "select * from (select a.*,rownum r from (select distinct
		// a.rowid rid,a.id,a.tplj,a.yhm,a.fbsj,a.ly,a.ip from jygl_lyb a order
		// by a.fbsj ) a ) a where a.r>"
		// + dataSearchForm.getPages().getStart()
		// + " and a.r<="
		// + (dataSearchForm.getPages().getStart() + dataSearchForm
		// .getPages().getPageSize())
		// + " order by a.fbsj ";
		// colList = new String[] { "rid", "id", "tplj", "yhm", "fbsj",
		// "ly", "ip" };
		// rs1 = dao.rsToVator(sql, new String[] {}, colList);
		// rs.addAll(rs1);
		// request.setAttribute("rs", rs);
		// return mapping.findForward("success");
		// }
		// session.setAttribute("yzm", "");
		// }

		String[] fbrinfo = dao.getOneRs(
				"select dlm,nc from sylt_yhb where dlm=?",
				new String[] { dlm }, new String[] { "dlm", "nc" });
		if (fbrinfo != null) {
			fbr = fbrinfo[0];
			fbrnc = fbrinfo[1];
		}

		// ��IP
		if ("fip".equals(doType)) {
			sql = "select ip from syltb where rowid=?";
			ip = dao.getOneRs(sql, new String[] { pkValue }, "ip");
			if (null != ip && !("".equals(ip))) {
				boolean judge = false;
				judge = StandardOperation.insert("sylt_fip",
						new String[] { "ip" }, new String[] { ip }, request);
				if (judge) {
					request.setAttribute("fip", "ok");
				} else {
					request.setAttribute("fip", "no");
				}
			}
		}

		// �����ύ
		if ("save".equals(doType)) {
			sql = "select fip from sylt_fip where ip=?";
			String fip = dao.getOneRs(sql, new String[] { ip }, "fip");
			if ("fip".equals(fip)) {
				request.setAttribute("add", "fip");
				go = "no";
			}
			String standardYzm = request.getSession().getAttribute("yzm")
					.toString(); // ��׼����֤��
			String yzm = request.getParameter("yzm"); // �û��������֤��
			if (!yzm.equalsIgnoreCase(standardYzm)) {
				request.setAttribute("checkYzm", "no");
			} else if (!("no".equals(go))) {
				// ������֤����ȷ
				String fbsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
								new String[] {}, new String[] { "sdate" }))[0];
				boolean judge = false;
				judge = StandardOperation.insert("syltb", new String[] { "bk",
						"bt", "nr", "fbr", "fbrnc", "fbsj", "ip", "fbrtype",
						"tplj", "btrowid" }, // ����ʶ�����ĸ�������
						new String[] { bk, bt, nr, fbr, fbrnc, fbsj, ip, "",
								"jyweb/images/1.png", pkValue }, request);
				// ����������1
				/*
				 * if(judge){ syltService.addLllOrPll("syltb", "hfs", "rowid",
				 * pkValue); }
				 */
				if (judge) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from syltb where rowid=?";
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] { pkValue },
						"count")) - 1);

		colList = new String[] { "rid", "tplj", "fbrnc", "fbsj", "nr", "ip",
				"bt","fbr", "type" };
		// �õ���������Ϣ
		sql = "select rowid rid,a.* from syltb a where a.rowid = ?";
		String[] zhutieInfo = dao.getOneRs(sql, new String[] { pkValue },
				colList);
		HashMap<String, String> zhutieInfoMap = new HashMap<String, String>();
		if (zhutieInfo != null) {
			for (int i = 0; i < zhutieInfo.length; i++) {
				zhutieInfoMap.put(colList[i], zhutieInfo[i]);
			}
			// �ж��Ƿ��Ǳ������Ӳ����б༭����
			if (userName.equalsIgnoreCase(zhutieInfoMap.get("fbr"))) {
				zhutieInfoMap.put("update", "yes");
			}
		}
		if (!StringUtils.isNull(zhutieInfoMap.get("fbsj"))) {
			zhutieInfoMap.put("fbsj", dao.doForTime(zhutieInfoMap.get("fbsj")));
		}
		// ���Ӿ�������[���԰���Ϣ]
		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.tplj,a.fbrnc,a.fbsj,a.nr,a.ip,a.bt,a.fbr,a.type from syltb a  where btrowid='"
				+ pkValue
				+ "' order by a.fbsj) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<= "
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by a.fbsj";
		rs = syltDao.rsToVator(sql, new String[] {}, colList);
		dataSearchForm.getPages().setMaxRecord(rs.size());
		rs.add(0, zhutieInfoMap);
		request.setAttribute("bk", bk);
		request.setAttribute("bt", bt);
		// request.setAttribute("content1", nr);
		request.setAttribute("fbrnc", fbrnc);
		request.setAttribute("fbr", dlm);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", rs1);
		return mapping.findForward("success");
	}

	//�޸�
	public ActionForward syltupdateinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String dlm = session.getAttribute("userName").toString();
		String nc = dao.getOneRs("select nc from sylt_yhb where dlm=?", new String[]{dlm}, "nc");
//		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		if (null != pkValue && "".equalsIgnoreCase(pkValue)) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ("update".equalsIgnoreCase(doType)) {
			boolean judge = false;
			String xgsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') sdate from dual", // ����ʱ��
							new String[] {}, new String[] { "sdate" }))[0];

			String nr = DealString.toGBK(request.getParameter("content1"));
			String bjtag = "";
			if (nr.contains(" ---------]")) {
				bjtag = "�ٴ�";
			}
			nr += "<P></P>" + "[--------- �������ڣ�" + xgsj + "��" + nc + bjtag
					+ "�༭�� ---------]";
			judge = StandardOperation.update("syltb", new String[] { "nr" },
					new String[] { nr }, "rowid", pkValue, request);

			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}
		}

		String[] colList = new String[] { "rid", "tplj", "fbrnc", "fbsj", "nr",
				"ip", "bt", "fbr", "type" };
		// �õ���������Ϣ
		String sql = "select rowid rid,a.* from syltb a where a.rowid = ?";
		String[] zhutieInfo = dao.getOneRs(sql, new String[] { pkValue },
				colList);
		HashMap<String, String> rs = new HashMap<String, String>();
		if (zhutieInfo != null) {
			for (int i = 0; i < zhutieInfo.length; i++) {
				rs.put(colList[i], zhutieInfo[i]);
			}
			if (null != zhutieInfo[4]) {
				request.setAttribute("content1", zhutieInfo[4]);
			}
		}

		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}

	/** ��̳��ҳ */
	public ActionForward syltDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		// �õ�����������Ϣ
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		SyltService service = SyltService.getSyltService();
		// ����Ȩ��
		String power = service.checkUserPower(userName);
		String userType = (String) session.getAttribute("userType");
		if ("admin".equalsIgnoreCase(userType) || power.equalsIgnoreCase("2")) { // ��������Ա
			request.setAttribute("power", "yes");
		} else {
			request.setAttribute("power", "no");
		}
		// List topTr = service.getTalksCN();
		List<HashMap<String, String>> talksList = service.getTalksInfo(); // �õ���̳�����
		request.setAttribute("talksList", talksList);
		// request.setAttribute("topTr", topTr);
		return mapping.findForward("deFault");
	}

	/**
	 * �û�ע��ҳ��
	 */
	public ActionForward userReg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		SyltForm ltForm = (SyltForm) form;
		// UserRegModel model = new UserRegModel();//�û�ע������Model
		// UserRegService service = new UserRegService();//�û�ע��ҵ��Model
		// BeanUtils.copyProperties(model,
		// ltForm);//UserRegModel����SyltForm����ر���ֵ
		// String userType = session.getAttribute("userType").toString();//�û�����
		String userName = session.getAttribute("userName").toString();// �û���½��
		ltForm.setYhm(userName);
		// request.setAttribute("gotoPage", "userReg");
		// return mapping.findForward("deFault");
		return mapping.findForward("userReg");
	}

	/**
	 * ע����Ϣ����
	 * 
	 * @throws Exception
	 */
	public ActionForward regInFoSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		boolean done = false;
		SyltForm ltForm = (SyltForm) form;
		UserRegModel model = new UserRegModel();// �û�ע������Model
		UserRegService service = new UserRegService();// �û�ע��ҵ��Model
		String userType = session.getAttribute("userType").toString();// �û�����
		String userName = session.getAttribute("userName").toString();// �û���½��
		BeanUtils.copyProperties(model, ltForm);// UserRegModel����SyltForm����ر���ֵ
		done = service.regToSave(userType, userName, model);
		ltForm.setYhm(userName);
		request.setAttribute("done", done);
		// request.setAttribute("gotoPage", "userReg");
		// return mapping.findForward("deFault");
		return mapping.findForward("userReg");
	}

	/** ���������ҳ�� */
	public ActionForward bkManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		bkManageService service = new bkManageService();
		request.setAttribute("yhList", service.getCommonList(1));// �û��б�
		request.setAttribute("bkList", service.getCommonList(2));// ��̳����б�
		// request.setAttribute("gotoPage","bkManage");
		// return mapping.findForward("deFault");
		return mapping.findForward("bkMag");
	}

	/**
	 * ����Ա��Ӧ����ѯ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward glyandbkQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyltForm syltForm = (SyltForm) form;
		// BkglyPpModel bkglModel = new BkglyPpModel();//������Աƥ��MODEL
		// BeanUtils.copyProperties(bkglModel, syltForm);
		syltForm.getPages().setPageSize(5);
		bkManageService service = new bkManageService();
		SyltService syltService = SyltService.getSyltService();
		List<HashMap<String, String>> topList = service.getBkGlyppTitle();// ��ѯ��ͷ
		List<String[]> resList = service.getBkGlyppResult(syltForm);// ��ѯ���,��ҳ��ʾ
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		String[] columns = new String[] { "GLYM", "BKDM" };
		String[] values = { syltForm.getYhm(), syltForm.getBkdm() };
		String rsNum = syltService.getTotalRsNumByEqual("view_sylt_bkglypp",
				columns, values);
		request.setAttribute("rsNum", rsNum);// ��¼��
		syltForm.getPages().setMaxRecord(Integer.parseInt(rsNum));
		request.setAttribute("yhList", service.getCommonList(1));// �û��б�
		request.setAttribute("bkList", service.getCommonList(2));// ��̳����б�
		String xm = syltForm.getXm();
		request.setAttribute("xm", xm);
		//syltForm.setXm(syltForm.getXm());
		// request.setAttribute("gotoPage", "bkManage");
		// return mapping.findForward("deFault");
		return mapping.findForward("bkMag");
	}

	/**
	 * ����Ա�����Ϣ����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward glyandbkAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		bkManageService service = new bkManageService();
		request.setAttribute("yhList", service.getCommonList(1));// �û��б�
		request.setAttribute("bkList", service.getCommonList(2));// ��̳����б�
		return mapping.findForward("glyandbkadd");
	}

	/**
	 * �ȼ���û��Ƿ�ע���ٲ�ѯ���û�������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward refreshglyInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		bkManageService service = new bkManageService();
		String yhm = DealString.toGBK(request.getParameter("yhm"));
		boolean bFlag = service.chkYhmisReg(yhm);// ����û��Ƿ���ע��
		if (bFlag) {
			String[] yhList = service.getYhInfo(yhm);// ��ȡ�û�������Ϣ
			if (yhList != null && yhList.length == 2) {
				request.setAttribute("nc", yhList[0]);// �ǳ�
				request.setAttribute("grqm", yhList[1]);// ����ǩ��
			}
		} else {
			String[] yhList = service.getYhxm(yhm);
			if (yhList != null && yhList.length == 1) {
				request.setAttribute("nc", yhList[0]);// ����
			}
		}
		request.setAttribute("yhList", service.getCommonList(1));// �û��б�
		request.setAttribute("bkList", service.getCommonList(2));// ��̳����б�
		return mapping.findForward("glyandbkadd");
	}

	/**
	 * ����Ա�����Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward glyandbkSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyltForm syltForm = (SyltForm) form;
		BkglyPpModel bkglModel = new BkglyPpModel();// ������MODEL
		BeanUtils.copyProperties(bkglModel, syltForm);
		bkManageService service = new bkManageService();
		boolean bFlag = service.bkglInfoSave(bkglModel, request);// ��Ϣ����
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("yhList", service.getCommonList(1));// �û��б�
		request.setAttribute("bkList", service.getCommonList(2));// ��̳����б�
		return mapping.findForward("glyandbkadd");
	}

	/**
	 * ����Ա��Ӧ��ʽ����Ϣ�޸�ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward glyandbkModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyltForm syltForm = (SyltForm) form;
		String type = request.getParameter("type");// ��Ϣ��ʾ/��Ϣ�޸�
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		bkManageService service = new bkManageService();
		HashMap<String, String> resMap = service.getBkglyPpInfo(pkValue);// ��ȡ�����Ϣ
		syltForm.setBkdm(resMap.get("bkdm"));
		syltForm.setYhm(resMap.get("glym"));
		request.setAttribute("nc", resMap.get("nc"));
		request.setAttribute("grqm", resMap.get("grqm"));
		if (!StringUtils.isNull(type) && StringUtils.isEqual(type, "modi")) {
			request.setAttribute("isModi", "yes");
		}
		request.setAttribute("yhList", service.getCommonList(1));// �û��б�
		request.setAttribute("bkList", service.getCommonList(2));// ��̳����б�
		request.setAttribute("glym", resMap.get("glym"));
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("glyandbkmodi");
	}

	/**
	 * ����Ա��Ӧ��ʽ��Ϣ����ɾ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward glyandbkDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyltForm syltForm = (SyltForm) form;
		bkManageService service = new bkManageService();
		String[] keys = syltForm.getCbv();
		String sJg = service.delBkglyPpInfo(keys, request);// ����ɾ��
		request.setAttribute("isDel", sJg);
		request.setAttribute("yhList", service.getCommonList(1));// �û��б�
		request.setAttribute("bkList", service.getCommonList(2));// ��̳����б�
		return mapping.findForward("bkMag");
	}

	/**
	 * ����Ա��Ӧ��ʽ��Ϣ�޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward glyandbkModifly(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		bkManageService service = new bkManageService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String yhm = DealString.toGBK(request.getParameter("yhm"));
		String bkdm = request.getParameter("bkdm");
		boolean bFlag = service.bkglInfoModi(pkValue, yhm, bkdm, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("yhList", service.getCommonList(1));// �û��б�
		request.setAttribute("bkList", service.getCommonList(2));// ��̳����б�
		return mapping.findForward("glyandbkmodi");
	}

	/** ���ά������ */
	public ActionForward bkWeiHu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		bkManageService service = new bkManageService();
		SyltForm syltForm = (SyltForm) form;
		ArrayList<HashMap<String, String>> topTr = service.getBKWHSearchTitle();
		ArrayList<String[]> rs = service.getBKResult(syltForm);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		// request.setAttribute("gotoPage", "bkWeiHu");
		// return mapping.findForward("deFault");
		return mapping.findForward("bkWH");
	}

	/** ������ҳ�� */
	public ActionForward bkAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("bkadd");
	}

	/**
	 * ���ά������
	 * 
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	public ActionForward bkAddSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, InvocationTargetException {
		SyltForm syltForm = (SyltForm) form;
		BkWeiHuModel model = new BkWeiHuModel();// ������MODEL
		BeanUtils.copyProperties(model, syltForm);
		bkManageService service = new bkManageService();
		boolean bFlag = service.bkInfoSave(model, request);// ��Ϣ����
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("yhList", service.getCommonList(1));// �û��б�
		request.setAttribute("bkList", service.getCommonList(2));// ��̳����б�
		return mapping.findForward("bkadd");
	}

	/** �����Ϣ�޸� */
	public ActionForward bkWeiHuModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, InvocationTargetException {
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		bkManageService service = new bkManageService();
		map = service.viewBKInfo(pkValue);
		request.setAttribute("rs", map);
		return mapping.findForward("bkmodi");
	}

	/** �����Ϣ�鿴 */
	public ActionForward bkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, InvocationTargetException {
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		bkManageService service = new bkManageService();
		map = service.viewBKInfo(pkValue);
		request.setAttribute("rs", map);
		return mapping.findForward("bkview");
	}

	/** ���ɾ�� */
	public ActionForward bkwhDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, InvocationTargetException {

		SyltForm syltForm = (SyltForm) form;
		String[] keys = syltForm.getCbv();
		bkManageService service = new bkManageService();
		int del = service.bkInFoDel(keys, request);// ����ɾ��
		
		
		
		for (int i = 0; i < keys.length; i++) {
			String whichbkmc = DealString.toGBK(keys[i]).trim();// �õ�����
			System.out.print("a" + whichbkmc + "b");
			StandardOperation.delete("syltb", "bk",
					whichbkmc, request);
		}// end for
		
		
		
		request.setAttribute("del", "yes");
		request.setAttribute("delcout", del);
		return new ActionForward("/sylt.do?method=bkWeiHu", false);
	}

	/** ע���û�����Ĭ��ҳ�� */
	public ActionForward regUserManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// request.setAttribute("gotoPage","regUserManage");
		// return mapping.findForward("deFault");
		return mapping.findForward("regUserManage");
	}

	public ActionForward regUserQer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, InvocationTargetException {
		SyltForm syltForm = (SyltForm) form;
//		UserRegModel model = new UserRegModel();// �û�ע���������Model
		UserRegService service = new UserRegService();// �û�ע�����ҵ��Model
		// BeanUtils.copyProperties(model,
		// syltForm);//UserRegModel����SyltForm����ر���ֵ
		List<HashMap<String, String>> topList = service.getregUserQerTitle();// ��ѯ��ͷ
		List<String[]> resList = service.getRegUserResult(syltForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);// ��¼��
		// request.setAttribute("gotoPage","regUserManage");
		// return mapping.findForward("deFault");
		return mapping.findForward("regUserManage");
	}

	public ActionForward regUserDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyltForm syltForm = (SyltForm) form;
		String[] keys = syltForm.getCbv();
		UserRegService service = new UserRegService();
		int del = service.regUserDel(keys, request);// ����ɾ��
		request.setAttribute("del", "yes");
		request.setAttribute("delcout", del);
		return new ActionForward("/sylt.do?method=regUserQer", false);
	}

	public ActionForward delNewByRID(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String newsid = request.getParameter("newsid");
		// System.out.print(newsid);
		if(null!=newsid){
		  newsid=newsid.replace(" ", "+");
		}
		boolean deleteOk = StandardOperation.delete("syltb",
				new String[] { "rowid" }, new String[] { newsid }, request);
		StandardOperation.delete("syltb", new String[]{ "btrowid" }, new String[]{newsid}, request);
		if (deleteOk == true) {
			request.setAttribute("delete", "ok");
		} else {
			request.setAttribute("delete", "no");
		}
		syltbklb(mapping, form, request, response);
		return mapping.findForward("delNewByRID");
	}

	// public ActionForward delCommentByRID(ActionMapping mapping,ActionForm
	// form,HttpServletRequest request,HttpServletResponse response) throws
	// Exception{
	// String pkValue = request.getParameter("pkValue");
	// boolean deleteOk = StandardOperation.delete("syltb", new
	// String[]{"rowid"}, new String[]{pkValue}, request);
	// if(deleteOk == true){
	// request.setAttribute("delete","ok");
	// }else{
	// request.setAttribute("delete","no");
	// }
	// BeanUtils.copyProperties(new CommonForm(),form);
	// syltmoreinfo(mapping, (CommonForm)form, request, response);
	// return mapping.findForward("delCommentByRID");
	// }

	public ActionForward noPassNoteList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String doType = request.getParameter("doType");
		String bkmc = DealString.toGBK(request.getParameter("bk")).trim();
		SyltService service = SyltService.getSyltService();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		boolean power = service.checkUserOperatePower(userName, bkmc);
		String userType = (String) session.getAttribute("userType");
		String powerNum = service.checkUserPower(userName);
		SyltDao syltDao = SyltDao.getSyltDao();
		if (power || userType.equalsIgnoreCase("admin")
				|| powerNum.equalsIgnoreCase("2")) {
			// ��ʾ������ù���Ա��Ȩ�޻��ǳ�������Ա����admin
			request.setAttribute("power", "yes"); // ��Ȩ��
		} else {
			request.setAttribute("power", "no"); // û��Ȩ��
		}
		boolean result = false;
		if (!StringUtils.isNull(doType)) {
			String rid = request.getParameter("rid").trim()
					.replaceAll(" ", "+");
			if (doType.equalsIgnoreCase("pass")) { // ���ͨ��
				result = syltDao.setNotePass(rid);
			} else if (doType.equalsIgnoreCase("nopass")) { // ��˲�ͨ��
				result = syltDao.setNoteNoPass(rid);
			} else if (doType.equalsIgnoreCase("del")) { // ɾ������
				result = syltDao.delNoPassNote(rid);
			}
			request.setAttribute("result", result == true ? "ok" : "no");
		}
		// ��ʾû��ͨ����û����˵�������Ϣ
		List<HashMap<String, String>> rs = syltDao.getNoPassNoteList(bkmc);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("bk", bkmc);
		return mapping.findForward("noPassNoteList");
	}

	/** ������ö� */
	public ActionForward noteZd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String newsid = request.getParameter("newsid");
		String sql = "update syltb set is_zd =?,zdsj = to_char(sysdate,'yyyymmddhh24miss') where rowid ='"
				+ newsid + "'";
		String[] input = new String[] { "1" };
		boolean zdOk = dao.runUpdate(sql, input);
		// StandardOperation.update("syltb", sql, request);
		if (zdOk == true) {
			request.setAttribute("notezd", "ok");
		} else {
			request.setAttribute("notezd", "no");
		}
		syltbklb(mapping, form, request, response);
		return mapping.findForward("delNewByRID");
	}

	/** ����ö� */
	public ActionForward cancleZd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String newsid = request.getParameter("newsid");
		String sql = "update syltb set is_zd = ? , zdsj=? where rowid ='"
				+ newsid + "'";
		String[] input = new String[] { "0", "0" };
		boolean zdOk = dao.runUpdate(sql, input);
		// StandardOperation.update("syltb", sql, request);
		if (zdOk == true) {
			request.setAttribute("canclezd", "ok");
		} else {
			request.setAttribute("canclezd", "no");
		}
		syltbklb(mapping, form, request, response);
		return mapping.findForward("delNewByRID");
	}

	/** ���������ȼ� */
	public ActionForward noteDj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String newsid = request.getParameter("newsid");
		String dj = request.getParameter("dj");
		String sql = "update syltb set dj = " + dj + " where rowid ='" + newsid
				+ "'";
		boolean zdOk = dao.runUpdate(sql, new String[] {});
		if (zdOk == true) {
			request.setAttribute("notedj", "ok");
		} else {
			request.setAttribute("notedj", "no");
		}
		syltbklb(mapping, form, request, response);
		return mapping.findForward("delNewByRID");
	}

	/** ������ö� */
	public ActionForward bkSort(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyltForm syltForm = (SyltForm) form;
		bkManageService service = new bkManageService();
		request.setAttribute("bkList", service.getBkDmMcList());
		request.setAttribute("rs", syltForm);
		return mapping.findForward("bksort");
	}

}