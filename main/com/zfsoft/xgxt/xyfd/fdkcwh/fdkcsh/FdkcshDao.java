package com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * 朋辈志愿者审核
 * Created by llf on 2019/7/31.
 */
public class FdkcshDao extends SuperDAOImpl<FdkcshForm>{
    @Override
    protected void setTableInfo() {
        super.setClass(FdkcshForm.class);
        super.setKey("sqid");
        super.setTableName("xg_xyfd_fdkcsqb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdkcshForm pbshForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdkcshForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* from (");
        sql.append("select t1.*,t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
        sql.append("decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t6.gwz, ");
        sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
        sql.append("from ( ");
        sql.append(" select a.*,c.xm,c.zgh yhm,d.* from xg_xyfd_fdkcsqb a left join xg_xyfd_fdjsxxb b on a.fdjs = b.djh left join fdyxxb c ");
        sql.append(" on b.zgh = c.zgh left join xg_xyfd_fdsxxb d on b.fds = d.id where a.fdjs like 'JS%' union ");
        sql.append(" select a.*,c.xm,c.xh yhm,d.* from xg_xyfd_fdkcsqb a left join xg_xyfd_pbjgb b on a.fdjs = b.djh left join  ");
        sql.append(" (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) c on b.xh = c.xh left join  ");
        sql.append(" xg_xyfd_fdsxxb d on b.fds = d.id where a.fdjs like 'PB%' ) t1 ");
        sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid");
        sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
        sql.append(" where t5.spyh ='"+ user.getUserName() + "' ");
        String shlx = t.getShzt();
        if (!shlx.equals("dsh")) {
            sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
        } else {
            sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
        }
        sql.append(" ) t where 1=1 ");
        sql.append(" and  rn = 1 ");
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }
}
