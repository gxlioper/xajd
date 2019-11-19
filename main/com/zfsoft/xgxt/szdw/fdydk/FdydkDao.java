package com.zfsoft.xgxt.szdw.fdydk;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class FdydkDao extends SuperDAOImpl<FdydkForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(FdydkForm.class);
        super.setKey("id");
        super.setTableName("SZDW_FDY_FDYDKJGB");
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdydkForm fdydkForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdydkForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from (");
        sql.append(" select a.*,b.mc kclbmc,c.xm,c.bmdm from SZDW_FDY_FDYDKJGB a ");
        sql.append(" left join XG_SZDW_KCLBB b on a.kclbdm = dm ");
        sql.append(" left join FDYXXB c on a.zgh = c.ZGH ");
        sql.append(" ) t where 1 = 1 ");
//        sql.append(searchTjByUser);//û�а༶�ֶ��޷�ʹ��
        if(!"xx".equalsIgnoreCase(user.getUserStatus())){
            sql.append(" bmdm = '"+user.getUserDep()+"' " );
        }
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
    * @description: TODO ����Ա��Ϣ�б�
    * @param zgh
    * @return java.util.HashMap<java.lang.String,java.lang.String>
    * @author Wang ChenHui
    * @date 2019/5/24 9:43
    */
    public HashMap<String,String> getFdyxx(String zgh) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from view_fdyxx where zgh = ?");
        return dao.getMapNotOut(sql.toString(),new String[]{zgh});
    }

    /**
    * @description: TODO �γ��б�
    * @param
    * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
    * @author Wang ChenHui
    * @date 2019/5/24 9:43
    */
    public List<HashMap<String,String>> getKcList() {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_SZDW_KCLBB");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }

    /**
    * @description: TODO ����Ա������Ϣ
    * @param id
    * @return java.util.HashMap<java.lang.String,java.lang.String>
    * @author Wang ChenHui
    * @date 2019/5/24 10:28
    */
    public HashMap<String,String> getFdyDkxx(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.mc kclbmc from SZDW_FDY_FDYDKJGB a left join XG_SZDW_KCLBB b on a.kclbdm = b.dm  where a.id = ?");
        return dao.getMapNotOut(sql.toString(),new String[]{id});
    }
}
