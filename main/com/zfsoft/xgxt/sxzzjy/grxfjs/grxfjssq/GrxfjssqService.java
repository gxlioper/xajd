package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjssq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-26 09:42
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GrxfjssqService extends SuperServiceImpl<GrxfjssqForm,GrxfjssqDao> {
    private ShlcInterface shlc = new CommShlcImpl();
    public static final String SUBMIT = "submit";
    public static final String SAVE = "save";

    /**
     * @����:��ȡ�����걨����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/26 10:12
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllSblx() {
        return dao.getAllSblx();
    }

    public boolean saveGrxfjssq(GrxfjssqForm model) throws Exception {
        if(model.getType().equals(SUBMIT)){
            model.setShzt(Constants.YW_SHZ);//�����
        }else{
            model.setShzt(Constants.YW_WTJ);//δ�ύ
        }
        // ��ȡ��������
        String splc = dao.getShlcID();
        model.setSplc(splc);
        boolean insertResult = super.runInsert(model);
        if(SAVE.equalsIgnoreCase(model.getType())){
            return insertResult;
        }
        boolean result = false;
        if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
            //�����������
            result = shlc.runSubmit(model.getSqid(),splc,model.getSqr(),"sxzzjy_grxfjs_grxfjssh.do","sxzzjy_grxfjs_grxfjssq.do");
        }
        return result;
    }

        public boolean updateGrxfjssq(GrxfjssqForm model) throws Exception {
        if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
            // ��ȡ����������
            model.setSplc(dao.getShlcID());
        }

        if(model.getType().equals(SUBMIT)){
            model.setShzt(Constants.YW_SHZ);//�����
        }

        boolean insertResult = super.runUpdate(model);
        boolean result = true;
        if (insertResult && SUBMIT.equals(model.getType())) {
            shlc.deleteShjl(model.getSqid());
            result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"sxzzjy_grxfjs_grxfjssh.do","sxzzjy_grxfjs_grxfjssq.do");
        }
        return insertResult && result;
    }

    /**
     * @����:�ύ
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/26 10:18
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: boolean
     */
    public boolean grxfjssqSubmit(GrxfjssqForm model) throws Exception {
        if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
            // ��ȡ����������
            model.setSplc(dao.getShlcID());
        }

        model.setShzt(Constants.YW_SHZ);
        boolean resultsq = dao.updateGrxfjssq(model);
        boolean result = false;
        if(resultsq){
            //�����������
            result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"sxzzjy_grxfjs_grxfjssh.do","sxzzjy_grxfjs_grxfjssq.do");
        }
        return result;
    }

    /**
     * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/26 10:19
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [sqid, lcid]
     * @return: boolean
     */
    public boolean cancelRecord(String sqid, String lcid) throws Exception {
        return shlc.firstStepCancle(sqid,lcid);
    }

    public boolean grxfjssqCancel(GrxfjssqForm model) throws Exception {
        return dao.updateGrxfjssq(model);
    }

    /**
     * @����:�ж��Ƿ����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/26 10:20
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: boolean
     */
    public boolean isExist(GrxfjssqForm model) throws Exception {
        boolean flag = false;
        String num = dao.checkExistForSave(model);
        if (!"0".equalsIgnoreCase(num)) {
            flag = true;
        }
        return flag;
    }

    public List<HashMap<String,String>> getStuList(GrxfjssqForm model, User user) throws Exception {
        return dao.getStuList(model,user);
    }

    public HashMap<String,String> getGrxfjssqInfo(GrxfjssqForm model) {
        return dao.getGrxfjssqInfo(model);
    }
}
