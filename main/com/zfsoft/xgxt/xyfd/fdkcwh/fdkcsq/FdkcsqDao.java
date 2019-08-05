package com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/2.
 */
public class FdkcsqDao extends SuperDAOImpl<FdkcsqForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(FdkcsqForm.class);
        super.setKey("sqid");
        super.setTableName("xg_xyfd_fdkcsqb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdkcsqForm pbsqForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdkcsqForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.*,decode(t.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t.shzt) shztmc from ( ");
        sql.append(" select a.*,c.xm,c.zgh yhm,d.* from xg_xyfd_fdkcsqb a left join xg_xyfd_fdjsxxb b on a.fdjs = b.djh left join fdyxxb c ");
        sql.append(" on b.zgh = c.zgh left join xg_xyfd_fdsxxb d on b.fds = d.id where a.fdjs like 'JS%' union ");
        sql.append(" select a.*,c.xm,c.xh yhm,d.* from xg_xyfd_fdkcsqb a left join xg_xyfd_pbjgb b on a.fdjs = b.djh left join  ");
        sql.append(" (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) c on b.xh = c.xh left join  ");
        sql.append(" xg_xyfd_fdsxxb d on b.fds = d.id where a.fdjs like 'PB%' ");
        sql.append("  ) t where 1=1 ");

        if(!isAdmin(user)){ //���ǹ���ԱҲ���Ǹ���������Ա
            sql.append(" and (t.lrr =  '" + user.getUserName() + "' or t.yhm = '" + user.getUserName() + "' ) ");
        }

        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * ����ѧ�𼰱��ý���
     * @param t
     * @param xn
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getJlxx(FdkcsqForm t, String xn) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from XG_PJPY_NEW_PJJGB where xh = ? and xn = ? ");
        return dao.getListNotOut(sql.toString(),new String[]{"",xn});
    }

    /**
     * ѧ����Ϣ
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getXsxx(FdkcsqForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc, ");
        sql.append(" case when a.xb='1' then '��' when a.xb = '2' then 'Ů' else a.xb end xbm ");
        sql.append(" from XSXXB a left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c");
        sql.append(" on a.zybj = c.bjdm) where xh = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{""});
    }

    /**
     * ������־Ը������
     * @param t
     * @return
     * @throws Exception
     */
    public boolean savePbsq(FdkcsqForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into xg_xyfd_pbsqb(xh,xsgbrz,lxdh,dzyx,fdpb,fdkm,fds,mon,tues,wed,thur,fri,sat,sun,fjid,)");
        return dao.runUpdate(sql.toString(),new String[]{});
    }

    /**
     * ��ȡ��������
     * @return
     */
    public String getShlcID(String lx) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select splc from xg_xyfd_shlcszb where lx = ? ");
        return dao.getOneRs(sql.toString(), new String[] {lx}, "splc");
    }

    public HashMap<String,String> getFdkcxx(FdkcsqForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_xyfd_pbsqb where sqid = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getSqid()});
    }

    public List<HashMap<String, String>> getPbList(FdkcsqForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (");
        sql.append(" select a.*, b.xm,case when b.xb='1' then '��' when b.xb = '2' then 'Ů' ");
        sql.append(" else b.xb end xb,c.fdsmc,c.fdsdd ,c.syksrq,c.syjsrq from xg_xyfd_pbjgb a  ");
        sql.append(" left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a ");
        sql.append(" left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c ");
        sql.append(" on a.zybj = c.bjdm) b on a.xh = b.xh left join XG_XYFD_FDSXXB c on a.fds = c.id  ");
        sql.append(" ) t");
        sql.append(" where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public List<HashMap<String, String>> getFdjsList(FdkcsqForm t, User user) throws Exception {
        //���ɸ߼���ѯ�������������ֵ
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from (");
        sql.append("select a.djh,a.kcmc,a.xkzy,a.lxdh,a.dzyx,a.fdkm,a.fds,b.zgh,b.xm,case when b.xb='1' then '��'  when b.xb = '2' then 'Ů' ");
        sql.append(" else b.xb end xb,b.kzzd13 zc,c.fdsmc,d.bmdm,d.bmmc from XG_XYFD_FDJSXXB a  ");
        sql.append(" left join fdyxxb b on a.zgh = b.zgh left join XG_XYFD_FDSXXB c on a.fds = c.id left join ZXBZ_XXBMDM d  ");
        sql.append(" on b.bmdm = d.bmdm ");
        sql.append(" ) t where 1=1 ");
        if(!isAdmin(user)){
            sql.append(" and (t.zgh = '" + user.getUserName() + "') ");
        }
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * �ж��Ƿ�Ϊ����Ա�򸨵�����
     * @param user ��ǰ��¼�û�
     * @return
     */
    public boolean isAdmin(User user){
        StringBuilder userSql = new StringBuilder();
        userSql.append(" select zdm from yhb where yhm = ? ");
        String zdm = dao.getOneRs(userSql.toString(),new String[]{user.getUserName()},"zdm");
        String[] zdms = zdm.split(",");
        boolean isAdmin = false;
        for(int i=0;i<zdms.length;i++){
            StringBuilder check = new StringBuilder();
            check.append(" select zmc from yhzb where zdm = ? ");
            String zmc = dao.getOneRs(check.toString(),new String[]{zdms[i]},"zmc");
            if(zmc.equals("��������Ա")||zmc.equals("��������")){
                isAdmin = true;
            }
        }
        return isAdmin;
    }

    public String getFdjs(FdkcsqForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select xm from ( ");
        sql.append(" select a.djh ,b.xm from xg_xyfd_fdjsxxb a left join fdyxxb b on a.zgh = b.zgh union ");
        sql.append(" select a.djh,b.xm from xg_xyfd_pbjgb a left join xsxxb b on a.xh = b.xh ");
        sql.append(") where djh = ? ");
        String xm = dao.getOneRs(sql.toString(),new String[]{t.getFdjs()},"xm");
        return xm;
    }
    public HashMap<String,String> getFdjsxx(FdkcsqForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from xg_xyfd_fdjsxxb where djh = ?");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getFdjs()});
    }
    public HashMap<String,String> getPbxx(FdkcsqForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from xg_xyfd_pbjgb where djh = ?");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getFdjs()});
    }
}
