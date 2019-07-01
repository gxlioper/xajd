package xsgzgl.jxgl.general.jxjzgl.lsjzgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.jxgl.general.jxjzgl.jxjzgl.JxjzglFrom;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_历史建制管理_Dao类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 易江东
 * @version 1.0
 */

public class LsjzglDAO extends CommDAO {
	/**
	 * 查询人数总数统计表
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRszjTjb(LsjzglFrom model)
			throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();

		sql.append(" select cxrs, ybzrs, (cxrs - ybzrs) wbzrs ");
		sql.append(" from (select (select count(1) from xg_jxgl_jxcxmdb where cxqk = 'cx' and jxid=? ) cxrs, ");
		sql.append(" (select count(1) ");
		sql.append(" from (select * ");
		sql.append(" from xg_jxgl_jxjzmdb a ");
		sql.append(" inner join (select jzid, ");
		sql.append(" jzmc, ");
		sql.append(" jzjb, ");
		sql.append(" sjid, ");
		sql.append(" jgmc, ");
		sql.append(" jgdh, ");
		sql.append(" jsmc, ");
		sql.append(" jsdh, ");
		sql.append(" SYS_CONNECT_BY_PATH(jzmc, ' ') treejzmc ");
		sql.append(" FROM xg_jxgl_jxjzwhb ");
		sql.append(" START WITH sjid =? ");
		sql.append(" CONNECT BY sjid = PRIOR jzid) b ");
		sql.append(" on a.jzid = b.jzid)) ybzrs ");
		sql.append(" from dual) ");

		String[] inputValue = new String[] {model.getSjid(),model.getSjid()};
		String[] outputValue = new String[] { "cxrs", "ybzrs", "wbzrs"};
		HashMap<String, String> rs = dao.getMap(sql.toString(), inputValue,
				outputValue);
		return rs;
	}
	
	/**
	 * 查询军训等级
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxJzdj(LsjzglFrom model) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select dm djdm, mc djmc, dj djdj, bm djbm ");
		sql.append(" from xg_jxgl_jxjzdjb ");
		sql.append(" where to_number(dm) >= ");
		sql.append(" to_number((select dj ");
		sql.append(" from (select a.jzjb, b.dj ");
		sql.append(" from xg_jxgl_jxjzwhb a ");
		sql.append(" left join xg_jxgl_jxjzdjb b ");
		sql.append(" on a.jzjb = b.dm ");
		sql.append(" where sjid = ?) ");
		sql.append(" group by dj)) ");
		sql.append(" order by to_number(dj) asc ");
	
		String[] inputValue = new String[] { model.getSjid() };
		String[] outputValue = new String[] { "djdm", "djmc", "djdj","djbm" };
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		return list;
	}
	
	/**
	 * 查询军训建制统计表
	 * @param jzdjList
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjzTjb(List<HashMap<String, String>> jzdjList,LsjzglFrom model) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		int jzdj=jzdjList.size();
		
		String[] outputValue = new String[jzdjList.size()*4+6];
		
		StringBuffer show1 = new StringBuffer();
		StringBuffer show2 = new StringBuffer();
		StringBuffer show3 = new StringBuffer();
		for (int i = 0; i < jzdj; i++) {
			if(i == 0){
				show1.append(" a.jzid");
				show2.append("jxjz");
			}else{
				show1.append(" ,a.jzid");
				show2.append(",jxjz");
			}
			
			show1.append(jzdjList.get(i).get("djdm"));
			show1.append(", a.jzmc");
			show1.append(jzdjList.get(i).get("djdm"));
			show1.append(", a.jgmc");
			show1.append(jzdjList.get(i).get("djdm"));
			show1.append(", a.jsmc");
			show1.append(jzdjList.get(i).get("djdm"));
			
			show2.append(jzdjList.get(i).get("djdm"));
			show2.append(".jzid jzid");
			show2.append(jzdjList.get(i).get("djdm"));
			show2.append(",jxjz");
			show2.append(jzdjList.get(i).get("djdm"));
			show2.append(".jzmc jzmc");
			show2.append(jzdjList.get(i).get("djdm"));
			show2.append(",jxjz");
			show2.append(jzdjList.get(i).get("djdm"));
			show2.append(".jgmc jgmc");
			show2.append(jzdjList.get(i).get("djdm"));
			show2.append(",jxjz");
			show2.append(jzdjList.get(i).get("djdm"));
			show2.append(".jsmc jsmc");
			show2.append(jzdjList.get(i).get("djdm"));
			
			show3.append(" left join xg_jxgl_jxjzwhb ");
			show3.append("jxjz");
			show3.append(jzdjList.get(i).get("djdm"));
			if(i == 0){
				show3.append(" on a.jzid = ");
				show3.append("jxjz");
				show3.append(jzdjList.get(i).get("djdm"));
				show3.append(".sjid ");
			}else{
				show3.append(" on ");
				show3.append("jxjz");
				show3.append(jzdjList.get(i-1).get("djdm"));
				show3.append(".jzid = ");
				show3.append("jxjz");
				show3.append(jzdjList.get(i).get("djdm"));
				show3.append(".sjid ");
			}
			outputValue[((i*4))]="jzid"+jzdjList.get(i).get("djdm");
			outputValue[((i*4)+1)]="jzmc"+jzdjList.get(i).get("djdm");
			outputValue[((i*4)+2)]="jgmc"+jzdjList.get(i).get("djdm");
			outputValue[((i*4)+3)]="jsmc"+jzdjList.get(i).get("djdm");
		}
		
		sql.append(" select  ");
		sql.append(show1);
		sql.append(" ,count(a.xh) jzrs ");
		sql.append(" ,a.bjdm,a.bjmc,a.xydm,a.xymc,a.xb ");
		sql.append(" from (select a.*, b.xh, c.xymc,c.xydm,c.bjdm,c.bjmc,c.xb ");
		sql.append(" from (select ");
		sql.append(show2);
		sql.append(" from xg_jxgl_jxjzwhb a ");
		sql.append(show3);
		sql.append(" where a.jzid = ?) a ");
		sql.append(" left join xg_jxgl_jxjzmdb b ");
			
		if(checkXxdmExists(GlobalsVariable.JXGL_JXJZ_SIXLV)) {
			sql.append(" on a.jzid006 = b.jzid ");
		}else if(checkXxdmExists(GlobalsVariable.JXGL_JXJZ_FIVELV)) {
			sql.append(" on a.jzid005 = b.jzid ");
		}else {
			sql.append(" on a.jzid004 = b.jzid ");
		}
			
		sql.append(" left join (select a.xh, d.xymc,d.zymc,d.bjmc, ");
		sql.append(" a.xm, ");
		sql.append(" (case a.xb ");
		sql.append(" when '1' then ");
		sql.append(" '男' ");
		sql.append(" when '2' then ");
		sql.append(" '女' ");
		sql.append(" else ");
		sql.append(" a.xb ");
		sql.append(" end) xb, ");
		sql.append(" a.nj, ");
		sql.append(" a.xydm, ");
		sql.append(" a.zydm, ");
		sql.append(" a.bjdm ");
		sql.append(" from (select xh, ");
		sql.append(" xm, ");
		sql.append(" xbm xb, ");
		sql.append(" to_char(nj) nj, ");
		sql.append(" bmdm xydm, ");
		sql.append(" zydm, ");
		sql.append(" bjdm ");
		sql.append(" from bks_xsjbxx a ");
		sql.append(" where not exists ");
		sql.append(" (select 1 from xsxxb b where a.xh = b.xh) ");
		sql.append(" union all ");
		sql.append(" select a.xh, ");
		sql.append(" a.xm, ");
		sql.append(" a.xb, ");
		sql.append(" a.nj, ");
		sql.append(" a.xydm, ");
		sql.append(" a.zydm, ");
		sql.append(" a.bjdm ");
		sql.append(" from xsxxb a ");
		sql.append(" where (sfyby = '否' or sfyby is null) ");
		sql.append(" and (sfzx = '在校' or sfzx is null)) a left join view_njxyzybj_all d on a.bjdm = d.bjdm ) c ");
		sql.append(" on b.xh = c.xh ) a");
		sql.append(" group by ");
		sql.append(show1);
		sql.append(" ,a.bjdm,a.bjmc,a.xydm,a.xymc,a.xb ");
		
		for (int i = 0; i < jzdjList.size(); i++) {
			if(i == 0){
				sql.append(" order by ");
			}else{
				sql.append(",");
			}
			sql.append("a.jzid");
			sql.append(jzdjList.get(i).get("djdm"));
			sql.append(" asc ");
			
			if(i == (jzdjList.size()+1)){
				sql.append(",a.bjdm");
				sql.append(" asc ");
			}
		}
		

		String[] inputValue = new String[] { model.getSjid() };
		outputValue[(jzdj*4)]="jzrs";
		outputValue[(jzdj*4)+1]="xydm";
		outputValue[(jzdj*4)+2]="xymc";
		outputValue[(jzdj*4)+3]="bjdm";
		outputValue[(jzdj*4)+4]="bjmc";
		outputValue[(jzdj*4)+5]="xb";
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * 查询建制学生信息列表 带分页
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getJxjzmdList(LsjzglFrom model,List<HashMap<String, String>> djList)
			throws Exception {
		//军训建制高级查询等级sql
		StringBuffer djsql=new StringBuffer();
		StringBuffer djzdSql=new StringBuffer();
		//因为内容是数据库传过来的,所以不会sql注入
		if(djList != null){
			for (int i = 0; i < djList.size(); i++) {
				djsql.append(" func_getjxjzid(jzid,'");
				djsql.append(djList.get(i).get("djdm"));
				djsql.append("') ");
				djsql.append(djList.get(i).get("djbm"));
				djsql.append(",");
				
				djzdSql.append(" b.");
				djzdSql.append(djList.get(i).get("djbm"));
				djzdSql.append(", ");
			}
		}
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		StringBuffer sql = new StringBuffer();

		sql.append(" select a.*,rownum r from ( ");
		sql.append(" select b.mdid,");
		sql.append(" a.xh, ");
		sql.append(djzdSql);
		sql.append(" c.xm, ");
		sql.append(" c.xb, ");
		sql.append(" c.nj, ");
		sql.append(" c.xymc, ");
		sql.append(" c.xydm, ");
		sql.append(" c.zymc, ");
		sql.append(" c.zydm, ");
		sql.append(" c.bjmc, ");
		sql.append(" c.bjdm, ");
		sql.append(" (case ");
		sql.append(" when b.treejzmc is null then ");
		sql.append(" '未分配' ");
		sql.append(" else ");
		sql.append(" b.treejzmc ");
		sql.append("  end) treejzmc, ");
		sql.append(" (case ");
		sql.append(" when b.treejzmc is null then ");
		sql.append(" '否' ");
		sql.append(" else ");
		sql.append(" '是' ");
		sql.append(" end) sfybz ");
		sql.append(" from ");
		sql.append("  (select * from xg_jxgl_jxcxmdb where cxqk='cx' and jxid='");
		sql.append(model.getJxid());
		sql.append("' ) a left join ");
		sql.append(" (select a.*, b.mdid, b.xh ");
		sql.append(" from (select jzid, ");
		sql.append(" jzmc, ");
		sql.append(" jzjb, ");
		sql.append(" sjid, ");
		sql.append(" jgmc, ");
		sql.append(" jgdh, ");
		sql.append(" jsmc, ");
		sql.append(" jsdh, ");
		sql.append(djsql);
		sql.append(" SYS_CONNECT_BY_PATH(jzmc, ' ') treejzmc ");
		sql.append(" FROM xg_jxgl_jxjzwhb ");
		sql.append(" START WITH sjid = '");
		sql.append(model.getSjid());
		sql.append("' ");
		sql.append(" CONNECT BY sjid = PRIOR jzid) a ");
		sql.append(" inner join xg_jxgl_jxjzmdb b ");
		sql.append(" on a.jzid = b.jzid) b ");
		sql.append(" on a.xh=b.xh ");
		sql.append(" left join (select a.xh, ");
		sql.append(" a.xm, ");
		sql.append(" (case a.xb ");
		sql.append(" when '1' then ");
		sql.append(" '男' ");
		sql.append(" when '2' then ");
		sql.append(" '女' ");
		sql.append(" else ");
		sql.append(" a.xb ");
		sql.append(" end) xb, ");
		sql.append(" a.nj, ");
		sql.append(" a.xydm, ");
		sql.append(" a.zydm, ");
		sql.append(" a.bjdm, ");
		sql.append(" b.xymc, ");
		sql.append(" b.zymc, ");
		sql.append(" b.bjmc ");
		sql.append(" from (select xh, ");
		sql.append(" xm, ");
		sql.append(" xbm xb, ");
		sql.append(" to_char(nj) nj, ");
		sql.append(" bmdm xydm, ");
		sql.append(" zydm, ");
		sql.append(" bjdm ");
		sql.append(" from bks_xsjbxx a ");
		sql.append(" where not exists ");
		sql.append(" (select 1 from xsxxb b where a.xh = b.xh) ");
		sql.append(" union all ");
		sql.append(" select a.xh, a.xm, a.xb, a.nj, a.xydm, a.zydm, a.bjdm ");
		sql.append(" from xsxxb a ");
		sql.append(" where (sfyby = '否' or sfyby is null) ");
		sql.append(" and (sfzx = '在校' or sfzx is null)) a ");
		sql.append(" left join view_njxyzybj_all b ");
		sql.append(" on a.bjdm = b.bjdm) c ");
		sql.append("  on a.xh = c.xh ");
		sql.append(" order by c.bjdm asc,b.xh asc ");
		sql.append(" ) a where 1=1 ");

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		String[] colList = new String[] { "xh", "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "treejzmc" };
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj, inputV,
				colList, model);
	}
	
	/**
	 * 查询建制学生信息列表  无分页
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjzmdNoPageList(LsjzglFrom model, List<HashMap<String, String>> djList)
			throws Exception {
		//军训建制高级查询等级sql
		StringBuffer djsql=new StringBuffer();
		StringBuffer djzdSql=new StringBuffer();
		//因为内容是数据库传过来的,所以不会sql注入
		if(djList != null){
			for (int i = 0; i < djList.size(); i++) {
				djsql.append(" func_getjxjzid(jzid,'");
				djsql.append(djList.get(i).get("djdm"));
				djsql.append("') ");
				djsql.append(djList.get(i).get("djbm"));
				djsql.append(",");
				
				djzdSql.append(" b.");
				djzdSql.append(djList.get(i).get("djbm"));
				djzdSql.append(", ");
			}
		}
		
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		
		sql.append(" select a.*,rownum r from ( ");
		sql.append(" select b.mdid,");
		sql.append(" b.xh, ");
		sql.append(djzdSql);
		sql.append(" c.xm, ");
		sql.append(" c.xb, ");
		sql.append(" c.nj, ");
		sql.append(" c.xymc, ");
		sql.append(" c.xydm, ");
		sql.append(" c.zymc, ");
		sql.append(" c.zydm, ");
		sql.append(" c.bjmc, ");
		sql.append(" c.bjdm, ");
		sql.append(" (case ");
		sql.append(" when b.treejzmc is null then ");
		sql.append(" '未分配' ");
		sql.append(" else ");
		sql.append(" b.treejzmc ");
		sql.append("  end) treejzmc, ");
		sql.append(" (case ");
		sql.append(" when b.treejzmc is null then ");
		sql.append(" '否' ");
		sql.append(" else ");
		sql.append(" '是' ");
		sql.append(" end) sfybz ");
		sql.append(" from (select a.*, b.mdid, b.xh ");
		sql.append(" from (select jzid, ");
		sql.append(" jzmc, ");
		sql.append(" jzjb, ");
		sql.append(" sjid, ");
		sql.append(" jgmc, ");
		sql.append(" jgdh, ");
		sql.append(" jsmc, ");
		sql.append(" jsdh, ");
		sql.append(djsql);
		sql.append(" SYS_CONNECT_BY_PATH(jzmc, ' ') treejzmc ");
		sql.append(" FROM xg_jxgl_jxjzwhb ");
		sql.append(" START WITH sjid = '");
		sql.append(model.getSjid());
		sql.append("' ");
		sql.append(" CONNECT BY sjid = PRIOR jzid) a ");
		sql.append(" inner join xg_jxgl_jxjzmdb b ");
		sql.append(" on a.jzid = b.jzid) b ");
		sql.append(" left join (select a.xh, ");
		sql.append(" a.xm, ");
		sql.append(" (case a.xb ");
		sql.append(" when '1' then ");
		sql.append(" '男' ");
		sql.append(" when '2' then ");
		sql.append(" '女' ");
		sql.append(" else ");
		sql.append(" a.xb ");
		sql.append(" end) xb, ");
		sql.append(" a.nj, ");
		sql.append(" a.xydm, ");
		sql.append(" a.zydm, ");
		sql.append(" a.bjdm, ");
		sql.append(" b.xymc, ");
		sql.append(" b.zymc, ");
		sql.append(" b.bjmc ");
		sql.append(" from (select xh, ");
		sql.append(" xm, ");
		sql.append(" xbm xb, ");
		sql.append(" to_char(nj) nj, ");
		sql.append(" bmdm xydm, ");
		sql.append(" zydm, ");
		sql.append(" bjdm ");
		sql.append(" from bks_xsjbxx a ");
		sql.append(" where not exists ");
		sql.append(" (select 1 from xsxxb b where a.xh = b.xh) ");
		sql.append(" union all ");
		sql.append(" select a.xh, a.xm, a.xb, a.nj, a.xydm, a.zydm, a.bjdm ");
		sql.append(" from xsxxb a ");
		sql.append(" where (sfyby = '否' or sfyby is null) ");
		sql.append(" and (sfzx = '在校' or sfzx is null)) a ");
		sql.append(" left join view_njxyzybj_all b ");
		sql.append(" on a.bjdm = b.bjdm) c ");
		sql.append("  on b.xh = c.xh ");
		sql.append(" ) a where 1=1 ");
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputValue = SearchService.getTjInput(searchModel);
		
		sql.append(searchTj);
		
		// 查询条件
		String[] inputV = inputValue;
		String[] colList = new String[] { "xh", "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "treejzmc" };
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputV, colList);
		return list;
	}
	
	/**
	 * 查询军训等级
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxdjList(JxjzglFrom model)
			throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();

		sql.append(" select dm djdm, mc djmc, dj djdj , bm djbm ");
		sql.append(" from xg_jxgl_jxjzdjb ");
		sql.append(" order by to_number(djdj) asc ");

		String[] inputValue = new String[] {};
		String[] outputValue = new String[] { "djdm", "djmc", "djdj","djbm" };
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * 合并数组
	 * @param arrays
	 * @return
	 * @author sjf
	 */
	public String[] uniteArrays(String[]...arrays){
		int length = 0;
		for (int i=0; i<arrays.length; i++){
			length += arrays[i].length;
		}
		
		String[] strs = new String[length];
		
		int count = 0;
		for(String[] array : arrays){
			for(int j=0; j<array.length; j++){
				strs[count++] = array[j];
			}
		}
		
		return strs; 
	}
	
