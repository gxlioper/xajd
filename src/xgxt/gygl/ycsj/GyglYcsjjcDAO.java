package xgxt.gygl.ycsj;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.gygl.GyglCommForm;
import xgxt.utils.CommonQueryDAO;

public class GyglYcsjjcDAO extends DAO {

	/**
	 * 多人住同一张床异常数据总数
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByDrtcw() throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) count ")
		   .append(" from (select lddm, qsh, cs, cwh, count(1) c")
		   .append(" from xszsxxb group by lddm, qsh, cs, cwh) b")
		   .append(" where b.c > 1 ");
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * 处理返回值 
	 * @param data
	 * @param topTr
	 * @return
	 */
	private HashMap<String,Object> getResult(List<String[]> data,String[] topTr){
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		map.put("data", data);
		map.put("topTr", topTr);
		
		return map;
	}
	
	
	/**
	 * 多人住同一张床的异常数据
	 * @return
	 */
	public HashMap<String,Object> getZsxxByDrtcw(GyglCommForm model){
		//从这xszsxxb表删除异常数据，主键 xh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","xh","xm","lddm","ldmc","qsh","cs","rzrq"};
		String[] topTr = new String[]{"pkValue","学号","姓名","楼栋代码","楼栋名称","寝室号","楼层","入住日期"};
		
		sql.append(" select rownum r,xh pkValue,a.xh,a.lddm,a.qsh,a.cs,a.rzrq,v.xm,s.ldmc from xszsxxb a ")
		   .append(" left join sslddmb s on a.lddm=s.lddm ")
		   .append(" left join view_xsjbxx v on a.xh=v.xh")
		   .append(" where  exists (select 1 from (select lddm, qsh, cs, cwh, count(1) c")
		   .append(" from xszsxxb group by lddm, qsh, cs, cwh) b where b.c > 1")
		   .append(" and a.lddm = b.lddm and a.qsh = b.qsh and a.cs = b.cs and a.cwh = b.cwh)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * 宿舍已入住人数超过所在宿舍的床位总数的宿舍人数
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByZgcws () throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from xszsxxb t where exists")
		   .append(" (select 1 from(select a.rzrs,a.lddm,a.qsh,a.cs,b.cws from")
		   .append(" (select count(1) rzrs,lddm,qsh,cs from xszsxxb group by lddm,qsh,cs) a ")
		   .append(" left join ssxxb b on a.lddm=b.lddm and a.qsh=b.qsh and a.cs=b.cs")
		   .append(" ) c where c.rzrs>c.cws and t.lddm=c.lddm and t.qsh=c.qsh and t.cs=c.cs)");
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * 宿舍已入住人数超过所在宿舍的床位总数的宿舍中的学生
	 * @return
	 */
	public HashMap<String,Object>  getZsxxByZgcws(GyglCommForm model){
		//异常数据存在表 xszsxxb ，主键 xh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","xh","xm","lddm","ldmc","qsh","cs","rzrq"};
		String[] topTr = new String[]{"pkValue","学号","姓名","楼栋代码","楼栋名称","寝室号","楼层","入住日期"};
		
		sql.append(" select rownum r,t.xh pkValue,t.xh,c.xm,t.lddm,b.ldmc,t.qsh,t.cs,t.rzrq from xszsxxb t ")
		   .append(" left join sslddmb b on t.lddm=b.lddm")
		   .append(" left join view_xsjbxx c on t.xh=c.xh")
		   .append(" where exists (select 1 from(")
		   .append(" select a.rzrs,a.lddm,a.qsh,a.cs,b.cws from")
		   .append(" (select count(1) rzrs,lddm,qsh,cs from xszsxxb group by lddm,qsh,cs) a ")
		   .append(" left join ssxxb b on a.lddm=b.lddm and a.qsh=b.qsh and a.cs=b.cs")
		   .append(" ) c where c.rzrs>c.cws and t.lddm=c.lddm and t.qsh=c.qsh and t.cs=c.cs)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * 被分配的保留寝室总数
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByFpblqs() throws SQLException{
		
		String sql = "select count(1) count from ssxxb a where a.fpbj='保留' and exists " +
				"(select 1 from xg_gygl_qsfpb b where a.lddm=b.lddm and a.qsh=b.qsh and a.cs=b.cs)";
		
		return getOneRsint(sql);
	}
	
	
	/**
	 * 分配信息中的保留寝室
	 * @return
	 */
	public HashMap<String,Object> getFpxxByFpblqs(GyglCommForm model){
		
		//异常数据在 xg_gygl_qsfpb 表，主键 lddm||cs||qsh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","lddm","ldmc","cs","qsh","fpdx"};
		String[] topTr = new String[]{"pkValue","楼栋代码","楼栋名称","楼层","寝室号","分配对象"};
		
		sql.append(" select rownum r,t.lddm||t.cs||t.qsh pkValue,t.lddm,s.ldmc,t.cs,t.qsh,t.fpdx,")
		   .append(" t.bmdm from xg_gygl_qsfpb t left join sslddmb s on t.lddm=s.lddm")
		   .append(" where exists (select 1 from ssxxb a ")
		   .append(" where a.fpbj='保留' and t.lddm=a.lddm and t.qsh=a.qsh and t.cs=a.cs)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	/**
	 * 保留寝室住了多少人
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByBlqszr() throws SQLException{
		
		String sql = "select count(1) count from xszsxxb t where exists " +
				"(select 1 from ssxxb a where a.fpbj='保留' and t.lddm=a.lddm and t.qsh=a.qsh and t.cs=a.cs)";
		
		return getOneRsint(sql);
	}
	
	
	/**
	 * 住在保留寝室中的人
	 * @return
	 */
	public HashMap<String,Object> getZsxxByBlqszr(GyglCommForm model){
		//异常数据存在表 xszsxxb，主键 xh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","xh","xm","lddm","ldmc","cs","qsh","cwh"};
		String[] topTr = new String[]{"pkValue","学号","姓名","楼栋代码","楼栋名称","楼层","寝室号","床位号"};
		
		sql.append(" select rownum r,t.xh pkValue,t.xh,v.xm,t.lddm,s.ldmc,t.qsh,t.cs,t.cwh from xszsxxb t ")
		   .append(" left join sslddmb s on t.lddm=s.lddm")
		   .append(" left join view_xsjbxx v on t.xh=v.xh where exists ")
		   .append(" (select 1 from ssxxb a where a.fpbj='保留' and ")
		   .append(" t.lddm=a.lddm and t.qsh=a.qsh and t.cs=a.cs)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * 多少人被分配在行李床位上了
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByXlcwfp() throws SQLException{
		
		String sql = "select count(1) count from xszsxxb t where exists (select 1 from " +
				"xg_gygl_qtcwxxb b where b.cwbj='行李床位' and t.lddm=b.lddm and t.qsh=b.qsh " +
				"and t.cs=b.cs and t.cwh=b.cwh)";
		
		return getOneRsint(sql);
	}
	
	
	/**
	 * 分配住人的行李床位
	 * @return
	 */
	public HashMap<String,Object> getZsxxByXlcwfp(GyglCommForm model){
		
		//异常数据在表 xszsxxb，主键 xh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","xh","xm","lddm","ldmc","cs","qsh","cwh"};
		String[] topTr = new String[]{"pkValue","学号","姓名","楼栋代码","楼栋名称","楼层","寝室号","床位号"};
		
		sql.append(" select rownum r,t.xh pkValue,t.xh,v.xm,t.lddm,s.ldmc,t.qsh,t.cs,t.cwh from xszsxxb t ")
		   .append(" left join sslddmb s on t.lddm=s.lddm")
		   .append(" left join view_xsjbxx v on t.xh=v.xh")
		   .append(" where exists (select 1 from xg_gygl_qtcwxxb b where b.cwbj='行李床位' ")
		   .append(" and t.lddm=b.lddm and t.qsh=b.qsh and t.cs=b.cs and t.cwh=b.cwh)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * 因性别不符分错宿舍的有多少人
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByXbyw() throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from (")
		   .append("select a.lddm,a.qsh,a.cs,a.xh,b.xb from xszsxxb a left join view_xsjbxx b on a.xh=b.xh")
		   .append(") a where exists (select 1 from ssxxb b where a.lddm=b.lddm and a.qsh=b.qsh and a.cs=b.cs and a.xb<>b.xb)");
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * 因性别不符分错宿舍的人
	 * @return
	 */
	public HashMap<String,Object> getZsxxByXbyw(GyglCommForm model){
		
		//异常数据在表 xszsxxb，主键 xh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","xh","xm","xb","lddm","ldmc","qsh","cs"};
		String[] topTr = new String[]{"pkValue","学号","姓名","性别","楼栋代码","楼栋名称","寝室号","楼层"};
		
		sql.append(" select rownum r,a.xh pkValue,a.lddm,a.qsh,a.cs,a.xh,a.xb,c.ldmc,v.xm from(")
		   .append(" select a.lddm,a.qsh,a.cs,a.xh,")
		   .append(" b.xb from xszsxxb a left join view_xsjbxx b on a.xh=b.xh")
		   .append(" ) a left join sslddmb c on a.lddm=c.lddm left join view_xsjbxx v on a.xh=v.xh")
		   .append(" where exists (select 1 from ssxxb b")
		   .append(" where a.lddm=b.lddm and a.qsh=b.qsh and a.cs=b.cs and a.xb<>b.xb)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * 不符合楼栋要求性别的宿舍总数
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByLdxbbf() throws SQLException{
		
		String sql = "select count(1) count from ssxxb a where exists " +
				"(select 1 from sslddmb b where a.lddm=b.lddm and a.xb<>b.xbxd and b.xbxd<>'混合')";
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * 不符合楼栋要求性别的宿舍
	 * @return
	 */
	public HashMap<String,Object> getSsxxByLdxbbf(GyglCommForm model){
		
		//异常数据存在表 ssxxb，主键 lddm||cs||qsh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","lddm","ldmc","qsh","cs","cws","xb"};
		String[] topTr = new String[]{"pkValue","楼栋代码","楼栋名称","寝室号","楼层","床位数","性别"};
		
		sql.append(" select rownum r,a.lddm||a.cs||a.qsh pkValue,a.lddm,")
		   .append(" c.ldmc,a.qsh,a.cs,a.cws,a.xb from ssxxb a ")
		   .append(" left join sslddmb c on a.lddm=c.lddm")
		   .append(" where exists (select 1 from sslddmb b ")
		   .append(" where a.lddm=b.lddm and a.xb<>b.xbxd and b.xbxd<>'混合')");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * 超过所在楼栋最高层数的宿舍有多少个
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByCgzgcs() throws SQLException{
	
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from ssxxb t where exists (select 1 from (select a.lddm,max(to_number(a.cs)) cs,")
		   .append("(select b.cs from sslddmb b where a.lddm=b.lddm) ldcs ")
		   .append(" from ssxxb a group by lddm) c where c.cs>c.ldcs and t.lddm=c.lddm and t.cs=c.cs)");
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * 超过所在楼栋最高层数的宿舍信息
	 * @return
	 */
	public HashMap<String,Object> getSsxxByCgzgcs(GyglCommForm model){
		
		//异常数据在表 ssxxb，主键 lddm||cs||qsh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","lddm","ldmc","cs","zgcs","qsh","cws"};
		String[] topTr = new String[]{"pkValue","楼栋代码","楼栋名称","楼层","最高楼层","寝室号","床位数"};
		
		sql.append(" select rownum r,t.lddm||t.cs||t.qsh pkValue,t.lddm,s.ldmc,")
		   .append(" t.cs,s.cs zgcs,t.qsh,t.cws from ssxxb t ")
		   .append(" left join sslddmb s on t.lddm=s.lddm")
		   .append(" where exists (select 1 from (select a.lddm,max(to_number(a.cs)) cs,")
		   .append(" (select b.cs from sslddmb b where a.lddm=b.lddm) ldcs")
		   .append(" from ssxxb a group by lddm) c where c.cs>c.ldcs and t.lddm=c.lddm and t.cs=c.cs)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * 超过楼栋最高层数的床位信息总数
	 * @return
	 * @throws SQLException
	 */
	public int getCountByCgzgcscw() throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from xg_gygl_cwxxb t where ")
		   .append(" exists (select 1 from (select a.lddm,max(to_number(a.cs)) cs,")
		   .append("(select b.cs from sslddmb b where a.lddm=b.lddm) ldcs ")
		   .append("from xg_gygl_cwxxb a group by lddm) c where c.cs>c.ldcs and t.lddm=c.lddm and t.cs=c.cs)");
	
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * 超过所在楼栋最高层数的床位信息
	 * @return
	 */
	public HashMap<String,Object> getCwxxByCgzgcs(GyglCommForm model){
		
		//异常数据所在表 xg_gygl_cwxxb ，主键 lddm||cs||qsh||cwh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","lddm","ldmc","qsh","cwh","cs","zgcs"};
		String[] topTr = new String[]{"pkValue","楼栋代码","楼栋名称","寝室号","床位号","楼层","最高楼层"};
		
		sql.append(" select rownum r,t.lddm||t.cs||t.qsh||t.cwh pkValue,t.lddm,t.cs,t.qsh, ")
		   .append(" s.ldmc,s.cs zgcs,t.cwh from xg_gygl_cwxxb t")
		   .append(" left join sslddmb s on t.lddm=s.lddm")
		   .append(" where exists (select 1 from (select a.lddm,max(to_number(a.cs)) cs,")
		   .append(" (select b.cs from sslddmb b where a.lddm=b.lddm) ldcs")
		   .append(" from xg_gygl_cwxxb a group by lddm) c ")
		   .append(" where c.cs>c.ldcs and t.lddm=c.lddm and t.cs=c.cs)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * 超过楼栋最高层数的其它床位信息总数
	 * @return
	 * @throws SQLException
	 */
	public int getCountByCgzgcsqtcw() throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from xg_gygl_qtcwxxb t where")
		   .append(" exists (select 1 from (select a.lddm,max(to_number(a.cs)) cs,")
		   .append("(select b.cs from sslddmb b where a.lddm=b.lddm) ldcs ")
		   .append(" from xg_gygl_qtcwxxb a group by lddm) c where c.cs>c.ldcs and t.lddm=c.lddm and t.cs=c.cs)");
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * 超过所在楼栋最高层数的其它床位信息
	 * @return
	 */
	public HashMap<String,Object> getQtcwxxByCgzgcs(GyglCommForm model){
		
		//异常数据所在表 xg_gygl_qtcwxxb ，主键 lddm||cs||qsh||cwh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","lddm","ldmc","cs","zgcs","qsh","cwh"};
		String[] topTr = new String[]{"pkValue","楼栋代码","楼栋名称","楼层","最高楼层","寝室号","床位号"};
		
		sql.append(" select rownum r,t.lddm||t.cs||t.qsh||t.cwh pkValue,t.lddm,t.cs,")
		   .append(" t.qsh,t.cwh,s.ldmc,s.cs zgcs from xg_gygl_qtcwxxb t ")
		   .append(" left join sslddmb s on t.lddm=s.lddm")
		   .append(" where exists (select 1 from (select a.lddm,max(to_number(a.cs)) cs,")
		   .append(" (select b.cs from sslddmb b where a.lddm=b.lddm) ldcs ")
		   .append(" from xg_gygl_qtcwxxb a group by lddm) c ")
		   .append(" where c.cs>c.ldcs and t.lddm=c.lddm and t.cs=c.cs)");
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * 因部门不可混住分错宿舍的总人数
	 * @return
	 * @throws SQLException 
	 */
	public int getCountBybmbkhz() throws SQLException{
		
		String fpdx = getOneRs("select fpdx from xg_gygl_jbszb", new String[]{}, "fpdx");
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from(select a.*,b.kfhz from xg_gygl_qsfpb a ")
		   .append(" left join ssxxb b on a.lddm=b.lddm and a.qsh=b.qsh and a.cs=b.cs")
		   .append(" ) c where c.kfhz='不可' and ")
		   .append(" exists (select 1 from (select a.lddm,a.qsh,a.cs,b.xydm,b.zydm,")
		   .append(" b.bjdm,b.nj from xszsxxb a left join view_xsjbxx b on a.xh=b.xh) d ")
		   .append(" where d.lddm=c.lddm and d.qsh=c.qsh and d.cs=c.cs ");
		
		if ("xy".equals(fpdx)){
			sql.append(" and d.xydm<>c.bmdm");
		} else if ("njxy".equals(fpdx)){
			sql.append(" and d.nj||d.xydm<>c.bmdm");
		} else if ("njzy".equals(fpdx)){
			sql.append(" and d.nj||d.zydm<>c.bmdm");
		} else if ("bj".equals(fpdx)){
			sql.append(" and d.bjdm<>c.bmdm");
		}
		sql.append(")");
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * 因部门不可混住而分配错宿舍的学生
	 * @return
	 */
	public HashMap<String,Object> getSsxxBybmbkhz(GyglCommForm model){
		
		String fpdx = getOneRs("select fpdx from xg_gygl_jbszb", new String[]{}, "fpdx");
		
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","xh","xm","lddm","ldmc","qsh","cs"};
		String[] topTr = new String[]{"pkValue","学号","姓名","楼栋代码","楼栋名称","寝室号","楼层"};
		
		sql.append(" select rownum r,a.xh pkValue,a.xh,a.xm,a.lddm,s.ldmc,a.qsh,a.cs from (")
		   .append(" select a.xh,b.xm,a.lddm,a.qsh,a.cs,b.xydm,b.zydm,b.bjdm,b.nj from xszsxxb a")
		   .append(" left join view_xsjbxx b on a.xh=b.xh) a ")
		   .append(" left join sslddmb s on a.lddm=a.lddm")
		   .append(" where exists (select 1 from (select a.*,b.kfhz from xg_gygl_qsfpb a ")
		   .append(" left join ssxxb b on a.lddm=b.lddm and a.qsh=b.qsh and a.cs=b.cs")
		   .append(" ) c where c.kfhz='不可' and a.lddm=c.lddm and a.qsh=c.qsh and a.cs=c.cs");
		
		if ("xy".equals(fpdx)){
			sql.append(" and a.xydm<>c.bmdm");
		} else if ("njxy".equals(fpdx)){
			sql.append(" and a.nj||a.xydm<>c.bmdm");
		} else if ("njzy".equals(fpdx)){
			sql.append(" and a.nj||a.zydm<>c.bmdm");
		} else if ("bj".equals(fpdx)){
			sql.append(" and a.bjdm<>c.bmdm");
		}
		
		sql.append(" )");
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * 分配信息不符合基本设置中分配方式的宿舍总数
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByJbszbf() throws SQLException{
		
		String sql = "select count(1) count from xg_gygl_qsfpb a where a.fpdx <>(select fpdx from xg_gygl_jbszb)";
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * 分配信息不符合基本设置中分配方式的宿舍
	 * @return
	 */
	public HashMap<String,Object> getFbxxByJbszbf(GyglCommForm model){
		
		//异常数据所在表 xg_gygl_qsfpb ，主键 lddm||cs||qsh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","lddm","ldmc","cs","qsh"};
		String[] topTr = new String[]{"pkValue","楼栋代码","楼栋名称","楼层","寝室号"};
		
		sql.append(" select rownum r,a.lddm||a.cs||a.qsh pkValue,a.lddm,a.cs,a.qsh,")
		   .append(" a.fpdx,b.ldmc from xg_gygl_qsfpb a ")
		   .append(" left join sslddmb b on a.lddm=b.lddm")
		   .append(" where a.fpdx <>(select fpdx from xg_gygl_jbszb)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	
	public boolean delYcsj(String tableName, String pkKey, String[] pkValues) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" delete from ")
		   .append(tableName)
		   .append(" where ")
		   .append(pkKey)
		   .append(" in (");
		
		for (int i = 0 ; i < pkValues.length ; i++){
			sql.append("'")
			   .append(pkValues[i])
			   .append("'");
			   
			if(i != pkValues.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return runUpdate(sql.toString(), new String[]{});
	}
}
