package com.zfsoft.xgxt.cxcy.sbwh.xmsb;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:创新创业-上报维护-项目上报
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-07 09:15
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XmsbDao extends SuperDAOImpl<XmsbForm> {
    @Override
    protected void setTableInfo() {
        super.setKey("id");
        super.setTableName("xg_new_cxcy_xmsbb");
        super.setClass(XmsbForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(XmsbForm xmsbForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(XmsbForm t, User user) throws Exception {
        StringBuilder sql = new StringBuilder();
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm");
        sql.append("select t.* from ( ");
        sql.append(" select a.*,z.xqmc,c.xm tbrmc,b.bmmc xymc ");
        sql.append(" from xg_new_cxcy_xmsbb a ");
        sql.append(" left join fdyxxb c on c.zgh=a.tbr ");
        sql.append(" left join ZXBZ_XXBMDM b on b.bmdm=a.xydm ");
        sql.append(" left join xqdzb z on z.xqdm=a.xq ");
        sql.append(" ) t  where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        sql.append(" order by tbsj desc ");
        return getPageList(t, sql.toString(),inputV);
    }

    public boolean checkExistForSave(XmsbForm model) {
        boolean flag = false;
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        paraList.add(model.getXn());
        paraList.add(model.getXq());
        paraList.add(model.getBgr());
        paraList.add(model.getXmmc());
        sql.append("select id from xg_new_cxcy_xmsbb t where ");
        sql.append("t.xn = ? and t.xq = ?  and t.bgr = ? and xmmc=?  ");
        if(StringUtils.isNotNull(model.getId())){
            sql.append(" and id <> ? ");
            paraList.add(model.getId());
        }
        String num = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}), "id");
        if (num != null && ! num.equals("") ){
            flag = true;
        }
        return flag;
    }

    public HashMap<String,String> getInfoById(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from ( ");
        sql.append(" select a.*,z.xqmc,c.xm tbrmc,b.bmmc xymc  ");
        sql.append(" from xg_new_cxcy_xmsbb a ");
        sql.append(" left join fdyxxb c on c.zgh=a.tbr ");
        sql.append(" left join ZXBZ_XXBMDM b on b.bmdm=a.xydm ");
        sql.append(" left join xqdzb z on z.xqdm=a.xq ");
        sql.append(" ) t  where 1=1 and id=? ");
        return dao.getMapNotOut(sql.toString(),new String[]{id});
    }
}
