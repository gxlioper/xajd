package com.zfsoft.xgxt.jjgl.jjlsjg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * 西交大：家教老师结果dao.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-11-19 16:44
 */
public class JjlsjgDao extends SuperDAOImpl<JjlsjgForm> {
    /**
     * 用于设置DAO所对应的表
     */
    @Override
    protected void setTableInfo() {
        super.setClass(JjlsjgForm.class);
        super.setKey("xh");
        super.setTableName("XG_JJGL_JJLSJGB");
    }

    @Override
    public List<HashMap<String, String>> getPageList(JjlsjgForm jjlsjgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(JjlsjgForm jjlsjgForm, User user) throws Exception {

        String searchTj = SearchService.getSearchTj(jjlsjgForm.getSearchModel());
        String[] inputV = SearchService.getTjInput(jjlsjgForm.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT t.* FROM (");
        sql.append("SELECT t1.*,t2.XM,t2.xb,t2.BJDM,t2.BJMC,t2.ZYDM,t2.ZYMC,t2.XYDM,t2.XYMC,t2.SYDM1 SYDM,");
        sql.append("t2.SYMC1 SYMC,t2.NJ,t3.XM DJRXM ");
        sql.append("FROM XG_JJGL_JJLSJGB t1 LEFT JOIN VIEW_XSJBXX t2 ON t1.XH = t2.XH ");
        sql.append("LEFT JOIN YHB t3 ON t1.DJR = t3.YHM ");
        sql.append(" ) t WHERE 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(jjlsjgForm, sql.toString(), inputV);
    }

    public boolean isRepeat(JjlsjgForm jjlsjgForm) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT count(1) num FROM XG_JJGL_JJLSJGB WHERE xh = ? ");
        String num = dao.getOneRs(sql.toString(), new String[] {jjlsjgForm.getXh()}, "num");
        return Integer.valueOf(num) > 0;
    }

    /**
     * @描述:重写：查询一条申请详细信息
     * @作者：xuwen[工号：1426]
     * @日期：2017年5月9日 上午11:52:50
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @return
     * boolean 返回类型
     * @throws Exception
     */
    public JjlsjgForm getModel(String xh) throws Exception{

        String sql = "select t1.*,t2.jjnjmc from XG_JJGL_JJLSJGB t1 left join XSGGFW_JJFW_JJNJDMB t2 on t1.jjnj=t2.jjnjdm where xh = ?";
        return super.getModel(sql, new String[]{xh});
    }
}
