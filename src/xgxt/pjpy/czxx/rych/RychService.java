package xgxt.pjpy.czxx.rych;

import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;


import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.czxx.jxj.JxjModel;
import xgxt.pjpy.czxx.tycj.TycjDAO;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;

public class RychService {

	RychDAO dao = new RychDAO();
	
	/**
	 * ��ѯ��ѧ���ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryJxjTitle() {
		String[] en = new String[]{"pk",
				"dis", "r", "xn", "xqmc", "xh", "xm", "bjmc", "jxjmc", "jlje","zfpm",
				"fdysh", "xysh", "xxsh" };
		String[] cn = new String[]{"pk",
				"dis", "�к�", "ѧ��", "ѧ��", "ѧ��", "����", "�༶", "��ѧ��","�������", "�۲��ܳɼ�����",
				"����Ա���", Base.YXPZXY_KEY+"���", "ѧУ���" };
		DAO mydao = DAO.getInstance();
		return mydao.arrayToList(en, cn);
	}
	
	/**
	 * ��ѧ����������ѯ
	 * @param userType
	 * @param isFdy
	 * @param userName
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjReuslt(String userType, String isFdy,String userName,
			JxjModel model) throws Exception {
		String fdySql = "true".equalsIgnoreCase(isFdy) ? " and exists(select 1 from fdybjb c "
				+ "where a.bjdm=c.bjdm and c.zgh='"
				+ userName
				+ "')"
				: "" ;
		return dao.queryJxjResult(model, userType, fdySql);
	}
	
	/**
	 * ��ѯѧ��������Ϣ
	 * @param paramMap
	 * @return
	 */
	public List<String[]> queryStuWjcfxx(HashMap<String, String> paramMap) {
		return dao.queryStuWjcfxx(paramMap);
	}
	
	/**
	 * ��ѯѧ���γ̳ɼ���Ϣ
	 * @param paramMap
	 * @return
	 */
	public List<String[]> queryStuKccjxx(HashMap<String, String> paramMap) {
		return dao.queryStuKccjxx(paramMap);
	}
	
	public String getDisSqlByQuery(String userType, String isFdy, String type) {
		if ("qry".equalsIgnoreCase(type)) {
			if (UserTypePd.isFdy(isFdy)) {
				return " (case when xysh='ͨ��' or xxsh='ͨ��' then 'disabled' else '' end) dis,";
			} else if (UserTypePd.isXy(userType)) {
				return " (case when xxsh='ͨ��' then 'disabled' else '' end) dis,";
			} else {
				return " '' dis,";
			}
		} else {
			if (UserTypePd.isFdy(isFdy)) {
				return " (case when (select tjzt from PJPY_BMSHTJB b where b.bm='bj' and " +
						"a.bjdm=b.dm and a.xn||a.xq=b.zjz and b.tjxm='rych')='1' or a.xysh='ͨ��' or a.xxsh='ͨ��'" +
						" then 'disabled' else '' end) dis,(select tjr from pjpy_bmshtjb b " +
						"where b.tjxm='rych' and b.bm='bj' and b.zjz=a.xn||a.xq and a.bjdm=b.dm and rownum=1) " +
						"�ύ��,(select tjsj from pjpy_bmshtjb b where b.tjxm='rych' and b.bm='bj' " +
						"and b.zjz=a.xn||a.xq and a.bjdm=b.dm and rownum=1) �ύʱ��,";
			} else {
				return " '' dis,";
			}
		}
		
	}
	
