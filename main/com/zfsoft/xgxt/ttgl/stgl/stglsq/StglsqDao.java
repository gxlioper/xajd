/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stglsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class StglsqDao extends SuperDAOImpl<StglsqForm>{

	@Override
	public List<HashMap<String, String>> getPageList(StglsqForm t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(StglsqForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,(select splc from xg_ttgl_stgljcszb) splcidnew");
		sql.append(" from ( select t1.*,t2.xm, (case t2.xb when '1' then  '男' when '2' then '女' else t2.xb end) xb,t2.xz,t2.rxrq, ");
		sql.append(" t2.sfzh,t2.bjmc,t2.lxdh,t2.xydm,t2.zydm,t2.bjdm,t2.xymc,t2.zymc,t2.nj, ");
		sql.append(" decode(t1.shzt, '0','未提交', '1', '通过','2','不通过', '3','已退回', '4','需重审', ");
		sql.append(" '5', '审核中', '','无需审核',t1.shzt) shztmc,t3.xm stzdlsxm ,t4.bmmc from xg_ttgl_stglsqb t1");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(" left join fdyxxb t3 on t1.stzdls=t3.zgh ");
		sql.append(" left join zxbz_xxbmdm t4 on t1.ywzddw = t4.bmdm  ");
		sql.append("  ) a where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_ttgl_stglsqb");
	}

	public boolean isExist(StglsqForm model) {
		String sql = "select count(1) num from xg_ttgl_stglsqb where xh = ? and stqc =? and stlx=? and shzt <> '2'" ;
		String num = dao.getOneRs(sql, new String[]{model.getXh(),model.getStqc(),model.getStlx()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isExistforUpdate(StglsqForm model) {
		String sql = "select count(1) num from xg_ttgl_stglsqb where  xh = ? and stqc =? and stlx=? and shzt <> '2' and sqid <> ?" ;
		String num = dao.getOneRs(sql, new String[]{model.getXh(),model.getStqc(),model.getStlx(),model.getSqid()}, "num");
		return Integer.valueOf(num)>0;
	}

	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_ttgl_stgljcszb");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}

	public boolean isCanDel(String sqid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select shzt from xg_ttgl_stglsqb where sqid=? ");
		String shzt=dao.getOneRs(sb.toString(), new String[] {sqid}, "shzt");
		return null==shzt||shzt.equals("0")?true:false;
	}

	public HashMap<String, String> getStsqxx(String sqid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, b.xm xm from xg_ttgl_stglsqb a");
		sb.append(",view_xsxxb b where a.xh=b.xh and a.sqid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{sqid});
	}

	public boolean updateStsq(StglsqForm model) throws Exception {
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_ttgl_stglsqb ");
		sql.append(" set shzt = ? ,splc = ? where sqid = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getSqid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}

	public HashMap<String, String> getStsqInfo(StglsqForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.xm zdlsxm,t3.bmmc,t4.mc as zzlbmc,t5.mc as ndzzztmc");
		sql.append(" from xg_ttgl_stglsqb t1 left join fdyxxb t2 on t1.stzdls = t2.zgh");
		sql.append(" left join zxbz_xxbmdm t3 on t1.ywzddw = t3.bmdm  ");
		sql.append(" left join XG_TTGL_ZZLBDMB t4 on t1.zzlb = t4.dm");
		sql.append(" left join XG_TTGL_NDZZZTDMB t5 on t1.ndzzzt = t5.dm");
		sql.append(" where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}

	public HashMap<String, String> getXsxx(String xh, String[] xhs) {
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select * from (select xh,xm,xb,xydm,a.bjdm,xymc,bjmc,nj,zymc,zydm,sjhm,c.symc from view_xsbfxx a left join xg_xtwh_sybjglb b on a.bjdm=b.bjdm");
		sql.append(" left join xg_xtwh_sydmb c on b.sydm=c.sydm ) t ");
		sql.append(" where 1=1 ");
		sql.append(" ");
		if(xhs != null){
			sql.append("and xh not in(");
			for (int i = 0; i < xhs.length; i++) {
				sql.append("?");
				if(xhs.length-1 != i ){
					sql.append(",");
				}
				paralist.add(xhs[i]);
			}
			sql.append(")");
		}
		sql.append(" and  xh = ?");
		paralist.add(xh);
		return dao.getMapNotOut(sql.toString(),paralist.toArray(new String[]{}));
	}

	public boolean saveFzrb(List<String[]> paraList) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_ttgl_stglfzrb(xh,sqid,fzrfz) values(?,?,?)");
		return dao.runBatchBoolean(sql.toString(), paraList);
	}

	public boolean saveTzsb(List<String[]> tzsList) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into XG_TTGL_STGLTZSB(xh,sqid) values(?,?)");
        return dao.runBatchBoolean(sql.toString(), tzsList);
    }

//    public boolean delZwInfo(String[] ids) throws Exception {
//		StringBuilder sql = new StringBuilder();
//		sql.append("delete from xg_ttgl_stzwglb where sqid in(");
//		for (int i = 0; i < ids.length; i++) {
//			sql.append("?");
//			if(i != ids.length-1){
//				sql.append(",");
//			}
//		}
//		sql.append(")");
//		return dao.runUpdate(sql.toString(),ids);
//	}
	public boolean delFzrbInfo(String[] ids) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_ttgl_stglfzrb where sqid in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(),ids );
	}

	public boolean delTzsbIfo(String[] ids) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from XG_TTGL_STGLTZSB where sqid in(");
        for (int i = 0; i < ids.length; i++) {
            sql.append("?");
            if(i != ids.length-1){
                sql.append(",");
            }
        }
        sql.append(")");
        return dao.runUpdate(sql.toString(),ids );
    }

    /**
     * 获取负责人信息
     * @param sqid
     * @return
     */
	public List<HashMap<String, String>> getFzrxx(String sqid) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,b.xm,b.xb,b.bjmc,b.sjhm,b.xymc,b.zymc,d.symc,a.fzrfz from  xg_ttgl_stglfzrb a");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh");
		sql.append(" left join xg_xtwh_sybjglb c on b.bjdm=c.bjdm");
		sql.append(" left join xg_xtwh_sydmb d on c.sydm=d.sydm ");
		sql.append(" where a.sqid = ?");
		return dao.getListNotOut(sql.toString(),new String[]{sqid});
	}

    /**
     * 获取团支书信息
     * @param sqid
     * @return
     */
	public List<HashMap<String, String>> getTzsxx(String sqid){
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.xh,b.xm,b.xb,b.bjmc,b.sjhm,b.xymc,b.zymc,d.symc from  XG_TTGL_STGLTZSB a");
        sql.append(" left join view_xsbfxx b on a.xh = b.xh");
        sql.append(" left join xg_xtwh_sybjglb c on b.bjdm=c.bjdm");
        sql.append(" left join xg_xtwh_sydmb d on c.sydm=d.sydm ");
        sql.append(" where a.sqid = ?");
        return dao.getListNotOut(sql.toString(),new String[]{sqid});
    }

	public boolean delFzrb(String sqid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_ttgl_stglfzrb where sqid = ?");
		return dao.runUpdate(sql.toString(),new String[]{sqid});
	}

	public boolean delTzs(String sqid) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from XG_TTGL_STGLTZSB where sqid = ?");
        return dao.runUpdate(sql.toString(),new String[]{sqid});
    }
	public HashMap<String, String> getXsjbxxMore(String xh) {
		StringBuilder sql = new StringBuilder();
		ArrayList<String> paralist = new ArrayList<String>();
		sql.append(" select * from (select xh,xm,xb,xydm,a.bjdm,xymc,bjmc,nj,zymc,zydm,sjhm,c.symc from view_xsbfxx a left join xg_xtwh_sybjglb b on a.bjdm=b.bjdm");
		sql.append(" left join xg_xtwh_sydmb c on b.sydm=c.sydm ) t ");
		sql.append(" where 1=1 ");
		sql.append(" and  xh = ?");
		paralist.add(xh);
		return dao.getMapNotOut(sql.toString(),paralist.toArray(new String[]{}));
	}

//	public boolean saveZW(List<String[]> zwList) throws SQLException {
//		StringBuilder sql = new StringBuilder();
//		sql.append(" insert into xg_ttgl_stzwglb(sqid,zwmc,zwms,rssx,sjzw) values(?,?,?,?,?)");
//		return dao.runBatchBoolean(sql.toString(), zwList);
//	}

	public boolean saveStcyb(List<String[]> rxxList) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_ttgl_stcyb(xh,jgid,shzt,tnzw,sjly) values(?,?,?,?,?)");
		return dao.runBatchBoolean(sql.toString(), rxxList);
	}

	public boolean delStcyb(String jgid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_ttgl_stcyb where jgid = ?");
		return dao.runUpdate(sql.toString(),new String[]{jgid});
	}

	public boolean delStcyInfo(String[] ids) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_ttgl_stcyb where jgid in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			if(i != ids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(),ids );
	}

}
