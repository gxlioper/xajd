/*
 * �������� 2007-1-12 bat_zzj
 *
 */
package xgxt.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.sql.CLOB;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.DAO.SxjyDAO;
import xgxt.action.news.NewsModel;
import xgxt.action.news.NewsService;
import xgxt.base.DealString;
import xgxt.comm.CommDAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.User;
import xgxt.qgzx.zgdzdx.QgzxZgdzdxDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.MakeQuery;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.basic.BasicService;
import com.zfsoft.xgxt.base.util.UniqID;

import common.Globals;

public class NewsAction extends BasicAction {
	// DAO dao = DAO.getInstance();
	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward myActFwd = null;
		String myAct = mapping.getParameter();

		if ("showNews".equalsIgnoreCase(myAct)) {
			// ��Ϣ��ʾ
			String newsQuery = Base.getNewsQuery();
			
			if ("new".equals(newsQuery)){
				myActFwd = new xgxt.action.news.NewsAction().newsQuery(mapping, form, request, response);
			} else {
				myActFwd = showNews(mapping, form, request, response);
			}
		} else if ("saveNews".equalsIgnoreCase(myAct)) {
			// ��Ϣ��ʾ
			myActFwd = saveNews(mapping, form, request, response);
		} else if ("newsPub".equalsIgnoreCase(myAct)) {
			// ��Ϣ����
			myActFwd = newsPub(mapping, form, request, response);
		} else if ("newsContent".equalsIgnoreCase(myAct)) {
			// ��Ϣ�鿴
			myActFwd = newsContent(mapping, form, request, response);
		} else if ("newsManage".equalsIgnoreCase(myAct)) {
			// ����Ϊ������������Ϣ�鿴
			myActFwd = newsManage(mapping, form, request, response);
		} else if ("newsManageUpdate".equalsIgnoreCase(myAct)) {
			// ��Ϣ�޸�
			myActFwd = newsManageUpdate(mapping, form, request, response);
		} else if ("saveNewsManage".equalsIgnoreCase(myAct)) {
			// ��Ϣ�鿴
			myActFwd = saveNewsManage(mapping, form, request, response);
		} else if ("moreNews".equalsIgnoreCase(myAct)) {
			// ����������Ϣ
			myActFwd = moreNews(mapping, form, request, response);
		} else if("newsPigManage".equalsIgnoreCase(myAct)){
			// ��ҳ����ͼƬ��ʾ
			myActFwd = newsPigManage(mapping, form, request, response);
		} else if("newsInfo".equalsIgnoreCase(myAct)){
			//��ҳ����ͼƬ��ʾ
			myActFwd = newsInfo(mapping, form, request, response);
		}
		return myActFwd;
	}

	private ActionForward saveNewsManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommanForm msgForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String ssmk = msgForm.getXmdm();
		String newsTitle = DealString.toGBK(request.getParameter("newsTitle"));
		String sContent = DealString.toGBK(request.getParameter("content1"));
		String puber = session.getAttribute("userName").toString();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {// �������ϴ�ѧ
			puber = dao.getOneRs("select xm from yhb where yhm = ?",
					new String[] { puber }, "xm");
		}
		String newsId = request.getParameter("newsId");
		newsTitle = isNull(newsTitle) ? "�ޱ���" : newsTitle;
		boolean insert = dao.updateNews(newsTitle, ssmk, sContent, puber,
				newsId);
		if (insert) {
			request.setAttribute("inserted", "ok");
		} else {
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("success");
	}

	// ����������
	// ����Ϊ�ܵ����Ź���ҳ�浥���޸�
	private ActionForward newsManageUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		CommanForm myForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		String newsId = request.getParameter("pk");
		String sql = "select newstitle,newscont,NEWSPART from "
				+ "newscontent where newsid=?";
		CLOB clob = dao.getOneClob(sql, new String[] { newsId }, "newscont");
		String[] tmp = (dao.getOneRs(sql, new String[] { newsId },
				new String[] { "newstitle", "NEWSPART" }));
		String newsTit = tmp[0];
		myForm.setXmdm(tmp[1]);
		request.setAttribute("newscont", clob.getSubString(1L, (int) clob
				.length()));
		request.setAttribute("content1", clob.getSubString(1L, (int) clob
				.length()));
		request.setAttribute("newstit", newsTit);
		request.setAttribute("isModi", "yes");
		request.setAttribute("newsId", newsId);
		request.setAttribute("towho", "");
		request.setAttribute("modList", dao.getModelList());
		return mapping.findForward("success");
	}

	// ����������
	// ����Ϊ�ܵ����Ź���ҳ��
	private ActionForward newsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String tableName = "view_newsForBjlh";
		String xmdm = request.getParameter("xmdm");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String pubTime = request.getParameter("pubTime");
		SxjyDAO dao = new SxjyDAO();
		CommanForm myForm = (CommanForm) form;
		myForm.setXm(xm);

		String[] colList = new String[] { "newsid", "newstitle", "gnmkmc",
				"puber", "xm", "pubtime", "towho" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1 ");

		// String userType = session.getAttribute("userType").toString();
		// String userDep = session.getAttribute("userDep").toString();
		// String xxdm = StandardOperation.getXxdm();

		if (xmdm != null && !("".equalsIgnoreCase(xmdm.trim()))) {
			query.append(" and newspart = '");
			query.append(xmdm);
			query.append("' ");
		}
		if (xm != null && !("".equalsIgnoreCase(xm.trim()))) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%' ");
		}
		if (pubTime != null && !("".equalsIgnoreCase(pubTime.trim()))) {
			query.append(" and pubtime like '%");
			query.append(pubTime);
			query.append("%' ");
		}

		List topTr = dao.arrayToList(colList, colListCN);// ��ͷ
		if ((request.getParameter("del") != null)
				&& request.getParameter("del").equalsIgnoreCase("true")) {
			String pk = request.getParameter("pk");
			boolean del = StandardOperation.delete("newscontent", "newsid", pk,
					request);
			if (del) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
			rs = dao.sxjyQuery(tableName, query.toString(), new String[] {},
					colList, "");
		}
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs = dao.sxjyQuery(tableName, query.toString(), new String[] {},
					colList, "");
		}
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("modList", dao.getModelList());
		return mapping.findForward("success");
	}

	@SuppressWarnings("unchecked")
	private ActionForward showNews(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm msgForm = (CommanForm) form;
		SxjyDAO dao = new SxjyDAO();
		QgzxZgdzdxDAO qgzxDao = new QgzxZgdzdxDAO();
		DAO dao1 = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		String tagId = request.getParameter("tagId");
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				: session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		if (tagId.equalsIgnoreCase("N01")) {
			String sql = "select newsid,newstitle,newspart,towho,pubtime,(select gnmkmc from gnmkdmb a where gnmkdm = a.newspart) newspartmc, (select xm from yhb c where a.puber=c.yhm)puber from "
					+ "newscontent a ";
			String[] colList = { "newsid", "newstitle", "newspart",
					"newspartmc", "towho", "pubtime", "puber" };
			// �������ϴ�ѧ ��������Ժ ���ս���
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)
						|| xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)
						|| xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {
					sql = "select rownum, a.* from (select a.newsid,a.newstitle,a.newspart, b.gnmkmc newspartmc,a.pubtime,a.puber,a.towho from "
							+ "(select * from newscontent order by pubtime desc) a ,gnmkdmb b where a.newspart = b.gnmkdm order by a.pubtime desc ) a";
					if ("stu".equals(userType)) {
						sql += " where towho<>'2'";
					} else if ("tea".equals(userType) || "xy".equals(userType)) {
						sql += " where towho<>'3'";
					}
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
					sql = "select rownum, a.* from (select a.newsid,a.newstitle,a.newspart, b.gnmkmc newspartmc,a.pubtime,(select xm from yhb c where a.puber=c.yhm)puber,a.towho from "
							+ "(select * from newscontent order by pubtime desc) a ,gnmkdmb b where a.newspart = b.gnmkdm order by a.pubtime desc ) a";
					if ("stu".equals(userType)) {
						sql += " where towho<>'2'";
					} else if ("tea".equals(userType) || "xy".equals(userType)) {
						sql += " where towho<>'3'";
					}
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)
						|| xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					sql = "select rownum, a.* from (select a.newsid,a.newstitle,a.newspart, b.gnmkmc newspartmc,a.pubtime,(select xm from yhb c where a.puber=c.yhm)puber,a.towho,(select bmmc from zxbz_xxbmdm where bmdm=a.bmdm) bmmc,a.bmdm from "
							+ "(select * from newscontent order by pubtime desc) a ,gnmkdmb b where a.newspart = b.gnmkdm order by a.pubtime desc ) a";
					if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
						if ("stu".equalsIgnoreCase(userType)) {
							sql += " where (towho='4' or towho='1')";
						} else if ("xy".equalsIgnoreCase(userType)) {
							sql += " where (towho='3' or towho='1')";
						} else if ("xx".equalsIgnoreCase(userType)) {
							sql += " where (towho='2' or towho='1')";
						} else {
							sql += " where 1=1 ";
						}
						if (qgzxDao.isYrdw(userName)) {
							sql += " and newspart<>'N06'";
						}
					}
					if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {// ��ɳ����
						if ("stu".equalsIgnoreCase(userType)) {
							sql += " where ((towho='4' and bmdm=" + userDep
									+ ") or towho='1')";
						} else if ("xy".equalsIgnoreCase(userType)) {
							sql += " where (towho='1' or (towho='3' and bmdm not in (select distinct xydm from view_njxyzybj)) or (towho='3' and bmdm="
									+ userDep + "))";
						} else if ("xx".equalsIgnoreCase(userType)) {
							sql += " where (towho='2' or towho='1')";
						}
					}

				} else {
					sql = "select rownum, a.newsid,a.newstitle,a.newspart, b.gnmkmc newspartmc,(select xm from yhb c where a.puber=c.yhm)puber,a.puber from "
							+ "(select * from newscontent order by pubtime desc) a ,gnmkdmb b where a.newspart = b.gnmkdm ";
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					colList = new String[] { "rownum", "newsid", "newstitle",
							"newspart", "newspartmc", "towho", "pubtime",
							"puber", "bmmc" };
				} else {
					colList = new String[] { "rownum", "newsid", "newstitle",
							"newspart", "newspartmc", "pubtime", "puber" };
					request.setAttribute("showSsmk", "is");
				}
				request.setAttribute("isEXIST", "is");
			} else {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
						|| xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)
						|| xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
					if ("stu".equals(userType)) {
						sql += " where towho<>'2'  order by pubtime desc";
					} else if ("tea".equals(userType) || "xy".equals(userType)) {
						sql += " where towho<>'3'  order by pubtime desc";
					} else {
						sql += " order by pubtime desc";
					}
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					if ("stu".equals(userType)) {
						sql = "select  a.newsid,a.newstitle,a.newspart,a.pubtime,b.bmmc puber from "
								+ "newscontent a,view_yhxx b where a.puber = b.yhm and towho<>'2'  order by a.pubtime desc ";
					} else if ("tea".equals(userType) || "xy".equals(userType)) {
						sql = " select a.newsid,a.newstitle,a.newspart,a.pubtime,b.bmmc puber from "
								+ "newscontent a,view_yhxx b where a.puber = b.yhm and  towho<>'3'  order by a.pubtime desc";
					} else {
						sql = " select a.newsid,a.newstitle,a.newspart,a.pubtime,b.xm puber from "
								+ "newscontent a,view_yhxx b where a.puber = b.yhm order by a.pubtime desc";
					}
					colList = new String[] { "newsid", "newstitle", "newspart",
							"pubtime", "puber" };
				}
				else {
					if ("stu".equalsIgnoreCase(userType)) {
						sql += " where (towho='4' or towho='1') order by pubtime desc";
					} else if ("xy".equalsIgnoreCase(userType)) {
						sql += " where (towho='3' or towho='1') order by pubtime desc";
					} else if ("xx".equalsIgnoreCase(userType)
							|| "admin".equalsIgnoreCase(userType)) {
						sql += " where (towho='2' or towho='1') order by pubtime desc";
					}
				}
				request.setAttribute("disXh", "is");
				request.setAttribute("isEXIST", "is");
				request.setAttribute("showSsmk", "is");
			}
			List rs = dao1.getList(sql, new String[] {}, colList);
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
				if (qgzxDao.isYrdw(userName)) {
					sql = "select rownum, a.* from (select a.newsid,a.newstitle,a.newspart, b.gnmkmc newspartmc,a.pubtime,(select xm from yhb c where a.puber=c.yhm)puber,a.towho from "
							+ "(select * from newscontent order by pubtime desc) a ,gnmkdmb b where a.newspart = b.gnmkdm order by a.pubtime desc ) a";
					sql += " where newspart='N06' and (towho='1' or towho='5')";
					rs.addAll(dao1.getList(sql, new String[] {}, colList));
				}
			}
			request.setAttribute("rs", rs);
		} else {
			String sql = "select newsid,newstitle,newspart,towho,pubtime,(select xm from yhb c where a.puber=c.yhm)puber from "
					+ "newscontent a where newspart=?";
			String[] colList = { "newsid", "newstitle", "newspart", "towho",
					"pubtime", "puber" };
			// �������ϴ�ѧ
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
					sql = "select rownum, a.*,(select b.gnmkmc from gnmkdmb b where b.gnmkdm=a.newspart) newspartmc from (select newsid,newstitle,newspart, pubtime,puber from "
							+ "newscontent where  newspart = ? order by pubtime desc) a";
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)
						|| xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					sql = "select rownum, a.* from (select a.newsid,a.newstitle,a.newspart, b.gnmkmc newspartmc,a.pubtime,(select xm from yhb c where a.puber=c.yhm)puber,a.towho,(select bmmc from zxbz_xxbmdm where bmdm=a.bmdm) bmmc,a.bmdm from "
							+ "(select * from newscontent order by pubtime desc) a ,gnmkdmb b where a.newspart = b.gnmkdm order by a.pubtime desc ) a";
					if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
						if ("N28".equalsIgnoreCase(tagId)) {// ����ѧ��
							String newsType = msgForm.getNewsType();
							sql = "select rownum, a.* from (select a.newsid,a.newstitle,a.newspart, b.gnmkmc newspartmc,a.pubtime,(select xm from yhb c where a.puber=c.yhm)puber,"
									+ " (case a.xwlx when '001' then '֪ͨ����' when '002' then '��������' when '003' then '��ʾ' when'004' then '�������' end )newsType,a.xwlx,a.towho,(select bmmc from zxbz_xxbmdm where bmdm=a.bmdm) bmmc,a.bmdm from "
									+ "(select * from newscontent order by pubtime desc) a ,gnmkdmb b where a.newspart = b.gnmkdm order by a.xwlx, a.pubtime  desc ) a";
							colList = new String[] { "newsid", "newstitle",
									"newspart", "towho", "pubtime", "puber",
									"newsType" };
							request.setAttribute("NewsTypeList", dao1
									.getTypeList(2));
							request
									.setAttribute("showNewsType",
											"showNewsType");
							sql += " where 1=1 ";
							if (!Base.isNull(newsType)) {
								sql += " and xwlx='" + newsType + "'";
							}
						} else {
							sql += " where 1=1 ";
						}
						if ("stu".equalsIgnoreCase(userType)) {
							sql += " and (towho='4' or towho='1')";
						} else if ("xy".equalsIgnoreCase(userType)) {
							sql += " and (towho='3' or towho='1')";
						} else if ("xx".equalsIgnoreCase(userType)) {
							sql += " and (towho='2' or towho='1')";
						}
						if (qgzxDao.isYrdw(userName)) {
							sql += " and newspart<>'N06'";
						}
					}
					if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
						if ("stu".equalsIgnoreCase(userType)) {
							sql += " where ((towho='4' and bmdm='" + userDep
									+ "') or towho='1')";
						} else if ("xy".equalsIgnoreCase(userType)) {
							sql += " where (towho='1' or (towho='3' and bmdm not in (select distinct xydm from view_njxyzybj)) or (towho='3' and bmdm='"
									+ userDep + "')) ";
						} else if ("xx".equalsIgnoreCase(userType)) {
							sql += " where (towho='2' or towho='1')";
						} else {
							sql += " where 1=1 ";
						}
					}
					sql += " and newspart=?";
				} else {
					sql = "select rownum, a.newsid,a.newstitle,a.newspart, a.pubtime,a.puber,(select b.gnmkmc from gnmkdmb b where b.gnmkdm=a.newspart) newspartmc from "
							+ "newscontent a where  a.newspart = ? order by a.pubtime desc";
					request.setAttribute("showSsmk", "is");// ��ʾ����ģ��
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					colList = new String[] { "rownum", "newsid", "newstitle",
							"newspart", "newspartmc", "towho", "pubtime",
							"puber", "bmmc" };
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
					if ("N28".equalsIgnoreCase(tagId)) {
						colList = new String[] { "rownum", "newsid",
								"newstitle", "newspart", "newspartmc",
								"pubtime", "puber", "newsType" };
					} else {
						colList = new String[] { "rownum", "newsid",
								"newstitle", "newspart", "newspartmc",
								"pubtime", "puber" };
					}
					request.setAttribute("showSsmk", "is");// ��ʾ����ģ��
				} else {
					colList = new String[] { "rownum", "newsid", "newstitle",
							"newspart", "newspartmc", "pubtime", "puber" };
					request.setAttribute("showSsmk", "is");// ��ʾ����ģ��
				}
				request.setAttribute("isEXIST", "is");
			} else {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
						|| xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)
						|| xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
					if ("stu".equals(userType)) {
						sql += " and towho<>'2'  order by pubtime desc ";
					} else if ("tea".equals(userType) || "xy".equals(userType)) {
						sql += " and towho<>'3'  order by pubtime desc ";
					} else {
						sql += " order by pubtime desc";
					}
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					if ("stu".equals(userType)) {
						sql = "select  towho,a.newsid,a.newstitle,a.newspart,a.pubtime,b.bmmc puber from "
								+ "newscontent a,view_yhxx b where a.puber = b.yhm and a.newspart=? and towho<>'2'  order by a.pubtime desc ";
					} else if ("tea".equals(userType) || "xy".equals(userType)) {
						sql = " select towho,a.newsid,a.newstitle,a.newspart,a.pubtime,b.bmmc puber from "
								+ "newscontent a,view_yhxx b where a.puber = b.yhm and a.newspart=? and towho<>'3'  order by a.pubtime desc ";
					} else {
						sql = " select towho,a.newsid,a.newstitle,a.newspart,a.pubtime,b.xm puber from "
								+ "newscontent a,view_yhxx b where a.puber = b.yhm and a.newspart=? order by a.pubtime desc ";
					}
				} else if (xxdm.equals(Globals.XXDM_CSMZZYJSXY)
						|| xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {// ��ɳ����
					if ("stu".equals(userType)) {
						sql = " select a.newsid,a.newstitle,a.newspart,substr(a.pubtime,0,10)pubtime,c.xm puber,(select bmmc from zxbz_xxbmdm f where a.bmdm=f.bmdm) bmmc from "
								+ "newscontent a,view_yhxx c where a.puber = c.yhm and (a.bmdm ='"
								+ userDep
								+ "' or exists(select XGBDM from xtszb b where a.bmdm=b.XGBDM)) and a.newspart=?  order by a.pubtime desc ";
					} else {
						sql = " select a.newsid,a.newstitle,a.newspart,substr(a.pubtime,0,10)pubtime,b.xm puber,(select distinct bmmc from zxbz_xxbmdm c where a.bmdm=c.bmdm) bmmc from "
								+ "newscontent a,view_yhxx b where a.puber = b.yhm and a.newspart=?  order by a.pubtime desc  ";
					}
					colList = new String[] { "newsid", "newstitle", "newspart",
							"towho", "pubtime", "puber", "bmmc" };
				} else {
					if ("stu".equalsIgnoreCase(userType)) {
						sql += " and (towho='4' or towho='1')";
					} else if ("xy".equalsIgnoreCase(userType)) {
						sql += " and (towho='3' or towho='1')";
					} else if ("xx".equalsIgnoreCase(userType)) {
						sql += " and (towho='2' or towho='1')";
					} else {
						sql += " and 1=1 ";
					}
				}
			}
			sql = " select * from ( " + sql + ") order by pubtime desc ";
			List rs = dao1.getList(sql, new String[] { tagId }, colList);
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
				if (qgzxDao.isYrdw(userName)) {
					sql = "select rownum, a.* from (select a.newsid,a.newstitle,a.newspart, b.gnmkmc newspartmc,a.pubtime,(select xm from yhb c where a.puber=c.yhm)puber,a.towho from "
							+ "(select * from newscontent order by pubtime desc) a ,gnmkdmb b where a.newspart = b.gnmkdm order by a.pubtime desc ) a";
					sql += " where newspart='N06' and (towho='1' or towho='5')";
					rs.addAll(dao1.getList(sql, new String[] {}, colList));
				}
			}
			request.setAttribute("rs", rs);
		}
		if ("N16".equalsIgnoreCase(tagId)) {// ��Ԣ����
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)) {// �㽭����ְҵ����ѧԺ
				request.setAttribute("showType", "showType");
			}
			List rs = null;
			String sql = " select jbid ,jbtitle,jbcontent,jbpubtime,jbpubername,xxsh from gygl_mzjb where xxsh ='ͨ��'  order by jbpubtime desc ";
			rs = dao1.getList(sql, new String[] {}, new String[] { "jbid",
					"jbtitle", "jbpubtime", "jbpubername" });
			request.setAttribute("rsjb", rs);
		}
		msgForm.setXmdm(tagId);
		request.setAttribute("modList", dao.getModelList());
		if (tagId.equalsIgnoreCase("N02")
				&& xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
				&& userType.equalsIgnoreCase("xy")) {
			Vector<Object> rs2 = dao.getSxjyAwoke(userDep);
			request.setAttribute("rs2", rs2);
			request.setAttribute("rsNum", rs2.size());
		}
		return mapping.findForward("success");
	}

	private ActionForward saveNews(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm msgForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String ssmk = msgForm.getXmdm();
		String newsTitle = DealString.toGBK(request.getParameter("newsTitle"));
		String towho = request.getParameter("towho");
		String sContent = DealString.toGBK(request.getParameter("content1"));
		String tagId = request.getParameter("tagId");
		String puber = (String) session.getAttribute("userName");
		String userDep = (String) session.getAttribute("userDep");
		String userType = (String) session.getAttribute("userType");
		String newsType = request.getParameter("newsType");
		String fwfs = request.getParameter("fwfs");
		String isModi = request.getParameter("isModi");
		String newsId = request.getParameter("newsId");
		newsTitle = isNull(newsTitle) ? "�ޱ���" : newsTitle;
		String act = request.getParameter("act");
		String message="";
		boolean blog= false;
		if (!isNull(act) && act.equalsIgnoreCase("del") && !isNull(puber)) {
			String []sql=new String[2];
			sql[1] = "delete newscontent where newsid='"+newsId+"'";
			sql[0] = " delete from xg_sysz_tpszb a where exists";
			sql[0]+= " (select 1 from newscontent b where newsid='"+newsId+"' and a.newsid=b.newssign)";
			CommDAO comDAO=new CommDAO();
			blog=comDAO.saveArrDate(sql);
		} else {
			
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				blog = dao.updateNews2(newsTitle, ssmk, sContent, puber, newsId,
						towho, newsType,fwfs);
				//����ͼƬ
				if(blog){
					String delSql = " delete from xg_sysz_tpszb a where exists  ";
					delSql+=" (select 1 from newscontent b where newsid='"+newsId+"'  ";
					delSql+=" and a.newsid=b.newssign) ";
					dao.runUpdate(delSql, new String[]{});
					
					String []pic=saveNewPic(sContent,puber,ssmk,newsTitle);
					if (blog && pic.length>0) {
						dao.runBatch(pic);
					}
				}
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
				String []pic=saveNewPic(sContent,puber,ssmk,newsTitle);
				String bmdm = "xy".equals(userType) ? userDep : "";
				blog=dao.addNews2(newsTitle, ssmk, sContent, puber, towho, newsType,fwfs,bmdm);
				if (blog && pic.length>0) {
					dao.runBatch(pic);
				}
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				//��ͼƬ���浽ͼƬ�������ñ�
				String []pic=saveNewPic(sContent,puber,ssmk,newsTitle);
				blog=dao.addNews3(newsTitle, ssmk, sContent, puber, towho, userDep);
				if (blog && pic.length>0) {
					dao.runBatch(pic);
				}
			} else {
				//��ͼƬ���浽ͼƬ�������ñ�
				String []pic=saveNewPic(sContent,puber,ssmk,newsTitle);
				blog = dao.addNews3(newsTitle, ssmk, sContent, puber,
						towho, userDep);
				if (blog && pic.length>0) {
					dao.runBatch(pic);
				}
			}
			
		}
		message=blog ? "true" : "false";
		return new ActionForward("newsPub.do?tagId=" + tagId+"&message="+message, true);
	}

	private ActionForward newsPub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		CommanForm msgForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		String tagId = request.getParameter("tagId");
		String xxdm = StandardOperation.getXxdm();
		String doType = request.getParameter("doType");
		String[] input = {};
		String[] output = {};
		if ("update".equals(doType)) {
			request.setAttribute("update", "update");
		} else {
			request.setAttribute("normal", "normal");
		}
		// TODO ���ŷ��������⣬����Ժ��ȫ���ɼ���
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
			request.setAttribute("showshgc", "showshgc");
		}

		// =================2010/8/4 edit by luojw==========================
		// if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)
		// || xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){
		request.setAttribute("showzgdzdx", "showzgdzdx");
		// }
		// =================2010/8/4 end==========================
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)
				&& "N28".equalsIgnoreCase(tagId)) {
			request.setAttribute("showNewsType", "showNewsType");
			request.setAttribute("NewsTypeList", dao.getTypeList(2));
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			request.setAttribute("showbjlh", "showbjlh");
		} else {
			request.setAttribute("showbjlh", "");
		}
		String sql = "";
		List<String[]> rs = new ArrayList<String[]>();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			if (userType.equalsIgnoreCase("admin")) {
				sql = "select newsid,newstitle,(select gnmkmc from gnmkdmb where gnmkdm=newspart )newspart,substr(pubtime,0,10)pubtime,puber, xm, bmmc,towho from "
						+ "view_newsForCsmz where newspart=? order by pubtime";
				input = new String[] { tagId };
				output = new String[] { "newsid", "newstitle", "newspart",
						"towho", "pubtime", "puber", "xm", "bmmc" };
			} else {
				sql = "select newsid,newstitle,(select gnmkmc from gnmkdmb where gnmkdm=newspart )newspart,substr(pubtime,0,10)pubtime,puber, xm,bmmc,towho from "
						+ "view_newsForCsmz where newspart=? and puber = ? order by pubtime";
				input = new String[] { tagId, userName };
				output = new String[] { "newsid", "newstitle", "newspart",
						"towho", "pubtime", "puber", "xm", "bmmc" };
			}

		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {// ���ս���
			if (userType.equalsIgnoreCase("admin")) {
				sql = "select newsid,newstitle,'' towho,(select gnmkmc from gnmkdmb where gnmkdm=newspart )newspart,pubtime,(select xm from yhb where yhm=puber)puber from "
						+ "newscontent where newspart=? order by pubtime";
				input = new String[] { tagId };
				output = new String[] { "newsid", "newstitle", "newspart",
						"towho", "pubtime", "puber" };
			} else {
				sql = "select newsid,newstitle,''towho,(select gnmkmc from gnmkdmb where gnmkdm=newspart )newspart,pubtime,(select xm from yhb where yhm=puber)puber from "
						+ "newscontent where newspart=? and puber = ? order by pubtime";
				input = new String[] { tagId, userName };
				output = new String[] { "newsid", "newstitle", "newspart",
						"towho", "pubtime", "puber" };
			}

		} else if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// �й����ʴ�ѧ
			if (userType.equalsIgnoreCase("admin")) {
				sql = "select newsid,newstitle,(select gnmkmc from gnmkdmb where gnmkdm=newspart )newspart,towho,pubtime,"
						+ "(select xm from yhb c where a.puber=c.yhm)puber,(case xwlx when '001' then '֪ͨ����' when '002' then '��������' when '003' then '��ʾ' when'004' then '�������' end )newsType from "

						+ "newscontent a where newspart=? order by pubtime";
				input = new String[] { tagId };
				output = new String[] { "newsid", "newstitle", "newspart",
						"towho", "pubtime", "puber", "newsType" };
			} else {
				sql = "select newsid,newstitle,(select gnmkmc from gnmkdmb where gnmkdm=newspart )newspart,towho,pubtime,(select xm from yhb c where a.puber=c.yhm)puber,"
						+ " (case xwlx when '001' then '֪ͨ����' when '002' then '��������' when '003' then '��ʾ' when'004' then '�������' end )newsType from "
						+ "newscontent a where newspart=? and puber = ? order by pubtime";
				input = new String[] { tagId, userName };
				output = new String[] { "newsid", "newstitle", "newspart",
						"towho", "pubtime", "puber", "newsType" };
			}
		} else {
			if (userType.equalsIgnoreCase("admin")) {
				sql = "select newsid,newstitle,(select gnmkmc from gnmkdmb where gnmkdm=newspart )newspart,towho,pubtime,"
						+ "(select xm from yhb c where a.puber=c.yhm)puber from "
						+ "newscontent a where newspart=? order by pubtime";
				input = new String[] { tagId };
				output = new String[] { "newsid", "newstitle", "newspart",
						"towho", "pubtime", "puber" };
			} else {
				sql = "select newsid,newstitle,(select gnmkmc from gnmkdmb where gnmkdm=newspart )newspart,towho,pubtime,(select xm from yhb c where a.puber=c.yhm)puber from "
						+ "newscontent a where newspart=? and puber = ? order by pubtime";
				input = new String[] { tagId, userName };
				output = new String[] { "newsid", "newstitle", "newspart",
						"towho", "pubtime", "puber" };
			}

		}
		sql = "select a.*, rownum r from (" + sql + ") a order by pubtime desc";
		rs = CommonQueryDAO.commonQuery(sql, "", input, output, msgForm);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());

		String newsId = request.getParameter("newsId");
		if (!isNull(newsId)) {
			sql = "select newstitle,newscont,towho,xwlx newsType,fwfs from "
					+ "newscontent where newsid=?";
			CLOB clob = dao
					.getOneClob(sql, new String[] { newsId }, "newscont");
			HashMap<String, String> map = new HashMap<String, String>();
			map = dao.getMap(sql, new String[] { newsId }, new String[] {
					"newstitle", "towho", "newsType","fwfs" });
			String newsTit = map.get("newstitle");
			request.setAttribute("newscont", clob.getSubString(1L, (int) clob
					.length()));
			request.setAttribute("content1", clob.getSubString(1L, (int) clob
					.length()));
			request.setAttribute("newstit", newsTit);
			request.setAttribute("isModi", "yes");
			request.setAttribute("newsId", newsId);
			request.setAttribute("map", map);
		} else {
			request.setAttribute("newscont", "");
			request.setAttribute("newstit", "");
			request.setAttribute("isModi", "no");
			request.setAttribute("newsId", "");
		}
		msgForm.setXmdm(tagId);
		request.setAttribute("towho", "");
		request.setAttribute("modList", dao.getModelList());
		String blog=request.getParameter("message");
		if(!Base.isNull(blog)){
			String message="true".equalsIgnoreCase(blog)? "�����ɹ�!" : "����ʧ��!";
			request.setAttribute("message", message);
		}
		return mapping.findForward("success");
	}

	private ActionForward newsContent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		User user = getUser(request);
		String tagId = request.getParameter("newsId");
		String[] tit = new String[] { "newsid", "newstitle", "newspart",
				"pubtime", "puber", "fbrbmmc", "newscont","tablename","newslls" };
		String sql = "select d.newsid,d.newstitle,(select gnmkmc from gnmkdmb where gnmkdm=d.newspart )newspart,d.pubtime,(select xm from yhb c where d.puber=c.yhm)puber,(select b.bmmc from zxbz_xxbmdm b left join yhb a on (a.szbm=b.bmdm) where a.yhm = d.puber) fbrbmmc,d.newscont,d.tablename,nvl(d.newslls,0)newslls from "
				+ "newscontent d  where d.newsid=?";
		if (xxdm.equals(Globals.XXDM_CSMZZYJSXY)) {// ��ɳ����
			sql = "select newsid,newstitle,nvl(newslls,0)newslls,(select gnmkmc from gnmkdmb where gnmkdm=newspart )newspart,substr(pubtime,0,10)pubtime,puber,newscont,xm,bmmc,tablename from "
					+ "view_newsForCsmz where newsid=?";
			tit = new String[] { "newsid", "newstitle", "newspart", "pubtime",
					"puber", "newscont", "xm", "bmmc","tablename","newslls" };
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {// ���ս���
			sql = "select newsid,newstitle,nvl(newslls,0)newslls,tablename,(select gnmkmc from gnmkdmb where gnmkdm=newspart )newspart,pubtime,(select xm from yhb where yhm=puber)puber,newscont from "
					+ "newscontent where newsid=?";
			tit = new String[] { "newsid", "newstitle", "newspart",
					"pubtime", "puber", "newscont","tablename","newslls" };
		}
		String updateSql = "update newscontent set tablename=? where newsid=?";
		String[] rs = dao.getOneRs(sql, new String[] { tagId }, tit);
		if(null==rs[rs.length-2]||"".equals(rs[rs.length-2])){
			rs[rs.length-2]="xg_tzgglljlb_1";
			dao.runUpdate(updateSql, new String[]{rs[rs.length-2],tagId});
		}
		
		String tzllSql = "select count(1) tzlls from "+rs[rs.length-2]+" where newsid=?";
		String tzlls = dao.getOneRs(tzllSql, new String[]{tagId}, "tzlls");
		String llsSql = "select count(1) lls from "+rs[rs.length-2]+" where yhm=? and newsid=?";
		String llrSql=null;
		String lls = dao.getOneRs(llsSql, new String[]{user.getUserName(),tagId}, "lls");
		String sumLlsSql = "select count(1) lls from "+rs[rs.length-2];
		String sumLls = dao.getOneRs(sumLlsSql, new String[]{}, "lls");
		rs = (rs == null) ? new String[tit.length] : rs;
		String lljlid = UniqID.getInstance().getUniqIDHash();
		String llsj=GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss");
		
		if("0".equals(lls)){
			//�ж�֪ͨ������͵�ǰ�����Ա��¼���������Ƿ񳬳����ޣ�Ǩ�Ƶ�ǰ֪ͨ������������±�
			if(Integer.parseInt(tzlls)>50000&&Integer.parseInt(sumLls)>100000){
				String newtableSql="select substr(tablename,0,length(tablename)-1)||(to_number(substr(tablename,length(tablename),length(tablename)))+1) tablename from(select max(tablename) tablename from newscontent)";
				String tablename = dao.getOneRs(newtableSql, new String[]{}, "tablename");
				String removeSql =" create table "+tablename+" as (select * from "+rs[rs.length-2]+"  where newsid='"+tagId+"')";
				dao.runUpdate(removeSql, new String[]{});
				dao.runDelete("delete from "+rs[rs.length-2]+" where newsid=?", new String[]{tagId});
				dao.runUpdate(updateSql, new String[]{tablename,tagId});
				rs[tit.length-2]=tablename;
				
			}
			rs[tit.length-1]=String.valueOf(Integer.parseInt(tzlls)+1);
			llrSql = "insert into "+rs[tit.length-2]+" (lljlid,yhm,llsj,newsid,ip,yhlx) values(?,?,?,?,?,?)";
			dao.runUpdate(llrSql, new String[]{lljlid,user.getUserName(),llsj,tagId,"",user.getUserType()});
		}
		else{
			rs[tit.length-1]=String.valueOf(Integer.parseInt(tzlls));
			llrSql = "update "+rs[tit.length-2]+" set llsj=? where yhm=? and yhlx=? and newsid=?";
			dao.runUpdate(llrSql, new String[]{llsj,user.getUserName(),user.getUserType(),tagId});
		}
		for (int i = 0; i < tit.length; i++) {
			request.setAttribute(tit[i], isNull(rs[i]) ? " " : rs[i]);
		}
		CLOB clob = dao.getOneClob(sql, new String[] { tagId }, "newscont");
		String msg = clob.getSubString(1L, (int) clob.length());
		// msg = msg.replace("target=_blank", "");
		request.setAttribute("newscont", msg);
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("success");
	}

	/**
	 * ����������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward moreNews(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		BasicService basicService = new BasicService();
		DAO dao = DAO.getInstance();
		CommanForm msgForm = (CommanForm) form;

		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String newspart = request.getParameter("newspart");
		String nspName = msgForm.getNspName();
		StringBuilder mkmc = new StringBuilder();
		String[] inputStr = new String[] {};
		if ("stu".equalsIgnoreCase(userType)) {
			mkmc
					.append("select b.gnmkdm dm,b.gnmkmc mc from gnmkdmb b where exists");
			mkmc
					.append("(select 1 from yhzqxb a where zdm='6727' and a.gnmkdm=b.gnmkdm and a.gnmkdm  like 'N__' )order by gnmkdm");
			inputStr = new String[] {};
		} else {
			mkmc
					.append("select b.gnmkdm dm,b.gnmkmc mc from gnmkdmb b where exists");
			mkmc.append("(select 1 from yhqxb a where a.yhm like ? ");
			mkmc
					.append(" and a.gnmkdm like 'N__' and a.gnmkdm=b.gnmkdm)  order by gnmkdm ");
			inputStr = new String[] { userName };
		}

		// ����û���Ȩ�޷���ģ�����ʹ���
		List<HashMap<String, String>> list = dao.getList(mkmc.toString(),
				inputStr, new String[] { "dm", "mc" });
		String[] en = new String[list.size()];// ģ��dm
		String[] cn = new String[list.size()];// ģ��mc

		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> hashMap = list.get(i);
			en[i] = hashMap.get("dm");
			cn[i] = hashMap.get("mc");
		}

		StringBuilder newsStr = new StringBuilder();
		newsStr
				.append("select rownum r,a.* from newscontent a  where newspart= ?");
		newspart = (newspart == null || "".equalsIgnoreCase(newspart)) ? list
				.get(0).get("dm") : newspart;
		String[] inPutList = { newspart };
		String[] colList = { "newsid", "newstitle", "pubtime" };
		if ("stu".equalsIgnoreCase(userType)) {
			newsStr.append(" and (towho='4' or towho='1')");
		} else if ("xy".equalsIgnoreCase(userType)) {
			newsStr.append(" and (towho='3' or towho='1')");
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			newsStr.append(" and (towho='2' or towho='1')");
		}
		newsStr.append(" order by pubtime desc ");
		ArrayList<String[]> newsList = CommonQueryDAO.commonQuery(newsStr
				.toString(), "", inPutList, colList, msgForm);
		HashMap<String, String> hashMap = dao.getMap(
				"select gnmkmc from gnmkdmb  where gnmkdm= ?", inPutList,
				new String[] { "gnmkmc" });
		nspName = hashMap.get("gnmkmc");
		// ��ѡ�
		request.setAttribute("newspart", newspart);
		request.setAttribute("nspName", nspName);// ѡ�����
		request.setAttribute("rs", newsList);
		if (newsList != null) {
			request.setAttribute("rsNum", newsList.size());
		}
		request.setAttribute("topTr", basicService.getTopTr("newscontent",
				new String[] { "����", "����ʱ��" }));
		request.setAttribute("mkmc", list);
		return mapping.findForward("success");
	}

	/**
	 * ��ҳ����ͼƬ��ʾ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward newsPigManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicService basicService=new BasicService();
		DAO dao = DAO.getInstance();
		CommanForm myForm = (CommanForm) form;
		MakeQuery makeQuery=new MakeQuery();
		String doType=request.getParameter("doType");
		if("save".equalsIgnoreCase(doType)){
			String[]picArr=myForm.getPicArr();
			String[]xssxArr=myForm.getXssxArr();
			String[] sql=new String[picArr.length+1];
			sql[0]=" update xg_sysz_tpszb set xssx='none' where   " ;
			int m=0;
			for(int i=0;i<picArr.length;i++){
				
					if(m==0){
						sql[0]+="   xssx='"+xssxArr[i]+"' ";
						m++;
					}else{
						sql[0]+=" or  xssx='"+xssxArr[i]+"' ";
						
					}
				
				sql[i+1]="update xg_sysz_tpszb set xssx='"+xssxArr[i]+"' where picid='"+picArr[i]+"' ";
				
			}
			dao.runBatch(sql);
			boolean result=true;
			request.setAttribute("result", result);
			doType="query";
			
		}
		
		if("del".equalsIgnoreCase(doType)){
			List<String>inputV=new ArrayList<String>();
			StringBuilder delSql=new StringBuilder();
			String[]primary_key=myForm.getPrimary_key();
			delSql.append(" delete from xg_sysz_tpszb where 1=1 ");
			for(int i=0;i<primary_key.length;i++){
				if(i==0){
					delSql.append(" and (");
				}else{
					delSql.append(" or ");
				}
				delSql.append(" newsid= ? ");
				inputV.add(primary_key[i]);
			}
			delSql.append(" ) ");
			boolean result=dao.runUpdate(delSql.toString(),inputV.toArray(new String[]{}));
			request.setAttribute("result", result);
			doType="query";
		}
		
		if("query".equalsIgnoreCase(doType)){
			String[]colLikeList={"newsTitle"};
			makeQuery.makeQuery(new String[]{}, colLikeList, myForm);
			String query="";
			if(!myForm.getKssj().equalsIgnoreCase("")){
				query+=" and pubtime>='"+myForm.getKssj()+"'";
			}
			if(!myForm.getJssj().equalsIgnoreCase("")){
				 query+=" and pubtime<='"+myForm.getJssj()+"'";
				
			}
			
			String xwSql=" select rowNum r,a.* from(select a.*,b.gnmkmc from(select newspart,newsid,newstitle,pubtime,count(1)rowspan from xg_sysz_tpszb group by newspart,newsid,newstitle,pubtime   order by pubtime desc)a,gnmkdmb b where a.newspart=b.gnmkdm)a ";
			String sql = " select a.*,b.rowspan from ";
			sql+=" (select picid,newsid,newstitle,newscont,pubtime,xssx from xg_sysz_tpszb order by xssx asc)a, ";
			sql+=" (select newsid,newstitle,pubtime,count(1)rowspan from xg_sysz_tpszb group by newsid,newstitle,pubtime)b where a.newsid=b.newsid  ";
			
			ArrayList<HashMap<String,String>>rs=(ArrayList<HashMap<String,String>>)dao.getList(sql, new String[] {}, new String[] {"picid",  "newsid", "newstitle",
					"pubtime","newscont","xssx","rowspan" });
			ArrayList xwrs=(ArrayList)CommonQueryDAO.commonQuery(xwSql, makeQuery.getQueryString()+query, makeQuery.getInputList(), new String[] {"newsId","gnmkmc", "newstitle",
					"pubtime","rowspan"}, myForm);
			String[]dm={"1","2","3","4","5","none"};
			String[]mc={"��һ��","�ڶ���","������","������","������","����ʾ"};
			List<HashMap<String,String>>xssxList=dao.arrayToList(dm, mc);
			request.setAttribute("xssxList", xssxList);
			request.setAttribute("rs", rs);
			request.setAttribute("xwrs", xwrs);
			
		}
		request.setAttribute("topTr", basicService.getTopTr("xg_sysz_tpszb",new String[]{"����ģ��","���ű���","����ʱ��","ͼƬ","��ʾѭ��"}));
		request.setAttribute("path", "newsPigManage.do?method=newsPigManage");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		return mapping.findForward("success");
	}
	
	/**
	 * ��ҳ����ͼƬ��ʾ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward newsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session=request.getSession();
		CommanForm myForm = (CommanForm) form;
		BasicService basicService=new BasicService();
		String userName=session.getAttribute("userName").toString();
		String userType=session.getAttribute("userType").toString();
		String gnmk=request.getParameter("gnmk");
		String mkmc=request.getParameter("mkmc");
		
		NewsModel model = new NewsModel();
		
		model.setPubKssj(myForm.getKssj());
		model.setPubJssj(myForm.getJssj());
		model.setNewsTitle(myForm.getNewsTitle());
		model.setPuber(myForm.getPuber());
		model.setTagId(gnmk);
		model.setPages(myForm.getPages());
		User user = new User();
		user.setUserType(userType);
		NewsService service = new NewsService();
		
		List<HashMap<String, String>> rs = service.getNews(model,user);
		request.setAttribute("news", rs);
		request.setAttribute("gnmk", gnmk);
		request.setAttribute("mkmc", mkmc);
		request.setAttribute("path", "newsInfo.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("mkList", getMkList(userName,userType));
		return mapping.findForward("newsInfo");
	}
	
	public List<HashMap<String,String>> getMkList(String userName,String userType){
		DAO dao = DAO.getInstance();
		StringBuilder sql=new StringBuilder(); 
		String[] input=new String[] {userName};
		if ("stu".equalsIgnoreCase(userType)) {
			sql.append("select b.gnmkdm dm,b.gnmkmc mc from gnmkdmb b where exists");
			sql.append("(select 1 from yhzqxb a where zdm='6727' and a.gnmkdm=b.gnmkdm and a.gnmkdm  like 'N__' ) and gnmkdm!='N01' order by gnmkdm");
			input=new String[] {};
		} else {
			sql.append("select b.gnmkdm dm,b.gnmkmc mc from gnmkdmb b where exists");
			sql.append("(select 1 from yhqxb a where a.yhm like ? ");
			sql.append(" and a.gnmkdm like 'N__' and a.gnmkdm=b.gnmkdm) and gnmkdm!='N01' order by gnmkdm ");
		}
		return dao.getList(sql.toString(),input , new String[] {"dm","mc"});
	}
	
	public List<HashMap<String,String>> getNewsInfo(String userName,String userType){
		DAO dao = DAO.getInstance();
		String sql = " select b.newsid,a.newstitle,a.newscont ,b.towho from (select newsid,newstitle,newscont,newspart,xssx from xg_sysz_tpszb a, ";
		if("stu".equalsIgnoreCase(userType)){
			sql+=" (select a.gnmkdm dm,a.gnmkmc mc from yhzqxb b,gnmkdmb a  where zdm='6727' and a.gnmkdm=b.gnmkdm and a.gnmkdm  like 'N__' ) ";
		}else{
			sql+=" ( select b.gnmkdm dm,b.gnmkmc mc from yhqxb a,gnmkdmb b where a.gnmkdm=b.gnmkdm and b.gnmkdm like 'N__' and a.yhm='"+userName+"' order by b.gnmkdm)";
		}
			
		sql+=" b where a.newspart=b.dm)  a,newscontent b where a.newsid=b.newssign and a.xssx<>'none'";
		StringBuilder sb=new StringBuilder();
		if ("stu".equalsIgnoreCase(userType)) {
			sql+=" and (b.towho='4' or b.towho='1')";
		} else if ("xy".equalsIgnoreCase(userType)) {
			sql+=" and (b.towho='3' or b.towho='1')";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			sql+=" and (b.towho='2' or b.towho='1')";
		}
		sql+=" order by a.xssx asc ";
		
		return dao.getList(sql+sb.toString(), new String[] {}, new String[] {"newsid","newstitle","newscont"});
	}
	
	public boolean checkTpxssx(String[]tpId,String[]xssx){
		DAO dao = DAO.getInstance();
		StringBuilder xs=new StringBuilder();
		StringBuilder picid=new StringBuilder();
		xs.append(" and (  ");
		boolean blog=false;
		for(int i=0;i<tpId.length;i++){
			picid.append(" and picid<> '"+tpId[i]+"' ");
			if(i==0){
				blog=true;
				xs.append("  xssx='"+xssx[i]+"' ");
			} else {
				blog=true;
				xs.append(" or  xssx='"+xssx[i]+"' ");
			}
		}
		if(!blog){
			xs.append(" 1=1 ");
		}
		xs.append(")");
		
		String sql=" select count(1)num from xg_sysz_tpszb where 1=1  "+picid.toString()+xs.toString();
		String num= dao.getOneRs(sql, new String[]{}, "num");
		if(num.equalsIgnoreCase("0")){
			return true;
		}else{
			return false;
		}
	}
	
	public String[] saveNewPic(String sContent,String puber,String ssmk,String newsTitle){
		String pigStr = sContent;

		int first = pigStr.indexOf("UserFiles/Image/");
		int last=0;
		
		int m = 0;
		while (first != -1 ) {
			first = pigStr.indexOf("UserFiles/Image/");
			if(first!=-1){
			pigStr=pigStr.substring(first,pigStr.length());
			last=pigStr.substring(0, pigStr.length()).indexOf(">");
			pigStr=pigStr.substring(last,pigStr.length());
			m++;
			}
		}
		String[] pic = new String[m];
		pigStr = sContent;
		
		first = pigStr.indexOf("UserFiles/Image/");
		if (first != -1) {
			pigStr = pigStr.substring(first, pigStr.length());
		}
		last=pigStr.substring(0, pigStr.length()).indexOf(">");
		for (int i = 0; i < m; i++) {
			first=0;
			StringBuilder sb=new StringBuilder();
			sb.append(" insert into xg_sysz_tpszb(picid,newspart,newsid,newstitle,newscont,xssx) ");
			sb.append(" (select s_news_id.nextval,newspart,newssign,newstitle,'"+pigStr.substring(first, last-1) +"','none' from newscontent where puber='");
			sb.append(puber);
			sb.append("' and newspart='");
			sb.append(ssmk);
			sb.append("' and newstitle='");
			sb.append(newsTitle);
			sb.append("') ");
			pic[i]=sb.toString();
			pigStr=pigStr.substring(last+1,pigStr.length());
			first = pigStr.indexOf("UserFiles/Image/");
			if(first!=-1){
				pigStr=pigStr.substring(first,pigStr.length());
			}
			last=pigStr.substring(0, pigStr.length()).indexOf(">");
		}
		return pic;
	}
	
}
