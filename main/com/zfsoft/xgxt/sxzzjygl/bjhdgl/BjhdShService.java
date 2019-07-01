package com.zfsoft.xgxt.sxzzjygl.bjhdgl;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BjhdShService extends SuperServiceImpl<BjhdShForm,BjhdShDao> {
    private ShlcInterface shlc = new CommShlcImpl();

    public List<HashMap<String,String>> getBjhdInfo(String sqid) {
        return dao.getBjhdInfo(sqid);
    }

    public boolean saveForDgsh(BjhdShForm bjhdShForm, User user) {

        ShxxModel model = new ShxxModel();
        // �������ID
        model.setShlc(bjhdShForm.getSplc());
        // �����
        model.setShr(user.getUserName());
        // ������
        model.setShyj(bjhdShForm.getShyj());
        // ���״̬
        model.setShzt(bjhdShForm.getShjg());
        //��λ�˻�
        model.setThgw(bjhdShForm.getThgw());
        // ��˸�λID
        model.setGwid(bjhdShForm.getGwid());
        // ҵ��ID(��Ϊ����ID)
        model.setYwid(bjhdShForm.getSqid());
        //������id
        model.setSqrid(bjhdShForm.getSqr());

        model.setTzlj("bjhdgl_bjhdsh.do?method=bjhdSh");
        model.setTzljsq( "bjhdgl_bjhdsq.do?method=bjhdSq");

        boolean reuslt = false;
        try {
            String shzt = shlc.runAuditing(model);
            bjhdShForm.setShzt(shzt);
            reuslt = dao.runUpdate(bjhdShForm);
            // ���浽�����
            if (shzt.equalsIgnoreCase(Constants.YW_TG)) {
                BjhdJgForm bjhdJgForm = new BjhdJgForm();
                BjhdJgService bjhdJgService = new BjhdJgService();
                BjhdShForm newBjhdSh = new BjhdShDao().getInfo(bjhdShForm.getSqid());
                //ƴ�����
                BeanUtils.copyProperties(bjhdJgForm,newBjhdSh);
                bjhdJgForm.setSjly("1");
                bjhdJgForm.setLrr(newBjhdSh.getSqr());
                bjhdJgForm.setLrsj(newBjhdSh.getSqsj());
                bjhdJgForm.setLcywid(newBjhdSh.getSqid());
                reuslt = bjhdJgService.runInsert(bjhdJgForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reuslt;
    }


    public String saveForPlsh(BjhdShForm bjhdShForm, User user) {
        BjhdShForm model = new BjhdShForm();
        String[] sqids = bjhdShForm.getSqids();
        String[] gwids = bjhdShForm.getGwids();
        String[] sqrs = bjhdShForm.getXhs();

        model.setSplc(bjhdShForm.getSplc());
        model.setShyj(bjhdShForm.getShyj());
        model.setShjg(bjhdShForm.getShjg());

        List<String> failXhs = new ArrayList<String>();
        for (int i = 0, n = sqids.length; i < n; i++) {

            model.setGwid(gwids[i]);
            model.setSqid(sqids[i]);
            model.setSqr(sqrs[i]);

            boolean isSuccess = saveForDgsh(model, user);
            if (!isSuccess) {
                failXhs.add(sqrs[i]);
            }
        }
        JSONArray json = JSONArray.fromObject(failXhs);
        if (failXhs.isEmpty()) {
            return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
        } else if (Constants.SH_TH.equals(bjhdShForm.getShzt())) {
            //���ݱ����������������ʱû��û�������˻�
            return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
        } else {
            return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
        }
    }

    public boolean cancelShLast(BjhdShForm bjhdShForm) throws Exception {
        bjhdShForm.setShzt(Constants.YW_SHZ);
        boolean result = dao.runUpdate(bjhdShForm);
        if (result) {
            //ɾ�������������ywidΪ sqid������
            new BjhdJgDao().doDeleteJg(new String[]{bjhdShForm.getSqid()});
        }
        return result;
    }

}
