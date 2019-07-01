package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjssh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-22 15:33
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BjxfjsshDao extends SuperDAOImpl<BjxfjsshForm> {
    @Override
    protected void setTableInfo() {
        super.setKey("sqid");
        super.setTableName("xg_sxzzjy_bjxfjs_bjxfjssqb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjxfjsshForm bjxfjsshForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjxfjsshForm t, User user) throws Exception {
        //���ɸ߼���ѯ�������������ֵ
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (");
        sql.append(" select a.* from (");
        sql.append(" select t1.sqid,t1.bjdm,t1.xfjsmc,t1.sblx,t1.xn,t1.xq ,t1.jssl,t1.sqsj,t1.sqr,t1.shzt,");
        sql.append(" t1.splc, t2.bjmc,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t1.xn || t7.xqmc xnxq,");
        sql.append(" t4.guid shid, t4.gwid,t4.shr,t4.shyj, ");
        sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', 'δ���', '1', 'ͨ��','2','��ͨ��','3','�˻�','4','������', '5', '�����') || ']' shztmc, ");
        sql.append(" t6.gwz,row_number() over(partition by t1.sqid order by t4.shsj desc) rn, ");
        sql.append(" (select sblxmc from xg_sxzzjy_bjxfjs_sblxdmb where sblxdm=t1.sblx) sblxmc, ");
        sql.append(" 'sq' sqlx ,'�걨' sqlxmc,'' jgid ");
        sql.append(" from xg_sxzzjy_bjxfjs_bjxfjssqb t1 ");
        sql.append("  left join VIEW_NJXYZYBJ t2 on t2.bjdm = t1.bjdm");
        sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid ");
        String shlx = t.getShzt();
        if(!shlx.equals("dsh")){
            sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
        }else{
            sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
        }
        sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw ");
        sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
        sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
        sql.append(" where t5.spyh = '"+user.getUserName()+"' ");
        sql.append(" ) a ");
        sql.append("   union all " );
        sql.append(" (select  b.sqid,b1.bjdm,b1.xfjsmc,b1.sblx,b1.xn,b1.xq,b1.jssl, b.sqsj,b.sqr,b.shzt,b.splc,b2.bjmc," );
        sql.append(" b2.xydm,b2.xymc,b2.zydm,b2.zymc, b1.xn || b7.xqmc xnxq,b4.guid shid,b4.gwid,b4.shr,b4.shyj," );
        sql.append(" b6.mc || '[' || decode(b4.shzt, '0','δ���','1','ͨ��','2','��ͨ��','3','�˻�','4', '������','5','�����') || ']' shztmc," );
        sql.append(" b6.gwz,row_number() over(partition by b.sqid order by b4.shsj desc) rn,");
        sql.append(" (select sblxmc from xg_sxzzjy_bjxfjs_sblxdmb  where sblxdm = b1.sblx) sblxmc, " );
        sql.append(" b.hblx sqlx ,decode(b.hblx, 'nzhb','���ڻ㱨','nzzj','�����ܽ�','') sqlxmc,b.jgid ");
        sql.append(" from  xg_sxzzjy_bjxfjs_bjxfjshbsqb b " );
        sql.append(" left join xg_sxzzjy_bjxfjs_bjxfjsjgb b1 on b1.jgid=b.jgid" );
        sql.append(" left join VIEW_NJXYZYBJ b2 on b2.bjdm = b1.bjdm" );
        sql.append(" left join xg_xtwh_shztb b4  on b.sqid = b4.ywid " );
        if(!shlx.equals("dsh")){
            sql.append(" and (b4.shzt<>0 and  b4.shzt<>4) ");
        }else{
            sql.append(" and (b4.shzt=0  or b4.shzt = 4 )" );
        }
        sql.append(" left join xg_xtwh_spgwyh b5 on b4.gwid = b5.spgw" );
        sql.append(" left join xg_xtwh_spgw b6 on b4.gwid = b6.id " );
        sql.append(" left join xqdzb b7 on b1.xq = b7.xqdm " );
        sql.append(" where  b5.spyh = '"+user.getUserName()+"' ) ");
        sql.append(") t where 1=1 ");
        sql.append(" and  rn = 1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * @����:��������
     * @param sqid
     * @param shzt
     * @return
     * @throws Exception
     * boolean ��������
     * @throws
     */
    public boolean updateBjxfjssq(String sqid,String shzt) throws Exception{
        String[] inputV = new String[2];
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE xg_sxzzjy_bjxfjs_bjxfjssqb ");
        sql.append(" set ");
        sql.append(" shzt = ? ");
        sql.append(" where sqid = ? ");
        inputV[0] = shzt;
        inputV[1] = sqid;
        return dao.update(sql.toString(),inputV)>0 ? true:false;
    }

    /**
     * @����:ɾ��
     * @param bbsqid
     * @return
     * @throws Exception
     * boolean ��������
     * @throws
     */
    public boolean deleteBjxfjsjg(String ywid) throws Exception{
        String[] inputV = new String[1];
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from xg_sxzzjy_bjxfjs_bjxfjsjgb ");
        sql.append(" where lcywid = ? ");
        inputV[0] = ywid;
        return dao.runDelete(sql.toString(),inputV)>0;
    }

    /**
     *
     * @����:ȡ�������״̬����
     * @���ߣ�������[���ţ�1123]
     * @���ڣ�2016-4-5 ����03:06:58
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param sqid
     * @return
     * String ��������
     * @throws
     */
    public String getLastShxx(String sqid) {
        StringBuilder sql = new StringBuilder();
        sql.append("select t1.zd2 from (select b.mc, c.xm shr, a.*, rownum n from xg_xtwh_shztb a left join xg_xtwh_spgw b on a.gwid = b.id left join yhb c on a.shr = c.yhm where a.ywid = ? and shzt <> '0' order by a.shsj desc) t1 where t1.n = 1");
        return dao.getOneRs(sql.toString(), new String[] {sqid}, "zd2");
    }

    public String checkExistForSave(BjxfjsshForm model) {
        StringBuilder sql = new StringBuilder(
                " select count(1) num from xg_sxzzjy_bjxfjs_bjxfjsjgb where xn = ? and xq = ? and bjdm=? and sblx=? and sjly='1'");
        return dao.getOneRs(sql.toString(), new String[] {model.getXn(),model.getXq(),model.getBjdm(),model.getSblx()}, "num");
    }
}
