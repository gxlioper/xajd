package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjssq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-26 09:42
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GrxfjssqService extends SuperServiceImpl<GrxfjssqForm,GrxfjssqDao> {
    private ShlcInterface shlc = new CommShlcImpl();
    public static final String SUBMIT = "submit";
    public static final String SAVE = "save";

    /**
     * @描述:获取所有申报类型
     * @作者：lgx [工号：1553]
     * @日期：2018/6/26 10:12
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllSblx() {
        return dao.getAllSblx();
    }

    public boolean saveGrxfjssq(GrxfjssqForm model) throws Exception {
        if(model.getType().equals(SUBMIT)){
            model.setShzt(Constants.YW_SHZ);//审核中
        }else{
            model.setShzt(Constants.YW_WTJ);//未提交
        }
        // 获取审批流程
        String splc = dao.getShlcID();
        model.setSplc(splc);
        boolean insertResult = super.runInsert(model);
        if(SAVE.equalsIgnoreCase(model.getType())){
            return insertResult;
        }
        boolean result = false;
        if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
            //保存审核流程
            result = shlc.runSubmit(model.getSqid(),splc,model.getSqr(),"sxzzjy_grxfjs_grxfjssh.do","sxzzjy_grxfjs_grxfjssq.do");
        }
        return result;
    }

        public boolean updateGrxfjssq(GrxfjssqForm model) throws Exception {
        if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
            // 获取新审批流程
            model.setSplc(dao.getShlcID());
        }

        if(model.getType().equals(SUBMIT)){
            model.setShzt(Constants.YW_SHZ);//审核中
        }

        boolean insertResult = super.runUpdate(model);
        boolean result = true;
        if (insertResult && SUBMIT.equals(model.getType())) {
            shlc.deleteShjl(model.getSqid());
            result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"sxzzjy_grxfjs_grxfjssh.do","sxzzjy_grxfjs_grxfjssq.do");
        }
        return insertResult && result;
    }

    /**
     * @描述:提交
     * @作者：lgx [工号：1553]
     * @日期：2018/6/26 10:18
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: boolean
     */
    public boolean grxfjssqSubmit(GrxfjssqForm model) throws Exception {
        if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
            // 获取新审批流程
            model.setSplc(dao.getShlcID());
        }

        model.setShzt(Constants.YW_SHZ);
        boolean resultsq = dao.updateGrxfjssq(model);
        boolean result = false;
        if(resultsq){
            //保存审核流程
            result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"sxzzjy_grxfjs_grxfjssh.do","sxzzjy_grxfjs_grxfjssq.do");
        }
        return result;
    }

    /**
     * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
     * @作者：lgx [工号：1553]
     * @日期：2018/6/26 10:19
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [sqid, lcid]
     * @return: boolean
     */
    public boolean cancelRecord(String sqid, String lcid) throws Exception {
        return shlc.firstStepCancle(sqid,lcid);
    }

    public boolean grxfjssqCancel(GrxfjssqForm model) throws Exception {
        return dao.updateGrxfjssq(model);
    }

    /**
     * @描述:判断是否存在
     * @作者：lgx [工号：1553]
     * @日期：2018/6/26 10:20
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: boolean
     */
    public boolean isExist(GrxfjssqForm model) throws Exception {
        boolean flag = false;
        String num = dao.checkExistForSave(model);
        if (!"0".equalsIgnoreCase(num)) {
            flag = true;
        }
        return flag;
    }

    public List<HashMap<String,String>> getStuList(GrxfjssqForm model, User user) throws Exception {
        return dao.getStuList(model,user);
    }

    public HashMap<String,String> getGrxfjssqInfo(GrxfjssqForm model) {
        return dao.getGrxfjssqInfo(model);
    }
}
