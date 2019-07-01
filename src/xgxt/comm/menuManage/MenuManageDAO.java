package xgxt.comm.menuManage;

import java.sql.SQLException;
import java.util.*;

import xgxt.DAO.DAO;

public class MenuManageDAO extends DAO {

	
	/**
	 * 获取全部一级菜单
	 * @return List<HashMap<String, String>>
	 */
	protected List<HashMap<String, String>> getTipGnmkList(){
		
		String sql = "select gnmkdm,gnmkmc from gnmkdmb where gnmkdm like 'N__'order by to_number(xssx)";
		
		return getList(sql, new String[] {}, new String[] {"gnmkdm","gnmkmc"});
	}
	
	
	/**
	 * 返回指定级别功能模块
	 * @return
	 */
	protected List<HashMap<String, String>> getGnmkList(String cdjb) {

		String sql = "select * from xg_view_gnmkdm where sfqy='是' and cdjb=? order by to_number(xssx)";

		return getList(sql, new String[] { cdjb }, new String[] { "gnmkdm",
				"gnmkmc", "yjgnmkdm", "ejgnmkdm", "ejgnmkmc", "yjgnmkmc" });
	}
	
	
	/**
	 * 根据父菜单获取子菜单列表
	 * 供DWR调用
	 * @param gnmkdm
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getChildGnmkList(String gnmkdm){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select gnmkdm,gnmkmc from gnmkdmb where gnmkdm like '");
		sb.append(gnmkdm);
		sb.append("__'");
		return getList(sb.toString(), new String[] {}, new String[] {"gnmkdm","gnmkmc"});
	}
	
	
	/**
	 * 根据父菜单获取子菜单列表
	 * 供DWR调用
	 * @param gnmkdm
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getChildNode(String gnmkdm,String sfqy){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select gnmkdm,gnmkmc from gnmkdmb where gnmkdm like '");
		sb.append(gnmkdm);
		sb.append("__' and sfqy=?");
		return getList(sb.toString(), new String[] {sfqy}, new String[] {"gnmkdm","gnmkmc"});
	}
	
	
	
	/**
	 * 批量修改是否启用菜单
	 * @param pkValue
	 * @param sfqy
	 * @return
	 * @throws SQLException
	 */
	protected boolean batchUpdateSfqy(String[] pkValue,String[] sfqy) throws SQLException {
		
		String[] sqlArr = new String[pkValue.length] ;
		
		for (int i = 0 ; i < pkValue.length ; i++) {
			StringBuilder sql = new StringBuilder();
			
			sql.append("update gnmkdmb set sfqy='");
			sql.append(sfqy[i]);
			sql.append("' where gnmkdm='");
			sql.append(pkValue[i]);
			sql.append("'");
			sqlArr[i] = sql.toString();
		}
		
		int[] result = runBatch(sqlArr);
		return checkBatch(result);
	}
	
	
	
	/**
	 * 获取全部快捷方式图片
	 * @return
	 * @throws SQLException 
	 */
	protected List<String> getShortcutPic() throws SQLException{
	
		String sql = "select picpath from xtwh_kjfs_picb";
		
		return getList(sql, new String[] {}, "picpath");
	}
	
	
	
	/**
	 * 修改快捷方式图片相应内容
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected boolean updateShortcutPic(MenuManageForm model) throws Exception {
		
		String sql = "update xtwh_kjfs_picb set gnmk=?,mkms=?,bz=? where picpath=?";
		
		return runUpdate(sql, new String[] {model.getDyym(),model.getSave_gnmkmc(),model.getSave_gnbz(),model.getPath()});
		
	}
	
	
	/**
	 * 修改某张快捷方式图片对应的访问路径
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected boolean updateRepeatPath(MenuManageForm model) throws Exception {
		String sql = "update xtwh_kjfs_picb set gnmk='' where gnmk=?";
		
		return runUpdate(sql, new String[] {model.getDyym()});
	}
	
	
	/**
	 * 根据访问路径查找功能按钮
	 * @param path
	 * @return
	 */
	protected List<HashMap<String,String>> getBtnList(String path){
		String sql = "select * from (select a.*,b.btnid,b.btnmc from xg_xtwh_cdgnb a left join xg_xtwh_btndmb b on a.btndm=b.btndm) where path=?";
	
		return getList(sql, new String[] {path}, new String[] {"btndm","btnmc","btnid","sfqy"});
	}
	
	
	/**
	 * 修改某个功能按钮 是否启用
	 * @param path
	 * @param btndm
	 * @param sfqy
	 * @return
	 * @throws Exception
	 */
	public boolean changeBtnSfqy(String path,String btndm,String sfqy) throws Exception {
		
		String sql = "update xg_xtwh_cdgnb set sfqy=? where path=? and btndm=?";
		
		return runUpdate(sql, new String[] {sfqy,path,btndm});
	}
	
	
	/**
	 * 更换父菜单，返回自己新菜单下的功能模块代码
	 * DWR调用 
	 * @param ejgnmkdm
	 * @return
	 */
	public String getNextGnmkdm(String ejgnmkdm) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select case when length(next)=5 then 'N0'||next else 'N'||next end gnmkdm from(");
		sql.append("select max(replace(gnmkdm,'N',''))+1 next from gnmkdmb where gnmkdm like '");
		sql.append(ejgnmkdm);
		sql.append("__')");
		
		String gnmkdm = getOneRs(sql.toString(), new String[] {}, "gnmkdm");
		
		return gnmkdm;
	}
	
	
	/**
	 * 添加新的功能模块到用户权限表
	 * @param gnmkdm
	 * @return
	 */
	public boolean saveYhqx(String gnmkdm) {
		
		String sql = "insert into yhqxb values ('zf01',?,'1')";
		try {
			return runUpdate(sql, new String[] {gnmkdm});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 检测某功能模块在用户权限表是否存在
	 * @param gnmkdm
	 * @return
	 */
	public String checkYhqxExists(String gnmkdm) {
		String sql = "select count(*) count from yhqxb where yhm='zf01' and gnmkdm=?";
		
		return getOneRs(sql, new String[] {gnmkdm}, "count");
	}
	
	
	/**
	 * 根据功能模块代码获取相关信息
	 * @param gnmkdm
	 * @return
	 */
	public HashMap<String, String> getGnmkInfo(String gnmkdm) {

		String sql = "select * from xg_view_gnmkdm where gnmkdm = ?";

		return getMapNotOut(sql, new String[] {gnmkdm});
	}
}
