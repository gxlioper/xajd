package xsgzgl.gygl.xjdfygl.qsxxgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import org.apache.commons.lang.StringUtils;
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
public class QsxxglDao extends SuperDAOImpl<QsxxglForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(QsxxglForm.class);
        super.setTableName("XG_GYGL_NEW_QSXXB");
    }

    @Override
    public List<HashMap<String, String>> getPageList(QsxxglForm qsxxglForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(QsxxglForm qsxxglForm, User user) throws Exception {

        String searchTj = SearchService.getSearchTj(qsxxglForm.getSearchModel());
        // 高级查询输入值
        String[] inputV = SearchService.getTjInput(qsxxglForm.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select a.*,b.ldmc,a.lddm || '@!!!' || a.qsh pk ");
        sql.append(" ,(select count(1) from XG_GYGL_NEW_CWXXB x where x.lddm = a.lddm and x.qsh = a.qsh ) cwss");//统计床位表的床位数
        sql.append(" ,case when a.qsxb='1' then '男' when a.qsxb='2' then '女' else '混住' end qsxbmc");
        sql.append(",case when a.fjlx='01' then '宿舍' when a.fjlx='02' then '值班室' when a.fjlx='03' then '厕所' end fjlxmc");
        sql.append(",case when a.fjzx='1' then '东' when a.fjzx='2' then '南' when a.fjzx='3' then '西' when a.fjzx='4' then '北' else a.fjzx end fjzxmc");
        sql.append(",case when a.sfykt='1' then '是' else '否' end sfyktmc");
        sql.append(",case when a.sfywsj='1' then '是' else '否' end sfywsjmc");
        sql.append(",case when a.wsjwz='01' then '房间内部' when a.wsjwz='02' then '阳台' else a.wsjwz end wsjwzmc");
        sql.append(",case when a.wsjzx='1' then '西南角' when a.wsjzx='2' then '东南角' when a.wsjzx='3' then '西北角' when a.wsjzx='4' then '东北角' else a.wsjzx end wsjzxmc");
        sql.append(" from XG_GYGL_NEW_QSXXB a");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        return getPageList(qsxxglForm,sql.toString(),inputV);
    }

    @Override
    public QsxxglForm getModel(QsxxglForm qsxxglForm) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.xqdm,c.xqmc,b.ldmc from XG_GYGL_NEW_QSXXB a ");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm ");
        sql.append(" left join T_XQDMB c on b.xqdm = c.dm ");
        sql.append("  where a.qsh = ? and a.lddm = ? ");
        return super.getModel(qsxxglForm,sql.toString(),new String[]{qsxxglForm.getQsh(),qsxxglForm.getLddm()});
    }

    @Override
    public boolean runUpdate(QsxxglForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("update XG_GYGL_NEW_QSXXB set cws=?,sfbz=?,fjlx=?,fjzx=?,qsxb=?,qsdh=?,sfykt=?,sfywsj=?,wsjwz=?,wsjzx=?,bz=?");
        sql.append(" where lddm = ? and qsh = ?");
        String[] param = new String[]{t.getCws(),t.getSfbz(),t.getFjlx(),t.getFjzx(),t.getQsxb(),t.getQsdh(),
        t.getSfykt(),t.getSfywsj(),t.getWsjwz(),t.getWsjzx(),t.getBz(),t.getLddm(),t.getQsh()};
        return dao.runUpdate(sql.toString(),param);
    }

    public boolean delete(String[] pk) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_GYGL_NEW_QSXXB where (");
        for(int i=0;i<pk.length;i++){
            String[] para = pk[i].split("@!!!");
            sql.append(" (lddm = '"+para[0]+"' and qsh = '"+para[1]+"')");
            if(i<pk.length-1){
                sql.append(" or ");
            }
        }
        sql.append(")");
        return dao.runUpdate(sql.toString(),new String[]{});
    }

    public boolean deleteQscw(String[] pk) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_GYGL_NEW_CWXXB where (");
        for(int i=0;i<pk.length;i++){
            String[] para = pk[i].split("@!!!");
            sql.append(" (lddm = '"+para[0]+"' and qsh = '"+para[1]+"')");
            if(i<pk.length-1){
                sql.append(" or ");
            }
        }
        sql.append(")");
        return dao.runUpdate(sql.toString(),new String[]{});
    }
    public boolean batchInsert(List<String[]> params) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into XG_GYGL_NEW_QSXXB(lddm,qsh,ch,qsxb,cws,sfbz,sfykt,sfywsj) values(?,?,?,?,?,?,?,?)");
        return dao.runBatchBoolean(sql.toString(),params);
    }


    public boolean batchUpdate(List<QsxxglForm> qsxxs,String lddm) throws Exception {
        StringBuilder sql = new StringBuilder();

        sql.append(" MERGE INTO XG_GYGL_NEW_QSXXB a ");
        sql.append(" USING (");
        for(int i=0;i<qsxxs.size();i++){
            sql.append(" select '"+lddm+"' lddm,'"+qsxxs.get(i).getCh()+"' ch,'"+qsxxs.get(i).getQsxb()+"'qsxb from dual ");
            if(i<qsxxs.size()-1){
                sql.append(" union ");
            }
        }
        sql.append(" ) b ON (a.lddm = b.lddm and a.ch = b.ch) ");
        sql.append(" WHEN matched THEN UPDATE set a.qsxb = b.qsxb");
        return dao.runUpdate(sql.toString(),new String[]{});
    }

    public boolean plxgqs(QsxxglForm model)throws Exception {
        String[] pks = model.getPks().split(",");
        StringBuilder sql = new StringBuilder();
        sql.append("MERGE INTO XG_GYGL_NEW_QSXXB a ");
        sql.append(" USING ( ");
        for(int i=0;i<pks.length;i++){
            String[] pk = pks[i].split("@!!!");//pk[0] lddm;pk[1] qsh
            sql.append("select '"+pk[0]+"' lddm,'"+pk[1]+"' qsh,'"+model.getCws()+"' cws,'"+model.getSfbz()+"' sfbz,");
            sql.append(" '"+model.getFjzx()+"' fjzx,'"+model.getSfykt()+"' sfykt,'"+model.getSfywsj()+"' sfywsj,'"+model.getFjlx()+"' fjlx from dual ");
            if(i<pks.length-1){
                sql.append(" union ");
            }
        }
        sql.append(" ) b ON (a.lddm = b.lddm and a.qsh = b.qsh )");
        sql.append(" WHEN matched THEN UPDATE set ");
        if(StringUtils.isNotEmpty(model.getCws())){
            sql.append(" a.cws = b.cws,");
        }
        if(StringUtils.isNotEmpty(model.getSfbz())){
            sql.append(" a.sfbz = b.sfbz,");
        }
        if(StringUtils.isNotEmpty(model.getFjzx())){
            sql.append(" a.fjzx = b.fjzx,");
        }
        if(StringUtils.isNotEmpty(model.getSfykt())){
            sql.append(" a.sfykt = b.sfykt,");
        }
        if(StringUtils.isNotEmpty(model.getSfywsj())){
            sql.append(" a.sfywsj = b.sfywsj,");
        }
        if(StringUtils.isNotEmpty(model.getFjlx())){
            sql.append(" a.fjlx = b.fjlx");
        }
        String sqlstr = sql.toString();
        if(sqlstr.endsWith(",")){
            sqlstr = sqlstr.substring(0,sqlstr.length()-1);
        }
        return dao.runUpdate(sqlstr,new String[]{});
    }

    public List<HashMap<String,String>> getQscwList(String lddm,String qsh){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.ldmc,c.xm,a1.qsxb,decode(a1.qsxb,'1','男','2','女',a1.qsxb) qsxbmc ");
        sql.append(" ,decode(a.sfbl,'1','是','0','否',a.sfbl) sfblmc");
        sql.append(" from XG_GYGL_NEW_CWXXB a");
        sql.append(" left join XG_GYGL_NEW_QSXXB a1 on a.lddm = a1.lddm and a.qsh = a1.qsh ");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm ");
        sql.append(" left join view_xsjbxx c on a.xh = c.xh");
        sql.append("  where a.qsh = ? and a.lddm = ? order by cwh");
        return dao.getListNotOut(sql.toString(),new String[]{qsh,lddm});
    }

    public boolean delQsCw(String lddm,String qsh,String cwh) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_GYGL_NEW_CWXXB where lddm=? and qsh=? and cwh=?");
        return dao.runUpdate(sql.toString(),new String[]{lddm,qsh,cwh});
    }
    public boolean addQsCw(String lddm,String qsh,String cwh) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into XG_GYGL_NEW_CWXXB(lddm,qsh,cwh) values(?,?,?)");
        return dao.runUpdate(sql.toString(),new String[]{lddm,qsh,cwh});
    }

    public boolean isExistCwh(String lddm,String qsh,String cwh){
        StringBuilder sql = new StringBuilder();
        sql.append("select 1 isExist from XG_GYGL_NEW_CWXXB where lddm=? and qsh=? and cwh=?");
        String s = dao.getOneRs(sql.toString(),new String[]{lddm,qsh,cwh},"isExist");
        return StringUtils.isNotEmpty(s);
    }

    public List<String> getAllWrzQsList() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct a.lddm || '@!!!' || a.qsh pk");
        sql.append(" from XG_GYGL_NEW_QSXXB a");
        sql.append(" left join (SELECT xh,lddm,qsh from XG_GYGL_NEW_CWXXB where xh is not null) b on a.lddm = b.lddm and a.qsh = b.qsh ");
        sql.append(" where xh is null");
        return dao.getList(sql.toString(),new String[]{},"pk");
    }

    public boolean qssscsh(String[] pks) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("MERGE INTO XG_GYGL_NEW_QSXXB a ");
        sql.append(" USING ( ");
        String[] pk;
        for(int i=0;i<pks.length;i++){
            pk = pks[i].split("@!!!");
            sql.append("select '"+pk[0]+"' lddm,'"+pk[1]+"' qsh from dual ");
            if(i<pks.length-1){
                sql.append(" union ");
            }
        }
        sql.append(" ) b ON (a.lddm = b.lddm and a.qsh = b.qsh )");
        sql.append(" WHEN matched THEN UPDATE set a.xydm = '',a.nj = ''");
        return dao.runUpdate(sql.toString(),new String[]{});
    }

    public List<String> getCwids(String[] qspks) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("select lddm || '@!!!' || qsh || '@!!!' || cwh cwpk");
        sql.append(" from XG_GYGL_NEW_CWXXB ");
        sql.append(" where ( ");
        String[] qspk;
        for(int i=0;i<qspks.length;i++){
            qspk = qspks[i].split("@!!!");
            sql.append("lddm='"+qspk[0]+"' and qsh='"+qspk[1]+"'");
            if(i<qspks.length-1){
                sql.append(" or ");
            }
        }
        sql.append(")");
        return dao.getList(sql.toString(),new String[]{},"cwpk");
    }

    public List<HashMap<String,String>> getQsxxListByLddm(String lddm){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.symc from XG_GYGL_NEW_QSXXB a left join XG_XTWH_SYDMB b on a.xydm = b.sydm where a.lddm = ?");
        return dao.getListNotOut(sql.toString(),new String[]{lddm});
    }
    public List<HashMap<String,String>> getQsxxListByLddmAndCh(String lddm,String ch){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.symc from XG_GYGL_NEW_QSXXB a left join XG_XTWH_SYDMB b on a.xydm = b.sydm where a.lddm = ? and a.ch = ?");
        return dao.getListNotOut(sql.toString(),new String[]{lddm,ch});
    }
    public List<HashMap<String, String>> getQsfpList(QsxxglForm qsxxglForm, User user) throws Exception {

        String searchTj = SearchService.getSearchTj(qsxxglForm.getSearchModel());
        // 高级查询输入值
        String[] inputV = SearchService.getTjInput(qsxxglForm.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");

        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select a.*,b.ldmc,nvl(c.symc,d.bmmc) ssbm,a.lddm || '@!!!' || a.qsh pk");
        sql.append(" ,case when a.qsxb='1' then '男' when a.qsxb='2' then '女' else '混住' end qsxbmc");
        sql.append(" ,(select count(1) from XG_GYGL_NEW_CWXXB x where x.lddm = a.lddm and x.qsh = a.qsh ) cwss");//统计床位表的床位数
        sql.append(" ,(select count(1) from XG_GYGL_NEW_CWXXB x where x.lddm = a.lddm and x.qsh = a.qsh and x.sfbl = '1') blcws");
        sql.append(" ,(select count(1) from XG_GYGL_NEW_CWXXB x where x.lddm = a.lddm and x.qsh = a.qsh and x.xh is null and x.sfbl <> '1') kxcws");
        sql.append(" ,(select count(1) from XG_GYGL_NEW_CWXXB x where x.lddm = a.lddm and x.qsh = a.qsh and x.xh is not null ) ylzcws");
        sql.append(" from XG_GYGL_NEW_QSXXB a");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm");
        sql.append(" left join XG_XTWH_SYDMB c on a.xydm = c.sydm ");
        sql.append(" left join ZXBZ_XXBMDM d on a.xydm = d.bmdm ");
        sql.append(" where a.fjlx = '01'");//只显示宿舍
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        sql.append(searchTjByUser);
        return getPageList(qsxxglForm,sql.toString(),inputV);
    }

    public List<HashMap<String,String>> getXysy(String fpfs){
        StringBuilder sql = new StringBuilder();
        if("sy".equals(fpfs)){
            sql.append("select sydm dm,symc mc,substr(nvl(f_pinyin(symc),symc),0,1) pyszm from XG_XTWH_SYDMB order by pyszm ");
        } else {
            sql.append("select distinct xydm dm,xymc mc,substr(nvl(f_pinyin(xymc),xymc),0,1) pyszm from view_njxyzybj_all order by pyszm ");
        }

        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    public boolean fpBc(QsxxglForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("MERGE INTO XG_GYGL_NEW_QSXXB a ");
        sql.append(" USING ( ");
        String[] pks = model.getPks().split(",");
        String[] pk;
        for(int i=0;i<pks.length;i++){
            pk = pks[i].split("@!!!");
            sql.append("select '"+pk[0]+"' lddm,'"+pk[1]+"' qsh,'"+model.getXydmId()+"' xydm from dual ");
            if(i<pks.length-1){
                sql.append(" union ");
            }
        }
        sql.append(" ) b ON (a.lddm = b.lddm and a.qsh = b.qsh )");
        sql.append(" WHEN matched THEN UPDATE set a.xydm = b.xydm");
        return dao.runUpdate(sql.toString(),new String[]{});
    }
}
