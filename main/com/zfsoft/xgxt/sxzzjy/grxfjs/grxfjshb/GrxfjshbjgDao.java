package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-08-06 10:48
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GrxfjshbjgDao extends SuperDAOImpl<GrxfjshbjgForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_sxzzjy_grxfjs_grxfjshbjgb");
        super.setKey("sqid");
        super.setClass(GrxfjshbjgForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(GrxfjshbjgForm grxfjshbjgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(GrxfjshbjgForm grxfjshbjgForm, User user) throws Exception {
        return null;
    }

    public boolean delete(GrxfjshbjgForm model) throws Exception {
        String sql = "delete from xg_sxzzjy_grxfjs_grxfjshbjgb where jgid=? and hblx=?";
        return dao.runUpdate(sql,new String[]{model.getJgid(),model.getHblx()});
    }

    /**
     * @����:��˳���ʱ��ɾ�����������
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/7/30 15:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [ywid]
     * @return: boolean
     */
    public boolean deleteGrxfjshbjg(String ywid) throws Exception {
        String[] inputV = new String[1];
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from xg_sxzzjy_grxfjs_grxfjshbjgb ");
        sql.append(" where lcywid = ? ");
        inputV[0] = ywid;
        return dao.runDelete(sql.toString(),inputV)>0 ;
    }

    public HashMap<String,String> getInfo(GrxfjshbjgForm model) {
        String sql = "select * from xg_sxzzjy_grxfjs_grxfjshbjgb where jgid=? and hblx=?";
        return dao.getMapNotOut(sql,new String[]{model.getJgid(),model.getHblx()});
    }

    public HashMap<String,String> getWordInfo(String id,String hblx) {
        String sql = "select a.*,c.sblxmc,b.xn from xg_sxzzjy_grxfjs_grxfjshbjgb a " +
                " left join xg_sxzzjy_grxfjs_grxfjsjgb b on b.jgid=a.jgid " +
                " left join xg_sxzzjy_grxfjs_sblxdmb c on c.sblxdm=b.sblx where a.jgid=? and a.hblx=?";
        return dao.getMapNotOut(sql,new String[]{id,hblx});
    }
}
