package com.zfsoft.xgxt.xyfd.fdswh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/6/24.
 */
public class FdsDao extends SuperDAOImpl<FdsForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(FdsForm.class);
        super.setKey("id");
        super.setTableName("xg_xyfd_fdsxxb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdsForm fdsForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdsForm t, User user) throws Exception {
        //���ɸ߼���ѯ�������������ֵ
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select * from xg_xyfd_fdsxxb where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * ����������
     * @param t
     * @return
     * @throws Exception
     */
    public boolean saveFds(FdsForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into xg_xyfd_fdsxxb(fdsmc,fdsdd,syksrq,syjsrq,sykssj,syjssj,yxzt,qkms) ");
        sql.append(" values(?,?,?,?,?,?,?,? ) ");
        String[] input = new String[]{t.getFdsmc(),t.getFdsdd(),t.getSyksrq(),t.getSyjsrq(),t.getSykssj(),t.getSyjssj(),t.getYxzt(),t.getQkms()};
        return dao.runUpdate(sql.toString(),input);
    }

    /**
     * ���¸�����
     * @param t
     * @return
     * @throws Exception
     */
    public boolean updateFds(FdsForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("update xg_xyfd_fdsxxb set fdsmc = ? ,fdsdd = ? ,syksrq = ? ,syjsrq = ? ,sykssj = ?,syjssj = ?,yxzt = ?,qkms = ? ");
        sql.append(" where id = ? ");
        String[] in = new String[]{t.getFdsmc(),t.getFdsdd(),t.getSyksrq(),t.getSyjsrq(),t.getSykssj(),
                t.getSyjssj(),t.getYxzt(),t.getQkms(),t.getId()};
        return dao.runUpdate(sql.toString(),in);
    }

    /**
     * ���Ҹ�����
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getFds(FdsForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_xyfd_fdsxxb where id = ?");
        String[] input = new String[]{t.getId()};
        return dao.getMapNotOut(sql.toString(),input);
    }

}
