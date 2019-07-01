package xgxt.rcsw.kqgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class KqglDAO extends DAO {

	/**
	 * 考勤类型
	 * @return
	 */
	protected List<HashMap<String,String>> getKqlxList(){
		String sql = "select * from xg_rcsw_kqlxdmb";
		
		return super.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 按学生统计考勤次数
	 * @param model
	 * @return
	 */
	public List<String[]> getKqtjByStu(KqglForm model){
		Pages pages = model.getPages();//分页对象
		HashMap<String, Object> map = getTjSQL(model);//公用SQL和输入值
		//最大记录数
		StringBuilder maxRecordSql = new StringBuilder();
		maxRecordSql.append("select count(*) count from (select xh,count(*) count from (");
		maxRecordSql.append(map.get("sql"));
		maxRecordSql.append(") group by xh)");
		String sqlConet = getOneRs(maxRecordSql.toString(),(String[])map.get("input"),"count");
		pages.setMaxRecord(Integer.valueOf(sqlConet));
		//自己维护的考勤类型列表
		List<HashMap<String,String>> kqlxList = getKqlxList();
		String[] output = new String[kqlxList.size()];
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.*,rownum r,");
		sql.append("b.xm,b.nj,b.xymc,b.zymc,b.bjmc from (select xh,");
		//自己维护的考勤类型
		setKqlxResult(kqlxList, output,sql);
		sql.append("sum(kkxs) kkxs,sum(kkcs) kkcs,sum(cdcs) cdcs,sum(kqcs) kqcs ");
		sql.append("from  (select xh,");
		
		//自己维护的考勤类型
		setKqlxSql(kqlxList, sql);
		sql.append(map.get("sql"));
		sql.append(") group by xh,kqlx");
		sql.append(") group by xh");
		sql.append(") a left join view_xsjbxx b on a.xh=b.xh");
		sql.append(" ) where r > "); 
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));
		
		return rsToVator(sql.toString(), (String[])map.get("input"), 
					StringUtils.joinStrArr(new String[] {"xh","xm","nj","xymc","bjmc","cdcs","kkxs","kkcs","kqcs"},output));
	}


	/**
	 * 公用统计SQL
	 * @param kqlxList
	 * @param output
	 * @param topTr
	 * @param sql
	 */
	private void setKqlxResult(List<HashMap<String, String>> kqlxList,
			String[] output,StringBuilder sql) {
		for (int i = 0 ; i < kqlxList.size() ; i++){
			
			sql.append("sum(k");
			sql.append(kqlxList.get(i).get("dm"));
			sql.append(") k");
			sql.append(kqlxList.get(i).get("dm"));
			sql.append(",");
			
			output[i] = "k"+kqlxList.get(i).get("dm");
		}
	}

	/**
	 * 自已维护的考勤类型名称
	 * @param kqlxList
	 * @return
	 */
	protected String[] getKqlxTopTr(List<HashMap<String, String>> kqlxList) {
		
		String[] topTr = new String[kqlxList.size()];
		
		for (int i = 0 ; i < kqlxList.size() ; i++){
			topTr[i] = kqlxList.get(i).get("mc")+"次数";
		}
		
		return topTr;
	}
	

	/**
	 * 公用统计SQL
	 * @param kqlxList
	 * @param sql
	 */
	private void setKqlxSql(List<HashMap<String, String>> kqlxList,
			StringBuilder sql) {
		for (int i = 0 ; i < kqlxList.size() ; i++){
			sql.append("case when kqlx='");
			sql.append(kqlxList.get(i).get("dm"));
			sql.append("' then count(*) else 0 end k");
			sql.append(kqlxList.get(i).get("dm"));
			sql.append(",");
		}
		sql.append("case when kqlx='旷课' then sum(kkxs) else 0 end kkxs,");
		sql.append("case when kqlx='旷课' then count(*) else 0 end kkcs,");
		sql.append("case when kqlx='迟到' then count(*) else 0 end cdcs,");
		sql.append("case when kqlx='旷寝' then count(*) else 0 end kqcs ");
		sql.append("from (");
	}
	
	
	/**
	 * 考勤统计-按学院
	 * @param model
	 * @return
	 */
	public List<String[]> getKqtjByXy(KqglForm model) {
		HashMap<String, Object> map = getTjSQL(model);

		//自己维护的考勤类型列表
		List<HashMap<String,String>> kqlxList =  getKqlxList();
		String[] output = new String[kqlxList.size()];
		
		StringBuilder sql = new StringBuilder();
		sql.append("select xymc,");
		//自己维护的考勤类型
		setKqlxResult(kqlxList, output,sql);
		sql.append("sum(kkxs) kkxs,sum(kkcs) kkcs,sum(cdcs) cdcs,sum(kqcs) kqcs ");
		sql.append("from (select xymc,");
		//自己维护的考勤类型
		setKqlxSql(kqlxList, sql);
		sql.append(map.get("sql"));
		sql.append(") group by xymc,kqlx");
		sql.append(") group by xymc");
		
		return rsToVator(sql.toString(), (String[])map.get("input"), 
				StringUtils.joinStrArr(new String[] {"xymc","cdcs","kkxs","kkcs","kqcs"},output));
	}
	
	/**
	 * 考勤统计-按专业
	 * @param model
	 * @return
	 */
	public List<String[]> getKqtjByZy(KqglForm model) {
		Pages pages = model.getPages();
		HashMap<String, Object> map = getTjSQL(model);

		//自己维护的考勤类型列表
		List<HashMap<String,String>> kqlxList =  getKqlxList();
		String[] output = new String[kqlxList.size()];
		
		StringBuilder maxRecordSql = new StringBuilder();
		maxRecordSql.append("select count(*) count from (select zymc,count(*) count from (");
		maxRecordSql.append(map.get("sql"));
		maxRecordSql.append(") group by zymc)");
		String sqlConet = getOneRs(maxRecordSql.toString(),(String[])map.get("input"),"count");
		pages.setMaxRecord(Integer.valueOf(sqlConet));
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.*,rownum r from (");
		sql.append("select xymc,zymc, ");
		//自己维护的考勤类型
		setKqlxResult(kqlxList, output, sql);
		sql.append("sum(kkxs) kkxs,sum(kkcs) kkcs,sum(cdcs) cdcs,sum(kqcs) kqcs ");
		sql.append(" from (select xymc,zymc,");
		//自己维护的考勤类型
		setKqlxSql(kqlxList, sql);
		sql.append(map.get("sql"));
		sql.append(") group by xymc,zymc,kqlx");
		sql.append(") group by xymc,zymc) a");
		sql.append(" ) where r > "); 
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));
		
		return rsToVator(sql.toString(), (String[])map.get("input"), 
				StringUtils.joinStrArr(new String[] {"xymc","zymc","cdcs","kkxs","kkcs","kqcs"},output));
	}
	
	
	/**
	 * 考勤统计-按班级
	 * @param model
	 * @return
	 */
	public List<String[]> getKqtjByBj(KqglForm model) {
		Pages pages = model.getPages();
		HashMap<String, Object> map = getTjSQL(model);
		StringBuilder maxRecordSql = new StringBuilder();
		maxRecordSql.append("select count(*) count from (select bjmc,count(*) count from (");
		maxRecordSql.append(map.get("sql"));
		maxRecordSql.append(") group by bjmc)");
		String sqlConet = getOneRs(maxRecordSql.toString(),(String[])map.get("input"),"count");
		pages.setMaxRecord(Integer.valueOf(sqlConet));
		
		//自己维护的考勤类型列表
		List<HashMap<String,String>> kqlxList =  getKqlxList();
		String[] output = new String[kqlxList.size()];
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.*,rownum r from (");
		sql.append("select xymc,zymc,bjmc,");
		//自己维护的考勤类型
		setKqlxResult(kqlxList, output, sql);
		sql.append("sum(kkxs) kkxs,sum(kkcs) kkcs,sum(cdcs) cdcs,sum(kqcs) kqcs from ");
		sql.append("(select xymc,zymc,bjmc,");
		//自己维护的考勤类型
		setKqlxSql(kqlxList, sql);
		sql.append(map.get("sql"));
		sql.append(") group by xymc,zymc,bjmc,kqlx");
		sql.append(") group by xymc,zymc,bjmc) a");
		sql.append(" ) where r > "); 
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));

		return rsToVator(sql.toString(), (String[])map.get("input"), 
				StringUtils.joinStrArr(new String[] {"xymc","zymc","bjmc","cdcs","kkxs","kkcs","kqcs"},output));
	}
	
	/**
	 * 学生考勤统计公用SQL
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, Object> getTjSQL(KqglForm model) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		
		sql.append("select a.* from xg_view_rcsw_kqgl a where 1=1 ");
		
		if (Boolean.valueOf(model.getIsFdy())){
			sql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh=?)");
			input.add(model.getUserName());
		}
		
		if (StringUtils.isNotNull(model.getQuerylike_xh())) {
			sql.append(" and xh like '%'||?||'%' ");
			input.add(model.getQuerylike_xh());
		}
		
		if (StringUtils.isNotNull(model.getQuerylike_xm())) {
			sql.append(" and xm like '%'||?||'%'  ");
			input.add(model.getQuerylike_xm());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_nj())) {
			sql.append(" and nj=? ");
			input.add(model.getQueryequals_nj());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_xydm())) {
			sql.append(" and xydm=? ");
			input.add(model.getQueryequals_xydm());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_zydm())) {
			sql.append(" and zydm=? ");
			input.add(model.getQueryequals_zydm());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_bjdm())) {
			sql.append(" and bjdm=? ");
			input.add(model.getQueryequals_bjdm());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_xn())) {
			sql.append(" and xn=? ");
			input.add(model.getQueryequals_xn());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_xq())) {
			sql.append(" and xq=? ");
			input.add(model.getQueryequals_xq());
		}
		
		if (StringUtils.isNotNull(model.getKqkssj()) && StringUtils.isNull(model.getKqjssj())) {
			sql.append(" and to_date(kqsj,'yyyy-mm-dd hh24:mi') >= to_date(?,'yyyy-mm-dd')");
			input.add(model.getKqkssj());
		}
		
		if (StringUtils.isNotNull(model.getKqjssj()) && StringUtils.isNull(model.getKqkssj()) ) {
			sql.append(" and to_date(kqsj,'yyyy-mm-dd hh24:mi') <= to_date(?,'yyyy-mm-dd')");
			input.add(model.getKqjssj());
		}
		
		if (StringUtils.isNotNull(model.getKqkssj()) && StringUtils.isNotNull(model.getKqjssj())) {
			sql.append(" and to_date(kqsj,'yyyy-mm-dd hh24:mi') between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')+1");
			input.add(model.getKqkssj());
			input.add(model.getKqjssj());
		}
		
		map.put("sql", sql.toString());
		map.put("input", input.toArray(new String[] {}));
		return map;
	}
	
	
	
	/**
	 * 考勤统计-按全校（单条记录）
	 * @param model
	 * @return
	 */
	public List<String[]> getKqtjByAll(KqglForm model) {
		HashMap<String, Object> map = getTjSQL(model);

		//自己维护的考勤类型列表
		List<HashMap<String,String>> kqlxList =  getKqlxList();
		String[] output = new String[kqlxList.size()];
		
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		//自己维护的考勤类型
		setKqlxResult(kqlxList, output,sql);
		sql.append("sum(kkxs) kkxs,sum(kkcs) kkcs,sum(cdcs) cdcs,sum(kqcs) kqcs ");
		sql.append("from (select ");
		//自己维护的考勤类型
		setKqlxSql(kqlxList, sql);
		sql.append(map.get("sql"));
		sql.append(") group by kqlx");
		sql.append(")");
		
		return rsToVator(sql.toString(), (String[])map.get("input"), 
				StringUtils.joinStrArr(new String[] {"cdcs","kkxs","kkcs","kqcs"},output));
	}
	
	/**
	 * 学生考勤查询
	 * @param model
	 * @param user
	 * @param outPutList
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2013-01-22
	 */
	public List<String[]> getKqglList(KqglForm model, User user, String[] outPutList)  throws Exception {
		
		List<String> input = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.xh||a.kqsj pkValue,a.* from xg_view_rcsw_kqgl a where 1=1");
		
		if (StringUtils.isNotNull(model.getQuerylike_xh())) {
			sql.append(" and xh like '%'||?||'%' ");
			input.add(model.getQuerylike_xh());
		}
		
		if (StringUtils.isNotNull(model.getQuerylike_xm())) {
			sql.append(" and xm like '%'||?||'%'  ");
			input.add(model.getQuerylike_xm());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_nj())) {
			sql.append(" and nj=? ");
			input.add(model.getQueryequals_nj());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_xydm())) {
			sql.append(" and xydm=? ");
			input.add(model.getQueryequals_xydm());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_zydm())) {
			sql.append(" and zydm=? ");
			input.add(model.getQueryequals_zydm());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_bjdm())) {
			sql.append(" and bjdm=? ");
			input.add(model.getQueryequals_bjdm());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_xn())) {
			sql.append(" and xn=? ");
			input.add(model.getQueryequals_xn());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_xq())) {
			sql.append(" and xq=? ");
			input.add(model.getQueryequals_xq());
		}
		if (StringUtils.isNotNull(model.getQueryequals_kqlx())) {
			sql.append(" and kqlx=? ");
			input.add(model.getQueryequals_kqlx());
		}
		
		sql.append(user.getQueryCondition());
		String[] inPutList =input.toArray(new String[] {});
		return CommonQueryDAO.commonQuery(sql.toString(), "", inPutList, outPutList, model);
	}
}
