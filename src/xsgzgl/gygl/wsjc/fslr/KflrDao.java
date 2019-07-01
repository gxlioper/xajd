/**
 * @部门:学工产品事业部
 * @日期：2017-6-1 下午03:36:55 
 */  
package xsgzgl.gygl.wsjc.fslr;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 扣分录入dao(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-6-1 下午03:36:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KflrDao extends SuperDAOImpl<KflrForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KflrForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KflrForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		
	}
	
	/** 
	 * @描述:获取寝室扣分明细(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-1 下午03:39:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getKfmx(KflrForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.kf from (select dh,mk,nr,pf,? jcrcid,? lddm,? qsh from xg_gygl_new_wsjc_pfbzb)a");
		sql.append(" left join (select * from xg_gygl_new_wsjc_qskfb b where b.jcrcid = ? and b.lddm = ? and b.qsh = ?)b");
		sql.append(" on a.jcrcid = b.jcrcid and a.lddm = b.lddm and a.qsh = b.qsh and a.dh = b.kfdh order by to_number(dh) asc");
		return dao.getListNotOut(sql.toString(), new String[]{t.getJcrcid(),t.getLddm(),t.getQsh(),t.getJcrcid(),t.getLddm(),t.getQsh()});
	}
	
	/**
	 * @throws SQLException  
	 * @描述:批量插入(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-1 下午05:01:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plInsert(KflrForm t) throws SQLException{
		boolean flg = true;
		String sql = "insert into xg_gygl_new_wsjc_qskfb values(?,?,?,?,?)";
		String[] str = t.getKfArr();
		List<String[]> list = new ArrayList<String[]>();
		for(String strr : str){
			String[] strrr = strr.split("_");
			list.add(strrr);
		}
		int[] res = dao.runBatch(sql, list);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	
	/** 
	 * @描述:批量删除(这里用一句话描述这个方法的作用)
	 * @作者：liujun[工号：1282]
	 * @日期：2017-6-2 上午10:23:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plSc(KflrForm t) throws SQLException{
		boolean flg = true;
		String sql = "delete from xg_gygl_new_wsjc_qskfb where jcrcid = ? and lddm = ? and qsh = ? and kfdh = ?";
		String[] str = t.getDelArr();
		List<String[]> list = new ArrayList<String[]>();
		for(String strr : str){
			String[] strrr = strr.split("_");
			list.add(strrr);
		}
		int[] res = dao.runBatch(sql, list);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	
	/**
	 * @throws SQLException  
	 * @描述:删除扣分明细(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-5 上午11:19:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @return
	 * List<String> 返回类型 
	 * @throws 
	 */
	public boolean scKfmx(String[] jcrcid) throws SQLException {
		String sql = "delete from xg_gygl_new_wsjc_qskfb where jcrcid = ?";
		List<String[]> list = new ArrayList<String[]>();
		for(String str : jcrcid){
			list.add(new String[]{str});
		}
		return dao.runBatchNotCommit(sql, list);
	}
	
	/**
	 * @throws SQLException  
	 * @描述:删除分数录入(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-8 上午10:09:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jcrcid
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plScFslr(String[] jcrcid) throws SQLException {
		String sql = "delete from xg_gygl_new_wsjc_qsfsb where jcrcid = ?";
		List<String[]> list = new ArrayList<String[]>();
		for(String str : jcrcid){
			list.add(new String[]{str});
		}
		return dao.runBatchNotCommit(sql, list);
	}
	
	/** 
	 * @描述:批量删除检查日程(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-8 上午10:38:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jcrcid
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plScJcrc(String[] jcrcid) throws SQLException {
		String sql = "delete from xg_gygl_new_wsjc_jcrcb where guid = ?";
		List<String[]> list = new ArrayList<String[]>();
		for(String str : jcrcid){
			list.add(new String[]{str});
		}
		return dao.runBatchNotCommit(sql, list);
	}

	
	/**
	 * @throws SQLException  
	 * @描述:批量更新扣分依据(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-5 下午01:46:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plUpdateKfyj(String[] str) throws SQLException{
		boolean flg = true;
		String sql = "update xg_gygl_new_wsjc_qsfsb set kfyj = ? where guid = ? and lddm = ? and qsh = ?";
		List<String[]> params = new ArrayList<String[]>();
		for(String strr : str){
			if(null == strr){
				continue;
			}
			String[] strrr = strr.split("_");
			params.add(strrr);
		}
		if(params.size()<1){
			return true;
		}
		int[] res = dao.runBatch(sql, params);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	
	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-5 下午05:01:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public void scFslr(KflrForm t) throws Exception{
		String sql = "delete from xg_gygl_new_wsjc_qsfsb where guid = ? and lddm = ? and qsh = ?";
		dao.runDelete(sql, new String[]{t.getJcrcid(),t.getLddm(),t.getQsh()});
	}
	
	/** 
	 * @描述:是否为保存分(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-6 上午10:11:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isBcfs(KflrForm t){
		String sql = "select count(1) num from xg_gygl_new_wsjc_qsfsb where guid = ? and lddm = ? and qsh = ?";
		String num = dao.getOneRs(sql, new String[]{t.getJcrcid(),t.getLddm(),t.getQsh()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	/**
	 * @throws SQLException  
	 * @描述:批量删除分数扣分(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-19 下午07:18:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws 
	 */
	public void plScFskf(List<String[]> list) throws SQLException{		
		String sql = "delete from xg_gygl_new_wsjc_qskfb where jcrcid = ? and lddm = ? and qsh = ?";
		dao.runBatch(sql, list);
	}

}
