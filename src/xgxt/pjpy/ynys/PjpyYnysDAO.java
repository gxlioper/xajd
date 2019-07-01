
package xgxt.pjpy.ynys;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 云南艺术评奖评优DAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-17</p>
 */
public class PjpyYnysDAO {

	protected DAO dao = null;//低层数据操作
	
	/**
	 * 奖学金申请时间
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjsqsj() throws Exception {
		dao = DAO.getInstance();
		return dao.getMapNotOut("select jxjsqxn,jxjsqxq,jxjsqnd from xtszb",
				new String[] {});
	} 
	
	/**
	 * 查询表头
	 * @param enList
	 * @param cnList目 传入二个数组,英文在前,中文在后
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQryTitle(String[] enList,
			String[] cnList) throws Exception {
		dao = DAO.getInstance();
		return dao.arrayToList(enList, cnList);
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = DAO.getInstance();
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,(select sjhm from xsfzxxb " +
						"b where b.xh=view_xsjbxx.xh) sjhm,(select wysp from xsfzxxb b " +
						"where b.xh=view_xsjbxx.xh) wysp from view_xsjbxx where xh=?",
						new String[] { xh });
	}
	
	public List<String[]> getCjList(String xh) throws Exception {
		dao = DAO.getInstance();
		String xn = Base.getJxjsqxn();
		return dao.rsToVator("select kcmc,cj,kclx,(case when sfbxk='0' then '是' else '否' end) sfbxk from view_kxwhszfb where xh=? and xn=?", new String[]{xh, xn}, new String[]{"kcmc", "cj", "kclx", "sfbxk"});
	}
	
	public HashMap<String, String> getCjpm(String xh) throws Exception {
		dao = DAO.getInstance();
		String xn = Base.getJxjsqxn();
		return dao
				.getMapNotOut(
						"select (select distinct trunc(avg(cj) over (partition by xh),2) whkpjf from view_kxwhszfb a where kclx='文化课' and xn=? and xh=?) whkpjf,(select distinct trunc(avg(cj) over (partition by xh),2) zykpjf from view_kxwhszfb a where kclx='专业课' and xn=? and xh=?) zykpjf from dual",
						new String[] {xn, xh, xn, xh});
	}
	
	public HashMap<String, String> getZhfpm(String xh) throws Exception {
		dao = DAO.getInstance();
		String xn = Base.getJxjsqxn();
		return dao
				.getMapNotOut(
						"select zf,pm from (select xh,nj,zydm,zhszcpzf zf,(dense_rank() over (partition by nj,zydm order by to_number(trim(zhszcpzf)) desc nulls last)) pm from view_ynys_zhszcp where xn=?) where xh=?",
						new String[] {xn, xh});
	}
}
