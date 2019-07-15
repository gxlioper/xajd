package com.zfsoft.xgxt.dtjs.llxxjl.sq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

import java.util.HashMap;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2019-03-01 09:22
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class LlxxjlsqService extends SuperServiceImpl<LlxxjlsqForm, LlxxjlsqDao> {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private ShlcInterface shlc = new CommShlcImpl();

    public boolean saveSthdsq(LlxxjlsqForm model) throws Exception {
        String sqid = UniqID.getInstance().getUniqIDHash();
        model.setSqid(sqid);
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
        if (!"save".equals(model.getType())) {
            if (result) {
                result = shlc.runSubmit(sqid, splc, sqid, "dtjs_llxxjl_sh.do", "dtjs_llxxjl_sq.do");
            }
        }
        return true;
    }

    public boolean saveUpdate( LlxxjlsqForm model) throws Exception {
        boolean result = false;
        // 如果提交，插入审核状态
        if ("submit".equalsIgnoreCase(model.getType())) {
            model.setShzt(Constants.YW_SHZ);// 审核中
            String splc = dao.getShlcID();
            model.setSplc(splc);
            result = runUpdate(model);
            ShlcInterface shlc = new CommShlcImpl();
            if (result) {
                result = shlc.runSubmit(model.getSqid(), splc, model.getSqid(), "dtjs_llxxjl_sh.do", "dtjs_llxxjl_sq.do");
            }
        } else {
            result = runUpdate(model);
        }
        return result;

    }

    public boolean submit(LlxxjlsqForm model) throws Exception {
        boolean result = false;
        model.setShzt(Constants.YW_SHZ);// 审核中
        String splc = dao.getShlcID();
        model.setSplc(splc);
        result = runUpdate(model);
        ShlcInterface shlc = new CommShlcImpl();
        if (result) {
            result = shlc.runSubmit(model.getSqid(), splc, model.getSqid(), "dtjs_llxxjl_sh.do", "dtjs_llxxjl_sq.do");
        }
        return result;
    }
    /**
     * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
     * @作者：lgx [工号：1553]
     * @日期：2019/3/1 11:47
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [ywid, lcid]
     * @return: boolean
     */
    public boolean cancelRecord(String ywid, String lcid) throws Exception {
        return shlc.firstStepCancle(ywid, lcid);
    }

    public boolean checkEdit(LlxxjlsqForm model) throws Exception {
        String num = dao.checkEdit(model);
        return "0".equals(num);
    }

    public HashMap<String,String> getInfo(String sqid) {
        return dao.getInfo(sqid);
    }
}
