package com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhsq;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.cxcy.cssz.CsszService;
import net.sf.json.JSONObject;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-09-05 15:28
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BzsbwhsqService extends SuperServiceImpl<BzsbwhsqForm,BzsbwhsqDao> {
    private static final String MESSAGE_REPEAT = "ͬһѧ��ѧ����Ŀ���Ʋ����ظ���";
    private ShlcInterface shlc = new CommShlcImpl();

    public boolean save(BzsbwhsqForm model, User user) throws Exception{
        boolean rs = true;
        if("submit".equals(model.getType())){
            model.setShzt(Constants.YW_SHZ);
        }else{
            model.setShzt(Constants.YW_WTJ);
        }
        model.setSplc(new CsszService().getModel().getSplc());
        if(dao.checkExistForSave(model)){
            JSONObject json = new JSONObject();
            json.put("message", MESSAGE_REPEAT);
            throw new SystemException(json);
        }
        if(StringUtils.isNotNull(model.getSqid())){
            rs = dao.runUpdate(model);
        }else{
            model.setTbr(user.getUserName());
            model.setSqsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
            model.setSqid(UniqID.getInstance().getUniqIDHash().toUpperCase());
            rs = dao.runInsert(model);
        }
        if("submit".equals(model.getType())){
            if (rs) {
                rs = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(), "cxcy_bzsbwh_sq.do", "cxcy_bzsbwh_sh.do");
            }
        }
        return rs;
    }

    /**
     * @����:�ύ
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/9/6 8:49
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [model]
     * @return: boolean
     */
    public boolean bzsbwhsqSubmit(BzsbwhsqForm model) throws Exception {
        String splc = new  CsszService().getModel().getSplc();
        if(Constants.YW_YTH.equals(model.getShzt())){
            splc = model.getSplc();
        }
        model.setShzt(Constants.YW_SHZ);
        model.setSplc(splc);
        boolean flag = dao.runUpdate(model);
        if(flag){
            shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(), "cxcy_bzsbwh_sq.do", "cxcy_bzsbwh_sh.do");
        }
        return flag;
    }

    /**
     * @����:����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/9/6 8:48
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param:
     * @return:
     */
    public boolean bzsbwhsqCancel(String ywid,String lcid) throws Exception{
        return shlc.firstStepCancle(ywid, lcid);
    }

    public HashMap<String,String> getInfoById(String sqid) {
        return dao.getInfoById(sqid);
    }
}
