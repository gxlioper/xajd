package com.zfsoft.xgxt.sxzzjygl.bjhdgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.sxzzjygl.ztbhgl.ZtbhSqForm;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class BjhdSqService extends SuperServiceImpl<BjhdSqForm,BjhdSqDao> {
    private ShlcInterface shlc = new CommShlcImpl();
    public String[] getSqShKg() {
        return dao.getSqShKg();
    }

    public List<HashMap<String,String>> getXxList(BjhdSqForm model, User user) throws Exception {
        return dao.getXxList(model,user);
    }

    public List<HashMap<String,String>> getBjList(BjhdSqForm model, User user) throws Exception {
        return dao.getBjList(model,user);
    }

    public boolean bjhdSqSaveForAdd(BjhdSqForm model) throws Exception {
        String sqid = UniqID.getInstance().getUniqIDHash();
        model.setSqid(sqid);
        //获取基础设置中设置的审核流程
        String splc = dao.getShlcID();
        model.setSplc(splc);
        if ("submit".equals(model.getType())) {
            model.setShzt(Constants.YW_SHZ);// 审核中
        } else {
            model.setShzt(Constants.YW_WTJ);// 未提交
        }
        // 保存申请信息
        boolean result = dao.runInsert(model);
        // 保存审核信息
        if ("submit".equals(model.getType())) {
            if (result) {
                result = shlc.runSubmit(sqid, splc, model.getSqr(),
                        "bjhdgl_bjhdsh.do?method=bjhdSh", "bjhdgl_bjhdsq.do?method=bjhdSq");
            }
        }
        return result;
    }

    public String[] getHdfzr(BjhdSqForm model) {
        return dao.getHdfzr(model);
    }

    public String[] getBjmc(BjhdSqForm model) {
        return dao.getBjmc(model);
    }

    public boolean bjhdSqSaveForEdit(BjhdSqForm model) throws Exception {
        boolean result = false;
        // 如果提交，插入审核状态
        if ("submit".equalsIgnoreCase(model.getType())) {
            model.setShzt(Constants.YW_SHZ);// 审核中
            String splc = dao.getShlcID();
            model.setSplc(splc);
            result = runUpdate(model);
            if (result) {
                result = shlc.runSubmit(model.getSqid(), splc, model.getSqr() ,
                        "bjhdgl_bjhdsh.do?method=bjhdSh", "bjhdgl_bjhdsq.do?method=bjhdSq");
            }
        } else {
            result = runUpdate(model);
        }
        return result;
    }


    public boolean bjhdSqSubmit(BjhdSqForm model) throws Exception {
        boolean result = false;
        model.setShzt(Constants.YW_SHZ);// 审核中
        String splc = dao.getShlcID();
        model.setSplc(splc);
        result = runUpdate(model);
        ShlcInterface shlc = new CommShlcImpl();
        if (result) {
            result = shlc.runSubmit(model.getSqid(), splc,model.getSqr() ,
                    "bjhdgl_bjhdsh.do?method=bjhdSh", "bjhdgl_bjhdsq.do?method=bjhdSq");
        }
        return result;
    }

    public boolean bjhdSqCancel(String sqid, String lcid) throws Exception {
        //只有刚提交并且第一级未审核的前提下，申请人可以撤销
        boolean result = shlc.firstStepCancle(sqid, lcid);
        if (result) {
            // 更新业务状态为'未提交'
            BjhdSqForm bjhdSqForm = new BjhdSqForm();
            bjhdSqForm.setSqid(sqid);
            bjhdSqForm.setSplc(lcid);
            // 查看是否有退回记录,有：审核状态就为退回
            ShlcDao shlcdao = new ShlcDao();
            if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
                bjhdSqForm.setShzt(Constants.YW_YTH);
            } else {
                bjhdSqForm.setShzt(Constants.YW_WTJ);
            }
            result = this.runUpdate(bjhdSqForm);
        }
        return result;
    }
}
