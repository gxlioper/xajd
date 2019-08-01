package com.zfsoft.xgxt.xszz.xfjm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.szdw.xgsz.CsszModel;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称:
 * @类功能描述:
 * @作者: 何爽 [工号:1730]
 * @时间: 2019/7/1 20:36
 */
public class XfjmCsszDao extends SuperDAOImpl<CsszModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

    @Override
    protected void setTableInfo() {
        super.setClass(CsszModel.class);
        super.setKey("id");
        super.setTableName("xg_xszz_new_xfjm_shkgsz");
    }


    @Override
    public List<HashMap<String, String>> getPageList(CsszModel t)
            throws Exception {
        return null;
    }

    @Override
    public List<HashMap<String, String>> getPageList(CsszModel t, User user)
            throws Exception {
        return null;
    }

    public boolean insert(CsszModel model){
        try {
            String sql = "insert into xg_xszz_new_xfjm_shkgsz (id,sqkg,sqkssj,sqjssj,splc) values (?,?,?,?,?)";
            String[] input = {
                    model.getId(), model.getSqkg(), model.getSqkssj(), model.getSqjssj(), model.getSplc()
            };
            return dao.runUpdate(sql, input);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(CsszModel model){
        try {
            String sql = "update xg_xszz_new_xfjm_shkgsz set sqkg=?,sqkssj=?,sqjssj=?,splc=? where id=?";
            String[] input = {
                     model.getSqkg(), model.getSqkssj(), model.getSqjssj(), model.getSplc(),model.getId()
            };
            return dao.runUpdate(sql, input);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public CsszModel getModel() throws Exception {
        String sql = "select * from xg_xszz_new_xfjm_shkgsz where rownum=1";
        return super.getModel(sql, new String[]{});
    }
}
