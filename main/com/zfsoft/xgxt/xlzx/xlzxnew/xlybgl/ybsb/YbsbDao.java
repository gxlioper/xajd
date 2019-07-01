package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsb;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class YbsbDao extends SuperDAOImpl<YbsbForm> {

	@Override
	public List<HashMap<String, String>> getPageList(YbsbForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(YbsbForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select * from (");
		sql.append(" select t.*,to_number(t.yf) || '月' yfmc,t1.xm,t2.bmmc xymc,decode(t.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',t.shzt) shztmc from xg_xljk_xlwygl_ybsbb t");
		sql.append(" left join yhb t1");
		sql.append(" on t.txr =t1.yhm");
		sql.append(" left join ZXBZ_XXBMDM t2");
		sql.append(" on t.xydm = t2.bmdm");
		sql.append(")t where 1=1 ");
		sql.append(searchTj);
		sql.append(" order by xn,yf,txrq desc");
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(YbsbForm.class);
		this.setKey("sbid");
		this.setTableName("xg_xljk_xlwygl_ybsbb");
	}
	
	
	/**
	 * 
	 * @描述: 获取周报问题人员信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午04:26:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbsqid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getYbWtryInfo(String ybjgid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.ybwtms,a.ybgycs,a.ybgyhjg,a.wtfsrq,b.xm,b.xb,b.bjmc,b.sjhm from  xg_xljk_xlwygl_wtxxb a");
		sql.append(" left join view_xsbfxx b");
		sql.append(" on a.xh = b.XH");
		sql.append(" where a.ybsqid = ?");
		return dao.getListNotOut(sql.toString(),new String[]{ybjgid});
	}
	
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-5 下午04:22:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbsqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delWtb(String ybsqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xljk_xlwygl_wtxxb where ybsqid = ?");
		return dao.runUpdate(sql.toString(),new String[]{ybsqid});
	}
	
	/**
	 * 
	 * @描述: 验证是否已填写周报
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-1 上午11:33:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @param sbzbid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExist(String xydm,String xn,String yf){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_xljk_xlwygl_ybsbb where xydm =? and xn = ? and yf=?");
		int rs = Integer.valueOf(dao.getOneRs(sql.toString(),new String[]{xydm,xn,yf},"cnt"));
		return rs > 0 ? false : true;
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @描述: 保存问题表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午05:34:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbsb
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveDataIntoWtb(List<String[]> paraList) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xljk_xlwygl_wtxxb(lx,ybsqid,xh,ybwtms,ybgycs,ybgyhjg,wtfsrq) values(?,?,?,?,?,?,?)");
		return dao.runBatchBoolean(sql.toString(), paraList);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除月报结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-2 下午03:07:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delSbjg(String[] sbjgids) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xljk_xlwygl_wtxxb where ybsqid in(");
		for (int i = 0; i < sbjgids.length; i++) {
			sql.append("?");
			if(i != sbjgids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(),sbjgids );
	}

	public boolean update(YbsbForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_XLJK_XLWYGL_YBSBB set ztqk='',sfywt=?,shzt=? where sbid = ?");
		return dao.runUpdate(sql.toString(),new String[]{t.getSfywt(),t.getShzt(),t.getSbid()});
	}

}
