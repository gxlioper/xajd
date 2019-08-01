package com.zfsoft.xgxt.xyfd.pbwh.pbsq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsModel;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/7/30.
 */
public class PbsqService extends SuperServiceImpl<PbsqForm,PbsqDao> {
    private ShlcInterface shlc = new CommShlcImpl();

    /**
     * 获取专业排名
     * @param t
     * @param user
     * @return
     * @throws Exception
     */
    public HashMap<String,String> getZypmlist(PbsqForm t,User user) throws Exception{
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        String xn = "";
        if(month>=7){
            xn = (year-1)+"-"+year;
        }else {
            xn = (year-2)+"-"+(year-1);
        }
        SearchModel searchModel = new SearchModel();
        searchModel.setPath("pj_jgcx.do");
        searchModel.setInput_mhcx(t.getXh());
        searchModel.setMhcx_lx("all");
        searchModel.setSearch_tj_xn(new String[]{xn});
        ZcfsModel zcfsForm = new ZcfsModel();
        zcfsForm.setSearchModel(searchModel);
        zcfsForm.setPmfs("njzypm");
        zcfsForm.setZczq("xn");
        ZcfsService zcfsService = new ZcfsService();
        List<HashMap<String,String>> zypmlist = zcfsService.getZcfjgList(zcfsForm,user);

        return zypmlist.size()>0?zypmlist.get(0):new HashMap<>();
    }

    /**
     * 获取奖、助学金与表彰奖励
     * @param t
     * @return
     * @throws Exception
     */
    public List<HashMap<String,String>> getJlxx(PbsqForm t) throws Exception{
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        String xn = "";
        if(month>=7){
            xn = (year-1)+"-"+year;
        }else {
            xn = (year-2)+"-"+(year-1);
        }
        return dao.getJlxx(t,xn);
    }

    public HashMap<String,String> getXsxx(PbsqForm t) throws Exception{
        return dao.getXsxx(t);
    }

    public boolean savePbsq(PbsqForm model) throws Exception {
        String sqid = UniqID.getInstance().getUniqIDHash();
        model.setSqid(sqid);
        String splc = dao.getShlcID("pb");//获取朋辈志愿者申请审批流程
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
                result = shlc.runSubmit(sqid, splc, sqid, "xyfd_xyfd_pbsh.do", "xyfd_xyfd_pbsq.do");
            }
        }
        return result;
    }

    public boolean saveUpdate( PbsqForm model) throws Exception {
        boolean result = false;
        // 如果提交，插入审核状态
        if ("submit".equalsIgnoreCase(model.getType())) {
            model.setShzt(Constants.YW_SHZ);// 审核中
            String splc = dao.getShlcID("pb");
            model.setSplc(splc);
            result = runUpdate(model);
            ShlcInterface shlc = new CommShlcImpl();
            if (result) {
                result = shlc.runSubmit(model.getSqid(), splc, model.getSqid(), "xyfd_xyfd_pbsh.do", "xyfd_xyfd_pbsq.do");
            }
        } else {
            result = runUpdate(model);
        }
        return result;

    }

    public HashMap<String,String> getPbxx(PbsqForm t) throws Exception{
        return dao.getPbxx(t);
    }

    /**
     * 提交
     * @param model
     * @return
     * @throws Exception
     */
    public boolean submit(PbsqForm model) throws Exception {
        boolean result = false;
        model.setShzt(Constants.YW_SHZ);// 审核中
        String splc = dao.getShlcID("pb");
        model.setSplc(splc);
        result = runUpdate(model);
        ShlcInterface shlc = new CommShlcImpl();
        if (result) {
            result = shlc.runSubmit(model.getSqid(), splc, model.getSqid(), "xyfd_xyfd_pbsh.do", "xyfd_xyfd_pbsq.do");
        }
        return result;
    }

    public boolean cancelRecord(String ywid, String lcid) throws Exception {
        return shlc.firstStepCancle(ywid, lcid);
    }
}
