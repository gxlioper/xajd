package com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhsh;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhjg.BzsbwhjgDao;
import com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhjg.BzsbwhjgForm;
import com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhjg.BzsbwhjgService;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @类功能描述:创新创业补助申报审核
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-06 10:38
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BzsbwhshService extends SuperServiceImpl<BzsbwhshForm,BzsbwhshDao> {
    private ShlcInterface shlc = new CommShlcImpl();

    public boolean saveSh(BzsbwhshForm form, User user) {
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
        model.setYwid(form.getSqid());
        model.setSqrid(form.getXh());
        model.setTzlj("cxcy_bzsbwh_sh.do");
        model.setTzljsq("cxcy_bzsbwh_sq.do");
        boolean reuslt = false;
        try {
            String zhzt = shlc.runAuditing(model);
            form.setShzt(zhzt);
            reuslt = dao.runUpdate(form, form.getSqid());
            // 保存到结果表
            if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
                BzsbwhshForm shForm = dao.getModel(form.getSqid());
                dao.deleteJg(shForm.getXn(),shForm.getXq(),shForm.getXh(),shForm.getXmmc());
                BzsbwhjgForm jgForm = new BzsbwhjgForm();
                BeanUtils.copyProperties(jgForm, StringUtils.formatData(shForm));
                jgForm.setSjly("1");
                jgForm.setLrr(shForm.getTbr());
                jgForm.setLrsj(shForm.getSqsj());
                jgForm.setLcywid(form.getSqid());
                reuslt = new BzsbwhjgService().runInsert(jgForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reuslt;
    }

    /**
     * @描述:批量审核保存
     * @作者：lgx [工号：1553]
     * @日期：2018/9/6 11:02
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [t, user]
     * @return: java.lang.String
     */
    public String savePlsh(BzsbwhshForm t, User user) throws Exception {
        BzsbwhshForm model = new BzsbwhshForm();
        String[] sqids = t.getIds();
        String[] gwid = t.getGwids();
        String[] xhs = t.getXhs();
        String[] splcs = t.getSplcs();
        List<String> failXhs = new ArrayList<String>();

        for (int i = 0, n = sqids.length; i < n; i++) {
            model.setSplc(t.getSplc());
            model.setGwid(gwid[i]);
            model.setSqid(sqids[i]);
            model.setShyj(t.getShyj());
            model.setShjg(t.getShzt());
            model.setXh(xhs[i]);
            model.setXn(t.getXn());
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

    /**
     * @描述:撤销
     * @作者：lgx [工号：1553]
     * @日期：2018/9/6 11:01
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [myForm]
     * @return: boolean
     */
    public boolean cancel(BzsbwhshForm myForm) throws Exception {
        myForm.setShzt(Constants.YW_SHZ);
        boolean result = dao.runUpdate(myForm, myForm.getSqid());
        if (result) {
            BzsbwhjgDao jgdao = new BzsbwhjgDao();
            // 删除结果表中的申请结果
            jgdao.deleteByLcywid(myForm.getSqid());
        }
        return result;
    }

   /**
    * @描述:撤销
    * @作者：lgx [工号：1553]
    * @日期：2018/9/6 11:01
    * @修改记录: 修改者名字-修改日期-修改内容
    * @param: [ywid, model, user]
    * @return: java.lang.String
    */
    public String cxshnew(String ywid, BzsbwhshForm model, User user) throws Exception {
        ShlcInterface service = new CommShlcImpl();
        String shzt = Constants.YW_SHZ;
        model.setSqid(ywid);
        model.setShzt(shzt);
        String cancelFlag = service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
        dao.runUpdate(model, ywid);
        return cancelFlag;
    }
}
