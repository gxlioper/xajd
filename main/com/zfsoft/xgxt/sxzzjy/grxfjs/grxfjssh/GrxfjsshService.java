package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjssh;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb.GrxfjshbDao;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb.GrxfjshbForm;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb.GrxfjshbjgDao;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb.GrxfjshbjgForm;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjg.GrxfjsjgDao;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjg.GrxfjsjgForm;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-26 15:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GrxfjsshService extends SuperServiceImpl<GrxfjsshForm,GrxfjsshDao> {


    private ShlcInterface shlc = new CommShlcImpl();
    /**
     * @����:��˹����жϽ��������������
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/22 17:11
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: java.lang.String
     */
    public String checkExistForSave(GrxfjsshForm model) {
        return dao.checkExistForSave(model);
    }

    public boolean saveSh(GrxfjsshForm form,User user){
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
        model.setTzlj("sxzzjy_grxfjs_grxfjssh.do");
        if("sq".equals(form.getSqlx())){
            model.setTzljsq("sxzzjy_grxfjs_grxfjssq.do");
        }else{
            model.setTzljsq("sxzzjy_grxfjs_grxfjshb.do");
        }
        boolean reuslt = false;
        try {
            String zhzt = shlc.runAuditing(model);
            if("sq".equals(form.getSqlx())){
                GrxfjsshForm upForm = new GrxfjsshForm();
                upForm.setSqid(form.getSqid());
                upForm.setShzt(zhzt);
                reuslt = dao.runUpdate(upForm, form.getSqid());
                //���״̬Ϊͨ��
                if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
                    GrxfjsjgForm jgForm = new GrxfjsjgForm();
                    form = getModel(form);
                    BeanUtils.copyProperties(jgForm, StringUtils.formatData(form));
                    jgForm.setSjly("1");
                    jgForm.setLcywid(form.getSqid());
                    GrxfjsjgDao jgDao = new GrxfjsjgDao();
                    boolean flag = jgDao.deleteJg(jgForm);
                    if(flag)
                        jgDao.runInsert(jgForm);
                }
            }else{
                GrxfjshbForm upForm = new GrxfjshbForm();
                upForm.setSqid(form.getSqid());
                upForm.setShzt(zhzt);
                GrxfjshbDao grxfjshbDao = new GrxfjshbDao();
                reuslt = grxfjshbDao.runUpdate(upForm, form.getSqid());
                //���״̬Ϊͨ��
                if(zhzt.equalsIgnoreCase(Constants.YW_TG)){
                    GrxfjshbjgForm grxfjshbjgForm = new GrxfjshbjgForm();
                    upForm = grxfjshbDao.getModel(form.getSqid());
                    BeanUtils.copyProperties(grxfjshbjgForm, StringUtils.formatData(upForm));
                    grxfjshbjgForm.setSjly("1");
                    grxfjshbjgForm.setLcywid(form.getSqid());
                    GrxfjshbjgDao grxfjshbjgDao = new GrxfjshbjgDao();
                    boolean flag = grxfjshbjgDao.delete(grxfjshbjgForm);
                    if(flag)
                        grxfjshbjgDao.runInsert(grxfjshbjgForm);

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
    public String savePlsh(GrxfjsshForm t, User user) {
        String[] ids = t.getId();
        String[] splcs = t.getSplcs();
        String[] xhs = t.getXhs();
        String[] gwids = t.getGwids();
        String[] sqlxs = t.getSqlxs();
        List<String> fails = new ArrayList<String>();
        for (int i = 0, n = ids.length; i < n; i++) {
            GrxfjsshForm model = new GrxfjsshForm();
            model.setSqid(ids[i]);
            model.setSplc(splcs[i]);
            model.setShyj(t.getShyj());
            model.setShjg(t.getShzt());
            model.setGwid(gwids[i]);
            model.setSqlx(sqlxs[i]);
            boolean isSuccess = saveSh(model, user);
            if (!isSuccess) {
                fails.add(xhs[i]);
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
    public boolean newCancelSh(GrxfjsshForm model, User user){
        boolean result = false;
        try {
        //���������
            if("sq".equals(model.getSqlx())){
                model = getModel(model);
                result = dao.updateGrxfjssq(model.getSqid(), Constants.YW_SHZ);
                if(result && Constants.YW_TG.equals(model.getShzt())){
                    //ɾ�������
                    result = dao.deleteGrxfjsjg(model.getSqid());
                }
            }else{
                GrxfjshbDao grxfjshbDao = new GrxfjshbDao();
                GrxfjshbForm grxfjshbForm = grxfjshbDao.getModel(model.getSqid());
                result = grxfjshbDao.updateSq(model.getSqid(), Constants.YW_SHZ);
                if(result && Constants.YW_TG.equals(grxfjshbForm.getShzt())){
                    //ɾ�������
                    GrxfjshbjgDao grxfjshbjgDao = new GrxfjshbjgDao();
                    result = grxfjshbjgDao.deleteGrxfjshbjg(model.getSqid());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*try {
            model = getModel(model);
            //���������
            result = dao.updateBjxfjssq(model.getSqid(), Constants.YW_SHZ);
            if(result && Constants.YW_TG.equals(model.getShzt())){
                //ɾ�������
                result = dao.deleteBjxfjsjg(model.getSqid());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return result;
    }
}
