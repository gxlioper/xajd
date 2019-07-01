package xgxt.pjpy.zjcm.tjsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.zjcm.ZhszcpDAO;

public class TjszDAO extends ZhszcpDAO {

	/**
	 * @describe ��������ֶ��б�
	 * @author luo
	 */
	public List<HashMap<String, String>> getZdList(String szlx) {
		DAO dao = new DAO();
		String sql = "select * from jxjtjzdb where szlx =?";
		return dao.getList(sql, new String[] { szlx }, new String[] { "zdmc",
				"zdsm" });

	}

	/**
	 * @describe ��ý�ѧ������б�
	 * @author luo
	 */
	public List<HashMap<String, String>> getJxjList() {
		DAO dao = new DAO();
		String sql = "select jxjdm,jxjmc from jxjdmb where jxjlb = 'У' order by jxjdm";
		return dao.getList(sql, new String[] {}, new String[] { "jxjdm",
				"jxjmc" });
	}

	/**
	 * @describe ��������ƺ�����б�
	 * @author luo
	 */
	public List<HashMap<String, String>> getRychList() {
		DAO dao = new DAO();
		String sql = "select rychdm jxjdm,rychmc jxjmc from rychdmb order by jxjdm";
		return dao.getList(sql, new String[] {}, new String[] { "jxjdm",
				"jxjmc" });
	}
	
