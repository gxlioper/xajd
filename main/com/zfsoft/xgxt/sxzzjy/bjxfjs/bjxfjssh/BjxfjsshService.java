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
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-22 15:33
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BjxfjsshService extends SuperServiceImpl<BjxfjsshForm,BjxfjsshDao> {

    private ShlcInterface shlc = new CommShlcImpl();
    /**
     * @����:��˹����жϽ��������������
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/22 17:11
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: java.lang.String
     */
    public String checkExistForSave(BjxfjsshForm model) {
        return dao.checkExistForSave(model);
    }

    public boolean saveSh(BjxfjsshForm form,User user){
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
        // ======= ҵ���ֶ� begin======
        model.setSqrid(form.getSqr());
        // ======= ҵ���ֶ� end======
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
                //���״̬Ϊͨ��
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
                //���״̬Ϊͨ��
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
     * @����:������˱���
     * @param model
     * @param user
     * @return
     * String ��������
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
     * @����:�������
     * @param model
     * @return
     * boolean ��������
     * @throws
     */
    public boolean newCancelSh(BjxfjsshForm model, User user){
        boolean result = false;
        try {

            //���������
            if("sq".equals(model.getSqlx())){
                model = getModel(model);
                result = dao.updateBjxfjssq(model.getSqid(), Constants.YW_SHZ);
                if(result && Constants.YW_TG.equals(model.getShzt())){
                    //ɾ�������
                   result = dao.deleteBjxfjsjg(model.getSqid());
                }
            }else{
                BjxfjshbDao bjxfjshbDao = new BjxfjshbDao();
                BjxfjshbForm bjxfjshbForm = bjxfjshbDao.getModel(model.getSqid());
                result = bjxfjshbDao.updateSq(model.getSqid(), Constants.YW_SHZ);
                if(result && Constants.YW_TG.equals(bjxfjshbForm.getShzt())){
                    //ɾ�������
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
