package com.zfsoft.xgxt.zhdj.xsdjsgygc.dyssgl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-15 13:59
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DyssglDao extends SuperDAOImpl<DyssglForm>{
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_zhdj_sgy_dyssgl");
        super.setKey("dyssid");
        super.setClass(DyssglForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(DyssglForm dyssglForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(DyssglForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm");
        StringBuilder sql = new StringBuilder();
        sql.append("select t.*,case when t.num > 0 then '已提交' else '未提交' end sftj from ( ");
        sql.append(" select b.ldmc||a.qsh zsxx,a.*,c.xm dyxm, " +
                " (select count(0) c from xg_zhdj_sgy_dysszjqk e where e.dyssid=a.dyssid ) num " +
                " from xg_zhdj_sgy_dyssgl a " +
                " left join XG_GYGL_NEW_LDXXB b on a.lddm=b.lddm " +
                " left join view_xsjbxx c on c.xh=a.dyxh ");
        sql.append(" ) t where 1 = 1");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public List<HashMap<String,String>> getDyList(DyssglForm t, User user) throws Exception{
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm");
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from ( ");
        sql.append("select a.xh dyxh,a.xm dyxm,b.dzbmc,d1.xm dzbsjxm,a.* from XG_ZHDJ_DZBGL_DZBCY t " +
                " left join view_xsjbxx a on t.xh=a.xh " +
                " left join xg_zhdj_dzbgl_dzb b on t.dzbid=b.dzbid" +
                " left join fdyxxb d1 on d1.zgh=b.DZBSJ ");
        sql.append(" ) t where 1 = 1");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public List<HashMap<String,String>> getQsList(DyssglForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm");
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from ( ");
        sql.append("select a.lddm,a.qsh,a.xydm,a.nj,b.ldmc,c.bmmc xymc from XG_GYGL_NEW_QSXXB a " +
                " left join XG_GYGL_NEW_LDXXB b on b.lddm=a.lddm " +
                " left join ZXBZ_XXBMDM c on a.xydm=c.bmdm");
        sql.append(" ) t where 1 = 1");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * @描述:获取寝室成员信息
     * @作者：lgx [工号：1553]
     * @日期：2018/6/19 16:12
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [lddm, qsh]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getQscy(String lddm, String qsh) {
        StringBuffer sql = new StringBuffer();
        sql.append("select a.cwh,a.xh,b.xm from XG_GYGL_NEW_CWXXB a " +
                " left join view_xsjbxx b on b.xh=a.xh " +
                " where a.xh is not null and lddm=? and qsh=? " +
                " order by a.cwh ");
        return dao.getMapNotOut(sql.toString(),new  String[]{lddm,qsh});
    }

    /**
     * @描述:获取结果信息
     * @作者：lgx [工号：1553]
     * @日期：2018/6/19 16:21
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getUpdateInfo(DyssglForm model) {
        StringBuilder sql = new StringBuilder();
        sql.append("select t.*,b.dzbmc,d1.xm dzbsjxm,a.xm,a.xb,a.lxdh,a.sjhm,e.ldmc||t.qsh qsxx  from xg_zhdj_sgy_dyssgl t " +
                " left join view_xsjbxx a on t.dyxh=a.xh " +
                " left join XG_ZHDJ_DZBGL_DZBCY z on z.xh=t.dyxh" +
                " left join xg_zhdj_dzbgl_dzb b on z.dzbid=b.dzbid " +
                " left join fdyxxb d1 on d1.zgh=b.DZBSJ " +
                " left join XG_GYGL_NEW_LDXXB e on e.lddm=t.lddm  " +
                " where t.dyssid=?");
        return dao.getMapNotOut(sql.toString(),new String[]{model.getDyssid()});
    }

    public HashMap<String,String> getViewInfo(DyssglForm model) {
        StringBuilder sql = new StringBuilder();
        sql.append("select t.*,b.dzbmc,d1.xm dzbsjxm,a.xm,a.xb,a.lxdh,a.sjhm,e.ldmc||t.qsh qsxx," +
                " decode(t.wsqk,'01','好'，'02','较好','03','一般','04','差') wsqkmc, " +
                " decode(t.ydaq,'01','好'，'02','较好','03','一般','04','差') ydaqmc, " +
                " decode(t.ssxf,'01','好'，'02','较好','03','一般','04','差') ssxfmc, " +
                " decode(t.ywdbxjxx,'01','有'，'02','没有') ywdbxjxxmc, " +
                " decode(t.ywxyxx,'01','有'，'02','没有') ywxyxxmc " +
                " from xg_zhdj_sgy_dyssgl t " +
                " left join view_xsjbxx a on t.dyxh=a.xh " +
                " left join XG_ZHDJ_DZBGL_DZBCY z on z.xh=t.dyxh" +
                " left join xg_zhdj_dzbgl_dzb b on z.dzbid=b.dzbid " +
                " left join fdyxxb d1 on d1.zgh=b.DZBSJ " +
                " left join XG_GYGL_NEW_LDXXB e on e.lddm=t.lddm  " +
                " where t.dyssid=?");
        return dao.getMapNotOut(sql.toString(),new String[]{model.getDyssid()});
    }

    public HashMap<String,String> getZjInfo(DyssglForm model) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from xg_zhdj_sgy_dysszjqk t" +
                " where t.dyssid=?");
        return dao.getMapNotOut(sql.toString(),new String[]{model.getDyssid()});
    }

    public boolean save_zj(DyssglForm model) throws Exception {
        String delSql = "delete from xg_zhdj_sgy_dysszjqk where dyssid=?";
        boolean rs = dao.runUpdate(delSql,new String[]{model.getDyssid()});
        if(rs){
            StringBuffer sql = new StringBuffer();
            String[] input = new String[]{model.getDyssid(),model.getWsqk(),model.getYdaq(),model.getSsxf(),
                    model.getYwdbxjxx(),model.getYwxyxx(),model.getYwxjssjcpp(),model.getYwxjssjcby(),model.getSfwmss()};
            sql.append("insert into xg_zhdj_sgy_dysszjqk values(" +
                    "?,?,?,?,?,?,?,?,?)");
            rs = dao.runUpdate(sql.toString(),input );
        }
        return rs;
    }
}
