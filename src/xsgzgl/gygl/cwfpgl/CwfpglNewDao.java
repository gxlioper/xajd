package xsgzgl.gygl.cwfpgl;

import com.zfsoft.ms.mail.util.StringUtils;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class CwfpglNewDao extends SuperDAOImpl<CwfpglForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(CwfpglForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(CwfpglForm cwfpglForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(CwfpglForm cwfpglForm, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(cwfpglForm.getSearchModel());
        // �߼���ѯ����ֵ
        String[] inputV = SearchService.getTjInput(cwfpglForm.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append("select distinct a.*,a.nj || '@!!!' || a.xydm || '@!!!' || a.zydm || '@!!!' || a.bjdm pk ");
        sql.append(",nvl(b.rs,0) rs");
        sql.append(",nvl(c.yfpcws,0) yfpcws");//�ѷ��䴲λ��
        sql.append(",nvl(e.yfpqss,0) yfpqss");//�ѷ���������
        sql.append(",nvl(d.yfplds,0) yfplds");//�ѷ���¥����
        //����
        sql.append(" from (");
        sql.append("  select nj,xydm,xymc,zydm,zymc,bjdm,bjmc");
        sql.append("  from view_njxyzybj_all a");
        sql.append("  group by nj, xydm, xymc,zydm,zymc,bjdm,bjmc");
        sql.append(" ) a ");
        //��ѯ�༶�����ӱ�
        sql.append(" inner join");
        sql.append("  (select count(1) rs,nj,xydm,zydm,zybj");
        sql.append("  from view_xsjbxx a");
        sql.append("  group by nj,xydm,zydm,zybj ) b");
        sql.append(" on b.nj = a.nj and b.xydm = a.xydm and b.zydm = a.zydm and b.zybj = a.bjdm");
        //��ѯ�ѷ��䴲λ���ӱ�
        sql.append(" left join");
        sql.append("  (select count(1) yfpcws,nj,xydm,zydm,bjdm");
        sql.append("  from xg_gygl_new_cwxxb");
        sql.append("  group by nj,xydm,zydm,bjdm) c");
        sql.append(" on c.nj = a.nj and c.xydm = a.xydm and c.zydm = a.zydm and c.bjdm = a.bjdm");
        //��ѯ�ѷ���¥�����ӱ�
        sql.append(" left join");
        sql.append("  (select count(distinct lddm) yfplds,nj,xydm,zydm,bjdm");
        sql.append("  from xg_gygl_new_cwxxb");
        sql.append("  group by nj,xydm,zydm,bjdm) d");
        sql.append(" on d.nj = a.nj and d.xydm = a.xydm and d.zydm = a.zydm and d.bjdm = a.bjdm");
        //��ѯ�ѷ����������ӱ�
        sql.append(" left join ");
        sql.append("  (select count(distinct lddm||qsh) yfpqss,nj,xydm,zydm,bjdm");
        sql.append("  from xg_gygl_new_cwxxb");
        sql.append("  group by nj,xydm,zydm,bjdm) e");
        sql.append(" on e.nj = a.nj and e.xydm = a.xydm and e.zydm = a.zydm and e.bjdm = a.bjdm");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        return getPageList(cwfpglForm,sql.toString(),inputV);
    }

    public List<HashMap<String, String>> getSybjList(CwfpglForm cwfpglForm, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(cwfpglForm.getSearchModel());
        // �߼���ѯ����ֵ
        String[] inputV = SearchService.getTjInput(cwfpglForm.getSearchModel());

        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from (");
        sql.append("select distinct a.*,a.nj || '@!!!' || a.sydm || '@!!!' || a.bjdm pk ");
        sql.append(",nvl(b.rs,0) rs");
        sql.append(",nvl(c.yfpcws,0) yfpcws");//�ѷ��䴲λ��
        sql.append(",nvl(e.yfpqss,0) yfpqss");//�ѷ���������
        sql.append(",nvl(d.yfplds,0) yfplds");//�ѷ���¥����
        //����
        sql.append(" from (");
        sql.append("  select a.nj,b.sydm,c.symc,a.bjdm,a.bjmc");
        sql.append("  from view_njxyzybj_all a");
        sql.append("  inner join XG_XTWH_SYBJGLB b on a.bjdm = b.bjdm");
        sql.append("  left join XG_XTWH_SYDMB c on b.sydm = c.sydm");
        sql.append("  group by a.nj,b.sydm,c.symc,a.bjdm,a.bjmc");
        sql.append(" ) a ");
        //��ѯ�༶�����ӱ�
        sql.append(" inner join");
        sql.append("  (select count(1) rs,nj,a.bjdm,b.sydm");
        sql.append("  from view_xsjbxx a");
        sql.append("  left join XG_XTWH_SYBJGLB b on a.bjdm = b.bjdm");
        sql.append("  left join XG_XTWH_SYDMB c on b.sydm = c.sydm");
        sql.append("  group by nj,a.bjdm,b.sydm ) b");
        sql.append(" on b.nj = a.nj and b.sydm = a.sydm and b.bjdm = a.bjdm");
        //��ѯ�ѷ��䴲λ���ӱ�
        sql.append(" left join");
        sql.append("  (select count(1) yfpcws,nj,sydm,a.bjdm");
        sql.append("  from xg_gygl_new_cwxxb a");
        sql.append("  left join XG_XTWH_SYBJGLB b on a.bjdm = b.bjdm");
        sql.append("  group by nj,sydm,a.bjdm) c");
        sql.append(" on c.nj = a.nj and c.sydm = a.sydm and c.bjdm = a.bjdm");
        //��ѯ�ѷ���¥�����ӱ�
        sql.append(" left join");
        sql.append("  (select count(distinct lddm) yfplds,nj,sydm,a.bjdm");
        sql.append("  from xg_gygl_new_cwxxb a");
        sql.append("  left join XG_XTWH_SYBJGLB b on a.bjdm = b.bjdm");
        sql.append("  group by nj,sydm,a.bjdm,lddm) d");
        sql.append(" on d.nj = a.nj and d.sydm = a.sydm and d.bjdm = a.bjdm");
        //��ѯ�ѷ����������ӱ�
        sql.append(" left join ");
        sql.append("  (select count(distinct lddm||qsh) yfpqss,nj,sydm,a.bjdm");
        sql.append("  from xg_gygl_new_cwxxb a");
        sql.append("  left join XG_XTWH_SYBJGLB b on a.bjdm = b.bjdm");
        sql.append("  group by nj,sydm,a.bjdm,lddm,qsh) e");
        sql.append(" on e.nj = a.nj and e.sydm = a.sydm and e.bjdm = a.bjdm");
        sql.append(" ) t where 1=1 ");
        sql.append(searchTj);
        return getPageList(cwfpglForm,sql.toString(),inputV);
    }
    //�꼶������
    public List<HashMap<String,String>> getNjList(){
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct nj from view_njxyzybj_bzr order by nj");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }
    //�꼶��������Ժ�����ã�
    public List<HashMap<String,String>> getNjListForSy(){
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct nj from view_njsybj order by nj");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }
    //ѧԺ������
    public List<HashMap<String,String>> getXyListByNj(String nj){
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct xydm dm,xymc mc,substr(nvl(f_pinyin(xymc),xymc),0,1) pyszm from view_njxyzybj_bzr where nj = ? order by pyszm ");
        return dao.getListNotOut(sql.toString(),new String[]{nj});
    }
    //��ȡ��Ժ������
    public List<HashMap<String,String>> getSyListByNj(String nj){
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct sydm dm,symc mc,substr(nvl(f_pinyin(symc),symc),0,1) pyszm");
        sql.append(" from view_njsybj ");
        sql.append(" where nj = ? order by pyszm ");
        return dao.getListNotOut(sql.toString(),new String[]{nj});
    }
    //רҵ������
    public List<HashMap<String,String>> getZyListByNjXy(String nj,String xy){
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct zydm dm,zymc mc,substr(nvl(f_pinyin(zymc),zymc),0,1) pyszm from view_njxyzybj_bzr where nj = ? and xydm = ? order by pyszm ");
        return dao.getListNotOut(sql.toString(),new String[]{nj,xy});
    }
    //�༶������
    public List<HashMap<String,String>> getBjListByNjXyZy(String nj,String xy,String zy){
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct bjdm dm,bjmc mc,substr(nvl(f_pinyin(bjmc),bjmc),0,1) pyszm from view_njxyzybj_bzr where nj = ? and xydm = ? and zydm = ? order by pyszm ");
        return dao.getListNotOut(sql.toString(),new String[]{nj,xy,zy});
    }
    //��ȡ�����༶��������Ժ�����ã�
    public List<HashMap<String,String>> getBjListByNjSy(String nj,String sy){
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct bjdm dm,bjmc mc,substr(nvl(f_pinyin(bjmc),bjmc),0,1) pyszm from view_njsybj where nj = ? and sydm = ? order by pyszm ");
        return dao.getListNotOut(sql.toString(),new String[]{nj,sy});
    }
    //��ȡ��ǰ�༶�ѷ���ͳ�Ʊ����Ϣ
    public List<HashMap<String,String>> getYfpTjXx(String nj,String xy,String zy,String bj){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.bjcws");
        sql.append(" from (");
        sql.append("   select a.lddm,c.ldmc,count(1) cws");
        sql.append("     ,count(case when a.bjdm is not null then 1 end) yfpcws");
        sql.append("     ,count(case when a.bjdm is null then 1 end) kcws");
        sql.append("   from xg_gygl_new_cwxxb a");
        sql.append("   left join xg_gygl_new_qsxxb b on a.lddm = b.lddm and a.qsh = b.qsh");
        sql.append("   left join xg_gygl_new_ldxxb c on a.lddm = c.lddm");
        sql.append("   group by a.lddm,c.ldmc");
        sql.append(" ) a");
        sql.append(" inner join (");
        sql.append("   select a.lddm,count(1) bjcws");
        sql.append("   from xg_gygl_new_cwxxb a");
        sql.append("   left join xg_gygl_new_qsxxb b on a.lddm = b.lddm and a.qsh = b.qsh");
//        sql.append("   left join xg_gygl_new_ldxxb c on a.lddm = c.lddm");
        sql.append("   where a.nj = ? and a.xydm = ? and a.zydm = ? and a.bjdm = ?");
        sql.append("   group by a.lddm");
        sql.append("  ) b");
        sql.append(" on a.lddm = b.lddm");
        return dao.getListNotOut(sql.toString(),new String[]{nj,xy,zy,bj});
    }
    //��ȡ��ǰ�༶�ѷ���ͳ�Ʊ����Ϣ����Ժ�����ã�
    public List<HashMap<String,String>> getYfpTjXxForSy(String nj,String sy,String bj){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,b.bjcws");
        sql.append(" from (");
        sql.append("   select a.lddm,c.ldmc,count(1) cws");
        sql.append("     ,count(case when a.bjdm is not null then 1 end) yfpcws");
        sql.append("     ,count(case when a.bjdm is null then 1 end) kcws");
        sql.append("   from xg_gygl_new_cwxxb a");
        sql.append("   left join xg_gygl_new_qsxxb b on a.lddm = b.lddm and a.qsh = b.qsh");
        sql.append("   left join xg_gygl_new_ldxxb c on a.lddm = c.lddm");
        sql.append("   group by a.lddm,c.ldmc");
        sql.append(" ) a");
        sql.append(" inner join (");
        sql.append("   select a.lddm,count(1) bjcws");
        sql.append("   from xg_gygl_new_cwxxb a");
        sql.append("   left join xg_gygl_new_qsxxb b on a.lddm = b.lddm and a.qsh = b.qsh");
//        sql.append("   left join xg_gygl_new_ldxxb c on a.lddm = c.lddm");
        sql.append("   where a.nj = ? and a.xydm = ? and a.bjdm = ?");
        sql.append("   group by a.lddm");
        sql.append("  ) b");
        sql.append(" on a.lddm = b.lddm");
        return dao.getListNotOut(sql.toString(),new String[]{nj,sy,bj});
    }
    //��ȡ��ǰ�༶�ѷ���ͳ����Ϣ
    public HashMap<String,String> getBjTjXx(String nj,String xydm,String zydm,String zybj){
        StringBuilder sql = new StringBuilder();
        sql.append("select nvl(a.rs,0) rs");//�༶����
        sql.append(",nvl(c.yfpcws,0) yfpcws");//�ѷ��䴲λ��
        sql.append(",nvl(e.yfpqss,0) yfpqss");//�ѷ���������
        sql.append(",nvl(d.yfplds,0) yfplds");//�ѷ���¥����
        //��ѯ�༶��������
        sql.append(" from ");
        sql.append("  (select count(1) rs,nj,xydm,zydm,zybj");
        sql.append("  from view_xsjbxx a");
        sql.append("  group by nj,xydm,zydm,zybj ) a");
        //��ѯ�ѷ��䴲λ���ӱ�
        sql.append(" left join");
        sql.append("  (select count(1) yfpcws,nj,xydm,zydm,bjdm");
        sql.append("  from xg_gygl_new_cwxxb");
        sql.append("  group by nj,xydm,zydm,bjdm) c");
        sql.append(" on c.nj = a.nj and c.xydm = a.xydm and c.zydm = a.zydm and c.bjdm = a.zybj");
        //��ѯ�ѷ���¥�����ӱ�
        sql.append(" left join");
        sql.append("  (select count(distinct lddm) yfplds,nj,xydm,zydm,bjdm");
        sql.append("  from xg_gygl_new_cwxxb");
        sql.append("  group by nj,xydm,zydm,bjdm) d");
        sql.append(" on d.nj = a.nj and d.xydm = a.xydm and d.zydm = a.zydm and d.bjdm = a.zybj");
        //��ѯ�ѷ����������ӱ�
        sql.append(" left join ");
        sql.append("  (select count(distinct lddm|| qsh) yfpqss,nj,xydm,zydm,bjdm");
        sql.append("  from xg_gygl_new_cwxxb");
        sql.append("  group by nj,xydm,zydm,bjdm) e");
        sql.append(" on e.nj = a.nj and e.xydm = a.xydm and e.zydm = a.zydm and e.bjdm = a.zybj");
        sql.append(" where a.nj=? and a.xydm=? and a.zydm=? and a.zybj=?");
        return dao.getMapNotOut(sql.toString(),new String[]{nj,xydm,zydm,zybj});
    }
    //��ȡ��ǰ�༶�ѷ���ͳ����Ϣ����Ժ�����ã�
    public HashMap<String,String> getBjTjXxForSy(String nj,String sydm,String bjdm){
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct nvl(a.rs,0) rs");//�༶����
        sql.append(",nvl(c.yfpcws,0) yfpcws");//�ѷ��䴲λ��
        sql.append(",nvl(e.yfpqss,0) yfpqss");//�ѷ���������
        sql.append(",nvl(d.yfplds,0) yfplds");//�ѷ���¥����
        //��ѯ�༶��������
        sql.append(" from ");
        sql.append("  (select count(1) rs,nj,sydm1 sydm,bjdm");
        sql.append("  from view_xsjbxx a");
        sql.append("  group by nj,sydm1,bjdm ) a");
        //��ѯ�ѷ��䴲λ���ӱ�
        sql.append(" left join");
        sql.append("  (select count(1) yfpcws,nj,xydm,bjdm");
        sql.append("  from xg_gygl_new_cwxxb");
        sql.append("  group by nj,xydm,bjdm) c");
        sql.append(" on c.nj = a.nj and c.xydm = a.sydm and c.bjdm = a.bjdm");
        //��ѯ�ѷ���¥�����ӱ�
        sql.append(" left join");
        sql.append("  (select count(distinct lddm) yfplds,nj,xydm,bjdm");
        sql.append("  from xg_gygl_new_cwxxb");
        sql.append("  group by nj,xydm,bjdm,lddm) d");
        sql.append(" on d.nj = a.nj and d.xydm = a.sydm and d.bjdm = a.bjdm");
        //��ѯ�ѷ����������ӱ�
        sql.append(" left join ");
        sql.append("  (select count(distinct lddm|| qsh) yfpqss,nj,xydm,bjdm");
        sql.append("  from xg_gygl_new_cwxxb");
        sql.append("  group by nj,xydm,bjdm) e");
        sql.append(" on e.nj = a.nj and e.xydm = a.sydm and e.bjdm = a.bjdm");
        sql.append(" where a.nj=? and a.sydm=? and a.bjdm=?");
        return dao.getMapNotOut(sql.toString(),new String[]{nj,sydm,bjdm});
    }
    /**
     * ���ݵ���¥�������ţ���ȡ��λ��Ϣ
     * @param lddm
     * @param ch
     * @param user
     * @return
     */
    public List<HashMap<String,String>> getQsCw(String lddm,String ch,User user){
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.* from(");
        sql.append(" select a.lddm,a.qsh,b.ch,b.qsxb,a.cwh,b.xydm qsss,c.ldmc");
        sql.append(" ,case when b.qsxb = '1' then '��' when b.qsxb = '2' then 'Ů' else b.qsxb end qsxbmc");
        sql.append(" ,case when a.xh is not null then '��' else '��' end sfrz");
        sql.append(" ,case when a.sfbl='1' then '��' else '��' end sfblmc");
        sql.append(" ,nvl(d.bmmc,d1.symc) qsssmc,a.nj,a.zydm,a.bjdm,e.zymc,nvl(e.bjmc,f.bjmc) bjmc");
        sql.append(" from xg_gygl_new_cwxxb a");
        sql.append(" left join xg_gygl_new_qsxxb b on a.lddm = b.lddm and a.qsh = b.qsh");
        sql.append(" left join xg_gygl_new_ldxxb c on a.lddm = c.lddm");
        sql.append(" left join ZXBZ_XXBMDM d on b.xydm = d.bmdm");
        sql.append(" left join XG_XTWH_SYDMB d1 on b.xydm = d1.sydm");
        sql.append(" left join view_njxyzybj_bzr e on a.nj = e.nj and a.xydm = e.xydm and a.zydm = e.zydm and a.bjdm = e.bjdm");
        sql.append(" left join view_njsybj f on a.nj = f.nj and a.xydm = f.sydm and a.bjdm = f.bjdm");
        sql.append(" ) a");
        sql.append(" where a.lddm = ? and ch = ? ");
        List<String> input = new ArrayList<String>();
        input.add(lddm);
        input.add(ch);
        if(!("xx".equalsIgnoreCase(user.getUserType()) || "admin".equalsIgnoreCase(user.getUserType()))){
            //ɸѡ����¥�����ڱ�ѧԺ������
            sql.append(" and a.qsss = ?");
            if("".equals(user.getUserType())){
                input.add(user.getUserSyDep());
            } else {
                input.add(user.getUserDep());
            }

        }
        sql.append(" order by a.qsh,a.cwh");
        return dao.getListNotOut(sql.toString(),input.toArray(new String[input.size()]));
    }
    /**
     * ����¥�����룬�����Ա𣬻�ȡ¥����Ϣ
     * @param lddm
     * @param qsxb
     * @param user
     * @return
     */
    public List<HashMap<String,String>> getLcxx(String lddm,String qsxb,User user){
        List<String> input = new ArrayList<String>();
        StringBuilder sql = new StringBuilder();
        sql.append("select a.*,(cws-yfpcws) kcws from (");
        sql.append("select a.lddm,a.ldmc,a.ch,a.cws,b.qss,nvl(c.yfpcws,0) yfpcws,a.qsxb ");
        sql.append(" from (");
        sql.append(" select count(1) cws,b.ch,b.lddm,b.qsxb,c.ldmc");
        sql.append(" from xg_gygl_new_cwxxb a");
        sql.append(" left join xg_gygl_new_qsxxb b on a.lddm = b.lddm and a.qsh = b.qsh");
        sql.append(" left join XG_GYGL_NEW_LDXXB c on a.lddm = c.lddm");
        if(!("xx".equalsIgnoreCase(user.getUserType()) || "admin".equalsIgnoreCase(user.getUserType()))){
            sql.append(" where b.xydm = ?");
            input.add(user.getUserDep());
        }
        sql.append(" group by b.lddm,c.ldmc,b.ch,b.qsxb");
        sql.append(" ) a");
        sql.append(" left join (");
        sql.append(" select count(1) qss,a.ch,a.lddm");
        sql.append(" from xg_gygl_new_qsxxb a");
        sql.append(" group by a.lddm,a.ch");
        sql.append(" ) b on a.lddm = b.lddm and a.ch = b.ch");
        sql.append(" left join (");
        sql.append(" select a.lddm,b.ch,count(case when a.bjdm is not null then 1 end) yfpcws");
        sql.append(" from xg_gygl_new_cwxxb a");
        sql.append(" left join xg_gygl_new_qsxxb b on a.lddm = b.lddm and a.qsh = b.qsh");
        sql.append(" group by a.lddm,b.ch");
        sql.append(" ) c on a.lddm = c.lddm and a.ch = c.ch");
        sql.append(" order by a.lddm,a.ch");
        sql.append(" ) a ");
        sql.append(" where a.lddm = ? ");

        input.add(lddm);
        if(StringUtils.isNotEmpty(qsxb)){
            sql.append(" and a.qsxb = ?");
            input.add(qsxb);
        }
        return dao.getListNotOut(sql.toString(),input.toArray(new String[input.size()]));
    }

    /**
     * ���ݶ��¥�������ţ���ȡ��λ��Ϣ(�����ѯ��)
     */
    public List<String> getCw(String lddm,String[] lcs,User user) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.*,a.ch || '@!!!' || a.qsh || '@!!!' || a.cwh pk from(");
        sql.append(" select a.lddm,a.qsh,a.cwh,b.ch,b.xydm qsss");
        sql.append(" from xg_gygl_new_cwxxb a");
        sql.append(" left join xg_gygl_new_qsxxb b on a.lddm = b.lddm and a.qsh = b.qsh");
        sql.append(" where a.bjdm is null ");
        sql.append(" ) a");
        sql.append(" where (");
        for(int i=0;i<lcs.length;i++){
            sql.append(" (a.lddm = '"+lddm+"' and a.ch = '"+lcs[i]+"') ");
            if(i<lcs.length-1){
                sql.append(" or ");
            }
        }
        sql.append(" ) ");
        if(!("xx".equalsIgnoreCase(user.getUserType()) || "admin".equalsIgnoreCase(user.getUserType()))){
            //ɸѡ����¥�����ڱ�ѧԺ������
            sql.append(" and a.qsss = ?");
        }
        sql.append(" order by a.qsh,a.cwh");
        return dao.getList(sql.toString(),new String[]{},"pk");
    }

    //��Ժ���䱣��
    public boolean saveForSy(CwfpglForm model,List<String> keys) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("merge into xg_gygl_new_cwxxb a");
        sql.append(" USING ( ");
        int listSize = keys.size();
        for(int i=0;i<listSize;i++){
            String[] pk = keys.get(i).split("@!!!");//pk[0] lc;pk[1] qsh;pk[2] cwh
            sql.append("select '"+model.getLddmkey()+"' lddm,'"+pk[1]+"' qsh,'"+pk[2]+"' cwh,'"+model.getNj()+"' nj,");
            sql.append(" '"+model.getSydm()+"' xydm,'"+model.getBjdm()+"' bjdm from dual ");
            if(i<listSize-1){
                sql.append(" union ");
            }
        }
        sql.append(" ) b ON (a.lddm = b.lddm and a.qsh = b.qsh and a.cwh = b.cwh )");
        sql.append(" WHEN matched THEN UPDATE set a.nj = b.nj,a.xydm = b.xydm, a.bjdm = b.bjdm");
        return dao.runUpdate(sql.toString(),new String[]{});
    }

    //ѧԺ���䱣��
    public boolean save(CwfpglForm model,List<String> keys) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("merge into xg_gygl_new_cwxxb a");
        sql.append(" USING ( ");
        int listSize = keys.size();
        for(int i=0;i<listSize;i++){
            String[] pk = keys.get(i).split("@!!!");//pk[0] lc;pk[1] qsh;pk[2] cwh
            sql.append("select '"+model.getLddmkey()+"' lddm,'"+pk[1]+"' qsh,'"+pk[2]+"' cwh,'"+model.getNj()+"' nj,");
            sql.append(" '"+model.getXydm()+"' xydm,'"+model.getZydm()+"' zydm,'"+model.getBjdm()+"' bjdm from dual ");
            if(i<listSize-1){
                sql.append(" union ");
            }
        }
        sql.append(" ) b ON (a.lddm = b.lddm and a.qsh = b.qsh and a.cwh = b.cwh )");
        sql.append(" WHEN matched THEN UPDATE set a.nj = b.nj,a.xydm = b.xydm, a.zydm = b.zydm, a.bjdm = b.bjdm");
        return dao.runUpdate(sql.toString(),new String[]{});
    }

    //��շ���
    public boolean qkFp(CwfpglForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("update xg_gygl_new_cwxxb set  nj='' , xydm='' , zydm='' , bjdm='' where lddm=?");
        return dao.runUpdate(sql.toString(),new String[]{t.getLddm()});
    }
}
