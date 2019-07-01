package xgxt.jygl.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.jygl.model.JyglModel;
import xgxt.utils.New_Random_ID;
import xgxt.utils.form.FormUtils;
import xgxt.utils.sql.SQL_Util;

public class XsJyglDao extends DAO {
	
	/** 
	 * Method getQuery 获得查询条件
	 * @param model JyglModel对象
	 * @return String
	 */
	public String getQuery(JyglModel model){
		String query = " 1=1 ";
		if(!Base.isNull(model.getXh())){
			query += " and xh like '%"+model.getXh()+"%'";
		}
		if(!Base.isNull(model.getHyxh())){
			query += " and hyxh like '%"+model.getHyxh()+"%'";
		}
		if(!Base.isNull(model.getXb())){
			query += " and xb = '"+(model.getXb().equals("1")?"男":"女")+"'";
		}
		if(!Base.isNull(model.getXm())){
			query += " and xm like '%"+DealString.toGBK(model.getXm())+"%'";
		}
		if(!Base.isNull(model.getHybh())){
			query += " and hybh like '%"+model.getHybh()+"%'";
		}
		if(!Base.isNull(model.getBjdm())){
			query += " and bjdm='"+model.getBjdm()+"'";
		}
		if(!Base.isNull(model.getXydm())){
			query += " and xydm='"+model.getXydm()+"'";
		}
		if(!Base.isNull(model.getZydm())){
			query += " and zydm='"+model.getZydm()+"'";
		}
		return query;
	}
	
	/** 
	 * Method getTableTop_db 获得表头
	 * @param tableName 表名
	 * @param cols 字段名
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getTableTop_db(String tableName,String[] cols){
		String[] tabTitleCN = getColumnNameCN(cols, tableName);
		return arrayToList(cols, tabTitleCN);
	}
	
	/** 
	 * Method getHyxx_ser 就业促进会会员信息
	 * @param model JyglModel对象
	 * @return List<String[]>
	 */
	public List<String[]> getHyxx_db(JyglModel model){
		
		String query = getQuery(model);
		String sql = "select * from view_jyglhyxx where "+query;
		String[] cols = new String[] { "xn_id", "hybh", "xm", "xb",
				"hyxh","xymc", "bjmc", "sjhm"};
		return rsToVator2(sql, new String[]{}, cols);	
	}
	
	/** 
	 * Method addHyxx_db 就业促进会会员信息增加
	 * @param model JyglModel对象
	 * @return String
	 * @throws SQLException 
	 */
	public String addHyxx_db(JyglModel model) throws SQLException{
		New_Random_ID newid = new New_Random_ID();
		String tableName = "jygl_hyxx";
		String hyxh = model.getHyxh();
		String hybh = model.getHybh();
		String sql = "select xn_id from "+tableName + " where hybh=?";
		String value = getOneRs(sql, new String[]{hybh}, "xn_id");
		if (!Base.isNull(value)) {
			return "bh exits";
		}
		sql = "select xn_id from "+tableName + " where hyxh=?";
		value = getOneRs(sql, new String[]{hyxh}, "xn_id");
		if (!Base.isNull(value)) {
			return "stu exits";
		}
		String[] columns = new String[] { "xn_id", "hybh", "hyxh", "qsdh",
				"csrq", "sjhm", "zw", "bz" };
		model.setXn_id(newid.new_xnid("jygl_hyxx"));
		boolean flag = insert(SQL_Util.getInsertSqlUseValue(tableName,
				columns, FormUtils.getProperties(columns, model)),
				new String[] {});
		return flag == false ? "insert fail" : "insert success";
	}
	
