
package xgxt.wjcf.szzy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 深圳职业信息技术学院学生考勤信息DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-12</p>
 */
public class WjcfSzzyDAO  {
	DAO dao = DAO.getInstance();
	ArrayList<String> values = null;//查询条件值
	
	/**
	 * 获取表头
	 * @return
	 */
	public ArrayList<HashMap<String, String>> getSearchTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] enCol = new String[] {"xn||xq||bjdm||kcdm" , "rq||sjd", "kssj", "jssj","skdd", "xymc", "bjmc", "bzr", "rkjs",
				"kcmc", "rs", "sdrs", "cql", "qq", "qjrs", "kqy" };//英文列名
		String[] cnCol = new String[] {"主键", "pk", "kssj", "jssj", "教室", "系别", "班级", "班导师", "授课老师", "课程名",
				"应到(人)", "实到(人)", "出勤率(%)", "缺勤", "请假", "考勤员" };//中文列名
		for (int i = 0; i < enCol.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", enCol[i]);
			map.put("cn", cnCol[i]);
			result.add(map);
			map = null;
		}//end for
		return result;
	}
	
	/**
	 * 获取查询结果
	 * @param szzyQryModel
	 * @param tableName
	 * @return
	 */
	public ArrayList<String[]> getSearchResult(WjcfSzzyQryModel szzyQryModel, String tableName) throws Exception {
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] opCol = new String[] {"主键", "pk","kssj", "jssj", "skdd", "xymc", "bjmc", "bzr", "rkjs",
				"kcmc", "rs", "sdrs", "cql", "qq", "qjrs", "kqy" };//输出列名
		String sql = "select distinct xn||xq||bjdm||kcdm 主键,rq||sjd pk,a.kssj,a.jssj,a.skdd,a.xymc,a.bjmc,a.bzr,a.rkjs,a.kcmc,a.rs,a.sdrs,a.cql,a.qq,a.qjrs,a.kqy from "
				+ tableName 
				+ " a where 1=1 ";
		StringBuffer wherSql = getWhereSql(szzyQryModel);
		result = dao.rsToVator(sql + wherSql.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, opCol);
		return result;
	}
	
	/**
	 * 生成查询条件
	 * @param szzyQryModel
	 * @return
	 */
	private StringBuffer getWhereSql(WjcfSzzyQryModel szzyQryModel) throws Exception {
		String xydm = szzyQryModel.getXydm();
		String zydm = szzyQryModel.getZydm();
		String bjdm = szzyQryModel.getBjdm();
		String xn = szzyQryModel.getXn();
		String xq = szzyQryModel.getXq();
//		String xqj = szzyQryModel.getXqj();
		String rq = szzyQryModel.getRq();
		String nj = szzyQryModel.getNj();
		String jsbh = szzyQryModel.getJsbh();
		String kssj = szzyQryModel.getKssj();
		String jssj = szzyQryModel.getJssj();
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and a.xydm = ?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and a.zydm = ?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and a.bjdm = ?");
			values.add(bjdm);
		}//end if 
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and a.nj = ?");
			values.add(nj);
		}//end if
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and a.xn = ?");
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and a.xq = ?");
			values.add(xq);
		}//end if
		if (!StringUtils.isNull(rq)) {//TODO日期还未确定
			String[] rqkszb = getRqKsXx(rq);
			if (rqkszb != null && rqkszb.length == 2 ) {
				whereSql.append(" and a.xqj = ?");
				whereSql.append(" and a.qsz <= '");
				whereSql.append(rqkszb[1]);
				whereSql.append("' and a.jsz >= '");
				whereSql.append(rqkszb[1]);
				whereSql.append("'");
				values.add(rqkszb[0]);
			}
		}//end if
		if (!StringUtils.isNull(jsbh)) {//教室编号
			whereSql.append(" and a.skdd = ?");
			values.add(jsbh.toUpperCase());
		}//end if
		/*if (!StringUtils.isNull(xqj)) {//TODO星期还未确定
			whereSql.append(" and a.xqj = ?");
			values.add(xqj);
		}*///end if
		if (!StringUtils.isNull(kssj)) {
			whereSql.append(" and a.kssj = ?");
			values.add(kssj);
		}//end if
		if (!StringUtils.isNull(jssj)) {
			whereSql.append(" and a.jssj = ?");
			values.add(jssj);
		}//end if
		return whereSql;
	}
	
	/**
	 * 增加考勤时获取的信息
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getAddResult(String tableName, String pk, String pkValue, String pkValue2){
		String sql = "select xn,xq,bjdm,bjmc,kcdm,kcmc,rs ydrs,sdrs,qjrs,bz,rq,sjd,xn_id,bjsj,gj,kqy,kssj,jssj from "
					+ tableName
					+ " where "
					+ pk
					+ " = ?";
		if (!StringUtils.isNull(pkValue2)){
			sql += " and rq||sjd = '" + pkValue2;
			sql += "'";
		}
		Map<String, String> result = dao.getMapNotOut(sql, new String[]{pkValue});
		return result;
	}
	
	/**
	 * 保存或修改考勤信息
	 * @param tableName
	 * @param pkVal
	 * @param szzyKqxxModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveKqxx(String tableName, String pkVal, WjcfSzzyKqxxModel szzyKqxxModel, String pkValue2) throws Exception {
		boolean flag = false;
		String sql = "delete from " + tableName
					+ " where xn||xq||bjdm||kcdm = ? and rq||sjd = ?";
		String xn = szzyKqxxModel.getXn();
		String xq = szzyKqxxModel.getXq();
		String bjdm = szzyKqxxModel.getBjdm();
		String kcdm = szzyKqxxModel.getKcdm();
//		String xn_id = szzyKqxxModel.getXn_id();
		String ydrs = szzyKqxxModel.getYdrs();
		String sdrs = szzyKqxxModel.getSdrs();
		String qjrs = szzyKqxxModel.getQjrs();
		String bjsj = szzyKqxxModel.getBjsj();
		String gj = szzyKqxxModel.getGj();
		String kqy = DealString.toGBK(szzyKqxxModel.getKqy());
		String rq = szzyKqxxModel.getRq();
		String kssj = szzyKqxxModel.getKssj();
		String jssj = szzyKqxxModel.getJssj();
		String sjd = szzyKqxxModel.getKssj()+"-"+szzyKqxxModel.getJssj();
		String bz = DealString.toGBK(szzyKqxxModel.getBz());
		int sd = sdrs!="" ?Integer.parseInt(sdrs) :0;
		int yd = ydrs!="" ?Integer.parseInt(ydrs) :0;
		int bj = bjsj!="" ?Integer.parseInt(bjsj) :0;
		int gj1 = gj!="" ?Integer.parseInt(gj) :0;
		String qq = String.valueOf(yd-bj-gj1-sd);
		String cql = "";
		if (sd != 0 && yd !=0) {
			cql = new java.text.DecimalFormat("#,##0.0").format((float)(sd+gj1)/((float)yd)*100);
		}
		boolean isDel = dao.runUpdate(sql, new String[]{pkVal,pkValue2});
		if (isDel) {
			sql = "insert into " + tableName 
			   + " (xn,xq,bjdm,kcdm,sdrs,bz,qjrs,rq,sjd,ydrs,cql,qq,kqy,bjsj,gj,kssj,jssj) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
			flag = dao.runUpdate(sql, new String[]{xn,xq,bjdm,kcdm,sdrs,bz,qjrs,rq,sjd,ydrs,cql,qq,kqy,bjsj,gj,kssj,jssj});
		}
		return flag;
	}

	/**
	 * 学工考勤信息查询
	 * @param szzyQryModel
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getKqxxTjJg(WjcfSzzyQryModel szzyQryModel, String tableName) throws Exception {
		ArrayList<String[]> result = null;
		String sql = "select distinct a.xn||a.xq||a.bjdm||a.kcdm 主键,a.rq||a.sjd pk,a.kssj,a.jssj,b.skdd,b.xymc,b.bjmc,b.bzr,b.rkjs,b.kcmc,b.rs,a.sdrs,a.cql,a.qq,a.qjrs,a.kqy from " 
					+ tableName
					+ " a left join view_xskqxx b on a.xn=b.xn and a.xq=b.xq and a.bjdm=b.bjdm and a.kcdm=b.kcdm"
					+ " where 1=1 ";
		StringBuffer wherSql = getWhereSql2(szzyQryModel);
		String[] opCol = new String[] {"主键", "pk", "kssj", "jssj","skdd", "xymc", "bjmc", "bzr", "rkjs",
				"kcmc", "rs", "sdrs", "cql", "qq", "qjrs", "kqy" };//输出列名
		result = dao.rsToVator(sql + wherSql.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, opCol);
		return result;
	}
	
	/**
	 * 输入日期返回该日期在本学期的几周
	 * @param rq
	 * @return
	 * @throws Exception
	 */
	private String[] getRqKsXx(String rq) throws Exception {
		String sql = "select distinct xqj,djz from rqkszb@DBLINK_JW where nyr = ?";//连学年学分制版
		String[] rqksxx = dao.getOneRs(sql, new String[]{rq}, new String[]{"xqj", "djz"});
		return rqksxx;
	}
	
	/**
	 * 生成查询条件
	 * @param szzyQryModel
	 * @return
	 */
	private StringBuffer getWhereSql2(WjcfSzzyQryModel szzyQryModel) {
		String xydm = szzyQryModel.getXydm();
		String zydm = szzyQryModel.getZydm();
		String bjdm = szzyQryModel.getBjdm();
		String xn = szzyQryModel.getXn();
		String xq = szzyQryModel.getXq();
//		String xqj = szzyQryModel.getXqj();
		String rq = szzyQryModel.getRq();
		String nj = szzyQryModel.getNj();
//		String jsbh = szzyQryModel.getJsbh();
		String kssj = szzyQryModel.getKssj();
		String jssj = szzyQryModel.getJssj();
		String nf = szzyQryModel.getNf();
		String yf = szzyQryModel.getYf();
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and b.xydm = ?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and b.zydm = ?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and b.bjdm = ?");
			values.add(bjdm);
		}//end if 
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and b.nj = ?");
			values.add(nj);
		}//end if
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and b.xn = ?");
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and b.xq = ?");
			values.add(xq);
		}//end if
		if (!StringUtils.isNull(rq)) {
			whereSql.append(" and b.rq = ?");
			values.add(rq);
		}//end if
		if (!StringUtils.isNull(kssj)) {
			whereSql.append(" and a.kssj = ?");
			values.add(kssj);
		}//end if
		if (!StringUtils.isNull(jssj)) {
			whereSql.append(" and a.jssj = ?");
			values.add(jssj);
		}//end if
		if (!StringUtils.isNull(nf)) {
			whereSql.append(" and substr(a.rq,1,2) like '");
			whereSql.append(nf.substring(2,4));
			whereSql.append("'");
		}//end if
		if (!StringUtils.isNull(yf)) {
			whereSql.append(" and substr(a.rq,4,2) like '");
			whereSql.append(yf);
			whereSql.append("'");
		}//end if
		return whereSql;
	}
	
}
