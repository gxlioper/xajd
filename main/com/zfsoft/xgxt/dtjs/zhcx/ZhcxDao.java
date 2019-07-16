package com.zfsoft.xgxt.dtjs.zhcx;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2019/7/3.
 */
public class ZhcxDao extends SuperDAOImpl<ZhcxForm> {
    @Override
    protected void setTableInfo() {
//        super.setTableName("view_xszz_zhcx");
//        super.setKey("id");
//        super.setClass(ZhcxForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZhcxForm zhcxForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZhcxForm t, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from view_dtjs_rcjy_zhcx t  where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * 通过xh查询理论学习记录
     * @param t
     * @return
     * @throws Exception
     */
    public List<HashMap<String, String>> getllxxListByXh(ZhcxForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (select t.*");
        sql.append(" from (select t1.*,t3.xqmc,");
        sql.append(" t6.xm,");
        sql.append(" t6.xydm,");
        sql.append(" t6.xymc,");
        sql.append(" t6.zydm,");
        sql.append(" t6.zymc,");
        sql.append(" t6.bjdm,");
        sql.append(" t6.bjmc,");
        sql.append("t6.nj,t6.xb,t6.zybj,t6.zybjmc,t6.mz,t6.mzdm,t6.zzmm,t6.zzmmmc,t6.sydm1 sydm,t6.symc1 symc, ");
        sql.append(" ssx.shengmc||ssx.shimc||ssx.xianmc || t1.dd ddxxdz ");
        sql.append(" from xg_dtjs_llxxjl_jg t1");
        sql.append(" left join view_xsjbxx t6");
        sql.append(" on t1.xh = t6.xh");
        sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm ");
        sql.append(" left join xg_view_dmk_qx ssx");
        sql.append(" on ssx.qxdm=t1.ddssx");
        sql.append(" ) t");
        sql.append(" union ");
        sql.append(" select hdid jgid,xh,xn,xq,hdmc,hddd dd,hdkssj sj,zbf zbdw,'' ddssx,'' fjid,'' sqid,'ek' sjly,'' lrr,hdkssj lrsj,xqmc, ");
        sql.append(" xm,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,xb,zybj,zybjmc,mz,mzdm,zzmm,zzmmmc,sydm1 sydm,symc1 symc,hddd ddxxdz ");
        sql.append(" from (select a.zyxss,b.*,c.*,e.xqmc from Xg_Hdgl_Hdqdxxb a ");
        sql.append(" left join XG_HDGL_HDXXB b  on a.hdid = b.hdid ");
        sql.append(" left join VIEW_XSJBXX c on a.xh = c.xh ");
        sql.append(" left join XG_HDGL_HDLXDMB d on b.hdlx = d.hdlxdm ");
        sql.append("  left join xqdzb e on b.xq=e.xqdm ");
        sql.append(" where d.hdlxdm = '2' and ( c.zzmm = '01' or c.zzmm = '02'))  ");//社会实践活动类型
        sql.append(" union ");
        sql.append(" (select decode(a.sqid,null,a.jgid,a.sqid) jgid,a.xh,a.xn,a.xq,a.hdmc,a.hddd dd,a.hdsj sj,zbf zbdw,'' ddssx,'' fjid, ");
        sql.append(" '' sqid,'ekbl' sjly,''lrr,a.sqsj lrsj,e.xqmc,b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj,b.xb,b.zybj,b.zybjmc,b.mz,b.mzdm, ");
        sql.append(" b.zzmm,b.zzmmmc,b.sydm1 sydm,b.symc1 symc,a.hddd ddxxdz from XG_HDGL_HDBLJGB a ");
        sql.append(" left join VIEW_XSJBXX b on a.xh = b.xh ");
        sql.append(" left join XG_HDGL_HDLXDMB c on a.hdlx = c.hdlxdm ");
        sql.append("  left join xqdzb e on a.xq=e.xqdm ");
        sql.append("  where c.hdlxdm = '2' and (b.zzmm = '01' or b.zzmm = '02')) ");
        sql.append(" ) t");
        sql.append(" where 1=1 ");
        sql.append(" and t.xh = ?");

        return getPageList(t, sql.toString(), new String[]{t.getXh()});
    }

    /**
     * 通过xh查询社会实践记录
     * @param t
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getshsjListByXh(ZhcxForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (select t.*");
        sql.append(" from (select t1.*,t3.xqmc,");
        sql.append(" t6.xm,");
        sql.append(" t6.xydm,");
        sql.append(" t6.xymc,");
        sql.append(" t6.zydm,");
        sql.append(" t6.zymc,");
        sql.append(" t6.bjdm,");
        sql.append(" t6.bjmc,");
        sql.append("t6.nj,t6.xb,t6.zybj,t6.zybjmc,t6.mz,t6.mzdm,t6.zzmm,t6.zzmmmc,t6.sydm1 sydm,t6.symc1 symc, ");
        sql.append(" ssx.shengmc||ssx.shimc||ssx.xianmc || t1.dd ddxxdz ");
        sql.append(" from xg_dtjs_shsjjl_jg t1");
        sql.append(" left join view_xsjbxx t6");
        sql.append(" on t1.xh = t6.xh");
        sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm ");
        sql.append(" left join xg_view_dmk_qx ssx");
        sql.append(" on ssx.qxdm=t1.ddssx");
        sql.append(" ) t");
        sql.append(" union ");
        sql.append(" select hdid jgid,xh,xn,xq,hdmc,hddd dd,hdkssj sj,zbf zbdw,'' ddssx,'' fjid,'' sqid,'ek' sjly,'' lrr,hdkssj lrsj,xqmc, ");
        sql.append(" xm,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,xb,zybj,zybjmc,mz,mzdm,zzmm,zzmmmc,sydm1 sydm,symc1 symc,hddd ddxxdz ");
        sql.append(" from (select a.zyxss,b.*,c.*,e.xqmc from Xg_Hdgl_Hdqdxxb a ");
        sql.append(" left join XG_HDGL_HDXXB b  on a.hdid = b.hdid ");
        sql.append(" left join VIEW_XSJBXX c on a.xh = c.xh ");
        sql.append(" left join XG_HDGL_HDLXDMB d on b.hdlx = d.hdlxdm ");
        sql.append("  left join xqdzb e on b.xq=e.xqdm ");
        sql.append(" where d.hdlxdm = '3' and ( c.zzmm = '01' or c.zzmm = '02'))  ");//社会实践活动类型
        sql.append(" union ");
        sql.append(" (select decode(a.sqid,null,a.jgid,a.sqid) jgid,a.xh,a.xn,a.xq,a.hdmc,a.hddd dd,a.hdsj sj,zbf zbdw,'' ddssx,'' fjid, ");
        sql.append(" '' sqid,'ekbl' sjly,''lrr,a.sqsj lrsj,e.xqmc,b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj,b.xb,b.zybj,b.zybjmc,b.mz,b.mzdm, ");
        sql.append(" b.zzmm,b.zzmmmc,b.sydm1 sydm,b.symc1 symc,a.hddd ddxxdz from XG_HDGL_HDBLJGB a ");
        sql.append(" left join VIEW_XSJBXX b on a.xh = b.xh ");
        sql.append(" left join XG_HDGL_HDLXDMB c on a.hdlx = c.hdlxdm ");
        sql.append("  left join xqdzb e on a.xq=e.xqdm ");
        sql.append("  where c.hdlxdm = '3' and (b.zzmm = '01' or b.zzmm = '02')) ");
        sql.append(" ) t ");
        sql.append(" where 1=1 ");
        sql.append(" and t.xh = ? ");
        return getPageList(t,sql.toString(),new String[]{t.getXh()});
    }

    /**
     * 通过xh查询志愿服务记录
     * @param t
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getzyfwListByXh(ZhcxForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (select t.*");
        sql.append(" from (select t1.*,");
        sql.append(" t6.xm,");
        sql.append(" t6.xydm,");
        sql.append(" t6.xymc,");
        sql.append(" t6.zydm,");
        sql.append(" t6.zymc,");
        sql.append(" t6.bjdm,");
        sql.append(" t6.bjmc,");
        sql.append("t6.nj,t6.xb,t6.zybj,t6.zybjmc,t6.mz,t6.mzdm,t6.zzmm,t6.zzmmmc,t6.sydm1 sydm,t6.symc1 symc, ");
        sql.append(" ssx.shengmc||ssx.shimc||ssx.xianmc || t1.fwdd fwddxxdz ");
        sql.append(" from xg_sthd_hdjg t1");
        sql.append(" left join view_xsjbxx t6");
        sql.append(" on t1.xh = t6.xh");
        sql.append(" left join xg_view_dmk_qx ssx");
        sql.append(" on ssx.qxdm=t1.fwddssx");
        sql.append(" ) t  ");
        sql.append(" union ");
        sql.append(" select hdid,xh,hdmc,hddd fwdd,hdkssj fwsj,zyxss fwsc,zbf zbdw,'' id,'ek' sjly,'' fwddssx,'' fjid,'' lrr,hdkssj lrsj, ");
        sql.append(" xm,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,xb,zybj,zybjmc,mz,mzdm,zzmm,zzmmmc,sydm1 sydm,symc1 symc,hddd fwddxxdz ");
        sql.append(" from (select a.zyxss,b.*,c.* from Xg_Hdgl_Hdqdxxb a ");
        sql.append(" left join XG_HDGL_HDXXB b  on a.hdid = b.hdid ");
        sql.append(" left join VIEW_XSJBXX c on a.xh = c.xh ");
        sql.append(" left join XG_HDGL_HDLXDMB d on b.hdlx = d.hdlxdm ");
        sql.append(" where d.hdlxdm = '4' and ( c.zzmm = '01' or c.zzmm = '02'))  ");//志愿活动类型
        sql.append(" union ");
        sql.append(" (select decode(a.sqid,null,a.jgid,a.sqid) hdid,a.xh,a.hdmc,a.hddd fwdd,a.hdsj fwsj,a.zyxss fwsc,zbf zbdw,'' id,'ekbl' sjly, ");
        sql.append(" '' fwddssx,'' fjid,''lrr,a.sqsj lrsj,b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj,b.xb,b.zybj,b.zybjmc,b.mz,b.mzdm, ");
        sql.append(" b.zzmm,b.zzmmmc,b.sydm1 sydm,b.symc1 symc,a.hddd fwddxxdz from XG_HDGL_HDBLJGB a ");
        sql.append(" left join VIEW_XSJBXX b on a.xh = b.xh ");
        sql.append(" left join XG_HDGL_HDLXDMB c on a.hdlx = c.hdlxdm ");
        sql.append("  where c.hdlxdm = '4' and (b.zzmm = '01' or b.zzmm = '02')) ");
        sql.append(" ) t ");
        sql.append(" where 1=1 ");
        sql.append(" and t.xh = ? ");
        return getPageList(t,sql.toString(),new String[]{t.getXh()});
    }
}
