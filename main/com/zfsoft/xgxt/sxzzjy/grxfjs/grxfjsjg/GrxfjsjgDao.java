package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-26 16:06
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GrxfjsjgDao extends SuperDAOImpl<GrxfjsjgForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(GrxfjsjgForm.class);
        super.setKey("jgid");
        super.setTableName("xg_sxzzjy_grxfjs_grxfjsjgb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(GrxfjsjgForm grxfjsjgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(GrxfjsjgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");

        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from ( ");
        sql.append(" select a.jgid,a.xh,a.xfjsmc,a.sblx,a.xn,a.xq,a.bxnmb,a.jssl,a.lrsj,a.lrr,a.fjid,a.sjly,a.lcywid,");
        sql.append(" b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjmc,b.bjdm,b.nj,b.sjhm,b.mz,b.zzmmmc,b.xb,");
        sql.append(" c.sblxmc,a.xn||e.xqmc xnxq,f.qsh,g.ldmc, h1.id nzhbid,h2.id nzzjid,");
        sql.append(" case when h1.id is null then '未完成' else '已完成' end sfnzhb, ");
        sql.append(" case when h2.id is null then '未完成' else '已完成' end sfnzzj  ");
        sql.append(" from   xg_sxzzjy_grxfjs_grxfjsjgb a ");
        sql.append(" left join view_xsjbxx b on b.xh = a.xh");
        sql.append(" left join xg_sxzzjy_grxfjs_sblxdmb c on c.sblxdm=a.sblx");
        sql.append(" left join fdyxxb d on d.zgh=a.lrr");
        sql.append(" left join xqdzb e on e.xqdm=a.xq");
        sql.append(" left join XG_GYGL_NEW_CWXXB f on f.xh=a.xh");
        sql.append(" left join XG_GYGL_NEW_LDXXB g on g.lddm=f.lddm");
        sql.append(" left join XG_SXZZJY_GRXFJS_GRXFJSHBJGB h1 on h1.jgid=a.jgid and h1.hblx='nzhb' ");
        sql.append(" left join XG_SXZZJY_GRXFJS_GRXFJSHBJGB h2 on h2.jgid=a.jgid and h2.hblx='nzzj' ");
        sql.append(" ) t where 1 = 1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * @描述:获取所有申报类型
     * @作者：lgx [工号：1553]
     * @日期：2018/6/26 17:41
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllSblx() {
        String sql = "select * from xg_sxzzjy_grxfjs_sblxdmb";
        return dao.getListNotOut(sql,new String[]{});
    }
    public HashMap<String,String> getGrxfjsjgInfo(GrxfjsjgForm model) {
        String sql = "select a.*,b.xm,b.xydm,b.xymc,b.bjdm,b.bjmc,b.xb,mz,b.zzmmmc,b.lxdh,b.sjhm," +
                " c.sblxmc,t7.xqmc " +
                " from xg_sxzzjy_grxfjs_grxfjsjgb a " +
                " left join  view_xsjbxx b on b.xh=a.xh " +
                " left join xg_sxzzjy_grxfjs_sblxdmb c on c.sblxdm=a.sblx " +
                " left join xqdzb t7 on a.xq = t7.xqdm " +
                "where a.jgid=?";
        return dao.getMapNotOut(sql,new String[]{model.getJgid()});
    }

    public String checkExistForSave(GrxfjsjgForm model) {
        String jgid = "-1";
        if(!StringUtils.isNull(model.getJgid())){
            jgid = model.getJgid();
        }
        String[] arr = new String[] { model.getXn(),model.getXq(),model.getXh(), model.getSblx(),jgid};
        StringBuilder sql = new StringBuilder(
                " select count(1) num from xg_sxzzjy_grxfjs_grxfjsjgb where xn = ? and xq=? and xh = ? and sblx=? and jgid <> ? ");
        String num = dao.getOneRs(sql.toString(), arr, "num");
        return num;
    }

    public boolean deleteJg(GrxfjsjgForm model) throws Exception {
        String sql = "delete from xg_sxzzjy_grxfjs_grxfjsjgb where xn=? and xq=? and xh=? and sblx=?";
        return dao.runUpdate(sql,new String[]{model.getXn(),model.getXq(),model.getXh(),model.getSblx()});
    }

}
