package com.zfsoft.xgxt.zhdj.xszbhd.zbhdsb;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-07 10:53
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZbhdsbDao extends SuperDAOImpl<ZbhdsbForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_zhdj_dzbhd_hdzxsbb");//自行上报表
        super.setKey("hdsbid");
        super.setClass(ZbhdsbForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZbhdsbForm zbhdsbForm) throws Exception {

        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ZbhdsbForm model, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String[] inputV = SearchService.getTjInput(model.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append(" select t.*,e.mc shykdrhdmc,f.mc hdlxmc,decode(t.xqdm,'01','第一学期','02','第二学期') xqmc from xg_zhdj_dzbhd_hdzxsbb t " +
                " left join ZHDJ_DRHDLBB e on e.dm=t.shykdrhd" +
                " left join ZHDJ_HDLXB f on f.dm=t.hdlx " );
        sql.append(" where t.dzbid=(select dzbid from xg_zhdj_dzbgl_dzbcy where xh='"+user.getUserName()+"')");
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);
    }

    public List<HashMap<String, String>> getXssbList(ZbhdsbForm model, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String[] inputV = SearchService.getTjInput(model.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select t.*,decode(t.zt,'0','未上报','1','已上报') ztmc from （ select a.*," +
                " b.xn,b.xqdm,b.hdzt,b.kssj,b.jzsj," +
                " d.hdsbid,d.hdsj,d.hddd,d.sbsj,d.hdlx,d.shykdrhd," +
                " e.mc shykdrhdmc ,f.mc hdlxmc," +
                " decode(b.xqdm,'01','第一学期','02','第二学期') xqmc," +
                " (case when d.hdsbid='' then '0' when d.hdsbid is null then '0' else '1' end) zt" +
                " from xg_zhdj_dzbhd_hdcjzbszb a " +
                " left join xg_zhdj_dzbhd_hdfbb b on b.hdid=a.hdid " +
                " left join  xg_zhdj_dzbhd_hdxssbb d on d.hdid=b.hdid and d.dzbid=a.dzbid " +
                " left join ZHDJ_DRHDLBB e on e.dm=d.shykdrhd" +
                " left join ZHDJ_HDLXB f on f.dm=d.hdlx" +
                " where a.dzbid=(select dzbid from xg_zhdj_dzbgl_dzbcy where xh='"+user.getUserName()+"') ) t");
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);
    }

    public HashMap<String,String> getInfo(ZbhdsbForm model,String userName) {
        StringBuffer sql = new StringBuffer();
        if(!StringUtil.isNull(model.getHdid())){
            sql.append("select a.dzbid,b.*,c.*,d.xydm,d.xymc from xg_zhdj_dzbgl_dzbcy a " +
                    " left join (" +
                    " select z.dzbid,z.dzbmc,z.jcdwdm,z.clsj from XG_ZHDJ_DZBGL_DZB z " +
                    " group by z.dzbid,z.dzbmc,z.jcdwdm,z.clsj" +
                    " ) b on b.dzbid=a.dzbid " +
                    " left join xg_zhdj_dzbhd_hdfbb c on c.hdid='"+model.getHdid()+"' ");
        }else{
            sql.append("select a.dzbid,b.*,d.xydm,d.xymc   from xg_zhdj_dzbgl_dzbcy a " +
                    " left join (" +
                    " select z.dzbid,z.dzbmc,z.jcdwdm,z.clsj from XG_ZHDJ_DZBGL_DZB z " +
                    " group by z.dzbid,z.dzbmc,z.jcdwdm,z.clsj" +
                    " ) b on b.dzbid=a.dzbid " );
        }
        sql.append(" left join view_xsjbxx d on d.xh='"+userName+"'" +
                   " where a.xh=?");
        return dao.getMapNotOut(sql.toString(),new String[]{userName});
    }

    public List<HashMap<String,String>> getAllDrhd() {
        String sql = "select * from ZHDJ_DRHDLBB";
        return dao.getListNotOut(sql,new String[]{});
    }
    public List<HashMap<String,String>> getAllHdlx() {
        String sql = "select * from ZHDJ_HDLXB";
        return dao.getListNotOut(sql,new String[]{});
    }

    public boolean save(ZbhdsbForm model) throws Exception {
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
        String sbsj = sDateFormat.format(new Date());
        String tableName = "xg_zhdj_dzbhd_hdzxsbb";
        boolean rs = false;
        if("xssb".equals(model.getType()))
            tableName = "xg_zhdj_dzbhd_hdxssbb";
        if(!StringUtil.isNull(model.getHdsbid())){
            String delSql = "delete from "+tableName+" where hdsbid=?";
          rs =  dao.runUpdate(delSql,new String[]{model.getHdsbid()});
        }

        String col = "dzbid,xn,xqdm,hdsj,hddd,ydrs,sdrs,qjrs,wgbdrs,shykdrhd,hdlx,hdsc,tjsxbgrs,hyjl,sbsj,fjid";
        String wh = "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
        StringBuffer sql = new StringBuffer();

        sql.append("insert into ");
        if("xssb".equals(model.getType())){//限时上报
            sql.append(" xg_zhdj_dzbhd_hdxssbb ("+col+",hdid) values("+wh+")");
            String[] input = new String[]{model.getDzbid(),model.getXn(), model.getXqdm(),model.getHdsj(),model.getHddd(),
                    model.getYdrs(),model.getSdrs(),model.getQjrs(),model.getWgbdrs(),model.getShykdrhd(),model.getHdlx(),
                    model.getHdsc(),model.getTjsxbgrs(),model.getHyjl(),sbsj,model.getFjid(),model.getHdid()};
            rs = dao.runUpdate(sql.toString(),input);
        }else{
            sql.append(" xg_zhdj_dzbhd_hdzxsbb ("+col+",hdzt) values("+wh+")");
            String[] input = new String[]{model.getDzbid(),model.getXn(), model.getXqdm(),model.getHdsj(),model.getHddd(),
                        model.getYdrs(),model.getSdrs(),model.getQjrs(),model.getWgbdrs(),model.getShykdrhd(),model.getHdlx(),
                        model.getHdsc(),model.getTjsxbgrs(),model.getHyjl(),sbsj,model.getFjid(),model.getHdzt()};
            rs = dao.runUpdate(sql.toString(),input);
        }


        return rs;
    }

    public HashMap<String,String> getXssbModel(ZbhdsbForm model) {
        String sql = "select * from xg_zhdj_dzbhd_hdxssbb where hdsbid=?";
        return dao.getMapNotOut(sql,new String[]{model.getHdsbid()});
    }

    public HashMap<String,String> getHdsbInfo(ZbhdsbForm model,User user) {
        String sql = "";
        if("xssb".equals(model.getType())){
            sql = "select a.*,decode(a.xqdm,'01','第一学期','02','第二学期') xqmc,b.hdzt,c.dzbmc,d.mc shykdrhdmc,e.mc hdlxmc, " +
                    " (select xymc from view_xsjbxx where xh='"+user.getUserName()+"') xymc " +
                    " from xg_zhdj_dzbhd_hdxssbb a " +
                    " left join xg_zhdj_dzbhd_hdfbb b on b.hdid=a.hdid " +
                    " left join (" +
                    " select z.dzbid,z.dzbmc,z.jcdwdm,z.clsj from XG_ZHDJ_DZBGL_DZB z " +
                    " group by z.dzbid,z.dzbmc,z.jcdwdm,z.clsj" +
                    " ) c on c.dzbid=a.dzbid " +
                    " left join ZHDJ_DRHDLBB d on d.dm=a.shykdrhd " +
                    " left join ZHDJ_HDLXB e on e.dm=a.hdlx " +
                    " where a.hdsbid=?";
        }else{
            sql = "select a.*,decode(a.xqdm,'01','第一学期','02','第二学期') xqmc,c.dzbmc,d.mc shykdrhdmc,e.mc hdlxmc, " +
                    " (select xymc from view_xsjbxx where xh='"+user.getUserName()+"') xymc " +
                    " from xg_zhdj_dzbhd_hdzxsbb a " +
                    " left join (" +
                    " select z.dzbid,z.dzbmc,z.jcdwdm,z.clsj from XG_ZHDJ_DZBGL_DZB z " +
                    " group by z.dzbid,z.dzbmc,z.jcdwdm,z.clsj" +
                    " ) c on c.dzbid=a.dzbid " +
                    " left join ZHDJ_DRHDLBB d on d.dm=a.shykdrhd " +
                    " left join ZHDJ_HDLXB e on e.dm=a.hdlx " +
                    " where a.hdsbid=?" ;
        }

        return dao.getMapNotOut(sql,new String[]{model.getHdsbid()});
    }

    public int deleteXssb(String[] ids) throws Exception {
        String sql = "delete from xg_zhdj_dzbhd_hdxssbb where hdsbid=?";
        return  dao.runDelete(sql,ids);
    }
}
