package xsgzgl.gygl.xjdgybz.ktsq;

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
import xsgzgl.gygl.xjdfygl.cwxxgl.CwxxglDao;
import xsgzgl.gygl.xjdfygl.cwxxgl.CwxxglService;
import xsgzgl.gygl.xjdgybz.ktsqjg.KtsqjgDao;
import xsgzgl.gygl.xjdgybz.ktsqjg.KtsqjgForm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class KtsqService extends SuperServiceImpl<KtsqForm,KtsqDao> {
    private ShlcInterface shlc = new CommShlcImpl();
    private KtsqjgDao ktsqjgDao = new KtsqjgDao();
    private CwxxglDao cwxxglDao = new CwxxglDao();
    public List<HashMap<String,String>> getShList(KtsqForm t, User user) throws Exception {
        return dao.getShList(t,user);
    }
    public boolean save(KtsqForm t) throws Exception {
        return dao.save(t);
    }
    public String getSplc(){
        return dao.getSplc();
    }
    public String yz(KtsqForm t,String lx){
        //������֤
        List<JfxxForm> jfxx = t.getJfxx();
        if(CollectionUtils.isEmpty(jfxx)){
            return "�����һ�δ����ס��Ϣ��";
        }
        for(JfxxForm a : jfxx){
            if(StringUtil.isNull(a.getWz())){
                return "��ַ����Ϊ�գ�";
            }
            if(StringUtil.isNull(a.getFtbl())){
                return "��̯��������Ϊ�գ�";
            }
        }
        if(StringUtil.isNull(t.getSynx())){
            return "����дʹ�����ޣ�";
        }
        if(StringUtil.isNull(t.getAzrq())){
            return "����д��װ���ڣ�";
        }
        //������֤
        //ҵ����֤
        if(!cwxxglDao.sfzjqs(t.getLddm(),t.getQsh(),t.getXh())){
            return "ֻ��Ϊ�Լ����������룡";
        }
        if("add".equals(lx)){
            if(dao.isExist(t)){
                return "�������������룬��ȷ�ϣ�";
            }
        }
        //ҵ����֤

        return "true";
    }
    public boolean sqSave(KtsqForm t) throws Exception {

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
        if(f){
            dao.jfxxBc(t);
        }
        if(f && "submit".equals(t.getType())){
            f = shlc.runSubmit(t.getSqid(), t.getSplc(), t.getXh(),
                    "gygl_gybz_ktsh.do",
                    "gygl_gybz_ktsq.do");
        }
        return f;
    }
    public boolean update(KtsqForm model) throws Exception {
        dao.jfxxBc(model);
        if("submit".equals(model.getType())){
            model.setShzt(Constants.YW_SHZ);
        } else {
            model.setShzt(Constants.YW_WTJ);
        }
        boolean flag = dao.runUpdate(model);
        if(flag && "submit".equals(model.getType())){
            flag = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),
                    "gygl_gybz_ktsh.do",
                    "gygl_gybz_ktsq.do");
        }
        return flag;
    }

    public boolean submit(KtsqForm model)throws Exception {
        return shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),
                "gygl_gybz_ktsh.do",
                "gygl_gybz_ktsq.do");
    }
    public List<HashMap<String,String>> getQscyJfList(String lddm,String qsh,String xn){
        return dao.getQscyJfList(lddm, qsh, xn);
    }

    public boolean saveSh(KtsqForm model, User user) throws Exception{

        ShxxModel shxxModel = new ShxxModel();
        // �������ID
        shxxModel.setShlc(model.getSplc());
        // �����
        shxxModel.setShr(user.getUserName());
        // ������
        shxxModel.setShyj(model.getShyj());
        // ���״̬
        shxxModel.setShzt(model.getShjg());
//        shxxModel.setThgw(model.getThgw());
        // ��˸�λID
        shxxModel.setGwid(model.getGwid());
        // ҵ��ID(��Ϊ����ID)
        shxxModel.setYwid(model.getSqid());
        shxxModel.setSqrid(model.getXh());
        shxxModel.setTzlj("gygl_gybz_ktsh.do");
        shxxModel.setTzljsq("gygl_gybz_ktsq.do");

        boolean result;
        String zhzt = shlc.runAuditing(shxxModel);
        model.setShzt(zhzt);
        result = dao.runUpdate(model);
        if(result && zhzt.equals(Constants.YW_TG)){
            KtsqjgForm jgForm = new KtsqjgForm();
            KtsqForm data = getModel(model.getSqid());
            BeanUtils.copyProperties(jgForm,data);
            jgForm.setSjly("1");
            result = ktsqjgDao.runInsert(jgForm);
        }
        return result;
    }

    public String plshBc(KtsqForm t, User user) throws Exception {

        KtsqForm model = new KtsqForm();
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
    public boolean cancelSh(KtsqForm model) throws Exception{
        model.setShzt(Constants.YW_SHZ);
        boolean flag = dao.runUpdate(model);
        if(flag){
            flag = dao.delJg(model.getSqid());
        }
        return flag;
    }

    public boolean delQscy(String[] ids) throws Exception {
        return dao.delQscy(ids);
    }
}
