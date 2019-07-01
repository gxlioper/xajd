/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package xsgzgl.jxgl.general.gfjyqk.gfjyqkjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.jxgl.general.gfjyqk.gfjyqksq.GfjysqForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class GfjyjgDao extends SuperDAOImpl<GfjyjgForm>{

	@Override
	public List<HashMap<String, String>> getPageList(GfjyjgForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(GfjyjgForm t, User user)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*");
		sql.append(" from ( select t1.*,decode(t1.sjly,1,'��������',0,'�������') sjlymc,decode(t1.gfqkfl,1,'���۵Ǽ����',2,'�ξ��������',3,'���鸴ѧ���',4,'��������',5,'�������',6,'�μӻ���') gfqkflmc,");
		sql.append("t2.xm, (case t2.xb when '1' then  '��' when '2' then 'Ů' else t2.xb end) xb,t2.xz,t2.rxrq, ");
		sql.append(" t2.sfzh,t6.bjmc,t2.lxdh,t6.xydm,t6.zydm,t2.bjdm,t6.xymc,t6.zymc,t2.nj,t3.xqmc from xg_gfjy_gfjyqkjgb ");
		sql.append(" t1  left join view_xsbfxx t2 on t1.xh = t2.xh left join view_njxyzybj_all t6 on t2.bjdm = t6.bjdm ");
		sql.append(" left join  xqdzb t3 on t1.xq = t3.xqdm ) a  ");
		sql.append(" where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("jgid");
		super.setTableName("xg_gfjy_gfjyqkjgb");
	}

	public boolean deleteExist(GfjyjgForm model) throws Exception {
		StringBuilder sql = new StringBuilder(
		" delete from xg_gfjy_gfjyqkjgb where xh=? and xn = ? and xq = ? and gfqkfl=?");
		return dao.runUpdate(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq(),model.getGfqkfl()});
	}

	public boolean isExist(GfjyjgForm model) {
		String sql = "select count(1) num from xg_gfjy_gfjyqkjgb where xn = ? and xq =? and xh=? and gfqkfl=? " ;
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getXh(),model.getGfqkfl()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isExistforUpdate(GfjyjgForm model) {
		String sql = "select count(1) num from xg_gfjy_gfjyqkjgb where xn = ? and xq =? and xh=? and gfqkfl=?  and jgid <> ?" ;
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getXh(),model.getGfqkfl(),model.getJgid()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isCanDel(String jgid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_gfjy_gfjyqkjgb where jgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{jgid});
		String sjly=map.get("sjly");
		//���δ�ύ�ſ����ύ
		return sjly.equals("0")?true:false;
	}

	public HashMap<String, String> getGfjyqkjg(String jgid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_gfjy_gfjyqkjgb a");
		sb.append(",view_xsxxb xsxx where a.xh=xsxx.xh and a.jgid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{jgid});
	}

	public HashMap<String, String> getGfjyqkInfo(GfjyjgForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,decode(t1.gfqkfl,1,'���۵Ǽ����',2,'�ξ��������',3,'���鸴ѧ���',4,'��������',5,'�������',6,'�μӻ���') gfqkflmc,t3.xqmc");
		sql.append(" from xg_gfjy_gfjyqkjgb t1 left join view_xsxxb t2 ");
		sql.append(" on t1.xh = t2.xh left join xqdzb t3 on t1.xq = t3.xqdm ");
		sql.append(" where t1.jgid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getJgid()});
	}

}
