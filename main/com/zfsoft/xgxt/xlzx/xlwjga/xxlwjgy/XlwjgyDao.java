package com.zfsoft.xgxt.xlzx.xlwjga.xxlwjgy;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-04-10 14:42
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XlwjgyDao extends SuperDAOImpl<XlwjgyForm> {
    @Override
    protected void setTableInfo() {
        super.setClass(XlwjgyForm.class);
        super.setKey("id");
        super.setTableName("xg_xljl_xlwjgyb");
    }

    @Override
    public List<HashMap<String, String>> getPageList(XlwjgyForm xlwjgyForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(XlwjgyForm t, User user) throws Exception {
        String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
        String[] inputV = SearchService.getTjInput(t.getSearchModel());
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ( ");
        sql.append(" select a.*, ");
        sql.append(" decode(a.fxtj,'fdy','����Ա','stu','ѧ��','tel','�绰����','bbs','BBs','zx','��ѯ','qq','QQ','qt','����',a.fxtj) fxtjmc ,");
        sql.append(" decode(a.wjcd,'fcjj','�ǳ�����','jj','����','yb','һ��','bj','����',a.wjcd) wjcdmc ,");
        sql.append(" decode(a.wjgyfs,'xcgy','�ֳ���Ԥ','zdxy','ָ����Ժ/ѧԺ','yjzx','����ԤԼ��ѯ','jyyl','����ҽ��',a.wjgyfs) wjgyfsmc ,");
        sql.append(" decode(a.xtbm,'sy','��Ժ/ѧԺ','gac','������','wlzx','��������','xyy','УҽԺ','qt','����',a.xtbm) xtbmmc ,");
        sql.append(" decode(a.wjgyjg,'sljj','˳�����','ydgj','�д�����','jyyl','����ҽ��','xx','��ѧ','tx','��ѧ','qt','����',a.wjgyjg) wjgyjgmc ,");
        sql.append(" b.xm,b.xb,b.nj,b.xymc,b.bjmc,b.zymc,b.zydm,b.bjdm,b.xydm,");
        sql.append(" b.lxdh,b.zybj,b.zybjmc,b.sydm1 sydm,b.symc1 symc,b.zzmmmc,b.mz ");
        sql.append(" from xg_xljl_xlwjgyb a ");
        sql.append(" left join view_xsjbxx b on a.xh=b.xh ");
        sql.append(" ) t1 where 1=1 ");
//		sql.append(searchTjByUser);
        sql.append(searchTj);
        return getPageList(t, sql.toString(), inputV);
    }

    public HashMap<String,String> getInfoById(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select * from ( ");
        sql.append(" select a.*, ");
        sql.append(" decode(a.fxtj,'fdy','����Ա','stu','ѧ��','tel','�绰����','bbs','BBs','zx','��ѯ','qq','QQ','qt','����',a.fxtj) fxtjmc ,");
        sql.append(" decode(a.wjcd,'fcjj','�ǳ�����','jj','����','yb','һ��','bj','����',a.wjcd) wjcdmc ,");
        sql.append(" decode(a.wjgyfs,'xcgy','�ֳ���Ԥ','zdxy','ָ����Ժ/ѧԺ','yjzx','����ԤԼ��ѯ','jyyl','����ҽ��',a.wjgyfs) wjgyfsmc ,");
        sql.append(" decode(a.xtbm,'sy','��Ժ/ѧԺ','gac','������','wlzx','��������','xyy','УҽԺ','qt','����',a.xtbm) xtbmmc ,");
        sql.append(" decode(a.wjgyjg,'sljj','˳�����','ydgj','�д�����','jyyl','����ҽ��','xx','��ѧ','tx','��ѧ','qt','����',a.wjgyjg) wjgyjgmc ,");
        sql.append(" b.xm,b.xb,b.nj,b.xymc,b.bjmc,b.zymc,b.zydm,b.bjdm,b.xydm,");
        sql.append(" b.lxdh,b.zybj,b.zybjmc,b.sydm1 sydm,b.symc1 symc,b.zzmmmc,b.mz ");
        sql.append(" from xg_xljl_xlwjgyb a ");
        sql.append(" left join view_xsjbxx b on a.xh=b.xh ");
        sql.append(" ) t1 where id=? ");
        return dao.getMapNotOut(sql.toString(),new String[]{id});
    }
}
