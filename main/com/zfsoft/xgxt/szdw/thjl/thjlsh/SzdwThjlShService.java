package com.zfsoft.xgxt.szdw.thjl.thjlsh;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.thjl.SzdwThjlDao;
import com.zfsoft.xgxt.szdw.thjl.SzdwThjlForm;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述：TODO
 * @作者：1601
 * @日期：
 */
public class SzdwThjlShService extends SuperServiceImpl<SzdwThjlShForm,SzdwThjlShDao> {
    private ShlcInterface shlc = new CommShlcImpl();
    private SzdwThjlDao jgDao = new SzdwThjlDao();
    public boolean saveSh(SzdwThjlShForm model, User user) throws Exception{

        ShxxModel shxxModel = new ShxxModel();
        // 审核流程ID
        shxxModel.setShlc(model.getSplc());
        // 审核人
        shxxModel.setShr(user.getUserName());
        // 审核意见
        shxxModel.setShyj(model.getShyj());
        // 审核状态
        shxxModel.setShzt(model.getShjg());
        // 审核岗位ID
        shxxModel.setGwid(model.getGwid());
        // 业务ID(多为申请ID)
        shxxModel.setYwid(model.getSqid());
        shxxModel.setSqrid(model.getXh());
        shxxModel.setTzlj("szdw_thjl_thjl_sh.do");
        shxxModel.setTzljsq("szdw_thjl_thjl_sq.do");

        boolean result;
        String zhzt = shlc.runAuditing(shxxModel);
        model.setShzt(zhzt);
        result = dao.runUpdate(model);
        if(result && zhzt.equals(Constants.YW_TG)){
            SzdwThjlForm jgForm = new SzdwThjlForm();
            SzdwThjlShForm data = getModel(model.getSqid());
            BeanUtils.copyProperties(jgForm,data);
            jgForm.setSjly("1");
            jgForm.setId(UniqID.getInstance().getUniqIDHash());
            result = jgDao.runInsert(jgForm);
        }
        return result;
    }
    public String plshBc(SzdwThjlShForm t, User user) throws Exception {

        SzdwThjlShForm model = new SzdwThjlShForm();
        String[] sqids = t.getIds();
        String[] gwid = t.getGwids();
        String[] sqrs = t.getSqrs();
        String[] splcids = t.getSplcids();
        List<String> failZghs = new ArrayList<String>();
        //要不要做验证有待研究

        for (int i = 0, n = sqids.length; i < n; i++) {
            model.setSplc(splcids[i]);
            model.setGwid(gwid[i]);
            model.setSqid(sqids[i]);
            model.setShyj(t.getShyj());
            model.setShjg(t.getShzt());
            model.setXh(sqrs[i]);
            boolean isSuccess = saveSh(model, user);
            if (!isSuccess) {
                failZghs.add(sqrs[i]);
            }
        }
        JSONArray json = JSONArray.fromObject(failZghs);
        if (failZghs.isEmpty()) {
            return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
        } else if (Constants.SH_TH.equals(t.getShzt())) {
            return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
        } else {
            return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
        }
    }
    public boolean cancelSh(SzdwThjlShForm model) throws Exception {
        model.setShzt(Constants.YW_SHZ);
        boolean flag = dao.runUpdate(model);
        if(flag){
            flag = dao.delJg(model.getSqid());
        }
        return flag;
    }
}
