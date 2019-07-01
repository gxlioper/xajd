package xgxt.pjpy.czxx.cssz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.pjpy.czxx.PjpyDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class CsszDAO {
	
	//查询综合测评排名信息
	final StringBuffer QUERY_ZHSZCPPM = new StringBuffer("select a.*,rownum r")
					 .append(" from (select lb||dm pk,lb,")
					 .append("(case when lb='jxj' then '奖学金' else '荣誉称号' ")
	                 .append("end) lbmc,dm,bfb,(case when lb='jxj' then (select")
	                 .append(" jxjmc from jxjdmb b where a.dm=b.jxjdm) else (" )
	                 .append("select rychmc from rychdmb b where a.dm=b.rychdm)")
	                 .append(" end) mc from zhszpmbfbb a) a");
	
	//删除综合测评排名信息
	final StringBuffer DELETE_ZHSZCPPM = new StringBuffer("delete from ")
					 .append("zhszpmbfbb where lb = ? and dm = ?");
	
	//删除开关设置维护信息
	final StringBuffer DELETE_KGWHXX = new StringBuffer("delete from pjpykgszb");
	
	//保存开关设置维护信息
	final StringBuffer SAVE_KGWHXX = new StringBuffer("insert into pjpykgszb")
	                 .append(" (jxjsq,jxjsh,rychsq,rychsh) values (?,?,?,?)");
	                 
	//查询开关设置维护信息
	final StringBuffer QUERY_KGWHXX = new StringBuffer("select jxjsq,jxjsh")
	                 .append(",rychsq,rychsh from pjpykgszb");
	
	//查询荣誉称号申请获奖条件信息
	final StringBuffer QUERY_RYCHSQTJXX = new StringBuffer("select jxjdm dm,")
	                 .append("jxjmc mc,(select (case when count(*)>0 then ")
	                 .append("'checked' else '' end) from czxx_rychtjszb b where")
	                 .append(" a.jxjdm=b.dm) chk from jxjdmb a");
	
	//查询荣誉称号申请德育成绩条件信息
	final StringBuffer QUERY_RYCHSQDYCJXX = new StringBuffer("select cj from ")
	                 .append("czxx_rychtjszb where cj is not null and rownum=1");
	
	//删除荣誉称号申请条件信息
	final StringBuffer DELETE_RYCHSQTJXX = new StringBuffer("delete from")
				     .append(" czxx_rychtjszb");
	
	//保存荣誉称号申请条件德育成绩信息
	final StringBuffer SAVE_RYCHSQTJDYCYXX = new StringBuffer("insert into ")
	                 .append("czxx_rychtjszb(cj) values (?)"); 
	
	DAO dao = DAO.getInstance();
	
	/**
	 * 保存综合测评排名比例
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
	 * 删除综合测评排名信息
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
	 * 删除综合素质测评排名比例数据
	 * @param sqlArr
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteZhszcppmBlf(String[] sqlArr) throws SQLException{
		PjpyDAO myDAO = new PjpyDAO();
		return myDAO.excuteSqlResult(sqlArr);
	}
	
	/**
	 * 查询综合素质测评排名比例信息
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
	 * 传入二个数组返回查询表头
	 * @param en
	 * @param cn
	 * @return
	 */
	public List<HashMap<String, String>> queryTitle(String[] en, String[] cn) {
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 删除评奖评优开关设置信息
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
	 * 保存评奖评优开关设置信息
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
	 * 查询开关设置信息
	 * @return
	 */
	public HashMap<String, String> queryKgwhxx() {
		return dao.getMapNotOut(QUERY_KGWHXX.toString(), new String[]{});
	}
	
	/**
	 * 查询荣誉称号申请获奖条件信息
	 * @return
	 */
	public List<HashMap<String, String>> queryRychsqtjxx() {
		return dao.getList(QUERY_RYCHSQTJXX.toString(), new String[] {},
				new String[] { "dm", "mc", "chk" });
	}
	
	/**
	 * 查询荣誉称号申请获奖条件中的德育成绩信息
	 * @return
	 */
	public String queryRychsqdycjtj() {
		return dao.getOneRs(QUERY_RYCHSQDYCJXX.toString(), new String[]{}, "cj");
	}
	
	/**
	 * 删除荣誉称号申请获奖条件信息
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
