package xgxt.xsgygl.zjcm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.write.Label;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.GyglTyDAO;
import xgxt.xsgygl.GyglTyForm;

public class ZjcmGyglDAO extends GyglTyDAO {

	/**
	 * ��ȡϵͳ������Ϣ
	 * 
	 * @author luo
	 */
	public HashMap<String, String> getXtZsInfo() {
		DAO dao = DAO.getInstance();

		String sql = "select qsrq, xqzs from xtszb where rownum = 1";
		HashMap<String, String> map = dao.getMap(sql, new String[] {},
				new String[] { "qsrq", "xqzs" });
		return map;
	}

	/**
	 * �ж����ݿ��д洢���ܴ���Ŀ��ϵͳ���ñ��Ƿ�һ��
	 * 
	 * @author luo
	 */
	public Boolean isZcYz(String xqzs, String xn, String xq) {
		DAO dao = DAO.getInstance();
		boolean flag = false;

		String sql = "select count(zc) zzc from gygl_xnxqzsb where xn = ? and xq = ?";
		String zzc = dao.getOneRs(sql, new String[] { xn, xq }, "zzc");

		if (!xqzs.equalsIgnoreCase(zzc)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * �ж����ݿ����������ȼ��Ƿ�ά��
	 * 
	 * @author luo
	 */
	public Boolean isWsjcdj() {
		DAO dao = DAO.getInstance();
		boolean flag = false;

		String sql = "select count(*) num from zjcm_wsjcdjb";
		String num = dao.getOneRs(sql, new String[] {}, "num");

		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * �޸���������
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public Boolean updateWsjcf(String fs, String pkValue,
			HttpServletRequest request) throws Exception {

		String tableName = "zjcm_wsjcb";
		String[] columns = new String[] { "fs" };
		String[] values = new String[] { fs };
		String primaryKey = "xn||xq||jcqs||jcsj";

		return StandardOperation.update(tableName, columns, values, primaryKey,
				pkValue, request);
	}

	/**
	 * ����������б�
	 * 
	 * @author luo
	 */
	public List<HashMap<String, String>> getZqsList(ZjcmGyglModel model) {

		DAO dao = DAO.getInstance();

		String needNj = model.getNeedNj();
		String tableName = ("no".equalsIgnoreCase(needNj)) ? "view_zjcm_wsjctj_nonj"
				: "view_zjcm_wsjctj_nj";

		String[] colList = null;
		StringBuffer sql = new StringBuffer();
		if ("no".equalsIgnoreCase(needNj)) {
			sql
					.append(" select distinct (a.xydm), a.xymc, nvl(b.num,0) num from view_njxyzybj a");
			sql
					.append(" left join (select count(distinct(ssbh)) num,xydm,xymc from ");
			sql.append(tableName);
			sql.append(" group by xydm,xymc order by xydm) b");
			sql.append(" on a.xydm = b.xydm order by xydm");

			colList = new String[] { "xydm", "xymc", "num" };
		} else {
			// sql.append(" select distinct (a.xydm), a.xymc,a.nj, nvl(b.num,0)
			// num from view_njxyzybj a");
			// sql.append(" left join (select count(distinct(ssbh))
			// num,nj,xydm,xymc from ");
			// sql.append(tableName);
			// sql.append(" group by nj,xydm,xymc order by xydm,nj) b");
			// sql.append(" on a.xydm = b.xydm and a.nj = b.nj order by
			// xydm,nj");

			sql.append(" select * from (select a.*, nvl(b.num, 0) num");
			sql.append(" from (select distinct (a.xydm), a.xymc, '��ѧ����ס' nj");
			sql
					.append(" from view_njxyzybj a union all select distinct (a.xydm), ");
			sql.append(" a.xymc, to_char(a.nj) from view_njxyzybj a) a");
			sql
					.append(" left join (select count(distinct(ssbh)) num, nj, xydm, xymc");
			sql.append(" from " + tableName + " group by nj, xydm, xymc");
			sql
					.append(" order by xydm, nj) b on a.xydm = b.xydm and a.nj = to_char(b.nj))");
			sql.append(" order by xydm, nj");

			colList = new String[] { "xydm", "xymc", "nj", "num" };
		}

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, colList);

		return list;
	}

	/**
	 * ����������ͳ���б�
	 * 
	 * @author luo
	 */
	public List<HashMap<String, String>> getTjList(ZjcmGyglModel model) {

		DAO dao = DAO.getInstance();

		String xn = model.getXn();
		String xq = model.getXq();
		String needNj = model.getNeedNj();
		String bblx = model.getBblx()
				.substring(0, model.getBblx().length() - 1);
		String tableName = ("no".equalsIgnoreCase(needNj)) ? "view_zjcm_wsjctj_nonj"
				: "view_zjcm_wsjctj_nj";

		String[] colList = null;
		StringBuffer sql = new StringBuffer();
		if ("no".equalsIgnoreCase(needNj)) {
			sql.append(" select xydm, xymc, count(ssbh) num from ");
			sql.append(tableName);
			sql.append(" where xn=? and xq=? and mc like ?||'%'");
			sql.append(" group by xydm, xymc");

			colList = new String[] { "xydm", "xymc", "num" };
		} else {
			sql.append(" select xydm, xymc,nj, count(ssbh) num from ");
			sql.append(tableName);
			sql.append(" where xn=? and xq=? and mc like ?||'%'");
			sql.append(" group by nj,xydm, xymc");

			colList = new String[] { "xydm", "xymc", "nj", "num" };
		}

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, bblx }, colList);

		return list;
	}