	/** 
	 * Method oneRecord_db 获得一条记录的信息
	 * @param tableName 表或视图名
	 * @param col 字段名
	 * @param value 字段值
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String> oneRecord_db(String tableName,String col,String value) {
		String sql = "select * from "+tableName+" where "+col+"=?"; 
		return getMapNotOut(sql, new String[]{value});
	}
	/** 
	 * Method modiHyxx_db 修改会员信息
	 * @param model JyglModel对象
	 * @return String
	 */
	public String modiHyxx_db(JyglModel model)
		throws Exception {
		String tableName = "jygl_hyxx";
		String[] columns = new String[] { "csrq", "sjhm", "qsdh", "zw", "bz" };
		boolean flag = runUpdate(SQL_Util.getUpdateSqlByPKValue(tableName,
		"xn_id", model.getXn_id(), columns), FormUtils.getProperties(columns,
				model));
		return flag == true ? "update_success" : "update_fail";
	}
	
	/** 
	 * Method delete_db 删除
	 * @param tableName 表名
	 * @param tableName 主键
	 * @param value 主键值
	 * @return boolean
	 */
	public boolean delete_db(String tableName,String pk,String value)
		throws Exception {
		String sql = "delete  from "+tableName+" where "+pk+"=?";
		return this.runUpdate(sql, new String []{value});
	}
	
	/** 
	 * Method getHdqk_db 就业促进会会员信息
	 * @param rq 日期
	 * @return List<String[]>
	 */
	public List<String[]> getHdqk_db(String rq){
		String[] cols = { "xn_id","RQ", "ZT", "CYXS", "RS", "HDXS", "ZCR", "XS" };
		if(Base.isNull(rq)){
			String sql = "select xn_id,RQ,ZT,CYXS,RS,HDXS,ZCR,XS from jygl_hdqk";
			return rsToVator(sql, new String[]{}, cols);
		}else{
			String sql = "select xn_id,RQ,ZT,CYXS,RS,HDXS,ZCR,XS from jygl_hdqk where rq=?";
			return rsToVator(sql, new String[]{rq}, cols);
		}
		
	}
	/** 
	 * Method getHdxs_db 就业促进会活动形式
	 * @param tableName 表名
	 * @param dmCol 代码字段名
	 * @param mcCol 名称字段名
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getHdxs_db(String tableName,String dmCol,String mcCol){
		String sql = "select "+dmCol+" dm,"+mcCol+" mc from "+tableName;
		return getListNotOut(sql, new String[]{});
	}
	
	/** 
	 * Method addHdqk_db 就业促进会会员信息增加
	 * @param model JyglModel对象
	 * @return boolean
	 * @throws SQLException 
	 */
	public boolean addHdqk_db(JyglModel model) throws SQLException{
		String tableName = "jygl_hdqk";
		New_Random_ID newId = new New_Random_ID();
		model.setXn_id(newId.new_xnid(tableName));
		String[] columns = new String[] { "zt", "xn_id", "rq", "zcr", "xs",
				"hdxs", "qthdxs", "cyxs", "rs", "hdjl", "hdxg", "dd", "kssj",
				"jssj" };
		boolean flag = insert(SQL_Util.getInsertSqlUseValue(tableName,
				columns, FormUtils.getProperties(columns, model)),
				new String[] {});
		return flag;
	}
	/** 
	 * Method modiHdqk_db 修改活动信息
	 * @param model JyglModel对象
	 * @return String
	 */
	public boolean modiHdqk_db(JyglModel model)
		throws Exception {
		String tableName = "jygl_hdqk";
		String[] columns = new String[] { "zt", "xn_id", "rq", "zcr", "xs",
				"hdxs", "qthdxs", "cyxs", "rs", "hdjl", "hdxg", "dd", "kssj",
				"jssj" };
		boolean flag = runUpdate(SQL_Util.getUpdateSqlByPKValue(tableName,
		"xn_id", model.getXn_id(), columns), FormUtils.getProperties(columns,
				model));
		return flag;
	}
	
