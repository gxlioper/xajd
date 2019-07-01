package com.zfsoft.xgxt.zhdj.xszbhd.zbhdjg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:支部活动结果
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-11 14:55
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZbhdjgDao extends SuperDAOImpl<ZbhdjgForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_zhdj_dzbhd_hdzxsbb");//自行上报表
        super.setKey("hdsbid");
        super.setClass(ZbhdjgForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZbhdjgForm zbhdjgForm) throws Exception {

        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZbhdjgForm model, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "");
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select t1.*,decode(t1.xqdm,'01','第一学期','02','第二学期') xqmc," +
                " t2.dzbmc,t3.xydm,t4.bmmc xymc,t5.mc shykdrhdmc,t6.mc hdlxmc from " +
                " (select hdsbid,dzbid,xn,xqdm,hdzt,hdlx,shykdrhd ,'zxsb' lx from xg_zhdj_dzbhd_hdzxsbb a " +
                " union (select b.hdsbid,b.dzbid,b.xn,b.xqdm,c.hdzt,b.hdlx,b.shykdrhd,'xssb' lx  from  xg_zhdj_dzbhd_hdxssbb b " +
                " left join xg_zhdj_dzbhd_hdfbb c on c.hdid=b.hdid ) ) t1" +
                " left join (" +
                "select z.dzbid,z.dzbmc,z.jcdwdm,z.clsj from XG_ZHDJ_DZBGL_DZB z" +
                " group by z.dzbid,z.dzbmc,z.jcdwdm,z.clsj" +
                ") t2 on t2.dzbid=t1.dzbid " +
                " left join xg_zhdj_dzbgl_jcdwdmb t3 on t3.dm=t2.jcdwdm " +
                " left join ZXBZ_XXBMDM t4 on t4.bmdm = t3.xydm " +
                " left join ZHDJ_DRHDLBB t5 on t5.dm=t1.shykdrhd " +
                " left join ZHDJ_HDLXB t6 on t6.dm=t1.hdlx ");
        sql.append(") t where 1=1");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);
}

    public HashMap<String,String> getMap(ZbhdjgForm model) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from (");
        if("xssb".equals(model.getType())){
            sql.append("select a.*,b.hdzt,f.dzbmc,c.bmmc xymc ,d.mc shykdrhdmc,e.mc hdlxmc ," +
                    " decode(a.xqdm,'01','第一学期','02','第二学期') xqmc " +
                    " from xg_zhdj_dzbhd_hdxssbb a " +
                    " left join xg_zhdj_dzbhd_hdfbb b on b.hdid=a.hdid " +
                    " left join (" +
                    " select z.dzbid,z.dzbmc,z.jcdwdm,z.clsj from XG_ZHDJ_DZBGL_DZB z " +
                    " group by z.dzbid,z.dzbmc,z.jcdwdm,z.clsj" +
                    " ) f on f.dzbid=a.dzbid " +
                    " left join xg_zhdj_dzbgl_jcdwdmb g on g.dm=f.jcdwdm " +
                    " left join ZXBZ_XXBMDM c on c.bmdm = g.xydm " +
                    " left join ZHDJ_DRHDLBB d on d.dm=a.shykdrhd  "+
                    " left join ZHDJ_HDLXB e on e.dm=a.hdlx ");
        }else{
            sql.append("select a.*,b.dzbmc,d.bmmc xymc,e.mc shykdrhdmc,f.mc hdlxmc, " +
                    " decode(a.xqdm,'01','第一学期','02','第二学期') xqmc " +
                    " from xg_zhdj_dzbhd_hdzxsbb a " +
                    " left join (" +
                    " select z.dzbid,z.dzbmc,z.jcdwdm,z.clsj from XG_ZHDJ_DZBGL_DZB z group by z.dzbid,z.dzbmc,z.jcdwdm,z.clsj" +
                    " ) b on b.dzbid=a.dzbid " +
                    " left join xg_zhdj_dzbgl_jcdwdmb c on c.dm=b.jcdwdm " +
                    " left join ZXBZ_XXBMDM d on d.bmdm = c.xydm " +
                    " left join ZHDJ_DRHDLBB e on e.dm=a.shykdrhd  "+
                    " left join ZHDJ_HDLXB f on f.dm=a.hdlx ");
        }
        sql.append(") t where t.hdsbid=?");
        return dao.getMapNotOut(sql.toString(),new String[]{model.getHdsbid()});

    }
}
