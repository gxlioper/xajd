package com.zfsoft.xgxt.xszz.xfjm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:
 * @�๦������:
 * @����: ��ˬ [����:1730]
 * @ʱ��: 2019/7/3 10:50
 */
public class XfjmDao extends SuperDAOImpl<XfjmForm> {
    public XfjmDao(){}
    public static volatile XfjmDao xfjmDao = null;
    public String url = "";
    public static XfjmDao getXfjmDao(){
        if(xfjmDao == null){
            synchronized (XfjmDao.class){
                if(xfjmDao == null){
                    xfjmDao = new XfjmDao();
                }
            }
        }
        return xfjmDao;
    }

    @Override
    protected void setTableInfo() {

    }

    @Override
    public List<HashMap<String, String>> getPageList(XfjmForm xfjmForm) throws Exception {
        return null;
    }

    /**
     * @����: ��ȡ����ѧ�Ѽ���������Ϣ�б�
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/5 14:18
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param t
	* @param user
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     **/
    @Override
    public List<HashMap<String, String>> getPageList(XfjmForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from(");
        sql.append("select a.id,a.xh,a.xn,a.sqly,a.sqfj,a.sqsj,a.shlc,a.xfjml,a.xfjmje,a.splc,");
        sql.append(" decode(a.shzt,'0', 'δ�ύ', '1', 'ͨ��', '2','��ͨ��',");
        sql.append(" '3','���˻�','5', '�����',a.shzt) shztmc,a.shzt,c.dcmc,d.xydm,");
        sql.append(" d.xymc,d.zydm,d.zymc,d.bjdm,d.bjmc,e.sydm,f.symc,d.xb,d.xm,d.zybjmc,g.shlgwmc ");
        sql.append(" from xg_xszz_new_xfjmb a");
        sql.append(" left join xg_xszz_new_knsjgb b on a.xh=b.xh");
        sql.append(" left join xg_xszz_new_kndcdmb c on b.rddc=c.dcdm");
        sql.append(" left join view_xsbfxx d on d.xh = a.xh");
        sql.append(" left join XG_XTWH_SYBJGLB e on e.bjdm=d.bjdm ");
        sql.append(" left join XG_XTWH_SYDMB f on f.sydm = e.sydm");
        sql.append(" left join (select mc shlgwmc, ywid,shzt shlzt,shsj from (select b.mc,a.ywid,a.shr,a.shsj,a.shzt,row_number() over(partition by a.ywid order by a.shsj desc) rn from xg_xtwh_shztb a ");
        sql.append(" left join xg_xtwh_spgw b on a.gwid=b.id) where rn=1) g on a.id=g.ywid ");
        sql.append(") t where 1=1");
        if("stu".equals(user.getUserType())){
            sql.append(" and xh='"+user.getUserName()+"'");
        }
        sql.append(" and bjdm is not null ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * @����: ��ȡ������б�
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/4 21:05
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param t
	* @param user
     * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     **/
    public List<HashMap<String, String>> getDshPageList(XfjmForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        //������˸�λ��������
        String shgwzByUser = SearchService.getShgwzByUser(user, "t","xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from(");
        sql.append("select a.id,a.xh,a.xn,a.sqly,a.sqfj,a.sqsj,a.shlc,a.xfjml,a.xfjmje,a.splc,");
        sql.append(" decode(a.shlzt,'0', 'δ���', '1', 'ͨ��', '2','��ͨ��',");
        sql.append(" '3','���˻�','5', '�����',a.shlzt) shztmc,a.shzt,a.dcmc,d.xydm,d.zybj,d.nj,");
        sql.append(" d.xymc,d.zydm,d.zymc,d.bjdm,d.bjmc,e.sydm,f.symc,d.xb,d.xm,d.zybjmc,");
        sql.append(" a.gwid,a.shr,a.shlzt,a.shid,h.mc shlgwmc,a.shlshsj,h.gwz,a.rn");
        sql.append(" from (");
        sql.append(" select a.id,a.xh,a.xn,a.sqly,a.sqfj,a.sqsj,a.shlc,a.xfjml,a.xfjmje,a.splc,a.shzt,c.dcmc,");
        sql.append(" g.gwid,g.shzt shlzt,g.guid shid,g.shr,g.shsj shlshsj,row_number() over(partition by a.id order by g.shsj desc) rn ");
        sql.append(" from xg_xszz_new_xfjmb a ");
        sql.append(" left join xg_xszz_new_knsjgb b on a.xh=b.xh");
        sql.append(" left join xg_xszz_new_kndcdmb c on b.rddc=c.dcdm");
        sql.append(" left join xg_xtwh_shztb g on a.id = g.ywid ");
        sql.append(" where 1=1 ");
        sql.append(" and g.gwid in (select spgw from xg_xtwh_spgwyh where spyh='"+user.getUserName()+"' )");
        if (!"dsh".equalsIgnoreCase(t.getStatus())){
            //�����
            sql.append(" and g.shzt not in('0','4') and g.shr='"+user.getUserName()+"'" );
        }else{
            //�����
            sql.append(" and g.shzt in('0','4')" );
        }
        sql.append(" ) a ");
        sql.append(" left join view_xsbfxx d on d.xh = a.xh");
        sql.append(" left join XG_XTWH_SYBJGLB e on e.bjdm=d.bjdm ");
        sql.append(" left join XG_XTWH_SYDMB f on f.sydm = e.sydm");
        sql.append(" left join xg_xtwh_spgw h on a.gwid = h.id ");
        sql.append(") t where 1=1 and rn=1");
        sql.append(" and bjdm is not null ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        sql.append(shgwzByUser);//��˸�λ��������
        return getPageList(t, sql.toString(), inputV);
    }

    public List<HashMap<String, String>> getJgPageList(XfjmForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from(");
        sql.append("select a.id,a.xh,a.xn,a.sqly,a.sqfj,a.sqsj,a.shlc,a.xfjml,a.xfjmje,a.splc,");
        sql.append(" decode(a.shzt,'0', 'δ���', '1', 'ͨ��', '2','��ͨ��',");
        sql.append(" '3','���˻�','5', '�����',a.shzt) shztmc,a.shzt,c.dcmc,d.xydm,");
        sql.append(" d.xymc,d.zydm,d.zymc,d.bjdm,d.bjmc,e.sydm,f.symc,d.xb,d.xm,d.zybjmc ");
        sql.append(" from xg_xszz_new_xfjmb a");
        sql.append(" left join xg_xszz_new_knsjgb b on a.xh=b.xh");
        sql.append(" left join xg_xszz_new_kndcdmb c on b.rddc=c.dcdm");
        sql.append(" left join view_xsbfxx d on d.xh = a.xh");
        sql.append(" left join XG_XTWH_SYBJGLB e on e.bjdm=d.bjdm ");
        sql.append(" left join XG_XTWH_SYDMB f on f.sydm = e.sydm");
        //sql.append(" left join (");//רҵ�༶����
        //sql.append(bjpmsql()+") bp on a.xh=bp.xh and a.xn=bp.xn");
        //sql.append(" left join (");//רҵ����
        //sql.append(zypmSql()+") zp on a.xh=zp.xh and a.xn=zp.xn");
        sql.append(") t where 1=1");
        sql.append(" and (shzt='1')");
        sql.append(" and bjdm is not null ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public List<HashMap<String, String>> getJgList(XfjmForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from(");
        sql.append("select a.id,a.xh,a.xn,a.sqly,a.sqfj,a.sqsj,a.shlc,a.xfjml,a.xfjmje,a.splc,");
        sql.append(" decode(a.shzt,'0', 'δ���', '1', 'ͨ��', '2','��ͨ��',");
        sql.append(" '3','���˻�','5', '�����',a.shzt) shztmc,a.shzt,c.dcmc,d.xydm,d.nj,d.zzmmmc,");
        sql.append("d.mzmc,d.sfzh,d.jg jgmc,");
        sql.append(" d.xymc,d.zydm,d.zymc,d.bjdm,d.bjmc,e.sydm,f.symc,d.xb,d.xm,d.zybjmc,bp.zybjpm,zp.zypm ");
        sql.append(" from xg_xszz_new_xfjmb a");
        sql.append(" left join xg_xszz_new_knsjgb b on a.xh=b.xh");
        sql.append(" left join xg_xszz_new_kndcdmb c on b.rddc=c.dcdm");
        sql.append(" left join view_xsbfxx d on d.xh = a.xh");
        sql.append(" left join XG_XTWH_SYBJGLB e on e.bjdm=d.bjdm ");
        sql.append(" left join XG_XTWH_SYDMB f on f.sydm = e.sydm");
        sql.append(" left join (");
        sql.append(bjpmsql()+") bp on a.xh=bp.xh and a.xn=bp.xn");
        sql.append(" left join (");
        sql.append(zypmSql()+") zp on a.xh=zp.xh and a.xn=zp.xn");
        sql.append(") t where 1=1");
        sql.append(" and (shzt='1')");
        sql.append(" and bjdm is not null ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        sql.append(" order by xymc,zymc,bjmc,xh");
        return getPageList(t, sql.toString(), inputV);
    }

    /**
     * @����: ����id��ȡѧ�Ѽ���������Ϣ
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/3 20:01
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param id 
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String,String> getById(String id){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.id,a.xh,a.xn,a.sqly,a.sqfj,a.sqsj,a.shlc,a.xfjml,a.xfjmje,a.splc,a.shzt,");
        sql.append(" decode(a.shzt,'0', 'δ�ύ', '1', 'ͨ��', '2','��ͨ��',");
        sql.append(" '3','���˻�','5', '�����',a.shzt) shztmc,c.dcmc,d.xydm,");
        sql.append(" d.xymc,d.zydm,d.zymc,d.bjdm,d.bjmc,e.sydm,f.symc,d.xb,d.xm,d.zybjmc ");
        sql.append(" from xg_xszz_new_xfjmb a");
        sql.append(" left join xg_xszz_new_knsjgb b on a.xh=b.xh");
        sql.append(" left join xg_xszz_new_kndcdmb c on b.rddc=c.dcdm");
        sql.append(" left join view_xsbfxx d on d.xh = a.xh");
        sql.append(" left join XG_XTWH_SYBJGLB e on e.bjdm=d.bjdm ");
        sql.append(" left join XG_XTWH_SYDMB f on f.sydm = e.sydm");
        sql.append(" where a.id = ?");
        return dao.getMapNotOut(sql.toString(),new String[]{id});

    }

    public HashMap<String,String> getCurrXfjm(String xh,String xn){
        String sql = "select * from xg_xszz_new_xfjmb where xh=? and xn=?";
        return dao.getMapNotOut(sql,new String[]{xh,xn});
    }

    /**
     * @����: ����idɾ��ѧ�Ѽ���������Ϣ
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/3 19:54
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param id
     * @return boolean
     **/
    public boolean removeById(String id){
        String sql = "delete from xg_xszz_new_xfjmb where id = ?";
        try {
            //�����Ƿ�ɾ����������е�����
            return dao.runDelete(sql, new String[]{id}) > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateById(XfjmForm model){
        String sql = "update xg_xszz_new_xfjmb set sqly=?,shzt=? where id=?";
        try {
            return dao.runUpdate(sql,new String[]{model.getSqly(),model.getShzt(),model.getId()});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateJgId(XfjmForm model){
        StringBuilder sql = new StringBuilder();
        sql.append("update xg_xszz_new_xfjmb set ");
        boolean flag = false;
        if(StringUtils.isNotNull(model.getXfjmje())){
            flag = true;
            sql.append(" xfjmje='"+model.getXfjmje()+"'");
        }

        if(StringUtils.isNotNull(model.getSqly())){
            if (flag){
                sql.append(",");
            }
            sql.append("sqly='"+model.getSqly()+"'");
        }
        sql.append(" where id='"+model.getId()+"'");
        try {
            return dao.runUpdate(sql.toString(),new String[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * @����: �������״̬
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/8 19:43
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param model 
     * @return boolean
     **/
    public boolean updateShById(XfjmForm model){
        StringBuilder sql = new StringBuilder();
        sql.append("update xg_xszz_new_xfjmb");
        sql.append(" set shzt='"+model.getShzt()+"' ");
        if(StringUtils.isNotNull(model.getXfjmje())){
            sql.append(",xfjmje='"+model.getXfjmje()+"'");
        }
        sql.append(" where id='"+model.getId()+"'");
        try {
            return dao.runUpdate(sql.toString(),new String[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @����: �ύѧ�Ѽ�������
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/4 16:00
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param model 
     * @return boolean
     **/
    public boolean insert(XfjmForm model){
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("insert into xg_xszz_new_xfjmb(id,xh,xn,sqly,sqsj,shlc,xfjml,xfjmje,splc,shzt)");
            sql.append(" values(?,?,?,?,?,?,?,?,?,?)");
            String[] inputs = {
                    model.getId(), model.getXh(), model.getXn(), model.getSqly(),model.getSqsj(),
                    model.getShlc(), null, model.getXfjmje(),model.getSplc(), model.getShzt()
            };
            return dao.insert(sql.toString(), inputs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @����: �޸����״̬
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/3 20:20
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param id ����id
	* @param shzt ���״̬ 0δ�ύ 1ͨ�� 2δͨ�� 3�˻� 5�����
     * @return boolean
     **/
    public boolean sqztxg(String id,String shzt){
        String sql = "update xg_xszz_new_xfjmb set shzt=? where id=?";
        try {
            return dao.runUpdate(sql, new String[]{shzt, id});
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /**
     * @Author llf
     * @Description �������
     * @Date 9:41 2019/11/12
     * @Param [id]
     * @return boolean
     **/
    public boolean qxsh(String id) throws Exception{
        String sql = "update xg_xszz_new_xfjmb set xfjmje = '' , shzt = '5' where id = ?";
        return dao.runUpdate(sql,new String[]{id});
    }

    /**
     * @����: ����ѧ��ѧ�꣬��ȡ�������϶������Ϣ
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/4 10:41
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param xh ѧ��
	* @param xn ѧ��
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String,String> getKnsrdjg(String xh,String xn){
        String sql = "select guid,dcmc,sqly,sqsj,ylzd2,ylzd3,xqmc,xn,yymc,ylzd5,sqlydm from xg_view_xszz_new_knsjgb where xh=? and xn=?";
        return dao.getMapNotOut(sql,new String[]{xh,xn});
    }

    /**
     * @����: �ж�ѧ��ָ��ѧ���Ƿ�����������϶������Ҽ�����Ϣ�Ƿ��Ѵ���
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/11 9:01
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param xh
	* @param xn
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String,String> knsrdinfo(String xh,String xn){
        //������ڼ�¼���ʾ����������϶�
        //���id������˵���Ѵ���ѧ�Ѽ�����Ϣ
        StringBuilder sql = new StringBuilder();
        sql.append("select xh,xn,id from (");
        sql.append("select a.xh,a.xn,b.id from (select xh,xn from xg_xszz_new_knsjgb where xh=? and xn=?) a");
        sql.append(" left join (select id,xh,xn from xg_xszz_new_xfjmb where shzt='1' and xn=?) b on a.xh=b.xh");
        sql.append(") where rownum = 1");
        return dao.getMapNotOut(sql.toString(),new String[]{xh,xn,xn});
    }

    /**
     * @����: ��������ѧ�Ѽ�����Ϣ
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/11 9:31
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param xfjmList 
     * @return int[]
     **/
    public int[] batchImport(List<HashMap<String,String>> xfjmList){
        try {
            int[] result = null;
            List<String[]> paramList = new ArrayList<String[]>();
            StringBuffer sb = new StringBuffer();
            String nowTime = DateTranCnDate.timeStampToDate(System.currentTimeMillis());
            sb.append("insert into xg_xszz_new_xfjmb(xh,xn,sqsj,xfjmje,shzt) ");
            sb.append(" values(?,?,?,?,?)");
            for (HashMap<String, String> item : xfjmList) {
                String[] param = {item.get("xh"), item.get("xn"), nowTime, item.get("jmje"), "1"};
                paramList.add(param);
            }
            return dao.runBatch(sb.toString(), paramList);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @����: ����ѧ��ѧ���ȡרҵ�༶����
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/18 14:30
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param xh ѧ�Ų���Ϊ��
	* @param xn ѧ��Ϊ����Ĭ�ϻ�ȡ����һѧ������
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String,String> getZybjpm(String xh,String xn){
        StringBuilder sql = new StringBuilder();
        sql.append(bjpmsql());
        if(StringUtils.isNotNull(xh)){
            sql.append(" and xh='"+xh+"'");
        }
        if(StringUtils.isNotNull(xn)){
            sql.append(" and xn = '"+xn+"'");
        }
        sql.append(" order by xn desc");
        return dao.getMapNotOut(sql.toString(),new String[]{});
    }
    /**
     * @����: ����ѧ��ѧ���ȡרҵ����
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/18 14:30
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param xh ѧ�Ų���Ϊ��
     * @param xn ѧ��Ϊ����Ĭ�ϻ�ȡ����һѧ������
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String,String> getZypm(String xh,String xn){
        StringBuilder sql = new StringBuilder();
        sql.append(zypmSql());
        if(StringUtils.isNotNull(xh)){
            sql.append(" and xh='"+xh+"'");
        }
        if(StringUtils.isNotNull(xn)){
            sql.append(" and xn = '"+xn+"'");
        }
        sql.append(" order by xn desc");
        return dao.getMapNotOut(sql.toString(),new String[]{});
    }

    private String bjpmsql(){
        StringBuilder sql = new StringBuilder();
        sql.append("select xn,xq,xh,xm,bjdm,nj,xydm,zydm,sy,fs0 zybjfs,pm0 zybjpm from (");
        sql.append(" select t1.xn,t1.xq,t1.xh,t1.xm,t1.tjzt,t1.zybj,t1.bjdm,t3.nj,t3.xymc,t3.xydm,t3.zydm,t3.zymc,t3.bjmc,t11.sydm sy,");
        sql.append(" t2.fs0, t2.pm0 || '/' || t6.rs pm0,t1.xn zczq  from xg_pjpy_new_cpmdb t1 ");
        sql.append("  left join ( select sum(fs0) fs0,sum(pm0) pm0,xh,xn,xq from ");
        sql.append("  (select  case when t.xmmc='�ۺϳɼ�' then t.fs else '' end fs0, case when t.xmmc='�ۺϳɼ�' then t.zybjpm else '' end pm0,");
        sql.append("  t.xh,t.xn,t.xq from (select a.*,b.xmmc from xg_zhcp_zcfsb a left join xg_zhcp_zcxmb b on a.xmdm = b.xmdm )  t ) group by xh,xn,xq ) t2");
        sql.append("  on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq ");
        sql.append("  left join view_njxyzybj_all t3 on t1.bjdm=t3.bjdm ");
        sql.append("  left join VIEW_NJSYBJ t11 on t1.BJDM=t11.BJDM  ");
        sql.append(" left join (select count(xh) rs, t1.ZYBJ,t1.XN,t1.XQ  from XG_PJPY_NEW_CPMDB t1 ");
        sql.append(" left join XG_ZHCP_FSTJJLB t2 ON t2.BJDM=t1.BJDM and t2.XN=t1.XN and t2.XQ=t1.XQ ");
        sql.append(" where t2.TJZT='1' GROUP BY t1.ZYBJ,t1.XN,t1.XQ)t6 ON t6.ZYBJ=t1.ZYBJ and t1.XN=t6.XN and t1.XQ=t6.XQ ");
        sql.append("  where exists (select 1 from xg_zhcp_fstjjlb t3 where t1.bjdm=t3.bjdm  and t1.xn=t3.xn and t1.xq=t3.xq and t3.tjzt='1')  order by  t1.zybj  ,t2.fs0 desc ");
        sql.append("   ) t where 1=1  and xq = 'on'");
        return sql.toString();
    }

    private String zypmSql(){
        StringBuilder sql = new StringBuilder();
        sql.append("select xn,xq,xh,xm,bjdm,nj,xydm,zydm,sy,fs0 zyfs,pm0 zypm from (");
        sql.append("  select t1.*,t3.nj,t3.xymc,t3.xydm,t3.zydm,t3.zymc,t3.bjmc,t11.sydm sy, ");
        sql.append("  t2.fs0, t2.pm0 || '/' || t6.rs pm0,t1.xn zczq ");
        sql.append("  from xg_pjpy_new_cpmdb t1 left join ( select sum(fs0) fs0,sum(pm0) pm0, xh,xn,xq from ");
        sql.append("  (select  case when t.xmmc='�ۺϳɼ�' then t.fs else '' end fs0, case when t.xmmc='�ۺϳɼ�' then t.njzypm else '' end pm0,t.xh,t.xn,t.xq from ");
        sql.append("  (select a.*,b.xmmc from xg_zhcp_zcfsb a left join xg_zhcp_zcxmb b on a.xmdm = b.xmdm )  t ) group by xh,xn,xq ) t2 on t1.xh=t2.xh and t1.xn=t2.xn and t1.xq=t2.xq ");
        sql.append("  left join view_njxyzybj_all t3 on t1.bjdm=t3.bjdm");
        sql.append("  left join VIEW_NJSYBJ t11 on t1.BJDM=t11.BJDM");
        sql.append(" left join (  select count(xh) rs,zydm,nj,xn,xq from (select distinct t2.nj,t2.zydm,t1.xh,t.xn,t.xq from xg_zhcp_fstjjlb t ");
        sql.append("  left join xg_pjpy_new_cpmdb t1 on t.bjdm = t1.bjdm and t.xn = t1.xn and t.xq = t1.xq ");
        sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm where t.tjzt = '1') group by zydm,nj,xq,xn  )t6 on t3.zydm = t6.zydm and t1.xn = t6.xn and t1.xq = t6.xq and t3.nj = t6.nj  ");
        sql.append("  where exists (select 1 from xg_zhcp_fstjjlb t3 where t1.bjdm=t3.bjdm  and t1.xn=t3.xn and t1.xq=t3.xq and t3.tjzt='1')  ");
        sql.append(" order by t3.zydm ,t2.fs0 desc  ");
        sql.append("  ) t where 1=1  and xq = 'on'");
        return sql.toString();
    }

}
