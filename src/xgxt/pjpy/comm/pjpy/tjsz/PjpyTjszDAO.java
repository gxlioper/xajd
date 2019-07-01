package xgxt.pjpy.comm.pjpy.tjsz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class PjpyTjszDAO extends DAO {

	
	/**
	 * ����������Ŀ�����ѯ����������
	 * @param xmdm
	 * @return
	 */
	protected List<HashMap<String,String>> getXmtj(String xmdm){
		
		String sql = "select a.tjdm,(select b.tjmc from xg_pjpy_pjtjkb b where b.tjdm||b.tjms=a.tjdm) tjmc,tjfw,gx,tjz,qtxz from xg_pjpy_tjszb a where xmdm=?";
		
		return getListNotOut(sql, new String[]{xmdm});
	}
	
	/**
	 * ��ѯ��������ȫ������
	 * 
	 * @return
	 */
	protected List<HashMap<String, String>> getTjk() {

		// ============2011.07.21 edit by ³��=======================

		String sql = "select a.tjdm||tjms tjdm,a.tjmc from xg_pjpy_pjtjkb a where sfqy='yes'";

		List<HashMap<String, String>> list = getList(sql, new String[] {},
				new String[] { "tjdm", "tjmc" });

		return list;
	}
	
	/**
	 * ��ѯ��������ȫ������
	 * @return
	 */
	protected List<HashMap<String,String>> getTjk(String xmdm){
		
		// ============2011.07.21 edit by ³��=======================
		String sql = "select a.tjdm||tjms tjdm,a.tjmc from xg_pjpy_pjtjkb a "
				+ "where sfqy='yes' and not exists (select 1 from xg_pjpy_tjszb b where a.tjdm||a.tjms=b.tjdm and b.xmdm=?) ";
		
		return getListNotOut(sql, new String[]{xmdm});
	}
	
	
	/**
	 * ��ѯָ��������Ŀ��ָ��������Ϣ
	 * @param xmdm
	 * @param tjdm
	 * @return
	 */
	protected List<HashMap<String,String>> getXmtjInfo(String xmdm,String[] tjdm){
		
		// ============2011.07.21 edit by ³��=======================
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
		
		// ============2011.07.21 edit by ³��=======================
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
	 * ��ѯ����������
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
	 * ��ѯ�γ�����
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
	 * ��ѯ�γ�����
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
	 * ɾ��ָ����Ŀ����������
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
	 * ������������
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
	 * �޸���������
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
