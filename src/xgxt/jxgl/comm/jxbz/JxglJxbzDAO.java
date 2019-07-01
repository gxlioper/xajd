package xgxt.jxgl.comm.jxbz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mortbay.html.Page;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.jxgl.comm.JxglCommDAO;
import xgxt.jxgl.comm.JxglCommForm;
import xgxt.pjpy.comm.pjpy.PjpyCommDAO;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.mypj.PjpyMypjForm;
import xgxt.pjpy.comm.pjpy.pjlc.xmsb.PjpyXmsbForm;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ѵ����_��ѵ����_DAO��
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

public class JxglJxbzDAO extends JxglCommDAO {

	/**
	 * ��þ�ѵ���Ƶȼ�
	 * 
	 * @author ΰ�����
	 */
	public String getBzdj(String bzlx) {

		StringBuilder sql = new StringBuilder();
		
		sql.append("select ");
		sql.append("max".equalsIgnoreCase(bzlx) ? "max" : "min");
		sql.append("(jzdm) djdm from jxjzdj");
		// ����ֵ
		String[] inPutList = new String[] {};

		DAO dao = DAO.getInstance();
		String bzdj = dao.getOneRs(sql.toString(), inPutList, "djdm");

		return bzdj;
	}

	/**
	 * ��þ�ѵ�����б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getJxbzList(JxglJxbzForm model,
			User user) {

		// ��ǰѧ��
		String xn = Base.currXn;

		StringBuilder sql = new StringBuilder();

		sql.append("select a.bzdm, a.bzmc, a.bzdj, a.sjdm, a.jsmc, a.jgmc, nvl(b.num,1) num ");
		sql.append("from (select bzdm, bzmc, bzdj, sjdm, jsdm jsmc, jgbh jgmc ");
		sql.append("from jxbzdmb ");
		sql.append("where xn = ?) a ");
		sql.append("left join (select sjdm bzdm, count(1) num from jxbzdmb group by sjdm) b ");
		sql.append("on a.bzdm = b.bzdm ");
		sql.append("order by bzdj, bzdm ");
		
		// ����ֵ
		String[] inPutList = new String[] { xn };
		// ���ֵ
		String[] colList = new String[] { "bzdm", "bzmc", "bzdj", "sjdm",
				"jsmc", "jgmc","num" };

		return getRsList("", "", inPutList, colList, sql.toString());
	}
	
	/**
	 * ��ñ��������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getJxbzInfo(JxglJxbzForm model,
			User user) {

		JxglCommForm jxszModel = JxglJxbzForm.jxglCommForm;
		// ���ż���
		String bzdj = model.getBzdj();
		// ���ż���
		String bzdm = model.getBzdm();
		// �ϼ�����
		String sjdm = model.getSjdm();
		// ѧ��
		String xn = Base.currXn;
		// ��������
		String czlx = model.getCzlx();
		
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("sjdm", sjdm);
		map.put("bzdj", bzdj);
		map.put("xn", xn);

		if ("self".equalsIgnoreCase(czlx) || "edit".equalsIgnoreCase(czlx)) {
			String tableName = "jxbzdmb";
			String pk = "bzdm";
			String pkValue = bzdm;
			String[] colList = new String[] { "xn", "bzdm", "bzmc", "bzdj",
					"jsdm", "jgbh" };
			map.putAll(getRsInfo(tableName, pk, pkValue, colList));
		}

		return map;
	}

	/**
	 * ����ۺϷ�ά���б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getBjfpList(JxglJxbzForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		String xn = model.getXn();
		String nj = model.getNj().trim();
		String xydm = model.getXydm().trim();
		String zydm = model.getZydm().trim();
		String bjdm = model.getBjdm().trim();
		
		StringBuilder tableSql = new StringBuilder();
		tableSql.append("(");
		tableSql.append("select a.bjdm pk,a.nj,a.xymc,a.zymc,a.bjmc from view_njxyzybj a ");
		tableSql.append("where not exists (select 1 from jxbzdmb b  ");
		tableSql.append("where b.xn = ? and a.bjdm = b.bzdm) ");
		tableSql.append(Base.isNull(nj)?"":" and a.nj = '"+nj+"' ");
		tableSql.append(Base.isNull(zydm)?"":" and a.zydm = '"+zydm+"' ");
		tableSql.append(Base.isNull(xydm)?"":" and a.xydm = '"+xydm+"' ");
		tableSql.append(Base.isNull(bjdm)?"":" and a.bjdm = '"+bjdm+"' ");
		tableSql.append(")");
		
		SearchService searchService = new SearchService();
		
		// Ȩ�޿���
		HashMap<String, String> notCtrlStatus = new HashMap<String, String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTjByUser = searchService.getSearchTjByUser("a", user);

		String query = " where 1 = 1 ";
		query += searchTjByUser;
		query += " order by pk ";
		
		return getRsArrList(tableSql.toString(), query, new String[] { xn },
				new String[] { "pk", "nj", "xymc", "zymc", "bjmc" }, "", model);
	}
	
	/**
	 * ��ñ�������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String[] getBzmc(String[] bzdm) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" select bjmc from view_njxyzybj ");
		sql.append(" where 1 = 1 ");

		if (bzdm != null && bzdm.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < bzdm.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" bjdm = '" + bzdm[i] + "' ");
			}
			sql.append(" )");
		}

		DAO dao = DAO.getInstance();
		String[] bzmc = dao.getRs(sql.toString(), new String[] {}, "bjmc");

		return bzmc;
	}
	
	/**
	 * ɾ���¼�����
	 * 
	 * @author ΰ�����
	 */
	public Boolean delXjbz() {

		StringBuilder sql = new StringBuilder();
		sql.append("delete from jxbzdmb c ");
		sql.append("where not exists (select 1 ");
		sql.append("from (select * from jxbzdmb a ");
		sql.append("where exists (select 1 from jxbzdmb b where b.bzdm = a.sjdm) ");
		sql.append("or a.sjdm is null) d ");
		sql.append("where c.bzdm = d.bzdm) ");

		DAO dao = DAO.getInstance();
		
		boolean flag = false;
		
		try {
			flag = dao.runUpdate(sql.toString(),new String[]{});
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			flag = false;
		}
		
		return flag ;

	}
}