	/** 
	 * Method getJyphbList 获得就业排行list
	 * @param String nd
	 * @return List<HashMap<String,Object>>
	 */
	public List<HashMap<String,Object>> getJyphbList_db(String nd){
		List<HashMap<String,String>> allrecorde = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,Object>> rs = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String,String>> zyrecorde = new ArrayList<HashMap<String,String>>();
		String sql = "";
		StringBuffer sb = new StringBuffer();
		sb.append("select xymc,zymc,zyrs,qyrs,jyrs,zyqyl,zyjyl,qylmc,jylmc,xyqyrs,xyjyrs,xyrs,(case when xyqyl * 100 >= 10 ");
		sb.append("and xyqyl * 100 < 100 then to_char(xyqyl * 100, '00.00') when xyqyl * 100 < 10 then to_char(xyqyl * 100, '0.00') ");
		sb.append("else to_char(xyqyl * 100, '000.00') end)||'%' xyqyl,(case when xyjyl * 100 >= 10 and xyjyl * 100 < 100 then ");
		sb.append("to_char(xyjyl * 100, '00.00') when xyqyl * 100 < 10 then to_char(xyjyl * 100, '0.00') else ");
		sb.append("to_char(xyjyl * 100, '000.00') end)||'%' xyjyl from (select b.xymc,zymc,zyrs,qyrs,jyrs,");
		sb.append("(case  when (qyrs/zyrs)*100>=10 and (qyrs / zyrs) * 100 < 100 then to_char((qyrs/zyrs)*100,'00.00') when (qyrs/zyrs)*100<10 then ");
		sb.append("to_char((qyrs/zyrs)*100,'0.00') else to_char((qyrs/zyrs)*100,'000.00') end)||'%' zyqyl,(case  when (jyrs/zyrs)*100>=10 and (jyrs / zyrs) * 100 < 100 ");
		sb.append("then to_char((jyrs/zyrs)*100,'00.00') when (jyrs/zyrs)*100<10 then to_char((jyrs/zyrs)*100,'0.00') else ");
		sb.append("to_char((jyrs/zyrs)*100,'000.00') end)||'%' zyjyl,xyqyl,xyjyl,xyqyrs, ");
		sb.append("xyjyrs,xyrs,qylmc,jylmc from (select xymc,zymc,zyrs,nvl((select count(xsxh) from jygl_jyxy  where zydm = a.jygl_zydm and ");
		sb.append("bynd=? and byqxdm in ('01','02','03','12','04','15','17') group by zydm),0) qyrs,nvl((select count(xsxh) ");
		sb.append("from jygl_jyxy where zydm = a.jygl_zydm and bynd=? and byqxdm in ('01','02','03','04','12','13','14','15','17') ");
		sb.append("group by zydm),0) jyrs from (select distinct b.xydm,b.xymc,(select count(xsxh) from jygl_xsjbxxb where ");
		sb.append("a.zydm = zydm and bynd=? group by zydm) zyrs,a.zymc,a.zydm,b.jygl_zydm from jygl_xsjbxxb a, view_jygl_xyzydz b where a.bynd=?");
		sb.append("and a.zydm = b.jygl_zydm group by b.xydm, b.xymc, a.zydm, b.jygl_zydm, a.zymc order by b.xydm) a) b,(select xymc,xyqyl,xyjyl,");
		sb.append("xyqyrs,xyjyrs,xyrs,rank() over( order by xyqyl desc) qylmc,rank() over( order by xyjyl desc) jylmc from (select xymc,sum(qyrs) / sum(zyrs) xyqyl,");
		sb.append("sum(jyrs) / sum(zyrs) xyjyl,sum(qyrs) xyqyrs,sum(jyrs) xyjyrs,sum(zyrs) xyrs from (select xymc,zymc,zyrs,nvl((select count(xsxh) ");
		sb.append(" from jygl_jyxy where zydm = a.jygl_zydm and bynd=? and byqxdm in ('01', '02', '03', '12', '04', '15', '17') ");
		sb.append("group by zydm),0) qyrs,nvl((select count(xsxh) from jygl_jyxy where zydm = a.jygl_zydm and bynd=? and byqxdm in");
		sb.append("('01', '02', '03', '04', '12', '13', '14', '15', '17') group by zydm),0) jyrs from (select distinct b.xydm,");
		sb.append("b.xymc,(select count(xsxh) from jygl_xsjbxxb where a.zydm = zydm and bynd = ? group by zydm) zyrs,");
		sb.append("a.zymc,a.zydm,b.jygl_zydm from jygl_xsjbxxb a, view_jygl_xyzydz b where a.bynd = ? and a.zydm = b.jygl_zydm ");
		sb.append("group by b.xydm, b.xymc, a.zydm, b.jygl_zydm, a.zymc order by b.xydm) a) group by xymc)) c where b.xymc=c.xymc ");
		sb.append("group by b.xymc, zymc, zyrs, qyrs, jyrs,xyqyl,xyjyl,xyqyrs,xyjyrs,xyrs,qylmc,jylmc) a order by xymc");
		sql = sb.toString();
		String[] col = new String[]{"xymc","zymc","zyrs","qyrs","zyqyl","qylmc","jyrs","zyjyl","jylmc","xyqyrs","xyjyrs","xyrs","xyqyl","xyjyl"};
		allrecorde = getList(sql, new String[]{nd,nd,nd,nd,nd,nd,nd,nd},col );
		if(allrecorde != null && allrecorde.size()>0){
			String xymc = allrecorde.get(0).get("xymc");
			String qylmc = allrecorde.get(0).get("qylmc");
			String jylmc = allrecorde.get(0).get("jylmc");
			String xyqyrs = allrecorde.get(0).get("xyqyrs");
			String xyjyrs = allrecorde.get(0).get("xyjyrs");
			String xyrs = allrecorde.get(0).get("xyrs");
			String xyqyl = allrecorde.get(0).get("xyqyl");
			String xyjyl = allrecorde.get(0).get("xyjyl");
			for(int i = 0;i < allrecorde.size()+1;i++){
				HashMap<String,String> map = new HashMap<String,String>();
				String xymc2 = "";
				if(i < allrecorde.size()){
					map = allrecorde.get(i);
					xymc2 = map.get("xymc");
				}		
				if(!xymc.equals(xymc2)){				
					HashMap<String,Object> xyzymap = new HashMap<String,Object>();
					xyzymap.put("xymc", xymc);
					xyzymap.put("qylmc", qylmc);
					xyzymap.put("jylmc", jylmc);
					xyzymap.put("xyqyrs", xyqyrs);
					xyzymap.put("xyjyrs", xyjyrs);
					xyzymap.put("xyrs", xyrs);
					xyzymap.put("xyqyl", xyqyl);
					xyzymap.put("xyjyl", xyjyl);
					xyzymap.put("xyzymap",zyrecorde);
					xyzymap.put("count", zyrecorde.size()+1);
					rs.add(xyzymap);
					if(i < allrecorde.size()){
						zyrecorde = new ArrayList<HashMap<String,String>>();
						xymc = xymc2;
						qylmc = map.get("qylmc");
						jylmc = map.get("jylmc");
						xyqyrs = map.get("xyqyrs");
						xyjyrs = map.get("xyjyrs");
						xyrs = map.get("xyrs");
						xyqyl = map.get("xyqyl");
						xyjyl = map.get("xyjyl");
					}			
				}
				if(i < allrecorde.size()){
					zyrecorde.add(map);
				}			
			}
		}
		return rs;
	}
	
