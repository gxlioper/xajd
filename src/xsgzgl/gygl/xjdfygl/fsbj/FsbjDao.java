package xsgzgl.gygl.xjdfygl.fsbj;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class FsbjDao extends SuperDAOImpl<FsbjForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("XG_FYGL_FSBJXXB");
        super.setClass(FsbjForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(FsbjForm fsbjForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(FsbjForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        // 高级查询输入值
        String[] inputV = SearchService.getTjInput(t.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select c.fsbj,c.xn,c.fsrq,b.ldmc,a.qsh,a.cws,a.lddm,a.lddm || '@!!' || a.qsh pk,d.xqmc ");
        sql.append(" from XG_GYGL_NEW_QSXXB a ");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm ");
        sql.append(" left join XG_FYGL_FSBJXXB c on a.lddm = c.lddm and a.qsh = c.qsh and c.xn = '"+ Base.currNd+"' ");
        sql.append(" left join T_XQDMB d on b.xqdm = d.dm ");
        sql.append(") t where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    //获取未粉刷寝室pk
    public List<String> getWfsQsgs() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.lddm || '@!!' || a.qsh pk  from XG_GYGL_NEW_QSXXB a ");
        sql.append(" where not exists (select 1 from XG_FYGL_FSBJXXB where lddm = a.lddm and qsh = a.qsh and xn = '"+Base.currNd+"')");
        return dao.getList(sql.toString(),new String[]{},"pk");
    }

    public boolean save(FsbjForm t,String[] pks) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_FYGL_FSBJXXB where (");
        for(int i=0;i<pks.length;i++){
            String[] pk = pks[i].split("@!!");
            sql.append("(xn='"+t.getXn()+"' and lddm = '"+pk[0]+"' and qsh = '"+pk[1]+"')");
            if(i<pks.length-1){
                sql.append(" or ");
            }
        }
        sql.append(")");
        dao.runUpdate(sql.toString(),new String[]{});

        sql = new StringBuilder();
        sql.append("insert all ");
        for(int i=0;i<pks.length;i++){
            String[] pk = pks[i].split("@!!");
            sql.append(" into XG_FYGL_FSBJXXB(xn,lddm,qsh,fsbj,fsrq,llr,llsj) values('"+t.getXn()+"','"+pk[0]+"'");
            sql.append(",'"+pk[1]+"','"+t.getFsbj()+"','"+t.getFsrq()+"','"+t.getLlr()+"','"+t.getLlsj()+"') ");
        }
        sql.append(" select 1 from dual ");
        return dao.runUpdate(sql.toString(),new String[]{});
    }
}
