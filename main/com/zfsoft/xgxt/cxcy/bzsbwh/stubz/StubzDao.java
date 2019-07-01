package com.zfsoft.xgxt.cxcy.bzsbwh.stubz;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:创新创业-我的创新创业补助
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-11 09:10
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class StubzDao extends SuperDAOImpl<StubzForm> {
    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(StubzForm stubzForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(StubzForm t, User user) throws Exception {
        StringBuilder sql = new StringBuilder();
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        sql.append("select t.* from (");
        sql.append("select t1.*, z.xqmc,c.xm,c.xydm,c.xymc,c.zydm,c.zymc,c.bjdm,c.bjmc,c.nj,d.xm tbrmc from ( ");
        sql.append(" select a.xh,a.sqid id,a.xmmc,a.bzje,a.xn,a.xq,a.tbr,a.sqsj tbsj,a.shzt,a.splc,");
        sql.append(" decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',a.shzt) shztmc,'0' sjly ");
        sql.append(" from xg_new_cxcy_bzsqb a where 1=1 ");
        sql.append(" and xh='"+t.getXh()+"' ");
        sql.append(" union ");
        sql.append(" select b.xh,b.jgid id,b.xmmc,b.bzje,b.xn,b.xq,b.lrr tbr,b.lrsj tbsj,'1','', ");
        sql.append(" '' shztmc ,'1' sjly ");
        sql.append(" from xg_new_cxcy_bzjgb b where b.sjly='0' ");
        sql.append(" and xh='"+t.getXh()+"' ");
        sql.append(" ) t1  ");
        sql.append(" left join view_xsjbxx c on c.xh=t1.xh ");
        sql.append(" left join fdyxxb d on d.zgh=t1.tbr ");
        sql.append(" left join xqdzb z on z.xqdm=t1.xq ");
        sql.append(" ) t where 1=1  ");
        sql.append(searchTj);
        sql.append(" order by tbsj desc ");
        return getPageList(t, sql.toString(),inputV);
    }
}
