package com.zfsoft.xgxt.dtjs.shsjjl.sh;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.shsjjl.jg.ShsjjljgDao;
import com.zfsoft.xgxt.dtjs.shsjjl.jg.ShsjjljgForm;
import com.zfsoft.xgxt.dtjs.shsjjl.jg.ShsjjljgService;
import com.zfsoft.xgxt.dtjs.shsjjl.sq.ShsjjlsqDao;
import com.zfsoft.xgxt.dtjs.shsjjl.sq.ShsjjlsqForm;
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
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-03-01 09:23
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class ShsjjlshService extends SuperServiceImpl<ShsjjlshForm,ShsjjlshDao> {
    private ShlcInterface shlc = new CommShlcImpl();

    public HashMap<String, String> getInfo(String sqid) {
        return dao.getInfo(sqid);

    }

    public boolean saveSh(ShsjjlshForm form, User user) {
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
        model.setTzlj("dtjs_shsjjl_sq.do");
        model.setTzljsq("dtjs_shsjjl_sh.do");
        boolean reuslt = false;
        try {
            String zhzt = shlc.runAuditing(model);
            ShsjjlshForm hdshForm = new ShsjjlshForm();
            hdshForm.setSqid(form.getSqid());
            hdshForm.setShzt(zhzt);
            hdshForm.setYxgs(form.getYxgs());
            reuslt = dao.runUpdate(hdshForm, form.getSqid());
            // ���浽�����
            if (zhzt.equalsIgnoreCase(Constants.YW_TG)) {
                ShsjjljgForm jgForm = new ShsjjljgForm();
                ShsjjljgService jgService = new ShsjjljgService();
                ShsjjlsqForm sqForm = new ShsjjlsqDao().getModel(form.getSqid());
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
    public String savePlsh(ShsjjlshForm t, User user) throws Exception {
        ShsjjlshForm model = new ShsjjlshForm();
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
    public boolean cancel(ShsjjlshForm myForm) throws Exception {
        myForm.setShzt(Constants.YW_SHZ);
        boolean result = dao.runUpdate(myForm, myForm.getSqid());
        if (result) {
            ShsjjljgDao jgDao = new ShsjjljgDao();
            jgDao.delSthdjgBySqId(myForm.getSqid());
        }
        return result;
    }
}
