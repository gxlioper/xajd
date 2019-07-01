/**
 * @部门:学工产品事业部
 * @日期：2016-3-9 上午09:31:48 
 */  
package xsgzgl.szdw.jtff.util;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2016-3-9 上午09:31:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JtffUtilDao extends SuperDAOImpl<JtffUtilForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JtffUtilForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JtffUtilForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	
	/**
	 * 津贴名单维护教师选择查询 
	 * 参数：jtlb （正常津贴 zc,固定津贴gd）
	 */
	public List<HashMap<String, String>> getJsxxList(JtffUtilForm t, User user)
	  throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t.zgh,");
		sql.append(" t.xm,");
		sql.append(" case  t.xb");
		sql.append(" when '1' then '男'");
		sql.append(" when '2' then '女'");
		sql.append(" else t.xb end xb,");
		sql.append(" t.sfzh,");
		sql.append(" substr(t.sfzh,'7','6') csny,");
		sql.append(" t.mz mzdm,");
		sql.append(" t1.mzmc,");
		sql.append(" substr(t.cjgzrq,'1','4') rznf,");
		sql.append(" t.xl,");
		sql.append(" t.zzmm zzmmdm,");
		sql.append(" t2.zzmmmc,");
		sql.append(" t.xsgzyjfx,");
		sql.append(" t.lxdh,");
		sql.append(" t.bmdm,");
		sql.append(" t3.bmmc,");
		sql.append(" t3.dbrs,");
		sql.append(" t4.zwmc");
		sql.append(" from fdyxxb t");
		sql.append(" left join mzdmb t1");
		sql.append(" on t.mz = t1.mzdm");
		sql.append(" left join zzmmdmb t2");
		sql.append(" on t.zzmm = t2.zzmmdm");
		sql.append(" left join zxbz_xxbmdm t3");
		sql.append(" on t.bmdm = t3.bmdm");
		sql.append(" left join");
		sql.append(" ( select nvl(sum(dbrs),0)  dbrs,zgh from");
		sql.append(" ( select  count(xh) dbrs,zgh from xsxxb a left join");
		sql.append(" (select bjdm, zgh");
		sql.append(" from fdybjb");
		sql.append(" union");
		sql.append(" select bjdm, zgh");
		sql.append(" from bzrbbb) b");
		sql.append(" on a.bjdm = b.bjdm");
		sql.append(" group by zgh) group by zgh) t3");
		sql.append("  on t.zgh = t3.zgh");
		sql.append(" left join zwb t4");
		sql.append(" on t.zw = t4.zwdm");
	
		//判断参数
		if(t.getJtlb().equals("zc")){
			sql.append(" where t.zgh not in(select distinct zgh from XG_SZDW_NEW_JTFFRYB)");
		}else{
			sql.append(" where t.zgh in (select distinct zgh from XG_SZDW_NEW_JTFFRYB where jtlb = 'zc')");
		}
		sql.append(" ");
		sql.append(" )t  where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	public HashMap<String, String> getJsjbxx(String zgh) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.zgh,");
		sql.append(" t.xm,");
		sql.append(" case  t.xb");
		sql.append(" when '1' then '男'");
		sql.append(" when '2' then '女'");
		sql.append(" else t.xb end xb,");
		sql.append(" t.sfzh,");
		sql.append(" substr(t.sfzh,'7','6') csny,");
		sql.append(" t.mz mzdm,");
		sql.append(" t1.mzmc,");
		sql.append(" substr(t.cjgzrq,'1','4') rznf,");
		sql.append(" t.xl,");
		sql.append(" t.zzmm zzmmdm,");
		sql.append(" t2.zzmmmc,");
		sql.append(" t.xsgzyjfx,");
		sql.append(" t.lxdh,");
		sql.append(" t.bmdm,");
		sql.append(" t3.bmmc,");
		sql.append(" t3.dbrs,");
		sql.append(" t4.zwmc");
		sql.append(" from fdyxxb t");
		sql.append(" left join mzdmb t1");
		sql.append(" on t.mz = t1.mzdm");
		sql.append(" left join zzmmdmb t2");
		sql.append(" on t.zzmm = t2.zzmmdm");
		sql.append(" left join zxbz_xxbmdm t3");
		sql.append(" on t.bmdm = t3.bmdm");
		sql.append(" left join");
		sql.append(" ( select nvl(sum(dbrs),0)  dbrs,zgh from");
		sql.append(" ( select  count(xh) dbrs,zgh from xsxxb a left join");
		sql.append(" (select bjdm, zgh");
		sql.append(" from fdybjb");
		sql.append(" union");
		sql.append(" select bjdm, zgh");
		sql.append(" from bzrbbb) b");
		sql.append(" on a.bjdm = b.bjdm");
		sql.append(" group by zgh) group by zgh) t3");
		sql.append("  on t.zgh = t3.zgh");
		sql.append(" left join zwb t4");
		sql.append(" on t.zw = t4.zwdm");
		sql.append(" where t.zgh = ?");
		return dao.getMapNotOut(sql.toString(), new String[] { zgh });
	}
	
	public boolean isExists(String zgh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from XG_SZDW_NEW_JTFFRYB where zgh = ?");
		int num = Integer.parseInt(dao.getOneRs(sql.toString(), new String[]{zgh}, "num")) ;
		return num > 0 ? true :false;
	}
	
	/**
	 * 根据职工号获取人员维护信息
	 */
	public HashMap<String, String> getRywhxx(String zgh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from XG_SZDW_NEW_JTFFRYB where zgh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}
	
	public boolean isExists1(String zgh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from XG_SZDW_NEW_JTFFRYB where zgh = ? and jtlb = 'gd'");
		int num = Integer.parseInt(dao.getOneRs(sql.toString(), new String[]{zgh}, "num")) ;
		return num > 0 ? true :false;
	}
}
