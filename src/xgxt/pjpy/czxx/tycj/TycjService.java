
package xgxt.pjpy.czxx.tycj;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.pjpy.czxx.dycj.DyjcfDAO;
import xgxt.pjpy.czxx.dycj.DyjcfModel;
import xgxt.pjpy.zjjj.model.DycjModel;
import xgxt.utils.String.StringUtils;

public class TycjService {
	
	TycjDAO dao = new TycjDAO();
	
	/**
	 * 查询体育课成绩信息
	 * @param model
	 * @param isFdyStr
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryTykcjResult(TycjModel model, String isFdyStr)
			throws Exception {
		String fdyQuerySql = "true".equalsIgnoreCase(isFdyStr) ? 
				" and exists(select 1 from fdybjb c "
				+ "where a.bjdm=c.bjdm and c.zgh='"
				+ model.getUserName()
				+ "')"
				: ""; 
		return dao.queryTykcjResult(model, fdyQuerySql);
	}
	
	/**
	 * 查询体育课成绩信息
	 * @return
	 */
	public List<HashMap<String, String>> queryTykcjTitle() {
		String[] en = {"r","xn", "xq", "xh", "xm", "bjmc", "kcmc", "kcxz", "cj"};
		String[] cn = { "行号", "学年", "学期", "学号", "姓名", "班级", "课程名称", 
				"课程性质","成绩" };
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 查询体育附加分表头
	 * @return
	 */
	public List<HashMap<String, String>> queryTyfjfTitle() {
		String[] en = { "pk","r", "xn", "xq", "xh", "xm", "bjmc", "zf" };
		String[] cn = { "pk", "行号", "学年", "学期", "学号", "姓名", "班级",
				"体育附加总分" };
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 查询体育附加分表头
	 * @return
	 */
	public List<HashMap<String, String>> queryTydlfTitle() {
		String[] en = { "pk","r", "xn", "xq", "xh", "xm", "bjmc", "fs" };
		String[] cn = { "pk", "行号", "学年", "学期", "学号", "姓名", "班级",
				"课外体育锻炼分" };
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 查询体育附加分表头
	 * @return
	 */
	public List<HashMap<String, String>> queryTycjTitle() {
		String[] en = { "pk","r", "xn", "xq", "xh", "xm", "bjmc", "","kwf", "fjf", "zf" };
		String[] cn = { "pk", "行号", "学年", "学期", "学号", "姓名", "班级","体育课成绩",
				"课外体育锻炼分","体育附加分","体育总分" };
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 查询体育附加分表头
	 * @return
	 */
	public List<HashMap<String, String>> queryZhcjTitle() {
		String[] en = { "pk","r", "xn", "xq", "xh", "xm", "bjmc", "dcj","zcj", "tcj", "zxf","zfpm" };
		String[] cn = { "pk", "行号", "学年", "学期", "学号", "姓名", "班级","德育成绩",
				"智育成绩","体育成绩","综合测评总成绩","综合测评总成绩班级排名" };
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 查询德育附加分信息
	 * @param model
	 * @param isFdyStr
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryTyfjfResult(DyjcfModel model, String isFdyStr)
			throws Exception {
		String fdyQuerySql = "true".equalsIgnoreCase(isFdyStr) ?
				" and exists(select 1 from fdybjb c "
				+ "where a.bjdm=c.bjdm and c.zgh='"
				+ model.getUserName()
				+ "')"
				: "";
				
		StringBuffer sql = new StringBuffer("select a.xh||'!@'||a.xn||'!@'||a.xq")
		    .append(" pk,rownum r,a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,(select nvl(case when instr(to_char")
		    .append("(nvl(sum(nvl(lb,'')||nvl(fs,0)),0)),'.',1,1)=1 then '0'")
		    .append("||to_char(nvl(sum(nvl(lb,'')||nvl(fs,0)),0)) else ")
		    .append("to_char(nvl(sum(nvl(lb,'')||nvl(fs,0)),0)) end, '0')")
		    .append(" from czxx_tyfjfb b where a.xh=b.xh and a.xn=b.xn and ")
		    .append("a.xq=b.xq) zf from (select xh,xm,xb,nj,xydm,zydm,bjdm,")
		    .append("bjmc,'"+model.getXn()+"' xn,'"+model.getXq()+"' xq from ")
		    .append("view_xsjbxx a) a");
		return dao.queryTyfjfResult(model, sql.toString(), fdyQuerySql);
	}
	
	/**
	 * 保存体育附加分信息
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public boolean saveTyfjfxx(DyjcfModel model) throws SQLException{
		if (model.getPllb() == null || model.getPllb().length ==0) {
			return false;
		} else {
			String[] sqlArr = new String[model.getPllb().length + 1];
			sqlArr[0] = "delete czxx_tyfjfb where xh||'!@'||xn||'!@'||xq='"
					+ model.getPkValue() + "'";
			for (int i=0;i<model.getPllb().length;i++) {
				StringBuffer sql = new StringBuffer("insert into czxx_tyfjfb(");
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
	 * 查询德育附加分信息
	 * @param model
	 * @param isFdyStr
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryTydlffResult(DyjcfModel model, String isFdyStr)
			throws Exception {
		String fdyQuerySql = "true".equalsIgnoreCase(isFdyStr) ?
				" and exists(select 1 from fdybjb c "
				+ "where a.bjdm=c.bjdm and c.zgh='"
				+ model.getUserName()
				+ "')"
				: "";
				
		StringBuffer sql = new StringBuffer("select a.xh||'!@'||a.xn||'!@'||a.xq")
		    .append(" pk,rownum r,a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,(select fs")
		    .append(" from czxx_tydlcjb b where a.xh=b.xh and a.xn=b.xn and ")
		    .append("a.xq=b.xq) zf from (select xh,xm,xb,nj,xydm,zydm,bjdm,")
		    .append("bjmc,'"+model.getXn()+"' xn,'"+model.getXq()+"' xq from ")
		    .append("view_xsjbxx a) a");
		return dao.queryTyfjfResult(model, sql.toString(), fdyQuerySql);
	}
	
	/**
	 * 保存课外锻炼成绩信息
	 * @param pkValues
	 * @param fs
	 * @return
	 */
	public boolean saveTydlfResult(String[] pkValues, String[] fs) {
		if (pkValues != null && pkValues.length > 0
				&& pkValues.length == fs.length) {
			String[] sqlArr = new String[pkValues.length * 2];
			/* 先删除数据 */
			for (int i = 0; i < pkValues.length; i++) {
				StringBuilder sql = new StringBuilder(
						"delete from czxx_tydlcjb where xh||'!@'||xn||'!@'||xq = '");
				sql.append(pkValues[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
			/* 再插入数据 */
			for (int i = 0; i < pkValues.length; i++) {
				String[] arr = pkValues[i] != null ? pkValues[i].split("!@")
						: new String[] {};
				if (arr != null && arr.length >= 3) {
					StringBuilder sql = new StringBuilder(
							"insert into czxx_tydlcjb(xh,xn,xq,fs) values ('");
					sql.append(arr[0]);
					sql.append("','");
					sql.append(arr[1]);
					sql.append("','");
					sql.append(arr[2]);
					sql.append("','");
					sql.append(StringUtils.isNotNull(fs[i]) ? fs[i] : "");
					sql.append("')");
					sqlArr[pkValues.length + i] = sql.toString();
				}
			}
			
			try {
				return dao.excuteSqlResult(sqlArr);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 删除课外锻炼信息
	 * @param pkValue
	 * @return
	 */
	public boolean deleteTydlfxx(String[] pkValue) {
		if (pkValue != null && pkValue.length > 0) {
			String[] sqlArr = new String[pkValue.length];
			for (int i=0;i<pkValue.length;i++) {
				StringBuilder sql = new StringBuilder("delete from czxx_tydlcjb where xh||'!@'||xn||'!@'||xq = '");
				sql.append(pkValue[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
			try {
				return dao.excuteSqlResult(sqlArr);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 查询体育课成绩信息
	 * @param model
	 * @param isFdyStr
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryTycjResult(TycjModel model, String isFdyStr)
			throws Exception {
		DyjcfDAO myDAO  = new DyjcfDAO();
		String fdyQuerySql = "true".equalsIgnoreCase(isFdyStr) ? 
				" and exists(select 1 from fdybjb c "
				+ "where a.bjdm=c.bjdm and c.zgh='"
				+ model.getUserName()
				+ "')"
				: ""; 
		
		HashMap<String, String> rs = myDAO.queryDycjBl();		
		return dao.queryTycjResult(model, fdyQuerySql, rs);
	}
	
	/**
	 * 查询综合测评总成绩信息
	 * @param model
	 * @param isFdyStr
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhcjResult(TycjModel model, String isFdyStr)
			throws Exception {
		String fdyQuerySql = "true".equalsIgnoreCase(isFdyStr) ? 
				" and exists(select 1 from fdybjb c "
				+ "where a.bjdm=c.bjdm and c.zgh='"
				+ model.getUserName()
				+ "')"
				: ""; 
			
		return dao.queryZhcpResult(model, fdyQuerySql);
	}
	
	/**
	 * 自动计算总分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean countZhszcpzf(TycjModel model) throws Exception{
		return dao.countZhszcpzf(model);
	}
}
