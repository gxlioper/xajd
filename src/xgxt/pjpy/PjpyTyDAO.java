package xgxt.pjpy;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.zjcm.ZjcmPjpyModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class PjpyTyDAO extends CommonQueryDAO {

	// ---------------以下 made by luojw-------------
	
	/**
	 * 获得评奖评优相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getPjpyListInfo(String tableName, Object model,
			String[] colList,String other_query) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq", "lx", "pycc", "jxjdm", "rychdm", "xxsh", "xqdm",
				"lddm", "cs", "qsh" };
		
		String[] queryLikeList = new String[] { "xh", "xm" };
		
		MakeQuery myQuery = new MakeQuery();
		
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String query = myQuery.getQueryString();
		
		other_query = Base.isNull(other_query) ? "" : other_query;
		
		if(!Base.isNull(query)){
			query += " "+other_query;
		}else{
			query = other_query;
		}

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}
	
	/**
	 * 获得评奖评优相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getPjpyListInfo(PjpyTyForm model,
			String[] colList,String other_query) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		
		StringBuilder sql=new StringBuilder();
		
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq", "lx", "pycc", "jxjdm", "rychdm", "xxsh", "xqdm",
				"lddm", "cs", "qsh" };
		
		String[] queryLikeList = new String[] { "xh", "xm" };
		
		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String ksrq=model.getKsrq();
		
		String jsrq=model.getJsrq();
		
		String query = myQuery.getQueryString();
		
		sql.append(" select rownum r,a.* from(select a.xh || b.xn || b.xq pk,a.xh,a.xb,a.xm,a.nj,a.xydm,");
		sql.append(" a.xymc, a.zydm,a.zymc,a.bjdm,a.bjmc, a.sfzh,a.xqdm,");
		sql.append(" a.xqmc,a.lddm,a.ldmc,a.cs,a.qsh,a.ssbh,b.xn, b.xq,");
		sql.append(" (select c.xqmc from xqdzb c where b.xq = c.xqdm) xqm,");
		sql.append(" b.mrz,nvl(case  when instr(to_char(b.jiaf), '.', 1, 1) = 1 then '0' || to_char(b.jiaf)");
		sql.append(" else to_char(b.jiaf)  end,'0') jiaf,");
		sql.append(" nvl(case when instr(to_char(b.jianf), '.', 1, 1) = 1 then '0' || to_char(b.jianf)");
		sql.append(" else  to_char(b.jianf) end, '0') jianf,");
		sql.append(" nvl(case  when instr(to_char(b.mrz + b.jiaf - b.jianf), '.', 1, 1) = 1 then");
		sql.append(" '0' || to_char(b.mrz + b.jiaf - b.jianf)");
		sql.append(" else  to_char(b.mrz + b.jiaf - b.jianf) end, '0') cxf");
		sql.append(" from view_zjjt_cxfsbxs a,");
		sql.append(" (select a.xh,a.xn,a.xq,nvl((select b.mrz from zjjt_cxf_sz b");
		sql.append(" where a.xn = b.xn  and a.xq = b.xq), 0) mrz,");
		sql.append(" sum(jiaf) jiaf,sum(jianf) jianf");
		sql.append(" from (select xh, xn,xq,");
		sql.append(" case when jjf = '加分' then  fz else '0' end jiaf,");
		sql.append(" case when jjf = '减分' then  fz  else '0' end jianf");
		sql.append(" from (select b.pjxh xh, b.xn, b.xq, b.jjf, b.fz");
		sql.append(" from zjjt_cxflrb b where 1=1  ");
		
		if(!Base.isNull(ksrq)){
			sql.append(" and rq >= '"+ksrq+"' ");
		}
		
		if(!Base.isNull(jsrq)){
			sql.append(" and rq <= '"+jsrq+"' ");
		}
		
		sql.append(" )) a  group by a.xh, a.xn, a.xq) b ");
		sql.append(" where a.xh = b.xh)a ");
		
		other_query = Base.isNull(other_query) ? "" : other_query;
		
		if(!Base.isNull(query)){
			query += " "+other_query;
		}else{
			query = other_query;
		}
		
		return CommonQueryDAO.commonQuery(sql.toString(), query, myQuery.getInputList(), colList, model);

	}
	
	/**
	 * 获得评奖评优相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getPjpyInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getPjpyList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();

		return dao.getWhList(tableName, dm, mc, msg, pk, pkValue);
	}
	
	/**
	 * 获得无需维护下拉框值
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("sflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "是", "否" };
			mc = new String[] { "是", "否" };
		} else if ("bblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "zhbnoyj", "zhbyj", "zhblxy", "jxjjetj",
					"jxjhj", "rychhj", "jxjjehz" };
			mc = new String[] { "综合测评表(不考虑英语、计算机等级)", "综合测评表(考虑英语、计算机等级)",
					"学院综合测评比例表", "奖学金金额统计", "奖学金获奖名单", "荣誉称号获奖名单", "奖学金金额汇总表" };
		} else if ("wlk".equalsIgnoreCase(lx)) {
			dm = new String[] { "文科", "理科", "艺术类" };
			mc = new String[] { "文科", "理科", "艺术类" };
		} else if ("tjlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "", ">", ">=", "=", "<=", "<" };
			mc = new String[] { "-----请选择-----", ">", ">=", "=", "<=", "<" };
		} else if ("ywlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "", "有", "无" };
			mc = new String[] { "-----请选择-----", "有", "无" };
		} else if ("kglx".equalsIgnoreCase(lx)) {
			dm = new String[] { "开", "关" };
			mc = new String[] { "开", "关" };
		} else if ("jxjorrych".equalsIgnoreCase(lx)) {
			dm = new String[] { "jxj", "rych" };
			mc = new String[] { "奖学金", "荣誉称号" };
		}else if ("over".equalsIgnoreCase(lx)) {
			dm = new String[] { "未做", "已做" };
			mc = new String[] { "未做", "已做" };
		}else if ("zz".equalsIgnoreCase(lx)) {
			dm = new String[] { "已组卷", "未组卷" };
			mc = new String[] { "已组卷", "未组卷" };
		} else if ("xblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "男", "女" };
			mc = new String[] { "男", "女" };
		} else if ("fplx".equalsIgnoreCase(lx)) {
			dm = new String[] { "未分配", "已分配" };
			mc = new String[] { "未分配", "已分配" };
		}
		
		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * 获得指定表的指定字段
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk, String pkValue) {
		
		DAO dao = DAO.getInstance();

		return dao.getOneValue(tableName, dm, pk, pkValue);
	}

	/**
	 * 获得班级人数
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getBjRs(String bjdm) {
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { "bjdm", "bjmc", "num" };
		String sql = "select bjdm,bjmc,count(xh) num from view_xsjbxx where bjdm = ? group by bjdm,bjmc";
		HashMap<String, String> map = dao.getMap(sql, new String[] { bjdm },
				colList);
		return map;
	}
	
	/**
	 * 批量提交
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql)
			throws Exception {

		DAO dao = new DAO();
		boolean flag = true;
		int[] res = dao.runBatch(sql);

		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		return flag;
	}
	
	/**
	 * 获得参评组相关信息(传媒学院)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getCpzInfoList(String xn,String xq,String xydm) {
		
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { "zwdm", "zwmc", "zhfkg", "jxjkg" };
		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.zwdm,a.zhfkg, a.jxjkg,");
		sql.append("(select b.bjgbmc from sxjy_bjgbzlb b where b.bjgbdm = a.zwdm) zwmc ");
		sql.append("from zjcm_cpz a ");
		sql.append("where a.xn = ? and a.xq = ? and a.cpxy = ?");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, xydm }, colList);
		return list;
	}
	
	/**
	 * 获得奖学金列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getJxjList(String query) {

		DAO dao = DAO.getInstance();

		String[] colList = new String[] { "jxjdm", "jxjmc" };

		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select a.jxjdm, a.jxjmc, ");
		sql.append("a.jxjlb, b.jxjlbmc  from jxjdmb a ");
		sql.append("left join jxjlbdmb b on a.jxjlb = b.jxjlbdm) ");
		sql.append("where jxjlbmc like '%'||?||'%' ");
		sql.append("order by to_number(jxjdm)");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { query }, colList);
		
		return list;
	}
	
	/**
	 * 获得指定学院（专业，班级）学生学号
	 * 
	 * @author luojw
	 * @throws SQLException 
	 */
	public String[] getZdXh(String lx, String dm) throws SQLException {
		
		DAO dao = DAO.getInstance();
		
		String[] xh = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select xh from view_xsjbxx ");
		sql.append("xy".equalsIgnoreCase(lx) ? "where xydm = ?" : "");
		sql.append("zy".equalsIgnoreCase(lx) ? "where zydm = ?" : "");
		sql.append("bj".equalsIgnoreCase(lx) ? "where bjdm = ?" : "");
		sql.append("xh".equalsIgnoreCase(lx) ? "where a.bjdm = (select b.bjdm from view_xsjbxx b where b.xh = ?)" : "");
		
		List<String> xhList = dao.getList(sql.toString(), new String[]{dm}, "xh");
		
		if(xhList != null && xhList.size()>0){
			xh = new String[xhList.size()];
			for (int i = 0; i < xhList.size(); i++) {
				xh[i] = xhList.get(i);
			}
		}
		
		return xh;
	}
	
	/**
	 * 获得评奖评优条件列表
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getTjList() {

		DAO dao = DAO.getInstance();

		String sql = "select zdmc dm, zdsm mc from jxjtjzdb order by lsh";

		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	/**
	 * 根据年级获得相关学院
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getXyList(String nj) {

		DAO dao = DAO.getInstance();

		String sql = "select distinct xydm dm, xymc mc, nj from view_njxyzybj where nj = ? order by xydm, nj";

		return dao.getList(sql, new String[] {nj}, new String[] { "dm", "mc" });
	}
	
	/**
	 * 根据年级获得相关学院
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public String sswrValue(String value) {

		String num = "";
		
		if (!Base.isNull(value)) {

			DAO dao = DAO.getInstance();

			if (!Base.isNull(value)) {
				StringBuilder sql = new StringBuilder();
				sql.append("select nvl(case when instr(to_char(round('" + value
						+ "',2)),'.',1,1)=1 then ");
				sql.append("'0'||to_char(round('" + value + "',2)) ");
				sql.append(" else to_char(round('" + value
						+ "',2)) end, '0')  num from dual ");

				num = dao.getOneRs(sql.toString(), new String[] {}, "num");
			}

			num = setNumZero(num);
		}

		return num;
	}
	
	/**
	 * @param num
	 * @return
	 */
	public String setNumZero(String num) {
		int pointNum = 0;

		for (int i = 0; i < num.length(); i++) {

			if (".".equalsIgnoreCase(String.valueOf(num.charAt(i)))) {
				pointNum = i;
			}

		}

		if (pointNum == 1 && num.length() == 3) {
			num += "0";
		} else if (pointNum == 2 && num.length() == 4) {
			num += "0";
		} else if (pointNum == 0) {
			num += ".00";
		}
		return num;
	}
	// ---------------以上 made by luojw-------------
	
	/**
	 * 查询各学院学期奖学金获奖人数
	 * @author lr
	 * @param userType
	 * @param ZjcmPjpyModel model
	 * @return List<String[]>
	 */
	public List<String[]> queryXyList(ZjcmPjpyModel model) {
		DAO dao = DAO.getInstance();
		String sql = "";
		
		sql = StringUtils.joinStr( "select xydm,xymc,jxjdm,rs from (",
				"select xydm,xymc,jxjdm," ,
				"(select count(*) from view_pjpy_zjcm_xsjxjb b where a.jxjdm=b.jxjdm and a.xydm=b.xydm and b.xn=? and b.xq=? and b.xxsh='通过') rs " ,
				"from (" ,
					"select distinct a.xydm,a.xymc,b.jxjdm  from view_njxyzybj a," ,
					"(select jxjdm,jxjmc from jxjdmb a,jxjlbdmb b where a.jxjlb=b.jxjlbdm ) b" ,
				") a" ,
				") where rs is not null and rs <>'0'");
		if (!Base.isNull(model.getXydm())) {
			sql = StringUtils.joinStr(sql," and xydm='",model.getXydm(),"'");
		} 
		return dao.rsToVator(sql, new String[] { model.getXn(),model.getXq() }, 
					new String[] { "xydm", "xymc","jxjdm", "rs" });
	}
	
	/**
	 * 查询各奖学金获奖人数
	 * @author lr
	 * @param userType
	 * @param ZjcmPjpyModel model
	 * @return List<String[]>
	 */
	public List<String[]> queryJxjmcjrs(ZjcmPjpyModel model) {
		DAO dao = DAO.getInstance();
		String sql =StringUtils.joinStr("select jxjdm,jxjmc,rs from (" ,
								"select jxjdm,jxjmc," ,
								"(select count(*) from view_pjpy_zjcm_xsjxjb c where a.jxjdm=c.jxjdm and c.xn=? and c.xq=? and c.xxsh='通过'");
		//选择了学院 
		sql += !Base.isNull(model.getXydm()) ? StringUtils.joinStr(" and c.xydm='" , model.getXydm() , "'") : "";
		
		sql += StringUtils.joinStr(") rs from jxjdmb a,jxjlbdmb b where a.jxjlb=b.jxjlbdm) where rs is not null and rs <>'0' ");
		return dao.rsToVator(sql, new String[] { model.getXn(),model.getXq() }, 
				new String[] { "jxjdm", "jxjmc", "rs" });
	}
	
	/**
	 * 查询各奖学金获奖姓名
	 * @author lr
	 * @param userType
	 * @param ZjcmPjpyModel model
	 * @return List<String[]>
	 */
	public List<String[]> queryJxjhjxmxx(ZjcmPjpyModel model) {
		DAO dao = DAO.getInstance();
		String sql = StringUtils.joinStr("select jxjdm,xydm,xm from view_pjpy_zjcm_xsjxjb where xn=? and xq=? and xxsh='通过' ",
							!Base.isNull(model.getXydm()) ? " and xydm='" + model.getXydm() + "'" : ""," order by jxjdm,xydm,xm");
		
		return dao.rsToVator(sql.toString(), new String[] { model.getXn(), model.getXq() }, 
				new String[] {"jxjdm", "xydm", "xm"});
	}
	
	/**
	 * 查询各学院学期荣誉称号获奖人数
	 * @author lr
	 * @param userType
	 * @param ZjcmPjpyModel model
	 * @return List<String[]>
	 */
	public List<String[]> queryRychXyList(ZjcmPjpyModel model) {
		DAO dao = DAO.getInstance();
		String sql = "";
		
		sql = StringUtils.joinStr( "select xydm,xymc,rychdm,rs from (",
				"select xydm,xymc,rychdm," ,
				"(select count(*) from view_zjcm_rychsq b where a.rychdm=b.rychdm and a.xydm=b.xydm and b.xn=? and b.xq=? and b.xxsh='通过') rs " ,
				"from (" ,
					"select distinct a.xydm,a.xymc,b.rychdm  from view_njxyzybj a," ,
					"(select a.rychdm,a.rychmc from rychdmb a,zjcm_rychsqb b where a.rychdm=b.rychdm and b.rychdm=?) b" ,
				") a" ,
				") where rs is not null and rs <>'0'");
		if (!Base.isNull(model.getXydm())) {
			sql = StringUtils.joinStr(sql," and xydm='",model.getXydm(),"'");
		} 
		return dao.rsToVator(sql, new String[] { model.getXn(),model.getXq(), model.getRychdm() }, 
					new String[] { "xydm", "xymc","rychdm", "rs" });
	}
	
	/**
	 * 查询荣誉称号获奖姓名
	 * @author lr
	 * @param userType
	 * @param ZjcmPjpyModel model
	 * @return List<String[]>
	 */
	public List<String[]> queryRychhjxmxx(ZjcmPjpyModel model) {
		DAO dao = DAO.getInstance();
		String sql = StringUtils.joinStr("select rychdm,xydm,xm from view_zjcm_rychsq where xn=? and xq=? and rychdm=? and xxsh='通过' ",
							!Base.isNull(model.getXydm()) ? " and xydm='" + model.getXydm() + "'" : ""," order by rychdm,xydm,xm");
		
		return dao.rsToVator(sql.toString(), new String[] { model.getXn(), model.getXq(), model.getRychdm()}, 
				new String[] {"rychdm", "xydm", "xm"});
	}
	
	/**
	 * 查询银行类别列表
	 * @return
	 */
	public List<HashMap<String, String>> queryYhlxList() {
		DAO dao = DAO.getInstance();
		return dao.getList("select yhdm dm,yhmc mc from dmk_yh order by yhdm", new String[]{}, new String[]{"dm", "mc"});
	}
	
}
