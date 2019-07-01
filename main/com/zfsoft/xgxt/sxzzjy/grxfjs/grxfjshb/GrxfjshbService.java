package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

import java.util.HashMap;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-08-02 17:24
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GrxfjshbService extends SuperServiceImpl<GrxfjshbForm,GrxfjshbDao> {
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
    public boolean isExist(GrxfjshbForm model) throws Exception {
        boolean flag = false;
        String num = dao.checkExistForSave(model);
        if (!"0".equalsIgnoreCase(num)) {
            flag = true;
        }
        return flag;
    }

    public boolean saveGrxfjshb(GrxfjshbForm model) throws Exception {
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
            result = shlc.runSubmit(model.getSqid(),splc,model.getSqr(),"sxzzjy_grxfjs_grxfjssh.do","sxzzjy_grxfjs_grxfjshb.do");
        }
        return result;
    }

    public boolean updateYlbxsq(GrxfjshbForm model) throws Exception {
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
            result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"sxzzjy_grxfjs_grxfjssh.do","sxzzjy_grxfjs_grxfjshb.do");
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
    public boolean grxfjshbSubmit(GrxfjshbForm model) throws Exception {
        if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
            // ��ȡ����������
            model.setSplc(dao.getShlcID(model.getHblx()));
        }

        model.setShzt(Constants.YW_SHZ);
        boolean resultYlbxsq = dao.updateGrxfjshb(model);
        boolean result = false;
        if(resultYlbxsq){
            //�����������
            result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"sxzzjy_grxfjs_grxfjssh.do","sxzzjy_grxfjs_grxfjssq.do");
        }
        return result;
    }
    public int grxfjshbDel(String[] jgids, String[] hblxs) throws Exception {
        return dao.grxfjshbDel(jgids,hblxs);
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
    public boolean grxfjshbCancel(GrxfjshbForm model) throws Exception {
        return dao.updateGrxfjshb(model);
    }

    public HashMap<String,String> getInfo(GrxfjshbForm model) {
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
