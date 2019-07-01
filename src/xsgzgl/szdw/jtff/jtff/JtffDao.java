/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 下午02:26:24 
 */  
package xsgzgl.szdw.jtff.jtff;

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
 * @时间： 2016-3-8 下午02:26:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JtffDao extends SuperDAOImpl<JtffForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JtffForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JtffForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t.zgh,");
		sql.append("  t.ffny,");
		sql.append("  t.jsje,");
		sql.append("  t.ffje,");
		sql.append("  t.bz,");
		sql.append("  t.id,");
		sql.append("  t.gw gw,");
		sql.append("  t.dbrs dbrs,");
		sql.append(" (case (t1.xb)");
		sql.append("  when '1' then");
		sql.append(" '男'");
		sql.append(" when '2' then");
		sql.append(" '女'");
		sql.append(" else");
		sql.append(" t1.xb");
		sql.append(" end) xb,");
		sql.append(" t1.xm,");
		sql.append(" t2.bmmc,");
		sql.append(" t2.bmdm,");
		sql.append(" t2.bmdm xydm");
//		sql.append(" t3.dbrs,");
//		sql.append(" t4.gw");
		sql.append(" from XG_SZDW_NEW_JTFFMXB t");
		sql.append(" left join fdyxxb t1");
		sql.append(" on t.zgh = t1.zgh");
		sql.append(" left join zxbz_xxbmdm t2");
		sql.append(" on t1.bmdm = t2.bmdm ");