	/** 
	 * Method getXjspList_db 获得薪金水平list
	 * @param String nd
	 * @return List<HashMap<String,Object>>
	 */
	public List<HashMap<String,Object>> getXjspList_db(String nd){
		List<HashMap<String,String>> allrecorde = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,Object>> rs = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String,String>> zyrecorde = new ArrayList<HashMap<String,String>>();
		String sql = "";
		StringBuffer sb = new StringBuffer();
		sb.append("select xymc,zymc,nvl(count(xsxh),0) zyrs,nvl(sum(dynypjgz),0) zydynyzgz,round(nvl(sum(dynypjgz),0)/count(xsxh),0) zydynypjgz,");
		sb.append("nvl((select count(xsxh) from jygl_jyxy where byqxdm = '01' and bynd = ? and ");
		sb.append("to_number(dynypjgz) >= 0 and to_number(dynypjgz) < 1000 and zymc = a.zymc group by zymc),0) xj1,");
		sb.append("nvl((select count(xsxh) from jygl_jyxy where byqxdm = '01' and bynd = ? and ");
		sb.append("to_number(dynypjgz) >= 1000 and to_number(dynypjgz) < 1500 and zymc = a.zymc group by zymc),0) xj2,");
		sb.append("nvl((select count(xsxh) from jygl_jyxy where byqxdm = '01' and bynd = ? and ");
		sb.append("to_number(dynypjgz) >= 1500 and to_number(dynypjgz) < 2000 and zymc = a.zymc group by zymc),0) xj3,");
		sb.append("nvl((select count(xsxh) from jygl_jyxy where byqxdm = '01' and bynd = ? and ");
		sb.append(" to_number(dynypjgz) >= 2000 and to_number(dynypjgz) < 2500 and zymc = a.zymc group by zymc),0) xj4,");
		sb.append("nvl((select count(xsxh) from jygl_jyxy where byqxdm = '01' and bynd = ? and ");
		sb.append("to_number(dynypjgz) >= 2500 and to_number(dynypjgz) < 3000 and zymc = a.zymc group by zymc),0) xj5,");
		sb.append("nvl((select count(xsxh) from jygl_jyxy where byqxdm = '01' and bynd = ? and ");
		sb.append(" to_number(dynypjgz) >= 3000  and zymc = a.zymc group by zymc),0) xj6 from jygl_jyxy a");
		sb.append(" where byqxdm = '01' and bynd = ? group by xymc, zymc order by xymc");
		sql = sb.toString();
		String[] col = new String[]{"xymc","zymc","zyrs","zydynyzgz","zydynypjgz","xj1","xj2","xj3","xj4","xj5","xj6"};
		allrecorde = getList(sql, new String[]{nd,nd,nd,nd,nd,nd,nd},col );
		if(allrecorde != null && allrecorde.size()>0){
			String xymc = allrecorde.get(0).get("xymc");
			int xj1 = 0;
			int xj2 = 0;
			int xj3 = 0;
			int xj4 = 0;
			int xj5 = 0;
			int xj6 = 0;
			int xydynyzgz = 0;
			int xyrs = 0;
			for(int i = 0;i < allrecorde.size()+1;i++){
				HashMap<String,String> map = new HashMap<String,String>();
				String xymc2 = "";
				if(i < allrecorde.size()){
					map = allrecorde.get(i);
					xymc2 = map.get("xymc");
				}		
				if(!xymc.equals(xymc2)){				
					HashMap<String,Object> xyzymap = new HashMap<String,Object>();
					xyzymap.put("xymc", xymc);
					xyzymap.put("xj1", xj1);
					xyzymap.put("xj2", xj2);
					xyzymap.put("xj3", xj3);
					xyzymap.put("xj4", xj4);
					xyzymap.put("xj5", xj5);
					xyzymap.put("xj6", xj6);
					xyzymap.put("xydynypjgz", xydynyzgz/xyrs);
					xyzymap.put("xyrs", xyrs);
					xyzymap.put("xyzymap",zyrecorde);
					xyzymap.put("count", zyrecorde.size()+1);
					rs.add(xyzymap);
					if(i < allrecorde.size()){
						zyrecorde = new ArrayList<HashMap<String,String>>();
						xymc = xymc2;
						xj1 = 0;
						xj2 = 0;
						xj3 = 0;
						xj4 = 0;
						xj5 = 0;
						xj6 = 0;
						xydynyzgz = 0;
						xyrs = 0;
					}			
				}
				if(i < allrecorde.size()){
					xj1 = xj1 + Integer.parseInt(map.get("xj1"));
					xj2 = xj2 + Integer.parseInt(map.get("xj2"));
					xj3 = xj3 + Integer.parseInt(map.get("xj3"));
					xj4 = xj4 + Integer.parseInt(map.get("xj4"));
					xj5 = xj5 + Integer.parseInt(map.get("xj5"));
					xj6 = xj6 + Integer.parseInt(map.get("xj6"));
					xydynyzgz = xydynyzgz+Integer.parseInt(map.get("zydynyzgz"));
					xyrs = xyrs + Integer.parseInt(map.get("zyrs"));
					zyrecorde.add(map);
				}			
			}
		}
	
		return rs;
	}
	