	/**
	 * �޸Ľ�ѧ�����״̬
	 * @param pkValue
	 * @param jg
	 * @param userType
	 * @param isFdy
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjplshjg(String[] pkValue, String jg,
			String userType, String isFdy) throws Exception{
		if (pkValue == null) {
			return false;
		} else {
			jg = "tg".equalsIgnoreCase(jg) ? "ͨ��"
					: ("btg".equalsIgnoreCase(jg) ? "��ͨ��" : "δ���");			
			String sh = UserTypePd.isFdy(isFdy) ? "fdysh" : (UserTypePd
					.isXy(userType) ? "xysh" : "xxsh");
			
			String[] sqlArr = new String[pkValue.length];
			for (int i=0;i<pkValue.length;i++) {
				StringBuilder sql = new StringBuilder("update xsrychb set ");
				sql.append(sh);
				sql.append("='");
				sql.append(jg);
				sql.append("' where xh||xn||rychdm||xq = '");
				sql.append(pkValue[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
			
			TycjDAO mydao = new TycjDAO();
			return mydao.excuteSqlResult(sqlArr);
		}
	}
	
	public boolean updateJxjdgshjg(String pkValue, String sh, String yj,
			String userType, String isFdy) throws Exception {
		String shzd = "";
		if (UserTypePd.isFdy(isFdy)) {
			shzd = "fdysh";
		} else if (UserTypePd.isXy(userType)) {
			shzd = "xysh";
		} else {
			shzd = "xxsh";
		}
		StringBuilder sql = new StringBuilder("update xsrychb set ");
		sql.append(shzd);
		sql.append("= ?,");
		sql.append("fdysh".equalsIgnoreCase(shzd) ? "fdyyj" : ("xysh"
				.equalsIgnoreCase(shzd) ? "xyyj" : "xxyj"));
		sql.append("=? where xh||xn||rychdm||xq=?");
		return dao.jxjDgshjg(pkValue, sql.toString(), sh, yj);
	}
	
	/**
	 * ����Ա�ύ��˽��
	 * @param model
	 * @param userName
	 * @param xm
	 * @param zt
	 * @param bmlb
	 * @return
	 * @throws Exception
	 */
	public boolean fdyTjshjg(String bmdm,JxjModel model, String userName, String xm,
			String zt, String bmlb) throws Exception {
		return dao.fdyTjJxjjg(bmdm,model, userName, xm, zt, bmlb);
	}
	
	/**
	 * ��ѯ�ύ״̬
	 * @param dm
	 * @param model
	 * @param lb
	 * @return
	 */
	public String queryTjzt(String dm, JxjModel model, String lb, String xmlb) {
		if (StringUtils.isNull(dm)) {
			return "";
		}
		String tjz = dao.queryTjzt(dm, model, lb, xmlb); 
		return StringUtils.isNull(tjz) ? "δ�ύ" : tjz;
	}
	
