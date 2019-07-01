package com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhsq;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-05 15:27
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BzsbwhsqDao extends SuperDAOImpl<BzsbwhsqForm>{
    @Override
    protected void setTableInfo() {
        super.setKey("sqid");
        super.setTableName("xg_new_cxcy_bzsqb");
        super.setClass(BzsbwhsqForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(BzsbwhsqForm bzsbwhsqForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(BzsbwhsqForm t, User user) throws Exception {
        StringBuilder sql = new StringBuilder();
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        sql.append("select t.* from ( ");
        sql.append(" select a.*,z.xqmc,b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj,c.xm tbrmc,");
        sql.append(" decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',a.shzt) shztmc ");
        sql.append(" from xg_new_cxcy_bzsqb a ");
        sql.append(" left join view_xsjbxx b on b.xh=a.xh ");
        sql.append(" left join fdyxxb c on c.zgh=a.tbr ");
        sql.append(" left join xqdzb z on z.xqdm=a.xq ");
        sql.append(" ) t  where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        sql.append(" order by sqsj desc ");
        return getPageList(t, sql.toString(),inputV);
    }

    public boolean checkExistForSave(BzsbwhsqForm model) {
        boolean flag = false;
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        paraList.add(model.getXh());
        paraList.add(model.getXn());
        paraList.add(model.getXq());
        paraList.add(model.getXmmc());
        sql.append("select sqid from xg_new_cxcy_bzsqb t where t.xh = ? and t.xn = ? and t.xq = ? and t.xmmc = ? ");
        if(StringUtils.isNotNull(model.getSqid())){
            sql.append(" and sqid <> ? ");
            paraList.add(model.getSqid());
        }
        String num = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}), "sqid");
        if (num != null && ! num.equals("") ){
            flag = true;
        }
        return flag;
    }

    public HashMap<String,String> getInfoById(String sqid) {
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from ( ");
        sql.append(" select a.*,z.xqmc,b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj,c.xm tbrmc,");
        sql.append(" decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',a.shzt) shztmc ");
        sql.append(" from xg_new_cxcy_bzsqb a ");
        sql.append(" left join view_xsjbxx b on b.xh=a.xh ");
        sql.append(" left join fdyxxb c on c.zgh=a.tbr ");
        sql.append(" left join xqdzb z on z.xqdm=a.xq ");
        sql.append(" ) t  where sqid=? ");
        return dao.getMapNotOut(sql.toString(),new String[]{sqid});
    }


}