	/** 
	 * Method getZydklList_db 获得专业对口list
	 * @param String nd
	 * @return List<HashMap<String,Object>>
	 */
	public List<HashMap<String,Object>> getZydklList_db(String nd){
		List<HashMap<String,String>> allrecorde = new ArrayList<HashMap<String,String>>();
		List<HashMap<String,Object>> rs = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String,String>> zyrecorde = new ArrayList<HashMap<String,String>>();
		String sql = "";
		StringBuffer sb = new StringBuffer();
		sb.append("select xymc,zymc,zyrs,bzyrs,kzyrs,(case when (bzyrs / zyrs) * 100 >= 10 and (bzyrs / zyrs) * 100 < 100 then ");
		sb.append("to_char((bzyrs / zyrs) * 100, '00.00') when (bzyrs / zyrs) * 100 < 10 then to_char((bzyrs / zyrs) * 100, '0.00') ");
		sb.append("else to_char((bzyrs / zyrs) * 100, '000.00') end) || '%' zydkl from (select xymc,zymc,nvl(count(xsxh),0) zyrs,");
		sb.append("nvl((select count(xsxh) from jygl_jyxy where byqxdm='01' and bynd = ? and zydk = '1' and zymc = a.zymc group by zymc),0) bzyrs,");
		sb.append("nvl((select count(xsxh) from jygl_jyxy where byqxdm='01' and bynd = ? and zydk = '2' and zymc = a.zymc group by zymc),0) kzyrs ");
		sb.append("from jygl_jyxy a where byqxdm = '01' and bynd = ? and zydk is not null group by xymc,zymc order by xymc)");
		sql = sb.toString();
		String[] col = new String[]{"xymc","zymc","zyrs","bzyrs","kzyrs","zydkl"};
		allrecorde = getList(sql, new String[]{nd,nd,nd},col );
		if(allrecorde != null && allrecorde.size()>0){
			String xymc = allrecorde.get(0).get("xymc");
			int xyrs = 0;
			int xybzyrs = 0;
			int xykzyrs = 0;
			for(int i = 0;i < allrecorde.size()+1;i++){
				HashMap<String,String> map = new HashMap<String,String>();
				String xymc2 = "";
				if(i < allrecorde.size()){
					map = allrecorde.get(i);
					xymc2 = map.get("xymc");
				}		
				if(!xymc.equals(xymc2)){				
					HashMap<String,Object> xyzymap = new HashMap<String,Object>();
					xyzymap.put("xymc", xymc);
					xyzymap.put("xyrs", xyrs);
					xyzymap.put("xybzyrs", xybzyrs);
					xyzymap.put("xykzyrs", xykzyrs);
					float per = ((float)((int)((((float)(xybzyrs*100))/xyrs)*100)))/100;
					xyzymap.put("xyzydkl", per+"%");
					xyzymap.put("xyzymap",zyrecorde);
					xyzymap.put("count", zyrecorde.size()+1);
					rs.add(xyzymap);
					if(i < allrecorde.size()){
						zyrecorde = new ArrayList<HashMap<String,String>>();
						xymc = xymc2;
						xyrs = 0;
						xybzyrs = 0;
						xykzyrs = 0;
					}			
				}
				if(i < allrecorde.size()){
					xyrs = xyrs + Integer.parseInt(map.get("zyrs"));
					xybzyrs = xybzyrs + Integer.parseInt(map.get("bzyrs"));
					xykzyrs = xykzyrs + Integer.parseInt(map.get("kzyrs"));
					zyrecorde.add(map);
				}			
			}
		}
		return rs;
	}
	public List dao_Dyfb(String bynd) throws Exception {
		DAO dao = new DAO();
		String sql = "select a.xymc, b.byrs, c.jyrs, nvl(sum(a.jq),'0') jq, nvl(sum(a.wd),'0') wd, nvl(sum(a.sq),'0') sq from (select a.xymc,"
				+ " a.bynd,(case when dqlx = '郊区' then 1 else 0 end) jq, (case when dqlx = '外地' then 1"
				+ " else 0 end) wd, (case when dqlx = '市区' then 1 else 0 end) sq from jygl_jyxy a"
				+ " where a.byqxdm = '01' and a.bynd = ? ) a, (select a.xymc, nvl(count(a.xsxh),'0') byrs "
				+ " from jygl_jyxy a where a.bynd = ? group by a.xymc) b, (select a.xymc, nvl(count(a.xsxh),'0') jyrs from jygl_jyxy a"
				+ " where a.byqxdm = '01' and a.bynd = ? group by a.xymc) c"
				+ " where a.xymc = b.xymc and b.xymc = c.xymc group by a.xymc, b.byrs, c.jyrs order by a.xymc";

		String[] colList = new String[] { "xymc", "byrs", "jyrs", "jq", "wd",
				"sq" };

		List list = dao.getList(sql, new String[] { bynd, bynd, bynd }, colList);
		return list;
	}
	
