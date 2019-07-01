
package xgxt.pjpy.commonutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 评奖评优通用工具类</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-10</p>
 */
public class PjpyCommonDAO {

	DAO  dao = null;//数据操作DAO
	
	/**
	 * 获取奖学金列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList() throws Exception {
		dao = DAO.getInstance();
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		String sql = "select jxjdm,jxjmc from jxjdmb";
		jxjList = dao.getList(sql, new String[]{}, new String[]{"jxjdm", "jxjmc"});
		return jxjList;
	}
	
	/**
	 * 通过学号获取学生相关信息(姓名，性别，年龄，学院，专业，班级等)
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = DAO.getInstance();
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.zzmmmc,a.jg,a.csrq,a.xydm,a.zydm,a.bjdm,round(months_between(to_date(to_char(sysdate, 'yyyymmdd'),'yyyy-mm-dd'),to_date(a.csrq, 'yyyy-mm-dd'))/12) nl,(select b.wysp from xsfzxxb b where a.xh=b.xh) wysp,(select c.sjhm from xsfzxxb c where a.xh=c.xh) sjhm from view_xsxxb a where xh=? and rownum=1";
		resMap = dao.getMapNotOut(sql, new String[]{xh});
		return resMap;
	}
	
	/**
	 * 通过传入中，英文获取查询表头
	 * @param enList
	 * @param cnList
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTableQryTitle(String[] enList, String[] cnList) throws Exception{
		if (enList != null && cnList != null) {
			return dao.arrayToList(enList, cnList); 
		} 
		return null;
	}
}
