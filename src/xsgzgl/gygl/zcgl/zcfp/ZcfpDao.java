package xsgzgl.gygl.zcgl.zcfp;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class ZcfpDao extends SuperDAOImpl<ZcfpForm>{
    @Override
    protected void setTableInfo() {
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZcfpForm zcfpForm) throws Exception {
        return null;
    }

    //楼栋资产数量查询
    @Override
    public List<HashMap<String, String>> getPageList(ZcfpForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append(" select a.lddm,a.ldmc,b.num,b.lxdm,c.mc,d.mc lxmc ");
        sql.append(" from xg_gygl_new_ldxxb a ");
        sql.append(" left join ");
        sql.append(" (select nvl(sum(to_number(sl)),0) num,lddm,zcid,lxdm from XG_GYGL_ZCSLB group by lddm,zcid,lxdm ) b on a.lddm = b.lddm ");
        sql.append(" left join xg_gygl_zcxxb c on b.zcid = c.id ");
        sql.append(" left join XG_GYGL_ZCLXDBB d on b.lxdm = d.dm");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public List<HashMap<String, String>> getLcZcslList(ZcfpForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append(" select a.lddm,a.ldmc,e.ch,b.num,b.lxdm,c.mc,d.mc lxmc,a.lddm || '@!!!' || e.ch pk ");
        sql.append(" from (select lddm,ch from XG_GYGL_NEW_QSXXB group by lddm,ch) e");
        sql.append(" left join xg_gygl_new_ldxxb a on e.lddm = a.lddm ");
        sql.append(" left join ");
        sql.append(" (select nvl(sum(to_number(sl)),0) num,lddm,ch,zcid,lxdm from XG_GYGL_ZCSLB group by lddm,zcid,lxdm,ch ) b on e.lddm = b.lddm and e.ch = b.ch");
        sql.append(" left join xg_gygl_zcxxb c on b.zcid = c.id ");
        sql.append(" left join XG_GYGL_ZCLXDBB d on b.lxdm = d.dm");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public List<HashMap<String, String>> getQsZcslList(ZcfpForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append(" select a.lddm,a.ldmc,e.ch,e.qsh,b.sl num,b.lxdm,c.mc,d.mc lxmc,a.lddm || '@!!!' || e.qsh pk ");
        sql.append(" from XG_GYGL_NEW_QSXXB e");
        sql.append(" left join xg_gygl_new_ldxxb a on e.lddm = a.lddm ");
        sql.append(" left join XG_GYGL_ZCSLB b on e.lddm = b.lddm and e.qsh = b.qsh");
        sql.append(" left join xg_gygl_zcxxb c on b.zcid = c.id ");
        sql.append(" left join XG_GYGL_ZCLXDBB d on b.lxdm = d.dm");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public boolean save(List<FpFrom> data) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" MERGE INTO XG_GYGL_ZCSLB a ");
        sql.append(" USING (");
        for(int i=0;i<data.size();i++){
            FpFrom item = data.get(i);
            sql.append("select '"+item.getLddm()+"' lddm,'"+item.getCh()+"' ch,'"+item.getQsh()+"' qsh,'"+item.getLxdm()+"' lxdm,'"+item.getZcid()+"' zcid,'"+item.getSl()+"' sl,'"+item.getBz()+"' bz from dual ");
            if(i<data.size()-1){
                sql.append(" union ");
            }
        }
        sql.append(" ) b ON (a.lddm = b.lddm and a.ch = b.ch and a.qsh = b.qsh) ");
        sql.append(" WHEN matched THEN UPDATE set a.lxdm=b.lxdm,a.zcid=b.zcid,a.sl=b.sl,a.bz=b.bz ");
        sql.append(" WHEN NOT matched THEN INSERT (lddm,ch,qsh,lxdm,zcid,sl,bz) VALUES (b.lddm,b.ch,b.qsh,b.lxdm,b.zcid,b.sl,b.bz)");
        return dao.runUpdate(sql.toString(),new String[]{});
    }

    public boolean del(String[] pks) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_GYGL_ZCSLB where ");
        String[] pk;
        for(int i=0;i<pks.length;i++){
            pk = pks[i].split("@!!!");
            sql.append("(lddm = '"+pk[0]+"' and qsh = '"+pk[1]+"') ");
            if(i<pks.length-1){
                sql.append(" or ");
            }
        }
        return dao.runUpdate(sql.toString(),new String[]{});
    }

    public ZcfpForm getQsxx(String keyValue) throws Exception {
        String[] key = keyValue.split("@!!!");
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.xqdm from XG_GYGL_ZCSLB a left join xg_gygl_new_ldxxb on a.lddm = b.lddm where a.lddm = ? and a.qsh = ?");
        return getModel(sql.toString(),key);
    }

    public List<HashMap<String,String>> getQsZclist(String lddm,String qsh){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_GYGL_ZCSLB where lddm = ? and qsh = ?");
        return dao.getListNotOut(sql.toString(),new String[]{lddm,qsh});
    }
}
