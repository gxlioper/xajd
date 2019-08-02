package com.zfsoft.xgxt.dtjs.llxxjl.sq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

import java.util.HashMap;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-03-01 09:22
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class LlxxjlsqService extends SuperServiceImpl<LlxxjlsqForm, LlxxjlsqDao> {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private ShlcInterface shlc = new CommShlcImpl();

    public boolean saveSthdsq(LlxxjlsqForm model) throws Exception {
        String sqid = UniqID.getInstance().getUniqIDHash();
        model.setSqid(sqid);
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
        if (!"save".equals(model.getType())) {
            if (result) {
                result = shlc.runSubmit(sqid, splc, sqid, "dtjs_llxxjl_sh.do", "dtjs_llxxjl_sq.do");
            }
        }
        return result;
    }

    public boolean saveUpdate( LlxxjlsqForm model) throws Exception {
        boolean result = false;
        // ����ύ���������״̬
        if ("submit".equalsIgnoreCase(model.getType())) {
            model.setShzt(Constants.YW_SHZ);// �����
            String splc = dao.getShlcID();
            model.setSplc(splc);
            result = runUpdate(model);
            ShlcInterface shlc = new CommShlcImpl();
            if (result) {
                result = shlc.runSubmit(model.getSqid(), splc, model.getSqid(), "dtjs_llxxjl_sh.do", "dtjs_llxxjl_sq.do");
            }
        } else {
            result = runUpdate(model);
        }
        return result;

    }

    public boolean submit(LlxxjlsqForm model) throws Exception {
        boolean result = false;
        model.setShzt(Constants.YW_SHZ);// �����
        String splc = dao.getShlcID();
        model.setSplc(splc);
        result = runUpdate(model);
        ShlcInterface shlc = new CommShlcImpl();
        if (result) {
            result = shlc.runSubmit(model.getSqid(), splc, model.getSqid(), "dtjs_llxxjl_sh.do", "dtjs_llxxjl_sq.do");
        }
        return result;
    }
    /**
     * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2019/3/1 11:47
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [ywid, lcid]
     * @return: boolean
     */
    public boolean cancelRecord(String ywid, String lcid) throws Exception {
        return shlc.firstStepCancle(ywid, lcid);
    }

    public boolean checkEdit(LlxxjlsqForm model) throws Exception {
        String num = dao.checkEdit(model);
        return "0".equals(num);
    }

    public HashMap<String,String> getInfo(String sqid) {
        return dao.getInfo(sqid);
    }
}
