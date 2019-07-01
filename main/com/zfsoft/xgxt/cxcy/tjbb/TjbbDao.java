package com.zfsoft.xgxt.cxcy.tjbb;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-10 08:54
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class TjbbDao extends SuperDAOImpl<TjbbForm> {
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(TjbbForm tjbbForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(TjbbForm t, User user) throws Exception {
        StringBuilder sql = new StringBuilder();
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm");
        String temp = org.apache.commons.lang.StringUtils.join(inputV, ",");
        if(StringUtils.isNotNull(temp))
               temp = temp + ",";
        String str = temp + temp + temp + temp + temp + temp;
        String[] parm = new String[]{};
        if(StringUtils.isNotNull(str))
                 str = str.substring(0,str.length()-1);
                 parm = str.split(",");
        sql.append("select t.*,");
        sql.append(" (select count(0) from (select xh,xn,xq,lrsj tbsj,lrr from XG_NEW_CXCY_BZJGB) a2 " );
        sql.append(" left join view_xsjbxx b2 on b2.xh=a2.xh where b2.xydm=t.xydm ");
        sql.append(searchTj);
        sql.append(" ) bzs ,");
        sql.append(" (select count(0) from xg_new_cxcy_jzsbb a3 where a3.xydm=t.xydm ");
        sql.append(searchTj);
        sql.append(" ) jzs ,");
        sql.append(" (select count(0) from xg_new_cxcy_xmsbb a4  where a4.xydm=t.xydm ");
        sql.append(searchTj);
        sql.append(" ) xms ");
        sql.append(" from ( ");
        sql.append(" select bmdm xydm,bmmc xymc from  ZXBZ_XXBMDM a where bmdm in  ");
        sql.append(" (select distinct b1.xydm from (select xh,xn,xq,lrsj tbsj,lrr from XG_NEW_CXCY_BZJGB) a1 ");
        sql.append(" left join view_xsjbxx b1 on b1.xh=a1.xh where 1=1 ");
        sql.append(searchTj);
        sql.append(" union select  distinct xydm from xg_new_cxcy_jzsbb  where 1=1 ");
        sql.append(searchTj);
        sql.append(" union select  distinct xydm from xg_new_cxcy_xmsbb  where 1=1 ");
        sql.append(searchTj);
        sql.append(" ) ) t  where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(" order by xymc ");
        return getPageList(t, sql.toString(),parm);
    }
}
