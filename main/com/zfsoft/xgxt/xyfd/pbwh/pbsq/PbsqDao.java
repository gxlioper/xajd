package com.zfsoft.xgxt.xyfd.pbwh.pbsq;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/7/30.
 */
public class PbsqDao extends SuperDAOImpl<PbsqForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(PbsqForm.class);
        super.setKey("sqid");
        super.setTableName("xg_xyfd_pbsqb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(PbsqForm pbsqForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(PbsqForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ( ");
        sql.append(" select a.*,decode(a.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',a.shzt) shztmc, ");
        sql.append(" b.xm,case when b.xb='1' then '男' when b.xb = '2' then '女' ");
        sql.append(" else b.xb end xb,c.fdsmc,c.fdsdd from xg_xyfd_pbsqb a  ");
        sql.append(" left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a ");
        sql.append(" left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c ");
        sql.append(" on a.zybj = c.bjdm) b on a.xh = b.xh left join XG_XYFD_FDSXXB c on a.fds = c.id  ");
        sql.append("  ) t where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * 奖助学金及表彰奖励
     * @param t
     * @param xn
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getJlxx(PbsqForm t,String xn) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_PJPY_NEW_PJJGB where xh = ? and xn = ? ");
        return dao.getListNotOut(sql.toString(),new String[]{t.getXh(),xn});
    }

    /**
     * 学生信息
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getXsxx(PbsqForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc, ");
        sql.append(" case when a.xb='1' then '男' when a.xb = '2' then '女' else a.xb end xbm ");
        sql.append(" from XSXXB a left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c");
        sql.append(" on a.zybj = c.bjdm) where xh = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getXh()});
    }

    /**
     * 保存朋辈志愿者申请
     * @param t
     * @return
     * @throws Exception
     */
    public boolean savePbsq(PbsqForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into xg_xyfd_pbsqb(xh,xsgbrz,lxdh,dzyx,fdpb,fdkm,fds,mon,tues,wed,thur,fri,sat,sun,fjid,)");
        return dao.runUpdate(sql.toString(),new String[]{});
    }

    /**
     * 获取审批流程
     * @return
     */
    public String getShlcID(String lx) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select splc from xg_xyfd_shlcszb where lx = ? ");
        return dao.getOneRs(sql.toString(), new String[] {lx}, "splc");
    }

    public HashMap<String,String> getPbxx(PbsqForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_xyfd_pbsqb where sqid = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getSqid()});
    }
}
