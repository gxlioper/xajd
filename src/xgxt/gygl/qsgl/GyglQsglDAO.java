package xgxt.gygl.qsgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.gygl.GyglCommDAO;
import xgxt.gygl.jbsz.GyglJbszForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��Ԣ����_���ҹ���_DAO��
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

public class GyglQsglDAO extends GyglCommDAO {

	/**
	 * ����Զ����䲿���б�(ѧԺ)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getZdfpXyList(GyglQsglForm model, User user,
			String[] colList, String query, String[] inPutList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		StringBuilder tableSql = new StringBuilder();

		tableSql.append("(select a.xydm, a.xymc,a.bmrs,a.fpqss,a.kzrcws,(a.bmrs-a.kzrcws) xfprs ");
		tableSql.append("from (select a.xydm,a.xymc,nvl(b.bmrs, 0) bmrs, ");
		tableSql.append("nvl(c.fpqss, 0) fpqss,nvl(c.kzrcws, 0) kzrcws from");

		tableSql.append("(select distinct a.xydm, a.xymc from view_njxyzybj a ");
		tableSql.append(" where 1 = 1 ");
		tableSql.append(query);
		tableSql.append("order by a.xydm) a ");

		tableSql.append("left join (select xydm, count(1) bmrs from ");
		tableSql.append("view_xsjbxx b group by xydm) b on a.xydm = b.xydm ");

		tableSql.append("left join (select c.bmdm xydm, count(1) fpqss, nvl(sum(c.cws),0) kzrcws ");
		tableSql.append("from (select c.bmdm, c.lddm, c.cs, c.qsh, d.cws ");
		tableSql.append("from xg_gygl_qsfpb c left join (select lddm, cs, qsh, count(1) cws ");
		tableSql.append("from xg_gygl_cwxxb d where ");
		tableSql.append("not exists(select 1 from xg_gygl_qtcwxxb e where d.lddm = e.lddm and d.cs=e.cs and d.qsh = e.qsh)");
		tableSql.append("group by lddm, cs, qsh) d on c.lddm = d.lddm ");
		tableSql.append("and c.cs = d.cs and c.qsh = d.qsh) c ");
		
		tableSql.append("group by c.bmdm) c on a.xydm = c.xydm) a ) ");

		return getRsArrList(tableSql.toString(), "", inPutList, colList, "",
				model);

	}

	/**
	 * ����Զ����䲿���б�(�꼶+ѧԺ)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getZdfpNjXyList(GyglQsglForm model, User user,
			String[] colList, String query, String[] inPutList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		StringBuilder tableSql = new StringBuilder();

		tableSql.append("(select a.nj,a.xydm, a.xymc,a.bmrs,a.fpqss,a.kzrcws,(a.bmrs-a.kzrcws) xfprs ");
		tableSql.append("from (select a.nj,a.xydm,a.xymc,nvl(b.bmrs, 0) bmrs, ");
		tableSql.append("nvl(c.fpqss, 0) fpqss,nvl(c.kzrcws, 0) kzrcws from");

		tableSql.append("(select distinct a.nj,a.xydm, a.xymc from view_njxyzybj a ");
		tableSql.append(" where 1 = 1 ");
		tableSql.append(query);
		tableSql.append("order by a.nj,a.xydm) a ");

		tableSql.append("left join (select b.nj,b.xydm, count(1) bmrs from ");
		tableSql.append("view_xsjbxx b group by b.nj,b.xydm) b ");
		tableSql.append("on a.xydm = b.xydm and a.nj = b.nj ");

		tableSql.append("left join (select c.nj,c.bmdm xydm, count(1) fpqss, nvl(sum(c.cws),0) kzrcws ");
		tableSql.append("from (select c.nj,c.bmdm, c.lddm, c.cs, c.qsh, d.cws ");
		tableSql.append("from xg_gygl_qsfpb c left join (select lddm, cs, qsh, count(1) cws ");
		tableSql.append("from xg_gygl_cwxxb d where ");
		tableSql.append("not exists(select 1 from xg_gygl_qtcwxxb e where d.lddm = e.lddm and d.cs=e.cs and d.qsh = e.qsh)");
		tableSql.append("group by lddm, cs, qsh) d on c.lddm = d.lddm ");
		tableSql.append("and c.cs = d.cs and c.qsh = d.qsh) c ");
		tableSql
				.append("group by c.nj,c.bmdm) c on a.xydm = c.xydm and a.nj = c.nj) a)");

		return getRsArrList(tableSql.toString(), "", inPutList, colList, "",
				model);

	}

	/**
	 * ����Զ����䲿���б�(�꼶+רҵ)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getZdfpNjZyList(GyglQsglForm model, User user,
			String[] colList, String query, String[] inPutList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		StringBuilder tableSql = new StringBuilder();

		tableSql.append("(select a.nj,a.xymc,a.zydm,a.zymc,a.bmrs,a.fpqss,a.kzrcws,(a.bmrs-a.kzrcws) xfprs ");
		tableSql.append("from (select a.nj,a.xymc,a.zydm,a.zymc,nvl(b.bmrs, 0) bmrs, ");
		tableSql.append("nvl(c.fpqss, 0) fpqss,nvl(c.kzrcws, 0) kzrcws from");

		tableSql.append("(select distinct a.nj,a.xydm,a.xymc,a.zydm,a.zymc from view_njxyzybj a ");
		tableSql.append(" where 1 = 1 ");
		tableSql.append(query);
		tableSql.append("order by a.nj,a.xydm,a.zydm) a ");

		tableSql.append("left join (select b.nj,b.zydm, count(1) bmrs from ");
		tableSql.append("view_xsjbxx b group by b.nj,b.zydm) b ");
		tableSql.append("on a.zydm = b.zydm and a.nj = b.nj ");

		tableSql.append("left join (select c.nj,c.bmdm zydm, count(1) fpqss, nvl(sum(c.cws),0) kzrcws ");
		tableSql.append("from (select c.nj,c.bmdm, c.lddm, c.cs, c.qsh, d.cws ");
		tableSql.append("from xg_gygl_qsfpb c left join (select lddm, cs, qsh, count(1) cws ");
		tableSql.append("from xg_gygl_cwxxb d where ");
		tableSql.append("not exists(select 1 from xg_gygl_qtcwxxb e where d.lddm = e.lddm and d.cs=e.cs and d.qsh = e.qsh)");
		tableSql.append("group by lddm, cs, qsh) d on c.lddm = d.lddm ");
		tableSql.append("and c.cs = d.cs and c.qsh = d.qsh) c ");
		tableSql
				.append("group by c.nj,c.bmdm) c on a.zydm = c.zydm and a.nj = c.nj) a )");

		return getRsArrList(tableSql.toString(), "", inPutList, colList, "",
				model);

	}

	/**
	 * ����Զ����䲿���б�(�༶)
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getZdfpBjList(GyglQsglForm model, User user,
			String[] colList, String query, String[] inPutList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		StringBuilder tableSql = new StringBuilder();

		tableSql.append("(select a.nj,a.xydm,a.xymc,a.zydm,a.zymc, ");
		tableSql.append("a.bjdm,a.bjmc,a.bmrs,a.fpqss,a.kzrcws,(a.bmrs-a.kzrcws) xfprs ");
		tableSql.append("from (select a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,nvl(b.bmrs, 0) bmrs, ");
		tableSql.append("nvl(c.fpqss, 0) fpqss,nvl(c.kzrcws, 0) kzrcws from");

		tableSql.append("(select a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc from view_njxyzybj a ");
		tableSql.append(" where 1 = 1 ");
		tableSql.append(query);
		tableSql.append("order by a.nj,a.xydm,a.zydm,a.bjdm) a ");

		tableSql.append("left join (select bjdm, count(1) bmrs from ");
		tableSql.append("view_xsjbxx b group by bjdm) b on a.bjdm = b.bjdm ");

		tableSql.append("left join (select c.bmdm bjdm, count(1) fpqss, nvl(sum(c.cws),0) kzrcws ");
		tableSql.append("from (select c.bmdm, c.lddm, c.cs, c.qsh, d.cws ");
		tableSql.append("from xg_gygl_qsfpb c left join (select lddm, cs, qsh, count(1) cws ");
		tableSql.append("from xg_gygl_cwxxb d where ");
		tableSql.append("not exists(select 1 from xg_gygl_qtcwxxb e where d.lddm = e.lddm and d.cs=e.cs and d.qsh = e.qsh)");
		tableSql.append("group by lddm, cs, qsh) d on c.lddm = d.lddm ");
		tableSql.append("and c.cs = d.cs and c.qsh = d.qsh) c ");
		tableSql.append("group by c.bmdm) c on a.bjdm = c.bjdm) a )");

		return getRsArrList(tableSql.toString(), "", inPutList, colList, "",
				model);

	}

	/**
	 * ��ÿɷ��������б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKfpQsList(GyglQsglForm model,User user) {

		GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
		// ����¥��
		String[] ld = model.getLd();
		// ����У��
		String[] xiaoqu = model.getXiaoqu();
		// ����԰��
		String[] yuanqu = model.getYuanqu();
		// �Ա��޶�
		String xb = model.getXb();
		// �ɷ��ס
		String kfhz = model.getKfhz();
		// ���䷽ʽ
		String fpfs = jbszModel.getFpfs();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.lddm, a.cs, a.qsh, a.xb,a.cws, ");
		sql.append(" (a.cws - nvl(bkzrcws, 0)) kzrcws from (");
		
		sql.append(" select a.lddm,a.cs,a.qsh,a.xb,a.cws from ssxxb a where 1 = 1 ");
		sql.append(" and a.fpbj = 'һ��' ");
		
		// ���䷽ʽΪ��ѧУ->ѧԺ->�༶
		if ("2".equalsIgnoreCase(fpfs)) {
			if ("xx".equalsIgnoreCase(user.getUserStatus())) {
				sql.append(" and not exists (select 1 from xg_gygl_qsfpb b ");
				sql.append(" where a.lddm=b.lddm and a.cs = b.cs and a.qsh = b.qsh) ");
			}else{
				sql.append(" and exists (select 1 from xg_gygl_qsfpb b ");
				sql.append(" where a.lddm=b.lddm and a.cs = b.cs and a.qsh = b.qsh ");
				sql.append(" and b.fpdx = 'xy' and b.bmdm = '"+user.getUserDep()+"') ");
			}
		}else{
			sql.append(" and not exists (select 1 from xg_gygl_qsfpb b ");
			sql.append(" where a.lddm=b.lddm and a.cs = b.cs and a.qsh = b.qsh) ");
		}

		// �Ա�����
		if (!"������".equalsIgnoreCase(xb)) {
			sql.append(" and a.xb = '" + xb + "' ");
		}

		// ��ס����
		if (!"������".equalsIgnoreCase(kfhz)) {
			sql.append(" and a.kfhz = '" + kfhz + "' ");
		}

		
		sql.append(" and exists(select 1 from sslddmb b ");
		sql.append(" where a.lddm = b.lddm ");

		// У������
		if (xiaoqu != null && xiaoqu.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < xiaoqu.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" b.xqdm = '" + xiaoqu[i] + "' ");
			}
			sql.append(" ) ");

		}
		
		// ԰������
		if (yuanqu != null && yuanqu.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < yuanqu.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" b.yqdm = '" + yuanqu[i] + "' ");
			}
			sql.append(" ) ");
		}

		sql.append(" )");

		// ¥������
		if (ld != null && ld.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < ld.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.lddm = '" + ld[i] + "' ");
			}
			sql.append(" ) ");
		}
		
		sql.append(" ) a ");
		
		// ����ס�˵Ĵ�λ
		sql.append(" left join (select b.lddm, b.cs, b.qsh, count(1) bkzrcws ");
		sql.append(" from xg_gygl_cwxxb b where b.cwbj <> '�ɷ���' ");
		sql.append(" group by b.lddm, b.cs, b.qsh) b on a.lddm = b.lddm ");
		sql.append(" and a.cs = b.cs and a.qsh = b.qsh ");
				
		sql.append(" order by a.lddm,a.cs,a.qsh ");
		
		// ��ѯ����
		DAO dao = DAO.getInstance();

		String[] outputValue = new String[] { "lddm", "cs", "qsh","xb", "cws",
				"kzrcws" };
		//System.out.println(sql);
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, outputValue);

		return list;
	}

	/**
	 * ȡ�����ҷ���
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean cancelQsfp(GyglQsglForm model) throws Exception {

		DAO dao = DAO.getInstance();

		// ������
		String[] ssbh = model.getPrimarykey_checkVal();

		boolean flag = true;

		if (ssbh != null && ssbh.length > 0) {

			StringBuilder sql = new StringBuilder();
			sql.append("delete from xg_gygl_qsfpb a ");
			sql.append("where exists (select 1 from xg_view_gygl_qsfp b ");
			sql.append("where b.lddm = a.lddm ");
			sql.append("and a.cs = b.cs ");
			sql.append("and a.qsh = b.qsh ");
			sql.append("and (");

			for (int i = 0; i < ssbh.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" b.ssbh = '"+ssbh[i].split("_")[0]+"' ");
			}

			sql.append("))");

			flag = dao.runUpdate(sql.toString(), new String[]{});
		}

		return flag;
	}

	/**
	 * ���������ס����б�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQsrzInfoList(GyglQsglForm model) {

		// ������
		String[] ssbh = model.getSsbh();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.lddm,a.cs,a.qsh,a.cws, ");
		sql.append("nvl(b.bkzrcws, 0) bkzrcws,nvl(c.yzrs, 0) yzrs ");
		sql.append("from (select a.lddm, a.cs, a.qsh, a.cws from xg_view_gygl_qsfp a ");

		if (ssbh != null && ssbh.length > 0) {
			sql.append(" where 1 = 1 ");
			sql.append(" and ( ");
			for (int i = 0; i < ssbh.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" ssbh = '" + ssbh[i] + "' ");
			}
			sql.append(" ) ");
		}

		sql.append(") a ");

		sql.append("left join (select b.lddm, b.cs, b.qsh, count(1) bkzrcws ");
		sql.append("from xg_gygl_qtcwxxb b where b.cwbj <> '�ɷ���' ");
		sql.append("group by b.lddm, b.cs, b.qsh) b on a.lddm = b.lddm ");
		sql.append("and a.cs = b.cs and a.qsh = b.qsh ");

		sql.append("left join (select c.lddm, c.cs, c.qsh, count(1) yzrs ");
		sql.append("from xszsxxb c group by c.lddm, c.cs, c.qsh) c on a.lddm = c.lddm ");
		sql.append("and a.cs = c.cs and a.qsh = c.qsh ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "lddm", "cs", "qsh", "cws",
						"bkzrcws", "yzrs" });

		return list;
	}

	/**
	 * ���У���б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXqList(GyglQsglForm model,
			GyglJbszForm jbszModel) {

		// ����
		String query = " order by dm ";
		// ���ֵ
		String[] colList = new String[] { "dm", "mc" };
		// sql
		String sql = "select to_char(dm) dm ,xqmc mc from dm_zju_xq ";
		sql += query;

		return getRsList("", query, new String[] {}, colList, sql);
	}
	
	/**
	 * ���԰���б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getYqList(GyglQsglForm model,
			GyglJbszForm jbszModel) {

		// ����
		String query = " order by yqdm ";
		// ���ֵ
		String[] colList = new String[] { "dm", "mc", "sjdm" };
		// sql
		String sql = "select yqdm dm ,yqmc mc,xqdm sjdm from yqdmb ";
		sql += query;
		
		return getRsList("", query, new String[] {}, colList, sql);
	}
	
	/**
	 * ���¥���б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getLdList(GyglQsglForm model,
			User user) {

		DAO dao = DAO.getInstance();

		GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
		// ���䷽ʽ
		String fpfs = jbszModel.getFpfs();

		// ������
		String[] ssbh = model.getPrimarykey_checkVal();
		// ������ϵ
		String csgx = jbszModel.getCsgx();
		// ���ֵ
		String[] colList = null;
		// ������ϵ��1��У��-->԰��-->¥����

		if ("1".equalsIgnoreCase(csgx)) {
			colList = new String[] { "xqdm", "xqmc", "yqdm", "yqmc", "lddm",
					"ldmc", "cs","lddmKey" };
		}
		// ������ϵ��2��У��-->¥����
		else if ("2".equalsIgnoreCase(csgx)) {
			colList = new String[] { "xqdm", "xqmc", "lddm", "ldmc", "cs","lddmKey"  };
		}
		// ������ϵ��3��԰��-->¥����
		else if ("3".equalsIgnoreCase(csgx)) {
			colList = new String[] { "yqdm", "yqmc", "lddm", "ldmc", "cs","lddmKey"  };
		}
		// ������ϵ��4��У��-->¥����
		else if ("4".equalsIgnoreCase(csgx)) {
			colList = new String[] { "lddm", "ldmc", "cs","lddmKey"  };
		}

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,'lddm_'||lddm lddmKey from xg_view_ldxx a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and exists (select 1 from ssxxb b where a.lddm=b.lddm)  ");
		
		if (ssbh != null && ssbh.length > 0) {

			sql.append(" and exists (select 1 from xg_view_gygl_qsfp b ");
			sql.append(" where a.lddm = b.lddm ");
			sql.append(" and (");
			for (int i = 0; i < ssbh.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" b.ssbh = '" + ssbh[i].split("_")[0] + "' ");
			}
			sql.append(" )");
			sql.append(" ) ");
			
		}

		if ("yes".equalsIgnoreCase(model.getWfpqs())) {
			sql.append(" and exists (select 1 from (select distinct(a.lddm)lddm from ssxxb a  ");

			// ���䷽ʽΪ��ѧУ->ѧԺ->�༶
			if ("2".equalsIgnoreCase(fpfs)) {
				if ("xx".equalsIgnoreCase(user.getUserStatus())) {
					sql.append(" left join xg_gygl_qsfpb b   ");
					sql.append(" on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh where b.fpdx is null");
				} else {
					sql.append(" where exists(select 1 from xg_gygl_qsfpb b ");
					sql.append(" where a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh ");
					sql.append(" and b.fpdx = 'xy' and b.bmdm='"+user.getUserDep()+"')");
				}
			} else {
				sql.append(" left join xg_gygl_qsfpb b   ");
				sql.append(" on a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh where b.fpdx is null");
			}

			sql.append(" ) b  ");
			sql.append(" where a.lddm = b.lddm ) ");
		}

		sql.append(" order by xqdm,yqdm,lddm ");
		return dao.getList(sql.toString(), new String[] {}, colList);
	}

	/**
	 * ��������������б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getQsNumList() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from ssxxb a ");
		sql.append(" union all ");
		sql.append(" select count(1) num from xg_gygl_qsfpb b ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "num" });

		return list;
	}

	// ====================2011.6.24 edit by luojw===========================
	// =====================���¹�Ԣ�Ż�======================================
	/**
	 * ������ҷ���ͳ����Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getQsfpTjList(String fpdx, String searchTj,
			String[] inputV, GyglQsglForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		StringBuilder tableSql = new StringBuilder();
		String[] colList = null;
		String orderBy = "";

		tableSql.append("(");
		tableSql.append("select a.*,nvl(b.manNum,0) manNum ,nvl(d.manZqss,0) manZqss,nvl(d.manZcws,0) manZcws ");
		tableSql.append(",nvl(c.womanNum,0) womanNum ,nvl(e.womanZqss,0) womanZqss,nvl(e.womanZcws,0) womanZcws ");
		tableSql.append(",(nvl(b.manNum,0)+nvl(c.womanNum,0)) bmrs from (");
		tableSql.append("select distinct");

		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			tableSql.append(" a.xydm, a.xymc,a.xydm pk ");
			colList = new String[] { "pk", "xymc","bmrs", "manNum", "manZqss",
					"manZcws", "womanNum", "womanZqss", "womanZcws" };
			orderBy = "order by a.xydm";
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			tableSql.append(" a.nj,a.xydm, a.xymc,a.nj||'!!@@!!'||a.xydm pk ");
			colList = new String[] { "pk", "nj", "xymc", "bmrs","manNum", "manZqss",
					"manZcws", "womanNum", "womanZqss", "womanZcws" };
			orderBy = "order by a.nj,a.xydm";
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
			tableSql.append(" a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.nj||'!!@@!!'||a.zydm pk  ");
			colList = new String[] { "pk", "nj", "zymc", "bmrs","manNum", "manZqss",
					"manZcws", "womanNum", "womanZqss", "womanZcws" };
			orderBy = "order by a.nj,a.zydm";
		} else {// �������Ϊ�༶
			tableSql.append(" a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc ");
			colList = new String[] { "bjdm", "bjmc", "bmrs","manNum", "manZqss",
					"manZcws", "womanNum", "womanZqss", "womanZcws" };
			orderBy = "order by a.nj,a.bjdm";
		}
		tableSql.append(" from view_njxyzybj a ");
		tableSql.append(" where 1 = 1 ");
		tableSql.append(searchTj);
		tableSql.append(") a ");

		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ

			tableSql.append(" left join ");
			tableSql.append(" (select xydm, xb, count(1) manNum ");
			tableSql.append(" from view_xsjbxx where xb = '��' ");
			tableSql.append(" group by xydm, xb) b  ");
			tableSql.append(" on a.xydm = b.xydm  ");
			
			tableSql.append(" left join ");
			tableSql.append(" (select xydm, xb, count(1) womanNum ");
			tableSql.append(" from view_xsjbxx where xb = 'Ů' ");
			tableSql.append(" group by xydm, xb) c  ");
			tableSql.append(" on a.xydm = c.xydm  ");
			
			tableSql.append(" left join ");
			tableSql.append(" (select a.bmdm, count(1) manZqss, sum(cws) manZcws from xg_gygl_qsfpb a ");
			tableSql.append(" ,(select b.* from ssxxb b where b.xb = '��') b ");
			tableSql.append(" where a.lddm = b.lddm and a.cs = b.cs and a.qsh = b.qsh ");
			tableSql.append(" group by a.bmdm) d ");
			tableSql.append(" on a.xydm = d.bmdm  ");
			
			tableSql.append(" left join ");
			tableSql.append(" (select a.bmdm, count(1) womanZqss, sum(cws) womanZcws from xg_gygl_qsfpb a ");
			tableSql.append(" ,(select b.* from ssxxb b where b.xb = 'Ů') b ");
			tableSql.append(" where a.lddm = b.lddm and a.cs = b.cs and a.qsh = b.qsh ");
			tableSql.append(" group by a.bmdm) e ");
			tableSql.append(" on a.xydm = e.bmdm  ");
			
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			
			tableSql.append(" left join ");
			tableSql.append(" (select nj, xydm, xb, count(1) manNum ");
			tableSql.append(" from view_xsjbxx where xb = '��' ");
			tableSql.append(" group by nj, xydm, xb) b  ");
			tableSql.append(" on a.nj = b.nj and a.xydm = b.xydm  ");
			
			tableSql.append(" left join ");
			tableSql.append(" (select nj, xydm, xb, count(1) womanNum ");
			tableSql.append(" from view_xsjbxx where xb = 'Ů' ");
			tableSql.append(" group by nj, xydm, xb) c  ");
			tableSql.append(" on a.nj = c.nj and a.xydm = c.xydm  ");
			
			tableSql.append(" left join ");
			tableSql.append(" (select a.nj, a.bmdm, count(1) manZqss, sum(cws) manZcws from xg_gygl_qsfpb a ");
			tableSql.append(" ,(select b.* from ssxxb b where b.xb = '��') b ");
			tableSql.append(" where a.lddm = b.lddm and a.cs = b.cs and a.qsh = b.qsh ");
			tableSql.append(" group by a.nj, a.bmdm) d ");
			tableSql.append(" on a.nj = d.nj and a.xydm = d.bmdm  ");
			
			tableSql.append(" left join ");
			tableSql.append(" (select a.nj, a.bmdm, count(1) womanZqss, sum(cws) womanZcws from xg_gygl_qsfpb a ");
			tableSql.append(" ,(select b.* from ssxxb b where b.xb = 'Ů') b ");
			tableSql.append(" where a.lddm = b.lddm and a.cs = b.cs and a.qsh = b.qsh ");
			tableSql.append(" group by a.nj, a.bmdm) e ");
			tableSql.append(" on a.nj = e.nj and a.xydm = e.bmdm  ");
			
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ

			tableSql.append(" left join ");
			tableSql.append(" (select nj, zydm, xb, count(1) manNum ");
			tableSql.append(" from view_xsjbxx where xb = '��' ");
			tableSql.append(" group by nj, zydm, xb) b  ");
			tableSql.append(" on a.nj = b.nj and a.zydm = b.zydm  ");
			
			tableSql.append(" left join ");
			tableSql.append(" (select nj, zydm, xb, count(1) womanNum ");
			tableSql.append(" from view_xsjbxx where xb = 'Ů' ");
			tableSql.append(" group by nj, zydm, xb) c  ");
			tableSql.append(" on a.nj = c.nj and a.zydm = c.zydm  ");
			
			tableSql.append(" left join ");
			tableSql.append(" (select a.nj, a.bmdm, count(1) manZqss, sum(cws) manZcws from xg_gygl_qsfpb a ");
			tableSql.append(" ,(select b.* from ssxxb b where b.xb = '��') b ");
			tableSql.append(" where a.lddm = b.lddm and a.cs = b.cs and a.qsh = b.qsh ");
			tableSql.append(" group by a.nj, a.bmdm) d ");
			tableSql.append(" on a.nj = d.nj and a.zydm = d.bmdm  ");
			
			tableSql.append(" left join ");
			tableSql.append(" (select a.nj, a.bmdm, count(1) womanZqss, sum(cws) womanZcws from xg_gygl_qsfpb a ");
			tableSql.append(" ,(select b.* from ssxxb b where b.xb = 'Ů') b ");
			tableSql.append(" where a.lddm = b.lddm and a.cs = b.cs and a.qsh = b.qsh ");
			tableSql.append(" group by a.nj, a.bmdm) e ");
			tableSql.append(" on a.nj = e.nj and a.zydm = e.bmdm  ");
			
		} else {// �������Ϊ�༶

			tableSql.append(" left join ");
			tableSql.append(" (select nj, bjdm, xb, count(1) manNum ");
			tableSql.append(" from view_xsjbxx where xb = '��' ");
			tableSql.append(" group by nj, bjdm, xb) b  ");
			tableSql.append(" on a.nj = b.nj and a.bjdm = b.bjdm  ");
			
			tableSql.append(" left join ");
			tableSql.append(" (select nj, bjdm, xb, count(1) womanNum ");
			tableSql.append(" from view_xsjbxx where xb = 'Ů' ");
			tableSql.append(" group by nj, bjdm, xb) c  ");
			tableSql.append(" on a.nj = c.nj and a.bjdm = c.bjdm  ");
			
			tableSql.append(" left join ");
			tableSql.append(" (select a.bmdm, count(1) manZqss, sum(cws) manZcws from xg_gygl_qsfpb a ");
			tableSql.append(" ,(select b.* from ssxxb b where b.xb = '��') b ");
			tableSql.append(" where a.lddm = b.lddm and a.cs = b.cs and a.qsh = b.qsh ");
			tableSql.append(" group by a.bmdm) d ");
			tableSql.append(" on a.bjdm = d.bmdm  ");
			
			tableSql.append(" left join ");
			tableSql.append(" (select a.bmdm, count(1) womanZqss, sum(cws) womanZcws from xg_gygl_qsfpb a ");
			tableSql.append(" ,(select b.* from ssxxb b where b.xb = 'Ů') b ");
			tableSql.append(" where a.lddm = b.lddm and a.cs = b.cs and a.qsh = b.qsh ");
			tableSql.append(" group by a.bmdm) e ");
			tableSql.append(" on a.bjdm = e.bmdm  ");
		}
		tableSql.append(orderBy);
		tableSql.append(")");
		
		String query = "";
		
		return getRsArrList(tableSql.toString(), query, inputV, colList, "",
				model);
	}
	
	/**
	 * ��ò���ͳ���б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String,String>> getBmTjList(String fpdx, ArrayList<String[]> bmList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		StringBuilder tableSql = new StringBuilder();
		tableSql.append(" (select a.nj,a.bmdm,b.xb,b.kfhz,");
		tableSql.append(" count(1) qsnum from xg_gygl_qsfpb a,ssxxb b ");
		tableSql.append(" where a.fpdx = ? ");
		tableSql.append(" and a.lddm = b.lddm ");
		tableSql.append(" and a.cs = b.cs ");
		tableSql.append(" and a.qsh = b.qsh ");
		// ��ͳ�Ʋ����б���������
		if (bmList != null && bmList.size() > 0) {
			String bmdm = "";
			String nj = "";
			tableSql.append(" and ( ");
			for (int i = 0; i < bmList.size(); i++) {

				String[] bmInfo = bmList.get(i);

				if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
					bmdm = bmInfo[0];
				} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
					nj = bmInfo[0];
					bmdm = bmInfo[1];
				} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
					nj = bmInfo[0];
					bmdm = bmInfo[3];
				} else {// �������Ϊ�༶
					nj = bmInfo[0];
					bmdm = bmInfo[5];
				}

				if (i != 0) {
					tableSql.append(" or ");
				}
				tableSql.append(" (");
				tableSql.append(" bmdm = '" + bmdm + "'");
				tableSql.append(" and");
				tableSql.append(Base.isNull(nj) ? " 1 = 1" : " nj = '" + nj + "'");
				tableSql.append(" )");

			}
			tableSql.append(" ) ");
		}
		tableSql.append(" group by a.nj,a.bmdm,b.xb,b.kfhz ");
		tableSql.append(" order by a.nj,a.bmdm)");

		return getRsList(tableSql.toString(), "", new String[] { fpdx },
				new String[] { "nj", "bmdm", "xb", "kfhz", "qsnum" }, "");
	}

	/**
	 * ��ò���ͳ����Ϣ
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getBmqsfpList(String[] pk,
			String fpdx) {
		
		String[] nj = null;
		String[] xy = null;
		String[] zy = null;
		String[] bj = null;
		String[] outputValue = null;
		
		if (pk != null && pk.length > 0) {
			nj = new String[pk.length];
			xy = new String[pk.length];
			zy = new String[pk.length];
			bj = new String[pk.length];
			if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
				xy = pk;
			} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
				for (int i = 0; i < pk.length; i++) {
					String[] arr_value = pk[i].split("!!@@!!");
					nj[i] = arr_value[0];
					xy[i] = arr_value[1];
				}
			} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
				for (int i = 0; i < pk.length; i++) {
					String[] arr_value = pk[i].split("!!@@!!");
					nj[i] = arr_value[0];
					zy[i] = arr_value[1];
				}
			} else {// �������Ϊ�༶
				bj = pk;
			}
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select nvl(b.man,0) man,nvl(c.woman,0) woman, ");
		sql.append("nvl(d.mancws,0) mancws,nvl(e.womancws,0) womancws ");
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			sql.append(" ,a.xydm,xymc ");
			outputValue = new String[] {"xymc", "man", "woman", "mancws",
					"womancws","xydm" };
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			sql.append(" ,a.nj,a.xydm,xymc ");
			outputValue = new String[] { "nj", "xymc", "man", "woman", "mancws",
			"womancws","xydm" };
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
			sql.append(" ,a.nj,a.zydm,zymc ");
			outputValue = new String[] { "nj", "zymc", "man", "woman", "mancws",
			"womancws","zydm" };
		} else {// �������Ϊ�༶
			sql.append(" ,a.nj,a.bjdm,bjmc ");
			outputValue = new String[] { "bjmc", "man", "woman", "mancws",
			"womancws","bjdm","nj" };
		}
		sql.append(" from ");
		
		//======================������Ϣ===========================
		sql.append("(");
		sql.append("select distinct ");
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			sql.append(" xydm, xymc ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			sql.append(" nj,xydm, xymc ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
			sql.append(" nj,zydm,zymc ");
		} else {// �������Ϊ�༶
			sql.append(" nj,bjdm,bjmc ");
		}
		sql.append("from view_njxyzybj ");
		sql.append("where 1 = 1 ");
		sql.append(" and ( ");
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" xydm = '"+xy[i]+"' ");
			}
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (nj = '"+nj[i]+"' ");
				sql.append(" and xydm = '"+xy[i]+"') ");
			}
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
			for(int i=0;i<zy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (nj = '"+nj[i]+"' ");
				sql.append(" and zydm = '"+zy[i]+"') ");
			}
		} else {// �������Ϊ�༶
			for(int i=0;i<bj.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" bjdm = '"+bj[i]+"' ");
			}
		}
		sql.append(" ) ");
		sql.append(") a ");
		// ======================��������===========================
		sql.append("left join ( ");
		sql.append("select ");
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			sql.append(" xydm, ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			sql.append(" nj,xydm, ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
			sql.append(" nj,zydm, ");
		} else {// �������Ϊ�༶
			sql.append(" nj,bjdm, ");
		}
		sql.append("count(1) man from view_xsjbxx ");
		sql.append("where xb = '��' ");
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" xydm = '"+xy[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by xydm) b ");
			sql.append(" on a.xydm =b.xydm ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (nj = '"+nj[i]+"' ");
				sql.append(" and xydm = '"+xy[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by nj,xydm) b ");
			sql.append(" on a.nj =b.nj ");
			sql.append(" and a.xydm =b.xydm ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
			sql.append(" and ( ");
			for(int i=0;i<zy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (nj = '"+nj[i]+"' ");
				sql.append(" and zydm = '"+zy[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by nj,zydm) b ");
			sql.append(" on a.nj =b.nj ");
			sql.append(" and a.zydm =b.zydm ");
		} else {// �������Ϊ�༶
			sql.append(" and ( ");
			for(int i=0;i<bj.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" bjdm = '"+bj[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by nj,bjdm) b ");
			sql.append(" on a.bjdm =b.bjdm ");
		}
		
		sql.append("left join ( ");
		sql.append("select ");
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			sql.append(" xydm, ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			sql.append(" nj,xydm, ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
			sql.append(" nj,zydm, ");
		} else {// �������Ϊ�༶
			sql.append(" nj,bjdm, ");
		}
		sql.append("count(1) woman from view_xsjbxx ");
		sql.append("where xb = 'Ů' ");
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" xydm = '"+xy[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by xydm) c ");
			sql.append(" on a.xydm =c.xydm ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (nj = '"+nj[i]+"' ");
				sql.append(" and xydm = '"+xy[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by nj,xydm) c ");
			sql.append(" on a.nj =c.nj ");
			sql.append(" and a.xydm =c.xydm ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
			sql.append(" and ( ");
			for(int i=0;i<zy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (nj = '"+nj[i]+"' ");
				sql.append(" and zydm = '"+zy[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by nj,zydm) c ");
			sql.append(" on a.nj =c.nj ");
			sql.append(" and a.zydm =c.zydm ");
		} else {// �������Ϊ�༶
			sql.append(" and ( ");
			for(int i=0;i<bj.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" bjdm = '"+bj[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by nj,bjdm) c ");
			sql.append(" on a.bjdm =c.bjdm ");
		}
		
		// ======================�ѷ���������===========================
		sql.append("left join ( ");
		sql.append("select ");
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			sql.append(" b.bmdm, ");
		} else {
			sql.append(" b.nj,b.bmdm, ");
		}
		sql.append("sum(a.cws) mancws from ssxxb a, xg_gygl_qsfpb b ");
		sql.append("where a.lddm = b.lddm ");
		sql.append("and a.cs = b.cs ");
		sql.append("and a.qsh = b.qsh ");
		sql.append("and xb = '��' ");
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" b.bmdm = '"+xy[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by b.bmdm) d ");
			sql.append(" on a.xydm =d.bmdm ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (b.bmdm = '"+xy[i]+"' ");
				sql.append(" and b.nj = '"+nj[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by b.bmdm,b.nj) d ");
			sql.append(" on a.xydm =d.bmdm ");
			sql.append(" and a.nj =d.nj ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (b.bmdm = '"+zy[i]+"' ");
				sql.append(" and b.nj = '"+nj[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by b.bmdm,b.nj) d ");
			sql.append(" on a.zydm =d.bmdm ");
			sql.append(" and a.nj =d.nj ");
		} else {// �������Ϊ�༶
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" b.bmdm = '"+bj[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by b.nj,b.bmdm) d ");
			sql.append(" on a.bjdm =d.bmdm ");
		}
		
		sql.append("left join ( ");
		sql.append("select ");
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			sql.append(" b.bmdm, ");
		} else {
			sql.append(" b.nj,b.bmdm, ");
		}
		sql.append("sum(a.cws) womancws from ssxxb a, xg_gygl_qsfpb b ");
		sql.append("where a.lddm = b.lddm ");
		sql.append("and a.cs = b.cs ");
		sql.append("and a.qsh = b.qsh ");
		sql.append("and xb = 'Ů' ");
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" b.bmdm = '"+xy[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by b.bmdm) e ");
			sql.append(" on a.xydm =e.bmdm ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (b.bmdm = '"+xy[i]+"' ");
				sql.append(" and b.nj = '"+nj[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by b.bmdm,b.nj) e ");
			sql.append(" on a.xydm =e.bmdm ");
			sql.append(" and a.nj =e.nj ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (b.bmdm = '"+zy[i]+"' ");
				sql.append(" and b.nj = '"+nj[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by b.bmdm,b.nj) e ");
			sql.append(" on a.zydm =e.bmdm ");
			sql.append(" and a.nj =e.nj ");
		} else {// �������Ϊ�༶
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" b.bmdm = '"+bj[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by b.nj,b.bmdm) e ");
			sql.append(" on a.bjdm =e.bmdm ");
		}
		
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			sql.append(" order by a.xydm ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			sql.append(" order by a.nj,a.xydm ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
			sql.append(" order by a.nj,a.zydm ");
		} else {// �������Ϊ�༶
			sql.append(" order by a.nj,a.bjdm ");
		}
		
		DAO dao = DAO.getInstance();
		
		List<HashMap<String,String>> list = dao.getList(sql.toString(), new String[]{}, outputValue);
		
		return list;
		
	}
	
	/**
	 * ������ϢBY���䲿��
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getBmxxByFpdx(GyglQsglForm model){
		DAO dao=DAO.getInstance();
		List<String>inputV=new ArrayList<String>();
		StringBuilder sql=new StringBuilder();
		StringBuilder query=new StringBuilder();
		StringBuilder zd=new StringBuilder();
		String fpdx=model.getFpdx();
		String []outList=null;
		String []pkValue=model.getPrimarykey_checkVal();
		String pk="";
		String[] colList = new String[] { "dm", "mc", "allmc", "sjdm", "bmlx",
		"nj" };
		if ("xy".equalsIgnoreCase(fpdx)) {// �������ΪѧԺ
			pk="xydm";
			zd.append(" distinct(xydm)pk,'' nj, ");
			zd.append(" case when length(xymc) > 6 then substr(xymc, 0, 6) || '...' else  to_char(xymc) end mc, ");
			zd.append(" xydm dm,xymc allmc,'' sjdm,'"+fpdx+"' bmlx ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+ѧԺ
			pk="nj||'!!@@!!'||xydm";
			zd.append(" distinct(nj||xydm)pk,nj,  ");
			zd.append(" case when length(xymc||'('||nj||')') > 6 then substr(xymc||'('||nj||')', 0, 6) || '...' else  to_char(xymc||'('||nj||')') end  mc,");
			zd.append(" xydm dm ,xymc||'('||nj||')' allmc,'' sjdm,'"+fpdx+"' bmlx ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// �������Ϊ�꼶+רҵ
			pk="nj||'!!@@!!'||zydm";
			zd.append(" distinct(nj||zydm)pk,nj,  ");
			zd.append(" case when length(zymc||'('||nj||')') > 6 then substr(zymc||'('||nj||')', 0, 6) || '...' else  to_char(zymc||'('||nj||')') end  mc,");
			zd.append(" zydm dm,zymc||'('||nj||')' allmc,'' sjdm,'"+fpdx+"' bmlx  ");
		} else {// �������Ϊ�༶
			pk="bjdm";
			zd.append(" distinct(bjdm)pk,nj,bjdm dm,  ");
			zd.append(" case when length(bjmc) > 6 then substr(bjmc, 0, 6) || '...' else  to_char(bjmc) end mc, ");
			zd.append(" bjmc allmc,'' sjdm,'"+fpdx+"' bmlx  ");
		}
		
		sql.append(" select ");
		sql.append(zd);
		sql.append(" from view_njxyzybj where 1=1 ");
		
		if(pkValue!=null && pkValue.length>0){
			for(int i=0;i<pkValue.length;i++){
				if(i==0){
					query.append(" and ( ");
				}else {
					query.append(" or ");
				}
				query.append(pk);
				query.append(" = ? ");
				inputV.add(pkValue[i]);
			}
			query.append(")");
		}
		
		sql.append(query);
		//System.out.println(sql);
		return dao.getList(sql.toString(), inputV.toArray(new String[]{}), colList);
	}
	
	/**
	 * ���¥����Ϣ�б�δ������������
	 * 
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, String>> getLdForWfpQssList(User user) {
		
		GyglJbszForm jbszModel = GyglJbszForm.gyglJbszModel;
		String fpfs = jbszModel.getFpfs();
		
		// �����Զ�����
		StringBuilder gySql = new StringBuilder();
		gySql.append(" select a.lddm,a.ldmc,a.xbxd,");
		gySql.append(" case when a.qsnum <> 0 then (a.qsnum - a.yfpnum) else 0 end num ");
		gySql.append(" from ( ");
		gySql.append(" select a.lddm,a.ldmc,a.xbxd,nvl(b.qsNum,0) qsNum,nvl(c.yfpNum,0) yfpNum from ");
		gySql.append(" (select * from sslddmb where 1 = 1 ");	
		gySql.append(" ) a ");
		
		gySql.append(" left join ");
		
		// ���䷽ʽΪ��ѧУ->ѧԺ->�༶
		if ("2".equalsIgnoreCase(fpfs)) {
			if ("xx".equalsIgnoreCase(user.getUserStatus())) {
				gySql.append(" (select b.lddm, count(1) qsNum from ssxxb b group by b.lddm) b on a.lddm = b.lddm ");
				gySql.append(" left join ");
				gySql.append(" (select c.lddm, count(1) yfpNum from xg_gygl_qsfpb c group by c.lddm) c on a.lddm = c.lddm ");
			} else {
				gySql.append(" (select b.lddm, count(1) qsNum from xg_gygl_qsfpb b ");
				gySql.append(" where b.fpdx = 'xy' and b.bmdm='"+user.getUserDep()+"' ");
				gySql.append(" group by b.lddm) b on a.lddm = b.lddm ");
				gySql.append(" left join ");
				gySql.append(" (select c.lddm, count(1) yfpNum from xg_gygl_qsfpb c ");
				gySql.append(" where c.fpdx <> 'xy' ");
				gySql.append(" group by c.lddm) c on a.lddm = c.lddm ");
			}
		} else {
			gySql.append(" (select b.lddm, count(1) qsNum from ssxxb b group by b.lddm) b on a.lddm = b.lddm ");
			gySql.append(" left join ");
			gySql.append(" (select c.lddm, count(1) yfpNum from xg_gygl_qsfpb c group by c.lddm) c on a.lddm = c.lddm ");
		}
		
		gySql.append(" ) a order by a.lddm");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> ldQsList = dao.getList(gySql.toString(),
				new String[] {}, new String[] { "lddm", "ldmc","xbxd", "num" });

		return ldQsList;

	}
	
	/**
	 * �����ҷ�����༶
	 * 
	 * @param model
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveQsfpToBj(GyglQsglForm model, User user) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_gygl_qsfpb set fpdx = 'bj' ");
		sql.append(",nj=? ");
		sql.append(",bmdm=? ");
		sql.append(",kffp='yes' ");
		sql.append("where lddm=? ");
		sql.append("and cs=? ");
		sql.append("and qsh=? ");
		sql.append("and bmdm<>? ");

		List<String[]> params = new ArrayList<String[]>();
		// ¥��
		String[] lddm = model.getLddm();
		// ����
		String[] cs = model.getCs();
		// ���Һ�
		String[] qsh = model.getQsh();
		// �꼶
		String[] nj = model.getNj();
		// ���Ŵ���
		String[] bmdm = model.getBmdm();
		
		for(int i =0;i<lddm.length;i++){
			ArrayList<String> list = new ArrayList<String>();
			list.add(nj[i]);
			list.add(bmdm[i]);
			list.add(lddm[i]);
			list.add(cs[i]);
			list.add(qsh[i]);
			list.add(bmdm[i]);
			params.add(list.toArray(new String[]{}));
		}
		
		boolean flag = saveArrDate(sql.toString(), params, "xg_gygl_qsfpb",
				user);

		return flag;
	}
	
	/**
	 * δ����¥��¥��
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getWfpLcList(GyglQsglForm model, User user){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select lddm,mc,dm from( ");
		sql.append(" select a.lddm,a.cs dm,a.cs mc from ssxxb a  ");
		sql.append(" where a.fpbj='һ��' and not exists(select 1 from xg_gygl_qsfpb b  ");
		sql.append(" where a.lddm=b.lddm and a.cs=b.cs and a.qsh=b.qsh)) ");
		sql.append(" group by lddm,dm,mc order by lddm,dm,mc ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"lddm","dm","mc"});
	}
}