	/**
	 * ��ù�Ԣ��������ͳ���б�
	 * 
	 * @author luo
	 */
	public ArrayList<String[]> getBxTjSsList(GyglTyForm model) {

		DAO dao = DAO.getInstance();

		ArrayList<String> outputValue = new ArrayList<String>();

		String tjfw = model.getTjfw();

		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(getBxQuery(model));
		// ������Χ
		StringBuffer tjfwSql = new StringBuffer();
		// ���鷶Χ
		StringBuffer groupSql = new StringBuffer();
		groupSql.append(" group by ");
		String orderBy = "";

		orderBy = getBxFw(model, outputValue, tjfwSql, groupSql, orderBy);

		outputValue.add("bxrs");
		outputValue.add("ywx");
		outputValue.add("wwx");
		outputValue.add("ypj");
		outputValue.add("wpj");

		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,nvl(b.ywx, '0') ywx,");
		sql.append(" (a.bxrs - nvl(b.ywx, '0')) wwx,");
		sql.append(" nvl(c.ypj, '0') ypj,");
		sql.append(" (a.bxrs - nvl(c.ypj, '0')) wpj from (");
		// ===================ͳ�Ʊ�������==============================
		sql.append(" select count(1) bxrs ");
		sql.append(tjfwSql);
		sql.append(" from view_gygl_zjcm_gybx a ");
		sql.append(query);
		sql.append(groupSql);
		// ===================ͳ����ά������==============================
		sql.append(" ) a left join (");
		sql.append(" select count(1) ywx ");
		sql.append(tjfwSql);
		sql.append(" from view_gygl_zjcm_gybx a ");
		sql.append(query);
		sql.append(" and sfwg = '��' ");
		sql.append(groupSql);
		sql.append(" ) b ");
		if ("nj".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ���꼶��
			sql.append(" on a.nj = b.nj ");
		} else if ("xy".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ��ѧԺ��
			sql.append(" on a.xydm = b.xydm ");
		} else if ("zy".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ��רҵ��
			sql.append(" on a.zydm = b.zydm ");
		} else if ("bj".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ���༶��
			sql.append(" on a.bjdm = b.bjdm ");
		} else if ("ld".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ��¥����
			sql.append(" on a.lddm = b.lddm ");
		}
		// ===================ͳ������������==============================
		sql.append(" left join (");
		sql.append(" select count(1) ypj ");
		sql.append(tjfwSql);
		sql.append(" from view_gygl_zjcm_gybx a ");
		sql.append(query);
		sql.append(" and fwtd is not null ");
		sql.append(groupSql);
		sql.append(" ) c ");
		if ("nj".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ���꼶��
			sql.append(" on a.nj = c.nj ");
		} else if ("xy".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ��ѧԺ��
			sql.append(" on a.xydm = c.xydm ");
		} else if ("zy".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ��רҵ��
			sql.append(" on a.zydm = c.zydm ");
		} else if ("bj".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ���༶��
			sql.append(" on a.bjdm = c.bjdm ");
		} else if ("ld".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ��¥����
			sql.append(" on a.lddm = c.lddm ");
		}

		sql.append(orderBy);

		ArrayList<String[]> list = dao.rsToVator(sql.toString(),
				new String[] {}, outputValue.toArray(new String[] {}));

		return list;
	}

