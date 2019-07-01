package xgxt.pjpy.comm.pjpy.ryqd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.utils.String.StringUtils;

public class PjpyRyqdDAO {
	
	
	
	
	/**
	 * 获取评奖学院列表
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getPjxyList(){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append("select distinct(xydm)xydm, xymc from xg_pjpy_xyb a where ");
		sql.append(" exists(select 1 from xg_pjpy_xtszb b where a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd)order by xydm ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xydm","xymc"});
	}
	
	/**
	 * 获取评奖专业列表
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getPjzyList(String xydm,String nj){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select distinct zydm,zymc from view_xg_pjpy_njxyzybj a  where 1=1  ");
		sql.append(" and  exists(select 1 from xg_pjpy_xtszb b where a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd) ");
		List<String>input=new ArrayList<String>();
		if(!Base.isNull(xydm) && !"".equalsIgnoreCase(xydm)){
			sql.append(" and xydm=? ");
			input.add(xydm);
		}
		if(!Base.isNull(nj) && !"".equalsIgnoreCase(nj)){
			sql.append(" and nj = ? ");
			input.add(nj);
		}
		
		sql.append(" order by zydm ");
		
		return dao.getList(sql.toString(),input.toArray(new String[]{}), new String[]{"zydm","zymc"});
	}
	
	
	
	/**
	 * 获取评奖班级列表
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getPjbjList(String xydm,String zydm,String nj){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select distinct bjdm,bjmc from view_xg_pjpy_njxyzybj a where 1=1  ");
		sql.append(" and  exists(select 1 from xg_pjpy_xtszb b where a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd) ");
		List<String>input=new ArrayList<String>();
		if(!Base.isNull(xydm) && !"".equalsIgnoreCase(xydm)){
			sql.append(" and xydm=? ");
			input.add(xydm);
		}
		if(!Base.isNull(zydm) && !"".equalsIgnoreCase(zydm)){
			sql.append(" and zydm=? ");
			input.add(zydm);
		}
		if(!Base.isNull(nj)&& !"".equalsIgnoreCase(nj)){
			sql.append(" and nj = ? ");
			input.add(nj);
		}
		
		sql.append(" order by bjdm ");
		
		return dao.getList(sql.toString(), input.toArray(new String[]{}), new String[]{"bjdm","bjmc"});
	}
	
	/**
	 * 获取评奖年级列表
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getPjnjList(){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append("select distinct(nj)nj from xg_pjpy_bjb a where 1=1 ");
		sql.append(" and  exists(select 1 from xg_pjpy_xtszb b where a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd) ");
		sql.append(" order by nj asc ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"nj"});
	}
	
	
	/**
	 * @param query　查询条件
	 * @return 返回专业列表
	 */
	public List<HashMap<String, String>> getPjZyList(String tableName,String xydm,String nj,String userName,String isFdy,String isBzr) {
		DAO dao=DAO.getInstance();
		String sql = "";
		String querry = " 1=1 ";		
		String query = "";		
		if(xydm != null&&!xydm.equalsIgnoreCase("")){
		    query += " and xydm='"+xydm+"' ";
		}	
		if(StringUtils.isNotNull(nj)){
		    query += " and nj='"+nj+"' ";
		}

		if("true".equalsIgnoreCase(isBzr)&&"true".equalsIgnoreCase(isFdy)){
			//是班主任加辅导员
			querry += " and ( exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='" + userName + "') or exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "'))"  ;
		}else if("true".equalsIgnoreCase(isBzr)){
			//是班主任
			querry += " and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
		}else if("true".equalsIgnoreCase(isFdy)){
			//是辅导员
			querry += " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
		}
			
		sql = "select 'zy' dm,'--请选择--' mc from "+tableName+" where rownum='1' union all select * from (select distinct a.zydm dm,a.zymc mc from "+tableName+" a"
			+ " where 1=1 "+query+" and" + querry + " order by dm)";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	/**
	 * @param query 查询条件
	 * @return 返回班级列表
	 */
	public List<HashMap<String, String>> getPjBjList(String tableName,String query,String userName,String isFdy,String isBzr) {
		DAO dao=DAO.getInstance();
		String [] setpara = query.split("!!-!!");
		String querry = " 1=1 ";
		
		String sql = "select 'bj' dm, '--请选择--' mc from dual" +
		" union all select * from (select distinct" +
		" bjdm dm,bjmc mc from "+tableName+" a where xydm like ? and zydm" +
		"  like ? and nj like ? order by dm)";
		if("true".equalsIgnoreCase(isFdy)&&"true".equalsIgnoreCase(isBzr)){//是辅导员
			querry += " and (exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "') or " +
					"exists(select 1 from  bzrbbb b where a.bjdm=b.bjdm and b.zgh='" + userName + "'))"  ;
			sql = "select 'bj' dm, '--请选择--' mc from dual union all select * from (select distinct bjdm dm,bjmc mc from "+tableName+" a where xydm like ? and zydm like ? and nj like ? and " + querry + " order by bjdm)";
		}else if("true".equalsIgnoreCase(isFdy)){//是辅导员
			querry += " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
			sql = "select 'bj' dm, '--请选择--' mc from dual union all select * from (select distinct bjdm dm,bjmc mc from "+tableName+" a where xydm like ? and zydm like ? and nj like ? and " + querry + " order by bjdm)";
		}else if("true".equalsIgnoreCase(isBzr)){//是班主任
			querry += " and exists(select 1 from  bzrbbb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')";
			sql = "select 'bj' dm, '--请选择--' mc from dual union all select * from (select distinct bjdm dm,bjmc mc from "+tableName+" a where xydm like ? and zydm like ? and nj like ? and " + querry + " order by bjdm)";
		}		
		return dao.getList(sql, setpara , new String[] { "dm", "mc" });
	}
	
	/**
	 * 批量评奖人员设置
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean plRysz(PjpyRyqdForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		String sql=" update xg_pjpy_xsb a set bjdm=? where exists(select 1 from xg_pjpy_xtszb b where a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd) and bjdm=?  ";
		return dao.runUpdate(sql, new String[]{myForm.getPjbjdm(),myForm.getBjdm()});
	}
	
	public HashMap<String,String>getNjXyZy(PjpyRyqdForm myForm){
		DAO dao=DAO.getInstance();
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		String sql=" select nj,xydm,zydm from xg_view_pjpy_njxyzybj where bjdm=? and pjxn=? and pjnd=? and pjxq=? ";
		return dao.getMap(sql, new String[]{myForm.getSave_bjdm(),pjxn,pjnd,pjxq}, new String[]{"nj","xydm","zydm"});
	}
}
