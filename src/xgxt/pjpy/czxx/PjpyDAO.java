package xgxt.pjpy.czxx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

public class PjpyDAO {

	DAO dao = DAO.getInstance();
	
	//查询等级考试名称
	final StringBuffer QUERY_XSDJKSMC = new StringBuffer("select djksmc||'('||")
	           .append("cj||'分)' mc from xsdjksb where xh=? and xn=? and xq=?");
	
	/**
	 * 拼接批处理SQL语句执行
	 * @param cbv  主键数组值
	 * @param tableName  表名
	 * @param pkzd       主键字段 格式:a||b
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
	 * 传入SQL数组,返回执行结果
	 * @param sqlArr
	 * @return
	 * @throws SQLException
	 */
	private int[] executeSql(String[] sqlArr) throws SQLException{		
		return dao.runBatch(sqlArr);
	}
	
	/**
	 * 对批处理执行结果进行检测
	 * @param sqlArr
	 * @return
	 * @throws SQLException
	 */
	public boolean excuteSqlResult(String[] sqlArr) throws SQLException{
		return dao.checkBatch(executeSql(sqlArr));
	}
	
	/**
	 * 奖学金代码列表
	 * @return
	 */
	public List<HashMap<String, String>> queryJxjdmList() {
		return dao.getList("select jxjdm dm,jxjmc mc from jxjdmb order by jxjdm",
				new String[]{}, new String[] { "dm", "mc" });
	}
	
	/**
	 * 荣誉称号代码列表
	 * @return
	 */
	public List<HashMap<String, String>> queryRychdmList() {
		return dao.getList("select rychdm dm,rychmc mc from rychdmb order by rychdm",
				new String[]{}, new String[] { "dm", "mc" });
	}
	
	
	//以下为提供给入党积极分子模块的接口
	/**
	 * 传入学号,学年,学期返回学生的成绩班级排名 
	 *    无成绩返回 ""
	 */
	public String queryStucjbjpmByxh(String xh, String xn, String xq) {
		return dao.getOneRs("select xh,xn,xq,bjdm,cj,pjcj from (select xh,xn,xq,bjdm,cj,dense_rank() over (partition by xn,xq,bjdm order by to_number(cj) desc nulls last) pjcj from (select xh,xn,xq,bjdm,avg(cj) cj from view_cjb group by xh,xn,xq,bjdm)) where xh=? and xn=? and xq=?",
				new String[] {xh,xn,xq}, "pjcj");
		
	}
	
	/**
	 * 传入学号,学年,学期返回学生的综测班级排名
	 *     无成绩返回 ""
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 */
	public String queryStuZcpmByxh(String xh, String xn, String xq) {
		return "";
	}
	
	/**
	 * 传入学号,学年,学期返回学生的不及格科目成绩
	 *     无及格科目返回 "",多条数据则以,分割
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 */
	public String queryStuBjgkmByxh(String xh, String xn, String xq) {
		List<String[]> rs = dao.rsToVator("select kcmc||'('||cj||'分)' mc from view_cjb where cj <60 and xh=? and xn=? and xq=?",
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
	 * 传入学号,学年,学期返回学生的奖学金获奖信息
	 *     无获奖则返回 "",多条数据则以,分割
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 */
	public String queryStuHjxxByxh(String xh, String xn, String xq) {
		List<String[]> rs = dao.rsToVator("select jxjdm,(select jxjmc from jxjdmb b where a.jxjdm=b.jxjdm) jxjmc from xsjxjb a where xh=? and xn=? and xq=? and xxsh='通过' and xysh='通过'",
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
	 * 传入学号,学年,学期返回学生的违纪处分信息
	 *     无获奖则返回 "",多条数据则以,分割
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
	 * 传入学号,学年,学期返回学生的等级考试信息
	 *     无获奖则返回 "",多条数据则以,分割
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