	public List dao_Xzqk(String bynd) throws Exception {
		DAO dao = new DAO();
		String sql = "select a.xymc, b.byrs, c.jyrs, nvl(sum(a.gy),'0') gy, nvl(sum(a.jt),'0') jt, nvl(sum(a.my),'0') my, nvl(sum(a.sy),'0') sy,"
				+ " nvl(sum(a.gfz),'0') gfz, nvl(sum(a.hz),'0') hz, nvl(sum(a.dz),'0') dz from (select a.xymc, a.bynd, (case when dwxzdm2 = '1' then"
				+ " 1 else 0 end) gy, (case when dwxzdm2 = '2' then 1 else 0 end) jt, (case when dwxzdm2 = '3' then"
				+ " 1 else 0 end) my, (case when dwxzdm2 = '4' then 1 else 0 end) sy, (case when dwxzdm2 = '5' then"
				+ " 1 else 0 end) gfz, (case when dwxzdm2 = '6' then 1 else 0 end) hz, (case when dwxzdm2 = '7' then"
				+ " 1 else 0 end) dz from jygl_jyxy a where a.byqxdm = '01' and a.bynd = ?) a,(select a.xymc, nvl(count(a.xsxh),'0')"
				+ " byrs from jygl_jyxy a where a.bynd = ? group by a.xymc) b, (select a.xymc, nvl(count(a.xsxh),'0') jyrs from jygl_jyxy a"
				+ " where a.byqxdm = '01' and a.bynd = ? group by a.xymc) c where a.xymc = b.xymc and b.xymc = c.xymc group by a.xymc,"
				+ " b.byrs, c.jyrs order by a.xymc";

		String[] colList = new String[] { "xymc", "byrs", "jyrs", "gy", "jt",
				"my", "sy", "gfz", "hz", "dz" };

		List list = dao
				.getList(sql, new String[] { bynd, bynd, bynd }, colList);
		return list;
	}
	
