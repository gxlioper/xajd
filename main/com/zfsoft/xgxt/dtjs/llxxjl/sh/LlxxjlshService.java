package com.zfsoft.xgxt.dtjs.llxxjl.sh;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgDao;
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgForm;
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgService;
import com.zfsoft.xgxt.dtjs.llxxjl.sq.LlxxjlsqDao;
import com.zfsoft.xgxt.dtjs.llxxjl.sq.LlxxjlsqForm;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： llf [工号:1754]
 * @时间： 2019-07-15 14:36
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class LlxxjlshService extends SuperServiceImpl<LlxxjlshForm, LlxxjlshDao> {
    private ShlcInterface shlc = new CommShlcImpl();

    public HashMap<String, String> getInfo(String sqid) {
        return dao.getInfo(sqid);

    }

    public boolean saveSh(LlxxjlshForm form, User user) {
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
        model.setTzlj("dtjs_llxxjl_sq.do");
        model.setTzljsq("dtjs_llxxjl_sh.do");
        boolean reuslt = false;
        try {
            String zhzt = shlc.runAuditing(model);
            LlxxjlshForm hdshForm = new LlxxjlshForm();
            hdshForm.setSqid(form.getSqid());
            hdshForm.setShzt(zhzt);
            hdshForm.setYxgs(form.getYxgs());
            reuslt = dao.runUpdate(hdshForm, form.getSqid());
            // 保存到结果表
            if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
                LlxxjljgForm jgForm = new LlxxjljgForm();
                LlxxjljgService jgService = new LlxxjljgService();
                LlxxjlsqForm sqForm = new LlxxjlsqDao().getModel(form.getSqid());
                BeanUtils.copyProperties(jgForm, StringUtils.formatData(sqForm));
                jgForm.setLrr(user.getUserName());
                jgForm.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
                jgForm.setSqid(sqForm.getSqid());
                jgForm.setSjly("1");
                //申请的数据插入结果表，若时间重复，则直接覆盖
                reuslt = jgService.delBySj(jgForm);
                if(reuslt)
                    reuslt = jgService.runInsert(jgForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reuslt;
    }

    /**
     * @描述:批量审核
     * @作者：lgx [工号：1553]
     * @日期：2019/3/1 15:24
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [t, user]
     * @return: java.lang.String
     */
    public String savePlsh(LlxxjlshForm t, User user) throws Exception {
        LlxxjlshForm model = new LlxxjlshForm();
        String[] ids = t.getId();
        String[] gwid = t.getGwids();
        String[] xhs = t.getXhs();
        List<String> failXhs = new ArrayList<String>();
        for (int i = 0, n = ids.length; i < n; i++) {
            model.setSplc(t.getSplc());
            model.setYwid(ids[i]);
            model.setGwid(gwid[i]);
            model.setSqid(ids[i]);
            model.setShyj(t.getShyj());
            model.setShjg(t.getShzt());
            model.setXh(xhs[i]);

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


    /**
     *
     * @描述:最后一级撤销
     * @作者：xiaxia[工号：1104]
     * @日期：2015-7-28 下午03:22:51
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param myForm
     * @return
     * @throws Exception boolean 返回类型
     * @throws
     */
    public boolean cancel(LlxxjlshForm myForm) throws Exception {
        myForm.setShzt(Constants.YW_SHZ);
        boolean result = dao.runUpdate(myForm, myForm.getSqid());
        if (result) {
            LlxxjljgDao jgDao = new LlxxjljgDao();
            jgDao.delSthdjgBySqId(myForm.getSqid());
        }
        return result;
    }
}
