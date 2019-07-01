package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import com.zfsoft.ms.mail.util.BlankUtils;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DzbglDao extends SuperDAOImpl<DzbglForm> {
    @Override
    protected void setTableInfo() {
        this.setClass(DzbglForm.class);
        this.setKey("dzbhjid");
        this.setTableName("xg_zhdj_dzbgl_dzb");

    }

    @Override
    public List<HashMap<String, String>> getPageList(DzbglForm dzbglForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(DzbglForm dzbglForm, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(dzbglForm.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(dzbglForm.getSearchModel());

        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select * from (select x.*,c.MC jcdwmc  ");
        sql.append("  from (select a.* from xg_zhdj_dzbgl_dzb a where a.HJSJ=(select max(b.HJSJ) from xg_zhdj_dzbgl_dzb b where a.DZBID=b.DZBID) ");
        sql.append(" OR (a.HJSJ is null and (select count(1) from xg_zhdj_dzbgl_dzb c where a.DZBID=c.DZBID) =1 )) x  ");
        sql.append("   LEFT JOIN XG_ZHDJ_DZBGL_JCDWDMB_SYDMB c ON x.JCDWDM=c.DM) t where 1=1  ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(dzbglForm, sql.toString(), inputV);
    }

    public List<HashMap<String, String>> getJcdw() {
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.mc,a.dm,a.sydm,e.symc,g.dzblx,f.zwss from xg_zhdj_dzbgl_jcdwdmb_sydmb a left join xg_xtwh_sybjglb b on b.sydm = a.sydm ");
        sql.append(" left join view_njxyzybj c on c.bjdm = b.bjdm left join ZXBZ_XXBMDM d on c.xydm = d.bmdm  left join xg_xtwh_sydmb e on e.sydm = a.sydm ");
        sql.append(" left join xg_zhdj_dzbgl_dzb g on g.jcdwdm = a.dm left join xg_zhdj_dzbgl_zwdmb f on f.dm = g.dzblx ");
        sql.append(" group by a.mc,a.dm,a.sydm,e.symc,g.dzblx,f.zwss order by a.dm");
        return dao.getListNotOut(sql.toString(), new String[]{});

    }

    public List<HashMap<String, String>> getZgList(DzbglForm myForm, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select * from (select t.xm,t.zgh,t.bmdm,t.bmmc,t.zzmm,t.zzmmmc,t.lxdh from view_fdyxx t) a where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(myForm, sql.toString(), inputV);
    }

    public List<HashMap<String, String>> getXxList(DzbglForm model, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select * from (select v.XH,v.XM,v.XB,v.CSRQ,v.SFZH,v.RXRQ,v.ZYMC,v.BJMC,v.LXDH,v.XYDM,v.ZYDM,v.BJDM,V.XYMC  ");
        sql.append(",(select a.jtdh from view_xsxxb a where v.xh = a.XH ) as JTDH, ");
        sql.append(" (select a.jtdz from view_xsxxb a where v.xh = a.XH ) as JTDZ , ");
        sql.append(" (select a.mzmc from view_xsxxb a where v.xh = a.XH ) as MZMC from view_xsjbxx v)t  where 1=1  ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(model, sql.toString(), inputV);

    }

    public boolean checkRepeatDZB(DzbglForm model) {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        sql.append(" select count(1) cnt");
        sql.append(" from xg_zhdj_dzbgl_dzb");
        sql.append(" where dzbmc = ?");
        paraList.add(model.getDzbmc());
        String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
        return "0".equals(cnt) ? true : false;

    }

    public List<HashMap<String, String>> getDzb(DzbglForm model) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select a.*, s.symc ,s.mc as xx ");
        sql.append(",(select xm from view_fdyxx  where zgh =  a.dzbsj ) as sjxm, ");
        sql.append("(select xm from view_fdyxx  where zgh =  a.zzwy ) as zzwyxm, ");
        sql.append("(select xm from view_fdyxx  where zgh =  a.xcwy ) as xcwyxm, ");
        sql.append("(select xm from view_fdyxx  where zgh =  a.jjwy ) as jjwyxm  ");
        sql.append("from xg_zhdj_dzbgl_dzb a  left join  ");
        sql.append("(select * from  xg_zhdj_dzbgl_jcdwdmb_sydmb x left join xg_xtwh_sydmb b on x.sydm = b.sydm ) s on a.jcdwdm = s.dm");
        if (StringUtils.isNotNull(model.getDzbhjid())) {
            sql.append(" where a.dzbhjid = ? ");
            params.add(model.getDzbhjid());
        }
        return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));

    }

    public List<HashMap<String, String>> getljDzbList(DzbglForm model) {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select a.dzbmc,a.clsj,a.hjsj, s.symc as symc  ,s.mc as xx,y.* ");
        sql.append("from xg_zhdj_dzbgl_dzb a  left join  xg_zhdj_dzbgl_zwglb y on y.dzbhjid = a.dzbhjid left join  ");
        sql.append("(select * from  xg_zhdj_dzbgl_jcdwdmb_sydmb x left join xg_xtwh_sydmb b on x.sydm = b.sydm ) s on a.jcdwdm = s.dm   ");
        if (StringUtils.isNotNull(model.getDzbmc())) {
            sql.append(" where a.dzbmc = ?  ");
            params.add(model.getDzbmc());
        }
        sql.append("  order by a.hjsj ");
        return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
    }

    public boolean checkCyInDzb(DzbglForm model) {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        sql.append(" select count(1) cnt");
        sql.append(" from xg_zhdj_dzbgl_dzbcy");
        sql.append(" where dzbid = ?");
        paraList.add(model.getDzbid());
        String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
        return "0".equals(cnt) ? true : false;
    }

    public boolean delCy(DzbglForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        String dzbid = model.getDzbid();
        sql.append(" delete from xg_zhdj_dzbgl_dzb where dzbid = ? ");
        paraList.add(dzbid);
        return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
    }

    public boolean delZw(DzbglForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        String dzbid = model.getDzbid();
        sql.append(" delete from xg_zhdj_dzbgl_zwglb where dzbid = ? ");
        paraList.add(dzbid);
        return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
    }

    public boolean saveZwb(DzbglForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        String dzbid = model.getDzbid();
        boolean sum = true;
        sql.append(" insert into xg_zhdj_dzbgl_zwglb (dzbid,zwid,zwmc,lxrid,lxrmc,lxrdh) values(?,?,?,?,?,?) ");
        if (model.getDzblx().equals("学生党支部") && model.getXsmcs() != null) {
            for (int i = 0; i < model.getXsmcs().length; i++) {
                String[] zwmcs = model.getZwmcs();
                String[] xhs = model.getXhs();
                String[] xsmcs = model.getXsmcs();
                String[] xsdhs = model.getXsdhs();
                String[] zwdms = model.getZwdms();
                paraList.add(dzbid);
                paraList.add(zwdms[i]);
                paraList.add(zwmcs[i]);
                paraList.add(xhs[i]);
                paraList.add(xsmcs[i]);
                paraList.add(xsdhs[i]);
                sum = dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
                if (!sum) {
                    break;
                }
                paraList = new ArrayList<String>();
            }
        } else if (model.getDzblx().equals("教工党支部") && model.getJzgmcs() != null) {
            for (int i = 0; i < model.getJzgmcs().length; i++) {
                String[] jgzwmcs = model.getJgzwmcs();
                String[] lxrzghs = model.getLxrzghs();
                String[] jzgmcs = model.getJzgmcs();
                String[] jzgdhs = model.getJzgdhs();
                String[] jgzwdms = model.getJgzwdms();
                paraList.add(dzbid);
                paraList.add(jgzwdms[i]);
                paraList.add(jgzwmcs[i]);
                paraList.add(lxrzghs[i]);
                paraList.add(jzgmcs[i]);
                paraList.add(jzgdhs[i]);
                sum = dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
                if (!sum) {
                    break;
                }
                paraList = new ArrayList<String>();
            }
        }
        return sum;
    }

    public List<HashMap<String, String>> getdzbInfo(DzbglForm model) {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("select * from xg_zhdj_dzbgl_dzb a left join xg_zhdj_dzbgl_jcdwdmb_sydmb b on a.jcdwdm = b.dm where 1=1 ");
        if (StringUtils.isNotNull(model.getDzbhjid())) {
            sql.append(" and a.dzbhjid = ? ");
            params.add(model.getDzbhjid());
        }
        if (StringUtils.isNotNull(model.getDzbid())) {
            sql.append(" and a.dzbid = ? ");
            params.add(model.getDzbid());
        }
        return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));

    }

    public List<HashMap<String, String>> getDzbCy(DzbglForm model, User user) {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select a.*,x.xm,x.zymc,x.zydm,x.bjdm,x.xydm,x.bjmc,x.lxdh,d.dzbmc,z.zzmmmc,d.dzblx, ");
        sql.append("(case a.sfsl when  '0' then '否' when  '1' then '是' else '否' end ) as sl,");
        sql.append("  (case a.sfld when  '0' then '否' when  '1' then '是' else '否' end ) as ld,   ");
        sql.append("(case x.xb when  '1' then '男' when  '2' then '女' else '未知' end ) as xb   ");
        sql.append(" from xg_zhdj_dzbgl_dzbcy a left join xsxxb x on a.xh = x.xh  ");
        sql.append(" left join (select l.dzbid,l.dzbmc,l.dzblx from  xg_zhdj_dzbgl_dzb l group by l.dzbid,l.dzbmc,l.dzblx) d on a.dzbid = d.dzbid ");
        sql.append("  left join zzmmdmb z on a.zzmmdm = z.zzmmdm  where 1=1 ");

        if (StringUtils.isNotNull(model.getDzbid())) {
            sql.append(" and a.dzbid = ? ");
            params.add(model.getDzbid());
        }
        return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
    }

    public List<HashMap<String, String>> getJgdzbCy(DzbglForm model, User user) {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select * from (select a.*,x.xm,x.bmmc,x.lxdh,d.dzbmc,z.zzmmmc,d.dzblx,   ");
        sql.append("(case a.sfsl when  '0' then '否' when  '1' then '是' else '否' end ) as sl,");
        sql.append("  (case a.sfld when  '0' then '否' when  '1' then '是' else '否' end ) as ld,   ");
        sql.append(" x.xb   ");
        sql.append(" from xg_zhdj_dzbgl_dzbcy a left join view_fdyxx x on a.xh = x.zgh  ");
        sql.append(" left join (select l.dzbid,l.dzbmc,l.dzblx from  xg_zhdj_dzbgl_dzb l group by l.dzbid,l.dzbmc,l.dzblx) d on a.dzbid = d.dzbid ");
        sql.append("  left join zzmmdmb z on a.zzmmdm = z.zzmmdm )t where 1=1  ");
        if (StringUtils.isNotNull(model.getDzbid())) {
            sql.append(" and t.dzbid = ? ");
            params.add(model.getDzbid());
        }
        return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
    }


    public List<HashMap<String, String>> getDzbZwList(DzbglForm model, User user) {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder("  select a.* from xg_zhdj_dzbgl_zwglb a  ");
        sql.append("  left join (select l.dzbid, l.dzbmc from xg_zhdj_dzbgl_dzb l group by l.dzbid, l.dzbmc) d  ");
        sql.append("  on a.dzbid = d.dzbid where 1 = 1 ");
        if (StringUtils.isNotNull(model.getDzbid())) {
            sql.append(" and a.dzbid = ? ");
            params.add(model.getDzbid());
        }
        return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
    }


    public List<HashMap<String, String>> getInfoNoHjId(DzbglForm model) {
        List<String> params = new ArrayList<String>();
        StringBuilder sql = new StringBuilder(" select * from (select x.*from (select a.* from xg_zhdj_dzbgl_dzb a   ");
        sql.append(" ,(select a.dzbmc,max(a.hjsj) max_day from xg_zhdj_dzbgl_dzb a group by a.dzbmc) b   ");
        sql.append(" where (a.dzbmc=b.dzbmc and a.hjsj = b.max_day)    or ( a.dzbmc=b.dzbmc and a.hjsj is null ) ) x) t where 1=1 ");
        if (StringUtils.isNotNull(model.getDzbid())) {
            sql.append(" and t.dzbid = ? ");
            params.add(model.getDzbid());
        }
        return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));

    }

    public List<HashMap<String, String>> getDCList(DzbglForm t, User user) throws Exception {
        // TODO 自动生成方法存根
        List<String> params = new ArrayList<String>();
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder(" select * from (select x.*  ");
        sql.append("  from (select a.* from xg_zhdj_dzbgl_dzb a ");
        sql.append(" ,(select a.dzbmc,max(a.hjsj) max_day from xg_zhdj_dzbgl_dzb a group by a.dzbmc) b  ");
        sql.append("   where (a.dzbmc=b.dzbmc and a.hjsj = b.max_day)  or ( a.dzbmc=b.dzbmc and a.hjsj is null )) x) t where 1=1  ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return dao.getListNotOut(sql.toString(), inputV);

    }


    /**
     * @return List<HashMap < String , String>> 返回类型
     * @throws
     * @描述:TODO(这里用一句话描述这个方法的作用)
     * @作者：张昌路[工号：982]
     * @日期：2018-12-27 上午11:59:53
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getJgdzbList() {
        StringBuilder sql = new StringBuilder();
        sql.append("  select a.dm as jgzwdm ,a.mc as jgzwmc,a.zwss,a.zwlx from XG_ZHDJ_DZBGL_ZWDMB a  where a.zwss='教工党支部' group by a.dm,a.mc,a.zwss,a.zwlx");
        return dao.getListNotOut(sql.toString(), new String[]{});
    }

    public List<HashMap<String, String>> getXsdzbList() {
        StringBuilder sql = new StringBuilder();
        sql.append("  select a.dm as zwdm ,a.mc as zwmc,a.zwss,a.zwlx from XG_ZHDJ_DZBGL_ZWDMB a  where a.zwss='学生党支部' group by a.dm,a.mc,a.zwss,a.zwlx ");
        return dao.getListNotOut(sql.toString(), new String[]{});
    }

    /**
     * @return List<HashMap < String , String>> 返回类型
     * @throws
     * @描述:TODO(这里用一句话描述这个方法的作用)
     * @作者：张昌路[工号：982]
     * @日期：2018-12-29 上午08:56:17
     * @修改记录: 修改者名字-修改日期-修改内容
     */

    public List<HashMap<String, String>> getJgdzbZwList(DzbglForm model) {
        StringBuilder sql = new StringBuilder();
        List<String> params = new ArrayList<String>();
        sql.append(" select distinct a.dm as jgzwdm,a.mc as jgzwmc,a.zwss,a.zwlx,b.*,c.dzbid from   ");
        sql.append(" XG_ZHDJ_DZBGL_ZWDMB a left join (SELECT * FROM xg_zhdj_dzbgl_zwglb where DZBHJID=?) b on a.dm =b.zwid  ");
        sql.append("  left join XG_ZHDJ_DZBGL_dzb c  on b.DZBHJID =c.DZBHJID  ");
        if (StringUtils.isNotNull(model.getDzbhjid())) {
            params.add(model.getDzbhjid());
        }
        sql.append(" where a.zwss = '教工党支部' ");
        return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
    }

    /**
     * @return List<HashMap < String , String>> 返回类型
     * @throws
     * @描述:TODO(这里用一句话描述这个方法的作用)
     * @作者：张昌路[工号：982]
     * @日期：2018-12-29 上午08:58:56
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public List<HashMap<String, String>> getXsdzbZwList(DzbglForm model) {
        StringBuilder sql = new StringBuilder();
        List<String> params = new ArrayList<String>();

        sql.append(" select distinct a.dm as zwdm,a.mc as zwmc,a.zwss,a.zwlx,b.*,c.dzbid from  ");
        sql.append(" XG_ZHDJ_DZBGL_ZWDMB a left join (SELECT * FROM xg_zhdj_dzbgl_zwglb where DZBHJID=?) b on a.dm =b.zwid ");
        sql.append("  left join XG_ZHDJ_DZBGL_dzb c  on b.DZBHJID =c.DZBHJID ");
        if (StringUtils.isNotNull(model.getDzbhjid())) {
            params.add(model.getDzbhjid());
        }
        sql.append(" where a.zwss = '学生党支部' ");
        return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
    }

    /**
     * @param model
     * @return boolean 返回类型
     * @throws Exception
     * @throws
     * @描述:TODO(这里用一句话描述这个方法的作用)
     * @作者：张昌路[工号：982]
     * @日期：2018-12-29 上午09:32:09
     * @修改记录: 修改者名字-修改日期-修改内容
     */
    public boolean updateZwb(DzbglForm model, String zt) throws Exception {
        StringBuilder sql = new StringBuilder(" UPDATE xg_zhdj_dzbgl_zwglb set lxrid = ?,zwmc=?,lxrmc=?,lxrdh=?,dzbhjid =? where dzbid = ? and zwid=?");
        StringBuilder sqlcount = new StringBuilder("select count(*) cnt from xg_zhdj_dzbgl_zwglb where dzbhjid = ? and zwmc=?");
        StringBuilder sqlinsert = new StringBuilder(" insert into xg_zhdj_dzbgl_zwglb (lxrid,zwmc,lxrmc,lxrdh,dzbhjid,dzbid,zwid) values(?,?,?,?,?,?,?) ");
        List<String> paraList = new ArrayList<String>();
        String dzbid = model.getDzbid();
        boolean sum = false;
        if (!BlankUtils.isBlank(Arrays.asList(model.getXsmcs())) || BlankUtils.isBlank(Arrays.asList(model.getJzgmcs()))) {
            if (model.getDzblx().equals("学生党支部")) {
                for (int i = 0; i < model.getXsmcs().length; i++) {
                    String[] zwmcs = model.getZwmcs();
                    String[] xhs = model.getXhs();
                    String[] xsmcs = model.getXsmcs();
                    String[] xsdhs = model.getXsdhs();
                    String[] zwdms = model.getZwdms();
                    paraList.add(xhs[i]);
                    paraList.add(zwmcs[i]);
                    paraList.add(xsmcs[i]);
                    paraList.add(xsdhs[i]);
                    paraList.add(model.getDzbhjid());
                    paraList.add(dzbid);
                    paraList.add(zwdms[i]);
                    if (zt.equals("hj")) {
                        sum = dao.runUpdate(sqlinsert.toString(), paraList.toArray(new String[]{}));
                    } else {
                        String cnt = dao.getOneRs(sqlcount.toString(), new String[]{model.getDzbhjid(), zwmcs[i]}, "cnt");
                        if (cnt.equals("1")) {
                            sum = dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
                        } else {
                            sum = dao.runUpdate(sqlinsert.toString(), paraList.toArray(new String[]{}));
                        }
                    }
                    if (!sum) {
                        break;
                    }
                    paraList = new ArrayList<String>();
                }
            } else if (model.getDzblx().equals("教工党支部")) {
                for (int i = 0; i < model.getJzgmcs().length; i++) {
                    String[] jgzwmcs = model.getJgzwmcs();
                    String[] lxrzghs = model.getLxrzghs();
                    String[] jzgmcs = model.getJzgmcs();
                    String[] jzgdhs = model.getJzgdhs();
                    String[] jgzwdms = model.getJgzwdms();
                    paraList.add(lxrzghs[i]);
                    paraList.add(jgzwmcs[i]);
                    paraList.add(jzgmcs[i]);
                    paraList.add(jzgdhs[i]);
                    paraList.add(model.getDzbhjid());
                    paraList.add(dzbid);
                    paraList.add(jgzwdms[i]);
                    if (zt.equals("hj")) {
                        sum = dao.runUpdate(sqlinsert.toString(), paraList.toArray(new String[]{}));
                    } else {
                        String cnt = dao.getOneRs(sqlcount.toString(), new String[]{model.getDzbhjid(), jgzwmcs[i]}, "cnt");
                        if (cnt.equals("1")) {
                            sum = dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
                        } else {
                            sum = dao.runUpdate(sqlinsert.toString(), paraList.toArray(new String[]{}));
                        }
                    }
                    if (!sum) {
                        break;
                    }
                    paraList = new ArrayList<String>();
                }
            }
        }
        return sum;
    }

    public String getJgCount() {

        String sql = "select count(1) count from XG_ZHDJ_DZBGL_ZWDMB a where a.zwss='教工党支部'";

        return dao.getOneRs(sql, new String[]{}, "count");
    }

    public String getXsCount() {

        String sql = "select count(1) count from XG_ZHDJ_DZBGL_ZWDMB a where a.zwss='学生党支部'";

        return dao.getOneRs(sql, new String[]{}, "count");
    }

}