	/**
	 * @describe ������������
	 * @author luo
	 * @throws Exception
	 */
	public boolean saveTjsz(TjszModel myModel, HttpServletRequest request)
			throws Exception {
		DAO dao = new DAO();

		// ��������
		String szlx = myModel.getSzlx();
		// ѧ��
		String xn = myModel.getXn();
		// ѧ��
		String xq = myModel.getXq();
		// ��ѧ�����
		String jxjdm = myModel.getJxjdm();
		// �ֶ���
		String zdm = myModel.getZdm();
		// ��������
		String tjlx = myModel.getTjlx();
		tjlx = Base.isNull(tjlx) ? "=" : tjlx;
		// ����ֵ
		String tjz = DealString.toGBK(myModel.getTjz());
		// ����
		String tj = zdm + " " + tjlx + " '" + tjz + "'";

		String sql = "select * from jxjtjzdb where zdmc= ? and szlx = ?";
		HashMap<String, String> map = dao.getMap(sql,
				new String[] { zdm, szlx }, new String[] { "zdsm" });

		String tjmc = map.get("zdsm");

		String pkValue = xn + xq + szlx + jxjdm + zdm;

		boolean flg = StandardOperation.delete("zjcm_pjpy_tjsz",
				"xn||xq||szlx||jxjdm||tjzd", pkValue, request);
		if (flg) {
			flg = StandardOperation.insert("zjcm_pjpy_tjsz", new String[] {
					"xn", "xq", "szlx", "jxjdm", "tj", "tjzd", "tjmc", "tjlx",
					"tjz" }, new String[] { xn, xq, szlx, jxjdm, tj, zdm, tjmc,
					tjlx, tjz }, request);
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe �������
	 */
	public List<HashMap<String, String>> getTjList(TjszModel model,
			String[] colList) {
		DAO dao = new DAO();
		// ѧ��
		String xn = DealString.toGBK(model.getXn());
		// ѧ��
		String xq = DealString.toGBK(model.getXq());
		// ��������
		String szlx = DealString.toGBK(model.getSzlx());
		// ��ѧ�����
		String jxjdm = DealString.toGBK(model.getJxjdm());

		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select a.szlx || a.xn || a.xq ||a.jxjdm || a.tjzd pk,a.szlx, a.xn,a.xq, a.jxjdm, a.tjzd, ");
		sql.append("case when a.szlx = 'rych' then '�����ƺ�' when a.szlx = 'jxj' then '��ѧ��' else '�Ƚ��༶' end szmc, ");
		sql.append("b.jxjmc, a.tjmc, a.tjlx,a.tjz || case when a.tjmc like '%����%' then '%' when a.tjmc like '%��%' then '%' ");
		sql.append("else '' end tjz from zjcm_pjpy_tjsz a left join jxjdmb b on a.jxjdm = b.jxjdm ) ");
		sql.append("where xn = ? and xq = ? and szlx = ? and jxjdm = ?");
		// System.out.println(sql);
		return dao.getList(sql.toString(),
				new String[] { xn, xq, szlx, jxjdm }, colList);
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe ɾ������
	 */
	public boolean delTj(String pk, HttpServletRequest request)
	throws Exception {
		boolean flg = StandardOperation.delete("zjcm_pjpy_tjsz",
				"szlx || xn || xq|| jxjdm || tjzd", pk, request);
		return flg;

	}
	
	/**
	 * ���ѧ������
	 * 
	 * @throws Exception
	 */
	public String getXqmc(String xqdm) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xqmc from xqdzb where xqdm=?",
				new String[] { xqdm }, "xqmc");
	}
	
	/**
	 * ���ѧԺ����
	 * 
	 * @throws Exception
	 */
	public String getXymc(String xydm) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xymc from view_njxyzybj where xydm = ?",
				new String[] { xydm }, "xymc");
	}
	
	/**
	 * ��ð༶����
	 * 
	 * @throws Exception
	 */
	public String getBjmc(String bjdm) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select bjmc from view_njxyzybj where bjdm = ?",
				new String[] { bjdm }, "bjmc");
	}
	
	/**
	 * ���ѧԺ�ۺ����ʲ����б�
	 */
	public List<HashMap<String, String>> getXyzhList(String bjdm, String xn,
			String xq, String xqmc) {
		
		DAO dao = DAO.getInstance();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,b.jxjmc xnmc,c.jxjmc xwmc,b.bjgkms,d.rychmc,f.wjcs, ");
		sql.append("(select djksmc from djksdmb where djksdm = b.yygjqk) yygjqk, ");
		sql.append("(select djksmc from djksdmb where djksdm = b.jsjgjqk) jsjgjqk, ");
		sql.append("b.tqxf,e.num from (select a.xh, a.xm, a.bjdm, b.dyf, b.dypm, b.zyf, ");
		sql.append("b.zypm, b.tyf, b.typm, b.nlf, b.nlpm, b.zhf, b.zhpm ");
		sql.append("from (select xh, xm,bjdm from view_xsjbxx a where bjdm = ?) a left join (select xh,xn,xq, ");
		sql.append("dyf,(rank()over(partition by xn,xq,bjdm order by to_number(dyf) desc nulls last)) dypm, ");
		sql.append("zyf,(rank()over(partition by xn,xq,bjdm order by to_number(zyf) desc nulls last)) zypm, ");
		sql.append("tyf,(rank()over(partition by xn,xq,bjdm order by to_number(tyf) desc nulls last)) typm, ");
		sql.append("nlf,(rank()over(partition by xn,xq,bjdm order by to_number(nlf) desc nulls last)) nlpm, ");
		sql.append("zhf,zhpm from view_zjcm_zhf) b on b.xn = ? and b.xq = ? and a.xh = b.xh) a ");
		
		sql.append("left join (select t.xh, max(ltrim(sys_connect_by_path((t.jxjmc) || '', '��'), '��')) jxjmc,");
		sql.append("t.tqxf,t.yygjqk,t.jsjgjqk,t.bjgkms from (select a.xh,c.jxjmc,");
		sql.append("a.tqxf,a.yygjqk,a.jsjgjqk,a.bjgkms,row_number() over(partition by b.xh order by a.xh) pno, ");
		sql.append("row_number() over(partition by b.xh order by a.xh) - 1 sno from zjcm_jxjsq a, zjcm_jxjsq b, jxjdmb c ");
		sql.append("where a.xh = b.xh and a.jxjdm = c.jxjdm and a.xn = ? and a.xq = ? and a.xxsh = 'ͨ��' ");
		sql.append("and c.jxjlb = 'У' group by a.xh, b.xh, c.jxjmc,a.tqxf,a.yygjqk,a.jsjgjqk,a.bjgkms) t start with pno = 1 connect by prior pno = sno ");
		sql.append("and prior t.xh = t.xh group by t.xh,t.tqxf,t.yygjqk,t.jsjgjqk,t.bjgkms order by t.xh) b on a.xh = b.xh ");
		
		sql.append("left join (select t.xh, max(ltrim(sys_connect_by_path((t.jxjmc) || '', '��'), '��')) jxjmc ");
		sql.append("from (select a.xh,c.jxjmc,row_number() over(partition by b.xh order by a.xh) pno, ");
		sql.append("row_number() over(partition by b.xh order by a.xh) - 1 sno from zjcm_jxjsq a, zjcm_jxjsq b, jxjdmb c ");
		sql.append("where a.xh = b.xh and a.jxjdm = c.jxjdm and a.xn = ? and a.xq = ? and a.xxsh = 'ͨ��' ");
		sql.append("and c.jxjlb = '��' group by a.xh, b.xh, c.jxjmc) t start with pno = 1 connect by prior pno = sno ");
		sql.append("and prior t.xh = t.xh group by t.xh order by t.xh) c on a.xh = c.xh ");
		
		sql.append("left join (select t.xh, max(ltrim(sys_connect_by_path((t.rychmc) || '', '��'), '��')) rychmc ");
		sql.append("from (select a.xh,c.rychmc,row_number() over(partition by b.xh order by a.xh) pno, ");
		sql.append("row_number() over(partition by b.xh order by a.xh) - 1 sno from zjcm_rychsqb a, zjcm_rychsqb b, rychdmb c ");
		sql.append("where a.xh = b.xh and a.rychdm = c.rychdm and a.xn = ? and a.xqdm = ? and a.xxsh = 'ͨ��' ");
		sql.append("group by a.xh, b.xh, c.rychmc) t start with pno = 1 connect by prior pno = sno ");
		sql.append("and prior t.xh = t.xh group by t.xh order by t.xh) d on a.xh = d.xh ");
		
		sql.append("left join (select count(xh) num,xh,xxsh,xn,xq from view_wjcf group ");
		sql.append("by xh,xxsh,xn,xq ) e on a.xh = e.xh and e.xxsh = 'ͨ��' and ");
		sql.append("e.xn = ? and e.xq = ? ");
		
		sql.append("left join (select xh,sum(wjcs) wjcs from pjpy_xfjs_xsjljcb where wjlxdm = '001' and xn = ? and xq = ? group by xh");
		sql.append(") f on a.xh = f.xh order by bjdm,to_number(zhpm)");
		
		//System.out.println(sql);
		return dao.getList(sql.toString(), new String[] { bjdm, xn, xqmc, xn,
				xq, xn, xq, xn, xq, xn, xq, xn, xq }, new String[] { "xh",
				"xm", "dyf", "dypm", "zyf", "zypm", "tyf", "nlf", "nlpm",
				"zhf", "zhpm", "xnmc", "xwmc", "rychmc", "bjgkms", "yygjqk",
				"jsjgjqk", "tqxf", "wjcs", "num" });
	}
	
	/**
	 * ��ð༶��ý�ѧ�����
	 */
	public HashMap<String, String> getBjJxj(String bjdm, String xn, String xq) {
		
		DAO dao = DAO.getInstance();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select bjdm,bjmc,num,sum(ydjxj) ydjxj,sum(rdjxj) rdjxj, ");
		sql.append("sum(sdjxj) sdjxj from (select a.xh, a.bjdm, b.bjmc, b.num, ");
		sql.append("case when a.jxjmc like '%һ��%' then 1 else 0 end ydjxj, ");
		sql.append("case when a.jxjmc like '%����%' then 1 else 0 end rdjxj, ");
		sql.append("case when a.jxjmc like '%����%' then 1 else 0 end sdjxj ");
		sql.append("from (select a.xh, b.jxjmc, c.bjdm from zjcm_jxjsq a, jxjdmb b, ");
		sql.append("view_stu_details c where a.jxjdm = b.jxjdm and a.xh = c.xh ");
		sql.append("and a.xn = ? and a.xq = ? and a.xxsh = 'ͨ��' and c.bjdm = ?) a, ");
		sql.append("(select bjdm, bjmc, count(xh) num from view_xsjbxx group by bjdm, bjmc) b ");
		sql.append("where a.bjdm = b.bjdm) group by bjdm,bjmc,num ");
		
		return dao.getMap(sql.toString(), new String[] { xn, xq, bjdm },
				new String[] { "bjmc", "num", "ydjxj", "rdjxj", "sdjxj" });
	}
	/**
	 * ѧԺ���������������ܱ�
	 */
	public List<HashMap<String, String>> getXyPymdList(String xydm, String xn,
			String xq) {
	
		DAO dao = DAO.getInstance();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select bjmc,xm,djjxj,shxs,yxgb,dxjxj,xwjxj,kh from (select a.bjdm,a.bjmc,a.xh,a.xm,b.jxjmc ");
		sql.append("djjxj,c.rychmc shxs,d.rychmc yxgb,e.jxjmc dxjxj,f.jxjmc xwjxj,g.kh ");

		sql.append("from (select bjdm, bjmc, xh, xm from view_xsjbxx where xydm = '"+xydm+"' order by bjdm) a ");

		sql.append("left join (select a.xh, b.jxjmc from zjcm_jxjsq a, jxjdmb b where a.jxjdm = b.jxjdm ");
		sql.append("and a.xxsh = 'ͨ��' and a.xn = '"+xn+"' and a.xq = '"+xq+"' and (b.jxjmc like '%һ��%' ");
		sql.append("or b.jxjmc like '%����%' or b.jxjmc like '%����%')) b on a.xh = b.xh  ");

		sql.append("left join (select a.xh, b.rychmc from zjcm_rychsqb a, rychdmb b where a.rychdm = b.rychdm ");
		sql.append("and a.xxsh = 'ͨ��' and a.xn = '"+xn+"' and a.xqdm = '"+xq+"' and b.rychmc like '%����%') c on a.xh = c.xh ");

		sql.append("left join (select a.xh, b.rychmc from zjcm_rychsqb a, rychdmb b where a.rychdm = b.rychdm ");
		sql.append("and a.xxsh = 'ͨ��' and a.xn = '"+xn+"' and a.xqdm = '"+xq+"' and b.rychmc like '%�ɲ�%') d on a.xh = d.xh ");

		sql.append("left join (select t.xh,max(ltrim(sys_connect_by_path((t.jxjmc) || '', '��'), '��')) jxjmc ");
		sql.append("from (select a.xh,c.jxjmc,row_number() over(partition by b.xh order by a.xh) pno, ");
		sql.append("row_number() over(partition by b.xh order by a.xh) - 1 sno from zjcm_jxjsq a, zjcm_jxjsq b, jxjdmb c ");
		sql.append("where a.xh = b.xh and a.jxjdm = c.jxjdm and a.xn = '"+xn+"' and a.xq = '"+xq+"' and a.xxsh = 'ͨ��' ");
		sql.append("and c.jxjlb = 'У' and jxjmc not like '%һ��%' and jxjmc not like '%����%' and jxjmc not like '%����%' ");
		sql.append("group by a.xh, b.xh, c.jxjmc) t start with pno = 1 connect by prior pno = sno and prior t.xh = t.xh ");
		sql.append("group by t.xh order by t.xh) e on a.xh = e.xh ");

		sql.append("left join (select t.xh, max(ltrim(sys_connect_by_path((t.jxjmc) || '', '��'), '��')) jxjmc ");
		sql.append("from (select a.xh,c.jxjmc,row_number() over(partition by b.xh order by a.xh) pno, ");
		sql.append("row_number() over(partition by b.xh order by a.xh) - 1 sno from zjcm_jxjsq a, zjcm_jxjsq b, jxjdmb c ");
		sql.append("where a.xh = b.xh and a.jxjdm = c.jxjdm and a.xn = '"+xn+"' and a.xq = '"+xq+"' and a.xxsh = 'ͨ��' ");
		sql.append("and c.jxjlb = '��' group by a.xh, b.xh, c.jxjmc) t start with pno = 1 connect by prior pno = sno ");
		sql.append("and prior t.xh = t.xh group by t.xh order by t.xh) f on a.xh = f.xh ");

		sql.append("left join view_stu_details g on a.xh = g.xh) where djjxj is not null or shxs is not null ");
		sql.append("or yxgb is not null or dxjxj is not null or xwjxj is not null order by bjdm ");
		
		return dao.getList(sql.toString(), new String[] {}, new String[] { "bjmc","xm","djjxj","shxs","yxgb","dxjxj","xwjxj","kh" });
	}
		
}
