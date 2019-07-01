package com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypx;

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
public class YwxsypxDao extends SuperDAOImpl<YwxsypxForm>{
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_szdw_fdy_ywxsypxxxb");
        super.setKey("sqid");
        super.setClass(YwxsypxForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(YwxsypxForm ywxsypxForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(YwxsypxForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.* from (");
        sql.append(" select t.*,t1.xb,t1.xm,t1.bmmc,t1.bmdm,t1.sydm,t1.symc,t2.bmmc zzbmmc, ");
        sql.append(" decode(t.shzt,'0','未提交','1','通过','2','不通过','3','退回','4','需重审','5','审核中','','无需审核',t.shzt) shztmc ");
        sql.append(" from xg_szdw_fdy_ywxsypxxxb t ");
        sql.append(" left join view_fdyxx t1 on t.zgh = t1.zgh ");
        sql.append(" left join ZXBZ_XXBMDM t2 on t.zzbm = t2.bmdm");
        sql.append(" where t.zgh = '"+user.getUserName()+"'");
        sql.append(" ) a where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    public List<HashMap<String, String>> getShList(YwxsypxForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.* from (");
        sql.append(" select t.*,t1.xb,t1.xm,t1.bmmc,t1.bmdm,t1.sydm,t1.symc,t2.bmmc zzbmmc,t4.shzt shztx,t4.guid shid,t4.gwid, ");
        sql.append(" t6.mc || '[' || ");
        sql.append(" decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,");
        sql.append(" row_number() over(partition by t.sqid order by t4.shsj desc) rn ");
        sql.append(" from xg_szdw_fdy_ywxsypxxxb t ");
        sql.append(" left join view_fdyxx t1 on t.zgh = t1.zgh ");
        sql.append(" left join ZXBZ_XXBMDM t2 on t.zzbm = t2.bmdm");
        sql.append(" left join xg_xtwh_shztb t4 on t.sqid = t4.ywid ");
        sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw ");
        sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
        sql.append(" where t5.spyh = '"+user.getUserName()+"'");
        String shlx = t.getShzt();
        if (!shlx.equals("dsh")) {
            sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
        } else {
            sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
        }
        sql.append(" ) a where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }
    public List<HashMap<String,String>> getAllBmList(){
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ZXBZ_XXBMDM ");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    public boolean cssz(String splc) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from XG_FDY_YWXSYPXXXB_CSSZ ");
        boolean flag = dao.runUpdate(sql.toString(),new String[]{});
        if(flag){
            sql = new StringBuilder();
            sql.append("insert into XG_FDY_YWXSYPXXXB_CSSZ(splc) values(?)");
            flag = dao.runUpdate(sql.toString(),new String[]{splc});
        }
        return flag;
    }

    public String getSplc(){
        StringBuilder sql = new StringBuilder();
        sql.append("select splc from XG_FDY_YWXSYPXXXB_CSSZ ");
        return dao.getOneRs(sql.toString(),new String[]{},"splc");
    }

    @Override
    public YwxsypxForm getModel(YwxsypxForm ywxsypxForm) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.bmmc zzbmmc from xg_szdw_fdy_ywxsypxxxb a");
        sql.append(" left join ZXBZ_XXBMDM b on a.zzbm = b.bmdm ");
        sql.append(" where a.sqid = ?");
        return getModel(ywxsypxForm,sql.toString(),new String[]{ywxsypxForm.getSqid()});
    }

    public boolean delJg(String sqid) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from xg_szdw_fdy_ywxsypxxxjgb where sqid = ?");
        return dao.runUpdate(sql.toString(),new String[]{sqid});
    }
}
