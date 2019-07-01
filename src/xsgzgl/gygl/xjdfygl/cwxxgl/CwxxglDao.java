package xsgzgl.gygl.xjdfygl.cwxxgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class CwxxglDao extends SuperDAOImpl<CwxxglForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("XG_GYGL_NEW_CWXXB");
        super.setClass(CwxxglForm.class);
    }

    @Override
    public CwxxglForm getModel(CwxxglForm cwxxglForm) throws Exception {
        String[] pk = cwxxglForm.getPk().split("@!!!");
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,c.ldmc,d.bmmc xymc,case when b.qsxb='1' then '男' when b.qsxb='2' then '女' when b.qsxb='3' then '混住' else b.qsxb end qsxbmc");
        sql.append(" ,a.lddm || '@!!!' || a.qsh || '@!!!' || a.cwh pk");
        sql.append(" from XG_GYGL_NEW_CWXXB a");
        sql.append(" left join XG_GYGL_NEW_QSXXB b on a.lddm = b.lddm and a.qsh = b.qsh");
        sql.append(" left join XG_GYGL_NEW_LDXXB c on b.lddm = c.lddm");
        sql.append(" left join ZXBZ_XXBMDM d on a.xydm = d.bmdm");
        sql.append(" where a.lddm = ? and a.qsh = ? and a.cwh = ?");
        return getModel(sql.toString(),pk);
    }

    @Override
    public List<HashMap<String, String>> getPageList(CwxxglForm cwxxglForm) throws Exception {
        String searchTj = SearchService.getSearchTj(cwxxglForm.getSearchModel());
        // 高级查询输入值
        String[] inputV = SearchService.getTjInput(cwxxglForm.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append("select a.*,b.ldmc,c.xm ");
        sql.append(" from XG_GYGL_NEW_CWXXB a");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm ");
        sql.append(" left join view_xsjbxx c on a.xh = c.xh");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        return getPageList(cwxxglForm,sql.toString(),inputV);
    }

    @Override
    public List<HashMap<String, String>> getPageList(CwxxglForm cwxxglForm, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(cwxxglForm.getSearchModel());
        // 高级查询输入值
        String[] inputV = SearchService.getTjInput(cwxxglForm.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append("select a.*,b.ldmc,c.xm,a.lddm || '@!!!' || a.qsh || '@!!!' || a.cwh pk ");
        sql.append(" from XG_GYGL_NEW_CWXXB a");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm ");
        sql.append(" left join view_xsjbxx c on a.xh = c.xh");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        return getPageList(cwxxglForm,sql.toString(),inputV);
    }

    public List<HashMap<String, String>> getzsqxcwList(CwxxglForm cwxxglForm, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(cwxxglForm.getSearchModel());
        // 高级查询输入值
        String[] inputV = SearchService.getTjInput(cwxxglForm.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append("select a.*,b.ldmc,c.xm,c.xb,a.lddm || '@!!!' || a.qsh || '@!!!' || a.cwh pk ");
        sql.append(" from XG_GYGL_NEW_CWXXB a");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm ");
        sql.append(" left join view_xsjbxx c on a.xh = c.xh");
        sql.append(" where a.xh is not null ");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        return getPageList(cwxxglForm,sql.toString(),inputV);
    }
    //房源预测分两部分：1:查询空床位与到期床位；2:查询入住率低于50%的寝室
    //查询空床位与到期床位
    public List<HashMap<String, String>> fyycList(CwxxglForm cwxxglForm, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(cwxxglForm.getSearchModel());
        // 高级查询输入值
        String[] inputV = SearchService.getTjInput(cwxxglForm.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append("select a.*,b.ldmc,c.xm,c.xb,a.lddm || '@!!!' || a.qsh || '@!!!' || a.cwh pk ");
        sql.append(" from XG_GYGL_NEW_CWXXB a");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm ");
        sql.append(" left join view_xsjbxx c on a.xh = c.xh");
        sql.append(" where (a.xh is null or a.zsdqsj <= to_char(sysdate,'yyyy-MM-dd') )");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        return getPageList(cwxxglForm,sql.toString(),inputV);
    }
    //房源预测分两部分：1:查询空床位与到期床位；2:查询入住率低于50%的寝室
    //查询入住率低于50%的寝室
    public List<HashMap<String, String>> fyycqsList(CwxxglForm cwxxglForm, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(cwxxglForm.getSearchModel());
        // 高级查询输入值
        String[] inputV = SearchService.getTjInput(cwxxglForm.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from (");
        sql.append("select t.*,round((ylzcws/cwss),2)*100 rzl from (");
        sql.append(" select a.*,b.ldmc,nvl(c.symc,d.bmmc) ssbm,a.lddm || '@!!!' || a.qsh pk");
        sql.append(" ,case when a.qsxb='1' then '男' when a.qsxb='2' then '女' else '混住' end xb");
        sql.append(" ,(select count(1) from XG_GYGL_NEW_CWXXB x where x.lddm = a.lddm and x.qsh = a.qsh ) cwss");//统计床位表的床位数
        sql.append(" ,(select count(1) from XG_GYGL_NEW_CWXXB x where x.lddm = a.lddm and x.qsh = a.qsh and x.sfbl = '1') blcws");
        sql.append(" ,(select count(1) from XG_GYGL_NEW_CWXXB x where x.lddm = a.lddm and x.qsh = a.qsh and x.xh is null and x.sfbl <> '1') kxcws");
        sql.append(" ,(select count(1) from XG_GYGL_NEW_CWXXB x where x.lddm = a.lddm and x.qsh = a.qsh and x.xh is not null ) ylzcws");
        sql.append(" from XG_GYGL_NEW_QSXXB a");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm");
        sql.append(" left join XG_XTWH_SYDMB c on a.xydm = c.sydm ");
        sql.append(" left join ZXBZ_XXBMDM d on a.xydm = d.bmdm ");
        sql.append(" where a.fjlx = '01'");//只显示宿舍
        sql.append(" ) t ");
        sql.append(" ) t where rzl <=50 ");
        sql.append(searchTj);
        return getPageList(cwxxglForm,sql.toString(),inputV);
    }
    public boolean batchInsert(List<String[]> params) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into XG_GYGL_NEW_CWXXB(lddm,qsh,cwh) values(?,?,?)");
        return dao.runBatchBoolean(sql.toString(),params);
    }

    public List<HashMap<String,String>> getBlyyList(){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_GYGL_NEW_BLYYDMB order by dm ");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    public List<HashMap<String,String>> getRzyyList(){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_GYGL_NEW_RZYYDMB order by rzyydm ");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    public List<HashMap<String,String>> getTsyyList(){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_GYGL_NEW_TSYYDMB order by tsyydm ");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    public boolean plblcw(CwxxglForm model) throws Exception {
        String[] pks = model.getPks().split(",");
        StringBuilder sql = new StringBuilder();
        sql.append("MERGE INTO XG_GYGL_NEW_CWXXB a ");
        sql.append(" USING ( ");
        String[] pk;
        for(int i=0;i<pks.length;i++){
            pk = pks[i].split("@!!!");
            sql.append("select '"+pk[0]+"' lddm,'"+pk[1]+"' qsh,'"+pk[2]+"' cwh,'"+model.getSfbl()+"' sfbl,'"+model.getBlyy()+"' blyy,'"+model.getBlsm()+"' blsm from dual ");
            if(i<pks.length-1){
                sql.append(" union ");
            }
        }
        sql.append(" ) b ON (a.lddm = b.lddm and a.qsh = b.qsh and a.cwh = b.cwh)");
        sql.append(" WHEN matched THEN UPDATE set a.sfbl = b.sfbl,a.blyydm = b.blyy,a.blsm = b.blsm ");

        return dao.runUpdate(sql.toString(),new String[]{});
    }
    //获取未入住且已分配的床位个数
    public List<String> getAllWrzCw() throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("select lddm || '@!!!' || qsh || '@!!!' || cwh pk");
        sql.append(" from XG_GYGL_NEW_CWXXB ");
        sql.append(" where xh is null and (xydm is not null or nj is not null or bjdm is not null or zydm is not null)");
        return dao.getList(sql.toString(),new String[]{},"pk");
    }

    public boolean cwsscsh(String[] pks,String cshlx) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("MERGE INTO XG_GYGL_NEW_CWXXB a ");
        sql.append(" USING ( ");
        String[] pk;
        for(int i=0;i<pks.length;i++){
            pk = pks[i].split("@!!!");
            sql.append("select '"+pk[0]+"' lddm,'"+pk[1]+"' qsh,'"+pk[2]+"' cwh from dual ");
            if(i<pks.length-1){
                sql.append(" union ");
            }
        }
        sql.append(" ) b ON (a.lddm = b.lddm and a.qsh = b.qsh and a.cwh = b.cwh )");
        if("all".equals(cshlx)){
            sql.append(" WHEN matched THEN UPDATE set a.xydm = '',a.nj = '',a.zydm = '',a.bjdm = ''");
        } else {
            sql.append(" WHEN matched THEN UPDATE set a.bjdm = ''");
        }
        return dao.runUpdate(sql.toString(),new String[]{});
    }
    //根据床位获取寝室ids
    public List<String> getQsids(String[] cwpks) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct a.lddm || '@!!!' || a.qsh pk");
        sql.append(" from XG_GYGL_NEW_QSXXB a");
        sql.append(" left join XG_GYGL_NEW_CWXXB b on a.lddm = b.lddm and a.qsh = b.qsh ");
        sql.append(" where ");
        String[] pk;
        for(int i=0;i<cwpks.length;i++){
            pk = cwpks[i].split("@!!!");
            sql.append(" (b.lddm = '"+pk[0]+"' and b.qsh = '"+pk[1]+"' and b.cwh = '"+pk[2]+"')");
            if(i<cwpks.length-1){
                sql.append(" or ");
            }
        }
        return dao.getList(sql.toString(),new String[]{},"pk");
    }

    public boolean rzSave(CwxxglForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        String[] pk = model.getPk().split("@!!!");
        sql.append("update XG_GYGL_NEW_CWXXB set xh=?,rzsj=?,rzyydm=?,bz=? where lddm=? and qsh=? and cwh=?");
        return dao.runUpdate(sql.toString(),new String[]{model.getXh(),model.getRzsj(),model.getRzyy(),model.getBz(),pk[0],pk[1],pk[2]});
    }
    //ssydlx ——> 01:入住；02：退宿；03：调整
    public boolean rzSsydBc(CwxxglForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into XG_GYGL_NEW_SSYD_SSYDJG");
        sql.append("(xh,czsj,xn,xq,ssydlx,tstzyy,tstzsj,bz)");
        sql.append("values(?,?,?,?,'01',?,?,?)");
        String[] param = {model.getXh(),model.getRzsj(), Base.currXn,Base.currXq,model.getRzyy(),model.getRzsj(),model.getBz()};
        return dao.runUpdate(sql.toString(),param);
    }

    public HashMap<String,String> getXsxx(String pk){
        StringBuilder sql = new StringBuilder();
        String[] pkArray = pk.split("@!!!");
        sql.append("select a.xh,a.qsh,a.cwh,b.ldmc,c.xymc,c.xm,c.xb,c.nj");
        sql.append(" from XG_GYGL_NEW_CWXXB a");
        sql.append(" left join XG_GYGL_NEW_LDXXB b on a.lddm = b.lddm");
        sql.append(" left join view_xsjbxx c on a.xh = c.xh");
        sql.append(" where a.lddm = ? and a.qsh = ? and a.cwh = ?");
        return dao.getMapNotOut(sql.toString(),pkArray);
    }

    public boolean tsxxBc(CwxxglForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("update XG_GYGL_NEW_CWXXB set xh = '',rzsj='',bz=''");
        if("1".equals(model.getSfcshcw())){
            sql.append(" ,xydm='',zydm='',bjdm='',nj=''");
        }
        sql.append(" where ");
        String[] pks = model.getPks().split(",");
        String[] pk;
        for(int i=0;i<pks.length;i++){
            pk = pks[i].split("@!!!");
            sql.append(" (lddm = '"+pk[0]+"' and qsh = '"+pk[1]+"' and cwh = '"+pk[2]+"')");
            if(i<pks.length-1){
                sql.append(" or ");
            }
        }
        return dao.runUpdate(sql.toString(),new String[]{});
    }

    //ssydlx ——> 01:入住；02：退宿；03：调整
    public boolean tsSsydBc(CwxxglForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into XG_GYGL_NEW_SSYD_SSYDJG");
        sql.append("(xh,czsj,xn,xq,ssydlx,tstzyy,tstzsj,bz)");
        String[] xhs = model.getXhs().split(",");
        for(int i=0;i<xhs.length;i++){
            sql.append(" SELECT '"+xhs[i]+"','"+model.getTssj()+"','"+model.getXn()+"','"+model.getXq()+"','02','"+model.getTsyy()+"','"+model.getTssj()+"','"+model.getBz()+"' from dual");
            if(i<xhs.length-1){
                sql.append(" union ");
            }
        }
        return dao.runUpdate(sql.toString(),new String[]{});
    }

    public List<HashMap<String,String>> getQshXsxxList(String lddm,String qsh){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.xh,a.cwh,c.symc sssy,b.symc1 symc,b.xymc,b.xm,b.bjmc,b.zybjmc,b.lxdh ");
        sql.append(" from XG_GYGL_NEW_CWXXB a ");
        sql.append(" left join XG_XTWH_SYDMB c on a.xydm = c.sydm ");
        sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
        sql.append(" where a.lddm = ? and a.qsh = ? and a.xh is not null");
        return dao.getListNotOut(sql.toString(),new String[]{lddm,qsh});
    }

    //是否自己寝室
    public boolean sfzjqs(String lddm,String qsh,String xh){
        StringBuilder sql = new StringBuilder();
        sql.append("select 1 s from XG_GYGL_NEW_CWXXB where lddm = ? and qsh = ? and xh = ? ");
        String s = dao.getOneRs(sql.toString(),new String[]{lddm,qsh,xh},"s");
        return !StringUtils.isNull(s);
    }

    public boolean saveDqsj(CwxxglForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        String[] pks = model.getPks().split(",");
        String[] pk;
        sql.append(" MERGE INTO XG_GYGL_NEW_CWXXB a ");
        sql.append(" USING (");
        for(int i=0;i<pks.length;i++){
            pk = pks[i].split("@!!!");
            sql.append(" select '"+pk[0]+"' lddm,'"+pk[1]+"' qsh,'"+pk[2]+"' cwh,'"+model.getZsdqsj()+"' zsdqsj from dual ");
            if(i<pks.length-1){
                sql.append(" union ");
            }
        }
        sql.append(" ) b ON (a.lddm = b.lddm and a.qsh = b.qsh and a.cwh = b.cwh ) ");
        sql.append(" WHEN matched THEN UPDATE set a.zsdqsj = b.zsdqsj");
        return dao.runUpdate(sql.toString(),new String[]{});
    }
}
