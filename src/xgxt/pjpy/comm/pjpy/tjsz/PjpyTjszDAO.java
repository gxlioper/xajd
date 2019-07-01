package xgxt.pjpy.comm.pjpy.tjsz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class PjpyTjszDAO extends DAO {

	
	/**
	 * 根据评奖项目代码查询已设置条件
	 * @param xmdm
	 * @return
	 */
	protected List<HashMap<String,String>> getXmtj(String xmdm){
		
		String sql = "select a.tjdm,(select b.tjmc from xg_pjpy_pjtjkb b where b.tjdm||b.tjms=a.tjdm) tjmc,tjfw,gx,tjz,qtxz from xg_pjpy_tjszb a where xmdm=?";
		
		return getListNotOut(sql, new String[]{xmdm});
	}
	
	/**
	 * 查询条件库中全部条件
	 * 
	 * @return
	 */
	protected List<HashMap<String, String>> getTjk() {

		// ============2011.07.21 edit by 鲁达=======================

		String sql = "select a.tjdm||tjms tjdm,a.tjmc from xg_pjpy_pjtjkb a where sfqy='yes'";

		List<HashMap<String, String>> list = getList(sql, new String[] {},
				new String[] { "tjdm", "tjmc" });

		return list;
	}
	
	/**
	 * 查询条件库中全部条件
	 * @return
	 */
	protected List<HashMap<String,String>> getTjk(String xmdm){
		
		// ============2011.07.21 edit by 鲁达=======================
		String sql = "select a.tjdm||tjms tjdm,a.tjmc from xg_pjpy_pjtjkb a "
				+ "where sfqy='yes' and not exists (select 1 from xg_pjpy_tjszb b where a.tjdm||a.tjms=b.tjdm and b.xmdm=?) ";
		
		return getListNotOut(sql, new String[]{xmdm});
	}
	
	
	/**
	 * 查询指定评奖项目中指定条件信息
	 * @param xmdm
	 * @param tjdm
	 * @return
	 */
	protected List<HashMap<String,String>> getXmtjInfo(String xmdm,String[] tjdm){
		
		// ============2011.07.21 edit by 鲁达=======================
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.tjdm, a.tjmc,a.tsgs, b.tjfw,b.gx,b.tjz,b.qtxz,a.tjvalue from ");
		sql.append("(select a.tjdm||a.tjms tjdm,a.tjmc,a.tsgs,a.tjz tjvalue from xg_pjpy_pjtjkb a where a.tjdm||a.tjms in (");
		for (int i = 0 ; i < tjdm.length ; i ++){
			sql.append("'").append(tjdm[i]).append("'");
			
			if (i != tjdm.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		sql.append(" ) a");
		sql.append(" left join (select * from xg_pjpy_tjszb where xmdm=?)b on a.tjdm = b.tjdm ");
		
		
		return getListNotOut(sql.toString(), new String[]{xmdm});
	}
	/**
	 * lyl
	 * @param xmdm
	 * @param tjdm
	 * @return
	 */
   protected List<HashMap<String,String>> getXmtjInfo(String xmdm,String tjdm){
		
		// ============2011.07.21 edit by 鲁达=======================
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.tjdm, a.tjmc,a.tsgs,a.tjlx, b.tjfw,b.gx,b.tjz,b.qtxz,a.tjvalue,tablename from ");
		sql.append("(select a.tjdm||a.tjms tjdm,a.tjmc,a.tsgs,a.tjz tjvalue,a.tjlx,tablename from xg_pjpy_pjtjkb a where a.tjdm||a.tjms in (");
	    sql.append("'").append(tjdm).append("'");
		sql.append(")");
		sql.append(" ) a");
		sql.append(" left join (select * from xg_pjpy_tjszb where xmdm=?)b on a.tjdm = b.tjdm ");
		
		System.out.println(sql);
		return getListNotOut(sql.toString(), new String[]{xmdm});
	}
	
	/**
	 * 查询困难生级别
	 * @return
	 * @throws SQLException 
	 */
	protected String[] getKnsjb(){
		
		String sql = "select fjmc from xszz_xmfjqkb where xmdm='5002'";
		
		try {
			return getArray(sql, new String[]{}, "fjmc");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 查询课程性质
	 * @return
	 */
	protected String[] getKcxz(){
		
		String sql = "select distinct a.kcxz from cjb a";
		
		try {
			return getArray(sql, new String[]{}, "kcxz");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查询课程性质
	 * @return
	 */
	protected String[] getKhfs(){
		
		String sql = "select distinct a.khfs from cjb a";
		
		try {
			return getArray(sql, new String[]{}, "khfs");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 删除指定项目的条件设置
	 * @param xmdm
	 * @return
	 */
	protected boolean delTjsz(String xmdm){
		
		String sql = "delete from xg_pjpy_tjszb where xmdm=?";
		try {
			return runUpdate(sql, new String[]{xmdm});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 保存条件设置
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	protected boolean saveTjsz(PjpyTjszForm model) throws SQLException{
		
		String[] sqlArr = new String[model.getTjdm().length];
		
		for (int i = 0 ; i < model.getTjdm().length ; i++){
			StringBuilder sql = new StringBuilder();
			
			sql.append(" insert into xg_pjpy_tjszb(xmdm,tjdm,tjfw,gx,tjz,qtxz) values ('")
			   .append(model.getXmdm())
			   .append("','")
			   .append(model.getTjdm()[i])
			   .append("','")
			   .append(model.getTjfw()[i])
			   .append("','")
			   .append(model.getTjgx()[i])
			   .append("','")
			   .append(model.getTjz()[i])
			   .append("','")
			   .append(model.getQtxz()[i])
			   .append("')");
			
			sqlArr[i] = sql.toString();
		}
		
		int[] result = runBatch(sqlArr);
		return checkBatch(result);
	}
	
	
	/**
	 * 修改条件设置
	 * @param model
	 * @return
	 */
	protected boolean updateTjsz(PjpyTjszForm model){
		
		String sql = "update xg_pjpy_tjszb set tjfw=?,gx=?,tjz=?,qtxz=? where xmdm||tjdm||tjfw=?";
		
		try {
			return runUpdate(sql, new String[]{model.getTjfw()[0],model.getTjgx()[0],model.getTjz()[0],model.getQtxz()[0],model.getPkValue()});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
