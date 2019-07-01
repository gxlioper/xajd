/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xsgzgl.jxgl.general.gfjyqk.gfjyqksq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class GfjysqDao extends SuperDAOImpl<GfjysqForm>{

	@Override
	public List<HashMap<String, String>> getPageList(GfjysqForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(GfjysqForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.splc splcidnew");
		sql.append(" from ( select t1.*,decode(t1.gfqkfl,1,'兵役登记情况',2,'参军入伍情况',3,'退伍复学情况',4,'评奖评优',5,'奖惩情况',6,'参加活动情况') gfqkflmc,");
		sql.append("t2.xm, (case t2.xb when '1' then  '男' when '2' then '女' else t2.xb end) xb,t2.xz,t2.rxrq, ");
		sql.append(" t2.sfzh,t6.bjmc,t2.lxdh,t6.xydm,t6.zydm,t2.bjdm,t6.xymc,t6.zymc,t2.nj,t3.xqmc, ");
		sql.append(" decode(t1.shzt, '0','未提交', '1', '通过','2','不通过', '3','已退回', '4','需重审', ");
		sql.append(" '5', '审核中', '','无需审核',t1.shzt) shztmc from xg_gfjy_gfjyqksqb ");
		sql.append(" t1  left join view_xsbfxx t2 on t1.xh = t2.xh left join view_njxyzybj_all t6 on t2.bjdm = t6.bjdm ");
		sql.append(" left join  xqdzb t3 on t1.xq = t3.xqdm ) a left join  xg_gfjy_gfjyjcszb b on 1=1 ");
		sql.append(" where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_gfjy_gfjyqksqb");
	}

	public boolean isExist(GfjysqForm model) {
		String sql = "select count(1) num from xg_gfjy_gfjyqksqb where xn = ? and xq =? and xh=? and gfqkfl=? and shzt <> '2'" ;
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getXh(),model.getGfqkfl()}, "num");
		return Integer.valueOf(num)>0;
	}

	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_gfjy_gfjyjcszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}

	public boolean isExistforUpdate(GfjysqForm model) {
		String sql = "select count(1) num from xg_gfjy_gfjyqksqb where xn = ? and xq =? and xh=? and gfqkfl=? and shzt <> '2' and sqid <> ?" ;
		String num = dao.getOneRs(sql, new String[]{model.getXn(),model.getXq(),model.getXh(),model.getGfqkfl(),model.getSqid()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isCanDel(String sqid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select shzt from xg_gfjy_gfjyqksqb where sqid=? ");
		String shzt=dao.getOneRs(sb.toString(), new String[] {sqid}, "shzt");
		return null==shzt||shzt.equals("0")?true:false;
	}

	public HashMap<String, String> getGfjyqk(String sqid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, b.xm xm from xg_gfjy_gfjyqksqb a");
		sb.append(",view_xsxxb b where a.xh=b.xh and a.sqid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{sqid});
	}

	public boolean updateGfjyqk(GfjysqForm model) throws Exception {
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_gfjy_gfjyqksqb ");
		sql.append(" set shzt = ? ,splc = ? where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public HashMap<String,String> getGfjyqkInfo(GfjysqForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,decode(t1.gfqkfl,1,'兵役登记情况',2,'参军入伍情况',3,'退伍复学情况',4,'评奖评优',5,'奖惩情况',6,'参加活动情况') gfqkflmc,t3.xqmc");
		sql.append(" from xg_gfjy_gfjyqksqb t1 left join view_xsxxb t2 ");
		sql.append(" on t1.xh = t2.xh left join xqdzb t3 on t1.xq = t3.xqdm ");
		sql.append(" where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}
}
