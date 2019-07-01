package com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhjg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-09-06 10:57
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BzsbwhjgDao extends SuperDAOImpl<BzsbwhjgForm> {
    @Override
    protected void setTableInfo() {
        super.setKey("jgid");
        super.setTableName("xg_new_cxcy_bzjgb");
        super.setClass(BzsbwhjgForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(BzsbwhjgForm bzsbwhjgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(BzsbwhjgForm t, User user) throws Exception {
        StringBuilder sql = new StringBuilder();
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
        sql.append("select t.* from ( ");
        sql.append(" select a.*,z.xqmc,b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj,c.xm tbrmc ");
        //ͳ�Ʊ�������ҳ��߼���ѯ����Ϊtbsj�����Լ��˸�tbsj
        sql.append(",a.lrsj tbsj ");
        sql.append(" from xg_new_cxcy_bzjgb a ");
        sql.append(" left join view_xsjbxx b on b.xh=a.xh ");
        sql.append(" left join fdyxxb c on c.zgh=a.lrr ");
        sql.append(" left join xqdzb z on z.xqdm=a.xq ");
        sql.append(" ) t  where 1=1 ");
        sql.append(searchTjByUser);
        sql.append(searchTj);
        sql.append(" order by lrsj desc ");
        return getPageList(t, sql.toString(),inputV);
    }



    public boolean checkExistForSave(BzsbwhjgForm model) {
        boolean flag = false;
        StringBuilder sql = new StringBuilder();
        List<String> paraList = new ArrayList<String>();
        paraList.add(model.getXh());
        paraList.add(model.getXn());
        paraList.add(model.getXq());
        paraList.add(model.getXmmc());
        sql.append("select jgid from xg_new_cxcy_bzjgb t where t.xh = ? and t.xn = ? and t.xq = ? and t.xmmc = ? ");
        if(StringUtils.isNotNull(model.getJgid())){
            sql.append(" and jgid <> ? ");
            paraList.add(model.getJgid());
        }
        String num = dao.getOneRs(sql.toString(),paraList.toArray(new String[]{}), "jgid");
        if (num != null && ! num.equals("") ){
            flag = true;
        }
        return flag;
    }

    public HashMap<String,String> getInfoById(String jgid) {
        StringBuilder sql = new StringBuilder();
        sql.append("select t.* from ( ");
        sql.append(" select a.*,z.xqmc,b.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj,c.xm tbrmc");
        sql.append(" from xg_new_cxcy_bzjgb a ");
        sql.append(" left join view_xsjbxx b on b.xh=a.xh ");
        sql.append(" left join fdyxxb c on c.zgh=a.lrr ");
        sql.append(" left join xqdzb z on z.xqdm=a.xq ");
        sql.append(" ) t  where jgid=? ");
        return dao.getMapNotOut(sql.toString(),new String[]{jgid});
    }

    /**
     * @����:�������ҵ������id������id��ɾ�����-��������һ������ʱ��
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/9/6 16:12
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [sqid]
     * @return: int
     */
    public int deleteByLcywid(String sqid) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from xg_new_cxcy_bzjgb where lcywid=? ");
        return dao.runDelete(sql.toString(),new String[]{sqid});
    }

}
