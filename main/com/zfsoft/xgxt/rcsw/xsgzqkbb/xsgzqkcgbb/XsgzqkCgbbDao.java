package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkcgbb;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * 安徽农业大学
 * 学生工作情况常规报表dao.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:12
 */
public class XsgzqkCgbbDao extends SuperDAOImpl<XsgzqkCgbbForm> {
    /**
     * 用于设置DAO所对应的表
     */
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_rcsw_xsgzqk_cgbb");
        super.setKey("id");
        super.setClass(XsgzqkCgbbForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(XsgzqkCgbbForm xsgzqkCgbbForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(XsgzqkCgbbForm xsgzqkCgbbForm, User user) throws Exception {

        //生成高级查询相关条件、条件值
        SearchModel searchModel = xsgzqkCgbbForm.getSearchModel();
        String searchTj = SearchService.getSearchTj(searchModel);
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "bsdw");
        String[] inputV = SearchService.getTjInput(searchModel);

        StringBuilder sql = new StringBuilder("SELECT * FROM (");
        sql.append("SELECT a.id,a.xn,a.xq,b.xqmc,a.bszt,a.bsnr,a.bsr,d.xm bsrmc,a.bssj,");
        sql.append("a.bsdw,c.bmmc bsdwmc FROM xg_rcsw_xsgzqk_cgbb a ");
        sql.append("LEFT JOIN xqdzb b ON a.xq = b.xqdm ");
        sql.append("LEFT JOIN zxbz_xxbmdm c ON a.bsdw = c.bmdm ");
        sql.append("LEFT JOIN fdyxxb d ON a.bsr = d.zgh");
        sql.append(") t WHERE 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);

        return getPageList(xsgzqkCgbbForm, sql.toString(), inputV);
    }

    /**
     *  根据id，查询一条常规报表信息.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-19 15:28
     * @param id
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String, String> getCgbbById(String id) throws Exception {

        StringBuilder sql = new StringBuilder("SELECT a.id,a.xn,a.xq,b.xqmc,a.bszt,a.bsr,d.xm bsrmc, ");
        sql.append("a.bssj,a.bsdw,c.bmmc bsdwmc,a.bsnr,a.fjid ");
        sql.append("FROM xg_rcsw_xsgzqk_cgbb a ");
        sql.append("LEFT JOIN xqdzb b ON a.xq = b.xqdm ");
        sql.append("LEFT JOIN zxbz_xxbmdm c ON a.bsdw = c.bmdm ");
        sql.append("LEFT JOIN fdyxxb d ON a.bsr = d.zgh ");
        sql.append("WHERE a.id = ?");

        return dao.getMapNotOut(sql.toString(),new String[]{id});
    }
}
