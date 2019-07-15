package com.zfsoft.xgxt.dtjs.llxxjl.jg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： llf [工号:1754]
 * @时间： 2019-07-15 14:36
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class LlxxjljgDao extends SuperDAOImpl<LlxxjljgForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(LlxxjljgForm.class);
        super.setKey("jgid");
        super.setTableName("xg_dtjs_llxxjl_jg");
    }

    @Override
    public List<HashMap<String, String>> getPageList(LlxxjljgForm llxxjljgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(LlxxjljgForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
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
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public String checkEdit(LlxxjljgForm t) throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append("select count(0) num from xg_dtjs_llxxjl_jg");
        sql.append("  where xh=? and sj=? ");
        if(StringUtils.isNotNull(t.getJgid())){
            sql.append(" and jgid<>'"+t.getJgid()+"'");
        }
        return dao.getOneRs(sql.toString(),new String[]{t.getXh(),t.getSj()},"num");
    }

    public boolean delSthdjgBySqId(String id)throws Exception{
        StringBuffer sql = new StringBuffer();
        sql.append("delete from xg_dtjs_llxxjl_jg where sqid=? ");
        return dao.runUpdate(sql.toString(),new String[]{id});
    }

    public boolean delBySj(LlxxjljgForm t) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from xg_dtjs_llxxjl_jg where xh=? and sj=? ");
        return dao.runUpdate(sql.toString(),new String[]{t.getXh(),t.getSj()});
    }

    public HashMap<String,String> getInfo(String jgid) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.*");
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
        sql.append(" where jgid=? ");
        return dao.getMapNotOut(sql.toString(),new String[]{jgid});
    }

    /**
     * 获取二课中社会实践活动信息
     * @param t
     * @return
     */
    public HashMap<String,String> getEkxx(LlxxjljgForm t){
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ( ");
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
        sql.append(" ) where 1=1 and jgid = ? and xh = ?");
        String[] input = new String[]{t.getJgid(),t.getXh()};
        return dao.getMapNotOut(sql.toString(),input);
    }
}
