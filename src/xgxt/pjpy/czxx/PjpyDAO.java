package xgxt.pjpy.czxx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

public class PjpyDAO {

	DAO dao = DAO.getInstance();
	
	//��ѯ�ȼ���������
	final StringBuffer QUERY_XSDJKSMC = new StringBuffer("select djksmc||'('||")
	           .append("cj||'��)' mc from xsdjksb where xh=? and xn=? and xq=?");
	
	/**
	 * ƴ��������SQL���ִ��
	 * @param cbv  ��������ֵ
	 * @param tableName  ����
	 * @param pkzd       �����ֶ� ��ʽ:a||b
	 * @return
	 */
	public String[] appendBatchSql(String[] cbv, String tableName, 
									String pkzd) {
		String[] sqlArr = new String[cbv.length];
		for (int i = 0; i < sqlArr.length; i++) {
			StringBuffer sql = new StringBuffer("delete from ");
			sql.append(tableName);
			sql.append(" where ");
			sql.append(pkzd);
			sql.append("='");
			sql.append(cbv[i]);
			sql.append("'");
			sqlArr[i] = sql.toString();
		}
		return sqlArr;
	}
	
	/**
	 * ����SQL����,����ִ�н��
	 * @param sqlArr
	 * @return
	 * @throws SQLException
	 */
	private int[] executeSql(String[] sqlArr) throws SQLException{		
		return dao.runBatch(sqlArr);
	}
	
	/**
	 * ��������ִ�н�����м��
	 * @param sqlArr
	 * @return
	 * @throws SQLException
	 */
	public boolean excuteSqlResult(String[] sqlArr) throws SQLException{
		return dao.checkBatch(executeSql(sqlArr));
	}
	
	/**
	 * ��ѧ������б�
	 * @return
	 */
	public List<HashMap<String, String>> queryJxjdmList() {
		return dao.getList("select jxjdm dm,jxjmc mc from jxjdmb order by jxjdm",
				new String[]{}, new String[] { "dm", "mc" });
	}
	
	/**
	 * �����ƺŴ����б�
	 * @return
	 */
	public List<HashMap<String, String>> queryRychdmList() {
		return dao.getList("select rychdm dm,rychmc mc from rychdmb order by rychdm",
				new String[]{}, new String[] { "dm", "mc" });
	}
	
	
	//����Ϊ�ṩ���뵳��������ģ��Ľӿ�
	/**
	 * ����ѧ��,ѧ��,ѧ�ڷ���ѧ���ĳɼ��༶���� 
	 *    �޳ɼ����� ""
	 */
	public String queryStucjbjpmByxh(String xh, String xn, String xq) {
		return dao.getOneRs("select xh,xn,xq,bjdm,cj,pjcj from (select xh,xn,xq,bjdm,cj,dense_rank() over (partition by xn,xq,bjdm order by to_number(cj) desc nulls last) pjcj from (select xh,xn,xq,bjdm,avg(cj) cj from view_cjb group by xh,xn,xq,bjdm)) where xh=? and xn=? and xq=?",
				new String[] {xh,xn,xq}, "pjcj");
		
	}
	
	/**
	 * ����ѧ��,ѧ��,ѧ�ڷ���ѧ�����۲�༶����
	 *     �޳ɼ����� ""
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 */
	public String queryStuZcpmByxh(String xh, String xn, String xq) {
		return "";
	}
	
	/**
	 * ����ѧ��,ѧ��,ѧ�ڷ���ѧ���Ĳ������Ŀ�ɼ�
	 *     �޼����Ŀ���� "",������������,�ָ�
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 */
	public String queryStuBjgkmByxh(String xh, String xn, String xq) {
		List<String[]> rs = dao.rsToVator("select kcmc||'('||cj||'��)' mc from view_cjb where cj <60 and xh=? and xn=? and xq=?",
				new String[] {xh,xn,xq}, new String[] {"mc"});
		String result = "";
		if (!rs.isEmpty()) {
			for (String[] s : rs) {
				result += s != null && s.length ==0 ? s[0] : "";
				result += ",";
			}
		}
		return StringUtils.isNull(result) ? "" : result.substring(0,result.length()-1);
	}
	
	/**
	 * ����ѧ��,ѧ��,ѧ�ڷ���ѧ���Ľ�ѧ�����Ϣ
	 *     �޻��򷵻� "",������������,�ָ�
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 */
	public String queryStuHjxxByxh(String xh, String xn, String xq) {
		List<String[]> rs = dao.rsToVator("select jxjdm,(select jxjmc from jxjdmb b where a.jxjdm=b.jxjdm) jxjmc from xsjxjb a where xh=? and xn=? and xq=? and xxsh='ͨ��' and xysh='ͨ��'",
				new String[] {xh,xn,xq}, new String[] {"jxjmc"});
		String result = "";
		if (!rs.isEmpty()) {
			for (String[] s : rs) {
				result += s != null && s.length ==0 ? s[0] : "";
				result += ",";
			}
		}
		return StringUtils.isNull(result) ? "" : result.substring(0,result.length()-1);
	}
	
	/**
	 * ����ѧ��,ѧ��,ѧ�ڷ���ѧ����Υ�ʹ�����Ϣ
	 *     �޻��򷵻� "",������������,�ָ�
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 */
	public String queryStuWjcfxxByxh(String xh, String xn, String xq) {
		List<String[]> rs = dao.rsToVator("select cflb,cfyy,(select cflbmc from cflbdmb b where a.cflb=b.cflbdm) cflbmc,(select cfyymc from cfyydmb b where a.cfyy=b.cfyydm) cfyymc from wjcfb a where xh=? and xn=? and xq=?",
						new String[] {xh,xn,xq}, new String[] {"cflbmc"});
		String result = "";
		if (!rs.isEmpty()) {
			for (String[] s : rs) {
				result += s != null && s.length ==0 ? s[0] : "";
				result += ",";
			}
		}
		return StringUtils.isNull(result) ? "" : result.substring(0,result.length()-1);
	}
	
	/**
	 * ����ѧ��,ѧ��,ѧ�ڷ���ѧ���ĵȼ�������Ϣ
	 *     �޻��򷵻� "",������������,�ָ�
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 */
	public String queryStuDjksxxByxh(String xh, String xn, String xq) {
		return dao.getOneRs(getQUERY_XSDJKSMC(),new String[] {xh,xn,xq}, "mc");
	}

	public String getQUERY_XSDJKSMC() {
		return QUERY_XSDJKSMC.toString();
	}
}
