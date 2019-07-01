package xsgzgl.xszz.jhzy.cssz;

import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description:ѧ������-��������
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */
public class XszzCsszDao extends CommDAO {

	/**
	 * �������������Ϣ
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean bcCsszxx(XszzCsszActionForm form) throws Exception {
		DAO dao = DAO.getInstance();
		return dao
				.runUpdate(
						"insert into xg_xszz_jhzy_csszb(xn,jtqkdzkssj,jtqkdzjssj,"
								+ "knssqkssj,knssqjssj,knsshkssj,knsshjssj,lzjxjsqkssj,lzjxjsqjssj,"
								+ "lzjxjshkssj,lzjxjshjssj,gjzxjsqkssj,gjzxjsqjssj,gjzxjshkssj,"
								+ "gjzxjshjssj) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
						new String[] { form.getXn(), form.getJtqkdzkssj(),
								form.getJtqkdzjssj(), form.getKnssqkssj(),
								form.getKnssqjssj(), form.getKnsshkssj(),
								form.getKnsshjssj(), form.getLzjxjsqkssj(),
								form.getLzjxjsqjssj(), form.getLzjxjshkssj(),
								form.getLzjxjshjssj(), form.getGjzxjsqkssj(),
								form.getGjzxjsqjssj(), form.getGjzxjshkssj(),
								form.getGjzxjshjssj() });
	}

	/**
	 * ɾ������������Ϣ
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean scCsszxx(XszzCsszActionForm form) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runUpdate("delete from xg_xszz_jhzy_csszb", new String[] {});
	}

	/**
	 * ��ѯ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cxCsszxx() throws Exception {
		DAO dao = DAO.getInstance();
		return dao.getMapNotOut(
				"select * from xg_xszz_jhzy_csszb where rownum <2",
				new String[] {});
	}

	/**
	 * ������һѧ�����������Ϣ
	 * 
	 * @date 2012-12-06
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void copyKnsrd(String xn) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xszz_jhzy_knssqb ");
		sql.append("( ");
		sql.append("xh,xn,sqlb,sqly,sqsj,bzrsh,bzrshsj,bzrshyj, ");
		sql.append("bzrshr,bzrtjdc,fdysh,fdyshsj,fdyshyj,fdyshr, ");
		sql.append("fdytjdc,xysh,xyshsj,xyshyj,xyshr,xytjdc,xxsh,xxshsj, ");
		sql.append("xxshyj,xxshr,xxtjdc,shzt ");
		sql.append(") ");
		sql.append(" select ");
		sql.append("xh,'" + xn + "' xn,sqlb,sqly,sqsj,bzrsh,bzrshsj,bzrshyj, ");
		sql.append("bzrshr,bzrtjdc,fdysh,fdyshsj,fdyshyj,fdyshr, ");
		sql.append("fdytjdc,xysh,xyshsj,xyshyj,xyshr,xytjdc,xxsh,xxshsj, ");
		sql.append("xxshyj,xxshr,xxtjdc,shzt ");
		sql.append(" from xg_xszz_jhzy_knssqb a ");
		sql.append(" where a.shzt='tg' ");
		sql.append(" and exists( ");
		sql.append(" select 1 from ");
		sql.append(" (select * ");
		sql.append(" from (select t.xn ");
		sql.append(" from xg_xszz_jhzy_knssqb t ");
		sql.append(" order by xn desc) ");
		sql.append(" where rownum = 1) b ");
		sql.append(" where a.xn = b.xn ");
		sql.append(" ) ");

		dao.runUpdate(sql.toString(), new String[] {});
	}
	/**
	 * ������һѧ��ļ�ͥ���
	 * @throws Exception 
	 */
	public void copyJtqk(String dqxn,String xn) throws Exception {
		// TODO �Զ����ɷ������
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xszz_jhzy_jtqkdzb");
		sql.append("(");
		sql.append("xh,xn,dzsj,byxx,sjhm,tc,sfgc,sfdq,sflszn,sfdb,jthk,szssx,jtdz,jtdh,jtyb,jtrks,jtrjsr,jtnzsr,srly,yhzzqk,jtszqk,tfsjqk,cjnmqk,jtsyqk,jtqzqk,jtqtqk)");
		sql.append("select xh,'"+xn+"' xn,dzsj,byxx,sjhm,tc,sfgc,sfdq,sflszn,sfdb,jthk,szssx,jtdz,jtdh,jtyb,jtrks,jtrjsr,jtnzsr,srly,yhzzqk,jtszqk,tfsjqk,cjnmqk,jtsyqk,jtqzqk,jtqtqk");
		sql.append(" from xg_xszz_jhzy_jtqkdzb where xn='"+dqxn+"'");
		dao.runUpdate(sql.toString(), new String[]{});
	}
}