//		sql.append(" left join");
//		sql.append(" ( select nvl(sum(dbrs),0)  dbrs,zgh from");
//		sql.append(" ( select  count(xh) dbrs,zgh from xsxxb a left join");
//		sql.append(" (select bjdm, zgh");
//		sql.append("  from fdybjb");
//		sql.append(" union");
//		sql.append(" select bjdm, zgh");
//		sql.append(" from bzrbbb) b");
//		sql.append(" on a.bjdm = b.bjdm");
//		sql.append(" group by zgh) group by zgh) t3");
//		sql.append(" on t.zgh = t3.zgh");
//		sql.append(" left join XG_SZDW_NEW_JTFFRYB t4");
//		sql.append(" on t.zgh = t4.zgh");
		sql.append(" order by t.ffny desc,t1.bmdm asc,t.gw asc,t.zgh asc");
		sql.append(" ");
		sql.append(" ");
		sql.append(" )t  where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(JtffForm.class);
		super.setKey("id");
		super.setTableName("XG_SZDW_NEW_JTFFMXB");
	}
	
	/**
	 * 
	 * @描述:已生成津贴发放月份津贴数据查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-11 下午02:45:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ffny
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJtfflist(String ffny){
		StringBuilder sql = new StringBuilder();
		sql.append(" select x.*, nvl(x.ffje, x.jsje) xsje");
		sql.append(" from (select y.zgh,");
		sql.append(" y.gw,");
		sql.append(" y.jtlb,");
		sql.append(" y.bz,");
		sql.append(" y.gdffje,");
		sql.append(" y.xb,");
		sql.append(" y.xm,");
		sql.append(" y.bmmc,");
		sql.append(" y.bmdm,");
		sql.append(" y.dbrs,");
		sql.append(" y.ffny,");
		sql.append(" y.ffje,");
		sql.append(" case ");
		sql.append(" when y.jtlb = 'gd' then");
		sql.append(" y.jsje");
		sql.append(" when y.jtlb = 'zc' then");
		sql.append(" case");
		sql.append(" when (y.gw = '学院专职辅导员' or y.gw = '借调辅导员') then");
		sql.append(" case");
		sql.append(" when y.dbrs > 0 then");
		sql.append(" to_char(280 + 180 +");
		sql.append(" decode(sign(y.dbrs - 90), -1, 0, y.dbrs - 90) * 2)");
		sql.append(" when y.dbrs = 0 then");
		sql.append(" to_char(280)");
		sql.append(" else");
		sql.append(" y.jsje");
		sql.append(" end");
		sql.append(" when (y.gw = '部门兼职辅导员') then");
		sql.append(" case");
		sql.append(" when y.dbrs > 0 then");
		//sql.append(" to_char(280 + 180)");
		sql.append(" to_char(190 + 180 + decode(sign(y.dbrs-40),-1,0,y.dbrs-40) * 2)");
		sql.append(" when y.dbrs = 0 then");
		//sql.append(" '280'");
		sql.append(" '180'");
		sql.append(" else");
		sql.append(" y.jsje");
		sql.append(" end");
		sql.append(" when (y.gw = '学院副书记') then");
		sql.append(" to_char(280 + 180 +");
		sql.append(" decode(sign(y.dbrs - 90), -1, 0, y.dbrs - 90) * 2)");
		sql.append(" when (y.gw = '班主任') then");
		sql.append(" to_char(100 + 90 +");
		sql.append(" decode(sign(y.dbrs - 40), -1, 0, y.dbrs - 40) * 2)");
		sql.append(" when (y.gw = '国际学院、台港澳班主任') then");
		sql.append(" to_char(90 +");
		sql.append(" decode(sign(y.dbrs - 40), -1, 0, y.dbrs - 40) * 2)");
		sql.append(" when (y.gw = '一临二临辅导员') then");
		sql.append(" '280' ");
		sql.append(" else");
		sql.append(" y.jsje");
		sql.append(" end");
		sql.append(" else");
		sql.append(" y.jsje");
		sql.append(" end jsje");
		sql.append(" from (select t.*,");
		sql.append(" (case (t1.xb)");
		sql.append(" when '1' then");
		sql.append(" '男'");
		sql.append(" when '2' then");
		sql.append(" '女'");
		sql.append(" else");
		sql.append(" t1.xb");
		sql.append(" end) xb,");
		sql.append(" t1.xm,");
		sql.append(" t2.bmmc,");
		sql.append(" t2.bmdm,");
		sql.append(" t2.bmdm xydm,");
		sql.append(" t3.dbrs,");
		sql.append(" t4.ffny,");
		sql.append(" case t.jtlb");
		sql.append(" when 'zc' then");
		sql.append(" t4.ffje");
		sql.append(" when 'gd' then");
		sql.append(" t.gdffje");
		sql.append(" else");
		sql.append(" t4.ffje");
		sql.append(" end ffje,");
		sql.append(" case t.jtlb");
		sql.append(" when 'zc' then");
		sql.append(" t4.jsje");
		sql.append(" when 'gd' then");
		sql.append(" t.gdffje");
		sql.append(" else");
		sql.append(" t4.jsje");
		sql.append(" end jsje");
		sql.append(" from XG_SZDW_NEW_JTFFRYB t");
		sql.append(" left join fdyxxb t1");
		sql.append(" on t.zgh = t1.zgh");
		sql.append(" left join zxbz_xxbmdm t2");
		sql.append(" on t1.bmdm = t2.bmdm");
		sql.append(" left join (select nvl(sum(n.dbrs), 0) dbrs, m.zgh");
		sql.append(" from fdyxxb m left join  ");
		sql.append(" (select count(xh) dbrs, zgh");
		sql.append(" from view_xsjbxx a");
		sql.append(" left join (select bjdm, zgh");
		sql.append(" from fdybjb");
		sql.append(" union");
		sql.append(" select bjdm, zgh");
		sql.append(" from bzrbbb) b");
		sql.append(" on a.bjdm = b.bjdm");
		sql.append(" group by zgh) n on m.zgh = n.zgh");
		sql.append(" group by m.zgh) t3");
		sql.append(" on t.zgh = t3.zgh");
		sql.append(" left join XG_SZDW_NEW_JTFFMXB t4");
		sql.append(" on t.zgh = t4.zgh");
		sql.append(" where t4.ffny = ?");
		sql.append(" order by  t1.bmdm asc, t.gw asc, t.zgh asc) y) x");
		return dao.getListNotOut(sql.toString(), new String[]{ffny});
	}
	
	/**
	 * 
	 * @描述:已生成津贴发放月份津贴数据查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-11 下午02:45:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ffny
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getWscJtfflist(String ffny){
		StringBuilder sql = new StringBuilder();
		sql.append("  select x.*,  x.jsje xsje,x.jsje ffje");
		sql.append("  from (select y.zgh,");
		sql.append("  y.gw,");
		sql.append("  y.jtlb,");
		sql.append("  y.bz,");
		sql.append("  y.gdffje,");
		sql.append("  y.xb,");
		sql.append("  y.xm,");
		sql.append("  y.bmmc,");
		sql.append("  y.bmdm,");
		sql.append("  y.dbrs,");
		sql.append("  y.ffny,");
		sql.append("  case");
		sql.append("  when y.jtlb = 'gd' then");
		sql.append("  y.gdffje");
		sql.append("   when y.jtlb = 'zc' then");
	    sql.append("   case");
		sql.append("  when (y.gw = '学院专职辅导员' or y.gw = '借调辅导员') then");
		sql.append("  case");
		sql.append("  when y.dbrs > 0 then");
		sql.append("  to_char(280 + 180 +");
		sql.append("  decode(sign(y.dbrs - 90), -1, 0, y.dbrs - 90) * 2)");
		sql.append("  when y.dbrs = 0 then");
		sql.append("  to_char(280)");
		sql.append("  else");
		sql.append("  to_char(280)");
		sql.append("  end");
		sql.append("  when (y.gw = '部门兼职辅导员') then");
		sql.append("  case");
		sql.append("  when y.dbrs > 0 then");
		sql.append("  to_char(190 + 180 + decode(sign(y.dbrs-40),-1,0,y.dbrs-40) * 2) ");
		//sql.append("  to_char(280 + 180)");
		sql.append("  when y.dbrs = 0 then");
		sql.append("  '180'");
		//sql.append("  '280'");
		sql.append("  else");
		sql.append(" '180'");
		//sql.append("  '280'");
		sql.append("  end");
	    sql.append("  when (y.gw = '学院副书记') then");
		sql.append("  to_char(280 + 180 +");
		sql.append("  decode(sign(y.dbrs - 90), -1, 0, y.dbrs - 90) * 2)");
		sql.append("  when (y.gw = '班主任') then");
		sql.append("  to_char(100 + 90 +");
		sql.append("  decode(sign(y.dbrs - 40), -1, 0, y.dbrs - 40) * 2)");
		sql.append("  when (y.gw = '国际学院、台港澳班主任') then");
		sql.append("  to_char(90 +");
		sql.append("  decode(sign(y.dbrs - 40), -1, 0, y.dbrs - 40) * 2)");
		sql.append(" when (y.gw = '一临二临辅导员') then");
		sql.append(" '280' ");
	    sql.append("  end");
		sql.append("  end jsje");
		sql.append("  from (select t.*,");
		sql.append("  (case (t1.xb)");
		sql.append("  when '1' then");
		sql.append("  '男'");
		sql.append("   when '2' then");
		sql.append("  '女'");
		sql.append("  else");
		sql.append("  t1.xb");
		sql.append("  end) xb,");
		sql.append("  t1.xm,");
		sql.append("  t2.bmmc,");
		sql.append("  t2.bmdm,");
		sql.append("  t2.bmdm xydm,");
		sql.append("  t3.dbrs,");
		sql.append("  ? ffny");
		sql.append("   from XG_SZDW_NEW_JTFFRYB t");
		sql.append("   left join fdyxxb t1");
		sql.append("   on t.zgh = t1.zgh");
		sql.append("   left join zxbz_xxbmdm t2");
		sql.append("   on t1.bmdm = t2.bmdm");
		sql.append("   left join (select nvl(sum(n.dbrs), 0) dbrs, m.zgh");
		sql.append("   from fdyxxb m left join  ");
		sql.append("   (select count(xh) dbrs, zgh");
		sql.append("   from view_xsjbxx a");
        sql.append("   left join (select bjdm, zgh");
	    sql.append("   from fdybjb");
		sql.append("   union");
		sql.append("   select bjdm, zgh");
		sql.append("   from bzrbbb) b");
		sql.append("   on a.bjdm = b.bjdm");
		sql.append("   group by zgh) n on m.zgh = n.zgh ");
		sql.append("   group by m.zgh) t3");
		sql.append("   on t.zgh = t3.zgh");
		sql.append("   order by t1.bmdm asc, t.gw asc, t.zgh asc) y) x");
		return dao.getListNotOut(sql.toString(), new String[]{ffny});
	}
	
	/**
	 * 获取最近三个月日期
	 */
	public List<HashMap<String, String>> getLastThreeMonth(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select to_char(add_months(trunc(sysdate),-2),'yyyymm') ny from dual");
		sql.append(" union");
		sql.append(" select to_char(add_months(trunc(sysdate),-1),'yyyymm') ny from dual");
		sql.append(" union");
		sql.append(" select to_char(trunc(sysdate),'yyyymm') ny from dual");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 *验证是否有数据
	 */
	public boolean isExist(String ffny){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) result from XG_SZDW_NEW_JTFFMXB where ffny = ?");
		sql.append(" ");
		sql.append(" ");
		int  result= Integer.parseInt(dao.getOneRs(sql.toString(), new String[]{ffny}, "result"));
		return result > 0 ? true:false;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:插入前先删除
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2016-3-11 下午04:52:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ffny
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteRows(String ffny) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_SZDW_NEW_JTFFMXB where ffny = ?");
		return dao.runUpdate(sql.toString(),new String[]{ffny});
	}
}
