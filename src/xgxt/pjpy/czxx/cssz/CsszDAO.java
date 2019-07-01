package xgxt.pjpy.czxx.cssz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.pjpy.czxx.PjpyDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class CsszDAO {
	
	//��ѯ�ۺϲ���������Ϣ
	final StringBuffer QUERY_ZHSZCPPM = new StringBuffer("select a.*,rownum r")
					 .append(" from (select lb||dm pk,lb,")
					 .append("(case when lb='jxj' then '��ѧ��' else '�����ƺ�' ")
	                 .append("end) lbmc,dm,bfb,(case when lb='jxj' then (select")
	                 .append(" jxjmc from jxjdmb b where a.dm=b.jxjdm) else (" )
	                 .append("select rychmc from rychdmb b where a.dm=b.rychdm)")
	                 .append(" end) mc from zhszpmbfbb a) a");
	
	//ɾ���ۺϲ���������Ϣ
	final StringBuffer DELETE_ZHSZCPPM = new StringBuffer("delete from ")
					 .append("zhszpmbfbb where lb = ? and dm = ?");
	
	//ɾ����������ά����Ϣ
	final StringBuffer DELETE_KGWHXX = new StringBuffer("delete from pjpykgszb");
	
	//���濪������ά����Ϣ
	final StringBuffer SAVE_KGWHXX = new StringBuffer("insert into pjpykgszb")
	                 .append(" (jxjsq,jxjsh,rychsq,rychsh) values (?,?,?,?)");
	                 
	//��ѯ��������ά����Ϣ
	final StringBuffer QUERY_KGWHXX = new StringBuffer("select jxjsq,jxjsh")
	                 .append(",rychsq,rychsh from pjpykgszb");
	
	//��ѯ�����ƺ������������Ϣ
	final StringBuffer QUERY_RYCHSQTJXX = new StringBuffer("select jxjdm dm,")
	                 .append("jxjmc mc,(select (case when count(*)>0 then ")
	                 .append("'checked' else '' end) from czxx_rychtjszb b where")
	                 .append(" a.jxjdm=b.dm) chk from jxjdmb a");
	
	//��ѯ�����ƺ���������ɼ�������Ϣ
	final StringBuffer QUERY_RYCHSQDYCJXX = new StringBuffer("select cj from ")
	                 .append("czxx_rychtjszb where cj is not null and rownum=1");
	
	//ɾ�������ƺ�����������Ϣ
	final StringBuffer DELETE_RYCHSQTJXX = new StringBuffer("delete from")
				     .append(" czxx_rychtjszb");
	
	//���������ƺ��������������ɼ���Ϣ
	final StringBuffer SAVE_RYCHSQTJDYCYXX = new StringBuffer("insert into ")
	                 .append("czxx_rychtjszb(cj) values (?)"); 
	
	DAO dao = DAO.getInstance();
	
	/**
	 * �����ۺϲ�����������
	 * @param model
	 * @return
	 */
	public boolean saveZhszcppmBlf(CsszModel model) {
		try {
			return dao
					.runUpdate(
							"insert into zhszpmbfbb(lb,dm,bfb) values (?,?,?)",
							new String[] { model.getLb(), model.getDm(),
									model.getBfb() });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ɾ���ۺϲ���������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteZhszcppmBlf(CsszModel model){
		try {
			return dao.runUpdate(DELETE_ZHSZCPPM.toString(), new String[] {
					model.getLb(), model.getDm() });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ɾ���ۺ����ʲ���������������
	 * @param sqlArr
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteZhszcppmBlf(String[] sqlArr) throws SQLException{
		PjpyDAO myDAO = new PjpyDAO();
		return myDAO.excuteSqlResult(sqlArr);
	}
	
	/**
	 * ��ѯ�ۺ����ʲ�������������Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryResult(CsszModel model) throws Exception {
		String[] oputputArr = { "pk", "lbmc", "mc", "bfb" };
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(new String[] { "lb", "dm" }, null, model);
		return CommonQueryDAO.commonQuery(QUERY_ZHSZCPPM.toString(),
				queryObject.getQueryString(), queryObject.getInputList(),
				oputputArr, model);
	}
	
	/**
	 * ����������鷵�ز�ѯ��ͷ
	 * @param en
	 * @param cn
	 * @return
	 */
	public List<HashMap<String, String>> queryTitle(String[] en, String[] cn) {
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ɾ���������ſ���������Ϣ
	 * @return
	 */
	public boolean deleteKgwhxx() {
		try {
			return dao.runUpdate(DELETE_KGWHXX.toString(), new String[]{});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * �����������ſ���������Ϣ
	 * @param model
	 * @return
	 */
	public boolean saveKgwhxx(CsszModel model) {
		try {
			return dao.runUpdate(SAVE_KGWHXX.toString(), new String[] {
					model.getJxjsq(), model.getJxjsh(), model.getRychsq(),
					model.getRychsh() });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ��ѯ����������Ϣ
	 * @return
	 */
	public HashMap<String, String> queryKgwhxx() {
		return dao.getMapNotOut(QUERY_KGWHXX.toString(), new String[]{});
	}
	
	/**
	 * ��ѯ�����ƺ������������Ϣ
	 * @return
	 */
	public List<HashMap<String, String>> queryRychsqtjxx() {
		return dao.getList(QUERY_RYCHSQTJXX.toString(), new String[] {},
				new String[] { "dm", "mc", "chk" });
	}
	
	/**
	 * ��ѯ�����ƺ�����������еĵ����ɼ���Ϣ
	 * @return
	 */
	public String queryRychsqdycjtj() {
		return dao.getOneRs(QUERY_RYCHSQDYCJXX.toString(), new String[]{}, "cj");
	}
	
	/**
	 * ɾ�������ƺ������������Ϣ
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRychsqtjxx() throws Exception{
		return dao.runUpdate(DELETE_RYCHSQTJXX.toString(), new String[]{});
	}
	
	public boolean saveRychsqtjDycjxx(CsszModel model) throws Exception {
		return dao.runUpdate(SAVE_RYCHSQTJDYCYXX.toString(),
				new String[] { model.getCj() });
	}
}
