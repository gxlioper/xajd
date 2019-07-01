package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

import java.util.HashMap;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-08-02 17:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GrxfjshbService extends SuperServiceImpl<GrxfjshbForm,GrxfjshbDao> {
    public static final String SUBMIT = "submit";
    public static final String SAVE = "save";
    private ShlcInterface shlc = new CommShlcImpl();

    /**
     * @描述:验证重复
     * @作者：lgx [工号：1553]
     * @日期：2018/7/25 14:52
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: boolean
     */
    public boolean isExist(GrxfjshbForm model) throws Exception {
        boolean flag = false;
        String num = dao.checkExistForSave(model);
        if (!"0".equalsIgnoreCase(num)) {
            flag = true;
        }
        return flag;
    }

    public boolean saveGrxfjshb(GrxfjshbForm model) throws Exception {
        if(model.getType().equals(SUBMIT)){
            model.setShzt(Constants.YW_SHZ);//审核中
        }else{
            model.setShzt(Constants.YW_WTJ);//未提交
        }
        // 获取审批流程
        String splc = dao.getShlcID(model.getHblx());
        model.setSplc(splc);
        boolean insertResult = super.runInsert(model);
        if(SAVE.equalsIgnoreCase(model.getType())){
            return insertResult;
        }
        boolean result = false;
        if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
            //保存审核流程
            result = shlc.runSubmit(model.getSqid(),splc,model.getSqr(),"sxzzjy_grxfjs_grxfjssh.do","sxzzjy_grxfjs_grxfjshb.do");
        }
        return result;
    }

    public boolean updateYlbxsq(GrxfjshbForm model) throws Exception {
        if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
            // 获取新审批流程
            model.setSplc(dao.getShlcID(model.getHblx()));
        }

        if(model.getType().equals(SUBMIT)){
            model.setShzt(Constants.YW_SHZ);//审核中
        }

        boolean insertResult = super.runUpdate(model);
        boolean result = true;
        if (insertResult && SUBMIT.equals(model.getType())) {
            shlc.deleteShjl(model.getSqid());
            result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"sxzzjy_grxfjs_grxfjssh.do","sxzzjy_grxfjs_grxfjshb.do");
        }
        return insertResult && result;
    }

    /**
     * @描述:提交
     * @作者：lgx [工号：1553]
     * @日期：2018/7/27 10:19
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: boolean
     */
    public boolean grxfjshbSubmit(GrxfjshbForm model) throws Exception {
        if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
            // 获取新审批流程
            model.setSplc(dao.getShlcID(model.getHblx()));
        }

        model.setShzt(Constants.YW_SHZ);
        boolean resultYlbxsq = dao.updateGrxfjshb(model);
        boolean result = false;
        if(resultYlbxsq){
            //保存审核流程
            result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"sxzzjy_grxfjs_grxfjssh.do","sxzzjy_grxfjs_grxfjssq.do");
        }
        return result;
    }
    public int grxfjshbDel(String[] jgids, String[] hblxs) throws Exception {
        return dao.grxfjshbDel(jgids,hblxs);
    }

    /**
     * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
     * @作者：lgx [工号：1553]
     * @日期：2018/7/27 10:20
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [sqid, lcid]
     * @return: boolean
     */
    public boolean cancelRecord(String sqid, String lcid) throws Exception {
        return shlc.firstStepCancle(sqid,lcid);
    }
    public boolean grxfjshbCancel(GrxfjshbForm model) throws Exception {
        return dao.updateGrxfjshb(model);
    }

    public HashMap<String,String> getInfo(GrxfjshbForm model) {
        return dao.getInfo(model);
    }
    /**
     * @描述:打印信息
     * @作者：lgx [工号：1553]
     * @日期：2018/8/2 14:44
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [id, hblx]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getWordInfo(String id,String hblx) {
        return dao.getWordInfo(id,hblx);
    }
}
