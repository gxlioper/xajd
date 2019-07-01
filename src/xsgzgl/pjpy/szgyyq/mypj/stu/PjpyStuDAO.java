package xsgzgl.pjpy.szgyyq.mypj.stu;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjDAO;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����(ѧ��)_DAO��
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

public class PjpyStuDAO extends PjpyMypjDAO {

	/**
	 * ���ѧ����������Ŀ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getMypjStuList(PjpyStuForm model,
			User user) {

		// �û���
		String userName = user.getUserName();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.xmdm, a.xmmc,a.shjb,a.shzt,a.lcid,");
		sql.append("a.pk||(select b.spgw from xg_xtwh_spbz b ");
		sql.append("where b.splc = a.lcid and b.xh = a.shjb) pk ");
		sql.append("from(");
		sql.append("select a.pk,a.xmdm,a.xmmc,a.lcid,max(shjb) shjb, ");
		sql.append("to_char(WM_CONCAT(a.gwmc || '(' || a.shzt || ')')) shzt ");
		sql.append("from (select a.xmdm||a.pjxn||a.pjxq||a.pjnd||a.xh pk,");
		sql
				.append("a.xmdm, a.xmmc, a.shjb, a.mc gwmc, a.shzt,a.lcid from xg_view_pjpy_xmsh a ");
		sql.append("order by xmdm, to_number(shjb)) a ");
		sql.append("group by a.pk,a.xmdm,a.xmmc,a.lcid) a ");

		return getRsList("", "", new String[] {}, new String[] { "pk", "xmdm",
				"xmmc", "shzt", "shjb" }, sql.toString());
	}

	/**
	 * ���������Ŀ���
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	public List<HashMap<String,String>> getSqxmInfo(PjpyStuForm model) {

		// ѧ��
		String xh = model.getXh();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ����
		String lx = "";
		// ѧ��
		String xn = Base.currXn;
		// ѧ��
		String xq = Base.currXq;

		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// ����
			lx = "dshd";
		} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// ���Ա��
			lx = "yybd";
		} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT��̳
			lx = "ivtlt";
		} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// ����
			lx = "wthd";
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// ��֯����
			lx = "zznl";
		} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// ���ʵ��
			lx = "shsj";
		} else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5S��
			lx = "wsmk";
		}

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.jcf, a.zf, nvl(b.sqf, 0) sqf, ");
		sql.append(" nvl(b.shf, 0) shf from (select zxmjcf jcf, ");
		sql.append(" zxmzgf zf from szgy_zhszycfsszb ");
		sql.append(" where zxmdm = ?) a ");
		sql.append(" left join (select sum(sqf) sqf, sum(shf) shf ");
		sql.append(" from (select sqf, case when xxsh = 'ͨ��' then ");
		sql.append(" sqf when xxsh = 'δ���' and xysh = 'ͨ��' then ");
		sql.append(" sqf else '0' end shf from ");
		sql.append(xmdm);
		sql.append(" where xn = ? ");
		sql.append(" and xq = ? ");
		sql.append(" and xh = ?)) b on 1 = 1 ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { lx, xn, xq, xh }, new String[] { "sqf", "shf",
						"jcf", "zf" });

		return list;
	}
	
	/**
	 * ����ҵ�������Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getMyssList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		CommService commService = new CommService();
		SearchModel searchModel = model.getSearchModel();

		// ѧ��
		String xh = model.getXh();
		// ѧ��
		String[] xn = searchModel.getSearch_tj_xn();
		// ѧ��
		String[] xq = searchModel.getSearch_tj_xq();
		// ����
		String[] lx = searchModel.getSearch_tj_lx();
		// ��Ŀ
		String[] xmlx = searchModel.getSearch_tj_xmdm();
		
		// ģ����ѯ
		String input_mhcx = searchModel.getInput_mhcx();
		// ��ѯ����
		String mhcx_lx = searchModel.getMhcx_lx();

		StringBuilder tableSql = new StringBuilder();

		tableSql.append("(");
		tableSql.append("select a.xn,a.xq,a.xmlx,xmmc,a.ssnr,a.allssnr,a.sssj, ");
		tableSql.append("a.clr,nvl(a.clrxm,'δ����') clrxm,nvl(a.clyj,'δ����') clyj,");
		tableSql.append("a.allclyj,a.clsj,a.lx, ");
		tableSql.append("a.xh,a.xmid from ( ");
		tableSql.append("select a.xn,a.xq,a.xmlx,a.ssnr allssnr, ");
		tableSql.append("case when clr is not null then '�Ѵ���' ");
		tableSql.append("else 'δ����' end lx, ");	
		tableSql.append("case ");
		tableSql.append("when xmlx = 'szyq_dshdjzb' then '����' ");
		tableSql.append("when xmlx = 'szyq_yybdjzb' then '���Ա��' ");
		tableSql.append("when xmlx = 'szyq_ivtltb' then 'IVT��̳' ");
		tableSql.append("when xmlx = 'szyq_xthddjb' then '����' ");
		tableSql.append("when xmlx = 'szyc_zznlfzb' then '��֯����' ");
		tableSql.append("when xmlx = 'szyc_5sb' then '5S' ");
		tableSql.append("when xmlx = 'szyc_shsjfzb' then '���ʵ��' end xmmc, ");
		tableSql.append("case when length(a.ssnr)>6 then substr(a.ssnr,0,6) || '...' ");
		tableSql.append("else a.ssnr end ssnr,a.sssj,a.clr,a.clsj,a.clyj allclyj, ");
		tableSql.append("case when length(a.clyj)>6 then substr(a.clyj,0,6) || '...' ");
		tableSql.append("else a.clyj end clyj,a.xh,a.xmid,  ");
		tableSql.append("(select b.xm from yhb b where a.clr = b.yhm) clrxm ");
		tableSql.append("from (select a.xn, a.xq, a.xmlx, a.ssnr, a.sssj, a.clyj, a.clr, a.clsj, ");
		tableSql.append("a.xh,a.xmid from xg_pjpy_szgyyq_xsssb a where xh = ? ) a ) a ");
		
		tableSql.append("where 1 = 1 ");

		// ѧ��
		if (xn != null && xn.length > 0) {
			tableSql.append("and ( ");
			for (int i = 0; i < xn.length; i++) {
				if (i != 0) {
					tableSql.append(" or ");
				}
				tableSql.append("a.xn = '" + xn[i] + "'");
			}
			tableSql.append(") ");
		}

		// ѧ��
		if (xq != null && xq.length > 0) {
			tableSql.append("and ( ");
			for (int i = 0; i < xq.length; i++) {
				if (i != 0) {
					tableSql.append(" or ");
				}
				tableSql.append("a.xq = '" + xq[i] + "'");
			}
			tableSql.append(") ");
		}
		
		// ��Ŀ����
		if (xmlx != null && xmlx.length > 0) {
			tableSql.append("and ( ");
			for (int i = 0; i < xmlx.length; i++) {
				if (i != 0) {
					tableSql.append(" or ");
				}
				tableSql.append("a.xmlx = '" + xmlx[i] + "'");
			}
			tableSql.append(") ");
		}
		
		// ����״̬
		if (lx != null && lx.length > 0) {
			tableSql.append("and ( ");
			for (int i = 0; i < lx.length; i++) {
				if (i != 0) {
					tableSql.append(" or ");
				}
				tableSql.append("a.lx = '" + commService.unicode2Gbk(lx[i])
						+ "'");
			}
			tableSql.append(") ");
		}

		// ģ����ѯ
		if (!Base.isNull(input_mhcx.trim())) {

			String[] mhcx = input_mhcx.split(" ");

			if (!"all".equalsIgnoreCase(mhcx_lx)) {

				tableSql.append("and ( ");
				for (int i = 0; i < mhcx.length; i++) {
					if (i != 0) {
						tableSql.append(" or ");
					}
					tableSql.append("a." + mhcx_lx + " like '%" + mhcx[i]
							+ "%'");
				}
				tableSql.append(") ");

			} else {

				tableSql.append("and ( ");
				for (int i = 0; i < mhcx.length; i++) {
					if (i != 0) {
						tableSql.append(" or ");
					}
					tableSql.append("(a.allssnr like '%" + mhcx[i] + "%' ");
					tableSql.append("or ");
					tableSql.append("a.clrxm like '%" + mhcx[i] + "%') ");
				}
				tableSql.append(") ");
			}
		}

		tableSql.append(")");

		return getRsArrList(tableSql.toString(), "", new String[] { xh },
				new String[] { "xmmc", "ssnr", "sssj", "clyj", "clrxm", "clsj",
						"xn", "xq", "xh", "xmid", "allssnr", "allclyj", "lx" },
				"", model);

	}
	
	/**
	 * ����ҵ�������Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getMytsList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		CommService commService = new CommService();
		SearchModel searchModel = model.getSearchModel();

		// ѧ��
		String xh = model.getXh();
		// ѧ��
		String[] xn = searchModel.getSearch_tj_xn();
		// ѧ��
		String[] xq = searchModel.getSearch_tj_xq();
		// ����
		String[] lx = searchModel.getSearch_tj_lx();
		// ��Ŀ
		String[] xmlx = searchModel.getSearch_tj_xmdm();
		
		// ģ����ѯ
		String input_mhcx = searchModel.getInput_mhcx();
		// ��ѯ����
		String mhcx_lx = searchModel.getMhcx_lx();

		StringBuilder tableSql = new StringBuilder();

		tableSql.append("(");
		tableSql.append("select a.xn,a.xq,a.xh,a.xm,a.xmlx,a.xmmc, ");
		tableSql.append("a.tsnr,a.alltsnr,a.tssj,a.clr, ");
		tableSql.append("nvl(a.clrxm, 'δ����') clrxm, ");
		tableSql.append("nvl(a.clyj, 'δ����') clyj, ");
		tableSql.append("a.allclyj,a.clsj,a.lx from (select a.xn, ");
		tableSql.append("a.xq,a.btsr xh,a.xm,a.xmlx,a.tsnr alltsnr, ");
		tableSql.append("case when clr is not null then ");
		tableSql.append("'�Ѵ���' else 'δ����' end lx, ");
		tableSql.append("case ");
		tableSql.append("when xmlx = 'szyq_dshdjzb' then '����' ");
		tableSql.append("when xmlx = 'szyq_yybdjzb' then '���Ա��' ");
		tableSql.append("when xmlx = 'szyq_ivtltb' then 'IVT��̳' ");
		tableSql.append("when xmlx = 'szyq_xthddjb' then '����' ");
		tableSql.append("when xmlx = 'szyc_zznlfzb' then '��֯����' ");
		tableSql.append("when xmlx = 'szyc_shsjfzb' then '���ʵ��' ");
		tableSql.append("when xmlx = 'szyc_5sb' then '5S' ");
		tableSql.append("end xmmc, ");
		tableSql.append("case when length(a.tsnr) > 6 then ");
		tableSql.append("substr(a.tsnr, 0, 6) || '...' else ");
		tableSql.append("a.tsnr end tsnr,a.tssj,a.clr,a.clsj, ");
		tableSql.append("a.clyj allclyj,case when length(a.clyj) > 6 ");
		tableSql.append("then substr(a.clyj, 0, 6) || '...' ");
		tableSql.append("else a.clyj end clyj, ");
		tableSql.append("(select b.xm from yhb b where a.clr = b.yhm) clrxm ");
		tableSql.append("from (select a.xn,a.xq,a.btsr,b.xm,a.xmlx, ");
		tableSql.append("a.tsnr,a.tssj,a.clyj,a.clr,a.clsj ");
		tableSql.append("from xg_pjpy_szgyyq_xstsb a ");
		tableSql.append("left join view_xsjbxx b on a.btsr = b.xh ");
		tableSql.append("where a.xh = ?) a ) a ");
		tableSql.append("where 1 = 1 ");

		// ѧ��
		if (xn != null && xn.length > 0) {
			tableSql.append("and ( ");
			for (int i = 0; i < xn.length; i++) {
				if (i != 0) {
					tableSql.append(" or ");
				}
				tableSql.append("a.xn = '" + xn[i] + "'");
			}
			tableSql.append(") ");
		}

		// ѧ��
		if (xq != null && xq.length > 0) {
			tableSql.append("and ( ");
			for (int i = 0; i < xq.length; i++) {
				if (i != 0) {
					tableSql.append(" or ");
				}
				tableSql.append("a.xq = '" + xq[i] + "'");
			}
			tableSql.append(") ");
		}
		
		// ��Ŀ����
		if (xmlx != null && xmlx.length > 0) {
			tableSql.append("and ( ");
			for (int i = 0; i < xmlx.length; i++) {
				if (i != 0) {
					tableSql.append(" or ");
				}
				tableSql.append("a.xmlx = '" + xmlx[i] + "'");
			}
			tableSql.append(") ");
		}
		
		// ����״̬
		if (lx != null && lx.length > 0) {
			tableSql.append("and ( ");
			for (int i = 0; i < lx.length; i++) {
				if (i != 0) {
					tableSql.append(" or ");
				}
				tableSql.append("a.lx = '" + commService.unicode2Gbk(lx[i])
						+ "'");
			}
			tableSql.append(") ");
		}

		// ģ����ѯ
		if (!Base.isNull(input_mhcx.trim())) {

			String[] mhcx = input_mhcx.split(" ");

			if (!"all".equalsIgnoreCase(mhcx_lx)) {

				tableSql.append("and ( ");
				for (int i = 0; i < mhcx.length; i++) {
					if (i != 0) {
						tableSql.append(" or ");
					}
					tableSql.append("a." + mhcx_lx + " like '%" + mhcx[i]
							+ "%'");
				}
				tableSql.append(") ");

			} else {

				tableSql.append("and ( ");
				for (int i = 0; i < mhcx.length; i++) {
					if (i != 0) {
						tableSql.append(" or ");
					}
					tableSql.append("(a.xh like '%" + mhcx[i] + "%' ");
					tableSql.append("or ");
					tableSql.append("a.xm like '%" + mhcx[i] + "%') ");
				}
				tableSql.append(") ");
			}
		}

		tableSql.append(")");

		return getRsArrList(
				tableSql.toString(),
				"",
				new String[] { xh },
				new String[] { "xmmc", "xh", "xm", "tsnr", "tssj", "clrxm",
						"clyj", "clsj", "xn", "xq", "alltsnr", "allclyj", "lx" },
				"", model);

	}
}
