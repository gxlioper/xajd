package com.zfsoft.xgxt.dtjs.rdjjfzpy.dkcj;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-02-27 10:58
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DkcjDao extends SuperDAOImpl<DkcjForm> {
    @Override
    protected void setTableInfo() {
        this.setKey("id");
        this.setTableName("XG_DTJS_DKCJB");
        this.setClass(DkcjForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(DkcjForm dkcjForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(DkcjForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
                "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());

        //StringBuffer sql = new StringBuffer(getBaseSql());

        StringBuffer sql  = new StringBuffer();
        sql.append("select t.* from(");
        sql.append("select a.*,t2.xm,t2.xb,t2.nj,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,");
        sql.append("t2.bjmc,t2.sydm1 sydm,t2.symc1 symc,t2.csrq,t2.mz,t2.zzmmmc,t2.zybj,t2.zybjmc ");
        sql.append("from XG_DTJS_DKCJB a ");
        sql.append(" left join view_xsjbxx t2 on t2.xh = a.xh ");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(" ");
        sql.append(searchTj);
        return this.getPageList(t, sql.toString(), inputV);
    }

    public HashMap<String,String> getInfo(String id) {
        StringBuffer sql  = new StringBuffer();
        sql.append("select t.* from(");
        sql.append("select a.*,t2.xm,t2.xb,t2.nj,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,");
        sql.append("t2.bjmc,t2.sydm1 sydm,t2.symc1 symc,t2.csrq,t2.mz,t2.zzmmmc, t2.zybj,t2.zybjmc");
        sql.append("from XG_DTJS_DKCJB a ");
        sql.append(" left join view_xsjbxx t2 on t2.xh = a.xh ");
        sql.append(" ) t where id=? ");
        return dao.getMapNotOut(sql.toString(),new String[]{id});
    }
}
