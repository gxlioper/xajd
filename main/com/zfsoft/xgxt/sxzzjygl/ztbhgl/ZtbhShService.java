package com.zfsoft.xgxt.sxzzjygl.ztbhgl;

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

public class ZtbhShService extends SuperServiceImpl<ZtbhShForm,ZtbhShDao> {
    private ShlcInterface shlc = new CommShlcImpl();



    public List<HashMap<String,String>> getZtbhInfo(String sqid) {
        return dao.getZtbhInfo(sqid);
    }

    public boolean saveForDgsh(ZtbhShForm ztbhShForm, User user) {

        ShxxModel model = new ShxxModel();
        // �������ID
        model.setShlc(ztbhShForm.getSplc());
        // �����
        model.setShr(user.getUserName());
        // ������
        model.setShyj(ztbhShForm.getShyj());
        // ���״̬
        model.setShzt(ztbhShForm.getShjg());
        //��λ�˻�
        model.setThgw(ztbhShForm.getThgw());
        // ��˸�λID
        model.setGwid(ztbhShForm.getGwid());
        // ҵ��ID(��Ϊ����ID)
        model.setYwid(ztbhShForm.getSqid());
        //������id
        model.setSqrid(ztbhShForm.getSqr());

        model.setTzlj("ztbhgl_ztbhsh.do?method=ztbhSh");
        model.setTzljsq( "ztbhgl_ztbhsq.do?method=ztbhSq");

        boolean reuslt = false;
        try {
            String shzt = shlc.runAuditing(model);
            ztbhShForm.setShzt(shzt);
            reuslt = dao.runUpdate(ztbhShForm);
            // ���浽�����
            if (shzt.equalsIgnoreCase(Constants.YW_TG)) {
                ZtbhJgForm ztbhJgForm = new ZtbhJgForm();
                ZtbhJgService ztbhJgService = new ZtbhJgService();
                ZtbhShForm newZtbhSh = new ZtbhShDao().getInfo(ztbhShForm.getSqid());
                //ƴ�����
                BeanUtils.copyProperties(ztbhJgForm,newZtbhSh);
                ztbhJgForm.setSjly("1");
                ztbhJgForm.setLrr(newZtbhSh.getSqr());
                ztbhJgForm.setLrsj(newZtbhSh.getSqsj());
                ztbhJgForm.setLcywid(newZtbhSh.getSqid());
                reuslt = ztbhJgService.runInsert(ztbhJgForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reuslt;
    }

    public String saveForPlsh(ZtbhShForm ztbhShForm, User user) {
        ZtbhShForm model = new ZtbhShForm();
        String[] sqids = ztbhShForm.getSqids();
        String[] gwids = ztbhShForm.getGwids();
        String[] sqrs = ztbhShForm.getXhs();

        model.setSplc(ztbhShForm.getSplc());
        model.setShyj(ztbhShForm.getShyj());
        model.setShjg(ztbhShForm.getShjg());

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
        } else if (Constants.SH_TH.equals(ztbhShForm.getShzt())) {
            //���ݱ����������������ʱû��û�������˻�
            return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
        } else {
            return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
        }
    }

    public boolean cancelShLast(ZtbhShForm ztbhShForm) throws Exception {
        ztbhShForm.setShzt(Constants.YW_SHZ);
        boolean result = dao.runUpdate(ztbhShForm);
        if (result) {
            //ɾ�������������ywidΪ sqid������
            new ZtbhJgDao().doDeleteJg(new String[]{ztbhShForm.getSqid()});
            //�����������״̬
            ZtbhSqForm ztbhSqForm = new ZtbhSqForm();
            ztbhSqForm.setSqid(ztbhShForm.getSqid());
            ztbhSqForm.setShzt(Constants.YW_SHZ);
            new ZtbhSqDao().runUpdate(ztbhSqForm);
        }
        return result;
    }
}
