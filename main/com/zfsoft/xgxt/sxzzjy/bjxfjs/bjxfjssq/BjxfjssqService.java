package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjssq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:班级学风建设申请
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-20 14:36
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxfjssqService extends SuperServiceImpl<BjxfjssqForm,BjxfjssqDao>{
    public static final String SUBMIT = "submit";
    public static final String SAVE = "save";
    private ShlcInterface shlc = new CommShlcImpl();

    /**
     * @描述:查询所有上报类型
     * @作者：lgx [工号：1553]
     * @日期：2018/6/22 9:48
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllSblx() {
        return dao.getAllSblx();
    }

    /**
     * @描述:获取班级列表
     * @作者：lgx [工号：1553]
     * @日期：2018/6/22 9:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model, user]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getBjList(BjxfjssqForm model, User user) throws Exception {
        return dao.getBjList(model,user);
    }

    /**
     * @描述:获取班级信息
     * @作者：lgx [工号：1553]
     * @日期：2018/6/22 9:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [bjdm]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getBjInfo(String bjdm) {
        return dao.getBjInfo(bjdm);
    }

    public boolean saveBjxfjssq(BjxfjssqForm model) throws Exception {
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
            result = shlc.runSubmit(model.getSqid(),splc,model.getSqr(),"sxzzjy_bjxfjs_bjxfjssh.do","sxzzjy_bjxfjs_bjxfjssq.do");
        }
        return result;
    }

    public boolean updateYlbxsq(BjxfjssqForm model) throws Exception {
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
            result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"sxzzjy_bjxfjs_bjxfjssh.do","sxzzjy_bjxfjs_bjxfjssq.do");
        }
        return insertResult && result;
    }

    /**
     * @描述:提交
     * @作者：lgx [工号：1553]
     * @日期：2018/6/22 14:34
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: boolean
     */
    public boolean bjxfjssqSubmit(BjxfjssqForm model) throws Exception {
        if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
            // 获取新审批流程
            model.setSplc(dao.getShlcID());
        }

        model.setShzt(Constants.YW_SHZ);
        boolean resultYlbxsq = dao.updateBjxfjssq(model);
        boolean result = false;
        if(resultYlbxsq){
            //保存审核流程
            result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"sxzzjy_bjxfjs_bjxfjssh.do","sxzzjy_bjxfjs_bjxfjssq.do");
        }
        return result;
    }

    public HashMap<String,String> getBjxfjssqInfo(BjxfjssqForm model) {
        return dao.getBjxfjssqInfo( model);
    }

    /**
     * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
     * @作者：lgx [工号：1553]
     * @日期：2018/6/22 15:24
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [sqid, lcid]
     * @return: boolean
     */
    public boolean cancelRecord(String sqid, String lcid) throws Exception {
        return shlc.firstStepCancle(sqid,lcid);
    }

    public boolean bjxfjssqCancel(BjxfjssqForm model) throws Exception {
        return dao.updateBjxfjssq(model);
    }

    /**
     * @描述:判断是否存在
     * @作者：lgx [工号：1553]
     * @日期：2018/6/22 9:50
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: boolean
     */
    public boolean isExist(BjxfjssqForm model) throws Exception {
        boolean flag = false;
        String num = dao.checkExistForSave(model);
        if (!"0".equalsIgnoreCase(num)) {
            flag = true;
        }
        return flag;
    }
}
