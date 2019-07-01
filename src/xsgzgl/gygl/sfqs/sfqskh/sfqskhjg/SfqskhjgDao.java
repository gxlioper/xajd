package xsgzgl.gygl.sfqs.sfqskh.sfqskhjg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.sfqs.sfqscj.sfqscjjg.SfqscjjgForm;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class SfqskhjgDao extends SuperDAOImpl<SfqskhjgForm>{
    @Override
    protected void setTableInfo() {
        super.setClass(SfqskhjgForm.class);
        super.setKey("sqid");
        super.setTableName("XG_GYGL_SFQSKHJGB");
    }

    @Override
    public List<HashMap<String, String>> getPageList(SfqskhjgForm t) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(SfqskhjgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append(" select a.*,b.xm,c.ldmc ");
        sql.append(" from XG_GYGL_SFQSKHJGB a ");
        sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
        sql.append(" left join XG_GYGL_NEW_LDXXB c on a.lddm = c.lddm");
        sql.append(") t where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public List<HashMap<String,String>> export(SfqskhjgForm t)throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append(" select distinct a.*,a1.sblx,d.xh cyxh,e.xm,c.ldmc,f.symc ");
        sql.append(",case when h.xh is not null then '是' else '否' end  sfqsz");
        sql.append(" from XG_GYGL_SFQSKHJGB a ");
        sql.append(" left join XG_GYGL_SFQSCJJGB a1 on a.lddm = a1.lddm and a.qsh = a1.qsh and a.xn = a1.xn ");
        sql.append(" left join XG_GYGL_NEW_LDXXB c on a.lddm = c.lddm");
        sql.append(" left join XG_GYGL_NEW_QSXXB g on c.lddm = g.lddm");
        sql.append(" left join XG_XTWH_SYDMB f on g.xydm = f.sydm ");
        sql.append(" left join XG_GYGL_SFQSCYB d on a.lddm = d.lddm and a.qsh = d.qsh and a.xn = d.xn ");
        sql.append(" left join view_xsjbxx e on d.xh = e.xh ");
        sql.append(" left join xg_gygl_new_gyglryb h on d.lddm = h.lddm and d.xh = h.xh and d.qsh = h.qsh and h.rzzt = '在任'");
        sql.append(") t where 1=1 ");
        sql.append(searchTj);
        return dao.getListNotOut(sql.toString(),inputV);
    }
}