	public List dao_Hyfb(String bynd) throws Exception {
		DAO dao = new DAO();
		String sql = "select a.xymc, b.byrs, c.jyrs, nvl(sum(a.zzy),'0') zzy, nvl(sum(a.shfw),'0') shfw, nvl(sum(a.ydtx),'0') ydtx, nvl(sum(a.jrbx),'0') jrbx,"
				+ " nvl(sum(a.jykj),'0') jykj, nvl(sum(a.pfls),'0') pfls, nvl(sum(a.jzy),'0') jzy, nvl(sum(a.jtys),'0') jtys, nvl(sum(a.gjjg),'0') gjjg, nvl(sum(a.qt),'0') qt from (select a.xymc,"
				+ " a.bynd, (case when hyfl = '制造业' then 1 else 0 end) zzy, (case when hyfl = '社会服务业' then 1"
				+ " else 0 end) shfw, (case when hyfl = '邮电通信、计算机信息业' then 1 else 0 end) ydtx, (case when hyfl = "
				+ "'金融、保险及房地产业' then 1 else 0 end) jrbx, (case when hyfl = '教育科研,文广,体育卫生和社会福利业' then"
				+ " 1 else 0 end) jykj, (case when hyfl = '批发零售、贸易、餐饮业' then 1 else 0 end) pfls, (case when hyfl = '建筑业' then"
				+ " 1 else 0 end) jzy, (case when hyfl = '交通运输、仓储业' then 1 else 0 end) jtys, (case when hyfl "
				+ " = '国家、党政机关和社会团体' then 1 else 0 end) gjjg, (case when hyfl = '其他' then 1 else 0 end) qt from jygl_jyxy a"
				+ " where a.byqxdm = '01' and a.bynd = ?) a, (select a.xymc, nvl(count(a.xsxh),'0') byrs from jygl_jyxy a where a.bynd = ? group by a.xymc) b,"
				+ " (select a.xymc, nvl(count(a.xsxh),'0') jyrs from jygl_jyxy a where a.byqxdm = '01' and a.bynd = ? group by a.xymc) c"
				+ " where a.xymc = b.xymc and b.xymc = c.xymc group by a.xymc, b.byrs, c.jyrs order by a.xymc";

		String[] colList = new String[] { "xymc", "byrs", "jyrs", "zzy",
				"shfw", "ydtx", "jrbx", "jykj", "pfls", "jzy", "jtys", "gjjg",
				"qt" };

		List list = dao
				.getList(sql, new String[] { bynd, bynd, bynd }, colList);
		return list;
	}
	
