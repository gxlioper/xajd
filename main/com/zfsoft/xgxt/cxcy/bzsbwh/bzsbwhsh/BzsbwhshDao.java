package com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhsh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:创新创业补助申报审核
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-06 10:38
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BzsbwhshDao extends SuperDAOImpl<BzsbwhshForm> {
    @Override
    protected void setTableInfo() {
        super.setKey("sqid");
        super.setTableName("xg_new_cxcy_bzsqb");
        super.setClass(BzsbwhshForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(BzsbwhshForm bzsbwhshForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(BzsbwhshForm t, User user) throws Exception {
        StringBuilder sql = new StringBuilder();
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        sql.append("select t.* from ( ");
        sql.append(" select a.*,z.xqmc,b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj,c.xm tbrmc,");
        sql.append(" t4.shzt shztx,t4.guid shid,t4.gwid,t4.shr,t4.shyj,");
        sql.append(" t6.mc || '[' || decode(t4.shzt,'0', '未审核', '1','通过','2','不通过', '3','退回', '4','需重审','5','审核中') || ']' shztmc,");
        sql.append(" t6.gwz, row_number() over(partition by a.sqid order by t4.shsj desc) rn");
        sql.append(" from xg_new_cxcy_bzsqb a ");
        sql.append(" left join view_xsjbxx b on b.xh=a.xh ");
        sql.append(" left join fdyxxb c on c.zgh=a.tbr ");
        sql.append(" left join xqdzb z on z.xqdm=a.xq ");

        sql.append(" left join xg_xtwh_shztb t4 on a.sqid = t4.ywid");
        sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw");
        sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id");
        sql.append(" where t5.spyh = '" + user.getUserName() + "'  ");
        String shlx = t.getShzt();
        if (!shlx.equals("dsh")) {
            sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
        } else {
            sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
        }
        sql.append(" ) t  where 1 = 1");
        sql.append(" and  rn = 1 ");
        sql.append(" ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        sql.append(" order by sqsj desc");
        return getPageList(t, sql.toString(), inputV);
    }

    public boolean deleteJg(String xn, String xq, String xh, String xmmc) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        paraList.add(xn);
        paraList.add(xh);
        paraList.add(xq);
        paraList.add(xmmc);
        sql.append(" delete from xg_new_cxcy_bzjgb where xn = ?  and xh = ?");
        sql.append(" and xq = ? and xmmc = ?");
        return dao.runUpdate(sql.toString(), paraList.toArray(new String[]{}));
    }
}