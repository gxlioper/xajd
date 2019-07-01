package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

import java.util.HashMap;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-07-24 14:21
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BjxfjshbService extends SuperServiceImpl<BjxfjshbForm,BjxfjshbDao> {
    public static final String SUBMIT = "submit";
    public static final String SAVE = "save";
    private ShlcInterface shlc = new CommShlcImpl();

    /**
     * @����:��֤�ظ�
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/7/25 14:52
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: boolean
     */
    public boolean isExist(BjxfjshbForm model) throws Exception {
        boolean flag = false;
        String num = dao.checkExistForSave(model);
        if (!"0".equalsIgnoreCase(num)) {
            flag = true;
        }
        return flag;
    }

    public boolean saveBjxfjshb(BjxfjshbForm model) throws Exception {
        if(model.getType().equals(SUBMIT)){
            model.setShzt(Constants.YW_SHZ);//�����
        }else{
            model.setShzt(Constants.YW_WTJ);//δ�ύ
        }
        // ��ȡ��������
        String splc = dao.getShlcID(model.getHblx());
        model.setSplc(splc);
        boolean insertResult = super.runInsert(model);
        if(SAVE.equalsIgnoreCase(model.getType())){
            return insertResult;
        }
        boolean result = false;
        if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
            //�����������
            result = shlc.runSubmit(model.getSqid(),splc,model.getSqr(),"sxzzjy_bjxfjs_bjxfjssh.do","sxzzjy_bjxfjs_bjxfjshb.do");
        }
        return result;
    }

    public boolean updateYlbxsq(BjxfjshbForm model) throws Exception {
        if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
            // ��ȡ����������
            model.setSplc(dao.getShlcID(model.getHblx()));
        }

        if(model.getType().equals(SUBMIT)){
            model.setShzt(Constants.YW_SHZ);//�����
        }

        boolean insertResult = super.runUpdate(model);
        boolean result = true;
        if (insertResult && SUBMIT.equals(model.getType())) {
            shlc.deleteShjl(model.getSqid());
            result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"sxzzjy_bjxfjs_bjxfjssh.do","sxzzjy_bjxfjs_bjxfjshb.do");
        }
        return insertResult && result;
    }

    /**
     * @����:�ύ
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/7/27 10:19
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: boolean
     */
    public boolean bjxfjshbSubmit(BjxfjshbForm model) throws Exception {
        if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
            // ��ȡ����������
            model.setSplc(dao.getShlcID(model.getHblx()));
        }

        model.setShzt(Constants.YW_SHZ);
        boolean resultYlbxsq = dao.updateBjxfjshb(model);
        boolean result = false;
        if(resultYlbxsq){
            //�����������
            result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"sxzzjy_bjxfjs_bjxfjssh.do","sxzzjy_bjxfjs_bjxfjssq.do");
        }
        return result;
    }
    public int bjxfjshbDel(String[] jgids, String[] hblxs) throws Exception {
        return dao.bjxfjshbDel(jgids,hblxs);
    }

    /**
     * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/7/27 10:20
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [sqid, lcid]
     * @return: boolean
     */
    public boolean cancelRecord(String sqid, String lcid) throws Exception {
        return shlc.firstStepCancle(sqid,lcid);
    }
    public boolean bjxfjshbCancel(BjxfjshbForm model) throws Exception {
        return dao.updateBjxfjshb(model);
    }

    public HashMap<String,String> getInfo(BjxfjshbForm model) {
        return dao.getInfo(model);
    }
    /**
     * @����:��ӡ��Ϣ
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/8/2 14:44
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [id, hblx]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getWordInfo(String id,String hblx) {
        return dao.getWordInfo(id,hblx);
    }
}