	/**
	 * ��ù�Ԣ���޲���ͳ���б�
	 * 
	 * @author luo
	 */
	public ArrayList<String[]> getBxTjClList(GyglTyForm model) {
		DAO dao = DAO.getInstance();

		ArrayList<String> outputValue = new ArrayList<String>();

		// ������Χ
		StringBuffer tjfwSql = new StringBuffer();
		// ���鷶Χ
		StringBuffer groupSql = new StringBuffer();
		groupSql.append(" group by lxmc");

		String orderBy = "";
		getBxFw(model, outputValue, tjfwSql, groupSql, orderBy);

		// ͳ�Ʒ�Χ
		String tjfw = model.getTjfw();
		// ��������
		String tjcllx = model.getTjcllx();
		// ��������
		String tjclmc = model.getTjclmc();
		// ��ѯ����
		StringBuilder query = new StringBuilder();
		query.append(getBxQuery(model));

		StringBuffer sql = new StringBuffer();
		sql.append("select sum(clsl) num,sum(zj) zj ");
		sql.append(tjfwSql);
		if ("cllx".equalsIgnoreCase(tjfw)) {
			outputValue.add("lxmc");
			sql.append(",b.cllx,b.lxmc ");
		} else {
			outputValue.add("clmc");
			outputValue.add("lxmc");
			sql.append(",b.clmc,b.lxmc ");
		}
		sql.append(" from view_gygl_zjcm_bxcl b,view_gygl_zjcm_gybx a ");
		sql.append(query);
		sql.append(" and a.xh = b.xh and a.bxsj = b.bxsj");
		sql.append(Base.isNull(tjcllx) ? "" : " and b.cllx = '" + tjcllx + "'");
		sql.append(Base.isNull(tjclmc) ? "" : " and b.clmc like '%" + tjclmc
				+ "%'");

		if ("cllx".equalsIgnoreCase(tjfw)) {
			groupSql.append(" ,cllx ");
			groupSql.append(" order by cllx ");
		} else {
			groupSql.append(" ,clmc ");
			groupSql.append(" order by clmc ");
		}
		sql.append(groupSql);

		outputValue.add("num");
		outputValue.add("zj");

		ArrayList<String[]> list = dao.rsToVator(sql.toString(),
				new String[] {}, outputValue.toArray(new String[] {}));

		return list;
	}

