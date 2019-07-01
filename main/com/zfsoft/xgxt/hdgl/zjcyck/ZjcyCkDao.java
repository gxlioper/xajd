package com.zfsoft.xgxt.hdgl.zjcyck;

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
public class ZjcyCkDao extends SuperDAOImpl<ZjcyCkForm> {
    @Override
    protected void setTableInfo() {
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZjcyCkForm zjcyCkForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZjcyCkForm t, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();

        sql.append("select t.* from (");
        sql.append(" select distinct a.jdid,a.zgh,nvl(b1.hdmc,c1.hdmc) hdmc,d.jdmc,e.xm,nvl(b1.hdid,c1.hdid) hdid");
        sql.append(" from XG_HDGL_ZPZJGXB a");
        sql.append(" left join XG_HDGL_HDRYB b on a.hdsqid = b.sqid ");
        sql.append(" left join XG_HDGL_HDXXB b1 on b.hdid = b1.hdid ");
        sql.append(" left join XG_HDGL_ZDHDRYB c on a.hdsqid = c.sqid ");
        sql.append(" left join XG_HDGL_HDXXB c1 on c.hdid = c1.hdid ");
        sql.append(" left join XG_HDGL_JDGLB d on a.jdid = d.jdid");
        sql.append(" left join fdyxxb e on a.zgh = e.zgh");
        sql.append(") t where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }
}
