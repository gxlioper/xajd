package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjssq;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-20 14:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BjxfjssqDao extends SuperDAOImpl<BjxfjssqForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_sxzzjy_bjxfjs_bjxfjssqb");
        super.setKey("sqid");
        super.setClass(BjxfjssqForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjxfjssqForm bjxfjssqForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjxfjssqForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");

        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from ( ");
        sql.append(" select a.sqid,a.bjdm,a.xfjsmc,a.sblx,a.xn,a.xq,a.bxnmb,a.jssl,a.sqsj,a.sqr,a.fjid,a.shzt,a.splc," +
                " b.xydm,b.xymc,b.zydm,b.zymc,b.bjmc,b.nj,c.sblxmc,d.xm,a.xn||e.xqmc xnxq," +
                " decode(a.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��', '3','�˻�','4','������','5','�����','','�������',a.shzt) shztmc" +
                " from   xg_sxzzjy_bjxfjs_bjxfjssqb a " +
                " left join VIEW_NJXYZYBJ b on b.bjdm = a.bjdm" +
                " left join xg_sxzzjy_bjxfjs_sblxdmb c on c.sblxdm=a.sblx" +
                " left join fdyxxb d on d.zgh=a.sqr" +
                " left join xqdzb e on e.xqdm=a.xq");
        sql.append(" ) t where 1 = 1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public List<HashMap<String,String>> getAllSblx() {
        String sql = "select * from xg_sxzzjy_bjxfjs_sblxdmb";
        return dao.getListNotOut(sql,new String[]{});
    }

    public List<HashMap<String,String>> getBjList(BjxfjssqForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");

        StringBuilder sql = new StringBuilder();
        sql.append(" select * from VIEW_NJXYZYBJ t where 1 = 1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public HashMap<String,String> getBjInfo(String bjdm) {
        StringBuilder sql = new StringBuilder();
        String temp = "select b.xm from XG_SZDW_XSGB_DWB t " +
                " left join XG_SZDW_XSGB_ZWB a on a.zwid = t.zwid" +
                " left join view_xsjbxx b on b.xh=t.xh where b.bjdm='"+bjdm+"'";
        sql.append(" select t.*, ");
        sql.append(" ("+temp+" and a.zwmc='�೤') bzxm,");
        sql.append(" ("+temp+" and a.zwmc='��֧��') tzsxm,");
        sql.append(" (select b.xm from  BZRBBB a left join fdyxxb b on a.zgh=b.zgh where bjdm='"+bjdm+"' ) bzrxm,");
        sql.append(" (select b.xm from  FDYBJB a left join fdyxxb b on a.zgh=b.zgh where bjdm='"+bjdm+"' ) fdyxm,");
        sql.append(" (select count(0)  from view_xsjbxx where bjdm='"+bjdm+"' ) bjzrs,");
        sql.append(" (select count(0)  from view_xsjbxx where bjdm='"+bjdm+"' and xb='��' ) nansrs,");
        sql.append(" (select count(0)  from view_xsjbxx where bjdm='"+bjdm+"' and xb='Ů') nvsrs,");
        sql.append(" (select count(0)  from view_xsjbxx where bjdm='"+bjdm+"'  and zzmmmc='�й���������Ա' or zzmmmc='��Ա') dyrs ");
        sql.append(" from VIEW_NJXYZYBJ t where t.bjdm = ? ");
        return  dao.getMapNotOut(sql.toString(),new String[]{bjdm});
    }

    public String getShlcID() {
        StringBuffer sql = new StringBuffer();
        sql.append(" select splc from xg_sxzzjy_bjxfjs_jcszb ");
        return dao.getOneRs(sql.toString(), new String[] {}, "splc");
    }

    public boolean updateBjxfjssq(BjxfjssqForm model) throws Exception {
        String[] inputV = new String[3];
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE xg_sxzzjy_bjxfjs_bjxfjssqb ");
        sql.append(" set ");
        sql.append(" shzt = ?,");
        sql.append(" splc = ? ");
        sql.append(" where sqid = ?");
        inputV[0] = model.getShzt();
        inputV[1] = model.getSplc();
        inputV[2] = model.getSqid();
        return dao.update(sql.toString(),inputV)>0;
    }

    public HashMap<String,String> getBjxfjssqInfo(BjxfjssqForm model) {
        StringBuffer sql = new StringBuffer();
        sql.append("select a.*,decode(a.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��', '3','�˻�','4','������','5','�����','','�������',a.shzt) shztmc,");
        sql.append(" e.xqmc,b.sblxmc from xg_sxzzjy_bjxfjs_bjxfjssqb a " );
        sql.append(" left join xg_sxzzjy_bjxfjs_sblxdmb b on b.sblxdm=a.sblx " );
        sql.append(" left join xqdzb e on e.xqdm=a.xq ");
        sql.append("where sqid=?");
        return dao.getMapNotOut(sql.toString(),new String[]{model.getSqid()});
    }

    /**
     * @����:�ж��Ƿ��ظ�
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/22 9:53
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: java.lang.String
     */
    public String checkExistForSave(BjxfjssqForm model) {
        String sqid = "-1";
        if(!StringUtils.isNull(model.getSqid())){
            sqid = model.getSqid();
        }
        String[] arr = new String[] { model.getXn(),model.getXq(),model.getBjdm(), model.getSblx(),sqid};
        StringBuilder sql = new StringBuilder(
                " select count(1) num from xg_sxzzjy_bjxfjs_bjxfjssqb where xn = ? and xq = ? and bjdm = ? and sblx=? and sqid <> ? ");
        String num = dao.getOneRs(sql.toString(), arr, "num");
        return num;
    }
}
