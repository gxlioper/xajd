package xgxt.pjpy.zjcm.xwjxj;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class XwjxjNewDAO {

	DAO dao = DAO.getInstance();
	
	//��ѯУ�⽱ѧ����˽��
	private final StringBuilder QUERY_XWJXJSH_SQL = new StringBuilder("select a.*,rownum r,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,(case when cfcs > 0 then 'red' else '' end) color from (")
	                      .append("select pk,xh,xn,xq,jxjdm,jxjmc,xxsh,xxshsj,xxyj,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xm,dypm,zypm,typm,nlpm,zhpm,cfcs from view_pjpy_zjcm_xsjxjb a ")
	                              .append("where exists (select 1 from jxjdmb b where a.jxjdm=b.jxjdm and")
	                                      .append(" exists (select 1 from jxjlbdmb c where b.jxjlb=c.jxjlbdm and c.jxjlbmc like 'У��%'))) a");

	//��ѯ����У�⽱ѧ����Ϣ
	private final StringBuilder QUERY_JXJDGSHXX_SQL = new StringBuilder("select xh,")
                         .append("xn,xq,jxjmc,fdysh,xysh,xxsh,fdyshsj,xyshsj,xxshsj,")
                         .append("fdyyj,xyyj,xxyj,xymc,zymc,bjmc,dyf,zyf,tyf,nlf,dyzhf,")
                         .append("zyzhf,tyzhf,nlzhf,dypm,zypm,typm,nlpm,zhpm,xm,xb,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xqmc from ")
                         .append("view_pjpy_zjcm_xsjxjb a where xh||jxjdm||xn||xq=?");
	
	//�����޸Ľ�ѧ�������Ϣ
	private final StringBuilder UPDATE_JXJDGSHXX_SQL = new StringBuilder("update zjcm_jxjsq set xxsh=?, xxyj=? where pjxh||jxjdm||xn||xq=?");	
	/**
	 * ��ѯУ�⽱ѧ����˽��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXwjxjshResult(XwjxjModel model) throws Exception {
		String[] queryList = new String[] { "xydm", "xn", "xq", "nj", "zydm",
				"bjdm", "jxjdm", "xxsh"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, queryObject.baseListQueryArr, model);
		String[] colList = new String[] { "pk", "color", "r", "xh", "xm", "bjmc",
				"xn", "xqmc", "jxjmc", "dypm", "zypm", "typm", "zhpm", "xxsh" };
		return CommonQueryDAO.commonQuery(getQUERY_XWJXJSH_SQL(),
				queryObject.getQueryString(), queryObject.getInputList(),
				colList, model);
	}
	
	/**
	 * �޸�У�⽱ѧ����˽��
	 * @param pk
	 * @param jg
	 * @return
	 * @throws Exception
	 */
	public boolean updateXwjxjshJg(String[] pk, String jg) throws Exception {
		jg = "yes".equalsIgnoreCase(jg) ? "ͨ��"
				: ("no".equalsIgnoreCase(jg) ? "��ͨ��" : "δ���");
		if (pk != null) {
			return dao.checkBatch(dao.runBatch(appendUpdateSql(pk, jg)));
		}
		return false;
	}
	
	//ƴ���޸Ľ�ѧ�����SQL
	private String[] appendUpdateSql(String[] pkValue, String jg) {
		String[] sql = new String[pkValue.length];
		for (int i=0;i<pkValue.length;i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("update zjcm_jxjsq set xxsh='");
			sb.append(jg);
			sb.append("' where pjxh||jxjdm||xn||xq='");
			sb.append(pkValue[i]);
			sb.append("'");
			sql[i] = sb.toString();
		}
		return sql;
	}
	
	/**
	 * ��ѯ����У�⽱�����Ϣ
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryXwjxjDgxx(String pkValue) {
		return dao.getMapNotOut(getQUERY_JXJDGSHXX_SQL(),
				new String[] { pkValue });
	}

	/**
	 * �޸ĵ���У�⽱ѧ�������Ϣ
	 * 
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean updateXwjxjDgxx(XwjxjModel model, String pkValue)
			throws Exception {
		return dao.runUpdate(getUPDATE_JXJDGSHXX_SQL(), new String[] {
				model.getXxsh(), model.getXxyj(), pkValue });
	}
	
	
	public String getQUERY_XWJXJSH_SQL() {
		return QUERY_XWJXJSH_SQL.toString();
	}

	public String getQUERY_JXJDGSHXX_SQL() {
		return QUERY_JXJDGSHXX_SQL.toString();
	}

	public String getUPDATE_JXJDGSHXX_SQL() {
		return UPDATE_JXJDGSHXX_SQL.toString();
	}
	
	
	
}
