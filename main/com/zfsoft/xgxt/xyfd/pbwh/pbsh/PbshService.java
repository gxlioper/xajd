package com.zfsoft.xgxt.xyfd.pbwh.pbsh;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xyfd.pbwh.pbjg.PbjgDao;
import com.zfsoft.xgxt.xyfd.pbwh.pbjg.PbjgForm;
import com.zfsoft.xgxt.xyfd.pbwh.pbjg.PbjgService;
import com.zfsoft.xgxt.xyfd.pbwh.pbsq.PbsqDao;
import com.zfsoft.xgxt.xyfd.pbwh.pbsq.PbsqForm;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by llf on 2019/7/31.
 */
public class PbshService extends SuperServiceImpl<PbshForm,PbshDao> {
    private ShlcInterface shlc = new CommShlcImpl();

    public boolean saveSh(PbshForm form, User user) {
        ShxxModel model = new ShxxModel();
        // 审核流程ID
        model.setShlc(form.getSplc());
        // 审核人
        model.setShr(user.getUserName());
        // 审核意见
        model.setShyj(form.getShyj());
        // 审核状态
        model.setShzt(form.getShjg());
        model.setThgw(form.getThgw());
        // 审核岗位ID
        model.setGwid(form.getGwid());
        // 业务ID(多为申请ID)
        model.setYwid(form.getSqid());
        model.setSqrid(form.getXh());
        model.setTzlj("xyfd_xyfd_pbsq.do");
        model.setTzljsq("xyfd_xyfd_pbsh.do");
        boolean reuslt = false;
        try {
            String zhzt = shlc.runAuditing(model);
            PbshForm pbshForm = new PbshForm();
            pbshForm.setSqid(form.getSqid());
            pbshForm.setShzt(zhzt);
            pbshForm.setYxgs(form.getYxgs());
            reuslt = dao.runUpdate(pbshForm, form.getSqid());
            // 保存到结果表
            if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
                PbjgForm jgForm = new PbjgForm();
                PbjgService jgService = new PbjgService();
                PbsqForm sqForm = new PbsqDao().getModel(form.getSqid());
                BeanUtils.copyProperties(jgForm, StringUtils.formatData(sqForm));

                String maxDjh = jgService.getDjh();
                int num = 0;
                String yearLast = new SimpleDateFormat("yy", Locale.CHINESE).format(Calendar.getInstance().getTime());
                if(org.apache.commons.lang.StringUtils.isNotEmpty(maxDjh)&&maxDjh.substring(2,4).equals(yearLast)){
                    num = Integer.parseInt(maxDjh.substring(2,maxDjh.length()))+1;
                }else {
                    num = Integer.parseInt(yearLast+"0001");
                }
                jgForm.setDjh("PB"+num);    //生成登记号

                jgForm.setLrr(user.getUserName());
                jgForm.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
                jgForm.setSqid(sqForm.getSqid());
                jgForm.setSjly("1");

                reuslt = jgService.delPbjgBySqId(jgForm.getSqid());//删除结果表sqid相同的数据
                if(reuslt)
                    reuslt = jgService.runInsert(jgForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reuslt;
    }

    public boolean cancel(PbshForm myForm) throws Exception {
        myForm.setShzt(Constants.YW_SHZ);
        boolean result = dao.runUpdate(myForm, myForm.getSqid());
        if (result) {
            PbjgDao jgDao = new PbjgDao();
            jgDao.delPbjgBySqId(myForm.getSqid());
        }
        return result;
    }

    public String savePlsh(PbshForm t, User user) throws Exception {
        PbshForm model = new PbshForm();
        String[] ids = t.getId();
        String[] gwid = t.getGwids();
        String[] xhs = t.getXhs();
        String[] splcs = t.getSplcs();
        List<String> failXhs = new ArrayList<String>();
        for (int i = 0, n = ids.length; i < n; i++) {
            model.setYwid(ids[i]);
            model.setGwid(gwid[i]);
            model.setSqid(ids[i]);
            model.setShyj(t.getShyj());
            model.setShjg(t.getShzt());
            model.setXh(xhs[i]);
            model.setSplc(splcs[i]);

            boolean isSuccess = saveSh(model, user);
            if (!isSuccess) {
                failXhs.add(xhs[i]);
            }
        }
        JSONArray json = JSONArray.fromObject(failXhs);
        if (failXhs.isEmpty()) {
            return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
        } else if (Constants.SH_TH.equals(t.getShzt())) {
            return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
        } else {
            return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
        }
    }
}
