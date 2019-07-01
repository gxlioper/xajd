package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-08-02 17:22
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GrxfjshbDao extends SuperDAOImpl<GrxfjshbForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_sxzzjy_grxfjs_grxfjshbsqb");
        super.setKey("sqid");
        super.setClass(GrxfjshbForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(GrxfjshbForm grxfjshbForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(GrxfjshbForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");

        StringBuilder sql = new StringBuilder();

        sql.append(" select a.* from (select t.*,case t.nzhbcount when 0 then 'δ�㱨' else t.hbshztmc end nzhbshztmc, ");
        sql.append(" case t.nzzjcount when 0 then 'δ�㱨' else t.zjshztmc end nzzjshztmc ");
        sql.append(" from ( ");
        sql.append(" select a.jgid,a.xh,a.xfjsmc,a.sblx,a.xn,a.xq,a.bxnmb,a.jssl,a.lrsj,a.lrr,a.fjid,a.sjly,a.lcywid,");
        sql.append(" b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjmc,b.bjdm,b.nj,b.sjhm,b.mz,b.zzmmmc,b.xb,");
        sql.append(" c.sblxmc,a.xn||e.xqmc xnxq,f.qsh,g.ldmc, ");
        sql.append(" h1.shzt  nzhbshzt,h2.shzt  nzzjshzt,h1.sqid nzhbid,h2.sqid nzzjid,h1.splc nzhbsplc,h2.splc nzzjsplc,");
        sql.append(" decode(h1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��', '3','�˻�','4','������','5','�����','','�������',h1.shzt) hbshztmc,");
        sql.append(" decode(h2.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��', '3','�˻�','4','������','5','�����','','�������',h2.shzt) zjshztmc,");
        sql.append(" (select count(0) from xg_sxzzjy_grxfjs_grxfjshbsqb z where hblx='nzhb' and z.jgid=a.jgid) nzhbcount ,");
        sql.append(" (select count(0) from xg_sxzzjy_grxfjs_grxfjshbsqb z where hblx='nzzj' and z.jgid=a.jgid) nzzjcount ");
        sql.append(" from   xg_sxzzjy_grxfjs_grxfjsjgb a ");
        sql.append(" left join view_xsjbxx b on b.xh = a.xh");
        sql.append(" left join xg_sxzzjy_grxfjs_sblxdmb c on c.sblxdm=a.sblx");
        sql.append(" left join fdyxxb d on d.zgh=a.lrr");
        sql.append(" left join xqdzb e on e.xqdm=a.xq");
        sql.append(" left join XG_GYGL_NEW_CWXXB f on f.xh=a.xh");
        sql.append(" left join XG_GYGL_NEW_LDXXB g on g.lddm=f.lddm");
        sql.append(" left join (select * from xg_sxzzjy_grxfjs_grxfjshbsqb where hblx='nzhb') h1 on h1.jgid=a.jgid");
        sql.append(" left join (select * from xg_sxzzjy_grxfjs_grxfjshbsqb where hblx='nzzj') h2 on h2.jgid=a.jgid");
        sql.append(" ) t ) a where 1 = 1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * @����:��ȡ��������
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/7/25 14:57
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: []
     * @return: java.lang.String
     */
    public String getShlcID(String hblx) {
        StringBuffer sql = new StringBuffer();
        if("nzhb".equals(hblx)){
            sql.append(" select splc_nzhb splc from xg_sxzzjy_grxfjs_jcszb ");
        }
        if("nzzj".equals(hblx)){
            sql.append(" select splc_nzzj splc from xg_sxzzjy_grxfjs_jcszb ");
        }
        return dao.getOneRs(sql.toString(), new String[] {}, "splc");
    }

    public String checkExistForSave(GrxfjshbForm model) {
        String sqid = "-1";
        if(!StringUtils.isNull(model.getSqid())){
            sqid = model.getSqid();
        }
        String[] arr = new String[] { model.getHblx(),model.getJgid(),sqid};
        StringBuilder sql = new StringBuilder(
                " select count(1) num from xg_sxzzjy_grxfjs_grxfjshbsqb where hblx = ? and jgid = ? and sqid <> ? ");
        String num = dao.getOneRs(sql.toString(), arr, "num");
        return num;
    }

    public int grxfjshbDel(String[] jgids, String[] hblxs) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from xg_sxzzjy_grxfjs_grxfjshbsqb where jgid in (");
        for(int i=0;i<jgids.length-1;i++){
            sql.append("?,");
        }
        sql.append("?) and (hblx = ");
        for(int i=0;i<hblxs.length;i++){
            sql.append("'"+hblxs[i]+"'");
            if(i<hblxs.length-1){
                sql.append(" or ");
            }
        }
        sql.append(") ");
        return dao.runDelete(sql.toString(),jgids);
    }

    public boolean updateGrxfjshb(GrxfjshbForm model) throws Exception {
        String[] inputV = new String[3];
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE xg_sxzzjy_grxfjs_grxfjshbsqb ");
        sql.append(" set ");
        sql.append(" shzt = ?,");
        sql.append(" splc = ? ");
        //sql.append(" sqsj = ?");
        sql.append(" where sqid = ?");
        inputV[0] = model.getShzt();
        inputV[1] = model.getSplc();
        //inputV[2] = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss");
        inputV[2] = model.getSqid();
        return dao.update(sql.toString(),inputV)>0;
    }

    public HashMap<String,String> getInfo(GrxfjshbForm model) {
        String sql = "select a.*,decode(shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��', '3','�˻�','4','������','5','�����','','�������',shzt) shztmc " +
                " from xg_sxzzjy_grxfjs_grxfjshbsqb a where jgid=? and hblx=?";
        return dao.getMapNotOut(sql,new String[]{model.getJgid(),model.getHblx()});
    }

    /**
     * @����:�޸����״̬
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/7/30 15:44
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [sqid, shzt]
     * @return: boolean
     */
    public boolean updateSq(String sqid, String shzt) throws Exception {
        String[] inputV = new String[2];
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE xg_sxzzjy_grxfjs_grxfjshbsqb ");
        sql.append(" set ");
        sql.append(" shzt = ? ");
        sql.append(" where sqid = ? ");
        inputV[0] = shzt;
        inputV[1] = sqid;
        return dao.update(sql.toString(),inputV)>0 ;
    }

    public HashMap<String,String> getWordInfo(String id,String hblx) {
        String sql = "select a.*,c.sblxmc,b.xn,b.xh from xg_sxzzjy_grxfjs_grxfjshbsqb a " +
                " left join xg_sxzzjy_grxfjs_grxfjsjgb b on b.jgid=a.jgid " +
                " left join xg_sxzzjy_grxfjs_sblxdmb c on c.sblxdm=b.sblx where a.jgid=? and a.hblx=?";
        return dao.getMapNotOut(sql,new String[]{id,hblx});
    }
}
