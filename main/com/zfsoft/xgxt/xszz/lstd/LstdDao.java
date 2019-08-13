package com.zfsoft.xgxt.xszz.lstd;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：2018-09-25
 */
public class LstdDao extends SuperDAOImpl<LstdForm>{
    @Override
    protected void setTableInfo() {
        super.setKey("sqid");
        super.setTableName("XG_XSZZ_LSTDSQB");
        super.setClass(LstdForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(LstdForm lstdForm, User user) throws Exception {

        String searchTj = SearchService.getSearchTj(lstdForm.getSearchModel());
        String[] inputV = SearchService.getTjInput(lstdForm.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append("select t.sqid,t.shzt,t.shlc,t.sqsj,t1.xh,t1.xm,t2.bjdm,t2.bjmc,t3.xydm,t3.xymc,t3.bjdm zybj,t3.bjmc zybjmc,t3.zydm,t3.zymc ");
        sql.append(" ,decode(t1.xb,'0','女','1','男',t1.xb) xb,t5.sydm,t5.symc");
        sql.append(" ,decode(t.shzt,'0','未提交','1','通过','2','不通过','3','退回','4','需重审','5','审核中',t.shzt) shztmc");
        sql.append(" from XG_XSZZ_LSTDSQB t ");
        sql.append(" left join xsxxb t1 on t.xh = t1.xh");
        sql.append(" left join view_njxyzybj_all t2 on t2.bjdm = t1.bjdm ");
        sql.append(" left join view_njxyzybj_all t3 on t3.bjdm = t1.zybj ");
        sql.append(" left join XG_XTWH_SYBJGLB t4 on t4.bjdm = t2.bjdm ");
        sql.append(" left join XG_XTWH_SYDMB t5 on t5.sydm = t4.sydm ");
        sql.append(" ) t where 1=1 and xh = '" + user.getUserName() + "'");
        sql.append(searchTj);
        return getPageList(lstdForm,sql.toString(),inputV);
    }

    @Override
    public List<HashMap<String, String>> getPageList(LstdForm lstdForm) throws Exception {
        return null;
    }

    public String isExist(LstdForm lstdForm){
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(1) isExist from XG_XSZZ_LSTDSQB where xh = ? and xn = ? and xq = ? ");
        return dao.getOneRs(sql.toString(),new String[]{lstdForm.getXh(),lstdForm.getXn(),lstdForm.getXq()},"isExist");
    }

    public List<HashMap<String,String>> getShList(LstdForm model,User user) throws Exception{

        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String searchShgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(model.getSearchModel());

        StringBuilder sql = new StringBuilder();

        sql.append("select t.* from (");
        sql.append(" select t1.*,t2.xm,t2.xymc,t2.xydm,t2.zydm,t4.symc,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.zybj,t2.zybjmc,");
        sql.append(" decode(t2.xb,'2','女','1','男',t2.xb) xb");
        sql.append(" ,case when t1.hjfs='01' then '生源地国家助学贷款' when t1.hjfs='02' then '校园地国家助学贷款'");
        sql.append(" when t1.hjfs='03' then '勤工助学' when t1.hjfs='04' then '申请助学金' when t1.hjfs='05' then '家庭帮助解决' else '其他' end hjfsmc");
        sql.append(" ,t5.guid shid,t5.shzt shztx,t5.gwid,t5.shr,t5.shsj,");
        sql.append(" t7.mc || '[' || decode(t5.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,");
        sql.append(" row_number() over(partition by t1.sqid order by t5.shsj desc) rn,t7.gwz");
        sql.append(" from XG_XSZZ_LSTDSQB t1");
        sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh");
        sql.append(" left join XG_XTWH_SYBJGLB t3 on t3.bjdm = t2.bjdm");
        sql.append(" left join XG_XTWH_SYDMB t4 on t3.sydm = t4.sydm");
        sql.append(" left join xg_xtwh_shztb t5 on t1.sqid = t5.ywid");
        sql.append(" left join xg_xtwh_spgwyh t6 on t5.gwid = t6.spgw");
        sql.append(" left join xg_xtwh_spgw t7 on t6.spyh = t7.id");
        sql.append(" where t6.spyh = ?");
        if("dsh".equals(model.getShzt())){
            sql.append(" and (t5.shzt=0 or t5.shzt = 4 )");
        } else {
            sql.append(" and (t5.shzt<>0 and t5.shzt<>4)");
        }
        sql.append(" ) t where 1 = 1 and rn = 1");
        sql.append(searchTj);
        sql.append(searchTjByUser);
        sql.append(searchShgwzByUser);
        sql.append(" order by sqsj desc");
        return getPageList(model,sql.toString(), StringUtils.joinStrArr(new String[]{user.getUserName()},inputV));
    }

    public boolean updateLstdsq(LstdForm model) throws Exception {
        String[] inputV = new String[3];
        StringBuilder sql = new StringBuilder();
        sql.append(" update XG_XSZZ_LSTDSQB ");
        sql.append(" set shzt = ? ,shlc = ? where sqid = ?");
        inputV[0] = model.getShzt();
        inputV[1] = model.getShlc();
        inputV[2] = model.getSqid();
        return dao.update(sql.toString(),inputV)>0 ? true:false;
    }

    public boolean updateLstdsqxx(String sqid, String shzt) throws Exception {
        String[] inputV = new String[2];
        StringBuilder sql = new StringBuilder();
        sql.append(" update XG_XSZZ_LSTDSQB set shzt = ?  where sqid = ?");
        inputV[0] = shzt;
        inputV[1] = sqid;
        return dao.update(sql.toString(),inputV)>0 ? true:false;
    }

    public boolean deleteLstdxxjg(String sqid) throws Exception {
        String[] inputV = new String[1];
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from  XG_XSZZ_LSTDJGB ");
        sql.append(" where sqid = ? ");
        inputV[0] = sqid;
        return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
    }

    public List<HashMap<String, String>> getJgPageList(LstdForm lstdForm, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(lstdForm.getSearchModel());
        String[] inputV = SearchService.getTjInput(lstdForm.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append(" select t.*,t1.xm,t2.bjdm,t2.bjmc,t2.nj,t3.xydm,t3.xymc,t3.bjdm zybj,t3.bjmc zybjmc,t3.zydm,t3.zymc ");
        sql.append(" ,decode(t1.xb,'2','女','1','男',t1.xb) xb,t5.sydm,t5.symc ");
        sql.append(" ,case when t.hjfs='01' then '生源地国家助学贷款' when t.hjfs='02' then '校园地国家助学贷款'");
        sql.append(" when t.hjfs='03' then '勤工助学' when t.hjfs='04' then '申请助学金' when t.hjfs='05' then '家庭帮助解决' else '其他' end hjfsmc");
        sql.append(" from XG_XSZZ_LSTDJGB t ");
        sql.append(" left join xsxxb t1 on t.xh = t1.xh ");
        sql.append(" left join view_njxyzybj_all t2 on t2.bjdm = t1.bjdm ");
        sql.append(" left join view_njxyzybj_all t3 on t3.bjdm = t1.zybj ");
        sql.append(" left join XG_XTWH_SYBJGLB t4 on t4.bjdm = t2.bjdm ");
        sql.append(" left join XG_XTWH_SYDMB t5 on t5.sydm = t4.sydm ");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        return getPageList(lstdForm,sql.toString(),inputV);
    }

    public boolean delJcsz() throws Exception {
        String sql = "delete from XG_XSZZ_LSTDJCSZB ";
        return dao.runUpdate(sql,new String[]{});
    }

    public boolean insertJcsz(LstdForm lstdForm) throws Exception {
        String sql = "insert into XG_XSZZ_LSTDJCSZB(sqkssj,sqjssj,splc) values(?,?,?)";
        return dao.runUpdate(sql,new String[]{lstdForm.getSqkssj(),lstdForm.getSqjssj(),lstdForm.getSplc()});
    }

    public Map<String,String> getJcsz(){
        String sql = "select * from XG_XSZZ_LSTDJCSZB";
        return dao.getMapNotOut(sql,new String[]{});
    }
    public String isOpean(){
        String sql = "select case when to_char(sysdate,'yyyy-MM-dd') between sqkssj and sqjssj then '1' else '0'end isOpean from XG_XSZZ_LSTDJCSZB t";
        return dao.getOneRs(sql,new String[]{},"isOpean");
    }
}