	/**
	 * 获取军训建制sql
	 * @return
	 */
	public HashMap<String, String> getJxjzModelByJzid(List<HashMap<String, String>> list,String jzid){
		DAO dao = DAO.getInstance();
		String jzdjid="";
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select jzid, ");
		sql.append(" jzmc, ");
		sql.append(" jzjb, ");
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				sql.append(" func_getJxjzid(jzid,'");
				sql.append(list.get(i).get("djdm"));
				sql.append("') ");
				sql.append(list.get(i).get("djbm"));
				sql.append(", ");
				
				if(i==0){
					jzdjid=list.get(i).get("djbm");
				}else{
					jzdjid=jzdjid+"@@"+list.get(i).get("djbm");
				}
			}
		}
		sql.append(" sjid, ");
		sql.append(" jgmc, ");
		sql.append(" jgdh, ");
		sql.append(" jsmc, ");
		sql.append(" jsdh, ");
		sql.append(" SYS_CONNECT_BY_PATH(jzmc, ' ') treejzmc ");
		sql.append(" FROM xg_jxgl_jxjzwhb ");
		sql.append(" where jzjb is not null and jzid=? ");
		sql.append(" START WITH jzid = ? ");
		sql.append(" CONNECT BY jzid = PRIOR sjid ");
		
		String[] inputValue = new String[] {jzid,jzid};
		String[] outputValue = new String[] { "jzid", "jzmc", "jzjb","sjid" };
		outputValue=dao.uniteArrays(outputValue,jzdjid.split("@@"));
		HashMap<String, String> rs = dao.getMap(sql.toString(), inputValue,
				outputValue);
		return rs;
	}
	
	/**
	 * 历史建制管理自定义导出
	 * @param model
	 * @param djList
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJxjzmdExportDataList(LsjzglFrom model,List<HashMap<String, String>> djList)
	throws Exception {
		//军训建制高级查询等级sql
		StringBuffer djsql=new StringBuffer();
		StringBuffer djzdSql=new StringBuffer();
		//因为内容是数据库传过来的,所以不会sql注入
		if(djList != null){
			for (int i = 0; i < djList.size(); i++) {
				djsql.append(" func_getjxjzid(jzid,'");
				djsql.append(djList.get(i).get("djdm"));
				djsql.append("') ");
				djsql.append(djList.get(i).get("djbm"));
				djsql.append(",");
				
				djzdSql.append(" b.");
				djzdSql.append(djList.get(i).get("djbm"));
				djzdSql.append(", ");
			}
		}
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select a.*,rownum r from ( ");
		sql.append(" select b.mdid,");
		sql.append(" a.xh, ");
		sql.append(djzdSql);
		sql.append(" c.xm, ");
		sql.append(" c.xb, ");
		sql.append(" c.nj, ");
		sql.append(" c.xymc, ");
		sql.append(" c.xydm, ");
		sql.append(" c.zymc, ");
		sql.append(" c.zydm, ");
		sql.append(" c.bjmc, ");
		sql.append(" c.bjdm, ");
		sql.append(" (case ");
		sql.append(" when b.treejzmc is null then ");
		sql.append(" '未分配' ");
		sql.append(" else ");
		sql.append(" b.treejzmc ");
		sql.append("  end) treejzmc, ");
		sql.append(" (case ");
		sql.append(" when b.treejzmc is null then ");
		sql.append(" '否' ");
		sql.append(" else ");
		sql.append(" '是' ");
		sql.append(" end) sfybz ");
		sql.append(" from ");
		sql.append("  (select * from xg_jxgl_jxcxmdb where cxqk='cx' and jxid='");
		sql.append(model.getJxid());
		sql.append("' ) a left join ");
		sql.append(" (select a.*, b.mdid, b.xh ");
		sql.append(" from (select jzid, ");
		sql.append(" jzmc, ");
		sql.append(" jzjb, ");
		sql.append(" sjid, ");
		sql.append(" jgmc, ");
		sql.append(" jgdh, ");
		sql.append(" jsmc, ");
		sql.append(" jsdh, ");
		sql.append(djsql);
		sql.append(" SYS_CONNECT_BY_PATH(jzmc, ' ') treejzmc ");
		sql.append(" FROM xg_jxgl_jxjzwhb ");
		sql.append(" START WITH sjid = '");
		sql.append(model.getSjid());
		sql.append("' ");
		sql.append(" CONNECT BY sjid = PRIOR jzid) a ");
		sql.append(" inner join xg_jxgl_jxjzmdb b ");
		sql.append(" on a.jzid = b.jzid) b ");
		sql.append(" on a.xh=b.xh ");
		sql.append(" left join (select a.xh, ");
		sql.append(" a.xm, ");
		sql.append(" (case a.xb ");
		sql.append(" when '1' then ");
		sql.append(" '男' ");
		sql.append(" when '2' then ");
		sql.append(" '女' ");
		sql.append(" else ");
		sql.append(" a.xb ");
		sql.append(" end) xb, ");
		sql.append(" a.nj, ");
		sql.append(" a.xydm, ");
		sql.append(" a.zydm, ");
		sql.append(" a.bjdm, ");
		sql.append(" b.xymc, ");
		sql.append(" b.zymc, ");
		sql.append(" b.bjmc ");
		sql.append(" from (select xh, ");
		sql.append(" xm, ");
		sql.append(" xbm xb, ");
		sql.append(" to_char(nj) nj, ");
		sql.append(" bmdm xydm, ");
		sql.append(" zydm, ");
		sql.append(" bjdm ");
		sql.append(" from bks_xsjbxx a ");
		sql.append(" where not exists ");
		sql.append(" (select 1 from xsxxb b where a.xh = b.xh) ");
		sql.append(" union all ");
		sql.append(" select a.xh, a.xm, a.xb, a.nj, a.xydm, a.zydm, a.bjdm ");
		sql.append(" from xsxxb a ");
		sql.append(" where (sfyby = '否' or sfyby is null) ");
		sql.append(" and (sfzx = '在校' or sfzx is null)) a ");
		sql.append(" left join view_njxyzybj_all b ");
		sql.append(" on a.bjdm = b.bjdm) c ");
		sql.append("  on a.xh = c.xh ");
		sql.append(" order by c.bjdm asc,b.xh asc ");
		sql.append(" ) a where 1=1 ");
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		String[] colList = new String[] { "xh", "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "treejzmc" };
		return CommonQueryDAO.commonQueryforList(sql.toString(), searchTj, inputV,
				colList, model);
		}
	
	/**
	 * 验证学校代码
	 */
	// check school code exists in sso
	public boolean checkXxdmExists(String[] array) {
		String xxdm = Base.xxdm;
		for (String s : array) {
			if (s.equalsIgnoreCase(xxdm)) {
				return true;
			}
		}
		return false;
	}
}
