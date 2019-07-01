package xgxt.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.sql.CLOB;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.base.Encrypt;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.dealDate;
import xgxt.wjsc.WjscDataAccessAction;

public class JywebAction extends DispatchAction {
	// DAO dao = DAO.getInstance();

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}

	// ��ҵ��ҳ��򿪼���½
	public ActionForward jyindex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		Encrypt ept = new Encrypt();
		HttpSession session = request.getSession();
		session.setAttribute("userName", "anonymous");
		session.setAttribute("person", "anonymous");

		// ҳ�������������Ϻ����̣�
		session.setAttribute("xxdm", Base.xxdm);

		String xxmcinfo = dao.getOneRs("select xxmc from xtszb ",
				new String[] {}, "xxmc");

		if (xxmcinfo != null) {
			session.setAttribute("xxmc", xxmcinfo);
		} else {
			session.setAttribute("xxmc", "");
		}

		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String usertype = request.getParameter("usertype");

		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> find = new HashMap<String, String>();

		String[] colList = null;
		// ArrayList<Object> news = new ArrayList<Object>();
		ArrayList<Object> zczp = new ArrayList<Object>();
		ArrayList<Object> syjs = new ArrayList<Object>();
		ArrayList<Object> wjdc = new ArrayList<Object>();

		// ҳ�������
		String lll = dao.getOneRs("select jyweblll from jyweb_liulan ",
				new String[] {}, "jyweblll");
		if (null != lll && !"".equalsIgnoreCase(lll)) {
			int i = 1;
			i = Integer.valueOf(lll);
			if (session.getAttribute("lll") == null) {
				i++;
				session.setAttribute("lll", i);
			}
			StandardOperation.update("jyweb_liulan",
					new String[] { "jyweblll" }, new String[] { String
							.valueOf(i) }, "jywebxx", "zfsoft", request);
		} else if (null == lll || "".equalsIgnoreCase(lll)) {
			sql = " delete from jyweb_liulan";
			dao.runUpdateTab(sql);
			sql = "insert into jyweb_liulan (jyweblll,jywebxx) values('1','zfsoft')";
			dao.runUpdateTab(sql);
			session.setAttribute("lll", "1");
		}

		// �ʾ����
		sql = "select choice from jygl_wjdcb order by rownum";
		colList = new String[] { "choice" };
		wjdc.addAll(dao.rsToVator(sql, new String[] {}, colList));

		sql = "select distinct bt from jygl_wjdcb";
		String[] wjdcinfo = dao.getOneRs(sql, new String[] {},
				new String[] { "bt" });
		if (wjdcinfo != null) {
			request.setAttribute("wjdcbt", wjdcinfo[0]);
		}

		// ��ְ��Ϣ

		ArrayList<HashMap<String, String>> qzxx = new ArrayList<HashMap<String, String>>();
		sql = "select xh,xm,xb,xymc,zymc,qzyx,fbsj from (select a.xh,a.xm,a.xb,a.xymc,a.zymc,a.qzyx,a.fbsj from jygl_qzxxb a order by fbsj desc) jygl_qzxxb where rownum<6 ";
		colList = new String[] {"xh","xm","xb","xymc","zymc","qzyx","fbsj"};
		qzxx = dao.getArrayList3(sql, new String[] {}, colList);

		// ���¶�̬�����ţ�
		ArrayList<HashMap<String, String>> zxdt = new ArrayList<HashMap<String, String>>();
		sql = "select b.* from (SELECT ROWNUM r,c.* FROM (select a.* from newscontent a WHERE NEWSPART = 'N13' order by newsid DESC) c) b  WHERE r<7";
		colList = new String[] { "r", "newsid", "newstitle", "pubtime" };
		zxdt = dao.getArrayList3(sql, new String[] {}, colList);

		// ר����Ƹ��
		sql = "select * from (select a.rowid,a.* from JYGL_ZCWJB a order by fbsj desc ) b where wjlx = 'У԰ר����Ƹ��' and rownum<6";
		colList = new String[] { "rowid", "wjbt" };
		zczp.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// ������
		ArrayList<HashMap<String, String>> ggl = new ArrayList<HashMap<String, String>>();
		sql = "select * from (select a.rowid,a.*,a.wjbt wjbttitle from JYGL_ZCWJB a order by fbsj desc ) b where wjlx = '������'";
		colList = new String[] { "rowid", "wjbt", "wjbttitle", "fbsj" };
		ggl = dao.getArrayList3(sql, new String[] {}, colList);

		// ��Դ����
		sql = "select * from (select a.rowid,a.* from JYGL_ZCWJB a order by fbsj desc ) b where wjlx = '��Դ����'";
		colList = new String[] { "rowid", "wjbt" };
		syjs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// �����Ѷ
		ArrayList<HashMap<String, String>> xgzx = new ArrayList<HashMap<String, String>>();
		sql = "select * from (select a.rowid,a.*,a.wjbt wjbttitle from JYGL_ZCWJB a order by fbsj desc ) b where wjlx !='������' and rownum<7";
		colList = new String[] { "rowid", "wjlx", "wjbt", "fbsj", "wjbttitle" };
		xgzx = dao.getArrayList3(sql, new String[] {}, colList);

		// ͼƬ��Ϣ
		ArrayList<HashMap<String, String>> tpxx = new ArrayList<HashMap<String, String>>();
		sql = "select b.rowid,b.bt,b.picpath1,b.bt bttitle from  (select a.rowid,a.bt,a.picpath1 from jygl_tpxxb a order by fbsj desc) b where rownum<3 ";
		colList = new String[] { "rowid", "bt", "picpath1", "bttitle" };
		tpxx = dao.getArrayList3(sql, new String[] {}, colList);

		// ��������
		ArrayList<HashMap<String, String>> yqlj = new ArrayList<HashMap<String, String>>();
		sql = "select b.rowid,b.mc,b.wz from  (select a.rowid,a.mc,a.wz from jygl_yqljb a ) b where rownum<11 ";
		colList = new String[] { "rowid", "mc", "wz" };
		yqlj = dao.getArrayList(sql, new String[] {}, colList);

		request.setAttribute("who", "anonymous");// Ĭ���û�Ϊ�����û�
		if ("left".equals(doType)) {
			session.setAttribute("usertype", "anonymous");
			session.setAttribute("yhm", "");
		}

		if (null != session.getAttribute("usertype")
				&& !("login".equals(doType)) && !("left".equals(doType))) {
			usertype = session.getAttribute("usertype").toString();
			if ("dw".equals(usertype)) {
				request.setAttribute("who", "dw");
				String dwmc = session.getAttribute("dwmc").toString();
				request.setAttribute("dwmc", dwmc);
			} else if ("xs".equals(usertype)) {
				request.setAttribute("who", "xs");
				String xm = session.getAttribute("xm").toString();
				request.setAttribute("xm", xm);
			} else if ("admin".equals(usertype)) {
				request.setAttribute("who", "admin");
				String xm = session.getAttribute("xm").toString();
				request.setAttribute("xm", xm);
			}
		}
		if (Globals.XXDM_ZJJJZYJSXY.equals(Base.xxdm)) {
			if ("login".equals(doType)) {
				String yhm = request.getParameter("yhm");
				String xh = request.getParameter("xh");
				String mm = ept.encrypt(request.getParameter("mm"));
				// ��λ�û���½
				if ("dw".equals(usertype)) {
					sql = "select dwmc from jygl_dwxxb where yhm=? and mm=? ";
					String dwinfo = dao.getOneRs(sql, new String[] { yhm, mm },
							"dwmc");

					if (null != dwinfo && !("".equals(dwinfo))) {
						sql = "select count(*) from jygl_dwjlb where gsmc=?";
						String dwinfo2 = dao.getOneRs(sql,
								new String[] { dwinfo }, "count(*)");

						if ("".equals(dwinfo2)) {
							session.setAttribute("howmany", "0");
						} else {
							session.setAttribute("howmany", dwinfo2);
						}
						session.setAttribute("yhm", yhm);
						session.setAttribute("user", yhm);
						session.setAttribute("dwmc", dwinfo);
						session.setAttribute("usertype", "dw");
						request.setAttribute("who", "dw");
					} else {
						request.setAttribute("login", "no");
					}
				}
				// ѧ���û���½
				if ("xs".equals(usertype)) {
					if ("12866".equals(Base.xxdm)) {
						String xnOrGdFlag = dao.getOneRs(
								"select jwflag from xtszb", new String[] {},
								"jwflag");// �����ѧ��ľͻ��1,������ֵ

						if ((xnOrGdFlag != null && xnOrGdFlag.trim()
								.equals("1"))
								&& usertype.equalsIgnoreCase("xs")) {
							mm = request.getParameter("mm");
						} else {
							mm = ept.encrypt(request.getParameter("mm"));
						}

						sql = "select xm from xsjyzcb where xh=? and mm=?";

						String stuinfo = dao.getOneRs(sql, new String[] { yhm,
								mm }, "xm");

						if (null != stuinfo && !("".equals(stuinfo))) {

							sql = "select count(*) from jygl_dwhfb where xh=?";
							String stuinfo2 = dao.getOneRs(sql,
									new String[] { xh }, "count(*)");
							if ("".equals(stuinfo2)) {
								session.setAttribute("howmanydw", "0");
							} else {
								session.setAttribute("howmanydw", stuinfo2);
							}

							session.setAttribute("yhm", yhm);
							session.setAttribute("user", yhm);
							session.setAttribute("xm", stuinfo);
							session.setAttribute("usertype", "xs");
							request.setAttribute("who", "xs");
						} else {
							request.setAttribute("login", "no");
						}
					} else {
						String xnOrGdFlag = dao.getOneRs(
								"select jwflag from xtszb", new String[] {},
								"jwflag");// �����ѧ��ľͻ��1,������ֵ

						if ((xnOrGdFlag != null && xnOrGdFlag.trim()
								.equals("1"))
								&& usertype.equalsIgnoreCase("xs")) {
							mm = request.getParameter("mm");
						} else {
							mm = ept.encrypt(request.getParameter("mm"));
						}

						sql = "select xm from view_xsjbxx where xh=? and mm=?";

						String stuinfo = dao.getOneRs(sql, new String[] { yhm,
								mm }, "xm");

						if (null != stuinfo && !("".equals(stuinfo))) {

							sql = "select count(*) from jygl_dwhfb where xh=?";
							String stuinfo2 = dao.getOneRs(sql,
									new String[] { yhm }, "count(*)");
							if ("".equals(stuinfo2)) {
								session.setAttribute("howmanydw", "0");
							} else {
								session.setAttribute("howmanydw", stuinfo2);
							}

							session.setAttribute("yhm", yhm);
							session.setAttribute("user", yhm);
							session.setAttribute("xm", stuinfo);
							session.setAttribute("usertype", "xs");
							request.setAttribute("who", "xs");
						} else {
							request.setAttribute("login", "no");
						}
					}
				}
				// ����Ա��½
				if ("admin".equals(usertype)) {
					sql = "select xm from yhb where yhm=? and kl=? and szbm='888888'";

					String admininfo = dao.getOneRs(sql,
							new String[] { yhm, mm }, "xm");

					if (null != admininfo && !("".equals(admininfo))) {
						session.setAttribute("yhm", yhm);
						session.setAttribute("user", yhm);
						session.setAttribute("xm", admininfo);
						session.setAttribute("usertype", "admin");
						request.setAttribute("who", "admin");
					} else {
						request.setAttribute("login", "no");
					}
				}
			}
		} else {
			if ("login".equals(doType)) {
				String yhm = request.getParameter("yhm");
				String xh = request.getParameter("xh");
				String mm = ept.encrypt(request.getParameter("mm"));
				String yzm = session.getAttribute("yzm").toString()
						.toLowerCase();
				String yzm2 = request.getParameter("yzm").toLowerCase();

				if (yzm.equals(yzm2)) {

					// ��λ�û���½
					if ("dw".equals(usertype)) {
						sql = "select dwmc from jygl_dwxxb where yhm=? and mm=? ";
						String dwinfo = dao.getOneRs(sql, new String[] { yhm,
								mm }, "dwmc");

						if (null != dwinfo && !("".equals(dwinfo))) {
							sql = "select count(*) from jygl_dwjlb where gsmc=?";
							String dwinfo2 = dao.getOneRs(sql,
									new String[] { dwinfo }, "count(*)");

							if ("".equals(dwinfo2)) {
								session.setAttribute("howmany", "0");
							} else {
								session.setAttribute("howmany", dwinfo2);
							}
							session.setAttribute("yhm", yhm);
							session.setAttribute("user", yhm);
							session.setAttribute("dwmc", dwinfo);
							session.setAttribute("usertype", "dw");
							request.setAttribute("who", "dw");
						} else {
							request.setAttribute("login", "no");
						}
					}
					// ѧ���û���½
					if ("xs".equals(usertype)) {
						if ("12866".equals(Base.xxdm)) {
							String xnOrGdFlag = dao.getOneRs(
									"select jwflag from xtszb",
									new String[] {}, "jwflag");// �����ѧ��ľͻ��1,������ֵ

							if ((xnOrGdFlag != null && xnOrGdFlag.trim()
									.equals("1"))
									&& usertype.equalsIgnoreCase("xs")) {
								mm = request.getParameter("mm");
							} else {
								mm = ept.encrypt(request.getParameter("mm"));
							}

							sql = "select xm from xsjyzcb where xh=? and mm=?";

							String stuinfo = dao.getOneRs(sql, new String[] {
									yhm, mm }, "xm");

							if (null != stuinfo && !("".equals(stuinfo))) {

								sql = "select count(*) from jygl_dwhfb where xh=?";
								String stuinfo2 = dao.getOneRs(sql,
										new String[] { xh }, "count(*)");
								if ("".equals(stuinfo2)) {
									session.setAttribute("howmanydw", "0");
								} else {
									session.setAttribute("howmanydw", stuinfo2);
								}

								session.setAttribute("yhm", yhm);
								session.setAttribute("user", yhm);
								session.setAttribute("xm", stuinfo);
								session.setAttribute("usertype", "xs");
								request.setAttribute("who", "xs");
							} else {
								request.setAttribute("login", "no");
							}
						} else {
							String xnOrGdFlag = dao.getOneRs(
									"select jwflag from xtszb",
									new String[] {}, "jwflag");// �����ѧ��ľͻ��1,������ֵ

							if ((xnOrGdFlag != null && xnOrGdFlag.trim()
									.equals("1"))
									&& usertype.equalsIgnoreCase("xs")) {
								mm = request.getParameter("mm");
							} else {
								mm = ept.encrypt(request.getParameter("mm"));
							}

							sql = "select xm from view_xsjbxx where xh=? and mm=?";

							String stuinfo = dao.getOneRs(sql, new String[] {
									yhm, mm }, "xm");

							if (null != stuinfo && !("".equals(stuinfo))) {

								sql = "select count(*) from jygl_dwhfb where xh=?";
								String stuinfo2 = dao.getOneRs(sql,
										new String[] { yhm }, "count(*)");
								if ("".equals(stuinfo2)) {
									session.setAttribute("howmanydw", "0");
								} else {
									session.setAttribute("howmanydw", stuinfo2);
								}

								session.setAttribute("yhm", yhm);
								session.setAttribute("user", yhm);
								session.setAttribute("xm", stuinfo);
								session.setAttribute("usertype", "xs");
								request.setAttribute("who", "xs");
							} else {
								request.setAttribute("login", "no");
							}
						}
					}
					// ����Ա��½
					if ("admin".equals(usertype)) {
						sql = "select xm from yhb where yhm=? and kl=? and szbm='888888'";

						String admininfo = dao.getOneRs(sql, new String[] {
								yhm, mm }, "xm");

						if (null != admininfo && !("".equals(admininfo))) {
							session.setAttribute("yhm", yhm);
							session.setAttribute("user", yhm);
							session.setAttribute("xm", admininfo);
							session.setAttribute("usertype", "admin");
							request.setAttribute("who", "admin");
						} else {
							request.setAttribute("login", "no");
						}
					}
				} else {
					request.setAttribute("yz", "no");
				}
			}
		}
		// ��Ƹ��Ϣ
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		if (!Base.isNull(Base.xxdm) && Base.xxdm.equals(Globals.XXDM_SHGC)
				&& session.getAttribute("usertype") != null
				&& "xs".equals(session.getAttribute("usertype").toString())) {
			sql = "select b.* from (select a.rowid rid, a.* from jygl_zpxxb a where sxzy is null or "
					+ "instr(',' || sxzy || ',',',' || (select zydm from view_xsjbxx where xh = '"
					+ session.getAttribute("yhm").toString()
					+ "') || ',')>0 "
					+ "order by fbsj desc) b where b.xxsh = 'ͨ��'";
		} else {
			sql = "select b.* from (select a.rowid rid ,a.* from jygl_zpxxb a order by fbsj desc ) b where b.xxsh='ͨ��' and rownum<9";
		}
		colList = new String[] { "rid", "gsmc", "zpzw", "fbsj", "readtimes" };
		rs = dao.getArrayList3(sql, new String[] {}, colList);
		// �ʾ����
		if ("wjdc".equals(doType2)) {
			String temp = session.getAttribute("wjdc") == null ? "" : session
					.getAttribute("wjdc").toString();
			if (temp.equals("")) {
				String choice = DealString
						.toGBK(request.getParameter("choice"));
				if (!("".equals(choice))) {
					sql = "select times from jygl_wjdcb where choice=?";
					String[] timesinfo = dao.getOneRs(sql,
							new String[] { choice }, new String[] { "times" });
					if (null != timesinfo) {
						String times = timesinfo[0];
						int time = Integer.parseInt(times);
						time++;
						times = String.valueOf(time);
						boolean judge = false;
						sql = "update jygl_wjdcb set times=? where choice=?";
						judge = dao.runUpdate(sql,
								new String[] { times, choice });
						if (judge) {
							request.setAttribute("wjdcok", "wjdcok");
							session.setAttribute("wjdc", "yes");
						} else {
							request.setAttribute("wjdcno", "wjdcno");
						}
					}
				} else {
					request.setAttribute("wjdcnull", "wjdcnull");
				}
			} else {
				request.setAttribute("wjdccf", "wjdccf");
			}
		}

		// ��������
		request.setAttribute("find", find);
		request.setAttribute("tpxx", tpxx);
		request.setAttribute("map", map);
		request.setAttribute("rs", rs);
		request.setAttribute("zxdt", zxdt);
		request.setAttribute("qzxx", qzxx);
		request.setAttribute("zczp", zczp);
		request.setAttribute("ggl", ggl);
		request.setAttribute("syjs", syjs);
		request.setAttribute("yqlj", yqlj);
		request.setAttribute("xgzx", xgzx);

		request.setAttribute("wjdc", wjdc);
		return mapping.findForward("success");
	}

	// �ʾ�������鿴
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

	// ����Ա �ʾ�������
	public ActionForward wjdcgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> wjdclist = new ArrayList<HashMap<String, String>>();

		String doType = request.getParameter("doType");
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_wjdcb",
					new String[] { "choice" }, new String[] { pkValue },
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

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
			sql = "select rownum, a.choice,a.times,round(to_number((a.times/( "
					+ sql1 + " )) * 100),2) bili from jygl_wjdcb a";
			wjdclist = dao.getArrayList(sql, new String[] {}, new String[] {
					"rownum", "choice", "times", "bili" });

		}
		// if(null==wjdclist){
		// reque
		// }
		request.setAttribute("wjdclist", wjdclist);
		request.setAttribute("bt", map);
		return mapping.findForward("success");

	}

	// �ʾ���� ����ѡ��
	public ActionForward addwjdcchoice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> rs = new HashMap<String, String>();
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String bt = DealString.toGBK(request.getParameter("pkValue"));
		String choice = DealString.toGBK(request.getParameter("choice"));

		if ("first".equals(act)) {
			request.setAttribute("first", "first");
		}
		if ("save".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.insert("jygl_wjdcb", new String[] { "bt",
					"choice" }, new String[] { bt, choice }, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
			request.setAttribute("goon", "goon");
		}
		map.put("bt", bt);
		request.setAttribute("bt", map);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}

	// �ʾ���� ���·����ʾ����
	public ActionForward newwjdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String sql = "delete jygl_wjdcb";
		dao.runUpdateTab(sql);
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");

		if ("first".equals(act)) {
			request.setAttribute("first", "first");
		}

		if ("save".equals(doType)) {
			String bt = DealString.toGBK(request.getParameter("bt"));
			String choice = DealString.toGBK(request.getParameter("choice"));
			boolean judge = false;

			judge = StandardOperation.insert("jygl_wjdcb", new String[] { "bt",
					"choice" }, new String[] { bt, choice }, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
		}

		return mapping.findForward("success");
	}

	// ����Ա����
	public ActionForward adminoperation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String act = request.getParameter("act");

		// ����Ա����
		// ������Ŀ����
		if ("addmessage".equals(act)) {

			return mapping.findForward("success");
		}
		// �����Ķ������
		if ("fbshenhe".equals(act)) {
			return mapping.findForward("success2");
		}
		// ���¶�̬����
		if ("zxdt".equals(act)) {
			return mapping.findForward("success3");
		}
		// �������Ĺ���
		if ("xzzx".equals(act)) {
			return mapping.findForward("success4");
		}
		// ��������
		if ("jlgl".equals(act)) {
			return mapping.findForward("success5");
		}
		// ��ְ����
		if ("qzxxgl".equals(act)) {
			return mapping.findForward("success6");
		}
		// ͼƬ��Ϣ
		if ("tpxx".equals(act)) {
			return mapping.findForward("success7");
		}

		// �ʾ�������
		if ("wjdcgl".equals(act)) {
			return mapping.findForward("success9");
		}

		// �û�����
		if ("yhgl".equals(act)) {
			return mapping.findForward("success8");
		}

		// ��ҵָ��
		if ("jyzd".equals(act)) {
			return mapping.findForward("success10");
		}
		// ������Ƹ
		if ("fbzp".equals(act)) {
			return mapping.findForward("success11");
		}
		// ʵʱͳ��
		if ("sstj".equals(act)) {
			return mapping.findForward("success12");
		}
		// ��������
		if ("yqlj".equals(act)) {
			return mapping.findForward("success13");
		}

		// ��ҵ����
		// ������Ƹ��Ϣ
		if ("zpxx".equals(act)) {
			return mapping.findForward("successA");
		}
		// �鿴���˼���
		if ("ckjl".equals(act)) {
			return mapping.findForward("successB");
		}
		// Ĭ�ϻظ�����
		if ("mrhf".equals(act)) {
			return mapping.findForward("successC");
		}

		// ѧ������
		// ���˼����Ǽ�
		if ("grjldj".equals(act)) {
			return mapping.findForward("successW");
		}
		// ��ְ��Ϣ����
		if ("qzxx".equals(act)) {
			return mapping.findForward("successX");
		}
		// �ҵ��ļ���
		if ("mywj".equals(act)) {
			return mapping.findForward("successY");
		}
		return mapping.findForward("success14");
	}

	// ʵʱͳ��
	public ActionForward sstj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		String grjlnum = "0"; // �ѵǼǼ���������
		String grjltdnum = "0"; // ���˼���Ͷ������
		String dwnum = "0"; // ��ע�ᵥλ����
		String dwhfnum = "0"; // ��λ�ۼƻظ�ѧ��������
		String zpxxllnum = "0"; // ��Ƹ��Ϣ�ۼƱ��������
		String zpxxfbnum = "0";// ��Ƹ��Ϣ������
		String fbzpxxdws = "0";// ������Ƹ��Ϣ��λ��
		String hotdw = "�޽��"; // �����ŵ�λ
		String cooldw = "�޽��"; // �����ŵ�λ
		String hotllgw = "�޽��"; // �����������λ
		String hottdgw = "�޽��"; // �����ż���Ͷ�ݸ�λ
		String hotscgw = "�޽��"; // �������ղظ�λ
		String coolllgw = "�޽��"; // �����������λ
		String cooltdgw = "�޽��"; // �����ż���Ͷ�ݸ�λ
		String coolscgw = "�޽��"; // �������ղظ�λ

		// �ǼǼ�������
		sql = "select count(*) count from jygl_grjlb";
		grjlnum = dao.getOneRs(sql, new String[] {}, "count");
		map.put("grjlnum", grjlnum);
		// ��ע�ᵥλ��
		sql = "select count(*) count from jygl_dwxxb";
		dwnum = dao.getOneRs(sql, new String[] {}, "count");
		map.put("dwnum", dwnum);
		// ���˼���Ͷ������
		sql = "select count(*) count from jygl_dwjlb";
		grjltdnum = dao.getOneRs(sql, new String[] {}, "count");
		map.put("grjltdnum", grjltdnum);

		// ��Ƹ��Ϣ������
		sql = "select count(*) count from jygl_zpxxb";
		zpxxfbnum = dao.getOneRs(sql, new String[] {}, "count");
		map.put("zpxxfbnum", zpxxfbnum);
		// ������Ƹ��Ϣ��λ��
		sql = "select count(distinct gsmc) count from jygl_zpxxb";
		fbzpxxdws = dao.getOneRs(sql, new String[] {}, "count");
		map.put("fbzpxxdws", fbzpxxdws);

		// ��λ�ۼƻظ�ѧ��������
		sql = "select count(*) count from jygl_dwhfb";
		dwhfnum = dao.getOneRs(sql, new String[] {}, "count");
		map.put("dwhfnum", dwhfnum);
		// ��Ƹ��Ϣ�ۼƱ��������
		sql = "select count(*) count from jygl_xsscb where savetype='���'";
		zpxxllnum = dao.getOneRs(sql, new String[] {}, "count");
		map.put("zpxxllnum", zpxxllnum);

		// �����ŵ�λ���ƣ�
		sql = "select gsmc from (select gsmc,cout from (select gsmc,count(*) cout  from jygl_xsscb group by gsmc)  where cout = (select max(cout) from (select gsmc,count(*) cout  from jygl_xsscb  group by gsmc)) group by gsmc)";

		hotdw = dao.getOneRs(sql, new String[] {}, "gsmc");
		map.put("hotdw", hotdw);

		// �����������λ
		sql = "select zpzw from (select zpzw,cout from (select zpzw,count(*) cout  from jygl_xsscb where savetype='���' group by zpzw)  where cout = (select max(cout) from (select zpzw,count(*) cout  from jygl_xsscb where savetype='���' group by zpzw))  group by zpzw)";
		String[] hotllgwinfo = dao.getOneRs(sql, new String[] {},
				new String[] { "zpzw" });
		if (null != hotllgwinfo) {
			hotllgw = hotllgwinfo[0];
		}
		map.put("hotllgw", hotllgw);

		// ���ܼ�������λ
		sql = "select zpzw from (select zpzw,cout from (select zpzw,count(*) cout  from jygl_xsscb where savetype='Ͷ����' group by zpzw)  where cout = (select max(cout) from (select zpzw,count(*) cout  from jygl_xsscb where savetype='Ͷ����' group by zpzw))  group by zpzw)";
		String[] hottdgwinfo = dao.getOneRs(sql, new String[] {},
				new String[] { "zpzw" });
		if (null != hottdgwinfo) {
			hottdgw = hottdgwinfo[0];
		}
		map.put("hottdgw", hottdgw);

		// ���ղ����ĸ�λ
		sql = "select zpzw from (select zpzw,cout from (select zpzw,count(*) cout  from jygl_xsscb where savetype='�ղ�' group by zpzw)  where cout = (select max(cout) from (select zpzw,count(*) cout  from jygl_xsscb where savetype='�ղ�' group by zpzw))  group by zpzw)";
		String[] hotscgwinfo = dao.getOneRs(sql, new String[] {},
				new String[] { "zpzw" });
		if (null != hotscgwinfo) {
			hotscgw = hotscgwinfo[0];
		}
		map.put("hotscgw", hotscgw);

		// �����������λ
		sql = "select zpzw from (select zpzw,cout from (select zpzw,count(*) cout  from jygl_xsscb where savetype='���' group by zpzw)  where cout = (select min(cout) from (select zpzw,count(*) cout  from jygl_xsscb where savetype='���' group by zpzw))  group by zpzw)";
		String[] coolllgwinfo = dao.getOneRs(sql, new String[] {},
				new String[] { "zpzw" });
		if (null != coolllgwinfo) {
			coolllgw = coolllgwinfo[0];
		}
		map.put("coolllgw", coolllgw);

		// ���ܼ������ٸ�λ
		sql = "select zpzw from (select zpzw,cout from (select zpzw,count(*) cout  from jygl_xsscb where savetype='Ͷ����' group by zpzw)  where cout = (select min(cout) from (select zpzw,count(*) cout  from jygl_xsscb where savetype='Ͷ����' group by zpzw))  group by zpzw)";
		String[] cooltdgwinfo = dao.getOneRs(sql, new String[] {},
				new String[] { "zpzw" });
		if (null != cooltdgwinfo) {
			cooltdgw = cooltdgwinfo[0];
		}
		map.put("cooltdgw", cooltdgw);

		// ���ղ����ٵĸ�λ
		sql = "select zpzw from (select zpzw,cout from (select zpzw,count(*) cout  from jygl_xsscb where savetype='�ղ�' group by zpzw)  where cout = (select min(cout) from (select zpzw,count(*) cout  from jygl_xsscb where savetype='�ղ�' group by zpzw))  group by zpzw)";
		String[] coolscgwinfo = dao.getOneRs(sql, new String[] {},
				new String[] { "zpzw" });
		if (null != coolscgwinfo) {
			coolscgw = coolscgwinfo[0];
		}
		map.put("coolscgw", coolscgw);

		// �����ŵ�λ
		sql = "select gsmc from (select gsmc,cout from (select gsmc,count(*) cout  from jygl_xsscb group by gsmc)  where cout = (select min(cout) from (select gsmc,count(*) cout  from jygl_xsscb  group by gsmc)) group by gsmc)";
		String[] cooldwinfo = dao.getOneRs(sql, new String[] {},
				new String[] { "gsmc" });
		if (null != cooldwinfo) {
			cooldw = cooldwinfo[0];
		}
		map.put("cooldw", cooldw);

		request.setAttribute("rs", map);
		return mapping.findForward("success");

	}

	// ����Ա�������¶�̬
	public ActionForward addzxdt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String yhm = session.getAttribute("yhm").toString();
		String newsid = request.getParameter("pkValue");
		String[] colList = null;
		String rsNum = "0";
		ArrayList<HashMap<String, String>> news = new ArrayList<HashMap<String, String>>();
		String doType = request.getParameter("doType");
		String newsTitle = DealString.toGBK(request.getParameter("bt"));
		String sContent = DealString.toGBK(request.getParameter("content1"));
		// String pubtime = (dao
		// .getOneRs(
		// "select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
		// //
		// ȡ�����ݿ���ʱ��
		// new String[] {}, new String[] { "sdate" }))[0];
		String sql = "";

		if ("save".equals(doType)) {
			boolean judge = false;
			judge = dao.addZxdt(newsTitle, "N13", sContent, yhm);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("newscontent", "newsid", newsid,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from newscontent a where NEWSPART = 'N13'";

		rsNum = dao.getOneRs(sql, new String[] {}, "count");
		dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));

		// ���¶�̬�����ţ�

		sql = "select * from (select a.*,rownum r from (select distinct a.NEWSID,a.NEWSTITLE,a.PUBTIME,a.PUBER from newscontent a where a.NEWSPART='N13' order by pubtime desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by pubtime desc";

		colList = new String[] { "newsid", "r", "newstitle", "pubtime", "puber" };

		request.setAttribute("rsNum", rsNum);
		news = dao.getArrayList3(sql, new String[] {}, colList);

		request.setAttribute("news", news);
		request.setAttribute("zxdtclass", "current");
		return mapping.findForward("success");
	}

	// ��ҵ����Ŀ����
	public ActionForward addmessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String sql = "";
		HttpSession session = request.getSession();
		String[] colList = null;
		String[] colListCN = null;
		ArrayList<Object> rs = new ArrayList<Object>();
		String rowid = request.getParameter("pkValue");

		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_zcwjb", "rowid", rowid,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		if ("save".equals(doType)) {
			String wjbt = DealString.toGBK(request.getParameter("bt"));
			String wjlx = DealString.toGBK(request.getParameter("wjlx"));
			String wjnr = DealString.toGBK(request.getParameter("content1"));
			String fbr = session.getAttribute("yhm").toString();
			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];
			boolean judge = false;
			judge = dao.addMessage(wjbt, wjlx, wjnr, fbr, fbsj);

			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from jygl_zcwjb a";

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ���¶�̬�����ţ�

		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.wjbt,a.wjlx ,a.fbr,a.fbsj from jygl_zcwjb a  order by wjlx, fbsj desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ " order by wjlx , fbsj desc";

		colList = new String[] { "rid", "wjbt", "wjlx", "fbr", "fbsj" };
		colListCN = dao.getColumnNameCN(colList, "jygl_zcwjb");
		List topTr = dao.arrayToList(colList, colListCN);
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}

	// ��λ�û�ע��
	public ActionForward dwregister(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		Encrypt ept = new Encrypt();
		String sql = "";
		String tableName = "jygl_dwxxb";
		HashMap<String, String> map = new HashMap<String, String>();
		sql = "select dwxzdm,dwxz from dmk_dwxz2"; // ��λ���ʴ���2
		List dwxzdm2List = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzdm2List", dwxzdm2List);

		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);
		String doType = request.getParameter("doType");

		if ("save".equals(doType)) {

			String yhm = DealString.toGBK(request.getParameter("yhm")); // �û���
			String mm = ept.encrypt(request.getParameter("mm")); // ����
			String dwmc = DealString.toGBK(request.getParameter("dwmc")); // ��λ����
			String qyfr = DealString.toGBK(request.getParameter("qyfr")); // ��ҵ����
			String dwxz = DealString.toGBK(request.getParameter("dwxz")); // ��λ����
			String hyfl = DealString.toGBK(request.getParameter("hyfl")); // ��ҵ����
			String dz = DealString.toGBK(request.getParameter("dz")); // ��ַ
			String lxr = DealString.toGBK(request.getParameter("lxr")); // ��ϵ��
			String lxdh = DealString.toGBK(request.getParameter("lxdh")); // ��ϵ�绰
			String email = DealString.toGBK(request.getParameter("email")); // ��������
			String yb = DealString.toGBK(request.getParameter("yb")); // �ʱ�
			String cz = DealString.toGBK(request.getParameter("cz")); // ����
			String dwjj = DealString.toGBK(request.getParameter("dwjj")); // ��λ���
			String tswt1 = DealString.toGBK(request.getParameter("tswt1")); // ��ʾ����1
			String da1 = DealString.toGBK(request.getParameter("da1")); // ��1
			String tswt2 = DealString.toGBK(request.getParameter("tswt2")); // ��ʾ����2
			String da2 = DealString.toGBK(request.getParameter("da2")); // ��2
			String jgdmzh = DealString.toGBK(request.getParameter("jgdmzh")); // ��������֤��

			String zcsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ע��ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];

			HttpSession session = request.getSession();
			String yzm = session.getAttribute("yzm").toString().toLowerCase();
			String yzm2 = request.getParameter("yzm").toLowerCase();
			if (yzm2 == null || !(yzm2.equals(yzm)) || "".equals(yzm2)) {
				request.setAttribute("add", "no");

				map.put("dwmc", dwmc);
				map.put("qyfr", qyfr);
				map.put("dwxz", dwxz);
				map.put("hyfl", hyfl);
				map.put("dz", dz);
				map.put("lxr", lxr);
				map.put("lxdh", lxdh);
				map.put("email", email);
				map.put("yb", yb);
				map.put("cz", cz);
				map.put("dwjj", dwjj);
				map.put("tswt1", tswt1);
				map.put("da1", da1);
				map.put("tswt2", tswt2);
				map.put("da2", da2);
				map.put("jgdmzh", jgdmzh);
				request.setAttribute("rs", map);
				session.setAttribute("yzm", "");

				return mapping.findForward("success");
			}

			sql = "select yhm from jygl_dwxxb where dwmc=?";
			String dwinfo = dao.getOneRs(sql, new String[] { dwmc }, "yhm");

			boolean judge = false;

			if ("".equals(dwinfo)) {

				judge = StandardOperation.insert(tableName, new String[] {
						"yhm", "mm", "dwmc", "qyfr", "dwxz", "hyfl", "dz",
						"lxr", "lxdh", "email", "yb", "cz", "dwjj", "tswt1",
						"da1", "tswt2", "da2", "jgdmzh", "zcsj" },
						new String[] { yhm, mm, dwmc, qyfr, dwxz, hyfl, dz,
								lxr, lxdh, email, yb, cz, dwjj, tswt1, da1,
								tswt2, da2, jgdmzh, zcsj }, request);
			} else {
				request.setAttribute("dwmcchongfu", "dwmcchongfu");
			}
			if (judge) {
				return mapping.findForward("success2");
			} else {
				request.setAttribute("save", "no");
				map.put("dwmc", dwmc);
				map.put("qyfr", qyfr);
				map.put("dwxz", dwxz);
				map.put("hyfl", hyfl);
				map.put("dz", dz);
				map.put("lxr", lxr);
				map.put("lxdh", lxdh);
				map.put("email", email);
				map.put("yb", yb);
				map.put("cz", cz);
				map.put("dwjj", dwjj);
				map.put("tswt1", tswt1);
				map.put("da1", da1);
				map.put("tswt2", tswt2);
				map.put("da2", da2);
				map.put("jgdmzh", jgdmzh);
			}
			session.setAttribute("yzm", "");
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// �㽭����ѧ���û�ע��
	public ActionForward xsregister(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		Encrypt ept = new Encrypt();
		String sql = "";
		String tableName = "xsjyzcb";
		HashMap<String, String> map = new HashMap<String, String>();
		sql = "select dwxzdm,dwxz from dmk_dwxz2"; // ��λ���ʴ���2
		List dwxzdm2List = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzdm2List", dwxzdm2List);

		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);
		String doType = request.getParameter("doType");

		if ("save".equals(doType)) {

			String xh = DealString.toGBK(request.getParameter("xh"));
			String mm = ept.encrypt(request.getParameter("mm"));
			String cfmm = DealString.toGBK(request.getParameter("mm2"));
			String tswt1 = DealString.toGBK(request.getParameter("tswt1"));
			String da1 = DealString.toGBK(request.getParameter("da1"));
			String tswt2 = DealString.toGBK(request.getParameter("tswt2"));
			String da2 = DealString.toGBK(request.getParameter("da2"));
			String xm = DealString.toGBK(request.getParameter("xm"));
			String lxdh = DealString.toGBK(request.getParameter("lxdh"));
			String cz = DealString.toGBK(request.getParameter("cz"));
			String sjhm = DealString.toGBK(request.getParameter("sjhm"));
			String dzyx = DealString.toGBK(request.getParameter("dzyx"));
			String dz = DealString.toGBK(request.getParameter("dz"));
			String yzbh = DealString.toGBK(request.getParameter("yb"));

			String zcsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ע��ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];

			HttpSession session = request.getSession();

		

			sql = "SELECT * from view_xsjbxx where xh=?";
			String dwinfo = dao.getOneRs(sql, new String[] { xh }, "mm");

			// if (yzm2 == null || !(yzm2.equals(yzm)) || "".equals(yzm2) ||
			// dwinfo == null || "".equals(dwinfo)) {
			if (dwinfo == null || "".equals(dwinfo)) {

				// request.setAttribute("add", "no");
				map.put("xh", xh);
				map.put("mm", mm);
				map.put("cfmm", cfmm);
		
				map.put("tswt1", tswt1);
				map.put("da1", da1);
				map.put("tswt2", tswt2);
				map.put("da2", da2);
				map.put("xm", xm);
				map.put("lxdh", lxdh);
				map.put("cz", cz);
				map.put("sjhm", sjhm);
				map.put("dzyx", dzyx);
				map.put("dz", dz);
				map.put("yzbh", yzbh);
				request.setAttribute("rs", map);
				request.setAttribute("xhbzq", "xhbzq");
				return mapping.findForward("success");
			}

			sql = "SELECT * from xsjyzcb where xh=?";
			String dwinfo1 = dao.getOneRs(sql, new String[] { xh }, "mm");
			dwinfo1 = Base.isNull(dwinfo1) ? "" : dwinfo1;
			if (!"".equals(dwinfo1)) {
				request.setAttribute("yhyzc", "yhyzc");
				request.setAttribute("rs", map);
				return mapping.findForward("success");
			}

			boolean judge = false;

			if ("".equals(dwinfo1)) {

				judge = StandardOperation.insert(tableName, new String[] {
						"xh", "mm", "tswt1", "da1", "tswt2", "da2",
						"xm", "lxdh", "cz", "sjhm", "dzyx", "dz", "yzbh",
						"zcsj" }, new String[] { xh, mm, tswt1, da1,
						tswt2, da2, xm, lxdh, cz, sjhm, dzyx, dz, yzbh, zcsj },
						request);
			} else {
				request.setAttribute("dwmcchongfu", "dwmcchongfu");
			}
			if (judge) {
				return mapping.findForward("success2");
			} else {
				request.setAttribute("save", "no");
				map.put("xh", xh);
				map.put("mm", mm);
				map.put("cfmm", cfmm);
				map.put("tswt1", tswt1);
				map.put("da1", da1);
				map.put("tswt2", tswt2);
				map.put("da2", da2);
				map.put("xm", xm);
				map.put("lxdh", lxdh);
				map.put("cz", cz);
				map.put("sjhm", sjhm);
				map.put("dzyx", dzyx);
				map.put("dz", dz);
				map.put("yzbh", yzbh);
			}
			session.setAttribute("yzm", "");
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// �û��޸�����
	public ActionForward changepassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Encrypt ept = new Encrypt(); // ���ܷ���
		DAO dao = DAO.getInstance();
		String usertype = "";
		String sql = "";
		String yhm = "";
		String doType = request.getParameter("doType");
		HttpSession session = request.getSession();
		// boolean judge = false;

		if (null != session.getAttribute("usertype") && "change".equals(doType)) {
			usertype = session.getAttribute("usertype").toString();
			yhm = session.getAttribute("yhm").toString();
			String ymm = ept.encrypt(request.getParameter("ymm"));
			String xmm = ept.encrypt(request.getParameter("xmm"));
			// ����Ա
			if ("admin".equals(usertype)) {
				String sql2 = "select kl from yhb where yhm=?";
				String xinfo = dao.getOneRs(sql2, new String[] { yhm }, "kl");
				sql = "update yhb set kl=? where yhm=? and kl=?";
				dao.runUpdate(sql, new String[] { xmm, yhm, ymm });
				String hinfo = dao.getOneRs(sql2, new String[] { yhm }, "kl");
				if (xinfo.equals(hinfo)) {
					return mapping.findForward("success3");
				} else {
					return mapping.findForward("success2");
				}
			}
			// ��λ�û�
			if ("dw".equals(usertype)) {
				String sql2 = "select mm from jygl_dwxxb where yhm=?";
				String xinfo = dao.getOneRs(sql2, new String[] { yhm }, "mm");
				sql = "update jygl_dwxxb set mm=? where yhm=? and mm=?";
				dao.runUpdate(sql, new String[] { xmm, yhm, ymm });
				String hinfo = dao.getOneRs(sql2, new String[] { yhm }, "mm");
				if (xinfo.equals(hinfo)) {
					return mapping.findForward("success3");
				} else {
					return mapping.findForward("success2");
				}
			}
			// ѧ��
			if ("xs".equals(usertype)) {
				String hinfo;
				String xinfo;
				if(Globals.XXDM_ZJJJZYJSXY.equals(Base.xxdm)){
					String sql2 = "select mm from xsjyzcb where xh=?";
					xinfo = dao.getOneRs(sql2, new String[] { yhm }, "mm");
					sql = "update xsjyzcb set mm=? where xh=? and mm=?";
					dao.runUpdate(sql, new String[] { xmm, yhm, ymm });
				    hinfo = dao.getOneRs(sql2, new String[] { yhm }, "mm");
				}else{
					String sql2 = "select mm from view_xsjbxx where xh=?";
					xinfo = dao.getOneRs(sql2, new String[] { yhm }, "mm");
					sql = "update xsmmb set mm=? where xh=? and mm=?";
					dao.runUpdate(sql, new String[] { xmm, yhm, ymm });
				    hinfo = dao.getOneRs(sql2, new String[] { yhm }, "mm");
				}
				if (xinfo.equals(hinfo)) {
					return mapping.findForward("success3");
				} else {
					return mapping.findForward("success2");
				}
			}
		}

		return mapping.findForward("success");
	}

	// ��λ�û������һ�
	public ActionForward dwyhmmzh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		// Encrypt ept = new Encrypt();
		String sql = "";
		String doType1 = request.getParameter("doType1");
		String doType2 = request.getParameter("doType2");
		String yhm = DealString.toGBK(request.getParameter("yhm"));
		String tswt1 = DealString.toGBK(request.getParameter("tswt1"));
		String da1 = DealString.toGBK(request.getParameter("da1"));
		String tswt2 = DealString.toGBK(request.getParameter("tswt2"));
		String da2 = DealString.toGBK(request.getParameter("da2"));
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		request.setAttribute("timesago", "0");

		if ("pro".equals(doType1)) {
			sql = "select tswt1 , tswt2 from jygl_dwxxb where yhm=?";
			String[] dwinfo = dao.getOneRs(sql, new String[] { yhm },
					new String[] { "tswt1", "tswt2" });
			if (null == dwinfo) {
				request.setAttribute("nothisyhm", "nothisyhm");
			} else {
				map.put("yhm", yhm);
				map.put("tswt1", dwinfo[0]);
				map.put("tswt2", dwinfo[1]);
			}
		}

		if ("find".equals(doType2)) {
			sql = "select dwmc from  jygl_dwxxb where yhm=? and tswt1=? and da1=? and tswt2=? and da2=?";
			String dwmc = dao.getOneRs(sql, new String[] { yhm, tswt1, da1,
					tswt2, da2 }, "dwmc");
			if (null != dwmc && !("".equals(dwmc))) {
				session.setAttribute("yhm", yhm);
				return mapping.findForward("success2");
			}
			map.put("yhm", yhm);
			map.put("tswt1", tswt1);
			map.put("tswt2", tswt2);
			request.setAttribute("find", "no");
			// ��ʼ�����ѯ����
			int inttimesago = 0;
			String strtimesago = "0";

			inttimesago = Integer.valueOf(request.getParameter("timesago"));
			inttimesago++;
			strtimesago = String.valueOf(inttimesago);
			request.setAttribute("timesago", strtimesago);
			if (inttimesago > 3) {
				request.setAttribute("find", "most");
				return mapping.findForward("success3");
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ��λ�û��������
	public ActionForward dwyhmmchange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		Encrypt ept = new Encrypt();
		String doType = request.getParameter("doType");

		String yhm = session.getAttribute("yhm").toString();
		if ("".equals(yhm) || null == yhm) {
			return mapping.findForward("success2");
		}
		String mm = ept.encrypt(request.getParameter("mm"));

		if ("update".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.update("jygl_dwxxb",
					new String[] { "mm" }, new String[] { mm }, "yhm", yhm,
					request);
			if (judge) {
				return mapping.findForward("success3");
			} else {
				request.setAttribute("update", "no");
			}
		}

		return mapping.findForward("success");
	}

	// ����Ա������Ƹ��Ϣ
	public ActionForward adminaddzpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArrayList<Object> list = new ArrayList<Object>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String rsNum = "0";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String[] colList = null;
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String yhm = session.getAttribute("yhm").toString();
		if (null != pkValue) {
			pkValue = pkValue.replaceAll(" ", "+");
		}

		if ("save".equals(doType)) {
			String dwxz = DealString.toGBK(request.getParameter("dwxz"));
			String zpzw = DealString.toGBK(request.getParameter("zpzw"));
			String gsmc = DealString.toGBK(request.getParameter("gsmc"));
			String email = request.getParameter("email");
			String lxdh = request.getParameter("lxdh");
			String gzdd = DealString.toGBK(request.getParameter("gzdd")); // �����ص�
			String zprs = DealString.toGBK(request.getParameter("zprs"));
			String hyfl = DealString.toGBK(request.getParameter("hyfl"));
			String wyyq = DealString.toGBK(request.getParameter("wyyq"));
			String syxs = DealString.toGBK(request.getParameter("syxs"));
			String zzxs = DealString.toGBK(request.getParameter("zzxs"));
			String xlyq = DealString.toGBK(request.getParameter("xlyq"));
			String gwzz = DealString.toGBK(request.getParameter("gwzz"));
			String zwyq = DealString.toGBK(request.getParameter("zwyq"));
			String gsjj = DealString.toGBK(request.getParameter("gsjj"));
			String msxd = DealString.toGBK(request.getParameter("msxd"));
			String msdd = DealString.toGBK(request.getParameter("msdd"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String dwdz = DealString.toGBK(request.getParameter("dwdz"));
			String yddh = DealString.toGBK(request.getParameter("yddh"));
			String lxr = DealString.toGBK(request.getParameter("lxr"));
			String gswz = DealString.toGBK(request.getParameter("gswz"));
			String cz = DealString.toGBK(request.getParameter("cz"));
			String xxsh = "ͨ��";
			String mssj = "";
			String day = DealString.toGBK(request.getParameter("day"));
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");
			String viewzydm = request.getParameter("viewzydm");
			if (Base.isNull(day)) {
				mssj = "";
			} else if (Base.isNull(hour)) {
				mssj = day;
			} else if (Base.isNull(min)) {
				mssj = day + " " + hour;
			} else {
				mssj = day + " " + hour + ":" + min;
			}
			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];

			boolean judge = false;
			judge = StandardOperation.insert("jygl_zpxxb",
					new String[] { "dwxz", "zpzw", "gsmc", "email", "lxdh",
							"gzdd", "zprs", "hyfl", "wyyq", "syxs", "zzxs",
							"xlyq", "mssj", "gwzz", "zwyq", "gsjj", "fbsj",
							"msxd", "msdd", "xb", "fbr", "xxsh", "dwdz",
							"yddh", "lxr", "gswz", "cz", "xxly", "sxzy" },
					new String[] { dwxz, zpzw, gsmc, email, lxdh, gzdd, zprs,
							hyfl, wyyq, syxs, zzxs, xlyq, mssj, gwzz, zwyq,
							gsjj, fbsj, msxd, msdd, xb, yhm, xxsh, dwdz, yddh,
							lxr, gswz, cz, "ѧУ", viewzydm }, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}

		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_zpxxb", "rowid", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from jygl_zpxxb a where fbr=?";
		rsNum = dao.getOneRs(sql, new String[] { yhm }, "count");
		dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));

		// ��Ƹ��Ϣ
		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.gsmc,a.fbsj,a.xxsh,a.zpzw from jygl_zpxxb a where fbr=?"
				+ " order by fbsj desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by fbsj desc";

		colList = new String[] { "rid", "gsmc", "fbsj", "xxsh", "zpzw" };
		list.addAll(dao.rsToVator2(sql, new String[] { yhm }, colList));

		request.setAttribute("rsNum", rsNum);

		// sql = "select b.rowid, b.* from (select a.* from jygl_zpxxb a order
		// by fbsj desc ) b where b.fbr=?";
		// colList = new String[] { "rowid", "gsmc", "fbsj", "xxsh", "zpzw" };
		// list.addAll(dao.rsToVator(sql, new String[] { yhm }, colList));
		//
		// // ��¼��
		// if (list == null) {
		// rsNum = "0";
		// } else {
		// rsNum = String.valueOf(list.size());
		// }
		// request.setAttribute("rsNum", rsNum);

		// ��ҵ�����б�
		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);
		if (!Base.isNull(Base.xxdm) && Base.xxdm.equals(Globals.XXDM_SHGC)) {
			sql = "select zydm,zymc from bks_zydm order by bmdm";
			List zyList = dao.getList(sql, new String[] {}, new String[] {
					"zydm", "zymc" });
			request.setAttribute("zyList", zyList);
		}
		request.setAttribute("list", list);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ��λ������Ƹ��Ϣ
	public ActionForward addzpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String sql = "";
		String rsNum = "0";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String[] colList = null;
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String dwmc = session.getAttribute("dwmc").toString();
		String yhm = session.getAttribute("yhm").toString();
		map.put("gsmc", dwmc);

		if (null != pkValue) {
			pkValue = pkValue.replaceAll(" ", "+");
		}

		if ("save".equals(doType)) {
			String dwdz = DealString.toGBK(request.getParameter("dwdz"));
			String dwxz = DealString.toGBK(request.getParameter("dwxz"));
			String zpzw = DealString.toGBK(request.getParameter("zpzw"));
			String gsmc = DealString.toGBK(request.getParameter("gsmc"));
			String email = request.getParameter("email");
			String lxdh = request.getParameter("lxdh");
			String gzdd = DealString.toGBK(request.getParameter("gzdd")); // �����ص�
			String zprs = DealString.toGBK(request.getParameter("zprs"));
			String hyfl = DealString.toGBK(request.getParameter("hyfl"));
			String wyyq = DealString.toGBK(request.getParameter("wyyq"));
			String syxs = DealString.toGBK(request.getParameter("syxs"));
			String zzxs = DealString.toGBK(request.getParameter("zzxs"));
			String xlyq = DealString.toGBK(request.getParameter("xlyq"));
			String gwzz = DealString.toGBK(request.getParameter("gwzz"));
			String zwyq = DealString.toGBK(request.getParameter("zwyq"));
			String gsjj = DealString.toGBK(request.getParameter("gsjj"));
			String msxd = DealString.toGBK(request.getParameter("msxd"));
			String msdd = DealString.toGBK(request.getParameter("msdd"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String yddh = DealString.toGBK(request.getParameter("yddh"));
			String lxr = DealString.toGBK(request.getParameter("lxr"));
			String gswz = DealString.toGBK(request.getParameter("gswz"));
			String cz = DealString.toGBK(request.getParameter("cz"));
			String mssj = "";
			String day = DealString.toGBK(request.getParameter("day"));
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");
			String viewzydm = request.getParameter("viewzydm");
			if (Base.isNull(day)) {
				mssj = "";
			} else if (Base.isNull(hour)) {
				mssj = day;
			} else if (Base.isNull(min)) {
				mssj = day + " " + hour;
			} else {
				mssj = day + " " + hour + ":" + min;
			}
			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];

			boolean judge = false;
			judge = StandardOperation.insert("jygl_zpxxb", new String[] {
					"dwdz", "dwxz", "zpzw", "gsmc", "email", "lxdh", "gzdd",
					"zprs", "hyfl", "wyyq", "syxs", "zzxs", "xlyq", "mssj",
					"gwzz", "zwyq", "gsjj", "fbsj", "msxd", "msdd", "xb",
					"yddh", "lxr", "gswz", "cz", "xxly", "sxzy" },
					new String[] { dwdz, dwxz, zpzw, gsmc, email, lxdh, gzdd,
							zprs, hyfl, wyyq, syxs, zzxs, xlyq, mssj, gwzz,
							zwyq, gsjj, fbsj, msxd, msdd, xb, yddh, lxr, gswz,
							cz, "��λ", viewzydm }, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}

		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_zpxxb", "rowid", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		sql = "select email,lxdh,hyfl,dwjj,dwxz from jygl_dwxxb where yhm=?";
		String[] dwinfo = dao.getOneRs(sql, new String[] { yhm }, new String[] {
				"email", "lxdh", "hyfl", "dwjj", "dwxz" });
		if (null != dwinfo) {
			map.put("email", dwinfo[0]);
			map.put("lxdh", dwinfo[1]);
			map.put("hyfl", dwinfo[2]);
			map.put("gsjj", dwinfo[3]);
			map.put("dwxz", dwinfo[4]);
		}

		sql = "select b.rowid, b.* from (select a.* from jygl_zpxxb a order by fbsj desc ) b where b.gsmc=?";
		colList = new String[] { "rowid", "gsmc", "fbsj", "xxsh", "zpzw" };
		list = dao.getArrayList2(sql, new String[] { dwmc }, colList);

		// ��¼��
		if (list == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(list.size());
		}
		request.setAttribute("rsNum", rsNum);

		// ��ҵ�����б�
		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);
		if (!Base.isNull(Base.xxdm) && Base.xxdm.equals(Globals.XXDM_SHGC)) {
			sql = "select zydm,zymc from bks_zydm order by bmdm";
			List zyList = dao.getList(sql, new String[] {}, new String[] {
					"zydm", "zymc" });
			request.setAttribute("zyList", zyList);
		}
		request.setAttribute("list", list);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ��λ��Ƹ��Ϣ�޸�
	public ActionForward updatezpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String pk = "rowid";
		String realTable = "jygl_zpxxb";
		String doType = DealString.toGBK(request.getParameter("doType"));

		if (pkValue != null) {
			pkValue.replace(" ", "+");
		}
		// ��ҵ�����б�
		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		String xxshinfo = dao.getOneRs(
				"select xxsh from jygl_zpxxb where rowid=?",
				new String[] { pkValue }, "xxsh");
		if (null != xxshinfo && "ͨ��".equalsIgnoreCase(xxshinfo)) {
			request.setAttribute("xxsh", "pass");
		}

		if ("update".equalsIgnoreCase(doType)
				&& !"ͨ��".equalsIgnoreCase(xxshinfo)) {

			String dwdz = DealString.toGBK(request.getParameter("dwdz"));
			String dwxz = DealString.toGBK(request.getParameter("dwxz"));
			String zpzw = DealString.toGBK(request.getParameter("zpzw"));
			String email = request.getParameter("email");
			String lxdh = request.getParameter("lxdh");
			String gzdd = DealString.toGBK(request.getParameter("gzdd")); // �����ص�
			String zprs = DealString.toGBK(request.getParameter("zprs"));
			String hyfl = DealString.toGBK(request.getParameter("hyfl"));
			String wyyq = DealString.toGBK(request.getParameter("wyyq"));
			String syxs = DealString.toGBK(request.getParameter("syxs"));
			String zzxs = DealString.toGBK(request.getParameter("zzxs"));
			String xlyq = DealString.toGBK(request.getParameter("xlyq"));
			String mssj = DealString.toGBK(request.getParameter("day")) + " "
					+ request.getParameter("hour") + ":"
					+ request.getParameter("min");
			String gwzz = DealString.toGBK(request.getParameter("gwzz"));
			String zwyq = DealString.toGBK(request.getParameter("zwyq"));
			String gsjj = DealString.toGBK(request.getParameter("gsjj"));
			String msxd = DealString.toGBK(request.getParameter("msxd"));
			String msdd = DealString.toGBK(request.getParameter("msdd"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String viewzydm = request.getParameter("viewzydm");
			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];

			boolean judge = false;

			judge = StandardOperation.update("jygl_zpxxb", new String[] {
					"dwdz", "dwxz", "zpzw", "email", "lxdh", "gzdd", "zprs",
					"hyfl", "wyyq", "syxs", "zzxs", "xlyq", "mssj", "gwzz",
					"zwyq", "gsjj", "fbsj", "msxd", "msdd", "xb", "viewzydm" },
					new String[] { dwdz, dwxz, zpzw, email, lxdh, gzdd, zprs,
							hyfl, wyyq, syxs, zzxs, xlyq, mssj, gwzz, zwyq,
							gsjj, fbsj, msxd, msdd, xb, viewzydm }, "rowid",
					pkValue, request);

			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}
		}

		// ��ѯ����
		sql = "select rowid rid, a.* from " + realTable + " a where " + pk
				+ "='" + pkValue + "'";
		String[] colList = dao
				.getColumnName("select rowid rid,a.* from jygl_zpxxb a where 1=2"); // ������������
		String[] zpxxinfo = dao.getOneRs(sql, new String[] {}, colList);
		if (zpxxinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), zpxxinfo[i]); // ����¼ѭ������map��
			}
			map.put("fbsj", dao.doForTime2(map.get("fbsj")));

			String mssj = map.get("mssj");
			if (mssj.length() < 13 && mssj.length() > 9) {
				mssj = mssj.substring(0, 10);
				map.put("mssj", mssj);
			} else if (mssj.length() < 9) {
				mssj = "";
				map.put("mssj", mssj);
			}
			map.put("pkValue", pkValue);
			String fbr = map.get("fbr");
			if (null == fbr) {
				request.setAttribute("who", "dw");
			} else {
				request.setAttribute("who", "admin");
			}
		}
		if (!Base.isNull(Base.xxdm) && Base.xxdm.equals(Globals.XXDM_SHGC)) {
			sql = "select zydm,zymc from bks_zydm order by bmdm";
			List zyList = dao.getList(sql, new String[] {}, new String[] {
					"zydm", "zymc" });
			request.setAttribute("zyList", zyList);
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// Ĭ�ϻظ��б�
	public ActionForward allmrhfinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		ArrayList<Object> list = new ArrayList<Object>();
		String sql = "";
		String rsNum = "0";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String[] colList = null;
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String dwmc = session.getAttribute("dwmc").toString();
		String yhm = session.getAttribute("yhm").toString();

		if (null != pkValue) {
			pkValue = pkValue.replaceAll(" ", "+");
		}

		if ("save".equals(doType)) {
			String bt = DealString.toGBK(request.getParameter("bt"));
			String hf = DealString.toGBK(request.getParameter("hf"));

			boolean judge = false;
			judge = StandardOperation.insert("jygl_dwmrhfb", new String[] {
					"yhm", "dwmc", "bt", "hf" }, new String[] { yhm, dwmc, bt,
					hf }, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}

		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_dwmrhfb", "rowid", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from jygl_dwmrhfb a where yhm = ?";

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] { yhm },
						"count")));

		// Ĭ�ϻظ�

		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.yhm,a.dwmc,a.bt from jygl_dwmrhfb a where a.yhm=?) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize());

		colList = new String[] { "rid", "yhm", "dwmc", "bt" };
		list.addAll(dao.rsToVator(sql, new String[] { yhm }, colList));

		sql = "select count(*) from jygl_dwmrhfb where yhm ='" + yhm + "'";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("list", list);
		request.setAttribute("rs", map);

		return mapping.findForward("success");
	}

	// ��λ�鿴Ĭ�ϻظ�����
	public ActionForward mrhfinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String[] grjlinfo = null;
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if (null != pkValue) {
			pkValue = pkValue.replaceAll(" ", "+");
		}

		sql = "select bt,hf from jygl_dwmrhfb where rowid=?";
		String[] colList = new String[] { "bt", "hf" }; // ������������
		grjlinfo = dao.getOneRs(sql, new String[] { pkValue }, colList);

		if (null != grjlinfo) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), grjlinfo[i]); // ����¼ѭ������map��
			}
		}

		request.setAttribute("rs", map);

		return mapping.findForward("success");
	}

	// ��λ�鿴ȫ�����˼���
	public ActionForward allgrjlinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String rsNum = "0";
		DAO dao = DAO.getInstance();
		ArrayList<Object> rs = new ArrayList<Object>();
		String sql = "";

		HashMap<String, String> map = new HashMap<String, String>();
		String doType2 = request.getParameter("doType2");
		String doType3 = request.getParameter("doType3");
		String act = request.getParameter("act");
		String xymc = DealString.toGBK(request.getParameter("xymc"));// ѧԺ����
		String zymc = DealString.toGBK(request.getParameter("zymc"));// רҵ����
		String xm = DealString.toGBK(request.getParameter("xm"));// ����
		String xb = DealString.toGBK(request.getParameter("xb"));// �Ա�

		HttpSession session = request.getSession();
		// String usertype = session.getAttribute("usertype").toString();
		String yhm = session.getAttribute("yhm").toString();

		sql = "select sftgsfyz from jygl_dwxxb where yhm=?";
		String dwinfo = dao.getOneRs(sql, new String[] { yhm }, "sftgsfyz");

		if ("��".equalsIgnoreCase(dwinfo)) {
			request.setAttribute("nopass", "nopass");
		}

		// ���ڷ���ʱ��Ĵ��� ���ڲ�ѯ����
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // ���ʱ��

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}

		// ѧԺ����
		List<HashMap<String, String>> xyList = Base.getXyList();
		request.setAttribute("xyList", xyList);

		sql = "select xydm from view_njxyzybj where xymc=?";
		String xydminfo = dao.getOneRs(sql, new String[] { xymc }, "xydm");

		// רҵ����
		HashMap<String, List<HashMap<String, String>>> zyRs = Base.getZyMap();
		String xydm = xydminfo == null ? "" : xydminfo;
		List<HashMap<String, String>> zyList = zyRs.get(xydm);
		request.setAttribute("zyList", zyList);

		// String a = "and xh in (select xh from view_xsjbxx where xymc =)";

		if ("change".equals(doType2)) {
			map.put("xymc", xymc);
			map.put("zymc", zymc);
			map.put("xb", xb);
		}

		if ("query".equals(act)) {
			map.put("xymc", xymc);
			map.put("zymc", zymc);
			map.put("xb", xb);
			map.put("xjsj", xjsj);
		}

		StringBuffer query = new StringBuffer();
		if (!("".equals(xymc))) {
			query
					.append(" and xsxh in (select xh from view_xsjbxx where xymc= '");
			query.append(xymc);
			query.append("')");
		}
		if (!("".equals(zymc))) {
			query.append(" and zymc = '");
			query.append(zymc);
			query.append("'");
		}
		if (!("".equals(xm))) {
			query.append(" and name like '%");
			query.append(xm);
			query.append("%'");
		}
		if (!("".equals(xb))) {
			query.append(" and xb = '");
			query.append(xb);
			query.append("'");
		}
		if (!("".equals(xjsj))) {
			query.append(" and (fbsj between '");
			query.append(beforesj);
			query.append("' and '");
			query.append(nowsj);
			query.append("') ");
		}

		String query1 = query.toString();
		if ("change".equals(doType2)) {
			query1 = "";
		}
		if ("qingkong".equals(doType3)) {
			map = new HashMap<String, String>();
			query1 = "";
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from jygl_grjlb a where 1=1 and hidden is null "
				+ query1;
		rsNum = dao.getOneRs(sql, new String[] {}, "count");
		dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));

		// ���˼���
		sql = "select * from (select a.*,rownum r from (select a.xsxh,a.name,a.xb,a.zymc,a.fbsj from jygl_grjlb a where 1=1 and hidden is null "
				+ query1
				+ " order by  fbsj desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + "  order by fbsj desc";

		String[] colList = new String[] { "xsxh", "name", "xb", "zymc", "fbsj" };
		String[] colListCN = dao.getColumnNameCN(colList, "jygl_grjlb");
		List topTr = dao.arrayToList(colList, colListCN);
		if ("��".equalsIgnoreCase(dwinfo)) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		}

		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);

		return mapping.findForward("success");
	}

	// �鿴��Ƹ��Ϣ
	public ActionForward jyzpinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String pk = "rowid";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_zpxxb";
		HttpSession session = request.getSession();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String act2 = request.getParameter("act2");
		String pkValue = request.getParameter("pkValue");
		String gsmc = DealString.toGBK(request.getParameter("gsmc"));
		String zpzw = DealString.toGBK(request.getParameter("zpzw"));
		String fbsj = DealString.toGBK(request.getParameter("fbsj"));
		String tdsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		HashMap<String, String> map = new HashMap<String, String>();

		if (null != pkValue) {
			pkValue = pkValue.replaceAll(" ", "+");
		}

		if (null != session.getAttribute("yhm")) {
			if (!("".equals(session.getAttribute("yhm").toString()))) {
				String yhm = session.getAttribute("yhm").toString();
				sql = "select * from jygl_xsscb where xh=? and pkvalue=? and savetype='���'";
				String[] seeinfo = dao.getOneRs(sql, new String[] { yhm,
						pkValue }, new String[] { "xh" });
				sql = "select gsmc,zpzw,fbsj from jygl_zpxxb where rowid=?";
				String[] dwinfo = dao.getOneRs(sql, new String[] { pkValue },
						new String[] { "gsmc", "zpzw", "fbsj" });
				if (null != dwinfo) {
					gsmc = dwinfo[0];
					zpzw = dwinfo[1];
					fbsj = dao.doForTime(dwinfo[2]);
				}

				if (null == seeinfo) {
					StandardOperation.insert("jygl_xsscb",
							new String[] { "pkvalue", "gsmc", "zpzw", "fbsj",
									"xh", "savetype" }, new String[] { pkValue,
									gsmc, zpzw, fbsj, yhm, "���" }, request);
				}
			}
		}

		if ("toujl".equals(act)) {
			String yhm = session.getAttribute("yhm").toString();
			sql = "select xh from jygl_dwjlb where xh=? and dwinfonum=?";
			String stuinfo = dao.getOneRs(sql, new String[] { yhm, pkValue },
					"xh");

			sql = "select xsxh from jygl_grjlb where xsxh=?";
			String stuinfo2 = dao.getOneRs(sql, new String[] { yhm }, "xsxh");

			sql = "select name from jygl_grjlb where xsxh=?";
			String stuinfo3 = dao.getOneRs(sql, new String[] { yhm }, "name");

			if ("".equals(stuinfo2)) {
				request.setAttribute("nogrjl", "nogrjl");
			} else {
				if (!("".equals(stuinfo))) {
					request.setAttribute("chongfu", "chongfu");
				} else {
					boolean judge = false;
					judge = StandardOperation.insert("jygl_dwjlb",
							new String[] { "dwinfonum", "xh", "xm", "gsmc",
									"zpzw", "tdsj" }, new String[] { pkValue,
									yhm, stuinfo3, gsmc, zpzw, tdsj }, request);
					if (judge) {
						StandardOperation.insert("jygl_xsscb", new String[] {
								"pkvalue", "gsmc", "zpzw", "fbsj", "xh",
								"savetype" }, new String[] { pkValue, gsmc,
								zpzw, fbsj, yhm, "Ͷ����" }, request);
						request.setAttribute("toujl", "ok");
					} else {
						request.setAttribute("toujl", "no");
					}
				}
			}
		}

		if ("save".equals(act2)) {
			String yhm = session.getAttribute("yhm").toString();
			sql = "select * from jygl_xsscb where pkvalue=? and xh=? and savetype='�ղ�'";
			String[] saveinfo = dao.getOneRs(sql,
					new String[] { pkValue, yhm }, new String[] { "pkvalue" });

			if (null == saveinfo) {
				boolean judge = false;
				judge = StandardOperation.insert("jygl_xsscb", new String[] {
						"pkvalue", "gsmc", "zpzw", "fbsj", "xh", "savetype" },
						new String[] { pkValue, gsmc, zpzw, fbsj, yhm, "�ղ�" },
						request);
				if (judge) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
			} else {
				request.setAttribute("save", "chongfu");
			}

		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_zpxxb where 1=2"); // ������������
			String[] zpxxinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zpxxinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(),
							Base.isNull(zpxxinfo[i]) ? "" : zpxxinfo[i]); // ����¼ѭ������map��
				}
				fbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjsec = null;
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjsec = sj.substring(12, 14);

				fbsj = sjyear + "��" + sjmon + "��" + sjday + "��" + " " + sjhour
						+ ":" + sjmin + ":" + sjsec;
				map.put("fbsj", fbsj);
				String mssj = map.get("mssj");
				if (mssj.length() < 13 && mssj.length() > 9) {
					mssj = mssj.substring(0, 10);
					map.put("mssj", mssj);
				} else if (mssj.length() < 9) {
					mssj = "";
					map.put("mssj", mssj);
				}
				map.put("pkValue", pkValue);
				String fbr = map.get("fbr");
				if ("jyweb".equals(fbr)) {
					request.setAttribute("fbrjs", "admin");
				} else {
					request.setAttribute("fbrjs", "dw");
				}
			}
			int readtimes = Integer.parseInt(map.get("readtimes"));
			readtimes = readtimes + 1;

			StandardOperation.update("jygl_zpxxb",
					new String[] { "readtimes" }, new String[] { String
							.valueOf(readtimes) }, pk, pkValue, request);
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ��λ�鿴Ͷ�ݵ�ѧ������
	public ActionForward dwviewallgrjlinfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String[] colList = null;
		String rsNum = "0";
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HttpSession session = request.getSession();
		String yhm = session.getAttribute("yhm").toString();
		String doType = request.getParameter("doType");
		String query = "";
		if ("hidden".equals(doType)) {
			query = " and hidden='no'";
		}
		// ��ѯ��˾����
		sql = "select dwmc from jygl_dwxxb where yhm=? " + query;
		String dwmc = dao.getOneRs(sql, new String[] { yhm }, "dwmc");

		if ("del".equalsIgnoreCase(doType)) {
			boolean judge = false;
			String rid = DealString.toGBK(request.getParameter("rid"));
			if (null != rid) {
				rid = rid.replace(" ", "+");
			}
			judge = StandardOperation.delete("jygl_dwjlb",
					new String[] { "rowid" }, new String[] { rid }, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from jygl_dwjlb a where gsmc=?";
		rsNum = dao.getOneRs(sql, new String[] { dwmc }, "count");
		dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));

		// ��ҳ
		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid, a.xh,a.gsmc,a.zpzw,a.xm,a.tdsj from jygl_dwjlb a  order by tdsj ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ " and gsmc=? "
				+ query
				+ " order by tdsj";
		colList = new String[] { "rid", "xh", "gsmc", "zpzw", "xm", "tdsj" };

		rs = dao.getArrayList2(sql, new String[] { dwmc }, colList);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}

	// ��λ�ظ�
	public ActionForward dwhf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String sql = "";
		String yhm = session.getAttribute("yhm").toString();
		String dwmc = session.getAttribute("dwmc").toString();
		String bt = DealString.toGBK(request.getParameter("bt"));
		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String hf = DealString.toGBK(request.getParameter("hf"));
		String hfsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // �ظ�ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];

		sql = "select bt,hf from jygl_dwmrhfb where yhm=?"; // Ĭ�ϻظ��б�
		List mrhfList = dao.getList(sql, new String[] { yhm }, new String[] {
				"bt", "hf" });
		request.setAttribute("mrhfList", mrhfList);

		if ("change".equals(act)) {
			sql = "select hf from jygl_dwmrhfb where bt =? and yhm=?";
			String hfinfo = dao.getOneRs(sql, new String[] { bt, yhm }, "hf");
			if (null != hfinfo) {
				map.put("bt", bt);
				map.put("hf", hfinfo);
			}
		}

		if ("save".equals(doType)) {

			boolean judge = false;
			judge = StandardOperation.insert("jygl_dwhfb", new String[] {
					"yhm", "dwmc", "xh", "xm", "hf", "hfsj" }, new String[] {
					yhm, dwmc, xh, xm, hf, hfsj }, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}

		}

		map.put("xm", xm);
		map.put("xh", xh);

		request.setAttribute("rs", map);

		return mapping.findForward("success");
	}

	// δ��ͨ�ľ�ҵָ��
	public ActionForward alljyzdinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String rsNum = "0";
		String sql = "";
		DAO dao = DAO.getInstance();
		String[] colList = null;
		ArrayList<Object> jyzd = new ArrayList<Object>();

		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_zxjsxxb", "num", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// ��ѯʦ��Ϣ
		sql = "select num,name,xb,zxszg from jygl_zxjsxxb order by num";
		colList = new String[] { "num", "name", "xb", "zxszg" };
		jyzd.addAll(dao.rsToVator(sql, new String[] {}, colList));
		if (jyzd == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(jyzd.size());
		}
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("jyzd", jyzd);
		return mapping.findForward("success");
	}

	// �鿴��������
	public ActionForward newsinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map1 = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		String tagId = request.getParameter("newsid");
		String rownum = request.getParameter("rownum");

		String[] tit = new String[] { "newsid", "newstitle", "newspart",
				"pubtime", "puber", "newscont" };
		String sql = "select newsid,newstitle,newspart,pubtime,puber,newscont from "
				+ "newscontent where newsid=?";
		String[] rs = dao.getOneRs(sql, new String[] { tagId }, tit);
		rs = (rs == null) ? new String[tit.length] : rs;
		for (int i = 0; i < tit.length; i++) {
			request.setAttribute(tit[i], isNull(rs[i]) ? " " : rs[i]);
		}
		CLOB clob = dao.getOneClob(sql, new String[] { tagId }, "newscont");
		request.setAttribute("newscont", clob.getSubString(1L, (int) clob
				.length()));

		String rownum1 = "";
		rownum1 = String.valueOf((Integer.parseInt(rownum) - 1));

		sql = "select b.* from (SELECT ROWNUM r,c.* FROM (select a.* from newscontent a WHERE NEWSPART = 'N13' order by newsid DESC) c) b  WHERE r=?";
		String[] beforeinfo = dao.getOneRs(sql, new String[] { rownum1 },
				new String[] { "newsid", "newstitle" });
		if (null != beforeinfo) {
			map1.put("beforesid", beforeinfo[0]);
			map1.put("beforetitle", beforeinfo[1]);
			map1.put("rownum", rownum1);
			request.setAttribute("before", "before");
		}

		String rownum2 = "";
		rownum2 = String.valueOf((Integer.parseInt(rownum) + 1));
		String[] afterinfo = dao.getOneRs(sql, new String[] { rownum2 },
				new String[] { "newsid", "newstitle" });
		if (null != afterinfo) {
			map2.put("aftersid", afterinfo[0]);
			map2.put("aftertitle", afterinfo[1]);
			map2.put("rownum", rownum2);
			request.setAttribute("after", "after");
		}

		request.setAttribute("rs1", map1);
		request.setAttribute("rs2", map2);
		return mapping.findForward("success");
	}

	// �޸���������
	public ActionForward updatenewsinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		HashMap<String, String> map = new HashMap<String, String>();
		String tagId = request.getParameter("pkValue");
		HttpSession session = request.getSession();
		String yhm = session.getAttribute("yhm").toString();

		if ("update".equals(doType)) {
			String newstitle = DealString.toGBK(request
					.getParameter("newstitle"));
			String content = DealString.toGBK(request.getParameter("content1"));
			boolean judge = false;

			judge = dao.updateNews(newstitle, "N13", content, yhm, tagId);
			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}

		}

		String[] tit = new String[] { "newsid", "newstitle", "newspart",
				"pubtime", "puber", "newscont" };
		String sql = "select newsid,newstitle,newspart,pubtime,puber,newscont from "
				+ "newscontent where newsid=?";
		String[] newsinfo = dao.getOneRs(sql, new String[] { tagId }, tit);
		newsinfo = (newsinfo == null) ? new String[tit.length] : newsinfo;
		for (int i = 0; i < tit.length; i++) {
			map.put(tit[i], isNull(newsinfo[i]) ? " " : newsinfo[i]);
			// request.setAttribute(tit[i], isNull(rs[i]) ? " " : rs[i]);
		}
		CLOB clob = dao.getOneClob(sql, new String[] { tagId }, "newscont");
		// request.setAttribute("newscont", clob.getSubString(1L, (int) clob
		// .length()));
		map.put("content1", clob.getSubString(1L, (int) clob.length()));

		request.setAttribute("rs", map);

		return mapping.findForward("success");
	}

	// �鿴ȫ����������
	public ActionForward allnewsinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String[] colList = null;
		String rsNum = "0";
		ArrayList<HashMap<String, String>> news = new ArrayList<HashMap<String, String>>();
		String doType = request.getParameter("doType");
		String tagId = request.getParameter("newsId");

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("newscontent", "newsid", tagId,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from newscontent a where NEWSPART = 'N13'";

		rsNum = dao.getOneRs(sql, new String[] {}, "count");
		dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));

		// ���¶�̬�����ţ�

		sql = "select * from (select a.*,rownum r from (select distinct a.NEWSID,a.NEWSTITLE,a.PUBTIME,a.PUBER from newscontent a where a.NEWSPART='N13' order by pubtime desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by pubtime desc";

		colList = new String[] { "newsid", "r", "newstitle", "pubtime", "puber" };
		news = dao.getArrayList(sql, new String[] {}, colList);
		request.setAttribute("rsNum", rsNum);

		request.setAttribute("news", news);
		return mapping.findForward("success");
	}

	// �鿴ȫ����Ƹ��Ϣ
	public ActionForward alljyzpinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rsNum = "0";
		String sql = "";
		String[] colList = null;
		ArrayList<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		CommanForm dataSearchForm = (CommanForm) form;

		String doType = request.getParameter("doType");
		String doType3 = request.getParameter("doType3");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		HttpSession session = request.getSession();
		// ��ҵ�����б�
		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		if (null != pkValue) {
			pkValue = pkValue.replaceAll(" ", "+");
		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_zpxxb", "rowid", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		String dwmc = DealString.toGBK(request.getParameter("dwmc"));
		String zpzw = DealString.toGBK(request.getParameter("zpzw"));
		String gzdd = DealString.toGBK(request.getParameter("gzdd"));
		String hyfl = DealString.toGBK(request.getParameter("iData"));
		String wyyq = DealString.toGBK(request.getParameter("wyyq"));
		String xlyq = DealString.toGBK(request.getParameter("xlyq"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String zzxs = DealString.toGBK(request.getParameter("zzxs"));
		String xxly = DealString.toGBK(request.getParameter("xxly"));

		// ���ڷ���ʱ��Ĵ��� ���ڲ�ѯ����
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // ���ʱ��

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}

		if ("query".equals(act)) {
			map.put("dwmc", dwmc);
			map.put("zpzw", zpzw);
			map.put("gzdd", gzdd);
			map.put("iData", hyfl);
			map.put("wyyq", wyyq);
			map.put("xlyq", xlyq);
			map.put("xb", xb);
			map.put("zzxs", zzxs);
			map.put("xjsj", xjsj);
			map.put("xxly", xxly);
		}

		StringBuffer query = new StringBuffer();
		if (!("".equals(dwmc))) {
			query.append(" and gsmc like '%");
			query.append(dwmc);
			query.append("%'");
		}
		if (!("".equals(zpzw))) {
			query.append(" and zpzw like '%");
			query.append(zpzw);
			query.append("%'");
		}
		if (!("".equals(gzdd))) {
			query.append(" and gzdd like '%");
			query.append(gzdd);
			query.append("%'");
		}
		if (!("".equals(hyfl))) {
			query.append(" and hyfl='");
			query.append(hyfl);
			query.append("'");
		}
		if (!("".equals(wyyq))) {
			query.append(" and wyyq like '%");
			query.append(wyyq);
			query.append("%'");
		}
		if (!("".equals(xlyq))) {
			query.append(" and xlyq='");
			query.append(xlyq);
			query.append("'");
		}
		if (!("".equals(xb))) {
			query.append(" and xb='");
			query.append(xb);
			query.append("'");
		}
		if (!("".equals(zzxs))) {
			query.append(" and zzxs='");
			query.append(zzxs);
			query.append("'");
		}
		if (!("".equals(xxly))) {
			query.append(" and xxly='");
			query.append(xxly);
			query.append("'");
		}
		if (!("".equals(xjsj))) {
			query.append(" and (fbsj between '");
			query.append(beforesj);
			query.append("' and '");
			query.append(nowsj);
			query.append("') ");
		}

		String query1 = query.toString();

		if ("qingkong".equals(doType3)) {
			map = new HashMap<String, String>();
			query1 = "";
		}
		// ��Ƹ��Ϣ

		if (!Base.isNull(Base.xxdm) && Base.xxdm.equals(Globals.XXDM_SHGC)
				&& session.getAttribute("usertype") != null
				&& "xs".equals(session.getAttribute("usertype").toString())) {
			sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.gsmc,a.zpzw,a.xxly,a.fbsj,a.readtimes from jygl_zpxxb a where "
					+ "(sxzy is null or instr(',' || sxzy || ',',',' || (select zydm from view_xsjbxx where xh = '"
					+ session.getAttribute("yhm").toString()
					+ "') || ',')>0) and xxsh='ͨ��' "
					+ query1
					+ " order by fbsj desc) a ) a where a.r>"
					+ dataSearchForm.getPages().getStart()
					+ " and a.r<="
					+ (dataSearchForm.getPages().getStart() + dataSearchForm
							.getPages().getPageSize()) + " order by fbsj desc";
		} else {
			sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.gsmc,a.zpzw,a.xxly,a.fbsj,a.readtimes from jygl_zpxxb a where xxsh='ͨ��'"
					+ query1
					+ " order by fbsj desc) a ) a where a.r>"
					+ dataSearchForm.getPages().getStart()
					+ " and a.r<="
					+ (dataSearchForm.getPages().getStart() + dataSearchForm
							.getPages().getPageSize()) + " order by fbsj desc";
		}

		colList = new String[] { "rid", "gsmc", "zpzw", "xxly", "fbsj",
				"readtimes" };
		rs.addAll(dao.rsToVator2(sql, new String[] {}, colList));

		if (!Base.isNull(Base.xxdm) && Base.xxdm.equals(Globals.XXDM_SHGC)
				&& session.getAttribute("usertype") != null
				&& "xs".equals(session.getAttribute("usertype").toString())) {
			sql = "select count(*) from jygl_zpxxb where (sxzy is null or instr(',' || sxzy || ',',',' || (select zydm from view_xsjbxx where xh = '"
					+ session.getAttribute("yhm").toString()
					+ "') || ',')>0) and xxsh='ͨ��'" + query1;
		} else {
			sql = "select count(*) from jygl_zpxxb where xxsh='ͨ��'" + query1;
		}
		int rsNuminfo = dao.getOneRsint(sql);
		dataSearchForm.getPages().setMaxRecord(rsNuminfo);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}

	// �鿴ȫ����ְ��Ϣ
	public ActionForward allqzxxinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String[] colList = null;
		String rsNum = "0";
		ArrayList<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String doType3 = request.getParameter("doType3");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");

		String xymc = DealString.toGBK(request.getParameter("xymc"));
		String zymc = DealString.toGBK(request.getParameter("zymc"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String hyfl = DealString.toGBK(request.getParameter("iData"));
		String qzyx = DealString.toGBK(request.getParameter("qzyx"));

		// ���ڷ���ʱ��Ĵ��� ���ڲ�ѯ����
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // ���ʱ��

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}

		// ��ҵ�����б�
		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		// ѧԺ����
		List<HashMap<String, String>> xyList = Base.getXyList();
		request.setAttribute("xyList", xyList);

		sql = "select xydm from view_njxyzybj where xymc=?";
		String xydminfo = dao.getOneRs(sql, new String[] { xymc }, "xydm");

		// רҵ����
		HashMap<String, List<HashMap<String, String>>> zyRs = Base.getZyMap();
		String xydm = xydminfo == null ? "" : xydminfo;
		List<HashMap<String, String>> zyList = zyRs.get(xydm);
		request.setAttribute("zyList", zyList);

		if ("change".equals(doType2)) {
			map.put("xymc", xymc);
			map.put("zymc", zymc);
			map.put("xb", xb);
			map.put("iData", hyfl);
			map.put("qzyx", qzyx);
			map.put("xjsj", xjsj);

		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_qzxxb", "xh", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		if ("query".equals(act)) {
			map.put("xymc", xymc);
			map.put("zymc", zymc);
			map.put("xb", xb);
			map.put("iData", hyfl);
			map.put("qzyx", qzyx);
			map.put("xjsj", xjsj);
		}

		StringBuffer query = new StringBuffer();
		if (!("".equals(xymc))) {
			query.append(" and xymc = '");
			query.append(xymc);
			query.append("'");
		}
		if (!("".equals(zymc))) {
			query.append(" and zymc = '");
			query.append(zymc);
			query.append("'");
		}
		if (!("".equals(xb))) {
			query.append(" and xb = '");
			query.append(xb);
			query.append("'");
		}
		if (!("".equals(hyfl))) {
			query.append(" and qzhy='");
			query.append(hyfl);
			query.append("'");
		}
		if (!("".equals(qzyx))) {
			query.append(" and qzyx like '%");
			query.append(qzyx);
			query.append("%'");
		}
		if (!("".equals(xjsj))) {
			query.append(" and (fbsj between '");
			query.append(beforesj);
			query.append("' and '");
			query.append(nowsj);
			query.append("') ");
		}

		String query1 = query.toString();
		if ("change".equals(doType2)) {
			query1 = "";
		}
		if ("qingkong".equals(doType3)) {
			map = new HashMap<String, String>();
			query1 = "";
		}
		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from jygl_qzxxb a where 1=1 " + query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ��ְ��Ϣ
		sql = "select * from (select a.*,rownum r from (select distinct a.xh,a.xymc,a.zymc,a.xb,a.qzyx,a.fbsj from jygl_qzxxb a where 1=1 "
				+ query1
				+ " order by fbsj desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by fbsj desc";

		colList = new String[] { "xh", "xymc", "zymc", "xb", "qzyx", "fbsj" };
		rs.addAll(dao.rsToVator2(sql, new String[] {}, colList));

		sql = "select count(*) from jygl_qzxxb where 1=1" + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}

	// �鿴��ְ��Ϣ
	public ActionForward qzxxinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		// �ж��û����ͣ�������ϵ�绰
		HttpSession session = request.getSession();
		if (null != session.getAttribute("usertype")) {
			String usertype = session.getAttribute("usertype").toString();
			if (usertype.equalsIgnoreCase("admin")
					|| usertype.equalsIgnoreCase("dw")) {
				request.setAttribute("usertype", "dw");
			} else {
				request.setAttribute("usertype", "xs");
			}
		} else {
			request.setAttribute("usertype", "xs");
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from jygl_qzxxb where xh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_qzxxb where 1=2"); // ������������
			String[] qzxxinfo = dao.getOneRs(sql, new String[] { pkValue },
					colList);
			if (qzxxinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), qzxxinfo[i]); // ����¼ѭ������map��
				}
				String fbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjsec = null;
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjsec = sj.substring(12, 14);

				fbsj = sjyear + "��" + sjmon + "��" + sjday + "��" + " " + sjhour
						+ ":" + sjmin + ":" + sjsec;
				map.put("fbsj", fbsj);
			}
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// �鿴ȫ�����߷�����Ϣ
	public ActionForward allzcfginfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rsNum = "0";
		String sql = "";
		String[] colList = null;
		ArrayList<Object> zcfg = new ArrayList<Object>();
		String doType = request.getParameter("doType");
		String rowid = request.getParameter("rowid");
		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_zcwjb", "rowid", rowid,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from JYGL_ZCWJB a where wjlx in ('���Ҽ��ļ�','�м��ļ�','У���ļ�')";
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ���߷���
		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.wjbt,a.wjlx,a.fbr,a.fbsj from JYGL_ZCWJB a where a.wjlx in ('���Ҽ��ļ�','�м��ļ�','У���ļ�') order by wjlx ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by wjlx";
		colList = new String[] { "rid", "wjbt", "wjlx", "fbr", "fbsj" };
		zcfg.addAll(dao.rsToVator(sql, new String[] {}, colList));

		sql = "select count(*) from JYGL_ZCWJB where wjlx in ('���Ҽ��ļ�','�м��ļ�','У���ļ�')";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);

		request.setAttribute("zcfg", zcfg);
		return mapping.findForward("success");
	}

	// �鿴ȫ��ר����Ƹ��Ϣ
	public ActionForward allzczpinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rsNum = "0";
		String sql = "";
		String[] colList = null;
		ArrayList<Object> zczp = new ArrayList<Object>();

		String doType = request.getParameter("doType");
		String rowid = request.getParameter("rowid");
		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_zcwjb", "rowid", rowid,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from JYGL_ZCWJB a where wjlx = 'У԰ר����Ƹ��'";
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ר����Ƹ��Ϣ
		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.wjbt,a.fbr,a.fbsj from JYGL_ZCWJB a where a.wjlx = 'У԰ר����Ƹ��' order by a.fbsj desc ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by a.fbsj desc";
		colList = new String[] { "rid", "wjbt", "fbr", "fbsj" };
		zczp.addAll(dao.rsToVator(sql, new String[] {}, colList));

		sql = "select count(*) from JYGL_ZCWJB where wjlx = 'У԰ר����Ƹ��'";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);

		request.setAttribute("zczp", zczp);
		return mapping.findForward("success");
	}

	// �鿴ȫ����������Ϣ
	public ActionForward allgglinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rsNum = "0";
		String sql = "";
		String[] colList = null;
		ArrayList<Object> ggl = new ArrayList<Object>();
		String doType = request.getParameter("doType");
		String rowid = request.getParameter("rowid");
		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_zcwjb", "rowid", rowid,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from JYGL_ZCWJB a where wjlx = '������'";
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ������
		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.wjbt,a.fbr,a.fbsj from JYGL_ZCWJB a where a.wjlx = '������' order by a.fbsj desc ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by a.fbsj desc";
		colList = new String[] { "rid", "wjbt", "fbr", "fbsj" };
		ggl.addAll(dao.rsToVator(sql, new String[] {}, colList));

		sql = "select count(*) from JYGL_ZCWJB where wjlx = '������'";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);

		request.setAttribute("ggl", ggl);
		return mapping.findForward("success");
	}

	// �鿴ȫ����Դ������Ϣ
	public ActionForward allsyjsinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rsNum = "0";
		String sql = "";
		String[] colList = null;
		ArrayList<Object> syjs = new ArrayList<Object>();

		String doType = request.getParameter("doType");
		String rowid = request.getParameter("rowid");
		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_zcwjb", "rowid", rowid,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from JYGL_ZCWJB a where wjlx = '��Դ����'";
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ��Դ������Ϣ
		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.wjbt,a.fbr,a.fbsj from JYGL_ZCWJB a where a.wjlx = '��Դ����' order by a.fbsj desc ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by a.fbsj desc";
		colList = new String[] { "rid", "wjbt", "fbr", "fbsj" };
		syjs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		sql = "select count(*) from JYGL_ZCWJB where wjlx = '��Դ����'";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);

		request.setAttribute("syjs", syjs);
		return mapping.findForward("success");
	}

	// �鿴ר����Ƹ,������,��Դ����,���߷�������
	public ActionForward qitainfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rowid = request.getParameter("rowid");
		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}

		String[] tit = new String[] { "rowid", "wjbt", "fbr", "fbsj", "wjnr",
				"wjlx" };
		String sql = "select rowid,wjbt,fbr,fbsj,wjnr,wjlx from "
				+ "JYGL_ZCWJB where rowid=?";
		String[] rs = dao.getOneRs(sql, new String[] { rowid }, tit);
		rs = (rs == null) ? new String[tit.length] : rs;
		for (int i = 0; i < tit.length; i++) {
			if (i == 3) {
				String time = dao.doForTime(rs[3]);
				request.setAttribute(tit[3], isNull(rs[3]) ? " " : time);
			}
			if (i != 3) {
				request.setAttribute(tit[i], isNull(rs[i]) ? " " : rs[i]);
			}
		}
		CLOB clob = dao.getOneClob(sql, new String[] { rowid }, "wjnr");
		String wjnr = clob.getSubString(1L, (int) clob.length());
		request.setAttribute("wjnr", wjnr);
		return mapping.findForward("success");
	}

	// �ļ������б�
	public ActionForward allwjxzsinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rsNum = "0";
		String sql = "";

		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("wjsc_scwjxxb", "wjh", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from wjsc_scwjxxb a where wjffbm='ѧ����'";
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// �ļ�����
		sql = "select * from (select a.*,rownum r from (select distinct a.wjh,a.wjm,a.wjffbm,a.ffr,a.wjffsj from wjsc_scwjxxb a where a.wjffbm='ѧ����' order by a.wjffsj desc ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by a.wjffsj desc";
		List rs = dao.getList(sql, new String[] {}, new String[] { "wjh",
				"wjm", "wjffbm", "ffr", "wjffsj" });

		sql = "select count(*) from wjsc_scwjxxb where wjffbm='ѧ����'";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);

		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}

	// �鿴�ļ���ϸ��Ϣ
	public ActionForward wjxzsinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String wjh = DealString.toGBK(request.getParameter("wjh"));
		String sql = "select wjh,wjffbm,wjffsj,wjscsm,ffr,wjsclj,wjm,substr(wjsclj,23,length(wjsclj)-22) filename from wjsc_scwjxxb where wjh = ?";
		map = dao.getMap(sql, new String[] { wjh }, new String[] { "wjh",
				"wjffbm", "wjffsj", "wjscsm", "ffr", "wjsclj", "wjm",
				"filename" });

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// �ļ�����
	public ActionForward downwj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		byte b[] = new byte[500];
		String dir = DealString.toGBK(request.getParameter("wjsclj"));
		String filename = dir.substring(22, dir.length());
		File fileload = new File(dir);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(filename));
		InputStream in = new FileInputStream(fileload);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
	}

	// �ļ��ϴ�
	public ActionForward updatewj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm wjscform = (CommanForm) form;
		HttpSession session = request.getSession();
		String ffr = session.getAttribute("yhm").toString(); // �û���
		String wjh = request.getParameter("wjh"); // �ļ���
		String wjm = request.getParameter("wjm"); // �ļ���
		String wjscsm = request.getParameter("wjscsm");
		String wjffbm = request.getParameter("wjffbm"); // �ļ���������
		String wjffsj = WjscDataAccessAction.GetSysTime(); // �ļ�����ʱ��
		String filePath = ""; // �ļ�·��
		String message = "";
		String dir = "/upload"; // ·����Ŀ¼
		String doType = request.getParameter("doType");
		String sql = "";

		if ("save".equals(doType)) {

			File f = new File(dir);
			if (!f.exists()) {
				f.mkdir();
			}
			Timestamp date = new Timestamp(System.currentTimeMillis());
			String dateStr = date.toString().substring(0, 19);
			dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
					.replaceAll(":", "");
			FormFile file = wjscform.getUploadFile();
			if (file == null) {
				message = "�ļ��ϴ�ʧ��!";
				request.setAttribute("message", message);
				request.setAttribute("save", "no");
				return mapping.findForward("success");
			} else {
				int size = file.getFileSize();
				String fName = dateStr + file.getFileName();
				InputStream in = file.getInputStream();
				filePath = dir + "/" + fName;
				OutputStream out = new FileOutputStream(filePath);
				int bytesRead = 0;
				byte[] buffer = new byte[size];
				while ((bytesRead = in.read(buffer, 0, size)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				out.close();
				in.close();
			}
		}

		if ("save".equals(doType)) {
			String[] setpara = { wjh, wjm, wjffbm, wjscsm, filePath, wjffsj,
					ffr };
			boolean judge = false;
			judge = StandardOperation.insert("wjsc_scwjxxb",
					new String[] { "wjh", "wjm", "wjffbm", "wjscsm", "wjsclj",
							"wjffsj", "ffr" }, setpara, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "mo");
			}
		}

		String rsNum = "0";
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("wjsc_scwjxxb", "wjh", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		sql = "select count(*) count from wjsc_scwjxxb a where wjffbm='ѧ����' ";
		wjscform.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// �ļ��ϴ�
		sql = "select * from (select a.*,rownum r from (select distinct a.wjh,a.wjm,a.wjffbm,a.ffr,a.wjffsj from wjsc_scwjxxb a where wjffbm='ѧ����'  order by wjffsj desc) a ) a where a.r>"
				+ wjscform.getPages().getStart()
				+ " and a.r<="
				+ (wjscform.getPages().getStart() + wjscform.getPages()
						.getPageSize()) + " order by wjffsj desc";
		String[] colList = new String[] { "wjh", "wjm", "wjffbm", "ffr",
				"wjffsj" };
		List rs = dao.getList(sql, new String[] {}, colList);

		sql = "select count(*) from wjsc_scwjxxb";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");

	}

	// ȫ��ͼƬ��Ϣ�鿴
	public ActionForward alltpxxinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String rsNum = "0";
		String pkValue = request.getParameter("pkValue");
		String sql = "";

		if ("del".equals(doType)) {

			boolean del = false;
			String picpath1 = dao.getOneRs(
					"select picpath1 from jygl_tpxxb where rowid=?",
					new String[] { pkValue }, "picpath1");

			picpath1 = servlet.getServletContext().getRealPath(
					picpath1.substring(picpath1.indexOf("/pictures")));
			File file = new File(picpath1);

			if ((file.exists())) {
				try {
					del = file.delete();
					if (del) {
						System.out.println("��ҵ��ͼƬ" + picpath1 + "ɾ���ɹ�!");
					} else {
						System.out.println("��ҵ��ͼƬ" + picpath1 + "ɾ��ʧ��!");
					}
				} catch (SecurityException e) {
					System.out.println("����ɾ�����ļ�!");
				}
			}

			boolean judge = false;
			judge = StandardOperation.delete("jygl_tpxxb", "rowid", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from jygl_tpxxb a ";
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.bt,a.nr,a.picpath1,a.fbsj,a.fbr from jygl_tpxxb a  order by fbsj desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by fbsj desc";
		List rs = dao.getList(sql, new String[] {}, new String[] { "rid", "bt",
				"nr", "picpath1", "fbsj", "fbr" });

		sql = "select count(*) from jygl_tpxxb";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("name", "tpxx");
		return mapping.findForward("success");
	}

	// �ϴ�ͼƬ
	public ActionForward updatepic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String dir = servlet.getServletContext().getRealPath("/pictures");
		CommanForm wjscform = (CommanForm) form;
		HttpSession session = request.getSession();
		String fbr = session.getAttribute("yhm").toString(); // ������
		String bt = request.getParameter("bt"); // ����
		String nr = request.getParameter("nr");// ����
		String fbsj = WjscDataAccessAction.GetSysTime(); // ����ʱ��
		String filePath = ""; // ͼƬ·��
		String realPath = "";
		String message = ""; // ��Ϣ��ʾ
		// String dir = "/uploadpic"; //·����Ŀ¼
		String doType = request.getParameter("doType");
		String sql = "";
		if ("save".equals(doType)) {

			File f = new File(dir);
			if (!f.exists()) {
				f.mkdir();
			}
			Timestamp date = new Timestamp(System.currentTimeMillis());
			String dateStr = date.toString().substring(0, 19);
			dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
					.replaceAll(":", "");
			FormFile file = wjscform.getUploadFile();
			if (file == null) {
				message = "ͼƬ�ϴ�ʧ��!";
				request.setAttribute("message", message);
				request.setAttribute("save", "no");
				return mapping.findForward("success");
			} else {
				int size = file.getFileSize();
				String fName = dateStr + file.getFileName();
				InputStream in = file.getInputStream();
				filePath = dir + "/" + fName;
				realPath = "/xgxt/pictures/" + fName;
				OutputStream out = new FileOutputStream(filePath);
				int bytesRead = 0;
				byte[] buffer = new byte[size];
				while ((bytesRead = in.read(buffer, 0, size)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				out.close();
				in.close();
			}
		}

		if ("save".equals(doType)) {
			String[] setpara = { bt, nr, realPath, fbsj, fbr };
			boolean judge = false;
			judge = StandardOperation.insert("jygl_tpxxb", new String[] { "bt",
					"nr", "picpath1", "fbsj", "fbr" }, setpara, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "mo");
			}
		}

		String rsNum = "0";
		String pkValue = request.getParameter("pkValue");

		if (null != pkValue) {
			pkValue = pkValue.replaceAll(" ", "+");
		}

		if ("del".equals(doType)) {

			boolean del = false;
			String picpath1 = dao.getOneRs(
					"select picpath1 from jygl_tpxxb where rowid=?",
					new String[] { pkValue }, "picpath1");

			picpath1 = servlet.getServletContext().getRealPath(
					picpath1.substring(picpath1.indexOf("/pictures")));
			File file = new File(picpath1);

			if ((file.exists())) {
				try {
					del = file.delete();
					if (del) {
						System.out.println("��ҵ��ͼƬ" + picpath1 + "ɾ���ɹ�!");
					} else {
						System.out.println("��ҵ��ͼƬ" + picpath1 + "ɾ��ʧ��!");
					}
				} catch (SecurityException e) {
					System.out.println("����ɾ�����ļ�!");
				}
			}

			boolean judge = false;
			judge = StandardOperation.delete("jygl_tpxxb", "rowid", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		sql = "select count(*) count from jygl_tpxxb a where 1=1 ";
		wjscform.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ͼƬ��Ϣ
		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.bt,a.nr,a.picpath1,a.fbsj,a.fbr from jygl_tpxxb a where 1=1  order by fbsj desc) a ) a where a.r>"
				+ wjscform.getPages().getStart()
				+ " and a.r<="
				+ (wjscform.getPages().getStart() + wjscform.getPages()
						.getPageSize()) + " order by fbsj desc";
		String[] colList = new String[] { "rid", "bt", "nr", "picpath1",
				"fbsj", "fbr" };
		List rs = dao.getList(sql, new String[] {}, colList);

		sql = "select count(*) from jygl_tpxxb";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");

	}

	// �鿴ͼƬ��Ϣ
	public ActionForward tpxxinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		if (pkValue != null) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from jygl_tpxxb where rowid=?";
			String[] colList = dao
					.getColumnName("select * from jygl_tpxxb where 1=2"); // ������������
			String[] tpxxinfo = dao.getOneRs(sql, new String[] { pkValue },
					colList);
			if (tpxxinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), tpxxinfo[i]); // ����¼ѭ������map��
				}
			}
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");

	}

	// ���԰�
	public ActionForward lybinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String[] colList = null;
		ArrayList<Object> rs = new ArrayList<Object>();
		ArrayList<String[]> rs1 = new ArrayList<String[]>();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String ip = request.getRemoteAddr();
		String go = "";
		if (null != pkValue) {
			pkValue = pkValue.replaceAll(" ", "+");
		}
		// ����ɾ��
		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_lyb", "rowid", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}
		// ȫ�����
		if ("delall".equals(doType)) {
			boolean judge = false;
			sql = "delete from jygl_lyb";
			judge = dao.runUpdateTab(sql);
			if (judge) {
				request.setAttribute("delall", "ok");
			} else {
				request.setAttribute("delall", "no");
			}
		}

		// �����жϼ��û���֤
		if ("save".equals(doType)) {
			HttpSession session = request.getSession();
			String yzm = session.getAttribute("yzm").toString().toLowerCase();
			String yzm2 = request.getParameter("yzm").toLowerCase();
			sql = "select fip from jygl_lyb_fip where ip=?";
			String fip = dao.getOneRs(sql, new String[] { ip }, "fip");
			if ("fip".equals(fip)) {
				request.setAttribute("add", "fip");
				go = "no";
			}
			if (yzm2 == null || !(yzm2.equals(yzm)) || "".equals(yzm2)) {
				request.setAttribute("add", "no");
				session.setAttribute("yzm", "");
				// ���԰�����

				CommanForm dataSearchForm = (CommanForm) form;
				sql = "select count(*) count from view_jygl_lyb ";
				dataSearchForm.getPages().setMaxRecord(
						Integer.parseInt(dao.getOneRs(sql, new String[] {},
								"count")));

				sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.id,a.tplj,a.yhm,a.fbsj,a.ly,a.ip from jygl_lyb a  order by a.fbsj ) a ) a where a.r>"
						+ dataSearchForm.getPages().getStart()
						+ " and a.r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize())
						+ " order by a.fbsj ";
				colList = new String[] { "rid", "id", "tplj", "yhm", "fbsj",
						"ly", "ip" };
				rs1 = dao.rsToVator(sql, new String[] {}, colList);
				rs.addAll(rs1);
				request.setAttribute("rs", rs);
				return mapping.findForward("success");
			}
			session.setAttribute("yzm", "");
		}

		// �����ύ
		if ("save".equals(doType) && !("no".equals(go))) {
			String yhm = DealString.toGBK(request.getParameter("yhm"));
			if ("".equals(yhm)) {
				yhm = "�����û�";
			}

			String ly = DealString.toGBK(request.getParameter("content1"));
			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];
			boolean judge = false;
			judge = dao.addLy(ip, yhm, ly, fbsj);

			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
		}

		// ��IP
		if ("fip".equals(doType)) {
			sql = "select ip from jygl_lyb where rowid=?";
			ip = dao.getOneRs(sql, new String[] { pkValue }, "ip");
			if (null != ip && !("".equals(ip))) {
				boolean judge = false;
				judge = StandardOperation.insert("jygl_lyb_fip",
						new String[] { "ip" }, new String[] { ip }, request);
				if (judge) {
					request.setAttribute("fip", "ok");
				} else {
					request.setAttribute("fip", "no");
				}
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from view_jygl_lyb ";
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ���԰�����
		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.id,a.tplj,a.yhm,a.fbsj,a.ly,a.ip from jygl_lyb a  order by a.fbsj) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by a.fbsj";
		colList = new String[] { "rid", "id", "tplj", "yhm", "fbsj", "ly", "ip" };
		rs1 = dao.rsToVator(sql, new String[] {}, colList);
		rs.addAll(rs1);
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}

	// ����Ա�һ�����
	public ActionForward findpassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String yhm = request.getParameter("yhm");
		String usertype = request.getParameter("usertype");
		HashMap<String, String> map = new HashMap<String, String>();
		String mm = "";
		Encrypt ept = new Encrypt(); // ���ܽ���
		String sql = "";
		String doType = request.getParameter("doType");
		if ("find".equals(doType)) {
			if ("admin".equals(usertype)) {
				sql = "select kl from yhb where yhm=?";
				mm = dao.getOneRs(sql, new String[] { yhm }, "kl");
			} else if ("xs".equals(usertype)) {

				sql = "select mm from view_xsjbxx where xh=?";
				mm = dao.getOneRs(sql, new String[] { yhm }, "mm");
			} else if ("dw".equals(usertype)) {
				sql = "select mm from jygl_dwxxb where yhm=?";
				mm = dao.getOneRs(sql, new String[] { yhm }, "mm");
			} else {
				request.setAttribute("nothisyhm", "nothisyhm");

			}
			String xnOrGdFlag = dao.getOneRs("select jwflag from xtszb",
					new String[] {}, "jwflag");// �����ѧ��ľͻ��1,������ֵ

			if ((xnOrGdFlag != null && xnOrGdFlag.trim().equals("1"))
					&& usertype.equalsIgnoreCase("xs")) {
				// mm = mm;
			} else {
				mm = ept.decrypt(mm);
			}

			if (!("".equals(mm))) {
				request.setAttribute("you", "you");
			} else {
				request.setAttribute("mei", "mei");
			}
			map.put("yhm", yhm);
			map.put("usertype", usertype);
			map.put("mm", mm);
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ����Ա��Ƹ����б�
	public ActionForward zpshenheinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String rsNum = "0";
		String sql = "";
		ArrayList<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();

		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");

		StringBuffer query = new StringBuffer();
		String querry = "";
		query.append(" where 1=1");

		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String xxly = request.getParameter("xxly") == null ? "" : request
				.getParameter("xxly");

		if (!"".equalsIgnoreCase(xxsh) && null != xxsh) {
			query.append(" and xxsh='");
			query.append(xxsh);
			query.append("' ");
			map.put("xxsh", xxsh);
		}
		if (!Base.isNull(xxly)) {
			if (xxly.equals("xx")) {
				query.append(" and (xxly='ѧУ' or xxly is null) ");
			} else {
				query.append(" and xxly='��λ' ");
			}
			map.put("xxly", xxly);
		}
		querry = query.toString();
		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_zpxxb", "rowid", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from jygl_zpxxb a " + querry;
		rsNum = dao.getOneRs(sql, new String[] {}, "count");
		dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));

		// ��Ƹ��Ϣ
		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.gsmc,a.zpzw,a.fbsj,a.xxsh from jygl_zpxxb a "
				+ querry
				+ "  order by fbsj desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by fbsj desc";
		String[] colList = new String[] { "rid", "gsmc", "zpzw", "fbsj", "xxsh" };
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}

	// ����Ա��Ƹ���
	public ActionForward zpshenhe(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String pk = "rowid";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_zpxxb";
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		HashMap<String, String> map = new HashMap<String, String>();

		if (null != pkValue) {
			pkValue = pkValue.replaceAll(" ", "+");
		}

		if ("shenhe".equals(act)) {
			boolean judge = false;
			judge = StandardOperation.update(realTable,
					new String[] { "xxsh" }, new String[] { xxsh }, "rowid",
					pkValue, request);

			if (judge) {
				request.setAttribute("shenhe", "ok");
			} else {
				request.setAttribute("shenhe", "no");
			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_zpxxb where 1=2"); // ������������
			String[] zpxxinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zpxxinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zpxxinfo[i]); // ����¼ѭ������map��
				}
				String fbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjsec = null;
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjsec = sj.substring(12, 14);

				fbsj = sjyear + "��" + sjmon + "��" + sjday + "��" + " " + sjhour
						+ ":" + sjmin + ":" + sjsec;
				map.put("fbsj", fbsj);
				String mssj = map.get("mssj");
				if (map.get("mssj") != null
						&& !map.get("mssj").trim().equals("")) {
					if (mssj.length() < 13 && mssj.length() > 9) {
						mssj = mssj.substring(0, 10);
						map.put("mssj", mssj);
					} else if (mssj.length() < 9) {
						mssj = "";
						map.put("mssj", mssj);
					}
				}
			}
			if (null != pkValue) {
				map.put("pkValue", pkValue);
			}
		}

		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ���˼����Ǽ�
	public ActionForward grjldj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String tableName = "jygl_grjlb";
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String go = "";
		String yhm = session.getAttribute("yhm").toString();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";

		// ɾ��
		if ("del".equals(act)) {
			boolean judge = false;
			judge = StandardOperation.delete(tableName, "xsxh", yhm, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		sql = "select * from jygl_grjlb where xsxh=?";
		String[] colList = dao
				.getColumnName("select * from jygl_grjlb where 1=2"); // ������������
		String[] grjlinfo = dao.getOneRs(sql, new String[] { yhm }, colList);

		if (null != grjlinfo) {
			go = "update";
		}

		if (!("update".equals(go))) {
			sql = "select xm,xb,sfzh,zymc,lxdh,xz,nj from view_xsjbxx where xh=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { yhm },
					new String[] { "xm", "xb", "sfzh", "zymc", "lxdh", "xz",
							"nj" });
			String xz = "";
			if (null != stuinfo) {
				map.put("xsxh", yhm);
				map.put("name", stuinfo[0]);
				map.put("xb", stuinfo[1]);
				map.put("id", stuinfo[2]);
				map.put("zymc", stuinfo[3]);
				map.put("lxdh", stuinfo[4]);
				xz = stuinfo[5];
				map.put("rxnf", stuinfo[6]);
			}

			if (null != map.get("id")) {
				// �����֤��ĳ�������ȡ����
				String sfzh = map.get("id");
				String csny = "";
				StringBuffer sfzh1 = new StringBuffer();

				if (sfzh.length() == 18 && !("".equals(sfzh))) {
					sfzh1.append(sfzh.substring(6, 10));
					sfzh1.append("��");
					sfzh1.append(sfzh.substring(10, 12));
					sfzh1.append("��");
					sfzh1.append(sfzh.substring(12, 14));
					sfzh1.append("��");
					csny = sfzh1.toString();
					map.put("csny", csny);
				} else if (sfzh.length() == 15 && !("".equals(sfzh))) {
					sfzh1.append(sfzh.substring(6, 8));
					sfzh1.append("��");
					sfzh1.append(sfzh.substring(8, 10));
					sfzh1.append("��");
					sfzh1.append(sfzh.substring(10, 12));
					sfzh1.append("��");
					csny = sfzh1.toString();
					map.put("csny", csny);
				}
			}
			if (!("".equals(xz))) {
				if ("2".equals(xz) || "3".equals(xz)) {
					// ר��
					map.put("xl", "ר��");
				} else if ("4".equals(xz) || "5".equals(xz)) {
					// ����
					map.put("xl", "����");
				} else if ("7".equals(xz)) {
					// ˶ʿ
					map.put("xl", "˶ʿ");
				}
			}

		}

		String id = request.getParameter("id"); // ���֤����1
		String idsee = request.getParameter("idsee"); // ���֤�Ƿ���2
		String rxnf = DealString.toGBK(request.getParameter("rxnf"));// ��ѧ���;
		String xsxh = request.getParameter("xsxh"); // ѧ��3
		String hidden = request.getParameter("hidden"); // ֻ��Ͷ�ݵ�λ����
		String name = DealString.toGBK(request.getParameter("name")); // ����4
		String xb = DealString.toGBK(request.getParameter("xb")); // �Ա�5
		String csny = DealString.toGBK(request.getParameter("csny")); // ��������6
		String mz = DealString.toGBK(request.getParameter("mz")); // ����7
		String xl = DealString.toGBK(request.getParameter("xl")); // ѧ��8
		String zzmm = DealString.toGBK(request.getParameter("zzmm")); // ������ò9
		String zymc = DealString.toGBK(request.getParameter("zymc")); // רҵ����10
		String fxzymc = DealString.toGBK(request.getParameter("fxzymc")); // ����רҵ����11
		String sydq = DealString.toGBK(request.getParameter("sydq")); // ��Դ����12
		String lxdz = DealString.toGBK(request.getParameter("lxdz")); // ��ϵ��ַ13
		String lxdh = request.getParameter("lxdh"); // ��ϵ�绰14
		String yzbm = request.getParameter("yzbm"); // ��������15
		String email = request.getParameter("email"); // ��������16
		String hjqk = DealString.toGBK(request.getParameter("hjqk")); // �����17
		String xxqk = DealString.toGBK(request.getParameter("xxqk")); // ѧϰ���18
		String xjysjl = DealString.toGBK(request.getParameter("xjysjl")); // У�����Ͻ���19
		String shsjqk = DealString.toGBK(request.getParameter("shsjqk")); // ���ʵ�����20
		String gzjl = DealString.toGBK(request.getParameter("gzjl")); // ��������21
		String grtc = DealString.toGBK(request.getParameter("grtc")); // �����س�22
		String zwtj = DealString.toGBK(request.getParameter("zwtj")); // �����Ƽ�23
		String fbsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��25
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];

		if (!("update".equals(go)) && "save".equals(doType)) {

			boolean judge = false;
			judge = StandardOperation.insert(tableName, new String[] { "id",
					"idsee", "rxnf", "xsxh", "hidden", "name", "xb", "csny",
					"mz", "xl", "zzmm", "zymc", "fxzymc", "sydq", "lxdz",
					"lxdh", "yzbm", "email", "hjqk", "xxqk", "xjysjl",
					"shsjqk", "gzjl", "grtc", "zwtj", "fbsj" }, new String[] {
					id, idsee, rxnf, xsxh, hidden, name, xb, csny, mz, xl,
					zzmm, zymc, fxzymc, sydq, lxdz, lxdh, yzbm, email, hjqk,
					xxqk, xjysjl, shsjqk, gzjl, grtc, zwtj, fbsj }, request);

			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "ok");
			}

			sql = "select * from jygl_grjlb where xsxh=?";
			colList = dao.getColumnName("select * from jygl_grjlb where 1=2"); // ������������
			grjlinfo = dao.getOneRs(sql, new String[] { yhm }, colList);

			if (null != grjlinfo) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), grjlinfo[i]); // ����¼ѭ������map��
				}
			}
		}

		if ("update".equals(go) && "save".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.update(tableName, new String[] { "id",
					"idsee", "rxnf", "hidden", "name", "xb", "csny", "mz",
					"xl", "zzmm", "zymc", "fxzymc", "sydq", "lxdz", "lxdh",
					"yzbm", "email", "hjqk", "xxqk", "xjysjl", "shsjqk",
					"gzjl", "grtc", "zwtj", "fbsj" }, new String[] { id, idsee,
					rxnf, hidden, name, xb, csny, mz, xl, zzmm, zymc, fxzymc,
					sydq, lxdz, lxdh, yzbm, email, hjqk, xxqk, xjysjl, shsjqk,
					gzjl, grtc, zwtj, fbsj }, "xsxh", xsxh, request);

			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "ok");
			}

			sql = "select * from jygl_grjlb where xsxh=?";
			colList = dao.getColumnName("select * from jygl_grjlb where 1=2"); // ������������
			grjlinfo = dao.getOneRs(sql, new String[] { yhm }, colList);

			if (null != grjlinfo) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), grjlinfo[i]); // ����¼ѭ������map��
				}
			}

		}

		if (null != grjlinfo) {
			go = "update";
			request.setAttribute("youjl", "youjl");
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), grjlinfo[i]); // ����¼ѭ������map��
			}
		} else {
			request.setAttribute("nojl", "nojl");
		}

		sql = "select zzmmdm,zzmm from dmk_zzmm"; // ������ò
		List zzmmList = dao.getList(sql, new String[] {}, new String[] {
				"zzmmdm", "zzmm" });
		request.setAttribute("zzmmList", zzmmList);

		sql = "select mzdm,mzmc from mzdmb"; // ����
		List mzList = dao.getList(sql, new String[] {}, new String[] { "mzdm",
				"mzmc" });
		request.setAttribute("mzList", mzList);

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ѧ���鿴��ҵ�ظ��б�

	public ActionForward alldwhfinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String rsNum = "0";
		String sql = "";
		String[] colList = null;
		ArrayList<Object> dwhf = new ArrayList<Object>();
		HttpSession session = request.getSession();
		String xh = session.getAttribute("yhm").toString();

		String doType = request.getParameter("doType");
		String rowid = request.getParameter("rowid");
		if (null != rowid) {
			rowid = rowid.replaceAll(" ", "+");
		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_dwhfb", "rowid", rowid,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from jygl_dwhfb a where xh = ?";
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao
						.getOneRs(sql, new String[] { xh }, "count")));

		// ��λ�ظ���Ϣ
		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.dwmc,a.hfsj from jygl_dwhfb a where a.xh=? order by a.hfsj desc ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by a.hfsj desc";
		colList = new String[] { "rid", "dwmc", "hfsj" };
		dwhf.addAll(dao.rsToVator(sql, new String[] { xh }, colList));

		sql = "select count(*) from jygl_dwhfb where xh='" + xh + "'";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);

		request.setAttribute("dwhf", dwhf);
		return mapping.findForward("success");

	}

	// ѧ���鿴��ҵ�ظ�����
	public ActionForward dwhfinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		if (null != pkValue) {
			pkValue = pkValue.replaceAll(" ", "+");
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from jygl_dwhfb where rowid=?";
			String[] colList = dao
					.getColumnName("select * from jygl_dwhfb where 1=2"); // ������������
			String[] qzxxinfo = dao.getOneRs(sql, new String[] { pkValue },
					colList);
			if (qzxxinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), qzxxinfo[i]); // ����¼ѭ������map��
				}
				String hfsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjsec = null;
				String sj = map.get("hfsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjsec = sj.substring(12, 14);

				hfsj = sjyear + "��" + sjmon + "��" + sjday + "��" + " " + sjhour
						+ ":" + sjmin + ":" + sjsec;
				map.put("hfsj", hfsj);
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ѧ�����ļ���
	public ActionForward mywj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String rsNum = "0";
		String sql = "";
		String[] colList = null;
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String xh = session.getAttribute("yhm").toString();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String tablename = "jygl_xsscb";
		String savetype = "";

		if (null != pkValue) {
			pkValue = pkValue.replaceAll(" ", "+");
		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_xsscb", "rowid", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		String[] pages = null;
		String[] titNames = null;
		String titName = request.getParameter("titName");
		// ��ͷ��

		pages = new String[] { "�ղص���Ƹ��Ϣ", "Ͷ�������ĵ�λ", "�����������Ƹ��Ϣ" };
		titNames = new String[] { "save", "toudi", "see" };

		List pagesList = dao.arrayToList(titNames, pages); // �ѱ�ͷ��Ϣװ������
		request.setAttribute("pages", pagesList);
		if (titName == null) {
			titName = titNames[0];
		}
		request.setAttribute("titName", titName);

		if ("save".equals(titName)) {
			savetype = "�ղ�";
			request.setAttribute("which", "save");
		} else if ("toudi".equals(titName)) {
			savetype = "Ͷ����";
			request.setAttribute("which", "toudi");
		} else if ("see".equals(titName)) {
			savetype = "���";
			request.setAttribute("which", "see");
		}

		// ��ҳ
		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from " + tablename
				+ " a where a.savetype=? and a.xh=?";
		rsNum = dao.getOneRs(sql, new String[] { savetype, xh }, "count");
		dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));
		// ��Ƹ��Ϣ
		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.pkvalue,a.gsmc,a.zpzw,a.fbsj from "
				+ tablename
				+ " a where savetype=? and xh=? order by fbsj desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by fbsj desc";

		colList = new String[] { "rid", "pkvalue", "gsmc", "zpzw", "fbsj" };
		rs = dao.getArrayList(sql, new String[] { savetype, xh }, colList);

		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}

	// ����Ա���˼�������
	public ActionForward jlgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rsNum = "0";

		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		ArrayList<Object> rs = new ArrayList<Object>();
		String sql = "";

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_grjlb", "xsxh", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from jygl_grjlb a";

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ���˼���
		sql = "select * from (select a.*,rownum r from (select a.xsxh,a.name,a.xb,a.zymc,a.fbsj from jygl_grjlb a  order by  fbsj desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by fbsj desc";

		String[] colList = new String[] { "xsxh", "name", "xb", "zymc", "fbsj" };
		String[] colListCN = dao.getColumnNameCN(colList, "jygl_grjlb");
		List topTr = dao.arrayToList(colList, colListCN);
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		sql = "select count(*) from jygl_grjlb";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");
	}

	// ���˼�����ϸ��Ϣ
	public ActionForward grjlinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String[] colList = null;
		String[] grjlinfo = null;
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		sql = "select * from jygl_grjlb where xsxh=?";
		colList = dao.getColumnName("select * from jygl_grjlb where 1=2"); // ������������
		grjlinfo = dao.getOneRs(sql, new String[] { pkValue }, colList);

		if (null == grjlinfo) {
			return mapping.findForward("success2");
		}

		if (null != grjlinfo) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), grjlinfo[i]); // ����¼ѭ������map��
			}
			String idsee = map.get("idsee");
			if ("no".equals(idsee)) {
				request.setAttribute("idsee", "no");
			} else {
				request.setAttribute("idsee", "yes");
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ����Ա��ְ��Ϣ����
	public ActionForward qzxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String rsNum = "0";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql = "";

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_qzxxb", "xh", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from jygl_qzxxb a where 1=1 ";

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ��ְ��Ϣ
		sql = "select * from (select a.*,rownum r from (select distinct a.xh,a.xm,a.xb,a.xymc,a.zymc,a.qzyx,a.fbsj from jygl_qzxxb a where 1=1  order by fbsj desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by fbsj desc";

		String[] colList = new String[] { "xh", "xm", "xb", "zymc", "qzyx",
				"fbsj" };
		String[] colListCN = dao.getColumnNameCN(colList, "jygl_qzxxb");
		List topTr = dao.arrayToList(colList, colListCN);
		rs = dao.getArrayList2(sql, new String[] {}, colList);

		sql = "select count(*) from jygl_qzxxb where 1=1";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");
	}

	// ����Ա�û���Ϣ����
	public ActionForward yhgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm cform = (CommanForm) form;
		String rsNum = "0";
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		ArrayList<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		String dwmc = DealString.toGBK(request.getParameter("dwmc"));

		StringBuffer query = new StringBuffer();
		String querry = "";

		query.append(" where 1=1 ");

		if ("query".equalsIgnoreCase(doType2)) {
			if (!"".equalsIgnoreCase(dwmc) && null != dwmc) {

				query.append(" and dwmc like'%");
				query.append(dwmc);
				query.append("%' ");
				querry = query.toString();
				map.put("dwmc", dwmc);
			}
		}

		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_dwxxb", "yhm", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}
		if ("zpdw".equals(doType)) {
			String temp = request.getParameter("query");
			String query2 = "where 1=1 ";
			if (!Base.isNull(temp) && temp.equals("yes")) {
				cform.setGsmc(DealString.toGBK(request.getParameter("gsmc")));
				cform.setSfzc(DealString.toGBK(request.getParameter("sfzc")));
				if (!Base.isNull(cform.getGsmc())) {
					query2 += " and gsmc like '%" + cform.getGsmc() + "%'";
				}
				if (!Base.isNull(cform.getSfzc())) {
					if (cform.getSfzc().equals("��ע��")) {
						query2 += " and yhm  is not null";
					} else {
						query2 += " and yhm  is  null";
					}
				}
			}
			sql = "select gsmc,zpzws,decode(yhm,null,'δע��','��ע��') sfzc from (select distinct a.gsmc,count(a.zpzw) zpzws,(select yhm from jygl_dwxxb where dwmc=a.gsmc) yhm from jygl_zpxxb a  group by gsmc) "
					+ query2 + " order by gsmc";
			ArrayList<String[]> list = dao.rsToVator2(sql, new String[] {},
					new String[] { "gsmc", "zpzws", "sfzc" });
			request.setAttribute("list", list);
			return mapping.findForward("zpdwxx");
		}

		// sql = "select a.yhm,rownum �к�,a.* from jygl_dwxxb a ";
		// String[] colList = new String[] { "yhm", "�к�", "yhm","dwmc", "zcsj"};
		// String[] colListCN = dao.getColumnNameCN(colList, "jygl_dwxxb");
		// List topTr = dao.arrayToList(colList, colListCN);
		// rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// sql = "select count(*) from jygl_dwxxb";
		// int rsNuminfo = dao.getOneRsint(sql);
		// rsNum = String.valueOf(rsNuminfo);
		// request.setAttribute("rsNum", rsNum);
		// request.setAttribute("rs", rs);

		// request.setAttribute("topTr", topTr);
		// return mapping.findForward("success");

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from jygl_dwxxb a" + querry;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ע����Ϣ
		sql = "select * from (select a.*,rownum r from (select a.yhm,a.dwmc,a.zcsj,a.sftgsfyz from jygl_dwxxb a "
				+ querry
				+ "  order by  zcsj desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by zcsj desc";
		String[] colList = new String[] { "yhm", "dwmc", "zcsj", "sftgsfyz" };
		String[] colListCN = dao.getColumnNameCN(colList, "jygl_dwxxb");
		List topTr = dao.arrayToList(colList, colListCN);
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		sql = "select count(*) from jygl_dwxxb";
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");

	}

	// ����Ա�û���Ϣ�鿴
	public ActionForward yhxxinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String sftgsfyz = DealString.toGBK(request.getParameter("sftgsfyz"));
		Encrypt ept = new Encrypt();

		if ("update".equalsIgnoreCase(act)) {
			boolean judge = false;

			judge = StandardOperation.update("jygl_dwxxb",
					new String[] { "sftgsfyz" }, new String[] { sftgsfyz },
					"yhm", pkValue, request);
			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from jygl_dwxxb where yhm=?";
			String[] colList = dao
					.getColumnName("select * from jygl_dwxxb where 1=2"); // ������������
			String[] yhxxinfo = dao.getOneRs(sql, new String[] { pkValue },
					colList);
			if (yhxxinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), yhxxinfo[i]); // ����¼ѭ������map��
				}
				String zcsj = dao.doForTime(map.get("zcsj").toString());
				map.put("zcsj", zcsj);
				String mm = map.get("mm");
				mm = ept.encrypt(mm);
				map.put("mm", mm);
			}
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ���˲�����ְ��Ϣ
	public ActionForward qzxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String tableName = "jygl_qzxxb";
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String go = "";
		String yhm = session.getAttribute("yhm").toString();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";

		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		if ("del".equals(act)) {
			boolean judge = false;
			judge = StandardOperation.delete(tableName, "xh", yhm, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		sql = "select * from jygl_qzxxb where xh=?";
		String[] colList = dao
				.getColumnName("select * from jygl_qzxxb where 1=2"); // ������������
		String[] qzxxinfo = dao.getOneRs(sql, new String[] { yhm }, colList);

		if (null != qzxxinfo) {
			go = "update";
		}

		if (!("update".equals(go))) {
			sql = "select * from view_xsjbxx where xh=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { yhm },
					new String[] { "xm", "xb", "xymc", "zymc", "lxdh" });
			if (null != stuinfo) {
				map.put("xh", yhm);
				map.put("xm", stuinfo[0]);
				map.put("xb", stuinfo[1]);
				map.put("xymc", stuinfo[2]);
				map.put("zymc", stuinfo[3]);
				map.put("lxdh", stuinfo[4]);
			}

		}

		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String xymc = DealString.toGBK(request.getParameter("xymc"));
		String zymc = DealString.toGBK(request.getParameter("zymc"));
		String lxdh = request.getParameter("lxdh");
		String email = request.getParameter("email");
		String qzhy = DealString.toGBK(request.getParameter("qzhy"));
		String qzyx = DealString.toGBK(request.getParameter("qzyx"));
		String grjs = DealString.toGBK(request.getParameter("grjs"));
		String fbsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��25
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];

		if (!("update".equals(go)) && "save".equals(doType)) {

			boolean judge = false;
			judge = StandardOperation.insert(tableName, new String[] { "xh",
					"xm", "xb", "xymc", "zymc", "lxdh", "email", "qzhy",
					"qzyx", "grjs", "fbsj" }, new String[] { xh, xm, xb, xymc,
					zymc, lxdh, email, qzhy, qzyx, grjs, fbsj }, request);

			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "ok");
			}

			sql = "select * from jygl_qzxxb where xh=?";
			colList = dao.getColumnName("select * from jygl_qzxxb where 1=2"); // ������������
			qzxxinfo = dao.getOneRs(sql, new String[] { yhm }, colList);

			if (null != qzxxinfo) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), qzxxinfo[i]); // ����¼ѭ������map��
				}
			}
		}

		if ("update".equals(go) && "save".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.update(tableName, new String[] { "xm",
					"xb", "xymc", "zymc", "lxdh", "email", "qzhy", "qzyx",
					"grjs", "fbsj" }, new String[] { xm, xb, xymc, zymc, lxdh,
					email, qzhy, qzyx, grjs, fbsj }, "xh", yhm, request);

			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "ok");
			}

			sql = "select * from jygl_qzxxb where xh=?";
			colList = dao.getColumnName("select * from jygl_qzxxb where 1=2"); // ������������
			qzxxinfo = dao.getOneRs(sql, new String[] { yhm }, colList);

			if (null != qzxxinfo) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), qzxxinfo[i]); // ����¼ѭ������map��
				}
			}

		}

		if (null != qzxxinfo) {
			go = "update";
			request.setAttribute("youxx", "youxx");
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), qzxxinfo[i]); // ����¼ѭ������map��
			}
			fbsj = null;
			String sjyear = null;
			String sjmon = null;
			String sjday = null;
			String sjhour = null;
			String sjmin = null;
			String sjsec = null;
			String sj = map.get("fbsj");
			sjyear = sj.substring(0, 4);
			sjmon = sj.substring(4, 6);
			sjday = sj.substring(6, 8);
			sjhour = sj.substring(8, 10);
			sjmin = sj.substring(10, 12);
			sjsec = sj.substring(12, 14);

			fbsj = sjyear + "��" + sjmon + "��" + sjday + "��" + " " + sjhour
					+ ":" + sjmin + ":" + sjsec;
			map.put("fbsj", fbsj);
		} else {
			request.setAttribute("noxx", "noxx");
		}

		request.setAttribute("rs", map);

		return mapping.findForward("success");
	}

	// ��������
	public ActionForward findarticle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String[] colList = null;
		String doType = request.getParameter("doType");
		String gjz = DealString.toGBK(request.getParameter("gjz"));
		String find = request.getParameter("find");
		ArrayList<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		List topTr = new ArrayList<HashMap<String, String>>();
		String rsNum = "0";
		gjz = "%" + gjz + "%";

		if ("find".equals(doType)) {
			if ("zxdt".equals(find)) {
				// ���¶�̬�����ţ�

				// ��ҳ
				CommanForm dataSearchForm = (CommanForm) form;
				sql = "select count(*) count from newscontent a where NEWSPART = 'N13'and  NEWSTITLE like ?";
				rsNum = dao.getOneRs(sql, new String[] { gjz }, "count");

				dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));
				sql = "select * from (select a.*,rownum r from (select distinct a.NEWSID,a.NEWSTITLE,a.PUBTIME,a.PUBER from newscontent a where a.NEWSPART='N13' order by pubtime desc) a ) a where a.r>"
						+ dataSearchForm.getPages().getStart()
						+ " and a.r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize())
						+ " and NEWSTITLE like ? order by pubtime desc";

				colList = new String[] { "NEWSID", "NEWSTITLE", "PUBER",
						"PUBTIME", };
				rs.addAll(dao.rsToVator(sql, new String[] { gjz }, colList));
				map2.put("tablename", "newscontent");
				String[] en = new String[] { "NEWSTITLE", "PUBER", "PUBTIME", };
				String[] cn = new String[] { "���ű���", "������", "����ʱ��" };
				topTr = dao.arrayToList(en, cn);
			}
			if ("ggl".equals(find)) {
				// ������

				// ��ҳ
				CommanForm dataSearchForm = (CommanForm) form;
				sql = "select count(*) count from JYGL_ZCWJB a where wjlx = '������' and wjbt like ?";
				rsNum = dao.getOneRs(sql, new String[] { gjz }, "count");
				dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));

				sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.wjbt,a.fbr,a.fbsj from JYGL_ZCWJB a where a.wjlx = '������' order by a.fbsj desc ) a ) a where a.r>"
						+ dataSearchForm.getPages().getStart()
						+ " and a.r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize())
						+ " and a.wjbt like ? order by a.fbsj desc";
				colList = new String[] { "rid", "wjbt", "fbr", "fbsj" };
				rs.addAll(dao.rsToVator(sql, new String[] { gjz }, colList));
				map2.put("tablename", "jygl_zcwjb");
				String[] en = new String[] { "wjbt", "fbr", "fbsj" };
				String[] cn = new String[] { "�ļ�����", "������", "����ʱ��" };
				topTr = dao.arrayToList(en, cn);
			}
			if ("zcfg".equals(find)) {
				// ���߷���

				// ��ҳ
				CommanForm dataSearchForm = (CommanForm) form;
				sql = "select count(*) count from JYGL_ZCWJB a where (a.wjlx='���Ҽ��ļ�' or a.wjlx='У���ļ�' or a.wjlx='�м��ļ�') and a.wjbt like ?";
				rsNum = dao.getOneRs(sql, new String[] { gjz }, "count");
				dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));

				sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.wjbt,a.wjlx,a.fbr,a.fbsj from JYGL_ZCWJB a where a.wjlx in ('���Ҽ��ļ�','�м��ļ�','У���ļ�') order by wjlx ) a ) a where a.r>"
						+ dataSearchForm.getPages().getStart()
						+ " and a.r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize())
						+ " and  a.wjbt like ? order by a.wjlx";
				colList = new String[] { "rid", "wjbt", "wjlx", "fbr", "fbsj" };
				rs.addAll(dao.rsToVator(sql, new String[] { gjz }, colList));
				map2.put("tablename", "jygl_zcwjb");
				String[] en = new String[] { "wjbt", "wjlx", "fbr", "fbsj" };
				String[] cn = new String[] { "�ļ�����", "�ļ�����", "������", "����ʱ��" };
				topTr = dao.arrayToList(en, cn);
			}
			if ("zph".equals(find)) {
				// У԰ר����Ƹ��

				// ��ҳ
				CommanForm dataSearchForm = (CommanForm) form;
				sql = "select count(*) count from JYGL_ZCWJB a where wjlx = 'У԰ר����Ƹ��' and wjbt like ?";
				rsNum = dao.getOneRs(sql, new String[] { gjz }, "count");
				dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));

				// ר����Ƹ��Ϣ
				sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.wjbt,a.wjlx,a.fbr,a.fbsj from JYGL_ZCWJB a where a.wjlx = 'У԰ר����Ƹ��' order by a.fbsj desc ) a ) a where a.r>"
						+ dataSearchForm.getPages().getStart()
						+ " and a.r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize())
						+ " and a.wjbt like ? order by a.fbsj desc";
				colList = new String[] { "rid", "wjbt", "wjlx", "fbr", "fbsj" };
				rs.addAll(dao.rsToVator(sql, new String[] { gjz }, colList));
				map2.put("tablename", "jygl_zcwjb");
				String[] en = new String[] { "wjbt", "wjlx", "fbr", "fbsj" };
				String[] cn = new String[] { "�ļ�����", "�ļ�����", "������", "����ʱ��" };
				topTr = dao.arrayToList(en, cn);
			}
			if ("syjs".equals(find)) {
				// ��Դ����

				// ��ҳ
				CommanForm dataSearchForm = (CommanForm) form;
				sql = "select count(*) count from JYGL_ZCWJB a where wjlx = '��Դ����' and wjbt like ?";
				rsNum = dao.getOneRs(sql, new String[] { gjz }, "count");
				dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));

				// ��Դ������Ϣ
				sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.wjbt,a.wjlx,a.fbr,a.fbsj from JYGL_ZCWJB a where a.wjlx = '��Դ����' order by a.fbsj desc ) a ) a where a.r>"
						+ dataSearchForm.getPages().getStart()
						+ " and a.r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize())
						+ " and wjbt like ? order by a.fbsj desc";
				colList = new String[] { "rid", "wjbt", "wjlx", "fbr", "fbsj" };
				rs.addAll(dao.rsToVator(sql, new String[] { gjz }, colList));
				map2.put("tablename", "jygl_zcwjb");
				String[] en = new String[] { "wjbt", "wjlx", "fbr", "fbsj" };
				String[] cn = new String[] { "�ļ�����", "�ļ�����", "������", "����ʱ��" };
				topTr = dao.arrayToList(en, cn);
			}
			if ("tpxx".equals(find)) {
				// ͼƬ��Ϣ

				CommanForm dataSearchForm = (CommanForm) form;
				sql = "select count(*) count from jygl_tpxxb a where bt like ?";
				rsNum = dao.getOneRs(sql, new String[] { gjz }, "count");
				dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));

				sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.bt,a.picpath1,a.fbsj,a.fbr from jygl_tpxxb a  order by fbsj desc) a ) a where a.r>"
						+ dataSearchForm.getPages().getStart()
						+ " and a.r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize())
						+ " and bt like ? order by fbsj desc";
				colList = new String[] { "rid", "bt", "fbr", "fbsj" };
				rs.addAll(dao.rsToVator(sql, new String[] { gjz }, colList));
				map2.put("tablename", "jygl_tpxxb");
				String[] en = new String[] { "bt", "fbr", "fbsj" };
				String[] cn = new String[] { "���ű���", "������", "����ʱ��" };
				topTr = dao.arrayToList(en, cn);
			}
			if ("xzzx".equals(find)) {
				// ��������

				// ��ҳ
				CommanForm dataSearchForm = (CommanForm) form;
				sql = "select count(*) count from wjsc_scwjxxb a where wjffbm='ѧ����' and wjm like ?";
				rsNum = dao.getOneRs(sql, new String[] { gjz }, "count");
				dataSearchForm.getPages().setMaxRecord(Integer.parseInt(rsNum));

				sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid, a.wjh,a.wjm,a.wjffbm,a.ffr,a.wjffsj from wjsc_scwjxxb a where a.wjffbm='ѧ����' order by a.wjffsj desc ) a ) a where a.r>"
						+ dataSearchForm.getPages().getStart()
						+ " and a.r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize())
						+ " and a.wjm like ?  order by a.wjffsj desc";
				colList = new String[] { "rid", "wjm", "wjffbm", "wjffsj" };
				rs.addAll(dao.rsToVator(sql, new String[] { gjz }, colList));
				map2.put("tablename", "wjsc_scwjxxb");
				String[] en = new String[] { "wjm", "wjffbm", "wjffsj" };
				String[] cn = new String[] { "�ļ���", "��������", "����ʱ��" };
				topTr = dao.arrayToList(en, cn);
			}
		}
		gjz = gjz.replace("%", "");
		map.put("gjz", gjz);
		map.put("find", find);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("tablename", map2);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");
	}

	// �����Ժ�鿴��������
	public ActionForward viewmorearticleinfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String tablename = request.getParameter("tablename");
		String pkValue = request.getParameter("pkValue");
		String sql = "";
		if (null != pkValue) {
			pkValue = pkValue.replaceAll(" ", "+");
		}
		// ����
		if ("jygl_zcwjb".equals(tablename)) {

			String[] tit = new String[] { "rowid", "wjbt", "fbr", "fbsj",
					"wjnr", "wjlx" };
			sql = "select rowid,wjbt,fbr,fbsj,wjnr,wjlx from "
					+ "JYGL_ZCWJB where rowid=?";
			String[] rs = dao.getOneRs(sql, new String[] { pkValue }, tit);
			rs = (rs == null) ? new String[tit.length] : rs;
			for (int i = 0; i < tit.length; i++) {
				if (i == 3) {
					String time = dao.doForTime(rs[3]);
					request.setAttribute(tit[3], isNull(rs[3]) ? " " : time);
				}
				if (i != 3) {
					request.setAttribute(tit[i], isNull(rs[i]) ? " " : rs[i]);
				}
			}
			CLOB clob = dao.getOneClob(sql, new String[] { pkValue }, "wjnr");
			String wjnr = clob.getSubString(1L, (int) clob.length());
			request.setAttribute("wjnr", wjnr);
			return mapping.findForward("success1");
		}
		// ���¶�̬
		if ("newscontent".equals(tablename)) {
			String[] tit = new String[] { "newsid", "newstitle", "newspart",
					"pubtime", "puber", "newscont" };
			sql = "select newsid,newstitle,newspart,pubtime,puber,newscont from "
					+ "newscontent where newsid=?";
			String[] rs = dao.getOneRs(sql, new String[] { pkValue }, tit);
			rs = (rs == null) ? new String[tit.length] : rs;
			for (int i = 0; i < tit.length; i++) {
				request.setAttribute(tit[i], isNull(rs[i]) ? " " : rs[i]);
			}
			CLOB clob = dao.getOneClob(sql, new String[] { pkValue },
					"newscont");
			request.setAttribute("newscont", clob.getSubString(1L, (int) clob
					.length()));
			return mapping.findForward("success2");
		}
		// ͼƬ��Ϣ
		if ("jygl_tpxxb".equals(tablename)) {
			HashMap<String, String> map = new HashMap<String, String>();
			sql = "select * from jygl_tpxxb where rowid=?";
			String[] colList = dao
					.getColumnName("select * from jygl_tpxxb where 1=2"); // ������������
			String[] tpxxinfo = dao.getOneRs(sql, new String[] { pkValue },
					colList);
			if (tpxxinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), tpxxinfo[i]); // ����¼ѭ������map��
				}
			}
			request.setAttribute("rs", map);// �������ݼ�
			return mapping.findForward("success3");
		}
		// �ļ�����
		if ("wjsc_scwjxxb".equals(tablename)) {
			HashMap<String, String> map = new HashMap<String, String>();
			sql = "select a.rowid ,a.wjh,a.wjffbm,a.wjffsj,a.wjscsm,a.ffr,a.wjsclj,a.wjm,substr(a.wjsclj,23,length(a.wjsclj)-22) filename from wjsc_scwjxxb a where a.rowid = ?";
			map = dao.getMap(sql, new String[] { pkValue }, new String[] {
					"wjh", "wjffbm", "wjffsj", "wjscsm", "ffr", "wjsclj",
					"wjm", "filename" });
			request.setAttribute("rs", map);
			return mapping.findForward("success4");
		}

		return mapping.findForward("success");
	}

	// ������������
	public ActionForward yqlj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String sql = "";
		String tableName = "jygl_yqljb";
		String[] colList = null;
		String rsNum = "0";
		boolean judge = false;

		String doType = request.getParameter("doType");
		if ("save".equals(doType)) {
			// sql = "select count(*) from " + tableName;
			// String countinfo = dao.getOneRs(sql, new String[] {},
			// "count(*)");
			//
			// if (null != countinfo) {
			//
			// if ((Integer.parseInt(countinfo)) > 8) {
			// ArrayList<HashMap<String, String>> yqlj = new
			// ArrayList<HashMap<String, String>>();
			// sql =
			// "select b.rowid,b.mc,b.wz from  (select a.rowid,a.mc,a.wz from jygl_yqljb a ) b where rownum<10 ";
			// colList = new String[] { "rowid", "mc", "wz" };
			// yqlj = dao.getArrayList(sql, new String[] {}, colList);
			//
			// if (null != yqlj) {
			// rsNum = String.valueOf(yqlj.size());
			// }
			//
			// request.setAttribute("yqlj", yqlj);
			// request.setAttribute("rsNum", rsNum);
			// request.setAttribute("somany", "somany");
			// return mapping.findForward("success");
			//
			// }
			//
			// }
			String mc = DealString.toGBK(request.getParameter("mc"));
			String wz = DealString.toGBK(request.getParameter("wz"));

			judge = StandardOperation.insert(tableName, new String[] { "mc",
					"wz" }, new String[] { mc, wz }, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
		}

		if ("del".equals(doType)) {
			String pkValue = DealString.toGBK(request.getParameter("pkValue"));
			judge = StandardOperation.delete(tableName, "mc", pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		ArrayList<HashMap<String, String>> yqlj = new ArrayList<HashMap<String, String>>();
		sql = "select a.rowid,a.mc,a.wz from jygl_yqljb a ";
		colList = new String[] { "rowid", "mc", "wz" };
		yqlj = dao.getArrayList(sql, new String[] {}, colList);

		if (null != yqlj) {
			rsNum = String.valueOf(yqlj.size());
		}

		request.setAttribute("yqlj", yqlj);
		request.setAttribute("rsNum", rsNum);
		return mapping.findForward("success");
	}

	// ��������list
	public ActionForward yqljList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "select a.mc,a.wz from jygl_yqljb a ";
		List<HashMap<String, String>> rs = dao.getList(sql, new String[] {},
				new String[] { "mc", "wz" });
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}

	// �޸���Ŀ����
	public ActionForward updateMessageInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		HashMap<String, String> map = new HashMap<String, String>();
		String tagId = request.getParameter("pkValue");
		HttpSession session = request.getSession();
		String yhm = session.getAttribute("yhm").toString();
		String sql = "";

		if ("update".equals(doType)) {
			String wjbt = DealString.toGBK(request.getParameter("wjbt"));
			String wjlx = DealString.toGBK(request.getParameter("wjlx"));
			String content = DealString.toGBK(request.getParameter("content1"));
			boolean judge = false;
			sql = "update jygl_zcwjb set wjbt=?,wjlx=?,wjnr=?,fbr=?,fbsj=(select to_char(sysdate,'yyyymmddhh24miss') from dual) where rowid=?";
			judge = dao.runUpdate2(sql, new String[] { wjbt, wjlx, content,
					yhm, tagId });
			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}
		}
		String[] tit = new String[] { "rowid", "wjbt", "wjlx" };
		sql = "select rowid,wjbt,wjlx,wjnr from " + "jygl_zcwjb where rowid=?";
		String[] newsinfo = dao.getOneRs(sql, new String[] { tagId }, tit);
		newsinfo = (newsinfo == null) ? new String[tit.length] : newsinfo;
		for (int i = 0; i < tit.length; i++) {
			map.put(tit[i], isNull(newsinfo[i]) ? " " : newsinfo[i]);
		}
		CLOB clob = dao.getOneClob(sql, new String[] { tagId }, "wjnr");
		map.put("content1", clob.getSubString(1L, (int) clob.length()));

		request.setAttribute("rs", map);

		return mapping.findForward("success");
	}

	// �޸�ͼƬ��Ϣ
	public ActionForward updatePictureInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		HashMap<String, String> map = new HashMap<String, String>();
		String tagId = request.getParameter("pkValue");
		HttpSession session = request.getSession();
		String yhm = session.getAttribute("yhm").toString();
		String sql = "";

		if ("update".equals(doType)) {
			String bt = request.getParameter("bt");
			String nr = request.getParameter("content1");
			String picpath1 = request.getParameter("picpath1");
			boolean judge = false;
			sql = "update jygl_tpxxb set bt=?,nr=?,picpath1=?,fbr=?,fbsj=(select to_char(sysdate,'yyyymmddhh24miss') from dual) where rowid=?";
			judge = dao.runUpdate2(sql, new String[] { bt, nr, picpath1, yhm,
					tagId });
			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}
		}
		String[] tit = new String[] { "rowid", "bt", "content1", "picpath1" };
		sql = "select rowid,bt,nr content1,picpath1 from jygl_tpxxb where rowid=?";
		String[] newsinfo = dao.getOneRs(sql, new String[] { tagId }, tit);
		newsinfo = (newsinfo == null) ? new String[tit.length] : newsinfo;
		for (int i = 0; i < tit.length; i++) {
			map.put(tit[i], isNull(newsinfo[i]) ? " " : newsinfo[i]);
		}
		request.setAttribute("rs", map);

		return mapping.findForward("success");
	}

	// �޸���Ƹ��Ϣ
	public ActionForward updateZpxxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		HashMap<String, String> map = new HashMap<String, String>();
		String tagId = request.getParameter("pkValue");
		HttpSession session = request.getSession();
		String yhm = session.getAttribute("yhm").toString();
		String sql = "";

		if ("update".equals(doType)) {
			String dwdz = DealString.toGBK(request.getParameter("dwdz"));
			String dwxz = DealString.toGBK(request.getParameter("dwxz"));
			String zpzw = DealString.toGBK(request.getParameter("zpzw"));
			String gsmc = DealString.toGBK(request.getParameter("gsmc"));
			String email = request.getParameter("email");
			String lxdh = request.getParameter("lxdh");
			String gzdd = DealString.toGBK(request.getParameter("gzdd")); // �����ص�
			String zprs = DealString.toGBK(request.getParameter("zprs"));
			String hyfl = DealString.toGBK(request.getParameter("hyfl"));
			String wyyq = DealString.toGBK(request.getParameter("wyyq"));
			String syxs = DealString.toGBK(request.getParameter("syxs"));
			String zzxs = DealString.toGBK(request.getParameter("zzxs"));
			String xlyq = DealString.toGBK(request.getParameter("xlyq"));
			String gwzz = DealString.toGBK(request.getParameter("gwzz"));
			String zwyq = DealString.toGBK(request.getParameter("zwyq"));
			String gsjj = DealString.toGBK(request.getParameter("gsjj"));
			String msxd = DealString.toGBK(request.getParameter("msxd"));
			String msdd = DealString.toGBK(request.getParameter("msdd"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String yddh = DealString.toGBK(request.getParameter("yddh"));
			String lxr = DealString.toGBK(request.getParameter("lxr"));
			String gswz = DealString.toGBK(request.getParameter("gswz"));
			String cz = DealString.toGBK(request.getParameter("cz"));
			String mssj = "";
			String day = DealString.toGBK(request.getParameter("day"));
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");
			String viewzydm = request.getParameter("viewzydm");
			if (Base.isNull(day)) {
				mssj = "";
			} else if (Base.isNull(hour)) {
				mssj = day;
			} else if (Base.isNull(min)) {
				mssj = day + " " + hour;
			} else {
				mssj = day + " " + hour + ":" + min;
			}
			boolean judge = false;
			sql = "update jygl_zpxxb set dwdz=?, dwxz=?, zpzw=?, gsmc=?, email=?, lxdh=?, gzdd=?,zprs=?, hyfl=?, wyyq=?, syxs=?, zzxs=?, xlyq=?, mssj=?,gwzz=?, zwyq=?, gsjj=?, msxd=?, msdd=?, xb=?,yddh=?,lxr=?,gswz=?,cz=?,fbr=?,fbsj=(select to_char(sysdate,'yyyymmddhh24miss') from dual),sxzy=? where rowid=?";
			judge = dao.runUpdate2(sql, new String[] { dwdz, dwxz, zpzw, gsmc,
					email, lxdh, gzdd, zprs, hyfl, wyyq, syxs, zzxs, xlyq,
					mssj, gwzz, zwyq, gsjj, msxd, msdd, xb, yddh, lxr, gswz,
					cz, yhm, viewzydm, tagId });
			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}
		}
		String[] tit = new String[] { "rowid", "dwdz", "dwxz", "zpzw", "gsmc",
				"email", "lxdh", "gzdd", "zprs", "hyfl", "wyyq", "syxs",
				"zzxs", "xlyq", "mssj", "gwzz", "zwyq", "gsjj", "fbsj", "msxd",
				"msdd", "xb", "xxly", "yddh", "lxr", "gswz", "cz", "sxzy" };
		sql = "select rowid,a.* from jygl_zpxxb a where rowid=?";
		String[] newsinfo = dao.getOneRs(sql, new String[] { tagId }, tit);
		newsinfo = (newsinfo == null) ? new String[tit.length] : newsinfo;
		for (int i = 0; i < tit.length; i++) {
			map.put(tit[i], isNull(newsinfo[i]) ? "" : newsinfo[i]);
		}
		// ��ҵ�����б�
		sql = "select hyfldm,hyfl from dmk_hyfl";
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		if (map.get("mssj") != null && !map.get("mssj").trim().equals("")) {
			if (map.get("mssj").length() == 10) {
				map.put("day", map.get("mssj"));
			} else {
				map.put("day", map.get("mssj").substring(0, 10));
				map.put("hour", map.get("mssj").substring(11, 13));
				map.put("min", map.get("mssj").substring(14));
			}
		}
		if (!Base.isNull(Base.xxdm) && Base.xxdm.equals(Globals.XXDM_SHGC)) {
			sql = "select zydm,zymc from bks_zydm order by bmdm";
			List zyList = dao.getList(sql, new String[] {}, new String[] {
					"zydm", "zymc" });
			request.setAttribute("zyList", zyList);
		}
		request.setAttribute("hyflList", hyflList);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// �޸��ļ��ϴ�
	public ActionForward updateWjxzInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		HashMap<String, String> map = new HashMap<String, String>();
		String tagId = DealString.toGBK(request.getParameter("pkValue"));
		HttpSession session = request.getSession();
		String yhm = session.getAttribute("yhm").toString();
		String sql = "";

		if ("update".equals(doType)) {
			String wjh = request.getParameter("wjh"); // �ļ���
			String wjm = request.getParameter("wjm"); // �ļ���
			String wjscsm = request.getParameter("wjscsm");
			String wjsclj = request.getParameter("wjsclj");
			boolean judge = false;
			sql = "update wjsc_scwjxxb set wjh=?,wjm=?,wjscsm=?,wjsclj=?,ffr=?,wjffsj=(select to_char(sysdate,'yyyymmddhh24miss') from dual) where wjh=?";
			judge = dao.runUpdate2(sql, new String[] { wjh, wjm, wjscsm,
					wjsclj, yhm, tagId });
			if (judge) {
				tagId = wjh;
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}
		}
		String[] tit = new String[] { "wjh", "wjm", "wjffbm", "wjscsm",
				"wjsclj" };
		sql = "select wjh, wjm, wjffbm, wjscsm, wjsclj from wjsc_scwjxxb where wjh=?";
		String[] newsinfo = dao.getOneRs(sql, new String[] { tagId }, tit);
		newsinfo = (newsinfo == null) ? new String[tit.length] : newsinfo;
		for (int i = 0; i < tit.length; i++) {
			map.put(tit[i], isNull(newsinfo[i]) ? "" : newsinfo[i]);
		}
		request.setAttribute("rs", map);

		return mapping.findForward("success");
	}
	// �㽭���þ�ҵ��ҳ��򿪼���½
	public ActionForward zjjjjyindex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		Encrypt ept = new Encrypt();
		HttpSession session = request.getSession();
		session.setAttribute("userName", "anonymous");
		session.setAttribute("person", "anonymous");

		// ҳ�������������Ϻ����̣�
		session.setAttribute("xxdm", Base.xxdm);

		String xxmcinfo = dao.getOneRs("select xxmc from xtszb ",
				new String[] {}, "xxmc");

		if (xxmcinfo != null) {
			session.setAttribute("xxmc", xxmcinfo);
		} else {
			session.setAttribute("xxmc", "");
		}

		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String usertype = request.getParameter("usertype");

		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> find = new HashMap<String, String>();

		String[] colList = null;
		// ArrayList<Object> news = new ArrayList<Object>();
		ArrayList<Object> zczp = new ArrayList<Object>();
		ArrayList<Object> syjs = new ArrayList<Object>();
		ArrayList<Object> wjdc = new ArrayList<Object>();

		// ҳ�������
		String lll = dao.getOneRs("select jyweblll from jyweb_liulan ",
				new String[] {}, "jyweblll");
		if (null != lll && !"".equalsIgnoreCase(lll)) {
			int i = 1;
			i = Integer.valueOf(lll);
			if (session.getAttribute("lll") == null) {
				i++;
				session.setAttribute("lll", i);
			}
			StandardOperation.update("jyweb_liulan",
					new String[] { "jyweblll" }, new String[] { String
							.valueOf(i) }, "jywebxx", "zfsoft", request);
		} else if (null == lll || "".equalsIgnoreCase(lll)) {
			sql = " delete from jyweb_liulan";
			dao.runUpdateTab(sql);
			sql = "insert into jyweb_liulan (jyweblll,jywebxx) values('1','zfsoft')";
			dao.runUpdateTab(sql);
			session.setAttribute("lll", "1");
		}

		// �ʾ����
		sql = "select choice from jygl_wjdcb order by rownum";
		colList = new String[] { "choice" };
		wjdc.addAll(dao.rsToVator(sql, new String[] {}, colList));

		sql = "select distinct bt from jygl_wjdcb";
		String[] wjdcinfo = dao.getOneRs(sql, new String[] {},
				new String[] { "bt" });
		if (wjdcinfo != null) {
			request.setAttribute("wjdcbt", wjdcinfo[0]);
		}

		// ��ְ��Ϣ

		ArrayList<HashMap<String, String>> qzxx = new ArrayList<HashMap<String, String>>();
		sql = "select xh,xm,xb,xymc,zymc,qzyx,fbsj from (select a.xh,a.xm,a.xb,a.xymc,a.zymc,a.qzyx,a.fbsj from jygl_qzxxb a order by fbsj desc) jygl_qzxxb where rownum<6 ";
		colList = new String[] {"xh","xm","xb","xymc","zymc","qzyx","fbsj"};
		qzxx = dao.getArrayList3(sql, new String[] {}, colList);

		// ���¶�̬�����ţ�
		ArrayList<HashMap<String, String>> zxdt = new ArrayList<HashMap<String, String>>();
		sql = "select b.* from (SELECT ROWNUM r,c.* FROM (select a.* from newscontent a WHERE NEWSPART = 'N13' order by newsid DESC) c) b  WHERE r<7";
		colList = new String[] { "r", "newsid", "newstitle", "pubtime" };
		zxdt = dao.getArrayList3(sql, new String[] {}, colList);

		// ר����Ƹ��
		sql = "select * from (select a.rowid,a.* from JYGL_ZCWJB a order by fbsj desc ) b where wjlx = 'У԰ר����Ƹ��' and rownum<6";
		colList = new String[] { "rowid", "wjbt" };
		zczp.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// ������
		ArrayList<HashMap<String, String>> ggl = new ArrayList<HashMap<String, String>>();
		sql = "select * from (select a.rowid,a.*,a.wjbt wjbttitle from JYGL_ZCWJB a order by fbsj desc ) b where wjlx = '������'";
		colList = new String[] { "rowid", "wjbt", "wjbttitle", "fbsj" };
		ggl = dao.getArrayList3(sql, new String[] {}, colList);

		// ��Դ����
		sql = "select * from (select a.rowid,a.* from JYGL_ZCWJB a order by fbsj desc ) b where wjlx = '��Դ����'";
		colList = new String[] { "rowid", "wjbt" };
		syjs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// �����Ѷ
		ArrayList<HashMap<String, String>> xgzx = new ArrayList<HashMap<String, String>>();
		sql = "select * from (select a.rowid,a.*,a.wjbt wjbttitle from JYGL_ZCWJB a order by fbsj desc ) b where wjlx !='������' and rownum<7";
		colList = new String[] { "rowid", "wjlx", "wjbt", "fbsj", "wjbttitle" };
		xgzx = dao.getArrayList3(sql, new String[] {}, colList);

		// ͼƬ��Ϣ
		ArrayList<HashMap<String, String>> tpxx = new ArrayList<HashMap<String, String>>();
		sql = "select b.rowid,b.bt,b.picpath1,b.bt bttitle from  (select a.rowid,a.bt,a.picpath1 from jygl_tpxxb a order by fbsj desc) b where rownum<3 ";
		colList = new String[] { "rowid", "bt", "picpath1", "bttitle" };
		tpxx = dao.getArrayList3(sql, new String[] {}, colList);

		// ��������
		ArrayList<HashMap<String, String>> yqlj = new ArrayList<HashMap<String, String>>();
		sql = "select b.rowid,b.mc,b.wz from  (select a.rowid,a.mc,a.wz from jygl_yqljb a ) b where rownum<11 ";
		colList = new String[] { "rowid", "mc", "wz" };
		yqlj = dao.getArrayList(sql, new String[] {}, colList);

		request.setAttribute("who", "anonymous");// Ĭ���û�Ϊ�����û�
		if ("left".equals(doType)) {
			session.setAttribute("usertype", "anonymous");
			session.setAttribute("yhm", "");
		}

		if (null != session.getAttribute("usertype")
				&& !("login".equals(doType)) && !("left".equals(doType))) {
			usertype = session.getAttribute("usertype").toString();
			if ("dw".equals(usertype)) {
				request.setAttribute("who", "dw");
				String dwmc = session.getAttribute("dwmc").toString();
				request.setAttribute("dwmc", dwmc);
			} else if ("xs".equals(usertype)) {
				request.setAttribute("who", "xs");
				String xm = session.getAttribute("xm").toString();
				request.setAttribute("xm", xm);
			} else if ("admin".equals(usertype)) {
				request.setAttribute("who", "admin");
				String xm = session.getAttribute("xm").toString();
				request.setAttribute("xm", xm);
			}
		}
		if (Globals.XXDM_ZJJJZYJSXY.equals(Base.xxdm)) {
			if ("login".equals(doType)) {
				String yhm = request.getParameter("yhm");
				String xh = request.getParameter("xh");
				String mm = ept.encrypt(request.getParameter("mm"));
				// ��λ�û���½
				if ("dw".equals(usertype)) {
					sql = "select dwmc from jygl_dwxxb where yhm=? and mm=? ";
					String dwinfo = dao.getOneRs(sql, new String[] { yhm, mm },
							"dwmc");

					if (null != dwinfo && !("".equals(dwinfo))) {
						sql = "select count(*) from jygl_dwjlb where gsmc=?";
						String dwinfo2 = dao.getOneRs(sql,
								new String[] { dwinfo }, "count(*)");

						if ("".equals(dwinfo2)) {
							session.setAttribute("howmany", "0");
						} else {
							session.setAttribute("howmany", dwinfo2);
						}
						session.setAttribute("yhm", yhm);
						session.setAttribute("user", yhm);
						session.setAttribute("dwmc", dwinfo);
						session.setAttribute("usertype", "dw");
						request.setAttribute("who", "dw");
					} else {
						request.setAttribute("login", "no");
					}
				}
				// ѧ���û���½
				if ("xs".equals(usertype)) {
					if ("12866".equals(Base.xxdm)) {
						String xnOrGdFlag = dao.getOneRs(
								"select jwflag from xtszb", new String[] {},
								"jwflag");// �����ѧ��ľͻ��1,������ֵ

						if ((xnOrGdFlag != null && xnOrGdFlag.trim()
								.equals("1"))
								&& usertype.equalsIgnoreCase("xs")) {
							mm = request.getParameter("mm");
						} else {
							mm = ept.encrypt(request.getParameter("mm"));
						}

						sql = "select xm from xsjyzcb where xh=? and mm=?";

						String stuinfo = dao.getOneRs(sql, new String[] { yhm,
								mm }, "xm");

						if (null != stuinfo && !("".equals(stuinfo))) {

							sql = "select count(*) from jygl_dwhfb where xh=?";
							String stuinfo2 = dao.getOneRs(sql,
									new String[] { xh }, "count(*)");
							if ("".equals(stuinfo2)) {
								session.setAttribute("howmanydw", "0");
							} else {
								session.setAttribute("howmanydw", stuinfo2);
							}

							session.setAttribute("yhm", yhm);
							session.setAttribute("user", yhm);
							session.setAttribute("xm", stuinfo);
							session.setAttribute("usertype", "xs");
							request.setAttribute("who", "xs");
						} else {
							request.setAttribute("login", "no");
						}
					} else {
						String xnOrGdFlag = dao.getOneRs(
								"select jwflag from xtszb", new String[] {},
								"jwflag");// �����ѧ��ľͻ��1,������ֵ

						if ((xnOrGdFlag != null && xnOrGdFlag.trim()
								.equals("1"))
								&& usertype.equalsIgnoreCase("xs")) {
							mm = request.getParameter("mm");
						} else {
							mm = ept.encrypt(request.getParameter("mm"));
						}

						sql = "select xm from view_xsjbxx where xh=? and mm=?";

						String stuinfo = dao.getOneRs(sql, new String[] { yhm,
								mm }, "xm");

						if (null != stuinfo && !("".equals(stuinfo))) {

							sql = "select count(*) from jygl_dwhfb where xh=?";
							String stuinfo2 = dao.getOneRs(sql,
									new String[] { yhm }, "count(*)");
							if ("".equals(stuinfo2)) {
								session.setAttribute("howmanydw", "0");
							} else {
								session.setAttribute("howmanydw", stuinfo2);
							}

							session.setAttribute("yhm", yhm);
							session.setAttribute("user", yhm);
							session.setAttribute("xm", stuinfo);
							session.setAttribute("usertype", "xs");
							request.setAttribute("who", "xs");
						} else {
							request.setAttribute("login", "no");
						}
					}
				}
				// ����Ա��½
				if ("admin".equals(usertype)) {
					sql = "select xm from yhb where yhm=? and kl=? and szbm='888888'";

					String admininfo = dao.getOneRs(sql,
							new String[] { yhm, mm }, "xm");

					if (null != admininfo && !("".equals(admininfo))) {
						session.setAttribute("yhm", yhm);
						session.setAttribute("user", yhm);
						session.setAttribute("xm", admininfo);
						session.setAttribute("usertype", "admin");
						request.setAttribute("who", "admin");
					} else {
						request.setAttribute("login", "no");
					}
				}
			}
		} else {
			if ("login".equals(doType)) {
				String yhm = request.getParameter("yhm");
				String xh = request.getParameter("xh");
				String mm = ept.encrypt(request.getParameter("mm"));
				String yzm = session.getAttribute("yzm").toString()
						.toLowerCase();
				String yzm2 = request.getParameter("yzm").toLowerCase();

				if (yzm.equals(yzm2)) {

					// ��λ�û���½
					if ("dw".equals(usertype)) {
						sql = "select dwmc from jygl_dwxxb where yhm=? and mm=? ";
						String dwinfo = dao.getOneRs(sql, new String[] { yhm,
								mm }, "dwmc");

						if (null != dwinfo && !("".equals(dwinfo))) {
							sql = "select count(*) from jygl_dwjlb where gsmc=?";
							String dwinfo2 = dao.getOneRs(sql,
									new String[] { dwinfo }, "count(*)");

							if ("".equals(dwinfo2)) {
								session.setAttribute("howmany", "0");
							} else {
								session.setAttribute("howmany", dwinfo2);
							}
							session.setAttribute("yhm", yhm);
							session.setAttribute("user", yhm);
							session.setAttribute("dwmc", dwinfo);
							session.setAttribute("usertype", "dw");
							request.setAttribute("who", "dw");
						} else {
							request.setAttribute("login", "no");
						}
					}
					// ѧ���û���½
					if ("xs".equals(usertype)) {
						if ("12866".equals(Base.xxdm)) {
							String xnOrGdFlag = dao.getOneRs(
									"select jwflag from xtszb",
									new String[] {}, "jwflag");// �����ѧ��ľͻ��1,������ֵ

							if ((xnOrGdFlag != null && xnOrGdFlag.trim()
									.equals("1"))
									&& usertype.equalsIgnoreCase("xs")) {
								mm = request.getParameter("mm");
							} else {
								mm = ept.encrypt(request.getParameter("mm"));
							}

							sql = "select xm from xsjyzcb where xh=? and mm=?";

							String stuinfo = dao.getOneRs(sql, new String[] {
									yhm, mm }, "xm");

							if (null != stuinfo && !("".equals(stuinfo))) {

								sql = "select count(*) from jygl_dwhfb where xh=?";
								String stuinfo2 = dao.getOneRs(sql,
										new String[] { xh }, "count(*)");
								if ("".equals(stuinfo2)) {
									session.setAttribute("howmanydw", "0");
								} else {
									session.setAttribute("howmanydw", stuinfo2);
								}

								session.setAttribute("yhm", yhm);
								session.setAttribute("user", yhm);
								session.setAttribute("xm", stuinfo);
								session.setAttribute("usertype", "xs");
								request.setAttribute("who", "xs");
							} else {
								request.setAttribute("login", "no");
							}
						} else {
							String xnOrGdFlag = dao.getOneRs(
									"select jwflag from xtszb",
									new String[] {}, "jwflag");// �����ѧ��ľͻ��1,������ֵ

							if ((xnOrGdFlag != null && xnOrGdFlag.trim()
									.equals("1"))
									&& usertype.equalsIgnoreCase("xs")) {
								mm = request.getParameter("mm");
							} else {
								mm = ept.encrypt(request.getParameter("mm"));
							}

							sql = "select xm from view_xsjbxx where xh=? and mm=?";

							String stuinfo = dao.getOneRs(sql, new String[] {
									yhm, mm }, "xm");

							if (null != stuinfo && !("".equals(stuinfo))) {

								sql = "select count(*) from jygl_dwhfb where xh=?";
								String stuinfo2 = dao.getOneRs(sql,
										new String[] { yhm }, "count(*)");
								if ("".equals(stuinfo2)) {
									session.setAttribute("howmanydw", "0");
								} else {
									session.setAttribute("howmanydw", stuinfo2);
								}

								session.setAttribute("yhm", yhm);
								session.setAttribute("user", yhm);
								session.setAttribute("xm", stuinfo);
								session.setAttribute("usertype", "xs");
								request.setAttribute("who", "xs");
							} else {
								request.setAttribute("login", "no");
							}
						}
					}
					// ����Ա��½
					if ("admin".equals(usertype)) {
						sql = "select xm from yhb where yhm=? and kl=? and szbm='888888'";

						String admininfo = dao.getOneRs(sql, new String[] {
								yhm, mm }, "xm");

						if (null != admininfo && !("".equals(admininfo))) {
							session.setAttribute("yhm", yhm);
							session.setAttribute("user", yhm);
							session.setAttribute("xm", admininfo);
							session.setAttribute("usertype", "admin");
							request.setAttribute("who", "admin");
						} else {
							request.setAttribute("login", "no");
						}
					}
				} else {
					request.setAttribute("yz", "no");
				}
			}
		}
		// ��Ƹ��Ϣ
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		if (!Base.isNull(Base.xxdm) && Base.xxdm.equals(Globals.XXDM_SHGC)
				&& session.getAttribute("usertype") != null
				&& "xs".equals(session.getAttribute("usertype").toString())) {
			sql = "select b.* from (select a.rowid rid, a.* from jygl_zpxxb a where sxzy is null or "
					+ "instr(',' || sxzy || ',',',' || (select zydm from view_xsjbxx where xh = '"
					+ session.getAttribute("yhm").toString()
					+ "') || ',')>0 "
					+ "order by fbsj desc) b where b.xxsh = 'ͨ��'";
		} else {
			sql = "select b.* from (select a.rowid rid ,a.* from jygl_zpxxb a order by fbsj desc ) b where b.xxsh='ͨ��' and rownum<9";
		}
		colList = new String[] { "rid", "gsmc", "zpzw", "fbsj", "readtimes" };
		rs = dao.getArrayList3(sql, new String[] {}, colList);
		// �ʾ����
		if ("wjdc".equals(doType2)) {
			String temp = session.getAttribute("wjdc") == null ? "" : session
					.getAttribute("wjdc").toString();
			if (temp.equals("")) {
				String choice = DealString
						.toGBK(request.getParameter("choice"));
				if (!("".equals(choice))) {
					sql = "select times from jygl_wjdcb where choice=?";
					String[] timesinfo = dao.getOneRs(sql,
							new String[] { choice }, new String[] { "times" });
					if (null != timesinfo) {
						String times = timesinfo[0];
						int time = Integer.parseInt(times);
						time++;
						times = String.valueOf(time);
						boolean judge = false;
						sql = "update jygl_wjdcb set times=? where choice=?";
						judge = dao.runUpdate(sql,
								new String[] { times, choice });
						if (judge) {
							request.setAttribute("wjdcok", "wjdcok");
							session.setAttribute("wjdc", "yes");
						} else {
							request.setAttribute("wjdcno", "wjdcno");
						}
					}
				} else {
					request.setAttribute("wjdcnull", "wjdcnull");
				}
			} else {
				request.setAttribute("wjdccf", "wjdccf");
			}
		}

		// ��������
		request.setAttribute("find", find);
		request.setAttribute("tpxx", tpxx);
		request.setAttribute("map", map);
		request.setAttribute("rs", rs);
		request.setAttribute("zxdt", zxdt);
		request.setAttribute("qzxx", qzxx);
		request.setAttribute("zczp", zczp);
		request.setAttribute("ggl", ggl);
		request.setAttribute("syjs", syjs);
		request.setAttribute("yqlj", yqlj);
		request.setAttribute("xgzx", xgzx);

		request.setAttribute("wjdc", wjdc);
		return mapping.findForward("success11");
	}
	// �㽭������Ƹ��Ϣ����
	public ActionForward zjjjzpxxjz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		session.setAttribute("userName", "anonymous");
		session.setAttribute("person", "anonymous");

		// ҳ�������������Ϻ����̣�
		session.setAttribute("xxdm", Base.xxdm);

		String xxmcinfo = dao.getOneRs("select xxmc from xtszb ",
				new String[] {}, "xxmc");

		if (xxmcinfo != null) {
			session.setAttribute("xxmc", xxmcinfo);
		} else {
			session.setAttribute("xxmc", "");
		}

		String doType = request.getParameter("doType");
		String usertype = request.getParameter("usertype");

		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> find = new HashMap<String, String>();

		String[] colList = null;
		// ArrayList<Object> news = new ArrayList<Object>();
		ArrayList<Object> zczp = new ArrayList<Object>();
		ArrayList<Object> syjs = new ArrayList<Object>();
		ArrayList<Object> wjdc = new ArrayList<Object>();

		// ҳ�������
		String lll = dao.getOneRs("select jyweblll from jyweb_liulan ",
				new String[] {}, "jyweblll");
		if (null != lll && !"".equalsIgnoreCase(lll)) {
			int i = 1;
			i = Integer.valueOf(lll);
			if (session.getAttribute("lll") == null) {
				i++;
				session.setAttribute("lll", i);
			}
			StandardOperation.update("jyweb_liulan",
					new String[] { "jyweblll" }, new String[] { String
							.valueOf(i) }, "jywebxx", "zfsoft", request);
		} else if (null == lll || "".equalsIgnoreCase(lll)) {
			sql = " delete from jyweb_liulan";
			dao.runUpdateTab(sql);
			sql = "insert into jyweb_liulan (jyweblll,jywebxx) values('1','zfsoft')";
			dao.runUpdateTab(sql);
			session.setAttribute("lll", "1");
		}


		// ��ְ��Ϣ

		ArrayList<HashMap<String, String>> qzxx = new ArrayList<HashMap<String, String>>();
		sql = "select xh,xm,xb,xymc,zymc,qzyx,fbsj from (select a.xh,a.xm,a.xb,a.xymc,a.zymc,a.qzyx,a.fbsj from jygl_qzxxb a order by fbsj desc) jygl_qzxxb where rownum<6 ";
		colList = new String[] {"xh","xm","xb","xymc","zymc","qzyx","fbsj"};
		qzxx = dao.getArrayList3(sql, new String[] {}, colList);

		request.setAttribute("who", "anonymous");// Ĭ���û�Ϊ�����û�
		if ("left".equals(doType)) {
			session.setAttribute("usertype", "anonymous");
			session.setAttribute("yhm", "");
		}

		if (null != session.getAttribute("usertype")
				&& !("login".equals(doType)) && !("left".equals(doType))) {
			usertype = session.getAttribute("usertype").toString();
			if ("dw".equals(usertype)) {
				request.setAttribute("who", "dw");
				String dwmc = session.getAttribute("dwmc").toString();
				request.setAttribute("dwmc", dwmc);
			} else if ("xs".equals(usertype)) {
				request.setAttribute("who", "xs");
				String xm = session.getAttribute("xm").toString();
				request.setAttribute("xm", xm);
			} else if ("admin".equals(usertype)) {
				request.setAttribute("who", "admin");
				String xm = session.getAttribute("xm").toString();
				request.setAttribute("xm", xm);
			}
		}
		// ��Ƹ��Ϣ
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		if (!Base.isNull(Base.xxdm) && Base.xxdm.equals(Globals.XXDM_SHGC)
				&& session.getAttribute("usertype") != null
				&& "xs".equals(session.getAttribute("usertype").toString())) {
			sql = "select b.* from (select a.rowid rid, a.* from jygl_zpxxb a where sxzy is null or "
					+ "instr(',' || sxzy || ',',',' || (select zydm from view_xsjbxx where xh = '"
					+ session.getAttribute("yhm").toString()
					+ "') || ',')>0 "
					+ "order by fbsj desc) b where b.xxsh = 'ͨ��'";
		} else {
			sql = "select b.* from (select a.rowid rid ,a.* from jygl_zpxxb a order by fbsj desc ) b where b.xxsh='ͨ��' and rownum<9";
		}
		colList = new String[] { "rid", "gsmc", "zpzw", "fbsj", "readtimes" };
		rs = dao.getArrayList3(sql, new String[] {}, colList);
		request.setAttribute("find", find);
		request.setAttribute("map", map);
		request.setAttribute("rs", rs);
		request.setAttribute("qzxx", qzxx);
		request.setAttribute("zczp", zczp);
		request.setAttribute("syjs", syjs);

		request.setAttribute("wjdc", wjdc);
		return mapping.findForward("success12");
	}
	// �㽭������ְ��Ϣ����
	public ActionForward zjjjqzxxjz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		session.setAttribute("userName", "anonymous");
		session.setAttribute("person", "anonymous");

		// ҳ�������������Ϻ����̣�
		session.setAttribute("xxdm", Base.xxdm);

		String xxmcinfo = dao.getOneRs("select xxmc from xtszb ",
				new String[] {}, "xxmc");

		if (xxmcinfo != null) {
			session.setAttribute("xxmc", xxmcinfo);
		} else {
			session.setAttribute("xxmc", "");
		}

		String doType = request.getParameter("doType");
		String usertype = request.getParameter("usertype");

		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> find = new HashMap<String, String>();

		String[] colList = null;
		// ArrayList<Object> news = new ArrayList<Object>();
		ArrayList<Object> zczp = new ArrayList<Object>();
		ArrayList<Object> syjs = new ArrayList<Object>();
		ArrayList<Object> wjdc = new ArrayList<Object>();

		// ҳ�������
		String lll = dao.getOneRs("select jyweblll from jyweb_liulan ",
				new String[] {}, "jyweblll");
		if (null != lll && !"".equalsIgnoreCase(lll)) {
			int i = 1;
			i = Integer.valueOf(lll);
			if (session.getAttribute("lll") == null) {
				i++;
				session.setAttribute("lll", i);
			}
			StandardOperation.update("jyweb_liulan",
					new String[] { "jyweblll" }, new String[] { String
							.valueOf(i) }, "jywebxx", "zfsoft", request);
		} else if (null == lll || "".equalsIgnoreCase(lll)) {
			sql = " delete from jyweb_liulan";
			dao.runUpdateTab(sql);
			sql = "insert into jyweb_liulan (jyweblll,jywebxx) values('1','zfsoft')";
			dao.runUpdateTab(sql);
			session.setAttribute("lll", "1");
		}


		// ��ְ��Ϣ

		ArrayList<HashMap<String, String>> qzxx = new ArrayList<HashMap<String, String>>();
		sql = "select xh,xm,xb,xymc,zymc,qzyx,fbsj from (select a.xh,a.xm,a.xb,a.xymc,a.zymc,a.qzyx,a.fbsj from jygl_qzxxb a order by fbsj desc) jygl_qzxxb where rownum<6 ";
		colList = new String[] {"xh","xm","xb","xymc","zymc","qzyx","fbsj"};
		qzxx = dao.getArrayList3(sql, new String[] {}, colList);

		request.setAttribute("who", "anonymous");// Ĭ���û�Ϊ�����û�
		if ("left".equals(doType)) {
			session.setAttribute("usertype", "anonymous");
			session.setAttribute("yhm", "");
		}

		if (null != session.getAttribute("usertype")
				&& !("login".equals(doType)) && !("left".equals(doType))) {
			usertype = session.getAttribute("usertype").toString();
			if ("dw".equals(usertype)) {
				request.setAttribute("who", "dw");
				String dwmc = session.getAttribute("dwmc").toString();
				request.setAttribute("dwmc", dwmc);
			} else if ("xs".equals(usertype)) {
				request.setAttribute("who", "xs");
				String xm = session.getAttribute("xm").toString();
				request.setAttribute("xm", xm);
			} else if ("admin".equals(usertype)) {
				request.setAttribute("who", "admin");
				String xm = session.getAttribute("xm").toString();
				request.setAttribute("xm", xm);
			}
		}
		// ��Ƹ��Ϣ
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		if (!Base.isNull(Base.xxdm) && Base.xxdm.equals(Globals.XXDM_SHGC)
				&& session.getAttribute("usertype") != null
				&& "xs".equals(session.getAttribute("usertype").toString())) {
			sql = "select b.* from (select a.rowid rid, a.* from jygl_zpxxb a where sxzy is null or "
					+ "instr(',' || sxzy || ',',',' || (select zydm from view_xsjbxx where xh = '"
					+ session.getAttribute("yhm").toString()
					+ "') || ',')>0 "
					+ "order by fbsj desc) b where b.xxsh = 'ͨ��'";
		} else {
			sql = "select b.* from (select a.rowid rid ,a.* from jygl_zpxxb a order by fbsj desc ) b where b.xxsh='ͨ��' and rownum<9";
		}
		colList = new String[] { "rid", "gsmc", "zpzw", "fbsj", "readtimes" };
		rs = dao.getArrayList3(sql, new String[] {}, colList);
		request.setAttribute("find", find);
		request.setAttribute("map", map);
		request.setAttribute("rs", rs);
		request.setAttribute("qzxx", qzxx);
		request.setAttribute("zczp", zczp);
		request.setAttribute("syjs", syjs);

		request.setAttribute("wjdc", wjdc);
		return mapping.findForward("success13");
	}
}
