package com.zfsoft.xgxt.xyfd.pjwh;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqDao;
import common.newp.StringUtil;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/17.
 */
public class MydDao extends SuperDAOImpl<MydForm> {
    private FdkcsqDao fdkcsqDao = new FdkcsqDao();
    @Override
    protected void setTableInfo() {
        super.setClass(MydForm.class);
        super.setKey("pjid");
        super.setTableName("xg_xyfd_yypjjlb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(MydForm mydForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(MydForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select a.*,b.pf from( ");
        sql.append(" select yyh jlbh,xh,fdsj,'fd' lx,'�γ̸���' lxmc,fdjslx,fdsdd fddd,fdjsxm,kcmc,yyr,zt, ");
        sql.append(" decode(zt,'4','δ����','6','������',zt) ztmc from ( ");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.zgh yhm,e.*,'��ʦ' fdjslx from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid ");
        sql.append("  left join xg_xyfd_fdjsxxb c on b.fdjs = c.djh left join fdyxxb d on c.zgh = d.zgh  ");
        sql.append(" left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'JS%' union  ");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.xh yhm,e.*,'��' fdjslx from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid ");
        sql.append("  left join xg_xyfd_pbjgb c on b.fdjs = c.djh left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc  ");
        sql.append(" from XSXXB a left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) d ");
        sql.append(" on c.xh = d.xh left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'PB%' ) where zt = '4' or zt = '6' ");
        sql.append(" union ");
        sql.append(" select zxid jlbh,xh,fdrq fdjs,'zx' lx,'ѧҵ��רҵ��ѯ' lxmc,fdjslx,fddd,fdjsxm,'' kcmc,'����' yyr,zxzt zt, ");
        sql.append(" decode(zxzt,null,'δ����','1','������',zxzt) ztmc from ( ");
        sql.append(" select a.*,c.xm fdjsxm,'��ʦ' fdjslx from xg_xyfd_xyzyzxjlb a left join xg_xyfd_fdjsxxb b on a.fdjs = b.djh ");
        sql.append(" left join fdyxxb c on b.zgh = c.zgh where a.fdjs like  'JS%' union  ");
        sql.append(" select a.*,c.xm fdjsxm,'��' fdjslx from xg_xyfd_xyzyzxjlb a left join xg_xyfd_pbjgb b  on a.fdjs = b.djh ");
        sql.append(" left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc  ");
        sql.append("  from XSXXB a left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) c ");
        sql.append("  on b.xh = c.xh where a.fdjs like 'PB%' ) ) a left join xg_xyfd_yypjjlb b on a.jlbh = b.jlbh where 1=1");
        if(!StringUtil.isNull(t.getPjzt())&&t.getPjzt().equals("ypj")){//������
            sql.append(" and ztmc = '������' ");
        }else if(!StringUtil.isNull(t.getPjzt())&&t.getPjzt().equals("dpj")) {//������
            sql.append(" and ztmc = 'δ����' ");
        }
        if(!fdkcsqDao.isAdmin(user)){ //���ǹ���ԱҲ���Ǹ���������Ա
            sql.append(" and xh = '" + user.getUserName() + "' ");
        }
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * ��ȡ��¼����Ϣ
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getJlxx(MydForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from( ");
        sql.append(" select yyh jlbh,xh,fdsj,'fd' lx,'�γ̸���' lxmc,fdjslx,fdsdd fddd,fdjsxm,kcmc,yyr,zt, ");
        sql.append(" decode(zt,'4','δ����','6','������',zt) ztmc from ( ");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.zgh yhm,e.*,'��ʦ' fdjslx from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid ");
        sql.append("  left join xg_xyfd_fdjsxxb c on b.fdjs = c.djh left join fdyxxb d on c.zgh = d.zgh  ");
        sql.append(" left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'JS%' union  ");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.xh yhm,e.*,'��' fdjslx from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid ");
        sql.append("  left join xg_xyfd_pbjgb c on b.fdjs = c.djh left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc  ");
        sql.append(" from XSXXB a left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) d ");
        sql.append(" on c.xh = d.xh left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'PB%' ) where zt = '4' or zt = '6' ");
        sql.append(" union ");
        sql.append(" select zxid jlbh,xh,fdrq fdjs,'zx' lx,'ѧҵ��רҵ��ѯ' lxmc,fdjslx,fddd,fdjsxm,'' kcmc,'����' yyr,zxzt zt, ");
        sql.append(" decode(zxzt,null,'δ����','1','������',zxzt) ztmc from ( ");
        sql.append(" select a.*,c.xm fdjsxm,'��ʦ' fdjslx from xg_xyfd_xyzyzxjlb a left join xg_xyfd_fdjsxxb b on a.fdjs = b.djh ");
        sql.append(" left join fdyxxb c on b.zgh = c.zgh where a.fdjs like  'JS%' union  ");
        sql.append(" select a.*,c.xm fdjsxm,'��' fdjslx from xg_xyfd_xyzyzxjlb a left join xg_xyfd_pbjgb b  on a.fdjs = b.djh ");
        sql.append(" left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc  ");
        sql.append("  from XSXXB a left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) c ");
        sql.append("  on b.xh = c.xh where a.fdjs like 'PB%' ) )  where jlbh = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getJlbh()});
    }

    /**
     * ͨ����¼��Ų�ѯ����
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getPjxx(MydForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from xg_xyfd_yypjjlb where jlbh = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getJlbh()});
    }

    /**
     * ���ۺ�״̬��Ϊ������
     * @param jlbh
     * @return
     * @throws Exception
     */
    public boolean updateFdjl(String jlbh) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" update xg_xyfd_wdyy set zt = '6' where yyh = ? ");
        return dao.runUpdate(sql.toString(),new String[]{jlbh});
    }
    public boolean updateZxjl(String jlbh) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" update xg_xyfd_xyzyzxjlb set zxzt = '1' where zxid = ? ");
        return dao.runUpdate(sql.toString(),new String[]{jlbh});
    }

    public boolean savePj(MydForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select 1 f from xg_xyfd_yypjjlb where jlbh = ? ");
        String f = dao.getOneRs(sql.toString(),new String[]{t.getJlbh()},"f");
        if(StringUtil.isNull(f)){
            sql = new StringBuilder();
            sql.append("insert into xg_xyfd_yypjjlb(jlbh,pf,sfjj,xxpj,pjr,pjsj,lx) values(?,?,?,?,?,?,?) ");
            String[] intputV = new String[]{t.getJlbh(),t.getPf(),t.getSfjj(),t.getXxpj(),t.getPjr(),t.getPjsj(),t.getLx()};
            return dao.runUpdate(sql.toString(),intputV);
        }else {
            sql = new StringBuilder();
            sql.append("update xg_xyfd_yypjjlb set pf=?,sfjj=?,xxpj=?,pjr=?,pjsj=?,lx=? where jlbh = ?");
            String[] inputV = new String[]{t.getPf(),t.getSfjj(),t.getXxpj(),t.getPjr(),t.getPjsj(),t.getLx(),t.getJlbh()};
            return dao.runUpdate(sql.toString(),inputV);
        }
    }
}