	/**
	 * @param model
	 * @param query
	 * @return
	 */
	private String getBxQuery(GyglTyForm model) {

		StringBuilder query = new StringBuilder();

		// �꼶
		String nj = model.getNj();
		// ѧԺ
		String xydm = model.getXydm();
		// רҵ
		String zydm = model.getZydm();
		// �༶
		String bjdm = model.getBjdm();
		// У��
		String xqdm = model.getXqdm();
		// ¥��
		String lddm = model.getLddm();
		// ����
		String cs = model.getCs();
		// ����
		String qsh = model.getQsh();
		// �Ƿ��շ�
		String sfsf = model.getSfsf();
		// �Ƿ��깤
		String sfwg = model.getSfwg();
		// ѧ��
		String xh = model.getXh();
		// ����
		String xm = model.getXm();
		// �Ա�
		String xb = model.getXb();
		// ��ʼʱ��
		String kssj = model.getQuerygreaterequal_bxsj();
		// ����ʱ��
		String jssj = model.getQuerylessequal_bxsj();

		query.append(" where 1 = 1 ");
		query.append(Base.isNull(nj) ? "" : " and a.nj = '" + nj + "'");
		query.append(Base.isNull(xb) ? "" : " and a.xb = '" + xb + "'");
		query.append(Base.isNull(xydm) ? "" : " and a.xydm = '" + xydm + "'");
		query.append(Base.isNull(zydm) ? "" : " and a.zydm = '" + zydm + "'");
		query.append(Base.isNull(bjdm) ? "" : " and a.bjdm = '" + bjdm + "'");
		query.append(Base.isNull(xqdm) ? "" : " and a.xqdm = '" + xqdm + "'");
		query.append(Base.isNull(lddm) ? "" : " and a.lddm = '" + lddm + "'");
		query.append(Base.isNull(cs) ? "" : " and a.cs = '" + cs + "'");
		query.append(Base.isNull(qsh) ? "" : " and a.qsh = '" + qsh + "'");
		query.append(Base.isNull(sfsf) ? "" : " and a.sfsf = '" + sfsf + "'");
		query.append(Base.isNull(sfwg) ? "" : " and a.sfwg = '" + sfwg + "'");
		query.append(Base.isNull(kssj) ? "" : " and a.bxsj >= '" + kssj + "'");
		query.append(Base.isNull(jssj) ? "" : " and a.bxsj <= '" + jssj + "'");
		query.append(Base.isNull(xh) ? "" : " and a.xh like '%" + xh + "%'");
		query.append(Base.isNull(xm) ? "" : " and a.xm like '%" + xm + "%'");

		return query.toString();
	}

