/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xsgzgl.qgzx.hmdgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

public class HmdglDao extends SuperDAOImpl<HmdglForm>{

	@Override
	public List<HashMap<String, String>> getPageList(HmdglForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(HmdglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select a.*,d.xm ");
		sql.append(" ,case when a.dwlx = '01' then c.bmmc when a.dwlx = '02' then b.yrdwmc when a.dwlx = '03' then '家教数据' else '其他' end mc");
		sql.append(" from xg_qgzx_hmdglb a ");
		sql.append(" left join xg_qgzx_yrdwdmb b on a.dwid = b.id ");
		sql.append(" left join ZXBZ_XXBMDM c on b.xydm = c.bmdm ");
		sql.append(" left join yhb d on a.czr = d.yhm");
		sql.append(" ) t where 1 = 1 ");
//		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	public List<HashMap<String, String>> getYrdwList(HmdglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		if("03".equals(t.getDwlx())){//家教是另一张表
			//todo 等家教做好再改
			sql.append("select * from xg_qgzx_yrdwdmb a ");
		} else{
			sql.append("select * from (");
			sql.append(" select a.id,nvl(yrdwmc,b.bmmc) yrdwmc,nvl(a.xm,c.xm) xm,a.dwlb");
			sql.append(" from xg_qgzx_yrdwdmb a");
			sql.append(" left join ZXBZ_XXBMDM b on a.xydm = b.bmdm");
			sql.append(" left join yhb c on a.zgh = c.yhm");
			sql.append(" where a.dwlb = '"+t.getDwlx()+"'");
			sql.append(" ) t where 1=1 ");
			sql.append(" and not exists (select 1 from xg_qgzx_hmdglb where dwid = t.id)");
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_qgzx_hmdglb");
		super.setKey("id");
	}

	public HashMap<String, String> getSingleHmdInfo(HmdglForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_qgzx_new_hmdb where id = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getId()});
	}

	public List<HashMap<String,String>> getXsHmdList(HmdglForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select t.id,t.xh,t1.xm,t.lhrq,t2.xydm,t2.yhm ");
		sql.append(" ,case when t.dwlx = '01' then t3.bmmc when t.dwlx = '02' then t2.yrdwmc when t.dwlx = '03' then '家教数据' else '其他' end mc");
		sql.append(" from XG_QGZX_NEW_HMDB t ");
		sql.append(" left join view_xsjbxx t1 on t.xh = t1.xh ");
		sql.append(" left join XG_QGZX_YRDWDMB t2 on t.yrdw = t2.id ");
		sql.append(" left join ZXBZ_XXBMDM t3 on t2.xydm = t3.bmdm ");
		sql.append(" ) t where 1=1 ");
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			sql.append(" and (xydm = '"+user.getUserDep()+"' ");
			sql.append("    or yhm = '"+user.getUserName()+"') ");
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	public boolean addxshmdSave(HmdglForm t) throws Exception {
		String sql = "insert into XG_QGZX_NEW_HMDB(xh,dwlx,yrdw,bz,lhrq,czr) values(?,?,?,?,?,?)";
		return dao.runUpdate(sql,new String[]{t.getXh(),t.getDwlx(),t.getDwid(),t.getBz(),t.getLhrq(),t.getCzr()});
	}

	public HashMap<String,String> getYrdwxxByUser(User user){

		StringBuilder sql = new StringBuilder();
		sql.append("select t.id,nvl(t.yrdwmc,t1.bmmc) yrdwmc,t.dwlb from XG_QGZX_YRDWDMB t");
		sql.append(" left join ZXBZ_XXBMDM t1 on t.xydm = t1.bmdm ");
		sql.append(" where t.yhm = ? or t.xydm = ?");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(),new String[]{user.getUserName(),user.getUserDep()});
		//如果校内或者校外单位，去家教表中查询
		if(map == null){
			//todo 家教
			map = new HashMap<String, String>();
		}
		return map;
	}

	public boolean delXsHmd(String[] ids) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from XG_QGZX_NEW_HMDB where (");
		for(int i=0;i<ids.length;i++){
			sql.append(" id = ? ");
			if(i < ids.length-1){
				sql.append(" or ");
			}
		}
		sql.append(") ");

		return dao.runUpdate(sql.toString(),ids);
	}
}
