package com.zfsoft.xgxt.xszz.lstd;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszForm;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszService;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述：绿色通道service
 * @作者：WANCHEN
 * @日期：2018-09-25
 */
public class LstdService extends SuperServiceImpl<LstdForm,LstdDao> {
//    JcszService jcszService = new JcszService();
    private ShlcInterface shlc = new CommShlcImpl();
    private LstdjgDao lstdjgDao = new LstdjgDao();

    public boolean saveLstdSq(LstdForm model) throws Exception {
        String sqid = UniqID.getInstance().getUniqIDHash();
        model.setSqid(sqid);
        Map<String,String> map = getJcsz();
        model.setXn(Base.currXn);
        model.setXq(Base.currXq);
        if (!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())&&"submit".equalsIgnoreCase(model.getType())&&map != null && !StringUtil.isNull(map.get("splc"))) {
            // 设置审批流程到申请记录上
            model.setShlc(map.get("splc"));
        }

        //新增和修改都用这个方法,若审核流程为空，这里重新分配
        if(StringUtil.isNull(model.getShlc())){

            model.setShlc(map.get("splc"));
        }

        // 设置申请时间为当前系统时间,默认审核状态为“未审核”
        model.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));

        if(model.getType().equals("submit")){
            model.setShzt(Constants.YW_SHZ);
        }else{
            model.setShzt(Constants.YW_WTJ);
        }
        boolean isSuccess = dao.runInsert(model);
        if(model.getType().equals("submit") && isSuccess){

            isSuccess = shlc.runSubmit(sqid, model.getShlc(), model.getXh(), "xszz_lstdsh.do", "xszz_lstdsq.do");
        }

        return isSuccess;
    }
    public boolean saveLstdJg(LstdForm model) throws Exception {
        LstdjgForm lstdjgForm = new LstdjgForm();
        BeanUtils.copyProperties(lstdjgForm, StringUtils.formatData(model));
        lstdjgForm.setXn(Base.currXn);
        lstdjgForm.setXq(Base.currXq);
        lstdjgDao.deleteExist(lstdjgForm);
        return lstdjgDao.runInsert(lstdjgForm);
    }

    public boolean updateLstdsq(LstdForm model) throws Exception {
        if(model.getType().equals("submit")){
            model.setShzt(Constants.YW_SHZ);
        }else{
            model.setShzt(Constants.YW_WTJ);
        }
        boolean result = dao.runUpdate(model);
        if("submit".equals(model.getType()) && result){
            result = shlc.runSubmit(model.getSqid(), model.getShlc(), model.getXh(), "xszz_lstdsh.do", "xszz_lstdsq.do");
        }
        return result;
    }

    public boolean isExist(LstdForm model)throws Exception{
        model.setXn(Base.currXn);
        model.setXq(Base.currXq);
        return !"0".equals(dao.isExist(model));
    }

    public List<HashMap<String,String>> getShList(LstdForm model,User user) throws Exception{
        return dao.getShList(model,user);
    }

    public boolean saveSh(LstdForm model,User user) throws Exception{

        ShxxModel shxxModel = new ShxxModel();
        // 审核流程ID
        shxxModel.setShlc(model.getShlc());
        // 审核人
        shxxModel.setShr(user.getUserName());
        // 审核意见
        shxxModel.setShyj(model.getShyj());
        // 审核状态
        shxxModel.setShzt(model.getShzt());
//        shxxModel.setThgw(model.getThgw());
        // 审核岗位ID
        shxxModel.setGwid(model.getGwid());
        // 业务ID(多为申请ID)
        shxxModel.setYwid(model.getSqid());
        shxxModel.setSqrid(model.getXh());
        shxxModel.setTzlj("xszz_lstdsh.do");
        shxxModel.setTzljsq("xszz_lstdsq.do");

        boolean result = false;
        String zhzt = shlc.runAuditing(shxxModel);
        LstdForm lstdForm = new LstdForm();
        lstdForm.setSqid(model.getSqid());
        lstdForm.setShzt(zhzt);
        result = dao.runUpdate(lstdForm,model.getSqid());
        //审核状态为通过的往结果表中保存该条数据
        if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
            LstdjgForm lstdjgForm = new LstdjgForm();
            model = getModel(model);
            BeanUtils.copyProperties(lstdjgForm, StringUtils.formatData(model));
            lstdjgForm.setSjly("1");
            lstdjgForm.setSqid(model.getSqid());
            lstdjgDao.deleteExist(lstdjgForm);
            lstdjgDao.runInsert(lstdjgForm);
        }
        return result;
    }

    public String savePlsh(LstdForm t,User user) throws Exception {
        String[] ids = t.getId();
        String[] gwid = t.getGwids();
        String[] xhs = t.getXhs();
        String[] shlcs = t.getShlcs();

        List<String> failXhs = new ArrayList<String>();
        for (int i = 0, n = ids.length; i < n; i++) {
            LstdForm model = new LstdForm();
            model.setShlc(shlcs[i]);
            model.setSqid(ids[i]);
            model.setGwid(gwid[i]);
            model.setSqid(ids[i]);
            model.setShyj(t.getShyj());
            model.setShzt(t.getShzt());
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

    public boolean cancelLstdsq(String ywid, String lcid) throws Exception {
        return shlc.firstStepCancle(ywid,lcid);
    }
    public boolean updateCxLstdsq(LstdForm model) throws Exception {
        return dao.updateLstdsq(model);
    }

    public boolean CancelSh(LstdForm model) {
        boolean resultsq = false;
        boolean resultjg = false;
        try {
            resultsq = dao.updateLstdsqxx(model.getSqid(), Constants.YW_SHZ);
            if(resultsq){
                String shzt = model.getShzt();
                if(shzt != null && shzt.equals("2")){
                    resultjg = true;
                }else{
                    resultjg = dao.deleteLstdxxjg(model.getSqid());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultjg;
    }

    public boolean submitLstd(LstdForm model) throws Exception {
        if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
            model.setShlc(getJcsz().get("splc"));
        }
        model.setShzt(Constants.YW_SHZ);
        boolean flag = dao.updateLstdsq(model);
        boolean result = false;
        if(flag){
            result = shlc.runSubmit(model.getSqid(), model.getShlc(),model.getXh(),"xszz_lstdsh.do","xszz_lstdsq.do");
        }
        return result;
    }

    public List<HashMap<String,String>> getJgList(LstdForm model,User user) throws Exception {
        return dao.getJgPageList(model,user);
    }

    public boolean saveJcsz(LstdForm model) throws Exception {
        if(dao.delJcsz()){
            return dao.insertJcsz(model);
        }
        return false;
    }
    public Map<String,String> getJcsz(){
        return dao.getJcsz();
    }
    public String isOpean(){
        return dao.isOpean();
    }

    public int deleteJg(String[] ids) throws Exception {
        return lstdjgDao.runDelete(ids);
    }
}
