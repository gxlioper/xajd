package xsgzgl.xtwh.jhzy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
/**
 * 金华职业系统维护
 * @author yeyipin
 */
public class XtwhDAO {
	DAO dao = DAO.getInstance();
	/**
	 * 用户组列表String
	 * @return
	 */
	public String getGroupToSplit() {
		String sql = "select distinct zdm,zmc from yhzb where sfxs='1' and zdm<>'6727' order by zdm";
		String[] inputValue = {};
		String[] outputValue = { "zdm", "zmc" };
		return dao.getStringToSplit(sql, inputValue, outputValue);
	}
	/**
	 * 用户组列表List
	 * @return
	 */
	public List<HashMap<String,String>> getGroupList(){
		String sql = "select distinct zdm,zmc from yhzb where sfxs='1' and zdm<>'6727' order by zdm";
		String[] inputValue = {};
		String[] outputValue = { "zdm", "zmc" };
		return dao.getList(sql, inputValue, outputValue);
	}
	/**
	 * 部门列表String
	 * @return
	 */
	public String getPartToSplit() {
		String sql = "select distinct bmdm,bmmc from ZXBZ_XXBMDM order by bmdm";
		String[]inputValue = {};
		String[]outputValue = {"bmdm","bmmc"};
		return dao.getStringToSplit(sql, inputValue, outputValue);
	}
	/**
	 * 单位列表String
	 * @return
	 */
	public String getUnitToSplit() {
		String sql = "select distinct dwdm,dwmc from bks_dwdmb order by dwdm";
		String[]inputValue = {};
		String[]outputValue = {"dwdm","dwmc"};
		return dao.getStringToSplit(sql, inputValue, outputValue);
	}
	/**
	 * 获得当前用户下的所有功能代码模块String
	 * @param username
	 * @return
	 */
	public String getPowerToSplit(String username) {
		String sql = "select gnmkdm,gnmkmc from view_yhqx where yhm=? order by gnmkdm";
		String[]inputValue = {username};
		String[]outputValue = {"gnmkdm", "gnmkmc" };
		return dao.getStringToSplit(sql, inputValue, outputValue);
	}
	/**
	 * 获得辅导员信息List
	 * @return
	 */
	public  List<HashMap<String, String>> getFdyxxList(){
		String sql = " select a.zgh yhm,(a.zgh||'/'||nvl(a.xm,'-')||'/'||nvl(d.zmc,'-')) xm " +
				"from (select a.zgh,a.xm,c.yhzdm from(select a.zgh,b.xm from (select distinct zgh from fdybjb) a " +
				"left join fdyxxb b on a.zgh = b.zgh) a left join xg_xtwh_yhsszb c on (a.zgh||'_fdy') = c.yhm) a  " +
				"left join yhzb d on a.yhzdm = d.zdm order by yhm";
		String[] inputValue = {};
		String[] outputValue = { "yhm", "xm" };
		return dao.getList(sql, inputValue, outputValue);
	}
	/**
	 * 获得班主任信息List
	 * @return
	 */
	public  List<HashMap<String, String>> getBzrxxList(){
		String sql = " select a.zgh yhm,(a.zgh||'/'||nvl(a.xm,'-')||'/'||nvl(d.zmc,'-')) xm " +
				"from (select a.zgh,a.xm,c.yhzdm from(select a.zgh,b.xm from (select distinct zgh from bzrbbb) a " +
				"left join fdyxxb b on a.zgh = b.zgh) a left join xg_xtwh_yhsszb c on (a.zgh||'_bzr') = c.yhm) a  " +
				"left join yhzb d on a.yhzdm = d.zdm order by yhm";
		String[] inputValue = {};
		String[] outputValue = { "yhm", "xm" };
		return dao.getList(sql, inputValue, outputValue);
	}
	/**
	 * 用户所拥有的用户模块
	 * @param userID
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getPowerList(String userID,
			String userName,String type) {
		String table = "fdybjb";
		if("fdy".equalsIgnoreCase(type)){
			table = "fdybjb";
		}else if("bzr".equalsIgnoreCase(type)){
			table = "bzrbbb";
		}
		String sql;
		String[] inputValue;
		if (userID == null || "".equalsIgnoreCase(userID)) {
			sql = "select (nvl(dxq,'0')||gnmkdm) gnmkdm,(rpad(gnmkdm,7,'_')||' | '||replace(replace(nvl(dxq,'0'),'0','只读'),'1','可写')||' | '||gnmkmc) gnmkmc from view_yhqx where yhm=(select min(zgh)||'_"+type+"' from "+table+") and substr(gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?) order by gnmkdm";
			inputValue = new String[] { userName };
		} else {
			sql = "select (nvl(dxq,'0')||gnmkdm) gnmkdm,(rpad(gnmkdm,7,'_')||' | '||replace(replace(nvl(dxq,'0'),'0','只读'),'1','可写')||' | '||gnmkmc) gnmkmc from view_yhqx where yhm=? and substr(gnmkdm,1) in(select gnmkdm from yhqxb where yhm=?) order by gnmkdm";
			inputValue = new String[] { userID, userName };
		}
		String[] outputValue = { "gnmkdm", "gnmkmc" };
		return dao.getList(sql, inputValue, outputValue);
	}
	/**
	 * 保存辅导员所属组
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveFdyssz(HttpServletRequest request) throws Exception {
		String yhm = request.getParameter("yhm");
		String yhzdm = request.getParameter("newUserGroup");
		boolean flag = false;
		if(yhm!=null&&!"".equalsIgnoreCase(yhm)&&yhzdm!=null&&!"".equalsIgnoreCase(yhzdm)){
			//用户名加后缀
			yhm+="_fdy";
			String[] input = {yhm,yhzdm};
			String num = dao.getOneRs("select count(1) num from xg_xtwh_yhsszb where yhm=? and yhzdm=? ", input, "num");
			flag = dao.runUpdate("delete from xg_xtwh_yhsszb where yhm = ? ", new String[]{yhm});
			if(flag){
				flag = dao.runUpdate("insert into xg_xtwh_yhsszb (yhm,yhzdm) values(?,?) ", input);
				if (Integer.parseInt(num) == 0) {
					// 删除用户所有权限
					String sql = "delete from yhqxb where yhm=?";
					flag = dao.runUpdate(sql, new String[]{yhm});
					// 添加用户新权限
					if(flag){
					sql = "insert into yhqxb (select ?,gnmkdm,dxq from yhzqxb where zdm=?)";
					flag = dao.runUpdate(sql, input);
					}
				}
			}
		}
		return flag;
	}
	/**
	 * 保存辅导员权限
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveFdyqx(HttpServletRequest request) throws Exception {
		String yhm = request.getParameter("yhm");
		String[] userPower = request.getParameter("power").split(",");
		boolean flag = false;
		if(yhm!=null&&!"".equalsIgnoreCase(yhm)&&userPower!=null){
			//用户名加后缀
			yhm+="_fdy";
			int i = userPower.length;
			String[][] groupPowerRW = new String[i][2];
			for (int j = 0; j < i; j++) {
				groupPowerRW[j] = userPower[j].split(":");
			}
			flag = dao.runUpdate("delete from yhqxb where yhm=? ", new String[]{yhm});
			if(flag){
				String sql = " insert into yhqxb(yhm,gnmkdm,dxq) values(?,?,?) ";
				for (i = 0; i < groupPowerRW.length; i++) {
					String tmp[] = new String[] { yhm, groupPowerRW[i][1],
							groupPowerRW[i][0] };
					flag = dao.runUpdate(sql, tmp);
				}
			}
		}
		return flag;
	}
	/**
	 * 保存班主任所属组
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveBzrssz(HttpServletRequest request) throws Exception {
		String yhm = request.getParameter("yhm");
		String yhzdm = request.getParameter("newUserGroup");
		boolean flag = false;
		if(yhm!=null&&!"".equalsIgnoreCase(yhm)&&yhzdm!=null&&!"".equalsIgnoreCase(yhzdm)){
			//用户名加后缀
			yhm+="_bzr";
			String[] input = {yhm,yhzdm};
			String num = dao.getOneRs("select count(1) num from xg_xtwh_yhsszb where yhm=? and yhzdm=? ", input, "num");
			flag = dao.runUpdate("delete from xg_xtwh_yhsszb where yhm = ?", new String[]{yhm});
			if(flag){
				flag = dao.runUpdate("insert into xg_xtwh_yhsszb (yhm,yhzdm) values(?,?) ", input);
				if (Integer.parseInt(num) == 0) {
					// 删除用户所有权限
					String sql = "delete from yhqxb where yhm=?";
					flag = dao.runUpdate(sql, new String[]{yhm});
					// 添加用户新权限
					if(flag){
					sql = "insert into yhqxb (select ?,gnmkdm,dxq from yhzqxb where zdm=?)";
					flag = dao.runUpdate(sql, input);
					}
				}
			}
		}
		return flag;
	}
	/**
	 * 保存班主任权限
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveBzrqx(HttpServletRequest request) throws Exception {
		String yhm = request.getParameter("yhm");
		String[] userPower = request.getParameter("power").split(",");
		boolean flag = false;
		if(yhm!=null&&!"".equalsIgnoreCase(yhm)&&userPower!=null){
			//用户名加后缀
			yhm+="_bzr";
			int i = userPower.length;
			String[][] groupPowerRW = new String[i][2];
			for (int j = 0; j < i; j++) {
				groupPowerRW[j] = userPower[j].split(":");
			}
			flag = dao.runUpdate("delete from yhqxb where yhm=? ", new String[]{yhm});
			if(flag){
				String sql = " insert into yhqxb(yhm,gnmkdm,dxq) values(?,?,?) ";
				for (i = 0; i < groupPowerRW.length; i++) {
					String tmp[] = new String[] { yhm, groupPowerRW[i][1],
							groupPowerRW[i][0] };
					flag = dao.runUpdate(sql, tmp);
				}
			}
		}
		return flag;
	}
}
