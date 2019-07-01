package com.zfsoft.xgxt.dtjs.shsjjl.sh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-03-01 09:23
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ShsjjlshDao extends SuperDAOImpl<ShsjjlshForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(ShsjjlshForm.class);
        super.setKey("sqid");
        super.setTableName("xg_dtjs_shsjjl_sq");
    }

    @Override
    public List<HashMap<String, String>> getPageList(ShsjjlshForm shsjjlshForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(ShsjjlshForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuffer sql = new StringBuffer();
        sql.append("select t.* from (");
        sql.append("select t1.*,t3.xqmc,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,");
        sql.append("t2.nj,t2.xb,t2.zybj,t2.zybjmc,t2.mz,t2.mzdm,t2.zzmm,t2.zzmmmc,t2.sydm1 sydm,t2.symc1 symc, ");
        sql.append("t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
        sql.append("decode(t4.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t6.gwz, ");
        sql.append(" ssx.shengmc||ssx.shimc||ssx.xianmc || t1.dd ddxxdz, ");
        sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
        sql.append("from xg_dtjs_shsjjl_sq t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
        sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm ");
        sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid");
        sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
        sql.append(" left join xg_view_dmk_qx ssx on ssx.qxdm=t1.ddssx ");
        sql.append(" where t5.spyh ='"+ user.getUserName() + "' ");
        String shlx = t.getShzt();
        if (!shlx.equals("dsh")) {
            sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
        } else {
            sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
        }
        sql.append(" ) t where 1=1 ");
        sql.append(" and  rn = 1 ");
        sql.append(searchTj);
        sql.append(searchTjByUser);
        return getPageList(t, sql.toString(), inputV);
    }

    public HashMap<String, String> getInfo(String sqid) {
        StringBuilder sql = new StringBuilder();
        sql.append("select t1.*,t3.xqmc,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,");
        sql.append("t2.nj,t2.xb,t2.zybj,t2.zybjmc,t2.mz,t2.mzdm,t2.zzmm,t2.zzmmmc,t2.sydm1 sydm,t2.symc1 symc, ");
        sql.append(" decode(t1.shzt,  '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3', ");
        sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', ");
        sql.append(" t1.shzt) shztmc from xg_dtjs_shsjjl_sq t1 ");
        sql.append(" left join view_xsjbxx t2 on t1.xh = t2.xh ");
        sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm ");
        sql.append(" where t1.sqid = ? ");
        return dao.getMapNotOut(sql.toString(), new String[] { sqid});
    }
}
