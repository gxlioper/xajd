package xsgzgl.gyjc.ccjgcx;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class CcjgDao extends SuperDAOImpl<CcjgForm> {

	@Override
	public List<HashMap<String, String>> getPageList(CcjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(CcjgForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select * from (");
		sql.append(" select a.*,case when e.kfs='0' or e.kfs is null then 'A' when e.kfs<3 and e.kfs>0 and a.jjlx = '1' then 'B' else 'C' end qsdj,e.xh bhgsm,b.bmmc xymc,c.ldmc, c.CH,d.xm tjrxm,case when a.jjlx='1' then '卫生检查' when a.jjlx='2' then '安全检查' else '纪律检查' end jclxmc");
		sql.append(" from xg_jhzy_gygl_ccmx a left join zxbz_xxbmdm b on a.xydm=b.bmdm left join view_xg_gygl_new_qsxx c on a.lddm = c.lddm and a.qsh = c.QSH");
		sql.append(" left join yhb d on a.tjr=d.yhm left join ( ");
		sql.append("  select * from (");
		sql.append("  select ");
		sql.append("  a.xydm,");
		sql.append("  a.qsh,");
		sql.append("  a.lddm,");
		sql.append("  a.jcrq,");
		sql.append("  b.jjlx,");
		sql.append("  nvl(sum(case when b.xh is not null then 1 else 0 end) over(partition by b.jjlx,a.jcrq,a.lddm,a.qsh order by b.xh), 0)  kfs,");
		sql.append(" replace(wm_concat(b.xh) over(partition by b.jjlx,a.jcrq,a.lddm,a.qsh  order by b.xh), ';', ',') xh,");
		sql.append("  row_number() over(partition by b.jjlx,a.jcrq,a.lddm,a.qsh  order by b.xh desc) as ddd");
		sql.append(" from xg_jhzy_gygl_ccmxbz a");
		sql.append(" left join xg_jhzy_gygl_pfbz b");
		sql.append(" on a.pfid = b.guid ) where ddd = 1");
		sql.append(" )e  ");
		sql.append(" on a.xydm=e.xydm and a.lddm=e.lddm and a.qsh=e.qsh and a.jcrq=e.jcrq and a.jjlx=e.jjlx)t where 1=1 ");
		//如果是抽查菜单，只看分配给自己的学院抽查数据
		if("cc".equals(t.getFlag())){
			sql.append(" and exists(select 1 from xg_jhzy_gygl_fpjc fp where fp.zgh = '"+user.getUserName()+"' and fp.js = 'xx' and fp.jjlx = '0' and fp.xydm = t.xydm)");
		}
		sql.append(searchTj);
		sql.append("order by jcrq desc,lddm asc,qsh asc,jjlx asc");
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getYdxgCcjgPageList(CcjgForm t, User user)throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
	     sql.append("select a.*,c.xymc,c.ch,c.ldmc,to_char(to_date(a.jcrq,'yyyy-mm-dd'),'day') jcz,to_char(to_date(a.jcrq,'yyyy-mm-dd'),'DD')jcr,nvl(b.wsjc,'A') wsjc,nvl(b.aqjc,'A') aqjc,nvl(b.jljc,'A') jljc ");
	     sql.append("from(select distinct xydm,lddm,qsh,jcrq,fjid from xg_jhzy_gygl_ccmx)a left join "); 
	     sql.append(" (select xydm,lddm,qsh,jcrq,wm_concat(wsjc) wsjc,wm_concat(aqjc) aqjc,wm_concat(jljc)jljc from( select xydm,lddm,qsh,jcrq, case when jjlx='1' then qsdj else '' end wsjc,");
	     sql.append(" case  when jjlx='2' then qsdj else '' end aqjc, case when jjlx='3' then qsdj else '' end jljc from(select e.*,case when e.kfs='0' or e.kfs is null then 'A' when e.kfs<3 ");
	     sql.append(" and e.kfs>0 and e.jjlx = '1' then 'B' else 'C' end qsdj  from ( select count(1)kfs,wm_concat(b.xh) xh,a.xydm,a.qsh,a.lddm,a.jcrq,b.jjlx from xg_jhzy_gygl_ccmxbz a ");
	     sql.append(" left join xg_jhzy_gygl_pfbz b on a.pfid=b.guid group by a.xydm,a.qsh,a.lddm,a.jcrq,b.jjlx)e)) group by xydm,lddm,qsh,jcrq)b on a.xydm=b.xydm and a.lddm=b.lddm ");
	     sql.append(" and a.qsh=b.qsh and a.jcrq=b.jcrq left join view_xg_gygl_new_qsxx c on a.lddm=c.lddm and a.qsh=c.qsh where to_char(to_date(a.jcrq, 'yyyy-mm-dd'),'yyyy-mm')   like '%'||?||'%'  ");
	     sql.append(" and exists(select 1 from xg_jhzy_gygl_fpjc fp where fp.zgh = '"+user.getUserName()+"' and fp.js = 'xx' and fp.jjlx = '0' and fp.xydm = a.xydm)");
	     sql.append(" order by a.jcrq desc");
		return getPageList(t, sql.toString(), new String[]{t.getJcrq()});
	}
	public String getYhqx(User user)throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
	     sql.append(" select  count(1) num from xg_jhzy_gygl_fpjc fp where fp.zgh = '"+user.getUserName()+"' and fp.js = 'xx' and fp.jjlx = '0'");
		return dao.getOneRs(sql.toString(), new String[]{}, "num");
	}
	public List<HashMap<String, String>> getQsxxPageList(CcjgForm t, User user)
	throws Exception {
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select t.* from view_xg_gygl_new_qsxx t where 1=1 ");
		sql.append(searchTj);
		sql.append(" and xydm in (select xydm from xg_jhzy_gygl_fpjc where zgh = '"+
				user.getUserName()
				+"' and  jjlx = '0')");
		return getPageList(t, sql.toString(), inputV);
   }
	
	public CcjgForm getModel(CcjgForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select a.* ,b.xymc,b.ldmc,b.ch from xg_jhzy_gygl_ccmx a left join view_xg_gygl_new_qsxx b");
		sql.append(" on a.lddm=b.lddm and a.qsh=b.qsh where a.guid=?");
		return getModel(sql.toString(), new String[]{t.getGuid()});
	}
	public CcjgForm getCcjgModel(CcjgForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select a.* ,b.xymc,b.ldmc,b.ch from xg_jhzy_gygl_ccmx a left join view_xg_gygl_new_qsxx b");
		sql.append(" on a.lddm=b.lddm and a.qsh=b.qsh where a.xydm=? and a.lddm=? and a.qsh=? and a.jcrq=? and rownum=1");
		return getModel(sql.toString(), new String[]{t.getXydm(),t.getLddm(),t.getQsh(),t.getJcrq()});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(CcjgForm.class);
		super.setKey("guid");
		super.setTableName("xg_jhzy_gygl_ccmx");
	}
	public boolean delCcmx(CcjgForm t) throws Exception {
		String sql = "delete from xg_jhzy_gygl_ccmx where xydm = ? and lddm=? and qsh=? and jcrq=? ";
		return dao.runUpdate(sql, new String[] { t.getXydm(),t.getLddm(),t.getQsh(),t.getJcrq() });
	}
	public boolean delCcmxbz(CcjgForm t) throws Exception {
		String sql = "delete from xg_jhzy_gygl_ccmxbz where xydm = ? and lddm=? and qsh=? and jcrq=? ";
		return dao.runUpdate(sql, new String[] { t.getXydm(),t.getLddm(),t.getQsh(),t.getJcrq() });
	}
	public boolean isHaveCcjg(CcjgForm t) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_jhzy_gygl_ccmx where xydm = ? and lddm=? and qsh=? and jcrq=?");
		if (null != t.getGuid()) {
			sql.append(" and guid<>'" + t.getGuid() + "' ");
		}
		String num = dao.getOneRs(sql.toString(), new String[] { t.getXydm(),t.getLddm(),t.getQsh(),t.getJcrq()}, "num");
		return Integer.parseInt(num) > 2;
	}
	/**
	 * 
	 * @描述:检查类型保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-4-20 上午11:28:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean jclxPlbc(List<String[]> params) throws SQLException {
		String sql = "insert into XG_JHZY_GYGL_CCMX(xydm,lddm,qsh,jjlx,tjr,jcrq,fjid) values(?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 
	 * @描述:保存评分标准明细
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-4-20 上午11:38:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean pfbzPlbc(List<String[]> params) throws SQLException {
		String sql = "insert into xg_jhzy_gygl_ccmxbz(pfid,xydm,lddm,qsh,jcrq,xh,cwh) values(?,?,?,?,?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	public boolean delCcmxbz(String[] ids) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_jhzy_gygl_ccmxbz where xydm||lddm||qsh||jcrq in (select xydm||lddm||qsh||jcrq ");
		sql.append(" from XG_JHZY_GYGL_CCMX where guid in(");
		for (int i = 0 , n = ids.length ; i < n ; i++){
			sql.append("?");
			if(i!=0){
				sql.append(",");
			}
		}
		sql.append("))");
		return dao.runUpdate(sql.toString(),ids);
	}
	/**
	 * 
	 * @描述:卫生检查信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-4-20 下午05:02:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	List<HashMap<String, String>> getCcjgList(CcjgForm t) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select a.pfid,b.wsqkyq pfmc,b.fjid dm,d.xymc,c.wsqkyq mc,c.jjlx,a.xh,a.cwh,(select xm from xsxxb t where a.xh=t.xh)xm ");
		sql.append(" from xg_jhzy_gygl_ccmxbz a left join xg_jhzy_gygl_pfbz b on a.pfid=b.guid");
		sql.append(" left join xg_jhzy_gygl_pfbz c on b.fjid=c.guid left join view_xsbfxx d on a.xh=d.xh where a.xydm=? and a.lddm=? and a.qsh=? and a.jcrq=? ");
		return dao.getListNotOut(sql.toString(), new String[]{t.getXydm(),t.getLddm(),t.getQsh(),t.getJcrq()});
	}
	
	List<HashMap<String, String>> getQscyList(CcjgForm t) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct a.xh,d.xm,d.xydm,d.xymc,a.cwh, ");
		sql.append("(select nvl2(t.xh,'寝室长','') from xg_gygl_new_gyglryb t ");
		sql.append("where rzzt = '在任' and a.lddm=t.lddm and a.qsh=t.qsh and a.xh=t.xh) qsz ");
		sql.append(" from xg_jhzy_gygl_ccmxbz a left join view_xsbfxx d on a.xh=d.xh");
		sql.append("   where a.xh is not null and a.xydm=? and a.lddm=? and a.qsh=? and a.jcrq=? order by to_number(a.cwh)");
		return dao.getListNotOut(sql.toString(), new String[]{t.getXydm(),t.getLddm(),t.getQsh(),t.getJcrq()});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 在维护保存时判断附件是否必填
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-24 下午04:57:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fjid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean  delFjxxByfid(String fjid,String[] fids) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("  delete from xg_comm_fileupload_data where  gid = ? ");
		paraList.add(fjid);
		if(fids != null && fids.length >0){
			sql.append(" and fid not in(");
			for (int i = 0; i < fids.length; i++) {
				sql.append("?");
				if(i != fids.length-1){
					sql.append(",");
				}
				paraList.add(fids[i]);
			}
			sql.append(")");
		}
		return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 插入文件名
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-28 下午06:37:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fjbList
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertIntoFjb(List<String[]> fjbList) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_comm_fileupload_data(fid,originalname,generatename,ext,filesize,uploadtime,gid)values(?,?,?,?,?,?,?)");
		int[] rs = dao.runBatch(sql.toString(), fjbList);
		for (int i = 0; i < rs.length; i++) {
			if(rs[i] == Statement.EXECUTE_FAILED){
				return false;
			}
		}
		return true;
	}
	
	
}
