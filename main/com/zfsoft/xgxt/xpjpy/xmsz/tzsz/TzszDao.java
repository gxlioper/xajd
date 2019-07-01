/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:11:11
 */
package com.zfsoft.xgxt.xpjpy.xmsz.tzsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 项目维护-审核调整奖项设置
 * @作者： ligl
 * @日期：2013-8-5 上午11:11:11
 * @版本： V1.0
 * @修改记录:
 */
public class TzszDao extends SuperDAOImpl<TzszModel> {

	/**
	 * 
	 * @描述:普通查询方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(TzszModel model)
			throws Exception {
		return null;
	}

	/**
	 * 
	 * @描述:高级查询方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(TzszModel model,
			User user) throws Exception {
		return null;
	}
	
	/**
	 * 
	 * @描述:根据xmdm查询设置的记录
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getByXmdm(String xmdm,String currXn, String currXq) throws Exception{
		if(xmdm == null || xmdm.equals("")){
			throw new SystemException("查询参数为空！");
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,c.xmdm fztzjxdm from xg_pjpy_new_jxtzsz a  left join xg_pjpy_new_pjxmb b");
		sql.append(" on a.tzjxdm=b.xmdm left join(select * from xg_pjpy_new_pjxmb where xn=? and xq=?)c on b.xmmc=c.xmmc where a.xmdm=? ");
		String[] input = {currXn,currXq,xmdm};
		List<HashMap<String, String>> result = dao.getListNotOut(sql.toString(), input);
		return result;
	}	
	
	/**
	 * 
	 * @描述:根据xmdm返回项目代码、名称
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKshxm(String xmdm) throws Exception{
		if(xmdm == null || xmdm.equals("")){
			throw new SystemException("查询参数为空！");
		}
		String sql = " select a.tzjxdm dm,b.xmmc mc,b.sqxn sqxn,b.sqxq sqxq  from  xg_pjpy_new_jxtzsz a,xg_pjpy_new_pjxmb b" ;
		sql += 	" where a.xmdm=? and a.tzjxdm=b.xmdm ";
		String[] input = {xmdm};
		List<HashMap<String, String>> result = dao.getListNotOut(sql, input);
		return result;
	}
	
	/**
	 * 
	 * @描述:设置
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: 
	 * @param model
	 * @param key
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean runShsz(String xmdm,String xmdms) throws Exception {
		int[] result = null;
		
		String sql = null;
		List<String> sqlList = new ArrayList<String>();
		sql = "delete from xg_pjpy_new_jxtzsz where xmdm='"+xmdm+"'";
		sqlList.add(sql);
		
		if(xmdms != null && !xmdms.trim().equals("")){
			String[] arr = xmdms.split(",");
			if(arr != null){
				for (int i = 0; i < arr.length; i++) {
					sql = "insert into xg_pjpy_new_jxtzsz(xmdm,tzjxdm) values('"+xmdm+"','"+arr[i]+"')";
					sqlList.add(sql);
				}
			}
		}
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}
	
	protected void setTableInfo() {
		super.setTableName("xg_pjpy_new_jxtzsz");
		super.setKey("xmdm");
	}

	
	/**
	 * 
	 * @描述: 按项目代码查询可调整的评奖项目
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-5 下午02:23:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getKtzPjxmList(String xmdm){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.tzjxdm xmdm,t2.xmmc from xg_pjpy_new_jxtzsz t1");
		sql.append(" left join xg_pjpy_new_pjxmb t2 on t1.tzjxdm=t2.xmdm");
		sql.append(" where t1.xmdm=?");
		sql.append(" and t2.sfqy = '1' and t2.shkg = '1' ");
		sql.append(" and (sysdate between to_date(nvl(t2.shkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t2.shjssj, '2020-01-01 00:00'), 'yyyy-mm-dd hh24:mi')) ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}

	/**
	 * @throws Exception  
	 * @描述:根据项目代码删除记录
	 * @作者：cq [工号：785]
	 * @日期：2013-12-23 下午05:25:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean runDeleteByXmdm(String xmdm) throws Exception {
		
			String sql = "delete from xg_pjpy_new_jxtzsz where xmdm=? ";
			String[] inputValue = { xmdm };
			int result = dao.runDelete(sql, inputValue);
			return result > 0;
	}
}
