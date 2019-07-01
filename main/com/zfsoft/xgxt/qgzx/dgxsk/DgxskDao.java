package com.zfsoft.xgxt.qgzx.dgxsk;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-07-06 09:49
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DgxskDao extends SuperDAOImpl<DgxskForm> {
    @Override
    protected void setTableInfo() {
        /*super.setTableName("xg_qgzx_xsgwsqb");
        super.setKey("sqbh");
        super.setClass(DgxskForm.class);*/
    }

    @Override
    public List<HashMap<String, String>> getPageList(DgxskForm dgxskForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(DgxskForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (");
        sql.append(" select t1.*,t2.xb,t2.xm,t2.xymc,t2.zymc,t2.bjmc,t2.xydm,t2.zydm,t2.bjdm from ( ");
        sql.append(" (select a.xh,b.gwmc, ");
        sql.append(" decode(a.shzt,'3', '已退回', '5', '审核中', a.shzt) ztmc, ");
        sql.append(" a.shzt zt,b.gwdm,a.sqbh,a.splc ");
        sql.append(" from XG_QGZX_XSGWSQB a ");
        sql.append(" left join view_xg_qgzx_gwxxb b on b.gwdm=a.gwdm ");
        sql.append(" where shzt='5' or shzt='3')   ");
        sql.append(" union all ");
        sql.append(" select xh,'' gwmc,'待岗中' ztmc,'-5' zt,'' gwdm,'' sqbh,'' splc from XGXT_QGZX_DGXSBMB where dgzt='1') t1 ");
        sql.append(" left join view_xsjbxx t2 on t1.xh=t2.xh ");
        sql.append(" where t1.xh not in ");
        sql.append(" (select xh from xg_qgzx_xsgwxxb t3 ");
        sql.append(" left join view_xg_qgzx_gwxxb t4 on t4.gwdm=t3.gwdm ");
        sql.append(" where t3.zgzt = 'zg'  and t4.sfyxgw='是' ) ");
        sql.append(" order by t1.xh ");
        sql.append(" ) t where 1 = 1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }
}
