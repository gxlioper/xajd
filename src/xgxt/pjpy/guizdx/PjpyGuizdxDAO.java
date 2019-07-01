package xgxt.pjpy.guizdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.PjpyTyDAO;
import xgxt.pjpy.PjpyTyForm;

public class PjpyGuizdxDAO extends PjpyTyDAO {

	/**
	 * ���ѧԺ�����б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getXyRsList(PjpyTyForm model) {

		DAO dao = DAO.getInstance();
		
		String zqSql = "select xn,xq,nd from pjpy_pjzqb where rownum = 1";
		HashMap<String, String> map = dao.getMap(zqSql, new String[] {},
				new String[] { "xn", "xq", "nd" });
		
		String pjzq = "";

		if ("checked".equalsIgnoreCase(map.get("xn"))) {
			pjzq = "ѧ��";
		} else if ("checked".equalsIgnoreCase(map.get("xq"))) {
			pjzq = "ѧ��";
		} else if ("checked".equalsIgnoreCase(map.get("nd"))) {
			pjzq = "���";
		}
		
		// �꼶
		String nj = model.getNj();
		// ��ָ����ѧԺ
		String[] szbm = model.getSzbm();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.nj, a.xydm, a.xymc, nvl(b.num,0) num ");
		sql.append("from (select distinct xydm, xymc, nj ");
		sql.append("from view_njxyzybj a where 1 = 1 ");
		sql.append(Base.isNull(nj) ? "" : "and nj = ? ");
		if (szbm != null && szbm.length > 0) {
			sql.append("and (");
			for (int i = 0; i < szbm.length; i++) {
				if (i == 0) {
					sql.append("xydm = '" + szbm[i] + "' ");
				} else {
					sql.append("or xydm = '" + szbm[i] + "' ");
				}
			}
			sql.append(")");
		}
		sql.append(" ) a left join (select xydm, nj, count(xh) num ");
		sql.append(" from ");
		//sql.append(" view_xsjbxx ");
		sql.append(" (");
		
		sql.append(" select distinct xh, xydm, nj from view_pjpy_zhszcpb where jb = '1' and fs is not null ");
		
//		sql.append("(");
//		sql.append("(select xh,xydm, nj,xn,xq,nd from view_gzdx_dysjfb union all ");
//		sql.append(" select xh,xydm, nj,xn,xq,nd from view_gzdx_pdbxfb union all ");
//		sql.append(" select xh,xydm, nj,xn,xq,nd from view_gzdx_jlfb   union all ");
//		sql.append(" select xh,xydm, nj,xn,xq,nd from view_gzdx_wthdbxfb union all ");
//		sql.append(" select xh,xydm, nj,xn,xq,nd from view_gzdx_wtjlfb)");
//		sql.append(")");
		//sql.append(" where 1 = 1 ");
		sql.append("ѧ��".equalsIgnoreCase(pjzq) ? "" : " and xn = '"+Base.getJxjsqxn()+"' ");
		sql.append("ѧ��".equalsIgnoreCase(pjzq) ? "" : " and xn = '"+Base.getJxjsqxn()+"' ");
		sql.append("ѧ��".equalsIgnoreCase(pjzq) ? "" : " and xq = '"+Base.getJxjsqxq()+"' ");
		sql.append("���".equalsIgnoreCase(pjzq) ? "" : " and nd = '"+Base.getJxjsqnd()+"' ");
		
		sql.append(" ) ");
		sql.append(" group by xydm, nj) b on a.nj = b.nj ");
		sql.append(" and a.xydm = b.xydm order by nj, xydm ");
		
		String[] inputValue = Base.isNull(nj) ? new String[] {}
				: new String[] { nj };
		String[] outputValue = new String[] { "nj", "xydm", "xymc", "num" };
		
		List<HashMap<String,String>> list = dao.getList(sql.toString(), inputValue, outputValue);
		
		return list;
	}
	
	/**
	 * ���רҵ�����б�
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getZyRsList(PjpyTyForm model) {

		DAO dao = DAO.getInstance();

		// ѧ��
		String xn = model.getXn();
		// �꼶
		String nj = model.getNj();
		// ѧԺ����
		String xydm = model.getXydm();
		// רҵ����
		String zydm = model.getZydm();
		// ��������
		String szlx = model.getLx();
		szlx = Base.isNull(szlx) ? "jxj" : szlx;
		// ��ѧ�����
		String jxjdm = model.getJxjdm();
		//SQL
		StringBuilder sql = new StringBuilder();

		sql.append("select distinct ? xn,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,? szlx, ");
		sql.append("case when ? = 'jxj' then '��ѧ��' else '�����ƺ�' end lxmc,");
		sql.append("(select c.jxjmc from jxjdmb c where c.jxjdm = ? ) jxjmc,");
		sql.append("b.jxjdm,b.szrs,b.bmlx,b.jxjdm,b.szlx from view_njxyzybj a ");
		sql.append("left join guizdx_pjpy_rssz b on a.nj = b.sznj ");
		sql.append("and a.zydm = b.szbm and b.bmlx = 'zy' and b.jxjdm = ? ");
		sql.append("where a.xydm = ? and a.nj = ? ");
		sql.append(Base.isNull(zydm) ? "" : "and a.zydm = '"+zydm+"'");
		sql.append(" order by a.zydm");

		String[] inputValue = new String[] { xn, szlx, szlx, jxjdm, jxjdm,
				xydm, nj };
		String[] outputValue = new String[] { "xn", "nj", "xymc", "zymc",
				"lxmc", "jxjmc", "szrs", "zydm", "xydm", "bmlx", "jxjdm",
				"szlx" };

		ArrayList<String[]> list = dao.rsToVator(sql.toString(), inputValue,
				outputValue);

		return list;
	}
	
	/**
	 * ���ѧԺ�ɷ����ʣ������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String getXySyRs(PjpyTyForm model) {

		DAO dao = DAO.getInstance();

		// ѧ��
		String xn = model.getXn();
		// ��������
		String szlx = model.getSzlx();
		szlx = Base.isNull(szlx) ? "jxj" : szlx;
		// ��ѧ�����
		// ��������
		String bmlx = "xy";
		// ѧԺ����
		String szbm = model.getXydm();
		// ��ѧ�����
		String jxjdm = model.getJxjdm();
		// �꼶
		String sznj = model.getNj();

		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select (xyrs - zyrs) syrs from (select a.xn,a.szlx, ");
		sql.append("a.bmlx,a.szbm,a.jxjdm,a.sznj,a.szrs xyrs,b.szrs zyrs ");
		sql.append("from (select a.xn, a.szlx, a.bmlx, a.szbm, a.jxjdm, a.sznj, a.szrs ");
		sql.append("from guizdx_pjpy_rssz a  where a.xn = ? ");
		sql.append("and a.szlx = ? and a.bmlx = ? and a.szbm = ? ");
		sql.append("and a.jxjdm = ? and a.sznj = ?) a left join (select b.nj, ");
		sql.append("b.xydm,b.xn,b.szlx,b.jxjdm,sum(szrs) szrs from (select distinct a.nj, ");
		sql.append("a.xydm,a.xymc,a.zydm,a.zymc,b.xn,b.szlx,b.jxjdm,b.szrs ");
		sql.append("from view_njxyzybj a, guizdx_pjpy_rssz b ");
		sql.append("where a.zydm = b.szbm and a.nj = b.sznj and b.bmlx = 'zy') b ");
		sql.append("group by b.nj, b.xydm, b.xn, b.szlx, b.jxjdm) b on a.xn = b.xn ");
		sql.append("and a.szlx = b.szlx and a.szbm = b.xydm ");
		sql.append("and a.jxjdm = b.jxjdm and a.sznj = b.nj) ");

		String[] inputValue = new String[] { xn, szlx, bmlx, szbm, jxjdm, sznj };
		String outputValue = "syrs";

		String syrs = dao.getOneRs(sql.toString(), inputValue, outputValue);

		return syrs;

	}
}
