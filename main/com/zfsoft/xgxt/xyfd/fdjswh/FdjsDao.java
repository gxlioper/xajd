package com.zfsoft.xgxt.xyfd.fdjswh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/6/24.
 */
public class FdjsDao extends SuperDAOImpl<FdjsForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(FdjsForm.class);
        super.setKey("djh");
        super.setTableName("xg_xyfd_fdjsxxb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdjsForm fdjsForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdjsForm t, User user) throws Exception {
        //���ɸ߼���ѯ�������������ֵ
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (");
        sql.append("select a.djh,a.kcmc,a.xkzy,a.lxdh,a.dzyx,a.fdkm,a.fds,b.zgh,b.xm,case when b.xb='1' then '��'  when b.xb = '2' then 'Ů' ");
        sql.append(" else b.xb end xb,b.kzzd13 zc,c.fdsmc,d.bmdm,d.bmmc from XG_XYFD_FDJSXXB a  ");
        sql.append(" left join fdyxxb b on a.zgh = b.zgh left join XG_XYFD_FDSXXB c on a.fds = c.id left join ZXBZ_XXBMDM d  ");
        sql.append(" on b.bmdm = d.bmdm ) where 1=1 ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * ����������ʦ
     * @param t
     * @return
     * @throws Exception
     */
    public boolean saveFdjs(FdjsForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into xg_xyfd_fdjsxxb(djh,zgh,kcmc,xkzy,lxdh,dzyx,fdkm,fds,mon,tues,wed,thur,fri,sat,sun) ");
        sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
        String[] input = new String[]{t.getDjh(),t.getZgh(),t.getKcmc(),t.getXkzy(),t.getLxdh(),t.getDzyx(),t.getFdkm(),t.getFds(),t.getMon(),
        t.getTues(),t.getWed(),t.getThur(),t.getFri(),t.getSat(),t.getSun()};
        return dao.runUpdate(sql.toString(),input);
    }

    /**
     * ���¸�����
     * @param t
     * @return
     * @throws Exception
     */
    public boolean updateFdjs(FdjsForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("update xg_xyfd_fdjsxxb set kcmc = ?,xkzy = ?,lxdh = ?,dzyx = ?,fdkm = ?, fds = ?,mon = ?,tues = ?,wed = ?,thur = ?, ");
        sql.append("fri = ?,sat = ?,sun = ? where djh = ? ");
        String[] in = new String[]{t.getKcmc(),t.getXkzy(),t.getLxdh(),t.getDzyx(),t.getFdkm(),t.getFds(),t.getMon(),t.getTues(),
                t.getWed(),t.getThur(),t.getFri(),t.getSat(),t.getSun(),t.getDjh()};
        return dao.runUpdate(sql.toString(),in);
    }

    /**
     * ���Ҹ�����ʦ
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getFdjs(FdjsForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_xyfd_fdjsxxb where djh = ?");
        String[] input = new String[]{t.getDjh()};
        return dao.getMapNotOut(sql.toString(),input);
    }

    public String getDjh() throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select max(djh) djh from xg_xyfd_fdjsxxb ");
        return dao.getOneRs(sql.toString(),new String[]{},"djh");
    }


    public List<HashMap<String, String>> getAllTeacher(FdjsForm t) throws Exception {
        //���ɸ߼���ѯ�������������ֵ
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select t.zgh,t.xm,t.lxdh,t.bmdm xydm,t.bgdd,t.bgdh,t.dzyx,t.KZZD13 zc, ");
        sql.append(" case when t.xb = '1' then '��' when t.xb='2' then 'Ů' else t.xb end xb,t1.bmmc ");
        sql.append(" from fdyxxb t left join ZXBZ_XXBMDM t1 on t.bmdm = t1.bmdm ");
        sql.append(" ) where 1=1 ");
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**��ѯ��ʦ��Ϣ***/
    public HashMap<String, String> getTea(FdjsForm t){

        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select t.zgh,t.xm,t.lxdh,t.bmdm xydm,t.bgdd,t.bgdh,t.dzyx,t.KZZD13 zc, ");
        sql.append(" case when t.xb = '1' then '��' when t.xb='2' then 'Ů' else t.xb end xb,t1.bmmc ");
        sql.append(" from fdyxxb t left join ZXBZ_XXBMDM t1 on t.bmdm = t1.bmdm ");
        sql.append(" ) where 1=1 and zgh = ? ");
        return dao.getMapNotOut(sql.toString(), new String[]{t.getZgh()});
    }

    /**
     * ��ȡ���ø�����
     * @param t
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getKxFds(FdjsForm t) throws Exception{
        //���ɸ߼���ѯ�������������ֵ
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append(" select a.* from xg_xyfd_fdsxxb a where not exists(select 1 from xg_xyfd_fdjsxxb b  where a.id = b.fds) and a.yxzt='1' ");
        sql.append(" and not exists(select 1 from xg_xyfd_pbsqb c  where a.id = c.fds) ");
        sql.append(" and not exists(select 1 from xg_xyfd_pbjgb d  where a.id = d.fds) ");
        sql.append(" ) where 1=1 ");
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }
}
