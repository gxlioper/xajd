package com.zfsoft.xgxt.szdw.thjl.thjlsh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：1601
 * @日期：
 */
public class SzdwThjlShDao extends SuperDAOImpl<SzdwThjlShForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(SzdwThjlShForm.class);
        super.setKey("sqid");
        super.setTableName("XG_SZDW_THJLSQB");
    }

    @Override
    public List<HashMap<String, String>> getPageList(SzdwThjlShForm szdwThjlShForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(SzdwThjlShForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.* from (");
        sql.append(" select a.*,b.xm,b.xb,b.nj,b.xymc,b.xydm,b.symc1 symc,b.sydm1 sydm,b.zydm,b.zymc,b.zybjmc,b.zybj,b.bjmc,b.bjdm ");
        sql.append(" ,t6.mc || '[' || ");
        sql.append(" decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,");
        sql.append(" t4.shzt shztx,t4.guid shid,t4.gwid,");
        sql.append(" row_number() over(partition by a.sqid order by t4.shsj desc) rn ");
        sql.append(" ,c.xm jsxm");
        sql.append(" from xg_szdw_thjlsqb a");
        sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
        sql.append(" left join FDYXXB c on a.zgh = c.zgh ");
        sql.append(" left join xg_xtwh_shztb t4 on a.sqid = t4.ywid ");
        sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw ");
        sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
        sql.append(" where t5.spyh = '"+user.getUserName()+"'");
        String shlx = t.getShzt();
        if (!shlx.equals("dsh")) {
            sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
        } else {
            sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
        }
        sql.append(" ) a where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public boolean delJg(String sqid) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_SZDW_THJL where sqid = ?");
        return dao.runUpdate(sql.toString(),new String[]{sqid});
    }
}
