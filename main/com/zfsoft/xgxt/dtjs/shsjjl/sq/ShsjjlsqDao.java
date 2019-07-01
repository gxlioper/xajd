package com.zfsoft.xgxt.dtjs.shsjjl.sq;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-03-01 09:21
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ShsjjlsqDao extends SuperDAOImpl<ShsjjlsqForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(ShsjjlsqForm.class);
        super.setKey("sqid");
        super.setTableName("xg_dtjs_shsjjl_sq");
    }

    @Override
    public List<HashMap<String, String>> getPageList(ShsjjlsqForm shsjjlsqForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ShsjjlsqForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append("select t1.*,t5.xqmc,t6.xm,t6.xydm,t6.xymc,t6.zydm,t6.zymc,t6.bjdm,t6.bjmc,");
        sql.append("t6.nj,t6.xb,t6.zybj,t6.zybjmc,t6.mz,t6.mzdm,t6.zzmm,t6.zzmmmc,t6.sydm1 sydm,t6.symc1 symc, ");
        sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc, ");
        sql.append(" ssx.shengmc||ssx.shimc||ssx.xianmc|| t1.dd ddxxdz ");
        sql.append(" from xg_dtjs_shsjjl_sq t1 ");
        sql.append(" left join view_xsjbxx t6 on t1.xh=t6.xh ");
        sql.append(" left join xg_view_dmk_qx ssx on ssx.qxdm=t1.ddssx ");
        sql.append(" left join xqdzb t5 on t1.xq=t5.xqdm ");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * @描述:获取审核流
     * @作者：lgx [工号：1553]
     * @日期：2019/3/1 11:13
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: []
     * @return: java.lang.String
     */
    public String getShlcID() {
        StringBuffer sql = new StringBuffer();
        sql.append(" select splc from xg_dtjs_shsjjl_cssz ");
        return dao.getOneRs(sql.toString(), new String[] {}, "splc");
    }

    public HashMap<String,String> getInfo(String sqid){
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* from (");
        sql.append("select t1.*,t5.xqmc,t6.xm,t6.xydm,t6.xymc,t6.zydm,t6.zymc,t6.bjdm,t6.bjmc,");
        sql.append("t6.nj,t6.xb,t6.zybj,t6.zybjmc,t6.mz,t6.zzmmmc, t6.sydm1 sydm,t6.symc1 symc,");
        sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc, ");
        sql.append(" ssx.shengmc||ssx.shimc||ssx.xianmc|| t1.dd ddxxdz ");
        sql.append(" from xg_dtjs_shsjjl_sq t1 ");
        sql.append(" left join view_xsjbxx t6 on t1.xh=t6.xh ");
        sql.append(" left join xqdzb t5 on t1.xq=t5.xqdm ");
        sql.append(" left join xg_view_dmk_qx ssx on ssx.qxdm=t1.ddssx ");
        sql.append(" ) t where t.sqid=? ");
        return dao.getMapNotOut(sql.toString(),new String[]{sqid});
    }

    public String checkEdit(ShsjjlsqForm t) throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append("select count(0) num from xg_dtjs_shsjjl_sq");
        sql.append("  where xh=? and sj=? ");
        if(StringUtils.isNotNull(t.getSqid())){
            sql.append(" and sqid<>'"+t.getSqid()+"'");
        }
        return dao.getOneRs(sql.toString(),new String[]{t.getXh(),t.getSj()},"num");
    }

}
