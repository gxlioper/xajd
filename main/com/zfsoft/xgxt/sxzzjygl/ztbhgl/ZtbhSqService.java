package com.zfsoft.xgxt.sxzzjygl.ztbhgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq.ZyfwSqForm;
import freemarker.template.SimpleDate;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class ZtbhSqService extends SuperServiceImpl<ZtbhSqForm,ZtbhSqDao> {
    private ShlcInterface shlc = new CommShlcImpl();
    public String[] getSqShKg() {
        return dao.getSqShKg();
    }

    public List<HashMap<String,String>> getXxList(ZtbhSqForm model, User user) throws Exception{
        return dao.getXxList(model,user);
    }

    public List<HashMap<String,String>> getBjList(ZtbhSqForm model, User user)throws Exception {
        return dao.getBjList(model,user);
    }

    public boolean ztbhSqSaveForAdd(ZtbhSqForm model) throws Exception {
        String sqid = UniqID.getInstance().getUniqIDHash();
        model.setSqid(sqid);
        //��ȡ�������������õ��������
        String splc = dao.getShlcID();
        model.setSplc(splc);
        if ("submit".equals(model.getType())) {
            model.setShzt(Constants.YW_SHZ);// �����
        } else {
            model.setShzt(Constants.YW_WTJ);// δ�ύ
        }
        // ����������Ϣ
        boolean result = dao.runInsert(model);
        // ���������Ϣ
        if ("submit".equals(model.getType())) {
            if (result) {
                result = shlc.runSubmit(sqid, splc, model.getSqr(),
                        "ztbhgl_ztbhsh.do?method=ztbhSh", "ztbhgl_ztbhsq.do?method=ztbhSq");
            }
        }
        return result;
    }

    public String[] getHdfzr(ZtbhSqForm model) {
        return dao.getHdfzr(model);
    }

    public String[] getBjmc(ZtbhSqForm model) {
        return dao.getBjmc(model);
    }

    public boolean ztbhSqSaveForEdit(ZtbhSqForm model) throws Exception {
        boolean result = false;
        // ����ύ���������״̬
        if ("submit".equalsIgnoreCase(model.getType())) {
            model.setShzt(Constants.YW_SHZ);// �����
            String splc = dao.getShlcID();
            model.setSplc(splc);
            result = runUpdate(model);
            if (result) {
                result = shlc.runSubmit(model.getSqid(), splc, model.getSqr() ,
                        "ztbhgl_ztbhsh.do?method=ztbhSh", "ztbhgl_ztbhsq.do?method=ztbhSq");
            }
        } else {
            result = runUpdate(model);
        }
        return result;
    }

    public boolean ztbhSqSubmit(ZtbhSqForm model) throws Exception {
        boolean result = false;
        model.setShzt(Constants.YW_SHZ);// �����
        String splc = dao.getShlcID();
        model.setSplc(splc);
        result = runUpdate(model);
        ShlcInterface shlc = new CommShlcImpl();
        if (result) {
            result = shlc.runSubmit(model.getSqid(), splc,model.getSqr() ,
                    "ztbhgl_ztbhsh.do?method=ztbhSh", "ztbhgl_ztbhsq.do?method=ztbhSq");
        }
        return result;
    }

    public boolean ztbhSqCancel(String sqid, String lcid) throws Exception {
        //ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
        boolean result = shlc.firstStepCancle(sqid, lcid);
        if (result) {
            // ����ҵ��״̬Ϊ'δ�ύ'
            ZtbhSqForm ztbhSqForm = new ZtbhSqForm();
            ztbhSqForm.setSqid(sqid);
            ztbhSqForm.setSplc(lcid);
            // �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
            ShlcDao shlcdao = new ShlcDao();
            if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
                ztbhSqForm.setShzt(Constants.YW_YTH);
            } else {
                ztbhSqForm.setShzt(Constants.YW_WTJ);
            }
            result = this.runUpdate(ztbhSqForm);
        }
        return result;
    }
}