	/**
	 * ��ѯѧԺ��ѧ����˱�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryXyJxjshTitle(String userType) {
		String[] en = new String[] {
				"pk", "dis", "r", "xn", "xqmc", "xymc", "bjmc", "rychmc", "bjrs",
				"tjr", "tjsj", "sh", "xxsh" };
		String[] cn = new String[] {
				"pk", "dis", "�к�", "ѧ��", "ѧ��", Base.YXPZXY_KEY, "�༶", "�����ƺ�", "��������",
				"�ύ��", "�ύʱ��", Base.YXPZXY_KEY+"���", "ѧУ���" };
		if (UserTypePd.isXx(userType)) {
			en = new String[] {
					"pk","dis", "r", "xn", "xqmc", "xymc", "bjrs", "xxsh" };
			cn = new String[] {
					"pk","dis", "�к�", "ѧ��", "ѧ��",Base.YXPZXY_KEY, "��������", "ѧУ���"  };
		}
		DAO mydao = DAO.getInstance();
		return mydao.arrayToList(en, cn);
	}
	
	/**
	 * ѧԺ��ѯ��ѧ����˽��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXyjxjshjg(JxjModel model, String userType) throws Exception{
		return UserTypePd.isXy(userType) ? dao.queryXyjxjshjg(model) : dao.queryXxjxjshjg(model);
	}
	
	/**
	 * ѧԺ��˽�ѧ����˽��
	 * @param jg
	 * @param yj
	 * @param pkValue
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public boolean updateXyjxjplshjg(String jg, String yj, String[] pkValue,
			String userType) throws Exception {
		if (pkValue == null) {
			return false;
		} else {
			String shzd = ("xy".equalsIgnoreCase(userType) ? "xysh" : "xxsh");
			String shyj = ("xy".equalsIgnoreCase(userType) ? "xyyj" : "xxyj");
			jg = "tg".equalsIgnoreCase(jg) ? "ͨ��"
					: ("btg".equalsIgnoreCase(jg) ? "��ͨ��" : "δ���");		
			String sql = "update xsrychb a set " + shzd + "='";
			sql += jg;
			sql += "',";
			sql += shyj;
			sql += "='";
			sql += yj;
			
			if (UserTypePd.isXy(userType)) {
				sql += "' where a.fdysh='ͨ��' and exists (select 1 from view_czxx_xsrychb b where xn||xq||bjdm||rychdm in ('";
			} else {
				sql += "' where a.xysh='ͨ��' and exists (select 1 from view_czxx_xsrychb b where xn||xq||xydm in ('";
			}

			for (String s : pkValue) {
				sql += s;
				sql += "','";
			}
			sql = sql.substring(0, sql.length() - 2);
			sql += ") and a.xn=b.xn and a.xq=b.xq and a.xh=b.xh and a.rychdm=b.rychdm)";
			return dao.updateXyjxjplshjg(sql);
		}
	}
	
	/**
	 * ѧԺ��ѧ�𷵻��������
	 * @param jg
	 * @param yj
	 * @param pkValue
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public boolean xyjxjFhcs(String jg, String yj, String[] pkValue,
			String userType, String userName) throws Exception{
		//ѧԺ��ѧУ������˽�ѧ��
		boolean result = updateXyjxjplshjg(jg, yj, pkValue, userType);
		if (result) {
			if (UserTypePd.isXy(userType)) {
				StringBuilder sql = new StringBuilder("delete from pjpy_bmshtjb a where a.bm='bj' and a.tjxm='rych' and exists (select 1 from view_czxx_xsrychb b where xn||xq||bjdm||rychdm in ('");
				StringBuilder addSql = new StringBuilder("insert into pjpy_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,tjr,tjsj) select distinct bjdm,'bj','xn||xq',xn||xq,'rych','2','");
				addSql.append(userName);
				addSql.append("',to_char(sysdate,'yyyy-mm-dd') from view_czxx_xsrychb where xn||xq||bjdm||rychdm in ('");
				for (String s : pkValue) {
					sql.append(s);
					sql.append("','");
					addSql.append(s);
					addSql.append("','");
				}
				sql.delete(sql.length() - 2, sql.length());
				sql.append(") and a.zjz=b.xn||b.xq and a.dm=b.bjdm)");
				
				addSql.delete(addSql.length() - 2, addSql.length());
				addSql.append(")");
				
				TycjDAO mydao = new TycjDAO();
				result = mydao.excuteSqlResult(new String[]{sql.toString(), addSql.toString()});
			} else {
				StringBuilder sql = new StringBuilder("delete from pjpy_bmshtjb a where a.bm='xy' and a.tjxm='rych' and exists (select 1 from view_czxx_xsrychb b where xn||xq||xydm in ('");
				StringBuilder addSql = new StringBuilder("insert into pjpy_bmshtjb(dm,bm,zj,zjz,tjxm,tjzt,tjr,tjsj) select distinct xydm,'xy','xn||xq',xn||xq,'rych','2','");
				addSql.append(userName);
				addSql.append("',to_char(sysdate,'yyyy-mm-dd') from view_czxx_xsrychb where xn||xq||xydm in ('");
				for (String s : pkValue) {
					sql.append(s);
					sql.append("','");
					addSql.append(s);
					addSql.append("','");
				}
				sql.delete(sql.length() - 2, sql.length());
				sql.append(") and a.zjz=b.xn||b.xq and a.dm=b.xydm)");
				
				addSql.delete(addSql.length() - 2, addSql.length());
				addSql.append(")");
				
				TycjDAO mydao = new TycjDAO();
				result = mydao.excuteSqlResult(new String[]{sql.toString(), addSql.toString()});
			}
		}
		return result;
	}
}
