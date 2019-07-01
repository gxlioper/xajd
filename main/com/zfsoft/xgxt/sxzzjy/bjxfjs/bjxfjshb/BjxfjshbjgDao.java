package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:班级学风建设汇报结果
 * @作者： lgx [工号:1553]
 * @时间： 2018-07-30 14:48
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxfjshbjgDao extends SuperDAOImpl<BjxfjshbjgForm> {
    @Override
    protected void setTableInfo() {
        super.setTableName("xg_sxzzjy_bjxfjs_bjxfjshbjgb");
        super.setKey("id");
        super.setClass(BjxfjshbjgForm.class);
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjxfjshbjgForm bjxfjshbjgForm) throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(BjxfjshbjgForm bjxfjshbjgForm, User user) throws Exception {
        return null;
    }

    public boolean delete(BjxfjshbjgForm model) throws Exception {
        String sql = "delete from xg_sxzzjy_bjxfjs_bjxfjshbjgb where jgid=? and hblx=?";
        return dao.runUpdate(sql,new String[]{model.getJgid(),model.getHblx()});
    }

    /**
     * @描述:审核撤销时，删除结果表数据
     * @作者：lgx [工号：1553]
     * @日期：2018/7/30 15:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [ywid]
     * @return: boolean
     */
    public boolean deleteBjxfjshbjg(String ywid) throws Exception {
        String[] inputV = new String[1];
        StringBuilder sql = new StringBuilder();
        sql.append(" delete from xg_sxzzjy_bjxfjs_bjxfjshbjgb ");
        sql.append(" where lcywid = ? ");
        inputV[0] = ywid;
        return dao.runDelete(sql.toString(),inputV)>0 ;
    }

    public HashMap<String,String> getInfo(BjxfjshbjgForm model) {
        String sql = "select * from xg_sxzzjy_bjxfjs_bjxfjshbjgb where jgid=? and hblx=?";
        return dao.getMapNotOut(sql,new String[]{model.getJgid(),model.getHblx()});
    }

    public HashMap<String,String> getWordInfo(String id,String hblx) {
        String sql = "select a.*,c.sblxmc,b.xn from xg_sxzzjy_bjxfjs_bjxfjshbjgb a " +
                " left join xg_sxzzjy_bjxfjs_bjxfjsjgb b on b.jgid=a.jgid " +
                " left join xg_sxzzjy_bjxfjs_sblxdmb c on c.sblxdm=b.sblx where a.jgid=? and a.hblx=?";
        return dao.getMapNotOut(sql,new String[]{id,hblx});
    }
}
