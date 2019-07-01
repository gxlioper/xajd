package com.zfsoft.xgxt.zhdj.dzbyd;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DzbydDao extends SuperDAOImpl<DzbydForm> {
    @Override
    protected void setTableInfo() {
        this.setClass(DzbydForm.class);
        this.setKey("xh ");
        this.setTableName("XG_ZHDJ_DZBGL_DZBYD");
    }

    @Override
    public List<HashMap<String, String>> getPageList(DzbydForm dzbydForm) throws Exception {
        return null;
    }

    /**
     * @param dzbydForm
     * @param user
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @description: TODO 异动列表
     * @author Wang ChenHui
     * @date 2019/5/23 10:22
     */
    @Override
    public List<HashMap<String, String>> getPageList(DzbydForm dzbydForm, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(dzbydForm.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(dzbydForm.getSearchModel());
        StringBuilder sql = new StringBuilder("select * from (select a.XH,b.xm,b.xb,b.MZ,b.MZDM,b.CSRQ,b.NJ,b.SYDM1 sydm,b.SYMC1 symc,b.ZYDM,b.ZYMC,b.BJMC,b.BJDM,b.ZYBJMC,b.ZYBJ,b.ZZMMMC,b.ZZMM,");
        sql.append("c.DZBMC,c.DZBID,nvl(d.DZBMC,'无') yddzbmc,d.DZBID yddzbid,c.dzblx from ");
        sql.append("(select dzb.dzbid,dzb.dzbmc,dzb.dzblx,max(dzb.hjsj) hjsj  from XG_ZHDJ_DZBGL_DZB dzb group by dzb.DZBID,dzb.DZBMC,dzb.DZBLX) c ");
        sql.append("left join XG_ZHDJ_DZBGL_DZBYD a on a.dzbid = c.DZBID ");
        sql.append("inner join VIEW_XSJBXX b on a.xh = b.XH  ");
        sql.append("left join (select dzb.dzbid,dzb.dzbmc,dzb.dzblx,max(dzb.hjsj) hjsj  from XG_ZHDJ_DZBGL_DZB dzb group by dzb.DZBID,dzb.DZBMC,dzb.DZBLX) d on a.yddzbid = d.DZBID ");
        sql.append(")t where 1=1 and t.dzblx = '学生党支部' ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(dzbydForm, sql.toString(), inputV);
    }

    /**
     * @param model
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @description: TODO 获取异动详情
     * @author Wang ChenHui
     * @date 2019/5/23 10:21
     */

    public HashMap<String, String> getDzbydInfo(DzbydForm model) {
        StringBuilder sql = new StringBuilder("select * from (select a.XH,b.xm,b.xb,b.MZ,b.MZDM,b.CSRQ,b.NJ,b.SYDM1 sydm,b.SYMC1 symc,b.ZYDM,b.ZYMC,b.BJMC,b.BJDM,b.ZYBJMC,b.ZYBJ,b.ZZMMMC,b.ZZMM,");
        sql.append("c.DZBMC,c.DZBID,nvl(d.DZBMC,'无') yddzbmc,d.DZBID yddzbid,c.dzblx from ");
        sql.append("(select dzb.dzbid,dzb.dzbmc,dzb.dzblx,max(dzb.hjsj) hjsj  from XG_ZHDJ_DZBGL_DZB dzb group by dzb.DZBID,dzb.DZBMC,dzb.DZBLX) c ");
        sql.append("left join XG_ZHDJ_DZBGL_DZBYD a on a.dzbid = c.DZBID ");
        sql.append("inner join VIEW_XSJBXX b on a.xh = b.XH  ");
        sql.append("left join (select dzb.dzbid,dzb.dzbmc,dzb.dzblx,max(dzb.hjsj) hjsj  from XG_ZHDJ_DZBGL_DZB dzb group by dzb.DZBID,dzb.DZBMC,dzb.DZBLX) d on a.yddzbid = d.DZBID ");
        sql.append(")t where 1=1 and t.dzblx = '学生党支部' and t.xh =?");
        return dao.getMapNotOut(sql.toString(), new String[]{model.getXh()});
    }

    /**
     * @param model
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @description: TODO 获取学生党支部列表
     * @author Wang ChenHui
     * @date 2019/5/23 10:21
     */
    public List<HashMap<String, String>> getXsDzb(DzbydForm model) {
        StringBuilder sql = new StringBuilder();
        sql.append("select dzbid,dzbmc from (select dzb.dzbid,dzb.dzbmc,dzb.jcdwdm,dzb.dzblx,max(dzb.hjsj) hjsj  from XG_ZHDJ_DZBGL_DZB dzb group by dzb.DZBID,dzb.DZBMC,dzb.DZBLX,dzb.jcdwdm) where dzblx = '学生党支部'");
        return dao.getListNotOut(sql.toString(), new String[]{});
    }

    /**
     * @param model
     * @return boolean
     * @description: TODO 验证学号是否已经存在异动情况
     * @author Wang ChenHui
     * @date 2019/5/23 10:30
     */
    public String checkIsExit(DzbydForm model) {
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) count from XG_ZHDJ_DZBGL_DZBYD where xh = ?");
        return dao.getOneRs(sql.toString(), new String[]{model.getXh()}, new String("count"));
    }

    /**
     * @param model
     * @param user
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @description: TODO 导出列表
     * @author Wang ChenHui
     * @date 2019/5/23 10:45
     */
    public List<HashMap<String, String>> getDcList(DzbydForm model, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(model.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(model.getSearchModel());
        StringBuilder sql = new StringBuilder("select * from (select a.XH,b.xm,b.xb,b.MZ,b.MZDM,b.CSRQ,b.NJ,b.SYDM1 sydm,b.SYMC1 symc,b.ZYDM,b.ZYMC,b.BJMC,b.BJDM,b.ZYBJMC,b.ZYBJ,b.ZZMMMC,b.ZZMM,");
        sql.append("c.DZBMC,c.DZBID,nvl(d.DZBMC,'无') yddzbmc,d.DZBID yddzbid,c.dzblx from ");
        sql.append("(select dzb.dzbid,dzb.dzbmc,dzb.dzblx,max(dzb.hjsj) hjsj  from XG_ZHDJ_DZBGL_DZB dzb group by dzb.DZBID,dzb.DZBMC,dzb.DZBLX) c ");
        sql.append("left join XG_ZHDJ_DZBGL_DZBYD a on a.dzbid = c.DZBID ");
        sql.append("inner join VIEW_XSJBXX b on a.xh = b.XH  ");
        sql.append("left join (select dzb.dzbid,dzb.dzbmc,dzb.dzblx,max(dzb.hjsj) hjsj  from XG_ZHDJ_DZBGL_DZB dzb group by dzb.DZBID,dzb.DZBMC,dzb.DZBLX) d on a.yddzbid = d.DZBID ");
        sql.append(")t where 1=1 and t.dzblx = '学生党支部' ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return dao.getListNotOut(sql.toString(), inputV);
    }

    /**
     * @param model
     * @return boolean
     * @description: TODO 更新异动情况
     * @author Wang ChenHui
     * @date 2019/5/23 11:47
     */
    public boolean update(DzbydForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("update XG_ZHDJ_DZBGL_DZBYD set yddzbid = ?,dzbid =? where xh = ?");
        return dao.runUpdate(sql.toString(), new String[]{model.getYddzbid(),model.getDzbid(), model.getXh()});
    }

    /**
     * @param model
     * @return boolean
     * @description: TODO 新增异动情况
     * @author Wang ChenHui
     * @date 2019/5/23 11:46
     */
    public boolean insert(DzbydForm model) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into XG_ZHDJ_DZBGL_DZBYD(xh,dzbid,yddzbid) values(?,?,?)");
        return dao.runUpdate(sql.toString(), new String[]{model.getXh(), model.getDzbid(), model.getYddzbid()});
    }

    /**
     * @param values
     * @return int
     * @description: TODO 删除异动情况
     * @author Wang ChenHui
     * @date 2019/5/23 11:46
     */
    public boolean deleteYd(String[] values) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<>();
        sql.append("delete XG_ZHDJ_DZBGL_DZBYD where xh in (");
        for (int i = 0; i < values.length; i++) {
            sql.append("?");
            if (i != values.length - 1) {
                sql.append(",");
            }
            paraList.add(values[i]);
        }
        sql.append(" )");
        return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
    }
}
