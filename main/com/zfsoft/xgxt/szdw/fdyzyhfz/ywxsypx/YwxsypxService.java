package com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypx;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypxJg.PxJgDao;
import com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypxJg.PxJgForm;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;
import xsgzgl.qgzx.gwglnew.QgzxGwglForm;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class YwxsypxService extends SuperServiceImpl<YwxsypxForm,YwxsypxDao>{
    private ShlcInterface shlc = new CommShlcImpl();
    private PxJgDao pxJgDao = new PxJgDao();

    public boolean cssz(String splc) throws Exception {
        return dao.cssz(splc);
    }
    public String getSplc(){
        return dao.getSplc();
    }
    public List<HashMap<String,String>> getAllBmList(){
        return dao.getAllBmList();
    }

    public boolean save(YwxsypxForm model) throws Exception {
        model.setSqid(UniqID.getInstance().getUniqIDHash());
        model.setSplc(dao.getSplc());
        boolean flag = dao.runInsert(model);
        if(flag && "submit".equals(model.getType())){
            flag = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getZgh(),"szdw_fdy_ywxxypx_sh.do","szdw_fdy_ywxxypx_sq.do");
        }
        return flag;
    }

    public boolean update(YwxsypxForm model) throws Exception {
        boolean flag = dao.runUpdate(model);
        if(flag && "submit".equals(model.getType())){
            flag = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getZgh(),"szdw_fdy_ywxxypx_sh.do","szdw_fdy_ywxxypx_sq.do");
        }
        return flag;
    }
    public List<HashMap<String,String>> getShList(YwxsypxForm t, User user) throws Exception {
        return dao.getShList(t,user);
    }

    public boolean saveSh(YwxsypxForm model, User user) throws Exception{

        ShxxModel shxxModel = new ShxxModel();
        // 审核流程ID
        shxxModel.setShlc(model.getSplc());
        // 审核人
        shxxModel.setShr(user.getUserName());
        // 审核意见
        shxxModel.setShyj(model.getShyj());
        // 审核状态
        shxxModel.setShzt(model.getShjg());
//        shxxModel.setThgw(model.getThgw());
        // 审核岗位ID
        shxxModel.setGwid(model.getGwid());
        // 业务ID(多为申请ID)
        shxxModel.setYwid(model.getSqid());
        shxxModel.setSqrid(model.getZgh());
        shxxModel.setTzlj("szdw_fdy_ywxxypx_sh.do");
        shxxModel.setTzljsq("szdw_fdy_ywxxypx_sq.do");

        boolean result;
        String zhzt = shlc.runAuditing(shxxModel);
        model.setShzt(zhzt);
        result = dao.runUpdate(model);
        if(result && zhzt.equals(Constants.YW_TG)){
            PxJgForm jgForm = new PxJgForm();
            YwxsypxForm data = getModel(model.getSqid());
            BeanUtils.copyProperties(jgForm,data);
            jgForm.setSjly("1");
            result = pxJgDao.runInsert(jgForm);
        }
        return result;
    }
    public String plshBc(YwxsypxForm t, User user) throws Exception {

        YwxsypxForm model = new YwxsypxForm();
        String[] sqids = t.getId();
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
            model.setZgh(sqrs[i]);
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
    public boolean cancelSh(YwxsypxForm model) throws Exception{
        model.setShzt(Constants.YW_SHZ);
        boolean flag = dao.runUpdate(model);
        if(flag){
            flag = dao.delJg(model.getSqid());
        }
        return flag;
    }
}
