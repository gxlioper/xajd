package xsgzgl.gyjc.jcsd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class PfbzDao extends SuperDAOImpl<PfbzForm> {

	@Override
	public List<HashMap<String, String>> getPageList(PfbzForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(PfbzForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" SELECT RPAD(' ', 2 * (LEVEL - 1)) || wsqkyq wsqkyq,");
		sql.append(" t.guid,");
		sql.append(" t.fjid,");
		sql.append(" t.xh");
		sql.append(" FROM XG_JHZY_GYGL_PFBZ t");
		sql.append(" where t.jjlx = ? and t.xydm = ? and js = ?");
		paraList.add(t.getJjlx());
		paraList.add(t.getXydm());
		paraList.add(t.getJs());
		if(StringUtils.isNotNull(t.getWsqkyq())){
			sql.append(" and t.wsqkyq like ? ");
			paraList.add("%"+t.getWsqkyq()+"%");
		}
		sql.append(" START WITH FJID = 'top'");
		sql.append(" CONNECT BY FJID = PRIOR GUID");
		sql.append(" ORDER SIBLINGS BY t.xh");
		sql.append(" ");
		t.getPages().setPageSize(Integer.MAX_VALUE);
		return getPageList(t, sql.toString(), paraList.toArray(new String[]{}));
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setKey("guid");
		this.setClass(PfbzForm.class);
		this.setTableName("xg_jhzy_gygl_pfbz");
	}
	
	/**
	 *    
	 * @描述: 查询序号有没有重复
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-11 下午05:17:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkRepeatXh(PfbzForm t){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt");
		sql.append(" from xg_jhzy_gygl_pfbz");
		sql.append(" where xydm = ?");
		if("top".equals(t.getFjid())){
			sql.append(" and fjid = 'top'");
		}else{
			sql.append(" and fjid != 'top'");
		}
		sql.append(" and xh = ?");
		sql.append(" and js = ?");
		sql.append(" and jjlx = ?");
		paraList.add(t.getXydm());
		paraList.add(t.getXh());
		paraList.add(t.getJs());
		paraList.add(t.getJjlx());
		if(StringUtils.isNotNull(t.getGuid())){
			sql.append(" and guid !=?");
			paraList.add(t.getGuid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(cnt)?true:false;
	}
	
	/**
	 * 
	 * @描述: 评分标准是否重复
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-11 下午05:52:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkRepeat(PfbzForm t){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt");
		sql.append(" from xg_jhzy_gygl_pfbz");
		sql.append(" where xydm = ?");
		sql.append(" and fjid = ?");
		sql.append(" and wsqkyq = ?");
		sql.append(" and js = ?");
		sql.append(" and jjlx = ?");
		paraList.add(t.getXydm());
		paraList.add(t.getFjid());
		paraList.add(t.getWsqkyq());
		paraList.add(t.getJs());
		paraList.add(t.getJjlx());
		if(StringUtils.isNotNull(t.getGuid())){
			sql.append(" and guid !=?");
			paraList.add(t.getGuid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(cnt)?true:false;
	}
	
	/**
	 * 
	 * @描述:检查项目是否被使用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-11 下午07:11:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsUserd(String[] guids){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from (select pfid from xg_jhzy_gygl_jcmxbz union select pfid from xg_jhzy_gygl_ccmxbz) where pfid in(");
		for (int i = 0; i < guids.length; i++) {
			sql.append("?");
			if(i != guids.length-1){
				sql.append(",");
			}
			paraList.add(guids[i]);
		}
		sql.append(")");
		String cnt = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}),"cnt");
		return "0".equals(cnt) ? true :false;
	}
	
	/**
	 * 
	 * @描述:检查是否存在子级项目
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-11 下午07:19:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsCzZjxm(String[] guids){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_jhzy_gygl_pfbz where fjid in(");
		for (int i = 0; i < guids.length; i++) {
			sql.append("?");
			if(i != guids.length-1){
				sql.append(",");
			}
			paraList.add(guids[i]);
		}
		sql.append(")");
		String cnt = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}),"cnt");
		return "0".equals(cnt) ? true :false;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除评分标准
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-11 下午07:21:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delPfbz(String[] guids) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" delete from xg_jhzy_gygl_pfbz where guid in(");
		for (int i = 0; i < guids.length; i++) {
			sql.append("?");
			if(i != guids.length-1){
				sql.append(",");
			}
			paraList.add(guids[i]);
		}
		sql.append(")");
		return dao.runUpdateNotCommit(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述:获取评分标准下拉框中内容
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-12 上午09:05:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmSelectList(PfbzForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select guid dm, wsqkyq mc");
		sql.append(" from xg_jhzy_gygl_pfbz t");
		sql.append(" where t.jjlx = ?");
		sql.append(" and t.js = ?");
		sql.append(" and t.xydm = ?");
		sql.append(" and t.fjid = 'top'");
		sql.append(" order by t.xh");
		return dao.getListNotOut(sql.toString(), new String[]{t.getJjlx(),t.getJs(),t.getXydm()});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:修改model
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-12 上午11:55:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * PfbzForm 返回类型 
	 * @throws
	 */
	public PfbzForm getPfbzModel(String guid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, t1.wsqkyq fjmc");
		sql.append(" from XG_JHZY_GYGL_PFBZ t");
		sql.append(" left join XG_JHZY_GYGL_PFBZ t1");
		sql.append(" on t.fjid = t1.guid");
		sql.append("  where t.guid = ?");
		return getModel(sql.toString(), new String[]{guid});
	}
	
	public List<HashMap<String, String>>  getPfbzListAjax(String fjid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.guid dm, t.wsqkyq mc");
		sql.append(" from XG_JHZY_GYGL_PFBZ t where t.fjid=? order by t.xh asc");
		return dao.getListNotOut(sql.toString(), new String[]{fjid});
	}
	
}
