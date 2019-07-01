package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjssq;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-26 09:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GrxfjssqDao extends SuperDAOImpl<GrxfjssqForm> {
    @Override
    protected void setTableInfo() {
        this.setTableName("xg_sxzzjy_grxfjs_grxfjssqb");
        this.setKey("sqid");
        this.setClass(GrxfjssqForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(GrxfjssqForm grxfjssqForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(GrxfjssqForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");

        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from ( ");
        sql.append(" select a.sqid,a.xh,a.xfjsmc,a.sblx,a.xn,a.xq,a.bxnmb,a.jssl,a.sqsj,a.sqr,a.fjid,a.shzt,a.splc," );
        sql.append(" b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjmc,b.bjdm,b.nj,c.sblxmc,a.xn||e.xqmc xnxq,e.xqmc," );
        sql.append(" decode(a.shzt,'0','未提交','1','通过','2','不通过', '3','退回','4','需重审','5','审核中','','无需审核',a.shzt) shztmc");
        sql.append(" from   xg_sxzzjy_grxfjs_grxfjssqb a " );
        sql.append(" left join VIEW_XSJBXX b on b.xh = a.xh" );
        sql.append(" left join xg_sxzzjy_grxfjs_sblxdmb c on c.sblxdm=a.sblx" );
        sql.append(" left join xqdzb e on e.xqdm=a.xq");
        sql.append(" ) t where 1 = 1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public List<HashMap<String,String>> getAllSblx() {
        String sql = "select * from xg_sxzzjy_grxfjs_sblxdmb order by sblxdm";
        return dao.getListNotOut(sql,new String[]{});
    }
    public String getShlcID() {
        StringBuffer sql = new StringBuffer();
        sql.append(" select splc from xg_sxzzjy_grxfjs_jcszb ");
        return dao.getOneRs(sql.toString(), new String[] {}, "splc");
    }

    public String checkExistForSave(GrxfjssqForm model) {
        String sqid = "-1";
        if(!StringUtils.isNull(model.getSqid())){
            sqid = model.getSqid();
        }
        String[] arr = new String[] { model.getXn(),model.getXq(),model.getXh(), model.getSblx(),sqid};
        StringBuilder sql = new StringBuilder(
                " select count(1) num from xg_sxzzjy_grxfjs_grxfjssqb where xn = ? and xq = ? and xh = ? and sblx=? and sqid <> ? ");
        String num = dao.getOneRs(sql.toString(), arr, "num");
        return num;
    }

    public boolean updateGrxfjssq(GrxfjssqForm model) throws Exception {
        String[] inputV = new String[3];
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE xg_sxzzjy_grxfjs_grxfjssqb ");
        sql.append(" set ");
        sql.append(" shzt = ?,");
        sql.append(" splc = ? ");
        sql.append(" where sqid = ?");
        inputV[0] = model.getShzt();
        inputV[1] = model.getSplc();
        inputV[2] = model.getSqid();
        return dao.update(sql.toString(),inputV)>0;
    }

    public List<HashMap<String,String>> getStuList(GrxfjssqForm t, User user) throws Exception{
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");

        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from  (select a.*,e.ldmc||d.qsh||'寝室' qsxx,e.ldmc,d.qsh  from VIEW_XSJBXX a ");
        sql.append(" left join XG_GYGL_NEW_CWXXB d on d.xh=a.xh ");
        sql.append(" left join XG_GYGL_NEW_LDXXB e on e.lddm=d.lddm ");
        sql.append(" ) t where 1 = 1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public HashMap<String,String> getGrxfjssqInfo(GrxfjssqForm model) {
        String sql = "select a.*,f.xqmc," +
                " c.sblxmc,decode(a.shzt,'0','未提交','1','通过','2','不通过', '3','退回','4','需重审','5','审核中','','无需审核',a.shzt) shztmc" +
                " from xg_sxzzjy_grxfjs_grxfjssqb a " +
                " left join xg_sxzzjy_grxfjs_sblxdmb c on c.sblxdm=a.sblx " +
                " left join xqdzb f on f.xqdm=a.xq"+
                " where a.sqid=?";
        return dao.getMapNotOut(sql,new String[]{model.getSqid()});
    }
}
