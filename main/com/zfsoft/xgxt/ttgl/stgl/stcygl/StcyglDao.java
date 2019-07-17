/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stcygl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class StcyglDao extends SuperDAOImpl<StcyglForm>{
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public List<HashMap<String, String>> getPageList(StcyglForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(StcyglForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( select a.jgid,a.stlx,a.stqc,a.stjc,a.styx,a.ywzddw,a.stzdls,a.stjs,a.gzh,decode(a.stzt,0,'预备期',1,'正式') stzt,a.sjly,'0' ly,(select count(1) from XG_TTGL_STCYB where jgid=a.jgid and shzt='1') strs from xg_ttgl_stgljgb a where a.stzt <>'2' "); 
		sql.append(" union all ");
		sql.append(" select sqid jgid,stlx, stqc, stjc, styx,ywzddw, stzdls, stjs,gzh,case when shzt='3' then '新社团退回' else '新社团待审核' end stzt, '1' sjly,'1' ly,(select count(1)from XG_TTGL_STCYB where jgid = b.sqid and shzt = '1') strs  from xg_ttgl_stglsqb b  where b.shzt not in( '1','2') ");
		sql.append(" )t where 1 = 1 "); 
		if("stu".equalsIgnoreCase(user.getUserStatus())){
			sql.append(" and exists (select 1 from XG_TTGL_STCYB k where  k.jgid=t.jgid and xh='"+user.getUserName()+"' and tnzw='负责人') ");
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
		
	}

	@Override
	protected void setTableInfo() {
		// TODO Auto-generated method stub
		
	}

	public HashMap<String, String> getStxxInfo(StcyglForm myForm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xm zdlsxm,t3.bmmc,t4.mc as zzlbmc,t5.mc as ndzzztmc from ");
		if ("0".equals(myForm.getLy())||myForm.getLy()==null) {
			sql.append(" xg_ttgl_stgljgb t1");
			sql.append(" left join fdyxxb t2 on t1.stzdls = t2.zgh");
			sql.append(" left join zxbz_xxbmdm t3 on t1.ywzddw = t3.bmdm  ");
			sql.append(" left join XG_TTGL_ZZLBDMB t4 on t1.zzlb = t4.dm");
			sql.append(" left join XG_TTGL_NDZZZTDMB t5 on t1.ndzzzt = t5.dm");
			sql.append(" where t1.jgid = ? ");
		}else {
			sql.append(" xg_ttgl_stglsqb t1");
			sql.append(" left join fdyxxb t2 on t1.stzdls = t2.zgh");
			sql.append(" left join zxbz_xxbmdm t3 on t1.ywzddw = t3.bmdm  ");
			sql.append(" left join XG_TTGL_ZZLBDMB t4 on t1.zzlb = t4.dm");
			sql.append(" left join XG_TTGL_NDZZZTDMB t5 on t1.ndzzzt = t5.dm");
			sql.append(" where t1.sqid = ? ");
		}
		return dao.getMapNotOut(sql.toString(), new String[]{myForm.getJgid()});
	}

	public List<HashMap<String, String>> getFzrxx(StcyglForm myForm) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,b.xm,b.xb,b.bjmc,b.sjhm,b.xymc,b.zymc,d.symc,a.fzrfz from xg_ttgl_stglfzrb a");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_xtwh_sybjglb c on b.bjdm=c.bjdm");
		sql.append(" left join xg_xtwh_sydmb d on c.sydm=d.sydm");
		if ("0".equals(myForm.getSjly())) {
			sql.append(" where a.jgid = ?");
		}else {
			sql.append(" where a.sqid = ?");
		}
		return dao.getListNotOut(sql.toString(),new String[]{myForm.getJgid()});
	}

    public List<HashMap<String, String>> getTzsxx(StcyglForm myForm) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.xh,b.xm,b.xb,b.bjmc,b.sjhm,b.xymc,b.zymc,d.symc from XG_TTGL_STGLTZSB a");
        sql.append(" left join view_xsbfxx b on a.xh = b.xh");
        sql.append(" left join xg_xtwh_sybjglb c on b.bjdm=c.bjdm");
        sql.append(" left join xg_xtwh_sydmb d on c.sydm=d.sydm");
        if ("0".equals(myForm.getSjly())) {
            sql.append(" where a.jgid = ?");
        }else {
            sql.append(" where a.sqid = ?");
        }
        return dao.getListNotOut(sql.toString(),new String[]{myForm.getJgid()});
    }

	public List<HashMap<String, String>> getStRyList(StcyglForm t, User user) throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.xh,a.guid,a.jgid,a.sqly,a.tc,a.sqsj,decode(a.shzt,0,'待审核',1,'已通过',2,'已拒绝') shzt,a.tnzw,b.xm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,d.sydm,d.symc from xg_ttgl_stcyb a  ");
		sql.append(" left join view_xsbfxx b on a.xh=b.xh  ");
		sql.append(" left join xg_xtwh_sybjglb c on b.bjdm=c.bjdm ");
		sql.append(" left join xg_xtwh_sydmb d on c.sydm=d.sydm "); 
		if ("dsh".equals(t.getGlzt())) {
			sql.append(" where  a.shzt='0' ");
		}else {
			sql.append(" where  a.shzt<>'0'  ");
		}
		sql.append(" and a.jgid='"+t.getJgid()+"')");
		sql.append(" t where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputValue);
	}

	public boolean saveRysh(String shzt, String[] guidArr) throws Exception {
		String[] sqlArr = new String[guidArr.length];
		if ("1".equals(shzt)) {
			for(int i = 0;i<guidArr.length;i++){
				StringBuilder sql = new StringBuilder();
				sql.append(" update xg_ttgl_stcyb set shzt='"+shzt+"',shsj='"+df.format(new Date())+"',tnzw='成员',sjly='2' where guid='"+guidArr[i]+"'  ");
				sqlArr[i]=sql.toString();
			}
		}else {
			for(int i = 0;i<guidArr.length;i++){
				StringBuilder sql = new StringBuilder();
				sql.append(" update xg_ttgl_stcyb set shzt='"+shzt+"',shsj='"+df.format(new Date())+"',sjly='2' where guid='"+guidArr[i]+"'  ");
				sqlArr[i]=sql.toString();
			}
		}
		int[] num =dao.runBatch(sqlArr);
		return dao.checkBatch(num);
	}

	public HashMap<String, String> getSqxxInfo(StcyglForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select *");
		sql.append(" from xg_ttgl_stcyb a");
		sql.append(" where a.guid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getGuid()});
	}

	public boolean updateSqbZzShzt(StcyglForm model) throws Exception {
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_ttgl_stglsqb set zzshzt = '5',zzsqsj='"+df.format(new Date())+"',zzly=? where sqid = ?");
		inputV[0] = model.getZzly();
		inputV[1] = model.getJgid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public String getZzywid(String jgid) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select zzywid from xg_ttgl_stglsqb where sqid=?");
		return dao.getOneRs(sql.toString(), new String[] {jgid}, "zzywid");
	}

}
