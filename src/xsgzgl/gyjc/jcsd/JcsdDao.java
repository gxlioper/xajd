package xsgzgl.gyjc.jcsd;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import common.newp.ArrayUtil;

public class JcsdDao extends SuperDAOImpl<JcsdForm> {

	@Override
	public List<HashMap<String, String>> getPageList(JcsdForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JcsdForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		//取用户身份作为参数
		String userRole = user.getUserStatus();
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select * from (");
		sql.append(" select distinct t.xydm,");
		sql.append(" t.xymc,");
		sql.append(" replace(wm_concat(case when t1.jjlx = '1' and t1.js = ? and t3.xm is not null then t3.xm else null end),';',',') wsjcr,");
		sql.append(" replace(wm_concat(case when t1.jjlx = '2' and t1.js = ? and t3.xm is not null then t3.xm else null end),';',',') aqjcr,");
		sql.append(" replace(wm_concat(case when t1.jjlx = '3' and t1.js = ? and t3.xm is not null then t3.xm else null end),';',',') jljcr,");
		if("xx".equals(userRole)){
			sql.append(" replace(wm_concat(case when t1.jjlx = '0' and t1.js = ? and t3.xm is not null then t3.xm else null end),';',',') ccr,");
			paraList.add(userRole);
		}
		paraList.add(userRole);
		paraList.add(userRole);
		paraList.add(userRole);
		sql.append(" nvl(t2.qshnum,0) qshnum,");
		sql.append(" nvl(t2.chnum,0) chnum, ");
		sql.append(" nvl(t2.ldnum,0) ldnum,");
		sql.append(" nvl(t4.pfnum,0) pfnum");
		sql.append(" from (select distinct xydm,xymc from view_njxyzybj) t");
		sql.append(" left join xg_jhzy_gygl_fpjc t1");
		sql.append(" on t.xydm = t1.xydm");
		sql.append(" left join (select xydm,");
		sql.append(" count(distinct lddm || qsh) qshnum,");
		sql.append(" count(distinct lddm || ch) chnum,");
		sql.append(" count(distinct lddm) ldnum");
		sql.append(" from xg_gygl_new_qsxxb");
		sql.append(" group by xydm) t2");
		sql.append(" on t.xydm = t2.xydm");
		sql.append(" left join fdyxxb t3");
		sql.append(" on t1.zgh = t3.zgh ");
		sql.append(" left join (select xydm,count(1) pfnum from xg_jhzy_gygl_pfbz group by xydm) t4");
		sql.append(" on t.xydm = t4.xydm");
		sql.append(" group by t.xydm,t.xymc,t2.qshnum,t2.chnum,t2.ldnum,t4.pfnum order by t.xydm asc");
		sql.append(" ) where 1=1 ");
		if("xy".equals(userRole)){
			sql.append(" and xydm = ?");
			paraList.add(user.getUserDep());
		}
		sql.append(searchTj);
		ArrayUtil arrayutil = new ArrayUtil();
		//参数重组
		String[] inputVnew = arrayutil.unionArray(paraList.toArray(new String[]{}), inputV);
		return getPageList(t, sql.toString(), inputVnew);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setKey("xydm");
		this.setClass(JcsdForm.class);
		this.setTableName("xg_jhzy_gygl_fpjc");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 查询人员分配List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-10 上午10:28:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getRyfpList(JcsdForm t, User user) throws  Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		String userRole = user.getUserStatus();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select t.* from ");
		sql.append(" (select *");
		sql.append(" from view_fdyxx t where ");
		if(StringUtils.isNotNull(t.getSffp())){
			sql.append(t.getSffp());
		}
		sql.append(" exists (select 1 from xg_jhzy_gygl_fpjc t1");
		sql.append(" where  t1.zgh = t.zgh");
		sql.append(" and xydm = ?");
		sql.append(" and js = ?");
		sql.append(" and JJLX = ?)");
		paraList.add(t.getXydm());
		paraList.add(userRole);
		paraList.add(t.getJjlx());
		sql.append(" )t where 1=1 ");
		sql.append(searchTj);
		sql.append(" ");
		ArrayUtil arrayutil = new ArrayUtil();
		//参数重组
		String[] inputVnew = arrayutil.unionArray(paraList.toArray(new String[]{}), inputV);
		return getPageList(t, sql.toString(), inputVnew);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存人员分配
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-10 下午01:43:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paraList
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveFpry(List<String[]> paraList) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_jhzy_gygl_fpjc(xydm,zgh,jjlx,js)values(?,?,?,?)");
		return dao.runBatchNotCommit(sql.toString(), paraList);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 取消分配
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-10 下午01:55:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param js
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRyfp(JcsdForm t,String js) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		String[] zghs = t.getZghs();
		String xydm = t.getXydm();
		String jjlx = t.getJjlx();
		sql.append(" delete from xg_jhzy_gygl_fpjc where xydm = ? and jjlx = ? and js = ? and zgh in( ");
		paraList.add(xydm);
		paraList.add(jjlx);
		paraList.add(js);
		for (int i = 0; i < zghs.length; i++) {
		  sql.append("?");
		  paraList.add(zghs[i]);
		  if(i != zghs.length-1){
			  sql.append(",");
		  }
		}
		sql.append(" )");
		return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}

}
