package xsgzgl.gygl.gyccgl.dmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class GyccDmwhDao extends SuperDAOImpl<GyccDmwhForm> {
	
	/**
	 * 
	 * @描述: 物品财产查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 上午11:46:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @return
	 * @throws Exception
	 * GyccDmwhForm 返回类型 
	 * @throws
	 */
	@Override
	public List<HashMap<String, String>> getPageList(GyccDmwhForm t)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_gygl_new_ssccgl_wpwhb t where 1=1");
		sql.append(" ");
		// TODO 自动生成方法存根
		return getPageList(t, sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述: 顺坏程度物品查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 上午11:46:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @return
	 * @throws Exception
	 * GyccDmwhForm 返回类型 
	 * @throws
	 */
	@Override
	public List<HashMap<String, String>> getPageList(GyccDmwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_gygl_new_ssccgl_shcdwhb t where 1=1");
		sql.append(" ");
		// TODO 自动生成方法存根
		return getPageList(t, sql.toString(), new String[]{});
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(GyccDmwhForm.class);
		this.setKey("dm");
		this.setTableName("xg_gygl_new_ssccgl_wpwhb");
	}
	
	/**
	 * 
	 * @描述: 损坏程度获取model
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 上午11:46:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @return
	 * @throws Exception
	 * GyccDmwhForm 返回类型 
	 * @throws
	 */
	public GyccDmwhForm getBscdForm(String key) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_gygl_new_ssccgl_shcdwhb where shcddm = ?");
		return getModel(sql.toString(),new String[]{key});
	}
	
	/**
	 * 
	 * @描述: 验证物品代码、物品名称是否存在
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午04:34:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExistWpdm(String dm,String mc,String type){
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_gygl_new_ssccgl_wpwhb where 1=1");
		if(StringUtils.isNotNull(dm)){
			if("update".equals(type)){
				sql.append(" and dm != ?");
			}else{
				sql.append(" and dm = ?");
			}
			paraList.add(dm);
		}
		if(StringUtils.isNotNull(mc)){
			sql.append(" and mc = ?");
			paraList.add(mc);
		}
		
		return "0".equals(dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt"));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存损害程度
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午05:16:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param type
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveShcd(GyccDmwhForm form,String type) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		if("add".equals(type)){
			sql.append("insert into xg_gygl_new_ssccgl_shcdwhb(shcddm,shcdmc,je) values(?,?,?)");
			paraList.add(form.getShcddm());
			paraList.add(form.getShcdmc());
			paraList.add(form.getJe());
		}else{
			sql.append("update xg_gygl_new_ssccgl_shcdwhb set shcdmc = ?,je = ? where shcddm = ?");
			paraList.add(form.getShcdmc());
			paraList.add(form.getJe());
			paraList.add(form.getShcddm());
		}
	    return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午05:34:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dm
	 * @param mc
	 * @param type
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExistShcd(String dm,String mc,String type){
		StringBuffer sql = new StringBuffer();
		ArrayList<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_gygl_new_ssccgl_shcdwhb where 1=1");
		if(StringUtils.isNotNull(dm)){
			if("update".equals(type)){
				sql.append(" and shcddm != ?");
			}else{
				sql.append(" and shcddm = ?");
			}
			paraList.add(dm);
		}
		if(StringUtils.isNotNull(mc)){
			sql.append(" and shcdmc = ?");
			paraList.add(mc);
		}
		
		return "0".equals(dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt"));
	}
	
	/**
	 * 
	 * @描述:删除损害程度
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午05:50:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shcddm
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int DelShcd(String[] shcddm) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from xg_gygl_new_ssccgl_shcdwhb where shcddm in(");
		for (int i = 0; i < shcddm.length; i++) {
			sql.append("?");
			if(i != shcddm.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runDelete(sql.toString(), shcddm);
	}
	
	/**
	 * 
	 * @描述: 物品代码是否已被使用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-7 上午09:59:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String[] 返回类型 
	 * @throws
	 */
	public boolean isWpdmNotUserd(String[] dms){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_gygl_new_ssccgl_qswpshb");
		sql.append(" where wpdm in(");
		for (int i = 0; i < dms.length; i++) {
			sql.append("?");
			paraList.add(dms[i]);
			if(i != dms.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		return "0".equals(dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt"));
	}
	
	/**
	 * 
	 * @描述: 损害程度是否已被使用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-7 上午09:59:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String[] 返回类型 
	 * @throws
	 */
	public boolean isShcdNotUserd(String[] dms){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_gygl_new_ssccgl_qswpshb");
		sql.append(" where shcd in(");
		for (int i = 0; i < dms.length; i++) {
			sql.append("?");
			paraList.add(dms[i]);
			if(i != dms.length-1){
				sql.append(",");
			}
		}
		sql.append(" )");
		return "0".equals(dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt"));
	}
}
