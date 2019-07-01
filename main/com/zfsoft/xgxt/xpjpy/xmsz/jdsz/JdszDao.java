/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:11:11
 */
package com.zfsoft.xgxt.xpjpy.xmsz.jdsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 项目维护-兼得设置
 * @作者： ligl
 * @日期：2013-8-5 上午11:11:11
 * @版本： V1.0
 * @修改记录:
 */
public class JdszDao extends SuperDAOImpl<JdszModel> {

	/**
	 * 
	 * @描述:普通查询方法
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(JdszModel model)
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
	public List<HashMap<String, String>> getPageList(JdszModel model,
			User user) throws Exception {
		return null;
	}
	/**
	 * 
	 * @描述:获取星级登记
	 * @作者：caopei[工号：1352]
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllXj() {
		String sql = "select dj from xg_gygl_new_wsjc_djfsb where lx='1' order by dj ";
		return dao.getListNotOut(sql.toString(), null);
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
		sql.append("select a.*,c.xmdm fzbjdxmdm from xg_pjpy_new_jdszb a  left join xg_pjpy_new_pjxmb b");
		sql.append(" on a.bjdxmdm=b.xmdm left join(select * from xg_pjpy_new_pjxmb where xn=? and xq=?)c on b.xmmc=c.xmmc where a.xmdm=? ");
		String[] input = {currXn,currXq,xmdm};
		List<HashMap<String, String>> result = dao.getListNotOut(sql.toString(), input);
		return result;
	}
	
	/**
	 * 
	 * @描述:根据xmdm返回不可兼得项目
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjdxm(String xmdm) throws Exception{
		if(StringUtil.isNull(xmdm)){
			throw new SystemException("查询参数为空！");
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.xmdm,t2.xmmc,t2.xmje from (");
		sql.append(" select bjdxmdm xmdm from xg_pjpy_new_jdszb where xmdm=?");
		sql.append(" union ");
		sql.append(" select xmdm from xg_pjpy_new_jdszb where bjdxmdm=?");
		sql.append(" ) t1 left join xg_pjpy_new_pjxmb t2 on t1.xmdm=t2.xmdm");
		
		return dao.getListNotOut(sql.toString(), new String[]{xmdm,xmdm});
	}	
	
	/**
	 * 
	 * @描述:兼得设置
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
	public boolean runJdsz(String xmdm,String xmdms) throws Exception {
		int[] result = null;

		String sql = null;
		List<String> sqlList = new ArrayList<String>();
		sql = "delete from xg_pjpy_new_jdszb where xmdm='"+xmdm+"'";
		sqlList.add(sql);
		sql = "delete from xg_pjpy_new_jdszb where bjdxmdm='"+xmdm+"'";
		sqlList.add(sql);		
		if(xmdms != null && !xmdms.trim().equals("")){
			String[] arr = xmdms.split(",");
			if(arr != null){
				for (int i = 0; i < arr.length; i++) {
					sql = "insert into xg_pjpy_new_jdszb(xmdm,bjdxmdm) values('"+xmdm+"','"+arr[i]+"')";
					sqlList.add(sql);
					sql = "insert into xg_pjpy_new_jdszb(xmdm,bjdxmdm) values('"+arr[i]+"','"+xmdm+"')";
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
		super.setTableName("xg_pjpy_new_jdszb");
		super.setKey("xmdm");
	}

	/** 
	 * @描述:根据项目代码返回不可兼得已申请项目
	 * @作者：cq [工号：785]
	 * @日期：2014-3-3 上午10:37:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJdysq(User user, String xmdm) throws Exception{
		
		if(StringUtil.isNull(xmdm)){
			throw new SystemException("查询参数为空！");
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select b.xmmc from xg_pjpy_new_jdszb a ");
		sql.append(" left join xg_pjpy_new_pjxmb b on a.bjdxmdm = b.xmdm ");
		sql.append(" where exists (select 1 from xg_pjpy_new_xmsq c where a.bjdxmdm = c.xmdm and c.xh = ?) ");
		sql.append(" and a.xmdm = ? ");
		
		return dao.getListNotOut(sql.toString(), new String[]{user.getUserName(),xmdm});
	}





}
