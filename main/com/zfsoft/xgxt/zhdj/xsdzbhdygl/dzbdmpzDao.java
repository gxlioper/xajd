package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class dzbdmpzDao extends SuperDAOImpl<dzbdmpzForm> {
    @Override
    protected void setTableInfo() {
        this.setClass(dzbdmpzForm.class);
        this.setKey("dm");
        this.setTableName("xg_zhdj_dzbgl_jcdwdmb_SYDMB");

    }

    @Override
    public List<HashMap<String, String>> getPageList(dzbdmpzForm dzbdmpzForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(dzbdmpzForm dzbdmpzForm, User user) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select a.dm, a.mc,c.symc,");
        sql.append("(select count(*) from (select dzb.dzbid,dzb.dzbmc,dzb.jcdwdm,dzb.dzblx,max(dzb.hjsj) hjsj  from XG_ZHDJ_DZBGL_DZB dzb group by dzb.DZBID,dzb.DZBMC,dzb.DZBLX,dzb.jcdwdm) b where b.JCDWDM = a.DM and b.DZBLX = '教工党支部') jgdzbs,");
        sql.append("(select count(*) from (select dzb.dzbid,dzb.dzbmc,dzb.jcdwdm,dzb.dzblx,max(dzb.hjsj) hjsj  from XG_ZHDJ_DZBGL_DZB dzb group by dzb.DZBID,dzb.DZBMC,dzb.DZBLX,dzb.jcdwdm) b where b.JCDWDM = a.DM and b.DZBLX = '学生党支部') xsdzbs,");
        sql.append("(select count(*) from XG_ZHDJ_DZBGL_DZBCY d left join (select dzb.dzbid,dzb.dzbmc,dzb.dzblx,dzb.jcdwdm,max(dzb.hjsj) hjsj  from XG_ZHDJ_DZBGL_DZB dzb group by dzb.DZBID,dzb.DZBMC,dzb.DZBLX,dzb.jcdwdm) e on d.DZBID = e.DZBID where e.JCDWDM = a.dm and e.DZBLX = '教工党支部') jgdrs,");
        sql.append("(select count(*) from XG_ZHDJ_DZBGL_DZBCY d left join (select dzb.dzbid,dzb.dzbmc,dzb.dzblx,dzb.jcdwdm,max(dzb.hjsj) hjsj  from XG_ZHDJ_DZBGL_DZB dzb group by dzb.DZBID,dzb.DZBMC,dzb.DZBLX,dzb.jcdwdm) e on d.DZBID = e.DZBID where e.JCDWDM = a.dm and e.DZBLX = '学生党支部') xsdrs ");
        sql.append("from xg_zhdj_dzbgl_jcdwdmb_sydmb a left join xg_xtwh_sydmb c on c.sydm = a.sydm");
        return getPageList(dzbdmpzForm, sql.toString(), params.toArray(new String[]{}));
    }

    public List<HashMap<String, String>> getXyList(dzbdmpzForm myForm, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select *  from(select a.sydm as sydm ,a.symc as symc from xg_xtwh_sydmb a) t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(myForm, sql.toString(), inputV);


    }


    public boolean checkRepeatMC(dzbdmpzForm model) {
        // TODO 自动生成方法存根
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        sql.append(" select count(1) cnt");
        sql.append(" from xg_zhdj_dzbgl_jcdwdmb_sydmb");
        sql.append(" where mc = ?");
        paraList.add(model.getMc());
        String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
        return "0".equals(cnt) ? true : false;

    }

    public boolean checkRepeatDM(dzbdmpzForm model) {
        // TODO 自动生成方法存根
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        sql.append(" select count(1) cnt");
        sql.append(" from xg_zhdj_dzbgl_jcdwdmb_sydmb");
        sql.append(" where dm = ?");
        paraList.add(model.getDm());
        String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
        return "0".equals(cnt) ? true : false;
    }

    public boolean delDm(dzbdmpzForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        String[] dms = model.getDms();
        sql.append(" delete from xg_zhdj_dzbgl_jcdwdmb_sydmb where dm in(");
        for (int i = 0; i < dms.length; i++) {
            sql.append("?");
            if (i != dms.length - 1) {
                sql.append(",");
            }
            paraList.add(dms[i]);
        }
        sql.append(" )");
        return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
    }

    public List<HashMap<String, String>> getXymc(dzbdmpzForm model) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select t.sydm as sydm ,t.symc as symc from xg_xtwh_sydmb t  where sydm = ?");
        params.add(model.getSydm());
        return getPageList(model, sql.toString(), params.toArray(new String[]{}));

    }

    public List<HashMap<String, String>> getSyList(dzbdmpzForm myForm, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select *  from(select a.sydm as sydm ,a.symc as symc from xg_xtwh_sydmb a) t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(myForm, sql.toString(), inputV);


    }

    public List<HashMap<String, String>> getDCList(dzbdmpzForm t, User user) throws Exception {
        // TODO 自动生成方法存根
        List<String> params = new ArrayList<String>();
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder(" select * from (select a.dm, a.mc,c.symc from xg_zhdj_dzbgl_jcdwdmb_sydmb a left join xg_xtwh_sydmb c on c.sydm = a.sydm)");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return dao.getListNotOut(sql.toString(), inputV);

    }

    /**
     * @param model
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @description: TODO
     * @author Wang ChenHui
     * @date 2019/5/23 15:37
     */
    public List<HashMap<String, String>> getDzzList(dzbdmpzForm model) {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.DM,a.MC,b.DM zwdm,b.MC zwmc,a.clsj,a.hjsj,c.symc from xg_zhdj_dzbgl_jcdwdmb_sydmb a ");
        sql.append("left join XG_ZHDJ_DZBGL_ZWDMB b on b.ZWSS = '党总支' ");
        sql.append("left join XG_XTWH_SYDMB c on c.SYDM = a.SYDM ");
        sql.append("where a.dm = ?");
        return dao.getListNotOut(sql.toString(), new String[]{model.getDm()});
    }

    /**
     * @param model
     * @return boolean
     * @description: TODO 党总支换届更新
     * @author Wang ChenHui
     * @date 2019/5/21 15:40
     */
    public boolean hjDzz(dzbdmpzForm model) throws Exception {
        boolean sum = false;
        StringBuilder sql2 = new StringBuilder(" update XG_ZHDJ_DZZGL_ZWGLB set zt = '0' where dzzid = ? ");
        sum = dao.runUpdate(sql2.toString(), new String[]{model.getDzzid()});
        if (sum) {
            StringBuilder sql = new StringBuilder(" insert into XG_ZHDJ_DZZGL_ZWGLB(lxrid,zwmc,lxrxm,lxrdh,hjsj,dzzid,zwdm,zt) values(?,?,?,?,?,?,?,?)");
            List<String> paraList = new ArrayList<>();
            for (int i = 0; i < model.getZwdms().length; i++) {
                String[] lxrids = model.getLxrzghs();
                String[] zwmcs = model.getZwmcs();
                String[] lxrxms = model.getJzgmcs();
                String[] lxrdhs = model.getJzgdhs();
                String dzzid = model.getDzzid();
                String[] zwdms = model.getZwdms();
                paraList.add(lxrids[i]);
                paraList.add(zwmcs[i]);
                paraList.add(lxrxms[i]);
                paraList.add(lxrdhs[i]);
                paraList.add(model.getHjsj());
                paraList.add(dzzid);
                paraList.add(zwdms[i]);
                paraList.add("1");
                sum = dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
                paraList = new ArrayList<String>();
            }
        }
        if (sum) {
            StringBuilder sql1 = new StringBuilder(" update XG_ZHDJ_DZBGL_JCDWDMB_SYDMB set hjsj=? where dm = ?");
            sum = dao.runUpdate(sql1.toString(), new String[]{model.getHjsj(), model.getDzzid()});
        }
        return sum;
    }


    /**
     * @param dzzid
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @description: TODO 党总支换届详情
     * @author Wang ChenHui
     * @date 2019/5/23 16:20
     */
    public List<HashMap<String, String>> getDzzDe(String dzzid) {
        StringBuilder sql = new StringBuilder("select a.DM zwdm,a.mc zwmc,b.lxrxm,b.lxrid,b.lxrdh,b.dzzid from XG_ZHDJ_DZBGL_ZWDMB a ");
        sql.append("left join XG_ZHDJ_DZZGL_ZWGLB b on a.DM = b.zwdm ");
        sql.append("left join XG_ZHDJ_DZBGL_JCDWDMB_SYDMB c on b.dzzid = c.dm ");
        sql.append("where a.ZWSS = '党总支' and zt = '1' and c.dm = ?");
        return dao.getListNotOut(sql.toString(), new String[]{dzzid});
    }

    /**
     * @param dzzid
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @description: TODO 历届党总支详情
     * @author Wang ChenHui
     * @date 2019/5/23 16:20
     */
    public List<HashMap<String, String>> getljDzz(String dzzid) {
        StringBuilder sql = new StringBuilder("select b.lxrxm,b.lxrid,b.lxrdh,b.dzzid,b.hjsj ljsj,b.zwmc,b.zwdm from XG_ZHDJ_DZBGL_ZWDMB a ");
        sql.append("left join XG_ZHDJ_DZZGL_ZWGLB b on a.DM = b.zwdm ");
        sql.append("where a.ZWSS = '党总支' and zt = '0' and b.dzzid = ?");
        return dao.getListNotOut(sql.toString(), new String[]{dzzid});
    }
}
