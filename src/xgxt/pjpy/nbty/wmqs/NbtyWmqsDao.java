package xgxt.pjpy.nbty.wmqs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;

public class NbtyWmqsDao extends CommonQueryDAO{
	
	/**
	 * method getZsInfo
	 * 根据学号获取住宿信息
	 * @param xh
	 * @return
	 */
	public static Map<String, String> getZsInfo(String xh) {
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Base.isNull(xh)) {
			String[] colList = new String[] { "xh", "ssbh", "qsh", "lddm","ldmc",
					"cs","xm"};
			map = commonQueryOne("view_xszsxx", colList, "xh",xh);
		}
		return map;
	}
	
	public static boolean getZsInfo(String lddm,String qsh) {
		DAO dao = DAO.getInstance();
		String ssbh=lddm+'-'+qsh;
		String sql = "select count(*)num from view_xszsxx  where ssbh =?";
		String num = dao.getOneRs(sql, new String[]{ssbh}, new String("num"));
		if(!"0".equalsIgnoreCase(num)){
			return true;
		}else{
			return false;
		}
	}
	
	public static List<Map<String,String>> getStZsInfo(String xh){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map= new HashMap<String,String>();
		String sql = "select xn,ldmc,cs,qsh,bzrsh,fdysh,xysh,xxsh from xszsxxb b,view_nbty_wmqsdjb a where a.ssbh =b.ssbh and b.xh=?";
		List xyList = dao.getList(sql, new String[] {xh}, new String[] {
						"xn", "ldmc","cs","qsh","bzrsh","fdysh","xysh","xxsh" });
		return xyList;
	}
	//根据ssbh获取住宿生寝室的所有学院
	public static List<HashMap<String, String>> getXszsInfo(String lddm,String qsh) {
		DAO dao = DAO.getInstance();
		String ssbh=lddm+"-"+qsh;
		String sql = "select xydm,xymc,zydm,zymc,bjmc,bjdm from view_xszsxx where ssbh =?";
	    List xyList = dao.getList(sql, new String[] {ssbh}, new String[] {
			"xydm", "xymc","zydm","zymc","bjdm","bjmc" });
	    return xyList;
	}
	
	
	//	根据ssbh获取住宿生寝室的所有学院
	public static List<Map<String, String>> getBjByFdy(String userName) {
		DAO dao = DAO.getInstance();
		String sql = "select bjdm from view_fdybbj where zgh =?";
	    List fdyBjList = dao.getList(sql, new String[] {userName}, new String[] {
			"bjdm" });
	    return fdyBjList;
	}
	
	//根据ssbh获取住宿生寝室的所有学院
	public static List<HashMap<String, String>> getBjByBzr(String userName) {
		DAO dao = DAO.getInstance();
		String sql = "select bjdm from view_bzrbbj where zgh =?";
	    List BzrBjList = dao.getList(sql, new String[] {userName}, new String[] {
			"bjdm" });
	    return BzrBjList;
	}
	
	public static List<HashMap<String, String>> getXszsInfo(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select xydm,xymc,zydm,zymc,bjmc,bjdm from view_xszsxx  where ssbh =(select ssbh from view_xszsxx where xh=?)";
	    List xyList = dao.getList(sql, new String[] {xh}, new String[] {
			"xydm", "xymc","zydm","zymc","bjdm","bjmc" });
	    return xyList;
	}
	
	
	/**
	 * method getQslbInfo
	 * 获取楼栋列表
	 * @param xh
	 * @return
	 */
	public static List<HashMap<String, String>> getLdlbInfo() {
		DAO dao = DAO.getInstance();
		String sql = "select lddm dm,ldmc mc from sslddmb";
	    List ldlbList = dao.getList(sql, new String[] {}, new String[] {
			"dm", "mc" });
	    return ldlbList;
	}
	
	/**
	 * method getQslbInfo
	 * 获取寝室列表
	 * @param xh
	 * @return
	 */
	public static List<HashMap<String, String>> getQslbInfo() {
		DAO dao = DAO.getInstance();
		String sql = "select qsh mc,qsh dm from view_ssxx";
	    List qslbList = dao.getList(sql, new String[] {}, new String[] {
			"dm","mc" });
	    return qslbList;
	}
	
	
	/**
	 * 获取单个寝室，入住的学生的学院名称
	 */
	public static  List<HashMap<String, String>> getZsxxInfo(String dldm,String qsh) {
		DAO dao = DAO.getInstance();
		String ssbh=dldm+'-'+qsh;
		String sql = "select xydm,zydm,bjdm,xy xymc,zymc,bjmc  from xsxxb where xh in (select xh from xszsxxb where SSBH =?) ";
	    List zsxxList = dao.getList(sql, new String[] {ssbh}, new String[] {
			"xydm","zydm","bjdm","xymc","zymc","bjmc" });
	   return zsxxList;
	}
	
}