	/**
	 * @param model
	 * @param outputValue
	 * @param tjfwSql
	 * @param groupSql
	 * @param orderBy
	 * @return
	 */
	private String getBxFw(GyglTyForm model, ArrayList<String> outputValue,
			StringBuffer tjfwSql, StringBuffer groupSql, String orderBy) {

		// ͳ�Ʒ�Χ
		String tjfw = model.getTjfw();
		// �꼶
		String nj = model.getNj();
		// ѧԺ
		String xydm = model.getXydm();
		// רҵ
		String zydm = model.getZydm();
		// �༶
		String bjdm = model.getBjdm();
		// У��
		String xqdm = model.getXqdm();
		// ¥��
		String lddm = model.getLddm();
		// ����
		String cs = model.getCs();
		// ����
		String qsh = model.getQsh();
		// ѧ��
		String xh = model.getXh();
		// ����
		String xm = model.getXm();
		// �Ա�
		String xb = model.getXb();
		
		if ("nj".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ���꼶��

			outputValue.add("nj");
			groupSql.append(" nj ");
			tjfwSql.append(",a.nj ");
			orderBy = " order by nj";

		} else if ("xy".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ��ѧԺ��

			outputValue.add("xymc");
			groupSql.append(" xydm,xymc ");
			tjfwSql.append(",a.xydm,a.xymc ");
			orderBy = " order by xydm";

		} else if ("zy".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ��רҵ��

			outputValue.add("zymc");
			groupSql.append(" zydm,zymc ");
			tjfwSql.append(",a.zydm,a.zymc ");
			orderBy = " order by zydm";

		} else if ("bj".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ���༶��

			outputValue.add("bjmc");
			groupSql.append(" bjdm,bjmc ");
			tjfwSql.append(",a.bjdm,a.bjmc ");
			orderBy = " order by bjdm";

		} else if ("ld".equalsIgnoreCase(tjfw)) {// ͳ�Ʒ�Χ��¥����

			outputValue.add("ldmc");
			groupSql.append(" lddm,ldmc ");
			tjfwSql.append(",a.lddm,nvl(a.ldmc,'��')ldmc ");
			orderBy = " order by lddm";

		}

		if (!Base.isNull(nj) && !"nj".equalsIgnoreCase(tjfw)) {
			// outputValue.add("zydm");
			outputValue.add("nj");
			tjfwSql.append(",a.nj ");
			groupSql.append(" ,nj ");
		}

		if (!Base.isNull(xydm) && !"xy".equalsIgnoreCase(tjfw)) {
			// outputValue.add("xydm");
			outputValue.add("xymc");
			tjfwSql.append(",a.xydm,a.xymc ");
			groupSql.append(" ,xydm,xymc ");
		}

		if (!Base.isNull(zydm) && !"zy".equalsIgnoreCase(tjfw)) {
			// outputValue.add("zydm");
			outputValue.add("zymc");
			tjfwSql.append(",a.zydm,a.zymc ");
			groupSql.append(" ,zydm,zymc ");
		}

		if (!Base.isNull(bjdm) && !"bj".equalsIgnoreCase(tjfw)) {
			// outputValue.add("bjdm");
			outputValue.add("bjmc");
			tjfwSql.append(",a.bjdm,a.bjmc ");
			groupSql.append(" ,bjdm,bjmc ");
		}

		if (!Base.isNull(xqdm)) {
			// outputValue.add("xqdm");
			outputValue.add("xqmc");
			tjfwSql.append(",a.xqdm,a.xqmc ");
			groupSql.append(" ,xqdm,xqmc ");
		}

		if (!Base.isNull(lddm) && !"ld".equalsIgnoreCase(tjfw)) {
			// outputValue.add("lddm");
			outputValue.add("ldmc");
			tjfwSql.append(",a.lddm,a.ldmc ");
			groupSql.append(" ,lddm,ldmc ");
		}

		if (!Base.isNull(cs)) {
			outputValue.add("cs");
			tjfwSql.append(",a.cs ");
			groupSql.append(" ,cs ");
		}

		if (!Base.isNull(qsh)) {
			outputValue.add("qsh");
			tjfwSql.append(",a.qsh ");
			groupSql.append(" ,qsh ");
		}

		if (!Base.isNull(xh) && !Base.isNull(xm)) {
			outputValue.add("xh");
			outputValue.add("xm");
			tjfwSql.append(",a.xh,a.xm ");
			groupSql.append(" ,a.xh,a.xm ");
		} else if (!Base.isNull(xh)) {
			outputValue.add("xh");
			outputValue.add("xm");
			tjfwSql.append(",a.xh,a.xm ");
			groupSql.append(" ,a.xh,a.xm ");
		} else if (!Base.isNull(xm)) {
			outputValue.add("xh");
			outputValue.add("xm");
			tjfwSql.append(",a.xh,a.xm ");
			groupSql.append(" ,a.xh,a.xm ");
		}

		return orderBy;
	}

	
	
	/**
	 * �������¼�루�б�
	 * 
	 * @author quph
	 */
	public ArrayList<String[]> getWsjcQueryList(String tableName,
			ZjcmGyglModel model, String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		DAO dao = DAO.getInstance();

		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String other_query = "";

		if (!Base.isNull(kssj)) {
			other_query += " and jcsj >='" + kssj + "'";
		}
		if (!Base.isNull(jssj)) {
			other_query += " and jcsj <='" + jssj + "'";
		}

		String[] queryList = new String[] { "xqdm", "lddm", "cs", "qsh", "xn",
				"xq" };
		String[] njxyzybjList = new String[] { "nj", "xydm", "zydm", "bjdm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, new String[] {}, model);
		String query = myQuery.getQueryString();
		String[] inputList = myQuery.getInputList();

		myQuery.makeQuery(njxyzybjList, new String[] {}, model);
		String njxyzybjQuery = myQuery.getQueryString();
		String[] njxyzybjInputList = myQuery.getInputList();

		String[] allInputList = StringUtils.joinStrArr(njxyzybjInputList,
				inputList);

		StringBuilder sql = new StringBuilder();

		sql.append("select count(*) count from (select * from (select a.* from ");
		sql.append(tableName);
		sql.append(" a where exists (select 1 from (select * from view_xszsxx ");
		sql.append(njxyzybjQuery);
		sql.append(")b where b.lddm=a.lddm and b.cs=a.cs and b.qsh=a.qsh))");
		sql.append(query);
		sql.append(other_query);
		sql.append(")");
		
		
		Class myClass = model.getClass();
		Pages pages = (Pages) myClass.getMethod("getPages", (Class[]) null)
				.invoke(model, (Object[]) null);
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(sql.toString(),
				allInputList, "count")));

