package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjssh;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb.BjxfjshbDao;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb.BjxfjshbForm;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb.BjxfjshbjgDao;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb.BjxfjshbjgForm;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjg.BjxfjsjgDao;
import com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjg.BjxfjsjgForm;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-22 15:33
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class BjxfjsshService extends SuperServiceImpl<BjxfjsshForm,BjxfjsshDao> {

    private ShlcInterface shlc = new CommShlcImpl();
    /**
     * @描述:审核过程判断结果库中有无数据
     * @作者：lgx [工号：1553]
     * @日期：2018/6/22 17:11
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [model]
     * @return: java.lang.String
     */
    public String checkExistForSave(BjxfjsshForm model) {
        return dao.checkExistForSave(model);
    }

    public boolean saveSh(BjxfjsshForm form,User user){
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
        // ======= 业务字段 begin======
        model.setSqrid(form.getSqr());
        // ======= 业务字段 end======
        model.setTzlj("sxzzjy_bjxfjs_bjxfjssh.do");
        if("sq".equals(form.getSqlx())){
            model.setTzljsq("sxzzjy_bjxfjs_bjxfjssq.do");
        }else{
            model.setTzljsq("sxzzjy_bjxfjs_bjxfjshb.do");
        }

        boolean reuslt = false;
        try {
            String zhzt = shlc.runAuditing(model);
            if("sq".equals(form.getSqlx())){
                BjxfjsshForm upForm = new BjxfjsshForm();
                upForm.setSqid(form.getSqid());
                upForm.setShzt(zhzt);
                reuslt = dao.runUpdate(upForm, form.getSqid());
                //审核状态为通过
                if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
                    BjxfjsjgForm bjxfjsjgForm = new BjxfjsjgForm();
                    form = getModel(form);
                    BeanUtils.copyProperties(bjxfjsjgForm, StringUtils.formatData(form));
                    bjxfjsjgForm.setSjly("1");
                    bjxfjsjgForm.setLcywid(form.getSqid());
                    BjxfjsjgDao bjxfjsjgDao = new BjxfjsjgDao();
                    boolean flag = bjxfjsjgDao.deleteJg(bjxfjsjgForm);
                    if(flag)
                        bjxfjsjgDao.runInsert(bjxfjsjgForm);

                }
            }else{
                BjxfjshbForm upForm = new BjxfjshbForm();
                upForm.setSqid(form.getSqid());
                upForm.setShzt(zhzt);
                BjxfjshbDao bjxfjshbDao = new BjxfjshbDao();
                reuslt = bjxfjshbDao.runUpdate(upForm, form.getSqid());
                //审核状态为通过
                if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
                    BjxfjshbjgForm bjxfjshbjgForm = new BjxfjshbjgForm();
                    upForm = bjxfjshbDao.getModel(form.getSqid());
                    BeanUtils.copyProperties(bjxfjshbjgForm, StringUtils.formatData(upForm));
                    bjxfjshbjgForm.setSjly("1");
                    bjxfjshbjgForm.setLcywid(form.getSqid());
                    BjxfjshbjgDao bjxfjshbjgDao = new BjxfjshbjgDao();
                    boolean flag = bjxfjshbjgDao.delete(bjxfjshbjgForm);
                    if(flag)
                        bjxfjshbjgDao.runInsert(bjxfjshbjgForm);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reuslt;
    }

    /**
     * @描述:批量审核保存
     * @param model
     * @param user
     * @return
     * String 返回类型
     * @throws
     */
    public String savePlsh(BjxfjsshForm t, User user) {
        String[] ids = t.getId();
        String[] splcs = t.getSplcs();
        String[] bjmcs = t.getBjdms();
        String[] gwids = t.getGwids();
        String[] sqlxs = t.getSqlxs();
        List<String> fails = new ArrayList<String>();
        for (int i = 0, n = ids.length; i < n; i++) {
            BjxfjsshForm model = new BjxfjsshForm();
            model.setSqid(ids[i]);
            model.setSplc(splcs[i]);
            model.setShyj(t.getShyj());
            model.setShjg(t.getShzt());
            model.setGwid(gwids[i]);
            model.setSqlx(sqlxs[i]);

            boolean isSuccess = saveSh(model, user);
            if (!isSuccess) {
                fails.add(bjmcs[i]);
            }
        }
        JSONArray json = JSONArray.fromObject(fails);
        if (fails.isEmpty()) {
            return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
        } else if (Constants.SH_TH.equals(t.getShzt())) {
            return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
        } else {
            return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
        }
    }

    /**
     * @描述:撤销审核
     * @param model
     * @return
     * boolean 返回类型
     * @throws
     */
    public boolean newCancelSh(BjxfjsshForm model, User user){
        boolean result = false;
        try {

            //更新申请表
            if("sq".equals(model.getSqlx())){
                model = getModel(model);
                result = dao.updateBjxfjssq(model.getSqid(), Constants.YW_SHZ);
                if(result && Constants.YW_TG.equals(model.getShzt())){
                    //删除结果表
                   result = dao.deleteBjxfjsjg(model.getSqid());
                }
            }else{
                BjxfjshbDao bjxfjshbDao = new BjxfjshbDao();
                BjxfjshbForm bjxfjshbForm = bjxfjshbDao.getModel(model.getSqid());
                result = bjxfjshbDao.updateSq(model.getSqid(), Constants.YW_SHZ);
                if(result && Constants.YW_TG.equals(bjxfjshbForm.getShzt())){
                    //删除结果表
                    BjxfjshbjgDao bjxfjshbjgDao = new BjxfjshbjgDao();
                    result = bjxfjshbjgDao.deleteBjxfjshbjg(model.getSqid());
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
