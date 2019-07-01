package com.zfsoft.xgxt.sxzzjygl.ztbhgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class ZtbhShDao extends SuperDAOImpl<ZtbhShForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(ZtbhShForm.class);
        super.setKey("sqid");
        super.setTableName("xg_sxzzjy_ztbh_ztbhsqb");

    }

    @Override
    public List<HashMap<String, String>> getPageList(ZtbhShForm ztbhShForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZtbhShForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* from (");

        sql.append("select t1.*, t4.guid shid,t4.gwid,t4.shr,t4.shyj, ");
        sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc, ");
        sql.append("t6.gwz,t2.bjmc, ");
        sql.append("row_number() over(partition by t1.sqid order by t4.shsj desc) rn  ");
        sql.append("from xg_sxzzjy_ztbh_ztbhsqb t1  ");
        sql.append("left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
        sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid ");
        sql.append("left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw ");
        sql.append("left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
        sql.append(" where t5.spyh ='"+ user.getUserName() + "' ");
        String shlx = t.getShlx();
        if (!shlx.equals("dsh")) {
            sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
        } else {
            sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
        }
        sql.append(" ) t where 1=1 ");
        sql.append(" and  rn = 1 ");
        sql.append(searchTj);
        sql.append(searchTjByUser);
        return getPageList(t, sql.toString(), inputV);

    }



    public ZtbhShForm getInfo(String sqid) throws Exception{
        String sql = ("select t1.* , t2.bjmc , t3.xm as hdfzr from xg_sxzzjy_ztbh_ztbhsqb t1 left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm  left join view_xsjbxx t3 on t1.hdfzr = t3.xh  where t1.sqid = ?  ");
        return super.getModel(sql, new String[]{sqid});
    }

    public List<HashMap<String,String>> getZtbhInfo(String sqid) {
        String sql = ("select t1.* , t2.bjmc , t3.xm as hdfzr from xg_sxzzjy_ztbh_ztbhsqb t1 left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm  left join view_xsjbxx t3 on t1.hdfzr = t3.xh  where t1.sqid = ?  ");
        return dao.getListNotOut(sql,new String[]{sqid});
    }
}