		StringBuilder sb = new StringBuilder();
		
		sb.append("select * from (select a.*,rownum r from (select a.* from ");
		sb.append(tableName);
		sb.append(" a where exists (select 1 from (select * from view_xszsxx ");
		sb.append(njxyzybjQuery);
		sb.append(")b where b.lddm=a.lddm and b.cs=a.cs and b.qsh=a.qsh)) a");
		sb.append(query);
		sb.append(other_query);
		sb.append(") where r > ");
		sb.append(pages.getStart());
		sb.append(" and r <= ");
		sb.append((pages.getStart() + pages.getPageSize()));
		
		return dao.rsToVator(sb.toString(), allInputList, colList);
	}
	
	/**
	 * �������������
	 */
	public HashMap<String,String>getMaxTimes(GyglTyForm form){
		DAO dao =DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		if(!"".equalsIgnoreCase(form.getNj()) && null!=form.getNj()){
			sb.append(" and nj='"+form.getNj()+"'");
		}
		
		if(!"".equalsIgnoreCase(form.getXydm()) && null!=form.getXydm()){
			sb.append(" and xydm='"+form.getXydm()+"'");
		}
		
		if(!"".equalsIgnoreCase(form.getZydm()) && null!=form.getZydm()){
			sb.append(" and zydm='"+form.getZydm()+"'");
		}
		String sql=" select nvl(max(zcs), '0') zcs from (select nvl(max(zcs), '0') zcs from (select count(*) zcs, lddm, qsh from view_zjcm_wsjc ";
		sql+=" where xq = ?  and xn = ?  group by lddm, qsh) a, view_xszsxx b   where a.lddm = b.lddm  and a.qsh = b.qsh "+sb+"  group by a.lddm, a.qsh)";
		System.out.println(sql);
		return dao.getMap(sql, new String[]{form.getTjxq(),form.getTjxn()}, new String[]{"zcs"});
	}
	
	/**
	 * �������������ʱ������
	 * ���������Ϣ
	 */
	public List<HashMap<String,String>>getWsjcxx(GyglTyForm form){
		DAO dao =DAO.getInstance();
		String sql=" select a.lddm,a.qsh,a.fs,a.JCSJ,RANK()OVER(PARTITION BY SSBH  ORDER BY JCSJ) Rank from  view_zjcm_wsjc a where a.xn=? and a.xq=? ";
		return dao.getList(sql, new String[]{form.getTjxn(),form.getTjxq()}, new String[]{"lddm","qsh","jcsj","fs"});
	}
	
	/**
	 * ѧ��ס����Ϣ
	 */
	public List<HashMap<String,String>>getXszsxx(GyglTyForm form){
		DAO dao =DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		if(!"".equalsIgnoreCase(form.getNj()) && null!=form.getNj()){
			sb.append(" and nj='"+form.getNj()+"'");
		}
		
		if(!"".equalsIgnoreCase(form.getXydm()) && null!=form.getXydm()){
			sb.append(" and xydm='"+form.getXydm()+"'");
		}
		
		if(!"".equalsIgnoreCase(form.getZydm()) && null!=form.getZydm()){
			sb.append(" and zydm='"+form.getZydm()+"'");
		}
		String sql=" select * from view_xszsxx where 1=1 "+sb;
		return dao.getList(sql, new String[]{}, new String[]{"xh","xm","xb","xymc","nj","bjmc","sfzh","lddm","ldmc","qsh","cwh","sfbz","xqdm","lxdh"});
	}
	
}
