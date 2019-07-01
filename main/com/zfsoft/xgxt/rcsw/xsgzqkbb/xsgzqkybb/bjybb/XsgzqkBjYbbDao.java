package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkybb.bjybb;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ����ũҵ��ѧ
 * ѧ����������༶�±���dao.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:14
 */
public class XsgzqkBjYbbDao extends SuperDAOImpl<XsgzqkBjYbbForm> {
    /**
     * ��������DAO����Ӧ�ı�
     */
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_rcsw_xsgzqk_bjybb");
        super.setKey("id");
        super.setClass(XsgzqkBjYbbForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(XsgzqkBjYbbForm xsgzqkBjYbbForm) throws Exception {

        //���ɸ߼���ѯ�������������ֵ
        SearchModel searchModel = xsgzqkBjYbbForm.getSearchModel();
        String searchTj = SearchService.getSearchTj(searchModel);
        String[] inputV = SearchService.getTjInput(searchModel);

        StringBuilder sql = new StringBuilder("SELECT * FROM (");
        sql.append("SELECT a.id,a.xyybbid,a.bjdm,decode(grouping(a.id),1,'-',(b.nj||b.xymc||b.zymc||b.bjmc)) bjmc,b.zydm,b.xydm,decode(grouping(a.id),1,'�ϼ�',t3.dbfdy) dbfdy,sum(a.mxss) mxss,sum(a.wxss) wxss,sum(a.zkbhcs) zkbhcs, ");
        sql.append("sum(a.bjhdkzcs) bjhdkzcs,sum(a.srsscs) srsscs,sum(a.ssthcs) ssthcs, ");
        sql.append("sum(a.gbtkcs) gbtkcs,sum(a.yjzlxqk) yjzlxqk,decode(grouping(a.id),1,'-',a.tfsjclqk) tfsjclqk,sum(a.xxrs) xxrs,sum(a.fxrs) fxrs, ");
        sql.append("sum(a.txrs) txrs,sum(a.qtrs) qtrs,a.bz FROM xg_rcsw_xsgzqk_bjybb  a ");
        sql.append("LEFT JOIN VIEW_NJXYZYBJ b ON a.bjdm = b.bjdm ");
        sql.append("LEFT JOIN (SELECT WM_CONCAT(t2.XM) dbfdy,t1.BJDM FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH GROUP BY t1.BJDM) t3 ");
        sql.append("ON a.bjdm = t3.bjdm WHERE a.xyybbid = ? ");
        sql.append("group by rollup((a.id,a.xyybbid,b.nj,b.xymc,b.zymc,a.bjdm,b.bjmc,b.zydm,b.xydm,t3.dbfdy,a.mxss,a.wxss,a.zkbhcs,a.bjhdkzcs,a.srsscs,a.ssthcs,  ");
        sql.append("a.gbtkcs,a.yjzlxqk,a.tfsjclqk,a.xxrs,a.fxrs,a.txrs,a.qtrs,a.bz)) ");
        sql.append(") t WHERE 1=1 ");
        sql.append(searchTj);

        String [] newInputV= new String[inputV.length+1];
        System.arraycopy(inputV,0,newInputV,1,inputV.length);
        newInputV[0] = xsgzqkBjYbbForm.getXyybbid();

        return getPageList(xsgzqkBjYbbForm, sql.toString(), newInputV);
    }

    @Override
    public List<HashMap<String, String>> getPageList(XsgzqkBjYbbForm xsgzqkBjYbbForm, User user) throws Exception {
        return null;
    }

    /**
     *  ��ѯ�༶�б�.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-27 15:55
     * @param xsgzqkBjYbbForm
     * @param user
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     * @throw
     */
    public List<HashMap<String,String>> getBjList(XsgzqkBjYbbForm xsgzqkBjYbbForm, User user) throws Exception{

        //���ɸ߼���ѯ�������������ֵ
        String searchTj = SearchService.getSearchTj(xsgzqkBjYbbForm.getSearchModel());
        String[] inputValue = SearchService.getTjInput(xsgzqkBjYbbForm.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm");

        StringBuilder sql = new StringBuilder();

        sql.append(" select * from ( ");
        sql.append(" select a.bjrs,d.mxss,(a.bjrs-d.mxss) wxss,b.*,c.dbfdy from ");
        sql.append(" (select * from view_njxyzybj where xydm = ?) b ");

        sql.append(" left join (select bjdm,nvl(count(1),0) bjrs  from view_xsjbxx group by bjdm ");
        sql.append(" ) a on b.bjdm=a.bjdm ");

        sql.append("left join (");
        sql.append(" select bjdm,nvl(count(1),0) mxss  from view_xsjbxx where xb = '��' group by bjdm ");
        sql.append(" ) d on b.bjdm = d.bjdm ");

        sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON b.BJDM = c.BJDM ");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        sql.append(searchTjByUser);

        String[] newInputValue = new String[inputValue.length+1];
        System.arraycopy(inputValue,0,newInputValue,1,inputValue.length);
        newInputValue[0] = xsgzqkBjYbbForm.getXydm();

        return getPageList(xsgzqkBjYbbForm, sql.toString(), newInputValue);
    }

    /**
     *  ����ѧԺ�±���id��ѯѧԺ����.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-27 16:33
     * @param xyybbid
     * @return java.lang.String
     * @throw
     */
    public String getXydmByXyybbid(String xyybbid) {

        String sql = "SELECT xydm FROM xg_rcsw_xsgzqk_xyybb WHERE id = ?";
        return dao.getOneRs(sql,new String[] {xyybbid},"xydm");
    }

    /**
     *  ����xyybbid,bjdm�жϰ༶�±��������Ƿ��Ѿ�����.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-28 09:03
     * @param xsgzqkBjYbbForm
     * @return boolean
     * @throw
     */
    public boolean isBjYfRepeat(XsgzqkBjYbbForm xsgzqkBjYbbForm) {

        StringBuilder sql = new StringBuilder("SELECT count(1) num FROM xg_rcsw_xsgzqk_bjybb WHERE xyybbid = ? AND bjdm = ? ");
        List<String> inputList = new ArrayList<String>();
        inputList.add(xsgzqkBjYbbForm.getXyybbid());
        inputList.add(xsgzqkBjYbbForm.getBjdm());

        if(!StringUtil.isNull(xsgzqkBjYbbForm.getId())){
            sql.append("AND id <> ? ");
            inputList.add(xsgzqkBjYbbForm.getId());
        }
        String [] inputValue = inputList.toArray(new String[]{});
        String num = dao.getOneRs(sql.toString(),inputValue,"num");
        return Integer.valueOf(num)>0;
    }

    /**
     *  ����xyybbid��id��ѯһ���༶�±�����Ϣ.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-28 09:16
     * @param id
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throw
     */
    public HashMap<String,String> getBjYbbById(String id) {

        StringBuilder sql = new StringBuilder("SELECT a.id,a.xyybbid,a.bjdm,(b.nj||b.xymc||b.zymc||b.bjmc) bjmc,b.zydm,b.xydm,a.mxss,a.wxss,a.zkbhcs,a.bjhdkzcs,a.srsscs,a.ssthcs, ");
        sql.append("a.gbtkcs,a.yjzlxqk,a.tfsjclqk,a.xxrs,a.fxrs,a.txrs,a.qtrs,a.bz FROM xg_rcsw_xsgzqk_bjybb a ");
        sql.append("LEFT JOIN VIEW_NJXYZYBJ b ON a.bjdm = b.bjdm ");
//        sql.append("LEFT JOIN (SELECT WM_CONCAT(t2.XM) dbfdy,t1.BJDM FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH GROUP BY t1.BJDM) t3 ");
//        sql.append("ON a.bjdm = t3.bjdm  ");
        sql.append("WHERE  a.id = ?");

        return dao.getMapNotOut(sql.toString(),new String[]{id});
    }
}
