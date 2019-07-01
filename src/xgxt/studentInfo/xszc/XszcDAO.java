package xgxt.studentInfo.xszc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class XszcDAO {
	
	DAO dao = DAO.getInstance();
	
	/**
	 * 获取结果集
	 * @param sql 查询语句
	 * @param in 输入数组
	 * @param out 输出数组
	 * @return
	 */
	public List<String[]> getRs(String sql,String[] in,String[] out){
		return dao.rsToVator(sql, in, out);
	}
	
	/**
	 * 获取查询结果表头信息
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(){
		String[] en = new String[]{"xn","xq","zczt","fdysh"};
		String[] cn = new String[]{"学年","学期","注册状态","审核状态"};
		return dao.arrayToList(en,cn);
	}
	
	/**
	 * 批量执行语句
	 * @param sqlArr
	 * @return
	 * @throws SQLException
	 */
	public boolean zc(String[] sqlArr) throws SQLException{
		return dao.checkBatch(dao.runBatch(sqlArr));
	}
	
	/**
	 * 根据主键或者学生注册信息
	 * @param pk 学年+学期+学号
	 * @return
	 */
	public HashMap<String, String> getXszcInfo(String pk){
		return dao.getMapNotOut("select a.*,a.xn||a.xq||a.xh pk from xg_view_xsxx_zcqkb a where xn||xq||xh=?", new String[]{pk});
	}
	
	/**
	 * 根据主键获取学生已经入库的注册信息
	 * @param pk 学年+学期+学号
	 * @return
	 */
	public String getCont(String pk){
		return dao.getOneRs("select count(1) cont from xg_xsxx_zcqkb where xn||xq||xh=?", new String[]{pk}, "cont");
	}
	
	/**
	 * 删除学生注册信息
	 * @param pk 学年+学期+学号
	 * @return
	 * @throws Exception
	 */
	public boolean del(String pk) throws Exception{
		return dao.runUpdate("delete from xg_xsxx_zcqkb where xn||xq||xh=?", new String[]{pk});
	}
	
	/**
	 * 下拉列表选项维护
	 * 
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		String[] dm = null;
		String[] mc = null;
		
		if ("shzt".equalsIgnoreCase(lx)) {
			dm = new String[] { "未审核", "通过","不通过" };
			mc = new String[] { "未审核", "通过" ,"不通过"};
		} else if("zczt".equalsIgnoreCase(lx)){
			dm = new String[] { "已注册", "未注册","暂缓注册" };
			mc = new String[] { "已注册", "未注册","暂缓注册" };
		}
		return dao.arrayToList(dm, mc);
	}
	

	/**
	 * 学期列表(武汉商业服务学院)
	 * @return
	 */
	public List<HashMap<String, String>> getXq() {
//		String sql = "select xqdm,xqmc from xqdzb where xqjb<3";
//		return dao.getList(sql, new String[] {}, new String[] {"xqdm","xqmc"});
		String[] dm = new String[]{"上学期","下学期"};
		String[] mc = new String[]{"上学期","下学期"};
		return dao.arrayToList(dm, mc);
	}
}
