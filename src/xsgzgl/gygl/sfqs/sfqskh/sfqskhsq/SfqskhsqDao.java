package xsgzgl.gygl.sfqs.sfqskh.sfqskhsq;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class SfqskhsqDao extends SuperDAOImpl<SfqskhsqForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(SfqskhsqForm.class);
        super.setKey("sqid");
        super.setTableName("XG_GYGL_SFQSKHSQB");
    }

    @Override
    public List<HashMap<String, String>> getPageList(SfqskhsqForm t) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(SfqskhsqForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append(" select a.*,b.xm,c.ldmc ");
        sql.append(" ,decode(a.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����','','�������',a.shzt) shztmc ");
        sql.append(" from XG_GYGL_SFQSKHSQB a ");
        sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
        sql.append(" left join XG_GYGL_NEW_LDXXB c on a.lddm = c.lddm");
        sql.append(" where a.xh= '"+user.getUserName()+"'");
        sql.append(") t where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }
    public List<HashMap<String,String>> getShList(SfqskhsqForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append(" select a.*,b.xm,c.ldmc,t4.shzt shztx,t4.guid shid,t4.gwid ");
        sql.append(" ,t6.mc || '[' || ");
        sql.append(" decode(t4.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,");
        sql.append(" row_number() over(partition by a.sqid order by t4.shsj desc) rn ");
        sql.append(" from XG_GYGL_SFQSKHSQB a ");
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

    public String getSplc(){
        StringBuilder sql = new StringBuilder();
        sql.append("select splc from XG_GYGL_SFQSJCSZB ");
        return dao.getOneRs(sql.toString(),new String[]{},"splc");
    }
    //��������
    public boolean isExist(SfqskhsqForm t){
        StringBuilder sql = new StringBuilder();
        sql.append("select 1 s from XG_GYGL_SFQSKHSQB where lddm = ? and qsh = ? and xn = ?");
        String s = dao.getOneRs(sql.toString(),new String[]{t.getLddm(),t.getQsh(),t.getXn()},"s");
        return !StringUtils.isNull(s);
    }
    //�Ƿ��Ѵ���ʾ������
    public boolean sfSfqs(SfqskhsqForm t){
        StringBuilder sql = new StringBuilder();
        sql.append("select 1 s from XG_GYGL_SFQSCJJGB where lddm = ? and qsh = ? and xn = ?");
        String s = dao.getOneRs(sql.toString(),new String[]{t.getLddm(),t.getQsh(),t.getXn()},"s");
        return !StringUtils.isNull(s);
    }
    public boolean delJg(String sqid) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_GYGL_SFQSKHJGB where sqid = ?");
        return dao.runUpdate(sql.toString(),new String[]{sqid});
    }

    @Override
    public SfqskhsqForm getModel(SfqskhsqForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.ldmc from XG_GYGL_SFQSKHSQB a");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm ");
        sql.append(" where a.sqid = ?");
        return getModel(t,sql.toString(),new String[]{t.getSqid()});
    }

    public List<HashMap<String,String>> getQscyList(String lddm,String qsh,String xn){
        String sql = "select a.xh,b.xm from XG_GYGL_SFQSCYB a " +
                "left join view_xsjbxx b on a.xh = b.xh " +
                "where lddm=? and qsh=? and xn=?";
        return dao.getListNotOut(sql,new String[]{lddm,qsh,xn});
    }

    public HashMap<String,String> getMap(String id){
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct a.*,b.ldmc,d.symc from XG_GYGL_SFQSKHSQB a");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm ");
        sql.append(" left join xg_gygl_new_qsxxb c on a.lddm = c.lddm");
        sql.append(" left join XG_XTWH_SYDMB d on c.xydm = d.sydm");
        sql.append(" where a.sqid = ?");
        return dao.getMapNotOut(sql.toString(),new String[]{id});
    }
}
