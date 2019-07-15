package com.zfsoft.xgxt.dtjs.llxxjl.sh;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgDao;
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgForm;
import com.zfsoft.xgxt.dtjs.llxxjl.jg.LlxxjljgService;
import com.zfsoft.xgxt.dtjs.llxxjl.sq.LlxxjlsqDao;
import com.zfsoft.xgxt.dtjs.llxxjl.sq.LlxxjlsqForm;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� llf [����:1754]
 * @ʱ�䣺 2019-07-15 14:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class LlxxjlshService extends SuperServiceImpl<LlxxjlshForm, LlxxjlshDao> {
    private ShlcInterface shlc = new CommShlcImpl();

    public HashMap<String, String> getInfo(String sqid) {
        return dao.getInfo(sqid);

    }

    public boolean saveSh(LlxxjlshForm form, User user) {
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
        model.setSqrid(form.getXh());
        model.setTzlj("dtjs_llxxjl_sq.do");
        model.setTzljsq("dtjs_llxxjl_sh.do");
        boolean reuslt = false;
        try {
            String zhzt = shlc.runAuditing(model);
            LlxxjlshForm hdshForm = new LlxxjlshForm();
            hdshForm.setSqid(form.getSqid());
            hdshForm.setShzt(zhzt);
            hdshForm.setYxgs(form.getYxgs());
            reuslt = dao.runUpdate(hdshForm, form.getSqid());
            // ���浽�����
            if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
                LlxxjljgForm jgForm = new LlxxjljgForm();
                LlxxjljgService jgService = new LlxxjljgService();
                LlxxjlsqForm sqForm = new LlxxjlsqDao().getModel(form.getSqid());
                BeanUtils.copyProperties(jgForm, StringUtils.formatData(sqForm));
                jgForm.setLrr(user.getUserName());
                jgForm.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
                jgForm.setSqid(sqForm.getSqid());
                jgForm.setSjly("1");
                //��������ݲ���������ʱ���ظ�����ֱ�Ӹ���
                reuslt = jgService.delBySj(jgForm);
                if(reuslt)
                    reuslt = jgService.runInsert(jgForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reuslt;
    }

    /**
     * @����:�������
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2019/3/1 15:24
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [t, user]
     * @return: java.lang.String
     */
    public String savePlsh(LlxxjlshForm t, User user) throws Exception {
        LlxxjlshForm model = new LlxxjlshForm();
        String[] ids = t.getId();
        String[] gwid = t.getGwids();
        String[] xhs = t.getXhs();
        List<String> failXhs = new ArrayList<String>();
        for (int i = 0, n = ids.length; i < n; i++) {
            model.setSplc(t.getSplc());
            model.setYwid(ids[i]);
            model.setGwid(gwid[i]);
            model.setSqid(ids[i]);
            model.setShyj(t.getShyj());
            model.setShjg(t.getShzt());
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


    /**
     *
     * @����:���һ������
     * @���ߣ�xiaxia[���ţ�1104]
     * @���ڣ�2015-7-28 ����03:22:51
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param myForm
     * @return
     * @throws Exception boolean ��������
     * @throws
     */
    public boolean cancel(LlxxjlshForm myForm) throws Exception {
        myForm.setShzt(Constants.YW_SHZ);
        boolean result = dao.runUpdate(myForm, myForm.getSqid());
        if (result) {
            LlxxjljgDao jgDao = new LlxxjljgDao();
            jgDao.delSthdjgBySqId(myForm.getSqid());
        }
        return result;
    }
}
