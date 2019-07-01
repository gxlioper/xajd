package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjssq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:�༶ѧ�罨������
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-20 14:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BjxfjssqService extends SuperServiceImpl<BjxfjssqForm,BjxfjssqDao>{
    public static final String SUBMIT = "submit";
    public static final String SAVE = "save";
    private ShlcInterface shlc = new CommShlcImpl();

    /**
     * @����:��ѯ�����ϱ�����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/22 9:48
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: []
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getAllSblx() {
        return dao.getAllSblx();
    }

    /**
     * @����:��ȡ�༶�б�
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/22 9:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model, user]
     * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
     */
    public List<HashMap<String,String>> getBjList(BjxfjssqForm model, User user) throws Exception {
        return dao.getBjList(model,user);
    }

    /**
     * @����:��ȡ�༶��Ϣ
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/22 9:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [bjdm]
     * @return: java.util.HashMap<java.lang.String,java.lang.String>
     */
    public HashMap<String,String> getBjInfo(String bjdm) {
        return dao.getBjInfo(bjdm);
    }

    public boolean saveBjxfjssq(BjxfjssqForm model) throws Exception {
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
            result = shlc.runSubmit(model.getSqid(),splc,model.getSqr(),"sxzzjy_bjxfjs_bjxfjssh.do","sxzzjy_bjxfjs_bjxfjssq.do");
        }
        return result;
    }

    public boolean updateYlbxsq(BjxfjssqForm model) throws Exception {
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
            result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"sxzzjy_bjxfjs_bjxfjssh.do","sxzzjy_bjxfjs_bjxfjssq.do");
        }
        return insertResult && result;
    }

    /**
     * @����:�ύ
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/22 14:34
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: boolean
     */
    public boolean bjxfjssqSubmit(BjxfjssqForm model) throws Exception {
        if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
            // ��ȡ����������
            model.setSplc(dao.getShlcID());
        }

        model.setShzt(Constants.YW_SHZ);
        boolean resultYlbxsq = dao.updateBjxfjssq(model);
        boolean result = false;
        if(resultYlbxsq){
            //�����������
            result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"sxzzjy_bjxfjs_bjxfjssh.do","sxzzjy_bjxfjs_bjxfjssq.do");
        }
        return result;
    }

    public HashMap<String,String> getBjxfjssqInfo(BjxfjssqForm model) {
        return dao.getBjxfjssqInfo( model);
    }

    /**
     * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/22 15:24
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [sqid, lcid]
     * @return: boolean
     */
    public boolean cancelRecord(String sqid, String lcid) throws Exception {
        return shlc.firstStepCancle(sqid,lcid);
    }

    public boolean bjxfjssqCancel(BjxfjssqForm model) throws Exception {
        return dao.updateBjxfjssq(model);
    }

    /**
     * @����:�ж��Ƿ����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/6/22 9:50
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: boolean
     */
    public boolean isExist(BjxfjssqForm model) throws Exception {
        boolean flag = false;
        String num = dao.checkExistForSave(model);
        if (!"0".equalsIgnoreCase(num)) {
            flag = true;
        }
        return flag;
    }
}
