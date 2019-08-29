package com.zfsoft.xgxt.xyfd.wfcyywh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqDao;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;

import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/6.
 */
public class FdyyDao extends SuperDAOImpl<FdyyForm> {
    private FdkcsqDao fdkcsqDao = new FdkcsqDao();
    @Override
    protected void setTableInfo() {
        super.setClass(FdyyForm.class);
        super.setKey("yyid");
        super.setTableName("xg_xyfd_wdyy");
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdyyForm fdyyForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(FdyyForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select a.*,b.xm,decode(a.zt,'0','δ�ύ','1','������','5','ԤԼ��','3','��ȡ��','4','�Ѹ���','6','������',a.zt) shztmc from (");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.zgh yhm,e.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid  ");
        sql.append(" left join xg_xyfd_fdjsxxb c on b.fdjs = c.djh left join fdyxxb d on c.zgh = d.zgh ");
        sql.append(" left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'JS%' union  ");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.xh yhm,e.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid ");
        sql.append(" left join xg_xyfd_pbjgb c on b.fdjs = c.djh left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc  ");
        sql.append(" from XSXXB a left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) d  ");
        sql.append(" on c.xh = d.xh left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'PB%' ");
        sql.append(") a left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b on a.xh = b.xh ");
        sql.append(") where 1=1 ");

        if(user.getUserType().equals("stu")){
            if (isPb(user)){
                sql.append(" and yhm = '" + user.getUserName() + "' ");
            }else {
                sql.append(" and xh = '" + user.getUserName() + "'");
            }
        }else {
            sql.append(" and yhm = '" + user.getUserName() + "' ");
        }
        if(!StringUtil.isNull(t.getYyzt())&&t.getYyzt().equals("dsh")){//ɸѡ�����ύ��ԤԼ
            sql.append(" and (zt <> '0' or zt <> '3') ");
        }
        if(!StringUtil.isNull(t.getYyzt())&&t.getYyzt().equals("dfd")){//ɸѡ����ȷ�ϵ�ԤԼ
            sql.append(" and zt = '1' ");
        }
        if(!StringUtil.isNull(t.getYyzt())&&t.getYyzt().equals("yfd")){//ɸѡ���Ѹ�����ԤԼ
            sql.append(" and (zt = '4' or zt = '6') ");
        }
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * ��ȡ�����γ���Ϣ
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getFdkc(FdyyForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from ( ");
        sql.append(" select a.*,c.xm,c.zgh yhm,d.* from xg_xyfd_fdkcjgb a left join xg_xyfd_fdjsxxb b on a.fdjs = b.djh left join fdyxxb c ");
        sql.append(" on b.zgh = c.zgh left join xg_xyfd_fdsxxb d on b.fds = d.id where a.fdjs like 'JS%' union ");
        sql.append(" select a.*,c.xm,c.xh yhm,d.* from xg_xyfd_fdkcjgb a left join xg_xyfd_pbjgb b on a.fdjs = b.djh left join  ");
        sql.append(" (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) c on b.xh = c.xh left join  ");
        sql.append(" xg_xyfd_fdsxxb d on b.fds = d.id where a.fdjs like 'PB%' ");
        sql.append("  ) t where jgid = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getFdkc()});
    }

    /**
     * ��ȡ���ԤԼ��
     * @return
     * @throws Exception
     */
    public String getMaxYyh() throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select max(yyh) yyh from xg_xyfd_wdyy");
        return dao.getOneRs(sql.toString(),new String[]{},"yyh");
    }

    /**
     * �Ƿ�Ǽǵ�ѧ�����󱲣�
     * @param user
     * @return
     * @throws Exception
     */
    public boolean isPb(User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(1) f from xg_xyfd_pbjgb where xh=?");
        String f = dao.getOneRs(sql.toString(),new String[]{user.getUserName()},"f");
        return Integer.parseInt(f)>0?true:false;
    }

    /**
     * �Ƿ�ǼǵĽ�ʦ
     * @param user
     * @return
     * @throws Exception
     */
    public boolean isJs(User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(1) f from xg_xyfd_fdjsxxb where zgh = ?");
        String f = dao.getOneRs(sql.toString(),new String[]{user.getUserName()},"f");
        return Integer.parseInt(f)>0?true:false;
    }

    /**
     * ȡ��ԤԼ��ԭ���¼
     * @param t
     * @param user
     * @return
     * @throws Exception
     */
    public boolean qxYy(FdyyForm t, User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" merge into XG_XYFD_YYQXJLB a using ");
        sql.append(" (select ? yyid from dual) b on (a.yyid = b.yyid) ");
        sql.append(" when matched then update set a.qxyy=?,qxr=?,qxsj=?,qtqk=? ");
        sql.append(" when not matched then insert values (?,?,?,?,?) ");
        String[] input = new String[]{t.getYyid(),t.getQxyy(), user.getUserName(), GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"),t.getQtqk(),
                t.getYyid(),t.getQxyy(), user.getUserName(), GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"),t.getQtqk()};
        return dao.runUpdate(sql.toString(),input);
    }

    /**
     * ��ԤԼ�Ŀγ�
     * @param t
     * @param user
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> yykcList(FdyyForm t,User user) throws Exception{
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select t.* from ( ");
        sql.append(" select a.*,'��ʦ' fdjslx,c.xm,c.zgh yhm,d.* from xg_xyfd_fdkcjgb a left join xg_xyfd_fdjsxxb b on a.fdjs = b.djh left join fdyxxb c ");
        sql.append(" on b.zgh = c.zgh left join xg_xyfd_fdsxxb d on b.fds = d.id where a.fdjs like 'JS%' union ");
        sql.append(" select a.*,'��' fdjslx,c.xm,c.xh yhm,d.* from xg_xyfd_fdkcjgb a left join xg_xyfd_pbjgb b on a.fdjs = b.djh left join  ");
        sql.append(" (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) c on b.xh = c.xh left join  ");
        sql.append(" xg_xyfd_fdsxxb d on b.fds = d.id where a.fdjs like 'PB%' ");
        sql.append("  ) t where to_char(sysdate,'yyyyMMdd')>t.syksrq and  to_char(sysdate,'yyyyMMdd')<t.syjsrq ");
        if(!StringUtil.isNull(t.getYyzt())&&t.getYyzt().equals("tea")){ //����ԤԼ����ʦ/�󱲽��棩
            boolean isJsOrPb = true;
            if(user.getUserType().equals("stu")){
                isJsOrPb = isPb(user);
                if (isJsOrPb){
                    sql.append(" and t.yhm = '" + user.getUserName() + "' ");
                }
            }else {
                if(!fdkcsqDao.isAdmin(user)){ //���ǹ���ԱҲ���Ǹ���������Ա
                    sql.append(" and t.yhm = '" + user.getUserName() + "' ");
                }
            }
        }
        sql.append(" ");
        sql.append(searchTj);
        return getPageList(t,sql.toString(),inputV);
    }

    /**
     * ��ȡĳѧ��ĳ�γ̵�����ԤԼ
     * @param t
     * @return
     * @throws Exception
     */
    public List<HashMap<String, String>> getMyYyList(FdyyForm t) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select a.*,b.xm,decode(a.zt,'0','δ�ύ','1','������','5','ԤԼ��','3','��ȡ��','4','�Ѹ���','6','������',a.zt) shztmc from (");
        sql.append(" select a.*,b.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid  ");
        sql.append(" left join xg_xyfd_fdjsxxb c on b.fdjs = c.djh where b.fdjs like 'JS%' union  ");
        sql.append(" select a.*,b.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid ");
        sql.append(" left join xg_xyfd_pbjgb c on b.fdjs = c.djh where b.fdjs like 'PB%' ");
        sql.append(") a left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b on a.xh = b.xh ");
        sql.append(") where 1=1 ");
        sql.append(" and xh = ? ");
        sql.append(" and fdkc = ? ");
        return getPageList(t,sql.toString(),new String[]{t.getXh(),t.getFdkc()});
    }

    /**
     * ͨ��ԤԼ�Ų�ѯԤԼ��¼
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getFdyyByYyh(FdyyForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select a.*,b.xm,b.lxdh,decode(a.zt,'0','δ�ύ','1','������','5','ԤԼ��','3','��ȡ��','4','�Ѹ���','6','������',a.zt) shztmc from (");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.zgh yhm,'��ʦ' fdjslb,e.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid  ");
        sql.append(" left join xg_xyfd_fdjsxxb c on b.fdjs = c.djh left join fdyxxb d on c.zgh = d.zgh ");
        sql.append(" left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'JS%' union  ");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.xh yhm,'��' fdjslb,e.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid ");
        sql.append(" left join xg_xyfd_pbjgb c on b.fdjs = c.djh left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc  ");
        sql.append(" from XSXXB a left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) d  ");
        sql.append(" on c.xh = d.xh left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'PB%' ");
        sql.append(") a left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b on a.xh = b.xh ");
        sql.append(") where yyh = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getYyh()});
    }

    public boolean saveFdjl(FdyyForm t,User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("insert into xg_xyfd_fdjlb(yyh,jlsj,jlr,fdjl) values(?,?,?,?)");
        return dao.runUpdate(sql.toString(),new String[]{t.getYyh(),GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"),user.getUserName(),t.getFdjl()});
    }

    /**
     * ͨ��ԤԼ�Ų�ѯ������¼
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getFdjl(FdyyForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from xg_xyfd_fdjlb where yyh = ?");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getYyh()});
    }

    public boolean updateFdyyByYyh(FdyyForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("update xg_xyfd_wdyy set zt = ? where yyh = ? ");
        return dao.runUpdate(sql.toString(),new String[]{t.getZt(),t.getYyh()});
    }

    /**
     * ѧ���Կγ̵�ԤԼ��¼
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getFdyy(FdyyForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("select * from (");
        sql.append("select a.*,b.xm,b.lxdh,decode(a.zt,'0','δ�ύ','1','������','5','ԤԼ��','3','��ȡ��','4','�Ѹ���','6','������',a.zt) shztmc from (");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.zgh yhm,'��ʦ' fdjslb,e.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid  ");
        sql.append(" left join xg_xyfd_fdjsxxb c on b.fdjs = c.djh left join fdyxxb d on c.zgh = d.zgh ");
        sql.append(" left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'JS%' union  ");
        sql.append(" select a.*,b.*,d.xm fdjsxm,d.xh yhm,'��' fdjslb,e.* from xg_xyfd_wdyy a left join xg_xyfd_fdkcjgb b on a.fdkc = b.jgid ");
        sql.append(" left join xg_xyfd_pbjgb c on b.fdjs = c.djh left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc  ");
        sql.append(" from XSXXB a left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) d  ");
        sql.append(" on c.xh = d.xh left join xg_xyfd_fdsxxb e on c.fds = e.id where b.fdjs like 'PB%' ");
        sql.append(") a left join (select a.*, c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a left join view_njsybj b ");
        sql.append(" on a.bjdm = b.bjdm left join view_njxyzybj_all c on a.zybj = c.bjdm) b on a.xh = b.xh ");
        sql.append(") where fdkc = ? and xh = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getFdkc(),t.getXh()});
    }

    /**
     * ��ȡ�γ����ۼ�¼
     * @param t
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getKcpj(FdyyForm t) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_xyfd_yypjjlb where jlbh = ? ");
        return dao.getMapNotOut(sql.toString(),new String[]{t.getYyh()});
    }

    /**
     * ����γ�����
     * @param t
     * @return
     * @throws Exception
     */
    public boolean saveKcpj(FdyyForm t,User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("insert into xg_xyfd_yypjjlb(jlbh,pf,sfjj,xxpj,pjr,pjsj,lx) values(?,?,?,?,?,?,?)");
        String[] input = new String[]{t.getYyh(),t.getPf(),t.getSfjj(),t.getXxpj(),user.getUserName(),GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"),"fd"};
        return dao.runUpdate(sql.toString(),input);
    }
    public boolean updateKcpj(FdyyForm t,User user) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append("update xg_xyfd_yypjjlb set pf = ?,sfjj = ?,xxpj = ? where pjid = ?");
        return dao.runUpdate(sql.toString(),new String[]{t.getPf(),t.getSfjj(),t.getXxpj(),t.getPjid()});
    }

    public boolean updateFdjl(String jlbh) throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" update xg_xyfd_wdyy set zt = '6' where yyh = ? ");
        return dao.runUpdate(sql.toString(),new String[]{jlbh});
    }

    /**
     * �������ԤԼȡ��ԭ���������
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getQxyyList() throws Exception{
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from xg_xyfd_yyqxyydmb ");
        return dao.getListNotOut(sql.toString(),new String[]{});
    }
}
