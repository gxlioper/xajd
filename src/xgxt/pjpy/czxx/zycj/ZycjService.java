package xgxt.pjpy.czxx.zycj;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.pjpy.czxx.PjpyDAO;
import xgxt.pjpy.czxx.dycj.DyjcfDAO;
import xgxt.pjpy.czxx.dycj.DyjcfModel;

public class ZycjService {

	ZycjDAO dao = new ZycjDAO();
	
	/**
	 * ��ѯ�������ӷ���Ϣ
	 * @param model
	 * @param isFdyStr
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZyfjfResult(DyjcfModel model, String isFdyStr)
			throws Exception {
		String fdyQuerySql = "true".equalsIgnoreCase(isFdyStr) ?
				" and exists(select 1 from fdybjb c "
				+ "where a.bjdm=c.bjdm and c.zgh='"
				+ model.getUserName()
				+ "')"
				: "";
				
		StringBuffer sql = new StringBuffer("select a.xh||'!@'||a.xn||'!@'||a.xq")
		    .append(" pk,rownum r,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,a.*,(select nvl(case when instr(to_char")
		    .append("(nvl(sum(nvl(lb,'')||nvl(fs,0)),0)),'.',1,1)=1 then '0'")
		    .append("||to_char(nvl(sum(nvl(lb,'')||nvl(fs,0)),0)) else ")
		    .append("to_char(nvl(sum(nvl(lb,'')||nvl(fs,0)),0)) end, '0')")
		    .append(" from czxx_zyfjfb b where a.xh=b.xh and a.xn=b.xn and ")
		    .append("a.xq=b.xq) zf from (select xh,xm,xb,nj,xydm,zydm,bjdm,")
		    .append("bjmc,'"+model.getXn()+"' xn,'"+model.getXq()+"' xq from ")
		    .append("view_xsjbxx a) a");
		DyjcfDAO myDAO = new DyjcfDAO();
		return myDAO.queryDyfjfResult(model, sql.toString(), fdyQuerySql);
	}
	
	/**
	 * ��ѯ�������ӷֱ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> queryZyfjfTitle() {
		String[] en = { "pk","r", "xn", "xq", "xh", "xm", "bjmc", "zf" };
		String[] cn = { "pk", "�к�", "ѧ��", "ѧ��", "ѧ��", "����", "�༶",
				"���������ܷ�" };
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ����������ӷ���Ϣ
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public boolean saveZyfjfxx(DyjcfModel model) throws SQLException{
		if (model.getPllb() == null || model.getPllb().length ==0) {
			return false;
		} else {
			String[] sqlArr = new String[model.getPllb().length + 1];
			sqlArr[0] = "delete czxx_zyfjfb where xh||'!@'||xn||'!@'||xq='"
					+ model.getPkValue() + "'";
			for (int i=0;i<model.getPllb().length;i++) {
				StringBuffer sql = new StringBuffer("insert into czxx_zyfjfb(");
				sql.append("xh,xn,xq,lb,yy,bz,fs) values ('");
				sql.append(model.getXh());
				sql.append("','");
				sql.append(model.getXn());
				sql.append("','");
				sql.append(model.getXq());
				sql.append("','");
				sql.append(model.getPllb()[i]);
				sql.append("','");
				sql.append(model.getPlyy()[i]);
				sql.append("','");
				sql.append(model.getPlbz()[i]);
				sql.append("','");
				sql.append(model.getPlfs()[i]);
				sql.append("')");
				sqlArr[i+1] = sql.toString();
			}
			PjpyDAO myDao = new PjpyDAO();
			return myDao.excuteSqlResult(sqlArr);
		}
	}
	
	/**
	 * ��ѯ�����ɼ���Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZycjResult(ZycjModel model, String isFdyStr)
			throws Exception {
		DyjcfDAO myDAO  = new DyjcfDAO();
		//��ѯ�������ɼ�����
		HashMap<String, String> rs = myDAO.queryDycjBl();
		String fdyQuerySql = "true".equalsIgnoreCase(isFdyStr) ? 
				" and exists(select 1 from fdybjb c "
				+ "where a.bjdm=c.bjdm and c.zgh='"
				+ model.getUserName()
				+ "')"
				: ""; 
		return dao.queryZycjResult(model, rs, fdyQuerySql);
	}
	
	public List<HashMap<String, String>> queryZycjTitle() {
		String[] en = {"r", "xn", "xq", "xh",
				"xm", "bjmc", "kscj", "kccj", "zyfjf", "cj"};
		String[] cn = {"�к�", "ѧ��", "ѧ��", "ѧ��",
				"����", "�༶", "���Կ�Ŀƽ���ɼ�",
				"�����Ŀƽ���ɼ�", "�������ӷ�", "�����ܳɼ�" };
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
}
