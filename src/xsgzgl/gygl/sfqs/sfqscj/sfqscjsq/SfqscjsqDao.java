package xsgzgl.gygl.sfqs.sfqscj.sfqscjsq;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xjdgybz.ktsq.JfxxForm;
import xsgzgl.gygl.xjdgybz.ktsq.KtsqForm;

import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class SfqscjsqDao extends SuperDAOImpl<SfqscjsqForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(SfqscjsqForm.class);
        super.setKey("sqid");
        super.setTableName("XG_GYGL_SFQSCJSQB");
    }

    @Override
    public List<HashMap<String, String>> getPageList(SfqscjsqForm sfqscjsqForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(SfqscjsqForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append(" select a.*,b.xm,c.ldmc ");
        sql.append(" ,decode(a.shzt,'0','未提交','1','通过','2','不通过','3','退回','4','需重审','5','审核中','','无需审核',a.shzt) shztmc ");
        sql.append(" from XG_GYGL_SFQSCJSQB a ");
        sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
        sql.append(" left join XG_GYGL_NEW_LDXXB c on a.lddm = c.lddm");
        sql.append(" where a.xh= '"+user.getUserName()+"'");
        sql.append(") t where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }
    public List<HashMap<String,String>> getShList(SfqscjsqForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append(" select a.*,b.xm,c.ldmc,t4.shzt shztx,t4.guid shid,t4.gwid ");
        sql.append(" ,t6.mc || '[' || ");
        sql.append(" decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,");
        sql.append(" row_number() over(partition by a.sqid order by t4.shsj desc) rn ");
        sql.append(" from XG_GYGL_SFQSCJSQB a ");
        sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
        sql.append(" left join XG_GYGL_NEW_LDXXB c on a.lddm = c.lddm");
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
        sql.append(") t where 1=1 and rn = 1 ");
        sql.append(searchTj);

        return getPageList(t,sql.toString(),inputV);
    }
    public boolean save(SfqscjsqForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_GYGL_SFQSJCSZB");
        dao.runUpdate(sql.toString(),new String[]{});
        sql = new StringBuilder();
        sql.append("insert into XG_GYGL_SFQSJCSZB(splc) values (?)");
        return dao.runUpdate(sql.toString(),new String[]{t.getSplc()});
    }

    public String getSplc(){
        StringBuilder sql = new StringBuilder();
        sql.append("select splc from XG_GYGL_SFQSJCSZB ");
        return dao.getOneRs(sql.toString(),new String[]{},"splc");
    }
    //存在申请
    public boolean isExist(SfqscjsqForm t){
        StringBuilder sql = new StringBuilder();
        sql.append("select 1 s from XG_GYGL_SFQSCJSQB where lddm = ? and qsh = ? and xn = ?");
        String s = dao.getOneRs(sql.toString(),new String[]{t.getLddm(),t.getQsh(),t.getXn()},"s");
        return !StringUtils.isNull(s);
    }
    public boolean delJg(String sqid) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_GYGL_SFQSCJJGB where sqid = ?");
        return dao.runUpdate(sql.toString(),new String[]{sqid});
    }

    @Override
    public SfqscjsqForm getModel(SfqscjsqForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.ldmc from XG_GYGL_SFQSCJSQB a");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm ");
        sql.append(" where a.sqid = ?");
        return getModel(t,sql.toString(),new String[]{t.getSqid()});
    }

    public boolean qscyBc(SfqscjsqForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_GYGL_SFQSCYB where lddm=? and qsh=? and xn=?");
        dao.runUpdate(sql.toString(),new String[]{t.getLddm(),t.getQsh(),t.getXn()});
        sql = new StringBuilder();
        sql.append("insert all ");
        for(QscyForm j : t.getQscyxx()){
            sql.append(" into XG_GYGL_SFQSCYB(lddm,qsh,xh,xn) values('"+t.getLddm()+"','"+t.getQsh()+"','"+j.getXh()+"','"+t.getXn()+"') ");
        }
        sql.append(" select 1 from dual ");
        return dao.runUpdate(sql.toString(),new String[]{});
    }
    public List<HashMap<String,String>> getQscyList(String lddm,String qsh,String xn){
        String sql = "select a.xh,b.xm from XG_GYGL_SFQSCYB a " +
                "left join view_xsjbxx b on a.xh = b.xh " +
                "where lddm=? and qsh=? and xn=?";
        return dao.getListNotOut(sql,new String[]{lddm,qsh,xn});
    }

    public boolean delCy(String[] ids) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_GYGL_SFQSCYB where (");
        for(int i=0;i<ids.length;i++){
            String[] key = ids[i].split("@!!");
            sql.append("(lddm = '"+key[0]+"' and qsh='"+key[1]+"' and xn='"+key[2]+"')");
            if(i<ids.length-1){
                sql.append(" or ");
            }
        }
        sql.append(")");
        return dao.runUpdate(sql.toString(),new String[]{});
    }

    public HashMap<String,String> getMap(String id){
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct a.*,b.ldmc,d.symc from XG_GYGL_SFQSCJSQB a");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm ");
        sql.append(" left join xg_gygl_new_qsxxb c on a.lddm = c.lddm");
        sql.append(" left join XG_XTWH_SYDMB d on c.xydm = d.sydm");
        sql.append(" where a.sqid = ?");
        return dao.getMapNotOut(sql.toString(),new String[]{id});
    }
}
