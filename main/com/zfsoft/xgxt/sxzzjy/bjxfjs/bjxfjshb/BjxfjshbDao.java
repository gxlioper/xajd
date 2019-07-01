package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-07-24 14:21
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxfjshbDao extends SuperDAOImpl<BjxfjshbForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_sxzzjy_bjxfjs_bjxfjshbsqb");
        super.setKey("sqid");
        super.setClass(BjxfjshbForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjxfjshbForm bjxfjshbForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjxfjshbForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");

        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (select t.*,case t.nzhbcount when 0 then '未汇报' else t.hbshztmc end nzhbshztmc, ");
        sql.append(" case t.nzzjcount when 0 then '未汇报' else t.zjshztmc end nzzjshztmc ");
        sql.append(" from ( ");
        sql.append(" select a.jgid,a.bjdm,a.xfjsmc,a.sblx,a.xn,a.xq,a.bxnmb,a.jssl,a.lrsj,a.lrr,a.fjid,a.sjly,a.lcywid,");
        sql.append(" b.xydm,b.xymc,b.zydm,b.zymc,b.bjmc,b.nj,c.sblxmc,d.xm,a.xn||e.xqmc xnxq,e.xqmc,");
        sql.append(" f.shzt  nzhbshzt,g.shzt  nzzjshzt,f.sqid nzhbid,g.sqid nzzjid,f.splc nzhbsplc,g.splc nzzjsplc,");
        sql.append(" decode(f.shzt,'0','未提交','1','通过','2','不通过', '3','退回','4','需重审','5','审核中','','无需审核',f.shzt) hbshztmc,");
        sql.append(" decode(g.shzt,'0','未提交','1','通过','2','不通过', '3','退回','4','需重审','5','审核中','','无需审核',g.shzt) zjshztmc,");
        sql.append(" (select count(0) from xg_sxzzjy_bjxfjs_bjxfjshbsqb z where hblx='nzhb' and z.jgid=a.jgid) nzhbcount ,");
        sql.append(" (select count(0) from xg_sxzzjy_bjxfjs_bjxfjshbsqb z where hblx='nzzj' and z.jgid=a.jgid) nzzjcount ");
        sql.append(" from   xg_sxzzjy_bjxfjs_bjxfjsjgb a ");
        sql.append(" left join VIEW_NJXYZYBJ b on b.bjdm = a.bjdm");
        sql.append(" left join xg_sxzzjy_bjxfjs_sblxdmb c on c.sblxdm=a.sblx" );
        sql.append(" left join fdyxxb d on d.zgh=a.lrr" );
        sql.append(" left join xqdzb e on e.xqdm=a.xq");
        sql.append(" left join (select * from xg_sxzzjy_bjxfjs_bjxfjshbsqb where hblx='nzhb') f on f.jgid=a.jgid");
        sql.append(" left join (select * from xg_sxzzjy_bjxfjs_bjxfjshbsqb where hblx='nzzj') g on g.jgid=a.jgid");
        sql.append(" ) t ) a where 1 = 1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * @描述:获取审批流程
     * @作者：lgx [工号：1553]
     * @日期：2018/7/25 14:57
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: []
     * @return: java.lang.String
     */
    public String getShlcID(String hblx) {
        StringBuffer sql = new StringBuffer();
        if("nzhb".equals(hblx)){
            sql.append(" select splc_nzhb splc from xg_sxzzjy_bjxfjs_jcszb ");
        }
        if("nzzj".equals(hblx)){
            sql.append(" select splc_nzzj splc from xg_sxzzjy_bjxfjs_jcszb ");
        }
        return dao.getOneRs(sql.toString(), new String[] {}, "splc");
    }

    public String checkExistForSave(BjxfjshbForm model) {
        String sqid = "-1";
        if(!StringUtils.isNull(model.getSqid())){
            sqid = model.getSqid();
        }
        String[] arr = new String[] { model.getHblx(),model.getJgid(),sqid};
        StringBuilder sql = new StringBuilder(
                " select count(1) num from xg_sxzzjy_bjxfjs_bjxfjshbsqb where hblx = ? and jgid = ? and sqid <> ? ");
        String num = dao.getOneRs(sql.toString(), arr, "num");
        return num;
    }

    public int bjxfjshbDel(String[] jgids, String[] hblxs) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from xg_sxzzjy_bjxfjs_bjxfjshbsqb where jgid in (");
        for(int i=0;i<jgids.length-1;i++){
            sql.append("?,");
        }
        sql.append("?) and (hblx = ");
        for(int i=0;i<hblxs.length;i++){
            sql.append("'"+hblxs[i]+"'");
            if(i<hblxs.length-1){
                sql.append(" or ");
            }
        }
        sql.append(") ");
       return dao.runDelete(sql.toString(),jgids);
    }

    public boolean updateBjxfjshb(BjxfjshbForm model) throws Exception {
        String[] inputV = new String[3];
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE xg_sxzzjy_bjxfjs_bjxfjshbsqb ");
        sql.append(" set ");
        sql.append(" shzt = ?,");
        sql.append(" splc = ? ");
        //sql.append(" sqsj = ?");
        sql.append(" where sqid = ?");
        inputV[0] = model.getShzt();
        inputV[1] = model.getSplc();
        //inputV[2] = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss");
        inputV[2] = model.getSqid();
        return dao.update(sql.toString(),inputV)>0;
    }

    public HashMap<String,String> getInfo(BjxfjshbForm model) {
        String sql = "select a.*,decode(shzt,'0','未提交','1','通过','2','不通过', '3','退回','4','需重审','5','审核中','','无需审核',shzt) shztmc " +
                " from xg_sxzzjy_bjxfjs_bjxfjshbsqb a where jgid=? and hblx=?";
        return dao.getMapNotOut(sql,new String[]{model.getJgid(),model.getHblx()});
    }

    /**
     * @描述:修改审核状态
     * @作者：lgx [工号：1553]
     * @日期：2018/7/30 15:44
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [sqid, shzt]
     * @return: boolean
     */
    public boolean updateSq(String sqid, String shzt) throws Exception {
        String[] inputV = new String[2];
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE xg_sxzzjy_bjxfjs_bjxfjshbsqb ");
        sql.append(" set ");
        sql.append(" shzt = ? ");
        sql.append(" where sqid = ? ");
        inputV[0] = shzt;
        inputV[1] = sqid;
        return dao.update(sql.toString(),inputV)>0 ;
    }

    public HashMap<String,String> getWordInfo(String id,String hblx) {
        String sql = "select a.*,c.sblxmc,b.xn,b.bjdm from xg_sxzzjy_bjxfjs_bjxfjshbsqb a " +
                " left join xg_sxzzjy_bjxfjs_bjxfjsjgb b on b.jgid=a.jgid " +
                " left join xg_sxzzjy_bjxfjs_sblxdmb c on c.sblxdm=b.sblx where a.jgid=? and a.hblx=?";
        return dao.getMapNotOut(sql,new String[]{id,hblx});
    }
}
