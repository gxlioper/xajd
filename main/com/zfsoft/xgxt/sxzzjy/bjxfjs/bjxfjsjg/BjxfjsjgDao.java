package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-22 17:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxfjsjgDao extends SuperDAOImpl<BjxfjsjgForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(BjxfjsjgForm.class);
        super.setKey("jgid");
        super.setTableName("xg_sxzzjy_bjxfjs_bjxfjsjgb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjxfjsjgForm bjxfjsjgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjxfjsjgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");

        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from ( ");
        sql.append(" select a.jgid,a.bjdm,a.xfjsmc,a.sblx,a.xn,a.xq,a.bxnmb,a.jssl,a.lrsj,a.lrr,a.fjid,a.sjly,a.lcywid,");
        sql.append(" b.xydm,b.xymc,b.zydm,b.zymc,b.bjmc,b.nj,c.sblxmc,d.xm,a.xn||e.xqmc xnxq,e.xqmc,f1.id nzhbid,f2.id nzzjid, ");
        sql.append(" case when f1.id is null then '未完成' else '已完成' end sfnzhb, ");
        sql.append(" case when f2.id is null then '未完成' else '已完成' end sfnzzj  ");
        sql.append(" from   xg_sxzzjy_bjxfjs_bjxfjsjgb a ");
        sql.append(" left join VIEW_NJXYZYBJ b on b.bjdm = a.bjdm");
        sql.append(" left join xg_sxzzjy_bjxfjs_sblxdmb c on c.sblxdm=a.sblx" );
        sql.append(" left join fdyxxb d on d.zgh=a.lrr" );
        sql.append(" left join xqdzb e on e.xqdm=a.xq");
        sql.append(" left join XG_SXZZJY_BJXFJS_BJXFJSHBJGB f1 on f1.jgid=a.jgid and f1.hblx='nzhb' ");
        sql.append(" left join XG_SXZZJY_BJXFJS_BJXFJSHBJGB f2 on f2.jgid=a.jgid and f2.hblx='nzzj' ");
        sql.append(" ) t where 1 = 1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }



    public List<HashMap<String,String>> getAllSblx() {
        String sql = "select * from xg_sxzzjy_bjxfjs_sblxdmb";
        return dao.getListNotOut(sql,new String[]{});
    }

    public List<HashMap<String,String>> getBjList(BjxfjsjgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");

        StringBuilder sql = new StringBuilder();
        sql.append(" select * from VIEW_NJXYZYBJ t where 1 = 1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public HashMap<String,String> getBjInfo(String bjdm) {
        StringBuilder sql = new StringBuilder();
        String temp = "select b.xm from XG_SZDW_XSGB_DWB t " +
                " left join XG_SZDW_XSGB_ZWB a on a.zwid = t.zwid" +
                " left join view_xsjbxx b on b.xh=t.xh where b.bjdm='"+bjdm+"'";
        sql.append(" select t.*, ");
        sql.append(" ("+temp+" and a.zwmc='班长') bzxm,");
        sql.append(" ("+temp+" and a.zwmc='团支书') tzsxm,");
        sql.append(" (select b.xm from  BZRBBB a left join fdyxxb b on a.zgh=b.zgh where bjdm='"+bjdm+"' ) bzrxm,");
        sql.append(" (select b.xm from  FDYBJB a left join fdyxxb b on a.zgh=b.zgh where bjdm='"+bjdm+"' ) fdyxm,");
        sql.append(" (select count(0)  from view_xsjbxx where bjdm='"+bjdm+"' ) bjzrs,");
        sql.append(" (select count(0)  from view_xsjbxx where bjdm='"+bjdm+"' and xb='男' ) nansrs,");
        sql.append(" (select count(0)  from view_xsjbxx where bjdm='"+bjdm+"' and xb='女') nvsrs,");
        sql.append(" (select count(0)  from view_xsjbxx where bjdm='"+bjdm+"'  and zzmmmc='中国共产党党员' or zzmmmc='党员') dyrs ");
        sql.append(" from VIEW_NJXYZYBJ t where t.bjdm = ? ");
        return  dao.getMapNotOut(sql.toString(),new String[]{bjdm});
    }

    public HashMap<String,String> getBjxfjsjgInfo(BjxfjsjgForm model) {
        String sql = "select a.*,e.xqmc,b.sblxmc from xg_sxzzjy_bjxfjs_bjxfjsjgb a " +
                " left join xg_sxzzjy_bjxfjs_sblxdmb b on b.sblxdm=a.sblx " +
                " left join xqdzb e on e.xqdm=a.xq where jgid=?";
        return dao.getMapNotOut(sql,new String[]{model.getJgid()});
    }
    /**
     * @描述:判断是否重复
     * @作者：lgx [工号：1553]
     * @日期：2018/6/25 11:16
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: java.lang.String
     */
    public String checkExistForSave(BjxfjsjgForm model) {
        String jgid = "-1";
        if(!StringUtils.isNull(model.getJgid())){
            jgid = model.getJgid();
        }
        String[] arr = new String[] { model.getXn(),model.getXq(),model.getBjdm(), model.getSblx(),jgid};
        StringBuilder sql = new StringBuilder(
                " select count(1) num from xg_sxzzjy_bjxfjs_bjxfjsjgb where xn = ? and xq=? and bjdm = ? and sblx=? and jgid <> ? ");
        String num = dao.getOneRs(sql.toString(), arr, "num");
        return num;
    }

    public boolean deleteJg(BjxfjsjgForm model) throws Exception {
        String sql = "delete from xg_sxzzjy_bjxfjs_bjxfjsjgb where xn=? and xq=? and bjdm=? and sblx=?";
        return dao.runUpdate(sql,new String[]{model.getXn(),model.getXq(),model.getBjdm(),model.getSblx()});
    }
}