	public List dao_Zydkl(String bynd) throws Exception {
		DAO dao = new DAO();
		String sql = "select b.xymc, b.zymc, (b.bzyrs + b.kzyrs) cyrs, b.bzyrs, b.kzyrs, c.sumcyrs, c.sumbzyrs,"
				+ " c.sumkzyrs from (select a.xymc, a.zymc, nvl(sum(a.bzyrs),'0') bzyrs, nvl(sum(a.kzyrs),'0') kzyrs from (select a.xymc, a.zymc,"
				+ " a.bynd, (case when a.zydk = '1' then 1 else 0 end) bzyrs, (case when a.zydk = '2' then 1 else 0 end) kzyrs"
				+ " from jygl_jyxy a where a.byqxdm = '01' and a.bynd = ? and a.zydk is not null) a group by a.zymc, a.xymc) b"
				+ " left join (select xymc, (sumbzyrs + sumkzyrs) sumcyrs, sumbzyrs, sumkzyrs from (select xymc, nvl(sum(bzyrs),'0') sumbzyrs,"
				+ " nvl(sum(kzyrs),'0') sumkzyrs from (select a.xymc, a.bynd, (case when a.zydk = '1' then 1 else 0 end) bzyrs, (case"
				+ " when a.zydk = '2' then 1 else 0 end) kzyrs from jygl_jyxy a where a.byqxdm = '01' and a.bynd = ?"
				+ " and a.zydk is not null) group by xymc)) c on b.xymc = c.xymc order by b.xymc";

		String[] colList = new String[] { "xymc", "zymc", "cyrs", "bzyrs",
				"kzyrs", "sumcyrs", "sumbzyrs", "sumkzyrs" };

		List list = dao.getList(sql, new String[] { bynd,bynd }, colList);
		return list;
	}
}
