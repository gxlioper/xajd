package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkybb.xyybb;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 安徽农业大学
 * 学生工作情况学院月报表dao.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:14
 */
public class XsgzqkXyYbbDao extends SuperDAOImpl<XsgzqkXyYbbForm> {
    /**
     * 用于设置DAO所对应的表
     */
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_rcsw_xsgzqk_xyybb");
        super.setKey("id");
        super.setClass(XsgzqkXyYbbForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(XsgzqkXyYbbForm xsgzqkXyYbbForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(XsgzqkXyYbbForm xsgzqkXyYbbForm, User user) throws Exception {

        //生成高级查询相关条件、条件值
        SearchModel searchModel = xsgzqkXyYbbForm.getSearchModel();
        String searchTj = SearchService.getSearchTj(searchModel);
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm");
        String[] inputV = SearchService.getTjInput(searchModel);

        StringBuilder sql = new StringBuilder("SELECT * FROM (");
        sql.append("SELECT a.id,a.xn,a.xq,b.xqmc,a.yf,a.xydm,c.bmmc xymc,a.xsgzfzr,d.xm xsgzfzrmc,a.tbr,e.xm tbrmc,a.tbrq, ");

        sql.append("(SELECT nvl(count(bjdm),0) FROM xg_rcsw_xsgzqk_bjybb WHERE xyybbid = a.id )|| ");
        sql.append("'/'||(SELECT count(bjdm) FROM VIEW_NJXYZYBJ WHERE XYDM = a.xydm) ytbjrsb ");

        sql.append("FROM xg_rcsw_xsgzqk_xyybb a ");
        sql.append("LEFT JOIN xqdzb b ON a.xq = b.xqdm ");
        sql.append("LEFT JOIN zxbz_xxbmdm c ON a.xydm = c.bmdm ");
        sql.append("LEFT JOIN fdyxxb d ON a.xsgzfzr = d.zgh ");
        sql.append("LEFT JOIN fdyxxb e ON a.tbr = e.zgh ");
        sql.append(") t WHERE 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);

        return getPageList(xsgzqkXyYbbForm, sql.toString(), inputV);
    }

    /**
     *  根据id查询一条学院月报表信息.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-25 15:54
     * @param id
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String,String> getXyYbbById(String id) {

        StringBuilder sql = new StringBuilder("SELECT a.id,a.xn,a.xq,b.xqmc,a.yf,a.xydm,c.bmmc xymc,a.xsgzfzr,d.xm xsgzfzrmc,a.tbr,e.xm tbrmc,a.tbrq ");
        sql.append("FROM xg_rcsw_xsgzqk_xyybb a ");
        sql.append("LEFT JOIN xqdzb b ON a.xq = b.xqdm ");
        sql.append("LEFT JOIN zxbz_xxbmdm c ON a.xydm = c.bmdm ");
        sql.append("LEFT JOIN fdyxxb d ON a.xsgzfzr = d.zgh ");
        sql.append("LEFT JOIN fdyxxb e ON a.tbr = e.zgh ");
        sql.append("WHERE a.id = ?");
        return dao.getMapNotOut(sql.toString(),new String[]{id});
    }

    /**
     *  根据学年学期学院月份判断是否已存在数据.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-25 15:55
     * @param xsgzqkXyYbbForm
     * @return boolean
     * @throw
     */
    public boolean isXyYfRepeat(XsgzqkXyYbbForm xsgzqkXyYbbForm) {

        StringBuilder sql = new StringBuilder("SELECT count(1) num FROM xg_rcsw_xsgzqk_xyybb ");
        sql.append("WHERE xn = ? AND xq = ? AND xydm = ? AND yf = ? ");

        List<String> inputList = new ArrayList<String>();
        inputList.add(xsgzqkXyYbbForm.getXn());
        inputList.add(xsgzqkXyYbbForm.getXq());
        inputList.add(xsgzqkXyYbbForm.getXydm());
        inputList.add(xsgzqkXyYbbForm.getYf());

        if(!StringUtil.isNull(xsgzqkXyYbbForm.getId())){
            sql.append("and id <> ? ");
            inputList.add(xsgzqkXyYbbForm.getId());
        }
        String [] inputValue = inputList.toArray(new String[]{});
        String num = dao.getOneRs(sql.toString(),inputValue,"num");
        return Integer.valueOf(num)>0;
    }

    /**
     *  根据学院月报表id查询是否存在班级月报数据.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-28 15:26
     * @param idArr
     * @return boolean
     * @throw
     */
    public boolean isBjYbbDataExists(String[] idArr) {

        StringBuilder sql = new StringBuilder("SELECT count(id) num FROM xg_rcsw_xsgzqk_bjybb WHERE xyybbid IN (");
        for(int i=0;i<idArr.length;i++){
            sql.append("?");
            if(i != idArr.length-1){
                sql.append(",");
            }
        }
        sql.append(")");
        String num = dao.getOneRs(sql.toString(),idArr,"num");
        return Integer.valueOf(num) > 0;
    }
    public List<HashMap<String, String>> getFdybsbList(String id ) throws Exception {

    	 StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.id,a.xyybbid,a.bjdm,decode(grouping(a.id),1,'-',(b.nj||b.xymc||b.zymc||b.bjmc)) bjmc,b.zydm,b.xydm,decode(grouping(a.id),1,'合计',t3.dbfdy) dbfdy,sum(a.mxss) mxss,sum(a.wxss) wxss,sum(a.zkbhcs) zkbhcs, ");
        sql.append("sum(a.bjhdkzcs) bjhdkzcs,sum(a.srsscs) srsscs,sum(a.ssthcs) ssthcs, ");
        sql.append("sum(a.gbtkcs) gbtkcs,sum(a.yjzlxqk) yjzlxqk,decode(grouping(a.id),1,'-',a.tfsjclqk) tfsjclqk,sum(a.xxrs) xxrs,sum(a.fxrs) fxrs, ");
        sql.append("sum(a.txrs) txrs,sum(a.qtrs) qtrs,a.bz FROM xg_rcsw_xsgzqk_bjybb  a ");
        sql.append("LEFT JOIN VIEW_NJXYZYBJ b ON a.bjdm = b.bjdm ");
        sql.append("LEFT JOIN (SELECT WM_CONCAT(t2.XM) dbfdy,t1.BJDM FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH GROUP BY t1.BJDM) t3 ");
        sql.append("ON a.bjdm = t3.bjdm WHERE a.xyybbid = ? ");
        sql.append("group by rollup((a.id,a.xyybbid,b.nj,b.xymc,b.zymc,a.bjdm,b.bjmc,b.zydm,b.xydm,t3.dbfdy,a.mxss,a.wxss,a.zkbhcs,a.bjhdkzcs,a.srsscs,a.ssthcs,  ");
        sql.append("a.gbtkcs,a.yjzlxqk,a.tfsjclqk,a.xxrs,a.fxrs,a.txrs,a.qtrs,a.bz)) ");
        return dao.getListNotOut(sql.toString(), new String[] { id});
    }
    public String getzsh(String id ) throws Exception {

   	 StringBuilder sql = new StringBuilder();
       sql.append("select (SELECT count(bjdm) FROM VIEW_NJXYZYBJ WHERE XYDM = a.xydm)zsh FROM xg_rcsw_xsgzqk_xyybb a where  a.id = ?");
       return dao.getOneRs(sql.toString(), new String[]{id}, "zsh");
   }
}
