package xsgzgl.gygl.sfqs.sfqskh.sfqskhsq;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.CollectionUtils;
import xgxt.form.User;
import xsgzgl.gygl.sfqs.sfqskh.sfqskhjg.SfqskhjgDao;
import xsgzgl.gygl.sfqs.sfqskh.sfqskhjg.SfqskhjgForm;
import xsgzgl.gygl.xjdfygl.cwxxgl.CwxxglDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class SfqskhsqService extends SuperServiceImpl<SfqskhsqForm,SfqskhsqDao>{
    private ShlcInterface shlc = new CommShlcImpl();
    private SfqskhjgDao sfqskhjgDao = new SfqskhjgDao();
    private CwxxglDao cwxxglDao = new CwxxglDao();

    public String getSplc(){
        return dao.getSplc();
    }
    public List<HashMap<String,String>> getShList(SfqskhsqForm t, User user) throws Exception {
        return dao.getShList(t,user);
    }
    public String yz(SfqskhsqForm t,String lx){
        List<QscyForm> qscy = t.getQscyxx();
        if(CollectionUtils.isEmpty(qscy)){
            return "该寝室没有入住信息，请确认！";
        }
        if(StringUtil.isNull(t.getLxfs())){
            return "请输入联系方式！";
        }
        if(StringUtil.isNull(t.getZysj())){
            return "请填写主要事迹！";
        }
        if(StringUtil.isNull(t.getHjqk())){
            return "请填写获奖情况！";
        }
        if(StringUtil.isNull(t.getFilepath())){
            return "请上传证明材料！";
        }
        if(!cwxxglDao.sfzjqs(t.getLddm(),t.getQsh(),t.getXh())){
            return "只能为自己的寝室申请！";
        }
        if(!dao.sfSfqs(t)){
            return "该寝室不在示范寝室名单中，请先创建示范寝室！";
        }
        if("add".equals(lx)){
            if(dao.isExist(t)){
                return "该寝室已有申请，请确认！";
            }
        }
        return "true";
    }
    public boolean sqSave(SfqskhsqForm t) throws Exception {

        if("submit".equals(t.getType())){
            t.setShzt(Constants.YW_SHZ);
        } else {
            t.setShzt(Constants.YW_WTJ);
        }
        String splc = dao.getSplc();
        if(StringUtil.isNull(splc)){
            return false;
        }
        t.setSplc(splc);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        t.setSqsj(sdf.format(new Date()));
        t.setSqid(UniqID.getInstance().getUniqIDHash());
        boolean f = dao.runInsert(t);

        if(f && "submit".equals(t.getType())){
            f = shlc.runSubmit(t.getSqid(), t.getSplc(), t.getXh(),
                    "gygl_sfqskh_sh.do",
                    "gygl_sfqskh_sq.do");
        }
        return f;
    }

    public boolean update(SfqskhsqForm model) throws Exception {

        if("submit".equals(model.getType())){
            model.setShzt(Constants.YW_SHZ);
        } else {
            model.setShzt(Constants.YW_WTJ);
        }
        boolean flag = dao.runUpdate(model);
        if(flag && "submit".equals(model.getType())){
            flag = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),
                    "gygl_sfqskh_sh.do",
                    "gygl_sfqskh_sq.do");
        }
        return flag;
    }

    public boolean submit(SfqskhsqForm model) throws Exception {
        return shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),
                "gygl_sfqskh_sh.do",
                "gygl_sfqskh_sq.do");
    }

    public boolean saveSh(SfqskhsqForm model, User user) throws Exception{

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
        shxxModel.setSqrid(model.getXh());
        shxxModel.setTzlj("gygl_sfqskh_sh.do");
        shxxModel.setTzljsq("gygl_sfqskh_sq.do");

        boolean result;
        String zhzt = shlc.runAuditing(shxxModel);
        model.setShzt(zhzt);
        result = dao.runUpdate(model);
        if(result && zhzt.equals(Constants.YW_TG)){
            SfqskhjgForm jgForm = new SfqskhjgForm();
            SfqskhsqForm data = getModel(model.getSqid());
            BeanUtils.copyProperties(jgForm,data);
            jgForm.setSjly("1");
            result = sfqskhjgDao.runInsert(jgForm);
        }
        return result;
    }
    public String plshBc(SfqskhsqForm t, User user) throws Exception {

        SfqskhsqForm model = new SfqskhsqForm();
        String[] sqids = t.getSqids();
        String[] gwid = t.getGwids();
        String[] sqrs = t.getSqrs();
        String[] splcids = t.getSplcs();
        List<String> failZghs = new ArrayList<String>();

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
    public boolean cancelSh(SfqskhsqForm model) throws Exception{
        model.setShzt(Constants.YW_SHZ);
        boolean flag = dao.runUpdate(model);
        if(flag){
            flag = dao.delJg(model.getSqid());
        }
        return flag;
    }
    public List<HashMap<String,String>> getQscyList(String lddm,String qsh,String xn){
        return dao.getQscyList(lddm,qsh,xn);
    }

    public HashMap<String,String> getMap(String id){
        return dao.getMap(id);
    }
}
