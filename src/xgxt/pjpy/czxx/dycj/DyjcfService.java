package xgxt.pjpy.czxx.dycj;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.pjpy.czxx.PjpyDAO;

public class DyjcfService {

	DyjcfDAO dao = new DyjcfDAO();
	
	/**
	 * 查询德育基础分结果
	 * @param model
	 * @param isFdy  是否辅导员标志,如果是则只查询辅导员所带班级数据
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDyjcfResult(DyjcfModel model, String isFdyStr)
			throws Exception {
		String fdyQuerySql = "true".equalsIgnoreCase(isFdyStr) ? 
									" and exists(select 1 from fdybjb c "
									+ "where a.bjdm=c.bjdm and c.zgh='"
									+ model.getUserName()
									+ "')"
									: ""; 
		StringBuffer sql = new StringBuffer("select a.*,rownum r,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc from (select")
				.append(" b.xh||'!@'||'")
				.append(model.getXn())
				.append("'||'!@'||'")
				.append(model.getXq())
				.append("' pk,b.xh,b.xn,b.xq,cj,b.xm,b.nj,b.xydm,b.zydm,b.bjdm")
				.append(",b.bjmc from (select xm,xh,'"+model.getXn()+"' xn,'" 
						+ model.getXq()
						+ "' xq,xydm,zydm,bjdm,nj,bjmc from view_xsjbxx) b left join ")
				.append("czxx_dyjcfb a on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) a");			
		return dao.queryDyjcfResult(model, sql.toString(), fdyQuerySql); 		
	}
	
	/**
	 * 查询德育基础分表头
	 * @return
	 */
	public List<HashMap<String, String>> queryDyjcfTitle() {
		String[] en = { "pk", "r", "xn", "xq", "xh", "xm", "bjmc", "cj" };
		String[] cn = { "pk", "行号", "学年", "学期", "学号", "姓名", "班级", "德育基础分" };
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 保存德育基础分信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveDyjcfxx(DyjcfModel model) throws Exception {
		if (model.getHiddenCbv() == null || model.getHiddenCbv().length == 0) {
			return false;
		} else {
			String[] delSqlArr = new String[model.getHiddenCbv().length];
			String[] insertSqlArr = new String[model.getHiddenCbv().length];
			for (int i = 0; i < delSqlArr.length; i++) {
				StringBuffer sql = new StringBuffer("delete from czxx_dyjcfb");
				sql.append(" where xh||'!@'||xn||'!@'||xq = '");
				sql.append(model.getHiddenCbv()[i]);
				sql.append("'");
				delSqlArr[i] = sql.toString();
				sql = new StringBuffer("insert into czxx_dyjcfb (xh,xn,xq,cj)");
				sql.append(" values ('");
				sql.append(model.getHiddenCbv()[i] != null ? model.getHiddenCbv()[i]
						.split("!@")[0] : "");
				sql.append("','");
				sql.append(model.getXn());
				sql.append("','");
				sql.append(model.getXq());
				sql.append("','");
				sql.append(model.getCjArr()[i] == null ? "" : model.getCjArr()[i]);
				sql.append("')");
				insertSqlArr[i] = sql.toString();
			}
			DAO myDAO = DAO.getInstance();
			return dao.excuteSqlResult(myDAO
					.unionArray(delSqlArr, insertSqlArr));
		}
	}
	
	/**
	 * 删除德育基础分信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteDyjcfxx(DyjcfModel model) throws Exception {
		PjpyDAO dao = new PjpyDAO();
		String[] sqlArr = dao.appendBatchSql(model.getCbv(), "czxx_dyjcfb",
				"xh||'!@'||xn||'!@'||xq");
		return dao.excuteSqlResult(sqlArr);
	}
	
	/**
	 * 查询德育附加分信息
	 * @param model
	 * @param isFdyStr
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDyfjfResult(DyjcfModel model, String isFdyStr)
			throws Exception {
		String fdyQuerySql = "true".equalsIgnoreCase(isFdyStr) ?
				" and exists(select 1 from fdybjb c "
				+ "where a.bjdm=c.bjdm and c.zgh='"
				+ model.getUserName()
				+ "')"
				: "";
				
		StringBuffer sql = new StringBuffer("select a.xh||'!@'||a.xn||'!@'||a.xq")
		    .append(" pk,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,rownum r,a.*,(select nvl(case when instr(to_char")
		    .append("(nvl(sum(nvl(lb,'')||nvl(fs,0)),0)),'.',1,1)=1 then '0'")
		    .append("||to_char(nvl(sum(nvl(lb,'')||nvl(fs,0)),0)) else ")
		    .append("to_char(nvl(sum(nvl(lb,'')||nvl(fs,0)),0)) end, '0')")
		    .append(" from czxx_dyfjfb b where a.xh=b.xh and a.xn=b.xn and ")
		    .append("a.xq=b.xq) zf from (select xh,xm,xb,nj,xydm,zydm,bjdm,")
		    .append("bjmc,'"+model.getXn()+"' xn,'"+model.getXq()+"' xq from ")
		    .append("view_xsjbxx a) a");
		return dao.queryDyfjfResult(model, sql.toString(), fdyQuerySql);
	}
	
	/**
	 * 查询德育附加分表头
	 * @return
	 */
	public List<HashMap<String, String>> queryDyfjfTitle() {
		String[] en = { "pk","r", "xn", "xq", "xh", "xm", "bjmc", "zf" };
		String[] cn = { "pk", "行号", "学年", "学期", "学号", "姓名", "班级",
				"德育附加总分" };
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 保存德育附加分信息
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public boolean saveDyfjfxx(DyjcfModel model) throws SQLException{
		if (model.getPllb() == null || model.getPllb().length ==0) {
			return false;
		} else {
			String[] sqlArr = new String[model.getPllb().length + 1];
			sqlArr[0] = "delete czxx_dyfjfb where xh||'!@'||xn||'!@'||xq='"
					+ model.getPkValue() + "'";
			for (int i=0;i<model.getPllb().length;i++) {
				StringBuffer sql = new StringBuffer("insert into czxx_dyfjfb(");
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
			return dao.excuteSqlResult(sqlArr);
		}
	}
	
	/**
	 * 查询德育成绩信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDycjResult(DyjcfModel model, String isFdyStr)
			throws Exception {
		HashMap<String, String> rs = dao.queryDycjBl();
		String fdyQuerySql = "true".equalsIgnoreCase(isFdyStr) ? 
				" and exists(select 1 from fdybjb c "
				+ "where a.bjdm=c.bjdm and c.zgh='"
				+ model.getUserName()
				+ "')"
				: ""; 
		return dao.queryDycjResult(model, rs, fdyQuerySql);
	}
	
	/**
	 * 查询德育成绩表头
	 * @return
	 */
	public List<HashMap<String, String>> queryDycjTitle() {
		String[] en = { "r", "xn", "xq",
				"xh", "xm", "bjmc", "cj", "gff", "fjf", "dyzf"};
		String[] cn = { "行号", "学年", "学期", "学号", "姓名", "班级", "德育基础分",
				"宿舍生活规范分","德育附加分", "德育成绩" };
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
}
