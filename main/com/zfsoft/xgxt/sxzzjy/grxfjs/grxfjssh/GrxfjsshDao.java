package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjssh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-26 15:42
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GrxfjsshDao extends SuperDAOImpl<GrxfjsshForm> {
    @Override
    protected void setTableInfo() {
        super.setKey("sqid");
        super.setTableName("xg_sxzzjy_grxfjs_grxfjssqb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(GrxfjsshForm grxfjsshForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(GrxfjsshForm t, User user) throws Exception {
        //生成高级查询相关条件、条件值
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from (");
        sql.append(" select a.* from (");
        sql.append(" select t1.sqid,t1.xh,t1.xfjsmc,t1.sblx,t1.xn,t1.xq,t1.jssl,t1.sqsj,t1.sqr,t1.shzt,t1.splc,");
        sql.append(" t2.xm,t2.bjdm,t2.bjmc,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t1.xn || t7.xqmc xnxq,");
        sql.append(" t4.guid shid, t4.gwid,t4.shr,t4.shyj, ");
        sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', '未审核', '1', '通过','2','不通过','3','退回','4','需重审', '5', '审核中') || ']' shztmc, ");
        sql.append(" t6.gwz,row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
        sql.append(" ,(select sblxmc from xg_sxzzjy_grxfjs_sblxdmb where sblxdm=t1.sblx) sblxmc, ");
        sql.append(" 'sq' sqlx ,'申报' sqlxmc,'' jgid ");
        sql.append(" from xg_sxzzjy_grxfjs_grxfjssqb t1 ");
        sql.append("  left join VIEW_XSJBXX t2 on t2.xh = t1.xh");
        sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid ");
        String shlx = t.getShzt();
        if(!shlx.equals("dsh")){
            sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
        }else{
            sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
        }
        sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw ");
        sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
        sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
        sql.append(" where t5.spyh = '"+user.getUserName()+"' ");
        sql.append(" ) a ");

        sql.append("   union all ( " );
        sql.append(" select t.sqid,t1.xh,t1.xfjsmc,t1.sblx,t1.xn,t1.xq,t1.jssl,t.sqsj,t.sqr,t.shzt,t.splc,");
        sql.append(" t2.xm,t2.bjdm,t2.bjmc,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t1.xn || t7.xqmc xnxq,");
        sql.append(" t4.guid shid, t4.gwid,t4.shr,t4.shyj, ");
        sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', '未审核', '1', '通过','2','不通过','3','退回','4','需重审', '5', '审核中') || ']' shztmc, ");
        sql.append(" t6.gwz,row_number() over(partition by t.sqid order by t4.shsj desc) rn ");
        sql.append(" ,(select sblxmc from xg_sxzzjy_grxfjs_sblxdmb where sblxdm=t1.sblx) sblxmc, ");
        sql.append(" t.hblx sqlx ,decode(t.hblx, 'nzhb','中期汇报','nzzj','年终总结','') sqlxmc,t.jgid ");
        sql.append(" from xg_sxzzjy_grxfjs_grxfjshbsqb t ");
        sql.append("  left join xg_sxzzjy_grxfjs_grxfjsjgb t1 on t1.jgid = t.jgid");
        sql.append("  left join VIEW_XSJBXX t2 on t2.xh = t1.xh");
        sql.append(" left join xg_xtwh_shztb t4 on t.sqid = t4.ywid ");
        if(!shlx.equals("dsh")){
            sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
        }else{
            sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
        }
        sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw ");
        sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
        sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
        sql.append(" where t5.spyh = '"+user.getUserName()+"' ");
        sql.append(" )  ");
        sql.append(" ) t where 1=1 ");
        sql.append(" and  rn = 1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public boolean updateGrxfjssq(String sqid,String shzt) throws Exception{
        String[] inputV = new String[2];
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE xg_sxzzjy_grxfjs_grxfjssqb ");
        sql.append(" set ");
        sql.append(" shzt = ? ");
        sql.append(" where sqid = ? ");
        inputV[0] = shzt;
        inputV[1] = sqid;
        return dao.update(sql.toString(),inputV)>0 ? true:false;
    }

    /**
     * @描述:删除医疗保险申请
     * @param bbsqid
     * @return
     * @throws Exception
     * boolean 返回类型
     * @throws
     */
        public boolean deleteGrxfjsjg(String ywid) throws Exception{
        String[] inputV = new String[1];
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from xg_sxzzjy_grxfjs_grxfjsjgb ");
        sql.append(" where lcywid = ? ");
        inputV[0] = ywid;
        return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
    }

    /**
     *
     * @描述:取最新审核状态数据
     * @作者：沈晓波[工号：1123]
     * @日期：2016-4-5 下午03:06:58
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param sqid
     * @return
     * String 返回类型
     * @throws
     */
    public String getLastShxx(String sqid) {
        StringBuilder sql = new StringBuilder();
        sql.append("select t1.zd2 from (select b.mc, c.xm shr, a.*, rownum n from xg_xtwh_shztb a left join xg_xtwh_spgw b on a.gwid = b.id left join yhb c on a.shr = c.yhm where a.ywid = ? and shzt <> '0' order by a.shsj desc) t1 where t1.n = 1");
        return dao.getOneRs(sql.toString(), new String[] {sqid}, "zd2");
    }

    public String checkExistForSave(GrxfjsshForm model) {
        StringBuilder sql = new StringBuilder(
                " select count(1) num from xg_sxzzjy_grxfjs_grxfjsjgb where xh = ? and xn = ? and xh=? and sblx=? and sjly='1'");
        return dao.getOneRs(sql.toString(), new String[] {model.getXn(),model.getXq(),model.getXh(),model.getSblx()}, "num");
    }
}
