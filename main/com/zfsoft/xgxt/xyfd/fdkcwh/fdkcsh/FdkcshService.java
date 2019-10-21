package com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsh;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcjg.FdkcjgDao;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcjg.FdkcjgForm;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcjg.FdkcjgService;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqDao;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqForm;
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
 * Created by llf on 2019/8/5.
 */
public class FdkcshService extends SuperServiceImpl<FdkcshForm,FdkcshDao> {
    private ShlcInterface shlc = new CommShlcImpl();

    public boolean saveSh(FdkcshForm form, User user) {
        ShxxModel model = new ShxxModel();
        // �������ID
        model.setShlc(form.getSplc());
        // �����
        model.setShr(user.getUserName());
        // ������
        model.setShyj(form.getShyj());
        // ���״̬
        model.setShzt(form.getShjg());
        model.setThgw(form.getThgw());
        // ��˸�λID
        model.setGwid(form.getGwid());
        // ҵ��ID(��Ϊ����ID)
        model.setYwid(form.getSqid());
        model.setSqrid(form.getLrr());
        model.setTzlj("xyfd_xyfd_fdkcsq.do");
        model.setTzljsq("xyfd_xyfd_fdkcsh.do");
        boolean reuslt = false;
        try {
            String zhzt = shlc.runAuditing(model);
            FdkcshForm fdkcshForm = new FdkcshForm();
            fdkcshForm.setSqid(form.getSqid());
            fdkcshForm.setShzt(zhzt);
            fdkcshForm.setYxgs(form.getYxgs());
            reuslt = dao.runUpdate(fdkcshForm, form.getSqid());
            // ���浽�����������̽�����
            if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
                FdkcjgForm jgForm = new FdkcjgForm();
                FdkcjgService jgService = new FdkcjgService();
                FdkcsqForm sqForm = new FdkcsqDao().getModel(form.getSqid());//��ȡ������Ϣ
                BeanUtils.copyProperties(jgForm, StringUtils.formatData(sqForm));//������

                jgForm.setLrr(sqForm.getLrr());
                jgForm.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
                jgForm.setSqid(sqForm.getSqid());
                jgForm.setSjly("1");

                reuslt = jgService.delPbjgBySqId(jgForm.getSqid());//ɾ�������sqid��ͬ������
                if(reuslt)
                    reuslt = jgService.runInsert(jgForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reuslt;
    }

    public boolean cancel(FdkcshForm myForm) throws Exception {
        myForm.setShzt(Constants.YW_SHZ);
        boolean result = dao.runUpdate(myForm, myForm.getSqid());
        if (result) {
            FdkcjgDao jgDao = new FdkcjgDao();
            jgDao.delPbjgBySqId(myForm.getSqid());
        }
        return result;
    }

    public String savePlsh(FdkcshForm t, User user) throws Exception {
        FdkcshForm model = new FdkcshForm();
        String[] ids = t.getId();
        String[] gwid = t.getGwids();
        String[] sqrids = t.getSqrids();
        String[] splcs = t.getSplcs();
        List<String> failXhs = new ArrayList<String>();
        for (int i = 0, n = ids.length; i < n; i++) {
            model.setYwid(ids[i]);
            model.setGwid(gwid[i]);
            model.setSqid(ids[i]);
            model.setShyj(t.getShyj());
            model.setShjg(t.getShzt());
            model.setSqrid(sqrids[i]);
            model.setSplc(splcs[i]);

            boolean isSuccess = saveSh(model, user);
            if (!isSuccess) {
                failXhs.add(sqrids[i]);
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
